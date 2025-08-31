package bolsatrabajos;

import java.util.HashMap;
import java.util.Map;

public class Postulante {
    private String rut;
    private String nombre;
    private String ciudad;
    private String numeroContacto;
    private Map<String, Nivel> competencias;

    public Postulante(String rut, String nombre, String ciudad, String numeroContacto) {
        this.rut = rut;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.numeroContacto = numeroContacto;
        this.competencias = new HashMap<>();
    }
    
    public String getRut() { return rut; }
    public String getNombre() { return nombre; }
    public String getCiudad() { return ciudad; }
    public String getNumeroContacto() { return numeroContacto; }
    public Map<String, Nivel> getCompetencias() { return competencias; }

    public void setRut(String rut) {this.rut = rut;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setCiudad(String ciudad) {this.ciudad = ciudad;}
    public void setNumeroContacto(String numeroContacto) {this.numeroContacto = numeroContacto;}
    public void setCompetencias(Map<String, Nivel> competencias) {this.competencias = competencias;}

    public void agregarCompetencia(String nombre, Nivel nivel) {competencias.put(nombre, nivel);}
    
    public void agregarCompetencia(Competencia competenciaNueva) {
        if (competenciaNueva != null) {
            competencias.put(competenciaNueva.getNombreCompetencia(), competenciaNueva.getNivelCompetencia());
        }
    }
    
    public boolean tieneCompetenciaConNivelMinimo(String nombre, Nivel nivelRequerido) {
        Nivel nivelPostulante = competencias.get(nombre);

        if (nivelPostulante == null) {
            return false;
        }
        return nivelPostulante.esMayorOIgual(nivelRequerido);
    }
    
    public void eliminarCompetencia(String nombreCompetencia) {competencias.remove(nombreCompetencia);}

    public Nivel buscarCompetencia(String nombreCompetencia) {return competencias.get(nombreCompetencia);}

    public void mostrarCompetencias() {System.out.println("Competencias de " + nombre + ": " + competencias);}

    @Override
    public String toString() {
        return "Postulante: " + nombre + " [" + rut + "]\n" +
               "Ciudad: " + ciudad + "\n" +
               "Contacto: " + numeroContacto + "\n" +
               "Competencias: " + competencias + "\n";
    }
}



