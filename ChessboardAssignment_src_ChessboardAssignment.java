import java.util.Scanner;
//Nathaniel Stone
//W0472662
//09/15/14
//CPMS_280


/* Created by nathanielstone on 9/14/15.
 */
public class ChessboardAssignment {



    //publics for easy testing
    //create the new 2d array
    public static String[][] chessboard = new String[8][8];
    public static boolean running = true;
    public static int turn = 1;
    public static String player;

    /**
     * This method switches between player1 and player2
     */
    public static void checkPlayerTurn() {
        if (turn % 2 == 0) {
            player = "Player2: ";
        }
        else {
            player = "Player1: ";
        }
    }

    /**
     * This method checks that the coordinates are in range of array (0-7)
     * and handles the error checking
     *
     */
    public static boolean findAndCheckCoordinates(String piece) {

        //create a new scanner for the method scope
        Scanner in = new Scanner(System.in);
        boolean loser = false;
        int x=0;
        int y=0;

        try{
            System.out.println("Please enter the X coordinates (0-7) of your move.");
            x = in.nextInt();
            System.out.println("Please enter the Y coordinates (0-7) of your move.");
            y = in.nextInt();
        } catch (Exception e) {
            System.out.println("Please input a valid integer value. You lost your turn");
        }

        //check that the coordinates are in range and place the piece
        if (x <= 0 && x <=7 && y <=0 && y <= 7) {
            if(chessboard[x][y] == null) {
                chessboard[x][y] = piece;
            } else {
                System.out.println("A Piece is already in the location: x: " + x + " y: " + y + ". Lost Turn.");
            }
        } else {
            System.out.println("Please enter a valid coordinate of 0-7. You lost your turn.");
        }
PrintBoard(x,y);
        if(piece == "r") {
            loser = checkDiagonals(x, y);
        }

        if(piece == "b") {
            loser = checkHorizontalVerticle(x, y);
        }

        System.out.println("loser:" + loser);
        return loser;
    }

    /**
     * Checks for winning player for Rooks
     * @param x x-coordinate
     * @param y y-coordinate
     * @return loser T/F
     */
    public static boolean checkHorizontalVerticle(int x, int y) {
        boolean loser = false;

        //loop through array on row
        for(int i = 0; i < chessboard[x].length; i++) {

            //if any values are not null return false
            if(chessboard[x][i] != null) {
                loser = true;
            }
        }

        //loop through all column values
        for(int n = 0; n < chessboard.length; n++) {
            //if any values are not null return false
            if(chessboard[n][y] != null) {
                loser = true;
            }
        }
        return loser;
    }

    /**
     * Checks for winning player for bishops
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return diagonalLoser T/F
     */
    public static boolean checkDiagonals(int x, int y) {
        boolean diagonalLoser = false;

        //right to left; x + 1, y + 1
        int i = x;
        int j = y;
        for(int k = x; k < chessboard.length - 1; k++) {
            i++;
            j++;
            if(chessboard[i][j] != null) {
                diagonalLoser = true;
            }
        }

        //left to right; x - 1, y - 1
        int a = x;
        int b = y;
        for(int k = x; k > 0 && k < chessboard.length; k--) {
            a--;
            b--;
            if(a < 0 ||b < 0) {
                break;
            }
            if(chessboard[a][b] != null) {
                diagonalLoser = true;
            }
        }

        return  diagonalLoser;
    }

    //Print board which iterates through the columns and rows to print a chess board
    public static void PrintBoard(int x,int y){
        for( x = 0; x < 8; x++){
            for( y =0; y<8; y++){
                chessboard[x][y]="_";
            }
        }
        for ( x = 0; x < 8; x ++){
            System.out.println();
            for( y = 0; y< 8; y++){
                if(y==0)
                System.out.print("| ");
                System.out.print(chessboard[x][y]+ " | ");

            }
        }

        System.out.println();



    }


    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the Chessboard!");


        //keep the game going until someone loses
        while(running) {

            //get the right player
            checkPlayerTurn();
            //increment for the next player
            turn++;


            System.out.println(player + "Would you like to play with Rooks 'R' or Bishops 'B' ?");
            //create new scanner to read input
            Scanner input = new Scanner(System.in);

            //get the answer from the user
            String answer = input.nextLine();

            //decide what to do
            switch (answer.toLowerCase()) {
                case "r":
                    //do something with rooks
                    findAndCheckCoordinates("r");
                    break;
                case "b":
                    //do something with bishops
                    findAndCheckCoordinates("b");
                    break;
                default:
                    System.out.println("I'm sorry. I didn't recognized your answer. Please restart game!");
                    running = false;
                    break;

            }
        }


    }

}

/* while looping through array it recognizes a piece that hasn't been placed and prints game over.*/