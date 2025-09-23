
package proyectopoo;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.LinkedList;

public class Puesto {
    private String idPuesto, nombrePuesto, descripcionPuesto;    
    private LinkedList<Persona> postulantes;
    private ArrayList<Competencia> requisitos; 

    public Puesto(String idPuesto, String nombrePuesto, String descripcionPuesto)
    {
        this.idPuesto = idPuesto;
        this.nombrePuesto = nombrePuesto;
        this.descripcionPuesto = descripcionPuesto;
        postulantes = new LinkedList<>();
        requisitos =  new ArrayList<>();
    }

    public String getIdPuesto() {return idPuesto;}
    public String getNombrePuesto() {return nombrePuesto;}
    public String getDescripcionPuesto() {return descripcionPuesto;}
    
    //VALIDAR QUE ID PUESTO SEA SOLO NROS
    public void setIdPuesto(String idPuesto)
    {
        if(idPuesto == null)
        {
            System.out.println("ID PUESTO INVALIDO (NULO)");
            return;
        }
        
        String soloDig = idPuesto.trim().replaceAll("[^0-9]", "");
        if(!soloDig.matches("\\d+"))
        {
            System.out.println("ID PUESTO inválido. Debe contener solo dígitos.");
            return;
        }
        this.idPuesto = soloDig;
    }

    //VALIDAR NOMBRE DEL PUESTO SEA SOLO LETRAS
    public void setNombrePuesto(String nombrePuesto) 
    {
        if(nombrePuesto == null)
        {
            System.out.println("NOMBRE PUESTO INVALIDO (NULO)");
            return;
        }
        
        String norm = Normalizer.normalize(nombrePuesto.trim().toUpperCase(), Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        norm = norm.replaceAll("[^A-Z ]", "");
        
        if(!norm.matches("^[A-Z ]+$"))
        {
            System.out.println("NOMBRE PUESTO INVALIDO. Debe contener sólo letras y espacios (sin números ni símbolos).");
            return;
        }
        this.nombrePuesto = norm;
    }
    
    public void setDescripcionPuesto(String descripcionPuesto) 
    {
        if(descripcionPuesto == null)
        {
            System.out.println("DESCRIPCION INVALIDA (NULO)");
            return;
        }
        
        String desc = descripcionPuesto.trim();
        if (desc.isEmpty()) 
        {
            System.out.println("DESCRIPCION INVALIDA (VACIA)");
            return;
        }
        
        final int MAX_LEN = 500;
        if (desc.length() > MAX_LEN) 
        {
            System.out.println("DESCRIPCION MUY LARGA. Se truncará a " + MAX_LEN + " caracteres.");
            desc = desc.substring(0, MAX_LEN).trim();
        }
        this.descripcionPuesto = desc;
    }
    
    public void agregarPostulante(Persona candidato)
    {
        if(candidato == null)
        {
            System.out.println("POSTULANTE INVALIDO (NULO)");
            return;
        }
        
        for (Persona actual : postulantes) 
        {
            if (actual != null && candidato.getRut() != null && actual.getRut().equals(candidato.getRut()))
            {
                System.out.println("EL POSTULANTE YA EXISTE: " + candidato.getRut());
                return;
            }
        }
        postulantes.add(candidato);
        System.out.println("Postulante agregado: " + candidato.getRut());
    }
    
    public boolean eliminarPostulantePorRut(String rut) 
    {
        if (rut == null) return false;
        return postulantes.removeIf(actual -> actual != null && rut.equals(actual.getRut()));
    }
    
    public Persona buscarPostulante(String rut)
    {
        if (rut == null) return null;
        for (Persona actual : postulantes) 
        {
            if (actual != null && rut.equals(actual.getRut())) return actual;
        }
        return null;
    }
    
    public void mostrarPuesto() 
    {
        System.out.println("ID Puesto: " + idPuesto);
        System.out.println("Nombre Puesto: " + nombrePuesto);
        System.out.println("Descripción: " + descripcionPuesto);

        System.out.println("\nPostulantes:");
        if (postulantes.isEmpty()) {
            System.out.println("No hay postulantes.");
        }   else {
            for (Persona p : postulantes) {
                if (p != null) {
                    System.out.println("RUT: " + p.getRut() + ", Nombre: " + p.getNombre());
                }
            }
        }
        System.out.println("\nRequisitos:");
        if (requisitos.isEmpty()) {
            System.out.println("No hay requisitos.");
        }   else {
            for (Competencia c : requisitos) {
                if (c != null) {
                    String nom = (c.getNombre() != null) ? c.getNombre() : "?";
                    String niv = (c.getNivel() != null) ? c.getNivel() : "?";
                    System.out.println("- " + nom + " (" + niv + ")");
                }
            }
    }
    }
    
    //AGG CON SOBRECARGA
    public void agregarRequisito(Competencia req)
    {
        if(req == null)
        {
            System.out.println("REQUISITO INVALIDO (NULO)");
            return;
        }
        
        if(req.getNivel() == null || !req.esNivelValido(req.getNivel()))
        {
            System.out.println("REQUISITO INVALIDO: nivel inválido para '" + req.getNombre() + "'.");
            return;
        }
        
        for (int i = 0; i < requisitos.size(); i++)
        {
            Competencia actual = requisitos.get(i);
            if (actual == null || actual.getNombre() == null) continue;
            String nombreActual = actual.getNombre();
            
            if (nombreActual.equals(req.getNombre())) 
            {
                int rankNuevo = req.nivelToRank(req.getNivel());
                int rankActual = actual.nivelToRank(actual.getNivel());
                if (rankNuevo > rankActual) 
                {
                    requisitos.set(i, req);
                    System.out.println("Requisito actualizado: " + req.getNombre() + " (" + req.getNivel() + ")");
                }  else {
                    System.out.println("No se actualizó el requisito. El nivel existente es igual o superior.");
                }
                return;
            }
        }
        requisitos.add(req);
        System.out.println("Requisito agregado: " + req.getNombre() + " (" + req.getNivel() + ")");
    }
        
    public void agregarRequisito(String nombre, String nivel)
    {
        if(nombre == null || nivel == null)
        {
            System.out.println("REQUISITO INVALIDO (NULO)");
            return;
        }
        Competencia nueva = new Competencia(nombre, nivel);
        agregarRequisito(nueva);
    }
    
    //ElIMINA REQ Y DEVUELVE BOOLEAN SI LO HACE
    public boolean eliminarReq(String nombre)
    {
        if(nombre == null) return false;
        
        String nombreNorm = java.text.Normalizer.normalize(nombre.trim().toUpperCase(), java.text.Normalizer.Form.NFD).replaceAll("\\p{M}", "").replaceAll("[^A-Z0-9 ]", "");
        
        if (nombreNorm.isEmpty()) {
            System.out.println("NOMBRE INVALIDO TRAS NORMALIZAR");
            return false;
        }

        java.util.Iterator<Competencia> it = requisitos.iterator();
        while (it.hasNext()) 
        {
            Competencia actual = it.next();
            if (actual == null || actual.getNombre() == null) continue;

            if (actual.getNombre().equals(nombreNorm))
            {
                it.remove();
                System.out.println("Requisito eliminado: " + actual.getNombre() + " (" + actual.getNivel() + ")");
                return true;
            }
        }
        System.out.println("No se encontró requisito con nombre: " + nombre);
        return false;   
    }
    
    public void mostrarRequisitos() 
    {
        System.out.println("Requisitos del puesto " + nombrePuesto + " (ID: " + idPuesto + "):");

        if (requisitos == null || requisitos.isEmpty()) {
            System.out.println(" - No hay requisitos.");
            return;
        }

        for (int i = 0; i < requisitos.size(); i++) {
            Competencia c = requisitos.get(i);
            if (c == null) continue;
            System.out.println(" " + (i + 1) + ". " + c); // imprime c.toString()
        }
    }
    
    //SELECIONAR POSTULANTES (FILTRAR POR REQUISITOS Y HABILIDADES) AUTOMATICAMNTE DE ACUERDO AL CRITERIO QUE NECESITE EL PUESTO
    public LinkedList<Persona> seleccionarPostulantes() 
    {
        LinkedList<Persona> seleccion = new LinkedList<>();

        if (postulantes == null || postulantes.isEmpty()) return seleccion;
        if (requisitos == null || requisitos.isEmpty())
        {
            seleccion.addAll(postulantes);
            return seleccion;
        }

        for (Persona p : postulantes)
        {
            if (p == null) continue;
            boolean cumpleTodos = true;
            
            for (Competencia req : requisitos) 
            {
                if (req == null) continue;
                if (!p.tieneCompetenciaConNivelMinimo(req.getNombre(), req.getNivel())) {
                    cumpleTodos = false;
                    break;
                }
            }
            if (cumpleTodos) seleccion.add(p);
        }
        return seleccion;
    } 
}

