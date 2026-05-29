# INDICE SEZIONE
- [ ] [[#INTRODUZIONE]]
      - [[#PRINCIPIO DI SOVRAPPOSIZIONE DEGLI EFFETTI]]
      - [[#EVOLUZIONE LIBERA E RISPOSTA FORZATA]]
      - [[#ESEMPIO CIRCUITO]]
- [ ] [[#CALCOLO EVOLUZIONE LIBERA - MODI NATURALI REALI]]
      - [[#CASO PARTICOLARE REALE]]
      - [[#CASO GENERALE]]
      - [[#ESEMPIO MODI NATURALI REALI]]
- [ ] [[#CALCOLO EVOLUZIONE LIBERA - MODI NATURALI COMPLESSI]]
      - [[#CASO PARTICOLARE COMPLESSO]]
      - [[#TRAIETTORIE NELLO SPAZIO DI STATO]]
      - [[#ESEMPIO MODI NATURALI COMPLESSI]]
- [ ] [[#EVOLUZIONE LIBERA IN GENERALE]]
      - [[#ESPONENZIALE DI MATRICE ED EVOLUZIONE LIBERA]]
# INTRODUZIONE
>[!def] SISTEMA LINEARE TEMPO INVARIANTE
>Un sistema lineare si dice **tempo invariante** se *tutti i coefficienti sono costanti*.

Sono quindi sistemi del tipo
$$
\dot{x}_{i}(t) = \sum_{j=1}^n a_{ij}x_{j}(t) + \sum_{j=1}^mb_{ij}u_{j}(t)
$$
Che scriviamo nella forma
$$
\dot{x}_{n\times 1}(t) = A_{n\times n}x_{n\times 1}(t) + B_{n\times m}u_{m\times 1}(t)
$$
In un sistema dinamico *LTI*:
1. Vale il *principio di sovrapposizione degli effetti*.
2. Si ha *invarianza nel tempo*, cioè le sue caratteristiche non cambiano nel tempo.

>[!note] NOTA
>Per cui, se a partire da *assegnate condizioni iniziali*, l'ingresso $u(t)$ determina lo stato $x(t)$, allora un *ingresso ritardato* $u(t-t_{0})$ determina lo *stato ritardato* $x(t-t_{0})$.
## PRINCIPIO DI SOVRAPPOSIZIONE DEGLI EFFETTI
Consideriamo il sistema
$$
\dot{x}(t) = Ax(t) + Bu(t)
$$
Se:
1. $x'(t)$ è la *soluzione* relativa alle *condizioni iniziali* $x'(0)$ e all'*ingresso* $u'(t)$
2. $x''(t)$ è la *soluzione* relativa alle *condizioni iniziali* $x''(0)$ e all'*ingresso* $u''(t)$

Allora la *soluzione* $x'''(t)$ relativa alle condizioni iniziali $x'''(0)=\alpha x'(0)+\beta x'(0)$ e all'ingresso $u'''(t)=\alpha u'(t)+\beta u''(t)$ risulta essere:
$$
x'''(t) = \alpha x'(t) + \beta x''(t)
$$
>[!check] DIM.
>Dato che $x'(t)$ e $x''(t)$ sono *soluzioni del sistema*, allora:
>$$ \begin{align*} \dot{x}'(t) = Ax'(t) + Bu'(t) \\ \\ \dot{x}''(t) = Ax''(t) + Bu''(t) \end{align*} $$
>Di conseguenza, abbiamo:
>$$ \begin{align*} \dot{x}'''(t) &= \alpha(Ax'(t) + Bu'(t)) + \beta(Ax''(t) + Bu''(t)) \\ \\ &= A(\alpha x'(t)+\beta x''(t)) + B(\alpha u'(t)+\beta u''(t)) \\ \\ &= Ax'''(t) + Bu'''(t) \end{align*} $$
## EVOLUZIONE LIBERA E RISPOSTA FORZATA
Consideriamo il problema di Cauchy:
$$
\dot{x}(t) = Ax(t) + Bu(t)
$$
Fissati $x(0)=x_{0}$ e l'ingresso $u(t)$ abbiamo:
1. **EVOLUZIONE LIBERA**: soluzione $x_{l}(t)$ del sistema dinamico a partire da assegnate condizioni iniziali $x(0)$ e *ingressi nulli* $u(t)=0$.
2. **RISPOSTA FORZATA**: soluzione $x_{f}(t)$ del sistema dinamico a partire da *condizioni iniziali nulle* $x(0)=0$ e *ingressi non nulli* $u(t)$.

>[!idea] SOLUZIONE GENERALE 
>Dato il sistema lineare sopra, la corrispondente soluzione di un problema di Cauchy per *assegnate condizioni iniziali* e *ingresso* è data dalla *somma di evoluzione libera ed evoluzione forzata*:
>$$ x(t) = x_{l}(t) + x_{f}(t) $$
## ESEMPIO CIRCUITO
Consideriamo il seguente circuito:

```tikz
\usepackage{circuitikz}

\begin{document}
\begin{tikzpicture}[american, thick]

    % Riquadro tratteggiato
    \draw[dashed, thick, darkgray] (0.3, -0.6) rectangle (5.2, 2.8);

    % Generatore di tensione e terminali di ingresso
    \draw[gray] (-2,0) to[V, color=gray, invert] (-2,2);
    \draw (-2,2) -- (0,2) node[ocirc]{};
    \draw (-2,0) -- (0,0) node[ocirc]{};

    % Resistore e filo superiore
    \draw (0,2) -- (1,2) to[R=$R$] (3,2);
    
    % Segni + e - in rosso vicino al resistore
    \node[red, font=\large] at (1.1, 2.5) {$+$};
    \node[red, font=\large] at (2.9, 2.5) {$-$};

    % Continuazione del filo superiore fino all'uscita
    \draw (3,2) -- (6,2) node[ocirc]{};

    % Freccia della corrente i(t) sopra il filo
    \draw[->, >=latex] (3.4, 2.3) -- (4.2, 2.3) node[midway, above] {$i(t)$};

    % Condensatore
    \draw (4.6,2) to[C, l_=$C$] (4.6,0);

    % Filo inferiore
    \draw (0,0) -- (6,0) node[ocirc]{};

    % Polarità di uscita
    \node[font=\large] at (6.4, 1.5) {$+$};
    \node[font=\large] at (6.4, 0.5) {$-$};

\end{tikzpicture}
\end{document}
```

Identifichiamo l'*ingresso esterno* con il *generatore di tensione* sulla sinistra.
In particolare, consideriamo una *tensione costante*:
$$
u(t) := \delta_{-1}(t) = 1 \text{ }\forall t
$$
Consideriamo come *variabile di stato* la *tensione ai capi del condensatore*:
$$
x(t) := v_{C}(t)
$$
L'equazione che descrive il sistema è quindi:
$$
\begin{align*}
u(t) &= RC\dot{x}(t) + x(t) \implies \\ \\
\dot{x}(t) &= -\frac{1}{RC}x(t) + \frac{1}{RC}u(t)
\end{align*}
$$
Per studiare l'**evoluzione libera** consideriamo:
- $x(0)=1\ne 0$,
- $u(t)=0$.

Troviamo così:
$$
x_{l}(t) = e^{ -t/RC }
$$
Invece, per studiare la **risposta forzata**, consideriamo:
- $x(0)=0$,
- $u(t)=1\ne 0$

E troviamo:
$$
x_{f}(t) = 1-e^{ -t/RC }
$$
La **soluzione complessiva** al problema è data da:
$$
x(t) = x_{l}(t) + x_{f}(t) = 1 \text{ }\forall t
$$
>[!note] NOTA
>L'approccio è perfettamente equivalente all'ottenere la *soluzione più generale* come *somma* della *soluzione all'equazione omogenea* associata e di una *soluzione particolare* all'equazione originale, che avevamo usato in Analisi e in Algebra Lineare.
# CALCOLO EVOLUZIONE LIBERA - MODI NATURALI REALI
Concentriamoci ora sullo studio dell'*evoluzione libera*, quindi in presenza di *ingressi nulli*.
Abbiamo:
- $x(0)\ne 0$,
- $u(t)=0$.

E vogliamo $x_{l}(t)$, soluzione di:
$$
\dot{x}_{l}(t) = Ax_{l}(t)
$$
## CASO PARTICOLARE REALE
Cominciamo analizzando un *caso particolare* ("fortunato"): quello in cui lo **stato iniziale** si trova lungo la **direzione di un autovettore del sistema**.

Abbiamo quindi:
$$
\begin{align*}
x(0) &= x_{0} = c_{i}v_{i} \\ \\
Av_{i} &= \lambda_{i}v_{i} 
\end{align*}
$$

```tikz
\usepackage{amsmath}
\usepackage{tikz}
\usetikzlibrary{calc,intersections,arrows.meta}

\begin{document}
\begin{tikzpicture}[>=stealth]
    % Definizione degli stili
    \tikzset{
        axis/.style={->, thick},
        dashedline/.style={dashed, gray!80},
        vector/.style={->, cyan, very thick},
        pointnode/.style={fill=cyan, circle, inner sep=2pt}
    }

    % Disegno degli assi
    \draw[axis] (-1,0) -- (4.8,0) node[pos=0.98, anchor=south east] {$x_1$};
    \draw[axis] (0,-1) -- (0,6.8) node[pos=0.98, anchor=north west] {$x_2$};

    % Definizione dei punti chiave
    \coordinate (O) at (0,0);
    \coordinate (Vi) at (1.8, 3); % Posizione proporzionale del vettore vi
    \coordinate (X0) at ($(O)!1.67!(Vi)$); % Posizione del punto x0, scalato da Vi

    % Disegno della linea tratteggiata estesa
    % Estesa in entrambe le direzioni dall'origine
    \draw[dashedline] ($(O)!-0.4!(X0)$) -- ($(O)!1.25!(X0)$);

    % Disegno del vettore vi
    \draw[vector] (O) -- (Vi) node[pos=1.07, anchor=west, black, font=\normalsize] {$v_i$};

    % Disegno del punto x(0) e della sua etichetta
    \node[pointnode] at (X0) {};
    \node[anchor=west, black, inner sep=5pt, font=\normalsize] at (X0) {$x(0) = x_0 = c_i v_i$};

\end{tikzpicture}
\end{document}
```

In questo caso, la soluzione risulta essere *molto semplice*:
$$
x_{l}(t) = c_{i}e^{ \lambda_{i}t }v_{i} = x_{0}e^{ \lambda_{i}t }
$$
>[!check] DIM.
>Infatti:
>$$ \frac{d(c_{i}e^{ \lambda_{i}t }v_{i})}{dt} = Ac_{i}e^{ \lambda_{i}t }v_{i} $$
>Che riscriviamo come:
>$$ c_{i} \frac{de^{ \lambda_{i}t }}{dt}v_{i} = c_{i}e^{ \lambda_{i}t }Av_{i} $$
>Ma:
>$$ \frac{de^{ \lambda_{i}t }}{dt} = \lambda_{i}e^{ \lambda_{i}t } $$
>Per cui, sostituendo, troviamo:
>$$ c_{i}e^{ \lambda_{i}t }\lambda_{i}v_{i} = c_{i}e^{ \lambda_{i}t }Av_{i} $$
>Inoltre, notiamo anche:
>$$ x_{l}(0) = c_{i}e^{ \lambda_{i}t }v_{i}\bigg|_{t=0} = c_{i}v_{i} = x_{0} $$
>Come volevamo $\square$.

>[!note] NOTA
>Questa soluzione è detta **modo naturale** *relativo all'autovalore $\lambda_{i}$*.

E' una conseguenza di quanto ricordato in > [[01 - MODELLI DI TRASFERIMENTO DI RISORSE (TEMPO CONTINUO)#RICHIAMO SOLUZIONE DEL SISTEMA]].

Siccome la soluzione *dipende dall'autovettore* $v_{i}$, la *traiettoria dell'evoluzione libera* si origina e **rimane confinata nel sottospazio** unidimensionale **identificato dall'autovettore** stesso, che viene chiamato anche **sottospazio invariante** per il sistema.

L'*andamento* del modo naturale $e^{ \lambda_{i}t }v_{i}$ *dipende* poi dal *segno dell'autovalore reale* $\lambda_{i}$.

```tikz
\usepackage{tikz}
\usetikzlibrary{calc,decorations.markings,arrows.meta}

\begin{document}
\begin{tikzpicture}[>=Stealth, scale=0.8]

% --- Definizione degli stili ---
\tikzset{
    axis/.style={->, gray!60, thin},
    dashedline/.style={dashed, thin, gray!60},
    vector_vi/.style={->, cyan!70!white, ultra thick},
    point_x0/.style={circle, fill=cyan!70!white, inner sep=2.2pt},
    trajectory/.style={thin, black, postaction={decorate}},
    many_arrows_step/.style={
        decoration={
            markings,
            mark=between positions 0.05 and 0.95 step 6mm with {\arrow{>}}
        }
    },
    few_arrows/.style={
        decoration={
            markings,
            mark=at position 0.3 with {\arrow{>}},
            mark=at position 0.8 with {\arrow{>}}
        }
    }
}

% --- Pannello 1: lambda_i > 0 ---
\begin{scope}[xshift=0cm]
    % Assi
    \draw[axis] (-0.8,0) -- (3.5,0) node[right, black] {$x_1$};
    \draw[axis] (0,-0.8) -- (0,5.5) node[above, black] {$x_2$};
    \node[below left, gray!60] at (0,0) {$0$};

    % Linea tratteggiata
    \draw[dashedline] (-0.8,-1.6) -- (3,6);

    % Coordinate chiave
    \coordinate (Vi1) at (1,2);
    \coordinate (X01) at (1.7, 3.4);

    % Vettore vi
    \draw[vector_vi] (0,0) -- (Vi1);
    \node[right=2pt, black] at ($(Vi1)+(-0.1,0)$) {$v_i$};

    % Punto x(0)
    \node[point_x0] at (X01) {};
    \node[right=2pt, black] at (X01) {$x(0)$};

    % Traiettoria verso l'esterno
    \begin{scope}[few_arrows]
        \draw[trajectory] (X01) -- (2.6, 5.2);
    \end{scope}

    % Testo lambda
    \node[black, font=\large] at (3.2, 4.3) {$\lambda_i > 0$};
\end{scope}

% --- Pannello 2: lambda_i < 0 ---
\begin{scope}[xshift=5.2cm]
    % Assi
    \draw[axis] (-0.8,0) -- (3.5,0) node[right, black] {$x_1$};
    \draw[axis] (0,-0.8) -- (0,5.5) node[above, black] {$x_2$};
    \node[below left, gray!60] at (0,0) {$0$};

    % Linea tratteggiata
    \draw[dashedline] (-0.8,-1.6) -- (3,6);

    % Coordinate chiave
    \coordinate (Vi2) at (1,2);
    \coordinate (X02) at (1.7, 3.4);

    % Vettore vi
    \draw[vector_vi] (0,0) -- (Vi2);
    \node[right=2pt, black] at ($(Vi2)+(-0.0,0)$) {$v_i$};

    % Punto x(0)
    \node[point_x0] at (X02) {};
    \node[right=2pt, black] at (X02) {$x(0)$};

    % Traiettoria verso l'interno (molte frecce)
    \begin{scope}[many_arrows_step]
        \draw[trajectory] (X02) -- (0,0);
    \end{scope}

    % Testo lambda
    \node[black, font=\large] at (2.2, 2.7) {$\lambda_i < 0$};
\end{scope}

% --- Pannello 3: lambda_i = 0 ---
\begin{scope}[xshift=10.4cm]
    % Assi
    \draw[axis] (-0.8,0) -- (3.5,0) node[right, black] {$x_1$};
    \draw[axis] (0,-0.8) -- (0,5.5) node[above, black] {$x_2$};
    \node[below left, gray!60] at (0,0) {$0$};

    % Linea tratteggiata
    \draw[dashedline] (-0.8,-1.6) -- (3,6);

    % Coordinate chiave
    \coordinate (Vi3) at (1,2);
    \coordinate (X03) at (1.7, 3.4);

    % Vettore vi
    \draw[vector_vi] (0,0) -- (Vi3);
    \node[right=2pt, black] at ($(Vi3)+(-0.1,0)$) {$v_i$};

    % Punto x(0)
    \node[point_x0] at (X03) {};
    \node[right=2pt, black] at (X03) {$x(0)$};

    % Testo lambda
    \node[black, font=\large] at (1, 3.8) {$\lambda_i = 0$};
\end{scope}

\end{tikzpicture}
\end{document}
```

Al variare di $\lambda_{i}$ abbiamo:
1. $\lambda_{i}>0$ : **modo divergente**.
2. $\lambda_{i}=0$ : **modo costante**.
3. $\lambda_{i}<0$ : **modo convergente**.

```tikz
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}[>=latex, xscale=1.2, yscale=1.5, font=\sffamily]

    % Definizione del colore azzurro personalizzato
    \definecolor{myblue}{RGB}{90,195,240}
    
    % Stili per le linee
    \tikzset{
        curve/.style={thick, myblue},
        dashedcurve/.style={thick, myblue, dash pattern=on 8pt off 4pt}
    }

    % Disegno degli assi
    \draw[->, thick] (0,0) -- (8,0) node[right] {$t$};
    \draw[->, thick] (0,0) -- (0,2.8) node[right] {$e^{\lambda_i t}$};

    % Etichette sugli assi (0 e 1) e punto iniziale
    \node[below left] at (0,0) {0};
    \node[left] at (0,1) {1};
    \filldraw (0,1) circle (1.5pt);

    % --- Curva: Modo divergente (lambda > 0) ---
    \draw[curve, domain=0:6, samples=100] plot (\x, {exp(0.15*\x)});
    \draw[dashedcurve, domain=6:7.5, samples=20] plot (\x, {exp(0.15*\x)});
    % Etichette lambda e modo
    \node[above left, text=darkgray] at (4.5, {exp(0.15*4.5)}) {$\lambda_i > 0$};
    \node[above, black] at (6.8, {exp(0.15*6.8)}) {modo divergente};

    % --- Curva: Modo costante (lambda = 0) ---
    \draw[curve] (0,1) -- (6,1);
    \draw[dashedcurve] (6,1) -- (7.5,1);
    % Etichette lambda e modo
    \node[above left, text=darkgray] at (5.8, 1) {$\lambda_i = 0$};
    \node[above, black] at (6.8, 1) {modo costante};

    % --- Curva: Modo convergente (lambda < 0) ---
    \draw[curve, domain=0:6, samples=100] plot (\x, {exp(-0.25*\x)});
    \draw[dashedcurve, domain=6:7.5, samples=20] plot (\x, {exp(-0.25*\x)});
    % Etichette lambda e modo
    \node[below left, text=darkgray] at (4.2, {exp(-0.25*4.2)}) {$\lambda_i < 0$};
    \node[above, black] at (6.8, {exp(-0.25*6.8)}) {modo convergente};

\end{tikzpicture}
\end{document}
```

>[!note] NOTA
>Tornerà utile in seguito definire la **costante di tempo del modo naturale**:
>$$ \tau_{i} = -\frac{1}{\lambda_{i}} $$
## CASO GENERALE
Analizziamo ora il *caso più generale*, in cui lo **stato iniziale** del sistema $x(0)=x_{0}$ sia un **punto qualsiasi** dello spazio di stato.

>[!warning] ATTENZIONE
>Per comodità assumiamo che gli **autovalori** della matrice $A$ siano **reali** (il caso complesso è discusso più avanti) e **distinti** (in caso contrario dovremmo lavorare con *matrici di Jordan* e *autovettori generalizzati*).

Siano quindi $(v_{1},\dots,v_{n})$ gli *autovettori* della matrice $A$: questi *formano una base* nello spazio di stato $n$-dimensionale.
Un punto qualsiasi di tale spazio si può scrivere nella forma:
$$
x_{0} = \sum_{i=1}^n c_{i}v_{i}
$$
```tikz
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}[>=latex, x=1.5cm, y=1.5cm] % Imposta le dimensioni generali

    % Definizione delle coordinate e costanti
    \coordinate (O) at (0,0);
    \def\c{1.6} % Lunghezza scalata per c1v1 e c2v2 (sono uguali nell'immagine)
    \coordinate (V1) at (0.8,0); % Lunghezza v1
    \coordinate (V2) at (0,0.8); % Lunghezza v2
    \coordinate (C1V1) at (\c,0); % Vettore c1v1
    \coordinate (C2V2) at (0,\c); % Vettore c2v2
    \coordinate (X0) at (\c,\c);  % Vettore x0

    % Disegno degli assi con frecce sottili
    \draw[->, thin] (-1.5,0) -- (2.5,0); % Asse x
    \draw[->, thin] (0,-1.5) -- (0,2.5); % Asse y

    % Rettangolo di base grigio chiaro
    \draw[gray!30] (O) rectangle (X0);

    % Vettori base scalati (grigi scuri, molto spessi, dietro i vettori colorati)
    \draw[->, gray, very thick] (O) -- (C1V1) node[below right, black] {$c_1 v_1$};
    \draw[->, gray, very thick] (O) -- (C2V2) node[above left, black] {$c_2 v_2$};

    % Linee di proiezione (punteggiate, grigie)
    \draw[gray!70, dotted] (X0) -- (C1V1);
    \draw[gray!70, dotted] (X0) -- (C2V2);

    % Vettore x0 (grigio medio, spesso)
    \draw[->, gray, very thick] (O) -- (X0) node[right=5pt, black] {$x_0$};

    % Vettori base (colorati, spessi, sopra i vettori grigi scalati)
    \draw[->, red, ultra thick] (O) -- (V1) node[below=5pt, black] {$v_1$};
    \draw[->, blue!80!black, ultra thick] (O) -- (V2) node[left=5pt, black] {$v_2$};

    % Piccolo cerchio trasparente all'origine
    \draw[gray] (O) circle (1.5pt);

\end{tikzpicture}
\end{document}
```

Perciò, data la *linearità del sistema*, possiamo calcolare l'*evoluzione libera* come *somma* di $n$ *modi naturali*, a cui corrispondono $n$ traiettorie, ciascuna delle quali evolve lungo il sottospazio unidimensionale determinato dal corrispondete autovettore:
$$
x_{l}(t) = \sum_{i=1}^n c_{i} e^{ \lambda_{i}t } v_{i}
$$
## ESEMPIO MODI NATURALI REALI
Consideriamo l'esempio seguente:
$$
\begin{align*}
\dot{x}(t) = \begin{bmatrix}
0 & 1 \\
-4 & -5
\end{bmatrix}x(t) & & x_{0} = \begin{bmatrix}
3 \\
3
\end{bmatrix}
\end{align*}
$$
Cominciamo col *calcolare gli autovalori* dall'equazione caratteristica:
$$
\begin{align*}
\det(A-\lambda I) &= 0 \\ \\
\det \begin{bmatrix}
-\lambda & 1 \\
-4 & -5-\lambda
\end{bmatrix} &= -\lambda(-5-\lambda) -1(4) \\ \\
\lambda^2+5\lambda+4 &= 0 \\ \\
(\lambda+1)(\lambda+4) &= 0
\end{align*}
$$
Per cui troviamo:
$$
\begin{align*}
\lambda_{1} = -1 & & \lambda_{2} = -4
\end{align*}
$$
>[!note] NOTA
>Siccome entrambi gli autovalori sono *negativi*, sappiamo già che i due *modi naturali* associati saranno *convergenti*, per cui il sistema partirà da $x_{0}=(3,3)$ e *convergerà verso l'origine* $(0,0)$.

Ora calcoliamo gli *autovettori*:
$$
\begin{align*}
(A-\lambda I)v = 0 & & v:= \begin{bmatrix}
v_{11} \\
v_{12}
\end{bmatrix}
\end{align*}
$$
Per $\lambda_{1}=-1$ abbiamo $A-\lambda_{1}I=A+I$, per cui otteniamo il sistema:
$$
\begin{cases}
v_{11} + v_{12} = 0 \\
-4v_{11} - 4v_{12} = 0
\end{cases}
$$
E troviamo l'*autovettore* associato a $\lambda_{1}$:
$$
v_{1} = \begin{bmatrix}
1 \\
-1
\end{bmatrix}
$$
Similmente, troviamo anche l'autovettore associato a $\lambda_{2}$:
$$
v_{2} = \begin{bmatrix}
-1 \\
4
\end{bmatrix}
$$
Ora scriviamo lo stato iniziale $x_{0}$ come *combinazione lineare* degli autovettori trovati:
$$
\begin{align*}
x_{0} &= c_{1}v_{1} + c_{2}v_{2} \\ \\
\begin{bmatrix}
3 \\
3
\end{bmatrix} &= c_{1} \begin{bmatrix}
1 \\
-1
\end{bmatrix} +c_{2}\begin{bmatrix}
-1 \\
4
\end{bmatrix}
\end{align*}
$$
Troviamo così:
$$
\begin{align*}
c_{1} = 5 & & c_{2} = 2
\end{align*}
$$
A questo punto abbiamo tutto quello che ci serve per scrivere l'*evoluzione libera*:
$$
\begin{align*}
x_{l}(t) &= 5e^{ -t }\begin{bmatrix}
1 \\
-1
\end{bmatrix} + 2e^{ -4t }\begin{bmatrix}
-1 \\
4
\end{bmatrix} \\ \\
&= \begin{bmatrix}
5e^{ -t } -2e^{ -4t } \\
-5e^{ -t } +8e^{ -4t }
\end{bmatrix}
\end{align*}
$$
Possiamo anche rappresentare la traiettoria nello *spazio degli stati*:

```tikz
\usepackage{tikz}
\usetikzlibrary{calc, decorations.markings, arrows.meta}
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}[>=Stealth, scale=0.8]
    
    % Definizione dei punti chiave in base ai vettori forniti
    \coordinate (O) at (0,0);
    \coordinate (V1) at (1,-1);         % v1
    \coordinate (V2) at (-1,4);         % v2
    \coordinate (C1V1) at (5,-5);       % c1*v1 = 5*(1,-1)
    \coordinate (C2V2) at (-2,8);       % c2*v2 = 2*(-1,4)
    \coordinate (X0) at (3,3);          % x(0) = c1v1 + c2v2

    % Assi x1 e x2
    \draw[->, thin] (-3.5,0) -- (6.5,0) node[above right] {$x_1$};
    \draw[->, thin] (0,-6.5) -- (0,9.5) node[right] {$x_2$};

    % Linee tratteggiate degli autospazi (estensioni fino ai vettori scalati)
    \draw[dashed, gray!80!black] (O) -- (C1V1);
    \draw[dashed, gray!80!black] (O) -- (C2V2);

    % Parallelogramma: Linee rosse punteggiate
    \draw[thick, red, densely dotted] (C2V2) -- (X0) -- (C1V1);

    % Vettori di base v1 e v2
    \draw[->, thick, cyan!80!black] (O) -- (V1) node[right=1pt, black] {$v_1$};
    \draw[->, thick, cyan!80!black] (O) -- (V2) node[right=1pt, black] {$v_2$};

    % Vettore dello stato iniziale x(0)
    \draw[->, thick, red!80!black] (O) -- (X0);
    
    % Punto di partenza x(0) (pallino azzurro)
    \fill[cyan!80!black] (X0) circle (2.5pt);
    \node[above right] at (X0) {$x(0)$};

    % Traiettoria dinamica convergente verso l'origine
    % Parametrizzata per e^(-t) [modo lento v1] ed e^(-3t) [modo veloce v2]
    \draw[thick, 
          postaction={decorate},
          decoration={
              markings,
              mark=at position 0.12 with {\arrow{Stealth}},
              mark=at position 0.38 with {\arrow{Stealth}},
              mark=at position 0.65 with {\arrow{Stealth}},
              mark=at position 0.90 with {\arrow{Stealth}}
          }] 
          plot[domain=0:5, samples=50] 
          ({5*exp(-\x) - 2*exp(-3*\x)}, {-5*exp(-\x) + 8*exp(-3*\x)});

    % --- Quotature laterali per c1v1 e c2v2 ---

    % Quotatura per c2v2 (spostata a sinistra perpendicolarmente)
    \coordinate (Offset2) at ($ (O)!0.8cm!90:(C2V2) $); 
    \coordinate (D2_start) at (Offset2);
    \coordinate (D2_end) at ($ (C2V2) + (Offset2) $);
    \draw[<->, thin] (D2_start) -- (D2_end) node[midway, fill=white, sloped] {$c_2 v_2$};
    \draw[dashed, thin, gray] (O) -- (D2_start);
    \draw[dashed, thin, gray] (C2V2) -- (D2_end);

    % Quotatura per c1v1 (spostata in basso a sinistra perpendicolarmente)
    \coordinate (Offset1) at ($ (O)!0.8cm!-90:(C1V1) $);
    \coordinate (D1_start) at (Offset1);
    \coordinate (D1_end) at ($ (C1V1) + (Offset1) $);
    \draw[<->, thin] (D1_start) -- (D1_end) node[midway, fill=white, sloped] {$c_1 v_1$};
    \draw[dashed, thin, gray] (O) -- (D1_start);
    \draw[dashed, thin, gray] (C1V1) -- (D1_end);

\end{tikzpicture}
\end{document}
```
# CALCOLO EVOLUZIONE LIBERA - MODI NATURALI COMPLESSI
Studiamo ancora una volta l'*evoluzione libera* del sistema.
Come prima, abbiamo:
- $x(0)=x_{0}\ne 0$
- $u(t)=0$

E vogliamo $x_{l}(t)$ soluzione di:
$$
\dot{x}_{l}(t) = Ax_{l}(t)
$$
>[!idea] AUTOVALORI COMPLESSI
>Prima abbiamo studiato il caso in cui gli *autovalori erano tutti reali*, ma cosa succede se tra gli autovalori della matrice $A$ ci sono anche **numeri complessi**?

Supponiamo allora che la matrice $A$ abbia *autovalori complessi coniugati*:
$$\begin{align*}

\lambda_{k} = \alpha_{k} \pm j\omega_{k} & & \alpha_{k},\omega_{k}\in \mathbb{R}
\end{align*}
$$
I *corrispondenti autovettori* (tali che $Av_{k}=\lambda_{k}v_{k}$) sono anch'essi *complessi coniugati*:
$$
\begin{align*}
v_{k} = v_{ka} \pm jv_{kb} & & v_{ka},v_{kb}\in \mathbb{R}^n
\end{align*}
$$
>[!note] NOTA
>Quando l'equazione caratteristica ha radici complesse, queste si presentano *sempre* in *coppia* e, in particolare, sono *speculari* (**complessi coniugati**).
## CASO PARTICOLARE COMPLESSO
Come prima, cominciamo analizzando un *caso semplice*: supponiamo che lo **stato iniziale** sia una *combinazione lineare* dei due **autovettori complessi** $v_{k}'$ e $v_{k}''$ associati all'**autovalore complesso** $\lambda_{k}$.
Abbiamo quindi:
$$
\begin{align*}
x_{0} = c_{1}(v_{ka}+jv_{kb}) + c_{2}(v_{ka}-jv_{kb}) & & c_{1},c_{2}\in \mathbb{C}
\end{align*}
$$
Chiaramente, lo stato iniziale *deve essere un numero reale*.
Imponendo tale condizione troviamo:
$$
x_{0} = c_{ka}v_{ka} + c_{kb}v_{kb}
$$
Dove $c_{ka}$ e $c_{kb}$ sono *numeri reali* che soddisfano:
- $c_{1}=\frac{1}{2}(c_{ka}-jc_{kb})$.
- $c_{2}=\frac{1}{2}(c_{ka}+jc_{kb})$.

>[!note] NOTA
>Prima avevamo considerato uno stato iniziale sulla *direzione di un singolo autovettore*.
>Ora, siccome gli *autovalori complessi* si presentano sempre in *coppie coniugate*, stiamo considerando uno **stato iniziale** che **giace sul piano** individuato dai due *autovettori coniugati*.

Fissato questo stato iniziale, l'*evoluzione libera* risulta:
$$
x_{l}(t) = m_{k} \underbrace{ e^{ -\alpha_{k}t }[\sin(\omega_{k}t + \varphi_{k})v_{ka} + \cos(\omega_{k}t + \varphi_{k})v_{kb}] }_{ \text{modo naturale} }
$$
Dove i parametri $m_{k}$ e $\varphi_{k}$ sono dati da:
$$
\begin{align*}
m_{k} = \sqrt{ c_{ka}^2 + c_{kb}^2 } & & \varphi_{k} : \begin{cases}
\sin \varphi_{k} = \frac{c_{ka}}{m_{k}} \\ \\
\cos \varphi_{k} = \frac{c_{kb}}{m_{k}}
\end{cases}
\end{align*}
$$
## TRAIETTORIE NELLO SPAZIO DI STATO
Dati gli *autovalori coniugati* $\lambda_{k}=\alpha_{k}\pm j\omega_{k}$ ed uno *stato iniziale* sul *piano* individuato dai vettori $v_{ka}$ e $v_{kb}$, vediamo *quali forme può assumere* la **traiettoria nello spazio di stato**:

```tikz
\usetikzlibrary{decorations.markings, arrows.meta}

\begin{document}

\begin{tikzpicture}[
    scale=0.9, 
    transform shape,
    % Definizione dello stile per le frecce lungo la traiettoria
    spiralarrow/.style={
        decoration={markings,
            mark=at position 0.15 with {\arrow{Stealth[length=2.5mm]}},
            mark=at position 0.45 with {\arrow{Stealth[length=2.5mm]}},
            mark=at position 0.75 with {\arrow{Stealth[length=2.5mm]}},
            mark=at position 0.95 with {\arrow{Stealth[length=2.5mm]}}
        },
        postaction={decorate}
    },
    spiralarrowin/.style={
        decoration={markings,
            mark=at position 0.10 with {\arrow{Stealth[length=2.5mm]}},
            mark=at position 0.35 with {\arrow{Stealth[length=2.5mm]}},
            mark=at position 0.65 with {\arrow{Stealth[length=2.5mm]}},
            mark=at position 0.90 with {\arrow{Stealth[length=2.5mm]}}
        },
        postaction={decorate}
    }
]

% Definizione delle funzioni parametriche per le traiettorie
% a: autovalore (parte reale), \t: tempo
\tikzset{
    declare function={
        % Componenti nel piano modale (rotazione oraria)
        u(\t,\a) = exp(\a*\t)*(0.8*cos(deg(\t)) + 0.6*sin(deg(\t)));
        v(\t,\a) = exp(\a*\t)*(0.6*cos(deg(\t)) - 0.8*sin(deg(\t)));
        % Trasformazione nel piano x1, x2 secondo i vettori v_ka e v_kb
        X(\t,\a) = u(\t,\a)*1.4 + v(\t,\a)*0.9;
        Y(\t,\a) = v(\t,\a)*1.6;
    }
}

% ---------------------------------------------------------
% 1. PRIMO GRAFICO: \alpha_k > 0 (Spirale instabile) - 1 GIRO COMPLETO
% ---------------------------------------------------------
\begin{scope}[xshift=0cm]
    % Assi
    \draw[-Stealth] (-2.8,0) -- (2.8,0) node[right] {$x_1$};
    \draw[-Stealth] (0,-2.8) -- (0,2.8) node[right] {$x_2$};
    
    % Autovettori (Ciano)
    \draw[-Stealth, cyan, thick] (0,0) -- (1.4,0) node[below right] {$v_{ka}$};
    \draw[-Stealth, cyan, thick] (0,0) -- (0.9,1.6) node[right] {$v_{kb}$};
    
    % Proiezioni dello stato iniziale (linee tratteggiate)
    % X(0) = 1.66, Y(0) = 0.96
    \draw[cyan, densely dotted, thick] (1.12,0) -- (1.66,0.96);
    \draw[cyan, densely dotted, thick] (0.54,0.96) -- (1.66,0.96);
    
    % Traiettoria: t da 0 a 6.5 (un giro completo è 2*pi =~ 6.28).
    % a=0.04 per farla crescere dolcemente senza uscire troppo dai bordi.
    \draw[spiralarrow] plot [domain=0:6.5, samples=200] ({X(\x, 0.04)}, {Y(\x, 0.04)});
    
    % Punto iniziale
    \filldraw[cyan] (1.66,0.96) circle (1.5pt) node[above, text=black] {$x(0)$};
    
    % Etichetta
    \node at (1.5, -2.5) {$\alpha_k > 0$};
\end{scope}

% ---------------------------------------------------------
% 2. SECONDO GRAFICO: \alpha_k < 0 (Spirale stabile)
% ---------------------------------------------------------
\begin{scope}[xshift=6.5cm]
    % Assi
    \draw[-Stealth] (-2.8,0) -- (2.8,0) node[right] {$x_1$};
    \draw[-Stealth] (0,-2.8) -- (0,2.8) node[right] {$x_2$};
    
    % Autovettori
    \draw[-Stealth, cyan, thick] (0,0) -- (1.4,0) node[below right] {$v_{ka}$};
    \draw[-Stealth, cyan, thick] (0,0) -- (0.9,1.6) node[right] {$v_{kb}$};
    
    % Proiezioni
    \draw[cyan, densely dotted, thick] (1.12,0) -- (1.66,0.96);
    \draw[cyan, densely dotted, thick] (0.54,0.96) -- (1.66,0.96);
    
    % Traiettoria (t da 0 a 18 per mostrare la convergenza verso il centro)
    \draw[spiralarrowin] plot [domain=0:18, samples=300] ({X(\x, -0.12)}, {Y(\x, -0.12)});
    
    % Punto iniziale
    \filldraw[cyan] (1.66,0.96) circle (1.5pt) node[above right, text=black] {$x(0)$};
    
    % Etichetta
    \node at (1.5, -2.5) {$\alpha_k < 0$};
\end{scope}

% ---------------------------------------------------------
% 3. TERZO GRAFICO: \alpha_k = 0 (Modo periodico)
% ---------------------------------------------------------
\begin{scope}[xshift=13cm]
    % Assi
    \draw[-Stealth] (-2.8,0) -- (2.8,0) node[right] {$x_1$};
    \draw[-Stealth] (0,-2.8) -- (0,2.8) node[right] {$x_2$};
    
    % Autovettori
    \draw[-Stealth, cyan, thick] (0,0) -- (1.4,0) node[below right] {$v_{ka}$};
    \draw[-Stealth, cyan, thick] (0,0) -- (0.9,1.6) node[above right] {$v_{kb}$};
    
    % Proiezioni
    \draw[cyan, densely dotted, thick] (1.12,0) -- (1.66,0.96);
    \draw[cyan, densely dotted, thick] (0.54,0.96) -- (1.66,0.96);
    
    % Traiettoria (t da 0 a 2*pi per un'orbita completa)
    \draw[spiralarrow] plot [domain=0:6.2832, samples=150] ({X(\x, 0)}, {Y(\x, 0)});
    
    % Punto iniziale
    \filldraw[cyan] (1.66,0.96) circle (1.5pt) node[above right, text=black] {$x(0)$};
    
    % Etichetta
    \node at (1.5, -2.5) {$\alpha_k = 0$};
\end{scope}

\end{tikzpicture}
\end{document}
```

>[!idea] ANDAMENTO DELLE TRAIETTORIE
>L'*andamento delle traiettorie* dipende dal **segno della parte reale degli autovalori** $\alpha_{k} = \mathrm{Re}(\lambda_{k})$:
>- $\alpha_{k}>0$ : modo **oscillante divergente**.
>- $\alpha_{k}<0$ : modo **oscillante convergente**.
>- $\alpha_{k}=0$ : modo **oscillante periodico**.

A questi andamenti corrispondono le seguenti rappresentazioni nel dominio del tempo:

```tikz
\usepackage{amsmath}
\usetikzlibrary{arrows.meta}

% Definizione di un colore azzurro simile a quello dei libri di testo
\definecolor{myblue}{RGB}{0, 160, 219} 

\begin{document}
\begin{tikzpicture}[
    scale=0.7,
    transform shape,
    axis/.style={-Stealth, thin},
    envelope/.style={dashed, thin, black!70},
    wave/.style={myblue, thick}
]

% ==========================================
% 1. MODO OSCILLANTE DIVERGENTE
% ==========================================
\begin{scope}[xshift=0cm]
    
    % Assi
    \draw[axis] (0,-3.5) -- (0,3.5) node[right, xshift=1mm, yshift=-2mm] {$e^{\alpha_k t}\sin(\omega_k t)$};
    \draw[axis] (0,0) -- (6,0) node[right] {$t$};
    
    % Tacca su asse y
    \draw (-0.1, 1) -- (0.1, 1) node[left] {$1$};
    
    % Inviluppi esponenziali
    \draw[envelope] plot[domain=0:5.5, samples=100] (\x, {exp(0.2*\x)});
    \draw[envelope] plot[domain=0:5.5, samples=100] (\x, {-exp(0.2*\x)});
    
    % Onda divergente
    \draw[wave] plot[domain=0:5.5, samples=200, smooth] (\x, {exp(0.2*\x)*sin(deg(4.5*\x))});
    
    % Etichetta
    \node at (3.5, -2.8) {$\alpha_k > 0$};
\end{scope}

% ==========================================
% 2. MODO OSCILLANTE CONVERGENTE
% ==========================================
\begin{scope}[xshift=8.5cm]
    
    % Assi
    \draw[axis] (0,-3.5) -- (0,3.5) node[right, xshift=1mm, yshift=-2mm] {$e^{\alpha_k t}\sin(\omega_k t)$};
    \draw[axis] (0,0) -- (6,0) node[right] {$t$};
    
    % Tacca su asse y (scalata a 2.5 per riprodurre le proporzioni dell'immagine)
    \draw (-0.1, 2.5) -- (0.1, 2.5) node[left] {$1$};
    
    % Inviluppi esponenziali
    \draw[envelope] plot[domain=0:5.5, samples=100] (\x, {2.5*exp(-0.4*\x)});
    \draw[envelope] plot[domain=0:5.5, samples=100] (\x, {-2.5*exp(-0.4*\x)});
    
    % Onda convergente (uso del coseno per forzare la partenza a y=1 come in figura)
    \draw[wave] plot[domain=0:5.5, samples=200, smooth] (\x, {2.5*exp(-0.4*\x)*cos(deg(5*\x))});
    
    % Etichetta
    \node at (3.5, -2.8) {$\alpha_k < 0$};

\end{scope}

% ==========================================
% 3. MODO PERIODICO
% ==========================================
\begin{scope}[xshift=17cm]
    
    % Assi
    \draw[axis] (0,-3.5) -- (0,3.5) node[right, xshift=1mm, yshift=-2mm] {$e^{\alpha_k t}\sin(\omega_k t)$};
    \draw[axis] (0,0) -- (6,0) node[right] {$t$};
    
    % Tacca su asse y (scalata a 2.5)
    \draw (-0.1, 2.5) -- (0.1, 2.5) node[left] {$1$};
    
    % Inviluppi (linee parallele)
    \draw[envelope] (0, 2.5) -- (5.5, 2.5);
    \draw[envelope] (0, -2.5) -- (5.5, -2.5);
    
    % Onda periodica
    \draw[wave] plot[domain=0:5.5, samples=200, smooth] (\x, {2.5*sin(deg(5*\x))});
    
    % Etichetta
    \node at (3.5, -2.8) {$\alpha_k = 0$};
\end{scope}

\end{tikzpicture}
\end{document}
```

>[!note] NOTA
>La **costante di tempo** che avevamo definito nel *caso reale* esiste anche in questo caso, ma la definizione cambia leggermente:
>$$ \tau_{k} = -\frac{1}{\alpha_{k}} = -\frac{1}{\mathrm{Re}(\lambda_{k})} $$
## ESEMPIO MODI NATURALI COMPLESSI
Consideriamo il seguente sistema d'esempio: $\dot{x}(t)=Ax(t)$ con
$$
\begin{align*}
A = \begin{bmatrix}
1 & -2 \\
2 & 1
\end{bmatrix} & & x(0) = x_{0} = \begin{bmatrix}
-0.5 \\
0.5
\end{bmatrix}
\end{align*}
$$
Come al solito, calcoliamo gli autovalori a partire dall'equazione caratteristica:
$$
\begin{align*}
\det(A-\lambda I) &= 0 \\ \\
\det \begin{bmatrix}
1-\lambda & -2 \\
2 & 1-\lambda
\end{bmatrix} &= 0 \\ \\
(1-\lambda)^2 + 4 &= \lambda^2-2\lambda+5 = 0
\end{align*}
$$
Troviamo quindi:
$$
\lambda_{1,2} = 1\pm 2j
$$
Cioè abbiamo $\alpha_{1}=1>0$ e $\omega_{1}=2$.
Calcoliamo a questo punto gli *autovettori*, cominciando da quello associato a $\lambda_{1}=1+2j$ :
$$
A-\lambda_{1}I = \begin{bmatrix}
1-(1+2j) & -2 \\
2 & 1-(1+2j)
\end{bmatrix} = \begin{bmatrix}
-2j & -2 \\
2 & -2j
\end{bmatrix}
$$
Per cui abbiamo:
$$
\begin{bmatrix}
-2j & -2 \\
-2 & -2j
\end{bmatrix}\begin{bmatrix}
p \\
q
\end{bmatrix} = \begin{bmatrix}
0 \\
0
\end{bmatrix}
$$
Da cui otteniamo:
$$
\begin{align*}
\begin{cases}
-2jp-2q = 0 \\ \\
2p-2jq = 0
\end{cases} & & \implies q = -jp
\end{align*}
$$
Fissiamo per esempio $p=j$ e troviamo:
$$
v = \begin{bmatrix}
j \\
1
\end{bmatrix} = \begin{bmatrix}
0 \\
1
\end{bmatrix} + j\begin{bmatrix}
1 \\
0
\end{bmatrix} = v_{1a} + jv_{1b}
$$
Per $\lambda_{2}=1-2j$ troviamo invece (non serve ripetere lo stesso procedimento, visto che *sappiamo che anche gli autovettori sono coniugati*):
$$
v^* = \begin{bmatrix}
0 \\
1
\end{bmatrix} -j\begin{bmatrix}
1 \\
0
\end{bmatrix} = v_{1a} - jv_{1b}
$$
Scriviamo lo *stato iniziale* come *combinazione lineare dei vettori trovati*:
$$
x_{0} = c_{1a}v_{1a} + c_{1b}v_{1b} = \begin{bmatrix}
-0.5 \\
0.5
\end{bmatrix} = c_{1a}\begin{bmatrix}
0 \\
1
\end{bmatrix} + c_{1b}\begin{bmatrix}
1 \\
0
\end{bmatrix}
$$
Otteniamo:
$$
\begin{align*}
c_{1a} = 0.5 & & c_{1b} = -0.5
\end{align*}
$$
Ora determiniamo i *coefficienti* $m_{1}$ e $\varphi_{1}$:
$$
m_{1} = \sqrt{ c_{1a}^2 + c_{1b}^2 } = \frac{1}{\sqrt{ 2 }}
$$
$$
\begin{align*}
\sin \varphi_{1} &= \frac{c_{1a}}{m_{1}} = \frac{1}{\sqrt{ 2 }} \\ \\
\cos \varphi_{1} &= \frac{c_{1b}}{m_{1}} = -\frac{1}{\sqrt{ 2 }} \\ \\
\implies \varphi &= \frac{3\pi}{4}
\end{align*}
$$
L'*evoluzione libera* è allora data da:
$$
\begin{align*}
x_{l}(t) &= me^{ \alpha_{1}t }[\sin(\omega_{1}t+\varphi_{1})v_{1a} + \cos(\omega_{1}t+\varphi_{1})v_{1b}] \\ \\
&= \frac{1}{\sqrt{ 2 }} e^{ t } \left[ \sin\left( 2t+ \frac{3\pi}{4} \right) \begin{bmatrix} 0 \\ 1 \end{bmatrix} + \cos\left( 2t + \frac{3\pi}{4} \right) \begin{bmatrix} 1 \\ 0 \end{bmatrix} \right] \\ \\
&= \frac{1}{\sqrt{ 2 }}e^{t} \begin{bmatrix}
\cos\left( 2t+ \frac{3\pi}{4} \right) \\
\sin\left( 2t + \frac{3\pi}{4} \right)
\end{bmatrix}
\end{align*}
$$

Che rappresentiamo nello *spazio degli stati* come in figura:

```tikz
\usepackage{amsmath}
\usetikzlibrary{decorations.markings, arrows.meta}

\begin{document}

\begin{tikzpicture}[
    scale=1.3, % Scala generale del grafico
    axis/.style={-Stealth, thin},
    tick/.style={thin},
    % Stile personalizzato per inserire le frecce direzionali lungo la curva
    spiralarrow/.style={
        decoration={markings,
            mark=at position 0.10 with {\arrow{Stealth[length=2.5mm, width=1.8mm]}},
            mark=at position 0.22 with {\arrow{Stealth[length=2.5mm, width=1.8mm]}},
            mark=at position 0.40 with {\arrow{Stealth[length=2.5mm, width=1.8mm]}},
            mark=at position 0.58 with {\arrow{Stealth[length=2.5mm, width=1.8mm]}},
            mark=at position 0.81 with {\arrow{Stealth[length=2.5mm, width=1.8mm]}},
            mark=at position 1.00 with {\arrow{Stealth[length=2.5mm, width=1.8mm]}}
        },
        postaction={decorate}
    }
]

% ==========================================
% 1. ASSI CARTESIANI
% ==========================================
\draw[axis] (-6.2, 0) -- (2.5, 0) node[right] {$x_1$};
\draw[axis] (0, -5.2) -- (0, 3.2) node[right] {$x_2$};


% ==========================================
% 2. TACCHE ED ETICHETTE (TICK MARKS)
% ==========================================
% Asse X (valori negativi con la spaziatura originale)
\foreach \x/\label in {-5/-\,500, -4/-\,400, -3/-\,300, -2/-\,200, -1/-\,100} {
    \draw[tick] (\x, 0.08) -- (\x, -0.08);
    \node[above=2pt, font=\small] at (\x, 0) {$\label$};
}
% Asse X (valori positivi)
\draw[tick] (1, 0.08) -- (1, -0.08);
\node[above left=1pt, font=\small] at (1.1, 0) {$100$};

% Asse Y
\foreach \y/\label in {-4/-\,400, -3/-\,300, -2/-\,200, -1/-\,100, 1/100, 2/200} {
    \draw[tick] (0.08, \y) -- (-0.08, \y);
    \node[left=3pt, font=\small] at (0, \y) {$\label$};
}


% ==========================================
% 3. TRAIETTORIA (SPIRALE)
% ==========================================
% Porzione principale continua (con frecce)
% Dominio da -15 (per nascere esattamente dall'origine) fino a 4.0 radianti
\draw[spiralarrow, semithick] plot [domain=-15:4.0, samples=300] 
    ({1.3 * exp(0.41 * \x) * cos(deg(\x))}, {1.3 * exp(0.41 * \x) * sin(deg(\x))});

% Codino tratteggiato finale
\draw[dashed, semithick] plot [domain=4.0:4.25, samples=15] 
    ({1.3 * exp(0.41 * \x) * cos(deg(\x))}, {1.3 * exp(0.41 * \x) * sin(deg(\x))});

\end{tikzpicture}

\end{document}
```

>[!note] NOTA
>E' un *modo oscillante* **divergente** perchè avevamo $\alpha_{k}=1>0$.
# EVOLUZIONE LIBERA IN GENERALE
I *modi naturali* sono *evoluzioni libere particolari* relative alla componente dello stato iniziale *lungo gli autovettori* che definiscono dei *sottospazi invarianti per le traiettorie*.

>[!idea] EVOLUZIONE LIBERA - CASO PIU' GENERALE
>In generale, l'evoluzione libera è data da una **combinazione lineare** dei **modi naturali** corrispondenti agli **autovalori reali** e **complessi** della matrice $A$. 

Nell'ipotesi che la matrice $A$ abbia $n$ *autovalori* **distinti** (reali o complessi), l'*espressione generale* dell'evoluzione libera è:
$$
x_{l}(t) = \sum_{i=1}^\mu c_{i}e^{ \lambda_{i}t }v_{i} + \sum_{k=1}^\nu m_{k}e^{ \alpha_{k}t } [\sin(\omega_{k}t+\varphi_{k})v_{ka} + \cos(\omega_{k}t+\varphi_{k})v_{kb}]
$$
E lo *stato iniziale* è esprimibile come:
$$
x_{0} = \sum_{i=1}^\mu c_{i}v_{i} + \sum_{k=1}^\nu (c_{ka}v_{ka} + c_{kb}v_{kb})
$$
Dove $\mu$ è il *numero di autovalori reali* e $\nu$ è il *numero di coppie di autovalori complessi coniugati*: vale $\mu+2\nu=n$.

Ricapitolando quanto visto fin'ora, possiamo *classificare le leggi temporali* corrispondenti alle diverse possibili posizioni di un autovalore $\lambda$ nel *piano complesso*:
- $\mathrm{Im}(\lambda) = 0$ : parte **immaginaria nulla**
  - $\mathrm{Re}(\lambda)>0$ : modo **divergente**.
  - $\mathrm{Re}(\lambda)<0$ : modo **convergente**.
  - $\mathrm{Re}(\lambda)=0$ : modo **costante**.
- $\mathrm{Im}(\lambda)\ne 0$ : parte **immaginaria non nulla**
  - $\mathrm{Re}(\lambda)>0$ : modo **oscillante divergente**.
  - $\mathrm{Re}(\lambda)<0$ : modo **oscillante convergente**.
  - $\mathrm{Re}(\lambda)=0$ : modo **oscillante periodico**.
## ESPONENZIALE DI MATRICE ED EVOLUZIONE LIBERA
Possiamo scrivere l'*evoluzione libera* in *forma compatta* con un *esponenziale di matrice* (come accennato in > [[01 - MODELLI DI TRASFERIMENTO DI RISORSE (TEMPO CONTINUO)#RICHIAMO SOLUZIONE DEL SISTEMA]]).
Dati:
$$
\begin{align*}
x_{0} = \sum_{i=1}^n c_{i}v_{i} & & x_{l}(t) = \sum_{i=1}^n c_{i}e^{ \lambda_{i}t }v_{i}
\end{align*}
$$
Possiamo scrivere:

>[!idea] EVOLUZIONE LIBERA IN FORMA COMPATTA
>$$ x_{l}(t) = e^{ At }x_{0} $$

>[!note] NOTA
>In questa forma *non compaiono gli autovalori*: vale *a prescindere* dal fatto che questi siano *reali o complessi*.

A questo punto ci chiediamo: come si calcola l'*esponenziale di una matrice*?
Avevamo visto in Algebra Lineare che possiamo sfruttare lo *sviluppo in serie di Taylor per l'esponenziale*:
$$
e^{ A } := \sum_{k=0}^\infty \frac{A^k}{k!} = I + A + \frac{A^2}{2} + \dots
$$
E' facile intuire tuttavia che *non è realistico utilizzare questo metodo*.
Possiamo invece calcolare $e^{ At }$ sfruttando il seguente fatto: la **prima colonna** di $e^{ At }$, cioè $[e^{ At }]_{1}$ non è altro che l'**evoluzione libera** a partire dallo **stato iniziale** dato da
$$
x_{0} = w_{1} = \begin{bmatrix}
1 \\
0 \\
\vdots \\
0
\end{bmatrix}
$$
Analogamente, la **i-esima colonna** della matrice esponenziale è data da:

>[!th] I-ESIMA COLONNA DELLA MATRICE ESPONENZIALE
>$$ [e^{ At }]_{i} = e^{ At }w_{i} $$

Dove $w_{i}$ è il *versore* dell'$i$-esimo *asse coordinato* (tutti $0$, tranne un $1$ nella riga $i$-esima).

>[!tldr] PROCEDIMENTO
>Una volta determinati tutti gli *autovettori* $v_{i}$, anzichè risolvere:
>$$ x_{0} = \sum_{i=1}^n c_{i}v_{i} $$
>per ricavare i coefficienti $c_{i}$ *specifici per il nostro caso*, risolviamo:
>$$ w_{i} = \sum_{i=1}^n c_{i}v_{i} $$
>$n$ volte: ogni volta troviamo *una colonna* di $e^{ At }$.

>[!idea] PERCHE' FARLO?
>Questo procedimento è particolarmente utile perchè ci permette di **studiare il sistema in generale**, a *prescindere dalla condizione iniziale* $x_{0}$: se successivamente vogliamo studiare l'evoluzione libera del sistema con una *condizione iniziale diversa* $x_{0}'$ possiamo ricavare la nuova espressione per $x_{l}'(t)$ usando la *stessa matrice già calcolata*, semplicemente moltiplicandola per $x_{0}'$.
