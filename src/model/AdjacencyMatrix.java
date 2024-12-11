package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdjacencyMatrix {
    private Integer[][] matrix;
    ArrayList<String>  map = new ArrayList<>();

    public AdjacencyMatrix() {
        readMatrix("C:\\Users\\Startklar\\Desktop\\Matrix.csv");
    }

    public Integer[][] getMatrix() {
        return this.matrix;
    }

    /*
     * Liest die csv Datei mittels buffered reader Zeile fuer Zeile
     * und kopiert den Inhalt in einem Array aus Strings.
     */
    public void readMatrix(String filename){
        List<String[]> rowList = new ArrayList<String[]>();

        if(filename != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line = br.readLine();
                int row = 0;
                while (line != null) {
                    String[] lineItems = line.split(";");
                    rowList.add(lineItems);
                    line = br.readLine();
                    row++;
                }
                br.close();

                matrix = new Integer[row][row];

                for(int i = 0; i < rowList.size(); i++) {
                    String[] rowItems = rowList.get(i);
                    for(int j = 0; j < rowList.size(); j++) {
                        matrix[i][j] = Integer.parseInt(rowItems[j]);
                    }
                }
            }
            catch(FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Fehler bei readMatrix(): Null-Referenz erhalten");
        }
    }

    @Override
    public String toString() {
        System.out.println("Adjazentmatrix:");
        String str = "";

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            map.add(String.valueOf(ch));
        }
        //Ausgabe mit Index
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                str += this.matrix[i][j]+" | ";
            }
            str += "   Knoten: "+ map.get(i) +"\n";
        }

        return str;
    }
}
