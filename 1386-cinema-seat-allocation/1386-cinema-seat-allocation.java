import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Store reserved seats of each affected row as a bitmask
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            // Seats 2 to 9 are the only important ones
            if (col >= 2 && col <= 9) {
                map.put(row, map.getOrDefault(row, 0) | (1 << col));
            }
        }

        // Every completely empty row can accommodate 2 families
        int ans = (n - map.size()) * 2;

        for (int mask : map.values()) {

            // Family: seats 2,3,4,5
            boolean left = (mask & 0b000000111100) == 0;

            // Family: seats 6,7,8,9
            boolean right = (mask & 0b1111000000) == 0;

            // Family: seats 4,5,6,7
            boolean middle = (mask & 0b000011110000) == 0;

            if (left && right) {
                ans += 2;
            } 
            else if (left || right || middle) {
                ans += 1;
            }
        }

        return ans;
    }
}