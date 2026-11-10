package telem;

import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteTest {

    @Test
    public void testGetters() {
        Cliente cliente = new Cliente("Juan", "12345678A");

        assertEquals("Juan", cliente.getNombre());
        assertEquals("12345678A", cliente.getDni());
    }
}