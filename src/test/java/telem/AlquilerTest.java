package telem;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;

public class AlquilerTest {

    @Test
    public void testGetters() {
        Vehiculo vehiculo = new Turismo(
            "1234ABC", Categoria.NORMAL, 2020, 5, 400.0
        );

        Cliente cliente = new Cliente("Juan", "12345678A");

        LocalDate inicio = LocalDate.of(2026, 10, 1);
        LocalDate fin = LocalDate.of(2026, 10, 3);

        Alquiler alquiler = new Alquiler(
            vehiculo, cliente, inicio, fin
        );

        assertEquals(vehiculo, alquiler.getVehiculo());
        assertEquals(cliente, alquiler.getCliente());
        assertEquals(inicio, alquiler.getFechaInicio());
        assertEquals(fin, alquiler.getFechaFin());
    }

    @Test
    public void testCalcularPrecioAlquiler() {
        LocalDate inicio = LocalDate.of(2026, 10, 1);
        LocalDate fin = LocalDate.of(2026, 10, 3);

        Vehiculo vehiculo = new Turismo(
            "1234ABC", Categoria.NORMAL, 2020, 5, 400.0
        );

        Cliente cliente = new Cliente("Juan", "12345678A");

        Alquiler alquiler = new Alquiler(
            vehiculo, cliente, inicio, fin
        );

        assertEquals(150, alquiler.calcularPrecioAlquiler(inicio, fin));
    }
}