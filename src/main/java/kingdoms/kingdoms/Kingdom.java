package kingdoms.kingdoms;

import java.util.ArrayList;
import java.util.List;

public class Kingdom extends Duchy {

    private int legitimacy;

    public Kingdom(String name, Ruler ruler, List<Province> provinces, Centralization centralization, int legitimacy)
    {
        super(name, ruler, provinces, centralization);
        //this.setArea(this.getArea());
        this.legitimacy = legitimacy;
        updateStability();
    }

    public int getLegitimacy() {
        return legitimacy;
    }

    public void setLegitimacy(int legitimacy) {
        this.legitimacy = legitimacy;
    }

    public void setRuler(Ruler ruler)
    {
        this.setRuler(ruler);
        updateStability();
    }

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

    public String ToString()
    {
        String info = "(ID: "+this.getId()+") \""+this.getName()+"\", Powierzchnia: "+this.getArea()+",\n-Władca: "+getRuler().ToString()+"\n---,\n-Stabilność: "+this.getStability().name+",\n-Centralizacja: "+this.getCentralization().name()+",\n-Prawowitość: "+legitimacy+",\n---Prowincje/Księstwa:";

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
