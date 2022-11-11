public class Atendimento {

    // Variáveis
    private Servico servico;
    private Animal animal;
    private String senha;
    /* falta as variaveis da data !!! */

    // construtor
    public Atendimento(Servico servico, Animal animal, String senha) {
        this.servico = servico;
        this.animal = animal;
        this.senha = senha;
    }

    // get e set
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
