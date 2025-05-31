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

    public void bloquearCuadrante(int fila, int columna){ //necesario? [Cristian: Maybe maybe]
        Gato gato = cuadrantes[fila][columna];
        int espaciosVacios = 0;

        // Check X, O y libres horizontales
        for(int i=0; i<3; i++){
            if(String.valueOf(gato.getGato()[i][0]).equals("X") && 
                String.valueOf(gato.getGato()[i][1]).equals("X") && 
                String.valueOf(gato.getGato()[i][2]).equals("X")){
                cuadrantes[fila][columna].setEstado("X");
            }
            if(String.valueOf(gato.getGato()[i][0]).equals("O") && 
                String.valueOf(gato.getGato()[i][1]).equals("O") && 
                String.valueOf(gato.getGato()[i][2]).equals("O")){
                cuadrantes[fila][columna].setEstado("O");
            }
            if(String.valueOf(gato.getGato()[i][0]).equals(" ") || 
                String.valueOf(gato.getGato()[i][1]).equals(" ") || 
                String.valueOf(gato.getGato()[i][2]).equals(" ")){
                espaciosVacios += 1;
            }
        }

        // Check X y O verticales
        for(int j=0; j<3; j++){
            if(String.valueOf(gato.getGato()[0][j]).equals("X") && 
                String.valueOf(gato.getGato()[1][j]).equals("X") && 
                String.valueOf(gato.getGato()[2][j]).equals("X")){
                cuadrantes[fila][columna].setEstado("X");
            }
            if(String.valueOf(gato.getGato()[0][j]).equals("O") && 
                String.valueOf(gato.getGato()[1][j]).equals("O") && 
                String.valueOf(gato.getGato()[2][j]).equals("O")){
                cuadrantes[fila][columna].setEstado("O");
            }
        }

        // Check diagonal principal
        if(String.valueOf(gato.getGato()[0][0]).equals("X") && 
            String.valueOf(gato.getGato()[1][1]).equals("X") && 
            String.valueOf(gato.getGato()[2][2]).equals("X")){
            cuadrantes[fila][columna].setEstado("X");
        }
        if(String.valueOf(gato.getGato()[0][0]).equals("O") && 
            String.valueOf(gato.getGato()[1][1]).equals("O") && 
            String.valueOf(gato.getGato()[2][2]).equals("O")){
            cuadrantes[fila][columna].setEstado("O");
        }

        // Check diagonal secundaria
        if(String.valueOf(gato.getGato()[0][2]).equals("X") && 
            String.valueOf(gato.getGato()[1][1]).equals("X") && String.valueOf(gato.getGato()[2][0]).equals("X")){
            cuadrantes[fila][columna].setEstado("X");
        }
        if(String.valueOf(gato.getGato()[0][2]).equals("O") && 
            String.valueOf(gato.getGato()[1][1]).equals("O") && String.valueOf(gato.getGato()[2][0]).equals("O")){
            cuadrantes[fila][columna].setEstado("O");
        }

        // Si no hay espacios vacíos, es empate
        if(espaciosVacios==0){
            cuadrantes[fila][columna].setEstado("empate");
        }
        // Si nada se cumple se queda libre
    }

    public boolean hayCuadrantesLibres(){ //chequea si hay cuadrantes libres
        boolean bandera=false;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(this.cuadrantes[i][j].getEstado().equals("libre")){ //hay a lo menos un cuadrante libre
                    mostrarCoordsCuadrantesLibres(i, j);
                    bandera=true;
                }
            }
        }
        return bandera; //no hay cuadrantes libres
    }

    public void mostrarCoordsCuadrantesLibres(int x, int y){// mostrar al usuario las coordenadas de los cuadrantes libres
        System.out.println("Coordenadas de los cuadrantes libres: "+x+1+","+y+1);
    }

    public void terminarJuego(){ //termina el juego
        //logica de terminar juego
    }

    public Gato getCuadrante(int fila, int columna){
        return this.cuadrantes[fila][columna];
    }
}