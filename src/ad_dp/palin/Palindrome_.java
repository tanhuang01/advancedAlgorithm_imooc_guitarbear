package ad_dp.palin;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Palindrome_ {

    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(
                Paths.get("src/ad_dp/palin", "1.in"));
        int n = scanner.nextInt();

        char[][] grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            grid[i] = scanner.next().toCharArray();
        }
        scanner.close();

        long[][][] f = new long[2][n][n];
        for (int i = 0; i < n; i++)
            f[0][n - i - 1][n - i - 1] = 1;

        for (int k = 1; k < n; k++) {
            int k1 = k % 2;
            int k2 = (k - 1) % 2;

            for (int i = 0; i < f[k1].length; i++) {
                Arrays.fill(f[k1][i], 0);
            }

            for (int i = 0; i < n - k; i++) {
                int iy = n - k - i - 1;
                for (int j = k; j < n; j++) {
                    int jy = n + k - j - 1;
                    if (grid[i][iy] == grid[j][jy]) {
                        f[k1][i][j] += f[k2][i + 1][j - 1] * ((grid[i + 1][iy] == grid[j - 1][jy]) ? 1: 0);
                        f[k1][i][j] += f[k2][i + 1][j] * ((grid[i + 1][iy] == grid[j][jy - 1]) ? 1: 0);
                        f[k1][i][j] %= MOD;
                        f[k1][i][j] += f[k2][i][j - 1] * ((grid[i][iy + 1] == grid[j - 1][jy])? 1 : 0);
                        f[k1][i][j] += f[k2][i][j] * ((grid[i][iy + 1] == grid[j][jy - 1]) ? 1 : 0);
                        f[k1][i][j] %= MOD;
                    }
                }
            }

            System.out.println();
            for (int i = 0; i < n; i++) {
                System.out.println(Arrays.toString(f[k1][i]));
            }
        }

        System.out.println(f[(n - 1) % 2][0][n - 1]);

    }
}
