# INDICE SEZIONE
- [ ] [[#INTRODUZIONE]]
      - [[#GUADAGNO STATICO]]
      - [[#CONNESSIONE IN SERIE DI SISTEMI LTI]]
      - [[#CONNESSIONE IN PARALLELO DI SISTEMI LTI]]
      - [[#PROBLEMA DEL CONTROLLO]]
- [ ] [[#CONTROLLO IN CATENA APERTA (OPEN LOOP)]]
      - [[#ESEMPI CA]]
      - [[#CONTROLLO IN CA IN PRESENZA DI INCERTEZZA]]
- [ ] [[#CONTROLLO IN RETROAZIONE (FEEDBACK)]]
      - [[#CONTROLLO PROPORZIONALE]]
      - [[#ANALISI IN FREQUENZA]]
      - [[#ANALISI DELL'ERRORE]]
      - [[#RUOLO DEL FEEDBACK IN PRESENZA DI DISTURBI]]
      - [[#RUOLO DEL FEEDBACK IN PRESENZA DI INCERTEZZA]]
      - [[#LIMITI E COMPROMESSI]]
      - [[#CASO CANCELLAZIONI FRA ZERI E POLI]]
# INTRODUZIONE
Ci siamo fin'ora occupati di *modellare* i sistemi dinamici e di *analizzarli*.

Ora, invece, vogliamo iniziare a capire **come controllare** i sistemi in modo che raggiungano uno **stato** (o **uscita**) **desiderato**.

>[!note] NOTA
>In > [[00 - INTRODUZIONE]] avevamo accennato alla differenza tra *problema diretto* e *problema inverso*.
>- Il problema **diretto** corrisponde all'**analisi** del sistema.
>- Il problema **inverso** corrisponde alla **sintesi del controllo** del sistema.

Per questo tipo di studio, è di fondamentale importanza la **matrice di trasferimento** tra *ingressi e uscite*.
Ricordiamo che nel dominio della trasformata di Laplace abbiamo:
$$
Y(s) = G(s)U(s)
$$
Dove:
- $Y(s)$ è l'**uscita**.
- $U(s)$ è l'**ingresso**.
- $G(s)$ è la **matrice di trasferimento**.

>[!idea] OSSERVAZIONE IMPORTANTE
>Se l'ingresso (nel dominio del tempo) è l'**impulso di Dirac** $\delta(t)$, abbiamo che $U(s)=1$.
>Notiamo quindi che la **matrice di trasferimento** è la **trasformata di Laplace della risposta impulsiva**.
## GUADAGNO STATICO
Nel caso in cui l'ingresso sia costante, risulta utile definire il *guadagno statico* del sistema:

>[!def] GUADAGNO STATICO
>Dato un sistema *asintoticamente stabile* con funzione di trasferimento $G(s)$ *priva di poli nell'origine*, si definisce **guadagno statico** $K$ il *rapporto* tra la *variazione di uscita a regime* e la *variazione dell'ingresso costante*.

Cioè abbiamo:
$$
K = \frac{\Delta y(\infty)}{\Delta r}
$$
Consideriamo per esempio un sistema con f.d.t. $G(s)=\frac{1}{s+2}$ e un ingresso dato dal gradino unitario.
In questo caso:

```tikz
\usepackage{pgfplots}
\usepackage{amsmath}

% Imposta la compatibilità per pgfplots per usare le coordinate standard direttamente
\pgfplotsset{compat=1.16}
\usetikzlibrary{positioning, arrows.meta, calc, shapes.geometric}

% Definizione del colore blu tipico dei grafici
\definecolor{matlabblue}{HTML}{0072BD}

\begin{document}
\begin{tikzpicture}[scale = 0.8]

    % ===============================
    % GRAFICO SINISTRO: Reference
    % ===============================
    \begin{axis}[
        name=plot_ref,
        width=7cm, height=6.5cm,
        title={\textbf{Reference}},
        xlabel={Time(seconds)},
        ylabel={Amplitude},
        xmin=0, xmax=5,
        ymin=0, ymax=2,
        xtick={0,0.5,...,5},
        ytick={0,0.2,...,2},
        grid=both,
        major grid style={solid, draw=black!15},
        tick label style={font=\tiny},
        label style={font=\scriptsize},
        title style={font=\scriptsize\sffamily},
        % Evita che i bordi del grafico taglino le frecce o le linee
        clip=false
    ]
        % Gradino
        \draw[matlabblue, very thick] (0,0) -- (0,1) -- (5,1);

        % Etichetta della funzione
        \node[above] at (1.5, 1.05) {\Large $\delta_{-1}(t)$};

        % Freccia per Delta r
        \draw[{Stealth[scale=1.2]}-{Stealth[scale=1.2]}, matlabblue, line width=1.2pt] 
            (4,0) -- (4,1) node[midway, left, text=black] {\Large $\Delta r$};
    \end{axis}

    % ===============================
    % GRAFICO DESTRO: Step Response
    % ===============================
    \begin{axis}[
        name=plot_resp,
        at={(plot_ref.east)}, xshift=8cm, anchor=west, % Posizionato a destra del primo grafico
        width=7cm, height=6.5cm,
        title={\textbf{Step Response}},
        xlabel={Time (seconds)},
        xmin=0, xmax=5,
        ymin=0, ymax=1,
        xtick={0,0.5,...,5},
        ytick={0,0.1,...,1},
        grid=both,
        major grid style={solid, draw=black!15},
        tick label style={font=\tiny},
        label style={font=\scriptsize},
        title style={font=\scriptsize\sffamily},
        clip=false
    ]
        % Curva di risposta al gradino
        \addplot[domain=0:5, samples=100, matlabblue, very thick] {0.5*(1 - exp(-2*x))};

        % Asintoto orizzontale
        \draw[dotted, thick] (0,0.5) -- (5,0.5);

        % Etichetta della funzione
        \node[above] at (2.5, 0.55) {\large $y(t) = 0.5(1 - e^{-2t})\delta_{-1}(t)$};

        % Freccia per Delta y(inf)
        \draw[{Stealth[scale=1.2]}-{Stealth[scale=1.2]}, matlabblue, line width=1.2pt] 
            (4,0) -- (4,0.5) node[midway, left, text=black] {\Large $\Delta y(\infty)$};
    \end{axis}

    % ===============================
    % SEZIONE CENTRALE: Schema a blocchi
    % ===============================
    
    % Blocco del sistema (posizionato a metà tra i due grafici)
    \node[draw=gray!80, fill=gray!20, line width=1.5pt, minimum width=2.5cm, minimum height=1.5cm, inner sep=10pt]
        (system) at ($(plot_ref.east)!0.5!(plot_resp.west)$) {\Large $G(s) = \frac{1}{s+2}$};

    % Freccia in ingresso (u(t))
    \draw[-{Stealth[scale=1.5]}, gray!80, line width=1.5pt] 
        ($(system.west)-(1.5cm,0)$) -- (system.west);

    % Freccia in uscita (y(t))
    \draw[-{Stealth[scale=1.5]}, gray!80, line width=1.5pt] 
        (system.east) -- ($(system.east)+(1.5cm,0)$);

    % Titolo in alto "Esempio:"
    \node[above=1.5cm of system, font=\Large\sffamily] {Esempio:};

\end{tikzpicture}
\end{document}
```

Abbiamo quindi:
$$
K = \frac{\Delta y(\infty)}{\Delta r} = \frac{0.5}{1} = 0.5
$$
Osserviamo inoltre:
$$
u(t) = u_{0} \implies U(s) = \frac{u_{0}}{s} \implies Y(s) = G(s) \frac{u_{0}}{s}
$$
Usando il *teorema del valore finale* abbiamo:
$$
y(\infty) )= \lim_{ s \to 0 } sY(s) = \lim_{ s \to 0 } sG(s) \frac{u_{0}}{s} = u_{0}G(0)
$$
Per cui:
$$
K = \frac{y(\infty)}{u_{0}} = G(0)
$$
>[!def] GUADAGNO GENERALIZZATO
>Se $G(s)$ ha $b$ *poli nell'origine* si definisce il **guadagno generalizzato**:
>$$ K_{g} = \lim_{ s \to 0 } s^bG(s) $$ 
## CONNESSIONE IN SERIE DI SISTEMI LTI
Un possibile modo per *connettere* due sistemi è la **connessione in serie**:

```tikz
\usetikzlibrary{positioning, arrows.meta}

\begin{document}
\begin{tikzpicture}[
    % Definizione dello stile globale per le frecce
    >={Stealth[scale=1.2]},
    % Definizione dello stile per i blocchi
    block/.style={
        draw, 
        thick, 
        rectangle, 
        minimum height=1.2cm, 
        minimum width=1.8cm, 
        font=\Large
    }
]

    % Creazione dei blocchi G1 e G2
    \node[block] (G1) {$G_1(s)$};
    \node[block, right=2.5cm of G1] (G2) {$G_2(s)$};

    % Definizione dei punti di inizio (ingresso) e fine (uscita)
    \coordinate (input) at ([xshift=-2.2cm]G1.west);
    \coordinate (output) at ([xshift=2.2cm]G2.east);

    % Disegno delle linee di connessione con le relative etichette
    \draw[->, thick] (input) -- (G1.west) node[midway, above, font=\Large] {$U=U_1$};
    \draw[->, thick] (G1.east) -- (G2.west) node[midway, above, font=\Large] {$Y_1=U_2$};
    \draw[->, thick] (G2.east) -- (output) node[midway, above, font=\Large] {$Y_2=Y$};

\end{tikzpicture}
\end{document}
```

Calcoliamo l'uscita *complessiva* del sistema *"intero"* (cioè $Y_{2}$):
$$
\begin{align*}
Y(s) &= Y(s) = G_{2}(s)U_{2}(s) = G_{2}(s)Y_{1}(s) \\ \\
&= G_{2}(s)G_{1}(s)U_{1}(s) \\ \\
&= G_{2}(s)G_{1}(s)U(s)
\end{align*}
$$
Per cui abbiamo che la **matrice di trasferimento** per la **connessione in serie** è:
$$
G_{s}(s) = G_{2}(s)G_{1}(s)
$$
Consideriamo per esempio due sistemi le cui *matrici di trasferimento* sono:
$$
\begin{align*}
G_{1}(s) = \frac{1}{s+1} & & G_{2}(s) = \frac{1}{5s+1}
\end{align*}
$$
Abbiamo allora:
$$
G_{s} = \frac{1}{(5s+1)(s+1)}
$$
Il **guadagno statico** della serie è:
$$
G_{s}(0) = G_{1}(0)G_{2}(0) = 1
$$

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}
\definecolor{matlabblue}{RGB}{0,114,189}
\definecolor{matlaborange}{RGB}{217,83,25}

\begin{document}
\begin{tikzpicture}
\begin{axis}[
    width=10cm,
    height=9cm,
    xmin=-1, xmax=0,
    ymin=-1, ymax=1,
    xtick pos=both,
    ytick pos=both,
    xtick={-1, -0.9, -0.8, -0.7, -0.6, -0.5, -0.4, -0.3, -0.2, -0.1, 0},
    ytick={-1, -0.8, -0.6, -0.4, -0.2, 0, 0.2, 0.4, 0.6, 0.8, 1},
    xticklabel style={/pgf/number format/fixed, /pgf/number format/precision=1},
    yticklabel style={/pgf/number format/fixed, /pgf/number format/precision=1},
    xlabel={$\Re(s)$},
    ylabel={$\Im(s)$},
    tick align=inside,
    xticklabel pos=bottom,
    yticklabel pos=left,
    axis line style={thin},
    axis background/.style={fill=white},
    clip marker paths=false
]

% 1. Griglia frequenza naturale (omega_n)
\pgfplotsinvokeforeach{0.2, 0.4, 0.6, 0.8, 1.0}{
    \addplot [forget plot, domain=90:270, samples=60, gray, thick, dotted] ({#1*cos(x)}, {#1*sin(x)});
}

% 2. Griglia coefficiente di smorzamento (zeta)
\pgfplotsinvokeforeach{0, 0.36, 0.75, 1.2, 1.73, 2.43, 3.42, 5.79, 12.46}{
    \draw[gray, thick, dotted] (0,0) -- (-1, #1);
    \draw[gray, thick, dotted] (0,0) -- (-1, -#1);
}

% Etichetta asse Y destro
\node[rotate=90, anchor=south] at (rel axis cs:1.05,0.5) {Imaginary Axis (seconds$^{-1}$)};

% 4. Etichette Zeta (Sopra)
\node[fill=white, inner sep=1pt, font=\tiny] at (-0.92, 0.35) {0.94};
\node[fill=white, inner sep=1pt, font=\tiny] at (-0.93, 0.75) {0.8};
\node[fill=white, inner sep=1pt, font=\tiny] at (-0.81, 0.95) {0.64};
\node[fill=white, inner sep=1pt, font=\tiny] at (-0.55, 0.95) {0.5};
\node[fill=white, inner sep=1pt, font=\tiny] at (-0.41, 0.95) {0.38};
\node[fill=white, inner sep=1pt, font=\tiny] at (-0.29, 0.95) {0.28};
\node[fill=white, inner sep=1pt, font=\tiny] at (-0.17, 0.95) {0.17};
\node[fill=white, inner sep=1pt, font=\tiny] at (-0.08, 0.95) {0.08};

% 5. Marcatori Poli
\addplot [only marks, mark=x, mark options={scale=2.5, very thick, line width=2pt}, color=matlabblue] coordinates {(-1,0)};
\addplot [only marks, mark=x, mark options={scale=2.5, very thick, line width=2pt}, color=matlaborange] coordinates {(-0.2,0)};

\end{axis}
\end{tikzpicture}
\end{document}
```

>[!idea] OSSERVAZIONE IMPORTANTE
>Il *polo* di $G_{2}$ ($s_{2}=-\frac{1}{5}$) è **più vicino all'asse immaginario** rispetto al polo di $G_{1}$ ($s_{1}=-1$) e per questo **domina il transitorio** del sistema complessivo.
>
>**NOTA**: Ricordiamo infatti che avevamo definito la **costante di tempo** associata a un modo (e quindi ad un polo nel dominio $s$) come $\tau=-\frac{1}{\lambda}$.

Questo fatto è evidenziato anche dall'*andamento nel tempo delle componenti del sistema*:

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}

\begin{document}
\begin{tikzpicture}
\begin{axis}[
    width=10cm,
    height=8cm,
    xlabel={Tempo (secondi)},
    ylabel={Ampiezza},
    xmin=0, xmax=30,
    ymin=0, ymax=1,
    xtick={0,5,10,15,20,25,30},
    ytick={0,0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1},
    grid=major,
    major grid style={solid, draw=black!15},
    legend pos=north east,
    legend cell align={left},
    legend style={
        fill=white,
        draw=black,
        nodes={scale=0.9, transform shape}
    },
    tick align=inside,
    enlargelimits=false,
    axis on top
]

% Colori standard di MATLAB
\definecolor{matlabblue}{HTML}{0072BD}
\definecolor{matlaborange}{HTML}{D95319}

% Risposta G1 (tau = 1)
\addplot [
    color=matlabblue,
    solid,
    line width=1.2pt,
    domain=0:30,
    samples=150
]
{1 - exp(-x)};
\addlegendentry{G1}

% Risposta G2 (tau = 5)
\addplot [
    color=matlaborange,
    solid,
    line width=1.2pt,
    domain=0:30,
    samples=150
]
{1 - exp(-x/5)};
\addlegendentry{G2}

% Risposta Gs (G1 in serie con G2)
\addplot [
    color=black!90,
    solid,
    line width=1.2pt,
    domain=0:30,
    samples=150
]
{1 - 1.25*exp(-x/5) + 0.25*exp(-x)};
\addlegendentry{Gs}

\end{axis}
\end{tikzpicture}
\end{document}
```

>[!note] NOTA
>Il **transitorio** di $G_{s}$ è *dominato* dalla **dinamica più lenta** tra i sistemi da cui è ottenuto.
## CONNESSIONE IN PARALLELO DI SISTEMI LTI
E' possibile anche connettere i sistemi **in parallelo**, come in figura:

```tikz
\usepackage{tikz}
\usetikzlibrary{shapes,arrows.meta,positioning,calc}

\begin{document}
\begin{tikzpicture}[
    auto,
    block/.style = {rectangle, draw, thick, minimum width=2.4cm, minimum height=1.4cm, align=center, font=\Large},
    sum/.style = {circle, draw, thick, minimum size=0.6cm, inner sep=0pt},
    branch/.style = {circle, fill, minimum size=4pt, inner sep=0pt},
    >={Stealth[scale=1.1]}
]

% Nodi principali
\coordinate (input) at (-2,0);
\node [branch] (split) at (0,0) {};
\node [block] (G1) at (2.5, 1.5) {$G_1(s)$};
\node [block] (G2) at (2.5,-1.5) {$G_2(s)$};
\node [sum] (sum) at (5.5,0) {};
\coordinate (output) at (7.5,0);

% Ramo di ingresso
\draw [->, thick] (input) -- node[above, font=\Large] {$U$} (-0.5,0);
\draw [thick] (-0.5,0) -- (split);

% Diramazione verso G1 e G2
\draw [->, thick] (split) |- (G1.west);
\draw [->, thick] (split) |- (G2.west);

% Uscita G1 verso nodo sommatore
\draw [thick] (G1.east) -- node[above, font=\Large] {$Y_1$} (G1.east -| sum.north);
\draw [->, thick] (G1.east -| sum.north) -- node[right, font=\large] {$+$} (sum.north);

% Uscita G2 verso nodo sommatore
\draw [thick] (G2.east) -- node[above, font=\Large] {$Y_2$} (G2.east -| sum.south);
\draw [->, thick] (G2.east -| sum.south) -- node[left, font=\large] {$+$} (sum.south);

% Ramo di uscita
\draw [->, thick] (sum.east) -- node[above, font=\Large] {$Y$} (output);

\end{tikzpicture}
\end{document}
```

Calcoliamo l'*uscita complessiva* del sistema $Y(s)$, cioè $Y_{1}(s)+Y_{2}(s)$:
$$
\begin{align*}
Y(s) &= Y_{1}(s) + Y_{2}(s) = G_{1}(s)U(s) + G_{2}(s)U(s) \\ \\
&= [G_{1}(s)+G_{2}(s)]U(s)
\end{align*}
$$
Per cui la **matrice di trasferimento** per la connessione **in parallelo** è:
$$
G_{p}(s) = G_{1}(s) + G_{2}(s)
$$
Consideriamo ancora due sistemi con le stesse funzioni di trasferimento dell'esempio precedente:
$$
\begin{align*}
G_{1}(s) = \frac{1}{s+1} & & G_{2}(s) = \frac{1}{5s+1}
\end{align*}
$$
Abbiamo allora:
$$
G_{p} = \frac{1}{s+1} + \frac{1}{5s+1} = \frac{6s+2}{(s+1)(5s+1)} = 2 \frac{3s+1}{(s+1)(5s+1)}
$$

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}
\definecolor{matlabblue}{RGB}{0,114,189}
\definecolor{matlaborange}{RGB}{217,83,25}

\begin{document}
\begin{tikzpicture}
\begin{axis}[
    width=10cm,
    height=9cm,
    xmin=-1, xmax=0,
    ymin=-1, ymax=1,
    xtick pos=both,
    ytick pos=both,
    xtick={-1, -0.9, -0.8, -0.7, -0.6, -0.5, -0.4, -0.3, -0.2, -0.1, 0},
    ytick={-1, -0.8, -0.6, -0.4, -0.2, 0, 0.2, 0.4, 0.6, 0.8, 1},
    xticklabel style={/pgf/number format/fixed, /pgf/number format/precision=1},
    yticklabel style={/pgf/number format/fixed, /pgf/number format/precision=1},
    xlabel={$\Re(s)$},
    ylabel={$\Im(s)$},
    tick align=inside,
    xticklabel pos=bottom,
    yticklabel pos=left,
    axis line style={thin},
    axis background/.style={fill=white},
    clip marker paths=false
]

% 1. Griglia frequenza naturale (omega_n)
\pgfplotsinvokeforeach{0.2, 0.4, 0.6, 0.8, 1.0}{
    \addplot [forget plot, domain=90:270, samples=60, gray, thick, dotted] ({#1*cos(x)}, {#1*sin(x)});
}

% 2. Griglia coefficiente di smorzamento (zeta)
\pgfplotsinvokeforeach{0, 0.36, 0.75, 1.2, 1.73, 2.43, 3.42, 5.79, 12.46}{
    \draw[gray, thick, dotted] (0,0) -- (-1, #1);
    \draw[gray, thick, dotted] (0,0) -- (-1, -#1);
}

% Etichetta asse Y destro
\node[rotate=90, anchor=south] at (rel axis cs:1.05,0.5) {Imaginary Axis (seconds$^{-1}$)};

% 4. Etichette Zeta (Sopra)
\node[fill=white, inner sep=1pt, font=\tiny] at (-0.92, 0.35) {0.94};
\node[fill=white, inner sep=1pt, font=\tiny] at (-0.93, 0.75) {0.8};
\node[fill=white, inner sep=1pt, font=\tiny] at (-0.81, 0.95) {0.64};
\node[fill=white, inner sep=1pt, font=\tiny] at (-0.55, 0.95) {0.5};
\node[fill=white, inner sep=1pt, font=\tiny] at (-0.41, 0.95) {0.38};
\node[fill=white, inner sep=1pt, font=\tiny] at (-0.29, 0.95) {0.28};
\node[fill=white, inner sep=1pt, font=\tiny] at (-0.17, 0.95) {0.17};
\node[fill=white, inner sep=1pt, font=\tiny] at (-0.08, 0.95) {0.08};

% 5. Marcatori Poli
\addplot [only marks, mark=x, mark options={scale=2.5, very thick, line width=2pt}, color=matlabblue] coordinates {(-1,0)};
\addplot [only marks, mark=x, mark options={scale=2.5, very thick, line width=2pt}, color=matlaborange] coordinates {(-0.2,0)};
\addplot [only marks, mark=o, mark options={scale=2.5, very thick, line width=2pt}, color=red] coordinates {(-0.333,0)};

\end{axis}
\end{tikzpicture}
\end{document}
```

>[!idea] OSSERVAZIONE IMPORTANTE
>Ora, oltre ai *poli*, **compare** anche uno **zero** (sopra rappresentato dal cerchio) per il sistema (in questo caso in $s_{0}=-\frac{1}{3}$).
>Questo **modifica la forma del transitorio** nella *risposta complessiva*.

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}

\begin{document}
\begin{tikzpicture}
\begin{axis}[
    width=10cm,
    height=8cm,
    xlabel={Tempo (secondi)},
    ylabel={Ampiezza},
    xmin=0, xmax=30,
    ymin=0, ymax=2,
    xtick={0,5,10,15,20,25,30},
    ytick={0,0.2,0.4,0.6,0.8,1,1.2,1.4,1.6,1.8,2},
    grid=major,
    major grid style={solid, draw=black!15},
    legend pos=north east,
    legend cell align={left},
    legend style={
        fill=white,
        draw=black,
        nodes={scale=0.9, transform shape}
    },
    tick align=inside,
    enlargelimits=false,
    axis on top
]

% Colori standard di MATLAB
\definecolor{matlabblue}{HTML}{0072BD}
\definecolor{matlaborange}{HTML}{D95319}

% Risposta G1 (tau = 1)
\addplot [
    color=matlabblue,
    solid,
    line width=1.2pt,
    domain=0:30,
    samples=150
]
{1 - exp(-x)};
\addlegendentry{G1}

% Risposta G2 (tau = 5)
\addplot [
    color=matlaborange,
    solid,
    line width=1.2pt,
    domain=0:30,
    samples=150
]
{1 - exp(-x/5)};
\addlegendentry{G2}

% Risposta Gp (G1 in parallelo con G2)
\addplot [
    color=black!90,
    solid,
    line width=1.2pt,
    domain=0:30,
    samples=150
]
{2 - exp(-x/0.5) - exp(-x/5)};
\addlegendentry{Gp}

\end{axis}
\end{tikzpicture}
\end{document}
```

>[!note] NOTA
>La risposta di $G_{p}$ **cresce subito** grazie al *ramo veloce* $G_{1}$, ma l'*avvicinamento al regime* è *dominato dalla dinamica lenta* di $G_{2}$.
## PROBLEMA DEL CONTROLLO
In generale, abbiamo un sistema come in figura:

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta, positioning}

% Definizione dei colori per farli corrispondere all'immagine
\definecolor{blockborder}{RGB}{140,140,140}
\definecolor{blockfill}{RGB}{232,232,232}
\definecolor{arrowgray}{RGB}{160,160,160}
\definecolor{rust}{RGB}{185, 65, 20}

\begin{document}
\begin{tikzpicture}[scale = 1.7, >={Stealth[scale=1.1]}]

% Nodi U, Y e il blocco G(s)
\node (u) at (-2.5, 0) {$u(t)$};
\node[
    draw=blockborder, 
    fill=blockfill, 
    very thick, 
    minimum width=2.2cm, 
    minimum height=1.6cm, 
    label={[font=\small, text=black, yshift=-1mm]above:Sistema}
] (G) at (0, 0) {$G(s)$};
\node (y) at (2.5, 0) {$y(t)$};

% Frecce per segnale di ingresso e uscita
\draw[->, very thick, draw=arrowgray] (u) -- (G);
\draw[->, very thick, draw=arrowgray] (G) -- (y);

% Ramo del disturbo d(t) a forma di saetta
\draw[->, draw=rust, thick, line width=1.1pt] 
    (2.1, 0.9) node[right, text=black, xshift=1pt, yshift=2pt] {$d(t)$} 
    -- (1.3, 0.5) 
    -- (1.7, 0.4) 
    -- (1.2, 0.1);

\end{tikzpicture}
\end{document}
```

Dove:
- $u(t)$ è l'**ingresso**, da *decidere istante per istante*.
- $y(t)$ è l'**uscita**.
- $d(t)$ è un eventuale **disturbo esterno**.

Il problema è *decidere l'ingresso giusto* per raggiungere l'*uscita desiderata*.

>[!def] PROBLEMA DEL CONTROLLO
>Consiste nel *determinare la legge di evoluzione* della *variabile di ingresso* $u(t)$ (detta anche **variabile manipolata**), in modo da **ottenere un comportamento desiderato** per l'uscita $y(t)$ (detta anche **variabile controllata**) di un *sistema dinamico* $G$ (che rappresenta il *processo*, *fenomeno* o *impianto* da *controllare*).

A tal fine, viene assegnato un **riferimento** $r(t)$ per l'uscita che rappresenta l'**obiettivo del controllo**.

>[!idea] OSSERVAZIONE IMPORTANTE
>Ottenere un'*eguaglianza esatta* tra *uscita e riferimento* è *praticamente impossibile*.
>Per questo, si cerca di ottenere una risposta *più simile possibile*, basata su determinate *metriche di valutazione*, che possono tenere conto anche di *limiti temporali e/o di budget*.

In generale, esistono **due categorie principali** di *problemi di controllo*:
1. Problema di **regolazione**: il *riferimento* $r(t)$ è *costante* (es. regolazione temperatura di una stanza a $20°C$).
2. Problema di **asservimento** (**tracking**): il riferimento $r(t)$ *varia nel tempo* (es. auto a guida autonoma che deve seguire la strada).

Esistono inoltre **due modi principali** di **controllare il sistema**:
1. Controllo in **catena aperta**.
2. Controllo in **retroazione**.
# CONTROLLO IN CATENA APERTA (OPEN LOOP)
Una tipologia di sistema di controllo è quello in **catena aperta**.

>[!def] CATENA APERTA
>Il controllore **non riceve** alcuna **informazione** sulle **uscite del sistema**.

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta, positioning}

% Definizione dei colori per farli corrispondere all'immagine
\definecolor{blockborder}{RGB}{140,140,140}
\definecolor{blockfill}{RGB}{232,232,232}
\definecolor{arrowgray}{RGB}{160,160,160}
\definecolor{ctrlblue}{RGB}{0, 114, 189}

\begin{document}
\begin{tikzpicture}[
    >={Stealth[scale=1.1]},
    block/.style={
        rectangle, 
        draw=blockborder, 
        fill=blockfill, 
        very thick, 
        minimum width=2cm, 
        minimum height=1.6cm, 
        align=center,
        font=\Large
    }
]

% Nodi principali (Blocchi)
\node[block, label={[font=\sffamily\color{ctrlblue}, yshift=1mm]above:Controllore}] (C) at (0,0) {\color{ctrlblue}$C(s)$};
\node[block, label={[font=\sffamily, yshift=1mm]above:Sistema}] (G) [right=2.2cm of C] {$G(s)$};

% Nodi di ingresso
\node (r) [left=1cm of C] {\large $r(t)$};
\node (Rtext) [left=0.1cm of r] {\sffamily Riferimento};

% Nodi di uscita
\node (y) [right=1cm of G] {\large $y(t)$};
\node (Ytext) [right=0.1cm of y] {\sffamily Uscita};

% Frecce e connessioni
\draw[->, very thick, draw=arrowgray] (r) -- (C);
\draw[->, very thick, draw=arrowgray] (C) -- 
    node[above, text=black, yshift=1mm] {\large $u(t)$} 
    node[below, font=\sffamily, text=black, yshift=-1mm] {Ingresso} 
    (G);
\draw[->, very thick, draw=arrowgray] (G) -- (y);

\end{tikzpicture}
\end{document}
```

In questo caso abbiamo:
$$
Y(s) = G(s)C(s)R(s)
$$

>[!idea] OBIETTIVO DEL CONTROLLO
>Vogliamo:
>$$ R(s) = Y(s) $$
>Cioè vogliamo che l'**uscita segua il riferimento**.

Ma allora deve valere:
$$
G(s)C(s) = 1 \implies C(s) = G(s)^{-1}
$$
Cioè vogliamo che la *funzione di trasferimento* sia identicamente uguale a uno.

>[!idea] OSSERVAZIONE IMPORTANTE
>Per poter realizzare un sistema di questo tipo, dobbiamo **conoscere molto bene il sistema**.
>Cioè abbiamo bisogno di un **modello esatto**: se $G(s)$ è *strettamente propria* o ha *zeri instabili* l'inversa può risultare *non fisicamente realizzabile* oppure *instabile*.

>[!note] NOTA
>L'osservazione sopra deriva dai seguenti fatti:
>1. Processi **causali** sono descritti da **funzioni razionali proprie**.
>   Se $G(s)$ è *strettamente propria*, allora l'*inversa ha numeratore con grado maggiore del denominatore*; cioè **non è fisicamente realizzabile** (perchè descrive un processo che *"prevede il futuro"*).
>2. Nell'inversa, quelli che erano gli **zeri diventano i poli**. Per cui eventuali *zeri instabili* si traducono in *poli instabili*.
## ESEMPI CA
### ESEMPIO 1
Cominciamo col vedere un semplice esempio.
Consideriamo:
$$
\begin{align*}
r(t) = \delta_{-1}(t) & & d(t) = 0
\end{align*}
$$
E assumiamo che la *funzione di trasferimento* del sistema sia data da:
$$
G(s) = \frac{s+1}{s+2}
$$
Allora dobbiamo costruire il controllore come segue:
$$
C(s) = G^{-1}(s) = \frac{s+2}{s+1}
$$
>[!note] NOTA
>- $G(s)$ aveva un *polo* in $s=-2$ e uno *zero* in $s=-1$.
>- $C(s)$ ha un *polo* in $s=-1$ e uno *zero* in $s=-2$.
### ESEMPIO 2
Consideriamo lo stesso sistema di prima, ma ora anche un **disturbo additivo costante** in uscita ($d(t)=0.1\delta_{-1}(t)$):

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta, positioning, calc}

% Definizione dei colori per farli corrispondere all'immagine
\definecolor{blockborder}{RGB}{140,140,140}
\definecolor{blockfill}{RGB}{232,232,232}
\definecolor{arrowgray}{RGB}{160,160,160}
\definecolor{rust}{RGB}{185, 65, 20}

\begin{document}
\begin{tikzpicture}[
    >={Stealth[scale=1.1]},
    block/.style={
        rectangle, 
        draw=blockborder, 
        fill=blockfill, 
        very thick, 
        minimum width=2cm, 
        minimum height=1.6cm, 
        align=center,
        font=\Large
    },
    sum/.style={
        circle,
        draw=blockborder,
        very thick,
        minimum size=0.45cm,
        inner sep=0pt
    }
]

% Nodi principali (Blocchi e Segnali)
\node (r) at (-2.2, 0) {\large $r(t)$};
\node[block] (C) at (0, 0) {$C(s)$};
\node[block] (G) at (3.5, 0) {$G(s)$};
\node[sum] (sum) at (5.5, 0) {};
\node (y) at (7.2, 0) {\large $y(t)$};

% Frecce e connessioni percorso principale
\draw[->, very thick, draw=arrowgray] (r) -- (C);
\draw[->, very thick, draw=arrowgray] (C) -- (G);
\draw[->, very thick, draw=arrowgray] (G) -- node[pos=0.7, below=1mm, text=black] {$+$} (sum);
\draw[->, very thick, draw=arrowgray] (sum) -- (y);

% Disturbo d(t) a forma di saetta
\draw[->, draw=rust, thick, line width=1.1pt] 
    (5.7, 1.4) node[above right, text=black, xshift=-1mm, yshift=-1mm] {$d(t)$} 
    -- (5.35, 0.7) 
    -- (5.65, 0.8) 
    -- (sum.north);

% Segno '+' per il disturbo
\node at (5.15, 0.5) {$+$};

\end{tikzpicture}
\end{document}
```

Costruiamo sempre $C(s)$ in modo che $G(s)C(s)=1$.
Allora l'uscita è data da:
$$
y(t) = r(t) + d(t) = \delta_{-1}(t) + 0.1\delta_{-1}(t)
$$
>[!note] NOTA
>In catena aperta, un **disturbo** (ad esempio additivo) **non viene compensato** e *rimane presente in uscita*.
### ESEMPIO 3
Vediamo ora un esempio di **controllore non realizzabile**.
Consideriamo un sistema con la *funzione di trasferimento* seguente:
$$
G(s) = \frac{1}{s+2}
$$
Vorremmo:
$$
C(s) = G^{-1}(s) = s+2
$$
>[!note] NOTA
>$C(s)$ **non è propria**: il grado del numeratore è *maggiore di quello del denominatore*.

Vediamo *perchè* un controllore di questo tipo *non è realizzabile*.
L'*uscita del controllore* è data da:
$$
U_{C}(s) = C(s)R(s)
$$
Che nel nostro caso diventa:
$$
U_{C}(s) = (s+2)R(s) = sR(s) + 2R(s)
$$
Ora, ritornando al *dominio del tempo* abbiamo:
$$
u(t) = \mathcal{L}^{-1}[U_{C}(s)] = \frac{dr(t)}{dt} + 2r(t)
$$
Per cui il controllore dovrebbe *calcolare una derivata*, che per definizione è data da:
$$
\frac{dr(t)}{dt} = \lim_{ \varepsilon \to 0 } \frac{r(t+\varepsilon) - r(t)}{\varepsilon}
$$
>[!idea] OSSERVAZIONE IMPORTANTE
>Il controllore **non è fisicamente realizzabile** perchè per calcolare la derivata è necessario **conoscere il valore futuro** di $r(t)$ (cioè $r(t+\varepsilon)$).
### ESEMPIO 4
Consideriamo infine un sistema la cui funzione di trasferimento è:
$$
G(s) = \frac{s-1}{s+2}
$$
Dovremmo costruire il controllore in modo che:
$$
C(s) = G^{-1}(s) = \frac{s+2}{s-1}
$$
Ma così $C(s)$ avrebbe un *polo a parte reale positiva* in $s=1$, che si tradurrebbe in un termine del tipo $e^{ t }\delta_{-1}(t)$.

>[!note] NOTA
>Lo **zero instabile** del sistema si è tradotto in un **polo instabile** del controllore, che quindi *non è BIBO stabile*.
## CONTROLLO IN CA IN PRESENZA DI INCERTEZZA
>[!idea] OSSERVAZIONE IMPORTANTE
>Negli esempi precedenti abbiamo assunto di *conoscere perfettamente il modello del sistema*, ma nella pratica il **modello del processo non è mai perfettamente noto**.

Indichiamo con $\widehat{G}(s)$ il **modello nominale noto** (modello teorico), il quale differisce dal **processo reale**, che indichiamo con $G(s)$, di un fattore dato dall'*incertezza* $\Delta(s)$.
Abbiamo quindi:
$$
G(s) = \widehat{G}(s)(1+\Delta(s))
$$
Quando progettiamo il sistema di controllo non conosciamo $G(s)$ (reale), per cui realizziamo $C(s)$ tale che:
$$
C(s) = \widehat{G}^{-1}(s)
$$
Per cui la **funzione di trasferimento** risulterà essere:
$$ 
\begin{align*}
C(s)G(s) &= \widehat{G}^{-1}(s)\widehat{G}(s)(1+\Delta(s)) \\ \\
&= 1 + \Delta(s)
\end{align*}
$$
Per cui l'**uscita** del sistema sarà:
$$
Y(s) = R(s) + \Delta(s)R(s)
$$
>[!note] NOTA
>L'**errore del modello** si trasferisce **direttamente sull'uscita**.
# CONTROLLO IN RETROAZIONE (FEEDBACK)
Un'altra tipologia di controllo è data dai sistemi di **controllo in retroazione**, che sono schematizzabili come in figura:
```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta, positioning, calc}

% Definizione dei colori per farli corrispondere all'immagine
\definecolor{blockborder}{RGB}{140,140,140}
\definecolor{blockfill}{RGB}{232,232,232}
\definecolor{arrowgray}{RGB}{160,160,160}
\definecolor{rust}{RGB}{185, 65, 20}
\definecolor{ctrlgreen}{RGB}{0, 160, 80}
\definecolor{feedred}{RGB}{190, 0, 0}

\begin{document}
\begin{tikzpicture}[
    >={Stealth[scale=1.1]},
    block/.style={
        rectangle, 
        draw=blockborder, 
        fill=blockfill, 
        very thick, 
        minimum width=2.4cm, 
        minimum height=1.6cm, 
        align=center,
        font=\Large
    },
    sum/.style={
        circle,
        draw=blockborder,
        very thick,
        minimum size=0.45cm,
        inner sep=0pt
    },
    branch/.style={
        circle,
        fill=arrowgray,
        minimum size=3.5pt,
        inner sep=0pt
    }
]

% Coordinate e Nodi principali
\coordinate (r_start) at (-3, 0);
\node[sum] (sum1) at (-1, 0) {};
\node[block, label={[font=\sffamily, text=ctrlgreen, yshift=-1mm]below:Controllore}] (C) at (2, 0) {$C(s)$};
\node[block] (G) at (6, 0) {$G(s)$};
\node[sum] (sum2) at (8.5, 0) {};
\node[branch] (takeoff) at (9.4, 0) {};
\coordinate (y_end) at (10.6, 0);

% Ramo diretto (frecce orizzontali)
\draw[->, very thick, draw=arrowgray] 
    (r_start) -- node[pos=0.15, above, text=black] {$r(t)$} node[pos=0.8, above=0.5mm, text=black] {$+$} (sum1);
\draw[->, very thick, draw=arrowgray] 
    (sum1) -- node[above, text=black] {$e(t)$} (C);
\draw[->, very thick, draw=arrowgray] 
    (C) -- node[above, text=black] {$u(t)$} (G);
\draw[->, very thick, draw=arrowgray] 
    (G) -- node[pos=0.6, below=1mm, text=black] {$+$} (sum2);
\draw[thick, draw=arrowgray] 
    (sum2) -- (takeoff); % Linea verso la diramazione
\draw[->, very thick, draw=arrowgray] 
    (takeoff) -- (y_end) node[right, text=black] {$y(t)$};

% Ramo di Feedback (retroazione)
\draw[->, very thick, draw=arrowgray] 
    (takeoff) -- (takeoff |- 0, -1.8) 
    -- node[above, text=feedred, font=\sffamily] {feedback} (-1, -1.8) 
    -- node[pos=0.85, left=0.5mm, text=black] {$-$} (sum1.south);

% Disturbo d(t) a forma di saetta
\draw[->, draw=rust, thick, line width=1.1pt] 
    (8.8, 1.5) node[right, text=black, xshift=1mm, yshift=2pt] {$d(t)$} 
    -- (8.3, 0.8) 
    -- (8.6, 0.9) 
    -- (sum2.north);
\node[text=black] at (8.15, 0.6) {$+$};

\end{tikzpicture}
\end{document}
```

>[!note] NOTA (TERMINOLOGIA)
>La serie $C(s)$ - $G(s)$ è detta **catena diretta**, mentre il "percorso" sottostante è detto **anello**.

>[!idea] OSSERVAZIONE IMPORTANTE
>Si tratta di un approccio *più complicato*, perchè **richiede sensori di misura** (almeno dell'uscita $y(t)$).
>Tuttavia, è **più robusto** a *incertezze/disturbi esterni*.

Per semplicità, assumiamo per ora che *non ci siano disturbi* ($d(t)=0$).
Abbiamo allora:
$$
\begin{align*}
Y(s) &= G(s)U(s) = G(s)C(s)E(s) \\ \\
&= G(s)C(s)[R(s)-Y(S)]
\end{align*}
$$
Per cui:
$$
Y(s)[1+G(s)C(s)] = G(s)C(s)R(s)
$$
E troviamo infine:
$$
Y(s) = \frac{G(s)C(s)}{1+G(s)C(s)} R(s)
$$
Per cui definiamo la **funzione di trasferimento**:
$$
W_{r}(s) = \frac{G(s)C(s)}{1+G(s)C(s)} = \frac{L(s)}{1+L(s)}
$$
Dove abbiamo definito:

>[!def] GUADAGNO DI ANELLO
>$$ L(s) := G(s)C(s) $$

>[!idea] OSSERVAZIONE IMPORTANTE
>Siccome ora il controllore **riceve un feedback**, non è necessario costruirlo in modo che la sua FdT sia inversa rispetto a quella del sistema: possiamo **scegliere** noi quale **tipo di controllo** applicare. 
## CONTROLLO PROPORZIONALE
Consideriamo un sistema d'esempio con funzione di trasferimento:
$$
G(s) = \frac{1}{s+1}
$$
E scegliamo di applicare un **controllo** di **tipo proporzionale**.
Scegliamo (per esempio):
$$
C(s) = K = 1
$$
Abbiamo allora:
$$
L(s) = G(s)C(s) = \frac{1}{s+1}
$$
Possiamo quindi calcolare la funzione di trasferimento:
$$
W_{r}(s) = \frac{L(s)}{1+L(s)} = \frac{\frac{1}{s+1}}{1+ \frac{1}{s+1}} = \frac{1}{s+2}
$$
E quindi il **guadagno statico** è:
$$
W_{r}(0) = 0.5
$$
>[!idea] OSSERVAZIONE IMPORTANTE
>Ora il polo si trova in $s=-2$ anzichè in $s=-1$, per cui la *dinamica del sistema* risulta **più veloce**. Questo significa che il sistema, dopo l'applicazione del controllore, *reagisce più velocemente* e questo è un *effetto desiderato*. 
>
>Tuttavia un **guadagno statico** pari a $0.5$ è **terribile**: significa infatti che, dall'*ingresso* all'*uscita* stiamo *perdendo metà dell'ampiezza del segnale*.

Per verificare se possiamo fare meglio, consideriamo ora $K$ generico.
Troviamo in questo caso:
$$
W_{r}(s) = \frac{K}{s+1+K}
$$
>[!note] NOTA
>Notiamo che il **polo del sistema** si trova ora in $-(1+K)$.

Chiamiamo $p_{cl}=-(1+K)$ e vediamo *come si sposta il polo* al *variare di* $K$:

```tikz
\usepackage{tikz}
\usepackage{pgfplots}
\usetikzlibrary{arrows.meta}
\pgfplotsset{compat=1.16}

% Definizione del colore rosso scuro per la freccia
\definecolor{darkred}{RGB}{192, 0, 0}

\begin{document}
\begin{tikzpicture}
\begin{axis}[
    width=12cm, height=6.5cm,
    xmin=-110, xmax=5,
    ymin=-5, ymax=5,
    xtick={-100,-80,-60,-40,-20,0},
    ytick={-4,-2,2,4},
    grid=major,
    major grid style={densely dashed, black!30},
    xlabel={\scriptsize Re\{s\}},
    ticklabel style={font=\scriptsize},
    tick align=inside,
    axis lines=box,
    enlargelimits=false
]

% Asse reale (orizzontale) e immaginario (verticale) passanti per l'origine
\draw [black, thin] (axis cs:-110,0) -- (axis cs:5,0);
\draw [black, thin] (axis cs:0,-5) -- (axis cs:0,5);

% Etichetta dell'asse immaginario (Im{s}) ruotata
\node[rotate=90, font=\scriptsize, anchor=south] at (axis cs:-1, 3.5) {Im\{s\}};

% Posizionamento dei poli
\addplot [only marks, mark=x, mark size=4pt, thick, black] coordinates {
    (-2,0)
    (-11,0)
    (-101,0)
};

% Etichette dei valori dei poli
\node[above, font=\scriptsize] at (axis cs:-2, 0.2) {-2};
\node[above, font=\scriptsize] at (axis cs:-11, 0.2) {-11};
\node[above, font=\scriptsize] at (axis cs:-101, 0.2) {-101};

% Freccia rossa spessa per indicare la direzione
\draw [
    -{Triangle[length=2mm, width=2.7mm]}, 
    color=darkred, 
    line width=1.5mm
] (axis cs:-4, -1.3) -- (axis cs:-95, -1.3);

\end{axis}
\end{tikzpicture}
\end{document}
```

Al variare di $K$ abbiamo:
$$
\begin{matrix}
K   & | & p_{cl} & | & W_{r}(0) \\
1   & | & -2     & | & 0.5 \\
10  & | & -11    & | & \approx 0.91 \\
100 & | & -101   & | & \approx 0.99
\end{matrix}
$$
>[!note] NOTA
>All'aumentare di $K$, $p_{cl}$ si *sposta verso sinistra*.
>Per cui:
>1. Il **sistema è più veloce**, perchè la costante di tempo diminuisce.
>2. Il **guadagno statico tende a uno** e quindi l'uscita si *avvicina al riferimento desiderato*.
## ANALISI IN FREQUENZA
L'analisi fatta sopra, da sola, è sufficiente a comprendere a fondo il sistema.
Per avere una *visione completa*, dobbiamo effettuare anche un'**analisi della risposta in frequenza**, che avevamo accennato in > [[13 - TDL E ANALISI DEI SISTEMI LTI#RISPOSTA IN FREQUENZA]].

Vediamo allora cosa succede, sempre considerando valori di $K$ "elevati", per esempio $K=100$.
Abbiamo:
$$
\begin{align*}
L(s) &= G(s)C(s) = \frac{100}{s+1} \\ \\
W_{r}(s) &= \frac{L(s)}{1+L(s)} = \frac{100}{s+101}
\end{align*}
$$
Per effettuare l'analisi in frequenza dobbiamo considerare $s=j\omega$.
Per cui il **modulo del guadagno di anello** è dato da:
$$
|L(j\omega)| = \frac{|100|}{|1+j\omega|} = \frac{100}{\sqrt{ 1+\omega^2 }}
$$
>[!note] NOTA
>Se consideriamo *pulsazioni* $\omega$ (*frequenze*) **basse**, il modulo del **guadagno di anello** è **elevato**.

In tal caso abbiamo infatti:
$$
|L(j\omega)| \approx 100 \gg 1  
$$
E quindi segue che:
$$
|W_{r}(j\omega)| \approx \frac{100}{101} \approx 1
$$
Ma $Y(s)=W_{r}(s)R(S)$, per cui:

>[!idea] OSSERVAZIONE IMPORTANTE
>1. A **basse frequenze** l'**uscita** $y(t)$ **insegue** molto bene **il riferimento** $r(t)$.
>2. Per segnali di ingresso ad **alte frequenze**, invece, il **modulo del guadagno di anello diminuisce** (e così anche $|W_{r}|$) e quindi $y(t)$ inseguirà peggio $r(t)$.

L'osservazione è infatti confermata dai diagrammi di Bode seguenti:

```tikz
\usepackage{tikz}
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}
\usetikzlibrary{arrows.meta}

% Colori standard di Matplotlib (tab10)
\definecolor{mplblue}{HTML}{1F77B4}
\definecolor{mplorange}{HTML}{FF7F0E}

\begin{document}
\begin{tikzpicture}
\begin{axis}[
    width=14cm, height=8cm,
    xmode=log,
    xmin=5e-3, xmax=2e4,
    ymin=-44, ymax=44,
    xlabel={Frequenza $\omega$ [rad/s]},
    ylabel={Modulo [dB]},
    ytick={-40,-30,-20,-10,0,10,20,30,40},
    grid=both,
    major grid style={densely dotted, black!30},
    minor grid style={densely dotted, black!15},
    legend pos=north east,
    legend cell align=left,
    legend style={font=\scriptsize, fill=white, fill opacity=0.9},
    tick align=inside,
    axis lines=box,
    enlargelimits=false
]

% 1. Curva del modulo di L(s) (Blu)
% Formula: 20 * log10( 100 / sqrt(w^2 + 1) )
\addplot[
    domain=1e-2:1e4,
    samples=200,
    color=mplblue,
    thick
] {20*log10(100/sqrt(x^2 + 1))};
\addlegendentry{$|L(j\omega)|$}

% 2. Curva del modulo di Wr(s) (Arancione)
% Formula: 20 * log10( 100 / sqrt(w^2 + 101^2) )
% 101^2 = 10201
\addplot[
    domain=1e-2:1e4,
    samples=200,
    color=mplorange,
    thick
] {20*log10(100/sqrt(x^2 + 10201))};
\addlegendentry{$|W_r(j\omega)|$}

% 3. Linea orizzontale a 0 dB
\addplot[
    domain=5e-3:2e4,
    color=mplblue,
    densely dashed,
    semithick
] {0};
\addlegendentry{0 dB}

% 4. Linea verticale a wc = 100 rad/s
\addplot[
    color=mplblue!60,
    densely dotted,
    thick
] coordinates {(100,-44) (100,44)};
\addlegendentry{$\omega_c \approx 100$ rad/s}

\end{axis}
\end{tikzpicture}
\end{document}
```
## ANALISI DELL'ERRORE
Vediamo cosa succede invece dal punto di vista dell'**errore**, cioè la *"distanza" fra l'uscita e il riferimento*:
$$
E(s) = R(s) - Y(s) = R(s) - L(s)E(s)
$$
Abbiamo quindi:
$$
[1+L(s)]E(s) = R(s)
$$
Per cui troviamo:
$$
E(s) = \frac{1}{1+L(s)}R(s)
$$
E possiamo quindi definire la **funzione di trasferimento per l'errore**:

>[!def] FdT DELL'ERRORE
>$$ W_{e}(s) = \frac{1}{1+L(s)} $$

Siccome stiamo considerando un *controllo di tipo proporzionale*, abbiamo visto che il guadagno di anello è dato da $L(s)=\frac{K}{s+1}$. 
Per cui troviamo:
$$
W_{e}(s) = \frac{s+1}{s+1+K}
$$
E il **guadagno statico** (dell'errore) è allora:
$$
W_{e}(0) = \frac{1}{1+K}
$$
All'aumentare di $K$ abbiamo:
$$
\begin{matrix}
K   & | & W_{e}(0) \\
1   & | & 0.5 \\
10  & | & \approx 0.0909 \\
100 & | & \approx 0.0099
\end{matrix}
$$

>[!idea] OSSERVAZIONE IMPORTANTE
>Se $K$ aumenta, il **guadagno statico dell'errore tende a 0**.
>Ma sappiamo che:
>$$ E(s) = W_{e}(s)R(s) $$
>Per cui, con $K$ elevato, l'**errore** $e(t)$ **a regime** diventa **sempre più piccolo**.
## RUOLO DEL FEEDBACK IN PRESENZA DI DISTURBI
Vediamo ora cosa succede quando *introduciamo un disturbo* nel sistema.

Per semplicità, consideriamo un *disturbo additivo costante all'uscita dal sistema* ed un riferimento $r(t)=0$, come in figura:
```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta, positioning, calc}

% Definizione dei colori per farli corrispondere all'immagine
\definecolor{blockborder}{RGB}{140,140,140}
\definecolor{blockfill}{RGB}{232,232,232}
\definecolor{arrowgray}{RGB}{160,160,160}
\definecolor{rust}{RGB}{185, 65, 20}

\begin{document}
\begin{tikzpicture}[
    >={Stealth[scale=1.1]},
    block/.style={
        rectangle, 
        draw=blockborder, 
        fill=blockfill, 
        very thick, 
        minimum width=2.4cm, 
        minimum height=1.6cm, 
        align=center,
        font=\Large
    },
    sum/.style={
        circle,
        draw=blockborder,
        very thick,
        minimum size=0.45cm,
        inner sep=0pt
    },
    branch/.style={
        circle,
        fill=arrowgray,
        minimum size=3.5pt,
        inner sep=0pt
    }
]

% Coordinate e Nodi principali
\coordinate (r_start) at (-3, 0);
\node[sum] (sum1) at (-1, 0) {};
\node[block] (C) at (2, 0) {$C(s)$};
\node[block] (G) at (6, 0) {$G(s)$};
\node[sum] (sum2) at (8.5, 0) {};
\node[branch] (takeoff) at (9.4, 0) {};
\coordinate (y_end) at (10.6, 0);

% Ramo diretto (frecce orizzontali)
\draw[->, very thick, draw=arrowgray] 
    (r_start) -- node[pos=0.15, above, text=black] {$r(t) = 0$} node[pos=0.8, above=0.5mm, text=black] {$+$} (sum1);
\draw[->, very thick, draw=arrowgray] 
    (sum1) -- node[above, text=black] {$e(t)$} (C);
\draw[->, very thick, draw=arrowgray] 
    (C) -- node[above, text=black] {$u(t)$} (G);
\draw[->, very thick, draw=arrowgray] 
    (G) -- node[pos=0.6, below=1mm, text=black] {$+$} (sum2);
\draw[thick, draw=arrowgray] 
    (sum2) -- (takeoff); % Linea verso la diramazione
\draw[->, very thick, draw=arrowgray] 
    (takeoff) -- node[pos=0.7, below, text=black] {$y(t)$} (y_end);

% Ramo di Feedback (retroazione)
\draw[->, very thick, draw=arrowgray] 
    (takeoff) -- (takeoff |- 0, -1.8) 
    -- (-1, -1.8) 
    -- node[pos=0.85, left=0.5mm, text=black] {$-$} (sum1.south);

% Disturbo d(t) a forma di saetta
\draw[->, draw=rust, thick, line width=1.1pt] 
    (8.8, 1.5) node[right, text=black, xshift=1mm, yshift=2pt] {$d(t)$} 
    -- (8.3, 0.8) 
    -- (8.6, 0.9) 
    -- (sum2.north);
\node[text=black] at (8.15, 0.6) {$+$};

\end{tikzpicture}
\end{document}
```

Abbiamo quindi:
$$
\begin{align*}
Y(s) &= D(s) + G(s)U(s) = \\ \\
&= D(s) + L(s)E(s) \\ \\
&= D(s) - L(s)Y(s)
\end{align*}
$$
E troviamo:
$$
Y(s)[1+L(s)] = D(s)
$$
Per cui:
$$
Y(s) = \frac{1}{1+L(s)}D(s) = \frac{1}{1+C(s)G(s)}D(s)
$$
E allora abbiamo che la *funzione di trasferimento per il disturbo* è:
$$
W_{d}(s) = \frac{1}{1+L(s)} = \frac{s+1}{s+1+K}
$$
E il *guadagno statico* è:
$$
W_{d}(0) = \frac{1}{1+K}
$$
>[!idea] OSSERVAZIONE IMPORTANTE
>$W_{d}(s)$ segue la *stessa struttura* di $W_{e}(s)$.
>Ci aspettiamo quindi che, con $K$ elevato, se consideriamo *frequenze basse*, tali per cui il *guadagno di anello* $L$ sia elevato, il controllore **contrasti e attenui il disturbo costante** (*reiezione*) all'uscita.

Consideriamo per esempio $K=100$ e diamo uno sguardo ai diagrammi di Bode.
Abbiamo:
$$
L(j\omega) = \frac{100}{1+j\omega} \implies |L(j\omega)| = \frac{100}{\sqrt{ 1+\omega^2 }}
$$
Per *basse frequenze* abbiamo:
$$
|L(j\omega)| \approx 100 \gg 1
$$
E quindi:
$$
|W_{d}(j\omega)| = \frac{1}{|1+L(j\omega)|} \ll 1
$$
E infatti i diagrammi di Bode confermano le nostre ipotesi:
1. A *basse frequenze* un *alto valore* del guadagno di anello si traduce in una **forte reiezione** del disturbo.
2. Ad *alte frequenze*, invece, il *guadagno di anello diminuisce*, e così anche la *capacità di reiezione*.

```tikz
\usepackage{tikz}
\usepackage{pgfplots}
\usepackage{amsmath}
\pgfplotsset{compat=1.16}

% Definizione colori stile Matplotlib
\definecolor{mplblue}{HTML}{1F77B4}
\definecolor{mplorange}{HTML}{FF7F0E}

\begin{document}
\begin{tikzpicture}
\begin{axis}[
    width=16cm, height=8.5cm,
    xmode=log,
    xmin=1e-2, xmax=1e4,
    ymin=-45, ymax=45,
    xlabel={Frequenza $\omega$ [rad/s]},
    ylabel={Modulo [dB]},
    ytick={-40,-30,-20,-10,0,10,20,30,40},
    grid=both,
    major grid style={densely dotted, black!30},
    minor grid style={densely dotted, black!15},
    legend pos=north east,
    legend cell align=left,
    legend style={font=\scriptsize, fill=white, fill opacity=0.9},
    tick align=inside,
    axis lines=box,
    enlargelimits=false
]

% 1. Curva del modulo di L(s) (Blu)
% Formula: 20 * log10( 100 / sqrt(w^2 + 1) )
\addplot[
    domain=1e-2:1e4,
    samples=200,
    color=mplblue,
    thick
] {20*log10(100/sqrt(x^2 + 1))};
\addlegendentry{$|L(j\omega)|$}

% 2. Curva del modulo di Wd(s) (Arancione)
% Formula: 20 * log10( sqrt(w^2 + 1) / sqrt(w^2 + 101^2) )
% 101^2 = 10201
\addplot[
    domain=1e-2:1e4,
    samples=200,
    color=mplorange,
    thick
] {20*log10(sqrt(x^2 + 1) / sqrt(x^2 + 10201))};
\addlegendentry{$|W_d(j\omega)|$}

% 3. Linea orizzontale a 0 dB (Tratteggiata blu)
\addplot[
    domain=1e-2:1e4,
    color=mplblue,
    densely dashed,
    semithick
] {0};
\addlegendentry{$0$ dB}

% 4. Linea verticale a wc = 100 rad/s (Puntinata azzurra)
\addplot[
    color=mplblue!60,
    densely dotted,
    thick
] coordinates {(100,-45) (100,45)};
\addlegendentry{$\omega_c = 100$ rad/s}

% 5. Linea orizzontale per Wd(0) = -40 dB (Puntinata azzurra)
\addplot[
    domain=1e-2:1e4,
    color=mplblue!60,
    densely dotted,
    thick
] {-40};
\addlegendentry{$W_d(0) = -40$ dB}

\end{axis}
\end{tikzpicture}
\end{document}
```
## RUOLO DEL FEEDBACK IN PRESENZA DI INCERTEZZA
Abbiamo visto sopra come si comporta un sistema a *catena aperta* in caso di **incertezza del modello**. Vediamo ora come si comportano invece i sistemi con *feedback*.

Indichiamo con $\widehat{G}(s)$ il **modello nominale noto** e con $G(s)$ il **processo reale**:
$$
G(s) = \widehat{G}(s)(1+\Delta(s))
$$
Definiamo la **FdT d'anello nominale**:
$$
\widehat{L}(s) = \widehat{G}(s)C(s)
$$
Allora la **FdT d'anello reale** è:
$$
\begin{align*}
L(s) &= G(s)C(s) = \widehat{G}(s)C(s)(1+\Delta(s)) \\ \\
&= \widehat{L}(s)(1+\Delta(s))
\end{align*}
$$
E quindi la **FdT reale** tra **riferimento e uscita** diventa:
$$
W_{r}(s) = \frac{\widehat{L}(s)(1+\Delta(s))}{1+\widehat{L}(s)(1+\Delta(s))}
$$

>[!note] NOTA
>Se, in una *certa banda di frequenze*, abbiamo:
>$$ |\widehat{L}(j\omega)(1+\Delta(j\omega))| \gg 1 $$
>Allora:
>$$ W_{r}(j\omega) = \frac{\widehat{L}(j\omega)(1+\Delta(j\omega))}{1+\widehat{L}(j\omega)(1+\Delta(j\omega))} \approx 1 $$

>[!idea] OSSERVAZIONE IMPORTANTE
>Quindi, se il **guadagno di anello reale** è **elevato**, il feedback **attenua l'effetto dell'errore** di modello (in quelle frequenze in cui il guadagno reale di anello è elevato).

Consideriamo per esempio un sistema descritto dal modello noto con FdT data da:
$$
\widehat{G}(s) = \frac{1}{s+1}
$$
Allora abbiamo:
$$
G(s) = \frac{1+\varepsilon}{s+1}
$$
Consideriamo ancora una volta un *controllore proporzionale*:
$$
C(s) = K = 100
$$
Il *guadagno reale di anello* è dato da:
$$
L(s) = C(s)G(s) = \frac{100(1+\varepsilon)}{s+1}
$$
E troviamo quindi che la *funzione di trasferimento* è:
$$
W_{r}(s) = \frac{L(s)}{1+L(s)} = \frac{100(1+\varepsilon)}{s+1+100(1+\varepsilon)}
$$
E quindi il **guadagno statico** è:
$$
W_{r}(0) = \frac{100(1+\varepsilon)}{1+100(1+\varepsilon)}
$$
Per esempio, per i seguenti valori di $\varepsilon$ abbiamo:
$$
\begin{matrix}
\varepsilon & | & W_{r}(0) \\
-20\% & | & \frac{80}{81} \approx 0.988 \\
0\%   & | & \frac{100}{101} \approx 0.990 \\
+20\% & | & \frac{120}{121} \approx 0.992
\end{matrix}
$$

>[!note] NOTA
>In feedback, l'*effetto dell'errore di modello* viene *attenuato dal guadagno di anello*.
>Si parla quindi di **robustezza del sistema di controllo**.
## LIMITI E COMPROMESSI
Siano:
$$
\begin{align*}
S(s) := \frac{1}{1+L(s)} & & T(s) := \frac{L(s)}{1+L(s)} & & S(s) + T(s) = 1
\end{align*}
$$
Abbiamo visto che:
1. La **FdT riferimento - errore** è data da: $W_{e}=S(s)$.
2. La **FdT disturbo - uscita** è data da: $W_{d}(s)=S(s)$.
3. La **FdT riferimento - uscita** è data da $W_{r}=T(s)$.

E abbiamo capito che:
$$
\begin{align*}
|L(j\omega)| \gg 1 & & \implies & & S(j\omega) \approx 0 \text{ , } T(j\omega) \approx 1 
\end{align*}
$$

>[!idea] RUOLO DEL FEEDBACK
>Il feedback è importante perchè, dove il *guadagno di anello è elevato*, **migliora inseguimento**, **reiezione** dei **disturbi** e **robustezza**.

Tuttavia, il *guadagno d'anello* **non può essere aumentato** indefinitamente **senza effetti collaterali**.

>[!warning] ATTENZIONE: COMPROMESSI
>1. Un controllore proporzionale con $K$ *elevato* **può richiedere comandi** $u(t)$ in ingresso al sistema da controllare **molto grandi** (problema della *saturazione dell'attuatore*).
>2. Un eventuale **rumore di misura** su $y(t)$ può essere **amplificato dal controllore** e *propagarsi all'uscita*.
>3. Eventuali **ritardi temporali** tra $u(t)$ e $y(t)$ possono *degradare il comportamento* (a causa di **sovra-correzioni** effettuate nel momento sbagliato).

Come spesso accade in ingegneria, ogni soluzione presenta sempre dei *compromessi*.
## CASO CANCELLAZIONI FRA ZERI E POLI
Consideriamo un sistema di controllo *in retroazione* come quello in figura:

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta, positioning, calc}

% Definizione dei colori per farli corrispondere all'immagine
\definecolor{blockborder}{RGB}{140,140,140}
\definecolor{blockfill}{RGB}{232,232,232}
\definecolor{arrowgray}{RGB}{160,160,160}
\definecolor{rust}{RGB}{185, 65, 20}

\begin{document}
\begin{tikzpicture}[
    >={Stealth[scale=1.1]},
    block/.style={
        rectangle, 
        draw=blockborder, 
        fill=blockfill, 
        very thick, 
        minimum width=2.4cm, 
        minimum height=1.6cm, 
        align=center,
        font=\Large
    },
    sum/.style={
        circle,
        draw=blockborder,
        very thick,
        minimum size=0.45cm,
        inner sep=0pt
    },
    branch/.style={
        circle,
        fill=arrowgray,
        minimum size=3.5pt,
        inner sep=0pt
    }
]

% Coordinate e Nodi principali
\coordinate (r_start) at (-3, 0);
\node[sum] (sum1) at (-1, 0) {};
\node[block] (C) at (2, 0) {$C(s)$};
\node[block] (G) at (6, 0) {$G(s)$};
\node[sum] (sum2) at (8.5, 0) {};
\node[branch] (takeoff) at (9.4, 0) {};
\coordinate (y_end) at (10.6, 0);

% Ramo diretto (frecce orizzontali)
\draw[->, very thick, draw=arrowgray] 
    (r_start) -- node[pos=0.15, above, text=black] {$r(t)$} node[pos=0.8, above=0.5mm, text=black] {$+$} (sum1);
\draw[->, very thick, draw=arrowgray] 
    (sum1) -- node[above, text=black] {$e(t)$} (C);
\draw[->, very thick, draw=arrowgray] 
    (C) -- node[above, text=black] {$u(t)$} (G);
\draw[->, very thick, draw=arrowgray] 
    (G) -- node[pos=0.6, below=1mm, text=black] {$+$} (sum2);
\draw[thick, draw=arrowgray] 
    (sum2) -- (takeoff); % Linea verso la diramazione
\draw[->, very thick, draw=arrowgray] 
    (takeoff) -- node[pos=0.7, below, text=black] {$y(t)$} (y_end);

% Ramo di Feedback (retroazione)
\draw[->, very thick, draw=arrowgray] 
    (takeoff) -- (takeoff |- 0, -1.8) 
    -- (-1, -1.8) 
    -- node[pos=0.85, left=0.5mm, text=black] {$-$} (sum1.south);

\end{tikzpicture}
\end{document}
```

Sappiamo che il **guagagno di anello** è dato da:
$$
L(s) := C(s)G(s)
$$
Che scriviamo come segue:
$$
L(s) = \frac{n(s)}{d(s)} = \frac{n_{C}(s)}{d_{C}(s)} \frac{n_{G}(s)}{d_{G}(s)}
$$
Allora abbiamo:
$$
\begin{align*}
\text{zeri}\{ L(s) \} \subseteq \text{zeri}\{ C(s) \} \cup \text{zeri}\{ G(s) \} \\ \\
\text{poli}\{ L(s) \} \subseteq \text{poli}\{ C(s) \} \cup \text{poli}\{ G(s) \}
\end{align*}
$$

>[!note] NOTA
>L'**inclusione** è **stretta** se e solo se *nel prodotto* $C(s)G(s)$ si verificano **cancellazioni zero - polo**.
>

Sappiamo che la **FdT in anello chiuso** è:
$$
\begin{align*}
W(s) &= \frac{L(s)}{1+L(s)} = \frac{\frac{n(s)}{d(s)}}{1+ \frac{n(s)}{d(s)}} \\ \\
&= \frac{n(s)}{d(s)+n(s)}
\end{align*}
$$
>[!idea] OSSERVAZIONI IMPORTANTI
>La *retroazione* ha i seguenti effetti:
>1. **Non cambia la posizione degli zeri**: gli zeri di $W(s)$ *coincidono* con gli zeri di $L(s)$, salvo *eventuali cancellazioni*.
>2. **Cambia la posizione dei poli** del sistema in retroazione *rispetto a quelli in anello aperto*.

Noi assumeremo, salvo casi particolari esplicitamente segnalati, che $G(s)$ *rappresenti correttamente le dinamiche del sistema da controllare*, immaginando quindi di essere in **assenza cancellazioni polo-zero nascoste**.

>[!warning] ATTENZIONE
>E' fondamentale però **evitare che la FdT** $C(s)$ **cancelli poli** (e/o zeri) della **FdT** $G(s)$ a **parte reale non negativa**.
>Tali cancellazioni possono **rendere NON asintoticamente stabile** l'*interconnessione in retroazione*, anche se la FdT $W(s)$ **appare stabile**.

Infatti tali *cancellazioni incrociate* sono **puramente teoriche** nel senso che, in generale, la FdT $G(s)$ **non è nota in modo esatto**: allora i suoi *zeri e poli* sono *noti in modo approssimato* e, al più, possono esserci delle "*quasi cancellazioni*".

Per capire meglio, consideriamo un esempio pratico.
Sia, idealmente:
$$
\begin{align*}
C(s) = \frac{s-1}{s} & & G(s) = \frac{1}{s-1}
\end{align*}
$$
Allora abbiamo:
$$
L(s) = C(s)G(s) = \frac{s-1}{s} \frac{1}{s-1} = \frac{1}{s}
$$
Da cui troviamo:
$$
W(s) = \frac{L(s)}{1+L(s)} = \frac{1/s}{1+1/s} = \frac{1}{s+1}
$$
>[!note] NOTA
>La **funzione di trasferimento** $W(s)$ **appare BIBO stabile** (polo in $s=-1$).

Sia ora, *nella realtà*, dato $\varepsilon \ne 0$, $|\varepsilon|\ll 1$ tale che:
$$
G(s) = \frac{1}{s-1-\varepsilon}
$$
Allora, in tal caso, abbiamo:
$$
L(s) = \frac{s-1}{s(s-1-\varepsilon)}
$$
Da cui:
$$
W(s) = \frac{s-1}{s(s-1-\varepsilon)+(s-1)} = \frac{s-1}{s^2-\varepsilon s-1}
$$
>[!warning] ATTENZIONE
>Il polinomio $s^2-\varepsilon s -1$ **non è Hurwitz** (condizione necessaria è che tutti i coefficienti siano positivi), per cui $W(s)$ **in realtà non è BIBO stabile**.
