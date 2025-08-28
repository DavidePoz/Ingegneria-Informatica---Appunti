# ==FLUIDOSTATICA==

>[!info] FLUIDO
>Un fluido occupa un *certo volume*, ma, al contrario del corpo rigido, **non** ha una **forma definita**: assume quella del contenitore. Sono fluidi i gas e i liquidi.
>**NOTA:** Al contrario dei gas, i liquidi sono **incomprimibili**.

Se consideriamo un volumetto infinitesimale all'interno del fluido, su di esso agiscono due tipi di forze:
- Forze di **volume**, dovute al volume complessivo di fluido.
- Forze di **pressione**, esercitate dal *resto del fluido* sul volumetto.

## FORZE DI VOLUME
Tali forze comprendono la forza peso, la forza centrifuga, la forza gravitazionale...
Vediamo, per esempio, la forza peso.
Sul volumetto agisce una forza peso infinitesimale data da:
$$
d\vec{F}_{p} = -gdm\vec{u}_{z} = -g\rho dV\vec{u}_{z} \tag{1}
$$
Dove $\rho$ è la *densità del fluido*.
Allora la forza peso totale è data da:
$$
\vec{F}_{p} = \int_{\mathbf{V}}d\vec{F}_{p} = -g\rho \int_{\mathbf{V}}dV\vec{u}_{z} = -g\rho V\vec{u}_{z} \tag{2}
$$
Dove $\mathbf{V}$ è la regione di spazio occupata dal fluido e $V$ il suo volume totale.
## FORZE DI PRESSIONE
Dato un certo $dV$, il resto del fluido esercita una certa forza ortogonale alla superficie di $dV$ stesso.
Consideriamo la seguente figura esemplificativa, con facce ben definite:

```tikz
\usepackage{tikz} 
\usetikzlibrary {perspective}

\begin{document}

\newcommand\triangoloide[3]{
  % faccia sopra
  \draw[thick] (tpp cs:x=0,y=0,z=0)
    -- (tpp cs:x=#1,y=0,z=#3)
    -- (tpp cs:x=#1,y=#2,z=#3)
    -- (tpp cs:x=0,y=#2,z=0) 
    -- cycle;
  \fill[gray!80!white] (tpp cs:x=0,y=0,z=0)
    -- (tpp cs:x=#1,y=0,z=#3)
    -- (tpp cs:x=#1,y=#2,z=#3)
    -- (tpp cs:x=0,y=#2,z=0) 
    -- cycle;
    
  % faccia destra
  \draw[thick] (tpp cs:x=0,y=0,z=0)
    -- (tpp cs:x=#1,y=0,z=0)
    -- (tpp cs:x=#1,y=0,z=#3)
    -- cycle;
  \fill[gray!50!white] (tpp cs:x=0,y=0,z=0)
    -- (tpp cs:x=#1,y=0,z=0)
    -- (tpp cs:x=#1,y=0,z=#3)
    -- cycle;}
\newcommand{\simpleaxes}[3]{
  \draw[->] (-0.5,0,0) -- (#1,0,0) node[pos=1.1]{$\vec{u}_x$};
  \draw[->] (0,-0.5,0) -- (0,#2,0) node[pos=1.15]{$\vec{u}_y$};
  \draw[->] (0,0,-0.5) -- (0,0,#3) node[pos=1.1]{$\vec{u}_z$};}

\begin{tikzpicture}[3d view]
  \draw[->, thick, red] (4, 1.25, 1) -- (2, 1.25, 1) node[pos=-0.25]{$d\vec{F}_B$};
  \draw[->, thick, dashed, blue] (1.5, 5, 0.75) -- (1.5, 2.5, 0.75) node[pos=-0.35, below]{$-d\vec{F}_D$};
  \draw[->, thick, red] (1, 1, -1.5) -- (1, 1, 0) node[pos=-0.25]{$d\vec{F}_A$};
  \triangoloide{2}{2.5}{2}

  \draw[->, thick, dashed, blue] (1.5, -2, 0.75) -- (1.5, 0, 0.75) node[pos=-0.2, below]{$d\vec{F}_D$};
  \draw[->, thick, red] (-0.5, 1.25, 2) -- (1, 1.25, 1) node[pos=-0.15, above]{$d\vec{F}_C$};

  \simpleaxes{3}{3}{3}
  \draw[dashed, thick] (0.75,0,0) arc (-30:-10:5) node[pos=0.5, right]{$\theta$};

\end{tikzpicture}

\end{document}
```

Siccome il corpo è all'equilibrio (è fermo), le forze evidenziate in blu sono uguali ed opposte; possiamo allora soffermarci su $d\vec{F}_{A}, d\vec{F}_{B}, d\vec{F}_{C}$.
Ci aspettiamo che tali forze siano in qualche modo proporzionali alle superfici delle facce: più estesa è la superficie e più fluido esercita una forza su di essa.
Scriviamo allora:
$$
d\vec{F}_{A} = p_{A}dS_{A}\vec{u}_{z} \tag{3}
$$
$$
d\vec{F}_{B} = -p_{B}dS_{B}\vec{u}_{x} \tag{4}
$$
$$
d\vec{F}_{C} = p_{C}dS_{C}(-\cos\theta \vec{u}_{z} + \sin\theta \vec{u}_{x})
$$
Dove abbiamo introdotto i coefficienti di proporzionalità $p_{A}, p_{B}, p_{C}$.
Tali coefficienti, tuttavia, *non* sono *indipendenti*. Essendo $dV$ in equilibrio, infatti, deve valere:
$$
d\vec{F}_{A} + d\vec{F}_{B} + d\vec{F}_{C} = \vec{0} \tag{5}
$$
Inoltre, anche $dS_{A}, dS_{B}, dS_{C}$ sono legate dalla geometria del problema e si ha:
$$
\begin{matrix}
dS_{A} = \cos\theta dS_{C} \\
dS_{B} = \sin\theta dS_{C}
\end{matrix} \tag{6}
$$
Allora l'equazione ==(5)== diventa:
$$
p_{A}\cos\theta dS_{C}\vec{u}_{z} - p_{B}\sin\theta dS_{C}\vec{u}_{x} + p_{C}\sin\theta dS_{C}\vec{u}_{x} - p_{C}\cos\theta dS_{C}\vec{u}_{z} = \vec{0} \tag{7}
$$
Che riscriviamo come:
$$
(p_{A} - p_{C})\cos\theta dS_{C}\vec{u}_{z} + (p_{C} - p_{B})\sin\theta dS_{C}\vec{u}_{x} = \vec{0} \tag{8}
$$
Ma allora deve essere:
$$
p_{A} = p_{B} = p_{C} := p \tag{9}
$$
Chiamiamo $p$ **pressione**: è la forza per unità di superficie (era infatti $dF=pdS$).
## PRESSIONE IN PRESENZA DELLA FORZA PESO
Consideriamo un volumetto $dV$ come in figura, soggetto anche alla forza peso:

```tikz
\usepackage{tikz} 
\usetikzlibrary {perspective}

\begin{document}

\newcommand\cuboid[3]{
  \fill[gray!80!white] (tpp cs:x=0,y=0,z=#3)
    -- (tpp cs:x=0,y=#2,z=#3)
    -- (tpp cs:x=#1,y=#2,z=#3)
    -- (tpp cs:x=#1,y=0,z=#3) -- cycle;
  \fill[gray]  (tpp cs:x=0,y=0,z=0)
    -- (tpp cs:x=0,y=0,z=#3)
    -- (tpp cs:x=0,y=#2,z=#3)
    -- (tpp cs:x=0,y=#2,z=0) -- cycle;
  \fill[gray!50!white] (tpp cs:x=0,y=0,z=0)
    -- (tpp cs:x=0,y=0,z=#3)
    -- (tpp cs:x=#1,y=0,z=#3)
    -- (tpp cs:x=#1,y=0,z=0) -- cycle;
}
    
\newcommand{\simpleaxes}[3]{
  \draw[->] (-0.5,0,0) -- (#1,0,0) node[pos=1.1]{$\vec{u}_x$};
  \draw[->] (0,-0.5,0) -- (0,#2,0) node[pos=1.15]{$\vec{u}_y$};
  \draw[->] (0,0,-0.5) -- (0,0,#3) node[pos=1.1]{$\vec{u}_z$};
}

\begin{tikzpicture}[3d view]
  
  \draw[->, thick, dashed, blue] (4, 1.1, 1) -- (2, 1.1, 1) 
  node[pos=-0.7]{$d\vec{F}_{pr,x}(x+dx)$};
  \draw[->, thick, dashed, blue] (1, 5, 1) -- (1, 2, 1) 
  node[pos=-0.5]{$d\vec{F}_{pr,y}(y+dy)$};
  \draw[->, thick, red] (1, 1.1, -1.5) -- (1, 1.1, 0) 
  node[pos=-0.25]{$d\vec{F}_{pr,z}(z)$};
  
  \cuboid{2}{2.2}{2}

  \draw[->, thick, dashed, blue] (1, -3.3, 1) -- (1, 0, 1) 
  node[pos=-0.4]{$d\vec{F}_{pr,y}(y)$};
  \draw[->, thick, dashed, blue] (-2.2, 1.1, 1) -- (0, 1.1, 1) 
  node[pos=-0.15, below]{$d\vec{F}_{pr,x}(x)$};
  \draw[->, thick, red] (1, 1.1, 3.5) -- (1, 1.1, 2) 
  node[pos=-0.15]{$d\vec{F}_{pr,z}(z+dz)$};

  \simpleaxes{3.5}{4.9}{3.2}
  
\end{tikzpicture}

\end{document}
```

Esattamente come prima, essendo il volumetto in equilibrio e non essendoci alcuna forza esterna lungo $\vec{u}_{x}$ e $\vec{u}_{y}$, le forze evidenziate in blu sono uguali ed opposte lungo le rispettive direzioni.
Concludiamo allora che la pressione *non varia* lungo $\vec{u}_{x}$ e $\vec{u}_{y}$.
Per cui:
$$
\begin{matrix}
\frac{\partial}{\partial x}p(x,y,z) = 0 \\
\frac{\partial}{\partial y}p(x,y,z) = 0
\end{matrix} \tag{10}
$$
Lo stesso **non** si può dire lungo $\vec{u}_{z}$: lungo questa direzione agisce infatti anche la forza peso.
Per cui, lungo $\vec{u}_{z}$, deve essere:
$$
dF_{pr,z}(z) + dF_{pr,z}(z+dz) + dF_{p} = 0 \tag{11}
$$
Da cui segue:
$$
p(z)dS_{z} - p(z+dz)dS_{z} -g\rho dV = 0 \tag{12}
$$
Ma $dV=dS_{z}dz$.
Allora:
$$
\big( p(z) - p(z+dz) -g\rho dz \big)dS_{z} = 0 \tag{13}
$$
A questo punto, ricordiamo che $p(z+dz) = p(z) + \frac{\partial}{\partial z}p(x,y,z)dz$ e otteniamo:
$$
\cancel{ p(z) - p(z) } -\frac{\partial}{\partial z}pdz -g\rho dz = 0 \tag{14}
$$
E allora:
$$
\frac{\partial p}{\partial z} = -g\rho \tag{15}
$$
Da cui ricaviamo:
>[!info] LEGGE DI STEVINO
>$$p(z) = -g\rho z + p_{0} \tag{16}$$

>[!warning] NOTA
>Se il fluido è soggetto alla sola forza peso, diretta lungo $\vec{u}_{z}$, allora la pressione varia solo lungo $\vec{u}_{z}$ ed è costante lungo le altre.
>In generale, tuttavia, $p$ potrebbe variare anche lungo $\vec{u}_{x}$ e $\vec{u}_{y}$ se vi fossero delle forze agenti lungo tali direzioni.
## ESPERIMENTO DI TORRICELLI
Capovolgendo un tubo (chiuso ad un'estremità) pieno di un fluido in una vasca contenente altro fluido, si osserva che nel tubo rimane una colonna di fluido alta $\Delta h$ rispetto al pelo del contenuto della vasca.

```tikz
\usepackage{tikz}
\usetikzlibrary{patterns,decorations.pathmorphing}
\usetikzlibrary{arrows.meta}
\tikzset{>=latex}

\colorlet{mydarkblue}{blue!50!black}
\colorlet{myred}{red!65!black}
\colorlet{watercol}{blue!80!cyan!10!white}
\colorlet{darkwatercol}{blue!80!cyan!20!white}
\tikzstyle{piston}=[blue!50!black,top color=blue!30,bottom color=blue!50,middle color=blue!20,shading angle=0]
\tikzstyle{water}=[draw=mydarkblue,top color=watercol!90,bottom color=watercol!90!black,shading angle=5]
\tikzstyle{vertical water}=[water,
  top color=watercol!90!black!90,bottom color=watercol!90!black!90,middle color=watercol!80,shading angle=90]
\def\tick#1#2{\draw[thick] (#1)++(#2:0.1) --++ (#2-180:0.2)}

\begin{document}

% PRESSURE TORRICELLI
\begin{tikzpicture}
  \def\Rx{1.0}
  \def\Ry{0.05*\Rx}
  \def\rx{0.18*\Rx}
  \def\ry{0.06*\rx}
  \def\H{1.0}
  \def\h{0.72*\H}   % water level height
  \def\th{2.7*\H}   % tube height
  \def\ty{0.95*\th} % tube level
  \def\td{0.6*\h}   % tube depth
  \def\N{14}
  
  % WATER + CONTAINER
  \draw[vertical water] %rounded corners=2
    (-\Rx,\h) --++ (0,-\h) arc(180:360:{\Rx} and {\Ry}) --++ (0,\h);
  \draw[water]
    (0,\h) ellipse ({\Rx} and {\Ry});
  \draw[thick] (0,\H) ellipse ({\Rx} and {\Ry});
  
  % TUBE
  \draw[vertical water]
    (-\rx,\h) |-++ (2*\rx,\ty) --++ (0,-\ty);
  \draw[water]
    (0,\h+\ty) ellipse ({\rx} and {\ry});
  \draw[thick,line cap=round]
    (-\rx,\h) --++ (0,\th) coordinate (T) arc(180:0:\rx) --++ (0,-\th);
  \draw[mydarkblue,line cap=round]
    (-1.09*\rx,\h-0.005) arc(180:360:{1.09*\rx} and 1.12*\ry);
  \foreach \i [evaluate={\y=1.12*\H+0.84*\th*\i/\N}] in {0,...,\N}{
    \draw[line cap=round] (\rx,\y) arc(0:-50:{\rx} and \ry);
  }
  \begin{scope}
    \clip (-\Rx,\h) |-++ (2*\Rx,-\h) --++ (0,\h) arc(360:180:{\Rx} and {\Ry}) -- cycle;
    \draw[thick,line cap=round]
      (-\rx,\h) --++ (0,-\td) (\rx,\h) --++ (0,-\td);
      %(-\rx,\h) --++ (0,-\td) arc(180:360:{\rx} and {\ry}) --++ (0,\td);
    \draw[vertical water,opacity=0.5]
      (-\Rx,\h) --++ (0,-\h) arc(180:360:{\Rx} and {\Ry}) --++ (0,\h);
  \end{scope}
  \draw[mydarkblue]
    (-\Rx,\h) arc(180:360:{\Rx} and {\Ry});
  \draw[<->] (0.6*\Rx,\h) --++ (0,\ty) node[midway, right] {$\Delta h$};
  \draw[very thin,line cap=round]
    (T)++(130:\rx) node[anchor=-19,inner sep=1] {$P=0$} to[out=-50,in=170]++ (-30:1.5*\rx);
  \node[above] at (-0.6*\Rx,\H) {$P_\mathrm{atm}$};
  
  % CONTAINER
  \draw[thick]
    (-\Rx,\H) --++ (0,-\H) arc(180:360:{\Rx} and {\Ry})
              --++ (0,\H) arc(360:180:{\Rx} and {\Ry}) -- cycle;
  
\end{tikzpicture}

\end{document}
```

**NOTA:** nella parte alta del tubo rimane il vuoto, a pressione nulla.

Torricelli eseguì questo esperimento usando del *mercurio* come fluidi, misurando
$$
\Delta h = 760mm \tag{17}
$$
### INTERPRETAZIONE
Scelti due punti alla stessa quota, le pressioni saranno uguali per la legge di Stevino.
Scegliamo allora un punto sul pelo dell'acqua, fuori dal tubo, e uno alla stessa quota ma all'interno del tubo.
Nel primo punto la pressione è quella atmosferica, mentre nel secondo è quella data dalla colonna di mercurio, e queste sono uguali.
Per cui:
$$
p_{atm} = 760\text{mmHg} = 1atm \tag{18} 
$$
Conoscendo $\Delta h$, $g$ e $\rho_{Hg}$ troviamo:
$$
p_{atm} = \rho g \Delta h \approx 1,01\cdot 10^5 \text{ Pa} \tag{19}
$$

## PRINCIPIO DI ARCHIMEDE
Consideriamo un fluido e concentriamo la nostra attenzione su una regione di volume $V$, su cui agiscono forze di pressione dovute al resto del fluido e la forza peso.
Abbiamo visto che all'equilibrio lungo $\vec{u}_{z}$ vale:
$$
F_{pr} = -g\bar{\rho}V \tag{20}
$$
Dove $\bar{\rho}$ è la densità media del fluido nella regione considerata.
Se *sostituissimo* questa porzione di fluido con un ugual volume di una sostanza *diversa* di densità media $\rho'$, $F_{pr}$ rimarrebbe invariata perchè dipende solo dal fluido circostante (che non è stato modificato in alcun modo), mentre la *forza peso* **cambierebbe** perchè dipende dalla massa della sostanza.
Allora avremmo:
$$
F_{TOT} = F_{pr} + F_{p} = g(\bar{\rho}-\rho')V \tag{21}
$$
Dove $V$ è la porzione sommersa del volume del corpo.
Notiamo che la componente seguente
$$
F_{A} = g\bar{\rho}V \tag{22}
$$
rappresenta una spinta verso l'alto dovuta alla massa di fluido spostata ($\bar{\rho}V$) dal corpo, ed è detta **spinta di Archimede**.
All'equilibrio si ha:
$$
g\bar{\rho}V_{s} = g\rho'V_{tot} \tag{23}
$$
Dove il membro di sinistra è la spinta di Archimede data dal volume sommerso $V_{s}$ e il membro di destra è la forza peso subita dal corpo.
Si trova allora:
$$
\frac{V_{s}}{V_{tot}} = \frac{\rho'}{\bar{\rho}} \tag{24}
$$
Per cui la percentuale di volume sommerso sarà tanto maggiore quanto più alta è la densità del corpo in rapporto a quella del fluido.

# ==FLUIDODINAMICA==
## LAVORO DELLE FORZE DI PRESSIONE
Consideriamo un fluido che si muove in un tubo *sotto l'azione di una forza di pressione*, come in figura:

```tikz
\usepackage{tikz}
\usetikzlibrary{patterns,decorations.pathmorphing}
\usetikzlibrary{decorations.markings}
\usetikzlibrary{arrows.meta}
\usetikzlibrary{calc}
\tikzset{>=latex}

\colorlet{mydarkblue}{blue!40!black}
\colorlet{myblue}{blue!70!black}
\colorlet{myred}{red!65!black}
\colorlet{myorange}{orange!90!black!90}
\colorlet{vcol}{green!45!black}
\colorlet{watercol}{blue!80!cyan!10!white}
\colorlet{darkwatercol}{blue!80!cyan!80!black!30!white}
\colorlet{metalcol}{blue!25!black!30!white}
\tikzstyle{piston}=[blue!50!black,top color=blue!30,bottom color=blue!50,middle color=blue!20,shading angle=0]
\tikzstyle{water}=[draw=mydarkblue,rounded corners=0.1,top color=watercol!90,bottom color=watercol!90!black,middle color=watercol!50,shading angle=20]
\tikzstyle{horizontal water}=[water,top color=watercol!90!black!90,bottom color=watercol!90!black!90,middle color=watercol!80,shading angle=0]
\tikzstyle{metal}=[draw=metalcol!10!black,rounded corners=0.1,top color=metalcol,bottom color=metalcol!80!black,shading angle=10]
\tikzstyle{vvec}=[->,very thick,vcol,line cap=round]
\tikzstyle{force}=[->,myred,very thick,line cap=round]
\tikzstyle{width}=[{Latex[length=4,width=3]}-{Latex[length=4,width=3]}]
\def\tick#1#2{\draw[thick] (#1)++(#2:0.12) --++ (#2-180:0.24)}

\begin{document}


% LAMINAR FLOW LAYERS
\begin{tikzpicture}
  \def\L{5.0}   % total length
  \def\Rx{0.40} % big pipe vertical radius right
  \def\Ry{0.90} % big pipe vertical radius right
  \def\v{1.3}   % velocity magnitude
  \def\N{3}     % velocity magnitude
  
  % WATER
  \draw[horizontal water,thick]
    (0,\Ry) -- (0,-\Ry)  coordinate (A1) --++ (\L,0) arc(-90:90:{\Rx} and \Ry) -- cycle;
  \draw[water]
    (\L,0) ellipse({\Rx} and \Ry);
  
  \draw[water]
    (0,0) ellipse({\Rx} and \Ry);

  \filldraw[mydarkblue!35] (0.4*\L,0) ellipse({0.285} and 0.64);
  \draw[] (0.4*\L,0) ellipse({0.285} and 0.64) node[left=6pt]{$dS$};
    
  \foreach \i [evaluate={\x=(\i-0.5)/(\N+0.5)}] in {1,...,\N}{
    \draw[mydarkblue] %,line width=0.05
      (0,0) ellipse({\x*\Rx} and \x*\Ry)
      (\L,0) ellipse({\x*\Rx} and \x*\Ry);
    \draw[mydarkblue] %,dashed]  %,line width=0.05
      (0, \x*\Ry) --++ (\L,0)
      (0,-\x*\Ry) --++ (\L,0); %arc(90:-90:{\x*\Rx} and \x*\Ry) --++ (-\L,0);
  }
  
  \draw[water,thick]
    (0,0) ellipse({\Rx} and \Ry);
  \foreach \i [evaluate={\x=(\i-0.5)/(\N+0.5)}] in {1,...,\N}{
    \draw[mydarkblue] (0,0) ellipse({\x*\Rx} and \x*\Ry);
  }
  
  \draw[vvec] (0.4*\L,0) --++ (\v,0)node[pos=0.7, above]{$\vec{v}$};  
  
\end{tikzpicture}

\end{document}
```

Calcoliamo il lavoro di tale forza di pressione.
Cominciamo con il lavoro della pressione infinitesima $d\vec{F}_{pr}$ sulla superficie $dS$:
$$
d\mathcal{W} = d\vec{F}_{pr}\cdot \vec{v} dt = pdS \vec{u}_{x} \cdot \frac{dx}{dt}\vec{u}_{x}dt = pdS \frac{dx}{\cancel{ dt }}\cancel{ dt } = pdSdx \tag{25}
$$
Dove abbiamo considerato una velocità parallela alla parete del tubo e all'asse $\vec{u}_{x}$
Ma $dSdx=dV$, ($dV$ è il volume che ha attraversato $dS$ nel tempo $dt$) per cui:
$$
\mathcal{W} = \int_{\mathbf{V}}d\mathcal{W} = \int_{\mathbf{V}}pdV \tag{26}
$$
>[!warning] NOTA
>$p$ è in buona approssimazione costante nella regione infinitesima $dV$, ma **non lo è** in tutto $\mathbf{V}$.

## FLUSSO E PORTATA
Consideriamo ora un fluido, e più in particolare un liquido, che si muove di un moto più generico rispetto a quello dell'esempio precedente. Scelto un volumetto $dV$, chiamiamo **linea di flusso** la traiettoria che esso segue.
Per il nostro studio ci limiteremo al caso in cui il fluido è in **regime stazionario**.

>[!info] REGIME STAZIONARIO
>Moto di un fluido non soggetto ad attriti e le cui *linee di flusso non si intersecano mai*.

**NOTA:** Le linee di flusso si intersecano in presenza di vortici (*turbolenza*).

Definiamo allora la **portata**, ovvero la quantità di fluido che passa attraverso una superficie in un determinato tempo:
$$
q = \int_{\mathbf{S}} vdS \tag{27}
$$
Consideriamo un volume infinitesimo $dV_{1}$ attraversato dal fluido con velocità $v_{1}$: allora abbiamo:
$$
dV_{1} = dS_{1}dx_{1} = dS_{1}v_{1}dt \tag{28}
$$
Immaginiamo di seguire il moto di tale volume fino ad un punto in cui la sezione considerata è $dS_{2}$ e la sua velocità è $v_{2}$.
$$
dV_{2} = dS_{2}v_{2}dt \tag{29}
$$
Essendo i liquidi incomprimibili, deve essere $dV_{1}=dV_{2}$, allora:
$$
dS_{1}v_{1}\cancel{ dt } = dS_{2}v_{2}\cancel{ dt } \implies dS_{1}v_{1} = dS_{2}v_{2} \implies q_{1} = q_{2} \tag{30}
$$
E concludiamo che la *portata è costante lungo il flusso*.
## GENERALIZZAZIONE LEGGE DI STEVINO
Dal teorema dell'energia cinetica, sappiamo che vale:
$$
\mathcal{W}_{tot} = \Delta E_{K} \tag{31}
$$
Nel caso di un fluido, abbiamo $\mathcal{W}_{tot}=\mathcal{W}_{pr} + \mathcal{W}_{vol}$.
Allora otteniamo:
$$
pdV + g\underbrace{ \rho dV }_{ m }dz = \Delta E_{K} \tag{32}
$$
Nel caso statico, $\Delta E_{K}=0$ e troviamo quindi:
$$
p\cancel{ dV } = -g\rho dz\cancel{ dV } \tag{33}
$$
E integrando:
$$
p(z) = -g\rho z + p_{0} \tag{34}
$$
Che è esattamente la *legge di Stevino*.
Per cui abbiamo dimostrato che, nel caso **statico**, la legge di Stevino e il *teorema dell'energia cinetica* sono **equivalenti**.
### TEOREMA DI BERNOULLI
Possiamo dedurre qualcosa anche dal caso *non statico*?
Per verificarlo, consideriamo $dV$ lungo il flusso:
- $dV=dx_{1}dS_{1}$ varia in $dV=dx_{2}dS_{2}$.
- La quota $z_{1}$ varia in $z_{2}$.
- La velocità $v_{1}$ varia in $v_{2}$.
- La pressione $p_{1}$ varia in $p_{2}$.

```tikz
\usepackage{tikz}
\usetikzlibrary{patterns,decorations.pathmorphing}
\usetikzlibrary{decorations.markings}
\usetikzlibrary{arrows.meta}
\usetikzlibrary{calc}
\tikzset{>=latex}

\colorlet{mydarkblue}{blue!40!black}
\colorlet{myblue}{blue!30}
\colorlet{myred}{red!65!black}
\colorlet{vcol}{green!45!black}
\colorlet{watercol}{blue!80!cyan!10!white}
\colorlet{darkwatercol}{blue!80!cyan!80!black!30!white}
\tikzstyle{water}=[draw=mydarkblue,top color=watercol!90,bottom color=watercol!90!black,middle color=watercol!50,shading angle=0]
\tikzstyle{horizontal water}=[water,
  top color=watercol!90!black!90,bottom color=watercol!90!black!90,middle color=watercol!80,shading angle=0]
\tikzstyle{dark water}=[draw=blue!20!black,top color=darkwatercol,bottom color=darkwatercol!80!black,middle color=darkwatercol!40,shading angle=0]
\tikzstyle{vvec}=[->,very thick,vcol,line cap=round]
\tikzstyle{force}=[->,myred,very thick,line cap=round]
\tikzstyle{width}=[{Latex[length=3,width=3]}-{Latex[length=3,width=3]}]

\begin{document}

% BERNOUILLI EQUATION
\begin{tikzpicture}
  \def\LL{1.7}          % length pipe left
  \def\LR{1.7}          % length pipe right
  \def\L{3.5*\LL}       % total length
  \def\H{2.0}           % height
  \def\l{\L-\LL-\LR}    % length between pipes
  \def\xL{0.32*\LL}     % position volume left
  \def\xR{\L-0.80*\LR}  % position volume right
  \def\lL{0.2*\LR}      % length volume left
  \def\lR{\lL*\Ry/\ry}  % length volume right
  \def\rx{0.08}         % small pipe horizontal radius left
  \def\ry{0.32}         % small pipe vertical radius left
  \def\Rx{0.18}         % big pipe vertical radius right
  \def\Ry{0.90}         % big pipe vertical radius right
  \def\v{1.0}           % velocity magnitude
  \def\F{0.9}           % force magnitude
  \def\y{(\Ry+0.35*\H)} % height from ground
  
  % WATER
  \draw[horizontal water,thick]
    (0,\Ry) -- (0,-\Ry) coordinate (P1) --++ (\LL,0) to[out=0,in=180]
    (\L-\LR,\H-\ry) -- (\L,\H-\ry) coordinate (P2) arc(-90:90:{\rx} and \ry)
    --++ (-\LR,0)  to[out=180,in=0] (\LL,\Ry) -- cycle;
  \node[above left=2] at (P1) {$p_1$};
  \node[above=-1,right=0] at (P2) {$p_2$};
  
  % VOLUMES
  \draw[vvec] (\xL+\lL+\Rx,0) --++ (\v*1.2*\ry/\Ry,0) node[above=-1] {$v_1$};
  \draw[vvec] (\xR+\lR+\rx,\H-0.2*\ry) --++ (\v,0) node[right=-2] {$v_2$};
  \draw[dark water,dashed,very thin]
    (\xL,0) ellipse({\Rx} and \Ry)
    (\xR,\H) ellipse({\rx} and \ry);
  \draw[dark water]
    (\xL,\Ry)
      arc(90:-90:{\Rx} and \Ry) coordinate (A1) --++ (\lL,0)
      arc(-90:90:{\Rx} and \Ry) -- cycle;
  \draw[dark water]
    (\xR,\H+\ry) coordinate (A2)
      arc(90:-90:{\rx} and \ry) --++ (\lR,0)
      arc(-90:90:{\rx} and \ry) -- cycle;
  \draw[width]
    (\xL,1.15*\Ry) --++ (\lL,0) node[midway,above] {$dx_1$};
  \draw[width]
    (\xR,\H-1.6*\ry) --++ (\lR,0) node[midway, below] {$dx_2$};
  \node[left=1,below=-1] at (A1) {$S_1$};
  \node[left=1,above=-1] at (A2) {$S_2$};
  
  % CONTAINER
  \draw[mydarkblue,thick]
    (0,\Ry) -- (0,-\Ry)  coordinate (A1) --++ (\LL,0) to[out=0,in=180]
    (\L-\LR,\H-\ry) -- (\L,\H-\ry) coordinate (A2) arc(-90:90:{\rx} and \ry)
    --++ (-\LR,0)  to[out=180,in=0] (\LL,\Ry) -- cycle;
  \draw[water,thick]
    (0,0) ellipse({\Rx} and \Ry);
  
  % HEIGHT
  \draw[dashed] (0.23*\L,{-\y}) --++ (0.6*\L,0);
  \draw[<->]
    (1.1*\LL,{-\y}) --++ (0,{\y}) node[pos=0.13,above right=-1] {$z_1$};
  \draw[<->]
    (0.95*\xR,{-\y}) --++ (0,{\H+\y}) node[midway, right] {$z_2$};
  
\end{tikzpicture}

\end{document}
```

Per il teorema dell'energia cinetica, abbiamo:
$$
p_{1}dV-p_{2}dV + dmgz_{1}-dmgz_{2} = \frac{1}{2}dmv_{2}^2 - \frac{1}{2}dmv_{1}^2 \tag{35}
$$
Che riscriviamo come:
$$
p_{1}dV-p_{2}dV + \rho dVgz_{1}-\rho dVgz_{2} = \frac{1}{2}\rho dVv_{2}^2 - \frac{1}{2}\rho dVv_{1}^2 \tag{36}
$$
Semplificando e riordinando, troviamo:
$$
p_{1} + \rho gz_{1} + \frac{1}{2}\rho v_{1}^2 = p_{2} + \rho gz_{2} + \frac{1}{2}\rho v_{2}^2 \tag{37}
$$
Ovvero:
>[!info] TEOREMA DI BERNOULLI
>La quantità
>$$p+\rho gz+\frac{1}{2}\rho v^2$$
>è **costante** lungo il flusso.
#### PRINCIPIO DI VENTURI
Dal teorema di Bernoulli, se consideriamo una quota $z$ costante, troviamo che vale:
>[!info] PRINCIPIO DI VENTURI
>La quantità
>$$p+\frac{1}{2}\rho v^2$$
>è **costante** lungo il flusso.

Consideriamo ora il *tubo di Venturi*, ovvero un tubo con sezioni di ampiezza diversa in punti diversi:
```tikz
\usepackage{tikz}
\usetikzlibrary{patterns,decorations.pathmorphing}
\usetikzlibrary{decorations.markings}
\usetikzlibrary{arrows.meta}
\usetikzlibrary{calc}
\tikzset{>=latex}

\colorlet{mydarkblue}{blue!40!black}
\colorlet{myblue}{blue!30}
\colorlet{myred}{red!65!black}
\colorlet{vcol}{green!45!black}
\colorlet{watercol}{blue!80!cyan!10!white}
\colorlet{darkwatercol}{blue!80!cyan!80!black!30!white}
\tikzstyle{water}=[draw=mydarkblue,top color=watercol!90,bottom color=watercol!90!black,middle color=watercol!50,shading angle=0]
\tikzstyle{horizontal water}=[water,
  top color=watercol!90!black!90,bottom color=watercol!90!black!90,middle color=watercol!80,shading angle=0]
\tikzstyle{dark water}=[draw=blue!20!black,top color=darkwatercol,bottom color=darkwatercol!80!black,middle color=darkwatercol!40,shading angle=0]
\tikzstyle{vvec}=[->,very thick,vcol,line cap=round]
\tikzstyle{force}=[->,myred,very thick,line cap=round]
\tikzstyle{width}=[{Latex[length=3,width=3]}-{Latex[length=3,width=3]}]

\begin{document}


% CONTINUITY EQUATION
\begin{tikzpicture}
  \def\LL{2.0}         % length pipe left
  \def\LR{1.7}         % length pipe right
  \def\L{3.0*\LL}      % total length
  \def\l{\L-\LL-\LR}   % length between pipes
  \def\xL{0.24*\LL}    % position volume left
  \def\xR{\L-0.80*\LR} % position volume right
  \def\lL{0.7*\LR}     % length volume left
  \def\lR{\lL*\ry/\Ry} % length volume right
  \def\rx{0.08}        % small pipe horizontal radius left
  \def\ry{0.32}        % small pipe vertical radius left
  \def\Rx{0.18}        % big pipe vertical radius right
  \def\Ry{0.90}        % big pipe vertical radius right
  \def\v{1.3}          % velocity magnitude
  
  % WATER
  \draw[horizontal water]
    (0,\ry) -- (0,-\ry)  coordinate (A1) --++ (\LL,0) to[out=0,in=180]
    (\L-\LR,-\Ry) -- (\L,-\Ry) coordinate (A2) arc(-90:90:{\Rx} and \Ry)
    --++ (-\LR,0)  to[out=180,in=0] (\LL,\ry) -- cycle;
  \node[above left] at (A1) {$S_1$};
  \node[above right=2] at (A2) {$S_2$};
  
  % VOLUMES
  \draw[vvec] (\xL+\lL+\rx,0) --++ (\v,0) node[right=-2] {$v_1$};
  \draw[vvec] (\xR+\lR+\Rx,0) --++ (\v*\ry/\Ry,0) node[right=-2] {$v_2$};
  \draw[dark water,dashed,very thin]
    (\xL,0) ellipse({\rx} and \ry)
    (\xR,0) ellipse({\Rx} and \Ry);
  \draw[dark water]
    (\xL,\ry) arc(90:-90:{\rx} and \ry) --++ (\lL,0)
              arc(-90:90:{\rx} and \ry) -- cycle;
  \draw[dark water]
    (\xR,\Ry) arc(90:-90:{\Rx} and \Ry) --++ (\lR,0)
              arc(-90:90:{\Rx} and \Ry) -- cycle;

  % CONTAINER
  \draw[mydarkblue,thick]
    (0,\ry) -- (0,-\ry)  coordinate (A1) --++ (\LL,0) to[out=0,in=180]
    (\L-\LR,-\Ry) -- (\L,-\Ry) coordinate (A2) arc(-90:90:{\Rx} and \Ry)
    --++ (-\LR,0)  to[out=180,in=0] (\LL,\ry) -- cycle;
  \draw[water,thick]
    (0,0) ellipse({\rx} and \ry);
  
\end{tikzpicture}

\end{document}
```

In regime stazionario, la portata è costante e si ha quindi:
$$
S_{1}v_{1} = S_{2}v_{2} \implies v_{2}=\frac{S_{1}}{S_{2}}v_{1} \tag{38}
$$
Per il principio di Venturi vale anche:
$$
p_{1} + \frac{1}{2}\rho v_{1}^2 = p_{2} + \frac{1}{2}\rho v_{2}^2 \tag{39}
$$
Da cui troviamo:
$$
p_{1} - p_{2} = \frac{1}{2}\rho(v_{2}^2-v_{1}^2) = \frac{1}{2}\rho v_{1}^2\left( \left( \frac{S_{1}}{S_{2}} \right)^2 -1 \right) < 0 \tag{40}
$$
Cioè la pressione è maggiore nel punto di sezione $S_{2}$.
Aumentando la sezione del tubo, diminuisce la velocità del fluido e aumenta la sua pressione.
