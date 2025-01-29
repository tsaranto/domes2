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
        int largest = i; //arxikopoihsh
        int left = 2 * i + 1; //aristero paidi
        int right = 2 * i + 2; //deksi paidi
        //an to aristero paidi einai megalutero apo to largest
        if (left < n && jobs.get(left).getTime() > jobs.get(largest).getTime()) {
            largest = left;
        }
        //an to deksi paidi einai megalutero apo to largest
        if (right < n && jobs.get(right).getTime() > jobs.get(largest).getTime()) {
            largest = right;
        }
        //an to largest den einai to i
        if (largest != i) {
            swap(jobs, i, largest);
            heapify(jobs, n, largest);
        }
    }

    private static void swap(List<Job> jobs, int i, int j) {
        Job temp = jobs.get(i);
        jobs.set(i, jobs.get(j));
        jobs.set(j, temp);
    }
}