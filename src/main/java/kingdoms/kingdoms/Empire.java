package kingdoms.kingdoms;

import java.util.ArrayList;
import java.util.List;

/**
<p>Implements an empire - a higher level of state that holds at least one kingdom. It has all parametres of a kingdom and additionaly a influence level</p>
 */

public class Empire extends Kingdom
{
    private int influence;
    private List<Kingdom> kingdoms;

    /**
    Constructs empire with name, ruler, kingdoms and centralization
    @param name Name of the empire
    @param ruler Ruler of the empire
    @param kingdoms List of kingdoms held by the empire
    @param centralization Empire's centralization enum
    */
    public Empire(String name, Ruler ruler, List<Kingdom> kingdoms, Centralization centralization)
    {
        super(name, ruler, kingdoms, centralization, 0);
        this.kingdoms = kingdoms;
        updateLegitimacy();
        updateStability();
        updateInfluence();
        this.getRuler().updateStrength(getArea());
    }

    /**
    @return Influence of the empire*/
    public int getInfluence() {
        return influence;
    }

    /**
    @return Kindoms held by the empire. Overrides getProvinces() from Duchy because Empire holds only kingdoms, not provinces */
    public List<Kingdom> getProvinces()
    {
        return kingdoms;
    }

    /**
    Adds new kingdom to the empire
    @param kingdom New kingdom to add
     */
    public void addProvince(Kingdom kingdom)
    {
        kingdoms.add(kingdom);
    }

    /**
    @return Total area of kingdoms in the Empire */
    public int getArea()
    {
        int area = 0;
        for(Kingdom kingdom : kingdoms)
            area += kingdom.getArea();
        return area;
    }

    /**
    Updates empire's influence based on its area, centralization, legitimacy and ruler's prestige */
    public void updateInfluence() {
        int stability = StabilityToInt(this.getStability());

        influence = getArea() * this.getCentralization().level * stability + this.getLegitimacy() + this.getRuler().getPrestige();
    }

    /**
    Updates empire's legitimacy. Empire's legitimacy is an average legitimacy of its kingdoms */
    public void updateLegitimacy()
    {
        int l = 0;
        for(Kingdom kingdom : kingdoms)
            l += kingdom.getLegitimacy();
        l /= kingdoms.size();
        setLegitimacy(l);
    }

    /**
    Updates empire's stability. Empire's stability is an average stability of its kingdoms */
    public void updateStability()
    {
        if(kingdoms == null)
        {
            this.setStability(Stability.Prosperity);
        }
        else
        {
            int stability = 0;
            for(Kingdom k : kingdoms)
                stability += StabilityToInt(k.getStability());
            stability /= kingdoms.size();

            switch(stability)
            {
                case 0:
                    this.setStability(Stability.Anarchy);
                    break;
                case 1:
                    this.setStability(Stability.Unstable);
                    break;
                case 2:
                    this.setStability(Stability.Balance);
                    break;
                case 3:
                    this.setStability(Stability.Stable);
                    break;
                case 4:
                    this.setStability(Stability.Prosperity);
                    break;
                default:
                    this.setStability(Stability.Prosperity);
                    break;
            }
        }
    }

    /**
    Prints out all parameters of the kingdom
    @return Stylized string with all the kingdom's parameters
     */
    public String ToString()
    {
        String info = "(ID: "+this.getId()+") \""+this.getName()+"\",\n-Władca: "+this.getRuler().ToString()+"\n---\nPowierzchnia: "+this.getArea()+",\n-Stabilność: "+this.getStability().name+",\n-Centralizacja: "+this.getCentralization().name()+",\n-Prawowitość: "+this.getLegitimacy()+",\n-Poziom wpływu: "+influence+"\nKrólestwa:";

            for (Kingdom kingdom : kingdoms)
            {
                info += "\n\t" + kingdom.ToString();
            }

        return info;
    }
}
