package ad_dp.frog;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Frog {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(Paths.get("src/ad_dp/frog", "3.in"));
//        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] stones = new int[n];
        for (int i = 0; i < stones.length; i++) {
            stones[i] = scanner.nextInt();
        }

//        System.out.println(Arrays.toString(stones));

        boolean[][] dp = new boolean[n][2001];
        dp[0][0] = true;

        for (int i = 1; i < n; i++) {
            int k = i - 1;
            for (int j = 0; j <= i; j++) {
                while (k >= 0 && stones[i] - stones[k] < j) {
                    k--;
                }
                if (k >= 0 && stones[i] - stones[k] == j) {
                    dp[i][j] = dp[k][j - 1] || dp[k][j] || dp[k][j + 1];
                }
            }
        }

        boolean res = false;
        for (boolean b : dp[n - 1]) {
            if (b) {
                res = true;
                break;
            }
        }
        System.out.println(res);

    }


}
