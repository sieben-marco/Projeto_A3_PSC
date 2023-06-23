public class Conta {
    private int id;
    private double saldo;
    private Pfisica pFisica;
    private Pjuridica pJuridica;

    public Conta(Pfisica pFisica) {
        this.saldo = 0.00;
        this.pFisica = pFisica;
    }

    public Conta(Pjuridica pJuridica) {
        this.saldo = 0.00;
        this.pJuridica = pJuridica;
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
