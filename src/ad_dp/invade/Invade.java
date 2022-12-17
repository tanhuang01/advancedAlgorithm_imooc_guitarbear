package ad_dp.invade;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Invade {
    static int[] times = new int[10001];

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(
                Paths.get("src/ad_dp/invade", "1.in"));
//        Scanner scanner = new Scanner(System.in);

        int totalSet = scanner.nextInt();  // t groups

        for (int k = 0; k < totalSet; k++) {


            int n = scanner.nextInt();  // n rows in one group

            int[] a = new int[n];
            int[] b = new int[n];
            int[] d = new int[n];
            int[][] f = new int[601][601];

            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
                b[i] = scanner.nextInt();
                d[i] = scanner.nextInt();
                times[a[i]] = 1;
                times[b[i]] = 1;
            }

//        for (int i = 0; i < n; i++) {
//            System.out.printf("[%d, %d]", a[i], b[i]);
//        }
//        System.out.println();

            int timeN = 0;
            for (int i = 0; i < times.length; i++) {
                if (times[i] != 0) {
                    times[i] = ++timeN;
                }
            }

            for (int i = 0; i < n; i++) {
                a[i] = times[a[i]];
                b[i] = times[b[i]];
            }

//        for (int i = 0; i < n; i++) {
//            System.out.printf("[%d, %d]", a[i], b[i]);
//        }
//        System.out.println();

            for (int l = 1; l <= timeN; l++) {
                for (int i = 0; i <= timeN - l; i++) {
                    int j = i + l;
                    int maxDi = -1;
                    for (int di = 0; di < n; di++) {
                        if (a[di] >= i && b[di] <= j && (maxDi == -1 || d[di] > maxDi)) {
                            maxDi = di;
                        }
                    }
                    if (maxDi == -1) {
                        continue;
                    }
                    f[i][j] = 10001;
                    for (int t = a[maxDi]; t <= b[maxDi]; t++) {
                        int tmp = 0;
                        if (t - 1 >= i) {
                            tmp += f[i][t - 1];
                        }
                        if (t + 1 <= j) {
                            tmp += f[t + 1][j];
                        }
                        f[i][j] = Math.min(f[i][j], tmp + d[maxDi]);
                    }

                }
            }

//        for (int i = 0; i <= timeN; i++) {
//            for (int j = 0; j <= timeN; j++) {
//                System.out.print(f[i][j] + " ");
//            }
//            System.out.println();
//        }

            System.out.println(f[1][timeN]);
        }
    }
}












