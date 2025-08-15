/*
public class gestionEquipaje {
    
private static maletas [] maletas = new maletas [5]; 
    private static int contador = 0;

    public static void registrarMaleta () {
        if (contador >= maletas.length) {
            JOptionPane.showMessageDialog(null, "Ya no se pueden registrar más maletas.");
            return;
        }

        String tipo = JOptionPane.showInputDialog(
            "Ingrese el tipo de maleta:\nMano\nGrande\nAmbas");
        Double peso = Double.parseDouble(JOptionPane.showInputDialog(
            "Ingrese el peso de la maleta:"));

        maletas m = new maletas(peso, tipo);
        maletas [contador] = m; 
        contador++;

        JOptionPane.showMessageDialog(null, "Maleta registrada correctamente.");
    }
    public static void mostrarTodasLasMaletas(){
        System.out.println("\n--- RESUMEN DE EQUIPAJE REGISTRADO ---");
        for (int i = 0; i < contador; i++) {
            maletas [i].mostrarInformacion(i+1);
        }
    }
}
*/ 