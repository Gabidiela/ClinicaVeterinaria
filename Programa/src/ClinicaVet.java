import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class ClinicaVet {
    private Fila FilaNormal;
    private Fila FilaPrioritaria;
    private List<Atendimento> Atendidos;
    private List<Animal> animais;

    public ClinicaVet() {
        FilaNormal = new Fila('N');
        FilaPrioritaria = new Fila('P');
        Atendidos = new ArrayList<Atendimento>();
    }

    public void CadConsulta(Atendimento atendimento) {
        if (atendimento.isPrioritario()) {
            FilaPrioritaria.Inserir(atendimento);
        } else {
            FilaNormal.Inserir(atendimento);
        }
    }

    public Animal ChamaProximo() {
        return null;
    }

    public Boolean VerificaAnimal() {
        return null;
    }

    public Animal FichaProxAnimal() {
        return null;
    }

    public void RelAnimalFila() {
    }

    public void RelAnimalFaixa() {
    }

    public void RelAnimalEspera() {
    }

    public void RelPermanenciaMedia() {
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
