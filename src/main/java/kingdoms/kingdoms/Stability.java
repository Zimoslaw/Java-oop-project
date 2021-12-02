package kingdoms.kingdoms;

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