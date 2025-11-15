package pro.Informes;

import pro.Entidad.Cliente;
import pro.Entidad.Pago;
import pro.Entidad.Plan;

public class Fila {
    private Cliente cliente;
    private Pago pago;
    private Plan plan;
    private String motivo;
    private int tipoFila;

    // Inicializa la fila para un informe de tipo pago o actualizacion
    public Fila(String nombre, String apellido, String localidad, int numeroPlan, int numeroCuota, double importeC,
            String medioPago, String banco, String fecha, double importeD, String nroTransaccion) {
        cliente = new Cliente(nombre, apellido, localidad);
        pago = new Pago(nroTransaccion, fecha, importeD, banco, medioPago);
        plan = new Plan(numeroPlan, importeC, numeroCuota);
        tipoFila = 1;
    }

    // Inicializa la fila para un informe de tipo impago
    public Fila(String nombre, String apellido, String localidad, int numeroPlan, int numeroCuota, String motivo) {
        cliente = new Cliente(nombre, apellido, localidad);
        plan = new Plan(numeroPlan, numeroCuota);
        this.motivo = motivo;
        tipoFila = 2;
    }

    // Inicializa la fila para un informe de tipo bajas
    public Fila(String nombre, String apellido, String localidad, int numeroPlan, String motivo) {
        cliente = new Cliente(nombre, apellido, localidad);
        plan = new Plan(numeroPlan);
        this.motivo = motivo;
        tipoFila = 3;
    }

    // Recupera los atributos de la fila en un arreglo String
    public String[] recuperarCeldas() {

        String[] celdas = { "" };
        switch (tipoFila) {
            case 1:
                celdas = new String[] { cliente.getNombre(), cliente.getApellido(), cliente.getLocalidad(),
                        String.valueOf(plan.getNumeroPlan()), String.valueOf(plan.getNumeroCuota()),
                        String.valueOf(plan.getImporte()), pago.getTipoPago(), pago.getBanco(), pago.getFecha(),
                        String.valueOf(pago.getImporte()), pago.getNumeroTransaccion() };
                break;
            case 2:
                celdas = new String[] { cliente.getNombre(), cliente.getApellido(), cliente.getLocalidad(),
                        String.valueOf(plan.getNumeroPlan()), String.valueOf(plan.getNumeroCuota()), motivo };
                break;
            case 3:
                celdas = new String[] { cliente.getNombre(), cliente.getApellido(), cliente.getLocalidad(),
                        String.valueOf(plan.getNumeroPlan()), motivo };
                break;
        }
        return celdas;
    }

    // Getters
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

}
