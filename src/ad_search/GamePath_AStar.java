package ad_search;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class GamePath_AStar {

    private final static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    private final static int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};

    private static int[][] hashCash;
    private static int[][] pre;

    private static int statesCount = 1;
    private static long start = System.currentTimeMillis();

    private static void bsf_a(int m, int n, char[][] gameBoard,
                              int sx, int sy, int ex, int ey) {
        BoardState state = new BoardState(sx, sy, 0);
        state.calcHeuristic(ex, ey);
        Queue<BoardState> queue = new PriorityQueue<>();
        queue.offer(state);

        while (!queue.isEmpty()){

            state = queue.poll();

            for (int i = 0; i < 8; i++) {
                int tx = state.x + dx[i];
                int ty = state.y + dy[i];
                int g = state.g += (i % 2 == 0 ? 10 : 14);

                if (tx < 0 || ty < 0 || tx >= m || ty >= n
                        || gameBoard[tx][ty] == '#'
                        || hashCash[tx][ty] <= g) {
                    continue;
                }

                hashCash[tx][ty] = g;
                pre[tx][ty] = i;

                if (tx == ex && ty == ey) {
                    printResult(sx, sy, ex, ey, gameBoard, pre);
                    return;
                }

                BoardState nState = new BoardState(tx, ty, g);
                nState.calcHeuristic(ex, ey);
                queue.offer(nState);
                statesCount++;
            }
        }

    }

    private static void printResult(int sx, int sy, int ex, int ey,
                                    char[][] gameBoard, int[][] pre) {
        System.out.printf("Total Length: %d\n", hashCash[ex][ey]);
        System.out.printf("Total states: %d\n", statesCount);
        System.out.printf("Total time: %d ms\n", (System.currentTimeMillis() - start));
        if (gameBoard.length > 100) {
            return;
        }
        int tx = ex, ty = ey;
        while (tx != sx || ty != sy) {
            gameBoard[tx][ty] = '%';
            int dir = pre[tx][ty];
            tx -= dx[dir];
            ty -= dy[dir];
        }
        gameBoard[sx][sy] = 'S';
        gameBoard[ex][ey] = 'E';
        MazeUtilities.printMaze(gameBoard);
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(
                Paths.get("src/ad_search/astar_game", "2.in"));

        int m, n;
        m = scanner.nextInt();
        n = scanner.nextInt();
        hashCash = new int[m][n];
        pre = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(hashCash[i], Integer.MAX_VALUE);
            Arrays.fill(pre[i], -1);
        }

        char[][] gameBoard = new char[m][n];
        for (int i = 0; i < gameBoard.length; i++) {
            gameBoard[i] = scanner.next().trim().toCharArray();
        }

        int sx, sy, ex, ey;
        sx = scanner.nextInt();
        sy = scanner.nextInt();
        ex = scanner.nextInt();
        ey = scanner.nextInt();

        bsf_a(m, n, gameBoard, sx, sy, ex, ey);
    }


    static class BoardState implements Comparable<BoardState> {
        int x, y, g, f;

        BoardState(int x, int y, int g) {
            this.x = x;
            this.y = y;
            this.g = g;
        }

        void calcHeuristic(int ex, int ey) {
            int deltaX = Math.abs(ex - x);
            int deltaY = Math.abs(ey - y);
            if (deltaX > deltaY) {
                f = g + 14 * deltaY + 10 * (deltaX - deltaY);
            } else {
                f = g + 14 * deltaX + 10 * (deltaY - deltaX);
            }
        }

        @Override
        public int compareTo(BoardState o) {
            return f - o.f;
        }
    }

}
