import javax.swing.JOptionPane;

public class proceso_pago {

    public boolean validacionFechaMes(int fecha_vencimiento_tarjetaMes) {
        if (fecha_vencimiento_tarjetaMes < 1 || fecha_vencimiento_tarjetaMes > 12) {
            JOptionPane.showMessageDialog(null, "El mes ingresado es incorrecto, ingreselo nuevamente");
            return true;
        } else {
            return false;
        }
    }

    public boolean validacionFechaAnio(int fecha_vencimiento_tarjetaAnio) {
        // Se asume que el año es un valor de dos dígitos (ej. 25 para 2025)
        if (fecha_vencimiento_tarjetaAnio < 25 || fecha_vencimiento_tarjetaAnio > 35) {
            JOptionPane.showMessageDialog(null, "El año ingresado no entra en el rango establecido (25-35), ingreselo nuevamente");
            return true;
        } else {
            return false;
        }
    }

    public boolean validacionCVV(int codigo_seguridad_tarjeta) {
        if (codigo_seguridad_tarjeta < 100 || codigo_seguridad_tarjeta > 999) {
            JOptionPane.showMessageDialog(null, "El codigo es incorrecto, ingreselo nuevamente: ");
            return true;
        } else {
            return false;
        }
    }

    public String mostrarFecha(int fecha_vencimiento_tarjetaMes, int fecha_vencimiento_tarjetaAnio) {
        return "(" + fecha_vencimiento_tarjetaMes + "/" + fecha_vencimiento_tarjetaAnio + ")";
    }

}
