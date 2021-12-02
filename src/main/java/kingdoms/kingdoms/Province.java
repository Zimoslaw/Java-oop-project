package kingdoms.kingdoms;

public class Province extends BaseObject {

    private int area;

    public Province(String name, int area)
    {
        super(name);
        this.area = area;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area)
    {
        this.area = area;
    }

    public String ToString()
    {
        return "(ID: "+this.getId()+") \""+this.getName()+"\", Powierzchnia: "+area;
    }
}
