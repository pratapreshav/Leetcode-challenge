class Solution {

    int[] left, right, prefix, suffix, best;
    char[] arr;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {

        int n = s.length();

        arr = s.toCharArray();

        left = new int[4 * n];
        right = new int[4 * n];
        prefix = new int[4 * n];
        suffix = new int[4 * n];
        best = new int[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {

            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            arr[index] = ch;

            update(1, 0, n - 1, index);

            ans[i] = best[1];
        }

        return ans;
    }

    void build(int node, int l, int r) {

        left[node] = l;
        right[node] = r;

        if (l == r) {
            prefix[node] = 1;
            suffix[node] = 1;
            best[node] = 1;
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        merge(node);
    }

    void update(int node, int l, int r, int index) {

        if (l == r) {
            prefix[node] = 1;
            suffix[node] = 1;
            best[node] = 1;
            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index);
        } else {
            update(node * 2 + 1, mid + 1, r, index);
        }

        merge(node);
    }

    void merge(int node) {

        int leftNode = node * 2;
        int rightNode = node * 2 + 1;

        // IMPORTANT: boundaries of children
        int l1 = left[leftNode];
        int r1 = right[leftNode];

        int l2 = left[rightNode];
        int r2 = right[rightNode];

        prefix[node] = prefix[leftNode];
        suffix[node] = suffix[rightNode];

        best[node] = Math.max(
            best[leftNode],
            best[rightNode]
        );

        // Can we join the two children?
        if (arr[r1] == arr[l2]) {

            best[node] = Math.max(
                best[node],
                suffix[leftNode] + prefix[rightNode]
            );

            // Entire left child has same character
            if (prefix[leftNode] == r1 - l1 + 1) {
                prefix[node] =
                    prefix[leftNode] + prefix[rightNode];
            }

            // Entire right child has same character
            if (suffix[rightNode] == r2 - l2 + 1) {
                suffix[node] =
                    suffix[leftNode] + suffix[rightNode];
            }
        }
    }
}