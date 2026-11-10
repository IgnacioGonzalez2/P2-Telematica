package telem;

public class PrecioNormal implements EstrategiaPrecio {

    @Override
    public int calcularPrecio(Vehiculo vehiculo, int dias) {
        return vehiculo.getPrecioBase() * dias;
    }
}
