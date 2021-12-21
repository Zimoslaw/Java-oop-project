package kingdoms.kingdoms;

import java.util.ArrayList;
import java.util.List;

/**
<p>Implements a duchy, a state of one or more provinces, ruled by a ruler. It has its stability (how well is living in this state @see Stability)
and centralization (how much power does a ruler have over this state @see Centralization)</p>
 */

public class Duchy extends Province {

    private Ruler ruler;
    private List<Province> provinces;
    private Stability stability;
    private Centralization centralization;

    /**
    Constructs a duchy witch given name, ruler, provinces and centralization
    @param name Name of the duchy
    @param ruler Duchy's ruler
    @param provinces List of provinces held by the duchy
    @param centralization Centralization of the duchy (enum)
    */
    public Duchy(String name, Ruler ruler, List<Province> provinces, Centralization centralization)
    {
        super(name, 0);
        this.ruler = ruler;
        //this.provinces = new ArrayList<Province>();
        this.provinces = provinces;
        this.centralization = centralization;
        this.setArea(getArea());
        updateStability();
        this.ruler.updateStrength(getArea()*(5-centralization.level));
    }

    /**
    Overrides getArea() from Province class. Sum of provinces area held by the duchy
    @return Total area of duchy's provinces */
    public int getArea()
    {
        int area = 0;
        for(Province p : provinces)
        {
            area += p.getArea();
        }
        return area;
    }

    /**
    @return Duchy's ruler
     */
    public Ruler getRuler()
    {
        return ruler;
    }

    /**
    Sets a new ruler of the duchy
    @param ruler New Ruler of the duchy
     */
    public void setRuler(Ruler ruler)
    {
        this.ruler = ruler;
        updateStability();
    }

    /**
    @return List of provinces held by the duchy */
    public List<Province> getProvinces() {
        return provinces;
    }

    /**
    Adds a province to duchy
    @param province New province to add
     */
    public void addProvince(Province province)
    {
        provinces.add(province);
    }

    /**
    @return Duchy's stability */
    public Stability getStability()
    {
        return stability;
    }

    /**
    Sets new stability
    @param stability New stability enum of the duchy
     */
    public void setStability(Stability stability) { this.stability = stability; }

    /**
    Updates stability of the duchy based on number of its provinces, prestige and age of ruler, and centralization */
    public void updateStability()
    {
        int domense = provinces.size() > 25 ? 25 : provinces.size();
        int prestige = ruler.getPrestige() > 100 ? 100 : ruler.getPrestige();
        short age = ruler.getAge() > 100 ? 100 : ruler.getAge();
        int centralization = this.centralization.level;
        int stability = Math.round((((prestige+100) * 2 * age)/100 - domense*centralization)/40);

        stability = stability < 0 ? 0 : stability;

        switch(stability)
        {
            case 0:
                this.stability = Stability.Anarchy;
            break;
            case 1:
                this.stability = Stability.Unstable;
            break;
            case 2:
                this.stability = Stability.Balance;
            break;
            case 3:
                this.stability = Stability.Stable;
            break;
            case 4:
                this.stability = Stability.Prosperity;
            break;
            default:
                this.stability = Stability.Prosperity;
            break;
        }
    }

    /**
    @return Duchy's centralization
     */
    public Centralization getCentralization()
    {
        return centralization;
    }

    /**
    Sets a new centralization
    @param centralization New duchy's centralization enum
     */
    public void setCentralization(Centralization centralization)
    {
        this.centralization = centralization;
    }

    /**
    Converts stability enum to coresponding decimal level
    @param stability Stability enum
    @return  Intiger expressing stability level
    */
    public static int StabilityToInt(Stability stability)
    {
        switch(stability)
        {
            case Anarchy:
                return(1);
            case Unstable:
                return(2);
            case Balance:
                return(3);
            case Stable:
                return(4);
            case Prosperity:
                return(5);
            default:
                return(1);
        }
    }

    /**
    Prints out all parameters of the duchy
    @return Stylized string with all the duchy's parameters
     */
    public String ToString()
    {
        String info = "(ID: "+this.getId()+") \""+this.getName()+"\", Powierzchnia: "+this.getArea()+",\n-Władca: "+getRuler().ToString()+"\n---\n-Stabilność: "+stability.name+",\n-Centralizacja: "+centralization.name()+",\n------Prowincje:";

        if(!provinces.isEmpty())
        {
            for (Province province : provinces)
            {
                info += "\n\t\t" + province.ToString();
            }
        }

        info+="\n------";

        return info;
    }
}
