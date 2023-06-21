import java.time.LocalDate;

public class Transacao {
    private int id;
    private String tipo;
    private double valor;
    private LocalDate data;

    public Transacao(String tipo, double valor, LocalDate data) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
    }

    public int getId() {
        return this.id;
    }

    public String getTipo() {
        return this.tipo;
    }

    public double getValor() {
        return this.valor;
    }

    public LocalDate getData() {
        return this.data;
    }
}
