
package telem;

import java.time.LocalDate;
import static org.junit.Assert.*;
import org.junit.Test;

public class FactoriasTest {

    @Test
    public void testCliente() {

        Cliente original = new Cliente("Ana", "12345678A");

        Cliente recuperado =
            FactoriaClientes.getCliente(original.toString());

        assertEquals(original.getNombre(), recuperado.getNombre());
        assertEquals(original.getDni(), recuperado.getDni());
    }

    @Test
    public void testTurismo() {

        Vehiculo original = new Turismo(
            "1234ABC", Categoria.NORMAL, 2020, 5, 450.0
        );

        original.setDisponible(true);

        Vehiculo recuperado =
            FactoriaVehiculos.getVehiculo(original.toString());

        assertTrue(Turismo.class.isInstance(recuperado));
        assertEquals(original.getMatricula(), recuperado.getMatricula());
        assertEquals(original.getCategoria(), recuperado.getCategoria());
        assertEquals(original.getAnnio(), recuperado.getAnnio());
        assertEquals(original.isDisponible(), recuperado.isDisponible());
        assertEquals(5, ((Turismo) recuperado).getPlazas());
        assertEquals(450.0,
            ((Turismo) recuperado).getCapacidadMaletero(), 0.001);
    }

    @Test
    public void testMotocicleta() {

        Vehiculo original = new Motocicleta(
            "5678DEF", Categoria.ESPECIAL, 2022, 500
        );

        Vehiculo recuperado =
            FactoriaVehiculos.getVehiculo(original.toString());

        assertTrue(Motocicleta.class.isInstance(recuperado));
        assertEquals(500, ((Motocicleta) recuperado).getCilindrada());
        assertEquals(original.getMatricula(), recuperado.getMatricula());
    }

    @Test
    public void testVehiculoIndustrial() {

        Vehiculo original = new VehiculoIndustrial(
            "9999XYZ", Categoria.SUPERIOR, 2021, 2000, 3
        );

        Vehiculo recuperado =
            FactoriaVehiculos.getVehiculo(original.toString());

        assertTrue(VehiculoIndustrial.class.isInstance(recuperado));
        assertEquals(2000,
            ((VehiculoIndustrial) recuperado).getCapacidadCarga());
        assertEquals(3,
            ((VehiculoIndustrial) recuperado).getNumeroEjes());
    }

    @Test
    public void testAlquiler() {

        Cliente cliente = new Cliente("Luis", "11111111A");

        Vehiculo vehiculo = new Turismo(
            "1234ABC", Categoria.NORMAL, 2020, 5, 400.0
        );

        Alquiler original = new Alquiler(
            vehiculo,
            cliente,
            LocalDate.of(2026, 11, 10),
            LocalDate.of(2026, 11, 13)
        );

        Alquiler recuperado =
            FactoriaAlquileres.getAlquiler(original.toString());

        assertEquals("Luis", recuperado.getCliente().getNombre());
        assertTrue(Turismo.class.isInstance(recuperado.getVehiculo()));
        assertEquals(original.getFechaInicio(), recuperado.getFechaInicio());
        assertEquals(original.getFechaFin(), recuperado.getFechaFin());
    }

    @Test
    (expected = IllegalArgumentException.class)
    public void testTipoDesconocido() {
        String json = "{\"tipo\":\"Avion\"}";

        FactoriaVehiculos.getVehiculo(json);
}
}
