package ad_dp.reg_match;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class RegMatch {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(
                Paths.get("src/ad_dp/regMatch", "1.in"));

        String s = "." + scanner.next();
        String p = "." + scanner.next();

        scanner.close();

        boolean[][] dp = new boolean[s.length()][p.length()];
        dp[0][0] = true;

        for (int i = 1; i < s.length(); i++) {
            for (int j = 1; j < p.length(); j++) {
                char ch = p.charAt(j);
                if (ch == '*') {
                    // we assume that the match string p is legal.
                    // so '*' will never be the first letter.
                    // and there is no two successive '*'.
                    int k = j - 2;
                    if (p.charAt(j - 1) == '.' && i - 2 > 0) {
                        while (k >= 0 && !dp[i-2][k]) {
                            k--;
                        }
                        dp[i][j] = dp[i - 2][k];
                    } else { // p.charAt(j - 1) is a letter.
                        while (s.charAt(k) != p.charAt(j - 1) && k > 0) {
                            k--;
                        }
                        if (dp[k][j - 2]) {
                            dp[i][j] = true;
                        }
                    }
                } else if (ch == '.') {
                    if (j + 1 < p.length() && p.charAt(j + 1) != '*'
                            || j == p.length() - 1) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else { // ch is a character.
                    dp[i][j] = dp[i - 1][j - 1] && ch == s.charAt(i);
                }
            }
        }

        System.out.println(dp[s.length() - 1][p.length() - 1]);

    }


}
