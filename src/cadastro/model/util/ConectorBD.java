package cadastro.model.util;

import javax.xml.transform.Result;
import java.sql.*;

public class ConectorBD {
    public static String sql;
    //Usuario MySQL
    private static final String USERNAME = "root";
    //Senha MySQL
    private static final String PASSWORD = "admin";
    //URL MySQL
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/loja";

    // Metodo que retorna uma conexao com o BD
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        return connection;
    }

    // Metodo para retornar um PreparedStatement SQL
    public static PreparedStatement getPrepared(String sql) throws Exception {
        Connection conn = ConectorBD.getConnection();
        PreparedStatement pstm = null;

        return conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    }

    // Metodo para retornar um ResultSet relacionado a consulta
    public static ResultSet getSelect(String sql, Object... params) throws Exception {
        PreparedStatement pstm = getPrepared(sql);

        for (int i = 0; i < params.length; i++) {
            pstm.setObject(i + 1, params[i]);
        }
        return pstm.executeQuery();
    }

    // Metodos de fechamento
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection connection, Statement statement) {
        close(statement);
        close(connection);
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        close(resultSet);
        close(connection, statement);
    }

    public static void main(String[] args) throws Exception {
        Connection con = getConnection();
        if(con != null) {
            System.out.println("Conexao obtida com sucesso!");
            con.close();
        }
    }
}
