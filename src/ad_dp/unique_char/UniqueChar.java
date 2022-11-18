package ad_dp.unique_char;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class UniqueChar {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(
                Paths.get("src/ad_dp/unique_char", "3.in"));
        String s = scanner.next();

        int[] dp = new int[26];
        int[] pre = new int[26];
        Arrays.fill(pre, -1);

        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i) - 'A';
            for (int j = 0; j < dp.length; j++) {
                if (ch == j) {
                    if (pre[ch] == -1) {
                        dp[j] = dp[j] + i - pre[ch];
                    } else {
                        dp[j] = i - pre[ch];
                    }
                    pre[ch] = i;
                }
                sum += dp[j];
            }
        }

        System.out.println(sum);
    }


}
