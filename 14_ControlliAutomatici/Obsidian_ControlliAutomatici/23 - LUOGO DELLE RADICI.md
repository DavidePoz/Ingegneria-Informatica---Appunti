# INDICE SEZIONE
- [ ] [[#INTRODUZIONE]]
      - [[#ESEMPIO INTRODUTTIVO]]
- [ ] [[#LUOGO DELLE RADICI]]
      - [[#EQUAZIONI DEL LUOGO]]
- [ ] [[#REGOLE PER IL TRACCIAMENTO DEL LUOGO]]
      - [[#PUNTI DOPPI]]
      - [[#ESEMPI TRACCIAMENTO DEL LUOGO]]
      - [[#PUNTI MULTIPLI]]
- [ ] [[#ESEMPIO - CONTROLLO ORIENTAMENTO DI UN SATELLITE]]
      - [[#CONTROLLO P]]
      - [[#CONTROLLO PD]]
      - [[#CONTROLLORE PD REALE]]
- [ ] [[#CONTROLLO CON FLESSIBILITA']]
      - [[#CASO CO - LOCATO]]
      - [[#CASO NON CO - LOCATO]]
# INTRODUZIONE
Abbiamo visto che, dato un sistema $G(s)$, applicando un *controllore standard PID* $C(s)$, otteniamo:
$$
L(s) = \frac{k_{d}s^2 + k_{p}s + k_{i}}{s}G(s)
$$
E da questa ricaviamo la **FdT di anello chiuso**:
$$
W(s) = \frac{L(s)}{1+L(s)}
$$
Fin'ora abbiamo visto **criteri di stabilità** per determinare se un sistema è *asintoticamente stabile*, *BIBO stabile* o *instabile* (es. criterio di *Routh*) ed il **legame** tra i **vincoli da rispettare** (imposti dalle richieste progettuali) e la **regione ammissibile del piano complesso** in cui possono trovarsi i **poli della FdT** $W(s)$.

>[!idea] NECESSITA' DI UN ULTERIORE STRUMENTO
>Chiaramente, la **posizione dei poli** di $W(s)$ **dipende dai parametri del controllore**. 
>Oltre a *determinare la regione ammissibile*, vorremo avere uno strumento che ci permetta di capire esattamente **come varia** la loro posizione al variare di $k_{p},k_{i},k_{d}$: questo ci permetterebbe di progettare un **ottimo controllore**, anzichè limitarci ad uno che "semplicemente funziona".
## ESEMPIO INTRODUTTIVO
Per osservare *come cambia la posizione dei poli* al *variare dei parametri di controllo*, consideriamo un sistema d'esempio con la FdT seguente:
$$
G(s) = \frac{A}{s(\tau s+1)}
$$
Per semplicità, immaginiamo che il controllore sia *semplicemente proporzionale*.
Abbiamo quindi:
$$
L(s) = k_{p}G(s) = \frac{k_{p}A}{s(1+\tau s)}
$$
Per cui risulta:
$$
\begin{align*}
W(s) &= \frac{k_{p}A}{s(1+\tau s)+k_{p}A} \\ \\
&= \frac{k_{p}A/\tau}{s^2 + s/\tau + k_{p}A/\tau} \\ \\
&= \frac{K}{s^2 + s/\tau + K}
\end{align*}
$$
Consideriamo ora il seguente problema:

>[!tldr] OBIETTIVO
>Sia $\tau=1$. Determinare il **valore di K** in modo che $W(s)$ sia *stabile* e:
>1. Abbia **poli reali distinti**, oppure
>2. Abbia **poli complessi** coniugati con $\xi=0.5$.

Abbiamo:
$$
W(s) = \frac{K}{s^2 + s + K}
$$
I poli di $W(s)$ sono:
$$
p_{1,2} = -\frac{1}{2} \pm \frac{1}{2}\sqrt{ 1-4K }
$$
Per cui abbiamo:
1. $K< \frac{1}{4}$ : radici **reali distinte**.
2. $K= \frac{1}{4}$ : radici **reali coincidenti**.
3. $K> \frac{1}{4}$ : radici **complesse** con $\mathrm{Re}(p_{1,2})= -\frac{1}{2}$.

Per quanto riguarda la seconda richiesta, se vogliamo $\xi=0.5$, l'*angolo* $\varphi$ *formato con l'asse reale* deve essere tale che:
$$
\varphi = \arccos \frac{1}{2} = \frac{\pi}{3}
$$
Per cui abbiamo:
$$
\begin{align*}
\left|\frac{\omega_{n}}{\sigma} \right| = \sqrt{ 4K-1 } &:= \tan \frac{\pi}{3} = \sqrt{ 3 } \\ \\
\implies K &= 1
\end{align*}
$$

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}

\begin{document}
\begin{tikzpicture}[scale = 1.2]
\begin{axis}[
    width=12cm,
    height=10cm,
    xmin=-2, xmax=1,
    ymin=-1, ymax=1,
    xlabel={Real Axis},
    ylabel={Imaginary Axis},
    xtick={-2, -1.5, -1, -0.5, 0, 0.5, 1},
    ytick={-1, -0.8, -0.6, -0.4, -0.2, 0, 0.2, 0.4, 0.6, 0.8, 1},
    xtick pos=both, % Tick su entrambi i lati (sopra e sotto)
    ytick pos=both, % Tick su entrambi i lati (destra e sinistra)
    xtick align=inside,
    ytick align=inside,
    tick label style={font=\sffamily\small},
    label style={font=\sffamily\small},
    % Formattazione dei numeri per evitare zeri superflui
    x tick label style={/pgf/number format/fixed, /pgf/number format/precision=1},
    y tick label style={/pgf/number format/fixed, /pgf/number format/precision=1},
]

% 1. Disegna gli assi cartesiani centrali (x=0, y=0)
\draw[black, thick] (axis cs:-2,0) -- (axis cs:1,0);
\draw[black, thick] (axis cs:0,-1) -- (axis cs:0,1);

% 2. Disegna le linee continue del luogo delle radici (Rosse)
\draw[red, line width=1.5pt] (axis cs:-1,0) -- (axis cs:0,0);
\draw[red, line width=1.5pt] (axis cs:-0.5,-0.87) -- (axis cs:-0.5,0.87);

% 3. Disegna le radici discrete (Croci x)
% Croci sull'asse reale
\addplot [only marks, mark=x, green!70!black, mark size=2pt, thick, domain=-1:-0.5, samples=15] {0};
\addplot [only marks, mark=x, blue, mark size=2pt, thick, domain=-0.5:0, samples=15] {0};

% Croci sui rami complessi coniugati (s = -0.5 +/- jw)
\addplot [only marks, mark=x, green!70!black, mark size=2pt, thick, domain=-0.866:0, samples=20, variable=\t] ({-0.5}, {\t});
\addplot [only marks, mark=x, blue, mark size=2pt, thick, domain=0:0.866, samples=20, variable=\t] ({-0.5}, {\t});

% 4. Linea tratteggiata per K=1
\draw[dashed, semithick] (axis cs:0,0) -- (axis cs:-0.5, 0.866);

% 5. Arco per l'angolo theta (dal raggio della linea tratteggiata)
\draw[black, semithick] (axis cs:-0.25, 0) arc (180:120:0.25);
\node[text=blue, font=\sffamily\bfseries\huge] at (axis cs:-0.28, 0.25) {$\varphi$};

\end{axis}
\end{tikzpicture}
\end{document}
```

>[!note] INTERPRETAZIONE GRAFICO
>Dalla figura notiamo che:
>1. Per $0<K< \frac{1}{4}$ le radici sono reali, *"partono"* da $-1$ e $0$ e *convergono verso* $z=\frac{1}{2}$.
>2. Per $K=\frac{1}{4}$ le radici sono *reali coincidenti*.
>3. Per $K> \frac{1}{4}$ le radici *diventano complesse* e si *allontanano* sempre più dall'asse reale.
>4. Per $K=1$ abbiamo lo *smorzamento richiesto* dal problema.

Immaginiamo ora di *fissare* $K=1$ e voler studiare la dinamica del sistema al variare di $\frac{1}{\tau}=c>0$.
Abbiamo:
$$
\begin{align*}
W(s) = \frac{1}{s^2 + cs +1} & & p_{1,2} = -\frac{c}{2} \pm \frac{1}{2}\sqrt{ c^2 -4 }
\end{align*}
$$
Notiamo che:
1. Per $0<c<2$ le radici sono **complesse coniugate**.
2. Per $c=2$ le radici sono **reali coincidenti**.
3. Per $c>2$ le radici sono **reali distinte**.

In particolare, l'andamento della posizione delle radici nel piano complesso è il seguente:

```tikz
\usepackage{tikz}
\usepackage{pgfplots}

% Imposta la compatibilità per pgfplots
\pgfplotsset{compat=1.16}

\begin{document}

\begin{tikzpicture}
\begin{axis}[
    width=12cm,
    height=9.6cm,
    xmin=-2, xmax=1,
    ymin=-1, ymax=1,
    xtick={-2,-1.5,-1,-0.5,0,0.5,1},
    ytick={-1,-0.8,-0.6,-0.4,-0.2,0,0.2,0.4,0.6,0.8,1},
    xlabel={Real Axis},
    ylabel={Imag Axis},
    axis on top,
    tick align=inside,
    % Stile del box e dei tick
    axis line style={black},
    tick style={black},
    enlargelimits=false
]

% Disegna gli assi passanti per lo zero (x=0 e y=0)
\draw[black, thin] (axis cs:-2,0) -- (axis cs:1,0);
\draw[black, thin] (axis cs:0,-1) -- (axis cs:0,1);

% --- LUOGO DELLE RADICI (Linea continua rossa) ---
% Semicerchio sinistro (raggio 1)
\addplot [red, very thick, domain=90:270, samples=100] ({cos(x)}, {sin(x)});
% Asse reale negativo (da 0 verso -infinito)
\addplot [red, very thick] coordinates {(-2,0) (0,0)};

% --- MARKER DELLE RADICI AL VARIARE DI c ---

% Ramo 1 (blu): c < 2 (Semicerchio superiore)
\addplot [
    only marks, mark=x, mark size=2.5pt, blue, thick, 
    domain=0:1.96, samples=20, variable=\c
] ({-\c/2}, {sqrt(4-\c^2)/2});
    
% Ramo 2 (verde): c < 2 (Semicerchio inferiore)
\addplot [
    only marks, mark=x, mark size=2.5pt, green!60!black, thick, 
    domain=0:1.96, samples=20, variable=\c
] ({-\c/2}, {-sqrt(4-\c^2)/2});
    
% Ramo 1 (blu): c > 2 (Ramo sull'asse reale da -1 verso lo zero)
\addplot [
    only marks, mark=x, mark size=2.5pt, blue, thick, 
    domain=2.04:20, samples=40, variable=\c
] ({-\c/2 + sqrt(\c^2-4)/2}, {0});
    
% Ramo 2 (verde): c > 2 (Ramo sull'asse reale da -1 verso -infinito)
% Nota: ci fermiamo a c=2.5 perché corrisponde esattamente a x=-2 (limite grafico)
\addplot [
    only marks, mark=x, mark size=2.5pt, green!60!black, thick, 
    domain=2.04:2.5, samples=7, variable=\c
] ({-\c/2 - sqrt(\c^2-4)/2}, {0});

% Zero nell'origine 
\addplot [only marks, mark=o, mark size=3pt, blue, thick] coordinates {(0,0)};

\end{axis}
\end{tikzpicture}

\end{document}
```

>[!note] INTERPRETAZIONE GRAFICO
>Al variare di $c$, $\omega_{n}$ *rimane costante* ($=1$). 
>**Cambia** però lo **smorzamento**, che aumenta fino a diventare *critico* per $c=2$ e raggiungere la zona di *sovrasmorzamento* per $c>2$.
# LUOGO DELLE RADICI
Consideriamo un *sistema in retroazione* costituito da un sistema $G(s)$ ed un controllore $C(s)$, con *FdT di anello chiuso* $W(s)$.
Sappiamo che:
$$
W(s) = \frac{C(s)G(s)}{1+ C(s)G(s)} = \frac{L_{a}(s)}{1+ L_{a}(s)}
$$
Possiamo scrivere il denominatore nella forma seguente:
$$
1+KL(s) = 1 + K \frac{b(s)}{a(s)}
$$
Con $a(s)$ e $b(s)$ **polinomi monici coprimi**.
Notiamo allora che i **poli di W** sono le **radici** del seguente polinomio:
$$
p_{K}(s) = a(s) + Kb(s)
$$
E formuliamo così il problema discusso sopra:

>[!idea] PROBLEMA / OBIETTIVO
>Determinare **come variano** gli **zeri** di $p_{K}(s)$ al **variare di K** in $\mathbb{R}$.

Definiamo quindi il **luogo delle radici** $L$:

>[!def] LUOGO DELLE RADICI
>$$ L = \{ s \in \mathbb{C} : \exists K \text{ t.c } p_{K}(s) = 0 \} $$
>
>**NOTA**: Per $K\geq0$ si parla di *luogo positivo*, altrimenti si parla di *luogo negativo*.

>[!idea] FATTO IMPORTANTE
>La **funzione** che **associa** ad un *polinomio monico di grado* $n$ le *sue* $n$ **radici** è **continua**.
>
>Di conseguenza, *al variare* di $K$, ciascuna **radice** del polinomio $p_{K}(s)$ descrive una **curva continua** in $\mathbb{C}$.

Facciamo alcune *osservazioni preliminari*:
1. Consideriamo solo $K\geq 0$ e assumiamo che $m\leq n$, dove $m$ è il grado di $b(s)$ e $n$ è il grado di $a(s)$ (Assumiamo $L(s)$ **frazione propria**).
2. A *ciascun valore di* $K$ corrispondono **n radici** del polinomio $p_{K}(s)$ (contate con la propria molteplicità). Intuiamo quindi che il **luogo ha n "rami"**, cioè $n$ *curve parametrizzate* in $K$, che si intersecano per i valori di $K$ a cui corrispondono *radici multiple* di $p_{K}(s)$.
3. E' **possibile** una **costruzione per punti**.
## EQUAZIONI DEL LUOGO
Per prima cosa, dobbiamo porre $L(s)$ nella *forma fattorizzata di Evans*, cioè:
$$
\begin{align*}
L(s) &= \frac{s^m + b_{1}s^{m-1} + \dots + b_{m}}{s^n + a_{1}s^{n-1} + \dots + a_{n}} \\ \\
&= \frac{(s-z_{1})(s-z_{2})\dots(s-z_{m})}{(s-p_{1})(s-p_{2})\dots(s-p_{n})}
\end{align*}
$$
E notiamo che le *radici* di $p_{K}(s)$ sono le **soluzioni dell'equazione** seguente:
$$
\frac{b(s)}{a(s)} = -\frac{1}{K} \text{ }\forall K \ne 0
$$
>[!note] NOTE
>Assumendo $K>0$, $-\frac{1}{K}$ è un *numero reale negativo*.
>Tuttavia $\frac{b(s)}{a(s)}$ è un *numero complesso*.
>Dall'equazione sopra ricaviamo allora **due equazioni**, associate ad una **condizione di fase** e una **condizione di modulo**.

Condizione di **fase**: individua i punti $s \in \mathbb{C}$ per cui $\frac{b(s)}{a(s)}$ giace sull'*asse reale negativo*:
$$
\arg\left[ \frac{b(s)}{a(s)} \right] = (2h+1)\pi \text{ , }h\in \mathbb{Z}
$$
Tale equazione può essere riformulata nella seguente forma:
$$
\begin{align*}
&\sum_{i=1}^m \arg(s-z_{i}) - \sum_{i=1}^n\arg(s-p_{i}) = \\ \\
&= \sum_{i=1}^m \psi_{i} - \sum_{i=1}^n \phi_{i} = (2h+1)\pi \text{ , }h\in \mathbb{Z}
\end{align*}
$$
Condizione di **modulo**: determina, a meno del segno, il valore di $K$ cui *corrisponde un particolare punto del luogo*:
$$
\left| \frac{b(s)}{a(s)} \right| = \frac{1}{|K|}
$$
Anche questa equazione viene riformulata in una forma più comoda:
$$
|K| = \frac{\prod_{i=1}^n |(s-p_{i})| }{\prod_{i=1}^m |(s-z_{i})| }
$$
>[!note] COMMENTO SULLE EQUAZIONI
>La *prima equazione* ci permette di *capire se un punto appartiene al luogo* o meno; la *seconda* ci permette di *trovare il valore* di $K$ per cui *si ha una radice nel punto trovato* con la prima.
# REGOLE PER IL TRACCIAMENTO DEL LUOGO
Vediamo ora quali sono le *regole* per il tracciamento del luogo delle radici.
Cominciamo innanzitutto con la seguente osservazione:

>[!idea] OSSERVAZIONE IMPORTANTE
>Per ogni $K>0$, $p_{K}(s)$ ha **n radici**, quindi il **luogo ha n rami**.
>Siccome le radici figurano a *coppie complesse coniugate*, il **luogo è simmetrico** rispetto all'**asse reale**.

Avevamo riscritto l'equazione nella forma:
$$
L(s) = \frac{b(s)}{a(s)} = -\frac{1}{K}
$$
Tuttavia, ricordiamo che eravamo partiti da:
$$
p_{K} = a(s) + Kb(s) = 0
$$
Questo ci permette di studiare il **comportamento limite** nei seguenti casi:
1. **CASO K=0**. L'equazione diventa banalmente: $a(s) = 0$, per cui il luogo è *costituito dalle radici* di $a(s)$.
2. **CASO K INFINITO**. In tal caso:
   - $m$ punti **tendono agli zeri** del sistema, cioè le radici di $b(s)$.
   - $n-m$ punti **tendono all'infinito** lungo $n-m$ **semirette asintotiche** che formano una **stella regolare** con **centro sull'asse reale**.

I parametri della stella sono i seguenti.

>[!def] PARAMETRI DELLA STELLA
>Il **centro** della stella si trova nel punto:
>$$ \alpha = \frac{\sum_{i=1}^n p_{i} - \sum_{i=1}^m z_{i}}{n-m} $$
>E l'**inclinazione degli asintoti** è data da:
>$$ \phi_{l} = \frac{2l+1}{n-m}\pi \text{ , }l=0,1,\dots,n-m-1 $$

L'analisi del comportamento limite è utile perchè ci permette di capire *come sono fatti i rami* del luogo delle radici.

>[!idea] ANDAMENTO DEI RAMI
>Ogni **ramo esce da un polo** di $L(s)$ (quindi da una *radice* di $a(s)$) e **tende ad uno zero** di $L(s)$ (quindi ad una radice di $b(s)$) **oppure verso infinito**.

Se $n=m$, il *luogo non va all'infinito* per $K\to +\infty$ perchè *tutti i rami tendono alle radici* di $b(s)$.

Il luogo delle radici potrebbe inoltre **attraversare l'asse immaginario** in alcuni punti.

>[!note] ATTRAVERSAMENTO DELL'ASSE IMMAGINARIO
>Gli *eventuali* punti di *attraversamento dell'asse immaginario* ed il corrispondente valore di $K$ si determinano usando il **criterio di Routh**, cercando la *transizione da stabilità a instabilità*.
## PUNTI DOPPI
Spesso i *luoghi delle radici* comprendono dei "punti particolari" detti **punti doppi**.

>[!note] NOTA: PORZIONE DELL'ASSE REALE APPARTENENTE AL LUOGO
>Il punto $\sigma \in \mathbb{R}$ **appartiene al luogo** *se e solo se* il **numero** totale **di zeri e poli** al finito di $L(s)$ **a destra** di $\sigma$ è **dispari**.

Siano $p_{1},p_{2}$ due **poli reali** (radici di $a(s)$) **non separati da uno zero reale** (radice di $b(s)$) (o, *viceversa*, siano $z_{1},z_{2}$ *due zeri reali non separati da un polo reale*), e il **segmento che li congiunge** appartenga al **luogo**, come in figura:

```tikz
\usetikzlibrary{arrows.meta, calc}

% Definizione colori personalizzati
\definecolor{myorange}{RGB}{255, 69, 0}
\definecolor{myblue}{RGB}{0, 0, 255}

\begin{document}
\begin{tikzpicture}[
    >={Stealth[length=3mm, width=2.5mm]},
    arrow line/.style={line width=1.5pt},
    axis line/.style={line width=0.6pt, black},
    marker/.style={myblue, line width=2pt},
    label font/.style={font=\huge\sffamily\bfseries, text=myblue}
]

% Macro per disegnare le radici (poli e zeri)
\newcommand{\drawpole}[1]{
    \draw[marker] (#1) +(-0.15,-0.15) -- +(0.15,0.15);
    \draw[marker] (#1) +(-0.15,0.15) -- +(0.15,-0.15);
}
\newcommand{\drawzero}[1]{
    \draw[marker] (#1) circle (0.15);
}

% ==========================================
% DIAGRAMMA DI SINISTRA (Poli)
% ==========================================
\begin{scope}
    % Asse orizzontale
    \draw[axis line] (-3.2, 0) -- (1.8, 0);

    % Frecce Orizzontali (con punta intermedia)
    \draw[->, arrow line, black] (-2, 0) -- (-0.8, 0);
    \draw[arrow line, black] (-0.8, 0) -- (0, 0);

    \draw[->, arrow line, myorange] (1.5, 0) -- (0.6, 0);
    \draw[arrow line, myorange] (0.6, 0) -- (0, 0);

    % Frecce Verticali
    \draw[->, arrow line, myorange] (0, 0) -- (0, 1.1);
    \draw[->, arrow line, black] (0, 0) -- (0, -1.1);

    % Marker (Poli)
    \drawpole{-2,0}
    \drawpole{1.5,0}

    % Etichette
    \node[label font, anchor=north] at (-2, -0.4) {$p_1$};
    \node[label font, anchor=north] at (1.5, -0.4) {$p_2$};
    \node[label font, anchor=south east] at (0, 0.1) {$s^*$};
\end{scope}

% ==========================================
% DIAGRAMMA DI DESTRA (Zeri)
% ==========================================
\begin{scope}[xshift=8.5cm] % Spaziatura tra i due diagrammi
    % Asse orizzontale
    \draw[axis line] (-2.5, 0) -- (2.5, 0);

    % Frecce Orizzontali (con punta intermedia)
    \draw[->, arrow line, black] (0, 0) -- (-0.8, 0);
    \draw[arrow line, black] (-0.8, 0) -- (-1.5, 0);

    \draw[->, arrow line, myorange] (0, 0) -- (0.8, 0);
    \draw[arrow line, myorange] (0.8, 0) -- (1.5, 0);

    % Frecce Verticali
    \draw[->, arrow line, black] (0, -1.1) -- (0, 0);
    \draw[->, arrow line, myorange] (0, 1.1) -- (0, 0);

    % Marker (Zeri)
    \drawzero{-1.5,0}
    \drawzero{1.5,0}

    % Etichette
    \node[label font, anchor=north] at (-1.5, -0.4) {$z_1$};
    \node[label font, anchor=north] at (1.5, -0.4) {$z_2$};
    \node[label font, anchor=south east] at (0, 0.1) {$s^*$};
\end{scope}

\end{tikzpicture}
\end{document}
```

>[!def] PUNTO DOPPIO
>Punto $s^*$ in cui **si incontrano due rami del luogo**.
>
>**NOTA**: sono associati a valori di $K$ per cui $p_{K}(s)$ ha una radice con *molteplicità due* in $s^*$.

E' possibile dare una *caratterizzazione analitica* dei punti doppi: se $s^*$ è una *radice doppia* di $p_{K}(s)$, allora vale che:
$$
\begin{align*}
p_{K}(s) &= (s-s^*)^2q(s) \\ \\
&\implies \frac{d}{ds}p_{K}(s) = 2(s-s^*)q(s) + (s-s^*)^2 \frac{d}{ds}q(s) \\ \\
&\implies \frac{d}{ds}p_{K}(s)\bigg|_{s=s^*} = 0
\end{align*}
$$
Per cui:

>[!th] CARATTERIZZAZIONE DEI PUNTI DOPPI
>Il punto $s^*$ è **punto doppio** del luogo se:
>$$ \begin{cases} p_{K}(s^*) = 0 \\ \\ \displaystyle\frac{d}{ds}p_{K}(s)\bigg|_{s=s^*} = 0 \end{cases} $$

>[!note] NOTA
>Se abbiamo *due poli o due zeri* come sopra, sappiamo per certo di avere punti doppi.
>In caso contrario, *potrebbero comunque presentarsi* e quindi è necessario **usare la caratterizzazione** analitica per determinarli.

Ricordando com'era definito $p_{K}(s)$, la condizione sopra è equivalente a:
$$
\begin{cases}
a(s) + Kb(s) = 0 \\ \\
\displaystyle \frac{da(s)}{ds} + K \frac{db(s)}{ds} = 0
\end{cases}
$$
Dalla prima ricaviamo:
$$
K = -\frac{a(s)}{b(s)}
$$
E sostituendo nella seconda troviamo:
$$
\begin{align*}
\frac{da(s)}{ds} - \frac{a(s)}{b(s)} \frac{db(s)}{ds} = 0 \\ \\
\implies b(s) \frac{da(s)}{ds} - a(s) \frac{db(s)}{ds} = 0
\end{align*}
$$
## ESEMPI TRACCIAMENTO DEL LUOGO
### ESEMPIO 1
Consideriamo per esempio:
$$
L_{a}(s) = K \frac{s+2}{s(s+1)(s+3)}
$$
Troviamo allora l'equazione:
$$
\begin{align*}
1 + K \frac{s+2}{s(s+1)(s+3)} = 0 \\ \\
\underbrace{ s(s+1)(s+3) }_{ a(s) } + K\underbrace{ (s+2) }_{ b(s) } = 0
\end{align*}
$$
Consideriamo il *luogo positivo*, quindi per $K\geq 0$.
Notiamo subito che:
1. Per $K=0$ i poli di $G(s)$ sono $0,-1,-3$.
2. Il luogo avrà **tre rami**.
3. Per $K\to +\infty$ **un ramo tende verso lo zero** in $-2$, gli altri **due tendono a infinito**.

Cominciamo determinando il **centro della stella** degli asintoti:
$$
\begin{align*}
\alpha &= \frac{1}{n-m}\left( \sum_{i=1}^np_{i} - \sum_{i=1}^m z_{i} \right) \\ \\
&= -\frac{4+2}{2} = -1
\end{align*}
$$
Determiniamo ora le **inclinazioni degli asintoti**:
$$
\begin{align*}
\phi_{h} &= \frac{(2h+1)\pi}{n-m} & & h=0,1,\dots,n-m-1 \\ \\
&= \frac{(2h+1)\pi}{2} \\ \\
h &=0,1 \implies \begin{cases}
\displaystyle \phi_{0} = \frac{\pi}{2} \\ \\
\displaystyle \phi_{1} = \frac{3\pi}{2}
\end{cases}
\end{align*}
$$
La **porzione dell'asse reale** che appartiene al luogo è:
$$
[-3,-2] \cup [-1,0]
$$
E abbiamo un **punto doppio** tra $0$ e $1$.
Il luogo delle radici risulta quindi essere:

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta, decorations.markings}

\begin{document}
\begin{tikzpicture}[
    x=1.6cm, y=0.9cm, % Scala per le proporzioni
    font=\sffamily,
    % Stile per inserire le frecce a metà dei percorsi
    midarrow/.style={postaction={decorate,decoration={
        markings,
        mark=at position #1 with {\arrow{Stealth[length=3.5mm, width=3mm]}}
    }}}
]

% Definizione dei colori per i tre rami
\colorlet{branchA}{green!60!black}
\colorlet{branchB}{red}
\colorlet{branchC}{blue}

% 1. Griglia di background
\draw[thin, black!50, step=1] (-4,-5) grid (2,5);

% 2. Asintoto Giallo (Centroide in s = -1)
\draw[gray!60!black, dashed, line width=1.5pt] (-1,-5) -- (-1,5);

% 3. Assi Principali (Reale e Immaginario)
\draw[thick, black] (-4,0) -- (2,0);
\draw[thick, black] (0,-5) -- (0,5);

% ==========================================
% TRACCIAMENTO DEI RAMI
% ==========================================

% Ramo A (Blu): Dal polo in -3 allo zero in -2
\draw[branchA, line width=2pt, midarrow=0.6] (-3,0) -- (-2,0);

% Ramo B (Rosso): Dal polo nell'origine verso il basso (breakaway) e poi asintoto superiore
\draw[branchB, line width=2pt, midarrow=0.6] (0,0) -- (-0.53,0);
\draw[branchB, line width=2pt, midarrow=0.8] (-0.53, 0) .. controls (-0.53, 1.5) and (-0.8, 3) .. (-0.95, 5);

% Ramo C (Verde scuro): Dal polo in -1 verso l'alto (breakaway) e poi asintoto inferiore
\draw[branchC, line width=2pt, midarrow=0.6] (-1,0) -- (-0.53,0);
\draw[branchC, line width=2pt, midarrow=0.8] (-0.53, 0) .. controls (-0.53, -1.5) and (-0.8, -3) .. (-0.95, -5);


% ==========================================
% MARCATORI (Poli e Zeri colorati per ramo)
% ==========================================

% Macro aggiornate per accettare il colore come secondo argomento
\newcommand{\drawpole}[2]{
    \begin{scope}[shift={(#1)}]
        \draw[#2, line width=1.5pt] (-3.5pt,-3.5pt) -- (3.5pt,3.5pt);
        \draw[#2, line width=1.5pt] (-3.5pt,3.5pt) -- (3.5pt,-3.5pt);
    \end{scope}
}
\newcommand{\drawzero}[2]{
    \draw[#2, line width=1.5pt, fill=white] (#1) circle (3.5pt);
}

% Inserimento Marcatori
\drawpole{-3,0}{branchA}  % Polo in -3 appartiene al Ramo A
\drawzero{-2,0}{branchA}  % Zero in -2 appartiene al Ramo A

\drawpole{0,0}{branchB}   % Polo in 0 appartiene al Ramo B
\drawpole{-1,0}{branchC}  % Polo in -1 appartiene al Ramo C


% ==========================================
% CORNICE ED ETICHETTE
% ==========================================

% Bounding Box (Bordo esterno stile MATLAB)
\draw[line width=1.2pt, black] (-4,-5) rectangle (2,5);

% Tacche e etichette sull'asse X
\foreach \x in {-4,-3,-2,-1,0,1,2} {
    \draw[line width=1pt] (\x, -5) -- ++(0, 4pt);
    \draw[line width=1pt] (\x, 5) -- ++(0, -4pt);
    \node[below] at (\x, -5.1) {\x};
}
% Tacche e etichette sull'asse Y
\foreach \y in {-5,-4,-3,-2,-1,0,1,2,3,4,5} {
    \draw[line width=1pt] (-4, \y) -- ++(4pt, 0);
    \draw[line width=1pt] (2, \y) -- ++(-4pt, 0);
    \node[left] at (-4.1, \y) {\y};
}

% Etichette degli Assi
\node[below=0.6cm] at (-1, -5) {Real Axis};
\node[left=0.8cm, rotate=90] at (-4, 0) {Imag Axis};

\end{tikzpicture}
\end{document}
```

Sappiamo *qualitativamente* dove si trova il *punto doppio*. 
Determiniamolo in *maniera analitica* sfruttando la *caratterizzazione dei punti doppi*:
$$
\begin{align*}
\begin{cases}
p_{K}(s) = 0 \\ \\
\frac{d}{ds}p_{K}(s) = 0
\end{cases} & & \implies & & \begin{cases}
s^3 + 4s^2 + 3s + K(s+2) = 0 \\ \\
3s^2 + 8s + 3 + K = 0
\end{cases}
\end{align*}
$$
Dalla seconda equazione ricaviamo:
$$
K = -3s^2 -8s -3
$$
Sostituendo nella prima troviamo:
$$
2s^3 + 10s^2 + 16s + 6 = 0
$$
Facciamo risolvere l'equazione a matlab e troviamo i seguenti candidati:

```matlab
roots([2 10 16 6])
ans = 
	-2.2328 + 0.7926i
	-2-2328 - 0.7926i
	-0.5344
```

Chiaramente, la soluzione da noi cercata è:
$$
s = -0.5344
$$
A cui è associato il valore di $K$:
$$
K = 0.4186
$$
### ESEMPIO 2
Consideriamo ora:
$$
L_{a}(s) = K \frac{s+5}{(s+2)(s+3)}
$$
Ancora una volta, ci limitiamo al *luogo positivo*.
Otteniamo:
$$
(s+2)(s+3) + K(s+5) = 0
$$
Notiamo subito che:
1. Per $K=0$, troviamo i poli di $L_{a}(s)$: $-2,-3$.
2. Il luogo è costituito da **due rami**.
3. Per $K\to +\infty$ **un ramo tende verso lo zero** in $-5$, mentre **un ramo tende verso infinito** lungo un asintoto.

>[!note] NOTA
>Visto che c'è **un solo asintoto**, il **centro** della stella **non è significativo**.

Determiniamo l'inclinazione dell'unico asintoto:
$$
\phi_{0} = \frac{(2\cdot0+1)\pi}{n-m} = \pi
$$
La **porzione dell'asse reale** appartenente al luogo è:
$$
(-\infty,-5] \cup [-3,-2]
$$
Il luogo è pertanto il seguente:

```tikz
\usepackage{tikz}
\usetikzlibrary{arrows.meta, decorations.markings}

\begin{document}
\begin{tikzpicture}[
    x=0.9cm, y=0.9cm, % Scala ottimizzata per ingombro orizzontale
    font=\sffamily,
    midarrow/.style={postaction={decorate,decoration={
        markings,
        mark=at position #1 with {\arrow{Stealth[length=3.5mm, width=3mm]}}
    }}}
]

% Definizione colori per i rami (stile precedente)
\colorlet{branchA}{blue}
\colorlet{branchB}{red}

% 1. Griglia di background (limiti basati sull'immagine)
\draw[thin, black!50, step=1] (-10,-4.2) grid (2,4.2);

% 2. Assi Principali (Reale e Immaginario)
\draw[thick, black] (-10,0) -- (2,0);
\draw[thick, black] (0,-4.2) -- (0,4.2);

% ==========================================
% TRACCIAMENTO DEI RAMI (Basato sulla matematica esatta)
% ==========================================
% Centro del cerchio: -5
% Raggio: sqrt(6) = 2.4495
% Breakaway point: -2.5505
% Break-in point: -7.4495

% Ramo A (Blu): Polo -2 -> Distacco (-2.55) -> Semicerchio Superiore -> Zero -5
\draw[branchA, line width=2pt, midarrow=0.5] (-2,0) -- (-2.5505,0);
\draw[branchA, line width=2pt, midarrow=0.5] (-2.5505,0) arc (0:180:2.4495);
\draw[branchA, line width=2pt, midarrow=0.5] (-7.4495,0) -- (-5,0);

% Ramo B (Verde): Polo -3 -> Distacco (-2.55) -> Semicerchio Inferiore -> Asintoto -Inf
\draw[branchB, line width=2pt, midarrow=0.5] (-3,0) -- (-2.5505,0);
\draw[branchB, line width=2pt, midarrow=0.5] (-2.5505,0) arc (0:-180:2.4495);
\draw[branchB, line width=2pt, midarrow=0.85] (-7.4495,0) -- (-10,0);

% ==========================================
% MARCATORI (Poli e Zeri colorati per ramo)
% ==========================================
\newcommand{\drawpole}[2]{
    \begin{scope}[shift={(#1)}]
        \draw[#2, line width=1.5pt] (-3.5pt,-3.5pt) -- (3.5pt,3.5pt);
        \draw[#2, line width=1.5pt] (-3.5pt,3.5pt) -- (3.5pt,-3.5pt);
    \end{scope}
}
\newcommand{\drawzero}[2]{
    \draw[#2, line width=1.5pt, fill=white] (#1) circle (3.5pt);
}

% Inserimento Marcatori
\drawpole{-2,0}{branchA} % Ramo Blu
\drawpole{-3,0}{branchB} % Ramo Verde
\drawzero{-5,0}{branchA} % Ramo Blu finisce qui

% ==========================================
% CORNICE ED ETICHETTE (Stile MATLAB)
% ==========================================

% Bounding Box esterna
\draw[line width=1.2pt, black] (-10,-4.2) rectangle (2,4.2);

% Tacche e etichette sull'asse X (Step di 2, ma griglia di 1)
\foreach \x in {-10,-8,-6,-4,-2,0,2} {
    \draw[line width=1pt] (\x, -4.2) -- ++(0, 4pt);
    \draw[line width=1pt] (\x, 4.2) -- ++(0, -4pt);
    \node[below] at (\x, -4.4) {\x};
}

% Tacche e etichette sull'asse Y (Step di 1)
\foreach \y in {-4,-3,-2,-1,0,1,2,3,4} {
    \draw[line width=1pt] (-10, \y) -- ++(4pt, 0);
    \draw[line width=1pt] (2, \y) -- ++(-4pt, 0);
    \node[left] at (-10.1, \y) {\y};
}

% Etichette degli Assi
\node[below=0.6cm] at (-4, -4.5) {Real Axis}; % Posizionato al centro orizzontale del box
\node[left=0.8cm, rotate=90] at (-10, 0) {Imag Axis};

\end{tikzpicture}
\end{document}
```

>[!note] NOTA
>I rami si "muovono" **lungo una circonferenza** per *passare tra le due porzioni ammesse dell'asse reale*.
>Questo si verifica anche in altre situazioni, ma non ci soffermiamo sulla dimostrazione.

Calcoliamo anche in questo caso i *punti doppi* e i *corrispondenti valori* di $K$:
$$
\frac{da(s)}{ds}b(s) - a(s) \frac{db(s)}{ds} = s^2 + 10s +19 = 0
$$
Troviamo quindi:
$$
s_{1,2} = -5 \pm \sqrt{ 6 } = \begin{cases}
-2.55 \\
-7.45
\end{cases}
$$
Per determinare $K$:
$$
a(s) + Kb(s) = s^2 + 5s + 6 +K(s+5) = 0
$$
Ricaviamo:
$$
K = - \frac{s^2 +5s + 6}{s+5}
$$
Per cui:
$$
K_{1,2} = \begin{cases}
0.10 \\
9.89
\end{cases}
$$
## ESEMPIO 3
Consideriamo ora:
$$
L_{a}(s) = K \frac{1}{s(s+1)(s+2)}
$$
Per cui:
$$
p_{K}(s) = s(s+1)(s+2) + K = s^3 + 3s^2 + 2s + K
$$
Abbiamo:
1. **Tre rami**, che *divergeranno tutti quanti* visto che *non ci sono zeri*.
2. Per $K=0$ abbiamo i **poli** seguenti: $0,-1,-2$.
3. Per $K\to +\infty$ i tre rami vanno verso infinito lungo tre asintoti.

Il centro della stella si trova in:
$$
\begin{align*}
\alpha &= \frac{1}{n-m}\left(  \sum_{i=1}^n p_{i} - \sum_{i=1}^mz_{i} \right) \\ \\
&= \frac{0-1-2}{3} = -1
\end{align*}
$$
Gli angoli degli asintoti sono dati da:
$$
\phi_{h} = \frac{(2h+1)\pi}{3} = \begin{cases}
\pi/3 \\ \\
\pi \\ \\
5\pi/3
\end{cases}
$$
La **porzione dell'asse reale** è:
$$
(-\infty,-2] \cup [-1,0]
$$
E abbiamo un **punto doppio** in $[-1,0]$.

>[!note] NOTA
>Visti gli angoli degli asintoti, il luogo **attraversa l'asse immaginario**.

Calcoliamo allora i *punti di attraversamento dell'asse immaginario*, usando il criterio di Routh.

>[!note] NOTA: MOTIVO PER L'UTILIZZO DEL CRITERIO DI ROUTH
>Se un ramo attraversa l'asse immaginario, per *quel valore di K* il sistema ha una *coppia di poli puramente immaginari* e tale situazione *corrisponde ad una riga nulla nella tabella di Routh*.
>

Il nostro polinomio era:
$$
p_{K}(s) = s(s+1)(s+2) + K = s^3 + 3s^2 + 2s + K
$$
Per cui la tabella di Routh risulta essere:
$$
\begin{matrix}
3 & | & 1 & 2 \\
2 & | & 3 & K \\
1 & | & (6-K)/3 & 0 \\
0 & | & K & 0
\end{matrix}
$$
Notiamo che:
1. Per $0<K<6$ tutti gli elementi della **prima colonna** sono **positivi**, per cui il sistema è stabile.
2. Per $K=6$ la **riga 1 è nulla**.
3. Se $K>6$ abbiamo un **cambio di segno** e il sistema è instabile.

Siamo ora interessati al caso $K=6$. Ricordiamo che in tal caso facciamo ricorso al *polinomio ausiliario*, ricavato dalla *riga precedente* a quella con gli zeri.
Abbiamo quindi:
$$
\mathcal{A}(s) = 3s^2 + K = 3s^2 + 6
$$
Per cui, per $K=6$, $p_{K}(s)$ ha un fattore $3s^2+6=3(s^2+2)$.
I *poli sull'asse immaginario* associati si trovano quindi in:
$$
s^2 + 2 = 0 \implies s = \pm j\sqrt{ 2 }
$$

```tikz
\usepackage{amsmath}
\usetikzlibrary{arrows.meta, decorations.markings}

\begin{document}

\begin{tikzpicture}[
    x=1.1cm, y=1.1cm, % Scala ottimizzata
    font=\sffamily,
    midarrow/.style={postaction={decorate,decoration={
        markings,
        mark=at position #1 with {\arrow{Stealth[length=3.5mm, width=3mm]}}
    }}}
]

% Definizione colori per i rami
\colorlet{branchBlue}{blue}
\colorlet{branchRed}{red}
\colorlet{branchGreen}{green!60!black} % Verde leggermente scuro per contrasto

% 1. Griglia di background (limiti basati sull'immagine)
\draw[thin, black!50, step=1] (-5.5,-3.5) grid (3.2,3.5);

% 2. Assi Principali (Reale e Immaginario)
\draw[thick, black] (-5.5,0) -- (3.2,0);
\draw[thick, black] (0,-3.5) -- (0,3.5);

% ========================================
% TRACCIAMENTO DEGLI ASINTOTI (Linee sottili)
% ========================================
% Centro degli asintoti: (-1, 0). Angoli: 60° e -60°
% Calcolo punto finale per toccare il bordo Y a 3.5: x = -1 + 3.5/sqrt(3) = 1.0207
\draw[thin, dashed] (-1,0) -- (1.0207, 3.5);
\draw[thin, dashed] (-1,0) -- (1.0207, -3.5);

% ========================================
% TRACCIAMENTO DEI RAMI (Matematica esatta)
% ========================================
% Punto di break-away: -1 + 1/sqrt(3) ≈ -0.4226

% Ramo Blu: Polo -1 -> Distacco (-0.4226) -> Iperbole Superiore
\draw[branchBlue, line width=1.5pt] (-1,0) -- (-0.4226,0);
\draw[branchBlue, line width=1.5pt, midarrow=0.8] 
    plot[domain=-0.4226:1.1, samples=100] (\x, {sqrt(abs(3*\x*\x + 6*\x + 2))});

% Ramo Rosso: Polo 0 -> Distacco (-0.4226) -> Iperbole Inferiore
\draw[branchRed, line width=1.5pt] (0,0) -- (-0.4226,0);
\draw[branchRed, line width=1.5pt, midarrow=0.8] 
    plot[domain=-0.4226:1.1, samples=100] (\x, {-sqrt(abs(3*\x*\x + 6*\x + 2))});

% Ramo Verde: Polo -2 -> Asintoto a -Infinito
\draw[branchGreen, line width=1.5pt, midarrow=0.95] (-2,0) -- (-5.5,0);

% ========================================
% MARCATORI (Poli) E TESTI
% ========================================
\newcommand{\drawpole}[2]{
    \begin{scope}[shift={(#1)}]
        \draw[#2, line width=1.5pt] (-3.5pt,-3.5pt) -- (3.5pt,3.5pt);
        \draw[#2, line width=1.5pt] (-3.5pt,3.5pt) -- (3.5pt,-3.5pt);
    \end{scope}
}

% Tutti i poli sono mostrati in blu nell'immagine
\drawpole{0,0}{red}
\drawpole{-1,0}{blue}
\drawpole{-2,0}{green!60!black}

% ========================================
% CORNICE ED ETICHETTE (Stile MATLAB)
% ========================================
% Bounding Box esterna
\draw[line width=1.2pt, black] (-5.5,-3.5) rectangle (3.2,3.5);

% Tacche e etichette sull'asse X
\foreach \x in {-5,-4,-3,-2,-1,0,1,2,3} {
    \draw[line width=1pt] (\x, -3.5) -- ++(0, 4pt);
    \draw[line width=1pt] (\x, 3.5) -- ++(0, -4pt);
    \node[below] at (\x, -3.6) {\x};
}

% Tacche e etichette sull'asse Y
\foreach \y in {-3,-2,-1,0,1,2,3} {
    \draw[line width=1pt] (-5.5, \y) -- ++(4pt, 0);
    \draw[line width=1pt] (3.2, \y) -- ++(-4pt, 0);
    \node[left] at (-5.6, \y) {\y};
}

% Etichette degli Assi
\node[below=0.7cm] at (-1.15, -3.5) {Real Axis}; % Centrato sul box orizzontalmente
\node[left=0.8cm, rotate=90] at (-5.5, 0) {Imag Axis};

\end{tikzpicture}

\end{document}
```

>[!idea] OSSERVAZIONE IMPORTANTE
>Il sistema è *gia di tipo 1*, applicando un *controllo puramente proporzionale* (abbiamo solo K) rischiamo di rendere instabile il sistema (attraversamento asse immaginario), senza guadagnare in precisione (rispetto al gradino) visto che il sistema è già di tipo 1
## PUNTI MULTIPLI
Abbiamo visto sopra che il luogo può presentare dei *punti doppi*; in realtà, in generale, si possono avere **punti multipli**.

>[!def] PUNTI MULTIPLI
>Un punto $s^*$ si dice **punto multiplo** se in $s^*$ si incontrano $h$ **rami** del luogo.
>Possiamo *caratterizzare un punto multiplo* come segue:
>$$ \begin{cases} p_{K}(s^*) &= 0 \\ \\ \displaystyle\frac{d}{ds}p_{K}(s)\bigg|_{s=s^*} &= 0 \\ \\ \dots \\ \\ \displaystyle\frac{d^{(h-1)}}{ds^{(h-1)}}p_{K}(s)\bigg|_{s=s^*} &= 0 \end{cases} $$

Inoltre, gli $h$ *rami entranti* e gli $h$ *rami uscenti* del punto sono **alternati** e **divisi da 2h angoli** *tutti pari* a $\pi/h$:

```tikz
\usepackage{amsmath}
\usetikzlibrary{decorations.markings, arrows.meta}

\begin{document}
\begin{tikzpicture}[
    % Definizione dello stile globale per le frecce e le linee
    >=latex,
    thickline/.style={thick, draw=black},
    midarrow/.style={postaction={decorate,decoration={markings,mark=at position #1 with {\arrow{latex}}}}}
]

% ==========================================
% DIAGRAMMA SINISTRO (Asintoti a \pi/2)
% ==========================================
\begin{scope}
    % Asse orizzontale (Frecce uscenti alle estremità)
    \draw[<->, thickline] (-2.5,0) -- (2.5,0);
    
    % Asse verticale (Frecce entranti verso l'origine)
    % Usiamo midarrow per posizionare la freccia prima del centro
    \draw[thickline, midarrow=0.75] (0,2.5) -- (0,0);
    \draw[thickline, midarrow=0.75] (0,-2.5) -- (0,0);

    % Arco per l'angolo
    \draw[thickline] (1.2,0) arc (0:90:1.2);
    
    % Testo dell'angolo
    \node[text=blue, font=\huge] at (1.5,1.4) {$\boldsymbol{\pi/2}$};
\end{scope}

% ==========================================
% DIAGRAMMA DESTRO (Asintoti a \pi/3)
% ==========================================
\begin{scope}[xshift=8cm]
    % Raggio a 0 rad (Entrante, freccia verso l'origine)
    \draw[thickline, midarrow=0.65] (3.2,0) -- (0,0);
    
    % Raggio a \pi rad (Uscente, freccia alla punta)
    \draw[thickline, ->] (0,0) -- (-3.2,0);

    % Raggio a 4\pi/3 rad (Entrante, freccia verso l'origine)
    \draw[thickline, midarrow=0.65] (240:3.2) -- (0,0);
    
    % Raggio a \pi/3 rad (Uscente, freccia alla punta)
    \draw[thickline, ->] (0,0) -- (60:3.2);

    % Raggio a 2\pi/3 rad (Entrante, freccia verso l'origine)
    \draw[thickline, midarrow=0.65] (120:3.2) -- (0,0);
    
    % Raggio a 5\pi/3 rad (Uscente, freccia alla punta)
    \draw[thickline, ->] (0,0) -- (300:3.2);

    % Arco per l'angolo
    \draw[thickline] (1.2,0) arc (0:60:1.2);
    
    % Testo dell'angolo
    \node[text=blue, font=\huge] at (2.2,0.9) {$\boldsymbol{\pi/3}$};
\end{scope}

\end{tikzpicture}
\end{document}
```
In figura vediamo i casi $h=2$ e $h=3$.

>[!note] PUNTI MULTIPLI COMPLESSI
>Noi abbiamo assunto che i *punti multipli* fossero *reali*.
>Chiaramente, **potrebbero anche essere complessi**, ma ciò si verifica solitamente per sistemi particolarmente complicati, che noi non affrontiamo.
# ESEMPIO - CONTROLLO ORIENTAMENTO DI UN SATELLITE
Analizziamo ora un esempio che ci permetta di comprendere meglio lo *studio del luogo delle radici* e la sua *utilità*.

Immaginiamo di voler *controllare l'orientamento di un satellite* usando dei razzetti (thrusters) che imprimono una certa coppia, facendo ruotare il satellite stesso.
Abbiamo quindi:
$$
\tau(t) = J \ddot{\theta}(t)
$$
Per comodità "nascondiamo" l'inerzia $J$ nel guadagno $K$; troviamo così:
$$
u(t) = \ddot{\theta}(t)
$$
Applicando la TDL abbiamo:
$$
U(s) = s^2 \Theta(s) \implies G(s) = \frac{\Theta(s)}{U(s)} = \frac{1}{s^2}
$$
>[!note] NOTA
>Il sistema è un **doppio integratore naturale**.
>In precedenza abbiamo considerato $G(s)=\frac{1}{s(s+\alpha)}$: il coefficiente $\alpha$ rappresentava l'attrito (per esempio tra le componenti di un motore), che ora, nello spazio, è trascurabile.
>Di conseguenza il *polo frenante collassa in zero* e rende il sistema **più difficile da controllare** (perchè *in partenza* non ha *nessun polo a parte reale negativa*).
## CONTROLLO P
Immaginiamo di controllare il sistema con un *controllore proporzionale*.
Abbiamo allora:
$$
L_{a}(s) = k_{p} \frac{1}{s^2}
$$
E quindi:
$$
p_{K}(s) = s^2 + k_{p}
$$
Abbiamo un *polo doppio* in $s=0$ e *nessuno zero*.
Il luogo avrà quindi **due rami** e **due asintoti** verticali lungo l'asse immaginario.
Infatti:
$$
\phi_{h} = \frac{(2h+1)\pi}{2} = \begin{cases}
\pi/2 \\ \\
3\pi/2
\end{cases}
$$
Chiaramente, il luogo **non include** alcuna **porzione dell'asse reale**.
Inoltre, $s=0$ è anche un **punto doppio**.

Siccome $p_{K}(s)=0\implies s^2=-k_{p}$, abbiamo **solo radici complesse** e la risposta è quindi *oscillatoria*.

>[!idea] OSSERVAZIONE IMPORTANTE
>Di conseguenza, con un controllore proporzionale **non riusciremo mai** a rendere **BIBO stabile** il sistema.
>Continuerà a rimanere *marginalmente stabile*, cioè ad *oscillare attorno all'equilibrio*, tuttavia l'**ampiezza delle oscillazioni crescerebbe** all'**aumentare del guadagno**.
## CONTROLLO PD
Vogliamo allora **"spostare" i poli** verso il **semipiano sinistro**.
Per farlo, utilizziamo un *controllore PD*, che **aggiunge uno zero** (e quindi *un polo convergerà verso esso* all'aumentare di $K$).

Abbiamo allora:
$$
L_{a}(s) = (k_{p}+k_{d}s) \frac{1}{s^2} = K \frac{s+1}{s^2}
$$
>[!note] NOTA
>Abbiamo raccolto $K$ assumendo $k_{p}/k_{d}=1$ e $K=k_{d}$.

Per cui $p_{K}(s) = s^2 + K(s+1)$.

Abbiamo:
1. **2 rami** (uno converge verso lo zero, l'altro diverge).
2. Un **asintoto orizzontale** lungo il *semiasse reale negativo*.
3. Un **punto doppio** in $s=0$.
4. La **porzione dell'asse reale** è $(-\infty,-1]\cup \{ 0 \}$.
5. Un **punto doppio** in $(-\infty,-1]$.

Determiniamo le *posizioni dei punti doppi*:
$$
\begin{align*}
\begin{cases}
s^2 + K(s+1) &= 0 \\ \\
2s + K &= 0
\end{cases} & & \begin{cases}
s^2 + (-2s)(s+1) = -s^2 - 2s \\ \\
K = -2s
\end{cases}
\end{align*}
$$
Dalla prima equazione troviamo:
1. $s=0$, corrispondente a $K=0$.
2. $s=-2$, corrispondente a $K=4$.

```tikz
\usepackage{amsmath}
\usetikzlibrary{arrows.meta, decorations.markings}

\begin{document}

\begin{tikzpicture}[
    x=2.5cm, y=2.5cm, % Scala ottimizzata per l'ingombro (limiti più stretti)
    font=\sffamily,
    % Nuova macro per frecce custom che permette di cambiare colore al volo
    coloredarrow/.style 2 args={
        postaction={decorate,decoration={
            markings,
            mark=at position #1 with {\arrow[#2]{Stealth[length=3.5mm, width=3mm]}}
        }}
    }
]

% Definizione colori per i rami fedeli all'immagine
\colorlet{branchBlue}{blue}
\colorlet{branchRed}{red}

% 1. Griglia di background (step di 0.5 come nell'immagine)
\draw[thin, black!50, step=0.5] (-3,-1.5) grid (1,1.5);

% 2. Assi Principali (Reale e Immaginario)
\draw[thick, black] (-3,0) -- (1,0);
\draw[thick, black] (0,-1.5) -- (0,1.5);

% ========================================
% TRACCIAMENTO DEI RAMI (Basato sulla matematica esatta)
% ========================================
% Sistema: G(s) = K(s+1)/s^2
% Il luogo forma un cerchio perfetto di raggio 1 centrato in (-1,0)

% Ramo Blu: Dal doppio polo (0) -> Semicerchio Superiore -> Distacco (-2) -> Zero in -1
\draw[branchBlue, line width=1.5pt, coloredarrow={0.25}{branchBlue}] 
    (0,0) arc (0:180:1);
\draw[branchBlue, line width=1.5pt, coloredarrow={0.85}{branchBlue}] 
    (-2,0) -- (-1,0);

% Ramo Verde: Dal doppio polo (0) -> Semicerchio Inferiore -> Distacco (-2) -> Asintoto a -Inf
\draw[branchRed, line width=1.5pt, coloredarrow={0.25}{branchRed}] 
    (0,0) arc (0:-180:1);
\draw[branchRed, line width=1.5pt, coloredarrow={0.8}{branchRed}] 
    (-2,0) -- (-3,0);

% ========================================
% MARCATORI (Poli e Zeri colorati)
% ========================================
\newcommand{\drawpole}[2]{
    \begin{scope}[shift={(#1)}]
        \draw[#2, line width=1.5pt] (-3.5pt,-3.5pt) -- (3.5pt,3.5pt);
        \draw[#2, line width=1.5pt] (-3.5pt,3.5pt) -- (3.5pt,-3.5pt);
    \end{scope}
}

\newcommand{\drawzero}[2]{
    \draw[#2, line width=1.5pt, fill=white] (#1) circle (3.5pt);
}

% Inserimento Marcatori
\drawpole{0,0}{black}  % Doppio polo nell'origine
\drawzero{-1,0}{branchBlue} % Zero in -1

% ========================================
% CORNICE ED ETICHETTE (Stile MATLAB)
% ========================================
% Bounding Box esterna
\draw[line width=1.2pt, black] (-3,-1.5) rectangle (1,1.5);

% Tacche e etichette sull'asse X
\foreach \x in {-3, -2.5, -2, -1.5, -1, -0.5, 0, 0.5, 1} {
    \draw[line width=1pt] (\x, -1.5) -- ++(0, 4pt);
    \draw[line width=1pt] (\x, 1.5) -- ++(0, -4pt);
    \node[below=2pt] at (\x, -1.5) {\x};
}

% Tacche e etichette sull'asse Y
\foreach \y in {-1.5, -1, -0.5, 0, 0.5, 1, 1.5} {
    \draw[line width=1pt] (-3, \y) -- ++(4pt, 0);
    \draw[line width=1pt] (1, \y) -- ++(-4pt, 0);
    \node[left=2pt] at (-3, \y) {\y};
}

% Etichette degli Assi
\node[below=0.7cm] at (-1, -1.5) {Real Axis};
\node[left=1.1cm, rotate=90] at (-3, 0) {Imag Axis};

\end{tikzpicture}

\end{document}
```

>[!idea] OSSERVAZIONE IMPORTANTE
>Con un controllore di tipo PD possiamo **sempre stabilizzare questo sistema**; infatti ora, all'aumentare del guadagno, i poli sono "trascinati" *sempre verso il semipiano sinistro*.
## CONTROLLORE PD REALE
Abbiamo fin'ora schematizzato i controllori PD con la seguente FdT:
$$
C_{PD}(s) = k_{p} + k_{d}s
$$
In realtà, un controllore PD **reale** è in genere realizzato come:
$$
C(s) = k_{p} \frac{k_{d}s + 1}{s/p + 1} = K\frac{s+z}{s+p}
$$
>[!note] NOTA: RUOLO DEL POLO
>Il polo è aggiunto in modo da ottenere un effetto di **filtro passa-basso** per *tagliare i rumori ad alta frequenza*, a cui il blocco derivativo sarebbe *troppo sensibile*

Osservando $C(s)$, ci viene da pensare:

>[!idea] IPOTESI
>Scegliendo $p\gg 1$, il polo si trova **molto a sinistra dell'asse immaginario** e introduce quindi una **dinamica molto veloce**.
>Guardando il sistema in *catena aperta* siamo allora **portati a pensare** che questa dinamica sia **trascurabile**: vediamo **cosa succede in realtà** quando consideriamo il sistema in **catena chiusa**.

Per farlo, applichiamo il controllore PD reale (scegliendo $k_{d}=1$ per comodità) al sistema dell'esempio precedente.
Abbiamo quindi:
$$
C(s)G(s) = K \frac{s+1}{s^2} \frac{1}{s+p}
$$
Per cui:
$$
p_{K}(s) = s^2(s+p) + K(s+1)
$$
Determiniamo da subito la posizione degli *eventuali punti multipli*:
$$
\begin{align*}
\begin{cases}
s^2(s+p) + K(s+1) &= 0 \\ \\
3s^2 + 2ps + K &=0
\end{cases} & & \begin{cases}
s^2(s+p) - s(3s+2p)(s+1) = 0 \\ \\
K = -s(3s+2p)
\end{cases}
\end{align*}
$$
Le soluzioni sono:
$$
\begin{align*}
s_{0}=0 & & s_{1,2} = -\frac{p+3}{4} \pm \sqrt{ \frac{(p+3)^2}{16} - p }
\end{align*}
$$

Per cui abbiamo **punti doppi** in:
1. $s=0$, corrispondente a $K=0$ (ritroviamo il sistema visto sopra).
2. Se $\Delta=p^2-10p+9>0$, cioè $p<1,p>9$ abbiamo **due punti doppi reali**.

Consideriamo per esempio $p=11$.
Abbiamo:
1. **Tre rami**, di cui *uno converge verso lo zero*.
2. **Due asintoti**.
3. La **porzione dell'asse reale** è $[-11,-1]\cup \{ 0 \}$

I due asintoti hanno inclinazione $\pm \pi/2$ e il **centro della stella** si trova in:
$$
\alpha = \frac{1}{2}(-p+1) = -5
$$
I punti doppi (come determinato sopra) si trovano in:
$$
\begin{align*}
s_{0} = 0 & & s_{1,2} = \frac{-7\pm \sqrt{ 5 }}{2} = \begin{cases}
-2.38 \\
-4.62
\end{cases}
\end{align*}
$$
Ed il luogo risulta essere il seguente:

```tikz
\usepackage{amsmath}
\usetikzlibrary{arrows.meta, decorations.markings}

\begin{document}

\begin{tikzpicture}[
    x=0.55cm, y=0.55cm, % Scala ottimizzata per l'ingombro
    font=\sffamily,
    coloredarrow/.style 2 args={
        postaction={decorate,decoration={
            markings,
            mark=at position #1 with {\arrow[#2]{Stealth[length=3.5mm, width=3mm]}}
        }}
    }
]

% Definizione colori per i rami
\colorlet{branchBlue}{blue}
\colorlet{branchRed}{red}
\colorlet{branchGreen}{green!60!black}

% 1. Griglia di background (step di 2 come nell'immagine)
\draw[thin, black!50, step=2] (-14,-8) grid (6,8);

% 2. Assi Principali (Reale e Immaginario)
\draw[thick, black] (-14,0) -- (6,0);
\draw[thick, black] (0,-8) -- (0,8);

% ========================================
% TRACCIAMENTO DEI RAMI (Matematica esatta)
% ========================================
\begin{scope}
    % Ritaglio per evitare che i rami asintotici escano dalla griglia
    \clip (-14,-8) rectangle (6,8);
    
    % Asintoto verticale a s = -5
    \draw[thick, black!50, dashed] (-5, -8.5) -- (-5, 8.5);

    % RAMO BLU (Dal polo doppio 0 -> break-in -> zero in -1)
    % Curva complessa superiore
    \draw[branchBlue, line width=1.5pt, coloredarrow={0.5}{branchBlue}] 
        plot[domain=0:-2.382, samples=100] (\x, {sqrt(abs(-\x * (\x*\x + 7*\x + 11) / (\x + 5)))});
    % Segmento su asse reale (verso destra allo zero)
    \draw[branchBlue, line width=1.5pt, coloredarrow={0.6}{branchBlue}] 
        (-2.382, 0) -- (-1, 0);

    % RAMO VERDE (Dal polo doppio 0 -> break-in -> break-away -> asintoto -Inf)
    % Curva complessa inferiore
    \draw[branchGreen, line width=1.5pt, coloredarrow={0.5}{branchGreen}] 
        plot[domain=0:-2.382, samples=100] (\x, {-sqrt(abs(-\x * (\x*\x + 7*\x + 11) / (\x + 5)))});
    % Segmento su asse reale (verso sinistra al break-away)
    \draw[branchGreen, line width=1.5pt, coloredarrow={0.55}{branchGreen}] 
        (-2.382, 0) -- (-4.618, 0);
    % Ramo asintotico inferiore (tende a x = -5)
    \draw[branchGreen, line width=1.5pt, coloredarrow={0.8}{branchGreen}] 
        plot[domain=-4.618:-4.96, samples=100] (\x, {-sqrt(abs(-\x * (\x*\x + 7*\x + 11) / (\x + 5)))});

    % RAMO ROSSO (Dal polo -11 -> break-away -> asintoto +Inf)
    % Segmento su asse reale (verso destra al break-away)
    \draw[branchRed, line width=1.5pt, coloredarrow={0.5}{branchRed}] 
        (-11, 0) -- (-4.618, 0);
    % Ramo asintotico superiore (tende a x = -5)
    \draw[branchRed, line width=1.5pt, coloredarrow={0.8}{branchRed}] 
        plot[domain=-4.618:-4.96, samples=100] (\x, {sqrt(abs(-\x * (\x*\x + 7*\x + 11) / (\x + 5)))});
\end{scope}

% ========================================
% MARCATORI (Poli e Zeri)
% ========================================
\newcommand{\drawpole}[2]{
    \begin{scope}[shift={(#1)}]
        \draw[#2, line width=1.5pt] (-3.5pt,-3.5pt) -- (3.5pt,3.5pt);
        \draw[#2, line width=1.5pt] (-3.5pt,3.5pt) -- (3.5pt,-3.5pt);
    \end{scope}
}

\newcommand{\drawzero}[2]{
    \draw[#2, line width=1.5pt, fill=white] (#1) circle (3.5pt);
}

% Inserimento Marcatori (tutti blu come nell'immagine originale)
\drawpole{0,0}{branchBlue}   % Polo doppio nell'origine
\drawpole{-11,0}{branchRed} % Polo a -11
\drawzero{-1,0}{branchBlue}  % Zero a -1

% ========================================
% CORNICE ED ETICHETTE (Stile MATLAB)
% ========================================
% Bounding Box esterna
\draw[line width=1.2pt, black] (-14,-8) rectangle (6,8);

% Tacche e etichette sull'asse X
\foreach \x in {-14, -12, -10, -8, -6, -4, -2, 0, 2, 4, 6} {
    \draw[line width=1pt] (\x, -8) -- ++(0, 4pt);
    \draw[line width=1pt] (\x, 8) -- ++(0, -4pt);
    \node[below=2pt] at (\x, -8) {\x};
}

% Tacche e etichette sull'asse Y
\foreach \y in {-8, -6, -4, -2, 0, 2, 4, 6, 8} {
    \draw[line width=1pt] (-14, \y) -- ++(4pt, 0);
    \draw[line width=1pt] (6, \y) -- ++(-4pt, 0);
    \ifnum\y=0 \else \node[left=2pt] at (-14, \y) {\y}; \fi
}

% Etichette degli Assi
\node[below=0.75cm] at (-4, -8) {Real Axis}; % Centrato orizzontalmente rispetto al box [-14, 6]
\node[left=0.9cm, rotate=90] at (-14, 0) {Imag Axis};

\end{tikzpicture}

\end{document}
```

>[!note] NOTA
>Rispetto al luogo precedente, ora, per valori di $K$ *oltre una certa soglia*, i **poli diventano complessi** con **parte immaginaria sempre maggiore**.
>Per cui *aumentando eccessivamente il guadagno* rischiamo di *ottenere una risposta oscillatoria* di *grande ampiezza*.

>[!idea] RIMEDIO E COMPROMESSI
>Potremmo pensare allora di progettare il controllore in modo che il **polo** sia **più lontano possibile dall'asse immaginario**, e questo andrebbe effettivamente ad *aumentare la soglia* limite di *guadagno* entro la quale la risposta *non oscilla eccessivamente*.
>
>Ricordiamo però che il polo era stato introdotto per **correggere il difetto del PD ideale**, che *amplificava i disturbi ad alta frequenza*. Aumentando troppo il valore di $p$, *aumenteremmo* anche la **frequenza di taglio**, *vanificando* l'effetto del filtro **passa-basso**.
>
>Per questo, si sceglie generalmente un *valore di compromesso* per il polo in modo che $|p|\approx 10|z|$.
# CONTROLLO CON FLESSIBILITA'
Fin'ora abbiamo trattato i problemi di controllo assumendo vera l'**ipotesi** che i *sistemi meccanici* fossero **corpi rigidi e indeformabili**.

Chiaramente, si tratta di una semplificazione: nella realtà ogni sistema presenta una **certa elasticità strutturale**, che può influire sul sistema di controllo.

Distinguiamo allora **due casi**:
1. Caso **co - locato**: *non c'è flessibilità* tra *sensore e attuatore* (cioè il sensore e l'attuatore sono **sullo stesso corpo rigido**). Allora *non vi è ritardo* dovuto a *mezzi elastici* che *separano le componenti*.
2. Caso **non co - locato**: *c'è flessibilità* tra *sensore e attuatore* (cioè il sensore e l'attuatore sono su **due corpi rigidi diversi**). In tal caso il segnale del controllore *deve attraversare altri mezzi (potenzialmente elastici)* per *raggiungere il sistema*, il che *introduce dei ritardi*.

Consideriamo ancora una volta il sistema $G(s)=\frac{1}{s^2}$ e vediamo di seguito entrambi i casi.
## CASO CO - LOCATO
Si ottengono, in questi casi, funzioni $L_{a}(s)$ del tipo seguente:
$$
C(s)G(s) = K \frac{s+1}{s+12} \frac{(s+0.1)^2+6^2}{(s+0.1)^2 + 6.6^2} \frac{1}{s^2}
$$
Abbiamo:
1. **Cinque rami**.
2. **Due asintoti verticali** con **centro** $-11/2$.
3. **Porzione asse reale**: tra $-12$ e $-1$.
4. Un **punto doppio** tra $-12$ e $-1$.
5. I **rami uscenti dai poli complessi** sono **"attirati"** verso gli **zeri complessi** ad essi vicini.

```tikz
\usepackage{tikz}
\usepackage{amsmath}
\usetikzlibrary{arrows.meta, decorations.markings}

\begin{document}
\begin{tikzpicture}[
    x=0.65cm, y=0.65cm, % Scala ottimizzata per l'ingombro
    font=\sffamily,
    coloredarrow/.style 2 args={
        postaction={decorate,decoration={
            markings,
            mark=at position #1 with {\arrow[#2]{Stealth[length=3.5mm, width=3mm]}}
        }}
    }
]

% Definizione colori per i rami (stile MATLAB)
\colorlet{branchBlue}{blue}
\colorlet{branchRed}{red}
\colorlet{branchGreen}{green!60!black}
\colorlet{branchCyan}{cyan!90!black}
\colorlet{branchMagenta}{magenta}

% 1. Griglia di background (step di 2 come nell'immagine)
\draw[thin, black!50, step=2] (-14,-8) grid (4,8);

% 1. Assi di background 
\draw[thick, black!70] (-14, 0) -- (4, 0);
\draw[thick, black!70] (0, -8) -- (0, 8);

% Asintoto verticale a s = -5.5
% Calcolo: (Somma Poli - Somma Zeri)/(n-m) = (-12.2 - (-1.2)) / 2 = -11 / 2 = -5.5
\draw[black!80, dashed, line width=0.8pt] (-5.5, -8) -- (-5.5, 8);

% ======================================
% TRACCIAMENTO DEI RAMI
% (Approssimazione fedele tramite curve di Bézier)
% ======================================
\begin{scope}
    % Ritaglio per evitare sbavature esterne alla griglia
    \clip (-14,-8) rectangle (5,8);

    % RAMO ROSSO (Dal polo -12 -> break-away -> asintoto +Inf)
    \draw[branchRed, line width=1.5pt, coloredarrow={0.45}{branchRed}] 
        (-12, 0) -- (-4.7, 0);
    \draw[branchRed, line width=1.5pt, coloredarrow={0.6}{branchRed}] 
        (-4.7, 0) .. controls (-4.7, 2) and (-5.4, 4) .. (-5.45, 8);

    % RAMO VERDE (Dal polo 0 -> break-in -> break-away -> asintoto -Inf)
    \draw[branchGreen, line width=1.5pt, coloredarrow={0.5}{branchGreen}] 
        (0,0) .. controls (0, -1.6) and (-2.8, -1.6) .. (-2.8, 0);
    \draw[branchGreen, line width=1.5pt, coloredarrow={0.55}{branchGreen}] 
        (-2.8, 0) -- (-4.7, 0);
    \draw[branchGreen, line width=1.5pt, coloredarrow={0.6}{branchGreen}] 
        (-4.7, 0) .. controls (-4.7, -2) and (-5.4, -4) .. (-5.45, -8);

    % RAMO BLU (Dal polo 0 -> break-in -> zero in -1)
    \draw[branchBlue, line width=1.5pt, coloredarrow={0.5}{branchBlue}] 
        (0,0) .. controls (0, 1.6) and (-2.8, 1.6) .. (-2.8, 0);
    \draw[branchBlue, line width=1.5pt, coloredarrow={0.6}{branchBlue}] 
        (-2.8, 0) -- (-1, 0);

    % RAMO CIANO (Polo complesso sup -> Zero complesso sup)
    \draw[branchCyan, line width=1.5pt, coloredarrow={0.5}{branchCyan}] 
        (-0.1, 6.6) .. controls (-1.5, 6.6) and (-1.5, 6.0) .. (-0.1, 6.0);

    % RAMO MAGENTA (Polo complesso inf -> Zero complesso inf)
    \draw[branchMagenta, line width=1.5pt, coloredarrow={0.5}{branchMagenta}] 
        (-0.1, -6.6) .. controls (-1.5, -6.6) and (-1.5, -6.0) .. (-0.1, -6.0);
\end{scope}

% ======================================
% MARCATORI (Poli e Zeri)
% ======================================
\newcommand{\drawpole}[2]{
    \begin{scope}[shift={(#1)}]
        \draw[#2, line width=1.5pt] (-3.5pt,-3.5pt) -- (3.5pt,3.5pt);
        \draw[#2, line width=1.5pt] (-3.5pt,3.5pt) -- (3.5pt,-3.5pt);
    \end{scope}
}
\newcommand{\drawzero}[2]{
    \draw[#2, line width=1.5pt, fill=white] (#1) circle (3.5pt);
}

% Inserimento Marcatori (tutti blu come nell'immagine originale)
\drawpole{0,0}{branchBlue}         % Polo doppio nell'origine
\drawpole{-12,0}{branchRed}       % Polo a -12
\drawpole{-0.1, 6.6}{branchCyan}   % Polo complesso superiore
\drawpole{-0.1, -6.6}{branchMagenta}  % Polo complesso inferiore

\drawzero{-1,0}{blue}        % Zero a -1
\drawzero{-0.1, 6.0}{branchCyan}   % Zero complesso superiore
\drawzero{-0.1, -6.0}{branchMagenta}  % Zero complesso inferiore

% ======================================
% CORNICE ED ETICHETTE (Stile MATLAB)
% ======================================
% Bounding Box esterna
\draw[line width=1.2pt, black] (-14,-8) rectangle (4,8);

% Tacche e etichette sull'asse X (step di 5)
\foreach \x in {-14, -10, -5, 0, 4} {
    \draw[line width=1pt] (\x, -8) -- ++(0, 4pt);
    \draw[line width=1pt] (\x, 8) -- ++(0, -4pt);
    \node[below=2pt] at (\x, -8) {\x};
}

% Tacche e etichette sull'asse Y (step di 2)
\foreach \y in {-8, -6, -4, -2, 0, 2, 4, 6, 8} {
    \draw[line width=1pt] (-14, \y) -- ++(4pt, 0);
    \draw[line width=1pt] (4, \y) -- ++(-4pt, 0);
    \ifnum\y=0 \else \node[left=2pt] at (-14, \y) {\y}; \fi
}

% Etichette degli Assi
\node[below=0.75cm] at (-5, -8) {Real Axis}; 
\node[left=0.9cm, rotate=90] at (-14, 0) {Imag Axis};

\end{tikzpicture}
\end{document}
```

>[!idea] OSSERVAZIONE IMPORTANTE
>I rami **uscenti dai poli complessi** aggiuntivi sono **"attirati" verso gli zeri** complessi ad essi **vicini**, per cui il sistema è *intrinsecamente robusto*.
## CASO NON CO - LOCATO
Vediamo ora il caso **non co-locato**.
Si ottengono, in questi casi, funzioni $L_{a}(s)$ del tipo seguente:
$$
C(s)G(s) = K \frac{s+1}{s+12} \frac{1}{(s+0.1)^2 + 6.6^2} \frac{1}{s^2}
$$
>[!note] NOTA
>Ora **mancano gli zeri**, che non possono quindi "catturare" i rami uscenti dai nuovi poli introdotti.

Il luogo delle radici risulta essere, in tal caso:

```tikz
\usepackage{tikz}
\usepackage{amsmath}
\usetikzlibrary{arrows.meta, decorations.markings}

\begin{document}
\begin{tikzpicture}[
    x=0.65cm, y=0.65cm, % Scala ottimizzata per l'ingombro
    font=\sffamily,
    coloredarrow/.style 2 args={
        postaction={decorate,decoration={
            markings,
            mark=at position #1 with {\arrow[#2]{Stealth[length=3.5mm, width=3mm]}}
        }}
    }
]

% Definizione colori per i rami (stile MATLAB)
\colorlet{branchBlue}{blue}
\colorlet{branchRed}{red}
\colorlet{branchGreen}{green!60!black}
\colorlet{branchCyan}{cyan!90!black}
\colorlet{branchMagenta}{magenta}

\draw[thin, black!50, step=2] (-15,-8) grid (5,8);

% 1. Assi di background (linee punteggiate)
\draw[thick, dotted, black!70] (-14.5, 0) -- (4.5, 0);
\draw[thick, dotted, black!70] (0, -7.5) -- (0, 7.5);

% 2. Asintoti
% Calcolo Centro: (Somma Poli - Somma Zeri)/(n-m) = (-12 - 0.1 - 0.1 - (-1)) / 4 = -11.2 / 4 = -2.8
% Angoli: +/- 45°, +/- 135° (Pendenza +/- 1)
\draw[blue, dashed, line width=0.8pt] (-10.8, -8) -- (5.2, 8);
\draw[blue, dashed, line width=0.8pt] (-10.8, 8) -- (5.2, -8);

% ======================================
% TRACCIAMENTO DEI RAMI
% (Approssimazione fedele tramite curve di Bézier)
% ======================================
\begin{scope}
    % Ritaglio per evitare sbavature esterne alla griglia
    \clip (-15,-8) rectangle (5,8);

    % RAMO ROSSO (Dal polo -12 -> break-away -> asintoto +135°)
    \draw[branchRed, line width=1.5pt, coloredarrow={0.45}{branchRed}] 
        (-12, 0) -- (-7.8, 0);
    \draw[branchRed, line width=1.5pt, coloredarrow={0.6}{branchRed}] 
        (-7.8, 0) .. controls (-7.8, 3) and (-9, 6) .. (-10.8, 8);

    % RAMO VERDE (Dal polo 0 -> break-in -> break-away -> asintoto -135°)
    \draw[branchGreen, line width=1.5pt, coloredarrow={0.5}{branchGreen}] 
        (0,0) .. controls (0, -1.4) and (-2.8, -1.4) .. (-2.8, 0);
    \draw[branchGreen, line width=1.5pt, coloredarrow={0.55}{branchGreen}] 
        (-2.8, 0) -- (-7.8, 0);
    \draw[branchGreen, line width=1.5pt, coloredarrow={0.6}{branchGreen}] 
        (-7.8, 0) .. controls (-7.8, -3) and (-9, -6) .. (-10.8, -8);

    % RAMO BLU (Dal polo 0 -> break-in -> zero in -1)
    \draw[branchBlue, line width=1.5pt, coloredarrow={0.5}{branchBlue}] 
        (0,0) .. controls (0, 1.4) and (-2.8, 1.4) .. (-2.8, 0);
    \draw[branchBlue, line width=1.5pt, coloredarrow={0.6}{branchBlue}] 
        (-2.8, 0) -- (-1, 0);

    % RAMO CIANO (Polo complesso sup -> asintoto +45°)
    \draw[branchCyan, line width=1.5pt, coloredarrow={0.55}{branchCyan}] 
        (-0.1, 6.6) .. controls (1.5, 6.1) and (3.5, 6.8) .. (4.5, 8);

    % RAMO MAGENTA (Polo complesso inf -> asintoto -45°)
    \draw[branchMagenta, line width=1.5pt, coloredarrow={0.55}{branchMagenta}] 
        (-0.1, -6.6) .. controls (1.5, -6.1) and (3.5, -6.8) .. (4.5, -8);
\end{scope}

% ======================================
% MARCATORI (Poli e Zeri)
% ======================================
\newcommand{\drawpole}[2]{
    \begin{scope}[shift={(#1)}]
        \draw[#2, line width=1.5pt] (-3.5pt,-3.5pt) -- (3.5pt,3.5pt);
        \draw[#2, line width=1.5pt] (-3.5pt,3.5pt) -- (3.5pt,-3.5pt);
    \end{scope}
}
\newcommand{\drawzero}[2]{
    \draw[#2, line width=1.5pt, fill=white] (#1) circle (3.5pt);
}

% Inserimento Marcatori (tutti blu)
\drawpole{0,0}{branchBlue}         % Polo doppio nell'origine
\drawpole{-12,0}{branchRed}       % Polo a -12
\drawpole{-0.1, 6.6}{branchCyan}   % Polo complesso superiore
\drawpole{-0.1, -6.6}{branchMagenta}  % Polo complesso inferiore

\drawzero{-1,0}{blue}        % Zero a -1

% ======================================
% CORNICE ED ETICHETTE (Stile MATLAB)
% ======================================
% Bounding Box esterna
\draw[line width=1.2pt, black] (-15,-8) rectangle (5,8);

% Tacche e etichette sull'asse X (step di 5)
\foreach \x in {-15, -10, -5, 0, 5} {
    \draw[line width=1pt] (\x, -8) -- ++(0, 4pt);
    \draw[line width=1pt] (\x, 8) -- ++(0, -4pt);
    \node[below=2pt] at (\x, -8) {\x};
}

% Tacche e etichette sull'asse Y (step di 2)
\foreach \y in {-8, -6, -4, -2, 0, 2, 4, 6, 8} {
    \draw[line width=1pt] (-15, \y) -- ++(4pt, 0);
    \draw[line width=1pt] (5, \y) -- ++(-4pt, 0);
    \ifnum\y=0 \else \node[left=2pt] at (-15, \y) {\y}; \fi
}

% Etichette degli Assi
\node[below=0.75cm] at (-5, -8) {Real Axis}; 
\node[left=0.9cm, rotate=90] at (-15, 0) {Imag Axis};

\end{tikzpicture}
\end{document}
```

>[!idea] OSSERVAZIONE IMPORTANTE
>Ora, a causa dell'*assenza degli zeri*, all'*aumentare del guadagno* il sistema *diventa instabile*.
>I sistemi **non co-locati** sono generalmente **più difficili da controllare**.
