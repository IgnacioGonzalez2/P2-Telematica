package telem;

import java.time.LocalDate;

public class DemoEstrategias {

    public static void main(String[] args) {

        Vehiculo vehiculo = new Turismo(
            "1234ABC", Categoria.NORMAL, 2020, 5, 400.0
        );

        Cliente cliente = new Cliente("Juan", "12345678A");

        LocalDate inicio = LocalDate.of(2026, 10, 1);
        LocalDate fin = LocalDate.of(2026, 10, 8);

        Alquiler alquiler = new Alquiler(
            vehiculo, cliente, inicio, fin
        );

        alquiler.setEstrategiaPrecio(new PrecioNormal());
        System.out.println(
            "Precio normal: " + alquiler.calcularPrecioAlquiler()
        );

        alquiler.setEstrategiaPrecio(new PrecioTemporadaAlta());
        System.out.println(
            "Temporada alta: " + alquiler.calcularPrecioAlquiler()
        );

        alquiler.setEstrategiaPrecio(new PrecioOferton());
        System.out.println(
            "Ofertón: " + alquiler.calcularPrecioAlquiler()
        );

        alquiler.setEstrategiaPrecio(new PrecioClienteFrecuente());
        System.out.println(
            "Cliente frecuente: " + alquiler.calcularPrecioAlquiler()
        );
    }
}