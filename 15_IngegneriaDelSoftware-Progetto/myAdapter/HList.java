package myAdapter;

/**
 * <p>
 * Una raccolta ordinata (nota anche come sequenza). L'utente di questa interfaccia ha un controllo
 * preciso su dove ogni elemento viene inserito nella lista. L'utente può accedere agli elementi
 * tramite il loro indice intero (posizione nella lista) e cercare elementi nella lista.
 * </p>
 * <p>
 * A differenza dei {@code Set}, questa lista consente lo storage di elementi nulli o elementi
 * dupplicati, ovvero consente l'inserimento di elementi e1 e2 tali che {@code e1.equals(e2)}. Ogni
 * utente nell'implementazione di questa interfaccia è libero di modificare questi comportamenti.
 * </p>
 * <p>
 * L'interfaccia {@code HList} pone ulteriori vincoli, oltre a quelli specificati nell'interfaccia
 * HCollection, sui contratti dei metodi hiterator, add, remove, equals e hashCode. Le dichiarazioni
 * per altri metodi ereditati sono incluse qui per comodità.
 * </p>
 * <p>
 * L'interfaccia {@code HList} fornisce quattro metodi per l'accesso posizionale (indicizzato) agli
 * elementi della lista. Il primo indice della lista (come con gli array) corrisponde a zero. Si
 * noti che queste operazioni possono essere eseguite in un tempo proporzionale al valore
 * dell'indice per alcune implementazioni (la classe {@code LinkedList}, ad esempio). Pertanto,
 * iterare sugli elementi di una lista è tipicamente preferibile all'indicizzazione se il chiamante
 * non conosce l'implementazione.
 * </p>
 * <p>
 * L'interfaccia {@code HList} fornisce un iteratore speciale, chiamato {@code HListIterator}, che
 * consente l'inserimento e la sostituzione degli elementi, e l'accesso bidirezionale oltre alle
 * normali operazioni fornite dall'interfaccia HIterator. È fornito un metodo per ottenere un
 * iteratore della lista che inizi in una posizione specificata nella lista.
 * </p>
 * <p>
 * L'interfaccia {@code HList} fornisce due metodi per cercare un oggetto specificato. Dal punto di
 * vista delle prestazioni, questi metodi dovrebbero essere usati con cautela. In molte
 * implementazioni eseguiranno ricerche lineari costose.
 * </p>
 * <p>
 * L'interfaccia {@code HList} fornisce due metodi per inserire e rimuovere efficientemente più
 * elementi in un punto arbitrario della lista.
 * </p>
 * <p>
 * Nota: anche se alle liste è permesso contenere se stesse come elementi, estrema cautela è consigliata:
 * i metodi {@code equals} e {@code hashCode} non sono più ben definiti su tali liste.
 * </p>
 * <p>
 * Alcune implementazioni di liste hanno restrizioni sugli elementi che possono contenere. Ad
 * esempio, alcune implementazioni proibiscono elementi nulli, e alcune hanno restrizioni sui tipi
 * dei loro elementi. Tentare di aggiungere un elemento non idoneo lancia un'eccezione non
 * controllata, tipicamente {@link NullPointerException} o {@link ClassCastException}. Tentare di
 * interrogare la presenza di un elemento non idoneo può lanciare un'eccezione, oppure può
 * semplicemente restituire {@code false}; alcune implementazioni mostreranno il primo comportamento
 * e altre il secondo. Più in generale, tentare un'operazione su un elemento non idoneo la cui
 * conclusione non comporterebbe l'inserimento di un elemento non idoneo nella lista può lanciare
 * un'eccezione oppure può avere successo, a discrezione dell'implementazione. Tali eccezioni sono
 * contrassegnate come "opzionali" nella specifica di questa interfaccia.
 * </p>
 */
public interface HList extends HCollection {

    // ========================================================================
    // Metodi ereditati da HCollection ridefiniti con contratto specifico List
    // (inclusi per comodità, come in java.util.List 1.4.2)
    // ========================================================================

    /**
     * Restituisce il numero di elementi in questa lista.
     * Se questa lista contiene più di {@code Integer.MAX_VALUE} elementi, restituisce
     * {@code Integer.MAX_VALUE}.
     *
     * @return il numero di elementi in questa lista.
     */
    int size();

    /**
     * Restituisce {@code true} se questa lista non contiene elementi.
     *
     * @return {@code true} se questa lista non contiene elementi.
     */
    boolean isEmpty();

    /**
     * Restituisce {@code true} se questa lista contiene l'elemento specificato.
     * Più formalmente, restituisce {@code true} se e solo se questa lista contiene almeno un
     * elemento {@code e} tale che {@code (o==null ? e==null : o.equals(e))}.
     *
     * @param o l'elemento la cui presenza deve essere verificata.
     * @return {@code true} se questa lista contiene l'elemento specificato.
     * @throws ClassCastException se il tipo dell'elemento specificato è incompatibile con questa
     *         lista (opzionale).
     * @throws NullPointerException se l'elemento specificato è {@code null} e questa lista non
     *         supporta elementi {@code null} (opzionale).
     */
    boolean contains(Object o);

    /**
     * Restituisce un iteratore sugli elementi di questa lista nell'ordine corretto
     * (proper sequence).
     *
     * @return un {@code HIterator} sugli elementi di questa lista nell'ordine corretto.
     */
    HIterator iterator();

    /**
     * Restituisce un array contenente tutti gli elementi di questa lista nell'ordine corretto.
     * Obbedisce al contratto generale di {@code HCollection.toArray()}.
     *
     * @return un array contenente tutti gli elementi di questa lista nell'ordine corretto.
     */
    Object[] toArray();

    /**
     * Restituisce un array contenente tutti gli elementi di questa lista nell'ordine corretto;
     * il tipo runtime dell'array restituito è quello dell'array specificato.
     * Obbedisce al contratto generale di {@code HCollection.toArray(Object[])}.
     *
     * @param a l'array in cui memorizzare gli elementi, se sufficientemente grande; altrimenti
     *        viene allocato un nuovo array dello stesso tipo runtime.
     * @return un array contenente gli elementi di questa lista.
     * @throws ArrayStoreException se il tipo runtime dell'array specificato non è un supertipo del
     *         tipo runtime di ogni elemento in questa lista.
     * @throws NullPointerException se l'array specificato è {@code null}.
     */
    Object[] toArray(Object[] a);

    /**
     * Aggiunge l'elemento specificato alla fine di questa lista (operazione opzionale).
     * Le liste che supportano questa operazione possono porre limitazioni sugli elementi
     * aggiungibili. Le classi di lista devono specificare chiaramente nella documentazione
     * eventuali restrizioni.
     *
     * @param o l'elemento da aggiungere.
     * @return {@code true} (come da contratto di {@code HCollection.add}).
     * @throws MyUnsupportedOperationException se l'operazione {@code add} non è supportata.
     * @throws ClassCastException se la classe dell'elemento specificato ne impedisce l'aggiunta.
     * @throws NullPointerException se l'elemento è {@code null} e la lista non supporta null.
     * @throws IllegalArgumentException se qualche aspetto dell'elemento ne impedisce l'aggiunta.
     */
    boolean add(Object o);

    /**
     * Rimuove la prima occorrenza dell'elemento specificato da questa lista, se presente
     * (operazione opzionale). Se la lista non contiene l'elemento, rimane invariata.
     * Più formalmente, rimuove l'elemento con indice più basso {@code i} tale che
     * {@code (o==null ? get(i)==null : o.equals(get(i)))}.
     *
     * @param o l'elemento da rimuovere, se presente.
     * @return {@code true} se la lista conteneva l'elemento specificato.
     * @throws ClassCastException se il tipo dell'elemento è incompatibile con questa lista (opzionale).
     * @throws NullPointerException se l'elemento è {@code null} e la lista non supporta null (opzionale).
     * @throws MyUnsupportedOperationException se l'operazione {@code remove} non è supportata.
     */
    boolean remove(Object o);

    /**
     * Restituisce {@code true} se questa lista contiene tutti gli elementi della collezione
     * specificata.
     *
     * @param c la collezione da verificare.
     * @return {@code true} se la lista contiene tutti gli elementi di {@code c}.
     * @throws ClassCastException se il tipo di uno o più elementi in {@code c} è incompatibile (opzionale).
     * @throws NullPointerException se {@code c} contiene null e la lista non supporta null (opzionale).
     * @throws NullPointerException se la collezione specificata è {@code null}.
     * @see #contains(Object)
     */
    boolean containsAll(HCollection c);

    /**
     * Aggiunge tutti gli elementi della collezione specificata alla fine di questa lista,
     * nell'ordine in cui sono restituiti dall'iteratore di {@code c} (operazione opzionale).
     * Il comportamento non è definito se {@code c} viene modificata durante l'operazione
     * (ciò accade se {@code c} è questa lista ed è non vuota).
     *
     * @param c la collezione di elementi da aggiungere.
     * @return {@code true} se la lista è stata modificata.
     * @throws MyUnsupportedOperationException se {@code addAll} non è supportato.
     * @throws ClassCastException se la classe di un elemento ne impedisce l'aggiunta.
     * @throws NullPointerException se {@code c} contiene null non supportati o è {@code null}.
     * @throws IllegalArgumentException se qualche aspetto di un elemento ne impedisce l'aggiunta.
     * @see #add(Object)
     */
    boolean addAll(HCollection c);

    /**
     * Rimuove da questa lista tutti gli elementi contenuti nella collezione specificata
     * (operazione opzionale).
     *
     * @param c la collezione che definisce gli elementi da rimuovere.
     * @return {@code true} se la lista è stata modificata.
     * @throws MyUnsupportedOperationException se {@code removeAll} non è supportato.
     * @throws ClassCastException se il tipo di uno o più elementi è incompatibile (opzionale).
     * @throws NullPointerException se la lista contiene null e {@code c} non supporta null (opzionale).
     * @throws NullPointerException se la collezione specificata è {@code null}.
     * @see #remove(Object)
     * @see #contains(Object)
     */
    boolean removeAll(HCollection c);

    /**
     * Mantiene in questa lista solo gli elementi contenuti nella collezione specificata
     * (operazione opzionale). Rimuove tutti gli elementi non contenuti in {@code c}.
     *
     * @param c la collezione che definisce gli elementi da mantenere.
     * @return {@code true} se la lista è stata modificata.
     * @throws MyUnsupportedOperationException se {@code retainAll} non è supportato.
     * @throws ClassCastException se il tipo di uno o più elementi è incompatibile (opzionale).
     * @throws NullPointerException se la lista contiene null e {@code c} non supporta null (opzionale).
     * @throws NullPointerException se la collezione specificata è {@code null}.
     * @see #remove(Object)
     * @see #contains(Object)
     */
    boolean retainAll(HCollection c);

    /**
     * Rimuove tutti gli elementi da questa lista (operazione opzionale).
     * La lista sarà vuota dopo il ritorno, a meno che non venga lanciata un'eccezione.
     *
     * @throws MyUnsupportedOperationException se {@code clear} non è supportata.
     */
    void clear();

    /**
     * Confronta l'oggetto specificato con questa lista per l'uguaglianza.
     * Restituisce {@code true} se e solo se l'oggetto è anch'esso una lista, entrambe le liste
     * hanno la stessa dimensione e tutte le coppie corrispondenti di elementi sono <em>uguali</em>
     * ({@code (e1==null ? e2==null : e1.equals(e2))}). Due liste sono uguali se contengono
     * gli stessi elementi nello stesso ordine. Questa definizione garantisce che equals funzioni
     * correttamente tra diverse implementazioni di {@code HList}.
     *
     * @param o l'oggetto da confrontare.
     * @return {@code true} se l'oggetto specificato è uguale a questa lista.
     * @see Object#hashCode()
     */
    boolean equals(Object o);

    /**
     * Restituisce il valore hash per questa lista. L'hash è definito come:
     * <pre>
     * hashCode = 1;
     * HIterator i = list.iterator();
     * while (i.hasNext()) {
     *     Object obj = i.next();
     *     hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
     * }
     * </pre>
     * Assicura che {@code list1.equals(list2)} implichi {@code list1.hashCode()==list2.hashCode()}.
     *
     * @return il valore hash per questa lista.
     * @see Object#hashCode()
     * @see #equals(Object)
     */
    int hashCode();

    // ========================================================================
    // Metodi specifici di List (accesso posizionale, ricerca, iteratori, subList)
    // ========================================================================

    /**
     * Inserisce tutti gli elementi nella collezione specificata in questa lista nella posizione
     * specificata (operazione opzionale). Sposta l'elemento attualmente in quella posizione (se
     * presente) e tutti gli elementi successivi verso destra (aumenta i loro indici). I nuovi
     * elementi appariranno in questa lista nell'ordine in cui vengono restituiti dall'iteratore
     * della collezione specificata. Il comportamento di questa operazione non è specificato se la
     * collezione specificata viene modificata mentre l'operazione è in corso. (Si noti che ciò
     * accadrà se la collezione specificata è questa lista ed è non vuota).
     *
     * @param index l'indice in cui inserire il primo elemento della collezione specificata.
     * @param c gli elementi da inserire in questa lista.
     * @return {@code true} se questa lista è stata modificata come risultato della chiamata.
     * @throws MyUnsupportedOperationException se {@code addAll} non è supportato da questa lista.
     * @throws ClassCastException se la classe di uno degli elementi della collezione specificata ne
     *         impedisce l'aggiunta a questa lista.
     * @throws NullPointerException se la collezione specificata contiene uno o più elementi
     *         {@code null} e questa lista non supporta elementi {@code null}, oppure se la
     *         collezione specificata è {@code null}.
     * @throws IllegalArgumentException se qualche aspetto di uno degli elementi della collezione
     *         specificata ne impedisce l'aggiunta a questa lista.
     * @throws IndexOutOfBoundsException se l'indice è fuori dal range
     *         {@code (index < 0 || index > size())}.
     */
    boolean addAll(int index, HCollection c);

    /**
     * Restituisce l'elemento alla posizione specificata in questa lista.
     *
     * @param index l'indice dell'elemento da restituire.
     * @return l'elemento alla posizione specificata in questa lista.
     * @throws IndexOutOfBoundsException se l'indice è fuori dal range
     *         {@code (index < 0 || index >= size())}.
     */
    Object get(int index);

    /**
     * Sostituisce l'elemento alla posizione specificata in questa lista con l'elemento specificato
     * (operazione opzionale).
     *
     * @param index l'indice dell'elemento da sostituire.
     * @param element l'elemento da memorizzare alla posizione specificata.
     * @return l'elemento precedentemente alla posizione specificata.
     * @throws MyUnsupportedOperationException se {@code set} non è supportato da questa lista.
     * @throws ClassCastException se la classe dell'elemento specificato impedisce di essere
     *         aggiunto a questa lista.
     * @throws NullPointerException se l'elemento specificato è {@code null} e questa lista non
     *         supporta elementi {@code null}.
     * @throws IllegalArgumentException se qualche aspetto dell'elemento specificato impedisce di
     *         essere aggiunto a questa lista.
     * @throws IndexOutOfBoundsException se l'indice è fuori dal range
     *         {@code (index < 0 || index >= size())}.
     */
    Object set(int index, Object element);

    /**
     * Inserisce l'elemento specificato nella posizione specificata in questa lista (operazione
     * opzionale). Sposta l'elemento attualmente in quella posizione (se presente) e tutti gli
     * elementi successivi verso destra (aggiunge uno ai loro indici).
     *
     * @param index l'indice in cui l'elemento specificato deve essere inserito.
     * @param element l'elemento da inserire.
     * @throws MyUnsupportedOperationException se {@code add} non è supportato da questa lista.
     * @throws ClassCastException se la classe dell'elemento specificato ne impedisce l'aggiunta a
     *         questa lista.
     * @throws NullPointerException se l'elemento specificato è {@code null} e questa lista non
     *         supporta elementi {@code null}.
     * @throws IllegalArgumentException se qualche aspetto dell'elemento specificato ne impedisce
     *         l'aggiunta a questa lista.
     * @throws IndexOutOfBoundsException se l'indice è fuori dal range
     *         {@code (index < 0 || index > size())}.
     */
    void add(int index, Object element);

    /**
     * Rimuove l'elemento alla posizione specificata in questa lista (operazione opzionale). Sposta
     * eventuali elementi successivi a sinistra (sottrae uno dai loro indici). Restituisce
     * l'elemento che è stato rimosso dalla lista.
     *
     * @param index l'indice dell'elemento da rimuovere.
     * @return l'elemento precedentemente alla posizione specificata.
     * @throws MyUnsupportedOperationException se {@code remove} non è supportato da questa lista.
     * @throws IndexOutOfBoundsException se l'indice è fuori dal range
     *         {@code (index < 0 || index >= size())}.
     */
    Object remove(int index);

    /**
     * Restituisce l'indice in questa lista della prima occorrenza dell'elemento specificato, oppure
     * {@code -1} se questa lista non contiene l'elemento. Più formalmente, restituisce il più basso
     * indice {@code i} tale che {@code (o==null ? get(i)==null : o.equals(get(i)))}, oppure
     * {@code -1} se non esiste tale indice.
     *
     * @param o l'elemento da cercare.
     * @return l'indice in questa lista della prima occorrenza dell'elemento specificato, oppure
     *         {@code -1} se questa lista non contiene l'elemento.
     * @throws ClassCastException se il tipo dell'elemento specificato è incompatibile con questa
     *         lista (opzionale).
     * @throws NullPointerException se l'elemento specificato è {@code null} e questa lista non
     *         supporta elementi {@code null} (opzionale).
     */
    int indexOf(Object o);

    /**
     * Restituisce l'indice in questa lista dell'ultima occorrenza dell'elemento specificato, oppure
     * {@code -1} se questa lista non contiene l'elemento. Più formalmente, restituisce l'indice più
     * alto {@code i} tale che {@code (o==null ? get(i)==null : o.equals(get(i)))}, oppure
     * {@code -1} se non esiste tale indice.
     *
     * @param o l'elemento da cercare.
     * @return l'indice in questa lista dell'ultima occorrenza dell'elemento specificato, oppure
     *         {@code -1} se questa lista non contiene l'elemento.
     * @throws ClassCastException se il tipo dell'elemento specificato è incompatibile con questa
     *         lista (opzionale).
     * @throws NullPointerException se l'elemento specificato è {@code null} e questa lista non
     *         supporta elementi {@code null} (opzionale).
     */
    int lastIndexOf(Object o);

    /**
     * Restituisce un iteratore di lista degli elementi in questa lista (nell'ordine corretto).
     *
     * @return un iteratore di lista degli elementi in questa lista (nell'ordine corretto).
     */
    HListIterator listIterator();

    /**
     * Restituisce un iteratore di lista degli elementi in questa lista (nell'ordine corretto),
     * partendo dalla posizione specificata in questa lista. L'indice specificato indica il primo
     * elemento che verrebbe restituito da una chiamata iniziale al metodo {@code next}. Una
     * chiamata iniziale al metodo {@code previous} restituirebbe l'elemento con l'indice
     * specificato meno uno.
     *
     * @param index l'indice del primo elemento da restituire dall'iteratore di lista (tramite una
     *        chiamata al metodo {@code next}).
     * @return un iteratore di lista degli elementi in questa lista (nell'ordine corretto), partendo
     *         dalla posizione specificata in questa lista.
     * @throws IndexOutOfBoundsException se l'indice è fuori dal range
     *         {@code (index < 0 || index > size())}.
     */
    HListIterator listIterator(int index);

    /**
     * Restituisce una vista della porzione di questa lista compresa tra l'indice {@code fromIndex},
     * incluso, e l'indice {@code toIndex}, escluso. (Se {@code fromIndex} e {@code toIndex} sono
     * uguali, la lista restituita è vuota.) La lista restituita è supportata da questa lista,
     * quindi le modifiche non strutturali nella lista restituita sono riflesse in questa lista, e
     * viceversa. La lista restituita supporta tutte le operazioni opzionali della lista supportate
     * da questa lista.
     *
     * Questo metodo elimina la necessità di operazioni esplicite di intervallo (del tipo
     * comunemente esistente per gli array). Qualsiasi operazione che si aspetta una lista può
     * essere utilizzata come operazione di intervallo passando una vista {@code subList} invece di
     * una lista intera. Ad esempio, il seguente codice rimuove un intervallo di elementi da una
     * lista:
     * <pre>
     * list.subList(from, to).clear();
     * </pre>
     *
     * Idiomi simili possono essere costruiti per {@code indexOf} e {@code lastIndexOf}, e tutti gli
     * algoritmi nella classe {@code Collections} possono essere applicati a una {@code subList}.
     *
     * La semantica della lista restituita da questo metodo diventano indefinite se la lista di base
     * (ossia, questa lista) è modificata strutturalmente in qualsiasi modo diverso tramite la lista
     * restituita. (Le modifiche strutturali sono quelle che cambiano la dimensione di questa lista,
     * o la perturbano in modo tale che le iterazioni in corso possono dare risultati errati.)
     *
     * @param fromIndex l'estremo inferiore (inclusivo) della {@code subList}.
     * @param toIndex l'estremo superiore (esclusivo) della {@code subList}.
     * @return una vista dell'intervallo specificato all'interno di questa lista.
     * @throws IndexOutOfBoundsException se il valore dell'indice di estremità è illecito
     *         {@code (fromIndex < 0 || toIndex > size || fromIndex > toIndex)}.
     */
    HList subList(int fromIndex, int toIndex);
}
