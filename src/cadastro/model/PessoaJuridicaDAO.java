package cadastro.model;

import cadastro.model.util.ConectorBD;
import cadastrodb.model.PessoaJuridica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {
    public int id;

    public PessoaJuridica getPessoa(PessoaJuridica pessoaJuridica, int id) {
        String sql = "SELECT pessoa.*, pessoajuridica.* \n" +
                    "FROM pessoajuridica \n" +
                    "INNER JOIN pessoa ON pessoajuridica.idpessoa = pessoa.idpessoa \n" +
                    "WHERE pessoajuridica.idpessoa = ?";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConectorBD.getConnection();
            pstm = ConectorBD.getPrepared(sql);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();

            if (rset.next()) {
                pessoaJuridica.setId(rset.getInt("idpessoa"));
                pessoaJuridica.setNome(rset.getString("nome"));
                pessoaJuridica.setLogradouro(rset.getString("logradouro"));
                pessoaJuridica.setCidade(rset.getString("cidade"));
                pessoaJuridica.setEstado(rset.getString("estado"));
                pessoaJuridica.setTelefone(rset.getString("telefone"));
                pessoaJuridica.setEmail(rset.getString("email"));
                pessoaJuridica.setCnpj(rset.getString("cnpj"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(conn, pstm);
        }
        return pessoaJuridica;
    }

    public List<PessoaJuridica> getPessoas() {
        String sql = "SELECT pessoa.*, pessoajuridica.*\n" +
                "FROM pessoajuridica\n" +
                "INNER JOIN pessoa ON pessoajuridica.idpessoa = pessoa.idpessoa;";

        List<PessoaJuridica> pessoas = new ArrayList<PessoaJuridica>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConectorBD.getConnection();
            pstm = ConectorBD.getPrepared(sql);
            rset = ConectorBD.getSelect(sql);

            while (rset.next()) {
                PessoaJuridica pessoaJuridica = new PessoaJuridica();

                pessoaJuridica.setId(rset.getInt("idpessoa"));
                pessoaJuridica.setNome(rset.getString("nome"));
                pessoaJuridica.setLogradouro(rset.getString("logradouro"));
                pessoaJuridica.setCidade(rset.getString("cidade"));
                pessoaJuridica.setEstado(rset.getString("estado"));
                pessoaJuridica.setTelefone(rset.getString("telefone"));
                pessoaJuridica.setEmail(rset.getString("email"));
                pessoaJuridica.setCnpj(rset.getString("cnpj"));

                pessoas.add(pessoaJuridica);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(conn, pstm, rset);
        }
        return pessoas;
    }

    public void incluir(PessoaJuridica pessoaJuridica) {
        String sql = "INSERT INTO pessoa(nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?);";

        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = ConectorBD.getConnection();
            pstm = ConectorBD.getPrepared(sql);

            pstm.setString(1, pessoaJuridica.getNome());
            pstm.setString(2, pessoaJuridica.getLogradouro());
            pstm.setString(3, pessoaJuridica.getCidade());
            pstm.setString(4, pessoaJuridica.getEstado());
            pstm.setString(5, pessoaJuridica.getTelefone());
            pstm.setString(6, pessoaJuridica.getEmail());

            pstm.execute();

            ResultSet generatyedKeys = pstm.getGeneratedKeys();
            int idPessoa = 0;
            if (generatyedKeys.next()) {
                idPessoa = generatyedKeys.getInt(1);
            }

            String sqlJuridc = "INSERT INTO pessoajuridica (idpessoa, cnpj) VALUES (?, ?)";
            pstm = ConectorBD.getPrepared(sqlJuridc);

            pstm.setInt(1, idPessoa);
            pstm.setString(2, pessoaJuridica.getCnpj());

            pstm.execute();

            System.out.println("Contato salvo com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(conn, pstm);
        }
    }

    public void alterar(PessoaJuridica pessoaJuridica) {
        String sql = "UPDATE pessoa \n" +
                    "SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? \n" +
                    "WHERE idpessoa = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConectorBD.getConnection();
            pstm = ConectorBD.getPrepared(sql);

            pstm.setString(1, pessoaJuridica.getNome());
            pstm.setString(2, pessoaJuridica.getLogradouro());
            pstm.setString(3, pessoaJuridica.getCidade());
            pstm.setString(4, pessoaJuridica.getEstado());
            pstm.setString(5, pessoaJuridica.getTelefone());
            pstm.setString(6, pessoaJuridica.getEmail());

            pstm.setInt(7, pessoaJuridica.getId());

            pstm.execute();

            String sqlJuridic = "UPDATE pessoajuridica \n" +
                    "SET idpessoa = ?, cnpj = ? \n" +
                    "WHERE idpessoa = ?";

            pstm = ConectorBD.getPrepared(sqlJuridic);

            pstm.setInt(1, pessoaJuridica.getId());
            pstm.setString(2, pessoaJuridica.getCnpj());
            pstm.setInt(3, pessoaJuridica.getId());

            pstm.execute();
            System.out.println("Contato atualizado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(conn, pstm);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM pessoa \n" +
                "WHERE idpessoa = ?";
        String sqlJuridic = "DELETE FROM pessoajuridica \n" +
                "WHERE idpessoa = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConectorBD.getConnection();
            pstm = ConectorBD.getPrepared(sqlJuridic);

            pstm.setInt(1, id);
            pstm.execute();

            pstm = ConectorBD.getPrepared(sql);

            pstm.setInt(1, id);
            pstm.execute();
            System.out.println("Usuario removido com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(conn, pstm);
        }
    }
}