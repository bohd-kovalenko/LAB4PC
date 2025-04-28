package org.example.data;

public class ComputationalData {
    private int matrixSize;
    private int maxCellNumber;
    private int threadCount;

    public ComputationalData() {
    }

    public ComputationalData(int matrixSize, int maxCellNumber, int threadCount) {
        this.matrixSize = matrixSize;
        this.maxCellNumber = maxCellNumber;
        this.threadCount = threadCount;
    }

    public int getMatrixSize() {
        return matrixSize;
    }

    public void setMatrixSize(int matrixSize) {
        this.matrixSize = matrixSize;
    }

    public int getMaxCellNumber() {
        return maxCellNumber;
    }

    public void setMaxCellNumber(int maxCellNumber) {
        this.maxCellNumber = maxCellNumber;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }
}