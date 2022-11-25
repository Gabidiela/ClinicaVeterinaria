import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Fila{
    private int NumeroFila;
    private char TipoFila;

    private Queue<Atendimento> Fila;

    public Fila(char tipoFila) {
        NumeroFila = 0;
        TipoFila = tipoFila;
        Fila = new LinkedList<Atendimento>();
    }
    public void inserir(Atendimento A){
        NumeroFila++;
        A.setSenha(TipoFila + "." + NumeroFila);
        Fila.add(A);
    }

    public Atendimento chamarProximo(){
        if(!vazia()) {
            Atendimento proximo = Fila.remove();
            proximo.setHoraConsulta();
            return proximo;
        }
        return null;
    }

    public Atendimento verProximo(){
        if(!vazia()) {
            return Fila.peek();
        }
        return null;
    }

    public boolean vazia(){
        return Fila.isEmpty();
    }

    public int tamFila() {
        return Fila.size();
    }

    public Iterator<Atendimento> getFilaIterator() {
        return Fila.iterator();
    }
}
