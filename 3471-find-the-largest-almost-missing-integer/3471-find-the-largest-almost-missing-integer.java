class Solution {
    public int largestInteger(int[] nums, int k) {

        int n = nums.length;
        int ans = -1;

        for (int i = 0; i <= n - k; i++) {

            // Current window
            for (int j = i; j < i + k; j++) {

                int count = 0;

                // Check current number in all windows
                for (int start = 0; start <= n - k; start++) {

                    boolean found = false;

                    for (int x = start; x < start + k; x++) {
                        if (nums[x] == nums[j]) {
                            found = true;
                            break;
                        }
                    }

                    if (found) {
                        count++;
                    }
                }

                if (count == 1) {
                    ans = Math.max(ans, nums[j]);
                }
            }
        }

        return ans;
    }
}