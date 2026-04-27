package viewController;

import java.util.HashSet;

public class InputJugador {
    private HashSet<Accion> acciones = new HashSet<>();

    public void setAccion(Accion accion, boolean activa) {
        if (activa) acciones.add(accion);
        else acciones.remove(accion);
    }

    public boolean estaActiva(Accion accion) {
        return acciones.contains(accion);
    }

    public void reiniciar(){
        acciones.clear();
    }
}