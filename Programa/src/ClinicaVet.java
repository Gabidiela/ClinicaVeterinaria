import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

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

    public Boolean verificaAnimal() {
        return null;
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

    public void relAnimalFila() {
    }

    public void relAnimalFaixa() {
    }

    public void relAnimalEspera() {
    }

    public void relPermanenciaMedia() {
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
}
