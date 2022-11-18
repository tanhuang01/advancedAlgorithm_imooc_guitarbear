package ad_search;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;


public class DSF_ID_maze {

    // (x[i], y[i]) means east, south, west, north
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    private static int totalState = 2;

    private static StringBuilder res = null;

    private static int level;

    private static void dsf_init(int n, char[][] maze) {
        level = -1;
        while (true) {
            level++;
            int[][][] minTurns = new int[n + 2][n + 2][4];
            Node[][][] pre = new Node[n + 2][n + 2][4];
            for (int[][] minTurn : minTurns)
                for (int[] iterm : minTurn)
                    Arrays.fill(iterm, Integer.MAX_VALUE);

            // east
            dsf(1, 1, 0, -1, n, maze, minTurns, pre);
            // south
            dsf(1, 1, 1, -1, n, maze, minTurns, pre);

            if (res != null) {
                return;
            }
        }

    }

    private static void dsf(int x, int y, int dir, int turns,
                            int n, char[][] maze,
                            int[][][] minTurns, Node[][][] pre) {
        if (turns > level) {
            return;
        }
        int tx = x;
        int ty = y;
        totalState++;

        while (true) {
            tx += dx[dir];
            ty += dy[dir];

            if (maze[tx][ty] == '*') {
                break;
            }
            if (tx == n && ty == n) {
                updateResult(x, y, dir, pre);
            }

            for (int i = 0; i < 2; i++) {
                int id = (dir + 3 - i * 2) % 4;
                if (maze[tx + dx[id]][ty + dy[id]] == '.' &&
                        minTurns[tx][ty][id] > turns + 1) {
                    minTurns[tx][ty][id] = turns + 1;
                    pre[tx][ty][id] = new Node(x, y, dir, turns);
                    dsf(tx, ty, id, turns + 1,
                            n, maze, minTurns, pre);
                }
            }

        }
    }

    private static void updateResult(int x, int y, int dir, Node[][][] pre) {
        StringBuilder tempRes = new StringBuilder();
        tempRes.insert(0, String.format("(%d,%d) --> ", x, y));
        while (x != 1 && y != 1) {
            int tx = pre[x][y][dir].x;
            int ty = pre[x][y][dir].y;
            dir = pre[x][y][dir].dir;
            tempRes.insert(0, String.format("(%d,%d) -->", x, y));
            x = tx;
            y = ty;
        }
        if (res == null || tempRes.length() < res.length()) {
            res = tempRes;
        }
    }


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(
                Paths.get("src/ad_search/maze", "4.in"));
        int n = scanner.nextInt();
        char[][] maze = new char[n + 2][n + 2];
        for (int i = 0; i < n + 2; i++) {
            maze[i] = scanner.next().trim().toCharArray();
        }
        scanner.close();

        dsf_init(n, maze);

        res.append(String.format("(%d,%d)", n, n));

        System.out.println(res.toString());
        System.out.println("totalStates: " + totalState);
    }
}
