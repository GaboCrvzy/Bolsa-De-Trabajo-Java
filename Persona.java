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
    
    public int getCantHabilidades(){ return habilidades.size();}
    
    @Override
    public String toString() {
        return nombre + " " + rut + " - Contacto: " + nroContacto + " | Numero De Habilidades: " + getCantHabilidades();
    }
    
    
    //AGG COMPETENCIA YA CREADA SOBRECARGAR
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
                    habilidades.set(i, nuevaCompe);
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
    
    // Sobrecarga: crear y agregar desde nombre + nivel
    public void agregarHabilidad(String nombreComp, String nivel) 
    {
        Competencia nuevaCompetencia = new Competencia(nombreComp, nivel);
        if (nuevaCompetencia.getNivel() == null) {
            System.out.println("No se agregó la competencia. Nivel inválido para: " + nombreComp);
            return;
        }
        agregarHabilidad(nuevaCompetencia);
    }
    
    //METODO DE BUSCAR COMPETENCIA POR NOMBRE , NIVEL Y AMBAS
   public Competencia buscarPorNombreHab(String nombre) 
   {
    if (nombre == null)
    {
        System.out.println("NOMBRE INVALIDO (NULO)");
        return null;
    }
    // normalizamos
    String buscadoNorm = normalizarNamePersona(nombre);

    if (habilidades == null || habilidades.isEmpty()) 
    {
        System.out.println("COMPETENCIAS VACIAS");
        return null;
    }

    for (int i = 0; i < habilidades.size(); i++) {
        Competencia actual = habilidades.get(i);
        if (actual == null || actual.getNombre() == null) continue;

        String actualNorm = normalizarNamePersona(actual.getNombre());
        if (actualNorm.equals(buscadoNorm)) {
            System.out.println("COMPETENCIA '" + nombre + "' ENCONTRADA");
            return actual;
        }
    }
    System.out.println("COMPETENCIA '" + nombre + "' NO ENCONTRADA");
    return null;
}

    public ArrayList<Competencia> buscarHabPorNivel(String nivel)
    {
        ArrayList<Competencia> resultado = new ArrayList<>();
        if(nivel == null) return resultado;
        
        String nivelNorm = normalizarNamePersona(nivel); 
        for(int i = 0; i < habilidades.size(); i++)
        {
            Competencia actual = habilidades.get(i);
            if(actual != null && actual.getNivel()!= null && normalizarNamePersona(actual.getNivel()).equals(nivel))
            {
                resultado.add(actual);
            }
        }
        return resultado;
    }
    
    public boolean tieneHabilidadConNivelMinimo(String nombreComp, String nivelMinimo)
    {
        Competencia c = buscarPorNombreHab(nombreComp);
        if (c == null) return false;
        return c.cumpleCon(nivelMinimo);
    }
    
    public boolean tieneHabilidad(String nombre)
    {
        if(nombre == null) return false;
        
        if (habilidades == null || habilidades.isEmpty()) return false;
        
        String nomNorm = normalizarNamePersona(nombre);
        for(int i = 0; i < habilidades.size(); i++)
        {
            Competencia actual = habilidades.get(i);
            if(actual != null && actual.getNombre() != null && actual.getNombre().equals(nomNorm)) return true;
        }
        return false;
    }
   
    //ELIMINA LA HABILIDADES CN ESE NOMBRE SIN IMPORTAR EL NIVEL
    public boolean eliminarHabilidad(String nombre)
    {
        if(nombre == null)
        {
            System.out.println("NOMBRE INVALIDO (NULO)");
            return false;
        }
        
        String nombNorm = normalizarNamePersona(nombre);
        for(int i = 0; i < habilidades.size(); i++)
        {
            Competencia actual = habilidades.get(i);
            
            if(actual != null && actual.getNombre()!= null && actual.getNombre().equals(nombre)) 
            {
                habilidades.remove(actual);
                System.out.println("SE ELIMINO LA HABILIDAD :" + nombre);
                return true;

            }
        }
        System.out.println("NO SE PUEDO ELIMINAR LA HABILIDAD :" + nombre);
        return false;
    }
    
    //AGG METODO DE MOSTRAR LAS COMPETENCIAS (TODAS) 
    public void mostrarHabilidades()
    {
        if (habilidades.isEmpty()) 
        {
            System.out.println("La persona no tiene habilidades registradas.");
            return;
        }

        System.out.println("Habilidades de " + nombre + " (" + rut + "):");
        for (int i = 0; i < habilidades.size(); i++) 
        {
            Competencia actual = habilidades.get(i);
            if (actual != null) {
                System.out.println(" Comptencia : " + actual.getNombre() + " - (" + actual.getNivel() + ")");
            }
        }
    }
}
