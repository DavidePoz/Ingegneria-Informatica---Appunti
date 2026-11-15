package myTest;

import static org.junit.Assert.*;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * <p>
 * <b>Summary</b> Verifica il corretto funzionamento della vista {@code subList}
 * prodotta da {@link AbstractHList#subList(int, int)}. La suite copre la
 * creazione della vista con indici validi, le operazioni di lettura e scrittura
 * sulla vista riflettute nella lista di backing, le operazioni bulk, gli
 * iteratori ({@link HIterator} e {@link HListIterator}), i controlli di bounds
 * rispetto alla dimensione della vista, e la semantica backed tra vista e lista
 * originale incluse le viste annidate.
 * <br>
 * Per eseguire questa test suite: {@code java -cp "bin:JUnit/*" org.junit.runner.JUnitCore myTest.SubListAdapterTest}
 * </p>
 * <p>
 * <b>Test Case Design</b> La suite organizza i test in 6 categorie:
 * <ul>
 * <li>A) Costruzione e lettura (8 test)</li>
 * <li>B) Modifica posizionale (8 test)</li>
 * <li>C) Operazioni bulk (7 test)</li>
 * <li>D) Iteratori (4 test)</li>
 * <li>E) Controlli di bounds e casi limite (3 test)</li>
 * <li>F) Vista e lista originale (4 test)</li>
 * </ul>
 * <br>
 * Ogni test utilizza il metodo {@link #setUp()} che inizializza una lista
 * {@code original = [A, B, C, D, E]} (VectorAdapter di size 5, capacity 10 di
 * default) e la vista {@code sub = original.subList(1, 4)} con contenuto
 * {@code [B, C, D]} (size 3). I test con collezioni di supporto usano il metodo
 * {@link #makeCollection(Object...)} che crea un {@link VectorAdapter} con gli
 * elementi specificati. I criteri di valutazione usano {@code assert} JUnit e
 * verifica di eccezioni attese ({@link IndexOutOfBoundsException},
 * {@link NoSuchElementException}). <br>
 * Per ogni test method la documentazione dettagliata riporta i valori concreti
 * delle variabili e le dimensioni attese della vista e della lista originale.
 * <br>
 * <b>Versione JUnit:</b> Questa test suite è stata scritta utilizzando il framework JUnit 4.13.
 * <p>
 * <b>Test Cases</b>
 * </p>
 * <p>
 * <b>A) Costruzione e lettura (8 test)</b>
 * </p>
 * <ul>
 * <li>{@link #testSubListCreation()} - Verifica che la subList creata con indici validi abbia
 * size 3 e contenga {@code [B, C, D]} nell'ordine corretto.</li>
 * <li>{@link #testGet()} - Verifica che {@link HList#get(int)} sulla subList restituisca
 * gli elementi corretti e lanci {@link IndexOutOfBoundsException} per indici fuori intervallo.</li>
 * <li>{@link #testContainsAndIndexOf()} - Verifica che {@link HCollection#contains(Object)} e
 * {@link HList#indexOf(Object)} funzionino sulla vista per elementi presenti, assenti e fuori range.</li>
 * <li>{@link #testContainsAllOnView()} - Verifica che {@link HCollection#containsAll(HCollection)}
 * sulla subList veda solo gli elementi visibili nella vista.</li>
 * <li>{@link #testLastIndexOf()} - Verifica che {@link HList#lastIndexOf(Object)} sulla vista
 * funzioni correttamente dopo l'inserimento di un duplicato.</li>
 * <li>{@link #testToArray()} - Verifica che {@link HCollection#toArray()} sulla subList restituisca
 * un nuovo array indipendente con {@code [B, C, D]}.</li>
 * <li>{@link #testToArrayWithArray()} - Verifica {@link HCollection#toArray(Object[])} sulla vista
 * con array troppo piccolo, esatto e più grande.</li>
 * <li>{@link #testEqualsAndHashCodeWithEquivalentList()} - Verifica che la vista sia equals
 * a un VectorAdapter equivalente con lo stesso contenuto, con hash code coerente.</li>
 * </ul>
 * <p>
 * <b>B) Modifica posizionale (8 test)</b>
 * </p>
 * <ul>
 * <li>{@link #testSet()} - Verifica che {@link HList#set(int,Object)} sulla subList modifichi
 * l'elemento nella vista e nella lista originale, restituendo il precedente.</li>
 * <li>{@link #testAdd()} - Verifica che {@link HList#add(int,Object)} inserisca l'elemento
 * nella posizione corretta della vista e della lista originale.</li>
 * <li>{@link #testRemove()} - Verifica che {@link HList#remove(int)} rimuova l'elemento
 * dalla vista e dalla lista originale restituendolo.</li>
 * <li>{@link #testSetOutOfBounds()} - Verifica che {@link HList#set(int,Object)} lanci
 * {@link IndexOutOfBoundsException} per indici relativi alla vista e non al backing.</li>
 * <li>{@link #testRemoveOutOfBounds()} - Verifica che {@link HList#remove(int)} lanci
 * {@link IndexOutOfBoundsException} per indici relativi alla vista e non al backing.</li>
 * <li>{@link #testAddIndexOutOfBounds()} - Verifica che {@link HList#add(int,Object)} lanci
 * {@link IndexOutOfBoundsException} per indici oltre la size della vista.</li>
 * <li>{@link #testNullElementsThroughView()} - Verifica che {@code null} possa transitare
 * attraverso la vista ed essere visibile nella lista originale.</li>
 * <li>{@link #testAddAllAtIndexOutOfBounds()} - Verifica che {@code addAll(int, HCollection)} sulla vista
 * lanci {@link IndexOutOfBoundsException} per indici fuori intervallo.</li>
 * </ul>
 * <p>
 * <b>C) Operazioni bulk (7 test)</b>
 * </p>
 * <ul>
 * <li>{@link #testAddAll()} - Verifica che {@link HCollection#addAll(HCollection)} aggiunga
 * gli elementi in coda alla vista e alla lista originale.</li>
 * <li>{@link #testAddAllAtIndex()} - Verifica che {@link HList#addAll(int,HCollection)} inserisca
 * gli elementi alla posizione specificata della vista.</li>
 * <li>{@link #testRemoveAll()} - Verifica che {@link HCollection#removeAll(HCollection)} rimuova
 * dalla vista e dall'originale tutti gli elementi specificati.</li>
 * <li>{@link #testRetainAll()} - Verifica che {@link HCollection#retainAll(HCollection)} conservi
 * solo gli elementi specificati nella vista e nell'originale.</li>
 * <li>{@link #testAddAllEmptyCollectionReturnsFalse()} - Verifica che addAll con argomento collezione vuota
 * restituisca false e non modifichi la vista.</li>
 * <li>{@link #testClear()} - Verifica che {@link HCollection#clear()} svuoti la vista e rimuova
 * gli elementi corrispondenti dalla lista originale.</li>
 * <li>{@link #testRemoveAllEmptyCollectionNoOp()} - Verifica che {@code removeAll} con collezione vuota
 * restituisca false e non modifichi la vista.</li>
 * </ul>
 * <p>
 * <b>D) Iteratori (4 test)</b>
 * </p>
 * <ul>
 * <li>{@link #testIteratorRemove()} - Verifica che {@link HIterator#remove()} durante
 * l'iterazione sulla vista rimuova l'elemento dalla vista e dalla lista originale.</li>
 * <li>{@link #testIteratorNoSuchElementException()} - Verifica che l'iteratore sulla vista
 * estragga esattamente {@code [B, C, D]} e lanci {@link NoSuchElementException} a esaurimento.</li>
 * <li>{@link #testListIteratorOperations()} - Verifica le operazioni combinate
 * {@link HListIterator#set(Object)}, {@link HListIterator#add(Object)} e
 * {@link HIterator#remove()} sul listIterator della vista.</li>
 * <li>{@link #testListIteratorNavigationAndIndices()} - Verifica la navigazione avanti e
 * indietro del listIterator con i valori di {@code nextIndex} e {@code previousIndex} ai bordi.</li>
 * </ul>
 * <p>
 * <b>E) Controlli di bounds e casi limite (3 test)</b>
 * </p>
 * <ul>
 * <li>{@link #testSubListBounds()} - Verifica che la creazione di una subList con indici
 * non validi ({@code fromIndex<0, toIndex>size, fromIndex>toIndex}) lanci {@link IndexOutOfBoundsException}.</li>
 * <li>{@link #testListIteratorIndexOutOfBounds()} - Verifica che {@link HList#listIterator(int)}
 * sulla vista applichi i limiti relativi alla sua dimensione.</li>
 * <li>{@link #testEmptySubList()} - Verifica che una subList vuota ({@code fromIndex==toIndex})
 * sia creata correttamente e che le operazioni funzionino come per una lista vuota.</li>
 * </ul>
 * <p>
 * <b>F) Vista e lista originale (4 test)</b>
 * </p>
 * <ul>
 * <li>{@link #testModifyOriginalInsideRange()} - Verifica che modifiche sulla lista originale
 * all'interno dell'intervallo della subList si riflettano sulla vista.</li>
 * <li>{@link #testModifyOriginalOutsideRange()} - Verifica che modifiche sulla lista originale
 * all'esterno dell'intervallo non compromettano la stabilità della vista.</li>
 * <li>{@link #testSubListOfSubList()} - Verifica la creazione e il funzionamento di una subList
 * annidata con propagazione delle modifiche su tutti i livelli.</li>
 * <li>{@link #testClearOnNestedSubList()} - Verifica che {@link HCollection#clear()} su una
 * subList annidata rimuova gli elementi da tutti i livelli della gerarchia di viste.</li>
 * </ul>
 */
public class SubListAdapterTest {

    /**
     * Costruttore pubblico di default.
     */
    public SubListAdapterTest() {}

    private VectorAdapter original;
    private HList sub;

    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static final String D = "D";
    private static final String E = "E";
    private static final String X = "X";
    private static final String Y = "Y";
    private static final String Z = "Z";

    /**
     * Costruisce la lista originale [A, B, C, D, E] e ne estrae la subList
     * [B, C, D] da utilizzare nei test.
     */
    @Before
    public void setUp() {
        original = new VectorAdapter();
        original.add(A);
        original.add(B);
        original.add(C);
        original.add(D);
        original.add(E);
        sub = original.subList(1, 4); // [B, C, D]
    }

    /**
     * Crea una collezione HCollection con gli elementi specificati.
     */
    private HCollection makeCollection(Object... elements) {
        VectorAdapter c = new VectorAdapter();
        for (Object e : elements) {
            c.add(e);
        }
        return c;
    }

    // ====================================================================
    // Costruzione e lettura
    // ====================================================================

    /**
     * <p>
     * <b>Summary</b> Verifica che la subList creata con indici validi abbia
     * la dimensione e il contenuto corretti, e che isEmpty rifletta lo stato.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale di creazione di una vista backed con
     * indici interni alla lista. La subList(1,4) su [A,B,C,D,E] deve produrre
     * una vista [B,C,D] con size 3: gli indici fromIndex=1 e toIndex=4 sono
     * entro i limiti [0,size] con fromIndex &lt; toIndex. La verifica controlla
     * i quattro invarianti post-creazione della vista: size, isEmpty, e la
     * corrispondenza degli elementi accessibili per indice. La dimensione della
     * vista (3) coincide con toIndex - fromIndex, come prescritto dal contratto
     * {@link HList#subList(int, int)}.
     * </p>
     * <p>
     * <b>Test Description</b> Dalla vista setUp sub = original.subList(1,4) si
     * leggono size, isEmpty e i tre elementi tramite get(0), get(1), get(2).
     * Le asserzioni verificano che size valga 3, isEmpty restituisca false, e
     * che B, C, D siano accessibili rispettivamente alle posizioni 0, 1, 2 della
     * vista.
     * </p>
     * <p>
     * <b>Pre-Condition</b> original = [A,B,C,D,E], sub = original.subList(1,4).
     * </p>
     * <p>
     * <b>Post-Condition</b> Nessuna modifica.
     * </p>
     * <p>
     * <b>Expected Results</b> sub.size() == 3, sub.isEmpty() == false,
     * sub.get(0)==B, sub.get(1)==C, sub.get(2)==D.
     * </p>
     */
    @Test
    public void testSubListCreation() {
        assertEquals(3, sub.size());
        assertFalse(sub.isEmpty());
        assertEquals(B, sub.get(0));
        assertEquals(C, sub.get(1));
        assertEquals(D, sub.get(2));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code get(int)} sulla subList restituisca
     * l'elemento corretto e lanci {@link IndexOutOfBoundsException} per indici
     * fuori intervallo.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale di lettura posizionale con
     * verifiche di bounds rispetto alla dimensione della vista. Gli indici
     * validi [0,1,2] coprono tutti i posizioni della vista di size 3; le
     * minime violazioni -1 e 3 rappresentano rispettivamente il limite
     * inferiore e superiore dell'intervallo valido [0,size). Il contratto
     * {@link HList#get(int)} prescrive {@link IndexOutOfBoundsException}
     * per indici fuori intervallo. Si verifica anche che gli indici 0 e 2
     * (bordi della vista) restituiscano B e D, non A ed E della lista
     * originale, confermando che la vista maschera correttamente gli
     * elementi esterni all'intervallo.
     * </p>
     * <p>
     * <b>Test Description</b> Si leggono get(0), get(1), get(2) sulla vista
     * e si controllano i valori attesi B, C, D. Si tentano quindi get(-1) e
     * get(3) tramite try/fail/catch attendendo IndexOutOfBoundsException per
     * ciascuna delle due violazioni di bounds.
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D].
     * </p>
     * <p>
     * <b>Post-Condition</b> sub invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> get validi restituiscono gli elementi, get(-1)
     * e get(3) lanciano IndexOutOfBoundsException.
     * </p>
     */
    @Test
    public void testGet() {
        assertEquals(B, sub.get(0));
        assertEquals(C, sub.get(1));
        assertEquals(D, sub.get(2));
        try {
            sub.get(-1);
            fail("get(-1) deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }
        try {
            sub.get(3);
            fail("get(3) deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code contains} e {@code indexOf} funzionino
     * correttamente sulla vista.
     * </p>
     * <p>
     * <b>Test Case Design</b> Ricerca di elementi presenti e assenti nella
     * vista, con distinzione tra elementi interni all'intervallo (B, C, D)
     * e elementi esterni alla lista originale (A, E) o mai inseriti (X).
     * La presenza di A e E nella lista originale ma non nella subList
     * verifica che contains e indexOf operino esclusivamente sugli elementi
     * visibili nella vista. L'elemento X non è presente in nessun livello.
     * {@link HList#indexOf(Object)} deve restituire indici relativi alla
     * vista: B è all'indice 0 della vista (non 1 dell'originale).
     * </p>
     * <p>
     * <b>Test Description</b> Si invocano contains con B, C, D (atteso true),
     * poi con A, E, X (atteso false). Si verifica indexOf per B (atteso 0),
     * D (atteso 2) e X (atteso -1), confermando che gli indici sono relativi
     * alla vista e non alla lista di backing.
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D], original = [A,B,C,D,E].
     * </p>
     * <p>
     * <b>Post-Condition</b> sub invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> contains restituisce true solo per elementi
     * visibili nella subList; indexOf restituisce l'indice relativo alla vista.
     * </p>
     */
    @Test
    public void testContainsAndIndexOf() {
        assertTrue(sub.contains(B));
        assertTrue(sub.contains(C));
        assertTrue(sub.contains(D));
        assertFalse(sub.contains(A));
        assertFalse(sub.contains(E));
        assertFalse(sub.contains(X));

        assertEquals(0, sub.indexOf(B));
        assertEquals(2, sub.indexOf(D));
        assertEquals(-1, sub.indexOf(X));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code containsAll} sulla subList veda solo gli
     * elementi della vista e non quelli esterni all'intervallo.
     * </p>
     * <p>
     * <b>Test Case Design</b> Ricerca bulk con collezioni che intersecano
     * parzialmente o totalmente la vista: [B,C] è un sottoinsieme puro
     * della vista, [A] contiene solo elementi esterni all'intervallo, [C,X]
     * è parzialmente presente e parzialmente assente, la collezione vuota
     * attiva il caso vacuoso del contratto containsAll. Si verifica che il
     * metodo operi esclusivamente sulla proiezione visibile della vista,
     * ignorando gli elementi della lista originale al di fuori
     * dell'intervallo [1,4).
     * </p>
     * <p>
     * <b>Test Description</b> Si invoca containsAll con makeCollection(B,C),
     * makeCollection(A), makeCollection(C,X) e makeCollection() sulla vista
     * sub = [B,C,D]. Si verifica che solo la prima collezione restituisca
     * true; le altre restituiscono false rispettivamente per assenza totale
     * nella vista (A), assenza parziale (X) e per vacuità (collezione vuota
     * restituisce true).
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D], original = [A,B,C,D,E].
     * </p>
     * <p>
     * <b>Post-Condition</b> Nessuna modifica.
     * </p>
     * <p>
     * <b>Expected Results</b> true solo se ogni elemento della collezione è
     * visibile nella vista: [B,C] true, [A] false, [C,X] false, [] true.
     * </p>
     */
    @Test
    public void testContainsAllOnView() {
        assertTrue(sub.containsAll(makeCollection(B, C)));
        assertFalse(sub.containsAll(makeCollection(A)));
        assertFalse(sub.containsAll(makeCollection(C, X)));
        assertTrue(sub.containsAll(makeCollection()));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code lastIndexOf} sulla vista funzioni
     * correttamente per elementi duplicati.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale di ricerca all'indietro con
     * duplicati. L'inserimento di un secondo B alla fine della vista
     * (posizione 3) crea la situazione [B,C,D,B] dove lastIndexOf(B) deve
     * restituire 3 (ultima occorrenza nella vista). La verifica sulla lista
     * originale [A,B,C,D,B,E] conferma che l'inserimento tramite la vista
     * si propaga correttamente: lastIndexOf(B) su original deve restituire
     * 4, ossia la posizione assoluta del nuovo B nell'originale.
     * </p>
     * <p>
     * <b>Test Description</b> Si inserisce B in coda alla vista tramite
     * sub.add(B), ottenendo sub = [B,C,D,B] e original = [A,B,C,D,B,E].
     * Si verifica lastIndexOf(B) == 3 sulla vista e lastIndexOf(B) == 4
     * sulla lista originale.
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D].
     * </p>
     * <p>
     * <b>Post-Condition</b> sub = [B,C,D,B], original = [A,B,C,D,B,E].
     * </p>
     * <p>
     * <b>Expected Results</b> lastIndexOf(B) == 3.
     * </p>
     */
    @Test
    public void testLastIndexOf() {
        sub.add(B);
        assertEquals(3, sub.lastIndexOf(B));
        // verifica che anche original sia aggiornato
        assertEquals(4, original.lastIndexOf(B)); // posizione 4 in originale (dopo l'originale B in posizione 1 e il nuovo B in posizione 4)
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code toArray()} sulla subList restituisca
     * un array contenente gli elementi della vista nell'ordine corretto.
     * </p>
     * <p>
     * <b>Test Case Design</b> Conversione della vista in array senza argomenti.
     * {@link HCollection#toArray()} deve restituire un nuovo array Object[]
     * indipendente dalla vista con gli elementi [B,C,D] nell'ordine della
     * vista. La verifica dell'indipendenza conferma che le modifiche
     * all'array non si propagano alla vista, come prescritto dal contratto
     * Collection.toArray di J2SE 1.4.2.
     * </p>
     * <p>
     * <b>Test Description</b> Si invoca sub.toArray() e si verifica che la
     * lunghezza sia 3 e che gli elementi arr[0], arr[1], arr[2] siano
     * rispettivamente B, C, D. Si sovrascrive arr[0] con X e si conferma
     * che sub.get(0) resti B, dimostrando l'indipendenza della copia.
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D].
     * </p>
     * <p>
     * <b>Post-Condition</b> sub invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Array corretto, indipendente.
     * </p>
     */
    @Test
    public void testToArray() {
        Object[] arr = sub.toArray();
        assertEquals(3, arr.length);
        assertEquals(B, arr[0]);
        assertEquals(C, arr[1]);
        assertEquals(D, arr[2]);
        arr[0] = X;
        assertEquals(B, sub.get(0)); // la modifica dell'array non tocca la lista
    }

    /**
     * <p>
     * <b>Summary</b> Verifica {@code toArray(Object[])} sulla vista nelle tre
     * situazioni previste dal contratto: array troppo piccolo, esatto, più grande.
     * </p>
     * <p>
     * <b>Test Case Design</b> Le tre frontiere della lunghezza dell'array
     * fornito rispetto alla size della vista: array di lunghezza 0 (0 &lt; 3,
     * troppo piccolo), array di lunghezza 3 (esattamente size) e array di
     * lunghezza 5 (size &lt; length, più grande). Il contratto
     * {@link HCollection#toArray(Object[])} prescrive che se l'array è
     * troppo piccolo venga allocato uno nuovo, se è sufficientemente grande
     * venga riutilizzato con un terminatore null in posizione size. La
     * verifica con large[3]=X, large[4]=Y conferma che la sentinella null
     * sovrascrive solo la posizione size, lasciando intatte le posizioni
     * successive.
     * </p>
     * <p>
     * <b>Test Description</b> Si invoca toArray con un array vuoto (atteso
     * nuovo array di lunghezza 3 con [B,C,D]), con un array esatto (atteso
     * lo stesso array riempito), e con un array più grande in cui le
     * posizioni 3 e 4 contengono X e Y. Si verifica che il risultato sia
     * lo stesso array (assertSame), che large[3] diventi null e large[4]
     * resti Y.
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D].
     * </p>
     * <p>
     * <b>Post-Condition</b> sub invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Array piccolo: nuovo array; array esatto o grande:
     * stessa istanza (assertSame); sentinella null solo quando length &gt; size.
     * </p>
     */
    @Test
    public void testToArrayWithArray() {
        Object[] result = sub.toArray(new Object[0]);
        assertEquals(3, result.length);
        assertEquals(B, result[0]);
        assertEquals(C, result[1]);
        assertEquals(D, result[2]);

        Object[] exact = new Object[3];
        assertSame(exact, sub.toArray(exact));
        assertEquals(B, exact[0]);
        assertEquals(C, exact[1]);
        assertEquals(D, exact[2]);

        Object[] large = new Object[5];
        large[3] = X;
        large[4] = Y;
        assertSame(large, sub.toArray(large));
        assertNull(large[3]);      // terminatore null sovrascrive X
        assertEquals(Y, large[4]); // posizione oltre la sentinella intatta
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che la vista sia equals a una lista distinta ma
     * con lo stesso contenuto, con hash code coerente.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale di eguaglianza tra implementazioni
     * diverse (SubList vs VectorAdapter) con contenuto identico [B,C,D].
     * Il contratto {@link HList#equals(Object)} prescrive che due liste siano
     * uguali se contengono gli stessi elementi nello stesso ordine, a
     * prescindere dalla classe concreta. La verifica della simmetria
     * (sub.equals(equivalent) e equivalent.equals(sub)) e della coerenza
     * hashCode (identico per liste uguali) completa la validazione del
     * contratto equals/hashCode.
     * </p>
     * <p>
     * <b>Test Description</b> Si costruisce un VectorAdapter con gli stessi
     * elementi della vista [B,C,D]. Si verifica sub.equals(equivalent) e
     * equivalent.equals(sub) (entrambi true), e si conferma
     * sub.hashCode() == equivalent.hashCode().
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D].
     * </p>
     * <p>
     * <b>Post-Condition</b> Nessuna modifica.
     * </p>
     * <p>
     * <b>Expected Results</b> equals true in entrambe le direzioni e hashCode
     * identico, nonostante le classi concrete siano diverse.
     * </p>
     */
    @Test
    public void testEqualsAndHashCodeWithEquivalentList() {
        HList equivalent = new VectorAdapter();
        equivalent.add(B);
        equivalent.add(C);
        equivalent.add(D);

        assertTrue(sub.equals(equivalent));
        assertTrue(equivalent.equals(sub));
        assertEquals(sub.hashCode(), equivalent.hashCode());
    }

    // ====================================================================
    // Operazioni di modifica posizionale
    // ====================================================================

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code set} su subList modifichi l'elemento
     * nella vista e nella lista originale.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale di sostituzione posizionale sulla
     * vista con verifica integrata della propagazione alla lista di backing.
     * set(1,X) sulla vista corrisponde alla posizione 2 della lista
     * originale (offset 1 + indice 1): il vecchio elemento C deve essere
     * restituito e sostituito da X sia nella vista che nell'originale. Il
     * contratto {@link HList#set(int, Object)} prescrive la restituzione
     * del precedente e la modifica in-place; la verifica su entrambi i
     * livelli conferma la semantica backed della vista.
     * </p>
     * <p>
     * <b>Test Description</b> Si esegue sub.set(1, X) e si verifica che il
     * valore restituito sia C. Si controlla quindi che sub.get(1) e
     * original.get(2) restituiscano X, confermando la modifica visibile da
     * entrambi i livelli.
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D], original = [A,B,C,D,E].
     * </p>
     * <p>
     * <b>Post-Condition</b> sub = [B,X,D], original = [A,B,X,D,E].
     * </p>
     * <p>
     * <b>Expected Results</b> get(1) sulla vista e get(2) sull'originale
     * restituiscono X.
     * </p>
     */
    @Test
    public void testSet() {
        Object old = sub.set(1, X);
        assertEquals(C, old);
        assertEquals(X, sub.get(1));
        assertEquals(X, original.get(2)); // posizione 2 in originale (0:A,1:B,2:C,3:D,4:E) -> ora X
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code add(int,Object)} su subList inserisca
     * l'elemento nella posizione corretta della vista e della lista originale.
     * </p>
     * <p>
     * <b>Test Case Design</b> Inserimento posizionale in tre punti strategici
     * della vista: testa (indice 0), centro (indice 2) e coda (indice
     * sub.size()). Il contratto {@link HList#add(int, Object)} prescrive che
     * gli elementi dalla posizione specificata in poi siano shiftati a
     * destra e che la size aumenti di 1 per ogni inserimento. Le tre
     * inserizioni su una vista di size 3 producono una vista di size 6 e
     * una lista originale di size 8 (A + 6 + E). La verifica dell'ordine
     * completo in entrambi i livelli conferma la corretta propagazione
     * dell'inserimento attraverso la backed view.
     * </p>
     * <p>
     * <b>Test Description</b> Si inserisce X all'inizio (sub.add(0,X)),
     * Y al centro (sub.add(2,Y)) e Z in coda (sub.add(sub.size(),Z)).
     * Si verifica size e contenuto della vista [X,B,Y,C,D,Z] e
     * dell'originale [A,X,B,Y,C,D,Z,E] con controlli posizione per
     * posizione.
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D], original = [A,B,C,D,E].
     * </p>
     * <p>
     * <b>Post-Condition</b> sub = [X,B,Y,C,D,Z], original = [A,X,B,Y,C,D,Z,E].
     * </p>
     * <p>
     * <b>Expected Results</b> Ordine e dimensioni corretti sia nella vista
     * che nell'originale.
     * </p>
     */
    @Test
    public void testAdd() {
        sub.add(0, X);          // [X,B,C,D]
        sub.add(2, Y);          // [X,B,Y,C,D]
        sub.add(sub.size(), Z); // [X,B,Y,C,D,Z]

        assertEquals(6, sub.size());
        assertEquals(8, original.size()); // A + 6 elementi + E = 8

        assertEquals(X, sub.get(0));
        assertEquals(B, sub.get(1));
        assertEquals(Y, sub.get(2));
        assertEquals(C, sub.get(3));
        assertEquals(D, sub.get(4));
        assertEquals(Z, sub.get(5));

        // verifica su original
        assertEquals(A, original.get(0));
        assertEquals(X, original.get(1));
        assertEquals(B, original.get(2));
        assertEquals(Y, original.get(3));
        assertEquals(C, original.get(4));
        assertEquals(D, original.get(5));
        assertEquals(Z, original.get(6));
        assertEquals(E, original.get(7));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code remove(int)} su subList rimuova
     * l'elemento dalla vista e dalla lista originale.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale di rimozione posizionale dalla vista
     * con verifica della propagazione alla lista di backing. La rimozione
     * dell'elemento centrale (indice 1, valore C) da una vista di size 3
     * produce una vista di size 2 [B,D] e una lista originale di size 4
     * [A,B,D,E]. Il contratto {@link HList#remove(int)} prescrive la
     * restituzione dell'elemento rimosso e lo shift a sinistra degli
     * elementi successivi. Il test complementare
     * {@link #testRemoveOutOfBounds()} verifica i casi di errore ai
     * bordi dell'intervallo.
     * </p>
     * <p>
     * <b>Test Description</b> Si invoca sub.remove(1) e si verifica che il
     * valore restituito sia C. Si controlla che sub.size() sia 2 con
     * elementi [B,D], e che original.size() sia 4 con elementi [A,B,D,E]
     * nell'ordine corretto.
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D], original = [A,B,C,D,E].
     * </p>
     * <p>
     * <b>Post-Condition</b> sub = [B,D], original = [A,B,D,E].
     * </p>
     * <p>
     * <b>Expected Results</b> L'elemento rimosso è C, le dimensioni diminuiscono.
     * </p>
     */
    @Test
    public void testRemove() {
        Object removed = sub.remove(1);
        assertEquals(C, removed);
        assertEquals(2, sub.size());
        assertEquals(4, original.size());
        assertEquals(B, sub.get(0));
        assertEquals(D, sub.get(1));
        assertEquals(A, original.get(0));
        assertEquals(B, original.get(1));
        assertEquals(D, original.get(2));
        assertEquals(E, original.get(3));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code set(int,Object)} sulla vista accetti
     * solo indici relativi alla sua dimensione e non a quella del backing.
     * </p>
     * <p>
     * <b>Test Case Design</b> Classi di errore per violazioni di bounds su
     * set: le minime violazioni -1 e 3 dell'intervallo valido [0,size) della
     * vista. La posizione 3 è particolarmente significativa perché sarebbe
     * valida sul backing (index 3 = elemento D) ma deve fallire sulla vista
     * di size 3. Il contratto {@link HList#set(int, Object)} prescrive
     * {@link IndexOutOfBoundsException} per indici fuori intervallo; la
     * verifica post-eccezione conferma che nessuna scrittura parziale sia
     * avvenuta su vista o originale (atomicità dell'operazione).
     * </p>
     * <p>
     * <b>Test Description</b> Si tentano set(-1,X) e set(3,X) tramite
     * try/fail/catch attendendo IndexOutOfBoundsException per ciascuna.
     * Dopo le eccezioni si verifica che sub.size() resti 3, original.size()
     * resti 5, sub.get(1) resti C e original.get(4) resti E.
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D], original = [A,B,C,D,E].
     * </p>
     * <p>
     * <b>Post-Condition</b> Nessuna modifica (nessuna scrittura parziale).
     * </p>
     * <p>
     * <b>Expected Results</b> IndexOutOfBoundsException per -1 e 3; sub.size()
     * resta 3, original.size() resta 5 e sub.get(1) resta C.
     * </p>
     */
    @Test
    public void testSetOutOfBounds() {
        try {
            sub.set(-1, X);
            fail("set(-1) deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }
        try {
            sub.set(3, X);
            fail("set(3) deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }

        assertEquals(3, sub.size());
        assertEquals(5, original.size());
        assertEquals(C, sub.get(1));
        assertEquals(E, original.get(4));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code remove(int)} sulla vista accetti solo
     * indici relativi alla sua dimensione e non a quella del backing.
     * </p>
     * <p>
     * <b>Test Case Design</b> Classi di errore per violazioni di bounds su
     * remove: le minime violazioni -1 e 3 dell'intervallo valido [0,size)
     * della vista. Analogamente a {@link #testSetOutOfBounds()}, la posizione
     * 3 è valida sul backing ma non sulla vista. Il contratto
     * {@link HList#remove(int)} prescrive {@link IndexOutOfBoundsException}
     * e restituzione dell'elemento rimosso; la verifica post-eccezione
     * conferma che nessuna rimozione parziale sia avvenuta, testando
     * l'atomicità dell'operazione su vista e originale.
     * </p>
     * <p>
     * <b>Test Description</b> Si tentano remove(-1) e remove(3) tramite
     * try/fail/catch attendendo IndexOutOfBoundsException per ciascuna.
     * Dopo le eccezioni si verifica che sub.size() resti 3, original.size()
     * resti 5, e che tutti gli elementi siano ancora accessibili nelle
     * posizioni originarie.
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D], original = [A,B,C,D,E].
     * </p>
     * <p>
     * <b>Post-Condition</b> Nessuna modifica (nessuna rimozione parziale).
     * </p>
     * <p>
     * <b>Expected Results</b> IndexOutOfBoundsException per -1 e 3; dimensioni
     * invariate (3 e 5) e contenuto intatto.
     * </p>
     */
    @Test
    public void testRemoveOutOfBounds() {
        try {
            sub.remove(-1);
            fail("remove(-1) deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }
        try {
            sub.remove(3);
            fail("remove(3) deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }

        assertEquals(3, sub.size());
        assertEquals(5, original.size());
        assertEquals(B, sub.get(0));
        assertEquals(D, sub.get(2));
        assertEquals(A, original.get(0));
        assertEquals(E, original.get(4));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code add(int,Object)} sulla vista accetti
     * solo indici di inserimento relativi alla sua dimensione.
     * </p>
     * <p>
     * <b>Test Case Design</b> Classi di errore per violazioni dei limiti
     * dell'intervallo di inserimento [0,size] della vista. Le minime
     * violazioni -1 e 4 (size della vista) testano entrambi i bordi: add(-1)
     * viola il limite inferiore, add(4,X) corrisponderebbe a una posizione
     * valida del backing (dopo E) ma deve fallire perché 4 &gt; size della
     * vista. Il contratto {@link HList#add(int, Object)} prescrive
     * {@link IndexOutOfBoundsException} per indici non in [0,size]; la
     * verifica post-eccezione conferma che nessun inserimento parziale sia
     * avvenuto su vista o originale.
     * </p>
     * <p>
     * <b>Test Description</b> Si tentano add(-1,X) e add(4,X) tramite
     * try/fail/catch attendendo IndexOutOfBoundsException per ciascuna.
     * Dopo le eccezioni si verifica che sub.size() resti 3,
     * original.size() resti 5 e original.get(4) resti E.
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D], original = [A,B,C,D,E].
     * </p>
     * <p>
     * <b>Post-Condition</b> Nessuna modifica (nessun inserimento parziale).
     * </p>
     * <p>
     * <b>Expected Results</b> IndexOutOfBoundsException per -1 e 4; dimensioni
     * invariate (3 e 5) e ultimo elemento del backing ancora E.
     * </p>
     */
    @Test
    public void testAddIndexOutOfBounds() {
        try {
            sub.add(-1, X);
            fail("add(-1,X) deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }
        try {
            sub.add(4, X);
            fail("add(4,X) deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }

        assertEquals(3, sub.size());
        assertEquals(5, original.size());
        assertEquals(E, original.get(4));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che null possa transitare attraverso la vista ed
     * essere visibile anche nella lista originale.
     * </p>
     * <p>
     * <b>Test Case Design</b> Elemento speciale null inserito in coda alla vista
     * tramite {@link HCollection#add(Object)}. La collezione supporta null
     * come elemento valido e il contratto non impone restrizioni; la verifica
     * conferma che null sia leggibile dalla vista (get(3)) e dalla lista
     * originale (get(4)), dimostrando che il riferimento nullo transita
     * correttamente attraverso la backed view. L'elemento E viene shiftato
     * in posizione 5 nell'originale, confermando l'inserimento posizionale.
     * </p>
     * <p>
     * <b>Test Description</b> Si esegue sub.add(null) e si verifica che la
     * vista abbia size 4 con sub.get(3) == null. Si controlla che
     * original.size() sia 6, che original.get(4) sia null e che E sia
     * accessibile in original.get(5).
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D], original = [A,B,C,D,E].
     * </p>
     * <p>
     * <b>Post-Condition</b> sub = [B,C,D,null], original = [A,B,C,D,null,E].
     * </p>
     * <p>
     * <b>Expected Results</b> null leggibile da vista (indice 3) e originale
     * (indice 4); dimensioni 4 e 6.
     * </p>
     */
    @Test
    public void testNullElementsThroughView() {
        sub.add(null);
        assertEquals(4, sub.size());
        assertNull(sub.get(3));
        assertEquals(6, original.size());
        assertNull(original.get(4));
        assertEquals(E, original.get(5));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code addAll(int, HCollection)} sulla vista
     * lanci {@link IndexOutOfBoundsException} per indici fuori intervallo.
     * </p>
     * <p>
     * <b>Test Case Design</b> Le minime violazioni dell'intervallo valido
     * [0, size] della vista per addAll posizionale. Il contratto
     * {@link HList#addAll(int, HCollection)} prescrive IndexOutOfBoundsException
     * per indici negativi o maggiori di size. Complementa testAddIndexOutOfBounds
     * che testa add posizionale singolo.
     * </p>
     * <p>
     * <b>Test Description</b> Su sub = [B,C,D], si tentano addAll(-1, coll) e
     * addAll(4, coll) attendendo IndexOutOfBoundsException. Si verifica che la
     * vista rimanga invariata.
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D], coll = [X,Y].
     * </p>
     * <p>
     * <b>Post-Condition</b> sub invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> IndexOutOfBoundsException per entrambi i tentativi.
     * </p>
     */
    @Test
    public void testAddAllAtIndexOutOfBounds() {
        HCollection coll = makeCollection(X, Y);
        try {
            sub.addAll(-1, coll);
            fail("addAll(-1) deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }
        try {
            sub.addAll(4, coll);
            fail("addAll(4) deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }
        assertEquals(3, sub.size());
    }

    // ====================================================================
    // Operazioni bulk
    // ====================================================================

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code addAll(HCollection)} su subList
     * aggiunga gli elementi in coda alla vista e all'originale.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale di append bulk sulla vista tramite
     * {@link HCollection#addAll(HCollection)}. L'aggiunta di [X,Y] in coda
     * alla vista [B,C,D] produce [B,C,D,X,Y] con size 5 e aggiorna la
     * lista originale a [A,B,C,D,X,Y,E] con size 7. Il contratto prescrive
     * che il valore di ritorno sia true se la collezione è stata modificata.
     * La verifica dell'ordine in entrambi i livelli conferma che gli
     * elementi siano stati inseriti nella posizione corretta della vista
     * con propagazione alla backed list.
     * </p>
     * <p>
     * <b>Test Description</b> Si invoca sub.addAll(makeCollection(X,Y)) e si
     * verifica che restituisca true. Si controllano sub.size() == 5 e
     * original.size() == 7, e l'ordine degli elementi nella vista [B,C,D,X,Y]
     * e nell'originale [A,B,C,D,X,Y,E].
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D], original = [A,B,C,D,E].
     * </p>
     * <p>
     * <b>Post-Condition</b> sub = [B,C,D,X,Y], original = [A,B,C,D,X,Y,E].
     * </p>
     * <p>
     * <b>Expected Results</b> true, size aumentata, ordine preservato.
     * </p>
     */
    @Test
    public void testAddAll() {
        HCollection coll = makeCollection(X, Y);
        assertTrue(sub.addAll(coll));
        assertEquals(5, sub.size());
        assertEquals(7, original.size());
        assertEquals(X, sub.get(3));
        assertEquals(Y, sub.get(4));
        assertEquals(X, original.get(4)); // posizione originale: 0:A,1:B,2:C,3:D,4:X,5:Y,6:E
        assertEquals(Y, original.get(5));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code addAll(int, HCollection)} su subList
     * inserisca gli elementi alla posizione specificata.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale di inserimento bulk posizionale
     * tramite {@link HList#addAll(int, HCollection)}. L'inserimento di
     * [X,Y] in posizione 1 della vista [B,C,D] produce [B,X,Y,C,D] con
     * size 5 e aggiorna la lista originale a [A,B,X,Y,C,D,E] con size 7.
     * La posizione 1 inserisce tra il primo e il secondo elemento della
     * vista; gli elementi da C in poi sono shiftati a destra. Il contratto
     * prescrive che gli elementi siano inseriti nella posizione specificata
     * mantenendo l'ordine della collezione sorgente.
     * </p>
     * <p>
     * <b>Test Description</b> Si invoca sub.addAll(1, makeCollection(X,Y)) e
     * si verifica che restituisca true. Si controllano sub.size() == 5 e
     * original.size() == 7, e l'ordine completo degli elementi nella vista
     * [B,X,Y,C,D] e nell'originale [A,B,X,Y,C,D,E].
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D].
     * </p>
     * <p>
     * <b>Post-Condition</b> sub = [B,X,Y,C,D], original = [A,B,X,Y,C,D,E].
     * </p>
     * <p>
     * <b>Expected Results</b> true, ordine e dimensioni aggiornate.
     * </p>
     */
    @Test
    public void testAddAllAtIndex() {
        HCollection coll = makeCollection(X, Y);
        assertTrue(sub.addAll(1, coll));
        assertEquals(5, sub.size());
        assertEquals(7, original.size());
        assertEquals(B, sub.get(0));
        assertEquals(X, sub.get(1));
        assertEquals(Y, sub.get(2));
        assertEquals(C, sub.get(3));
        assertEquals(D, sub.get(4));
        // original
        assertEquals(A, original.get(0));
        assertEquals(B, original.get(1));
        assertEquals(X, original.get(2));
        assertEquals(Y, original.get(3));
        assertEquals(C, original.get(4));
        assertEquals(D, original.get(5));
        assertEquals(E, original.get(6));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code removeAll(HCollection)} su subList
     * rimuova dalla vista e dall'originale tutti gli elementi contenuti
     * nella collezione specificata.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale di rimozione bulk dalla vista. La
     * rimozione di [B,D] dalla vista [B,C,D] lascia [C]; la propagazione
     * alla lista originale rimuove B e D anche da [A,B,C,D,E], producendo
     * [A,C,E]. Il contratto {@link HCollection#removeAll(HCollection)}
     * prescrive la rimozione di tutte le occorrenze degli elementi
     * contenuti nella collezione specificata e il valore true se la lista è
     * stata modificata. La verifica dell'ordine residuo in entrambi i
     * livelli conferma la corretta propagazione della rimozione.
     * </p>
     * <p>
     * <b>Test Description</b> Si invoca sub.removeAll(makeCollection(B,D)) e
     * si verifica che restituisca true. Si controllano sub.size() == 1 con
     * sub.get(0) == C, e original.size() == 3 con elementi [A,C,E].
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D].
     * </p>
     * <p>
     * <b>Post-Condition</b> sub = [C], original = [A,C,E].
     * </p>
     * <p>
     * <b>Expected Results</b> true, dimensioni ridotte, elementi rimasti corretti.
     * </p>
     */
    @Test
    public void testRemoveAll() {
        HCollection toRemove = makeCollection(B, D);
        assertTrue(sub.removeAll(toRemove));
        assertEquals(1, sub.size());
        assertEquals(3, original.size());
        assertEquals(C, sub.get(0));
        assertEquals(A, original.get(0));
        assertEquals(C, original.get(1));
        assertEquals(E, original.get(2));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code retainAll(HCollection)} su subList
     * conservi solo gli elementi contenuti nella collezione specificata.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale di intersezione bulk sulla vista. La
     * retensione di [B,D] dalla vista [B,C,D] elimina C, lasciando [B,D];
     * la propagazione alla lista originale rimuove C da [A,B,C,D,E],
     * producendo [A,B,D,E]. Il contratto {@link HCollection#retainAll(HCollection)}
     * prescrive di conservare solo gli elementi presenti nella collezione
     * specificata e restituire true se la lista è stata modificata. Il test
     * complementare {@link #testAddAllEmptyCollectionReturnsFalse()} verifica
     * il caso vacuoso.
     * </p>
     * <p>
     * <b>Test Description</b> Si invoca sub.retainAll(makeCollection(B,D)) e
     * si verifica che restituisca true. Si controllano sub.size() == 2 con
     * elementi [B,D], e original.size() == 4 con elementi [A,B,D,E].
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D].
     * </p>
     * <p>
     * <b>Post-Condition</b> sub = [B,D], original = [A,B,D,E].
     * </p>
     * <p>
     * <b>Expected Results</b> true, dimensioni ridotte, elementi conservati corretti.
     * </p>
     */
    @Test
    public void testRetainAll() {
        HCollection toRetain = makeCollection(B, D);
        assertTrue(sub.retainAll(toRetain));
        assertEquals(2, sub.size());
        assertEquals(4, original.size());
        assertEquals(B, sub.get(0));
        assertEquals(D, sub.get(1));
        assertEquals(A, original.get(0));
        assertEquals(B, original.get(1));
        assertEquals(D, original.get(2));
        assertEquals(E, original.get(3));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che addAll con una collezione vuota non modifichi
     * nulla e restituisca false, sia in forma append che posizionale.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso vacuoso per entrambe le varianti di
     * {@link HList#addAll(HCollection) addAll} (append e posizionale) con
     * collezione vuota. L'obiettivo è verificare che l'inserimento di zero
     * elementi produca il valore di ritorno false, lasci dimensioni e contenuto
     * della vista e della lista originale perfettamente invariati. Il test
     * copre la classe di equivalenza del caso vacuoso, complementare ai test
     * {@link #testAddAll() testAddAll} e {@link #testAddAllAtIndex() testAddAllAtIndex}
     * che eseguono insertions effettive.
     * </p>
     * <p>
     * <b>Test Description</b> Si invocano addAll(HCollection vuota) in forma
     * append e addAll(1, HCollection vuota) sulla vista sub. Si verifica che
     * entrambe restituiscano false, che sub.size() resti 3 e original.size()
     * resti 5. Si controlla inoltre che i singoli elementi della vista (B, C, D)
     * siano rimasti invariati in ordine.
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D], original = [A,B,C,D,E].
     * </p>
     * <p>
     * <b>Post-Condition</b> Nessuna modifica.
     * </p>
     * <p>
     * <b>Expected Results</b> false per entrambe le forme; sub.size() == 3 e
     * original.size() == 5.
     * </p>
     */
    @Test
    public void testAddAllEmptyCollectionReturnsFalse() {
        assertFalse(sub.addAll(makeCollection()));
        assertFalse(sub.addAll(1, makeCollection()));
        assertEquals(3, sub.size());
        assertEquals(5, original.size());
        assertEquals(B, sub.get(0));
        assertEquals(C, sub.get(1));
        assertEquals(D, sub.get(2));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code clear()} su subList svuoti la vista
     * e rimuova gli elementi corrispondenti dalla lista originale.
     * </p>
     * <p>
     * <b>Test Case Design</b> Operazione strutturale di svuotamento della vista
     * backed. L'obiettivo è verificare che clear() rimuova tutti gli elementi
     * dalla vista e che la rimozione si propaghi correttamente alla lista
     * originale, lasciando solo gli elementi esterni all'intervallo. Il test
     * valida la classe di equivalenza di cancellazione completa su una vista
     * non vuota, complementare a {@link #testClearOnNestedSubList() testClearOnNestedSubList}
     * che esegue la stessa operazione su una vista annidata.
     * </p>
     * <p>
     * <b>Test Description</b> Si invoca clear() sulla vista sub = [B,C,D]. Si
     * verifica che sub diventi vuota (isEmpty true, size 0). Si controlla che
     * original sia ridotta a [A,E] (size 2) con gli elementi esterni A ed E
     * ancora presenti e nell'ordine corretto.
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D], original = [A,B,C,D,E].
     * </p>
     * <p>
     * <b>Post-Condition</b> sub = [], original = [A,E].
     * </p>
     * <p>
     * <b>Expected Results</b> sub.isEmpty() true, original size 2, elementi
     * rimasti A,E.
     * </p>
     */
    @Test
    public void testClear() {
        sub.clear();
        assertTrue(sub.isEmpty());
        assertEquals(0, sub.size());
        assertEquals(2, original.size());
        assertEquals(A, original.get(0));
        assertEquals(E, original.get(1));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code removeAll} con argomento collezione vuota
     * restituisca false e non modifichi la vista.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso vacuoso per removeAll: la collezione vuota
     * non contiene elementi, quindi nessuna rimozione avviene. Il contratto
     * {@link HCollection#removeAll(HCollection)} prescrive false quando la
     * lista non viene modificata. Complementa testRemoveAll (rimozione effettiva).
     * </p>
     * <p>
     * <b>Test Description</b> Su sub = [B,C,D], si invoca
     * sub.removeAll(makeCollection()) e si verifica che restituisca false e che
     * la vista rimanga [B,C,D].
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D].
     * </p>
     * <p>
     * <b>Post-Condition</b> sub invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> false; sub.size() == 3.
     * </p>
     */
    @Test
    public void testRemoveAllEmptyCollectionNoOp() {
        assertFalse(sub.removeAll(makeCollection()));
        assertEquals(3, sub.size());
        assertEquals(B, sub.get(0));
        assertEquals(C, sub.get(1));
        assertEquals(D, sub.get(2));
    }

    // ====================================================================
    // Iteratori
    // ====================================================================

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code iterator()} sulla subList iteri
     * correttamente e supporti {@code remove}.
     * </p>
     * <p>
     * <b>Test Case Design</b> Iterazione sequenziale con rimozione condizionale
     * di un elemento durante il percorso. L'obiettivo è verificare che
     * {@link HIterator#remove() remove()} sull'iteratore della vista rimuova
     * l'elemento corrente da entrambe le strutture (vista e backing), senza
     * alterare la navigazione sui rimanenti. Il test copre la classe di
     * equivalenza rimozione durante iterazione forward su vista non vuota.
     * </p>
     * <p>
     * <b>Test Description</b> Si itera su sub = [B,C,D] tramite iterator() e
     * si invoca remove() quando l'elemento corrente è C. Dopo l'iterazione
     * completa si verifica che la vista contenga [B,D] (size 2) e che
     * l'originale contenga [A,B,D,E] (size 4), confermando la propagazione
     * della rimozione.
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D].
     * </p>
     * <p>
     * <b>Post-Condition</b> sub = [B,D], original = [A,B,D,E].
     * </p>
     * <p>
     * <b>Expected Results</b> La rimozione ha effetto su vista e originale.
     * </p>
     */
    @Test
    public void testIteratorRemove() {
        HIterator it = sub.iterator();
        while (it.hasNext()) {
            Object o = it.next();
            if (o.equals(C)) {
                it.remove();
            }
        }
        assertEquals(2, sub.size());
        assertEquals(4, original.size());
        assertEquals(B, sub.get(0));
        assertEquals(D, sub.get(1));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica il funzionamento di {@code listIterator()} sulla
     * subList, incluse le operazioni {@code add}, {@code set} e {@code remove}.
     * </p>
     * <p>
     * <b>Test Case Design</b> Uso combinato di operazioni di navigazione e
     * modifica sul ListIterator: next, set, add, previous e remove. L'obiettivo
     * è validare la corretta interazione tra le operazioni mutuamente
     * dipendenti del ListIterator (ad esempio set dopo next, add che resetta il
     * cursore, remove che opera sull'ultimo elemento visitato). Il test
     * percorre un flusso completo di manipolazione su una vista, verificando
     * la propagazione a ogni passo sull'originale. Complementare a
     * {@link #testListIteratorNavigationAndIndices() testListIteratorNavigationAndIndices}
     * che testa solo la navigazione pura.
     * </p>
     * <p>
     * <b>Test Description</b> Si apre un listIterator posizionato a 1 sulla
     * vista sub = [B,C,D]. Si esegue next() ottenendo C, poi set(X) che
     * sostituisce C con X, quindi add(Y) che inserisce Y dopo X spostando il
     * cursore. Si torna indietro con previous() ottenendo Y, e infine remove()
     * rimuove Y. Si verifica che la vista finale sia [B,X,D] (size 3) e che
     * l'originale sia [A,B,X,D,E] (size 5), con gli elementi X e Y inseriti
     * e rimossi nel corretto ordine logico.
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D].
     * </p>
     * <p>
     * <b>Post-Condition</b> sub = [B,X,D], original = [A,B,X,D,E].
     * </p>
     * <p>
     * <b>Expected Results</b> set, add, remove funzionano correttamente e
     * aggiornano vista e originale.
     * </p>
     */
    @Test
    public void testListIteratorOperations() {
        HListIterator lit = sub.listIterator(1);
        assertEquals(C, lit.next()); // C
        lit.set(X);                  // [B,X,D]
        lit.add(Y);                  // [B,X,Y,D]
        assertEquals(Y, lit.previous()); // Y
        lit.remove();                // [B,X,D]

        assertEquals(3, sub.size());
        assertEquals(5, original.size());
        assertEquals(B, sub.get(0));
        assertEquals(X, sub.get(1));
        assertEquals(D, sub.get(2));
        assertEquals(A, original.get(0));
        assertEquals(B, original.get(1));
        assertEquals(X, original.get(2));
        assertEquals(D, original.get(3));
        assertEquals(E, original.get(4));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che l'iteratore della vista estragga esattamente
     * gli elementi dell'intervallo e lanci NoSuchElementException a esaurimento.
     * </p>
     * <p>
     * <b>Test Case Design</b> Attraversamento completo con verifica del
     * contenuto (nessun elemento fuori range come A o E deve trapelare dal
     * backing), seguito dall'accesso oltre l'ultimo elemento.
     * </p>
     * <p>
     * <b>Test Description</b> Si consumano i tre next() controllando i valori B,
     * C, D; quindi hasNext() diventa false e un ulteriore next() deve lanciare
     * NoSuchElementException.
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D].
     * </p>
     * <p>
     * <b>Post-Condition</b> Nessuna modifica.
     * </p>
     * <p>
     * <b>Expected Results</b> Sequenza esattamente [B,C,D]; hasNext() false al
     * termine; NoSuchElementException sul next() successivo.
     * </p>
     */
    @Test
    public void testIteratorNoSuchElementException() {
        HIterator it = sub.iterator();
        assertEquals(B, it.next());
        assertEquals(C, it.next());
        assertEquals(D, it.next());
        assertFalse(it.hasNext());
        try {
            it.next();
            fail("next() oltre la fine deve lanciare NoSuchElementException");
        } catch (NoSuchElementException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica la navigazione pura del listIterator sulla vista:
     * hasNext/hasPrevious e gli indici logici nextIndex/previousIndex ai bordi.
     * </p>
     * <p>
     * <b>Test Case Design</b> Percorso avanti-indietro completo sui bordi
     * [0..size] della vista: stato iniziale, stati intermedi dopo ogni next e
     * ritorno fino all'inizio con soli previous.
     * </p>
     * <p>
     * <b>Test Description</b> Da listIterator() si controllano gli stati
     * iniziali (nextIndex 0, previousIndex -1, hasPrevious false), poi si
     * avanzano tre next() fino a nextIndex 3 e si torna indietro fino a
     * previousIndex -1, verificando valori e indici a ogni passo.
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D].
     * </p>
     * <p>
     * <b>Post-Condition</b> Nessuna modifica alla lista (navigazione senza
     * set/add/remove).
     * </p>
     * <p>
     * <b>Expected Results</b> Gli indici seguono la convenzione ListIterator di
     * J2SE 1.4.2: nextIndex in [0,size], previousIndex in [-1,size-1]; i valori
     * letti in direzione inversa sono D, C, B; a cursore 0 hasNext torna true.
     * </p>
     */
    @Test
    public void testListIteratorNavigationAndIndices() {
        HListIterator lit = sub.listIterator();
        assertTrue(lit.hasNext());
        assertFalse(lit.hasPrevious());
        assertEquals(0, lit.nextIndex());
        assertEquals(-1, lit.previousIndex());

        assertEquals(B, lit.next());
        assertTrue(lit.hasPrevious());
        assertEquals(1, lit.nextIndex());
        assertEquals(0, lit.previousIndex());

        assertEquals(C, lit.next());
        assertEquals(D, lit.next());
        assertFalse(lit.hasNext());
        assertEquals(3, lit.nextIndex());

        assertEquals(D, lit.previous());
        assertEquals(1, lit.previousIndex());
        assertEquals(C, lit.previous());
        assertEquals(B, lit.previous());
        assertEquals(-1, lit.previousIndex());
        assertFalse(lit.hasPrevious());
        assertTrue(lit.hasNext());
    }

    // ====================================================================
    // Controlli di bounds e casi limite
    // ====================================================================

    /**
     * <p>
     * <b>Summary</b> Verifica che la creazione di una subList con indici
     * non validi lanci {@link IndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Test Case Design</b> Tre minime violazioni dell'intervallo valido
     * [0,size] per i parametri di subList: fromIndex negativo (-1), toIndex
     * superiore alla size della lista (6 > 5), e fromIndex maggiore di toIndex
     * (3 > 1). Queste coprono le tre classi di errore distinte previste dal
     * contratto {@link HList#subList(int, int)} di J2SE 1.4.2: indice
     * negativo, indice fuori limite superiore, e ordine invertito. Il bordo
     * valido subList(0,5) non è testato qui poiché esula dall'obiettivo di
     * verificare le eccezioni, e subList(2,2) è coperto da
     * {@link #testEmptySubList() testEmptySubList}.
     * </p>
     * <p>
     * <b>Test Description</b> Si invocano tre tentativi su original (size 5):
     * subList(-1, 3), subList(0, 6) e subList(3, 1). Ogni invocazione deve
     * essere raccolta in un blocco try/fail/catch che verifica il lancio di
     * IndexOutOfBoundsException; l'assenza di eccezione provoca il fallimento
     * tramite fail.
     * </p>
     * <p>
     * <b>Pre-Condition</b> original size 5.
     * </p>
     * <p>
     * <b>Post-Condition</b> Nessuna modifica.
     * </p>
     * <p>
     * <b>Expected Results</b> IndexOutOfBoundsException per ogni tentativo.
     * </p>
     */
    @Test
    public void testSubListBounds() {
        try {
            original.subList(-1, 3);
            fail("fromIndex < 0 deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }

        try {
            original.subList(0, 6);
            fail("toIndex > size deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }

        try {
            original.subList(3, 1);
            fail("fromIndex > toIndex deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code listIterator(int)} sulla vista applichi
     * i limiti relativi alla sua dimensione e non a quella della lista originale.
     * </p>
     * <p>
     * <b>Test Case Design</b> Minime violazioni dell'intervallo valido
     * [0..size()] della vista: -1 sotto e 4 sopra; listIterator(4) sarebbe
     * valido sul backing (5 elementi) ma deve fallire. Il bordo valido
     * size()==3 è incluso come controllo positivo.
     * </p>
     * <p>
     * <b>Test Description</b> Si tentano listIterator(-1) e listIterator(4)
     * attendendo IndexOutOfBoundsException; quindi si crea listIterator(3) e si
     * verifica che sia posizionato in coda (hasNext false, hasPrevious true).
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D], original = [A,B,C,D,E].
     * </p>
     * <p>
     * <b>Post-Condition</b> Nessuna modifica.
     * </p>
     * <p>
     * <b>Expected Results</b> IndexOutOfBoundsException per -1 e 4;
     * listIterator(3) accettato e privo di elementi successivi.
     * </p>
     */
    @Test
    public void testListIteratorIndexOutOfBounds() {
        try {
            sub.listIterator(-1);
            fail("listIterator(-1) deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }
        try {
            sub.listIterator(4); // valida sul backing (size 5), non sulla vista (size 3)
            fail("listIterator(4) deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }

        HListIterator lit = sub.listIterator(3);
        assertFalse(lit.hasNext());
        assertTrue(lit.hasPrevious());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che una subList vuota (fromIndex == toIndex)
     * sia creata correttamente e che le operazioni di lettura e modifica
     * si comportino come per una lista vuota.
     * </p>
     * <p>
     * <b>Test Case Design</b> Creazione di subList con indici uguali.
     * </p>
     * <p>
     * <b>Test Description</b> Si crea empty = original.subList(2,2), si
     * verifica size 0 e isEmpty true. Si tenta get(0) che deve lanciare.
     * Si aggiunge X e si controlla che l'originale sia aggiornato.
     * </p>
     * <p>
     * <b>Pre-Condition</b> original = [A,B,C,D,E].
     * </p>
     * <p>
     * <b>Post-Condition</b> original = [A,B,X,C,D,E].
     * </p>
     * <p>
     * <b>Expected Results</b> La vista vuota si comporta come una lista vuota;
     * add su di essa inserisce all'inizio della vista (cioè prima di C).
     * </p>
     */
    @Test
    public void testEmptySubList() {
        HList empty = original.subList(2, 2);
        assertTrue(empty.isEmpty());
        assertEquals(0, empty.size());
        try {
            empty.get(0);
            fail("get(0) su subList vuota deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }

        empty.add(X);
        assertEquals(1, empty.size());
        assertEquals(X, empty.get(0));
        assertEquals(6, original.size());
        assertEquals(A, original.get(0));
        assertEquals(B, original.get(1));
        assertEquals(X, original.get(2)); // inserito in posizione 2
        assertEquals(C, original.get(3));
        assertEquals(D, original.get(4));
        assertEquals(E, original.get(5));
    }

    // ====================================================================
    // Vista e lista originale
    // ====================================================================

    /**
     * <p>
     * <b>Summary</b> Verifica che le modifiche sulla lista originale
     * all'interno dell'intervallo della subList si riflettano sulla vista.
     * </p>
     * <p>
     * <b>Test Case Design</b> Modifica posizionale sulla lista originale
     * all'interno dell'intervallo coperto dalla subList. L'obiettivo è
     * verificare che la vista backed rifletta immediatamente una modifica
     * eseguita sull'originale tramite il suo API posizionale, confermando il
     * legame diretto tra backing e vista. Il test usa la sostituzione singola
     * original.set(2, X) (posizione corrispondente a sub.get(1)) come caso
     * normale di visibilità trasversale, complementare a
     * {@link #testModifyOriginalOutsideRange() testModifyOriginalOutsideRange}
     * che verifica il comportamento per modifiche fuori intervallo.
     * </p>
     * <p>
     * <b>Test Description</b> Si invoca original.set(2, X) che sostituisce C
     * (indice 2 nell'originale) con X. Si verifica che sub.get(1) (corrispondente
     * all'indice 2 dell'originale, offset 1 della vista) restituisca X,
     * confermando la propagazione istantanea della modifica.
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D], original = [A,B,C,D,E].
     * </p>
     * <p>
     * <b>Post-Condition</b> sub = [B,X,D], original = [A,B,X,D,E].
     * </p>
     * <p>
     * <b>Expected Results</b> La vista riflette la modifica.
     * </p>
     */
    @Test
    public void testModifyOriginalInsideRange() {
        original.set(2, X); // posizione 2 è C
        assertEquals(X, sub.get(1));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che modifiche strutturali sulla lista originale
     * all'esterno dell'intervallo della subList non influenzino la vista.
     * </p>
     * <p>
     * <b>Test Case Design</b> Modifica strutturale sulla lista originale
     * all'esterno dell'intervallo coperto dalla subList. L'obiettivo è
     * documentare il comportamento della vista quando la lista di backing viene
     * modificata in un punto che non appartiene all'intervallo logico della
     * subList. In base al contratto di List.subList di J2SE 1.4.2, il
     * comportamento di una vista backed diventa indefinito dopo modifiche
     * strutturali esterne non effettuate attraverso la vista stessa. Questo
     * test non asserisce valori specifici sui contenuti della vista, ma si
     * limita a verificare l'assenza di eccezioni, documentando così la zona
     * grigia del comportamento indefinito. Complementare a
     * {@link #testModifyOriginalInsideRange() testModifyOriginalInsideRange}
     * che testa le modifiche all'interno dell'intervallo.
     * </p>
     * <p>
     * <b>Test Description</b> Si inserisce X in posizione 0 della lista
     * original (prima dell'intervallo della subList). Si verifica che la vista
     * maintenga ancora 3 elementi accessibili tramite get(0), get(1), get(2),
     * senza che vengano lanciate eccezioni. Non si asseriscono valori specifici
     * dei singoli elementi poiché il comportamento è indefinito per contratto.
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D], original = [A,B,C,D,E].
     * </p>
     * <p>
     * <b>Post-Condition</b> original = [X,A,B,C,D,E].
     * </p>
     * <p>
     * <b>Expected Results</b> La vista potrebbe non riflettere la modifica;
     * il test verifica solo che non vengano lanciate eccezioni.
     * </p>
     */
    @Test
    public void testModifyOriginalOutsideRange() {
        original.add(0, X); // aggiunge X all'inizio, fuori dalla subList
        assertEquals(3, sub.size());
        assertNotNull(sub.get(0));
        assertNotNull(sub.get(1));
        assertNotNull(sub.get(2));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che sia possibile creare una subList di una
     * subList e che le modifiche sulla vista annidata si propaghino
     * correttamente.
     * </p>
     * <p>
     * <b>Test Case Design</b> Creazione di subList(0,2) sulla vista [B,C,D],
     * che produce [B,C], e applicazione di operazioni (set e add) sulla vista
     * annidata per verificarne la propagazione.
     * </p>
     * <p>
     * <b>Test Description</b> Si crea nested = sub.subList(0,2) che contiene
     * [B,C]. Si modifica il primo elemento con X tramite nested.set(0, X) e
     * si controlla che la modifica sia visibile su nested, sub e original.
     * Si aggiunge quindi Y in coda alla nested tramite nested.add(Y) e si
     * verifica che la modifica si propaghi a sub e original.
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D], original = [A,B,C,D,E].
     * </p>
     * <p>
     * <b>Post-Condition</b> nested = [X,C,Y], sub = [X,C,Y,D],
     * original = [A,X,C,Y,D,E].
     * </p>
     * <p>
     * <b>Expected Results</b> Le modifiche (set e add) eseguite sulla nested
     * si riflettono su tutti i livelli (sub e original).
     * </p>
     */
    @Test
    public void testSubListOfSubList() {
        HList nested = sub.subList(0, 2); // [B,C]
        assertEquals(2, nested.size());
        assertEquals(B, nested.get(0));
        assertEquals(C, nested.get(1));

        nested.set(0, X);
        assertEquals(X, nested.get(0));
        assertEquals(X, sub.get(0));
        assertEquals(X, original.get(1));

        nested.add(Y); // nested = [X, C, Y]
        assertEquals(3, nested.size());
        assertEquals(Y, nested.get(2));
        assertEquals(Y, sub.get(2)); 
        assertEquals(Y, original.get(3));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code clear()} su una subList annidata
     * rimuova gli elementi dal backing e lasci utilizzabile la vista intermedia.
     * </p>
     * <p>
     * <b>Test Case Design</b> Svuotamento della vista annidata [C,D] (offset 1
     * rispetto a sub): propagazione doppia verso sub e original e stato
     * coerente di tutte le viste coinvolte.
     * </p>
     * <p>
     * <b>Test Description</b> Si crea nested = sub.subList(1,3), si invoca
     * nested.clear() e si verifica nested vuota, sub ridotta a [B], original
     * ridotto a [A,B,E] e l'assenza di C e D dalla vista intermedia.
     * </p>
     * <p>
     * <b>Pre-Condition</b> sub = [B,C,D], original = [A,B,C,D,E].
     * </p>
     * <p>
     * <b>Post-Condition</b> nested = [], sub = [B], original = [A,B,E].
     * </p>
     * <p>
     * <b>Expected Results</b> La cancellazione tramite la vista annidata si
     * riflette su tutti i livelli; sub resta consultabile senza errori.
     * </p>
     */
    @Test
    public void testClearOnNestedSubList() {
        HList nested = sub.subList(1, 3); // [C, D]
        nested.clear();

        assertTrue(nested.isEmpty());
        assertEquals(0, nested.size());

        assertEquals(1, sub.size());
        assertEquals(B, sub.get(0));
        assertFalse(sub.contains(C));
        assertFalse(sub.contains(D));

        assertEquals(3, original.size());
        assertEquals(A, original.get(0));
        assertEquals(B, original.get(1));
        assertEquals(E, original.get(2));
    }
}
