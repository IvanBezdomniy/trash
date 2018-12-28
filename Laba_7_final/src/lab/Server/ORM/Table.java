package lab.Server.ORM;

import java.util.ArrayList;
import java.util.List;

public class Table
{
    private String name;
    private List<Attribute> attributes = new ArrayList<>();
    private boolean serialID = false;

    public Table(String name)
    {
        this.name = name;
    }

    public Table(String name, boolean serialID)
    {
        this.name = name;
        this.serialID = serialID;
    }

    public void addAttribute(Attribute attrib)
    {
        attributes.add(attrib);
    }

    public List<Attribute> getAttributes()
    {
        return attributes;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isSerialID()
    {
        return serialID;
    }

    public void setSerialID(boolean serialID)
    {
        this.serialID = serialID;
    }

    @Override
    public String toString()
    {
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append(name);
        sqlBuilder.append("(\n");

        int index = 0;

        if(serialID)
            sqlBuilder.append("ID serial PRIMARY KEY");
        else
        {
            sqlBuilder.append(attributes.get(index));
            index++;
        }

        for(int i = index; i < attributes.size(); i++)
        {
            sqlBuilder.append(",\n");
            sqlBuilder.append(attributes.get(i));
        }

        sqlBuilder.append("\n);");

        return sqlBuilder.toString();
    }
}
