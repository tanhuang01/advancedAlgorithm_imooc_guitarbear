package ad_search;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

import static ad_search.PuzzleUtilities.getPermutationHash;


public class _8Puzzle_Astar {


    private static void printResult(int depth, int stateCount, long startTime) {
        System.out.printf("Total step: %d\n", depth);
        System.out.printf("Total state: %d\n", stateCount);
        System.out.printf("Total time: %d ms\n", System.currentTimeMillis() - startTime);
    }

    private static void bsf(PuzzleState sp, PuzzleState ep) {

        int stateCount = 0;
        long startTime = System.currentTimeMillis();

        Queue<PuzzleState> queue = new PriorityQueue<>();
        queue.offer(sp);
        int[] hashState = new int[362881];
        Arrays.fill(hashState, Integer.MAX_VALUE);
        hashState[getPermutationHash(sp.puzzle)] = 0;

        while (!queue.isEmpty()) {
            PuzzleState state = queue.poll();
            stateCount++;
            // p is the space index in the puzzle
            int p = 0;
            while (state.puzzle[p] != 0)
                p++;
            for (int i = 0; i < 4; i++) {
                int[] newPuzzle = Arrays.copyOf(state.puzzle, state.puzzle.length);
                int rp = -1;
                if (i == 0 && p < 6) { // move space down
                    rp = p + 3;
                }
                if (i == 1 && p > 2) { // move space up
                    rp = p - 3;
                }
                if (i == 2 && p % 3 != 2) { // move space right
                    rp = p + 1;
                }
                if (i == 3 && p % 3 != 0) { // move space left
                    rp = p - 1;
                }
                if (rp == -1) {
                    continue;
                }
                newPuzzle[p] = newPuzzle[rp];
                newPuzzle[rp] = 0;

                int permHash = getPermutationHash(newPuzzle);
                if (hashState[permHash] < Integer.MAX_VALUE) {
                    continue;
                }
                hashState[permHash] = state.depth + 1;
                if (Arrays.equals(newPuzzle, ep.puzzle)) {
                    printResult(state.depth + 1,
                            stateCount + queue.size(), startTime);
                    return;
                }

                PuzzleState ps = new PuzzleState(newPuzzle, state.depth + 1);
                ps.calcHeuristic(ep);
                queue.offer(ps);
            }

        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(
                Paths.get("src/ad_search/astar", "1.in"));

        int n = 9;
        PuzzleState.n = n;
        PuzzleState sp = new PuzzleState(n);
        PuzzleState ep = new PuzzleState(n);

        for (int i = 0; i < n; i++) {
            sp.puzzle[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            ep.puzzle[i] = scanner.nextInt();
        }
        scanner.close();

        sp.calcHeuristic(ep);
        bsf(sp, ep);

    }
}












