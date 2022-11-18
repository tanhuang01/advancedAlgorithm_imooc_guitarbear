import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class AnimalExhibition {

    private static final int OFFSET = 400_000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();  // Num of the products

        int[] S = new int[N + 1];
        int[] F = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            S[i] = scanner.nextInt();
            F[i] = scanner.nextInt();
        }

        // dp[i][j] means that under intelligence j,
        // the max emotional with the first i animals
        int[][] dp = new int[N + 1][800_000];
        for (int[] subDp : dp) {
            Arrays.fill(subDp, Integer.MIN_VALUE);
        }

        dp[0][OFFSET] = 0;
        int minRange = OFFSET;
        int maxRange = OFFSET;

        for (int i = 1; i <= N; i++) {
            if (S[i] > 0) {
                for (int j = maxRange; j >= minRange; j--) {
                    if (dp[i - 1][j] > Integer.MIN_VALUE) {
                        dp[i][j] = dp[i - 1][j];
                        if (dp[i - 1][j + S[i]] < dp[i - 1][j] + F[i]) {
                            dp[i][j + S[i]] = dp[i - 1][j] + F[i];
                        }
                    }
                }
                maxRange += S[i];
            } else { // S[i] < 0
                for (int j = minRange; j <= maxRange; j++) {
                    if (dp[i - 1][j] > Integer.MIN_VALUE) {
                        dp[i][j] = dp[i - 1][j];
                        if (dp[i - 1][j + S[i]] < dp[i - 1][j] + F[i]) {
                            dp[i][j + S[i]] = dp[i - 1][j] + F[i];
                        }
                    }
                }
                minRange += S[i];
            }
        }

        IntStream.rangeClosed(OFFSET, maxRange)
                .filter(i -> dp[N][i] > 0)
                .map(i -> dp[N][i] + i)
                .max()
                .ifPresent(res -> System.out.println(res - OFFSET));

    }
}

















