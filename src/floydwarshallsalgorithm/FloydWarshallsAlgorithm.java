/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floydwarshallsalgorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Myron Williams
 */
public class FloydWarshallsAlgorithm {

    /**
     * @param args the command line arguments 
     * A,4,B,1,E,6,C 
     * B,-3,C,1,E,4,A,5,D
     * C,-3,B,3,E,6,A 
     * D,-5,E,5,B 
     * E,-5,D,1,A,1,B,3,C
     */
    static int[][] adjMatrix;
    static HashMap<String, Integer> nodeArray;
    static String filename = "testfile.txt";
    static String outputFile = "outFile.txt";
    static int n = 0;
    static int MAX = 9999;

    public static void main(String[] args) {
        // TODO code application logic here
        nodeArray = new HashMap<String, Integer>();

        createMatrix();
//        printMatrix();
        shotestPath();
        printMatrix();
    }

    public static void createMatrix() {
        try {
            FileReader file = new FileReader(filename);
            BufferedReader bufferReader = new BufferedReader(file);
            String line;
            String[] allVertices;
            // Read each line of the file
            while ((line = bufferReader.readLine()) != null) {
                if (!line.isEmpty()) {
                    allVertices = line.split(",");
                    nodeArray.put(allVertices[0], n);
                    n++;
                }
            }
//       A,4,B,1,E,6,C
//       B,-3,C,1,E,4,A,5,D
//       C,-3,B,3,E,6,A
//       D,-5,E,5,B
//       E,-5,D,1,A,1,B,3,C
            adjMatrix = new int[n][n];
            for (int[] row : adjMatrix) {
                Arrays.fill(row, MAX);
            }
            file = new FileReader(filename);
            bufferReader = new BufferedReader(file);

            int i = 0;
            while ((line = bufferReader.readLine()) != null) {

                if (!line.isEmpty()) {

                    allVertices = line.split(",");

                    for (int j = 0, k = 1; j < n; j++) {
                        if (i == j) {
                            adjMatrix[i][j] = 0;
                        }

                        if (i != j && k <= allVertices.length - 1) {
                            int place = nodeArray.get(allVertices[k + 1]);
                            adjMatrix[i][place] = Integer.parseInt(allVertices[k]);
                            k = k + 2;
                        }
                    }
                    i++;
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FloydWarshallsAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FloydWarshallsAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void shotestPath() {
        //int i, j, k;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    
                   
                      if(i!=j&&(adjMatrix[i][k] + adjMatrix[k][j] < adjMatrix[i][j])){
                          adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];    
                      }
                   // if (adjMatrix[i][k] != max && adjMatrix[k][j] != max)
                        //adjMatrix[i][j] = min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
//                    System.out.println("TK: " + k + " SI: " + i + " DJ: " + j);
//                    System.out.println("MIN: " + adjMatrix[i][j]+" = "+adjMatrix[i][k]+" + "+adjMatrix[k][j]+"="+(adjMatrix[i][k]+adjMatrix[k][j]));
//                    printMatrix();
                }
            }
        }
    }


    public static void printMatrix() {
        // Create a file and write the content
        PrintWriter writer;
        try {
            writer = new PrintWriter(outputFile, "UTF-8");
             for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjMatrix[i][j] >= MAX) {
                    writer.print("INF\t");
                 } else {
                    writer.print(adjMatrix[i][j] + "\t");
                } 
             } 
            writer.println("");
        }
        writer.println("");
          
            writer.close();
        } catch (FileNotFoundException ex) {

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FloydWarshallsAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjMatrix[i][j] >= MAX) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(adjMatrix[i][j] + "\t");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }

}
