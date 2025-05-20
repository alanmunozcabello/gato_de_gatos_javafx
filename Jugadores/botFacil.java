public class botFacil extends Jugador {
    public botFacil(String nombre) {
        super(nombre);
    }

    @Override
    public void hacerSeleccion(Gato gato){
        // Selecciona una casilla aleatoria y se escoje cuando sean validas
        int fila, columna;
        boolean bandera=false; //siendo falsa es que la casilla no es valida
        while(!bandera){
            fila= (int) (Math.random() * 3)-1;
            columna= (int) (Math.random() * 3)-1;
            //verifica si la casilla es ocupada
            bandera=gato.verificarCasillaOcupada(fila, columna);//si la casilla es valida se saldra del ciclo
        }
        this.fila=fila;
        this.columna=columna;
        gato.marcarSimbolo(fila, columna, simbolo); //marca el simbolo en la casilla valida
    }
}