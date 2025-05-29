public class VistaEnConsola implements Observador{
    public VistaEnConsola() {
        // Constructor de la clase VistaEnConsola
    }

    @Override
    public void actualizar(Cuadrantes cuadrantes){ //mostrar en pantalla todos los gatos
        System.out.print(" " + cuadrantes.getCuadrante(0,0).getGato()[0][0] + " | " + cuadrantes.getCuadrante(0,0).getGato()[0][1] + " | " + cuadrantes.getCuadrante(0,0).getGato()[0][2] + "  || ");
        System.out.print(" " + /*cuadrantes.getCuadrante(0,1).getGato()[0][0]*/"X" + " | " + cuadrantes.getCuadrante(0,1).getGato()[0][1]+ " | " + cuadrantes.getCuadrante(0,1).getGato()[0][2] + "  || ");
        System.out.println(" " + cuadrantes.getCuadrante(0,2).getGato()[0][0] + " | " + cuadrantes.getCuadrante(0,2).getGato()[0][1] + " | " + cuadrantes.getCuadrante(0,2).getGato()[0][2] + " ");
        System.out.println("___|___|___ || ___|___|___ || ___|___|___");
        System.out.println("   |   |    ||    |   |    ||    |   |   ");
        System.out.print(" " + cuadrantes.getCuadrante(0,0).getGato()[1][0] + " | " + cuadrantes.getCuadrante(0,0).getGato()[1][1] + " | " + cuadrantes.getCuadrante(0,0).getGato()[1][2] + "  || ");
        System.out.print(" " + cuadrantes.getCuadrante(0,1).getGato()[1][0] + " | " + cuadrantes.getCuadrante(0,1).getGato()[1][1] + " | " + cuadrantes.getCuadrante(0,1).getGato()[1][2] + "  || ");
        System.out.println(" " + cuadrantes.getCuadrante(0,2).getGato()[1][0] + " | " + /*cuadrantes.getCuadrante(0,2).getGato()[1][1]*/"O" + " | " + cuadrantes.getCuadrante(0,2).getGato()[1][2] + " ");
        System.out.println("___|___|___ || ___|___|___ || ___|___|___");
        System.out.println("   |   |    ||    |   |    ||    |   |   ");
        System.out.print(" " + /*cuadrantes.getCuadrante(0,0).getGato()[2][0]*/"O" + " | " + cuadrantes.getCuadrante(0,0).getGato()[2][1] + " | " + cuadrantes.getCuadrante(0,0).getGato()[2][2] + "  || ");
        System.out.print(" " + cuadrantes.getCuadrante(0,1).getGato()[2][0] + " | " + cuadrantes.getCuadrante(0,1).getGato()[2][1] + " | " + cuadrantes.getCuadrante(0,1).getGato()[2][2] + "  || ");
        System.out.println(" " + cuadrantes.getCuadrante(0,2).getGato()[2][0] + " | " + cuadrantes.getCuadrante(0,2).getGato()[2][1] + " | " + cuadrantes.getCuadrante(0,2).getGato()[2][2] + " ");
        System.out.println("=========== || =========== || ===========");
        System.out.print(" " + cuadrantes.getCuadrante(1,0).getGato()[0][0] + " | " + cuadrantes.getCuadrante(1,0).getGato()[0][1] + " | " + cuadrantes.getCuadrante(1,0).getGato()[0][2] + "  || ");
        System.out.print(" " + cuadrantes.getCuadrante(1,1).getGato()[0][0] + " | " + cuadrantes.getCuadrante(1,1).getGato()[0][1]+ " | " + cuadrantes.getCuadrante(1,1).getGato()[0][2] + "  || ");
        System.out.println(" " + cuadrantes.getCuadrante(1,2).getGato()[0][0] + " | " + cuadrantes.getCuadrante(1,2).getGato()[0][1] + " | " + cuadrantes.getCuadrante(1,2).getGato()[0][2] + " ");
        System.out.println("___|___|___ || ___|___|___ || ___|___|___");
        System.out.println("   |   |    ||    |   |    ||    |   |   ");
        System.out.print(" " + cuadrantes.getCuadrante(1,0).getGato()[1][0] + " | " + cuadrantes.getCuadrante(1,0).getGato()[1][1] + " | " + cuadrantes.getCuadrante(1,0).getGato()[1][2] + "  || ");
        System.out.print(" " + cuadrantes.getCuadrante(1,1).getGato()[1][0] + " | " + cuadrantes.getCuadrante(1,1).getGato()[1][1] + " | " + cuadrantes.getCuadrante(1,1).getGato()[1][2] + "  || ");
        System.out.println(" " + cuadrantes.getCuadrante(1,2).getGato()[1][0] + " | " + cuadrantes.getCuadrante(1,2).getGato()[1][1] + " | " + cuadrantes.getCuadrante(1,2).getGato()[1][2] + " ");
        System.out.println("___|___|___ || ___|___|___ || ___|___|___");
        System.out.println("   |   |    ||    |   |    ||    |   |   ");
        System.out.print(" " + cuadrantes.getCuadrante(1,0).getGato()[2][0] + " | " + cuadrantes.getCuadrante(1,0).getGato()[2][1] + " | " + cuadrantes.getCuadrante(1,0).getGato()[2][2] + "  || ");
        System.out.print(" " + cuadrantes.getCuadrante(1,1).getGato()[2][0] + " | " + cuadrantes.getCuadrante(1,1).getGato()[2][1] + " | " + cuadrantes.getCuadrante(1,1).getGato()[2][2] + "  || ");
        System.out.println(" " + cuadrantes.getCuadrante(1,2).getGato()[2][0] + " | " + cuadrantes.getCuadrante(1,2).getGato()[2][1] + " | " + cuadrantes.getCuadrante(1,2).getGato()[2][2] + " ");
        System.out.println("=========== || =========== || ===========");
        System.out.print(" " + cuadrantes.getCuadrante(2,0).getGato()[0][0] + " | " + cuadrantes.getCuadrante(2,0).getGato()[0][1] + " | " + cuadrantes.getCuadrante(2,0).getGato()[0][2] + "  || ");
        System.out.print(" " + cuadrantes.getCuadrante(2,1).getGato()[0][0] + " | " + cuadrantes.getCuadrante(2,1).getGato()[0][1]+ " | " + cuadrantes.getCuadrante(2,1).getGato()[0][2] + "  || ");
        System.out.println(" " + cuadrantes.getCuadrante(2,2).getGato()[0][0] + " | " + cuadrantes.getCuadrante(2,2).getGato()[0][1] + " | " + cuadrantes.getCuadrante(2,2).getGato()[0][2] + " ");
        System.out.println("___|___|___ || ___|___|___ || ___|___|___");
        System.out.println("   |   |    ||    |   |    ||    |   |   ");
        System.out.print(" " + cuadrantes.getCuadrante(2,0).getGato()[1][0] + " | " + cuadrantes.getCuadrante(2,0).getGato()[1][1] + " | " + cuadrantes.getCuadrante(2,0).getGato()[1][2] + "  || ");
        System.out.print(" " + cuadrantes.getCuadrante(2,1).getGato()[1][0] + " | " + cuadrantes.getCuadrante(2,1).getGato()[1][1] + " | " + cuadrantes.getCuadrante(2,1).getGato()[1][2] + "  || ");
        System.out.println(" " + cuadrantes.getCuadrante(2,2).getGato()[1][0] + " | " + cuadrantes.getCuadrante(2,2).getGato()[1][1] + " | " + cuadrantes.getCuadrante(2,2).getGato()[1][2] + " ");
        System.out.println("___|___|___ || ___|___|___ || ___|___|___");
        System.out.println("   |   |    ||    |   |    ||    |   |   ");
        System.out.print(" " + cuadrantes.getCuadrante(2,0).getGato()[2][0] + " | " + cuadrantes.getCuadrante(2,0).getGato()[2][1] + " | " + cuadrantes.getCuadrante(2,0).getGato()[2][2] + "  || ");
        System.out.print(" " + cuadrantes.getCuadrante(2,1).getGato()[2][0] + " | " + cuadrantes.getCuadrante(2,1).getGato()[2][1] + " | " + cuadrantes.getCuadrante(2,1).getGato()[2][2] + "  || ");
        System.out.println(" " + cuadrantes.getCuadrante(2,2).getGato()[2][0] + " | " + cuadrantes.getCuadrante(2,2).getGato()[2][1] + " | " + cuadrantes.getCuadrante(2,2).getGato()[2][2] + " ");
    }
}