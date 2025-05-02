public class Mazo {
    private Carta[] cartas;
    private int indiceActual;
    
    public Mazo() {
        cartas = new Carta[52];
        inicializar();
        barajar();
    }
    
    private void inicializar() {
        int i = 0;
        for (Palo palo : Palo.values()) {
            for (Valor valor : Valor.values()) {
                cartas[i++] = new Carta(valor, palo);
            }
        }
    }
    
    public void barajar() {
        for (int i = 0; i < cartas.length; i++) {
            int j = (int)(Math.random() * cartas.length);
            Carta temp = cartas[i];
            cartas[i] = cartas[j];
            cartas[j] = temp;
        }
        indiceActual = 0;
    }
    
    public Carta sacarCarta() {
        if (indiceActual >= cartas.length) return null;
        return cartas[indiceActual++];
    }
    
    public boolean quedanCartas() {
        return indiceActual < cartas.length;
    }
    
    public void resetear() {
        indiceActual = 0;
        barajar();
    }
}