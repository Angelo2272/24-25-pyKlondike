public class Carta {
    private final Valor valor;
    private final Palo palo;
    private boolean visible;
    
    public Carta(Valor valor, Palo palo) {
        this.valor = valor;
        this.palo = palo;
        this.visible = false;
    }
    
    public void voltear() {
        this.visible = !this.visible;
    }
    
    public boolean esVisible() {
        return visible;
    }
    
    public Palo getPalo() {
        return palo;
    }
    
    public Valor getValor() {
        return valor;
    }
    
    @Override
    public String toString() {
        if (!visible) return "[? ?]";
        return "[" + valor.getSimbolo() + " " + palo.getSimbolo() + "]";
    }
}