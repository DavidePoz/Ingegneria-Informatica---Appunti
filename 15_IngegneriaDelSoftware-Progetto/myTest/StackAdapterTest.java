package myTest;

import static org.junit.Assert.*;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;

/**
 * <p>
 * <b>Summary</b> Verifica il corretto funzionamento di {@link StackAdapter},
 * adapter J2SE 1.4.2 per {@code java.util.Stack} basato su {@code Vector} CLDC 1.1
 * tramite {@link VectorAdapter}. La suite copre le cinque operazioni
 * specifiche di Stack ({@code push}, {@code pop}, {@code peek}, {@code empty},
 * {@code search}) in casi normali e limite, la gestione delle eccezioni
 * ({@link EmptyStackException}) e l'ereditarietà da {@link VectorAdapter}.
 * <br>
 * Per eseguire questa test suite: {@code java -cp "bin:JUnit/*" org.junit.runner.JUnitCore myTest.StackAdapterTest}
 * </p>
 * <p>
 * <b>Test Case Design</b> La suite organizza i test in 4 categorie:
 * <ul>
 * <li>A) Operazioni Stack (5 test)</li>
 * <li>B) Operazioni Stack – casi limite (6 test)</li>
 * <li>C) Ereditarietà da VectorAdapter (4 test)</li>
 * <li>D) Eccezioni e gestione errori (2 test)</li>
 * </ul>
 * <br>
 * Ogni test utilizza il metodo {@link #setUp()} che crea un {@code StackAdapter}
 * vuoto. I test con dati usano metodi di supporto {@link #pushThree()} e
 * {@link #pushFive()} per popolare lo stack con 3 o 5 elementi.
 * <br>
 * I criteri di valutazione usano {@code assert} JUnit e verifica di eccezioni
 * attese ({@link EmptyStackException}). <br>
 * Per ogni test method la documentazione dettagliata riporta i valori concreti
 * delle variabili (es. {@code stack=[A,B,C]}, con C in cima).
 * <br>
 * <b>Versione JUnit:</b> Questa test suite è stata scritta utilizzando il framework JUnit 4.13.
 * <p>
 * <b>Test Cases</b>
 * </p>
 * <p>
 * <b>A) Operazioni Stack (5 test)</b>
 * </p>
 * <ul>
 * <li>{@link #testPush()} - Verifica che {@code push()} aggiunga elementi in cima
 * allo stack e che {@code size()} si aggiorni correttamente.</li>
 * <li>{@link #testPop()} - Verifica che {@code pop()} rimuova e restituisca
 * l'elemento in cima in ordine LIFO.</li>
 * <li>{@link #testPeek()} - Verifica che {@code peek()} restituisca l'elemento
 * in cima senza rimuoverlo.</li>
 * <li>{@link #testEmpty()} - Verifica che {@code empty()} rifletta correttamente
 * lo stato dello stack.</li>
 * <li>{@link #testSearch()} - Verifica che {@code search()} restituisca la
 * distanza corretta dalla cima per elementi presenti.</li>
 * </ul>
 * <p>
 * <b>B) Operazioni Stack – casi limite (6 test)</b>
 * </p>
 * <ul>
 * <li>{@link #testPopOnEmptyStack()} - {@code pop()} su stack vuoto lancia
 * {@link EmptyStackException}.</li>
 * <li>{@link #testPeekOnEmptyStack()} - {@code peek()} su stack vuoto lancia
 * {@link EmptyStackException}.</li>
 * <li>{@link #testPushAndPopSingleElement()} - Sequenza {@code push}/{@code pop}
 * su un singolo elemento.</li>
 * <li>{@link #testSearchWithDuplicates()} - {@code search()} su stack con
 * duplicati trova l'occorrenza più vicina alla cima.</li>
 * <li>{@link #testSearchWithNullInStack()} - {@code search()} su stack contenente
 * null restituisce la distanza corretta per l'occorrenza più vicina alla cima.</li>
 * <li>{@link #testPushNull()} - Verifica che {@code null} possa essere inserito
 * nello stack (come da contratto Vector).</li>
 * </ul>
 * <p>
 * <b>C) Ereditarietà da VectorAdapter (4 test)</b>
 * </p>
 * <ul>
 * <li>{@link #testInheritedAddAndGet()} - Verifica che {@code add()} e
 * {@code get()} ereditati funzionino sullo stack.</li>
 * <li>{@link #testInheritedRemove()} - Verifica che {@code remove()} ereditato
 * rimuova l'elemento specificato.</li>
 * <li>{@link #testInheritedSizeAndIsEmpty()} - Verifica che {@code size()} e
 * {@code isEmpty()} funzionino sullo stack.</li>
 * <li>{@link #testInheritedClear()} - Verifica che {@code clear()} svuoti lo stack.</li>
 * </ul>
 * <p>
 * <b>D) Eccezioni e gestione errori (2 test)</b>
 * </p>
 * <ul>
 * <li>{@link #testSearchOnEmptyStack()} - {@code search()} su stack vuoto
 * restituisce -1.</li>
 * <li>{@link #testSearchNotFound()} - {@code search()} per un elemento non
 * presente restituisce -1.</li>
 * </ul>
 */
public class StackAdapterTest {

    /**
     * Costruttore pubblico di default.
     */
    public StackAdapterTest() {}

    private StackAdapter stack;

    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static final String D = "D";
    private static final String E = "E";
    private static final String X = "X";
    private static final String Y = "Y";
    private static final String Z = "Z";

    /**
     * Crea uno StackAdapter vuoto.
     */
    @Before
    public void setUp() {
        stack = new StackAdapter();
    }

    /**
     * Popola lo stack con tre elementi [A, B, C] (C in cima).
     */
    private void pushThree() {
        stack.push(A);
        stack.push(B);
        stack.push(C);
    }

    /**
     * Popola lo stack con cinque elementi [A, B, C, D, E] (E in cima).
     */
    private void pushFive() {
        stack.push(A);
        stack.push(B);
        stack.push(C);
        stack.push(D);
        stack.push(E);
    }

    // ====================================================================
    // A) Operazioni Stack
    // ====================================================================

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code push()} aggiunga elementi in cima
     * allo stack e che {@code size()} si aggiorni correttamente.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale di inserimento di elementi in uno
     * stack. {@code push()} deve aggiungere l'elemento alla fine della lista
     * (cima dello stack) e restituire l'elemento stesso. La size deve
     * incrementare di 1 per ogni push. Il test utilizza tre push consecutivi
     * e verifica che l'ultimo elemento pushato sia quello in cima (posizione
     * size-1). Complementa testPopOnEmptyStack (pop su vuoto) e
     * testPushAndPopSingleElement (push/pop singolo).
     * </p>
     * <p>
     * <b>Test Description</b> Su stack vuoto, si invoca push(A), push(B),
     * push(C). Si verifica che i valori restituiti da push() siano A, B, C
     * e che size passi da 0 a 3. Si controlla che l'elemento in cima
     * (posizione 2) sia C.
     * </p>
     * <p>
     * <b>Pre-Condition</b> stack vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> stack = [A, B, C] (C in cima), size = 3.
     * </p>
     * <p>
     * <b>Expected Results</b> push() restituisce l'elemento inserito;
     * size incrementa; l'ultimo elemento pushato è in cima.
     * </p>
     */
    @Test
    public void testPush() {
        assertEquals(0, stack.size());
        assertSame(A, stack.push(A));
        assertEquals(1, stack.size());
        assertSame(B, stack.push(B));
        assertEquals(2, stack.size());
        assertSame(C, stack.push(C));
        assertEquals(3, stack.size());
        assertEquals(C, stack.get(2)); // cima
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code pop()} rimuova e restituisca
     * l'elemento in cima in ordine LIFO.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale di rimozione dallo stack.
     * {@code pop()} deve rimuovere l'ultimo elemento della lista (cima)
     * e restituirlo. La size deve decrementare di 1. L'ordine di estrazione
     * deve essere LIFO (l'ultimo pushato è il primo popato). Il test
     * utilizza tre push seguiti da tre pop e verifica che gli elementi
     * vengano estratti in ordine inverso. Complementa testPopOnEmptyStack
     * (pop su vuoto) e testPush (push).
     * </p>
     * <p>
     * <b>Test Description</b> Dopo push(A), push(B), push(C), si invoca
     * pop() tre volte. Si verifica che i valori restituiti siano C, B, A
     * e che size passi da 3 a 0.
     * </p>
     * <p>
     * <b>Pre-Condition</b> stack = [A, B, C] (C in cima).
     * </p>
     * <p>
     * <b>Post-Condition</b> stack = [], size = 0.
     * </p>
     * <p>
     * <b>Expected Results</b> pop() restituisce C, poi B, poi A;
     * size decrementa a 0.
     * </p>
     */
    @Test
    public void testPop() {
        pushThree();
        assertEquals(C, stack.pop());
        assertEquals(2, stack.size());
        assertEquals(B, stack.pop());
        assertEquals(1, stack.size());
        assertEquals(A, stack.pop());
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code peek()} restituisca l'elemento
     * in cima senza rimuoverlo.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale di lettura della cima senza
     * modifica dello stack. {@code peek()} deve restituire l'ultimo elemento
     * della lista senza rimuoverlo e senza alterare la size. Il test
     * verifica che chiamate multiple a peek() restituiscano lo stesso
     * elemento e che lo stack rimanga invariato. Complementa
     * testPop (che rimuove) e testPeekOnEmptyStack (peek su vuoto).
     * </p>
     * <p>
     * <b>Test Description</b> Dopo push(A), push(B), push(C), si invoca
     * peek() due volte. Si verifica che entrambe restituiscano C e che
     * size resti 3 e che l'elemento in cima sia ancora C.
     * </p>
     * <p>
     * <b>Pre-Condition</b> stack = [A, B, C] (C in cima).
     * </p>
     * <p>
     * <b>Post-Condition</b> stack = [A, B, C] (invariato), size = 3.
     * </p>
     * <p>
     * <b>Expected Results</b> peek() restituisce C a ogni chiamata;
     * size e contenuto invariati.
     * </p>
     */
    @Test
    public void testPeek() {
        pushThree();
        assertEquals(C, stack.peek());
        assertEquals(C, stack.peek());
        assertEquals(3, stack.size());
        assertEquals(C, stack.get(2));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code empty()} rifletta correttamente
     * lo stato dello stack.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale di verifica dello stato vuoto/non
     * vuoto dello stack. {@code empty()} deve restituire true se lo stack
     * è vuoto, false altrimenti. Il test verifica il predicato su stack
     * vuoto, dopo push, dopo pop parziale e dopo svuotamento completo.
     * Complementa testInheritedSizeAndIsEmpty (isEmpty ereditato).
     * </p>
     * <p>
     * <b>Test Description</b> Si verifica empty() true su stack vuoto.
     * Dopo push(A), empty() deve essere false. Dopo pop(), che svuota lo
     * stack, empty() deve tornare true.
     * </p>
     * <p>
     * <b>Pre-Condition</b> stack vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> stack invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> empty() true su vuoto, false dopo push,
     * true dopo svuotamento.
     * </p>
     */
    @Test
    public void testEmpty() {
        assertTrue(stack.empty());
        stack.push(A);
        assertFalse(stack.empty());
        stack.pop();
        assertTrue(stack.empty());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code search()} restituisca la
     * distanza corretta dalla cima per elementi presenti.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale di ricerca lineare dall'alto
     * verso il basso. {@code search()} deve restituire la distanza dalla
     * cima (1-based) della prima occorrenza più vicina alla cima. Il test
     * utilizza uno stack con elementi [A, B, C, D, E] (E in cima) e
     * verifica le distanze per ciascun elemento. Complementa
     * testSearchWithDuplicates (duplicati) e testSearchOnEmptyStack
     * (ricerca su vuoto).
     * </p>
     * <p>
     * <b>Test Description</b> Dopo push(A..E), si verifica che search(E)
     * restituisca 1, search(D) 2, search(C) 3, search(B) 4, search(A) 5.
     * Si verifica anche che search(X) restituisca -1 per elemento assente.
     * </p>
     * <p>
     * <b>Pre-Condition</b> stack = [A, B, C, D, E] (E in cima).
     * </p>
     * <p>
     * <b>Post-Condition</b> stack invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> search() restituisce distanze 1..5 per
     * elementi presenti; -1 per assente.
     * </p>
     */
    @Test
    public void testSearch() {
        pushFive();
        assertEquals(1, stack.search(E));
        assertEquals(2, stack.search(D));
        assertEquals(3, stack.search(C));
        assertEquals(4, stack.search(B));
        assertEquals(5, stack.search(A));
        assertEquals(-1, stack.search(X));
    }

    // ====================================================================
    // B) Operazioni Stack – casi limite
    // ====================================================================

    /**
     * <p>
     * <b>Summary</b> {@code pop()} su stack vuoto lancia {@link EmptyStackException}.
     * </p>
     * <p>
     * <b>Test Case Design</b> Classe di errore per operazioni su stack
     * vuoto. Il contratto di Stack in J2SE 1.4.2 prescrive che
     * {@code pop()} lanci {@link EmptyStackException} quando lo stack
     * è vuoto. Il test verifica che l'eccezione venga lanciata e che lo
     * stack rimanga vuoto. Complementa testPop (pop valido).
     * </p>
     * <p>
     * <b>Test Description</b> Su stack vuoto, si invoca pop() e si verifica
     * che venga lanciata EmptyStackException. Si controlla che size resti 0.
     * </p>
     * <p>
     * <b>Pre-Condition</b> stack vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> stack invariato (vuoto).
     * </p>
     * <p>
     * <b>Expected Results</b> EmptyStackException; size == 0.
     * </p>
     */
    @Test
    public void testPopOnEmptyStack() {
        try {
            stack.pop();
            fail("pop() su stack vuoto deve lanciare EmptyStackException");
        } catch (EmptyStackException e) { /* atteso */ }
        assertEquals(0, stack.size());
    }

    /**
     * <p>
     * <b>Summary</b> {@code peek()} su stack vuoto lancia {@link EmptyStackException}.
     * </p>
     * <p>
     * <b>Test Case Design</b> Classe di errore per lettura su stack vuoto.
     * Il contratto di Stack in J2SE 1.4.2 prescrive che {@code peek()}
     * lanci {@link EmptyStackException} quando lo stack è vuoto. Il test
     * verifica che l'eccezione venga lanciata e che lo stack rimanga vuoto.
     * Complementa testPeek (peek valido).
     * </p>
     * <p>
     * <b>Test Description</b> Su stack vuoto, si invoca peek() e si verifica
     * che venga lanciata EmptyStackException. Si controlla che size resti 0.
     * </p>
     * <p>
     * <b>Pre-Condition</b> stack vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> stack invariato (vuoto).
     * </p>
     * <p>
     * <b>Expected Results</b> EmptyStackException; size == 0.
     * </p>
     */
    @Test
    public void testPeekOnEmptyStack() {
        try {
            stack.peek();
            fail("peek() su stack vuoto deve lanciare EmptyStackException");
        } catch (EmptyStackException e) { /* atteso */ }
        assertEquals(0, stack.size());
    }

    /**
     * <p>
     * <b>Summary</b> Sequenza {@code push}/{@code pop} su un singolo elemento.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso di frontiera con stack di un solo elemento.
     * La sequenza push(X), pop() deve restituire X e riportare lo stack
     * allo stato vuoto. Il test verifica che dopo il pop lo stack sia
     * nuovamente vuoto e che un successivo pop() lanci EmptyStackException.
     * Complementa testPop (pop multiplo) e testPopOnEmptyStack.
     * </p>
     * <p>
     * <b>Test Description</b> Su stack vuoto, si push(X), si verifica che
     * size sia 1, si pop() e si verifica che size torni a 0 e che l'elemento
     * restituito sia X. Si verifica infine che empty() sia true.
     * </p>
     * <p>
     * <b>Pre-Condition</b> stack vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> stack vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> pop() restituisce X; size torna a 0.
     * </p>
     */
    @Test
    public void testPushAndPopSingleElement() {
        assertSame(X, stack.push(X));
        assertEquals(1, stack.size());
        assertSame(X, stack.pop());
        assertEquals(0, stack.size());
        assertTrue(stack.empty());
        try {
            stack.pop();
            fail("pop() dopo svuotamento deve lanciare EmptyStackException");
        } catch (EmptyStackException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> {@code search()} su stack con duplicati trova
     * l'occorrenza più vicina alla cima.
     * </p>
     * <p>
     * <b>Test Case Design</b> Classe di equivalenza degli elementi duplicati.
     * {@code search()} deve restituire la distanza della prima occorrenza
     * più vicina alla cima, non la più lontana. Il test utilizza uno stack
     * con duplicati di B e D e verifica che search(B) restituisca la
     * distanza dell'occorrenza più vicina alla cima (posizione 4, distanza 2)
     * e non quella più lontana (posizione 1, distanza 5). Complementa
     * testSearch (ricerca su elementi unici).
     * </p>
     * <p>
     * <b>Test Description</b> Dopo push(A), push(B), push(C), push(B),
     * push(D) (stack = [A, B, C, B, D] con D in cima), si verifica che
     * search(B) restituisca 2 (distanza dell'occorrenza più vicina alla
     * cima in posizione 3) e non 5 (occorrenza in posizione 1). Si verifica
     * anche search(A) = 5, search(C) = 3, search(D) = 1.
     * </p>
     * <p>
     * <b>Pre-Condition</b> stack = [A, B, C, B, D] (D in cima).
     * </p>
     * <p>
     * <b>Post-Condition</b> stack invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> search(B) == 2 (occorrenza più vicina
     * alla cima).
     * </p>
     */
    @Test
    public void testSearchWithDuplicates() {
        stack.push(A);
        stack.push(B);
        stack.push(C);
        stack.push(B);
        stack.push(D); // stack: [A, B, C, B, D], D in cima

        assertEquals(1, stack.search(D));
        assertEquals(2, stack.search(B)); // occorrenza più vicina alla cima in posizione 3
        assertEquals(3, stack.search(C));
        assertEquals(5, stack.search(A));
        assertEquals(-1, stack.search(X));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code search()} su stack contenente null
     * restituisca la distanza corretta per l'occorrenza più vicina alla cima.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso di ricerca dell'elemento null, che è un
     * valore legittimo in Vector/Stack. Il metodo search() deve trattare null
     * come qualsiasi altro elemento, restituendo la distanza dalla cima della
     * prima occorrenza più vicina. Il test utilizza uno stack con null in
     * posizione intermedia e in cima per verificare che venga trovata
     * l'occorrenza più vicina alla cima. Complementa testSearchWithDuplicates
     * (duplicati non null) e testPushNull (push/pop di null).
     * </p>
     * <p>
     * <b>Test Description</b> Dopo push(A), push(null), push(B), push(null),
     * push(C) (stack = [A, null, B, null, C] con C in cima), si verifica che
     * search(null) restituisca 2 (l'occorrenza in posizione 3, distanza 2)
     * e non 4 (occorrenza in posizione 1). Si verifica anche search(C)=1,
     * search(B)=3, search(A)=5.
     * </p>
     * <p>
     * <b>Pre-Condition</b> stack vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> stack invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> search(null) == 2; gli altri elementi hanno
     * distanze corrette.
     * </p>
     */
    @Test
    public void testSearchWithNullInStack() {
        stack.push(A);
        stack.push(null);
        stack.push(B);
        stack.push(null);
        stack.push(C); // stack: [A, null, B, null, C], C in cima

        assertEquals(1, stack.search(C));
        assertEquals(2, stack.search(null)); // occorrenza più vicina alla cima (posizione 3, distanza 2)
        assertEquals(3, stack.search(B));
        assertEquals(5, stack.search(A));
        assertEquals(-1, stack.search(X));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code null} possa essere inserito nello
     * stack (come da contratto Vector).
     * </p>
     * <p>
     * <b>Test Case Design</b> Classe di equivalenza dell'elemento null.
     * Lo stack, essendo un Vector, supporta elementi null. Il test verifica
     * che push(null) funzioni, che peek() restituisca null e che pop()
     * rimuova null. Complementa testSearchWithNullInStack (search su null).
     * </p>
     * <p>
     * <b>Test Description</b> Su stack vuoto, si push(null), si verifica
     * che peek() restituisca null, che pop() restituisca null e che
     * lo stack torni vuoto.
     * </p>
     * <p>
     * <b>Pre-Condition</b> stack vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> stack vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> push(null), peek() == null, pop() == null.
     * </p>
     */
    @Test
    public void testPushNull() {
        stack.push(null);
        assertEquals(1, stack.size());
        assertNull(stack.peek());
        assertNull(stack.pop());
        assertEquals(0, stack.size());
    }

    // ====================================================================
    // C) Ereditarietà da VectorAdapter
    // ====================================================================

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code add()} e {@code get()} ereditati
     * funzionino sullo stack.
     * </p>
     * <p>
     * <b>Test Case Design</b> Verifica che i metodi ereditati da VectorAdapter
     * siano accessibili e funzionino correttamente su uno StackAdapter.
     * Il test utilizza add() per inserire elementi e get() per leggerli,
     * dimostrando che lo stack può essere trattato come un Vector.
     * Complementa testPush, che usa push() invece di add().
     * </p>
     * <p>
     * <b>Test Description</b> Su stack vuoto, si invoca add(A), add(B),
     * add(C). Si verifica che size sia 3 e che get(0), get(1), get(2)
     * restituiscano A, B, C nell'ordine di inserimento (cima = C).
     * </p>
     * <p>
     * <b>Pre-Condition</b> stack vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> stack = [A, B, C].
     * </p>
     * <p>
     * <b>Expected Results</b> add() funziona; get() restituisce gli
     * elementi nell'ordine corretto.
     * </p>
     */
    @Test
    public void testInheritedAddAndGet() {
        stack.add(A);
        stack.add(B);
        stack.add(C);
        assertEquals(3, stack.size());
        assertEquals(A, stack.get(0));
        assertEquals(B, stack.get(1));
        assertEquals(C, stack.get(2));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code remove()} ereditato rimuova
     * l'elemento specificato.
     * </p>
     * <p>
     * <b>Test Case Design</b> Verifica che i metodi di rimozione ereditati
     * da VectorAdapter funzionino sullo stack. Il test utilizza remove(Object)
     * per rimuovere un elemento centrale e remove(int) per rimuovere un
     * elemento per indice. Complementa testPop (rimozione specifica dello
     * stack).
     * </p>
     * <p>
     * <b>Test Description</b> Dopo push(A), push(B), push(C), si invoca
     * remove(B) per rimuovere l'elemento B (posizione 1). Si verifica che
     * size sia 2 e che gli elementi rimasti siano A, C. Si invoca quindi
     * remove(0) per rimuovere A e si verifica che size sia 1 con C.
     * </p>
     * <p>
     * <b>Pre-Condition</b> stack = [A, B, C].
     * </p>
     * <p>
     * <b>Post-Condition</b> stack = [C].
     * </p>
     * <p>
     * <b>Expected Results</b> remove(B) restituisce true; remove(0)
     * restituisce A; size finale 1.
     * </p>
     */
    @Test
    public void testInheritedRemove() {
        pushThree();
        assertTrue(stack.remove(B));
        assertEquals(2, stack.size());
        assertEquals(A, stack.get(0));
        assertEquals(C, stack.get(1));

        assertEquals(A, stack.remove(0));
        assertEquals(1, stack.size());
        assertEquals(C, stack.get(0));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code size()} e {@code isEmpty()}
     * funzionino sullo stack.
     * </p>
     * <p>
     * <b>Test Case Design</b> Verifica che i metodi di osservazione ereditati
     * da VectorAdapter riflettano lo stato dello stack. Il test verifica
     * size e isEmpty su stack vuoto, dopo push e dopo pop. Complementa
     * testEmpty (che usa il metodo specifico empty()).
     * </p>
     * <p>
     * <b>Test Description</b> Si verifica size=0 e isEmpty=true su stack
     * vuoto. Dopo push(A), size=1 e isEmpty=false. Dopo pop(), size=0 e
     * isEmpty=true.
     * </p>
     * <p>
     * <b>Pre-Condition</b> stack vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> stack vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> size e isEmpty coerenti con lo stato.
     * </p>
     */
    @Test
    public void testInheritedSizeAndIsEmpty() {
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());

        stack.push(A);
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());

        stack.pop();
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code clear()} svuoti lo stack.
     * </p>
     * <p>
     * <b>Test Case Design</b> Verifica che il metodo clear() ereditato da
     * VectorAdapter funzioni correttamente su uno StackAdapter. clear()
     * deve rimuovere tutti gli elementi, lasciando size=0 e stack vuoto.
     * Complementa testPop (svuotamento progressivo).
     * </p>
     * <p>
     * <b>Test Description</b> Dopo push(A), push(B), push(C), si invoca
     * clear(). Si verifica che size sia 0, che isEmpty sia true e che
     * un tentativo di peek() lanci EmptyStackException.
     * </p>
     * <p>
     * <b>Pre-Condition</b> stack = [A, B, C].
     * </p>
     * <p>
     * <b>Post-Condition</b> stack vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> size=0, isEmpty=true; peek() lancia
     * EmptyStackException.
     * </p>
     */
    @Test
    public void testInheritedClear() {
        pushThree();
        stack.clear();
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
        try {
            stack.peek();
            fail("peek() dopo clear() deve lanciare EmptyStackException");
        } catch (EmptyStackException e) { /* atteso */ }
    }

    // ====================================================================
    // D) Eccezioni e gestione errori
    // ====================================================================

    /**
     * <p>
     * <b>Summary</b> {@code search()} su stack vuoto restituisce -1.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso di ricerca su stack vuoto. Il contratto
     * di {@code search()} in J2SE 1.4.2 prescrive che su stack vuoto il
     * metodo restituisca -1 senza lanciare eccezioni. Il test verifica
     * questo comportamento. Complementa testSearch (ricerca su stack
     * popolato) e testSearchNotFound (ricerca di elemento assente).
     * </p>
     * <p>
     * <b>Test Description</b> Su stack vuoto, si invoca search(A) e si
     * verifica che restituisca -1.
     * </p>
     * <p>
     * <b>Pre-Condition</b> stack vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> stack invariato (vuoto).
     * </p>
     * <p>
     * <b>Expected Results</b> search() == -1.
     * </p>
     */
    @Test
    public void testSearchOnEmptyStack() {
        assertEquals(-1, stack.search(A));
        assertEquals(-1, stack.search(null));
    }

    /**
     * <p>
     * <b>Summary</b> {@code search()} per un elemento non presente
     * restituisce -1.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso di ricerca di elemento assente su stack
     * popolato. Il contratto di {@code search()} in J2SE 1.4.2 prescrive
     * che se l'elemento non è presente nello stack, il metodo restituisca
     * -1. Il test verifica questo comportamento su uno stack popolato.
     * Complementa testSearch (ricerca di elementi presenti) e
     * testSearchOnEmptyStack (ricerca su vuoto).
     * </p>
     * <p>
     * <b>Test Description</b> Dopo push(A), push(B), push(C), si invoca
     * search(X) e si verifica che restituisca -1. Si verifica anche
     * search(null) su stack che non contiene null.
     * </p>
     * <p>
     * <b>Pre-Condition</b> stack = [A, B, C].
     * </p>
     * <p>
     * <b>Post-Condition</b> stack invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> search(X) == -1; search(null) == -1.
     * </p>
     */
    @Test
    public void testSearchNotFound() {
        pushThree();
        assertEquals(-1, stack.search(X));
        assertEquals(-1, stack.search(null));
    }
}
