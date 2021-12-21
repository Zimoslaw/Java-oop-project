package kingdoms.kingdoms;

/**
Implements state's (duchy, kingdom or empire) centralization - how much power belongs to state's ruler */

enum Centralization {
    MocnaDecentralizacja(4),
    Niska(3),
    Zrównoważona(2),
    Wysoka(1),
    Absolutna(0);

    int level;

    Centralization(int lvl)
    {
        level = lvl;
    }
}