# INDICE SEZIONE
- [ ] [[#INTRODUZIONE]]
- [ ] [[#CONTROLLO POSIZIONE MOTORE ELETTRICO]]
      - [[#DETERMINAZIONE FDT]]
      - [[#LUOGO DELLE RADICI]]
      - [[#SCELTA DEI POLI]]
- [ ] [[#CONTROLLO VELOCITA' MOTORE ELETTRICO]]
      - [[#SCELTA DELLO ZERO]]
- [ ] [[#RETROAZIONE STATICA DALLO STATO (CENNI)]]
      - [[#CONTROLLO CON RETROAZIONE DALL'USCITA]]
      - [[#CONTROLLO CON RETROAZIONE DALLO STATO]]
# INTRODUZIONE
>[!idea] OSSERVAZIONE IMPORTANTE
>Possiamo usare il **luogo delle radici** per **progettare il controllore**.
>In particolare, è utile per trattare problemi dove gli *obiettivi del controllo* sono direttamente *esprimibili in termini di posizione nel piano complesso* dei *poli in anello chiuso*.

Può essere usato, per esempio, nelle seguenti situazioni:
1. Quando l*unico requisito* riguarda la *stabilità del sistema*.
2. Quando si *vogliono prescrivere* le *caratteristiche dei poli dominanti*.
# CONTROLLO POSIZIONE MOTORE ELETTRICO
Consideriamo il seguente *motore elettrico*:

```tikz
\usepackage[american]{circuitikz}

\begin{document}
\begin{tikzpicture}[>=latex, scale=1.2]

    % --- Circuito Elettrico di Armatura ---
    % Terminali di ingresso e tensione v(t)
    \draw (0,2) node[ocirc] {} -- (0.5,2);
    \node at (0, 2.3) {$+$};
    \draw (0,0) node[ocirc] {} -- (0.5,0);
    \node at (0, -0.3) {$-$};
    \node at (-0.3, 1) {$v(t)$};

    % Resistenza R e Induttanza L
    \draw (0.5,2) to[R, l=$R$] (2.5,2) to[cute inductor, l=$L$] (4.5,2) -- (5,2) -- (5,1.3);

    % Motore (circolo centrale)
    \draw[thick] (5,1) circle (0.3);
    \node at (4.6, 1.4) {$+$};
    \node at (4.6, 0.6) {$-$};

    % Ritorno verso il terminale negativo
    \draw (5,0.7) -- (5,0) -- (0.5,0);

    % Corrente i(t) (freccia rivolta a sinistra come nel disegno)
    \draw[<-, thick] (2.5,0) -- (2.6,0);
    \node at (2.5, 0.3) {$i(t)$};

    % --- Circuito di Eccitazione (Campo) in alto ---
    \draw (5.2, 3.5) -- (5.2, 2.7) to[cute inductor] (6.8, 2.7) -- (7, 3.5);
    \draw[->] (5.2, 3.3) -- (5.2, 3.0); % Freccia della corrente di campo

    % --- Parte Meccanica ---
    % Albero motore
    \draw[thick] (5.3, 1.1) -- (7.24, 1.1);
    \draw[thick] (5.3, 0.9) -- (7.24, 0.9);

    % Freccia di rotazione e coordinate (theta, theta punto)
    \draw[->, thick] (6.1, 0.4) arc (-70:70:0.4 and 0.6);
    \node at (6.5, 1.7) {$\theta, \dot{\theta}$};

    % Disco di inerzia J
    % Faccia frontale del disco
    \draw[thick] (7.5, 1) ellipse (0.25 and 0.8);
    
\end{tikzpicture}
\end{document}
```

Caratterizzato da:
- $R=0.3\Omega$.
- $L=0.025H$.
- $J=2kg\cdot m^2$.
- $h=0.9994Nm/A$.
- $q_{r}=0.002Nms/rad$.

Consideriamo le seguenti specifiche:

>[!tldr] SPECIFICHE
>Vogliamo:
>1. **Errore a regime nullo**: $e_{\infty}=0$ per $r(t)=\delta_{-1}(t)$.
>2. **Massima sovraelongazione** inferiore a: $M_{p}\leq_{5}\%$.
>3. **Tempo di assestamento**: $T_{s}\leq 6$ secondi.

Scegliamo le seguenti **variabili di stato**:
$$
x(t) = \begin{bmatrix}
x_{1}(t) \\
x_{2}(t) \\
x_{3}(t)
\end{bmatrix} = \begin{bmatrix}
i(t) \\
\dot{\theta}(t) \\
\theta(t)
\end{bmatrix}
$$
Abbiamo quindi:
$$
\begin{cases}
\dot{x}(t) = Ax(t) + Bu(t) \\ \\
y(t) = Cx(t)
\end{cases}
$$
Con:
$$
\begin{align*}
A = \begin{bmatrix}
-\frac{R}{L} & -\frac{h}{L} & 0 \\
\frac{h}{J} & -\frac{q_{r}}{J} & 0 \\
0 & 1 & 0
\end{bmatrix} & & B = \begin{bmatrix}
\frac{1}{L} \\
0 \\
0
\end{bmatrix} & & C = \begin{bmatrix}
0 & 1 & 0
\end{bmatrix}
\end{align*}
$$
>[!idea] OSSERVAZIONE IMPORTANTE
>L'ultima equazione serve solo per *determinare la posizione* (come *integrale della velocità*), banalmente:
>$$ \dot{\theta}(t) = \int_{t_{0}}^t \theta(\tau)d\tau $$

Possiamo allora considerare le *"matrici ridotte"*:
$$
\begin{align*}
A = \begin{bmatrix}
-\frac{R}{L} & -\frac{h}{L} \\
\frac{h}{J} & -\frac{q_{r}}{J} \\
\end{bmatrix} & & B = \begin{bmatrix}
\frac{1}{L} \\
0
\end{bmatrix} & & C = \begin{bmatrix}
0 & 1
\end{bmatrix}
\end{align*}
$$
Del resto, abbiamo:
$$
G_{0}(s) := \frac{\overbrace{ s\Theta(s) }^{\text{TDL di }\dot{\theta}(t)}}{V(s)}
$$
Per cui:
$$
\frac{1}{s}G_{0}(s) = \frac{\Theta(s)}{V(s)}
$$
Cioè per **ricavare la posizione** basta **aggiungere un blocco integratore**:

```tikz
\usepackage{amsmath}
\usetikzlibrary{positioning, arrows.meta}

\begin{document}
\begin{tikzpicture}[
    auto,
    >=stealth, % Stile della punta della freccia
    % Stile per il blocco della funzione di trasferimento
    block/.style={draw, rectangle, minimum height=3em, minimum width=4em, thick},
    % Stile per il blocco integratore
    integral/.style={draw, rectangle, minimum height=3em, minimum width=2.5em, thick},
    % Stile per le linee di connessione
    line/.style={draw, thick, ->}
]

% --- Posizionamento dei nodi ---
\node (input) {};
\node [block, right=1.5cm of input] (G) {$G_0(s)$};
\node [integral, right=2cm of G] (int) {\Large $\int$};
\node [right=1.5cm of int] (output) {};

% --- Disegno delle frecce e delle etichette ---
\draw [line] (input) -- node[above] {$v(t)$} (G);
\draw [line] (G) -- node[above] {$\dot{\theta}(t)$} (int);
\draw [line] (int) -- node[above] {$\theta(t)$} (output);

\end{tikzpicture}
\end{document}
```

## DETERMINAZIONE FDT
Schematizziamo allora il sistema di controllo come segue:

```tikz
\usepackage{amsmath}
\usetikzlibrary{positioning, arrows.meta, calc}

\begin{document}
\begin{tikzpicture}[
    auto,
    >=stealth, % Stile della punta della freccia
    % Stili per i blocchi e il nodo sommatore
    block/.style={draw, rectangle, minimum height=3em, minimum width=3.5em, thick},
    integral/.style={draw, rectangle, minimum height=3em, minimum width=2.5em, thick},
    sum/.style={draw, circle, minimum size=1.5em, inner sep=0pt, thick},
    % Stile per le linee di connessione
    line/.style={draw, thick, ->}
]

% --- Posizionamento dei nodi principali ---
\node (input) {};
\node [sum, right=1.2cm of input] (sum) {};
\node [block, right=1.5cm of sum] (C) {$C(s)$};
\node [block, right=2cm of C] (G) {$G_0(s)$};
\node [integral, right=1.5cm of G] (int) {\Large $\int$};
\node [right=2.5cm of int] (output) {};

% --- Disegno del ramo diretto e delle etichette ---
\draw [line] (input) -- node[above, near start] {$r(t)$} node[above, near end] {$+$} (sum);
\draw [line] (sum) -- node[above] {$e(t)$} (C);
\draw [line] (C) -- node[above] {$u(t)=v(t)$} (G);
\draw [line] (G) -- node[above] {$\dot{\theta}(t)$} (int);

% Punto di diramazione in uscita all'integratore
\coordinate (branch) at ($(int.east) + (0.8cm,0)$);
\draw [line] (int) -- (branch) -- node[above] {$\theta(t)=y(t)$} (output);
\fill (branch) circle (1.5pt); % Pallino nero per la diramazione

% --- Disegno del ramo di retroazione (feedback) ---
\draw [line] (branch) -- ++(0,-1.5cm) -| node[left, very near end] {$-$} (sum.south);

\end{tikzpicture}
\end{document}
```

Sappiamo:
$$
G_{0}(s) = \frac{s\Theta(s)}{U(s)} = C(sI-A)^{-1}B
$$
La matrice $sI-A$ è:
$$
sI-A = \begin{bmatrix}
s + \frac{R}{L} & \frac{h}{L} \\
-\frac{h}{J} & s + \frac{q_{r}}{J}
\end{bmatrix}
$$
Per cui l'inversa risulta essere:
$$
(sI-A)^{-1} = \frac{1}{\Delta} \begin{bmatrix}
s + \frac{q_{r}}{J} & - \frac{h}{L} \\
\frac{h}{J} & s + \frac{R}{L}
\end{bmatrix}
$$
Con:
$$
\Delta = \left( s+\frac{R}{L} \right)\left( s+\frac{q_{r}}{J} \right) + \frac{h^2}{JL}
$$
E troviamo infine:
$$
C(sI-A)^{-1}B = \frac{1}{\Delta} \frac{h}{JL}
$$
Per cui:
$$
\begin{align*}
G_{0}(s) &= \frac{h}{JL} \frac{1}{s^2 + \left( \frac{q_{r}}{J} + \frac{R}{L} \right)s + \frac{h^2}{JL} + \frac{Rq_{r}}{JL}} \\ \\
&\approx \frac{20}{s^2+12s+20} \\ \\
&= \frac{20}{(s+2)(s+20)}
\end{align*}
$$
Abbiamo:
$$
L(s) = C(s)G_{0}(s)\frac{1}{s}
$$
>[!note] NOTA
>Siccome la **funzione d'anello** $L(s)$ contiene un **polo nell'origine**, il sistema in retroazione unitaria è di **tipo 1**.
>Se l'anello chiuso è stabile, l'**errore a regime** per riferimento costante è **nullo** e sarà quindi **sufficiente** utilizzare un **controllore** di tipo **proporzionale**.
## LUOGO DELLE RADICI
Abbiamo:
$$
G(s) = \frac{G_{0}(s)}{s} = \frac{20}{s(s+2)(s+10)}
$$
Consideriamo per comodità:
$$
G_{1}(s) := \frac{1}{s(s+2)(s+10)} = \frac{1}{s^3+12s^2+20s}
$$
Per cui avremo $L(s)=kG(s)=k_{1}G_{1}(s)$ con $k_{1}:=20k$.
Ricaviamo:
$$
W_{1}(s) =  \frac{k_{1}G_{1}(s)}{1+k_{1}G_{1}(s)}
$$
E possiamo ora studiare il **luogo delle radici** a partire dal polinomio:
$$
p_{k_{1}}(s) = s^3 + 12s^2 + 20s + k_{1}
$$
Abbiamo:
1. **Tre poli** ($p_{i}=\{ 0,-2,-10 \}$) e **nessuno zero**.
2. **Tre rami**, tutti **tendenti a infinito** data l'assenza di zeri.
3. **Centro** della stella in $\alpha=(0-2-10)/3=-4$.
4. **Angoli** tra gli **asintoti** pari a $2\pi/3$.

Determiniamo **eventuali punti doppi**:
$$
\begin{cases}
s^3 + 12s^2 + 20s + k_{1} = 0 \\ \\
3s^2 + 24s + 20 = 0
\end{cases}
$$
Dalla seconda equazione ricaviamo $s_{1,2}\approx \{ -7,-0.94 \}$.
Per $s=-7$ troviamo $k_{1}<0$, che appartiene al *luogo negativo* e quindi lo scartiamo.
Per $s=-0.94$ troviamo invece $k_{1}^*\approx 9.03$, che è **accettabile**.

Il luogo delle radici risulta quindi essere il seguente:

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}[scale = 1.2]
\begin{axis}[
    width=8.5cm,
    height=9.5cm,
    xmin=-15, xmax=6,
    ymin=-15, ymax=15,
    xlabel={Real Axis (seconds$^{-1}$)},
    ylabel={Imaginary Axis (seconds$^{-1}$)},
    axis background/.style={fill=white},
    xtick={-15,-10,-5,0,5},
    ytick={-15,-10,-5,0,5,10,15},
    tick align=inside,
    tick style={color=black},
    label style={font=\small},
    tick label style={font=\footnotesize},
    enlargelimits=false
]

% --- Assi centrali (origine) punteggiati ---
\draw[dotted, thick, gray!80] (axis cs:-15,0) -- (axis cs:6,0);
\draw[dotted, thick, gray!80] (axis cs:0,-15) -- (axis cs:0,15);

% --- Asintoti (centro in s = -4, angoli +- 60 gradi) ---
% Ramo asintotico superiore
\addplot [dashed, thick, gray, domain=-4:5, samples=2] {sqrt(3)*(x+4)};
% Ramo asintotico inferiore
\addplot [dashed, thick, gray, domain=-4:5, samples=2] {-sqrt(3)*(x+4)};
% Ramo asintotico sull'asse reale
\draw[dashed, thick, gray] (axis cs:-4,0) -- (axis cs:-15,0);

% --- Luogo delle radici sull'asse reale ---
% Ramo rosso da -10 verso -inf
\draw[red, thick] (axis cs:-10,0) -- (axis cs:-15,0);
% Ramo verde da 0 a -0.94 (punto di diramazione)
\draw[green!65!black, thick] (axis cs:0,0) -- (axis cs:-0.945,0);
% Ramo blu da -2 a -0.94 (punto di diramazione)
\draw[blue, thick] (axis cs:-2,0) -- (axis cs:-0.945,0);

% --- Rami complessi del Luogo delle radici ---
% Equazione esatta del luogo: 3x^2 - y^2 + 24x + 20 = 0 -> x = -4 + sqrt(y^2/3 + 28/3)
% Ramo superiore (blu)
\addplot [blue, thick, domain=0:15, samples=150, variable=\y] ({-4 + sqrt(\y^2/3 + 28/3)}, {\y});
% Ramo inferiore (verde)
\addplot [green!65!black, thick, domain=-15:0, samples=150, variable=\y] ({-4 + sqrt(\y^2/3 + 28/3)}, {\y});

% --- Poli a ciclo aperto (X marker) ---
\addplot [
    only marks, 
    mark=x, 
    mark options={scale=1.5, thick}, 
    color=cyan!70!blue
] coordinates {(0,0) (-2,0) (-10,0)};

% --- Etichette testuali sull'asse reale ---
\node[below, inner sep=4pt] at (axis cs:-10,0) {$-10$};
\node[below left, inner sep=2pt, xshift=-2pt] at (axis cs:-4,0) {$-4$};
\node[below left, inner sep=2pt, xshift=-2pt] at (axis cs:-2,0) {$-2$};

% --- Etichetta del punto di diramazione (breakaway point) ---
\draw[thin, black] (axis cs:-0.945, 0) -- (axis cs:0.2, 1.2) 
    node[right, inner sep=1pt, fill=white, fill opacity=0.8, text opacity=1] {$-0.94$};

\end{axis}
\end{tikzpicture}
\end{document}
```
## SCELTA DEI POLI
Per **scegliere i poli** determiniamo innanzitutto i punti di **attraversamento dell'asse immaginario**, usando il criterio di Routh:
$$
\begin{matrix}
3 & | & 1 & 20 \\
2 & | & 12 & k_{1} \\
1 & | & \frac{240-k_{1}}{12} & 0 \\
0 & | & k_{1}
\end{matrix}
$$
Siccome consideriamo $k_{1}>0$, l'unico termine che potrebbe essere negativo è il primo della riga 1.

Capiamo quindi che il sistema è **stabile** se:
$$
0<k_{1}<240
$$
Cioè se:
$$
0 < k = \frac{k_{1}}{20} < 12
$$
Possiamo ora **scegliere la posizione dei poli**, ricordando i vincoli dati dalle specifiche.
Notiamo innanzitutto che:
$$
\begin{align*}
p_{3} \leq -10 & & p_{1,2} = \sigma \pm j\omega = -\omega_{n}\cos \varphi \pm j\omega_{n}\sin \varphi
\end{align*}
$$
Volevamo $M_{p}\leq 0.05$, per cui:
$$
\frac{1}{\sqrt{ 2 }} \leq \xi \leq 1
$$
Ma $\xi=\cos \varphi$, per cui troviamo (volendo mantenere i poli sul *limite* della *regione imposta dalle specifiche*):
$$
\cos \varphi = \sin \varphi = \frac{1}{\sqrt{ 2 }}
$$
Per cui:
$$
p_{1,2} = -\frac{\omega_{n}}{\sqrt{ 2 }} \pm j \frac{\omega_{n}}{\sqrt{ 2 }}
$$
Allora:
$$
(s-p_{1})(s-p_{2}) = s^2 + \sqrt{ 2 }\omega_{n}s + \omega_{n}^2
$$
E sostituendo in $p_{k_{1}}(s)$:
$$
\begin{align*}
s^3 + 12s^2 + 20s + k_{1} = p_{k_{1}}(s) &= (s-p_{3})(s^2 + \sqrt{ 2 }\omega_{n}s + \omega_{n}^2) \\ \\
&= s^3 + (\sqrt{ 2 }\omega_{n}-p_{3})s^2 + (\omega_{n}^2-\sqrt{ 2 }\omega_{n}p_{3})s - \omega_{n}^2p_{3}
\end{align*}
$$
Eguagliando i coefficienti ricaviamo le seguenti equazioni
$$
\begin{cases}
\sqrt{ 2 }\omega_{n} - p_{3} = 12 \\ \\
\omega_{n}^2 - \sqrt{ 2 }\omega_{n}p_{3} = 20 \\ \\
-\omega_{n}^2p_{3} = k_{1}
\end{cases}
$$
Troviamo allora:
$$
\begin{align*}
\omega_{n} \approx 1.2743 & & p_{3} \approx -10.198 & & k_{1} \approx 16.56
\end{align*}
$$
Per tali valori dei parametri, troviamo le seguenti **posizioni dei poli**:
$$
\begin{align*}
p_{1} = -0.9 + j 0.9 & & p_{2} = -0.9 - j 0.9 & & p_{3} = -10.198
\end{align*}
$$
>[!note] NOTA
>Notiamo che sia $p_{3}$ che $k_{1}$ rispettano i vincoli trovati sopra ($p_{3}< -10$ e $k_{1}<240$).
>Inoltre abbiamo $\sigma\leq-\frac{3}{T_{s}}=-0.5$ per tutti e tre i poli, quindi anche questo requisito è soddisfatto.

Troviamo quindi:
$$
k = \frac{k_{1}}{20} \approx 0.83
$$
E la *risposta del sistema* risulta essere:

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}
\usepackage{amsmath}

% Colore blu tipico dei grafici di MATLAB
\definecolor{matlabblue}{RGB}{0,114,189}

\begin{document}
\begin{tikzpicture}
\begin{axis}[
    width=10cm,
    height=7cm,
    xmin=0, xmax=10,
    ymin=0, ymax=1.2,
    xlabel={Time (seconds)},
    ylabel={Amplitude},
    xtick={0,1,2,3,4,5,6,7,8,9,10},
    ytick={0,0.2,0.4,0.6,0.8,1.0,1.2},
    grid=major,
    major grid style={line width=0.5pt, draw=gray!40},
    tick align=inside,
    enlargelimits=false,
    clip=false % Permette di posizionare nodi all'esterno del riquadro degli assi
]

% Curva della risposta al gradino
% La funzione matematica è calcolata per rispettare i parametri di picco e overshoot forniti
\addplot [
    domain=0:10,
    samples=200,
    line width=1.5pt,
    color=matlabblue
] {1 - exp(-0.877*x) * (cos(deg(0.878*x)) + sin(deg(0.878*x)))};

% Bande di assestamento (linee tratteggiate a +- 5%)
\addplot [dashed, thick, black, domain=0:10] {1.05};
\addplot [dashed, thick, black, domain=0:10] {0.95};

% Etichetta r(t) posizionata a sinistra dell'asse Y, all'altezza di 1
\node[left, font=\large] at (axis cs:-0.8, 1) {$r(t)$};

% Riquadro di testo con le caratteristiche della risposta (stile terminale)
\node[
    anchor=south east,
    align=right,
    font=\ttfamily\scriptsize,
    fill=white,       % Sfondo bianco
    fill opacity=0.9, % Leggermente trasparente
    text opacity=1,
    inner sep=4pt
] at (axis cs:9.5, 0.05) {
        RiseTime: 1.6965\\
   TransientTime: 2.3980\\
    SettlingTime: 2.3980\\
     SettlingMin: 0.9046\\
     SettlingMax: 1.0433\\
       Overshoot: 4.3252\\
      Undershoot: 0\\
            Peak: 1.0433\\
        PeakTime: 3.5789
};

\end{axis}
\end{tikzpicture}
\end{document}
```

>[!idea] OSSERVAZIONE IMPORTANTE
>Abbiamo voluto *posizionare* $p_{1}$ e $p_{2}$ *sulle rette* che delimitano la regione ammessa.
>Tuttavia potevamo notare che il **punto doppio** determinato durante il tracciamento del luogo **soddisfa le specifiche**, per cui avremmo potuto scegliere di posizionare $p_{1}$ e $p_{2}$ li, evitando i conti successivi.

In tal caso avremmo avuto $k\approx 0.45$ e la risposta del sistema sarebbe stata:

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}
\usepackage{amsmath}

% Colore blu tipico dei grafici di MATLAB
\definecolor{matlabblue}{RGB}{0,114,189}

\begin{document}
\begin{tikzpicture}
\begin{axis}[
    width=10cm,
    height=7cm,
    xmin=0, xmax=15,
    ymin=0, ymax=1.1, % Il grafico si ferma poco sopra l'1
    xlabel={Time (seconds)},
    ylabel={Amplitude},
    xtick={0,5,10,15},
    ytick={0,0.2,0.4,0.6,0.8,1.0},
    grid=major,
    major grid style={line width=0.5pt, draw=gray!40},
    tick align=inside,
    enlargelimits=false,
    clip=false % Permette di posizionare nodi (come r(t)) all'esterno degli assi
]

% Curva della risposta al gradino
% Modellata come un sistema criticamente smorzato per non avere overshoot
% (adattata per avere un rise time di ~3.57s)
\addplot [
    domain=0:15,
    samples=200,
    line width=1.5pt,
    color=matlabblue
] {1 - exp(-1.1*x) * (1 + 1.1*x)};

% Bande di tolleranza (linee tratteggiate a +- 5%)
\addplot [dashed, thick, black!60, domain=0:15] {1.05};
\addplot [dashed, thick, black!60, domain=0:15] {0.95};

% Etichetta r(t) posizionata a sinistra dell'asse Y, all'altezza di 1
\node[left, font=\large] at (axis cs:-0.8, 1) {$r(t)$};

% Riquadro di testo con le caratteristiche della risposta
\node[
    anchor=south east,
    align=right,
    font=\ttfamily\scriptsize,
    fill=white,       % Sfondo bianco
    fill opacity=0.9, % Leggermente trasparente per far intravedere la griglia
    text opacity=1,
    inner sep=4pt
] at (axis cs:14.5, 0.05) {
        RiseTime: 3.5768\\
   TransientTime: 5.1442\\
    SettlingTime: 5.1442\\
     SettlingMin: 0.9006\\
     SettlingMax: 0.9989\\
       Overshoot: 0\\
      Undershoot: 0\\
            Peak: 0.9989\\
        PeakTime: 9.7770
};

\end{axis}
\end{tikzpicture}
\end{document}
```
# CONTROLLO VELOCITA' MOTORE ELETTRICO
Consideriamo di nuovo lo *stesso sistema di prima*, ma immaginiamo ora di **voler controllare la velocità del motore**.
Allora la FdT in questo caso coincide proprio con $G_{0}$, essendo l'uscita $\dot{\theta}(t)$:
$$
G_{0}(s) = \frac{s\Theta(s)}{U(s)} = \frac{20}{(s+2)(s+10)}
$$
>[!note] NOTA
>Per soddisfare la specifica dell'errore nullo a regime dobbiamo **rendere di tipo 1 il sistema**.
>Dovendo **introdurre un polo nell'origine** dobbiamo utilizzare un **controllore PI**.

Abbiamo allora:
$$
\begin{align*}
C(s) &= k_{p} + \frac{k_{i}}{s} = \frac{k_{p}s +k_{i}}{s} = k_{p} \frac{s + k_{i}/k_{p}}{s} \\ \\
&= k_{p} \frac{s+z_{1}}{s}
\end{align*}
$$
Per cui:
$$
L(s) = C(s)G(s) = k_{p}(s+z_{1}) \underbrace{ \frac{20}{s(s+2)(s+10)} }_{ := G_{1}(s) }
$$
Definiamo per comodità $G_{1}(s)$ e $G(s)$:
$$
G_{2}(s) := (s+z_{1})\frac{1}{s^3 +12s^2 +20s}
$$
E definiamo anche $k_{2}:=20k_{p}$.
Abbiamo così:
$$
L(s) = k_{2}G_{2}(s) = k_{2}(s+z_{1})G_{1}(s)
$$
>[!idea] OSSERVAZIONE IMPORTANTE
>Il LDR si **modificherà** rispetto a prima per la **presenza dello zero** a numeratore.
## SCELTA DELLO ZERO
Dobbiamo ora **scegliere la posizione dello zero** introdotto dal controllore PI.

>[!idea] IDEA
>Posizioniamo lo zero del PI **tra l'origine** del piano $s$ ed il **polo dominante** (*più lento*) del sistema.
>**NOTA**: è una strategia *efficace* perchè *attira il LDR verso una regione desiderata*.

Tale soluzione offre un *compromesso tra rapidità della risposta e sovraelongazione*:
- Se lo zero è **troppo vicino all'origine** otteniamo una *risposta più veloce*, ma *maggiore overshoot*.
- Se lo zero è **troppo vicino al polo dominante** otteniamo una *risposta più lenta* ma con *overshoot più contenuto*.
- Collocandolo a **metà strada** si ottiene un *buon compromesso*.

Scegliamo per esempio $z_{1}=1$ (per cui $k_{p}=k_{d}$).
Il polinomio per lo studio del LDR risulta essere:
$$
\begin{align*}
p_{k_{2}} &= s(s+2)(s+10) + k_{2}(s+1) \\ \\
&= s^3 + 12s^2 + (20+k_{2})s + k_{2}
\end{align*}
$$

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}[scale = 1.2]
\begin{axis}[
    width=8.5cm,
    height=9.5cm,
    xmin=-15, xmax=6,
    ymin=-15, ymax=15,
    xlabel={Real Axis (seconds$^{-1}$)},
    ylabel={Imaginary Axis (seconds$^{-1}$)},
    axis background/.style={fill=white},
    xtick={-15,-10,-5,0,5},
    ytick={-15,-10,-5,0,5,10,15},
    tick align=inside,
    tick style={color=black},
    label style={font=\small},
    tick label style={font=\footnotesize},
    enlargelimits=false
]

% --- Assi centrali (origine) punteggiati ---
\draw[dotted, thick, gray!80] (axis cs:-15,0) -- (axis cs:6,0);
\draw[dotted, thick, gray!80] (axis cs:0,-15) -- (axis cs:0,15);

% --- Asintoti (centro in s = -5.5, angoli +- 90 gradi) ---
% Baricentro degli asintoti: (-2 -10 - (-1)) / 2 = -5.5
\draw[dashed, thick, gray] (axis cs:-5.5, -15) -- (axis cs:-5.5, 15);

% --- Luogo delle radici sull'asse reale ---
% Ramo rosso da 0 verso lo zero in -1
\draw[red, thick] (axis cs:0,0) -- (axis cs:-1,0);
% Ramo verde da -10 verso il punto di diramazione in -5.78
\draw[green!65!black, thick] (axis cs:-10,0) -- (axis cs:-5.78,0);
% Ramo blu da -2 verso il punto di diramazione in -5.78
\draw[blue, thick] (axis cs:-2,0) -- (axis cs:-5.78,0);

% --- Rami complessi del Luogo delle radici ---
% I rami si staccano in -5.78 e tendono all'asintoto verticale in -5.5
% Ramo superiore (blu)
\draw[blue, thick] (axis cs:-5.78, 0) 
    .. controls (axis cs:-5.78, 4) and (axis cs:-5.5, 8) .. (axis cs:-5.5, 15);
% Ramo inferiore (verde)
\draw[green!65!black, thick] (axis cs:-5.78, 0) 
    .. controls (axis cs:-5.78, -4) and (axis cs:-5.5, -8) .. (axis cs:-5.5, -15);


% --- Poli a ciclo aperto (X marker) ---
\addplot [
    only marks, 
    mark=x, 
    mark options={scale=1.5, thick}, 
    color=cyan!70!blue
] coordinates {(0,0) (-2,0) (-10,0)};

% --- Zero a ciclo aperto (O marker) ---
\addplot [
    only marks, 
    mark=o, 
    mark options={scale=1.3, thick}, 
    color=cyan!70!blue
] coordinates {(-1,0)};

% --- Etichette testuali sull'asse reale ---
\node[below, inner sep=4pt] at (axis cs:-10,0) {$-10$};
\node[below, inner sep=4pt] at (axis cs:-2,0) {$-2$};
\node[above, inner sep=4pt] at (axis cs:-1,0) {$-1$};

\end{axis}
\end{tikzpicture}
\end{document}
```

La specifica sul *tempo di assestamento* imponeva $\sigma\leq-0.5$. Siccome lo **zero attrae il polo dominante**, possiamo fissare quest'ultimo proprio in $s=-0.5$.
Per cui deve risultare:
$$
\begin{align*}
p_{k_{2}}(-0.5) &= 0 \\ \\
-7.125 + 0.5k_{2} &= 0
\end{align*}
$$
E troviamo:
$$
k_{2} = \frac{7.125}{0.5} = 14.25
$$
Da cui:
$$
k_{p} = k_{d} = \frac{k_{2}}{20} \approx 0.7
$$
Per tali parametri del controllore otteniamo la seguente risposta:

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}
\usepackage{amsmath}

% Definizione del colore blu tipico di MATLAB
\definecolor{matlabblue}{RGB}{0,114,189}

\begin{document}
\begin{tikzpicture}
\begin{axis}[
    width=10cm,
    height=7cm,
    xmin=0, xmax=15,
    ymin=0, ymax=1.1, % Il grafico si ferma poco sopra 1
    xlabel={Time (seconds)},
    ylabel={Amplitude},
    xtick={0,5,10,15},
    ytick={0,0.2,0.4,0.6,0.8,1.0},
    grid=major,
    major grid style={line width=0.5pt, draw=gray!40},
    tick align=inside,
    enlargelimits=false,
    clip=false % Permette di inserire nodi testuali all'esterno del box degli assi
]

% Curva della risposta al gradino
% Funzione matematica modellata per riprodurre RiseTime ~3.55 e SettlingTime ~5.11
\addplot [
    domain=0:15,
    samples=200,
    line width=1.5pt,
    color=matlabblue
] {1 - exp(-0.93*x) * (1 + 0.93*x)};

% Bande di tolleranza (linee tratteggiate orizzontali al +- 5%)
\addplot [dashed, thick, black!80, domain=0:15] {1.05};
\addplot [dashed, thick, black!80, domain=0:15] {0.95};

% Etichetta r(t) posizionata a sinistra dell'asse Y all'altezza di 1
\node[left, font=\large] at (axis cs:-0.4, 1) {$r(t)$};

% Riquadro di testo con le caratteristiche della risposta
\node[
    anchor=south east,
    align=right,
    font=\ttfamily\scriptsize,
    fill=white,       % Sfondo bianco
    fill opacity=0.9, % Leggera trasparenza per la griglia
    text opacity=1,
    inner sep=4pt
] at (axis cs:14.5, 0.05) {
        RiseTime: 3.5521\\
   TransientTime: 5.1102\\
    SettlingTime: 5.1102\\
     SettlingMin: 0.9002\\
     SettlingMax: 0.9986\\
       Overshoot: 0\\
      Undershoot: 0\\
            Peak: 0.9986\\
        PeakTime: 12.2845
};

\end{axis}
\end{tikzpicture}
\end{document}
```
# RETROAZIONE STATICA DALLO STATO (CENNI)
Consideriamo il seguente problema:

>[!tldr] CONTROLLO POSIZIONE VEICOLO
>Vogliamo **raggiungere/mantenere una posizione** $p$ *desiderata* per un veicolo *nonostante la presenza di disturbi esterni*.

Abbiamo quindi le seguenti *equazioni del moto*:
$$
\begin{cases}
m\ddot{p}(t) = u(t) - b\dot{p}(t) \\ \\
y(t) = p(t)
\end{cases}
$$
Per ottenere il **modello in spazio di stato** scegliamo:
$$
\begin{align*}
x_{1}(t) := p(t) & & x_{2}(t) = \dot{p}(t) & & x(t) = \begin{bmatrix}
x_{1}(t) \\
x_{2}(t)
\end{bmatrix}
\end{align*}
$$
Per cui le equazioni del modello sono:
$$
\begin{cases}
\dot{x}_{1}(t) = x_{2}(t) \\ \\
\dot{x}_{2}(t) = \frac{1}{m}(-bx_{2}(t)) + u(t)
\end{cases}
$$
Che scriviamo nella forma usuale:
$$
\begin{cases}
\dot{x}(t) = Ax(t) + Bu(t) \\ \\
y(t) = Cx(t)
\end{cases}
$$
Con:
$$
\begin{align*}A = \begin{bmatrix}
0 & 1 \\
0 & -\frac{b}{m}
\end{bmatrix} & & B = \begin{bmatrix}
0 \\
\frac{1}{m}
\end{bmatrix} & & C = \begin{bmatrix}
1 & 0
\end{bmatrix}
\end{align*}
$$
## CONTROLLO CON RETROAZIONE DALL'USCITA
Consideriamo il classico sistema di controllo con **retroazione unitaria dall'uscita**:

```tikz
\usepackage{amsmath}
\usetikzlibrary{positioning, arrows.meta, calc}

\begin{document}
\begin{tikzpicture}[
    auto,
    >=stealth, % Stile frecce
    thick,
    % Stile per ricalcare il grigio scuro dell'immagine originale
    every path/.style={draw=gray!80!black},
    every node/.style={text=black}, 
    % Stili dei blocchi e del nodo sommatore
    block/.style={rectangle, draw=gray!80!black, minimum height=3.5em, minimum width=4.5em},
    sum/.style={circle, draw=gray!80!black, minimum size=1.5em, inner sep=0pt}
]

% --- Posizionamento dei nodi principali ---
\coordinate (start) at (0,0);
\node [sum, right=1.5cm of start] (sum) {};
\node [block, right=1.5cm of sum] (kp) {\Large $C(s)$};
\node [block, right=1.5cm of kp] (G) {\Large $G(s)$};

% Diramazione per l'uscita
\coordinate [right=1.5cm of G.east] (branch);
\node [right=0.8cm of branch] (end) {$y(t)$};

% --- Connessioni ramo diretto ---
\draw [->] (start) node[above] {$r(t)$} -- node[above, near end] {$+$} (sum);
\draw [->] (sum) -- node[above] {$e(t)$} (kp);
\draw [->] (kp) -- node[above] {$u(t)$} (G);
\draw [->] (G) -- (branch) -- (end);

% Pallino di diramazione
\fill[gray!80!black] (branch) circle (1.5pt);

% --- Connessioni ramo di retroazione ---
\draw [->] (branch) -- ++(0,-1.8) -| node[left, near end] {$-$} (sum.south);

\end{tikzpicture}
\end{document}
```

La funzione di trasferimento *del sistema* $G$ da controllare è:
$$
G(s) = \frac{Y(s)}{U(s)} = C(sI-A)^{-1}B
$$
Abbiamo:
$$
sI-A = \begin{bmatrix}
s & -1 \\
0 & s+\frac{b}{m}
\end{bmatrix}
$$
Per cui:
$$
(sI-A)^{-1} = \frac{1}{s\left( s+\frac{b}{m} \right)} \begin{bmatrix}
s+\frac{b}{m} & 1 \\
0 & s
\end{bmatrix}
$$
E troviamo quindi:
$$
G(s) = \begin{bmatrix}
1 & 0
\end{bmatrix} \cdot \left( \frac{1}{s\left( s+\frac{b}{m} \right)} \begin{bmatrix}
s+\frac{b}{m} & 1 \\
0 & s
\end{bmatrix} \right) \cdot \begin{bmatrix}
0 \\
\frac{1}{m}
\end{bmatrix} = \frac{1}{ms\left( s+\frac{b}{m} \right)}
$$
Scegliamo di utilizzare un **controllore proporzionale**.
Abbiamo allora:
$$
L(s) = k_{p}G(s) = \frac{k_{p}}{m} \frac{1}{s^2 + \frac{b}{m}s}
$$
Definiamo $k:=k_{p}/m$.
Allora:
$$
W(s) = \frac{k \frac{1}{s^2 + \frac{b}{m}s}}{1 + k \frac{1}{s^2 + \frac{b}{m}s}}
$$
>[!idea] OSSERVAZIONE IMPORTANTE
>Un sistema di *controllo proporzionale* sull'uscita sa **solo quanto l'uscita sia lontana dal riferimento**, ma **non sa nulla sullo stato del sistema**.
## CONTROLLO CON RETROAZIONE DALLO STATO
Alla luce dell'osservazione precedente, osserviamo che:

>[!idea] OSSERVAZIONE IMPORTANTE
>Se il controllore disponesse di un'*informazione più ricca*, che **include le variabili di stato** del sistema, potremmo effettuare un'**azione di controllo più precisa**.

Consideriamo le solite equazioni per gli **stati** e le **uscite**:
$$
\begin{cases}
\dot{x}(t) = Ax(t) + Bu(t) \\ \\
y(t) = Cx(t)
\end{cases}
$$
Implementiamo il seguente sistema di controllo:

```tikz
\usepackage{amsmath, amssymb}
\usetikzlibrary{shapes,arrows.meta,positioning,calc}

\begin{document}
\begin{tikzpicture}[
    auto,
    % Stili per i blocchi e i nodi sommatore
    block/.style = {draw, fill=gray!35, rectangle, minimum height=3em, minimum width=2.5em, thick},
    sum/.style = {draw, fill=gray!35, circle, minimum size=1.5em, inner sep=0pt, thick},
    % Stili per le frecce (normali e marcate per i vettori di stato)
    >=LaTeX,
    line/.style = {draw, thick, -LaTeX},
    thickline/.style = {draw, line width=1.8pt, -LaTeX}
]

% --- Posizionamento dei nodi principali (Ramo Diretto) ---
\node [coordinate] (start) {};
\node [block, right=1cm of start] (Kr) {$\boldsymbol{Kr}$};
\node [sum, right=1.2cm of Kr] (sum1) {};
\node [block, right=1.2cm of sum1] (B) {$B$};
\node [sum, right=1.2cm of B] (sum2) {};
\node [block, right=1.2cm of sum2] (int) {$1/s$};
\node [block, right=3cm of int] (C) {$C$};
\node [coordinate, right=1.5cm of C] (end) {};

% --- Coordinate per le diramazioni della variabile x ---
% b1 e b2 sono piazzati al 33% e al 75% del percorso tra l'integratore e C
\path (int.east) -- coordinate[pos=0.33] (b1) coordinate[pos=0.75] (b2) (C.west);

% --- Posizionamento dei nodi di Feedback ---
\node [block, below=1.2cm of int] (A) {$A$};
\node [block, below=1.2cm of A] (K) {$K$};

% --- Disegno delle connessioni (Ramo Diretto) ---
\draw [line] (start) -- node[above] {$r$} (Kr);
\draw [line] (Kr) -- node[above, very near end] {$+$} (sum1);
\draw [line] (sum1) -- node[above] {$u$} (B);
\draw [thickline] (B) -- node[above, very near end] {$+$} (sum2);
\draw [thickline] (sum2) -- node[above] {$\dot{\mathbf{x}}$} (int);
\draw [thickline] (int.east) -- node[above] {$\mathbf{x}$} (C.west);
\draw [line] (C) -- node[above] {$y$} (end);

% --- Disegno delle connessioni (Feedback) ---
% Diramazione interna (matrice A)
\draw [thickline] (b1) -- (b1 |- A.east) -- (A.east);
\draw [thickline] (A.west) -| node[right, very near end] {$+$} (sum2.south);

% Diramazione esterna (matrice K)
\draw [thickline] (b2) -- (b2 |- K.east) -- (K.east);
\draw [line] (K.west) -| node[right, very near end] {$-$} (sum1.south);

\end{tikzpicture}
\end{document}
```

>[!note] NOTA: RUOLO DEI BLOCCHI
>Diamo un significato alle matrici che compaiono nello schema a blocchi:
>1. **A** e **B** sono legate al **modello nello spazio di stato**.
>2. **C** è la matrice che **seleziona gli stati rilevanti per l'uscita**.
>3. **K** è il **controllore**.
>4. **Kr** è il parametro di **guadagno**.

Abbiamo quindi:
$$
u(t) := -Kx(t) + K_{r}r(t) 
$$
E l'equazione per gli stati è quindi:
$$
\begin{align*}
\dot{x}(t) &= Ax(t) + Bu(t) \\ \\
&= Ax(t) + B(-Kx(t)+K_{r}r(t)) \\ \\
&= [A-BK]x(t) + BK_{r}r(t)
\end{align*}
$$
>[!note] NOTE DIO CAN
>Sistemi raggiungibili ... boh yap yap... matrici compagne (sia A, che B, che A-BK).

Per realizzare il controllore, **allochiamo i poli** (e quindi gli *autovalori*) della matrice $A-BK$ in **maniera adeguata** (per esempio, assicurando *stabilità*) per il **sistema a catena chiusa**.

Per ottenere **errore nullo a regime** rispetto a un riferimento costante, si impone la **condizione di equilibrio** seguente:
$$
\begin{align*}
0 = (A-BK)x_{\infty} + BK_{r}r & & y_{\infty} = Cx_{\infty} = r
\end{align*}
$$
Da cui ricaviamo:
$$
K_{r} = -[C(A-BK)^{-1}B]^{-1}
$$
>[!note] NOTA
>Esistono **algoritmi** che, date la matrici $A$ e $B$ ed il *riferimento desiderato* **restituiscono i poli** di $A-BK$ ed il **parametro** $K_{r}$ (formula di *Ackermann*).

Per il sistema del nostro esempio, si allocano i poli in $s=-0.2$ e $s=-0.3$, si ricava $K=[60, 450]$ e si calcola $K_{r}=60$.

```tikz
\usepackage{amsmath}
\usetikzlibrary{shapes.geometric, arrows.meta, positioning, calc}

\begin{document}
\begin{tikzpicture}[
    >={LaTeX[scale=1.1]}, % Stile frecce Simulink
    auto, 
    node distance=1.5cm,
    % Stili dei vari blocchi
    block/.style={draw, rectangle, minimum height=2.5em, minimum width=2.5em, fill=white, semithick},
    sum/.style={draw, circle, minimum size=2em, inner sep=0pt, fill=white, semithick},
    % Triangolo rivolto verso destra
    gain/.style={draw, regular polygon, regular polygon sides=3, shape border rotate=-90, minimum size=2.5em, inner sep=0pt, fill=white, semithick},
    % Triangolo rivolto verso sinistra per i feedback
    gainback/.style={draw, regular polygon, regular polygon sides=3, shape border rotate=90, minimum size=2.5em, inner sep=0pt, fill=white, semithick},
    % Barra di Demux
    demux/.style={fill=black, minimum width=4pt, minimum height=4em, inner sep=0pt},
    % Blocco Scope con oscilloscopio
    scope/.style={draw, rectangle, minimum width=3em, minimum height=2.5em, fill=white, semithick}
]

% --- Posizionamento dei nodi ---

% Ramo diretto
\node [block] (input) {1};
\node [gain, right=0.8cm of input] (gain_in) {60};
\node [sum, right=1cm of gain_in] (sum) {};

% Segni del nodo sommatore (posizionati manualmente all'interno del cerchio)
\node at ($(sum.center) + (-0.5em, 0.4em)$) {\tiny $+$};
\node at ($(sum.center) + (-0.4em, -0.4em)$) {\tiny $-$};
\node at ($(sum.center) + (0.5em, -0.4em)$) {\tiny $-$};

% Blocco Spazio di Stato
\node [block, right=2cm of sum, align=left, inner xsep=1em, inner ysep=0.8em] (ss) {$\dot{x} = Ax + Bu$ \\[1ex] $y = Cx$};

% Demux
\node [demux, right=1.2cm of ss] (demux) {};
\coordinate (out_top) at ($(demux.east) + (0, 0.8em)$);
\coordinate (out_bot) at ($(demux.east) + (0, -0.8em)$);

% Punto di diramazione e Scope
\coordinate (branch) at ($(out_top) + (0.8cm, 0)$);
\node [scope, right=0.5cm of branch, anchor=west] (scope) {};

% Disegno manuale dello schermetto interno allo Scope
\draw [thin] ([shift={(3pt,3pt)}]scope.south west) rectangle ([shift={(-3pt,-3pt)}]scope.north east);
\draw [thin] ([shift={(6pt,10pt)}]scope.south west) -- ++(4pt,0) -- ++(0,6pt) -- ++(8pt,0); % Grafico a gradino

% Blocchi di retroazione (posizionati in basso rispetto al blocco SS)
\node [gainback] (gain450) at ($(ss.south east) + (-0.5cm, -1.2cm)$) {450};
\node [gainback] (gain60)  at ($(ss.south) + (-1cm, -2.5cm)$) {60};


% --- Disegno delle connessioni ---

% Ramo di ingresso
\draw [->] (input) -- (gain_in);
\draw [->] (gain_in.east) -- ++(0.3cm, 0) |- (sum.150);

% Collegamenti centrali
\draw [->] (sum) -- (ss);
\draw [->] (ss.east) -- (demux.west);

% Uscita Demux superiore a Scope
\draw [->] (out_top) -- (scope.west);
\fill (branch) circle (1.5pt); % Pallino di connessione

% Diramazione superiore verso il feedback 60
\draw [->] (branch) |- (gain60.east);

% Uscita Demux inferiore verso il feedback 450
\draw [->] (out_bot) -- ++(0.3cm,0) |- (gain450.east);

% Ritorno dal guadagno 450 al sommatore (entra in basso a destra)
\draw [->] (gain450.west) -| ($(sum.315) + (0.4cm, -0.5cm)$) -- (sum.315);

% Ritorno dal guadagno 60 al sommatore (entra in basso a sinistra)
\draw [->] (gain60.west) -| ($(sum.225) + (-0.4cm, -1cm)$) -- (sum.225);

\end{tikzpicture}
\end{document}
```

Vediamo di seguito il *confronto* fra le risposte nel caso della *retroazione dall'uscita* e della *retroazione dallo stato*:

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}

% Definizione dei colori classici di Matplotlib per una riproduzione fedele
\definecolor{mplblue}{RGB}{31,119,180}
\definecolor{mplorange}{RGB}{255,127,14}

\begin{document}
\begin{tikzpicture}
\begin{axis}[
    width=12cm,
    height=7cm,
    xlabel={Tempo [s]},
    ylabel={Posizione y(t) [m]},
    xmin=0, xmax=300,
    ymin=0, ymax=1.1,
    xtick={0,50,100,150,200,250,300},
    ytick={0,0.2,0.4,0.6,0.8,1.0},
    grid=major,
    major grid style={line width=0.2pt, draw=gray!30},
    legend pos=south east,
    legend style={font=\footnotesize, draw=gray!40, fill=white, legend cell align=left},
    tick align=inside,
    enlargelimits=false
]

% Curva Blu: P su uscita 
% Modellata con una risposta al gradino di un sistema del 2° ordine sottosmorzato
\addplot [
    domain=0:300,
    samples=200,
    line width=1.2pt,
    color=mplblue
] {1 - exp(-0.026*x) * (cos(deg(0.02*x)) + 1.333*sin(deg(0.02*x)))};
\addlegendentry{P su uscita}

% Curva Arancione: feedback stato
% Modellata con una risposta al gradino molto rapida e criticamente smorzata
\addplot [
    domain=0:300,
    samples=150,
    line width=1.2pt,
    color=mplorange
] {1 - (1 + 0.25*x) * exp(-0.25*x)};
\addlegendentry{feedback stato}

% Curva Tratteggiata: riferimento r(t)=1
\addplot [
    domain=0:300,
    dashed,
    color=mplblue,
    line width=0.6pt,
    opacity=0.8
] {1};
\addlegendentry{r(t)=1}

\end{axis}
\end{tikzpicture}
\end{document}
```

>[!idea] EFFETTO DELLA RETROAZIONE DALLO STATO
>La **retroazione dallo stato** considera sia **dove siamo**, sia **come ci stiamo muovendo**.
>Quest'*informazione più completa* ci permette di **prevedere il comportamento futuro** del sistema e di **controllarlo in modo più efficace**.
>
>**NOTA**: Spesso il controllo con retroazione dallo stato risulta **più costoso** perchè **necessita di tanti sensori** quante sono le **variabili di stato**. Esistono comunque dei casi in cui tale tecnica è implementabile *"sfruttando in modo intelligente le informazioni a disposizione"* (per esempio, *calcolando* determinati stati a partire da altri anzichè impiegare un sensore).
>
