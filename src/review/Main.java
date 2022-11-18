package review;

import java.util.Scanner;

public class Main {


    private static char[][] map = new char[6][6];
    private static char[][] check =

            {
                    {'0', '0', '0', '0', '0', '0',},
                    {'0', '1', '1', '1', '1', '1',},
                    {'0', '0', '1', '1', '1', '1',},
                    {'0', '0', '0', '*', '1', '1',},
                    {'0', '0', '0', '0', '0', '1',},
                    {'0', '0', '0', '0', '0', '0',},
            };

    private static int[] dx = {0, -1, +1, -2, +2, -2, +2, -1, +1};
    private static int[] dy = {0, -2, -2, -1, -1, +1, +1, +2, +2};

    private static int ans;


    private static void swap(
            char[][] board, int x1, int y1, int x2, int y2) {
        char temp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = temp;
    }


    static int dif() {
        int ret = 0;
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                if (map[i][j] != check[i][j]) ret++;
            }
        }
        return ret;
    }

    static void dfs(int y, int x, int d, int f) {
        int l = dif();
        if (d + l > 16) return;
        if (d >= ans) return;
        if (l == 0) {
            ans = d;
            return;
        }
        for (int i = 1; i <= 8; i++) {
            if ((y + dy[i] < 1) || (y + dy[i] > 5)) continue;
            if ((x + dx[i] < 1) || (x + dx[i] > 5)) continue;
            if (f + i != 9) {
                swap(map, x + dx[i], y + dy[i], x, y);
                dfs(y + dy[i], x + dx[i], d + 1, i);
                swap(map, x + dx[i], y + dy[i], x, y);
            }
        }
    }


    static void init(Scanner sc) {
        int yy = -1, x = -1;
        String s;
        for (int i = 1; i <= 5; i++) {
            s = "0" + sc.nextLine().trim();
            map[i] = s.toCharArray();
        }
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                if (map[i][j] == '*') {
                    yy = j;
                    x = i;
                }
            }
        }
        ans = 25;
        dfs(yy, x, 0, 0);
        System.out.printf("%d\n", ans == 25 ? -1 : ans);
    }

    public static void main(String[] args) {
        int t = 0;
        Scanner sc = new Scanner(System.in);
        t = Integer.parseInt(sc.nextLine().trim());
        while (t-- > 0) init(sc);

    }
}