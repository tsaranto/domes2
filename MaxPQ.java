public class MaxPQ implements PQInterface {
    private Processor[] heap;
    private int size;

    private static final int DEFAULT_CAPACITY = 4;

    public MaxPQ() {
        this.heap = new Processor[DEFAULT_CAPACITY + 1];
        this.size = 0;
    }


    public void insert(Processor processor) {
        if (size >= heap.length * 3 / 4) {
            resize();
        }
        heap[++size] = processor;
        swim(size);
    }


    public Processor max() {
        if (size == 0) {
            return null;
        }
        return heap[1];
    }

    public Processor getmax() {
        if (size == 0) {
            return null;
        }

        Processor root = heap[1];
        heap[1] = heap[size--];
        sink(1);
        return root;
    }

    private void swim(int i) {
        while (i > 1 && heap[i].compareTo(heap[i / 2]) > 0) {
            swap(i, i / 2);
            i = i / 2;
        }
    }

    private void sink(int i) {
        while (2 * i <= size) {
            int j = 2 * i;
            if (j < size && heap[j].compareTo(heap[j + 1]) < 0) {
                j++;
            }
            if (heap[i].compareTo(heap[j]) >= 0) {
                break;
            }
            swap(i, j);
            i = j;
        }
    }

    private void swap(int i, int j) {
        Processor temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

 
    private void resize() {
        Processor[] newHeap = new Processor[heap.length * 2];
        System.arraycopy(heap, 1, newHeap, 1, size);
        heap = newHeap;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
