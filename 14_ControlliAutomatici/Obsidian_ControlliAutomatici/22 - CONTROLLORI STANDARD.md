# INDICE SEZIONE
- [ ] [[#CONTROLLORE STANDARD PID]]
- [ ] [[#CONTROLLORE PROPORZIONALE]]
      - [[#ESEMPIO CONTROLLORE P]]
- [ ] [[#CONTROLLORE PROPORZIONALE - INTEGRALE]]
      - [[#ESEMPIO CONTROLLORE PI]]
- [ ] [[#CONTROLLORE PROPORZIONALE - DERIVATIVO]]
      - [[#ESEMPIO CONTROLLORE PD]]
- [ ] [[#CONTROLLORE PROPORZIONALE - INTEGRALE - DERIVATIVO]]
      - [[#EFFETTO DEI TRE PARAMETRI]]
- [ ] [[#PROGETTO DEL CONTROLLORE]]
# CONTROLLORE STANDARD PID
Abbiamo visto che lo *schema a blocchi* per un sistema di controllo *in retroazione* è il seguente:

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

\end{tikzpicture}
\end{document}
```

In moltissimi casi, il blocco di controllo $C(s)$ è costituito da un **controllore standard**.

>[!def] CONTROLLORE STANDARD
>Il *controllore standard* è formato dalla *combinazione* delle seguenti azioni:
>- **Proporzionale** (P).
>- **Integrale** (I).
>- **Derivativa** (D).

Cerchiamo di capire il **ruolo** di ciascuna delle tre azioni.

```tikz
\usepackage{amsmath}
\usetikzlibrary{patterns, arrows.meta}

\begin{document}
\begin{tikzpicture}[x=1.5cm, y=1.5cm, >=stealth]

    % --- Assi ---
    \draw[->, gray!80!black, thick] (-0.5, 0) -- (8.5, 0) node[below right, text=black] {$t$};
    \draw[->, gray!80!black, thick] (0, -1.2) -- (0, 2.8) node[left, text=black] {$e(t)$};

    % --- Area Ombreggiata (Azione Integrale - Passato) ---
    % Viene tracciato un poligono chiuso delimitato dalla curva, l'asse x e x=t
    \fill[pattern=north west lines, pattern color=gray!80]
        (0, 0) -- (0, 0.4)
        .. controls (0.5, 0.0) and (1.5, -0.8) .. (2.5, -0.8)
        .. controls (3.5, -0.8) and (4.2, -0.2) .. (4.8, 0.6)
        -- (4.8, 0) -- cycle;

    % --- Curva dell'errore e(t) ---
    \draw[thick]
        (-0.3, 0.6) .. controls (-0.1, 0.45) .. (0, 0.4)
        .. controls (0.5, 0.0) and (1.5, -0.8) .. (2.5, -0.8)
        .. controls (3.5, -0.8) and (4.2, -0.2) .. (4.8, 0.6)
        .. controls (5.4, 1.4) and (6.4, 1.2) .. (7.2, 0.0)
        .. controls (7.6, -0.6) .. (8.0, -0.8);

    % --- Linea Tangente (Azione Derivativa - Futuro) ---
    \draw[->, gray!70!black, thick] (3.6, -0.7) -- (5.5, 1.4);

    % --- Linee Verticali ---
    % Linea al tempo t
    \draw[gray!80!black] (4.8, 0.6) -- (4.8, -0.3) node[below, text=black] {$t$};
    % Linea al tempo t + ...
    \draw[gray!80!black] (6.5, 0.9) -- (6.5, -0.3) node[below, text=black] {$t + \dots$};

    % --- Formule Matematiche ---
    \node at (2.0, 2.2) {$u_i(t) = k_i \int_0^t e(\tau)d\tau$};
    \node at (4.8, 3.1) {$u_p(t) = k_p e(t)$};
    \node at (7.8, 2.0) {$u_d(t) = k_d \frac{de(t)}{dt}$};

    % --- Etichette Temporali (Past, Present, Future) ---
    % Presente
    \node at (4.8, 2.4) {Presente};
    \draw[->, gray!80!black] (4.8, 2.1) -- (4.8, 1.0);

    % Passato
    \draw[<-, gray!80!black] (3.0, 1.7) -- (4.5, 1.7);
    \node[above] at (3.75, 1.7) {Passato};

    % Futuro
    \draw[->, gray!80!black] (5.1, 1.7) -- (6.6, 1.7);
    \node[above] at (5.85, 1.7) {Futuro};

\end{tikzpicture}
\end{document}
```

La figura ci aiuta ad intuire che:

>[!idea] AZIONI NEL CONTROLLORE PID
>1. L'azione **proporzionale** tiene conto del "*presente*": la sua risposta dipende dall'**errore attuale**.
>2. L'azione **integrale** tiene conto del "*passato*": la sua risposta riesce a **tenere conto** di **piccoli errori che non si annullano** (infatti questi *continuano a sommarsi* nel tempo, contribuendo ad una risposta che sarebbe invece "ignorata" dall'azione proporzionale).
>3. L'azione **derivativa** cerca di *"prevedere" il futuro*: *stima* la **velocità di variazione dell'errore** per *prevederne la tendenza* (se per esempio l'errore dovesse diminuire troppo in fretta, l'azione derivativa permetterebbe di *rallentare la risposta* e *prevenire l'overshoot*). 

Le **tre azioni** vengono poi **sommate** per contribuire all'uscita $u(t)$.
Pertanto il controllore è schematizzabile come segue:

```tikz
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}[
    % Definizione degli stili
    block/.style={rectangle, draw, thick, minimum width=1.6cm, minimum height=1.2cm, align=center},
    sum/.style={circle, draw, thick, minimum size=0.6cm, inner sep=0pt},
    >=stealth % Stile delle frecce
]

    % --- Nodi e coordinate principali ---
    
    % Ingresso E(s)
    \coordinate (input) at (0,0);
    \node at (0.5, 0.6) {\Large $E(s)$};
    
    % Punto di diramazione
    \coordinate (branch) at (1.8, 0);
    
    % Blocchi del controllore
    \node[block] (Kp) at (4.5, 2) {\Large $k_p$};
    \node[block] (Ki) at (4.5, 0) {\Large $k_i\frac{1}{s}$};
    \node[block] (Kd) at (4.5, -2) {\Large $k_d s$};
    
    % Nodo sommatore
    \node[sum] (sum) at (7.5, 0) {};
    
    % Uscita U(s)
    \coordinate (output) at (9.5, 0);
    \node at (9, 0.6) {\Large $U(s)$};

    % --- Collegamenti ---
    
    % Ramo di ingresso
    \draw[thick] (input) -- (branch);
    \filldraw (branch) circle (2pt); % Pallino di connessione
    
    % Dalla diramazione ai blocchi
    \draw[->, thick] (branch) |- (Kp);
    \draw[->, thick] (branch) -- (Ki);
    \draw[->, thick] (branch) |- (Kd);

    % Dai blocchi al sommatore (con i segni +)
    \draw[->, thick] (Kp) -| node[pos=0.85, left=0.05cm] {$+$} (sum);
    \draw[->, thick] (Ki) -- node[pos=0.8, below=0.05cm] {$+$} (sum);
    \draw[->, thick] (Kd) -| node[pos=0.85, right=0.05cm] {$+$} (sum);

    % Ramo di uscita
    \draw[->, thick] (sum) -- (output);

    % --- Riquadro rosso tratteggiato ---
    \draw[red, dashed, dash pattern=on 8pt off 6pt, very thick] (1.2, 2.9) rectangle (8.2, -2.9);

\end{tikzpicture}
\end{document}
```

Abbiamo allora, nel dominio di Laplace:
$$
C(s) = \frac{U(s)}{E(s)} = k_{p} + k_{i}\frac{1}{s} + k_{d}s
$$
# CONTROLLORE PROPORZIONALE
Abbiamo già parlato di *controllori proporzionali* in > [[20 - PROBLEMA DI CONTROLLO#CONTROLLO PROPORZIONALE]].
Per completezza, ricordiamo e riassumiamo di seguito quanto già visto.

Lo schema a blocchi del controllore è il seguente:

```tikz
\usetikzlibrary{positioning, arrows.meta}

\begin{document}
\begin{tikzpicture}[
    auto,
    >=Stealth, % Stile della freccia simile all'immagine
    thick,
    draw=gray!80!black, % Colore del tratto leggermente più tenue, come nell'originale
    text=black,
    block/.style={draw, rectangle, minimum height=1.6cm, minimum width=2.2cm},
    sum/.style={draw, circle, minimum size=0.5cm, inner sep=0pt}
]

% Definizione dei nodi e delle coordinate
\coordinate (input);
\node [sum, right=1.8cm of input] (sum) {};
\node [block, right=1.5cm of sum] (kp) {\Large $k_p$};
\node [block, right=1.5cm of kp] (gs) {\Large $G(s)$};
\coordinate [right=2.2cm of gs] (output);
\coordinate [right=1.1cm of gs] (branch);

% Connessioni dirette
\draw [->] (input) -- node[above, near start] {$r(t)$} node[above, pos=0.9] {$+$} (sum);
\draw [->] (sum) -- node[above] {$e(t)$} (kp);
\draw [->] (kp) -- node[above] {$u(t)$} (gs);
\draw [->] (gs) -- (output) node[right, yshift=1pt] {$y(t)$};

% Pallino per la diramazione (branch point)
\filldraw[gray!80!black] (branch) circle (1.5pt);

% Ramo di retroazione (feedback)
\draw [->] (branch) -- ++(0,-1.8) -| node[left, pos=0.95] {$-$} (sum);

\end{tikzpicture}
\end{document}
```

Essendo il controllore proporzionale, abbiamo, fissato un valore $k_{p}>0$:
$$
\begin{align*}
u(t) = k_{p}e(t) & & U(s) = k_{p}E(s) & & C(s) = \frac{U(s)}{E(s)} = k_{p}
\end{align*}
$$
Per cui il **guadagno di anello** è:
$$
L(s) = C(s)G(s) = k_{p}G(s)
$$
La **FdT in anello chiuso** è quindi:
$$
\begin{align*}
W(s) &= \frac{Y(s)}{R(s)} = \frac{L(s)}{1+L(s)} \\ \\
&= \frac{k_{p} \frac{n_{G}(s)}{d_{G}(s)}}{1 + k_{p} \frac{n_{G}(s)}{d_{G}(s)}} \\ \\
&= \frac{k_{p}n_{G}(s)}{d_{G}(s) + k_{p}n_{G}(s)}
\end{align*}
$$
>[!note] NOTA
>Ricordiamo che $d_{G}(s)+k_{p}n_{G}(s)$ deve **essere Hurwitz** per **garantire BIBO - stabilità**.

La **FdT dell'errore** è invece:
$$
W_{e}(s) = \frac{E(s)}{R(s)} = \frac{1}{1+L(s)}
$$
Assumendo che l'ingresso sia dato dal gradino unitario, abbiamo:
$$
E(s) = \frac{1}{s} \frac{1}{1+k_{p}G(s)}
$$
Per cui abbiamo:
$$
e_{\infty} = \lim_{ t \to \infty } e(t) = \lim_{ s \to 0 } sE(s) = \frac{1}{1+k_{p}G(0)}
$$
>[!note] NOTA
>Se $k_{p}$ **cresce**, l'**errore** a regime **diminuisce**.
## ESEMPIO CONTROLLORE P
Consideriamo il controllo proporzionale del *livello di liquido* $y(t)$ in un serbatoio (di sezione unitaria) manipolando il grado di apertura di una valvola.
Immaginiamo inoltre che il serbatoio abbia un'apertura alla base che lascia fuoriuscire il liquido e che il livello al tempo $0$ sia $y(0)=0$.

Abbiamo quindi:
$$
\dot{y}(t) = -\alpha y(t) + u(t)
$$
Applicando la TDL:
$$
\begin{align*}
sY(s) &= -\alpha Y(s) + U(s) \\ \\
(s+\alpha)Y(s) &= U(s) \\ \\
G(s) &= \frac{Y(s)}{U(s)} = \frac{1}{s+\alpha} 
\end{align*}
$$
La *funzione di trasferimento* del sistema in retroazione è quindi:
$$
W(s) = \frac{k_{p}G(s)}{1+k_{p}G(s)} = \ldots = \frac{k_{p}}{s+\alpha+k_{p}} 
$$
Siccome abbiamo $\alpha,k_{p}>0$, il **polo** di $W(s)$ è:
$$
s = -(\alpha+k_{p}) <0
$$
Per cui il sistema è **BIBO stabile**.
Inoltre osserviamo che:
$$
\begin{align*}
e_{\infty} &= \frac{1}{1+k_{p}G(0)} = \frac{\alpha}{\alpha+k_{p}} \\ \\
y(\infty) &= 1 - e_{\infty} = \frac{k_{p}}{\alpha+k_{p}}
\end{align*}
$$

>[!note] NOTA
>Stiamo assumendo che il riferimento $r(t)$ sia il *gradino unitario*.

Il grafico in figura ci mostra *come variano* il **valore di regime** e l'**errore a regime** all'*aumentare* del valore di $k_{p}$:

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}
\usetikzlibrary{arrows.meta}

\begin{document}
\begin{tikzpicture}[scale = 1]
\begin{axis}[
    width=13cm, height=8cm,
    xlabel={Tempo [s]},
    ylabel={\Large $y(t)$},
    xmin=-0.5, xmax=10.5,
    ymin=-0.05, ymax=1.05,
    axis lines=left,
    grid=both,
    grid style={dotted, gray!60},
    legend pos=south east,
    legend style={font=\footnotesize, cells={anchor=west}, fill=white, draw=gray!40},
    clip=false % Permette di disegnare elementi (come r(t)) fuori dal riquadro stretto degli assi
]

% Aggiunta manuale del testo r(t)
\node[left] at (axis cs: -0.8, 1) {\Large $r(t)$};

% Curve di risposta (formula esatta della risposta al gradino)
\addplot[blue, thick, domain=0:10, samples=100] {1/2 * (1 - exp(-2*x))};
\addlegendentry{$k_p=1$}

\addplot[red, thick, domain=0:10, samples=100] {2/3 * (1 - exp(-3*x))};
\addlegendentry{$k_p=2$}

\addplot[yellow!90!orange, thick, domain=0:10, samples=100] {3/4 * (1 - exp(-4*x))};
\addlegendentry{$k_p=3$}

\addplot[green!70!black, thick, domain=0:10, samples=100] {4/5 * (1 - exp(-5*x))};
\addlegendentry{$k_p=4$}

\addplot[violet, thick, domain=0:10, samples=100] {5/6 * (1 - exp(-6*x))};
\addlegendentry{$k_p=5$}

% Linea di riferimento unitario
\addplot[gray, densely dashed, thick, domain=0:10] {1};
\addlegendentry{Riferimento unitario}

% Testo "alpha = 1"
\node at (axis cs: 6, 0.15) {\large $\alpha = 1$};

% Freccia tratteggiata per l'andamento al crescere di kp
\draw[gray!90!black, dashed, very thick, -{Latex[length=4mm, width=4mm]}] 
    (axis cs: 2.1, 0.35) -- (axis cs: 0.15, 0.9);

% Freccia verticale per l'errore a regime
\draw[very thick, <->, >=Stealth] (axis cs: 2.8, 0.84) -- (axis cs: 2.8, 0.99);

\end{axis}
\end{tikzpicture}
\end{document}
```

>[!note] OSSERVAZIONE
>L'**errore diminuisce**, tuttavia **non si annulla mai**.
# CONTROLLORE PROPORZIONALE - INTEGRALE
Vediamo ora il *controllore proporzionale-integrale* (PI).
Lo schema a blocchi è il seguente:

```tikz
\usetikzlibrary{positioning, arrows.meta}

\begin{document}
\begin{tikzpicture}[
    auto,
    >=Stealth, % Stile della freccia simile all'immagine
    thick,
    draw=gray!80!black, % Colore del tratto leggermente più tenue, come nell'originale
    text=black,
    block/.style={draw, rectangle, minimum height=1.6cm, minimum width=2.5cm}, % Leggermente più largo per il PI
    sum/.style={draw, circle, minimum size=0.5cm, inner sep=0pt}
]

% Definizione dei nodi e delle coordinate
\coordinate (input);
\node [sum, right=1.8cm of input] (sum) {};
\node [block, right=1.5cm of sum] (pi) {\Large $k_p + \frac{k_i}{s}$};
\node [block, right=1.5cm of pi] (gs) {\Large $G(s)$};
\coordinate [right=2.2cm of gs] (output);
\coordinate [right=1.1cm of gs] (branch);

% Connessioni dirette
\draw [->] (input) -- node[above, near start] {$r(t)$} node[above, pos=0.9] {$+$} (sum);
\draw [->] (sum) -- node[above] {$e(t)$} (pi);
\draw [->] (pi) -- node[above] {$u(t)$} (gs);
\draw [->] (gs) -- (output) node[right, yshift=1pt] {$y(t)$};

% Pallino per la diramazione (branch point)
\filldraw[gray!80!black] (branch) circle (1.5pt);

% Ramo di retroazione (feedback)
\draw [->] (branch) -- ++(0,-1.8) -| node[left, pos=0.95] {$-$} (sum);

\end{tikzpicture}
\end{document}
```

In tal caso, il segnale *inviato dal controllore al sistema* è (nel dominio $s$):
$$
U(s) = k_{p}E(s) + \frac{k_{i}}{s}E(s)
$$
Vediamo chiaramente che la **FdT del controllore** è:
$$
C(s) = \frac{U(s)}{E(s)} = k_{p} + \frac{k_{i}}{s} = \frac{sk_{p}+k_{i}}{s}
$$
E troviamo quindi che il **guadagno di anello** è:
$$
L(s) = C(s)G(s) = \frac{sk_{p}+k_{i}}{s}G(s)
$$
>[!idea] OSSERVAZIONE IMPORTANTE
>Il controllore PI **aggiunge** (almeno *potenzialmente*, cioè *a meno di cancellazioni* con $G(s)$) **un polo in zero** ed **uno zero** a numeratore.

Troviamo a questo punto che la **FdT di anello chiuso** è:
$$
\begin{align*}
W(s) &= \frac{Y(s)}{R(s)} = \frac{L(s)}{1+L(s)} \\ \\
&= \frac{\frac{n_{C}(s)}{d_{c}(s)} \frac{n_{G}(s)}{d_{G}(s)}}{1+ \frac{n_{C}(s)}{d_{c}(s)} \frac{n_{G}(s)}{d_{G}(s)}} \\ \\
&= \frac{(sk_{p} + k_{i})n_{G}(s)}{sd_{G}(s) + (sk_{p}+k_{i})n_{G}(s)}
\end{align*}
$$
>[!note] NOTA
>Come al solito, il *denominatore* deve **essere Hurwitz** per garantire **BIBO stabilità**.
>

Per quanto riguarda l'errore abbiamo invece:
$$
W_{e} = \frac{E(s)}{R(s)} = \frac{1}{1+L(s)}
$$
Per cui, adottando sempre il *gradino unitario* come riferimento:
$$
\begin{align*}
E(s) &= \frac{1}{1 + \frac{sk_{p}+k_{i}}{s}G(s)} \\ \\
&= \frac{1}{s} \frac{s}{[s + (sk_{p}+k_{i})G(s)]}
\end{align*}
$$
Calcolando l'errore a regime troviamo:
$$
\begin{align*}
e_{\infty} &= \lim_{ t \to \infty } e(t) = \lim_{ s \to 0 } sE(s) \\ \\
&= \frac{0}{0+k_{i}G(0)} =^* 0
\end{align*}
$$
>[!idea] OSSERVAZIONE IMPORTANTE
>Assumendo $G(0)\ne 0$, l'**errore a regime è nullo**.
>Del resto, se $G(0)\ne 0$, $G(s)$ **non ha zeri** che **cancellino il polo in zero** introdotto dal controllore integrale, per cui è un **sistema di tipo 1** e riesce quindi ad *inseguire perfettamente* il riferimento costante.

>[!note] NOTE
>Se $G(s)=s\tilde{G}(s)$ (cioè presenta *uno zero* in $s=0$) il sistema **reagisce solo alle variazioni dell'ingresso**.
>Per capire meglio, scriviamo l'*uscita* di $G$ come segue:
>$$ Y(s) = G(s)U(s) = s\tilde{G}(s)U(s) = \tilde{G}(s)[sU(s)] $$
>E vediamo quindi che $Y(s)$ dipende dalla **derivata dell'ingresso** ($sU(s)$ è la trasformata di $\dot{u}(t)$).
>
>La conseguenza è che l'**effetto dell'integratore** (cioè l'aggiunta del polo in $s=0)$) viene **vanificato**: la soluzione è *aggiungere un altro blocco integratore* all'interno del controllore. 
>In generale, vogliamo avere *un integratore in più* rispetto al *numero di zeri* in $s=0$ del sistema.
## ESEMPIO CONTROLLORE PI
Consideriamo ancora una volta il serbatoio dell'esempio precedente, scegliendo stavolta di controllarlo con un *controllore PI*.

Abbiamo:
$$
\begin{align*}
G(s) = \frac{1}{s+\alpha} & & C(s) = \frac{sk_{p}+k_{i}}{s}
\end{align*}
$$
Per cui:
$$
W(s) = \frac{sk_{p} + k_{i}}{s^2 + (\alpha+k_{p})s + k_{i}}
$$

Lo **zero** introdotto dal controllore si trova in:
$$ 
z_{1} = -\frac{k_{i}}{k_{p}}
$$
Determiniamo i coefficienti $\xi$ e $\omega_{n}$ del sistema del secondo ordine:
$$
\begin{align*}
& s^2 + (\alpha+k_{p})s + k_{i} &:= s^2 + 2\xi\omega_{n}s + \omega_{n}^2 \\ \\
& \omega_{n} = \sqrt{ k_{i} }   &\xi = \frac{\alpha+k_{p}}{2\sqrt{ k_{i} }}
\end{align*}
$$
Ricordiamo che i *poli* sono dati da:
$$
p_{1,2} = \sigma \pm j\omega = -\xi\omega_{n} \pm j\omega_{n}\sqrt{ 1-\xi^2 }
$$
Immaginiamo di voler imporre che:
1. L'**overshoot** sia *inferiore* al $5\%$.
2. Il **tempo di assestamento** sia *inferiore* ad $1\text{s}$.

Sappiamo che per $\xi^*=\frac{\sqrt{ 2 }}{2}$ si ha $M_{p}=4.3\%$, per cui otteniamo $\varphi\leq \arccos \xi^*=\frac{\pi}{4}$.
Dalla seconda condizione troviamo invece:
$$
T_{s}^* \leq 1 \implies -\xi\omega_{n} \leq -\frac{3}{T_{s}^*} = -3 = \mathrm{Re}_{max}(p_{1,2})
$$
Per cui:
$$
\omega_{n} \geq 3\sqrt{ 2 } \approx 4.24
$$
Possiamo quindi **determinare i coefficienti del controllore**:
$$
\begin{cases}
k_{i} = \omega_{n}^2 \approx 18 \\ \\
k_{p} = 2\omega_{n}\xi - \alpha \approx 6-\alpha
\end{cases}
$$
# CONTROLLORE PROPORZIONALE - DERIVATIVO
Vediamo ora il *controllore proporzionale-derivativo* (PD).
Lo schema a blocchi è il seguente:

```tikz
\usetikzlibrary{positioning, arrows.meta}

\begin{document}
\begin{tikzpicture}[
    auto,
    >=Stealth, % Stile della freccia simile all'immagine
    thick,
    draw=gray!80!black, % Colore del tratto leggermente più tenue, come nell'originale
    text=black,
    block/.style={draw, rectangle, minimum height=1.6cm, minimum width=2.5cm}, % Leggermente più largo per il PI
    sum/.style={draw, circle, minimum size=0.5cm, inner sep=0pt}
]

% Definizione dei nodi e delle coordinate
\coordinate (input);
\node [sum, right=1.8cm of input] (sum) {};
\node [block, right=1.5cm of sum] (pi) {\Large $k_p + k_d s$};
\node [block, right=1.5cm of pi] (gs) {\Large $G(s)$};
\coordinate [right=2.2cm of gs] (output);
\coordinate [right=1.1cm of gs] (branch);

% Connessioni dirette
\draw [->] (input) -- node[above, near start] {$r(t)$} node[above, pos=0.9] {$+$} (sum);
\draw [->] (sum) -- node[above] {$e(t)$} (pi);
\draw [->] (pi) -- node[above] {$u(t)$} (gs);
\draw [->] (gs) -- (output) node[right, yshift=1pt] {$y(t)$};

% Pallino per la diramazione (branch point)
\filldraw[gray!80!black] (branch) circle (1.5pt);

% Ramo di retroazione (feedback)
\draw [->] (branch) -- ++(0,-1.8) -| node[left, pos=0.95] {$-$} (sum);

\end{tikzpicture}
\end{document}
```

La **FdT del controllore** è la seguente:
$$
C(s) = k_{p} + k_{d}s
$$
Per cui il **guadagno di anello** sarà dato da:
$$
L(s) = C(s)G(s) = (k_{p}+k_{d}s)G(s)
$$
>[!note] NOTA
>Anche il controllore PD **introduce uno zero**.
## ESEMPIO CONTROLLORE PD
Applichiamo ora un controllore PD al sistema degli esempi precedenti.

Troviamo in questo caso:
$$
\begin{align*}
W(s) = \frac{k_{p} + k_{d}s}{(k_{d}+1)s + k_{p} +\alpha} & & W(0) = \frac{k_{p}}{k_{p}+\alpha}
\end{align*}
$$
E notiamo subito che lo **zero** $z_{1}$ ed il **polo** $p_{1}$ si trovano in:
$$
\begin{align*}
z_{1} = -\frac{k_{p}}{k_{d}} & & p_{1} = -\frac{k_{p}+\alpha}{k_{d}+1}
\end{align*}
$$
L'**errore a regime** (rispetto al gradino) è:
$$
\begin{align*}
e_{\infty} &= \lim_{ t \to \infty } e(t) = 1 - \lim_{ t \to \infty } y(t) \\ \\
&= 1 - W(0) = \frac{\alpha}{k_{p}-\alpha} \ne 0
\end{align*}
$$
Assumiamo ora $\alpha=1$ e di *fissare* $k_{d}=1$.
Vediamo come si comporta la risposta al gradino al variare di $k_{p}$:

```tikz
\usepackage{tikz}
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}
\usetikzlibrary{arrows.meta}

% Definizione dei colori predefiniti di MATLAB per i grafici
\definecolor{matblue}{HTML}{0072BD}
\definecolor{matorange}{HTML}{D95319}
\definecolor{matyellow}{HTML}{EDB120}
\definecolor{matpurple}{HTML}{7E2F8E}

\begin{document}

\begin{tikzpicture}[scale = 1.2]
\begin{axis}[
    width=10cm,
    height=7cm,
    xmin=0, xmax=4,
    ymin=0, ymax=1,
    xlabel={Time (seconds)},
    ylabel={$y(t)$},
    xtick={0,0.5,1,1.5,2,2.5,3,3.5,4},
    ytick={0,0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1.0},
    tick align=inside,
    axis on top,
    % Spessore delle linee per farle risaltare come nell'immagine
    every axis plot/.append style={line width=1.5pt},
    label style={font=\normalsize},
    tick label style={font=\normalsize, color=black!80},
    legend pos = south east
]

% 1. Linee tratteggiate orizzontali (Asintoti a regime)
% k=2 -> y=2/3 (~0.667)
\draw[dotted, thick, black!60] (0, 2/3) -- (4, 2/3);
% k=4 -> y=4/5 (0.8)
\draw[dotted, thick, black!60] (0, 4/5) -- (4, 4/5);
% k=6 -> y=6/7 (~0.857)
\draw[dotted, thick, black!60] (0, 6/7) -- (4, 6/7);
% k=8 -> y=8/9 (~0.889)
\draw[dotted, thick, black!60] (0, 8/9) -- (4, 8/9);

% 2. Curve di risposta al gradino
% k = 2 (Blu)
\addplot[matblue, domain=0:4, samples=100] {2/3 - (2/3 - 0.5)*exp(-2*x)};
\addlegendentry{$k_p=2$}

% k = 4 (Arancione)
\addplot[matorange, domain=0:4, samples=100] {4/5 - (4/5 - 0.5)*exp(-4*x)};
\addlegendentry{$k_p=4$}

% k = 6 (Giallo)
\addplot[matyellow, domain=0:4, samples=100] {6/7 - (6/7 - 0.5)*exp(-6*x)};
\addlegendentry{$k_p=6$}

% k = 8 (Viola)
\addplot[matpurple, domain=0:4, samples=100] {8/9 - (8/9 - 0.5)*exp(-8*x)};
\addlegendentry{$k_p=8$}

\draw[-{Triangle[width=6pt,length=8pt]}, dashed, line width=1.2pt, gray] 
    (3.25, 0.69) -- (3.25, 0.98);

\end{axis}
\end{tikzpicture}

\end{document}
```

>[!idea] EFFETTO DEL CONTROLLO PROPORZIONALE
>A parità di $k_{d}$, **aumentare** $k_{p}$ **riduce l'errore** a regime (rispetto al gradino), ma **non lo annulla**.

Fissiamo ora $k_{p}=1$ e vediamo come il valore di $k_{d}$ influisce sulla risposta.
La **costante di tempo** del transitorio è:
$$
\tau = \frac{1}{|p_{1}|} = \frac{k_{d}+1}{k_{p}+\alpha}
$$
Al variare di $k_{d}$ abbiamo quindi le seguenti risposte:

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}
\usetikzlibrary{arrows.meta}

% Definizione dei colori predefiniti di MATLAB per i tracciati
\definecolor{matlabblue}{HTML}{0072BD}
\definecolor{matlaborange}{HTML}{D95319}
\definecolor{matlabyellow}{HTML}{EDB120}
\definecolor{matlabpurple}{HTML}{7E2F8E}

\begin{document}
\begin{tikzpicture}[scale = 1.2]
    \begin{axis}[
	    width=10cm,
	    height=7cm,
        xmin=0, xmax=30,
        ymin=0, ymax=1,
        xlabel={Time (seconds)},
        ylabel={y(t)},
        xtick={0, 5, 10, 15, 20, 25, 30},
        ytick={0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1},
        axis on top, 
        tick align=inside, 
        every axis plot/.append style={line width=1.5pt}, 
        label style={font=\normalsize},
        tick label style={font=\normalsize},
        legend pos = south east
    ]
    
    % Curva blu: k_d = 2
    \addplot[matlabblue, domain=0:30, samples=100] {0.5 + (2/(2+1) - 0.5) * exp(-x/2)};
    \addlegendentry{$k_d=2$}
    
    % Curva arancione: k_d = 4
    \addplot[matlaborange, domain=0:30, samples=100] {0.5 + (4/(4+1) - 0.5) * exp(-x/4)};
    \addlegendentry{$k_d=4$}
    
    % Curva gialla: k_d = 6
    \addplot[matlabyellow, domain=0:30, samples=100] {0.5 + (6/(6+1) - 0.5) * exp(-x/6)};
    \addlegendentry{$k_d=6$}
    
    % Curva viola: k_d = 8
    \addplot[matlabpurple, domain=0:30, samples=100] {0.5 + (8/(8+1) - 0.5) * exp(-x/8)};
\addlegendentry{$k_d=8$}

	% Linea asintotica tratteggiata a y=0.5
    \addplot[dashed, domain=0:30, black, line width=1pt] {0.5};

    % Freccia che mostra l'aumento di k_d
    \draw[-{Triangle[width=8pt,length=10pt]}, dashed, line width=1pt, color=black!70] (4.5, 0.54) -- (12, 0.75);

    \end{axis}
\end{tikzpicture}
\end{document}
```

**NOTA**: Abbiamo assunto $\alpha=k_{p}=1$, per cui il valore a regime è $W(0)=\frac{1}{2}$.

>[!idea] EFFETTO DEL CONTROLLO DERIVATIVO
>1. La risposta al gradino "parte" con un **salto iniziale**. 
>   Ciò è dovuto a quanto visto in > [[21 - SISTEMI ELEMENTARI E RISPETTO DELLE SPECIFICHE#SISTEMI ELEMENTARI DEL PRIMO ORDINE#PRESENZA DI UNO ZERO]].
>   **NOTA**: Questo salto iniziale *non è necessariamente una buona cosa*, perchè significa che il sistema subisce *immediatamente una grande sollecitazione*.
>2. All'aumentare di $k_{d}$ sia lo **zero** che il **polo si avvicinano all'origine**.
>   Di conseguenza, la **risposta** è **inizialmente più "pronta"** (per il salto iniziale causato dallo zero), ma presenta un **transitorio più lento**, a causa della *costante di tempo* associata ad un *polo sempre più vicino all'origine*.
# CONTROLLORE PROPORZIONALE - INTEGRALE - DERIVATIVO
Vediamo infine il *controllore completo*, sempre applicato allo stesso sistema d'esempio.

Abbiamo:
$$
\begin{align*}
C(s) &= k_{p} + \frac{k_{i}}{s} + k_{d}s & & G(s) = \frac{1}{s+\alpha} \\ \\
&= \frac{k_{d}s^2 + k_{p}s + k_{i}}{s}
\end{align*}
$$
Allora il **guadagno di anello** è:
$$
L(s) = \frac{k_{d}s^2 + k_{p}s + k_{i}}{s} \frac{1}{s+\alpha}
$$
>[!note] NOTA
>Il controllore PID **introduce** (sempre potenzialmente) **due zeri** ed un **polo in zero**.

>[!idea] OSSERVAZIONE IMPORTANTE
>Notiamo che $C(s)$ è **impropria**, per cui **non è fisicamente realizzabile**.
>Si tratta infatti di una *semplificazione*: nella **realtà** un controllore PID è del tipo seguente
>$$ C_{PID}(s) = k_{d} \frac{(s+z_{1})(s+z_{2})}{s(s+p_{1})} $$
>Dove $p_{1}$ è un polo *posto sufficientemente a sinistra* da influenzare poco la dinamica del sistema.

La **FdT di anello chiuso** risulta essere:
$$
W(s) = \frac{L(s)}{1+L(s)} = \frac{k_{d}s^2 + k_{p}s + k_{i}}{(1+k_{d})s^2 + (\alpha+k_{p}) + k_{i}}
$$
Notiamo subito che $W(0)=1$, per cui:
$$
e_{\infty} = 0
$$
Proprio alla luce del fatto che il controllore PID rende la *serie diretta C-G* un **sistema di tipo 1**, che riesce quindi a seguire perfettamente il riferimento costante. 

A questo punto, riscriviamo il denominatore in una forma che ci aiuti a determinare i coefficienti $\xi$ e $\omega_{n}$:
$$
\begin{align*}
(1+k_{d})s^2 + (\alpha+k_{p}) + k_{i} &= (1+k_{d})\left[ s^2 + \frac{\alpha+k_{p}}{1+k_{d}}s + \frac{k_{i}}{1+k_{d}} \right] \\ \\
&= (1+k_{d})[s^2 + 2\xi\omega_{n}s + \omega_{n}^2]
\end{align*}
$$
Troviamo allora:
$$
\begin{align*}
\omega_{n} &= \sqrt{ \frac{k_{i}}{1+k_{d}} } \\ \\
\xi &= \frac{\alpha+k_{p}}{2\sqrt{ k_{i}(1+k_{d}) }}
\end{align*}
$$
A partire da queste relazioni possiamo poi partire per *determinare i parametri del controllore* in base alle *specifiche*.
## EFFETTO DEI TRE PARAMETRI
L'*effetto dell'aumento di un parametro* (fissati gli altri due) di un controllore è riportato in una **tabella qualitativa-euristica** come la seguente:

```tikz
\usepackage{tikz}

\begin{document}

\begin{tikzpicture}[x=1cm, y=1cm, font=\sffamily]
  % Sfondo riga intestazione
  \fill[gray!15] (0,0) rectangle (17.4, -0.8);
  
  % Sfondo righe dati
  \fill[gray!5] (0,-0.8) rectangle (17.4, -3.2);

  % Griglia orizzontale
  \draw[gray!50, thin] (0, 0) -- (17.4, 0);
  \draw[gray!50, thin] (0, -0.8) -- (17.4, -0.8);
  \draw[gray!50, thin] (0, -1.6) -- (17.4, -1.6);
  \draw[gray!50, thin] (0, -2.4) -- (17.4, -2.4);
  \draw[gray!50, thin] (0, -3.2) -- (17.4, -3.2);

  % Griglia verticale
  \draw[gray!50, thin] (0, 0) -- (0, -3.2);
  \draw[gray!50, thin] (2.2, 0) -- (2.2, -3.2);
  \draw[gray!50, thin] (4.4, 0) -- (4.4, -3.2);
  \draw[gray!50, thin] (6.8, 0) -- (6.8, -3.2);
  \draw[gray!50, thin] (9.6, 0) -- (9.6, -3.2);
  \draw[gray!50, thin] (13.2, 0) -- (13.2, -3.2);
  \draw[gray!50, thin] (17.4, 0) -- (17.4, -3.2);

  % Testi - Intestazione
  \node at (1.1, -0.4) {\textbf{Parameter}};
  \node at (3.3, -0.4) {\textbf{Rise time}};
  \node at (5.6, -0.4) {\textbf{Overshoot}};
  \node at (8.2, -0.4) {\textbf{Settling time}};
  \node at (11.4, -0.4) {\textbf{Steady-state error}};
  \node at (15.3, -0.4) {\textbf{Stability}};

  % Testi - Riga 1
  \node at (1.1, -1.2) {$K_p \uparrow$};
  \node at (3.3, -1.2) {Decrease};
  \node at (5.6, -1.2) {Increase};
  \node at (8.2, -1.2) {Small change};
  \node at (11.4, -1.2) {Decrease};
  \node at (15.3, -1.2) {Degrade};

  % Testi - Riga 2
  \node at (1.1, -2.0) {$K_i \uparrow$};
  \node at (3.3, -2.0) {Decrease};
  \node at (5.6, -2.0) {Increase};
  \node at (8.2, -2.0) {Increase};
  \node at (11.4, -2.0) {Eliminate};
  \node at (15.3, -2.0) {Degrade};

  % Testi - Riga 3
  \node at (1.1, -2.8) {$K_d \uparrow$};
  \node at (3.3, -2.8) {Minor change};
  \node at (5.6, -2.8) {Decrease};
  \node at (8.2, -2.8) {Decrease};
  \node at (11.4, -2.8) {No effect in theory};
  \node at (15.3, -2.8) {Improve if $K_d$ small};
\end{tikzpicture}

\end{document}
```

**NOTA**: La tabella è riferita all'ingresso $r(t)=\delta_{-1}(t)$.

>[!note] NOTA
>Spesso i controllori PID industriali sono dotati di **algoritmi interni di taratura automatica** che forniscono una *terna di parametri* **"sufficientemente buona"** per il sistema.
>Successivamente, si possono effettuare *modifiche più fini* per ottenere i *risultati voluti*.
>In ogni caso, tali aggiustamenti sono solitamente di stampo **piuttosto qualitativo**, effettuati sulla base di una tabella come quella sopra.
# PROGETTO DEL CONTROLLORE
Abbiamo un sistema $G(s)$ che **vogliamo controllare** con il controllore $C(s)$, come in figura:

```tikz
\usetikzlibrary{arrows.meta, positioning, calc}

\begin{document}
\begin{tikzpicture}[
    >={Stealth[length=2.5mm, width=2mm]},
    block/.style={draw, rectangle, minimum height=1.2cm, minimum width=1.8cm, thick},
    sum/.style={draw, circle, minimum size=0.4cm, thick}
]

    % Nodi e coordinate principali
    \coordinate (start) at (0,0);
    \node [sum] (sum) at (1.5,0) {};
    \node [block] (C) at (4,0) {$C(s)$};
    \node [block] (G) at (7.5,0) {$G(s)$};
    \coordinate (branch) at (9.5,0);
    \coordinate (end) at (10.5,0);

    % Linee di flusso in andata (forward path)
    \draw [->, thick] (start) -- node[above, pos=0.2] {$r(t)$} node[below, pos=0.85] {$+$} (sum);
    \draw [->, thick] (sum) -- node[above left] {$e(t)$} (C);
    \draw [->, thick] (C) -- node[above] {$u(t)$} (G);
    \draw [->, thick] (G) -- (branch) -- (end) node[right] {$y(t)$};

    % Nodo di diramazione (pallino nero)
    \filldraw (branch) circle (2pt);

    % Linea di retroazione (feedback loop)
    \draw [->, thick] (branch) -- ++(0, -1.8) -| node[right, pos=0.9] {$-$} (sum);

    % Riquadro tratteggiato blu
    \draw [dashed, draw=blue, thick, rounded corners=2pt] 
        ([xshift=-0.5cm, yshift=0.7cm]C.north west) 
        rectangle 
        ([xshift=0.5cm, yshift=-0.7cm]G.south east);

\end{tikzpicture}
\end{document}
```

Siamo *noi* a costruire $C(s)$; per cui viene spontaneo chiedersi *come* costruirlo/progettarlo.

Assumiamo innanzitutto che valgano le seguenti ipotesi:

>[!tldr] IPOTESI
>1. Sia $G(s)$ FdT **razionale**.
>2. Poli di $G(s)\in \{ s \in \mathbb{C} : \mathrm{Re}(s)<0 \}\cup \{ 0 \}$ (tutti i poli hanno **parte reale non positiva**).
>3. $G(s)=\frac{k_{B}}{s^h}G_{1}(s)$ con $G_{1}(s)$ *FdT razionale*, $G_{1}(0)=1$ (sistema di **tipo h**).

>[!note] NOTA
>La seconda ipotesi viene fatta solo per **semplificare lo studio**.
>Nella realtà *non è assolutamente detto* che i poli siano *tutti a parte reale non positiva*, ma bisognerebbe fare affidamento a *tecniche di controllo molto più complesse*.

Ricordiamo che un la risposta di un sistema è caratterizzata dai parametri riportati nella figura di seguito:

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

L'obiettivo è **progettare** $C(s)$ **razionale propria** con poli in $\{ s \in \mathbb{C} : \mathrm{Re}(s)<0\}\cup \{ 0 \}$, in modo che la **FdT di anello chiuso** soddisfi le seguenti specifiche:

>[!tldr] SPECIFICHE DELLA FDT DI ANELLO CHIUSO
>1. $W(s)$ deve essere **BIBO stabile**.
>2. $W(s)$ deve essere di **tipo h**.
>3. L'**errore a regime** deve essere *minore (o uguale) di un valore fissato* ($e_{\infty}\leq e_{\infty}^*$).
>4. La **massima sovraelongazione** deve essere *minore di un valore prefissato* ($M_{p}\leq M_{p}^*$).
>5. Il **tempo di salita** (o il **tempo di assestamento**) deve essere *minore di un tempo prefissato* ($T_{r}\leq T_{r}^*$).

>[!idea] OSSERVAZIONE FONDAMENTALE
>Il progetto del controllore tiene conto di:
>1. Aspetti **transitori** (punti 4 e 5).
>2. Aspetti **a regime** (punti 1,2 e 3).
