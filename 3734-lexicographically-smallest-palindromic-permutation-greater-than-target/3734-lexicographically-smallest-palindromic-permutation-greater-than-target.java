class Solution {

    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();

        // Frequency of characters
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // A palindrome can have at most one odd frequency
        int odd = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        // Frequency available for left half
        int[] half = new int[26];

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
        }

        int halfLen = n / 2;

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {

            boolean found = false;

            // Try smallest possible character
            for (int c = 0; c < 26; c++) {

                if (half[c] == 0) {
                    continue;
                }

                // Choose this character
                half[c]--;
                left.append((char) ('a' + c));

                // Check whether some completion can be > target
                if (canBeGreater(left, half, middle, target)) {
                    found = true;
                    break;
                }

                // Undo
                left.deleteCharAt(left.length() - 1);
                half[c]++;
            }

            if (!found) {
                return "";
            }
        }

        // Construct final palindrome
        StringBuilder ans = new StringBuilder();

        ans.append(left);

        if (n % 2 == 1) {
            ans.append(middle);
        }

        for (int i = left.length() - 1; i >= 0; i--) {
            ans.append(left.charAt(i));
        }

        return ans.toString().compareTo(target) > 0
                ? ans.toString()
                : "";
    }

    private boolean canBeGreater(
            StringBuilder left,
            int[] half,
            char middle,
            String target) {

        StringBuilder temp = new StringBuilder(left);

        // Put remaining characters in DESCENDING order.
        // This gives the largest possible completion.
        for (int c = 25; c >= 0; c--) {

            for (int k = 0; k < half[c]; k++) {
                temp.append((char) ('a' + c));
            }
        }

        // Build palindrome
        StringBuilder palindrome = new StringBuilder(temp);

        if (target.length() % 2 == 1) {
            palindrome.append(middle);
        }

        for (int i = temp.length() - 1; i >= 0; i--) {
            palindrome.append(temp.charAt(i));
        }

        return palindrome.toString().compareTo(target) > 0;
    }
}