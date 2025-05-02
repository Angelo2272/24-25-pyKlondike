import java.util.Scanner;
import java.util.Stack;

public class KlondikeGame {
    private Mazo mazo;
    private Stack<Carta> descarte;
    private Fundacion[] fundaciones;
    private Columna[] columnas;
    private Scanner scanner;
    
    public KlondikeGame() {
        scanner = new Scanner(System.in);
        inicializarJuego();
    }
    
    private void inicializarJuego() {
        mazo = new Mazo();
        descarte = new Stack<>();
        
        fundaciones = new Fundacion[4];
        for (int i = 0; i < 4; i++) {
            fundaciones[i] = new Fundacion(Palo.values()[i]);
        }
        
        columnas = new Columna[7];
        for (int i = 0; i < 7; i++) {
            columnas[i] = new Columna();
        }
        
        repartirCartasIniciales();
    }
    
    private void repartirCartasIniciales() {
        for (int col = 0; col < 7; col++) {
            for (int fila = 0; fila <= col; fila++) {
                Carta carta = mazo.sacarCarta();
                if (fila == col) { 
                    carta.voltear();
                }
                columnas[col].agregarCarta(carta);
            }
        }
    }
    
    public void jugar() {
        boolean salir = false;
        
        while (!salir) {
            mostrarEstadoJuego();
            mostrarMenu();
            
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1: moverMazoADescarte(); break;
                case 2: moverDescarteAFundacion(); break;
              
                case 9: salir = true; break;
                default: System.out.println("Opción no válida");
            }
        }
    }
    
    private void mostrarEstadoJuego() {
        System.out.println("\nBARAJA: [" + (mazo.quedanCartas() ? "?" : " ") + " ?]");
        System.out.print("Descarte: ");
        if (descarte.isEmpty()) {
            System.out.println("No hay cartas en el descarte");
        } else {
            System.out.println(descarte.peek().toString());
        }
        
        for (int i = 0; i < 4; i++) {
            System.out.println((i+1) + "º Palo: " + fundaciones[i].toString());
        }
        
        for (int i = 0; i < 7; i++) {
            System.out.println("Columna [" + (i+1) + "]: " + columnas[i].toString());
        }
    }
    
    private void mostrarMenu() {
        System.out.println("\nOPCIONES>");
        System.out.println("  1. Mover de Baraja a Descarte");
        System.out.println("  2. Mover de Descarte a Palo");
        System.out.println("  3. Mover de Descarte a Columna");
        System.out.println("  4. Mover de Palo a Columna");
        System.out.println("  5. Mover de Columna a Palo");
        System.out.println("  6. Mover de Columna a Columna");
        System.out.println("  7. Voltear carta de Columna");
        System.out.println("  8. Voltear Descarte en Baraja");
        System.out.println("  9. Salir");
        System.out.print("\nElige una opción [1-9]: ");
    }
    
    private void moverMazoADescarte() {
        if (!mazo.quedanCartas()) {
            System.out.println("No quedan cartas en la baraja");
            return;
        }
        
        Carta carta = mazo.sacarCarta();
        carta.voltear();
        descarte.push(carta);
    }
    
    private void moverDescarteAFundacion() {
        if (descarte.isEmpty()) {
            System.out.println("No hay cartas en el descarte");
            return;
        }
        
        Carta carta = descarte.peek();
        int paloIndex = carta.getPalo().ordinal();
        
        if (fundaciones[paloIndex].agregarCarta(carta)) {
            descarte.pop();
        } else {
            System.out.println("Movimiento no válido");
        }
    }
    

    
    public static void main(String[] args) {
        KlondikeGame juego = new KlondikeGame();
        juego.jugar();
    }
}