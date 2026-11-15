package myAdapter;

/**
 * L'interfaccia root nella gerarchia delle collezioni.
 * <p>
 * Una collezione rappresenta un gruppo di oggetti, conosciuti come i suoi elementi.
 * Alcune collezioni permettono elementi duplicati, mentre altre no.
 * Alcune sono ordinate e altre sono disordinate.
 * <p>
 * Tutte le classi di uso generale che implementano {@code HCollection}
 * dovrebbero fornire due costruttori "standard": un costruttore senza argomenti, che crea
 * una collezione vuota; e un costruttore con un singolo argomento di tipo {@code HCollection}, che
 * crea una nuova collezione con gli stessi elementi di quella dell'argomento.
 * <p>
 * In pratica, il secondo costruttore permette all'utente di copiare qualsiasi
 * collezione, producendo una collezione equivalente del tipo di implementazione desiderato.
 * <p>
 * Alcune implementazioni di collezioni hanno restrizioni sugli elementi che possono contenere. Ad
 * esempio, alcune implementazioni proibiscono elementi {@code null}, e alcune hanno restrizioni sui
 * tipi dei loro elementi.
 * Tentare di aggiungere un elemento non idoneo lancia un'eccezione non controllata, tipicamente
 * {@code NullPointerException} o {@code ClassCastException}.
 * Tentare di verificare la presenza di un elemento non idoneo può lanciare un'eccezione, o può semplicemente
 * restituire {@code false}; alcune implementazioni esibiranno il primo comportamento mentre altre esibiranno
 * il secondo.
 * Più in generale, tentare un'operazione su un elemento non idoneo il cui completamento non
 * risulterebbe nell'inserimento di un elemento non idoneo nella collezione può lanciare un'eccezione
 * oppure potrebbe avere successo, in base all'implementazione.
 * Tali eccezioni sono segnate come "opzionali" nella specifica di questa interfaccia.
 */
public interface HCollection {
    
    /**
     * Restituisce il numero di elementi presenti in questa collezione.
     * <p>
     * Se questa collezione contiene più di {@code Integer.MAX_VALUE} elementi,
     * restituisce {@code Integer.MAX_VALUE}.
     *
     * @return il numero di elementi in questa collezione.
     */
    int size();

    /**
     * Restituisce {@code true} se questa collezione non contiene elementi.
     *
     * @return {@code true} se questa collezione non contiene elementi.
     */
    boolean isEmpty();

    /**
     * Restituisce {@code true} se questa collezione contiene l'elemento specificato.
     * <p>
     * Più formalmente, restituisce {@code true} se e solo se questa collezione contiene almeno un
     * elemento {@code e} tale che {@code (o==null ? e==null : o.equals(e))}.
     *
     * @param o l'elemento la cui presenza in questa collezione deve essere verificata.
     * @return true se questa collezione contiene l'elemento specificato.
     * @throws ClassCastException se il tipo dell'elemento specificato è incompatibile con questa
     *         collezione (opzionale).
     * @throws NullPointerException se l'elemento specificato è {@code null} e questa collezione non
     *         supporta elementi {@code null} (opzionale).
     */
    boolean contains(Object o);

    /**
     * Restituisce un iteratore sugli elementi di questa collezione.
     * <p>
     * Non ci sono garanzie riguardo l'ordine in cui gli elementi vengono
     * restituiti (a meno che questa collezione non sia un'istanza di una classe che fornisce
     * una garanzia a riguardo).
     *
     * @return un {@code HIterator} sugli elementi di questa collezione.
     */
    HIterator iterator();

    /**
     * Restituisce un array contenente tutti gli elementi di questa collezione.
     * <p>
     * Se la collezione fornisce garanzie sull'ordine in cui i suoi elementi sono restituiti
     * dal suo iteratore, questo metodo deve restituire gli elementi nello stesso ordine.
     * <p>
     * L'array restituito sarà "sicuro", ovvero questa collezione non manterrà alcun
     * riferimento ad esso. (In altre parole, questo metodo deve allocare un nuovo array anche se
     * questa collezione è basata su un array).
     * Il chiamante è quindi libero di modificare l'array restituito.
     * <p>
     * Questo metodo si comporta da ponte tra le API basate su array e quelle basate su collezioni.
     *
     * @return un array contenente tutti gli elementi di questa collezione.
     */
    Object[] toArray();

    /**
     * Restituisce un array contenente tutti gli elementi di questa collezione; il tipo runtime
     * dell'array restituito è quello dell'array specificato.
     * <p>
     * Se la collezione è contenibile nell'array specificato, viene restituita in esso.
     * Altrimenti, viene allocato un nuovo array con il tipo a runtime dell'array specificato
     * e la dimensione di questa collezione.
     * <p>
     * Se questa collezione è contenibile nell'array specificato con spazio aggiuntivo (cioè, l'array ha
     * più elementi di questa collezione), l'elemento nell'array immediatamente successivo alla fine
     * della collezione viene impostato a {@code null}.
     * Ciò è utile per determinare la lunghezza di questa collezione solo se il chiamante sa
     * che questa collezione non contiene nessun elemento {@code null}.
     * <p>
     * Se questa collezione fornisce garanzie sull'ordine in cui i suoi elementi sono restituiti dal
     * suo iteratore, questo metodo deve restituire gli elementi nello stesso ordine.
     * <p>
     * Come il metodo {@code toArray()}, questo metodo si comporta da ponte tra le API basate su array e
     * quelle basate su collezioni.
     * Inoltre, questo metodo consente un controllo preciso sul tipo a runtime dell'array restituito
     * e, in certe circostanze, può essere utilizzato per risparmiare sui costi di allocazione.
     *
     * @param a l'array in cui memorizzare gli elementi di questa collezione, se è sufficientemente
     *        grande; altrimenti un nuovo array dello stesso tipo a runtime viene allocato per questo scopo.
     * @return un array contenente gli elementi di questa collezione.
     * @throws ArrayStoreException se il tipo runtime dell'array specificato non è un supertipo del
     *         tipo a runtime di ogni elemento in questa collezione.
     * @throws NullPointerException se l'array specificato è {@code null}.
     */
    Object[] toArray(Object[] a);

    /**
     * Assicura che questa collezione contenga l'elemento specificato (operazione opzionale).
     * <p>
     * Restituisce {@code true} se la collezione è cambiata in seguito alla chiamata. (Restituisce
     * {@code false} se la collezione non consente duplicati e contiene già l'elemento specificato).
     * <p>
     * Le collezioni che supportano questa operazione potrebbero imporre limitazioni su quali elementi
     * possono essere aggiunti a questa collezione. In particolare, alcune collezioni rifiutano di
     * aggiungere elementi {@code null} e altre impongono restrizioni sul tipo di elementi che
     * possono essere aggiunti.
     * Le classi di collezioni devono specificare chiaramente nella loro
     * documentazione qualsiasi restrizione sugli elementi che possono essere aggiunti.
     * <p>
     * Se una collezione impedisce di aggiungere un particolare elemento per qualsiasi motivo diverso
     * dal fatto che lo contiene già, deve necessariamente lanciare un'eccezione (invece di restituire
     * {@code false}).
     * Questo preserva l'invariante che una collezione contiene sempre l'elemento
     * specificato dopo il ritorno di questa chiamata.
     *
     * @param o elemento la cui presenza in questa collezione deve essere garantita.
     * @return {@code true} se la collezione è stata modificata a seguito della chiamata.
     * @throws MyUnsupportedOperationException se l'operazione {@code add} non è supportata da
     *         questa collezione.
     * @throws ClassCastException se la classe dell'elemento specificato impedisce di aggiungerlo a
     *         questa collezione.
     * @throws NullPointerException se l'elemento specificato è {@code null} e questa collezione non
     *         supporta elementi {@code null}.
     * @throws IllegalArgumentException se qualche aspetto di questo elemento impedisce di aggiungerlo
     *         a questa collezione.
     */
    boolean add(Object o);

    /**
     * Rimuove una singola istanza dell'elemento specificato da questa collezione, se è
     * presente (operazione opzionale).
     * <p>
     * Più formalmente, rimuove un elemento {@code e} tale che
     * {@code (o==null ? e==null : o.equals(e))}, se questa collezione contiene uno o più tali elementi.
     * Restituisce {@code true} se questa collezione conteneva l'elemento
     * specificato (o equivalentemente, se questa collezione è stata modificata come risultato della
     * chiamata).
     *
     * @param o l'elemento da rimuovere da questa collezione, se presente.
     * @return {@code true} se questa collezione è stata modificata come risultato della chiamata.
     * @throws MyUnsupportedOperationException se l'operazione {@code remove} non è supportata da
     *         questa collezione.
     * @throws ClassCastException se il tipo dell'elemento specificato è incompatibile con questa
     *         collezione (opzionale).
     * @throws NullPointerException se l'elemento specificato è {@code null} e questa collezione non
     *         supporta elementi {@code null} (opzionale).
     */
    boolean remove(Object o);

    /**
     * Restituisce {@code true} se questa collezione contiene tutti gli elementi della collezione
     * specificata.
     *
     * @param c la collezione la cui presenza è da verificare in questa collezione.
     * @return {@code true} se questa collezione contiene tutti gli elementi della collezione
     *         specificata.
     * @throws ClassCastException se il tipo di uno o più elementi nella collezione specificata è
     *         incompatibile con questa collezione (opzionale).
     * @throws NullPointerException se la collezione specificata contiene uno o più elementi
     *         {@code null} e questa collezione non supporta elementi {@code null} (opzionale).
     * @throws NullPointerException se la collezione specificata è {@code null}.
     */
    boolean containsAll(HCollection c);

    /**
     * Aggiunge tutti gli elementi della collezione specificata a questa
     * collezione (operazione opzionale).
     * <p>
     * Il comportamento di questa operazione non è definito se la collezione specificata
     * viene modificata durante l'esecuzione dell'operazione. (Ciò implica che il comportamento
     * di questa chiamata non è definito se la collezione specificata è proprio questa collezione
     * e questa è non vuota.)
     *
     * @param c la collezione di elementi da inserire in questa collezione.
     * @return {@code true} se questa collezione è stata modificata come risultato della chiamata.
     * @throws MyUnsupportedOperationException se l'operazione {@code addAll} non è supportata da
     *         questa collezione.
     * @throws ClassCastException se la classe di un elemento della collezione specificata impedisce
     *         di aggiungerlo a questa collezione.
     * @throws NullPointerException se la collezione specificata contiene uno o più elementi
     *         {@code null} e questa collezione non supporta elementi {@code null}, oppure se la
     *         collezione specificata è {@code null}.
     * @throws IllegalArgumentException se qualche aspetto di un elemento della collezione
     *         specificata impedisce di aggiungerlo a questa collezione.
     */
    boolean addAll(HCollection c);

    /**
     * Rimuove tutti gli elementi di questa collezione che sono anche contenuti nella collezione
     * specificata (operazione opzionale).
     * <p>
     * Dopo che questa chiamata ritorna, questa collezione non conterrà elementi in comune
     * con la collezione specificata.
     *
     * @param c gli elementi da rimuovere da questa collezione.
     * @return {@code true} se questa collezione è stata modificata come risultato della chiamata.
     * @throws MyUnsupportedOperationException se l'operazione {@code removeAll} non è supportata da
     *         questa collezione.
     * @throws ClassCastException se il tipo di uno o più elementi in questa collezione è
     *         incompatibile con la collezione specificata (opzionale).
     * @throws NullPointerException se questa collezione contiene uno o più elementi {@code null} e
     *         la collezione specificata non supporta elementi {@code null} (opzionale).
     * @throws NullPointerException se la collezione specificata è {@code null}.
     */
    boolean removeAll(HCollection c);

    /**
     * Mantiene in questa collezione solo gli elementi che sono contenuti nella collezione
     * specificata (operazione opzionale).
     * <p>
     * In altre parole, rimuove da questa collezione tutti i suoi elementi che non sono contenuti
     * anche nella collezione specificata.
     *
     * @param c gli elementi da mantenere in questa collezione.
     * @return {@code true} se questa collezione è stata modificata come risultato della chiamata.
     * @throws MyUnsupportedOperationException se l'operazione {@code retainAll} non è supportata da
     *         questa collezione.
     * @throws ClassCastException se il tipo di uno o più elementi in questa collezione è
     *         incompatibile con la collezione specificata (opzionale).
     * @throws NullPointerException se questa collezione contiene uno o più elementi {@code null} e
     *         la collezione specificata non supporta elementi {@code null} (opzionale).
     * @throws NullPointerException se la collezione specificata è {@code null}.
     */
    boolean retainAll(HCollection c);

    /**
     * Rimuove tutti gli elementi da questa collezione (operazione opzionale).
     * <p>
     * Questa collezione sarà vuota dopo che il metodo ritorna, a meno che non
     * venga sollevata un'eccezione.
     *
     * @throws MyUnsupportedOperationException se l'operazione {@code clear} non è supportata da
     *         questa collezione.
     */
    void clear();

    /**
     * Confronta l'oggetto specificato con questa collezione per verificarne l'uguaglianza.
     * <p>
     * Sebbene l'interfaccia {@code HCollection} non aggiunga restrizioni al contratto generale per
     * {@code Object.equals}, i programmatori che implementano l'interfaccia {@code HCollection}
     * "direttamente" (cioè creano una classe che è una {@code HCollection} ma non un {@code Set} o
     * una {@code List}) devono prestare attenzione se scelgono di sovrascrivere
     * {@code Object.equals}.
     * Non è necessario farlo, e l'azione più semplice è affidarsi all'implementazione
     * predefinita di Object, ma l'implementatore potrebbe desiderare di implementare un
     * confronto per "valore" al posto del confronto per "riferimento" predefinito.
     * (Le interfacce {@code List} e {@code Set} impongono tali confronti per valore.)
     * <p>
     * Il contratto generale per il metodo {@code Object.equals} afferma che equals deve essere
     * simmetrico (cioè, {@code a.equals(b)} se e solo se {@code b.equals(a)}). I contratti per
     * {@code List.equals} e {@code Set.equals} affermano che le liste sono uguali solo ad altre
     * liste e i set ad altri set. Quindi, un metodo equals ad hoc per una classe di
     * collezione che non implementa né l'interfaccia {@code List} né {@code Set} deve restituire
     * {@code false} quando questa collezione viene confrontata con qualsiasi lista o set. (Per la
     * stessa logica, non è possibile scrivere una classe che implementi correttamente entrambe le
     * interfacce {@code Set} e {@code List}.)
     *
     * {@inheritDoc}
     *
     * @param o l'oggetto da confrontare per uguaglianza con questa collezione.
     * @return {@code true} se l'oggetto specificato è uguale a questa collezione.
     */
    boolean equals(Object o);

    /**
     * Restituisce il valore dell'hash code per questa collezione.
     * <p>
     * Sebbene l'interfaccia {@code HCollection} non aggiunga restrizioni al contratto generale
     * per il metodo {@code Object.hashCode}, i programmatori devono prendere nota che
     * qualsiasi classe che sovrascrive il metodo {@code Object.equals} deve anche
     * sovrascrivere il metodo {@code Object.hashCode} per soddisfare il contratto generale
     * del metodo hashCode. In particolare,
     * {@code c1.equals(c2)} implica che {@code c1.hashCode() == c2.hashCode()}.
     *
     * {@inheritDoc}
     *
     * @return il valore dell'hash code per questa collezione.
     */
    int hashCode();
}
