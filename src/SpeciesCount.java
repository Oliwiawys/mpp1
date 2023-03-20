public class SpeciesCount {
    private int count;
    private final String speciesName;

    public SpeciesCount(String speciesName) {
        this.count = 0;
        this.speciesName = speciesName;
    }

    public int getCount() {
        return count;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void addCount() {
        setCount(getCount() + 1);
    }
}
