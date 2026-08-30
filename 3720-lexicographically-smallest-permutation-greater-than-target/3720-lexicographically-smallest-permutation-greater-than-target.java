class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int[] freq = new int[26];

        // Frequency of characters in s
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        int[] temp = freq.clone();

        // pivot = last position where we can make
        // the answer strictly greater
        int pivot = -1;

        for (int i = 0; i < target.length(); i++) {

            int t = target.charAt(i) - 'a';

            // Can we put a character GREATER than target[i]?
            for (int c = t + 1; c < 26; c++) {
                if (temp[c] > 0) {
                    pivot = i;
                    break;
                }
            }

            // If same character is unavailable,
            // we cannot continue matching target
            if (temp[t] == 0) {
                break;
            }

            // Use same character and continue
            temp[t]--;
        }

        // No possible position to make string greater
        if (pivot == -1) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        // Restore original frequency
        int[] remaining = freq.clone();

        // Keep target's prefix unchanged
        for (int i = 0; i < pivot; i++) {
            char ch = target.charAt(i);

            ans.append(ch);
            remaining[ch - 'a']--;
        }

        // At pivot, choose the SMALLEST character
        // greater than target[pivot]
        int t = target.charAt(pivot) - 'a';

        for (int c = t + 1; c < 26; c++) {

            if (remaining[c] > 0) {
                ans.append((char) ('a' + c));
                remaining[c]--;
                break;
            }
        }

        // Put all remaining characters in sorted order
        for (int c = 0; c < 26; c++) {
            while (remaining[c] > 0) {
                ans.append((char) ('a' + c));
                remaining[c]--;
            }
        }

        return ans.toString();
    }
}