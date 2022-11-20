import java.util.Date;

public class Animal {
    private String apelido;
    private String tipoAnimal;
    private Date dataNascimento;
    private String dono;
 
     public Animal( String apelido, String tipoAnimal, Date dataNascimento, String dono) {
         this.Apelido = apelido;
         this.tipoAnimal = tipoAnimal;
         this.dataNascimento = dataNascimento;
         this.dono = dono;
     }
     public String getApelido() {
         return apelido;
     }
     public void setApelido(String novoApelido) {
         apelido = novoApelido;
     }
     public String getTipoAnimal() {
         return tipoAnimal;
     }
     public void setTipoAnimal(String novoTipoAnimal) {
         tipoAnimal = novoTipoAnimal;
     }
     public Date getDataNascimento() {
         return dataNascimento;
     }
     public void setDataNascimento(Date novaDataNascimento) {
         dataNascimento = novaDataNascimento;
     }
     public String getDono() {
         return dono;
     }
     public void setDono(String novoDono) {
         dono = novoDono;
     }
     public void dados() {
         System.out.println("O apelido do cachorro é:" + apelido );
         System.out.println("o tipo do animal é:" + tipoAnimal);
         System.out.println("a data do nascimento é:" + dataNascimento);
         System.out.println("O dono do animal é:" + dono);
     }
}

