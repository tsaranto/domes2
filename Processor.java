public class Processor implements Comparable<Processor> {
    private int id; 
    private List<Job> processedJobs; 
    private int totalProcessingTime; 

    public Processor(int id) {
        this.id = id;
        this.processedJobs = new List<>(); 
        this.totalProcessingTime = 0; 
    }

    public void addJob(Job job) {
        processedJobs.insertAtBack(job);
        totalProcessingTime += job.getTime(); 
    }

    public int getTotalProcessingTime() {
        return totalProcessingTime;
    }

    public int getId() {
        return id;
    }

    public List<Job> getProcessedJobs() {
        return processedJobs;
    }

    //sygkrish twn epejergastwn me vasi ton sunoliko xrono
    @Override
    public int compareTo(Processor other) {
        if (this.totalProcessingTime != other.totalProcessingTime) {
            return Integer.compare(other.totalProcessingTime, this.totalProcessingTime); 
        }
        return Integer.compare(this.id, other.id);
    }
}
