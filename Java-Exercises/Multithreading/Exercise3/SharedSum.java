class SharedSum {
    private long totalSum = 0;

    public void add(long partialSum) {
        totalSum += partialSum;
    }

    public long getTotalSum() {
        return totalSum;
    }
}