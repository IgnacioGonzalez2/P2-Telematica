package telem;

public class PrecioOferton implements EstrategiaPrecio {

    @Override
    public int calcularPrecio(Vehiculo vehiculo, int dias) {
        int precioDiario = vehiculo.getPrecioBase();

        double total = precioDiario * 0.70 * dias;

        if (dias > 7) {
            total = total * 0.90;
        }

        return (int) Math.round(total);
    }
}
