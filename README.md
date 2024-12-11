# PathFinder
> Path Finder ist ein Java 17-Programm zur Berechnung und Visualisierung zentraler Metriken in der Graphentheorie. Es ermöglicht die Analyse von Graphen durch Berechnung von Distanzmatrizen, Exzentrizitäten, Radius, Durchmesser und weiteren wichtigen Eigenschaften. Das Programm bietet eine einfache Möglichkeit, diese Metriken zu berechnen und graphisch darzustellen. Es wurde im Rahmen des Fachs **POS (Graphentheorie)** entwickelt.
>
> Path Finder is a Java 17 program for calculating and visualizing key metrics in graph theory. It enables the analysis of graphs by calculating distance matrices, eccentricities, radius, diameter and other important properties. The program offers a simple way of calculating and graphically displaying these metrics. It was developed as part of the subject **POS (graph theory)**.

# Funktionen
- **Einlesen von .csv-Dateien:** Importiert Graphdaten aus CSV-Dateien zur einfachen Verarbeitung und Analyse.
  
- **Berechnung von Distanzmatrizen und kürzesten Wegen:** Berechnet die Distanz zwischen allen Paaren von Knoten und den kürzesten Weg zwischen Knoten, basierend auf Algorithmen wie Dijkstra.
  
- **Analyse von Exzentrizitäten, Radius und Durchmesser:** Bestimmt die Exzentrizität jedes Knotens, den Radius (den kleinsten maximalen Abstand) und den Durchmesser (den größten maximalen Abstand) des Graphen.
  
- **Berechnung von Brücken (Cut-Edges):** Identifiziert Kanten, deren Entfernung den Graphen in mehr als eine zusammenhängende Komponente zerlegt. Diese Kanten sind essentiell für die Struktur des Graphen.
  
- **Berechnung von Artikulationspunkten (Cut-Vertices):** Findet Knoten, deren Entfernung den Graphen in mehrere Komponenten aufteilen würde. Diese Knoten sind entscheidend für die Konnektivität des Graphen.
  
- **Berechnung von Komponenten:** Identifiziert zusammenhängende Teilgraphen innerhalb des Graphen. Dies hilft, die Struktur und den Zusammenhang des gesamten Graphen zu verstehen.
  
- **Unterstützung für ungerichtete Graphen**: Das Programm ist ausschließlich für ungerichtete Graphen ausgelegt und ermöglicht eine umfassende Analyse dieser speziellen Graphenart.
