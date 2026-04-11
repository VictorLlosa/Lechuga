package model;

import model.Composite.Coordenada;
import model.Composite.Pixel;

public abstract class EnemigoAbstracto {

        private final Pixel coord;
        private boolean muerto;
        private static int contadorId = 0; // Contador global para IDs
        private int id; // ID único de cada instancia

        public EnemigoAbstracto (Pixel pCoord) {
            coord = pCoord;
            muerto = false;
            id = contadorId++;
        }

        public void setCoord (int pX, int pY) {
            this.coord.setCoord(pX, pY);
        }

        public void actualizarPos() {
            coord.actualizarCoord(0, 1);
        }

        public Pixel getCoord() {
            if (coord == null) return null;
            // Devolver una copia para evitar que terceros modifiquen la coordenada directamente
            return new Pixel(coord.getX(), coord.getY());
        }

        public boolean estaEn(Coordenada pCoord){
            return coord.equals(pCoord);
        }

        /**
         * Devuelve el valor del atributo "muerto"
         * @return
         */
        public boolean estaMuerto(){
            return this.muerto;
        }

        public void matar() {
            muerto = true;
        }

        public Integer getId() {
            return id;
        }
}

