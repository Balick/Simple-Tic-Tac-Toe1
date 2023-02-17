package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static char[][] board = new char[3][3];

    public static void main(String[] args) {
        fillCases(); // fill all cases of board with empty spaces
        printBoard(); // Print initial board
        play(); // start playing
    }

    /** Fill the array with empty spaces */
    public static void fillCases() {
        for (char[] chars : board) {
            Arrays.fill(chars, ' ');
        }
    }

    /** let's play the game */
    public static void play() {
        Scanner scanner = new Scanner(System.in);
        // player's symbol
        char player = '_';

        // Game's main loop that will turn 9 times. The number of squares corresponds to the number of towers
        for (int i = 0; i < 9; i++) {
            // changing turns
            if (i % 2 == 0) player = 'X';
            else player = 'O';

            do { // Run while inputs are incorrect
                try {
                    int x = Integer.parseInt(String.valueOf(scanner.next().charAt(0)));
                    int y = Integer.parseInt(String.valueOf(scanner.next().charAt(0)));
                    // both failed
                    boolean bothFailed =!isChampion('X') && !isChampion('O');

                    if ((x < 1 || x > 3) || (y < 1 || y > 3)) {
                        System.out.println("Coordinates should be from 1 to 3!");
                    } else if (board[x-1][y-1] == 'X' || board[x-1][y-1] == 'O') {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        board[x-1][y-1] = player;
                        printBoard();
                        if (isChampion('X') || isChampion('O')) {
                            System.out.printf("%c wins", player);
                            System.exit(0); // Game's end
                        } else if (bothFailed && i == 8) {
                            System.out.println("Draw");
                            System.exit(0); // Game's end
                        }
                        break;
                    }
                } catch (NumberFormatException error) {
                    System.out.println("You should enter numbers!");
                }
            } while (true);
        }
    }

    /** Show edge with all boxes */
    public static void printBoard() {
        System.out.println("---------" /* decor */);
        for (char[] cases : board) {
            System.out.print("| " /* decor */);
            for (char value : cases) {
                System.out.printf("%c ", value);
            }
            System.out.println("| " /* decor */);
        }
        System.out.println("---------" /* decor */);
    }

    /**
     * Checks if the player passed in parameter has succeeded
     * @param player player's symbol
     * @return true [if player has failed] or false [if player has win]
     * */
    public static boolean isChampion(char player) {
        final boolean case1 = board[0][0] == player && board[0][1] == player && board[0][2] == player;
        final boolean case2 = board[1][0] == player && board[1][1] == player && board[1][2] == player;
        final boolean case3 = board[2][0] == player && board[2][1] == player && board[2][2] == player;
        final boolean case4 = board[0][0] == player && board[1][0] == player && board[2][0] == player;
        final boolean case5 = board[0][1] == player && board[1][1] == player && board[2][1] == player;
        final boolean case6 = board[0][2] == player && board[1][2] == player && board[2][2] == player;
        final boolean case7 = board[0][0] == player && board[1][1] == player && board[2][2] == player;
        final boolean case8 = board[0][2] == player && board[1][1] == player && board[2][0] == player;

        return case1 || case2 || case3 || case4 || case5 || case6 || case7 || case8;
    }
}
