public class Servico {
    // Variáveis
    private String nome;
    private String tipo;

    // Construtor
    public Servico(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    // getters e setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
