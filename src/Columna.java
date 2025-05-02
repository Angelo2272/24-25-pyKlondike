import java.util.Stack;

public class Columna {
    private final Stack<Carta> cartas;
    
    public Columna() {
        this.cartas = new Stack<>();
    }
    
    public void agregarCarta(Carta carta) {
        cartas.push(carta);
    }
    
    public boolean agregarGrupoCartas(Stack<Carta> grupo) {
        if (cartas.isEmpty()) {
            if (grupo.peek().getValor() == Valor.REY) {
                cartas.addAll(grupo);
                return true;
            }
        } else {
            Carta ultima = cartas.peek();
            Carta primeraGrupo = grupo.firstElement();
            
            if (primeraGrupo.getValor().ordinal() == ultima.getValor().ordinal() - 1 &&
                primeraGrupo.getPalo().ordinal() % 2 != ultima.getPalo().ordinal() % 2) {
                cartas.addAll(grupo);
                return true;
            }
        }
        return false;
    }
    
    public Stack<Carta> tomarCartas(int cantidad) {
        if (cantidad > cartas.size()) return null;
        
        Stack<Carta> grupo = new Stack<>();
        for (int i = 0; i < cantidad; i++) {
            grupo.push(cartas.pop());
        }
        return grupo;
    }
    
    public void voltearUltimaCarta() {
        if (!cartas.isEmpty() && !cartas.peek().esVisible()) {
            cartas.peek().voltear();
        }
    }
    
    public boolean estaVacia() {
        return cartas.isEmpty();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Carta carta : cartas) {
            sb.append(carta.toString());
        }
        return sb.toString();
    }
}