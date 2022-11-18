
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(
//                Paths.get("src/ad_dp/palin", "2.in"));
        Scanner scanner = new Scanner(System.in);
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
        for (int i = 1; i < n; i++) {
            for (int o = i + 1; o >= 1; o--) {
                for (int u = n - i; u <= n; u++) {
                    if (a[o][i + 2 - o] == a[u][n * 2 - i - u]) {
                        dp[o][u] = dp[o][u] + dp[o - 1][u] + dp[o][u + 1] + dp[o - 1][u + 1];
                        dp[o][u] %= MOD;
                    } else {
                        dp[o][u] = 0;
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
