import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class AdvancedSearchForHash {

    private static Integer[] a, tmp;
    private static final int base = 7;
    private static int s;   // the sum of all scores
    private static int winCount, evenCount;
    private static HashMap<Long, Long> map = new HashMap<>();

    private static Integer[] h;
    private static long ans = 0;


    private static void dfs(int x, int y, int n) {

        if (x >= n - 1) {   // x will not greater than n, only equal to n
            if (Arrays.equals(a, tmp)) {
                ans++;
            }
            return;
        }

        if (y >= n) {
            // since we number the team form 0, y will not greater than n
            // only equal to n
            dfs(x + 1, x + 2, n);
            return;
        }

        if (tmp[x] + 3 <= a[x] && winCount > 0) {
            tmp[x] += 3;
            winCount--;
            dfs(x, y + 1, n);
            winCount++;
            tmp[x] -= 3;
        }

        if (tmp[y] + 3 <= a[y] && winCount > 0) {
            tmp[y] += 3;
            winCount--;
            dfs(x, y + 1, n);
            winCount++;
            tmp[y] -= 3;
        }

        if (tmp[x] + 1 <= a[x] && tmp[y] + 1 <= a[y] && evenCount > 0) {
            tmp[x] += 1;
            tmp[y] += 1;
            evenCount--;
            dfs(x, y + 1, n);
            evenCount++;
            tmp[y] -= 1;
            tmp[x] -= 1;
        }
    }

    private static long dfs2(int x, int y, int n) {
        long cur = 0L;
        if (x >= n - 1) {   // x will not greater than n, only equal to n
            return Arrays.equals(a, tmp) ? 1 : 0;
        }

        if (y >= n) {
            // since we number the team form 0, y will not greater than n
            // only equal to n
            cur += dfs2(x + 1, x + 2, n);
            return cur;
        }

        if (tmp[x] + 3 <= a[x] && winCount > 0) {
            tmp[x] += 3;
            winCount--;
            cur += dfs2(x, y + 1, n);
            winCount++;
            tmp[x] -= 3;
        }

        if (tmp[y] + 3 <= a[y] && winCount > 0) {
            tmp[y] += 3;
            winCount--;
            cur += dfs2(x, y + 1, n);
            winCount++;
            tmp[y] -= 3;
        }

        if (tmp[x] + 1 <= a[x] && tmp[y] + 1 <= a[y] && evenCount > 0) {
            tmp[x] += 1;
            tmp[y] += 1;
            evenCount--;
            cur += dfs2(x, y + 1, n);
            evenCount++;
            tmp[y] -= 1;
            tmp[x] -= 1;
        }

        return cur;
    }

    private static long dfs3(int x, int y, int n) {
        long cur = 0L;
        if (x >= n - 1) {   // x will not greater than n, only equal to n
            return Arrays.equals(a, tmp) ? 1 : 0;
        }

        if (y >= n) {

            long correct = dfs3(x + 1, x + 2, n);


            // since we number the team form 0, y will not greater than n
            // only equal to n
            for (int i = x + 1; i < n; i++) {
                h[i] = a[i] - tmp[i];
            }
            Arrays.sort(h, x + 1, n);

            long hash = base;
            for (int i = x + 1; i < n; i++) {
                hash = hash * base + h[i];
            }

            if (map.get(hash) == null) {
                map.put(hash, correct);
                cur += correct;
            } else {
                cur += map.get(hash);
                if (map.get(hash) != correct) {
                    System.out.printf("\ncorrect: %d  fromHash: %s", correct, map.get(hash));
                }
            }


            return cur;
        }

        if (tmp[x] + 3 <= a[x] && winCount > 0) {
            tmp[x] += 3;
            winCount--;
            cur += dfs3(x, y + 1, n);
            winCount++;
            tmp[x] -= 3;
        }

        if (tmp[y] + 3 <= a[y] && winCount > 0) {
            tmp[y] += 3;
            winCount--;
            cur += dfs3(x, y + 1, n);
            winCount++;
            tmp[y] -= 3;
        }

        if (tmp[x] + 1 <= a[x] && tmp[y] + 1 <= a[y] && evenCount > 0) {
            tmp[x] += 1;
            tmp[y] += 1;
            evenCount--;
            cur += dfs3(x, y + 1, n);
            evenCount++;
            tmp[y] -= 1;
            tmp[x] -= 1;
        }

        return cur;
    }


    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(Paths.get("src", "input"));

        // the number of team.
        // there are n(n-1)/2 matches
        int n = sc.nextInt();

        a = new Integer[n];
        tmp = new Integer[n];

        Arrays.fill(tmp, 0);

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            s += a[i];
        }
        winCount = s - n * n + n;
        evenCount = n * (n - 1) / 2 - winCount;

        Arrays.sort(a, (o1, o2) -> o2 - o1);
        System.out.println("a[]: " + Arrays.toString(a));
        System.out.printf("winCount: %s, evenCount: %s\n", winCount, evenCount);

        dfs(0, 1, n);
        System.out.println("dfs1: " + ans);

        System.out.println("dfs2: " + dfs2(0, 1, n));

        h = new Integer[n];
        Arrays.fill(h, 0);
        Arrays.fill(tmp, 0);
        System.out.println("\ndfs3: " + dfs3(0, 1, n));

    }


}














