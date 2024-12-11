package model;

import java.util.*;

public class Calculations {
    private int[] eccentricity = new int[24];

    //-------------------------- Matrices --------------------------
    public Integer[][] distanceMatrix(AdjacencyMatrix aMatrix) {
        if (aMatrix == null) {
            return null;
        } else {
            //Kopie von A-Matrix
            Integer[][] powerMatrix = copyMatrix(aMatrix.getMatrix());
            Integer[][] DMatrix = copyMatrix(aMatrix.getMatrix());

            for (int i = 0; i < DMatrix.length; i++) {
                for (int j = 0; j < DMatrix[i].length; j++) {
                    //jede 0 mit "unendlich" ersetzen
                    if (DMatrix[i][j] == 0) {
                        DMatrix[i][j] = -1;
                    }
                }
            }

            //Hauptdiagonale mit 0 befüllen
            for (int i = 0; i < DMatrix.length; i++) {
                DMatrix[i][i] = 0;
            }

            //Berechnung A^n
            for (int exponent = 2; exponent < aMatrix.getMatrix().length; exponent++) {
                powerMatrix = multiplicationMatrix(powerMatrix, aMatrix.getMatrix());

                //Berechnung D^n
                for (int i = 0; i < aMatrix.getMatrix().length; i++) {
                    for (int j = 0; j < aMatrix.getMatrix().length; j++) {
                        if (powerMatrix[i][j] != 0 && DMatrix[i][j] == -1) {
                            DMatrix[i][j] = exponent;
                        }
                    }
                }
            }
            return DMatrix;
        }
    }

    public void printDistanceMatrix(Integer[][] dMatrix) {
        if (dMatrix == null) {
            System.out.println("Keine Distanzmatrix angegeben");
        } else {
            System.out.println("Distanzmatrix:");

            /*
            //Ausgabe mit größeren Abständen
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    System.out.printf("%4d", matrix[i][j]);
                }
                System.out.println();
            }
             */

            for (int i = 0; i < dMatrix.length; i++) {
                for (int j = 0; j < dMatrix[i].length; j++) {
                    System.out.print(dMatrix[i][j] + " | ");
                }
                System.out.println();
            }
        }
    }

    public Integer[][] pathMatrix(Integer[][] aMatrix) {
        Integer[][] powerMatrix = copyMatrix(aMatrix);
        Integer[][] pMatrix = copyMatrix(powerMatrix);

        //Hauptdiagonale mit 1 befüllen
        for (int i = 0; i < pMatrix.length; i++) {
            pMatrix[i][i] = 1;
        }

        powerMatrix = multiplicationMatrix(aMatrix, aMatrix);

        //Berechnung Wegematrix
        for (int i = 0; i < aMatrix.length; i++) {
            powerMatrix = multiplicationMatrix(powerMatrix, aMatrix);
            for (int j = 0; j < aMatrix.length; j++) {
                for (int k = 0; k < aMatrix[0].length; k++) {
                    if (powerMatrix[j][k] != 0 && pMatrix[j][k] != 1) {
                        pMatrix[j][k] = 1;
                    }
                }
            }
        }
        return pMatrix;
    }

    public void printPathMatrix(Integer[][] pMatrix) {
        if (pMatrix == null) {
            System.out.println("Keine Wegmatrix angegeben");
        } else {
            System.out.println("Wegmatrix:");

            for (int i = 0; i < pMatrix.length; i++) {
                for (int j = 0; j < pMatrix[i].length; j++) {
                    System.out.print(pMatrix[i][j] + " | ");
                }
                System.out.println();
            }
        }
    }
    //------------------------- Properties -------------------------
    public void eccentricity(Integer[][] dMatrix) {
        if (dMatrix == null) {
            System.out.println("Keine Distanzmatrix angegeben");
        } else {
            if (checkInfinity(dMatrix)) {
                System.out.println("Exzentrizitäten: \n" + "Graph ist nicht zusammenhängend");
            } else {
                ArrayList<String> map = new ArrayList<>();

                //Buchstaben als Key setzen
                for (char ch = 'A'; ch <= 'Z'; ch++) {
                    map.add(String.valueOf(ch));
                }

                for (int i = 0; i < dMatrix.length; i++) {
                    //erstes Element in der Zeile als Maximum in der ersten Iteration festlegen
                    int maxNum = dMatrix[i][0];
                    for (int j = 0; j < dMatrix[i].length; j++) {
                        if (maxNum < dMatrix[i][j]) {
                            maxNum = dMatrix[i][j];
                        }
                        eccentricity[i] = maxNum;
                    }
                }

                System.out.println("Exzentrizitäten:");
                for (int i = 0; i < dMatrix.length; i++) {
                    System.out.println("ex(" + map.get(i) + ") = " + eccentricity[i]);
                }
            }
        }
    }

    public void radius(Integer[][] dMatrix) {
        if (dMatrix == null) {
            System.out.println("Keine Distanzmatrix angegeben");
        } else {
            if (checkInfinity(dMatrix)) {
                System.out.println("Radius: \n" + "Graph ist nicht zusammenhängend");
            } else {
                Arrays.sort(eccentricity);
                System.out.println("Radius: \n" + "rad(G) = " + eccentricity[0]);
            }

        }
    }

    public void diameter(Integer[][] dMatrix) {
        if (dMatrix == null) {
            System.out.println("Keine Distanzmatrix angegeben");
        } else {
            if (checkInfinity(dMatrix)) {
                System.out.println("Durchmesser: \n" + "Graph ist nicht zusammenhängend");
            } else {
                Arrays.sort(eccentricity);
                System.out.println("Durchmesser: \n" + "dm(G) = " + eccentricity[eccentricity.length - 1]);
            }
        }
    }

    public void center(Integer[][] dMatrix) {
        if (dMatrix == null) {
            System.out.println("Keine Distanzmatrix angegeben");
        } else {
            if (checkInfinity(dMatrix)) {
                System.out.println("Zentrum: \n" + "Graph ist nicht zusammenhängend");
            } else {
                Arrays.sort(eccentricity);
                int center = 0;

                for (int i = 0; i < dMatrix.length; i++) {
                    if (eccentricity[i] == eccentricity[0]) {
                        center++;
                    }
                }
                System.out.println("Zentrum: \n" + "Z(G) = " + center);
            }
        }
    }
    //------------------------- Connections ------------------------
    public Integer components(Integer[][] pMatrix) {
        String sum = "";
        ArrayList<String> rowList = new ArrayList<>();
        ArrayList<String> uniqueRows = new ArrayList<>();

        for (int i = 0; i < pMatrix.length; i++) {
            for (int j = 0; j < pMatrix[0].length; j++) {
                sum += pMatrix[i][j];
            }
            rowList.add(sum);
            sum = "";
        }

        for (int i = 0; i < rowList.size(); i++) {
            if (!uniqueRows.contains(rowList.get(i))) {
                uniqueRows.add(rowList.get(i));
            }
        }
        return uniqueRows.size();
    }

    /*
    Ansatz für Artkulation:
    Prüfe jeden Knoten, ob er sich um eine Artikulation handelt indem der jeweilige Knoten entfernt wird
    (ganze Zeile und Spalte des Knoten auf 0 setzen) und die Wegmatrix erneut berechnet wird.
    Wenn sich durch Entfernung eines Knotens die Anzahl der Komponenten erhöht, handelt der Knoten sich um eine Artikulation.
     */

    public ArrayList<String> articulations(Integer[][] aMatrix) {
        ArrayList<String> articulationPoints = new ArrayList<>();
        Integer[][] adjacencyMatrix = copyMatrix(aMatrix);
        Integer[][] initialPathMatrix = pathMatrix(aMatrix);
        int componentsCount = components(initialPathMatrix);

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[0].length; j++) {
                adjacencyMatrix[i][j] = 0;
                adjacencyMatrix[j][i] = 0;
            }
            Integer[][] pathMatrix = pathMatrix(adjacencyMatrix);
            int newComponentsCount = components(pathMatrix);
            if (newComponentsCount > componentsCount + 1) {
                articulationPoints.add(String.valueOf((char) ('A' + i)));
            }
            adjacencyMatrix = copyMatrix(aMatrix);
        }
        return articulationPoints;
    }

    public void bridges(Integer[][] aMatrix) {
        //evtl. mittels HashSet nur unique values anzeigen
        ArrayList<String[]> edges = new ArrayList<>();
        Integer[][] adjacencyMatrix = copyMatrix(aMatrix);
        Integer[][] initialPathMatrix = pathMatrix(aMatrix);
        int componentsCount = components(initialPathMatrix);

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[0].length; j++) {
                if (adjacencyMatrix[i][j] == 1) {
                    adjacencyMatrix[i][j] = 0;
                    Integer[][] pathMatrix = pathMatrix(adjacencyMatrix);
                    int newComponentsCount = components(pathMatrix);
                    if (newComponentsCount > componentsCount) {
                        String value1 = String.valueOf((char) ('A' + i));
                        String value2 = String.valueOf((char) ('A' + j));
                        edges.add(new String[]{value1, value2});
                    }
                }
                adjacencyMatrix = copyMatrix(aMatrix);
            }
        }
        for (String[] bridge: edges) {
            System.out.println("["+bridge[0]+","+bridge[1]+"]");
        }
    }
    //------------------------ Miscellaneous -----------------------
    public boolean checkInfinity(Integer[][] matrix) {
        for (Integer[] i : matrix) {
            if (Arrays.asList(i).contains(-1)) {
                return true;
            }
        }
        return false;
    }

    public Integer[][] copyMatrix(Integer[][] src) {
        //Iteriert über jede Zeile im Array und kopiert sie mittels clone()
        Integer[][] copy = new Integer[src.length][];

        for (int i = 0; i < src.length; i++) {
            copy[i] = src[i].clone();
        }
        return copy;
    }

    public Integer[][] multiplicationMatrix(Integer[][] matrix1, Integer[][] matrix2) {
        Integer[][] result = new Integer[matrix1.length][matrix2[0].length];

        //m1[0][0] * m2[0][0] + m1[0][1] * m2[1][0]...
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                result[i][j] = 0;
                for (int k = 0; k < matrix2.length; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }
}