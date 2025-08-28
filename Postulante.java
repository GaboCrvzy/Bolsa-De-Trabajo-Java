
package bolsatrabajos;

import java.util.ArrayList;

public class Postulante {
    private String rutPostulante;
    private String nombrePostulante;
    private String ciudadPostulante;
    private String numeroContacto;
    private final ArrayList<Competencia> habilidades;

    // Constructor
    public Postulante(String rutPostulante, String nombrePostulante, String ciudadPostulante, String numeroContacto) {
        this.rutPostulante = rutPostulante;
        this.nombrePostulante = nombrePostulante;
        this.ciudadPostulante = ciudadPostulante;
        this.numeroContacto = numeroContacto;
        this.habilidades = new ArrayList<>();
    }

    // Getters y Setters
    public String getRutPostulante() { return rutPostulante; }
    public String getNombrePostulante() { return nombrePostulante; }
    public String getCiudadPostulante() { return ciudadPostulante; }
    public String getNumeroContacto() { return numeroContacto; }
    public ArrayList<Competencia> getCompetencias() { return habilidades; }
    

    public void setRutPostulante(String rutPostulante) { this.rutPostulante = rutPostulante; }
    public void setNombrePostulante(String nombrePostulante) { this.nombrePostulante = nombrePostulante; }
    public void setCiudadPostulante(String ciudadPostulante) { this.ciudadPostulante = ciudadPostulante; }
    public void setNumeroContacto(String numeroContacto) { this.numeroContacto = numeroContacto; }

    @Override
    public String toString() {
        return "\n" + "Postulante: " + nombrePostulante + " [" + rutPostulante + "]\n" +
                "Ciudad: " + ciudadPostulante + "\n" +
                "Contacto: " + numeroContacto + "\n" +
                "Habilidades: " + habilidades + "\n";
    }
}
