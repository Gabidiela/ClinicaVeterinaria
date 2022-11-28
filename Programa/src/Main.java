import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
  static ClinicaVet clinica;
  static Scanner entrada;
  static final int CADASTRAR_ANIMAL=1;
  static final int AGENDAR_ATENDIMENTO=2;
  static final int REALIZAR_ATENDIMENTO=3;
  static final int CONSULTAR_ATENDIMENTO=4;
  static final int CONSULTAR_ATENDIMENTO_FILA=5;
  static final int RELATORIOS=6;
  static final int SAIR=0;

  public static Animal selecionarAnimal() {
    List<Animal> animais = clinica.getAnimais();
    System.out.println("Selecione o Animal que para realizar o antendimento:");
    for (int i = 0; i < animais.size(); i++) {
      System.out.println(i + ") " + animais.get(i).getApelido() + " | " + animais.get(i).getDono());
    }
    int animalID = entrada.nextInt();
    return animais.get(animalID);
  }

  public static boolean naoPossuiAnimal(){
    if(!clinica.temAnimais()){
      System.out.println("Nenhum animal cadastrado, por favor cadastre um animal para prosseguir");
      return true;
    }
    return false;
  }

  public static boolean naoPossuiAtendimento(){
    if(clinica.getProximoAtendimento() == null){
      System.out.println("Não há animais em filas de espera!");
      return true;
    }
    return false;
  }

  public static void main(String[] args) throws Exception {
    entrada = new Scanner(System.in);
    clinica = new ClinicaVet();
    boolean sair = false;

    do {
      try {
        System.out.println("********************* MENU **********************");
        System.out.println("* O que deseja fazer?                           *");
        System.out.println("* 1. Cadastrar novo animal                      *");
        if(clinica.temAnimais()){
          System.out.println("* 2. Agendar Atendimento                        *");
          System.out.println("* 3. Realizar Atendimento                       *");
          System.out.println("* 4. Consultar proximo Atendimento              *");
          System.out.println("* 5. Consultar proximo Atendimento por Fila     *");
          System.out.println("* 6. Consultas de caráter estatístico           *");
        }
      System.out.println("* 0. Encerrar programa                          *");
      System.out.println("*************************************************");
      int op = entrada.nextInt();
      entrada.nextLine();

      switch (op) {
        case CADASTRAR_ANIMAL:
          // ANIMAL ------------------------------------------------------
          System.out.println("Informe os dados do animal");
          System.out.print("Apelido: ");
          String apelido = entrada.nextLine();

          String tipoAnimal = null;
          do {
            System.out.println("Tipo do animal: ");
            System.out.println("1) canino ");
            System.out.println("2) felino ");
            System.out.println("3) roedor ");

            int tipo = entrada.nextInt();
            entrada.nextLine();

            switch (tipo) {
              case 1:
                tipoAnimal = "canino";
                break;
              case 2:
                tipoAnimal = "felino";
                break;
              case 3:
                tipoAnimal = "roedor";
                break;
              default:
                System.out.println("Opção invalida, insira novamente");
            }
          }while (tipoAnimal == null);
          System.out.print("Dono: ");
          String dono = entrada.nextLine();

          Date dataNascimento = null;
          do {
            System.out.print("Data de nascimento (dd/mm/aaaa): ");
            String data = entrada.nextLine();

            try {
              dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            } catch (ParseException e) {
              System.out.println("Data Inválida! Insira a data corretamente");
            }
          }while (dataNascimento == null);

          // -------------------------------------------------------------
          Animal animal = new Animal(apelido, tipoAnimal, dataNascimento, dono);
          System.out.println(clinica.cadastrarAnimal(animal));
          break;
        case AGENDAR_ATENDIMENTO:
          if(!clinica.temAnimais()){
            System.out.println("Nenhum animal cadastrado, por favor cadastre um animal para prosseguir");
            break;
          }
          System.out.println("Selecione o serviço");
          System.out.println("1. Vacinar Animal");
          System.out.println("2. Castrar Animal");
          System.out.println("3. Check-up");
          System.out.println("4. Atendimento de Urgência/Emergência");
          System.out.println("5. Voltar");
          int sel = entrada.nextInt();

          switch (sel) {
            case 1:
              clinica.cadConsulta(new Atendimento(selecionarAnimal(), "Vacinação", false));
              break;
            case 2:
              clinica.cadConsulta(new Atendimento(selecionarAnimal(), "Castração", false));
              break;
            case 3:
              clinica.cadConsulta(new Atendimento(selecionarAnimal(), "Check-up", false));
              break;
            case 4:
              clinica.cadConsulta(new Atendimento(selecionarAnimal(), "Urgência", true));
              break;
            case 5:
              break;
          }
          System.out.println("Cadastro bem sucedido!");
          break;
        case REALIZAR_ATENDIMENTO:
          if(naoPossuiAnimal())
            break;

          Atendimento atendimento = clinica.chamaProximoAtendimento();
          if (atendimento == null) {
            System.out.println("Não existem animais em espera!");
          } else {
            System.out.println("Por favor, compareça ao consultório o próximo a ser atendido:");
            System.out.println("Senha: " + atendimento.getSenha());
            System.out.println("animal: " + atendimento.getAnimal().getApelido());
            System.out.println("Dono: " + atendimento.getAnimal().getDono());
            System.out.println("Tempo de permanência na fila: " + atendimento.getPermanecia()  + " minutos");
            System.out.println("Atendimento realizado com sucesso");
          }
          break;
        case CONSULTAR_ATENDIMENTO:
          if(naoPossuiAnimal() || naoPossuiAtendimento())
            break;

          System.out.println("Insira o apelido do animal:");
          String nomeAnimal = entrada.nextLine();
          System.out.println("Insira o nome do dono:");
          String nomeDono = entrada.nextLine();

          if (clinica.verificarProximoAtendimento(nomeAnimal, nomeDono)) {
            System.out.println("Este animal será o próximo a ser chamado");
          } else {
            System.out.println("Este animal não é o próximo a ser chamado");
          }
          break;
        case CONSULTAR_ATENDIMENTO_FILA:
          if(naoPossuiAnimal())
            break;
          boolean invalido = false;
          Atendimento proximoAtendimento =null;
          do {
            System.out.println("Selecione a fila:");
            System.out.println("1) Normal");
            System.out.println("2) Prioritaria");
            int fila = entrada.nextInt();
            entrada.nextLine();

            switch (fila) {
              case 1:
                invalido = false;
                proximoAtendimento = clinica.getProximoAtendimentoFilaNormal();
                break;
              case 2:
                invalido = false;
                proximoAtendimento = clinica.getProximoAtendimentoFilaPrioritaria();
                break;
              default:
                invalido = true;
                System.out.println("Opção invalida, insira novamente");
            }
          }while (invalido);
          if (proximoAtendimento == null) {
            System.out.println("Não existem animais em espera!");
          } else {
            System.out.println("Próximo animal a ser atendido:");
            System.out.println("Senha: " + proximoAtendimento.getSenha());
            proximoAtendimento.getAnimal().dados();
            System.out.println("Tempo de permanência na fila: " + proximoAtendimento.getPermanecia() + " minutos");
          }
          break;
        case RELATORIOS:
          if(naoPossuiAnimal())
            break;

          System.out.println("**********************************************************");
          System.out.println("* Selecione a opção desejada                             *");
          System.out.println("* 1. quantidade de animais em cada fila                  *");
          System.out.println("* 2. quantidade total de animais por faixa etária        *");
          System.out.println("* 3. quantidade total de animais em espera por serviço   *");
          System.out.println("* 4. tempo médio de permanência na fila em minutos       *");
          System.out.println("**********************************************************");
          int op2 = entrada.nextInt();
          switch (op2) {
            case 1:
              System.out.println("Fila Prioritária: ");
              clinica.relAnimalFilaPrioritaria();
              System.out.println("Fila Normal: ");
              clinica.relAnimalFilaNormal();
              break;
            case 2:
              System.out.println("Classificação por faixa etária" + clinica.relAnimalFaixa());
              break;
            case 3:
              System.out.println("Classificação por serviço ");
              clinica.relAnimalEspera();
              break;
            case 4:
              System.out.println("O tempo médio de permanência na fila foi de: "+ clinica.relPermanenciaMedia());
              break;

          }
          break;
        case SAIR:
          System.out.println("O programa será encerrado. Até logo!");
          sair = true;
          break;
      }
    }catch(Exception e){
      System.out.println("Entrada invalda, tente novamente");
        entrada.nextLine();
    }
    } while (!sair);

    entrada.close();
  }

}
