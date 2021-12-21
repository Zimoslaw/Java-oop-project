package kingdoms.kingdoms;

/**
Implements levels of state's (duchy, kingdom or empire) stability, which defines contentment among people*/

enum Stability {
    Anarchy("Anarchia"),
    Unstable("Niestabilnie"),
    Balance("Równowaga"),
    Stable("Stabilnie"),
    Prosperity("Dobrobyt");

    String name;

    Stability(String n)
    {
        name = n;
    }
}