package myTest;

import static org.junit.Assert.*;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * <p>
 * <b>Summary</b> Verifica il corretto funzionamento dell'iteratore bidirezionale
 * {@code HListIterator} prodotto da {@link AbstractHList#listIterator()} e
 * {@link AbstractHList#listIterator(int)}. La suite copre la navigazione
 * avanti e indietro, i metodi {@code nextIndex()} e {@code previousIndex()},
 * le operazioni di modifica ({@code set}, {@code add}, {@code remove}) in
 * contesti validi, le eccezioni per stati illegali e i controlli di bounds.
 * <br>
 * Per eseguire questa test suite: {@code java -cp "bin:JUnit/*" org.junit.runner.JUnitCore myTest.ListIteratorAdapterTest}
 * </p>
 * <p>
 * <b>Test Case Design</b> La suite organizza i test in 4 categorie:
 * <ul>
 * <li>A) Navigazione (6 test)</li>
 * <li>B) Modifiche (7 test)</li>
 * <li>C) Eccezioni e stati illegali (6 test)</li>
 * <li>D) Bounds e casi limite (3 test)</li>
 * </ul>
 * <br>
 * Ogni test utilizza il metodo {@link #setUp()} che crea un {@code VectorAdapter}
 * vuoto. I test con dati usano metodi di supporto {@link #populateThree()} e
 * {@link #populateFive()} per popolare la lista con 3 o 5 elementi.
 * <br>
 * I criteri di valutazione usano {@code assert} JUnit e verifica di eccezioni
 * attese ({@link NoSuchElementException}, {@link IllegalStateException},
 * {@link IndexOutOfBoundsException}). <br>
 * Per ogni test method la documentazione dettagliata riporta i valori concreti delle
 * variabili (es. {@code list=[A,B,C]}, {@code it = list.listIterator(1)}). <br>
 * <b>Versione JUnit:</b> Questa test suite è stata scritta utilizzando il framework JUnit 4.13.
 * <p>
 * <b>Test Cases</b>
 * </p>
 * <p>
 * <b>A) Navigazione (6 test)</b>
 * </p>
 * <ul>
 * <li>{@link #testNextAndPrevious()} - Verifica che {@code next()} e {@code previous()} restituiscano
 * gli elementi nell'ordine corretto durante la navigazione avanti e indietro.</li>
 * <li>{@link #testNextIndexAndPreviousIndex()} - Verifica che {@code nextIndex()} e
 * {@code previousIndex()} riflettano la posizione del cursore.</li>
 * <li>{@link #testHasNextAndHasPrevious()} - Verifica i predicati {@code hasNext()} e
 * {@code hasPrevious()} agli estremi e durante la navigazione.</li>
 * <li>{@link #testNavigateEmptyList()} - Verifica il comportamento su lista vuota:
 * hasNext/hasPrevious false, next/previous lanciano NoSuchElementException.</li>
 * <li>{@link #testNavigateSingleElement()} - Verifica la navigazione su lista di un solo elemento.</li>
 * <li>{@link #testAlternatingNextPreviousSameElement()} - Verifica che alternare
 * {@code next()} e {@code previous()} restituisca lo stesso elemento.</li>
 * </ul>
 * <p>
 * <b>B) Modifiche (7 test)</b>
 * </p>
 * <ul>
 * <li>{@link #testSetAfterNext()} - Verifica che {@code set()} dopo {@code next()} sostituisca
 * correttamente l'elemento corrente.</li>
 * <li>{@link #testSetAfterPrevious()} - Verifica che {@code set()} dopo {@code previous()} sostituisca
 * correttamente l'elemento corrente.</li>
 * <li>{@link #testAdd()} - Verifica che {@code add()} inserisca un elemento prima del cursore
 * e aggiorni la posizione, con controllo di nextIndex e previousIndex.</li>
 * <li>{@link #testAddToEmptyList()} - Verifica che {@code add()} su lista vuota inserisca
 * l'elemento come unico elemento e aggiorni gli indici.</li>
 * <li>{@link #testRemoveAfterNext()} - Verifica che {@code remove()} dopo {@code next()} rimuova
 * l'elemento appena visitato.</li>
 * <li>{@link #testRemoveAfterPrevious()} - Verifica che {@code remove()} dopo {@code previous()} rimuova
 * l'elemento appena visitato.</li>
 * <li>{@link #testDoubleSetAfterNext()} - Verifica che {@code set()} possa essere chiamato due
 * volte dopo un singolo {@code next()} senza lanciare eccezione.</li>
 * </ul>
 * <p>
 * <b>C) Eccezioni e stati illegali (6 test)</b>
 * </p>
 * <ul>
 * <li>{@link #testSetWithoutNextOrPrevious()} - {@code set()} senza {@code next()} o {@code previous()}
 * deve lanciare {@link IllegalStateException}.</li>
 * <li>{@link #testSetAfterRemove()} - {@code set()} dopo {@code remove()} deve lanciare
 * {@link IllegalStateException}.</li>
 * <li>{@link #testRemoveWithoutNextOrPrevious()} - {@code remove()} senza {@code next()} o
 * {@code previous()} deve lanciare {@link IllegalStateException}.</li>
 * <li>{@link #testRemoveAfterAdd()} - Dopo un {@code add()}, {@code remove()} non è più valido
 * e deve lanciare {@link IllegalStateException}.</li>
 * <li>{@link #testDoubleRemoveAfterNext()} - Chiamare {@code remove()} due volte dopo un singolo
 * {@code next()} deve lanciare {@link IllegalStateException} alla seconda invocazione.</li>
 * <li>{@link #testSetAfterAdd()} - Dopo un {@code add()}, {@code set()} non è più valido
 * e deve lanciare {@link IllegalStateException}.</li>
 * </ul>
 * <p>
 * <b>D) Bounds e casi limite (3 test)</b>
 * </p>
 * <ul>
 * <li>{@link #testListIteratorIndexBounds()} - {@code listIterator(int index)} con indice
 * fuori intervallo lancia {@link IndexOutOfBoundsException}.</li>
 * <li>{@link #testListIteratorAtEnd()} - Iteratore posizionato alla fine della lista:
 * hasNext false, hasPrevious true, previous restituisce l'ultimo elemento.</li>
 * <li>{@link #testListIteratorAtBeginning()} - Iteratore posizionato all'inizio:
 * hasPrevious false, hasNext true, next restituisce il primo elemento.</li>
 * </ul>
 */
public class ListIteratorAdapterTest {

    /**
     * Costruttore pubblico di default.
     */
    public ListIteratorAdapterTest() {}

    private VectorAdapter list;
    private HListIterator lit;

    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static final String D = "D";
    private static final String E = "E";
    private static final String X = "X";
    private static final String Y = "Y";
    private static final String Z = "Z";

    /**
     * Crea una lista vuota.
     */
    @Before
    public void setUp() {
        list = new VectorAdapter();
        lit = list.listIterator();
    }

    /**
     * Popola la lista con tre elementi [A, B, C] e crea un iteratore posizionato all'inizio.
     */
    private void populateThree() {
        list.add(A);
        list.add(B);
        list.add(C);
        lit = list.listIterator();
    }

    /**
     * Popola la lista con cinque elementi [A, B, C, D, E] e crea un iteratore posizionato all'inizio.
     */
    private void populateFive() {
        list.add(A);
        list.add(B);
        list.add(C);
        list.add(D);
        list.add(E);
        lit = list.listIterator();
    }

    // ====================================================================
    // A) Navigazione (6)
    // ====================================================================

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code next()} e {@code previous()} restituiscano
     * gli elementi nell'ordine corretto durante la navigazione avanti e indietro.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale di navigazione sequenziale avanti e
     * indietro su lista di 3 elementi. Il test percorre la lista in avanti con
     * {@code next()} fino alla fine, poi torna indietro con {@code previous()}
     * fino all'inizio, controllando i valori restituiti a ogni passo. I predicati
     * {@code hasNext()} e {@code hasPrevious()} vengono verificati agli estremi.
     * Complementa testNavigateSingleElement e testNavigateEmptyList.
     * </p>
     * <p>
     * <b>Test Description</b> Su lista [A, B, C], si esegue la sequenza:
     * {@code next()} restituisce A, B, C; poi {@code previous()} restituisce C, B, A.
     * I valori di {@code hasNext()} e {@code hasPrevious()} sono controllati
     * prima, durante e dopo la navigazione.
     * </p>
     * <p>
     * <b>Pre-Condition</b> list = [A, B, C], lit = list.listIterator().
     * </p>
     * <p>
     * <b>Post-Condition</b> list invariata; iteratore posizionato all'inizio.
     * </p>
     * <p>
     * <b>Expected Results</b> next() restituisce A, B, C; previous() restituisce C, B, A.
     * </p>
     */
    @Test
    public void testNextAndPrevious() {
        populateThree();
        assertTrue(lit.hasNext());
        assertFalse(lit.hasPrevious());

        assertEquals(A, lit.next());
        assertEquals(B, lit.next());
        assertEquals(C, lit.next());
        assertFalse(lit.hasNext());
        assertTrue(lit.hasPrevious());

        assertEquals(C, lit.previous());
        assertEquals(B, lit.previous());
        assertEquals(A, lit.previous());
        assertTrue(lit.hasNext());
        assertFalse(lit.hasPrevious());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code nextIndex()} e {@code previousIndex()} riflettano
     * la posizione del cursore durante la navigazione.
     * </p>
     * <p>
     * <b>Test Case Design</b> In ogni posizione del cursore, {@code nextIndex()} deve
     * restituire l'indice del prossimo elemento che verrebbe restituito da {@code next()},
     * e {@code previousIndex()} l'indice dell'elemento che verrebbe restituito da
     * {@code previous()}. In una lista di 3 elementi, le posizioni valide vanno da 0 a 3.
     * Il test controlla i valori degli indici agli estremi e dopo ogni {@code next()}.
     * Complementa testNextAndPrevious, che verifica i valori degli elementi.
     * </p>
     * <p>
     * <b>Test Description</b> Su lista [A, B, C], si parte da cursor=0:
     * nextIndex=0, previousIndex=-1. Dopo ogni next(), i valori si aggiornano.
     * Alla fine (cursor=3): nextIndex=3, previousIndex=2.
     * </p>
     * <p>
     * <b>Pre-Condition</b> list = [A, B, C], lit = list.listIterator().
     * </p>
     * <p>
     * <b>Post-Condition</b> list invariata; iteratore alla fine.
     * </p>
     * <p>
     * <b>Expected Results</b> nextIndex e previousIndex seguono la convenzione
     * ListIterator di J2SE 1.4.2.
     * </p>
     */
    @Test
    public void testNextIndexAndPreviousIndex() {
        populateThree();
        assertEquals(0, lit.nextIndex());
        assertEquals(-1, lit.previousIndex());

        lit.next(); // A
        assertEquals(1, lit.nextIndex());
        assertEquals(0, lit.previousIndex());

        lit.next(); // B
        assertEquals(2, lit.nextIndex());
        assertEquals(1, lit.previousIndex());

        lit.next(); // C
        assertEquals(3, lit.nextIndex());
        assertEquals(2, lit.previousIndex());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica i predicati {@code hasNext()} e {@code hasPrevious()}
     * agli estremi e durante la navigazione.
     * </p>
     * <p>
     * <b>Test Case Design</b> I predicati devono essere consistenti con la posizione
     * del cursore. All'inizio (cursor=0), hasNext=true, hasPrevious=false.
     * Dopo aver consumato tutti gli elementi (cursor=size), hasNext=false,
     * hasPrevious=true. Durante la navigazione, hasNext e hasPrevious devono
     * essere true contemporaneamente quando il cursore è in posizione intermedia.
     * </p>
     * <p>
     * <b>Test Description</b> Su lista [A, B, C], si controllano i predicati
     * all'inizio, dopo un next, dopo due next, dopo tre next (fine), e dopo
     * un previous.
     * </p>
     * <p>
     * <b>Pre-Condition</b> list = [A, B, C], lit = list.listIterator().
     * </p>
     * <p>
     * <b>Post-Condition</b> list invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> hasNext e hasPrevious riflettono correttamente
     * la presenza di elementi nelle due direzioni.
     * </p>
     */
    @Test
    public void testHasNextAndHasPrevious() {
        populateThree();
        assertTrue(lit.hasNext());
        assertFalse(lit.hasPrevious());

        lit.next(); // A
        assertTrue(lit.hasNext());
        assertTrue(lit.hasPrevious());

        lit.next(); // B
        assertTrue(lit.hasNext());
        assertTrue(lit.hasPrevious());

        lit.next(); // C
        assertFalse(lit.hasNext());
        assertTrue(lit.hasPrevious());

        lit.previous(); // C
        assertTrue(lit.hasNext());
        assertTrue(lit.hasPrevious());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica il comportamento su lista vuota: hasNext e hasPrevious
     * restituiscono false, next() e previous() lanciano NoSuchElementException,
     * nextIndex restituisce 0, previousIndex restituisce -1.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso limite di lista vuota. L'iteratore bidirezionale
     * su lista vuota non ha elementi in nessuna delle due direzioni. Tutti i
     * metodi di navigazione devono comportarsi come da contratto.
     * </p>
     * <p>
     * <b>Test Description</b> Su lista vuota, si controlla che hasNext e hasPrevious
     * siano false, che next() e previous() lancino NoSuchElementException,
     * e che gli indici siano 0 e -1.
     * </p>
     * <p>
     * <b>Pre-Condition</b> list = [], lit = list.listIterator().
     * </p>
     * <p>
     * <b>Post-Condition</b> list invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> hasNext=false, hasPrevious=false; next() e previous()
     * lanciano NoSuchElementException; nextIndex=0, previousIndex=-1.
     * </p>
     */
    @Test
    public void testNavigateEmptyList() {
        assertFalse(lit.hasNext());
        assertFalse(lit.hasPrevious());
        assertEquals(0, lit.nextIndex());
        assertEquals(-1, lit.previousIndex());

        try {
            lit.next();
            fail("next() su lista vuota deve lanciare NoSuchElementException");
        } catch (NoSuchElementException e) { /* atteso */ }

        try {
            lit.previous();
            fail("previous() su lista vuota deve lanciare NoSuchElementException");
        } catch (NoSuchElementException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica la navigazione su lista di un singolo elemento.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso di frontiera con size=1. L'iteratore deve
     * consentire di leggere l'unico elemento sia in avanti che all'indietro,
     * e i predicati devono riflettere le due direzioni. Dopo aver letto l'elemento
     * in avanti, hasPrevious deve essere true; dopo averlo letto all'indietro,
     * hasNext deve essere true.
     * </p>
     * <p>
     * <b>Test Description</b> Su lista [A], si controlla che hasNext=true,
     * hasPrevious=false; next() restituisce A e poi hasNext=false, hasPrevious=true;
     * previous() restituisce A e poi hasNext=true, hasPrevious=false.
     * </p>
     * <p>
     * <b>Pre-Condition</b> list = [A], lit = list.listIterator().
     * </p>
     * <p>
     * <b>Post-Condition</b> list invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> La navigazione avanti e indietro funziona correttamente
     * e i predicati si alternano.
     * </p>
     */
    @Test
    public void testNavigateSingleElement() {
        list.add(A);
        lit = list.listIterator();

        assertTrue(lit.hasNext());
        assertFalse(lit.hasPrevious());

        assertEquals(A, lit.next());
        assertFalse(lit.hasNext());
        assertTrue(lit.hasPrevious());

        assertEquals(A, lit.previous());
        assertTrue(lit.hasNext());
        assertFalse(lit.hasPrevious());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che alternare {@code next()} e
     * {@code previous()} restituisca lo stesso elemento ripetutamente,
     * come specificato dalla documentazione J2SE 1.4.2 di ListIterator.
     * </p>
     * <p>
     * <b>Test Case Design</b> Il contratto J2SE 1.4.2 di ListIterator stabilisce
     * che alternare next e previous sullo stesso elemento restituisce lo
     * stesso valore. Si testa l'alternanza in avanti (next, previous, next)
     * e all'indietro (previous, next, previous) per coprire entrambe le
     * direzioni. Complementa testNextAndPrevious che percorre l'intera lista
     * senza isolare questo comportamento.
     * </p>
     * <p>
     * <b>Test Description</b> Su lista [A, B, C], si posiziona l'iteratore a
     * cursor=1 (prima di B). Si esegue next() ottenendo B, poi previous()
     * che deve restituire B di nuovo (stesso elemento). Si esegue ancora
     * next() che restituisce B. Due previous() consecutive portano a A;
     * poi next() restituisce A, e previous() restituisce A.
     * </p>
     * <p>
     * <b>Pre-Condition</b> list = [A, B, C], lit = list.listIterator(1).
     * </p>
     * <p>
     * <b>Post-Condition</b> list invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> next/previous alternati restituiscono sempre lo
     * stesso elemento.
     * </p>
     */
    @Test
    public void testAlternatingNextPreviousSameElement() {
        populateThree();
        lit = list.listIterator(1); // cursor=1, prima di B

        // next B, previous B (stesso elemento), next B
        assertEquals(B, lit.next());
        assertEquals(B, lit.previous());
        assertEquals(B, lit.next());

        // due previous per raggiungere A
        assertEquals(B, lit.previous()); // cursor 2→1
        assertEquals(A, lit.previous()); // cursor 1→0

        // next A, previous A (stesso elemento)
        assertEquals(A, lit.next());
        assertEquals(A, lit.previous());
    }

    // ====================================================================
    // B) Modifiche (7)
    // ====================================================================

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code set()} dopo {@code next()} sostituisca
     * correttamente l'elemento corrente.
     * </p>
     * <p>
     * <b>Test Case Design</b> Dopo un {@code next()}, {@code set()} deve
     * sostituire l'elemento appena restituito senza spostare il cursore.
     * Si verifica che il valore restituito da {@code set()} sia l'elemento
     * originale e che la lista venga aggiornata.
     * </p>
     * <p>
     * <b>Test Description</b> Su lista [A, B, C], si esegue next() per ottenere B,
     * poi set(X) per sostituirlo. Si verifica che list.get(1) sia X e che
     * l'iteratore rimanga nella stessa posizione (nextIndex invariato).
     * </p>
     * <p>
     * <b>Pre-Condition</b> list = [A, B, C], lit = list.listIterator(1).
     * </p>
     * <p>
     * <b>Post-Condition</b> list = [A, X, C]; iteratore posizionato dopo B (ora X).
     * </p>
     * <p>
     * <b>Expected Results</b> set() restituisce B; list.get(1) == X.
     * </p>
     */
    @Test
    public void testSetAfterNext() {
        populateThree();
        lit = list.listIterator(1); // posizionato prima di B (cursor=1)
        assertEquals(B, lit.next()); // ottiene B
        lit.set(X);
        assertEquals(X, list.get(1));
        assertEquals(2, lit.nextIndex());
        assertEquals(1, lit.previousIndex());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code set()} dopo {@code previous()} sostituisca
     * correttamente l'elemento corrente.
     * </p>
     * <p>
     * <b>Test Case Design</b> Simmetrico a testSetAfterNext, ma usando previous()
     * per ottenere l'elemento. Dopo previous(), set() deve sostituire l'elemento
     * restituito da previous().
     * </p>
     * <p>
     * <b>Test Description</b> Su lista [A, B, C], si posiziona l'iteratore alla
     * fine (cursor=3), si esegue previous() per ottenere C, poi set(X) per
     * sostituirlo. Si verifica che list.get(2) sia X e che nextIndex e
     * previousIndex riflettano la nuova posizione.
     * </p>
     * <p>
     * <b>Pre-Condition</b> list = [A, B, C], lit = list.listIterator(3).
     * </p>
     * <p>
     * <b>Post-Condition</b> list = [A, B, X].
     * </p>
     * <p>
     * <b>Expected Results</b> set() restituisce C; list.get(2) == X.
     * </p>
     */
    @Test
    public void testSetAfterPrevious() {
        populateThree();
        lit = list.listIterator(3); // posizionato alla fine (cursor=3)
        assertEquals(C, lit.previous()); // ottiene C
        lit.set(X);
        assertEquals(X, list.get(2));
        assertEquals(2, lit.nextIndex());
        assertEquals(1, lit.previousIndex()); // cursor ora è 2, previousIndex=1
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code add()} inserisca un elemento prima del
     * cursore e sposti il cursore in avanti, aggiornando correttamente gli indici.
     * </p>
     * <p>
     * <b>Test Case Design</b> Il metodo add() deve inserire l'elemento alla
     * posizione corrente (prima dell'elemento che verrebbe restituito da next()),
     * incrementare il cursore di 1 e impostare lastRet a -1. Si verifica
     * l'ordine degli elementi, la nuova posizione del cursore e gli indici
     * nextIndex/previousIndex. Complementa testAddToEmptyList per il caso
     * vuoto.
     * </p>
     * <p>
     * <b>Test Description</b> Su lista [A, B, C], si inserisce X in posizione 1
     * (prima di B) tramite add(X), poi Y in posizione 3 (dopo X) e Z in coda.
     * Si controlla la lista risultante, la posizione del cursore e gli indici
     * di nextIndex e previousIndex dopo gli inserimenti.
     * </p>
     * <p>
     * <b>Pre-Condition</b> list = [A, B, C], lit = list.listIterator(1).
     * </p>
     * <p>
     * <b>Post-Condition</b> list = [A, X, Y, B, C, Z].
     * </p>
     * <p>
     * <b>Expected Results</b> Gli elementi sono inseriti nelle posizioni corrette;
     * il cursore avanza; nextIndex e previousIndex sono coerenti.
     * </p>
     */
    @Test
    public void testAdd() {
        populateThree();
        lit = list.listIterator(1); // cursor=1 (prima di B)
        lit.add(X); // [A, X, B, C], cursor=2, lastRet=-1
        lit.add(Y); // [A, X, Y, B, C], cursor=3
        lit = list.listIterator(list.size()); // posiziona alla fine
        lit.add(Z); // [A, X, Y, B, C, Z]

        assertEquals(6, list.size());
        assertEquals(A, list.get(0));
        assertEquals(X, list.get(1));
        assertEquals(Y, list.get(2));
        assertEquals(B, list.get(3));
        assertEquals(C, list.get(4));
        assertEquals(Z, list.get(5));

        // Verifica degli indici dopo gli inserimenti
        // Utilizziamo un iteratore posizionato dopo l'ultimo add (alla fine)
        // Dopo l'ultimo add, il cursore è avanzato e l'iteratore è alla fine.
        // Quindi nextIndex dovrebbe essere list.size(), previousIndex size()-1.
        assertEquals(6, lit.nextIndex());
        assertEquals(5, lit.previousIndex());
        assertFalse(lit.hasNext());
        assertTrue(lit.hasPrevious());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code add()} su lista vuota inserisca
     * correttamente l'elemento come unico elemento della lista.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso limite di add() su lista vuota. L'iteratore
     * deve inserire l'elemento alla posizione 0, e la lista deve contenere un
     * solo elemento. Il cursore deve avanzare a 1 e nextIndex/previousIndex
     * devono aggiornarsi correttamente. Complementa testAdd che opera su
     * lista non vuota.
     * </p>
     * <p>
     * <b>Test Description</b> Su lista vuota, si invoca add(X). Si verifica che
     * la lista contenga [X], che size sia 1, che nextIndex sia 1 e previousIndex
     * sia 0. Si verifica anche che hasNext sia false e hasPrevious true.
     * </p>
     * <p>
     * <b>Pre-Condition</b> list = [], lit = list.listIterator().
     * </p>
     * <p>
     * <b>Post-Condition</b> list = [X]; iteratore posizionato dopo l'elemento.
     * </p>
     * <p>
     * <b>Expected Results</b> list.size() == 1; list.get(0) == X;
     * lit.nextIndex() == 1; lit.previousIndex() == 0; lit.hasNext() == false;
     * lit.hasPrevious() == true.
     * </p>
     */
    @Test
    public void testAddToEmptyList() {
        lit.add(X);
        assertEquals(1, list.size());
        assertEquals(X, list.get(0));
        assertEquals(1, lit.nextIndex());
        assertEquals(0, lit.previousIndex());
        assertFalse(lit.hasNext());
        assertTrue(lit.hasPrevious());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code remove()} dopo {@code next()} rimuova
     * l'elemento appena visitato.
     * </p>
     * <p>
     * <b>Test Case Design</b> Dopo next(), remove() deve eliminare l'elemento
     * restituito, spostando gli elementi successivi a sinistra. Il cursore
     * deve essere decrementato di 1 per puntare al nuovo elemento che occupa
     * la stessa posizione.
     * </p>
     * <p>
     * <b>Test Description</b> Su lista [A, B, C], si esegue next() per ottenere B,
     * poi remove() per eliminarlo. La lista diventa [A, C] e il cursore si
     * sposta in modo che il prossimo next() restituisca C.
     * </p>
     * <p>
     * <b>Pre-Condition</b> list = [A, B, C], lit = list.listIterator(1).
     * </p>
     * <p>
     * <b>Post-Condition</b> list = [A, C].
     * </p>
     * <p>
     * <b>Expected Results</b> list.size() == 2; il prossimo next() restituisce C.
     * </p>
     */
    @Test
    public void testRemoveAfterNext() {
        populateThree();
        lit = list.listIterator(1);
        assertEquals(B, lit.next());
        lit.remove();
        assertEquals(2, list.size());
        assertEquals(A, list.get(0));
        assertEquals(C, list.get(1));
        // il cursore ora è a 1 (ex posizione 1, ora C)
        assertEquals(C, lit.next());
        // Verifica indici dopo remove
        assertEquals(2, lit.nextIndex());
        assertEquals(1, lit.previousIndex());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code remove()} dopo {@code previous()} rimuova
     * l'elemento appena visitato.
     * </p>
     * <p>
     * <b>Test Case Design</b> Simmetrico a testRemoveAfterNext, ma usando
     * previous(). Dopo previous(), remove() deve eliminare l'elemento restituito.
     * Il cursore deve rimanere nella stessa posizione (perché la rimozione
     * avviene prima del cursore).
     * </p>
     * <p>
     * <b>Test Description</b> Su lista [A, B, C], si posiziona l'iteratore alla
     * fine (cursor=3), si esegue previous() per ottenere C, poi remove() per
     * eliminarlo. La lista diventa [A, B] e il cursore rimane a 2 (ora dopo B).
     * </p>
     * <p>
     * <b>Pre-Condition</b> list = [A, B, C], lit = list.listIterator(3).
     * </p>
     * <p>
     * <b>Post-Condition</b> list = [A, B].
     * </p>
     * <p>
     * <b>Expected Results</b> list.size() == 2; previous() successivo restituisce B.
     * </p>
     */
    @Test
    public void testRemoveAfterPrevious() {
        populateThree();
        lit = list.listIterator(3);
        assertEquals(C, lit.previous());
        lit.remove();
        assertEquals(2, list.size());
        assertEquals(A, list.get(0));
        assertEquals(B, list.get(1));
        // il cursore ora è a 2 (dopo B), e previous() restituisce B
        assertEquals(B, lit.previous());
        // Verifica indici dopo remove
        assertEquals(1, lit.nextIndex());
        assertEquals(0, lit.previousIndex());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code set()} possa essere chiamato due
     * volte dopo un singolo {@code next()}: la seconda chiamata sostituisce
     * nuovamente lo stesso elemento.
     * </p>
     * <p>
     * <b>Test Case Design</b> Il contratto J2SE 1.4.2 di ListIterator stabilisce
     * che {@code set()} sostituisce l'ultimo elemento restituito da
     * {@code next()} o {@code previous()} senza invalidare lo stato del
     * cursore. A differenza di {@code remove()} e {@code add()}, {@code set()}
     * non reimposta {@code lastRet} a -1, quindi una seconda chiamata è valida
     * e sostituisce nuovamente lo stesso elemento. Complementa
     * testSetAfterNext (set singolo) e testSetAfterAdd (set dopo add è ISE).
     * </p>
     * <p>
     * <b>Test Description</b> Su lista [A, B, C], si esegue next() per ottenere B,
     * poi set(X) per sostituirlo, poi set(Y) per sostituirlo di nuovo. La lista
     * deve riflettere l'ultima sostituzione (Y) e il cursore non deve muoversi.
     * </p>
     * <p>
     * <b>Pre-Condition</b> list = [A, B, C], lit = list.listIterator(1).
     * </p>
     * <p>
     * <b>Post-Condition</b> list = [A, Y, C]; iteratore posizionato dopo Y.
     * </p>
     * <p>
     * <b>Expected Results</b> list.get(1) == Y; nextIndex invariato.
     * </p>
     */
    @Test
    public void testDoubleSetAfterNext() {
        populateThree();
        lit = list.listIterator(1);
        assertEquals(B, lit.next());
        lit.set(X); // sostituisce B con X
        assertEquals(X, list.get(1));
        lit.set(Y); // sostituisce X con Y
        assertEquals(Y, list.get(1));
        assertEquals(2, lit.nextIndex());
    }

    // ====================================================================
    // C) Eccezioni e stati illegali (6)
    // ====================================================================

    /**
     * <p>
     * <b>Summary</b> {@code set()} senza un precedente {@code next()} o {@code previous()}
     * deve lanciare {@link IllegalStateException}.
     * </p>
     * <p>
     * <b>Test Case Design</b> Lo stato illegale si verifica quando lastRet == -1,
     * cioè quando l'iteratore non ha ancora restituito alcun elemento tramite
     * next() o previous(), o dopo un add() o remove(). Il test chiama set()
     * subito dopo la creazione dell'iteratore.
     * </p>
     * <p>
     * <b>Test Description</b> Su lista [A, B, C], si crea un iteratore e si
     * chiama set(X) senza aver chiamato next() o previous(). Deve lanciare
     * IllegalStateException e la lista deve rimanere invariata.
     * </p>
     * <p>
     * <b>Pre-Condition</b> list = [A, B, C], lit = list.listIterator().
     * </p>
     * <p>
     * <b>Post-Condition</b> list invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> IllegalStateException; list.size() == 3.
     * </p>
     */
    @Test
    public void testSetWithoutNextOrPrevious() {
        populateThree();
        try {
            lit.set(X);
            fail("set() senza next/previous deve lanciare IllegalStateException");
        } catch (IllegalStateException e) { /* atteso */ }
        assertEquals(3, list.size());
        assertEquals(A, list.get(0));
    }

    /**
     * <p>
     * <b>Summary</b> {@code set()} dopo {@code remove()} deve lanciare
     * {@link IllegalStateException}.
     * </p>
     * <p>
     * <b>Test Case Design</b> Dopo un {@code remove()}, l'ultimo elemento restituito
     * non è più valido per un'operazione di {@code set()}, perché {@code lastRet}
     * è stato reimpostato a -1. Il test verifica che una chiamata a {@code set()}
     * dopo {@code remove()} senza un nuovo {@code next()} o {@code previous()}
     * lanci {@link IllegalStateException}. Complementa testSetWithoutNextOrPrevious
     * (set senza next) e testSetAfterNext (set valido).
     * </p>
     * <p>
     * <b>Test Description</b> Su lista [A, B, C], si esegue next() per ottenere B,
     * poi remove() per eliminarlo. Si tenta set(X) senza un nuovo next/previous:
     * deve lanciare IllegalStateException.
     * </p>
     * <p>
     * <b>Pre-Condition</b> list = [A, B, C], lit = list.listIterator(1).
     * </p>
     * <p>
     * <b>Post-Condition</b> list = [A, C] (rimozione avvenuta), iteratore posizionato
     * dopo A.
     * </p>
     * <p>
     * <b>Expected Results</b> IllegalStateException; list.size() == 2.
     * </p>
     */
    @Test
    public void testSetAfterRemove() {
        populateThree();
        lit = list.listIterator(1);
        assertEquals(B, lit.next());
        lit.remove(); // rimuove B
        try {
            lit.set(X);
            fail("set() dopo remove() deve lanciare IllegalStateException");
        } catch (IllegalStateException e) { /* atteso */ }
        assertEquals(2, list.size());
        assertEquals(A, list.get(0));
        assertEquals(C, list.get(1));
    }

    /**
     * <p>
     * <b>Summary</b> {@code remove()} senza un precedente {@code next()} o
     * {@code previous()} deve lanciare {@link IllegalStateException}.
     * </p>
     * <p>
     * <b>Test Case Design</b> Simile al caso precedente, ma per remove().
     * remove() è ereditato da IteratorAdapter, ma deve essere testato anche
     * nel contesto di ListIterator.
     * </p>
     * <p>
     * <b>Test Description</b> Su lista [A, B, C], si crea un iteratore e si
     * chiama remove() senza aver chiamato next() o previous(). Deve lanciare
     * IllegalStateException e la lista deve rimanere invariata.
     * </p>
     * <p>
     * <b>Pre-Condition</b> list = [A, B, C], lit = list.listIterator().
     * </p>
     * <p>
     * <b>Post-Condition</b> list invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> IllegalStateException; list.size() == 3.
     * </p>
     */
    @Test
    public void testRemoveWithoutNextOrPrevious() {
        populateThree();
        try {
            lit.remove();
            fail("remove() senza next/previous deve lanciare IllegalStateException");
        } catch (IllegalStateException e) { /* atteso */ }
        assertEquals(3, list.size());
        assertEquals(A, list.get(0));
    }

    /**
     * <p>
     * <b>Summary</b> Dopo un {@code add()}, {@code remove()} non è più valido
     * e deve lanciare {@link IllegalStateException}.
     * </p>
     * <p>
     * <b>Test Case Design</b> add() imposta lastRet = -1, quindi qualsiasi
     * successiva chiamata a remove() deve fallire con IllegalStateException,
     * a meno che non venga chiamato un nuovo next() o previous().
     * </p>
     * <p>
     * <b>Test Description</b> Su lista [A, B, C], si chiama add(X) in posizione 1,
     * poi si tenta remove() senza next/previous intermedio. Deve lanciare
     * IllegalStateException e la lista deve riflettere l'inserimento.
     * </p>
     * <p>
     * <b>Pre-Condition</b> list = [A, B, C], lit = list.listIterator(1).
     * </p>
     * <p>
     * <b>Post-Condition</b> list = [A, X, B, C].
     * </p>
     * <p>
     * <b>Expected Results</b> IllegalStateException; list.size() == 4.
     * </p>
     */
    @Test
    public void testRemoveAfterAdd() {
        populateThree();
        lit = list.listIterator(1);
        lit.add(X); // [A, X, B, C]
        try {
            lit.remove();
            fail("remove() dopo add() deve lanciare IllegalStateException");
        } catch (IllegalStateException e) { /* atteso */ }
        assertEquals(4, list.size());
        assertEquals(X, list.get(1));
    }

    /**
     * <p>
     * <b>Summary</b> Chiamare {@code remove()} due volte dopo un singolo
     * {@code next()} deve lanciare {@link IllegalStateException} alla seconda
     * invocazione.
     * </p>
     * <p>
     * <b>Test Case Design</b> Dopo la prima {@code remove()}, {@code lastRet}
     * viene reimpostato a -1; una seconda {@code remove()} senza un nuovo
     * {@code next()} o {@code previous()} intermedio deve quindi lanciare
     * {@link IllegalStateException}. La lista deve riflettere solo la prima
     * rimozione. Complementa testRemoveAfterNext (rimozione singola valida)
     * e testRemoveWithoutNextOrPrevious (stato illegale senza next).
     * </p>
     * <p>
     * <b>Test Description</b> Su lista [A, B, C], si esegue next() per ottenere B,
     * poi remove() per eliminarlo. La lista diventa [A, C]. Si tenta un secondo
     * remove() senza un nuovo next(): deve lanciare IllegalStateException.
     * </p>
     * <p>
     * <b>Pre-Condition</b> list = [A, B, C], lit = list.listIterator(1).
     * </p>
     * <p>
     * <b>Post-Condition</b> list = [A, C]; iteratore posizionato dopo A.
     * </p>
     * <p>
     * <b>Expected Results</b> IllegalStateException alla seconda remove;
     * list.size() == 2 dopo la prima rimozione.
     * </p>
     */
    @Test
    public void testDoubleRemoveAfterNext() {
        populateThree();
        lit = list.listIterator(1);
        assertEquals(B, lit.next());
        lit.remove(); // rimuove B
        assertEquals(2, list.size());
        try {
            lit.remove();
            fail("secondo remove() senza next() deve lanciare IllegalStateException");
        } catch (IllegalStateException e) { /* atteso */ }
        assertEquals(2, list.size());
        assertEquals(A, list.get(0));
        assertEquals(C, list.get(1));
    }

    /**
     * <p>
     * <b>Summary</b> Dopo un {@code add()}, {@code set()} non è più valido
     * e deve lanciare {@link IllegalStateException}.
     * </p>
     * <p>
     * <b>Test Case Design</b> Simile a testRemoveAfterAdd, ma per set().
     * add() imposta lastRet = -1, quindi set() non può essere chiamato
     * senza un successivo next() o previous().
     * </p>
     * <p>
     * <b>Test Description</b> Su lista [A, B, C], si chiama add(X) in posizione 1,
     * poi si tenta set(Y) senza next/previous intermedio. Deve lanciare
     * IllegalStateException e la lista deve riflettere l'inserimento.
     * </p>
     * <p>
     * <b>Pre-Condition</b> list = [A, B, C], lit = list.listIterator(1).
     * </p>
     * <p>
     * <b>Post-Condition</b> list = [A, X, B, C].
     * </p>
     * <p>
     * <b>Expected Results</b> IllegalStateException; list.size() == 4.
     * </p>
     */
    @Test
    public void testSetAfterAdd() {
        populateThree();
        lit = list.listIterator(1);
        lit.add(X); // [A, X, B, C]
        try {
            lit.set(Y);
            fail("set() dopo add() deve lanciare IllegalStateException");
        } catch (IllegalStateException e) { /* atteso */ }
        assertEquals(4, list.size());
        assertEquals(X, list.get(1));
    }

    // ====================================================================
    // D) Bounds e casi limite (3)
    // ====================================================================

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code listIterator(int index)} con indice
     * fuori intervallo lanci {@link IndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Test Case Design</b> I valori validi per index sono [0, size()].
     * Le minime violazioni sono -1 (inferiore) e size()+1 (superiore). Su
     * lista di size 3, index=3 è valido (fine), mentre -1 e 4 sono invalidi.
     * </p>
     * <p>
     * <b>Test Description</b> Su lista [A, B, C], si tentano listIterator(-1)
     * e listIterator(4), attendendo IndexOutOfBoundsException. Si verifica
     * anche che listIterator(3) sia accettato.
     * </p>
     * <p>
     * <b>Pre-Condition</b> list = [A, B, C].
     * </p>
     * <p>
     * <b>Post-Condition</b> list invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> IndexOutOfBoundsException per -1 e 4;
     * listIterator(3) accettato.
     * </p>
     */
    @Test
    public void testListIteratorIndexBounds() {
        populateThree();
        try {
            list.listIterator(-1);
            fail("listIterator(-1) deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }

        try {
            list.listIterator(4);
            fail("listIterator(4) deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }

        // index 3 è valido (fine)
        HListIterator litEnd = list.listIterator(3);
        assertFalse(litEnd.hasNext());
        assertTrue(litEnd.hasPrevious());
    }

    /**
     * <p>
     * <b>Summary</b> Iteratore posizionato alla fine della lista: hasNext false,
     * hasPrevious true, previous restituisce l'ultimo elemento.
     * </p>
     * <p>
     * <b>Test Case Design</b> La fine della lista corrisponde a cursor == size().
     * In questa posizione, non ci sono elementi in avanti (hasNext=false),
     * ma c'è un elemento indietro (hasPrevious=true). previous() deve
     * restituire l'ultimo elemento e spostare il cursore prima di esso.
     * </p>
     * <p>
     * <b>Test Description</b> Su lista [A, B, C], si crea un iteratore con
     * index=3. Si verifica hasNext=false, hasPrevious=true, previous() restituisce C.
     * </p>
     * <p>
     * <b>Pre-Condition</b> list = [A, B, C].
     * </p>
     * <p>
     * <b>Post-Condition</b> list invariata; iteratore posizionato prima di C.
     * </p>
     * <p>
     * <b>Expected Results</b> hasNext=false, hasPrevious=true; previous() == C.
     * </p>
     */
    @Test
    public void testListIteratorAtEnd() {
        populateThree();
        lit = list.listIterator(3);
        assertFalse(lit.hasNext());
        assertTrue(lit.hasPrevious());
        assertEquals(3, lit.nextIndex());
        assertEquals(2, lit.previousIndex());
        assertEquals(C, lit.previous());
        assertEquals(2, lit.nextIndex());
        assertEquals(1, lit.previousIndex());
    }

    /**
     * <p>
     * <b>Summary</b> Iteratore posizionato all'inizio: hasPrevious false,
     * hasNext true, next restituisce il primo elemento.
     * </p>
     * <p>
     * <b>Test Case Design</b> L'inizio della lista corrisponde a cursor == 0.
     * In questa posizione, non ci sono elementi indietro (hasPrevious=false),
     * ma c'è un elemento in avanti (hasNext=true). next() deve restituire
     * il primo elemento e spostare il cursore dopo di esso.
     * </p>
     * <p>
     * <b>Test Description</b> Su lista [A, B, C], si crea un iteratore con
     * index=0 (default). Si verifica hasNext=true, hasPrevious=false,
     * next() restituisce A.
     * </p>
     * <p>
     * <b>Pre-Condition</b> list = [A, B, C].
     * </p>
     * <p>
     * <b>Post-Condition</b> list invariata; iteratore posizionato dopo A.
     * </p>
     * <p>
     * <b>Expected Results</b> hasNext=true, hasPrevious=false; next() == A.
     * </p>
     */
    @Test
    public void testListIteratorAtBeginning() {
        populateThree();
        lit = list.listIterator(0);
        assertTrue(lit.hasNext());
        assertFalse(lit.hasPrevious());
        assertEquals(0, lit.nextIndex());
        assertEquals(-1, lit.previousIndex());
        assertEquals(A, lit.next());
        assertEquals(1, lit.nextIndex());
        assertEquals(0, lit.previousIndex());
    }
}
