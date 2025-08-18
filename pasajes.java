import javax.swing.JOptionPane;
import java.util.Random;

public class pasajes {
// Tiquete de ida
private String ida_origen = "";
private String ida_destino = "";
private String ida_fecha  = "";
private int ida_pasajeros = 0;
private double ida_precio = 0.0; // Se asignara con random
private String ida_opcionesExtra= "";
// Tiquete de vuelta
private String vuelta_origen = "";
private String vuelta_destino = "";
private String vuelta_fecha  = "";
private int vuelta_pasajeros = 0;
private double vuelta_precio = 0.0; // Se asignara con random
private String vuelta_opcionesExtra = "";

// datos
private String datos = "";
private String nombre = "";
private String apellido = "";
private String nacionalidad = "";
private String pais_de_residencia = "";
private String fecha_de_nacimiento = "";
private String numero_de_pasaporte = ""; // Cambiado a String
private String pais_emisor_pasaporte = "";
private String fecha_expiracion_pasaporte = "";

//Datos personales
private int telefono = 0;
private String email = "";
private String codigoPostal = "";
private String poblacion = "";


// Datos de pago
private String numeero_tarjeta = "";
private String nombre_titular_tarjeta = "";
private String fecha_vencimiento_tarjeta = "";
private String codigo_seguridad_tarjeta = "";

// Terminos y condiciones
private boolean aceptar_terminos = false;

// Constructor principal
public pasajes(String ida_origen, String ida_destino, String ida_fecha, int ida_pasajeros, double ida_precio, String ida_opcionesExtra, String vuelta_origen, String vuelta_destino, String vuelta_fecha, int vuelta_pasajeros, double vuelta_precio, String vuelta_opcionesExtra, String datos, String nombre, String apellido, String nacionalidad, String pais_de_residencia, String fecha_de_nacimiento, String numero_de_pasaporte, String pais_emisor_pasaporte, String fecha_expiracion_pasaporte, int telefono, String email, String codigoPostal, String poblacion, String numeero_tarjeta, String nombre_titular_tarjeta, String fecha_vencimiento_tarjeta, String codigo_seguridad_tarjeta, boolean aceptar_terminos) {
    this.ida_origen = ida_origen;
    this.ida_destino = ida_destino;
    this.ida_fecha = ida_fecha;
    this.ida_pasajeros = ida_pasajeros;
    this.ida_precio = ida_precio;
    this.ida_opcionesExtra = ida_opcionesExtra;
    this.vuelta_origen = vuelta_origen;
    this.vuelta_destino = vuelta_destino;
    this.vuelta_fecha = vuelta_fecha;
    this.vuelta_pasajeros = vuelta_pasajeros;
    this.vuelta_precio = vuelta_precio;
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
    this.codigoPostal = codigoPostal;
    this.poblacion = poblacion;
    this.numeero_tarjeta = numeero_tarjeta;
    this.nombre_titular_tarjeta = nombre_titular_tarjeta;
    this.fecha_vencimiento_tarjeta = fecha_vencimiento_tarjeta;
    this.codigo_seguridad_tarjeta = codigo_seguridad_tarjeta;
    this.aceptar_terminos = aceptar_terminos;
}

// Constructor alternativo (se ha corregido el tipo de numero_de_pasaporte2 a String)
// Este constructor no inicializa las variables de instancia, lo que podria ser un problema.
// Se recomienda usar solo el constructor principal o inicializar todas las variables aqui.
public pasajes(String ida_origen2, String ida_destino2, String ida_fecha2, int ida_pasajeros2, double ida_precio2,
            String ida_opcionesExtra2, String vuelta_origen2, String vuelta_destino2,
            String vuelta_fecha2, int vuelta_pasajeros2, double vuelta_precio2,
            String vuelta_opcionesExtra2, String datos2, String nombre2, String apellido2, String nacionalidad2,
            String pais_de_residencia2, String fecha_de_nacimiento2, String numero_de_pasaporte2, // CORREGIDO: Ahora es String
            String pais_emisor_pasaporte2, String fecha_expiracion_pasaporte2, int telefono2, String email2,
            String direccion, String codigoPostal2, String provincia, String poblacion2, String numero_tarjeta,
            String nombre_titular_tarjeta2, String fecha_vencimiento_tarjeta2, String codigo_seguridad_tarjeta2,
            boolean aceptar_terminos2) {
        // Este constructor esta vacio, lo que significa que las variables de instancia
        // de la clase 'pasajes' no seran inicializadas si se usa este constructor.
        // Se recomienda inicializar todas las variables aqui o eliminar este constructor
        // si no se utiliza.
    }

// Métodos get/set
public String getIda_origen() { return this.ida_origen; }
public void setIda_origen(String ida_origen) { this.ida_origen = ida_origen; }
public String getIda_destino() { return this.ida_destino; }
public void setIda_destino(String ida_destino) { this.ida_destino = ida_destino; }
public String getIda_fecha() { return this.ida_fecha; }
public void setIda_fecha(String ida_fecha) { this.ida_fecha = ida_fecha; }
public int getIda_pasajeros() { return this.ida_pasajeros; }
public void setIda_pasajeros(int ida_pasajeros) { this.ida_pasajeros = ida_pasajeros; }
public double getIda_precio() { return this.ida_precio; }
public void setIda_precio(double ida_precio) { this.ida_precio = ida_precio; }
public String getIda_opcionesExtra() { return this.ida_opcionesExtra; }
public void setIda_opcionesExtra(String ida_opcionesExtra) { this.ida_opcionesExtra = ida_opcionesExtra; }
public String getVuelta_origen() { return this.vuelta_origen; }
public void setVuelta_origen(String vuelta_origen) { this.vuelta_origen = vuelta_origen; }
public String getVuelta_destino() { return this.vuelta_destino; }
public void setVuelta_destino(String vuelta_destino) { this.vuelta_destino = vuelta_destino; }
public String getVuelta_fecha() { return this.vuelta_fecha; }
public void setVuelta_fecha(String vuelta_fecha) { this.vuelta_fecha = vuelta_fecha; }
public int getVuelta_pasajeros() { return this.vuelta_pasajeros; }
public void setVuelta_pasajeros(int vuelta_pasajeros) { this.vuelta_pasajeros = vuelta_pasajeros; }
public double getVuelta_precio() { return this.vuelta_precio; }
public void setVuelta_precio(double vuelta_precio) { this.vuelta_precio = vuelta_precio; }
public String getVuelta_opcionesExtra() { return this.vuelta_opcionesExtra; }
public void setVuelta_opcionesExtra(String vuelta_opcionesExtra) { this.vuelta_opcionesExtra = vuelta_opcionesExtra; }
public String getDatos() { return this.datos; }
public void setDatos(String datos) { this.datos = datos; }
public String getNombre() { return this.nombre; }
public void setNombre(String nombre) { this.nombre = nombre; }
public String getApellido() { return this.apellido; }
public void setApellido(String apellido) { this.apellido = apellido; }
public String getNacionalidad() { return this.nacionalidad; }
public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
public String getPais_de_residencia() { return this.pais_de_residencia; }
public void setPais_de_residencia(String pais_de_residencia) { this.pais_de_residencia = pais_de_residencia; }
public String getFecha_de_nacimiento() { return this.fecha_de_nacimiento; }
public void setFecha_de_nacimiento(String fecha_de_nacimiento) { this.fecha_de_nacimiento = fecha_de_nacimiento; }
public String getNumero_de_pasaporte() { return this.numero_de_pasaporte; }
public void setNumero_de_pasaporte(String numero_de_pasaporte) { this.numero_de_pasaporte = numero_de_pasaporte; }
public String getPais_emisor_pasaporte() { return this.pais_emisor_pasaporte; }
public void setPais_emisor_pasaporte(String pais_emisor_pasaporte) { this.pais_emisor_pasaporte = pais_emisor_pasaporte; }
public String getFecha_expiracion_pasaporte() { return this.fecha_expiracion_pasaporte; }
public void setFecha_expiracion_pasaporte(String fecha_expiracion_pasaporte) { this.fecha_expiracion_pasaporte = fecha_expiracion_pasaporte; }
public int getTelefono() { return this.telefono; }
public void setTelefono(int telefono) { this.telefono = telefono; }
public String getEmail() { return this.email; }
public void setEmail(String email) { this.email = email; }
public String getCodigoPostal() { return this.codigoPostal; }
public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; }
public String getPoblacion() { return this.poblacion; }
public void setPoblacion(String poblacion) { this.poblacion = poblacion; }
public String getNumeero_tarjeta() { return this.numeero_tarjeta; }
public void setNumeero_tarjeta(String numeero_tarjeta) { this.numeero_tarjeta = numeero_tarjeta; }
public String getNombre_titular_tarjeta() { return this.nombre_titular_tarjeta; }
public void setNombre_titular_tarjeta(String nombre_titular_tarjeta) { this.nombre_titular_tarjeta = nombre_titular_tarjeta; }
public String getFecha_vencimiento_tarjeta() { return this.fecha_vencimiento_tarjeta; }
public void setFecha_vencimiento_tarjeta(String fecha_vencimiento_tarjeta) { this.fecha_vencimiento_tarjeta = fecha_vencimiento_tarjeta; }
public String getCodigo_seguridad_tarjeta() { return this.codigo_seguridad_tarjeta; }
public void setCodigo_seguridad_tarjeta(String codigo_seguridad_tarjeta) { this.codigo_seguridad_tarjeta = codigo_seguridad_tarjeta; }
public boolean isAceptar_terminos() { return this.aceptar_terminos; }
public boolean getAceptar_terminos() { return this.aceptar_terminos; }
public void setAceptar_terminos(boolean aceptar_terminos) { this.aceptar_terminos = aceptar_terminos; }

}

// Clase PedirDatosUsuario original (ahora renombrada a PedirDatosUsuarioOriginal para evitar conflictos)
// Se mantiene para la opcion de "Recoleccion Simplificada"
class PedirDatosUsuarioOriginal {

    public pasajes solicitarDatos() {
        Random rand = new Random();
        double[] precios = {560.0, 650.0, 490.0, 340.0};

        //ida
        String ida_origen = JOptionPane.showInputDialog("Ingrese el origen del viaje de ida:");
        if (ida_origen == null) return null;

        String ida_destino = JOptionPane.showInputDialog("Ingrese el destino del viaje de ida (Escriba el numero):" +
            "\nOpciones:" + "1.Madrid" + " 2.Barcelona" + " 3.Moscu" + " 4.Osaka" + " 5.Bogota" );
        if (ida_destino == null) return null;

        switch (ida_destino) {
            case "1": ida_destino = "Madrid"; break;
            case "2": ida_destino = "Barcelona"; break;
            case "3": ida_destino = "Moscu"; break;
            case "4": ida_destino = "Osaka"; break;
            case "5": ida_destino = "Bogota"; break;
            default:
                JOptionPane.showMessageDialog(null, "Destino no valido. Por favor, intente de nuevo.");
                return null;
        }

        String ida_fecha = JOptionPane.showInputDialog("Ingrese la fecha del viaje de ida:");
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

        double ida_precio = precios[rand.nextInt(precios.length)];
        JOptionPane.showMessageDialog(null, "El precio del viaje de ida es: $" + ida_precio);
        String ida_opcionesExtra = JOptionPane.showInputDialog("Ingrese las opciones extra para el viaje de ida:");
        if (ida_opcionesExtra == null) ida_opcionesExtra = "";

        String vuelta_origen = JOptionPane.showInputDialog("Ingrese el origen del viaje de vuelta:");
        if (vuelta_origen == null) return null;

        //vuelta
        String vuelta_destino = JOptionPane.showInputDialog("Ingrese el destino del viaje de vuelta:" +
            "\nOpciones:" + "1.Madrid" + " 2.Barcelona" + " 3.Moscu" + " 4.Osaka" + " 5.Bogota" + " 6.Igual que ida");
        if (vuelta_destino == null) return null;

        switch (vuelta_destino) {
            case "1": vuelta_destino = "Madrid"; break;
            case "2": vuelta_destino = "Barcelona"; break;
            case "3": vuelta_destino = "Moscu"; break;
            case "4": vuelta_destino = "Osaka"; break;
            case "5": vuelta_destino = "Bogota"; break;
            case "6": vuelta_destino = ida_origen; break;
            default:
                JOptionPane.showMessageDialog(null, "Destino no valido. Por favor, intente de nuevo.");
                return null;
        }
        String vuelta_fecha = JOptionPane.showInputDialog("Ingrese la fecha del viaje de vuelta:");
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

        double vuelta_precio = precios[rand.nextInt(precios.length)];
        JOptionPane.showMessageDialog(null, "El precio del viaje de vuelta es: $" + vuelta_precio);
        String vuelta_opcionesExtra = JOptionPane.showInputDialog("Ingrese las opciones extra para el viaje de vuelta:");
        if (vuelta_opcionesExtra == null) vuelta_opcionesExtra = "";

        // Datos
        String datos = JOptionPane.showInputDialog("Ingrese sus datos personales:");
        if (datos == null) datos = "";

        String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
        if (nombre == null) return null;

        String apellido = JOptionPane.showInputDialog("Ingrese su apellido:");
        if (apellido == null) return null;

        String nacionalidad = JOptionPane.showInputDialog("Ingrese su nacionalidad:");
        if (nacionalidad == null) return null;

        String pais_de_residencia = JOptionPane.showInputDialog("Ingrese su pais de residencia:");
        if (pais_de_residencia == null) return null;

        String fecha_de_nacimiento = JOptionPane.showInputDialog("Ingrese su fecha de nacimiento:");
        if (fecha_de_nacimiento == null) return null;

        String numero_de_pasaporte = JOptionPane.showInputDialog("Ingrese su numero de pasaporte:");
        if (numero_de_pasaporte == null) return null;

        String pais_emisor_pasaporte = JOptionPane.showInputDialog("Ingrese el pais emisor de su pasaporte:");
        if (pais_emisor_pasaporte == null) return null;

        String fecha_expiracion_pasaporte = JOptionPane.showInputDialog("Ingrese la fecha de expiracion de su pasaporte:");
        if (fecha_expiracion_pasaporte == null) return null;

        // Datos personales
        int telefono = 0;
        String telefono_str = JOptionPane.showInputDialog("Ingrese su numero de telefono:");
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

        // Datos de pago
        String numeero_tarjeta = JOptionPane.showInputDialog("Ingrese el numero de su tarjeta:");
        if (numeero_tarjeta == null) return null;

        String nombre_titular_tarjeta = JOptionPane.showInputDialog("Ingrese el nombre del titular de la tarjeta:");
        if (nombre_titular_tarjeta == null) return null;

        String fecha_vencimiento_tarjeta = JOptionPane.showInputDialog("Ingrese la fecha de vencimiento de su tarjeta (MM/AA):");
        if (fecha_vencimiento_tarjeta == null) return null;

        String codigo_seguridad_tarjeta = JOptionPane.showInputDialog("Ingrese el codigo de seguridad de su tarjeta (CVV):");
        if (codigo_seguridad_tarjeta == null) return null;

        boolean aceptar_terminos = JOptionPane.showConfirmDialog(null, "Acepta los terminos y condiciones?") == JOptionPane.YES_OPTION;

        if (!aceptar_terminos) {
            JOptionPane.showMessageDialog(null, "Debe aceptar los terminos y condiciones para continuar.");
            return null;
        }

        // Crear objeto pasajes usando el constructor actualizado
        return new pasajes(ida_origen, ida_destino, ida_fecha, ida_pasajeros, ida_precio, ida_opcionesExtra,
                         vuelta_origen, vuelta_destino, vuelta_fecha, vuelta_pasajeros, vuelta_precio, vuelta_opcionesExtra,
                         datos, nombre, apellido, nacionalidad, pais_de_residencia, fecha_de_nacimiento,
                         numero_de_pasaporte, pais_emisor_pasaporte, fecha_expiracion_pasaporte,
                         telefono, email, codigoPostal, poblacion,
                         numeero_tarjeta, nombre_titular_tarjeta, fecha_vencimiento_tarjeta, codigo_seguridad_tarjeta, aceptar_terminos);
    }
}
