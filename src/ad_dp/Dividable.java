package ad_dp;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Dividable {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(Paths.get("src", "input"));
//        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        for (int im = 0; im < m; im++) {
            int n, k;
            n = scanner.nextInt();
            k = scanner.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = scanner.nextInt();
                if (nums[i] < 0) {
                    nums[i] = -nums[i];
                }
                nums[i] %= k;
            }

            boolean[][] dp = new boolean[n][k];
//            dp[0][0] = ture;  // no signal before first number
            dp[0][nums[0]] = true;
//            System.out.println(Arrays.deepToString(dp));

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < k; j++) {
                    if (dp[i - 1][j]) {
                        dp[i][(j + nums[i]) % k] = true;
                        dp[i][(j + k - nums[i]) % k] = true;
                    }
                }
            }

            if (dp[n - 1][0]) {
                System.out.println("Divisible");
            } else {
                System.out.println("Not divisible");
            }
        }   // end im
    }


}
