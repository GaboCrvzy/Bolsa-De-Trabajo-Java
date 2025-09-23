
package proyectopoo;

import java.text.Normalizer;

public class Competencia {
    private String nombre;
    private String nivel; //SOLO BASICO(1) , INTERMEDIO(2) , AVANZADO(3)
    
    //CONSTRUCTOR CON PARAMETROS
    public Competencia(String nombre, String nivel){
        this.nombre = nombre;
        this.nivel = nivel;
    }
    
    // CONSTRUCTOR SIN PARAMETROS
    public Competencia(){
        nombre = null;
        nivel = null;
    }

    public String getNombre(){return nombre;}

    public void setNombre(String nombre){
        if(nombre == null) return;
        this.nombre = normalizarName(nombre);
    }

    public String getNivel(){return nivel;}

    //SI EL NIVEL ES INVALIDO SE ASIGNA NULL
    public void setNivel(String nivel)
    {
        if(nivel == null)
        {
            System.out.println("Ningun nivel ingresado correctamente");
            this.nivel = null;
            return;
        }
        
        if(esNivelValido(nivel)){this.nivel = normalizarName(nivel);}
        else
        {
            System.out.println("Ningun nivel ingresado correctamente");
            this.nivel = null;
        }
    }
    
    //NORMALIZA LOS STRING SOLO A MAYUSCULAS SIN TILDES 
    public String normalizarName(String name) 
    {
        if (name == null) return "";
        String aux = name.trim().toUpperCase();
        // quitar tildes
        aux = Normalizer.normalize(aux, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        aux = aux.replaceAll("[^A-Z0-9 ]", "");
        return aux;
    }   
    
    // COMPRUEBA SI EL NIVEL ES VALIDO
    public boolean esNivelValido(String nivel) {
        if (nivel == null) return false;
        String aux = normalizarName(nivel);
        return "BASICO".equals(aux) || "INTERMEDIO".equals(aux) || "AVANZADO".equals(aux);
    }
    
    // CONVIERTE NIVEL A RANK PARA COMPARAR
    public int nivelToRank(String nivel) 
    {
        if (nivel == null) return 0;
        switch (normalizarName(nivel)) {
            case "BASICO": return 1;
            case "INTERMEDIO": return 2;
            case "AVANZADO": return 3;
            default: return 0;
        }
    }
    
    // COMPROBAR SI EL NIVEL DE LA COMPETENCIA ACTUAL CUMPLE CON NIVEL MINIMO DEL STRING PUESTO
    public boolean cumpleCon(String nivelMinimo) 
    {
        if (!esNivelValido(this.nivel)) return false;
        if (!esNivelValido(nivelMinimo)) return false;
        return nivelToRank(this.nivel) >= nivelToRank(nivelMinimo);
    }
    
    // SOBRECARGA METODOS 
    public boolean cumpleCon(Competencia otra)
    {
        if (otra == null) return false;
        return cumpleCon(otra.getNivel());
    }
    
    // Compara niveles de dos competencias: -1 si actual < otra, 0 igual, +1 if actual > otra
    public int compareNivel(Competencia otra)
    {
        if (otra == null) return 1; // Actua es "mayor" que null
        int r1 = nivelToRank(this.nivel);
        int r2 = nivelToRank(otra.getNivel());
        return Integer.compare(r1, r2);
    }

    @Override
    public String toString(){return nombre + " (" + nivel + ")";}
}
    


