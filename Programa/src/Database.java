import java.io.Serializable;
import java.util.List;

public class Database implements Serializable {
    public Fila filaNormal;
    public Fila filaPrioritaria;
    public List<Atendimento> atendidos;
    public List<Animal> animais;

    public Database(Fila filaNormal, Fila filaPrioritaria, List<Atendimento> atendidos, List<Animal> animais) {
        this.filaNormal = filaNormal;
        this.filaPrioritaria = filaPrioritaria;
        this.atendidos = atendidos;
        this.animais = animais;
    }
}
