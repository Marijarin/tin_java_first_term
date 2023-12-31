package edu.hw1;

public class Task7 {
    int rotateRight(int n, int shift) {
        if (shift == 0) {
            return n;
        } else if (shift < 0) {
            return rotateLeft(n, Math.abs(shift));
        } else {
            String bits = Integer.toBinaryString(n);
            String taken = bits.substring(bits.length() - shift);
            String result = taken + bits.substring(0, bits.length() - shift);
            return Integer.parseInt(result, 2);
        }
    }

    int rotateLeft(int n, int shift) {
        if (shift == 0) {
            return n;
        } else if (shift < 0) {
            return rotateRight(n, Math.abs(shift));
        } else {
            String bits = Integer.toBinaryString(n);
            String taken = bits.substring(shift);
            String result = taken + bits.substring(0, shift);
            return Integer.parseInt(result, 2);
        }
    }
}
