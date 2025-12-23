# INDICE SEZIONE
- [ ] [[#PROBLEMA COMPUTAZIONALE]]
      - [[#ESEMPI DI PROBLEMI COMPUTAZIONALI]]
- [ ] [[#ALGORITMO E MODELLO DI CALCOLO]]
- [ ] [[#PSEUDOCODICE]]
      - [[#ES TRASPOSTA DI UNA MATRICE N x N DI INTERI]]
- [ ] [[#STRUTTURE DATI]]
- [ ] [[#ANALISI DEGLI ALGORITMI]]
      - [[#TAGLIA DI UNA ISTANZA]]
      - [[#COMPLESSITA' IN TEMPO]]
      - [[#ANALISI COMPLESSITA' IN PRATICA]]
      - [[#TERMINOLOGIA]]
      - [[#ESEMPIO CRITTOGRAFIA (SCELTA DELLA TAGLIA)]]
      - [[#CAVEAT SULL'ANALISI ASINTOTICA AL CASO PESSIMO]]
- [ ] [[#CORRETTEZZA]] 
      - [[#ANALISI DI CICLI]]
- [ ] [[#RICORSIONE]]
      - [[#ESECUZIONE DI UN ALGORITMO RICORSIVO]]
      - [[#COMPLESSITA' DI ALGORITMI RICORSIVI]]
      - [[#CORRETTEZZA DEGLI ALGORITMI RICORSIVI]]
      - [[#CALCOLO EFFICIENTE DI POTENZE]]
# PROBLEMA COMPUTAZIONALE
Un *problema computazionale* è costituito da:
- Un insieme $\mathcal{I}$  di **ISTANZE** (cioè i possibili *input*).
- Un insieme $\mathcal{S}$ di **SOLUZIONI** (cioè i possibili *output*).
- Una relazione $\Pi$ che ad ogni istanza $i\in\mathcal{I}$ associa *una o più* soluzioni $s\in\mathcal{S}$.

>[!important] NOTA
>$\Pi$ è un sottoinsieme del prodotto cartesiano $\mathcal{I}\times\mathcal{S}$.
## ESEMPI DI PROBLEMI COMPUTAZIONALI 
### ORDINAMENTO DI ARRAY DI INTERI
- $\mathcal{I} = \{A : A = \text{array di interi}\}$;
- $\mathcal{S} = \{B : B = \text{array ordinati di interi}\}$;
- $\Pi = \{(A,B) : A\in\mathcal{I},B\in\mathcal{S} \text{ , B contiene gli stessi interi di A}\}$.

Ad esempio:
- $(<43,16,75,2>,<2,16,43,75>)\in\Pi$.
- $(<7,1,7,3,3,5>,<1,2,3,5,7,7>)\notin\Pi$. 
### VERIFICA SU DUE INSIEMI
**PROBLEMA:** verifica se due insiemi finiti di oggetti da un universo $U$ sono disgiunti oppure no.
Quindi abbiamo:
- $\mathcal{I}= \{ (A,B): A,B \subset U \text{ , A,B disgiunti} \}$.
- $\mathcal{S} = \{ \text{true,false} \}$.
- $\Pi = \{ \left( (A,B), S \right) : \text{ S = true se } A\cap B=\emptyset \text{ , S = false se } A\cap B \ne \emptyset \}$.
# ALGORITMO E MODELLO DI CALCOLO

>[!def] ALGORITMO
>Procedura computazionale ben definita che trasforma un dato *input* in un *output* eseguendo una *sequenza finita* di *operazioni elementari*.

L'algoritmo fa riferimento ad un *modello di calcolo*, ovvero un'astrazione di computer che definisce l'insieme delle operazioni elementari.
Nel modello di calcolo **RAM** (*Random Access Machine*), *input, output, dati intermedi e programma* si trovano in **memoria** e le **operazioni elementari** sono: *assegnamento, operazioni logiche, operazioni aritmetiche, indicizzazione di array, return di un valore da una funzione, etc...* .

Un algoritmo $A$ risolve un problema computazionale $\Pi \subseteq \mathcal{I}\times\mathcal{S}$ se:
1. Calcola una funzione da $\mathcal{I}$ a $\mathcal{S}$, ovvero riceve in *input* istanze $i\in\mathcal{I}$ e produce come *output* soluzioni $s \in \mathcal{S}$.
2. $i$ ed $s$ sono tali per cui $(i,s)\in \Pi$.

>[!Warning] ATTENZIONE
>Se $\Pi$ associa *più soluzioni* ad una istanza $i$, per tale istanza $A$ calcola **una sola** soluzione. Quale viene calcolata dipende da come viene progettato l'algoritmo.

# PSEUDOCODICE
Per *semplicità* e facilità di analisi, descriviamo un algoritmo utilizzando uno **pseudocodice** strutturato come segue:
- **Algoritmo**: nome (parametri).
- **Input**: breve descrizione dell'istanza di input.
- **Output**: breve descrizione della soluzione restituita in output.

Descrizione **chiara** dell'algoritmo tramite costrutti di linguaggi di programmazione e, se utile ai fini della chiarezza, anche tramite *linguaggio naturale*, dalla quale sia facilmente determinabile la *sequenza di operazioni elementari* eseguita per ogni dato input.

## ES: TRASPOSTA DI UNA MATRICE N x N DI INTERI
L'insieme delle *istanze* è:
$$
\mathcal{I} = \{ A: \text{ matrice } n\times n \text{ di interi} \}
$$
L'insieme delle *soluzioni* è:
$$
\mathcal{S} = \{ B: \text{ matrice } n\times n \text{ di interi} \}
$$
La *relazione* $\Pi$, invece, è:
$$
\Pi = \{ (A,B) : A\in\mathcal{I}, B\in\mathcal{S}, B=A^t \}
$$
**IDEA**: scambiare ciascuna entry $a_{ij}$ del *trinagolo superiore* ($i=0,\dots,n-2$ e $j>i$) con l'entry $a_{ji}$.
- **Algoritmo:** _TRANSPOSE_.
- **Input**: matrice $A$ $n\times n$ di interi $a_{ij}$ con $0\leq i,j\leq n$
- **Output**: matrice $A^t$.

Operazioni:
```pseudo
\begin{algorithm}
\caption{TRANSPOSE}

 \begin{algorithmic}
   \For{$j \gets 0$, \To $n-2$}
	  
	  \For{$j \gets i+1$ \To $n-1$}
	    \State exchange $a_{ij}$ with $a_{ji}$
	  \EndFor
  
  \EndFor

 \end{algorithmic}
\end{algorithm}
```
# STRUTTURE DATI
Sono usate dagli algoritmi per organizzare e accedere in modo sistematico ai dati di input e ai dati generati durante l'esecuzione.

>[!def] STRUTTURA DATI
>Una struttura dati è una **collezione di oggetti** corredata di *metodi di acesso* e/o *modifica*.
>

Presenta due *livelli di astrazione*:
- Livello **logico**: specifica l'organizzazione logica degli oggetti della collezione e la relazione input-output di ciascun metodo (a questo livello si parla di *Abstract Data Type* o *ADT*).
- Livello **fisico**: specifica il layout fisico dei dati e la realizzazione dei metodi tramite algoritmi.

Esempio in Java:
- Livello *logico*: **interfacce**.
- Livello *fisico*: **classi**.
# ANALISI DEGLI ALGORITMI
L'analisi di un algoritmo $A$ mira a studiarne l'**efficienza** e l'**efficacia**.
In particolare, essa valuta:
- **COMPLESSITA'**:
  - *Temporale*.
  - *Spaziale*.
- **CORRETTEZZA**:
  - *Terminazione*.
  - *Soluzione al problema*.

Daremo particolare attenzione alla *complessità temporale* e alla *correttezza* della soluzione al problema computazionale.
## TAGLIA DI UNA ISTANZA
L'analisi di un algoritmo viene solitamente fatta partizionando le istanze in gruppi in base alla loro **taglia** (*size*), in modo che le istanze di un gruppo siano tra loro *confrontabili*.

La taglia di una istanza è espressa da *uno o più valori* che ne caratterizzano la grandezza.
**ESEMPI**:
- *Array*: la sua lunghezza $n$.
- *Matrice quadrata*: numero di righe (o colonne).
- *Matrice (non quadrata)*: numero di righe *e* numero di colonne.
## COMPLESSITA' IN TEMPO
L'obiettivo è quello di stimare il *tempo di esecuzione* (*running time*) di un algoritmo al fine di valutarne l'efficienza e poterlo confrontare con altri algoritmi per lo stesso problema.
Tale complessità deve essere *derivabile dallo pseudocodice*.

Un esempio di approccio che soddisfa i requisiti è l'analisi al **caso pessimo** (*worst-case*) in funzione dell'*istanza*:
- Conteggio operazioni elementari nel modello RAM.
- Analisi asintotica (per semplificare il conteggio).

>[!Tip] NOTA
>Esistono anche le analisi al *caso medio* e l'*analisi probabilistica*.
>

Sia $A$ un algoritmo che risolve il problema $\Pi$.

>[!def] COMPLESSITA' (IN TEMPO)
>La **complessità** (in tempo) al **caso pessimo** di $A$ è una funzione $t_{A}(n)$ definita come il *massimo numero di operazioni* elementari che $A$ *esegue per risolvere* una istanza di taglia $n$.

Tuttavia determinare $t_{A}(n)$ per ogni $n$ è arduo, se non impossibile, perchè:
- E' difficile identificare l'*istanza peggiore* di taglia $n$.
- E' difficile contare il *massimo numero di operazioni* richieste per risolvere tale istanza, il che richiederebbe anche una specifica dettagliata del set di operazioni elementari del modello di calcolo.

Fortunatamente, *non è necessario* determinare *esattamente* $t_{A}(n)$, per le seguenti ragioni:
- Il *tempo di esecuzione*, che la complessità vuole stimare, dipende da tanti fattori ed è pertanto *impossibile* da quantificare in modo preciso.
- Le diverse operazioni elementari del modello RAM possono avere *impatto diverso* sui tempi di esecuzione a seconda dell'*architettura dell'elaboratore*.

Per questi motivi, ci accontentiamo di **limiti superiori ed inferiori** a $t_{A}(n)$, ottenuti ricorrendo all'**analisi asintotica**, cioè ignorando:
- *Fattori moltiplicativi* costanti.
- *Termini additivi* non dominanti.
### RICHIAMO DI ANALISI: ORDINI DI GRANDEZZA
Per l'analisi asintotica ci vengono in aiuto gli *ordini di grandezza*.
Date due funzioni $f(n),g(n):\mathbb{R}\to\mathbb{R}^+ \cup \{0\}$ ricordiamo la definizione delle notazioni seguenti.
#### O-GRANDE

>[!def] O-grande
>$f(n)\in O(g(n))$ se $\exists c>0$ ed $\exists n_{0} \geq 1$, costanti rispetto ad $n$, tali che valga:
>$$ f(n) \leq cg(n), \forall n \ge n_{0} $$
##### ESEMPI
- $f(n)=3n+4$ è $O(n)$ perchè $f(n)\le 4n, \forall n\ge 4$.
- $f(n)=n+2n^2$ è $O(n^2)$ perchè $f(n)\le 3n^2, \forall n\ge 1$.
- $f(n)=c_{1}n+c_{2}$ con $c_{1},c_{2}>0$ perchè $f(n)\le (c_{1}+c_{2})n,\forall n\ge 1$.
#### $\Omega$-GRANDE
>[!def] $\Omega$-GRANDE
>$f(n)\in \Omega(g(n))$ se $\exists c>0$ e $\exists n_{0}\ge 1$, costanti rispetto ad $n$, tali che valga:
>$$ f(n) \geq cg(n), \forall n\geq n_{0} $$
##### ESEMPI
- $f(n)=3n+4$ è $\Omega(n)$ perchè $f(n)>n,\forall n\ge 1$.
- $f(n)=n+2n^2$ è $\Omega(n^2)$ perchè $f(n)>n^2,\forall n\geq 1$.
- $f(n)=c_{1}n+c_{2}$ è $\Omega(n)$ perchè $f(n)>c_{1}n,\forall n\geq 1$.
#### $\Theta$-GRANDE
>[!def] $\Theta$-Grande
>$f(n)\in \Theta(g(n))$ se:
>$$ f(n)\in O(g(n)) $$
>e anche
>$$ f(n)\in \Omega(g(n)) $$
##### ESEMPI
- $f(n)=3n+4$ è $\Theta(n)$.
- $f(n)=n+2n^2$ è $\Theta(n^2)$.
- $f(n)=c_{1}n+n_{2}$ è $\Theta(n)$.

#### o-PICCOLO
>[!def] o-PICCOLO
>$f(n)\in o(g(n))$ se
>$$ \lim_{ n \to \infty } \frac{f(n)}{g(n)} = 0 $$

##### ESEMPI
- $f(n)=100n$ è $o(n^2)$.
- $f(n)=\frac{3n}{\log n}$ è $o(n)$.
#### PROPRIETA' DEGLI ORDINI
Elenchiamo di seguito alcune proprietà utili degli ordini di grandezza:
- $\mathrm{max}\{ f(n),g(n) \}\in\Theta(f(n)+g(n))\text{ }\forall f,g:\mathbb{N}\to \mathbb{R}^+ \cup\{0\}$.
- $\sum_{i=0}^k a_in^i\in\Theta(n^k)$ se $a_{k}>0$ e $k\geq 0$.
- $\log_{b}n\in\Theta(\log n)$ se $b>1$, cioè la base non è importante.
- $n^k\in o(a^n)$ se $k>0,a>1$.
- $(\log n)^k\in o(n^h)$ se $h,k>0$.
## ANALISI COMPLESSITA' IN PRATICA
Dato un algoritmo $A$ e detta $t_{A}(n)$ la sua complessità al *caso pessimo* si cercano limiti asintotici *superiori* e/o *inferiori* per $t_{A}(n)$, come anticipato in [[#COMPLESSITA' IN TEMPO]].

>[!Def] LIMITE SUPERIORE
>$$t_{A}(n)\in O(f(n))$$
>Si prova argomentando che per ogni $n$ *"abbastanza grande"* e per ciascuna istanza di taglia $n$ l'algoritmo esegue un numero $\leq cf(n)$ di operazioni, con $c$ costante (che non è necessario determinare).

>[!Def] LIMITE INFERIORE
>$$t_{A}(n)\in \Omega(f(n))$$
>Si prova argomentando che per ogni $n$ *"abbastanza grande"* e per ciascuna istanza di taglia $n$ l'algoritmo esegue un numero $\geq cf(n)$ di operazioni, con $c$ costante (che non è necessario determinare).
>In alcuni casi è comodo argomentare che per *ciascuna istanza* di *taglia* $n$ l'algoritmo esegue almeno tale numero di operazioni.

In ogni caso, sia nel caso di *O-Grande* che di *$\Omega$-Grande*:
- $f(n)$ deve essere più vicino possibile alla complessità vera (**tight bound**).
- $f(n)$ deve essere più semplice possibile: utilizzare solo **termini essenziali**.
## TERMINOLOGIA
Nell'analisi della complessità si fa spesso riferimento alle seguenti categorie:
- **Logaritmica**: $\Theta(\log n)$ con base $2$ (ma non è importante).
- **Lineare**: $\Theta(n)$.
- **Quadratica**: $\Theta(n^2)$.
- **Cubica**: $\Theta(n^3)$.
- **Polinomiale**: $\Theta(n^c)$, con $c>0$.
- **Esponenziale**: $\Omega(a^n)$ con $a>1$.
- **Polilogaritmica**: $\Theta\big( (\log n)^c \big)$ con $c>0$.
## ESEMPIO CRITTOGRAFIA (SCELTA DELLA TAGLIA)
Nell'ambito della *crittografia a chiave pubblica* l'algoritmo più conosciuto è sicuramente **RSA** (*Rivest Shamir Adleman*).
La sua sicurezza si basa sulla difficoltà nel risolvere il seguente problema:
>[!tip] INTEGER FACTORIZATION
>Dato un intero $N$ prodotto di due primi $p$ e $q$, determinare $p$ e $q$.

Un algoritmo *banale* per risolvere il problema può essere il seguente:

```pseudo
\begin{algorithm}
\caption{NaiveIntegerFactorization(N)}

 \begin{algorithmic}
   \For{$p \gets 2$, \To $\lfloor \sqrt{N} \rfloor$}
	  
	  \If{($N\mod p =0$)}
		  \Return $\{p,N/p\}$ 
      \EndIf
  
  \EndFor

 \end{algorithmic}
\end{algorithm}
```

Osservando l'algoritmo saremmo tentati di dire che la sua complessità sia $\Theta(\sqrt{  N})$, tuttavia in questo caso è importante riflettere sulla *scelta della taglia dell'istanza*: dato un numero interno $N$ servono $n=\log_{2}N$ bit per rappresentarlo, quindi è più corretto scegliere $n$ come taglia dell'istanza.
Allora troviamo che la complessità è *in realtà*:
$$
\Theta(N) = \Theta(2^{n/2})
$$
La complessità "vera" è **esponenziale**!
## CAVEAT SULL'ANALISI ASINTOTICA AL CASO PESSIMO
A questo punto è bene chiarire alcuni punti riguardo l'analisi asintotica esposta fin'ora.
### **ASINTOTICAMENTE** MIGLIORE
Siano $A$ e $B$ due algoritmi che risolvono $\Pi$ e siano $t_{A}(n)$ e $t_{B}(n)$ le loro rispettive complessità.
Quando diciamo
$$
t_{A}(n) \in o\big(t_{B}(n)\big)
$$
Intendiamo che $A$ è **asintoticamente** *migliore* di $B$, cioè $A$ è più efficiente di $B$ $\forall n\geq n_{0}$, ma tale $n_{0}$ potrebbe potenzialmente essere *molto grande*.
### COSTANTI TRASCURATE
Abbiamo visto che nell'analisi asintotica le *costanti* sono spesso trascurate, tuttavia potrebbero essere *elevate* e avere, nella pratica, un certo *impatto* sulle prestazioni.

Conviene allora dare anche una *stima delle costanti*.
### ISTANZE PATOLOGICHE
Talvolta il caso pessimo potrebbe essere costituito dalle cosiddette **istanze patologiche**, mentre per tutte le **istanze di interesse** la complessità potrebbe essere asintoticamente *migliore*.

In questi casi conviene quindi:
- *Restringere il dominio* delle istanze, limitandosi a quelle di nostro interesse.
- Aggirare il caso pessimo con un'analisi *probabilistica*.
- Fare un'analisi al *caso medio*.
# CORRETTEZZA
Per dimostrare la *correttezza* di un algoritmo è necessario innanzitutto provare la sua **terminazione**: bisogna assicurarsi che i cicli e l'eventuale ricorsione abbiano termine.

Più in generale, l'approccio è il seguente:
- Definire lo *stato* **iniziale** dell'algoritmo e quello **finale** che esso deve raggiungere.
- Decomporre l'algoritmo in **segmenti** e definire per ogni segmento lo *stato* in cui l'algoritmo si deve trovare al *termine del segmento* (**checkpoint**).
- Dimostrare che a partire dallo stato iniziale si *raggiungono in successione* gli stati specificati per la *fine di ogni segmento*.
  In particolare, lo stato che deve valere alla fine dell'*ultimo segmento* deve *coincidere* (o *implicare*) lo stato **finale desiderato**.
## ANALISI DI CICLI
I cicli sono i segmenti più *difficili* da analizzare perchè definiscono *implicitamente molte operazioni*, ma, insieme alla ricorsione, costituiscono gli strumenti *essenziali* per la scrittura di algoritmi/programmi interessanti.

Provare la **correttezza di un ciclo** consiste nel dimostrare che al *termine* della sua esecuzione *vale una certa proprietà* $\mathcal{L}$ che rappresenta lo stato finale del ciclo (segmento) ed è funzionale alla correttezza dell'algoritmo.

A tal fine si fa uso di un **INVARIANTE**.

>[!def] INVARIANTE
>Un *invariante* per un ciclo è una **proprietà** espressa in funzione delle variabili usate nel ciclo che *descrive lo stato* in cui si trova l'esecuzione alla *fine* di una generica *iterazione* del ciclo stesso.

Quindi il processo da seguire è il seguente:
>[!tldr] CORRETTEZZA DI UN CICLO
>1. Si individua un *opportuno* **invariante**.
>2. Si dimostra che *vale all'inizio* del ciclo.
>3. Si dimostra per induzione che *vale alla fine di ogni iterazione*.
>4. Si dimostra che *alla fine del ciclo* l'invariante **implica la proprietà $\mathcal{L}$**.
### RICHIAMO: INDUZIONE
Per dimostrare che una proprietà $Q(n)$ è *vera* $\forall n\geq n_{0}$ si procede per **induzione**:
- Si sceglie un interno $k\geq 0$.
- **BASE:** si dimostra $Q(n_{0}),Q(n_{0}+1),\dots,Q(n_{0}+k)$.
- **PASSO INDUTTIVO:** si fissa un valore arbitrario $n\geq n_{0}+k$ e si dimostra che
  $$ Q(m) \text{ vera }\forall m: n_{0}\leq m\leq n \implies Q(n+1) \text{ vera} $$

### ESEMPIO
Prendiamo come esempio l'algoritmo *arrayMax*:
- **INPUT**: Array $A[0,\dots,n-1]$ di $n\geq 1$ interi.
- **OUTPUT**: Massimo intero in $A$.

```pseudo
\begin{algorithm}
\caption{arrayMax(A)}

 \begin{algorithmic}
   \State $\text{currMax }\gets A[0]$ 
   \For{$i \gets 1$, \To $n-1$}
	  \If{($A[i] > \text{currMax}$)}
		  \State $\text{currMax }\gets A[i]$ 
      \EndIf
  \EndFor
  \Return currMax
 \end{algorithmic}
\end{algorithm}
```

Tale algoritmo è sostanzialmente costituito da un unico segmento: il ciclo for.

- La *proprietà $\mathcal{L}$* che implica la correttezza dell'algoritmo è la seguente:
$$
\mathcal{L}: \text{ Alla fine del ciclo, currMax e' il massimo intero in A}
$$
- Scegliamo a questo punto l'**invariante** $\mathbf{I}$:
$$
\mathbf{I} = \text{ Alla fine dell'iterazione i vale currMax} = \max\{A[0],\dots,A[i]\} 
$$
- All'*inizio* del ciclo ($i=0$) l'invariante è **vero**: $\text{currMax}$ è il massimo del sotto-array contenente solo $A[0]$.

- *Supponiamo* allora che l'invariante sia *vero a fine iterazione* $i<n-1$, cioè:
$$
\text{currMax} = \max\{ A[0],\dots,A[i] \}
$$
- e dimostriamo che vale anche alla fine dell'iterazione successiva (*Passo induttivo*). All'iterazione $i+1$ abbiamo:
$$
\text{currMax} = \max\{ \text{currMax} ,A[i] \} = \max\{ A[0],\dots,A[i+1] \}
$$
- Quindi l'invariante *continua a valere* alla fine dell'iterazione $i+1$.
- Alla fine dell'ultima iterazione $i=n-1$, siccome vale l'invariante, allora $\text{currMax}$ è *effettivamente il massimo* intero in $A$, che è esattamente la *proprietà* $\mathcal{L}$.
# RICORSIONE
>[!def] ALGORITMO RICORSIVO
>E' un algoritmo che *invoca se stesso* su istanze *sempre più piccole* sfruttando la nozione di induzione.

La soluzione di una istanza di taglia $n$ è ottenuta:
- Direttamente: se $n=n_{0},n_{0}+1,\dots,\mathbf{n_{0}+k}$ (casi base).
- Riconducendosi alla soluzione di $r\geq 1$ istanze di taglia $<n$: se $n>n_{0}+k$.
  Se $r=1$ si parla di *linear recursion*.
## ESECUZIONE DI UN ALGORITMO RICORSIVO
All'esecuzione di un algoritmo ricorsivo su una data istanza è associato un **albero della ricorsione** tale che:
- Ogni **nodo**corrisponde a un'*invocazione ricorsiva distinta* fatta durante l'esecuzione dell'algoritmo.
- La **radice** dell'albero corrisponde alla prima invocazione e i **figli** di un nodo $x$ sono associati alle invocazioni ricorsive fatte direttamente dall'invocazione corrispondente a $x$.
- Le **foglie** (nodi senza figli) rappresentano i *casi base*.

```tikz
\begin{document}
\begin{tikzpicture}
[level distance=10mm,every node/.style={ circle,inner sep=1pt },
level  1/.style={ sibling distance=20mm},
level  2/.style={ sibling distance=10mm},
scale=2]
  \node {root}
    child {node {1}
      child {node {leaf}}
      child {node {leaf}}
    }  
    child {node {2}
	  child {node {leaf}}
      child {node {leaf}}
    };
\end{tikzpicture}
\end{document}
```

>[!important] NOTA
>L'albero della ricorsione è detto anche **recursion trace**.

Nell'esecuzione di un programma (in Java per esempio) entrano in gioco due spazi di memoria:
1. **HEAP**: memoria per allocazione dinamica, destinata a contenere gli oggetti.
2. **STACK**: spazio destinato a memorizzare variabili locali ai metodi e i riferimenti agli oggetti.
   - Per ogni invocazione di un metodo viene inserito un *record di attivazione* contenente variabili e riferimenti a oggetti relativi a *quella invocazione*.
   - Un *RA* viene *eliminato* quando l'*invocazione* corrispondente *termina*.
   - I *RA* sono inseriti/eliminati con politica *LIFO* e in ogni istante si può accedere ai dati relativi all'ultimo RA ma non agli altri.
## COMPLESSITA' DI ALGORITMI RICORSIVI
La complessità di un algoritmo ricorsivo $A$ può essere *stimata* grazie all'*albero della ricorsione*.

Consideriamo l'albero associato all'esecuzione di $A$ su una istanza $i$ di taglia $n$:
- A ogni *nodo* è attribuito un **costo** pari al numero di operazioni eseguite dall'invocazione corrispondente a quel nodo, *escluse* quelle fatte dalle *invocazioni ricorsive al suo interno*.
- Il numero totale di operazioni eseguite da $A$ per risolvere $i$ si ottiene sommando i costi associati a tutti i nodi.

Per ottenere un *upper bound* a $t_{A}(n)$, si ricava una stima per eccesso del numero totale di operazioni che valga per tutte le istanze $i$ di taglia $n$.
Per ottenere un *lower bound* si trova un'istanza particolare o si fa una stima inferiore che va bene per tutte le istanze.

Vediamo come applicare questo procedimento all'algoritmo *reverseArray(A,i,j)*:
- **INPUT**: Array $A$, indici $i,j\geq 0$.
- **OUTPUT**: Array $A$ con gli elementi in $A[i\div j]$ ribaltati.
```pseudo
\begin{algorithm}
\caption{reverseArray(A,i,j)}

 \begin{algorithmic}
	\If{($i<j$)}
		\State exchange $A[i]$ with $A[j]$
		\State \Call{reverseArray}{$A,i+1,j-1$} 
    \EndIf
  \Return 
 \end{algorithmic}
\end{algorithm}
```
Notiamo che l'algoritmo esegue *al più una chiamata ricorsiva* per ogni istanza: si tratta infatti di una *ricorsione lineare*. 
Albero della ricorsione associato ad una generica istanza di taglia $n$ ($=j-i+1$):
- **Numero di livelli**: $\left\lfloor  \frac{n}{2}  \right\rfloor+1$ dato che ad ogni chiamata successiva riduco di $2$ la taglia.
- **Costo** di **ciascun nodo**: $\Theta(1)$ operazioni.

Per cui ogni istanza di *taglia* $n$ richiede $\Theta(n)$ operazioni.
## CORRETTEZZA DEGLI ALGORITMI RICORSIVI
Per provare la **correttezza** o una qualsiasi altra proprietà di un algoritmo ricorsivo $A$ si ricorre ancora una volta all'**induzione**.

Sia $n$ la taglia dell'istanza:
- Si dimostra la correttezza per i *casi base* $n \in[n_{0},n_{0}+k]$.
- Supponendo che $A$ risolva correttamente tutte le istanze di taglia $m \in[n_{0},n]$ per un qualche $n>n_{0}+k$ si dimostra che esso *risolve correttamente anche* tutte le istanze di *taglia $n+1$*.

Vediamo come applicare il procedimento all'algoritmo *linearSum(A,n)*.
- **INPUT**: Array $A$ di interi, indice $n\geq 1$.
- **OUTPUT**: $\sum_{i=0}^{n-1}A[i]$.
```pseudo
\begin{algorithm}
\caption{linearSum(A,n)}

 \begin{algorithmic}
	\If{($n=1$)}
		\Return A[0]
    \EndIf
  \Return \Call{linearSum}{$A,n-1$} + A[n-1]
 \end{algorithmic}
\end{algorithm}
```

- **CASO BASE**: $n=1$, la correttezza è banale.
- **PASSO INDUTTIVO**: Fissiamo $n\geq 1$ arbitrario e assumiamo che $\text{linearSum}(A,j)$ 
  sia *corretto* $\forall A$, $\forall 1\leq j\leq n$.
  Consideriamo ora $\text{linearSum}(A,n+1)$ con $A$ array di $n+1$ elementi, che restituirà $\text{linearSum}(A,n)+A[n]$.
  Ma questo è uguale a $\left( \sum_{i=0}^{n-1}A[i] \right)+A[n]$ per ipotesi induttiva.
  Ma tale valore è anche $\sum_{i=0}^n A[i]$.
- Concludiamo quindi che il *valore restituito* è **corretto**.
## CALCOLO EFFICIENTE DI POTENZE
Consideriamo il seguente problema:
>[!tip] PROBLEMA
>Dato $x \in \mathbb{R}$ e $n\geq 0$ intero, calcolare $p(x,n)=x^n$.

Il primo algoritmo che ci viene in mente consiste nel moltiplicare $x$ per sè stesso $n$ volte, la cui complessità è chiaramente $\Theta(n)$.
Vediamo tuttavia di seguito un approccio più efficiente.

>[!tip] OSSERVAZIONE CHIAVE:
>$$ p(x,n) = \begin{cases} 1 & n=0 \\ x\cdot p(x,\frac{n-1}{2})^2 & n > 0 \text{ dispari} \\ p(x,\frac{n}{2})^2 & n > 0 \text{ pari} \end{cases} $$

Vediamo ora l'algoritmo *power(x,n)*:
- **INPUT**: $x \in \mathbb{R}$ e $n\geq 0$ intero.
- **OUTPUT**: $p(x,n)$.
```pseudo
\begin{algorithm}
\caption{power($x,n$)}

 \begin{algorithmic}
	\If{($n=0$)}
		\Return $1$
    \EndIf
    \If{($n$ even)}
	    \State $y\gets$ \Call{power}{$x,n/2$}
		\Return $y\cdot y$
    \Else
	    \State $y\gets$ \Call{power}{$x,(n-1)/2$}
		\Return $x\cdot y\cdot y$
    \EndIf
  
 \end{algorithmic}
\end{algorithm}
```
Consideriamo $n$ stesso come taglia dell'istanza e supponiamo $n\geq 1$:
- Alla $i$-esima chiamata ricorsiva l'algoritmo viene invocato per un esponente $n_{i}\leq \frac{n}{2^i}$.
  Di conseguenza, l'albero della ricorsione avrà $O(\log n)$ **nodi**.
- Ogni invocazione di $\text{power}$ esegue $\Theta(1)$ operazioni, esclusa l'eventuale chiamata ricorsiva.
  Quindi il **costo per nodo** è $\Theta(1)$.
- Concludiamo che $t_{\text{power}}(n)\in O(\log n)$.

### APPLICAZIONE AI NUMERI DI FIBONACCI
Fissati il *rapporto aureo* $\phi=\frac{1+\sqrt{ 5 }}{2}$ e $\hat{\phi} = \frac{1-\sqrt{ 5 }}{2}$ possiamo calcolare l'*n-esimo numero di Fibonacci* grazie alla seguente relazione:
$$
F(n) = \frac{1}{\sqrt{ 5 }}(\phi^n-\hat{\phi}^n)
$$
Possiamo allora scrivere l'algoritmo *powerFib(n)*:
- **INPUT**: intero $n\geq 0$.
- **OUTPUT**: $F(n)$.

```pseudo
\begin{algorithm}
\caption{powerFib($n$)}

 \begin{algorithmic}
	  \State $\phi \gets \frac{1+\sqrt{ 5 }}{2}$
	  \State $\hat{\phi} \gets \frac{1-\sqrt{ 5 }}{2}$
	  \Return \Call{power}{$\phi,n$} - \Call{power}{$\hat{\phi},n$} 
 \end{algorithmic}
\end{algorithm}
```
Da quanto provato per $\text{power}$, otteniamo che la complessità di $\text{powerFib}$ è $O(\log n)$.