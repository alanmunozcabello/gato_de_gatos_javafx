package Gato_de_Gatos;

public class Dados{
    public Dados(){
    }
    public int tirarDados(){
        int numero = (int) (Math.random() * 12 + 1);
        return numero;
    }
}