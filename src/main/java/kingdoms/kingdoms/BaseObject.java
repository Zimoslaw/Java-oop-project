package kingdoms.kingdoms;

/**
<p>Class implementing a base object with its unique ID, name and type. All objects extend this class</p>
@author Jakub Niewiarowski
 */

class BaseObject implements IData {

    private long id; 
    private String name;
    private Classes type;
    private static long idNumerator = -1;

    /**
    Constructs a base object with given name
    @param name name of the object
     */
    public BaseObject(String name)
    {
        this.name = name;
        this.type = Classes.Wszystkie;
        this.id = getNextIdNumerator();
    }

    /**
    Incerements static ID variable in order to set unique ID for each new created object in database
    @return next ID in database
     */
    public static long getNextIdNumerator() {
        idNumerator++;
        return idNumerator;
    }

    /**@return ID of the object */
    public long getId() { return id; }

    /**@return Name of the object */
    public String getName() { return name; }

    /**
    Sets a new name of the object
    @param name Name of the object
     */
    public void setName(String name) { this.name = name; }

    /**
    Prints out all parameters of the object
    @return Stylized string with all the parameters
     */
    public String ToString()
    {
        return "(ID: "+id+") \""+name+"\"";
    }
}
