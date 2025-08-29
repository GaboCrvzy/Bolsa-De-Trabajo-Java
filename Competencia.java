package bolsatrabajos;

public class Competencia {
    private String nombre;
    private Nivel nivel;

    public Competencia(String nombre, Nivel nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public boolean cumpleConNivel(Nivel nivelMinimo) {
        return nivel.esMayorOIgual(nivelMinimo);
    }
}
