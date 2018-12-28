package lab.Server.ORM;

public class RefAttribute extends Attribute
{
    private String refAttribName;
    private String refTableName;

    public RefAttribute(String name, String tableName, String refAttribName)
    {
        super(name);

        this.refTableName = tableName;
        this.refAttribName = refAttribName;
    }

    public RefAttribute(Attribute attr, String refTableName, String refAttribName)
    {
        super(attr.getName(), attr.isNotNull(), attr.isPrimaryKey());

        this.refTableName = refTableName;
        this.refAttribName = refAttribName;
    }

    @Override
    public String toString()
    {
        return super.toString()+" REFERENCES " + refTableName +" (" + refAttribName + ")";
    }
}
