import java.io.*;
import java.util.*;

public class Reader {
    private String filePath;

    public Reader(String filePath) {
        this.filePath = filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    //diavazei to arxeio kai epistrefei to plithos twn epejergastwn kai tis diergasies
    public Data readInput() throws IOException {
        try (Scanner sc = new Scanner(new File(filePath))) {
            int numProcessors = sc.nextInt();
            int numJobs = sc.nextInt();

            List<Job> jobs = new List<>();

            while (sc.hasNext()) {
                int jobId = sc.nextInt();
                int processingTime = sc.nextInt();
                jobs.insertAtBack(new Job(jobId, processingTime));
            }

            if (jobs.size() != numJobs) {
                throw new IOException("File format error.");
            }

            return new Data(numProcessors, jobs);
        } catch (Exception e) {
            throw new IOException("Error reading file:" + e.getMessage());
        }
    }

    //klasi pou periexei to plithos twn epejergastwn kai tis diergasies
    public static class Data {
        private int numProcessors;
        private List<Job> jobs;

        public Data(int numProcessors, List<Job> jobs) {
            this.numProcessors = numProcessors;
            this.jobs = jobs;
        }

        public int getNumProcessors() {
            return numProcessors;
        }

        public List<Job> getJobs() {
            return jobs;
        }
    }
}