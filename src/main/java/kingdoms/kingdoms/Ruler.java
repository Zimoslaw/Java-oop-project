package kingdoms.kingdoms;

import java.util.ArrayList;
import java.util.List;

public class Ruler extends Person {
    
    private int prestige;
    private List<Province> claims;
    private int strength;

    public Ruler(String name, short age, int prestige, Province initClaim)
    {
        super(name, age);
        this.prestige = prestige;
        claims = new ArrayList<Province>();
        claims.add(initClaim);
        updateStrength();
    }

    public void Age()
    {
        short age = this.getAge();
        this.setAge(age++);
    }

    public int getPrestige() {
        return prestige;
    }

    public void setPrestige(int prestige) {
        this.prestige = prestige;
    }

    public List<Province> getClaims() {
        return claims;
    }

    public void addClaim(Province claim)
    {
        claims.add(claim);
    }

    public void updateStrength()
    {
        strength = 5*prestige;
    }

    public void updateStrength(int domesne)
    {
        strength = prestige*domesne;
    }

    public int getStrength()
    {
        return strength;
    }

    public String ToString() {
        String info = "(ID: "+super.getId()+"), \""+super.getName()+"\", Wiek: "+super.getAge()+", Siła armii: "+strength+" bojów, Roszczenia:";

        if(!claims.isEmpty())
        {
            for (Province claim : claims)
            {
                info += "\n\t" + claim.ToString();
            }
        }

        return info;
    }
}
