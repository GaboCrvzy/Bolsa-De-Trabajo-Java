package bolsatrabajos;

import java.util.HashMap;
import java.util.Map;

public class Postulante {
    private String rut;
    private String nombre;
    private String ciudad;
    private String contacto;
    private final Map<String, Nivel> competencias; // tercera colección

    public Postulante(String rut, String nombre, String ciudad, String contacto) {
        this.rut = rut;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.contacto = contacto;
        this.competencias = new HashMap<>();
    }

    // getters / setters básicos
    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getContacto() { return contacto; }
    public void setContacto(String contacto) { this.contacto = contacto; }

    public void agregarCompetencia(String nombreCompetencia, Nivel nivel) {
        if (nombreCompetencia == null || nivel == null) return;
        competencias.put(nombreCompetencia.toLowerCase(), nivel);
    }

    public Nivel obtenerNivelCompetencia(String nombreCompetencia) {
        if (nombreCompetencia == null) return null;
        return competencias.get(nombreCompetencia.toLowerCase());
    }

    public boolean tieneCompetenciaConNivelMinimo(String nombreCompetencia, Nivel nivelMinimo) {
        Nivel n = obtenerNivelCompetencia(nombreCompetencia);
        if (n == null) return false;
        if (nivelMinimo == null) return true;
        return n.esMayorOIgual(nivelMinimo);
    }

    @Override
    public String toString() {
        return nombre + " [" + rut + "] " + competencias;
    }
}
