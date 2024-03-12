import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private final String url = "jdbc:sqlite:alunos.db";

    public AlunoDAO() {
        // Cria a tabela se não existir
        String sql = "CREATE TABLE IF NOT EXISTS alunos (id INTEGER PRIMARY KEY, nome TEXT, idade INTEGER);";
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adicionarAluno(Aluno aluno) {
        String sql = "INSERT INTO alunos (nome, idade) VALUES (?, ?);";
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setInt(2, aluno.getIdade());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                aluno.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> listarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM alunos;";
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(resultSet.getInt("id"));
                aluno.setNome(resultSet.getString("nome"));
                aluno.setIdade(resultSet.getInt("idade"));
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    public Aluno buscarAluno(int id) {
        String sql = "SELECT * FROM alunos WHERE id = ?;";
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(resultSet.getInt("id"));
                aluno.setNome(resultSet.getString("nome"));
                aluno.setIdade(resultSet.getInt("idade"));
                return aluno;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void atualizarAluno(Aluno alunoAtualizado) {
        String sql = "UPDATE alunos SET nome = ?, idade = ? WHERE id = ?;";
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, alunoAtualizado.getNome());
            preparedStatement.setInt(2, alunoAtualizado.getIdade());
            preparedStatement.setInt(3, alunoAtualizado.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarAluno(int id) {
        String sql = "DELETE FROM alunos WHERE id = ?;";
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
