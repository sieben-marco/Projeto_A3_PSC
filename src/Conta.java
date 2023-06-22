public class Conta {
    private int id;
    private double saldo;
    private Pfisica pFisica;

    public Conta(Pfisica pFisica) {
        this.saldo = 0.00;
        this.pFisica = pFisica;
    }

    public int getId() {
        return this.id;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public int getIdCliente() {
        return pFisica.getId();
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
