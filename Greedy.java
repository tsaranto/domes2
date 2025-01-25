import java.io.IOException;

public class Greedy {
    public static void main(String[] args) throws IOException {
        Reader reader =  new Reader("input.txt");

        Reader.Data data = reader.readInput();
        List<Job> jobs = data.getJobs();
        int numProcessors = data.getNumProcessors();

        MaxPQ maxPQ = new MaxPQ();
//arxiko assignment twn diergasiwn sta processors
        for (int i = 0; i < numProcessors; i++) {
            Processor processor = new Processor(i);
            try {
                processor.addJob(jobs.removeFromFront());
            } catch (EmptyListException e) {
                System.out.println("No more jobs to assign: " + e.getMessage());
                break;
            }
            maxPQ.insert(processor);
        }
//assignment ton diergasion sto processsor me ton mikrotero xrono
        while (!jobs.isEmpty()) {
            Job job;
            try {
                job = jobs.removeFromFront();
            } catch (EmptyListException e) {
                System.out.println("No more jobs to assign: " + e.getMessage());
                break;
            }
            Processor processor = maxPQ.getmax();
            processor.addJob(job);
            maxPQ.insert(processor);
        }
//ektiposi twn diergasion kai tou sunolikou xronou      
        int makespan = 0;
        Processor[] processors = new Processor[numProcessors];
        for (int i = 0; i < numProcessors; i++) {
            processors[i] = maxPQ.getmax();
            if (processors[i] != null) {
                makespan = Math.max(makespan, processors[i].getTotalProcessingTime());
            }
        }

        if (jobs.size() < 50) {
            for (Processor processor : processors) {
                if (processor != null) {
                    System.out.print("id " + processor.getId() + ", load=" + processor.getTotalProcessingTime() + ": ");
                    List<Job> processedJobs = processor.getProcessedJobs();
                    for (int i = 0; i < processedJobs.size(); i++) {
                        System.out.print(processedJobs.get(i).getTime() + " ");
                    }
                    System.out.println();
                }
            }
        }


        System.out.println("Makespan = " + makespan);
    }

    }
