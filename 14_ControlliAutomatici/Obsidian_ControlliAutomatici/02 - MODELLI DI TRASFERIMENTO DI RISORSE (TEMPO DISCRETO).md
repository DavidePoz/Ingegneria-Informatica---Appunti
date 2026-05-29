# INDICE SEZIONE
- [ ] [[#MODELLI DI DECISIONE]]
- [ ] [[#MODELLI A STRUTTURA D'ETA' (MODELLO DI LESLIE)]]
      - [[#DINAMICA DELLE CLASSI]]
      - [[#SISTEMA LINEARE E ANDAMENTO DELLA POPOLAZIONE]]
      - [[#STRUTTURE D'ETA' D'EQUILIBRIO]]
- [ ] [[#ESEMPI]]
      - [[#POPOLAZIONE DI CONIGLI]]
      - [[#POPOLAZIONE SALMONI]]
      - [[#CATENE DI PRODUZIONE CON TEST QUALITA']]
# MODELLI DI DECISIONE
A *tempo discreto*, le equazioni che regolano gli scambi sono *analoghe*, con la sola differenza che la *derivata* è sostituita da *una differenza*.
Abbiamo quindi a che fare con equazioni del tipo:
$$
\begin{align*}
x_{i}(k+1)-x_{i}(k) &= q_{i}^{(in)} - q_{i}^{(out)} = \\
&= \sum_{j=1,j\ne i}^n (\alpha_{ji}+\gamma_{ji})x_{j} + \gamma_{ii}x_{i}(k) - \sum_{j=0,j\ne i}^n \alpha_{ij}x_{i}(k) \\
&+ \sum_{l=1}^p \beta_{li}u_{l}(k) - \sum_{h=1}^p\beta_{ih}u_{h}(k)
\end{align*}
$$
In *forma matriciale* scriviamo:
$$
x(k+1) = Ax(k) + Bu(k)
$$
>[!note] COEFFICIENTI MATRICE $A$
>- $a_{ij}=\alpha_{ji}+\gamma_{ji}$
>- $a_{ii} = 1+\gamma_{ii} -\sum_{j=0,j\ne i}^n \alpha_{ij}$

L'aggiunta dell'$1$ deriva dal fatto che $x_{i}(k+1)-x_{i}(k)=\dots$ diventa $x_{i}(k+1)=x_{i}(k)+\dots$

Notiamo che, assegnate le *condizioni iniziali* $x(0)$ e $u(0)$, abbiamo:
$$
\begin{align*}
x(1) &= Ax(0) + Bu(0) \\
x(2) &= Ax(1) + Bu(1) = A^2x(0) + ABu(0) + Bu(1) \\
\dots
\end{align*}
$$
>[!note] NOTA
>*Non abbiamo a che fare con equazioni differenziali*, ma con **relazioni ricorsive**.
# MODELLI A STRUTTURA D'ETA' (MODELLO DI LESLIE)
>[!def] POPOLAZIONE
>Numero di **individui** (*femmine o maschi*) o **coppie**.

Fissato un istante $k$ ed un'età $T$, usiamo la seguente notazione:
- $x_{1}(k)$ : popolazione di età $[0,T)$ all'istante $k$.
- $x_{2}(k)$ : popolazione di età $[T,2T)$ all'istante $k$.
- $\dots$
- $x_{n}(k)$ : popolazione di età $[(n-1)T,+\infty)$ all'istante $k$.

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}[
    % --- Configurazione Stili ---
    timeline/.style={thick, -{Stealth[scale=1.2]}},
    tick/.style={thick},
    dimension/.style={gray!80, {Latex[scale=0.8]}-{Latex[scale=0.8]}, shorten >=2pt, shorten <=2pt}
]

    % --- 1. Asse Temporale ---
    \draw[timeline] (0,0) -- (10,0);

    % --- 2. Tacche (Ticks) ---
    \foreach \x in {1.2, 4.2, 7.2} {
        \draw[tick] (\x, 0.3) -- (\x, -0.3);
    }

    % --- 3. Etichette Superiori ---
    \node[above=0.3cm] at (1.2, 0) {\Large $(i-1)T$};
    \node[above=0.3cm] at (4.2, 0) {\Large $iT$};

    % --- 4. Quota Dimensionale (Intervallo T) ---
    \draw[dimension] (1.2, -0.4) -- node[below=0.1cm, black] {\Large $T$} (4.2, -0.4);

\end{tikzpicture}
\end{document}
```

>[!note] NOTA
>In questi modelli, i *compartimenti* diventano le **fasce d'età** in cui è *suddivisa una popolazione*.

Per descrivere tali modelli definiamo:
- $s_{i}$ : tasso di *sopravvivenza*.
- $s_{0}$ : tasso di *sopravvivenza alla nascita*.
- $f_{i}$ : tasso di *fertilità*.

E li rappresentiamo con grafi come il seguente:

```tikz
\usepackage{amsmath}
\usepackage{tikz}
\usetikzlibrary{positioning, arrows.meta, calc}

\definecolor{mygrey}{RGB}{160, 160, 160} % Grigio leggermente più chiaro per i nodi

\begin{document}
\begin{tikzpicture}[
    auto,
    >= {Stealth[scale=1.2]},      
    node distance= 2.5cm,         
    % --- Stili dei Nodi ---
    state/.style={
        circle,
        draw=mygrey,
        fill=mygrey,
        text=white,
        minimum size=1cm,
    },
    % --- Stili delle Frecce ---
    flow/.style={
        ->,
        thick,
        draw=mygrey,
        text=black % Testo nero per leggibilità delle etichette
    },
    inhibit/.style={
        {Bar[width=2.5mm, line width=1pt]}-{Stealth[scale=1.2]}, 
        thick,
        draw=mygrey,
        text=black,
        shorten <=2pt
    }
]

    % --- 1. Posizionamento dei Nodi ---
    \node[state] (n1) {1};
    \node[state, right=of n1] (n2) {2};
    \node[state, right=of n2] (n3) {3};
    \node[right=1.2cm of n3] (dots) {\Large $\dots$};
    \node[state, right=1.2cm of dots] (nn_minus) {$n-1$};
    \node[state, right=of nn_minus] (nn) {$n$};

    % --- 2. Transizioni di Sopravvivenza (Orizzontali) ---
    \draw[flow] (n1) -- node[below] {$s_1$} (n2);
    \draw[flow] (n2) -- node[below] {$s_2$} (n3);
    \draw[flow] (n3) -- node[below] {$s_3$} (dots);
    \draw[flow] (dots) -- (nn_minus);
    \draw[flow] (nn_minus) -- node[below] {$s_{n-1}$} (nn);

    % --- 3. Uscite verso il basso (Mortalità) ---
    \draw[flow] (n1) -- ++(0,-1.5cm) node[right] {$1-s_1$};
    \draw[flow] (n2) -- ++(0,-1.5cm) node[right] {$1-s_2$};
    \draw[flow] (n3) -- ++(0,-1.5cm) node[right] {$1-s_3$};
    \draw[flow] (nn_minus) -- ++(0,-1.5cm) node[right] {$1-s_{n-1}$};
    \draw[flow] (nn) -- ++(0,-1.5cm) node[right] {$1-s_n$};

    % --- 4. Frecce di Fertilità (Archi superiori con inibizione) ---
    
    % Auto-loop su 1
    \draw[inhibit] (n1) to[out=135, in=225, looseness=5] node[left, pos=0.5] {$s_0 f_1$} (n1);
    
    % Da 2 a 1
    \draw[inhibit] (n2) to[bend right=45] node[above] {$s_0 f_2$} (n1);
    
    % Da 3 a 1
    \draw[inhibit] (n3) to[bend right=55] node[above] {$s_0 f_3$} (n1);
    
    % Da n-1 a 1
    \draw[inhibit] (nn_minus) to[bend right=65] node[above, pos=0.7] {$s_0 f_{n-1}$} (n1);
    
    % Da n a 1
    \draw[inhibit] (nn) to[bend right=75] node[above, pos=0.8] {$s_0 f_n$} (n1);

\end{tikzpicture}
\end{document}
```

Ogni *nodo* rappresenta una *fascia d'età*, detta anche **classe**.

>[!note] SIGNIFICATO DELLA RAPPRESENTAZIONE
>1. Per ogni *fascia d'età* $i$ una *porzione* di individui $s_{i}$ *sopravvive e raggiunge la fascia successiva*, i rimanenti $1-s_{i}$ *muoiono*.
>2. I *nuovi individui generati*, ovviamente, *partono dalla prima fascia d'età*.
>3. *Non tutti* i nuovi nati *sopravvivono*: le generazione di individui è *regolata dal tasso di sopravvivenza alla nascita* $s_{0}$.
## DINAMICA DELLE CLASSI
La dinamica della popolazione della classe $i$-esima, per $i\in \{ 2,3,\dots,n-1 \}$ (classi **intermedie**) è la seguente:
$$
x_{i}(k+1) - x_{i}(k) = s_{i-1}x_{i-1}(k) - s_{i}x_{i}(k) - (1-s_{i})x_{i}(k)
$$
>[!note] NOTA
>Il primo termine indica gli *individui della classe precedente* che sono *invecchiati ed entrati nella classe in esame*, gli altri due termini indicano gli *individui invecchiati e passati alla classe successiva* e quelli *morti*.

La relazione sopra si semplifica e diventa:
$$
x_{i}(k+1) = s_{i-1}x_{i-1}(k)
$$
Vediamo ora da che relazione è descritta la dinamica dell'**ultima classe** d'età ($i=n$):
$$
x_{n}(k+1) - x_{n}(k) = s_{n-1}x_{n-1}(k) - (1-s_{n})x_{n}(k)
$$
Che semplifichiamo per trovare:
$$
x_{n}(k+1) = s_{n-1}x_{n-1}(k) + s_{n}x_{n}(k)
$$
>[!note] NOTA
>Stavolta il numero di individui è dato da *quelli invecchiati dalla classe precedente*, più *quelli sopravvissuti nella stessa classe*.

E vediamo infine la dinamica della **prima** classe d'età ($i=1$):
$$
x_{1}(k+1) - x_{1}(k) = s_{0}[f_{1}x_{1}(k)+f_{2}x_{2}(k)+\ldots+f_{n}x_{n}(k)] - s_{1}x_{1}(k) - (1-s_{1})x_{1}(k)
$$
Che ancora una volta semplifichiamo, trovando:
$$
x_{1}(k+1) = s_{0}[f_{1}x_{1}(k)+f_{2}x_{2}(k)+\ldots+f_{n}x_{n}(k)]
$$
>[!note] NOTA
>Il numero di *nuovi individui della prima classe* è dato semplicemente dai *sopravvissuti* tra i *nuovi nati generati dalle varie classi*.
## SISTEMA LINEARE E ANDAMENTO DELLA POPOLAZIONE
Ora che abbiamo analizzato la dinamica delle varie classi d'età, vogliamo scrivere il *sistema lineare associato*.

Come al solito, vogliamo scrivere le equazioni nella forma:
$$
x(k+1) = Ax(k)
$$
Con
$$
x(k) = \begin{pmatrix}
x_{1}(k) \\
x_{2}(k) \\
\vdots \\
x_{n}(k)
\end{pmatrix}
$$
Dalla dinamica delle classi vista sopra, otteniamo allora:
$$
x(k+1) = \underbrace{ \begin{bmatrix}
s_{0}f_{1} & s_{0}f_{2} & \dots & s_{0}f_{n-1} & s_{0}f_{n} \\
s_{1} & 0 & \dots & 0 & 0 \\
0 & s_{2} & \dots & 0 & 0 \\
\vdots &  & \ddots &  & \vdots \\
0 & 0 & \dots & s_{n-1} & s_{n}
\end{bmatrix} }_{ A } x(k)
$$
La matrice $A$ è detta anche **matrice di Leslie**.

A questo punto, per analizzare l'*andamento della popolazione*, è utile introdurre un indice $R$.
Per farlo, consideriamo:
- $s_{0}s_{1}\dots s_{i-1}$ : probabilità che una *femmina* sopravviva **almeno fino all'età** $(i-1)T$.
- $s_{0}s_{1}\dots s_{i-1}f_{i}$ : *femmine generate in media* da una *femmina* della *classe* $i$.

>[!idea] NUMERO DI INDIVIDUI VS NUMERO DI FEMMINE
>Generalmente, $x_{i}(k)$ indica la **popolazione femminile** nella classe $i$-esima al tempo $k$ ed i tassi di fertilità $f_{i}$ sono riferiti alla *probabilità di generare figlie femmine*.
>
>Questa è una *buona approssimazione* della dinamica reale, dal momento che in quasi tutte le specie il *numero di maschi non è un fattore limitante* per la crescita della popolazione, che è invece *fortemente correlata* con il numero di *femmine fertili*.
>
>Se invece volessimo tracciare *sia maschi che femmine*, dovremmo creare un modello *molto più complesso*, **non lineare**.

>[!note] NOTA
>Si assume inoltre che il *rapporto maschi/femmine* rimanga all'incirca *costante*, il che permette di conoscere automaticamente l'*intera popolazione* partendo dalla sola informazione sul numero di femmine.

Fatte queste osservazioni, definiamo l'indice $R$:

>[!def] TASSO NETTO DI RIPRODUZIONE
>$$ R = s_{0}f_{1} + s_{0}s_{1}f_{2} + \ldots + s_{0}s_{1}\dots s_{n-2}f_{n-1} + s_{0}s_{1}\dots s_{n-1}f_{n}(1 + s_{n} + s_{n}^2 + \dots) $$
>**NOTA**: l'ultimo termine tiene conto del fatto che gli individui dell'*ultima classe, vi rimangono*. Tuttavia $s_{n}^k\to 0$ per $k\to \infty$, cioè *non sopravviveranno all'infinito*. 

Il *tasso netto di riproduzione* è molto utile, perchè ci permette di *prevedere l'andamento della popolazione*:
- $R>1$ :  la popolazione **aumenterà**.
- $R<1$ : la popolazione **diminuirà**.
- $R=1$ : la popolazione **rimane costante** (stazionaria).
## STRUTTURE D'ETA' D'EQUILIBRIO
Proseguiamo lo studio del modello, definendo le seguenti quantità:

>[!def] POPOLAZIONE TOTALE
>$$ N(k) = x_{1}(k) + \ldots + x_{n}(k) $$

>[!def] FRAZIONE DI POPOLAZIONE $i$
>$$ S_{i}(k) = \frac{x_{i}(k)}{N(k)} $$

A partire da queste quantità, definiamo la **struttura d'età** della popolazione:

>[!def] STRUTTURA D'ETA'
>$$ S(k) = \begin{pmatrix} S_{1}(k) \\ \vdots \\ S_{n}(k) \end{pmatrix} = \frac{x(k)}{N(k)} $$

La *struttura d'età* è quindi la *frazione di individui che compongono ciascuna classe d'età rispetto alla popolazione complessiva*.

>[!idea] MOTIVAZIONE
>Solitamente $N(k)$ e i singoli $x_{i}(k)$ sono **altamente instabili** (aumentano esponenzialmente oppure la specie si estingue).
>$S(k)$, al contrario, tende ad **essere stabile**.

A questo punto viene allora naturale chiedersi se $S(k)$ *raggiunge* effettivamente un valore $S_{eq}$ di **equilibrio**.
Ipotizziamo:
$$
S_{eq} = \frac{x(k)}{N(k)} \implies x(k) = N(k)S_{eq}
$$
Cioè il numero di individui è dato dal *totale moltiplicato per la distribuzione* (che *rimane stazionaria*) nelle varie classi.
Inoltre, siccome stiamo assumendo che $S_{eq}$ sia un *valore di equilibrio*, abbiamo anche:
$$
x(k+1) = N(k+1)S_{eq}
$$
Avevamo $x(k+1)=Ax(k)$, per cui, mettendo insieme le due relazioni, troviamo:
$$
\begin{align*}
N(k+1)S_{eq} = x(k+1) &= Ax(k) = AN(k)S_{eq} \\ \\
N(k+1)S_{eq} &= AN(k)S_{eq}
\end{align*}
$$
Riscriviamo questa relazione nella forma seguente:
$$
AS_{eq} = \frac{N(k+1)}{N(k)}S_{eq}
$$
>[!note] NOTA
>Così notiamo subito che $S_{eq}$ è **autovettore** di $A$ associato all'**autovalore** $\lambda=\frac{N(k+1)}{N(k)}$.

Possiamo quindi scrivere:
$$
N(k+1) = \lambda N(k)
$$
E al variare di $\lambda$ abbiamo:
- $\lambda=1$ : *popolazione costante*.
- $\lambda>1$ : *popolazione in crescita*.
- $\lambda<1$ : *popolazione in diminuzione*.

>[!note] NOTA
>Per certe matrici (tra cui anche quelle della stessa forma delle *matrici di Leslie*) è possibile dimostrare che, tra *tutti gli autovalori possibili*, ne esiste uno **dominante** (positivo e maggiore degli altri) associato all'**unico autovettore** con **tutte le componenti positive** ($\lambda$ ed $S_{eq}$ corrispondono proprio a questi).

>[!idea] CONSEGUENZA
>Per studiare l'*andamento della popolazione* possiamo semplicemente calcolare l'*autovalore* $\lambda$ e l'*autovettore* $S_{eq}$ associati alla *matrice di Leslie* della specie in esame.

## TASSO DI RIPRODUZIONE VS STRUTTURA D'ETA'
Abbiamo *previsto l'andamento della popolazione* usando *due metodi diversi*:
1. Analizzando il **tasso netto di riproduzione**.
2. Analizzando la **struttura d'età di equilibrio** e l'**autovalore associato**.

Si può dimostrare che:
- Se $R>1$, allora anche $\lambda>1$.
- Se $R<1$, allora anche $\lambda<1$.
- Se $R=1$, allora anche $\lambda=1$.

Chiaramente questo è ciò che ci aspettiamo (*coerenza matematica*), ma ci porta anche a chiederci quale sia la *differenza tra i due approcci*: se la *conclusione è la stessa*, perchè dobbiamo seguire due *strade diverse* (dal punto di vista *matematico*)?

>[!idea] DIFFERENZA
>1. $R$ è un **identificatore globale**, che rispecchia le *generazioni* (vite intere).
>   Ci dice sostanzialmente *quante figlie genererà un individuo nella sua vita* (in totale), permettendoci di *prevedere* il **destino finale della specie**, ma *senza darci informazioni su quanto velocemente ci arriverà*.
>2. $\lambda$ invece fornisce *informazioni* in termini di **passi temporali**: ci dice di **quanto si moltiplica** l'intera popolazione nel *passaggio da $k$ a $k+1$* (è una *misura di velocità*).
## RICCHEZZA DELLA POPOLAZIONE E NATALITA'
Possiamo usare la seguente *rappresentazione* per *visualizzare le strutture d'età di equilibrio*:

```tikz
\usepackage{amsmath}
\usepackage{tikz}
\usetikzlibrary{arrows.meta, positioning, calc}

\begin{document}

\begin{tikzpicture}[
	scale = 0.7,
    y=0.6cm, % scala verticale
    bar/.style={fill=gray!70, draw=none},
    highlight/.style={fill=gray!90, draw=black, thick},
    axis/.style={thick, -{Stealth}}
]
    % --- GRAFICO A SINISTRA ---
    \begin{scope}[shift={(0,0)}]
        \node[above] at (5, 8.5) {\textbf{FEMMINE}};
        % Assi
        \draw[axis] (0,0) -- (6,0) node[right] {\#};
        \draw[axis] (0,0) -- (0,8.5);
        % Etichette asse X
        \foreach \x/\val in {0/0, 2.5/5, 5/10} \node[below] at (\x,0) {\small \val};
        
        % Barre (dal basso verso l'alto)
        \draw[bar] (0,0) rectangle (5.2, 1);   % 0-14
        \draw[bar] (0,1) rectangle (4.8, 2);   % 15-29
        \draw[highlight] (0,2) rectangle (4.5, 3) node[right, black] {\Large $S_{eq_i}$}; % 30-44
        \draw[bar] (0,3) rectangle (4.0, 4);   % 45-59
        \draw[bar] (0,4) rectangle (3.2, 5);   % 60-74
        \draw[bar] (0,5) rectangle (2.2, 6);   % 75-89
        \draw[bar] (0,6) rectangle (0.8, 7);   % 90-105
        
        % Etichette Età (centrali tra i due grafici)
        \node at (9, 9) {\textbf{età}};
        \foreach \y/\label in {0.5/0-14, 1.5/15-29, 2.5/30-44, 3.5/45-59, 4.5/60-74, 5.5/75-89, 6.5/90-105}
            \node at (9, \y) {\small \label};
    \end{scope}

    % --- GRAFICO A DESTRA ---
    \begin{scope}[shift={(12,0)}]
        \node[above] at (5, 8.5) {\textbf{FEMMINE}};
        % Assi
        \draw[axis] (0,0) -- (10,0) node[right] {\#};
        \draw[axis] (0,0) -- (0,8.5);
        % Etichette asse X
        \foreach \x/\val in {0/0, 2.5/5, 5/10, 7.5/15, 10/20} \node[below] at (\x,0) {\small \val};

        % Barre (dal basso verso l'alto)
        \draw[bar] (0,0) rectangle (9.5, 1);   % 0-14
        \draw[bar] (0,1) rectangle (6.0, 2);   % 15-29
        \draw[highlight] (0,2) rectangle (4.5, 3) node[right, black] {\Large $S_{eq_i}$}; % 30-44
        \draw[bar] (0,3) rectangle (3.0, 4);   % 45-59
        \draw[bar] (0,4) rectangle (2.0, 5);   % 60-74
        \draw[bar] (0,5) rectangle (1.2, 6);   % 75-89
        \draw[bar] (0,6) rectangle (0.4, 7);   % 90-105
    \end{scope}
\end{tikzpicture}

\end{document}
```

Tale rappresentazione ci dà informazioni sulla **ricchezza della popolazione**:
- Nel grafico a **sinistra** abbiamo una **popolazione ricca**: le *condizioni di vita* ottimali fanno si che i *tassi di sopravvivenza* $s_{i}$ siano *alti* e, di conseguenza, per mantenere l'equilibrio *non è necessario generare molti figli*.
- Nel grafico a **destra** abbiamo una **popolazione povera**: vi è un'*altissima natalità*, ma i *tassi di sopravvivenza sono bassissimi* a causa di *condizioni di vita peggiori* (minor accesso a cure e cibo, etc.). L'equilibrio viene mantenuto *proprio grazie al gran numero di nascite*.

>[!note] OSSERVAZIONE
>In *entrambi i casi*, ogni classe d'età ha una **numerosità minore della classe precedente**.
>Questo implica $\lambda\geq 1$ e garantisce la *sopravvivenza della specie*.

Analizziamo meglio la questione dal punto di vista matematico.
Per farlo, consideriamo una popolazione in cui *esiste una classe* (intermedia) *di età più avanzata* che ha una **numerosità maggiore** di una *classe di età meno avanzata*, come la seguente:

```tikz
\usepackage{amsmath}
\usepackage{tikz}
\usetikzlibrary{arrows.meta, positioning, calc}

\begin{document}

\begin{tikzpicture}[
    y=0.7cm, % scala verticale per le barre
    bar/.style={fill=gray!70, draw=none},
    % Adattamento dello stile 'state' originale per le barre
    state_bar/.style={fill=gray, draw=gray, text=white, inner sep=2pt},
    axis/.style={thick, -{Stealth}}
]

    % --- 1. Struttura del Grafico ---
    % Titolo centrale superiore
    \node[above] at (5, 9) {\textbf{età}};

    % Assi (Asse Y a sinistra, Asse X in basso)
    \draw[axis] (0,0) -- (12,0) node[right, black] {\#};
    \draw[axis] (0,0) -- (0,9);
    
    % Etichette asse X (Valori numerici)
    \foreach \x/\val in {0/0, 5/5, 10/10} 
        \node[below, black] at (\x,0) {\small \val};

    % --- 2. Barre e Fasce d'Età ---
    % Dati e posizionamento barre (dal basso verso l'alto)
    \foreach \y/\length/\label in {
        0/10.5/0-14, 
        1/10.0/15-29, 
        2/6.5/30-44, 
        3/9.5/45-59, 
        4/8.0/60-74, 
        5/4.5/75-89, 
        6/1.5/90-105
    } {
        % Disegna la barra grigia
        \draw[bar] (0,\y) rectangle (\length, \y+1);
        
        % Etichetta fascia d'età (posizionata a destra dell'asse Y)
        \node[right, black] at (12.5, \y+0.5) {\label};
    }

    % Etichetta del grafico
    \node[above=0.2cm, black] at (5, 8) {\textbf{FEMMINE}};

\end{tikzpicture}

\end{document}
```

In tal caso abbiamo, per le classi d'età $30\text{-}44$ e $45\text{-}59$ (che indichiamo con $i$ e $i+1$ per comodità):
$$
S_{i}^{eq}(k) = \frac{x_{i}(k)}{N(k)}
$$
E anche
$$
S_{i+1}^{eq}(k) = \frac{x_{i+1}(k)}{N(k)} = S_{i+1}^{eq}(k+1) = \frac{x_{i+1}(k+1)}{N(k+1)} = \frac{s_{i}x_{i}(k)}{\lambda N(k)}
$$
Dove l'uguaglianza $S_{i+1}^{eq}(k) = S_{i+1}^{eq}(k+1)$ è data dal fatto che ci troviamo *all'equilibrio*.
Inoltre, come abbiamo detto, abbiamo:
$$
S_{i}^{eq}(k) < S_{i+1}^{eq}(k)
$$
Il che implica:
$$
\frac{x_{i}(k)}{N(k)} < \frac{s_{i}x_{i}(k)}{\lambda N(k)}
$$
Da cui:
$$
1 < \frac{s_{i}}{\lambda} \implies \lambda < s_{i} \leq 1
$$
>[!idea] CONSEGUENZA
>Avere una *classe d'età che ha numerosità maggiore di una classe più giovane* all'equilibrio **equivale** ad avere
>$$ \lambda < 1 $$
>E quindi una popolazione che **decresce esponenzialmente**.
# ESEMPI
## POPOLAZIONE DI CONIGLI
*"Un tizio lascia una coppia di conigli in un luogo circondato da mura. Quante coppie di conigli verranno prodotte in un anno, a partire da un’unica coppia, se ogni mese ciascuna coppia dà alla luce una nuova coppia che diventa produttiva a partire dal secondo mese?"*
(Fibonacci - Liber Abaci, 1202)

Chiamiamo:
- $x_{1}(k)$ numero di *coppie di conigli giovani* (età $<$ 1 mese).
- $x_{2}(k)$ numero di *coppie di conigli adulti* (età $>$ 1mese).

E ipotizziamo che i conigli *non muoiano entro un anno*.

```tikz
\usepackage{amsmath}
\usepackage{tikz}
\usetikzlibrary{positioning, arrows.meta}

\definecolor{mygrey}{RGB}{160, 160, 160} 

\begin{document}
\begin{tikzpicture}[
    auto,
    >= {Stealth[scale=1.2]},      
    node distance= 2.5cm,         
    % --- Stili dei Nodi ---
    state/.style={
        circle,
        draw=mygrey,
        fill=mygrey,
        text=white,
        minimum size=1cm,
        font=\Large\bfseries
    },
    % --- Stili delle Frecce ---
    flow/.style={
        ->,
        thick,
        draw=mygrey,
        text=black
    },
    inhibit/.style={
        {Bar[width=2.5mm, line width=1pt]}-{Stealth[scale=1.2]}, 
        thick,
        draw=mygrey,
        text=black,
        shorten <=2pt
    }
]

    % --- 1. Posizionamento dei Nodi ---
    \node[state] (n1) {1};
    \node[state, right=of n1] (n2) {2};

    % --- 2. Transizioni di Sopravvivenza (Orizzontali e Uscite) ---
    % Sopravvivenza da 1 a 2
    \draw[flow] (n1) to[bend right=30] node[above] {$s_1$} (n2);
    
    % Mortalità (frecce verso il basso)
    \draw[flow] (n1) -- ++(0,-1.5cm) node[right] {$1-s_1$};
    \draw[flow] (n2) -- ++(0,-1.5cm) node[right] {$1-s_2$};

    % --- 3. Archi di Fertilità (Ritorno e Auto-anello) ---
    
    % Auto-anello su nodo 1 (s0 * f1)
    \draw[inhibit] (n1) to[out=135, in=225, looseness=5] node[left, pos=0.5] {$s_0 f_1$} (n1);
    
    % Ritorno da nodo 2 a nodo 1 (s0 * f2)
    \draw[inhibit] (n2) to[bend right=45] node[above] {$s_0 f_2$} (n1);

\end{tikzpicture}
\end{document}
```

Allora
- $s_{0}f_{1}=0$ (solo i conigli adulti generano figli)
- $s_{0}f_{2}=1$ (assunzione nel testo)
- $s_{1}=s_{2}=1$ (i conigli non muoiono)

Abbiamo:
$$
\begin{align*}
x_{1}(k+1) &= x_{2}(k) \\ \\
x_{2}(k+1) &= x_{1}(k) + x_{2}(k)
\end{align*}
$$
Che scriviamo come:
$$
x(k+1) = Ax(k)
$$
con
$$
A = \begin{bmatrix}
0 & 1 \\
1 & 1
\end{bmatrix}
$$
Abbiamo:
$$
\begin{align*}
x(0) = \begin{pmatrix}
1 \\
0
\end{pmatrix} & & x(1) = Ax(0) = \begin{pmatrix}
0 \\
1
\end{pmatrix} & & x(2) = Ax(1) = \begin{pmatrix}
1 \\
1
\end{pmatrix} & & x(3) = Ax(2) = \begin{pmatrix}
1 \\
2
\end{pmatrix}
\end{align*}
$$
Chiamiamo $N(k)=x_{1}(k)+x_{2}(k)$ il numero di *coppie totali*.
Allora abbiamo:
$$
\begin{align*}
N(k+2) &= x_{1}(k+2) + x_{2}(k+2) = x_{2}(k+1) + x_{1}(k+1) + x_{2}(k+1) \\ \\
&= N(k+1) + x_{2}(k+1) = N(k+1) + x_{1}(k) + x_{2}(k) \\ \\
&= N(k+1) + N(k)
\end{align*}
$$
>[!note] NOTA
>Il numero di *coppie totali* segue la **serie di Fibonacci**.

Siano:
$$
\begin{align*}
S_{1}(k) = \frac{x_{1}(k)}{N(k)} & & S_{2}(k) = \frac{x_{2}(k)}{N(k)}
\end{align*}
$$
La **struttura d'età** tenderà all'**equilibrio** $S_{eq}$ :
$$
\begin{pmatrix}
S_{1}(k) \\
S_{2}(k)
\end{pmatrix} \longrightarrow \begin{pmatrix}
S_{eq,1} \\
S_{eq,2}
\end{pmatrix} = S_{eq}
$$
Con $S_{eq}$ *autovettore* di $A$, come visto sopra.
Sia $\lambda$ l'*autovalore associato* a $S_{eq}$. Per $k$ sufficientemente alto abbiamo:
$$
N(k+1) \approx \lambda N(k)
$$
>[!note] NOTA
>Abbiamo $\lambda>1$ visto che abbiamo visto che *la popolazione cresce*.

Calcoliamo l'autovalore a partire da $A$.
Cominciamo dal *polinomio caratteristico*:
$$
\det(zI-A) = \det \begin{bmatrix}
z & -1 \\
-1 & z-1
\end{bmatrix} = z(z-1)-1 = z^2 -z -1
$$
Le cui radici sono:
$$
z_{1,2} = \frac{1 \pm \sqrt{ 5 }}{2}
$$
E deduciamo quindi:
$$
\lambda = \frac{1+\sqrt{ 5 }}{2} = \varphi
$$
L'autovalore $\lambda$ è pari alla **sezione aurea**, che del resto è quanto ci aspettavamo, visto che $N(k)$ segue la *serie di Fibonacci*.

```tikz
\usepackage{amsmath}
\usepackage{pgfplots}

\definecolor{mygrey}{RGB}{120, 120, 120}

\begin{document}
\begin{tikzpicture}
\begin{axis}[
    axis lines = left,
    xlabel = {$k$},
    ylabel = {$N(k+1)/N(k)$},
    ymin = 0, ymax = 2.2,
    xmin = 0.8, xmax = 8.5,
    xtick = {1, 2, 3, 4, 5, 6, 7, 8},
    ytick = {0, 1, 2},
    y label style={at={(axis description cs:0.1,1)},anchor=south, rotate=-90},
    x label style={at={(axis description cs:1,0)},anchor=west},
    grid = none,
    width = 10cm,
    height = 7cm,
    % Stile per i punti e le linee tratteggiate
    every axis plot/.append style={mark=*, mygrey, thick}
]

    % Linea tratteggiata orizzontale (Autovalore dominante)
    \draw[dashed, thick, black] (axis cs:0.8, 1.618) -- (axis cs:8.5, 1.618) 
        node[left, pos=0, xshift=-0.5cm] {\Large $\lambda \approx 1.618$};

    % Dati del grafico (Stem plot)
    \addplot+[
        ycomb, 
        dashed, 
        mark options={solid, fill=mygrey}
    ] coordinates {
        (1, 1.0)
        (2, 2.0)
        (3, 1.5)
        (4, 1.66)
        (5, 1.6)
        (6, 1.625)
        (7, 1.615)
        (8, 1.618)
    };

\end{axis}
\end{tikzpicture}
\end{document}
```

Il corrispondente *autovettore* si calcola a partire da:
$$
AS_{eq} = \lambda S_{eq} \implies (\lambda I-A)S_{eq} = 0
$$
Per cui abbiamo:
$$
\begin{bmatrix}
\frac{1+\sqrt{ 5 }}{2} & -1 \\
-1 & \frac{1+\sqrt{ 5 }}{2} -1
\end{bmatrix}\begin{pmatrix}
S_{eq,1} \\
S_{eq,2}
\end{pmatrix} = 0 \implies S_{eq} = \begin{pmatrix}
0.382 \\
0.618
\end{pmatrix}
$$
Notiamo così che abbiamo anche:
$$
\frac{S_{eq,2}}{S_{eq,1}} \approx \varphi
$$
Il che si traduce nella seguente *rappresentazione nello spazio degli stati*:

```tikz
\usepackage{amsmath}
\usepackage{pgfplots}
\usetikzlibrary {arrows.meta}

\definecolor{mygrey}{RGB}{120, 120, 120}

\begin{document}
\begin{tikzpicture}
\begin{axis}[
    axis lines = middle,
    xlabel = {$x_1$},
    ylabel = {$x_2$},
    ymin = 0, ymax = 16,
    xmin = 0, xmax = 11,
    xtick = {5, 10},
    ytick = {5, 10, 15},
    x label style={at={(axis description cs:1,0)},anchor=west},
    y label style={at={(axis description cs:0,1)},anchor=south},
    width = 8cm,
    height = 10cm,
    clip = false
]

    % --- 1. Retta di pendenza Sezione Aurea (phi approx 1.618) ---
    \addplot[dashed, thick, black, domain=0:9.27] {1.618*x};

    % --- 2. Proiezioni tratteggiate ---
    \draw[dashed, thin, gray] (axis cs:0, 15) -- (axis cs:9.27, 15);
    \draw[dashed, thin, gray] (axis cs:9.27, 0) -- (axis cs:9.27, 15);

    % --- 3. Traiettoria (Punti x(k)) ---
    % I punti seguono approssimativamente la serie di Fibonacci
    \addplot[
        only marks, 
        mark=*, 
        mygrey, 
        mark size=2pt,
        nodes near coords,
        point meta=explicit symbolic,
        every node near coord/.append style={anchor=north, xshift=4pt, font=\small, black}
    ] table [meta=label] {
        x   y   label
        1   0   $x(0)$
        0   1   $x(1)$
        1   1   $x(2)$
        1   2   $x(3)$
        2   3   $x(4)$
        3   5   $x(5)$
        5   8   $x(6)$
        8   13  $x(7)$
    };

    % --- 4. Freccia di direzione sulla retta ---
    \draw[-{Stealth}, very thick, mygrey] (axis cs:6, 9.7) -- (axis cs:7.5, 12.1);

\end{axis}
\end{tikzpicture}
\end{document}
```
## POPOLAZIONE SALMONI
Consideriamo ora l'andamento di una *popolazione di salmoni*.
Siano:
- $x_{1}(k)$ il numero di *salmoni giovani* (tutti i giovani diventano adulti).
- $x_{2}(k)$ il numero di *salmoni adulti* (possono generare nuovi nati).

La situazione è descritta dal seguente grafo:
```tikz
\usepackage{amsmath}
\usepackage{tikz}
\usetikzlibrary{positioning, arrows.meta}

\definecolor{mygrey}{RGB}{160, 160, 160} 

\begin{document}
\begin{tikzpicture}[
    auto,
    >= {Stealth[scale=1.2]},      
    node distance= 2.8cm,         
    % --- Stili dei Nodi ---
    state/.style={
        circle,
        draw=mygrey,
        fill=mygrey,
        text=white,
        minimum size=1.1cm,
        font=\Large\bfseries
    },
    % --- Stili delle Frecce ---
    flow/.style={
        ->,
        thick,
        draw=mygrey,
        text=black,
        font=\Large
    },
    inhibit/.style={
        {Bar[width=2.5mm, line width=1pt]}-{Stealth[scale=1.2]}, 
        thick,
        draw=mygrey,
        text=black,
        shorten <=2pt,
        font=\Large
    }
]

    % --- 1. Posizionamento dei Nodi ---
    \node[state] (n1) {1};
    \node[state, right=of n1] (n2) {2};

    % --- 2. Transizioni ---
    % Sopravvivenza da 1 a 2 (valore 1)
    \draw[flow] (n1) to[bend right=30] node[above] {1} (n2);
    
    % Fertilità/Ritorno da 2 a 1 (valore a = 1.4)
    \draw[inhibit] (n2) to[bend right=35] node[above] {$a = 1.4$} (n1);

    % Uscita verso il basso dal nodo 2 (valore 1)
    \draw[flow] (n2) -- ++(0,-1.8cm) node[right, pos=0.6] {1};

\end{tikzpicture}
\end{document}
```

Abbiamo allora:
$$
\begin{align*}
x_{1}(k+1) &= ax_{2}(k) \\ \\
x_{2}(k+1) &= x_{1}(k) 
\end{align*}
$$
E diamo la *condizione iniziale*:
$$
x(0) = \begin{pmatrix}
x_{1}(0) \\
x_{2}(0)
\end{pmatrix} = \begin{pmatrix}
1 \\
0
\end{pmatrix}
$$
Abbiamo allora, per i *primi valori di* $k$:
$$
\begin{align*}
x(1) = \begin{pmatrix}
0 \\
1
\end{pmatrix} & & x(2) = \begin{pmatrix}
1.4 \\
0
\end{pmatrix} & & x(2) = \begin{pmatrix}
0 \\
1.4
\end{pmatrix} & & x(1) = \begin{pmatrix}
1.4^2 \\
0
\end{pmatrix}
\end{align*}
$$

```tikz
\usepackage{pgfplots}

\begin{document}
\begin{tikzpicture}[scale = 1.7]
    % Assi coordinati
    \draw[->, thick] (0,0) -- (4.5,0) node[right] {$x_1$};
    \draw[->, thick] (0,0) -- (0,4.5) node[above] {$x_2$};
    
    % Origine
    \node[below left] at (0,0) {0};

    % Tacche e etichette numeriche sugli assi
    \foreach \x in {1,2,3,4}
        \draw (\x, 1pt) -- (\x, -3pt) node[below] {\x};
    \foreach \y in {1,2,3,4}
        \draw (1pt, \y) -- (-3pt, \y) node[left] {\y};

    % Definizione dei punti (basata sulla progressione visiva dell'immagine)
    % Punti sull'asse x1 (indici pari)
    \filldraw (1.0, 0) circle (2pt) node[above] {$x(0)$};
    \filldraw (1.4, 0) circle (2pt) node[above] {$x(2)$};
    \filldraw (1.9, 0) circle (2pt) node[above] {$x(4)$};
    \filldraw (2.7, 0) circle (2pt) node[above] {$x(6)$};
    \filldraw (3.8, 0) circle (2pt) node[above] {$x(8)$};

    % Punti sull'asse x2 (indici dispari)
    \filldraw (0, 1.0) circle (2pt) node[right] {$x(1)$};
    \filldraw (0, 1.4) circle (2pt) node[right] {$x(3)$};
    \filldraw (0, 2.0) circle (2pt) node[right] {$x(5)$};
    \filldraw (0, 2.8) circle (2pt) node[right] {$x(7)$};
    \filldraw (0, 3.9) circle (2pt) node[right] {$x(9)$};

\end{tikzpicture}
\end{document}
```

>[!note] NOTA
>Osserviamo un **comportamento divergente** e la *numerosità delle classi* è *alternativamente nulla* al passare del tempo.

Cerchiamo ora di capire se *esiste una struttura di età di equilibrio*.
Abbiamo:
$$
\det(zI-A) = \det \begin{bmatrix}
z & -a \\
-1 & z
\end{bmatrix} = z^2 - a
$$
E quindi gli *autovalori* sono $\lambda_{1,2}=\pm \sqrt{ a }$.
Per $\lambda=+\sqrt{ a }$ calcoliamo l'*autovettore* risolvendo
$$
\begin{align*}
(\lambda I-A)S_{eq} &= 0 \\ \\
\begin{bmatrix}
\sqrt{ a } & -a \\
-1 & \sqrt{ a }
\end{bmatrix}\begin{pmatrix}
S_{eq,1} \\
S_{eq,2}
\end{pmatrix} &= 0
\end{align*}
$$
Troviamo quindi:
$$
S_{eq} = \begin{pmatrix}
0.542 \\
0.458
\end{pmatrix}
$$

```tikz
\usepackage{pgfplots}

\begin{document}
\begin{tikzpicture}[scale = 1.7]
    % Assi coordinati
    \draw[->, thick] (0,0) -- (4.5,0) node[right] {$x_1$};
    \draw[->, thick] (0,0) -- (0,4.5) node[above] {$x_2$};
    
    % Origine
    \node[below left] at (0,0) {0};

    % Tacche e etichette numeriche sugli assi
    \foreach \x in {1,2,3,4}
        \draw (\x, 1pt) -- (\x, -3pt) node[below] {\x};
    \foreach \y in {1,2,3,4}
        \draw (1pt, \y) -- (-3pt, \y) node[left] {\y};

    % Bisettrice tratteggiata (Distribuzione di equilibrio)
    \draw[dashed, thick] (0,0) -- (4.2,4.2);
    \node[rotate=45, anchor=south] at (2.5,2.5) {\textsf{distribuzione di equilibrio}};

    % Punti sull'asse x1 (indici pari)
    \filldraw (1.0, 0) circle (2pt) node[above] {$x(0)$};
    \filldraw (1.4, 0) circle (2pt) node[above] {$x(2)$};
    \filldraw (1.9, 0) circle (2pt) node[above] {$x(4)$};
    \filldraw (2.7, 0) circle (2pt) node[above] {$x(6)$};
    \filldraw (3.8, 0) circle (2pt) node[above] {$x(8)$};

    % Punti sull'asse x2 (indici dispari)
    \filldraw (0, 1.0) circle (2pt) node[right] {$x(1)$};
    \filldraw (0, 1.4) circle (2pt) node[right] {$x(3)$};
    \filldraw (0, 2.0) circle (2pt) node[right] {$x(5)$};
    \filldraw (0, 2.8) circle (2pt) node[right] {$x(7)$};
    \filldraw (0, 3.9) circle (2pt) node[right] {$x(9)$};

\end{tikzpicture}
\end{document}
```

>[!idea] CONCLUSIONE
>*Esiste* una *struttura d'età di equilibrio*, ma **non è stabile**.
>Se la *distribuzione iniziale* **non** è quella *di equilibrio*, allora tale struttura *non si instaura nel lungo periodo*.
## CATENE DI PRODUZIONE CON TEST QUALITA'
Consideriamo infine una *catena di produzione*, con test di *controllo di qualità*.
Siano:
- $x_{1}(k)$ il *numero di pezzi grezzi*.
- $x_{2}(k)$ il *numero di pezzi verniciati*.
- $x_{3}(k)$ il *numero di pezzi essiccati*.
- $x_{4}(k)$ il *numero di pezzi confezionati* e *disponibili in magazzino*.
- $u_{1}(k)$ il *numero di pezzi* non lavorati e *messi in lavorazione*.
- $u_{2}(k)$ il *numero di pezzi completati e venduti*.

Il grafo associato è il seguente:

```tikz
\usetikzlibrary{arrows.meta, positioning, calc}

\begin{document}
\begin{tikzpicture}[
    % Stili dei nodi
    fase/.style={circle, fill=gray!60, text=white, font=\bfseries, minimum size=1cm},
    esterno/.style={rectangle, draw=gray!60, thick, text=gray!60, font=\bfseries, minimum size=0.8cm},
    freccia/.style={-Stealth, thick, gray!80},
    etichetta/.style={font=\small\sffamily}
]

    % Nodi principali (Fasi)
    \node[esterno] (S) at (0,0) {1};
    \node[fase, right=1.2cm of S, label={[etichetta]above:grezzo}] (N1) {1};
    \node[fase, right=1.2cm of N1, label={[etichetta]above:verniciatura}] (N2) {2};
    \node[fase, right=1.2cm of N2, label={[etichetta]above:forno}] (N3) {3};
    \node[fase, right=1.5cm of N3, label={[etichetta]above:magazzino}] (N4) {4};
    \node[esterno, right=1.2cm of N4] (P) {2};

    % Archi diretti
    \draw[freccia] (S) -- node[above, black] {1} (N1);
    \draw[freccia] (N1) -- node[above, black] {1} (N2);
    \draw[freccia] (N2) -- node[above, black] {1} (N3);
    \draw[freccia] (N3) -- node[above, black] {$\alpha_{34}$} (N4);
    \draw[freccia] (N4) -- node[above, black] {1} (P);

    % Arco di feedback (da 3 a 2)
    \draw[freccia] (N3) to[bend left=45] node[below, black] {$\alpha_{32}$} (N2);

    % Uscita verso controllo qualità / scarto
    \coordinate (bottom) at ($(N3.south) + (0,-1cm)$);
    \draw[freccia] (N3.south) -- (bottom) 
        node[below, align=center, black, etichetta] {controllo\\qualità};
    
    % Formule a destra
    \node[right=0.2cm of bottom, anchor=west, black, etichetta] (F1) {$1 - \alpha_{32} - \alpha_{34} = s$ (scarto)};
    \node[below=0.8cm of F1, anchor=west, black, etichetta] {$\alpha_{32} + \alpha_{34} = 1 - s < 1$};

\end{tikzpicture}
\end{document}
```

In particolare abbiamo:
- $\alpha_{32}$ : frazione di *pezzi ri-lavorati*.
- $\alpha_{34}$ : frazione di *pezzi che superano il controllo qualità*.

Le equazioni che descrivono il sistema sono quindi:
$$
\begin{align*}
x_{1}(k+1) &= u_{1}(k) \\
x_{2}(k+1) &= x_{1}(k) + \alpha_{32}x_{3}(k) \\
x_{3}(k+1) &= x_{2}(k) \\
x_{4}(k+1) &= \alpha_{34}x_{3}(k) + x_{4}(k) - u_{2}(k)
\end{align*}
$$
Definendo:
$$
\begin{align*}
x(k) := \begin{pmatrix}
x_{1}(k) \\
x_{2}(k) \\
x_{3}(k) \\
x_{4}(k)
\end{pmatrix} & & u(k) := \begin{pmatrix}
u_{1}(k) \\
u_{2}(k)
\end{pmatrix}
\end{align*}
$$
Possiamo scrivere:
$$
x(k+1) = \begin{bmatrix}
0 & 0 & 0 & 0 \\
1 & 0 & \alpha_{32} & 0 \\
0 & 1 & 0 & 0 \\
0 & 0 & \alpha_{34} & 1
\end{bmatrix}x(t) + \begin{bmatrix}
1 & 0 \\
0 & 0 \\
0 & 0 \\
0 & -1
\end{bmatrix} u(k)
$$
Cerchiamo di capire ora *se* (e *quando*) *esiste* lo *stato di equilibrio* in corrispondenza di *ingressi di lavorazione e vendita costanti*.

Abbiamo un certo $\bar{u}=(\bar{u}_{1},\bar{u}_{2})$ e ci chiediamo se esiste $\bar{x}=(\bar{x}_{1}, \bar{x}_{2}, \bar{x}_{3}, \bar{x}_{4})$ tale per cui si abbia:
$$
\bar{x} = A\bar{x} + B\bar{u}
$$
Otteniamo il seguente sistema lineare:
$$
\begin{cases}
\bar{x}_{1} = \bar{u}_{1} \\
\bar{x}_{2} = \bar{x}_{1} + \alpha_{32}\bar{x}_{3} \\
\bar{x}_{3} = \bar{x}_{2} \\
\bar{x}_{4} = \alpha_{34}\bar{x}_{3} + \bar{x}_{4} - \bar{u}_{2}
\end{cases}
$$
Dalla *prima*, *seconda* e dalla *terza* troviamo:
$$
\bar{x}_{2} = \bar{x}_{3} = \frac{\bar{u}_{1}}{1-\alpha_{32}}
$$
Dalla *quarta* troviamo:
$$
\bar{x}_{3} = \frac{\bar{u}_{2}}{\alpha_{34}}
$$
Allora, affinchè l'equilibrio esista, deve valere:
$$
\frac{\bar{u}_{1}}{1-\alpha_{32}} = \frac{\bar{u}_{2}}{\alpha_{34}} \implies \bar{u}_{2} = \frac{\alpha_{34}}{1-\alpha_{32}}\bar{u}_{1}
$$
In tal caso, troviamo la **struttura d'equilibrio** seguente:
$$
\bar{x} = \begin{pmatrix}
\bar{u}_{1} \\
\frac{\bar{u}_{1}}{1-\alpha_{32}} \\
\frac{\bar{u}_{1}}{1-\alpha_{32}} \\
\bar{x}_{4}
\end{pmatrix}
$$

>[!note] NOTA
>Sembrerebbe che $\bar{x}_{4}$ sia un *parametro libero*.
>In realtà *non è proprio così*: vediamo di seguito perchè.

Calcoliamo $\bar{x}_{4}$ sapendo che abbiamo:
- $x_{4}(k+1) = \alpha_{34}x_{3}(k)+x_{4}(k)-\bar{u}_{2}$
- All'equilibrio $\begin{cases} \bar{x}_{1} = \bar{u}_{1} \\ \bar{x}_{2} = x_{1}(k) + \alpha_{32}\bar{x}_{3} \\ \bar{x}_{3} = \bar{x}_{2} \\ \bar{u}_{2} = \alpha_{34}\bar{x}_{3} \end{cases}$ 

Possiamo scrivere:
$$
\sum_{k=0}^N x_{4}(k+1) = \sum_{k=0}^N [\alpha_{34}x_{3}(k)+x_{4}(k)-\bar{u}_{2}]
$$
Che riscriviamo come segue:
$$
x_{4}(1) + \ldots x_{4}(N-1) + x_{4}(N) = \sum_{k=0}^{N-1}\alpha_{34}x_{3}(k) + [x_{4}(0)+x_{4}(1)+\ldots+x_{4}(N-1)] - \sum_{k=0}^{N-1}\bar{u}_{2}
$$
Possiamo allora *semplificare un po' di termini* e ottenere:
$$
x_{4}(N) = x_{4}(0) + \sum_{k=0}^{N-1} \alpha_{34}x_{3}(k) - \sum_{k=0}^{N-1}\bar{u}_{2}
$$
>[!idea] IDEA
>Ora, come *spesso si fa nell'analisi dei sistemi di controllo*, scriviamo questa relazione nella forma seguente (in termini della *distanza dal valore di equilibrio*).

$$
x_{4}(N) = x_{4}(0) +\alpha_{34}\sum_{k=0}^{N-1} (x_{3}(k)-\bar{x}_{3})
$$
Definiamo:
$$
d(k) := x_{3}(k) - \bar{x}_{3}
$$
E abbiamo quindi:
$$
x_{4}(N) = x_{4}(0) + \alpha_{34}\sum_{k=0}^{N-1} d(k)
$$
Per i *primi valori* di $k$ abbiamo:
$$
\begin{align*}
d(0) &= x_{3}(0) - \bar{x}_{3} \\ \\
d(1) &= x_{3}(1) - \bar{x}_{3} = x_{2}(0) - \bar{x}_{2} \\ \\
d(2) &= \ldots = x_{1}(0) - \bar{u}_{1} + \alpha_{32}d(0) \\ \\
d(3) &= \ldots = \alpha_{32}d(1) \\ \\
d(4) &= \ldots = \alpha_{32}(x_{1}(0)-\bar{u}_{1}) + \alpha_{32}^2d(0) \\ \\
d(5) &= \ldots = \alpha_{32}^2d(1)
\end{align*}
$$
Se definiamo $b:=x_{1}(0)-\bar{u}_{1}$, possiamo scrivere:
$$
\begin{align*}
\sum_{k=0}^{+\infty} d(k) &= \underbrace{ d(0) + \alpha_{32}d(0) + \alpha_{32}^2d(0) + \ldots + b + \alpha_{32}b + \alpha_{32}^2b + \dots }_{ \text{indici pari} } \underbrace{ + d(1) + \alpha_{32}d(1) + \alpha_{32}^2d(1) + \dots }_{ \text{indici dispari} } \\ \\
&= (b+d(0)) \sum_{i=0}^{+\infty}\alpha_{32}^i + d(1)\sum_{i=0}^{+\infty}\alpha_{32}^i = \\ \\
&= (b+d(0)) \frac{1}{a-\alpha_{32}} + d(1) \frac{1}{1-\alpha_{32}} = \\ \\
&= \frac{b+d(0)+d(1)}{1-\alpha_{32}}
\end{align*}
$$
Per cui troviamo infine:
$$
\begin{align*}
\lim_{ N \to +\infty } x_{4}(N) &= x_{4}(0) + \alpha_{34}\frac{b+d(0)+d(1)}{1-\alpha_{32}} \\ \\
&= x_{4}(0) + (x_{1}(0) +x_{2}(0) +x_{3}(0)) \frac{\alpha_{34}}{1-\alpha_{32}} - (3-\alpha_{32}) \frac{\alpha_{34}}{(1-\alpha_{32})^2} \bar{u}_{1} = \bar{x}_{4}
\end{align*}
$$
>[!note] NOTA
>E notiamo quindi che *in realtà* $\bar{x}_{4}$ **dipende dalle condizioni iniziali**.
>Inoltre, per essere sensato, dobbiamo avere $\bar{x}_{4}>0$.

Vediamo l'*andamento grafico* per un esempio in cui:
- $x_{1}(0)=x_{2}(0)=x_{3}(0)=0$
- $x_{4}(0)=100$
- $\alpha_{32}=0.1$, $\alpha_{34}=0.89$ e $s=0.01$

```tikz
\usepackage{pgfplots}
\usetikzlibrary{calc}

% Definizione colori stile MATLAB
\definecolor{matblue}{RGB}{0, 114, 189}

\begin{document}
\begin{tikzpicture}

% Impostazioni comuni per tutti i grafici
\pgfplotsset{
    every axis/.append style={
        width=8.5cm,
        height=5cm,
        xmin=0, xmax=25,
        xlabel={k},
        grid=both,
        grid style={line width=0.3pt, draw=gray!20},
        tick align=inside,
        tick label style={font=\small},
        title style={font=\bfseries\small},
        label style={font=\small},
        % Stile per il grafico a pettine (stem)
        only marks,
        mark size=1.5pt,
        scatter,
        scatter src=y,
        mark options={fill=matblue, draw=matblue},
    }
}

% --- STATO X1 (Top Left) ---
\begin{axis}[
    name=ax1,
    title={Stato $x_1$},
    ylabel={$x_1(k)$},
    ymin=0, ymax=11,
]
    \addplot+[ycomb, matblue, thick] coordinates {
        (0,0) (1,10) (2,10) (3,10) (4,10) (5,10) (6,10) (7,10) (8,10) (9,10) (10,10) (11,10) (12,10) (13,10) (14,10) (15,10) (16,10) (17,10) (18,10) (19,10) (20,10) (21,10) (22,10) (23,10) (24,10)
    };
    \node[draw, fill=white, anchor=north east, yshift = -10, font=\scriptsize, inner sep=2pt] at (rel axis cs:0.95,0.95) {$\bar{x}_1 = 10$};
\end{axis}

% --- STATO X2 (Top Right) ---
\begin{axis}[
    name=ax2,
    at={(ax1.outer east)}, anchor=outer west, xshift=1cm,
    title={Stato $x_2$},
    ylabel={$x_2(k)$},
    ymin=0, ymax=12,
]
    \addplot+[ycomb, matblue, thick] coordinates {
        (0,0) (1,0) (2,10) (3,10) (4,11.1) (5,11.1) (6,11.11) (7,11.11) (8,11.11) (9,11.11) (10,11.11) (11,11.11) (12,11.11) (13,11.11) (14,11.11) (15,11.11) (16,11.11) (17,11.11) (18,11.11) (19,11.11) (20,11.11) (21,11.11) (22,11.11) (23,11.11) (24,11.11)
    };
    \node[draw, fill=white, anchor=north east, yshift = -10, font=\scriptsize, inner sep=2pt] at (rel axis cs:0.95,0.95) {$\bar{x}_2 = 11.1111$};
\end{axis}

% --- STATO X3 (Middle Left) ---
\begin{axis}[
    name=ax3,
    at={(ax1.outer south)}, anchor=outer north, yshift=-0.5cm,
    title={Stato $x_3$},
    ylabel={$x_3(k)$},
    ymin=0, ymax=12,
]
    \addplot+[ycomb, matblue, thick] coordinates {
        (0,0) (1,0) (2,0) (3,10) (4,10) (5,11.1) (6,11.11) (7,11.11) (8,11.11) (9,11.11) (10,11.11) (11,11.11) (12,11.11) (13,11.11) (14,11.11) (15,11.11) (16,11.11) (17,11.11) (18,11.11) (19,11.11) (20,11.11) (21,11.11) (22,11.11) (23,11.11) (24,11.11)
    };
    \node[draw, fill=white, anchor=north east, yshift = -10, font=\scriptsize, inner sep=2pt] at (rel axis cs:0.95,0.95) {$\bar{x}_3 = 11.1111$};
\end{axis}

% --- STATO X4 (Middle Right) ---
\begin{axis}[
    name=ax4,
    at={(ax3.outer east)}, anchor=outer west, xshift=1cm,
    title={Stato $x_4$},
    ylabel={$x_4(k)$},
    ymin=0, ymax=110,
]
    \addplot+[ycomb, matblue, thick] coordinates {
        (0,100) (1,90) (2,81) (3,75) (4,71) (5,70) (6,69) (7,68.5) (8,68.2) (9,68.1) (10,68.1) (11,68.1) (12,68.1) (13,68.1) (14,68.1) (15,68.1) (16,68.1) (17,68.1) (18,68.1) (19,68.1) (20,68.1) (21,68.1) (22,68.1) (23,68.1) (24,68.1)
    };
    \node[draw, fill=white, anchor=north east, yshift = -10, font=\scriptsize, inner sep=2pt] at (rel axis cs:0.95,0.95) {$\bar{x}_4 = 68.1358$};
\end{axis}

% --- INGRESSO U1 (Bottom Left) ---
\begin{axis}[
    name=u1,
    at={(ax3.outer south)}, anchor=outer north, yshift=-0.5cm,
    title={Ingresso $u_1 = 10$},
    ylabel={$u_1(k)$},
    ymin=0, ymax=11,
]
    \addplot+[ycomb, matblue, thick] coordinates {
        (0,10) (1,10) (2,10) (3,10) (4,10) (5,10) (6,10) (7,10) (8,10) (9,10) (10,10) (11,10) (12,10) (13,10) (14,10) (15,10) (16,10) (17,10) (18,10) (19,10) (20,10) (21,10) (22,10) (23,10) (24,10)
    };
\end{axis}

% --- INGRESSO U2 (Bottom Right) ---
\begin{axis}[
    name=u2,
    at={(u1.outer east)}, anchor=outer west, xshift=1cm,
    title={Ingresso $u_2 = 9.8889$},
    ylabel={$u_2(k)$},
    ymin=0, ymax=11,
]
    \addplot+[ycomb, matblue, thick] coordinates {
        (0,10) (1,10) (2,10) (3,10) (4,10) (5,10) (6,10) (7,10) (8,10) (9,10) (10,10) (11,10) (12,10) (13,10) (14,10) (15,10) (16,10) (17,10) (18,10) (19,10) (20,10) (21,10) (22,10) (23,10) (24,10)
    };
\end{axis}

\end{tikzpicture}
\end{document}
```

>[!note] NOTA
>Il parametro $\alpha_{34}=0.89$ è *tarato per mantenere l'equilibrio*.
>Altrimenti:
>- Se $\alpha_{34}>0.89$, il *magazzino si svuoterebbe* (preleviamo più velocemente di quanto produciamo).
>- Se $\alpha_{34}<0.89$, il *magazzino si riempirebbe* (produciamo più velocemente di quanto preleviamo).

