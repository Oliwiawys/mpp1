import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public Main(String training, String test) {
        List<Data> trainingData = readFile(training);
        List<Data> testData = readFile(test);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj parametr k: ");
        int k = scanner.nextInt();
        KNNClassifier knnClassifier = new KNNClassifier(trainingData, testData, k);
        List<String> classifiedSpeciesNames = knnClassifier.classifySpecies();
        int count = 0;
        for (int i = 0; i < testData.size(); i++) {
            if (testData.get(i).getSpeciesName().equals(classifiedSpeciesNames.get(i))) count++;
        }
        double accuracy = (double) count / testData.size() * 100;
        System.out.println("Prawidłowowo zaklasyfikowane przykłady: " + count + "/" + testData.size() + ", Dokładność: " + accuracy + "%");
        System.out.println("Wpisz 1 aby zakończyć lub dowolny znak aby kontynuować: ");
        int odp = scanner.nextInt();
        if (odp != 1)
            new Main(training, test);
        scanner.close();
    }

    public static List<Data> readFile(String filename) {
        BufferedReader inputReader;
        List<Data> dataList = new ArrayList<>();

        try {
            inputReader = new BufferedReader(new FileReader(filename));

            String line;
            while ((line = inputReader.readLine()) != null) {
                line = line.replace(",", ".");
                String[] splitted = line.split(" {2} *");
                String data1 = splitted[0].trim();
                String data2 = splitted[1].trim();
                String data3 = splitted[2].trim();
                String data4 = splitted[3].trim();
                String className = splitted[4].trim();

                dataList.add(new Data(Double.parseDouble(data1), Double.parseDouble(data2), Double.parseDouble(data3), Double.parseDouble(data4), className));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return dataList;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Muszą być 2 pliki");
            return;
        }
        String training = args[0], test = args[1];
        new Main(training, test);
    }
}