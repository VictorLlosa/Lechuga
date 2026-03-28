package model.Strategy;

import model.Coordenada;

public class DisparoRombo implements DisparoStrategy {
    private final int numComp=13;
    private Coordenada centro;
    private Coordenada[] componentes = new Coordenada[numComp]; //distintas balas de 1 pixel que componen

    /**
     * A la constructora le entra la Coordenada del centro del disparo
     */
    public DisparoRombo(Coordenada c){
        this.centro = c;
        this.darFormaAlDisparo(c.getX(),c.getY());
    }

    @Override
    public Coordenada getCentro() {
        return this.centro;
    }

    @Override
    public Coordenada[] getComponentesDelDisparo() {
        return this.componentes;
    }

    @Override
    public void setCoord(int cX, int cY) {

    }

    /**
     * Establece la coordenada del centro y actualiza las demas con "darFormaAlDisparo"
     * (para que la bala este correctamente dibujada)
     * @param cX
     * @param cY
     */
    @Override
    public void setCentro(int cX, int cY) {
        this.centro= new Coordenada(cX,cY);
        this.darFormaAlDisparo(cX,cY);
    }

    /**
     * Dadas las coordenadas de un centro, el metodo se encarga de mover la bala correctamente
     * (de momento solo para hacer pruebas). NO cambia la coordenanda del atributo 'centro',
     * solo la del array de coordenadas
     * @param cX
     * @param cY
     */
    @Override
    public void darFormaAlDisparo(int cX, int cY){
        Coordenada nuevoCentro = new Coordenada(cX,cY);
        Coordenada[] nuevosComponentes = new Coordenada[this.numComp];
        nuevosComponentes[0]=new Coordenada(cX,cY);
        nuevosComponentes[1]=new Coordenada(cX,cY-1); //arriba
        nuevosComponentes[2]=new Coordenada(cX-1,cY); //izq
        nuevosComponentes[5]=new Coordenada(cX-1,cY-1); //esquina noroeste
        nuevosComponentes[4]=new Coordenada(cX,cY+1); //abajo
        nuevosComponentes[3]=new Coordenada(cX+1,cY); //derecha
        nuevosComponentes[6]=new Coordenada(cX+1,cY-1); //esquina noreste
        nuevosComponentes[7]=new Coordenada(cX+1,cY+1); //esquina sudeste
        nuevosComponentes[8]=new Coordenada(cX-1,cY+1); //esquina suroeste
        nuevosComponentes[9]=new Coordenada(cX,cY-2); //punta superior
        nuevosComponentes[10]=new Coordenada(cX,cY+2); //punta inferior
        nuevosComponentes[11]=new Coordenada(cX-2,cY); //punta izquierda
        nuevosComponentes[12]=new Coordenada(cX+2,cY); //punta derecha

        this.componentes=nuevosComponentes;
    }

    /**
     * Actualiza las coordenadas de todos los componentes de la bala (no comprueba si
     * son validas o no). Hay que actualizar la del centro en el centro y en el array de coordenadas.
     * Este metodo es igual en todas las balas compuestas.
     */
    @Override
    public void actualizarPos() {
        centro.setCoord(centro.getX(), centro.getY() - 1);
        for (int i=0;i<this.componentes.length;i++) {
            this.componentes[i].setCoord(centro.getX(), centro.getY() - 1);
        }
    }
}
