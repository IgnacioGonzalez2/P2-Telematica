package telem;

import org.junit.Test;
import static org.junit.Assert.*;

public class MotocicletaTest {

    @Test
    public void testGetters() {
        Motocicleta moto = new Motocicleta(
            "1111ABC", Categoria.NORMAL, 2020, 125
        );

        assertEquals(125, moto.getCilindrada());
    }

    @Test
    public void testGetTipo() {
        Motocicleta moto = new Motocicleta(
            "1111ABC", Categoria.NORMAL, 2020, 125
        );

        assertEquals("Motocicleta", moto.getTipo());
    }

    @Test
    public void testGetPrecioBase() {
        Motocicleta normal = new Motocicleta(
            "1111ABC", Categoria.NORMAL, 2020, 125
        );
        Motocicleta especial = new Motocicleta(
            "2222ABC", Categoria.ESPECIAL, 2020, 125
        );
        Motocicleta superior = new Motocicleta(
            "3333ABC", Categoria.SUPERIOR, 2020, 125
        );

        assertEquals(50, normal.getPrecioBase());
        assertEquals(80, especial.getPrecioBase());
        assertEquals(100, superior.getPrecioBase());
    }
}