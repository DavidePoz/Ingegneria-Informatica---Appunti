# ==IL CORPO RIGIDO==

>[!info] CORPO RIGIDO
>E' un oggetto la cui *forma non cambia* quando soggetto a forze.
>Ha una certa massa e, a differenza del punto materiale, anche una certa estensione nello spazio.
## CARATTERISTICHE
Essendo *continuo*, a differenza di una collezione discreta di punti materiali, possiamo immaginarlo come un insieme di volumetti infinitesimali $dV$.

Se chiamiamo $\vec{r}$ la posizione di un volumetto e $dm(\vec{r})$ la sua massa, possiamo definire la densità $\rho(\vec{r})$:
$$
\rho(\vec{r}) = \frac{dm(\vec{r})}{dV} \tag{1}
$$
A questo punto, per trovare la massa totale $M$ del corpo si deve calcolare:
$$
M = \int_{\mathbf{V}} dm(\vec{r}) = \int_{\mathbf{V}}\rho(\vec{r})dV \tag{2}
$$
Spesso può essere utile approssimare un corpo rigido ad un oggetto bidimensionale (come un foglio) o unidimensionale (un filo). Tale approssimazione è giustificata dal fatto che una o due dimensioni del corpo sono ordini di grandezza più piccole rispetto all'/alle altra/e.
Conviene allora definire le seguenti quantità, analoghe della densità per un corpo tridimensionale.
**Densità superficiale:**
$$
\rho_{s}(\vec{r}) = \frac{dm(\vec{r})}{dS} \tag{3}
$$
**Densità lineare:**
$$
\rho_{l}(\vec{r}) = \frac{dm(\vec{r})}{dL} \tag{4}
$$
### CENTRO DI MASSA
Come per un sistema di $N$ punti materiali, anche per lo studio del corpo rigido è utile definire il *centro di massa*.
La definizione è del tutto analoga, trattandosi di una generalizzazione al caso di un corpo continuo:
$$
\vec{r}_{CM} = \int_{\mathbf{V}} \frac{\vec{r}}{M}dm(\vec{r}) \tag{5}
$$
E che riscriviamo per comodità nel seguente modo:
$$
\vec{r}_{CM} = \frac{1}{M}\int_{\mathbf{V}} \vec{r}\rho(\vec{r})dV \tag{6}
$$
#### OSSERVAZIONE
Se il corpo preso in considerazione è *omogeneo*, cioè la densità è costante al suo interno, si ha:
$$
\vec{r}_{CM} = \frac{1}{M} \int_{\mathbf{V}} \vec{r}\rho dV = \frac{1}{\cancel{ \rho } V}\int_{\mathbf{V}} \vec{r}\cancel{ \rho } dV = \frac{1}{V} \int_{\mathbf{V}} \vec{r}dV \tag{7}
$$
Cioè, per un corpo omogeneo, il centro di massa è una caratteristica puramente geometrica.
## AZIONE DELLA FORZA PESO
Ciascun volumetto $dV$ contribuisce alla forza peso con una forza infinitesima data da:
$$
d\vec{F}_{p} = -gdm(\vec{r})\vec{u}_{z} \tag{8}
$$
La forza peso totale è la risultante di questi contributi:
$$
\vec{F}_{p} = \int_{\mathbf{V}}d\vec{F}_{p} = -g\int_{\mathbf{V}}dm \vec{u}_{z} = -Mg \vec{u}_{z} \tag{9}
$$
A questo punto, dato che il corpo rigido ha una certa estensione, è importante calcolare il *momento* di $\vec{F}_{p}$: se questo fosse non nullo, il corpo ruoterebbe.
Ciascun volumetto $dV$ contribuisce al momento totale con un momento infinitesimo dato da:
$$
d\vec{\mathcal{M}} = \vec{r} \times d\vec{F}_{p} = -\vec{r} \times gdm \vec{u}_{z} \tag{10}
$$
Allora il momento totale è dato da:
$$
\vec{\mathcal{M}} = \int_{\mathbf{V}} d \vec{\mathcal{M}} = \int_{\mathbf{V}} (-gdm \vec{r}\times \vec{u}_{z}) \tag{11}
$$
$$
= -g\left( \int_{\mathbf{V}}\vec{r}dm \right)\times \vec{u}_{z} = -gM\left( \int_{\mathbf{V}} \frac{\vec{r}}{M}dm \right)\times \vec{u}_{z} = -gM\vec{r}_{CM}\times \vec{u}_{z}
$$
E concludiamo allora:
$$
\vec{\mathcal{M}} = \vec{r}_{CM} \times (-gM\vec{u}_{z}) = \vec{r}_{CM} \times \vec{F}_{p} \tag{12}
$$
Cioè possiamo immaginare che la *forza peso totale* sia applicata interamente nel CM.
### CONDIZIONI DI EQUILIBRIO
Per il corpo rigido, allora, si ha equilibrio se sono verificate due condizioni:
$$
\begin{cases}
\vec{F}_{TOT} = \vec{0} \text{ (I)}\\ \\
\vec{\mathcal{M}}_{TOT} = \vec{0} \text{ (II)}
\end{cases} \tag{13}
$$
## MOTI DEL CORPO RIGIDO
Oltre a *traslare* come un punto materiale, il corpo rigido può anche **ruotare**.
In generale, può **traslare** e **ruotare** *contemporaneamente* (*rototraslazione*).
### ROTAZIONE
Consideriamo un corpo rigido che ruota attorno ad un asse fisso.
```tikz
\usepackage{tikz}
\usetikzlibrary{calc}
\usetikzlibrary{arrows.meta}
\tikzset{>=latex} 

\begin{document}

\tikzset{
  pics/rotarr/.style={
    code={
      \draw[white,very thick] ({#1*cos(200)},0) arc(-200:30:{#1} and {#1/2}) --++ (125:0.1);
      \draw[->] ({#1*cos(200)},0) coordinate (W1) arc(-200:20:{#1} and {#1/2}) node[midway] (W2) {} --++ (125:0.1) coordinate (W3);
  }},
  pics/rotarr/.default=0.4
}

  \def\R{1.4}
  \def\ang{8}
  \def\angp{40} % perspective
  \def\angdr{14}

  \def\L{1.2}    % size scale
  \def\bodyshape{
    (-100:1.0*\L) to[out=190,in=-90]
    (180:1.4*\L) to[out=90,in=175]
    (110:1.0*\L) to[out=-5,in=185]
    ($(\ang:\R)+(110:0.6*\L)$) to[out=5,in=90]
    ($(\ang:\R)+(15:0.7*\L)$) to[out=-90,in=-20]
    ($(\ang:\R)+(-120:0.7*\L)$) to[out=160,in=10] cycle
  }
  \def\body{ % shape
    \fill[ball color=red!80!black] \bodyshape;
    \draw[line width=0.6,draw=red!30!black,fill=red!60!black!5,fill opacity=0.85]
     \bodyshape;
  }

\begin{tikzpicture}
  \coordinate (M) at (0,0); 
  
  \draw (M) --++ (-90:1.5*\L);

  \draw (M) --++ (-30:2.2*\L) node[at end, right] {}; 
  \draw (M) --++ (-150:2.2*\L) node[at end, left] {}; 

  \draw[->] (M) --++ (30:2*\L) node[at end, right] {$\vec{u}_{x}$};
  \draw[->] (M) --++ (150:2*\L) node[at end, left] {$\vec{u}_{y}$}; 
  
  \body
  \draw[dashed] (M) --++ (30:1.5*\L) node[at end, right] {};
  \draw[dashed] (M) --++ (150:1.4*\L) node[at end, left] {}; 

  \draw[dashed] (M) --++ (-30:\L) node[at end, right] {};
  \draw[dashed] (M) --++ (-150:1.4*\L) node[at end, left] {}; 
  
  \draw[dashed] (M) --++ (-90:\L) node(Z)[at end]{}; 
  
  \draw[dashed] (M) --++ (90:0.75*\L) node(Z)[at end]{}; 
  \draw[->] (Z) --++ (90:1.2*\L) node[at end, above]{$\vec{u}_{z}$}; 
  \pic at ($(M)+(0,1.8)$) {rotarr}; 

  \draw[black,fill=gray!50] (1.2,0.4,0) -- ++(-0.2,0,0) -- ++(0,-0.2,0) -- ++(0.2,0,0) -- cycle;
  \draw[black,fill=gray!50] (1.2,0.4,0) -- ++(0,0,-0.2) -- ++(0,-0.2,0) -- ++(0,0,0.2) -- cycle;
  \draw[black,fill=gray!50] (1.2,0.4,0) -- ++(-0.2,0,0) -- ++(0,0,-0.2) -- ++(0.2,0,0) -- cycle;

  \node (C) at (1.2,0.4,0){};
  \node at (C)[right=10pt, below]{$dV$};
  \draw[->] (0,0,0) -- (1,0.3,0); 

  \draw[blue] (0.5,0.15,0) arc (30:66:1.04) node[right=3pt, below]{$\phi$};  
  
\end{tikzpicture}

\end{document}
```
Consideriamo un volumetto $dV$ in posizione $\vec{r}$: durante la rotazione, $dV$ compie una traiettoria circolare di raggio $R$ attorno a $\vec{u}_{z}$, con $R=\|\vec{r}\|\sin \phi$.
Se supponiamo che gli assi $\vec{u}_{x}$ e $\vec{u}_{y}$ si muovano insieme al corpo, nel disegno abbiamo:
$$
\vec{r} = -\|\vec{r}\|\sin (\phi) \vec{u}_{y} + \|\vec{r}\|\cos (\phi)\vec{u}_{z} \tag{14}
$$
$$
\vec{v} = \|\vec{v}\|\vec{u}_{x} \tag{15}
$$
Supponiamo che ruoti con velocità angolare $\vec{\omega}=\omega\vec{u}_{z}$, e che quindi la velocità tangenziale del volumetto sia $\|\vec{v}\| = R\omega$.
Allora:
$$
\vec{r} = -R\vec{u}_{y} + R\cot(\phi) \vec{u}_{z} \tag{16}
$$
$$
\vec{v} = R\omega \vec{u}_{x} \tag{17}
$$
Il momento angolare sul volumetto è:
$$
d\vec{L} = \vec{r} \times d\vec{p} = \vec{r} \times \vec{v}dm \tag{18}
$$
Sostituendo $\vec{r}$ e $\vec{v}$ troviamo:
$$
d\vec{L} = \underbrace{ (R^2\omega dm) }_{ dL_{z} } \vec{u}_{z} + \underbrace{ (R^2\omega \cot(\phi)dm) }_{ dL_{\perp} }\vec{u}_{x} \tag{19}
$$
Per cui, a questo punto, possiamo calcolare il **momento angolare totale**:
$$
\vec{L} = \int_{\mathbf{V}} d\vec{L} = \int_{\mathbf{V}} dL_{z} \vec{u}_{z} + \int_{\mathbf{V}} dL_{\perp} \vec{u}_{\perp} \tag{20}
$$
Il secondo termine è generalmente complesso da calcolare, soprattutto se si considerano assi fissi per il piano di rotazione (mentre noi abbiamo assunto che seguissero la rotazione del corpo).
In presenza di *simmetrie assiali* del corpo, tuttavia, tale termine è nullo.
In tal caso si ha:
$$
\vec{L} = \int_{\mathbf{V}} R^2\omega dm \vec{u}_{z} = \left( \int_{\mathbf{V}} R^2dm \right)\omega \vec{u}_{z} \tag{21}
$$
E si definisce il **momento di inerzia** lungo $\vec{u}_{z}$:
$$
I_{z} = \int_{\mathbf{V}} R^2dm = \int_{\mathbf{V}}(x^2+y^2)\rho(x,y) dV \tag{22}
$$
E si ha:
$$
\vec{L} = I_{z}\vec{\omega} \tag{23}
$$
$I_{z}$ è una quantità che dipende dalla geometria del corpo e dalla distribuzione della sua massa. Inoltre, è interpretabile come l'analogo rotazionale della massa inerziale.
Dal *teorema del momento angolare* ricaviamo anche:
$$
\vec{\mathcal{M}} = \frac{d\vec{L}}{dt} = I_{z} \frac{d\vec{\omega}}{dt} = I_{z}\vec{\alpha} \tag{24}
$$
### ENERGIA CINETICA ROTAZIONALE
Calcoliamo ora l'energia cinetica associata alla rotazione.
Partiamo dall'energia infinitesimale del volumetto:
$$
dE_{K} = \frac{1}{2}\|\vec{v}\|^2dm = \frac{1}{2}(\omega R)^2dm \tag{25}
$$
E quindi:
$$
E_{K} = \int_{\mathbf{V}}dE_{K} = \frac{1}{2}\omega^2\int_{\mathbf{V}}R^2dm = \frac{1}{2}I_{z}\omega^2 \tag{26}
$$
>[!warning] OSSERVAZIONE
>Anche da questa relazione notiamo come $I_{z}$ sia una quantità analoga alla massa inerziale per la rotazione.

Ora, sappiamo che per il teorema dell'energia cinetica vale, per una qualche forza esterna $\vec{F}_{ext}$:
$$
\mathcal{W}_{ext}\left[\Gamma_{01} \right] = E_{K}(t_{1}) - E_{K}(t_{0}) = \frac{1}{2}I_{z}\big(\omega(t_{1})\big)^2 - \frac{1}{2}I_{z}\big(\omega(t_{0})\big)^2 \tag{27}
$$
Fissiamo $t_{0}$ e studiamo la variazione di $\mathcal{W}_{ext}$ al variare di $t:=t_{1}$.
$$
\frac{d\mathcal{W}}{dt} = \frac{d}{dt}\left(\frac{1}{2}I_{z}\big(\omega(t)\big)^2 - \frac{1}{2}I_{z}\big(\omega(t_{0})\big)^2\right) = I_{z}\omega(t)\alpha(t) \tag{28}
$$
Allora abbiamo:
$$
\frac{d\mathcal{W}}{dt} = I_{z} \frac{d\theta}{dt} \alpha dt \implies d\mathcal{W} = I_{z}\alpha dt \tag{29}
$$
Per cui possiamo calcolare il lavoro compiuto da $\vec{F}_{ext}$:
$$
\mathcal{W} = \int d\mathcal{W} = \int I_{z}\alpha d\theta = \int\mathcal{M}d\theta \tag{30}
$$
E deduciamo quindi che una forza esterna con momento non nullo compie lavoro opponendosi alla rotazione del corpo.
## TEOREMA DI HUYGENS - STEINER
Dato un asse passante per il CM e uno parallelo ad esso, a distanza $L$, vale:
$$
I_{z}' = I_{z,CM} + ML^2 \tag{31}
$$

```tikz
\usepackage{tikz}
\usetikzlibrary{calc}
\usetikzlibrary{arrows.meta}
\tikzset{>=latex} 
\tikzstyle{CM}=[black,fill=black!50]

\begin{document}

\tikzset{
  pics/rotarr/.style={
    code={
      \draw[white,very thick] ({#1*cos(200)},0) arc(-200:30:{#1} and {#1/2}) --++ (125:0.1);
      \draw[->] ({#1*cos(200)},0) coordinate (W1) arc(-200:20:{#1} and {#1/2}) node[midway] (W2) {} --++ (125:0.1) coordinate (W3);
  }},
  pics/rotarr/.default=0.4
}

  \def\R{1.4}
  \def\ang{8}
  \def\angp{40} 
  \def\angdr{14}

  \def\L{1.2}    
  \def\bodyshape{
    (-100:1.0*\L) to[out=190,in=-90]
    (180:1.4*\L) to[out=90,in=175]
    (110:1.0*\L) to[out=-5,in=185]
    ($(\ang:\R)+(110:0.6*\L)$) to[out=5,in=90]
    ($(\ang:\R)+(15:0.7*\L)$) to[out=-90,in=-20]
    ($(\ang:\R)+(-120:0.7*\L)$) to[out=160,in=10] cycle
  }
  \def\body{ % shape
    \fill[ball color=red!80!black] \bodyshape;
    \draw[line width=0.6,draw=red!30!black,fill=red!60!black!5,fill opacity=0.85]
     \bodyshape;
  }
\begin{tikzpicture}
  \coordinate (M) at (0,0); 
  \coordinate (R) at (-20:1.7); 
  \draw (M) --++ (-108:1.5*\L) node[above left] {$I_{CM}$}; 
  \draw (R) --++ (-108:0.9*\L) node[above=4,right=2] {$I'$}; 
  \draw (R) --++ (72:2.7);
  \body
  \draw[CM] (M) circle(0.06) node[left] {CM}; 
  \draw (M)++(72:0.05) --++ (72:2.6); 
 
  \draw[<->] (72:2) --++ (-20:1.7) 
    node[pos=0.5, above]{$L$};
\end{tikzpicture}

\end{document}
```

# ==PURO ROTOLAMENTO==
Consideriamo un oggetto di sezione circolare che ruota su un piano senza strisciare.
Chiamiamo $A$ il punto apicale e $C$ il punto di contatto, come in figura:

```tikz
\usepackage{tikz} 

\begin{document}

\begin{tikzpicture}

  \draw[thick] (0,0) -- (10,0) node[right]{};
  \fill[ball color=red!90!black] (5,2) circle (2);
  \draw[line width=0.6,draw=red!30!black,fill=red!60!black!5,fill opacity=0.85] (5,2) circle (2);
  
  \filldraw[] (5,2) circle (0.1) node[left=2pt]{$CM$};
  \draw[->, thick] (5,2) -- (6,2) node[at end, right]{$\vec{v}_{CM}$};
  \draw[->, thick] (5,2.5) arc (90:-90:0.5) node[at end, below=2pt]{$\omega$};

  \filldraw[] (5,4) circle (0.1) node[above=2pt]{$A$};
  \draw[->, thick] (5,4) -- (7,4) node[pos=0.5, above]{$\vec{v}_{A}$};

  \filldraw[] (5,0) circle (0.1) node[below=2pt]{$C$};

\end{tikzpicture}

\end{document}
```
Il corpo ruota con velocità angolare $\omega$ e il CM si sposta con $\vec{v}_{CM}$.
In $C$ agiscono delle forze, tra cui ci aspettiamo sicuramente $\vec{F}_{R}$ e $\vec{F}_{A}$.
## SISTEMA SOLIDALE AL CM
Nel sistema del CM, $A$ ruota attorno al CM con:
$$
\vec{v}'_{A} = \vec{\omega} \times \vec{r}'_{A} = (\omega \vec{u}'_{y}) \times (r\vec{u}'_{z}) = \omega r \vec{u}'_{x} \tag{32}
$$
**NOTA:** $\vec{u}_{y}$ è perpendicolare al piano della figura.
E similmente, abbiamo anche:
$$
\vec{v}'_{C} = -\omega r\vec{u}'_{x} \tag{33}
$$
## SISTEMA SOLIDALE AL PAVIMENTO
In questo sistema abbiamo invece:
$$
\vec{v}_{CM} = v\vec{u}_{x} \tag{34}
$$
$$
\vec{v}_{A} = \vec{v}'_{A} + \vec{v}_{CM} = (\omega r + v) \vec{u}_{x} \tag{35}
$$
$$
\vec{v}_{C} = \vec{v}'_{C} + \vec{v}_{CM} = (v- \omega r)\vec{u}_{x} \tag{36}
$$
## CONDIZIONE PER IL PURO ROTOLAMENTO
La condizione per il puro rotolamento, cioè una rotazione senza slittamento, è che la *velocità nel punto di contatto sia nulla*.
Significa che $C$ fa da perno per la rotazione, e quindi segue anche:
$$
v_{CM} = \omega r \text{ }\text{ (I)}\tag{37}
$$
Il che ci dice proprio che la velocità del CM è dovuta proprio alla sua rotazione intorno al punto di contatto.
Derivando tale equazione si trovano anche le seguenti:
$$
M\vec{a}_{CM} = \vec{F}_{TOT} \text{ }\text{ (II)}\tag{38}
$$
$$
I\vec{\alpha} = \vec{\mathcal{M}}_{TOT} \text{ }\text{ (III)}\tag{39}
$$
(I), (II) e (III) sono le equazioni del puro rotolamento.
## FORZE ESTERNE E ATTRITO
Supponiamo ora che il corpo sia soggetto ad una forza esterna $\vec{F}_{ext}$ e ad un momento esterno $\vec{\mathcal{M}}_{ext}$.
Allora abbiamo:
$$
\vec{F}_{TOT} = \vec{F}_{ext} + \vec{F}_{R} + \vec{F}_{A} \tag{40}
$$
E quindi: 
$$
\vec{\mathcal{M}}_{TOT} = \mathcal{M}_{ext}\vec{u}_{y} + \cancelto{ 0 }{ \vec{r}_{C} \times \vec{F}_{R} } + \underbrace{ \vec{r}_{C} \times \vec{F}_{A} }_{ =-rF_{A}\vec{u}_{y} } \tag{41}
$$
Cioè:
$$
\vec{\mathcal{M}}_{TOT} = (\mathcal{M}_{ext}-rF_{A})\vec{u}_{y} \tag{42}
$$
Consideriamo (II):
- Lungo $\vec{u}_{z}$ (verticale) non c'è moto, per cui $F_{R} = -F_{ext,\perp}$.
- Lungo $\vec{u}_{x}$, invece, si ha: 
$$
Ma_{CM} = F_{ext,\parallel} +F_{A} \tag{43}
$$

Consideriamo ora (III):
il momento totale ha solo una componente lungo $\vec{u}_{y}$, per cui:
$$
I\alpha = \mathcal{M}_{ext} - rF_{A} \tag{44}
$$
Da ==(43)== troviamo:
$$
M\alpha r = F_{ext,\parallel} + F_{A} \implies \alpha = \frac{F_{ext,\parallel}+F_{A}}{Mr} \tag{45}
$$
Da ==(44)==, invece:
$$
\alpha = \frac{\mathcal{M}_{ext} -rF_{A}}{I} \tag{46}
$$
Eguagliando ==(45)== e ==(46)== si ha allora:
$$
(F_{ext,\parallel}+F_{A})I = (\mathcal{M}_{ext} -rF_{A})Mr \tag{47}
$$
Risolvendo per $F_{A}$ troviamo:
$$
F_{A} = \frac{Mr\mathcal{M}_{ext} -F_{ext,\parallel}I}{I+Mr^2} \tag{48}
$$
E a questo punto possiamo sostituire ==(48)== in ==(43)== per trovare:
$$
a_{CM} = \frac{r}{I+Mr^2}(\mathcal{M}_{ext} + rF_{ext,\parallel}) \tag{49}
$$
>[!warning] NOTA
>Nelle ultime due equazioni compare il termine $I+Mr^2$.
>Grazie al teorema di Huygens-Steiner concludiamo che questo è il momento di inerzia del corpo rispetto ad un asse passante per il punto di contatto (a distanza $r$ dal CM).

Inoltre, essendo il punto di contatto fermo, la forza di attrito è quella dovuta all'attrito statico.
Allora deve valere anche:
$$
|F_{A}| < \mu_{s}|F_{R}| \tag{50}
$$
Altrimenti si avrebbe attrito dinamico, che vorrebbe dire che il punto di contatto *non è fermo* e quindi il corpo slitta.