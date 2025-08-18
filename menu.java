import javax.swing.JOptionPane;

public class menu {
    private Login login;
    private pasajes pasajeActual;

    // Agrega variables para maletas
    private SistemaMaletasGUI sistemaMaletas; // Instancia de SistemaMaletasGUI

    public menu() {
        this.login = new Login();
        this.pasajeActual = null;
        this.sistemaMaletas = new SistemaMaletasGUI(); // Inicializar SistemaMaletasGUI
    }

    public void mostrarMenu() {
        while (true) {
            String[] opciones = obtenerOpcionesMenu();

            String titulo = "=== SISTEMA AEROPUERTO ===\n";
            if (login.isSesionActiva()) {
                titulo = titulo + "Usuario Activo: " + login.getUsuarioActivo() + "\n";
            } else {
                titulo = titulo + "Sin sesion activa\n";
            }
            titulo = titulo + "Seleccione una opcion:";

            int opcion = JOptionPane.showOptionDialog(
                null,
                titulo,
                "Menu Principal",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );

            if (opcion == -1) {
                salir();
            } else {
                ejecutarOpcion(opcion);
            }
        }
    }

    private String[] obtenerOpcionesMenu() {
        if (!login.isSesionActiva()) {
            if (login.getTotalUsuarios() == 0) {
                return new String[]{
                    "Registrar Usuario",
                    "Ver Estado",
                    "Salir"
                };
            } else {
                return new String[]{
                    "Iniciar Sesion",
                    "Ver Estado",
                    "Mostrar Usuario",
                    "Eliminar Usuario",
                    "Registrar Nuevo Usuario",
                    "Salir"
                };
            }
        } else {
            return new String[]{
                "Gestion de Pasajes",
                "Solicitar Datos de Reserva",
                "Registrar Maletas",
                "Ver Resumen de Maletas",
                "Proceso de Pago",
                "Ver Datos Recopilados",
                "Cerrar Sesion",
                "Ver Estado",
                "Mostrar Usuario",
                "Cambiar Usuario",
                "Salir"
            };
        }
    }

    private void ejecutarOpcion(int opcion) {
        if (!login.isSesionActiva()) {
            if (login.getTotalUsuarios() == 0) {
                switch (opcion) {
                    case 0: registrarUsuario(); break;
                    case 1: verEstado(); break;
                    case 2: salir(); break;
                    default: mensajeOpcionInvalida();
                }
            } else {
                switch (opcion) {
                    case 0: iniciarSesion(); break;
                    case 1: verEstado(); break;
                    case 2: mostrarUsuario(); break;
                    case 3: eliminarUsuario(); break;
                    case 4: registrarNuevoUsuario(); break;
                    case 5: salir(); break;
                    default: mensajeOpcionInvalida();
                }
            }
        } else {
            switch (opcion) {
                case 0: gestionPasajes(); break;
                case 1: solicitarDatosReserva(); break;
                case 2: registrarMaletas(); break;
                case 3: verResumenMaletas(); break;
                case 4: procesoPago(); break;
                case 5: verDatosRecopilados(); break;
                case 6: cerrarSesion(); break;
                case 7: verEstado(); break;
                case 8: mostrarUsuario(); break;
                case 9: cambiarUsuario(); break;
                case 10: salir(); break;
                default: mensajeOpcionInvalida();
            }
        }
    }

    // === NUEVA FUNCIONALIDAD: SOLICITAR DATOS ===
    private void solicitarDatosReserva() {
        String[] metodos = {
            "Recoleccion Completa (Recomendado)",
            "Recoleccion Simplificada (Estilo Original)",
            "Cancelar"
        };

        int metodoSeleccionado = JOptionPane.showOptionDialog(
            null,
            "=== SOLICITAR DATOS DE RESERVA ===\n\n" +
            "Seleccione el metodo de recoleccion de datos:\n\n" +
            "o COMPLETA: Interfaz mejorada con validaciones\n" +
            "o SIMPLIFICADA: Estilo original con precios automaticos\n\n" +
            "Ambos metodos recopilan:\n" +
            "o Informacion de vuelos (ida y vuelta)\n" +
            "o Datos personales y pasaporte\n" +
            "o Informacion de contacto y pago\n" +
            "o Terminos y condiciones",
            "Metodo de Recoleccion",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            metodos,
            metodos[0]
        );

        switch (metodoSeleccionado) {
            case 0: // Recoleccion Completa
                solicitarDatosCompletos();
                break;
            case 1: // Recoleccion Simplificada
                solicitarDatosSimplificados();
                break;
            case 2: case -1: // Cancelar
                return;
        }
    }

    private void solicitarDatosCompletos() {
        JOptionPane.showMessageDialog(null,
            "=== RECOLECCION COMPLETA ===\n\n" +
            "Se abrira una interfaz completa con validaciones.\n" +
            "Puede cancelar en cualquier momento presionando 'Cancelar'."
        );

        PedirDatosUsuarioMejorado recolectorMejorado = new PedirDatosUsuarioMejorado();
        this.pasajeActual = recolectorMejorado.solicitarDatosCompletos();
        if (this.pasajeActual != null) {
            this.pasajeActual.setNombre(login.getUsuarioActivo());
            JOptionPane.showMessageDialog(null,
                "Datos recopilados exitosamente!\n\n" +
                "Toda la informacion ha sido registrada.\n" +
                "Puede ver un resumen en 'Ver Datos Recopilados'."
            );
        } else {
            JOptionPane.showMessageDialog(null,
                "Recoleccion de datos cancelada.\n" +
                "Puede intentar nuevamente cuando desee."
            );
        }
    }

    private void solicitarDatosSimplificados() {
        JOptionPane.showMessageDialog(null,
            "=== RECOLECCION SIMPLIFICADA ===\n\n" +
            "Los precios seran asignados automaticamente.\n" +
            "Complete la informacion solicitada."
        );

        PedirDatosUsuarioOriginal recolectorOriginal = new PedirDatosUsuarioOriginal();
        this.pasajeActual = recolectorOriginal.solicitarDatos();
        if (this.pasajeActual != null) {
            this.pasajeActual.setNombre(login.getUsuarioActivo());
            JOptionPane.showMessageDialog(null,
                "Datos recopilados exitosamente!\n\n" +
                "Los precios han sido asignados automaticamente.\n" +
                "Total: $" + (this.pasajeActual.getIda_precio() + this.pasajeActual.getVuelta_precio())
            );
        } else {
            JOptionPane.showMessageDialog(null,
                "Recoleccion de datos cancelada.\n" +
                "Puede intentar nuevamente cuando desee."
            );
        }
    }

    private void verDatosRecopilados() {
        if (pasajeActual == null) {
            JOptionPane.showMessageDialog(null,
                "No hay datos recopilados.\n\n" +
                "Use la opcion 'Solicitar Datos de Reserva' para\n" +
                "recopilar la informacion necesaria."
            );
            return;
        }

        String resumen = "=== DATOS RECOPILADOS ===\n\n" +
                        "Usuario: " + login.getUsuarioActivo() + "\n\n" +
                        "INFORMACION DE VUELOS:\n" +
                        "o Vuelo de Ida: " + pasajeActual.getIda_origen() + " -> " + pasajeActual.getIda_destino() + "\n" +
                        "o Fecha Ida: " + pasajeActual.getIda_fecha() + "\n" +
                        "o Pasajeros Ida: " + pasajeActual.getIda_pasajeros() + "\n" +
                        "o Precio Ida: $" + pasajeActual.getIda_precio() + "\n\n" +
                        "o Vuelo de Vuelta: " + pasajeActual.getVuelta_origen() + " -> " + pasajeActual.getVuelta_destino() + "\n" +
                        "o Fecha Vuelta: " + pasajeActual.getVuelta_fecha() + "\n" +
                        "o Pasajeros Vuelta: " + pasajeActual.getVuelta_pasajeros() + "\n" +
                        "o Precio Vuelta: $" + pasajeActual.getVuelta_precio() + "\n\n" +
                        "INFORMACION PERSONAL:\n" +
                        "o Nombre: " + pasajeActual.getNombre() + " " + pasajeActual.getApellido() + "\n" +
                        "o Nacionalidad: " + pasajeActual.getNacionalidad() + "\n" +
                        "o Email: " + pasajeActual.getEmail() + "\n" +
                        "o Telefono: " + pasajeActual.getTelefono() + "\n\n" +
                        "ESTADO:\n" +
                        "o Datos de vuelos registrados\n" +
                        "o Informacion personal completa\n" +
                        "o Datos de pasaporte registrados\n" +
                        "o Informacion de contacto completa\n" +
                        "o Datos de pago registrados\n" +
                        "o Terminos y condiciones: " + (pasajeActual.getAceptar_terminos() ? "Aceptados" : "Pendientes") + "\n\n" +
                        "PRECIO TOTAL: $" + (pasajeActual.getIda_precio() + pasajeActual.getVuelta_precio());

        JOptionPane.showMessageDialog(null, resumen, "Datos Recopilados", JOptionPane.INFORMATION_MESSAGE);
    }

    // === METODOS DE AUTENTICACION (sin cambios) ===
    private void registrarUsuario() {
        login.Register();
    }

    private void registrarNuevoUsuario() {
        int confirmacion = JOptionPane.showConfirmDialog(
            null,
            "Esto eliminara el usuario actual. Esta seguro?",
            "Confirmar",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            login.eliminarUsuario();
            login.Register();
        }
    }

    private void iniciarSesion() {
        boolean loginExitoso = login.iniciarSesion();
        if (loginExitoso) {
            JOptionPane.showMessageDialog(null, "Bienvenido! Ahora puede acceder a todos los servicios.");
        }
    }

    private void cerrarSesion() {
        // Confirmar si hay datos sin guardar
        if (pasajeActual != null) {
            int confirmacion = JOptionPane.showConfirmDialog(
                null,
                "Tiene datos de reserva recopilados que se perderan\n" +
                "al cerrar la sesion. Esta seguro de continuar?",
                "Confirmar Cierre de Sesion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (confirmacion != JOptionPane.YES_OPTION) {
                return; // No cerrar sesion
            }
        }

        login.cerrarSesion();
        this.pasajeActual = null; // Limpiar datos
        JOptionPane.showMessageDialog(null,
            "Su sesion se ha cerrado exitosamente.\n" +
            "Todos los datos temporales han sido eliminados."
        );
    }

    private void cambiarUsuario() {
        int confirmacion = JOptionPane.showConfirmDialog(
            null,
            "Desea cerrar la sesion actual para cambiar de usuario?\n" +
            "Se perderan todos los datos no guardados.",
            "Cambiar Usuario",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            login.cambiarUsuario();
            this.pasajeActual = null; // Limpiar datos
        }
    }

    private void mostrarUsuario() {
        login.mostrarUsuarios();
    }

    private void eliminarUsuario() {
        int confirmacion = JOptionPane.showConfirmDialog(
            null,
            "Esta seguro de que desea eliminar el usuario? Esta accion no se puede deshacer.",
            "Eliminar Usuario",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            login.eliminarUsuario();
            this.pasajeActual = null; // Limpiar datos
        }
    }

    // === GESTION DE SERVICIOS ACTUALIZADOS ===
    private void gestionPasajes() {
        if (pasajeActual == null) {
            int opcion = JOptionPane.showConfirmDialog(
                null,
                "No hay datos de reserva recopilados.\n\n" +
                "Desea recopilar los datos ahora?\n" +
                "(Podra elegir entre recoleccion completa o simplificada)",
                "Datos Requeridos",
                JOptionPane.YES_NO_OPTION
            );

            if (opcion == JOptionPane.YES_OPTION) {
                solicitarDatosReserva();
                return;
            } else {
                JOptionPane.showMessageDialog(null, "Volviendo al menu principal...");
                return;
            }
        }

        String[] opciones = {
            "Ver Resumen de Reserva",
            "Modificar Datos de Vuelo",
            "Modificar Datos Personales",
            "Cancelar Reserva",
            "Volver al Menu Principal"
        };

        int opcion = JOptionPane.showOptionDialog(
            null,
            "=== GESTION DE PASAJES ===\n" +
            "Usuario: " + login.getUsuarioActivo() + "\n" +
            "Estado: Datos recopilados\n\n" +
            "Seleccione una opcion:",
            "Gestion de Pasajes",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );

        switch (opcion) {
            case 0: mostrarResumenReserva(); break;
            case 1: JOptionPane.showMessageDialog(null, "Funcion de modificar vuelo en desarrollo."); break;
            case 2: JOptionPane.showMessageDialog(null, "Funcion de modificar datos en desarrollo."); break;
            case 3: cancelarReserva(); break;
            case 4: case -1: break; // Volver
        }
    }

    private void mostrarResumenReserva() {
        String resumen = "=== RESUMEN DE RESERVA ===\n\n" +
                        "Usuario: " + login.getUsuarioActivo() + "\n\n" +
                        "VUELOS PROGRAMADOS:\n" +
                        "--------------------------------------------------\n" +
                        "VUELO DE IDA:\n" +
                        "o Ruta: " + pasajeActual.getIda_origen() + " -> " + pasajeActual.getIda_destino() + "\n" +
                        "o Fecha: " + pasajeActual.getIda_fecha() + "\n" +
                        "o Pasajeros: " + pasajeActual.getIda_pasajeros() + "\n" +
                        "o Precio: $" + pasajeActual.getIda_precio() + "\n" +
                        "o Extras: " + (pasajeActual.getIda_opcionesExtra().isEmpty() ? "Ninguno" : pasajeActual.getIda_opcionesExtra()) + "\n\n" +
                        "VUELO DE VUELTA:\n" +
                        "o Ruta: " + pasajeActual.getVuelta_origen() + " -> " + pasajeActual.getVuelta_destino() + "\n" +
                        "o Fecha: " + pasajeActual.getVuelta_fecha() + "\n" +
                        "o Pasajeros: " + pasajeActual.getVuelta_pasajeros() + "\n" +
                        "o Precio: $" + pasajeActual.getVuelta_precio() + "\n" +
                        "o Extras: " + (pasajeActual.getVuelta_opcionesExtra().isEmpty() ? "Ninguno" : pasajeActual.getVuelta_opcionesExtra()) + "\n\n" +
                        "--------------------------------------------------\n" +
                        "PASAJERO PRINCIPAL:\n" +
                        "o Nombre: " + pasajeActual.getNombre() + " " + pasajeActual.getApellido() + "\n" +
                        "o Nacionalidad: " + pasajeActual.getNacionalidad() + "\n" +
                        "o Pasaporte: " + pasajeActual.getNumero_de_pasaporte() + "\n" +
                        "o Email: " + pasajeActual.getEmail() + "\n\n" +
                        "--------------------------------------------------\n" +
                        "PRECIO TOTAL: $" + (pasajeActual.getIda_precio() + pasajeActual.getVuelta_precio()) + "\n" +
                        "ESTADO: " + (pasajeActual.getAceptar_terminos() ? "Listo para pago" : "Terminos pendientes");

        JOptionPane.showMessageDialog(null, resumen, "Resumen de Reserva", JOptionPane.INFORMATION_MESSAGE);
    }

    private void cancelarReserva() {
        int confirmacion = JOptionPane.showConfirmDialog(
            null,
            "Esta seguro de que desea cancelar su reserva?\n" +
            "Se perderan todos los datos recopilados.",
            "Cancelar Reserva",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            this.pasajeActual = null;
            JOptionPane.showMessageDialog(null, "Reserva cancelada exitosamente.");
        }
    }

    // Metodo para registrar maletas (llama al metodo de SistemaMaletasGUI)
    private void registrarMaletas() {
        if (pasajeActual == null) {
            JOptionPane.showMessageDialog(null,
                "Debe tener una reserva activa para registrar maletas.\n" +
                "Use 'Solicitar Datos de Reserva' primero."
            );
            return;
        }
        sistemaMaletas.registrarMaletas();
    }

    // Metodo para ver resumen de maletas (llama a los metodos de SistemaMaletasGUI)
    private void verResumenMaletas() {
        if (sistemaMaletas.getCantidadMaletas() == 0) {
            JOptionPane.showMessageDialog(null,
                "No hay maletas registradas. Use 'Registrar Maletas' primero."
            );
            return;
        }

        StringBuilder resumen = new StringBuilder();
        resumen.append("--- RESUMEN DE EQUIPAJE REGISTRADO ---\n");
        for (int i = 0; i < sistemaMaletas.getCantidadMaletas(); i++) {
            Maleta m = sistemaMaletas.getMaletas()[i];
            resumen.append("Maleta #" + (i+1) + ": " +
                m.getTipo() + ", " +
                m.getPeso() + " kg, " +
                "Precio final: $" + m.getCosto() + "\n");
        }
        JOptionPane.showMessageDialog(null,
            resumen.toString(),
            "Resumen de Maletas",
            JOptionPane.INFORMATION_MESSAGE);
    }


    private void procesoPago() {
        if (pasajeActual == null) {
            JOptionPane.showMessageDialog(null,
                "Debe tener una reserva activa para procesar el pago.\n" +
                "Use 'Solicitar Datos de Reserva' primero."
            );
            return;
        }

        proceso_pago validador = new proceso_pago();
        String[] fecha = pasajeActual.getFecha_vencimiento_tarjeta().split("/");
        int mes = 0;
        int anio = 0;
        int cvv = 0;

        try {
            mes = Integer.parseInt(fecha[0]);
            anio = Integer.parseInt(fecha[1]);
            cvv = Integer.parseInt(pasajeActual.getCodigo_seguridad_tarjeta());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error en el formato de los datos de la tarjeta (mes/anio/CVV). Corrija antes de continuar.");
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
             JOptionPane.showMessageDialog(null, "Formato de fecha de vencimiento incorrecto (debe ser MM/AA). Corrija antes de continuar.");
             return;
        }


        if (validador.validacionFechaMes(mes) ||
            validador.validacionFechaAnio(anio) ||
            validador.validacionCVV(cvv)) {
            JOptionPane.showMessageDialog(null, "Datos de tarjeta invalidos. Corrija antes de continuar.");
            return;
        }

        // Mostrar resumen de pago
        double total = pasajeActual.getIda_precio() + pasajeActual.getVuelta_precio();
        String tarjeta = pasajeActual.getNumeero_tarjeta();
        String tarjetaOculta = tarjeta.length() > 4 ? "**** **** **** " + tarjeta.substring(tarjeta.length() - 4) : "****";

        String resumenPago = "=== PROCESO DE PAGO ===\n\n" +
                           "RESUMEN DE TRANSACCION:\n" +
                           "--------------------------------------------------\n" +
                           "Cliente: " + pasajeActual.getNombre() + " " + pasajeActual.getApellido() + "\n" +
                           "Email: " + pasajeActual.getEmail() + "\n\n" +
                           "DESGLOSE DE PRECIOS:\n" +
                           "o Vuelo Ida (" + pasajeActual.getIda_origen() + " -> " + pasajeActual.getIda_destino() + "): $" + pasajeActual.getIda_precio() + "\n" +
                           "o Vuelo Vuelta (" + pasajeActual.getVuelta_origen() + " -> " + pasajeActual.getVuelta_destino() + "): $" + pasajeActual.getVuelta_precio() + "\n" +
                           "o Pasajeros: " + pasajeActual.getIda_pasajeros() + "\n" +
                           "--------------------------------------------------\n" +
                           "TOTAL A PAGAR: $" + total + "\n\n" +
                           "METODO DE PAGO:\n" +
                           "o Titular: " + pasajeActual.getNombre_titular_tarjeta() + "\n" +
                           "o Tarjeta: " + tarjetaOculta + "\n" +
                           "o Vencimiento: " + pasajeActual.getFecha_vencimiento_tarjeta() + "\n\n" +
                           "Desea procesar el pago ahora?";

        int confirmacion = JOptionPane.showConfirmDialog(
            null,
            resumenPago,
            "Confirmar Pago",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            // Simular procesamiento de pago
            JOptionPane.showMessageDialog(null,
                "Procesando pago...\n\n" +
                "o Verificando datos de tarjeta\n" +
                "o Contactando banco\n" +
                "o Autorizando transaccion\n" +
                "o Generando confirmacion"
            );

            // Generar numero de confirmacion aleatorio
            int numeroConfirmacion = (int)(Math.random() * 900000) + 100000;

            JOptionPane.showMessageDialog(null,
                "PAGO PROCESADO EXITOSAMENTE!\n\n" +
                "DETALLES DE CONFIRMACION:\n" +
                "--------------------------------------------------\n" +
                "Numero de Confirmacion: " + numeroConfirmacion + "\n" +
                "Fecha: " + java.time.LocalDate.now() + "\n" +
                "Monto: $" + total + "\n" +
                "Estado: CONFIRMADO\n\n" +
                "Su reserva ha sido confirmada.\n" +
                "Recibira un email de confirmacion en:\n" +
                pasajeActual.getEmail() + "\n\n" +
                "Buen viaje!",
                "Pago Confirmado",
                JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(null, "Pago cancelado. Puede procesar el pago mas tarde.");
        }
    }

    // === METODOS DE UTILIDAD ===
    private void verEstado() {
        String usuarioActivo = login.getUsuarioActivo();
        String estado = "=== ESTADO DEL SISTEMA ===\n\n";

        if (login.getTotalUsuarios() == 0) {
            estado += "o Sin usuarios registrados\no Sesion: Inactiva\no Estado: Sistema nuevo\n\nRegistre un usuario para comenzar.";
        } else {
            estado += "o Usuario Registrado: Si\n";
            estado += "o Usuario Activo: " + (usuarioActivo != null ? usuarioActivo : "Ninguno") + "\n";
            estado += "o Sesion: " + (login.isSesionActiva() ? "Activa" : "Inactiva") + "\n";
            estado += "o Total Usuarios: " + login.getTotalUsuarios() + "\n";

            if (login.isSesionActiva()) {
                estado += "\nESTADO DE RESERVAS:\n";
                estado += "o Datos Recopilados: " + (pasajeActual != null ? "Si" : "No") + "\n";
                estado += "o Estado Sistema: " + (pasajeActual != null ? "Listo para procesar" : "Esperando datos") + "\n\n";
                estado += "Sistema operativo!";
            } else {
                estado += "\nInicie sesion para acceder a los servicios.";
            }
        }

        JOptionPane.showMessageDialog(null, estado, "Estado del Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mensajeOpcionInvalida() {
        JOptionPane.showMessageDialog(null, "Opcion no valida. Por favor seleccione una opcion del menu.");
    }

    private void salir() {
        String mensaje = "Gracias por usar el Sistema Aeropuerto!";
        if (login.isSesionActiva()) {
            mensaje += "\n\nSu sesion se cerrara automaticamente.";
            if (pasajeActual != null) {
                mensaje += "\nNota: Los datos de reserva no guardados se perderan.";
            }
        }
        JOptionPane.showMessageDialog(null, mensaje);
        System.exit(0);
    }
}

// === CLASE AUXILIAR PARA RECOLECCION MEJORADA DE DATOS ===
class PedirDatosUsuarioMejorado {

    public pasajes solicitarDatosCompletos() {
        java.util.Random rand = new java.util.Random();
        double[] precios = {560.0, 650.0, 490.0, 340.0, 750.0, 820.0, 430.0, 590.0};

        // === DATOS DE VUELO DE IDA ===
        String ida_origen = JOptionPane.showInputDialog("=== VUELO DE IDA ===\n\nIngrese el origen del viaje de ida:");
        if (ida_origen == null) return null; // Usuario cancelo

        String ida_destino = JOptionPane.showInputDialog(
            "Seleccione el destino del viaje de ida (Escriba el numero):\n\n" +
            "1. Madrid\n" +
            "2. Barcelona\n" +
            "3. Moscu\n" +
            "4. Osaka\n" +
            "5. Bogota");
        if (ida_destino == null) return null;

        ida_destino = convertirDestino(ida_destino);
        if (ida_destino == null) return null;

        String ida_fecha = JOptionPane.showInputDialog("Ingrese la fecha del viaje de ida (DD/MM/AAAA):");
        if (ida_fecha == null) return null;

        int ida_pasajeros = 0;
        String ida_pasajeros_str = JOptionPane.showInputDialog("Ingrese el numero de pasajeros para el viaje de ida:");
        if (ida_pasajeros_str == null) return null;
        try {
            ida_pasajeros = Integer.parseInt(ida_pasajeros_str);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Numero de pasajeros invalido. Ingrese un numero entero.");
            return null;
        }

        // Generar precio aleatorio para ida
        double ida_precio = precios[rand.nextInt(precios.length)];
        JOptionPane.showMessageDialog(null, "El precio del viaje de ida es: $" + ida_precio);

        String ida_opcionesExtra = JOptionPane.showInputDialog("Ingrese las opciones extra para el viaje de ida (opcional):");
        if (ida_opcionesExtra == null) ida_opcionesExtra = "";

        // === DATOS DE VUELO DE VUELTA ===
        String vuelta_origen = JOptionPane.showInputDialog("=== VUELO DE VUELTA ===\n\nIngrese el origen del viaje de vuelta:");
        if (vuelta_origen == null) return null;

        String vuelta_destino = JOptionPane.showInputDialog(
            "Seleccione el destino del viaje de vuelta (Escriba el numero):\n\n" +
            "1. Madrid\n" +
            "2. Barcelona\n" +
            "3. Moscu\n" +
            "4. Osaka\n" +
            "5. Bogota\n" +
            "6. Mismo que origen de ida (" + ida_origen + ")");
        if (vuelta_destino == null) return null;

        if (vuelta_destino.equals("6")) {
            vuelta_destino = ida_origen;
        } else {
            vuelta_destino = convertirDestino(vuelta_destino);
            if (vuelta_destino == null) return null;
        }

        String vuelta_fecha = JOptionPane.showInputDialog("Ingrese la fecha del viaje de vuelta (DD/MM/AAAA):");
        if (vuelta_fecha == null) return null;

        int vuelta_pasajeros = 0;
        String vuelta_pasajeros_str = JOptionPane.showInputDialog("Ingrese el numero de pasajeros para el viaje de vuelta:");
        if (vuelta_pasajeros_str == null) return null;
        try {
            vuelta_pasajeros = Integer.parseInt(vuelta_pasajeros_str);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Numero de pasajeros invalido. Ingrese un numero entero.");
            return null;
        }

        // Generar precio aleatorio para vuelta
        double vuelta_precio = precios[rand.nextInt(precios.length)];
        JOptionPane.showMessageDialog(null, "El precio del viaje de vuelta es: $" + vuelta_precio);

        String vuelta_opcionesExtra = JOptionPane.showInputDialog("Ingrese las opciones extra para el viaje de vuelta (opcional):");
        if (vuelta_opcionesExtra == null) vuelta_opcionesExtra = "";

        // === DATOS PERSONALES ===
        String datos = JOptionPane.showInputDialog("=== DATOS PERSONALES ===\n\nIngrese informacion adicional (opcional):");
        if (datos == null) datos = "";

        String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
        if (nombre == null) return null;

        String apellido = JOptionPane.showInputDialog("Ingrese su apellido:");
        if (apellido == null) return null;

        String nacionalidad = JOptionPane.showInputDialog("Ingrese su nacionalidad:");
        if (nacionalidad == null) return null;

        String pais_de_residencia = JOptionPane.showInputDialog("Ingrese su pais de residencia:");
        if (pais_de_residencia == null) return null;

        String fecha_de_nacimiento = JOptionPane.showInputDialog("Ingrese su fecha de nacimiento (DD/MM/AAAA):");
        if (fecha_de_nacimiento == null) return null;

        // === DATOS DE PASAPORTE ===
        String numero_de_pasaporte = JOptionPane.showInputDialog("=== DATOS DE PASAPORTE ===\n\nIngrese su numero de pasaporte:");
        if (numero_de_pasaporte == null) return null;

        String pais_emisor_pasaporte = JOptionPane.showInputDialog("Ingrese el pais emisor de su pasaporte:");
        if (pais_emisor_pasaporte == null) return null;

        String fecha_expiracion_pasaporte = JOptionPane.showInputDialog("Ingrese la fecha de expiracion de su pasaporte (DD/MM/AAAA):");
        if (fecha_expiracion_pasaporte == null) return null;

        // === DATOS DE CONTACTO ===
        int telefono = 0;
        String telefono_str = JOptionPane.showInputDialog("=== DATOS DE CONTACTO ===\n\nIngrese su numero de telefono:");
        if (telefono_str == null) return null;
        try {
            telefono = Integer.parseInt(telefono_str);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Numero de telefono invalido. Ingrese un numero entero.");
            return null;
        }

        String email = JOptionPane.showInputDialog("Ingrese su correo electronico:");
        if (email == null) return null;

        String codigoPostal = JOptionPane.showInputDialog("Ingrese su codigo postal:");
        if (codigoPostal == null) return null;

        String poblacion = JOptionPane.showInputDialog("Ingrese su poblacion:");
        if (poblacion == null) return null;

        // === DATOS DE PAGO ===
        String numero_tarjeta = JOptionPane.showInputDialog("=== DATOS DE PAGO ===\n\nIngrese el numero de su tarjeta:");
        if (numero_tarjeta == null) return null;

        String nombre_titular_tarjeta = JOptionPane.showInputDialog("Ingrese el nombre del titular de la tarjeta:");
        if (nombre_titular_tarjeta == null) return null;

        String fecha_vencimiento_tarjeta = JOptionPane.showInputDialog("Ingrese la fecha de vencimiento de su tarjeta (MM/AA):");
        if (fecha_vencimiento_tarjeta == null) return null;

        String codigo_seguridad_tarjeta = JOptionPane.showInputDialog("Ingrese el codigo de seguridad de su tarjeta (CVV):");
        if (codigo_seguridad_tarjeta == null) return null;

        // === TERMINOS Y CONDICIONES ===
        boolean aceptar_terminos = JOptionPane.showConfirmDialog(
            null,
            "=== TERMINOS Y CONDICIONES ===\n\n" +
            "Al aceptar, usted confirma que:\n" +
            "o Ha leido y acepta los terminos de servicio\n" +
            "o La informacion proporcionada es correcta\n" +
            "o Autoriza el procesamiento de sus datos\n" +
            "o Acepta las politicas de cancelacion\n\n" +
            "Acepta los terminos y condiciones?",
            "Terminos y Condiciones",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        ) == JOptionPane.YES_OPTION;

        if (!aceptar_terminos) {
            JOptionPane.showMessageDialog(null, "Debe aceptar los terminos y condiciones para continuar.");
            return null;
        }

        // Mostrar resumen de precios
        double total = ida_precio + vuelta_precio;
        JOptionPane.showMessageDialog(null,
            "=== RESUMEN DE PRECIOS ===\n\n" +
            "Vuelo de Ida: $" + ida_precio + "\n" +
            "Vuelo de Vuelta: $" + vuelta_precio + "\n" +
            "--------------------------------------------------\n" +
            "TOTAL: $" + total + "\n\n" +
            "Los precios han sido asignados automaticamente\n" +
            "segun disponibilidad y demanda."
        );

        // === CREAR OBJETO PASAJES CON TODOS LOS DATOS ===
        return new pasajes(
            ida_origen, ida_destino, ida_fecha, ida_pasajeros, ida_precio, ida_opcionesExtra,
            vuelta_origen, vuelta_destino, vuelta_fecha, vuelta_pasajeros, vuelta_precio, vuelta_opcionesExtra,
            datos, nombre, apellido, nacionalidad, pais_de_residencia, fecha_de_nacimiento,
            numero_de_pasaporte, pais_emisor_pasaporte, fecha_expiracion_pasaporte,
            telefono, email, codigoPostal, poblacion,
            numero_tarjeta, nombre_titular_tarjeta, fecha_vencimiento_tarjeta, codigo_seguridad_tarjeta, aceptar_terminos
        );
    }

    private String convertirDestino(String opcion) {
        switch (opcion) {
            case "1": return "Madrid";
            case "2": return "Barcelona";
            case "3": return "Moscu";
            case "4": return "Osaka";
            case "5": return "Bogota";
            default:
                JOptionPane.showMessageDialog(null, "Destino no valido. Por favor, intente de nuevo.");
                return null;
        }
    }
}
