
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            res = Math.max(dp[i], res);
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(
//                Paths.get("src/ad_dp/palin", "2.in"));
//        Scanner scanner = new Scanner(System.in);
//        scanner.close();

        for (int i = 21; i < 42; i++) {
            System.out.println(i);
        }

    }


}
