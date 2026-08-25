class Solution {
    public int missingMultiple(int[] nums, int k) {

        boolean[] seen = new boolean[101];

        // Mark numbers present in nums
        for (int x : nums) {
            seen[x] = true;
        }

        // Check k, 2k, 3k, ...
        for (int i = 1; ; i++) {
            int multiple = i * k;

            if (multiple >= seen.length || !seen[multiple]) {
                return multiple;
            }
        }
    }
}