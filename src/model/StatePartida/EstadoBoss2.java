package model.StatePartida;

import model.Enemigos.ListaEnemigos;
import model.Espacio;
import model.Naves.ListaNaves;
import model.Tipos.TipoEntidad;
import model.Tipos.TipoEventoJuego;

public class EstadoBoss2 implements EstadoPartida {
    @Override
    public void loopJuego(GestorPartida gestorPartida) {
        TipoEventoJuego estadoAct = gestorPartida.esFinPartida();
        switch (estadoAct){
            case TipoEventoJuego.GANADO:
                gestorPartida.cambiarEstado(new EstadoFase1()); //volvemos al estado incial
                gestorPartida.detenerGameTimer();
                gestorPartida.cambiarPantalla(TipoEventoJuego.GANADO);
                break;
            case TipoEventoJuego.PERDIDO:
                gestorPartida.cambiarEstado(new EstadoFase1());
                gestorPartida.detenerGameTimer();
                gestorPartida.cambiarPantalla(TipoEventoJuego.PERDIDO);
                break;
            default:
                gestorPartida.contadorAcciones++; //suma 1 cada 10 ms
                if(gestorPartida.contadorAcciones == 3) { // 30 ms
                    gestorPartida.cambiarPantalla(TipoEventoJuego.REPAINT);
                }
                // mover balas y mover enemigos con su respectivo contador para controlar velocidad de movimiento
                if (gestorPartida.contadorAcciones == 5) { // 50 ms
                    ListaNaves.getListaNaves().moverBalas();
                }
                if (gestorPartida.contadorAcciones == 20) { // 200 ms
                    ListaEnemigos.getListaEnemigos().moverEnemigos();
                    gestorPartida.contadorAcciones = 0; // reset contador para evitar overflow a largo plazo
                }
                break;

        }
    }
}
