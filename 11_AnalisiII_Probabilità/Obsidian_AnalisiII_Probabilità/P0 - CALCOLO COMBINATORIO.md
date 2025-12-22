# INDICE SEZIONE
- [ ] [[#OBIETTIVI]]
- [ ] [[#CARDINALITA']]
- [ ] [[#INSIEMI E PRINCIPI]]
- [ ] [[#SEQUENZE]]
- [ ] [[#SPARTIZIONI E SOTTOINSIEMI]]
- [ ] [[#BINOMIALE]]
# OBIETTIVI
Il calcolo combinatorio nasce principalmente con l'obiettivo di *contare i modi in cui avvengono i fenomeni*.
I metodi sviluppati si basano su:
- *Cardinalità* di *insiemi finiti*.
- Principi di *moltiplicazione, divisione, biezione*.
- Contare i *sottoinsiemi* di un insieme finito.
- Contare le *permutazioni* di un insieme finito.
# CARDINALITA'
Ricordiamo la definizione di cardinalità di un insieme:
>[!def] CARDINALITA'
>Dato un insieme finito $X$, chiamiamo cardinalità di $X$ il numero degli elementi in $X$ e la indichiamo con $|X|$.

>[!prop] PROPRIETA' DELLA CARDINALITA'
>Se $A,B\subseteq X$ sono *sottoinsiemi finiti*, allora:
>1. $|A\cup B|=|A|+|B|-|A\cap B|$
>2. $|A\times B|=|A|\cdot|B|$
>3. $|X\setminus A|=|X|-|A|$
>

>[!prop] PRINCIPIO DI INCLUSIONE/ESCLUSIONE (3 sottoinsiemi)
>Se $A_{1},A_{2},A_{3}$ sono *sottoinsiemi finiti* di $X$, allora:
>$$ |A_{1}\cup A_{2}\cup A_{3}| = |A_{1}|+|A_{2}|+|A_{3}| -|A_{1}\cap A_{2}|-|A_{1}\cap A_{3}|-|A_{2}\cap A_{3}| + |A_{1}\cap A_{2}\cap A_{3}| $$

>[!important] MNEMONICAMENTE
>Somma delle 3 cardinalità $-$ *intersezioni tra due* (contante due volte) $+$ *intersezione dei tre* (altrimenti contata e sottratta 3 volte).

Tale principio si può chiaramente generalizzare per $n$ sottoinsiemi:
>[!prop] GENERALIZZAZIONE P. DI INCLUSIONE/ESCLUSIONE
>Dati $A_{1},\dots,A_{n}\subseteq X$ sottoinsiemi *finiti* di un insieme $X$, allora:
>$$ |A_{1}\cup\dots\cup A_{n}| = \sum_{1}-\sum_{2} + \dots + (-1)^{n-2}\sum_{n-1} + (-1)^{n-1}\sum_{n} $$
>Con:
>- $\sum_{1}=|A_{1}|+\dots+|A_{n}|$
>- $\sum_{k} = \sum_{i_{1}<\dots<i_{k}}|A_{i_{1}}\cap\dots\cap A_{i_{k}}|$ intersezioni tra $k$ sottoinsiemi
# INSIEMI E PRINCIPI
## ESEMPIO
Vediamo un esempio banale di un problema che ha a che fare con il calcolo combinatorio: sapendo che 1 bit può essere $0$ o $1$ ed 1 byte è composto da 8 bit, quanti numeri possiamo rappresentare con un byte?

Per problemi semplici può sembrare utile *elencare tutti i casi possibili*. Tale approccio è detto **brute force** ed è *assolutamente inefficiente* nella maggior parte delle situazioni.

In questo caso abbiamo a che fare con una *sequenza*:
$$
\underline{s} = (a_{8}a_{7}\dots a_{1})
$$
con $a_{i}\in \{ 0,1 \}$.
Per cui possiamo dire:
$$
\underline{s}\in \{ 0,1 \}\times\{ 0,1 \} \times \dots \times \{ 0,1 \} = \{ 0,1 \}^8
$$
Per trovare il *range di valori possibili* osserviamo che vale (per una funzione $f$ che associa alla sequenza binaria il suo valore decimale):
$$
f(0\dots0) \leq f(\underline{s}) \leq f(1\dots1)
$$
La funzione $f$ è:
$$
f(\underline{s}) = \sum_{k=1}^{8}a_{k}2^{k-1}
$$
Allora:
$$
0 \leq f(\underline{s}) \leq \sum_{k=1}^{8}2^{k-1} = 2^{8}-1 = 255
$$
## PRINCIPIO DI MOLTIPLICAZIONE
Supponiamo di poter individuare gli elementi di un insieme $X$ mediante una *procedura*, individuando $k$ *tappe ordinate*, dove:
- La tappa $1$ ha $n_{1}$ *esiti possibili*.
- La tappa $2$ ha $n_{2}$ esiti possibili.
- ...
- La tappa $k$ ha $n_{k}$ esiti possibili.
- L'elemento di $X$ trovato con le $k$ tappe *individua* **univocamente** l'*esito di ciascuna fase*.

>[!prop] PROPOSIZIONE
>Se ci troviamo nella situazione descritta sopra si ha:
>$$ |X| = n_{1}n_{2}\dots n_{k} $$
### CASI IN CUI IL PRINCIPIO NON VALE
Esistono dei casi in cui l'ultima ipotesi non è rispettata, cioè l'elemento di $X$ *non individua* in modo *univoco* l'esito di ciascuna tappa.

Supponiamo di avere due gruppi di persone di due persone ciascuno:
- $A=\{ A_{1},A_{2} \}$
- $B=\{ B_{1},B_{2} \}$

Vogliamo un gruppo $X$ di 2 persone in cui *almeno una* proviene dal gruppo $A$:
- Tappa 1: scegliamo una persona del gruppo $A$. Abbiamo due possibilità $\implies n_{1}=2$.
- Tappa 2: scegliamo una persona tra le rimanenti. Tre possibilità $\implies n_{2}=3$.

Elenchiamo (solo per questa volta) tutti gli esiti possibili:
$$
A_{1}A_{2}, A_{1}B_{1}, A_{1},B_{2},A_{2}A_{1},A_{2}B_{1},A_{2}B_{2}
$$
Sembrano essere effettivamente $6=2\cdot3=n_{1}n_{2}$.
Tuttavia $A_{1}A_{2}$ e $A_{2}A_{1}$ sono *esiti* **equivalenti**: il gruppo ottenuto è lo stesso.
Per cui gli esiti possibili sono in realtà 5: nel gruppo $A_{1}A_{2}=A_{2}A_{1}$ **non** è *possibile* distinguere chi proviene dalla prima tappa e chi dalla seconda.
>[!important] NOTA
>Cioè stiamo dicendo che l'**ordine non è importante** (e quindi non permette di distinguere i due casi).
## PRINCIPIO DI DIVISIONE
>[!prop] PROPOSIZIONE
>Siano $X,Y$ inisiemi *finiti*.
>Se ad ogni elemento di $Y$ corrispondono $m$ elementi di $X$ tramite
>$$ f:X\to Y $$
>allora si ha:
>$$ |Y| = \frac{|X|}{m} $$

Un esempio banale è dato dalla seguente situazione: 10 studenti partecipano ad un'attività, presentando 3 progetti ciascuno.
Si estraggono 2 progetti per una presentazione. Quante sono le scelte possibili?
- $X$ insieme dei progetti consegnati $|X|=30$.
- $Y$ insieme degli studenti $|Y|=10$.

E si ha proprio $10=\frac{30}{m}=\frac{30}{3}$.
>[!important] NOTA
>E' una formulazione alternativa del *principio di moltiplicazione*: potevamo arrivare alla risposta osservando che la prima tappa consiste nello scegliere uno studente fra 10 ($n_{1}=10$) e la seconda nello scegliere un progetto fra 3 ($n_{2}=3$), per cui le possibilità sono sempre $n_{1}n_{2}=30$.
## PRINCIPIO DI BIEZIONE
>[!prop] PROPOSIZIONE
>Due insiemi *finiti* hanno la **stessa cardinalità** *se e solo se* possono essere messi in *corrispondeza* **biunivoca**.

Faremo uso della seguente notazione:
- Per $n\in \mathbb{N}$ denotiamo $I_{n}:=\{ 1,\dots,n \}$ e $I_{0}:=\emptyset$.
- $|I_{n}|=n$ $\forall n\in \mathbb{N}\cup \{ 0 \}$.
### APPLICAZIONE
Consideriamo i seguenti 3 insiemi:
1. $A=\{ \text{sottoinsiemi di } I_{n} \}$
2. $B=\{ \text{funzioni }f:I_{n}\to \{ 0,1 \} \}$
3. $C=$ numeri binari $\{ (a_{n}a_{n-1}\dots a_{1}):a_{i}\in \{ 0,1 \},i=1,\dots,n \}$

Sia $S=\{ k_{1},\dots,k:l \}\subseteq I_{n}$ con $0\leq l\leq n$. 
Definiamo la seguente funzione $\phi:A\to B$:
$$
\phi(S) :=  f_{S}(j) = \begin{cases}
1 & j\in S \\
0 & j\not\in S 
\end{cases} \text{ }\text{ }\text{ }\text{ }j=1,\dots,n
$$
Si ha $S=f^{-1}_{S}(1)$: $\phi$ è *invertibile* e quindi *biunivoca*.
In particolare, e' una *biezione* tra gli insiemi $A$ e $B$:
- Ad un sottoinsieme $S$ di $I_{n}$ (insieme $1$) associa una funzione $f_{S}\in B$.
- Ad una funzione $f_{S}\in B$ associa $S\in A$ (sottoinsieme di $I_{n}$).

Grazie ad $f_{S}$ possiamo trovare una *biezione* anche tra $B$ e $C$.
Si tratta della funzione $\psi:B\to C$:
$$
\psi(f_{S}) := (f_{S}(n)f_{S}(n-1)\dots f_{S}(1)) 
$$
- Ad una funzione $f_{S}$ associa un numero binario $\in C$.
- L'inversa $\psi^{-1}$ associa ad un numero binario una funzione $f_{S}$.

Per cui abbiamo mostrato che esiste una **corrispondenza** tra questi insiemi.
Si può quindi dimostrare che vale:
>[!theorem] TEOREMA
>Sia $X$ un insieme finito di cardinalità $n$.
>La cardinalità dell'*insieme dei sottoinsiemi di $X$*, che chiamiamo $SI_{X}$ è data da:
>$$ |SI_{X}| = |SI_{I_{n}}| = |\{ 0,1 \}^n| = 2^n $$

Cioè un insieme di $n$ elementi ha $2^n$ possibili sottoinsiemi (compreso quello vuoto).
# SEQUENZE 
Nel paragrafo precedente abbiamo avuto a che fare con *insiemi*, cioè "collezioni" in cui l'*ordine non è importante*.
Si può *generalizzare* la nozione di insieme per applicarla a casi in cui l'*ordine importa*: si parla in questo caso di *sequenze*.
>[!def] $k$-SEQUENZA 
>Chiamiamo $k$-**sequenza** di $I_{n}$ una $k$-upla **ordinata**
>$$ (a_{1}\dots a_{k}) $$
>di elementi *non necessariamente distinti* di $I_{n}$.
>Ossia:
>$$ I_{n}\times I_{n}\times\dots I_{n} = I_{n}^k $$

Inoltre diciamo che una $k$-sequenza è **senza ripetizioni** se $a_{l}\ne a_{k}$ per ogni $k\ne l$.
Faremo uso della seguente notazione:
- $S((n,k))$: insieme delle sequenze di $I_{k}$ **con** eventuali *ripetizioni*.
- $S(n,k)$: insieme delle sequenze di $I_{k}$ **senza** *ripetizioni*.

Dove $n$ indica il numero di *elementi possibili* e $k$ la lunghezza della sequenza.
>[!prop] PROPOSIZIONE
>$$ |S(n,k)| = n\cdot(n-1)\cdot...\cdot(n-k+1) $$
>$$ |S((n,k))| = n\cdot n\cdot...\cdot n = n^k $$

Per quanto riguarda le *sequenze senza ripetezioni* possiamo essere più precisi:
>[!prop] NUMERO DI SEQUENZE SENZA RIPETIZIONE
>$$ |S(n,k)| = \begin{cases} \frac{n!}{(n-k)!} & k\leq n \\ \\ 0 & \text{altrimenti} \end{cases} $$
## INFLUENZA DELL'ORDINAMENTO
Una $k$-sequenza si *distingue* da un *sottoinsieme* di $k$ elementi perchè è un **elenco ordinato**.
Vediamo che relazione esiste tra i sottoinsiemi di $X$ di $k$ elementi e le $k$-sequenze di $X$.
>[!def] PERMUTAZIONE
>Chiamiamo **permutazione** *di una* $k$-sequenza $\underline{a}=(a_{1}\dots a_{k})$ di un insieme $X$ una *qualunque* $k$-sequenza $\underline{b}=(b_{1}\dots b_{k})$ di $X$ ottenuta **riordinando** $\underline{a}$.
>Ossia esiste una funzione:
>$$ \tau:\{ 1,\dots,k \}=I_{k} \to \{ 1,\dots,k \}=I_{k} $$
>biunivoca che permette di scrivere:
>$$ b_{i} = a_{\tau(i)} $$
## ANAGRAMMI
>[!def] ANAGRAMMA
>Chiamiamo *anagramma* di una $k$-sequenza $\underline{s}$ una qualsiasi sequenza $\underline{s}'$ che ha gli *stessi termini* con le *stesse ripetizioni* di $\underline{s}$, ma in *ordine potenzialmente diverso*.

>[!prop] PROPOSIZIONE
>Se una $k$-sequenza $\underline{s}$ è composta da $r_{1}$ ripetizioni di $1$, $\dots$, $r_{n}$ ripetizioni di $n$ (qualche $r_{i}$ può anche essere nullo), il *numero dei suoi anagrammi* è dato da:
>$$ \frac{(r_{1}+\dots+r_{n})!}{k_{1}!\dots k_{n}!} = \frac{k!}{k_{1}!\dots k_{n}!} $$
# SPARTIZIONI E SOTTOINSIEMI
>[!def] $n$-SPARTIZIONI
>Chiamiamo $n$-**spartizione** di $I_{k}$ una $n$-*upla ordinata* di sottoinsiemi $(C_{1}\dots C_{n})$ con
>- $C_{l}\subseteq I_{k}$
>- $C_{l}\cap C_{m}=\emptyset$ se $l\ne m,l=1,\dots,n$ (a due a due disgiunti)
>- $C_{1}\cup\dots\cup C_{n}=I_{k}$
>  
>I sottoinsiemi possono anche comprendere l'insieme vuoto.
>NOTA: una *partizione* di $I_{k}$ è una *sparitizione* di $I_{k}$ *non ordinata*.

>[!prop] PROPOSIZIONE
>Le $n$-**spartizioni** di $I_{k}$ sono *tante quante* le $k$-**sequenze** di $I_{n}$.
>$$ |\{ n-\text{spartizioni di }I_{k} \}| = |S((n,k))| $$

>[!def] $k$-SOTTOINSIEMI
>Chiamiamo $k$-**sottoinsieme** un *sottoinsieme* formato da $k$ *elementi distinti* di $I_{n}$.
>Indichiamo con $C(n,k)$ il *numero* di $k$-*sottoinsiemi* di $I_{n}$.

>[!important] NOTA
>Cioè è una $k$-sequenza (*senza ripetizioni*) di $I_{n}$ *non ordinata*.
>Questo perchè un insieme è sostanzialmente una sequenza di elementi distinti, in cui l'ordine non è importante.
# BINOMIALE
>[!def] BINOMIALE
>Dati $n,k\in \mathbb{N}\cup \{ 0 \}$, con $k\leq n$, chiamiamo *binomiale* $n$ su $k$:
>$$ \begin{pmatrix} n \\ k \end{pmatrix} := \frac{|S(n,k)|}{k!} = \frac{n!}{k!(n-k)!} $$

>[!prop] PROPOSIZIONE
>Per $k,n\in \mathbb{N}$ il numero di *sottoinsiemi di* $I_{n}$ con $k$ *elementi* è
>$$ C(n,k) = \begin{pmatrix} n \\ k \end{pmatrix} $$

>[!check] DIM.
>$X=$ $k$-sequenze di $I_{n}$ *senza ripetizione*. (ordine importante)
>$Y=$ sottoinsiemi di $k$ elementi di $I_{n}$. (ordine non importante)
>Per il principio di divisione abbiamo:
>$$ |Y| = \frac{|X|}{k!} = \frac{S(n,k)}{k!} = \begin{pmatrix} n \\ k \end{pmatrix} $$
## PROPRIETA'
Valgono le seguenti proprietà:
1. "Simmetria":
$$
\begin{pmatrix}
n \\
k
\end{pmatrix} = \frac{n!}{k!(n-k)!} = \begin{pmatrix}
n \\
n-k
\end{pmatrix}
$$
2. Binomio di Newton:
$$
(x+y)^n = \sum_{k=0}^n \begin{pmatrix}
n \\
k
\end{pmatrix}x^k y^{n-k}
$$
3. Sottoinsiemi:
$$
|\{ \text{sottoinsiemi di }I_{n} \}| = 2^n = (1+1)^n =\sum_{k=0}^n \underbrace{ \begin{pmatrix}
n \\
k
\end{pmatrix} }_{ C(n,k) } = \sum_{k=0}^n |\{\text{sottoinsiemi di k elem. di}I_{n} \}| 
$$
4. Formula di Stiefel: se $n,k\geq 1$
$$
\begin{pmatrix}
n-1 \\
k-1
\end{pmatrix} + \begin{pmatrix}
n-1 \\
k
\end{pmatrix} = \begin{pmatrix}
n \\
k
\end{pmatrix}
$$
>[!important] NOTA
>Un modo per interpretare la $4$ è il seguente.
>Consideriamo l'insieme $I_{n}$ di $n$ elementi e scegliamo un $\bar{x}\in I_{n}$: $C(n,k)$ è il numero di sottoinsiemi di $I_{n}$ che contengono $k$ elementi.
>$C(n-1,k-1)$ è il numero di sottoinsiemi che *contengono* $\bar{x}$ ($\bar{x}$ fissato, restano $k-1$ elementi da scegliere tra i rimanenti $n-1$), mentre $C(n-1,k)$ è il numero di sottoinsiemi che *non contengono* $\bar{x}$ ($k$ elementi tra gli $n-1$ rimanenti).

