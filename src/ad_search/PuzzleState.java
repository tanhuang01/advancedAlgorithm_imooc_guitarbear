package ad_search;

class PuzzleState implements Comparable<PuzzleState> {
    int puzzle[];
    int depth;
    int f;
    static int n;

    PuzzleState(int n) {
        puzzle = new int[n];
    }

    PuzzleState(int[] puzzle) {
        this.puzzle = puzzle;
    }

    PuzzleState(int[] puzzle, int depth) {
        this.puzzle = puzzle;
        this.depth = depth;
    }

    private void calcHeuristic1(PuzzleState ps) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (puzzle[i] != ps.puzzle[i]) {
                res++;
            }
        }
        f = depth + res;
    }

    private void calcHeuristic2(PuzzleState ps) {
        int res = 0;
        int[] pos1 = new int[n];
        int[] pos2 = new int[n];
        for (int i = 0; i < n; i++) {
            pos1[puzzle[i]] = i;
            pos2[ps.puzzle[i]] = i;
        }
        for (int i = 0; i < n; i++) {
            int x1 = pos1[i] / 3;
            int y1 = pos1[i] % 3;
            int x2 = pos2[i] / 3;
            int y2 = pos2[i] % 3;
            res += (Math.abs(x1 - x2) + Math.abs(y1 - y2));
        }
        f = depth + res;

    }

    void calcHeuristic(PuzzleState ps) {
        calcHeuristic2(ps);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < puzzle.length; i++) {
            builder.append(puzzle[i]).append(" ");
            if (puzzle.length % 3 == 0 && i % 3 == 2) {
                builder.append('\n');
            }
            if (puzzle.length % 4 == 0 && i % 4 == 3) {
                builder.append('\n');
            }
        }
        return builder.toString();
    }

    @Override
    public int compareTo(PuzzleState ps) {
        return f - ps.f;
    }
}