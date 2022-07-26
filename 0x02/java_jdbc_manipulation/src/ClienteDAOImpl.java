
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ClienteDAOImpl implements ClienteDAO{

    @Override
    public Connection connect(String urlConexao) {
        Connection connection = null;
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(urlConexao);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    @Override
    public void createTable(String urlConexao) {
        try{
            Connection connection = connect(urlConexao);
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE Cliente (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR(50), idade INTEGER, cpf VARCHAR(50), rg VARCHAR(50))";
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void insert(String url_conexao, Cliente cliente) {
        try{
            Connection connection = connect(url_conexao);
            Statement statement = connection.createStatement();

            String values = String.format("%d, '%s', %d, '%s', '%s'", cliente.getId(), cliente.getNome(), cliente.getIdade(),
                    cliente.getCpf(), cliente.getRg());
            String sql = "INSERT INTO Cliente (id, nome, idade, cpf, rg) values(" + values + ")";

            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void selectAll(String urlConexao) {
        try{
            Connection connection = connect(urlConexao);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Cliente";

            statement.executeQuery(sql);
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(String urlConexao, int id, String name, Integer idade) {
        try{
            Connection connection = connect(urlConexao);
            Statement statement = connection.createStatement();
            String sql = "UPDATE Cliente SET nome = '" + name + "', idade = " + idade + " WHERE id = " + id;

            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(String urlConexao, int id) {
        try{
            Connection connection = connect(urlConexao);
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM Cliente WHERE id=" + id;

            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
