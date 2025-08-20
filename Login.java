import javax.swing.JOptionPane;

public class Login {
    // Variables simples para un solo usuario
    private String usuario = null;
    private String contraseña = null;
    private boolean sesionActiva = false;
    private boolean usuarioRegistrado = false;

    public void Register() {
        if (usuarioRegistrado) {
            JOptionPane.showMessageDialog(null, "Ya hay un usuario registrado. Eliminelo primero.");
            return;
        }

        String nuevoUsuario = JOptionPane.showInputDialog("Ingrese su usuario:");
        if (nuevoUsuario == null) { // Verificar si se ingresó un usuario
            JOptionPane.showMessageDialog(null, "Debe ingresar un usuario valido.");
            return;
        }

        String nuevaContraseña = JOptionPane.showInputDialog("Ingrese su contraseña:");
        if (nuevaContraseña == null || nuevaContraseña.isEmpty()) { // Verificar si se ingresó una contraseña
            JOptionPane.showMessageDialog(null, "Debe ingresar una contraseña valida.");
            return;
        }

        this.usuario = nuevoUsuario;
        this.contraseña = nuevaContraseña;
        this.usuarioRegistrado = true;

        JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente: " + nuevoUsuario);
    }

    public boolean iniciarSesion() {
        if (!usuarioRegistrado) {
            JOptionPane.showMessageDialog(null, "No hay usuarios registrados. Registre uno primero.");
            return false;
        }

        if (sesionActiva) {
            JOptionPane.showMessageDialog(null, "Ya hay una sesion activa para: " + usuario + ". Cierre sesion primero.");
            return false;
        }

        String inputUsuario = JOptionPane.showInputDialog("Ingrese su usuario:");
        if (inputUsuario == null) {
            return false;
        }

        String inputContraseña = JOptionPane.showInputDialog("Ingrese su contraseña:");
        if (inputContraseña == null) {
            return false;
        }

        if (usuario.equals(inputUsuario) && contraseña.equals(inputContraseña)) {
            JOptionPane.showMessageDialog(null, "Inicio de sesion exitoso para: " + inputUsuario + "!");
            this.sesionActiva = true;
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas.");
            return false;
        }
    }

    public void cerrarSesion() {
        if (sesionActiva) {
            JOptionPane.showMessageDialog(null, "Sesion cerrada exitosamente para: " + usuario);
            this.sesionActiva = false;
        } else {
            JOptionPane.showMessageDialog(null, "No hay ninguna sesion activa.");
        }
    }

    public void cambiarUsuario() {
        if (sesionActiva) {
            cerrarSesion();
        }

        JOptionPane.showMessageDialog(null, "Ahora puede iniciar sesion o registrar un nuevo usuario.");
    }

    public void eliminarUsuario() {
        if (!usuarioRegistrado) {
            JOptionPane.showMessageDialog(null, "No hay usuarios registrados.");
            return;
        }

        if (sesionActiva) {
            cerrarSesion();
        }

        this.usuario = null;
        this.contraseña = null;
        this.usuarioRegistrado = false;

        JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente.");
    }

    public void mostrarUsuarios() {
        if (!usuarioRegistrado) {
            JOptionPane.showMessageDialog(null, "No hay usuarios registrados.");
        } else {
            String mensaje = "Usuario registrado: " + usuario;
            if (sesionActiva) {
                mensaje = mensaje + " (ACTIVO)";
            }
            JOptionPane.showMessageDialog(null, mensaje);
        }
    }

    // Getters simplificados
    public String getUsuarioActivo() {
        if (sesionActiva) {
            return usuario;
        } else {
            return null;
        }
    }

    public boolean isSesionActiva() {
        return sesionActiva;
    }

    public int getTotalUsuarios() {
        if (usuarioRegistrado) {
            return 1;
        } else {
            return 0;
        }
    }

    public boolean usuarioExiste(String usuarioAVerificar) {
        if (usuarioRegistrado && usuario.equals(usuarioAVerificar)) {
            return true;
        } else {
            return false;
        }
    }
}
