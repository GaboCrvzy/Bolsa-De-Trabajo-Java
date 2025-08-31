package bolsatrabajos;

public class Competencia {
    private String nombreCompetencia;
    private Nivel nivelCompetencia;

    public Competencia(String nombreCompetencia, Nivel nivelCompetencia) {
        this.nombreCompetencia = nombreCompetencia;
        this.nivelCompetencia = nivelCompetencia;
    }

    public String getNombreCompetencia() { return nombreCompetencia; }
    public Nivel getNivelCompetencia() { return nivelCompetencia; }

    public void setNombreCompetencia(String nombreCompetencia) { this.nombreCompetencia = nombreCompetencia; }
    public void setNivelCompetencia(Nivel nivelCompetencia) { this.nivelCompetencia = nivelCompetencia; }

    @Override
    public String toString() {
        return nombreCompetencia + " (" + nivelCompetencia + ")";
    }
}
