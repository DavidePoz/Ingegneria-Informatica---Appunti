# INDICE SEZIONE
- [ ] [[#INTRO (DEFINIZIONE)]]
- [ ] [[#SISTEMI ELEMENTARI DEL PRIMO ORDINE]]
      - [[#ESEMPIO P.O. 1]]
      - [[#ESEMPIO P.O. 2]]
      - [[#PRESENZA DI UNO ZERO]]
- [ ] [[#SISTEMI ELEMENTARI DEL SECONDO ORDINE]]
      - [[#RISPOSTA NEL CASO SOTTOSMORZATO]]
      - [[#RISPOSTA NEGLI ALTRI CASI]]
      - [[#PARAMETRI CARATTERIZZANTI]]
      - [[#LEGAME TRA I PARAMETRI E I POLI]]
      - [[#RISPETTO DEI VINCOLI]]
      - [[#SISTEMI DEL SECONDO ORDINE CON UNO ZERO]]
- [ ] [[#SISTEMI DI ORDINE SUPERIORE]]
      - [[#MODELLI APPROSSIMATI DI ORDINE RIDOTTO]]
- [ ] [[#FEDELTA' DELLA RISPOSTA]]
      - [[#ERRORE A REGIME - TIPO DI UN SISTEMA]]
      - [[#ERRORE TRANSITORIO]]
      - [[#DALLA FEDELTA' DELLA RISPOSTA AL PROGETTO DEL CONTROLLORE]]
# INTRO (DEFINIZIONE)
>[!def] ORDINE DEL SISTEMA
>L'**ordine del sistema** è il **grado del denominatore** della *funzione di trasferimento* dopo *eventuali cancellazioni polo-zero*:
>$$ G(s) = \frac{b_{m}s^m + b_{m-1}s^{m-1} + \ldots + b_{1}s + b_{0}}{a_{n}s^n + a_{n-1}s^{n-1} + \ldots + a_{1}s + a_{0}} $$

Inoltre, per effettuare studi di *tipo generale* sui sistemi, si considerano spesso i cosiddetti **sistemi elementari**, cioè quelli di **ordine uno e due**.
La motivazione è la seguente:

>[!idea] SISTEMA ELEMENTARE: MOTIVAZIONE
>Una funzione $G(s)$ come quella sopra può essere riscritta nella seguente forma:
>$$ G(s) = \frac{\rho}{s^h} \frac{\Pi_{k}(s-z_{k}) \Pi_{i}(s^2+2\delta_{i}\alpha_{n,i}s+\alpha_{n,i}^2)}{\Pi_{k}(s-p_{k}) \Pi_{i}(s^2+2\xi_{i}\omega_{n,i}s+\omega_{n,i}^2)} $$
>Cioè il sistema può **essere scomposto** in **contributi elementari**: è quindi sufficiente studiare queste componenti singolarmente per capire come si evolve la dinamica del sistema completo.

>[!note] NOTA
>1. I sistemi di *ordine uno* sono associati a **poli reali**.
>2. I sistemi di *ordine due* sono associati a **poli complessi coniugati**.
# SISTEMI ELEMENTARI DEL PRIMO ORDINE
Consideriamo il seguente sistema:
$$
\begin{align*}
G(s) &= \frac{1}{s-p_{1}} = \frac{1}{-p_{1}\left( \frac{1}{-p_{1}}s +1 \right)} \\ \\
&= \frac{T_{1}}{T_{1}s+1} = \frac{K_{1}}{T_{1}s+1}
\end{align*}
$$
Assumendo $p_{1}<0$, il sistema è BIBO stabile.
Inoltre osserviamo che:

>[!note] NOTA
>La prima rappresentazione è detta *zero-polo*, perchè evidenzia bene la *posizione del polo*.
>La seconda, invece, è detta **rappresentazione in costanti di tempo** perchè mette in evidenza:
>1. $T_{1}:=\frac{1}{-p_{1}}>0$ è la **costante di tempo** che caratterizza il comportamento dinamico.
>2. $K_{1}:=G(0)=T_{1}>0$ è il **guadagno statico** che determina il valore di regime della risposta al gradino.

La risposta al *gradino unitario* è data da:
$$
Y(s) = \underset{ =U(s) }{ \frac{1}{s} }G(s)
$$
Per cui abbiamo:
$$
\begin{align*}
y(t) &= \mathcal{L}^{-1}\left[  \frac{K_{1}}{s(T_{1}s+1)}  \right] \\ \\
&= K_{1}(1-e^{ -t/T_{1} })
\end{align*}
$$
Vediamo subito che scrivere $G(s)$ nella forma *rappresentazione in costanti di tempo* ci aiuta a *calcolare velocemente l'antitrasformata*.
## ESEMPIO P.O. 1
Consideriamo per esempio:
$$
G(s) = -\frac{p_{1}}{s-p_{1}}
$$
Riscriviamo $G(s)$ come segue, ponendo $T_{1}=-1/p_{1}$:
$$
G(s) = \frac{1}{sT_{1}+1}
$$
Il guadagno è $G(0)=1$.
E la risposta al gradino unitario, come visto sopra, è:
$$
y(t) = 1-e^{ p_{1}t }
$$
Abbiamo detto che $T_{1}$ è la *costante di tempo*, e che valori maggiori di $T_{1}$ sono associati a *dinamiche più lente*.
Infatti:
$$
\frac{dy(t)}{dt}\bigg|_{t=0} = -p_{1}e^{ p_{1}t }\bigg|_{t=0} = -p_{1} = \frac{1}{T_{1}}
$$
>[!note] NOTA
>*Maggiore* è il valore di $T_{1}$, *minore è la pendenza iniziale*.
>

```tikz
\usepackage{pgfplots}
\usepackage{amsmath}
\pgfplotsset{compat=1.16}

\begin{document}
\begin{tikzpicture}
    \begin{axis}[
        width=12cm, 
        height=6.5cm,
        xmin=0, xmax=5.2,
        ymin=-0.05, ymax=1.05,
        xlabel={Tempo [s]},
        ylabel={Risposta y(t)},
        xtick={0,1,2,3,4,5},
        ytick={0,0.2,0.4,0.6,0.8,1.0},
        grid=major,
        grid style={dashed, gray!30},
        axis x line*=bottom,
        axis y line*=left,
        clip=false,
        legend pos=south east,
        legend style={
            font=\scriptsize, 
            cells={anchor=west},
            draw=gray!50
        },
        tick align=outside,
        tick style={draw=black, thin}
    ]

    % Curva p1 = -1 (Blu)
    \addplot[domain=0:5, samples=100, thick, blue] {1 - exp(-1*x)};
    \addlegendentry{$p_1 = -1$}

    % Curva p1 = -2 (Arancione)
    \addplot[domain=0:5, samples=100, thick, orange] {1 - exp(-2*x)};
    \addlegendentry{$p_1 = -2$}

    % Curva p1 = -5 (Viola)
    \addplot[domain=0:5, samples=100, thick, violet] {1 - exp(-5*x)};
    \addlegendentry{$p_1 = -5$}

    % Tangenti nell'origine (Ciano)
    % Le pendenze in t=0 sono uguali a -p1
    \draw[cyan, thick] (0,0) -- (0.5, 0.5);   % p1 = -1 -> T1 = 1
    \draw[cyan, thick] (0,0) -- (0.25, 0.5);  % p1 = -2 -> T1 = 0.5
    \draw[cyan, thick] (0,0) -- (0.1, 0.5);   % p1 = -5 -> T1 = 0.2

    % Freccia tratteggiata per la direzione di T1 crescente (Grigia)
    \draw[->, >=latex, dashed, gray!70, thick] (0.25, 0.95) -- (1.2, 0.35);

    \end{axis}
\end{tikzpicture}
\end{document}
```
E' inoltre utile osservare che:
$$
\begin{align*}
y(T_{1}) &\approx 0.63 \\ \\
y(2T_{1}) &\approx 0.865 \\ \\
y(3T_{1}) &\approx 0.95 \\ \\
y(5T_{1}) &\approx 0.993
\end{align*}
$$
## ESEMPIO P.O. 2
Consideriamo ora:
$$
G(s) = K \frac{1}{s+1}
$$
La risposta al gradino unitario è:
$$
y(t) = K(1-e^{ -t })
$$

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}

\begin{document}
\begin{tikzpicture}
    \begin{axis}[
        width=12cm, 
        height=6.5cm,
        xmin=-0.2, xmax=5.2,
        ymin=-0.2, ymax=5.2,
        xlabel={Tempo [s]},
        ylabel={Risposta y(t)},
        xtick={0,1,2,3,4,5},
        ytick={0,1,2,3,4,5},
        grid=major,
        grid style={dashed, gray!30},
        axis x line*=bottom,
        axis y line*=left,
        legend pos=north west,
        legend style={
            font=\scriptsize, 
            cells={anchor=west},
            draw=gray!50
        },
        tick align=outside,
        tick style={draw=black, thin}
    ]

    % Curva K = 1 (Blu)
    \addplot[domain=0:5, samples=100, thick, blue] {1 * (1 - exp(-1*x))};
    \addlegendentry{K = 1}

    % Curva K = 2 (Arancione)
    \addplot[domain=0:5, samples=100, thick, orange] {2 * (1 - exp(-1*x))};
    \addlegendentry{K = 2}

    % Curva K = 5 (Viola)
    \addplot[domain=0:5, samples=100, thick, violet] {5 * (1 - exp(-1*x))};
    \addlegendentry{K = 5}

    % Freccia tratteggiata verticale (Grigia)
    \draw[->, >=latex, dashed, gray!70, thick] (4.3, 0.5) -- (4.3, 4.5);

    \end{axis}
\end{tikzpicture}
\end{document}
```

>[!note] NOTA
>Il parametro $K$ modifica il *valore finale della risposta*, ma *non la velocità del transitorio*
## PRESENZA DI UNO ZERO
Se *oltre al polo* vi è **anche uno zero**, il sistema è *proprio* e la *risposta al gradino* può avere un *salto iniziale*.

Consideriamo:
$$
\begin{align*}
G(s) &= \frac{1+\hat{T}_{1}s}{1+T_{1}s} = \frac{\hat{T}_{1}}{T_{1}} + \frac{1-\hat{T}_{1}/T_{1}}{1+T_{1}s} \\ \\
&= \alpha + \frac{1-\alpha}{1+T_{1}s}
\end{align*}
$$
>[!note] NOTA
>Dove abbiamo definito:
>$$ \alpha := \frac{\hat{T}_{1}}{T_{1}} $$
>E' il *rapporto tra le costanti di tempo* (e anche il *rapporto zero-polo*: $\alpha=\frac{p_{1}}{z_{1}}$).

La risposta al gradino è data da:
$$
Y(s) = \frac{1}{s}G(s) = \frac{\alpha}{s} + \frac{1-\alpha}{s(1+T_{1}s)}
$$
Per cui:
$$
\begin{align*}
y(t) &= \alpha + (1-\alpha)(1-e^{ -t/T_{1} }) \\ \\
&= \alpha+ 1-\alpha + (\alpha-1)e^{ -t/T_{1} } \\ \\
&= 1 + (\alpha-1)e^{ -t/T_{1} }
\end{align*}
$$
Notiamo che:
$$
\begin{align*}
y(0) &= \alpha \\ \\
\frac{dy(t)}{dt}\bigg|_{t=0} &= \frac{1-\alpha}{T_{1}} 
\end{align*}
$$

>[!idea] OSSERVAZIONE IMPORTANTE
>La *presenza di uno zero* introduce un **salto iniziale** nella dinamica del sistema.

Al variare di $\alpha$ (e quindi della *posizione dello zero rispetto al polo*) abbiamo i seguenti andamenti:

```tikz
\usepackage{pgfplots}
\usepackage{amsmath}
\pgfplotsset{compat=1.16}

\begin{document}
\begin{tikzpicture}
    \begin{axis}[
        width=10cm, 
        height=8cm,
        xmin=0, xmax=6,
        ymin=-1, ymax=1.5,
        xtick={0,1,2,3,4,5,6},
        xticklabels={}, % Nasconde i numeri sull'asse x come nell'immagine
        ytick={-1, -0.5, 0, 0.5, 1, 1.5},
        y tick label style={font=\bfseries, text=black},
        grid=major,
        grid style={solid, gray!20},
        axis x line*=bottom,
        axis y line*=left,
        tick align=inside,
        tick style={draw=none}, % Nasconde i trattini esterni per un look più pulito
        enlargelimits=false,
        clip=false % Permette alle etichette di fuoriuscire leggermente dai bordi se necessario
    ]

    % Curva alpha = 1.5 (Blu)
    \addplot[domain=0:6, samples=100, thick, blue] {1 + (1.5 - 1)*exp(-x)};
    \node[blue] at (axis cs: 2.2, 1.25) {$\boldsymbol{\alpha = 1.5}$};

    % Curva alpha = 0.5 (Verde scuro)
    \addplot[domain=0:6, samples=100, thick, green!60!black] {1 + (0.5 - 1)*exp(-x)};
    \node[green!60!black] at (axis cs: 1.2, 0.97) {$\boldsymbol{\alpha = 0.5}$};

    % Curva alpha = 0 (Ciano, tratteggiata finemente)
    \addplot[domain=0:6, samples=100, thick, cyan!80!blue, densely dotted] {1 + (0 - 1)*exp(-x)};
    \node[cyan!80!blue, fill=white, inner sep=1pt] at (axis cs: 0.35, 0) {$\boldsymbol{\alpha = 0}$};

    % Curva alpha = -1 (Rosso)
    \addplot[domain=0:6, samples=100, thick, red] {1 + (-1 - 1)*exp(-x)};
    \node[red] at (axis cs: 1.5, 0.2) {$\boldsymbol{\alpha = -1}$};

    \end{axis}
\end{tikzpicture}
\end{document}
```

>[!note] NOTA
>Se lo *zero* si trova *nel semipiano destro* abbiamo $\alpha<0$ e la risposta *presenta potenzialmente* un *andamento iniziale inverso*, tipico dei sistemi a *fase non minima*.
# SISTEMI ELEMENTARI DEL SECONDO ORDINE
Un tipico sistema del secondo ordine, a meno di un fattore costante, si può rappresentare con una FdT del tipo:
$$
\begin{align*}
G(s) &= \frac{1}{1+2\xi\frac{s}{\omega_{n}} + \frac{s^2}{\omega^2_{n}}} \\ \\
&= \frac{\omega_{n}^2}{s^2 + 2\xi\omega_{n}s + \omega_{n}^2} \\ \\
&= \frac{\omega_{n}^2}{(s-p_{1})(s-p_{2})}
\end{align*}
$$
Con $0\leq \xi\leq 1$.
Dove i poli sono dati da:
$$
\begin{align*}
p_{1,2} &= \sigma \pm j\omega = -\xi\omega_{n} \pm j\omega_{n} \sqrt{ 1-\xi^2 } \\ \\
&= -\omega_{n}\cos \varphi \pm j\omega_{n}\sin \varphi
\end{align*} 
$$
Con $\varphi = \arccos \xi$.
I fattori $\omega_{n}$ e $\xi$ sono detti:
- $\omega_{n}=|s_{1}|=|s_{2}|=\sqrt{ \sigma^2+\omega^2 }$ : **pulsazione naturale**.
- $\xi=\cos \varphi =-\frac{\sigma}{\sqrt{ \sigma^2+\omega^2 }}$ : **fattore di smorzamento**.

```tikz
\usepackage{amsmath}
\usetikzlibrary{decorations.pathreplacing, arrows.meta}

\begin{document}
\begin{tikzpicture}
    % Definizione dei parametri geometrici per facilitare le modifiche
    \def\poleX{-3}
    \def\poleY{2.2}

    % Coordinate principali
    \coordinate (O) at (0,0);
    \coordinate (P1) at (\poleX, \poleY);
    \coordinate (P2) at (\poleX, -\poleY);
    \coordinate (Sigma) at (\poleX, 0);
    \coordinate (Omega) at (0, \poleY);

    % Assi coordinati
    \draw[thick, ->, >=latex] (-5.5, 0) -- (2.5, 0) node[below, font=\large] {$Re$};
    \draw[thick, ->, >=latex] (0, -3.2) -- (0, 4) node[right, font=\large] {$Im$};

    % Linee tratteggiate di costruzione (densely dotted come nell'immagine)
    \draw[thick, densely dotted] (P1) -- (P2);
    \draw[thick, densely dotted] (P1) -- (Omega);
    \draw[thick, densely dotted] (O) -- (P1) node[midway, above, sloped] {$\omega_n$};
    \draw[thick, densely dotted] (O) -- (P2);

    % Disegno dei poli (croci spesse)
    \draw[ultra thick] (P1) +(-0.18,-0.18) -- +(0.18,0.18);
    \draw[ultra thick] (P1) +(-0.18,0.18) -- +(0.18,-0.18);
    \node[left=6pt, font=\large] at (P1) {$p_1$};

    \draw[ultra thick] (P2) +(-0.18,-0.18) -- +(0.18,0.18);
    \draw[ultra thick] (P2) +(-0.18,0.18) -- +(0.18,-0.18);
    \node[left=6pt, font=\large] at (P2) {$p_2$};

    % Etichette sull'asse Immaginario
    \node[right=6pt, font=\large] at (Omega) {$\omega = \omega_n\sqrt{1-\xi^2}$};

    % Graffa per la parte reale (sigma)
    \draw[decorate, decoration={brace, amplitude=6pt, mirror}, thick]
        (\poleX, -2.8) -- (0, -2.8) node[midway, below=10pt, font=\large] {$\sigma = -\xi\omega_n$};

    % Arco per l'angolo (con doppia freccia)
    % L'angolo per (-3, 2.2) partendo dall'asse x positivo è atan(2.2 / -3) = 143.74 gradi
    \draw[<->, >=latex, thick] (-1.1, 0) arc (180:143.74:1.1);

    % Etichetta e freccia curva per l'angolo phi
    \node (phi) at (-3.5, 3.6) {\large $\varphi = \arccos(\xi)$};
    \draw[->, >=latex, thick, densely dotted] (phi.south) to[out=-90, in=180] (-1.2, 0.4);

    % Numero di pagina in basso a destra (come nell'originale)
    \node[gray, font=\scriptsize] at (3.5, -4) {7};

\end{tikzpicture}
\end{document}
```

Notiamo inoltre che:
1. Se $\xi=0$ abbiamo $s_{1,2}=\pm j\omega_{n}$: i poli sono **immaginari puri**.
2. Se $\xi=1$ abbiamo $s_{1,2}=-\omega_{n}$: i poli sono **reali** (e *coincidenti*).

>[!idea] OSSERVAZIONE IMPORTANTE
>Abbiamo assunto $0\leq \xi\leq 1$.
>Se invece avessimo $\xi>1$, allora:
>$$ j\omega_{n}\sqrt{ 1-\xi^2 } = \cancelto{ -1 }{ j^2 }\omega_{n}\sqrt{ \xi^2-1 } $$
>Per cui i poli $p_{1},p_{2}$ diventano *puramente reali* e **distinti**.
>$$ p_{1,2} = -\xi\omega_{n} \pm \omega_{n}\sqrt{ \xi^2-1 } $$
>All'*aumentare* di $\xi$, uno dei due poli si *avvicina all'origine*, mentre l'*altro si allontana*.
>Questi introducono, rispettivamente, una *dinamica lenta* e una *dinamica veloce*.

>[!note] NOTA
>Al variare di $\xi$ si parla di:
>1. $0\leq \xi <1$ : **sotto-smorzamento** (e per $\xi=0$ si parla *caso non smorzato*).
>2. $\xi=1$ : **smorzamento critico**.
>3. $\xi>1$ : **sovra smorzamento**.
## RISPOSTA NEL CASO SOTTOSMORZATO
La risposta al gradino unitario è data da:
$$
Y(s) = \frac{1}{s}G(s) = \frac{1}{s} \frac{\omega_{n}^2}{s^2+2\xi\omega_{n}s+\omega_{n}^2}
$$
Per $0<\xi<1$ troviamo:
$$
y(t) = 1 - Ae^{ -\xi\omega_{n}t }\sin(\omega t+\varphi)
$$
Dove:
$$
\begin{align*}
A &= \frac{1}{\sqrt{ 1-\xi^2 }} \\ \\
\omega &= \omega_{n}\sqrt{ 1-\xi^2 } \\ \\
\varphi &= \arccos \xi
\end{align*}
$$

>[!idea] OSSERVAZIONE IMPORTANTE
>Più $\xi$ si *avvicina ad uno* e **più le oscillazioni sono smorzate**.
>Al contrario, bassi valori di $\xi$ corrispondono ad *oscillazioni più ampie*.

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}

% Definizione dei colori esatti della palette di default di MATLAB
\definecolor{matblue}{rgb}{0,0.447,0.741}
\definecolor{matorange}{rgb}{0.85,0.325,0.098}
\definecolor{matyellow}{rgb}{0.929,0.694,0.125}
\definecolor{matpurple}{rgb}{0.494,0.184,0.556}
\definecolor{matgreen}{rgb}{0.466,0.674,0.188}
\definecolor{matcyan}{rgb}{0.301,0.745,0.933}

\begin{document}
\begin{tikzpicture}
    \begin{axis}[
        width=12cm, 
        height=9cm,
        xmin=0, xmax=10,
        ymin=0, ymax=1.8,
        xlabel={Tempo (s)},
        ylabel={\Large $y(t)$},
        xtick={0,1,2,3,4,5,6,7,8,9,10},
        ytick={0,0.2,0.4,0.6,0.8,1,1.2,1.4,1.6,1.8},
        grid=major,
        grid style={solid, gray!30},
        legend pos=north east,
        legend style={
            font=\footnotesize,
            cells={anchor=west},
            draw=black,
            fill=white
        },
        tick align=inside,
        tick style={draw=black, thin},
        enlargelimits=false
    ]

    % Curva xi = 0.1
    % Formula: y(t) = 1 - e^(-xi*wn*t) * (cos(wd*t) + (xi*wn/wd)*sin(wd*t)) con wn = 1
    \addplot[domain=0:10, samples=200, thick, matblue] {1 - exp(-0.1*x)*(cos(deg(0.994987*x)) + 0.100503*sin(deg(0.994987*x)))};
    \addlegendentry{$\xi = 0.1$}

    % Curva xi = 0.25
    \addplot[domain=0:10, samples=200, thick, matorange] {1 - exp(-0.25*x)*(cos(deg(0.968245*x)) + 0.258198*sin(deg(0.968245*x)))};
    \addlegendentry{$\xi = 0.25$}

    % Curva xi = 0.5
    \addplot[domain=0:10, samples=200, thick, matyellow] {1 - exp(-0.5*x)*(cos(deg(0.866025*x)) + 0.577350*sin(deg(0.866025*x)))};
    \addlegendentry{$\xi = 0.5$}

    % Curva xi = 0.707
    \addplot[domain=0:10, samples=200, thick, matpurple] {1 - exp(-0.707*x)*(cos(deg(0.707213*x)) + 0.9997*sin(deg(0.707213*x)))};
    \addlegendentry{$\xi = 0.707$}

    % Curva xi = 0.9
    \addplot[domain=0:10, samples=200, thick, matgreen] {1 - exp(-0.9*x)*(cos(deg(0.435890*x)) + 2.06474*sin(deg(0.435890*x)))};
    \addlegendentry{$\xi = 0.9$}

    % Curva xi = 1 (Smorzamento critico)
    % Formula: y(t) = 1 - e^(-wn*t) * (1 + wn*t)
    \addplot[domain=0:10, samples=100, thick, matcyan] {1 - exp(-x)*(1 + x)};
    \addlegendentry{$\xi = 1$}

    % Pallini sui picchi (calcolati analiticamente tp = pi/wd)
    \node[circle, fill=black!70, inner sep=1.8pt] at (axis cs:3.157, 1.729) {};
    \node[circle, fill=black!70, inner sep=1.8pt] at (axis cs:3.245, 1.444) {};
    \node[circle, fill=black!70, inner sep=1.8pt] at (axis cs:3.627, 1.163) {};
    \node[circle, fill=black!70, inner sep=1.8pt] at (axis cs:4.442, 1.043) {};
    \node[circle, fill=black!70, inner sep=1.8pt] at (axis cs:7.207, 1.001) {};

    % Freccia tratteggiata per la direzione di xi
    \draw[dashed, ->, >=latex, thick, gray!80] (axis cs: 2.8, 0.6) -- (axis cs: 1.5, 1.5);
    
    % Testi vicino alla freccia
    \node[font=\Large, fill=white, inner sep=1pt] at (axis cs: 3.2, 0.6) {$\xi = 1$};
    \node[font=\Large, fill=white, inner sep=1pt] at (axis cs: 1.2, 1.65) {$\xi = 0.1$};

    \end{axis}
\end{tikzpicture}
\end{document}
```

Si definisce inoltre la **massima sovraelongazione** (o *overshoot*):

>[!def] MASSIMA SOVRAELONGAZIONE
>$$ M_{p} = \exp{\left(  -\frac{\pi \xi}{\sqrt{ 1-\xi^2 }} \right)} $$

Per esempio, per i seguenti valori di $\xi$ abbiamo (in percentuale):
$$
\begin{matrix}
\xi  & | & M_{p} \text{(\%)} \\
0.1 & | & 72.9 \\
0.25 & | & 44.4 \\
0.5 & | & 16.3 \\
0.707 & | & 4.3 \\
0.9 & | & 0.15 \\
1 & | & 0
\end{matrix}
$$
>[!idea] OSSERVAZIONE IMPORTANTE
>Se il *coefficiente di smorzamento* $\xi$ è basso, il sistema (*inizialmente*) **"supera" di molto il riferimento**.
## RISPOSTA NEGLI ALTRI CASI
Vediamo ora invece cosa succede per $\xi=0$, $\xi=1$ e $\xi>1$.
1. Caso **smorzamento nullo**: $\xi=0$. 
   In tal caso abbiamo $y(t)=1-\cos(\omega_{n}t)$, cioè una *risposta oscillatoria non smorzata*.
2. Caso **smorzamento critico**: $\xi=1$.
   In tal caso abbiamo $y(t)=1-e^{ -\omega_{n}t }(1+\omega_{n}t)$, cioè una *risposta criticamente smorzata*.
3. Caso **sovrasmorzamento**: $\xi>1$.
   In tal caso la risposta è *puramente reale*, in quanto *somma di esponenziali reali*, **senza oscillazioni**:
$$
y(t) = 1- \frac{ (\xi+\sqrt{ \xi^2-1 })e^{ -\omega_{n}(\xi-\sqrt{ \xi^2-1 })t } - (\xi-\sqrt{ \xi^2-1 })e^{ -\omega_{n}(\xi+\sqrt{ \xi^2-1 })t } }{ 2\sqrt{ \xi^2-1 } }
$$

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}

% Definizione dei colori della palette predefinita di Matplotlib (tab10)
\definecolor{tabblue}{HTML}{1f77b4}
\definecolor{taborange}{HTML}{ff7f0e}
\definecolor{tabgreen}{HTML}{2ca02c}

\begin{document}
\begin{tikzpicture}
    \begin{axis}[
        width=12cm, 
        height=9cm,
        xmin=0, xmax=10,
        ymin=0, ymax=2.1,
        xlabel={Tempo (s)},
        ylabel={\Large $y(t)$},
        xtick={0,1,2,3,4,5,6,7,8,9,10},
        ytick={0,0.2,0.4,0.6,0.8,1,1.2,1.4,1.6,1.8,2},
        grid=major,
        grid style={solid, gray!30},
        legend pos=north east,
        legend style={
            font=\footnotesize,
            cells={anchor=west},
            draw=black,
            fill=white
        },
        tick align=inside,
        tick style={draw=black, thin},
        enlargelimits=false
    ]

    % Curva xi = 0 (Blu): Sistema non smorzato
    \addplot[domain=0:12, samples=200, thick, tabblue] {1 - cos(deg(x))};
    \addlegendentry{$\xi = 0$: non smorzato}

    % Curva xi = 1 (Arancione): Sistema criticamente smorzato
    \addplot[domain=0:12, samples=100, thick, taborange] {1 - exp(-x)*(1 + x)};
    \addlegendentry{$\xi = 1$: criticamente smorzato}

    % Curva xi = 1.5 (Verde): Sistema sovrasmorzato
    \addplot[domain=0:12, samples=100, thick, tabgreen] {1 - (1.17082 * exp(-0.381966 * x) - 0.17082 * exp(-2.618034 * x))};
    \addlegendentry{$\xi = 1.5$: sovrasmorzato}

    % Linea tratteggiata per il valore a regime
    \addplot[domain=-0.5:12.5, samples=2, densely dashed, semithick, tabblue!80] {1};
    \addlegendentry{valore di regime}

    \end{axis}
\end{tikzpicture}
\end{document}
```
## PARAMETRI CARATTERIZZANTI
Abbiamo già visto che la *risposta al gradino* è caratterizzata da una *massima sovraelongazione*.
Esistono anche altri **parametri caratterizzanti**, utili sia per lo *studio* (analisi) del sistema che per la *sintesi del sistema di controllo*.

Tali parametri sono:
1. $M_{p}$ : **massima sovraelongazione** (*overshoot*).
2. $y_{ss}$ : **valore di regime** (*steady state*).
3. $T_{r}$ : **tempo di salita** (*rise-time*, per esempio dal $10\%$ al $90\%$ del valore a regime).
4. $T_{s}$ : **settling time** (*tempo di assestamento*, ad esempio entro un $5\%$ dal valore a regime).

La seguente figura ci aiuta a capire cosa rappresentano questi parametri:

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}
\usetikzlibrary{arrows.meta}

% Definisce il colore tipico dei plot di MATLAB (quello usato nell'immagine)
\definecolor{matlaborange}{RGB}{217,83,25}

\begin{document}
\begin{tikzpicture}
\begin{axis}[
    width=14cm, height=6.5cm,
    xmin=0, xmax=30,
    ymin=0, ymax=2,
    xlabel={Time [s]},
    ylabel={Output $y(t)$},
    xtick={0,5,10,15,20,25,30},
    ytick={0,0.5,1,1.5,2},
    tick align=inside,
    every axis plot/.append style={line width=1pt},
    label style={font=\normalsize},
    tick label style={font=\small}
]

% Fascia di tolleranza (+/- 5%) - Linee continue
\addplot [domain=0:30, samples=2, solid, black, thin] {1.05};
\addplot [domain=0:30, samples=2, solid, black, thin] {0.95};

% Valore di regime (Steady state) - Linea tratteggiata
\addplot [domain=0:30, samples=2, dashed, black, thin] {1};

% Curva di risposta al gradino (sistema del 2° ordine)
% Funzione ottimizzata per ricalcare i parametri visivi dell'immagine fornita
\addplot [domain=0:30, samples=300, matlaborange, thick] 
    {1 - exp(-0.21*x) * (cos(deg(1.029*x)) + 0.204*sin(deg(1.029*x)))};

% --- Annotazioni ---

% Annotazione Overshoot (Sovraelongazione)
\draw [thin] (axis cs: 2.5, 1.528) -- (axis cs: 4.5, 1.528);
\draw [<->, >=Stealth, thin] (axis cs: 4.2, 1) -- (axis cs: 4.2, 1.528) 
    node[midway, right] {Overshoot $M_{\mathrm{p}}$};

% Annotazione Rise time (Tempo di salita)
% Le linee verticali sono a circa 10% e 90% del valore finale
\draw [thin] (axis cs: 0.45, 0.05) -- (axis cs: 0.45, 1.1);
\draw [thin] (axis cs: 1.55, 0.05) -- (axis cs: 1.55, 1.1);
\draw [->, >=Stealth, thin] (axis cs: 0, 0.6) -- (axis cs: 0.45, 0.6);
\draw [<-, >=Stealth, thin] (axis cs: 1.55, 0.6) -- (axis cs: 2.3, 0.6) 
    node[right, inner sep=2pt] {Rise time $T_{\mathrm{r}}$};

% Annotazione Settling time (Tempo di assestamento)
\draw [thin] (axis cs: 14, 0.05) -- (axis cs: 14, 1.1);
\draw [->, >=Stealth, thin] (axis cs: 0, 0.33) -- (axis cs: 14, 0.33) 
    node[right, inner sep=4pt] {Settling time $T_{\mathrm{s}}$};

% Annotazione Steady-state value (Valore di regime)
\node [anchor=east, inner sep=2pt] at (axis cs: 28, 0.65) {Steady-state value $y_{\mathrm{ss}}$};
\draw [->, >=Stealth, thin] (axis cs: 28, 0.65) -- (axis cs: 29.5, 0.95);

\end{axis}
\end{tikzpicture}
\end{document}
```

Per un sistema del secondo ordine con $0<\xi<1$ valgono le seguenti relazioni:
$$
\begin{align*}
M_{p} &= \exp\left( -\frac{\pi \xi}{\sqrt{ 1-\xi^2 }} \right) \\ \\
T_{r} &\approx \frac{1.8}{\omega_{n}\sqrt{ 1-\xi^2 }} \approx \frac{1.8}{\omega_{n}} \\ \\
T_{s}\bigg|_{5\%} &\approx \frac{3-\ln \sqrt{ 1-\xi^2 }}{\omega_{n}\xi} \approx \frac{3}{\xi\omega_{n}}
\end{align*}
$$
Facciamo ora alcune osservazioni su questi parametri.

>[!idea] OSSERVAZIONI
>1. A *parità* di $\omega_{n}$, **aumentando lo smorzamento** $\xi$ **diminuisce l'overshoot**, ma la risposta può diventare *più lenta nella fase iniziale*.
>2. A *parità* di $\xi$, **aumentando la pulsazione** $\omega_{n}$ la **risposta diventa più rapida**.
## LEGAME TRA I PARAMETRI E I POLI
Immaginiamo di **fissare il coefficiente di smorzamento**.
Allora al variare di $\omega_{n}$ la **massima sovraelongazione non cambia** e si hanno risposte al gradino del seguente tipo:

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}
\usetikzlibrary{arrows.meta}

\begin{document}

\begin{tikzpicture}
\begin{axis}[
    width=12cm,
    height=9cm,
    grid=both,
    grid style={dashed, gray!30},
    xmin=0, xmax=20,
    ymin=0, ymax=1.4,
    xlabel={\textbf{Tempo \quad (sec)}},
    ylabel={\textbf{y(t)}},
    tick label style={font=\small},
    legend style={at={(0.95,0.05)}, anchor=south east, font=\tiny},
    axis line style={black!80},
    samples=100 % Numero di campioni bilanciato per velocità e precisione
]

% Definizione parametri: xi costante, cambiamo wn
% Costante xi per Mp circa 25% (1.25)
\def\xi{0.4}
\def\beta{sqrt(1-\xi^2)}

% Ciclo corretto per evitare errori di colore
\foreach \wn/\col in {4/blue, 3/magenta, 2.5/olive, 2/cyan, 1.5/black, 1.2/red, 0.8/green!60!black, 0.4/blue!80!black} {
    \edef\temp{\noexpand\addplot [
        domain=0:20,
        thick,
        color=\col,
        smooth
    ] {1 - (exp(-\xi*\wn*x)/\beta) * sin(deg(\wn*\beta*x) + acos(\xi))};}
    \temp
}

% Linea Mp costante (verde orizzontale)
\addplot[green!70!black, ultra thick, domain=0:11] {1.253} 
    node[right, black] {$M_p$ non varia};

% Linea asintotica (1.0)
\addplot[black, thin, dashed, domain=0:20] {1};

% Linea rossa di riferimento inferiore (settling)
\draw[red, thick] (axis cs: 1, 0.94) -- (axis cs: 16, 0.94);

% Freccia blu direzionale (indica l'aumento di wn o la direzione delle curve)
\draw[black, -{stealth}, very thick] (axis cs: 5.3, 0.58) -- (axis cs: 0.1, 0.96);

\end{axis}
\end{tikzpicture}

\end{document}
```

Ricordando che $\xi=\cos \varphi$, fissare $\xi$ vuol dire *vincolare i poli su due rette*.

>[!note] NOTA
>All'aumentare di $\omega_{n}$, i **poli si allontanano dall'origine** e rendono le **oscillazioni** dell'evoluzione sempre **più rapide**.

```tikz
\usepackage{tikz}
\usepackage{amsmath}
\usetikzlibrary{arrows.meta, shapes.misc}

\begin{document}

\begin{tikzpicture}[
    cross/.style={draw, cross out, minimum size=4pt, inner sep=0pt, outer sep=0pt},
    >=Stealth
]

    % Assi Cartesiani
    \draw[->] (-7,0) -- (1,0) node[below left] {$\sigma$};
    \draw[->] (0,-4) -- (0,4) node[left] {$\omega$};

    % Definizione dell'angolo per xi costante 
    % (L'angolo tra l'asse reale negativo e il raggio è arccos(xi))
    \def\angle{150} % Corrisponde a un xi circa 0.86
    \def\negangle{210}

    % Raggi tratteggiati (Luogo dei poli a xi costante)
    \draw[dotted, thick] (0,0) -- (\angle:7);
    \draw[dotted, thick] (0,0) -- (\negangle:7);

    % Poli (le "X") - Variando wn (distanza dall'origine)
    \foreach \r in {2, 4, 6} {
        \node[cross] at (\angle:\r) {};
        \node[cross] at (\negangle:\r) {};
    }

    % Proiezioni per un polo specifico (quello centrale a r=4)
    \def\specR{4}
    \coordinate (P) at (\angle:\specR);
    
    \draw[dashed] (P) -- (P |- 0,0) node[below] {$\sigma = -\xi\omega_n$};
    \draw[dashed] (P) -- (0,0 |- P) node[right] {$\omega = \omega_n \sqrt{1-\xi^2}$};

    % Freccia blu direzionale (Aumento di wn)
    \draw[black, ultra thick, ->] ([shift={(0.3,-0.5)}] \angle:4) -- ([shift={(0.3,-0.5)}] \angle:6.5);

\end{tikzpicture}

\end{document}
```

Possiamo ottenere il **periodo della prima oscillazione smorzata** $T_{po}$ come segue:
$$
T_{po} = \frac{2\pi}{\omega_{n}\sqrt{ 1-\xi^2 }}
$$
Se invece **fissiamo la parte immaginaria** e facciamo *variare solo la parte reale*, come in figura:

```tikz
\usepackage{tikz}
\usepackage{amsmath}
\usetikzlibrary{arrows.meta, shapes.misc}

\begin{document}

\begin{tikzpicture}[
    cross/.style={draw, cross out, minimum size=4pt, inner sep=0pt, outer sep=0pt},
    >=Stealth
]

    % Assi Cartesiani
    \draw[->] (-7,0) -- (1,0) node[below left] {$\sigma$};
    \draw[->] (0,-4) -- (0,4) node[left] {$\omega$};

    % Linee tratteggiate orizzontali (omega costante)
    \def\omegaConst{2.5}
    \draw[dotted, thick] (-6.5, \omegaConst) -- (0, \omegaConst) node[right] {$\omega = \omega_n \sqrt{1-\xi^2}$};
    \draw[dotted, thick] (-6.5, -\omegaConst) -- (0, -\omegaConst);

    % Poli (le "X") - Variando sigma (parte reale)
    \foreach \x in {-1, -2.5, -4.5, -6} {
        \node[cross] at (\x, \omegaConst) {};
        \node[cross] at (\x, -\omegaConst) {};
    }

    % Proiezioni e vettori per un polo specifico (quello a x=-2.5)
    \coordinate (P) at (-2.5, \omegaConst);
    
    % Linea tratteggiata verticale
    \draw[dashed] (P) -- (-2.5, 0) node[below] {$\sigma = -\xi\omega_n$};
    
    % Vettore wn (ipotenusa)
    \draw[thick, ->] (0,0) -- (P) node[midway, above right, inner sep=1pt] {$\omega_n$};

    % Freccia rossa direzionale in basso (indica l'aumento di stabilità/sigma)
    \draw[black, ultra thick, <-] (-0.5, -3.5) -- (-5, -3.5);

\end{tikzpicture}

\end{document}
```

Avremmo in tal caso le seguenti *risposte al gradino*:

```tikz
\usepackage{pgfplots}

% Imposta la compatibilità per le versioni più recenti
\pgfplotsset{compat=1.16}

\begin{document}

\begin{tikzpicture}
    \begin{axis}[
        width=12cm, 
        height=9cm,
        xmin=0, xmax=20,
        ymin=0, ymax=2,
        grid=both,
        grid style={dashed, gray!50},
        ylabel={y(t)},
        xlabel={},
        % Definizione precisa dei tick per farli combaciare con l'immagine
        xtick={0,5,10,15,20},
        ytick={0,0.2,0.4,0.6,0.8,1.0,1.2,1.4,1.6,1.8,2.0},
        tick label style={font=\sffamily\bfseries},
        ylabel style={font=\sffamily\large},
        enlargelimits=false,
        samples=200, % Alto numero di campioni per curve morbide
        domain=0:20,
        thick
    ]

    % Frequenza naturale (stimata dal periodo delle oscillazioni del grafico)
    \def\wn{1.5}
    
    % 2. Curve con vari fattori di smorzamento (zeta)
    
    % zeta = 0.05 (Blu - molto sottosmorzato)
    \addplot[blue, thick] {1 - exp(-0.05*\wn*x)/sqrt(1-0.05^2)*sin(deg(\wn*sqrt(1-0.05^2)*x) + acos(0.05))};

    % zeta = 0.15 (Verde scuro)
    \addplot[green!50!black, thick] {1 - exp(-0.15*\wn*x)/sqrt(1-0.15^2)*sin(deg(\wn*sqrt(1-0.15^2)*x) + acos(0.15))};

    % zeta = 0.3 (Rosso)
    \addplot[red, thick] {1 - exp(-0.3*\wn*x)/sqrt(1-0.3^2)*sin(deg(\wn*sqrt(1-0.3^2)*x) + acos(0.3))};

    % zeta = 0.45 (Ciano)
    \addplot[cyan, thick] {1 - exp(-0.45*\wn*x)/sqrt(1-0.45^2)*sin(deg(\wn*sqrt(1-0.45^2)*x) + acos(0.45))};

    % zeta = 0.6 (Magenta)
    \addplot[magenta, thick] {1 - exp(-0.6*\wn*x)/sqrt(1-0.6^2)*sin(deg(\wn*sqrt(1-0.6^2)*x) + acos(0.6))};

    % zeta = 0.8 (Giallo scuro/Oliva)
    \addplot[olive, thick] {1 - exp(-0.8*\wn*x)/sqrt(1-0.8^2)*sin(deg(\wn*sqrt(1-0.8^2)*x) + acos(0.8))};

    % zeta = 1.0 (Grigio scuro - smorzamento critico, formula semplificata)
    \addplot[darkgray, thick] {1 - exp(-\wn*x)*(1 + \wn*x)};

    % 3. Freccia rossa che indica l'aumento di zeta
    \draw[-stealth, black, line width=2pt] (axis cs:2.15, 0.75) -- (axis cs:2.15, 2.0);

    \end{axis}
\end{tikzpicture}

\end{document}
```

>[!note] NOTA
>Spostando i *poli verso l'asse immaginario*  (cioè rendendo meno negativa la parte reale), il **transitorio diventa più lento** e *meno smorzato*. 
>L'**overshoot aumenta** e il tempo di assestamento cresce.

Infine, se **fissiamo la pulsazione naturale** $\omega_{n}$ (quindi il *modulo*) e facciamo variare la posizione dei poli come in figura:

```tikz
\begin{document}

\begin{tikzpicture}

    % Definisco il raggio della semicirconferenza (pulsazione naturale wn)
    \def\R{3}

    % Assi cartesiani
    \draw[thick, -stealth] (-\R-1, 0) -- (\R+1, 0) node[below=0.2cm, font=\large] {$\sigma$};
    \draw[thick, -stealth] (0, -\R-1) -- (0, \R+1) node[left=0.2cm, font=\large] {$\omega$};

    % Semicirconferenza tratteggiata (luogo dei poli a wn costante)
    \draw[thick, loosely dotted] (0, \R) arc (90:270:\R);

    % Poli (croci)
    % La distanza angolare tra le croci nel quadrante è simmetrica. 
    % Ci sono 5 intervalli da 18 gradi in ogni quadrante: da 90 a 180 gradi e da 180 a 270 gradi.
    \foreach \angle in {90, 108, 126, 144, 162, 180, 198, 216, 234, 252, 270} {
        \draw[thick, black] (\angle:\R) ++(-0.1,-0.1) -- ++(0.2,0.2);
        \draw[thick, black] (\angle:\R) ++(-0.1,0.1) -- ++(0.2,-0.2);
    }

    % Vettore dall'origine al polo (indicazione di omega_n)
    \draw[thick, -stealth] (0,0) -- (144:\R) node[midway, above right, font=\large] {$\omega_n$};

    % Etichette delle coordinate (parti reale e immaginaria)
    \node[font=\large] at (-\R/2, -0.4) {$\sigma = -\xi\omega_n$};
    \node[font=\large, right] at (0.2, \R) {$\omega = \omega_n\sqrt{1-\xi^2}$};

    \def\Rarrow{3.5}
    \draw[-stealth, thick, black] (175:\Rarrow) arc (175:75:\Rarrow);

\end{tikzpicture}

\end{document}
```

Otterremmo le seguenti risposte al gradino:

```tikz
\usepackage{pgfplots}

% Imposta la compatibilità
\pgfplotsset{compat=1.16}

\begin{document}

\begin{tikzpicture}
    \begin{axis}[
        width=12cm, 
        height=9cm,
        xmin=0, xmax=20,
        ymin=-0.5, ymax=2.5, % Limiti asse Y estesi come nell'immagine
        grid=both,
        grid style={dashed, gray!50},
        ylabel={y(t)},
        xlabel={},
        % Definizione precisa dei tick
        xtick={0,5,10,15,20},
        ytick={-0.5, 0, 0.5, 1.0, 1.5, 2.0, 2.5},
        tick label style={font=\sffamily\bfseries},
        ylabel style={font=\sffamily\large},
        enlargelimits=false,
        samples=200, 
        domain=0:20,
        thick
    ]

    % Pulsazione naturale stimata (dal periodo T=4 della curva gialla -> wn = 2*pi/4 = 1.57)
    \def\wn{1.5708}

    % 1. Curva Nera (Fortemente sovrasmorzato, zeta = 2.0)
    % Formula: y(t) = 1 - c1*exp(-L1*wn*t) + c2*exp(-L2*wn*t)
    \addplot[black, thick] {1 - 1.07735*exp(-0.26795*\wn*x) + 0.07735*exp(-3.73205*\wn*x)};

    % 2. Curva Blu (Sovrasmorzato, zeta = 1.2)
    \addplot[blue, thick] {1 - 1.4045*exp(-0.53668*\wn*x) + 0.4045*exp(-1.86332*\wn*x)};

    % 3. Curva Verde (Smorzamento critico, zeta = 1.0)
    \addplot[green!50!black, thick] {1 - exp(-\wn*x)*(1 + \wn*x)};

    % 4. Curva Rossa (Sottosmorzato, zeta = 0.8)
    \addplot[red, thick] {1 - exp(-0.8*\wn*x)/sqrt(1-0.8^2)*sin(deg(\wn*sqrt(1-0.8^2)*x) + acos(0.8))};

    % 5. Curva Ciano (Sottosmorzato, zeta = 0.5)
    \addplot[cyan, thick] {1 - exp(-0.5*\wn*x)/sqrt(1-0.5^2)*sin(deg(\wn*sqrt(1-0.5^2)*x) + acos(0.5))};

    % 6. Curva Magenta (Sottosmorzato con forte sovraelongazione, zeta = 0.3)
    \addplot[magenta, thick] {1 - exp(-0.3*\wn*x)/sqrt(1-0.3^2)*sin(deg(\wn*sqrt(1-0.3^2)*x) + acos(0.3))};

    % 7. Curva Gialla (Non smorzato / Oscillatore armonico, zeta = 0)
    \addplot[yellow!80!olive, thick] {1 - cos(deg(\wn*x))};

    % 8. Freccia azzurra (indica la diminuzione dello smorzamento)
    % Parte dalle curve lente e punta verso le oscillazioni rapide
    \draw[-stealth, black, line width=2pt] (axis cs:2.8, 0.45) -- (axis cs:0.5, 1.8);

    \end{axis}
\end{tikzpicture}

\end{document}
```

>[!note] NOTA
>A $\omega_{n}$ *costante*, **diminuendo lo smorzamento** $\xi$, **aumenta l'overshoot** e la **risposta si assesta molto più lentamente** (a $\xi=0$ *non si assesta*).
## RISPETTO DEI VINCOLI
Nel momento in cui bisogna progettare un sistema di controllo, si chiede spesso che:
1. La **massima sovraelongazione** non superi un certo **valore prefissato**.
2. Vi sia un **tempo massimo di assestamento**.
3. Vi sia un **massimo tempo di salita**.

Per rispettare questi vincoli, teniamo conto di quanto detto sopra, in > [[#LEGAME TRA I PARAMETRI E I POLI]].

Se vogliamo che il valore della **massima sovraelongazione** *non superari un certo* valore assegnato, i *poli del sistema* devono **essere compresi** nel *settore delimitato da due rette*.
Infatti:
$$
\begin{align*}
M_{p} &= \exp\left( -\frac{\pi \xi}{\sqrt{ 1-\xi^2 }} \right) \leq M_{p}^* \\ \\
&\implies \xi = \cos \varphi \geq - \frac{|\ln M_{p}^*|}{\sqrt{ \ln^2M_{p}^* + \pi^2 }} = \xi^*
\end{align*}
$$
Ovvero:
$$
\varphi \leq \arccos \xi^*
$$

```tikz
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}

    % Ritaglio per definire i limiti del grafico
    \clip (-5,-4) rectangle (4.5,4);

    % Bordi grigi spessi (metà di questi verrà coperta dal riempimento azzurro,
    % creando l'effetto di un bordo esterno)
    \draw[line width=16pt, gray!30] (0,0) -- (140:8);
    \draw[line width=16pt, gray!30] (0,0) -- (220:8);

    % Area azzurra chiara (sostituisce il verde)
    \fill[cyan!30] (0,0) -- (140:8) -- (-8,8) -- (-8,-8) -- (220:8) -- cycle;

    % Disegno degli assi (sovrapposti all'area colorata)
    \draw[-latex, thick] (-5,0) -- (3.5,0) node[below=0.1cm] {$\sigma$};
    \draw[-latex, thick] (0,-3.5) -- (0,3.5) node[left=0.1cm] {$\omega$};

    % Linee nere dei confini del settore
    \draw[thick] (0,0) -- (140:8);
    \draw[thick] (0,0) -- (220:8);

    % Etichette b e b' posizionate lungo le linee
    \node at (140:4) [below=0.15cm, left=0.05cm] {$b$};
    \node at (220:4) [above=0.15cm, left=0.05cm] {$b'$};

    % Arco e freccia per l'angolo
    \draw[<->, >=latex, thick] (-3,0) arc[start angle=180, end angle=140, radius=3];
    
    % Etichetta dell'angolo
    \node at (160:3.5) {$\varphi^*$};

    % Equazione nel quadrante in alto a destra
    \node[right] at (1.5, 2.2) {\large $\varphi^* = \arccos \xi^*$};

    % Numero della slide (opzionale, in basso a destra)
    \node[text=gray] at (4, -3.5) {\small 13};

\end{tikzpicture}
\end{document}
```

Se vogliamo che il **tempo di assestamento** *non superi un valore massimo* $T_{s}^*$ abbiamo:
$$
\begin{align*}
T_{s} &\approx \frac{3}{\xi\omega_{n}} < T_{s}^* \\ \\
\xi\omega_{n} &\geq \frac{3}{T_{s}^*}
\end{align*}
$$
Per cui deve essere:
$$
\sigma = -\xi\omega_{n} \leq -\frac{3}{T_{s}^*}
$$

Cioè i poli devono trovarsi *a sinistra di una retta verticale*:

```tikz
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}

    % Ritaglio per definire i limiti del grafico
    \clip (-6,-3.5) rectangle (3,3.7);

    % Bordo grigio spesso (metà di questo verrà coperta dal riempimento azzurro,
    % creando l'effetto della banda grigia a destra della linea)
    \draw[line width=16pt, gray!30] (-2.5,-4) -- (-2.5,4);

    % Area azzurra chiara (sostituisce il verde)
    \fill[cyan!30] (-6,-4) rectangle (-2.5,4);

    % Disegno degli assi (sovrapposti all'area colorata)
    \draw[-latex, thick] (-6,0) -- (2.5,0) node[below=0.1cm] {$\sigma$};
    \draw[-latex, thick] (0,-3.5) -- (0,3.5) node[left=0.1cm] {$\omega$};

    % Linea nera verticale di confine
    \draw[thick] (-2.5,-4) -- (-2.5,4);

    % Etichetta del punto sull'asse (posizionata in basso a sinistra rispetto all'incrocio)
    \node[below left=0.1cm] at (-2.5,0) {\Large $-\frac{3}{T_s^*}$};

\end{tikzpicture}
\end{document}
```

Infine, se vogliamo che il **tempo di salita** non superi un certo *valore massimo* $T_{r}^*$, abbiamo:
$$
\begin{align*}
T_{r} &\approx \frac{1.8}{\omega_{n}} \leq T_{s}^* \\ \\
\omega_{n} &\geq \frac{1.8}{T_{s}^*}
\end{align*}
$$
Cioè il *modulo dei poli* deve essere *maggiore di una certa soglia*.

```tikz
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}

    % Ritaglio per definire i limiti del grafico
    \clip (-5,-3.5) rectangle (2,3.5);

    % Area nel semipiano sinistro
    \fill[cyan!30] 
        (0, 3.5) -- 
        (-5, 3.5) -- 
        (-5, -3.5) -- 
        (0, -3.5) -- 
        (0, -1.5) arc [start angle=270, end angle=90, radius=1.5] -- 
        cycle;
        
    \fill[gray!30] 
        (0, -1.5) arc [start angle=270, end angle=90, radius=1.5] -- 
        (0, 1.5) -- 
        (0, 1.3) arc [start angle=90, end angle=270, radius=1.3] --
        cycle;

    % Disegno degli assi
    \draw[-latex, thick] (-4.5,0) -- (1.5,0) node[below=0.1cm] {$\text{Re}\{s\}$};
    \draw[-latex, thick] (0,-3) -- (0,3) node[right=0.1cm] {$\text{Im}\{s\}$};

    % Bordo del semicerchio
    \draw[thick] (0,1.5) arc [start angle=90, end angle=270, radius=1.5];

    % Formula in alto a sinistra
    \node at (-2.2, 1.3) {\Large $\omega_n \ge \frac{1.8}{T_r^*}$};

\end{tikzpicture}
\end{document}
```

Mettendo insieme *tutti questi vincoli* otteniamo una **suddivisione del piano complesso** in *due regioni*: un'**area ammissibile** in cui i poli rispettano i vincoli, ed una in cui invece i vincoli non sono rispettati, come in figura:

```tikz
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}[font=\sffamily, >=latex, scale = 0.8]

    % Definizione degli stili
    \tikzset{
        shadedregion/.style={fill=gray!40},
        boundary/.style={thick},
        axis/.style={->, thin},
        dashedbox/.style={dashed, thick, gray}
    }

    % ==========================================
    % Riquadro (a): Vincolo sulla parte reale
    % ==========================================
    \begin{scope}[shift={(-9, 7)}]
        % Area ombreggiata
        \fill[shadedregion] (-1.5, -3) rectangle (0.5, 3);
        % Linea di confine
        \draw[boundary] (-1.5, -3) -- (-1.5, 3);
        
        % Assi
        \draw[axis] (-3.5, 0) -- (1.5, 0) node[below right, scale=0.8] {$\text{Re}\{s\}$};
        \draw[axis] (0, -2.8) -- (0, 2.8) node[right, scale=0.8] {$\text{Im}\{s\}$};
        
        % Formula e Etichetta
        \node[left] at (-1.8, 1.5) {$\sigma = -\xi\omega_n \le -\dfrac{3}{T_s^*}$};
        \node at (-4.2, -2.5) {(a)};
        
        % Box tratteggiato
        \draw[dashedbox] (-4.5, -3) rectangle (1.5, 3);
    \end{scope}

    % ==========================================
    % Riquadro (b): Vincolo sulla pulsazione naturale
    % ==========================================
    \begin{scope}[shift={(-9, 0)}]
        % Area ombreggiata (interno del semicerchio sx)
        \fill[shadedregion] (0, 1.5) arc (90:270:1.5) -- cycle;
        % Linea di confine
        \draw[boundary] (0, 1.5) arc (90:270:1.5);
        \draw[boundary] (0, 1.5) -- (0, -1.5);
        
        % Assi
        \draw[axis] (-3.5, 0) -- (1.5, 0) node[below right, scale=0.8] {$\text{Re}\{s\}$};
        \draw[axis] (0, -2.8) -- (0, 2.8) node[right, scale=0.8] {$\text{Im}\{s\}$};
        
        % Formula e Etichetta
        \node[left] at (-1.8, 1.5) {$\omega_n \ge \dfrac{1.8}{T_r^*}$};
        \node at (-4.2, -2.5) {(b)};
        
        % Box tratteggiato
        \draw[dashedbox] (-4.5, -3) rectangle (1.5, 3);
    \end{scope}

    % ==========================================
    % Riquadro (c): Vincolo sullo smorzamento
    % ==========================================
    \begin{scope}[shift={(-9, -7)}]
        % Area ombreggiata (a destra delle linee diagonali)
        \fill[shadedregion] (0,0) -- (-1, 3) -- (1.5, 3) -- (1.5, -3) -- (-1, -3) -- cycle;
        % Linee di confine
        \draw[boundary] (0,0) -- (-1, 3);
        \draw[boundary] (0,0) -- (-1, -3);
        
        % Assi
        \draw[axis] (-3.5, 0) -- (1.5, 0) node[below right, scale=0.8] {$\text{Re}\{s\}$};
        \draw[axis] (0, -2.8) -- (0, 2.8) node[right, scale=0.8] {$\text{Im}\{s\}$};
        
        % Formula e Etichetta
        \node[left] at (-1.2, 1.5) {$\xi \ge \dfrac{|\ln(M_p^*)|}{\sqrt{\ln^2(M_p^*) + \pi^2}}$};
        \node at (-4.2, -2.5) {(c)};
        
        % Box tratteggiato
        \draw[dashedbox] (-4.5, -3) rectangle (1.5, 3);
    \end{scope}

    % ==========================================
    % Grafico Principale (d): Intersezione dei vincoli
    % ==========================================
    \begin{scope}[shift={(0, 0)}]
        % Ombreggiatura globale della regione inaccettabile
        \fill[shadedregion]
            (-2.666, 8) -- 
            (-1.5, 4.5) -- 
            (-1.5, 2.598) arc (120:240:3) -- 
            (-1.5, -4.5) -- 
            (-2.666, -8) -- 
            (5, -8) -- 
            (5, 8) -- cycle;
            
        % Bordo della regione (unione dei 3 vincoli base)
        \draw[boundary] 
            (-2.666, 8) -- 
            (-1.5, 4.5) -- 
            (-1.5, 2.598) arc (120:240:3) -- 
            (-1.5, -4.5) -- 
            (-2.666, -8);
            
        % Assi principali
        \draw[axis] (-4.5, 0) -- (6, 0) node[below right] {$\text{Re}\{s\}$};
        \draw[axis] (0, -8.5) -- (0, 8.5) node[right] {$\text{Im}\{s\}$};
        
        % Etichetta
        \node at (-2.3, -4.5) {(d)};
    \end{scope}

    % ==========================================
    % Frecce di collegamento
    % ==========================================
    % Da (a) alla linea verticale
    \draw[->, thick] (-7.5, 6) -- (-1.5, 3.5);
    
    % Da (b) all'arco di cerchio (doppia freccia come da disegno originale)
    \draw[->, thick] (-7.5, 1) -- ({-sqrt(8)}, 1); % Freccia alta sull'arco
    
    % Da (c) alla linea inclinata
    \draw[->, thick] (-7.5, -6) -- (-2, -6);

\end{tikzpicture}
\end{document}
```

**NOTA**: in figura, la *regione grigia* è quella **non ammissibile**.
### ESEMPIO
Assumiamo di voler rispettare le seguenti *specifiche desiderate*:
$$
\begin{align*}
T_{s,5\%} \leq 2\text{s} & & T_{r} \leq 0.8\text{s} & & M_{p} \leq 0.1
\end{align*}
$$
Le specifiche si traducono in:
$$
\begin{align*}
T_{s,5\%} &\approx \frac{3}{\xi\omega_{n}} < 2 \implies \mathrm{Re}(s) \leq -1.5 \\ \\
T_{r} &\approx \frac{1.8}{\omega_{n}} \leq 0.8 \implies \omega_{n} \geq 2.25 \\ \\
M_{p} &\leq 0.10 \implies \xi \geq -\frac{\ln(0.10)}{\sqrt{ \pi^2 + (\ln 0.10)^2 }} \approx 0.591
\end{align*}
$$
Possiamo allora scegliere, *per esempio*:
$$
\begin{align*}
\xi = 0.6 & & \omega_{n} = 3 \text{rad/s}
\end{align*}
$$
Per cui abbiamo:
$$
\begin{align*}
\mathrm{Re}(s) &= -\xi\omega_{n} = -0.6\cdot 3 = -1.8 \\ \\
\mathrm{Im}(s) &= \pm \omega_{n}\sqrt{ 1-\xi^2 } = \pm3\sqrt{ 1-0.6^2 } = \pm 2.4
\end{align*}
$$
E quindi i *poli scelti* sono:
$$
p_{1,2} = -1.8 \pm j2.4
$$

```tikz
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}[font=\sffamily, >=stealth]

% Definizione dei limiti e dei punti chiave
\def\xmin{-5}
\def\xmax{1.5}
\def\ymin{-4.5}
\def\ymax{4.5}

% Posizione dei poli (inalterata rispetto al testo dell'immagine)
\coordinate (P1) at (-1.8, 2.4);
\coordinate (P2) at (-1.8, -2.4);
\coordinate (O) at (0,0);

% Parametri aggiornati
% Raggio della circonferenza = 2.25
% Angolo: arccos(0.59) = 53.84 gradi -> 180 - 53.84 = 126.157 gradi
% Intersezione della retta obliqua con il bordo superiore (y = 4.5) -> x = -3.2883

% Riempimento dell'area grigia (Regione vietata)
\fill[gray!30] (\xmax, \ymax) -- (-3.2883, \ymax) -- (126.157:2.25) arc (126.157:233.843:2.25) -- (-3.2883, \ymin) -- (\xmax, \ymin) -- cycle;

% Riquadro tratteggiato esterno
\draw[dashed, thick] (\xmin, \ymin) rectangle (\xmax, \ymax);

% Assi
\draw[<->, thick] (\xmin, 0) -- (\xmax, 0) node[below left, xshift=-0.1cm] {Re$\{s\}$};
\draw[->, thick] (0, \ymin) -- (0, \ymax) node[below right, yshift=-0.1cm] {Im$\{s\}$};

% Nuova linea tratteggiata verticale in x = -1.5
\draw[dashed, thick] (-1.5, \ymin) -- (-1.5, \ymax);

% Linee tratteggiate dritte dall'origine verso l'inizio della regione ammissibile
\draw[dashed, thick] (O) -- (126.157:2.25);
\draw[dashed, thick] (O) -- (233.843:2.25);

% Archi di cerchio tratteggiati (continuazione della curva oltre i limiti di fase)
\draw[dashed, thick] (126.157:2.25) arc (126.157:90:2.25);
\draw[dashed, thick] (233.843:2.25) arc (233.843:270:2.25);

% Linee continue spesse di demarcazione delle regioni
\draw[very thick] (-3.2883, \ymax) -- (126.157:2.25);
\draw[very thick] (126.157:2.25) arc (126.157:233.843:2.25);
\draw[very thick] (233.843:2.25) -- (-3.2883, \ymin);

% Marcatori dei poli (X)
\draw[very thick] (-1.95, 2.55) -- (-1.65, 2.25);
\draw[very thick] (-1.95, 2.25) -- (-1.65, 2.55);
\draw[very thick] (-1.95, -2.25) -- (-1.65, -2.55);
\draw[very thick] (-1.95, -2.55) -- (-1.65, -2.25);

% Etichette di testo e coordinate
\node[above left, xshift=-2mm] at (P1) {$p_{1,2} = -1.8 \pm j2.4$};

\end{tikzpicture}
\end{document}
```

>[!note] NOTA
>Abbiamo scelto i poli **vicini al confine** tra le due regioni.
>Ciò viene fatto perchè, in pratica, i *parametri vanno aggiustati* dopo aver *testato il modello reale*, che non sarà *mai descritto perfettamente da quello teorico*.
## SISTEMI DEL SECONDO ORDINE CON UNO ZERO
Abbiamo visto in > [[#PRESENZA DI UNO ZERO]] come la presenza di *uno zero influenza un sistema del primo ordine*; vediamo ora anche l'influenza su un *sistema del secondo ordine*.

Consideriamo un **sistema del secondo** ordine caratterizzato dalla seguente FdT, con **uno zero** a numeratore:
$$
H(s) = \frac{\omega_{n}^2(1+\hat{T}s)}{s^2 +2\xi\omega_{n}s +\omega_{n}^2}
$$
Consideriamo la funzione $H_{2}$, cioè la FdT sopra *privata dello zero*:
$$
H_{2}(s) = \frac{\omega_{n}^2}{s^2 +2\xi\omega_{n}s +\omega_{n}^2}
$$
Notiamo allora che possiamo scrivere:
$$
H(s) = H_{2}(s) + \hat{T}sH_{2}(s)
$$
Supponiamo ora che l'ingresso del sistema $u(t)$ sia dato dal *gradino unitario*.
Allora:
$$
\begin{align*}
Y(s) &= H(s)U(s) = \frac{H_{2}(s)}{s} + \hat{T}s \frac{H_{2}(s)}{s} \\ \\
&= Y_{2}(s) + \hat{T}sY_{2}(s)
\end{align*}
$$
Applicando l'antitrasformata (ricordando che una *moltiplicazione* per $s$ si traduce in una *derivata*) troviamo:
$$
y(t) = y_{2}(t) + \hat{T}\dot{y}_{2}(t)
$$

>[!note] OSSERVAZIONE
>In questo caso, la risposta è data dalla *somma di due contributi*:
>1. La risposta "normale" del secondo ordine, *senza lo zero*.
>2. La *derivata della risposta normale*, scalata di un fattore dato dalla *costante di tempo* $\hat{T}$.

Calcoliamo il *valore a regime* (usando il *teorema del valore finale*):
$$
y_{\infty} = y(t\to \infty) = \lim_{ s \to 0 } sY(s) = \lim_{ s \to 0 } s \frac{1}{s}H(s) = 1
$$
Calcoliamo anche il *valore iniziale* (usando il *teorema del valore iniziale*):
$$
\begin{align*}
y(t \to 0^+) &= y(0) = y_{2}(0) + \hat{T}\dot{y}_{2}(0) = \\ \\
&= \lim_{ s \to \infty } sY_{2}(s) + \hat{T}\lim_{ s \to \infty } s(sY_{2}(s)) \\ \\
&= 0 + \hat{T}\cdot0 = 0
\end{align*} 
$$
Analogamente, calcoliamo anche il *valore iniziale della derivata* $\dot{y}(t)=\dot{y}_{2}(t)+\hat{T}\ddot{y}_{2}(t)$:
$$
\begin{align*}
\dot{y}(0) &= \lim_{ s \to \infty } s(sY_{2}(s)) + \hat{T}\lim_{ s \to \infty } s(s^2Y_{2}(s)) \\ \\
&= 0 + \hat{T}\omega_{n}^2
\end{align*}
$$
>[!idea] OSSERVAZIONE IMPORTANTE
>L'**andamento iniziale** della risposta *dipende* dalla costante di tempo $\hat{T}$, e quindi dallo **zero del sistema**.

Vediamo allora alcuni casi d'esempio, al *variare della posizione dello zero* **rispetto ai poli**.
### POLI REALI NEGATIVI E ZERO A PARTE REALE POSITIVA
Consideriamo $\hat{T}<0$.
Allora lo *zero* ha *parte reale positiva* e la posizione di poli e zeri nel piano complesso è del tipo in figura:

```tikz
\usepackage{amsmath}
\usetikzlibrary{shapes.misc}

\begin{document}
\begin{tikzpicture}[>=stealth, x=1.5cm, y=1.5cm]

    % Definizione dei colori e degli stili dei simboli
    \definecolor{axisgray}{HTML}{4D4D4D}
    \definecolor{polered}{HTML}{E31A1C}
    \definecolor{zeroblue}{HTML}{1F78B4}

    \tikzset{
        pole/.style={
            cross out,
            draw,
            thick,
            minimum size=4mm,
            inner sep=0pt,
            polered
        },
        zero/.style={
            circle,
            draw,
            thick,
            minimum size=4mm,
            inner sep=0pt,
            zeroblue
        }
    }

    % --- Coordinate principali ---
    \coordinate (Origin) at (0,0);
    \coordinate (P1) at (-3.5, 0); % Polo a sinistra (lontano)
    \coordinate (P2) at (-1.2, 0); % Polo a sinistra (vicino)
    \coordinate (Z1) at (1.2, 0);  % Zeros a destra

    % --- Disegno degli Assi ---
    % Asse Reale (Re)
    \draw[->, axisgray, thick] (-4, 0) -- (1.8, 0) node[right, text=black] {\large Re};
    % Asse Immaginario (Im)
    \draw[->, axisgray, thick] (0, -0.8) -- (0, 1.8) node[above, text=black] {\large Im};

    % --- Posizionamento dei Simboli (Poli e Zeri) ---
    \node[pole] at (P1) {};
    \node[pole] at (P2) {};
    \node[zero] at (Z1) {};

    % --- Annotazione di Testo ---
    % Il testo è posizionato a destra e leggermente in alto rispetto allo zero
    \node[right, zeroblue, font=\Large] at (0.6, 0.7) {$s_1 = z_1 = -\dfrac{1}{\hat{T}} > 0$};

    % --- Frecce di Misura (posizioni relative) ---
    % Freccia in alto: dall'origine verso l'estremità sinistra
    \draw[<->, black, thick] (-3.5, 1.1) -- (0, 1.1);

    % Frecce in basso: misurano le distanze dall'origine ai simboli
    % Da P2 all'origine
    \draw[<->, black, thick] (P2) ++(0, -0.6) -- ++(1.2, 0);
    
    % Dall'origine a Z1
    \draw[<->, black, thick] (Origin) ++(0, -0.6) -- ++(1.2, 0);

\end{tikzpicture}
\end{document}
```

E l'andamento della risposta al gradino è del seguente tipo:

```tikz
\usepackage{tikz}
\usepackage{pgfplots}
\usepackage{amsmath}

% Consigliato per le versioni recenti di pgfplots
\pgfplotsset{compat=1.16}

\begin{document}
\begin{tikzpicture}
    \begin{axis}[
        width=10cm,
        height=8cm,
        xmin=0, xmax=12,
        ymin=-1.5, ymax=1.0,
        xtick={0,2,4,6,8,10,12},
        ytick={-1.5,-1.0,-0.5,0,0.5,1.0},
        grid=both,
        major grid style={line width=0.5pt, draw=gray!60},
        xlabel={$t$},
        ylabel={$y(t)$},
        xlabel style={font=\Large},
        ylabel style={font=\Large},
        tick label style={font=\normalsize},
        legend style={
            at={(0.95,0.15)},
            anchor=south east,
            font=\normalsize,
            cells={anchor=west},
            nodes={inner xsep=1ex},
            draw=black,
        },
        % Imposta le linee della legenda più sottili rispetto a quelle del grafico
        legend image post style={line width=1pt},
        samples=200, % Alta risoluzione per curve morbide
        domain=0:12,
        axis line style={black, thick}
    ]

    % Curva per T = -1
    \addplot[blue, line width=2pt] {1 + 2*exp(-x) - 3*exp(-x/2)};
    \addlegendentry{$\hat{T} = -1$}

    % Curva per T = -3
    \addplot[color=green!75!black, line width=2pt] {1 + 4*exp(-x) - 5*exp(-x/2)};
    \addlegendentry{$\hat{T} = -3$}

    % Curva per T = -5
    \addplot[red!80!black, line width=2pt] {1 + 6*exp(-x) - 7*exp(-x/2)};
    \addlegendentry{$\hat{T} = -5$}

    \end{axis}
\end{tikzpicture}
\end{document}
```

>[!note] OSSERVAZIONE
>Siccome $\dot{y}(0)=\hat{T}\omega_{n}^2<0$, la risposta parte inizialmente con una **sottoelongazione** in *direzione opposta* rispetto al *regime permanente*.
### POLI REALI NEGATIVI E ZERO A PARTE REALE NEGATIVA (A DESTRA DEI POLI)
Consideriamo ora $\hat{T}>0$ tale che lo zero sia *a destra dei poli*, come in figura:

```tikz
\usepackage{amsmath}
\usepackage{tikz}
\usetikzlibrary{shapes.misc}

\begin{document}

\begin{tikzpicture}[>=stealth, x=1.5cm, y=1.5cm]
% Definizione dei colori e degli stili dei simboli
\definecolor{polered}{HTML}{E31A1C}
\definecolor{zeroblue}{HTML}{1F78B4}

\tikzset{
    pole/.style={
        cross out,
        draw,
        very thick,
        minimum size=4mm,
        inner sep=0pt,
        polered
    },
    zero/.style={
        circle,
        draw,
        very thick,
        minimum size=3.5mm,
        inner sep=0pt,
        zeroblue
    }
}

% --- Coordinate principali ---
\coordinate (Origin) at (0,0);
\coordinate (P1) at (-4.2, 0); % Polo a sinistra (lontano)
\coordinate (P2) at (-2.2, 0); % Polo a sinistra (vicino)
\coordinate (Z1) at (-0.5, 0); % Zero a sinistra (vicino all'origine)

% --- Disegno degli Assi ---
% Asse Reale (Re)
\draw[->, thick] (-4.8, 0) -- (0.8, 0) node[right, text=black] {\large Re};
% Asse Immaginario (Im)
\draw[->, thick] (0, -1) -- (0, 1.8) node[left, text=black] {\large Im};

% --- Posizionamento dei Simboli (Poli e Zeri) ---
\node[pole] at (P1) {};
\node[pole] at (P2) {};
\node[zero] at (Z1) {};

% --- Annotazione di Testo ---
% Il testo è posizionato sotto lo zero e leggermente traslato verso destra
\node[zeroblue, font=\large] at (0.1, -0.8) {$s_1 = z_1 = -\dfrac{1}{\hat{T}} < 0$};

\end{tikzpicture}

\end{document}
```

E l'andamento delle risposte al gradino è del tipo in figura:

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}
    \begin{axis}[
        width=10cm,
        height=8.5cm,
        xmin=0, xmax=15,
        ymin=0, ymax=2.5,
        xtick={0, 5, 10, 15},
        ytick={0, 0.5, 1, 1.5, 2, 2.5},
        tick align=inside,
        xtick pos=both,
        ytick pos=both,
        grid=major,
        grid style={dashed, gray!70},
        xlabel={$t$},
        ylabel={$y(t)$},
        xlabel style={font=\Large},
        ylabel style={font=\Large},
        ticklabel style={font=\large},
        enlargelimits=false,
        every axis plot/.append style={line width=1.5pt}
    ]

    % Curva verde: Risposta del sistema base (senza l'effetto di T_hat)
    \addplot[domain=0:15, samples=150, color=green] {1 - exp(-x)*(1+x)};
	\addlegendentry{senza zero}

    % Curve blu: Risposta del sistema con l'aggiunta dello zero
    % Curva blu inferiore (approssima l'effetto per T_hat più basso)
    \addplot[domain=0:15, samples=150, color=green!75!black] {1 - exp(-x) + 1.8*x*exp(-x)};
    \addlegendentry{$\hat{T}=4$}

    % Curva blu intermedia
    \addplot[domain=0:15, samples=150, color=red!90!black] {1 - exp(-x) + 3.0*x*exp(-x)};
	\addlegendentry{$\hat{T}=6$}

    % Curva blu superiore (approssima l'effetto per T_hat più alto)
    \addplot[domain=0:15, samples=150, color=blue] {1 - exp(-x) + 4.5*x*exp(-x)};
    \addlegendentry{$\hat{T}=8$}

    \end{axis}
\end{tikzpicture}
\end{document}
```

>[!note] OSSERVAZIONI
>- Ora $\hat{T}\omega_{n}^2>0$, per cui la risposta *parte nella direzione "giusta"*.
>- All'aumentare di $\hat{T}$, lo **zero si avvicina all'origine** e l'**overshoot aumenta**.
>  Questa situazione è simile a quella vista sopra, tuttavia ora lo zero ha *segno opposto*, e infatti abbiamo *sovraelongazione* anzichè *sottoelongazione*.
>- La risposta del sistema $H(s)$ **con lo zero** è molto **più brusca** di quella del sistema $H_{2}(s)$ senza lo zero.
### POLI REALI NEGATIVI E ZERO A PARTE REALE NEGATIVA (CON QUASI CANCELLAZIONE)
Consideriamo ora $\hat{T}>0$ tale da introdurre una *quasi-cancellazione* con il *polo più a destra*.
La posizione nel piano complesso è la seguente:

```tikz
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}[>=latex, scale=1.2]

    % Assi del piano complesso
    \draw[->, thick] (-5.5, 0) -- (0.8, 0) node[right] {\large Re};
    \draw[->, thick] (0, -1.2) -- (0, 2.5) node[left] {\large Im};

    % Definizione delle coordinate principali
    \def\pz{-2}      % Posizione dello zero e del polo cancellato
    \def\pDue{-4.6}  % Posizione del secondo polo più a sinistra
    \def\csize{0.12} % Dimensione dei bracci della croce (polo)

    % Secondo Polo (quello più a sinistra)
    \draw[red, very thick] (\pDue-\csize, -\csize) -- (\pDue+\csize, \csize);
    \draw[red, very thick] (\pDue-\csize, \csize) -- (\pDue+\csize, -\csize);

    % Primo Polo (s1) - Disegnato leggermente sfalsato a sinistra per replicare l'effetto visivo
    \def\px{\pz-0.12}
    \draw[red, very thick] (\px-\csize, -\csize) -- (\px+\csize, \csize);
    \draw[red, very thick] (\px-\csize, \csize) -- (\px+\csize, -\csize);

    % Zero (z1) - Sovrapposto al primo polo
    \draw[blue!70!white, ultra thick] (\pz, 0) circle (0.13);

    % Etichetta in basso
    % Utilizzo di un blu desaturato per avvicinarmi al colore originale dell'equazione
    \node[below, text=blue!40!gray!80!black] at (\pz-0.1, -0.3) {
        $s_1 = z_1 = -\frac{1}{\hat{T}} < 0$
    };

\end{tikzpicture}
\end{document}
```

E l'andamento della risposta al gradino è del tipo in figura:

```tikz
\usepackage{pgfplots}
\usepackage{amsmath}
\pgfplotsset{compat=1.16}

\begin{document}
\begin{tikzpicture}
\begin{axis}[
    width=11cm,
    height=8.5cm,
    xmin=0, xmax=3,
    ymin=0, ymax=1.1,
    xtick={0, 0.5, 1, 1.5, 2, 2.5, 3},
    ytick={0, 0.2, 0.4, 0.6, 0.8, 1},
    tick align=inside,
    grid=major,
    major grid style={solid, gray!40},
    xlabel={$t$},
    ylabel={$y(t)$},
    xlabel style={font=\Large},
    ylabel style={font=\Large, yshift=2mm},
    tick label style={font=\normalsize},
    axis line style={thick},
    enlargelimits=false,
    clip=false,
    legend pos = south east
]

% ==================== CURVE DEL GRAFICO ====================
% Curva rossa
\addplot[red!90!black, line width=1.2pt, domain=0:3, samples=150] {1 + 0.08*exp(-1.5*x) - 1.08*exp(-10*x)};
\addlegendentry{Quasi cancellazione (zero a destra del polo)}

% Curva verde
\addplot[green!75!black, line width=1.5pt, domain=0:3, samples=150] {1 - exp(-10*x)};
\addlegendentry{Cancellazione perfetta}

% Curva blu 
\addplot[blue, line width=1.5pt, domain=0:3, samples=150] {1 - 0.15*exp(-1.5*x) - 0.85*exp(-10*x)};
\addlegendentry{Quasi cancellazione (zero a sinistra del polo)}

\end{axis}
\end{tikzpicture}
\end{document}
```

>[!note] OSSERVAZIONE
>L'andamento di $y(t)$ è **inizialmente analogo** a quello di un **sistema del primo ordine** (governato dal polo veloce a sinistra).
>Col passare del tempo, tuttavia, può emergere un *contributo di piccola ampiezza* che si esaurisce con *velocità determinata dal polo quasi cancellato*: si parla di **coda di assestamento**, dovuta al fatto che la *cancellazione non è perfetta*. 
### ALTRE SITUAZIONI E RIASSUNTO
Qui sopra abbiamo visto solo alcune casistiche; chiaramente ne esistono altre (e anche situazioni in cui i poli non sono reali).

In particolare, restano da analizzare due casi importanti:
1. Zero **in mezzo ai poli**.
2. Zero **molto a sinistra**.

>[!idea] ZERO IN MEZZO AI POLI
>Il risultato è facilmente intuibile a partire dalla situazione precedente: continuiamo a *spostare lo zero a sinistra* del polo lento, ma *senza sorpassare il polo veloce*.
>In questo caso la **dinamica** del sistema diventa **molto più veloce** (rispetto al sistema senza lo zero), ma **senza generare overshoot**.

Osserviamo il seguente grafico per capire meglio:

```tikz
\usepackage{tikz}
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}

% Definizione dei colori della palette predefinita di Matplotlib (tab10)
\definecolor{tabblue}{HTML}{1f77b4}
\definecolor{taborange}{HTML}{ff7f0e}
\definecolor{tabgreen}{HTML}{2ca02c}

\begin{document}
\begin{tikzpicture}
    \begin{axis}[
        width=12cm, 
        height=9cm,
        xmin=0, xmax=10,
        ymin=0, ymax=1.1,
        xlabel={Tempo (s)},
        ylabel={\Large $y(t)$},
        xtick={0,1,2,3,4,5,6,7,8,9,10},
        ytick={0,0.2,0.4,0.6,0.8,1},
        grid=major,
        grid style={solid, gray!30},
        legend pos=south east,
        legend style={
            font=\footnotesize,
            cells={anchor=west},
            draw=black,
        },
        tick align=inside,
        tick style={draw=black, thin},
        enlargelimits=false
    ]

    % Curva xi = 1.5 (Verde): Sistema sovrasmorzato standard (Senza Zero)
    \addplot[domain=0:10, samples=100, thick, tabgreen] {1 - (1.17082 * exp(-0.381966 * x) - 0.17082 * exp(-2.618034 * x))};
    \addlegendentry{Senza Zero}

    % NUOVA CURVA (Arancione): Sistema con Zero in mezzo ai poli (z = -1.5)
    \addplot[domain=0:10, samples=100, thick, taborange] {1 - (0.872677 * exp(-0.381966 * x) + 0.127323 * exp(-2.618034 * x))};
    \addlegendentry{Con Zero tra i poli}

    % Linea tratteggiata per il valore a regime
    \addplot[domain=-0.5:12.5, samples=2, densely dashed, semithick, tabblue!80] {1};
    \addlegendentry{valore di regime}

    \end{axis}
\end{tikzpicture}
\end{document}
```

>[!idea] ZERO MOLTO A SINISTRA NEL PIANO COMPLESSO
>In tal caso la **costante di tempo** $\hat{T}=-\frac{1}{z}$ associata è **molto piccola**, per cui la dinamica introdotta è *lenta e trascurabile*.

Per fare un po' di ordine, riassumiamo di seguito le situazioni affrontate:

>[!tldr] EFFETTO DELLA PRESENZA DI UNO ZERO
>1. Molto **a sinistra dei poli**: effetto **trascurabile**.
>2. (Quasi) - **cancellazione** del **polo veloce**: dinamica simile a quella di un **sistema del primo ordine**, governata dal **polo lento**.
>3. A **metà fra i poli**: sistema **più rapido** di quello senza zero e **senza overshoot**.
>4. (Quasi) - **cancellazione** del **polo lento**: dinamica simile a quella di un **sistema del primo ordine**, governata dal **polo veloce**.
>5. A **destra dei poli** (nel semipiano **negativo**): effetto frusta e grande **sovraelongazione**.
>6. A **destra dei poli** (nel semipiano **positivo**): iniziale **sottoelongazione**.
# SISTEMI DI ORDINE SUPERIORE
Abbiamo accennato nell'introduzione che, in generale, un sistema di *ordine superiore al secondo* può essere scomposto in **contributi dati dai sistemi elementari**.

Consideriamo un sistema con funzione di trasferimento:
$$
G(s) = \frac{b_{m}s^m + b_{m-1}s^{m-1} + \ldots + b_{1}s + b_{0}}{a_{n}s^n + a_{n-1}s^{n-1} + \ldots + a_{1}s + a_{0}}
$$
Con $n\geq m$ (condizione di *causalità*).
Il sistema può essere scritto nella **forma fattorizzata di Evans** (in termini di *zeri e poli*):
$$
G(s) = K_{E} \frac{1}{s^\nu} \frac{\Pi^m_{i=1}(s-z_{i})}{\Pi^{n-\nu}_{i=1}(s-p_{i})}
$$
Oppure nella **forma fattorizzata di Bode** (in termini delle *costanti di tempo* e dei *coefficienti di smorzamento* e *pulsazioni naturali*):
$$
G(s) = K_{B} \frac{1}{s^\nu} \frac{ \Pi_{i=1}^{\hat{r}}(1+s\hat{T}_{i}) \Pi_{i=1}^\hat{c}\left( 1+\frac{2\hat{\xi}_{i}}{\hat{\omega}_{n,i}}s + \frac{s^2}{\hat{\omega}_{n,i}^2} \right) }{ \Pi_{i=1}^{r}(1+sT_{i}) \Pi_{i=1}^c\left( 1+\frac{2\xi_{i}}{\omega_{n,i}}s + \frac{s^2}{\omega_{n,i}^2} \right) }
$$
Dove:
1. $K_{B}=\lim_{ s \to 0 }s^\nu G(s)$ è il **guadagno di Bode**.
2. $T_{i}=\frac{1}{|p_{i}|}>0$, $\hat{T}_{i}=\frac{1}{|z_{i}|}$ sono le **costanti di tempo**.
3. $\omega_{n,i}>0$ sono le **pulsazioni naturali**.
4. $\xi_{i}, |\xi_{i}|\leq1$ sono i **coefficienti di smorzamento**.
## MODELLI APPROSSIMATI DI ORDINE RIDOTTO
Modelli di ordine elevato sono *particolarmente difficili da analizzare*; conviene allora lavorare su **sistemi approssimati di ordine ridotto**.

>[!idea] APPROSSIMAZIONE
>E' possibile ottenere un **modello approssimato di ordine ridotto**, con una *risposta al gradino simile a quella del sistema originale*, **trascurando eventuali coppie polo-zero vicine tra loro** nel piano complesso e **poli** sufficientemente **lontani dall'asse immaginario** (perchè la loro *dinamica* è *veloce e poco rilevante* nel comportamento globale del sistema).

La procedura per ottenere il sistema approssimato è quindi la seguente:
1. *Eliminare* questi *elementi non dominanti*.
2. Identificare i **poli dominanti**, ossia quelli **più vicini all'asse immaginario**.
3. Approssimare la *risposta al gradino* del sistema originale con quella del *sistema ridotto*, che ha lo **stesso guadagno statico**.

>[!warning] ATTENZIONE
>Bisogna fare attenzione ad alcune accortezze:
>1. La *riduzione d'ordine* deve **preservare il guadagno statico** se si vuole *mantenere lo stesso valore finale della risposta al gradino*.
>2. **Non** bisogna **cancellare** o trascurare **poli instabili** o **zeri nel semipiano destro**.
>3. La *rilevanza dei modi dinamici* dipende non solo dalla posizione dei poli, ma **anche da residui**, zeri e guadagno. 
>   Il modello ridotto andrebbe quindi sempre verificato confrontandone la risposta con quella del sistema originale.

Per capire meglio, consideriamo un sistema descritto dalla seguente FdT:
$$
G(s) = \frac{1200(s+20)}{(s+10)(s^2+0.1s+1.0025)(s^2+10s+425)}
$$
Abbiamo:
$$
\begin{align*}
z_{1} &= -20 \\ \\
p_{1} &= -10 \\ \\
p_{2,3} &= 0.05 \pm j1 \\ \\
p_{4,5} &= -5 \pm j20
\end{align*}
$$
Vediamoli rappresentati in una *mappa zeri-poli*:

```tikz
\usepackage{pgfplots}
\usepackage{amsmath}

\pgfplotsset{compat=1.16}
\definecolor{matlabblue}{RGB}{0,114,189}

\begin{document}

\begin{tikzpicture}
\begin{axis}[
    width=12cm,
    height=10cm,
    xmin=-30, xmax=10,
    ymin=-22, ymax=22,
    axis lines=middle,
    xlabel={Real Axis},
    ylabel={Asse immaginario},
    grid=none,
    tick align=inside,
    set layers,
    title={Mappa zeri-poli}
]

% --- 1. GRIGLIA FREQUENZA NATURALE (Semicerchi omega_n) ---
\pgfplotsinvokeforeach{5, 10, 15, 20, 25}{
    \addplot [forget plot, domain=90:270, samples=100, gray!40, dashed, thin] 
    ({#1*cos(x)}, {#1*sin(x)});
    % Etichette sui cerchi
}

% --- 2. GRIGLIA SMORZAMENTO (Raggi zeta) ---
\pgfplotsinvokeforeach{0.16, 0.34, 0.5, 0.64, 0.76, 0.86, 0.94, 0.985}{
    % Calcolo dell'angolo in gradi
    \pgfmathsetmacro{\angleZ}{acos(#1)}
    
    % Raggio superiore
    \addplot [gray!30, dashed, thin, forget plot] coordinates {
        (0,0) 
        ({40*cos(180-\angleZ)}, {40*sin(180-\angleZ)})
    };
    
    % Raggio inferiore
    \addplot [gray!30, dashed, thin, forget plot] coordinates {
        (0,0) 
        ({40*cos(180+\angleZ)}, {40*sin(180+\angleZ)})
    };
}

% --- 3. POLI E ZERI ---

% Zero: z1 = -20
\addplot [only marks, mark=o, mark options={scale=2, line width=1.5pt}, color=matlabblue] 
coordinates {(-20, 0)};

% Polo reale: p1 = -10
\addplot [only marks, mark=x, mark options={scale=2.5, line width=1.5pt}, color=matlabblue] 
coordinates {(-10, 0)};

% Poli complessi dominanti: p2,3 = -0.05 +/- j1
\addplot [only marks, mark=x, mark options={scale=2.5, line width=1.5pt}, color=matlabblue] 
coordinates {(-0.05, 1) (-0.05, -1)};

% Poli complessi ad alta frequenza: p4,5 = -5 +/- j20
\addplot [only marks, mark=x, mark options={scale=2.5, line width=1.5pt}, color=matlabblue] 
coordinates {(-5, 20) (-5, -20)};

% --- 4. EVIDENZIATORE GIALLO (Poli dominanti) ---
\fill[yellow, opacity=0.3] (-1, -3) rectangle (1, 3);

\end{axis}
\end{tikzpicture}

\end{document}
```

>[!note] OSSERVAZIONE
>Notiamo che $p_{1}$ e $z_{1}$ sono *sufficientemente lontani dall'asse immaginario* da *poter essere trascurati*. Inoltre, i poli $p_{2,3}$ risultano *dominanti* rispetto a $p_{4,5}$.

Allora possiamo la **FdT approssimante**:
$$
G_{a}(s) = \frac{G(0)}{1+\frac{0.1}{1.0025}s + \frac{s^2}{1.0025}}
$$
Con:
$$
G(0) = \frac{1200\cdot 20}{10\cdot 1.025\cdot 425} = 5.633
$$
Il grafico seguente ci conferma che l'*approssimazione è buona*:

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}

\begin{document}

\begin{tikzpicture}
\begin{axis}[
    title={Risposte al gradino},
    xlabel={$t$},
    ylabel={},
    xmin=0, xmax=100,
    ymin=0, ymax=12,
    grid=both,
    grid style={dashed, gray!30},
    legend pos=north east,
    width=10cm,
    height=8cm,
    tick label style={font=\small},
    title style={font=\bfseries}
]

% Definizione parametri per ricalcare il grafico:
% K (guadagno statico) = 5.6
% zeta (smorzamento) = 0.05
% wn (frequenza naturale) = 1.0
\def\K{5.62}
\def\z{0.045}
\def\wn{0.95}

% Curva Blu (G) - Continua e spessa
\addplot [
    blue!50,
    line width=2pt,
    domain=0:100,
    samples=400
] { \K*(1 - (1/sqrt(1-\z^2))*exp(-\z*\wn*x)*sin(deg(sqrt(1-\z^2)*\wn*x) + acos(\z))) };
\addlegendentry{G}

% Curva Rossa (Ga) - Tratteggiata sopra la blu
\addplot [
    red!80!black,
    dashed,
    line width=1.5pt,
    domain=0:100,
    samples=400
] { \K*(1 - (1/sqrt(1-\z^2))*exp(-\z*\wn*x)*sin(deg(sqrt(1-\z^2)*\wn*x) + acos(\z))) };
\addlegendentry{G$_a$}

\end{axis}
\end{tikzpicture}

\end{document}
```
# FEDELTA' DELLA RISPOSTA
Ricordiamo che l'*obiettivo del controllo* è il seguente:

>[!def] OBIETTIVO DEL CONTROLLO
>Far sì che il **sistema segua il riferimento** desiderato nel modo *più fedele possibile*.

Ciò significa **minimizzare l'errore** $e(t)$ **tra l'uscita** $y(t)$ ed il **riferimento** $r(t)$.
*Fedeltà della risposta* può voler dire:
- *Mantenere* il *valore di riferimento costante* (problema di **regolazione**).
- *Seguire* un *riferimento variabile* (**tracking**).
- *Resistere* all'*effetto di disturbi* e incertezze nel modello (**robustezza**).

>[!idea] PRESTAZIONI VS ROBUSTEZZA
>Una *buona prestazione* (es. *risposta veloce e precisa*) può **entrare in conflitto** con la *robustezza* (capacità di funzionare bene anche con errori nel modello o disturbi).
>Solitamente, la **soluzione** di controllo **migliore** è un **compromesso** tra queste esigenze.

In un sistema di controllo, l’obiettivo principale è *ottenere un errore il più piccolo possibile*, idealmente nullo (**controllo ottimo**). 
Questo obiettivo *si traduce* in un **insieme di specifiche di progetto**, che guidano la progettazione del controllore. 

>[!def] SPECIFICHE DI PROGETTO
>Sono **criteri numerici** (o anche *qualitativi*) imposti alla **risposta del sistema**.
>Tipicamente includono:
>1. Specifiche **a regime**, cioè nel lungo periodo, a *transitorio esaurito*.
>2. Specifiche **dinamiche**, durante il *transitorio* (es. *sovraelongazione*, *tempo di salita* e di *assestamento*).

Solitamente si considerano *ingressi* per $W(s)$, cioè **riferimenti** $r(t)$, **canonici** che hanno *andamento polinomiale* $t^h$, di *ordine* $h$:
Si usano quindi:
1. **Gradino unitario**, *ordine zero*: $\delta_{-1}(t)$ ($\mathcal{L}\to \frac{1}{s}$) (controllo in *posizione*).
2. **Rampa unitaria**, *ordine uno*: $\delta_{-2}(t)$ ($\mathcal{L}\to \frac{1}{s^2}$) (controllo in *velocità*).
3. **Rampa parabolica**, *ordine due*: $\delta_{-3}(t)$ ($\mathcal{L}\to \frac{1}{s^3}$) (controllo in *accelerazione*).
## ERRORE A REGIME - TIPO DI UN SISTEMA
Consideriamo un sistema con *FdT* ad *anello chiuso* $W(s)$ *razionale, strettamente propria* e **BIBO stabile**.

>[!def] SISTEMA DI TIPO H
>Un sistema come quello sopra si dice **di tipo h** se la **FdT in anello aperto** $L(s)$ ha **esattamente h poli in zero** (cioè nell'origine).

Consideriamo allora $\tilde{L}(s)$ tale che:
$$
L(s) = \frac{\tilde{L}(s)}{s^h} = s^{-h}\tilde{L}(s)
$$
E' importante il seguente teorema:

>[!th] TEOREMA
>Dato un **sistema di tipo h** ed un **riferimento di ordine h'**, abbiamo che l'**errore a regime** $e(t)=r(t)-y(t)$:
>1. E' **finito e costante** se $h'=h$.
>2. E' **nullo** se $h'<h$.
>3. E' **tendente all'infinito** se $h'>h$.

Se il sistema in feedback è di **tipo 0**, per $r(t)=\delta_{-1}(t)$ abbiamo:
$$
\begin{align*}
e_{0} &= e(t\to \infty) = \lim_{ s \to 0 } s \frac{1}{s} \frac{1}{1+L(s)} \\ \\
&= \frac{1}{1+L(0)} = \frac{1}{1+\tilde{L}(0)}
\end{align*}
$$
Se il sistema in feedback è di **tipo 1**, per $r(t)=\delta_{-2}(t)$ abbiamo:
$$
\begin{align*}
e_{1} &= e(t\to \infty) = \lim_{ s \to 0 } s \frac{1}{s^2} \frac{1}{1+L(s)} \\ \\
&= \lim_{ s \to 0 } \frac{1}{s} \frac{1}{1+ \frac{\tilde{L}(s)}{s}} = \frac{1}{\tilde{L}(0)}
\end{align*}
$$
Se il sistema in feedback è di **tipo 2**, per $r(t)=\delta_{3}(t)$ abbiamo:
$$
\begin{align*}
e_{2} &= e(t\to \infty) = \lim_{ s \to 0 } s \frac{1}{s^3} \frac{1}{1+s^{-2}\tilde{L}(s)} \\ \\
&= \ldots = \frac{1}{\tilde{L}(0)}
\end{align*}
$$
>[!note] NOTA
>Abbiamo così verificato (per i primi valori di $h$) che, se $h=h'$ l'**errore è finito e costante**.
>La seguente tabella completa lo studio dei casi $h=0,1,2$.

```tikz
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}[x=1cm, y=1cm, >=stealth]

    % --- Impostazioni Generali della Griglia ---
    \def\cw{3.8}  % Larghezza delle colonne
    \def\rh{3.4}  % Altezza delle righe
    \def\hh{1.0}  % Altezza dell'intestazione

    % Bordi esterni (aggiustati per coprire esattamente 3 righe)
    \draw[thin] (0, \hh) rectangle (4*\cw, -3*\rh);

    % Linee di separazione verticali
    \draw[very thick, red] (\cw, \hh) -- (\cw, -3*\rh); % Linea rossa spessa
    \draw[thin] (2*\cw, \hh) -- (2*\cw, -3*\rh);
    \draw[thin] (3*\cw, \hh) -- (3*\cw, -3*\rh);

    % Linea di separazione orizzontale sotto le intestazioni
    \draw[thin] (0, 0) -- (4*\cw, 0);
    
    % (Opzionale) Decommenta le righe seguenti se vuoi le linee orizzontali interne
    % \draw[thin, gray!50] (0, -\rh) -- (4*\cw, -\rh);
    % \draw[thin, gray!50] (0, -2*\rh) -- (4*\cw, -2*\rh);

    % --- Intestazioni ---
    \node[font=\itshape] at (0.5*\cw, 0.5*\hh) {INGRESSO};
    \node[font=\itshape] at (1.5*\cw, 0.5*\hh) {SISTEMA TIPO 0};
    \node[font=\itshape] at (2.5*\cw, 0.5*\hh) {SISTEMA TIPO 1};
    \node[font=\itshape] at (3.5*\cw, 0.5*\hh) {SISTEMA TIPO 2};

    % Macro per disegnare gli assi coordinati in ogni cella
    \newcommand{\drawaxis}{
        \draw[->, very thin] (0.4, 0.4) -- (\cw-0.4, 0.4) node[above left, inner sep=1pt] {$t$};
        \draw[->, very thin] (0.4, 0.4) -- (0.4, \rh-0.4);
    }

    % ==========================================
    % RIGA 1: INGRESSO A GRADINO (h = 0)
    % ==========================================
    \def\cury{-\rh} % <--- CORREZIONE: partiamo dal "fondo" della riga 1
    
    % Colonna 0 (Ingresso)
    \begin{scope}[shift={(0, \cury)}]
        \drawaxis
        \draw[thick] (0.4, 2.0) -- (\cw-0.6, 2.0);
        \node[font=\sffamily] at (0.5*\cw, 1.4) {ordine h = 0};
    \end{scope}

    % Colonna 1 (Tipo 0)
    \begin{scope}[shift={(\cw, \cury)}]
        \drawaxis
        \draw[densely dashed] (0.4, 2.0) -- (\cw-0.6, 2.0);
        \node[above] at (1.0, 2.0) {$r(t)$}; % Etichetta r(t) manuale
        
        \draw[thick] (0.4, 0.4) to[out=80, in=180] (1.6, 1.6) -- (\cw-0.6, 1.6);
        
        \draw[<->, thin] (2.8, 1.6) -- (2.8, 2.0);
        \node[red, fill=white, inner sep=1pt] at (2.0, 1.1) {$e_0 = \frac{1}{1+\tilde{L}(0)}$};
    \end{scope}

    % Colonna 2 (Tipo 1)
    \begin{scope}[shift={(2*\cw, \cury)}]
        \drawaxis
        \draw[densely dashed] (0.4, 2.0) -- (\cw-0.6, 2.0);
        \draw[thick] (0.4, 0.4) to[out=80, in=180] (1.8, 2.0) -- (\cw-0.6, 2.0);
        \node[red] at (2.4, 2.4) {$e_0 = 0$};
    \end{scope}

    % Colonna 3 (Tipo 2)
    \begin{scope}[shift={(3*\cw, \cury)}]
        \drawaxis
        \draw[densely dashed] (0.4, 2.0) -- (\cw-0.6, 2.0);
        \draw[thick] (0.4, 0.4) to[out=85, in=180] (1.2, 2.0) -- (\cw-0.6, 2.0);
        \node[red] at (2.4, 2.4) {$e_0 = 0$};
    \end{scope}

    % ==========================================
    % RIGA 2: INGRESSO A RAMPA (h = 1)
    % ==========================================
    \def\cury{-2*\rh} % <--- CORREZIONE: partiamo dal "fondo" della riga 2
    
    % Colonna 0 (Ingresso)
    \begin{scope}[shift={(0, \cury)}]
        \drawaxis
        \draw[thick] (0.4, 0.4) -- (3.0, 2.6);
        \node[rotate=40, font=\sffamily] at (1.4, 1.7) {ordine h = 1};
    \end{scope}

    % Colonna 1 (Tipo 0)
    \begin{scope}[shift={(\cw, \cury)}]
        \drawaxis
        \draw[densely dashed] (0.4, 0.4) -- (3.0, 2.6);
        \draw[thick] (0.4, 0.4) to[out=0, in=230] (3.1, 1.6);
        \node[red] at (1.8, 2.4) {$e_1 = \infty$};
    \end{scope}

    % Colonna 2 (Tipo 1)
    \begin{scope}[shift={(2*\cw, \cury)}]
        \drawaxis
        \draw[densely dashed] (0.4, 0.4) -- (3.0, 2.6);
        \node[above left] at (1.5, 1.5) {$r(t)$};
        
        \draw[thick] (0.4, 0.4) to[out=0, in=218] (1.2, 0.6) -- (3.0, 2.12);
        \node[below right] at (2.1, 1.6) {$y(t)$};
        
        \draw[<->, thin] (2.2, 1.93) -- (2.2, 1.45);
        \node[red, fill=white, inner sep=1pt] at (2.6, 2.5) {$e_1 = \frac{1}{\tilde{L}(0)}$};
    \end{scope}

    % Colonna 3 (Tipo 2)
    \begin{scope}[shift={(3*\cw, \cury)}]
        \drawaxis
        \draw[densely dashed] (0.4, 0.4) -- (3.0, 2.6);
        \draw[thick] (0.4, 0.4) to[out=0, in=220] (1.2, 0.7) to[out=40, in=218] (2.2, 1.92) -- (3.0, 2.6);
        \node[red] at (2.4, 2.5) {$e_1 = 0$};
    \end{scope}

    % ==========================================
    % RIGA 3: INGRESSO A PARABOLA (h = 2)
    % ==========================================
    \def\cury{-3*\rh} % <--- CORREZIONE: partiamo dal "fondo" della riga 3
    
    % Colonna 0 (Ingresso)
    \begin{scope}[shift={(0, \cury)}]
        \drawaxis
        \draw[thick] (0.4, 0.4) parabola (3.0, 2.8);
        \node[rotate=55, font=\sffamily] at (1.2, 1.7) {ordine h = 2};
    \end{scope}

    % Colonna 1 (Tipo 0)
    \begin{scope}[shift={(\cw, \cury)}]
        \drawaxis
        \draw[densely dashed] (0.4, 0.4) parabola (3.0, 2.8);
        \draw[thick] (0.4, 0.4) parabola (3.1, 1.5);
        \node[red] at (1.8, 2.2) {$e_2 = \infty$};
    \end{scope}

    % Colonna 2 (Tipo 1)
    \begin{scope}[shift={(2*\cw, \cury)}]
        \drawaxis
        \draw[densely dashed] (0.4, 0.4) parabola (3.0, 2.8);
        \draw[thick] (0.4, 0.4) parabola (3.1, 1.7);
        \node[red] at (2.2, 1.8) {$e_2 = \infty$};
    \end{scope}

    % Colonna 3 (Tipo 2)
    \begin{scope}[shift={(3*\cw, \cury)}]
        \drawaxis
        \draw[densely dashed] (0.4, 0.4) parabola (3.0, 2.8);
        \node[above left] at (1.5, 1.3) {$r(t)$};
        
        \draw[thick] (0.4, 0.4) to[out=0, in=250] (3.0, 2.2);
        \node[right] at (2.9, 1.8) {$y(t)$};
        
        \draw[<->, thin] (2.6, 2.1) -- (2.6, 1.4); % Intervallo errore a regime
        \node[red, fill=white, inner sep=1pt] at (2.1, 2.6) {$e_2 = \frac{1}{\tilde{L}(0)}$};
    \end{scope}

\end{tikzpicture}
\end{document}
```

>[!warning] ATTENZIONE
>Tutte queste considerazioni sono fatto con l'**assunzione che il sistema sia BIBO stabile**.
>Si potrebbe pensare di *aggiungere tanti poli nell'origine* con il controllore, ma bisogna tenere conto che l'*aggiunta di tali poli* rende *molto più complicato il controllo del sistema*, che *rischia di diventare non più BIBO stabile*.
## ERRORE TRANSITORIO
*Quanto velocemente* si avvicina il sistema all'*obiettivo desiderato*?
Dato un sistema in retroazione unitaria negativa **BIBO stabile**, si considera in generale un *riferimento a gradino*.

```tikz
\usepackage{amsmath}
\usepackage{xcolor}
\usetikzlibrary{positioning, arrows.meta, calc}

\begin{document}
\begin{tikzpicture}[
    >=stealth, % Stile delle frecce
    block/.style={
        rectangle,
        draw=gray!80,
        fill=gray!20,
        thick,
        minimum width=2cm,
        minimum height=1.5cm,
        align=center
    },
    sum/.style={
        circle,
        draw=gray!80,
        thick,
        inner sep=0pt,
        minimum size=0.4cm
    },
    line/.style={
        draw=gray!80,
        thick,
        ->
    },
    scale  = 1
]

% --- 1. Grafico a gradino (Sinistra) ---
\begin{scope}[shift={(0, 0.5)}]
    % Assi
    \draw[-latex] (-1.5,0) -- (1.5,0) node[below left] {\tiny $t$};
    \draw[-latex] (0,-0.5) -- (0,1.5);
    
    % Etichette assi
    \node[left] at (0,1) {\tiny $1$};
    \node[below left=-0.05cm] at (0,0) {\tiny $0$};
    \node[left] at (-0.5,1) {$\delta_{-1}(t)$};
    
    % Funzione a gradino (linea spessa nera)
    \draw[very thick] (-1.2,0) -- (0,0);
    \draw[very thick] (0,0) -- (0,1);
    \draw[very thick] (0,1) -- (1.3,1);
\end{scope}

% --- 2. Schema a Blocchi (Destra) ---
\begin{scope}[shift={(5, 1)}] 
    % Nodi
    \node (input) at (-1.5, 0) {$\delta_{-1}(t) = r(t)$};
    \node[sum] (sum) at (0,0) {};
    
    \node[block, right=1.2cm of sum] (controller) {
        \textcolor{green!60!black}{\small\textsf{Controllore}}\\[0.1cm]
        $C$
    };
    
    \node[block, right=1.5cm of controller] (plant) {$G$};
    
    \node (output) [right=1.5cm of plant] {$y(t)$};
    
    % Collegamenti principali
    \draw[line] (input) -- (sum) node[pos=0.8, above] {\scriptsize $+$};
    \draw[line] (sum) -- (controller) node[midway, above] {$e(t)$};
    \draw[line] (controller) -- (plant) node[midway, above] {$u(t)$};
    \draw[line] (plant) -- (output);
    
    % Ramo di feedback
    \coordinate (split) at ($(plant.east) + (0.8,0)$); % Punto di diramazione
    \filldraw[gray!80] (split) circle (1.5pt); % Pallino grigio di connessione
    
    \draw[line] (split) -- ++(0,-1.6) -| (sum)
        node[pos=0.25, above] {\textcolor{red!80!black}{\textsf{feedback}}}
        node[pos=0.95, left] {\scriptsize $-$};
        
\end{scope}

\end{tikzpicture}
\end{document}
```

Sappiamo che:
$$
W(s) = \frac{Y(s)}{R(s)} = \frac{L(s)}{1+L(s)}
$$
Possiamo scrivere l'errore come *somma di due contributi*:
$$
e(t) = e_{\infty} + e_{t}(t)
$$
Dove:
- $e_{\infty}$ è l'**errore a regime**.
- $e_{t}(t)$ è l'**errore durante il transitorio**.

Sappiamo che la *FdT dell'errore* è:
$$
W_{e} = \frac{1}{1+L(s)}
$$
Abbiamo quindi:
$$
E(s) = \frac{1}{s}e_{\infty} + E_{t}(s) = \frac{1}{1+L(s)}R(s) = \frac{1}{1+L(s)} \frac{1}{s}
$$
Allora segue che:
$$
E_{t}(s) = \frac{1}{1+L(s)} \frac{1}{s} - \frac{1}{s}e_{\infty}
$$
Che scriviamo come segue:
$$
E_{t}(s) = \sum_{i=1}^r \sum_{k=1}^{m_{i}} \frac{a_{i,k}}{(s-p_{i})^k}
$$
Per cui troviamo:
$$
\begin{align*}
e_{t}(t) &= \mathcal{L}^{-1}[E_{t}(s)] \\ \\
&= \sum_{i=1}^r \sum_{k=1}^{m_{i}} a_{i,k} \frac{t^{k-1}}{(k-1)!}e^{ p_{i}t }\delta_{-1}(t)
\end{align*}
$$
>[!idea] OSSERVAZIONE IMPORTANTE
>L'errore transitorio $e_{t}(t)$ è **combinazione lineare** dei **modi naturali** associati ai **poli della funzione di sensibilità** $S(s)=\frac{1}{1+L(s)}$ (Vedi > [[20 - PROBLEMA DI CONTROLLO#LIMITI E COMPROMESSI]]).

Per ottenere una *risposta rapida* e **contenere la durata dell'errore transitorio**, si impone che *tutti i poli del sistema a ciclo chiuso* abbiano **parte reale minore di un valore assegnato** (negativo), cioè, dato $\alpha \in \mathbb{R}^+$:
$$
\mathrm{Re}(p_{i}) < -\alpha \text{ }\forall p_{i}
$$
Il parametro $\alpha$ rappresenta il *livello minimo desiderato* di *rapidità nella risposta*.
Questo garantisce che:
$$
|e_{t}(t)| \leq Ke^{ -\alpha t }
$$
Per qualche costante $K>0$.
## DALLA FEDELTA' DELLA RISPOSTA AL PROGETTO DEL CONTROLLORE
Abbiamo visto sopra (In > [[#RISPETTO DEI VINCOLI]]) che **prestazioni desiderate** della risposta si possono **tradurre in specifiche** che impongono dei **vincoli sulla posizione dei poli** della FdT ad anello chiuso $W(s)$.
Sappiamo che:
$$
\begin{align*}
L(s) = \frac{Y(s)}{E(s)} = C(s)G(s) & & W(s) = \frac{Y(s)}{R(s)} = \frac{L(s)}{1+L(s)}
\end{align*}
$$
Per cui il **controllore** $C(s)$ deve essere progettato in modo da garantire che i **poli** di $W(s)$ (che in assenza di cancellazioni sono gli *zeri* di $1+L(s)$) **rispettino i vincoli** dati dalle specifiche richieste.

Riassumendo:

>[!tldr] PROGETTO DEL CONTROLLORE
>La progettazione deve raggiungere un **compromesso** tra *prestazioni, limiti fisici e robustezza*.
>In particolare, deve tenere conto di:
>1. Aspetto **a regime**: precisione nel *lungo periodo*, *tipo* del sistema, guadagno a *bassa frequenza*.
>2. Aspetto **transitorio**: *qualità* e *rapidità* della risposta, *sovraelongazione*, tempo di *salita*, tempo di *assestamento*.
