package ad_dp.invade;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Invade_Standard {
    static int[][] f = new int[601][601];
    static int[] times = new int[10001];
    static int[] a = new int[301], b = new int[301], d = new int[301];
    static int n, totalSets;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(
                Paths.get("src/ad_dp/invade", "1.in"));
//        Scanner scanner = new Scanner(System.in);

        int totalSets;
        totalSets = scanner.nextInt();
        for (int sets = 0; sets < totalSets; sets++) {
            for (int i = 0; i < f.length; i++) {
                Arrays.fill(f[i], 0);
            }
            Arrays.fill(times, 0);
            n = scanner.nextInt();

            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
                b[i] = scanner.nextInt();
                d[i] = scanner.nextInt();
                times[a[i]] = 1;
                times[b[i]] = 1;
            }
            int timeN = 0;
            for (int i = 0; i <= 10000; i++)
                if (times[i] == 1) {
                    times[i] = ++ timeN;
                }
            for (int i = 0; i < n; i++) {
                a[i] = times[a[i]];
                b[i] = times[b[i]];
            }

            for (int l = 1; l <= timeN; l++)
                for (int i = 1; i <= timeN - l; i++) {
                    int j = i + l;
                    int maxDi = -1;
                    for (int di = 0; di < n; di++)
                        if (a[di] >= i && b[di] <= j && (maxDi == -1 || d[di] > d[maxDi]))
                            maxDi = di;
                    if (maxDi == -1)
                        continue;
                    f[i][j] = 10001;
                    for (int t = a[maxDi]; t <= b[maxDi]; t++) {
                        int tmp = 0;
                        if (t - 1 >= i)
                            tmp += f[i][t - 1];
                        if (t + 1 <= j)
                            tmp += f[t + 1][j];
                        f[i][j] = Math.min(f[i][j], tmp + d[maxDi]);
                    }
                }

            System.out.println(f[1][timeN]);
        }
    }
}
