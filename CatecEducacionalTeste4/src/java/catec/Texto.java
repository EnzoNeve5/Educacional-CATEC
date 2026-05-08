package catec;

public class Texto {
    private int id;
    private String conteudo;
    private int authorId;
    private String authorType; // "professor" or "aluno"
    private String authorNome;
    private String authorInstituicao;
    private String authorEmail;
    private String authorDataNascimento;
    private String authorAreaAtuacao;

    public Texto() {}

    public Texto(int id, String conteudo, int authorId, String authorType, String authorNome,
                 String authorInstituicao, String authorEmail, String authorDataNascimento,
                 String authorAreaAtuacao) {
        this.id = id;
        this.conteudo = conteudo;
        this.authorId = authorId;
        this.authorType = authorType;
        this.authorNome = authorNome;
        this.authorInstituicao = authorInstituicao;
        this.authorEmail = authorEmail;
        this.authorDataNascimento = authorDataNascimento;
        this.authorAreaAtuacao = authorAreaAtuacao;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }

    public int getAuthorId() { return authorId; }
    public void setAuthorId(int authorId) { this.authorId = authorId; }

    public String getAuthorType() { return authorType; }
    public void setAuthorType(String authorType) { this.authorType = authorType; }

    public String getAuthorNome() { return authorNome; }
    public void setAuthorNome(String authorNome) { this.authorNome = authorNome; }

    public String getAuthorInstituicao() { return authorInstituicao; }
    public void setAuthorInstituicao(String authorInstituicao) { this.authorInstituicao = authorInstituicao; }

    public String getAuthorEmail() { return authorEmail; }
    public void setAuthorEmail(String authorEmail) { this.authorEmail = authorEmail; }

    public String getAuthorDataNascimento() { return authorDataNascimento; }
    public void setAuthorDataNascimento(String authorDataNascimento) { this.authorDataNascimento = authorDataNascimento; }

    public String getAuthorAreaAtuacao() { return authorAreaAtuacao; }
    public void setAuthorAreaAtuacao(String authorAreaAtuacao) { this.authorAreaAtuacao = authorAreaAtuacao; }
}