
/*PARTE 1 - importa várias classes do pacote java.sql, que são necessárias para trabalhar com banco de dados */
import java.sql.Connection; //funcionalidades para conexão
import java.sql.DriverManager;//funcionalidades para manipulação de bancos de dados.
import java.sql.ResultSet;//funcionalidades para manipulação de bancos de dados.
import java.sql.Statement;//funcionalidades para consulta 
// PARTE 2 - Classe que representa o usuario do banco de dados 

public class User {
    public Connection conectarBD() { // Este método é responsável por estabelecer uma conexão com o banco de dados
                                     // MySQL
        Connection conn = null;
        try {
            Class.forName("com.mysql.Driver.Manager").newInstance();// Carrega o driver do MySQL
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";/*
                                                                                * Define a URL de conexão com o
                                                                                * banco de dados, incluindo o nome de
                                                                                * usuário e senha.
                                                                                */
            conn = DriverManager.getConnection(url);/*
                                                     * para obter uma conexão com o banco de dados.
                                                     * A conexão é retornada como resultado.
                                                     */
        } catch (Exception e) {
        } // captura exceções genéricas
        return conn;
    }/* A conexão é retornada como resultado. */

    public String nome = ""; // armazenar o nome do usuário
    public boolean result = false;// usada para rastrear se a verificação do usuário foi bem-sucedida

    public boolean verificarUsuario(String login, String senha) {/*
                                                                  * Este método é usado para verificar as
                                                                  * credenciais de um usuário no banco de dados.
                                                                  */
        String sql = "";
        Connection conn = conectarBD();// obter uma conexão com o banco de dados
        sql += "select nome from usuarios ";/*
                                             * 3 - Constrói uma consulta SQL para procurar um usuário
                                             * com o nome de usuário e senha especificados.
                                             */
        sql += "where login = " + "'" + login + "'";// 3
        sql += " and senha = " + "'" + senha + "'";// 3
        try {
            Statement st = conn.createStatement();
            /*
             * 4 - Executa a consulta SQL no banco de dados e, se um registro correspondente
             * for encontrado,
             * ele define result como verdadeiro e armazena o nome do usuário na variável
             * nome.
             */
            ResultSet rs = st.executeQuery(sql); // 4
            if (rs.next()) {// 4
                result = true;// 4
                nome = rs.getString("nome");// 4
            }

        } catch (Exception e) {
        } // captura exceções genéricas
        return result;
    }
}