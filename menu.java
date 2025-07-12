import javax.swing.JOptionPane;

public class menu {
    private Login login;
    
    public menu() {
        this.login = new Login();
    }
    
    public void mostrarMenu() {
        while (true) {
            String[] opciones = {
                "Registrar Usuario", 
                "Iniciar Sesión", 
                "Cerrar Sesión", 
                "Cambiar Usuario", 
                "Ver Estado", 
                "Gestión de Pasajes",
                "Gestión de Maletas",
                "Proceso de Pago",
                "Salir"
            };
            
            int opcion = JOptionPane.showOptionDialog(
                null,
                "=== SISTEMA AEROPUERTO ===\nSeleccione una opción:",
                "Menú Principal",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );
            
            ejecutarOpcion(opcion);
        }
    }
    
    private void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 0:
                registrarUsuario();
                break;
            case 1:
                iniciarSesion();
                break;
            case 2:
                cerrarSesion();
                break;
            case 3:
                cambiarUsuario();
                break;
            case 4:
                verEstado();
                break;
            case 5:
                gestionPasajes();
                break;
            case 6:
                gestionMaletas();
                break;
            case 7:
                procesoPago();
                break;
            case 8:
            case -1:
                salir();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida");
        }
    }
    
    private void registrarUsuario() {
        login.Register();
    }
    
    private void iniciarSesion() {
        boolean loginExitoso = login.iniciarSesion();
        if (loginExitoso) {
            System.out.println("✓ Sesión iniciada para: " + login.getUsuario());
        }
    }
    
    private void cerrarSesion() {
        login.cerrarSesion();
    }
    
    private void cambiarUsuario() {
        login.cambiarUsuario();
    }
    
    private void verEstado() {
        String estado = "=== ESTADO DEL SISTEMA ===\n" +
                       "Usuario: " + (login.getUsuario().isEmpty() ? "Sin registrar" : login.getUsuario()) + "\n" +
                       "Registrado: " + (login.isRegistrado() ? "Sí" : "No") + "\n" +
                       "Sesión Activa: " + (login.isSesionActiva() ? "Sí" : "No");
        
        JOptionPane.showMessageDialog(null, estado, "Estado del Sistema", JOptionPane.INFORMATION_MESSAGE);
        System.out.println(estado);
    }
    
    private void gestionPasajes() {
        if (!login.isSesionActiva()) {
            JOptionPane.showMessageDialog(null, "Debe iniciar sesión para acceder a esta función.");
            return;
        }
        
        JOptionPane.showMessageDialog(null, "Módulo de Pasajes (En desarrollo)");
    }
    
    private void gestionMaletas() {
        if (!login.isSesionActiva()) {
            JOptionPane.showMessageDialog(null, "Debe iniciar sesión para acceder a esta función.");
            return;
        }
        
        JOptionPane.showMessageDialog(null, "Módulo de Maletas (En desarrollo)");
    }
    
    private void procesoPago() {
        if (!login.isSesionActiva()) {
            JOptionPane.showMessageDialog(null, "Debe iniciar sesión para acceder a esta función.");
            return;
        }
        
        JOptionPane.showMessageDialog(null, "Módulo de Proceso de Pago (En desarrollo)");
    }
    
    private void salir() {
        JOptionPane.showMessageDialog(null, "¡Hasta luego!");
        System.exit(0);
    }
}