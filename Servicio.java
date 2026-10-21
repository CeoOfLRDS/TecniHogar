public abstract class Servicio {
    protected String nombre;
    protected String descripcion;

    public abstract double calcularCosto(double horas, Zona zona, double materiales);
    public abstract int diasEstimados(Zona zona);
    public abstract int maxVisitas();
    
    public boolean esCompatibleCon(Cuadrilla cuadrilla) { 
        return true; 
    }
    
    public String getNombre() { 
        return nombre; 
    }
}