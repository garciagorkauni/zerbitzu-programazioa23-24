public class SumaMillon extends Thread {
    private long start;
    private long end;
    private long suma;

    public SumaMillon(long start, long end) {
        this.start = start;
        this.end = end;
        this.suma = 0;
    }

    @Override
    public void run() {
        for (long i = start; i <= end; i++) {
            suma += i;
        }
    }

    public long getSuma() {
        return suma;
    }

    public static void main(String[] args) throws InterruptedException {
        int numThreads = 4;
        long range = 1000000;
        long blockSize = range / numThreads;

        SumaMillon[] threads = new SumaMillon[numThreads];

        for (int i = 0; i < numThreads; i++) {
            long start = i * blockSize + 1;
            long end = (i + 1) * blockSize;
            threads[i] = new SumaMillon(start, end);
            threads[i].start();
        }

        long totalSuma = 0;

        for (SumaMillon thread : threads) {
            thread.join();
            totalSuma += thread.getSuma();
        }

        System.out.println("Suma total: " + totalSuma);
    }
}