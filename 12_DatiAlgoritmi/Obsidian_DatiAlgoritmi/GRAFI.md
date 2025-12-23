# INDICE SEZIONE
- [ ] [[#DEFINIZIONE]]
- [ ] [[#GRAFI SEMPLICI E NON DIRETTI]]
- [ ] [[#VISITE (GRAPH TRAVERSAL)]]
- [ ] [[#BREADTH FIRST SEARCH]]
- [ ] [[#DEPTH FIRST SEARCH]]
- [ ] [[#CONFRONTO BFS E DFS]]
- [ ] [[#CAMMINI MINIMI SU GRAFI PESATI]]
- [ ] [[#ALGORITMO DI DIJKSTRA]]
- [ ] [[#GRAFI SEMPLICI DIRETTI]]
- [ ] [[#VISITE SU GRAFI DIRETTI]]
- [ ] [[#GRAFI DIRETTI ACICLICI]]
# DEFINIZIONE
>[!def] GRAFO
>Grafo $G=(V,E)$:
>	- $V=$ insieme di **vertici** (o *nodi*).
>	- $E=$ collezione di **archi** (*coppie di vertici*).
>
>Il grafo si dice **diretto** se ogni arco $(u,v)\in E$ è una *coppia ordinata* ($u\to v$), altrimenti si dice **non diretto** ($u-v$).
>

Esempio di grafo *diretto*:
```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A) at (0,0) {A};
    \node (B) at (0,3) {B};
    \node (C) at (2.5,4) {C};
    \node (D) at (2.5,1) {D};
    \node (E) at (2.5,-3) {E};
    \node (F) at (5,3) {F} ;
\end{scope}

\begin{scope}[>={Stealth[black]},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path [->] (A) edge node {$5$} (B);
    \path [->] (B) edge node {$3$} (C);
    \path [->] (A) edge node {$4$} (D);
    \path [->] (D) edge node {$3$} (C);
    \path [->] (A) edge node {$3$} (E);
    \path [->] (D) edge node {$3$} (E);
    \path [->] (D) edge node {$3$} (F);
    \path [->] (C) edge node {$5$} (F);
    \path [->] (E) edge node {$8$} (F); 
    \path [->] (B) edge[bend right=60] node {$1$} (E); 
\end{scope}
\end{tikzpicture}

\end{document}
```

Esempio di grafo *non diretto*:
```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A) at (0.5,0) {A};
    \node (B) at (0,3) {B};
    \node (C) at (2.5,4) {C};
    \node (D) at (2.5,1) {D};
    \node (E) at (3.5,-2) {E};
    \node (F) at (5,3) {F} ;
\end{scope}

\begin{scope}[>={Stealth[black]},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path  (A) edge node {$5$} (B);
    \path  (B) edge node {$3$} (C);
    \path  (A) edge node {$4$} (D);
    \path  (D) edge node {$3$} (C);
    \path  (A) edge node {$3$} (E);
    \path  (D) edge node {$3$} (E);
    \path  (D) edge node {$3$} (F);
    \path  (C) edge node {$5$} (F);
    \path  (E) edge node {$8$} (F); 
    \path  (B) edge[bend right=90] node {$1$} (E); 
\end{scope}
\end{tikzpicture}

\end{document}
```

>[!important] OSSERVAZIONI
>1. La definizione ammette la presenza di *archi multipli* tra due vertici (per questo $E$ è una *collezione* e non un insieme) e di *self loop* (archi $(u,u)$).
>2. Un **grafo semplice** è un grafo *senza archi multipli* (in questo caso $E$ è un insieme) e *senza self-loop*.
>3. Per alcune applicazioni, agli *archi* sono associati dei **pesi**.

Ci concentreremo principalmente su *grafi semplici*.
## ESEMPI DI APPLICAZIONI
I grafi sono utilizzati di solito per rappresentare **networked data**, cioè dati in cui l'informazione è costituita da:
1. *Singoli elementi*.
2. **Relazioni** tra *(coppie)* di elementi.

>[!important] NOTA
>Esistono anche *generalizzazioni* dei grafi (**ipergrafi**) in cui le *relazioni* coinvolgono *più di 2 elementi*.
### RETI SOCIALI
**FACEBOOK**:
- **Vertici**: *profili* degli utenti.
- **Archi**: *amicizie* fra utenti.
- **Non diretto**

**INSTAGRAM**:
- **Vertici**: *profili* degli utenti.
- **Archi**: *follow* 
- **Diretto** (un utente può seguirne uno e non è detto il viceversa)
### RETI STRADALI
- **Vertici**: *incroci*.
- **Archi**: *segmenti* di strade fra incroci.
- **Diretto** (possono esserci sensi unici).

### ALTRI ESEMPI
- Internet
- Web
- Supercomputer
- Reti di comunicazione
- Protein-protein network.
# GRAFI SEMPLICI E NON DIRETTI
>[!important] TERMINOLOGIA
>1. **Inglese**: per nodi/vertici si usa *nodes/vertices*. Per archi si usa *edges/arcs*.
>   Di solito si usa *edges* per grafi *non diretti*, *arcs* per grafi *diretti*.
>2. Dato un arco $e=(u,v)\in E$ diciamo che $e$ è *incidente* su $u$ e $v$ e che $u$ e $v$ sono **adiacenti**.
>3. I **vicini** di un vertice $v$ sono tutti i vertici $u$ tali che $(u,v)\in E$.
>4. Il **grado** di un vertice $v\in V$ ($\text{degree(v)}$) è il *numero di archi* incidenti su $v$.
## CONCETTI FONDAMENTALI
>[!def] CAMMINO
>In inglese *path*:
>$$ u_{1},u_{2},\dots, u_{k} $$
>con $(u_{i},u_{i+1})\in E$ per $1\leq i\leq k$.

La **lunghezza del cammino** è il *numero di archi* (ovvero $k-1$). Nel caso di *archi pesati*, la lunghezza è la *somma dei pesi degli archi*.
Il cammino si dice **semplice** se *non ha vertici ripetuti*.

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A) at (0.5,0) {A};
    \node (B) at (0,3) {B};
    \node (C) at (2.5,4) {C};
    \node (D) at (2.5,1) {D};
    \node (E) at (3.5,-2) {E};
    \node (F) at (5,3) {F} ;
\end{scope}

\begin{scope}[>={Stealth[black]},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path  (A) edge node {$5$} (B);
    \path  (B) edge node {$3$} (C);
    \path  (A) edge node {$4$} (D);
    \path  (D) edge[draw = blue] node {$3$} (C);
    \path  (A) edge[draw = red] node {$3$} (E);
    \path  (D) edge[draw = blue] node {$3$} (E);
    \path  (D) edge[draw = blue] node {$3$} (F);
    \path  (C) edge[draw = blue] node {$5$} (F);
    \path  (E) edge node {$8$} (F); 
    \path  (B) edge[draw = red, bend right=90] node {$1$} (E); 
\end{scope}
\end{tikzpicture}

\end{document}
```
In figura sono evidenziati due cammini:
- Cammino *semplice* **AEB** (in rosso).
- Cammino *non semplice* **EDFCD** (in blu).

>[!def] CAMMINO MINIMO
>Dati due vertici $x,y\in V$, il *cammino minimo* (**shortest path**) tra $x$ e $y$, se ne esiste uno, è un cammino $x=u_{1},u_{2},\dots,u_{k}=y$ di *lunghezza minima*.
>La sua lunghezza è detta **distanza** $d(x,y)$.
>Se non esiste alcun cammino tra $x$ e $y$, per convenzione si considera $d(x,y)=\infty$.

>[!def] CICLO
>E' un *cammino* $u_{1},\dots,u_{k}$ con $u_{1}=u_{k}$, cioè in cui i vertici *iniziale e finale coincidono*.

Un ciclo si dice **semplice** se gli unici vertici ripetuti sono gli estremi.

>[!def] SOTTOGRAFO
>Dato un grafo $G=(V,E)$, un suo *sottografo* è $G'=(V',E')$ con $V'\subseteq V$, $E'\subseteq E$ e tale che gli archi di $E'$ incidono solo su $V'$.

>[!def] SOTTOGRAFO DI COPERTURA
>Dato un grafo $G=(V,E)$, un suo *sottografo di copertura* (**spanning subgraph**) è un sottografo $G'=(V',E')$ con $V'=V$ e $E'\subseteq E$.

Cioè un *sottografo* che *copre tutti i vertici*, ma con potenzialmente meno archi.

>[!def] GRAFO CONNESSO
>Un grafo $G=(V,E)$ si dice *connesso* (**connected**) se per ogni $u,v\in V$ eiste un *cammino* che inizia in $u$ e termina in $v$.

>[!def] GRAFO DISCONESSO
>Un grafo si dice *disconnesso* (**disconnected**) se *non è conesso*.

>[!def] COMPONENTI CONNESSE
>Le *componenti connesse* (**connected components**) di un grafo $G=(V,E)$ sono i *sottografi connessi massimali* di $G$, ovvero la famiglia di sottografi $G_{i}=(V_{i},E_{i})$ con $1\leq i\leq k$ tali che:
>- $G_{i}=(V_{i},E_{i})$ è *connesso* per $1\leq i\leq k$.
>- $V=V_{1}\cup V_{2}\cup\dots\cup V_{k}$ (partizione: $V_{i}\cap V_{j}=\emptyset$ $\forall i\ne j$).
>- $E=E_{1}\cup E_{2}\cup\dots\cup E_{k}$ (partizione: $E_{i}\cap E_{j}=\emptyset$ $\forall i\ne j$)
>- $\forall i\ne j$ non esistono archi in $E$ tra $V_{i}$ e $V_{j}$.

E si ha che se $G$ è connesso $k=1$.

>[!def] ALBERO RADICATO (legame con i grafi)
>Un *albero radicato* (rooted tree) è un grafo $G=(V,E)$ tale che:
>- Esiste un vertice *radice* $r\in V$.
>- Per ogni $u\in V$, con $u\ne r$, esiste un *unico padre* $p(u)\in V$, e si ha che $E=\{ (u,p(u)):u\in V,u\ne r \}$
>- Per ogni $u\in V$ andando di *padre in padre* si *raggiunge* $r$.

>[!def] ALBERTO LIBERO (FREE TREE)
>E' un *grafo* $G=(E,V)$ **connesso e senza cicli**.

>[!def] FORESTA
>E' un *grafo* $G=(E,V)$ **senza cicli**, ovvero un insieme di *alberi disgiunti*.

>[!important] NOTA
>Il concetto di albero radicato è **lo stesso** di quello già visto in > [[ALBERI GENERALI]].
>I concetti di *albero radicato* e *albero libero* sono **equivalenti**: ogni albero *radicato* è un grafo connesso senza cicli, e ogni albero *libero* può essere visto come un albero radicato scegliendo un *vertice arbitrario* come *radice* e determinando di conseguenza le relazioni padre-figlio.

>[!def] SPANNING TREE
>Uno *spanning tree* di un grafo $G$ è uno spanning subgraph *connesso e senza cicli* (free tree).

Chiaramente, uno spanning tree *esiste solo se G è connesso*.

>[!def] SPANNING FOREST
>E' uno spanning subgraph senza cicli.

Cioè è una famiglia di alberi che copre il grafo.
## PRIMITIVE IMPORTANTI
- **Traversal**: esplorazione sistematica del grafo.
- **Connettività**: verifica se il grafo è connesso.
- **Identificazione componenti connesse**.
- **Ricerca di cammini minimi**.
- **Ricerca di minimum spanning tree**.
- **Stima della distanza media/massima**.
## PROPRIETA' 
>[!important] PROPOSIZIONI
>Sia $G=(V,E)$ un grafo *semplice e non diretto* con $|V|=n$ vertici ed $|E|=m$ archi.
>Valgono le seguenti proprietà:
>1. $\sum_{v\in V}\text{degree(v)}=2m$
>2. $m\leq\begin{pmatrix} n \\ 2 \end{pmatrix}$ e quindi $m\in O(n^2)$
>3. Se $G$ è un albero (rooted o free) allora $m=n-1$.
>4. Se $G$ è connesso, allora $m\geq n-1$.
>5. Se $G$ è senza cicli (cioè una foresta) $m\leq n-1$.

>[!check] DIM. 1
>Osserviamo banalmente che ogni arco $(u,v)$ è contato *esattamente due volte*: in $\text{degree(u)}$ e in $\text{degree(v)}$ $\square$.

>[!check] DIM. 2
>Essendo $G$ *semplice*, c'è *al più un arco per ogni coppia* $(u,v)$ con $u\ne v$ e $u,v\in V$.
>Perciò:
>$$ m \leq \begin{pmatrix} n \\ 2 \end{pmatrix} = \frac{n(n-1)}{2})\in O(n^2) $$ 

>[!check] DIM. 3
>Usando l'*equivalenza* tra *rooted* e *free tree* è sufficiente provare la proprietà in un rooted tree; e in un tale albero c'è *esattamente un arco* $(u,p(u))$ per ogni vertice $u\ne r$ $\implies n-1$ archi $\square$.
>

>[!check] DIM. 4
>Consideriamo il ciclo seguente:
>```pseudo
>\begin{algorithm}
>\begin{algorithmic}
>\While{esiste un ciclo C in G}
>\State rimuovi un arco da C
>\EndWhile
>\end{algorithmic}
>\end{algorithm}
>``` 
>Ad ogni iterazione il grafo *rimane connesso* e alla fine del while *non avrà cicli*.
>Per cui, dopo l'eliminazione di alcuni archi saranno rimasti $n-1$ archi.
>Allora inizialmente il numero di archi doveva essere $\geq n-1$ $\square$.

>[!check] DIM. 5
>Sia $k\geq1$ il numero di *componenti connesse* di $G$:
>$$ G_{i})=(V_{i},E_{i}) \text{ }i=1,\dots,k $$
>$\forall i$ $G_{i}$ è un grafo connesso e senza cicli.
>Se $n_{i}=|V_{i}|$ e $m_{i}=|E_{i}|$ allora:
>$$ m = \sum_{i=1}^k m_{i} = \sum_{i=1}^k (n_{i}-1) = -k + \sum_{i=1}^k n_{i} = n - k $$
>Essendo $k\geq 1$, si ha $m\leq n-1$ $\square$.
# RAPPRESENTAZIONE DI GRAFI
Sia $G=(V,E)$ un grafo con $n$ vertici ed $m$ archi.
Spesso, per comodità, si rappresentano i *vertici* con gli *interi* da $1$ a $n$.
## STRUTTURE DI BASE
La rappresentazione *più semplice* di $G$ fa uso di:
- Una **lista di vertici** $L_{V}$: ogni nodo della lista contiene *tutte le informazioni* rilevanti per un *vertice* $v\in V$ *distinto*.
  Se si rappresentano i vertici con numeri interi, $L_{V}$ può essere un array.
- Una **lista di archi** $L_{E}$: ogni nodo della lista contiene *tutte le informazioni* rilevanti per un *arco* $e=(u,v)\in E$ *distinto*, includendo tra esse i *puntatori* a $u$ e $v$.

>[!important] NOTA
>A seconda del problema da risolvere, ciascun vertice/arco può essere *arricchito da campi* strumentali per l'algoritmo scelto.

![[graphs_list_rep.png]]
## RAPPRESENTAZIONE ARCHI: LISTE DI ADIACENZA
Rappresentare gli archi tramite $L_{E}$ rende *poco efficienti* gli algoritmi che devono *esplorare i vicini di un vertice* o che devono avere accesso diretto agli archi.

Una possibile soluzione è data dalle **liste di adiacenza** (*adjacency lists*): per *ogni vertice* $v\in V$ si ha una *lista* $\mathcal{I}(v)$ di *puntatori agli archi* (elementi di $E$) *incidenti su* $v$.

![[graphs_adj_lists.png]]

>[!important] OSSERVAZIONE
>Tale rappresentazione è quella più utilizzata per i seguenti motivi:
>- Permette di rappresentare $G$ in *spazio lineare* nella taglia del grafo, cioè $\Theta(n+m)$.
>- Consente l'*accesso sequenziale* ai vicini di un vertice $v$, in *tempo lineare nel grado* di $v$. 
## RAPPRESENTAZIONE ARCHI: MATRICI DI ADIACENZA
Una soluzione alternativa è data dalle *matrici di adiacenza*: matrici $n\times n$ dove righe e colonne sono in corrispondenza $1:1$ con i vertici e si ha:
$$
A[i_{1},i_{2}] = \begin{cases}
\text{null} & \text{se }(i_{1},i_{2})\not\in E \\ \\
\text{puntatore a }e=(i_{1},i_{2})\in E & \text{se tale arco esiste}
\end{cases}
$$
![[graphs_adj_mat.png]]

>[!important] OSSERVAZIONI
>- La matrice di adiacenza consente l'*accesso* in *tempo costante* ad un arco e alle sue informazioni, ma richiede una rappresentazione dei *vertici tramite interi*.
>- La matrice di adiacenza occupa *spazio quadratico* $\Theta(n^2)$.
>  Per questo motivo è una rappresentazione usata soprattutto nel caso di *grafi densi* (con numero di archi quadratico nel numero di vertici) o per grafi con *pochi vertici*.
## MAPPE DI ADIACENZA
Una *via di mezzo* tra *liste* e *matrici* di adiacenza è costituita dalle **mappe di adiacenza**: per ogni vertice $v$ gli archi incidenti su di esso sono memorizzati in una mappa.
Usando una *tabella hash* per ogni mappa si può quindi avere *accesso* ad un qualsiasi arco in *tempo medio* $O(1)$, utilizzando globalmente uno spazio lineare nella taglia del grafo.
# VISITE (GRAPH TRAVERSAL)
Si tratta di *procedure sistematiche* per **esplorare** $G$ a partire da un vertice $s$, visitando *tutti* i vertici.
Esistono due approcci principali:
- **Breadth-First Search (BFS)** : dopo la visita di un vertice si visitano *tutti i vicini* prima di passare ai *vicini dei vicini*.
- **Depth-First Search (DFS)** : dopo la visita di un *vertice*, si visita un *vicino*, poi un *vicino del vicino* ecc.

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (1) at (4,4) {1};
    \node (2) at (2.1,2) {2};
    \node (3) at (4,2.3) {3};
    \node (4) at (6.1,2) {4};
    \node (5) at (2.3,0.4) {5};
    \node (6) at (4,0.2) {6} ;
\end{scope}

\begin{scope}[>={Stealth[black]},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path  (1) edge (2);
    \path  (1) edge (3);
    \path  (1) edge (4);
    \path  (2) edge (5);
    \path  (3) edge (6);
    \path  (5) edge (6); 
\end{scope}
\end{tikzpicture}

\end{document}
```

Vediamo l'ordine di visita dei vertici sul grafo di esempio in figura:
- **BFS**: 1, 2, 3, 4, 5, 6.
- **DFS**: 1, 2, 5, 6, 3, 4.
# BREADTH FIRST SEARCH
>[!def] NOTAZIONI E DEFINIZIONI
>Sia $G=(V,E)$ un grafo semplice, non diretto e non pesato:
>- Dato $s \in V$, $C_{s}\subseteq G$ denota la *componente connessa* di $G$ *contenente* $s$, costituita da tutti i vertici raggiungibili con cammini da $s$, e dagli archi si essi incidenti.
>- Ricordiamo che dati due vertici $x,y\in V$ nella stessa componente connessa, la loro *distanza* $d(x,y)$ è definita come la *minima lunghezza di un cammino* da $x$ a $y$. Se i vertici sono in componenti connesse distinte, si assume $d(x,y)=+\infty$.

>[!important] IPOTESI IMPLEMENTATIVE
>- Per ogni vertice $v\in V$ esiste un campo $\text{v.ID}$ che vale $1$ se $v$ è stato *visitato*, e vale $0$ altrimenti.
>- Per ogni arco $e\in E$ esiste un campo $\text{e.label}$ che memorizza una opportuna *etichetta* oppure vale $\text{null}$ se $e$ non ha ancora etichetta.
>- Per ogni $v\in V$ $\text{incidentEdges}(v)$ restituisce un *iteratore agli archi incidenti* su $v$ (quindi alla sua lista di adiacenza), che possono essere *enumerati* in tempo $\Theta(1)$ ciascuno.
>- Per ogni arco $e=(v,w)\in E$ $\text{opposite}(v,e)$ restituisce $w$ in tempo $\Theta(1)$.
## ALGORITMO
Algoritmo iterativo che, a partire da un vertice $s$:
1. *Visita tutti i vertici* di $C_{s}$.
2. *Etichetta* ciascun arco di $C_{s}$ come $\text{DISCOVERY}$ o $\text{CROSS EDGE}$.
3. *Partiziona* i vertici di $C_{s}$ in *livelli* $L_{i}$ in base alla loro *distanza* $i$ da $s$.

Algoritmo $\text{BFS}(G,s)$
- **INPUT**: grafo $G=(V,E)$, $s\in V$, ogni vertice $v$ di $C_{s}$ con $\text{v.ID}=0$ e ogni arco $e$ di $C_{s}$ con $\text{e.label=null}$.
- **OUTPUT**: visita ogni vertice di $C_{s}$ ed etichetta ogni arco di $C_{s}$ come $\text{DISCOVERY EDGE}$ o $\text{CROSS EDGE}$.

```pseudo
\begin{algorithm}
\caption{BFS(G,s)}
 \begin{algorithmic}
   \State visita il vertice $s$ e imposta $s$.ID$\gets$1
   \State $L_0 \gets$ lista contenente $s$, $i\gets$0
   \While{!$L_i$.isEmpty()}
     \State crea una lista di vertici $L_{i+1}$ vuota
     \ForAll{v$\in L_i$}
       \ForAll{e$\in$G.incidentEdges(v)}
         \If{e.label = null}
           \State w $\gets$ G.opposite(v,e)
           \If{w.ID = 0}
             \State e.label $\gets$ DISCOVERY EDGE
             \State visita il vertice w e imposta w.ID$\gets$1
             \State inserisci w in $L_{i+1}$
           \Else
             \State e.label $\gets$ CROSS EDGE
           \EndIf
         \EndIf
       \EndFor
     \EndFor
     \State i $\gets$ i+1
   \EndWhile 
   \Return
 \end{algorithmic}
\end{algorithm}
```
### CORRETTEZZA E PROPRIETA'
>[!important] PROPOSIZIONE
>Alla fine dell'esecuzione di $\text{BFS}(G,s)$ si ha che:
>1. Tutti i vertici di $C_{s}$ sono *visitati* e tutti gli archi di $C_{s}$ sono *etichettati* come $\text{DISCOVERY}$ o $\text{CROSS EDGE}$.
>2. I $\text{DISCOVERY EDGE}$ formano uno *spanning tree* $T$ di $C_{s}$ radicato in $s$ e chiamato **BFS tree**.
>3. $\forall v\in L_{i}$ il cammino in $T$ da $s$ a $v$ ha $i$ archi e $i=d(s,v)$ (*distanza* fra $s$ e $v$).
>4. Se $(u,v)\in E$ e $(u,v)\not\in T$ (cioè $(u,v)$ è un $\text{CROSS EDGE}$) gli *indici dei livelli* di $u$ e $v$ *differiscono al più di 1*.

>[!check] DIM. 1
>Il vertice $s$ è *visitato all'inizio*.
>Per ogni $v\in C_{s}$ esiste un *cammino* da $s$ a $v$:
>$$ s = u_{0} - u_{1} - \dots - u_{k} = v $$
>Una semplice induzione dimostra che prima o poi $\text{BFS}(G,s)$ visita $u_{i}$ $\forall0\leq i\leq k$ e quindi prima o poi $\text{BFS}(G,s)$ visita *tutti i vertici* di $C_{s}$ (per ogni vertice della componente connessa, l'algoritmo visita tutto il cammino da $s$ al vertice considerato, e quindi alla fine visita tutto $C_{s}$).
>
>Sia $(u,v)$ un *generico arco* di $C_{s}$.
>Per quanto detto sopra, prima o poi $u$ e $v$ saranno visitati da $\text{BFS}(G,s)$ e l'arco $(u,v)$ sarà quindi *etichettato* quando sarà considerato come arco incidente sul primo dei due vertici.

>[!check] DIM. 2
>Per come è progettato $\text{BFS}(G,s)$, per ogni vertice $w\in C_{s}$ con $w\ne s$, *esiste esattamente* un $\text{DISCOVERY EDGE}$ $(v,w)$ etichettato mentre si esamina $v$.
>Se definiamo $v$ *padre* di $w$ avremo che:
>- I $\text{DISCORVERY EDGE}$ definiscono *relazioni padre-figlio*.
>- $\forall w\in C_{s}$, $w\ne s$, esiste un *unico padre*.
>- $\forall w\in C_{s}$ *risalendo* di *padre in padre*, lungo $\text{DISCOVERY EDGE}$ si arriva ad $s$.
>  
>Allora i $\text{DISCOVERY EDGE}$ definiscono un *rooted tree* con radice $s$, che è uno *spanning tree* di $C_{s}$. 

>[!check] DIM. 3
>Sia $v\in L_{i}$.
>Risalendo da $v$ di *padre in padre* lungo $\text{DISCOVERY EDGE}$ si ottiene un *cammino* da $s$ a $v$ di *lunghezza* $i$.
>Supponiamo per *assurdo* che esista un cammino da $s$ a $v$ di *lunghezza minore* $t<i$. Sia esso:
>$$ s = u_{0} - u_{1} - \dots - u_{t} = v $$
>Per come è progettato $\text{BFS}(G,s)$ deve valere che:
>$$ u_{0}\in L_{0} $$
>$$ u_{1}\in L_{1} $$
>$$ u_{2}\in L_{j} \text{ per qualche j}\leq 2$$
>$$ \dots u_{n}\in L_{j} \text{ per qualche j}\leq h \dots$$
>$$ v = u_{t}\in L_{j} \text{ per qualche j}\leq t<i$$
>Ma ciò è *impossibile*, perchè abbiamo ipotizzato $v\in L_{i}$.

>[!check] DIM. 4
>Sia $(u,v)$ un $\text{CROSS EDGE}$ con $u,v\in C_{s}$.
>Siano inoltre $u\in L_{i}$ e $v\in L_{j}$.
>Assumiamo che $u$ venga *esaminato prima* di $v$ senza perdita di generalità (in caso contrario basterebbe scambiare il ruolo dei due nodi).
>Esaminando $u$ si *guarderà l'arco* $(u,v)$:
>- Se $v.\text{ID}=1$ allora $v\in L_{i}$ oppure $v\in L_{i+1}$
>- Se $v.\text{ID}=0$ allora $v$ è inserito in $L_{i+1}$
>
>Per cui $j\leq i+1$ e quindi $|j-i|\leq 1$.
### COMPLESSITA' BFS
>[!important] TEOREMA
>La complessità di $\text{BFS}(G,s)$ è
>$$ \Theta(m_{s}) $$
>dove $m_{s}$ è il *numero di archi* in $C_{s}$.

>[!important] NOTA
>Se $G=(V,E)$ è *connesso*, la complessità è $\Theta(|E|)$.

Assumiamo che il *costo di una visita* di un vertice sia $\Theta(1)$.

Per ogni $v\in C_{s}$ viene eseguita *esattamente* **una iterazione** del $\text{forall}$ esterno, il cui costo è $\Theta(1+\text{degree}(v))$.
Allora la complessità è data da:
$$
\Theta\left( \sum_{v\in C_{s}} (1+\text{degree}(v)) \right)
$$
Ma:
$$
\sum_{v\in C_{s}}(1+\text{degree}(v)) \in \Theta(m_{s} + n_{s})
$$
Dove:
- $n_{s}$ è il numero di *vertici* di $C_{s}$.
- $m_{s}\geq n_{s}-1$ è il numero di *archi* in $C_{s}$ (in un sottografo connesso).

Per cui concludiamo effettivamente che la complessità è $\Theta(m_{s})$.
>[!warning] ATTENZIONE
>Nel caso in cui la *visita* di un vertice abbia costo *non costante*, ad esempio $\Theta(t_{v})$ per il vertice $v$, la complessità di $\text{BFS}(G,s)$ diventa:
>$$ \Theta\left( m_{s}+\sum_{v\in C_{s}}t_{v} \right) $$
## VISITA DI TUTTO IL GRAFO
L'algoritmo che abbiamo visto **non** visita *tutto* il grafo: solo una sua *componente connessa* (quella contenente il nodo di partenza $s$).

Il seguente design pattern permette la *visita di tutto il grafo*, nel caso esso non sia connesso.
Si suppone di *poter enumerare i vertici* e gli *archi* (per esempio tramite iteratori delle liste $L_{V}$ ed $L_{E}$) generando il prossimo arco/vertice in tempo costante.

```pseudo
\begin{algorithm}
\caption{BFS(G)}
 \begin{algorithmic}
   \ForAll{$v\in V$}
     \State $v.$ID$\gets 0$
   \EndFor
   \ForAll{$e\in E$}
     \State $e.$label$\gets$null
   \EndFor
   \ForAll{$v\in V$}
     \If{$v.$ID = 0}
       \State BFS(G,v)
     \EndIf
   \EndFor
 \end{algorithmic}
\end{algorithm}
```
### ANALISI
Sia $G=(V,E)$ con:
- $|V|=n$
- $|E|=m$

Supponiamo che $G$ abbia $k$ *componenti connesse* ($k\geq 1$):
$$
G_{1},G_{2},\dots,G_{k}
$$
Sia $m_{i}$ il numero di archi in $G_{i}$ con $0\leq i\leq k$.
$$
\sum_{i=1}^k m_{i} = m
$$
 La complessità della *visita di tutto il grafo* è ottenuta sommando i seguenti contributi:
 - *Primi 2* cicli $\text{forall}$: $\Theta(n+m)$
 - *Terzo* ciclo:
	   - Scansione di $L_{v}$ : $\Theta(n)$
	   - Esattamente *un BFS* per *ogni* $G_{i}$ : $\Theta\left( \sum_{i} m_{i} \right)=\Theta(m)$

Per cui la *complessità totale* è data da:
$$
\Theta(n+m)
$$
>[!warning] ATTENZIONE
>Se $G$ *non è connesso* si potrebbe avere $n>m$, quindi è necessario tenere $n$ nell'ordine di grandezza.
### APPLICAZIONE: CONNETTIVITA'
Consideriamo un algoritmo con le seguenti specifiche di input e output:
- **INPUT**: Grafo $G=(V,E)$
- **OUTPUT**: *numero* delle *componenti connesse* di $G$ e vertici di ciascuna componente etichettati con *ID distinti*.

L'idea da seguire è la seguente: *visitare tutto* $G$ e, quando si visita la *i-esima* componente connessa, si impostano gli ID dei suoi vertici a $i$.

Facciamo allora uso di una *variante* di $\text{BFS}$ che, anzichè impostare gli ID a 1, li imposti a $i$: 
```pseudo
\begin{algorithm}
\caption{Connectivity(G)}
 \begin{algorithmic}
   \ForAll{$v\in V$}
     \State $v.$ID$\gets 0$
   \EndFor
   \ForAll{$e\in E$}
     \State $e.$label$\gets$null
   \EndFor
   \State $i\gets 0$
   \ForAll{$v\in V$}
     \If{$v.$ID = 0}
       \State $i\gets i+1$
       \State BFS(G,v,i)
     \EndIf
   \EndFor
   \Return i
 \end{algorithmic}
\end{algorithm}
```
### APPLICAZIONE: SPANNING TREE
- **INPUT**: Grafo $G=(V,E)$ *connesso*.
- **OUTPUT**: *Spanning tree* di $G$ (rappresentato come lista di archi).

Possiamo sfruttare il fatto che i $\text{DISCOVERY EDGE}$ *formano* uno *spanning tree* della componente connessa del vertice da cui si parte.

Possiamo usare una *variante* di $\text{BFS}$ che abbia come input anche una *lista vuota* $L=\emptyset$ in cui inserire una *copia di ogni* $\text{DISCOVERY EDGE}$ e che restituisca $L$ come output.

```pseudo
\begin{algorithm}
\caption{SpanningTree(G)}
 \begin{algorithmic}
   \State L$\gets$ lista vuota
   \State $s\gets$ vertice arbitrario di $V$
   \Return BFS(G,s,L)
 \end{algorithmic}
\end{algorithm}
```

La modifica a $\text{BFS}$ non ne altera la complessità, che, essendo il grafo connesso, è $\Theta(m)$.
### APPLICAZIONE: CAMMINI MINIMI
- **INPUT**: Grafo $G=(V,E)$ e due vertici $s,t\in V$.
- **OUTPUT**: *Cammino* di *lunghezza minima* da $s$ a $t$ in $G$ (rappresentato come lista di archi), se esiste, o $\text{null}$ se non esiste alcun cammino.

Per risolvere questo problema, modifichiamo $\text{BFS}$ come segue:
- $\forall v\in V$ usiamo *2 campi aggiuntivi*: $v.\text{parent}$ e $v.\text{edge}$.
- La *visita di un vertice* $w$ scoperto da $v$ consiste nell'*impostare* $w.\text{parent}\gets v$ e $w.\text{egde}\gets(v,w)$. Se $w=s$ entrambi i campi sono $\text{null}$.

```pseudo
\begin{algorithm}
\caption{ShortestPath(G,s,t)}
 \begin{algorithmic}
   \ForAll{$v\in V$}
     \State $v$.ID $\gets 0$
     \State $v$.parent $\gets null$
     \State $v$.edge $\gets null$
   \EndFor
   \ForAll{$e\in E$}
     \State $e$.label $\gets null$
   \EndFor
   \State \Call{BFS}{G,s}
   \If{$t.$ID = 0}
     \Return null
   \EndIf
   \State $L\gets$ lista vuota
   \State $w\gets t$
   \While{$w\ne s$}
     \State aggiungi $w.$edge a $L$
     \State $w\gets w.$parent
   \EndWhile
   \Return $L$   
 \end{algorithmic}
\end{algorithm}
```

La complessità $\Theta(n+m)$, infatti:
- *Primi 2 cicli* forall: $\Theta(n+m)$
- $\text{BFS}$ : $O(m)$
- Ciclo *while*: $O(n)$
### APPLICAZIONE: CICLICITA'
- **INPUT**: Grafo $G=(V,E)$
- **OUTPUT**: Un *ciclo* in $G$ (rappresentato come lista di archi) se esiste, o $\text{null}$ se nessun ciclo esiste.

In questo caso l'idea è la seguente: *visitare tutto* $G$ invocando $\text{BFS}$ su ciascuna componente connessa:
- Se *nessun arco* è stato etichettato come $\text{CROSS EDGE}$ ritorniamo $\text{null}$ perchè i $\text{DISCOVERY EDGE}$ *non formano cicli*.
- Se c'è un $\text{CROSS EDGE}$ c'è *sicuramente un ciclo*.
  Sia $(u,v)$ il $\text{CROSS EDGE}$ etichettato tale durante $\text{BFS}(G,s)$: se $u\in L_{i}$ allora $v\in L_{i}$ oppure $v\in L_{i+1}$:
	  - Se $v\in L_{i+1}$ *inseriamo* nel ciclo $(u,v)$ e $(v,v.\text{parent})$ e poi *risaliamo "in parellelo"* (di padre in padre, *contemporaneamente* sia da $u$ che $v$ ) fino a $v.\text{parent}$ aggiungendo al ciclo i $\text{DISCOVERY EDGE}$ incontrati.
	  - Se $v\in L_{i}$ facciamo la stessa cosa, ma *omettendo* l'aggiunta di $(v,v.\text{parent})$ al ciclo (perchè i due nodi sono già allo *stesso "livello"*).

# DEPTH FIRST SEARCH
## ALGORITMO
E' un algoritmo *ricorsivo* che, a partire da un vertice $s$:
- *Visita tutti* i vertici di $C_{s}$.
- *Etichetta* ciascun arco di $C_{s}$ come $\text{DISCOVERY}$ o $\text{BACK EDGE}$

Si usano le *stesse ipotesi* implementative di *BFS*.

Algoritmo $\text{DFS}(G,v)$:
- **INPUT**: Grafo $G=(V,E)$, $v\in V$.
- **OUTPUT**: Visita *ogni vertice raggiungibile* da $v$ e non ancora visitato, ed *etichetta* ogni arco esaminato come visto sopra.

```pseudo
\begin{algorithm}
\caption{DFS(G,v)}
 \begin{algorithmic}
   \State visita il vertice $v$ e imposta $v.$ID$\gets$1
   \ForAll{$e\in G$.incidentEdges($v$)}
     \If{$e.$label = null}
       \State $w\gets G$.opposite($v,e$)
       \If{$w$.ID = 0}
         \State $e.$label$\gets$ DISCOVERY EDGE
         \State DFS($G,w$)
       \Else
         \State $e.$label$\gets$ BACK EDGE  
       \EndIf
     \EndIf
   \EndFor
 \end{algorithmic}
\end{algorithm}
```

>[!important] NOTA
>L'etichetta **BACK EDGE** prende questo nome perchè, se osserviamo l'*ordine* in cui vengono *visitati i vertici*, questi formano un cammino che *si allunga ad ogni chiamata* ed i $\text{BACK EDGE}$ rappresentano dei percorsi che *tornano* indietro *verso vertici già visitati*.
### CORRETTEZZA E PROPRIETA'
>[!important] PROPOSIZIONE
>Si supponga di eseguire $\text{DFS}(G,s)$ e che, all'inizio, *nessuno* dei *vertici/archi* di $C_{s}$ sia *visitato/etichettato*.
>A fine esecuzione si ha che:
>1. *Tutti* i vertici di $C_{s}$ sono *visitati* e *tutti gli archi* di $C_{s}$ sono *etichettati* come $\text{DISCOVERY}$ o $\text{BACK EDGE}$.
>2. I $\text{DISCOVERY EDGE}$ formano uno *spanning tree* $T$ di $C_{s}$ radicato in $s$.

La dimostrazione è simile a quella fatta per $\text{BFS}$.
### COMPLESSITA'
>[!important] TEOREMA
>Si supponga di eseguire $\text{DFS}(G,s)$ e che, all'inizio, *nessuno* dei *vertici/archi* di $C_{s}$ sia *visitato/etichettato*.
>La **complessità** di $\text{DFS}(G,s)$ è
>$$ \Theta(m_{s}) $$
>dove $m_{s}$ è il *numero di archi* in $C_{s}$.

>[!important] NOTA
>Se $G=(V,E)$ è *connesso*, la complessità è $\Theta(|E|)$.

Dimostriamo il teorema.
L'*albero della ricorsione* associato a $\text{DFS}(G,s)$ è tale che:
- Contiene *esattamente 1 nodo* per *ogni vertice* di $C_{s}$.
- Il *costo* contribuito da un *vertice* $v\in C_{s}$ è $\Theta(\text{degree}(v))$ (assumendo che le visite dei vertici richiedano $\Theta(1)$ operazioni).

Per cui la complessità è:
$$
\Theta\left( \sum_{v\in C_{s}}\text{degree}(v) \right) = \Theta(m_{s}) 
$$
>[!warning] ATTENZIONE
>Come visto anche per $\text{BFS}$, se la *visita di un vertice* avesse un *costo* $\Theta(t_{v})$ (non costante), alla complessità si aggiunge un *termine additivo* $\Theta\left( \sum_{v}t_{v} \right)$.
# CONFRONTO BFS E DFS
>[!important] NOTA
>Su *grafi* **non diretti**, $\text{DFS}$ e $\text{BFS}$ hanno la *stessa complessità* e possono essere *usati equivalentemente* per: connettività, spanning tree, rilevamento di cicli e reachability.

>[!warning] DISTINZIONI
>Ciascuna visita ha poi degli *aspetti distintivi* che la rendono preferibile all'altra:
>- **BFS**: usata per il calcolo dei *cammini minimi*.
>- **DFS**:
> 	 - Più *space-efficient* perchè richiede solo spazio *proporzionale all'altezza* dell'*albero della ricorsione* (in aggiunta a quello per l'input).
> 	 - Può risultare efficiente per trovare *un cammino* da $s$ a $t$, se $t$ è *molto lontano* da $s$ in $G$, senza necessariamente dover visitare tanti vertici.
> 	 - Viene usato per *trovare cicli* nei grafi **diretti** (vedremo più avanti).
# CAMMINI MINIMI SU GRAFI PESATI
Sia $G=(V,E,w)$ un *grafo non diretto* e **pesato** dove:
$$
w:E\to \mathbb{R}
$$
è una funzione che associa un *peso* reale a ciascun *arco* $e\in E$. 
Può essere rappresentato con un campo $e.\text{weight}$.
Sottolineiamo che, per grafi pesati, la *lunghezza di un cammino* è la **somma dei pesi** degli archi del cammino.

>[!important] PROPOSIZIONE
>Se $u_{1},u_{2},\dots,u_{k}$ è un *cammino minimo* da $u_{1}$ a $u_{k}$, allora:
>$$ u_{i},u_{i+1},\dots,u_{j} $$
>è un **cammino minimo** da $u_{i}$ a $u_{j}$ per ogni $1\leq i<j\leq k$.

>[!check] DIM.
>Per assurdo, se esistesse un cammino da $u_{i}$ a $u_{j}$ di lunghezza $<w_{ij}$, sostituendo al segmento $u_{i},u_{i+1}\dots u_{j}$ nel cammino da $u_{1}$ a $u_{k}$ si otterrebbe un cammino da $u_{1}$ a $u_{k}$ di *lunghezza minore*, che è *impossibile* dato che abbiamo assunto che quello dato fosse un *cammino minimo*.

Ci chiediamo a questo punto come possiamo **calcolare** *distanze* e *cammini minimi* in un *grafo pesato*.
>[!def] DEFINIZIONE DEL PROBLEMA
>Dato un *grafo pesato* $G=(V,E,w)$, *non diretto*, ed un vertice $s \in V$, il problema dei **Single-Source Shortest Paths (SSSP)** richiede di determinare *tutte le distanze* tra $s$ e gli *altri vertici* di $V$ ed i relativi *cammini minimi*, opportunamente rappresentati.
>

>[!important] IPOTESI
>Assumiamo che $G$ **non** *contenga cicli* di *peso negativo*.
>Altrimenti, per coppie di vertici nella stessa componente connessa del ciclo esisterebbero cammini di lunghezza negativa *arbitrariamente piccola*.

Osservazioni:
1. Per i vertici *non appartenenti* alla *componente connessa* di $s$, la distanza da $s$ sarà $+\infty$ ed il relativo cammino minimo sarà vuoto.
2. Se $w(e)=1$ per ogni $e\in E$, la $\text{BFS}$ risolve il problema dei **SSSP** in tempo $\Theta(|V|+|E|)$.
# ALGORITMO DI DIJKSTRA
E' un algoritmo sviluppato nel 1956 da E. Dijkstra.
- Richiede che i *pesi* siano *non negativi*.
- *Generalizza* lo schema della *BFS* al caso pesato.
  A partire da un *vertice sorgente* $s \in V$:
	  1. Esegue una serie di iterazioni che fanno crescere progressivamente la **cloud** di vertici le cui distanze da $s$ ed i relativi cammini minimi sono stati identificati.
	  2. La *cloud* è *inizializzata* con $\{ s \}$.
	  3. Per ogni $v\in V$ mantiene la *distanza corrente* di $v$ da $s$, relativa a *cammini interni alla cloud* (tranne, eventualmente, $v$).
	  4. In ciascuna iterazione vienne *aggiunto* alla *cloud* il vertice *esterno ad essa più vicino a* $s$.
	  5. I vertici *esterni alla cloud* sono mantenuti in un *Priority Queue* $Q$ in base alla loro *distanza da* $s$.
## DETTAGLI IMPLEMENTATIVI
Per ogni *vertice* $v\in V$ usiamo i seguenti campi:
- $v.\text{D}$ memorizza la *distanza corrente* da $s$.
- $v.\text{parent}$ memorizza il *predecessore* di $v$ nel *cammino minimo corrente* da $s$ a $v$.

Per la *Priority Queue* $Q$ assumiamo che:
- Un vertice $v\in V$ è rappresentato da una *entry* $(v.\text{D},v)$ con *chiave* $v.\text{D}$.
- E' disponibile un *metodo* $Q.\text{decreaseKey}(v.\text{D},v)$ che, se $Q$ contiene una entry $(x,v)$ per il vertice $v$, con $x>v.\text{D}$, sostituisce la chiave di tale entry con $v.\text{D}$.
## ALGORITMO
Algoritmo **ShortestPaths(G,s)**:
- **INPUT**: grafo *non diretto* $G=(V,E,w)$ con *pesi non negativi* sugli archi, e sorgente $s \in V$.
- **OUTPUT**: *distanze* e *cammini minimi* da $s$ per ogni $v\in V$ rappresentati dai campi $v.\text{D}$ e $v.\text{parent}$.

```pseudo
\begin{algorithm}
\caption{ShortestPath(G,s)}
 \begin{algorithmic}
   \State s.D $\gets$ 0
   \State s.parent $\gets$ null
   \ForAll{$v\in V\setminus\{s\}$}
     \State v.D $\gets +\infty$
     \State v.parent $\gets$ null
   \EndFor
   \State Q $\gets$ Priority Queue contenente una entry (v.D,v) per ogni $v\in V$
   \While{!Q.isEmpty()}
     \State (u.D,u) $\gets$ Q.removeMin()
     \ForAll{(u,v)$\in$ G.incidentEdges(u)}
       \If{u.D+w(u,v)<v.D} \Comment{Edge relaxation}
         \State v.D $\gets$ u.D + w(u,v)
         \State v.parent $\gets$ u
         \State Q.decreaseKey(v.D,v)
       \EndIf
     \EndFor
   \EndWhile
 \end{algorithmic}
\end{algorithm}
```
### ESEMPIO ESECUZIONE
Consideriamo il seguente grafo e osserviamo l'esecuzione dell'algoritmo a partire dal nodo *A*.
Evidenziamo in *rosso* i vertici che vengono *estratti dalla PQ* (e quindi *aggiunti alla cloud*) ed in *verde* i vertici che vengono *esaminati* in ciascuna iterazione (appartenenti ad *archi incidenti sul vertice estratto*):

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A)[red] at (3,3) {A};
    \node (B)[green] at (0,4.5) {B};
    \node (C)[green] at (3,6) {C};
    \node (D)[green] at (6,5.3) {D};
    \node (E)[green] at (0.5,0.6) {E};
    \node (F)[green] at (3,0) {F};
    \node (G) at (5.3,1) {G};
    \node (H)[green] at (7,3) {H};
    \node (I) at (9,2.7) {I};
    \node (L) at (8,7) {L};
\end{scope}

\begin{scope}[>={Stealth[black]},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path  (A) edge node {$2$} (B);
    \path  (A) edge node {$5$} (C);
    \path  (A) edge node {$9$} (D);
    \path  (A) edge node {$2$} (F);
    \path  (A) edge node {$7$} (E);
    \path  (A) edge node {$5$} (H);
    \path  (C) edge node {$2$} (B); 
    \path  (C) edge node {$3$} (D);
    \path  (D) edge node {$8$} (L);
    \path  (F) edge node {$1$} (G);
    \path  (H) edge node {$1$} (G);
    \path  (H) edge node {$3$} (I);
    \path  (H) edge node {$4$} (L);   
\end{scope}
\end{tikzpicture}

\end{document}
```

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A)[red] at (3,3) {A};
    \node (B)[red] at (0,4.5) {B};
    \node (C)[green] at (3,6) {C};
    \node (D)[green] at (6,5.3) {D};
    \node (E)[green] at (0.5,0.6) {E};
    \node (F)[green] at (3,0) {F};
    \node (G) at (5.3,1) {G};
    \node (H)[green] at (7,3) {H};
    \node (I) at (9,2.7) {I};
    \node (L) at (8,7) {L};
\end{scope}

\begin{scope}[>={Stealth[black]},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path  (A) edge[red] node {$2$} (B);
    \path  (A) edge node {$5$} (C);
    \path  (A) edge node {$9$} (D);
    \path  (A) edge node {$2$} (F);
    \path  (A) edge node {$7$} (E);
    \path  (A) edge node {$5$} (H);
    \path  (C) edge node {$2$} (B); 
    \path  (C) edge node {$3$} (D);
    \path  (D) edge node {$8$} (L);
    \path  (F) edge node {$1$} (G);
    \path  (H) edge node {$1$} (G);
    \path  (H) edge node {$3$} (I);
    \path  (H) edge node {$4$} (L);   
\end{scope}
\end{tikzpicture}

\end{document}
```

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A)[red] at (3,3) {A};
    \node (B)[red] at (0,4.5) {B};
    \node (C)[green] at (3,6) {C};
    \node (D)[green] at (6,5.3) {D};
    \node (E)[green] at (0.5,0.6) {E};
    \node (F)[red] at (3,0) {F};
    \node (G)[green] at (5.3,1) {G};
    \node (H)[green] at (7,3) {H};
    \node (I) at (9,2.7) {I};
    \node (L) at (8,7) {L};
\end{scope}

\begin{scope}[>={Stealth[black]},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path  (A) edge[red] node {$2$} (B);
    \path  (A) edge node {$5$} (C);
    \path  (A) edge node {$9$} (D);
    \path  (A) edge[red] node {$2$} (F);
    \path  (A) edge node {$7$} (E);
    \path  (A) edge node {$5$} (H);
    \path  (C) edge node {$2$} (B); 
    \path  (C) edge node {$3$} (D);
    \path  (D) edge node {$8$} (L);
    \path  (F) edge node {$1$} (G);
    \path  (H) edge node {$1$} (G);
    \path  (H) edge node {$3$} (I);
    \path  (H) edge node {$4$} (L);   
\end{scope}
\end{tikzpicture}

\end{document}
```

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A)[red] at (3,3) {A};
    \node (B)[red] at (0,4.5) {B};
    \node (C)[green] at (3,6) {C};
    \node (D)[green] at (6,5.3) {D};
    \node (E)[green] at (0.5,0.6) {E};
    \node (F)[red] at (3,0) {F};
    \node (G)[red] at (5.3,1) {G};
    \node (H)[green] at (7,3) {H};
    \node (I) at (9,2.7) {I};
    \node (L) at (8,7) {L};
\end{scope}

\begin{scope}[>={Stealth[black]},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path  (A) edge[red] node {$2$} (B);
    \path  (A) edge node {$5$} (C);
    \path  (A) edge node {$9$} (D);
    \path  (A) edge[red] node {$2$} (F);
    \path  (A) edge node {$7$} (E);
    \path  (A) edge node {$5$} (H);
    \path  (C) edge node {$2$} (B); 
    \path  (C) edge node {$3$} (D);
    \path  (D) edge node {$8$} (L);
    \path  (F) edge[red] node {$1$} (G);
    \path  (H) edge node {$1$} (G);
    \path  (H) edge node {$3$} (I);
    \path  (H) edge node {$4$} (L);   
\end{scope}
\end{tikzpicture}

\end{document}
```

A questo punto sia *H* che *C* sono a *distanza 4*, scegliamo *C* perchè assumiamo che questo sia l'ordine in cui si trovano nella *PQ*.

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A)[red] at (3,3) {A};
    \node (B)[red] at (0,4.5) {B};
    \node (C)[red] at (3,6) {C};
    \node (D)[green] at (6,5.3) {D};
    \node (E)[green] at (0.5,0.6) {E};
    \node (F)[red] at (3,0) {F};
    \node (G)[red] at (5.3,1) {G};
    \node (H)[green] at (7,3) {H};
    \node (I) at (9,2.7) {I};
    \node (L) at (8,7) {L};
\end{scope}

\begin{scope}[>={Stealth[black]},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path  (A) edge[red] node {$2$} (B);
    \path  (A) edge node {$5$} (C);
    \path  (A) edge node {$9$} (D);
    \path  (A) edge[red] node {$2$} (F);
    \path  (A) edge node {$7$} (E);
    \path  (A) edge node {$5$} (H);
    \path  (C) edge[red] node {$2$} (B); 
    \path  (C) edge node {$3$} (D);
    \path  (D) edge node {$8$} (L);
    \path  (F) edge[red] node {$1$} (G);
    \path  (H) edge node {$1$} (G);
    \path  (H) edge node {$3$} (I);
    \path  (H) edge node {$4$} (L);   
\end{scope}
\end{tikzpicture}

\end{document}
```

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A)[red] at (3,3) {A};
    \node (B)[red] at (0,4.5) {B};
    \node (C)[red] at (3,6) {C};
    \node (D)[green] at (6,5.3) {D};
    \node (E)[green] at (0.5,0.6) {E};
    \node (F)[red] at (3,0) {F};
    \node (G)[red] at (5.3,1) {G};
    \node (H)[red] at (7,3) {H};
    \node (I)[green] at (9,2.7) {I};
    \node (L)[green] at (8,7) {L};
\end{scope}

\begin{scope}[>={Stealth[black]},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path  (A) edge[red] node {$2$} (B);
    \path  (A) edge node {$5$} (C);
    \path  (A) edge node {$9$} (D);
    \path  (A) edge[red] node {$2$} (F);
    \path  (A) edge node {$7$} (E);
    \path  (A) edge node {$5$} (H);
    \path  (C) edge[red] node {$2$} (B); 
    \path  (C) edge node {$3$} (D);
    \path  (D) edge node {$8$} (L);
    \path  (F) edge[red] node {$1$} (G);
    \path  (H) edge[red] node {$1$} (G);
    \path  (H) edge node {$3$} (I);
    \path  (H) edge node {$4$} (L);   
\end{scope}
\end{tikzpicture}

\end{document}
```

Ora *D*, *E* e *I* sono alla *stessa distanza* (*7*), anche in questo caso scegliamo prima *D*.

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A)[red] at (3,3) {A};
    \node (B)[red] at (0,4.5) {B};
    \node (C)[red] at (3,6) {C};
    \node (D)[red] at (6,5.3) {D};
    \node (E)[green] at (0.5,0.6) {E};
    \node (F)[red] at (3,0) {F};
    \node (G)[red] at (5.3,1) {G};
    \node (H)[red] at (7,3) {H};
    \node (I)[green] at (9,2.7) {I};
    \node (L)[green] at (8,7) {L};
\end{scope}

\begin{scope}[>={Stealth[black]},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path  (A) edge[red] node {$2$} (B);
    \path  (A) edge node {$5$} (C);
    \path  (A) edge node {$9$} (D);
    \path  (A) edge[red] node {$2$} (F);
    \path  (A) edge node {$7$} (E);
    \path  (A) edge node {$5$} (H);
    \path  (C) edge[red] node {$2$} (B); 
    \path  (C) edge[red] node {$3$} (D);
    \path  (D) edge node {$8$} (L);
    \path  (F) edge[red] node {$1$} (G);
    \path  (H) edge[red] node {$1$} (G);
    \path  (H) edge node {$3$} (I);
    \path  (H) edge node {$4$} (L);   
\end{scope}
\end{tikzpicture}

\end{document}
```

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A)[red] at (3,3) {A};
    \node (B)[red] at (0,4.5) {B};
    \node (C)[red] at (3,6) {C};
    \node (D)[red] at (6,5.3) {D};
    \node (E)[red] at (0.5,0.6) {E};
    \node (F)[red] at (3,0) {F};
    \node (G)[red] at (5.3,1) {G};
    \node (H)[red] at (7,3) {H};
    \node (I)[green] at (9,2.7) {I};
    \node (L)[green] at (8,7) {L};
\end{scope}

\begin{scope}[>={Stealth[black]},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path  (A) edge[red] node {$2$} (B);
    \path  (A) edge node {$5$} (C);
    \path  (A) edge node {$9$} (D);
    \path  (A) edge[red] node {$2$} (F);
    \path  (A) edge[red] node {$7$} (E);
    \path  (A) edge node {$5$} (H);
    \path  (C) edge[red] node {$2$} (B); 
    \path  (C) edge[red] node {$3$} (D);
    \path  (D) edge node {$8$} (L);
    \path  (F) edge[red] node {$1$} (G);
    \path  (H) edge[red] node {$1$} (G);
    \path  (H) edge node {$3$} (I);
    \path  (H) edge node {$4$} (L);   
\end{scope}
\end{tikzpicture}

\end{document}
```

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A)[red] at (3,3) {A};
    \node (B)[red] at (0,4.5) {B};
    \node (C)[red] at (3,6) {C};
    \node (D)[red] at (6,5.3) {D};
    \node (E)[red] at (0.5,0.6) {E};
    \node (F)[red] at (3,0) {F};
    \node (G)[red] at (5.3,1) {G};
    \node (H)[red] at (7,3) {H};
    \node (I)[red] at (9,2.7) {I};
    \node (L)[green] at (8,7) {L};
\end{scope}

\begin{scope}[>={Stealth[black]},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path  (A) edge[red] node {$2$} (B);
    \path  (A) edge node {$5$} (C);
    \path  (A) edge node {$9$} (D);
    \path  (A) edge[red] node {$2$} (F);
    \path  (A) edge[red] node {$7$} (E);
    \path  (A) edge node {$5$} (H);
    \path  (C) edge[red] node {$2$} (B); 
    \path  (C) edge[red] node {$3$} (D);
    \path  (D) edge node {$8$} (L);
    \path  (F) edge[red] node {$1$} (G);
    \path  (H) edge[red] node {$1$} (G);
    \path  (H) edge[red] node {$3$} (I);
    \path  (H) edge node {$4$} (L);   
\end{scope}
\end{tikzpicture}

\end{document}
```

E infine:
```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A)[red] at (3,3) {A};
    \node (B)[red] at (0,4.5) {B};
    \node (C)[red] at (3,6) {C};
    \node (D)[red] at (6,5.3) {D};
    \node (E)[red] at (0.5,0.6) {E};
    \node (F)[red] at (3,0) {F};
    \node (G)[red] at (5.3,1) {G};
    \node (H)[red] at (7,3) {H};
    \node (I)[red] at (9,2.7) {I};
    \node (L)[red] at (8,7) {L};
\end{scope}

\begin{scope}[>={Stealth[black]},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path  (A) edge[red] node {$2$} (B);
    \path  (A) edge node {$5$} (C);
    \path  (A) edge node {$9$} (D);
    \path  (A) edge[red] node {$2$} (F);
    \path  (A) edge[red] node {$7$} (E);
    \path  (A) edge node {$5$} (H);
    \path  (C) edge[red] node {$2$} (B); 
    \path  (C) edge[red] node {$3$} (D);
    \path  (D) edge node {$8$} (L);
    \path  (F) edge[red] node {$1$} (G);
    \path  (H) edge[red] node {$1$} (G);
    \path  (H) edge[red] node {$3$} (I);
    \path  (H) edge[red] node {$4$} (L);   
\end{scope}
\end{tikzpicture}

\end{document}
```

A questo punto vale la pena osservare il grafo dal seguente punto di vista:

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A) at (0,0) {A};
    \node (B) at (-3,-3) {B};
    \node (C) at (-3,-6) {C};
    \node (D) at (-3,-9) {D};
    \node (E) at (3,-3) {E};
    \node (F) at (0,-3) {F};
    \node (G) at (0,-6) {G};
    \node (H) at (0,-9) {H};
    \node (I) at (-1,-11) {I};
    \node (L) at (1,-11) {L};
\end{scope}

\begin{scope}[>={Stealth[black]},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path  (A) edge[red] node {$2$} (B);
    \path  (A) edge[bend right=65] node {$5$} (C);
    \path  (A) edge[bend right=85] node {$9$} (D);
    \path  (A) edge[red] node {$2$} (F);
    \path  (A) edge[red] node {$7$} (E);
    \path  (A) edge[bend left=20] node {$5$} (H);
    \path  (C) edge[red] node {$2$} (B); 
    \path  (C) edge[red] node {$3$} (D);
    \path  (D) edge[bend right=80] node {$8$} (L);
    \path  (F) edge[red] node {$1$} (G);
    \path  (H) edge[red] node {$1$} (G);
    \path  (H) edge[red] node {$3$} (I);
    \path  (H) edge[red] node {$4$} (L);   
\end{scope}
\end{tikzpicture}

\end{document}
```

In questo modo mettiamo in evidenza i *cammini minimi* da A verso ogni vertice, nell'*ordine in cui sono scoperti*.
In *bianco* restano gli archi che rappresentano *cammini più lunghi*.
## CORRETTEZZA
Sia $G=(V,E,w)$ un grafo non diretto con *pesi non negativi* sugli archi e sia $s \in V$ un vertice arbitrario.
>[!important] LEMMA 1
>Alla fine di *ciascuna iterazione* del ciclo $\text{while}$ di $\text{ShortestPaths}(G,s)$ vale il seguente *invariante*: per ogni $v\in V$
>- Se $v.\text{D}=+\infty$ allora $v.\text{parent=null}$.
>- Se $v.\text{D}<+\infty$, il *cammino* ottenuto *risalendo* da $v$ di *parent in parent* è un cammino da $s$ a $v$ di lunghezza $v.\text{D}$.

>[!check] DIM.
>All'*inizio* del while l'invariante è reso vero dalle inizializzazioni precedenti:
>- $s.\text{D}=0$ e infatti $s$ *non ha parent* (è il primo ad essere scelto).
>- Tutti gli *altri* vertici *non sono ancora stati visitati*, per cui si ha $v.\text{parent=null}$.
>
>Assumiamo ora che l'invariante sia vero alla fine della generica iterazione $i$ e dimostriamo che vale anche per l'iterazione $i+1$:
>viene *estratto* un vertice $u$dalla *PQ* e, per ipotesi induttiva (siccome $u.\text{D}<+\infty$), sappiamo che esiste un *cammino di lunghezza* $u.\text{D}$ da $s$ a $u$ definito dai campi $\text{parent}$.
>A questo punto si esaminano tutti i *vicini di* $u$ e, per ognuno di essi, si possono verificare due casi:
>1. **Non** avviene l'*edge relaxation*: i campi $v.\text{D}$ e $v.\text{parent}$ *non vengono modificati*, e per questi vertici l'invariante *rimane valido* (lo era nell'iterazione precedente per ipotesi).
>2. **Avviene** *l'edge relaxation*: i due campi vengono *aggiornati*.
>   Siccome $u$ è stato estratto dalla coda, si ha $u.\text{D}<+\infty$ e quindi anche $v.\text{D}<+\infty$ (secondo caso del lemma).
>   Per ipotesi induttiva, esisteva un cammino da $s$ a $u$ di lunghezza $u.\text{D}$: aggiungendo ora anche l'arco $(u,v)$ otteniamo un *cammino* da $s$ a $v$ di lunghezza $u.\text{D}+w(u,v)$. Ma questa è proprio la *lunghezza a cui abbiamo impostato il campo* $v.\text{D}$, quindi l'invariante è valido.
>
>Per tutti gli altri vertici (quelli *non ancora visitati*), invece, l'invariante *continua a valere* data l'inizializzazione prima del while.
>
>Concludiamo quindi per induzione che l'invariante è valido *anche alla fine del ciclo* $\square$.

>[!important] LEMMA 2
>Durante l'esecuzione di $\text{ShortestPaths}(G,s)$, quando un vertice $u$ è estratto da $Q$, il campo $u.\text{D}$ è uguale alla *distanza* $d(s,u)$ di $u$ da $s$.

>[!check] DIM.
>Per dimostrarlo, utilizziamo il *lemma precedente*.
>Supponiamo *per assurdo* che il lemma 2 non sia vero: chiamiamo allora $z$ il *primo vertice estratto* dalla coda *tale che* 
>$$ z.\text{D} \ne d(s,z) $$
>Sicuramente $z\ne s$ perchè $s$ è il primo vertice estratto e la sua distanza da sè stesso è correttamente impostata a $0$.
>Inoltre deve essere $z.\text{D}>d(s,z)$ siccome il lemma 1 assicura che *esiste* un *cammino da s a z di lunghezza* $z.\text{D}$ (e la loro distanza sarà sicuramente minore di tale quantità).
>Sia ora $P$ un *cammino minimo* da $s$ a $z$:
>$$ P = s\dots z $$
>1. **CASO 1**: *ogni vertice* $v\ne z$ in $P$ viene *estratto* da $Q$ *prima di z*.
>   Sia $x$ il predecessore di $z$ in $P$. Allora quando $x$ viene estratto da $Q$ si ha $x.\text{D}=d(s,x)$ e, dopo la *edge relaxation* eseguita sull'*arco (x,z)* deve valere:
>   $$ z.\text{D} \leq x.\text{D} + w(x,z) = d(s,z) + w(x,z) = d(s,z) $$
>   Ma questo *contraddirebbe l'ipotesi* che $z.\text{D}>d(s,z)$.
>2. **CASO 2**: Sia $y$ il *primo vertice* di $P$ che è *ancora in Q quando z viene estratto* da $Q$. Sia inoltre $x$ il suo predecessore in $P$.
>   Chiaramente deve essere $y\ne s$.
>   $$ P = s\dots x-y\dots z $$
>   Quindi $x$ è *estratto* da $Q$ *prima di z* e, data la scelta di $z$, quando $x$ è estratto si ha che $x.\text{D}=d(s,x)$.
>   Inoltre, dopo la *edge relaxation su (x,y)* deve valere anche:
>   $$ y.\text{D} \leq x.\text{D} + w(x,y) = d(s,x) + w(x,y) = d(s,y) $$
>   (Il cammino da $s$ a $y$ è minimo perchè *P è minimo*)
>   Ora, dato che i *pesi* degli archi sono tutti *non negativi*, deve essere
>   $$ d(s,y) \leq d(s,z) $$
>   Quindi, a questo punto sappiamo che *quando z viene estratto* da $Q$:
> 	  - $z.\text{D}>d(s,z)$
> 	  - $y$ è in $Q$ con $y.\text{D}\leq d(s,y)\leq d(s,z)$
>  
> 	 Ma questo è *impossibile*, perchè in tal caso la entry $(z.\text{D},z)$ *non sarebbe quella ad essere estratta* (cioè quella con chiave minima). $\square$

>[!important] TEOREMA
>L'algoritmo $\text{ShortestPaths}(G,s)$ è **corretto**.

>[!check] DIM.
>Per costruzione, *tutti i vertici* di $V$ sono *inseriti* in $Q$ e, prima o poi, ogni vertice è *anche estratto*.
>
>Quando un vertice $v\in V$ è *estratto* da $Q$ si ha che:
>- $v.\text{D}$ è la lunghezza del cammino minimo da $s$ a $v$ (dal Lemma 2).
>- Se $v.\text{D}<+\infty$, risalendo di *parent in parent* si ottiene un *cammino* da $s$ a $v$ di *lunghezza* $v.\text{D}$ (dal Lemma 1). Questo è *quindi* il *cammino minimo*.
>- Se $v.\text{D}=+\infty$, $v.\text{parent=null}$ (dal Lemma 1) ed è *corretto* dato che *non esiste* un cammino da $s$ a $v$. $\square$
## COMPLESSITA'
La complessità di $\text{ShortestPaths}$ *dipende dall'implementazione* scelta per la *Priority Queue* $Q$.

Consideriamo due possibili implementazioni: **heap** e **doubly-linked list** *non ordinata*.
>[!important] LEMMA
>Se $G=(V,E,w)$ ha $n$ vertici, i metodi invocati da $\text{ShortestPaths}(G,s)$ su $Q$ possono essere implementati con le seguenti complessità:
>$$ \begin{matrix}
\text{OPERAZIONE} & \text{ HEAP } & \text{ D-L LIST } \\
(A)\text{ Costruzione iniziale } & \Theta(n) & \Theta(n) \\
(B)\text{ Q.removeMin() } & \Theta(\log n) & \Theta(n) \\
(C)\text{ Q.decreaseKey}(v.\text{D},v) & \Theta(\log n) & \Theta(1)
\end{matrix} $$

Assumiamo che valgano le seguenti *ipotesi implementative*:
- L'oggetto che rappresenta un vertice $v\in V$ ha un *link alla entry* $(v.\text{D},v)$ in $Q$ (ed il link è $\text{null}$ se la entry è stata già estratta da $Q$).
- La entry $(v.\text{D},v)$ ha come valore un *riferimento all'oggetto* che rappresenta $v$.

La complessità delle *operazioni* **A,B** è determinata da quanto già visto sulle priority queue.
Per quanto riguarda **C**, invece: 
- Con le ipotesi implementative fatte, possiamo accedere alla entry $(v.\text{D},v)$ da $v$ in tempo $\Theta(1)$.
	  - Se $Q$ è implementata con un *heap*, dopo aver aggiornato $v.\text{D}$ si esegue un *up-heap bubbling* dal nodo associato alla entry. La complessità è quindi $\Theta(\log|Q|)$ e quindi $\Theta(\log n)$ dato che in ogni momento $|Q|\leq n$.
	  - Se $Q$ è implementata con una *lista non ordinata*, possiamo semplicemente aggiornare il valore $v.\text{D}$ in tempo $\Theta(1)$.

>[!important] TEOREMA
>Sia $G=(V,E,w)$ un grafo non diretto con $n$ vertici ed $m$ archi e con pesi non negativi sugli archi.
>Per ogni $s \in V$ la *complessità* di $\text{ShortestPaths}(G,s)$ è
>$$ \Theta(\min\{ n^2, (n+m)\log n \} ) $$

>[!check] DIM.
>Osserviamo che:
>-  *Ogni arco* $(u,v)$ è coinvolto in *esattamente due edge relaxation*.
>- Il *costo* di una *edge relaxation* è *al più* pari al costo di una $\text{decreaseKey}$ in $Q$.
>
>Pertanto la complessità di $\text{ShortestPaths}$ è *dominata* dalle seguenti operazioni:
>1. **Inizializzazione** di $Q$
> 	  - Costo $\Theta(n)$ con *heap*.
> 	  - Costo $\Theta(n)$ con *lista non ordinata*.
>2. **Ciclo while** (in totale)
> 	  - $n$ $\text{removeMin}()$
> 		- $\Theta(n\log n)$ con *heap*.
> 		- $\Theta(n^2)$ con *lista non ordinata*.
> 	  - $2m$ edge relaxation
> 		  - $\Theta(m\log n)$ con *heap*.
> 		  - $\Theta(1)$ con *lista non ordinata*.
>
>Quindi la **complessità finale** è:
>- **Heap**: $\Theta((n+m)\log n)$
>- **Lista non ordinata**: $\Theta(n^2)$

>[!warning] ATTENZIONE
>Indichiamo *entrambe* le complessità perchè una o l'altra scelta implementativa potrebbe risultare *più efficiente in base al grafo*:
>- Se $m\in O\left( \frac{n^2}{\log n} \right)$ conviene scegliere l'**heap**.
>- Se $m\in\Omega\left( \frac{n^2}{\log n} \right)$ conviene scegliere la **lista non ordinata**.
>
>Inoltre, esiste un'altra implementazione degli heap, detta **Fibonacci Heap** che può essere impiegata per ottenere una complessità $\in\Theta(m+n\log n)$.
# GRAFI SEMPLICI DIRETTI
La differenza con i grafi non diretti è data dal fatto che, in un *grafo diretto* $G=(V,E)$ ogni arco $e\in(u,v)\in E$ è una **coppia ordinata** di vertici.

>[!important] TERMINOLOGIA
>Dato un vertice $v\in V$:
>- Gli archi $(v,u)$ si dicono **uscenti** da $v$.
>- Gli archi $(u,v)$ si dicono **entranti** in $v$.
>- Il **grado uscente** di $v$ (indicato con $\text{outdegree}(v)$) è il *numero di archi uscenti* da $v$.
>- Il **grado entrante** di $v$ (indicato con $\text{indegree}(v)$) è il *numero di archi entranti* da $v$.

Chiaramente, vale:
$$
\sum_{v\in V} \text{outdegree}(v) = \sum_{v\in V} \text{indegree}(v) = |E|
$$
Dato che ogni arco contribuisce *esattamente 1 volta* all'outdegree di un vertice, e 1 all'indegree di un altro vertice.
## CAMMINI, CICLI E DISTANZE
Le definizioni di *cammino* e *ciclo* sono analoghe a quelle viste per i grafi non diretti, ma seguono la **direzionalità** degli archi.
La definizione di *lunghezza* rimane la *stessa*.

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A) at (-1.5,-1.5) {u};
    \node (B) at (0,0) {v};
    \node (C) at (0,-3) {w};
    \node (D) at (1.5,-1.5) {x};
    \node (E) at (3.3,-1.5) {z};
    \node (F) at (1.5,-4.5) {y};
\end{scope}

\begin{scope}[>={Stealth},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path [->] (B) edge (A);
    \path [->] (B) edge[blue] (D);
    \path [->] (A) edge[red] (C);
    \path [->] (C) edge[red] (D);
    \path [->] (D) edge[blue] (E);
    \path [->] (D) edge[red] (F);
    \path [->] (F) edge[red] (C);  
    \path [->] (C) edge[red] (B);
\end{scope}
\end{tikzpicture}

\end{document}
```

In figura vediamo:
- In *rosso* un *cammino non semplice*.
- In *blu* un *cammino semplice*.
## VERTICI RAGGIUNGIBILI E CONNESSIONE
>[!def] DEF
>Dato $v\in V$, l'insieme dei *vertici raggiungibili* da $v$ (indicato con $\text{reachable}(v)$) consiste di tutti i vertici $u\in V$ tali che esiste un *cammino diretto* da $v$ a $u$.

Nel grafo dell'esempio precedente:
- $\text{reachable}(x)=\{ y,z,w,v,u \}$
- $\text{reachable}(z)=\emptyset$

>[!def] DEF
>Dato un grafo *diretto* $G=(V,E)$, la sua *versione non diretta* $G^U$ si ottiene *ignorando la direzione* di ogni arco $(u,v)\in E$.

>[!def] CONNESSIONE (per grafi diretti)
>Dato un grafo diretto $G=(V,E)$:
>- $G$ si dice **fortemente connesso** (*strongly connected*) se per ogni coppia ordinata $u,v\in V$ esiste un cammino diretto da $u$ a $v$ in $G$.
>- $G$ si dice **debolmente connesso** (*weakly connected*) se $G^U$ è connesso.
## LISTE DI ADIACENZA
Come anche per i grafi non diretti, è spesso conveniente utilizzare le **liste di adiacenza** per rappresentare gli archi di un grafo diretto: per *ogni vertice* $v\in V$ si ha una *lista* $\text{out}(v)$ di *puntatori agli archi* $(v,u)\in E$ **uscenti** da $v$.
# VISITE SU GRAFI DIRETTI
**BFS** e **DFS** si applicano anche a *grafi diretti*, con alcune piccole modifiche:
- Ora $\text{incidentEdege}(v)$ restituisce un iteratore ai *soli archi uscenti* da $v$.
- Nella **BFS** un arco $e=(u,v)$ può essere etichettato come $\text{DISCOVERY EDGE}$ o $\text{ALTRO}$.
- Nella **DFS** un arco $e=(u,v)$ esaminato durante $\text{DFS}(G,u)$ e *non etichettato come* $\text{DISCOVERY}$ può ora essere etichettato come:
	  - **BACK EDGE** se $v$ è *antenato* di $u$ nell'*albero* definito dai $\text{DISCOVERY EDGE}$.
	  - **ALTRO**: negli altri casi.

>[!imporant] NOTA
>A differenza del caso non diretto, *non tutti* gli archi che non sono $\text{DISCOVERY EDGE}$ sono $\text{BACK EDGE}$.
## ESEMPIO BFS
Consideriamo il seguente grafo, le cui *liste di adiacenza* per i nodi sono:
- 1: $\{ 2,3 \}$
- 2: $\{ 4 \}$
- 3: $\emptyset$
- 4: $\{ 6 \}$
- 5: $\{ 3,4 \}$
- 6: $\{ 5,7 \}$
- 7: $\emptyset$

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A) at (0,0) {1};
    \node (B) at (-1.5,-1.5) {2};
    \node (C) at (1.5,-1.5) {3};
    \node (D) at (-1.5,-3.5) {4};
    \node (E) at (0.5,-3) {5};
    \node (F) at (0,-5) {6} ;
    \node (G) at (2.5,-3.5) {7} ;
\end{scope}

\begin{scope}[>={Stealth[black]},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path [->] (A) edge (B);
    \path [->] (A) edge (C);
    \path [->] (B) edge (D);
    \path [->] (D) edge (F);
    \path [->] (F) edge (E);
    \path [->] (F) edge (G);
    \path [->] (E) edge (C);
    \path [->] (E) edge (D);  
\end{scope}
\end{tikzpicture}

\end{document}
```

Osserviamo l'*ordine di visita* dei nodi, scegliendo come *nodo iniziale* $s=$*1*, secondo la **BFS**:

Il vertice *1* scopre *2* e *3*:
```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node[red] (A) at (0,0) {1};
    \node[red] (B) at (-1.5,-1.5) {2};
    \node[red] (C) at (1.5,-1.5) {3};
    \node (D) at (-1.5,-3.5) {4};
    \node (E) at (0.5,-3) {5};
    \node (F) at (0,-5) {6} ;
    \node (G) at (2.5,-3.5) {7} ;
\end{scope}

\begin{scope}[>={Stealth},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path [->] (A) edge[red] node{D} (B);
    \path [->] (A) edge[red] node{D} (C);
    \path [->] (B) edge (D);
    \path [->] (D) edge (F);
    \path [->] (F) edge (E);
    \path [->] (F) edge (G);
    \path [->] (E) edge (C);
    \path [->] (E) edge (D);  
\end{scope}
\end{tikzpicture}

\end{document}
```

Il vertice *2* scopre *4*. 3 *non scopre altri vertici:

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node[red] (A) at (0,0) {1};
    \node[red] (B) at (-1.5,-1.5) {2};
    \node[red] (C) at (1.5,-1.5) {3};
    \node[red] (D) at (-1.5,-3.5) {4};
    \node (E) at (0.5,-3) {5};
    \node (F) at (0,-5) {6} ;
    \node (G) at (2.5,-3.5) {7} ;
\end{scope}

\begin{scope}[>={Stealth},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path [->] (A) edge[red] node{D} (B);
    \path [->] (A) edge[red] node{D} (C);
    \path [->] (B) edge[red] node{D} (D);
    \path [->] (D) edge (F);
    \path [->] (F) edge (E);
    \path [->] (F) edge (G);
    \path [->] (E) edge (C);
    \path [->] (E) edge (D);  
\end{scope}
\end{tikzpicture}

\end{document}
```

Il vertice *4* scopre *6*:
```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node[red] (A) at (0,0) {1};
    \node[red] (B) at (-1.5,-1.5) {2};
    \node[red] (C) at (1.5,-1.5) {3};
    \node[red] (D) at (-1.5,-3.5) {4};
    \node (E) at (0.5,-3) {5};
    \node[red] (F) at (0,-5) {6} ;
    \node (G) at (2.5,-3.5) {7} ;
\end{scope}

\begin{scope}[>={Stealth},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path [->] (A) edge[red] node{D} (B);
    \path [->] (A) edge[red] node{D} (C);
    \path [->] (B) edge[red] node{D} (D);
    \path [->] (D) edge[red] node{D} (F);
    \path [->] (F) edge (E);
    \path [->] (F) edge (G);
    \path [->] (E) edge (C);
    \path [->] (E) edge (D);  
\end{scope}
\end{tikzpicture}

\end{document}
```

Il vertice *6* scopre *5* e *7*: 
```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node[red] (A) at (0,0) {1};
    \node[red] (B) at (-1.5,-1.5) {2};
    \node[red] (C) at (1.5,-1.5) {3};
    \node[red] (D) at (-1.5,-3.5) {4};
    \node[red] (E) at (0.5,-3) {5};
    \node[red] (F) at (0,-5) {6} ;
    \node[red] (G) at (2.5,-3.5) {7} ;
\end{scope}

\begin{scope}[>={Stealth},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path [->] (A) edge[red] node{D} (B);
    \path [->] (A) edge[red] node{D} (C);
    \path [->] (B) edge[red] node{D} (D);
    \path [->] (D) edge[red] node{D} (F);
    \path [->] (F) edge[red] node{D} (E);
    \path [->] (F) edge[red] node{D} (G);
    \path [->] (E) edge (C);
    \path [->] (E) edge (D);  
\end{scope}
\end{tikzpicture}

\end{document}
```

Il vertice *5* scopre gli archi *A* che *riportano* a *4* e *3*.

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node[red] (A) at (0,0) {1};
    \node[red] (B) at (-1.5,-1.5) {2};
    \node[red] (C) at (1.5,-1.5) {3};
    \node[red] (D) at (-1.5,-3.5) {4};
    \node[red] (E) at (0.5,-3) {5};
    \node[red] (F) at (0,-5) {6} ;
    \node[red] (G) at (2.5,-3.5) {7} ;
\end{scope}

\begin{scope}[>={Stealth},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path [->] (A) edge[red] node{D} (B);
    \path [->] (A) edge[red] node{D} (C);
    \path [->] (B) edge[red] node{D} (D);
    \path [->] (D) edge[red] node{D} (F);
    \path [->] (F) edge[red] node{D} (E);
    \path [->] (F) edge[red] node{D} (G);
    \path [->] (E) edge[cyan] node{A} (C);
    \path [->] (E) edge[cyan] node{A} (D);  
\end{scope}
\end{tikzpicture}

\end{document}
```

Osserviamo ora il grafo dal seguente punto di vista:

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A) at (0,0) {1};
    \node (B) at (-1.5,-1.5) {2};
    \node (C) at (1.5,-1.5) {3};
    \node (D) at (-1.5,-3.5) {4};
    \node (E) at (-0.5,-7.5) {5};
    \node (F) at (-1.5,-5.5) {6} ;
    \node (G) at (-2.5,-7.5) {7} ;
\end{scope}

\begin{scope}[>={Stealth},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path [->] (A) edge[red] node{D} (B);
    \path [->] (A) edge[red] node{D} (C);
    \path [->] (B) edge[red] node{D} (D);
    \path [->] (D) edge[red] node{D} (F);
    \path [->] (F) edge[red] node{D} (E);
    \path [->] (F) edge[red] node{D} (G);
    \path [->] (E) edge[bend right=65, cyan] node{A} (C);
    \path [->] (E) edge[bend right=30, cyan] node{A} (D);  
\end{scope}
\end{tikzpicture}

\end{document}
```

In *rosso* sono evidenziati i $\text{DISCOVERY EDGE}$ che formano uno *spanning tree* dei vertici raggiungibili da $s$.
## ESEMPIO DFS
Vediamo ora, sullo stesso grafo, l'esecuzione della DFS:
```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A)[red] at (0,0) {1};
    \node (B) at (-1.5,-1.5) {2};
    \node (C) at (1.5,-1.5) {3};
    \node (D) at (-1.5,-3.5) {4};
    \node (E) at (0.5,-3) {5};
    \node (F) at (0,-5) {6} ;
    \node (G) at (2.5,-3.5) {7} ;
\end{scope}

\begin{scope}[>={Stealth[black]},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path [->] (A) edge (B);
    \path [->] (A) edge (C);
    \path [->] (B) edge (D);
    \path [->] (D) edge (F);
    \path [->] (F) edge (E);
    \path [->] (F) edge (G);
    \path [->] (E) edge (C);
    \path [->] (E) edge (D);  
\end{scope}
\end{tikzpicture}

\end{document}
```

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A)[red] at (0,0) {1};
    \node (B)[red] at (-1.5,-1.5) {2};
    \node (C) at (1.5,-1.5) {3};
    \node (D) at (-1.5,-3.5) {4};
    \node (E) at (0.5,-3) {5};
    \node (F) at (0,-5) {6} ;
    \node (G) at (2.5,-3.5) {7} ;
\end{scope}

\begin{scope}[>={Stealth},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path [->] (A) edge[red] node{D} (B);
    \path [->] (A) edge (C);
    \path [->] (B) edge (D);
    \path [->] (D) edge (F);
    \path [->] (F) edge (E);
    \path [->] (F) edge (G);
    \path [->] (E) edge (C);
    \path [->] (E) edge (D);  
\end{scope}
\end{tikzpicture}

\end{document}
```

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A)[red] at (0,0) {1};
    \node (B)[red] at (-1.5,-1.5) {2};
    \node (C) at (1.5,-1.5) {3};
    \node (D)[red] at (-1.5,-3.5) {4};
    \node (E) at (0.5,-3) {5};
    \node (F) at (0,-5) {6} ;
    \node (G) at (2.5,-3.5) {7} ;
\end{scope}

\begin{scope}[>={Stealth},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path [->] (A) edge[red] node{D} (B);
    \path [->] (A) edge (C);
    \path [->] (B) edge[red] node{D} (D);
    \path [->] (D) edge (F);
    \path [->] (F) edge (E);
    \path [->] (F) edge (G);
    \path [->] (E) edge (C);
    \path [->] (E) edge (D);  
\end{scope}
\end{tikzpicture}

\end{document}
```

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A)[red] at (0,0) {1};
    \node (B)[red] at (-1.5,-1.5) {2};
    \node (C) at (1.5,-1.5) {3};
    \node (D)[red] at (-1.5,-3.5) {4};
    \node (E) at (0.5,-3) {5};
    \node (F)[red] at (0,-5) {6} ;
    \node (G) at (2.5,-3.5) {7} ;
\end{scope}

\begin{scope}[>={Stealth},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path [->] (A) edge[red] node{D} (B);
    \path [->] (A) edge (C);
    \path [->] (B) edge[red] node{D} (D);
    \path [->] (D) edge[red] node{D} (F);
    \path [->] (F) edge (E);
    \path [->] (F) edge (G);
    \path [->] (E) edge (C);
    \path [->] (E) edge (D);  
\end{scope}
\end{tikzpicture}

\end{document}
```

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A)[red] at (0,0) {1};
    \node (B)[red] at (-1.5,-1.5) {2};
    \node (C) at (1.5,-1.5) {3};
    \node (D)[red] at (-1.5,-3.5) {4};
    \node (E)[red] at (0.5,-3) {5};
    \node (F)[red] at (0,-5) {6} ;
    \node (G) at (2.5,-3.5) {7} ;
\end{scope}

\begin{scope}[>={Stealth},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path [->] (A) edge[red] node{D} (B);
    \path [->] (A) edge (C);
    \path [->] (B) edge[red] node{D} (D);
    \path [->] (D) edge[red] node{D} (F);
    \path [->] (F) edge[red] node{D} (E);
    \path [->] (F) edge (G);
    \path [->] (E) edge (C);
    \path [->] (E) edge (D);  
\end{scope}
\end{tikzpicture}

\end{document}
```

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A)[red] at (0,0) {1};
    \node (B)[red] at (-1.5,-1.5) {2};
    \node (C)[red] at (1.5,-1.5) {3};
    \node (D)[red] at (-1.5,-3.5) {4};
    \node (E)[red] at (0.5,-3) {5};
    \node (F)[red] at (0,-5) {6} ;
    \node (G) at (2.5,-3.5) {7} ;
\end{scope}

\begin{scope}[>={Stealth},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path [->] (A) edge[red] node{D} (B);
    \path [->] (A) edge (C);
    \path [->] (B) edge[red] node{D} (D);
    \path [->] (D) edge[red] node{D} (F);
    \path [->] (F) edge[red] node{D} (E);
    \path [->] (F) edge (G);
    \path [->] (E) edge[red] node{D} (C);
    \path [->] (E) edge (D);  
\end{scope}
\end{tikzpicture}

\end{document}
```

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A)[red] at (0,0) {1};
    \node (B)[red] at (-1.5,-1.5) {2};
    \node (C)[red] at (1.5,-1.5) {3};
    \node (D)[red] at (-1.5,-3.5) {4};
    \node (E)[red] at (0.5,-3) {5};
    \node (F)[red] at (0,-5) {6} ;
    \node (G) at (2.5,-3.5) {7} ;
\end{scope}

\begin{scope}[>={Stealth},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path [->] (A) edge[red] node{D} (B);
    \path [->] (A) edge (C);
    \path [->] (B) edge[red] node{D} (D);
    \path [->] (D) edge[red] node{D} (F);
    \path [->] (F) edge[red] node{D} (E);
    \path [->] (F) edge (G);
    \path [->] (E) edge[red] node{D} (C);
    \path [->] (E) edge[blue] node{B} (D);  
\end{scope}
\end{tikzpicture}

\end{document}
```

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A)[red] at (0,0) {1};
    \node (B)[red] at (-1.5,-1.5) {2};
    \node (C)[red] at (1.5,-1.5) {3};
    \node (D)[red] at (-1.5,-3.5) {4};
    \node (E)[red] at (0.5,-3) {5};
    \node (F)[red] at (0,-5) {6} ;
    \node (G)[red] at (2.5,-3.5) {7} ;
\end{scope}

\begin{scope}[>={Stealth},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path [->] (A) edge[red] node{D} (B);
    \path [->] (A) edge (C);
    \path [->] (B) edge[red] node{D} (D);
    \path [->] (D) edge[red] node{D} (F);
    \path [->] (F) edge[red] node{D} (E);
    \path [->] (F) edge[red] node{D} (G);
    \path [->] (E) edge[red] node{D} (C);
    \path [->] (E) edge[blue] node{B} (D);  
\end{scope}
\end{tikzpicture}

\end{document}
```

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A)[red] at (0,0) {1};
    \node (B)[red] at (-1.5,-1.5) {2};
    \node (C)[red] at (1.5,-1.5) {3};
    \node (D)[red] at (-1.5,-3.5) {4};
    \node (E)[red] at (0.5,-3) {5};
    \node (F)[red] at (0,-5) {6} ;
    \node (G)[red] at (2.5,-3.5) {7} ;
\end{scope}

\begin{scope}[>={Stealth},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path [->] (A) edge[red] node{D} (B);
    \path [->] (A) edge[cyan] node{A} (C);
    \path [->] (B) edge[red] node{D} (D);
    \path [->] (D) edge[red] node{D} (F);
    \path [->] (F) edge[red] node{D} (E);
    \path [->] (F) edge[red] node{D} (G);
    \path [->] (E) edge[red] node{D} (C);
    \path [->] (E) edge[blue] node{B} (D);  
\end{scope}
\end{tikzpicture}

\end{document}
```

Ancora una volta, osserviamo il grafo dal seguente punto di vista:

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A) at (0,0) {1};
    \node (B) at (0,-2) {2};
    \node (C) at (-1.5,-9.5) {3};
    \node (D) at (0,-4) {4};
    \node (E) at (-1.5,-7.5) {5};
    \node (F) at (0,-6) {6} ;
    \node (G) at (1.5,-7.5) {7} ;
\end{scope}

\begin{scope}[>={Stealth},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path [->] (A) edge[red] node{D} (B);
    \path [->] (A) edge[bend right = 40, cyan] node{A} (C);
    \path [->] (B) edge[red] node{D} (D);
    \path [->] (D) edge[red] node{D} (F);
    \path [->] (F) edge[red] node{D} (E);
    \path [->] (F) edge[red] node{D} (G);
    \path [->] (E) edge[red] node{D} (C);
    \path [->] (E) edge[bend left=30, blue] node{B} (D);  
\end{scope}
\end{tikzpicture}

\end{document}
```

>[!warning] ATTENZIONE
>Se $(v,w)$ è un arco esaminato da $\text{DFS}(G,v)$ con $w.\text{ID}=1$, tale arco *non può essere etichettato* come $\text{DISCOVERY EDGE}$.
>Sarà quindi:
>- **BACK EDGE** se quando viene esaminato $(v,w)$ la *chiamata* a $\text{DFS}(G,w)$ è *iniziata ma non conclusa*.
>- **ALTRO** negli altri casi.
## ANALISI BFS E DFS
Sia $G=(V,E)$ un grafo diretto e sia $s \in V$. Definiamo:
- $R_{s}=\text{reachable}(s)$ l'*insieme di vertici raggiungibili* da $s$, cioè i vertici $v$ per i quali esiste un cammino diretto da $s$ a $v$.
- $m_{s}=\sum_{v\in R_{s}}\text{outdegree}(v)$.

>[!important] PROPOSIZIONE
>Sia **BFS(g,s)** che **DFS(G,s)** hanno *complessità* $\Theta(m_{s})$ e, al *termine* della loro *esecuzione*, si ha:
>1. Tutti i *vertici* di $R_{s}$ ed i loro *archi uscenti* sono stati *visitati/etichettati*.
>2. I $\text{DISCOVERY EDGE}$ formano uno *spanning tree* $T$ di $R_{s}$ radicato in $s$.
>
>Inoltre permettono di risolvere i seguenti problemi:
>1. (**BFS + DFS**) Determinare $R_{s}$ con complessità $\Theta(m_{s})$.
>2. (**DFS**) Determinare se $G$ è *fortemente connesso* con complessità $\Theta(|V|+|E|)$.
>3. (**DFS**) Determinare un *ciclo diretto* in $G$, se esiste, con complessità $\Theta(|V|+|E|)$.
>4. (**BFS**) Trovare le *distanze* ed i *cammini minimi* diretti da $s$ ad ogni vertice $v\in R_{s}$ con complessità $\Theta(m_{s})$.

# GRAFI DIRETTI ACICLICI
>[!def] DEF
>Un *grafo diretto aciclico* (Directed Acyclic Graph o **DAG**) è un grafo diretto *privo di cicli diretti*.

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (A) at (0,0) {u};
    \node (B) at (1.5,1.5) {v};
    \node (C) at (3,0) {x};
    \node (D) at (1.5,-1.5) {w};
\end{scope}

\begin{scope}[>={Stealth},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path [->] (A) edge (B);
    \path [->] (B) edge (C);
    \path [->] (C) edge (D);
    \path [->] (A) edge (D);
    \path [->] (A) edge (C);
\end{scope}
\end{tikzpicture}

\end{document}
```

>[!important] OSSERVAZIONE
>Ignorando l'orientamento degli archi, il grafo qui sopra avrebbe dei cicli: tuttavia **non** ha **cicli diretti**.
## ORDINAMENTO TOPOLOGICO
>[!def] DEF
>Un *ordinamento topologico* per un DAG $G=(V,E)$ è un *ordinamento dei vertici* $v_{1},v_{2},\dots,v_{n}$ di $V$ tale che *per ogni arco* $(v_{i},v_{j})\in E$ vale $i<j$.

L'ordinamento topologico per l'esempio visto sopra è:
$$
u,v,x,y,w,z,q,s,k
$$
Gli ordinamenti topologici trovano applicazione nello *scheduling di task* con *dipendenze funzionali*: un *DAG* può rappresentare un contesto in cui bisogna stabilire l'*ordine* (e anche il tempo) in cui i *task* di un certo insieme devono *essere eseguiti* rispettando le dipendenze funzionali tra essi (ad esempio un certo task deve essere *eseguito prima di un altro*).
## ALGORITMO PER ORDINAMENTO TOPOLOGICO
L'ordinamento topologico di un DAG può essere calcolato con un *algoritmo iterativo* che utilizza una *coda L* (inizialmente vuota) ed una *lista S* (inizializzata con i *vertici con indegree nullo*).

In ciascuna *iterazione*, *estrae* un *vertice* $v$ da $S$ eseguendo le seguenti operazioni:
- *Aggiunge* $v$ in coda a $L$.
- Per ogni arco $e=(v,u)$ uscente $v$ decrementa di 1 il conteggio degli archi entranti in $u$ e, se *diventa 0*, *inserisce* $u$ in $S$.

Alla fine *L contiene l'ordinamento topologico* di $V$.
L'algoritmo assume di avere a disposizione un *campo* $e.\text{indeg}$ per ogni $u\in V$ che usa per tenere *traccia* del *grado entrante* di $u$.

Vediamo l'algoritmo **TopologicalSort(G)**:
- **INPUT** DAG $G=(V,E)$ rappresentato con liste di adiacenza.
- **OUTPUT** Coda $L$ con l'ordinamento topologico dei vertici di $G$.

```pseudo
\begin{algorithm}
\caption{TopologicalSort(G)}
 \begin{algorithmic}
   \State inizializza u.indeg = indegree(u) $\forall u \in V$
   \State $L\gets$ coda vuota
   \State $S\gets$ lista di vertici $v\in V$ con v.indeg = 0
   \While{!S.isEmpty()}
     \State $v\gets$ S.first().getElement()
     \State S.remove(S.first())
     \State L.addLast(v)
     \ForAll{$e\in$G.incidentEdges(v)}
       \State $u\gets$ G.opposite(v,e)
       \State u.indeg $\gets$ u.indeg-1
       \If{u.indeg = 0}
         \State S.addLast(u)
       \EndIf
     \EndFor
   \EndWhile
   \Return L
 \end{algorithmic}
\end{algorithm}
```
### ESEMPIO
Consideriamo il seguente grafo:

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (U) at (0,0) {u};
    \node (V) at (1.5,1.5) {v};
    \node (X) at (3,0) {x};
    \node (W) at (1.5,-1.5) {w};
    \node (Z) at (4.5,0) {z};
    \node (Y) at (3,-3) {y};
    \node (S) at (4.5,1.5) {s};
    \node (Q) at (4.5,-2) {q};
    \node (K) at (6,1.5) {k};
\end{scope}

\begin{scope}[>={Stealth},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path [->] (U) edge (V);
    \path [->] (V) edge (X);
    \path [->] (X) edge (W);
    \path [->] (U) edge (W);
    \path [->] (U) edge (X);
    \path [->] (X) edge (Y);
    \path [->] (Y) edge (W);
    \path [->] (X) edge (Z);
    \path [->] (Z) edge (S);
    \path [->] (S) edge (K);
    \path [->] (Z) edge (K);
    \path [->] (Z) edge (Q);
\end{scope}
\end{tikzpicture}

\end{document}
```

Dopo l'esecuzione dell'algoritmo, troviamo l'*ordinamento* seguente:
$$
u,v,x,y,z,w,s,q,k
$$
Per comprendere meglio l'applicazione allo *scheduling di processi*, osserviamo il grafo dal seguente punto di vista:

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}
\begin{scope}[every node/.style={circle,thick,draw}]
    \node (U) at (0,0) {u};
    \node (V) at (1.5,0) {v};
    \node (X) at (3,0) {x};
    \node (Y) at (4.5,0) {y};
    \node (Z) at (6,0) {z};
    \node (W) at (7.5,0) {w};
    \node (S) at (9,0) {s};
    \node (Q) at (10.5,0) {q};
    \node (K) at (12,0) {k};
\end{scope}

\begin{scope}[>={Stealth},
              every node/.style={fill=white,circle},
              every edge/.style={draw=black,very thick}]
    \path [->] (U) edge (V);
    \path [->] (V) edge (X);
    \path [->] (X) edge[bend right=50] (W);
    \path [->] (U) edge[bend right=80] (W);
    \path [->] (U) edge[bend left=35] (X);
    \path [->] (X) edge (Y);
    \path [->] (Y) edge[bend right=35] (W);
    \path [->] (X) edge[bend left=35] (Z);
    \path [->] (Z) edge[bend left=35] (S);
    \path [->] (S) edge[bend right=35] (K);
    \path [->] (Z) edge[bend left=70] (K);
    \path [->] (Z) edge[bend left=50] (Q);
\end{scope}
\end{tikzpicture}

\end{document}
```

In questo modo mettiamo in *evidenza* le **relazioni di dipendenza** e capiamo facilmente quali task devono essere *completati* prima di poterne *svolgere un altro*. 
### ANALISI ALGORITMO
>[!important] PROPOSIZIONE
>Sia $G=(V,E)$ un **DAG** con $n$ vertici ed $m$ archi.
>$\text{TopologicalSort}(G)$ restituisce un *ordinamento topologico* di $V$ in tempo $\Theta(n+m)$.

- L'inizializzazione dei campi $u.\text{indeg}$ ha un costo $\Theta(n+m)$.
- Se l'algoritmo è corretto, ogni vertice $v\in V$ sarà *inserito ed estratto* da $S$ esattamente *una volta*. Per cui verranno effettuate $n$ iterazioni del while.
- L'iterazione che *estrae un vertice* $v$ andrà ad *esaminare* tutti gli *archi uscenti* da $v$
  La complessità del while è quindi $\Theta(n+m)$.

Allora anche la *complessità totale* è:
$$
\Theta(n+m)
$$
