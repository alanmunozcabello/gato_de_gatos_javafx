public class Gato {
    private char[][] gato = new char[3][3];
    String estado; //"libre", "X", "O", "empate"

    public Gato(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                gato[i][j] = ' ';
            }
        }
        estado = "libre";
    }

    public boolean verificarCasillaOcupada(int fila, int columna){
        if(gato[fila][columna] == ' '){
            return true;
        }
    }

    public void marcarSimbolo(int fila, int columna, char simbolo){
        gato[fila][columna] = simbolo;
    }

    public String getEstado(){
        return estado;
    }

    @Override
    public String toString(){
        //kitian
    }
}