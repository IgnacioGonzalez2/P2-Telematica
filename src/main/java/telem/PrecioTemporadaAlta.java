package telem;

public class PrecioTemporadaAlta implements EstrategiaPrecio {

    @Override
    public int calcularPrecio(Vehiculo vehiculo, int dias) {
        return vehiculo.getPrecioBase() * 2 * dias;
    }
}