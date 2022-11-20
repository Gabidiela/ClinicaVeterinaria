import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
            System.out.println("************** MENU **************);
            System.out.println("* O que deseja fazer?           *");
            System.out.println("* 1. Cadastrar Animal           *");
            System.out.println("* 2. Realizar Atendimento       *");
            System.out.println("* 3. Chamar proximo Atendimento *")
            System.out.println("* 0. Encerrar programa          *");
            System.out.println("*********************************");          
            int op = entrada.nextInt();
            entrada.nextLine();

            switch (op){
                case 1:
                    System.out.println("Informe os dados do animal");
                    System.out.print("Apelido: ");
                	String Apelido = entrada.nextLine();
                	
                	System.out.print("Tipo do animal:  (canino, felino ou roedor)");
                    String tipoAnimal  = entrada.nextLine();
                    
                    System.out.print("Dono: ");
                    String dono  = entrada.nextLine();
                
                    System.out.print("Data de nascimento: (dd/MM/yyyy)");
                    String data = entrada.nextLine();
                    entrada.nextLine();
                
                    Animal animal;
                    Date dataNascimento = null;
                    try {
                		dataNascimento = sdf.parse(data);

                	} catch (ParseException e) {
                		e.printStackTrace();
                	}
                    
                    animal = new Animal(Apelido, tipoAnimal, dataNascimento, dono);
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
                            clinica.cadConsulta(new Atendimento(selecionarAnimal(),"Vacinação",false));
                            break;
                        case 2:
                            clinica.cadConsulta(new Atendimento(selecionarAnimal(),"Castração",false));
                            break;
                        case 3:
                            clinica.cadConsulta(new Atendimento(selecionarAnimal(),"Check-up",false));
                            break;
                        case 4:
                            clinica.cadConsulta(new Atendimento(selecionarAnimal(),"Urgência",true));
                            break;
                        case 5:
                            break;
                    }
                    break;
                case 0:
                    System.out.println("O programa será encerrado. Até logo!");
                    sair = true;
                    break;
            }
        }while (!sair);



        entrada.close();

    }
    public static Animal selecionarAnimal(){
        List<Animal> animais = clinica.getAnimais();
        System.out.println("Selecione o Animal que deseja realziar o procedimento:");
        for(int i=0;i<animais.size();i++){
            System.out.println(i+") "+animais.get(i).getApelido()+" | "+animais.get(i).getDono());
        }
        int animalID = entrada.nextInt();
        return animais.get(animalID);
    }
}
