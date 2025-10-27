package pro.Informes;
import pro.Entidad.Cliente;
import pro.Entidad.Pago;
import pro.Entidad.Plan;

public class Fila {
    Cliente cliente;
    Pago pago;
    Plan plan;

    public Fila(String nombre, String apellido, String localidad, int numeroPlan, int numeroCuota, double importeC,
            String medioPago, String banco, String fecha, double importeD, String nroTransaccion) {
        cliente = new Cliente(nombre, apellido, localidad);
        pago = new Pago(nroTransaccion, fecha, importeD, banco, medioPago);
        plan = new Plan(numeroPlan, importeC, numeroCuota);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Pago getPago() {
        return pago;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

}
