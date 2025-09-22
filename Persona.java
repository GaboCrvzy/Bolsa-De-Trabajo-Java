
package proyectopoo;

import java.text.Normalizer;
import java.util.ArrayList;

public class Persona {
    private String rut, nroContacto;
    private String nombre; //NOMBRE + APELLIDO
    private ArrayList<Competencia> habilidades; //Se usa size() para recorrerlo

    public Persona(String rut, String nroContacto, String nombre)
    {
        this.rut = rut;
        this.nroContacto = nroContacto;
        this.nombre = nombre;
        this.habilidades = new ArrayList<>();
    }

    public String getRut() {return rut;}

    public void setRut(String rut) {
        if(rut == null)
        {
            System.out.println("RUT INVALIDO (NULO).");
            return;
        }
        String soloNros = rut.trim().replaceAll("[^0-9]", "");
        if(!soloNros.matches("\\d{9}"))
        {
            System.out.println("RUT inválido. Debe contener 9 dígitos, SIN PUNTOS NI GUION.");
            return;
        }
        this.rut = soloNros;
    }

    public String getNroContacto() {return nroContacto;}

    public void setNroContacto(String nroContacto){
         if(nroContacto == null)
         {
             System.out.println("NUMERO DE CONTACTO INVALIDO (NULO)");
             return;
         }
         
         String soloNros = nroContacto.trim().replaceAll("[^0-9]", "");
         if(!soloNros.matches("\\d{9}"))
         {
            System.out.println("NUMERO DE CONTACTO INVALIDO. DEBE CONTENER 9 dígitos.");
            return;
         }
        this.nroContacto = soloNros;
    }

    public String getNombre() {return nombre;}
    
    public void setNombre(String nombre){
        if(nombre == null)
        {
            System.out.println("EL NOMBRE INGRESADO ES INVALIDO (NULO)");
            return;
        }
        String aux = normalizarNamePersona(nombre);
        this.nombre = aux;
    }
    
    //NORMALIZA LOS STRING SOLO A MAYUSCULAS SIN TILDES 
    public String normalizarNamePersona(String name) 
    {
        if (name == null) return "";
        String aux = name.trim().toUpperCase();
        // quitar tildes
        aux = Normalizer.normalize(aux, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        aux = aux.replaceAll("[^A-Z0-9 ]", "");
        return aux;
    } 
    
    public int getCantHabilidades() { return habilidades.size();}
    public ArrayList<Competencia> getHabilidades() { return new ArrayList<>(habilidades);}

    @Override
    public String toString() {
        return nombre + " " + rut + " - Contacto: " + nroContacto + " | Numero De Habilidades: " + getCantHabilidades();
    }
    
    //AGG METODOS DE AGG COMPETENCIA, ELIMINAR COMPETENCIA , BUSCAR COMPETENCIA, MOSTRAR COMPETENCIAS
    
    //AGG COMPETENCIA YA CREADA
    public void agregarHabilidad(Competencia nuevaCompe)
    {
        if(nuevaCompe == null)
        {
            System.out.println("NO SE PUEDE AGREGAR: COMPETENCIA NULA");
            return;
        }
        if(nuevaCompe.getNivel() == null)
        {
            System.out.println("No se puede agregar: nivel inválido para la competencia '" + nuevaCompe.getNombre() + "'.");
            return;
        }
        
        String nombreNorm = normalizarNamePersona(nuevaCompe.getNombre());
        
        for(int i = 0; i < habilidades.size(); i++)
        {
            Competencia actual = habilidades.get(i);
            
            if(actual != null && normalizarNamePersona(actual.getNombre()).equals(nombreNorm))
            {
                //YA EXISTE POR ENDE ACTUALIZAMOS SEGUN CRITERIOS
                int nuevoNivel = nuevaCompe.nivelToRank(nuevaCompe.getNivel());
                int nivelActual = actual.nivelToRank(actual.getNivel());
                
                if(nuevoNivel > nivelActual)
                {
                    habilidades.set(i, actual);
                    System.out.println("Competencia actualizada para " + this.rut + " : " + nuevaCompe);
                }
                else System.out.println("No se actualizó. Nivel existente es igual o superior.");
                return;
            }
        }
        //No existe, agg
        habilidades.add(nuevaCompe);
        System.out.println("Competencia agregada a " + this.rut + ": " + nuevaCompe); 
    }
    
    
    
