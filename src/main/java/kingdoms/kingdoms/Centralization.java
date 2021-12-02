package kingdoms.kingdoms;

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