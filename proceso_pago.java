public class proceso_pago {
    private double totalaPagar;
    private double impuestos;
    private double descuento;
    private pasajes datosPagoPasaje;
    private maletas datosPagoMaleta;

    public proceso_pago(double totalaPagar, double impuestos,
            pasajes datosPagoPasaje, maletas datosPagoMaleta) {

        this.totalaPagar = totalaPagar;
        this.impuestos = 0.20;
        this.descuento= descuento;
        this.datosPagoMaleta = datosPagoMaleta;
        this.datosPagoPasaje = datosPagoPasaje;

    }

    public double CalculoPagoTotal() {
        if(datosPagoPasaje.get=="X"){

        }
        return totalaPagar;
    }
}
