package Lab09_GemMatcher;

enum GemType {
    GREEN(5),
    BLUE(5),
    ORANGE(5),
    BOMB(1, true);

    int chance;
    boolean isSpecial;
    private GemType(int chance) {
        this.chance = chance;
    }
    private GemType(int chance, boolean isSpecial) {
        this(chance);
        this.isSpecial = isSpecial;
    }
}

