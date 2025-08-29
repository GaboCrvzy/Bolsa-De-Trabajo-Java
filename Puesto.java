package bolsatrabajos;

import java.util.ArrayList;

public class Puesto {
    private String id;
    private String titulo;
    private String empresa;
    private String ciudad;
    private ArrayList<Postulante> postulantes; // segunda colección (anidada)
    private String[] requisitos; // formato: "Java:INTERMEDIO"

    public Puesto(String id, String titulo, String empresa, String ciudad, String[] requisitos) {
        this.id = id;
        this.titulo = titulo;
        this.empresa = empresa;
        this.ciudad = ciudad;
        this.postulantes = new ArrayList<>();
        this.requisitos = (requisitos == null) ? new String[0] : requisitos.clone();
    }

    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getEmpresa() { return empresa; }
    public String getCiudad() { return ciudad; }
    public ArrayList<Postulante> getPostulantes() { return postulantes; }
    public String[] getRequisitos() { return requisitos.clone(); }

    public void agregarPostulante(Postulante nuevoPostulante) {
        if (nuevoPostulante != null) postulantes.add(nuevoPostulante);
    }

    private Nivel parseNivel(String nivelTexto) 
    {
        if (nivelTexto == null) return null;
        try {return Nivel.valueOf(nivelTexto.trim().toUpperCase());
        } catch (IllegalArgumentException exception) {return null;}
    }

    public boolean cumpleTodosLosRequisitos(Postulante postulanteSeleccionado) {
    if (requisitos == null || requisitos.length == 0) return true;

    for (String req : requisitos) {
        if (req == null || req.trim().isEmpty()) continue;

        String[] partes = req.split(":", 2);
        if (partes.length != 2) {
            System.out.println("Requisito mal formado (debe ser 'nombre:NIVEL'): '" + req + "'");
            return false;
        }
        
        String nombreReq = partes[0].trim();
        String nivelTxt = partes[1].trim();
        Nivel nivelReq = parseNivel(nivelTxt); 
        
        if (nivelReq == null) {
            System.out.println("Nivel inválido en requisito: '" + req + "'");
            return false; 
        }
        if (!postulanteSeleccionado.tieneCompetenciaConNivelMinimo(nombreReq, nivelReq)) {
            return false;
        }
    }
    return true;
}


    // Retorna lista de postulantes que cumplen requisitos (usa ArrayList)
    public ArrayList<Postulante> seleccionarPostulantes() {
        ArrayList<Postulante> seleccion = new ArrayList<>();
        for (Postulante p : postulantes) {
            if (cumpleTodosLosRequisitos(p)) seleccion.add(p);
        }
        return seleccion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Puesto: ").append(titulo).append(" - ").append(empresa).append(" (").append(ciudad).append(")\n");
        sb.append("Requisitos: ");
        if (requisitos == null || requisitos.length == 0) sb.append("[]");
        else {
            sb.append("[");
            for (int i = 0; i < requisitos.length; i++) {
                sb.append(requisitos[i]);
                if (i < requisitos.length - 1) sb.append(", ");
            }
            sb.append("]");
        }
        sb.append("\nPostulantes inscritos: ").append(postulantes.size());
        return sb.toString();
    }
}
