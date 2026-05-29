# INDICE SEZIONE
- [ ] [[#INTRODUZIONE]]
- [ ] [[#AFFARI DI CUORE (TEMPO CONTINUO)]]
- [ ] [[#CORSA AGLI ARMAMENTI (TEMPO CONTINUO)]]
      - [[#SPAZIO DI STATO]]
- [ ] [[#DINAMICA DEI PREZZI (TEMPO DISCRETO)]]
      - [[#ANDAMENTO DEL PREZZO METODO GRAFICO]]
      - [[#ANDAMENTO DEL PREZZO METODO ANALITICO]]
# INTRODUZIONE
>[!def] MODELLI DI INFLUENZA
>Modelli che rappresentano fenomeni di relazione **causa-effetto**.

Anche questi modelli possono essere *rappresentati con dei grafi*, e ne esistono *sia in tempo continuo* che in *tempo discreto*.

In **tempo continuo** sono descritti da:
$$
\begin{align*}
\dot{x}_{i}(t) &= \sum_{j=1}^n \alpha_{ji}x_{j}(t) + \sum_{l=1}^p \beta_{li}u_{l}(t) \\ \\
\dot{x}(t) &= Ax(t) + Bu(t)
\end{align*}
$$
In **tempo discreto** sono descritti da:
$$
\begin{align*}
x_{i}(k+1) &= \sum_{j=1}^n \alpha_{ji}x_{j}(k) + \sum_{l=1}^p \beta_{li}u_{l}(k) \\ \\
x(k+1) &= Ax(k) + Bu(k)
\end{align*}
$$
>[!note] NOTA
>In entrambi i casi, le entrate della *matrice* $A$ sono date da:
>$$ a_{ij} = \alpha_{ji} $$
>Ogni compartimento *ha un certa influenza sugli altri*, ma **non vi è nessuna perdita di risorsa**.
# AFFARI DI CUORE (TEMPO CONTINUO)
Consideriamo la dinamica della *relazione d'amore* tra Romeo e Giulietta.
Siano:
- $x_{1}(t)$ l'*intensità* del sentimento di Romeo al tempo $t$.
- $x_{2}(t)$ l'*intensità* del sentimento di Giulietta al tempo $t$.

Modelliamo la dinamica della relazione come segue:
- *Romeo*: più Giulietta ama Romeo, più lui ne è infastidito; ma quando Giulietta si stufa e perde interesse per lui, allora il desiderio di Romeo si riaccende.
- *Giulietta*: l'amore di Giulietta cresce quando lui la ama, ma lei cambia i propri sentimenti quando Romeo fa lo stesso.

Abbiamo allora:
$$
\begin{align*}
\dot{x}_{1}(t) &= -ax_{2}(t) \\ \\
\dot{x}_{2}(t) &= bx_{1}(t)
\end{align*}
$$
Che scriviamo in forma compatta come:
$$
\begin{align*}
\dot{x}(t) = Ax(t) & & A = \begin{bmatrix}
0 & -a \\
b & 0
\end{bmatrix}
\end{align*}
$$
>[!note] NOTA
>Le *entrate sulla diagonale* sono *nulle*: i sentimenti di uno sono *influenzati solo da quelli dell'altra*; lo *stato precedente* è *irrilevante*.

Ed il grafo associato è il seguente:

```tikz
\usetikzlibrary{arrows.meta, positioning, calc}

\begin{document}
\begin{tikzpicture}[
    % Stili dei nodi e delle frecce
    stato/.style={circle, fill=gray!60, text=white, font=\bfseries, minimum size=1cm},
    freccia/.style={-Stealth, thick, gray!80}
]

    % Nodi principali
    \node[stato] (N1) {1};
    \node[stato, right=2cm of N1] (N2) {2};

    % Archi curvi
    \draw[freccia] (N2) to[bend right=30] node[above, black] {$-a$} (N1);
    \draw[freccia] (N1) to[bend right=30] node[below, black] {$b$} (N2);

\end{tikzpicture}
\end{document}
```

Calcoliamo gli *autovalori* associati alla matrice $A$:
$$
\begin{align*}
\det(A-\lambda I) &= \det\begin{bmatrix}
-\lambda & -a \\
b & -\lambda
\end{bmatrix} = \lambda^2 + ab \\ \\
\lambda^2 + ab &= 0 \implies \lambda_{1.2} = \pm i\sqrt{ ab }
\end{align*}
$$
>[!idea] CONSEGUENZA
>Gli autovalori sono **puramente immaginari**, per cui le soluzioni alle equazioni differenziali sono di tipo *sinusoidale* e il sistema *oscilla perennemente*.

```tikz
\usepackage{pgfplots}

\definecolor{matblue}{RGB}{0, 176, 240} % Azzurro brillante per Giulietta
\definecolor{matgray}{RGB}{200, 200, 200} % Grigio chiaro per Romeo

\begin{document}
\begin{tikzpicture}
    \begin{axis}[
        width=10cm, height=7cm,
        axis lines=middle,
        xmin=0, xmax=4.2*pi,
        ymin=-1.2, ymax=1.5,
        ticks=none, % Rimuove i numeri e le tacche dagli assi
        xlabel={$t$},
        axis line style={->, thick},
        xlabel style={at={(ticklabel* cs:1)},anchor=west, font=\large},
        % Impostazioni globali per le trame
        domain=0:4*pi,
        samples=200,
        smooth,
    ]
        
        \addplot[color=matgray, line width=1.2pt] {cos(deg(x - pi/2))};
        \node[rotate=-75, color=black, font=\large\itshape] at (axis cs: 1.1*pi, 0.4) {Giulietta};
        
        \addplot[color=matblue, line width=1.8pt] {cos(deg(x))};
        \node[rotate=-75, color=black, font=\large\itshape] at (axis cs: 2.1*pi, 0.4) {Romeo};

    \end{axis}
\end{tikzpicture}
\end{document}
```
# CORSA AGLI ARMAMENTI (TEMPO CONTINUO)
Consideriamo ora la *spesa per gli armamenti di due nazioni* $1$ e $2$.
Siano:
- $x_{1}(t)$ la *spesa* per armamenti della nazione $1$.
- $u_{1}(t)$ una *variabile indipendente* che esprime l'*aggressività intrinseca* (variabile di rancore) della nazione $1$.
- $x_{2}(t)$ la *spesa* per armamenti della nazione $2$.
- $u_{2}(t)$ una *variabile indipendente* che esprime l'*aggressività intrinseca* (variabile di rancore) della nazione $2$.

La situazione è quindi la seguente:

```tikz
\usetikzlibrary{arrows.meta, positioning}

\begin{document}
\begin{tikzpicture}[
    % Stili dei nodi
    stato/.style={circle, fill=gray!60, text=white, font=\bfseries, minimum size=0.9cm},
    esterno/.style={rectangle, draw=gray!60, thick, text=gray!60, font=\bfseries, minimum size=0.8cm},
    freccia/.style={-Stealth, thick, gray!80},
    loop_style/.style={looseness=5, out=120, in=60, min distance=1cm}
]

    % Nodi (Stati e Input)
    \node[esterno] (S1) at (0,0) {1};
    \node[stato, right=1.5cm of S1] (N1) {1};
    \node[stato, right=2cm of N1] (N2) {2};
    \node[esterno, right=1.5cm of N2] (S2) {2};

    % Archi di ingresso
    \draw[freccia] (S1) -- node[above, black] {1} (N1);
    \draw[freccia] (S2) -- node[above, black] {1} (N2);

    % Archi tra gli stati (curvi)
    \draw[freccia] (N1) to[bend right=25] node[below, black] {$\alpha_{12}$} (N2);
    \draw[freccia] (N2) to[bend right=25] node[above, black] {$\alpha_{21}$} (N1);

    % Self-loops (auto-influenza)
    \draw[freccia] (N1) edge [loop_style] node[above, black] {$\alpha_{11}$} (N1);
    \draw[freccia] (N2) edge [loop_style] node[above, black] {$\alpha_{22}$} (N2);

\end{tikzpicture}
\end{document}
```

Le equazioni che descrivono il sistema sono:
$$
\begin{align*}
\dot{x}_{1} &= u_{1}(t) + \alpha_{21}x_{2}(t) - \alpha_{11}x_{1}(t) \\ \\
\dot{x}_{1} &= u_{2}(t) + \alpha_{12}x_{1}(t) - \alpha_{22}x_{2}(t)
\end{align*}
$$

>[!note] NOTA
>I coefficienti $\alpha_{21},\alpha_{12}$ sono **coefficienti di difesa**: spingono una nazione ad *incrementare la propria spesa in risposta all'altra nazione*.
>I coefficienti $\alpha_{11},\alpha_{22}$ sono **coefficienti di saturazione**: *moderano la spesa* per gli armamenti, che inevitabilmente è limitata dalla spesa in altri settori e dal PIL limitato della nazione.
## SPAZIO DI STATO
Consideriamo ad esempio le situazioni in cui $\dot{x}_{1}(t)>0$, per cui si ha:
$$
u_{1}(t) + \alpha_{21}x_{2}(t) - \alpha_{11}x_{1}(t) > 0
$$
Ricaviamo allora:
$$
x_{1}(t) < \frac{u_{1}(t)}{\alpha_{11}} + \frac{\alpha_{21}}{\alpha_{11}}x_{2}(t)
$$
Per *semplicità*, assumiamo $u_{1}(t)=u_{1}$ (costante). 
Allora l'equazione $x_{1}(t) = \frac{u_{1}}{\alpha_{11}} + \frac{\alpha_{21}}{\alpha_{11}}x_{2}(t)$ rappresenta una **retta**, e nello **spazio di stato** possiamo identificare *due aree*:
- Una a *sinistra*, in cui $x_{1}$ è minore della retta considerata e *la spesa aumenta*.
- Una a *destra*, in cui $x_{1}$ è maggiore della retta considerata e la *spesa diminuisce*.

```tikz
\usepackage{pgfplots}
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}
    \begin{axis}[
        width=10cm, height=9cm,
        axis lines=middle,
        xmin=0, xmax=5,
        ymin=0, ymax=5,
        ticks=none,
        xlabel={$x_1$},
        ylabel={$x_2$},
        % Posizionamento manuale compatibile con vecchie versioni
        every axis x label/.style={at={(ticklabel* cs:1)}, anchor=west},
        every axis y label/.style={at={(ticklabel* cs:1)}, anchor=south},
        axis line style={-stealth, thick, gray!80},
        clip=false 
    ]
        
        \draw[very thick, red!50] (axis cs:1.8, 0) -- (axis cs:3.5, 4.8);

        % Punto di intercetta sull'asse x
        \filldraw[black] (axis cs:1.8, 0) circle (2.5pt);
        
        % Etichetta frazione sotto l'asse
        \node[below, yshift=-5pt] at (axis cs:1.8, 0) {\Large $\dfrac{u_1}{\alpha_{11}}$};

        % Testo ruotato lungo la retta
        \node[rotate=70, anchor=south, font=\large] at (axis cs:2.6, 2.3) {$x_1 = \dots$};

        % Annotazioni a SINISTRA
        \node[align=left] at (axis cs:1, 2) {$\dot{x}_1 > 0$ \\ $x_1 \rightarrow$};
        \node at (axis cs:1.8, 4) {$x_1 < \dots$};

        % Annotazioni a DESTRA
        \node[align=left] at (axis cs:4, 2) {$\dot{x}_1 < 0$ \\ $\leftarrow x_1$};
        \node at (axis cs:4.2, 4) {$\dots < x_1$};

    \end{axis}
\end{tikzpicture}
\end{document}
```

Ora consideriamo invece le situazioni in cui $\dot{x}_{2}(t)>0$, per cui si ha:
$$
u_{2}(t) + \alpha_{12}x_{1}(t) - \alpha_{22}x_{2}(t) > 0
$$
Assumiamo anche qui $u_{2}(t)=u_{2}$ costante e troviamo:
$$
x_{2}(t) < \frac{u_{2}}{\alpha_{22}} + \frac{\alpha_{12}}{\alpha_{22}}x_{1}(t)
$$
La rappresentazione nello *spazio di stato* è quindi la seguente:

```tikz
\usepackage{pgfplots}
\usepackage{amsmath}

% Definizione del colore azzurro come da screenshot
\definecolor{myblue}{RGB}{0, 176, 240}

\begin{document}
\begin{tikzpicture}[scale=1]
    \begin{axis}[
        width=10cm, height=8cm,
        axis lines=middle,
        xmin=0, xmax=5,
        ymin=0, ymax=5,
        ticks=none,
        xlabel={$x_1$},
        ylabel={$x_2$},
        % Posizionamento etichette assi per vecchie versioni
        every axis x label/.style={at={(ticklabel* cs:1)}, anchor=north east},
        every axis y label/.style={at={(ticklabel* cs:1)}, anchor=south west},
        axis line style={-stealth, thick, gray!80},
        clip=false 
    ]
        
        % Retta di equilibrio x2 (nullclina) in azzurro
        \draw[very thick, myblue] (axis cs:0, 1.2) -- (axis cs:4.5, 3.5);

        % Punto di intercetta sull'asse y
        \filldraw[black] (axis cs:0, 1.2) circle (2.5pt);
        
        % Etichetta frazione a sinistra dell'asse y
        \node[left, xshift=-5pt] at (axis cs:0, 1.2) {\Large $\dfrac{u_2}{\alpha_{22}}$};

        % Testo ruotato lungo la retta (x2 =)
        \node[rotate=22, anchor=south, font=\large] at (axis cs:2.2, 2.3) {$x_2 = \dots$};

        % Annotazioni SUPERIORI (Dinamica decrescente)
        \node at (axis cs:3, 4.2) {\Large $\dot{x}_2 < 0 \quad x_2 \downarrow$};

        % Annotazioni INFERIORI (Dinamica crescente)
        \node at (axis cs:3, 0.8) {\Large $\dot{x}_2 > 0 \quad x_2 \uparrow$};

    \end{axis}
\end{tikzpicture}
\end{document}
```

Anche in questo caso possiamo individuare *due aree*:
- Una *sotto*, in cui $x_{2}$ è minore della retta considerata e *la spesa aumenta*.
- Una *sopra*, in cui $x_{2}$ è maggiore della retta considerata e la *spesa diminuisce*.

A questo punto mettiamo insieme le due rappresentazioni e *discutiamo la dinamica del sistema* sulla base della *pendenza delle due rette*.

**CASO 1**: le rette *si intersecano in un punto*.

```tikz
\usepackage{pgfplots}
\usepackage{amsmath}

% Colore azzurro per la nullclina di x2
\definecolor{myblue}{RGB}{0, 176, 240}

\begin{document}
\begin{tikzpicture}
    \begin{axis}[
        width=10cm, height=9cm,
        axis lines=middle,
        xmin=0, xmax=5,
        ymin=0, ymax=5,
        ticks=none,
        xlabel={$x_1$},
        ylabel={$x_2$},
        % Posizionamento etichette assi
        every axis x label/.style={at={(ticklabel* cs:1)}, anchor=north east},
        every axis y label/.style={at={(ticklabel* cs:1)}, anchor=south west},
        axis line style={-stealth, thick, gray!80},
        clip=false 
    ]
        
        % --- NULLCLINE ---
        % Nullclina x1 (Grigia)
        \draw[very thick, red!50] (axis cs:1.3, 0) -- (axis cs:3, 5);
        
        % Nullclina x2 (Azzurra)
        \draw[very thick, myblue] (axis cs:0, 1.5) -- (axis cs:4.5, 3.5);

        % --- PUNTI DI INTERCETTA E EQUILIBRIO ---
        % Intercetta su x
        \filldraw[black] (axis cs:1.3, 0) circle (2pt);
        \node[below, yshift=-3pt] at (axis cs:1.3, 0) {\Large $\dfrac{u_1}{\alpha_{11}}$};

        % Intercetta su y
        \filldraw[black] (axis cs:0, 1.5) circle (2pt);
        \node[left, xshift=-3pt] at (axis cs:0, 1.5) {\Large $\dfrac{u_2}{\alpha_{22}}$};

        % Punto di intersezione (Equilibrio)
        % Calcolato visivamente per l'intersezione delle due rette sopra
        \filldraw[black] (axis cs:2.15, 2.45) circle (2.5pt);
        \node[below right, xshift=2pt] at (axis cs:2.15, 2.45) {\Large equilibrio};

        % --- QUADRANTI (NUMERAZIONE ROMANA) ---
        \node at (axis cs:4, 2) {\Large I};
        \node at (axis cs:0.5, 0.5) {\Large II};
        \node at (axis cs:2, 4.5) {\Large III};
        \node at (axis cs:4.2, 4.5) {\Large IV};

    \end{axis}
\end{tikzpicture}
\end{document}
```

>[!idea] CONSEGUENZA
>In tal caso esiste un punto in cui **entrambe le derivate sono nulle**, in corrispondenza del quale il *sistema si trova in equilibrio*.

**CASO 2**: le rette *non si intersecano*.

```tikz
\usepackage{pgfplots}
\usepackage{amsmath}

% Colore azzurro per la nullclina di x2
\definecolor{myblue}{RGB}{0, 176, 240}

\begin{document}
\begin{tikzpicture}
    \begin{axis}[
        width=10cm, height=9cm,
        axis lines=middle,
        xmin=0, xmax=5,
        ymin=0, ymax=5,
        ticks=none,
        xlabel={$x_1$},
        ylabel={$x_2$},
        % Posizionamento etichette assi
        every axis x label/.style={at={(ticklabel* cs:1)}, anchor=north east},
        every axis y label/.style={at={(ticklabel* cs:1)}, anchor=south west},
        axis line style={-stealth, thick, gray!80},
        clip=false 
    ]
        
        % --- NULLCLINE ---
        % Nullclina x2 (Azzurra) - Pendenza maggiore
        \draw[very thick, myblue] (axis cs:0, 1.5) -- (axis cs:2.5, 5);
        
        % Nullclina x1 (Grigia) - Pendenza minore
        \draw[very thick, red!50] (axis cs:1.3, 0) -- (axis cs:4.5, 2.2);

        % --- PUNTI DI INTERCETTA ---
        % Intercetta su y (x2)
        \filldraw[black] (axis cs:0, 1.5) circle (2pt);
        \node[left, xshift=-3pt] at (axis cs:0, 1.5) {\Large $\dfrac{u_2}{\alpha_{22}}$};

        % Intercetta su x (x1)
        \filldraw[black] (axis cs:1.3, 0) circle (2pt);
        \node[below, yshift=-3pt] at (axis cs:1.3, 0) {\Large $\dfrac{u_1}{\alpha_{11}}$};

        % --- REGIONI (NUMERAZIONE ROMANA) ---
        % Regione I (sotto la retta grigia)
        \node at (axis cs:4, 0.8) {\Large I};
        
        % Regione II (tra le due rette e gli assi)
        \node at (axis cs:0.6, 0.6) {\Large II};
        
        % Regione III (sopra la retta azzurra)
        \node at (axis cs:2, 5) {\Large III};

    \end{axis}
\end{tikzpicture}
\end{document}
```

>[!idea] CONSEGUENZA
>In questo caso **non esiste un punto di equilibrio**.
>Inoltre, se il sistema dovesse trovarsi nella *regione II* (in cui *entrambe le derivate sono positive*), si avrebbe una **corsa illimitata**.
# DINAMICA DEI PREZZI (TEMPO DISCRETO)
Passiamo ora ad analizzare il fenomeno di *domanda e offerta* in microeconomia.
Siano:
- $p$ il *prezzo di un bene*.
- $q$ la *quantità* di un bene *presente sul mercato* (richiesta o programmata per la produzione).

Il *comportamento dei produttori* può essere modellato dalla seguente *relazione lineare*:
$$
q = bp + Q
$$
Dove $b>0$ è la *sensibilità dei produttori* e $Q$ è la *quantità già presente sul mercato*.
Anche il *comportamento dei consumatori* può essere descritto con una relazione *lineare*, come la seguente:
$$
q = -ap + D
$$
Dove $a>0$ è la *sensibilità dei consumatori*.

>[!note] NOTA
>I produttori sono *spinti a produrre di più* se il *prezzo aumenta*.
>Al contrario, i consumatori sono *spinti a compare di meno* se il *prezzo aumenta*.

Vediamo ora come il sistema può essere descritto, facendo le seguenti considerazioni:
1. I *consumatori agiscono istantaneamente*: acquistano i beni in base al *prezzo attuale*.
2. I *produttori pianificano* la produzione: la quantità di bene che produrranno domani dipende dal prezzo di oggi.

Per cui abbiamo:
$$
\begin{align*}
q(k) &= -ap(k) + D & \text{(consumatori)}\\ \\
q(k+1) &= bp(k) + Q & \text{(produttori)}
\end{align*}
$$
Per *comodità di notazione*, chiamiamo $x_{1}(k):=p(k)$.
Abbiamo allora:
$$
\begin{align*}
x_{1}(k+1) &= \frac{1}{a}(-q(k+1)+D) = -\frac{1}{a}(bp(k)+Q) + \frac{1}{a}D \\ \\
&= -\frac{b}{a}x_{1}(k) + \frac{1}{a}\underbrace{ (D-Q) }_{ := u_{1}(k) }
\end{align*}
$$
Per cui otteniamo:
$$
x_{1}(k+1) = -\frac{b}{a}x_{1}(k) + \frac{1}{a}u_{1}(k)
$$
Perciò il grafo associato al sistema è il seguente:

```tikz
\usetikzlibrary{arrows.meta, positioning}

\begin{document}
\begin{tikzpicture}[
    % Stili dei nodi
    stato/.style={circle, fill=gray!60, text=white, font=\bfseries, minimum size=0.9cm},
    esterno/.style={rectangle, draw=gray!60, thick, text=gray!60, font=\bfseries, minimum size=0.8cm},
    freccia/.style={-Stealth, thick, gray!80},
    % Stile per il loop (auto-influenza)
    loop_style/.style={looseness=5, out=150, in=210, min distance=1cm}
]

    % Nodi
    \node[stato] (N1) at (0,0) {1};
    \node[esterno, right=2cm of N1] (S1) {1};

    % Archi
    % Ingresso dall'esterno (quadrato) allo stato (cerchio)
    \draw[freccia] (S1) -- node[above, black] {$1/a$} (N1);

    % Self-loop (feedback -b/a) a sinistra dello stato
    \draw[freccia] (N1) edge [loop_style] node[left, black, xshift=-2pt, yshift=5pt] {$-b/a$} (N1);

\end{tikzpicture}
\end{document}
```
## ANDAMENTO DEL PREZZO: METODO GRAFICO
Analizziamo come *varia il prezzo* in funzione del *tempo $k$* con un *metodo grafico*.

```tikz
\usetikzlibrary{arrows.meta, intersections}

\begin{document}
\begin{tikzpicture}[
    scale=0.8,
    line join=round, 
    line cap=round,
    % Stile per le frecce della ragnatela
    cobweb/.style={dashed, thick, -{Stealth[scale=0.8]}}
]

    % 1. Definizione Assi
    \draw [-{Stealth}, thick] (0,0) -- (11,0) node[right] {$p$};
    \draw [-{Stealth}, thick] (0,0) -- (0,8) node[above] {$q$};

    % 2. Definizione e Disegno delle Rette (con nomi per calcolare intersezioni)
    % Domanda: q = -0.7p + 7
    \draw[ultra thick, red!50, name path=domanda] (0,7) -- (10,0) 
        node[pos=0.68, above right, black, sloped, font=\small] {funzione di domanda};
    
    % Offerta: q = 0.5p + 1
    \draw[ultra thick, blue!50, name path=offerta] (0,1) -- (10,6) 
        node[pos=0.68, above right, black, sloped, font=\small] {funzione di offerta};

    % 3. Calcolo Punto di Equilibrio (intersezione tra le due path)
    \fill [name intersections={of=domanda and offerta, by=E}, red] (E) circle (3pt);
    \draw[dashed, red] (E) -- (E |- 0,0) node[below] {$p_e$};
    \draw[dashed, red] (E) -- (0,0 |- E) node[left] {$q_e$};

    % 4. Costruzione della Dinamica (Ragnatela)
    % Definiamo i punti p(k) sull'asse x
    \coordinate (p0) at (9.5,0);
    \coordinate (p1) at (2.14,0); % Valore calcolato per poggiare sulla domanda
    \coordinate (p2) at (7.14,0);
    \coordinate (p3) at (3.8,0);

    % p(0) -> Offerta -> q(1)
    \path[name path=vert0] (9.5,0) -- (9.5,8);
    \path[name intersections={of=vert0 and offerta, by=Q1}];
    \draw[cobweb] (9.5,0.1) -- (Q1);
    \node[below, blue!70!black] at (9.5,0) {$p(0)$};

    % q(1) -> Domanda -> p(1)
    \path[name path=horiz1] (Q1) -- (0, 5.75); % linea orizzontale
    \path[name intersections={of=horiz1 and domanda, by=P1_fix}];
    \draw[cobweb] (Q1) -- (P1_fix);
    \draw[dashed, white] (P1_fix) -- (0,0 |- P1_fix) node[left, black] {$q(1)$};
    \draw[dashed, white] (P1_fix) -- (P1_fix |- 0,0) node[below, blue!70!black] {$p(1)$};

    % p(1) -> Offerta -> q(2)
    \path[name path=vert1] (P1_fix) -- (P1_fix |- 0,0);
    \path[name path=vert1_up] (P1_fix |- 0,0) -- (P1_fix |- 0,8);
    \path[name intersections={of=vert1_up and offerta, by=Q2}];
    \draw[cobweb] (P1_fix) -- (Q2);
    \draw[dashed, white] (Q2) -- (0,0 |- Q2) node[left, black] {$q(2)$};

    % q(2) -> Domanda -> p(2)
    \path[name path=horiz2] (Q2) -- (10, 2.07);
    \path[name intersections={of=horiz2 and domanda, by=P2_fix}];
    \draw[cobweb] (Q2) -- (P2_fix);
    \draw[dashed, white] (P2_fix) -- (P2_fix |- 0,0) node[below, blue!70!black] {$p(2)$};

    % p(2) -> Offerta -> q(3)
    \path[name path=vert2] (P2_fix |- 0,0) -- (P2_fix |- 0,8);
    \path[name intersections={of=vert2 and offerta, by=Q3}];
    \draw[cobweb] (P2_fix) -- (Q3);
    \draw[dashed, white] (Q3) -- (0,0 |- Q3) node[left, black] {$q(3)$};

    % q(3) -> Domanda -> p(3)
    \path[name path=horiz3] (Q3) -- (0, 4.57);
    \path[name intersections={of=horiz3 and domanda, by=P3_fix}];
    \draw[cobweb] (Q3) -- (P3_fix);
    \draw[dashed, white] (P3_fix) -- (P3_fix |- 0,0) node[below, blue!70!black] {$p(3)$};

    % Ultimo pezzetto verso l'interno
    \draw[cobweb] (P3_fix) -- (P3_fix |- 0,2.8);

\end{tikzpicture}
\end{document}
```

Supponiamo di analizzare un prodotto che parte dal prezzo $p(0)$:
1. Vedendo il *prezzo alto*, i produttori *immettono* sul mercato la *quantità* $q(1)$.
2. Con tale quantità, i *consumatori sono disposti a pagare* il prezzo $p(1)$.
3. Con un *prezzo così basso*, i *consumatori tagliano la produzione* e la merce immessa nel turno successivo è $q(2)$.
4. Adesso la *merce scarseggia* e i *consumatori* se la contendono, diventando *disposti a pagare di più*: prezzo $p(2)$.
5. Il *ciclo continua* fino a **raggiungere l'equilibrio**.

>[!note] NOTA
>Nel caso preso in esempio abbiamo $a>b$, il che *garantisce la convergenza verso il punto di equilibrio*.
>Se avessimo invece:
>1. $a<b$ : le *oscillazioni* avrebbero **ampiezza crescente** e non si raggiungerebbe l'equilibrio.
>2. $a=b$ : le *oscillazioni* avrebbero **ampiezza costante** e avverrebbero sempre tra gli *stessi prezzi e quantità di merce*.
## ANDAMENTO DEL PREZZO: METODO ANALITICO
Sfruttiamo ora il modello per analizzare la dinamica e *ritrovare gli stessi comportamenti*.
Avevamo trovato:
$$
x_{1}(k+1) = -\frac{b}{a}x_{1}(k) + \frac{\bar{u}_{1}}{a}
$$
All'**equilibrio** abbiamo:
$$
\bar{x}_{1} = x_{1}(k+1) = x_{1}(k)
$$
Per cui, sostituendo nell'equazione sopra, troviamo:
$$
\bar{x}_{1} = \frac{1}{a+b}\bar{u}_{1} := \bar{p}
$$
Eseguiamo ora un *cambiamento di variabile*:
$$
\tilde{x}_{1}(k) := x_{1}(k) - \bar{x}_{1}
$$

>[!note] NOTA
>Ancora una volta, stiamo considerando lo **scostamento rispetto all'equilibrio**.

Abbiamo quindi:
$$
\begin{align*}
\tilde{x}_{1}(k+1) &= -\frac{b}{a}x_{1}(k) + \frac{1}{a}\bar{u}_{1} - \bar{x}_{1} \\ \\
&= -\frac{b}{a}(x_{1}(k)-\bar{x}_{1}) -\frac{b}{a}\bar{x}_{1} + \frac{1}{a}\bar{u}_{1} - \bar{x}_{1} \\ \\
&= -\frac{b}{a}\tilde{x}_{1}(k) -\frac{1}{a} \cancelto{ 0 }{ ((a+b)\bar{x}_{1} - \bar{u}_{1}) }
\end{align*}
$$
E troviamo:
$$
\tilde{x}_{1}(k+1) = -\frac{b}{a}\tilde{x}_{1}(k)
$$
>[!idea] CONSEGUENZA
>Il risultato trovato è *in accordo* con *quanto visto graficamente*:
>1. Se $\frac{b}{a}<1$ si *converge all'equilibrio*.
>2. Se $\frac{b}{a}>1$ si ha *divergenza*.
>3. Se $\frac{b}{a}=1$ le *oscillazioni* hanno *ampiezza costante*.
