import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
        // Leitura do teclado para saber qual escolha o cliente esta fazendo.
        BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
        Scanner teclado = new Scanner(System.in);

        int op, id;
        String nome, documento, tipoCliente, senha;
        // Tela inicial para o cliente escolher as op��es do banco.
        do {
            System.out.println("Olá, bem vindo ao nosso banco");
            System.out.println("1 - Fazer Login");
            System.out.println("2 - Fazer Cadastro");
            System.out.println("0 - Sair do Banco");
            System.out.print("Digite a op��o desejada: ");

            op = Integer.parseInt(reader.readLine());

            //Qual das op��es acima foi escolhida.
            switch (op) {

                case 1:
                    // Realizndo seu Login.
                    System.out.println("\n Realizando seu Login...");


                    break;

                case 2:
                    // Realiza��o do Cadastro.
                    System.out.println("\n Realizando o seu Cadastro");
                    System.out.println("\n 1 - Você é Pessoa F�sica ?");
                    System.out.println("\n 2 - Você é Pessoa Jur�dica ?");
                    System.out.print("\n Digite sua op��o: ");

                    op = teclado.nextInt();

                    switch (op) {

                        case 1:
                            System.out.print("\n Digite seu Nome: ");
                            nome = reader.readLine();
                            System.out.print("\n Digite seu CPF: ");
                            documento = reader.readLine();
                            tipoCliente = "cpf";
                            System.out.print("\n Digite seu Senha: ");
                            senha = reader.readLine();

                            Pfisica pFisica = new Pfisica(nome, senha, documento);
                            PfisicaDAO acoespf = new PfisicaDAO();

                            acoespf.realizarCadastro(pFisica, tipoCliente);

                            break;

                        case 2:
                            System.out.print("\n Digite seu Nome: ");
                            nome = reader.readLine();
                            System.out.print("\n Digite seu CNPJ: ");
                            documento = reader.readLine();
                            tipoCliente = "cnpj";
                            System.out.print("\n Digite seu Senha: ");
                            senha = reader.readLine();

                            Pjuridica pJuridica = new Pjuridica(nome, senha, documento);
                            PjuridicaDAO acoespj = new PjuridicaDAO();

                            acoespj.realizarCadastro(pJuridica, tipoCliente);

                            break;

                    }

                    break;
            }

        } while(op != 0);
        teclado.close();

    }

}
