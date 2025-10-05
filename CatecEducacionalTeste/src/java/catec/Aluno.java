package catec; // Declara o pacote onde a classe Aluno está localizada.

import java.time.LocalDate; // Importa a classe LocalDate para lidar com datas de nascimento.

public class Aluno { // Declara a classe pública Aluno.
    private int id; // Atributo privado para armazenar o ID do aluno.
    private String nome; // Atributo privado para armazenar o nome do aluno.
    private String email; // Atributo privado para armazenar o e-mail do aluno.
    private String senha; // Atributo privado para armazenar a senha do aluno.
    private LocalDate dataNascimento; // Atributo privado para armazenar a data de nascimento do aluno.
    // Adicione outros atributos // Comentário indicando que outros atributos podem ser adicionados.

    // Construtores
    public Aluno() {} // Construtor padrão sem argumentos.

    public Aluno(String nome, String email, String senha, LocalDate dataNascimento) { // Construtor com argumentos para inicializar os atributos.
        this.nome = nome; // Inicializa o nome do aluno.
        this.email = email; // Inicializa o e-mail do aluno.
        this.senha = senha; // Inicializa a senha do aluno.
        this.dataNascimento = dataNascimento;  // Pode ser null // Inicializa a data de nascimento, que pode ser nula.
    }
    
    // Getters e Setters
    public int getId() { return id; } // Método getter para o ID do aluno.
    public void setId(int id) { this.id = id; } // Método setter para o ID do aluno.
    public String getNome() { return nome; } // Método getter para o nome do aluno.
    public void setNome(String nome) { this.nome = nome; } // Método setter para o nome do aluno.
    public String getEmail() { return email; } // Método getter para o e-mail do aluno.
    public void setEmail(String email) { this.email = email; } // Método setter para o e-mail do aluno.
    public String getSenha() { return senha; } // Método getter para a senha do aluno.
    public void setSenha(String senha) { this.senha = senha; } // Método setter para a senha do aluno.
    public LocalDate getDataNascimento() { return dataNascimento; } // Método getter para a data de nascimento do aluno.
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; } // Método setter para a data de nascimento do aluno.
}
