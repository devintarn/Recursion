import java.io.*;

public class Recursion {
    static int rows;
    static int columns;
    static char[][] filled;
    static boolean[][] visited;


    private static void blobsMain(String fileName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            readBlobFile(reader);
            int counter = 0;
            for(int i = 0; i < filled.length; i++){
                for(int j = 0; j < filled[i].length; j++){

                    System.out.print(filled[i][j]);

                    if(filled[i][j] == 'X'){
                        if (getBlobSize(i,j) != 0) counter++;
                    } else {
                        visited[i][j] = true;
                    }

//                    if(filled[i][j] == '.'){
//                        visited[i][j] = true;
//                    }
//                    else{
//                        getBlobSize(i,j);
//                        counter++;
//                    }
                }
                System.out.println();
            }
            System.out.println(counter);
//            for(var x : filled) {
//                for (var y :  x) {
//                    if(x[y] == '.'){
//                        visited[x][y] = true;
//                    }
//                    else{
//                        getBlobSize(x,y);
//                    }
//                    System.out.print(y + " ");
//                } System.out.println();
//            }
//            System.out.println(getBlobSize(rows,columns));


        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private static boolean[][] fillBooleanArray(int rows, int columns){
        boolean[][] array = new boolean[rows][columns];
        return array;
    }
    private static char[][] fillArray(BufferedReader reader, int rows, int columns)throws IOException{
        char[][] array = new char[rows][columns];
        String line;
        line = reader.readLine();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                array[i][j] = line.charAt(j);
            }
            line = reader.readLine();
        }
        return array;
    }

    private static void readBlobFile(BufferedReader reader)throws IOException{
        int columns = Integer.parseInt(reader.readLine());
        int rows = Integer.parseInt(reader.readLine());
        visited = fillBooleanArray(rows, columns);
        filled = fillArray(reader,rows,columns);
    }


    /**
     * Counts the squares in the blob at position (r,c) in the
     * grid.  Squares are only counted if they are filled and
     * unvisited.  If this routine is called for a position that
     * has been visited, the return value will be zero.
     */
    private static int getBlobSize(int r, int c) {
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
     *
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
        blobsMain("data41.txt");
//        String test = "m"; //Given string - test if can be a possible pal
////System.out.println(test.length());// string.length() gives number of chars
//
//        char[] arrayOfChar = new char[test.length()]; // creating an array of chars based off of the string length
//        arrayOfChar = test.toCharArray();
//        Arrays.sort(arrayOfChar);
//
////testing to find array length
//        int arraylen = arrayOfChar.length;// array.length // gives number of index - starts at 0 ends at 2 but still length of 3
////System.out.println(arraylen);
//        System.out.println(possiblePals(arrayOfChar, 0, 0, 0));
//
//        System.out.println(factorOfTwo(15));

    }

}