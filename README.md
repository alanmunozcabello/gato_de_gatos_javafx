Este es el repositorio publico de nuestro proyecto de programacion avanzada apodado como GATO DE GATOS

Un gato de gatos es un Tik-Tak-Toe pero que se juga de una manera iterativa.
El juego consiste en que 2 jugadores juegan al gato (tres en raya) sobre un tablero con 9 cuadrantes, donde cada cuadrante contiene un gato por separado.
Los jugadores toman turnos para jugar, en el primer turno comienza el jugador con la X, luego el jugador con la O y así sucesivamente.
  1. EL primer turno tiene la libertad de elegir cualquier cuadrante para comenzar la partida.
  2. El segundo turno será en el cuadrante con las mismas coordenadas que la casilla escogida por el primer turno.
  3. Si un jugador logra un tres en raya en un cuadrante este se bloquea y se le da un punto, o en caso contrario de que sea un empate tambien se bloquea pero nadie recibe nada.
  4. El ciclo continúa hasta que un jugador se rinda o hasta que no queden más cuadrantes libres para jugar.

tambien existen 3 modos de juego, jugador contra jugador, jugador contra bot facil (tira cosas al azar) y jugador contra bot dificil, el cual usa el algoritmo minimax para dar la mejor
jugada posible para el y la peor para el jugador contrario, que yo sepa es casi imposible ganarle en un tres en raya normal, pero tal vez no sea imposible en uno con más de un plano... no? 

Es una premisa simple (no el minimax) pero tal vez lleva más de un dolor de cabeza implementar.
