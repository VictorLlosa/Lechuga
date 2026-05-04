package model.StatePartida;

import model.Entidad.Balas.ListaBalas;
import model.Entidad.Enemigos.ListaEnemigos;
import model.Entidad.Naves.ListaNaves;
import model.Espacio;
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
                if (gestorPartida.contadorAcciones % 1 == 0) { // 10 ms
                    ListaBalas.getListaBalas().moverBalas();
                }
                if(gestorPartida.contadorAcciones % 3 == 0) { // 30 ms
                    gestorPartida.cambiarPantalla(TipoEventoJuego.REPAINT);
                }

                if (gestorPartida.contadorAcciones % 9 == 0) { // 90 ms
                    ListaEnemigos.getListaEnemigos().disparar();
                    ListaEnemigos.getListaEnemigos().moverTodosEnemigos();
                }
                if (gestorPartida.contadorAcciones % 100 == 0) { //1seg
                    ListaEnemigos.getListaEnemigos().toggleMovimiento(TipoEnemigo.boss1);
                }
                if(gestorPartida.contadorAcciones % 200 == 0) { //2 seg
                    ListaEnemigos.getListaEnemigos().toggleMovimiento(TipoEnemigo.boss2);
                    ListaEnemigos.getListaEnemigos().spawnearMinions(TipoEnemigo.boss1, TipoEnemigo.normal);
                    gestorPartida.contadorAcciones = 0; // reset contador para evitar overflow a largo plazo
                }
                if(gestorPartida.contadorAcciones % 500 == 0) { //5seg
                    ListaEnemigos.getListaEnemigos().spawnearMinions(TipoEnemigo.boss2, TipoEnemigo.boss1);
                    gestorPartida.contadorAcciones = 0; // reset contador para evitar overflow a largo plazo
                }
                break;

        }
    }
}
