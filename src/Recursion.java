import java.sql.SQLOutput;
import java.util.Arrays;
import java.io.*;
import java.util.Scanner;
public class Recursion {
    int rows;
    int columns;
    char[][] filled;
    boolean[][] visited;


    private void userInputBlobs(Scanner scan, String fileName){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            System.out.println("Enter the Number of Rectangles in the File:");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * When the user clicks the "Count the Blobs" button, find the number
     * of blobs in the grid and report the number in the message Label.
     */
    private void countBlobs() {

        int count = 0; // Number of blobs.

        /* First clear out the visited array. The getBlobSize() method will
               mark every filled square that it finds by setting the corresponding
               element of the array to true.  Once a square has been marked as
               visited, it will stay marked until all the blobs have been counted.
               This will prevent the same blob from being counted more than once. */

        for (int r = 0; r < rows; r++)
            for (int c = 0; c < columns; c++)
                visited[r][c] = false;

        /* For each position in the grid, call getBlobSize() to get the size
               of the blob at that position.  If the size is not zero, count a blob.
               Note that if we come to a position that was part of a previously
               counted square, getBlobSize() will return 0 and the blob will not
               be counted again. */

        for (int r = 0; r < rows; r++)
            for (int c = 0; c < columns; c++) {
                if (getBlobSize(r,c) > 0)
                    count++;
            }
    } // end countBlobs()


    /**
     * Counts the squares in the blob at position (r,c) in the
     * grid.  Squares are only counted if they are filled and
     * unvisited.  If this routine is called for a position that
     * has been visited, the return value will be zero.
     */
    private int getBlobSize(int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= columns) {
            // This position is not in the grid, so there is
            // no blob at this position.
            return 0;
        }
        if (filled[r][c] == '.'|| visited[r][c]) {
            // This square is not part of a blob, or else it has
            // already been counted, so return zero.
            return 0;
        }
        visited[r][c] = true;   // Mark the square as visited so that
        //    we won't count it again during the
        //    following recursive calls to this method.
        int size = 1;   // Count the square at this position, then count the
        //   the blobs that are connected to this square
        //    horizontally or vertically.
        size += getBlobSize(r-1,c);
        size += getBlobSize(r+1,c);
        size += getBlobSize(r,c-1);
        size += getBlobSize(r,c+1);
        return size;
    }  // end getBlobSize()

    /** Method Name: printLoop
     * @Author Devin Tran
     * @Date 10/23/2023
     * @Modified 10/23/2023
     * @Description BRIEF IN A SENTENCE
     * @Parameters A string that represents the file
     * @Returns N/A
     * Dependencies: I/O
     * Throws/Exceptions:
     **/
    public static void readRecord(String fileName, char[][] array) {
        //boolean fileRead = false;
        int rows;
        int columns;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            columns = Integer.parseInt(line);
            rows = Integer.parseInt(reader.readLine());
            while(reader.ready()) {
                for(int i = 0; i < rows; i++) {
                    for(int j = 0; j < columns; j++) {
                        array[i][j] = line.charAt(i);
                    }
                }
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

// public static char[][] characterArray(BufferedReader reader, String fileName){
//
// }
//
    /** Method Name: printLoop
     * @Author Devin Tran
     * @Date 10/20/2023
     * @Modified 10/22/2023
     * @Description BRIEF IN A SENTENCE
     * @Parameters A string that represents the file
     * @Returns N/A
     * Dependencies: I/O
     * Throws/Exceptions:
     **/

    public static void printLoop(char printedChar, int counter) {
        if(counter == 0) {
            return;
        }
        System.out.print(printedChar);
        printLoop(printedChar, counter--);
    }

    /** Method Name: factorOfTwo
     * @Author Devin Tran
     * @Date 10/22/2023
     * @Modified 10/22/2023
     * @Description BRIEF IN A SENTENCE
     * @Parameters A string that represents the file
     * @Returns N/A
     * Dependencies: I/O
     * Throws/Exceptions:
     **/
    public static boolean factorOfTwo(int num) {
        if(num % 2 == 0) {
            factorOfTwo(num/2);
            return true;
        }
        else {
            return false;
        }
    }

    /** Method Name: pattern
     * @Author Devin Tran
     * @Date 10/22/2023
     * @Modified 10/22/2023
     * @Description BRIEF IN A SENTENCE
     * @Parameters A string that represents the file
     * @Returns N/A
     * Dependencies: I/O
     * Throws/Exceptions:
     **/
    public static void pattern(int stars, int space) {
        if(!factorOfTwo(stars)) {
            System.out.println("ERROR");
            return;
        }
        if(stars == 0) {
            return;
        }
        pattern(stars / 2, space);
        printLoop(' ', space);
        printLoop('*', stars);
        System.out.println();
        pattern(stars / 2, space + stars / 2);
    }

    /** Method Name: possiblePal
     * @Author Devin Tran
     * @Date 10/19/2023
     * @Modified 10/22/2023
     * @Description Will check if a palindrome is possible in a given string
     * @Parameters A sorted character array. The array index. A counter to show how many odd occurrences of a character has happened. A counter that is the number of times a specific character has occurred
     * @Returns If the string can be a palindrome
     * Dependencies: N/A
     * Throws/Exceptions: N/A
     **/
    public static boolean possiblePals(char[] array, int index, int numOfOdd, int occurrence) {
        if(index >= array.length) {
            return numOfOdd <= 1;
        }
        if(array.length == 1 || array.length ==2) {
            return true;
        }
        if(index+1 < array.length && array[index] == array[index+1]) {
            return possiblePals(array, index+2, numOfOdd, occurrence+2);
        }
        else {
            return possiblePals(array, index+1, numOfOdd+1, occurrence+1);
        }
    }


    public static void main(String[] args) {
        String test = "m"; //Given string - test if can be a possible pal
//System.out.println(test.length());// string.length() gives number of chars

        char[] arrayOfChar = new char[test.length()]; // creating an array of chars based off of the string length
        arrayOfChar = test.toCharArray();
        Arrays.sort(arrayOfChar);

//testing to find array length
        int arraylen = arrayOfChar.length;// array.length // gives number of index - starts at 0 ends at 2 but still length of 3
//System.out.println(arraylen);
        System.out.println(possiblePals(arrayOfChar, 0, 0, 0));

        System.out.println(factorOfTwo(15));
    }

}