package bolsatrabajos;

import java.util.ArrayList;

public class Puesto {
    private String id;
    private String titulo;
    private String empresa;
    private String ciudad;
    private Competencia[] requisitos;
    private ArrayList<Postulante> postulantes;

    public Puesto(String id, String titulo, String empresa, String ciudad, Competencia[] requisitos) {
        this.id = id;
        this.titulo = titulo;
        this.empresa = empresa;
        this.ciudad = ciudad;
        this.requisitos = requisitos != null ? requisitos : new Competencia[0];
        this.postulantes = new ArrayList<>();
    }

    public void agregarPostulante(Postulante p) {
        postulantes.add(p);
    }

    public boolean eliminarPostulante(String rut) {
        return postulantes.removeIf(p -> p.getRut().equals(rut));
    }

    public boolean cumpleTodosLosRequisitos(Postulante postulante) {
        for (Competencia req : requisitos) {
            if (!postulante.tieneCompetenciaConNivelMinimo(req.getNombreCompetencia(), req.getNivelCompetencia())) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Postulante> seleccionarPostulantes() {
        ArrayList<Postulante> seleccion = new ArrayList<>();
        for (Postulante p : postulantes) {
            if (cumpleTodosLosRequisitos(p)) {
                seleccion.add(p);
            }
        }
        return seleccion;
    }

    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getEmpresa() { return empresa; }
    public String getCiudad() { return ciudad; }
    public Competencia[] getRequisitos() { return requisitos; }
    public ArrayList<Postulante> getPostulantes() { return postulantes; }
    
    public void setRequisitos(Competencia[] nuevosRequisitos) {
    this.requisitos = nuevosRequisitos;
}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Puesto: ").append(titulo).append(" en ").append(empresa)
          .append(" | Ciudad: ").append(ciudad).append("\nRequisitos:\n");
        for (Competencia c : requisitos) {
            sb.append("  - ").append(c).append("\n");
        }
        sb.append("Postulantes:\n");
        for (Postulante p : postulantes) {
            sb.append("  * ").append(p.getNombre()).append(" (").append(p.getRut()).append(")\n");
        }
        return sb.toString();
    }
}
