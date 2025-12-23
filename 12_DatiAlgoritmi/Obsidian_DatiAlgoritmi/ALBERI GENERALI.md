# INDICE SEZIONE
- [ ] [[#INTRODUZIONE]]
- [ ] [[#DEFINIZIONI]]
- [ ] [[#INTERFACCE ITERATOR E ITERABLE]]
- [ ] [[#INTERFACCIA TREE]]
- [ ] [[#ALGORITMI]]
- [ ] [[#VISITE DI ALBERI]]
# INTRODUZIONE
In modo *informale*, un albero è una **collezione di nodi** caratterizzata da una *struttura gerarchica* che si dipana da un nodo **radice** tramite relazioni *padre-figlio*.

```tikz
\usepackage{tikz}
\usetikzlibrary{trees}
\begin{document}
\begin{tikzpicture}[level distance=1.5cm,
  level 1/.style={sibling distance=3cm},
  level 2/.style={sibling distance=1.3cm}]
  \node {ROOT}
    child {node {child}
      child {node {child}}
      child {node {child}
        child {node {child}}
      }
    }
    child {node {child}
	  child {node {child}}
	  child {node {child}}
      child {node {child}
        child {node {child}}
      }
    };
\end{tikzpicture}
\end{document}
```

>[!important] OSSERVAZIONI
>- Le relazioni padre-figlio costituiscono un insieme di collegamenti minimali che inducono un legame fra tutti i nodi.
>- Una lista è un caso estremo di albero con una struttura gerarchica lineare.
## CAMPI APPLICATIVI:
- **Strutture dati**: mappe, priority queue.
- **Esplorazione risorse**: filesysteme, siti di e-commerce.
- **Reti di comunicazione**: sistemi distribuiti, sincronizzazione, broadcasting.
- **Analisi di algoritmi**: albero della ricorsione.
- **Compressione dati**: codici di Huffman.
- **Biologia computazionale**: alberi filogenetici.
# DEFINIZIONI
Diamo la definizione formale di albero (radicato):
>[!def] ALBERO RADICATO (ROOTED TREE)
>Un *albero radicato* $T$ è una collezione di nodi che, se non è vuota, soddisfa le seguenti proprietà:
>- Esiste un *nodo speciale* $r\in T$ detto **radice**.
>- $\forall v\in T$, $v\ne r:\exists!u\in T:u$ è **padre** di $v$ e ($v$ è figlio di $u$).
>- $\forall v\in T$, $v\ne r$ si ha che, **risalendo** di padre in padre si arriva a $r$ (ogni nodo è **discendente dalla radice**).
## ELEMENTI DEGLI ALBERI
Vediamo di seguito le definizioni di elementi degli alberi.
>[!def] ANTENATI
>Diciamo che $x$ è *antenato* di $y$ se $x=y$ oppure se $x$ è antenato del padre di $y$.

>[!def] DISCENDENTI
>Diciamo che $x$ è *discendente* di $y$ se $y$ è antenato di $x$.

>[!def] NODI INTERNI
>Sono i nodi con $\geq 1$ figli.

>[!def] NODI ESTERNI (FOGLIE)
>Sono i nodi senza figli.

>[!def] SOTTOALBERO CON RADICE $v$
>E' $T_{v}$: l'albero formato da tutti i discendenti di $v$ (quindi include $v$).

>[!def] ALBERO ORDINATO
>Diciamo che $T$ è un *albero ordinato* se per ogni nodo interno $v\in T$ è definito un *ordinamento lineare* tra i figli $u_{1},\dots,u_{k}$ di $v$.
## DEFINIZIONE RICORSIVA
Possiamo dare anche una definizione *ricorsiva* di albero:
>[!def] ALBERO RADICATO (DEF. RICORSIVA)
>Un albero radicato $T$ è una collezione di nodi che, *se non è vuota*, risulta partizionata nel seguente modo:
>$$ T=\{r\} \cup T_{1} \cup \dots \cup T_{k} $$
>Per un qualche $k\geq 0$, dove:
>- $r$ è radice con figli $u_{1},u_{2},\dots,u_{k}$.
>- $\forall i,1\leq i\leq k:T_{i}$ è un albero non vuoto con radice $u_{i}$.

## ALTRE DEFINIZIONI SUGLI ALBERI
>[!def] PROFONDITA' DI UN NODO 
>Possiamo dare due definizioni:
>1. $\mathrm{depth}_{T}(v)=\text{antenati}(v)-1$.
>2. Se $v=r$ allora $\mathrm{depth}_{T}(v)=0$, altrimenti $\mathrm{depth}_{T}(v)=1+\mathrm{depth}_{T}(\text{padre}(v))$.

>[!def] LIVELLO $i$
>Insieme di nodi a profondità $i$ ($\forall i\geq 0$).

>[!def] ALTEZZA DI UN NODO
>Se $v$ è foglia, allora $\mathrm{height}_{T}(v)=0$.
>Altrimenti $\mathrm{height}_{T}(v)=1+\mathrm{max}(\mathrm{height}_{T}(w))$ con $w$ figlio di $v$.

L'altezza "complessiva" dell'albero sarà quindi data da:
>[!def] ALTEZZA DI UN ALBERO
>$\mathrm{height}(T)=\mathrm{height}_{T}(r)$ con $r$ radice di $T$.

Vediamo allora il seguente fatto:
>[!theorem] PROPOSIZIONE
>Dato un albero $T$ non vuoto, $\mathrm{height}(T)=\mathrm{max}(\mathrm{depth}_{T}(v))$ con $v$ foglia.

E dimostriamolo:
>[!check] DIM.
>Definiamo $h:=\mathrm{height(T)}$ e $d:=\mathrm{max}(\mathrm{depth}_{T}(v))$ con $v\in T$ foglia.
>Dimostriamo che $h\geq d$.
>- Sia $v$ una foglia a profondità $d$ e sia $r$ la radice di $T$.
>- Allora esiste un percorso da $v$ a $r$ formato dai nodi $u_{d}=v$ ad altezza $0$, $u_{d-1}$ ad altezza $\geq1$, $\dots$, $u_{1}$ ad altezza $\geq d-1$ ed $u_{0}=r$ ad altezza $\geq d$.
>- Abbiamo quindi, in generale, $\mathrm{height}_{T}(u_{i})\geq d-i$ $\forall d\geq i\geq 0$.
>- Perciò si ha $h=\mathrm{height}_{T}(r)=\mathrm{height}_{T}(u_{0})\geq d$.
>
>Dimostriamo ora che $h\leq d$.
>- Consideriamo ancora una foglia $v$ a profondità $d$.  Assumiamo per assurdo che $h>d$.
>- Essendo $h$ altezza della radice, deve esistere un percorso da una foglia $v$ ad $r$ formato dai nodi $u_{0}=v$ ad altezza $0$, $u_{1}$ ad altezza $1$, $\dots$, $u_{h-1}$ ad altezza $h-1$ e $u_{h}=r$ ad altezza $h$.
>- Ma allora $v$ avrebbe $h$ antenati diversi da se stessa, per cui sarebbe $\mathrm{depth}_{T}(v)=h>d$, che contraddice il fatto che $d$ è la massima profondità di una foglia.
>- Deve quindi necessariamente essere $h\leq d$
>
>Ma era anche $h\geq d$, per cui concludiamo che $h=d$. $\square$

# INTERFACCE ITERATOR E ITERABLE
Prima di definire l'interfaccia di un albero definiamo le seguenti due, su cui essa sarà poi basata:
- **Iterator**: un *"cursore"* che permette di enumerare (*scansionare*) gli elementi di una collezione.
- **Iterable**: una collezione *"iterabile"*, cioè che rende disponibile un iteratore ai suoi elementi.

```java
public interface Iterator<E>{
	
	// Returns true if the scan of the collection is not over
	boolean hasNext();
	
	// Returns the next element in the collection
	E next();
}
```

```java
public interface Iterable<E>{

	// Returns an iterator of the collection
	Iterator<E> iterator();
}
```

L'interfaccia di un albero estenderà quindi *Iterable* in modo da rendere disponibile un *iteratore* con cui scansionare i nodi dell'albero.
# INTERFACCIA TREE
L'implementazione tipica di un albero è realizzata come segue:
- Un **puntatore** alla **radice** (unico *punto di accesso*).
- Ogni **nodo** è implementato come un *oggetto a sè stante* (per esempio una classe che implementi l'interfaccia Position) che offre dei metodi per *accedere* al *padre* e ai *figli*.

L'*interfaccia* di un albero (tree)  è quindi la seguente:
```java
public interface Tree<E> extends Iterable<E>{

	// Returns the number of positions in the tree
	int size();
	
	// Returns true if the tree contains no positions
	boolean isEmpty();
	
	// Returns the Position of the root (or null if empty)
	Position<E> root();
	
	// Returns the Position of p's parent (or null if p is the root)
	Position<E> parent(Position<E> p);
	
	// Returns an iterable containing p's children
	Iterable<Position<E>> children(Position<E> p);
	
	// Returns the number of children of p
	int numChildren(Position<E> p);
	
	// Returns true if p is internal
	boolean isInternal(Position<E> p);
	
	// Returns true if p is external
	boolean isExternal(Position<E> p);
	
	// Returns true if p is root
	boolean isRoot(Position<E> p);
	
	// Returns an iterator to all elements in the tree
	Iterator<E> iterator();
	
	// Returns an iterable containing all positions in the tree
	Iterable<Position<E>> positions();
	
}
```

# ALGORITMI
## PROFONDITA' DI UN NODO
Vediamo un algoritmo ricorsivo per calcolare la profondità di un nodo:
- **INPUT**: $v \in T$
- **OUTPUT**: profondità di $v$ in $T$.

```pseudo
\begin{algorithm}
\caption{depth(v)}

 \begin{algorithmic}
   \If{T.isRoot(v)} \Return 0
   \Else \Return 1 + \Call{depth}{T.parent(v)}
   \EndIf
 \end{algorithmic}
\end{algorithm}
```
Analizziamone la **complessità**:
- Se $d_{v}$ è la profondità di $v$ nell'albero $T$, saranno eseguite $d_{v}+1$ *invocazioni ricorsive* di $\mathrm{depth}$, *una per ogni antenato*. 
  Per cui l'albero della ricorsione ha $d_{v}+1$ livelli da un nodo ciascuno.
- Il costo di ogni nodo è $\Theta(1)$.

Concludiamo allora che la complessità di $\mathrm{depth}(v)$ è $\Theta(d_{v}+1)$.
>[!important] OSSERVAZIONE
> Il $+1$ è giustificato dal fatto che se $d_{v}=0$ ($v$ è radice) sono comunque richieste $\Theta(1)$ operazioni (non esiste $\Theta(0)$).
> Tuttavia possiamo semplificare la notazione e usare $\Theta(d_{v})$ per evidenziare il fatto che comunque la complessità è *proporzionale* alla *profondità* stessa.

Avendo usato come *taglia dell'istanza* proprio la **profondità dell'albero**, le possibili istanze di taglia $d$ sono tutti i *nodi a profondità d* di *qualsiasi albero* $T$ (che abbia nodi a profondità almeno $d$).

A questo punto ci si potrebbe domandare se sia possibile esprimere la complessità dell'algoritmo usando come *taglia dell'istanza* il **numero di nodi** $n$.
In questo caso le possibili istanze di taglia $n$ sono tutti i *nodi* appartenenti ad *alberi* $T$ con proprio $n$ nodi ($|T|=n$).
Allora abbiamo:
- **Upper bound**: $O(n)$ perchè in un albero con $n$ nodi, ogni nodo ha profondità $\leq n$.
- **Lower bound**: $\Omega(n)$ perchè in un albero che è una *catena* di $n$ nodi, l'ultimo nodo della catena è una foglia $v$ di profondità $n-1$.

Concludiamo quindi che in tal caso la complessità al caso pessimo sarebbe $\Theta(n)$.
## ALTEZZA DI UN NODO
Vediamo un altro algoritmo ricorsivo, stavolta per il calcolo dell'altezza di un nodo.
- **INPUT**: $v\in T$.
- **OUTPUT**: altezza di $v$ in $T$.

```pseudo
\begin{algorithm}
\caption{height(v)}

 \begin{algorithmic}
   \State {$h\gets0$}
   \ForAll{$w\in$T.children(v)}
	   \State $h\gets$ max\{h,1+\Call{height}{w}\} 
   \EndFor
   \Return $h$
 \end{algorithmic}
\end{algorithm}
```
**NOTA:** il caso base è dato da $v$ foglia, che non ha figli.

Nel for che scansiona i figli di $v$ assumiamo che ciascuno venga generato in tempo $\Theta(1)$.
Analizziamo ora la complessità. L'**albero della ricorsione** associato a $\mathrm{height}(v)$:
- Ha *un nodo per ogni* $u\in T_{v}$ (sottoalbero di radice $v$).
- Il costo associato al nodo $u\in T_{v}$ è $\Theta(c_{u}+1)$ dove $c_{u}$ è il *numero di figli* di $u$.

Allora la complessità di $\mathrm{height}(v)$ è data da:
$$
\Theta\left( \sum\limits_{u\in T_{v}}(c_{u}+1) \right)
$$
Riscriviamo la sommatoria come segue:
$$
\sum_{u\in T_{v}}c_{u} + \sum_{u\in T_{v}}1 = \sum_{u\in T_{v}}c_{u} +n_{v}
$$
Dove $n_{v}$ è il numero di nodi in $T_{v}$.
In generale vale il seguente fatto:
$$
\sum_{u\in T_{v}}c_{u} = n_{v}-1
$$
E concludiamo allora
$$
\sum_{u\in T_{v}}(c_{u}+1) = 2n_{v} -1
$$
Per cui possiamo dire che la complessità di $\mathrm{height(v)}$ è $\Theta(n_{v})$.
# VISITE DI ALBERI
Diamo la definizione di visita di un albero:
>[!def] VISITA DI UN ALBERO
>**Scansione sistematica** di *tutti i nodi* dell'albero $T$ che permette di eseguire una qualche operazione (*visita*) ad ogni nodo.

Le visite rappresentano un *design pattern* algoritmico che può essere utilizzato per il calcolo di valori e/o per impostare opportune variabili associate ai nodi.

Per alberi generali studiamo le visite:
- **Preorder**: visita *prima il padre* e *poi* (ricorsivamente) i sottoalberi radicati nei *figli*.
  In tal modo, le operazioni svolte per un nodo possono *dipendere da quelle svolte per i suoi antenati*.
- **Postorder**: visita *prima* (ricorsivamente) i sottoalberi radicati nei *figli*, *poi il padre*.
  In questo modo le operazioni svolte per un nodo possono *dipendere da quelle svolte per i sui discendenti*.
## VISITA PREODER
- **INPUT**: nodo $v\in T$.
- **OUTPUT**: risultante della visita di $T_{v}$.

```pseudo
\begin{algorithm}
\caption{preorder(v)}

 \begin{algorithmic}
   \State visita $v$
   \ForAll{$w\in$T.children(v)}
	   \State\Call{preorder}{w}
   \EndFor
 \end{algorithmic}
\end{algorithm}
```
**NOTA:** il caso base è dato da $v$ foglia, che non ha figli.

Consideriamo per esempio il seguente albero:

```tikz
\usepackage{tikz}
\usetikzlibrary{trees}
\begin{document}
\begin{tikzpicture}[level distance=1.5cm,
  level 1/.style={sibling distance=1.8cm},
  level 2/.style={sibling distance=1.3cm}]
  \node {A}
    child {node {B}
      child {node {E} }
      child {node {F} }
    }
    child {node {C}
    }
    child {node {D}
	  child {node {G}}
    };
\end{tikzpicture}
\end{document}
```

Assumendo che $\mathrm{children}()$ esamini i figli da sinistra a destra, l'ordine della visita in preorder è **A B E F C D G**.

>[!def] DEFINZIONE
>Sia $T$ albero ordinato e siano $u,v\in T$ due nodi allo stesso livello.
>Diciamo che $u$ è **a sinistra** di $v$ (e quindi $v$ è *a destra* di $u$) se $u$ viene prima di $v$ nella visita in preorder.
### ESEMPIO APPLICATIVO
Consideriamo l'indice di un libro rappresentato dal seguente albero:

```tikz
\usepackage{tikz}
\usetikzlibrary{trees}
\begin{document}
\begin{tikzpicture}[level distance=1.5cm,
  level 1/.style={sibling distance=5cm},
  level 2/.style={sibling distance=2.8cm},
  level 3/.style={sibling distance=2.1cm}]
  \node {Book}
    child {node {Chap.1}
      child {node {Sect. 1.1} }
      child {node {Sect. 1.2} 
	      child {node {Sect. 1.2.1} }
	      child {node {Sect. 1.2.2} }
      }
    }
    child {node {Chap.2}
	  child {node {Sect. 2.1} }
      child {node {Sect. 2.2} 
	      child {node {Sect. 2.2.1} }
	      child {node {Sect. 2.2.2} }
      }
    };
\end{tikzpicture}
\end{document}
```

>[!example] ESEMPI
>Per stampare l'indice di tale libro dobbiamo usare la visita in **preorder**.
>Un esempio analogo sarebbe dato dalla stampa della struttura di un *file system* come sequenza di cartelle e file.
### ALGORITMO BASATO SULLA VISITA PREORDER
Vogliamo progettare un algoritmo $\mathrm{allDepths}$ che dato un albero $T$ calcoli la *profondità* di ogni nodo $v\in T$ e la memorizzi in un campo $\mathrm{v.depth}$.

Per farlo possiamo adattare la visita in preorder, definendo la visita di un nodo $v$ come segue:
- Se $v$ è *radice*, si imposta la profondità a $0$.
- Se $v$ *non è radice*, si imposta la profondità a $1+$ la profondità del padre, che è già stata impostata dato che il padre è già stato visitato.

Vediamo l'algoritmo:
- **INPUT**: $v\in T$ e $u\mathrm{.depth}$ impostato correttamente per $u$ padre di $v$.
- **OUTPUT:** $z.\mathrm{depth}$ impostato correttamente $\forall z\in T_{v}$.
```pseudo
\begin{algorithm}
\caption{allDepths(T,v)}

 \begin{algorithmic}
   \Comment{Visita v} 
   \If{T.isRoot(v)}
   \State v.depth$\gets$0
   \Else
   \State v.depth$\gets$1+T.parent(v).depth
   \EndIf
   \ForAll{w$\in$T.children(v)} \Comment{Visita ricorsivamente i figli}
   \State\Call{allDepths}{T,w}
   \EndFor
 \end{algorithmic}
\end{algorithm}
```
>[!important] OSSERVAZIONI
>1. Per impostare il campo $\mathrm{depth}$ per *tutti i nodi* di $T$ invochiamo $\mathrm{allDepths(T,T.root())}$.
>2. L'algoritmo *non restituisce* alcun valore, ma modifica i nodi dell'albero che si considerano quindi *oggetti globali* che sopravvivono all'esecuzione dell'algoritmo.
## VISITA POSTORDER
- **INPUT**: nodo $v\in T$.
- **OUTPUT**: risultante della visita di $T_{v}$.

```pseudo
\begin{algorithm}
\caption{postorder(v)}

 \begin{algorithmic}
   \ForAll{$w\in$T.children(v)}
	   \State\Call{postorder}{w}
   \EndFor
   \State visita $v$
 \end{algorithmic}
\end{algorithm}
```
**NOTA:** il caso base è dato da $v$ foglia, che non ha figli.

Consideriamo per esempio il seguente albero:

```tikz
\usepackage{tikz}
\usetikzlibrary{trees}
\begin{document}
\begin{tikzpicture}[level distance=1.5cm,
  level 1/.style={sibling distance=1.8cm},
  level 2/.style={sibling distance=1.3cm}]
  \node {A}
    child {node {B}
      child {node {E} }
      child {node {F} }
    }
    child {node {C}
    }
    child {node {D}
	  child {node {G}}
    };
\end{tikzpicture}
\end{document}
```

Assumendo che $\mathrm{children}()$ esamini i figli da sinistra a destra, l'ordine della visita in preorder è **E F B C G D A**.
### ALGORITMI BASATI SULLA VISITA POSTORDER
>[!example] ESEMPIO 1
>L'algoritmo $\mathrm{height}$ è un esempio di visita *postorder* dato che l'altezza di un nodo viene calcolata completamente solo dopo aver calcolato quelle dei figli.

>[!example] ESEMPIO 2
>Si consideri un *file system* gerarchico la cui struttura è rappresentata da un albero $T$ dove i nodi *interni* corrispondono alle *cartelle* e i nodi *foglia* ai *file*.
>Ogni nodo $v$ ha un campo $\mathrm{v.loc-size}$ che memorizza lo spazio occupato dal nodo, escludendo quello dei discendenti.
>Vogliamo progettare un algoritmo $\mathrm{diskSpace}$ che dato un tale $T$ calcoli, per ogni nodo $v\in T$, lo spazio aggregato occupato dai suoi discendenti e lo memorizzi in un campo $\mathrm{v.aggr-size}$.

Vediamo come implementare l'algoritmo dell'esempio 2.
- **INPUT**: Albero $T$ e nodo $v\in T$, $\mathrm{u.loc-size}$ impostato $\forall u\in T$.
- **OUTPUT**: $\mathrm{v.aggr-size}$ e $\mathrm{z.aggr-size}$ impostato correttamente $\forall z\in T_{v}$.

```pseudo
\begin{algorithm}
\caption{diskSpace(T,v)}

 \begin{algorithmic}
   \State v.aggr-size $\gets$ v.loc-size
   \ForAll{w$\in$T.children(v)}
   \State v.aggr-size $\gets$ v.aggr-size + \Call{diskSpace}{T,w}
   \EndFor
   \Return v.aggr-size
 \end{algorithmic}
\end{algorithm}
```
## COMPLESSITA' VISITE
Sia $T$ un albero costituito da $n$ nodi. 
Consideriamo l'*albero della ricorsione* di **preorder(T.root())**:
- Ha $n$ nodi associati alle *invocazioni ricorsive* che sono esattamente *una per ogni nodo di T*.
- Il costo del nodo associato a **preorder(v)** con $v\in T$ generico è $\Theta(t_{v}+c_{v}+1)$.
  Dove $t_{v}$ è il *costo della visita di $v$* e $c_{v}$ è il numero di figli di $v$ (stiamo assumendo che i figli siano restituiti in tempo $\Theta(1)$).

Possiamo allora concludere che la complessità di **preorder** è:
$$
\Theta\left( \sum_{v\in T}(t_{v}+c_{v}+1) \right) = \Theta\left( n+\sum_{v\in T}t_{v} \right)
$$
>[!important] NOTA
>1. Si ha la *stessa complessità* anche per **postorder** e **inorder** (per gli alberi binari, vedremo).
>2. Dall'analisi si conclude che le visite consentono di *enumerare tutti i nodi* di $T$ in tempo *lineare* (l'operazione sul nodo non è considerata, ci stiamo riferendo alla sola enumerazione dei nodi).
>3. Se $t_{v}\in\Theta(1)$ allora abbiamo che *anche la complessità totale* è $\Theta(n)$.

