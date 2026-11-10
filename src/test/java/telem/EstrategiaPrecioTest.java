package telem;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;

public class EstrategiaPrecioTest {

    private Alquiler crearAlquiler(int dias) {
        Vehiculo vehiculo = new Turismo(
            "1234ABC", Categoria.NORMAL, 2020, 5, 400.0
        );

        Cliente cliente = new Cliente("Juan", "12345678A");

        LocalDate inicio = LocalDate.of(2026, 10, 1);
        LocalDate fin = inicio.plusDays(dias - 1);

        return new Alquiler(vehiculo, cliente, inicio, fin);
    }

    @Test
    public void testPrecioNormal() {
        Alquiler alquiler = crearAlquiler(3);

        alquiler.setEstrategiaPrecio(new PrecioNormal());

        assertEquals(150, alquiler.calcularPrecioAlquiler());
    }

    @Test
    public void testTemporadaAlta() {
        Alquiler alquiler = crearAlquiler(3);

        alquiler.setEstrategiaPrecio(new PrecioTemporadaAlta());

        assertEquals(300, alquiler.calcularPrecioAlquiler());
    }

    @Test
    public void testOfertonMenosDeSieteDias() {
        Alquiler alquiler = crearAlquiler(3);

        alquiler.setEstrategiaPrecio(new PrecioOferton());

        assertEquals(105, alquiler.calcularPrecioAlquiler());
    }

    @Test
    public void testOfertonMasDeSieteDias() {
        Alquiler alquiler = crearAlquiler(8);

        alquiler.setEstrategiaPrecio(new PrecioOferton());

        assertEquals(252, alquiler.calcularPrecioAlquiler());
    }

    @Test
    public void testClienteFrecuenteTresDias() {
        Alquiler alquiler = crearAlquiler(3);

        alquiler.setEstrategiaPrecio(new PrecioClienteFrecuente());

        assertEquals(120, alquiler.calcularPrecioAlquiler());
    }

    @Test
    public void testClienteFrecuenteMasDeTresDias() {
        Alquiler alquiler = crearAlquiler(8);

        alquiler.setEstrategiaPrecio(new PrecioClienteFrecuente());

        assertEquals(270, alquiler.calcularPrecioAlquiler());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEstrategiaNula() {
        Alquiler alquiler = crearAlquiler(3);

        alquiler.setEstrategiaPrecio(null);
    }
}
