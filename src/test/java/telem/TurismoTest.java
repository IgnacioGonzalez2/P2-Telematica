package telem;

import org.junit.Test;
import static org.junit.Assert.*;

public class TurismoTest {

    @Test
    public void testGetters() {
        Turismo turismo = new Turismo(
            "1234ABC",
            Categoria.NORMAL,
            2020,
            5,
            400.0
        );

        assertEquals(5, turismo.getPlazas());
        assertEquals(400.0, turismo.getCapacidadMaletero(), 0.001);
    }

    @Test
    public void testGetTipo() {
        Turismo turismo = new Turismo(
            "1234ABC", Categoria.NORMAL, 2020, 5, 400.0
        );

        assertEquals("Turismo", turismo.getTipo());
    }

    @Test
    public void testGetPrecioBase() {
        Turismo normal = new Turismo(
            "1234ABC", Categoria.NORMAL, 2020, 5, 400.0
        );
        Turismo especial = new Turismo(
            "5678DEF", Categoria.ESPECIAL, 2020, 5, 400.0
        );
        Turismo superior = new Turismo(
            "9012GHI", Categoria.SUPERIOR, 2020, 5, 400.0
        );

        assertEquals(50, normal.getPrecioBase());
        assertEquals(80, especial.getPrecioBase());
        assertEquals(100, superior.getPrecioBase());
    }
}