package ad_search;

class Node {
    int x;
    int y;
    int dir;    // direction 0-east, 1-south, 2-west, 3-north
    int turns;  // num of turns

    Node(int x, int y, int dir, int turns) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.turns = turns;
    }

    // use enum to optimize the direction
    static Node east() {
        return new Node(1, 1, 0, -1);
    }

    static Node south() {
        return new Node(1, 1, 1, -1);
    }
}


