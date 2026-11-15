package myAdapter;

/**
 * Eccezione lanciata per indicare che l'operazione richiesta non è supportata.
 * <p>
 * Sostituisce {@code java.lang.UnsupportedOperationException} di J2SE, non presente in CLDC 1.1.
 */
public class MyUnsupportedOperationException extends RuntimeException {

    /**
     * Costruisce una {@code MyUnsupportedOperationException} senza messaggio.
     */
    public MyUnsupportedOperationException() {
        super();
    }

    /**
     * Costruisce una {@code MyUnsupportedOperationException} con il messaggio specificato.
     *
     * @param message il messaggio di dettaglio.
     */
    public MyUnsupportedOperationException(String message) {
        super(message);
    }
}
