
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

    public boolean validacionNumeroTarjeta(long numeroTarjeta) {
        long temp = numeroTarjeta;
        int contador = 0;

        // Contar la cantidad de dígitos
        while (temp > 0) {
            temp /= 10;
            contador++;
        }

        // Validar rango de dígitos (13 a 19)
        if (contador < 13 || contador > 19) {
            JOptionPane.showMessageDialog(null,
                    "El número de tarjeta debe tener entre 13 y 19 dígitos.");
            return true; // inválido
        }

        // Validación con Luhn
        if (!algoritmoLuhn(numeroTarjeta)) {
            JOptionPane.showMessageDialog(null,
                    "El número de tarjeta no es válido.");
            return true; // inválido
        }

        return false; // válido
    }

// Algoritmo de Luhn para ver si una tarjeta es válida o no
    private boolean algoritmoLuhn(long numero) {
        int suma = 0;
        boolean alternar = false;

        while (numero > 0) {
            int digito = (int) (numero % 10); // último dígito y se tranforma a int

            if (alternar) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }

            suma += digito;
            alternar = !alternar; // alternar cada vez
            numero /= 10; // quitar el último dígito
        }

        return (suma % 10 == 0);
    }

    public String ocultarTarjeta(long numeroTarjeta) {
        long temp = numeroTarjeta;
        int contador = 0;
        long[] digitos = new long[19]; // máximo 19 dígitos
        int index = 0;

        // Extraer los dígitos del número en un arreglo (de derecha a izquierda)
        while (temp > 0) {
            digitos[index] = temp % 10;
            temp /= 10;
            index++;
            contador++;
        }

        String resultado = "";

        // Recorrer los dígitos de izquierda a derecha
        for (int i = contador - 1; i >= 0; i--) {
            if (i >= 4) {
                resultado += "*"; // ocultar todos menos los últimos 4
            } else {
                resultado += digitos[i] + ""; // concatenar los últimos 4
            }
        }

        return resultado;
    }

}
