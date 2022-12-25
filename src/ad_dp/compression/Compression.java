package ad_dp.compression;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Compression {
    private static int calLen(int[][] f, int l, int mid, int r) {
        int len, times;
        if (mid < (r + l + 1) / 2) {
            len = f[l][mid];
            times = (r - l + 1) / (mid - l + 1);
        } else {
            len = f[mid + 1][r];
            times = (r - l + 1) / (r - mid);
        }
        if (times >= 100) {
            len += 2;
        } else if (times >= 10) {
            len += 1;
        }
        return len + 3;
    }

    private static boolean isRepeat(char[] s, int l, int mid, int r) {
        if (mid < (r + l + 1) / 2) {
            if ((r - mid) % (mid - l + 1) != 0) return false;
            int t = mid - l + 1;
            for (int i = 0; i < r - mid; i++) {
                if (s[mid + 1 + i] != s[l + (i % t)]) return false;
            }
        } else {
            if ((mid - l + 1) % (r - mid) != 0)
                return false;
            int t = r - mid;
            for (int i = 0; i < mid - l + 1; i++) {
                if (s[mid + 1 + (i % t)] != s[l + i]) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(
//                Paths.get("src/ad_dp/compression", "4.in"));
        Scanner scanner = new Scanner(System.in);

        char[] s = scanner.next().toCharArray();
        scanner.close();

        int n = s.length;
        int[][] f = new int[n][n];

        for (int i = 0; i < n; i++) {
            f[i][i] = 1;
        }

        for (int len = 1; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len;
                f[i][j] = len + 1;
                for (int k = i; k < j; k++) {
                    f[i][j] = Math.min(f[i][j], f[i][k] + f[k + 1][j]);
                    if (isRepeat(s, i, k, j)) {
                        f[i][j] = Math.min(f[i][j], calLen(f, i, k, j));
                    }
                }
            }
        }

        System.out.println(f[0][n - 1]);
    }
}







