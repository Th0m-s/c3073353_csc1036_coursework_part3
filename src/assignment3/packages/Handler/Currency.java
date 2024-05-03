package assignment3.packages.Handler;

    public enum Currency {
        STERLING("Sterling"),
        USDOLLAR("US Dollar"),
        EURO("Euro"),
        CHINESEYUAN("Chinese Yuan"),
        JAPANSESYEN("Japanese Yen");


        private final String displayName;

    Currency(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
