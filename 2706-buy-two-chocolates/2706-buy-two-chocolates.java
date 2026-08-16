class Solution {
    public int buyChoco(int[] nums, int money) {
        Arrays.sort(nums);

        int cost = nums[0] + nums[1];

        if (cost <= money) {
            return money - cost;
        } else {
            return money;
        }
    }
}