package cadastro.model;

import cadastro.model.util.ConectorBD;
import cadastrodb.model.PessoaFisica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {
    public int id;
    public PessoaFisica getPessoa(PessoaFisica pessoaFisica, int id) {
        String sql = "SELECT pessoa.*, pessoafisica.* \n" +
                    "FROM pessoafisica \n" +
                    "INNER JOIN pessoa ON pessoafisica.idpessoa = pessoa.idpessoa \n" +
                    "WHERE pessoafisica.idpessoa = ?";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConectorBD.getConnection();
            pstm = ConectorBD.getPrepared(sql);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();

            if (rset.next()) {
                pessoaFisica.setId(rset.getInt("idpessoa"));
                pessoaFisica.setNome(rset.getString("nome"));
                pessoaFisica.setLogradouro(rset.getString("logradouro"));
                pessoaFisica.setCidade(rset.getString("cidade"));
                pessoaFisica.setEstado(rset.getString("estado"));
                pessoaFisica.setTelefone(rset.getString("telefone"));
                pessoaFisica.setEmail(rset.getString("email"));
                pessoaFisica.setCpf(rset.getString("cpf"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(conn, pstm);
        }
        return pessoaFisica;
    }

    public List<PessoaFisica> getPessoas() {
        String sql = "SELECT pessoa.*, pessoafisica.*\n" +
                    "FROM pessoafisica\n" +
                    "INNER JOIN pessoa ON pessoafisica.idpessoa = pessoa.idpessoa;";

        List<PessoaFisica> pessoas = new ArrayList<PessoaFisica>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConectorBD.getConnection();
            pstm = ConectorBD.getPrepared(sql);
            rset = ConectorBD.getSelect(sql);

            while (rset.next()) {
                PessoaFisica pessoaFisica = new PessoaFisica();

                pessoaFisica.setId(rset.getInt("idpessoa"));
                pessoaFisica.setNome(rset.getString("nome"));
                pessoaFisica.setLogradouro(rset.getString("logradouro"));
                pessoaFisica.setCidade(rset.getString("cidade"));
                pessoaFisica.setEstado(rset.getString("estado"));
                pessoaFisica.setTelefone(rset.getString("telefone"));
                pessoaFisica.setEmail(rset.getString("email"));
                pessoaFisica.setCpf(rset.getString("cpf"));

                pessoas.add(pessoaFisica);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(conn, pstm, rset);
        }
        return pessoas;
    }

    public void incluir(PessoaFisica pessoaFisica) {
        String sql = "INSERT INTO pessoa(nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?);";

        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = ConectorBD.getConnection();
            pstm = ConectorBD.getPrepared(sql);

            pstm.setString(1, pessoaFisica.getNome());
            pstm.setString(2, pessoaFisica.getLogradouro());
            pstm.setString(3, pessoaFisica.getCidade());
            pstm.setString(4, pessoaFisica.getEstado());
            pstm.setString(5, pessoaFisica.getTelefone());
            pstm.setString(6, pessoaFisica.getEmail());

            pstm.execute();

            ResultSet generatyedKeys = pstm.getGeneratedKeys();
            int idPessoa = 0;
            if (generatyedKeys.next()) {
                idPessoa = generatyedKeys.getInt(1);
            }

            String sqlFisic = "INSERT INTO pessoafisica (idpessoa, cpf) VALUES (?, ?)";
            pstm = ConectorBD.getPrepared(sqlFisic);

            pstm.setInt(1, idPessoa);
            pstm.setString(2, pessoaFisica.getCpf());

            pstm.execute();

            System.out.println("Contato salvo com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(conn, pstm);
        }
    }

    public void alterar(PessoaFisica pessoaFisica) {
        String sql = "UPDATE pessoa \n" +
                    "SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? \n" +
                    "WHERE idpessoa = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConectorBD.getConnection();
            pstm = ConectorBD.getPrepared(sql);

            pstm.setString(1, pessoaFisica.getNome());
            pstm.setString(2, pessoaFisica.getLogradouro());
            pstm.setString(3, pessoaFisica.getCidade());
            pstm.setString(4, pessoaFisica.getEstado());
            pstm.setString(5, pessoaFisica.getTelefone());
            pstm.setString(6, pessoaFisica.getEmail());

            pstm.setInt(7, pessoaFisica.getId());

            pstm.execute();

            String sqlFisic = "UPDATE pessoafisica \n" +
                            "SET idpessoa = ?, cpf = ? \n" +
                            "WHERE idpessoa = ?";

            pstm = ConectorBD.getPrepared(sqlFisic);

            pstm.setInt(1, pessoaFisica.getId());
            pstm.setString(2, pessoaFisica.getCpf());
            pstm.setInt(3, pessoaFisica.getId());

            pstm.execute();
            System.out.println("Contato atualizado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(conn, pstm);
        }
    }

    public void excluir(int id) {
        String sqlFisic = "DELETE FROM pessoafisica WHERE idpessoa = ?";
        String sqlPessoa = "DELETE FROM pessoa WHERE idpessoa = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConectorBD.getConnection();

            pstm = ConectorBD.getPrepared(sqlFisic);
            pstm.setInt(1, id);
            pstm.execute();
            pstm.close();

            pstm = ConectorBD.getPrepared(sqlPessoa);
            pstm.setInt(1, id);
            pstm.execute();
            System.out.println("Pessoa Fisica removida com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(conn, pstm);
        }
    }
}