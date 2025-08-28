package bolsatrabajos;

public class BolsaTrabajos {
    public static void main(String[] args) {
        // Competencias
        Competencia javaAvanzado = new Competencia("Java", Nivel.AVANZADO);
        Competencia sqlIntermedio = new Competencia("SQL", Nivel.INTERMEDIO);
        Competencia javaBasico = new Competencia("Java", Nivel.BASICO);

        // Postulantes
        Postulante postulanteUno = new Postulante("217104491", "Gabriel Fuentes", "Valparaiso", "949033564");
        Postulante postulanteDos = new Postulante("214486320", "Alexis Escobar", "Santiago", "976321235");

        postulanteUno.getCompetencias().add(javaAvanzado);
        postulanteUno.getCompetencias().add(sqlIntermedio);

        postulanteDos.getCompetencias().add(sqlIntermedio);
        postulanteDos.getCompetencias().add(javaBasico);

        // Puesto disponible
        Puesto puestoDisponibleUno = new Puesto("1234", "PROGRAMADOR", "BANCO DE CHILE", "Viña del Mar");

        puestoDisponibleUno.getRequisitos().add(new Competencia("Java", Nivel.INTERMEDIO));
        puestoDisponibleUno.getRequisitos().add(new Competencia("SQL", Nivel.INTERMEDIO));

        // Relacionar postulantes al puesto
        puestoDisponibleUno.getPostulantes().add(postulanteUno);
        puestoDisponibleUno.getPostulantes().add(postulanteDos);

        System.out.println(puestoDisponibleUno);
    }
}
