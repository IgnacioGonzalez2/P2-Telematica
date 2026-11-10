package telem;

import java.time.LocalDate;

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

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo != null) {
                vehiculo.setDisponible(true);
            }
        }
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

        Vehiculo[] filtrados = new Vehiculo[vehiculos.length];
        int contador = 0;

        for (Vehiculo vehiculo : vehiculos) {

            if (vehiculo != null
                    && vehiculo.getTipo().equals(tipo)
                    && (categoria == null
                        || vehiculo.getCategoria() == categoria)
                    && vehiculo.isDisponible()) {

                filtrados[contador] = vehiculo;
                contador++;
            }
        }

        Vehiculo[] resultado = new Vehiculo[contador];

        System.arraycopy(filtrados, 0, resultado, 0, contador);

        return resultado;
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

        if (cliente == null) {
            return new Alquiler[0];
        }

        Alquiler[] encontrados = new Alquiler[contadorAlquileres];
        int contador = 0;

        for (int i = 0; i < contadorAlquileres; i++) {

            Alquiler alquiler = alquileres[i];

            if (alquiler != null
                    && alquiler.getCliente() != null
                    && alquiler.getCliente().getDni().equals(
                        cliente.getDni())) {

                encontrados[contador] = alquiler;
                contador++;
            }
        }

        Alquiler[] resultado = new Alquiler[contador];

        System.arraycopy(encontrados, 0, resultado, 0, contador);

        return resultado;
    }



    /**
     * Informa si un cliente es cliente frecuente en función del número de alquileres previos.
     * @param cliente cliente a evaluar
     * @param umbral número mínimo de alquileres previos que se consideran para determinar si es cliente frecuente
     * @return
     */
    private boolean esClienteFrecuente(Cliente cliente, int umbral) {

        Alquiler[] historial = obtenerAlquileres(cliente);

        return historial.length > umbral;
    }


    public void devolverVehiculo(Cliente cliente) {

        if (cliente == null) {
            System.out.println("Cliente no válido.");
            return;
        }

        Alquiler[] historial = obtenerAlquileres(cliente);

        // Buscar alquileres cuyos vehículos siguen ocupados
        int pendientes = 0;

        for (Alquiler alquiler : historial) {
            if (alquiler != null
                    && !alquiler.getVehiculo().isDisponible()) {
                pendientes++;
            }
        }

        if (pendientes == 0) {
            System.out.println(
                "No tiene vehículos pendientes de devolución."
            );
            return;
        }

        Alquiler[] activos = new Alquiler[pendientes];
        int contador = 0;

        System.out.println("Vehículos pendientes de devolución:");

        for (Alquiler alquiler : historial) {

            if (alquiler != null
                    && !alquiler.getVehiculo().isDisponible()) {

                activos[contador] = alquiler;

                System.out.println(
                    contador + " - Matrícula: "
                    + alquiler.getVehiculo().getMatricula()
                );

                contador++;
            }
        }

        System.out.println(
            "Introduzca el número del vehículo a devolver:"
        );

        int opcion = KeyBoard.readInt();

        if (opcion < 0 || opcion >= activos.length) {
            System.out.println("Selección no válida.");
            return;
        }

        Vehiculo vehiculo = activos[opcion].getVehiculo();

        vehiculo.setDisponible(true);

        System.out.println(
            "Vehículo " + vehiculo.getMatricula()
            + " devuelto correctamente."
        );
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

                int opcion = KeyBoard.readInt();
                System.out.println("opción escogida: " + opcion);

                switch (opcion) {
                    
                case 1:

                    Vehiculo v = seleccionarVehiculo(cliente);

                    if (v == null) {
                        System.out.println("No se ha seleccionado un vehículo.");
                        break;
                    }

                    if (!v.isDisponible()) {
                        System.out.println(
                            "Debe seleccionar un vehículo disponible."
                        );
                        break;
                    }

                    System.out.println(
                        "¿Cuántos días desea alquilar el vehículo?"
                    );

                    int dias = KeyBoard.readInt();

                    if (dias <= 0) {
                        System.out.println(
                            "La duración debe ser mayor que cero."
                        );
                        break;
                    }

                    // Comprobar los alquileres anteriores
                    boolean frecuente = esClienteFrecuente(cliente, 3);

                    LocalDate inicio = LocalDate.now();
                    LocalDate fin = inicio.plusDays(dias - 1);

                    Alquiler nuevo = new Alquiler(
                        v, cliente, inicio, fin
                    );

                    // Seleccionar la política de precios
                    if (frecuente) {

                        nuevo.setPoliticaPrecios(
                            PoliticasDePrecios.ClienteFrecuente
                        );

                        System.out.println(
                            "Tarifa aplicada: Cliente frecuente"
                        );

                    } else {

                        nuevo.setPoliticaPrecios(
                            PoliticasDePrecios.Normal
                        );

                        System.out.println(
                            "Tarifa aplicada: Normal"
                        );
                    }

                    int precio = nuevo.calcularPrecioAlquiler();

                    // Registrar el alquiler
                    if (contadorAlquileres >= alquileres.length) {
                        System.out.println(
                            "No se pueden registrar más alquileres."
                        );
                        break;
                    }

                    agregarAlquiler(nuevo);

                    // Marcar el vehículo como no disponible
                    v.setDisponible(false);

                    System.out.println("Alquiler realizado correctamente.");
                    System.out.println("Vehículo: " + v.getMatricula());
                    System.out.println("Fecha inicio: " + inicio);
                    System.out.println("Fecha fin: " + fin);
                    System.out.println("Precio total: " + precio + " euros");

                    break;
                    case 2:

                        devolverVehiculo(cliente);

                    break;             
                    case 3:

                        Alquiler[] historial = obtenerAlquileres(cliente);

                        if (historial.length == 0) {

                            System.out.println(
                                "No tiene alquileres registrados."
                            );

                        } else {

                            System.out.println(
                                "Historial de alquileres de "
                                + cliente.getNombre()
                            );

                            for (int i = 0; i < historial.length; i++) {

                                Alquiler alquiler = historial[i];

                                System.out.println(
                                    "Alquiler " + (i + 1)
                                );

                                System.out.println(
                                    "Vehículo: "
                                    + alquiler.getVehiculo().getMatricula()
                                );

                                System.out.println(
                                    "Fecha inicio: "
                                    + alquiler.getFechaInicio()
                                );

                                System.out.println(
                                    "Fecha fin: "
                                    + alquiler.getFechaFin()
                                );

                                System.out.println("------------------------");
                            }
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

    public Vehiculo seleccionarVehiculo(Cliente cliente) {

        System.out.println(
            "Estimado Sr./Sra. " + cliente.getNombre()
        );

        System.out.println("Seleccione tipo de vehículo:");
        System.out.println("1. Turismo");
        System.out.println("2. Motocicleta");
        System.out.println("3. Vehículo Industrial");

        int opcionTipo = KeyBoard.readInt();

        String tipo;

        switch (opcionTipo) {

            case 1:
                tipo = "Turismo";
                break;

            case 2:
                tipo = "Motocicleta";
                break;

            case 3:
                tipo = "VehiculoIndustrial";
                break;

            default:
                System.out.println("Tipo no válido.");
                return null;
        }

        System.out.println("Seleccione categoría:");
        System.out.println("1. Normal");
        System.out.println("2. Superior");
        System.out.println("3. Especial");
        System.out.println("4. Cualquiera");

        int opcionCategoria = KeyBoard.readInt();

        Categoria categoria;

        switch (opcionCategoria) {

            case 1:
                categoria = Categoria.NORMAL;
                break;

            case 2:
                categoria = Categoria.SUPERIOR;
                break;

            case 3:
                categoria = Categoria.ESPECIAL;
                break;

            case 4:
                categoria = null;
                break;

            default:
                System.out.println("Categoría no válida.");
                return null;
        }

        Vehiculo[] elegibles =
            obtenerVehiculos(tipo, categoria);

        if (elegibles.length == 0) {

            System.out.println(
                "No hay vehículos disponibles de ese tipo."
            );

            return null;
        }

        System.out.println("Vehículos disponibles:");

        for (int i = 0; i < elegibles.length; i++) {

            System.out.println(
                i + " - "
                + elegibles[i].getMatricula()
                + " - "
                + elegibles[i].getCategoria()
            );
        }

        System.out.println(
            "Introduzca el número del vehículo:"
        );

        int seleccion = KeyBoard.readInt();

        if (seleccion < 0 || seleccion >= elegibles.length) {

            System.out.println("Selección no válida.");
            return null;
        }

        return elegibles[seleccion];
    }


    public void listarAlquileres() {

        System.out.println("Listado de alquileres:");

        for (int i = 0; i < contadorAlquileres; i++) {

            if (alquileres[i] != null) {
                System.out.println(alquileres[i]);
            }
        }
    }

    public void listarVehiculos() {

    System.out.println("Listado de vehículos:");

    for (Vehiculo vehiculo : vehiculos) {

        if (vehiculo != null) {
            System.out.println(vehiculo);
        }
    }
    }


    public static void probarEjercicio6() {

        GestorAlquileres gestor = new GestorAlquileres();

        Cliente cliente = gestor.clientes[0];

        // Prueba 1: filtrar vehículos
        Vehiculo[] turismos = gestor.obtenerVehiculos(
            "Turismo", Categoria.NORMAL
        );

        if (turismos.length != 1) {
            throw new AssertionError(
                "Error al filtrar turismos normales"
            );
        }

        // Prueba 2: cliente sin alquileres
        if (gestor.obtenerAlquileres(cliente).length != 0) {
            throw new AssertionError(
                "El cliente debería tener cero alquileres"
            );
        }

        // Prueba 3: cliente inicialmente no frecuente
        if (gestor.esClienteFrecuente(cliente, 3)) {
            throw new AssertionError(
                "El cliente no debería ser frecuente"
            );
        }

        // Prueba 4: registrar cuatro alquileres
        for (int i = 0; i < 4; i++) {

            Alquiler alquiler = new Alquiler(
                gestor.vehiculos[i],
                cliente,
                LocalDate.of(2026, 11, 10),
                LocalDate.of(2026, 11, 12)
            );

            gestor.agregarAlquiler(alquiler);
        }

        if (gestor.obtenerAlquileres(cliente).length != 4) {
            throw new AssertionError(
                "Error al obtener el historial"
            );
        }

        // Prueba 5: ahora es cliente frecuente
        if (!gestor.esClienteFrecuente(cliente, 3)) {
            throw new AssertionError(
                "El cliente debería ser frecuente"
            );
        }

        // Prueba 6: comprobar disponibilidad
        gestor.vehiculos[0].setDisponible(false);

        Vehiculo[] disponibles = gestor.obtenerVehiculos(
            "Turismo", Categoria.NORMAL
        );

        if (disponibles.length != 0) {
            throw new AssertionError(
                "El vehículo alquilado no debería aparecer"
            );
        }

        gestor.vehiculos[0].setDisponible(true);

        disponibles = gestor.obtenerVehiculos(
            "Turismo", Categoria.NORMAL
        );

        if (disponibles.length != 1) {
            throw new AssertionError(
                "El vehículo devuelto debería aparecer"
            );
        }

        System.out.println(
            "Todas las pruebas del ejercicio 6 superadas."
        );
    }

    public static void main(String[] args) {

        probarEjercicio6();

        GestorAlquileres app = new GestorAlquileres();
        app.menuPrincipal();
    }
}
