import java.io.*;
import java.util.Scanner; //user input
import java.util.Random;  //generate random number
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class CarAssembly {
    static int NUM_LINE = 2;
    static int NUM_STATION = 4;
    static int c = 0; //count primitive operations

    // Utility function to find minimum of two numbers
    static int min(int a, int b){
        return a < b ? a : b;
    }

    static int carAssembly(int a[][], int t[][], int e[], int x[]){
        int T1[]= new int [NUM_STATION];
        int T2[] =new int[NUM_STATION] ;
        int i;

        // time taken to leave first station in line 1
        T1[0] = e[0] + a[0][0]; c=c+5;

        // time taken to leave first station in line 2
        T2[0] = e[1] + a[1][0]; c=c+5;

        // Fill tables T1[] and T2[] using
        // the above given recursive relations
        c=c+(3*NUM_STATION)+1;
        for (i = 1; i < NUM_STATION; ++i){
            T1[i] = min(T1[i - 1] + a[0][i], T2[i - 1] + t[1][i] + a[0][i]);
            T2[i] = min(T2[i - 1] + a[1][i], T1[i - 1] + t[0][i] + a[1][i]);
        }
        c=13*(NUM_STATION-1);
        c=13*(NUM_STATION-1);

        c=c+10;
        return min(T1[NUM_STATION-1] + x[0], T2[NUM_STATION-1] + x[1]); // Consider exit times and return minimum
    }
    public static void main(String[] args) throws java.io.IOException{
        int upperbound=20; //limit upperbound
        Random rand = new Random(); //instance of random class
        FileWriter myWriter = new FileWriter("carAssembly_dataset.txt");

        Scanner myObj = new Scanner(System.in);
        System.out.print("Enter Station Number: ");
        NUM_STATION = myObj.nextInt();

        try {
            myWriter.write("Station Number: "+NUM_STATION);
            System.out.println(); myWriter.write("\n");
            System.out.print("Successfully wrote to the textfile: carAssembly_dataset.txt.\n\n");
        }
        catch (IOException k) {
            System.out.println("An error occurred.");
            k.printStackTrace();
        }

        int[][] a = new int[NUM_LINE][NUM_STATION];
        int[][] t = new int[NUM_LINE][NUM_STATION];
        int[] e = new int[NUM_LINE];
        int[] x = new int[NUM_LINE];

        try {
            myWriter.write("\n//---a values\n");
            System.out.println("//---a values");

            //Time required at station S[i,j]
            for(int i=0; i<NUM_LINE; i++){
                for(int j=0; j<NUM_STATION; j++ ){
                    int int_random = rand.nextInt(upperbound);
                    a[i][j] = int_random;
                    myWriter.write(a[i][j] + "\t");
                    System.out.print(a[i][j]+"\t");
                }
                System.out.println(); myWriter.write("\n");
            }
            System.out.print("Successfully wrote to the textfile: carAssembly_dataset.txt.\n\n");
        }
        catch (IOException k) {
            System.out.println("An error occurred.");
            k.printStackTrace();
        }

        try {
            myWriter.write("\n//---t values\n");
            System.out.println("//---t values");

            //Time required to transit from station S[i,j] to the other assembly line
            for(int i=0; i<NUM_LINE; i++) {
                for (int j = 0; j < NUM_STATION; j++) {
                    int int_random = rand.nextInt(upperbound);
                    t[i][j] = int_random;
                    myWriter.write(t[i][j] + "\t");
                    System.out.print(t[i][j] + "\t");
                } System.out.println(); myWriter.write("\n");
            }
            System.out.print("Successfully wrote to the textfile: carAssembly_dataset.txt.\n\n");
        }
        catch (IOException k) {
            System.out.println("An error occurred.");
            k.printStackTrace();
        }

        try {
            myWriter.write("\n//---e values\n");
            System.out.println("//---e values");

            //Entry time of product on assembly line i
            for(int i=0; i<e.length; i++){
                int int_random = rand.nextInt(upperbound);
                e[i] = int_random;
                myWriter.write(e[i]+"\t");
                System.out.print(e[i]+"\t");
            }
            System.out.println("\nSuccessfully wrote to the textfile: carAssembly_dataset.txt.\n");
        }
        catch (IOException k) {
            System.out.println("An error occurred.");
            k.printStackTrace();
        }

        try {
            myWriter.write("\n\n//---x values\n");
            System.out.println("//---x values");

            //Exit time from assembly line i
            for(int i=0; i<x.length; i++){
                int int_random = rand.nextInt(upperbound);
                x[i] = int_random;
                myWriter.write(x[i]+"\t");
                System.out.print(x[i]+"\t");
            }
            myWriter.close();
            System.out.println("\nSuccessfully wrote to the textfile: carAssembly_dataset.txt.");
        }
        catch (IOException k) {
            System.out.println("An error occurred.");
            k.printStackTrace();
        }

        System.out.println("\nThe minimum time taken = "+carAssembly(a, t, e, x));
        System.out.println("Primitive operations = "+c);
    }

}
