package proyectopoo;
import java.text.Normalizer;

public class Competencia {
    private String nombre;
    private String nivel; // SOLO BASICO, INTERMEDIO, AVANZADO

    // CONSTRUCTOR CON PARAMETROS - lanza NivelInvalidoException si nivel invalido
    public Competencia(String nombre, String nivel){
        this.nombre = nombre;
        this.nivel = nivel;
    }

    // CONSTRUCTOR SIN PARAMETROS
    public Competencia() {
        nombre = null;
        nivel = null;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) {
        if (nombre == null) return;
        this.nombre = normalizarName(nombre);
    }

    public String getNivel() { return nivel; }

    // setNivel lanza NivelInvalidoException si el nivel no es correcto
    public void setNivel(String nivel) throws NivelInvalidoException {
        if (nivel == null) {
            throw new NivelInvalidoException("Ningún nivel ingresado (nulo).");
        }
        String aux = normalizarName(nivel);
        if (aux.equals("BASICO") || aux.equals("INTERMEDIO") || aux.equals("AVANZADO")) {
            this.nivel = aux;
        } else {
            throw new NivelInvalidoException("Nivel inválido: " + nivel + ". Valores válidos: BASICO, INTERMEDIO, AVANZADO.");
        }
    }

    // NORMALIZA LOS STRING SOLO A MAYUSCULAS SIN TILDES
    public String normalizarName(String name) {
        if (name == null) return "";
        String aux = name.trim().toUpperCase();
        aux = Normalizer.normalize(aux, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        aux = aux.replaceAll("[^A-Z0-9 ]", "");
        return aux;
    }

    // CONVIERTE NIVEL A RANK PARA COMPARAR
    public int nivelToRank(String nivel) {
        if (nivel == null) return 0;
        switch (normalizarName(nivel)) {
            case "BASICO": return 1;
            case "INTERMEDIO": return 2;
            case "AVANZADO": return 3;
            default: return 0;
        }
    }

    // COMPROBAR SI EL NIVEL DE LA COMPETENCIA ACTUAL CUMPLE CON NIVEL MINIMO DEL STRING PUESTO
    public boolean cumpleCon(String nivelMinimo) {
        if (!esNivelValido(this.nivel)) return false;
        if (!esNivelValido(nivelMinimo)) return false;
        return nivelToRank(this.nivel) >= nivelToRank(nivelMinimo);
    }

    // COMPRUEBA SI EL NIVEL ES VALIDO
    public boolean esNivelValido(String nivel) {
        if (nivel == null) return false;
        String aux = normalizarName(nivel);
        return "BASICO".equals(aux) || "INTERMEDIO".equals(aux) || "AVANZADO".equals(aux);
    }

    // SOBRECARGA METODOS 
    public boolean cumpleCon(Competencia otra) {
        if (otra == null) return false;
        return cumpleCon(otra.getNivel());
    }

    // Compara niveles de dos competencias: -1 si actual < otra, 0 igual, +1 if actual > otra
    public int compareNivel(Competencia otra) {
        if (otra == null) return 1; // Actua es "mayor" que null
        int r1 = nivelToRank(this.nivel);
        int r2 = nivelToRank(otra.getNivel());
        return Integer.compare(r1, r2);
    }

    @Override
    public String toString() {
        String nom = (nombre == null) ? "<sin nombre>" : nombre;
        String niv = (nivel == null) ? "<sin nivel>" : nivel;
        return nom + " (" + niv + ")";
    }
}
