
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        int[][] board  = new int[20][10];   // array di array (matrice) 20 sono le colonne e 10 le righe
        board[6][5] = 1;

        int count_print_board = 0;


        while (true) { 

            System.out.println("\033\143"); // pulisce il terminale
            
            printBoard(board);
            
            count_print_board++;
            System.out.println("\n"+ count_print_board);
            sc.nextLine();
            
        }
    
    }


    static void printBoard(int[][] board) {
        for (int y = 0; y < board.length; y++) {    // per ogni colonna va a capo guardando la lunghezza (20)

            for (int x = 0; x < board[y].length; x++) { // per ogni riga della matrice guarda i valori e ne stampa uno per la lunghezza (10) se ne mettessimo di lunghezze diverse verrebbe un poligono non regolare
                
                if(x == 0 ){
                    System.out.print("▓▓");         //bordi laterali della matrice 
                } 

                if (board[y][x] == 0){
                    System.out.print("  "); // è vuota la casella
                }
                else if(board[y][x] == 1){
                    System.out.print("[]"); // nella casella c'è il blocco in movimento
                }
                else if(board[y][x] == 2){
                    System.out.print("##"); // è piena la casella (blocco statico)
                }

                if (x == board[y].length - 1) {
                    System.out.print("▓▓");         //bordi laterali della matrice 
                }        
                  
            }
            System.out.println();

            if (y == board.length - 1) {
                for (int x = 0; x < board[y].length + 2; x++) {
                    System.out.print("▓▓"); //bordi inferiori della matrice
                }
            }

    }
    

    }
}
