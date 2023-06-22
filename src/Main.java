import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
        // Leitura do teclado para saber qual escolha o cliente esta fazendo.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner teclado = new Scanner(System.in);

        PfisicaDAO acoesPF = new PfisicaDAO();
        PjuridicaDAO acoesPJ = new PjuridicaDAO();

        int op, id;
        String nome, documento, tipoCliente, senha;
        // Tela inicial para o cliente escolher as op��es do banco.
        do {
            System.out.println("Olá, bem vindo ao nosso banco");
            System.out.println("1 - Fazer Login");
            System.out.println("2 - Fazer Cadastro");
            System.out.println("0 - Sair do Banco");
            System.out.print("Digite a opção desejada: ");

            op = Integer.parseInt(reader.readLine());

            //Qual das op��es acima foi escolhida.
            switch (op) {

                case 1:
                    // Realizando seu Login.
                    System.out.println("\nRealizando seu Login...");

                    System.out.println("\nRealizando o seu Login...");
                    System.out.println("\n1 - Você é Pessoa Física?");
                    System.out.println("\n2 - Você é Pessoa Jurídica?");
                    System.out.print("\nDigite sua opção: ");

                    op = teclado.nextInt();

                    boolean acesso = false;
                    switch (op) {
                        case 1:
                            System.out.print("Digite seu CPF: ");
                            documento = reader.readLine();
                            System.out.print("Digite sua senha: ");
                            senha = reader.readLine();

                            acesso = acoesPF.realizarLogin(documento, senha);
                            System.out.println();
                            System.out.println(acesso);
                            System.out.println();

                            break;
                        case 2:
                            System.out.print("Digite seu CNPJ: ");
                            documento = reader.readLine();
                            System.out.print("Digite sua senha: ");
                            senha = reader.readLine();

                            acoesPJ.realizarLogin(documento, senha);

                            acesso = acoesPJ.realizarLogin(documento, senha);
                            System.out.println();
                            System.out.println(acesso);
                            System.out.println();

                            break;
                    }

                    break;

                case 2:
                    // Realização do Cadastro.
                    System.out.println("\nRealizando o seu Cadastro");
                    System.out.println("\n1 - Você é Pessoa Física?");
                    System.out.println("\n2 - Você é Pessoa Jurídica?");
                    System.out.print("\n Digite sua opção: ");

                    op = teclado.nextInt();

                    switch (op) {

                        case 1:
                            System.out.print("\nDigite seu Nome: ");
                            nome = reader.readLine();
                            System.out.print("\nDigite seu CPF: ");
                            documento = reader.readLine();
                            tipoCliente = "cpf";
                            System.out.print("\nDigite seu Senha: ");
                            senha = reader.readLine();

                            Pfisica pFisica = new Pfisica(nome, senha, documento);


                            acoesPF.realizarCadastro(pFisica, tipoCliente);

                            break;

                        case 2:
                            System.out.print("\nDigite seu Nome: ");
                            nome = reader.readLine();
                            System.out.print("\nDigite seu CNPJ: ");
                            documento = reader.readLine();
                            tipoCliente = "cnpj";
                            System.out.print("\nDigite seu Senha: ");
                            senha = reader.readLine();

                            Pjuridica pJuridica = new Pjuridica(nome, senha, documento);


                            acoesPJ.realizarCadastro(pJuridica, tipoCliente);

                            break;

                    }
            }

        } while(op != 0);
        teclado.close();

    }

}
