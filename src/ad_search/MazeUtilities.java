package ad_search;

import java.util.Arrays;

public class MazeUtilities {

    static void printMin(int[][][] minTurns) {
        for (int i = 0; i < minTurns.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < minTurns[i].length; j++) {
                System.out.print(j + "[");
                for (int k = 0; k < minTurns[i][j].length; k++) {
                    if (minTurns[i][j][k] == Integer.MAX_VALUE) {
                        System.out.print("*,");
                    } else {
                        System.out.print(minTurns[i][j][k]);
                    }
                }
                System.out.print("]");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void printMaze(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    static void printPre(Node[][][] pre) {
        for (int i = 0; i < pre.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < pre[i].length; j++) {
                System.out.print(j);
                System.out.print(Arrays.toString(pre[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }
}
