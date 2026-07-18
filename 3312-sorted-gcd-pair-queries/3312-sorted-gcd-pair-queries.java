import java.util.*;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {

        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int[] freq = new int[max + 1];
        for (int x : nums) freq[x]++;

        long[] countDiv = new long[max + 1];

        // count numbers divisible by i
        for (int i = 1; i <= max; i++) {
            for (int j = i; j <= max; j += i) {
                countDiv[i] += freq[j];
            }
        }

        // exactPairs[g] = number of pairs with gcd exactly g
        long[] exactPairs = new long[max + 1];

        for (int g = max; g >= 1; g--) {
            long total = countDiv[g];
            exactPairs[g] = total * (total - 1) / 2;

            for (int multiple = 2 * g; multiple <= max; multiple += g) {
                exactPairs[g] -= exactPairs[multiple];
            }
        }

        // prefix sums
        long[] prefix = new long[max + 1];
        for (int i = 1; i <= max; i++) {
            prefix[i] = prefix[i - 1] + exactPairs[i];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long q = queries[i];

            int l = 1;
            int r = max;

            while (l < r) {
                int mid = l + (r - l) / 2;

                if (prefix[mid] > q)
                    r = mid;
                else
                    l = mid + 1;
            }

            ans[i] = l;
        }

        return ans;
    }
}