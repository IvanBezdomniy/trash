package lab.Server.ORM;



import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.parser.JSONParser;
import lab.Server.ORM.annotations.AttribType;
import lab.Server.ORM.annotations.DBTable;
import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SQLCommands
{


    private Table table;
    private Class<?> c;

    public SQLCommands(Class<?> c)
    {
        this.c = c;
        this.table = ClassAnalizer.parse(c);
    }

    public String create()
    {
        return "CREATE TABLE "+table.toString();
    }

    public String createWithDependencies()
    {
        StringBuilder sqlBuilder = new StringBuilder();

        for(Table t : ClassAnalizer.parseWithConnections(c))
        {


            sqlBuilder.append("CREATE TABLE ");
            sqlBuilder.append(t.toString());
            sqlBuilder.append("\n");
        }

        return sqlBuilder.toString();
    }
    public String insert(String jsonString){
        StringBuilder sqlBuilder = new StringBuilder();
        for(Table t : ClassAnalizer.parseWithConnections(c)) {

            sqlBuilder.append("INSERT INTO ");
            sqlBuilder.append(c.getAnnotation(DBTable.class).name());
            sqlBuilder.append("(");
            for (Attribute row:table.getAttributes()
                 ) {
                String s =row.toString();
                s=s.substring(0,s.indexOf(" "));
                s=s+", ";
                sqlBuilder.append(s);
            }

            sqlBuilder.delete(sqlBuilder.length()-2,sqlBuilder.length()-1);
            sqlBuilder.append(") ");
            sqlBuilder.append("VALUES(");
            for (Attribute row:table.getAttributes()
            ) {


                String s =row.toString();
                s=s.substring(0,s.indexOf(" "));

            }
//            String tableName = c.getAnnotation(DBTable.class).name();
        }
        return sqlBuilder.toString();
    }

    public String select(List<Attribute> attributes)
    {
        if(!table.getAttributes().containsAll(attributes))
            throw new IllegalArgumentException("No such attributes in table "+table.getName());

        StringBuilder sqlBuilder = new StringBuilder();
        String tableName = c.getAnnotation(DBTable.class).name();

        if(attributes.size() == 0)
            return "SELECT * FROM " + tableName;

        sqlBuilder.append("SELECT ");

        sqlBuilder.append(attributes.get(0).getName());

        for(int i = 1; i < attributes.size(); i++)
        {
            sqlBuilder.append(",");
            sqlBuilder.append(attributes.get(i).getName());
        }

        sqlBuilder.append(" FROM ");
        sqlBuilder.append(tableName+";");

        return sqlBuilder.toString();
    }

    public String update(Map<Attribute,String> newValues, Map<Attribute, String> conditions)
    {

        String tableName = c.getAnnotation(DBTable.class).name();

        String result = "UPDATE " + tableName + "\n" + "SET ";

        Attribute[] newValAttribs =  newValues.keySet().toArray(new Attribute[conditions.size()]);

        if(!table.getAttributes().containsAll(newValues.keySet()))
            throw new IllegalArgumentException("No such attributes in table "+table.getName());

        result = result + newValAttribs[0].getName() + "=";

        if(newValAttribs[0].getType().equals("integer"))
            result = result+newValues.get(newValAttribs[0]);
        else
            result = result+"\'"+newValues.get(newValAttribs[0])+"\'";

        for(int i = 1; i < newValAttribs.length; i++)
        {
            result = result + "," + newValAttribs[i].getName() + "=";

            if(newValAttribs[i].getType().equals("integer"))
                result = result+newValues.get(newValAttribs[i]);
            else
                result = result+"\'"+newValues.get(newValAttribs[i])+"\'";
        }



        result = result+ makeWhereString(conditions) +";";

        return result;

    }

    public String delete(Map<Attribute, String> conditions)
    {
        String tableName = c.getAnnotation(DBTable.class).name();

        String result = "DELETE FROM "+tableName + makeWhereString(conditions)+";";

        return result;
    }

    private String makeWhereString(Map<Attribute, String> conditions)
    {
        String result = "";

        if(conditions.size() > 0)
        {
            result = result + "\nWHERE ";

            Attribute[] condAttribs =  conditions.keySet().toArray(new Attribute[conditions.size()]);

            if(!table.getAttributes().containsAll(conditions.keySet()))
                throw new IllegalArgumentException("No such attributes in table "+table.getName());

            result = result + condAttribs[0].getName() + "=";

            if(condAttribs[0].getType().equals("integer"))
                result = result+conditions.get(condAttribs[0]);
            else
                result = result+"\'"+conditions.get(condAttribs[0])+"\'";

            for(int i = 1; i < condAttribs.length; i++)
            {
                result = result + "," + condAttribs[i].getName() + "=";

                if(condAttribs[i].getType().equals("integer"))
                    result = result+conditions.get(condAttribs[i]);
                else
                    result = result+"\'"+conditions.get(condAttribs[i])+"\'";
            }
        }

        return result;

    }

    public List<Attribute> getAttributes()
    {
        return table.getAttributes();
    }
}
