package chp_11;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    /**
     * @param parent
     * @param x
     * @param y
     * @return false: x and y has already connected; true: connect x and y
     */
    static boolean union(int[] parent, int x, int y) {
        int r1 = find(parent, x);
        int r2 = find(parent, y);
        if (r1 == r2) {
            return false;   // x and y have been connected
        }
        if (parent[r1] < parent[r2]) { // r1 is bigger than r2
            parent[r1] += parent[r2];
            parent[r2] = r1;
        } else { // r2 is bigger than or equal r1.
            parent[r2] += parent[r1];
            parent[r1] = r2;
        } //
        count--;
        return true;
    }

    static int find(int[] parent, int x) {
        int r = x, t = x;
        while (x >= 0) {
            r = x;
            x = parent[x];
        }
        // compress the path
        x = t;
        while (x != r) {
            t = parent[x];
            parent[x] = r;
            x = t;
        }
        return r;
    }

    static void clear(int[] parent, int k) {
        for (int i = 0; i < parent.length; i++) {
            parent[i] = k;
        }
    }

    static int count;

    static class Edge {
        int distance;

        public Edge(int distance) {
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1;
        String[] ss;
        while ((s1 = br.readLine()) != null) {
            if (s1.equals("") || s1.equals("0")) {
                break;
            }

            int n = Integer.parseInt(s1);
            char[][] unitPic = new char[n][n];
            for (int i = 0; i < n; i++) {
                s1 = br.readLine();
                unitPic[i] = s1.toCharArray();
            }
            s1 = br.readLine();
            int q = Integer.parseInt(s1);

        }


        br.close();
    }


    static int maxSubMatrix(int[][] total, int[] arr, int[] dp) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < total.length; i++) {
            for (int j = i; j < total.length; j++) {
                for (int k = 0; k < total[j].length; k++) {
                    if (i == 0) {
                        arr[k] = total[j][k];
                    } else {
                        arr[k] = total[j][k] - total[i - 1][k];
                    }
                }
                int current = maxSubSequence(arr, dp);
                max = Math.max(max, current);
            }
        }
        return max;
    }

    private static int maxSubSequence(int[] arr, int[] dp) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                dp[i] = arr[i];
            } else {
                dp[i] = Math.max(arr[i], dp[i - 1] + arr[i]);
            }
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    static int criticalPath2(int[][] matrix, int[] inDegree, int[] outDegree,
                             int[] earliest, int[] last, List<Integer> topology, int[] time) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        int totalTime = 0;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            topology.add(u);
            for (int v = 0; v < matrix[u].length; v++) {
                if (matrix[u][v] != 0) {
                    earliest[v] = Math.max(earliest[v], earliest[u] + time[u]);
                    inDegree[v]--;
                    if (inDegree[v] == 0) {
                        queue.offer(v);
                        totalTime = Math.max(totalTime, earliest[v] + time[v]);
                    }
                }
            }
        }

        for (int i = topology.size() - 1; i >= 0; i--) {
            int u = topology.get(i);
            if (outDegree[u] == 0) {
                last[u] = totalTime - time[u];
            } else {
                last[u] = Integer.MAX_VALUE;
            }
            for (int v = 0; v < matrix[u].length; v++) {
                if (matrix[u][v] != 0) {
                    last[u] = Math.min(last[u], last[v] - time[u]);
                }
            }
        }
        return totalTime;
    }

    static boolean criticalPath(Edge[][] matrix, int[] inDegree, int[] outDegree,
                                int[] earliest, int[] last, List<Integer> topology) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
                earliest[i] = 1;
            }
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            topology.add(u);
            for (int v = 0; v < matrix[u].length; v++) {
                if (matrix[u][v] != null) {
                    int time = matrix[u][v].distance;
                    earliest[v] = Math.max(earliest[v], earliest[u] + time);
                    inDegree[v]--;
                    if (inDegree[v] == 0) {
                        queue.offer(v);
                    }
                }
            }
        }

        for (int i = topology.size() - 1; i >= 0; i--) {
            int u = topology.get(i);
            if (outDegree[u] == 0) {
                last[u] = earliest[u];
            } else {
                last[u] = Integer.MAX_VALUE;
            }
            for (int v = 0; v < matrix[u].length; v++) {
                if (matrix[u][v] != null) {
                    int l = matrix[u][v].distance;
                    last[u] = Math.min(last[u], last[v] - l);
                }
            }
        }
        return true;
    }

    static boolean topologicalSort(int[][] matrix, int[] inDegree, List<Integer> list) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.offer(inDegree[i]);
            }
        }
        int sortedNum = 0;  // count the vertex that have been in topological sort
        while (!queue.isEmpty()) {
            int u = queue.poll();
            sortedNum++;
            list.add(u);
            for (int v = 0; v < matrix[u].length; v++) {
                if (matrix[u][v] != 0) {
                    inDegree[v]--;
                    if (inDegree[v] == 0) {
                        queue.offer(v);
                    }
                }
            }
        }
        return sortedNum == matrix.length;
    }


}
