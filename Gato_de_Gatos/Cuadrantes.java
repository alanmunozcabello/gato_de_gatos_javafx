public class Cuadrantes{
    Gato cuadrantes[][];

    public Cuadrantes(){
        this.cuadrantes = new Gato[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                this.cuadrantes[i][j] = new Gato();
            }
        }
    }

    public void bloquearCuadrante(int fila, int columna){
        Gato gato = cuadrantes[fila][columna];
        char[][] tablero = gato.getGato();
        boolean bloqueado = false;
        
        // Chequea filas y columnas
        for(int i=0; i<3; i++){
            if(tablero[i][0] == 'X' && tablero[i][1] == 'X' && tablero[i][2] == 'X'){
                cuadrantes[fila][columna].setEstado("X");
                bloqueado = true;
            }
            if(tablero[i][0] == 'O' && tablero[i][1] == 'O' && tablero[i][2] == 'O'){
                cuadrantes[fila][columna].setEstado("O");
                bloqueado = true;
            }
            if(tablero[0][i] == 'X' && tablero[1][i] == 'X' && tablero[2][i] == 'X'){
                cuadrantes[fila][columna].setEstado("X");
                bloqueado = true;
            }
            if(tablero[0][i] == 'O' && tablero[1][i] == 'O' && tablero[2][i] == 'O'){
                cuadrantes[fila][columna].setEstado("O");
                bloqueado = true;
            }
        }
    
        // Chequea diagonales
        if(tablero[0][0] == 'X' && tablero[1][1] == 'X' && tablero[2][2] == 'X'){
            cuadrantes[fila][columna].setEstado("X");
            bloqueado = true;
        }
        if(tablero[0][0] == 'O' && tablero[1][1] == 'O' && tablero[2][2] == 'O'){
            cuadrantes[fila][columna].setEstado("O");
            bloqueado = true;
        }
        if(tablero[0][2] == 'X' && tablero[1][1] == 'X' && tablero[2][0] == 'X'){
            cuadrantes[fila][columna].setEstado("X");
            bloqueado = true;
        }
        if(tablero[0][2] == 'O' && tablero[1][1] == 'O' && tablero[2][0] == 'O'){
            cuadrantes[fila][columna].setEstado("O");
            bloqueado = true;
        }
    
        // Si no está bloqueado y no hay espacios vacíos, es empate
        int espaciosVacios = 0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(tablero[i][j] == ' '){
                    espaciosVacios++;
                }
            }
        }
        if(espaciosVacios == 0 && !bloqueado){
            cuadrantes[fila][columna].setEstado("empate");
        }
    }

    public boolean hayCuadrantesLibres(){ //chequea si hay cuadrantes libres
        boolean bandera=false;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                System.out.println(this.cuadrantes[i][j].getEstado() + " " + i + "," + j);
                if(this.cuadrantes[i][j].getEstado().equals("libre")){ //hay a lo menos un cuadrante libre
                    mostrarCoordsCuadrantesLibres(i, j);
                    bandera=true;
                }
            }
        }
        return bandera; //no hay cuadrantes libres
    }

    public void mostrarCoordsCuadrantesLibres(int x, int y){// mostrar al usuario las coordenadas de los cuadrantes libres
        System.out.println("Coordenadas de los cuadrantes libres: "+(x+1)+","+(y+1));
    }

    public void terminarJuego(){ //termina el juego
        //logica de terminar juego
    }

    public Gato getCuadrante(int fila, int columna){
        return this.cuadrantes[fila][columna];
    }
}