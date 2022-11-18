package ad_dp.word_break;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class WordBreak {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(
                Paths.get("src/ad_dp/word_break", "2.in"));
        String s = "." + scanner.next();
        int n = scanner.nextInt();
        String[] dict = new String[n];
        for (int i = 0; i < dict.length; i++) {
            dict[i] = scanner.next();
        }

        boolean[] dp = new boolean[s.length()];
        dp[0] = true;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dict.length; j++) {
                if (dict[j].length() > i) {
                    continue;
                }

                if (s.substring(i - dict[j].length() + 1, i + 1).equals(dict[j])
                        && dp[i - dict[j].length()]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        System.out.println(dp[s.length() - 1]);

    }


}
