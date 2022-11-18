public class ClinicaVet {
    private Fila FilaNormal;
    private Fila FilaPrioritaria;
    private Atendimento[] Atendidos;

    public ClinicaVet(Fila filaNormal, Fila filaPrioritaria, Atendimento[] atendidos) {
        FilaNormal = filaNormal;
        FilaPrioritaria = filaPrioritaria;
        Atendidos = atendidos;
    }

    public void CadConsulta(Animal A){

    }
    public Animal ChamaProximo(){
        return null;
    }
    public Boolean VerificaAnimal(){
        return null;
    }
    public Animal FichaProxAnimal(){
        return null;
    }
    public void RelAnimalFila(){}
    public void RelAnimalFaixa(){}
    public void RelAnimalEspera(){}
    public void RelPermanenciaMedia(){}


}
