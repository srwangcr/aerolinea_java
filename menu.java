import javax.swing.JOptionPane;

public class menu {
    private Login login;
    private pasajes pasajeActual;
    
    public menu() {
        this.login = new Login();
        this.pasajeActual = null;
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
            
            try {
                // Crear una nueva instancia mejorada del recolector de datos
                PedirDatosUsuarioMejorado recolectorMejorado = new PedirDatosUsuarioMejorado();
                this.pasajeActual = recolectorMejorado.solicitarDatosCompletos();
                
                if (this.pasajeActual != null) {
                    // Asignar el usuario actual al pasaje
                    this.pasajeActual.setNombre(login.getUsuarioActivo());
                    
                    // Mostrar mensaje de finalización
                    JOptionPane.showMessageDialog(null, 
                        "¡Datos recopilados exitosamente!\n\n" +
                        "Toda la información ha sido registrada.\n" +
                        "Puede ver un resumen en 'Ver Datos Recopilados'."
                    );
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "Recolección de datos cancelada.\n" +
                        "Puede intentar nuevamente cuando desee."
                    );
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                    "Error al recopilar datos: " + e.getMessage() + "\n" +
                    "Por favor, intente nuevamente."
                );
            }
        }
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
                        "INFORMACIÓN DE VUELOS:\n" +
                        "• Vuelo de Ida: " + pasajeActual.getIda_origen() + " → " + pasajeActual.getIda_destino() + "\n" +
                        "• Fecha Ida: " + pasajeActual.getIda_fecha() + "\n" +
                        "• Pasajeros Ida: " + pasajeActual.getIda_pasajeros() + "\n" +
                        "• Precio Ida: $" + pasajeActual.getIda_precio() + "\n\n" +
                        "• Vuelo de Vuelta: " + pasajeActual.getVuelta_origen() + " → " + pasajeActual.getVuelta_destino() + "\n" +
                        "• Fecha Vuelta: " + pasajeActual.getVuelta_fecha() + "\n" +
                        "• Pasajeros Vuelta: " + pasajeActual.getVuelta_pasajeros() + "\n" +
                        "• Precio Vuelta: $" + pasajeActual.getVuelta_precio() + "\n\n" +
                        "INFORMACIÓN PERSONAL:\n" +
                        "• Nombre: " + pasajeActual.getNombre() + " " + pasajeActual.getApellido() + "\n" +
                        "• Nacionalidad: " + pasajeActual.getNacionalidad() + "\n" +
                        "• Email: " + pasajeActual.getEmail() + "\n" +
                        "• Teléfono: " + pasajeActual.getTelefono() + "\n\n" +
                        "ESTADO:\n" +
                        "✓ Datos de vuelos registrados\n" +
                        "✓ Información personal completa\n" +
                        "✓ Datos de pasaporte registrados\n" +
                        "✓ Información de contacto completa\n" +
                        "✓ Datos de pago registrados\n" +
                        "✓ Términos y condiciones: " + (pasajeActual.getAceptar_terminos() ? "Aceptados" : "Pendientes") + "\n\n" +
                        "PRECIO TOTAL: $" + (pasajeActual.getIda_precio() + pasajeActual.getVuelta_precio());
        
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
                        "VUELOS PROGRAMADOS:\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                        "VUELO DE IDA:\n" +
                        "• Ruta: " + pasajeActual.getIda_origen() + " → " + pasajeActual.getIda_destino() + "\n" +
                        "• Fecha: " + pasajeActual.getIda_fecha() + "\n" +
                        "• Pasajeros: " + pasajeActual.getIda_pasajeros() + "\n" +
                        "• Precio: $" + pasajeActual.getIda_precio() + "\n" +
                        "• Paquete: " + (pasajeActual.getIda_turistaPaquete().isEmpty() ? "Ninguno" : pasajeActual.getIda_turistaPaquete()) + "\n\n" +
                        "VUELO DE VUELTA:\n" +
                        "• Ruta: " + pasajeActual.getVuelta_origen() + " → " + pasajeActual.getVuelta_destino() + "\n" +
                        "• Fecha: " + pasajeActual.getVuelta_fecha() + "\n" +
                        "• Pasajeros: " + pasajeActual.getVuelta_pasajeros() + "\n" +
                        "• Precio: $" + pasajeActual.getVuelta_precio() + "\n" +
                        "• Paquete: " + (pasajeActual.getVuelta_turistaPaquete().isEmpty() ? "Ninguno" : pasajeActual.getVuelta_turistaPaquete()) + "\n\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                        "PASAJERO PRINCIPAL:\n" +
                        "• Nombre: " + pasajeActual.getNombre() + " " + pasajeActual.getApellido() + "\n" +
                        "• Nacionalidad: " + pasajeActual.getNacionalidad() + "\n" +
                        "• Pasaporte: " + pasajeActual.getNumero_de_pasaporte() + "\n" +
                        "• Email: " + pasajeActual.getEmail() + "\n\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                        "PRECIO TOTAL: $" + (pasajeActual.getIda_precio() + pasajeActual.getVuelta_precio()) + "\n" +
                        "ESTADO: " + (pasajeActual.getAceptar_terminos() ? "Listo para pago" : "Términos pendientes");
        
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
        
        // Mostrar resumen de pago
        double total = pasajeActual.getIda_precio() + pasajeActual.getVuelta_precio();
        String tarjeta = pasajeActual.getNumeero_tarjeta();
        String tarjetaOculta = tarjeta.length() > 4 ? "**** **** **** " + tarjeta.substring(tarjeta.length() - 4) : "****";
        
        String resumenPago = "=== PROCESO DE PAGO ===\n\n" +
                           "RESUMEN DE TRANSACCIÓN:\n" +
                           "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                           "Cliente: " + pasajeActual.getNombre() + " " + pasajeActual.getApellido() + "\n" +
                           "Email: " + pasajeActual.getEmail() + "\n\n" +
                           "DESGLOSE DE PRECIOS:\n" +
                           "• Vuelo Ida (" + pasajeActual.getIda_origen() + " → " + pasajeActual.getIda_destino() + "): $" + pasajeActual.getIda_precio() + "\n" +
                           "• Vuelo Vuelta (" + pasajeActual.getVuelta_origen() + " → " + pasajeActual.getVuelta_destino() + "): $" + pasajeActual.getVuelta_precio() + "\n" +
                           "• Pasajeros: " + pasajeActual.getIda_pasajeros() + "\n" +
                           "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                           "TOTAL A PAGAR: $" + total + "\n\n" +
                           "MÉTODO DE PAGO:\n" +
                           "• Titular: " + pasajeActual.getNombre_titular_tarjeta() + "\n" +
                           "• Tarjeta: " + tarjetaOculta + "\n" +
                           "• Vencimiento: " + pasajeActual.getFecha_vencimiento_tarjeta() + "\n\n" +
                           "¿Desea procesar el pago ahora?";
        
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
                "• Verificando datos de tarjeta ✓\n" +
                "• Contactando banco ✓\n" +
                "• Autorizando transacción ✓\n" +
                "• Generando confirmación ✓"
            );
            
            // Generar número de confirmación aleatorio
            int numeroConfirmacion = (int)(Math.random() * 900000) + 100000;
            
            JOptionPane.showMessageDialog(null,
                "🎉 ¡PAGO PROCESADO EXITOSAMENTE! 🎉\n\n" +
                "DETALLES DE CONFIRMACIÓN:\n" +
                "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                "Número de Confirmación: " + numeroConfirmacion + "\n" +
                "Fecha: " + java.time.LocalDate.now() + "\n" +
                "Monto: $" + total + "\n" +
                "Estado: CONFIRMADO\n\n" +
                "Su reserva ha sido confirmada.\n" +
                "Recibirá un email de confirmación en:\n" +
                pasajeActual.getEmail() + "\n\n" +
                "¡Buen viaje!",
                "Pago Confirmado",
                JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(null, "Pago cancelado. Puede procesar el pago más tarde.");
        }
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

// === CLASE AUXILIAR PARA RECOLECCIÓN MEJORADA DE DATOS ===
class PedirDatosUsuarioMejorado {
    
    public pasajes solicitarDatosCompletos() {
        try {
            // === DATOS DE VUELO DE IDA ===
            String ida_origen = JOptionPane.showInputDialog("=== VUELO DE IDA ===\n\nIngrese el origen del viaje de ida:");
            if (ida_origen == null) return null; // Usuario canceló
            
            String ida_destino = JOptionPane.showInputDialog(
                "Seleccione el destino del viaje de ida (Escriba el número):\n\n" +
                "1. Madrid\n" +
                "2. Barcelona\n" +
                "3. Moscú\n" +
                "4. Osaka\n" +
                "5. Bogotá");
            if (ida_destino == null) return null;
            
            ida_destino = convertirDestino(ida_destino);
            if (ida_destino == null) return null;
            
            String ida_fecha = JOptionPane.showInputDialog("Ingrese la fecha del viaje de ida (DD/MM/AAAA):");
            if (ida_fecha == null) return null;
            
            String ida_pasajeros_str = JOptionPane.showInputDialog("Ingrese el número de pasajeros para el viaje de ida:");
            if (ida_pasajeros_str == null) return null;
            int ida_pasajeros = Integer.parseInt(ida_pasajeros_str);
            
            String ida_precio_str = JOptionPane.showInputDialog("Ingrese el precio del viaje de ida:");
            if (ida_precio_str == null) return null;
            double ida_precio = Double.parseDouble(ida_precio_str);
            
            String ida_turistaPaquete = JOptionPane.showInputDialog("Ingrese el paquete turístico para el viaje de ida:");
            if (ida_turistaPaquete == null) ida_turistaPaquete = "";
            
            String ida_opcionesExtra = JOptionPane.showInputDialog("Ingrese las opciones extra para el viaje de ida:");
            if (ida_opcionesExtra == null) ida_opcionesExtra = "";
            
            // === DATOS DE VUELO DE VUELTA ===
            String vuelta_origen = JOptionPane.showInputDialog("=== VUELO DE VUELTA ===\n\nIngrese el origen del viaje de vuelta:");
            if (vuelta_origen == null) return null;
            
            String vuelta_destino = JOptionPane.showInputDialog(
                "Seleccione el destino del viaje de vuelta (Escriba el número):\n\n" +
                "1. Madrid\n" +
                "2. Barcelona\n" +
                "3. Moscú\n" +
                "4. Osaka\n" +
                "5. Bogotá\n" +
                "6. Mismo destino que ida (" + ida_destino + ")");
            if (vuelta_destino == null) return null;
            
            if (vuelta_destino.equals("6")) {
                vuelta_destino = ida_destino;
            } else {
                vuelta_destino = convertirDestino(vuelta_destino);
                if (vuelta_destino == null) return null;
            }
            
            String vuelta_fecha = JOptionPane.showInputDialog("Ingrese la fecha del viaje de vuelta (DD/MM/AAAA):");
            if (vuelta_fecha == null) return null;
            
            String vuelta_pasajeros_str = JOptionPane.showInputDialog("Ingrese el número de pasajeros para el viaje de vuelta:");
            if (vuelta_pasajeros_str == null) return null;
            int vuelta_pasajeros = Integer.parseInt(vuelta_pasajeros_str);
            
            String vuelta_precio_str = JOptionPane.showInputDialog("Ingrese el precio del viaje de vuelta:");
            if (vuelta_precio_str == null) return null;
            double vuelta_precio = Double.parseDouble(vuelta_precio_str);
            
            String vuelta_turistaPaquete = JOptionPane.showInputDialog("Ingrese el paquete turístico para el viaje de vuelta:");
            if (vuelta_turistaPaquete == null) vuelta_turistaPaquete = "";
            
            String vuelta_opcionesExtra = JOptionPane.showInputDialog("Ingrese las opciones extra para el viaje de vuelta:");
            if (vuelta_opcionesExtra == null) vuelta_opcionesExtra = "";
            
            // === DATOS PERSONALES ===
            String datos = JOptionPane.showInputDialog("=== DATOS PERSONALES ===\n\nIngrese información adicional (opcional):");
            if (datos == null) datos = "";
            
            String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
            if (nombre == null) return null;
            
            String apellido = JOptionPane.showInputDialog("Ingrese su apellido:");
            if (apellido == null) return null;
            
            String nacionalidad = JOptionPane.showInputDialog("Ingrese su nacionalidad:");
            if (nacionalidad == null) return null;
            
            String pais_de_residencia = JOptionPane.showInputDialog("Ingrese su país de residencia:");
            if (pais_de_residencia == null) return null;
            
            String fecha_de_nacimiento = JOptionPane.showInputDialog("Ingrese su fecha de nacimiento (DD/MM/AAAA):");
            if (fecha_de_nacimiento == null) return null;
            
            // === DATOS DE PASAPORTE ===
            String numero_de_pasaporte_str = JOptionPane.showInputDialog("=== DATOS DE PASAPORTE ===\n\nIngrese su número de pasaporte:");
            if (numero_de_pasaporte_str == null) return null;
            int numero_de_pasaporte = Integer.parseInt(numero_de_pasaporte_str);
            
            String pais_emisor_pasaporte = JOptionPane.showInputDialog("Ingrese el país emisor de su pasaporte:");
            if (pais_emisor_pasaporte == null) return null;
            
            String fecha_expiracion_pasaporte = JOptionPane.showInputDialog("Ingrese la fecha de expiración de su pasaporte (DD/MM/AAAA):");
            if (fecha_expiracion_pasaporte == null) return null;
            
            // === DATOS DE CONTACTO ===
            String telefono_str = JOptionPane.showInputDialog("=== DATOS DE CONTACTO ===\n\nIngrese su número de teléfono:");
            if (telefono_str == null) return null;
            int telefono = Integer.parseInt(telefono_str);
            
            String email = JOptionPane.showInputDialog("Ingrese su correo electrónico:");
            if (email == null) return null;
            
            String direccion = JOptionPane.showInputDialog("Ingrese su dirección:");
            if (direccion == null) return null;
            
            String codigoPostal = JOptionPane.showInputDialog("Ingrese su código postal:");
            if (codigoPostal == null) return null;
            
            String provincia = JOptionPane.showInputDialog("Ingrese su provincia:");
            if (provincia == null) return null;
            
            String poblacion = JOptionPane.showInputDialog("Ingrese su población:");
            if (poblacion == null) return null;
            
            // === DATOS DE PAGO ===
            String numero_tarjeta = JOptionPane.showInputDialog("=== DATOS DE PAGO ===\n\nIngrese el número de su tarjeta:");
            if (numero_tarjeta == null) return null;
            
            String nombre_titular_tarjeta = JOptionPane.showInputDialog("Ingrese el nombre del titular de la tarjeta:");
            if (nombre_titular_tarjeta == null) return null;
            
            String fecha_vencimiento_tarjeta = JOptionPane.showInputDialog("Ingrese la fecha de vencimiento de su tarjeta (MM/AA):");
            if (fecha_vencimiento_tarjeta == null) return null;
            
            String codigo_seguridad_tarjeta = JOptionPane.showInputDialog("Ingrese el código de seguridad de su tarjeta (CVV):");
            if (codigo_seguridad_tarjeta == null) return null;
            
            // === TÉRMINOS Y CONDICIONES ===
            boolean aceptar_terminos = JOptionPane.showConfirmDialog(
                null,
                "=== TÉRMINOS Y CONDICIONES ===\n\n" +
                "Al aceptar, usted confirma que:\n" +
                "• Ha leído y acepta los términos de servicio\n" +
                "• La información proporcionada es correcta\n" +
                "• Autoriza el procesamiento de sus datos\n" +
                "• Acepta las políticas de cancelación\n\n" +
                "¿Acepta los términos y condiciones?",
                "Términos y Condiciones",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            ) == JOptionPane.YES_OPTION;
            
            if (!aceptar_terminos) {
                JOptionPane.showMessageDialog(null, "Debe aceptar los términos y condiciones para continuar.");
                return null;
            }
            
            // === CREAR OBJETO PASAJES CON TODOS LOS DATOS ===
            return new pasajes(
                ida_origen, ida_destino, ida_fecha, ida_pasajeros, ida_precio, ida_turistaPaquete, ida_opcionesExtra,
                vuelta_origen, vuelta_destino, vuelta_fecha, vuelta_pasajeros, vuelta_precio, vuelta_turistaPaquete, vuelta_opcionesExtra,
                datos, nombre, apellido, nacionalidad, pais_de_residencia, fecha_de_nacimiento, numero_de_pasaporte, pais_emisor_pasaporte, fecha_expiracion_pasaporte,
                telefono, email, direccion, codigoPostal, provincia, poblacion,
                numero_tarjeta, nombre_titular_tarjeta, fecha_vencimiento_tarjeta, codigo_seguridad_tarjeta, aceptar_terminos
            );
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar números válidos en los campos numéricos.");
            return null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
            return null;
        }
    }
    
    private String convertirDestino(String opcion) {
        switch (opcion) {
            case "1": return "Madrid";
            case "2": return "Barcelona";
            case "3": return "Moscú";
            case "4": return "Osaka";
            case "5": return "Bogotá";
            default:
                JOptionPane.showMessageDialog(null, "Destino no válido. Por favor, intente de nuevo.");
                return null;
        }
    }
}