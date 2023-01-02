package ad_dp.king;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class King {

    static int[] states = new int[513];
    static int[] nums = new int[513];

    static int stNum = 0;

    //f[i][j][s] means the number under the circumstance
    // that the [i]-th rows with [j] pieces under [s]-state
    static long[][][] f = new long[9][81][513];

    static void dfs(int x, int s, int num, int n) {
        if (x == n) {
            states[stNum] = s;
            nums[stNum++] = num;
            return;
        }

        for (int i = 0; i < 2; i++) {
            if ((s & i) != 1) {
                dfs(x + 1, (s << 1) + i, num + i, n);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(Paths.get("src/ad_dp/king", "1.in"));
//        Scanner scanner = new Scanner(System.in);

        int n, k;
        n = scanner.nextInt();
        k = scanner.nextInt();

        dfs(0, 0, 0, n);

//        for (int i = 0; i < stNum; i++) {
//            System.out.printf("No: %d, num:%d, %s\n", i, nums[i], Integer.toBinaryString(states[i]));
//        }


        for (int i = 0; i < stNum; i++) {
            f[0][nums[i]][states[i]] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int c = 0; c <= k; c++) {
                for (int st_j = 0; st_j < stNum; st_j++) {
                    // previous state
                    int st_pre = states[st_j];

                    // previous states should be meaningful
                    if (f[i - 1][c][st_pre] == 0)
                        continue;

                    // next state
                    int cover = st_pre | (st_pre >>> 1) | (st_pre << 1);
                    for (int st_i = 0; st_i < stNum; st_i++) {
                        int st_nxt = states[st_i];
                        int st_num = nums[st_i];
                        if ((st_nxt & cover) != 0 || c + st_num > k) {
                            continue;
                        }
                        f[i][c + st_num][st_nxt] += f[i - 1][c][st_pre];
                    }

                }
            }
        }


        long res = 0L;
        for (int i = 0; i < stNum; i++) {
            res += f[n - 1][k][states[i]];
        }
        System.out.println(res);

    }
}
