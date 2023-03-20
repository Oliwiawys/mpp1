public class Distance {
    private final double distance;
    private final String speciesName;

    public Distance(double distance, String className) {
        this.distance = distance;
        this.speciesName = className;
    }

    public double getDistance() {
        return distance;
    }

    public String getSpeciesName() {
        return speciesName;
    }
}
