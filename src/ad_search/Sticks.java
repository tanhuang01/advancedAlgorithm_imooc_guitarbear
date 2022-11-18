package ad_search;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Sticks {

    private static Integer[] sticks;
    private static boolean[] marked;
    private static int n, stickNum;
    private static boolean findAnswer = false;

    private static void dsf(int k, int l, int s, int stickLen) {
        if (k == stickNum - 1) {
            findAnswer = true;
            return;
        }

        if (l == stickLen) {
            dsf(k + 1, 0, -1, stickLen);
        }

        int prei = -1;
        for (int i = s + 1; i < n; i++) {
            if (!marked[i] && l + sticks[i] <= stickLen) {
                if (prei > -1 && sticks[i].equals(sticks[prei])) {
                    continue;
                }
                prei = i;
                marked[i] = true;
                dsf(k, l + sticks[i], i, stickLen);
                marked[i] = false;

                if ((l == 0) || (l + sticks[i] == stickLen) && !findAnswer) {
                    return;
                }

                if (findAnswer) {
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(
                Paths.get("src/ad_search/stick", "6.in"));

        n = scanner.nextInt();
        sticks = new Integer[n];
        marked = new boolean[n];
        Arrays.fill(marked, false);
        int maxStick = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sticks[i] = scanner.nextInt();
            if (sticks[i] > maxStick) maxStick = sticks[i];
            sum += sticks[i];
        }
        scanner.close();

        Arrays.sort(sticks, (o1, o2) -> o2 - o1);
        System.out.println(n);
        System.out.println(Arrays.toString(sticks));


        for (int stickLen = maxStick; stickLen <= sum / 2; stickLen++) {
            if (sum % stickLen != 0)
                continue;

            stickNum = sum / stickLen;

            dsf(0, 0, -1, stickLen);
            if (findAnswer) {
                System.out.println(stickLen);
                return;
            }
        }

        if (!findAnswer) {
            System.out.println(sum);
        }
    }
}












