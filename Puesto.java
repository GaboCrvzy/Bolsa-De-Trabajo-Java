package bolsatrabajos;

import java.util.ArrayList;

public class Puesto {
    private String idPuesto;
    private String titulo;
    private String empresa;
    private String ciudadPuesto;
    private final ArrayList<Competencia> requisitos;
    private final ArrayList<Postulante> postulantes;

    // Constructor
    public Puesto(String idPuesto, String titulo, String empresa, String ciudadPuesto) {
        this.idPuesto = idPuesto;
        this.titulo = titulo;
        this.empresa = empresa;
        this.ciudadPuesto = ciudadPuesto;
        this.requisitos = new ArrayList<>();
        this.postulantes = new ArrayList<>();
    }

    // Getters y Setters
    public String getIdPuesto() { return idPuesto; }
    public String getTitulo() { return titulo; }
    public String getEmpresa() { return empresa; }
    public String getCiudadPuesto() { return ciudadPuesto; }
    public ArrayList<Competencia> getRequisitos() { return requisitos; }
    public ArrayList<Postulante> getPostulantes() { return postulantes; }

    public void setIdPuesto(String idPuesto) { this.idPuesto = idPuesto; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }
    public void setCiudadPuesto(String ciudadPuesto) { this.ciudadPuesto = ciudadPuesto; }

    @Override
    public String toString()
    {
        return "Puesto: " + titulo + " en " + empresa + "\n" +
                "Ciudad: " + ciudadPuesto + "\n" +
                "Requisitos: " + requisitos + "\n" +
                "Postulantes: " + postulantes + "\n";
    }
}
