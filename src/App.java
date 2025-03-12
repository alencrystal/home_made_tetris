import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class App extends JFrame implements KeyListener {
    private static final int WIDTH = 10; // Larghezza della griglia
    private static final int HEIGHT = 20; // Altezza della griglia
    private char[][] grid; // Matrice che rappresenta la griglia di gioco
    private int pieceRow = 0; // Posizione della riga del pezzo corrente
    private int pieceCol = 4; // Posizione iniziale della colonna del pezzo corrente

    
    public App() throws InterruptedException {
        setTitle("Tetris Console"); // Imposta il titolo della finestra
        setSize(300, 200); // Imposta la dimensione della finestra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Chiude il programma alla chiusura della finestra
        addKeyListener(this); // Aggiunge il listener per la tastiera
        setVisible(true); // Rende la finestra visibile

        grid = new char[HEIGHT][WIDTH]; // Inizializza la griglia
        clearGrid(); // Pulisce la griglia
        placePiece('X'); // Posiziona il primo pezzo

        // Loop di gioco che muove automaticamente il pezzo verso il basso
        while (true) { 
            Thread.sleep(250); // Ritardo per simulare il movimento del pezzo, più è basso il valore, più scenderà in fretta
            moveDown();
        }
    }

    // Pulisce la griglia riempiendola di spazi vuoti
    private void clearGrid() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if(grid[i][j] != '#'){
                    grid[i][j] = ' ';
                }
            }
        }
    }

    // Genera un nuovo pezzo in cima alla griglia
    private void spawnPiece(){
        pieceCol = 4;
        pieceRow = 0;
    }

    // Posiziona il pezzo nella griglia
    private void placePiece(char piece) {
        clearGrid(); // Pulisce la griglia prima di posizionare il pezzo
        grid[pieceRow][pieceCol] = piece; // Inserisce il pezzo nella posizione attuale
    }

    // Stampa la griglia nella console
    private void printGrid() {
        System.out.println("\033\143"); // Comando per pulire il terminale

        for (int i = 0; i < HEIGHT; i++) {
            System.out.print("▓"); // Bordo sinistro
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(grid[i][j]); // Contenuto della griglia
            }
            System.out.println("▓"); // Bordo destro
        }
        for (int c = 0; c < WIDTH + 2; c++){ //il + 2 serve per raggiungere la larghezza con anche le pareti
            System.out.print("▓"); // Bordo inferiore
        }
        
    }

    // Muove il pezzo a sinistra se possibile
    public void moveLeft() {
        if (pieceCol > 0 && grid[pieceRow][pieceCol - 1] != '#') {
            pieceCol--;
            placePiece('X');
            printGrid();
        }
    }

    // Muove il pezzo a destra se possibile
    public void moveRight() {
        if (pieceCol < WIDTH - 1 && grid[pieceRow][pieceCol + 1] != '#') {
            pieceCol++;
            placePiece('X');
            printGrid();
        }
    }

    // Muove il pezzo verso il basso
    public void moveDown(){
        if (pieceRow < HEIGHT - 1){
            pieceRow ++;

            // Se il pezzo raggiunge il fondo o un ostacolo, si fissa nella griglia
            if (pieceRow == HEIGHT - 1 || grid[pieceRow + 1][pieceCol] == '#'){
                placePiece('#'); // Rappresenta un pezzo bloccato
                spawnPiece(); // Genera un nuovo pezzo
            } else {
                placePiece('X');  // Continua a muoversi in basso
            }
            printGrid();
        }
    }

    // Gestisce l'input da tastiera
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                moveLeft(); // Sposta il pezzo a sinistra
                break;
            case KeyEvent.VK_RIGHT:
                moveRight(); // Sposta il pezzo a destra
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    // Metodo principale per avviare il gioco
    public static void main(String[] args) throws InterruptedException {
        new App();
    }
}
