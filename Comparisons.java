import java.io.*;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Comparisons {
    public static void main(String[] args) throws IOException {
        int[] NValues = {100,250,500};
        List<Double> greedyMakespansN = new List<>();
        List<Double> greedyDecreasingMakespansN = new List<>();



        for (int N : NValues) {
            int M = (int) Math.sqrt(N);
            System.out.println("Processing N=" + N + ", M=" + M);
            ;
            double sum2 = 0.0;
            double sum1 = 0.0;

            for (int i = 1; i <= 10; i++) {
                //dimiourgia txt me tuxaies diergasies
                String inputFile = generateRandomTxt(N, i);

                //anagnosi tou random txt
                Reader reader = new Reader(inputFile);
                Reader.Data data = reader.readInput();
                List<Job> jobs = data.getJobs();
                List<Job> jobs2 = new List<>();

                //copy tin lista gia na taksinomithei argotera
                for (int k = 0; k < jobs.size(); k++) {
                    jobs2.insertAtBack(jobs.get(k));
                }

                //taksinomisi tis listas
                Sort.heapsort(jobs2);

                System.out.println("Executing Greedy algorithm.");
                int makespan1 = Greedy.greedyImpl(jobs, M);

                System.out.println("Executing Greedy Decreasing algorithm.");
                int makespan2 = Greedy.greedyImpl(jobs2, M);

                sum1 +=makespan1;
                sum2 +=makespan2;

                System.out.printf("File: %s, Makespan1: %d, Makespan2: %d\n", inputFile, makespan1, makespan2);
            }

            //upologismos mesou orou tou kathe makespan gia kathe N ksexorista
            double avMakespan1 = sum1/10;
            double avMakespan2 = sum2/10;

            System.out.println("Average Makespan for Greedy for N = " + N +" is: " + avMakespan1);
            System.out.println("Average Makespan for Greedy Decreasing for N = " + N +" is: " + avMakespan2);

            if (avMakespan2 > avMakespan1){
                System.out.println("Algorithm 1(Greedy) is more efficient for N = " + N);
            } else {
                System.out.println("Algorithm 2(Greedy Decreasing) is more efficient for N = " + N);
            }

            greedyMakespansN.insertAtBack(avMakespan1);
            greedyDecreasingMakespansN.insertAtBack(avMakespan2);

        }
        double sum1 = 0.0;
        double sum2 = 0.0;

        for (int i = 0; i < NValues.length; i++ ){
            sum1+= greedyMakespansN.get(i);
            sum2+= greedyDecreasingMakespansN.get(i);
        }

        //upologismos mesou orou tou kathe makespan gia ola ta N sunolika
        double overallAvGreedy = sum1/greedyMakespansN.size();
        double overallAvGreedyDecreasing =  sum2/greedyDecreasingMakespansN.size();

        System.out.println("Average Makespan for Greedy is = "  + overallAvGreedy);
        System.out.println("Average Makespan for Greedy Decreasing is = "  + overallAvGreedyDecreasing);

        if (overallAvGreedy > overallAvGreedyDecreasing){
            System.out.println("Algorithm 2(Greedy Decreasing) is more efficient");
        } else {
            System.out.println("Algorithm 1(Greedy) is more efficient");
        }
    }

    private static String generateRandomTxt(int N, int x) throws IOException {
        Random random = new Random();
        String filePath = "input_N" + N + "_file" + x + ".txt"; //kanei generate to onoma tou arxeiou txt me basi to N kai to i
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(String.valueOf((int) Math.floor(Math.sqrt(N)))); //grafei sto txt to M, diladi ton arithmo epeksergaston me basi to N kathe fora
            writer.newLine();
            writer.write(String.valueOf(N));    //grafei to N,ton arithmo ton diergasion
            writer.newLine();
            for (int i = 1; i <= N; i++) {
                int randomValue = random.nextInt(1000) + 1; //orio to 1000
                writer.write(i + " " + randomValue); //na pairnei os id to i gia na min tuxei na nai idia, kai random timi gia diergasia deksia tou
                writer.newLine();
            }
        }
        return filePath;
    }
}




