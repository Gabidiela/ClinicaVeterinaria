import java.util.Queue;
import java.util.LinkedList;
import java.util.Date;
import java.util.Iterator;

public class ClinicaVet {
    private Queue<Atendimento> filaPrioritaria;
    private Queue<Atendimento> filaNormal;
    private Queue<Atendimento> atendidos;
    private Iterator<Atendimento> it;

    public ClinicaVet() {
        filaNormal = new LinkedList<Atendimento>();
        filaPrioritaria = new LinkedList<Atendimento>();
        atendidos = new LinkedList<Atendimento>();
    }

    public void cadConsulta(Atendimento atendimento, String prioridade) {
        if (prioridade == "Prioritario") {
            filaPrioritaria.add(atendimento);
        } else if (prioridade == "Normal") {
            filaNormal.add(atendimento);
        }
    }

    public Animal realizarAtendimento() {
        Animal animalAtendido = null;

        if (!filaPrioritaria.isEmpty()) {
            animalAtendido = filaPrioritaria.peek().getAnimal();
            atendidos.add(filaPrioritaria.poll());
        } else if (!filaNormal.isEmpty()) {
            animalAtendido = filaNormal.peek().getAnimal();
            atendidos.add(filaNormal.poll());
        }

        return animalAtendido;
    }

    public Animal mostrarProximo() {
        Animal proximoAnimal = null;

        if (!filaPrioritaria.isEmpty()) {
            proximoAnimal = filaPrioritaria.peek().getAnimal();
        } else if (!filaNormal.isEmpty()) {
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
        int[] faixaEtaria = new int[3];

        it = filaNormal.iterator();
        while (it.hasNext()) {
            Date dataNascimento = it.next().getAnimal().getDataNascimento();

            int anoAtual = agora.getYear();
            int anoNascimento = dataNascimento.getYear();
            int idade = anoAtual - anoNascimento;

            int mesAtual = agora.getMonth();
            int mesNascimento = dataNascimento.getMonth();

            if (mesAtual < mesNascimento) {
                idade--;
            } else {
                int diaAtual = agora.getDay();
                int diaNascimento = dataNascimento.getDay();
                if (mesAtual == mesNascimento && diaAtual < diaNascimento) {
                    idade--;
                }
            }

            if (idade <= 2) {
                faixaEtaria[0]++;
            } else if (idade > 2 && idade <= 5) {
                faixaEtaria[1]++;
            } else {
                faixaEtaria[2]++;
            }
        }
        return String.format("------ Fila Prioritária ------%n") +
                String.format("a) até 2 anos de idade %d%n", faixaEtaria[0]) +
                String.format("b) entre 2 e 5 anos de idade %d%n", faixaEtaria[1]) +
                String.format("c) mais de 5 anos de idade %d%n", faixaEtaria[2]) +
                String.format("-------------------------------");
    }

    public String relAnimalFaixaP() {
        Date agora = new Date();
        int[] faixaEtaria = new int[3];

        it = filaPrioritaria.iterator();
        while (it.hasNext()) {
            Date dataNascimento = it.next().getAnimal().getDataNascimento();

            int anoAtual = agora.getYear();
            int anoNascimento = dataNascimento.getYear();
            int idade = anoAtual - anoNascimento;

            int mesAtual = agora.getMonth();
            int mesNascimento = dataNascimento.getMonth();

            if (mesAtual < mesNascimento) {
                idade--;
            } else {
                int diaAtual = agora.getDay();
                int diaNascimento = dataNascimento.getDay();
                if (mesAtual == mesNascimento && diaAtual < diaNascimento) {
                    idade--;
                }
            }

            if (idade <= 2) {
                faixaEtaria[0]++;
            } else if (idade > 2 && idade <= 5) {
                faixaEtaria[1]++;
            } else {
                faixaEtaria[2]++;
            }
        }
        return String.format("------ Fila Prioritária ------%n") +
                String.format("a) até 2 anos de idade %d%n", faixaEtaria[0]) +
                String.format("b) entre 2 e 5 anos de idade %d%n", faixaEtaria[1]) +
                String.format("c) mais de 5 anos de idade %d%n", faixaEtaria[2]) +
                String.format("-------------------------------");
    }

    public String relAnimalEsperaN() {
        int[] procedimento = new int[3];

        it = filaNormal.iterator();
        while (it.hasNext()) {

            switch(it.next().getNomeServico()) {
                case ("Vacinação"):
                    procedimento[0]++;
                    break;
                case("Castração"):
                    procedimento[1]++;
                    break;
                case("Check-up"):
                    procedimento[2]++;
                    break;
            }
        }
        return String.format("--- Fila Prioritária ---%n") +
                String.format("a) Vacinação: %d%n", procedimento[0]) +
                String.format("b) Castração: %d%n", procedimento[1]) +
                String.format("c) Check-Up: %d%n", procedimento[2]) +
                String.format("------------------------");
    }

    public String relAnimalEsperaP() {
        int[] procedimento = new int[3];

        it = filaPrioritaria.iterator();
        while (it.hasNext()) {

            switch(it.next().getNomeServico()) {
                case ("Vacinação"):
                    procedimento[0]++;
                    break;
                case("Castração"):
                    procedimento[1]++;
                    break;
                case("Check-up"):
                    procedimento[2]++;
                    break;
            }
        }
        return String.format("--- Fila Prioritária ---%n") +
                String.format("a) Vacinação: %d%n", procedimento[0]) +
                String.format("b) Castração: %d%n", procedimento[1]) +
                String.format("c) Check-Up: %d%n", procedimento[2]) +
                String.format("------------------------");
    }

    // *** FALTANDO COLOCAR NA MAIN
    public String relAnimalEspera(String apelidoAnimal) {
        String retorno = null;

        Queue<Atendimento> atendidosReserva = atendidos;

        for (int i = 0; i < atendidosReserva.size(); i++) {
            Atendimento atendimento = atendidosReserva.poll();
            long tempoEspera  = atendimento.getTempoEspera();
            Animal animal = atendimento.getAnimal();

            if (atendimento.getAnimal().getApelido().equalsIgnoreCase(apelidoAnimal)) {
                retorno = ("Tempo de espera do animal " + animal.getApelido() + ": " + tempoEspera);
                break;
            }
        }

        return retorno;
    }

    public String relPermanenciaMedia() {
        it = atendidos.iterator();

        long tempoTotalEspera = 0;

        while (it.hasNext()) {
            tempoTotalEspera += it.next().getTempoEspera();
        }

        long tempoMedioEspera  = tempoTotalEspera / atendidos.size();

        // CONVERTER PRA S,M,H AQUI !!

        return ("Tempo médio de espera: " + tempoMedioEspera);
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

                if (animalTemp.getApelido().equals(animal.getApelido())
                        && animalTemp.getDono().equals(animal.getDono())) {
                    retorno = true;
                    break;
                }
            }
        }

        return retorno;
    }
}
