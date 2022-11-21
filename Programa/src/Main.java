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
		boolean sair = false;

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
				System.out.print(" Digite o apelido do animalzinho: ");
				String apelido = entrada.nextLine();

				// A clinica s� trabalha com tr�s tipo de animais , por isso precisamos validar

				String tipoAnimal;
				do {
					System.out.println("Digite o tipo do animal (canino, felino ou roedor): ");
					tipoAnimal = entrada.nextLine();
					// Chamada do m�todo de validar o tipo de animal
					isTipoAnimal(tipoAnimal);
				} while (validaAnimal == false);
				// PROPRIET�RIO DO ANIMALZINHO
				System.out.println("Digite o nome do dono do(a) " + apelido);
				String dono = entrada.nextLine();

				System.out.println("Digite a data de nascimento (dd/mm/aaaa): ");
				Date dataNascimento = sdf.parse(entrada.next());

				// SERVI�O ------------------------------------------
				System.out.println("Selecione o servi�o");
				System.out.println("1. Vacina��o");
				System.out.println("2. Castra��o");
				System.out.println("3. Check-up");
				int numeroServico = entrada.nextInt();
				entrada.nextLine();

				System.out.print("O atendimento é urgente? (S/N): ");
				char atendimentoUrgente = entrada.nextLine().charAt(0);

				// ADD UM NOVO ANIMAL
				Animal animal = new Animal(apelido, tipoAnimal, dataNascimento, dono, numeroServico,
						atendimentoUrgente);

				// --------------------------------------------------

				// FILA
				// ----------------------------------------------------------------------------
				/*
				 * Est� com erro 0 if (atendimentoUrgente.equalsIgnoreCase("S")) {
				 * clinica.cadConsulta(new Atendimento(animal, numeroServico, true),
				 * "Prioritario"); } else { clinica.cadConsulta(new Atendimento(animal,
				 * numeroServico, false), "Normal"); }
				 */
				// ---------------------------------------------------------------------------------

				System.out.println("Cadastro bem sucedido!\n "  );

				break;
			case 2:
				Animal animalAtendido = clinica.realizarAtendimento();

				if (animalAtendido == null) {
					System.out.println("A fila está vázia!");
				} else {
					System.out.println(
							"Animal atendido: " + animalAtendido.getApelido() + " | Dono: " + animalAtendido.getDono());
				}

				break;
			case 3:
				Animal proximoAnimal = clinica.mostrarProximo();

				if (proximoAnimal == null) {
					System.out.println("A fila está vázia!");
				} else {
					System.out.println(
							"Próxmo animal: " + proximoAnimal.getApelido() + " | Dono: " + proximoAnimal.getDono());
				}

				break;
			/*
			 * case 4:
			 * System.out.println("***************************************************");
			 * System.out.println("* Selecione a opção desejada                      *");
			 * System.out.println("* 1. quantidade de animais em cada fila           *");
			 * System.out.println("* 2. quantidade total de animais por faixa etária *");
			 * System.out.println("* 3. quantidade total de animais em espera        *");
			 * System.out.println("* 4. tempo médio de permanência na fila           *");
			 * System.out.println("***************************************************");
			 * int op2 = entrada.nextInt(); switch (op2){ case 1:
			 * System.out.println("Fila Prioritária: " + clinica.relAnimalFila1());
			 * System.out.println("Fila Normal: " + clinica.relAnimalFila2());
			 * 
			 * break; case 2: System.out.println("Classificação por faixa etária");
			 * clinica.relAnimalFaixa();
			 * 
			 * break; case 3: break; case 4: break;
			 * 
			 * } break;
			 */
			case 0:
				System.out.println("O programa será encerrado. Até logo!");
				sair = true;
				break;
			}
		} while (!sair);

		entrada.close();
	}

	// M�todo que valida o tipo de animal
	public static boolean isTipoAnimal(String tipoAnimal) {

		if (tipoAnimal.equals("canino") || tipoAnimal.equals("roedor") || tipoAnimal.equals("felino")) {
			return validaAnimal = true;
		} else {
			System.err.println("O tipo de animal " + tipoAnimal + " � invalido" + "\n");
			return validaAnimal = false;
		}
	}
}
