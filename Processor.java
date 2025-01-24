import java.util.ArrayList;
import java.util.List;

public class Processor implements Comparable<Processor> {
    private int id; 
    private List<Job> processedJobs; 
    private int totalProcessingTime; 

    // Constructor
    public Processor(int id) {
        this.id = id;
        this.processedJobs = new ArrayList<>(); 
        this.totalProcessingTime = 0; 
    }


    public void addJob(Job job) {
        processedJobs.add(job);
        totalProcessingTime += job.getTime(); 
    }

    public int getTotalProcessingTime() {
        return totalProcessingTime;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Processor other) {
        // Σύγκριση με βάση τον συνολικό χρόνο
        if (this.totalProcessingTime != other.totalProcessingTime) {
            return Integer.compare(this.totalProcessingTime, other.totalProcessingTime);
        }
        // Σε περίπτωση ισοβαθμίας, προτιμάται ο επεξεργαστής με το μικρότερο ID
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Processor " + id + " [Total Time: " + totalProcessingTime + "]";
    }
}
