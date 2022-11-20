import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
  static ClinicaVet clinica;
  static Scanner entrada;

  public static void main(String[] args) {
    entrada = new Scanner(System.in);
    clinica = new ClinicaVet();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    boolean sair = false;

    do {
      System.out.println("***************** MENU *****************");
      System.out.println("* O que deseja fazer?                  *");
      System.out.println("* 1. Cadastrar novo animal             *");
      System.out.println("* 2. Realizar Atendimento              *");
      System.out.println("* 3. Mostrar proximo Atendimento       *");
      System.out.println("* 4. Consultas de caráter estatístico  *");
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

          System.out.print("Tipo do animal (canino, felino ou roedor): ");
         
          String tipoAnimal = entrada.nextLine();

          System.out.print("Dono: ");
          String dono = entrada.nextLine();

          System.out.print("Data de nascimento (dd/mm/aaaa): ");
          String data = entrada.nextLine();

          Date dataNascimento = null;
          try {
            dataNascimento = sdf.parse(data);
          } catch (ParseException e) {
            e.printStackTrace();
          }
          // -------------------------------------------------------------

          Animal animal = new Animal(apelido, tipoAnimal, dataNascimento, dono);

          // SERVIÇO ------------------------------------------
          System.out.println("Selecione o serviço");
          System.out.println("1. Vacinação");
          System.out.println("2. Castração");
          System.out.println("3. Check-up");
          int numeroServico = entrada.nextInt();
          entrada.nextLine();

          System.out.print("O atendimento é urgente? (S/N): ");
          String atendimentoUrgente = entrada.nextLine();
          entrada.nextLine();
          // --------------------------------------------------

          // FILA ----------------------------------------------------------------------------
          if (atendimentoUrgente.equalsIgnoreCase("S")) {
            clinica.cadConsulta(new Atendimento(animal, numeroServico, true), "Prioritario");
          } else {
            clinica.cadConsulta(new Atendimento(animal, numeroServico, false), "Normal");
          }
          // ---------------------------------------------------------------------------------

          System.out.println("Cadastro bem sucedido!");

          break;
        case 2:
          Animal animalAtendido = clinica.realizarAtendimento();

          if (animalAtendido == null) {
            System.out.println("A fila está vázia!");
          } else {
            System.out.println("Animal atendido: " + animalAtendido.getApelido() + " | Dono: " + animalAtendido.getDono());
          }

          break;
        case 3:
          Animal proximoAnimal = clinica.mostrarProximo();

          if (proximoAnimal == null) {
            System.out.println("A fila está vázia!");
          } else {
            System.out.println("Próxmo animal: " + proximoAnimal.getApelido() + " | Dono: " + proximoAnimal.getDono());
          }
          
          break;
        /*case 4:
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
}
