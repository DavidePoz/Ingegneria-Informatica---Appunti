package myAdapter;

import java.util.NoSuchElementException;

/**
 * <p>
 * Un iteratore per le liste che permette di attraversare la lista in entrambe le
 * direzioni, modificare la lista durante l'iterazione, e ottenere la posizione
 * corrente dell'iteratore nella lista. Un {@code HListIterator} non ha elemento corrente;
 * la posizione del suo cursore si trova sempre tra l'elemento che verrebbe restituito da una chiamata
 * a {@code previous()} e l'elemento che verrebbe restituito da una chiamata a {@code next()}.
 * In una lista di lunghezza {@code n}, ci sono {@code n+1} valori validi di indici, da {@code 0} 
 * a {@code n}, compresi.
 * </p>
 * <p>
 * Notare che i metodi {@code remove()} e {@code set(Object)} non sono definiti in termini della 
 * posizione del cursore; sono definiti per operare sull'ultimo elemento restituito 
 * da una chiamata a {@code next()} oppure {@code previous()}.
 * </p>
 */
public interface HListIterator extends HIterator {

    /**
     * Restituisce {@code true} se questo iteratore su lista ha altri elementi quando
     * attraversa la lista procedendo dall'inizio alla fine.
     * (In altre parole, restituisce {@code true} se {@code next} dovesse restituire un elemento
     * anzichè lanciare un'eccezione.)
     * 
     * @return true se l'iterazione ha un elemento successivo quando si attraversa la lista in avanti.
     */
    boolean hasNext();

    /**
     * Ritorna il successivo elemento nella lista.
     * Questo metodo può essere chiamato ripetutamente per iterare lungo la lista, oppure
     * essere alternato con chiamate a {@code previous} per muoversi avanti e indietro.
     * (Notare che alternare chiamate a {@code next} e {@code previous} restituirà lo stesso 
     * elemento ripetutamente.)
     * 
     * @return l'elemento successivo nella lista.
     * @throws NoSuchElementException Se non esiste l'elemento successivo nell'iterazione.
     */
    Object next();

    /**
     * Restituisce {@code true} se questo iteratore su lista ha altri elementi quando
     * attraversa la lista procedendo dalla fine verso l'inizio.
     * (In altre parole, restituisce {@code true} se {@code previous} dovesse restituire un elemento
     * anzichè lanciare un'eccezione.)
     * 
     * @return true se l'iterazione ha altri elementi quando si attraversa la lista all'indietro.
     */
    boolean hasPrevious();

    /**
     * Ritorna il precedente elemento nella lista.
     * Questo metodo può essere chiamato ripetutamente per iterare lungo la lista al contrario, oppure
     * essere alternato con chiamate a {@code next} per muoversi avanti e indietro.
     * (Notare che alternare chiamate a {@code next} e {@code previous} restituirà lo stesso 
     * elemento ripetutamente.)
     * 
     * @return l'elemento precedente nella lista.
     * @throws NoSuchElementException se l'iterazione non ha un elemento precedente.
     */
    Object previous();

    /**
     * Restituisce l'indice dell'elemento che sarebbe restituito da una successiva chiamata
     * a {@code next}.
     * (Restituisce la dimensione della lista se l'iteratore si trova alla fine della lista.)
     * 
     * @return l'indice dell'elemento che sarebbe restituito da una chiamata a {@code next}, oppure 
     *         la dimensione della lista se l'iteratore si trova alla fine della lista.
     */
    int nextIndex();

    /**
     * Ritorna l'indice dell'elemento che verrebbe restituito da una chiamata a {@code previous}.
     * (Restituisce -1 se l'iteratore si trova all'inizio della lista.)
     * 
     * @return l'indice dell'elemento che sarebbe ritornato da una chiamata a
     *         {@code previous()}, oppure -1 se l'itratore si trova all'inizio della lista.
     */
    int previousIndex();

    /**
     * Elimina l'ultimo elemento che è stato restituito dal metodo {@code next()} (operazione opzionale).
     * Questo metodo può essere chiamato solo una volta per ogni chiamata a {@code next()}
     * o {@code previous}.
     * La chiamata può essere effettuata solo se {@code HListIterator.add} non è stato chiamato
     * dopo l'ultima chiamata a {@code next} o {@code previous}.
     *
     * @throws MyUnsupportedOperationException se l'operazione non è supportata.
     * @throws IllegalStateException Se nè {@code next} nè {@code previous} sono stati chiamati, 
     *                               o se {@code remove} o {@code add} sono stati chiamati dopo l'ultima
     *                               chiamata a {@code next} o {@code previous}.
     */
    void remove();

    /**
     * Rimpiazza l'ultimo elemento restituito da {@code next()} o {@code previous()} 
     * con l'elemento specificato (operazione opzionale).
     * Una chiamata a questo metodo può essere effettuata solo se nè {@code HListIterator.remove} nè
     * {@code HListIterator.add} sono stati chiamati dopo l'ultima chiamata a {@code next} o {@code previous}.
     *
     * @param o L'oggetto con cui rimpiazzare l'elemento.
     * @throws MyUnsupportedOperationException se l'operazione non è supportatat dalla classe.
     * @throws ClassCastException se la classe dell'elemento specificato ne impedisce 
     *                            l'inserimento nella lista.
     * @throws IllegalArgumentException se qualche aspetto dell'elemento specificato ne impedisce 
     *                                  l'inserimento nella lista.
     * @throws IllegalStateException se nè {@code next()} nè {@code previous()} sono stati chiamati
     *                               in precedenza, oppure {@code remove} o {@code add} sono stati chiamati
     *                               dopo l'ultima chiamata a {@code next} o {@code previous}.
     */
    void set(Object o);

    /**
     * Aggiunge l'elemento specificato nella lista (operazione opzionale). 
     * L'elemento viene inserito immediatamente prima dell'elemento che verrebbe 
     * restituito dalla chiamata a {@code next()}, se presente, e immediatamente dopo l'elemento
     * che verrebbe ritornato dalla chiamata a {@code previous()}, se presente.
     * (Se la lista è vuota, l'elemento nuovo diventa l'unico elemento della lista).
     * Il nuovo elemento è inserito prima del cursore: una successiva chiamata a {@code next} non 
     * sarebbe influenzata, e una succcessiva chiamata a {@code previous} restituirebbe il nuovo elemento.
     * (Questa chiamata incrementa di uno il valore che sarebbe restituito da una chiamata a 
     * {@code nextIndex} o {@code previousIndex}.)
     *
     * @param o L'elemento da aggiungere alla lista.
     * @throws MyUnsupportedOperationException se l'operazione non è supportata.
     * @throws ClassCastException se la classe dell'elemento specificato ne impedisce l'inserimento
     *                            nella lista.
     * @throws IllegalArgumentException se qualche aspetto dell'elemento specificato ne impedisce 
     *                                  l'inserimento nella lista.
     */
    void add(Object o);
}
