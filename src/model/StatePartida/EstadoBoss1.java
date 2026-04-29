package model.StatePartida;

import model.Entidad.Balas.ListaBalas;
import model.Entidad.Enemigos.ListaEnemigos;
import model.Entidad.Naves.ListaNaves;
import model.Espacio;
import model.Tipos.TipoEnemigo;
import model.Tipos.TipoEntidad;
import model.Tipos.TipoEventoJuego;

public class EstadoBoss1 implements EstadoPartida {

    @Override
    public void loopJuego(GestorPartida gestorPartida) {
        TipoEventoJuego estadoAct = gestorPartida.esFinPartida();
        switch (estadoAct){
            case TipoEventoJuego.GANADO:
                ListaEnemigos.getListaEnemigos().anadirEnemigo(100, 20, TipoEnemigo.boss2);
                ListaEnemigos.getListaEnemigos().ponerEnemigosEnEspacio();
                gestorPartida.cambiarEstado(new EstadoBoss2());
                break;
            case TipoEventoJuego.PERDIDO:
                gestorPartida.detenerGameTimer();
                gestorPartida.cambiarEstado(new EstadoFase1());
                gestorPartida.cambiarPantalla(TipoEventoJuego.PERDIDO);
                break;
            default:
                gestorPartida.contadorAcciones++; //suma 1 cada 10 ms
                // mover balas y mover enemigos con su respectivo contador para controlar velocidad de movimiento
                if (gestorPartida.contadorAcciones % 2 == 0) { // 20 ms
                    ListaBalas.getListaBalas().moverBalas();
                }
                if(gestorPartida.contadorAcciones % 3 == 0) { // 30 ms
                    gestorPartida.cambiarPantalla(TipoEventoJuego.REPAINT);
                }

                if (gestorPartida.contadorAcciones % 10 == 0) { // 100 ms
                    ListaEnemigos.getListaEnemigos().moverEnemigos();
                    ListaEnemigos.getListaEnemigos().disparar();
                    gestorPartida.contadorAcciones = 0; // reset contador para evitar overflow a largo plazo
                }
                break;

        }
    }
}
