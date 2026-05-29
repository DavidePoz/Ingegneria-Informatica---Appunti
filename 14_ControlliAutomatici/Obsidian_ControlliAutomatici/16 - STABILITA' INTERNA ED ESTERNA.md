# INDICE SEZIONE
- [ ] [[#STABILITA' INTERNA]]
- [ ] [[#STABILITA' ESTERNA]]
      - [[#MATRICE DI TRASFERIMENTO (INGRESSI - STATI)]]
      - [[#MATRICE DI TRASFERIMENTO (INGRESSI - USCITE)]]
- [ ] [[#ESEMPIO - SERBATOI IN SERIE (T.C.)]]
      - [[#ANALISI STABILITA' INTERNA]]
      - [[#ANALISI STABILITA' ESTERNA]]
- [ ] [[#ESEMPIO - AMMORTAMENTO DI UN DEBITO (T.D.)]]
      - [[#STABILITA' INTERNA]]
      - [[#STABILITA' ESTERNA]]
- [ ] [[#OSSERVAZIONI SU SISTEMI SISO]]
      - [[#ESEMPIO AUTOMOBILE (PARTE 1)]]
      - [[#CONSIDERAZIONI SULLA STABILITA']]
      - [[#ESEMPIO AUTOMOBILE (PARTE 2)]]
      - [[#SISTEMI SISO A TEMPO DISCRETO]]
# STABILITA' INTERNA
Consideriamo un sistema a *tempo continuo* e studiamone l'*evoluzione libera*:
$$
\dot{x}_{l}(t) = Ax_{l}(t)
$$
Sappiamo che per *valutare la stabilità del sistema* dobbiamo *analizzare gli autovalori* della matrice $A$, dati dalle radici del polinomio caratteristico:
$$
p_{A}(\lambda) := \det(\lambda I-A)
$$
Se applichiamo la trasformata di Laplace al sistema troviamo:
$$
X_{l}(s) = (sI-A)^{-1}x_{l}(0)
$$
Ma
$$
(sI-A)^{-1} = \frac{N(s)}{\underbrace{ \det(sI-A) }_{ = p_{A}(s) }}
$$
>[!note] NOTA
>Chiaramente, $p_{A}(\lambda)$ e $p_{A}(s)$ hanno le **stesse radici**.

Consideriamo ora un sistema a *tempo discreto*:
$$
x(k+1) = Ax(k)
$$
Anche in questo caso, per studiarne la stabilità dobbiamo prima calcolare gli autovalori della matrice $A$, sempre a partire dal polinomio caratteristico di $A$:
$$
p_{A}(\lambda) := \det(\lambda I-A)
$$
Se applichiamo la trasformata Z al sistema troviamo:
$$
X_{l}(z) = (zI-A)^{-1}zx(0)
$$
E anche in questo caso abbiamo:
$$
(zI-A)^{-1} = \frac{N(z)}{\underbrace{ \det(zI-A }_{ =p_{A}(z) })}
$$
>[!note] NOTA
>Di nuovo, $p_{A}(\lambda)$ e $p_{A}(z)$ hanno le **stesse radici**.

>[!idea] OSSERVAZIONE IMPORTANTE
>Di conseguenza, in *entrambi i casi*, possiamo **studiare la stabilità** del sistema (stabilità *asintotica, marginale* o *instabilità*) anche nel *dominio della trasformata* a partire dal **polinomio al denominatore**.

>[!note] NOTA
>Abbiamo parlato fin'ora di *stabilità dei sistemi* in *assenza di ingressi esterni*.
>Per questo motivo si parla di **stabilità interna**: è una caratteristica del sistema che *non dipende dall'esterno*.
# STABILITA' ESTERNA
Vediamo ora il caso in cui siano presenti degli *ingressi esterni*: studiamo quella che viene detta **stabilità esterna** del sistema.

>[!idea] OSSERVAZIONE IMPORTANTE
>- Stabilità **interna**: analisi del sistema in *assenza di ingressi*.
>- Stabilità **esterna**: analisi del sistema in *presenza di ingressi*.

Avevamo già accennato a sistemi **BIBS** e **BIBO**; vediamo di seguito di cosa si tratta.

>[!def] STABILITA' ESTERNA BIBS
>In corrispondenza di un *ingresso limitato*, l'*evoluzione dello stato* del sistema è *limitata*.
>**Bounded Input - Bounded State**.

![[16_BIBS_system.png]]

>[!def] STABILITA' ESTERNA BIBO
>In corrispondenza di un *ingresso limitato*, l'*evoluzione* di **ogni uscita** del sistema è *limitata*.
>**Bounded Input - Bounded Output**.

Ricordiamo che, in generale, l'*uscita di un sistema* $y(t)$ è data da una *combinazione lineare degli stati e degli ingressi*:
$$
y(t) = Cx(t) + Du(t)
$$

![[16_BIBO_system.png]]

>[!note] NOTA
>La stabilità **BIBS** è una condizione **più forte** della stabilità *BIBO*.
>I sistemi BIBO sono infatti un *sottoinsieme* dei sistemi BIBS.
## MATRICE DI TRASFERIMENTO (INGRESSI - STATI)
### SISTEMI A TEMPO CONTINUO
Consideriamo un sistema LTI a tempo continuo con $m$ ingressi ed $n$ stati:
$$
\dot{x}(t) = Ax(t) + Bu(t)
$$
Applicando la trasformata di Laplace troviamo:
$$
\begin{align*}
X(s) &= (sI-A)^{-1}BU(s) = \\ \\
&= \frac{N(s)}{\det(sI-A)} BU(s)
\end{align*}
$$
Definiamo allora la **matrice di trasferimento** tra *ingressi e stati*:

>[!def] MATRICE DI TRASFERIMENTO INGRESSI-STATI
>E' la matrice per cui si ha:
>$$ X(s) = T(s)U(s) $$
>Ed è data da:
>$$ T(s) = (sI-A)^{-1}B = \frac{N(s)}{\det(sI-A)}B $$

>[!note] NOTA
>La matrice $T(s)$ ha $n$ righe ed $m$ colonne.

La matrice di trasferimento può essere usata per studiare la stabilità BIBS del sistema.
Infatti vale il seguente teorema:

>[!th] TEOREMA: CONDIZIONE PER STABILITA' BIBS
>Il sistema è BIBS - stabile **se e solo se** *tutti i poli effettivi* della matrice di trasferimento ingressi-stati $T(s)$ hanno **parte reale strettamente negativa**.

>[!idea] OSSERVAZIONE IMPORTANTE
>I poli di $T(s)$ coincidono con le *radici del polinomio* $\det(sI-A)$, cioè con gli autovalori di $A$, *solo in assenza di cancellazioni* tra numeratore e denominatore. 
>E' facile quindi intuire che la **stabilità asintotica implica la stabilità BIBS** (ma *non è detto il viceversa*).
### SISTEMI A TEMPO DISCRETO
Similmente, per un sistema a tempo discreto come il seguente:
$$
x(k+1) = Ax(k) + Bu(k)
$$
Applicando la trasformata di Laplace troviamo:
$$
\begin{align*}
X(z) &= (zI-A)^{-1}BU(z) \\ \\
&= \frac{N(z)}{\det(zI-A)}BU(z)
\end{align*}
$$
Anche in questo caso definiamo quindi la matrice di trasferimento tra ingressi e stati:
$$
T(z) = \frac{N(z)}{\det(zI-A)}B
$$
Per i sistemi a tempo discreto vale un teorema, del tutto simile a quello visto per i sistemi a tempo continuo, utile per l'analisi della stabilità.

>[!th] TEOREMA: CONDIZIONE PER STABILITA' BIBS
>Il sistema è BIBS-stabile **se e solo se** *tutti i poli* effettivi della *matrice di trasferimento* hanno *modulo strettamente minore di uno*.

>[!warning] ATTENZIONE
>Anche per i sistemi a tempo discreto, *non è detto* che la stabilità BIBS implichi la stabilità asintotica, sempre a causa di *eventuali cancellazioni* tra numeratore e denominatore nella *matrice di trasferimento*.
## MATRICE DI TRASFERIMENTO (INGRESSI - USCITE)
### SISTEMI A TEMPO CONTINUO
Molto spesso siamo interessati *più alle uscite* che agli stati; per questo è utile studiare la *matrice di trasferimento tra gli ingressi e le uscite*. 

>[!idea] MOTIVAZIONE
>Nei sistemi reali, ha *poco senso* impiegare un numero elevato di *sensori* per *monitorare ogni singola variabile di stato del sistema*.
>E' molto più comune impiegare sensori solo per *monitorare le uscite*.
>
>**NOTA**: molto spesso, il *numero di uscite* è *minore (anche di molto)* del *numero di stati*.

Dato un sistema LTI a tempo continuo con $n$ stati, $m$ ingressi e $p$ uscite scriviamo:
$$
\begin{cases}
\dot{x}(t) = Ax(t) + Bu(t) \\ \\
y(t) = Cx(t) + Du(t)
\end{cases}
$$
Applicando la *trasformata di Laplace* alla prima equazione e sostituendo poi nella seconda troviamo:
$$
\begin{cases}
X(s) = (sI-A)^{-1}BU(s) \\ \\
Y(s) = C(sI-A)^{-1}BU(s) + DU(s)
\end{cases}
$$
Abbiamo allora:
$$
Y(s) = \underbrace{ [C(sI-A)^{-1}B + D] }_{ G(s) } U(s)
$$
Definiamo in questo caso la *matrice di trasferimento* tra **ingressi e uscite**:

>[!def] MATRICE DI TRASFERIMENTO INGRESSI - USCITE
>E' la matrice per cui si ha:
>$$ Y(S) = G(s)U(s) $$
>Ed è data da:
>$$ G(s) = C(sI-A)^{-1}B + D $$ 

La matrice di trasferimento tra ingressi e stati era utile per lo studio della stabilità BIBS.
Similmente, la matrice di trasferimento tra *ingressi e uscite* è utile per lo studio della **stabilità BIBO**.
Vale infatti il seguente teorema:

>[!th] TEOREMA: CONDIZIONE PER STABILITA' BIBO
>Il sistema è BIBO - stabile **se e solo se** *tutti i poli effettivi della matrice di trasferimento* $G(s)$ hanno *parte reale strettamente negativa*.

>[!warning] ATTENZIONE
>La *stabilità asintotica* implica la *stabilità BIBS*, che *a sua volta implica stabilità BIBO*.
>**Non è vero il viceversa**, sempre a causa di *eventuali cancellazioni* tra numeratore e denominatore nella matrice di trasferimento.
### SISTEMI A TEMPO DISCRETO
In modo del tutto simile, per sistemi a tempo discreto troviamo che la matrice di trasferimento tra ingressi e uscite è data da:
$$
G(z) = C(zI-A)^{-1}B + D
$$
Di nuovo, vale un teorema simile a quello visto per i sistemi a tempo continuo:

>[!th] TEOREMA: CONDIZIONE PER STABILIA'
>Il sistema è BIBO - stabile **se e solo se** *tutti i poli effettivi della matrice di trasferimento* $G(z)$ hanno *modulo strettamente minore di uno*.

>[!warning] ATTENZIONE
>La *stabilità asintotica* implica la *stabilità BIBS*, che *a sua volta implica stabilità BIBO*.
>**Non è vero il viceversa**, sempre a causa di *eventuali cancellazioni* tra numeratore e denominatore nella matrice di trasferimento.
# ESEMPIO - SERBATOI IN SERIE (T.C.)
Consideriamo il solito esempio dei *due serbatoi in serie*, come in figura:

```tikz
\usepackage{tikz}
\usetikzlibrary{positioning, arrows.meta}

\begin{document}

\begin{tikzpicture}[
    >=stealth,
    tank/.style={thick},
    water/.style={thin, gray},
    pipe/.style={->, thick},
    sqnode/.style={draw, thick, rectangle, minimum size=0.8cm, font=\Large},
    circnode/.style={draw, thick, circle, minimum size=1.1cm, font=\Large}
]

% ==========================================
% PARTE SUPERIORE: SISTEMA FISICO
% ==========================================

% -- Serbatoio 1 --
\draw[tank] (0, 2) -- (0, 0) -- (2, 0) -- (2, 2); % Bordi
\draw[water] (0, 1.3) -- (2, 1.3); % Livello dell'acqua
\node[font=\large] at (1, 0.65) {$x_1(t)$};

% Valvola e ingresso u1(t)
\node[font=\large] at (-0.5, 2.9) {$u_1(t)$};
% Simbolo stilizzato della valvola a farfalla
\draw[thick] (-0.5, 2.3) -- (-0.5, 2.6);
\draw[thick] (-0.8, 2.6) -- (-0.2, 2.6);
\draw[thick] (-0.7, 2.5) -- (-0.3, 2.1) -- (-0.7, 2.1) -- (-0.3, 2.5);
% Tubo in ingresso al serbatoio
\draw[pipe] (-0.3, 2.3) -- (0.5, 2.3) -- (0.5, 1.6);

% Tubo in uscita dal Serbatoio 1
\draw[pipe] (2, 0.2) -- (2.8, 0.2);


% -- Serbatoio 2 --
\draw[tank] (2.5, -0.8) -- (2.5, -2.5) -- (4.5, -2.5) -- (4.5, -0.8); % Bordi
\draw[water] (2.5, -1.4) -- (4.5, -1.4); % Livello dell'acqua
\node[font=\large] at (3.5, -1.9) {$x_2(t)$};

% Tubo in uscita dal Serbatoio 2 all'ambiente
\draw[pipe] (4.5, -2.3) -- (5.2, -2.3) node[right, font=\large] {ambiente};


% ==========================================
% PARTE INFERIORE: GRAFO ASSOCIATO
% ==========================================
% Utilizzo un ambiente scope per spostare facilmente tutto il blocco in basso
\begin{scope}[yshift=-5.5cm, xshift=-0.5cm]
    
    % Nodo Input (1 quadrato)
    \node[sqnode] (u) at (0,0) {1};
    \node[above=0.1cm of u, font=\large] {$u_1(t)$};

    % Nodo x1 (1 tondo)
    \node[circnode, right=1.8cm of u] (x1) {1};
    \node[above=0.1cm of x1, font=\large] {$x_1(t)$};

    % Nodo x2 (2 tondo)
    \node[circnode, right=1.8cm of x1] (x2) {2};
    \node[above=0.1cm of x2, font=\large] {$x_2(t)$};

    % Nodo Ambiente (0 quadrato)
    \node[sqnode, right=1.8cm of x2] (env) {0};

    % Archi di connessione con etichette
    \draw[->, thick] (u) -- node[above] {1} (x1);
    \draw[->, thick] (x1) -- node[above] {$\alpha_{12}$} (x2);
    \draw[->, thick] (x2) -- node[above] {$\alpha_{20}$} (env);

\end{scope}

\end{tikzpicture}

\end{document}
```

Otteniamo allora le seguenti equazioni:
$$
\begin{cases}
\dot{x}_{1}(t) = u(t) - \alpha_{12}x_{1}(t) \\ \\
\dot{x}_{2}(t) = \alpha_{12}x_{1}(t) - \alpha_{20}x_{2}(t)
\end{cases}
$$
Abbiamo:
$$
\begin{align*}
x(t) = \begin{bmatrix}
x_{1}(t) \\
x_{2}(t)
\end{bmatrix} & & A = \begin{bmatrix}
-\alpha_{12} & 0 \\
\alpha_{12} & -\alpha_{20}
\end{bmatrix} & & B = \begin{bmatrix}
1 \\
0
\end{bmatrix}
\end{align*}
$$
## ANALISI STABILITA' INTERNA
Cominciamo con l'analisi dellì*evoluzione libera* e della *stabilità interna*.

Abbiamo:
$$
sI - A = \begin{bmatrix}
s + \alpha_{12} & 0 \\
-\alpha_{12} & s + \alpha_{20}
\end{bmatrix}
$$
Per cui nel dominio $s$ troviamo:
$$
\begin{align*}
X(s) &= (sI-A)^{-1}x_{0} = \frac{\mathrm{adj}(sI-A)}{\det(sI-A)}x(0) \\ \\
&= \frac{1}{(s+\alpha_{12})(s+\alpha_{20})}\begin{bmatrix}
s + \alpha_{20} & 0 \\
\alpha_{12} & s + \alpha_{12}
\end{bmatrix}x(0)
\end{align*}
$$
Notiamo subito che le *radici del polinomio caratteristico* sono $-\alpha_{12}$ e $-\alpha_{20}$: sono entrambe reali e *negative*, per cui il sistema è **asintoticamente stabile**.

Per vedere un esempio numerico, consideriamo:
$$
\begin{align*}
\alpha_{12} = 2 & & \alpha_{20} = 1 & & x(0) = \begin{bmatrix}
1 \\
0
\end{bmatrix}
\end{align*}
$$
Allora troviamo:
$$
\begin{align*}
X_{1}(s) &= \frac{x_{1}(0)}{s+\alpha_{12}} & & \implies x_{1}(t) = e^{ -2t }\delta_{-1}(t) \\ \\
X_{2}(s) &= \frac{\alpha_{12}}{(s+\alpha_{12})(s+\alpha_{20})} & & \implies x_{2}(t) = 2(e^{ -t } -e^{ -2t } )\delta_{-1}(t)
\end{align*}
$$
La dinamica del sistema è quindi rappresentata dai seguenti diagrammi:

```tikz
\usepackage{tikz}
\usetikzlibrary{decorations.markings}

\begin{document}

\begin{tikzpicture}[
    font=\sffamily,
    >=stealth, % Stile delle frecce
    axis/.style={darkgray, thick},
    grid/.style={lightgray!70, densely dotted, very thin}
]

% ==========================================
% GRAFICO SINISTRO: Traiettoria nello spazio degli stati
% ==========================================
\begin{scope}[x=5cm, y=8cm] % Scala manuale assi: X(0..1) -> 5cm, Y(0..0.5) -> 4cm

    % Griglia
    \draw[grid] (0,0) grid[xstep=0.2, ystep=0.1] (1,0.5);

    % Assi
    \draw[axis] (0,0) -- (1.05,0);
    \draw[axis] (0,0) -- (0,0.53);

    % Tick e Label Asse X
    \foreach \x in {0.0, 0.2, 0.4, 0.6, 0.8, 1.0} {
        \draw[darkgray] (\x, -0.01) -- (\x, 0.01);
        \node[below, darkgray, font=\scriptsize] at (\x, -0.01) {\x};
    }
    
    % Tick e Label Asse Y
    \foreach \y in {0.0, 0.1, 0.2, 0.3, 0.4, 0.5} {
        \draw[darkgray] (-0.01, \y) -- (0.01, \y);
        \node[left, darkgray, font=\scriptsize] at (-0.01, \y) {\y};
    }

    % Etichette Assi e Titolo
    \node[below, darkgray] at (0.5, -0.08) {\scriptsize $x_1(t)$};
    \node[left, darkgray, rotate=90] at (-0.20, 0.25) {\scriptsize $x_2(t)$};
    \node[above, darkgray] at (0.5, 0.53) {\small Traiettoria nello spazio degli stati};

    % Curva Parametrica con Frecce direzionali
    % Traccio da x=1 a x=0 così le frecce puntano automaticamente verso l'origine
    \draw[blue, thick, postaction={decorate}, decoration={
        markings,
        mark=at position 0.2 with {\arrow[scale=1.5]{stealth}},
        mark=at position 0.5 with {\arrow[scale=1.5]{stealth}}
    }] 
    plot[domain=1:0.0001, samples=100] (\x, {2*(sqrt(\x) - \x)});

\end{scope}

% ==========================================
% GRAFICO DESTRO: Dinamica degli stati nel tempo
% ==========================================
\begin{scope}[xshift=7.5cm, x=0.55cm, y=4cm] % xshift sposta il grafico a destra

    % Griglia
    \draw[grid] (0,0) grid[xstep=2, ystep=0.2] (10,1);

    % Assi
    \draw[axis] (0,0) -- (10.5,0);
    \draw[axis] (0,0) -- (0,1.05);

    % Tick e Label Asse X
    \foreach \x in {0, 2, 4, 6, 8, 10} {
        \draw[darkgray] (\x, -0.01) -- (\x, 0.01);
        \node[below, darkgray, font=\scriptsize] at (\x, -0.02) {\x};
    }
    
    % Tick e Label Asse Y
    \foreach \y in {0.0, 0.2, 0.4, 0.6, 0.8, 1.0} {
        \draw[darkgray] (-0.1, \y) -- (0.1, \y);
        \node[left, darkgray, font=\scriptsize] at (-0.1, \y) {\y};
    }

    % Etichette Assi e Titolo
    \node[below, darkgray] at (5, -0.15) {\scriptsize Tempo [s]};
    \node[above, darkgray] at (5, 1.05) {\small Dinamica degli stati nel tempo};

    % Curve esponenziali nel tempo
    % x1(t) = e^(-2t)
    \draw[orange, thick] plot[domain=0:10, samples=100, smooth] (\x, {exp(-2*\x)});
    
    % x2(t) = 2(e^(-t) - e^(-2t))
    \draw[red!70!orange, thick] plot[domain=0:10, samples=100, smooth] (\x, {2*(exp(-\x) - exp(-2*\x))});

    % Legenda
    \begin{scope}[shift={(7.5, 0.75)}] % Posizionamento manuale della legenda
        \draw[lightgray, fill=white, rounded corners=2pt] (0, 0) rectangle (2.2, 0.3);
        
        % Riga x1(t)
        \draw[orange, thick] (0.2, 0.2) -- (0.7, 0.2);
        \node[right, darkgray, font=\tiny] at (0.7, 0.2) {$x_1(t)$};
        
        % Riga x2(t)
        \draw[red!70!orange, thick] (0.2, 0.1) -- (0.7, 0.1);
        \node[right, darkgray, font=\tiny] at (0.7, 0.1) {$x_2(t)$};
    \end{scope}

\end{scope}

\end{tikzpicture}

\end{document}
```
## ANALISI STABILITA' ESTERNA
Teniamo ora in considerazione anche l'ingresso esterno:
$$
\dot{x}(t) = Ax(t) + Bu_{1}(t)
$$
Sappiamo che nel dominio $s$ la risposta forzata è data da:
$$
X(s) = (sI-A)^{-1}BU_{1}(s)
$$
E che la *matrice di trasferimento* tra ingresso e stato è:
$$
\begin{align*}
T(s) &= (sI-A)^{-1}B \\ \\
&= \frac{1}{(s+\alpha_{12})(s+\alpha_{20})}\begin{bmatrix}
s + \alpha_{20} & 0 \\
\alpha_{12} & s + \alpha_{12}
\end{bmatrix}\begin{bmatrix}
1 \\
0
\end{bmatrix} \\ \\
&= \frac{1}{(s+\alpha_{12})(s+\alpha_{20})}\begin{bmatrix}
s + \alpha_{20} \\
\alpha_{12}
\end{bmatrix}
\end{align*}
$$

>[!note] NOTA
>I *poli del denominatore* sono *reali strettamente negativi*, quindi, per il teorema visto sopra, il sistema è **BIBS - stabile**.
>
>Notiamo inoltre che nell'espressione relativa al *primo stato* c'è una *cancellazione*, che "*nasconde*" uno dei poli. Tuttavia *non perdiamo alcuna informazione* perchè nell'espressione *relativa al secondo stato non c'è nessuna cancellazione*.

Per vedere un esempio numerico, consideriamo gli stessi coefficienti di prima e un ingresso dato dal *gradino unitario*:
$$
\begin{align*}
u_{1}(t) = \delta_{-1}(t) & & U_{1}(s) = \frac{1}{s}
\end{align*}
$$
Abbiamo allora:
$$
\begin{align*}
X_{1}(s) &= \frac{1}{s(s+2)} = \frac{1}{2}\left( \frac{1}{s} - \frac{1}{s+2} \right) \\ \\
X_{2}(s) &= \frac{2}{s(s+1)(s+2)} = \frac{1}{s} - \frac{2}{s+1} + \frac{1}{s+2}
\end{align*}
$$
E applicando l'antitrasformata troviamo:
$$
\begin{align*}
x_{1}(t) &= \frac{1}{2}(1-e^{ -2t })\delta_{-1}(t) \\ \\
x_{2}(t) &= (1-2e^{ -t } +e^{ -2t })\delta_{-1}(t)
\end{align*}
$$
Per cui l'andamento del sistema e la dinamica degli stati sono i seguenti:

```tikz
\usepackage{tikz}
\usetikzlibrary{decorations.markings}

\begin{document}

\begin{tikzpicture}[
    font=\sffamily,
    >=stealth, % Stile delle frecce
    axis/.style={darkgray, thick},
    grid/.style={lightgray!70, densely dotted, very thin}
]

% ==========================================
% GRAFICO SINISTRO: Spazio degli stati
% ==========================================
\begin{scope}[x=10cm, y=4cm]

    % Griglia
    \draw[grid] (0,0) grid[xstep=0.1, ystep=0.2] (0.5,1);

    % Assi
    \draw[axis] (0,0) -- (0.52,0);
    \draw[axis] (0,0) -- (0,1.05);

    % Tick e Label Asse X
    \foreach \x in {0.0, 0.1, 0.2, 0.3, 0.4, 0.5} {
        \draw[darkgray] (\x, -0.02) -- (\x, 0.02);
        \node[below, darkgray, font=\scriptsize] at (\x, -0.02) {\x};
    }
    
    % Tick e Label Asse Y
    \foreach \y in {0.0, 0.2, 0.4, 0.6, 0.8, 1.0} {
        \draw[darkgray] (-0.01, \y) -- (0.01, \y);
        \node[left, darkgray, font=\scriptsize] at (-0.01, \y) {\y};
    }

    % Etichette Assi e Titolo
    \node[below, darkgray] at (0.25, -0.12) {\scriptsize $x_1(t)$};
    \node[left, darkgray, rotate=90] at (-0.08, 0.5) {\scriptsize $x_2(t)$};
    \node[above, darkgray] at (0.25, 1.05) {\small Spazio degli stati (BIBS stabile)};

    % Curva parametrica nel tempo (t da 0 a 6 secondi è sufficiente per l'asintoto)
    % x1(t) = 0.5 * (1 - e^(-2t))
    % x2(t) = (1 - e^(-t))^2
    \draw[green!50!black, thick, postaction={decorate}, decoration={
        markings,
        mark=at position 0.15 with {\arrow[scale=1.5]{stealth}},
        mark=at position 0.65 with {\arrow[scale=1.5]{stealth}}
    }] 
    plot[domain=0:6, samples=150] ({0.5*(1 - exp(-2*\x))}, {(1 - exp(-\x))^2});

\end{scope}

% ==========================================
% GRAFICO DESTRO: Dinamica nel tempo
% ==========================================
\begin{scope}[xshift=6.5cm, x=0.55cm, y=4cm] % xshift sposta il grafico a destra

    % Griglia
    \draw[grid] (0,0) grid[xstep=2, ystep=0.2] (10,1);

    % Assi
    \draw[axis] (0,0) -- (10.5,0);
    \draw[axis] (0,0) -- (0,1.05);

    % Tick e Label Asse X
    \foreach \x in {0, 2, 4, 6, 8, 10} {
        \draw[darkgray] (\x, -0.02) -- (\x, 0.02);
        \node[below, darkgray, font=\scriptsize] at (\x, -0.02) {\x};
    }
    
    % Tick e Label Asse Y
    \foreach \y in {0.0, 0.2, 0.4, 0.6, 0.8, 1.0} {
        \draw[darkgray] (-0.1, \y) -- (0.1, \y);
        \node[left, darkgray, font=\scriptsize] at (-0.1, \y) {\y};
    }

    % Etichette Assi e Titolo
    \node[below, darkgray] at (5, -0.12) {\scriptsize Tempo [s]};
    \node[above, darkgray] at (5, 1.05) {\small Dinamica degli stati nel tempo (BIBS)};

    % Curva x1(t) = 0.5 * (1 - e^(-2t))
    \draw[orange!95!yellow, thick] plot[domain=0:10, samples=100, smooth] (\x, {0.5*(1 - exp(-2*\x))});
    
    % Curva x2(t) = 1 - 2e^(-t) + e^(-2t)
    \draw[orange!90!red, thick] plot[domain=0:10, samples=100, smooth] (\x, {1 - 2*exp(-\x) + exp(-2*\x)});

    % Legenda
    \begin{scope}[shift={(10.5, 0.75)}] % Posizionamento manuale in alto a sinistra
        \draw[lightgray, fill=white, rounded corners=2pt] (0, 0) rectangle (1.8, 0.45);
        
        % Riga x1(t)
        \draw[orange!95!yellow, thick] (0.15, 0.3) -- (0.55, 0.3);
        \node[right, darkgray, font=\tiny] at (0.55, 0.3) {$x_1(t)$};
        
        % Riga x2(t)
        \draw[orange!90!red, thick] (0.15, 0.15) -- (0.55, 0.15);
        \node[right, darkgray, font=\tiny] at (0.55, 0.15) {$x_2(t)$};
    \end{scope}

\end{scope}

\end{tikzpicture}

\end{document}
```
# ESEMPIO - AMMORTAMENTO DI UN DEBITO (T.D.)
Vediamo ora un esempio riguardo ad un sistema in tempo discreto.

Consideriamo quindi una banca che propone un mutuo pari a $P$ con un *tasso d'interesse fisso* $i>0$ da estinguere con una *rata annuale fissa* $R$.

Abbiamo allora:
- $x(k)$ è il **debito** al tempo $k$.
- $x(0)=P$.
- $u(k):=R\delta_{-1}(k)$.
- $x(k+1)=(1+i)x(k)-u(k)$.
## STABILITA' INTERNA
Abbiamo:
$$
\begin{align*}
x(k+1) &= Ax(k) \\ \\
x(0) &= P
\end{align*}
$$
Applicando la trasformata Z:
$$
\begin{align*}
\mathcal{Z}[x(k+1)] &= \mathcal{Z}[Ax(k)] \\ \\
zX(z) - zP &= (1+i)X(z) \\ \\
X(z) &= P \frac{z}{z-(1+i)}
\end{align*}
$$
>[!note] NOTA
>Uno dei poli è $1+i$, che ha **modulo maggiore di uno**.
>Allora il sistema è **instabile**.

Infatti, antitrasformando troviamo:
$$
x(k) = P(1+i)^k
$$

```tikz
\usepackage{pgfplots}

\definecolor{mplblue}{HTML}{1F77B4}

\begin{document}

\begin{tikzpicture}
\begin{axis}[
    width=12cm, height=8cm, % Proporzioni simili all'immagine originale
    title={Evoluzione libera del debito con P = 1000 e interesse i = 5\%},
    xlabel={Anno (k)},
    ylabel={x(k) [euro]},
    xmin=-0.8, xmax=15.8,
    ymin=-80, ymax=2100,
    xtick={0,2,4,6,8,10,12,14},
    ytick={0,250,500,750,1000,1250,1500,1750,2000},
    grid=major,
    major grid style={dotted, gray!60}, % Griglia tratteggiata leggera
    enlarge x limits=false,
    enlarge y limits=false,
    tick label style={font=\small},
    title style={font=\normalsize},
]

% Linea orizzontale in corrispondenza dello zero (simile all'asse x di matplotlib)
\draw[mplblue!60, thick] (axis cs:-1,0) -- (axis cs:16,0);

% Grafico a stelo (stem plot)
\addplot[
    ycomb,            % Modalità "stem" (linee verticali)
    color=mplblue,    % Colore della linea
    mark=*,           % Pallino alla fine
    mark size=1.5pt,  % Dimensione del pallino
    thick,            % Spessore della linea
    samples at={0,1,...,15} % Punti sull'asse x
] {1000 * (1.05)^x}; % Formula matematica per generare i dati

\end{axis}
\end{tikzpicture}

\end{document}
```
## STABILITA' ESTERNA
Ora abbiamo:
$$
\begin{align*}
x(k+1) &= (1+i)x(k) - u(k) \\ \\
x(0) &= 0
\end{align*}
$$
Applicando la trasformata Z:
$$
\begin{align*}
\mathcal{Z}[x(k+1)] = \mathcal{Z}[(1+i)x(k)-u(k)] \\ \\
zX(z) - z\cancelto{ 0 }{ x(0) } = (1+i)X(z) - R \frac{z}{z-1}
\end{align*}
$$
Svolgendo dei semplici passaggi algebrici troviamo:
$$
X(z) = - \frac{Rz}{(z-1)(z-(1+i))}
$$

>[!note] NOTA
>Uno dei *poli* è $1+i$, il cui **modulo è maggiore di uno**.
>Allora il sistema **non è BIBS stabile**.

Scomponendo in fratti semplici troviamo:
$$
X(z) = \frac{R}{i} \frac{z}{z-1} - \frac{R}{i} \frac{z}{z-(1+i)}
$$
E, antitrasformando, otteniamo:
$$
x(k) = \frac{R}{i}[1-(1+i)^k]\delta_{-1}(k)
$$

```tikz
\usepackage{pgfplots}

\definecolor{mplblue}{HTML}{1F77B4}

\begin{document}

\begin{tikzpicture}
\begin{axis}[
    width=12cm, height=8cm,
    title={Credito con rata R = 200 euro e interesse i = 5\%},
    xlabel={anno k},
    ylabel={x(k) [euro] (negativo = credito)},
    xmin=-0.8, xmax=15.8,
    ymin=-4500, ymax=250, % Limiti asse y per far respirare il grafico
    xtick={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15},
    ytick={0,-1000,-2000,-3000,-4000},
    grid=major,
    major grid style={solid, gray!20}, % Griglia continua e molto chiara, come nell'immagine
    enlarge x limits=false,
    enlarge y limits=false,
    tick label style={font=\small},
    title style={font=\normalsize},
]

% Linea orizzontale in corrispondenza dello zero
\draw[mplblue!60, thick] (axis cs:-1,0) -- (axis cs:16,0);

% Grafico a stelo (stem plot)
\addplot[
    ycomb,
    color=mplblue,
    mark=*,
    mark size=1.5pt,
    thick,
    samples at={0,1,...,15}
] {4000 * (1 - (1.05)^x)}; % Formula per l'accumulo del credito

\end{axis}
\end{tikzpicture}

\end{document}
```
# OSSERVAZIONI SU SISTEMI SISO
Facciamo ora delle considerazioni su una categoria particolare di sistemi, detti **SISO**.

>[!def] SISTEMA SISO
>Sistema **Single Input - Single Output**.

Consideriamo un sistema descritto da un'*ODE di ordine* $n$:
$$
y^{(n)}(t) + a_{n-1}y^{(n-1)}(t) + \dots + a_{1}y'(t) + a_{0}y(t) = b_{0}u(t)
$$
Con le *condizioni iniziali* su $y(0),y'(0),\dots,y^{(n-1)}(0)$.

>[!note] NOTA
>Il *caso più generale* implicherebbe derivate *anche per l'ingresso*.
>Qui vediamo solo questo caso più semplice.

Per *modellare il sistema* dobbiamo innanzitutto *scegliere le variabili di stato*.
Scegliamo ad esempio:
$$
\begin{align*}
x_{1}(t) &:= y(t) \\
x_{2}(t) &:= y'(t) \\
x_{3}(t) &:= y''(t) \\
&\dots \\
x_{n}(t) &:= y^{(n-1)}(t)
\end{align*}
$$
Abbiamo allora:
$$
\begin{align*}
x(t) &= [x_{1}(t), x_{2}(t), \dots, x_{n}(t)]^T \\ \\
x(0) &= [y(0), y'(0), \dots, y^{(n-1)}(0)]^T
\end{align*}
$$
Notiamo che, per $i=1,2\dots,n-1$, vale:
$$
\dot{x}_{i}(t) = x_{i+1}(t)
$$
Mentre il comportamento di $\dot{x}_{n}(t)$ è descritto dall'equazione differenziale vista sopra.
Allora possiamo scrivere il sistema in *forma matriciale* come segue:
$$
\dot{x}(t) = Ax(t) + Bu(t)
$$
Con le matrici $A$ e $B$ seguenti:
$$
\begin{align*}
A = \begin{bmatrix}
0 & 1 & 0 & \dots & 0 \\
0 & 0 & 1 & \dots & 0 \\
0 & 0 & 0 & \ddots & 0 \\
\vdots & \vdots & \vdots & \ddots & 1 \\
-a_{0} & -a_{1} & -a_{2} & \dots & -a_{n-1}
\end{bmatrix} & & B=\begin{bmatrix}
0 \\
0 \\
\vdots \\
0 \\
b_{0}
\end{bmatrix}
\end{align*}
$$
L'**uscita** $y(t)$ è quindi data da:
$$
\begin{align*}
y(t) &= Cx(t) \\ \\
C &= \begin{bmatrix}
1 & 0 & 0 & \dots & 0
\end{bmatrix}
\end{align*}
$$

>[!idea] RAPPRESENTAZIONE IN FORMA STATO
>Si deve *per forza* usare la rappresentazione in *forma di stato* per *effettuare l'analisi* (controllo)?
>
>**NO !** Spesso, infatti, le *singole variabili di stato* potrebbero anche *non avere senso fisico* particolarmente rilevante.
## ESEMPIO AUTOMOBILE (PARTE 1)
Consideriamo il seguente sistema:

![[16_SISO_1.png]]

Il sistema è descritto dalla seguente equazione:
$$
m\ddot{y}(t) = -b\dot{y}(t) + u(t)
$$
Scegliamo le variabili di stato:
$$
\begin{align*}
x_{1}(t) = y(t) & & x_{2}(t) = \dot{y}(t)
\end{align*}
$$
E otteniamo quindi:
$$
\begin{cases}
\dot{x}_{1}(t) = x_{2}(t) \\ \\
\dot{x}_{2}(t) = -\frac{b}{m}x_{2}(t) + \frac{1}{m}u(t)
\end{cases}
$$
Scrivendolo in forma matriciale abbiamo:
$$
\begin{align*}
\dot{x}(t) = Ax(t) + Bu(t) & & y(t) = Cx(t)
\end{align*}
$$
Con:
$$
\begin{align*}
A = \begin{bmatrix}
0 & 1 \\
0 & -\frac{b}{m} 
\end{bmatrix} & & B=\begin{bmatrix}
0 \\
\frac{1}{m}
\end{bmatrix} & & C=\begin{bmatrix}
1 & 0
\end{bmatrix}
\end{align*}
$$

>[!note] NOTA
>Il *polinomio caratteristico* è $p_A(\lambda) = \lambda\left( \lambda+\frac{b}{m} \right)$.
>L'autovalore $\lambda=0$ *non è strettamente negativo*.
>Allora il sistema è **marginalmente stabile**, ma *non BIBS stabile*.
## CONSIDERAZIONI SULLA STABILITA'
Abbiamo visto sopra un *caso semplificato*; in quello più generale compaiono infatti anche le derivate dell'ingresso.
Consideriamo allora:
$$
y^{(n)}(t) + a_{n-1}y^{(n-1)}(t) + \ldots + a_{1}y'(t) + a_{0}y(t) = b_{m}u^{(m)}(t) + \ldots + b_{0}u(t)
$$
Con assegnate condizioni iniziali ed $m\leq n$.

Applichiamo la trasformata di Laplace ad entrambi i membri dell'equazione e ricaviamo $Y(s)$ in funzione delle c.i. e di $U(s)$:
$$
Y(s) = \underbrace{ \frac{\mathcal{P}(s)}{D(s)} }_{ Y_{l}(s) } + \underbrace{ \frac{\mathcal{N}(s)}{D(s)}U(s) }_{ Y_{f}(s) }
$$
In modo del tutto analogo a quanto visto in > [[#MATRICE DI TRASFERIMENTO (INGRESSI - STATI)]] e in > [[#MATRICE DI TRASFERIMENTO (INGRESSI - USCITE)]], valgono i seguenti teoremi:

>[!th] TEOREMA: EVOLUZIONE LIBERA
>1. Il sistema è **asintoticamente stabile** *se e solo se* **tutte le radici** di $D(s)$ hanno **parte reale strettamente negativa**.
>2. Il sistema è **marginalmente stabile** *se e solo se* **tutte le radici** di $D(s)$ hanno **parte reale minore o uguale a zero**, **almeno una ha parte reale nulla** e **tutte le radici sull'asse immaginario sono semplici**.
>3. Il sistema è **instabile** in **tutti gli altri casi**.

>[!th] TEOREMA: EVOLUZIONE FORZATA
>Il sistema è **BIBO - stabile** *se e solo se* **tutti i poli** della *funzione di trasferimento* $G(s)$ hanno **parte reale strettamente negativa**.

>[!note] NOTA
>Siccome $G(s)=\frac{\mathcal{N}(s)}{D(s)}$, i poli sono le *radici* di $D(s)$ **non cancellate** da *fattori comuni con* $\mathcal{N}(s)$.
## ESEMPIO AUTOMOBILE (PARTE 2)
Riprendiamo l'esempio precedente (automobile).
Ricordiamo che il sistema era descritto da:
$$
m\ddot{y}(t) = -b\dot{y}(t) + u(t)
$$
Applichiamo la trasformata di Laplace ad ambo i membri. Innanzitutto:
$$
\begin{align*}
\mathcal{L}[\dot{y}(t)] &= sY(s) - y(0) \\ \\
\mathcal{L}[\ddot{y}(t)] &= s^2Y(s) -sy(0) - \dot{y}(0)
\end{align*}
$$
Allora troviamo:
$$
\begin{align*}
m[s^2Y(s) -sy(0) -\dot{y}(0)] + b[sY(s)-y(0)] = U(s) \\ \\
(ms^2+bs)Y(s) = msy(0) + m\dot{y}(0) + by(0) + U(s)
\end{align*}
$$
E quindi:
$$
Y(s) = \underbrace{ \frac{msy(0) + m\dot{y}(0) + by(0)}{ms^2+bs} }_{ Y_{l}(s) } + \underbrace{ \frac{1}{ms^2+bs}U(s) }_{ Y_{f}(s) }
$$
Per cui la **funzione di trasferimento** (tra ingresso e uscita) è:
$$
G(s) = \frac{1}{s(ms+b)}
$$
I cui poli sono:
$$
\begin{align*}
s_{1} = -\frac{b}{m} < 0  & & s_{2} = 0
\end{align*} 
$$

>[!note] NOTA
>Il polo $s_{2}$ *non è strettamente negativo*, per cui il sistema è *solo* **marginalmente stabile**. 
>
>Inoltre il sistema *non è BIBO stabile*: del resto una *forza costante limitata* $u(t)$ determina una *velocità di regime costante* (per cui $\dot{y}(t)$ costante e limitata e $\ddot{y}=0$), il che implica una **posizione** $y(t)$ che **cresce indefinitamente** nel tempo.
## SISTEMI SISO A TEMPO DISCRETO
>[!idea] OSSERVAZIONE IMPORTANTE
>Le considerazioni fatte fin'ora valgono anche per sistemi SISO a **tempo discreto**, con le opportune accortezze.
>
>L'*unica differenza* è data dal fatto che per avere *stabilità asintotica* vogliamo che il **modulo degli autovalori** del sistema sia **strettamente minore di uno**, anzichè valutare il segno della parte reale.
