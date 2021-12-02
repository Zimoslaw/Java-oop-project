package kingdoms.kingdoms;

class BaseObject implements IData {

    private long id;
    private String name;
    private Classes type;
    private static long idNumerator = -1;

    public BaseObject(String name)
    {
        this.name = name;
        this.type = Classes.Wszystkie;
        this.id = getNextIdNumerator();
    }

    public static long getNextIdNumerator() {
        idNumerator++;
        return idNumerator;
    }

    public long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String ToString()
    {
        return "(ID: "+id+") \""+name+"\"";
    }
}
