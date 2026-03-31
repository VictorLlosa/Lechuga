package model;

import model.Composite.CompositeCoordenada;
import model.Composite.Coordenada;
import model.Composite.Pixel;
import model.Strategy.*;

import java.util.ArrayList;
import java.util.Observable;

/**
 * id Empieza en 0, es un atributo autoincremental
 */
public abstract class NaveAbstracta extends Observable {

    private CompositeCoordenada coord;
    private Pixel cannon;
    private static int contadorId = 0; // Contador global para IDs
    private int id; // ID único de cada instancia
    private DisparoStrategy disparo;
    private ListaBalas listaBalas;
    private boolean muerta;

    protected NaveAbstracta(int pX, int pY){
        this.disparo = new DisparoPixel();
        this.id = contadorId++; // Asignar ID único y luego incrementar el contador
        coord = new CompositeCoordenada();
        crearForma(pX, pY);
        listaBalas = new ListaBalas();
        muerta = false;
    }

    /**
     * Entra las coordenadas del centro
     * @param cX
     * @param cY
     */
    private void crearForma(int cX, int cY) {
        cannon = new Pixel(cX, cY); //cannon de la nave
        coord.addComponent(new Pixel(cX,cY)); //centro
        coord.addComponent(new Pixel(cX,cY-1)); //arriba (tb el cannon)
        cannon = new Pixel(cX,cY-1); //cannon de la nave
        coord.addComponent(new Pixel(cX-1,cY)); //izq
        coord.addComponent(new Pixel(cX+1,cY)); //derecha


        /* ESTO ES LA FORMA DE LA BALA ROMBO, NO BORRAR
        coord.addComponent(new Pixel(cX,cY)); //centro
        coord.addComponent(new Pixel(cX,cY-1)); //arriba
        coord.addComponent(new Pixel(cX-1,cY)); //izq
        coord.addComponent(new Pixel(cX-1,cY-1)); //esquina noroeste
        coord.addComponent(new Pixel(cX,cY+1)); //abajo
        coord.addComponent(new Pixel(cX+1,cY)); //derecha
        coord.addComponent(new Pixel(cX+1,cY-1)); //esquina noreste
        coord.addComponent(new Pixel(cX+1,cY+1)); //esquina sudeste
        coord.addComponent(new Pixel(cX-1,cY+1)); //esquina suroeste
        coord.addComponent(new Pixel(cX,cY-2)); //punta superior
        coord.addComponent(new Pixel(cX,cY+2)); //punta inferior
        coord.addComponent(new Pixel(cX-2,cY)); //punta izquierda
        coord.addComponent(new Pixel(cX+2,cY)); //punta derecha
        */
    }

    protected void setCannon(int pX, int pY) {
        cannon.setCoord(pX,pY);
    }

    /*
    protected void setCoord(int pX, int pY) {
        coord.setCoord(pX,pY);
    }
    */


    protected CompositeCoordenada getCoord() {
        return coord;
    }

    protected Coordenada getCannon() {
        return cannon;
    }

    /**
     * para disparar, tenemos que actualizar el cannon antes. hacemos "disparo.disparar(coords del disparo) y anadimos la Bala a la Lista de Balas.
     * @return
     */
    public CompositeCoordenada disparar(){
        CompositeCoordenada coordBala = disparo.disparar(cannon.getX(), cannon.getY()); //TODO el disparar de cada nave
        listaBalas.anadirBala(coordBala);
        return coordBala;
    }

    public boolean tienesId(int idNave) {
        return this.id == idNave;
    }
    public boolean estasEn(int cX, int cY) {
        return coord.equals(new Pixel(cX,cY));
    }

    public void changeStrategy(DisparoStrategy pSt){
        this.disparo = pSt;
    }

    /**
     * Llama a listaBalas.moverBalas()
     */
    public void moverBalas(){listaBalas.moverBalas();}

    public ArrayList<Pixel> getCoordBalas() { return listaBalas.getCoordBalas();}

    public void borrarBalas() {
        listaBalas.borrarListaBalas();
    }

    public int getId() {
        return id; // Devolver el ID único de la instancia
    }

    public void matar() {
        muerta = true;
    }

    /**
     *
     * @return Devuelve el valor del atributo "muerta"
     */
    public boolean estaMuerta() {
        return muerta;
    }

    public void reiniciarContadorNaves() {
        contadorId = 0; // Reiniciar el contador global
    }

    /**
     * Solo elimina la bala si la nave la tiene. Lo usamos en ListaNaves (eliminarbala por coord)
     * @param cX
     * @param cY
     */
    public void eliminarBalaPorCoord(int cX,int cY){
        if(tieneBalaEnCoord(cX,cY)) listaBalas.eliminarBalaEn(cX, cY);
    }

    /**
     * Se usa en eliminarBalaPorCoord(x,y)
     * @param cX
     * @param cY
     * @return
     */
    public boolean tieneBalaEnCoord(int cX, int cY){
       return listaBalas.existeBalaEn(cX, cY);
    }

    /**
     * Controlador llama a este metodo cuando se pulsa un boton para mover la nave.
     * WIP ahora el CompositeCoordenada debe llamar a Espacio
     * @param dx
     * @param dy
     */
    public void actualizarCoord(int dx, int dy) {
        Espacio.getEspacio().vaciarCasillas(coord); //TODO esto hay que quitarlo por el metodo moverNave que usa el de Composite
        coord.actualizarCoord(dx,dy);
        if(!Espacio.getEspacio().esCoordenadaValida(coord)){
            coord.actualizarCoord(-dx,-dy);
            Espacio.getEspacio().ponerNaveCasillas(coord);
        }else{
            Espacio.getEspacio().ponerNaveCasillas(coord);
        }

    }

    public CompositeCoordenada getForma() {
        return coord;
    }

    /**
     * WIP; llama al metodo moverNave de Composite
     * @param dx
     * @param dy
     */
    public void moverNave(int dx, int dy) {
        coord.moverNave(dx,dy);
    }
}