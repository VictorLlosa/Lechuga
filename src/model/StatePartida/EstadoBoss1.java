package model.StatePartida;

import model.Enemigos.ListaEnemigos;
import model.Espacio;
import model.Naves.ListaNaves;
import model.Tipos.TipoEntidad;
import model.Tipos.TipoEventoJuego;

public class EstadoBoss1 implements EstadoPartida {

    @Override
    public void loopJuego(GestorPartida gestorPartida) {
        TipoEventoJuego estadoAct = gestorPartida.esFinPartida();
        switch (estadoAct){
            case TipoEventoJuego.GANADO:
                ListaEnemigos.getListaEnemigos().anadirEnemigo(100, 20, TipoEntidad.boss2);
                gestorPartida.cambiarEstado(new EstadoBoss2());
                break;
            case TipoEventoJuego.PERDIDO:
                gestorPartida.detenerGameTimer();
                gestorPartida.cambiarEstado(new EstadoFase1());
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
