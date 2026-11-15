package myTest;

import static org.junit.Assert.*;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * <p>
 * <b>Summary</b> Verifica il corretto funzionamento dell'iteratore {@code HIterator}
 * prodotto da {@link AbstractHList#iterator()}. La suite copre l'iterazione su
 * liste non vuote (inclusi duplicati e null) e liste vuote, il metodo
 * {@code remove()} nei casi normali ed eccezionali, e le operazioni ripetute
 * (idempotenza di {@code hasNext()}).
 * <br>
 * Per eseguire questa test suite: {@code java -cp "bin:JUnit/*" org.junit.runner.JUnitCore myTest.IteratorAdapterTest}
 * </p>
 * <p>
 * <b>Test Case Design</b> La suite organizza i test in 4 categorie:
 * <ul>
 * <li>A) Iterazione normale (4 test)</li>
 * <li>B) Iterazione su lista vuota (1 test)</li>
 * <li>C) Rimozione – operazioni valide (7 test)</li>
 * <li>D) Rimozione – eccezioni e stati illegali (3 test)</li>
 * </ul>
 * <br>
 * Ogni test utilizza il metodo {@link #setUp()} che crea un {@code VectorAdapter}
 * vuoto. I test con dati usano metodi di supporto {@link #populateThree()} e
 * {@link #populateFive()} per popolare la lista con 3 o 5 elementi.
 * <br>
 * I criteri di valutazione usano {@code assert} JUnit e verifica di eccezioni
 * attese ({@link NoSuchElementException}, {@link IllegalStateException}). <br>
 * Per ogni test method la documentazione dettagliata riporta i valori concreti delle
 * variabili (es. {@code list=[A,B,C]}, {@code it= list.iterator()}). <br>
 * <b>Versione JUnit:</b> Questa test suite è stata scritta utilizzando il framework JUnit 4.13.
 * <p>
 * <b>Test Cases</b>
 * </p>
 * <p>
 * <b>A) Iterazione normale (4 test)</b>
 * </p>
 * <ul>
 * <li>{@link #testIterationOverNonEmptyList()} - Verifica che {@code hasNext()} e {@code next()} funzionino
 * correttamente su una lista di 3 elementi e che {@code next()} oltre la fine lanci {@link NoSuchElementException}.</li>
 * <li>{@link #testHasNextIdempotent()} - Verifica che chiamate ripetute a {@code hasNext()} senza {@code next()}
 * restituiscano lo stesso valore (idempotenza).</li>
 * <li>{@link #testIterationWithDuplicates()} - Verifica che l'iteratore gestisca correttamente elementi
 * duplicati.</li>
 * <li>{@link #testIterationWithNullElements()} - Verifica che l'iteratore gestisca correttamente elementi
 * null.</li>
 * </ul>
 * <p>
 * <b>B) Iterazione su lista vuota (1 test)</b>
 * </p>
 * <ul>
 * <li>{@link #testIteratorOnEmptyList()} - Verifica che su lista vuota {@code hasNext()} sia false e
 * {@code next()} lanci {@link NoSuchElementException}.</li>
 * </ul>
 * <p>
 * <b>C) Rimozione - operazioni valide (7 test)</b>
 * </p>
 * <ul>
 * <li>{@link #testRemoveAfterNext()} - Verifica che {@code remove()} dopo un {@code next()} rimuova
 * correttamente l'ultimo elemento restituito.</li>
 * <li>{@link #testRemoveAndContinueIteration()} - Rimozione di un elemento centrale e prosecuzione
 * dell'iterazione per verificare la consistenza.</li>
 * <li>{@link #testRemoveLastElement()} - Rimozione dell'ultimo elemento e verifica che l'iteratore
 * termini correttamente.</li>
 * <li>{@link #testRemoveAllElements()} - Rimozione di tutti gli elementi tramite iteratore e
 * verifica che la lista risulti vuota.</li>
 * <li>{@link #testRemoveAlternatingElements()} - Rimozione di elementi in posizioni dispari per
 * verificare la consistenza in uno scenario intermedio.</li>
 * <li>{@link #testRemoveOnSingleElementList()} - Rimozione dell'unico elemento di una lista e
 * verifica dello stato finale dell'iteratore.</li>
 * <li>{@link #testIteratorAfterRemoveAll()} - Verifica che, dopo aver svuotato la lista tramite
 * iteratore, un nuovo iteratore sulla stessa lista veda lo stato vuoto.</li>
 * </ul>
 * <p>
 * <b>D) Rimozione – eccezioni e stati illegali (3 test)</b>
* </p>
* <ul>
* <li>{@link #testRemoveWithoutNext()} - Chiamata a {@code remove()} senza aver chiamato {@code next()}
* deve lanciare {@link IllegalStateException}.</li>
* <li>{@link #testRemoveTwiceWithoutNext()} - Chiamare {@code remove()} due volte dopo un solo {@code next()}
* deve lanciare {@link IllegalStateException}.</li>
 * <li>{@link #testRemoveAfterModificationByList()} - Modifica della lista direttamente (add/remove)
 * dopo la creazione dell'iteratore e successiva chiamata di {@code remove()} (comportamento indefinito,
 * il test controlla solo l'assenza di eccezioni).</li>
 * </ul>
    */
    public class IteratorAdapterTest {

        /**
         * Costruttore pubblico di default.
         */
        public IteratorAdapterTest() {}

        private VectorAdapter list;
        private HIterator it;

        private static final String A = "A";
        private static final String B = "B";
        private static final String C = "C";
        private static final String D = "D";
        private static final String E = "E";
        private static final String X = "X";

        /**
         * Crea una lista vuota e un iteratore su di essa.
         * Ogni test che necessita di una lista popolata chiamerà i metodi di supporto.
         */
        @Before
        public void setUp() {
            list = new VectorAdapter();
            it = list.iterator();
        }

        /**
         * Popola la lista con tre elementi [A, B, C] e sostituisce l'iteratore.
         */
        private void populateThree() {
            list.add(A);
            list.add(B);
            list.add(C);
            it = list.iterator();
        }

        /**
         * Popola la lista con cinque elementi [A, B, C, D, E] e sostituisce l'iteratore.
         */
        private void populateFive() {
            list.add(A);
            list.add(B);
            list.add(C);
            list.add(D);
            list.add(E);
            it = list.iterator();
        }

        // ====================================================================
        // A) Iterazione normale
        // ====================================================================

        /**
         * <p>
         * <b>Summary</b> Verifica che {@code hasNext()} e {@code next()} funzionino
         * correttamente su una lista di 3 elementi e che {@code next()} oltre la fine
         * lanci {@link NoSuchElementException}.
         * </p>
         * <p>
         * <b>Test Case Design</b> Caso normale di iterazione sequenziale su lista
         * non vuota. Si usa la frontiera minima di 3 elementi per validare il ciclo
         * completo {@code hasNext/next}, sufficiente a coprire la transizione tra
         * elementi interni e la condizione di esaurimento. Il numero di chiamate a
         * {@code next()} è fissato a {@code size()}, e si verifica esplicitamente il
         * {@link NoSuchElementException} a esaurimento come da contratto
         * {@link java.util.Iterator Iterator}. Complementa testIteratorOnEmptyList
         * (boundary vuota) e testRemoveAfterNext (iterazione con rimozione).
         * </p>
         * <p>
         * <b>Test Description</b> Dopo aver popolato la lista con [A, B, C],
         * l'iteratore viene utilizzato per estrarre i tre elementi con {@code next()},
         * controllando {@code hasNext()} prima e dopo ogni estrazione. Al termine
         * si verifica che {@code hasNext()} sia false e che un ulteriore {@code next()}
         * lanci {@link NoSuchElementException}.
         * </p>
         * <p>
         * <b>Pre-Condition</b> list = [A, B, C], it = list.iterator().
         * </p>
         * <p>
         * <b>Post-Condition</b> list invariata, iteratore esaurito.
         * </p>
         * <p>
         * <b>Expected Results</b> hasNext() true per i primi tre step, false dopo;
         * next() restituisce A, B, C; beyond end lancia NoSuchElementException.
         * </p>
         */
        @Test
        public void testIterationOverNonEmptyList() {
            populateThree();
            assertTrue(it.hasNext());
            assertEquals(A, it.next());
            assertTrue(it.hasNext());
            assertEquals(B, it.next());
            assertTrue(it.hasNext());
            assertEquals(C, it.next());
            assertFalse(it.hasNext());
            try {
                it.next();
                fail("next() oltre la fine deve lanciare NoSuchElementException");
            } catch (NoSuchElementException e) { /* atteso */ }
        }

        /**
         * <p>
         * <b>Summary</b> Verifica che chiamate ripetute a {@code hasNext()} senza
         * {@code next()} restituiscano lo stesso valore (idempotenza).
         * </p>
         * <p>
         * <b>Test Case Design</b> Categoria ripetute: l'idempotenza di
         * {@code hasNext()} è richiesta dal contratto {@link java.util.Iterator}
         * per garantire che il metodo non alteri lo stato interno del cursore.
         * Si eseguono tre chiamate consecutive a {@code hasNext()} sia prima del
         * consumo sia dopo l'esaurimento, verificando che il risultato resti
         * costante in entrambi i casi. Complementa testIterationOverNonEmptyList
         * che esegue una singola chiamata a {@code hasNext()} per ogni step.
         * </p>
         * <p>
         * <b>Test Description</b> Su una lista di 3 elementi, si chiama {@code hasNext()}
         * tre volte consecutive e si verifica che restituisca sempre true. Dopo aver
         * consumato tutti gli elementi con {@code next()}, si ripete la stessa
         * verifica con atteso false.
         * </p>
         * <p>
         * <b>Pre-Condition</b> list = [A, B, C], it = list.iterator().
         * </p>
         * <p>
         * <b>Post-Condition</b> list invariata, iteratore posizionato dopo l'ultimo
         * elemento a fine test.
         * </p>
         * <p>
         * <b>Expected Results</b> hasNext() restituisce sempre lo stesso valore
         * (true prima del consumo, false dopo).
         * </p>
         */
        @Test
        public void testHasNextIdempotent() {
            populateThree();
            assertTrue(it.hasNext());
            assertTrue(it.hasNext());
            assertTrue(it.hasNext());

            // consuma tutti gli elementi
            while (it.hasNext()) {
                it.next();
            }

            assertFalse(it.hasNext());
            assertFalse(it.hasNext());
            assertFalse(it.hasNext());
        }

        /**
         * <p>
         * <b>Summary</b> Verifica che l'iteratore gestisca correttamente elementi
         * duplicati, restituendoli tutti nell'ordine corretto.
         * </p>
         * <p>
         * <b>Test Case Design</b> Classe di equivalenza degli elementi duplicati.
         * L'iteratore non deve filtrare, comprimere o alterare la molteplicità
         * degli elementi. Si costruisce la sequenza [A, A, B, B, C] per verificare
         * che ciascuna occorrenza venga restituita in ordine e che la size
         * della lista rimanga invariata dopo l'intera iterazione. Complementa
         * testRemoveAlternatingElements che usa duplicati impliciti (A,B,C,D,E).
         * </p>
         * <p>
         * <b>Test Description</b> Si costruisce la lista [A, A, B, B, C] e si
         * percorre con l'iteratore controllando che ogni elemento sia restituito
         * nell'ordine e che il numero di elementi coincida con la size.
         * </p>
         * <p>
         * <b>Pre-Condition</b> list = [A, A, B, B, C].
         * </p>
         * <p>
         * <b>Post-Condition</b> list invariata.
         * </p>
         * <p>
         * <b>Expected Results</b> L'iteratore restituisce A, A, B, B, C in sequenza.
         * </p>
         */
        @Test
        public void testIterationWithDuplicates() {
            list.add(A);
            list.add(A);
            list.add(B);
            list.add(B);
            list.add(C);
            it = list.iterator();

            assertEquals(A, it.next());
            assertEquals(A, it.next());
            assertEquals(B, it.next());
            assertEquals(B, it.next());
            assertEquals(C, it.next());
            assertFalse(it.hasNext());
            assertEquals(5, list.size());
        }

        /**
         * <p>
         * <b>Summary</b> Verifica che l'iteratore gestisca correttamente elementi
         * null, restituendoli come valori legittimi.
         * </p>
         * <p>
         * <b>Test Case Design</b> Classe di equivalenza dell'elemento {@code null}.
         * L'iteratore deve restituire {@code null} come valore legittimo e
         * deve permetterne la rimozione tramite {@code remove()}. Si usano due
         * occorrenze di {@code null} alternate a elementi non null per verificare
         * la corretta propagazione del valore nullo sia nella restituzione sia
         * nella rimozione selettiva. Complementa testRemoveAllElements (rimozione
         * completa) e testRemoveAlternatingElements (rimozione selettiva su
         * elementi non null).
         * </p>
         * <p>
         * <b>Test Description</b> Si costruisce la lista [null, A, null, B] e si
         * percorre con l'iteratore controllando che i null vengano restituiti
         * e che sia possibile rimuoverli.
         * </p>
         * <p>
         * <b>Pre-Condition</b> list = [null, A, null, B].
         * </p>
         * <p>
         * <b>Post-Condition</b> list = [A, B] dopo la rimozione dei null.
         * </p>
         * <p>
         * <b>Expected Results</b> L'iteratore restituisce null, A, null, B; la
         * rimozione dei null lascia [A, B].
         * </p>
         */
        @Test
        public void testIterationWithNullElements() {
            list.add(null);
            list.add(A);
            list.add(null);
            list.add(B);
            it = list.iterator();

            assertNull(it.next());
            assertEquals(A, it.next());
            assertNull(it.next());
            assertEquals(B, it.next());
            assertFalse(it.hasNext());

            // Rimuovi i null iterativamente
            it = list.iterator();
            while (it.hasNext()) {
                if (it.next() == null) {
                    it.remove();
                }
            }
            assertEquals(2, list.size());
            assertEquals(A, list.get(0));
            assertEquals(B, list.get(1));
        }

        // ====================================================================
        // B) Iterazione su lista vuota
        // ====================================================================

        /**
         * <p>
         * <b>Summary</b> Verifica che su lista vuota {@code hasNext()} sia false e
         * {@code next()} lanci {@link NoSuchElementException}.
         * </p>
         * <p>
         * <b>Test Case Design</b> Caso limite di lista vuota: l'iteratore non ha
         * elementi da restituire e {@code hasNext()} deve restituire {@code false}
         * fin dalla prima chiamata. Un ulteriore {@code next()} deve lanciare
         * {@link NoSuchElementException} come da contratto
         * {@link java.util.Iterator}. La size della lista rimane 0 dopo
         * l'operazione. Complementa testIterationOverNonEmptyList (boundary non
         * vuota) e testRemoveOnSingleElementList (boundary singolo).
         * </p>
         * <p>
         * <b>Test Description</b> Su una lista appena creata (vuota), si controlla
         * che {@code hasNext()} sia false e che {@code next()} lanci
         * {@link NoSuchElementException} senza modificare la lista.
         * </p>
         * <p>
         * <b>Pre-Condition</b> list = [], it = list.iterator().
         * </p>
         * <p>
         * <b>Post-Condition</b> list invariata, iteratore esaurito.
         * </p>
         * <p>
         * <b>Expected Results</b> hasNext() false; next() lancia NoSuchElementException.
         * </p>
         */
        @Test
        public void testIteratorOnEmptyList() {
            assertFalse(it.hasNext());
            try {
                it.next();
                fail("next() su lista vuota deve lanciare NoSuchElementException");
            } catch (NoSuchElementException e) { /* atteso */ }
            assertEquals(0, list.size());
        }

        // ====================================================================
        // C) Rimozione
        // ====================================================================

        /**
         * <p>
         * <b>Summary</b> Verifica che {@code remove()} dopo un {@code next()} rimuova
         * correttamente l'ultimo elemento restituito.
         * </p>
         * <p>
         * <b>Test Case Design</b> Caso normale di rimozione dell'elemento corrente.
         * Dopo un {@code next()}, {@code remove()} deve eliminare quell'elemento
         * dalla lista backing e ridurre la size di 1. Il cursore deve avanzare
         * correttamente in modo che il successivo {@code next()} restituisca
         * l'elemento immediatamente successivo a quello rimosso. Si verifica
         * anche che {@code hasNext()} rimanga {@code true} dopo la rimozione
         * di un elemento non finale. Complementa testRemoveLastElement (rimozione
         * ultimo) e testRemoveWithoutNext (stato illegale).
         * </p>
         * <p>
         * <b>Test Description</b> Su lista [A, B, C], si chiama next() per ottenere
         * il primo elemento (A), poi remove(). Si verifica che la lista sia diventata
         * [B, C] e che l'iteratore sia ancora in uno stato valido (hasNext() true,
         * prossimo next() restituisce B).
         * </p>
         * <p>
         * <b>Pre-Condition</b> list = [A, B, C], it = list.iterator().
         * </p>
         * <p>
         * <b>Post-Condition</b> list = [B, C]; iteratore posizionato sul nuovo
         * elemento in testa (B).
         * </p>
         * <p>
         * <b>Expected Results</b> list.size() == 2, list.get(0) == B, it.next() == B.
         * </p>
         */
        @Test
        public void testRemoveAfterNext() {
            populateThree();
            assertEquals(A, it.next());
            it.remove();

            assertEquals(2, list.size());
            assertEquals(B, list.get(0));
            assertTrue(it.hasNext());
            assertEquals(B, it.next());
        }

        /**
         * <p>
         * <b>Summary</b> Rimozione di un elemento centrale e prosecuzione dell'iterazione
         * per verificare la consistenza.
         * </p>
         * <p>
         * <b>Test Case Design</b> Rimozione di un elemento centrale (B in posizione 1)
         * su lista di 5 elementi, seguita dalla prosecuzione dell'iterazione fino
         * alla fine. Questo scenario verifica che il cursore si aggiusti
         * correttamente dopo la rimozione intermedia e che gli elementi successivi
         * (C, D, E) siano ancora accessibili nell'ordine corretto. Complementa
         * testRemoveAfterNext (rimozione testa) e testRemoveAllElements
         * (rimozione completa).
         * </p>
         * <p>
         * <b>Test Description</b> Su lista [A, B, C, D, E], si itera fino a B,
         * si rimuove B, poi si prosegue con next() fino alla fine. La lista finale
         * deve essere [A, C, D, E] e gli elementi estratti dopo la rimozione devono
         * essere C, D, E in sequenza.
         * </p>
         * <p>
         * <b>Pre-Condition</b> list = [A, B, C, D, E], it = list.iterator().
         * </p>
         * <p>
         * <b>Post-Condition</b> list = [A, C, D, E]; iteratore esaurito.
         * </p>
         * <p>
         * <b>Expected Results</b> Dopo la rimozione, l'iteratore restituisce C, D, E
         * in ordine; la lista finale è [A, C, D, E].
         * </p>
         */
        @Test
        public void testRemoveAndContinueIteration() {
            populateFive();
            assertEquals(A, it.next());
            assertEquals(B, it.next()); // ora siamo su B
            it.remove();               // rimuove B

            // Verifica stato intermedio
            assertEquals(4, list.size());
            assertEquals(A, list.get(0));
            assertEquals(C, list.get(1));

            // Continua l'iterazione
            assertTrue(it.hasNext());
            assertEquals(C, it.next());
            assertEquals(D, it.next());
            assertEquals(E, it.next());
            assertFalse(it.hasNext());

            // Verifica finale
            assertEquals(4, list.size());
            assertEquals(A, list.get(0));
            assertEquals(C, list.get(1));
            assertEquals(D, list.get(2));
            assertEquals(E, list.get(3));
        }

        /**
         * <p>
         * <b>Summary</b> Rimozione dell'ultimo elemento e verifica che l'iteratore
         * termini correttamente.
         * </p>
         * <p>
         * <b>Test Case Design</b> Caso limite di rimozione dell'ultimo elemento:
         * dopo la rimozione, l'iteratore deve risultare esaurito e {@code hasNext()}
         * deve restituire {@code false}. Un ulteriore {@code next()} deve lanciare
         * {@link NoSuchElementException}. Si verifica anche che la lista contenente
         * gli elementi rimanenti [A, B] sia corretta. Complementa
         * testRemoveAfterNext (rimozione non finale) e
         * testRemoveOnSingleElementList (lista singola).
         * </p>
         * <p>
         * <b>Test Description</b> Su lista [A, B, C], si itera fino all'ultimo
         * elemento C, si chiama remove(), e si verifica che hasNext() sia false,
         * che un ulteriore next() lanci NoSuchElementException e che la lista
         * sia diventata [A, B].
         * </p>
         * <p>
         * <b>Pre-Condition</b> list = [A, B, C], it = list.iterator().
         * </p>
         * <p>
         * <b>Post-Condition</b> list = [A, B]; iteratore esaurito.
         * </p>
         * <p>
         * <b>Expected Results</b> list.size() == 2; hasNext() false; next() lancia
         * NoSuchElementException.
         * </p>
         */
        @Test
        public void testRemoveLastElement() {
            populateThree();
            assertEquals(A, it.next());
            assertEquals(B, it.next());
            assertEquals(C, it.next());
            it.remove();

            assertEquals(2, list.size());
            assertEquals(A, list.get(0));
            assertEquals(B, list.get(1));
            assertFalse(it.hasNext());
            try {
                it.next();
                fail("next() dopo la rimozione dell'ultimo elemento deve lanciare NoSuchElementException");
            } catch (NoSuchElementException e) { /* atteso */ }
        }

        /**
         * <p>
         * <b>Summary</b> Rimozione di tutti gli elementi tramite iteratore e
         * verifica che la lista risulti vuota.
         * </p>
         * <p>
         * <b>Test Case Design</b> Rimozione completa di tutti gli elementi tramite
         * il pattern standard {@code while(hasNext()) { next(); remove(); }}. Si
         * verifica che la lista risulti vuota (size 0, isEmpty true) e che
         * l'iteratore sia esaurito. Un ulteriore {@code next()} dopo lo
         * svuotamento deve lanciare {@link NoSuchElementException}. Complementa
         * testRemoveAlternatingElements (rimozione parziale) e
         * testIteratorAfterRemoveAll (verifica stato vuoto con nuovo iteratore).
         * </p>
         * <p>
         * <b>Test Description</b> Su lista [A, B, C, D, E], si esegue
         * while (it.hasNext()) { it.next(); it.remove(); }. Al termine, la lista
         * deve essere vuota (size 0) e l'iteratore deve avere hasNext() false.
         * </p>
         * <p>
         * <b>Pre-Condition</b> list = [A, B, C, D, E], it = list.iterator().
         * </p>
         * <p>
         * <b>Post-Condition</b> list = []; iteratore esaurito.
         * </p>
         * <p>
         * <b>Expected Results</b> list.isEmpty() true; it.hasNext() false.
         * </p>
         */
        @Test
        public void testRemoveAllElements() {
            populateFive();
            while (it.hasNext()) {
                it.next();
                it.remove();
            }
            assertTrue(list.isEmpty());
            assertEquals(0, list.size());
            assertFalse(it.hasNext());
            try {
                it.next();
                fail("next() su lista vuota e iteratore esaurito deve lanciare NoSuchElementException");
            } catch (NoSuchElementException e) { /* atteso */ }
        }

        /**
         * <p>
         * <b>Summary</b> Verifica che l'iteratore mantenga consistenza quando si
         * rimuovono elementi in modo alternato (es. primo, terzo, quinto).
         * </p>
         * <p>
         * <b>Test Case Design</b> Rimozione selettiva di elementi in posizioni
         * pari (0, 2, 4) su lista di 5 elementi, lasciando quelli in posizioni
         * dispari (B, D). Lo scenario intermedio verifica che il cursore si
         * aggiusti correttamente a ogni rimozione e che l'iterazione prosegua
         * senza interruzioni. Si conferma la size finale e gli elementi
         * residui. Complementa testRemoveAllElements (rimozione completa) e
         * testRemoveAfterNext (rimozione singola).
         * </p>
         * <p>
         * <b>Test Description</b> Su lista [A, B, C, D, E], si scorre con l'iteratore
         * e si rimuovono gli elementi in posizione dispari (A, C, E) utilizzando
         * un contatore. La lista finale deve essere [B, D].
         * </p>
         * <p>
         * <b>Pre-Condition</b> list = [A, B, C, D, E].
         * </p>
         * <p>
         * <b>Post-Condition</b> list = [B, D].
         * </p>
         * <p>
         * <b>Expected Results</b> list.size() == 2; elementi residui B e D.
         * </p>
         */
        @Test
        public void testRemoveAlternatingElements() {
            populateFive();
            int index = 0;
            while (it.hasNext()) {
                it.next();
                if (index % 2 == 0) { // rimuovi posizioni 0, 2, 4
                    it.remove();
                }
                index++;
            }
            assertEquals(2, list.size());
            assertEquals(B, list.get(0));
            assertEquals(D, list.get(1));
        }

        /**
         * <p>
         * <b>Summary</b> Rimozione dell'unico elemento di una lista e verifica
         * dello stato finale dell'iteratore.
         * </p>
         * <p>
         * <b>Test Case Design</b> Caso di lista con un solo elemento: l'iteratore
         * deve consentire la rimozione dell'unico elemento tramite
         * {@code remove()} e poi risultare esaurito. La lista deve diventare
         * vuota (size 0, isEmpty true) e {@code hasNext()} deve restituire
         * {@code false}. Un ulteriore {@code next()} deve lanciare
         * {@link NoSuchElementException}. Complementa testIteratorOnEmptyList
         * (lista già vuota) e testRemoveLastElement (ultimo di lista multipla).
         * </p>
         * <p>
         * <b>Test Description</b> Su lista [A], si chiama next() per ottenere A,
         * poi remove() per eliminarlo. La lista deve diventare vuota e l'iteratore
         * deve avere hasNext() false.
         * </p>
         * <p>
         * <b>Pre-Condition</b> list = [A], it = list.iterator().
         * </p>
         * <p>
         * <b>Post-Condition</b> list = []; iteratore esaurito.
         * </p>
         * <p>
         * <b>Expected Results</b> list.isEmpty() true; it.hasNext() false.
         * </p>
         */
        @Test
        public void testRemoveOnSingleElementList() {
            list.add(A);
            it = list.iterator();
            assertTrue(it.hasNext());
            assertEquals(A, it.next());
            it.remove();
            assertTrue(list.isEmpty());
            assertFalse(it.hasNext());
            try {
                it.next();
                fail("next() dopo la rimozione dell'unico elemento deve lanciare NoSuchElementException");
            } catch (NoSuchElementException e) { /* atteso */ }
        }

        /**
         * <p>
         * <b>Summary</b> Verifica che, dopo aver svuotato la lista tramite iteratore,
         * un nuovo iteratore sulla stessa lista veda correttamente lo stato vuoto.
         * </p>
         * <p>
         * <b>Test Case Design</b> Riuso dell'iteratore su una lista modificata:
         * dopo aver svuotato la lista tramite iteratore, un nuovo iteratore
         * ottenuto con {@code list.iterator()} deve comportarsi come su lista
         * vuota, con {@code hasNext()} {@code false} e {@code next()} che
         * lancia {@link NoSuchElementException}. Questo verifica che il nuovo
         * iteratore legga la dimensione corrente della lista e non lo stato
         * interno del precedente. Complementa testRemoveAllElements (svuotamento)
         * e testIteratorOnEmptyList (lista inizialmente vuota).
         * </p>
         * <p>
         * <b>Test Description</b> Si svuota la lista [A, B, C] tramite iteratore,
         * poi si crea un nuovo iteratore e si verifica che hasNext() sia false.
         * </p>
         * <p>
         * <b>Pre-Condition</b> list = [A, B, C], it = list.iterator().
         * </p>
         * <p>
         * <b>Post-Condition</b> list = []; nuovo iteratore esaurito.
         * </p>
         * <p>
         * <b>Expected Results</b> Il nuovo iteratore ha hasNext() false e next()
         * lancia NoSuchElementException.
         * </p>
         */
        @Test
        public void testIteratorAfterRemoveAll() {
            populateThree();
            while (it.hasNext()) {
                it.next();
                it.remove();
            }
            assertTrue(list.isEmpty());

            HIterator newIt = list.iterator();
            assertFalse(newIt.hasNext());
            try {
                newIt.next();
                fail("next() su lista vuota deve lanciare NoSuchElementException");
            } catch (NoSuchElementException e) { /* atteso */ }
        }

        // ====================================================================
        // D) Rimozione – eccezioni e stati illegali
        // ====================================================================

        /**
         * <p>
         * <b>Summary</b> Chiamata a {@code remove()} senza aver chiamato {@code next()}
         * deve lanciare {@link IllegalStateException}.
         * </p>
         * <p>
         * <b>Test Case Design</b> Stato illegale: l'iteratore è appena stato creato
         * e non ha ancora restituito alcun elemento tramite {@code next()}.
         * {@code remove()} non ha senso in questo stato e deve lanciare
         * {@link IllegalStateException}. La lista deve rimanere invariata
         * (size 3) e l'iteratore deve restare nella posizione iniziale.
         * Complementa testRemoveTwiceWithoutNext (secondo remove) e
         * testRemoveAfterNext (remove valido).
         * </p>
         * <p>
         * <b>Test Description</b> Su una lista popolata, si invoca remove() sull'iteratore
         * senza alcun next() precedente. L'eccezione deve essere lanciata e la lista
         * non deve subire modifiche.
         * </p>
         * <p>
         * <b>Pre-Condition</b> list = [A, B, C], it = list.iterator().
         * </p>
         * <p>
         * <b>Post-Condition</b> list invariata [A, B, C]; iteratore ancora nella
         * posizione iniziale (nessun consumo).
         * </p>
         * <p>
         * <b>Expected Results</b> IllegalStateException; list.size() == 3.
         * </p>
         */
        @Test
        public void testRemoveWithoutNext() {
            populateThree();
            try {
                it.remove();
                fail("remove() senza next() deve lanciare IllegalStateException");
            } catch (IllegalStateException e) { /* atteso */ }
            assertEquals(3, list.size());
            assertEquals(A, list.get(0));
        }

        /**
         * <p>
         * <b>Summary</b> Chiamare {@code remove()} due volte dopo un solo {@code next()}
         * deve lanciare {@link IllegalStateException}.
         * </p>
         * <p>
         * <b>Test Case Design</b> Stato illegale: dopo un {@code remove()} valido,
         * l'ultimo elemento restituito non è più valido per una seconda
         * {@code remove()} senza un nuovo {@code next()} intermedio. La seconda
         * chiamata deve lanciare {@link IllegalStateException}. La lista deve
         * riflettere la prima rimozione (size 2, [B, C]) ma non subire ulteriori
         * modifiche. Complementa testRemoveWithoutNext (primo remove illegale)
         * e testRemoveAfterNext (remove valido dopo next).
         * </p>
         * <p>
         * <b>Test Description</b> Su lista [A, B, C], si chiama next() per ottenere A,
         * poi remove() per rimuoverlo. Si tenta un secondo remove() senza un nuovo
         * next(): deve lanciare IllegalStateException.
         * </p>
         * <p>
         * <b>Pre-Condition</b> list = [A, B, C], it = list.iterator().
         * </p>
         * <p>
         * <b>Post-Condition</b> list = [B, C]; iteratore posizionato sul nuovo primo
         * elemento (B).
         * </p>
         * <p>
         * <b>Expected Results</b> IllegalStateException alla seconda remove;
         * list.size() == 2 dopo la prima rimozione.
         * </p>
         */
        @Test
        public void testRemoveTwiceWithoutNext() {
            populateThree();
            assertEquals(A, it.next());
            it.remove(); // rimuove A
            try {
                it.remove();
                fail("secondo remove() senza next() deve lanciare IllegalStateException");
            } catch (IllegalStateException e) { /* atteso */ }
            assertEquals(2, list.size());
            assertEquals(B, list.get(0));
        }

        /**
         * <p>
         * <b>Summary</b> Modifica della lista direttamente (add/remove) dopo la
         * creazione dell'iteratore e successiva chiamata di {@code remove()}
         * (comportamento indefinito, il test controlla solo l'assenza di eccezioni).
         * </p>
         * <p>
         * <b>Test Case Design</b> Il contratto {@link java.util.Iterator} non
         * definisce il comportamento se la lista viene modificata direttamente
         * (non tramite l'iteratore) durante l'iterazione. In questa
         * implementazione, che non include meccanismi di fail-fast, la modifica
         * esterna è tecnicamente possibile ma il risultato è indefinito. Il test
         * non fa asserzioni sul contenuto finale, ma verifica che
         * {@code next()} e {@code remove()} non generino eccezioni inaspettate
         * nell'unità di prova.
         * </p>
         * <p>
         * <b>Test Description</b> Si crea un iteratore su [A, B, C]. Si aggiunge
         * un elemento X alla lista direttamente (list.add(X)), poi si chiama next()
         * e remove() sull'iteratore. Si verifica solo che non venga lanciata alcuna
         * eccezione (il comportamento effettivo può variare).
         * </p>
         * <p>
         * <b>Pre-Condition</b> list = [A, B, C], it = list.iterator().
         * </p>
         * <p>
         * <b>Post-Condition</b> list modificata (contenuto indefinito), ma il test
         * non fa asserzioni sul contenuto finale.
         * </p>
         * <p>
         * <b>Expected Results</b> Nessuna eccezione sollevata.
         * </p>
         */
        @Test
        public void testRemoveAfterModificationByList() {
            populateThree();
            list.add(X); // modifica strutturale della lista di backing
                         // il comportamento è indefinito; ci limitiamo a verificare che non vengano lanciate eccezioni
            try {
                assertEquals(A, it.next());
                it.remove();
                // Non facciamo asserzioni sul contenuto finale
            } catch (Exception e) {
                fail("Non dovrebbero essere lanciate eccezioni durante la modifica esterna + iterazione: " + e);
            }
        }
    }
