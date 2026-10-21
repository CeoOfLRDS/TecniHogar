public class Instalacion extends Servicio {
    private int puntos;
    private double tarifaBaseMet;
    private double tarifaBasePer;

    public Instalacion(int puntos) {
        this.nombre = "Instalación";
        this.puntos = puntos;
        this.tarifaBaseMet = Configuracion.tarifaBaseInstalacionMet;
        this.tarifaBasePer = Configuracion.tarifaBaseInstalacionPer;
    }

    @Override
    public double calcularCosto(double horas, Zona zona, double materiales) {
        double horasFacturables = Math.ceil(Math.max(horas, puntos * 0.75));
        double base = (zona == Zona.METROPOLITANA) ? tarifaBaseMet : tarifaBasePer;
        return base + (90.0 * horasFacturables);
    }

    @Override
    public int diasEstimados(Zona zona) { 
        return (zona == Zona.METROPOLITANA) ? 3 : 5; 
    }
    
    @Override
    public int maxVisitas() { 
        return 3; 
    }
}