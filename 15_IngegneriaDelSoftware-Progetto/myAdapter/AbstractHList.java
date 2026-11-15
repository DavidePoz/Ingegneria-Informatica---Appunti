package myAdapter;

import java.util.NoSuchElementException;

/**
 * Classe astratta scheletrica che fornisce un'implementazione di base
 * dell'interfaccia {@link HList}, analogamente a {@code java.util.AbstractList}
 * di J2SE 1.4.2.
 * <p>
 * Per implementare una lista non modificabile è sufficiente estendere questa
 * classe e fornire le implementazioni dei metodi {@link #size()} e
 * {@link #get(int)}. 
 * <p>
 * Per una lista modificabile è necessario sovrascrivere
 * anche {@link #set(int,Object)}, {@link #add(int,Object)} e
 * {@link #remove(int)}; che altrimenti lancerebbero
 * {@code MyUnsupportedOperationException} se non sovrascritte nella sottoclasse
 * concreta.
 * Se la lista è di dimensione variabile, il programmatore deve anche sovrascrivere
 * i metodi {@link #add(int index, Object element)} e {@link #remove(int index)}.
 * <p>
 * Il programmatore dovrebbe inoltre, generalmente, fornire un costruttore void 
 * ed uno che parta da un'altra collezione, come raccomandato dall'interfaccia {@code HCollection}.
 * <p>
 * Al contrario di altre implementazioni astratte di una collezione, il programmatore
 * <i>non</i> deve necessariamente fornire un'implementazione degli iteratori, che sono
 * già implementati da questa classe.
 * <p>
 * Ciascun metodo non astratto può essere sovrascritto se la collezione che deve essere implementata
 * ammette delle implementazioni più efficienti.
 */
public abstract class AbstractHList implements HList {

    /**
     * Costruttore protetto per sottoclassi.
     */
    protected AbstractHList() {}

    // ====================================================================
    // Metodi astratti 
    // ====================================================================

    /**
     * Restituisce il numero di elementi in questa lista.
     * <p>
     * Se la lista contiene più di {@code Integer.MAX_VALUE} elementi,
     * restituisce {@code Integer.MAX_VALUE}.
     *
     * @return il numero di elementi in questa lista
     */
    public abstract int size();

    /**
     * Restituisce l'elemento alla posizione specificata in questa lista.
     *
     * @param index l'indice dell'elemento da restituire
     * @return l'elemento alla posizione specificata
     * @throws IndexOutOfBoundsException se {@code index < 0 || index >= size()}
     */
    public abstract Object get(int index);

    /**
     * Sostituisce l'elemento alla posizione specificata con quello indicato
     * (operazione opzionale).
     *
     * @param index l'indice dell'elemento da sostituire
     * @param element l'elemento da memorizzare alla posizione specificata
     * @return l'elemento precedentemente alla posizione specificata
     * @throws MyUnsupportedOperationException se l'operazione non è supportata
     * @throws ClassCastException se la classe dell'elemento impedisce l'aggiunta
     * @throws IllegalArgumentException se qualche aspetto dell'elemento ne impedisce l'aggiunta
     * @throws IndexOutOfBoundsException se {@code index < 0 || index >= size()}
     */
    public abstract Object set(int index, Object element);

    /**
     * Inserisce l'elemento specificato alla posizione indicata (operazione
     * opzionale). Sposta l'elemento attualmente in quella posizione e gli
     * eventuali successivi verso destra.
     *
     * @param index l'indice in cui inserire l'elemento
     * @param element l'elemento da inserire
     * @throws MyUnsupportedOperationException se l'operazione non è supportata
     * @throws ClassCastException se la classe dell'elemento ne impedisce l'aggiunta
     * @throws IllegalArgumentException se qualche aspetto dell'elemento ne impedisce l'aggiunta
     * @throws IndexOutOfBoundsException se {@code index < 0 || index > size()}
     */
    public abstract void add(int index, Object element);

    /**
     * Rimuove l'elemento alla posizione specificata (operazione opzionale).
     * Sposta gli eventuali elementi successivi verso sinistra.
     *
     * @param index l'indice dell'elemento da rimuovere
     * @return l'elemento precedentemente alla posizione specificata
     * @throws MyUnsupportedOperationException se l'operazione non è supportata
     * @throws IndexOutOfBoundsException se {@code index < 0 || index >= size()}
     */
    public abstract Object remove(int index);

    // ====================================================================
    // Implementazioni HCollection / HList
    // ====================================================================

    /**
     * Restituisce {@code true} se questa lista non contiene elementi.
     * <p>
     * Implementazione: {@code return size() == 0}.
     *
     * @return {@code true} se vuota
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Restituisce {@code true} se questa lista contiene l'elemento specificato.
     * <p>
     * Più formalmente, {@code true} se e solo se esiste un {@code e} tale che
     * {@code (o==null ? e==null : o.equals(e))}.
     *
     * @param o l'elemento di cui verificare la presenza
     * @return {@code true} se presente
     * @throws ClassCastException se il tipo è incompatibile (opzionale)
     * @throws NullPointerException se {@code o==null} e la lista non supporta null (opzionale)
     */
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * Restituisce un iteratore sugli elementi nell'ordine corretto.
     * <p>
     * L'iteratore è quello di {@link IteratorAdapter}.
     *
     * @return un {@code HIterator} sugli elementi
     */
    public HIterator iterator() {
        return new IteratorAdapter();
    }

    /**
     * Restituisce un array contenente tutti gli elementi nell'ordine corretto.
     * Obbedisce al contratto di {@code HCollection.toArray()}.
     * L'array è sempre un nuovo {@code Object[]} allocato.
     *
     * @return array contenente gli elementi
     */
    public Object[] toArray() {
        Object[] arr = new Object[size()];
        for (int i = 0; i < arr.length; i++) arr[i] = get(i);
        return arr;
    }

    /**
     * Restituisce un array contenente tutti gli elementi nell'ordine corretto;
     * il tipo runtime è quello dell'array specificato solo se sufficientemente
     * grande, altrimenti viene allocato un nuovo {@code Object[]} (CLDC 1.1:
     * niente reflection, il tipo runtime non è preservato, come concordato).
     * <p>
     * Se {@code a.length >= size()}, gli elementi sono copiati in {@code a} e
     * {@code a[size()]} è posto a {@code null} se {@code a.length > size()}.
     * Altrimenti è allocato un nuovo array {@code Object[size()]}.
     *
     * @param a l'array in cui memorizzare gli elementi, se sufficientemente grande
     * @return array contenente gli elementi
     * @throws ArrayStoreException se il tipo di {@code a} non è compatibile (solo se si tenta un cast esterno)
     * @throws NullPointerException se {@code a == null}
     */
    public Object[] toArray(Object[] a) {
        if (a == null) throw new NullPointerException();
        int sz = size();
        if (a.length < sz) {
            Object[] newArr = new Object[sz];
            for (int i = 0; i < sz; i++) newArr[i] = get(i);
            return newArr;
        }
        for (int i = 0; i < sz; i++) a[i] = get(i);
        if (a.length > sz) a[sz] = null;
        return a;
    }

    /**
     * Aggiunge l'elemento specificato in coda (operazione opzionale).
     * <p>
     * Implementazione: {@code add(size(), o)}.
     *
     * @param o elemento da aggiungere
     * @return {@code true} (come da contratto {@code HCollection.add})
     * @throws MyUnsupportedOperationException se non supportata
     * @throws ClassCastException se la classe dell'elemento ne impedisce l'aggiunta
     * @throws NullPointerException se {@code o==null} e la lista non supporta null
     * @throws IllegalArgumentException se qualche aspetto dell'elemento ne impedisce l'aggiunta
     */
    public boolean add(Object o) {
        add(size(), o);
        return true;
    }

    /**
     * Rimuove la prima occorrenza dell'elemento specificato, se presente
     * (operazione opzionale). Restituisce {@code true} se la lista è mutata.
     *
     * @param o elemento da rimuovere
     * @return {@code true} se presente e rimosso
     * @throws ClassCastException se il tipo è incompatibile (opzionale)
     * @throws NullPointerException se {@code o==null} e la lista non supporta null (opzionale)
     * @throws MyUnsupportedOperationException se non supportata
     */
    public boolean remove(Object o) {
        int idx = indexOf(o);
        if (idx == -1) return false;
        remove(idx);
        return true;
    }

    /**
     * Restituisce {@code true} se questa lista contiene tutti gli elementi
     * della collezione specificata.
     *
     * @param c collezione da verificare
     * @return {@code true} se contiene tutti gli elementi di {@code c}
     * @throws ClassCastException se il tipo di un elemento è incompatibile (opzionale)
     * @throws NullPointerException se {@code c == null}
     */
    public boolean containsAll(HCollection c) {
        if (c == null) throw new NullPointerException();
        HIterator it = c.iterator();
        while (it.hasNext()) if (!contains(it.next())) return false;
        return true;
    }

    /**
     * Aggiunge tutti gli elementi della collezione specificata in coda,
     * nell'ordine dell'iteratore di {@code c} (operazione opzionale).
     *
     * @param c collezione di elementi da aggiungere
     * @return {@code true} se la lista è stata modificata
     * @throws MyUnsupportedOperationException se non supportata
     * @throws ClassCastException se la classe di un elemento ne impedisce l'aggiunta
     * @throws NullPointerException se {@code c==null} o contiene null non supportati
     * @throws IllegalArgumentException se qualche aspetto di un elemento ne impedisce l'aggiunta
     */
    public boolean addAll(HCollection c) {
        return addAll(size(), c);
    }

    /**
     * Inserisce tutti gli elementi della collezione specificata alla posizione
     * indicata (operazione opzionale). Sposta gli elementi successivi verso destra.
     *
     * @param index indice in cui inserire il primo elemento
     * @param c collezione di elementi da inserire
     * @return {@code true} se la lista è stata modificata
     * @throws MyUnsupportedOperationException se non supportata
     * @throws ClassCastException se la classe di un elemento ne impedisce l'aggiunta
     * @throws NullPointerException se {@code c==null} o contiene null non supportati
     * @throws IllegalArgumentException se qualche aspetto di un elemento ne impedisce l'aggiunta
     * @throws IndexOutOfBoundsException se {@code index < 0 || index > size()}
     */
    public boolean addAll(int index, HCollection c) {
        if (c == null) throw new NullPointerException();
        if (index < 0 || index > size()) throw new IndexOutOfBoundsException();
        HIterator it = c.iterator();
        boolean modified = false;
        int cursor = index;
        while (it.hasNext()) {
            add(cursor++, it.next());
            modified = true;
        }
        return modified;
    }

    /**
     * Rimuove tutti gli elementi contenuti nella collezione specificata
     * (operazione opzionale).
     *
     * @param c collezione che definisce gli elementi da rimuovere
     * @return {@code true} se la lista è stata modificata
     * @throws MyUnsupportedOperationException se non supportata
     * @throws ClassCastException se il tipo è incompatibile (opzionale)
     * @throws NullPointerException se {@code c==null}
     */
    public boolean removeAll(HCollection c) {
        if (c == null) throw new NullPointerException();
        boolean modified = false;
        HIterator it = iterator();
        while (it.hasNext()) {
            if (c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Mantiene solo gli elementi contenuti nella collezione specificata
     * (operazione opzionale). Rimuove tutti gli altri.
     *
     * @param c collezione che definisce gli elementi da mantenere
     * @return {@code true} se la lista è stata modificata
     * @throws MyUnsupportedOperationException se non supportata
     * @throws ClassCastException se il tipo è incompatibile (opzionale)
     * @throws NullPointerException se {@code c==null}
     */
    public boolean retainAll(HCollection c) {
        if (c == null) throw new NullPointerException();
        boolean modified = false;
        HIterator it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Rimuove tutti gli elementi (operazione opzionale).
     * <p>
     * Implementazione: {@code removeRange(0,size())}.
     *
     * @throws MyUnsupportedOperationException se non supportata
     */
    public void clear() {
        removeRange(0, size());
    }

    /**
     * Confronta l'oggetto specificato con questa lista per l'uguaglianza.
     * Due liste sono uguali se contengono gli stessi elementi nello stesso
     * ordine (confronto con {@code equals} o entrambi {@code null}).
     *
     * @param o oggetto da confrontare
     * @return {@code true} se uguale a questa lista
     * @see #hashCode()
     */
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof HList)) return false;
        HList other = (HList) o;
        if (size() != other.size()) return false;
        HIterator it1 = iterator();
        HIterator it2 = other.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            Object e1 = it1.next();
            Object e2 = it2.next();
            if (!(e1 == null ? e2 == null : e1.equals(e2))) return false;
        }
        return true;
    }

    /**
     * Restituisce l'hash code per questa lista come:
     * <pre>hash = 1; for (e : list) hash = 31*hash + (e==null?0:e.hashCode());</pre>
     *
     * @return hash code
     * @see #equals(Object)
     */
    public int hashCode() {
        int hash = 1;
        for (int i = 0; i < size(); i++) {
            Object obj = get(i);
            hash = 31 * hash + (obj == null ? 0 : obj.hashCode());
        }
        return hash;
    }

    /**
     * Restituisce l'indice della prima occorrenza dell'elemento specificato,
     * o {@code -1} se assente. Ricerca lineare.
     *
     * @param o elemento da cercare
     * @return indice della prima occorrenza, {@code -1} se assente
     * @throws ClassCastException se il tipo è incompatibile (opzionale)
     * @throws NullPointerException se {@code o==null} e la lista non supporta null (opzionale)
     */
    public int indexOf(Object o) {
        for (int i = 0; i < size(); i++) {
            Object e = get(i);
            if (o == null ? e == null : o.equals(e)) return i;
        }
        return -1;
    }

    /**
     * Restituisce l'indice dell'ultima occorrenza dell'elemento specificato,
     * o {@code -1} se assente. Ricerca lineare all'indietro.
     *
     * @param o elemento da cercare
     * @return indice dell'ultima occorrenza, {@code -1} se assente
     * @throws ClassCastException se il tipo è incompatibile (opzionale)
     * @throws NullPointerException se {@code o==null} e la lista non supporta null (opzionale)
     */
    public int lastIndexOf(Object o) {
        for (int i = size() - 1; i >= 0; i--) {
            Object e = get(i);
            if (o == null ? e == null : o.equals(e)) return i;
        }
        return -1;
    }

    /**
     * Restituisce un iteratore di lista sugli elementi (nell'ordine corretto)
     * partendo dall'inizio.
     *
     * @return un {@code HListIterator}
     * @see #listIterator(int)
     */
    public HListIterator listIterator() {
        return listIterator(0);
    }

    /**
     * Restituisce un iteratore di lista partendo dall'indice specificato.
     * {@code index} indica il primo elemento restituito da {@code next()}.
     *
     * @param index indice del primo elemento da restituire (0..size())
     * @return un {@code HListIterator} posizionato a {@code index}
     * @throws IndexOutOfBoundsException se {@code index < 0 || index > size()}
     */
    public HListIterator listIterator(int index) {
        if (index < 0 || index > size()) throw new IndexOutOfBoundsException();
        return new ListIteratorAdapter(index);
    }

    /**
     * Restituisce una vista della porzione di questa lista compresa tra
     * {@code fromIndex} (incluso) e {@code toIndex} (escluso). La vista è
     * backed: modifiche alla vista o alla lista si riflettono reciprocamente.
     * <p>
     * Se {@code fromIndex==toIndex} la vista è vuota.
     * <p>
     * La semantica della lista restituita diventa <i>indefinita</i> se la
     * lista di backing (questa lista) viene modificata strutturalmente in
     * qualsiasi modo che non avvenga attraverso la lista restituita.
     * Come in {@code java.util.AbstractList}, questa implementazione non
     * rileva tali modifiche esterne (nessun meccanismo di fail-fast).
     *
     * @param fromIndex estremo inferiore inclusivo
     * @param toIndex estremo superiore esclusivo
     * @return vista {@code [fromIndex,toIndex)}
     * @throws IndexOutOfBoundsException se {@code fromIndex<0 || toIndex>size() || fromIndex>toIndex}
     */
    public HList subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex)
            throw new IndexOutOfBoundsException();
        return new SubList(this, fromIndex, toIndex);
    }

    /**
     * Rimuove l'intervallo {@code [fromIndex,toIndex)} (operazione protetta).
     * Sposta gli elementi successivi verso sinistra. Usata da {@link #clear()}
     * e dalle viste subList. Questa implementazione esegue
     * {@code remove(fromIndex)} ripetutamente.
     *
     * @param fromIndex inizio inclusivo
     * @param toIndex fine esclusiva
     */
    protected void removeRange(int fromIndex, int toIndex) {
        int n = toIndex - fromIndex;
        for (int i = 0; i < n; i++) remove(fromIndex);
    }

    // ====================================================================
    // Vista backed subList
    // ====================================================================

    /**
     * Vista backed {@code [offset, offset+size)} su {@code parent}.
     * Estende {@code AbstractHList} così eredita bulk e iteratori.
     * Ogni operazione primitiva è tradotta con {@code offset+index} sul parent.
     * Supporta sub-subList annidate tramite delega ricorsiva (parent può essere
     * a sua volta una SubList).
     */
    private class SubList extends AbstractHList {
        /** Lista parent di backing. */
        private final AbstractHList parent;
        /** Offset nel parent. */
        private final int offset;
        /** Dimensione della vista. */
        private int size;

        /**
         * Crea una vista.
         *
         * @param parent lista parent
         * @param fromIndex inizio inclusivo nel parent
         * @param toIndex fine esclusiva nel parent
         */
        SubList(AbstractHList parent, int fromIndex, int toIndex) {
            this.parent = parent;
            this.offset = fromIndex;
            this.size = toIndex - fromIndex;
        }

        /**
         * {@inheritDoc}
         */
        public int size() { return size; }

        /**
         * {@inheritDoc}
         * @throws IndexOutOfBoundsException se {@code index < 0 || index >= size()}
         */
        public Object get(int index) {
            if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
            return parent.get(offset + index);
        }

        /**
         * {@inheritDoc}
         * @throws IndexOutOfBoundsException se {@code index < 0 || index >= size()}
         */
        public Object set(int index, Object element) {
            if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
            return parent.set(offset + index, element);
        }

        /**
         * {@inheritDoc}
         * @throws IndexOutOfBoundsException se {@code index < 0 || index > size()}
         */
        public void add(int index, Object element) {
            if (index < 0 || index > size) throw new IndexOutOfBoundsException();
            parent.add(offset + index, element);
            size++;
        }

        /**
         * {@inheritDoc}
         * @throws IndexOutOfBoundsException se {@code index < 0 || index >= size()}
         */
        public Object remove(int index) {
            if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
            Object r = parent.remove(offset + index);
            size--;
            return r;
        }

        /**
         * {@inheritDoc}
         * @throws IndexOutOfBoundsException se {@code fromIndex<0 || toIndex>size() || fromIndex>toIndex}
         */
        public HList subList(int fromIndex, int toIndex) {
            if (fromIndex < 0 || toIndex > size || fromIndex > toIndex)
                throw new IndexOutOfBoundsException();
            return new SubList(this, fromIndex, toIndex);
        }
    }

    // ====================================================================
    // Implementazione iteratori 
    // ====================================================================

    /**
     * Iteratore semplice sugli elementi nell'ordine corretto.
     * Implementa {@link HIterator} usando i primitivi {@link #size()},
     * {@link #get(int)} e {@link #remove(int)} della classe esterna.
     * <p>
     * Supporta {@code hasNext/next/remove} con semantica J2SE 1.4.2:
     * {@code remove} rimuove l'ultimo elemento restituito da {@code next()}
     * e può essere invocata una sola volta per ogni {@code next()}.
     */
    protected class IteratorAdapter implements HIterator {
        /** Posizione del prossimo elemento da restituire. */
        protected int cursor = 0;
        /** Indice dell'ultimo elemento restituito, -1 se nessuno o già rimosso. */
        protected int lastRet = -1;

        /** Costruttore. */
        protected IteratorAdapter() {}

        /**
         * Restituisce {@code true} se ci sono altri elementi.
         *
         * @return {@code true} se {@code next()} non lancerebbe eccezione
         */
        public boolean hasNext() {
            return cursor != size();
        }

        /**
         * Restituisce il prossimo elemento.
         *
         * @return il prossimo elemento
         * @throws NoSuchElementException se non ci sono altri elementi
         */
        public Object next() {
            if (!hasNext()) throw new NoSuchElementException();
            lastRet = cursor;
            return get(cursor++);
        }

        /**
         * Rimuove l'ultimo elemento restituito da {@code next()} (opzionale).
         *
         * @throws MyUnsupportedOperationException se non supportata (mai qui)
         * @throws IllegalStateException se {@code next()} non è stato ancora chiamato
         *         o {@code remove()} è già stata chiamata dopo l'ultima {@code next()}
         */
        public void remove() {
            if (lastRet == -1) throw new IllegalStateException();
            AbstractHList.this.remove(lastRet);
            if (lastRet < cursor) cursor--;
            lastRet = -1;
        }
    }

    /**
     * Iteratore bidirezionale su liste che estende {@link IteratorAdapter}.
     * <p>
     * Aggiunge navigazione all'indietro e operazioni {@code set/add} come da
     * {@link HListIterator} J2SE 1.4.2. La posizione del cursore è sempre tra
     * l'elemento che verrebbe restituito da {@code previous()} e quello di
     * {@code next()}; in una lista di {@code n} elementi ci sono {@code n+1}
     * posizioni valide {@code 0..n}.
     */
    protected class ListIteratorAdapter extends IteratorAdapter implements HListIterator {

        /**
         * Crea un iteratore posizionato a {@code index}.
         *
         * @param index l'indice del primo elemento da restituire con {@code next()}
         *               ( {@code 0 <= index <= size()} )
         */
        ListIteratorAdapter(int index) {
            this.cursor = index;
        }

        /**
         * Restituisce {@code true} se ci sono elementi procedendo all'indietro.
         *
         * @return {@code true} se {@code previous()} non lancerebbe eccezione
         */
        public boolean hasPrevious() { return cursor != 0; }

        /**
         * Restituisce l'elemento precedente.
         *
         * @return l'elemento precedente
         * @throws NoSuchElementException se non esiste elemento precedente
         */
        public Object previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            lastRet = --cursor;
            return get(cursor);
        }

        /**
         * Restituisce l'indice dell'elemento che verrebbe restituito da {@code next()}.
         *
         * @return {@code cursor}, o {@code size()} se alla fine
         */
        public int nextIndex() { return cursor; }

        /**
         * Restituisce l'indice dell'elemento che verrebbe restituito da {@code previous()}.
         *
         * @return {@code cursor-1}, o {@code -1} se all'inizio
         */
        public int previousIndex() { return cursor - 1; }

        /**
         * Sostituisce l'ultimo elemento restituito da {@code next()} o
         * {@code previous()} con quello specificato (opzionale).
         *
         * @param o l'elemento con cui sostituire
         * @throws MyUnsupportedOperationException se non supportata
         * @throws ClassCastException se la classe dell'elemento ne impedisce l'aggiunta
         * @throws IllegalArgumentException se qualche aspetto dell'elemento ne impedisce l'aggiunta
         * @throws IllegalStateException se né {@code next} né {@code previous} sono stati chiamati,
         *         o {@code remove} o {@code add} sono stati chiamati dopo l'ultima {@code next/previous}
         */
        public void set(Object o) {
            if (lastRet == -1) throw new IllegalStateException();
            AbstractHList.this.set(lastRet, o);
        }

        /**
         * Inserisce l'elemento specificato immediatamente prima dell'elemento
         * che verrebbe restituito da {@code next()} (opzionale). L'elemento
         * è inserito prima del cursore; {@code previous()} successivo restituirà
         * il nuovo elemento.
         *
         * @param o l'elemento da aggiungere
         * @throws MyUnsupportedOperationException se non supportata
         * @throws ClassCastException se la classe dell'elemento ne impedisce l'aggiunta
         * @throws IllegalArgumentException se qualche aspetto dell'elemento ne impedisce l'aggiunta
         */
        public void add(Object o) {
            AbstractHList.this.add(cursor++, o);
            lastRet = -1;
        }
    }
}
