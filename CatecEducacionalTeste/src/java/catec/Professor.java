package catec; // Declara o pacote onde a classe Professor está localizada.

public class Professor { // Declara a classe pública Professor.
    private int id; // Atributo privado para armazenar o ID do professor.
    private String nome; // Atributo privado para armazenar o nome do professor.
    private String email; // Atributo privado para armazenar o e-mail do professor.
    private String senha; // Atributo privado para armazenar a senha do professor.
    private String areaAtuacao; // Atributo privado para armazenar a área de atuação do professor.
    // Adicione outros atributos // Comentário indicando que outros atributos podem ser adicionados.

    // Construtores
    public Professor() {} // Construtor padrão sem argumentos.

    public Professor(int id, String nome, String email, String senha, String areaAtuacao) { // Construtor com argumentos para inicializar os atributos.
        this.id = id; // Inicializa o ID do professor.
        this.nome = nome; // Inicializa o nome do professor.
        this.email = email; // Inicializa o e-mail do professor.
        this.senha = senha; // Inicializa a senha do professor.
        this.areaAtuacao = areaAtuacao; // Inicializa a área de atuação do professor.
    }

    // Getters e Setters
    public int getId() { return id; } // Método getter para o ID do professor.
    public void setId(int id) { this.id = id; } // Método setter para o ID do professor.
    public String getNome() { return nome; } // Método getter para o nome do professor.
    public void setNome(String nome) { this.nome = nome; } // Método setter para o nome do professor.
    public String getEmail() { return email; } // Método getter para o e-mail do professor.
    public void setEmail(String email) { this.email = email; } // Método setter para o e-mail do professor.
    public String getSenha() { return senha; } // Método getter para a senha do professor.
    public void setSenha(String senha) { this.senha = senha; } // Método setter para a senha do professor.
    public String getAreaAtuacao() { return areaAtuacao; } // Método getter para a área de atuação do professor.
    public void setAreaAtuacao(String areaAtuacao) { this.areaAtuacao = areaAtuacao; } // Método setter para a área de atuação do professor.
}