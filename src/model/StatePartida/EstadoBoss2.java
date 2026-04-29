package model.StatePartida;

import model.Entidad.Balas.ListaBalas;
import model.Entidad.Enemigos.ListaEnemigos;
import model.Entidad.Naves.ListaNaves;
import model.MoverStrategy.MoverAbajo;
import model.MoverStrategy.MoverDerecha;
import model.MoverStrategy.MoverIzquierda;
import model.MoverStrategy.MoverStrategy;
import model.Tipos.TipoEnemigo;
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

                // mover balas y mover enemigos con su respectivo contador para controlar velocidad de movimiento
                if (gestorPartida.contadorAcciones % 2 == 0) { // 50 ms
                    ListaBalas.getListaBalas().moverBalas();
                }
                if(gestorPartida.contadorAcciones % 3 == 0) { // 30 ms
                    gestorPartida.cambiarPantalla(TipoEventoJuego.REPAINT);
                }

                if (gestorPartida.contadorAcciones % 10 == 0) { // 200 ms
                    ListaEnemigos.getListaEnemigos().disparar();
                    ListaEnemigos.getListaEnemigos().moverEnemigos();
                }
                if (gestorPartida.contadorAcciones % 100 == 0) { //1seg
                    ListaEnemigos.getListaEnemigos().toggleMovimiento(TipoEnemigo.boss2);
                    gestorPartida.contadorAcciones = 0; // reset contador para evitar overflow a largo plazo
                }
                break;

        }
    }
}
