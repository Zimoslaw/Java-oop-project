package kingdoms.kingdoms;

import java.util.ArrayList;
import java.util.List;

public class Empire extends Kingdom
{
    private int influence;
    private List<Province> kingdoms = new ArrayList<Province>();

    public Empire(String name, Ruler ruler, List<Province> kingdoms, Centralization centralization)
    {
        super(name, ruler, new ArrayList<Province>(), centralization, 0);
        this.kingdoms = kingdoms;
        updateProvinces();
        updateLegitimacy();
        updateStability();
        updateInfluence();
        this.getRuler().updateStrength(getArea());
    }

    public int getInfluence() {
        return influence;
    }

    public int getArea() {
        if(kingdoms==null || kingdoms.isEmpty())
            return 0;

        int area = 0;

        for(Province k : kingdoms)
        {
            area += k.getArea();
        }

        return area;
    }

    public List<Province> getKingdoms()
    {
        return kingdoms;
    }

    public void addKingdom(Province kingdom)
    {
        kingdoms.add(kingdom);
    }

    private void updateProvinces() {
        for(Province k : kingdoms)
        {
            for(Province p : k.getProvinces())
                this.addProvince(p);
        }
    }

    public void updateInfluence() {
        int stability = StabilityToInt(this.getStability());

        influence = getArea() * this.getCentralization().level * stability + this.getLegitimacy() + this.getRuler().getPrestige();
    }

    public void updateLegitimacy()
    {
        int l = 0;
        for(Province kingdom : kingdoms)
            l += kingdom.getLegitimacy();
        this.setLegitimacy(l);
    }

    public void updateStability()
    {
        if(kingdoms == null)
        {
            this.setStability(Stability.Prosperity);
        }
        else
        {
            int stability = 0;
            for(Province k : kingdoms)
                stability += StabilityToInt(k.getStability());
            stability/=kingdoms.size();

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

    public String ToString()
    {
        String info = "(ID: "+this.getId()+") \""+this.getName()+"\",\n-Władca: "+getRuler().ToString()+"\n---\nPowierzchnia: "+getArea()+",\n-Stabilność: "+this.getStability().name+",\n-Centralizacja: "+this.getCentralization().name()+",\n-Prawowitość: "+this.getLegitimacy()+",\n-Poziom wpływu: "+influence+"\nKrólestwa:";

            for (Province kingdom : kingdoms)
            {
                info += "\n\t" + kingdom.ToString();
            }

        return info;
    }
}
