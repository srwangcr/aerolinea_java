import javax.swing.JOptionPane;

public class pasajes {
// Tiquete de ida
private String ida_origen = ""; 
private String ida_destino = "";
private String ida_fecha  = ""; 
private int ida_pasajeros = 0;
private double ida_precio = 0.0;
private String ida_turistaPaquete = "";
private String ida_opcionesExtra= ""; // podrian quitarse hay que investigar un poco más
// Tiquete de vuelta
private String vuelta_origen = "";
private String vuelta_destino = "";
private String vuelta_fecha  = ""; 
private int vuelta_pasajeros = 0;
private double vuelta_precio = 0.0;
private String vuelta_turistaPaquete = "";
private String vuelta_opcionesExtra = ""; // podrian quitarse hay que investigar un poco más

// datos
private String datos = "";
private String nombre = "";
private String apellido = "";
private String nacionalidad = "";
private String pais_de_residencia = "";
private String fecha_de_nacimiento = "";
private int numero_de_pasaporte = 0;
private String pais_emisor_pasaporte = "";
private String fecha_expiracion_pasaporte = "";


//Datos personales
private int telefono = 0;
private String email = "";
private String direccion = "";
private String codigoPostal = "";
private String provincia = "";
private String poblacion = ""; 


// Datos de pago
private String numeero_tarjeta = "";
private String nombre_titular_tarjeta = "";
private String fecha_vencimiento_tarjeta = "";
private String codigo_seguridad_tarjeta = "";

// Terminos y condiciones
private boolean aceptar_terminos = false;

// Constructor


    public pasajes(String ida_origen, String ida_destino, String ida_fecha, int ida_pasajeros, double ida_precio, String ida_turistaPaquete, String ida_opcionesExtra, String vuelta_origen, String vuelta_destino, String vuelta_fecha, int vuelta_pasajeros, double vuelta_precio, String vuelta_turistaPaquete, String vuelta_opcionesExtra, String datos, String nombre, String apellido, String nacionalidad, String pais_de_residencia, String fecha_de_nacimiento, int numero_de_pasaporte, String pais_emisor_pasaporte, String fecha_expiracion_pasaporte, int telefono, String email, String direccion, String codigoPostal, String provincia, String poblacion, String numeero_tarjeta, String nombre_titular_tarjeta, String fecha_vencimiento_tarjeta, String codigo_seguridad_tarjeta, boolean aceptar_terminos) {
        this.ida_origen = ida_origen; 
        this.ida_destino = ida_destino;
        this.ida_fecha = ida_fecha;
        this.ida_pasajeros = ida_pasajeros;
        this.ida_precio = ida_precio;
        this.ida_turistaPaquete = ida_turistaPaquete;
        this.ida_opcionesExtra = ida_opcionesExtra;
        this.vuelta_origen = vuelta_origen;
        this.vuelta_destino = vuelta_destino;
        this.vuelta_fecha = vuelta_fecha;
        this.vuelta_pasajeros = vuelta_pasajeros;
        this.vuelta_precio = vuelta_precio;
        this.vuelta_turistaPaquete = vuelta_turistaPaquete;
        this.vuelta_opcionesExtra = vuelta_opcionesExtra;
        this.datos = datos;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
        this.pais_de_residencia = pais_de_residencia;
        this.fecha_de_nacimiento = fecha_de_nacimiento;
        this.numero_de_pasaporte = numero_de_pasaporte;
        this.pais_emisor_pasaporte = pais_emisor_pasaporte;
        this.fecha_expiracion_pasaporte = fecha_expiracion_pasaporte;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.provincia = provincia;
        this.poblacion = poblacion; 
        this.numeero_tarjeta = numeero_tarjeta;
        this.nombre_titular_tarjeta = nombre_titular_tarjeta;
        this.fecha_vencimiento_tarjeta = fecha_vencimiento_tarjeta;
        this.codigo_seguridad_tarjeta = codigo_seguridad_tarjeta;
        this.aceptar_terminos = aceptar_terminos;
    }



public void solicitar_datos_usuario() {
    this.nombre = JOptionPane.showInputDialog("Favor Ingrese su nombre");
}

    public String getIda_origen() { 
        return this.ida_origen;
    }

    public void setIda_origen(String ida_origen) {
        this.ida_origen = ida_origen;
    }

    public String getIda_destino() {
        return this.ida_destino;
    }

    public void setIda_destino(String ida_destino) {
        this.ida_destino = ida_destino;
    }

    public String getIda_fecha() {
        return this.ida_fecha;
    }

    public void setIda_fecha(String ida_fecha) {
        this.ida_fecha = ida_fecha;
    }

    public int getIda_pasajeros() {
        return this.ida_pasajeros;
    }

    public void setIda_pasajeros(int ida_pasajeros) {
        this.ida_pasajeros = ida_pasajeros;
    }

    public double getIda_precio() {
        return this.ida_precio;
    }

    public void setIda_precio(double ida_precio) {
        this.ida_precio = ida_precio;
    }

    public String getIda_turistaPaquete() {
        return this.ida_turistaPaquete;
    }

    public void setIda_turistaPaquete(String ida_turistaPaquete) {
        this.ida_turistaPaquete = ida_turistaPaquete;
    }

    public String getIda_opcionesExtra() {
        return this.ida_opcionesExtra;
    }

    public void setIda_opcionesExtra(String ida_opcionesExtra) {
        this.ida_opcionesExtra = ida_opcionesExtra;
    }

    public String getVuelta_origen() {
        return this.vuelta_origen;
    }

    public void setVuelta_origen(String vuelta_origen) {
        this.vuelta_origen = vuelta_origen;
    }

    public String getVuelta_destino() {
        return this.vuelta_destino;
    }

    public void setVuelta_destino(String vuelta_destino) {
        this.vuelta_destino = vuelta_destino;
    }

    public String getVuelta_fecha() {
        return this.vuelta_fecha;
    }

    public void setVuelta_fecha(String vuelta_fecha) {
        this.vuelta_fecha = vuelta_fecha;
    }

    public int getVuelta_pasajeros() {
        return this.vuelta_pasajeros;
    }

    public void setVuelta_pasajeros(int vuelta_pasajeros) {
        this.vuelta_pasajeros = vuelta_pasajeros;
    }

    public double getVuelta_precio() {
        return this.vuelta_precio;
    }

    public void setVuelta_precio(double vuelta_precio) {
        this.vuelta_precio = vuelta_precio;
    }

    public String getVuelta_turistaPaquete() {
        return this.vuelta_turistaPaquete;
    }

    public void setVuelta_turistaPaquete(String vuelta_turistaPaquete) {
        this.vuelta_turistaPaquete = vuelta_turistaPaquete;
    }

    public String getVuelta_opcionesExtra() {
        return this.vuelta_opcionesExtra;
    }

    public void setVuelta_opcionesExtra(String vuelta_opcionesExtra) {
        this.vuelta_opcionesExtra = vuelta_opcionesExtra;
    }

    public String getDatos() {
        return this.datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNacionalidad() {
        return this.nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getPais_de_residencia() {
        return this.pais_de_residencia;
    }

    public void setPais_de_residencia(String pais_de_residencia) {
        this.pais_de_residencia = pais_de_residencia;
    }

    public String getFecha_de_nacimiento() {
        return this.fecha_de_nacimiento;
    }

    public void setFecha_de_nacimiento(String fecha_de_nacimiento) {
        this.fecha_de_nacimiento = fecha_de_nacimiento;
    }

    public int getNumero_de_pasaporte() {
        return this.numero_de_pasaporte;
    }

    public void setNumero_de_pasaporte(int numero_de_pasaporte) {
        this.numero_de_pasaporte = numero_de_pasaporte;
    }

    public String getPais_emisor_pasaporte() {
        return this.pais_emisor_pasaporte;
    }

    public void setPais_emisor_pasaporte(String pais_emisor_pasaporte) {
        this.pais_emisor_pasaporte = pais_emisor_pasaporte;
    }

    public String getFecha_expiracion_pasaporte() {
        return this.fecha_expiracion_pasaporte;
    }

    public void setFecha_expiracion_pasaporte(String fecha_expiracion_pasaporte) {
        this.fecha_expiracion_pasaporte = fecha_expiracion_pasaporte;
    }

    public int getTelefono() {
        return this.telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return this.codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getProvincia() {
        return this.provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPoblacion() { 
        return this.poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getNumeero_tarjeta() {
        return this.numeero_tarjeta;
    }

    public void setNumeero_tarjeta(String numeero_tarjeta) {
        this.numeero_tarjeta = numeero_tarjeta;
    }

    public String getNombre_titular_tarjeta() {
        return this.nombre_titular_tarjeta;
    }

    public void setNombre_titular_tarjeta(String nombre_titular_tarjeta) {
        this.nombre_titular_tarjeta = nombre_titular_tarjeta;
    }

    public String getFecha_vencimiento_tarjeta() {
        return this.fecha_vencimiento_tarjeta;
    }

    public void setFecha_vencimiento_tarjeta(String fecha_vencimiento_tarjeta) {
        this.fecha_vencimiento_tarjeta = fecha_vencimiento_tarjeta;
    }

    public String getCodigo_seguridad_tarjeta() {
        return this.codigo_seguridad_tarjeta;
    }

    public void setCodigo_seguridad_tarjeta(String codigo_seguridad_tarjeta) {
        this.codigo_seguridad_tarjeta = codigo_seguridad_tarjeta;
    }

    public boolean isAceptar_terminos() {
        return this.aceptar_terminos;
    }

    public boolean getAceptar_terminos() {
        return this.aceptar_terminos;
    }

    public void setAceptar_terminos(boolean aceptar_terminos) {
        this.aceptar_terminos = aceptar_terminos;
    }

}

// oscar aqui


class PedirDatosUsuario {

    public void solicitarDatos() {
        //oscar aca
        //ida
    String ida_origen = JOptionPane.showInputDialog("Ingrese el origen del viaje de ida:"); 
    String ida_destino = JOptionPane.showInputDialog("Ingrese el destino del viaje de ida:");
    String ida_fecha = JOptionPane.showInputDialog("Ingrese la fecha del viaje de ida:");
    int ida_pasajeros = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de pasajeros para el viaje de ida:"));
    double ida_precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del viaje de ida:"));
    String ida_turistaPaquete = JOptionPane.showInputDialog("Ingrese el paquete turístico para el viaje de ida:");
    String ida_opcionesExtra = JOptionPane.showInputDialog("Ingrese las opciones extra para el viaje de ida:");
    String vuelta_origen = JOptionPane.showInputDialog("Ingrese el origen del viaje de vuelta:");

    //vuelta
    String vuelta_destino = JOptionPane.showInputDialog("Ingrese el destino del viaje de vuelta:");
    String vuelta_fecha = JOptionPane.showInputDialog("Ingrese la fecha del viaje de vuelta:");
    int vuelta_pasajeros = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de pasajeros para el viaje de vuelta:"));
    double vuelta_precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del viaje de vuelta:"));
    String vuelta_turistaPaquete = JOptionPane.showInputDialog("Ingrese el paquete turístico para el viaje de vuelta:");
    String vuelta_opcionesExtra = JOptionPane.showInputDialog("Ingrese las opciones extra para el viaje de vuelta:");

    // Datos 
    String datos = JOptionPane.showInputDialog("Ingrese sus datos personales:");
    String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
    String apellido = JOptionPane.showInputDialog("Ingrese su apellido:");  
    String nacionalidad = JOptionPane.showInputDialog("Ingrese su nacionalidad:");
    String pais_de_residencia = JOptionPane.showInputDialog("Ingrese su país de residencia:");
    String fecha_de_nacimiento = JOptionPane.showInputDialog("Ingrese su fecha de nacimiento:");
    int numero_de_pasaporte = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su número de pasaporte:"));
    String pais_emisor_pasaporte = JOptionPane.showInputDialog("Ingrese el país emisor de su pasaporte:");
    String fecha_expiracion_pasaporte = JOptionPane.showInputDialog("Ingrese la fecha de expiración de su pasaporte:");

    // Datos personales
    int telefono = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su número de teléfono:"));
    String email = JOptionPane.showInputDialog("Ingrese su correo electrónico:");
    String direccion = JOptionPane.showInputDialog("Ingrese su dirección:");
    String codigoPostal = JOptionPane.showInputDialog("Ingrese su código postal:");
    String provincia = JOptionPane.showInputDialog("Ingrese su provincia:");
    String poblacion = JOptionPane.showInputDialog("Ingrese su población:"); 

    // Datos de pago
    String numeero_tarjeta = JOptionPane.showInputDialog("Ingrese el número de su tarjeta:");
    String nombre_titular_tarjeta = JOptionPane.showInputDialog("Ingrese el nombre del titular de la tarjeta:");
    String fecha_vencimiento_tarjeta = JOptionPane.showInputDialog("Ingrese la fecha de vencimiento de su tarjeta:");
    String codigo_seguridad_tarjeta = JOptionPane.showInputDialog("Ingrese el código de seguridad de su tarjeta:");
    boolean aceptar_terminos = JOptionPane.showConfirmDialog(null, "¿Acepta los términos y condiciones?") == JOptionPane.YES_OPTION;

}





}

