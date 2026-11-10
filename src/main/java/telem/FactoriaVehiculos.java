package telem;

import org.json.JSONObject;

public class FactoriaVehiculos {

    public static Vehiculo getVehiculo(String jsonString) {

        JSONObject json = new JSONObject(jsonString);

        String tipo = json.getString("tipo");

        switch (tipo) {

            case "Turismo":
                return new Turismo(jsonString);

            case "Motocicleta":
                return new Motocicleta(jsonString);

            case "VehiculoIndustrial":
                return new VehiculoIndustrial(jsonString);

            default:
                throw new IllegalArgumentException(
                    "Tipo de vehículo desconocido: " + tipo
                );
        }
    }
}
