# INDICE SEZIONE
- [ ] [[#DEFINIZIONE RISPOSTA FORZATA]]
- [ ] [[#CALCOLO PER INGRESSI COSTANTI]]
      - [[#ESEMPIO INGRESSO COSTANTE]]
- [ ] [[#CONVOLUZIONE]]
      - [[#IMPULSO DI DIRAC E RISPOSTA IMPULSIVA]]
      - [[#NOTE SU SEGNALI CANONICI]]
      - [[#RISPOSTA FORZATA]]
# DEFINIZIONE RISPOSTA FORZATA
Ricordiamo che, *per definizione*, la *risposta forzata* $x_{f}(t)$ è *soluzione* di:
$$
\begin{align*}
\dot{x}_{f}(t) = Ax_{f}(t) + Bu(t) & & x_{f}(0) = 0
\end{align*}
$$
Si verifica che essa è data da:

>[!th] RISPOSTA FORZATA
>$$ x_{f}(t) = \int_{0}^t e^{ A(t-\tau) }Bu(\tau)d\tau $$

Per dimostrarlo, sfruttiamo la formula di Leibniz:
$$
\frac{d}{dt}\int_{0}^t f(t,\tau)d\tau = f(t,t) + \int_{0}^t \frac{d}{dt}f(t,\tau)d\tau
$$
Procediamo con la dimostrazione:
$$
\begin{align*}
\dot{x}_{f}(t) &= \frac{d}{dt}\int_{0}^t e^{ A(t-\tau) }Bu(\tau)d\tau \\ \\
&= e^{ A(t-t) }Bu(t) + \int_{0}^t \frac{d}{dt}e^{ A(t-\tau) }Bu(\tau)d\tau = \\ \\
&= Bu(t) + A\int_{0}^t e^{ A(t-\tau) }Bu(\tau)d\tau = \\ \\
&= Bu(t) + Ax_{f}(t)
\end{align*}
$$
Come volevamo $\square$.
# CALCOLO PER INGRESSI COSTANTI
Consideriamo il caso in cui l'*ingresso esterno* è **costante**.
Abbiamo quindi $u(t)=\bar{u}$.

La risposta forzata risulta essere:
$$
x_{f}(t) = \int_{0}^t e^{ A(t-\tau) }Bu(\tau)d\tau = \left( \int_{0}^t e^{ A(t-\tau) }Bd\tau \right)\bar{u}
$$
Introduciamo il cambio di variabile: $\xi:=t-\tau$, $\tau=0\implies \xi=t$ e $\tau=t\implies \xi=0$.
Abbiamo quindi:
$$
\int_{0}^t e^{ A(t-\tau) }Bd\tau = \int_{t}^0 e^{ A\xi }B(-d\xi) = \int_{0}^t e^{ A\xi }Bd\tau
$$
Procediamo con il calcolo di questo integrale, analizzando casi diversi.

**CASO 1**: $A$ *invertibile*.
$$
\int_{0}^t e^{ A\xi }Bd\tau = A^{-1}e^{ A\xi }B\bigg|_{0}^t = A^{-1}(e^{ At } - I)B
$$
Per cui troviamo:
$$
x_{f}(t) = A^{-1}(e^{ At } - I)B\bar{u}
$$

**CASO 2**: $A$ *non* invertibile, ed *un solo ingresso*.
In tal caso, $e^{ A\xi }B$ è l'*evoluzione libera* che si avrebbe a partire dallo stato iniziale $x_{0}=B$ (vedi > [[10 - SISTEMI LTI ED EVOLUZIONE LIBERA#ESPONENZIALE DI MATRICE ED EVOLUZIONE LIBERA]]).
Allora:
$$
x_{f}(t) = \left( \int_{0}^t x_{l}(\xi)\bigg|_{x_{0}=B}d\xi \right)\bar{u}
$$

**CASO 2**: $A$ *non* invertibile, ed *ingressi multipli* ($B$ ha $m$ colonne).
Allora, per il *principio di sovrapposizione degli effetti*:
$$
x_{f}(t) = \sum_{i=1}^m \left( \int_{0}^t e^{ A\xi }[B]_{i}d\xi \right) \bar{u}_{i}
$$
## ESEMPIO INGRESSO COSTANTE
Consideriamo il sistema d'esempio
$$
\dot{x}(t) = Ax(t) + Bu(t)
$$
Con
$$
\begin{align*}
A = \begin{bmatrix}
0 & 1 \\
-4 & -5
\end{bmatrix} & & B = \begin{bmatrix}
3 \\
3
\end{bmatrix}
\end{align*}
$$
Cominciamo con lo studio dell'*evoluzione libera*: $\dot{x}_{l}(t)=Ax_{l}(t)$.
Con $x_{0}=B=(3,3)^T$
**NOTA**: è lo stesso esempio visto in > [[10 - SISTEMI LTI ED EVOLUZIONE LIBERA#ESEMPIO MODI NATURALI REALI]], per cui senza ripetere i calcoli ricordiamo che avevamo trovato:
$$
\begin{align*}
\lambda_{1} = -1 & & v_{1} = \begin{bmatrix}
1 \\
-1
\end{bmatrix} \\ \\
\lambda_{2} = -4 & & v_{2} = \begin{bmatrix}
-1 \\
4
\end{bmatrix} \\ \\
c_{1} = 5 & & c_{2} = 2
\end{align*}
$$
Per cui scriviamo l'evoluzione libera come:
$$
\begin{align*}
x_{l}(t) &= e^{ At }B = c_{1}e^{ \lambda_{1} t }v_{1} + c_{2}e^{ \lambda_{2}t }v_{2} \\ \\
&= \begin{bmatrix}
5e^{ -t }-2e^{ -4t } \\
-5e^{ -t } +8e^{ -4t }
\end{bmatrix}
\end{align*}
$$
Possiamo a questo punto calcolare la *risposta forzata* (metodo **caso 2**):
$$
\begin{align*}
x_{f}(t) &= \left( \int_{0}^t \begin{bmatrix}
5e^{ -\tau }-2e^{ -4\tau } \\
-5e^{ -\tau } +8e^{ -4\tau }
\end{bmatrix}d\tau \right)\bar{u} \\ \\
&= \begin{bmatrix}
-5e^{ -t } +\frac{1}{2}e^{ -4t } +\frac{9}{2} \\
5e^{ -t } -2e^{ -4t } -3
\end{bmatrix}\bar{u}
\end{align*}
$$
Essendo $A$ invertibile, possiamo usare anche il procedimento relativo al **caso 1**:
$$
x_{f}(t) = A^{-1}(e^{ At}-I)B\bar{u}
$$
Calcoliamo l'inversa di $A$:
$$
A^{-1} = \frac{1}{\det A}\begin{bmatrix}
-5 & -1 \\
4 & 0
\end{bmatrix} = \frac{1}{4}\begin{bmatrix}
-5 & -1 \\
4 & 0
\end{bmatrix}
$$
Notiamo anche che $A$ è **diagonalizzabile** (2 autovettori linearmente indipendenti) e possiamo quindi scriverla nella forma $A=VDV^{-1}$, dove le colonne di $V$ sono gli autovettori e $D$ è la matrice diagonale le cui entrate sulla diagonale sono gli autovalori di $A$.
Abbiamo:
$$
\begin{align*}
V = \begin{bmatrix}
1 & -1 \\
-1 & 4
\end{bmatrix} & & D = \begin{bmatrix}
-1 & 0 \\
0 & -4
\end{bmatrix} & & V^{-1}=\frac{1}{3}\begin{bmatrix}
4 & 1 \\
1 & 1
\end{bmatrix}
\end{align*}
$$
Abbiamo a questo punto:
$$
\begin{align*}
e^{ At } &= Ve^{ Dt }V^{-1} = \\ \\
&= \begin{bmatrix}
1 & -1 \\
-1 & 4
\end{bmatrix} \begin{bmatrix}
e^{ -t } & 0 \\
0 & e^{ -4t }
\end{bmatrix} \frac{1}{3} \begin{bmatrix}
4 & 1 \\
1 & 1
\end{bmatrix} = \\ \\
&= \frac{1}{3} \begin{bmatrix}
4e^{ -t } -e^{ -4t } & e^{ -t } -e^{ -4t } \\
-4e^{ -t } +4e^{ -4t } & -e^{ -t } +4e^{ -4t }
\end{bmatrix}
\end{align*}
$$
Da cui ricaviamo:
$$
e^{ At } - I = \frac{1}{3}\begin{bmatrix}
4e^{ -t } -e^{ -4t } -3 & e^{ -t } -e^{ -4t } \\
-4e^{ -t } +4e^{ -4t } & -e^{ -t } +4e^{ -4t } -3
\end{bmatrix}
$$
Ora abbiamo tutto quello che ci serve per calcolare:
$$
A^{-1}(e^{ At }-I)B\bar{u} = \begin{bmatrix}
-5e^{ -t } +\frac{1}{2}e^{ -4t } +\frac{9}{2} \\
5e^{ -t } -2e^{ -4t } -3
\end{bmatrix}\bar{u}
$$
# CONVOLUZIONE
Abbiamo visto che la *risposta forzata* risulta essere:
$$
x_{f}(t) = \int_{0}^t e^{ A(t-\tau) }Bu(\tau)d\tau
$$
Integrali di questo tipo sono detti **integrali di convoluzione** (o *prodotto di convoluzione*).

>[!def] INTEGRALE DI CONVOLUZIONE
>$$ (h*u)(t) := \int_{-\infty}^{+\infty} h(t-\tau)u(\tau)d\tau $$ 

Nel nostro caso:
$$
h(t) = e^{ At }B
$$
>[!idea] CAUSALITA'
>Le nostre funzioni $h(t)$ sono **causali**, cioè vale:
>$$ h(t) = 0 \text{ }\forall t<0 $$
>Infatti vedremo di seguito che $h(t)$ è legata alla *risposta del sistema* al *segnale esterno* $u(t)$: in questo senso, il **segnale è la causa della risposta**, che deve quindi essere nulla per $t<0$.
## IMPULSO DI DIRAC E RISPOSTA IMPULSIVA
Prima di proseguire, è necessario introdurre l'**impulso di Dirac** (o *delta di Dirac*, o *impulso unitario*).
Formalmente, è definita come segue:

>[!def] IMPULSO DI DIRAC
>Sia $\varphi(t)$ una funzione *continua in un intorno* di $t_{0}$.
>L'impulso di Dirac è una *funzione generalizzata* che soddisfa:
>$$ \int_{-\infty}^{+\infty}\delta(t-t_{0})\varphi(t)dt = \varphi(t_{0}) $$

Spesso ci si riferisce alla *delta di Dirac* come una funzione nulla per $t\ne 0$, con integrale pari a 1 integrando sull'intero asse delle ascisse:
$$
\int_{-\infty}^{+\infty} \delta(x)dx = 1
$$
In tal senso, può essere rappresentata come segue:

```tikz
\usepackage{pgfplots}

\begin{document}

\begin{tikzpicture}
    \begin{axis}[
        width=10cm,
        height=8cm,
        xmin=-2.1, xmax=2.1,
        ymin=-0.25, ymax=1.25,
        xtick={-2,-1,0,1,2},
        minor x tick num=4, % Aggiunge le tacche minori sull'asse x
        ytick={-0.2, 0.0, 0.2, 0.4, 0.6, 0.8, 1.0, 1.2},
        yticklabel style={
            /pgf/number format/fixed,
            /pgf/number format/precision=1
        },
        minor y tick num=1, % Aggiunge le tacche minori sull'asse y
        xlabel={x},
        tick align=inside, % Le tacche puntano verso l'interno come nell'immagine
        axis on top,
        thick, % Spessore del riquadro degli assi
        tick style={thick, black}
    ]

    % 1. Linea orizzontale a y=0
    \draw[blue, thick] (axis cs:-2.1,0) -- (axis cs:2.1,0);

    % 2. Freccia verticale per la Delta di Dirac (da appena sopra lo zero a 1)
    \draw[blue, thick, ->, >=stealth, shorten <= 3pt] (axis cs:0,0) -- (axis cs:0,1);

    % 3. Pallino vuoto nell'origine
    \addplot[blue, thick, mark=*, mark options={fill=white, scale=1.2}, only marks] coordinates {(0,0)};

    \end{axis}
\end{tikzpicture}

\end{document}
```

>[!note] NOTA
>Dalla definizione formale, notiamo che l'impulso di Dirac può essere usato per **"selezionare"** un *punto di interesse* di una funzione esterna.

Tornando all'analisi dei *sistemi LTI*, si può verificare che, se l'*ingresso esterno* è:
$$
u(t) = \delta(t)
$$
Allora l'*evoluzione forzata* è data da:
$$
x_{f}(t)\bigg|_{u(t)=\delta(t)} = (h*\delta)(t) = h(t)
$$
>[!def] RISPOSTA IMPULSIVA
>L'*evoluzione forzata* data dall'*impulso unitario* è detta **risposta impulsiva unitaria**.
## NOTE SU SEGNALI CANONICI
Oltre all'impulso di Dirac, esistono anche altri "*segnali fondamentali*", detti **segnali canonici**.
Questi sono:
- **Gradino unitario** (o *scalino*): $\delta_{-1}(t)=1$ per $t\geq 0$, $0$ altrimenti.
- **Rampa**: $\delta_{-2}(t)=t$ per $t\geq 0$, $0$ altrimenti.
- **Parabola**: $\delta_{-3}(t)=\frac{t^2}{2}$ per $t\geq 0$, $0$ altrimenti.

Inoltre, ciascun segnale è *legato agli altri* da *relazioni di tipo differenziale/integrale*, come in figura:

```tikz
\usepackage{tikz}
\usepackage{pgfplots}
\usepackage{amsmath}

\begin{document}

\begin{tikzpicture}[>=latex, font=\small]

% Definition of functions for ramp and parabola
\tikzset{
  ramp function/.style={declare function={ ramp(\x) = (\x > 0) * (\x) ; }},
  parabola function/.style={declare function={ parabola(\x) = (\x > 0) * (\x*\x/2) ; }}
}

% --- Upper Section: Differential Equations ---
\node (eq1) at (0, 4) {$\delta(t) = \dfrac{d\delta_{-1}(t)}{dt}$};
\node (eq2) at (4.5, 4) {$\delta_{-1}(t) = \dfrac{d\delta_{-2}(t)}{dt}$};
\node (eq3) at (9, 4) {$\delta_{-2}(t) = \dfrac{d\delta_{-3}(t)}{dt}$};

% --- Middle Section: Graphs ---
% Common styles
\tikzset{
  axis/.style={thin},
  plot/.style={thick},
  dots/.style={dashed, gray, thin}
}

% 1. Graph of \delta(t)
\begin{scope}[shift={(0,0)}, scale=1.1]
    \draw[axis] (-1.5,0) -- (1.5,0) node[below right] {$t$};
    \draw[axis, ->] (0,-0.5) -- (0,2.2) node[above] {};
    \node[anchor=north east] at (0,0) {0};
    \node[above] at (0,2.3) {$\delta(t)$};
    % The delta pulse itself, with an arrow head on top and label '1'
    \draw[plot, ->] (0,0) -- (0,1.8) node[left] {1};
\end{scope}

% 2. Graph of \delta_{-1}(t) (Unit Step)
\begin{scope}[shift={(3.5,0)}, scale=1.1]
    \draw[axis] (-1.5,0) -- (1.5,0) node[below right] {$t$};
    \draw[axis] (0,-0.5) -- (0,2.2) node[above] {};
    \node[anchor=north east] at (0,0) {0};
    \node[above] at (0,2.3) {$\delta_{-1}(t)$};
    \draw[plot] (-1.5,0) -- (0,0) -- (0,1.5) -- (1.5,1.5);
    \node[left] at (0,1.5) {1};
\end{scope}

% 3. Graph of \delta_{-2}(t) (Ramp)
\begin{scope}[shift={(7,0)}, scale=1.1, ramp function]
    \draw[axis] (-1.5,0) -- (1.5,0) node[below right] {$t$};
    \draw[axis] (0,-0.5) -- (0,2.2) node[above] {};
    \node[anchor=north east] at (0,0) {0};
    \node[above] at (0,2.3) {$\delta_{-2}(t)$};
    % Plot the ramp function
    \draw[plot, domain=0:1.5, samples=2] plot (\x, {ramp(\x)});
    \draw[plot] (-1.5, 0) -- (0,0);
    % Construction lines for (1, 1)
    \draw[dots] (1,0) -- (1,1);
    \draw[dots] (0,1) -- (1,1);
    \node[below] at (1,0) {1};
    \node[left] at (0,1) {1};
\end{scope}

% 4. Graph of \delta_{-3}(t) (Parabola)
\begin{scope}[shift={(10.5,0)}, scale=1.1, parabola function]
    \draw[axis] (-1.5,0) -- (1.5,0) node[below right] {$t$};
    \draw[axis] (0,-0.5) -- (0,2.2) node[above] {};
    \node[anchor=north east] at (0,0) {0};
    \node[above] at (0,2.3) {$\delta_{-3}(t)$};
    % Plot the parabola function y = t^2/2
    \draw[plot, domain=0:1.5, samples=50] plot (\x, {parabola(\x)});
    \draw[plot] (-1.5, 0) -- (0,0);
    % Construction lines for (1, 0.5)
    \draw[dots] (1,0) -- (1,0.5);
    \draw[dots] (0,0.5) -- (1,0.5);
    \node[below] at (1,0) {1};
    \node[left] at (0,0.5) {0.5};
\end{scope}

% --- Lower Section: Integral Equations ---
\node (eq4) at (0, -1.5) {$\int_{-\infty}^t \delta(\tau) = \delta_{-1}(t)$};
\node (eq5) at (4.5, -1.5) {$\int_{-\infty}^t \delta_{-1}(\tau) = \delta_{-2}(t)$};
\node (eq6) at (9, -1.5) {$\int_{-\infty}^t \delta_{-2}(\tau) = \delta_{-3}(t)$};

\end{tikzpicture}

\end{document}
```
## RISPOSTA FORZATA
Ora vediamo come l'*impulso di Dirac* è legato alla *risposta forzata*.

Innanzitutto, ricordiamo che il nostro sistema è **lineare tempo-invariante**.
Per cui, come vediamo dai grafici in figura:
1. Se diamo un **impulso unitario** all'**istante 0**, il nostro sistema *reagisce secondo* $h(t)$.
2. Se diamo lo **stesso impulso**, ma all'**istante "spostato"** $\tau$, il nostro sistema *reagisce allo stesso modo* (semplicemente con un ritardo), secondo $h(t-\tau)$.
3. Se diamo un **impulso "riscalato"** (di un fattore $u(\tau)$), il nostro sistema *reagisce in modo proporzionale*, secondo $h(t-\tau)u(\tau)$.

```tikz
\begin{document}

\begin{tikzpicture}[
    >=latex, % Stile classico per le punte delle frecce
    asse/.style={thin},
    curva/.style={very thick},
    impulso/.style={very thick, -latex}
]

% --- Macro per evitare di riscrivere gli assi di sinistra ---
\newcommand{\assiSinistra}{
    \draw[asse] (-1,0) -- (4,0);
    \node[anchor=east, inner sep=1pt] at (3.5, -0.25) {$t$};
    \draw[-latex] (3.5, -0.25) -- (3.9, -0.25);
    \draw[asse] (0,-0.5) -- (0,2.2);
    \node[below right, inner sep=2pt] at (0,0) {$0$};
}

% --- Macro per evitare di riscrivere gli assi di destra (più lunghi) ---
\newcommand{\assiDestra}{
    \draw[asse] (-1,0) -- (6,0);
    \node[anchor=east, inner sep=1pt] at (5.5, -0.25) {$t$};
    \draw[-latex] (5.5, -0.25) -- (5.9, -0.25);
    \draw[asse] (0,-0.5) -- (0,2.2);
    \node[below right, inner sep=2pt] at (0,0) {$0$};
}

% ==========================================
% RIGA 1
% ==========================================

% 1. Top Left: \delta(t)
\begin{scope}[shift={(0,0)}]
    \assiSinistra
    \draw[impulso] (0,0) -- (0,1.8) node[right] {$\delta(t)$};
\end{scope}

% 2. Top Right: h(t)
\begin{scope}[shift={(7.5,0)}]
    \assiDestra
    \draw[curva] (0,0) .. controls (0.6, 2.0) and (2.0, 0.5) .. (4.5, 0.15);
    \node[right] at (0.2, 2.0) {$h(t)$};
\end{scope}

% ==========================================
% RIGA 2
% ==========================================

% 3. Middle Left: \delta(t-\tau)
\begin{scope}[shift={(0,-4.5)}]
    \assiSinistra
    \node[below] at (1.5,0) {$\tau$};
    \draw[impulso] (1.5,0) -- (1.5,1.8) node[right] {$\delta(t-\tau)$};
\end{scope}

% 4. Middle Right: h(t-\tau)
\begin{scope}[shift={(7.5,-4.5)}]
    \assiDestra
    \node[below] at (1.5,0) {$\tau$};
    \draw[curva] (1.5,0) .. controls (2.1, 2.0) and (3.5, 0.5) .. (6.0, 0.15);
    \node[right] at (1.7, 2.0) {$h(t-\tau)$};
\end{scope}

% ==========================================
% RIGA 3
% ==========================================

% 5. Bottom Left: u(\tau)\delta(t-\tau)
\begin{scope}[shift={(0,-9)}]
    \assiSinistra
    \node[below] at (1.5,0) {$\tau$};
    \draw[impulso] (1.5,0) -- (1.5,2.7) node[right] {$u(\tau)\delta(t-\tau)$};
\end{scope}

% 6. Bottom Right: h(t-\tau)u(\tau)
\begin{scope}[shift={(7.5,-9)}]
    \assiDestra
    \node[below] at (1.5,0) {$\tau$};
    \draw[curva] (1.5,0) .. controls (2.1, 3.0) and (3.5, 0.5) .. (6.0, 0.23);
    \node[right] at (1.7, 2.0) {$h(t-\tau)u(\tau)$};
\end{scope}

\end{tikzpicture}

\end{document}
```

Detto ciò, come facciamo a calcolare la *risposta forzata* generata da un *segnale generico*?

Per farlo, immaginiamo il *segnale di ingresso* $u(t)$ come se fosse costituito da **infiniti impulsi di Dirac**, ciascuno "scalato" di un certo fattore:

```tikz
\usepackage{pgfmath}

\begin{document}

\begin{tikzpicture}[
    >=latex, 
    asse/.style={thin},
    curva/.style={very thick, black}, 
    impulso/.style={thick, -latex, gray!80},
    scale = 1.4
]

% --- DEFINIZIONE DELLA FUNZIONE u(t) GENERICA ---

\pgfmathdeclarefunction{ufunc}{1}{%
    \pgfmathparse{1.0 + 0.7*sin(#1*45) + 0.3*#1 - 0.04*#1*#1}%
}

% --- DISEGNO DEGLI ASSI ---
\draw[asse] (-1,0) -- (7.5,0);
\node[anchor=east, inner sep=1pt] at (7.0, -0.25) {$t$};
\draw[-latex] (7.0, -0.25) -- (7.4, -0.25);
\node[below right, inner sep=2pt] at (0,0) {$0$};

\draw[asse] (0,-0.5) -- (0,3.5) node[above] {$u(t)$};

% --- DISEGNO DELLA CURVA u(t) ---
\draw[curva] plot[domain=0:6.8, samples=100] (\x, {ufunc(\x)});

% --- DISEGNO DEGLI IMPULSI ---

\foreach \t in {0.4, 0.8, 1.2, 1.6, 2.0, 2.4, 2.8, 3.2, 3.6, 4.0, 4.4, 4.8, 5.2, 5.6, 6.0, 6.4} {
    \pgfmathsetmacro{\h}{ufunc(\t)}
    \draw[impulso] (\t,0) -- (\t,\h);
}

% --- ANNOTAZIONI PER UN IMPULSO GENERICO ---

\pgfmathsetmacro{\tauu}{2.8}
\pgfmathsetmacro{\utau}{ufunc(\tauu)}

\node[below] at (\tauu,0) {$\tau$};
\draw[thin, dashed, gray] (\tauu, \utau) -- (-0.2, \utau) node[left, black] {$u(\tau)$};

\node[right, inner sep=2pt, font=\small, text=black] at (\tauu+0.3, \utau) {$\approx u(\tau)\delta(t-\tau)d\tau$};

\end{tikzpicture}

\end{document}
```

>[!idea] RISPOSTA COMPLESSIVA
>A questo punto è semplice intuire che per ricavare la *risposta forzata complessiva* al tempo $t$ non dobbiamo fare altro che *integrare* in $d\tau$ per $t\in[0,\tau]$:
>$$ x_{f}(t) = \int_{0}^t h(t-\tau)u(\tau)d\tau $$

Per dare un'ulteriore spiegazione, focalizziamoci su un **istante** $\tau \in[0,t]$:
- Al tempo $\tau$ l'*ingresso esterno* arriva con *intensità* $u(\tau)$.
- Al tempo $t$ (*istante di nostro interesse*) è trascorso un **intervallo di tempo** $\Delta=t-\tau$, per cui la *risposta del sistema* a quella *specifica componente* diventa $h(t-\tau)$.

>[!note] NOTA
>Stiamo quindi dicendo che la *componente* $u(\tau)$ *influisce* sulla risposta in un modo che **dipende da quanto tempo è passato dal suo arrivo** nel sistema.
