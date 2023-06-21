import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        int op, id;
        double valor;
        String tipo;
        LocalDate data = LocalDate.now();

        TransacaoDAO acoes = new TransacaoDAO();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Scanner input = new Scanner(System.in);

        do {
            System.out.println("\n\n========= TRANSAÇÃO =========");
            System.out.println("1 | Saque");
            System.out.println("2 | Depósito");
            System.out.println("3 | Extrato");
            System.out.println("0 | Sair da agenda");
            System.out.print("Digite a opção desejada: ");

            op = Integer.parseInt(reader.readLine());

            switch (op) {
                case 1:
                    tipo = "saque";
                    System.out.println("\n # INFORMAÇÕES PARA O SAQUE:\n");
                    System.out.print("Digite o valor que deseja sacar: ");
                    valor = input.nextDouble();

                    Transacao transacao1 = new Transacao(tipo, valor, data);
                    acoes.registrar(transacao1);
                    break;

                case 2:
                    tipo = "deposito";
                    System.out.println("\n # INFORMAÇÕES PARA O DEPÓSITO:\n");
                    System.out.print("Digite o valor que deseja depositar: ");
                    valor = input.nextDouble();

                    Transacao transacao2 = new Transacao(tipo, valor, data);
                    acoes.registrar(transacao2);
                    break;

                case 3:
                    //System.out.print("Digite o id da transacao: ");
                    //id = input.nextInt();

                    acoes.consultar();
                    break;
            }

        } while (op != 0);
    }
}
