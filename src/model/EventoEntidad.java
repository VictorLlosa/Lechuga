package model;

import model.Tipos.TipoEntidad;

/**
 * Encapsula el tipo de Entidad y el ID como atributos. Tambien tiene booleano "label" que es TRUE si el evento en cuestion esta dirigido a un objeto de tipo JLabel o no (false)
 * Lo usamos en el update de
 */
public class EventoEntidad {
    private boolean cambio;
    private TipoEntidad tipo;
    private int idEntidad;

    /**
     * Esta constructora marca label como false por defecto
     * @param tipo
     * @param idEntidad
     */
    public EventoEntidad(TipoEntidad tipo, int idEntidad){
        this.cambio = true;
        this.tipo = tipo;
        this.idEntidad = idEntidad;
    }

    public EventoEntidad(){
        this.cambio = false;
        this.tipo = null;
        this.idEntidad = -1;
    }

    public TipoEntidad getTipo() { return tipo; }
    public int getIdEntidad() { return idEntidad; }
    public boolean getCambio(){ return cambio;}
}
