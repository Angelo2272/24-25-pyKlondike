import java.util.Scanner;
import java.util.Stack;

public class KlondikeGame {
    private static final int NUM_PALOS = 4;
    private static final int NUM_COLUMNAS = 7;
    private static final int OPCION_SALIR = 9;

    private final Mazo mazo;
    private final Stack<Carta> descarte;
    private final Fundacion[] fundaciones;
    private final Columna[] columnas;
    private final Scanner scanner;
    private boolean juegoActivo;

    public static void main(String[] args) {
        new KlondikeGame().iniciarJuego();
    }

    public KlondikeGame() {
        this.scanner = new Scanner(System.in);
        this.mazo = new Mazo();
        this.descarte = new Stack<>();
        this.fundaciones = inicializarFundaciones();
        this.columnas = inicializarColumnas();
    }

    public void iniciarJuego() {
        juegoActivo = true;
        repartirCartasIniciales();

        while (juegoActivo) {
            mostrarInterfazJuego();
            procesarOpcion(obtenerOpcionUsuario());
        }
    }

    private Fundacion[] inicializarFundaciones() {
        Fundacion[] fundaciones = new Fundacion[NUM_PALOS];
        for (int i = 0; i < NUM_PALOS; i++) {
            fundaciones[i] = new Fundacion(Palo.values()[i]);
        }
        return fundaciones;
    }

    private Columna[] inicializarColumnas() {
        Columna[] columnas = new Columna[NUM_COLUMNAS];
        for (int i = 0; i < NUM_COLUMNAS; i++) {
            columnas[i] = new Columna();
        }
        return columnas;
    }

    private void repartirCartasIniciales() {
        for (int columna = 0; columna < NUM_COLUMNAS; columna++) {
            for (int fila = 0; fila <= columna; fila++) {
                Carta carta = mazo.sacarCarta();
                if (esUltimaCartaDeColumna(fila, columna)) {
                    carta.voltear();
                }
                columnas[columna].agregarCarta(carta);
            }
        }
    }

    private boolean esUltimaCartaDeColumna(int fila, int columna) {
        return fila == columna;
    }

    private void mostrarInterfazJuego() {
        mostrarEstadoBaraja();
        mostrarEstadoDescarte();
        mostrarEstadoFundaciones();
        mostrarEstadoColumnas();
        mostrarMenuOpciones();
    }

    private void mostrarEstadoBaraja() {
        System.out.printf("\nBARAJA: [%s ?]\n", mazo.quedanCartas() ? "?" : " ");
    }

    private void mostrarEstadoDescarte() {
        System.out.print("Descarte: ");
        if (descarte.isEmpty()) {
            System.out.println("No hay cartas en el descarte");
        } else {
            System.out.println(descarte.peek());
        }
    }

    private void mostrarEstadoFundaciones() {
        for (int i = 0; i < NUM_PALOS; i++) {
            System.out.printf("%dº Palo: %s\n", i + 1, fundaciones[i]);
        }
    }

    private void mostrarEstadoColumnas() {
        for (int i = 0; i < NUM_COLUMNAS; i++) {
            System.out.printf("Columna [%d]: %s\n", i + 1, columnas[i]);
        }
    }

    private void mostrarMenuOpciones() {
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

    private int obtenerOpcionUsuario() {
        return scanner.nextInt();
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> moverMazoADescarte();
            case 2 -> moverDescarteAFundacion();
            case OPCION_SALIR -> finalizarJuego();
            default -> System.out.println("Opción no válida");
        }
    }

    private void moverMazoADescarte() {
        if (mazo.estaVacio()) {
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
        Fundacion fundacionDestino = fundaciones[carta.getPalo().ordinal()];

        if (fundacionDestino.agregarCarta(carta)) {
            descarte.pop();
        } else {
            System.out.println("Movimiento no válido");
        }
    }

    private void finalizarJuego() {
        juegoActivo = false;
        scanner.close();
        System.out.println("¡Gracias por jugar!");
    }
}