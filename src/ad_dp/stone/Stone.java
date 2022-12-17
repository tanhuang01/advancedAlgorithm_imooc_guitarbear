package ad_dp.stone;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Stone {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(
                Paths.get("src/ad_dp/stone", "1.in"));
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (scanner.hasNext()) {
            list.add(scanner.nextInt());
        }
        scanner.close();
        int n = list.size();

        if (n == 1) {
            System.out.println(0);
            return;
        }

        Integer[] arr = list.toArray(new Integer[0]);
//        System.out.println(Arrays.toString(arr));

        int[] sum = new int[n + 1];
        sum[0] = 0;
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }

        /*
         * f[i][j] means that the max value we can get from range [i,j]
         */
        int[][] f = new int[n][n];
        for (int k = 1; k < n; k++) {
            for (int i = 0; i < n - k; i++) {
                int j = i + k;
                for (int m = i; m < j; m++) {
                    int sumL = sum[m + 1] - sum[i];
                    int sumR = sum[j + 1] - sum[m + 1];
                    if (sumL <= sumR) {
                        f[i][j] = Math.max(f[i][j], sumL + f[i][m]);
                    }
                    if (sumL >= sumR) {
                        f[i][j] = Math.max(f[i][j], sumR + f[m + 1][j]);
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(f[i]));
        }

        System.out.println(f[0][n - 1]);
    }
}















