
package telem;

import java.time.LocalDate;
import org.json.JSONObject;

public class DemoFactorias {

    public static void main(String[] args) {

        // 1. Crear un cliente
        Cliente cliente = new Cliente(
            "Juan Pérez", "12345678A"
        );

        // 2. Crear un vehículo
        Vehiculo vehiculo = new Turismo(
            "1234ABC", Categoria.NORMAL, 2020, 5, 450.0
        );

        vehiculo.setDisponible(true);

        // 3. Crear un alquiler
        Alquiler alquiler = new Alquiler(
            vehiculo,
            cliente,
            LocalDate.of(2026, 11, 10),
            LocalDate.of(2026, 11, 13)
        );

        // 4. Serializar el alquiler
        String json = alquiler.toString();

        System.out.println("JSON original:");
        System.out.println(json);

        // 5. Deserializar usando la factoría
        Alquiler recuperado =
            FactoriaAlquileres.getAlquiler(json);

        // 6. Mostrar los datos recuperados
        System.out.println("\nCliente recuperado:");
        System.out.println(recuperado.getCliente());

        System.out.println("\nVehículo recuperado:");
        System.out.println(recuperado.getVehiculo());

        System.out.println("\nAlquiler recuperado:");
        System.out.println(recuperado);

        // 7. Comprobar que el JSON es equivalente
        boolean iguales = new JSONObject(json).similar(
            new JSONObject(recuperado.toString())
        );

        System.out.println("\nMismo contenido: " + iguales);
    }
}
