package lab.Server.ORM;



import lab.Server.ORM.annotations.*;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListResourceBundle;

public class ClassAnalizer
{
    public static Table parse(Class<?> c)
    {
        boolean hasSerialPrimaryKey = false;

        if(!c.isAnnotationPresent(DBTable.class))
            throw new IllegalArgumentException(c.toString()+" doesnt support sql convertation");

        if(c.isAnnotationPresent(SerialID.class))
            hasSerialPrimaryKey = true;

        String tableName = c.getAnnotation(DBTable.class).name();

        Table table = new Table(tableName, hasSerialPrimaryKey);

        List<Field> fields = getFieldsWithoutRefs(c);

        for(Field f : fields)
            table.addAttribute(parseAttrib(f));

        return table;

    }

    public static List<Table> parseWithConnections(Class<?> c)
    {
        List<Table> tables = new ArrayList<>();

        Table table = parse(c);

        tables.add(table);

        List<Field> refsFields = getRefsField(c);

        for(Field f : refsFields)
        {
            List<Table> refTables;
            if(f.getGenericType() instanceof ParameterizedType)
            {
                refTables = parseWithConnections((Class)((ParameterizedType) f.getGenericType()).getActualTypeArguments()[0]);
            }
            else
                refTables = parseWithConnections(f.getType());

            refTables.get(0).addAttribute(parseRefAttribute(f, table.getName()));

            tables.addAll(refTables);
        }


        return tables;
    }

    private static Attribute parseAttrib(Field field)
    {
        String name = field.getName();

        Attribute attribute = new Attribute(name);

        if(field.isAnnotationPresent(NotNullAttrib.class))
            attribute.setNotNull(true);

        if(field.isAnnotationPresent(PrimaryKey.class))
            attribute.setPrimaryKey(true);

        if(field.isAnnotationPresent(AttribType.class))
        {
            String type = field.getAnnotation(AttribType.class).typeName();
          
            attribute.setType(type);
        }

        return attribute;
    }

    private static RefAttribute parseRefAttribute(Field field, String tableName)
    {
        Type genType = field.getGenericType();


        String refAttribName = field.getAnnotation(ConnectionAttrib.class).referenceAttribName();
        String refName = field.getAnnotation(ConnectionAttrib.class).referenceName();
        String refType = field.getAnnotation(ConnectionAttrib.class).sqlType();

        if( genType instanceof ParameterizedType)
        {
            RefAttribute refAttribute = new RefAttribute(refName, tableName, refAttribName);

            if(field.isAnnotationPresent(NotNullAttrib.class))
                refAttribute.setNotNull(true);

            refAttribute.setType(refType);

            return refAttribute;
        }

        RefAttribute refAttrib = new RefAttribute(parseAttrib(field), tableName, refAttribName);

        refAttrib.setPrimaryKey(false);
        refAttrib.setName(refName);
        refAttrib.setType(refType);

        return refAttrib;
    }

    private static List<Field> getFieldsWithoutRefs(Class<?> c)
    {
        List<Field> fields = new ArrayList<>();

        Class curClass = c;

        while(curClass != null)
        {
            for(Field f : curClass.getDeclaredFields())
            {
                if( f.isAnnotationPresent(IgnoreField.class) || f.isAnnotationPresent(ConnectionAttrib.class))
                    continue;

                fields.add(f);
            }

            curClass = curClass.getSuperclass();
        }

        return fields;
    }

    private static List<Field> getRefsField(Class<?> c)
    {
        List<Field> fields = new ArrayList<>();

        Class curClass = c;

        while(curClass != null)
        {
            for(Field f : curClass.getDeclaredFields())
            {
                if( f.isAnnotationPresent(IgnoreField.class) )
                    continue;
                else if(f.isAnnotationPresent(ConnectionAttrib.class))
                {
                    fields.add(f);
                }
            }

            curClass = curClass.getSuperclass();
        }

        return fields;

    }
}
