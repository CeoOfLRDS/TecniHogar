public class RegistroIngreso {
    private double monto;
    private TipoIngreso tipo;
    private String categoriaServicio;
    private String codigoOrden;

    public RegistroIngreso(double monto, TipoIngreso tipo, String categoria, String codigoOrden) {
        this.monto = monto;
        this.tipo = tipo;
        this.categoriaServicio = categoria;
        this.codigoOrden = codigoOrden;
    }

    public double getMonto() { 
        return monto; 
    }
}