package telem;

public class PrecioClienteFrecuente implements EstrategiaPrecio {

    @Override
    public int calcularPrecio(Vehiculo vehiculo, int dias) {
        int precioDiario = vehiculo.getPrecioBase();

        int diasPrimerTramo = Math.min(dias, 3);
        int diasSegundoTramo = Math.max(dias - 3, 0);

        double total =
            diasPrimerTramo * precioDiario * 0.80
            + diasSegundoTramo * precioDiario * 0.60;

        return (int) Math.round(total);
    }
}