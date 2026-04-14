package model.Tipos;

import java.awt.Color;

public enum TipoNave {

    red(Color.RED),
    green(Color.GREEN),
    blue(Color.BLUE);

    private final Color color;

    TipoNave(Color pColor) {
        this.color = pColor;
    }

    public Color getColor() {
        return color;
    }
}
