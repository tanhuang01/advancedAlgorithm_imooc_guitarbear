package ad_search;

class PuzzleUtilities {

    static final long[] fact =
            {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800,
                    39916800, 479001600,};

    // use Cantor expansion
    static int getPermutationHash(int[] perm) {
        int res = 0;
        for (int i = 0; i < perm.length; i++) {
            int t = 0;
            for (int j = 0; j < i; j++)
                if (perm[j] > perm[i]) t++;
            res += t * fact[i];

        }
        return res;
    }


}
