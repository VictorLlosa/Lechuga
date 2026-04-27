package model.StatePartida;

import model.Entidad.Enemigos.ListaEnemigos;
import model.Entidad.Naves.ListaNaves;
import model.Tipos.TipoEntidad;
import model.Tipos.TipoEventoJuego;

public class EstadoFase1 implements EstadoPartida {
    /**
     * Hace 1 loop por cada Estado de la Partida
     * @param gestorPartida
     */
    @Override
    public void loopJuego(GestorPartida gestorPartida) {
        TipoEventoJuego estadoAct = gestorPartida.esFinPartida();
        switch (estadoAct){
            case TipoEventoJuego.GANADO:
                ListaEnemigos.getListaEnemigos().anadirEnemigo(100, 20, TipoEntidad.boss1);
                gestorPartida.cambiarEstado(new EstadoBoss1());
            break;
            case TipoEventoJuego.PERDIDO:
                gestorPartida.detenerGameTimer();
                gestorPartida.cambiarEstado(new EstadoFase1());
                gestorPartida.cambiarPantalla(TipoEventoJuego.PERDIDO);
            break;
            default:
                gestorPartida.contadorAcciones++; //suma 1 cada 10 ms

                // mover balas y mover enemigos con su respectivo contador para controlar velocidad de movimiento
                if (gestorPartida.contadorAcciones % 2 == 0) { // 50 ms
                    ListaNaves.getListaNaves().moverBalas();
                }

                if(gestorPartida.contadorAcciones % 3 == 0) { // 30 ms
                    gestorPartida.cambiarPantalla(TipoEventoJuego.REPAINT);
                }

                if (gestorPartida.contadorAcciones % 10 == 0) { // 200 ms
                    ListaEnemigos.getListaEnemigos().moverEnemigos();
                    gestorPartida.contadorAcciones = 0; // reset contador para evitar overflow a largo plazo
                }
            break;

        }
        }
    }
