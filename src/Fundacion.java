import java.util.Stack;

public class Fundacion {
    private final Palo palo;
    private final Stack<Carta> cartas;
    
    public Fundacion(Palo palo) {
        this.palo = palo;
        this.cartas = new Stack<>();
    }
    
    public boolean agregarCarta(Carta carta) {
        if (carta.getPalo() != palo) return false;
        
        if (cartas.isEmpty()) {
            if (carta.getValor() == Valor.AS) {
                cartas.push(carta);
                return true;
            }
        } else {
            if (carta.getValor().ordinal() == cartas.peek().getValor().ordinal() + 1) {
                cartas.push(carta);
                return true;
            }
        }
        return false;
    }
    
    public Carta sacarCarta() {
        if (cartas.isEmpty()) return null;
        return cartas.pop();
    }
    
    public boolean estaCompleto() {
        return !cartas.isEmpty() && cartas.peek().getValor() == Valor.REY;
    }
    
    @Override
    public String toString() {
        if (cartas.isEmpty()) return "No hay cartas en el palo";
        return cartas.peek().toString();
    }
}