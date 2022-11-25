import java.util.Date;

public class Atendimento {
    // Variáveis
    private Animal animal;
    private String senha;
    private Date horaEntrada;
    private Date horaConsulta; // *** Faltando atualizar a variável de consulta. Na hora que chamar o animal, atualiza ela
    private String nomeServico;
    private boolean prioritario;

    // construtor
    public Atendimento(Animal animal, String NomeServico, boolean Prioritario) {
        this.animal = animal;
        this.nomeServico = NomeServico;
        this.prioritario = Prioritario;
        horaEntrada = new Date(System.currentTimeMillis());
    }

    // get e set
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeServico(){
        return nomeServico;
    }

    public Boolean isPrioritario(){
        return prioritario;
    }

    public void setHoraConsulta(){
        horaConsulta = new Date(System.currentTimeMillis());
    }

    public Animal getAnimal() {
        return animal;
    }

    public long getTempoEspera() {
        long tempoEspera = horaConsulta.getTime() - horaEntrada.getTime();
        return tempoEspera; // *** Falta converter pra s,m,h !!! NÃO É AQUI QUE FAZ
    }
}
