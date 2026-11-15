package myAdapter;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * Adapter per {@code java.util.Vector} di J2SE 1.4.2 che utilizza come adaptee
 * {@code java.util.Vector} di CLDC 1.1.
 * <p>
 * La classe {@code Vector} implementa un array di oggetti espandibile. Come un
 * array, contiene componenti accessibili tramite indice intero; tuttavia la
 * dimensione del {@code Vector} può crescere o ridursi per accogliere
 * l'aggiunta e la rimozione di elementi dopo la creazione. Ciascun
 * {@code Vector} cerca di ottimizzare la gestione dello spazio gestendo una
 * <i>capacità</i> e un <i>incremento di capacità</i>: la capacità è sempre
 * almeno pari alla dimensione; viene automaticamente aumentata quando
 * necessario. L'incremento specifica di quanto crescere ad ogni riallocazione;
 * se è zero o negativo la strategia di crescita raddoppia la capacità attuale.
 * <p>
 * Questo adapter estende {@link AbstractHList} per realizzare un'implementazione
 * di un vettore fedele a {@code Vector} di J2SE 1.4.2. 
 *
 * @see AbstractHList
 * @see HList
 * @see HCollection
 */
public class VectorAdapter extends AbstractHList {

    /** Adaptee CLDC 1.1 su cui l'implementazione è basata. */
    protected Vector vector;

    // ====================================================================
    // Costruttori 
    // ====================================================================

    /**
     * Costruisce un vettore vuoto cosicché il suo array interno abbia
     * dimensione 10 e il suo incremento di capacità sia zero.
     */
    public VectorAdapter() {
        vector = new Vector();
    }

    /**
     * Costruisce un vettore vuoto con la capacità iniziale specificata e con
     * incremento di capacità pari a zero.
     *
     * @param initialCapacity la capacità iniziale del vettore
     * @throws IllegalArgumentException se la capacità iniziale specificata è negativa
     */
    public VectorAdapter(int initialCapacity) {
        vector = new Vector(initialCapacity);
    }

    /**
     * Costruisce un vettore vuoto con la capacità iniziale e l'incremento di
     * capacità specificati.
     *
     * @param initialCapacity la capacità iniziale del vettore
     * @param capacityIncrement l'incremento di capacità quando il vettore cresce
     * @throws IllegalArgumentException se la capacità iniziale specificata è negativa
     */
    public VectorAdapter(int initialCapacity, int capacityIncrement) {
        vector = new Vector(initialCapacity, capacityIncrement);
    }

    /**
     * Costruisce un vettore contenente gli elementi della collezione
     * specificata, nell'ordine in cui sono restituiti dall'iteratore della
     * collezione.
     *
     * @param c la collezione i cui elementi devono essere posti in questo vettore
     * @throws NullPointerException se la collezione specificata è {@code null}
     */
    public VectorAdapter(HCollection c) {
        if (c == null) throw new NullPointerException();
        vector = new Vector();
        addAll(c);
    }

    // ====================================================================
    // Metodi AbstractHList
    // ====================================================================

    /**
     * Restituisce il numero di componenti in questo vettore.
     *
     * @return il numero di componenti in questo vettore
     */
    public int size() {
        return vector.size();
    }

    /**
     * Restituisce l'elemento alla posizione specificata in questo vettore.
     *
     * @param index indice dell'elemento da restituire
     * @return l'oggetto all'indice specificato
     * @throws ArrayIndexOutOfBoundsException se l'indice è fuori dai limiti
     *         ({@code index &lt; 0 || index &gt;= size()})
     */
    public Object get(int index) {
        if (index < 0 || index >= vector.size()) throw new ArrayIndexOutOfBoundsException();
        return vector.elementAt(index);
    }

    /**
     * Sostituisce l'elemento alla posizione specificata in questo vettore con
     * l'elemento specificato.
     *
     * @param index indice dell'elemento da sostituire
     * @param element elemento da memorizzare alla posizione specificata
     * @return l'elemento precedentemente alla posizione specificata
     * @throws ArrayIndexOutOfBoundsException se l'indice è fuori dai limiti
     *         ({@code index &lt; 0 || index &gt;= size()})
     */
    public Object set(int index, Object element) {
        if (index < 0 || index >= vector.size()) throw new ArrayIndexOutOfBoundsException();
        Object old = vector.elementAt(index);
        vector.setElementAt(element, index);
        return old;
    }

    /**
     * Inserisce l'elemento specificato alla posizione specificata in questo
     * vettore. Sposta l'elemento attualmente in quella posizione (se presente)
     * e gli eventuali elementi successivi verso destra (aumenta i loro indici di uno).
     *
     * @param index indice in cui inserire l'elemento
     * @param element elemento da inserire
     * @throws ArrayIndexOutOfBoundsException se l'indice è fuori dai limiti
     *         ({@code index &lt; 0 || index &gt; size()})
     */
    public void add(int index, Object element) {
        if (index < 0 || index > vector.size()) throw new ArrayIndexOutOfBoundsException();
        vector.insertElementAt(element, index);
    }

    /**
     * Rimuove l'elemento alla posizione specificata in questo vettore.
     * Sposta gli eventuali elementi successivi verso sinistra (diminuisce i loro
     * indici di uno). Restituisce l'elemento rimosso.
     *
     * @param index l'indice dell'elemento da rimuovere
     * @return l'elemento precedentemente alla posizione specificata
     * @throws ArrayIndexOutOfBoundsException se l'indice è fuori dai limiti
     *         ({@code index &lt; 0 || index &gt;= size()})
     */
    public Object remove(int index) {
        if (index < 0 || index >= vector.size()) throw new ArrayIndexOutOfBoundsException();
        Object old = vector.elementAt(index);
        vector.removeElementAt(index);
        return old;
    }

    // ====================================================================
    // API Vector J2SE 1.4.2 - capacità
    // ====================================================================

    /**
     * Restituisce la capacità attuale di questo vettore. La capacità è la
     * dimensione dell'array interno usato per memorizzare gli elementi; è
     * sempre almeno pari alla dimensione del vettore e viene automaticamente
     * aumentata quando necessario.
     *
     * @return la capacità attuale (lunghezza dell'array interno)
     */
    public int capacity() {
        return vector.capacity();
    }

    /**
     * Aumenta la capacità di questo vettore, se necessario, per garantire che
     * possa contenere almeno il numero di componenti specificato dall'argomento
     * {@code minCapacity}.
     * <p>
     * Se la capacità attuale è già &ge; {@code minCapacity} non accade nulla;
     * altrimenti la nuova capacità viene scelta come il massimo tra
     * {@code minCapacity} e la politica di crescita del vettore
     * (incremento o raddoppio). 
     * 
     * @param minCapacity la capacità minima desiderata
     */
    public void ensureCapacity(int minCapacity) {
        vector.ensureCapacity(minCapacity);
    }

    /**
     * Riduce la capacità di questo vettore alla sua dimensione attuale.
     * Se la capacità è già pari alla dimensione non accade nulla.
     * Utilizzabile per minimizzare l'occupazione di memoria.
     */
    public void trimToSize() {
        vector.trimToSize();
    }

    /**
     * Imposta la dimensione di questo vettore. Se la nuova dimensione è maggiore
     * di quella attuale, vengono aggiunti nuovi elementi {@code null} in coda e
     * la capacità viene aumentata se necessario; se è minore, gli elementi in
     * eccesso in coda vengono scartati.
     *
     * @param newSize la nuova dimensione del vettore
     * @throws ArrayIndexOutOfBoundsException se la nuova dimensione è negativa
     */
    public void setSize(int newSize) {
        vector.setSize(newSize);
    }

    // ====================================================================
    // API Vector J2SE 1.4.2 - accesso 
    // ====================================================================

    /**
     * Restituisce il componente all'indice specificato.
     * <p>
     * Questo metodo è identico nella funzionalità a {@link #get(int)} (che fa
     * parte dell'interfaccia {@code List}). 
     *
     * @param index un indice in questo vettore
     * @return il componente all'indice specificato
     * @throws ArrayIndexOutOfBoundsException se l'indice è fuori dai limiti
     *         ({@code index &lt; 0 || index &gt;= size()})
     */
    public Object elementAt(int index) {
        return vector.elementAt(index);
    }

    /**
     * Restituisce il primo componente (quello all'indice {@code 0}) di questo vettore.
     *
     * @return il primo componente di questo vettore
     * @throws NoSuchElementException se questo vettore non ha componenti
     */
    public Object firstElement() {
        return vector.firstElement();
    }

    /**
     * Restituisce l'ultimo componente di questo vettore.
     *
     * @return l'ultimo componente di questo vettore, cioè il componente all'indice {@code size()-1}
     * @throws NoSuchElementException se questo vettore è vuoto
     */
    public Object lastElement() {
        return vector.lastElement();
    }

    /**
     * Imposta il componente all'indice specificato di questo vettore affinché
     * sia l'oggetto specificato. Il componente precedente in quella posizione viene scartato.
     * <p>
     * L'indice deve essere compreso tra {@code 0} e {@code size()-1} inclusi.
     * Questo metodo è identico nella funzionalità a {@link #set(int,Object)}
     * (che fa parte dell'interfaccia {@code List}), con la differenza che il
     * valore di ritorno è {@code void} e l'ordine dei parametri è invertito.
     *
     * @param obj ciò che deve diventare il componente all'indice specificato
     * @param index l'indice specificato
     * @throws ArrayIndexOutOfBoundsException se l'indice è fuori dai limiti
     *         ({@code index &lt; 0 || index &gt;= size()})
     */
    public void setElementAt(Object obj, int index) {
        vector.setElementAt(obj, index);
    }

    /**
     * Cancella il componente all'indice specificato. Ogni componente con indice
     * maggiore viene spostato verso il basso di uno e la dimensione viene
     * decrementata di uno.
     * <p>
     * L'indice deve essere tra {@code 0} e {@code size()-1} inclusi.
     * Questo metodo è identico nella funzionalità a {@link #remove(int)}
     * (che fa parte dell'interfaccia {@code List}), con la differenza che non restituisce il valore rimosso.
     *
     * @param index l'indice del componente da rimuovere
     * @throws ArrayIndexOutOfBoundsException se l'indice è fuori dai limiti
     */
    public void removeElementAt(int index) {
        vector.removeElementAt(index);
    }

    /**
     * Inserisce l'oggetto specificato come componente in questo vettore
     * all'indice specificato. Ogni componente con indice &ge; all'indice
     * specificato viene spostato verso l'alto di uno (aumenta di uno i suoi indici).
     * <p>
     * L'indice deve essere tra {@code 0} e {@code size()} inclusi.
     * Questo metodo è identico nella funzionalità a {@link #add(int,Object)}
     * (che fa parte dell'interfaccia {@code List}).
     *
     * @param obj il componente da inserire
     * @param index dove inserire il nuovo componente
     * @throws ArrayIndexOutOfBoundsException se l'indice è fuori dai limiti
     *         ({@code index &lt; 0 || index &gt; size()})
     */
    public void insertElementAt(Object obj, int index) {
        vector.insertElementAt(obj, index);
    }

    /**
     * Aggiunge il componente specificato alla fine di questo vettore,
     * aumentandone la dimensione di uno. La capacità viene aumentata se necessario.
     * <p>
     * Questo metodo è identico nella funzionalità a {@link #add(Object)}
     * (che fa parte dell'interfaccia {@code List}).
     *
     * @param obj il componente da aggiungere
     */
    public void addElement(Object obj) {
        vector.addElement(obj);
    }

    /**
     * Rimuove la prima occorrenza (quella con indice più basso) dell'argomento
     * da questo vettore. Se l'oggetto viene trovato, gli elementi successivi
     * vengono spostati verso il basso di uno e la dimensione decrementata di uno;
     * restituisce {@code true}. Se non trovato restituisce {@code false}.
     * <p>
     * È identico nella funzionalità a {@link #remove(Object)} (List).
     *
     * @param obj il componente da rimuovere
     * @return {@code true} se l'argomento era un componente di questo vettore e rimosso; {@code false} altrimenti
     */
    public boolean removeElement(Object obj) {
        return vector.removeElement(obj);
    }

    /**
     * Rimuove tutti i componenti da questo vettore e ne imposta la dimensione a zero.
     * <p>
     * È identico nella funzionalità a {@link #clear()} (List).
     */
    public void removeAllElements() {
        vector.removeAllElements();
    }

    /**
     * Copia i componenti di questo vettore nell'array specificato.
     * L'elemento all'indice {@code k} viene copiato in {@code anArray[k]}.
     *
     * @param anArray l'array in cui copiare i componenti
     * @throws NullPointerException se l'array dato è {@code null}
     */
    public void copyInto(Object[] anArray) {
        if (anArray == null) throw new NullPointerException();
        vector.copyInto(anArray);
    }

    /**
     * Restituisce un'enumerazione dei componenti di questo vettore. L'oggetto
     * restituito genera tutti gli elementi nell'ordine crescente degli indici.
     * Il primo oggetto generato è quello all'indice {@code 0}, il secondo 
     * all'indice {@code 1}, e così via. 
     *
     * @return un'enumerazione dei componenti di questo vettore
     * @see Enumeration
     */
    public Enumeration elements() {
        return vector.elements();
    }

    // ====================================================================
    // Ricerca con indice 
    // ====================================================================

    /**
     * Restituisce l'indice della prima occorrenza dell'argomento specificato
     * in questo vettore, cercando in avanti a partire da {@code index}, o
     * {@code -1} se l'oggetto non viene trovato.
     * <p>
     * La ricerca usa {@code equals}: {@code (o==null ? get(i)==null : o.equals(get(i)))}.
     *
     * @param elem l'oggetto da cercare
     * @param index l'indice non negativo da cui iniziare la ricerca
     * @return l'indice della prima occorrenza dell'oggetto a partire da {@code index}; {@code -1} se non trovato
     * @throws IndexOutOfBoundsException se {@code index} è negativo
     */
    public int indexOf(Object elem, int index) {
        if (index < 0) throw new IndexOutOfBoundsException();
        return vector.indexOf(elem, index);
    }

    /**
     * Restituisce l'indice dell'ultima occorrenza dell'argomento specificato
     * in questo vettore, cercando all'indietro a partire da {@code index}, o
     * {@code -1} se l'oggetto non viene trovato.
     *
     * @param elem l'oggetto desiderato
     * @param index l'indice da cui iniziare a cercare all'indietro
     * @return l'indice &le; {@code index} dell'ultima occorrenza dell'oggetto; {@code -1} se non trovato
     * @throws IndexOutOfBoundsException se {@code index} è maggiore o uguale alla dimensione corrente del vettore
     */
    public int lastIndexOf(Object elem, int index) {
        if (index >= vector.size()) throw new IndexOutOfBoundsException();
        return vector.lastIndexOf(elem, index);
    }

    // ====================================================================
    // Altro 
    // ====================================================================

    /**
     * Restituisce un clone di questo vettore. La capacità clonata è
     * preservata costruendo il nuovo {@code Vector} con
     * {@code capacity()} originale.
     *
     * @return un clone di questo vettore
     */
    public Object clone() {
        VectorAdapter copy = new VectorAdapter(vector.capacity());
        for (int i = 0; i < vector.size(); i++) {
            copy.vector.addElement(vector.elementAt(i));
        }
        return copy;
    }

    /**
     * Restituisce una rappresentazione in stringa di questo vettore,
     * contenente la rappresentazione in stringa di ciascun elemento.
     *
     * @return una rappresentazione in stringa di questo vettore
     */
    public String toString() {
        HIterator iterator = iterator();
        if (!iterator.hasNext()) return "[]";

        String ret = "[";
        while (true) {
            Object e = iterator.next();
            ret += (e == this ? "(this Collection)" : e);
            if (!iterator.hasNext()) {
                ret += "]";
                return ret;
            }
            ret += ", ";
        }
    }

    /**
     * Rimuove da questa lista tutti gli elementi il cui indice è
     * in {@code [fromIndex,toIndex)} (estremo superiore escluso).
     * Sposta gli elementi successivi verso sinistra e riduce la dimensione.
     * <p>
     * Questo metodo accorcia il vettore di {@code toIndex - fromIndex} elementi.
     * Se {@code toIndex==fromIndex} questa operazione non ha alcun effetto.
     *
     * @param fromIndex indice del primo elemento da rimuovere (inclusivo)
     * @param toIndex indice dopo l'ultimo elemento da rimuovere (esclusivo)
     */
    protected void removeRange(int fromIndex, int toIndex) {
        int n = toIndex - fromIndex;
        for (int i = 0; i < n; i++) {
            vector.removeElementAt(fromIndex);
        }
    }
}
