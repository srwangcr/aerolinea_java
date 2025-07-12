import javax.swing.JOptionPane;

public class Login {
    private String usuario = "";  
    private String contraseña = "";
    private boolean registrado = false; // usuario no registrado inicialmente
    private boolean sesionActiva = false; // nueva variable para controlar sesión activa

    public void Register() {
        if (!registrado) { // verificar si el usuario ya está registrado
            this.usuario = JOptionPane.showInputDialog("Ingrese su usuario:");
            this.contraseña = JOptionPane.showInputDialog("Ingrese su contraseña:");
            this.registrado = true; // Marcar como registrado
            JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente: " + this.usuario);
        } else {
            JOptionPane.showMessageDialog(null, "El usuario ya está registrado.");
        }
    }

    public boolean iniciarSesion() {
        if (!registrado) { // Verificar si el usuario está registrado
            JOptionPane.showMessageDialog(null, "Debe registrarse primero.");
            return false;
        }
        
        if (sesionActiva) { // Verificar si ya hay una sesión activa
            JOptionPane.showMessageDialog(null, "Ya hay una sesión activa. Cierre sesión primero.");
            return false;
        }
        
        String inputUsuario = JOptionPane.showInputDialog("Ingrese su usuario:"); 
        String inputContraseña = JOptionPane.showInputDialog("Ingrese su contraseña:");
        
        if (this.usuario.equals(inputUsuario) && this.contraseña.equals(inputContraseña)) {
            JOptionPane.showMessageDialog(null, "¡Inicio de sesión exitoso!");
            this.sesionActiva = true; // Activar sesión
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas.");
            return false;
        }
    }

    public void cerrarSesion() {
        if (sesionActiva) {
            this.sesionActiva = false;
            JOptionPane.showMessageDialog(null, "Sesión cerrada exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No hay ninguna sesión activa.");
        }
    }

    public void cambiarUsuario() {
        if (sesionActiva) {
            cerrarSesion(); // Cerrar sesión actual
        }
        
        // Resetear datos para permitir nuevo registro
        this.usuario = "";
        this.contraseña = "";
        this.registrado = false;
        this.sesionActiva = false;
        
        JOptionPane.showMessageDialog(null, "Ahora puede registrar un nuevo usuario.");
    }

    public String getUsuario() {
        return usuario;
    }
    
    public String getContraseña() {
        return contraseña;
    }
    
    public boolean isRegistrado() {
        return registrado;
    }
    
    public boolean isSesionActiva() {
        return sesionActiva;
    }
}