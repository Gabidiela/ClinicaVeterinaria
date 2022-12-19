import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class Main {
  static ClinicaVet clinica;

  static final int CADASTRAR_ANIMAL=1;
  static final int AGENDAR_ATENDIMENTO=2;
  static final int REALIZAR_ATENDIMENTO=3;
  static final int CONSULTAR_ATENDIMENTO=4;
  static final int CONSULTAR_ATENDIMENTO_FILA=5;
  static final int RELATORIOS=6;
  static final int SAIR=0;

  public static Animal selecionarAnimal() {
    List<Animal> animais = clinica.getAnimais();
    String texto = "Selecione o Animal que para realizar o antendimento:\n";
    for (int i = 0; i < animais.size(); i++) {
      texto+= (i + ") " + animais.get(i).getApelido() + " | " + animais.get(i).getDono()+"\n");
    }
    int animalID = showIntInputDialog(texto);
    return animais.get(animalID);
  }

  public static boolean naoPossuiAnimal(){
    if(!clinica.temAnimais()){
      showInforMessage("Nenhum animal cadastrado, por favor cadastre um animal para prosseguir");
      return true;
    }
    return false;
  }

  public static boolean naoPossuiAtendimento(){
    if(clinica.getProximoAtendimento() == null){
      showInforMessage("Não há animais em filas de espera!");
      return true;
    }
    return false;
  }

  public static void main(String[] args) throws Exception {
    clinica = new ClinicaVet();
    boolean sair = false;

    do {
      try {

        String texto ="********************* MENU **********************\n"
                +"O que deseja fazer?                           \n"
                +"1. Cadastrar novo animal                      \n";
        if(clinica.temAnimais()){
          texto +="2. Agendar Atendimento                        \n"
                  +"3. Realizar Atendimento                       \n"
                  +"4. Consultar proximo Atendimento              \n"
                  +"5. Consultar proximo Atendimento por Fila     \n"
                  +"6. Consultas de caráter estatístico           \n";
        }
        texto +="0. Encerrar programa                          \n";
        int operacao = showIntInputDialog(texto);
        switch (operacao) {
          case CADASTRAR_ANIMAL:
            // ANIMAL ------------------------------------------------------
            String apelido = showInputDialog("Informe os dados do animal\n"
                    +"Apelido: ");

            String tipoAnimal = null;
            do {
              int tipo = showIntInputDialog("Tipo do animal: \n"
                      +"1) canino \n"
                      +"2) felino \n"
                      +"3) roedor ");

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
                  showErrorMessage("Opção inválida, tente novamente!");
              }
            }while (tipoAnimal == null);

            String dono = showInputDialog("Dono: ");

            Date dataNascimento = null;
            do {

              String data = showInputDialog("Data de nascimento (dd/mm/aaaa): ");

              try {
                dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(data);
              } catch (ParseException e) {
                showErrorMessage("Opção inválida, tente novamente!");
              }
            }while (dataNascimento == null);

            // -------------------------------------------------------------
            Animal animal = new Animal(apelido, tipoAnimal, dataNascimento, dono);
            showInforMessage(clinica.cadastrarAnimal(animal));
            break;
          case AGENDAR_ATENDIMENTO:
            if(!clinica.temAnimais()){
              showInforMessage("Nenhum animal cadastrado, por favor cadastre um animal para prosseguir");
              break;
            }

            int sel = showIntInputDialog("Selecione o serviço\n"
                    +"1. Vacinar Animal\n"
                    +"2. Castrar Animal\n"
                    +"3. Check-up\n"
                    +"4. Atendimento de Urgência/Emergência\n"
                    +"0. Voltar\n");

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
              case 0:
                break;
            }
            showInforMessage("Cadastro bem sucedido!", "Sucesso");
            break;
          case REALIZAR_ATENDIMENTO:
            if(naoPossuiAnimal())
              break;

            Atendimento atendimento = clinica.chamaProximoAtendimento();
            if (atendimento == null) {
              showInforMessage("Não existem animais em espera!");
            } else {
              showInforMessage("Por favor, compareça ao consultório o próximo a ser atendido:\n"
                      +"Senha: " + atendimento.getSenha()+"\n"
                      +"animal: " + atendimento.getAnimal().getApelido()+"\n"
                      +"Dono: " + atendimento.getAnimal().getDono()+"\n"
                      +"Tempo de permanência na fila: " + atendimento.getPermanecia() + "\n"
                      +"Atendimento realizado com sucesso\n","Atendimento");
            }
            break;
          case CONSULTAR_ATENDIMENTO:
            if(naoPossuiAnimal() || naoPossuiAtendimento())
              break;

            String nomeAnimal = showInputDialog("Insira o apelido do animal:");

            String nomeDono = showInputDialog("Insira o nome do dono:");

            if (clinica.verificarProximoAtendimento(nomeAnimal, nomeDono)) {
              showInforMessage("Este animal será o próximo a ser chamado");
            } else {
              showInforMessage("Este animal não é o próximo a ser chamado");
            }
            break;
          case CONSULTAR_ATENDIMENTO_FILA:
            if(naoPossuiAnimal())
              break;
            boolean invalido = false;
            Atendimento proximoAtendimento =null;
            do {
              int fila = showIntInputDialog("Selecione a fila:\n"
                      +"1) Normal\n"
                      +"2) Prioritaria");


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
                  showErrorMessage("Opção invalida, insira novamente");
              }
            }while (invalido);
            if (proximoAtendimento == null) {
              showInforMessage("Não existem animais em espera!");
            } else {
              showInforMessage("Próximo animal a ser atendido:\n"
                      +"Senha: " + proximoAtendimento.getSenha() + "\n"
                      + proximoAtendimento.getAnimal().dados()
                      +"Tempo de permanência na fila: " + proximoAtendimento.getPermanecia());
            }
            break;
          case RELATORIOS:
            if(naoPossuiAnimal())
              break;

            int op2 = showIntInputDialog("Selecione a opção desejada                             \n"
                    +"1. quantidade de animais em cada fila                  \n"
                    +"2. quantidade total de animais por faixa etária        \n"
                    +"3. quantidade total de animais em espera por serviço   \n"
                    +"4. tempo médio de permanência na fila em minutos       \n");
            switch (op2) {
              case 1:
                showInforMessage("Fila Prioritária: \n"
                        +clinica.relAnimalFilaPrioritaria()+"\n"
                        +"Fila Normal: \n"
                        +clinica.relAnimalFilaNormal()
                );

                break;
              case 2:
                showInforMessage("Classificação por faixa etária" + clinica.relAnimalFaixa());
                break;
              case 3:
                showInforMessage("Classificação por serviço \n"
                        +clinica.relAnimalEspera());
                break;
              case 4:
                showInforMessage("O tempo médio de permanência na fila foi de: "+ clinica.relPermanenciaMedia());
                break;

            }
            break;
          case SAIR:
            showInforMessage("O programa será encerrado. Até logo!");
            sair = true;
            break;
        }
      }catch(Exception e){
        showErrorMessage("Entrada invalda, tente novamente");
      }
    } while (!sair);

  }

  public static int showIntInputDialog(String texto){
    while(true) {
      String retorno = showInputDialog(texto);
      try {
        return Integer.parseInt(retorno);
      } catch (Exception e) {
        showErrorMessage("Opção inválida, tente novamente!");
      }
    }
  }
  public static String showInputDialog(String texto){
    do {
      String op = JOptionPane.showInputDialog(null, texto);
      try {
        if(op.isEmpty())
          throw new IllegalStateException();

        return op;
      } catch (Exception e) {
        showErrorMessage("Opção inválida, tente novamente!");
      }
    } while (true);
  }
  public static void showInforMessage(String message, String title){
    JOptionPane.showMessageDialog(null, message, title,
            JOptionPane.INFORMATION_MESSAGE);
  }
  public static void showErrorMessage(String message){
    showErrorMessage(message, "Erro");
  }
  public static void showInforMessage(String message){
    showInforMessage(message, "Info");
  }
  public static void showErrorMessage(String message, String title){
    JOptionPane.showMessageDialog(null, message, title,
            JOptionPane.ERROR_MESSAGE);
  }
}
