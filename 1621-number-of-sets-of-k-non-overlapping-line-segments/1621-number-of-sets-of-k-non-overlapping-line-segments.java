class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1000000007L;
        long ans = 1;

        for (int i = 1; i <= 2 * k; i++) {
            ans = ans * (n + k - i) % MOD;
            ans = ans * modInverse(i, MOD) % MOD;
        }

        return (int) ans;
    }

    long modInverse(long a, long mod) {
        return power(a, mod - 2, mod);
    }

    long power(long a, long b, long mod) {
        long res = 1;

        while (b > 0) {
            if ((b & 1) == 1)
                res = res * a % mod;

            a = a * a % mod;
            b >>= 1;
        }

        return res;
    }
}