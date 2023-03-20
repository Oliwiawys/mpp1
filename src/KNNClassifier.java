import java.util.*;

public class KNNClassifier {
    private final String species1 = "Iris-setosa";
    private final String species2 = "Iris-versicolor";
    private final String species3 = "Iris-virginica";
    private List<Data> trainingData, testData;
    private int k;

    public KNNClassifier(List<Data> trainingData, List<Data> testData, int k) {
        if (trainingData.size() == 0 || testData.size() == 0) {
            System.err.println("Pliki puste");
            return;
        }
        this.trainingData = trainingData;
        this.testData = testData;
        this.k = k;
    }

    public Double calculateDistance(Data a, Data b) {
        double distance;
        distance = Math.pow((a.getData1() - b.getData1()), 2) +
                Math.pow((a.getData2() - b.getData2()), 2) +
                Math.pow((a.getData3() - b.getData3()), 2) +
                Math.pow((a.getData4() - b.getData4()), 2);
        distance = Math.sqrt(distance);
        return distance;
    }

    public List<String> classifySpecies() {
        List<String> classifiedSpecies = new ArrayList<>();
        for (int i = 0; i < testData.size(); i++) {
            ArrayList<Distance> distances = new ArrayList<>();
            Data test = testData.get(i);
            for (Data training : trainingData) {
                double distance = calculateDistance(test, training);
                String speciesName = training.getSpeciesName();
                distances.add(new Distance(distance, speciesName));
            }
            distances.sort(new distanceComparator());
            String classifiedSpeciesName = calculateCount(distances, k);
            classifiedSpecies.add(classifiedSpeciesName);
        }
        return classifiedSpecies;
    }

    public String calculateCount(List<Distance> distances, int k) {
        String classifiedSpeciesName;
        List<SpeciesCount> speciesCount = new ArrayList<>();
        speciesCount.add(new SpeciesCount("Iris-setosa"));
        speciesCount.add(new SpeciesCount("Iris-versicolor"));
        speciesCount.add(new SpeciesCount("Iris-virginica"));
        for (int i = 0; i < k; i++) {
            switch (distances.get(i).getSpeciesName()) {
                case species1 -> speciesCount.get(0).addCount();
                case species2 -> speciesCount.get(1).addCount();
                case species3 -> speciesCount.get(2).addCount();
            }
        }
        speciesCount.sort(new classCountComparator());
        if (Objects.equals(speciesCount.get(0), speciesCount.get(1)))
            classifiedSpeciesName = calculateCount(distances, k + 1);
        else
            classifiedSpeciesName = speciesCount.get(0).getSpeciesName();
        return classifiedSpeciesName;
    }

    static class distanceComparator implements Comparator<Distance> {
        public int compare(Distance a, Distance b) {
            return Double.compare(a.getDistance(), b.getDistance());
        }
    }

    static class classCountComparator implements Comparator<SpeciesCount> {
        public int compare(SpeciesCount a, SpeciesCount b) {
            return Integer.compare(b.getCount(), a.getCount());
        }
    }
}
