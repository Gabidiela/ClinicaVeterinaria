import java.util.*;

public class ClinicaVet {
    private Fila filaNormal;
    private Fila filaPrioritaria;
    private List<Atendimento> atendidos;
    private List<Animal> animais;

    public ClinicaVet() {
        filaNormal = new Fila('N');
        filaPrioritaria = new Fila('P');
        atendidos = new ArrayList<Atendimento>();
        animais = new ArrayList<Animal>();
    }

    public void cadConsulta(Atendimento atendimento) {
        if (atendimento.isPrioritario()) {
            filaPrioritaria.inserir(atendimento);
        } else {
            filaNormal.inserir(atendimento);
        }
    }

    public Atendimento chamaProximoAtendimento() {
        Atendimento proximo;
        if(!filaPrioritaria.vazia()) {
            proximo = filaPrioritaria.chamarProximo();
            atendidos.add(proximo);
        }else if(!filaNormal.vazia()) {
            proximo = filaNormal.chamarProximo();
            atendidos.add(proximo);
        }else{
            return null;
        }
    return proximo;
    }

    public Atendimento getProximoAtendimento() {
        if(!filaPrioritaria.vazia())
           return filaPrioritaria.verProximo();
        else if(!filaNormal.vazia())
            return filaNormal.verProximo();
        else
            return null;
    }
    public Atendimento getProximoAtendimentoFilaPrioritaria() {
        if(!filaPrioritaria.vazia())
                return filaPrioritaria.verProximo();

            return null;
    }
    public Atendimento getProximoAtendimentoFilaNormal() {
        if(!filaNormal.vazia())
            return filaNormal.verProximo();

        return null;
    }
    public boolean verificarProximoAtendimento(String nomeAnimal, String nomeDono){
        Animal proximoAnimal = getProximoAtendimento().getAnimal();
        if (proximoAnimal.getApelido().equals(nomeAnimal) &&
                proximoAnimal.getDono().equals(nomeDono)) {
            return true;
        }
        return false;
    }

    public void relAnimalFilaPrioritaria() {
        if(!filaPrioritaria.vazia())
            System.out.println("Há " + filaPrioritaria.tamFila() + " animal(is) na fila");
    }

    public void relAnimalFilaNormal() {
        if(!filaNormal.vazia())
            System.out.println("Há " + filaNormal.tamFila() + " animal(is) na fila");
    }

    public AnimalPorFaixa separaAnimalPorFaixa(Iterator<Atendimento> atendimentoIterator){
        Date agora = new Date(System.currentTimeMillis());
        int crianca = 0, adulto = 0, idoso = 0;

        while (atendimentoIterator.hasNext()){
            Atendimento atendimento = atendimentoIterator.next();
            int idade = new Date(agora.getTime()-atendimento.getAnimal().getDataNascimento().getTime()).getYear();

            if (idade <= 2){
                crianca++;
            } else if(idade >2 && idade <=5) {
                adulto++;
            } else {
                idoso++;
            }
        }
        return new AnimalPorFaixa(crianca, adulto, idoso);
    }

    public String relAnimalFaixa() {
        AnimalPorFaixa faixaAnimal = new AnimalPorFaixa(0, 0, 0);
        faixaAnimal.incrementar(separaAnimalPorFaixa(filaPrioritaria.getFilaIterator()));
        faixaAnimal.incrementar(separaAnimalPorFaixa(filaNormal.getFilaIterator()));
        faixaAnimal.incrementar(separaAnimalPorFaixa(atendidos.iterator()));

        return String.format("\n --------- Faixas ------------- ") +
                String.format("\na) até 2 anos de idade %d%n", faixaAnimal.crianca) +
                String.format("b) entre 2 e 5 anos de idade %d%n", faixaAnimal.adulto) +
                String.format("c) mais de 5 anos de idade %d%n", faixaAnimal.idoso) +
                String.format("-------------------------------");
    }

    public void relAnimalEspera() {
        if (!filaNormal.vazia()){
            Iterator<Atendimento> atendimento = filaNormal.getFilaIterator();
            int vacinados = 0;
            int castrados = 0;
            int checados = 0;
            int urgencia = filaPrioritaria.tamFila();

            while(atendimento.hasNext()) {
                switch (atendimento.next().getNomeServico()){
                    case "Vacinação":
                        vacinados++;
                        break;
                    case "Castração":
                        castrados++;
                        break;
                    case "Check-up":
                        checados++;
                        break;
                }
            }
            System.out.println("Relatório de animais a serem  atendidos filtrado por serviços");
            System.out.println("Vacinação: " + vacinados + " animais");
            System.out.println("Castração: " + castrados + " animais");
            System.out.println("Check-up: " + checados + " animais");
            System.out.println("Urgência/emergência: " + urgencia + " animais");

        }
    }

    public double relPermanenciaMedia() {
        int totalAtendidos = atendidos.size();
        double soma = 0;
        for (int i = 0;i < totalAtendidos; i++ )
            soma +=  atendidos.get(i).getPermanecia();

        double media = (soma/totalAtendidos);
        return media;
    }

    public boolean animalJaExiste(Animal animal) {
        for (int i = 0; i < animais.size(); i++) {
            Animal cadastrado = animais.get(i);
            if (cadastrado.getApelido().equals(animal.getApelido()) &&
                    cadastrado.getDono().equals(animal.getDono())) {
                return true;
            }
        }
        return false;
    }

    public String cadastrarAnimal(Animal animal) {
        if (animalJaExiste(animal)) {
            return "Já existe um animal com este mesmo nome e dono, não foi possivel cadastrar";
        }

        animais.add(animal);
        return "Animal cadastrado com sucesso";
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public boolean temAnimais(){
        return animais.size()>0;
    }
}
