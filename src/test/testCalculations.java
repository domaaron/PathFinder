package test;

import model.AdjacencyMatrix;
import model.Calculations;

public class testCalculations {
    private static String separator = "===============================================================================================";

    public static void main(String[] args) {
        System.out.println(separator);

        //Adjazenzmatrix
        AdjacencyMatrix m1 = new AdjacencyMatrix();
        System.out.println(m1.toString());
        System.out.println(separator);

        //Distanzmatrix
        Calculations c1 = new Calculations();
        c1.printDistanceMatrix(c1.distanceMatrix(m1));
        System.out.println(separator);

        //Exzentrizitäten
        c1.eccentricity(c1.distanceMatrix(m1));
        System.out.println(separator);

        //Radius
        c1.radius(c1.distanceMatrix(m1));
        System.out.println(separator);

        //Durchmesser
        c1.diameter(c1.distanceMatrix(m1));
        System.out.println(separator);

        //Zentrum
        c1.center(c1.distanceMatrix(m1));
        System.out.println(separator);

        //Wegmatrix
        c1.printPathMatrix(c1.pathMatrix(m1.getMatrix()));
        System.out.println(separator);

        //Komponente
        System.out.println("Komponente: \n" + "c(G) = " + c1.components(c1.pathMatrix(m1.getMatrix())));
        System.out.println(separator);

        //Artikulationen
        System.out.println("Artikulationen: \n" + c1.articulations(m1.getMatrix()));
        System.out.println(separator);

        //Brücken
        System.out.println("Brücken:");
        c1.bridges(m1.getMatrix());
        System.out.println(separator);
    }

}
