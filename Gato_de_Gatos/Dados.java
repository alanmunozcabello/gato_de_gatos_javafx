public class Dados{
    int numero;
    public Dados(){
    }

    public int tirarDados(){
        numero = (int) (Math.random() * 12 + 1);
        return numero;
    }
}