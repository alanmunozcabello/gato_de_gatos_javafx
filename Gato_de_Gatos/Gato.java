public class Gato {
    private char[][] gato = new char[3][3];
    String estado; //"libre", "X", "O", "empate"

    public Gato(){ //crear gato vacio
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                this.gato[i][j] = ' ';
            }
        }
        this.estado = "libre";
    }

    public boolean verificarCasillaOcupada(int fila, int columna){ //verifica si la casilla esta ocupada
        if(this.gato[fila][columna] != ' '){
            return false;
        }
        return true;
    }

    public void marcarSimbolo(int fila, int columna, char simbolo){ //marca la casilla con el simbolo del jugador
        this.gato[fila][columna] = simbolo;
    }

    public String getEstado(){ //para saber el estado del gato
        return this.estado;
    }

    public char[][] getGato(){ //retorna el gato completo
        return this.gato;
    }
    
    // @Override
    // public String toString(){
    //     //kitian
    // }
}