package ad_dp.xiangqi;

import java.util.Scanner;

public class Xiangqi {

    final static int MOD = 9999973;

    static long[][][] f = new long[105][105][105];

    static int C(int x) {
        return (x * (x - 1) / 2) % MOD;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n, m;
        long res = 0L;
        n = scanner.nextInt();
        m = scanner.nextInt();
        scanner.close();

        f[0][0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= m - j; k++) {
                    // put 0 piece at i-th line.
                    f[i][j][k] = (f[i][j][k] + f[i - 1][j][k]) % MOD;

                    // put 1 piece at columns with 1 piece.
                    if (k >= 1) {

                        f[i][j][k] = (f[i][j][k] + f[i - 1][j + 1][k - 1] * (j + 1)) % MOD;
                    }


                    // put 1 piece at columns without pieces.
                    if (j >= 1) {
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j - 1][k] * (m - j - k + 1)) % MOD;
                    }


                    // put 2 pieces at 2 column with 1 piece
                    if (k >= 2) {
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j + 2][k - 2] * (j + 2) * (j + 1) / 2) % MOD;
                    }

                    // put 1 piece at a column without pieces, and put 1 piece at a column with 1 piece.
                    if (k >= 1) {
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j][k - 1] * j * (m - k - j + 1)) % MOD;
                    }

                    // put 2 pieces at 2 columns without pieces, respectively
                    if (j >= 2) {
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j - 2][k] * C(m - j - k + 2)) % MOD;
                    }
                }
            }
        }

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= m; j++) {
                res += f[n][i][j];
            }
            res %= MOD;
        }
        System.out.println(res);


    }


}
