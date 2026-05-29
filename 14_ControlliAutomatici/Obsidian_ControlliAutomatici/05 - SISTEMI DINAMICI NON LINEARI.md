# INDICE SEZIONE
- [ ] [[#PENDOLO SEMPLICE]]
      - [[#PUNTI DI EQUILIBRIO PENDOLO]]
- [ ] [[#SERBATOIO]]
      - [[#PUNTI DI EQUILIBRIO SERBATOIO]]
- [ ] [[#POPOLAZIONE (MODELLO LOGISTICO)]]
      - [[#PUNTI DI EQUILIBRIO POPOLAZIONE]]
- [ ] [[#SISTEMI LINEARI VS NON LINEARI]]
      - [[#TEMPO CONTINUO]]
      - [[#TEMPO DISCRETO]]
- [ ] [[#ANALISI DEI PUNTI DI EQUILIBRIO]]
      - [[#ANALISI TEMPO CONTINUO]]
      - [[#ANALISI TEMPO CONTINUO]]
- [ ] [[#LINEARIZZAZIONE]]
      - [[#IN TEMPO CONTINUO]]
      - [[#IN TEMPO DISCRETO]]
# PENDOLO SEMPLICE
Consideriamo il pendolo in figura:

```tikz
\usepackage{amsmath}
\usetikzlibrary{arrows.meta, calc}

\definecolor{myblue}{RGB}{40, 130, 180}

\begin{document}
\begin{tikzpicture}[
    % Stili per le frecce delle forze e dell'asta
    force/.style={thick, -{Triangle[length=4.5mm, width=3mm, fill=white]}},
    rodarrow/.style={thick, -{Triangle[length=4.5mm, width=3mm, fill=white]}},
    grayarrow/.style={thick, gray!90!black, -{Latex[length=2.5mm, width=2.5mm]}},
    bluearrow/.style={thick, myblue, <-}
]

    % Parametri principali
    \def\angle{-55}
    \def\L{5}
    \coordinate (O) at (0,0);
    \coordinate (B) at (\angle:\L);

    % Asse verticale tratteggiato
    \draw[dashed, thick, gray!80!black] (0, 1.5) -- (0, -7);

    % Traiettoria tratteggiata della massa
    \draw[dashed, thick, gray!80!black, -{Latex[length=3mm, width=2.5mm]}] (-95:\L) arc (-95:-20:\L);

    % Coppia C(t) = u(t)
    \draw[bluearrow] (180:-0.8) arc (180:60:-0.8) node[right=1cm, myblue] {$C(t) = u(t)$};

    % Angolo theta al perno
    \draw[grayarrow] (0, -1.5) arc (-90:\angle:1.5) node[midway, below=0.1cm, text=black] {$\vartheta$};

    % Asta del pendolo (spezzata per inserire la freccia cava a metà)
    \draw[thick] (O) -- (\angle:2.5);
    \draw[rodarrow] (B) -- (\angle:2.5);
    \node at (\angle:2.5) [above right=0.1cm] {$l$};

    % Massa (Bob)
    \filldraw[fill=lightgray!80, draw=black, thick] (B) circle (0.35) node[right=0.45cm] {$M$};
    % Linea di riferimento tratteggiata interna alla massa
    \draw[dashed, gray!80!black] (\angle:4.65) -- (\angle:5.35);

    % Forze
    % Forza peso (verticale)
    \draw[force] (B) -- ++(0,-2.5) node[left] {$M\mathbf{g}$};

    % Componente parallela (lungo l'asta)
    \draw[force] (B) -- ++(\angle:2.5) node[right] {$\mathbf{g}\cos\vartheta M$};

    % Componente perpendicolare (tangente alla traiettoria)
    \draw[force] (B) -- ++(\angle-90:2.2) node[below left] {$M\mathbf{g}\sin\vartheta$};

    % Angolo theta alla massa
    \draw[gray!90!black, thick] (B) ++(0,-1.2) arc (-90:\angle:1.2) node[midway, below=0.15cm, text=black] {$\vartheta$};

\end{tikzpicture}
\end{document}
```

L'equazione che descrive il sistema è (*bilancio delle coppie*):
$$
J \ddot{\theta}(t) = -Mgl\sin\theta -q_{r}\dot{\theta}(t) + C(t)
$$
>[!note]
>Abbiamo un *disturbo esterno* dato dalla coppia $C(t)$ e un *attrito viscoso* dato da $q_{r}\dot{\theta}(t)$.

Scegliamo:
$$
\begin{align*}
x_{1}(t):= \theta(t) & & x_{2}(t) := \dot{\theta}(t) & & u(t) := C(t)
\end{align*}
$$
Allora l'equazione sopra diventa:
$$
\ddot{\theta}(t) = -\frac{g}{l}\sin\theta(t) -\frac{q_{r}}{Ml^2}\dot{\theta}(t) +\frac{1}{Ml^2}u(t)
$$
Per cui le equazioni associate al sistema sono (ricordiamo $J=Ml^2$):
$$
\begin{cases}
\dot{x}_{1}(t) = x_{2}(t) \\ \\
\dot{x}_{2}(t) = -\frac{g}{l}\sin x_{1}(t) -\frac{q_{r}}{Ml^2}x_{2}(t) +\frac{1}{Ml^2}u(t)
\end{cases}
$$

>[!note] NON LINEARITA'
>La **non linearità** è data dal termine $\sin x_{1}(t)$.
## PUNTI DI EQUILIBRIO PENDOLO
Ora vogliamo trovare i *punti di equilibrio*, che ricordiamo essere soluzioni costanti del tipo $x(t)=\bar{x}$ e $u(t)=\bar{u}$.

Abbiamo quindi:
$$
\begin{cases}
0 = \dot{\bar{x}}_{1} = \bar{x}_{2} \\ \\
0 = \dot{\bar{x}}_{2} = -\frac{g}{l}\sin \bar{x}_{1} -\cancelto{ 0 }{ \frac{q_{r}}{Ml^2}\bar{x}_{2} } +\frac{1}{Ml^2}\bar{u}
\end{cases}
$$
E troviamo allora:
$$
\sin \bar{x}_{1} = \frac{1}{Mgl}\bar{u}
$$
Supponiamo che la *coppia applicata dall'esterno sia nulla*: abbiamo quindi
$$
\sin \bar{x}_{1} = 0
$$

```tikz
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}[x=1cm, y=1.2cm] 

    \draw (-3.8, 0) -- (8.5, 0); % Asse X (si estende un po' oltre 2pi)
    \draw (0, -1.2) -- (0, 1.2); % Asse Y

    \draw[thick, domain=-3.5:8, samples=100] plot (\x, {sin(\x r)});

    \filldraw (0,0) circle (1.5pt);
    \node at (0.3, -0.25) {$0$};

    \filldraw[thick] (pi,0) circle (1.5pt);
    \node at (pi, -0.3) {$\pi$};

    \filldraw (2*pi,0) circle (1.5pt);
    \node at (2*pi, -0.3) {$2\pi$};

\end{tikzpicture}
\end{document}
```

Per cui i *punti di equilibrio* sono del tipo:
$$
\bar{x} = (\bar{x}_{1}, \bar{x}_{2}) = (k\pi,0)
$$
Per analizzare i punti di equilibrio, conviene considerare le seguenti variabili:
$$
\begin{align*}
\begin{cases}
\tilde{x}_{1}(t) := x_{1}(t) -\bar{x}_{1} \\ \\
\tilde{x}_{2}(t) := x_{2}(t) -\bar{x}_{2}
\end{cases} & & \begin{cases}
x_{1}(t) = \tilde{x}_{1}(t) +\bar{x}_{1} \\ \\
x_{2}(t) = \tilde{x}_{2}(t) +\bar{x}_{2}
\end{cases}
\end{align*}
$$
>[!note] SIGNIFICATO
>Stiamo ancora una volta considerando lo *scostamento dal valore di equilibrio*, considerando questo come "*nuovo zero*" (punto di riferimento).
### PUNTO 1
Analizziamo il punto:
$$
\begin{align*}
\begin{cases}
\bar{x}_{1} = 0 \\ \\
\bar{x}_{2} = 0
\end{cases} & & \begin{cases}
\tilde{x}_{1}(t) = x_{1}(t) - 0 = x_{1}(t) \\ \\
\tilde{x}_{2}(t) = x_{2}(t) - 0 = x_{2}(t)
\end{cases}
\end{align*}
$$
Abbiamo quindi:
$$
\begin{cases}
\dot{\tilde{x}}_{1}(t) = \tilde{x}_{2}(t) + 0 \\ \\
\dot{\tilde{x}}_{2}(t) = -\frac{g}{l}\sin(\tilde{x}_{1}(t)+0) -\frac{q_{r}}{Ml^2}(\tilde{x}_{2}(t)+0)
\end{cases}
$$
>[!idea] IDEA: LINEARIZZAZIONE
>Ora vorremmo applicare l'*approssimazione dei piccoli angoli* per ottenere un'**approssimazione lineare**.
>
>E' *lecito* farlo? Si, perchè all'equilibrio i valori di $x_{1}$ e $x_{2}$ **rimangono pressochè constanti** e *vicini al valore di equilibrio* (per cui $\tilde{x}\approx 0$).

L'equazione relativa a $\dot{\tilde{x}}_{2}(t)$ diventa allora:
$$
\dot{\tilde{x}}_{2}(t) = -\frac{g}{l}\tilde{x}_{1}(t) -\frac{q_{r}}{Ml^2}\tilde{x}_{2}(t)
$$
Data questa *linearizzazione*, possiamo scrivere le due equazioni per $\tilde{x}$ nella forma compatta $\dot{\tilde{x}}=A\tilde{x}$ con:
$$
A = \begin{bmatrix}
0 & 1 \\
-\frac{g}{l} & -\frac{q_{r}}{Ml^2}
\end{bmatrix}
$$
Per trovare gli autovalori, calcoliamo gli zeri del *polinomio caratteristico* $\det(A-\lambda I)=0$: 
$$
\lambda^2 + \frac{q_{r}}{Ml^2}\lambda + \frac{g}{l} = 0
$$
>[!note] NOTA
>Tutti i *segni* dei coefficienti del polinomio di secondo grado sono positivi.
>Allora le *radici* (e quindi gli autovalori) avranno tutti **parte reale negativa**.

Per cui le soluzioni al sistema saranno del tipo:
$$
\begin{align*}
\tilde{x} \propto e^{ -\alpha t } & & \alpha \in \mathbb{R}\text{ , }\alpha>0
\end{align*} 
$$
>[!idea] CONSEGUENZA
>Le *deviazioni dall'equilibrio* saranno *smorzate nel tempo* e il pendolo *convergerà verso l'equilibrio*.
>Questo punto è quindi un **punto di equilibrio stabile**.
### PUNTO 2
Analizziamo ora il punto:
$$
\begin{align*}
\begin{cases}
\bar{x}_{1} = \pi \\ \\
\bar{x}_{2} = 0
\end{cases} & & \begin{cases}
\tilde{x}_{1}(t) = x_{1}(t) - \pi \\ \\
\tilde{x}_{2}(t) = x_{2}(t) - 0
\end{cases}
\end{align*}
$$
Abbiamo quindi:
$$
\begin{cases}
\dot{\tilde{x}}_{1}(t) = \tilde{x}_{2}(t) + 0 \\ \\
\dot{\tilde{x}}_{2}(t) = -\frac{g}{l}\sin(\tilde{x}_{1}(t)+\pi) -\frac{q_{r}}{Ml^2}(\tilde{x}_{2}(t)+0)
\end{cases}
$$
Per lo stesso motivo di prima, possiamo considerare $\tilde{x}_{1}\approx 0$, ottenendo così la *linearizzazione* seguente:
$$
\begin{align*}
\sin(\tilde{x}_{1}+\pi) &= \sin(\tilde{x}_{1})\cos \pi + \cos(\tilde{x}_{1})\sin \pi \\ \\
&\approx -\tilde{x}_{1}
\end{align*}
$$
L'equazione relativa a $\dot{\tilde{x}}_{2}(t)$ diventa allora:
$$
\dot{\tilde{x}}_{2}(t) = \frac{g}{l}\tilde{x}_{1} - \frac{q_{r}}{Ml^2}\tilde{x}_{2}(t)
$$
In questo caso la matrice $A$ è:
$$
\begin{bmatrix}
0 & 1 \\
+\frac{g}{l} & -\frac{q_{r}}{Ml^2}
\end{bmatrix}
$$
Per cui il *polinomio caratteristico* è:
$$
\lambda^2 +\frac{q_{r}}{Ml^2}\lambda -\frac{g}{l}
$$
>[!note] NOTA
>Il fatto che il *termine noto* abbia *segno negativo* implica la presenza di *una radice positiva* e *una negativa*.

>[!idea] CONSEGUENZA
>Allora $\tilde{x}$ contiene un termine che *cresce esponenzialmente* ($\propto e^{ \lambda t }\text{ , }\lambda>0$): questo è un **punto di equilibrio instabile**.
# SERBATOIO
Torniamo a considerare un *serbatoio* con una *valvola d'ingresso* ed un *flusso di uscita*, come in figura:

```tikz
\usepackage{amsmath}
\usetikzlibrary{shapes.geometric, positioning, calc, arrows.meta, decorations.pathmorphing}

% Definizione del colore personalizzato per l'acqua, come da tuo codice
\definecolor{water}{RGB}{173,216,230} 

\begin{document}
\begin{tikzpicture}[
    >={Stealth[scale=1.2]}, % Imposta lo stile della punta delle frecce
    line width=1.2pt,       % Spessore aumentato per emulare il tratto a penna
    label_font/.style={font=\Large}
]

% --- Definizione dei parametri geometrici ---
\def\tankw{4.0}
\def\tankh{5.0}
\def\waterh{3.2}
\def\pipew{0.5}
\def\pipexin{-2.2}
\def\pipexout{0.2}
\def\pipey{5.5}
\def\valveposx{-1.2}

% --- 1. Disegno dell'acqua ---
% Riempimento dell'acqua nel serbatoio
\fill [water] (0,0) rectangle (\tankw, \waterh);
% Riempimento dell'acqua nel tubo di uscita (in basso a destra)
\fill [water] (\tankw, 0) rectangle (\tankw+1.2, \pipew);

% Superficie ondulata dell'acqua
\draw [thick, decorate, decoration={snake, segment length=6mm, amplitude=0.6mm}] (0, \waterh) -- (\tankw, \waterh);

% Etichetta x(t) al centro del liquido
\node at (\tankw/2, \waterh/2) {\Huge $x(t)$};

% --- 2. Disegno del serbatoio ---
% Parete sinistra e fondo
\draw (0, \tankh) -- (0,0) -- (\tankw,0);
% Parete destra divisa dal tubo di uscita
\draw (\tankw, \tankh) -- (\tankw, \pipew) -- (\tankw+1.2, \pipew);
\draw (\tankw, 0) -- (\tankw+1.2, 0);

% Freccia del flusso in USCITA
\draw [->, line width=1.5pt] (\tankw+1.4, \pipew/2) -- (\tankw+2.2, \pipew/2);
\node [above=7pt, font=\Large] at (\tankw+1.4, \pipew/2) {flusso out};

% --- 3. Tubo di INGRESSO ---
% Tratto superiore e inferiore del tubo orizzontale
\draw (\pipexin, \pipey+\pipew/2) -- (\pipexout, \pipey+\pipew/2);
\draw (\pipexin, \pipey-\pipew/2) -- (\pipexout, \pipey-\pipew/2);

% Testo "flusso in"
\node [right, font=\Large] at (\pipexout+0.5, \pipey+0.2) {flusso in};

% Freccia verticale di caduta dell'acqua
\draw [->, line width=1.5pt] (\pipexout+0.2, \pipey-\pipew/2 - 0.2) -- (\pipexout+0.2, \pipey-1.2);

% --- 4. Valvola di INGRESSO (stile farfalla/clessidra) ---
% Rettangolo bianco nascosto per interrompere visivamente le linee del tubo sotto la valvola
\fill[white] (\valveposx-0.45, \pipey-0.6) rectangle (\valveposx+0.45, \pipey+0.6);

% Triangolo superiore e inferiore della valvola
\draw [fill=white] (\valveposx-0.4, \pipey+0.6) -- (\valveposx+0.4, \pipey+0.6) -- (\valveposx, \pipey) -- cycle;
\draw [fill=white] (\valveposx-0.4, \pipey-0.6) -- (\valveposx+0.4, \pipey-0.6) -- (\valveposx, \pipey) -- cycle;
% Linea orizzontale al centro per separare i triangoli se si toccano
\draw (\valveposx-0.4, \pipey+0.6) -- (\valveposx+0.4, \pipey+0.6);

% Attuatore (asta e pallino pieno)
\draw (\valveposx, \pipey+0.6) -- (\valveposx, \pipey+1.2);
\filldraw (\valveposx, \pipey+1.2) circle (3pt);

% Etichetta u(t) sopra l'attuatore
\node at (\valveposx-0.3, \pipey+1.8) [label_font] {$u(t)$};

\end{tikzpicture}
\end{document}
```

Il *grafo* associato a questo sistema è il seguente:

```tikz
\usepackage{tikz}
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
% Nodo 1: Ingresso
\node[box, label=above:{$u(t)$}] (u) {};

% Nodo 2: Stato
\node[state, right=of u, label=above:{$x(t)$}] (x) {};

% Coordinata invisibile per l'uscita
\coordinate[right=of x] (out);

% --- Frecce e pesi ---
% Da u(t) a x(t)
\draw[->] (u) -- node[above] {$1$} (x);

% Da x(t) verso l'esterno
\draw[->] (x) -- node[above] {$\alpha$} (out);

\end{tikzpicture}

\end{document}
```

Se volessimo descrivere questo sistema con lo stesso approccio adottato in > [[01 - MODELLI DI TRASFERIMENTO DI RISORSE (TEMPO CONTINUO)]] scriveremmo un'equazione del tipo:
$$
\dot{x}(t) = u(t) -\alpha x(t)
$$
>[!note] NOTA
>Si tratta tuttavia di un'*imprecisione*: la *velocità* con cui il *liquido fuoriesce* è data dalla **legge di Torricelli** ed è *proporzionale* alla *radice quadrata* dell'*altezza del liquido*.

Abbiamo allora:
$$
\dot{x}(t) = u(t) -\alpha \sqrt{ x(t) }
$$
E otteniamo così un **modello non lineare**.
## PUNTI DI EQUILIBRIO SERBATOIO
Per trovare i *punti di equilibrio*, cerchiamo sempre soluzioni costanti del tipo
$$
\begin{align*}
x(t) = \bar{x} & & u(t) = \bar{u}
\end{align*}
$$
Sostituendo, troviamo:
$$
\bar{u} - \alpha \sqrt{ \bar{x} } = 0
$$
A questo punto abbiamo *due modi di interpretare questa relazione*:
1. Fissiamo lo **stato desiderato** $\bar{x}$: allora per mantenerlo vogliamo che l'ingresso sia $\bar{u}=\alpha \sqrt{ \bar{x} }$.
2. Fissiamo l'**ingresso** $\bar{u}$: allora il sistema si stabilizzerà al livello $\bar{x}=\frac{\bar{u}^2}{\alpha^2}$.
# POPOLAZIONE (MODELLO LOGISTICO)
Sia $x(t)$ il *numero di individui* di una *popolazione* con *una sola classe d'età*.

```tikz
\usepackage{amsmath}
\usetikzlibrary{positioning, arrows.meta}

\begin{document}

\begin{tikzpicture}[
    auto,
    thick,
    > = {Stealth[scale=1.2]},      % Stile della punta delle frecce
    node distance= 2.5cm,          % Distanza orizzontale tra i nodi
    thick,                         % Spessore generale delle linee
    % Stile per i blocchi quadrati (sorgente/pozzo)
    box/.style={
        rectangle,
        draw=black,
        minimum size=1.1cm,
        inner sep=0.5em,
        font=\Large,
        align=center
    },
    % Stile per il blocco circolare (stato)
    state/.style={
        circle,
        draw=black,
        minimum size=1.4cm,        % Bilanciamento visivo con il rettangolo
        inner sep=0pt,
        font=\Large
    }
]

    % --- Posizionamento dei Nodi ---
    % Nodo 1: Sorgente (u) con etichetta 'u(t)' sopra
    \node[box, label=above:{$u(t)$}] (u) {u};

    % Nodo 2: Stato (x) con etichetta 'x(t)' sopra (cerchio vuoto)
    \node[state, right=of u, label=above:{$x(t)$}] (x) {};

    % --- Frecce e pesi ---
    % Freccia da u a x
    \draw[->] (u) -- (x);

    % Auto-ciclo su x con etichetta 'gamma' a destra
    \draw[->] (x) to [out=30, in=330, looseness=8] node[midway, right=1.2em, font=\Large] {$\gamma$} (x);

\end{tikzpicture}

\end{document}
```
Consideriamo un *modello lineare*:
$$
\dot{x}(t) = \gamma x(t) + u(t)
$$
Se ad esempio consideriamo $u(t)=0$, $\gamma>0$, allora:
$$
x(t) = x(0)e^{ \gamma t }
$$
>[!note] NOTA
>Questo modello lineare è **poco realistico** in quanto il *tasso di crescita* $\gamma$ dovrebbe essere **funzione del numero di individui**: più individui $\to$ più consumo risorse (che sono limitate).

Consideriamo allora un **modello logistico**, che **non è lineare**:
$$
\begin{align*}
\dot{x}(t) &= \gamma(x(t))x(t) + u(t) \\ \\
&= a\left( 1- \frac{x(t)}{k} \right)x(t) + u(t)
\end{align*}
$$
Cioè in questo caso abbiamo:
$$
\gamma(x) = a\left( 1- \frac{x(t)}{k} \right)
$$
>[!note] NOTA
>In questo caso, quando la *popolazione* $x(t)$ raggiunge il *valore di equilibrio* $k$, si *assesta proprio su questo valore*.

In particolare, l'andamento della popolazione è del tipo:

```tikz
\usepackage{amsmath}
\usetikzlibrary{arrows.meta}

\begin{document}

\begin{tikzpicture}[
    >= {Stealth[scale=1.2]}, % Stile delle frecce
    thick,
    scale=1.2 % Scala generale del grafico
]

    % --- Assi ---
    % Asse dei tempi (t)
    \draw[->] (-0.2, 0) -- (5, 0) node[right, font=\Large] {$t$};
    
    % Asse dello stato (x(t))
    \draw[->] (0, -0.2) -- (0, 3.5) node[above, font=\Large] {$x(t)$};

    % --- Asintoto ---
    % Linea tratteggiata per la capacità portante (K)
    \draw[dashed] (0, 1.5) node[left, font=\Large] {$k$} -- (4.5, 1.5);

    % --- Curve ---
    % Curva inferiore (crescita verso K)
    \draw[line width=2pt] (0.2, 0.1) to[out=15, in=180] (3.8, 1.4);

    % Curva superiore (decrescita verso K)
    \draw[line width=2pt] (0.2, 2.7) to[out=-45, in=180] (3.8, 1.6);

    % --- Equazione ---
    % Testo della funzione
    \node[font=\Large] at (2.5, 2.8) {$\gamma(x) = a \left(1 - \frac{x}{k}\right)$};

\end{tikzpicture}

\end{document}
```

Dal grafico vediamo che:
- Se $x(t)<k$ allora $\gamma(x)>0$ e la popolazione *cresce*.
- Se $x(t)>k$ allora $\gamma(x)<0$ e la popolazione *diminuisce*.
## PUNTI DI EQUILIBRIO POPOLAZIONE
Cerchiamo ancora un volta i punti di equilibrio, ovvero soluzioni costanti del tipo $x(t)=\bar{x}$ e $u(t)=\bar{u}$.
Abbiamo quindi:
$$
0 = \dot{\bar{x}} = a\left( 1- \frac{\bar{x}}{k} \right)\bar{x} + \bar{u}
$$
Se fissiamo $\bar{u}$ possiamo ricavare:
$$
-\bar{u} = a\left( 1 -\frac{\bar{x}}{k} \right)\bar{x}
$$

```tikz
\usepackage{amsmath}
\usetikzlibrary{arrows.meta}

\begin{document}

\begin{tikzpicture}[
    >= {Stealth[scale=1.2]},      % Stile delle frecce
    thick,                        % Spessore di base delle linee
    scale=1.2                     % Scala generale del grafico
]

    % --- Assi ---
    % Asse x
    \draw[->] (-1, 0) -- (5.5, 0) node[right, font=\Large] {$x$};
    
    % Asse verticale (nessuna etichetta esplicita nell'immagine per l'asse stesso)
    \draw[->] (0, -1) -- (0, 4);

    % --- Curva ---
    % Parabola con concavità verso il basso passante per (0,0) e (4,0).
    % Equazione usata: y = 0.5 * x * (4 - x)
    \draw[line width=1.5pt, domain=-0.8:4.8, samples=100] plot (\x, {0.5*\x*(4-\x)});

    % --- Linee tratteggiate orizzontali ---
    % Linea tratteggiata superiore (non interseca la parabola)
    \draw[dashed] (0, 3) -- (5, 3);
    
    % Linea tratteggiata inferiore (interseca la parabola) con etichetta -\bar{u}
    \draw[dashed] (0, 1.2) node[left, font=\Large] {$-\bar{u}$} -- (5, 1.2);

    % --- Punti (pallini neri) ---
    % Punto nell'origine
    \filldraw (0,0) circle (3.5pt);
    
    % Punto in K
    \filldraw (4,0) circle (3.5pt) node[below left, font=\Large, yshift=-3pt] {$k$};

\end{tikzpicture}

\end{document}
```

>[!note] NOTA
>- Se $\bar{u}=0$ allora $\bar{x}=\{ 0,k \}$
>- Se $\bar{u}$ assume *valori troppo elevati* allora non ci sono soluzioni reali positive, e quindi *non esiste un equilibrio*.
# SISTEMI LINEARI VS NON LINEARI
Supponiamo di avere:
- $n$ *stati* : $x_{1}(t),\dots,x_{n}(t)$
- $m$ *ingressi* : $u_{1}(t),\dots,u_{n}(t)$
## TEMPO CONTINUO
Un **sistema lineare** è del tipo:
$$
\begin{cases}
\dot{x}_{1}(t) = a_{11}x_{1}(t) + \ldots + a_{1n}x_{n}(t) + b_{11}u_{1}(t) + \ldots + b_{1m}u_{m}(t) \\
\dots \\
\dot{x}_{2}(t) = a_{n1}x_{1}(t) + \ldots + a_{nn}x_{n}(t) + b_{n1}u_{1}(t) + \ldots + b_{nm}u_{m}(t)
\end{cases}
$$
Che scriviamo nella *forma compatta*:
$$
\dot{x}_{n\times 1}(t) = A_{n\times n}x_{n\times 1}(t) + B_{n\times m}u_{m\times 1}(t)
$$

Un **sistema non lineare** invece è del tipo:
$$
\begin{cases}
\dot{x}_{1}(t) = f_{1}(x_{1}(t),\dots,x_{n}(t), u_{1}(t),\dots,u_{m}(t)) \\
\dots \\
\dot{x}_{n}(t) = f_{n}(x_{1}(t),\dots,x_{n}(t), u_{1}(t),\dots,u_{m}(t))
\end{cases}
$$
Che scriviamo nella *forma compatta*:
$$
\dot{x}_{n\times 1}(t) = \underbrace{ f(x_{n\times 1}(t), u_{m\times1}(t)) }_{ \mathbb{R}^{n+m} \to \mathbb{R}^n }
$$
## TEMPO DISCRETO
Un **sistema lineare** è del tipo:
$$
\begin{cases}
x_{1}(k+1) = a_{11}x_{1}(k) + \ldots + a_{1n}x_{n}(k) + b_{11}u_{1}(k) + \ldots + b_{1m}u_{m}(k) \\
\dots \\
x_{2}(k+1) = a_{n1}x_{1}(k) + \ldots + a_{nn}x_{n}(k) + b_{n1}u_{1}(k) + \ldots + b_{nm}u_{m}(k)
\end{cases}
$$
Che scriviamo nella *forma compatta*:
$$
x_{n\times 1}(k+1) = A_{n\times n}x_{n\times 1}(k) + B_{n\times m}u_{m\times 1}(k)
$$

Un **sistema non lineare** invece è del tipo:
$$
\begin{cases}
x_{1}(k+1) = f_{1}(x_{1}(t),\dots,x_{n}(k), u_{1}(t),\dots,u_{m}(k)) \\
\dots \\
x_{n}(k+1) = f_{n}(x_{1}(t),\dots,x_{n}(k), u_{1}(t),\dots,u_{m}(k))
\end{cases}
$$
Che scriviamo nella *forma compatta*:
$$
x_{n\times 1}(k+1) = \underbrace{ f(x_{n\times 1}(k), u_{m\times1}(k)) }_{ \mathbb{R}^{n+m} \to \mathbb{R}^n }
$$
# ANALISI DEI PUNTI DI EQUILIBRIO
## ANALISI TEMPO CONTINUO
Per tali sistemi abbiamo:
$$
\begin{align*}
x_{1}(t) = \bar{x}_{1} ,\dots, x_{n}(t) = \bar{x}_{n} & & u_{1}(t) = \bar{u}_{1},\dots,u_{m}(t) = \bar{u}_{m} & & \forall t\geq 0
\end{align*}
$$
Tali **valori di equilibrio** sono ottenuti *risolvendo* $n$ *equazioni* in $n+m$ *incognite*:
$$
\begin{align*}
\dot{\bar{x}}_{1} &= 0 = f_{1}(\bar{x}_{1}, \dots, \bar{x}_{n}, \bar{u}_{1}, \dots, \bar{u}_{m}) \\
&\dots \\
\dot{\bar{x}}_{n} &= 0 = f_{n}(\bar{x}_{1}, \dots, \bar{x}_{n}, \bar{u}_{1}, \dots, \bar{u}_{m})
\end{align*}
$$
Questi stati sono detti **stati stazionari**.
## ANALISI TEMPO DISCRETO
Per sistemi a *tempo discreto*, la situazione è *molto simile*:
$$
\begin{align*}
x_{1}(k) = \bar{x}_{1} ,\dots, x_{n}(k) = \bar{x}_{n} & & u_{1}(k) = \bar{u}_{1},\dots,u_{m}(k) = \bar{u}_{m} & & \forall k \geq 0
\end{align*}
$$
Tali **valori di equilibrio** sono ottenuti *sempre risolvendo* $n$ *equazioni* in $n+m$ *incognite*:
$$
\begin{align*}
\bar{x}_{1} &= 0 = f_{1}(\bar{x}_{1}, \dots, \bar{x}_{n}, \bar{u}_{1}, \dots, \bar{u}_{m}) \\
&\dots \\
\bar{x}_{n} &= 0 = f_{n}(\bar{x}_{1}, \dots, \bar{x}_{n}, \bar{u}_{1}, \dots, \bar{u}_{m})
\end{align*}
$$
Questi punti sono detti **punti fissi**.
# LINEARIZZAZIONE
Lavorare con *equazioni non lineari è particolarmente difficile*.
Come abbiamo visto negli esempi sopra, è utile considerare delle **approssimazioni lineari**.

>[!idea] LINEARIZZAZIONE INTORNO ALL'EQUILIBRIO
>*Intorno* ai *punti di equilibrio*, le **variazioni del sistema** (*segnali*) sono **sufficientemente piccoli** da **giustificare** l'utilizzo di un'**approssimazione lineare**. 

Tale approssimazione sfrutta l'*espansione di Taylor*:
```tikz
\usepackage{pgfplots}
\usepackage{amsmath}
\usetikzlibrary{arrows.meta, positioning, calc}

\begin{document}

\begin{tikzpicture}

    % ==========================================
    % GRAFICO SINISTRO: Superficie Non Lineare
    % ==========================================
    \begin{axis}[
        name=surfaceplot,
        width=8cm, height=7cm,
        view={120}{30},           
        xlabel={$x_1$}, ylabel={$x_2$},
        xlabel style={font=\Large}, ylabel style={font=\Large},
        xtick={0,2,4}, ytick={0,2,4},
        ztick=\empty,             
        colormap={monochrome}{color=(white) color=(gray!40)},
        mesh/interior colormap={monochrome}{color=(gray!10) color=(gray!20)},
        faceted color=black!30,   
        axis line style={draw=black!30}, 
        domain=0:4, y domain=0:4,
        samples=25,               
    ]
        % Disegna la superficie 
        \addplot3[surf] {0.8 * exp(-0.4*(x-2)^2 - 0.4*(y-2)^2)};

        % Punto scelto sulla "discesa" della collina. 
        % Aggiunto 'axis cs:' per la retrocompatibilità
        \coordinate (Xbar1) at (axis cs: 3, 3, 0.358); 
        \fill (Xbar1) circle (1.5pt) node[below left, font=\large, xshift=-1pt, yshift=-1pt] {$\bar{x}$};

        % Coordinate del quadratino (calcolate esplicitamente per massima sicurezza: 3±0.3)
        \coordinate (B1) at (axis cs: 2.7, 2.7, 0.6);
        \coordinate (B2) at (axis cs: 3.3, 2.7, 0.17);
        \coordinate (B3) at (axis cs: 3.3, 3.3, 0.17);
        \coordinate (B4) at (axis cs: 2.7, 3.3, 0.6);
        
        \draw[thick, black] (B1) -- (B2) -- (B3) -- (B4) -- cycle;
    \end{axis}

    % ==========================================
    % GRAFICO DESTRO: Piano Tangente (Zoom)
    % ==========================================
    \begin{axis}[
        name=planeplot,
        at={(surfaceplot.right of origin)}, anchor=left of origin, xshift=3cm, 
        width=8cm, height=7cm,
        view={120}{30},
        xlabel={$x_1$}, ylabel={$x_2$},
        xlabel style={font=\Large}, ylabel style={font=\Large},
        xtick={-1,0,1}, ytick={-1,0,1},
        xticklabels={2.95, 3.00, 3.05}, 
        yticklabels={2.95, 3.00, 3.05}, 
        ztick=\empty,
        colormap={monochrome}{color=(white) color=(gray!20)},
        faceted color=black!30,   
        axis line style={draw=black!30}, 
        domain=-1:1, y domain=-1:1,
        samples=15,
    ]
        % Disegna il piano inclinato 
        \addplot3[surf] {-0.32*x - 0.32*y};

        % Coordinate degli angoli del piano (con axis cs:)
        \coordinate (P1) at (axis cs: -1, -1, 0.64);
        \coordinate (P2) at (axis cs: 1, -1, 0);
        \coordinate (P3) at (axis cs: 1, 1, -0.64);
        \coordinate (P4) at (axis cs: -1, 1, 0);

        % Punto centrale \bar{x} sul piano
        \coordinate (Xbar2) at (axis cs: 0, 0, 0);
        \fill (Xbar2) circle (2pt) node[below right, font=\large, xshift=-1pt, yshift=-1pt] {$\bar{x}$};

        % Assi locali grigi e inclinati
        \draw[very thick, gray!80] (axis cs: -1, 0, 0.32) -- (axis cs: 1, 0, -0.32); 
        \draw[very thick, gray!80] (axis cs: 0, -1, 0.32) -- (axis cs: 0, 1, -0.32); 
        
    \end{axis}

    % ==========================================
    % CONNESSIONI (Effetto Zoom)
    % ==========================================
    \draw[dashed, thin] (B4) -- (P4);
    \draw[dashed, thin] (B1) -- (P1);
    \draw[dashed, thin] (B2) -- (P2);
    \draw[dashed, thin] (B3) -- (P3);

    % ==========================================
    % ETICHETTE DELLE DERIVATE CON FRECCE CURVE
    % ==========================================
    \node[font=\large] (deriv1) at ([yshift=3.5cm, xshift=-2cm]planeplot.center) {$\left. \frac{\partial f}{\partial x_1} \right|_{\bar{x}_1, \bar{x}_2}$};
    \node[font=\large] (deriv2) at ([yshift=3.5cm, xshift=2.5cm]planeplot.center) {$\left. \frac{\partial f}{\partial x_2} \right|_{\bar{x}_1, \bar{x}_2}$};

    \draw[->, >=Stealth, thick] (deriv1) to[out=-90, in=120] ([xshift=-1.5cm, yshift=0.5cm]planeplot.center);
    \draw[->, >=Stealth, thick] (deriv2) to[out=-90, in=60] ([xshift=1.5cm, yshift=0.5cm]planeplot.center);

\end{tikzpicture}

\end{document}
```

Nell'esempio in figura (per una funzione $\mathbb{R}^2\to \mathbb{R}$) abbiamo:
$$
f(x_{1},x_{2}) \approx \underbrace{ f(\bar{x}_{1}, \bar{x}_{2}) }_{ \bar{x} } + \left[ \frac{\partial f}{\partial x_{1}}\bigg|_{\bar{x}_{1},\bar{x}_{2}}(x_{1}-\bar{x}_{1}) + \frac{\partial f}{\partial x_{2}}\bigg|_{\bar{x}_{1},\bar{x}_{2}}(x_{2}-\bar{x}_{2}) \right]
$$
## IN TEMPO CONTINUO
Sia $(\bar{x},\bar{u})=(\bar{x}_{1},\dots,\bar{x}_{n}, \bar{u}_{1}, \dots, \bar{u}_{m})\in \mathbb{R}^{n+m}$ un *punto di equilibrio* per il sistema in esame.
Allora:
$$
\begin{align*}
\dot{x}_{1}(t) &= f_{1}(x_{1}(t),\dots,x_{n}(t), u_{1}(t), \dots, u_{m}(t)) \\ \\
&\approx \cancelto{ 0 }{ f_{1}(\bar{x}_{1},\dots, \bar{x}_{n}, \bar{u}_{1}, \dots, \bar{u}_{m}) } + \\ \\
&+ \frac{\partial f_{1}}{\partial x_{1}}\bigg|_{\bar{x},\bar{u}}(x_{1}(t)-\bar{x}_{1}) + \dots + \frac{\partial f_{1}}{\partial x_{n}}\bigg|_{\bar{x},\bar{u}}(x_{n}(t)-\bar{x}_{n}) + \\ \\
&+ \frac{\partial f_{1}}{\partial u_{1}}\bigg|_{\bar{x},\bar{u}}(u_{1}(t)-\bar{u}_{1}) + \dots + \frac{\partial f_{1}}{\partial u_{m}}\bigg|_{\bar{x},\bar{u}}(u_{m}(t)-\bar{u}_{m})
\end{align*}
$$
E le equazioni per gli altri stati sono analoghe.
In generale, possiamo definire:
$$
\begin{align*}
\tilde{x}_{i}(t) := x_{i}(t) -\bar{x}_{i} & & \tilde{u}_{i}(t) := u_{i}(t) -\bar{u}_{i} \\ \\
a_{ij} := \frac{\partial f_{i}}{\partial x_{j}}\bigg|_{\bar{x},\bar{u}} & & 
b_{ij} := \frac{\partial f_{i}}{\partial u_{j}}\bigg|_{\bar{x},\bar{u}}
\end{align*}
$$
>[!note] NOTA
>Notiamo che per il *sistema traslato* $\tilde{x}, \tilde{u}$ (che descrive la *dinamica degli scostamenti dal punto di equilibrio* $\bar{x},\bar{u}$) il *corrispondente punto di equilibrio* è
>$$ \bar{\tilde{x}} = 0 \text{ , } \bar{\tilde{u}} = 0 $$

Date queste definizioni, possiamo scrivere l'*approssimazione lineare* per il nostro sistema nella *forma matriciale compatta* seguente:
$$
\dot{\tilde{x}}(t) = A\tilde{x}(t) + B\tilde{u}(t)
$$
Con:
- $A=(a_{ij})$, $i,j=1,\dots,n$.
- $B=(b_{ij})$, $i=1,\dots,n$ e $j=1,\dots,m$.

>[!idea] MATRICI JACOBIANE
>Per come sono costruite, le matrici $A$ e $B$ hanno la stessa forma delle **matrici jacobiane**.
>Nel nostro caso, però, la funzione *non mappa spazio in spazio*, ma **mappa stato in velocità**:
>$$ \dot{x} = f(x,u) $$
>Quindi lo *Jacobiano* ci dice *come variano le velocità per piccoli scostamenti dall'equilibrio*.
## IN TEMPO DISCRETO
Sia $(\bar{x},\bar{u})=(\bar{x}_{1},\dots,\bar{x}_{n}, \bar{u}_{1}, \dots, \bar{u}_{m})\in \mathbb{R}^{n+m}$ un *punto di equilibrio* per il sistema in esame.
Allora:
$$
\begin{align*}
x_{1}(k+1) &= f_{1}(x_{1}(k),\dots,x_{n}(k), u_{1}(k), \dots, u_{m}(k)) \\ \\
&\approx \overbrace{ f_{1}(\bar{x}_{1},\dots, \bar{x}_{n}, \bar{u}_{1}, \dots, \bar{u}_{m}) }^{ \bar{x}_{1} } + \\ \\
&+ \frac{\partial f_{1}}{\partial x_{1}}\bigg|_{\bar{x},\bar{u}}(x_{1}(k)-\bar{x}_{1}) + \dots + \frac{\partial f_{1}}{\partial x_{n}}\bigg|_{\bar{x},\bar{u}}(x_{n}(k)-\bar{x}_{n}) + \\ \\
&+ \frac{\partial f_{1}}{\partial u_{1}}\bigg|_{\bar{x},\bar{u}}(u_{1}(k)-\bar{u}_{1}) + \dots + \frac{\partial f_{1}}{\partial u_{m}}\bigg|_{\bar{x},\bar{u}}(u_{m}(k)-\bar{u}_{m})
\end{align*}
$$
E le equazioni per gli altri stati sono analoghe.
Come prima, possiamo definire in generale:
$$
\begin{align*}
\tilde{x}_{i}(k) := x_{i}(k) -\bar{x}_{i} & & \tilde{u}_{i}(k) := u_{i}(k) -\bar{u}_{i} \\ \\
a_{ij} := \frac{\partial f_{i}}{\partial x_{j}}\bigg|_{\bar{x},\bar{u}} & & 
b_{ij} := \frac{\partial f_{i}}{\partial u_{j}}\bigg|_{\bar{x},\bar{u}}
\end{align*}
$$
In *forma matriciale compatta* risulta allora:
$$
\tilde{x}(k+1) = A\tilde{x}(k) + B\tilde{u}(k)
$$
