public class botFacil extends Jugador{
    public botFacil(char simbolo) {
        super(simbolo);
        setNombre("botFacil");
    }
    
    @Override
    public Gato seleccionarCuadranteDeJuego(Cuadrantes cuadrantes) {
        int x=0;
        int y=0;
        boolean bandera=false;
        while(!bandera){
            x = (int) (Math.random() * 3);
            y = (int) (Math.random() * 3);
            if(cuadrantes.getCuadrante(x, y).getEstado().equals("libre")){ //si las coordenadas son validas
                bandera=true;
            }
        }
        Gato gato = cuadrantes.getCuadrante(x,y);
        this.setFila(x);
        this.setColumna(y);
        return gato;
    }

    @Override
    public boolean hacerSeleccion(Gato gato){
        try {
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println(e);
            }
        // Selecciona una casilla aleatoria y se escoje cuando sean validas
        int fila=0; 
        int columna=0;
        boolean bandera=false; //siendo falsa es que la casilla no es valida
        while(!bandera){
            fila= (int) (Math.random() * 3);
            columna= (int) (Math.random() * 3);
            bandera=gato.verificarCasillaOcupada(fila, columna);//si la casilla es valida se saldra del ciclo
        }
        this.setFila(fila);
        this.setColumna(columna);
        gato.marcarSeleccion(fila, columna, getSimbolo()); //marca el simbolo en la casilla valida
        return false;
    }
}