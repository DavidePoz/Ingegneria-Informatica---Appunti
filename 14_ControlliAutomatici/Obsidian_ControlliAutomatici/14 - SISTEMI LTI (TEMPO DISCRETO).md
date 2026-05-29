# INDICE SEZIONE
- [ ] [[#TEMPO CONTINUO VS TEMPO DISCRETO]]
- [ ] [[#ANALISI SISTEMI LTI A TEMPO DISCRETO]]
      - [[#EVOLUZIONE LIBERA E MODI NATURALI]]
- [ ] [[#TRASFORMATA Z]]
      - [[#LEGAME CON LA TDL]]
      - [[#PROPRIETA']]
      - [[#ESEMPI DI TRASFORMATE Z]]
- [ ] [[#ANTITRASFORMATE Z]]
      - [[#ESEMPIO ANTITRASFORMATA]]
- [ ] [[#TRASFORMATA Z E CALCOLO E.L. ED R.F.]]
      - [[#CALCOLO EVOLUZIONE LIBERA]]
      - [[#CALCOLO RISPOSTA FORZATA]]
      - [[#ESEMPIO R.F. DINAMICA DEI PREZZI]]
# TEMPO CONTINUO VS TEMPO DISCRETO
Per un sistema a *tempo continuo* abbiamo $t\in \mathbb{R}^+$.

```tikz
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}[>=latex] % Stile delle frecce classico

    % ==========================================
    % 1. GRAFICO DI INGRESSO: u(t)
    % ==========================================
    \begin{scope}[shift={(0,0)}]
        % Assi cartesiani
        \draw[->] (-0.5, 0) -- (2.5, 0) node[right] {\footnotesize $t$};
        \draw[->] (0, -0.5) -- (0, 2.5);
        
        % Etichetta asse Y
        \node[left] at (-0.1, 1.2) {$u(t)$};
        
        % Segnale a gradino/impulso rettangolare
        \draw[thick] (0, 1.2) -- (1.2, 1.2) -- (1.2, 0);
    \end{scope}

    % ==========================================
    % 2. BLOCCO CENTRALE: LTI
    % ==========================================
    % Posizioniamo il blocco al centro della figura
    \node[draw, thick, minimum width=2.5cm, minimum height=1.2cm] (lti) at (5.2, 0.6) {\Large \textsf{LTI}};
    
    % Frecce di collegamento in ingresso e in uscita
    \draw[->] (3.2, 0.6) -- (lti.west);
    \draw[->] (lti.east) -- (7.2, 0.6);

    % ==========================================
    % 3. GRAFICO DI USCITA: x(t)
    % ==========================================
    \begin{scope}[shift={(8.5,0)}]
        % Assi cartesiani
        \draw[->] (-0.5, 0) -- (2.5, 0) node[right] {\footnotesize $t$};
        \draw[->] (0, -0.5) -- (0, 2.5);
        
        % Etichetta asse Y (posizionata a destra dell'asse Y come nell'immagine)
        \node[right] at (0.1, 1.8) {$x(t)$};
        
        % Segnale triangolare (risposta del sistema)
        \draw[thick] (0, 0) -- (0.6, 1.4) -- (2.2, 0);
    \end{scope}

\end{tikzpicture}
\end{document}
```

E abbiamo, come già visto:
$$
\begin{align*}
\dot{x}_{i}(t) &= \sum_{j=1}^n a_{ij}x_{j}(t) + \sum_{j=1}^p b_{ij}u_{j}(t) \\ \\
\dot{x}(t) &= Ax(t) + Bu(t)
\end{align*}
$$
Mentre in **tempo discreto** la *variabile temporale* $k$ è *intera*: $k\in \mathbb{Z}^+$.

```tikz
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}[>=latex] % Stile delle frecce

    % ==========================================
    % 1. GRAFICO DI INGRESSO: u(k)
    % ==========================================
    \begin{scope}[shift={(0,0)}, x=0.45cm, y=1cm]
        % Assi cartesiani
        \draw[->] (-3.5, 0) -- (5.5, 0) node[below] {\footnotesize $k$};
        \draw[->] (0, 0) -- (0, 1.8);
        
        % Etichette
        \node[left] at (-0.1, 1.3) {$u(k)$};
        \node[below] at (0, -0.1) {\footnotesize $0$};
        
        % Segnali discreti (stems)
        % \k = coordinata x, \val = altezza
        \foreach \k/\val in {-3/0, -2/0, -1/0, 0/1, 1/1, 2/1, 3/1, 4/0, 5/0} {
            \draw[thick] (\k, 0) -- (\k, \val);
            \fill (\k, \val) circle (1.8pt);
        }
    \end{scope}

    % ==========================================
    % 2. BLOCCO CENTRALE: LTI
    % ==========================================
    % Il blocco viene posizionato a una distanza adeguata
    \node[draw, thick, minimum width=2.4cm, minimum height=1cm] (lti) at (4.8, 0.5) {\Large \textsf{LTI}};
    
    % Frecce di connessione
    \draw[->, thick] (2.8, 0.5) -- (lti.west);
    \draw[->, thick] (lti.east) -- (6.8, 0.5);

    % ==========================================
    % 3. GRAFICO DI USCITA: x(k)
    % ==========================================
    \begin{scope}[shift={(9.2,0)}, x=0.45cm, y=1cm]
        % Assi cartesiani
        \draw[->] (-3.5, 0) -- (10.5, 0) node[below] {\footnotesize $k$};
        \draw[->] (0, 0) -- (0, 1.8);
        
        % Etichette
        \node[left] at (-0.1, 1.5) {$x(k)$};
        \node[below] at (0, -0.1) {\footnotesize $0$};
        
        % Segnali discreti (stems)
        % Le altezze sono stimate visivamente per replicare la forma d'onda dell'immagine
        \foreach \k/\val in {-3/0, -2/0, -1/0, 0/0.2, 1/0.4, 2/0.7, 3/1.2, 4/1.0, 5/0.8, 6/0.5, 7/0.3, 8/0, 9/0} {
            \draw[thick] (\k, 0) -- (\k, \val);
            \fill (\k, \val) circle (1.8pt);
        }
    \end{scope}

\end{tikzpicture}
\end{document}
```

E abbiamo, anche qui come già visto, delle equazioni di *forma del tutto simile*; l'unica differenza è data dal fatto che in *tempo continuo* abbiamo *equazioni differenziali*, mentre in *tempo discreto* abbiamo *equazioni alle differenze* (**relazioni ricorsive**).
$$
\begin{align*}
x_{i}(k+1) &= \sum_{j=1}^n a_{ij}x_{j}(k) + \sum_{j=1}^p b_{ij}u_{j}(k) \\ \\
x(k+1) &= Ax(k) + Bu(k)
\end{align*}
$$

>[!idea] IMPORTANTE
>Spesso il segnale di ingresso è *continuo*, ma abbiamo *bisogno* di **discretizzarlo**; per questo, è molto importante *scegliere una frequenza di campionamento adeguata*.
# ANALISI SISTEMI LTI A TEMPO DISCRETO
L'analisi dell'*evoluzione libera* e la *risposta forzata* di un *sistema LTI* in *tempo discreto* è *del tutto simile* a quella fatta per sistemi in *tempo continuo*.
Procediamo quindi direttamente con un esempio.

Una banca propone un mutuo pari a $P$ con un *tasso d’interesse fisso* $i$ da estinguere con una rata annuale fissa $R$ (che in questo sistema dinamico *rappresenta l’ingresso* $u(k)$).
La *variabile di stato* $x_{1}(k)$ rappresenta il *debito residuo* dopo $k$ anni e $x_{1}(0)=P$ è la *condizione iniziale*.

Abbiamo quindi:
$$
\begin{align*}
x_{1}(k+1) = (1+i)x_{1}(k) + u(k) & & u(k) = -R
\end{align*}
$$
Per i primi valori di $i$ abbiamo:
$$
\begin{align*}
x_{1}(1) &= (1+i)x_{1}(0) - R \\ \\
x_{1}(2) &= (1+i)x_{1}(1) - R = (1+i)[(1+i)x_{1}(0)] - R = \\ \\
&= (1+i)^2x_{1}(0) - R[1+(1+i)]
\end{align*}
$$
E, in generale, troviamo la *relazione ricorsiva*:
$$
\begin{align*}
x_{1}(k) &= (1+i)^kx_{1}(0) - R[1+(1+i)+\ldots+(1+i)^{k-1}] \\ \\
&= (1+i)^kx_{1}(0) - R \frac{(1+i)^k -1}{i} \\ \\
&= \underbrace{ (1+i)^kP }_{ \text{evoluzione libera} } - \underbrace{ R \frac{(1+i)^k -1}{i} }_{ \text{risposta forzata} }
\end{align*}
$$
## EVOLUZIONE LIBERA E MODI NATURALI
Vediamo di seguito alcune *analogie* e *differenze* tra i sistemi a tempo continuo e quelli a tempo discreto.

Per quanto riguarda l'evoluzione libera, in entrambi i casi dobbiamo calcolare *autovalori* e *autovettori* della matrice $A$.
Fatto questo, scriviamo lo stato iniziale $x(0)$ come combinazione lineare degli autovettori:
$$
x(0) = c_{1}v_{1} + \ldots + c_{n}v_{n}
$$
E i **modi naturali** sono dati da:
1. Tempo **continuo**: $e^{ \lambda_{i}t }v_{i}$.
2. Tempo **discreto**: $\lambda_{i}^k v_{i}$.

Nelle seguenti rappresentazioni nello *spazio degli stati* vediamo i possibili andamenti dei modi naturali in base a $\sigma_{h}=|\lambda_{h}|$:

```tikz
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}[>=latex, font=\small, scale=0.65, transform shape]
\definecolor{mycyan}{RGB}{0,176,240} % Colore ciano fedele all'immagine

% ==========================================
% PRIMA RIGA: RITRATTI DI FASE (Pian x1, x2)
% ==========================================

% --- Grafico in alto a sinistra (sigma > 1) ---
\begin{scope}[shift={(0,0)}]
    % Assi
    \draw[->] (-3.5,0) -- (4,0) node[above] {$x_1$};
    \draw[->] (0,-3) -- (0,3.5) node[left] {$x_2$};

    % Parametri di base
    \def\sig{1.1} % Fattore di scala > 1 (divergente)
    \def\vhbX{0.8} \def\vhbY{1.6}
    \def\vhcX{1.3} \def\vhcY{-0.4}

    % Calcolo delle coordinate
    \foreach \k in {0,1,...,10} {
        \pgfmathsetmacro{\x}{\sig^\k * (cos(45*\k)*\vhbX + sin(45*\k)*\vhcX)}
        \pgfmathsetmacro{\y}{\sig^\k * (cos(45*\k)*\vhbY + sin(45*\k)*\vhcY)}
        \coordinate (P\k) at (\x,\y);
    }

    % Linea tratteggiata e punti
    \draw[densely dotted, black!70] (P0) \foreach \k in {1,...,10} { -- (P\k) };
    \foreach \k in {0,...,10} { \fill[mycyan] (P\k) circle (1.8pt); }

    % Vettori
    \draw[->, mycyan, thick] (0,0) -- (1.5,0) node[below, black] {$v_{ha}$};
    \draw[->, mycyan, thick] (0,0) -- (\vhbX,\vhbY) node[below right, black, xshift=-2pt] {$v_{hb}$};

    % Etichette
    \node[above right, inner sep=2pt] at (P0) {$x(0)$};
    \node[right, inner sep=3pt] at (P1) {$x(1)$};
    \node[below right, inner sep=2pt] at (P2) {$x(2)$};
    \node[below, inner sep=3pt] at (P3) {$x(3)$};
    \node[below left, inner sep=2pt] at (P4) {$x(4)$};
    \node[left, inner sep=3pt] at (P5) {$x(5)$};
    
    \node at (2, -2) {$\sigma_h > 1$};
\end{scope}

% --- Grafico in alto al centro (sigma < 1) ---
\begin{scope}[shift={(8,0)}]
    % Assi
    \draw[->] (-3.5,0) -- (4,0) node[above] {$x_1$};
    \draw[->] (0,-3) -- (0,4) node[left] {$x_2$};

    % Parametri di base
    \def\sig{0.85} % Fattore di scala < 1 (convergente)
    \def\vhbX{2.0} \def\vhbY{3.8} 
    \def\vhcX{3.2} \def\vhcY{-0.9}

    % Calcolo delle coordinate
    \foreach \k in {0,1,...,12} {
        \pgfmathsetmacro{\x}{\sig^\k * (cos(45*\k)*\vhbX + sin(45*\k)*\vhcX)}
        \pgfmathsetmacro{\y}{\sig^\k * (cos(45*\k)*\vhbY + sin(45*\k)*\vhcY)}
        \coordinate (P\k) at (\x,\y);
    }

    % Linea tratteggiata e punti
    \draw[densely dotted, black!70] (P0) \foreach \k in {1,...,12} { -- (P\k) };
    \foreach \k in {0,...,12} { \fill[mycyan] (P\k) circle (1.8pt); }

    % Vettori
    \draw[->, mycyan, thick] (0,0) -- (3.0,0) node[above, black] {$v_{ha}$};
    \draw[->, mycyan, thick] (0,0) -- (\vhbX,\vhbY) node[below right, black] {$v_{hb}$};

    % Etichette
    \node[above right, inner sep=2pt] at (P0) {$x(0)$};
    \node[right, inner sep=3pt] at (P1) {$x(1)$};
    \node[below right, inner sep=2pt] at (P2) {$x(2)$};
    \node[below right, inner sep=2pt] at (P3) {$x(3)$};
    \node[below left, inner sep=2pt] at (P4) {$x(4)$};
    \node[left, inner sep=3pt] at (P5) {$x(5)$};
    
    \node at (2.5, -1.8) {$\sigma_h < 1$};
\end{scope}

% --- Grafico in alto a destra (sigma = 1) ---
\begin{scope}[shift={(16,0)}]
    % Assi
    \draw[->] (-3.5,0) -- (4,0) node[above] {$x_1$};
    \draw[->] (0,-3.5) -- (0,4) node[left] {$x_2$};

    % Parametri di base
    \def\vhbX{1.8} \def\vhbY{3.2}
    \def\vhcX{2.8} \def\vhcY{-0.8}

    % Ellisse continua tratteggiata
    \draw[densely dotted, black!70, smooth, variable=\t, domain=0:360, samples=100] 
        plot ({cos(\t)*\vhbX + sin(\t)*\vhcX}, {cos(\t)*\vhbY + sin(\t)*\vhcY});

    % Calcolo delle coordinate
    \foreach \k in {0,1,...,15} {
        \pgfmathsetmacro{\x}{cos(45*\k)*\vhbX + sin(45*\k)*\vhcX}
        \pgfmathsetmacro{\y}{cos(45*\k)*\vhbY + sin(45*\k)*\vhcY}
        \coordinate (P\k) at (\x,\y);
        \fill[mycyan] (P\k) circle (1.8pt);
    }

    % Vettori
    \draw[->, mycyan, thick] (0,0) -- (2.6,0) node[above, black] {$v_{ha}$};
    \draw[->, mycyan, thick] (0,0) -- (\vhbX,\vhbY) node[below right, black] {$v_{hb}$};

    % Etichette
    \node[above, inner sep=4pt] at (P0) {$x(0)$};
    \node[right, inner sep=3pt] at (P1) {$x(1)$};
    \node[right, inner sep=3pt] at (P2) {$x(2)$};
    \node[below right, inner sep=2pt] at (P3) {$x(3)$};
    \node[below, inner sep=4pt] at (P4) {$x(4)$};
    \node[left, inner sep=3pt] at (P5) {$x(5)$};
    
    \node at (2.5, -2) {$\sigma_h = 1$};
\end{scope}

% ==========================================
% SECONDA RIGA: RISPOSTE NEL TEMPO
% ==========================================

% --- Grafico in basso a sinistra (sigma > 1) ---
\begin{scope}[shift={(0,-7)}]
    % Assi
    \draw[->] (0,-3) -- (0,3) node[right, yshift=6pt] {$\sigma_h^k \sin(\theta_h k)$};
    \draw[->] (0,0) -- (6.5,0) node[above] {$k$};

    \def\sig{1.13}
    \def\yscale{1.1}
    \def\xstep{0.5}

    \foreach \k in {0,1,...,11} {
        \pgfmathsetmacro{\y}{\yscale * \sig^\k * sin(45*\k)}
        \draw[densely dashed] (\k*\xstep, 0) -- (\k*\xstep, \y);
        \fill[mycyan] (\k*\xstep, \y) circle (2.2pt);
    }
    \node at (3.5, 2) {$\sigma_h > 1$};
\end{scope}

% --- Grafico in basso al centro (sigma < 1) ---
\begin{scope}[shift={(8,-7)}]
    % Assi
    \draw[->] (0,-3) -- (0,3) node[right, yshift=6pt] {$\sigma_h^k \sin(\theta_h k)$};
    \draw[->] (0,0) -- (6.5,0) node[above] {$k$};

    \def\sig{0.85}
    \def\yscale{2.5}
    \def\xstep{0.5}

    \foreach \k in {0,1,...,11} {
        \pgfmathsetmacro{\y}{\yscale * \sig^\k * sin(45*\k)}
        \draw[densely dashed] (\k*\xstep, 0) -- (\k*\xstep, \y);
        \fill[mycyan] (\k*\xstep, \y) circle (2.2pt);
    }
    \node at (3.5, 1.2) {$\sigma_h < 1$};
\end{scope}

% --- Grafico in basso a destra (sigma = 1) ---
\begin{scope}[shift={(16,-7)}]
    % Assi
    \draw[->] (0,-3) -- (0,3) node[right, yshift=6pt] {$\sigma_h^k \sin(\theta_h k)$};
    \draw[->] (0,0) -- (6.5,0) node[above] {$k$};

    \def\yscale{1.8}
    \def\xstep{0.5}

    % Limiti orizzontali
    \draw[densely dashed, black!70] (0, \yscale) -- (5.8, \yscale);
    \draw[densely dashed, black!70] (0, -\yscale) -- (5.8, -\yscale);
    \node[left, inner sep=4pt] at (0, \yscale) {$1$};

    \foreach \k in {0,1,...,11} {
        \pgfmathsetmacro{\y}{\yscale * 1.0^\k * sin(45*\k)}
        \draw[densely dashed] (\k*\xstep, 0) -- (\k*\xstep, \y);
        \fill[mycyan] (\k*\xstep, \y) circle (2.2pt);
    }
    \node at (3, 1) {$\sigma_h = 1$};
\end{scope}

\end{tikzpicture}
\end{document}
```

>[!idea] OSSERVAZIONE IMPORTANTE
>La differenza principale tra i sistemi a *tempo continuo* e quelli a *tempo continuo* è la seguente:
>- Tempo **continuo**: le regioni di *convergenza* e *divergenza* sono *separate* dall'**asse immaginario**.
>- Tempo **discreto**: le regioni di *convergenza* e di *divergenza* sono *separate* dalla **circonferenza unitaria**.

In generale, i *modi naturali*, sono dati da:
$$
\sigma_{h}^k \sin(\theta_{h}k)
$$
Dove $\sigma_{h}$ è il *modulo* dell'autovalore e $\theta_{h}$ il suo *argomento*.
Per cui, al variare di $\lambda_{h}\in \mathbb{C}$ abbiamo i seguenti andamenti:

```tikz
\usepackage{tikz}
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}[
    >=stealth,
    dot/.style={circle, fill=cyan!60!blue, minimum size=3.8pt, inner sep=0pt},
    stem/.style={densely dashed, thin},
    axis/.style={->, thin},
    arr/.style={->, gray, thin, shorten >=2pt, shorten <=2pt}
]

% Definizione del colore dei poli
\colorlet{myblue}{cyan!60!blue}

% Macro per snellire il disegno delle sequenze temporali (stem)
\def\dostem#1#2{
    \draw[stem] (#1, 0) -- (#1, #2);
    \node[dot] at (#1, #2) {};
}

% ==============================
% 1. PIANO COMPLESSO CENTRALE
% ==============================
\begin{scope}[shift={(0,0)}]
    % Assi Re(λ) e Im(λ)
    \draw[axis] (-3.5, 0) -- (3.5, 0) node[right] {$\text{Re}(\lambda)$};
    \draw[axis] (0, -2.5) -- (0, 2.5) node[above] {$\text{Im}(\lambda)$};
    
    % Cerchio unitario
    \draw[thick, red] (0,0) circle (1.5);
    \node[above left, inner sep=3pt] at (1.5, 0) {1};

    % Poli Reali
    \node[dot] (pBL) at (-2.4, 0) {};
    \node[dot] (pML) at (-1.5, 0) {};
    \node[dot] (pTL) at (-0.75, 0) {};
    \node[dot] (pBM) at (0.75, 0) {};
    \node[dot] (pBR) at (1.5, 0) {};
    \node[dot] (pMR) at (2.4, 0) {};

    % Poli Complessi Coniugati (sinistra)
    \node[dot] (pTM_top) at (-0.6, 1.0) {};
    \node[dot] (pTM_bot) at (-0.6, -1.0) {};
    \draw[stem, gray] (pTM_top) -- (pTM_bot);

    % Poli Complessi Coniugati (destra)
    \node[dot] (pTR_top) at (1.6, 1.3) {};
    \node[dot] (pTR_bot) at (1.6, -1.3) {};
    \draw[stem, gray] (pTR_top) -- (pTR_bot);
\end{scope}

% ==============================
% 2. GRAFICI DELLE RISPOSTE (TEMPO)
% ==============================

% Top Left (Polo reale negativo interno -> decadimento alternato)
\begin{scope}[shift={(-6, 3.5)}]
    \draw[axis] (0,-1.5) -- (0,1.5);
    \draw[axis] (-0.2,0) -- (3,0) node[above]{$k$};
    \dostem{0}{-1.2} \dostem{0.5}{0.8} \dostem{1.0}{-0.5} \dostem{1.5}{0.3} \dostem{2.0}{-0.2} \dostem{2.5}{0.1}
\end{scope}

% Top Middle (Poli complessi interni -> oscillazione smorzata)
\begin{scope}[shift={(-1.5, 4.5)}]
    \draw[axis] (0,-1.5) -- (0,1.5);
    \draw[axis] (-0.2,0) -- (3.2,0) node[above]{$k$};
    \dostem{0}{1.2} \dostem{0.4}{-0.4} \dostem{0.8}{-1.0} \dostem{1.2}{0.4} \dostem{1.6}{0.7} \dostem{2.0}{-0.2} \dostem{2.4}{-0.4} \dostem{2.8}{0.1}
\end{scope}

% Top Right (Poli complessi esterni -> oscillazione instabile)
\begin{scope}[shift={(3, 3.5)}]
    \draw[axis] (0,-2.0) -- (0,1.5);
    \draw[axis] (-0.2,0) -- (3.2,0) node[above]{$k$};
    \dostem{0}{0.2} \dostem{0.4}{0.4} \dostem{0.8}{-0.3} \dostem{1.2}{-0.7} \dostem{1.6}{0.2} \dostem{2.0}{1.1} \dostem{2.4}{-0.5} \dostem{2.8}{-1.8}
\end{scope}

% Middle Left (Polo reale su cerchio (x=-1) -> oscillazione fissa)
\begin{scope}[shift={(-7.5, 0)}]
    \draw[axis] (0,-1.5) -- (0,1.5);
    \draw[axis] (-0.2,0) -- (3,0) node[above]{$k$};
    \dostem{0}{1} \dostem{0.6}{-1} \dostem{1.2}{1} \dostem{1.8}{-1} \dostem{2.4}{1}
\end{scope}

% Middle Right (Polo reale positivo esterno -> crescita esponenziale)
\begin{scope}[shift={(5, -0.5)}]
    \draw[axis] (0,-0.1) -- (0,2.2);
    \draw[axis] (-0.2,0) -- (3,0) node[above]{$k$};
    \dostem{0}{0.1} \dostem{0.5}{0.2} \dostem{1.0}{0.4} \dostem{1.5}{0.7} \dostem{2.0}{1.2} \dostem{2.5}{2.0}
\end{scope}

% Bottom Left (Polo reale negativo esterno -> crescita alternata instabile)
\begin{scope}[shift={(-6.5, -3.5)}]
    \draw[axis] (0,-2.0) -- (0,1.8);
    \draw[axis] (-0.2,0) -- (3,0) node[above]{$k$};
    \dostem{0}{0.2} \dostem{0.5}{-0.4} \dostem{1.0}{0.7} \dostem{1.5}{-1.1} \dostem{2.0}{1.4} \dostem{2.5}{-1.9}
\end{scope}

% Bottom Middle (Polo reale positivo interno -> decadimento esponenziale)
\begin{scope}[shift={(-1.5, -4)}]
    \draw[axis] (0,-0.1) -- (0,1.8);
    \draw[axis] (-0.2,0) -- (3,0) node[above]{$k$};
    \dostem{0}{1.5} \dostem{0.5}{0.8} \dostem{1.0}{0.4} \dostem{1.5}{0.2} \dostem{2.0}{0.1} \dostem{2.5}{0.05}
\end{scope}

% Bottom Right (Polo reale su cerchio (x=1) -> risposta a gradino fissa)
\begin{scope}[shift={(3, -3.5)}]
    \draw[axis] (0,-0.1) -- (0,1.5);
    \draw[axis] (-0.2,0) -- (3,0) node[above]{$k$};
    \dostem{0}{1} \dostem{0.5}{1} \dostem{1.0}{1} \dostem{1.5}{1} \dostem{2.0}{1} \dostem{2.5}{1}
\end{scope}

% ==============================
% 3. FRECCE DI COLLEGAMENTO
% ==============================
\draw[arr] (pTL) -- (-3.5, 2.0);
\draw[arr] (pTM_top) -- (-0.8, 3.2);
\draw[arr] (pTR_top) -- (3.5, 2.2);
\draw[arr] (pML) -- (-4.5, 0.5);
\draw[arr] (pMR) -- (4.5, 0.5);
\draw[arr] (pBL) -- (-4.2, -2.8);
\draw[arr] (pBM) -- (0.0, -3.2);
\draw[arr] (pBR) -- (3.8, -2);

\end{tikzpicture}
\end{document}
```


>[!note] NOTA
>Se l'autovalore $\lambda_{h}$ è *reale*, allora:
>- Se è **positivo**: $\theta_{h}=0$ e il modo è semplicemente $\sigma_{h}^k\cos(0)=\sigma_{h}^k$.
>- Se è **negativo**: $\theta_{h}=\pi$,  per cui il modo è $\sigma_{h}^k\sin (k\pi)$ e quindi *oscillante*.
# TRASFORMATA Z
Esattamente come già visto per i sistemi a *tempo continuo*, per analizzare il sistema è molto utile fare uso di *trasformate funzionali*.
In *tempo discreto*, anzichè usare la *trasformata di Laplace*, si utilizza la **trasformata Z**.

>[!def] TRASFORMATA Z
>Data una *successione* $x(k)$ con $k\in \mathbb{Z}^+$, si definisce la **trasformata Z unilatera** di $x(k)$ la funzione $X(z)$ seguente:
>$$ X(z) = \mathcal{Z}(x(k)) := \sum_{k=0}^{+\infty} x(k)z^{-k} $$

>[!check] ESEMPIO APPLICAZIONE
>Consideriamo la seguente *successione*:
>$$ x(k) = [4,2,0,5] $$
>Cioè $x(0)=4,x(1)=2$ etc..
>La *trasformata Z* è allora:
>$$ X(z) = 4z^{0} + 2z^{-1} + 0z^{-2} + 5z^{-3} $$

>[!idea] OPERATORE DI RITARDO
>Il termine $z^{-k}$ si comporta come un operatore di **ritardo temporale** di $k$ *passi*.
>
>Per capire meglio, consideriamo una successione $x(k)$.
>Se moltiplichiamo la sua Z-trasformata $X(z)$ per $z^{-a}$ troviamo $X'(z)=z^{-a}X(z)$. Quando poi *antitrasformiamo* per risalire a $x'(k)$, notiamo che $x'(k)$ è una successione che contiene gli **stessi termini** di $x(k)$, ma **spostati a destra** di $a$ posizioni.
## LEGAME CON LA TDL
Consideriamo un *segnale continuo* $x(t)$ e *campioniamolo* con *periodo* $T$.
Il segnale campionato $x_{q}(t)$ risulta essere:
$$
x_{q}(t) = x(0)\delta(t) + x(T)\delta(t-T) + x(2T)\delta(t-2T) + \dots = \sum_{k=0}^{+\infty} x(kT)\delta(t-kT)
$$
Per comodità indichiamo $x(k):=x(kT)$.
Applicando la *trasformata di Laplace* al segnale $x_{q}(k)$ troviamo:
$$
\begin{align*}
X_{q}(s) &= x(0) + x(1)e^{ -sT } + x(2)e^{ -2sT } + \ldots \\ \\
&= \sum_{k=0}^{+\infty}x(k)(e^{ -sT })^k & z:= e^{ sT } \\ \\
&= \sum_{k=0}^{+\infty} x(k)z^{-k}
\end{align*} 
$$

>[!idea] OSSERVAZIONE IMPORTANTE
>La *trasformata Z unilatera* è la *trasformata di Laplace* di un *segnale campionato* in modo ideale (con impulsi di Dirac):
>$$ X_{q}(s) = X(z)\bigg|_{z=e^{ sT }} $$
## PROPRIETA'
Vediamo alcune proprietà utili per calcolare le trasformate Z:
- **LINEARITA'**: $\mathcal{Z}[a_{1}x_{1}(k)+a_{2}x_{2}(k)]=a_{1}X_{1}(z)+a_{2}X_{2}(z)$.
- **MOLTIPLICAZIONE PER k**: $\mathcal{Z}[kx(k)]=-z \frac{dX(z)}{dz}$.
- **MOLTIPLICAZIONE PER K^2**: $\mathcal{Z}[k^2x(k)]=z \frac{dX(z)}{dz} +z^2 \frac{d^2X(z)}{dz^2}$.
- **RITARDO TEMPORALE**: $\mathcal{Z}[x(k-1)]=z^{-1}X(z)$.
- **ANTICIPO TEMPORALE**: $\mathcal{Z}[x(k+1)]=zX(z)-zx(0)$.
- **MOLTIPLICAZIONE PER SUCCESSIONE ESPONENZIALE**: $\mathcal{Z}[\lambda^kx(k)]=X\left( \frac{z}{\lambda} \right)$.
- **PRODOTTO DI CONVOLUZIONE**: $\mathcal{Z}[(x_{1}*x_{2})(k)] = X_{1}(z)X_{2}(z)$
## ESEMPI DI TRASFORMATE Z

```tikz
\usepackage{tikz}
\usepackage{amsmath}
\usepackage{amsfonts} % Necessario per \mathbb{Z} e \mathbb{N}

\begin{document}

\begin{tikzpicture}[x=1cm, y=-1cm]
    
    % --- Intestazione ---
    \node[anchor=west, text=red!75!black, font=\Large\sffamily\bfseries\itshape] at (0, 0) {Esempi di Trasformate Z};
    \node[anchor=east] at (9.5, 0) {$x(k), \quad k \in \mathbb{Z}_+$};
    \node at (10.5, 0) {$\overset{\mathcal{Z}}{\longleftrightarrow}$};
    \node[anchor=west] at (11.5, 0) {$X(z)$};

    \draw[thin] (0, 0.8) -- (16, 0.8);

    % --- Riga 1: Delta di Kronecker ---
    \node[anchor=west, font=\sffamily\bfseries\itshape] at (0, 1.6) {Delta di Kronecker (Impulso unitario discreto)};
    \node[anchor=east] at (9.5, 1.6) {$\delta(k),$};
    \node at (10.5, 1.6) {$\longleftrightarrow$};
    \node[anchor=west] at (11.5, 1.6) {$1$};

    \node[anchor=east] at (9.5, 2.5) {$\delta(k - i), \quad i \in \mathbb{N}$};
    \node at (10.5, 2.5) {$\longleftrightarrow$};
    \node[anchor=west] at (11.5, 2.5) {$z^{-i}$};

    \draw[thin] (0, 3.1) -- (16, 3.1);

    % --- Riga 2: Gradino unitario discreto ---
    \node[anchor=west, font=\sffamily\bfseries\itshape] at (0, 4.0) {Gradino unitario discreto};
    \node[anchor=east] at (9.5, 4.0) {$\delta_{-1}(k)$};
    \node at (10.5, 4.0) {$\longleftrightarrow$};
    \node[anchor=west] at (11.5, 4.0) {$\dfrac{z}{z - 1}$};

    \draw[thin] (0, 4.9) -- (16, 4.9);

    % --- Riga 3: Successione esponenziale causale ---
    \node[anchor=west, font=\sffamily\bfseries\itshape] at (0, 5.8) {Successione esponenziale causale};
    \node[anchor=east] at (9.5, 5.8) {$\lambda^k \delta_{-1}(k)$};
    \node at (10.5, 5.8) {$\longleftrightarrow$};
    \node[anchor=west] at (11.5, 5.8) {$\dfrac{z}{z - \lambda}$};

    \draw[thin] (0, 6.7) -- (16, 6.7);

    % --- Riga 4: K volte la successione... ---
    \node[anchor=west, font=\sffamily\bfseries\itshape] at (0, 7.7) {K volte la successione esponenziale causale};
    \node[anchor=east] at (9.5, 7.7) {$k \lambda^k \delta_{-1}(k)$};
    \node at (10.5, 7.7) {$\longleftrightarrow$};
    \node[anchor=west] at (11.5, 7.7) {$\dfrac{\lambda z}{(z - \lambda)^2}$};

    \draw[thin] (0, 8.8) -- (16, 8.8);

    % --- Riga 5: K^2 volte la successione... ---
    \node[anchor=west, font=\sffamily\bfseries\itshape] at (0, 9.9) {K$^2$ volte la successione esponenziale causale};
    \node[anchor=east] at (9.5, 9.9) {$k^2 \lambda^k \delta_{-1}(k)$};
    \node at (10.5, 9.9) {$\longleftrightarrow$};
    \node[anchor=west] at (11.5, 9.9) {$\dfrac{\lambda z(z + \lambda)}{(z - \lambda)^3}$};

    \draw[thin] (0, 11.0) -- (16, 11.0);

    % --- Riga 6: Successione sinusoidale causale ---
    \node[anchor=west, font=\sffamily\bfseries\itshape] at (0, 12.3) {Successione sinusoidale causale};
    \node[anchor=east] at (9.5, 12.3) {$A\cos(\theta k + \phi)\delta_{-1}(k)$};
    \node at (10.5, 12.3) {$\longleftrightarrow$};
    \node[anchor=west] at (11.5, 12.3) {$A\dfrac{z[z\cos(\phi) - \cos(\phi - \theta)]}{z^2 - 2z\cos\theta + 1}$};

    \draw[thin] (0, 13.5) -- (16, 13.5);

\end{tikzpicture}

\end{document}
```

# ANTITRASFORMATE Z
Sia $X(z)=N(z)/D(z)$ una *funzione razionale strettamente propria* a *coeffiecenti complessi* con *poli distinti non nulli* $\lambda_{1},\lambda_{2},\dots,\lambda_{r}$ di molteplicità, rispettivamente, $\mu_{1},\mu_{2},\dots,\mu_{r}$ ed un eventuale polo nell'origine di molteplicità $\nu\geq 0$:
$$
X(z) = \frac{N(z)}{D(z)} = \frac{\tilde{N}(z)}{z^{\nu}(z-\lambda_{1})^{\mu_{1}}(z-\lambda_{2})^{\mu_{2}}\dots(z-\lambda_{r})^{\mu_{r}}}
$$
Come facevamo anche per la *TDL*, scomponiamo $X(z)$ in fratti semplici:
$$
X(z) = \frac{A_{1}}{z} + \frac{A_{2}}{z^2} + \ldots + \frac{A_{\nu}}{z^\nu} + \ldots + \frac{C_{i1}}{(z-\lambda_{i})} + \frac{C_{i2}}{(z-\lambda_{i})^2} + \ldots + \frac{C_{i\mu_{i}}}{(z-\lambda_{i})^{\mu_{i}}} + \dots
$$

>[!idea] OSSERVAZIONE IMPORTANTE
>Nella tabella delle trasformate notevoli notiamo che è sempre presente un termine $z$ a *numeratore*, che nella scomposizione in fratti semplici non compare.
>
>Allora moltiplichiamo ogni termine $X_{j}$ di $X(z)$ per $\frac{z}{z}$, trovando così termini $X_{j}'$ come quelli *in tabella*, moltiplicati per il *fattore di ritardo* $z^{-1}$:
>$$ X_{j} = z^{-1}X_{j}' $$
>Per le proprietà viste in > [[#PROPRIETA']], basta *antitrasformare* $X_{j}'$ sfruttando la tabella e poi ricordarsi di *modificare l'argomento* da $k$ a $k-1$.
## ESEMPIO ANTITRASFORMATA
Consideriamo la seguente funzione:
$$
X(z) = \frac{z-1}{z(z+2)(z+1)}
$$
Scomponiamo in fratti semplici:
$$
X(z) = \frac{A}{z} + \frac{B}{z+2} + \frac{C}{z+1}
$$
Calcoliamo i residui:
$$
\begin{align*}
A &= \frac{z-1}{(z+2)(z+1)}\bigg|_{z=0} = -\frac{1}{2} \\ \\
B &= \frac{z-1}{z(z+1)}\bigg|_{z=-2} = -\frac{3}{2} \\ \\
C &= \frac{z-1}{z(z+2)}\bigg|_{z=-1} = 2
\end{align*}
$$
Per cui risulta:
$$
X(z) = -\frac{1}{2} \frac{1}{z} -\frac{3}{2} \frac{1}{z+2} +2 \frac{1}{z+1}
$$
Come detto sopra, moltiplichiamo e dividiamo per $z$:
$$
X(z) = -\frac{1}{2}z^{-1} \cancelto{ 1 }{ \frac{z}{z} } - \frac{3}{2}z^{-1} \frac{z}{z+2} +2z^{-1} \frac{z}{z+1}
$$
E, antitrasformando, troviamo:
$$
x(k) = -\frac{1}{2}\delta(k-1) -\frac{3}{2}(-2)^{k-1}\delta_{-1}(k-1) + 2(-1)^{k-1}\delta_{-1}(k-1)
$$
# TRASFORMATA Z E CALCOLO E.L. ED R.F.
Vediamo ora come applicare la *trasformata Z* per calcolare l'*evoluzione libera* e la *risposta forzata* dei sistemi LTI a *tempo discreto*.
## CALCOLO EVOLUZIONE LIBERA
Consideriamo il sistema LTI a tempo discreto:
$$
x(k+1) = Ax(k) + Bu(k)
$$
Ricordiamo che l'*evoluzione libera* è la soluzione al problema con *ingressi nulli* $u(k)=0$ e *condizioni iniziali* date da $x(0)=x_{0}$.
Si tratta allora di risolvere:
$$
\begin{align*}
x_{l}(k+1) = Ax_{l}(k) & & x_{l}(0) = x_{0}
\end{align*}
$$
Applichiamo la *trasformata Z* ad ambo i membri:
$$
\begin{align*}
\mathcal{Z}[x_{l}(k+1)] &= A\mathcal{Z}[x_{l}(k)] \\ \\
zX_{l}(z) - zx_{l}(0) &= AX_{l}(z) \\ \\
(zI-A)X_{l}(z) &= zx_{l}(0) \\ \\
X_{l}(z) &= (zI-A)^{-1}zx_{l}(0)
\end{align*}
$$
>[!note] NOTA
>Anche in questo caso, come anche per i sistemi in *tempo continuo*, dobbiamo determinare l'*inversa* della matrice $zI-A$.
>
>**RICORDA**: per i sistemi a tempo continuo, dovevamo calcolare $(zI-A)x_{l}(0)$.

Anche in questo caso, $(zI-A)^{-1}$ ha come elementi *funzioni razionali strettamente proprie*, e quindi possiamo ricavare facilmente l'*evoluzione libera* sfruttando l'*antitrasformata*:
$$
x_{l}(k) = \mathcal{Z}^{-1}[X_{l}(z)]
$$
## CALCOLO RISPOSTA FORZATA
Ricordiamo che la *risposta forzata* è la soluzione al problema seguente:
$$
\begin{align*}
x_{f}(k+1) = Ax_{f}(k) + Bu(k) & & x_{f}(0) = 0
\end{align*}
$$
Applicando la trasformata ad ambo i membri troviamo:
$$
\begin{align*}
\mathcal{Z}[x_{f}(k+1)] &= \mathcal{Z}[Ax_{f}(k)+Bu(k)] \\ \\
zX_{f}(z) &= AX_{f}(z) + BU(z) \\ \\
X_{f}(z) &= (zI-A)^{-1}BU(z)
\end{align*}
$$
Di nuovo, $X_{f}(z)$ risulterà essere composta da funzioni razionali proprie e potremmo quindi calcolare $x_{f}$ applicando l'antitrasformata:
$$
x_{f}(k) = \mathcal{Z}^{-1}[X_{f}(z)]
$$
## ESEMPIO R.F. : DINAMICA DEI PREZZI
Consideriamo di nuovo l'esempio della *dinamica dei prezzi* e vediamo come calcolare l'*evoluzione forzata*.
Siano:
- $p$ il **prezzo** di un bene.
- $q$ la **quantità** di un bene programmata per la produzione.

Abbiamo:
$$
\begin{align}
\text{Consumatori:} & & q(k) = -ap(k) + D \\ \\
\text{Produttori:} & & q(k+1) = bp(k) + Q
\end{align}
$$
Identifichiamo stato e ingresso:
- **Stato**: $x_{1}(k):=p(k)$
- **Ingresso**: $u_{1}(k):=\bar{u}=(D-Q)$

Allora:
$$
x_{1}(k+1) = -\frac{b}{a}x_{1}(k) + \frac{1}{a}\bar{u}
$$
Applicando la trasformata ad ambo i membri troviamo:
$$
zX_{1}(z) = -\frac{b}{a}X_{1}(z) + \frac{1}{a}\bar{u} \frac{z}{z-1}
$$
Svolgiamo ora qualche passaggio algebrico:
$$
\begin{align*}
\left( z+ \frac{b}{a} \right)X_{1}(z) &= \frac{\bar{u}}{a} \frac{z}{z-1} \\ \\
X_{1}(z) &= \left( \frac{a}{az+b} \right) \frac{\bar{u}}{a} \frac{z}{z-1} \\ \\
&= \bar{u} \frac{z}{(az+b)(z-1)} = \frac{\bar{u}}{a} \frac{z}{\left( z +\frac{b}{a} \right)(z-1)} \\ \\
&= \dots \\ \\
&= \frac{\bar{u}}{a} \left[  \frac{\frac{b}{a}}{\frac{b}{a}+1} \frac{1}{z+\frac{b}{a}} + \frac{1}{1+\frac{b}{a}} \frac{1}{z-1} \right] \\ \\
&= \frac{\bar{u}}{a} \left[  \frac{b}{b+a}z^{-1} \frac{z}{z+\frac{b}{a}} + \frac{a}{a+b}z^{-1} \frac{z}{z-1} \right]
\end{align*}
$$
Infine, applicando l'antitrasformata, troviamo:
$$
\frac{\bar{u}}{a} \left[  \frac{b}{b+a}\left( -\frac{b}{a} \right)^{k-1}\delta_{-1}(k-1) + \frac{a}{a+b}\delta_{-1}(k-1) \right]
$$
