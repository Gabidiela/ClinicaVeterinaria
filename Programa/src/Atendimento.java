import java.util.Date;

public class Atendimento {

    // Variáveis
    private Animal animal;
    private String senha;
    private Date HoraEntrada;
    private Date HoraConsulta;
    private String NomeServico;
    private boolean TipoServico;


    // construtor
    public Atendimento(Animal animal, String senha, String NomeServico, boolean TipoServico) {
        this.animal = animal;
        this.senha = senha;
        this.NomeServico = NomeServico;
        this.TipoServico = TipoServico;
        HoraEntrada = new Date(System.currentTimeMillis());
    }

    // get e set
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeServico(){
        return NomeServico;
    }

    public Boolean getTipoServico(){
        return TipoServico;
    }

    public void setHoraConsulta(){
        HoraConsulta = new Date(System.currentTimeMillis());
    }

}
