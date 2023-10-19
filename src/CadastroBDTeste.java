import cadastro.model.PessoaFisicaDAO;
import cadastro.model.PessoaJuridicaDAO;
import cadastrodb.model.PessoaFisica;
import cadastrodb.model.PessoaJuridica;

import java.util.List;
import java.util.Scanner;

public class CadastroBDTeste {
    public static void main(String[] args) {
        PessoaFisica pessoaFisica = new PessoaFisica();
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
        Scanner scanner = new Scanner(System.in);

        Boolean fecharWhile = false;

        System.out.println(
                "------Bem vindo!------ \n" +
                "1 - Incluir Pessoa Fisica ou Juridica \n" +
                "2 - Alterar um usuario Fisico ou Juridico \n" +
                "3 - Excluir um usuario \n" +
                "4 - Exibir usuario pelo ID \n" +
                "5 - Exibir todos os Usuarios \n" +
                "0 - Encerrar programa"
        );
        int entrada = scanner.nextInt();
        scanner.nextLine();

        switch (entrada) {
            case 1: // Incluir uma Pessoa Fisica ou Juridica.
                do {
                    String opcaoFJ = "";

                    System.out.println(
                            "------------------/------------------ \n" +
                            "Escolha uma das opções abaixo: \n" +
                            " F - para adicionar uma pessoa Fisica \n" +
                            " J - para adicionar uma pessoa Juridica"
                    );
                    opcaoFJ = scanner.nextLine().toUpperCase();

                    if (opcaoFJ.equals("F")) {
                        System.out.println("Digite um nome: ");
                        String nome = scanner.nextLine();

                        System.out.println("Digite um logradouro: ");
                        String logradouro = scanner.nextLine();

                        System.out.println("Digite a cidade: ");
                        String cidade = scanner.nextLine();

                        System.out.println("Digite o Estado: ");
                        String estado = scanner.nextLine();

                        System.out.println("Digite o telefone: ");
                        String telefone = scanner.nextLine();

                        System.out.println("Digite o email: ");
                        String email = scanner.nextLine();

                        System.out.println("Digite o CPF: ");
                        String cpf = scanner.nextLine();

                        pessoaFisica.setNome(nome);
                        pessoaFisica.setLogradouro(logradouro);
                        pessoaFisica.setCidade(cidade);
                        pessoaFisica.setEstado(estado);
                        pessoaFisica.setTelefone(telefone);
                        pessoaFisica.setEmail(email);
                        pessoaFisica.setCpf(cpf);

                        pessoaFisicaDAO.incluir(pessoaFisica);
                        fecharWhile = true;
                    } else if (opcaoFJ.equals("J")) {
                        System.out.println("Digite um nome juridico: ");
                        String nome = scanner.nextLine();

                        System.out.println("Digite um logradouro: ");
                        String logradouro = scanner.nextLine();

                        System.out.println("Digite a cidade: ");
                        String cidade = scanner.nextLine();

                        System.out.println("Digite o Estado: ");
                        String estado = scanner.nextLine();

                        System.out.println("Digite o telefone: ");
                        String telefone = scanner.nextLine();

                        System.out.println("Digite o email: ");
                        String email = scanner.nextLine();

                        System.out.println("Digite o CNPJ: ");
                        String cnpj = scanner.nextLine();

                        pessoaJuridica.setNome(nome);
                        pessoaJuridica.setLogradouro(logradouro);
                        pessoaJuridica.setCidade(cidade);
                        pessoaJuridica.setEstado(estado);
                        pessoaJuridica.setTelefone(telefone);
                        pessoaJuridica.setEmail(email);
                        pessoaJuridica.setCnpj(cnpj);

                        pessoaJuridicaDAO.incluir(pessoaJuridica);
                        fecharWhile = true;
                    } else {
                        System.out.println("Opção inválida. Tente novamente.");
                    }
                } while (!fecharWhile);
                break;
            case 2: // Alterar uma Pessoa Fisica ou Juridica.
                do {
                    String opcaoFJ = "";

                    System.out.println(
                            "------------------/------------------ \n" +
                            "Escolha uma das opções abaixo: \n" +
                            " F - para adicionar uma pessoa Fisica \n" +
                            " J - para adicionar uma pessoa Juridica"
                    );
                    opcaoFJ = scanner.nextLine().toUpperCase();
                    if (opcaoFJ.equals("F")) {
                        System.out.println("Digite o ID da Pessoa Fisica que você deseja alterar: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        pessoaFisica = pessoaFisicaDAO.getPessoa(pessoaFisica, id);

                        System.out.println("Esses sao os dados atuais do Usuario, faça suas alterações: ");
                        System.out.println(pessoaFisica.exibir());

                        System.out.println("Digite o novo nome: ");
                        String nome = scanner.nextLine();

                        System.out.println("Digite o novo logradouro: ");
                        String logradouro = scanner.nextLine();

                        System.out.println("Digite a nova cidade: ");
                        String cidade = scanner.nextLine();

                        System.out.println("Digite o novo estado: ");
                        String estado = scanner.nextLine();

                        System.out.println("Digite o novo telefone: ");
                        String telefone = scanner.nextLine();

                        System.out.println("Digite o novo email: ");
                        String email = scanner.nextLine();

                        System.out.println("Digite o novo cpf: ");
                        String cpf = scanner.nextLine();

                        pessoaFisica.setId(id);
                        pessoaFisica.setNome(nome);
                        pessoaFisica.setLogradouro(logradouro);
                        pessoaFisica.setCidade(cidade);
                        pessoaFisica.setEstado(estado);
                        pessoaFisica.setTelefone(telefone);
                        pessoaFisica.setEmail(email);
                        pessoaFisica.setCpf(cpf);

                        pessoaFisicaDAO.alterar(pessoaFisica);
                        fecharWhile = true;
                    } else if (opcaoFJ.equals("J")) {
                        System.out.println("Digite o ID da Pessoa Juridica que você deseja alterar: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        pessoaJuridica = pessoaJuridicaDAO.getPessoa(pessoaJuridica, id);

                        System.out.println("Esses sao os dados atuais do Usuario, faça suas alterações: ");
                        System.out.println(pessoaJuridica.exibir());

                        System.out.println("Digite o novo nome: ");
                        String nome = scanner.nextLine();

                        System.out.println("Digite o novo logradouro: ");
                        String logradouro = scanner.nextLine();

                        System.out.println("Digite a nova cidade: ");
                        String cidade = scanner.nextLine();

                        System.out.println("Digite o novo estado: ");
                        String estado = scanner.nextLine();

                        System.out.println("Digite o novo telefone: ");
                        String telefone = scanner.nextLine();

                        System.out.println("Digite o novo email: ");
                        String email = scanner.nextLine();

                        System.out.println("Digite o novo cnpj: ");
                        String cnpj = scanner.nextLine();

                        pessoaJuridica.setId(id);
                        pessoaJuridica.setNome(nome);
                        pessoaJuridica.setLogradouro(logradouro);
                        pessoaJuridica.setCidade(cidade);
                        pessoaJuridica.setEstado(estado);
                        pessoaJuridica.setTelefone(telefone);
                        pessoaJuridica.setEmail(email);
                        pessoaJuridica.setCnpj(cnpj);

                        pessoaJuridicaDAO.alterar(pessoaJuridica);
                        fecharWhile = true;
                    }
                } while (!fecharWhile);
                break;
            case 3: // Excluir uma Pessoa Fisica ou Juridica.
                do {
                    String opcaoFJ = "";

                    System.out.println(
                            "------------------/------------------ \n" +
                            "Escolha uma das opções abaixo: \n" +
                            " F - para adicionar uma pessoa Fisica \n" +
                            " J - para adicionar uma pessoa Juridica"
                    );
                    opcaoFJ = scanner.nextLine().toUpperCase();
                    if (opcaoFJ.equals("F")) {
                        System.out.println("Digite o ID da Pessoa Fisica que você deseja remover: ");

                        int id = scanner.nextInt();
                        scanner.nextLine();

                        pessoaFisicaDAO.excluir(id);
                        fecharWhile = true;
                    }
                    if (opcaoFJ.equals("J")) {
                        System.out.println("Digite o ID da Pessoa Juridica que você deseja remover: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        pessoaJuridicaDAO.excluir(id);
                        fecharWhile = true;
                    }
                } while (!fecharWhile);
                break;
            case 4: // Exibir usuario pelo ID.
                do {
                    String opcaoFJ = "";

                    System.out.println(
                            "------------------/------------------ \n" +
                            "Escolha uma das opções abaixo: \n" +
                            " F - para adicionar uma pessoa Fisica \n" +
                            " J - para adicionar uma pessoa Juridica"
                    );
                    opcaoFJ = scanner.nextLine().toUpperCase();
                    if (opcaoFJ.equals("F")) {
                        System.out.println("Digite o ID da Pessoa Fisica que você deseja consultar: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        pessoaFisica = pessoaFisicaDAO.getPessoa(pessoaFisica, id);
                        System.out.println(pessoaFisica.exibir());
                        fecharWhile = true;
                    } if (opcaoFJ.equals("J")) {
                        System.out.println("Digite o ID da Pessoa Juridica que você deseja consultar: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        pessoaJuridica = pessoaJuridicaDAO.getPessoa(pessoaJuridica, id);
                        System.out.println(pessoaJuridica.exibir());
                        fecharWhile = true;
                    }
                } while (!fecharWhile);
                break;
            case 5: // Exibir todos os usuarios
                do {
                    String opcaoFJ = "";

                    System.out.println(
                            "------------------/------------------ \n" +
                            "Escolha uma das opções abaixo: \n" +
                            " F - para adicionar uma pessoa Fisica \n" +
                            " J - para adicionar uma pessoa Juridica"
                    );
                    opcaoFJ = scanner.nextLine().toUpperCase();

                    if (opcaoFJ.equals("F")) {
                        if (opcaoFJ.equals("F")) {
                            List<PessoaFisica> pessoas = pessoaFisicaDAO.getPessoas();

                            for (PessoaFisica pessoa : pessoas) {
                                System.out.println(pessoa.exibir());
                            }
                            fecharWhile = true;
                        }
                    }
                    if (opcaoFJ.equals("J")) {
                        List<PessoaJuridica> pessoas = pessoaJuridicaDAO.getPessoas();

                        for (PessoaJuridica pessoa : pessoas) {
                            System.out.println(pessoa.exibir());
                        }
                        fecharWhile = true;
                    }
                } while (!fecharWhile);
                break;
            case 6: // Parar programa
                break;
            }
    }
}