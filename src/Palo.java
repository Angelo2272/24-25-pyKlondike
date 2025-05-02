public enum Palo {
    CORAZONES("♥️"), DIAMANTES("♦️"), TREBOLES("♣️"), PICAS("♠️");
    
    private final String simbolo;
    
    Palo(String simbolo) {
        this.simbolo = simbolo;
    }
    
    public String getSimbolo() {
        return simbolo;
    }
}

