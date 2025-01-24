public class Job {
    private final int id; 
    private final int time; 
    public Job(int id, int time) {
        this.id = id;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public int getTime() {
        return time;
    }
}
