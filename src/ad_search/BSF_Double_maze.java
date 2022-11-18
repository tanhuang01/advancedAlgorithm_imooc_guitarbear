package ad_search;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class BSF_Double_maze {
    // (x[i], y[i]) means east, south, west, north
    final static int[] dx = {0, 1, 0, -1};
    final static int[] dy = {1, 0, -1, 0};

    static Queue<Node> queue1 = new LinkedList<>();
    static Queue<Node> queue2 = new LinkedList<>();

    static int totalNodeNum = 4;

    static void bfs(char[][] maze, int n) {
        int[][][] minTurns1 = new int[n + 2][n + 2][4];
        int[][][] minTurns2 = new int[n + 2][n + 2][4];
        Node[][][] pre1 = new Node[n + 2][n + 2][4];
        Node[][][] pre2 = new Node[n + 2][n + 2][4];

        for (int i = 0; i < minTurns1.length; i++) {
            for (int j = 0; j < minTurns1[i].length; j++) {
                Arrays.fill(minTurns1[i][j], Integer.MAX_VALUE);
                Arrays.fill(minTurns2[i][j], Integer.MAX_VALUE);
            }
        }

        queue1.offer(new Node(1, 1, 0, -1));  // towards east
        queue1.offer(new Node(1, 1, 1, -1)); // towards south
        minTurns1[1][1][0] = -1;
        minTurns1[1][1][1] = -1;

        queue2.offer(new Node(n, n, 0, -1));  // from east
        queue2.offer(new Node(n, n, 1, -1)); // from south
        minTurns2[n][n][0] = -1;
        minTurns2[n][n][1] = -1;

        int level = -2;

        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            level++;
            while (!queue1.isEmpty() && queue1.peek().turns <= level) {
                Node node = queue1.poll();
                int tx = node.x;
                int ty = node.y;
                int dir = node.dir;
                int turns = node.turns;

                while (true) {
                    tx += dx[dir];
                    ty += dy[dir];
                    if (maze[tx][ty] == '*') {
                        break;
                    }
                    for (int i = 0; i < 2; i++) {
                        int id = (dir + 3 - i * 2) % 4;
                        if (maze[tx + dx[id]][ty + dy[id]] == '.' &&
                                minTurns1[tx][ty][id] > turns + 1) {
                            queue1.offer(new Node(tx, ty, id, turns + 1));
                            totalNodeNum++;
                            minTurns1[tx][ty][id] = turns + 1;
                            pre1[tx][ty][id] = node;

                            for (int j = 0; j < 2; j++) {
                                if (minTurns2[tx][ty][(id + 3 - j * 2) % 4] < Integer.MAX_VALUE) {
                                    printResult(n, tx, ty, id, (id + 3 - j * 2) % 4, pre1, pre2);
                                    return;
                                }
                            }
                        }
                    }
                }
            }

            while (!queue2.isEmpty() && queue2.peek().turns <= level) {
                Node node = queue2.poll();
                int tx = node.x;
                int ty = node.y;
                int dir = node.dir;
                int turns = node.turns;

                while (true) {
                    tx -= dx[dir];
                    ty -= dy[dir];
                    if (maze[tx][ty] == '*') {
                        break;
                    }
                    for (int i = 0; i < 2; i++) {
                        int id = (dir + 3 - i * 2) % 4;
                        if (maze[tx - dx[id]][ty - dy[id]] == '.' &&
                                minTurns2[tx][ty][id] > turns + 1) {
                            queue2.offer(new Node(tx, ty, id, turns + 1));
                            totalNodeNum++;
                            minTurns2[tx][ty][id] = turns + 1;
                            pre2[tx][ty][id] = node;

                            for (int j = 0; j < 2; j++) {
                                if (minTurns1[tx][ty][(id + 3 - j * 2) % 4] < Integer.MAX_VALUE) {
                                    printResult(n, tx, ty, (id + 3 - j * 2) % 4, id, pre1, pre2);
                                    return;
                                }
                            }

                        }
                    }
                }
            }

        }

    }

    private static void printResult(int n, int x, int y, int dir, int dir2,
                                    Node[][][] pre1, Node[][][] pre2) {

        List<String> res = new ArrayList<>();
        int x2 = x;
        int y2 = y;
        res.add(String.format("[%d,%d] --> ", x, y));
        while (x != 1 || y != 1) {
            Node node = pre1[x][y][dir];
            int tx = node.x;
            int ty = node.y;
            dir = node.dir;
            res.add(String.format("(%d,%d) --> ", tx, ty));
            x = tx;
            y = ty;
        }


        while (true) {
            Node node = pre2[x2][y2][dir2];
            int tx = node.x;
            int ty = node.y;
            dir2 = node.dir;
            if (tx == n && ty == n) {
                res.add(0, String.format("(%d,%d)\n", n, n));
                break;
            }
            res.add(0, String.format("(%d,%d) --> ", tx, ty));
            x2 = tx;
            y2 = ty;
        }

        for (int i = res.size() - 1; i >= 0; i--) {
            System.out.print(res.get(i));
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(
                Paths.get("src/ad_search/maze", "2.in"));
        int n = scanner.nextInt();

        char[][] maze = new char[n + 2][n + 2];
        for (int i = 0; i < n + 2; i++) {
            maze[i] = scanner.next().trim().toCharArray();
        }
        scanner.close();

//        printMaze(maze);

        bfs(maze, n);

        System.out.println("totalNode: " + totalNodeNum);

    }

}
















