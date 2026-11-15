package myAdapter;

import java.util.EmptyStackException;

/**
 * Adapter per {@code java.util.Stack} di J2SE 1.4.2 che utilizza come adaptee
 * {@code java.util.Vector} di CLDC 1.1 tramite {@link VectorAdapter}.
 * <p>
 * La classe {@code Stack} rappresenta uno stack di oggetti di tipo
 * last-in-first-out (LIFO). Estende la classe {@code Vector} con cinque
 * operazioni che permettono di trattare un vettore come uno stack. Vengono
 * fornite le usuali operazioni di {@code push} e {@code pop}, così come un
 * metodo per osservare l'elemento in cima allo stack ({@code peek}), un metodo
 * per verificare se lo stack è vuoto ({@code empty}), e un metodo per cercare
 * un elemento nello stack e scoprire la sua distanza dalla cima
 * ({@code search}).
 * <p>
 * Quando uno stack viene creato per la prima volta, non contiene alcun
 * elemento.
 *
 * @see VectorAdapter
 * @see AbstractHList
 */
public class StackAdapter extends VectorAdapter {

    // ====================================================================
    // Costruttore
    // ====================================================================

    /**
     * Crea uno {@code StackAdapter} vuoto.
     * <p>
     * Inizialmente non contiene
     * alcun elemento; la capacità interna è quella di default di
     * {@code Vector} (10).
     */
    public StackAdapter() {
        super();
    }

    // ====================================================================
    // Operazioni Stack J2SE 1.4.2
    // ====================================================================

    /**
     * Inserisce un elemento in cima a questo stack.
     * <p>
     * Ha esattamente lo stesso effetto di
     * {@link VectorAdapter#addElement(Object) addElement(item)}: aggiunge
     * l'elemento alla fine del vettore (che corrisponde alla cima dello
     * stack) e ne aumenta la dimensione di uno, espandendo la capacità se
     * necessario.
     *
     * @param item l'elemento da inserire in cima allo stack
     * @return l'elemento passato come argomento {@code item}
     * @see VectorAdapter#addElement(Object)
     */
    public Object push(Object item) {
        addElement(item);
        return item;
    }

    /**
     * Rimuove l'oggetto in cima a questo stack e restituisce quell'oggetto
     * come valore di questa funzione.
     * <p>
     * Rimuove l'ultimo elemento del vettore (indice {@code size()-1}) e lo
     * restituisce. 
     *
     * @return l'oggetto in cima a questo stack (l'ultimo elemento del vettore)
     * @throws EmptyStackException se questo stack è vuoto
     */
    public Object pop() {
        if (size() == 0) throw new EmptyStackException();
        return remove(size() - 1);
    }

    /**
     * Esamina l'oggetto in cima a questo stack senza rimuoverlo.
     * <p>
     * Restituisce l'ultimo elemento del vettore (indice {@code size()-1})
     * senza modificarlo. 
     *
     * @return l'oggetto in cima a questo stack (l'ultimo elemento del vettore)
     * @throws EmptyStackException se questo stack è vuoto
     */
    public Object peek() {
        if (size() == 0) throw new EmptyStackException();
        return get(size() - 1);
    }

    /**
     * Verifica se questo stack è vuoto.
     * <p>
     * Restituisce {@code true} se e solo se questo stack non contiene elementi.
     * Equivale a {@code isEmpty()} o {@code size()==0}.
     *
     * @return {@code true} se e solo se questo stack non contiene elementi;
     *         {@code false} altrimenti
     */
    public boolean empty() {
        return isEmpty();
    }

    /**
     * Restituisce la posizione in cui si trova un oggetto
     * in questo stack. Se l'oggetto {@code o} è presente come elemento in
     * questo stack, questo metodo restituisce la distanza dalla cima dello
     * stack della prima occorrenza più vicina alla cima; l'elemento in cima
     * allo stack è considerato alla distanza {@code 1}. Il metodo
     * {@code equals} viene utilizzato per confrontare {@code o} con gli
     * elementi presenti nello stack.
     *
     * @param o l'oggetto desiderato
     * @return la distanza, con {@code 1} corrispondente al primo elemento, dalla cima dello stack dove si
     *         trova l'oggetto; il valore di ritorno {@code -1} indica che
     *         l'oggetto non è presente nello stack.
     */
    public int search(Object o) {
        int i = lastIndexOf(o);
        if (i >= 0) return size() - i;
        return -1;
    }
}
