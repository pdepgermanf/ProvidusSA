package pro.Entidad;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pago {
    private String numeroTransaccion;
    private String fecha;
    private double importe;
    private String banco;
    private String tipoPago;
    public Pago(String numeroTransaccion, String fecha, double importe, String banco, String tipoPago) {
        this.importe = importe;
        this.banco = banco;
        this.tipoPago = tipoPago;
        this.numeroTransaccion=numeroTransaccion;

        
        //Cambia el formato por mysql
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate f = LocalDate.parse(fecha,formato);
        formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.fecha= f.format(formato);
    }
    public String getNumeroTransaccion() {
        return numeroTransaccion;
    }
    public void setNumeroTransaccion(String numeroTransaccion) {
        this.numeroTransaccion = numeroTransaccion;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        
        this.fecha = fecha;
    }
    public double getImporte() {
        return importe;
    }
    public void setImporte(double importe) {
        this.importe = importe;
    }
    public String getBanco() {
        return banco;
    }
    public void setBanco(String banco) {
        this.banco = banco;
    }
    public String getTipoPago() {
        return tipoPago;
    }
    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    
    
}
