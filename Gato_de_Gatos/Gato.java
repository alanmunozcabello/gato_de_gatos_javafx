package Gato_de_Gatos;

public class Gato {
    private char[][] gato = new char[3][3];
    private String estado; //"libre", "X", "O", "empate"

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

    public void marcarSeleccion(int fila, int columna, char simbolo){ //marca la casilla con el simbolo del jugador
        this.gato[fila][columna] = simbolo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado(){ //para saber el estado del gato
        return this.estado;
    }

    public char[][] getGato(){ //retorna el gato completo
        return this.gato;
    }

    public void mostrarGato(){
        System.out.println(this.gato[0][0] + "|" + this.gato[0][1] + "|" + this.gato[0][2]);
        System.out.println("-----");
        System.out.println(this.gato[1][0] + "|" + this.gato[1][1] + "|" + this.gato[1][2]);
        System.out.println("-----");
        System.out.println(this.gato[2][0] + "|" + this.gato[2][1] + "|" + this.gato[2][2]);
    }
}