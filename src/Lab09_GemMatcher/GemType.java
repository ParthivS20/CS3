package Lab09_GemMatcher;

enum GemType {
    GREEN(1),
    BLUE(0),
    ORANGE(0),
    BOMB(1, true);

    int chance;
    boolean isSpecial;
    GemType(int chance) {
        this.chance = chance;
    }
    GemType(int chance, boolean isSpecial) {
        this(chance);
        this.isSpecial = isSpecial;
    }
}

