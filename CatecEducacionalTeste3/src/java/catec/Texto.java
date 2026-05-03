package catec;

public class Texto {
    private int id;
    private String conteudo;
    private int authorId;
    private String authorType; // "professor" or "aluno"
    private String authorNome;

    public Texto() {}

    public Texto(int id, String conteudo, int authorId, String authorType, String authorNome) {
        this.id = id;
        this.conteudo = conteudo;
        this.authorId = authorId;
        this.authorType = authorType;
        this.authorNome = authorNome;
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
}