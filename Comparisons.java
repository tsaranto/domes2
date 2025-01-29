import java.io.*;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Comparisons {
    public static void main(String[] args) throws IOException {
        int[] NValues = {100, 250, 500};
        List<Double> greedyMakespansN = new List<>();
        List<Double> greedyDeacreasingMakespansN = new List<>();



        for (int N : NValues) {
            int M = (int) Math.sqrt(N);
            System.out.println("Processing N=" + N + ", M=" + M);
            ;
            double sum2 = 0.0;
            double sum1 = 0.0;

            for (int i = 1; i <= 10; i++) {
                String inputFile = generateRandomTxt(N, i);

                Reader reader = new Reader(inputFile);
                Reader.Data data = reader.readInput();
                List<Job> jobs = data.getJobs();
                List<Job> jobs2 = new List<>();

                for (int k = 0; k < jobs.size(); k++) {
                    jobs2.insertAtBack(jobs.get(k));
                }


                System.out.println("Before sorting:");
                for (int k = 0; k < jobs2.size(); k++) {
                    System.out.print(jobs2.get(k).getTime() + " ");
                }
                System.out.println();

                Sort.heapsort(jobs2);

                System.out.println("After sorting:");
                for (int k = 0; k < jobs2.size(); k++) {
                    System.out.print(jobs2.get(k).getTime() + " ");
                }
                System.out.println();


                

                System.out.println("Executing Greedy algorithm.");
                int makespan1 = Greedy.greedyImpl(jobs, M);

                System.out.println("Executing Greedy Deacreasing algorithm.");
                int makespan2 = Greedy.greedyImpl(jobs2, M);

                sum1 +=makespan1;
                sum2 +=makespan2;

                System.out.printf("File: %s, Makespan1: %d, Makespan2: %d\n", inputFile, makespan1, makespan2);
            }

            double avMakespan1 = sum1/10;
            double avMakespan2 = sum2/10;

            System.out.println("Average Makespan for Greedy for N = " + N +" is: " + avMakespan1);
            System.out.println("Average Makespan for Greedy Deacreasing for N = " + N +" is: " + avMakespan2);

            if (avMakespan2 > avMakespan1){
                System.out.println("Algorithm 1(Greedy) seems more efficient for N = " + N);
            } else {
                System.out.println("Algorithm 2(Greedy Deacreasing) seems more efficient for N = " + N);
            }

            greedyMakespansN.insertAtBack(avMakespan1);
            greedyDeacreasingMakespansN.insertAtBack(avMakespan2);

        }
        double sum1 = 0.0;
        double sum2 = 0.0;

        for (int i = 0; i < NValues.length; i++ ){
            sum1+= greedyMakespansN.get(i);
            sum2+= greedyDeacreasingMakespansN.get(i);
        }

        double overallAvGreedy = sum1/greedyMakespansN.size();
        double overallAvGreedyDeacreasing =  sum2/greedyDeacreasingMakespansN.size();

        System.out.println("Average Makespan for Greedy is = "  + overallAvGreedy);
        System.out.println("Average Makespan for Greedy Deacreasing is = "  + overallAvGreedyDeacreasing);

        if (overallAvGreedy > overallAvGreedyDeacreasing){
            System.out.println("Algorithm 2(Greedy Deacreasing) seems more efficient");
        } else {
            System.out.println("Algorithm 1(Greedy) seems more efficient");
        }
    }

    private static String generateRandomTxt(int N, int x) throws IOException {
        Random random = new Random();
        String filePath = "input_N" + N + "_file" + x + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(String.valueOf((int) Math.floor(Math.sqrt(N))));
            writer.newLine();
            writer.write(String.valueOf(N));
            writer.newLine();
            for (int i = 1; i <= N; i++) {
                int randomValue = random.nextInt(1000) + 1;
                writer.write(i + " " + randomValue); //na pairnei os id to i gia na min tuxei na nai idia, kai random timi dipla
                writer.newLine();
            }
        }
        return filePath;
    }
}




