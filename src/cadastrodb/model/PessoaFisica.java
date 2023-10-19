package cadastrodb.model;

public class PessoaFisica extends Pessoa{
    private String cpf;

    public PessoaFisica() {

    }
    public PessoaFisica(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cpf) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String exibir() {
        return "ID: " + getId() + "\n" +
                "Nome: " + getNome() + "\n" +
                "Logradouro: " + getLogradouro() + "\n" +
                "Cidade: " + getCidade() + "\n" +
                "Estado: " + getEstado() + "\n" +
                "Telefone: " + getTelefone() + "\n" +
                "Email: " + getEmail() + "\n" +
                "CPF: " + cpf + "\n" +
                "----------------/----------------";
    }

}
