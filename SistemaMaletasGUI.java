import javax.swing.JOptionPane;

public class SistemaMaletasGUI {
    private Maleta[] maletas = new Maleta[5];
    private int contador = 0;

    public void registrarMaletas() {
        JOptionPane.showMessageDialog(null,
            "SISTEMA DE CALCULO DE MALETAS\n------------------------------",
            "Aerolinea XYZ",
            JOptionPane.INFORMATION_MESSAGE);

        contador = 0; // Reinicia el contador para cada registro nuevo

        while (contador < maletas.length) {
            String[] opcionesMaleta = {"Mano", "Grande", "Ambas"};
            String tipo = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el tipo de maleta:",
                "Tipo de Maleta",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesMaleta,
                opcionesMaleta[0]);

            if (tipo == null) {
                JOptionPane.showMessageDialog(null,
                    "Operacion cancelada",
                    "Cancelado",
                    JOptionPane.WARNING_MESSAGE);
                break;
            }

            double peso = 0;
            boolean pesoValido = false;
            while (!pesoValido) {
                String pesoStr = JOptionPane.showInputDialog(
                    null,
                    "Ingrese el peso actual (kg):",
                    "Peso de la Maleta",
                    JOptionPane.QUESTION_MESSAGE);

                if (pesoStr == null) {
                    JOptionPane.showMessageDialog(null,
                        "Operacion cancelada",
                        "Cancelado",
                        JOptionPane.WARNING_MESSAGE);
                    tipo = null; // Para salir del bucle exterior también
                    break;
                }

                if (pesoStr.matches("\\d+(\\.\\d+)?")) {
                    peso = Double.parseDouble(pesoStr);
                    if (peso > 0) {
                        pesoValido = true;
                    } else {
                        JOptionPane.showMessageDialog(null,
                            "El peso debe ser mayor que cero",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                        "Debe ingresar un numero valido (ej. 5.5)",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
            if (tipo == null) break; // Si se canceló la entrada de peso

            Maleta maleta = new Maleta(peso, tipo);
            maletas[contador] = maleta;
            contador++;

            String mensajeMulta = "";
            if (tipo.equals("Mano") && peso > 10) {
                mensajeMulta = "\nADVERTENCIA: Exceso de peso en maleta de mano (+$10 de multa)";
            } else if (tipo.equals("Grande") && peso > 23) {
                mensajeMulta = "\nADVERTENCIA: Exceso de peso en maleta grande (+$20 de multa)";
            } else if (tipo.equals("Ambas") && peso > 33) {
                mensajeMulta = "\nADVERTENCIA: Exceso de peso en ambas maletas (+$30 de multa)";
            }

            String mensajeResultado = "Maleta #" + contador +
                                     "\nTipo: " + tipo +
                                     "\nPeso: " + peso + " kg" +
                                     mensajeMulta +
                                     "\n\nPrecio base: $" + maleta.getPrecioBase() +
                                     "\nPrecio final: $" + maleta.getCosto();

            JOptionPane.showMessageDialog(null,
                mensajeResultado,
                "Resultado del Calculo",
                JOptionPane.INFORMATION_MESSAGE);

            if (contador < maletas.length) {
                int opcion = JOptionPane.showConfirmDialog(null,
                    "Desea registrar otra maleta?",
                    "Agregar maleta",
                    JOptionPane.YES_NO_OPTION);
                if (opcion != JOptionPane.YES_OPTION) {
                    break;
                }
            }
        }

        if (contador > 0) {
            String resumen = "--- RESUMEN DE EQUIPAJE REGISTRADO ---\n";
            for (int i = 0; i < contador; i++) {
            Maleta m = maletas[i];
            resumen += "Maleta #" + (i+1) + ": " +
                m.getTipo() + ", " +
                m.getPeso() + " kg, " +
                "Precio final: $" + m.getCosto() + "\n";
            }
            JOptionPane.showMessageDialog(null,
            resumen,
            "Resumen de Maletas",
            JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public Maleta[] getMaletas() {
        return maletas;
    }
    public int getCantidadMaletas() {
        return contador;
    }
}

class Maleta {
    private double peso;
    private String tipo;
    private double costo;

    public Maleta(double peso, String tipo) {
        this.peso = peso;
        this.tipo = tipo;
        calcularCosto();
    }

    private void calcularCosto() {
        switch(tipo) {
            case "Mano":
                costo = 25;
                if (peso > 10) costo += 10;
                break;
            case "Grande":
                costo = 40;
                if (peso > 23) costo += 20;
                break;
            case "Ambas":
                costo = 60;
                if (peso > 33) costo += 30;
                break;
            default:
                costo = 0;
        }
    }

    public double getPrecioBase() {
        switch(tipo) {
            case "Mano": return 25;
            case "Grande": return 40;
            case "Ambas": return 60;
            default: return 0;
        }
    }

    public double getCosto() {
        return costo;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPeso() {
        return peso;
    }
}
