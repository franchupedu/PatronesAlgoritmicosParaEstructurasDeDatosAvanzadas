package loco;

public class Libro {
    @Imprimible(mayusculas=false)
    String titulo;
   @Imprimible(mayusculas=true)
    String autor;
    
   public Libro(String titulo, String autor) {
       super();
       this.titulo = titulo;
       this.autor = autor;
   }
   public String getTitulo() {
       return titulo;
   }
   public void setTitulo(String titulo) {
       this.titulo = titulo;
   }
   public String getAutor() {
       return autor;
   }
   public void setAutor(String autor) {
       this.autor = autor;
   }
    
}
