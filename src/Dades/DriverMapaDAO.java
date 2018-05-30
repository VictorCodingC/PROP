package Dades;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DriverMapaDAO {
    private static Scanner myScanner;
    private static MapaDAO mdao = new MapaDAO();
    public static void main(String[] args) throws IOException {
        String introduction = "Introduce qué operación desea ejecutar:\n"+
                "\t1) LoadMapa(ID)\n"+
                "\t2) LoadAllMaps()\n"+
                "\tx) Para salir del juego\n";
        System.out.println(introduction);
        String op = "";
        myScanner = new Scanner(System.in);
        op = myScanner.next();

        boolean active = true;
        while(active) {
            switch (op){
                case "1":
                    loadMapa();
                    break;
                case "2":
                    mdao.loadAllMapas();
                    break;
                case "x":
                    System.out.println("exiting game...");
                    active = false;
                    break;
                default:
                    System.out.println("this operation does not exist");
                    break;
            }
            if (active) {
                System.out.println("---------------------------------------\n\n"
                        +introduction);
                op = myScanner.next();
            }
        }
    }

    private static void loadMapa() {
        String mapaID = myScanner.next();
        StringBuilder topologia = new StringBuilder();
        StringBuilder adyacencia = new StringBuilder();
        ArrayList<ArrayList<String>> matrix = new ArrayList<ArrayList<String>>();
        try {
            mdao.loadMapa(mapaID, topologia, adyacencia, matrix);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[][] matrixResult = new String[matrix.size()][matrix.get(0).size()];
        for(int i=0; i<matrixResult.length; ++i){
            matrixResult[i] = matrix.get(i).toArray(matrixResult[i]);
        }
        System.out.println("mapaID: "+ mapaID);
        System.out.println(topologia+","+adyacencia);
        printTablero(matrixResult);
    }

    public static void printTablero(String[][] matrix){
        int filas = matrix.length;
        int columnas = matrix[0].length;
        for(int i=0; i<filas; ++i){
            for(int j=0; j<columnas; ++j) {
                System.out.print(matrix[i][j]);
                if(j!=columnas-1) System.out.print(",");
            }
            System.out.print("\n");
        }
        System.out.println();
    }
}
