/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nPuzzle;

import java.io.*;
import java.util.*;

/**
 *
 * @author SHAIKHALVEE
 */
public class MainFile {
    public static void main(String[] args) {
        Scanner sc = null;
        NPuzzle puzzleGame = null;
        int size, input[], goal[];
        try {
            sc = new Scanner(new File("input.txt"));
        } catch (Exception ex) {
        }
        while(sc.hasNext()){
            size = sc.nextInt() + 1; // size = 9 or 16
            int N = (int) Math.sqrt(size);
            input = new int[size];
            goal = new int[size];
            // input for start state and goal state.
            for (int i = 0; i < size; i++) {
                input[i] = sc.nextInt();
            }
            for (int i = 0; i < size; i++) {
                goal[i] = sc.nextInt();
            }
            puzzleGame = new NPuzzle(N, input, goal);
            puzzleGame.solve();
        }
    }
}