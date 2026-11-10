package telem;

import java.time.LocalDate;

public class DemoFactoriaPrecios {

    public static void main(String[] args) {

        // Crear un vehículo
        Vehiculo vehiculo = new Turismo(
            "1234ABC",
            Categoria.NORMAL,
            2020,
            5,
            450.0
        );

        // Crear un cliente
        Cliente cliente = new Cliente(
            "Juan Pérez",
            "12345678A"
        );

        // Crear un alquiler de 5 días
        LocalDate inicio = LocalDate.of(2026, 11, 10);
        LocalDate fin = LocalDate.of(2026, 11, 14);

        Alquiler alquiler = new Alquiler(
            vehiculo,
            cliente,
            inicio,
            fin
        );

        System.out.println("EJEMPLO FACTORIA DE PRECIOS");
        System.out.println("---------------------------");

        // Probar todas las políticas
        for (PoliticasDePrecios politica :
                PoliticasDePrecios.values()) {

            alquiler.setPoliticaPrecios(politica);

            int precio = alquiler.calcularPrecioAlquiler();

            System.out.println(
                "Politica: " + politica +
                " - Precio: " + precio + " euros"
            );
        }
    }
}
