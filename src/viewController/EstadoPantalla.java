package viewController;

public enum EstadoPantalla {
    INICIO("Inicio"),
    JUEGO("Juego"),
    FIN("Fin"),
    BOSS("Boss");



    private final String cardName;

    EstadoPantalla(String cardName) {
        this.cardName = cardName;
    }

    //**
    // Para el cardLayout
    //  */
    public String getCardName() {
        return cardName;
    }
}