package kingdoms.kingdoms;

import java.util.ArrayList;
import java.util.List;

/**
<p>Implements an ruler - person who has at least one claim (for province) and can rule a duchy/kingdom/empire</p>
<p>Ruler has its prestinge used for calculating its strength (army), stability of ruled duchy/kingdom/empire and influence of ruled empire</p>
<p>Strength is a size of a ruler's personal army (number of soldiers)</p>*/

public class Ruler extends Person {
    
    private int prestige;
    private List<Province> claims;
    private int strength;

    /**
    Constructs a ruler with given name, age, prestige nad initial claim
    @param name Name of the Ruler
    @param age Age of the ruler
    @param prestige Inital presige of the ruler
    @param initClaim Initial ruler's claim
    */
    public Ruler(String name, short age, int prestige, Province initClaim)
    {
        super(name, age);
        this.prestige = prestige;
        claims = new ArrayList<Province>();
        claims.add(initClaim);
        updateStrength();
    }

    /**
    @return Ruler's prestige
     */
    public int getPrestige() {
        return prestige;
    }

    /**
    Sets ruler's prestige
    @param prestige New rule'rs prestige 
    */
    public void setPrestige(int prestige) {
        this.prestige = prestige;
    }

    /**
    @return Ruler's prestige
     */
    public List<Province> getClaims() {
        return claims;
    }

    /**
    Adds a new claim
    @param claim A new claim to add 
    */
    public void addClaim(Province claim)
    {
        claims.add(claim);
    }

    /**
    Updates ruler's strength based on ruler's prestige
     */
    public void updateStrength()
    {
        strength = 5*prestige;
    }

    /**
    Updates ruler's strength based on ruler's prestige and ruler's domesne (number of provinces in a duchy/kingdom/empire ruled by the ruler)
    @param domesne A number of provinces ruled by the ruler
     */
    public void updateStrength(int domesne)
    {
        strength = prestige*domesne;
    }

    /**
    @return Strength of the ruler
     */
    public int getStrength()
    {
        return strength;
    }

    /**
    Prints out all parameters of the ruler
    @return Stylized string with all the ruler's parameters
     */
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
