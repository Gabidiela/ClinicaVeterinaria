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
            FilaPrioritaria.Inserir(atendimento);
        } else {
            FilaNormal.Inserir(atendimento);
        }
    }

    public Animal chamaProximo() {
        return null;
    }

    public Boolean verificaAnimal() {
        return null;
    }

    public Animal fichaProxAnimal() {
        return null;
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
