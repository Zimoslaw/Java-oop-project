package kingdoms.kingdoms;

import java.util.ArrayList;
import java.util.List;

/**
<p>Implements a kingdom - higher level of duchy @see Duchy. It has all the parameters of a duchy and additionaly a legitimacy of the kingdom</p>
 */

public class Kingdom extends Duchy {

    private int legitimacy;

    /**
    Constructs kingdom with name, ruler, provinces, centralization and legitimacy
    @param name Name of the kingdom
    @param ruler Ruler of the kingdom
    @param provinces List of provinces held by the kingdom. Also accepts duchies
    @param centralization Centralization of the kingdom (enum)
    @param legitimacy Level of kingdom's legitimacy
     */
    public Kingdom(String name, Ruler ruler, List<Province> provinces, Centralization centralization, int legitimacy)
    {
        super(name, ruler, provinces, centralization);
        //this.setArea(this.getArea());
        this.legitimacy = legitimacy;
        updateStability();
        this.getRuler().updateStrength(this.getArea()*(5-centralization.level)+legitimacy*10);
    }

    /**
    @return Legitimacy of the kingdom */
    public int getLegitimacy() {
        return legitimacy;
    }

    /**
    Sets new kingdom's legitimacy level 
    @param legitimacy New legitimacy level
    */
    public void setLegitimacy(int legitimacy) {
        this.legitimacy = legitimacy;
    }

    /**
    Sets new ruler. Overrides setRuler() from Duchy because of different stability calculations
    @param ruler New kingdom's ruler
     */
    public void setRuler(Ruler ruler)
    {
        super.setRuler(ruler);
        getRuler().updateStrength(this.getArea()*(5-getCentralization().level)+legitimacy*10);
        updateStability();
    }

    /**
    Update stability based on number of provinces, centralizaion, legitimacy, ruler's prestige and age. Overrides updateStability() from Duchy
    */
    public void updateStability()
    {
        int domesne = this.getProvinces().size() > 25 ? 25 : this.getProvinces().size();
        int prestige = this.getRuler().getPrestige() > 100 ? 100 : this.getRuler().getPrestige();
        short age = this.getRuler().getAge() > 100 ? 100 : this.getRuler().getAge();
        int centralization = this.getCentralization().level;
        int legitimacy = this.legitimacy > 100 ?  100 : this.legitimacy;
        int stability = Math.round((((prestige+legitimacy) * (age))/100 - domesne*centralization)/25);

        stability = stability < 0 ? 0 : stability;

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

    /**
    Prints out all parameters of the kingdom
    @return Stylized string with all the kingdom's parameters
     */
    public String ToString()
    {
        String info = "(ID: "+this.getId()+") \""+this.getName()+"\", Powierzchnia: "+this.getArea()+",\n--Władca: "+getRuler().ToString()+"\n--,\n-Stabilność: "+this.getStability().name+",\n-Centralizacja: "+this.getCentralization().name()+",\n-Prawowitość: "+legitimacy+",\n---Prowincje/Księstwa:";

        if(!this.getProvinces().isEmpty())
        {
            for (Province province : this.getProvinces())
            {
                info += "\n\t" + province.ToString();
            }
        }

        info+="\n---";

        return info;
    }
}
