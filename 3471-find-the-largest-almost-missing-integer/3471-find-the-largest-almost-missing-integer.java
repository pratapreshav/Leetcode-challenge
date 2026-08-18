import java.util.*;

class Solution {
    public int largestInteger(int[] nums, int k) {

        int n = nums.length;
        Map<Integer, Integer> windowCount = new HashMap<>();

        for (int i = 0; i <= n - k; i++) {

            Set<Integer> set = new HashSet<>();

            for (int j = i; j < i + k; j++) {
                set.add(nums[j]);
            }

         
            for (int num : set) {
                windowCount.put(num, windowCount.getOrDefault(num, 0) + 1);
            }
        }

        int ans = -1;

        for (int num : windowCount.keySet()) {
            if (windowCount.get(num) == 1) {
                ans = Math.max(ans, num);
            }
        }

        return ans;
    }
}