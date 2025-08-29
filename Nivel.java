package bolsatrabajos;

public enum Nivel {
    BASICO, INTERMEDIO, AVANZADO;

    public boolean esMayorOIgual(Nivel otro) {
        return this.ordinal() >= otro.ordinal();
    }
}
