class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int left = 0;
        int ones = 0;

        String ans = "";
        int minLen = Integer.MAX_VALUE;

        for (int right = 0; right < s.length(); right++) {

            // Add current character
            if (s.charAt(right) == '1') {
                ones++;
            }

            // More than k ones -> shrink
            while (ones > k) {
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }

            // Remove unnecessary leading zeroes
            while (ones == k && s.charAt(left) == '0') {
                left++;
            }

            // We have exactly k ones
            if (ones == k) {
                String curr = s.substring(left, right + 1);

                if (curr.length() < minLen ||
                    (curr.length() == minLen && curr.compareTo(ans) < 0)) {

                    ans = curr;
                    minLen = curr.length();
                }
            }
        }

        return ans;
    }
}