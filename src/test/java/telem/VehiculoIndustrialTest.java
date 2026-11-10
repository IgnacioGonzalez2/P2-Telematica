package telem;

import org.junit.Test;
import static org.junit.Assert.*;

public class VehiculoIndustrialTest {

    @Test
    public void testGetters() {
        VehiculoIndustrial industrial = new VehiculoIndustrial(
            "4444ABC", Categoria.NORMAL, 2020, 5000, 2
        );

        assertEquals(5000, industrial.getCapacidadCarga());
        assertEquals(2, industrial.getNumeroEjes());
    }

    @Test
    public void testGetTipo() {
        VehiculoIndustrial industrial = new VehiculoIndustrial(
            "4444ABC", Categoria.NORMAL, 2020, 5000, 2
        );

        assertEquals("VehiculoIndustrial", industrial.getTipo());
    }

    @Test
    public void testGetPrecioBase() {
        VehiculoIndustrial normal = new VehiculoIndustrial(
            "4444ABC", Categoria.NORMAL, 2020, 5000, 2
        );
        VehiculoIndustrial especial = new VehiculoIndustrial(
            "5555ABC", Categoria.ESPECIAL, 2020, 5000, 2
        );
        VehiculoIndustrial superior = new VehiculoIndustrial(
            "6666ABC", Categoria.SUPERIOR, 2020, 5000, 2
        );

        assertEquals(50, normal.getPrecioBase());
        assertEquals(80, especial.getPrecioBase());
        assertEquals(100, superior.getPrecioBase());
    }
}