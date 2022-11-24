import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
  static ClinicaVet clinica;
  static Scanner entrada;

  public static void main(String[] args) throws Exception {
    entrada = new Scanner(System.in);
    clinica = new ClinicaVet();
    boolean sair = false;

    do {
      System.out.println("***************** MENU *****************");
      System.out.println("* O que deseja fazer?                  *");
      System.out.println("* 1. Cadastrar novo animal             *");
      System.out.println("* 2. Agendar Atendimento               *");
      System.out.println("* 3. Realizar Atendimento              *");
      System.out.println("* 4. Mostrar proximo Atendimento       *");
      System.out.println("* 5. Consultas de caráter estatístico  *");
      System.out.println("* 0. Encerrar programa                 *");
      System.out.println("****************************************");
      int op = entrada.nextInt();
      entrada.nextLine();

      switch (op) {
        case 1:
          // ANIMAL ------------------------------------------------------
          System.out.println("Informe os dados do animal");
          System.out.print("Apelido: ");
          String apelido = entrada.nextLine();

          System.out.println("Tipo do animal: ");
          System.out.println("1) canino ");
          System.out.println("2) felino ");
          System.out.println("3) roedor ");

          int tipo =  entrada.nextInt();
          entrada.nextLine();
          String tipoAnimal ="";
          switch (tipo){
            case  1:
              tipoAnimal = "canino";
              break;
            case  2:
              tipoAnimal = "felino";
              break;
            case  3:
              tipoAnimal = "roedor";
              break;
            default:
              throw new Exception("tipo de animal inválido");
          }

          System.out.print("Dono: ");
          String dono = entrada.nextLine();

          System.out.print("Data de nascimento (dd/mm/aaaa): ");
          String data = entrada.nextLine();

          Date dataNascimento = null;
          try {
            dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(data);
          } catch (ParseException e) {
            e.printStackTrace();
          }
          // -------------------------------------------------------------

          Animal animal = new Animal(apelido, tipoAnimal, dataNascimento, dono);
          System.out.println(clinica.cadastrarAnimal(animal));
          break;
        case 2:

          System.out.println("Selecione o serviço");
          System.out.println("1. Vacinar Animal");
          System.out.println("2. Castrar Animal");
          System.out.println("3. Check-up");
          System.out.println("4. Atendimento de Urgência/Emergência");
          System.out.println("5. Voltar");
          int sel = entrada.nextInt();

          switch (sel){
            case 1:
              clinica.cadConsulta(new Atendimento(selecionarAnimal(),"Vacinação", false));
              break;
            case 2:
              clinica.cadConsulta(new Atendimento(selecionarAnimal(),"Castração", false));
              break;
            case 3:
              clinica.cadConsulta(new Atendimento(selecionarAnimal(),"Check-up",false));
              break;
            case 4:
              clinica.cadConsulta(new Atendimento(selecionarAnimal(),"Urgência", true));
              break;
            case 5:
              break;
          }
          System.out.println("Cadastro bem sucedido!");
          break;
        case 3:
          System.out.println("Por favor, compareça ao consultório:");
          exibirAtendimento(clinica.chamaProximoAtendimento());
          System.out.println("Atendimento realizado com sucesso");
          break;
        case 4:
          exibirAtendimento(clinica.verProximoAtendimento());
          break;
        /*case 5:
          System.out.println("***************************************************");
          System.out.println("* Selecione a opção desejada                      *");
          System.out.println("* 1. quantidade de animais em cada fila           *");
          System.out.println("* 2. quantidade total de animais por faixa etária *");
          System.out.println("* 3. quantidade total de animais em espera        *");
          System.out.println("* 4. tempo médio de permanência na fila           *");
          System.out.println("***************************************************");
          int op2 = entrada.nextInt();
          switch (op2){
            case 1:
              System.out.println("Fila Prioritária: " + clinica.relAnimalFila1());
              System.out.println("Fila Normal: " + clinica.relAnimalFila2());
            
              break;
            case 2:
              System.out.println("Classificação por faixa etária");
              clinica.relAnimalFaixa();
              
              break;
            case 3:
              break;
            case 4:
              break;
              
          }
          break;*/
        case 0:
          System.out.println("O programa será encerrado. Até logo!");
          sair = true;
          break;
      }
    } while (!sair);

    entrada.close();
  }
  public static Animal selecionarAnimal(){
    List<Animal> animais = clinica.getAnimais();
    System.out.println("Selecione o Animal que para realizar o antendimento:");
    for(int i=0;i<animais.size();i++){
      System.out.println(i+") "+animais.get(i).getApelido()+" | "+animais.get(i).getDono());
    }
    int animalID = entrada.nextInt();
    return animais.get(animalID);
  }
  public static void exibirAtendimento(Atendimento atendimento){
    if (atendimento == null) {
      System.out.println("Não existem atendidos em espera!");
    } else {
      System.out.println("Próximo a ser atendido animal: " + atendimento.getAnimal().getApelido() + " | Dono: " + atendimento.getAnimal().getDono());
    }
  }

}
