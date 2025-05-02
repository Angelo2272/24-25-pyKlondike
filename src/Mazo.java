public class Mazo {
    private static final int NUM_CARTAS_MAZO = 52;
    private final Carta[] cartas;
    private int indiceActual;
    
    public Mazo() {
        this.cartas = new Carta[NUM_CARTAS_MAZO];
        this.indiceActual = 0;
        inicializarMazo();
        barajar();
    }
    
    private void inicializarMazo() {
        int index = 0;
        for (Palo palo : Palo.values()) {
            for (Valor valor : Valor.values()) {
                cartas[index++] = new Carta(valor, palo);
            }
        }
    }
    
    public void barajar() {
        for (int i = cartas.length - 1; i > 0; i--) {
            int indiceAleatorio = (int)(Math.random() * (i + 1));
            intercambiarCartas(i, indiceAleatorio);
        }
        indiceActual = 0;
    }
    
    private void intercambiarCartas(int i, int j) {
        Carta temp = cartas[i];
        cartas[i] = cartas[j];
        cartas[j] = temp;
    }
    
    public Carta sacarCarta() {
        if (estaVacio()) {
            return null;
        }
        return cartas[indiceActual++];
    }
    
    public boolean quedanCartas() {
        return !estaVacio();
    }
    
    public boolean estaVacio() {
        return indiceActual >= cartas.length;
    }
    
    public void resetear() {
        indiceActual = 0;
        barajar();
    }
    
    public int cartasRestantes() {
        return cartas.length - indiceActual;
    }
}