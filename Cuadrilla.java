public class Cuadrilla {
    private String codigo;
    private double capacidadHoras;
    private boolean certificadaRefrigeracion;
    private boolean enJornadaAbierta;

    public Cuadrilla(String codigo, double capacidadHoras, boolean certificadaRefrigeracion) {
        this.codigo = codigo;
        this.capacidadHoras = capacidadHoras;
        this.certificadaRefrigeracion = certificadaRefrigeracion;
        this.enJornadaAbierta = false;
    }

    public boolean puedeAsignar(Servicio servicio) { 
        return servicio.esCompatibleCon(this); 
    }
    
    public double horasDisponibles() { 
        return capacidadHoras; 
    }
    
    public String getCodigo() { 
        return codigo; 
    }
    
    public boolean isCertificadaRefrigeracion() { 
        return certificadaRefrigeracion; 
    }
    
    public boolean isEnJornadaAbierta() { 
        return enJornadaAbierta; 
    }
    
    public void setEnJornadaAbierta(boolean estado) { 
        this.enJornadaAbierta = estado; 
    }
}