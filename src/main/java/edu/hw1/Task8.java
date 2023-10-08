package edu.hw1;

public class Task8 {

    boolean knightBoardCapture(int[][] knightBC) {
        int size = knightBC.length;
        int[] iNum = new int[] {-1, -1, 1, 1, -2, -2, 2, 2};
        int[] jNum = new int[] {2, -2, 2, -2, 1, -1, 1, -1};
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (knightBC[i][j] == 1) {
                    for (int k = 0; k < iNum.length; k++) {
                        int iNext = i + iNum[k];
                        int jNext = j + jNum[k];
                        if (isInRange(iNext, jNext, size) && knightBC[iNext][jNext] == 1) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean isInRange(int y, int x, int size) {
        return ((y >= 0 && y < size) && ((x >= 0 && x < size)));
    }
}
