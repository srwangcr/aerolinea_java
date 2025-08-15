import javax.swing.JOptionPane;

public class SistemaMaletasGUI {
    public static void main(String[] args) {
        // Mostrar mensaje de bienvenida
        JOptionPane.showMessageDialog(null, 
            "SISTEMA DE CÁLCULO DE MALETAS\n------------------------------", 
            "Aerolínea XYZ", 
            JOptionPane.INFORMATION_MESSAGE);
        
        // Opciones para el tipo de maleta
        String[] opcionesMaleta = {"Mano", "Grande", "Ambas"};
        String tipo = (String) JOptionPane.showInputDialog(
            null,
            "Seleccione el tipo de maleta:",
            "Tipo de Maleta",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcionesMaleta,
            opcionesMaleta[0]);
        
        // Validar si el usuario canceló
        if (tipo == null) {
            JOptionPane.showMessageDialog(null, 
                "Operación cancelada", 
                "Cancelado", 
                JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
        
        // Pedir el peso con validación
        double peso = 0;
        boolean pesoValido = false;
        while (!pesoValido) {
            try {
                String pesoStr = JOptionPane.showInputDialog(
                    null,
                    "Ingrese el peso actual (kg):",
                    "Peso de la Maleta",
                    JOptionPane.QUESTION_MESSAGE);
                
                // Validar si el usuario canceló
                if (pesoStr == null) {
                    JOptionPane.showMessageDialog(null, 
                        "Operación cancelada", 
                        "Cancelado", 
                        JOptionPane.WARNING_MESSAGE);
                    System.exit(0);
                }
                
                peso = Double.parseDouble(pesoStr);
                if (peso > 0) {
                    pesoValido = true;
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "El peso debe ser mayor que cero", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, 
                    "Debe ingresar un número válido", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        // Calcular costos
        Maleta maleta = new Maleta(peso, tipo);
        String mensajeMulta = "";
        
        if (tipo.equals("Mano") && peso > 10) {
            mensajeMulta = "\nADVERTENCIA: Exceso de peso en maleta de mano (+$10 de multa)";
        } else if (tipo.equals("Grande") && peso > 23) {
            mensajeMulta = "\nADVERTENCIA: Exceso de peso en maleta grande (+$20 de multa)";
        } else if (tipo.equals("Ambas") && peso > 33) {
            mensajeMulta = "\nADVERTENCIA: Exceso de peso en ambas maletas (+$30 de multa)";
        }
        
        // Mostrar resultados
        String mensajeResultado = "Tipo: " + tipo + 
                                 "\nPeso: " + peso + " kg" +
                                 mensajeMulta + 
                                 "\n\nPrecio base: $" + maleta.getPrecioBase() +
                                 "\nPrecio final: $" + maleta.getCosto();
        
        JOptionPane.showMessageDialog(null, 
            mensajeResultado, 
            "Resultado del Cálculo", 
            JOptionPane.INFORMATION_MESSAGE);
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
}
