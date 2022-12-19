import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Animal implements Serializable {
    private String apelido;
    private String tipoAnimal;
    private Date dataNascimento;
    private String dono;
 
     public Animal( String apelido, String tipoAnimal, Date dataNascimento, String dono) {
         this.apelido = apelido;
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
     public String dados() {
         String pattern = "dd/MM/yyyy";
         SimpleDateFormat sdf = new SimpleDateFormat(pattern);
         return "O apelido do Animal é: " + apelido +"\n"
                 +"o tipo do animal é: " + tipoAnimal+"\n"
                 +"a data do nascimento é: " + sdf.format(dataNascimento)+"\n"
                 +"O dono do animal é: " + dono+"\n";
     }
}

