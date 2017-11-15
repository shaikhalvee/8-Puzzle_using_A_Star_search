/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nPuzzle;
import java.util.*;
/**
 *
 * @author SHAIKHALVEE
 */
public class NPuzzle {
    public Board startBoard, goalBoard;
    private Node startNode, goalNode;
    private int N, totalEnqued;
    PriorityQueue<Node> openSet;
    ArrayList<Node> closeSet;

    NPuzzle(int N, int[] input, int[] goal) {
        totalEnqued = 0;
        this.N = N;
        int[][] temp1 = new int[N][N];
        int[][] temp2 = new int[N][N];
        for (int i = 0, k = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp1[i][j] = input[k++];
            }
        }
        for (int i = 0, k = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp2[i][j] = goal[k++];
            }
        }
        startBoard = new Board(N, temp1);
        goalBoard = new Board(N, temp2);
        startNode = new Node(startBoard, null, goalBoard, 0);
        goalNode = new Node(goalBoard);
        openSet = new PriorityQueue<>( (Node a, Node b) -> a.getF() - b.getF() );
        closeSet = new ArrayList<>();
    }

    public void solve() {
        startNode.setChildNodes();
        startNode.updateM(0);
        openSet.add(startNode);
        //System.out.println("startNode added");
        //System.out.println(startNode.toString() + goalNode.toString());
        while (!openSet.isEmpty()) {
            Node currentNode = openSet.remove();
            //currentNode.setChildNodes();
            if(currentNode.board.isSameTiles(goalBoard)) {
                System.out.println("Nodes explored: " + openSet.size() + "\nMoves: " + currentNode.getG());
                System.out.println("States: ");
                currentNode.printPath();
                return;
            }
            currentNode.setChildNodes();
            closeSet.add(currentNode);
            //System.out.println("currentNode: \n"+currentNode.toString());
            for (Node neighbour : currentNode.getChildNodes()) {
                if(closeSet.contains(neighbour)) {
                    continue;
                }
                int tempG = currentNode.getG() + 1;
                if ( !openSet.contains(neighbour) || tempG < neighbour.getG()) {
                    neighbour.updateM(tempG);
                    if (!openSet.contains(neighbour))
                        openSet.add(neighbour);
                }
            }
        }
        totalEnqued = closeSet.size();
        System.out.println("Value Printed \n");
        return;
    }
    
}
