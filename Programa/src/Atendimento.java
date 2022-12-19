import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Atendimento implements Serializable {

    // Variáveis
    private Animal animal;
    private String senha;
    private Date HoraEntrada;
    private Date HoraConsulta;
    private String NomeServico;
    private boolean Prioritario;


    // construtor
    public Atendimento(Animal animal, String NomeServico, boolean Prioritario) {
        this.animal = animal;
        this.NomeServico = NomeServico;
        this.Prioritario = Prioritario;
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

    public Boolean isPrioritario(){
        return Prioritario;
    }

    public void setHoraConsulta(){
        HoraConsulta = new Date(System.currentTimeMillis());
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getPermanecia(){
        Long permanece;
        if (HoraConsulta == null)
            permanece = (System.currentTimeMillis() - HoraEntrada.getTime());
        else
            permanece = (HoraConsulta.getTime()-HoraEntrada.getTime());

        double minutos = (permanece.doubleValue() / 60000);
        double segundos = minutos%1 *60;
        return ((int)(minutos-minutos%1))+" minutos e " +(segundos - segundos%0.1)+" segundos";
    }
    public double getPermaneciaDouble(){
        Long permanece;
        if (HoraConsulta == null)
            permanece = (System.currentTimeMillis() - HoraEntrada.getTime());
        else
            permanece = (HoraConsulta.getTime()-HoraEntrada.getTime());
        return (permanece.doubleValue() / 60000);
    }
}