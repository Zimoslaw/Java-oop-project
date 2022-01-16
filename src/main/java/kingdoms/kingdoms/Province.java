package kingdoms.kingdoms;

/**
<p>Class for implementing a province - a teoretical geographic area, that can be own by a ruler (@see Ruler) and is used to calculate a strength (army) of a ruler,
stability of a duchy/kingdom/empire and influence of a empire</p>
<p>Province has only an area</p>
@author Jakub Niewiarowski
 */

public class Province extends BaseObject {

    private int area;

    /**
    Constructs a province with given name and area
    @param name Name of the province
    @param area Area of the province (in square kilometers)
     */
    public Province(String name, int area)
    {
        super(name);
        this.area = area;
    }

    /**
     * Returns area of the province
    @return Area of the province
    */
    public int getArea() {
        return area;
    }

    /**
    Sets a new area of the province
    @param area New area of the province
     */
    public void setArea(int area)
    {
        this.area = area;
    }

    /**
    Prints out all parameters of the province
    @return Stylized string with all the parameters
     */
    public String ToString()
    {
        return "(ID: "+this.getId()+") \""+this.getName()+"\", Powierzchnia: "+area;
    }
}
