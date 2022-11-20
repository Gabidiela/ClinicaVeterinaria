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
/*
  public int relAnimalFila1() {
    int qtdPrioritaria = filaPrioritaria.size() + 1;
      return qtdPrioritaria;
  }
  public int relAnimalFila2() {
    int qtdNormal = filaNormal.size() + 1;
      return qtdNormal;
  }
  
  public String relAnimalFaixa() {
    Date agora = new Date();
    int crianca=0, adulto=0, idoso=0; 
    
    it = atendidos.iterator();
    while (it.hasNext()){ 
      int anoAtual = agora.getYear();
      int anoNascimento = atendidos.peek().getAnimal().getDataNascimento().getYear();
      int idade = anoAtual - anoNascimento; 
  
      int mesAtual = agora.getMonth();
      int mesNascimento = atendidos.peek().getAnimal().getDataNascimento().getMonth();
      if (mesAtual < mesNascimento){
        idade--;
      } else {
        int diaAtual = agora.getDay();
        int diaNascimento = atendidos.peek().getAnimal().getDataNascimento().getDay();
        if (mesAtual == mesNascimento && diaAtual < diaNascimento){
          idade--;
        }
      }
      if (idade < 2){
        crianca++;
      } else if(idade >=2 && idade <5) {
        adulto++;
      } else {
        idoso++;
      }
    }
    return String.format("a) até 2 anos de idade %d%n", crianca);
           String.format("b) entre 2 e 5 anos de idade %d%n", adulto); 
           String.format("c) mais de 5 anos de idade %d%n", idoso);
  }
   */

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
