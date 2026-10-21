public class Main {
    public static void main(String[] args) {
        SistemaTecniHogar sistema = new SistemaTecniHogar();

        // 1. Inicialización de Cuadrillas[cite: 1]
        Cuadrilla c1 = new Cuadrilla("C01", 8.0, true);
        Cuadrilla c2 = new Cuadrilla("C02", 8.0, false);
        sistema.agregarCuadrilla(c1); 
        sistema.agregarCuadrilla(c2);

        System.out.println("=== Pruebas de Requisitos Simplificados ===\n");

        // 2. Cotizar sin generar registros ni alterar nada[cite: 1]
        Servicio cotizacion = new Instalacion(5);
        System.out.printf("Cotización Instalación Rápida (Metropolitana): Q%.2f%n", 
            cotizacion.calcularCosto(2.5, Zona.METROPOLITANA, 0.0));

        // 3. Validación de Instalación y Eléctrica Certificada[cite: 1]
        OrdenServicio ordInstalacion = new OrdenServicio("ORD-001", "Eduardo R.", "Zona 10", 
            2.5, Zona.METROPOLITANA, new Instalacion(5));
        sistema.registrarOrden(ordInstalacion); // Q460

        OrdenServicio ordElectrica = new OrdenServicio("ORD-002", "Marta J.", "Zona 15", 
            2.5, Zona.METROPOLITANA, new InstalacionElectricaCertificada(5, 2000.0));
        sistema.registrarOrden(ordElectrica); // Q680

        // 4. Validación de restricciones polimórficas (Rechazos)[cite: 1]
        OrdenServicio ordDiag = new OrdenServicio("ORD-003", "Luis", "Zona 1", 
            2.0, Zona.METROPOLITANA, new Diagnostico());
        sistema.registrarOrden(ordDiag); // Falla por > 1.5 hrs

        OrdenServicio ordAire = new OrdenServicio("ORD-004", "Ana", "Carretera ES", 
            2.0, Zona.PERIFERICA, new MantenimientoAire(12000));
        sistema.registrarOrden(ordAire); // Falla por Zona Periférica

        // 5. Flujo de Jornada y Cargo por Visita Adicional[cite: 1]
        sistema.crearJornada(c1);
        Jornada j1 = sistema.getJornadas().get(0);
        j1.asignarOrden(ordInstalacion);
        
        System.out.println("\n-- Iniciando Visita 1 --");
        sistema.iniciarFlujoJornada(j1);
        sistema.registrarResultadoOrden(ordInstalacion, false); // Falla intencional
        j1.cerrar();
        
        System.out.println("\n-- Iniciando Visita 2 --");
        sistema.crearJornada(c1);
        Jornada j2 = sistema.getJornadas().get(1);
        j2.asignarOrden(ordInstalacion);
        sistema.iniciarFlujoJornada(j2); // Dispara el cargo VISITA_ADIC (Q40)
        sistema.registrarResultadoOrden(ordInstalacion, true);
        j2.cerrar();

        // 6. Reporte consolidado
        sistema.generarReportes();
    }
}