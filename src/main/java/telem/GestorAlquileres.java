package telem;

import java.time.LocalDate;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.json.JSONArray;



public class GestorAlquileres {
    
    private int contadorAlquileres = 0;
    private Alquiler[] alquileres = new Alquiler[1000];

    // Para simplificar, vamos a definir un array de clientes y 
    // otro de vehículos dentro del gestor.
    // En una aplicación real estarían en una base de datos.
    private Cliente[] clientes = new Cliente[10];
    private Vehiculo[] vehiculos = new Vehiculo[100];


    public GestorAlquileres() {
        inicializarClientes();
        inicializarVehiculos();
    }

    /////////////////////////////////////////////////////////////////////////////////////
    // INICIALIZACIÓN DE CLIENTES Y VEHÍCULOS DE EJEMPLO

    // Definimos métodos privados para inicializar clientes y vehículos de ejemplo
    private void inicializarClientes() {
        clientes[0] = new Cliente("Clara Videncia", "001");
        clientes[1] = new Cliente("Aitor Menta", "002");
        clientes[2] = new Cliente("Lola Mento", "003");
        clientes[3] = new Cliente("Armando Guerra", "004");
        clientes[4] = new Cliente("Ana Lítica", "005");
        clientes[5] = new Cliente("Carmelo Cotón", "006");
    }

    private void inicializarVehiculos() {
        vehiculos[0] = new Turismo("TNA2020", Categoria.NORMAL, 2020, 5, 450.0);
        vehiculos[1] = new Motocicleta("MNA2021", Categoria.NORMAL, 2021, 200);
        vehiculos[2] = new VehiculoIndustrial("CNA2019", Categoria.NORMAL, 2019, 1400, 2);
        vehiculos[3] = new Turismo("TSA2024", Categoria.SUPERIOR, 2024, 5, 550.0);
        vehiculos[4] = new Motocicleta("MSA2021", Categoria.SUPERIOR, 2021, 500);
        vehiculos[5] = new VehiculoIndustrial("CSA2025", Categoria.SUPERIOR, 2025, 2400, 3);
        vehiculos[6] = new Turismo("TEA2026", Categoria.ESPECIAL, 2026, 7, 650.0);
        vehiculos[7] = new Motocicleta("MEA2026", Categoria.ESPECIAL, 2026, 1000);
        vehiculos[8] = new VehiculoIndustrial("CEA2025", Categoria.ESPECIAL, 2025, 4400, 5);

    }

    /////////////////////////////////////////////////////////////////////////////////////
    // MÉTODOS PARA GESTIONAR VEHÍCULOS

     public Vehiculo[] obtenerVehiculos(String tipo) {
        
        Vehiculo[] vehiculosFiltrados = new Vehiculo[vehiculos.length];
        int contador = 0;

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo != null) {
                if (vehiculo.getTipo().equals(tipo)) {
                    vehiculosFiltrados[contador++] = vehiculo;
                }
            }
        }

        // Ajustar el tamaño del array para que solo contenga los vehículos encontrados
        Vehiculo[] resultado = new Vehiculo[contador];
        System.arraycopy(vehiculosFiltrados, 0, resultado, 0, contador);
        return resultado;
    }   

    /**
     * Devuelve un array con los vehículos del tipo y categoría especificados.
     * @param tipo
     * @param categoria
     * @return lista de vehículos del tipo y categoría especificados
     */
    public Vehiculo[] obtenerVehiculos(String tipo, Categoria categoria) {

        System.out.println("GestorAlquileres: obtenerVehiculos(tipo, categoria): método no implementado");

        // Para hacer.

        return null;
    }

    /////////////////////////////////////////////////////////////////////////////////////
    // MÉTODOS PARA GESTIONAR ALQUILERES

    /**
     * Agrega un nuevo alquiler al array de alquileres.
     * @param alquiler
     */
    public void agregarAlquiler(Alquiler alquiler) {
        if (contadorAlquileres < alquileres.length) {
            alquileres[contadorAlquileres] = alquiler;
            contadorAlquileres++;
        } else {
            System.out.println("No se pueden agregar más alquileres. Capacidad máxima alcanzada.");
        }
    }

    /**
     * Devuelve un array con todos los alquileres registrados en el gestor.
     * @return
     */
    public Alquiler[] obtenerAlquileres() {
        return alquileres; 
    }

    public Alquiler[] obtenerAlquileres(Cliente cliente) {

        System.out.println("GestorAlquileres: obtenerAlquileres(cliente): método no implementado");

        return null;
    }


    /**
     * Informa si un cliente es cliente frecuente en función del número de alquileres previos.
     * @param cliente cliente a evaluar
     * @param umbral número mínimo de alquileres previos que se consideran para determinar si es cliente frecuente
     * @return
     */
    private boolean esClienteFrecuente(Cliente cliente, int umbral) {

        System.out.println("GestorAlquileres: esClienteFrecuente(cliente, umbral): método no implementado");
 
        // Para hacer.
        
        return false; 
    }


    /////////////////////////////////////////////////////////////////////////////////////
    // INTERFAZ DE USUARIO (CONSOLA) Y LÓGICA DE NEGOCIO

    // La interfaz de usuario y la lógica de negocio suelen implementarse por separado.
    // Aquí se integran ambas por simplicidad, pero en una aplicación real hay que separarlas.


    public void menuPrincipal(){
        
        boolean salir = false;
        
        while(!salir){

            // Se da oportunidad de salir inmediatamente de la app.
            System.out.println("Gestíon de Alquileres");
            System.out.println("¿Desea salir de la app (S/N)?");

            char s = KeyBoard.readChar();
            if (s == 's' || s == 'S'){
                System.out.println("Bye ...");
                return;
            }

            // Primero se identifica al cliente:
            Cliente cliente = identificarCliente();

            // Ahora el cliente puede elegir una opción o salir de la aplicación.
            if (cliente != null) {
                System.out.println("Sr " + cliente.getNombre() + " Introduzca una opción");
                System.out.println("(1) seleccionar coche para alquilar");
                System.out.println("(2) entregar un coche alquilado");
                System.out.println("(3) listar su histórico de alquileres");
                System.out.println("(4) guardar datos en fichero JSON");
                System.out.println("(5) cargar datos desde fichero JSON");

                int opcion = KeyBoard.readInt();
                System.out.println("opción escogida: " + opcion);

                switch (opcion) {
                    case 1:
                        Vehiculo v = seleccionarVehiculo(cliente);
                        if (v.isDisponible()){
                            // para simplificar la IU se supone que el alquiler comienza "ahora"
                            // y el usuario especifica duración en días.
                            System.out.println("¿cuántos días desea alquilar el vehículo?");
                            int dias = KeyBoard.readInt();
                            LocalDate inicio = LocalDate.now();
                            LocalDate fin = inicio.plusDays(dias);

                            // Creamos y registramos el alquiler
                            Alquiler q = new Alquiler(v, cliente, inicio, fin);
                            this.agregarAlquiler(q);

                            // Calculamos precio.
                            // Aquí habría que determinar la política de precios aplicable según el tipo de cliente y la fecha.
                            // Para simplificar, se aplicará la política de cliente frecuente si el cliente tiene más de 3 alquileres previos, 
                            // y la política normal en caso contrario.

                            //Comprobamos si el cliente es usuario frecuente para aplicarle
                            // la tarifa que le corresponde.
                            boolean usuarioFrecuente = this.esClienteFrecuente(cliente, 3);

                            if (usuarioFrecuente){

                                // aplicamos tarifa de cliente frecuente.
                                System.out.println("Alquiler realizado. Tarifa Usuario frecuente.");
                                // Para completar ...
                            }
                            else{
                                // aplicamos tarifa normal.
                                System.out.println("Alquiler realizado. Tarifa Normal.");

                                // Para completar ...
                            }
                            
                        }
                        else{
                            System.out.println("Debe seleccionar un vehículo disponible");
                        }
                        break;
                    case 2: 
                        System.out.println("opción no implementada");

                        // Para completar ...

                        break;              
                    case 3:
                        Alquiler alqs [] = obtenerAlquileres(cliente);
                        for (Alquiler q: alqs){
                            System.out.print(q);
                        }
                    default:
                        break;

                    
                    case 4:
                        System.out.println("Nombre del fichero para guardar:");
                        String ficheroGuardar = KeyBoard.readString();

                        try {
                             guardarDatos(ficheroGuardar);
                             System.out.println("Datos guardados correctamente.");
                        } catch (IOException | RuntimeException e) {
                            System.out.println("Error al guardar: " + e.getMessage());
                        }
                        break;

                    case 5:
                        System.out.println("Nombre del fichero para cargar:");
                        String ficheroCargar = KeyBoard.readString();

                        try {
                            cargarDatos(ficheroCargar);
                            System.out.println("Datos cargados correctamente.");
                        } catch (IOException | RuntimeException e) {
                            System.out.println("Error al cargar: " + e.getMessage());
                        }
                        break;
                }
            }
            else{
                System.out.println("Debe identificarse correctamente o salir de la app");
            }
        }
    }

    public Cliente identificarCliente(){

        boolean seleccionado = false;
        Cliente cliente = null;

        while (!seleccionado) {
        
            System.out.println("Bienvenido al Gestor de Alquileres");
            for (int i = 0; i < clientes.length; i++) {
                if (clientes[i] != null) {
                    System.out.println(i + " ---> " + clientes[i].getNombre() + " - " + clientes[i].getDni());
                }
            }
            System.out.println("Identífiquese como cliente con su número de cliente (-1 para salir):");
            int numeroCliente = KeyBoard.readInt();
            if (numeroCliente == -1) {
                return null;
            }
            else if (numeroCliente < 0 || numeroCliente >= clientes.length || clientes[numeroCliente] == null) {
                System.out.println("identificarCliente:Número de cliente inválido.");
                return null;
            }
            else{
                seleccionado = true;
                cliente = clientes[numeroCliente];
            }
        }
        return cliente;
    }

    public Vehiculo seleccionarVehiculo(Cliente cliente){
        boolean seleccionado = false;
        Vehiculo vehiculo = null;

        while (!seleccionado) {
        
            System.out.println("Estimado Sr./Sra. " + cliente.getNombre());
            System.out.println("Introduzca el número correspondiente al tipo de vehículo que desea alquilar:");
            System.out.println("1. Turismo, 2. Motocicleta, 3. Vehículo Industrial: ");

            int tipoVehiculo = KeyBoard.readInt();
            String tipo = "";
            Vehiculo [] elegibles = new Vehiculo[100];

            switch (tipoVehiculo) {
                case 1:
                    System.out.println("Ha seleccionado Turismo.");
                    tipo = "Turismo";
                    break;
                case 2:
                    System.out.println("Ha seleccionado Motocicleta.");
                    tipo = "Motocicleta";
                    break;
                case 3:
                    System.out.println("Ha seleccionado Vehículo Industrial.");
                    tipo = "VehículoIndustrial";
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione un tipo de vehículo válido.");
                    continue; // Volver a pedir la selección
            }

            System.out.println("Vehiculos disponibles");

            System.out.println("Estimado Sr./Sra. " + cliente.getNombre() + "\n seleccione categoría.");
            System.out.println("Introduzca el número correspondiente a la categoría que desea alquilar:");
            System.out.println("1. Normal, 2. Superior, 3. Especial, 4.Cualquiera: ");

            int categoriaVehiculo = KeyBoard.readInt();

            switch (categoriaVehiculo) {
                case 1:
                    System.out.println("Ha seleccionado Categoría Normal.");
                    elegibles = obtenerVehiculos(tipo, Categoria.NORMAL);
                    break;
                case 2:
                    System.out.println("Ha seleccionado Categoría Superior.");
                    elegibles = obtenerVehiculos(tipo, Categoria.SUPERIOR);
                    break;
                case 3:
                    System.out.println("Ha seleccionado Categoría Especial.");
                    elegibles = obtenerVehiculos(tipo, Categoria.ESPECIAL);
                    break;
                default:
                    elegibles = obtenerVehiculos(tipo, Categoria.ESPECIAL);
                    break; // Volver a pedir la selección
            }

            System.out.println("Coches elegibles");

            for(int i = 0; i < elegibles.length; i++){
                System.out.println(i + " ---> " + elegibles[i]);
            }

            System.out.println("Introduzca número coche (sólo si está disponible)");
            int seleccion = KeyBoard.readInt();
            vehiculo = elegibles[seleccion];
            seleccionado = true;
        }
        return vehiculo;
    }

    public void listarAlquileres (){
        System.out.println("Listado de alquileres");
        for (Alquiler alq: alquileres){
            System.out.println(alq);
        }
        System.out.println();
    }

    public void listarVehiculos(){
        System.out.println("Listado de vehículos");
        for (Vehiculo veh: vehiculos){
            System.out.println(veh);
        }
        System.out.println();
    }

    public static void probarPersistencia() {

        try {
            String fichero = "prueba_alquileres.json";

            // Crear el gestor
            GestorAlquileres gestor = new GestorAlquileres();

            // Crear un alquiler de prueba
            Cliente cliente = gestor.clientes[0];
            Vehiculo vehiculo = gestor.vehiculos[0];

            Alquiler alquiler = new Alquiler(
                vehiculo,
                cliente,
                LocalDate.of(2026, 11, 10),
                LocalDate.of(2026, 11, 14)
            );

            gestor.agregarAlquiler(alquiler);

            // Guardar los datos
            gestor.guardarDatos(fichero);

            // Crear otro gestor y cargar los datos
            GestorAlquileres recuperado = new GestorAlquileres();
            recuperado.cargarDatos(fichero);

            // Comprobar clientes
            if (!recuperado.clientes[0].getDni().equals(
                    gestor.clientes[0].getDni())) {
                throw new AssertionError(
                    "Error al recuperar clientes"
                );
            }

            // Comprobar vehículos
            if (!recuperado.vehiculos[0].getMatricula().equals(
                    gestor.vehiculos[0].getMatricula())) {
                throw new AssertionError(
                    "Error al recuperar vehículos"
                );
            }

            // Comprobar alquileres
            if (recuperado.contadorAlquileres != 1) {
                throw new AssertionError(
                    "Error al recuperar alquileres"
                );
            }

            Alquiler alquilerRecuperado = recuperado.alquileres[0];

            if (!alquilerRecuperado.getFechaInicio().equals(
                    alquiler.getFechaInicio())) {
                throw new AssertionError(
                    "Error al recuperar las fechas"
                );
            }

            // Comprobar que se conservan las referencias
            if (alquilerRecuperado.getCliente() !=
                    recuperado.clientes[0]) {
                throw new AssertionError(
                    "El cliente no comparte la misma referencia"
                );
            }

            if (alquilerRecuperado.getVehiculo() !=
                    recuperado.vehiculos[0]) {
                throw new AssertionError(
                    "El vehículo no comparte la misma referencia"
                );
            }

            // Comprobar que cargar no duplica alquileres
            recuperado.cargarDatos(fichero);

            if (recuperado.contadorAlquileres != 1) {
                throw new AssertionError(
                    "La carga ha duplicado los alquileres"
                );
            }

            // Eliminar el fichero temporal
            Files.deleteIfExists(Paths.get(fichero));

            System.out.println("Todas las pruebas superadas.");

        } catch (IOException e) {
            throw new RuntimeException(
                "Error en las pruebas de persistencia", e
            );
        }
    }


    public static void main(String[] args) {

        probarPersistencia();

        GestorAlquileres app = new GestorAlquileres();
        app.menuPrincipal();
    }


    public void guardarDatos(String nombreFichero) throws IOException {

        JSONObject datos = new JSONObject();

        JSONArray clientesJSON = new JSONArray();
        JSONArray vehiculosJSON = new JSONArray();
        JSONArray alquileresJSON = new JSONArray();

        // Guardar clientes
        for (Cliente cliente : clientes) {
            if (cliente != null) {
                clientesJSON.put(new JSONObject(cliente.toString()));
            }
        }

        // Guardar vehículos
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo != null) {
                vehiculosJSON.put(new JSONObject(vehiculo.toString()));
            }
        }

        // Guardar alquileres
        for (int i = 0; i < contadorAlquileres; i++) {
            if (alquileres[i] != null) {
                alquileresJSON.put(
                    new JSONObject(alquileres[i].toString())
                );
            }
    }

        datos.put("clientes", clientesJSON);
        datos.put("vehiculos", vehiculosJSON);
        datos.put("alquileres", alquileresJSON);

        Files.write(
            Paths.get(nombreFichero),
            datos.toString(4).getBytes(StandardCharsets.UTF_8)
        );
    }
    

    public void cargarDatos(String nombreFichero) throws IOException {

        String contenido = new String(
            Files.readAllBytes(Paths.get(nombreFichero)),
            StandardCharsets.UTF_8
        );

        JSONObject datos = new JSONObject(contenido);

        JSONArray clientesJSON = datos.getJSONArray("clientes");
        JSONArray vehiculosJSON = datos.getJSONArray("vehiculos");
        JSONArray alquileresJSON = datos.getJSONArray("alquileres");

        // Comprobar que los datos caben en los arrays
        if (clientesJSON.length() > clientes.length ||
            vehiculosJSON.length() > vehiculos.length ||
            alquileresJSON.length() > alquileres.length) {

            throw new IllegalArgumentException(
                "El fichero contiene más objetos de los permitidos"
            );
        }

        // Crear arrays temporales
        Cliente[] nuevosClientes = new Cliente[clientes.length];
        Vehiculo[] nuevosVehiculos = new Vehiculo[vehiculos.length];
        Alquiler[] nuevosAlquileres = new Alquiler[alquileres.length];

        // Recuperar clientes
        for (int i = 0; i < clientesJSON.length(); i++) {
            String json = clientesJSON.getJSONObject(i).toString();

            nuevosClientes[i] = FactoriaClientes.getCliente(json);
        }

        // Recuperar vehículos
        for (int i = 0; i < vehiculosJSON.length(); i++) {
            String json = vehiculosJSON.getJSONObject(i).toString();

            nuevosVehiculos[i] = FactoriaVehiculos.getVehiculo(json);
        }

        // Recuperar alquileres
        
    // Recuperar alquileres
    for (int i = 0; i < alquileresJSON.length(); i++) {

        JSONObject jsonAlquiler = alquileresJSON.getJSONObject(i);

        String dni = jsonAlquiler
            .getJSONObject("cliente").getString("dni");

        String matricula = jsonAlquiler
            .getJSONObject("vehiculo").getString("matricula");

        Cliente clienteAlquiler = null;
        Vehiculo vehiculoAlquiler = null;

        // Buscar el cliente original
        for (Cliente c : nuevosClientes) {
            if (c != null && c.getDni().equals(dni)) {
                clienteAlquiler = c;
                break;
            }
        }

        // Buscar el vehículo original
        for (Vehiculo v : nuevosVehiculos) {
            if (v != null && v.getMatricula().equals(matricula)) {
                vehiculoAlquiler = v;
                break;
            }
        }

        if (clienteAlquiler == null || vehiculoAlquiler == null) {
            throw new IllegalArgumentException(
                "Alquiler con cliente o vehículo inexistente"
            );
        }

        LocalDate inicio = LocalDate.parse(
            jsonAlquiler.getString("fechaInicio")
        );

        LocalDate fin = LocalDate.parse(
            jsonAlquiler.getString("fechaFin")
        );

        nuevosAlquileres[i] = new Alquiler(
            vehiculoAlquiler,
            clienteAlquiler,
            inicio,
            fin
        );
    }


        // Sustituir los datos actuales
        this.clientes = nuevosClientes;
        this.vehiculos = nuevosVehiculos;
        this.alquileres = nuevosAlquileres;
        this.contadorAlquileres = alquileresJSON.length();
    }
}
