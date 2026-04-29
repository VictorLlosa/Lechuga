package model.Tipos;

public enum TipoEntidad {
    nave,enemigo,boss1, boss2, bala, vacio;

    public boolean esEnemigo() {
        return this == enemigo || this == boss1 || this == boss2;
    }

    public boolean esUnaAmenazaParaLaNave() {
        return this == enemigo || this == boss1 || this == boss2 || this == bala;
    }
}
