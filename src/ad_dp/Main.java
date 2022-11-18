package ad_dp;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(Paths.get("src", "input"));
        Scanner scanner = new Scanner(System.in);

        int m, n;
        n = scanner.nextInt();
        m = scanner.nextInt();

        int[][] rect = new int[n + 1][m + 1], dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                rect[i][j] = scanner.nextInt();
            }
        }

        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (rect[i][j] == 1) {
                    int k = Math.min(dp[i - 1][j], dp[i][j - 1]);
                    dp[i][j] = k + rect[i - k][j - k];
                    res = Math.max(res, dp[i][j]);
                }
            }
        }

        System.out.println(res);

    }


}
