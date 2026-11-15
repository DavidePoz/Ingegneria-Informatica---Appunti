package myTest;

import static org.junit.Assert.*;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Enumeration;
import java.util.NoSuchElementException;

/**
 * <p>
 * <b>Summary</b> Verifica il corretto funzionamento di {@link VectorAdapter},
 * adapter J2SE 1.4.2 per {@code java.util.Vector} basato su {@code Vector} CLDC 1.1
 * tramite {@link AbstractHList}. <br>
 * La suite copre i 4 costruttori, i metodi specificati dalle interfacce
 * HCollection ed HList, ereditati da {@link AbstractHList}; le API di
 * capacità e accesso di {@code Vector} richieste da J2SE 1.4.2,
 * la ricerca indicizzata a 2 argomenti,
 * {@code clone/toString/removeRange} e la gestione di {@code null}/duplicati.
 * <br>
 * Per eseguire questa test suite: {@code java -cp "bin:JUnit/*" org.junit.runner.JUnitCore myTest.VectorAdapterTest}
 * </p>
 * <p>
 * <b>Test Case Design</b> La suite organizza i test in 6 categorie:
 * <ul>
 * <li>A) Costruttori (12 test)</li>
 * <li>B) Primitivi e accesso posizionale (16 test)</li>
 * <li>C) Operazioni bulk (27 test)</li>
 * <li>D) Ricerca ed eguaglianza (19 test)</li>
 * <li>E) Metodi Vector (28 test)</li>
 * <li>F) Altro - casistiche rimanenti (13 test)</li>
 * </ul>
 * <br>
 * Ogni test isola il VectorAdapter con {@link #setUp()} che crea un nuovo
 * {@code VectorAdapter} vuoto; i test con dati usano un metodo di supporto {@link #populateThree()}
 * che inserisce {@code [E1="elem1", E2="elem2", E3="elem3"]} (size=3, con 
 * capacity=10 di default) nel vettore creato. 
 * I criteri di valutazione usano {@code assert}
 * JUnit e verifica di eccezioni attese (come {@code IndexOutOfBoundsException},
 * {@code ArrayIndexOutOfBoundsException}, {@code NoSuchElementException},
 * {@code NullPointerException}, {@code IllegalArgumentException}). <br>
 * Per ogni test method la documentazione dettagliata riporta i valori concreti delle
 * variabili (es. {@code v=[elem1,elem2,elem3]}, {@code index=1}, {@code a.length=1}). <br>
 * <b>Versione JUnit:</b> Questa test suite è stata scritta utilizzando il framework JUnit 4.13.
 * <p>
 * <b>Test Cases</b>
 * </p>
 * <p>
 * <b>A) Costruttori (12 test)</b>
 * </p>
 * <ul>
 * <li>{@link #testEmptyConstructor()} - Verifica che il costruttore di default {@link VectorAdapter#VectorAdapter()} 
 * crei un vettore vuoto con size 0, isEmpty true e capacity 10 di default.</li>
 * <li>{@link #testConstructorWithInitialCapacity()} - Verifica che il costruttore {@link VectorAdapter#VectorAdapter(int)} 
 * crei un vettore vuoto con la capacità iniziale richiesta.</li>
 * <li>{@link #testConstructorWithNegativeCapacityThrowsIAE()} - Verifica che il costruttore {@link VectorAdapter#VectorAdapter(int)} 
 * lanci {@link IllegalArgumentException} se la capacità iniziale e negativa.</li>
 * <li>{@link #testTwoArgConstructorNegativeCapacityThrowsIAE()} - Verifica che il costruttore {@link VectorAdapter#VectorAdapter(int,int)} 
 * lanci {@link IllegalArgumentException} se la capacità iniziale e negativa.</li>
 * <li>{@link #testConstructorCapacityZeroBoundary()} - Verifica che il costruttore {@link VectorAdapter#VectorAdapter(int)} 
 * con capacità zero crei un vettore vuoto con capacity 0 e gestione limite.</li>
 * <li>{@link #testConstructorWithCapacityAndIncrement()} - Verifica che il costruttore {@link VectorAdapter#VectorAdapter(int,int)} crei 
 * un vettore vuoto con capacità e incremento specificati e che la crescita rispetti l'incremento.</li>
 * <li>{@link #testConstructorIncrementZeroBoundary()} - Verifica che il costruttore {@link VectorAdapter#VectorAdapter(int,int)} 
 * con incremento zero gestisca correttamente la crescita per raddoppio.</li>
 * <li>{@link #testConstructorFromCollection()} - Verifica che il costruttore {@link VectorAdapter#VectorAdapter(HCollection)} copi 
 * correttamente tutti gli elementi della collezione sorgente nell'ordine dell'iteratore.</li>
 * <li>{@link #testConstructorFromNullCollectionThrowsNPE()} - Verifica che il costruttore {@link VectorAdapter#VectorAdapter(HCollection)} 
 * lanci {@link NullPointerException} se la collezione sorgente e null.</li>
 * <li>{@link #testConstructorFromEmptyCollectionBoundary()} - Verifica che il costruttore {@link VectorAdapter#VectorAdapter(HCollection)} 
 * con collezione vuota crei un vettore vuoto di size 0.</li>
 * <li>{@link #testConstructorFromSingleElementCollectionBoundary()} - Verifica che il costruttore {@link VectorAdapter#VectorAdapter(HCollection)}
 * con collezione singola crei un vettore di size 1 con elemento corretto.</li>
 * <li>{@link #testConstructorFromCollectionIndependenceAndRepeated()} - Verifica che il costruttore {@link VectorAdapter#VectorAdapter(HCollection)} 
 * crei una copia indipendente e che modifiche ripetute non propaghino.</li>
 * </ul>
<p>
 * <b>B) Primitivi e accesso posizionale (16 test)</b>
 * </p>
 * <ul>
 * <li>{@link #testGet()} - Verifica che {@link HList#get(int)} restituisca l'elemento corretto per indici validi e lanci 
 * {@link IndexOutOfBoundsException} per indici fuori intervallo.</li>
 * <li>{@link #testGetOnEmptyListThrowsIOOBE()} - Verifica che {@link HList#get(int)} su vettore vuoto lanci {@link IndexOutOfBoundsException} 
 * per qualsiasi indice.</li>
 * <li>{@link #testGetSingleElementBoundaries()} - Verifica che {@link HList#get(int)} su vettore singolo gestisca 
 * correttamente gli indici di bordo 0 e 1.</li>
 * <li>{@link #testGetRepeatedSequential()} - Verifica che chiamate ripetute a {@link HList#get(int)} e 
 * alternate con {@link HList#set(int,Object)} siano coerenti.</li>
 * <li>{@link #testSet()} - Verifica che {@link HList#set(int,Object)} sostituisca l'elemento all'indice specificato, restituisca 
 * il vecchio valore e lanci {@link IndexOutOfBoundsException} per indici non validi.</li>
 * <li>{@link #testSetBoundariesAndSingleElement()} - Verifica che {@link HList#set(int,Object)} su vettore singolo 
 * e sui bordi di un vettore di size 3 funzioni correttamente.</li>
 * <li>{@link #testSetRepeatedOverwrites()} - Verifica che chiamate ripetute a {@link HList#set(int,Object)} 
 * sullo stesso indice sovrascrivano correttamente.</li>
 * <li>{@link #testAddIndex()} - Verifica che {@link HList#add(int,Object)} inserisca l'elemento alla posizione specificata shiftando i 
 * successivi e lanci {@link IndexOutOfBoundsException} per indici non validi.</li>
 * <li>{@link #testAddAtIndexBoundaryZeroAndSize()} - Verifica che {@link HList#add(int,Object)} con indice 0 
 * e con indice uguale a size inserisca correttamente come casi limite.</li>
 * <li>{@link #testAddAtIndexThrowsIOOBE()} - Verifica che {@link HList#add(int,Object)} lanci 
 * {@link IndexOutOfBoundsException} per indici negativi e oltre size.</li>
 * <li>{@link #testAddAllIndex()} - Verifica che {@link HList#addAll(int,HCollection)} inserisca la collezione alla posizione specificata e 
 * lanci {@link IndexOutOfBoundsException} per indici non validi.</li>
 * <li>{@link #testAddAllAtIndexWithEmptyCollection()} - Verifica che {@link HList#addAll(int,HCollection)} con collezione vuota
 * restituisca false e non modifichi il vettore.</li>
 * <li>{@link #testRemoveIndex()} - Verifica che {@link HList#remove(int)} rimuova e restituisca l'elemento all'indice specificato shiftando 
 * i successivi e lanci {@link IndexOutOfBoundsException} per indici non validi.</li>
 * <li>{@link #testRemoveAtIndexSingleElementAndEmpty()} - Verifica che {@link HList#remove(int)} su vettore singolo 
 * e su vettore vuoto gestisca correttamente i limiti.</li>
 * <li>{@link #testRemoveAtIndexRepeatedUntilEmpty()} - Verifica che chiamate ripetute a {@link HList#remove(int)} 
 * fino a svuotare il vettore funzionino correttamente.</li>
 * <li>{@link #testAddNullAndDuplicate()} - Verifica che {@link HList#add(int,Object)} e {@link HCollection#add(Object)} supportino correttamente 
 * elementi null e duplicati.</li>
 * </ul>
<p>
 * <b>C) Operazioni bulk (27 test)</b>
 * </p>
 * <ul>
 * <li>{@link #testSizeAndIsEmpty()} - Verifica che {@link HCollection#size()} e {@link HCollection#isEmpty()} riflettano correttamente 
 * lo stato vuoto e popolato del vettore.</li>
 * <li>{@link #testSizeIsEmptyOnEmptyList()} - Verifica che {@link HCollection#size()} e {@link HCollection#isEmpty()} 
 * su vettore vuoto restituiscano 0 e true come caso limite.</li>
 * <li>{@link #testSizeIsEmptyRepeated()} - Verifica che chiamate ripetute a {@link HCollection#size()} e {@link HCollection#isEmpty()} 
 * dopo sequenze di add e remove siano coerenti.</li>
 * <li>{@link #testContains()} - Verifica che {@link HCollection#contains(Object)} restituisca true per elementi presenti, false per assenti e 
 * gestisca correttamente null.</li>
 * <li>{@link #testContainsOnEmptyList()} - Verifica che {@link HCollection#contains(Object)} su vettore 
 * vuoto restituisca false anche per null.</li>
 * <li>{@link #testContainsAll()} - Verifica che {@link HCollection#containsAll(HCollection)} restituisca true per sottoinsiemi presenti, false 
 * altrimenti e lanci {@link NullPointerException} per argomento null.</li>
 * <li>{@link #testContainsAllOnEmptyListVacuous()} - Verifica che {@link HCollection#containsAll(HCollection)} 
 * con collezione vuota restituisca true per vacuità e su vuoto.</li>
 * <li>{@link #testAddObject()} - Verifica che {@link HCollection#add(Object)} aggiunga l'elemento in coda e restituisca true.</li>
 * <li>{@link #testAddObjectOnEmptyList()} - Verifica che {@link HCollection#add(Object)} su 
 * vettore vuoto aggiunga correttamente come caso limite.</li>
 * <li>{@link #testAddObjectRepeatedSequential()} - Verifica che chiamate ripetute a {@link HCollection#add(Object)} 
 * aggiungano sequenzialmente in coda.</li>
 * <li>{@link #testAddAll()} - Verifica che {@link HCollection#addAll(HCollection)} aggiunga tutti gli elementi in coda nell'ordine dell'iteratore 
* e restituisca true se modificato, false se la collezione e vuota.</li>
 * <li>{@link #testAddAllWithEmptyCollectionNoOp()} - Verifica che {@link HCollection#addAll(HCollection)} 
 * con collezione vuota restituisca false e non modifichi come caso limite.</li>
 * <li>{@link #testAddAllRepeatedSequential()} - Verifica che chiamate ripetute a {@link HCollection#addAll(HCollection)} 
 * accumulino correttamente gli elementi.</li>
 * <li>{@link #testAddAllNullThrowsNPE()} - Verifica che {@link HCollection#addAll(HCollection)} e {@link HList#addAll(int,HCollection)} 
 * lancino {@link NullPointerException} se la collezione e null.</li>
 * <li>{@link #testRemoveObject()} - Verifica che {@link HCollection#remove(Object)} rimuova la prima occorrenza, restituisca true se presente, 
 * false altrimenti e gestisca null.</li>
 * <li>{@link #testRemoveAll()} - Verifica che {@link HCollection#removeAll(HCollection)} rimuova tutti gli elementi contenuti nella collezione 
 * specificata e restituisca true se il vettore e stato modificato.</li>
 * <li>{@link #testRemoveAllOnEmptyList()} - Verifica che {@link HCollection#removeAll(HCollection)} con vettore vuoto 
 * o collezione vuota gestisca correttamente i limiti.</li>
 * <li>{@link #testRemoveAllDuplicatesInVector()} - Verifica che {@link HCollection#removeAll(HCollection)} rimuova tutte le occorrenze di
 * un elemento duplicato nel vettore, non solo la prima.</li>
 * <li>{@link #testRemoveAllViaSelfReferentialClone()} - Verifica che {@link HCollection#removeAll(HCollection)} invocata con una copia clonata
 * del vettore stesso svuoti completamente il vettore senza corromperne lo stato.</li>
 * <li>{@link #testRetainAll()} - Verifica che {@link HCollection#retainAll(HCollection)} conservi solo gli elementi contenuti nella collezione 
 * specificata e restituisca true se modificato.</li>
 * <li>{@link #testRetainAllPreservesDuplicates()} - Verifica che {@link HCollection#retainAll(HCollection)} preservi le occorrenze duplicate
 * presenti nel vettore e restituisca false se il contenuto non cambia.</li>
 * <li>{@link #testRetainAllOnEmptyListEmpties()} - Verifica che {@link HCollection#retainAll(HCollection)} con 
 * collezione vuota svuoti il vettore e su vettore vuoto resti vuoto.</li>
 * <li>{@link #testRetainAllOnSelf()} - Verifica che {@link HCollection#retainAll(HCollection)} con la collezione stessa come argomento
 * non modifichi il vettore e restituisca false.</li>
 * <li>{@link #testClear()} - Verifica che {@link HCollection#clear()} rimuova tutti gli elementi lasciando size 0 e capacity invariata.</li>
 * <li>{@link #testClearOnEmptyListIdempotent()} - Verifica che {@link HCollection#clear()} su vettore vuoto 
 * sia idempotente e mantenga capacity come caso limite.</li>
 * <li>{@link #testClearRepeatedDoubleClear()} - Verifica che chiamate ripetute a {@link HCollection#clear()} 
 * siano idempotenti e ripetibili.</li>
 * <li>{@link #testRemoveAllRetainAllNullThrowsNPE()} - Verifica che {@link HCollection#removeAll(HCollection)} e {@link HCollection#retainAll(HCollection)} 
 * lancino {@link NullPointerException} se l'argomento e null.</li>
 * </ul>
<p>
 * <b>D) Ricerca ed eguaglianza (19 test)</b>
 * </p>
 * <ul>
 * <li>{@link #testToArray()} - Verifica che {@link HCollection#toArray()} restituisca un nuovo array con gli elementi nell'ordine del 
 * vettore, sia per vettore vuoto che non vuoto.</li>
 * <li>{@link #testToArrayWithSmallerArray()} - Verifica che {@link HCollection#toArray(Object[])} allochi un nuovo array quando 
 * quello fornito e troppo piccolo.</li>
 * <li>{@link #testToArrayWithLargerArray()} - Verifica che {@link HCollection#toArray(Object[])} riutilizzi l'array fornito 
 * quando sufficientemente grande e ponga il terminatore null in posizione size.</li>
 * <li>{@link #testToArrayNullThrowsNPE()} - Verifica che {@link HCollection#toArray(Object[])} lanci {@link NullPointerException} 
 * se l'array fornito e null.</li>
 * <li>{@link #testToArrayRepeatedIndependence()} - Verifica che chiamate ripetute a {@link HCollection#toArray()} 
 * restituiscano array indipendenti.</li>
 * <li>{@link #testToArrayWithLargerArrayExactSize()} - Verifica che {@link HCollection#toArray(Object[])} 
 * con array di lunghezza esattamente size riutilizzi l'array senza terminatore null.</li>
 * <li>{@link #testIndexOfAndLastIndexOf()} - Verifica che {@link HList#indexOf(Object)} e {@link HList#lastIndexOf(Object)} restituiscano 
 * la prima e l'ultima occorrenza gestendo correttamente duplicati, assenti e null.</li>
 * <li>{@link #testIndexOfOnEmptyAndSingleElement()} - Verifica che {@link HList#indexOf(Object)} su vettore 
 * vuoto e singolo gestisca correttamente i limiti.</li>
 * <li>{@link #testLastIndexOfOnEmptyAndSingleElement()} - Verifica che {@link HList#lastIndexOf(Object)} su
 * vettore vuoto e singolo gestisca correttamente i limiti.</li>
 * <li>{@link #testIndexOfRepeatedWithDuplicates()} - Verifica che chiamate ripetute a {@link HList#indexOf(Object)} 
 * con duplicati restituiscano sempre la prima occorrenza.</li>
 * <li>{@link #testIndexOfWithIndex()} - Verifica che {@link VectorAdapter#indexOf(Object,int)} cerchi dalla posizione 
 * specificata e gestisca indici negativi e oltre size secondo la semantica J2SE 1.4.2.</li>
 * <li>{@link #testLastIndexOfWithIndex()} - Verifica che {@link VectorAdapter#lastIndexOf(Object,int)} cerchi all'indietro dalla 
 * posizione specificata e gestisca indici fuori intervallo secondo la semantica J2SE 1.4.2.</li>
 * <li>{@link #testEqualsAndHashCode()} - Verifica il contratto di {@link HList#equals(Object)} e {@link HList#hashCode()} su 
 * VectorAdapter per contenuto uguale, diverso e coerenza.</li>
 * <li>{@link #testEqualsWithNullElements()} - Verifica che {@link HList#equals(Object)} restituisca true per due vettori
 * contenenti elementi null nelle stesse posizioni.</li>
 * <li>{@link #testEqualsDifferentOrderSameSize()} - Verifica che {@link HList#equals(Object)} restituisca false per due vettori 
 * di stessa size ma ordine degli elementi diverso.</li>
 * <li>{@link #testEqualsEmptyAndNull()} - Verifica che {@link HList#equals(Object)} su vettori vuoti, 
 * con null e tipi diversi gestisca correttamente i limiti.</li>
 * <li>{@link #testHashCodeEmptyList()} - Verifica che {@link HList#hashCode()} su vettore vuoto restituisca 1 
 * e sia coerente con la formula.</li>
 * <li>{@link #testHashCodeRepeatedConsistency()} - Verifica che chiamate ripetute a {@link HList#hashCode()} siano 
 * coerenti e uguali per contenuto uguale.</li>
 * <li>{@link #testHashCodeDifferentVectors()} - Verifica che {@link HList#hashCode()} produca valori diversi per vettori con contenuto diverso.</li>
 * </ul>
<p>
 * <b>E) Metodi Vector (28 test)</b>
 * </p>
 * <ul>
 * <li>{@link #testCapacityAndEnsureCapacity()} - Verifica che {@link VectorAdapter#capacity()} restituisca la capacità corrente 
 * e che {@link VectorAdapter#ensureCapacity(int)} aumenti la capacità quando necessario.</li>
 * <li>{@link #testEnsureCapacityNoShrinkBoundary()} - Verifica che {@link VectorAdapter#ensureCapacity(int)} con 
 * minCapacity minore di capacity non riduca la capacity come caso limite.</li>
 * <li>{@link #testEnsureCapacityNegativeBoundary()} - Verifica che {@link VectorAdapter#ensureCapacity(int)} con 
 * valore negativo non modifichi la capacity come caso limite.</li>
 * <li>{@link #testTrimToSize()} - Verifica che {@link VectorAdapter#trimToSize()} riduca la capacità al valore di size.</li>
 * <li>{@link #testTrimToSizeOnEmptyList()} - Verifica che {@link VectorAdapter#trimToSize()} su vettore vuoto 
 * riduca la capacity a 0 come caso limite.</li>
 * <li>{@link #testSetSizeGrowAndShrink()} - Verifica che {@link VectorAdapter#setSize(int)} aumenti la size aggiungendo null 
 * e la riduca scartando gli elementi in coda.</li>
 * <li>{@link #testSetSizeZeroEmpties()} - Verifica che {@link VectorAdapter#setSize(int)} con 0 svuoti 
 * il vettore mantenendo la capacity.</li>
 * <li>{@link #testSetSizeNegativeThrows()} - Verifica che {@link VectorAdapter#setSize(int)} lanci {@link ArrayIndexOutOfBoundsException} 
 * se la nuova size e negativa.</li>
 * <li>{@link #testSetSizeRepeatedGrowShrinkGrow()} - Verifica che chiamate ripetute a 
 * {@link VectorAdapter#setSize(int)} con sequenza grow-shrink-grow siano coerenti.</li>
 * <li>{@link #testElementAt()} - Verifica che {@link VectorAdapter#elementAt(int)} restituisca l'elemento all'indice specificato e 
 * lanci {@link ArrayIndexOutOfBoundsException} per indici non validi.</li>
 * <li>{@link #testElementAtSingleElementAndEmpty()} - Verifica che {@link VectorAdapter#elementAt(int)} su vettore 
 * singolo e vuoto gestisca correttamente i limiti.</li>
 * <li>{@link #testFirstAndLastElement()} - Verifica che {@link VectorAdapter#firstElement()} e {@link VectorAdapter#lastElement()} 
 * restituiscano rispettivamente il primo e l'ultimo elemento e lancino {@link NoSuchElementException} su vettore vuoto.</li>
 * <li>{@link #testFirstLastElementSingleElementCoincide()} - Verifica che {@link VectorAdapter#firstElement()} 
 * e {@link VectorAdapter#lastElement()} su vettore singolo coincidano.</li>
 * <li>{@link #testSetElementAt()} - Verifica che {@link VectorAdapter#setElementAt(Object,int)} sostituisca l'elemento alla 
 * posizione specificata con parametri invertiti rispetto a set.</li>
 * <li>{@link #testInsertElementAt()} - Verifica che {@link VectorAdapter#insertElementAt(Object,int)} inserisca l'elemento prima dell'indice 
 * specificato shiftando i successivi.</li>
 * <li>{@link #testInsertElementAtThrowsBounds()} - Verifica che {@link VectorAdapter#insertElementAt(Object,int)} 
 * lanci {@link ArrayIndexOutOfBoundsException} per indici non validi.</li>
 * <li>{@link #testRemoveElementAt()} - Verifica che {@link VectorAdapter#removeElementAt(int)} rimuova l'elemento all'indice specificato.</li>
 * <li>{@link #testRemoveElementAtOnEmptyThrowsIOOBE()} - Verifica che {@link VectorAdapter#removeElementAt(int)} 
 * su vettore vuoto lanci {@link ArrayIndexOutOfBoundsException} per qualsiasi indice.</li>
 * <li>{@link #testAddElementAndRemoveElement()} - Verifica che {@link VectorAdapter#addElement(Object)} aggiunga in coda e 
 * che {@link VectorAdapter#removeElement(Object)} rimuova la prima occorrenza restituendo true o false.</li>
 * <li>{@link #testLegacyNullElements()} - Verifica che le API legacy {@link VectorAdapter#addElement(Object)}, {@link VectorAdapter#insertElementAt(Object,int)}, 
 * {@link VectorAdapter#setElementAt(Object,int)} e {@link VectorAdapter#removeElement(Object)} gestiscano correttamente gli elementi null.</li>
 * <li>{@link #testRemoveAllElements()} - Verifica che {@link VectorAdapter#removeAllElements()} rimuova tutti gli elementi 
 * lasciando il vettore vuoto.</li>
 * <li>{@link #testCopyInto()} - Verifica che {@link VectorAdapter#copyInto(Object[])} copi tutti gli elementi nell'array 
 * fornito nell'ordine corretto.</li>
 * <li>{@link #testCopyIntoNullAndSmallThrows()} - Verifica che {@link VectorAdapter#copyInto(Object[])} lanci 
 * {@link NullPointerException} per array null e {@link ArrayIndexOutOfBoundsException} per array troppo piccolo.</li>
 * <li>{@link #testCopyIntoLargerArrayTrailingIntact()} - Verifica che {@link VectorAdapter#copyInto(Object[])} 
 * con array piu grande lasci intatta la coda oltre size.</li>
 * <li>{@link #testCopyIntoEmptyList()} - Verifica che {@link VectorAdapter#copyInto(Object[])} su vettore 
 * vuoto non modifichi l'array come caso limite.</li>
 * <li>{@link #testElementsEnumeration()} - Verifica che {@link VectorAdapter#elements()} restituisca una {@link Enumeration} 
 * che genera gli elementi nell'ordine crescente.</li>
 * <li>{@link #testElementsEnumerationExhaustionThrows()} - Verifica che {@link VectorAdapter#elements()} 
 * su vettore vuoto e su enumerazione esaurita lanci {@link NoSuchElementException}.</li>
 * <li>{@link #testElementsEnumerationHasMoreIdempotent()} - Verifica che chiamate ripetute a 
 * {@link Enumeration#hasMoreElements()} siano idempotenti.</li>
 * </ul>
<p>
 * <b>F) Altro - casistiche rimanenti (13 test)</b>
 * </p>
 * <ul>
 * <li>{@link #testCloneShallowAndCapacity()} - Verifica che {@link VectorAdapter#clone()} crei una copia shallow indipendente 
 * preservando la capacity originale.</li>
 * <li>{@link #testCloneEmptyList()} - Verifica che {@link VectorAdapter#clone()} su vettore vuoto 
 * crei una copia vuota indipendente preservando la capacity.</li>
 * <li>{@link #testCloneBidirectionalIndependence()} - Verifica che modifiche ripetute al 
 * clone e all'originale dopo {@link VectorAdapter#clone()} siano bidirezionalmente indipendenti.</li>
 * <li>{@link #testToString()} - Verifica che {@link VectorAdapter#toString()} deleghi a {@code Vector.toString()} 
 * rappresentando correttamente gli elementi.</li>
 * <li>{@link #testToStringEmptyListBoundary()} - Verifica che {@link VectorAdapter#toString()} su vettore vuoto restituisca 
 * la rappresentazione {@code "[]"} come da delega a {@code Vector}.</li>
 * <li>{@link #testToStringWithCyclicReference()} - Verifica che {@link VectorAdapter#toString()} gestisca correttamente un vettore
 * che contiene se stesso come elemento, producendo {@code "(this Collection)"}.</li>
 * <li>{@link #testRemoveRangeViaClear()} - Verifica che {@code removeRange} protetto sia correttamente invocato 
 * tramite {@link HCollection#clear()} con rimozione di intervallo.</li>
 * <li>{@link #testIteratorBasic()} - Verifica che {@link HCollection#iterator()} iteri nell'ordine, supporti remove 
 * e lanci {@link NoSuchElementException} e {@link IllegalStateException} quando appropriato.</li>
 * <li>{@link #testListIteratorForwardAndBackward()} - Verifica che {@link HList#listIterator()} iteri correttamente 
 * avanti e indietro in uso normale.</li>
 * <li>{@link #testListIteratorOnEmptyList()} - Verifica che {@link HList#listIterator()} su vettore vuoto 
 * gestisca correttamente i limiti di hasNext e hasPrevious.</li>
 * <li>{@link #testListIteratorInvalidIndicesThrows()} - Verifica che {@link HList#listIterator(int)} 
 * lanci {@link IndexOutOfBoundsException} per indici non validi.</li>
 * <li>{@link #testListIteratorHasPreviousPreviousBoundary()} - Verifica che {@link HListIterator#hasPrevious()} 
 * e {@link HListIterator#previous()} gestiscano correttamente i limiti.</li>
 * <li>{@link #testListIteratorSetAddRemoveNormal()} - Verifica che {@link HListIterator#set(Object)}, 
 * {@link HListIterator#add(Object)} e {@link HListIterator#remove()} funzionino in uso normale.</li>
 * </ul>
 */
public class VectorAdapterTest {

    /**
     * Costruttore pubblico di default.
     */
    public VectorAdapterTest() {}

    private VectorAdapter v;

    private static final String E1 = "elem1";
    private static final String E2 = "elem2";
    private static final String E3 = "elem3";
    private static final String DUP = "dup";
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Inizializza un nuovo VectorAdapter vuoto prima di ogni test.
     */
    @Before
    public void setUp() {
        v = new VectorAdapter();
    }

    /**
     * Popola il vettore con tre elementi [E1, E2, E3] (size=3, capacity=10).
     */
    private void populateThree() {
        v.addElement(E1);
        v.addElement(E2);
        v.addElement(E3);
    }

    /**
     * Crea una HCollection vuota.
     *
     * @return HCollection vuota
     */
    private HCollection makeCollection() {
        return new VectorAdapter();
    }

    /**
     * Crea una HCollection con un elemento.
     *
     * @param e1 primo elemento
     * @return HCollection contenente l'elemento
     */
    private HCollection makeCollection(Object e1) {
        VectorAdapter c = new VectorAdapter();
        c.add(e1);
        return c;
    }

    /**
     * Crea una HCollection con due elementi.
     *
     * @param e1 primo elemento
     * @param e2 secondo elemento
     * @return HCollection contenente gli elementi
     */
    private HCollection makeCollection(Object e1, Object e2) {
        VectorAdapter c = new VectorAdapter();
        c.add(e1);
        c.add(e2);
        return c;
    }

    /**
     * Crea una HCollection con tre elementi.
     *
     * @param e1 primo elemento
     * @param e2 secondo elemento
     * @param e3 terzo elemento
     * @return HCollection contenente gli elementi
     */
    private HCollection makeCollection(Object e1, Object e2, Object e3) {
        VectorAdapter c = new VectorAdapter();
        c.add(e1);
        c.add(e2);
        c.add(e3);
        return c;
    }


    // ====================================================================
    // A) Costruttori (12)
    // ====================================================================

    /**
     * <p>
     * <b>Summary</b> Verifica che il costruttore di default {@link VectorAdapter#VectorAdapter()} crei un vettore vuoto 
     * con size 0, isEmpty restituisca true e capacity sia 10 (default).
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale di costruzione senza argomenti.
     * Il costruttore di default alloca l'array interno con la capacità
     * standard di Vector CLDC/J2SE (10) senza elementi: la verifica controlla
     * i tre invarianti post-costruttore size==0, isEmpty==true e
     * capacity()==DEFAULT_CAPACITY. La capacity, osservabile tramite il
     * metodo capacity(), è il dato che distingue questo overload da
     * {@link VectorAdapter#VectorAdapter(int)}.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore vuoto creato da setUp si leggono
     * size, isEmpty e capacity. Le asserzioni verificano che size valga 0,
     * isEmpty restituisca true e capacity coincida con DEFAULT_CAPACITY = 10.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v appena costruito vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> size==0, isEmpty==true, capacity()==10.
     * </p>
     */
    @Test
    public void testEmptyConstructor() {
        assertEquals("size su vuoto deve essere 0", 0, v.size());
        assertTrue("isEmpty su vuoto deve essere true", v.isEmpty());
        assertEquals("capacity default deve essere 10", DEFAULT_CAPACITY, v.capacity());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il costruttore {@link VectorAdapter#VectorAdapter(int)} crei un vettore vuoto con la capacità iniziale richiesta.
     * </p>
     * <p>
     * <b>Test Case Design</b> Partizione normale del parametro
     * initialCapacity con due valori distinti (5 e 20) della stessa classe di
     * equivalenza: la capacità dell'array interno deve coincidere esattamente
     * con il valore richiesto, senza arrotondamenti ne sostituzione con la
     * capacità default. Per ciascun valore si verifica anche size==0, a
     * conferma che initialCapacity condiziona la sola struttura fisica e non
     * lo stato logico della lista.
     * </p>
     * <p>
     * <b>Test Description</b> Si costruiscono due vettori, il primo con
     * initialCapacity = 5, il secondo con initialCapacity = 20. Per ciascuno
     * le asserzioni verificano che capacity restituisca esattamente il valore
     * richiesto (5 e 20) e che size resti 0.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Nessuna.
     * </p>
     * <p>
     * <b>Post-Condition</b> Vettori vuoti invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> capacity 5 e 20, size 0.
     * </p>
     */
    @Test
    public void testConstructorWithInitialCapacity() {
        VectorAdapter v1 = new VectorAdapter(5);
        assertEquals(5, v1.capacity());
        assertEquals(0, v1.size());
        VectorAdapter v2 = new VectorAdapter(20);
        assertEquals(20, v2.capacity());
        assertEquals(0, v2.size());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il costruttore {@link VectorAdapter#VectorAdapter(int)} lanci {@link IllegalArgumentException} se la capacità iniziale è negativa.
     * </p>
     * <p>
     * <b>Test Case Design</b> Classe di errore del parametro initialCapacity
     * per l'overload a un argomento. Il valore -1 è la minima violazione della
     * precondizione initialCapacity&gt;=0 documentata per java.util.Vector in
     * J2SE 1.4.2, che prescrive IllegalArgumentException; l'eccezione
     * è dichiarata direttamente nell'annotazione @Test expected, quindi
     * qualunque esito alternativo, inclusa la creazione dell'oggetto,
     * determina fallimento.
     * </p>
     * <p>
     * <b>Test Description</b> Si tenta di costruire un vettore passando
     * initialCapacity = -1. La costruzione deve interrompersi immediatamente:
     * il metodo non contiene altre istruzioni e l'eccezione attesa è
     * dichiarata direttamente nell'annotazione {@code @Test}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Nessuna.
     * </p>
     * <p>
     * <b>Post-Condition</b> Nessun vettore creato.
     * </p>
     * <p>
     * <b>Expected Results</b> IllegalArgumentException.
     * </p>
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNegativeCapacityThrowsIAE() {
        new VectorAdapter(-1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il costruttore {@link VectorAdapter#VectorAdapter(int,int)} lanci {@link IllegalArgumentException} se la capacità iniziale è negativa.
     * </p>
     * <p>
     * <b>Test Case Design</b> Classe di errore del parametro initialCapacity
     * per l'overload a due argomenti. Le combinazioni (-1,0), (-5,2) e
     * (-1,-1) variano capacityIncrement mantenendo invalida la capacità
     * iniziale: ciò distingue il controllo su initialCapacity, eseguito al
     * momento della costruzione, dalla validazione di capacityIncrement. 
     * In tutti i casi è attesa IllegalArgumentException.
     * </p>
     * <p>
     * <b>Test Description</b> Si tentano tre costruzioni con capacità
     * iniziale negativa e incremento variato: (-1, 0), (-5, 2) e (-1, -1).
     * Ogni invocazione è racchiusa in un blocco try/fail/catch che accerta
     * il lancio di IllegalArgumentException per tutte le combinazioni.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Nessuna.
     * </p>
     * <p>
     * <b>Post-Condition</b> Nessun vettore creato.
     * </p>
     * <p>
     * <b>Expected Results</b> IllegalArgumentException.
     * </p>
     */
    @Test
    public void testTwoArgConstructorNegativeCapacityThrowsIAE() {
        try { new VectorAdapter(-1, 0); fail("new VectorAdapter(-1,0) deve lanciare IllegalArgumentException"); } catch (IllegalArgumentException e) { /* atteso */ }
        try { new VectorAdapter(-5, 2); fail("new VectorAdapter(-5,2) deve lanciare IllegalArgumentException"); } catch (IllegalArgumentException e) { /* atteso */ }
        try { new VectorAdapter(-1, -1); fail("new VectorAdapter(-1,-1) deve lanciare IllegalArgumentException"); } catch (IllegalArgumentException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il costruttore {@link VectorAdapter#VectorAdapter(int)} con capacità zero crei un vettore vuoto con capacity 0, e la gestione del limite.
     * </p>
     * <p>
     * <b>Test Case Design</b> Valore di limite inferiore dell'intervallo
     * valido di initialCapacity: 0 è ammesso e produce un array interno di
     * lunghezza nulla. Dopo la verifica dell'invariante lista vuota (size 0,
     * isEmpty true), l'inserimento di un elemento attiva la prima crescita
     * partendo da capacità zero, accertata tramite size==1 e capacity&gt;=1.
     * </p>
     * <p>
     * <b>Test Description</b> Si costruisce v0 con initialCapacity = 0 e si
     * verifica l'invariante di lista vuota: capacity 0, size 0, isEmpty true.
     * Si aggiunge quindi E1 = "elem1" tramite addElement: la size diventa 1
     * e la capacità, partendo da zero, cresce a un valore sufficiente a
     * ospitare l'elemento (almeno 1).
     * </p>
     * <p>
     * <b>Pre-Condition</b> Nessuna.
     * </p>
     * <p>
     * <b>Post-Condition</b> v0 vuoto poi con 1 elemento.
     * </p>
     * <p>
     * <b>Expected Results</b> capacity 0 iniziale, poi crescita dopo add.
     * </p>
     */
    @Test
    public void testConstructorCapacityZeroBoundary() {
        VectorAdapter v0 = new VectorAdapter(0);
        assertEquals(0, v0.capacity());
        assertEquals(0, v0.size());
        assertTrue(v0.isEmpty());
        v0.addElement(E1);
        assertEquals(1, v0.size());
        assertTrue(v0.capacity() >= 1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il costruttore {@link VectorAdapter#VectorAdapter(int,int)} crei un vettore vuoto con capacità e incremento specificati e che la crescita rispetti l'incremento.
     * </p>
     * <p>
     * <b>Test Case Design</b> Verifica funzionale del parametro
     * capacityIncrement. Con initialCapacity=5 e increment=2, l'inserimento
     * del sesto elemento eccede la capacità iniziale e attiva la politica di
     * crescita additiva: la nuova capacità deve essere almeno 5+2=7.
     * L'asserzione &gt;=7, anziche ==7, tollera politiche di crescita piu
     * generose mantenendo vincolante il rispetto dell'incremento dichiarato,
     * come da semantica di java.util.Vector.
     * </p>
     * <p>
     * <b>Test Description</b> Si costruisce va con initialCapacity = 5 e
     * capacityIncrement = 2, accertando subito la capacità iniziale. Si
     * aggiungono poi i sei elementi "x0".."x5": il sesto inserimento eccede
     * la capacità e attiva la crescita additiva. Le asserzioni finali
     * controllano size == 6 e capacity &gt;= 7, cioè una capacità aggiornata
     * di almeno un incremento pieno.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Nessuna.
     * </p>
     * <p>
     * <b>Post-Condition</b> Vettore con 6 elementi.
     * </p>
     * <p>
     * <b>Expected Results</b> size 6, capacity &gt;=7 (visto increment di 2).
     * </p>
     */
    @Test
    public void testConstructorWithCapacityAndIncrement() {
        VectorAdapter va = new VectorAdapter(5, 2);
        assertEquals(5, va.capacity());
        for (int i = 0; i < 6; i++) va.addElement("x" + i);
        assertEquals(6, va.size());
        assertTrue("capacity con increment 2 deve essere >=7", va.capacity() >= 7);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il costruttore {@link VectorAdapter#VectorAdapter(int,int)} con incremento zero gestisca correttamente la crescita per raddoppio.
     * </p>
     * <p>
     * <b>Test Case Design</b> Comportamento di frontiera del parametro
     * capacityIncrement: con incremento zero la crescita additiva non è
     * applicabile e java.util.Vector ricorre al raddoppio della capacità
     * corrente. Da capacità iniziale 2, tre inserimenti producono raddoppio
     * a 4 (asserito esattamente), sufficiente per contenere i tre elementi;
     * il test fissa così la politica di crescita alternativa prevista dalla
     * specifica Vector quando l'incremento e nullo.
     * </p>
     * <p>
     * <b>Test Description</b> Si costruisce va con initialCapacity = 2 e
     * increment = 0, accertando subito la capacità iniziale. L'aggiunta dei
     * tre elementi E1, E2 ed E3 eccede la capacità e attiva la crescita per
     * raddoppio: le asserzioni controllano size == 3 e capacity esattamente 4.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Nessuna.
     * </p>
     * <p>
     * <b>Post-Condition</b> v con 3 elementi.
     * </p>
     * <p>
     * <b>Expected Results</b> capacity 4 (raddoppio), size 3.
     * </p>
     */
    @Test
    public void testConstructorIncrementZeroBoundary() {
        VectorAdapter va = new VectorAdapter(2, 0);
        assertEquals(2, va.capacity());
        va.addElement(E1);
        va.addElement(E2);
        va.addElement(E3);
        assertEquals(3, va.size());
        assertTrue(va.capacity() >= 3);
        assertEquals(4, va.capacity());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il costruttore {@link VectorAdapter#VectorAdapter(HCollection)} copi correttamente tutti gli elementi della collezione sorgente nell'ordine dell'iteratore.
     * </p>
     * <p>
     * <b>Test Case Design</b> Comportamento normale del costruttore da
     * collezione: la copia deve preservare size e ordine di iterazione della
     * sorgente. La collezione [elem1,elem2,elem3] consente il confronto
     * posizionale completo elemento per elemento; il controllo su size e sui
     * tre indici copre quantità e ordine, requisiti distinti del contratto
     * del costruttore Vector(Collection) di J2SE 1.4.2.
     * </p>
     * <p>
     * <b>Test Description</b> Si costruisce c come collezione contenente
     * E1 = "elem1", E2 = "elem2" ed E3 = "elem3", quindi se ne crea la copia
     * v2 con il costruttore da collezione. Le asserzioni verificano size == 3
     * e il confronto posizionale completo: get(0) = elem1, get(1) = elem2,
     * get(2) = elem3.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Collezione c con 3 elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> v2 contiene copia shallow, c invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> size 3, ordine preservato, capacity 10 default.
     * </p>
     */
    @Test
    public void testConstructorFromCollection() {
        HCollection c = makeCollection(E1, E2, E3);
        VectorAdapter v2 = new VectorAdapter(c);
        assertEquals(3, v2.size());
        assertEquals(E1, v2.get(0));
        assertEquals(E2, v2.get(1));
        assertEquals(E3, v2.get(2));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il costruttore {@link VectorAdapter#VectorAdapter(HCollection)} lanci {@link NullPointerException} se la collezione sorgente è null.
     * </p>
     * <p>
     * <b>Test Case Design</b> Classe di errore dell'argomento del costruttore
     * da collezione: c=null viola la precondizione documentata per
     * Vector(Collection), che prescrive NullPointerException. L'eccezione è
     * dichiarata nell'annotazione @Test expected; il caso completa la
     * partizione dell'argomento insieme ai casi collezione vuota, singola e
     * multipla.
     * </p>
     * <p>
     * <b>Test Description</b> Si tenta di costruire un vettore passando
     * c = null. Il metodo non contiene altre istruzioni e l'eccezione attesa
     * è dichiarata direttamente nell'annotazione {@code @Test}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Nessuna.
     * </p>
     * <p>
     * <b>Post-Condition</b> Nessun vettore creato.
     * </p>
     * <p>
     * <b>Expected Results</b> NullPointerException.
     * </p>
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorFromNullCollectionThrowsNPE() {
        new VectorAdapter(null);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il costruttore {@link VectorAdapter#VectorAdapter(HCollection)} con collezione vuota crei un vettore vuoto di size 0.
     * </p>
     * <p>
     * <b>Test Case Design</b> Valore di limite inferiore dell'argomento
     * del costruttore da collezione: una sorgente vuota deve produrre un
     * vettore logicamente vuoto ma strutturalmente standard, cioè con
     * capacità default 10 anziche zero. La verifica combinata di size, isEmpty
     * e capacity distingue questo comportamento da quello del costruttore
     * {@link VectorAdapter#VectorAdapter(int)} con argomento 0.
     * </p>
     * <p>
     * <b>Test Description</b> Si costruisce c come collezione vuota tramite
     * makeCollection() e se ne crea la copia v2. Le asserzioni verificano
     * insieme lo stato logico e quello fisico del risultato: size == 0,
     * isEmpty true e capacity pari al default di 10.
     * </p>
     * <p>
     * <b>Pre-Condition</b> c vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> v2 vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> size 0, capacity 10.
     * </p>
     */
    @Test
    public void testConstructorFromEmptyCollectionBoundary() {
        HCollection c = makeCollection();
        VectorAdapter v2 = new VectorAdapter(c);
        assertEquals(0, v2.size());
        assertTrue(v2.isEmpty());
        assertEquals(DEFAULT_CAPACITY, v2.capacity());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il costruttore {@link VectorAdapter#VectorAdapter(HCollection)} con collezione singola crei un vettore di size 1 con elemento corretto.
     * </p>
     * <p>
     * <b>Test Case Design</b> Valore di frontiera minimo (non vuoto)
     * dell'argomento del costruttore da collezione: verifica che
     * l'elemento sia collocato all'indice 0 con size risultante 1. Completa
     * la partizione dimensionale della sorgente insieme ai casi vuoto e
     * multiplo.
     * </p>
     * <p>
     * <b>Test Description</b> Si costruisce c come collezione contenente il
     * solo E1 = "elem1" e se ne crea la copia v2. Le asserzioni verificano
     * size == 1 e che get(0) restituisca esattamente elem1.
     * </p>
     * <p>
     * <b>Pre-Condition</b> c con 1 elemento.
     * </p>
     * <p>
     * <b>Post-Condition</b> v2 con 1 elemento.
     * </p>
     * <p>
     * <b>Expected Results</b> size 1, get(0)=elem1.
     * </p>
     */
    @Test
    public void testConstructorFromSingleElementCollectionBoundary() {
        HCollection c = makeCollection(E1);
        VectorAdapter v2 = new VectorAdapter(c);
        assertEquals(1, v2.size());
        assertEquals(E1, v2.get(0));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il costruttore {@link VectorAdapter#VectorAdapter(HCollection)} crei una copia indipendente e che modifiche ripetute non propaghino.
     * </p>
     * <p>
     * <b>Test Case Design</b> Verifica dell'indipendenza strutturale tra
     * sorgente e copia prodotta dal costruttore da collezione: la copia è
     * shallow sugli elementi ma deve duplicare la struttura, quindi mutazioni
     * successive in entrambe le direzioni (add/remove sulla sorgente e sulla
     * copia) non devono propagarsi. Le dimensioni delle due liste sono
     * ricontrollate dopo ogni operazione per rilevare qualunque condivisione
     * residua dello stato interno.
     * </p>
     * <p>
     * <b>Test Description</b> Si costruisce c con gli elementi E1 ed E2 e se
     * ne crea la copia v2, inizialmente entrambe di size 2. Si aggiunge E3
     * alla sorgente: c arriva a 3 elementi mentre v2 resta a 2. Si aggiunge
     * quindi DUP = "dup" alla copia e si rimuove infine E1 da v2; dopo ogni
     * operazione le asserzioni ricontrollano entrambe le size per escludere
     * qualunque propagazione delle modifiche tra sorgente e copia.
     * </p>
     * <p>
     * <b>Pre-Condition</b> c con 2 elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> c e v2 indipendenti.
     * </p>
     * <p>
     * <b>Expected Results</b> c size 3, v2 size 3 separati.
     * </p>
     */
    @Test
    public void testConstructorFromCollectionIndependenceAndRepeated() {
        VectorAdapter c = new VectorAdapter();
        c.addElement(E1);
        c.addElement(E2);
        VectorAdapter v2 = new VectorAdapter(c);
        assertEquals(2, v2.size());
        c.addElement(E3);
        assertEquals(3, c.size());
        assertEquals(2, v2.size());
        v2.addElement(DUP);
        assertEquals(3, v2.size());
        assertEquals(3, c.size());
        v2.remove(E1);
        assertEquals(2, v2.size());
        assertEquals(3, c.size());
    }

    // ====================================================================
    // B) Primitivi e accesso posizionale (16)
    // ====================================================================
    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#get(int)} restituisca l'elemento corretto per indici validi e lanci {@link IndexOutOfBoundsException} per indici fuori intervallo.
     * </p>
     * <p>
     * <b>Test Case Design</b> Partizione normale dell'accesso indicizzato con
     * scansione completa degli indici validi [0,size): ogni posizione di
     * v=[elem1,elem2,elem3] viene letta e confrontata con l'elemento atteso.
     * I valori -1 e size costituiscono le minime violazioni rispettivamente
     * del limite inferiore e superiore dell'intervallo, per le quali il
     * contratto HList.get prescrive IndexOutOfBoundsException.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore v = [elem1, elem2, elem3] si
     * leggono tutti gli indici validi: get(0), get(1) e get(2) devono
     * restituire rispettivamente elem1, elem2 ed elem3. Seguono due letture
     * fuori intervallo, get(-1) e get(3), racchiuse nei blocchi
     * try/fail/catch che accertano IndexOutOfBoundsException.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v con [elem1,elem2,elem3].
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> get validi restituiscono elementi, invalidi lanciano IndexOutOfBounds.
     * </p>
     */
    @Test
    public void testGet() {
        populateThree();
        assertEquals(E1, v.get(0));
        assertEquals(E2, v.get(1));
        assertEquals(E3, v.get(2));
        try {
            v.get(-1);
            fail("get(-1) deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }
        try {
            v.get(3);
            fail("get(3) su size 3 deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#get(int)} su vettore vuoto lanci {@link IndexOutOfBoundsException} per qualsiasi indice.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso degenere dell'accesso indicizzato con
     * intervallo valido vuoto: per size=0 ogni indice e invalido. I valori 0,
     * -1 e 100 rappresentano rispettivamente la minima violazione interna,
     * quella inferiore e una violazione arbitrariamente superiore, accertando
     * che il controllo non dipenda dalla distanza dall'intervallo.
     * Comportamento atteso definito dal contratto HList.get: eccezione per
     * ogni indice esterno a [0,size).
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore vuoto di setUp si tentano tre
     * letture: get(0), get(-1) e get(100). Nessun indice è valido quando
     * size vale 0, quindi ciascuna invocazione, protetta da try/fail/catch,
     * deve produrre IndexOutOfBoundsException.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> IndexOutOfBoundsException.
     * </p>
     */
    @Test
    public void testGetOnEmptyListThrowsIOOBE() {
        try { v.get(0); fail("get(0) su vuoto deve lanciare"); } catch (IndexOutOfBoundsException e) { /* atteso */ }
        try { v.get(-1); fail("get(-1) su vuoto deve lanciare"); } catch (IndexOutOfBoundsException e) { /* atteso */ }
        try { v.get(100); fail("get(100) su vuoto deve lanciare"); } catch (IndexOutOfBoundsException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#get(int)} su vettore singolo gestisca correttamente gli indici di bordo 0 e 1.
     * </p>
     * <p>
     * <b>Test Case Design</b> Analisi dei valori di frontiera per l'accesso
     * indicizzato con size=1. L'intervallo degli indici validi si riduce al
     * valore singolo {0}, che coincide sia con il limite inferiore sia con
     * quello superiore: get(0) copre quindi entrambe le frontiere interne. I
     * valori 1 (= size) e -1 costituiscono le piu piccole violazioni
     * rispettivamente sopra e sotto l'intervallo valido e rappresentano le
     * due classi di errore simmetriche; il comportamento atteso e definito
     * dal contratto HList.get, eccezione per ogni indice esterno a [0,size).
     * Il caso size=0 e coperto da testGetOnEmptyListThrowsIOOBE.
     * </p>
     * <p>
     * <b>Test Description</b> Con il solo E1 = "elem1" nel vettore, l'indice
     * valido 0 coincide sia con il bordo inferiore sia con quello superiore:
     * get(0) deve restituire elem1, mentre get(-1) e get(1), le minime
     * violazioni dei due estremi, devono entrambe lanciare
     * IndexOutOfBoundsException.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1].
     * </p>
     * <p>
     * <b>Post-Condition</b> v=[elem1].
     * </p>
     * <p>
     * <b>Expected Results</b> get(0) ok, altri lanciano.
     * </p>
     */
    @Test
    public void testGetSingleElementBoundaries() {
        v.addElement(E1);
        assertEquals(E1, v.get(0));
        try { v.get(1); fail("get(1) su size 1 deve lanciare"); } catch (IndexOutOfBoundsException e) { /* atteso */ }
        try { v.get(-1); fail("get(-1) deve lanciare"); } catch (IndexOutOfBoundsException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che chiamate ripetute a {@link HList#get(int)} e alternate con {@link HList#set(int,Object)} siano coerenti.
     * </p>
     * <p>
     * <b>Test Case Design</b> Categoria ripetute sull'accesso indicizzato:
     * letture multiple della stessa posizione devono restituire valori
     * identici finche lo stato non cambia, mentre le scritture intercalate
     * con set devono riflettersi immediatamente nelle letture successive.
     * L'indice centrale 1 su tre elementi evita i bordi, già coperti dai
     * test limite; la sequenza termina con il ripristino del valore
     * originale, mantenendo lo stato coerente con la pre-condizione.
     * </p>
     * <p>
     * <b>Test Description</b> Su v = [elem1, elem2, elem3] la lettura di
     * get(1) viene ripetuta tre volte con esito sempre elem2. Si scrive poi
     * la stessa posizione tre volte con set(1, ...) usando i valori "a",
     * "b" ed infine E2 per ripristinare lo stato iniziale: dopo ogni
     * scrittura una lettura immediata conferma il valore appena assegnato.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2,elem3].
     * </p>
     * <p>
     * <b>Post-Condition</b> v=[elem1,elem2,elem3] ripristinato.
     * </p>
     * <p>
     * <b>Expected Results</b> get ripetuti uguali, set aggiorna.
     * </p>
     */
    @Test
    public void testGetRepeatedSequential() {
        populateThree();
        for (int i = 0; i < 3; i++) assertEquals(E2, v.get(1));
        v.set(1, "a");
        assertEquals("a", v.get(1));
        assertEquals("a", v.get(1));
        v.set(1, "b");
        assertEquals("b", v.get(1));
        v.set(1, E2);
        assertEquals(E2, v.get(1));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#set(int,Object)} sostituisca l'elemento all'indice specificato, restituisca il vecchio valore e lanci {@link IndexOutOfBoundsException} per indici non validi.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale di sostituzione con verifica
     * integrata delle condizioni di errore. set(int,Object) non modifica size
     * ne capacity: la verifica riguarda la sola sostituzione e il valore
     * restituito. L'indice 1 su una lista di tre elementi permette di
     * controllare che gli elementi adiacenti (0 e 2) restino invariati dopo
     * la scrittura. Il confronto tra valore restituito ed elemento precedente
     * implementa la clausola del contratto List.set di J2SE 1.4.2 relativa
     * alla restituzione del precedente; gli indici -1 e size sono le minime
     * violazioni rispettivamente del limite inferiore e superiore
     * dell'intervallo valido [0,size).
     * </p>
     * <p>
     * <b>Test Description</b> Su un vettore di tre elementi si invoca
     * set(1, "nuovo") conservando il valore restituito; seguono due tentativi
     * fuori intervallo, set(-1, ...) e set(3, ...), racchiusi nei blocchi
     * try/fail/catch. Le asserzioni controllano il valore precedente
     * restituito da set (elem2), la lettura aggiornata dell'indice 1 e la
     * size invariata a 3.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v con [elem1,elem2,elem3].
     * </p>
     * <p>
     * <b>Post-Condition</b> v=[elem1,nuovo,elem3].
     * </p>
     * <p>
     * <b>Expected Results</b> old=elem2, get(1)=nuovo, eccezioni su bounds.
     * </p>
     */
    @Test
    public void testSet() {
        populateThree();
        Object old = v.set(1, "nuovo");
        assertEquals(E2, old);
        assertEquals("nuovo", v.get(1));
        assertEquals(3, v.size());
        try {
            v.set(-1, "x");
            fail("set(-1) deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }
        try {
            v.set(3, "x");
            fail("set(3) su size 3 deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#set(int,Object)} su vettore singolo e sui bordi di un vettore di size 3 funzioni correttamente.
     * </p>
     * <p>
     * <b>Test Case Design</b> Analisi dei valori di frontiera per la
     * sostituzione indicizzata: gli indici 0 e size-1 sono gli estremi
     * interni dell'intervallo valido e coincidono quando size=1, dove
     * set(0,...) rappresenta l'unico accesso possibile. Il secondo blocco
     * replica la verifica sui due estremi distinti di una lista di tre
     * elementi, controllando anche che size resti invariata. Riferimento:
     * contratto List.set, sostituzione senza alterazione della struttura.
     * </p>
     * <p>
     * <b>Test Description</b> Su un vettore di un solo elemento si
     * sostituisce l'elemento in posizione 0 con "x", verificando sia il
     * valore restituito sia quello letto dopo la scrittura. Il vettore viene
     * poi ricostruito con tre elementi: si sostituiscono i due estremi
     * (indice 0 con "head", indice 2 con "tail") controllando le letture
     * aggiornate e che size resti 3.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v singolo poi con 3.
     * </p>
     * <p>
     * <b>Post-Condition</b> v con sostituzioni bordi.
     * </p>
     * <p>
     * <b>Expected Results</b> set bordi ok.
     * </p>
     */
    @Test
    public void testSetBoundariesAndSingleElement() {
        v.addElement(E1);
        Object old = v.set(0, "x");
        assertEquals(E1, old);
        assertEquals("x", v.get(0));
        v.clear();
        populateThree();
        v.set(0, "head");
        assertEquals("head", v.get(0));
        v.set(2, "tail");
        assertEquals("tail", v.get(2));
        assertEquals(3, v.size());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che chiamate ripetute a {@link HList#set(int,Object)} sullo stesso indice sovrascrivano correttamente.
     * </p>
     * <p>
     * <b>Test Case Design</b> Categoria ripetute sulla scrittura indicizzata:
     * sovrascritture consecutive della stessa posizione devono lasciare come
     * visibile l'ultimo valore assegnato, senza accumulo di stato residuo.
     * Ogni scrittura e seguita da una lettura immediata di controllo; il
     * valore finale c e la size invariata a 3 accertano l'assenza di effetti
     * collaterali strutturali dovuti alla ripetizione.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore a tre elementi la posizione 1
     * viene sovrascritta tre volte consecutive con set(1, ...) usando "a",
     * "b" e infine "c": ogni scrittura è seguita da una lettura di controllo
     * e al termine deve risultare visibile solo l'ultimo valore, con size
     * ferma a 3.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2,elem3].
     * </p>
     * <p>
     * <b>Post-Condition</b> v=[elem1,c,elem3].
     * </p>
     * <p>
     * <b>Expected Results</b> get(1)=c, size 3.
     * </p>
     */
    @Test
    public void testSetRepeatedOverwrites() {
        populateThree();
        v.set(1, "a");
        assertEquals("a", v.get(1));
        v.set(1, "b");
        assertEquals("b", v.get(1));
        v.set(1, "c");
        assertEquals("c", v.get(1));
        assertEquals(3, v.size());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#add(int,Object)} inserisca l'elemento alla posizione specificata shiftando i successivi e lanci {@link IndexOutOfBoundsException} per indici non validi.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale dell'inserimento posizionale
     * con verifica integrata delle condizioni di errore. Gli indici scelti
     * coprono i tre punti significativi dell'intervallo valido
     * {@code [0,size]}: la testa (0), una posizione interna (2) e la coda
     * ({@code index==size}), quest'ultima ammessa esplicitamente dal
     * contratto di {@code List.add} come equivalente di un append.
     * L'inserimento in posizione interna consente inoltre di osservare lo
     * shift a destra degli elementi successivi. Le violazioni {@code -1} e
     * {@code size+1} sono le minime violazioni rispettivamente del limite
     * inferiore e superiore, per cui il contratto {@link HList#add(int,Object)}
     * prescrive {@link IndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore a tre elementi si inserisce "head"
     * in testa con add(0, ...) ottenendo [head, elem1, elem2, elem3], poi
     * "mid" in posizione 2 ottenendo [head, elem1, mid, elem2, elem3] e
     * infine "tail" con add(size, ...) in coda. Dopo ogni inserimento le
     * asserzioni ricontrollano l'intero contenuto e la size crescente;
     * chiudono il test due inserimenti fuori intervallo, add(-1, ...) e
     * add(size+1, ...), che devono lanciare IndexOutOfBoundsException.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v con [elem1,elem2,elem3] size 3.
     * </p>
     * <p>
     * <b>Post-Condition</b> v con 6 elementi dopo 3 add.
     * </p>
     * <p>
     * <b>Expected Results</b> Ordine corretto, size 6, eccezioni bounds.
     * </p>
     */
    @Test
    public void testAddIndex() {
        populateThree();
        v.add(0, "head");
        assertEquals("head", v.get(0));
        assertEquals(E1, v.get(1));
        v.add(2, "mid");
        assertEquals("mid", v.get(2));
        assertEquals(E2, v.get(3));
        v.add(v.size(), "tail");
        assertEquals("tail", v.get(v.size() - 1));
        assertEquals(6, v.size());
        try {
            v.add(-1, "x");
            fail("add(-1) deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }
        try {
            v.add(v.size() + 1, "x");
            fail("add(size+1) deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#add(int,Object)} con indice 0 e con indice uguale a size inserisca correttamente come casi limite.
     * </p>
     * <p>
     * <b>Test Case Design</b> Valori di frontiera dell'operazione
     * {@code add(int,Object)} isolati dal caso generale. Gli estremi
     * {@code 0} e {@code size} vengono esercitati partendo da vettore vuoto
     * e da vettori minimi (size 1 e 2), configurazioni in cui l'inserzione
     * coincide rispettivamente con la creazione del primo elemento, con uno
     * shift completo e con un append puro. Queste frontiere sono le sole
     * configurazioni in cui l'indice di inserzione non richiede shift
     * parziale oppure lo richiede su tutta la lista, quindi individuano
     * classi di equivalenza distinte dal caso interno. Complementa
     * {@link #testAddIndex()}, che copre anche le posizioni intermedie.
     * </p>
     * <p>
     * <b>Test Description</b> Tre fasi coprono i soli indici ammessi alle
     * frontiere: sul vettore vuoto add(0, "head") crea la lista [head];
     * su [elem1] add(0, "pre") produce [pre, elem1]; su [elem1, elem2]
     * add(2, "tail"), con indice pari alla size, aggiunge in coda. A ogni
     * fase le asserzioni verificano contenuto e size aggiornati.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto poi con elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> v con inserimenti limite.
     * </p>
     * <p>
     * <b>Expected Results</b> testa e coda corrette.
     * </p>
     */
    @Test
    public void testAddAtIndexBoundaryZeroAndSize() {
        v.add(0, "head");
        assertEquals("head", v.get(0));
        assertEquals(1, v.size());
        v.clear();
        v.addElement(E1);
        v.add(0, "pre");
        assertEquals("pre", v.get(0));
        assertEquals(E1, v.get(1));
        v.clear();
        v.addElement(E1);
        v.addElement(E2);
        v.add(v.size(), "tail");
        assertEquals("tail", v.get(v.size() - 1));
        assertEquals(3, v.size());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#add(int,Object)} lanci {@link IndexOutOfBoundsException} per indici negativi e oltre size.
     * </p>
     * <p>
     * <b>Test Case Design</b> Classe di errore dell'indice fuori intervallo
     * per {@code add(int,Object)}. Gli indici {@code -1}, {@code size+1}
     * (su lista non vuota) e {@code 1} (su lista vuota, dove l'unico valore
     * valido e 0) rappresentano le minime violazioni dei due estremi
     * dell'intervallo {@code [0,size]}, inclusa la particolarita della
     * frontiera inferiore quando la taglia vale 0. Il vettore resta
     * invariato dopo ogni chiamata fallita, proprieta che il test verifica
     * riutilizzando lo stesso stato per le tre asserzioni. La responsabilita
     * deriva dal contratto {@link HList#add(int,Object)}, che prescrive
     * {@link IndexOutOfBoundsException} se l'indice non e valido.
     * </p>
     * <p>
     * <b>Test Description</b> Su un vettore contenente il solo elem1 si
     * tentano gli inserimenti add(-1, ...) e add(2, ...), minime violazioni
     * dei due estremi dell'intervallo valido [0, size]. Il vettore viene poi
     * svuotato e si prova add(1, ...): anche con size 0 l'indice 1 resta
     * fuori intervallo. Tutte le invocazioni devono lanciare
     * IndexOutOfBoundsException.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1] poi vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> IndexOutOfBoundsException.
     * </p>
     */
    @Test
    public void testAddAtIndexThrowsIOOBE() {
        v.addElement(E1);
        try { v.add(-1, "x"); fail("add(-1) deve lanciare"); } catch (IndexOutOfBoundsException e) { /* atteso */ }
        try { v.add(2, "x"); fail("add(2) su size1 deve lanciare"); } catch (IndexOutOfBoundsException e) { /* atteso */ }
        VectorAdapter empty = new VectorAdapter();
        try { empty.add(1, "x"); fail("add(1) su vuoto deve lanciare"); } catch (IndexOutOfBoundsException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#addAll(int,HCollection)} inserisca la collezione alla posizione specificata e lanci {@link IndexOutOfBoundsException} per indici non validi.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale dell'inserimento posizionale
     * bulk ({@code addAll(int,HCollection)}) con verifica delle condizioni
     * di errore. La collezione {@code [dup,dup]} contiene elementi
     * duplicati fra loro, cosi da rendere riconoscibili sia il punto di
     * innesto ({@code index=1}) sia l'ordine interno con cui gli elementi
     * vengono trascritti; l'indice 1, interno, obbliga inoltre uno shift
     * degli originali {@code elem2} ed {@code elem3}. Le chiamate
     * {@code addAll(-1,c)} e {@code addAll(size+1,c)} costituiscono le
     * minime violazioni dei limiti dell'intervallo valido {@code [0,size]}
     * previsto dal contratto {@link HList#addAll(int,HCollection)}, che
     * prescrive {@link IndexOutOfBoundsException}. Complementa
     * {@link #testAddAll()}, che opera sulla versione senza indice.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore a tre elementi si inserisce la
     * collezione c = [dup, dup] in posizione 1: il risultato atteso è
     * v = [elem1, dup, dup, elem2, elem3] con size 5, verificato elemento
     * per elemento. Chiudono il test due chiamate fuori intervallo,
     * addAll(-1, ...) e addAll(6, ...), che devono lanciare
     * IndexOutOfBoundsException.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2,elem3] size 3.
     * </p>
     * <p>
     * <b>Post-Condition</b> v con 5 elementi.
     * </p>
     * <p>
     * <b>Expected Results</b> Inserimento ordinato a index 1, size 5, IOOBE su bounds.
     * </p>
     */
    @Test
    public void testAddAllIndex() {
        populateThree();
        HCollection c = makeCollection(DUP, DUP);
        boolean b = v.addAll(1, c);
        assertTrue(b);
        assertEquals(5, v.size());
        assertEquals(E1, v.get(0));
        assertEquals(DUP, v.get(1));
        assertEquals(DUP, v.get(2));
        assertEquals(E2, v.get(3));
        assertEquals(E3, v.get(4));
        try {
            v.addAll(-1, c);
            fail("addAll(-1) deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }
        try {
            v.addAll(6, c);
            fail("addAll(size+1) deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#addAll(int,HCollection)} con collezione vuota
     * restituisca false e non modifichi il vettore.
     * </p>
     * <p>
     * <b>Test Case Design</b> Frontiera inferiore della versione posizionale:
     * {@code addAll(int, HCollection)} con collezione vuota deve comportarsi
     * come no-op e restituire false. Complementa {@link #testAddAllWithEmptyCollectionNoOp()}
     * che verifica la stessa frontiera sulla versione senza indice.
     * </p>
     * <p>
     * <b>Test Description</b> Si popola {@code v} con [E1, E2, E3] e si
     * invoca {@code v.addAll(1, empty)} dove {@code empty} e una
     * VectorAdapter vuota. Il vettore deve restare invariato e il metodo
     * deve restituire false.
     * </p>
     * <p>
     * <b>Pre-Condition</b> {@code v = [E1, E2, E3]}, size = 3.
     * </p>
     * <p>
     * <b>Post-Condition</b> {@code v = [E1, E2, E3]}, size = 3, invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> {@code addAll} restituisce false; size = 3;
     * contenuto invariato.
     * </p>
     */
    @Test
    public void testAddAllAtIndexWithEmptyCollection() {
        populateThree();
        assertFalse(v.addAll(1, makeCollection()));
        assertEquals(3, v.size());
        assertEquals(E1, v.get(0));
        assertEquals(E2, v.get(1));
        assertEquals(E3, v.get(2));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#remove(int)} rimuova e restituisca l'elemento all'indice specificato shiftando i successivi e lanci {@link IndexOutOfBoundsException} per indici non validi.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale della rimozione posizionale con
     * verifica integrata delle condizioni di errore. L'indice centrale 1 su
     * una lista di tre elementi e la scelta piu informativa: costringe lo
     * shift a sinistra di un elemento successivo e permette di controllare
     * che entrambi gli adiacenti restino intatti. Il controllo del valore di
     * ritorno (l'elemento rimosso) implementa la clausola corrispondente del
     * contratto {@code List.remove} di J2SE 1.4.2; gli indici {@code -1} e
     * {@code size} post-rimozione sono le minime violazioni dei limiti
     * validi {@code [0,size)} previste dal medesimo contratto, che prescrive
     * {@link IndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore a tre elementi si rimuove
     * l'indice 1: remove(1) deve restituire elem2 e lasciare la lista
     * [elem1, elem3] con size 2. Seguono due rimozioni fuori intervallo,
     * remove(-1) e remove(2), che devono lanciare IndexOutOfBoundsException.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2,elem3].
     * </p>
     * <p>
     * <b>Post-Condition</b> v=[elem1,elem3] size 2.
     * </p>
     * <p>
     * <b>Expected Results</b> r=elem2, size 2, ordine preservato, eccezioni bounds.
     * </p>
     */
    @Test
    public void testRemoveIndex() {
        populateThree();
        Object r = v.remove(1);
        assertEquals(E2, r);
        assertEquals(2, v.size());
        assertEquals(E1, v.get(0));
        assertEquals(E3, v.get(1));
        try {
            v.remove(-1);
            fail("remove(-1) deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }
        try {
            v.remove(2);
            fail("remove(2) su size 2 deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#remove(int)} su vettore singolo e su vettore vuoto gestisca correttamente i limiti.
     * </p>
     * <p>
     * <b>Test Case Design</b> Valori di frontiera della rimozione
     * posizionale: la transizione {@code size 1 -> 0} e il comportamento
     * sul contenitore vuoto. {@code remove(0)} sull'unico elemento e
     * l'unico caso in cui rimozione e svuotamento coincidono, mentre la
     * chiamata successiva sul vettore ormai vuoto esplora la frontiera
     * inferiore dell'intervallo valido, che si riduce all'insieme vuoto.
     * Il contratto {@link HList#remove(int)} prescrive
     * {@link IndexOutOfBoundsException} per ogni indice quando la lista non
     * possiede elementi. Complementa {@link #testRemoveIndex()}, che lavora
     * su una lista di tre elementi.
     * </p>
     * <p>
     * <b>Test Description</b> Dal vettore contenente il solo elem1 si rimuove
     * l'indice 0, unico indice valido: remove(0) restituisce elem1 e svuota
     * la lista (size 0, isEmpty true). La seconda remove(0), eseguita sulla
     * lista ormai vuota, non ha più indici validi e deve lanciare
     * IndexOutOfBoundsException.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1].
     * </p>
     * <p>
     * <b>Post-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> rimosso e vuoto, eccezione su vuoto.
     * </p>
     */
    @Test
    public void testRemoveAtIndexSingleElementAndEmpty() {
        v.addElement(E1);
        Object r = v.remove(0);
        assertEquals(E1, r);
        assertEquals(0, v.size());
        assertTrue(v.isEmpty());
        try { v.remove(0); fail("remove(0) su vuoto deve lanciare"); } catch (IndexOutOfBoundsException e) { /* atteso */ }
        try { v.remove(-1); fail("remove(-1) deve lanciare"); } catch (IndexOutOfBoundsException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che chiamate ripetute a {@link HList#remove(int)} fino a svuotare il vettore funzionino correttamente.
     * </p>
     * <p>
     * <b>Test Case Design</b> Categoria ripetute della rimozione
     * posizionale: una sequenza completa di {@code remove(0)} che attraversa
     * tutti gli stati intermedi di taglia fino allo svuotamento. Ripetere
     * sempre la stessa posizione trasforma il test in una verifica
     * sistematica dello shift sinistro e della decrescita della taglia, con
     * controllo del valore ritornato ad ogni passo per confermare l'ordine
     * degli elementi estratti. Lo stato finale atteso (lista vuota) segue
     * dal contratto {@link HList#remove(int)}, che rimuove l'elemento alla
     * posizione indicata ridimensionando la lista. Complementa
     * {@link #testRemoveAtIndexSingleElementAndEmpty()}, limitato alla taglia 1.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore a tre elementi si invoca remove(0)
     * tre volte consecutive raccogliendo i valori restituiti: devono uscire
     * nell'ordine elem1, elem2 ed elem3, finché la lista risulta vuota con
     * size 0 e isEmpty true.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2,elem3].
     * </p>
     * <p>
     * <b>Post-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> size 0, ordine rimosso.
     * </p>
     */
    @Test
    public void testRemoveAtIndexRepeatedUntilEmpty() {
        populateThree();
        assertEquals(E1, v.remove(0));
        assertEquals(2, v.size());
        assertEquals(E2, v.remove(0));
        assertEquals(1, v.size());
        assertEquals(E3, v.remove(0));
        assertEquals(0, v.size());
        assertTrue(v.isEmpty());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#add(int,Object)} e {@link HCollection#add(Object)} supportino correttamente elementi null e duplicati.
     * </p>
     * <p>
     * <b>Test Case Design</b> Classi di equivalenza degli elementi ammessi:
     * il riferimento {@code null} e le occorrenze ripetute dello stesso
     * oggetto. Entrambe le categorie sono parte del contratto di
     * HList/HCollection, che ammettono elementi null e non impongono
     * unicità; il Vector CLDC sottostante le supporta nativamente, quindi
     * il test accerta che l'adattatore non introduca restrizioni
     * aggiuntive. I dati {@code [null,dup,dup]} permettono di verificare in
     * un solo stato la memorizzazione del null e la corretta distinzione
     * delle posizioni duplicate tramite indexOf/lastIndexOf. Complementa i
     * test null dedicati alla API legacy ({@link #testLegacyNullElements()}).
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore vuoto si aggiunge null, letto poi
     * con get(0), seguito da due occorrenze di DUP = "dup": la lista diventa
     * [null, dup, dup]. Le asserzioni verificano contains per null e per
     * dup, la prima occorrenza di dup in posizione 1 tramite indexOf e
     * l'ultima in posizione 2 tramite lastIndexOf.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v=[null,dup,dup] size 3.
     * </p>
     * <p>
     * <b>Expected Results</b> null e duplicati memorizzati correttamente.
     * </p>
     */
    @Test
    public void testAddNullAndDuplicate() {
        v.add(null);
        assertEquals(1, v.size());
        assertNull(v.get(0));
        assertTrue(v.contains(null));
        v.add(DUP);
        v.add(DUP);
        assertEquals(3, v.size());
        assertEquals(DUP, v.get(1));
        assertEquals(DUP, v.get(2));
        assertEquals(1, v.indexOf(DUP));
        assertEquals(2, v.lastIndexOf(DUP));
    }

    // C) Operazioni bulk (27)
    // ====================================================================
    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#size()} e {@link HCollection#isEmpty()} riflettano correttamente lo stato vuoto e popolato del vettore.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale delle due operazioni di
     * osservazione della taglia, verificate sugli stati vuoto, popolato e
     * popolato oltre il dato iniziale. La sequenza vuoto -&gt; tre elementi
     * -&gt; quarto elemento copre la transizione {@code isEmpty}
     * true-&gt;false e la crescita coerente di {@code size}, includendo un
     * inserimento via API legacy ({@code addElement}) per confermare che
     * entrambe le interfacce agiscono sul medesimo stato interno. I valori
     * attesi derivano direttamente dai contratti {@link HCollection#size()}
     * e {@link HCollection#isEmpty()}, che legano il risultato al numero di
     * componenti presenti.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore vuoto si verifica la coppia
     * size == 0 / isEmpty true. Dopo populateThree() il vettore contiene
     * [elem1, elem2, elem3]: size passa a 3 e isEmpty a false. Un ultimo
     * addElement(DUP) porta la size a 4.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto; poi con 3 elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> v con 4 elementi.
     * </p>
     * <p>
     * <b>Expected Results</b> 0/true su vuoto, 3/false dopo populate, 4 dopo add.
     * </p>
     */
    @Test
    public void testSizeAndIsEmpty() {
        assertEquals(0, v.size());
        assertTrue(v.isEmpty());
        populateThree();
        assertEquals(3, v.size());
        assertFalse(v.isEmpty());
        v.addElement(DUP);
        assertEquals(4, v.size());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#size()} e {@link HCollection#isEmpty()} su vettore vuoto restituiscano 0 e true come caso limite.
     * </p>
     * <p>
     * <b>Test Case Design</b> Stato limite isolato della coppia
     * {@code size}/{@code isEmpty}: il contenitore appena costruito da
     * setUp. Il vuoto e una classe di equivalenza a se stessa per queste
     * operazioni (size 0, isEmpty true) e rappresenta la frontiera
     * inferiore della taglia. Il controllo accessorio sulla lunghezza
     * dell'array prodotto da {@code toArray()} fornisce una conferma
     * indiretta della taglia nulla secondo il contratto
     * {@link HCollection#toArray()}.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore vuoto di setUp si verificano
     * size == 0 ed isEmpty true; la lettura get(0), priva di indici validi,
     * deve lanciare IndexOutOfBoundsException, completando il quadro dello
     * stato limite vuoto.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> size 0, isEmpty true.
     * </p>
     */
    @Test
    public void testSizeIsEmptyOnEmptyList() {
        assertEquals(0, v.size());
        assertTrue(v.isEmpty());
        assertEquals(0, v.toArray().length);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che chiamate ripetute a {@link HCollection#size()} e {@link HCollection#isEmpty()} dopo sequenze di add e remove siano coerenti.
     * </p>
     * <p>
     * <b>Test Case Design</b> Categoria ripetute sulle operazioni di
     * osservazione: cicli completi di crescita (5 {@code addElement}) e
     * contrazione (5 {@code remove}) con verifica di size dopo ogni singola
     * mutazione. L'accoppiamento di ogni modifica con la lettura immediata
     * accerta che nessuno stato intermedio venga perso o alterato, inclusa
     * la transizione finale verso il vuoto. La coerenza richiesta tra
     * {@code size}, {@code isEmpty} e il numero effettivo di operazioni
     * segue dai contratti {@link HCollection#size()} e
     * {@link HCollection#isEmpty()}. Complementa
     * {@link #testSizeAndIsEmpty()}, che verifica solo tre stati discreti.
     * </p>
     * <p>
     * <b>Test Description</b> Dal vettore vuoto si eseguono cinque
     * inserimenti consecutivi, ricontrollando dopo ciascuno che size
     * coincida con il numero di elementi inseriti e isEmpty sia false.
     * Seguono cinque rimozioni che riportano progressivamente la size a 0,
     * con doppia verifica finale di isEmpty.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> size 0->5->0, isEmpty true finale.
     * </p>
     */
    @Test
    public void testSizeIsEmptyRepeated() {
        for (int i = 0; i < 5; i++) {
            v.addElement("x" + i);
            assertEquals(i + 1, v.size());
            assertFalse(v.isEmpty());
        }
        for (int i = 4; i >= 0; i--) {
            v.remove(i);
            assertEquals(i, v.size());
        }
        assertTrue(v.isEmpty());
        assertEquals(0, v.size());
        assertTrue(v.isEmpty());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#contains(Object)} restituisca true per elementi presenti, false per assenti e gestisca correttamente null.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale dell'appartenenza con verifica
     * della gestione del null. I dati coprono le tre classi di equivalenza
     * dell'argomento: elemento presente, elemento assente e riferimento
     * null (prima assente, poi inserito appositamente). Il comportamento
     * atteso su null discende dalla definizione contrattuale di
     * {@link HCollection#contains(Object)}, che richiede il confronto
     * {@code (o==null ? e==null : o.equals(e))}; il ramo null e quindi
     * osservabile solo dopo un inserimento esplicito, come previsto dalla
     * sequenza di test. Complementa {@link #testContainsOnEmptyList()} per
     * lo stato vuoto.
     * </p>
     * <p>
     * <b>Test Description</b> Su v = [elem1, elem2, elem3] si verifica che
     * contains("elem1") restituisca true e contains("missing") false. Si
     * aggiunge quindi null con add(null): da quel momento anche
     * contains(null) deve restituire true.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2,elem3].
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato (poi con null aggiunto per ultima verifica).
     * </p>
     * <p>
     * <b>Expected Results</b> true per presente, false per assente, true per null dopo add.
     * </p>
     */
    @Test
    public void testContains() {
        populateThree();
        assertTrue(v.contains(E1));
        assertTrue(v.contains(E2));
        assertFalse(v.contains("missing"));
        assertFalse(v.contains(null));
        v.add(null);
        assertTrue(v.contains(null));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#contains(Object)} su vettore vuoto restituisca false anche per null.
     * </p>
     * <p>
     * <b>Test Case Design</b> Stato limite dell'appartenenza: il
     * contenitore vuoto, per cui {@code contains} e falsa per ogni
     * argomento possibile. Le tre chiamate (elemento, null, assente)
     * esplorano l'intera classe di equivalenza dell'argomento sullo stato
     * degenere, dove nessun confronto puo avere esito positivo; le
     * verifiche successive su vettore ripopolato e ri-svuotato confermano
     * che il risultato dipende esclusivamente dallo stato corrente.
     * L'attesa false universale segue dal contratto
     * {@link HCollection#contains(Object)}, definito rispetto agli
     * elementi effettivamente contenuti.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore vuoto qualunque ricerca deve
     * fallire: le asserzioni verificano che contains("elem1"),
     * contains(null) e contains("missing") restituiscano tutte false.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> false sempre.
     * </p>
     */
    @Test
    public void testContainsOnEmptyList() {
        assertFalse(v.contains(E1));
        assertFalse(v.contains(null));
        assertFalse(v.contains("missing"));
        v.addElement(E1);
        assertFalse(v.contains(E2));
        v.clear();
        assertFalse(v.contains(E1));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#containsAll(HCollection)} restituisca true per sottoinsiemi presenti, false altrimenti e lanci {@link NullPointerException} per argomento null.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale dell'inclusione insiemistica con
     * verifica della condizione di errore sull'argomento. La coppia di
     * collezioni {@code [elem1,elem2]} (sottoinsieme) e
     * {@code [elem1,missing]} (quasi-sottoinsieme) discrimina il risultato
     * con un solo elemento di differenza, tecnica che isola la causa del
     * boolean; l'argomento null costituisce la classe di errore prevista
     * dal contratto {@link HCollection#containsAll(HCollection)}, che
     * prescrive {@link NullPointerException}. Complementa
     * {@link #testContainsAllOnEmptyListVacuous()} per il caso
     * dell'argomento vuoto.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore a tre elementi si invoca
     * containsAll con c1 = [elem1, elem2], sottoinsieme completo, atteso
     * true, e con c2 = [elem1, missing], contenente un elemento assente,
     * atteso false. Il tentativo finale containsAll(null) deve lanciare
     * NullPointerException.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v con 3 elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> true per subset, false per non-subset, NPE per null.
     * </p>
     */
    @Test
    public void testContainsAll() {
        populateThree();
        HCollection c1 = makeCollection(E1, E2);
        assertTrue(v.containsAll(c1));
        HCollection c2 = makeCollection(E1, "missing");
        assertFalse(v.containsAll(c2));
        try {
            v.containsAll(null);
            fail("containsAll(null) deve lanciare NullPointerException");
        } catch (NullPointerException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#containsAll(HCollection)} con collezione vuota restituisca true per vacuità e su vuoto.
     * </p>
     * <p>
     * <b>Test Case Design</b> Valore di frontiera vacuo di containsAll:
     * l'argomento collezione vuota. Per definizione insiemistica l'insieme
     * vuoto e incluso in qualunque insieme, quindi containsAll(vuota) deve
     * restituire true tanto su lista popolata quanto su lista vuota;
     * questa proprieta, nota come vacuita, e la frontiera naturale
     * dell'operazione e distingue il caso true-degenere dal caso false
     * osservato nel test precedente. Il controesempio finale (lista vuota
     * con argomento non vuoto) completa la matrice stati/argomenti.
     * </p>
     * <p>
     * <b>Test Description</b> Tre combinazioni coprono la semantica della
     * vacuità: su v = [elem1, elem2] containsAll(collezione vuota) è true
     * per inclusione vacuosa; sul vettore vuoto containsAll(vuota) resta
     * true; solo il vettore vuoto interrogato con c = [elem1] restituisce
     * false, perché l'elemento richiesto non può essere contenuto.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v con 2, c vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> true per vuota, false per non-sottinsieme.
     * </p>
     */
    @Test
    public void testContainsAllOnEmptyListVacuous() {
        v.addElement(E1);
        v.addElement(E2);
        HCollection empty = makeCollection();
        assertTrue(v.containsAll(empty));
        VectorAdapter vEmpty = new VectorAdapter();
        assertTrue(vEmpty.containsAll(empty));
        HCollection nonEmpty = makeCollection(E1);
        assertFalse(vEmpty.containsAll(nonEmpty));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#add(Object)} aggiunga l'elemento in coda e restituisca true.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale dell'append secondo HCollection:
     * {@code add(Object)} deve collocare l'elemento in coda e restituire
     * true. La verifica del boolean implementa la clausola del contratto
     * {@link HCollection#add(Object)} che garantisce l'esito positivo per
     * collezioni che ammettono duplicati, mentre il controllo di posizione
     * ({@code get(size-1)}) accerta un append effettivo e non un
     * inserimento altrove. Complementa {@link #testAddObjectOnEmptyList()}
     * per la transizione dallo stato vuoto.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore [elem1, elem2] si aggiunge
     * E3 = "elem3": add deve restituire true, portare la size a 3 e collocare
     * il nuovo elemento in coda, come confermato dalla lettura get(2).
     * </p>
     * <p>
     * <b>Pre-Condition</b> v con [elem1,elem2].
     * </p>
     * <p>
     * <b>Post-Condition</b> v con [elem1,elem2,elem3].
     * </p>
     * <p>
     * <b>Expected Results</b> true, size 3, ultimo elemento elem3.
     * </p>
     */
    @Test
    public void testAddObject() {
        v.addElement(E1);
        v.addElement(E2);
        boolean b = v.add(E3);
        assertTrue(b);
        assertEquals(3, v.size());
        assertEquals(E3, v.get(2));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#add(Object)} su vettore vuoto aggiunga correttamente come caso limite.
     * </p>
     * <p>
     * <b>Test Case Design</b> Valore di frontiera dell'append: la prima
     * inserzione sul contenitore vuoto, che porta size da 0 a 1. Lo stato
     * vuoto e una classe di equivalenza propria per {@code add(Object)}
     * perche coincide con {@code add(0,Object)} senza shift;
     * l'accoppiamento con un secondo inserimento di null estende il
     * controllo alla classe degli elementi null ammessi dal contratto.
     * L'attesa true costante deriva dalla clausola di ritorno del contratto
     * {@link HCollection#add(Object)} per liste che accettano l'elemento.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore vuoto il primo add(E1) deve
     * restituire true, portare la size a 1 e risultare leggibile tramite
     * get(0); un secondo add(null) accerta l'ammissione degli elementi
     * nulli portando la size a 2.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v=[elem1,null].
     * </p>
     * <p>
     * <b>Expected Results</b> true, size 1 poi 2.
     * </p>
     */
    @Test
    public void testAddObjectOnEmptyList() {
        assertTrue(v.add(E1));
        assertEquals(1, v.size());
        assertEquals(E1, v.get(0));
        assertTrue(v.add(null));
        assertEquals(2, v.size());
        assertNull(v.get(1));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che chiamate ripetute a {@link HCollection#add(Object)} aggiungano sequenzialmente in coda.
     * </p>
     * <p>
     * <b>Test Case Design</b> Categoria ripetute dell'append: dieci
     * inserzioni consecutive con verifica di taglia e posizione ad ogni
     * passo. Il numero elevato di iterazioni attraversa anche eventuali
     * crescite di capacità dell'array sottostante, quindi il test accerta
     * implicitamente che il ridimensionamento interno sia trasparente
     * all'osservatore. L'invariante verificato ({@code get(i)} pari
     * all'i-esimo inserito, size pari al numero di add) segue dai contratti
     * {@link HCollection#add(Object)} e {@link HList#get(int)}.
     * </p>
     * <p>
     * <b>Test Description</b> In un ciclo di dieci iterazioni si aggiungono
     * gli elementi "a0".."a9": dopo ogni inserimento le asserzioni
     * verificano che get(i) restituisca l'elemento appena aggiunto e che
     * size coincida con il numero di iterazioni completate.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v size 10.
     * </p>
     * <p>
     * <b>Expected Results</b> size 10, ordine.
     * </p>
     */
    @Test
    public void testAddObjectRepeatedSequential() {
        for (int i = 0; i < 10; i++) {
            assertTrue(v.add("a" + i));
            assertEquals(i + 1, v.size());
            assertEquals("a" + i, v.get(i));
        }
        assertEquals(10, v.size());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#addAll(HCollection)} aggiunga tutti gli elementi in coda nell'ordine dell'iteratore e restituisca true se modificato, false se la collezione e vuota.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale dell'unione bulk in coda con
     * entrambi gli esiti contrattuali. La collezione {@code [elem3,dup]}
     * contiene un elemento nuovo e uno già presente, cosi da dimostrare che
     * {@code addAll} non filtra i duplicati; il controllo posizionale
     * accerta l'ordine di trascrizione dell'iteratore della collezione
     * sorgente. Il doppio esito del valore di ritorno (true se la lista
     * cambia, false altrimenti) e prescritto dal contratto
     * {@link HCollection#addAll(HCollection)}, qui esercitato anche con
     * l'argomento vuoto che produce false senza modifiche. Complementa
     * {@link #testAddAllIndex()} per la variante posizionale.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore [elem1, elem2] si aggiunge la
     * collezione c = [elem3, dup]: addAll deve restituire true e produrre
     * la lista [elem1, elem2, elem3, dup] con size 4, verificata elemento
     * per elemento. Una seconda addAll con collezione vuota deve restituire
     * false senza modificare nulla.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2] size 2.
     * </p>
     * <p>
     * <b>Post-Condition</b> v con 4 elementi dopo primo addAll.
     * </p>
     * <p>
     * <b>Expected Results</b> true e ordine preservato, false su vuoto.
     * </p>
     */
    @Test
    public void testAddAll() {
        v.addElement(E1);
        v.addElement(E2);
        HCollection c = makeCollection(E3, DUP);
        boolean b = v.addAll(c);
        assertTrue(b);
        assertEquals(4, v.size());
        assertEquals(E3, v.get(2));
        assertEquals(DUP, v.get(3));
        HCollection empty = makeCollection();
        assertFalse(v.addAll(empty));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#addAll(HCollection)} con collezione vuota restituisca false e non modifichi come caso limite.
     * </p>
     * <p>
     * <b>Test Case Design</b> Valore di frontiera di {@code addAll}:
     * l'argomento collezione vuota, per cui l'operazione e un no-op che
     * restituisce false. Il test esercita la frontiera su due stati distinti
     * (lista con un elemento e lista vuota), dimostrando che il risultato
     * dipende solo dall'argomento e non dallo stato; la verifica della
     * taglia post-chiamata esclude effetti collaterali. La semantica discende
     * dal contratto {@link HCollection#addAll(HCollection)}, che lega il
     * ritorno all'avvenuta modifica della lista.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore contenente il solo elem1 si invoca
     * addAll con una collezione vuota: l'esito deve essere false e lo stato
     * del vettore (size 1) deve restare invariato.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1].
     * </p>
     * <p>
     * <b>Post-Condition</b> v=[elem1] invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> false, size 1.
     * </p>
     */
    @Test
    public void testAddAllWithEmptyCollectionNoOp() {
        v.addElement(E1);
        HCollection empty = makeCollection();
        assertFalse(v.addAll(empty));
        assertEquals(1, v.size());
        VectorAdapter vEmpty = new VectorAdapter();
        assertFalse(vEmpty.addAll(empty));
        assertEquals(0, vEmpty.size());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che chiamate ripetute a {@link HCollection#addAll(HCollection)} accumulino correttamente gli elementi.
     * </p>
     * <p>
     * <b>Test Case Design</b> Categoria ripetute dell'unione bulk: tre
     * {@code addAll} consecutivi con collezioni di taglie diverse (1, 2, 1)
     * accumulati sulla stessa lista. La sequenza verifica l'accumulo
     * progressivo in coda e l'ordine complessivo dei cinque elementi
     * risultanti, controllando le posizioni chiave dopo ogni fase.
     * L'ultimo {@code addAll} reinserisce elem1, già presente, confermando
     * che operazioni successive non alterano gli elementi preesistenti.
     * L'attesa di accumulo in ordine segue dal contratto
     * {@link HCollection#addAll(HCollection)}.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore contenente il solo elem1 si
     * aggiungono in sequenza due collezioni: prima c1 = [elem2], che porta
     * la size a 2, poi c2 = [elem3, dup], che porta la size a 4. Le
     * asserzioni verificano l'accumulo nell'ordine di inserimento.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1].
     * </p>
     * <p>
     * <b>Post-Condition</b> v=[elem1,elem2,elem3,dup].
     * </p>
     * <p>
     * <b>Expected Results</b> size 4, ordine.
     * </p>
     */
    @Test
    public void testAddAllRepeatedSequential() {
        v.addElement(E1);
        HCollection c1 = makeCollection(E2);
        assertTrue(v.addAll(c1));
        assertEquals(2, v.size());
        HCollection c2 = makeCollection(E3, DUP);
        assertTrue(v.addAll(c2));
        assertEquals(4, v.size());
        assertEquals(E2, v.get(1));
        assertEquals(E3, v.get(2));
        assertEquals(DUP, v.get(3));
        HCollection c3 = makeCollection(E1);
        assertTrue(v.addAll(c3));
        assertEquals(5, v.size());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#addAll(HCollection)} e {@link HList#addAll(int,HCollection)} lancino {@link NullPointerException} se la collezione e null.
     * </p>
     * <p>
     * <b>Test Case Design</b> Classe di errore dell'argomento null per
     * entrambe le forme di {@code addAll}. Le due firme (mono-argomento e
     * posizionale) condividono il medesimo obbligo contrattuale:
     * {@link HCollection#addAll(HCollection)} e
     * {@link HList#addAll(int,HCollection)} prescrivono
     * {@link NullPointerException} quando la collezione specificata e null,
     * coerentemente con la politica generale delle operazioni bulk.
     * L'indice 0 scelto per la forma posizionale e valido, cosi da isolare
     * il null come unica causa dell'eccezione ed evitare interferenze con
     * i controlli di bounds.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore [elem1, elem2] si tentano
     * entrambe le forme con argomento nullo: addAll(null) per la versione
     * ereditata da HCollection e addAll(0, null) per quella posizionale di
     * HList. Entrambe devono lanciare NullPointerException.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> NullPointerException in entrambi i casi.
     * </p>
     */
    @Test
    public void testAddAllNullThrowsNPE() {
        v.addElement(E1);
        try {
            v.addAll(null);
            fail("addAll(null) deve lanciare NullPointerException");
        } catch (NullPointerException e) { /* atteso */ }
        try {
            v.addAll(0, null);
            fail("addAll(0,null) deve lanciare NullPointerException");
        } catch (NullPointerException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#remove(Object)} rimuova la prima occorrenza, restituisca true se presente, false altrimenti e gestisca null.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale della rimozione per valore con
     * tutti e tre gli esiti osservabili: rimozione riuscita (true),
     * assenza dell'elemento (false) e gestione del null. Il dato
     * {@code [elem1,elem2,elem3]} con rimozione di elem2 permette di
     * controllare lo shift sinistro tramite la taglia risultante; il caso
     * missing dimostra che un esito negativo non altera lo stato,
     * proprieta richiesta dal contratto {@link HCollection#remove(Object)}.
     * La parte finale copre la classe null, rimossa correttamente dopo
     * inserimento esplicito. Complementa {@link #testRemoveIndex()}, che
     * opera per posizione e restituisce l'elemento.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore a tre elementi remove("elem2")
     * deve restituire true e lasciare [elem1, elem3]; remove("missing"), su
     * un valore assente, deve restituire false senza modifiche. Infine,
     * dopo aver aggiunto null con add(null), anche remove(null) deve
     * restituire true rimuovendolo.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2,elem3].
     * </p>
     * <p>
     * <b>Post-Condition</b> v=[elem1,elem3] dopo primo remove (poi con null test).
     * </p>
     * <p>
     * <b>Expected Results</b> true per presente, false per assente, gestione null corretta.
     * </p>
     */
    @Test
    public void testRemoveObject() {
        populateThree();
        assertTrue(v.remove(E2));
        assertEquals(2, v.size());
        assertFalse(v.contains(E2));
        assertFalse(v.remove("missing"));
        assertEquals(2, v.size());
        v.add(null);
        assertTrue(v.contains(null));
        assertTrue(v.remove(null));
        assertFalse(v.contains(null));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#removeAll(HCollection)} rimuova tutti gli elementi contenuti nella collezione specificata e restituisca true se il vettore e stato modificato.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale della differenza insiemistica
     * con entrambi gli esiti del boolean. La collezione
     * {@code [elem1,elem2]} interseca parzialmente il vettore, quindi la
     * rimozione deve eliminare tutte le occorrenze coinvolte preservando
     * elem3; il secondo invoco con {@code [missing]} rappresenta la classe
     * senza intersezione, per cui il contratto
     * {@link HCollection#removeAll(HCollection)} prescrive false e stato
     * invariato. La coppia di esiti copre entrambe le clausole del valore
     * di ritorno. Complementa {@link #testRemoveAllDuplicatesInVector()}
     * per i vettori con occorrenze ripetute.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore a tre elementi removeAll con
     * c = [elem1, elem2] deve restituire true e lasciare il solo [elem3]
     * con size 1. Una seconda removeAll con c = [missing], priva di
     * corrispondenze, deve restituire false senza modificare il vettore.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v con 3 elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> v=[elem3] dopo primo removeAll.
     * </p>
     * <p>
     * <b>Expected Results</b> true con match, v size 1, false senza match.
     * </p>
     */
    @Test
    public void testRemoveAll() {
        populateThree();
        HCollection c = makeCollection(E1, E2);
        assertTrue(v.removeAll(c));
        assertEquals(1, v.size());
        assertEquals(E3, v.get(0));
        HCollection c2 = makeCollection("missing");
        assertFalse(v.removeAll(c2));
        assertEquals(1, v.size());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#removeAll(HCollection)} con vettore vuoto o collezione vuota gestisca correttamente i limiti.
     * </p>
     * <p>
     * <b>Test Case Design</b> Valori di frontiera di {@code removeAll}
     * nelle due configurazioni degeneri: vettore vuoto con argomento non
     * vuoto, e vettore non vuoto con argomento vuoto. In entrambi i casi
     * l'intersezione tra gli elementi da eliminare e quelli presenti e
     * nulla, quindi l'esito atteso e false con stato invariato secondo il
     * contratto {@link HCollection#removeAll(HCollection)}; le due
     * configurazioni individuano pero classi di equivalenza distinte
     * (assenza di candidati vs assenza di bersagli). Complementa
     * {@link #testRemoveAll()}, che lavora su stati pieni.
     * </p>
     * <p>
     * <b>Test Description</b> Due casi limite devono entrambi restituire
     * false: sul vettore vuoto removeAll([elem1]) non ha nulla da rimuovere,
     * e sul vettore [elem1] removeAll(collezione vuota) non individua
     * elementi da eliminare. In entrambi i casi la size resta invariata.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto poi con 1.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> false, size invariato.
     * </p>
     */
    @Test
    public void testRemoveAllOnEmptyList() {
        HCollection c = makeCollection(E1);
        assertFalse(v.removeAll(c));
        assertEquals(0, v.size());
        v.addElement(E1);
        HCollection empty = makeCollection();
        assertFalse(v.removeAll(empty));
        assertEquals(1, v.size());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#removeAll(HCollection)} rimuova tutte le occorrenze di un elemento duplicato nel vettore, non solo la prima.
     * </p>
     * <p>
     * <b>Test Case Design</b> Bulk con duplicati nel vettore: removeAll deve
     * eliminare ogni occorrenza degli elementi contenuti in c, a differenza
     * di remove(Object) che ferma la prima.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore [elem1, elem1, elem2], che
     * contiene due occorrenze di elem1, si invoca removeAll con c =
     * [elem1]: deve restituire true ed eliminare entrambe le occorrenze,
     * lasciando il solo [elem2] con size 1.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v con duplicati di elem1.
     * </p>
     * <p>
     * <b>Post-Condition</b> v=[elem2].
     * </p>
     * <p>
     * <b>Expected Results</b> true, tutte le occorrenze di elem1 rimosse.
     * </p>
     */
    @Test
    public void testRemoveAllDuplicatesInVector() {
        v.addElement(E1);
        v.addElement(E1);
        v.addElement(E2);
        HCollection c = makeCollection(E1);
        assertTrue(v.removeAll(c));
        assertEquals(1, v.size());
        assertEquals(E2, v.get(0));
        assertFalse(v.contains(E1));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#removeAll(HCollection)} invocata con una copia clonata del vettore stesso svuoti completamente il vettore senza corromperne lo stato.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso auto-referenziale via oggetto distinto:
     * l'argomento e un clone (contenuto uguale, istanza diversa), quindi
     * le contains durante l'iterazione interna non sono invalidate dalle
     * rimozioni progressive su v.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore a tre elementi si crea una copia
     * indipendente tramite clone() e la si passa a removeAll: ogni elemento
     * del vettore è contenuto nella copia, quindi l'esito è true e il
     * vettore resta vuoto (size 0, isEmpty true), mentre il clone conserva
     * intatti i suoi tre elementi.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v con 3 elementi, c clone indipendente di v.
     * </p>
     * <p>
     * <b>Post-Condition</b> v vuoto, c con 3 elementi.
     * </p>
     * <p>
     * <b>Expected Results</b> true, v svuotato completamente.
     * </p>
     */
    @Test
    public void testRemoveAllViaSelfReferentialClone() {
        populateThree();
        HCollection c = (HCollection) v.clone();
        assertTrue(v.removeAll(c));
        assertEquals(0, v.size());
        assertTrue(v.isEmpty());
        assertEquals(3, c.size());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#retainAll(HCollection)} conservi solo gli elementi contenuti nella collezione specificata e restituisca true se modificato.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale dell'intersezione insiemistica
     * con entrambi gli esiti del boolean. La collezione
     * {@code [elem1,elem2]} esprime un sottoinsieme proprio dei tre
     * elementi, quindi {@code retainAll} deve eliminare il solo elem3
     * preservando l'ordine dei retenti; l'invoco successivo con una
     * collezione di contenuto identico rappresenta la classe no-op, per cui
     * il contratto {@link HCollection#retainAll(HCollection)} prescrive
     * false quando la lista non subisce modifiche. Complementa
     * {@link #testRemoveAll()}, che realizza la differenza invece
     * dell'intersezione.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore a tre elementi retainAll con
     * c = [elem1, elem2] deve restituire true e conservare i soli [elem1,
     * elem2] con size 2. Una seconda retainAll eseguita passando una copia
     * del vettore stesso non deve modificare nulla e restituire false.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2,elem3].
     * </p>
     * <p>
     * <b>Post-Condition</b> v=[elem1,elem2] dopo retain.
     * </p>
     * <p>
     * <b>Expected Results</b> true con modifica, v size 2, false se già uguale.
     * </p>
     */
    @Test
    public void testRetainAll() {
        populateThree();
        HCollection c = makeCollection(E1, E2);
        assertTrue(v.retainAll(c));
        assertEquals(2, v.size());
        assertTrue(v.contains(E1));
        assertTrue(v.contains(E2));
        assertFalse(v.contains(E3));
        HCollection same = makeCollection(E1, E2);
        assertFalse(v.retainAll(same));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#retainAll(HCollection)} preservi le occorrenze duplicate presenti nel vettore e restituisca false se il contenuto non cambia.
     * </p>
     * <p>
     * <b>Test Case Design</b> Bulk con duplicati nel vettore: retainAll deve
     * mantenere tutti i duplicati degli elementi retenti; se il contenuto
     * resta identico il risultato e false anche quando c esprime molteplicita
     * diverse.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore [elem1, elem1, elem2] retainAll
     * con c1 = [elem1] deve restituire true e preservare entrambe le
     * occorrenze di elem1, lasciando [elem1, elem1]. La successiva
     * retainAll con c2 = [elem1, elem2] trova tutti gli elementi già
     * presenti, quindi restituisce false senza modificare nulla.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v con duplicati di elem1.
     * </p>
     * <p>
     * <b>Post-Condition</b> v=[elem1,elem1] invariato dalla seconda retain.
     * </p>
     * <p>
     * <b>Expected Results</b> prima retain true con duplicati preservati,
     * seconda retain false.
     * </p>
     */
    @Test
    public void testRetainAllPreservesDuplicates() {
        v.addElement(E1);
        v.addElement(E1);
        v.addElement(E2);
        HCollection c1 = makeCollection(E1);
        assertTrue(v.retainAll(c1));
        assertEquals(2, v.size());
        assertEquals(E1, v.get(0));
        assertEquals(E1, v.get(1));
        assertFalse(v.contains(E2));
        HCollection c2 = makeCollection(E1, E2);
        assertFalse(v.retainAll(c2));
        assertEquals(2, v.size());
        assertEquals(E1, v.get(0));
        assertEquals(E1, v.get(1));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#retainAll(HCollection)} con collezione vuota svuoti il vettore e su vettore vuoto resti vuoto.
     * </p>
     * <p>
     * <b>Test Case Design</b> Valore di frontiera di {@code retainAll}:
     * l'argomento collezione vuota, per cui l'intersezione e vuota e ogni
     * elemento va rimosso. Il caso costituisce il duale del no-op osservato
     * in {@link #testAddAllWithEmptyCollectionNoOp()}: qui il ritorno
     * atteso e true con svuotamento completo, mentre il secondo invoco sul
     * vettore ormai vuoto cade nella classe no-op (false), chiudendo
     * entrambe le clausole contrattuali di
     * {@link HCollection#retainAll(HCollection)} in un'unica sequenza.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore [elem1, elem2] retainAll con
     * collezione vuota deve svuotarlo completamente restituendo true;
     * invocata nuovamente sul vettore ormai vuoto deve restituire false,
     * perché nessun elemento viene più rimosso.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2].
     * </p>
     * <p>
     * <b>Post-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> true e size0, poi false.
     * </p>
     */
    @Test
    public void testRetainAllOnEmptyListEmpties() {
        v.addElement(E1);
        v.addElement(E2);
        HCollection empty = makeCollection();
        assertTrue(v.retainAll(empty));
        assertEquals(0, v.size());
        assertTrue(v.isEmpty());
        assertFalse(v.retainAll(empty));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#retainAll(HCollection)} con la collezione stessa come argomento
     * non modifichi il vettore e restituisca false.
     * </p>
     * <p>
     * <b>Test Case Design</b> L'auto-riferimento in {@code retainAll} e un
     * edge case: tutti gli elementi del vettore sono contenuti nella collezione
     * sorgente (che e lo stesso vettore), quindi nulla viene rimosso e il
     * metodo deve restituire false. Complementa {@link #testRetainAll()} e
     * {@link #testRetainAllPreservesDuplicates()} che testano retainAll con
     * collezioni esterne.
     * </p>
     * <p>
     * <b>Test Description</b> Si popola {@code v} con [E1, E2, E3] e si
     * invoca {@code v.retainAll(v)}. Poiche tutti gli elementi di v sono
     * contenuti in v stesso, il vettore non viene modificato e il metodo
     * restituisce false.
     * </p>
     * <p>
     * <b>Pre-Condition</b> {@code v = [E1, E2, E3]}, size = 3.
     * </p>
     * <p>
     * <b>Post-Condition</b> {@code v = [E1, E2, E3]}, size = 3, invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> {@code retainAll} restituisce false; size = 3;
     * contenuto invariato.
     * </p>
     */
    @Test
    public void testRetainAllOnSelf() {
        populateThree();
        assertFalse(v.retainAll(v));
        assertEquals(3, v.size());
        assertEquals(E1, v.get(0));
        assertEquals(E2, v.get(1));
        assertEquals(E3, v.get(2));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#clear()} rimuova tutti gli elementi lasciando size 0 e capacity invariata.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale dello svuotamento totale con
     * verifica della politica della capacità. {@code clear()} deve azzerare
     * la taglia senza ridurre l'array interno, comportamento che distingue
     * l'operazione da un eventuale trimToSize; la coppia di asserzioni
     * size/capacity implementa quindi entrambi gli aspetti del contratto
     * {@link HCollection#clear()} e della semantica Vector. La chiamata
     * {@code get(0)} finale conferma lo stato vuoto attraverso la frontiera
     * degli accessi ammessi.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore a tre elementi (capacity 10)
     * clear() deve riportare size a 0 con isEmpty true, mantenendo la
     * capacità strutturale a 10. La lettura get(0) sulla lista ormai
     * priva di elementi deve infine lanciare IndexOutOfBoundsException.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v con 3 elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> v vuoto size 0.
     * </p>
     * <p>
     * <b>Expected Results</b> size 0, isEmpty true, capacity 10.
     * </p>
     */
    @Test
    public void testClear() {
        populateThree();
        int capBefore = v.capacity();
        v.clear();
        assertEquals(0, v.size());
        assertTrue(v.isEmpty());
        assertEquals(capBefore, v.capacity());
        try {
            v.get(0);
            fail("get(0) su vuoto deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#clear()} su vettore vuoto sia idempotente e mantenga capacity come caso limite.
     * </p>
     * <p>
     * <b>Test Case Design</b> Stato limite e idempotenza di {@code clear()}:
     * invocazione su contenitore già vuoto. Il clear su lista vuota e un
     * no-op ammesso dal contratto {@link HCollection#clear()}, che non
     * prevede eccezioni per lo stato degenere; la verifica accerta che
     * l'operazione sia idempotente e priva di effetti collaterali sulla
     * capacità. Complementa {@link #testClear()}, che parte da stato
     * popolato.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore già vuoto clear() deve lasciare
     * size 0 e capacity 10 invariata; una seconda clear() conferma
     * l'idempotenza dell'operazione senza alcun effetto collaterale.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> size0, capacity10.
     * </p>
     */
    @Test
    public void testClearOnEmptyListIdempotent() {
        int cap = v.capacity();
        v.clear();
        assertEquals(0, v.size());
        assertTrue(v.isEmpty());
        assertEquals(cap, v.capacity());
        v.clear();
        assertEquals(0, v.size());
        assertEquals(cap, v.capacity());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che chiamate ripetute a {@link HCollection#clear()} siano idempotenti e ripetibili.
     * </p>
     * <p>
     * <b>Test Case Design</b> Categoria ripetute dello svuotamento: due
     * cicli completi popolamento-svuotamento sulla stessa istanza. La
     * sequenza accerta che {@code clear()} riporti il vettore in uno stato
     * pienamente riutilizzabile, sul quale le operazioni successive si
     * comportano come dopo la costruzione; questa proprieta di riuso segue
     * dai contratti {@link HCollection#clear()} e
     * {@link HCollection#add(Object)}, ed e complementare a
     * {@link #testClearOnEmptyListIdempotent()}, limitato al versante
     * vuoto.
     * </p>
     * <p>
     * <b>Test Description</b> Per tre cicli consecutivi il vettore viene
     * riempito con tre elementi tramite populateThree() e poi svuotato con
     * clear(): al termine di ogni ciclo le asserzioni verificano size 0,
     * a testimonianza della ripetibilità dell'operazione.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v con 3.
     * </p>
     * <p>
     * <b>Post-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> size0 dopo ogni clear.
     * </p>
     */
    @Test
    public void testClearRepeatedDoubleClear() {
        for (int i = 0; i < 3; i++) {
            populateThree();
            assertEquals(3, v.size());
            v.clear();
            assertEquals(0, v.size());
            v.clear();
            assertEquals(0, v.size());
        }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#removeAll(HCollection)} e {@link HCollection#retainAll(HCollection)} lancino {@link NullPointerException} se l'argomento e null.
     * </p>
     * <p>
     * <b>Test Case Design</b> Classe di errore dell'argomento null condivisa
     * dalle due operazioni bulk duali. Sia {@code removeAll} sia
     * {@code retainAll} iterano l'argomento per valutare la relazione di
     * appartenenza, quindi il contratto {@link HCollection} prescrive
     * {@link NullPointerException} per argomento null in entrambe;
     * testarle insieme documenta la simmetria delle politiche di
     * validazione. Complementa {@link #testAddAllNullThrowsNPE()} per le
     * operazioni di inserimento bulk.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore a tre elementi si invocano
     * removeAll(null) e retainAll(null), entrambe con argomento nullo in
     * violazione della precondizione del contratto HCollection: ognuna
     * delle due deve lanciare NullPointerException.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> NullPointerException.
     * </p>
     */
    @Test
    public void testRemoveAllRetainAllNullThrowsNPE() {
        populateThree();
        try {
            v.removeAll(null);
            fail("removeAll(null) deve lanciare NullPointerException");
        } catch (NullPointerException e) { /* atteso */ }
        try {
            v.retainAll(null);
            fail("retainAll(null) deve lanciare NullPointerException");
        } catch (NullPointerException e) { /* atteso */ }
    }

    // D) Ricerca ed eguaglianza + toArray + ricerca 2-arg (19)
    // ====================================================================

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#toArray()} restituisca un nuovo array con gli elementi nell'ordine del vettore, sia per vettore vuoto che non vuoto.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale della conversione in array con
     * verifica dell'indipendenza dalla lista. Il contratto
     * {@link HCollection#toArray()} prescrive un array di nuova allocazione
     * che il chiamante puo modificare liberamente senza ripercussioni sul
     * vettore; la mutazione dell'elemento estratto implementa proprio
     * questa clausola. Gli stati vuoto (array di lunghezza 0) e popolato
     * coprono entrambe le classi di equivalenza della taglia.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore vuoto toArray() deve restituire un
     * array di lunghezza 0; dopo populateThree() deve restituire un array
     * di tre posizioni con arr[0] = elem1, arr[1] = elem2 e arr[2] = elem3,
     * nell'ordine della lista. La modifica dell'array ottenuto non deve
     * ripercuotersi sul vettore, a conferma che si tratta di una copia nuova.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto poi con 3 elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Array corretto, nuovo allocato.
     * </p>
     */
    @Test
    public void testToArray() {
        Object[] arr0 = v.toArray();
        assertEquals(0, arr0.length);
        populateThree();
        Object[] arr = v.toArray();
        assertEquals(3, arr.length);
        assertEquals(E1, arr[0]);
        assertEquals(E2, arr[1]);
        assertEquals(E3, arr[2]);
        arr[0] = "mod";
        assertEquals(E1, v.get(0));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#toArray(Object[])} allochi un nuovo array quando quello fornito e troppo piccolo.
     * </p>
     * <p>
     * <b>Test Case Design</b> Classe di equivalenza dell'argomento troppo
     * piccolo: {@code a.length &lt; size}. Il contratto
     * {@link HCollection#toArray(Object[])} prescrive in questo ramo
     * l'allocazione di un nuovo array di taglia size, dato che CLDC non
     * offre la reflection necessaria ad allargare l'array del tipo runtime
     * fornito; il controllo {@code assertNotSame} distingue quindi il ramo
     * di riallocazione dal riutilizzo osservabile nel caso larger.
     * L'array fornito resta invariato, come richiede il contratto.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore a tre elementi si invoca
     * toArray(a) passando l'array a = new Object[1], troppo piccolo:
     * il metodo deve allocare un nuovo array r di lunghezza 3 contenente
     * elem1, elem2 ed elem3 in ordine, con r istanza diversa da quella
     * fornita.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v size 3, a length 1.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato, a invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> r.length 3, contenuto corretto, r != a.
     * </p>
     */
    @Test
    public void testToArrayWithSmallerArray() {
        populateThree();
        Object[] a = new Object[1];
        Object[] r = v.toArray(a);
        assertEquals(3, r.length);
        assertNotSame(a, r);
        assertEquals(E1, r[0]);
        assertEquals(E2, r[1]);
        assertEquals(E3, r[2]);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#toArray(Object[])} riutilizzi l'array fornito quando sufficientemente grande e ponga il terminatore null in posizione size.
     * </p>
     * <p>
     * <b>Test Case Design</b> Classe di equivalenza dell'argomento
     * sufficientemente grande: {@code a.length &gt; size}. Il contratto
     * {@link HCollection#toArray(Object[])} prescrive in questo ramo il
     * riutilizzo dell'array fornito (medesima istanza ritornata), la copia
     * degli elementi in testa e l'inserimento del terminatore null nella
     * posizione {@code size}, clausola pensata per delimitare la porzione
     * valida; il valore sentinella in coda accerta inoltre che le posizioni
     * oltre size non vengano toccate.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore a tre elementi si invoca
     * toArray(a) con a = new Object[5], preimpostato con la sentinella
     * "sentinel" nell'ultima posizione: il metodo deve restituire la stessa
     * istanza, copiare gli elementi nelle posizioni da 0 a 2, porre il
     * terminatore null in posizione 3 e lasciare intatta la sentinella
     * in posizione 4.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v size 3, a length 5.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato, a modificato con elementi + null.
     * </p>
     * <p>
     * <b>Expected Results</b> r==a, a[3]==null, elementi copiati.
     * </p>
     */
    @Test
    public void testToArrayWithLargerArray() {
        populateThree();
        Object[] a = new Object[5];
        a[4] = "sentinel";
        Object[] r = v.toArray(a);
        assertSame(a, r);
        assertEquals(E1, a[0]);
        assertEquals(E2, a[1]);
        assertEquals(E3, a[2]);
        assertNull(a[3]);
        assertEquals("sentinel", a[4]);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#toArray(Object[])} lanci {@link NullPointerException} se l'array fornito e null.
     * </p>
     * <p>
     * <b>Test Case Design</b> Classe di errore dell'argomento null per
     * {@code toArray(Object[])}. Il contratto
     * {@link HCollection#toArray(Object[])} prescrive
     * {@link NullPointerException} quando l'array specificato e null,
     * indipendentemente dallo stato della lista; la lista non vuota scelta
     * come pre-condizione evita che l'esito possa dipendere da una
     * scorciatoia sul contenuto.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore contenente il solo elem1 si
     * invoca toArray(null): l'argomento nullo viola la precondizione del
     * contratto HCollection e deve lanciare NullPointerException.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> NullPointerException.
     * </p>
     */
    @Test(expected = NullPointerException.class)
    public void testToArrayNullThrowsNPE() {
        v.addElement(E1);
        v.toArray(null);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che chiamate ripetute a {@link HCollection#toArray()} restituiscano array indipendenti.
     * </p>
     * <p>
     * <b>Test Case Design</b> Categoria ripetute sulla conversione: due
     * invochi consecutivi di {@code toArray()} sulla stessa lista. La
     * coppia deve produrre istanze distinte ma con contenuto identico,
     * proprieta che segue dal contratto {@link HCollection#toArray()}
     * (nuova allocazione a ogni chiamata); la modifica successiva del primo
     * array dimostra l'assenza di condivisione sia con la lista sia con il
     * secondo array.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore [elem1, elem2] si invocano due
     * volte toArray(): i due array restituiti devono essere istanze
     * distinte ma con lo stesso contenuto; la modifica del primo non deve
     * riflettersi né sul vettore né sul secondo array.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2].
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> arr distinti, indipendenti.
     * </p>
     */
    @Test
    public void testToArrayRepeatedIndependence() {
        v.addElement(E1);
        v.addElement(E2);
        Object[] a1 = v.toArray();
        Object[] a2 = v.toArray();
        assertNotSame(a1, a2);
        assertEquals(E1, a1[0]);
        assertEquals(E1, a2[0]);
        a1[0] = "mod";
        assertEquals(E1, v.get(0));
        assertEquals(E1, a2[0]);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#toArray(Object[])} con array di lunghezza esattamente size riutilizzi l'array senza terminatore null.
     * </p>
     * <p>
     * <b>Test Case Design</b> Valore di frontiera esatto di
     * {@code toArray(Object[])}: {@code a.length == size}, punto di
     * raccordo tra i due rami dell'operazione. In questa configurazione
     * l'array viene riutilizzato (come nel caso larger) ma nessuna posizione
     * eccedente esiste, quindi il terminatore null non e osservabile; il
     * contratto {@link HCollection#toArray(Object[])} colloca qui il
     * confine tra riallocazione e riutilizzo, rendendo il caso
     * discriminante per l'implementazione. Complementa
     * {@link #testToArrayWithLargerArray()}, che usa un array piu grande.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore a tre elementi si invoca
     * toArray(a) con a = new Object[3], di lunghezza esattamente pari alla
     * size: il metodo deve restituire la stessa istanza riempita con
     * elem1, elem2 ed elem3, senza inserire alcun terminatore null.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v size3, a length3.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato, a riempito.
     * </p>
     * <p>
     * <b>Expected Results</b> r==a, contenuto corretto.
     * </p>
     */
    @Test
    public void testToArrayWithLargerArrayExactSize() {
        populateThree();
        Object[] a = new Object[3];
        Object[] r = v.toArray(a);
        assertSame(a, r);
        assertEquals(E1, a[0]);
        assertEquals(E2, a[1]);
        assertEquals(E3, a[2]);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#indexOf(Object)} e {@link HList#lastIndexOf(Object)} restituiscano la prima e l'ultima occorrenza gestendo correttamente duplicati, assenti e null.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale delle due ricerche lineari con
     * dati ricchi di occorrenze multiple. La sequenza
     * {@code [elem1,dup,elem2,dup,elem1]} presenta duplicati sia contigui
     * sia agli estremi, cosi da discriminare prima e ultima occorrenza per
     * entrambi i metodi; gli argomenti assenti restituiscono -1 secondo i
     * contratti {@link HList#indexOf(Object)} e
     * {@link HList#lastIndexOf(Object)}. Il null inserito in coda esercita
     * il ramo {@code (o==null ? e==null : o.equals(e))} previsto dai
     * medesimi contratti.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore [elem1, dup, elem2, dup, elem1],
     * con entrambi i valori presenti in più copie, indexOf("dup") deve
     * individuare la prima occorrenza in posizione 1 e lastIndexOf("dup")
     * l'ultima in posizione 3, mentre indexOf("missing") restituisce -1.
     * Dopo add(null), anche la ricerca di null trova corrispondenza nella
     * posizione 5.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,dup,elem2,dup,elem1].
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> indexOf/lastIndexOf corretti, -1 per assente.
     * </p>
     */
    @Test
    public void testIndexOfAndLastIndexOf() {
        v.addElement(E1);
        v.addElement(DUP);
        v.addElement(E2);
        v.addElement(DUP);
        v.addElement(E1);
        assertEquals(1, v.indexOf(DUP));
        assertEquals(3, v.lastIndexOf(DUP));
        assertEquals(0, v.indexOf(E1));
        assertEquals(4, v.lastIndexOf(E1));
        assertEquals(-1, v.indexOf("missing"));
        assertEquals(-1, v.lastIndexOf("missing"));
        v.add(null);
        assertEquals(5, v.indexOf(null));
        assertEquals(5, v.lastIndexOf(null));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#indexOf(Object)} su vettore vuoto e singolo gestisca correttamente i limiti.
     * </p>
     * <p>
     * <b>Test Case Design</b> Valori di frontiera di {@code indexOf} sugli
     * stati degeneri vuoto e singolo. Sul vuoto la ricerca non ha candidati
     * e il contratto {@link HList#indexOf(Object)} prescrive -1 per
     * qualunque argomento, incluso null; il passaggio alla taglia 1
     * introduce la frontiera inferiore dell'intervallo di ricerca e permette
     * di distinguere presente (0) da assente (-1). Complementa
     * {@link #testIndexOfAndLastIndexOf()}, che lavora su stati piu
     * popolati.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore vuoto indexOf("elem1") deve
     * restituire -1. Sul vettore ridotto al solo elem1 la stessa ricerca ha
     * esito 0, mentre indexOf("missing") e indexOf(null) restituiscono -1
     * per assenza di corrispondenze.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto poi singolo.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> -1 su vuoto, 0 su singolo presente.
     * </p>
     */
    @Test
    public void testIndexOfOnEmptyAndSingleElement() {
        assertEquals(-1, v.indexOf(E1));
        assertEquals(-1, v.indexOf(null));
        v.addElement(E1);
        assertEquals(0, v.indexOf(E1));
        assertEquals(-1, v.indexOf(E2));
        assertEquals(-1, v.indexOf(null));
        v.add(null);
        assertEquals(1, v.indexOf(null));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#lastIndexOf(Object)} su vettore vuoto e singolo gestisca correttamente i limiti.
     * </p>
     * <p>
     * <b>Test Case Design</b> Valori di frontiera di {@code lastIndexOf}
     * sugli stati degeneri vuoto e singolo, duale del test precedente per la
     * direzione di scansione. Con taglia 0 o 1 prima e ultima occorrenza
     * coincidono, quindi l'esito atteso (-1 sul vuoto, 0 sul presente)
     * coincide con quello di indexOf; il contratto
     * {@link HList#lastIndexOf(Object)} resta comunque distinto perche la
     * direzione diventa osservabile solo con occorrenze multiple, coperte in
     * {@link #testIndexOfAndLastIndexOf()}.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore vuoto lastIndexOf("dup") deve
     * restituire -1. Sul vettore ridotto al solo dup la stessa ricerca ha
     * esito 0, mentre lastIndexOf("missing") resta -1.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto poi singolo.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> -1 vuoto, 0 singolo.
     * </p>
     */
    @Test
    public void testLastIndexOfOnEmptyAndSingleElement() {
        assertEquals(-1, v.lastIndexOf(DUP));
        assertEquals(-1, v.lastIndexOf(null));
        v.addElement(DUP);
        assertEquals(0, v.lastIndexOf(DUP));
        assertEquals(-1, v.lastIndexOf(E1));
        assertEquals(-1, v.lastIndexOf(null));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che chiamate ripetute a {@link HList#indexOf(Object)} con duplicati restituiscano sempre la prima occorrenza.
     * </p>
     * <p>
     * <b>Test Case Design</b> Categoria ripetute sulla ricerca con
     * occorrenze duplicate: invochi consecutivi di {@code indexOf} su uno
     * stato immutato, seguiti da una rimozione che sposta la prima
     * occorrenza. La ripetizione accerta il determinismo del risultato
     * rispetto allo stesso stato, mentre la rimozione verifica che
     * l'indice restituito si aggiorni coerentemente con la mutazione, come
     * richiesto dalla semantica posizionale del contratto
     * {@link HList#indexOf(Object)}.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore [dup, elem1, dup] indexOf("dup")
     * viene richiamato tre volte restituendo sempre 0. Dopo remove(0),
     * che elimina la prima occorrenza, la stessa ricerca deve restituire 1:
     * l'esito segue lo spostamento delle occorrenze residue.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[dup,elem1,dup].
     * </p>
     * <p>
     * <b>Post-Condition</b> v=[elem1,dup] dopo remove.
     * </p>
     * <p>
     * <b>Expected Results</b> 0 ripetuto, poi 1.
     * </p>
     */
    @Test
    public void testIndexOfRepeatedWithDuplicates() {
        v.addElement(DUP);
        v.addElement(E1);
        v.addElement(DUP);
        for (int i = 0; i < 3; i++) assertEquals(0, v.indexOf(DUP));
        v.remove(0);
        assertEquals(1, v.indexOf(DUP));
        assertEquals(0, v.indexOf(E1));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#indexOf(Object,int)} cerchi dalla posizione specificata e gestisca indici negativi e oltre size secondo la semantica J2SE 1.4.2.
     * </p>
     * <p>
     * <b>Test Case Design</b> J2SE 1.4.2 specifica per indexOf 2-arg:
     * index&lt;0 lancia IndexOutOfBoundsException, index&gt;=size ritorna -1.
     * CLDC non documenta e l implementazione SE corrente lancia
     * ArrayIndexOutOfBounds per index&lt;0. Test verifica comportamento
     * reale delegato a Vector.indexOf con valori concreti.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore [elem1, elem2, elem1, dup] la
     * ricerca a due argomenti parte dalla posizione indicata:
     * indexOf("elem1", 0) trova la prima occorrenza in 0, indexOf("elem1",
     * 1) salta alla successiva in 2 e indexOf("elem1", 3) esaurisce le
     * corrispondenze restituendo -1. Un indice negativo (-5) deve lanciare
     * IndexOutOfBoundsException secondo la semantica J2SE 1.4.2, mentre un
     * indice oltre size (100) restituisce -1. Aggiunto null in coda,
     * anche indexOf(null, ...) viene verificato nelle posizioni 0 e 5.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2,elem1,dup] size 4.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato (poi con null aggiunto).
     * </p>
     * <p>
     * <b>Expected Results</b> 0 con index 0, 2 con index 1, -1 con index oltre size/assente, IndexOutOfBounds con index negativo.
     * </p>
     */
    @Test
    public void testIndexOfWithIndex() {
        v.addElement(E1);
        v.addElement(E2);
        v.addElement(E1);
        v.addElement(DUP);
        assertEquals(0, v.indexOf(E1, 0));
        assertEquals(2, v.indexOf(E1, 1));
        assertEquals(-1, v.indexOf(E1, 3));
        try {
            v.indexOf(E1, -5);
            fail("indexOf con index negativo deve lanciare IndexOutOfBoundsException (J2SE 1.4.2)");
        } catch (IndexOutOfBoundsException e) { /* atteso  */ }
        assertEquals(-1, v.indexOf("missing", 0));
        assertEquals(-1, v.indexOf(E1, 100));
        // con null
        v.add(null);
        assertEquals(4, v.indexOf(null, 0));
        assertEquals(-1, v.indexOf(null, 5));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#lastIndexOf(Object,int)} cerchi all'indietro dalla posizione specificata e gestisca indici fuori intervallo secondo la semantica J2SE 1.4.2.
     * </p>
     * <p>
     * <b>Test Case Design</b> J2SE 1.4.2 per lastIndexOf 2-arg: index&gt;=size
     * lancia IndexOutOfBounds. Index&lt;0 deve restituire -1. Test verifica
     * comportamento reale delegato con valori concreti diversi
     * da indexOf.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore [elem1, dup, elem1, dup] la
     * ricerca all'indietro parte dalla posizione indicata: lastIndexOf(
     * "dup", 3) trova l'occorrenza in 3, mentre lastIndexOf("dup", 2)
     * risale alla precedente in 1. Un indice oltre size (100) deve lanciare
     * IndexOutOfBoundsException, un indice negativo (-1) restituisce -1 e
     * lastIndexOf("missing", 3) resta -1. Aggiunto null in coda (posizione
     * 4), viene infine verificata anche la ricerca a due argomenti su null.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,dup,elem1,dup] size 4.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> 3 con index 3, 1 con index 2, -1 con -1, IndexOutOfBounds con index 100 su SE (ideale J2SE/CLDC -&gt; 3).
     * </p>
     */
    @Test
    public void testLastIndexOfWithIndex() {
        v.addElement(E1);
        v.addElement(DUP);
        v.addElement(E1);
        v.addElement(DUP);
        assertEquals(3, v.lastIndexOf(DUP, 3));
        assertEquals(1, v.lastIndexOf(DUP, 2));
        try {
            v.lastIndexOf(DUP, 100);
            fail("lastIndexOf con index>=size deve lanciare IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) { /* atteso */ }
        assertEquals(-1, v.lastIndexOf(DUP, -1));
        assertEquals(-1, v.lastIndexOf("missing", 3));
        assertEquals(0, v.lastIndexOf(E1, 1));
        v.add(null);
        assertEquals(4, v.lastIndexOf(null, 4));
        assertEquals(-1, v.lastIndexOf(null, -1));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica il contratto di {@link HList#equals(Object)} e {@link HList#hashCode()} su VectorAdapter per contenuto uguale, diverso e coerenza.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale della coppia equals/hashCode con
     * verifica delle proprieta fondamentali del contratto Object:
     * riflessivita, simmetria e coerenza tra oggetti uguali. Le liste
     * {@code v} e {@code w} hanno lo stesso contenuto ma sono istanze
     * distinte, cosi che l'esito true documenti il confronto per valore
     * prescritto da {@link HList#equals(Object)} (taglia uguale ed elementi
     * corrispondenti in ordine); l'uguaglianza degli hash implementa la
     * clausola obbligatoria per oggetti uguali. I controesempi (tipo
     * estraneo, null, taglia diversa) coprono i rami false.
     * </p>
     * <p>
     * <b>Test Description</b> Con v = [elem1, elem2] e w = [elem1, elem2]
     * si verificano le clausole principali del contratto: equals tra due
     * liste con lo stesso contenuto vale true, il confronto riflessivo
     * v.equals(v) vale true, il confronto con una stringa e con
     * diff = [elem1] vale false. A parità di contenuto v e w devono avere
     * lo stesso hashCode, stabile tra chiamate ripetute.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v e w con stesso contenuto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> equals true per stesso contenuto, false altrimenti, hash equal per equal objects.
     * </p>
     */
    @Test
    public void testEqualsAndHashCode() {
        v.addElement(E1);
        v.addElement(E2);
        VectorAdapter w = new VectorAdapter();
        w.addElement(E1);
        w.addElement(E2);
        assertTrue(v.equals(v));
        assertTrue(v.equals(w));
        assertTrue(w.equals(v));
        assertFalse(v.equals("string"));
        assertFalse(v.equals(null));
        assertEquals(v.hashCode(), w.hashCode());
        int h1 = v.hashCode();
        int h2 = v.hashCode();
        assertEquals(h1, h2);
        VectorAdapter diff = new VectorAdapter();
        diff.addElement(E1);
        assertFalse(v.equals(diff));
        assertFalse(v.equals(diff));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#equals(Object)} restituisca true per due vettori
     * contenenti elementi null nelle stesse posizioni.
     * </p>
     * <p>
     * <b>Test Case Design</b> I metodi {@code equals} e {@code hashCode}
     * nell'implementazione {@link AbstractHList} gestiscono esplicitamente
     * il caso {@code e1==null ? e2==null : e1.equals(e2)}. Testare con null
     * nel contenuto verifica questo ramo. Complementa {@link #testEqualsAndHashCode()}
     * e {@link #testEqualsEmptyAndNull()} che usano solo elementi non-null.
     * </p>
     * <p>
     * <b>Test Description</b> Si costruiscono due vettori {@code v = [E1, null, E3]}
     * e {@code w = [E1, null, E3]}. Entrambi contengono null all'indice 1.
     * {@code v.equals(w)} deve restituire true, {@code w.equals(v)} deve
     * restituire true (simmetria), e {@code v.hashCode()} deve essere uguale
     * a {@code w.hashCode()}. Vengono anche testati casi asimmetrici: un
     * vettore con null e uno senza.
     * </p>
     * <p>
     * <b>Pre-Condition</b> {@code v = [E1, null, E3]}, {@code w = [E1, null, E3]}.
     * </p>
     * <p>
     * <b>Post-Condition</b> v e w invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> equals bidirezionale true; hashCode uguale;
     * vettori con composizione diversa di null non sono equals.
     * </p>
     */
    @Test
    public void testEqualsWithNullElements() {
        v.addElement(E1);
        v.addElement(null);
        v.addElement(E3);
        VectorAdapter w = new VectorAdapter();
        w.addElement(E1);
        w.addElement(null);
        w.addElement(E3);
        assertTrue(v.equals(w));
        assertTrue(w.equals(v));
        assertEquals(v.hashCode(), w.hashCode());
        VectorAdapter diff = new VectorAdapter();
        diff.addElement(E1);
        diff.addElement(E2);
        diff.addElement(E3);
        assertFalse(v.equals(diff));
        assertFalse(diff.equals(v));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#equals(Object)} restituisca false per due vettori di stessa size ma ordine degli elementi diverso.
     * </p>
     * <p>
     * <b>Test Case Design</b> L'uguaglianza delle liste e posizionale:
     * stessi elementi in ordine diverso non sono uguali. Complementa
     * testEqualsAndHashCode che verifica solo size diverse.
     * </p>
     * <p>
     * <b>Test Description</b> Si costruiscono v = [elem1, elem2] e
     * w = [elem2, elem1]: stessa size e stessi elementi, ma ordine
     * invertito. Sia v.equals(w) sia il simmetrico w.equals(v) devono
     * restituire false, perché l'uguaglianza confronta le liste posizione
     * per posizione.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v e w con stessi elementi in ordine inverso.
     * </p>
     * <p>
     * <b>Post-Condition</b> v e w invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> false in entrambe le direzioni.
     * </p>
     */
    @Test
    public void testEqualsDifferentOrderSameSize() {
        v.addElement(E1);
        v.addElement(E2);
        VectorAdapter w = new VectorAdapter();
        w.addElement(E2);
        w.addElement(E1);
        assertEquals(2, v.size());
        assertEquals(2, w.size());
        assertFalse(v.equals(w));
        assertFalse(w.equals(v));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#equals(Object)} su vettori vuoti, con null e tipi diversi gestisca correttamente i limiti.
     * </p>
     * <p>
     * <b>Test Case Design</b> Valori di frontiera di {@code equals}: due
     * vuoti, confronto con null, tipo estraneo e transizioni di taglia. Il
     * caso vuoto-vs-vuoto e la frontiera inferiore dell'uguaglianza per
     * contenuto (true nonostante l'assenza di elementi); null e tipo
     * estraneo devono restituire false secondo il contratto
     * {@link HList#equals(Object)}, che richiede l'appartenenza a HList
     * prima del confronto. La sequenza finale con aggiunta dello stesso
     * elemento in entrambe le liste conferma la sensibilita al contenuto
     * anche alle minime taglie.
     * </p>
     * <p>
     * <b>Test Description</b> Due vettori vuoti sono equals tra loro; il
     * confronto con null e con un oggetto di tipo diverso ("string")
     * restituisce false senza lanciare eccezioni; infine un vettore con il
     * solo elem1 confrontato con il vettore vuoto è false per differenza
     * di size.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto, w vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> true tra vuoti, false altrimenti.
     * </p>
     */
    @Test
    public void testEqualsEmptyAndNull() {
        VectorAdapter w = new VectorAdapter();
        assertTrue(v.equals(w));
        assertTrue(w.equals(v));
        assertFalse(v.equals(null));
        assertFalse(v.equals("string"));
        v.addElement(E1);
        assertFalse(v.equals(w));
        assertFalse(w.equals(v));
        w.addElement(E1);
        assertTrue(v.equals(w));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#hashCode()} su vettore vuoto restituisca 1 e sia coerente con la formula.
     * </p>
     * <p>
     * <b>Test Case Design</b> Valori di frontiera del calcolo dell'hash:
     * lista vuota, singolo elemento e elemento null. Il contratto
     * {@link HList#hashCode()} definisce la formula iterativa
     * {@code 1 + 31*hash(e)}; sul vuoto essa si riduce alla costante 1,
     * frontiera che il test verifica letteralmente, mentre i passi
     * successivi ricomputano il valore atteso a mano (incluso il
     * contributo nullo di un elemento null) e lo confrontano col risultato,
     * realizzando una verifica esatta della formula.
     * </p>
     * <p>
     * <b>Test Description</b> Il test applica la formula del contratto
     * List.hashCode ai casi base: sul vettore vuoto il valore atteso è 1;
     * con il solo elem1 è 31*1 + elem1.hashCode(); con il solo null come
     * elemento è 31*1 + 0. Le asserzioni controllano i valori calcolati
     * esplicitamente per i tre casi.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v con 1 poi 2 elementi.
     * </p>
     * <p>
     * <b>Expected Results</b> hash 1 su vuoto.
     * </p>
     */
    @Test
    public void testHashCodeEmptyList() {
        assertEquals(1, v.hashCode());
        v.addElement(E1);
        int expected = 31 * 1 + E1.hashCode();
        assertEquals(expected, v.hashCode());
        v.add(null);
        int expected2 = 31 * expected + 0;
        assertEquals(expected2, v.hashCode());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che chiamate ripetute a {@link HList#hashCode()} siano coerenti e uguali per contenuto uguale.
     * </p>
     * <p>
     * <b>Test Case Design</b> Categoria ripetute sull'hash: invochi
     * consecutivi sulla stessa istanza e confronto tra istanze a contenuto
     * uguale. La stabilita del valore tra chiamate successive su stato
     * immutato e la coincidenza tra liste uguali implementano le clausole
     * di coerenza del contratto {@link HList#hashCode()}; la ripetizione
     * protegge inoltre da implementazioni dipendenti da stati interni non
     * osservabili. Complementa {@link #testHashCodeEmptyList()}, che
     * verifica la formula passo a passo.
     * </p>
     * <p>
     * <b>Test Description</b> Su v = [elem1, elem2] hashCode() viene
     * invocato tre volte con risultato identico a ogni chiamata; a parità
     * di contenuto, w = [elem1, elem2] produce lo stesso valore di v, come
     * richiesto dal patto equals/hashCode.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v e w con stesso contenuto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> hash uguali e stabili.
     * </p>
     */
    @Test
    public void testHashCodeRepeatedConsistency() {
        v.addElement(E1);
        v.addElement(E2);
        VectorAdapter w = new VectorAdapter();
        w.addElement(E1);
        w.addElement(E2);
        int h1 = v.hashCode();
        int h2 = v.hashCode();
        int h3 = v.hashCode();
        assertEquals(h1, h2);
        assertEquals(h2, h3);
        assertEquals(v.hashCode(), w.hashCode());
        w.addElement(E3);
        assertFalse(v.hashCode() == w.hashCode());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#hashCode()} produca valori diversi per vettori con contenuto diverso.
     * </p>
     * <p>
     * <b>Test Case Design</b> Sebbene il contratto {@code hashCode} non
     * garantisca valori distinti per oggetti non-uguali, la probabilita di
     * collisione con pochi elementi e trascurabile. Questo test verifica che
     * la {@code hashCode()} di AbstractHList rifletta il contenuto effettivo.
     * Complementa {@link #testHashCodeRepeatedConsistency()} che verifica
     * solo coerenza sulla stessa lista.
     * </p>
     * <p>
     * <b>Test Description</b> Si creano due vettori con contenuto diverso:
     * {@code v = [E1, E2, E3]} e {@code w = [E1]}. Le loro hashCodes devono
     * essere diverse. Si verifica anche che un vettore vuoto e uno non vuoto
     * abbiano hashCode diverse.
     * </p>
     * <p>
     * <b>Pre-Condition</b> {@code v = [E1, E2, E3]}, {@code w = [E1]}.
     * </p>
     * <p>
     * <b>Post-Condition</b> v e w invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> {@code v.hashCode() != w.hashCode()};
     * {@code empty.hashCode() != v.hashCode()}.
     * </p>
     */
    @Test
    public void testHashCodeDifferentVectors() {
        populateThree();
        VectorAdapter w = new VectorAdapter();
        w.addElement(E1);
        assertNotEquals(v.hashCode(), w.hashCode());
        assertNotEquals(new VectorAdapter().hashCode(), v.hashCode());
    }

    // ====================================================================
    // E) Legacy Vector (28)
    // ====================================================================

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#capacity()} restituisca la capacità corrente e che {@link VectorAdapter#ensureCapacity(int)} aumenti la capacità quando necessario.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale delle operazioni di gestione
     * della capacità ereditate dalla API Vector. {@code capacity()} espone
     * la taglia dell'array interno, mentre {@code ensureCapacity(min)} deve
     * garantire una capacità minima crescendo secondo la politica del
     * Vector CLDC (incremento prefissato o raddoppio); le asserzioni dopo
     * ogni chiamata implementano il contratto legacy di {@code java.util.Vector},
     * che non fa parte di HList ma e richiesto per fedelta all'adaptee.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore vuoto di capacita iniziale dieci
     * si invoca prima ensureCapacity(5), verificando che la capacita resti
     * dieci perche la richiesta e già soddisfatta; seguono le richieste
     * crescenti 20 e 100, dopo ciascuna delle quali la capacita deve
     * risultare almeno pari al minimo richiesto. La taglia, controllata a
     * fine sequenza, deve restare zero: la crescita della capacita non tocca
     * il contenuto.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto capacity 10.
     * </p>
     * <p>
     * <b>Post-Condition</b> v size 0, capacity aumentata.
     * </p>
     * <p>
     * <b>Expected Results</b> capacity &gt;= minCapacity dopo ensure.
     * </p>
     */
    @Test
    public void testCapacityAndEnsureCapacity() {
        assertEquals(DEFAULT_CAPACITY, v.capacity());
        v.ensureCapacity(5);
        assertTrue(v.capacity() >= 5);
        assertEquals(DEFAULT_CAPACITY, v.capacity());
        v.ensureCapacity(20);
        assertTrue(v.capacity() >= 20);
        v.ensureCapacity(100);
        assertTrue(v.capacity() >= 100);
        assertEquals(0, v.size());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#ensureCapacity(int)} con minCapacity minore di capacity non riduca la capacity come caso limite.
     * </p>
     * <p>
     * <b>Test Case Design</b> Valore di frontiera di {@code ensureCapacity}:
     * richieste inferiori o uguali alla capacità corrente. Il contratto
     * Vector prescrive che l'operazione non riduca mai la capacità, quindi
     * {@code min &lt; capacity} e {@code min == capacity} sono entrambi
     * no-op; questi valori delimitano inferiormente la regione di crescita
     * e completano {@link #testCapacityAndEnsureCapacity()}.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore vuoto di capacita dieci si
     * invocano ensureCapacity(5), ensureCapacity(10) ed anche
     * ensureCapacity(0): i primi due valori sono rispettivamente inferiore e
     * uguale alla capacita corrente, le frontiere sotto le quali
     * l'operazione non deve avere alcun effetto. Dopo ogni chiamata la
     * capacita viene confrontata esattamente con dieci, ad attestare che non
     * esista mai riduzione.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto capacity 10.
     * </p>
     * <p>
     * <b>Post-Condition</b> capacity 10.
     * </p>
     * <p>
     * <b>Expected Results</b> capacity 10 invariata.
     * </p>
     */
    @Test
    public void testEnsureCapacityNoShrinkBoundary() {
        assertEquals(DEFAULT_CAPACITY, v.capacity());
        v.ensureCapacity(5);
        assertEquals(DEFAULT_CAPACITY, v.capacity());
        v.ensureCapacity(10);
        assertEquals(DEFAULT_CAPACITY, v.capacity());
        v.ensureCapacity(0);
        assertEquals(DEFAULT_CAPACITY, v.capacity());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#ensureCapacity(int)} con valore negativo non modifichi la capacity come caso limite.
     * </p>
     * <p>
     * <b>Test Case Design</b> Classe di errore benigna dell'argomento
     * negativo per {@code ensureCapacity}. La semantica legacy di
     * {@code java.util.Vector} tratta i valori minori di zero come richieste
     * vuote, senza eccezioni ne effetti; il test accerta questo comportamento
     * tollerante, distinto dalla validazione rigorosa che il costruttore
     * impone invece su initialCapacity negativo.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore vuoto di capacita dieci si
     * invocano ensureCapacity(-5) e ensureCapacity(-100), entrambe con
     * argomento negativo: nessuna delle due deve produrre eccezioni o
     * variazioni di stato, e la capacita viene riletta ed attesa ancora
     * pari a dieci dopo ciascuna chiamata.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto capacity 10.
     * </p>
     * <p>
     * <b>Post-Condition</b> capacity 10.
     * </p>
     * <p>
     * <b>Expected Results</b> capacity 10.
     * </p>
     */
    @Test
    public void testEnsureCapacityNegativeBoundary() {
        assertEquals(DEFAULT_CAPACITY, v.capacity());
        v.ensureCapacity(-5);
        assertEquals(DEFAULT_CAPACITY, v.capacity());
        v.ensureCapacity(-100);
        assertEquals(DEFAULT_CAPACITY, v.capacity());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#trimToSize()} riduca la capacità al valore di size.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale della compattazione della
     * capacità. {@code trimToSize} deve riportare capacity esattamente a
     * size, liberando le posizioni inutilizzate dell'array interno; la
     * verifica congiunta di size e capacity implementa entrambe le clausole
     * della semantica legacy Vector, e il controllo successivo degli
     * elementi accerta che la compattazione non alteri il contenuto.
     * Complementa {@link #testClear()}, che azzera la taglia preservando la
     * capacità.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore [elem1, elem2, elem3], di taglia
     * tre e capacita dieci, trimToSize() deve riportare la capacita
     * esattamente a tre lasciando invariata la taglia; si aggiunge quindi
     * dup con addElement e si controlla che la capacita torni a crescere
     * oltre tre, prova che la compattazione non impedisce le espansioni
     * successive.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v size 3 capacity 10.
     * </p>
     * <p>
     * <b>Post-Condition</b> capacity 3 dopo trim, poi crescita dopo add.
     * </p>
     * <p>
     * <b>Expected Results</b> capacity 3 dopo trim.
     * </p>
     */
    @Test
    public void testTrimToSize() {
        populateThree();
        assertEquals(10, v.capacity());
        v.trimToSize();
        assertEquals(3, v.capacity());
        assertEquals(3, v.size());
        v.addElement(DUP);
        assertTrue(v.capacity() > 3);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#trimToSize()} su vettore vuoto riduca la capacity a 0 come caso limite.
     * </p>
     * <p>
     * <b>Test Case Design</b> Valore di frontiera di {@code trimToSize} sul
     * contenitore vuoto: la capacità decade da quella iniziale a zero,
     * configurazione limite in cui size e capacity giungono a coincidere
     * solo dopo l'operazione. Il comportamento segue dalla semantica legacy
     * di Vector, che allinea sempre la capacità alla taglia corrente;
     * complementa {@link #testTrimToSize()}, condotto su lista popolata.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore vuoto di capacita dieci
     * trimToSize() deve portare la capacita a zero mantenendo nulla la
     * taglia; una seconda invocazione consecutiva conferma l'idempotenza
     * dell'operazione sullo stato già compattato.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto capacity 10.
     * </p>
     * <p>
     * <b>Post-Condition</b> v vuoto capacity 0.
     * </p>
     * <p>
     * <b>Expected Results</b> capacity 0, size 0.
     * </p>
     */
    @Test
    public void testTrimToSizeOnEmptyList() {
        assertEquals(0, v.size());
        assertEquals(DEFAULT_CAPACITY, v.capacity());
        v.trimToSize();
        assertEquals(0, v.capacity());
        assertEquals(0, v.size());
        v.trimToSize();
        assertEquals(0, v.capacity());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#setSize(int)} aumenti la size aggiungendo null e la riduca scartando gli elementi in coda.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale di {@code setSize} nelle due
     * direzioni: crescita oltre la taglia corrente (con riempimento a null)
     * e contrazione con scarto della coda. I due rami costituiscono classi
     * di equivalenza distinte della semantica legacy
     * {@code Vector.setSize}, qui verificate su stati concreti con controllo
     * del contenuto residuo e dei null aggiunti; la crescita puo inoltre
     * innescare l'espansione dell'array interno quando la nuova taglia
     * eccede la capacità disponibile.
     * </p>
     * <p>
     * <b>Test Description</b> Si costruisce il vettore [elem1, elem2] e si
     * invoca setSize(5): la taglia diventa cinque e le tre posizioni nuove
     * devono contenere null, mentre elem1 ed elem2 restano nelle posizioni
     * zero e uno; segue setSize(1), che contrae la lista al solo elem1 e
     * consente di verificare che lo scarto della coda non lasci tracce.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2] size 2.
     * </p>
     * <p>
     * <b>Post-Condition</b> v size 1 dopo shrink.
     * </p>
     * <p>
     * <b>Expected Results</b> Crescita con null, shrink scarta.
     * </p>
     */
    @Test
    public void testSetSizeGrowAndShrink() {
        v.addElement(E1);
        v.addElement(E2);
        v.setSize(5);
        assertEquals(5, v.size());
        assertEquals(E1, v.get(0));
        assertEquals(E2, v.get(1));
        assertNull(v.get(2));
        assertNull(v.get(3));
        assertNull(v.get(4));
        v.setSize(1);
        assertEquals(1, v.size());
        assertEquals(E1, v.get(0));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#setSize(int)} con 0 svuoti il vettore mantenendo la capacity.
     * </p>
     * <p>
     * <b>Test Case Design</b> Valore di frontiera di {@code setSize}:
     * newSize pari a zero, estremo inferiore dell'intervallo ammesso
     * {@code [0,...]}. L'operazione equivale a uno svuotamento totale che
     * mantiene intatta la capacità, comportamento intermedio tra
     * {@code clear()} e un eventuale trimToSize; il contratto legacy Vector
     * prescrive lo scarto della coda senza eccezioni per questo valore.
     * Complementa {@link #testSetSizeGrowAndShrink()}, che esercita i rami
     * di crescita e contrazione parziale.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore [elem1, elem2, elem3] si invoca
     * setSize(0), estremo inferiore dell'intervallo valido: la lista diventa
     * vuota e isEmpty restituisce true mentre la capacita, letta prima
     * dell'operazione, deve risultare invariata. Un get(0) conclusivo,
     * racchiuso nel consueto blocco try/fail/catch, conferma che non residui
     * alcun elemento accessibile.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v con 3.
     * </p>
     * <p>
     * <b>Post-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> size 0, capacity 10.
     * </p>
     */
    @Test
    public void testSetSizeZeroEmpties() {
        populateThree();
        int cap = v.capacity();
        v.setSize(0);
        assertEquals(0, v.size());
        assertTrue(v.isEmpty());
        assertEquals(cap, v.capacity());
        try { v.get(0); fail("get su vuoto deve lanciare"); } catch (IndexOutOfBoundsException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#setSize(int)} lanci {@link ArrayIndexOutOfBoundsException} se la nuova size e negativa.
     * </p>
     * <p>
     * <b>Test Case Design</b> Classe di errore dell'argomento negativo per
     * {@code setSize}. A differenza di {@code ensureCapacity}, che tollera
     * i negativi come no-op, il Vector CLDC propaga qui un
     * ArrayIndexOutOfBoundsException generato dai controlli interni
     * sull'array sottostante; il test documenta questa asimmetria della API
     * legacy, rilevante perche l'esito dipende dall'implementazione CLDC di
     * riferimento.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore contenente il solo elem1 si
     * invoca setSize(-1) con nuova taglia negativa: l'invocazione deve
     * terminare con ArrayIndexOutOfBoundsException, come dichiarato
     * dall'annotazione @Test expected, senza necessita di asserzioni
     * ulteriori sullo stato.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> ArrayIndexOutOfBoundsException.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetSizeNegativeThrows() {
        v.addElement(E1);
        v.setSize(-1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che chiamate ripetute a {@link VectorAdapter#setSize(int)} con sequenza grow-shrink-grow siano coerenti.
     * </p>
     * <p>
     * <b>Test Case Design</b> Categoria ripetute su {@code setSize}:
     * sequenza alternata di crescita e contrazione ({@code 2->5->1->4}).
     * L'alternanza accerta che ogni chiamata parta dallo stato prodotto
     * dalla precedente, con riempimenti a null e scarti di coda corretti
     * anche attraverso transizioni ripetute; la coerenza attesa discende
     * dall'applicazione iterativa della semantica legacy
     * {@code Vector.setSize}. Complementa
     * {@link #testSetSizeGrowAndShrink()}, limitato a una crescita e una
     * contrazione.
     * </p>
     * <p>
     * <b>Test Description</b> Partendo da [elem1, elem2] la sequenza
     * setSize(5), setSize(1) e setSize(4) alterna crescita, contrazione e
     * nuova crescita: dopo la prima chiamata si controlla il null in
     * posizione due, dopo la seconda che resti il solo elem1, dopo la terza
     * che elem1 occupi la posizione zero seguito da tre null. Ogni passo
     * parte cosi dallo stato effettivamente prodotto dal precedente.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2].
     * </p>
     * <p>
     * <b>Post-Condition</b> v size4.
     * </p>
     * <p>
     * <b>Expected Results</b> size 4 con null.
     * </p>
     */
    @Test
    public void testSetSizeRepeatedGrowShrinkGrow() {
        v.addElement(E1);
        v.addElement(E2);
        v.setSize(5);
        assertEquals(5, v.size());
        assertNull(v.get(2));
        v.setSize(1);
        assertEquals(1, v.size());
        assertEquals(E1, v.get(0));
        v.setSize(4);
        assertEquals(4, v.size());
        assertEquals(E1, v.get(0));
        assertNull(v.get(1));
        assertNull(v.get(2));
        assertNull(v.get(3));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#elementAt(int)} restituisca l'elemento all'indice specificato e lanci {@link ArrayIndexOutOfBoundsException} per indici non validi.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale dell'accesso posizionale legacy.
     * {@code elementAt(int)} e l'operazione storica di Vector su cui
     * poggiano anche gli accessi HList; il test accerta la corrispondenza
     * con {@code get(int)} sullo stesso stato, confermando che l'adattatore
     * espone due viste coerenti dello stesso array interno secondo la
     * semantica legacy {@code java.util.Vector}.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore [elem1, elem2, elem3] si leggono
     * elementAt(0) ed elementAt(2), estremi dell'intervallo valido, attesi
     * rispettivamente pari a elem1 ed elem3. L'indice 3, primo valore fuori
     * intervallo, viene infine provato in un blocco try/fail/catch in attesa
     * di ArrayIndexOutOfBoundsException.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2,elem3].
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> elementAt corretto, eccezione su bounds.
     * </p>
     */
    @Test
    public void testElementAt() {
        populateThree();
        assertEquals(E1, v.elementAt(0));
        assertEquals(E3, v.elementAt(2));
        try {
            v.elementAt(3);
            fail("elementAt(3) su size 3 deve lanciare ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#elementAt(int)} su vettore singolo e vuoto gestisca correttamente i limiti.
     * </p>
     * <p>
     * <b>Test Case Design</b> Valori di frontiera di {@code elementAt} sugli
     * stati degeneri: taglia 1, dove solo l'indice 0 e ammesso, e taglia 0,
     * dove nessun indice lo e. Il contratto legacy Vector prescrive
     * ArrayIndexOutOfBoundsException per ogni indice fuori intervallo,
     * incluso il primo elemento richiesto su vettore vuoto; questi casi
     * completano il controllo di bounds del caso normale.
     * </p>
     * <p>
     * <b>Test Description</b> Su un vettore ridotto al solo elem1
     * elementAt(0) restituisce l'elemento richiesto, mentre gli indici 1 e
     * -1, minimhe violazioni dei due estremi dell'intervallo valido, vengono
     * respinti nei blocchi try/fail/catch in attesa di
     * ArrayIndexOutOfBoundsException. Su un secondo vettore lasciato vuoto
     * persino elementAt(0) deve lanciare, essendo l'intervallo ammesso privo
     * di posizioni.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1] poi vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> elemento e eccezioni.
     * </p>
     */
    @Test
    public void testElementAtSingleElementAndEmpty() {
        v.addElement(E1);
        assertEquals(E1, v.elementAt(0));
        try { v.elementAt(1); fail("elementAt(1) su size1 deve lanciare"); } catch (ArrayIndexOutOfBoundsException e) { /* atteso */ }
        try { v.elementAt(-1); fail("elementAt(-1) deve lanciare"); } catch (ArrayIndexOutOfBoundsException e) { /* atteso */ }
        VectorAdapter empty = new VectorAdapter();
        try { empty.elementAt(0); fail("elementAt su vuoto deve lanciare"); } catch (ArrayIndexOutOfBoundsException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#firstElement()} e {@link VectorAdapter#lastElement()} restituiscano rispettivamente il primo e l'ultimo elemento e lancino {@link NoSuchElementException} su vettore vuoto.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale degli accessi alle estremita
     * della API legacy. {@code firstElement} e {@code lastElement}
     * restituiscono rispettivamente il primo e l'ultimo componente secondo
     * la semantica {@code java.util.Vector}; su lista vuota il medesimo
     * contratto prescrive {@link NoSuchElementException}, eccezione
     * specifica della API legacy che distingue questi metodi dagli
     * equivalenti HList, i quali usano IndexOutOfBoundsException.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore [elem1, elem2, elem3]
     * firstElement() deve restituire elem1 e lastElement() elem3, le due
     * estremita opposte della lista. Su un secondo vettore mantenuto vuoto
     * entrambi i metodi vengono invocati dentro blocchi try/fail/catch in
     * attesa di NoSuchElementException, eccezione specifica della API legacy.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v con 3 elementi, poi vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> first elem1, last elem3, eccezioni su vuoto.
     * </p>
     */
    @Test
    public void testFirstAndLastElement() {
        populateThree();
        assertEquals(E1, v.firstElement());
        assertEquals(E3, v.lastElement());
        VectorAdapter empty = new VectorAdapter();
        try {
            empty.firstElement();
            fail("firstElement su vuoto deve lanciare NoSuchElementException");
        } catch (NoSuchElementException e) { /* atteso */ }
        try {
            empty.lastElement();
            fail("lastElement su vuoto deve lanciare NoSuchElementException");
        } catch (NoSuchElementException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#firstElement()} e {@link VectorAdapter#lastElement()} su vettore singolo coincidano.
     * </p>
     * <p>
     * <b>Test Case Design</b> Valore di frontiera dei due accessi alle
     * estremita: lista di un solo elemento, unico stato in cui prima e
     * ultima posizione coincidono e i due metodi devono restituire lo
     * stesso riferimento. Il caso conferma la coerenza interna delle
     * definizioni (l'indice 0 come estremo sia inferiore che superiore)
     * prevista dalla semantica legacy Vector. Complementa
     * {@link #testFirstAndLastElement()} per stati piu popolati.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore ridotto al solo elem1 entrambi gli
     * accessi alle estremita restituiscono elem1: oltre ai due confronti
     * individuali, firstElement() e lastElement() vengono confrontati
     * direttamente tra loro, unico stato in cui prima e ultima posizione
     * coincidono.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1].
     * </p>
     * <p>
     * <b>Post-Condition</b> v=[elem1].
     * </p>
     * <p>
     * <b>Expected Results</b> first==last==elem1.
     * </p>
     */
    @Test
    public void testFirstLastElementSingleElementCoincide() {
        v.addElement(E1);
        assertEquals(E1, v.firstElement());
        assertEquals(E1, v.lastElement());
        assertEquals(v.firstElement(), v.lastElement());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#setElementAt(Object,int)} sostituisca l'elemento alla posizione specificata con parametri invertiti rispetto a set.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale della sostituzione legacy con
     * attenzione alla firma distintiva. {@code setElementAt(Object,int)}
     * differisce da {@code set(int,Object)} per l'ordine degli argomenti
     * invertito e per il tipo void senza valore di ritorno, differenze di
     * interfaccia che il test documenta esercitando la variante legacy; la
     * verifica del contenuto post-operazione implementa la medesima
     * semantica di sostituzione del contratto {@code java.util.Vector}.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore [elem1, elem2, elem3] si invoca
     * setElementAt("nuovo", 1): un get(1) successivo deve restituire nuovo e
     * la taglia deve rimanere tre, a prova che si tratti di sostituzione e
     * non di inserimento. L'indice 3, pari alla taglia e quindi primo fuori
     * intervallo, viene infine respinto nel blocco try/fail/catch per
     * ArrayIndexOutOfBoundsException.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2,elem3].
     * </p>
     * <p>
     * <b>Post-Condition</b> v=[elem1,nuovo,elem3].
     * </p>
     * <p>
     * <b>Expected Results</b> Elemento sostituito, size invariata.
     * </p>
     */
    @Test
    public void testSetElementAt() {
        populateThree();
        v.setElementAt("nuovo", 1);
        assertEquals("nuovo", v.get(1));
        assertEquals(3, v.size());
        try {
            v.setElementAt("x", 3);
            fail("setElementAt su index 3 size 3 deve lanciare ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#insertElementAt(Object,int)} inserisca l'elemento prima dell'indice specificato shiftando i successivi.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale dell'inserzione legacy.
     * {@code insertElementAt(Object,int)} inserisce prima della posizione
     * indicata shiftando a destra, con {@code index==size} ammesso come
     * equivalente di {@code addElement} secondo la semantica
     * {@code java.util.Vector}; il comportamento rispecchia
     * {@code add(int,Object)}, cosi che il confronto tra le due forme sullo
     * stesso stato accerti la coerenza delle due interfacce.
     * </p>
     * <p>
     * <b>Test Description</b> Dal vettore [elem1, elem3] si arriva a
     * [elem1, elem2, elem3] tramite insertElementAt(elem2, 1), verificando
     * ordine e nuova taglia tre dopo lo shift a destra di elem3. Seguono le
     * due frontiere dell'intervallo valido [0, size]: l'inserzione in testa
     * all'indice zero e quella in coda all'indice pari alla taglia, con head
     * e tail letti rispettivamente in posizione zero e ultima.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem3].
     * </p>
     * <p>
     * <b>Post-Condition</b> v=[elem1,elem2,elem3] dopo insert.
     * </p>
     * <p>
     * <b>Expected Results</b> Inserimento corretto, size 3.
     * </p>
     */
    @Test
    public void testInsertElementAt() {
        v.addElement(E1);
        v.addElement(E3);
        v.insertElementAt(E2, 1);
        assertEquals(3, v.size());
        assertEquals(E1, v.get(0));
        assertEquals(E2, v.get(1));
        assertEquals(E3, v.get(2));
        v.insertElementAt("head", 0);
        assertEquals("head", v.get(0));
        v.insertElementAt("tail", v.size());
        assertEquals("tail", v.get(v.size() - 1));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#insertElementAt(Object,int)} lanci {@link ArrayIndexOutOfBoundsException} per indici non validi.
     * </p>
     * <p>
     * <b>Test Case Design</b> Classe di errore dell'indice per l'inserzione
     * legacy. Gli indici -1 e size+1 sono le minime violazioni
     * rispettivamente del limite inferiore e superiore dell'intervallo
     * valido {@code [0,size]} previsto dal contratto legacy Vector, che qui
     * prescrive ArrayIndexOutOfBoundsException anziche
     * IndexOutOfBoundsException; la distinzione del tipo di eccezione
     * riflette il lignaggio interno dell'adaptee.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore contenente il solo elem1 gli
     * indici -1 e 2, minimhe violazioni rispettivamente del limite inferiore
     * e superiore dell'intervallo valido [0, 1], vengono provati in blocchi
     * try/fail/catch in attesa di ArrayIndexOutOfBoundsException; su un
     * secondo vettore vuoto anche insertElementAt("x", 1) deve lanciare,
     * non esistendo posizioni utili.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1].
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> ArrayIndexOutOfBoundsException.
     * </p>
     */
    @Test
    public void testInsertElementAtThrowsBounds() {
        v.addElement(E1);
        try { v.insertElementAt("x", -1); fail("insert -1 deve lanciare"); } catch (ArrayIndexOutOfBoundsException e) { /* atteso */ }
        try { v.insertElementAt("x", 2); fail("insert size+1 deve lanciare"); } catch (ArrayIndexOutOfBoundsException e) { /* atteso */ }
        VectorAdapter empty = new VectorAdapter();
        try { empty.insertElementAt("x", 1); fail("insert 1 su vuoto deve lanciare"); } catch (ArrayIndexOutOfBoundsException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#removeElementAt(int)} rimuova l'elemento all'indice specificato.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale della rimozione posizionale
     * legacy, caratterizzata dal tipo void senza elemento restituito. La
     * semantica e quella di {@code remove(int)} con shift a sinistra dei
     * successivi secondo il contratto {@code java.util.Vector}; il controllo
     * del contenuto post-operazione accerta lo shift pur in assenza di un
     * valore di ritorno da verificare direttamente.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore [elem1, elem2, elem3]
     * removeElementAt(1) elimina elem2 portando la lista a [elem1, elem3]
     * di taglia due: il controllo degli elementi residui accerta lo shift a
     * sinistra nonostante l'assenza di valore di ritorno. L'indice 2, ora
     * pari alla taglia, viene infine respinto nel consueto blocco
     * try/fail/catch per ArrayIndexOutOfBoundsException.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2,elem3].
     * </p>
     * <p>
     * <b>Post-Condition</b> v=[elem1,elem3].
     * </p>
     * <p>
     * <b>Expected Results</b> size 2, elem2 rimosso.
     * </p>
     */
    @Test
    public void testRemoveElementAt() {
        populateThree();
        v.removeElementAt(1);
        assertEquals(2, v.size());
        assertEquals(E1, v.get(0));
        assertEquals(E3, v.get(1));
        try {
            v.removeElementAt(2);
            fail("removeElementAt(2) su size 2 deve lanciare ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#removeElementAt(int)} su vettore vuoto lanci {@link ArrayIndexOutOfBoundsException} per qualsiasi indice.
     * </p>
     * <p>
     * <b>Test Case Design</b> Stato limite della rimozione posizionale
     * legacy sul contenitore vuoto, dove nessun indice e valido. Il
     * contratto legacy Vector propaga ArrayIndexOutOfBoundsException anche
     * per l'indice 0; il controllo degli indici negativi completa la classe
     * di errore. Complementa il bounds check già esercitato su taglia
     * maggiore in {@link #testRemoveElementAt()}.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore lasciato vuoto nessun indice e
     * valido: sia removeElementAt(0) sia removeElementAt(-1), minimhe
     * violazioni dei due estremi, devono lanciare
     * ArrayIndexOutOfBoundsException dentro i blocchi try/fail/catch, e la
     * taglia rilevata a valle deve restare zero.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> ArrayIndexOutOfBoundsException per entrambi gli indici.
     * </p>
     */
    @Test
    public void testRemoveElementAtOnEmptyThrowsIOOBE() {
        try { v.removeElementAt(0); fail("removeElementAt(0) su vuoto deve lanciare"); } catch (ArrayIndexOutOfBoundsException e) { /* atteso */ }
        try { v.removeElementAt(-1); fail("removeElementAt(-1) su vuoto deve lanciare"); } catch (ArrayIndexOutOfBoundsException e) { /* atteso */ }
        assertEquals(0, v.size());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#addElement(Object)} aggiunga in coda e che {@link VectorAdapter#removeElement(Object)} rimuova la prima occorrenza restituendo true o false.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale abbinato della coppia
     * append/rimozione-per-valore legacy. {@code addElement(Object)}
     * aggiunge sempre in coda (equivalente a {@code add(Object)}), mentre
     * {@code removeElement(Object)} cerca la prima occorrenza e restituisce
     * true solo se la rimozione ha luogo, secondo il contratto
     * {@code java.util.Vector}; i dati includono un elemento assente per
     * coprire anche il ramo false. Complementa
     * {@link #testRemoveAllDuplicatesInVector()}, che mostra come
     * removeAll elimini invece ogni occorrenza.
     * </p>
     * <p>
     * <b>Test Description</b> Si aggiungono elem1 e dup con addElement fino
     * alla taglia due, poi removeElement(elem1) restituisce true e riduce la
     * lista al solo dup, mentre un tentativo su missing restituisce false
     * senza effetti. Con la lista riportata a [dup, dup] una nuova
     * removeElement(dup) dimostra infine che solo la prima occorrenza viene
     * scartata, lasciando [dup].
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto poi con elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> v con dup singolo dopo remove duplicato.
     * </p>
     * <p>
     * <b>Expected Results</b> add incrementa, removeElement true/false corretti.
     * </p>
     */
    @Test
    public void testAddElementAndRemoveElement() {
        v.addElement(E1);
        v.addElement(DUP);
        assertEquals(2, v.size());
        assertTrue(v.removeElement(E1));
        assertEquals(1, v.size());
        assertEquals(DUP, v.get(0));
        assertFalse(v.removeElement("missing"));
        v.addElement(DUP);
        assertEquals(2, v.size());
        assertTrue(v.removeElement(DUP));
        assertEquals(1, v.size());
        assertEquals(DUP, v.get(0));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che le API legacy {@link VectorAdapter#addElement(Object)}, {@link VectorAdapter#insertElementAt(Object,int)}, {@link VectorAdapter#setElementAt(Object,int)} e {@link VectorAdapter#removeElement(Object)} gestiscano correttamente gli elementi null.
     * </p>
     * <p>
     * <b>Test Case Design</b> La API legacy di Vector accetta null come le
     * operazioni HList: il null va tracciato da indexOf/contains e rimosso
     * da removeElement. Complementa i test null già presenti per le
     * operazioni HCollection/HList.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore vuoto si inseriscono due null con
     * addElement e insertElementAt(null, 0), accedendovi poi con
     * firstElement, lastElement ed elementAt tutti attesi nulli; segue
     * setElementAt(elem1, 1), che sostituisce il null in coda. La fase
     * finale traccia il null residuo con indexOf, lastIndexOf e contains,
     * tutti ancorati alla posizione zero, e lo rimuove con
     * removeElement(null), lasciando [elem1].
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v=[elem1] senza null residui.
     * </p>
     * <p>
     * <b>Expected Results</b> null gestito correttamente da tutta la API legacy.
     * </p>
     */
    @Test
    public void testLegacyNullElements() {
        v.addElement(null);
        assertEquals(1, v.size());
        assertNull(v.elementAt(0));
        v.insertElementAt(null, 0);
        assertEquals(2, v.size());
        assertNull(v.firstElement());
        assertNull(v.lastElement());
        v.setElementAt(E1, 1);
        assertNull(v.get(0));
        assertEquals(E1, v.get(1));
        assertEquals(0, v.indexOf(null));
        assertEquals(0, v.lastIndexOf(null));
        assertTrue(v.contains(null));
        assertTrue(v.removeElement(null));
        assertEquals(1, v.size());
        assertEquals(E1, v.get(0));
        assertFalse(v.contains(null));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#removeAllElements()} rimuova tutti gli elementi lasciando il vettore vuoto.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale dello svuotamento legacy.
     * {@code removeAllElements} e l'analogo void di {@code clear()} nella
     * API Vector, con identico effetto sulla taglia e sulla capacità
     * preservata secondo la semantica legacy; l'accoppiamento con isEmpty
     * accerta lo stato risultante. Complementa {@link #testClear()}, che
     * copre l'operazione equivalente di HCollection.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore [elem1, elem2, elem3]
     * removeAllElements() deve azzerare la taglia rendendo isEmpty vero;
     * la verifica resta volutamente limitata allo svuotamento, perche la
     * sorte della capacita e oggetto dei test dedicati alle operazioni
     * equivalenti di HCollection.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v con 3 elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> size 0, isEmpty true.
     * </p>
     */
    @Test
    public void testRemoveAllElements() {
        populateThree();
        v.removeAllElements();
        assertEquals(0, v.size());
        assertTrue(v.isEmpty());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#copyInto(Object[])} copi tutti gli elementi nell'array fornito nell'ordine corretto.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale della copia bulk verso array
     * esterno. {@code copyInto(Object[])} trascrive gli elementi nell'ordine
     * del vettore nelle prime size posizioni dell'array ricevuto, secondo
     * la semantica legacy {@code java.util.Vector}; le condizioni di errore
     * su argomento null e array troppo piccolo appartengono al medesimo
     * contratto e sono trattate nel test dedicato.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore [elem1, elem2, elem3] si prepara
     * un array Object di lunghezza tre, esattamente pari alla taglia, e
     * copyInto(a) deve trascrivervi gli elementi in ordine: a[0], a[1] e
     * a[2] vengono confrontati rispettivamente con elem1, elem2 ed elem3.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v size 3, a length 3.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato, a contiene copia.
     * </p>
     * <p>
     * <b>Expected Results</b> a[0]=elem1, a[1]=elem2, a[2]=elem3.
     * </p>
     */
    @Test
    public void testCopyInto() {
        populateThree();
        Object[] a = new Object[3];
        v.copyInto(a);
        assertEquals(E1, a[0]);
        assertEquals(E2, a[1]);
        assertEquals(E3, a[2]);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#copyInto(Object[])} lanci {@link NullPointerException} per array null e {@link ArrayIndexOutOfBoundsException} per array troppo piccolo.
     * </p>
     * <p>
     * <b>Test Case Design</b> Classi di errore dell'argomento per
     * {@code copyInto}: riferimento null, che il contratto legacy Vector
     * punisce con {@link NullPointerException}, e array di lunghezza
     * insufficiente, che genera ArrayIndexOutOfBoundsException dai
     * controlli interni CLDC durante la trascrizione. Le due violazioni sono
     * le condizioni patologiche dell'operazione, complementari al caso
     * normale di {@link #testCopyInto()}.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore [elem1, elem2, elem3] si invoca
     * prima copyInto(null), respinta con NullPointerException nel blocco
     * try/fail/catch, poi copyInto su un array di lunghezza due,
     * insufficiente per tre componenti e quindi causa di
     * ArrayIndexOutOfBoundsException durante la trascrizione.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v size 3.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> NullPointerException e ArrayIndexOutOfBoundsException.
     * </p>
     */
    @Test
    public void testCopyIntoNullAndSmallThrows() {
        populateThree();
        try {
            v.copyInto(null);
            fail("copyInto(null) deve lanciare NullPointerException");
        } catch (NullPointerException e) { /* atteso */ }
        try {
            v.copyInto(new Object[2]);
            fail("copyInto con array piccolo deve lanciare ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#copyInto(Object[])} con array piu grande lasci intatta la coda oltre size.
     * </p>
     * <p>
     * <b>Test Case Design</b> Valore di frontiera di {@code copyInto} con
     * array piu grande della taglia: solo le prime size posizioni vengono
     * sovrascritte e la restante coda deve restare intatta. Questa
     * proprieta, implicita nel contratto legacy Vector (nessuna azione oltre
     * la trascrizione), e resa osservabile tramite un valore sentinella
     * collocato oltre la zona di copia; complementa {@link #testCopyInto()},
     * condotto su array esatto.
     * </p>
     * <p>
     * <b>Test Description</b> Con il vettore ridotto a [elem1, elem2] si
     * copia in un array Object di lunghezza cinque pre-caricato con i
     * valori sentinel keep in posizione tre e keep2 in posizione quattro:
     * dopo copyInto(a) le prime due posizioni devono valere elem1 ed elem2
     * mentre i sentinel devono sopravvivere intatti, prova che la
     * trascrizione non eccede la taglia del vettore.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v size2, a length5.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato, a parziale.
     * </p>
     * <p>
     * <b>Expected Results</b> a[0..1] copiati, a[3..4] invariati.
     * </p>
     */
    @Test
    public void testCopyIntoLargerArrayTrailingIntact() {
        v.addElement(E1);
        v.addElement(E2);
        Object[] a = new Object[5];
        a[3] = "keep";
        a[4] = "keep2";
        v.copyInto(a);
        assertEquals(E1, a[0]);
        assertEquals(E2, a[1]);
        assertEquals("keep", a[3]);
        assertEquals("keep2", a[4]);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#copyInto(Object[])} su vettore vuoto non modifichi l'array come caso limite.
     * </p>
     * <p>
     * <b>Test Case Design</b> Stato limite di {@code copyInto}: vettore
     * vuoto, per cui l'operazione non scrive alcuna posizione ed e un no-op
     * osservabile sull'array fornito. Il contratto legacy Vector non prevede
     * effetti collaterali in assenza di componenti; il caso completa la
     * gamma di taglie esercitate da {@link #testCopyInto()} e
     * {@link #testCopyIntoLargerArrayTrailingIntact()}.
     * </p>
     * <p>
     * <b>Test Description</b> Su un vettore vuoto copyInto(a) non deve
     * scrivere alcuna posizione: l'array di lunghezza due pre-caricato con
     * i valori sentinel s0 ed s1 viene ricontrollato posizione per posizione
     * dopo l'invocazione, insieme alla taglia nulla del vettore.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v vuoto, a invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> a[0] e a[1] restano sentinel.
     * </p>
     */
    @Test
    public void testCopyIntoEmptyList() {
        Object[] a = new Object[2];
        a[0] = "s0";
        a[1] = "s1";
        v.copyInto(a);
        assertEquals("s0", a[0]);
        assertEquals("s1", a[1]);
        assertEquals(0, v.size());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#elements()} restituisca una {@link Enumeration} che genera gli elementi nell'ordine crescente.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale della vista enumerativa legacy.
     * {@code elements()} restituisce una Enumeration che genera i componenti
     * in ordine crescente di indice secondo il contratto
     * {@code java.util.Vector}; la verifica dell'ordine completo implementa
     * il comportamento atteso e documenta la convivenza della vista legacy
     * con l'HIterator moderno offerto dalla stessa classe.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore [elem1, elem2, elem3]
     * l'Enumeration fornita da elements() viene percorsa con tre coppie
     * hasMoreElements/nextElement, attese rispettivamente vere e pari a
     * elem1, elem2 ed elem3; dopo l'ultimo elemento hasMoreElements deve
     * tornare false.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2,elem3].
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Enumeration contiene elem1,elem2,elem3 in ordine.
     * </p>
     */
    @Test
    public void testElementsEnumeration() {
        populateThree();
        Enumeration e = v.elements();
        assertTrue(e.hasMoreElements());
        assertEquals(E1, e.nextElement());
        assertEquals(E2, e.nextElement());
        assertEquals(E3, e.nextElement());
        assertFalse(e.hasMoreElements());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#elements()} su vettore vuoto e su enumerazione esaurita lanci {@link NoSuchElementException}.
     * </p>
     * <p>
     * <b>Test Case Design</b> Classi di errore della Enumeration legacy:
     * generazione su collezione vuota ed esaurimento dopo l'ultimo elemento.
     * In entrambe le situazioni hasMoreElements restituisce false e
     * nextElement deve lanciare NoSuchElementException secondo il contratto
     * legacy Vector; i due punti costituiscono le frontiere naturali del
     * protocollo enumerativo. Complementa
     * {@link #testElementsEnumeration()}, limitato all'estrazione regolare.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore vuoto hasMoreElements restituisce
     * subito false e nextElement viene respinto con NoSuchElementException
     * nel blocco try/fail/catch; si ripete quindi il protocollo su un
     * vettore contenente il solo elem1, dove dopo l'estrazione regolare una
     * nuova nextElement su enumerazione esaurita deve lanciare ancora
     * NoSuchElementException.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto poi con 1.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> NoSuchElementException.
     * </p>
     */
    @Test
    public void testElementsEnumerationExhaustionThrows() {
        Enumeration e = v.elements();
        assertFalse(e.hasMoreElements());
        try { e.nextElement(); fail("nextElement su vuoto deve lanciare"); } catch (NoSuchElementException ex) { /* atteso */ }
        v.addElement(E1);
        Enumeration e2 = v.elements();
        assertTrue(e2.hasMoreElements());
        assertEquals(E1, e2.nextElement());
        assertFalse(e2.hasMoreElements());
        try { e2.nextElement(); fail("nextElement su esaurita deve lanciare"); } catch (NoSuchElementException ex) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che chiamate ripetute a {@link Enumeration#hasMoreElements()} siano idempotenti.
     * </p>
     * <p>
     * <b>Test Case Design</b> Categoria ripetute sul protocollo enumerativo:
     * invochi consecutivi di hasMoreElements senza nextElement intermedi.
     * L'idempotenza richiesta dal contratto legacy di Enumeration garantisce
     * che la sola interrogazione non consumi la sequenza ne alteri lo stato;
     * la ripetizione accerta questa proprieta prima dell'estrazione
     * effettiva. Complementa {@link #testElementsEnumerationExhaustionThrows()},
     * centrata sugli stati degeneri.
     * </p>
     * <p>
     * <b>Test Description</b> Sulla Enumeration del vettore [elem1, elem2]
     * hasMoreElements viene interrogato tre volte consecutive prima di ogni
     * estrazione: i risultati devono restare veri finche restano elementi da
     * generare e diventare falsi dopo l'esaurimento, provando che la sola
     * interrogazione non consuma la sequenza ne altera lo stato.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2].
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> hasMore stabile.
     * </p>
     */
    @Test
    public void testElementsEnumerationHasMoreIdempotent() {
        v.addElement(E1);
        v.addElement(E2);
        Enumeration e = v.elements();
        assertTrue(e.hasMoreElements());
        assertTrue(e.hasMoreElements());
        assertTrue(e.hasMoreElements());
        assertEquals(E1, e.nextElement());
        assertTrue(e.hasMoreElements());
        assertTrue(e.hasMoreElements());
        assertEquals(E2, e.nextElement());
        assertFalse(e.hasMoreElements());
        assertFalse(e.hasMoreElements());
        assertFalse(e.hasMoreElements());
    }

    // ====================================================================
    // F) Altro (13)
    // ====================================================================

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#clone()} crei una copia shallow indipendente preservando la capacity originale.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale della clonazione secondo la
     * semantica legacy di Object/Vector. {@code clone()} deve produrre una
     * nuova istanza VectorAdapter con identica capacità, medesima taglia e
     * condivisione shallow dei riferimenti (i componenti non vengono
     * duplicati), mantenendo l'indipendenza strutturale delle due liste;
     * la nota sul capacityIncrement, privo di getter in CLDC e quindi non
     * verificabile, documenta un limite intrinseco dell'osservabilita.
     * </p>
     * <p>
     * <b>Test Description</b> Da un VectorAdapter di capacita iniziale
     * cinque contenente elem1 ed elem2 si ottiene il clone tramite cast
     * esplicito: copia e originale devono essere istanze distinte ma con
     * identica capacita, stessa taglia due e stessi elementi, di cui quello
     * in posizione zero condiviso come medesimo riferimento a prova di
     * copia shallow. L'aggiunta di dup al clone porta la sua taglia a tre
     * lasciando quella dell'originale a due; un secondo clone, estratto dal
     * vettore standard di capacita dieci, conferma infine il rispecchiamento
     * di capacita e taglia anche sul default.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2] capacity 10.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato, clone indipendente.
     * </p>
     * <p>
     * <b>Expected Results</b> clone capacity 10, size 2, shallow, modifica clone non tocca originale.
     * </p>
     */
    @Test
    public void testCloneShallowAndCapacity() {
        VectorAdapter va = new VectorAdapter(5);
        va.addElement(E1);
        va.addElement(E2);
        int cap = va.capacity();
        VectorAdapter c = (VectorAdapter) va.clone();
        assertNotSame(va, c);
        assertEquals(cap, c.capacity());
        assertEquals(2, c.size());
        assertEquals(E1, c.get(0));
        assertEquals(E2, c.get(1));
        assertSame(va.get(0), c.get(0));
        c.addElement(DUP);
        assertEquals(3, c.size());
        assertEquals(2, va.size());
        // clone da default capacity 10
        populateThree();
        VectorAdapter c2 = (VectorAdapter) v.clone();
        assertEquals(v.capacity(), c2.capacity());
        assertEquals(v.size(), c2.size());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#clone()} su vettore vuoto crei una copia vuota indipendente preservando la capacity.
     * </p>
     * <p>
     * <b>Test Case Design</b> Stato limite della clonazione: vettore vuoto,
     * per cui la copia deve presentare medesima capacità iniziale e taglia
     * nulla, senza elementi da condividere. Il caso isola il comportamento
     * della copia interna dal trasferimento dei componenti; complementa
     * {@link #testCloneShallowAndCapacity()}, condotto su lista popolata.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore vuoto di capacita dieci il clone
     * deve presentare anch'esso taglia nulla e capacita invariata, senza
     * elementi da condividere; l'aggiunta di elem1 alla sola copia porta la
     * sua taglia a uno lasciando l'originale vuoto, a prova di indipendenza
     * strutturale.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v vuoto, clone vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> clone vuoto capacity10 indipendente.
     * </p>
     */
    @Test
    public void testCloneEmptyList() {
        assertEquals(0, v.size());
        int cap = v.capacity();
        VectorAdapter c = (VectorAdapter) v.clone();
        assertNotSame(v, c);
        assertEquals(0, c.size());
        assertEquals(cap, c.capacity());
        assertTrue(c.isEmpty());
        c.addElement(E1);
        assertEquals(1, c.size());
        assertEquals(0, v.size());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che modifiche ripetute al clone e all'originale dopo {@link VectorAdapter#clone()} siano bidirezionalmente indipendenti.
     * </p>
     * <p>
     * <b>Test Case Design</b> Categoria ripetute sulla clonazione: mutazioni
     * applicate all'originale e al clone dopo la duplicazione, in entrambe
     * le direzioni. L'indipendenza bidirezionale accerta che nessuna delle
     * due liste condivida lo stato strutturale dell'altra (restano comuni
     * soltanto i riferimenti ai singoli elementi), come prescritto dalla
     * semantica shallow del contratto di {@code clone}.
     * </p>
     * <p>
     * <b>Test Description</b> Dal vettore [elem1, elem2] si ottiene il clone
     * e le mutazioni vengono alternate nelle due direzioni: l'aggiunta di
     * dup alla copia la porta a tre elementi senza toccare l'originale, la
     * rimozione della testa dall'originale lo riduce al solo elem2 lasciando
     * intatta la copia con elem1 in testa, e l'impostazione di x in
     * posizione zero sulla copia lascia elem2 al suo posto nell'originale.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2].
     * </p>
     * <p>
     * <b>Post-Condition</b> v e clone indipendenti.
     * </p>
     * <p>
     * <b>Expected Results</b> v size2, c size3 separati.
     * </p>
     */
    @Test
    public void testCloneBidirectionalIndependence() {
        v.addElement(E1);
        v.addElement(E2);
        VectorAdapter c = (VectorAdapter) v.clone();
        c.addElement(DUP);
        assertEquals(2, v.size());
        assertEquals(3, c.size());
        v.remove(0);
        assertEquals(1, v.size());
        assertEquals(3, c.size());
        assertEquals(E1, c.get(0));
        c.set(0, "x");
        assertEquals(E2, v.get(0));
        assertEquals("x", c.get(0));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#toString()} deleghi a {@code Vector.toString()} rappresentando correttamente gli elementi.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale della rappresentazione testuale
     * delegata. La stringa restituita deve contenere gli elementi nel
     * formato convenzionale di {@code java.util.Vector} (parentesi quadre e
     * separatori), cosi che il controllo di presenza documenti la
     * leggibilita della vista; il formato esatto dipende
     * dall'implementazione dell'adaptee, quindi il test verifica le
     * proprieta essenziali e non una stringa letterale.
     * </p>
     * <p>
     * <b>Test Description</b> La rappresentazione viene acquisita subito
     * dopo il setUp, con semplice controllo di non nullita, e nuovamente
     * dopo il popolamento con elem1, elem2 ed elem3: la stringa ottenuta
     * deve contenere tutti e tre gli elementi, senza pretendere un formato
     * letterale che dipende dall'implementazione dell'adaptee.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto poi con 2 elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> "[]" su vuoto, contiene elem1/elem2 su popolato.
     * </p>
     */
    @Test
    public void testToString() {
        assertNotNull(v.toString());
        populateThree();
        String s = v.toString();
        assertTrue(s.contains(E1));
        assertTrue(s.contains(E2));
        assertTrue(s.contains(E3));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#toString()} su vettore vuoto restituisca la rappresentazione {@code "[]"} come da delega a {@code Vector.toString()}.
     * </p>
     * <p>
     * <b>Test Case Design</b> Stato limite della rappresentazione testuale:
     * lista vuota, per cui il Vector sottostante produce la stringa
     * canonica {@code []}. L'uguaglianza esatta e osservabile proprio in
     * questa configurazione degenere, priva di separatori; complementa
     * {@link #testToString()}, che verifica solo la presenza degli elementi
     * sugli stati popolati.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore lasciato vuoto dal setUp il
     * confronto esatto di toString() con la stringa canonica [], ripetuto
     * due volte e affiancato dalla verifica di isEmpty, diventa osservabile
     * proprio in assenza di elementi e separatori.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> toString() == "[]".
     * </p>
     */
    @Test
    public void testToStringEmptyListBoundary() {
        assertEquals("[]", v.toString());
        assertTrue(v.isEmpty());
        assertEquals("[]", v.toString());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#toString()} con un vettore che contiene se stesso
     * rappresenti il riferimento ciclico come {@code "(this Collection)"}.
     * </p>
     * <p>
     * <b>Test Case Design</b> J2SE 1.4.2 {@code AbstractCollection.toString()}
     * rileva i riferimenti ciclici confrontando {@code e == this} e li
     * sostituisce con {@code "(this Collection)"}. L'override di
     * {@link VectorAdapter#toString()} replica questo comportamento,
     * verificando che {@code this} si riferisca al VectorAdapter e non
     * al Vector CLDC sottostante. Complementa {@link #testToString()} e
     * {@link #testToStringEmptyListBoundary()} che testano solo casi
     * aciclici.
     * </p>
     * <p>
     * <b>Test Description</b> Si popola {@code v} con [E1] e si invoca
     * {@code v.addElement(v)} per creare un riferimento ciclico. La chiamata
     * {@code v.toString()} deve restituire una stringa che contiene
     * {@code "(this Collection)"} come ultimo elemento rappresentato,
     * senza entrare in ricorsione infinita.
     * </p>
     * <p>
     * <b>Pre-Condition</b> {@code v = [E1]}, poi {@code v = [E1, v]}.
     * </p>
     * <p>
     * <b>Post-Condition</b> {@code v = [E1, v]} (self-referencing).
     * </p>
     * <p>
     * <b>Expected Results</b> {@code toString()} restituisce una stringa contenente
     * {@code "(this Collection)"} come ultimo elemento.
     * </p>
     */
    @Test
    public void testToStringWithCyclicReference() {
        v.addElement(E1);
        v.addElement(v);
        String s = v.toString();
        assertTrue(s.contains("(this Collection)"));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@code removeRange} protetto sia correttamente invocato tramite {@link HCollection#clear()} con rimozione di intervallo.
     * </p>
     * <p>
     * <b>Test Case Design</b> Verifica indiretta dell'operazione protetta
     * {@code removeRange(fromIndex,toIndex)}, non invocabile direttamente
     * dal test perche estranea all'interfaccia pubblica. {@code clear()} ne
     * costituisce il principale cliente interno (intervallo
     * {@code [0,size)}), quindi la coppia svuotamento/capacità-invariata su
     * una lista di cinque elementi implementa il comportamento atteso della
     * primitiva secondo lo schema di {@code AbstractList}.
     * </p>
     * <p>
     * <b>Test Description</b> Si costruisce il vettore a cinque componenti
     * [elem1, elem2, elem3, dup, dup] e se ne annota la capacita prima di
     * clear(): la taglia deve azzerarsi restando la lista vuota e la
     * capacita immutata, coerentemente con la delega interna a removeRange.
     * Il ciclo si ripete ripopolando tre elementi e rieseguendo clear(),
     * stavolta su una lista ricostruita dopo le rimozioni precedenti.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v con 5 elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> v vuoto, capacity invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> clear svuota via removeRange.
     * </p>
     */
    @Test
    public void testRemoveRangeViaClear() {
        v.addElement(E1);
        v.addElement(E2);
        v.addElement(E3);
        v.addElement(DUP);
        v.addElement(DUP);
        assertEquals(5, v.size());
        int cap = v.capacity();
        v.clear();
        assertEquals(0, v.size());
        assertTrue(v.isEmpty());
        assertEquals(cap, v.capacity());
        populateThree();
        assertEquals(3, v.size());
        v.clear();
        assertEquals(0, v.size());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HCollection#iterator()} iteri nell'ordine, supporti remove e lanci {@link NoSuchElementException} e {@link IllegalStateException} quando appropriato.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale dell'iteratore HIterator con
     * verifica integrata delle condizioni di errore. Il protocollo
     * hasNext/next deve generare gli elementi in ordine, remove() deve
     * cancellare l'ultimo elemento restituito una sola volta per next, con
     * IllegalStateException alla seconda rimozione e NoSuchElementException
     * oltre la fine, secondo il contratto {@code java.util.Iterator} di
     * J2SE 1.4.2 ripreso da HIterator. Complementa la suite dedicata
     * IteratorAdapterTest per le casistiche estese.
     * </p>
     * <p>
     * <b>Test Description</b> Sull'iteratore del vettore [elem1, elem2,
     * elem3] hasNext e subito vero e il primo next restituisce elem1; la
     * remove() che segue cancella proprio quell'elemento portando la lista a
     * [elem2, elem3], mentre una seconda remove senza next intermedio viene
     * respinta con IllegalStateException nel blocco try/fail/catch. La
     * scansione prosegue con elem2 ed elem3 fino all'esaurimento, dove un
     * ulteriore next deve lanciare NoSuchElementException; su un secondo
     * vettore vuoto, infine, hasNext e falso e next lancia lo stesso errore.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2,elem3].
     * </p>
     * <p>
     * <b>Post-Condition</b> v=[elem2,elem3] dopo prima remove (size 2).
     * </p>
     * <p>
     * <b>Expected Results</b> next in ordine, remove muta, IllegalState/NoSuchElement lanciate.
     * </p>
     */
    @Test
    public void testIteratorBasic() {
        populateThree();
        HIterator it = v.iterator();
        assertTrue(it.hasNext());
        assertEquals(E1, it.next());
        it.remove();
        assertEquals(2, v.size());
        assertEquals(E2, v.get(0));
        try {
            it.remove();
            fail("seconda remove senza next deve lanciare IllegalStateException");
        } catch (IllegalStateException e) { /* atteso */ }
        assertEquals(E2, it.next());
        assertEquals(E3, it.next());
        assertFalse(it.hasNext());
        try {
            it.next();
            fail("next su esaurito deve lanciare NoSuchElementException");
        } catch (NoSuchElementException e) { /* atteso */ }
        // iterator su vuoto
        VectorAdapter empty = new VectorAdapter();
        HIterator it2 = empty.iterator();
        assertFalse(it2.hasNext());
        try {
            it2.next();
            fail("next su vuoto deve lanciare NoSuchElementException");
        } catch (NoSuchElementException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#listIterator()} iteri correttamente avanti e indietro in uso normale.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale del movimento bidirezionale di
     * HListIterator. La scansione avanti e indietro sull'intera lista
     * verifica la simmetria dei metodi previous/next e la coerenza degli
     * indici nextIndex/previousIndex ad ogni passo, proprieta richieste dal
     * contratto {@code java.util.ListIterator} di J2SE 1.4.2; la partenza
     * da {@code listIterator(0)} copre la posizione iniziale canonica.
     * </p>
     * <p>
     * <b>Test Description</b> Partito da listIterator(0) sul vettore
     * [elem1, elem2, elem3], il cursore attraversa tutta la lista con tre
     * next, controllando ad ogni passo la coppia di indici
     * nextIndex/previousIndex e i predicati hasNext/hasPrevious agli estremi,
     * per poi risalirla integralmente con tre previous fino a ritrovare
     * hasPrevious falso in posizione zero.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2,elem3].
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato, iteratore a inizio/fine.
     * </p>
     * <p>
     * <b>Expected Results</b> nextIndex e previousIndex corretti.
     * </p>
     */
    @Test
    public void testListIteratorForwardAndBackward() {
        populateThree();
        HListIterator it = v.listIterator();
        assertTrue(it.hasNext());
        assertFalse(it.hasPrevious());
        assertEquals(0, it.nextIndex());
        assertEquals(-1, it.previousIndex());
        assertEquals(E1, it.next());
        assertEquals(1, it.nextIndex());
        assertEquals(0, it.previousIndex());
        assertEquals(E2, it.next());
        assertEquals(E3, it.next());
        assertFalse(it.hasNext());
        assertTrue(it.hasPrevious());
        assertEquals(E3, it.previous());
        assertEquals(E2, it.previous());
        assertEquals(E1, it.previous());
        assertFalse(it.hasPrevious());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#listIterator()} su vettore vuoto gestisca correttamente i limiti di hasNext e hasPrevious.
     * </p>
     * <p>
     * <b>Test Case Design</b> Stato limite dell'iteratore bidirezionale sul
     * contenitore vuoto: entrambe le direzioni devono risultare
     * inaccessibili (hasNext false, hasPrevious false). La configurazione e
     * la sola in cui i due predicati coincidono sul valore false senza
     * eccezioni; il comportamento segue dal contratto ListIterator, che
     * definisce i predicati rispetto alla posizione corrente del cursore.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore vuoto listIterator() offre
     * entrambe le direzioni inaccessibili: hasNext e hasPrevious sono falsi,
     * gli indici valgono rispettivamente zero e -1, e sia next sia previous
     * vengono respinti con NoSuchElementException dentro i blocchi
     * try/fail/catch. Un secondo iteratore creato con listIterator(0),
     * indice ammesso anche a taglia nulla, conferma l'assenza di un elemento
     * successivo.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> v vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> false, eccezioni.
     * </p>
     */
    @Test
    public void testListIteratorOnEmptyList() {
        HListIterator it = v.listIterator();
        assertFalse(it.hasNext());
        assertFalse(it.hasPrevious());
        assertEquals(0, it.nextIndex());
        assertEquals(-1, it.previousIndex());
        try { it.next(); fail("next su vuoto deve lanciare"); } catch (NoSuchElementException e) { /* atteso */ }
        try { it.previous(); fail("previous su vuoto deve lanciare"); } catch (NoSuchElementException e) { /* atteso */ }
        HListIterator it2 = v.listIterator(0);
        assertFalse(it2.hasNext());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HList#listIterator(int)} lanci {@link IndexOutOfBoundsException} per indici non validi.
     * </p>
     * <p>
     * <b>Test Case Design</b> Classe di errore dell'indice iniziale per
     * listIterator(int). Gli indici -1 e size+1 sono le minime violazioni
     * dei limiti dell'intervallo valido {@code [0,size]} previsto dal
     * contratto {@link HList#listIterator(int)}, che prescrive
     * IndexOutOfBoundsException; su lista vuota l'indice size+1 coincide
     * con 1, frontiera inferiore particolarmente sensibile.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore [elem1, elem2] gli indici -1 e 3,
     * minimhe violazioni dei limiti dell'intervallo valido [0, 2], vengono
     * passati a listIterator(int) entro blocchi try/fail/catch in attesa di
     * IndexOutOfBoundsException; su un secondo vettore vuoto anche l'indice
     * 1, coincidente con size+1, deve lanciare.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2].
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> IndexOutOfBoundsException.
     * </p>
     */
    @Test
    public void testListIteratorInvalidIndicesThrows() {
        v.addElement(E1);
        v.addElement(E2);
        try { v.listIterator(-1); fail("listIterator(-1) deve lanciare"); } catch (IndexOutOfBoundsException e) { /* atteso */ }
        try { v.listIterator(3); fail("listIterator(3) su size2 deve lanciare"); } catch (IndexOutOfBoundsException e) { /* atteso */ }
        VectorAdapter empty = new VectorAdapter();
        try { empty.listIterator(1); fail("listIterator(1) su vuoto deve lanciare"); } catch (IndexOutOfBoundsException e) { /* atteso */ }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HListIterator#hasPrevious()} e {@link HListIterator#previous()} gestiscano correttamente i limiti.
     * </p>
     * <p>
     * <b>Test Case Design</b> Valori di frontiera del cursore: posizioni
     * iniziale (0) e finale (size), estremi dell'intervallo di
     * posizionamento valido. All'inizio previous non e disponibile e lancia
     * NoSuchElementException, alla fine next si esaurisce analogamente,
     * mentre i predicati riflettono la disponibilita asimmetrica nelle due
     * direzioni; questi estremi implementano le clausole di frontiera del
     * contratto ListIterator. Complementa
     * {@link #testListIteratorForwardAndBackward()} sulla scansione
     * completa.
     * </p>
     * <p>
     * <b>Test Description</b> Sul vettore [elem1, elem2] un iteratore posto
     * in zero presenta hasPrevious falso e previous respinto con
     * NoSuchElementException; uno posto alla fine, indice due, presenta
     * invece hasNext falso e hasPrevious vero, e la discesa con due previous
     * recupera elem2 ed elem1 fino a ritrovare hasPrevious falso.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2].
     * </p>
     * <p>
     * <b>Post-Condition</b> v invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> hasPrevious corretto, eccezione su inizio.
     * </p>
     */
    @Test
    public void testListIteratorHasPreviousPreviousBoundary() {
        v.addElement(E1);
        v.addElement(E2);
        HListIterator it0 = v.listIterator(0);
        assertFalse(it0.hasPrevious());
        try { it0.previous(); fail("previous su inizio deve lanciare"); } catch (NoSuchElementException e) { /* atteso */ }
        HListIterator itEnd = v.listIterator(2);
        assertFalse(itEnd.hasNext());
        assertTrue(itEnd.hasPrevious());
        assertEquals(E2, itEnd.previous());
        assertEquals(E1, itEnd.previous());
        assertFalse(itEnd.hasPrevious());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link HListIterator#set(Object)}, {@link HListIterator#add(Object)} e {@link HListIterator#remove()} funzionino in uso normale.
     * </p>
     * <p>
     * <b>Test Case Design</b> Caso normale delle tre operazioni mutative
     * dell'iteratore bidirezionale: set sostituisce l'ultimo elemento
     * restituito, add inserisce prima del cursore avanzandolo, remove
     * elimina l'ultimo elemento restituito invalidando l'operazione
     * successiva. La sequenza combinata verifica lo stato post-operazione
     * della lista e del cursore secondo il contratto
     * {@code java.util.ListIterator} di J2SE 1.4.2, incluse le regole di
     * validita che vietano set/remove dopo add.
     * </p>
     * <p>
     * <b>Test Description</b> Posto in posizione uno sul vettore
     * [elem1, elem2, elem3], l'iteratore legge elem2 con next e lo
     * sostituisce con x tramite set, portando la lista a [elem1, x, elem3];
     * l'add di y inserisce prima del cursore facendo salire la taglia a
     * quattro con y in posizione due. Dopo l'inserzione l'ultima operazione
     * non e piu valida: un previous restituisce proprio y, la remove() che
     * lo segue lo cancella riportando la lista a [elem1, x, elem3], e su un
     * iteratore fresco set e remove senza next o previous preliminari
     * vengono entrambi respinti con IllegalStateException.
     * </p>
     * <p>
     * <b>Pre-Condition</b> v=[elem1,elem2,elem3].
     * </p>
     * <p>
     * <b>Post-Condition</b> v=[elem1,x,elem3] finale.
     * </p>
     * <p>
     * <b>Expected Results</b> set/add/remove corretti.
     * </p>
     */
    @Test
    public void testListIteratorSetAddRemoveNormal() {
        populateThree();
        HListIterator it = v.listIterator(1);
        assertEquals(E2, it.next());
        it.set("x");
        assertEquals("x", v.get(1));
        it.add("y");
        assertEquals(4, v.size());
        assertEquals("y", v.get(2));
        assertEquals(E3, v.get(3));
        // it ora dopo add ha lastRet=-1, previous deve dare y
        assertEquals("y", it.previous());
        it.remove();
        assertEquals(3, v.size());
        assertEquals("x", v.get(1));
        assertEquals(E3, v.get(2));
        // set senza next/previous deve lanciare
        HListIterator it2 = v.listIterator();
        try { it2.set("z"); fail("set senza next deve lanciare IllegalState"); } catch (IllegalStateException e) { /* atteso */ }
        try { it2.remove(); fail("remove senza next deve lanciare IllegalState"); } catch (IllegalStateException e) { /* atteso */ }
    }

}
