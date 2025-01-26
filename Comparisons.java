import java.io.*;
import java.util.*;

public class Comparisons {
    public static void main(String[] args) throws IOException {
        int[] NValues = {100, 250, 500};

        for (int N : NValues) {
            int M = (int) Math.sqrt(N);
            System.out.println("Processing N=" + N + ", M=" + M);

            for (int i = 1; i <= 10; i++) {
                String inputFile = "input_N" + N + "_file" + i + ".txt";
                generateRandomInput(N, inputFile);

                List<Job> jobs = readInput(inputFile);
                int makespan1 = algorithm1(new List<>(jobs), M);
                int makespan2 = algorithm2(new List<>(jobs), M);

                System.out.printf("File: %s, Makespan1: %d, Makespan2: %d\n", inputFile, makespan1, makespan2);
            }
        }
    }

    private static void generateRandomInput(int N, String filename) throws IOException {
        Random random = new Random();
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            writer.println(N);
            writer.println((int) Math.sqrt(N));
            for (int i = 0; i < N; i++) {
                writer.println(random.nextInt(100) + 1);
            }
        }
    }

    private static List<Job> readInput(String filename) throws IOException {
        List<Job> jobs = new List<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            int numJobs = scanner.nextInt();
            int numProcessors = scanner.nextInt();
            while (scanner.hasNextInt()) {
                jobs.insertAtBack(new Job(scanner.nextInt()));
            }
        }
        return jobs;
    }

    private static int algorithm1(List<Job> jobs, int numProcessors) {
        MaxPQ maxPQ = new MaxPQ();

        // Initial assignment of jobs to processors
        for (int i = 0; i < numProcessors; i++) {
            Processor processor = new Processor(i + 1);
            try {
                processor.addJob(jobs.removeFromFront());
            } catch (EmptyListException e) {
                break;
            }
            maxPQ.insert(processor);
        }

        // Assign remaining jobs to the processor with the minimum total processing time
        while (!jobs.isEmpty()) {
            Job job;
            try {
                job = jobs.removeFromFront();
            } catch (EmptyListException e) {
                break;
            }
            Processor processor = maxPQ.getmax();
            processor.addJob(job);
            maxPQ.insert(processor);
        }

        // Calculate makespan
        int makespan = 0;
        while (!maxPQ.isEmpty()) {
            Processor processor = maxPQ.getmax();
            makespan = Math.max(makespan, processor.getTotalProcessingTime());
        }
        return makespan;
    }

    private static int algorithm2(List<Job> jobs, int numProcessors) {
        // Sort jobs in descending order using Heapsort
        Sort.heapsort(jobs);

        // Apply the Greedy algorithm
        return algorithm1(jobs, numProcessors);
    }
}
