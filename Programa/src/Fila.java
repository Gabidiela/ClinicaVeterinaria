import java.util.Queue;

public class Fila{
    private int NumeroFila;
    private char TipoFila;

    private Queue<Atendimento> Fila;

    public Fila(int numeroFila, char tipoFila) {
        NumeroFila = numeroFila;
        TipoFila = tipoFila;
    }
    public void Inserir(Atendimento A){
        NumeroFila++;
        A.setSenha(TipoFila + "." + NumeroFila);
        Fila.add(A);
    }

    public Atendimento Remover(){
        return Fila.remove();
    }

    public Atendimento VerFila(){
        return Fila.peek();
    }

}
