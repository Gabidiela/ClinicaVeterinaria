import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        boolean sair = false;

        do {
            System.out.println("O que deseja fazer?");
            System.out.println("1. Cadastrar Animal");
            System.out.println("2. Realizar Atendimento");
            System.out.println("3. Encerrar programa");
            int op = entrada.nextInt();

            switch (op){
                case 1:
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
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                    }

                    break;
                case 3:
                    System.out.println("O programa será encerrado. Até logo!");
                    sair = true;
                    break;
            }
        }while (!sair);



        entrada.close();

    }
}
