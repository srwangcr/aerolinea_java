import javax.swing.JOptionPane;

public class maletas {
    private double peso = 0.0;
    private String tipo = "";
    private double costo = 0.0;


    public maletas(double peso, String tipo, double costo) {
     this.peso = peso;
     this.tipo = tipo;
     this.costo = costo;

}

public void calcularCosto() {
    if (tipo.equals("Mano")) {
        costo = 25;
        if ( peso > 10) {
            System.out.println("¡Advertencia! El peso de la maleta de mano excede los 10 kg.");
            this.costo = 32.0;
        }

} else if (tipo.equals("Grande")) {
    costo = 40;
    if (peso > 25) {
     System.out.println("¡Advertencia! El peso de la maleta grande excede los 25 kg.");   
}
} else if (tipo.equals("Ambas")){
    costo = 60;
}
}

public double getCosto() {
    return costo;
}

public String getTipo () {
    return tipo; 
}

public double getPeso() {
    return peso;
}




//Thats not what i meant to say at all, i mean, i sick of meaning i just wanna hold you,
//but thats not what i meant to say at all, i mean, i sick of meaning i just wanna hold you
//is it the chorus yet? 
//No, its just a build another verse, so when the chorus does come it will be more rewarding
//I would speak to you in sog but you cant sing, as far as im aware, tho everyone can sing
//cause you are well aware, i keep so i quiet is hard  to say im a alive
//everybody swinging their hips, everyones giving the waistress tips,
//everybody is dancing all of the dances, everybody is dancing every dance now
// Body's by Car Seat Headrest
}

class datos_usuario {

    public double usuario_peso = Double.parseDouble(JOptionPane.showInputDialog("Dijite el peso de su maleta, sera revisado en el aeropuerto")); 
}