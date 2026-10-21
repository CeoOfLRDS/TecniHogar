public class MantenimientoAire extends Servicio {
    private int capacidadBTU;
    private double tarifaBase;

    public MantenimientoAire(int capacidadBTU) {
        if (capacidadBTU < 9000 || capacidadBTU > 60000) {
            throw new IllegalArgumentException("Capacidad BTU fuera de rango admisible (9,000 - 60,000).");
        }
        this.nombre = "Mantenimiento Aire Acondicionado";
        this.capacidadBTU = capacidadBTU;
        this.tarifaBase = Configuracion.tarifaBaseAire;
    }

    @Override
    public double calcularCosto(double horas, Zona zona, double materiales) {
        if (zona != Zona.METROPOLITANA) {
            throw new IllegalArgumentException("Mantenimiento de aire solo disponible en zona metropolitana.");
        }
        return tarifaBase + (110.0 * horas) + 80.0;
    }

    @Override
    public int diasEstimados(Zona zona) { 
        return 2; 
    }
    
    @Override
    public int maxVisitas() { 
        return 1; 
    }

    @Override
    public boolean esCompatibleCon(Cuadrilla cuadrilla) { 
        return cuadrilla.isCertificadaRefrigeracion(); 
    }
}