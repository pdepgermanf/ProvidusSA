package pro.Entidad;

public class Plan {
    private int numeroPlan;
    private double importe;
    private int numeroCuota;
    public Plan(int numeroPlan, double importe, int numeroCuota) {
        this.numeroPlan = numeroPlan;
        this.importe = importe;
        this.numeroCuota = numeroCuota;
    }

    public Plan(int numeroPlan, int numeroCuota) {
        this.numeroPlan = numeroPlan;
        this.numeroCuota = numeroCuota;
    }

    public Plan(int numeroPlan) {
        this.numeroPlan = numeroPlan;
    }

    public int getNumeroPlan() {
        return numeroPlan;
    }
    public void setNumeroPlan(int numeroPlan) {
        this.numeroPlan = numeroPlan;
    }
    public double getImporte() {
        return importe;
    }
    public void setImporte(double importe) {
        this.importe = importe;
    }
    public int getNumeroCuota() {
        return numeroCuota;
    }
    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }


}
