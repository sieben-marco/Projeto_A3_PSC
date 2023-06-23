import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
        // Leitura do teclado para saber qual escolha o cliente esta fazendo.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner teclado = new Scanner(System.in);

        PfisicaDAO acoesPF = new PfisicaDAO();
        PjuridicaDAO acoesPJ = new PjuridicaDAO();

        ContaDAO acoesConta = new ContaDAO();
        TransacaoDAO acoesTrans = new TransacaoDAO();

        int op1, op2, id;
        double valor, point = 0.0;
        String nome, documento, tipoCliente, senha, tipoTransacao;
        // Tela inicial para o cliente escolher as op��es do banco.
        do {
            System.out.println("Olá, bem vindo ao Nosso Banco");
            System.out.println("1 - Fazer Login");
            System.out.println("2 - Fazer Cadastro");
            System.out.println("0 - Sair do Banco");
            System.out.print("Digite a opção desejada: ");

            op1 = Integer.parseInt(reader.readLine());

            //Qual das op��es acima foi escolhida.
            switch (op1) {

                case 1:
                    // Realizando seu Login.
                    System.out.println("\nRealizando seu Login...");
                    /*System.out.println("1 - Você é Pessoa Física?");
                    System.out.println("2 - Você é Pessoa Jurídica?");
                    System.out.print("Digite sua opção: ");*/

                    System.out.print("Digite seu CPF/CNPJ: ");
                    documento = reader.readLine();
                    int tamanhoDoc = documento.length();

                    if (tamanhoDoc <= 14) {
                        op1 = 1;
                    } else {
                        op1 = 2;
                    }

                    //op1 = teclado.nextInt();

                    boolean acesso = false;
                    switch (op1) {
                        case 1:
                            //System.out.print("Digite seu CPF: ");
                            //documento = reader.readLine();
                            System.out.print("Digite sua senha: ");
                            senha = reader.readLine();

                            acesso = acoesPF.realizarLogin(documento, senha);

                            System.out.println();

                            if (acesso) {
                                do {
                                    id = acoesPF.obterIdCliente(documento);

                                    System.out.println("TELA INICIAL");

                                    if (senha.equals("admin")) {
                                        System.out.println("11 - Excluir Conta");
                                    }

                                    System.out.println("1 - Fazer Depósito");
                                    System.out.println("2 - Fazer Saque");
                                    System.out.println("3 - Mostrar Extrato");
                                    System.out.println("4 - Mostrar Saldo");
                                    System.out.println("5 - Consultar Recompensas");
                                    System.out.println("6 - Encerrar Conta");
                                    //System.out.println("7 - Excluir Conta");
                                    System.out.println("0 - Voltar para Tela inicial");
                                    System.out.print("Digite a opção desejada: ");

                                    op2 = Integer.parseInt(reader.readLine());

                                    switch (op2) {
                                        case 1:
                                            tipoTransacao = "deposito";
                                            System.out.print("Digite o valor a ser depositado: ");
                                            valor = Double.parseDouble(reader.readLine());
                                            LocalDate data1 = LocalDate.now();

                                            //Pfisica pFisica = new Pfisica();

                                            //Conta contaPF = new Conta(p);
                                            acoesConta.depositar(valor, id);

                                            point += (valor * 0.1);

                                            Transacao deposito = new Transacao(tipoTransacao, valor, data1);
                                            acoesTrans.registrar(deposito, id);



                                            break;
                                        case 2:
                                            tipoTransacao = "saque";
                                            System.out.print("Digite o valor a ser sacado: ");
                                            valor = Double.parseDouble(reader.readLine());
                                            LocalDate data2 = LocalDate.now();

                                            acoesConta.sacar(valor, id);

                                            Transacao saque = new Transacao(tipoTransacao, valor, data2);
                                            acoesTrans.registrar(saque, id);

                                            break;

                                        case 3:
                                            acoesTrans.consultar(id);
                                            break;

                                        case 4:
                                            acoesConta.mostrarSaldo(id);
                                            break;

                                        case 5:
                                            Produtos produtos = new Produtos();

                                            produtos.mostrarProdutos();

                                            break;

                                        case 6:
                                            System.out.println("Deseja realmente encerrar sua conta?" +
                                                    "\n1 - SIM" +
                                                    "\n2 - NÃO");
                                            System.out.print("Digite sua opção: ");
                                            int enc = teclado.nextInt();

                                            if (enc == 1) {
                                                System.out.print("Digite seu CPF: ");
                                                documento = reader.readLine();
                                                System.out.print("Digite sua senha: ");
                                                senha = reader.readLine();

                                                acesso = acoesPF.realizarLogin(documento, senha);
                                                System.out.println();

                                                if (acesso) {
                                                    acoesPF.encerrarConta(id);

                                                    op2 = 0;
                                                }
                                            }
                                            break;

                                        case 11:
                                            acoesPF.listarPfisica();

                                            System.out.println("Deseja realmente excluir esse cliente?" +
                                                               "\n1 - SIM" +
                                                               "\n2 - NÃO");
                                            System.out.print("Digite sua opção: ");
                                            int exc = teclado.nextInt();

                                            if (exc == 1) {
                                                System.out.print("Digite seu CPF: ");
                                                documento = reader.readLine();
                                                System.out.print("Digite sua senha: ");
                                                senha = reader.readLine();

                                                acesso = acoesPF.realizarLogin(documento, senha);
                                                System.out.println();

                                                System.out.print("Digite o ID que deseja excluir: ");
                                                int id55 = Integer.parseInt(reader.readLine());

                                                acoesTrans.excluirTrans(id55);
                                                acoesConta.excluirConta(id55);
                                                acoesPF.excluirCliente(id55);

                                                System.out.println();
                                            }
                                            break;

                                        case 0:
                                            System.out.println("\nVocê está voltando para tela inicial...\n");
                                            break;

                                        default:
                                            System.out.println("\nOpção não permitida. Escolha outra!\n");
                                    }
                                } while (op2 != 0);
                            }

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
                    System.out.println("\nRealizando seu Cadastro...");
                    //System.out.println("1 - Você é Pessoa Física?");
                    //System.out.println("2 - Você é Pessoa Jurídica?");
                    //System.out.print("Digite sua opção: ");

                    System.out.print("\nDigite seu Nome: ");
                    nome = reader.readLine();
                    System.out.print("Digite seu CPF/CNPJ: ");
                    documento = reader.readLine();
                    System.out.print("\nDigite seu Senha: ");
                    senha = reader.readLine();

                    int tamanhoDocC = documento.length();

                    if (tamanhoDocC <= 14) {
                        op1 = 1;
                    } else {
                        op1 = 2;
                    }

                    //op1 = teclado.nextInt();

                    switch (op1) {

                        case 1:

                            //System.out.print("\nDigite seu CPF: ");
                            //documento = reader.readLine();
                            tipoCliente = "cpf";


                            Pfisica pFisica = new Pfisica(nome, senha, documento);
                            acoesPF.realizarCadastro(pFisica, tipoCliente);

                            id = acoesPF.obterIdCliente(documento);
                            Conta conta1 = new Conta(pFisica);
                            acoesConta.abrirConta(conta1, id);

                            tipoTransacao = "deposito";
                            System.out.print("Digite o valor a ser depositado: ");
                            valor = Double.parseDouble(reader.readLine());
                            LocalDate data1 = LocalDate.now();

                            acoesConta.depositar(valor, id);

                            point = 100.00;

                            Transacao deposito = new Transacao(tipoTransacao, valor, data1);
                            acoesTrans.registrar(deposito, id);

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

                    break;
                case 0:
                    System.out.println("\nVocê está sendo desconectado...\n");
                    break;

                default:
                    System.out.println("\nOpção não permitida. Escolha outra!\n");
            }

        } while(op1 != 0);
        teclado.close();
    }
}
