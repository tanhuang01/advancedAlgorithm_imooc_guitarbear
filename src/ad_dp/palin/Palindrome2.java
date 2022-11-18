package ad_dp.palin;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Palindrome2 {
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(
                Paths.get("src/ad_dp/palin", "1.in"));
//        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        char[][] a = new char[n][n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next().toCharArray();
        }
        scanner.close();

        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(a[i]));
        }

        // we map the square to a diamond
        long[][][] dp = new long[2][n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            // in the center line of the diamond,
            // from i-th num to the i-th num,
            // we can get one palindrome
            dp[0][i][i] = 1;
        }

        for (int k = 1; k < n; k++) {
            int k2 = k % 2;
            int k1 = (k + 1) % 2;
            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[k2][i], 0);
            }
            for (int i = n - k - 1; i >= 0; i--) {
                int iy = n - k - 1 - i;
                int idp = iy;
//                System.out.printf("i:[%d][%d] ", i, iy);
                for (int j = n - 1; j >= k; j--) {
                    int jy = n + k - 1 - j;
                    int jdp = jy - 1;
//                    System.out.printf("j:[%d][%d] ", j, jy);

                    if (a[i][iy] == a[j][jy]) {
                        dp[k2][idp][jdp] += (dp[k1][idp][jdp] * (a[i + 1][iy] == a[j][jy - 1] ? 1 : 0));
                        dp[k2][idp][jdp] += (dp[k1][idp + 1][jdp] * (a[i][iy + 1] == a[j][jy - 1] ? 1 : 0));
                        dp[k2][idp][jdp] += (dp[k1][idp][jdp + 1] * (a[i + 1][iy] == a[j - 1][jy - 1] ? 1 : 0));
                        dp[k2][idp][jdp] += (dp[k1][idp + 1][jdp + 1] * (a[i][iy + 1] == a[j - 1][jy - 1] ? 1 : 0));
                        dp[k2][idp][jdp] %= MOD;
                    }
                    // do nothing if a[i][iy] != a[j][jy]
                }
            }
//            System.out.println();
        }

        System.out.println(dp[(n + 1) % 2][0][n - 1]);

    }


}
