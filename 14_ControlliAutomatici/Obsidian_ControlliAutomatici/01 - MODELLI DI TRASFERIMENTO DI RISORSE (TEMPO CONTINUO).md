# INDICE SEZIONE
- [ ] [[#MODELLI DI FLUSSO CONTINUO]]
      - [[#FLUSSI ENTRANTI NEL COMPARTIMENTO]]
      - [[#FLUSSI USCENTI DAL COMPARTIMENTO]]
      - [[#VARIAZIONE DELLE RISORSE NEL TEMPO]]    
- [ ] [[#SISTEMA LINEARE ASSOCIATO]]
      - [[#RICHIAMO SOLUZIONE DEL SISTEMA]]
- [ ] [[#ESEMPI CON SERBATOI]]
      - [[#SERBATOIO CON 2 VALVOLE]]
      - [[#SERBATOI A CASCATA]]
      - [[#ALTRO ESEMPIO]]    
- [ ] [[#ESEMPIO TRAFFICO AUTOMOBILISTICO]]
      - [[#PUNTI DI EQUILIBRIO DI UN SISTEMA DINAMICO]]
      - [[#PUNTI DI EQUILIBRIO DEL SISTEMA IN ESEMPIO]]
      - [[#CASO NON SIMMETRICO]]    
# MODELLI DI FLUSSO CONTINUO
Immaginiamo di avere una serie di **compartimenti** che *scambiano risorse* fra di *essi* e con l'*esterno*.

>[!def] COMPARTIMENTO
>Contenitore di una *risorsa*.

Possiamo rappresentare la situazione con un *grafo orientato*, come il seguente:

```tikz
\usepackage{amsmath}
\usetikzlibrary{positioning, arrows.meta, calc}

\definecolor{mygrey}{RGB}{120, 120, 120} 

\begin{document}
\begin{tikzpicture}[
    auto,
    >= {Stealth[scale=1.2]},      
    node distance= 2.2cm,         
    % --- Stili dei Nodi ---
    state/.style={
        circle,
        draw=mygrey,
        fill=mygrey,
        text=white,
        minimum size=1.2cm,
        font=\Large
    },
    box/.style={
        rectangle,
        draw=mygrey,
        fill=white,
        text=mygrey,
        thick,
        minimum size=0.9cm,
        font=\Large
    },
    % --- Stili delle Frecce ---
    flow/.style={
        ->,
        thick,
        draw=mygrey,
        text=mygrey
    },
    inhibit/.style={
        {Bar[width=2.5mm, line width=1pt]}-{Stealth[scale=1.2]}, 
        thick,
        draw=mygrey,
        text=mygrey,
        shorten <=2pt
    }
]

    % --- 1. Posizionamento dei Nodi ---
    \node[state] (i) {$i$};
    \node[state, above=2.5cm of i] (j) {$j$};
    \node[box, left=2.5cm of i] (l) {$l$};
    \node[box, right=2.5cm of i] (h) {$h$};

    % --- 2. Disegno degli Archi e delle Etichette ---
    
    % Da l a i
    \draw[flow] (l) -- node[above] {$\beta_{li}$} (i);
    
    % Da i ad h
    \draw[flow] (i) -- node[above] {$\beta_{ih}$} (h);
    
    % Uscita da i verso il basso a destra 
    \draw[flow] (i) -- ++(-45:1.8cm) node[pos=0.6, above right, xshift=-2pt] {$\alpha_{i0}$};
    
    % Arco da i a j 
    \draw[flow] (i) to[bend right=45] node[right] {$\alpha_{ij}$} (j);
    
    % Arco da j a i 
    \draw[flow] (j) to[bend right=55] node[left] {$\alpha_{ji}$} (i);
    
    % Arco di inibizione da j a i 
    \draw[inhibit] (j) to[bend right=15] node[right] {$\gamma_{ji}$} (i);
    
    % Auto-anello su i
    \draw[inhibit] (i) to[out=225, in=270, looseness=5.5] node[below] {$\gamma_{ii}$} (i);

\end{tikzpicture}
\end{document}
```

Nel grafo, i nodi $i$ e $j$ sono **compartimenti**.

Facciamo uso della seguente notazione:
- $x_{i}(t)$ : **risorsa** nel compartimento $i$-esimo (*variabile di stato*) al tempo $t$.
- "Blocco" $l$ : $u_{l}(t)$ rappresenta una *variabile indipendente* di **immissione** di risorsa.
- "Blocco" $h$ : $u_{h}(t)$ rappresenta una *variabile indipendente* di **prelievo** di risorsa.
- $\alpha,\beta,\gamma$ : parametri $\in \mathbb{R}^+$ che rappresentano lo **scambio** di risorse.
  - $\alpha_{ji}$ è un trasferimento da $j$ a $i$.
  - $\gamma_{ii}$ è un trasferimento da $i$ a $i$.

La *variazione* delle *risorse* in un *compartimento* è data dal **bilancio di flusso**.

>[!theorem] BILANCIO DI FLUSSO
>La velocità di variazione della risorsa è data dal *flusso entrante*, meno il *flusso uscente*.
>$$ \dot{x}_{i}(t) = \frac{dx_{i}(t)}{dt} = f_{i}^{(in)}(t) - f_{i}^{(out)}(t) $$

## FLUSSI ENTRANTI NEL COMPARTIMENTO
- $\beta_{li}u_{l}$ : Flussi dall'**esterno entranti** nel compartimento $i$-esimo.
- $\alpha_{ji}x_{j}(t)$ : Flussi **prelevati** e **trasferiti** da *altri compartimenti*, *verso* il *compartimento* $i$-esimo.
- $\gamma_{ji}x_{j}(t)$ : Flussi **generati** e **trasferiti** da *altri compartimenti*, verso il *compartimento* $i$-esimo.
- $\gamma_{ii}x_{i}(t)$ : Flussi **generati** e **accumulati** dal *compartimento* $i$-esimo.

Per cui complessivamente abbiamo:
$$
f_{i}^{(in)}(t) = \sum_{l=1}^p \beta_{li}u_{l}(t) + \sum_{j=1, j\ne i}^n (\alpha_{ji}+\gamma_{ji})x_{j}(t) + \gamma_{ii}x_{i}(t)
$$
Dove $n$ è il *numero di compartimenti* che compongono il sistema, e $p$ il numero di *variabili indipendenti* (immissione e prelievo).
## FLUSSI USCENTI DAL COMPARTIMENTO
- $\beta_{ih}u_{h}(t)$ : Flussi **uscenti** (indipendentemente dalla risorsa $x_{i}$).
- $\alpha_{ij}x_{i}(t)$ : Flussi **prelevati** e **trasferiti** *ad altri compartimenti*.
- $\alpha_{i0}x_{i}(t)$ : Flussi di **perdite** del compartimento $i$-esimo.

Per cui complessivamente abbiamo:
$$
f_{i}^{(out)}(t) = \sum_{h=1}^p \beta_{ih}u_{h}(t) + \sum_{j=0,j\ne i}^n \alpha_{ij}x_{i}(t)
$$
## VARIAZIONE DELLE RISORSE NEL TEMPO
Abbiamo visto che ogni compartimento è descritto da un'*equazione differenziale* data dal *bilancio di flusso*.
Ora che abbiamo visto tutti i flussi, possiamo scrivere:
$$
\begin{align*}
\dot{x}_{i}(t) &= \frac{dx_{i}(t)}{dt} = f_{i}^{(in)}(t) - f_{i}^{(out)}(t) \\
 &= \underbrace{ \sum_{j=1,j\ne i}^n (\alpha_{ji} + \gamma_{ji})x_{j}(t) + \gamma_{ii}x_{i}(t) - \sum_{j=0,j\ne i}^n \alpha_{ij}x_{i}(t) }_{ \text{flussi tra compartimenti} } + \underbrace{ \sum_{l=1}^p \beta_{li}u_{l}(t) - \sum_{h=1}^p \beta_{ih}u_{h}(t) }_{ \text{flussi con l'esterno} }
\end{align*}
$$
# SISTEMA LINEARE ASSOCIATO
>[!note] NOTA
>L'equazione differenziale trovata sopra **non** può essere *risolta singolarmente*: la sua soluzione **dipende** dalla soluzione alle equazioni associate gli *altri compartimenti*.

Abbiamo allora a che fare con un **sistema lineare** *di equazioni differenziali*.

Vediamo il modello relativo a $n$ compartimenti.
$$
\begin{align}
x(t) = \begin{bmatrix}
x_{1}(t) \\
x_{2}(t) \\
\vdots \\
x_{n}(t)
\end{bmatrix} & & u(t) = \begin{bmatrix}
u_{1}(t) \\
u_{2}(t) \\
\vdots \\
u_{p}(t)
\end{bmatrix}
\end{align}
$$
Possiamo considerare la seguente matrice:
$$
A = \begin{bmatrix}
a_{11} & a_{12} & \dots & a_{1n} \\
\vdots & \vdots & \ddots & \vdots \\
a_{n1} & a_{n2} & \dots & a_{nn}
\end{bmatrix}
$$
Dove:
- $a_{ij} =\alpha_{ji}+\gamma_{ji}$
- $a_{ii} =\gamma_{ii}-\sum_{j=0,j\ne i}^n\alpha_{ij}$

E anche la seguente:
$$
B = \begin{bmatrix}
\ddots & \cdots & \cdots & \cdots & \cdots \\
\vdots & \beta_{li} & \cdots & -\beta_{ih} & \cdots \\
\vdots & \vdots & \ddots & \vdots & \cdots \\
\vdots & -\beta_{jl} & \cdots & \beta_{hj} & \cdots \\
\vdots & \vdots & \vdots & \vdots & \ddots
\end{bmatrix}
$$
Definendo ora il vettore:
$$
\dot{x}(t) = \begin{bmatrix}
\dot{x}_{1}(t) \\
\vdots \\
\dot{x}_n(t)
\end{bmatrix}
$$
Possiamo finalmente scrivere in *modo compatto* il sistema di equazioni differenziali sopra descritto.
$$
\dot{x}(t) = Ax(t) + Bu(t)
$$
>[!note] NOTA
>Vale il *principio di sovrapposizione degli effetti*, e le matrici $A$ e $B$ sono **costanti**.
>Per quest'ultimo motivo, si dice che il sistema lineare è **tempo invariante**.
>

>[!attention] ATTENZIONE
>1. $A$ è sempre **quadrata** $n\times n$.
>2. $B$ deve avere *dimensioni* $n\times p$.
>   - $n$ *righe* : una per *ogni compartimento*. Ogni riga ci dice come il compartimento è influenzato dall'esterno.
>   - $p$ *colonne*: una per *ogni variabile esterna*. Ogni colonna ci dice come una variabile esterna influenza i compartimenti.
## RICHIAMO: SOLUZIONE DEL SISTEMA
Ricordiamo da algebra lineare che, dato un *sistema di $n$ equazioni differenziali ordinarie lineari*, lo scriviamo nella forma compatta grazie alla *matrice dei coefficienti* $A$
$$
\dot{x}(t) = Ax(t) + u(t)
$$
proprio come sopra.

>[!note] RICORDA
>- Se $u(t)=\underline{0}$ il sistema è **omogeneo**.
>- Se $f(t)\ne \underline{0}$ il sistema è **non omogeneo**: la soluzione è data dalla somma delle soluzioni al caso omogeneo e di una *soluzione particolare*.

In entrambi i casi, quindi, dobbiamo prima lavorare sul *sistema omogeneo associato*
$$
\dot{x}(t) = Ax(t)
$$
La *soluzione generale* al sistema è data da un **esponenziale**, spesso scritto nella forma matriciale compatta
$$
x(t) = e^{ At }
$$
Tale soluzione si basa sugli **autovalori** e **autovettori** di $A$.
In particolare è data da:
$$
x(t) = c_{1}\mathbf{v}_{1}e^{ \lambda_{1}t } + \ldots + c_{n}\mathbf{v}_{n}e^{ \lambda_{n}t }
$$
Dove:
- $\lambda_{i}$ è l'*autovalore* $i$-esimo di $A$.
- $\mathbf{v}_{i}$ è l'*autovettore* associato all'autovalore $\lambda_{i}$.
- $c_{i}$ sono coefficienti tali da soddisfare le *condizioni iniziali* dell'equazione. 
# ESEMPI CON SERBATOI
Per capire meglio, diamo un'occhiata a degli esempi.
## SERBATOIO CON 2 VALVOLE
Consideriamo un *serbatoio* di liquido, il cui livello è controllato da una *valvola di ingresso* e una *valvola di uscita*.

```tikz
\usepackage{amsmath} % Per i simboli matematici
\usetikzlibrary{shapes.geometric, positioning, calc, arrows.meta}

% Definizione del colore personalizzato per l'acqua
\definecolor{water}{RGB}{173,216,230} 

\begin{document}

\begin{tikzpicture}[
    >={Stealth[scale=1.2]}, % Imposta lo stile della punta delle frecce
    line width=0.8pt, % Spessore standard per tutte le linee
    dim_arrow/.style={<->, thick}, % Stile per le frecce delle dimensioni
    label_font/.style={font=\normalsize} % Dimensione del carattere per le etichette
]

    % --- Definizione dei parametri per facilitare le modifiche ---
    % Dimensioni del serbatoio
    \def\tankw{4.0}
    \def\tankh{6.0}
    \def\waterh{3.0} % Livello attuale dell'acqua h(t)
    \def\initwaterh{1.0} % Livello iniziale dell'acqua h(0)
    % Dimensioni e posizione dei tubi (ingresso)
    \def\pipew{0.4} % Diametro del tubo
    \def\pipexin{-2.5} % Coordinata X di inizio del tubo
    \def\pipey{6.0} % Coordinata Y dell'asse del tubo superiore
    \def\pipexout{1.3} % Coordinata X centrale dell'uscita del tubo nel serbatoio
    \def\valveposx{-0.5} % Coordinata X del centro della valvola di ingresso
    \def\pipeouty{5.0} % Coordinata Y della fine del tubo discendente

    % Nuovi parametri per la valvola di uscita
    \def\valveposxout{4.8} % Coordinata X del centro della valvola di uscita
    \def\valveposyout{0.2} % Coordinata Y centrale del tubo di uscita

    % --- 1. Disegno del serbatoio e dell'acqua ---
    % Profilo del serbatoio (lati e fondo)
    \draw [thick] (0, \tankh) -- (0,0) -- (\tankw,0) -- (\tankw, \tankh);

    % Riempimento dell'acqua nel serbatoio fino al livello h(t)
    \fill [water] (0,0) rectangle (\tankw, \waterh);
    
    % Riempimento dell'acqua nel tubo di uscita
    \fill [water] (\tankw, 0) rectangle (\valveposxout, \pipew);

    % --- 2. Disegno del rubinetto e della valvola di INGRESSO ---
    % Creazione di un tracciato pieno per l'intero sistema di tubi (orizzontale e discendente)
    \draw [fill=water, draw=none] (\pipexin, \pipey+\pipew/2) -- (\pipexout+\pipew/2, \pipey+\pipew/2) -- (\pipexout+\pipew/2, \pipeouty) -- (\pipexout-\pipew/2, \pipeouty) -- (\pipexout-\pipew/2, \pipey-\pipew/2) -- (\pipexin, \pipey-\pipew/2) -- cycle;
    
    % Posizionamento della valvola di ingresso (cerchio bianco per coprire il tubo dietro)
    \node (v) at (\valveposx, \pipey) [circle, draw, minimum size=0.8cm, thick, fill=white] {};
    \node at (\valveposx, 7.4) [label_font] {$u_2$};
    
    % Disegno della 'X' all'interno della valvola
    \draw [thick] (v.center) + (-0.28, 0.28) -- + (0.28, -0.28);
    \draw [thick] (v.center) + (0.28, 0.28) -- + (-0.28, -0.28);
    
    % Attuatore della valvola (asta e quadratino nero)
    \draw [thick] (v.north) -- +(0, 0.5);
    \node at ($(v.north) + (0, 0.5)$) [draw, fill=black, minimum size=0.18cm, anchor=south] {};
    
    % Disegno dei contorni dei tubi (fermandosi prima e ricominciando dopo la valvola)
    % Segmento a sinistra della valvola
    \draw (\pipexin, \pipey+\pipew/2) -- (\valveposx-0.4, \pipey+\pipew/2);
    \draw (\pipexin, \pipey-\pipew/2) -- (\valveposx-0.4, \pipey-\pipew/2);
    % Segmento a destra della valvola e curva discendente
    \draw (\valveposx+0.4, \pipey+\pipew/2) -- (\pipexout+\pipew/2, \pipey+\pipew/2) -- (\pipexout+\pipew/2, \pipeouty);
    \draw (\valveposx+0.4, \pipey-\pipew/2) -- (\pipexout-\pipew/2, \pipey-\pipew/2) -- (\pipexout-\pipew/2, \pipeouty);

    % --- 3. Disegno del flusso d'acqua IN ENTRATA ---
    % Colonna d'acqua piena tra l'uscita del tubo e la superficie dell'acqua
    \fill [water] (\pipexout-\pipew/2, \waterh) rectangle (\pipexout+\pipew/2, \pipeouty);
    % Disegno dei bordi della colonna di flusso
    \draw (\pipexout-\pipew/2, \pipeouty) -- (\pipexout-\pipew/2, \waterh);
    \draw (\pipexout+\pipew/2, \pipeouty) -- (\pipexout+\pipew/2, \waterh);
    
    % --- 4. Disegno della valvola e del tubo di USCITA ---
    % Posizionamento della valvola di uscita
    \node (vout) at (\valveposxout, \valveposyout) [circle, draw, minimum size=0.8cm, thick, fill=white] {};
    
    % Disegno della 'X' all'interno della valvola
    \draw [thick] (vout.center) + (-0.28, 0.28) -- + (0.28, -0.28);
    \draw [thick] (vout.center) + (0.28, 0.28) -- + (-0.28, -0.28);
    
    % Attuatore della valvola di uscita
    \draw [thick] (vout.north) -- +(0, 0.5);
    \node at ($(vout.north) + (0, 0.5)$) [draw, fill=black, minimum size=0.18cm, anchor=south] {};
    
    % Disegno dei contorni del tubo di uscita
    % Pipe interna (tra serbatoio e valvola)
    \draw (\tankw, \pipew) -- (\valveposxout-0.4, \pipew);
    \draw (\tankw, 0) -- (\valveposxout-0.4, 0);
    % Pipe esterna (dopo la valvola)
    \draw (\valveposxout+0.4, \pipew) -- (\tankw+1.8, \pipew);
    \draw (\valveposxout+0.4, 0) -- (\tankw+1.8, 0);
    
    % Etichetta \mu_1 sopra la valvola di uscita
    \node at (\valveposxout, 1.6) [label_font] {$u_1$};
    
    % Freccia del flusso in USCITA
    \draw [-{Stealth[scale=1.5]}] (\tankw+2.0, \valveposyout) -- ++(0.6, 0);

\end{tikzpicture}

\end{document}
```

Il grafo associato è il seguente (assumiamo coefficienti semplici per gli scambi con l'esterno):

```tikz
\usepackage{amsmath}
\usetikzlibrary{positioning, arrows.meta}

\begin{document}
\begin{tikzpicture}[
    auto,
    >= {Stealth[scale=1.2]},      % Stile della punta delle frecce
    node distance= 2cm,           % Distanza orizzontale tra i nodi
    thick,                        % Spessore generale delle linee
    % Stile per i blocchi quadrati (sorgente/pozzo)
    box/.style={
        rectangle,
        draw=black,
        minimum size=1.2cm,
        font=\Large
    },
    % Stile per il blocco circolare (stato)
    state/.style={
        circle,
        draw=black,
        minimum size=1.2cm,
        font=\Large
    }
]

    % --- Posizionamento dei Nodi ---
    % Nodo 1: Sorgente (mu_2)
    \node[box, label=above:{$u_2$}] (source) {2};
    
    % Nodo 2: Stato (x_1)
    \node[state, right=of source, label=above:{$x_1$}] (x1) {1};
    
    % Nodo 3: Pozzo (mu_1)
    \node[box, right=of x1, label=above:{$u_1$}] (sink) {1};

    % --- Frecce e pesi ---
    % Da mu_2 a x_1
    \draw[->] (source) -- node[above] {1} (x1);
    
    % Da x_1 a mu_1
    \draw[->] (x1) -- node[above] {1} (sink);

\end{tikzpicture}
\end{document}
```

Allora l'equazione che descrive il sistema è:
$$
\dot{x}_{1}(t) = u_{2}(t) - u_{1}(t) = Ax_{1} + Bu
$$
Dove la matrice $A$ è:
$$
A = \begin{bmatrix}
0
\end{bmatrix}
$$
Infatti *non avvengono scambi con altri compartimenti*.
La matrice $B$ è invece:
$$
B = \begin{bmatrix}
-1 & 1
\end{bmatrix}
$$
### IMPOSTAZIONE DEL SISTEMA
Immaginiamo ora che l'*altezza* $h(t)$ del liquido nel serbatoio sia costantemente misurata da un sensore e di **volerla controllare** per farle raggiungere il **valore desiderato** $h_{\text{des}}$.

Sia $u(t):= u_{2}(t)-u_{1}(t)$ il **flusso netto** di liquido.

>[!idea] IDEA
>Un modo intuitivo di *controllare il sistema* è il seguente:
>$$ u(t) := K(h_{\text{des}}-h(t)) $$
>Cioè *regoliamo il flusso netto* in funzione dello **scostamento dal valore desiderato**.

Se chiamiamo $S$ la sezione del serbatoio, abbiamo:
$$
\dot{x}_{1}(t) = K\left( h_{\text{des}} - \frac{x_{1}(t)}{S} \right) = \frac{K}{S}(Sh_{\text{des}} - x_{1}(t))
$$
A questo punto definiamo *per comodità*:
$$
\tilde{x}_{1}(t) := x_{1}(t) - Sh_{\text{des}}
$$
Possiamo così lavorare sull'equazione semplificata, in cui compare $\tilde{x}_{1}$ (lo *scostamento*):
$$
\dot{\tilde{x}}_{1}(t) = \dot{x}_{1}(t) = -\frac{K}{S}\tilde{x}_{1}(t)
$$
La cui soluzione è:
$$
\tilde{x}_{1}(t) = \tilde{x}_{1}(0) e^{ -Kt/S }
$$
Diamo ora un'*interpretazione del parametro* $K$:

```tikz
\usepackage{amsmath}

% Definizione del colore blu simile a quello del pennarello nello schizzo
\definecolor{sketchblue}{RGB}{12, 105, 168} 

\begin{document}
\begin{tikzpicture}[
    >=stealth,                  % Stile delle punte delle frecce
    color=sketchblue,           % Colore globale per linee
    every node/.style={color=sketchblue}, % Colore globale per i testi
    declare function={
        % Definiamo matematicamente le funzioni esponenziali (base = 2)
        % L'equazione è f(t) = 2 * e^{-k * t}
        grow(\t) = 2 * exp(0.15 * \t);   % Per k < 0 (es: k = -0.15)
        decay(\t) = 2 * exp(-1.2 * \t);  % Per k > 0 (es: k = 1.2)
    }
]

    % --- Assi ---
    \draw[->, color = black, line width=1.5pt] (0, -0.3) -- (0, 4.5) node[left, color = black, font=\Large] {$\widetilde{x}_1$};
    \draw[->, color = black, line width=1.5pt] (0, 0) -- (5.5, 0) node[below, color = black, font=\Large] {$t$};
    
    % Etichetta dell'origine
    \node[below, color = black, font=\Large] at (0,-0.3) {$0$};

    % --- Curve ---
    % Punto iniziale
    \coordinate (start) at (0, 2);
    \fill (start) circle (2.5pt);

    % 1. Caso k < 0 (crescita esponenziale)
    \draw[line width=1.5pt, domain=0:4.6, samples=50] 
        plot (\x, {grow(1.2*\x)}) node[right, font=\Large] {$k<0$};

    % 2. Caso k = 0 (linea orizzontale costante)
    \draw[line width=1.5pt] (start) -- (4.6, 2) node[right, font=\Large] {$k=0$};

    % 3. Caso k > 0 (decadimento esponenziale)
    \draw[line width=1.5pt, domain=0:4.6, samples=50] 
        plot (\x, {decay(\x)}) node[above, font=\Large, yshift=2pt] {$k>0$};

\end{tikzpicture}
\end{document}
```

Nel grafico vediamo i 3 possibili casi per il valore di $K$.

>[!note] INTERPRETAZIONE PARAMETRO $K$
>1. $K>0$. Abbiamo un *esponenziale decrescente*: lo scostamento *si riduce progressivamente* ed il sistema si *stabilizza al valore desiderato* $h_{\text{des}}$.
>2. $K=0$. *Non stiamo applicando alcun controllo*: il flusso netto è nullo ed il livello del liquido rimane invariato.
>3. $K<0$. Abbiamo un *esponenziale crescente*: abbiamo costruito un controllore "*al contrario*", che *aumenta lo scostamento*, anzichè diminuirlo. In questo caso il sistema è **instabile**.

Ne consegue che dobbiamo scegliere $K>0$ per ottenere un sistema di controllo corretto.
### AZIONE SULLE VALVOLE
A questo punto ricordiamo che il nostro sistema di controllo ha **due** valvole; tuttavia, per semplificare lo studio del sistema, abbiamo definito una *variabile ausiliaria* $u(t)$ **singola**.

>[!note] NOTA
>Ciò significa che il nostro *sistema decisionale* ha ora *un solo parametro d'uscita*: siamo passati da *due* a **un solo grado di libertà**.

In tal modo abbiamo semplificato la logica di controllo: in ogni istante $t$ il sistema determina che il *flusso netto* deve essere un certo valore $u(t)$, e le *valvole* verranno *regolate di conseguenza*.
C'è però un problema:

>[!idea] DOMANDA
> **Come** regoliamo le *due* valvole per ottenere il valore richiesto di flusso?

Il modo più semplice è il seguente:
$$
\begin{align*}
u(t) > 0 \implies \begin{cases}
u_{2}(t) = u(t) \\
u_{1}(t) = 0
\end{cases} & & u(t) < 0 \implies \begin{cases}
u_{2}(t) = 0 \\
u_{1}(t) = -u(t)
\end{cases}
\end{align*}
$$
## SERBATOI A CASCATA
Consideriamo ora un esempio un po' più complesso: due serbatoi posti a cascata, in cui il livello del liquido è ancora una volta regolato da *una valvola di ingresso* e *una valvola di uscita*, come in figura.

```tikz
\usepackage{amsmath}
\usetikzlibrary{arrows.meta, positioning, calc}

% Stesso colore dell'acqua usato in precedenza
\definecolor{water}{RGB}{173,216,230}

% Definizione di un comando personalizzato per disegnare le valvole
\newcommand{\bowtievalve}[3]{
    % #1: coordinata X, #2: coordinata Y, #3: etichetta
    \coordinate (V) at (#1, #2);
    % Triangolo sinistro
    \draw[thick, fill=white, line join=round] (V) -- ++(-0.4, 0.3) -- ++(0, -0.6) -- cycle;
    % Triangolo destro
    \draw[thick, fill=white, line join=round] (V) -- ++(0.4, 0.3) -- ++(0, -0.6) -- cycle;
    % Asta dell'attuatore
    \draw[thick] (V) -- ++(0, 0.45);
    % Attuatore (quadratino nero)
    \filldraw[black] (#1-0.1, #2+0.45) rectangle (#1+0.1, #2+0.6);
    % Etichetta testuale
    \node[above, font=\Large] at (#1, #2+0.65) {$#3$};
}

\begin{document}
\begin{tikzpicture}[
    >= {Stealth[scale=1.3]}, % Frecce più grandi e visibili
    line width=1.2pt         % Spessore di default delle linee
]

    % --- 1. Riempimento dell'Acqua (Layer inferiore) ---
    % Tubo di ingresso in alto
    \fill[water] (-0.2, 6.8) rectangle (1.5, 7.2);
    \fill[water] (1.1, 5.0) rectangle (1.5, 6.8); % Flusso che cade nel serbatoio 1

    % Serbatoio 1
    \fill[water] (0, 3.5) rectangle (3, 5.0);

    % Tubo di collegamento (dal 1 al 2)
    \fill[water] (3, 3.6) rectangle (4.2, 4.0);
    \fill[water] (3.8, 1.5) rectangle (4.2, 3.6); % Flusso che cade nel serbatoio 2

    % Serbatoio 2
    \fill[water] (3.5, 0) rectangle (6.5, 1.5);

    % Tubo di uscita
    \fill[water] (6.5, 0.1) rectangle (8.5, 0.5);


    % --- 2. Bordi dei Serbatoi e dei Tubi ---
    % Tubo di ingresso in alto
    \draw (-0.2, 7.2) -- (1.5, 7.2);
    \draw (-0.2, 6.8) -- (1.5, 6.8);

    % Serbatoio 1
    \draw (0, 6.5) -- (0, 3.5) -- (3, 3.5) -- (3, 3.6); % Bordo sinistro e fondo fino al buco
    \draw (3, 4.0) -- (3, 6.5);                         % Bordo destro sopra al buco
    \draw[thin] (0, 5.0) -- (3, 5.0);                   % Linea di superficie dell'acqua

    % Tubo di collegamento 
    \draw (3, 4.0) -- (4.2, 4.0);
    \draw (3, 3.6) -- (4.2, 3.6);

    % Serbatoio 2
    \draw (3.5, 3.0) -- (3.5, 0) -- (6.5, 0) -- (6.5, 0.1); % Bordo sinistro e fondo fino al buco
    \draw (6.5, 0.5) -- (6.5, 3.0);                         % Bordo destro sopra al buco
    \draw[thin] (3.5, 1.5) -- (6.5, 1.5);                   % Linea di superficie dell'acqua

    % Tubo di uscita
    \draw (6.5, 0.5) -- (8.5, 0.5);
    \draw (6.5, 0.1) -- (8.5, 0.1);


    % --- 3. Posizionamento delle Valvole ---
    % Utilizziamo il comando definito all'inizio
    \bowtievalve{0.5}{7.0}{u_1(t)}
    \bowtievalve{7.5}{0.3}{u_2(t)}


    % --- 4. Frecce di Flusso e Etichette ---
    % Frecce che indicano la direzione dell'acqua
    \draw[->] (1.3, 6.7) -- (1.3, 5.5);
    \draw[->] (4.0, 3.5) -- (4.0, 2.0);
    \draw[->] (8.7, 0.3) -- (9.5, 0.3);

    % Etichette degli stati nei serbatoi
    \node at (1.5, 4.25) {\LARGE $x_1(t)$};
    \node at (5, 0.75) {\LARGE $x_2(t)$};

\end{tikzpicture}
\end{document}
```

Il grafo associato è il seguente:

```tikz
\usepackage{amsmath}
\usetikzlibrary{positioning, arrows.meta}

\begin{document}
\begin{tikzpicture}[
    auto,
    >= {Stealth[scale=1.2]},      % Stile della punta delle frecce
    node distance= 2cm,           % Distanza orizzontale tra i nodi
    thick,                        % Spessore generale delle linee
    % Stile per i blocchi quadrati (sorgente/pozzo)
    box/.style={
        rectangle,
        draw=black,
        minimum size=1.2cm,
        font=\Large
    },
    % Stile per i blocchi circolari (stati)
    state/.style={
        circle,
        draw=black,
        minimum size=1.2cm,
        font=\Large
    }
]

    % --- Posizionamento dei Nodi ---
    % Nodo 1: Sorgente (mu_1)
    \node[box, label=above:{$u_1(t)$}] (source) {1};
    
    % Nodo 2: Stato (x_1)
    \node[state, right=of source, label=above:{$x_1(t)$}] (x1) {1};
    
    % Nodo 3: Stato (x_2)
    \node[state, right=of x1, label=above:{$x_2(t)$}] (x2) {2};
    
    % Nodo 4: Pozzo (mu_2)
    \node[box, right=of x2, label=above:{$u_2(t)$}] (sink) {2};

    % --- Frecce e pesi ---
    % Da mu_1 a x_1
    \draw[->] (source) -- node[above] {1} (x1);
    
    % Da x_1 a x_2
    \draw[->] (x1) -- node[above] {$\alpha_{12}$} (x2);
    
    % Da x_2 a mu_2
    \draw[->] (x2) -- node[above] {1} (sink);

\end{tikzpicture}
\end{document}
```

Le equazioni che *descrivono il sistema* sono:
$$
\begin{align*}
\dot{x}_{1}(t) &= -\alpha_{12}x_{1}(t) + u_{1}(t) \\
\dot{x}_{2}(t) &= \alpha_{12}x_{1}(t) - u_{2}(t)
\end{align*}
$$
Nel problema in analisi, le matrici $A$ e $B$ sono *entrambe quadrate*, infatti abbiamo *tanti stati quanti ingressi e uscite*.
$$
\begin{bmatrix}
\dot{x}_{1}(t) \\
\dot{x}_{2}(t)
\end{bmatrix} = \underbrace{ \begin{bmatrix}
-\alpha_{12} & 0 \\
\alpha_{12} & 0
\end{bmatrix} }_{ A } \begin{bmatrix}
x_{1}(t) \\
x_{2}(t)
\end{bmatrix} + \underbrace{ \begin{bmatrix}
1 & 0 \\
0 & -1
\end{bmatrix} }_{ B } \begin{bmatrix}
u_{1}(t) \\
u_{2}(t)
\end{bmatrix}
$$
$$
\dot{x}(t) = Ax(t) = Bu(t)
$$
Possiamo definire la seguente funzione:
$$
y(t) = x_{1}(t) + x_{2}(t)
$$
Che rappresenta la **quantità totale** di liquido nei due serbatoi in ogni istante.
Abbiamo allora:
$$
\begin{align*}
\dot{y}(t) &= \dot{x}_{1}(t) + \dot{x}_{2}(t) \\
 &= -\alpha_{12}x_{1}(t) + u_{1}(t) + \alpha_{12}x_{1}(t) - u_{2}(t) \\
 &= u_{1}(t) - u_{2}(t)
\end{align*}
$$
### CASO PARTICOLARE: SISTEMA ISOLATO
>[!note] NOTA
>Notiamo subito che, se $u_{1}(t)=0$, allora
>$$ x_{1}(t) = x_{1}(0)e^{ -\alpha_{12}t } $$

Inoltre, se $u_{1}(t)=u_{2}(t)=0$ (**sistema isolato**), allora:
$$
\begin{align*}
\dot{y}(t) = 0 & & \dot{y}(t) = 0 \text{ , }y(t) = y(0) 
\end{align*}
$$
>[!note] NOTA
>In tal caso la *quantità di risorse* rimane *costante* e viene semplicemente **ridistribuita** tra i due serbatoi, come possiamo vedere anche dal grafico in figura.

```tikz
\usepackage{pgfplots}

\begin{document}

\begin{tikzpicture}
    \begin{axis}[
        width=11cm, 
        height=7.5cm,           % Proporzioni simili a quelle dell'immagine originale
        xmin=0, xmax=5,         % Limiti asse X
        ymin=0, ymax=10,        % Limiti asse Y
        xlabel={Tempo},
        ylabel={Variabili di stato},
        grid=major,             % Abilita la griglia principale
        grid style={line width=0.3pt, draw=gray!40}, % Stile griglia
        xtick={0,0.5,1,1.5,2,2.5,3,3.5,4,4.5,5}, % Tick personalizzati su X
        ytick={0,2,4,6,8,10},   % Tick personalizzati su Y
        tick align=inside,      % I tick puntano verso l'interno come in MATLAB
        enlarge x limits=false, % Evita margini extra agli estremi dell'asse X
        enlarge y limits=false, % Evita margini extra agli estremi dell'asse Y
        legend style={
            at={(0.75,0.6)},    % Posiziona la legenda all'interno del grafico
            anchor=west,
            draw=black,         % Bordo nero della legenda
            nodes={scale=0.9, transform shape} % Dimensione del testo della legenda
        }
    ]
    
        % --- Curva x1(t) (Decadimento esponenziale) ---
        \addplot[
            color=blue, 
            line width=1.5pt,   % Linea spessa e continua
            domain=0:5, 
            samples=100         % 100 punti per una curva morbida
        ] 
        {10*exp(-x)};
        \addlegendentry{$x_1(t)$}

        % --- Curva x2(t) (Crescita asintotica) ---
        \addplot[
            color=red, 
            line width=1.5pt,   
            dashed,             % Linea tratteggiata
            dash pattern=on 5pt off 4pt, % Personalizza la lunghezza del tratto
            domain=0:5, 
            samples=100
        ] 
        {10*(1-exp(-x))};
        \addlegendentry{$x_2(t)$}

    \end{axis}
\end{tikzpicture}

\end{document}
```

Quella sopra è una rappresentazione dell'*andamento temporale* delle variabili di stato.
Spesso si osservano anche le rappresentazioni nello **spazio di stato**, come la seguente, relativa alla stessa situazione:

```tikz
\usepackage{pgfplots}
\usetikzlibrary{decorations.markings, arrows.meta}

\begin{document}

\begin{tikzpicture}
    \begin{axis}[
        width=10cm, 
        height=7cm,             % Proporzioni simili a quelle dell'immagine originale
        xmin=0, xmax=10,        % Limiti asse X
        ymin=0, ymax=10,        % Limiti asse Y
        xlabel={$x_1(t)$},      % Etichetta asse X
        ylabel={$x_2(t)$},      % Etichetta asse Y
        grid=major,             % Abilita la griglia principale
        grid style={line width=0.3pt, draw=gray!40}, % Stile della griglia
        xtick={0,1,2,3,4,5,6,7,8,9,10}, % Tick personalizzati su X (di 1 in 1)
        ytick={0,2,4,6,8,10},   % Tick personalizzati su Y (di 2 in 2)
        tick align=inside,      % I tick puntano verso l'interno (stile MATLAB)
        enlarge x limits=false, % Evita margini extra agli estremi dell'asse X
        enlarge y limits=false  % Evita margini extra agli estremi dell'asse Y
    ]
    
        % Traccia la retta e aggiunge la freccia centrale
        \addplot[
            color=black, 
            line width=1.5pt,   % Spessore della linea
            % Configurazione per inserire la freccia a metà della linea
            postaction={decorate},
            decoration={
                markings,
                % Posiziona una freccia (Stealth ingrandita) a metà esatta (0.5) del segmento
                mark=at position 0.5 with {\arrow{Stealth[scale=2.5]}} 
            }
        ] coordinates {
            (10,0)              % Punto di partenza
            (0,10)              % Punto di arrivo
        };

    \end{axis}
\end{tikzpicture}

\end{document}
```
### STUDIO CON ALGEBRA LINEARE
Proviamo ora a studiare il sistema con gli strumenti dell'*algebra lineare*.
Sappiamo che la quantità totale di liquido è data da:
$$
y(t) = x_{1}(t) + x_{2}(t) = \begin{bmatrix}
1 & 1
\end{bmatrix}x(t)
$$
>[!note] NOTA
>In *generale*, si ha:
$$ y(t) = Cx(t) + Du(t) $$

Dall'equazione sopra ricaviamo:
$$
\begin{align*}
\dot{y}(t) &= \begin{bmatrix}
1 & 1
\end{bmatrix} \dot{x}(t) = \begin{bmatrix}
1 & 1
\end{bmatrix} (Ax(t) + Bu(t)) \\
&= \begin{bmatrix}
0 & 0
\end{bmatrix} x(t) + \begin{bmatrix}
1 & 1
\end{bmatrix} Bu(t) \\
&= \begin{bmatrix}
1 & 1
\end{bmatrix} Bu(t)
\end{align*}
$$
Notiamo che il risultato è *in accordo* con quanto visto sopra: la *variazione della quantità* di liquido *dipende solo dagli scambi con l'esterno*, e se $u(t)=\underline{0}$ abbiamo $\dot{y}(t)=\underline{0}$.

Ora proseguiamo con altre osservazioni interessanti.

>[!idea] OSSERVAZIONE IMPORTANTE
>Abbiamo
>$$ \begin{bmatrix} 1 & 1 \end{bmatrix}A = \begin{bmatrix} 0 & 0 \end{bmatrix} $$
>Cioè:
>$$ \mathbb{1}^TA = 0\mathbb{1}^T $$
>Il che significa che $\lambda=0$ è **autovalore sinistro** per $A$.
>E quindi $\mathbb{1}^T$ è *autovettore sinistro*.

>[!note] RICORDA
>- Se $Av=\lambda v$, $v$ è **autovettore destro** (e $\lambda$ autovalore destro).
>- Se $w^TA=\lambda w^T$, $w$ è **autovettore sinistro** (e $\lambda$ autovalore sinistro).

Avevamo trovato (nel caso del sistema isolato):
$$
\dot{y}(t) = (\mathbb{1}^TA)x(t)
$$
E abbiamo scoperto che $\lambda=0$ è *autovalore associato all'autovettore* $\mathbb{1}$.
Allora possiamo scrivere
$$
\dot{y}(t) = \lambda x(t)
$$
Nel nostro caso $\lambda=0$, ma vale comunque la pena *discuterne il segno*:

>[!note] SIGNIFICATO DI $\lambda$
>- $\lambda<0$ : In tal caso la *derivata* sarebbe *negativa*, per cui il sistema starebbe *complessivamente perdendo massa* nel tempo.
>- $\lambda>0$ : In tal caso la *derivata* sarebbe *positiva*, per cui il sistema starebbe *complessivamente acquisendo massa* nel tempo.
>- $\lambda=0$ : La *derivata* è *nulla* e **la massa del sistema si conserva**.
## ALTRO ESEMPIO
Consideriamo un ultimo esempio.
```tikz
\usepackage{amsmath}
\usetikzlibrary{arrows.meta, positioning, calc}

% Stesso colore dell'acqua usato in precedenza
\definecolor{water}{RGB}{173,216,230}

% Definizione del comando personalizzato per disegnare le valvole a farfalla
\newcommand{\bowtievalve}[3]{
    % #1: coordinata X, #2: coordinata Y, #3: etichetta
    \coordinate (V) at (#1, #2);
    % Triangoli della valvola
    \draw[thick, fill=white, line join=round] (V) -- ++(-0.4, 0.3) -- ++(0, -0.6) -- cycle;
    \draw[thick, fill=white, line join=round] (V) -- ++(0.4, 0.3) -- ++(0, -0.6) -- cycle;
    % Attuatore (asta e quadratino nero)
    \draw[thick] (V) -- ++(0, 0.45);
    \filldraw[black] (#1-0.1, #2+0.45) rectangle (#1+0.1, #2+0.6);
    % Etichetta testuale
    \node[above, font=\Large] at (#1, #2+0.65) {$#3$};
}

\begin{document}
\begin{tikzpicture}[
    >= {Stealth[scale=1.3]}, % Frecce grandi e ben visibili
    line width=1.2pt         % Spessore di default delle linee
]

    % --- 1. Riempimento dell'Acqua ---
    % Serbatoio 3 (In alto)
    \fill[water] (3.5, 5.0) rectangle (6.5, 6.5);
    % Serbatoio 1 (Medio-sinistra)
    \fill[water] (0.0, 1.0) rectangle (3.0, 2.5);
    % Serbatoio 2 (In basso-destra)
    \fill[water] (6.0, -3.0) rectangle (9.0, -1.5);

    % --- 2. Bordi dei Serbatoi (Forma a "U") e Superfici ---
    % Serbatoio 3
    \draw (3.5, 8.0) -- (3.5, 5.0) -- (6.5, 5.0) -- (6.5, 8.0);
    \draw[thin] (3.5, 6.5) -- (6.5, 6.5); % Pelo dell'acqua
    
    % Serbatoio 1
    \draw (0.0, 4.0) -- (0.0, 1.0) -- (3.0, 1.0) -- (3.0, 4.0);
    \draw[thin] (0.0, 2.5) -- (3.0, 2.5); % Pelo dell'acqua
    
    % Serbatoio 2
    \draw (6.0, 0.0) -- (6.0, -3.0) -- (9.0, -3.0) -- (9.0, 0.0);
    \draw[thin] (6.0, -1.5) -- (9.0, -1.5); % Pelo dell'acqua

    % --- 3. Etichette degli Stati (dentro i serbatoi) ---
    \node at (5.0, 5.75) {\Large $x_3(t)$};
    \node at (1.5, 1.75) {\Large $x_1(t)$};
    \node at (7.5, -2.25) {\Large $x_2(t)$};

    % --- 4. Frecce dei Flussi e Pesi ---
    % Da 3 a 1 (alpha_31) - Esce a sinistra, entra dall'alto
    \draw[->] (3.5, 6.0) to[out=180, in=90] 
        node[pos=0.3, left, xshift=-4pt] {\Large $\alpha_{31}$} (1.5, 4.0);
        
    % Da 3 a 2 (alpha_32) - Esce a destra, entra dall'alto
    \draw[->] (6.5, 6.0) to[out=0, in=90] 
        node[pos=0.4, right, xshift=4pt] {\Large $\alpha_{32}$} (7.5, 0.0);
        
    % Da 1 a 2 (alpha_12) - Esce a destra, entra dall'alto/sinistra
    \draw[->] (3.0, 1.5) to[out=0, in=110] 
        node[pos=0.4, above, yshift=2pt] {\Large $\alpha_{12}$} (6.5, 0.0);
        
    % Uscita verso l'ambiente da 2 (alpha_20)
    \draw[->] (9.0, -2.5) -- 
        node[above] {\Large $\alpha_{20}$} (10.5, -2.5);

    % --- 5. Valvole e Ingressi ---
    % Valvola 1 (mu_1)
    \bowtievalve{-2.0}{4.0}{u_1(t)}
    % Freccia doppia per simulare il simbolo "=>" dello schizzo
    \draw[-{Stealth[scale=1.2]}, double, double distance=2pt, thick] (-1.3, 4.0) -- (-0.3, 4.0);

    % Valvola 2 (mu_2)
    \bowtievalve{3.0}{9.0}{u_2(t)}
    \draw[-{Stealth[scale=1.2]}, double, double distance=2pt, thick] (3.7, 9.0) -- (4.7, 9.0);

\end{tikzpicture}
\end{document}
```

Scriviamo le equazioni che descrivono il sistema:
$$
\begin{align*}
\dot{x}_{1}(t) &= -\alpha_{12}x_{1}(t) + \alpha_{31}x_{3}(t) + u_{1}(t) \\
\dot{x}_{2}(t) &= \alpha_{12}x_{1}(t) - \alpha_{20}x_{2}(t) + \alpha_{32}x_{3}(t) \\
\dot{x}_{3}(t) &= -\alpha_{32}x_{3}(t) - \alpha_{31}x_{3}(t) + u_{2}(t)
\end{align*}
$$
Per cui abbiamo:
$$
\dot{x}(t) = Ax(t) + Bu(t)
$$
Con:
$$
\begin{align*}
A = \begin{bmatrix}
-\alpha_{12} & 0 & \alpha_{31} \\
\alpha_{12} & -\alpha_{20} & \alpha_{32} \\
0 & 0 & -\alpha_{32}-\alpha_{31}
\end{bmatrix} & & B = \begin{bmatrix}
1 & 0 \\
0 & 0 \\
0 & 1
\end{bmatrix}
\end{align*}
$$
Questa volta abbiamo:
$$
\begin{bmatrix}
1 & 1 & 1
\end{bmatrix}A = \begin{bmatrix}
0 & -\alpha_{20} & 0
\end{bmatrix} \ne 0
$$
>[!note] CONSEGUENZA
>Per cui questo sistema **non è conservativo**: se volessimo *mantenere lo stesso livello di liquido*, dovremmo intervenire *agendo sugli ingressi* $u_{1}$ e $u_{2}$.
### RAPPRESENTAZIONE NELLO SPAZIO DEGLI STATI
Per capire meglio la *rappresentazione* nello *spazio degli stati*, immaginiamo qui che $\alpha_{20}=0$ e $u_{1}(t)=u_{2}(t)=0$ (cioè consideriamo un *sistema conservativo* con la *stessa struttura*).

```tikz
\usepackage{amsmath}
\usetikzlibrary{arrows.meta, calc}

% Definizione del colore "ruggine" del pennarello
\definecolor{sketchred}{RGB}{195, 65, 20}

\begin{document}
\begin{tikzpicture}[
    % Impostazione della prospettiva 3D (x a destra, y in alto, z in basso a sinistra)
    x={(1cm, 0cm)},
    y={(0cm, 1cm)},
    z={(-0.6cm, -0.6cm)},
    >= {Stealth[scale=1.3]},
    line width=1.2pt,
    color=sketchred,
    every node/.style={color=sketchred} % Applica il colore a tutti i testi di default
]

    % --- 1. Assi del sistema 3D ---
    \draw[->, color = black] (0,0,0) -- (4.5,0,0) node[below right, font=\Large, color = black] {$x_1$};
    \draw[->, color = black] (0,0,0) -- (0,4.5,0) node[above, font=\Large, color = black] {$x_3$}; 
    \draw[->, color = black] (0,0,0) -- (0,0,4.0) node[below left, font=\Large, color = black] {$x_2$};

    % --- 2. Vertici e piano del simplesso ---
    % Definisco le coordinate dei punti in cui il piano interseca gli assi
    \coordinate (X1) at (3, 0, 0);
    \coordinate (X3) at (0, 3, 0);
    \coordinate (X2) at (0, 0, 2.5);

    % Disegno i tre lati del triangolo (simplesso)
    \draw (X1) -- (X3) -- (X2) -- cycle;

    % --- 3. Punto iniziale x(0) e Testi ---
    % Posizione del punto x(0) (circa sul piano)
    \coordinate (x0) at (1.2, 1.2, 1.1);
    
    % Pallino e testo neri per x(0)
    \filldraw[black] (x0) circle (1.5pt);
    \node[right, text=black, font=\Large, xshift=2pt] at (x0) {$x(0)$};

    % --- 4. Traiettoria (Freccia curva) e destinazione ---
    % Freccia che parte da vicino a x(0) e curva verso il vertice x2
    \draw[->, very thick, color = blue] (1.0, 1.2, 1.1) to[out=140, in=60, looseness=1.2] (0.15, 0.15, 2.5);

    % Pallino pieno sul vertice dell'asse x2
    \filldraw (X2) circle (2.5pt);

\end{tikzpicture}
\end{document}
```

>[!note] NOTA
>Il sistema rimarrebbe *sempre confinato* sulla *superificie* delimitata dai segmenti rossi: qui la *quantità totale di liquido* nel sistema è **costante**, e *convergerebbe* verso il vertice sull'asse $x_{2}$ (compartimento in cui *tutto il liquido andrebbe a finire*).
# ESEMPIO TRAFFICO AUTOMOBILISTICO
Abbiamo 4 stati, che rappresentano 4 *città*.
Le città sono collegate da una rete *stradale* e *autostradale*, come in figura:

```tikz
\usepackage{amsmath}
\usetikzlibrary{positioning, arrows.meta, calc}

% Definizione del colore grigio utilizzato nel grafo
\definecolor{mygrey}{RGB}{155, 155, 155} 

\begin{document}
\begin{tikzpicture}[
    auto,
    >= {Stealth[scale=1.2]},      % Stile predefinito per le frecce
    node distance= 2.2cm,         % Distanza di base tra i nodi
    thick,
    % --- Stili dei Nodi ---
    state/.style={
        circle,
        draw=mygrey,
        fill=mygrey,
        text=white,
        minimum size=1.2cm,
        font=\Large\bfseries
    },
    % --- Stili delle Frecce ---
    edge/.style={
        ->,
        thick,
        draw=mygrey,
        text=mygrey,
        shorten >=1pt,
        shorten <=1pt,
        font=\large
    }
]

    % --- 1. Posizionamento dei Nodi ---
    \node[state] (1) {1};
    \node[state, right=of 1] (2) {2};
    \node[state, right=of 2] (3) {3};
    \node[state, right=of 3] (4) {4};

    % --- 2. Archi Adiacenti (Pesi 2\alpha) ---
    \draw[edge] (1) to[bend left=20] node[above] {$2\alpha$} (2);
    \draw[edge] (2) to[bend left=20] node[below] {$2\alpha$} (1);
    
    \draw[edge] (2) to[bend left=20] node[above] {$2\alpha$} (3);
    \draw[edge] (3) to[bend left=20] node[below] {$2\alpha$} (2);
    
    % Assegniamo un nome al nodo del testo per agganciarci "autostrada" dopo
    \draw[edge] (3) to[bend left=20] node[above] (alpha_34) {$2\alpha$} (4);
    \draw[edge] (4) to[bend left=20] node[below] {$2\alpha$} (3);

    % --- 3. Archi Superiori (tra 1 e 3) ---
    % Arco Esterno (da 1 a 3)
    \draw[edge] (1) to[bend left=60] node[above] (alpha_top) {$\alpha$} (3);
    % Arco Interno (da 3 a 1)
    \draw[edge] (3) to[bend right=45] node[above] {$\alpha$} (1);

    % --- 4. Archi Inferiori (tra 1 e 4) ---
    % Arco Interno (da 4 a 1)
    \draw[edge] (4) to[bend left=45] node[above] {$\alpha$} (1);
    % Arco Esterno (da 1 a 4) - curvatura leggermente inferiore dato che l'arco è più lungo
    \draw[edge] (1) to[bend right=55] node[below] {$\alpha$} (4);

    % --- 5. Etichette di Testo ---
    % "strada" centrato sopra il picco dell'arco superiore 1->3
    \node[above=0pt of alpha_top, font=\Large\sffamily, text=black] {strada};
    
    % "autostrada" centrato sopra il picco dell'arco 3->4
    \node[above=2pt of alpha_34, font=\Large\sffamily, text=black] {autostrada};

\end{tikzpicture}
\end{document}
```

Le *variabili di stato* $x_{1},x_{2},x_{3},x_{4}$ rappresentano i *veicoli in ogni città*.
Il parametro $\alpha$ rappresenta il **flusso di automobili**, che *assumiamo continuo* (approssimazione).

Scriviamo l'equazione relativa alla prima città.
$$
\dot{x}_{1}(t) = \alpha x_{3}(t) + \alpha x_{4}(t) +2\alpha x_{2}(t) - 4\alpha x_{1}(t) = \alpha[x_{3}(t)+x_{4}(t)+2x_{2}(t)-4x_{1}(t)]
$$
Le altre sono ottenute in modo analogo.

>[!note] NOTA
>Notiamo che il grafo orientato è **simmetrico**.
>Di conseguenza, anche $A$ è *simmetrica*.

Abbiamo infatti:
$$
\dot{x}(t) = Ax(t) = \alpha \begin{bmatrix}
-4 & 2 & 1 & 1 \\
2 & -4 & 2 & 0 \\
1 & 2 & -5 & 2 \\
1 & 0 & 2 & -3
\end{bmatrix}x(t)
$$
>[!note] NOTA
>La *somma degli elementi* di *ogni riga e colonna* è *pari a 0* (*equivalente* a *moltiplicare* $A$ per $\mathbb{1}^T$, come avevamo fatto negli esempi precedenti).
>Questo è ancora una volta dovuto alla **conservazione della risorsa**: non abbiamo "perdite" di macchine verso altre città, e il numero di macchine in ogni città rimane costante.
## PUNTI DI EQUILIBRIO DI UN SISTEMA DINAMICO
Dato il sistema
$$
\dot{x}(t) = Ax(t) + Bu(t)
$$
Definiamo il **punto di equilibrio** $\bar{x}$:

>[!def] PUNTO DI EQUILIBRIO
>Diciamo che $\bar{x}$ è *di equilibrio* se:
>$$ \overline{\begin{bmatrix} \dot{x}_{1} \\ \vdots \\ \dot{x}_{n} \end{bmatrix}} = 0 $$
>Che è *equivalente* a dire che $\bar{x}$ è di equilibrio se:
>$$ 0 = A\bar{x} + B\bar{u} $$

Un *punto di equilibrio* rappresenta una **stato costante nel tempo** (per cui la sua *derivata* è *nulla*): i *flussi tra i compartimenti* e *con l'ambiente esterno* sono *bilanciati*.

>[!note] CASO PARTICOLARE :  $A$ INVERTIBILE
>Se $A$ è *invertibile*, allora la soluzione è:
>$$ \bar{x} = -A^{-1}B\bar{u} $$

Se abbiamo a che fare con un sistema isolato che *non scambia risorse con l'esterno* (come nel nostro esempio), lo studio dei punti di equilibrio risulta semplificato.

>[!note] CASO PARTICOLARE : SISTEMA AUTONOMO
>In un **sistema autonomo** (*isolato*, senza ingressi espliciti) abbiamo $\dot{x}(t) =Ax(t)$.
>Allora i punti di equilibrio sono le soluzioni:
>$$ \{ \bar{x} \text{ : } A\bar{x} = 0 \} = \text{ker} A $$
## PUNTI DI EQUILIBRIO DEL SISTEMA IN ESEMPIO
Come abbiamo visto, per determinare i punti di equilibrio del nostro sistema, essendo questo isolato, dobbiamo risolvere:
$$
A\bar{x} = 0
$$
Abbiamo visto, all'inizio della sezione, che $\mathbb{1}^TA=0$.
Siccome la matrice è *simmetrica*, abbiamo anche:
$$
A\mathbb{1} = 0
$$
Ma allora $A$ ha *autovalore* $0$, il che ci dice due cose su $A$:
- $A$ **non** è **invertibile**.
- $\text{dim}(\text{Ker }A)\geq 1$.

>[!idea] IMPORTANTE
>La seconda informazione implica l'esistenza di **infiniti punti di equilibrio**.

Proviamo con una *soluzione candidata*:
$$
\bar{x} = x(0) = \beta \mathbb{1} = \begin{bmatrix}
\beta \\
\vdots \\
\beta
\end{bmatrix}
$$
Cioè la situazione in cui *tutte le città hanno la stessa quantità di veicoli*.
Verifichiamo ora che $\bar{x}$ è effettivamente punto di equilibrio:
$$
\dot{\bar{x}} = A\beta \mathbb{1} = \beta \cancelto{ 0 }{ A\mathbb{1} } = 0
$$
Quindi $\bar{x}$ è un punto di equilibrio.

>[!note] OSSERVAZIONI FINALI
>Abbiamo quindi visto che:
>1. $\mathbb{1}^TA=0$ implica la **conservazione della risorsa**.
>2. $A=A^T$ implica la **simmetria dei flussi**, che si traduce in uno *stato di equilibrio* in cui la risorsa è *perfettamente bilanciata* tra ogni compartimento.
## CASO NON SIMMETRICO
Supponiamo ora che ad un certo istante $t^*$, a causa di lavori di manutenzione, venga *chiusa la strada* dalla città 1 alla città 2, come in figura:

```tikz
\usepackage{amsmath}
\usetikzlibrary{positioning, arrows.meta, calc}

% Definizione del colore grigio utilizzato nel grafo
\definecolor{mygrey}{RGB}{155, 155, 155} 

\begin{document}
\begin{tikzpicture}[
    auto,
    >= {Stealth[scale=1.2]},      % Stile predefinito per le frecce
    node distance= 2.2cm,         % Distanza di base tra i nodi
    thick,
    % --- Stili dei Nodi ---
    state/.style={
        circle,
        draw=mygrey,
        fill=mygrey,
        text=white,
        minimum size=1.2cm,
        font=\Large\bfseries
    },
    % --- Stili delle Frecce ---
    edge/.style={
        ->,
        thick,
        draw=mygrey,
        text=mygrey,
        shorten >=1pt,
        shorten <=1pt,
        font=\large
    }
]

    % --- 1. Posizionamento dei Nodi ---
    \node[state] (1) {1};
    \node[state, right=of 1] (2) {2};
    \node[state, right=of 2] (3) {3};
    \node[state, right=of 3] (4) {4};

    % --- 2. Archi Adiacenti (Pesi 2\alpha) ---
    % Arco 1 -> 2 (con nome del nodo per posizionare la X rossa)
    \draw[edge] (1) to[bend left=20] node[above] (edge12) {$2\alpha$} (2);
    
    % Disegno della X rossa sopra il testo 2\alpha
    \draw[line width=1.5pt, red] ($(edge12.center) + (-0.4,-0.25)$) -- ($(edge12.center) + (0.4,0.25)$);
    \draw[line width=1.5pt, red] ($(edge12.center) + (-0.4,0.25)$) -- ($(edge12.center) + (0.4,-0.25)$);

    \draw[edge] (2) to[bend left=20] node[below] {$2\alpha$} (1);
    
    \draw[edge] (2) to[bend left=20] node[above] {$2\alpha$} (3);
    \draw[edge] (3) to[bend left=20] node[below] {$2\alpha$} (2);
    
    \draw[edge] (3) to[bend left=20] node[above] {$2\alpha$} (4);
    \draw[edge] (4) to[bend left=20] node[below] {$2\alpha$} (3);

    % --- 3. Archi Superiori (tra 1 e 3) ---
    % Arco Esterno (da 1 a 3)
    \draw[edge] (1) to[bend left=60] node[above] {$\alpha$} (3);
    % Arco Interno (da 3 a 1)
    \draw[edge] (3) to[bend right=45] node[above] {$\alpha$} (1);

    % --- 4. Archi Inferiori (tra 1 e 4) ---
    % Arco Interno (da 4 a 1)
    \draw[edge] (4) to[bend left=45] node[above] {$\alpha$} (1);
    % Arco Esterno (da 1 a 4) 
    \draw[edge] (1) to[bend right=55] node[below] {$\alpha$} (4);

\end{tikzpicture}
\end{document}
```

Ora la matrice $A$ diventa:
$$
A = \alpha \begin{bmatrix}
-2 & 2 & 1 & 1 \\
0 & -4 & 2 & 0 \\
1 & 2 & -5 & 2 \\
1 & 0 & 2 & -3
\end{bmatrix}
$$
Notiamo che vale ancora
$$
\mathbb{1}^TA = 0
$$
Per cui abbiamo ancora *conservazione della risorsa* (come ci aspettavamo).
E per quanto riguarda i *punti di equilibrio*?

>[!note] NOTA
>La matrice $A$ **non** è più **simmetrica**, per cui *autovettori destri e sinistri* **non** saranno più **uguali**.
>

Allora il sistema avrà un *nuovo punto di equilibrio*: a causa della chiusura della strada, le auto inizieranno ad *accumularsi in modo asimmetrico*.

Per determinare questo nuovo punto di equilibrio, dobbiamo risolvere il sistema $Ax=0$:
$$
\begin{cases}
-2x_{1} &+2x_{2} &+x_{3} &+x_{4} &=0 \\
&-4x_{2} &+2x_{3} & &=0 \\
1x_{1} &+2x_{2} &-5x_{3} &+2x_{4} &=0 \\
1x_{1} & &+2x_{3} &-3x_{4} &=0
\end{cases}
$$
Sappiamo che avremo *almeno un parametro libero*, quindi per semplificare le cose possiamo affermare $x_{2}=1$.
Risolvendo il sistema troviamo:
$$
\bar{x} = \begin{bmatrix}
\frac{16}{5} \\
1 \\
2 \\
\frac{12}{5}
\end{bmatrix} = \frac{1}{5} \begin{bmatrix}
16 \\
5 \\
10 \\
12
\end{bmatrix}
$$
Che *normalizziamo* per ottenere:
$$
\bar{x}_{\text{NOR}} = \frac{1}{43} \begin{bmatrix}
16 \\
5 \\
10 \\
12
\end{bmatrix}
$$
>[!note] OSSERVAZIONE
>Notiamo che la *maggior parte* dei veicoli ($\frac{16}{43}$) si concentra nella prima città, mentre la seconda è quella con il *minor numero di veicoli* ($\frac{5}{43}$).
### RAGGIUNGIMENTO DEL NUOVO EQUILIBRIO
Analizziamo ora il seguente grafico per osservare l'**evoluzione** del sistema, che passa dal *precedente* stato di equilibrio (ugual numero di veicoli in ogni città) al **nuovo stato di equilibrio** (dovuto alla chiusura della strada).

```tikz
\usepackage{pgfplots}
\usepackage{amsmath}

% Definizione dei colori stile MATLAB usati nel grafico
\definecolor{matblue}{RGB}{0, 114, 189}    % x1
\definecolor{matorange}{RGB}{217, 83, 25}  % x2
\definecolor{matyellow}{RGB}{237, 177, 32} % x3
\definecolor{matpurple}{RGB}{126, 47, 142} % x4
\definecolor{matgreen}{RGB}{119, 172, 48}  % x_ss,1
\definecolor{matcyan}{RGB}{77, 190, 238}   % x_ss,2
\definecolor{matpink}{RGB}{220, 50, 160}   % x_ss,3 (magenta chiaro)

\begin{document}
\begin{tikzpicture}
    \begin{axis}[
        width=12cm, 
        height=7cm,
        xmin=0, xmax=40,
        ymin=1000, ymax=4000,
        xlabel={t},
        ylabel={numero veicoli},
        grid=major,
        grid style={line width=0.3pt, draw=gray!40},
        xtick={0,5,10,15,20,25,30,35,40},
        ytick={1000,1500,2000,2500,3000,3500,4000},
        tick align=inside,
        enlarge x limits=false,
        enlarge y limits=false,
        % Configurazione della legenda spostata all'esterno
        legend pos=outer north east,
        legend style={
            draw=gray,
            fill=white,
            cells={anchor=west}, % Allinea il testo a sinistra
            font=\small
        },
        clip=false % Permette di disegnare fuori dagli assi (per la matrice)
    ]

        % --- Curve del transitorio (innesco a t=5) ---
        % Usiamo una funzione logica (x<=5) per mantenere il valore iniziale a 2500,
        % e un decadimento esponenziale (x>5) verso i nuovi valori stazionari.
        
        % x1
        \addplot[color=matblue, line width=1.5pt, domain=0:40, samples=400] 
            {(x<=5)*2500 + (x>5)*(3720.9 + (2500-3720.9)*exp(-1.5*(x-5)))};
        \addlegendentry{$x_1$}

        % x2
        \addplot[color=matorange, line width=1.5pt, domain=0:40, samples=400] 
            {(x<=5)*2500 + (x>5)*(1162.8 + (2500-1162.8)*exp(-1.5*(x-5)))};
        \addlegendentry{$x_2$}

        % x3
        \addplot[color=matyellow, line width=1.5pt, domain=0:40, samples=400] 
            {(x<=5)*2500 + (x>5)*(2325.6 + (2500-2325.6)*exp(-1.5*(x-5)))};
        \addlegendentry{$x_3$}

        % x4
        \addplot[color=matpurple, line width=1.5pt, domain=0:40, samples=400] 
            {(x<=5)*2500 + (x>5)*(2790.7 + (2500-2790.7)*exp(-1.5*(x-5)))};
        \addlegendentry{$x_4$}

        % --- Linee tratteggiate per gli stati stazionari (x_ss) ---
        
        % x_ss,1
        \addplot[color=matgreen, dashed, line width=1pt, domain=0:40] {3720.9};
        \addlegendentry{$x_{ss,1}$}

        % x_ss,2
        \addplot[color=matcyan, dashed, line width=1pt, domain=0:40] {1162.8};
        \addlegendentry{$x_{ss,2}$}

        % x_ss,3
        \addplot[color=matpink, dashed, line width=1pt, domain=0:40] {2325.6};
        \addlegendentry{$x_{ss,3}$}

        % x_ss,4 (Usa lo stesso blu di x1)
        \addplot[color=matblue, dashed, line width=1pt, domain=0:40] {2790.7};
        \addlegendentry{$x_{ss,4}$}
    \end{axis}
\end{tikzpicture}
\end{document}
```

Il numero finale di veicoli in ciascuna città rispecchia la distribuzione data da $\bar{x}_{\text{NOR}}$.

Osserviamo anche *come variano i flussi attraverso le strade*.

```tikz
\usepackage{pgfplots}
\usetikzlibrary{calc}

\definecolor{matblue}{RGB}{0, 114, 189}
\definecolor{matgray}{RGB}{160, 160, 160}

\begin{document}

\begin{tikzpicture}

    % Impostazioni comuni per tutti e 4 i grafici
    \pgfplotsset{
        every axis/.append style={
            width=7.5cm,
            height=5cm,
            xmin=0, xmax=40,
            xlabel={t},
            ylabel={flusso},
            grid=major,
            grid style={line width=0.3pt, draw=gray!40},
            tick align=inside,
            enlarge x limits=false,
            enlarge y limits=false,
            title style={font=\bfseries\small},
            scaled y ticks=false,            % Disabilita il moltiplicatore *10^4
            /pgf/number format/1000 sep={}   % Rimuove la virgola nelle migliaia (es. 10000 invece di 10,000)
        }
    }

    % --- Subplot 1 (Top Left): Città 1 ---
    \begin{axis}[
        name=ax1,
        title={flusso in uscita dalla città 1},
        ymin=0, ymax=8000,
        ytick={0,2000,4000,6000,8000},
        legend style={at={(0.95,0.45)}, anchor=east, font=\scriptsize}
    ]
        % Autostradale (parte da 5000 e poi scende a 0)
        \addplot[color=matgray, line width=1.5pt, domain=0:5] {5000};
        
        \addplot[color=matblue, line width=1.5pt, domain=0:5] {5000};
        
        \addplot[color=matgray, line width=2.5pt, domain=5:40] {0};

        \addlegendentry{autostradale (coeff = 2)}
        
        % Stradale
        \addplot[color=matblue, line width=1.5pt, domain=5:40, samples=100] {7441.8 - 2441.8*exp(-1.5*(x-5))};
        \addlegendentry{stradale (coeff = 1)}
        
        \draw[dashed, black, thick] (axis cs: 5, 0) -- (axis cs: 5, 8000);
    \end{axis}

    % --- Subplot 2 (Top Right): Città 2 ---
    \begin{axis}[
        name=ax2,
        at={(ax1.outer east)}, anchor=outer west, xshift=1cm,
        title={flusso in uscita dalla città 2},
        ymin=0, ymax=10000,
        ytick={0,2000,4000,6000,8000,10000}
    ]
        % Autostradale
        \addplot[color=matgray, line width=1.5pt, domain=0:5] {10000};
        \addplot[color=matgray, line width=1.5pt, domain=5:40, samples=100] {4651.2 + 5348.8*exp(-1.5*(x-5))};
        % Stradale
        \addplot[color=matblue, line width=2.5pt, domain=0:40] {0};
        
        \draw[dashed, black, thick] (axis cs: 5, 0) -- (axis cs: 5, 10000);
    \end{axis}

    % --- Subplot 3 (Bottom Left): Città 3 ---
    \begin{axis}[
        name=ax3,
        at={(ax1.outer south)}, anchor=outer north, yshift=-1.5cm,
        title={flusso in uscita dalla città 3},
        ymin=0, ymax=10000,
        ytick={0,2000,4000,6000,8000,10000}
    ]
        % Autostradale
        \addplot[color=matgray, line width=1.5pt, domain=0:5] {10000};
        \addplot[color=matgray, line width=1.5pt, domain=5:40, samples=100] {9302.4 + 697.6*exp(-1.5*(x-5))};
        % Stradale
        \addplot[color=matblue, line width=1.5pt, domain=0:5] {2500};
        \addplot[color=matblue, line width=1.5pt, domain=5:40, samples=100] {2325.6 + 174.4*exp(-1.5*(x-5))};
        
        \draw[dashed, black, thick] (axis cs: 5, 0) -- (axis cs: 5, 10000);
    \end{axis}

    % --- Subplot 4 (Bottom Right): Città 4 ---
    \begin{axis}[
        name=ax4,
        at={(ax3.outer east)}, anchor=outer west, xshift=1cm,
        title={flusso in uscita dalla città 4},
        ymin=2000, ymax=6000,
        ytick={2000,3000,4000,5000,6000}
    ]
        % Autostradale
        \addplot[color=matgray, line width=1.5pt, domain=0:5] {5000};
        \addplot[color=matgray, line width=1.5pt, domain=5:40, samples=100] {5581.4 - 581.4*exp(-1.5*(x-5))};
        % Stradale
        \addplot[color=matblue, line width=1.5pt, domain=0:5] {2500};
        \addplot[color=matblue, line width=1.5pt, domain=5:40, samples=100] {2790.7 - 290.7*exp(-1.5*(x-5))};
        
        \draw[dashed, black, thick] (axis cs: 5, 2000) -- (axis cs: 5, 6000);
    \end{axis}

\end{tikzpicture}

\end{document}
```
