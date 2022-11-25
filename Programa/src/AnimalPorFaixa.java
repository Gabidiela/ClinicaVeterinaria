public class AnimalPorFaixa {
    public int crianca;
    public int adulto;
    public int idoso;

    public AnimalPorFaixa(int crianca, int adulto, int idoso) {
        this.crianca = crianca;
        this.adulto = adulto;
        this.idoso = idoso;
    }

    public void incrementar(AnimalPorFaixa A){
        this.crianca += A.crianca;
        this.adulto += A.adulto;
        this.idoso += A.idoso;
    }
}
