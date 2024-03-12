import java.util.List;

public class Main {
    public static void main(String[] args) {
        AlunoDAO alunoDAO = new AlunoDAO();

        // Adicionar Alunos
        alunoDAO.adicionarAluno(new Aluno("Alice", 20));
        alunoDAO.adicionarAluno(new Aluno("Bob", 22));

        // Listar Alunos
        System.out.println("Lista de Alunos:");
        List<Aluno> alunos = alunoDAO.listarAlunos();
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }

        // Buscar Aluno por ID
        System.out.println("\nBuscar Aluno por ID:");
        Aluno alunoEncontrado = alunoDAO.buscarAluno(1);
        System.out.println(alunoEncontrado);

        // Atualizar Aluno
        System.out.println("\nAtualizar Aluno:");
        Aluno alunoAtualizado = new Aluno();
        alunoAtualizado.setId(1);
        alunoAtualizado.setNome("Charlie");
        alunoAtualizado.setIdade(25);
        alunoDAO.atualizarAluno(alunoAtualizado);
        alunos = alunoDAO.listarAlunos();
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }

        // Deletar Aluno
        System.out.println("\nDeletar Aluno:");
        alunoDAO.deletarAluno(1);
        alunos = alunoDAO.listarAlunos();
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }
}
