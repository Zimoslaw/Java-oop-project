package kingdoms.kingdoms;

import java.util.ArrayList;
import java.util.List;

public class Duchy extends Province {

    private Ruler ruler;
    private List<Province> provinces;
    private Stability stability;
    private Centralization centralization;

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

    public int getArea()
    {
        //if(provinces.isEmpty())
            //return 0;

        int area = 0;
        for(Province p : provinces)
        {
            area += p.getArea();
        }
        return area;
    }

    public Ruler getRuler()
    {
        return ruler;
    }

    public void setRuler(Ruler ruler)
    {
        this.ruler = ruler;
        updateStability();
    }

    public List<Province> getProvinces() {
        return provinces;
    }

    public void addProvince(Province province)
    {
        provinces.add(province);
    }

    public Stability getStability()
    {
        return stability;
    }

    public void setStability(Stability stability) { this.stability = stability; }

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

    public Centralization getCentralization()
    {
        return centralization;
    }

    public void setCentralization(Centralization centralization)
    {
        this.centralization = centralization;
    }

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
