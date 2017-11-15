/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nPuzzle;

/**
 *
 * @author SHAIKHALVEE
 */
public class Board {
    public int[][] tiles;  // n*n array of tiles in the Board.
    public int N;
    public int zeroX, zeroY;
    
    // initialize with 3*3 or 4*4 array
    public Board(int n) {
        this.N = n;
        tiles = new int[N][N];
        zeroX = zeroY = 5;     // 5 means it's still not set
    }
    // full initialization
    public Board(int n, int[][] ara) {
        this(n);
        for (int i = 0; i < N; i++) {
            System.arraycopy(ara[i], 0, tiles[i], 0, N);
        }
        setZeroPos();
    }
    // find position of zero
    private void setZeroPos() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0){
                    this.zeroX = i; 
                    this.zeroY = j;
                    return;
                }
            }
        }
        //System.out.println(zeroX + " "+ zeroY);
    }
    // fillup the board with ara[][].
    public void fillup(int[][] ara) {
        for (int i = 0; i < N; i++) {
            System.arraycopy(ara[i], 0, this.tiles[i], 0, N);
        }
    }
    // print the whole board.
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s += " " + tiles[i][j];
            }
            s += "\n";
        }
        return s;
    }
    // calculate the manhatton distance between the board and goal board.
    public int manhatton() {
        int manhattonDistance = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                manhattonDistance += positionDiff(i, j, (tiles[i][j] == 0 ? N - 1 : (int) (tiles[i][j] / (N + 0.3) ) ), (tiles[i][j] + N - 1) % N);
            }
        }
        return manhattonDistance;
    }
    // my heuristic neds to be implemented
    public int myHeuristic() {
        return 2*manhatton()-1;
    }
    // 2 boards have same tiles or not
    public boolean isSameTiles(Board given) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(tiles[i][j] != given.tiles[i][j])
                    return false;
            }
        }
        return true;
    }
    // difference between positions
    public int positionDiff(int x1, int y1, int x2, int y2) {
        int ans = Math.abs(x1 - x2);
        ans += Math.abs(y1 - y2);
        return ans;
    }
}
