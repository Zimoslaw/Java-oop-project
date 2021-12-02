package kingdoms.kingdoms;

import java.util.ArrayList;
import java.util.List;

public class Ruler extends Person {
    
    private int prestige;
    private List<Province> claims;

    public Ruler(String name, short age, int prestige, Province initClaim)
    {
        super(name, age);
        this.prestige = prestige;
        claims = new ArrayList<Province>();
        claims.add(initClaim);
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

    public String ToString() {
        String info = "(ID: "+super.getId()+"), \""+super.getName()+"\", Wiek: "+super.getAge()+", Roszczenia:";

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
