package agenda.model;

public class Contato {
    private int codContato;
    private String nome;
    private Long numero;
    private String dataAniversario;
    private String email;
    private String Descricao;

    public Contato(){
    }

    public Contato(String nome, Long numero, String email){
        this.nome = nome;
        this.numero = numero;
        this.email = email;
    }

    public int getCodContato() {
        return codContato;
    }

    public void setCodContato(int codContato) {
        this.codContato = codContato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getNumero() { return numero; }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getDataAniversario() {
        return dataAniversario;
    }

    public void setDataAniversario(String dataAniversario) {
        this.dataAniversario = dataAniversario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
