package cadastrodb.model;

public class PessoaJuridica extends Pessoa {
    private String cnpj;

    public PessoaJuridica() {

    }

    public PessoaJuridica(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cnpj) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
                "CNPJ: " + cnpj + "\n" +
                "----------------/----------------";
    }
}