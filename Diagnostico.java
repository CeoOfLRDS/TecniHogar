public class Diagnostico extends Servicio {
    private double maxHoras;
    private double costoMet;
    private double costoPer;

    public Diagnostico() {
        this.nombre = "Diagnóstico";
        this.maxHoras = 1.5;
        this.costoMet = Configuracion.tarifaBaseDiagnosticoMet;
        this.costoPer = Configuracion.tarifaBaseDiagnosticoPer;
    }

    @Override
    public double calcularCosto(double horas, Zona zona, double materiales) {
        if (horas > maxHoras) {
            throw new IllegalArgumentException("El diagnóstico excede el máximo permitido de 1.5 horas.");
        }
        return (zona == Zona.METROPOLITANA) ? costoMet : costoPer;
    }

    @Override
    public int diasEstimados(Zona zona) { 
        return (zona == Zona.METROPOLITANA) ? 1 : 2; 
    }
    
    @Override
    public int maxVisitas() { 
        return 3; 
    }
}