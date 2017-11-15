/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nPuzzle;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author SHAIKHALVEE
 */
public class Node {
    public Board board, goalBoard;
    public Node parentNode;
    private int f,g,h,m;
    private ArrayList<Board> childBoards;
    private ArrayList<Node> childNodes;

    public Node() {
        this.board = this.goalBoard = null;
        this.parentNode = null;
        m = f = g = h = 0;
        childBoards = null;
    }
    public Node(Board board) {
        this();
        this.board = board;
    }
    // board, parent and moves inputed. child need to calculate,
    // f = move or g + manhatton or h
    public Node(Board board, Node parentNode, Board goalBoard, int moves) {
        this.board = board;
        this.parentNode = parentNode;
        this.goalBoard = goalBoard;
        this.g = moves;
        this.h = this.board.manhatton();
        this.f = 0;
        this.m = this.board.myHeuristic();
        childBoards = new ArrayList<>();
        childNodes = new ArrayList<>();
        //setChildNodes();
    }
    
    // neighbours calculation
    public void getChilds() {
        int[][] tempTile = new int[board.N][board.N];
        for (int i = -1; i < board.N - 1; i++) {
            int x = board.zeroX + i;
            //System.out.println("x: "+x);
            if (x < 0 || x > board.N - 1)
                continue;
            for (int j = -1; j < board.N - 1; j++) {
                int y = board.zeroY + j;
                //System.out.println("x: "+x+" y: "+y);
                if (y < 0 || y > board.N - 1 || (x == board.zeroX && y == board.zeroY) || ((Math.abs(board.zeroX - x) + Math.abs(board.zeroY - y))) > 1)
                    continue;
                for (int k = 0; k < board.N; k++) {
                    System.arraycopy(board.tiles[k], 0, tempTile[k], 0, board.N);
                }
                int temp = tempTile[x][y];
                tempTile[x][y] = 0;
                tempTile[board.zeroX][board.zeroY] = temp;
                Board child = new Board(board.N, tempTile);
                // see to it that, it works 
                /*
                if ( parentNode != null){
                    if (child.isSameTiles(parentNode.board)){
                        //System.out.println("parent found");
                        continue;
                    }
                }
                */
                if (parentNode == null || !child.isSameTiles(parentNode.board)) {
                    childBoards.add(child);
                }
                //System.out.println(child.toString()+"\n");
            }
        }
    }
    public void setChildNodes() {
        getChilds();
        //System.out.println("getchild called, childboard size:" + childBoards.size());
        childBoards.stream().forEach((current) -> {
            childNodes.add(new Node(current, this, this.goalBoard, this.g+1));
        });
        /*
        childNodes.stream().forEach((current) -> {
            System.out.println(current.toString() + childNodes.size());
        });
        */
    }
    
    public void updateM(int g) {
        this.g = g;
        this.f = g + m;
    }
    
    public void updateG(int g) {
        this.g = g;
        this.f = g + h;
    }

    public int getM() {
        return m;
    }

    public int getG() {
        return g;
    }

    public Board getBoard() {
        return board;
    }

    public int getH() {
        return h;
    }

    public ArrayList<Node> getChildNodes() {
        return childNodes;
    }

    public int getF() {
        return f;
    }
    
    public boolean isSame(Node given) {
        return this.board.isSameTiles(given.board);
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    @Override
    public String toString() {
        String s = "";
        s += this.board.toString() + " f: " + this.f + " g: " + this.g + " h: " + this.h + " m: " +this.m +"\n";
        return s;
    }
    
    public void printPath() {
        if (parentNode != null) {
            parentNode.printPath();
        }
        System.out.println(toString());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.board);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        final Node other = (Node) obj;
        if (this.isSame(other)) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return false;
    }
    
    
}
