public class botFacil extends Jugador {
    public botFacil(char simbolo) {
        super(simbolo);
    }

    @Override
    public void hacerSeleccion(Gato gato){
        dormir();
        // Selecciona una casilla aleatoria y se escoje cuando sean validas
        int fila=0; 
        int columna=0;
        boolean bandera=false; //siendo falsa es que la casilla no es valida
        while(!bandera){
            fila= (int) (Math.random() * 3)-1;
            columna= (int) (Math.random() * 3)-1;
            bandera=gato.verificarCasillaOcupada(fila, columna);//si la casilla es valida se saldra del ciclo
        }
        this.fila=fila;
        this.columna=columna;
        gato.marcarSeleccion(fila, columna, simbolo); //marca el simbolo en la casilla valida
    }
}