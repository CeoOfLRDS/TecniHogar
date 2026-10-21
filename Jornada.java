import java.util.ArrayList;
import java.util.List;

public class Jornada {
    private String id;
    private EstadoJornada estado;
    private double horasUsadas;
    private Cuadrilla cuadrilla;
    private List<OrdenServicio> ordenes;

    public Jornada(String id, Cuadrilla cuadrilla) {
        this.id = id;
        this.cuadrilla = cuadrilla;
        this.estado = EstadoJornada.PREPARACION;
        this.horasUsadas = 0.0;
        this.ordenes = new ArrayList<>();
        cuadrilla.setEnJornadaAbierta(true);
    }

    public boolean asignarOrden(OrdenServicio orden) {
        if (estado != EstadoJornada.PREPARACION) throw new IllegalStateException("La jornada no está en preparación.");
        if (orden.getEstado() != EstadoOrden.PENDIENTE) throw new IllegalStateException("La orden debe estar pendiente.");
        if (!cuadrilla.puedeAsignar(orden.getServicio())) throw new IllegalArgumentException("La cuadrilla carece de certificación para esta orden.");
        if (horasUsadas + orden.getHorasEstimadas() > cuadrilla.horasDisponibles()) {
            throw new IllegalArgumentException("Capacidad máxima de jornada excedida.");
        }

        ordenes.add(orden);
        horasUsadas += orden.getHorasEstimadas();
        orden.asignarJornada(this);
        return true;
    }

    public void retirarOrden(OrdenServicio orden) {
        if (estado != EstadoJornada.PREPARACION) throw new IllegalStateException("Jornada cerrada o en curso.");
        if (ordenes.remove(orden)) {
            horasUsadas -= orden.getHorasEstimadas();
            orden.retirarJornada();
        }
    }

    public void iniciar() {
        if (ordenes.isEmpty()) throw new IllegalStateException("Imposible iniciar una jornada vacía.");
        this.estado = EstadoJornada.EN_CURSO;
        for (OrdenServicio orden : ordenes) {
            orden.cambiarEstado(EstadoOrden.EN_ATENCION);
            orden.incrementarVisita();
        }
    }

    public void cerrar() {
        for (OrdenServicio orden : ordenes) {
            if (orden.getEstado() == EstadoOrden.EN_ATENCION) {
                throw new IllegalStateException("Es obligatorio que toda orden posea un resultado antes del cierre.");
            }
            orden.retirarJornada();
        }
        this.estado = EstadoJornada.CERRADA;
        cuadrilla.setEnJornadaAbierta(false);
    }
    
    public List<OrdenServicio> getOrdenes() { 
        return ordenes; 
    }
}