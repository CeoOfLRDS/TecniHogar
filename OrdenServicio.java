public class OrdenServicio {
    private String codigo;
    private String cliente;
    private String direccion;
    private double horasEstimadas;
    private Zona zona;
    private EstadoOrden estado;
    private int visitasRealizadas;
    private Jornada jornadaActual;
    private Servicio servicio;

    public OrdenServicio(String codigo, String cliente, String direccion, double horasEstimadas, Zona zona, Servicio servicio) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.direccion = direccion;
        this.horasEstimadas = horasEstimadas;
        this.zona = zona;
        this.servicio = servicio;
        this.estado = EstadoOrden.PENDIENTE;
        this.visitasRealizadas = 0;
    }

    public void cambiarEstado(EstadoOrden nuevo) { 
        this.estado = nuevo; 
    }
    
    public void incrementarVisita() { 
        this.visitasRealizadas++; 
    }
    
    public void asignarJornada(Jornada jornada) {
        if (this.jornadaActual != null) throw new IllegalStateException("Orden ya se encuentra asignada.");
        this.jornadaActual = jornada;
    }
    
    public void retirarJornada() { 
        this.jornadaActual = null; 
    }

    public String getCodigo() { return codigo; }
    public double getHorasEstimadas() { return horasEstimadas; }
    public Zona getZona() { return zona; }
    public EstadoOrden getEstado() { return estado; }
    public Servicio getServicio() { return servicio; }
    public int getVisitasRealizadas() { return visitasRealizadas; }
}