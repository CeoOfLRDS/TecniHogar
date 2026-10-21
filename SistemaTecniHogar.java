import java.util.ArrayList;
import java.util.List;

public class SistemaTecniHogar {
    private List<OrdenServicio> ordenes = new ArrayList<>();
    private List<Cuadrilla> cuadrillas = new ArrayList<>();
    private List<Jornada> jornadas = new ArrayList<>();
    private List<RegistroIngreso> ingresos = new ArrayList<>();

    public void registrarOrden(OrdenServicio orden) {
        try {
            double costo = orden.getServicio().calcularCosto(orden.getHorasEstimadas(), orden.getZona(), 0.0);
            ordenes.add(orden);
            ingresos.add(new RegistroIngreso(costo, TipoIngreso.REGISTRO, orden.getServicio().getNombre(), orden.getCodigo()));
            System.out.printf("Orden %s aceptada. Monto facturable: Q%.2f%n", orden.getCodigo(), costo);
        } catch (IllegalArgumentException e) {
            System.out.println("Rechazo automático: " + e.getMessage());
        }
    }

    public void crearJornada(Cuadrilla cuadrilla) {
        if (cuadrilla.isEnJornadaAbierta()) {
            System.out.println("Operación denegada: La cuadrilla ya posee una jornada abierta.");
            return;
        }
        String id = "J-" + (jornadas.size() + 1);
        jornadas.add(new Jornada(id, cuadrilla));
    }

    public void generarReportes() {
        double total = ingresos.stream().mapToDouble(RegistroIngreso::getMonto).sum();
        System.out.println("\n--- Reporte Financiero de Operaciones ---");
        System.out.printf("Total Proyectado y Recaudado: Q%.2f%n", total);
        System.out.println("-----------------------------------------");
    }

    public List<Jornada> getJornadas() { return jornadas; }
    
    public void agregarCuadrilla(Cuadrilla c) { cuadrillas.add(c); }
    
    public void iniciarFlujoJornada(Jornada jornada) {
        jornada.iniciar();
        for (OrdenServicio ord : jornada.getOrdenes()) {
            if (ord.getVisitasRealizadas() > 1) {
                ingresos.add(new RegistroIngreso(Configuracion.cargoVisitaAdicional, TipoIngreso.VISITA_ADIC, ord.getServicio().getNombre(), ord.getCodigo()));
                System.out.printf("Facturación por visita adicional (Q%.2f) registrada en Orden %s.%n", Configuracion.cargoVisitaAdicional, ord.getCodigo());
            }
        }
    }

    public void registrarResultadoOrden(OrdenServicio orden, boolean exito) {
        if (orden.getEstado() != EstadoOrden.EN_ATENCION) return;
        if (exito) {
            orden.cambiarEstado(EstadoOrden.COMPLETADA);
        } else {
            if (orden.getVisitasRealizadas() >= orden.getServicio().maxVisitas()) {
                orden.cambiarEstado(EstadoOrden.CANCELADA);
            } else {
                orden.cambiarEstado(EstadoOrden.PENDIENTE);
            }
        }
    }
}