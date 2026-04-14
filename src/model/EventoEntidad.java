package model;

import model.Tipos.TipoEntidad;

public class EventoEntidad {
    private TipoEntidad tipo;
    private int idEntidad;

    public EventoEntidad(TipoEntidad tipo, int idEntidad){
        this.tipo = tipo;
        this.idEntidad = idEntidad;
    }

    public TipoEntidad getTipo() { return tipo; }
    public int getIidEntidad() { return idEntidad; }
}
