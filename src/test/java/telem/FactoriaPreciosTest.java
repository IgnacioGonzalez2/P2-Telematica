package telem;

import static org.junit.Assert.*;
import org.junit.Test;
import java.time.LocalDate;

public class FactoriaPreciosTest {

    @Test
    public void testPrecioNormal() {

        EstrategiaPrecio estrategia =
            FactoriaPrecios.getEstrategia(
                PoliticasDePrecios.Normal
            );

        assertTrue(estrategia instanceof PrecioNormal);
    }

    @Test
    public void testPrecioTemporadaAlta() {

        EstrategiaPrecio estrategia =
            FactoriaPrecios.getEstrategia(
                PoliticasDePrecios.TemporadaAlta
            );

        assertTrue(
            estrategia instanceof PrecioTemporadaAlta
        );
    }

    @Test
    public void testPrecioClienteFrecuente() {

        EstrategiaPrecio estrategia =
            FactoriaPrecios.getEstrategia(
                PoliticasDePrecios.ClienteFrecuente
            );

        assertTrue(
            estrategia instanceof PrecioClienteFrecuente
        );
    }

    @Test
    public void testPrecioOferton() {

        EstrategiaPrecio estrategia =
            FactoriaPrecios.getEstrategia(
                PoliticasDePrecios.Oferton
            );

        assertTrue(estrategia instanceof PrecioOferton);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPoliticaNula() {

        FactoriaPrecios.getEstrategia(null);
    }

    @Test
    public void testCalculoConFactoria() {

        Vehiculo vehiculo = new Turismo(
            "1234ABC",
            Categoria.NORMAL,
            2020,
            5,
            450.0
        );

        Cliente cliente = new Cliente(
            "Juan Pérez",
            "12345678A"
        );

        Alquiler alquiler = new Alquiler(
            vehiculo,
            cliente,
            LocalDate.of(2026, 11, 10),
            LocalDate.of(2026, 11, 14)
        );

        // Política normal
        alquiler.setPoliticaPrecios(
            PoliticasDePrecios.Normal
        );

        assertEquals(250, alquiler.calcularPrecioAlquiler());

        // Temporada alta
        alquiler.setPoliticaPrecios(
            PoliticasDePrecios.TemporadaAlta
        );

        assertEquals(500, alquiler.calcularPrecioAlquiler());

        // Cliente frecuente
        alquiler.setPoliticaPrecios(
            PoliticasDePrecios.ClienteFrecuente
        );

        assertEquals(180, alquiler.calcularPrecioAlquiler());

        // Ofertón
        alquiler.setPoliticaPrecios(
            PoliticasDePrecios.Oferton
        );

        assertEquals(175, alquiler.calcularPrecioAlquiler());
    }
}
