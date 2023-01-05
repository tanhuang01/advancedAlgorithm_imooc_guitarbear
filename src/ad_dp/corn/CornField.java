package ad_dp.corn;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class CornField {

    static final int MOD = 100_000_000;
    static int sn, res;
    static int[] field = new int[13];
    static int[] states = new int[4096];
    // f[i][j] refers to the number, that [i]-th row under the state [j].
    static int[][] f = new int[13][4096];

    private static void dfs(int x, int s, int n) {
        if (x == n) {
            states[sn++] = s;
            return;
        }
        for (int i = 0; i < 2; i++) {
            if ((s & i) == 0x0) {
                dfs(x + 1, (s << 1) + i, n);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(Paths.get("src/ad_dp/corn", "2.in"));
//        Scanner scanner = new Scanner(System.in);

        int m, n;
        m = scanner.nextInt();
        n = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                field[i] = (field[i] << 1) + (1 - scanner.nextInt());
            }
        }
        scanner.close();

        dfs(0, 0, n);

        for (int i = 0; i < sn; i++) {
            if ((states[i] & field[0]) == 0) {
                f[0][states[i]] = 1;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < sn; j++) {
                int s_pre = states[j];
                if (f[i - 1][s_pre] == 0) {
                    continue;
                }
                for (int k = 0; k < sn; k++) {
                    int s_nxt = states[k];
                    if ((s_nxt & s_pre) != 0 || (s_nxt & field[i]) != 0) {
                        continue;
                    }

                    f[i][s_nxt] += f[i - 1][s_pre];
                    f[i][s_nxt] %= MOD;
                }
            }
        }

        for (int i = 0; i < sn; i++) {
            res += (f[m - 1][states[i]]);
            res %= MOD;
        }
        System.out.println(res);
    }
}








