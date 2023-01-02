package ad_dp.caterpillar;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Caterpillar {
    static int res = 0;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(
                Paths.get("src/ad_dp/caterpillar", "1.in"));
//        Scanner scanner = new Scanner(System.in);
//        scanner.close();

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // replace the HashMap by an array.
        ArrayList<Integer>[] graph = new ArrayList[n + 1];

        int v, w;
        for (int i = 0; i < m; i++) {
            v = scanner.nextInt();
            w = scanner.nextInt();
            if (graph[v] == null) {
                graph[v] = new ArrayList<>();
            }
            if (graph[w] ==null) {
                graph[w] = new ArrayList<>();
            }
            graph[v].add(w);
            graph[w].add(v);
        }
        scanner.close();

//        showGraph(graph);

        // f[i] means the number of a caterpillar base on i.
        int[] f = new int[n + 1];
        dfs(1, f, graph);
        System.out.println(res);
    }

    private static void dfs(int v, int[] f, ArrayList<Integer>[] graph) {
        int l1 = 0, l2 = 0, children = 0;
        f[v] = 1;
        for (int i = 0; i < graph[v].size(); i++) {
            int w = graph[v].get(i);
            if (f[w] != 0) {
                continue;
            }
            children++;
            dfs(w, f, graph);
            if (l1 <= f[w]) {
                l2 = l1;
                l1 = f[w];
            } else {
                l2 = Math.max(l2, f[w]);
            }
            f[v] = l1 + 1 + Math.max(0, children - 1);
            res = Math.max(res, l1 + l2 + 1 + Math.max(0, graph[v].size() - 2));
        }

    }

}
