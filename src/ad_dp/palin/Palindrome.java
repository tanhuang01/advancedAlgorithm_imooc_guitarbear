package ad_dp.palin;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Palindrome {
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(
                Paths.get("src/ad_dp/palin", "2.in"));
//        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        char[][] a = new char[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = ("0" + scanner.next()).toCharArray();
        }
        scanner.close();

        if (a[1][1] != a[n][n]) {
            System.out.println(0);
            return;
        }

        long[][] dp = new long[n + 2][n + 2];

        /*
        dp[i][j] means:
            A is from (1,1), while B is from (n,n).
            [i][] is the abscissa of A and [][j] is abscissa of B,
            the number of palindromic line is dp[i][j].
         */
        dp[1][n] = 1;
        for (int k = 1; k < n; k++) {
            for (int i = k + 1; i >= 1; i--) {
                for (int j = n - k; j <= n; j++) {
                    if (a[i][k + 2 - i] == a[j][n * 2 - k - j]) {
                        dp[i][j] = dp[i][j] + dp[i - 1][j] + dp[i][j + 1] + dp[i - 1][j + 1];
                        dp[i][j] %= MOD;
                    } else {
                        dp[i][j] = 0;
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += dp[i][i];
            ans %= MOD;
        }
        System.out.println(ans);


    }


}
