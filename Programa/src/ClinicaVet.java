import java.util.Queue;
import java.util.LinkedList;
import java.util.Date;
//import java.util.Iterator;

public class ClinicaVet {
  private Queue<Atendimento> filaPrioritaria;
  private Queue<Atendimento> filaNormal;
  private Queue<Atendimento> atendidos;
  //private Iterator <Atendimento> it;

  public ClinicaVet() {
    filaNormal = new LinkedList<Atendimento>();
    filaPrioritaria = new LinkedList<Atendimento>();
    atendidos = new LinkedList<Atendimento>();
  }

  public void cadConsulta (Atendimento atendimento, String prioridade) {
    if (prioridade == "Prioritario") {
      filaPrioritaria.add(atendimento);
    } else if (prioridade == "Normal"){
      filaNormal.add(atendimento);
    }
  }

  public Animal realizarAtendimento() {
    Animal animalAtendido = null;
    
    if (!filaPrioritaria.isEmpty()) {
      animalAtendido = filaPrioritaria.peek().getAnimal();
      atendidos.add(filaPrioritaria.poll());
    } else if (!filaNormal.isEmpty()){
      animalAtendido = filaNormal.peek().getAnimal();
      atendidos.add(filaNormal.poll());
    }

    return animalAtendido;
  }

  public Animal mostrarProximo() {
    Animal proximoAnimal = null;
    
    if (!filaPrioritaria.isEmpty()) {
      proximoAnimal = filaPrioritaria.peek().getAnimal();
    } else if (!filaNormal.isEmpty()){
      proximoAnimal = filaNormal.peek().getAnimal();
    }

    return proximoAnimal;
  }

  public Boolean verificaAnimal() {
    return null;
  }

  public Animal fichaProxAnimal() {
    return null;
  }
  public int relAnimalFila1() {
    int qtdPrioritaria = filaPrioritaria.size();
    return qtdPrioritaria;
  }
  public int relAnimalFila2() {
    int qtdNormal = filaNormal.size();
    return qtdNormal;
  }
  public String relAnimalFaixaN() {
    Date agora = new Date();
    int crianca=0, adulto=0, idoso=0; 

    it = filaNormal.iterator();
    while (it.hasNext()){ 
      int anoAtual = agora.getYear();
      int anoNascimento = filaNormal.peek().getAnimal().getDataNascimento().getYear();
      int idade = anoAtual - anoNascimento; 

      int mesAtual = agora.getMonth();
      int mesNascimento = filaNormal.peek().getAnimal().getDataNascimento().getMonth();
      if (mesAtual < mesNascimento){
        idade--;
      } else {
        int diaAtual = agora.getDay();
        int diaNascimento = filaNormal.peek().getAnimal().getDataNascimento().getDay();
        if (mesAtual == mesNascimento && diaAtual < diaNascimento){
          idade--;
        }
      }
      if (idade <= 2){
        crianca++;
      } else if(idade >2 && idade <=5) {
        adulto++;
      } else {
        idoso++;
      }
      it.next();
    }
    return String.format("---------- Fila Normal ----------%n") +
        String.format("a) até 2 anos de idade %d%n", crianca) +
        String.format("b) entre 2 e 5 anos de idade %d%n", adulto) + 
        String.format("c) mais de 5 anos de idade %d%n", idoso) +
        String.format("-------------------------------");
        
  }
  public String relAnimalFaixaP() {
    Date agora = new Date();
    int crianca=0, adulto=0, idoso=0; 

    it = filaPrioritaria.iterator();
    while (it.hasNext()){ 
      int anoAtual = agora.getYear();
      int anoNascimento = filaPrioritaria.peek().getAnimal().getDataNascimento().getYear();
      int idade = anoAtual - anoNascimento; 

      int mesAtual = agora.getMonth();
      int mesNascimento = filaPrioritaria.peek().getAnimal().getDataNascimento().getMonth();
      if (mesAtual < mesNascimento){
        idade--;
      } else {
        int diaAtual = agora.getDay();
        int diaNascimento = filaPrioritaria.peek().getAnimal().getDataNascimento().getDay();
        if (mesAtual == mesNascimento && diaAtual < diaNascimento){
          idade--;
        }
      }
      if (idade <= 2){
        crianca++;
      } else if(idade >2 && idade <=5) {
        adulto++;
      } else {
        idoso++;
      }
      it.next();
    }
    return String.format("------ Fila Prioritária ------%n") +
      String.format("a) até 2 anos de idade %d%n", crianca) +
        String.format("b) entre 2 e 5 anos de idade %d%n", adulto) + 
        String.format("c) mais de 5 anos de idade %d%n", idoso) +
        String.format("-------------------------------");
  }
  public String relAnimalEsperaN() {
    int vacinacaoN=0, castracaoN=0, checkUpN=0;
    it = filaNormal.iterator();

    while (it.hasNext()){
      if(filaNormal.peek().getNomeServico().equalsIgnoreCase("Vacinação")){
        vacinacaoN++;
      } else if(filaNormal.peek().getNomeServico().equalsIgnoreCase("Castração")){
        castracaoN++;
      }else if(filaNormal.peek().getNomeServico().equalsIgnoreCase("Check-up")){
        checkUpN++;
      }
      it.next();
    }

      return String.format("----- Fila Normal -----%n") +
             String.format("a) Vacinação: %d%n", vacinacaoN) +
             String.format("b) Castração: %d%n", castracaoN) + 
             String.format("c) Check-Up: %d%n", checkUpN) +
             String.format("-----------------------");
  }
   public String relAnimalEsperaP() {
      int vacinacaoP=0, castracaoP=0, checkUpP=0;
      it = filaPrioritaria.iterator();

      while(it.hasNext()){
        if(filaPrioritaria.peek().getNomeServico().equalsIgnoreCase("Vacinação")){
          vacinacaoP++;
        } else if(filaPrioritaria.peek().getNomeServico().equalsIgnoreCase("Castração")){
          castracaoP++;
        }else if(filaPrioritaria.peek().getNomeServico().equalsIgnoreCase("Check-up")){
          checkUpP++;
        }
        it.next();
      }
      return String.format("--- Fila Prioritária ---%n") +
             String.format("a) Vacinação: %d%n", vacinacaoP) +
             String.format("b) Castração: %d%n", castracaoP) + 
             String.format("c) Check-Up: %d%n", checkUpP) +
             String.format("------------------------");
    }
  
  public void relAnimalEspera() {
    
  }

  public void relPermanenciaMedia() {
  }

  public boolean animalJaExiste(Animal animal) {
    boolean retorno = false;

    Queue<Atendimento> filaPrioritariaReserva = filaPrioritaria;
    Queue<Atendimento> filaNormalReserva = filaNormal;

    while (!filaPrioritariaReserva.isEmpty()) {
      Animal animalTemp = filaPrioritariaReserva.poll().getAnimal();

      if (animalTemp.getApelido().equals(animal.getApelido()) && animalTemp.getDono().equals(animal.getDono())) {
        retorno = true;
        break;
      }
    }

    if (!retorno) {
      while (!filaNormalReserva.isEmpty()) {
        Animal animalTemp = filaNormalReserva.poll().getAnimal();

        if (animalTemp.getApelido().equals(animal.getApelido()) && animalTemp.getDono().equals(animal.getDono())) {
          retorno = true;
          break;
        }
      }
    }
  
    return retorno;
  }
}
