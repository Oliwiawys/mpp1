public class Data {
    private final double data1;
    private final double data2;
    private final double data3;
    private final double data4;
    private final String speciesName;

    public Data(double data1, double data2, double data3, double data4, String speciesName) {
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
        this.speciesName = speciesName;
    }

    public double getData1() {
        return data1;
    }

    public double getData2() {
        return data2;
    }

    public double getData3() {
        return data3;
    }

    public double getData4() {
        return data4;
    }

    public String getSpeciesName() {
        return speciesName;
    }
}
