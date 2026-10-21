public class InstalacionElectricaCertificada extends Instalacion {
    private double materiales;

    public InstalacionElectricaCertificada(int puntos, double materiales) {
        super(puntos);
        this.nombre = "Instalación Eléctrica Certificada";
        this.materiales = materiales;
    }

    @Override
    public double calcularCosto(double horas, Zona zona, double materialesParam) {
        double costoInstalacion = super.calcularCosto(horas, zona, 0.0);
        return costoInstalacion + 120.0 + (this.materiales * 0.05);
    }

    @Override
    public int diasEstimados(Zona zona) { 
        return super.diasEstimados(zona) + 2; 
    }
    
    @Override
    public int maxVisitas() { 
        return 2; 
    }
}