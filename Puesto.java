package bolsatrabajos;

import java.util.ArrayList;

public class Puesto {
    private String id;
    private String titulo;
    private String empresa;
    private String ciudad;
    private Competencia[] requisitos;
    private ArrayList<Postulante> postulantes;

    public Puesto(String id, String titulo, String empresa, String ciudad, Competencia[] requisitos) 
    {
        this.id = id;
        this.titulo = titulo;
        this.empresa = empresa;
        this.ciudad = ciudad;
        this.requisitos = requisitos != null ? requisitos : new Competencia[0];
        this.postulantes = new ArrayList<>();
    }

    
    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getEmpresa() { return empresa; }
    public String getCiudad() { return ciudad; }
    public Competencia[] getRequisitos() { return requisitos; }
    public ArrayList<Postulante> getPostulantes() { return postulantes; }
    
    public void setId(String id) {this.id = id;}
    public void setTitulo(String titulo) {this.titulo = titulo;}
    public void setEmpresa(String empresa) {this.empresa = empresa;}
    public void setCiudad(String ciudad) {this.ciudad = ciudad;}
    public void setPostulantes(ArrayList<Postulante> postulantes){this.postulantes = postulantes;}
    public void setRequisitos(Competencia[] nuevosRequisitos){this.requisitos = nuevosRequisitos;}
    
    public void agregarPostulante(Postulante nuevoPostulante) {postulantes.add(nuevoPostulante);}
    
    public boolean cumpleTodosLosRequisitos(Postulante candidato)
    {
        for (Competencia req : requisitos){
            if (!candidato.tieneCompetenciaConNivelMinimo(req.getNombreCompetencia(), req.getNivelCompetencia())) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Postulante> seleccionarPostulantes() {
        ArrayList<Postulante> seleccion = new ArrayList<>();
        for (Postulante candidato: postulantes) {
            if (cumpleTodosLosRequisitos(candidato)) {
                seleccion.add(candidato);
            }
        }
        return seleccion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Puesto: ").append(titulo).append(" en ").append(empresa).append(" | Ciudad: ").append(ciudad).append("\nRequisitos:\n");
        for (Competencia c : requisitos) {
            sb.append("  - ").append(c).append("\n");
        }
        sb.append("Postulantes:\n");
        for (Postulante postulantes : postulantes) {
            sb.append("  * ").append(postulantes.getNombre()).append(" (").append(postulantes.getRut()).append(")\n");
        }
        return sb.toString();
    }
}


