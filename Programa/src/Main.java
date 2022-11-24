import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static ClinicaVet clinica;
    static Scanner entrada;
    public static boolean validaAnimal = false;

    public static void main(String[] args) throws ParseException {
        entrada = new Scanner(System.in);
        clinica = new ClinicaVet();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        boolean sairPrincipal = false;
        boolean sairEstatistica = false;

        do {
            System.out.println("***************** MENU *****************");
            System.out.println("* O que deseja fazer?                  *");
            System.out.println("* 1. Cadastrar novo animal             *");
            System.out.println("* 2. Realizar Atendimento              *");
            System.out.println("* 3. Mostrar proximo Atendimento       *");
            System.out.println("* 4. Consultas de caráter estatístico  *");
            System.out.println("* 0. Encerrar programa                 *");
            System.out.println("****************************************\n");
            int op = entrada.nextInt();
            entrada.nextLine();

            switch (op) {
                case 1:
                    // CADASTRO DE ANIMAL
                    System.out.println("Informe os dados do animal");
                    System.out.print("Digite o apelido do animalzinho: ");
                    String apelido = entrada.nextLine();

                    // A clinica só trabalha com três tipo de animais , por isso precisamos validar

                    String tipoAnimal;
                    do {
                        System.out.println("Digite o tipo do animal (canino, felino ou roedor): ");
                        tipoAnimal = entrada.nextLine();
                        // Chamada do método de validar o tipo de animal
                        isTipoAnimal(tipoAnimal);
                    } while (validaAnimal == false);
                    // PROPRIETÁRIO DO ANIMALZINHO
                    System.out.println("Digite o nome do dono do(a) " + apelido);
                    String dono = entrada.nextLine();

                    System.out.println("Digite a data de nascimento (dd/mm/aaaa): ");
                    Date dataNascimento = sdf.parse(entrada.next());

                    // SERVIÇO ------------------------------------------
                    System.out.println("Selecione o serviço");
                    System.out.println("1. Vacinação");
                    System.out.println("2. Castração");
                    System.out.println("3. Check-up");
                    int numeroServico = entrada.nextInt();
                    entrada.nextLine();

                    System.out.print("O atendimento é urgente? (S/N): ");
                    char atendimentoUrgente = entrada.nextLine().charAt(0);

                    String nomeServico = "";
                    switch(numeroServico) {
                        case 1:
                            nomeServico = "Vacinação";
                            break;
                        case 2:
                            nomeServico = "Castração";
                            break;
                        case 3:
                            nomeServico = "Check-up";
                            break;
                    }

                    // ADD UM NOVO ANIMAL
                    Animal animal = new Animal(apelido, tipoAnimal, dataNascimento, dono, numeroServico, atendimentoUrgente);

                    if (atendimentoUrgente == 'S' || atendimentoUrgente == 's') {
                        clinica.cadConsulta(new Atendimento(animal, nomeServico, true), "Prioritario");
                    } else {
                        clinica.cadConsulta(new Atendimento(animal, nomeServico, false), "Normal");
                    }
                    // ---------------------------------------------------------------------------------

                    System.out.println("Cadastro bem sucedido!\n ");

                    break;
                case 2:
                    Animal animalAtendido = clinica.realizarAtendimento();

                    if (animalAtendido == null) {
                        System.out.println("A fila está vázia!");
                    } else {
                        System.out.println(
                                "Animal atendido: " + animalAtendido.getApelido() + " | Dono: "
                                        + animalAtendido.getDono());
                    }

                    break;
                case 3:
                    Animal proximoAnimal = clinica.mostrarProximo();

                    if (proximoAnimal == null) {
                        System.out.println("A fila estÃ¡ vÃ¡zia!");
                    } else {
                        System.out.println(
                                "Próxmo animal: " + proximoAnimal.getApelido() + " | Dono: "
                                        + proximoAnimal.getDono());
                    }
                    break;
                case 4:
                    sairEstatistica = false;
                    do {
                        System.out.println("***************************************************");
                        System.out.println("* Selecione a opção desejada                      *");
                        System.out.println("* 0. Retornar ao menu principal                   *");
                        System.out.println("* 1. Quantidade de animais em cada fila           *");
                        System.out.println("* 2. Quantidade total de animais por faixa etária *");
                        System.out.println("* 3. Quantidade total de animais em espera        *");
                        System.out.println("* 4. Tempo médio de permanência na fila           *");
                        System.out.println("***************************************************");
                        int op2 = entrada.nextInt();

                        switch (op2) {
                            case 0:
                                sairEstatistica = true;
                                break;
                            case 1:
                                System.out.println("\nFila Prioritária: " + clinica.relAnimalFila1());
                                System.out.println("Fila Normal: " + clinica.relAnimalFila2() + "\n");
                                break;
                            case 2:
                                System.out.println("\nClassificação por faixa etária");
                                System.out.println(clinica.relAnimalFaixaN());
                                System.out.println(clinica.relAnimalFaixaP() + "\n");
                                break;
                            case 3:
                                System.out.println("\n" + clinica.relAnimalEsperaN());
                                System.out.println("\n" + clinica.relAnimalEsperaP() + "\n");
                                break;
                            case 4:
                                break;
                        }
                    } while (!sairEstatistica);
                case 0:
                    System.out.println("O programa foi encerrado com sucesso!");
                    sairPrincipal = true;
            }
        } while (!sairPrincipal);

        entrada.close();
    }

    // Método que valida o tipo de animal
    public static boolean isTipoAnimal(String tipoAnimal) {

        if (tipoAnimal.equals("canino") || tipoAnimal.equals("roedor") || tipoAnimal.equals("felino")) {
            return validaAnimal = true;
        } else {
            System.err.println("O tipo de animal " + tipoAnimal + " é invalido" + "\n");
            return validaAnimal = false;
        }
    }
}
