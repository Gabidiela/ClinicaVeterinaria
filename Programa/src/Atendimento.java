import java.util.Date;

public class Atendimento {

    // Variáveis
    private Servico servico;
    private Animal animal;
    private String senha;
    private Date HoraEntrada;
    private Date HoraConsulta;

    // construtor
    public Atendimento(Servico servico, Animal animal, String senha) {
        this.servico = servico;
        this.animal = animal;
        this.senha = senha;
        HoraEntrada = new Date(System.currentTimeMillis());
    }

    // get e set
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setHoraConsulta(){
        HoraConsulta = new Date(System.currentTimeMillis());
    }

}
