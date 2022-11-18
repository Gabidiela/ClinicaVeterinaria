import java.util.PriorityQueue;
import java.util.Queue;

public class Fila{
    private int NumeroFila;
    private char TipoFila;

    private Queue<Atendimento> Fila;

    public Fila(char tipoFila) {
        NumeroFila = 0;
        TipoFila = tipoFila;
        Fila = new PriorityQueue<Atendimento>();
    }
    public void Inserir(Atendimento A){
        NumeroFila++;
        A.setSenha(TipoFila + "." + NumeroFila);
        Fila.add(A);
    }

    public Atendimento ChamarProximo(){
        if(Fila.size()>0) {
            Atendimento proximo = Fila.remove();
            proximo.setHoraConsulta();
            return proximo;
        }
        return null;
    }

    public Atendimento VerFila(){
        return Fila.peek();
    }

}
