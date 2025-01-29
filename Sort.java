public class Sort {
    public static void heapsort(List<Job> jobs) {
        int n = jobs.size();

        // ftiakse to heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(jobs, n, i);
        }

        // ektelese to heap sort
        for (int i = n - 1; i > 0; i--) {
          
            swap(jobs, 0, i);
            heapify(jobs, i, 0);
        }
    }

    private static void heapify(List<Job> jobs, int n, int i) {
        int smallest = i; //arxikopoihsh
        int left = 2 * i + 1; //aristero paidi
        int right = 2 * i + 2; //deksi paidi
        //an to aristero paidi einai mikrotero apo to smallest
        if (left < n && jobs.get(left).getTime() < jobs.get(smallest).getTime()) {
            smallest = left;
        }
        //an to deksi paidi einai mikrotero apo to smallest
        if (right < n && jobs.get(right).getTime() < jobs.get(smallest).getTime()) {
            smallest = right;
        }
        //an to smallest den einai to i
        if (smallest != i) {
            swap(jobs, i, smallest);
            heapify(jobs, n, smallest);
        }
    }

    private static void swap(List<Job> jobs, int i, int j) {
        Job temp = jobs.get(i);
        jobs.set(i, jobs.get(j));
        jobs.set(j, temp);
    }
}
