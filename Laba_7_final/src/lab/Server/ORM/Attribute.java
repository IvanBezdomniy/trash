package lab.Server.ORM;

public class Attribute
{
    private String name;
    private boolean primaryKey = false;
    private boolean notNull = false;
    private String type = "text";

    public Attribute(String name)
    {
        this.name = name;
    }

    public Attribute(String name, boolean isNotNull)
    {
        this.name = name;
        this.notNull = isNotNull;
    }

    public Attribute(String name, boolean isNotNull, boolean isPrimaryKey)
    {
        this.name = name;
        this.notNull = isNotNull;
        this.primaryKey = isPrimaryKey;
    }


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isPrimaryKey()
    {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey)
    {
        this.primaryKey = primaryKey;
    }

    public boolean isNotNull()
    {
        return notNull;
    }

    public void setNotNull(boolean notNull)
    {
        this.notNull = notNull;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append(name);
        sqlBuilder.append(" ");
        sqlBuilder.append(type);

        if(notNull)
            sqlBuilder.append(" NOT NULL");

        if(primaryKey)
            sqlBuilder.append(" PRIMARY KEY");

        return sqlBuilder.toString();

    }

    @Override
    public boolean equals(Object obj)
    {
        if( obj == null)
            return false;

        if(!(obj instanceof Attribute))
            return false;

        Attribute attrib = (Attribute)obj;

        return name.equals(attrib.getName());
    }
}
