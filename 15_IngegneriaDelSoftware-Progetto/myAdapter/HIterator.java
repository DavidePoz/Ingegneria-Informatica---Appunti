package myAdapter;

import java.util.NoSuchElementException;

/**
 * Un iteratore su una collezione. 
 * {@code HIterator} sostituisce {@code Enumeration} nel framework
 * delle collezioni di Java. 
 * Gli iteratori si differenziano dalle enumerazioni in due modi:
 * <ul>
 * <li>Gli iteratori consentono al chiamante di rimuovere elementi dalla collezione sottostante
 * durante l'iterazione con una semantica ben definita.</li>
 * <li>I nomi dei metodi sono stati migliorati.</li>
 * </ul>
 */
public interface HIterator {
    /**
     * Restituisce {@code true} se l'iterazione ha altri elementi. (In altre parole, restituisce
     * {@code true} se {@code next()} restituirebbe un elemento piuttosto che lanciare
     * un'eccezione).
     * 
     * @return {@code true} se l'iteratore ha altri elementi.
     */
    boolean hasNext();

    /**
     * Restituisce l'elemento successivo nell'iterazione.
     *
     * @return l'elemento successivo nell'iterazione.
     * @throws NoSuchElementException se l'iterazione non ha più elementi.
     */
    Object next();

    /**
     * Rimuove dalla collezione sottostante l'ultimo elemento restituito dall'iteratore (operazione
     * opzionale). Questo metodo può essere chiamato solo una volta per ogni chiamata a
     * {@code next()}. Il comportamento di un iteratore non è specificato se la collezione
     * sottostante viene modificata durante l'iterazione in qualsiasi modo diverso dalla chiamata a
     * questo metodo.
     *
     * @throws MyUnsupportedOperationException se {@code remove} non è supportato da questo iteratore.
     * @throws IllegalStateException se il metodo {@code next()} non è stato ancora chiamato, o se
     *         il metodo {@code remove()} è già stato chiamato dopo l'ultima chiamata al metodo
     *         {@code next()}.
     */
    void remove();
}
