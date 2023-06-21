public class Conta {
    private int id;
    private double saldo;

    public Conta(double saldo) {
        this.saldo = 0.00;
    }

    public int getId() {
        return this.id;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
