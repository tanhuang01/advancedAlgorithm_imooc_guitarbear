package ad_dp.barn;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class BarnPainting {
    private static final int MOD = 1000000007;

    static class Node{

        public Node(int id, Node next) {
            this.id = id;
            this.next = next;
        }

        int id;
        Node next;
    }

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(
                Paths.get("src/ad_dp/barn", "3.in"));
//        Scanner scanner = new Scanner(System.in);

        int n, k;
        n = scanner.nextInt();
        k = scanner.nextInt();

        Node[] graph = new Node[n + 1];
        int v, w;
        for (int i = 1; i < n; i++) {
            v = scanner.nextInt();
            w = scanner.nextInt();
            graph[v] = new Node(w, graph[v]);
            graph[w] = new Node(v, graph[w]);
        }

        int[] setColor = new int[n + 1];
        boolean[] marked = new boolean[n + 1];
        Arrays.fill(setColor, -1);
        for (int i = 0; i < k; i++) {
            v = scanner.nextInt();  // node number
            w = scanner.nextInt(); // color of the node
            setColor[v] = w - 1;
        }
        scanner.close();

        int[][] f = new int[n + 1][3];

        dfs(1, f, setColor, marked, graph);
        System.out.println((f[1][0] + f[1][1] + f[1][2]) % MOD);



    }

    private static void dfs(int v, int[][] f, int[] setColor, boolean[] marked,
                            Node[] graph) {
        marked[v] = true;
        if (setColor[v] > -1) {
            f[v][setColor[v]] = 1;
        } else {
            f[v][0] = f[v][1] = f[v][2] = 1;
        }

        Node p = graph[v];
        while (p != null){
            int w = p.id;
            if (marked[w]) {
                p = p.next;
                continue;
            }
            dfs(w, f, setColor, marked, graph);

            for (int colori = 0; colori < 3; colori++) {
                if (setColor[v] == -1 || setColor[v] == colori) {
                    long comb = 0;
                    for (int colorj = 0; colorj < 3; colorj++) {
                        if (colorj != colori) {
                            comb += f[w][colorj];
                        }
                    }
                    f[v][colori] = (int) ((f[v][colori] * comb) % MOD);
                }
            }
            p = p.next;
        }
    }

    private static void show(Node[] graph) {
        for (int i = 1; i < graph.length; i++) {
            Node p = graph[i];
            System.out.print(i + ":");
            while (p != null) {
                System.out.print(p.id + " ");
                p = p.next;
            }
            System.out.println();
        }
    }
}













