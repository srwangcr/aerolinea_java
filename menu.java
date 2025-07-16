import javax.swing.JOptionPane;

public class menu {
    private Login login;
    private pasajes pasajeActual;
    private PedirDatosUsuario recolectorDatos;
    
    public menu() {
        this.login = new Login();
        this.pasajeActual = null;
        this.recolectorDatos = new PedirDatosUsuario();
    }
    
    public void mostrarMenu() {
        while (true) {
            String[] opciones = obtenerOpcionesMenu();
            
            String titulo = "=== SISTEMA AEROPUERTO ===\n";
            if (login.isSesionActiva()) {
                titulo = titulo + "Usuario Activo: " + login.getUsuarioActivo() + "\n";
            } else {
                titulo = titulo + "Sin sesión activa\n";
            }
            titulo = titulo + "Seleccione una opción:";
            
            int opcion = JOptionPane.showOptionDialog(
                null,
                titulo,
                "Menú Principal",
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
                    "Iniciar Sesión",
                    "Ver Estado",
                    "Mostrar Usuario",
                    "Eliminar Usuario",
                    "Registrar Nuevo Usuario",
                    "Salir"
                };
            }
        } else {
            return new String[]{
                "Gestión de Pasajes",
                "Solicitar Datos de Reserva",
                "Gestión de Maletas",
                "Proceso de Pago",
                "Ver Datos Recopilados",
                "Cerrar Sesión",
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
                case 2: gestionMaletas(); break;
                case 3: procesoPago(); break;
                case 4: verDatosRecopilados(); break;
                case 5: cerrarSesion(); break;
                case 6: verEstado(); break;
                case 7: mostrarUsuario(); break;
                case 8: cambiarUsuario(); break;
                case 9: salir(); break;
                default: mensajeOpcionInvalida();
            }
        }
    }
    
    // === NUEVA FUNCIONALIDAD: SOLICITAR DATOS ===
    private void solicitarDatosReserva() {
        int confirmacion = JOptionPane.showConfirmDialog(
            null,
            "=== SOLICITAR DATOS DE RESERVA ===\n\n" +
            "Se le solicitarán todos los datos necesarios para su reserva:\n\n" +
            "• Información de vuelos (ida y vuelta)\n" +
            "• Datos personales\n" +
            "• Información de pasaporte\n" +
            "• Datos de contacto\n" +
            "• Información de pago\n" +
            "• Términos y condiciones\n\n" +
            "¿Desea continuar?",
            "Confirmar Recolección de Datos",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, 
                "Iniciando recolección de datos...\n\n" +
                "Por favor, complete toda la información solicitada.\n" +
                "Puede cancelar en cualquier momento presionando 'Cancelar'."
            );
            
            // Llamar al método de la clase PedirDatosUsuario
            recolectorDatos.solicitarDatos();
            
            // Mostrar mensaje de finalización
            JOptionPane.showMessageDialog(null, 
                "¡Datos recopilados exitosamente!\n\n" +
                "Toda la información ha sido registrada.\n" +
                "Puede ver un resumen en 'Ver Datos Recopilados'."
            );
            
            // Crear un pasaje básico para indicar que hay datos
            crearPasajeConDatos();
        }
    }
    
    private void crearPasajeConDatos() {
        // Crear un pasaje con datos básicos para mantener el estado
        this.pasajeActual = new pasajes(
            "", "", "", 0, 0.0, "", "",  // Datos de ida
            "", "", "", 0, 0.0, "", "",  // Datos de vuelta  
            "", "", "", "", "", "", 0, "", "",  // Datos personales
            0, "", "", "", "", "",  // Contacto
            "", "", "", "", false  // Pago y términos
        );
        
        // Asignar el usuario actual
        this.pasajeActual.setNombre(login.getUsuarioActivo());
    }
    
    private void verDatosRecopilados() {
        if (pasajeActual == null) {
            JOptionPane.showMessageDialog(null, 
                "No hay datos recopilados.\n\n" +
                "Use la opción 'Solicitar Datos de Reserva' para\n" +
                "recopilar la información necesaria."
            );
            return;
        }
        
        String resumen = "=== DATOS RECOPILADOS ===\n\n" +
                        "Usuario: " + login.getUsuarioActivo() + "\n\n" +
                        "ESTADO DE RECOLECCIÓN:\n" +
                        "✓ Datos de vuelos registrados\n" +
                        "✓ Información personal registrada\n" +
                        "✓ Datos de pasaporte registrados\n" +
                        "✓ Información de contacto registrada\n" +
                        "✓ Datos de pago registrados\n" +
                        "✓ Términos y condiciones aceptados\n\n" +
                        "PRÓXIMOS PASOS:\n" +
                        "• Revisar información en 'Gestión de Pasajes'\n" +
                        "• Configurar maletas en 'Gestión de Maletas'\n" +
                        "• Procesar pago en 'Proceso de Pago'\n\n" +
                        "Nota: Esta información se mantendrá durante\n" +
                        "toda su sesión activa.";
        
        JOptionPane.showMessageDialog(null, resumen, "Datos Recopilados", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // === MÉTODOS DE AUTENTICACIÓN (sin cambios) ===
    private void registrarUsuario() {
        login.Register();
    }
    
    private void registrarNuevoUsuario() {
        int confirmacion = JOptionPane.showConfirmDialog(
            null,
            "Esto eliminará el usuario actual. ¿Está seguro?",
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
            JOptionPane.showMessageDialog(null, "¡Bienvenido! Ahora puede acceder a todos los servicios.");
        }
    }
    
    private void cerrarSesion() {
        // Confirmar si hay datos sin guardar
        if (pasajeActual != null) {
            int confirmacion = JOptionPane.showConfirmDialog(
                null,
                "Tiene datos de reserva recopilados que se perderán\n" +
                "al cerrar la sesión. ¿Está seguro de continuar?",
                "Confirmar Cierre de Sesión",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            
            if (confirmacion != JOptionPane.YES_OPTION) {
                return; // No cerrar sesión
            }
        }
        
        login.cerrarSesion();
        this.pasajeActual = null; // Limpiar datos
        JOptionPane.showMessageDialog(null, 
            "Su sesión se ha cerrado exitosamente.\n" +
            "Todos los datos temporales han sido eliminados."
        );
    }
    
    private void cambiarUsuario() {
        int confirmacion = JOptionPane.showConfirmDialog(
            null,
            "¿Desea cerrar la sesión actual para cambiar de usuario?\n" +
            "Se perderán todos los datos no guardados.",
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
            "¿Está seguro de que desea eliminar el usuario? Esta acción no se puede deshacer.",
            "Eliminar Usuario",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            login.eliminarUsuario();
            this.pasajeActual = null; // Limpiar datos
        }
    }
    
    // === GESTIÓN DE SERVICIOS ACTUALIZADOS ===
    private void gestionPasajes() {
        if (pasajeActual == null) {
            int opcion = JOptionPane.showConfirmDialog(
                null,
                "No hay datos de reserva recopilados.\n\n" +
                "¿Desea recopilar los datos ahora?",
                "Datos Requeridos",
                JOptionPane.YES_NO_OPTION
            );
            
            if (opcion == JOptionPane.YES_OPTION) {
                solicitarDatosReserva();
                return;
            } else {
                JOptionPane.showMessageDialog(null, "Volviendo al menú principal...");
                return;
            }
        }
        
        String[] opciones = {
            "Ver Resumen de Reserva",
            "Modificar Datos de Vuelo",
            "Modificar Datos Personales",
            "Cancelar Reserva",
            "Volver al Menú Principal"
        };
        
        int opcion = JOptionPane.showOptionDialog(
            null,
            "=== GESTIÓN DE PASAJES ===\n" +
            "Usuario: " + login.getUsuarioActivo() + "\n" +
            "Estado: Datos recopilados\n\n" +
            "Seleccione una opción:",
            "Gestión de Pasajes",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );
        
        switch (opcion) {
            case 0: mostrarResumenReserva(); break;
            case 1: JOptionPane.showMessageDialog(null, "Función de modificar vuelo en desarrollo."); break;
            case 2: JOptionPane.showMessageDialog(null, "Función de modificar datos en desarrollo."); break;
            case 3: cancelarReserva(); break;
            case 4: case -1: break; // Volver
        }
    }
    
    private void mostrarResumenReserva() {
        String resumen = "=== RESUMEN DE RESERVA ===\n\n" +
                        "Usuario: " + login.getUsuarioActivo() + "\n\n" +
                        "ESTADO ACTUAL:\n" +
                        "✓ Datos de usuario recopilados\n" +
                        "✓ Información de vuelos registrada\n" +
                        "✓ Datos personales completos\n" +
                        "✓ Información de pago disponible\n\n" +
                        "PRÓXIMOS PASOS:\n" +
                        "• Configurar maletas (opcional)\n" +
                        "• Revisar información final\n" +
                        "• Procesar pago para confirmar\n\n" +
                        "Nota: Los datos detallados se procesan\n" +
                        "internamente por el sistema.";
        
        JOptionPane.showMessageDialog(null, resumen, "Resumen de Reserva", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void cancelarReserva() {
        int confirmacion = JOptionPane.showConfirmDialog(
            null,
            "¿Está seguro de que desea cancelar su reserva?\n" +
            "Se perderán todos los datos recopilados.",
            "Cancelar Reserva",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            this.pasajeActual = null;
            JOptionPane.showMessageDialog(null, "Reserva cancelada exitosamente.");
        }
    }
    
    private void gestionMaletas() {
        if (pasajeActual == null) {
            JOptionPane.showMessageDialog(null, 
                "Debe tener una reserva activa para gestionar maletas.\n" +
                "Use 'Solicitar Datos de Reserva' primero."
            );
            return;
        }
        
        JOptionPane.showMessageDialog(null,
            "=== GESTIÓN DE MALETAS ===\n\n" +
            "Usuario: " + login.getUsuarioActivo() + "\n" +
            "Reserva: Activa\n\n" +
            "Servicios disponibles:\n" +
            "• Registrar equipaje\n" +
            "• Consultar políticas\n" +
            "• Equipaje extra\n" +
            "• Rastrear maletas\n\n" +
            "(Módulo en desarrollo)",
            "Gestión de Maletas",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    private void procesoPago() {
        if (pasajeActual == null) {
            JOptionPane.showMessageDialog(null, 
                "Debe tener una reserva activa para procesar el pago.\n" +
                "Use 'Solicitar Datos de Reserva' primero."
            );
            return;
        }
        
        JOptionPane.showMessageDialog(null,
            "=== PROCESO DE PAGO ===\n\n" +
            "Usuario: " + login.getUsuarioActivo() + "\n" +
            "Reserva: Lista para pago\n\n" +
            "Métodos de pago disponibles:\n" +
            "• Tarjeta de crédito ✓\n" +
            "• Tarjeta de débito ✓\n" +
            "• Transferencia bancaria\n" +
            "• PayPal\n\n" +
            "Los datos de pago ya fueron recopilados.\n" +
            "(Procesamiento en desarrollo)",
            "Proceso de Pago",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    // === MÉTODOS DE UTILIDAD ===
    private void verEstado() {
        String usuarioActivo = login.getUsuarioActivo();
        String estado = "=== ESTADO DEL SISTEMA ===\n\n";
        
        if (login.getTotalUsuarios() == 0) {
            estado += "• Sin usuarios registrados\n• Sesión: Inactiva\n• Estado: Sistema nuevo\n\nRegistre un usuario para comenzar.";
        } else {
            estado += "• Usuario Registrado: Sí\n";
            estado += "• Usuario Activo: " + (usuarioActivo != null ? usuarioActivo : "Ninguno") + "\n";
            estado += "• Sesión: " + (login.isSesionActiva() ? "Activa" : "Inactiva") + "\n";
            estado += "• Total Usuarios: " + login.getTotalUsuarios() + "\n";
            
            if (login.isSesionActiva()) {
                estado += "\nESTADO DE RESERVAS:\n";
                estado += "• Datos Recopilados: " + (pasajeActual != null ? "Sí" : "No") + "\n";
                estado += "• Estado Sistema: " + (pasajeActual != null ? "Listo para procesar" : "Esperando datos") + "\n\n";
                estado += "¡Sistema operativo!";
            } else {
                estado += "\nInicie sesión para acceder a los servicios.";
            }
        }
        
        JOptionPane.showMessageDialog(null, estado, "Estado del Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void mensajeOpcionInvalida() {
        JOptionPane.showMessageDialog(null, "Opción no válida. Por favor seleccione una opción del menú.");
    }
    
    private void salir() {
        String mensaje = "¡Gracias por usar el Sistema Aeropuerto!";
        if (login.isSesionActiva()) {
            mensaje += "\n\nSu sesión se cerrará automáticamente.";
            if (pasajeActual != null) {
                mensaje += "\nNota: Los datos de reserva no guardados se perderán.";
            }
        }
        JOptionPane.showMessageDialog(null, mensaje);
        System.exit(0);
    }
}