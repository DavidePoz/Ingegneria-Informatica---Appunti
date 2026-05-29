# INDICE SEZIONE
- [ ] [[#ANALISI SISTEMI LTI CON TDL]]
      - [[#EVOLUZIONE LIBERA E TDL]]
      - [[#RISPOSTA FORZATA E TDL]]
      - [[#RISPOSTA COMPLESSIVA E TDL]]
- [ ] [[#ESEMPI]]
      - [[#ESEMPIO GENERICO]]
      - [[#ESEMPIO CIRCUITO RC]]
- [ ] [[#ANALOGIE]]
      - [[#ANALOGIA ELETTRO - MECCANICA]]
- [ ] [[#ELEMENTI CIRCUITALI NEL DOMINIO S]]
      - [[#ESEMPIO]]
- [ ] [[#RISPOSTA IN FREQUENZA]]
      - [[#DIAGRAMMI DI BODE]]
# ANALISI SISTEMI LTI CON TDL
Ora che abbiamo studiato la *trasformata di Laplace*, vediamo come utilizzarla per l'analisi dei *sistemi lineari tempo invarianti*.
## EVOLUZIONE LIBERA E TDL
Cominciamo con lo studio dell'**evoluzione libera**, che ricordiamo essere la soluzione al problema:
$$
\begin{align*}
\dot{x}_{l}(t) = Ax_{l}(t) & & x(0)\ne 0 & & u(t) = 0
\end{align*}
$$
Applichiamo la *TDL* ad ambo i membri:
$$
\mathcal{L}[\dot{x}_{l}(t)] = \mathcal{L}[Ax_{l}(t)]
$$
Ricordando le proprietà viste in > [[12 - STRUMENTI MATEMATICI#APPROCCIO OPERATIVO E PROPRIETA']], troviamo:
$$
\begin{align*}
sX_{l}(s) - x_{l}(0) &= AX_{l}(t) \\ \\
sX_{l}(s) - AX_{l}(s) &= x_{l}(0) \\ \\
(sI-A)X_{l}(s) &= x_{l}(0) \\ \\
X_{l}(t) &= (sI-A)^{-1}x_{l}(0)
\end{align*}
$$
>[!note] RICHIAMO: MATRICE INVERSA
>Ricordiamo da Algebra Lineare che, data una matrice quadrata invertibile $M$, la sua inversa è data da:
>$$ M^{-1} = \frac{1}{\det M}M^* $$
>Dove $M^*$ è la **matrice aggiunta**, ovvero la *trasposta della matrice dei cofattori* di $M$ (I cofattori erano dati da $c_{ij}=(-1)^{i+j}\det M_{ij}$).

Siccome $\det(sI-A)=p_{A}(s)$ (*polinomio caratteristico*), possiamo scrivere:
$$
(sI-A)^{-1} = \frac{N(s)}{p_{A}(s)}
$$
Dove per $N(s)$ intendiamo la *matrice aggiunta* di $(sI-A)$.

>[!idea] OSSERVAZIONE IMPORTANTE
>Per come sono definiti i *cofattori*, ogni elemento di $N(s)$ deve essere un **polinomio** in $s$ di **grado** $n-1$.
>Sappiamo inoltre che $p_{A}(s)$ è un polinomio in $s$ di grado $n$.
>Allora *ogni elemento* della matrice inversa $(sI-A)^{-1}$ è un *frazione reale strettamente propria*, e possiamo quindi applicare quanto visto in > [[12 - STRUMENTI MATEMATICI#ANTITRASFORMATA]].

A questo punto, per calcolare l'evoluzione temporale della *risposta libera* basta antitrasformare:
$$
x_{l}(t) = \mathcal{L}^{-1}[X_{l}(s)] = \mathcal{L}^{-1}[(sI-A)^{-1}x_{l}(0)] = e^{ At }x_{l}(0)
$$

>[!note] NOTA
>Infatti:
>$$ \mathcal{L}[e^{ At }] = (sI-A)^{-1} $$
## RISPOSTA FORZATA E TDL
Ricordiamo che la risposta forzata è soluzione di:
$$
\begin{align*}
\dot{x}_{f}(t) = Ax_{f}(t) + Bu(t) & & x_{f}(0) = 0 & & u(t) \ne 0
\end{align*}
$$
Applicando la TDL ad ambo i membri e sfruttiamo le proprietà viste:
$$
\begin{align*}
\mathcal{L}[\dot{x}_{f}(t)] &= \mathcal{L}[Ax_{f}(t) + Bu(t)] \\ \\
sX_{f}(s) - x_{f}(0) &= AX_{f}(s) + BU(s) \\ \\
X_{f}(s) &= (sI-A)^{-1}BU(s) 
\end{align*}
$$
E per trovare la *risposta forzata* applichiamo l'antitrasformata:
$$
x_{f}(t) = \mathcal{L}^{-1}[(sI-A)^{-1}BU(s)]
$$

>[!def] MATRICE DI TRASFERIMENTO
>La matrice
>$$ G_{xu}(s) := (sI-A)^{-1}B $$
>è detta **matrice di trasferimento tra ingresso e stato** del sistema dinamico.
>
>**NOTA**: corrisponde alla *trasformata di Laplace della risposta impulsiva* $h(t)$.

E spesso si scrive:
$$
X_{f}(s) = G_{xu}(s)U(s)
$$
>[!idea] VARIABILI DI USCITA
>Spesso le **uscite** del sistema (variabili $y$ di *interesse*) *non coincidono con gli stati*, ma sono una *combinazione lineare di stati e ingressi*, del tipo:
>$$ \begin{cases} \dot{x}(t) = Ax(t) + Bu(t) \\ \\ y(t) = Cx(t) + Du(t) \end{cases} $$

In tal caso si definisce la **matrice di trasferimento tra ingresso e uscita**:
$$
\begin{align*}
G_{yu}(s) &:= C(sI-A)^{-1}B + D \\ \\
Y(s) &= G_{yu}(s)U(s)
\end{align*}
$$
Il calcolo della riposta forzata si può ottenere anche sfruttando la TDL dell'*integrale di convoluzione* che avevamo visto in > [[11 - SISTEMI LTI E RISPOSTA FORZATA (INTRO)#CONVOLUZIONE]]:
$$
x_{f}(t) = \int_{0}^t e^{ A(t-\tau) }Bu(\tau)d\tau
$$
Applicando la TDL abbiamo:
$$
\begin{align*}
\mathcal{L}[x(t)] &= \mathcal{L}[(h*u)(t)]\bigg|_{h(t)=e^{ At }B} \\ \\
&= H(s)U(s) = (sI-A)^{-1}BU(s)
\end{align*} 
$$
## RISPOSTA COMPLESSIVA E TDL
Una volta calcolate l'*evoluzione libera* e la *risposta forzata*, siamo interessati alla *risposta complessiva*.
Nel dominio della trasformata abbiamo:
$$
\begin{align*}
X(s) &= X_{l}(s) + X_{f}(s) \\ \\
&= (sI-A)^{-1}x_{l}(0) + (sI-A)^{-1}BU(s)
\end{align*}
$$
>[!idea] OSSERVAZIONE IMPORTANTE
>Notiamo che la *risposta complessiva* dipende da due termini  (i quali possono essere scomposti in fratti semplici):
>1. Primo termine: contributo delle *condizioni iniziali* governato dalle **radici del denominatore** di $(sI-A)^{-1}$.
>2. Secondo termine: contributo dell'*ingresso* governato dalle **radici dei denominatori sia di** $(sI-A)^{-1}$ **sia di** $U(s)$.

Applicando l'antitrasformata troviamo infine:
$$
\begin{align*}
x(t) &= x_{l} + x_{f}(t) \\ \\
&= \mathcal{L}^{-1}[(sI-A)^{-1}x_{l}(0)] + \mathcal{L}^{-1}[(sI-A)^{-1}BU(s)] \\ \\
&= e^{ At }x_{l}(0) + \mathcal{L}^{-1}[(sI-A)^{-1}BU(s)]
\end{align*}
$$
# ESEMPI
## ESEMPIO GENERICO
Consideriamo il seguente sistema d'esempio e vediamo come calcolare la risposta forzata:
$$
\begin{align*}
\dot{x}(t) = Ax(t) + Bu(t) & & A = \begin{bmatrix}
0 & 1 \\
-4 & -5
\end{bmatrix} & & B = \begin{bmatrix}
3 \\
3
\end{bmatrix} & & u(t) = ce^{ \alpha t }
\end{align*}
$$
Data la semplicità dell'ingresso, calcoliamone subito la trasformata:
$$
U(s) = \frac{c}{s-\alpha}
$$
Ora dobbiamo calcolare $(sI-A)^{-1}$.
Abbiamo:
$$
(sI-A)^{-1} = \begin{bmatrix}
s & -1 \\
4 & s+5
\end{bmatrix}^{-1}
$$
Il polinomio caratteristico è:
$$
p_{sI-A}(s) = (s+4)(s+1)
$$
E quindi troviamo:
$$
(sI-A)^{-1} = \frac{1}{(s+4)(s+1)}\begin{bmatrix}
s+5 & 1 \\
-4 & s
\end{bmatrix} = \begin{bmatrix}
\frac{s+5}{(s+4)(s+1)} & \frac{1}{(s+4)(s+1)} \\
-\frac{4}{(s+4)(s+1)} & \frac{s}{(s+4)(s+1)}
\end{bmatrix}
$$
E così possiamo calcolare:
$$
X_{f}(s) = (sI-A)^{-1}BU(s) = \begin{bmatrix}
\frac{18c+3cs}{(s+4)(s+1)(s-\alpha)} \\
\frac{-12c+3cs}{(s+4)(s+1)(s-\alpha)}
\end{bmatrix}
$$
Riscriviamo le entrate di $X_{f}(s)$ scomponendole in fratti semplici:
$$
\begin{align*}
\frac{18c+3cs}{(s+4)(s+1)(s-\alpha)} &= \underbrace{ \frac{3(\alpha+6)c}{(\alpha+1)(\alpha+4)} }_{ C_{11} } \frac{1}{s-\alpha} \underbrace{ -\frac{5c}{\alpha+1} }_{ C_{12} } \frac{1}{s+1} + \underbrace{ \frac{2c}{\alpha+4} }_{ C_{13} } \frac{1}{s+4} \\ \\
\frac{-12c+3cs}{(s+4)(s+1)(s-\alpha)} &= \underbrace{ \frac{3(\alpha-4)c}{(\alpha+1)(\alpha+4)} }_{ C_{21} } \frac{1}{s-\alpha} +\underbrace{ \frac{5c}{\alpha+1} }_{ C_{22} } \frac{1}{s+1} \underbrace{ -\frac{8c}{\alpha+4} }_{ C_{23} } \frac{1}{s+4}
\end{align*}
$$
A questo punto applichiamo l'antitrasformata:
$$
\begin{align*}
\mathcal{L}^{-1}\left[ \frac{1}{s-\alpha} \right] &= e^{ \alpha t } \\ \\
\mathcal{L}^{-1}\left[ \frac{1}{s+1} \right] &= e^{ -t } \\ \\
\mathcal{L}^{-1}\left[ \frac{1}{s+4} \right] &= e^{ -4t }
\end{align*}
$$
E troviamo così:
$$
x_{f}(t) = \begin{bmatrix}
\frac{3c(\alpha+6)}{(\alpha+1)(\alpha+4)} e^{ \alpha t } -\frac{5c}{\alpha+1}e^{ -t } + \frac{2c}{\alpha+4} e^{ -4t } \\
\frac{3c(\alpha-4)}{(\alpha+1)(\alpha+4)} e^{ \alpha t } +\frac{5c}{\alpha+1}e^{ -t } - \frac{8c}{\alpha+4} e^{ -4t }
\end{bmatrix}
$$
## ESEMPIO CIRCUITO RC
Consideriamo il circuito in figura:

```tikz
\usepackage{tikz}
\usepackage[american]{circuitikz}
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}[font=\small]

    % --- SFONDI GRIGI ---
    % Disegnati per primi in modo che rimangano in secondo piano
    \fill[gray!20] (-4.6, -0.2) rectangle (0.6, 3.2);
    \fill[gray!20] (1.7, -0.2) rectangle (5.8, 3.2);

    % --- GRAFICO A SINISTRA ---
    % Assi cartesiani
    \draw[-latex, thick] (-4.3, 0.5) -- (-1.8, 0.5) node[right] {$t$};
    \draw[-latex, thick] (-3.8, 0.5) -- (-3.8, 2.8) node[above] {$v_i$};

    % Asintoto orizzontale
    \draw[dashed, thick] (-4.0, 2.2) node[left] {$A_i$} -- (-1.8, 2.2);
    
    % Curva esponenziale
    \draw[very thick, darkgray, domain=0:2.2, samples=50] 
        plot ({\x - 3.8}, {0.5 + 1.7*(1 - exp(-1.8*\x))});

    % Equazione
    \node at (-3.3, -0.5) {$v_i(t) = A_i \left( 1 - e^{-t/\tau_i} \right)$};


    % --- CIRCUITO A DESTRA ---
    % Ramo sinistro: Generatore di tensione (colorato di grigio, ma testo nero)
    \draw[thick] (0,0) -- (0,0.6);
    \draw[thick, gray] (0,0.6) to[V, invert, l_=\textcolor{black}{$v_i$}] (0,1.9);
    \draw[thick] (0,1.9) -- (0,2.5);

    % Interruttore in chiusura
    \draw[thick] (0,2.5) -- (0.5,2.5) to[closing switch, l_=\textcolor{black}{$\mathrm{t=0}$}] (1.5,2.5) -- (2.5,2.5);

    % Resistore
    \draw[thick, gray] (2.5,2.5) to[R, l=\textcolor{black}{$R$}] (4.5,2.5);

    % Freccia della corrente (sopra il filo)
    \draw[-stealth, gray, thick] (4.3, 2.8) -- (5.1, 2.8) node[midway, above, text=black] {$i$};

    % Condensatore 
    \draw[thick] (4.5,2.5) -- (5.5,2.5);
    \draw[thick, gray] (5.5,2.5) to[C, l_=\textcolor{black}{$C$}] (5.5,0.5);
    \draw[thick] (5.5,0.5) -- (5.5,0);

    % Etichette tensione sul condensatore
    \node at (5.9, 2.0) {$+$};
    \node at (5.9, 1.0) {$-$};
    \node at (6.1, 1.5) {$v$};

    % Filo inferiore di ritorno
    \draw[thick] (5.5,0) -- (0,0);

    % Condizione iniziale
    \node at (5.5, -0.5) {$v(0^-)$};

\end{tikzpicture}
\end{document}
```

Assumiamo:
$$
\begin{align*}
v(0^-) &= 2[V] &  \tau_{C} &= 3^{-1}[s] \\ \\
A_{i} &= 6[V] &  \tau_{i} &= 2^{-1}[s] 
\end{align*}
$$

>[!note] DESCRIZIONE SISTEMA
>Abbiamo un *circuito RC* alimentato da un *generatore di tensione*.
>Al tempo $t=0$ un *interruttore viene chiuso* e la *tensione erogata* dal generatore cresce dal valore iniziale nullo fino al *valore a regime* $A_{i}$.

Applicando la legge delle maglie al circuito troviamo:
$$
-v_{i}(t) + RC \frac{dv(t)}{dt} + v(t) = 0
$$
Riscriviamo l'equazione nella forma seguente, tenendo conto che $\tau_{C}:=RC$:
$$
\dot{v}(t) = -\tau_{C}^{-1}v(t) + \tau_{C}^{-1}v_{i}(t)
$$
Quindi, in questo semplice esempio, le matrici $A$ e $B$ sono semplicemente:
$$
\begin{align*}
A = - \tau_{C}^{-1} & & B = \tau_{C}^{-1}
\end{align*}
$$
Sappiamo che la **risposta complessiva** è data da:
$$
v(t) = \underbrace{ e^{ -\tau_{C}^{-1}t }v(0^-) }_{ e^{ At }x_{l}(0) } + \mathcal{L}^{-1}[\underbrace{ (s+\tau_{C}^{-1})^{-1}\tau_{C}^{-1} }_{ (sI-A)^{-1}B } V_{i}(s)] 
$$
Calcoliamo $V_{i}(s)$:
$$
\begin{align*}
v_{i}(t) &= A_{i}(1-e^{ -t/\tau_{i} }) = A_{i} - A_{i}e^{ -t/\tau_{i} } \\ \\
&\implies V_{i}(s) = \frac{A_{i}}{s} - \frac{A_{i}}{s+\tau_{i}^{-1}} = \frac{A_{i}\tau_{i}^{-1}}{s(s+\tau_{i}^{-1})}
\end{align*}
$$
Per la risposta forzata nel dominio $s$ è data da:
$$
V_{f}(s) = \frac{A_{i}\tau_{C}^{-1}\tau_{i}^{-1}}{s(s+\tau_{C}^{-1})(s+\tau_{i}^{-1})}
$$
A questo punto vogliamo scrivere $V_{f}(s)$ come somma di fratti semplici:
$$
V_{f}(s) = \left[ \frac{k_{1}}{s} + \frac{k_{2}}{s+\tau_{C}^{-1}} + \frac{k_{3}}{s+\tau_{i}^{-1}} \right]
$$
Calcoliamo i residui $k_{1},k_{2},k_{3}$:
$$
\begin{align*}
k_{1} &= V_{f}(s)s\bigg|_{s=0} = \dots = A_{i} \\ \\
k_{1} &= V_{f}(s)(s+\tau_{C}^{-1})\bigg|_{s=-\tau_{C}^{-1}} = \dots = \frac{A_{i}\tau_{i}^{-1}}{\tau_{c}^{-1}-\tau_{i}^{-1}} \\ \\
k_{1} &= V_{f}(s)(s+\tau_{i}^{-1})\bigg|_{s=-\tau_{i}^{-1}} = \dots = \frac{A_{i}\tau_{C}^{-1}}{\tau_{i}^{-1}-\tau_{C}^{-1}}
\end{align*}
$$
Applicando l'antitrasformata, troviamo:
$$
v_{f}(t) = \mathcal{L}^{-1}[V_{f}(s)] = \left[ A_{i} + \frac{A_{i}\tau_{i}^{-1}}{\tau_{c}^{-1}-\tau_{i}^{-1}}e^{ -\tau_{C}^{-1}t } +\frac{A_{i}\tau_{C}^{-1}}{\tau_{i}^{-1}-\tau_{C}^{-1}}e^{ -\tau_{i}^{-1}t } \right] \delta_{-1}(t)
$$
E quindi la risposta complessiva sarà:
$$
\begin{align*}
v(t) &= v_{l}(t) + v_{f}(t) = \\ \\
&= 2e^{ -3t }\delta_{-1}(t) + [ 6 + 12e^{ -3t } -18e^{ -2t } ]\delta_{-1}(t)
\end{align*} 
$$
>[!idea] OSSERVAZIONE IMPORTANTE
>Notiamo che per $t\to \infty$ alcuni termini *decadono a zero* (**risposta transitoria**), mentre altri *permangono* dopo che il *transitorio è esaurito* (**stato stazionario**).

Raccogliamo allora i termini in $v(t)$:
$$
v(t) = \underbrace{ [14e^{ -3t } -18e^{ -2t }] }_{ \text{transitorio} }\delta_{-1}(t) + \underbrace{ 6\delta_{-1}(t) }_{ \text{stato stazionario} }
$$

```tikz
\usepackage{pgfplots}

% Impostiamo una versione stabile precedente alla 1.18 come richiesto
\pgfplotsset{compat=1.15} 

\begin{document}
\begin{tikzpicture}

    % ==========================================
    % GRAFICO 1: Risposta Libera e Forzata
    % ==========================================
    \begin{axis}[
        name=plot1,
        width=9cm, 
        height=5.5cm,
        xmin=0, xmax=5,
        ymin=0, ymax=7,
        xlabel={[s]},
        ylabel={[V]},
        xtick={0,0.5,1,1.5,2,2.5,3,3.5,4,4.5,5},
        ytick={0,2,4,6},
        grid=major,
        grid style={solid, gray!30},
        legend pos=south east,
        legend cell align={left},
        legend style={nodes={scale=0.9, transform shape}},
        domain=0:5,
        samples=150,
        every axis plot/.append style={thick}
    ]
        % Risposta libera v_l(t)
        \addplot[red, dashed] {2*exp(-2.5*x)};
        \addlegendentry{$v_l(t)$}

        % Risposta forzata v_f(t)
        \addplot[blue] {6 - (6 + 15*x)*exp(-2.5*x)};
        \addlegendentry{$v_f(t)$}

        % Risposta totale v(t) = v_l(t) + v_f(t)
        \addplot[green] {6 - (4 + 15*x)*exp(-2.5*x)};
        \addlegendentry{$v(t)$}
    \end{axis}

    % ==========================================
    % GRAFICO 2: Transitorio e Regime Permanente
    % ==========================================
    \begin{axis}[
        name=plot2,
        at={(plot1.south east)},
        xshift=1.5cm, % Spaziatura tra i due grafici
        width=9cm, 
        height=5.5cm,
        xmin=0, xmax=5,
        ymin=-5, ymax=7,
        xlabel={[s]},
        ylabel={[V]},
        xtick={0,0.5,1,1.5,2,2.5,3,3.5,4,4.5,5},
        ytick={-4,-2,0,2,4,6},
        grid=major,
        grid style={solid, gray!30},
        legend pos=north east,
        legend cell align={left},
        legend style={nodes={scale=0.9, transform shape}},
        domain=0:5,
        samples=150,
        every axis plot/.append style={thick}
    ]
        % Transitorio v_t(t)
        \addplot[red, dashed] {-(4 + 15*x)*exp(-2.5*x)};
        \addlegendentry{$v_t(t)$}

        % Regime permanente v_ss(t)
        \addplot[blue] {6};
        \addlegendentry{$v_{ss}(t)$}

        % Risposta totale v(t) = v_t(t) + v_ss(t)
        \addplot[green] {6 - (4 + 15*x)*exp(-2.5*x)};
        \addlegendentry{$v(t)$}
    \end{axis}

\end{tikzpicture}
\end{document}
```

>[!note] COMMENTI SUI GRAFICI
>1. Nel grafico a sinistra vediamo l'andamento dell'*evoluzione libera* $v_{l}(t)$, della *risposta forzata* $v_{f}(t)$ e della *risposta complessiva* $v(t)$.
>2. Nel grafico a destra vediamo invece la **componente transitoria** e quella **stazionaria**: dopo un certo intervallo di tempo, la *risposta complessiva coincide con lo stato stazionario*.
# ANALOGIE
Esistono alcune analogie tra *sistemi meccanici, elettrici, termici, idraulici*, etc.

>[!note] NOTA
>Tali analogie erano *particolarmente utili in passato*, per **semplificare lo studio di alcuni sistemi**. 
>Ora abbiamo *strumenti di calcolo* sufficientemente avanzati che ci permettono di *simulare con precisione* anche sistemi complessi.

Per comprendere meglio le analogie, si definiscono *due tipi di variabili*:
1. Variabili **across**.
2. Variabili **through**.

>[!def] VARIABILE ACROSS
>Definisce una grandezza fisica che è **misurata ai capi di un componente** ed è pari alla differenza di valori ai due capi.
>
>Es. : *velocità, tensione, pressione, temperatura*.

>[!def] VARIABILE THROUGH
>Definisce una grandezza fisica che **rimane inalterata** attraversando un componente.
>
>Es. : *forza, corrente, portata di fluido, flusso termico*.
## ANALOGIA ELETTRO - MECCANICA
Vediamo più nel dettaglio l'analogia *elettro-meccanica*.

Componenti **elettrici**:
- *Tensione*: variabile *across*.
- *Corrente*: variabile *through*.

Componenti **meccanici**:
- *Velocità*: variabile *across*.
- *Forza*: variabile *through*.

| COMP. ELETTRICO | EQUAZIONE                 | COMP. MECCANICO | EQUAZIONE                            | TIPOLOGIA    |
| :-------------: | ------------------------- | --------------- | ------------------------------------ | ------------ |
|    RESISTORE    | $v(t)=Ri(t)$              | SMORZATORE      | $f(t)=bv(t)$                         | DISSIPATIVI  |
|    INDUTTORE    | $v(t)=L \frac{di(t)}{dt}$ | MASSA           | $f(t)=m \frac{dv(t)}{dt}$            | CONSERVATIVI |
|  CONDENSATORE   | $i(t)=C \frac{dv(t)}{dt}$ | MOLLA           | $v(t)= \frac{1}{k} \frac{df(t)}{dt}$ | CONSERVATIVI |
>[!note] NOTA
>Il *resistore* e lo *smorzatore* sono componenti **dissipativi**, mentre *induttore e condensatore* e *massa e molla* sono componenti **conservativi** (possono *immagazzinare energia e rilasciarla*).

>[!idea] OSSERVAZIONE IMPORTANTE
>Un caso di analogia di questo tipo che abbiamo già incontrato è dato dall'**oscillatore armonico smorzato** e dal **circuito RLC in serie**: le equazioni che descrivono i due sistemi hanno la *stessa forma* e sono in accordo con le analogie elencate in tabella.
# ELEMENTI CIRCUITALI NEL DOMINIO S
Vediamo ora in cosa "*vengono trasformati*" alcuni elementi circuitali dopo aver applicato la *trasformata di Laplace*. 

Cominciamo con il **resistore**:

```tikz
\usepackage{tikz}
\usepackage[american]{circuitikz} % 'american' per il simbolo a zig-zag del resistore
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}[font=\sffamily, x=1cm, y=1cm]

    % Colori personalizzati per corrispondere all'immagine
    \definecolor{myred}{RGB}{192,0,0}
    \definecolor{mygray}{RGB}{140,140,140}

    % Disegno della struttura della tabella (griglia)
    \draw[gray, thin] (0,0) rectangle (14, 4);
    \draw[gray, thin] (0, 3.4) -- (14, 3.4); % Riga orizzontale
    \draw[gray, thin] (4.3, 0) -- (4.3, 4);  % Riga verticale divisoria

    % Testi delle intestazioni
    \node at (2.15, 3.7) {dominio-t};
    \node at (9.15, 3.7) {dominio-s};

    % Titolo della riga (mantenuto il typo "Resitore" come nell'originale)
    \node[anchor=west, text=myred] at (0.1, 3.1) {Resitore};

    % ==========================================
    % CIRCUITO NEL DOMINIO DEL TEMPO (Sinistra)
    % ==========================================
    \begin{scope}[shift={(2.15, 1.5)}]
        % Resistore R e nodi neri terminali (*-*)
        \draw (0, 1) to[R, l_=$R$, *-*] (0, -1);
        
        % Freccia della corrente i (grigia)
        \draw[-stealth, mygray, thick] (0.7, 0.2) -- (0.7, -0.2);
        \node[anchor=east] at (1.1, 0) {$i$};
        
        % Polarità (+ e - grigi) e label della tensione (v)
        \node[anchor=west, text=mygray] at (0.1, 0.8) {$+$};
        \node[anchor=west, text=mygray] at (0.1, -0.8) {$-$};
        \node[anchor=west] at (0.2, 0) {$v$};
        
        % Equazione caratteristica
        \node at (-1.5, 0) {$v = Ri$};
    \end{scope}

    % ==========================================
    % CIRCUITO NEL DOMINIO DI LAPLACE (Destra)
    % ==========================================
    \begin{scope}[shift={(6.2, 1.5)}]
        % Resistore R e nodi neri terminali
        \draw (0, 1) to[R, l_=$R$, *-*] (0, -1);
        
        % Freccia della corrente I (grigia, vettore/fasore in grassetto)
        \draw[-stealth, mygray, thick] (1, 0.2) -- (1, -0.2);
        \node[anchor=east] at (1.4, 0) {$\mathbf{I}$};
        
        % Polarità (+ e - grigi) e label della tensione V in grassetto
        \node[anchor=west, text=mygray] at (0.1, 0.8) {$+$};
        \node[anchor=west, text=mygray] at (0.1, -0.8) {$-$};
        \node[anchor=west] at (0.2, 0) {$\mathbf{V}$};
        
        % Equazione caratteristica
        \node at (3, 0) {$V(s) = RI(s)$};
    \end{scope}

\end{tikzpicture}
\end{document}
```

Applicando la TDL all'equazione del resistore otteniamo semplicemente:
$$
\begin{align}
v = Ri(t) & &  \longrightarrow & &  V(s) = RI(s)
\end{align}
$$

>[!note] NOTA
>Dimensionalmente, $V(s)$ ed $I(s)$ hanno u.d.m., rispettivamente, $[V\cdot s]$ e $[A\cdot s]$.
>La resistenza, invece, mantiene l'u.d.m. $[\Omega]$.

Passiamo ora a **induttori** e **condensatori**.
Le loro equazioni caratteristiche dipendono da delle derivate; allora per le regole viste in > [[12 - STRUMENTI MATEMATICI#APPROCCIO OPERATIVO E PROPRIETA']], avremo dei termini dipendenti da $i_{L}(0^-)$ e $v_{C}(0^-)$.

>[!note] NOTA
>Questi termini corrispondono ("fisicamente") a *condizioni iniziali* associate ad un'"**energia in memoria**" che potrebbe esistere nei condensatori e negli induttori al tempo $t=0^-$.

```tikz
\usepackage{tikz}
\usepackage[american]{circuitikz}
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}[]

% Stile personalizzato per i riquadri rossi tratteggiati delle equazioni
\tikzset{eqbox/.style={draw=red!80!black, dashed, thick, inner sep=6pt}}

% Linee di separazione per creare la griglia
\draw[gray, thin] (3, 7.5) -- (3, -9.5); % Linea verticale
\draw[gray, thin] (-3, -1) -- (17, -1);  % Linea orizzontale

% ==========================================
% RIGA 1: INDUTTORE
% ==========================================
\node[text=red!80!black, font=\sffamily\large, anchor=west] at (-2.5, 7) {Induttore};

% --- Colonna 1: Dominio del Tempo ---
\draw (0,5.5) node[circle,fill,inner sep=1.2pt]{} -- (0,5)
      to[L, l_=$L$] (0,4) -- (0,3.5) node[circle,fill,inner sep=1.2pt]{};

% Etichette
\node[gray] at (0.6, 5.2) {$+$};
\node[gray] at (0.6, 3.8) {$-$};
\node at (0.6, 4.5) {$v_L$};
\draw[-latex, gray, thick] (-0.4, 5.2) -- (-0.4, 4.6);
\node[left] at (-0.4, 4.9) {$i_L$};

% Equazioni
\node[eqbox] at (0, 2) {$\displaystyle v_L(t) = L \frac{di_L(t)}{dt}$};
\node at (0, 0.5) {$\displaystyle i_L(t) = \frac{1}{L} \int_{0^-}^t v_L(\tau)d\tau + i_L(0^-)$};


% --- Colonna 2: Dominio di Laplace (Serie) ---
\draw (6.5,5.5) node[circle,fill,inner sep=1.2pt]{} -- (6.5,5)
      to[L, l_=$sL$] (6.5,4)
      to[V, invert] (6.5,3) -- (6.5,2.5) node[circle,fill,inner sep=1.2pt]{};

% Etichette
\node[left] at (5.8, 3.5) {$L i_L(0^-)$};
\node[gray] at (7.1, 5.2) {$+$};
\node[gray] at (7.1, 2.8) {$-$};
\node at (7.5, 4.0) {$\mathbf{V}_L$};
\draw[-latex, gray, thick] (5.7, 5.2) -- (5.7, 4.6);
\node[left] at (5.7, 4.9) {$\mathbf{I}_L$};

% Equazioni
\node[eqbox] at (6.5, 0.8) {$\displaystyle V_L(s) = sL I_L(s) - L i_L(0^-)$};


% --- Colonna 3: Dominio di Laplace (Parallelo) ---
\draw (12.5,5.5) node[circle,fill,inner sep=1.2pt]{} -- (12.5,5) -- (11.5, 5)
      to[L, l_=$sL$] (11.5, 3) -- (12.5, 3) -- (12.5,2.5) node[circle,fill,inner sep=1.2pt]{};
\draw (12.5,5) -- (13.5, 5)
      to[I] (13.5, 3) -- (12.5, 3); % Freccia in giù

% Etichette
\node[right] at (13.8, 4) {$\displaystyle \frac{i_L(0^-)}{s}$};
\node[gray] at (12.5, 4.5) {$+$};
\node[gray] at (12.5, 3.5) {$-$};
\node at (12.5, 4.0) {$\mathbf{V}_L$};
\draw[-latex, gray, thick] (12.9, 6.3) -- (12.9, 5.9);
\node[left] at (12.9, 6.1) {$\mathbf{I}_L$};

% Equazioni
\node at (12.5, 0.8) {$\displaystyle I_L(s) = \frac{V_L(s)}{sL} + \frac{i_L(0^-)}{s}$};


% ==========================================
% RIGA 2: CONDENSATORE
% ==========================================
\node[text=red!80!black, font=\sffamily\large, anchor=west] at (-2.5, -1.8) {Condensatore};

% --- Colonna 1: Dominio del Tempo ---
\draw (0,-2.5) node[circle,fill,inner sep=1.2pt]{} -- (0,-3)
      to[C, l_=$C$] (0,-4) -- (0,-4.5) node[circle,fill,inner sep=1.2pt]{};

% Etichette
\node[gray] at (0.6, -2.8) {$+$};
\node[gray] at (0.6, -4.2) {$-$};
\node at (0.6, -3.5) {$v_C$};
\draw[-latex, gray, thick] (-0.4, -2.8) -- (-0.4, -3.4);
\node[left] at (-0.4, -3.1) {$i_C$};

% Equazioni
\node[eqbox] at (0, -6.5) {$\displaystyle i_C(t) = C \frac{dv_C(t)}{dt}$};
\node at (0, -8) {$\displaystyle v_C(t) = \frac{1}{C} \int_{0^-}^t i_C(\tau)d\tau + v_C(0^-)$};


% --- Colonna 2: Dominio di Laplace (Serie) ---
\draw (6.5,-2.5) node[circle,fill,inner sep=1.2pt]{} -- (6.5,-3)
      to[C, l_=$\displaystyle\frac{1}{sC}$] (6.5,-4)
      to[V] (6.5,-5) -- (6.5,-5.5) node[circle,fill,inner sep=1.2pt]{};

% Etichette
\node[left] at (5.8, -4.5) {$\displaystyle\frac{v_C(0^-)}{s}$};
\node[gray] at (7.1, -2.8) {$+$};
\node[gray] at (7.1, -5.2) {$-$};
\node at (7.1, -4.0) {$\mathbf{V}_C$};
\draw[-latex, gray, thick] (5, -2.8) -- (5, -3.4);
\node[left] at (5, -3.1) {$\mathbf{I}_C$};

% Equazioni
\node at (6.5, -8) {$\displaystyle V_C(s) = \frac{I_C(s)}{sC} + \frac{v_C(0^-)}{s}$};


% --- Colonna 3: Dominio di Laplace (Parallelo) ---
\draw (12.5,-2.5) node[circle,fill,inner sep=1.2pt]{} -- (12.5,-3) -- (11.5, -3)
      to[C, l_=$\displaystyle\frac{1}{sC}$] (11.5, -5) -- (12.5, -5) -- (12.5,-5.5) node[circle,fill,inner sep=1.2pt]{};
\draw (12.5,-5) -- (13.5, -5)
      to[I] (13.5, -3) -- (12.5, -3); % Freccia in su (dal basso verso l'alto)

% Etichette
\node[right] at (13.8, -4) {$C v_C(0^-)$};
\node[gray] at (12.5, -3.5) {$+$};
\node[gray] at (12.5, -4.5) {$-$};
\node at (12.5, -4.0) {$\mathbf{V}_C$};
\draw[-latex, gray, thick] (12.9, -1.8) -- (12.9, -2.2);
\node[left] at (12.9, -2) {$\mathbf{I}_C$};

% Equazioni
\node[eqbox] at (12.5, -8) {$\displaystyle I_C(s) = sC V_C(s) - C v_C(0^-)$};

\end{tikzpicture}
\end{document}
```

>[!note] NOTA
>Come vediamo dalla figura, possiamo scrivere le *"componenti trasformate"* sia come *serie* che come *parallelo* di due elementi.
>L'elemento *"aggiuntivo"* è dovuto alle *condizioni iniziali*.

>[!idea] IMPEDENZA COMPLESSA
>Ricordiamo che per circuiti in *corrente variabile*, le resistenza viene generalizzata all'**impedenza**:
>$$ z(t) = \frac{v(t)}{i(t)} $$
>Nel dominio $s$ abbiamo:
>$$ Z(s) = \frac{V(s)}{I(s)} $$
>Ed è un'**impedenza complessa**.
## ESEMPIO
Consideriamo il circuito in figura:

```tikz
\usepackage{tikz}
\usepackage[american]{circuitikz}
\usepackage{amsmath}

\begin{document}

\begin{tikzpicture}[font=\small]

% --- GENERATORE DI TENSIONE ---
\draw[thick] (0,0) -- (0,0.7);
\draw[thick] (0,0.7) to[V, invert] (0,2.3);
\draw[thick] (0,2.3) -- (0,3);

% --- RAMO SUPERIORE E RESISTORE ---
\draw[thick] (0,3) -- (1.42,3); 
% Terminale aperto (grigio)
\draw[very thick, gray, fill=white] (1.5,3) circle (0.08);
% Resistore (nero) - Le parentesi graffe proteggono l'uguale dal parser
\draw[thick] (1.58,3) to[R, l={${R = 0.5\,\mathrm{M}\Omega}$}] (4.5,3);

% --- RAMO INFERIORE ---
\draw[thick] (0,0) -- (1.42,0);
% Terminale aperto (grigio)
\draw[very thick, gray, fill=white] (1.5,0) circle (0.08);
% Filo di ritorno inferiore
\draw[thick] (1.58,0) -- (4.5,0);

% --- CONDENSATORE ---
\draw[thick] (4.5,3) to[C, l_={${C = 1\,\mu\mathrm{F}}$}] (4.5,0);

% --- TERMINALI DI USCITA ---
% Filo superiore verso l'uscita
\draw[thick] (4.5,3) -- (5.92,3);
\draw[very thick, gray, fill=white] (6.0,3) circle (0.08);

% Filo inferiore verso l'uscita
\draw[thick] (4.5,0) -- (5.92,0);
\draw[very thick, gray, fill=white] (6.0,0) circle (0.08);

% --- ETICHETTE DI USCITA E TESTO ---
\node at (6.4, 3.0) {$+$};
\node at (6.4, 0.0) {$-$};
\node at (6.4, 1.5) {$v_o(t)$};

% Testo "dominio-t" formattato usando il parametro font nativo (sicuro su TikzJax)
\node[font=\sffamily\itshape] at (3.0, -0.4) {dominio-t};

\end{tikzpicture}

\end{document}
```

Con il generatore di tensione che genera la tensione $v_{i}(t)$ seguente:

```tikz
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}
    % Disegna il segnale (colore grigio scuro, linea spessa)
    \draw[black!75, line width=1.5pt] (-0.5,0) -- (0,0) -- (0,1) -- (1,1) -- (1,0) -- (1.5,0);
    
    % Testo a sinistra
    \node[left] at (-0.4, 0.5) {$v_i(t) =$};
    
    % Etichette inferiori (tempi)
    \node[below] at (0,0) {$0$};
    \node[below] at (1,0) {$1\text{ s}$};
    
    % Etichetta superiore (ampiezza)
    % Usiamo uno shift leggero per allinearlo come nell'immagine originale
    \node[above left, xshift=4pt] at (0,1) {$1\text{ V}$};
\end{tikzpicture}
\end{document}
```
Cioè:
$$
v_{i}(t) = \delta_{-1}(t) - \delta_{-1}(t-1)
$$
Assumiamo inoltre $v_{o}(0^-)=0$.
In base a quanto visto, il "*circuito trasformato*" nel dominio $s$ diventa:

```tikz
\usepackage{tikz}
\usepackage[american]{circuitikz}
\usepackage{amsmath}

\begin{document}

\begin{tikzpicture}[font=\small]

% --- GENERATORE DI TENSIONE ---
\draw[thick] (0,0) -- (0,0.7);
\draw[thick] (0,0.7) to[V, invert, l={${V_i(s)}$}] (0,2.3);
\draw[thick] (0,2.3) -- (0,3);

% --- RAMO SUPERIORE E RESISTORE ---
\draw[thick] (0,3) -- (1.42,3); 
% Terminale aperto (grigio)
\draw[very thick, gray, fill=white] (1.5,3) circle (0.08);
% Resistore (nero) - Le parentesi graffe proteggono l'uguale dal parser
\draw[thick] (1.58,3) to[R, l={${R = 0.5\,\mathrm{M}\Omega}$}] (4.5,3);

% --- RAMO INFERIORE ---
\draw[thick] (0,0) -- (1.42,0);
% Terminale aperto (grigio)
\draw[very thick, gray, fill=white] (1.5,0) circle (0.08);
% Filo di ritorno inferiore
\draw[thick] (1.58,0) -- (4.5,0);

% --- CONDENSATORE ---
\draw[thick] (4.5,3) to[C, l_={${\frac{1}{sC} }$}] (4.5,0);

% --- TERMINALI DI USCITA ---
% Filo superiore verso l'uscita
\draw[thick] (4.5,3) -- (5.92,3);
\draw[very thick, gray, fill=white] (6.0,3) circle (0.08);

% Filo inferiore verso l'uscita
\draw[thick] (4.5,0) -- (5.92,0);
\draw[very thick, gray, fill=white] (6.0,0) circle (0.08);

% --- ETICHETTE DI USCITA E TESTO ---
\node at (6.4, 3.0) {$+$};
\node at (6.4, 0.0) {$-$};
\node at (6.4, 1.5) {$V_o(s)$};

% Testo "dominio-t" formattato usando il parametro font nativo (sicuro su TikzJax)
\node[font=\sffamily\itshape] at (3.0, -0.4) {dominio-s};

\end{tikzpicture}

\end{document}
```
Innanzitutto scriviamo la trasformata di $v_{i}(t)$:
$$
V_{i}(s) = \frac{1}{s} - \frac{1}{s}e^{ -s }
$$
Ora, applicando il *partitore di tensione*, troviamo $V_{o}(s)$:
$$
V_{o}(s) = V_{i}(s) \frac{\frac{1}{sC}}{R+ \frac{1}{sC}} = \dots = V_{i}(s) \frac{2}{s+2}
$$
Per cui:
$$
V_{o}(s) = 2(1-e^{ -s })\left( \frac{1}{s(s+2)} \right)
$$
Scomponiamo in fratti semplici il secondo termine:
$$
\begin{align*}
\frac{1}{s(s+2)} &= \frac{C_{1}}{s} + \frac{C_{2}}{s+2} \\ \\
C_{1} &= s \frac{1}{s(s+2)}\bigg|_{s=0} = \frac{1}{2} \\ \\
C_{2} &= (s+2) \frac{1}{s(s+2)}\bigg|_{s=-2} = -\frac{1}{2}
\end{align*}
$$
Per cui troviamo:
$$
V_{o}(s) = \frac{1}{s} - \frac{1}{s+2} - \frac{1}{s}e^{ -s } + \frac{1}{s+2}e^{ -s }
$$
E infine, applicando l'antitrasformata, troviamo:
$$
v_{o}(t) = \delta_{-1}(t) - e^{ -2t }\delta_{-1}(t) -\delta_{-1}(t-1) + e^{ -2(t-1) }\delta_{-1}(t-1)
$$
Visualizziamo quindi ingresso e risposta nel seguente grafico:

```tikz
\usepackage{pgfplots}
\usepackage{amsmath}

% Definizione dei colori simili a quelli dell'immagine
\definecolor{myblue}{RGB}{0,174,239}
\definecolor{myred}{RGB}{237,28,36}

\begin{document}
\begin{tikzpicture}
\begin{axis}[
    axis lines = middle,
    axis line style = {-latex, thick},
    xlabel = {$t \text{ (s)}$},
    ylabel = {V},
    xmin = 0, xmax = 5.4,
    ymin = 0, ymax = 1.15,
    xtick = {0,1,2,3,4,5},
    ytick = {0,0.2,0.4,0.6,0.8,1.0},
    % Forza la formattazione a una cifra decimale per 1.0 come nell'immagine
    yticklabels = {0,0.2,0.4,0.6,0.8,1.0}, 
    tick align = inside,
    major tick length = 3pt,
    % Posizionamento delle etichette degli assi
    every axis x label/.style={at={(ticklabel* cs:1.0)}, anchor=west},
    every axis y label/.style={at={(ticklabel* cs:1.0)}, anchor=south},
    width = 10cm,
    height = 7cm,
    clip = false
]

% 1. Segnale di ingresso v_i(t) (Impulso rettangolare azzurro)
\addplot[myblue, line width=1.5pt] coordinates {(0,1) (1,1) (1,0)};

% 2. Segnale di uscita v_o(t) - Fase di salita/carica (0 <= t <= 1)
% Equazione: 1 - e^(-t/tau) con tau = 0.5
\addplot[myred, line width=1.5pt, domain=0:1, samples=100] {1 - exp(-2*x)};

% 3. Segnale di uscita v_o(t) - Fase di discesa/scarica (t > 1)
% Equazione: V_peak * e^(-(t-1)/tau)
\addplot[myred, line width=1.5pt, domain=1:5.2, samples=100] {(1 - exp(-2)) * exp(-2*(x-1))};

% Etichette di testo vicine ai grafici
\node[myblue, above] at (axis cs:0.5, 1) {$v_i(t)$};
\node[myred, right=12pt] at (axis cs:1.2, 0.35) {$v_o(t)$};

\end{axis}
\end{tikzpicture}
\end{document}
```

>[!idea] OSSERVAZIONE IMPORTANTE
>La *tensione di output* $v_{o}(t)$:
>1. *Non cresce immediatamente*, ma con una certa *gradualità*.
>2. *Non raggiunge* lo *stesso valore dell'input*, ma si "ferma" ad un *valore inferiore*.
>
>Potrebbero sembrare dei problemi, in realtà sono caratteristiche particolarmente *utili* nei sistemi di controllo perchè piccole variazioni graduali *riducono le sollecitazioni* sui componenti.
# RISPOSTA IN FREQUENZA
Nell'analisi dei sistemi è molto importante considerare anche la **risposta in frequenza**.

Per capire di cosa si tratta, consideriamo nuovamente un circuito RC:

```tikz
\usepackage{tikz}
\usepackage[american]{circuitikz}
\usepackage{amsmath}

\begin{document}

\begin{tikzpicture}[font=\small]

% --- GENERATORE DI TENSIONE ---
\draw[thick] (0,0) -- (0,0.7);
\draw[thick] (0,0.7) to[V, invert, l={${u(t)}$}] (0,2.3);
\draw[thick] (0,2.3) -- (0,3);

% --- RAMO SUPERIORE E RESISTORE ---
\draw[thick] (0,3) -- (1.42,3); 
% Terminale aperto (grigio)
\draw[very thick, gray, fill=white] (1.5,3) circle (0.08);
% Resistore (nero) - Le parentesi graffe proteggono l'uguale dal parser
\draw[thick] (1.58,3) to[R, l={${R}$}] (4.5,3);

% --- RAMO INFERIORE ---
\draw[thick] (0,0) -- (1.42,0);
% Terminale aperto (grigio)
\draw[very thick, gray, fill=white] (1.5,0) circle (0.08);
% Filo di ritorno inferiore
\draw[thick] (1.58,0) -- (4.5,0);

% --- CONDENSATORE ---
\draw[thick] (4.5,3) to[C, l_={${C}$}] (4.5,0);

% --- TERMINALI DI USCITA ---
% Filo superiore verso l'uscita
\draw[thick] (4.5,3) -- (5.92,3);
\draw[very thick, gray, fill=white] (6.0,3) circle (0.08);

% Filo inferiore verso l'uscita
\draw[thick] (4.5,0) -- (5.92,0);
\draw[very thick, gray, fill=white] (6.0,0) circle (0.08);

% --- ETICHETTE DI USCITA E TESTO ---
\node at (6.4, 3.0) {$+$};
\node at (6.4, 0.0) {$-$};
\node at (6.4, 1.5) {$x(t)$};

\end{tikzpicture}

\end{document}
```

Con parametri:
$$
\begin{align*}
R=1[\Omega] & & C=1[F] & & \tau:= RC = 1[s]
\end{align*}
$$
Stavolta consideriamo però un ingresso $u(t)$ di tipo *sinusoidale*:
$$
\begin{align*}
u(t) = \sin\omega t & & \omega = \frac{2\pi}{T} = 2\pi f
\end{align*}
$$
Applicando l'equazione delle maglie al circuito troviamo:
$$
-u(t) + RC \frac{dx(t)}{dt} + x(t) = 0
$$
Che riscriviamo come segue:
$$
\dot{x}(t) = -\frac{1}{\tau}x(t) + \frac{1}{\tau}u(t)
$$
Applicando la TDL troviamo:
$$
sX(s) - x(0) = -X(s) + U(s)
$$
Consideriamo la sola *risposta forzata*, cioè con condizioni iniziali nulle $x(0)=0$.
Abbiamo allora:
$$
(s+1)X(s) = U(S)
$$
Da cui:
$$
\begin{align*}
X(s) &= \frac{1}{s+1} U(s) \\ \\
&= \frac{1}{s+1} \frac{\omega}{s^2+\omega^2}
\end{align*}
$$
Ora scomponiamo $X(s)$:
$$
X(s) = \frac{R_{1}}{s+1} + \frac{R_{2}+R_{3}s}{s^2+\omega^2}
$$
Determiniamo $R_{1},R_{2},R_{3}$ eguagliando i *polinomi al numeratore*:
$$
\begin{align*}
\omega &= R_{1}(s^2+\omega^2) + (R_{2}+R_{3}s)(s+1) \\ \\ 
&= (R_{1}+R_{3})s^2 + (R_{2}+R_{3})s + R_{1}\omega^2 + R_{2}
\end{align*}
$$
E troviamo:
$$
\begin{align*}
\begin{cases}
R_{1} + R_{3} = 0 \\
R_{2} + R_{3} = 0 \\
R_{1}\omega^2 + R_{2} = \omega
\end{cases} & & \dots & & \begin{cases}
R_{1} = \frac{\omega}{1+\omega^2} \\
R_{2} = \frac{\omega}{1+\omega^2} \\
R_{3} = -\frac{\omega}{1+\omega^2}
\end{cases}
\end{align*}
$$
Per cui abbiamo, chiamando $M=\frac{1}{1+\omega^2}$:
$$
\begin{align*}
X(s) &= M\omega \frac{1}{s+1} + M \frac{\omega}{s^2+\omega^2} -M\omega \frac{s}{s^2+\omega^2} \\ \\
x(t) &= \mathcal{L}^{-1}[X(s)] \implies \\ \\ 
x(t) &= M\omega e^{ -t } + M[\sin\omega t -\omega \cos\omega t]
\end{align*}
$$
>[!idea] RISPOSTA *IN FREQUENZA*
>Notiamo che la risposta del sistema *dipende dal parametro* $\omega$, che era la *frequenza del segnale di input*.
>Analizzare la *risposta in frequenza* significa infatti *valutare la risposta del sistema* **in funzione della frequenza dell'input**.

Per studiare *più comodamente* la risposta del sistema, tuttavia, conviene riscrivere $x(t)$ in un'altra forma.

>[!note] NOTA
>Ricordiamo che possiamo riscrivere una *combinazione lineare di seno e coseno* come segue:
>$$ A\sin\omega t + B\cos\omega t = C\sin(\omega t+\varphi) $$
>Con:
>$$ \begin{align*} \begin{cases} A = C\cos\varphi \\ B = C\sin\varphi \end{cases} & & \begin{cases} C = \sqrt{ A^2 + B^2 } \\ \varphi = \arctan \frac{B}{A} \end{cases} \end{align*} $$

Nel nostro caso:
$$
\begin{align*}
A = 1 \text{ , } B = -\omega & & \implies & & C = \sqrt{ 1+\omega ^2 } \text{ , }\varphi=-\arctan\omega 
\end{align*}
$$
Per cui troviamo:
$$
\begin{align*}
x(t) &= \frac{\omega}{1+\omega^2}e^{ -t } + \frac{\sqrt{ 1+\omega^2 }}{1+\omega^2}\sin(\omega t+\varphi) \\ \\
&= \frac{\omega}{1+\omega^2}e^{ -t } + \frac{1}{\sqrt{ 1+\omega^2 }}\sin(\omega t+\varphi)
\end{align*}
$$

>[!idea] STABILITA' BIBO
>La risposta del sistema è data da una **fase transitoria** (*esponenziale decrescente*) e da una "**a regime**", che risulta essere *limitata*.
>
>Si parla allora di un sistema che presenta **stabilità BIBO** (*Bounded Input - Bounded Output*), o anche *stabilità esterna*: in corrispondenza di un *ingresso limitato* la *risposta del sistema* è anch'essa *limitata*.

In realtà, per essere precisi, dovremo parlare in questo caso di **stabilità BIBS** (*Bounded Input - Bounded State*) visto che per noi $x(t)$ rappresenta uno *stato del sistema* più che un "output".
## DIAGRAMMI DI BODE
Considerata la *risposta forzata* di un sistema LTI BIBS ad un *ingresso sinusoidale*, una volta *esaurito il transitorio* (quindi a regime) risulta:
$$
x_{r}(t) := \lim_{ t \to \infty } x(t) = \bigg| H(s)\big|_{s=j\omega} \bigg| \sin \bigg( \omega t + \mathrm{arg}\{ H(s) \}\big|_{s=j\omega} \bigg)
$$
Dove $H(s)$ è la **funzione di trasferimento tra ingresso e stato**:
$$
H(s) = \frac{X(s)}{U(s)}
$$
>[!check] VERIFICA
>Nell'esempio visto sopra abbiamo:
>$$ H(s) = \frac{1}{s+1} \implies H(j\omega) = \frac{1}{1+j\omega} $$
>Per comodità riscriviamo $H(j\omega)$:
>$$ H(j\omega) = \frac{1-j\omega}{1+\omega^2} = \frac{1}{1+\omega^2} -j \frac{\omega}{1+\omega^2}$$
>Allora abbiamo:
>$$ |H(j\omega)| = \frac{1}{\sqrt{ 1+\omega^2 }} $$
>E:
>$$ \mathrm{arg}\{ H(j\omega) \} = -\arctan\omega $$
>E troviamo infatti lo *stesso risultato visto sopra* $\square$.

>[!idea] CONSEGUENZA
>Questo ci dice che, in generale, la **risposta forzata** di un *sistema LTI BIBS* ad un *segnale di ingresso sinusoidale* con una *certa pulsazione* (*frequenza*) è *ancora* un **segnale sinusoidale** alla **stessa pulsazione**.
>Questo segnale, tuttavia, è **modificato** rispetto al segnale di ingresso **in ampiezza e sfasamento** di fattori corrispondenti al *modulo* e alla *fase* esibiti dalla *funzione di trasferimento* $H(s)$.

```tikz
\usepackage{amsmath}

\begin{document}
\begin{tikzpicture}[>=latex] % Imposta lo stile delle frecce

    % Disegna il blocco LTI (spessore della linea e dimensioni)
    \node[draw, thick, minimum width=3cm, minimum height=1.5cm] (lti) at (0,0) {\textsf{LTI}};

    % Freccia di ingresso
    \draw[->, thick] (-2.2, 0) -- (lti.west);

    % Testo di ingresso (allineato a destra rispetto alla coordinata x indicata)
    \node[anchor=east] at (-2.4, 0) {$u(t) = \sin \omega t$};
    \node[anchor=east] at (-2.4, -0.7) {$\omega = \frac{2\pi}{T} = 2\pi f$};

    % Freccia di uscita
    \draw[->, thick] (lti.east) -- (2.2, 0);

    % Testo di uscita (equazione)
    \node[anchor=west] at (2.3, 0) {$x_r(t) = |H(j\omega)| \sin(\omega t + \arg\{H(j\omega)\})$};

\end{tikzpicture}
\end{document}
```

Modulo e fase di $H(j\omega)$, in funzione della *pulsazione* $\omega$, possono essere rappresentati con i **diagrammi di Bode** (uno relativo al modulo e uno alla fase).
Nel nostro caso, per il circuito RC, abbiamo:
$$
H(j\omega) = \frac{1}{1+j\omega}
$$
E i diagrammi sono i seguenti:

```tikz
\usepackage{pgfplots}
\usepackage{amsmath}

% Definizione dei colori per ricalcare il grafico
\definecolor{myblue}{RGB}{0,114,189}
\definecolor{myred}{RGB}{192,30,30}
\definecolor{mypurple}{RGB}{128,0,128}
\definecolor{fillbg}{RGB}{235,246,249}
\definecolor{boxbg}{RGB}{235,246,249}
\definecolor{boxtext}{RGB}{12,107,137}

\begin{document}
\begin{tikzpicture}

% ==========================================
% 1. DIAGRAMMA DI BODE - MODULO
% ==========================================
\begin{semilogxaxis}[
    name=mag,
    width=14cm, height=5.5cm,
    title={\textbf{Diagramma di Bode - Modulo (in decibel, $\mathbf{20\log_{10}Modulo}$)}},
    xlabel={pulsazione ($\log_{10}\omega$)},
    ylabel={dB},
    xmin=0.01, xmax=100,
    ymin=-45, ymax=10,
    ytick={-40,-30,-20,-10,0,10},
    grid=both,
    grid style={dotted, gray!60},
    major grid style={dotted, gray!90},
    axis on top=true,
    scale only axis
]

% Sfondo della Banda Passante
\fill[fillbg] (axis cs:0.01,-45) rectangle (axis cs:1,10);

% Curva della funzione del modulo: 20*log10(1/sqrt(1+w^2)) = -10*log10(1+w^2)
\addplot[myblue, thick, domain=0.01:100, samples=200] {-10*log10(1+x^2)};

% Linee di taglio (rosse tratteggiate)
\draw[myred, thick, dashed] (axis cs:0.01,-3) -- (axis cs:1,-3) 
    node[pos=0.45, below, text=mypurple, yshift=-1pt] {$20\log_{10}H(j) = -3\text{db}$};
\draw[myred, thick, dashed] (axis cs:1,-45) -- (axis cs:1,-3);

% Etichetta frequenza di taglio
\node[mypurple, right, align=left] at (axis cs:1.05,-35) {\textsf{pulsazione di taglio} \\ $\omega = 1\text{rad/s}$};

\end{semilogxaxis}

% ==========================================
% 2. DIAGRAMMA DI BODE - FASE
% ==========================================
\begin{semilogxaxis}[
    name=phase,
    at={(mag.below south west)},
    anchor=north west,
    yshift=-1.8cm, % Spazio verticale tra i due grafici
    width=14cm, height=5.5cm,
    title={\textbf{Diagramma di Bode - Fase}},
    xlabel={pulsazione ($\log_{10}\omega$)},
    ylabel={gradi},
    xmin=0.01, xmax=100,
    ymin=-100, ymax=10,
    ytick={-100,-80,-60,-40,-20,0},
    grid=both,
    grid style={dotted, gray!60},
    major grid style={dotted, gray!90},
    axis on top=true,
    scale only axis
]

% Sfondo della Banda Passante
\fill[fillbg] (axis cs:0.01,-100) rectangle (axis cs:1,10);

% Curva della funzione della fase: -arctan(w) in gradi
\addplot[myblue, thick, domain=0.01:100, samples=200] {-atan(x)};

% Linee di taglio (rosse tratteggiate)
\draw[myred, thick, dashed] (axis cs:0.01,-45) -- (axis cs:1,-45) 
    node[pos=0.45, above, text=mypurple, yshift=1pt] {$\arg\{H(j)\} = -45^\circ$};
\draw[myred, thick, dashed] (axis cs:1,-100) -- (axis cs:1,-45);

% Etichetta frequenza di taglio
\node[mypurple, right, align=left] at (axis cs:1.05,-90) {\textsf{pulsazione di taglio} \\ $\omega = 1\text{rad/s}$};

\end{semilogxaxis}

% ==========================================
% 3. ELEMENTI TESTUALI A SINISTRA
% ==========================================

% Etichetta a fianco del Modulo
\node[anchor=east] at ([xshift=-1cm, yshift=1cm]mag.west) {
    $20\log_{10}|H(j\omega)|$
};

% Etichetta a fianco della Fase
\node[anchor=east] at ([xshift=-1cm, yshift=1.5cm]phase.west) {
    $\arg\{H(j\omega)\}$
};

\end{tikzpicture}
\end{document}
```

Come vediamo dalla figura, al *crescere della frequenza* del *segnale in input*, il *segnale in uscita* viene *attenuato sempre di più* (**ATTENZIONE**: il modulo è rappresentato in termini di *guadagno in decibel*).

>[!note] NOTA: PULSAZIONE DI TAGLIO
>Nei diagrammi è indicata la **pulsazione di taglio**, pari a $\omega=1\mathrm{rad}/\mathrm{s}$.
>A questo valore è associato un segnale in uscita di ampiezza pari a $\frac{1}{\sqrt{ 2 }}$ di quella del segnale in input.
>In fisica, la *potenza* associata ad un segnale è proporzionale al *quadrato dell'ampiezza*.
>$$ P \propto A^2 $$
>Per questo la *pulsazione di taglio* è detta anche **frequenza di mezza potenza**.

>[!idea] FILTRO PASSA-BASSO
>Il circuito RC considerato è un esempio di **filtro passa-basso**.
>Dai diagrammi è facile capire come mai si chiama così:
>- Segnali a **bassa frequenza** vengono *"lasciati passare"*.
>- Segnali ad **alta frequenza** vengono *fortemente attenutati* (e quindi ignorati).

Infine, per quanto riguarda la fase, anche questa *aumenta* (ma in *negativo*) all'*aumentare della frequenza*: ciò significa che la *risposta del sistema* a *segnali ad alta frequenza* è *sempre più lenta* (frequenza maggiore $\to$ *sfasamento maggiore* $\to$ *ritardo nella risposta*).