
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
    
    //HACER GETTER Y SETTER CON VALIDACIONES
    
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
    //AGG POSTULANTE , ELIMINAR , BUSCAR ,MOSTRAR PUESTO
    //AGG REQ, ELIMINAR REQ, MOSTRAR REQ
    //SELECIONAR POSTULANTES (FILTRAR POR REQUISITOS Y HABILIDADES) AUTOMATICAMNTE DE ACUERDO AL CRITERIO

    
    
    
    
    
    
    
}
