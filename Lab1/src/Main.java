import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n, k;
        if (args.length < 2) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Introdu numarul de noduri (n): ");
            n = sc.nextInt();
            System.out.print("Introdu marimea clique-ului si a setului stabil (k): ");
            k = sc.nextInt();
            sc.close();
        } else {
            n = Integer.parseInt(args[0]);
            k = Integer.parseInt(args[1]);
        }
        if (k > 1 && n < 2 * k) {
            System.out.println("Nu se poate forma un set stabil de dimensiune " + k + " din " + n + " noduri.");
            return;
        }
        long startTime = System.currentTimeMillis();
        int[][] matrix = new int[n][n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int edge = rand.nextBoolean() ? 1 : 0;
                matrix[i][j] = edge;
                matrix[j][i] = edge;
            }
        }
        for (int i = 0; i < k; i++) { //clique de dim k
            for (int j = i + 1; j < k; j++) {
                matrix[i][j] = 1;
                matrix[j][i] = 1;
            }
        }
        int startStable = n - k; //set stabil de dim k
        for (int i = startStable; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                matrix[i][j] = 0;
                matrix[j][i] = 0;
            }
        }
        long m = 0;
        int[] degrees = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (matrix[i][j] == 1) {
                    m++; //nr de muchii
                    degrees[i]++;
                    degrees[j]++; //increm. gradele ambelor noduri implicate
                }
            }
        }
        int maxDegree = degrees[0];
        int minDegree = degrees[0];
        long sumDegrees = 0;
        for (int i = 0; i < n; i++) {
            sumDegrees += degrees[i];
            if (degrees[i] > maxDegree)
                maxDegree = degrees[i];
            if (degrees[i] < minDegree)
                minDegree = degrees[i];
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        if (n > 30000) {
            System.out.println("Matricea nu este afisata pentru ca n este mare (" + n + ").");
            System.out.println("Timpul de rulare: " + duration + " ms");
        } else {
            System.out.println(convertMatrix(matrix));
        }
        System.out.println("Nr. de muchii: " + m);
        System.out.println("Grad max Δ(G): " + maxDegree);
        System.out.println("Grad min δ(G): " + minDegree);
        System.out.println("Suma gradelor: " + sumDegrees);
        if (sumDegrees == 2 * m)
            System.out.println("Suma gradelor e corecta.");
        else
            System.out.println("Suma gradelor nu e corecta.");
    }

    public static String convertMatrix(int[][] matrix) {
        String s = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                s += matrix[i][j] == 1 ? "\u25A0 " : "\u25A1 ";
            }
            s += "\n";
        }
        return s;
    }
}