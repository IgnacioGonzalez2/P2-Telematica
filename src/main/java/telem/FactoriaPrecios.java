package telem;

public class FactoriaPrecios {

    public static EstrategiaPrecio getEstrategia(
            PoliticasDePrecios politica) {

        if (politica == null) {
            throw new IllegalArgumentException(
                "La politica de precios no puede ser null"
            );
        }

        switch (politica) {

            case Normal:
                return new PrecioNormal();

            case TemporadaAlta:
                return new PrecioTemporadaAlta();

            case ClienteFrecuente:
                return new PrecioClienteFrecuente();

            case Oferton:
                return new PrecioOferton();

            default:
                throw new IllegalArgumentException(
                    "Politica de precios desconocida: " + politica
                );
        }
    }
}
