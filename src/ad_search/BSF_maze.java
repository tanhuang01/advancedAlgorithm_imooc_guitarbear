package ad_search;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class BSF_maze {

    // (x[i], y[i]) means east, south, west, north
    final static int[] dx = {0, 1, 0, -1};
    final static int[] dy = {1, 0, -1, 0};

    static Queue<Node> queue = new LinkedList<>();
    static int totalNodeNum = 2;

    private static void bfs(char[][] maze, int n) {
        int[][][] minTurns = new int[n + 2][n + 2][4];
        Node[][][] pre = new Node[n + 2][n + 2][4];
        for (int[][] minTurn : minTurns)
            for (int[] iterm : minTurn)
                Arrays.fill(iterm, Integer.MAX_VALUE);

        queue.offer(Node.east());
        queue.offer(Node.south());

        minTurns[1][1][0] = -1;
        minTurns[1][1][1] = -1;


        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;
            int dir = node.dir;
            int turns = node.turns;

            int tx = x, ty = y;
            while (true) {
                tx += dx[dir];
                ty += dy[dir];
                if (maze[tx][ty] == '*') {
                    break;
                }
                if (tx == n && ty == n) {
//                    printPre(pre);
//                    printMin(minTurns);
                    printPath(n, x, y, dir, pre);
                    return;
                }
                for (int i = 0; i < 2; i++) {
                    // left or right of cur direction
                    int id = (dir + 3 - i * 2) % 4;
                    if (maze[tx + dx[id]][ty + dy[id]] == '.' &&
                            minTurns[tx][ty][id] > turns + 1) {
                        queue.offer(new Node(tx, ty, id, turns + 1));
                        totalNodeNum++;
                        minTurns[tx][ty][id] = turns + 1;
                        pre[tx][ty][id] = node;
                    }
                }
            }

        }
    }


    private static void printPath(int n, int x, int y, int dir, Node[][][] pre) {
        List<String> res = new ArrayList<>();
        res.add(String.format("(%d,%d) --> ", x, y));
        while (x != 1 || y != 1) {
            int tx = pre[x][y][dir].x;
            int ty = pre[x][y][dir].y;
            res.add(String.format("(%d,%d) --> ", tx, ty));
            dir = pre[x][y][dir].dir;
            x = tx;
            y = ty;
        }

        for (int i = res.size() - 1; i >= 0; i--) {
            System.out.print(res.get(i));
        }
        System.out.printf("(%d,%d)\n", n, n);
    }


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(
                Paths.get("src/ad_search/maze", "1.in"));
        int n = scanner.nextInt();

        char[][] maze = new char[n + 2][n + 2];
        for (int i = 0; i < n + 2; i++) {
            maze[i] = scanner.next().trim().toCharArray();
        }
        scanner.close();

        bfs(maze, n);

        System.out.println("totalNode: " + totalNodeNum);

    }



}
























