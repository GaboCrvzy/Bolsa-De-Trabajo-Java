package proyectopoo;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase Persona (Postulante).
 * Guarda rut, nombre, ciudad, contacto y lista de competencias/habilidades.
 */
public class Persona {
    private String rut;
    private String nroContacto;
    private String nombre; // NOMBRE + APELLIDO 
    private String ciudad; 
    private ArrayList<Competencia> habilidades;


    public Persona(String rut, String nombre, String ciudad, String nroContacto) {
        this.habilidades = new ArrayList<>();
        setRut(rut);
        setNombre(nombre);
        setCiudad(ciudad);
        setNroContacto(nroContacto);
    }

    public String getRut() { return rut; }

    public void setRut(String rut) {
        if (rut == null) {
            System.out.println("RUT INVALIDO (NULO).");
            return;
        }
        String soloNros = rut.trim().replaceAll("[^0-9]", "");
        if (!soloNros.matches("\\d{7,9}")) { 
            System.out.println("RUT inválido. Debe contener 7 a 9 dígitos, SIN PUNTOS NI GUIÓN.");
            return;
        }
        this.rut = soloNros;
    }

    public String getNroContacto() { return nroContacto; }

    public void setNroContacto(String nroContacto) {
        if (nroContacto == null) {
            System.out.println("NUMERO DE CONTACTO INVALIDO (NULO)");
            return;
        }
        String soloNros = nroContacto.trim().replaceAll("[^0-9]", "");
        if (!soloNros.matches("\\d{8,9}")) { // 8 o 9 dígitos (ajusta si tu país exige 9)
            System.out.println("NUMERO DE CONTACTO INVALIDO. DEBE CONTENER 8 o 9 dígitos.");
            return;
        }
        this.nroContacto = soloNros;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) {
        if (nombre == null) {
            System.out.println("EL NOMBRE INGRESADO ES INVALIDO (NULO)");
            return;
        }
        String aux = normalizarNamePersona(nombre);
        if (!aux.matches("^[A-Z ]+$")) {
            System.out.println("NOMBRE INVALIDO. DEBE CONTENER SOLO LETRAS Y ESPACIOS (SIN NUMEROS).");
            return;
        }
        this.nombre = aux;
    }

    public String getCiudad() { return ciudad; }

    public void setCiudad(String ciudad) {
        if (ciudad == null) {
            this.ciudad = null;
            return;
        }
        String aux = normalizarNamePersona(ciudad);
        if (!aux.matches("^[A-Z ]+$")) {
            System.out.println("CIUDAD INVALIDA. Se mantiene valor anterior.");
            return;
        }
        this.ciudad = aux;
    }

    //NORMALIZA LOS STRING SOLO A MAYUSCULAS SIN TILDES 
    public String normalizarNamePersona(String name) {
        if (name == null) return "";
        String aux = name.trim().toUpperCase();
        aux = Normalizer.normalize(aux, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        aux = aux.replaceAll("[^A-Z0-9 ]", "");
        return aux;
    }

    public int getCantHabilidades() { return habilidades.size(); }

    public List<Competencia> getHabilidades() {
        return new ArrayList<>(habilidades);
    }

    @Override
    public String toString() {
        return (nombre == null ? "<sin nombre>" : nombre) + " " +
               (rut == null ? "" : ("[" + rut + "]")) +
               " - Contacto: " + (nroContacto == null ? "<sin contacto>" : nroContacto) +
               " | Habilidades: " + getCantHabilidades();
    }

   
    public void agregarHabilidad(Competencia nuevaCompe) {
        if (nuevaCompe == null) {
            System.out.println("NO SE PUEDE AGREGAR: COMPETENCIA NULA");
            return;
        }
        if (nuevaCompe.getNivel() == null) {
            System.out.println("No se puede agregar: nivel inválido para la competencia '" + nuevaCompe.getNombre() + "'.");
            return;
        }

        String nombreNorm = normalizarNamePersona(nuevaCompe.getNombre());

        for (int i = 0; i < habilidades.size(); i++) {
            Competencia actual = habilidades.get(i);
            if (actual == null || actual.getNombre() == null) continue;
            if (normalizarNamePersona(actual.getNombre()).equals(nombreNorm)) {
                int nuevoNivel = nuevaCompe.nivelToRank(nuevaCompe.getNivel());
                int nivelActual = actual.nivelToRank(actual.getNivel());
                if (nuevoNivel > nivelActual) {
                    habilidades.set(i, nuevaCompe);
                    System.out.println("Competencia actualizada para " + this.rut + " : " + nuevaCompe);
                } else {
                    System.out.println("No se actualizó. Nivel existente es igual o superior.");
                }
                return;
            }
        }
        habilidades.add(nuevaCompe);
        System.out.println("Competencia agregada a " + this.rut + ": " + nuevaCompe);
    }
    
    //SOBRECARGA AGG HABILIDAD
    public void agregarHabilidad(String nombreComp, String nivel) 
    {
        if (nombreComp == null || nivel == null) {
            System.out.println("Nombre o nivel nulo. No se agregó la competencia.");
            return;
        }
        Competencia nuevaCompetencia = new Competencia(nombreComp, nivel);

        if (nuevaCompetencia.getNivel() == null) {
            System.out.println("No se agregó la competencia. Nivel inválido: " + nivel);
            return;
        }
        agregarHabilidad(nuevaCompetencia);
    }

    public Competencia buscarPorNombreHab(String nombre) {
        if (nombre == null) {return null;}
        String buscadoNorm = normalizarNamePersona(nombre);
        if (habilidades == null || habilidades.isEmpty()) return null;

        for (Competencia actual : habilidades) {
            if (actual == null || actual.getNombre() == null) continue;
            if (normalizarNamePersona(actual.getNombre()).equals(buscadoNorm)) {return actual;}
        }
        return null;
    }

    
    public ArrayList<Competencia> buscarHabPorNivel(String nivel) {
        ArrayList<Competencia> resultado = new ArrayList<>();
        if (nivel == null) return resultado;

        String nivelNorm = normalizarNamePersona(nivel);
        for (Competencia actual : habilidades) {
            if (actual == null || actual.getNivel() == null) continue;
            if (normalizarNamePersona(actual.getNivel()).equals(nivelNorm)) {
                resultado.add(actual);
            }
        }
        return resultado;
    }
 
    public boolean tieneHabilidadConNivelMinimo(String nombreComp, String nivelMinimo) {
        if (nombreComp == null || nivelMinimo == null) return false;
        Competencia c = buscarPorNombreHab(nombreComp);
        if (c == null) return false;
        return c.cumpleCon(nivelMinimo);
    }

    public boolean tieneHabilidad(String nombre) {
        if (nombre == null) return false;
        if (habilidades == null || habilidades.isEmpty()) return false;

        String nomNorm = normalizarNamePersona(nombre);
        for (Competencia actual : habilidades) {
            if (actual == null || actual.getNombre() == null) continue;
            if (normalizarNamePersona(actual.getNombre()).equals(nomNorm)) return true;
        }
        return false;
    }

    public boolean eliminarHabilidad(String nombre)
    {
        if (nombre == null) {
            System.out.println("NOMBRE INVALIDO (NULO)");
            return false;
        }
        String nombNorm = normalizarNamePersona(nombre);
        Iterator<Competencia> it = habilidades.iterator();
        while (it.hasNext()) {
            Competencia actual = it.next();
            if (actual == null || actual.getNombre() == null) continue;
            if (normalizarNamePersona(actual.getNombre()).equals(nombNorm)) {
                it.remove();
                System.out.println("SE ELIMINO LA HABILIDAD: " + nombre);
                return true;
            }
        }
        System.out.println("NO SE PUDO ELIMINAR LA HABILIDAD: " + nombre);
        return false;
    }

    public void mostrarHabilidades() {
        if (habilidades == null || habilidades.isEmpty()) {
            System.out.println("La persona no tiene habilidades registradas.");
            return;
        }
        System.out.println("Habilidades de " + (nombre == null ? "<sin nombre>" : nombre) + " (" + (rut == null ? "" : rut) + "):");
        for (int i = 0; i < habilidades.size(); i++) {
            Competencia actual = habilidades.get(i);
            if (actual != null) {
                System.out.println(" " + (i + 1) + ". " + actual); // usa toString() de Competencia
            }
        }
    }
    
    public boolean tieneCompetenciaConNivelMinimo(String nombreComp, String nivelMinimo) {
        return tieneHabilidadConNivelMinimo(nombreComp, nivelMinimo);
    }
}

