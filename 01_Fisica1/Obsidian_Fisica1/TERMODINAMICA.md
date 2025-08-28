# ==TEMPERATURA E CALORE==

## TEMPERATURA
E' un fatto intuitivo che, ponendo due corpi a temperature diverse a contatto, il più caldo si raffredda (e viceversa) fino a raggiungere un equilibrio.

>[!info] PRINCIPIO ZERO DELLA TERMODINAMICA
>Due corpi sono in **equilibrio termico** se hanno la stessa temperatura.
## CALORE
In particolare, il corpo caldo *cede calore* al corpo freddo, che lo *assorbe*.
Per convenzione:
- $Q>0$ se il calore è **assorbito**.
- $Q<0$ se il calore è **ceduto**.

>[!warning] NOTA
>Un corpo *ha una temperatura* e *scambia calore*.
>**Non** *ha calore*, in modo analogo all'energia e al lavoro.

### CALORE SPECIFICO
Si trova che la quantità di calore necessaria ad aumentare la temperatura di un corpo da $T_{0}$ a $T_{0}+dT$ è data da:
$$
dQ = c(T_{0})mdT \tag{1}
$$
Dove $m$ è la massa del corpo e $c(T_{0})$ è il **calore specifico**:
$$
c(T_{0}) = \frac{1}{m} \frac{dQ}{dT}\bigg|_{T_{0}} \tag{2} 
$$
E' *sempre positivo* ed è una proprietà che *dipende dal materiale*; inoltre, è approssimativamente costante al variare di $T_{0}$.
Per cui si ha che il calore scambiato da due corpi $A$ e $B$ a temperature $T_{A}$ e $T_{B}$ è dato da:
$$
Q_{AB} = \int_{T_{A}}^{T_{B}}dQ = cm\int_{T_{A}}^{T_{B}}dT = cm(T_{A}-T_{B})\tag{3}
$$
### CAPACITA' TERMICA
Consideriamo due corpi $1$ con $(m_{1},c_{1},T_{1})$ e $2$ con $(m_{2},c_{2},T_{2})$, con $T_{2}>T_{1}$.
Mettendoli a contatto, raggiungeranno la temperatura di equilibrio $T_{e}$ e si avrà:
$$
Q_{1} = c_{1}m_{1}(T_{e}-T_{1}) \tag{4}
$$
$$
Q_{2} = c_{2}m_{2}(T_{2}-T_{e}) \tag{5}
$$
Chiaramente, vale $T_{1}<T_{e}<T_{2}$.
Inoltre, dato che il calore ceduto da $2$ è lo stesso che viene assorbito da $1$, vale:
$$
Q_{1} + Q_{2} = 0 \tag{6}
$$
E si trova allora:
$$
T_{e} = \frac{c_{1}m_{1}T_{1}+c_{2}m_{2}T_{2}}{c_{1}m_{1}+c_{2}m_{2}} \tag{7}
$$
Notiamo che la temperatura $T_{e}$ è la media pesata delle temperature $T_{1}$ e $T_{2}$, dove i pesi sono dati dalla quantità $mc$.
Definiamo allora la **capacità termica**:
$$
C:= cm \tag{8}
$$
#### TERMOMETRI
Una sonda con capacità termica $C_{s}$ molto piccola rispetto alla capacità termica del corpo ($C_{c}$) di cui si vuole misurare la temperatura, misura:
$$
T_{e} = \frac{C_{s}T_{s}+C_{c}T_{c}}{C_{s}+C_{c}} \approx T_{c} \tag{9}
$$
Questo permette di costruire termometri e definire scale di temperatura:
- **CELSIUS**: a $0°\text{C}$ l'acqua gela, a $100°\text{C}$ bolle.
- **KELVIN**: un intervallo di un grado è uguale ad uno in gradi celsius, ma lo zero è posto a $0\text{ K} = -273,15°\text{C}$.

Definita la scala *celsius*, si definisce anche l'unità di misura del calore:
la **caloria**, che corrisponde al calore necessario ad aumentare la temperatura di $1\text{kg}$ di acqua da $14,5°\text{C}$ a $15,5°\text{C}$.
### CALORE LATENTE
Se un corpo allo stato **solido** si trova alla *temperatura di fusione*, il calore fornito **non** *fa aumentare la sua temperatura*, ma viene "impiegato" per *fondere il corpo*.
La temperatura tornerà ad *aumentare* solo *dopo aver fornito una certa quantità di calore* $Q_{f}$, data da:
$$
Q_{f} = \lambda m \tag{10}
$$
Dove $\lambda$ è detto **calore latente di fusione**.

>[!warning] NOTA
>Lo stesso avviene anche per il processo inverso: se il corpo allo stato liquido si trova alla temperatura di solidificazione (è uguale alla temperatura di fusione) dovrebbe cedere la stessa quantità $Q_{f}$ di calore prima che la sua temperatura cominci a diminuire.
### SCAMBIO ATTRAVERSO UNA BARRIERA
Dati due corpi a contatto tramite una barriera di spessore $dx$ e superficie $dS$, la quantità di calore scambiato è data da:
$$
dQ = -K \frac{dT}{dx}dSdt \tag{11}
$$
Dove $K$ è la **conducibilità termica** del materiale di cui è composta la barriera.
## SISTEMI TERMODINAMICI
Un sistema termodinamico può essere un oggetto, una massa di materia o una regione dello spazio che viene studiata dal punto di vista termodinamico: vale a dire studiandone il lavoro compiuto e gli scambi di calore al suo interno e, eventualmente, anche con l'ambiente esterno.
Un sistema termodinamico può essere:
- **APERTO** se scambia materia, calore e lavoro con l'ambiente esterno.
  **CHIUSO** nel caso contrario.
- **ADIABATICO** se scambia *solo lavoro* con l'ambiente esterno.
- **ISOLATO** se *non scambia niente* con un sistema esterno.

>[!warning] NOTA
>L'unico sistema *veramente isolato* è l'**universo** intero, perchè non è presente un sistema ad esso esterno. 
>Qualsiasi altro sistema non è mai propriamente isolato.

### EQUILIBRIO DI UN SISTEMA TERMODINAMICO
In meccanica abbiamo visto che un sistema è in *equilibrio* se $\vec{F}_{tot}=\vec{0}$ e $\vec{\mathcal{M}}_{tot}=\vec{0}$.

In termodinamica, invece, diciamo che un sistema è in **equilibrio** se $dQ=0$.
Un sistema all'equilibrio è inoltre identificato da:
- Una temperatura $T$.
- Una pressione $p$.
- Un volume $V$.

Queste tre quantità *non sono indipendenti*: esiste una funzione per cui si ha
$$
f_{eq}(p,V,T) = 0 \tag{12}
$$
Tale condizione è detta **equazione di stato** e verrà trattata in seguito.

**NOTA:** la maggior parte dei casi di studio in termodinamica riguarda i fluidi e, più in particolare, i gas.
## PIANO DI CLAPEYRON
In termodinamica, è utile studiare le trasformazioni nel piano $(p,V)$, detto *piano di Clapeyron*. 
Vediamo perchè considerando una generica trasformazione termodinamica tra due stati di equilibrio $A$ e $B$:

```tikz
\usepackage{tikz}
\usepackage{amsmath} % for \text
\usetikzlibrary{arrows.meta} % to control arrow size
\tikzset{>={Latex[length=4,width=4]}} % for LaTeX arrow head
\usetikzlibrary{calc,decorations.markings,arrows.meta}
\usepackage{xcolor} % for colored text

\colorlet{mylightblue}{blue!20}
\colorlet{myblue}{blue!80!black}
\colorlet{mydarkblue}{blue!30!black}
\colorlet{mylightred}{red!10}
\colorlet{myred}{red!80!black}
\colorlet{mydarkred}{red!60!black}
\colorlet{mydarkgreen}{green!30!black}

\tikzset{
  midarr/.style={decoration={markings,mark=at position #1 with {\arrow{stealth}}},postaction={decorate}},
  midarr/.default=0.5
}

\def\xtick#1#2{\draw[thick] (#1)++(0,.1) --++ (0,-.2) node[below=-.5pt,scale=0.9] {#2};}
\def\ytick#1#2{\draw[thick] (#1)++(.1,0) --++ (-.2,0) node[left=-.5pt,scale=0.9] {#2};}
\def\N{40} % number of plot samples
\def\xmax{3}
\def\ymax{2.5}

\begin{document}

\begin{tikzpicture}[scale=1.2]
  \def\isotherm{(A) to[out=-60,in=170] (B)}
  \def\T{1.2}
  \def\xa{.2*\xmax}
  \def\xb{.8*\xmax}
  \def\ya{{\T/(\xa)}}
  \def\yb{{\T/(\xb)}}
  
  % AREA
  \coordinate (A) at (\xa,\ya);
  \coordinate (B) at (\xb,\yb);
  \fill[mylightblue,samples=\N,domain=\xa:\xb]
    plot(\x,{\T/\x}) -- (B|-0,0) -- (A|-0,0) node[midway,left=4,above=5,blue] {$W$} -- cycle;
  
  % LINE
  \draw[myred,very thick,midarr=.58,samples=\N,domain={\xa}:{\xb}]
    plot(\x,{\T/\x});
  \fill (A) circle(0.07) node[right=5,above=2] {$p_A$, $V_A$};
  \fill (B) circle(0.07) node[right=2] {$p_B$, $V_B$};
  
  % AXIS
  \draw[->,thick] (0,-0.1*\ymax) -- (0,\ymax) node[anchor=north east] {$p$};
  \draw[->,thick] (-0.1*\xmax,0) -- (\xmax,0) node[anchor=north east] {$V$};
  
\end{tikzpicture}

\end{document}
```
Sappiamo che il lavoro delle forze di pressione lungo tale trasformazione è dato da:
$$
\mathcal{W}_{\Gamma_{AB}} = \int_{\Gamma_{AB}}pdV \tag{13}
$$
Allora, come possiamo notare anche dalla figura, $\mathcal{W}_{\Gamma_{AB}}$ corrisponde all'area sottesa alla curva $\Gamma_{AB}$ della trasformazione. 

### TRASFORMAZIONI TERMODINAMICHE
A questo punto, è bene classificare le trasformazioni termodinamiche.
- Tra di esse, le **quasi statiche** avvengono con sufficiente lentezza da far sì che ogni loro *stato intermedio* sia, in buona approssimazione, di equilibrio.
  - Tra queste, poi, alcune sono **reversibili**, cioè possono avvenire anche in verso opposto.

Sul piano di Clapeyron, le trasformazioni *reversibili* sono indicate con una linea *continua*, quelle irreversibili con una linea tratteggiata.
### CICLI TERMODINAMICI
Un ciclo è una *sequenza di trasformazioni* che riporta il sistema allo **stato iniziale**.

```tikz
\usepackage{tikz}
\usetikzlibrary{decorations.markings}
\tikzset{annotate/.style 2 args={postaction={decorate,decoration={markings,
mark=at position 0 with {\node[circle,inner sep=1.2pt,draw,fill=white,#1]{};},
mark=at position 0.52 with {\arrow[>=stealth,line width=1.5pt]{>};
\node at (0,0.4) {#2};}}}}}
\usepackage{amsmath}

\begin{document}

\begin{tikzpicture}
	% P-V Axis
	\draw[latex-latex, thick] (-0.5,5) node[below left]{$p$} |- (7,0) node[below left]{$V$};
	
	% Otto Cycle
	\begin{scope}[thick]
	\draw[annotate={label=below right:1,alias=1}{}] plot[variable=\x,domain=5:1] (\x,{5/(\x+3)});
	\draw[annotate={label=above left:3,alias=3}{}] plot[variable=\x,domain=1:5] (\x,{15/(\x+3)});
	\draw[annotate={label=below left:2,alias=2}{}] (1,5/4) -- (3);
	\draw[annotate={label=above right:4,alias=4}{}] (5,15/8) -- (1);  
	\end{scope} 
	
	\path (2) -- (3) coordinate[pos=0.5] (23) (1) -- (4) coordinate[pos=0.4] (14);
	
\end{tikzpicture}

\end{document}
```
Se *tutte* le trasformazioni sono *reversibili*, il ciclo si dice **reversibile**.
#### LAVORO DI UN CICLO
Il lavoro prodotto (o subito) durante un ciclo è dato dalla somma dei lavori delle singole trasformazioni.
Nel piano di Clapeyron è, graficamente, l'area racchiusa dal ciclo con segno:
- **Negativo** se il ciclo è percorso in verso *antiorario*.
- **Positivo** se il ciclo è percorso in verso *orario*.
## ESPERIMENTO DI JOULE ED ENERGIA INTERNA
Un importante esperimento sul calore fu eseguito da James Joule.
L' "apparecchiatura" utilizzata è riportata dalla figura di seguito:
![[joule_35657_md.gif| center |450]]

Si tratta di un contenitore pieno d'acqua, al cui interno è presente un mulinello azionato da un perno che viene messo in moto dalla caduta di due masse poste ai lati e attaccate a delle corde arrotolate attorno al perno stesso.

Il lavoro compiuto dalle due masse durante la caduta è:
$$
\mathcal{W}_{p} = 2mg\Delta h \tag{14}
$$
Misurando la temperatura dell'acqua prima della caduta delle masse e dopo che il perno avesse smesso di girare, Joule osservò che la temperatura dell'acqua era aumentata di $\Delta T$ e che tale variazione era proporzionale a $\mathcal{W}$.

Joule identificò allora l'**energia interna** $U$ e affermò che, nel caso di un *sistema adiabatico* come quello considerato (il contenitore era adiabatico), valeva:
$$
\Delta U = U_{B} - U_{A} = -\mathcal{W}_{AB} \tag{15}
$$
**NOTA:** in questo caso $\mathcal{W}_{AB}=-\mathcal{W}_{p}$ perchè il lavoro compiuto dalle masse è invece subito dall'acqua.

Più in generale, quindi anche per sistemi non adiabatici, si ha:
>[!info] PRIMO PRINCIPIO DELLA TERMODINAMICA
>$$\Delta U = Q_{AB} - \mathcal{W}_{AB}$$

Concludiamo quindi che **il calore è una forma di energia**.
Notiamo anche che $\Delta U$ dipende solo dagli stati in $A$ e $B$, per cui l'*energia interna* è una *funzione di stato*.

>[!info] FUNZIONE DI STATO
>E' una quantità/proprietà di un corpo che *dipende solo* dallo **stato** in cui si trova il corpo.

# ==I GAS==
Per il nostro studio, considereremo i cosiddetti **gas ideali**, caratterizzati dalle seguenti proprietà:
- *Non* sono *troppo freddi*.
- Sono *abbastanza rarefatti*.
- Obbediscono ad alcune *leggi sperimentali*.

**NOTA:** tali leggi valgono proprio alla luce delle prime due assunzioni.
## LEGGE DI BOYLE
Durante una trasformazione **isoterma** (a temperatura costante) *reversibile*, il prodotto $pV$ rimane costante.

```tikz
\usepackage{tikz}
\usepackage{amsmath} % for \text
\usetikzlibrary{arrows.meta} % to control arrow size
\tikzset{>={Latex[length=4,width=4]}} % for LaTeX arrow head
\usetikzlibrary{calc,decorations.markings,arrows.meta}
\usepackage{xcolor} % for colored text

\colorlet{mylightblue}{blue!20}
\colorlet{myblue}{blue!80!black}
\colorlet{mydarkblue}{blue!30!black}
\colorlet{mylightred}{red!10}
\colorlet{myred}{red!80!black}
\colorlet{mydarkred}{red!60!black}
\colorlet{mydarkgreen}{green!30!black}

%\tikzstyle{midarr}=[decoration={markings,mark=at position 0.5 with {\arrow{stealth}}},postaction={decorate}]
\tikzset{
  midarr/.style={decoration={markings,mark=at position #1 with {\arrow{stealth}}},postaction={decorate}},
  midarr/.default=0.5
}

\def\xtick#1#2{\draw[thick] (#1)++(0,.1) --++ (0,-.2) node[below=-.5pt,scale=0.9] {#2};}
\def\ytick#1#2{\draw[thick] (#1)++(.1,0) --++ (-.2,0) node[left=-.5pt,scale=0.9] {#2};}

\begin{document}

\def\N{40} % number of plot samples
\def\xmax{3}
\def\ymax{2.5}
\def\isotherm#1#2{{ #2/(#1) }}

\begin{tikzpicture}[scale=1.2]
  \def\Th{2.7}
  \def\Tc{.85}
  \coordinate (A) at ({0.30*\xmax},{\isotherm{0.30*\xmax}{\Tc}});
  \coordinate (B) at ({0.29*\xmax},{\isotherm{0.29*\xmax}{\Th}});
  \coordinate (C) at ({0.58*\xmax},{\isotherm{0.58*\xmax}{\Th}});
  \coordinate (D) at ({0.82*\xmax},{\isotherm{0.82*\xmax}{\Th}});
  
  % AXIS
  \draw[->,thick] (0,-0.1*\ymax) -- (0,\ymax+0.1)
    node[anchor=north east,inner sep=4,scale=1] {$p$};
  \draw[->,thick] (-0.1*\xmax,0) -- (\xmax+0.1,0)
    node[anchor=north east,inner sep=4,scale=1] {$V$};
  
  % ISOTHERMS
  \draw[mydarkblue,thick,
        domain={\isotherm{\ymax}{\Th}}:{.95*\xmax},samples=\N,smooth]
    plot (\x,\isotherm{\x}{\Th});
  \draw[mydarkblue,thick,
        domain={\isotherm{\ymax}{\Tc}}:{.95*\xmax},samples=\N,smooth]
    plot (\x,\isotherm{\x}{\Tc});
  
  % PATHS
  \draw[mydarkred,thick,midarr=.6] (A) to[out=50,in=-170] (C);
  \path (A) -- (C) node[mydarkred, right=15pt, above] {T cresce};
  
  % POINTS
  \node[mydarkblue,above=2,right=-5,scale=0.9]
    at (\xmax,{\isotherm{\xmax}{\Th}}) {$T_2$};
  \node[mydarkblue,above=1,right=-5,scale=0.9]
    at (\xmax,{\isotherm{\xmax}{\Tc}}) {$T_1$};
  \fill[mydarkblue]
     (A) circle(0.05)
     (C) circle(0.05);
  
\end{tikzpicture}

\end{document}
```
## LEGGE DI GAY-LUSSAC
Durante una trasformazione **isobara** (a pressione costante) *reversibile*, si osserva che vale:
$$
V = V_{0}\big( 1+\alpha(T-T_{0}) \big) \tag{1}
$$
Dove $V_{0}$ e $T_{0}$ sono volume e temperatura di riferimento, e $\alpha$ è una costante.

```tikz
\usepackage{tikz}
\usepackage{amsmath} % for \text
\usetikzlibrary{arrows.meta} % to control arrow size
\tikzset{>={Latex[length=4,width=4]}} % for LaTeX arrow head
\usetikzlibrary{calc,decorations.markings,arrows.meta}
\usepackage{xcolor} % for colored text

\colorlet{mylightblue}{blue!20}
\colorlet{myblue}{blue!80!black}
\colorlet{mydarkblue}{blue!30!black}
\colorlet{mylightred}{red!10}
\colorlet{myred}{red!80!black}
\colorlet{mydarkred}{red!60!black}
\colorlet{mydarkgreen}{green!30!black}

%\tikzstyle{midarr}=[decoration={markings,mark=at position 0.5 with {\arrow{stealth}}},postaction={decorate}]
\tikzset{
  midarr/.style={decoration={markings,mark=at position #1 with {\arrow{stealth}}},postaction={decorate}},
  midarr/.default=0.5
}

\def\xtick#1#2{\draw[thick] (#1)++(0,.1) --++ (0,-.2) node[below=-.5pt,scale=0.9] {#2};}
\def\ytick#1#2{\draw[thick] (#1)++(.1,0) --++ (-.2,0) node[left=-.5pt,scale=0.9] {#2};}

\begin{document}

\def\N{40} % number of plot samples
\def\xmax{3}
\def\ymax{2.5}
\def\isotherm#1#2{{ #2/(#1) }}

\begin{tikzpicture}
  
  % AREA
  \coordinate (A) at (.2*\xmax,.4*\ymax);
  \coordinate (B) at (.8*\xmax,.4*\ymax);
  
  % LINE
  \draw[very thick,midarr=.55,myred] (A) -- (B);
  \fill (A) circle(0.07) node[right=5,above=2] {$p_0$, $V_0$};
  \fill (B) circle(0.07) node[right=5,above=2] {$p_0$, $V$};
  
  % AXIS
  \draw[->,thick] (0,-0.1*\ymax) -- (0,\ymax) node[anchor=north east] {$p$};
  \draw[->,thick] (-0.1*\xmax,0) -- (\xmax,0) node[anchor=north east] {$V$};
  
\end{tikzpicture}

\end{document}
```

Chiaramente, $V$ deve essere positivo, per cui imponiamo che ==(1)== sia maggiore di zero e troviamo:
$$
T > -\frac{1}{\alpha} + T_{0} \tag{2}
$$
La quantità $-\frac{1}{\alpha}+T_{0}$ è lo **zero assoluto** ($0\text{ K}$) e quindi $T_{0}$ è la temperatura di riferimento della scala scelta.
Lavorando con la scala *celsius* si ha:
- $T_{0}=0°\text{C}$
- $\alpha=\frac{1}{273,15°\text{C}}$

**OSS:** A $T=0\text{ K}$ si avrebbe $V=0$ che non ha senso fisico. Questo è uno dei motivi per cui si fissa lo zero kelvin in questo punto.
## LEGGE ISOCORA
Durante una trasformazione **isocora** (a volume costante) *reversibile*, si osserva che vale:
$$
p = p_{0} \big( 1+\beta(T-T_{0}) \big) \tag{3}
$$
E si trova che $\alpha=\beta$ con un ragionamento analogo al precedente.

```tikz
\usepackage{tikz}
\usepackage{amsmath} % for \text
\usetikzlibrary{arrows.meta} % to control arrow size
\tikzset{>={Latex[length=4,width=4]}} % for LaTeX arrow head
\usetikzlibrary{calc,decorations.markings,arrows.meta}
\usepackage{xcolor} % for colored text

\colorlet{mylightblue}{blue!20}
\colorlet{myblue}{blue!80!black}
\colorlet{mydarkblue}{blue!30!black}
\colorlet{mylightred}{red!10}
\colorlet{myred}{red!80!black}
\colorlet{mydarkred}{red!60!black}
\colorlet{mydarkgreen}{green!30!black}

%\tikzstyle{midarr}=[decoration={markings,mark=at position 0.5 with {\arrow{stealth}}},postaction={decorate}]
\tikzset{
  midarr/.style={decoration={markings,mark=at position #1 with {\arrow{stealth}}},postaction={decorate}},
  midarr/.default=0.5
}

\def\xtick#1#2{\draw[thick] (#1)++(0,.1) --++ (0,-.2) node[below=-.5pt,scale=0.9] {#2};}
\def\ytick#1#2{\draw[thick] (#1)++(.1,0) --++ (-.2,0) node[left=-.5pt,scale=0.9] {#2};}

\begin{document}

\def\N{40} % number of plot samples
\def\xmax{3}
\def\ymax{2.5}
\def\isotherm#1#2{{ #2/(#1) }}

\begin{tikzpicture}
  
  % AREA
  \coordinate (B) at (.4*\xmax,.8*\ymax);
  \coordinate (C) at (.4*\xmax,.2*\ymax);
  
  % LINE
  \draw[very thick,midarr=.55,blue] (B) -- (C);
  \fill (B) circle(0.07) node[right=2] {$p_0$, $V_0$};
  \fill (C) circle(0.07) node[right=2] {$p$, $V_0$};
  
  % AXIS
  \draw[->,thick] (0,-0.1*\ymax) -- (0,\ymax) node[anchor=north east] {$p$};
  \draw[->,thick] (-0.1*\xmax,0) -- (\xmax,0) node[anchor=north east] {$V$};
  
\end{tikzpicture}

\end{document}
```

Se misuriamo la temperatura in Kelvin, $V(T)$ e $p(T)$ sono lineari (come in qualsiasi altra scala), ma si annullano proprio a $T=0\text{ K}$, per cui abbiamo:
- $V(T)=\alpha V_{0}T$
- $p(T)=\alpha p_{0}T$
## LEGGE DI AVOGADRO
Due gas ideali che occupano lo stesso volume $V$ e si trovano alla stessa temperatura $T$ e pressione $p$ consistono dello stesso numero di molecole.
Si introduce allora la **mole**:
$$
1\text{ mol} = N_{A} \text{ molecole} = 6,022\cdot 10^{23} \text{ molecole} \tag{4}
$$
Questo ci aiuta a fissare un riferimento standard:
- Scegliamo $T_{0}=0°\text{C}$.
- Scegliamo poi $p_{0}=p_{atm}=1,01\cdot 10^5\text{ Pa}$.
- A questo punto consideriamo una mole di gas a $T_{0}$ e $p_{0}$ scelti e misuriamo $V_{0}$, trovando $V_{mol}=22,4\text{ l} = 0,0224\text{ m}^3$, detto anche *volume molare*.
## EQUAZIONE DI STATO DEI GAS IDEALI
Nel capitolo precedente abbiamo introdotto il fatto che all'equilibrio esiste una relazione $f_{eq}$ per cui si ha:
$$
f_{eq}(p,V,T) = 0 \tag{5}
$$
Vogliamo trovare tale relazione per i *gas ideali* a partire dalle leggi presentate sopra.
Per farlo, consideriamo $n$ moli di un gas a $p=p_{0}$, $T=T_{0}$ e $V_{0}=nV_{mol}$.
Con una serie di trasformazioni, vogliamo portare il gas dallo stato $(p_{0},V_{0},T_{0})$ allo stato generico $(p_{f},V_{f},T_{f})$.

```tikz
\usepackage{tikz}
\usepackage{amsmath} % for \text
\usetikzlibrary{arrows.meta} % to control arrow size
\tikzset{>={Latex[length=4,width=4]}} % for LaTeX arrow head
\usetikzlibrary{calc,decorations.markings,arrows.meta}
\usepackage{xcolor} % for colored text

\colorlet{mylightblue}{blue!20}
\colorlet{myblue}{blue!80!black}
\colorlet{mydarkblue}{blue!30!black}
\colorlet{mylightred}{red!10}
\colorlet{myred}{red!80!black}
\colorlet{mydarkred}{red!60!black}
\colorlet{mydarkgreen}{green!30!black}

%\tikzstyle{midarr}=[decoration={markings,mark=at position 0.5 with {\arrow{stealth}}},postaction={decorate}]
\tikzset{
  midarr/.style={decoration={markings,mark=at position #1 with {\arrow{stealth}}},postaction={decorate}},
  midarr/.default=0.5
}

\def\xtick#1#2{\draw[thick] (#1)++(0,.1) --++ (0,-.2) node[below=-.5pt,scale=0.9] {#2};}
\def\ytick#1#2{\draw[thick] (#1)++(.1,0) --++ (-.2,0) node[left=-.5pt,scale=0.9] {#2};}

\begin{document}

\def\N{40} % number of plot samples
\def\xmax{3}
\def\ymax{2.5}
\def\isotherm#1#2{{ #2/(#1) }}
\def\Th{2.7}
\def\Tc{.85}

\begin{tikzpicture}
  
  % AXIS
  \draw[->,thick] (0,-0.1*\ymax) -- (0,1.2*\ymax+0.1)
    node[anchor=north east,inner sep=4,scale=1] {$p$};
  \draw[->,thick] (-0.1*\xmax,0) -- (\xmax+0.1,0)
    node[anchor=north east,inner sep=4,scale=1] {$V$};
  
  % ISOTHERMS
  \draw[gray, very thick, ->, 
        domain={\isotherm{\ymax}{\Th}}:{.95*\xmax},samples=\N,smooth]
    plot (\x,\isotherm{\x}{\Th}) node(C){};
  \node(A) at (\isotherm{\ymax}{\Th}, 0.2*\ymax){};
  \node(B) at (\isotherm{\ymax}{\Th}, \ymax){};

  \fill (A) circle(0.07) node[below=1] {$p_0$, $V_0$};
  \fill (B) circle(0.07) node[above=1] {$p_I$, $V_I$};
  \fill (C) circle(0.07) node[right=1] {$p_f$, $V_f$};

   \draw[very thick, -> , red] (A) -- (B);
\end{tikzpicture}

\end{document}
```

1. Con un'isocora reversibile raggiungiamo un punto intermedio $I$ che abbia la stessa temperatura del punto finale. Si avrà quindi:
   - $p_{I}=p_{0}\alpha T_{f}$
   - $V_{I}=V_{0}=nV_{mol}$
   - $T_{I}=T_{f}$
1. Con un'isoterma reversibile, raggiungiamo il punto finale, ricordando che vale
   $p_{f}V_{f}=p_{I}V_{I}=p_{0}\alpha T_{f}nV_{mol}$.

Troviamo allora, per uno stato generico $(p,V,T)$:
$$
pV = n (\alpha p_{0}V_{mol})T \tag{6}
$$
Definiamo la *costante dei gas ideali*:
$$
R:= \alpha p_{0}V_{mol} \tag{7}
$$
E concludiamo che la $f_{eq}$ che cercavamo è:
$$
f_{eq} = pV-nRT = 0 \implies pV = nRT \tag{8}
$$
Tale relazione è detta **equazione di stato dei gas ideali** e vale *all'equilibrio*.
## ENERGIA INTERNA DI UN GAS IDEALE
Consideriamo un gas in una porzione di un contenitore di volume $V$, a contatto con un fluido a temperatura $T$.
Immaginiamo che, aprendo una valvola, il gas sia libero di espandersi in tutto il contenitore, occupando un volume $V'=V+\Delta V$.
Se il sistema è isolato, si ha:
$$
\begin{matrix}
Q_{\text{gas}} + Q_{\text{fluido}} = 0  \\
\Delta U_{\text{gas}} + \Delta U_{\text{fluido}} = 0
\end{matrix} \tag{9}
$$
La prima relazione è ovvia per un sistema isolato.
Per quanto riguarda la seconda, basta notare che il fluido non compie/subisce alcun lavoro dato che il suo volume rimane costante; e anche il gas non compie/subisce lavoro in quanto si espande in uno spazio che prima era vuoto. Allora la seconda relazione segue dal primo principio della termodinamica.

Eseguendo tale esperimento, si osserva che la temperatura del fluido *non è variata*, per cui il fluido è nello stesso stato di partenza e si ha:
$$
\Delta U_{\text{fluido}} = 0 \tag{10}
$$
Ma allora deve essere anche:
$$
\Delta U_{\text{gas}} = 0 \tag{11}
$$
Tuttavia, in questo processo sono cambiati sia $V_{\text{gas}}$ che $p_{\text{gas}}$, per cui per un gas ideale $\Delta U$ *dipende* **solo da $T$**, essendo funzione di stato.
## CALORE SPECIFICO DEI GAS IDEALI
Abbiamo visto che il *calore specifico* ci dice come varia $T$ se forniamo calore $Q$ a una certa quantità di materia.
Per solidi e liquidi si usa il calore specifico *per unità di massa*.
Per i gas usiamo il calore specifico *per numero di moli*.

Tuttavia, è importante considerare il fatto che possiamo fornire calore ad un gas *a pressione costante* oppure a *volume costante*.
Definiamo allora:
$$
c_{V} := \frac{1}{n} \frac{dQ}{dT}\bigg|_{\text{V costante}} \tag{12}
$$
$$
c_{p} := \frac{1}{n} \frac{dQ}{dT}\bigg|_{\text{p costante}} \tag{13}
$$
>[!warning] NOTA
>Ci aspettiamo una differenza fra i due perchè a $p$ costante *parte del calore* è usato per far *espandere il gas* (compiendo lavoro $pdV$).
>Questa porzione di energia **non** contribuisce quindi a far *aumentare la temperatura* del gas.

### RELAZIONE DI MEYER
Sappiamo che:
$$
\frac{dU}{dT}\bigg|_{p \text{ cost}} = \frac{dU}{dT}\bigg|_{V \text{ cost}} \tag{14}
$$
Inoltre:
$$
dU = dQ - d\mathcal{W} \tag{15}
$$
Sappiamo anche:
$$
\frac{d\mathcal{W}}{dT}\bigg|_{V \text{ cost}} = 0 \ne \frac{d\mathcal{W}}{dT}\bigg|_{p \text{ cost}} \tag{16}
$$
E infine sappiamo che:
$$
pV = nRT \implies \frac{d}{dT}(pV) = nR \tag{17}
$$
A questo punto abbiamo tutto quello che ci serve per trovare una relazione fra $c_{p}$ e $c_{V}$.
Innanzitutto:
$$
nc_{V} = \frac{dQ}{dT}\bigg|_{V \text{ cost}} = \frac{dU+d\mathcal{W}}{dT}\bigg|_{V \text{ cost}}
$$
$$
= \frac{dU}{dT}\bigg|_{V \text{ cost}} + \cancelto{ 0 }{ \frac{d\mathcal{W}}{dT}\bigg|_{V \text{ cost}} } 
$$
Per cui troviamo:
$$
nc_{V} = \frac{dU}{dT}\bigg|_{V \text{cost}} \tag{18}
$$
Ma abbiamo visto che $\frac{dU}{dT}$ non dipende da $V$ o $p$, per cui è anche vero che:
$$
nc_{V} = \frac{dU}{dT}\bigg|_{p \text{ cost}}
$$
$$
=\frac{dQ - d\mathcal{W}}{dT}\bigg|_{p \text{ cost}} = \frac{dQ}{dT}\bigg|_{p \text{ cost}} - \frac{d\mathcal{W}}{dT}\bigg|_{p \text{ cost}}
$$
$$
= \frac{dQ}{dT}\bigg|_{p \text{ cost}} - p \frac{dV}{dT}\bigg|_{p \text{ cost}} = \frac{dQ}{dT}\bigg|_{p \text{ cost}} - \underbrace{ \frac{d(pV)}{dT}\bigg|_{p \text{ cost}} }_{ =nR }
$$
E concludiamo quindi:
$$
nc_{V} = nc_{P} - nR \tag{19}
$$
Da cui ricaviamo la:
>[!info] RELAZIONE DI MEYER
>$$c_{V} = c_{p} - R \tag{20}$$

Spesso è utile considerare il **rapporto di compressione** $\gamma$:
$$
\gamma := \frac{c_{p}}{c_{V}} \tag{21}
$$
E' un numero puro che si misura sperimentalmente.
Inoltre si ha:
- $\gamma=\frac{5}{3}$ per *gas monoatomici*.
- $\gamma=\frac{7}{5}$ per *gas biatomici*.
## TRASFORMAZIONI DEI GAS IDEALI
Vediamo le trasformazioni più importanti per i gas ideali, sia reversibili che non, considerando uno stato iniziale $A$ e uno stato finale $B$.
### ISOTERMA
Vale $dT=0$.
- Se **irreversibile** *non conosciamo* $p(V)$.
- Se **reversibile** vale l'equazione di stato dei gas, per cui troviamo $p=\frac{nRT}{V}$.

In ogni caso (anche se irreversibile) $U$ dipende solo da $T$, per cui:
$$
dT = 0 \implies dU = 0 \implies dQ = d\mathcal{W} \tag{22}
$$
E, se la trasformazione è reversibile, possiamo calcolare:
$$
Q_{AB} = \mathcal{W}_{AB} = \int_{V_{A}}^{V_{B}}pdV = \int_{V_{A}}^{V_{B}} \frac{nRT}{V}dV = nRT\ln\left( \frac{V_{B}}{V_{A}} \right) \tag{23}
$$
### ISOBARA
Vale $dp=0$, per cui sarà rappresentata da una linea orizzontale sul piano di Clapeyron, sia che essa sia reversibile che non.
In ogni caso vale:
$$
\mathcal{W}_{AB} = \int_{V_{A}}^{V_{B}}pdV = p \int_{V_{A}}^{V_{B}}dV = p(V_{B}-V_{A}) \tag{24}
$$
Per calcolare $\Delta U$:
$$
\Delta U = \int_{A}^{B}dU = \int_{T_{A}}^{T_{B}} nc_{V}dT \tag{25}
$$
Se la trasformazione è irreversibile, non sappiamo come varia $T$, per cui possiamo concludere solo:
$$
\Delta U = nc_{V}(T_{B}-T_{A}) \tag{26}
$$
Per cui se non conosciamo le temperature in $A$ e $B$, o come varia $T$ da $A$ a $B$, non possiamo calcolare $\Delta U$.
Se invece è reversibile, possiamo anche trovare $T(V)$ e quindi $dT$:
$$
T = \frac{pV}{nR} \implies dT = \frac{pdV}{nR} \tag{27} 
$$
E allora possiamo calcolare $\Delta U$ anche nel seguente modo:
$$
\Delta U = nc_{V}\int_{V_{A}}^{V_{B}} \frac{p}{nR}dV = \frac{c_{V}p}{R}(V_{B}-V_{A}) \tag{28} 
$$
### ISOCORA
Vale $dV=0$  (e quindi $d\mathcal{W}=0$) e allora sarà rappresentata sul piano di Clapeyron con una linea verticale sul piano sia che essa sia reversibile che non.
Inoltre, per il primo principio, si ha:
$$
\Delta U = Q_{AB} \tag{28}
$$
Per cui segue anche:
$$
\frac{dU}{dT} = \frac{dQ}{dT}\bigg|_{V \text{ cost}} = nc_{V} \tag{29}
$$
E troviamo quindi
$$
\Delta U = Q_{AB} = nc_{V}(T_{B}-T_{A}) \tag{30}
$$
Analogamente al caso precedente, se la trasformazione è irreversibile non possiamo sapere come varia $T$ in funzione di $p$.
Se reversibile, invece, troviamo:
$$
T = \frac{Vp}{nR} \implies dT = \frac{V}{nR}dp \tag{31}
$$
Il che ci permette di calcolare $\Delta U$ e $Q_{AB}$.
### ADIABATICA
Vale $dQ=0$, e quindi, per il primo principio, si ha:
$$
dU = - d\mathcal{W} \implies \Delta U = -\mathcal{W}_{AB} \tag{32}
$$
E allora:
$$
\mathcal{W}_{AB} = -\Delta U = -nc_{V}(T_{B}-T_{A}) \tag{33}
$$
In questo caso variano sia $p$, che $V$, che $T$; per cui *non conosciamo* $p(V)$:
$$
nc_{V}dT = dU = -d\mathcal{W} = -pdV \tag{34}
$$
Infatti da questa relazione *non* è possibile ricavare $p,V$ o $T$ in funzione di una delle altre due.
Se la trasformazione è reversibile, tuttavia, sappiamo che $p=\frac{nRT}{V}$ e allora troviamo:
$$
nc_{V}dT = -\frac{nRT}{V}dV \implies \frac{dT}{T} = -\frac{R}{c_{V}} \frac{dV}{V} \tag{35}
$$
Per cui, integrando ambo i membri, troviamo:
$$
\ln\left( \frac{T_{B}}{T_{A}} \right) = -\frac{R}{c_{V}} \ln\left( \frac{V_{B}}{V_{A}} \right)
$$
Sostituendo $R=c_{P}-c_{V}$ si arriva a:
$$
\ln\left( \frac{T_{B}}{T_{A}} \right) = -(\gamma-1)\ln\left( \frac{V_{B}}{V_{A}} \right)
$$
Da cui ricaviamo:
$$
\frac{T_{B}}{T_{A}} = \left( \frac{V_{B}}{V_{A}} \right)^{-(\gamma-1)} \tag{36}
$$
E concludiamo che, lungo un'*adiabatica reversibile*, vale:
$$
TV^{\gamma-1} = \text{ cost} \tag{37}
$$
E quindi anche:
$$
pV^\gamma = \text{ cost} \tag{38}
$$
# ==MACCHINE TERMICHE==

>[!info] MACCHINA TERMICA
>E' un oggetto che compie un *ciclo termodinamico* **estraendo calore** da una *sorgente calda*, grazie al quale **produce lavoro**, **cedendo** tuttavia **calore** ad una *sorgente fredda*.

```tikz
\usepackage{amssymb,amsmath}
\usepackage{tikz}
\usetikzlibrary{calc,patterns,decorations.pathmorphing}
\tikzset{>=latex}

\colorlet{mydarkblue}{blue!50!black}
\colorlet{myblue}{blue!30}
\colorlet{mydarkred}{red!60!black}
\colorlet{myred}{red!30}
\colorlet{mydarkgreen}{green!60!black}
\colorlet{mygreen}{green!30}
\colorlet{mydarkorange}{yellow!40!red}
\colorlet{myorange}{yellow!80!red}
\colorlet{myyellow}{yellow!80}
\colorlet{mygrey}{black!15}
\colorlet{mydarkgrey}{black!50}

\tikzstyle{bath}=[draw=blue!40!black,top color=blue!10,
                                  bottom color=blue!20,shading angle=30,thick,rounded corners=1]
\tikzstyle{source}=[draw=red!50!black,top color=red!20,
                                   bottom color=red!30,shading angle=30,thick,rounded corners=1]
\tikzstyle{conductor}=[draw=black!40,top color=black!8,
                                  bottom color=black!20,shading angle=30,thick,rounded corners=1]
\tikzstyle{insulator}=[draw=yellow!40!red!80,top color=yellow!90!red!80,
                                          bottom color=yellow!80!red!80,shading angle=10,thick,rounded corners=1]
\tikzstyle{C1}=[draw=black!80!red!70,top color=black!40!red!10,
                                  bottom color=black!80!red!20,shading angle=30,thick]
\tikzstyle{C2}=[draw=black!90!green!70,top color=black!50!green!8,
                                    bottom color=black!90!green!20,shading angle=30,thick]
\tikzstyle{C3}=[draw=black!80!yellow!80,top color=black!20!yellow!10,
                                     bottom color=black!80!yellow!20,shading angle=30,thick]

\begin{document}

\begin{tikzpicture}[scale=1.7]
  \def\H{.8}
  \def\W{3.2}
  \def\L{1.6}
  \def\dl{0.0185}
  \coordinate (H) at (0, \L/2);
  \coordinate (C) at (0,-\L/2);
  
  \draw[bath]
    (C)++(-.4*\W,0) rectangle ++(\W,-\H) node[midway,align=center] {Sorgente fredda, $T_1$};
  
  \draw[mydarkblue,top color=red!22,bottom color=blue!10]
    (H) ++ (-.15*\W,0) -- (-.15*\W,.2-\L/2) --++ (-.2,0) --
    (0,-.2-\L/2) -- (.2+.15*\W,.2-\L/2) --++ (-.2,0) -- % large arrow: cold reservoir
    (.15*\W,.1*\L) to[out=-90,in=180] (.5*\W,-.2*\L) --++
    (0,-.15) --++ (.25,.3) coordinate (W) --++ (-.25,.3) --++ (0,-.15) % small arrow: work
    to[out=180,in=-90] (.5+.1*\W,.2+.05*\L) -- (.5+.1*\W,\L/2);
  
  \draw[source]
    (H)++(-.4*\W,0) rectangle ++(\W,\H) node[midway,align=center] {Sorgente calda, $T_2$};
  
  \node[red,right=4,below=1] at (H) {$Q_A$};
  \node[mydarkgreen,right=0] at (W) {$\mathcal{W}$};
  \node[blue,above=1] at (C) {$|Q_C|$};
  
\end{tikzpicture}

\end{document}
```

La figura schematizza il funzionamento di una macchina termica che lavora con due sorgenti a $T_{1}$ e $T_{2}$:
- $Q_{A}$ è il calore assorbito dalla macchina.
- $\mathcal{W}$ è il lavoro prodotto da un ciclo.
- $|Q_{C}|$ è il calore ceduto alla sorgente fredda ($Q_{C}$ è negativo).

## RENDIMENTO
Siccome le macchine termiche lavorano con cicli termodinamici, per uno di questi cicli si ha:
$$
\Delta U = Q_{A} -|Q_{C}| - \mathcal{W} = 0 \tag{1}
$$
Per cui troviamo:
$$
\mathcal{W} = Q_{A} -|Q_{C}| \tag{2}
$$
Come si può intuire anche dalla figura.
Idealmente, vorremmo che il *rapporto fra $\mathcal{W}$ e $Q_{A}$ sia maggiore possibile*: vogliamo cioè convertire la più alta % possibile di $Q_{A}$ in lavoro, *riducendo* al minimo il *calore ceduto* alla sorgente fredda, che è invece *sprecato*.
Definiamo allora il **rendimento** $\eta$ di una macchina termica:
$$
\eta = \frac{\mathcal{W}}{Q_{A}} = \frac{Q_{A}-|Q_{C}|}{Q_{A}} = 1- \frac{|Q_{C}|}{Q_{A}} \tag{3}
$$
E, chiaramente, vale sempre:
$$
\eta \in \left[0,1\right] \tag{4}
$$
## CICLO DI STIRLING
Consideriamo una macchina termica che sfrutta il ciclo di Stirling, rappresentato di seguito:

```tikz
\usepackage{tikz}
\usepackage{amsmath} % for \text
\usetikzlibrary{arrows.meta} % to control arrow size
\tikzset{>={Latex[length=4,width=4]}} % for LaTeX arrow head
\usetikzlibrary{calc,decorations.markings,arrows.meta}
\usepackage{xcolor} % for colored text

\colorlet{mylightblue}{blue!20}
\colorlet{myblue}{blue!80!black}
\colorlet{mydarkblue}{blue!30!black}
\colorlet{mylightred}{red!10}
\colorlet{myred}{red!80!black}
\colorlet{mydarkred}{red!60!black}
\colorlet{mydarkgreen}{green!30!black}

\tikzset{
  midarr/.style={decoration={markings,mark=at position #1 with {\arrow{stealth}}},postaction={decorate}},
  midarr/.default=0.5
}

\def\xtick#1#2{\draw[thick] (#1)++(0,.1) --++ (0,-.2) node[below=-.5pt,scale=0.9] {#2};}
\def\ytick#1#2{\draw[thick] (#1)++(.1,0) --++ (-.2,0) node[left=-.5pt,scale=0.9] {#2};}
\def\N{40} % number of plot samples
\def\xmax{3}
\def\ymax{2.5}

\begin{document}

\begin{tikzpicture}[scale=1.3]
  \def\Ch{2.5}
  \def\Cc{0.9}
  \def\N{40}
  \def\gam{1.0}
  \def\isotherm#1#2{{ #2/(#1) }}
  \def\adiabatic#1#2{{ #2/(#1)^(\gam) }}
  \def\xA{.24*\xmax}
  \def\xB{.90*\xmax}
  
  \coordinate (A) at ({\xA},{\isotherm{\xA}{\Ch}});
  \coordinate (B) at ({\xB},{\isotherm{\xB}{\Ch}});
  \coordinate (C) at ({\xB},{\isotherm{\xB}{\Cc}});
  \coordinate (D) at ({\xA},{\isotherm{\xA}{\Cc}});

  \node at (A) [left]{$A$};
  \node at (B) [right]{$B$};
  \node at (C) [right]{$C$};
  \node at (D) [left]{$D$};
  
  % WORK
  \fill[mylightblue,domain={\xA:\xB},samples=\N]
    plot (\x,\adiabatic{\x}{\Ch}) --
    plot[domain={\xB:\xA}](\x,\adiabatic{\x}{\Cc}) -- cycle;
  \node[below=-5,blue,scale=.9] at ($(D)!.4!(B)$) {$W$};
  
  % ADIABATIC TRANSFORMATION
  \draw[myred,thick,midarr=.18,midarr=.75,domain={\xA:\xB},samples=\N]
    (D) -- (A)
    plot (\x,\adiabatic{\x}{\Ch});
  \draw[blue,thick,midarr=.1,midarr=.62,domain={\xB:\xA},samples=\N]
    (B) -- plot(\x,\adiabatic{\x}{\Cc});
  
  % POINTS
  \fill[mydarkblue]
     (A) circle(0.05)
     (B) circle(0.05)
     (C) circle(0.05)
     (D) circle(0.05);
  
  % AXIS
  \draw[->,thick] (0,-0.1*\ymax) -- (0,\ymax+0.1)
    node[anchor=north east,inner sep=4,scale=1] {$P$};
  \draw[->,thick] (-0.1*\xmax,0) -- (\xmax+0.1,0)
    node[anchor=north east,inner sep=4,scale=1] {$V$};
  
\end{tikzpicture}

\end{document}
```

Il ciclo consiste di 2 isoterme e 2 isocore:
- $AB$ *espansione* **isoterma** reversibile.
- $BC$ *raffreddamento* **isocoro** reversibile.
- $CD$ *compressione* **isoterma** reversibile.
- $DA$ *riscaldamento* **isocoro**.

Abbiamo:
- $T_{A}=T_{B}=T_{2}$
- $T_{C}=T_{D}=T_{1}$
- $V_{B}=V_{C}=V_{2}$
- $V_{D}=V_{A}=V_{1}$

Vogliamo determinare il rendimento $\eta$.
Calcoliamo allora $\mathcal{W}$ e $Q_{A}$.
### TRASFORMAZIONE AB
La trasformazione è isoterma, per cui $\mathcal{W}_{AB}=Q_{AB}$ e, in particolare, abbiamo:
$$
\mathcal{W}_{AB} + Q_{AB} = nRT_{2}\ln\left( \frac{V_{2}}{V_{1}} \right) > 0 \tag{5}
$$
Allora il gas assorbe calore e compie lavoro.
### TRASFORMAZIONE BC
La trasformazione è isocora, per cui $\mathcal{W}_{BC}=0$ e $\Delta U=Q_{BC}$.
Quindi abbiamo:
$$
Q_{BC} = nc_{V}(T_{1}-T_{2}) < 0 \tag{6}
$$
Allora il gas cede calore e non compie lavoro.
### TRASFORMAZIONE CD
La trasformazione è isoterma, per cui $\mathcal{W}_{CD}=Q_{CD}$ e abbiamo:
$$
\mathcal{W}_{CD} = Q_{CD} = nRT_{1}\ln\left( \frac{V_{1}}{V_{2}} \right) < 0 \tag{7}
$$
Allora il gas cede calore e subisce lavoro.
### TRASFORMAZIONE DA
La trasformazione è isocora, per cui $\mathcal{W}_{DA}=0$ e $\Delta U=Q_{DA}$
Perciò abbiamo:
$$
Q_{DA} = nc_{V}(T_{2}-T_{1}) > 0 \tag{8}
$$
### RENDIMENTO DEL CICLO
Per cui il lavoro totale è:
$$
\mathcal{W} = \mathcal{W}_{AB} + \mathcal{W}_{CD} = nR(T_{2}-T_{1})\ln\left( \frac{V_{2}}{V_{1}} \right) \tag{9}
$$
E il calore assorbito è:
$$
Q_{A} = Q_{AB} + Q_{DA} = nRT_{2}\ln\left( \frac{V_{2}}{V_{1}} \right) + nc_{V}(T_{2}-T_{1}) \tag{10}
$$
Calcoliamo allora il rendimento del ciclo e troviamo:
$$
\eta = \frac{\mathcal{W}}{Q_{A}} = \frac{R\ln\left( \frac{V_{2}}{V_{1}} \right)(T_{2}-T_{1})}{RT_{2}\ln\left( \frac{V_{2}}{V_{1}} \right) + c_{V}(T_{2}-T_{1})} \tag{11}
$$
Osserviamo a questo punto che $Q_{DA}=-Q_{BC}$.
Se potessimo riutilizzare il calore ceduto in $BC$ per alimentare il ciclo, non avremmo bisogno di fornire $Q_{DA}$.
Allora sarebbe $Q_{A}=Q_{AB}$, e in tal caso troveremmo:
$$
\eta_{rev} = \frac{T_{2}-T_{1}}{T_{2}} = 1 - \frac{T_{1}}{T_{2}} \tag{12}
$$
Che è maggiore di quanto trovato prima, ma come si realizza un ciclo di questo tipo?
La soluzione consiste nell'implementare un **rigeneratore**, ovvero lavorare con delle *sorgenti intermedie* fra $T_{1}$ e $T_{2}$.
In questo modo, invece che raffreddare il gas da $T_{2}$ a $T_{1}$, lo raffredderemmo da $T_{2}$ a $T_{i,1}$, poi $T_{i,2}$ e così via fino a $T_{i,N}$.
Nella trasformazione $DA$, invece, riutilizzeremmo le stesse sorgenti in ordine inverso da $T_{1}$ a $T_{2}$ potendo così riutilizzare il calore ceduto in $BC$ invece che lasciare che si disperda.

>[!warning] NOTA
>Utilizzando queste sorgenti intermedie, stiamo sostanzialmente scomponendo le due isocore in tante trasformazioni tra stati più "vicini" gli uni agli altri; cioè stiamo rallentando le trasformazioni in modo da renderle **quasi statiche** e quanto più vicine possibile a delle trasformazioni *reversibili*.
## CICLO DI CARNOT
Studiamo ora una macchina che lavora con il ciclo di Carnot, rappresentato di seguito:

```tikz
\usepackage{tikz}
\usepackage{amsmath} % for \text
\usetikzlibrary{arrows.meta} % to control arrow size
\tikzset{>={Latex[length=4,width=4]}} % for LaTeX arrow head
\usetikzlibrary{calc,decorations.markings,arrows.meta}
\usepackage{xcolor} % for colored text

\colorlet{mylightblue}{blue!20}
\colorlet{myblue}{blue!80!black}
\colorlet{mydarkblue}{blue!30!black}
\colorlet{mylightred}{red!10}
\colorlet{myred}{red!80!black}
\colorlet{mydarkred}{red!60!black}
\colorlet{mydarkgreen}{green!30!black}

%\tikzstyle{midarr}=[decoration={markings,mark=at position 0.5 with {\arrow{stealth}}},postaction={decorate}]
\tikzset{
  midarr/.style={decoration={markings,mark=at position #1 with {\arrow{stealth}}},postaction={decorate}},
  midarr/.default=0.5
}

\def\xtick#1#2{\draw[thick] (#1)++(0,.1) --++ (0,-.2) node[below=-.5pt,scale=0.9] {#2};}
\def\ytick#1#2{\draw[thick] (#1)++(.1,0) --++ (-.2,0) node[left=-.5pt,scale=0.9] {#2};}
\def\N{40} % number of plot samples
\def\xmax{3}
\def\ymax{2.5}

\begin{document}

\begin{tikzpicture}
  \def\Th{1.20}
  \def\Tc{0.45}
  \def\Ch{0.4}
  \def\Cc{1.9}
  \def\N{40}
  \def\gam{2.2}
  \def\isotherm#1#2{{ #2/(#1) }}
  \def\adiabatic#1#2{{ #2/(#1)^(\gam) }}
  \def\xA{ (\Th/\Ch)^(1/(1-\gam)) }
  \def\xB{ (\Th/\Cc)^(1/(1-\gam)) }
  \def\xC{ (\Tc/\Cc)^(1/(1-\gam)) }
  \def\xD{ (\Tc/\Ch)^(1/(1-\gam)) }
  \coordinate (A) at ({\xA},{\isotherm{\xA}{\Th}});
  \coordinate (B) at ({\xB},{\isotherm{\xB}{\Th}});
  \coordinate (C) at ({\xC},{\isotherm{\xC}{\Tc}});
  \coordinate (D) at ({\xD},{\isotherm{\xD}{\Tc}});
  
  %\clip (-0.1*\xmax,-0.12*\ymax) rectangle (1.05*\xmax,1.1*\ymax);
  
  % WORK
  \fill[mylightblue,samples=\N]
    plot[domain={\xA:\xB}] (\x,\isotherm{\x}{\Th}) --
    plot[domain={\xB:\xC}] (\x,\adiabatic{\x}{\Cc}) --
    plot[domain={\xC:\xD}] (\x,\isotherm{\x}{\Tc}) --
    plot[domain={\xD:\xA}] (\x,\adiabatic{\x}{\Ch});
 \node[blue,scale=.9] at ($(B)!.5!(D)$) {$W$};
  
  % ADIABATIC & ISOTHERMIC TRANSFORMATIONS
  \draw[gray,thick,midarr=.60,domain={\xA:\xB},samples=\N]
    plot (\x,\isotherm{\x}{\Th}); % hot
  \draw[blue,thick,midarr=.45,domain={\xB:\xC},samples=\N]
    plot (\x,\adiabatic{\x}{\Cc}); % cold
  \draw[gray,thick,midarr=.65,domain={\xC:\xD},samples=\N]
    plot (\x,\isotherm{\x}{\Tc}); % cold
  \draw[myred,thick,midarr=.40,domain={\xD:\xA},samples=\N]
    plot(\x,\adiabatic{\x}{\Ch}); % hot
  
  % POINTS
  \fill[mydarkblue]
     (A) circle(0.05) node[above=1,scale=.8] {$A$}
     (B) circle(0.05) node[above right,scale=.8] {$B$}
     (C) circle(0.05) node[above=1,scale=.8] {$C$}
     (D) circle(0.05) node[below left,scale=.8] {$D$};
  
  % AXIS
  \draw[->,thick] (0,-0.1*\ymax) -- (0,\ymax+0.1)
    node[anchor=north east,inner sep=4,scale=1] {$P$};
  \draw[->,thick] (-0.1*\xmax,0) -- (\xmax+0.1,0)
    node[anchor=north east,inner sep=4,scale=1] {$V$};
  
\end{tikzpicture}

\end{document}
```

Il ciclo è così composto:
- $AB$ *espansione* **isoterma**.
- $BC$ *espansione* **adiabatica**.
- $CD$ *compressione* **isoterma**.
- $DA$ *compressione* **adiabatica**.

Supponiamo che tutte e quattro le trasformazioni siano reversibili e calcoliamo il rendimento $\eta$ a partire dai seguenti dati:
- $T_{A}=T_{B}=T_{2}$
- $T_{C}=T_{D}=T_{1}$
### TRASFORMAZIONE AB
E' una trasformazione isoterma, per cui $Q_{AB}=\mathcal{W}_{AB}$.
Allora:
$$
Q_{AB} = \mathcal{W}_{AB} = nRT_{2}\ln\left( \frac{V_{B}}{V_{A}} \right) > 0 \tag{13}
$$
### TRASFORMAZIONE BC
E' una trasformazione adiabatica, per cui $Q_{BC}=0$ e $\Delta U=-\mathcal{W}_{BC}$.
Allora:
$$
\mathcal{W}_{BC} = nc_{V}(T_{2}-T_{1}) > 0 \tag{14}
$$
### TRASFORMAZIONE CD
E' una trasformazione isoterma, per cui $Q_{CD}=\mathcal{W}_{CD}$.
Allora:
$$
Q_{CD} = \mathcal{W}_{CD} = nRT_{1}\ln\left( \frac{V_{D}}{V_{C}} \right) < 0 \tag{15}
$$
### TRASFORMAZIONE DA
E' una trasformazione adiabatica, per cui $Q_{DA}=0$ e $\Delta U=\mathcal{W}_{DA}$.
Allora:
$$
\mathcal{W}_{DA} = nc_{V}(T_{1}-T_{2}) < 0 \tag{16}
$$
### RENDIMENTO DEL CICLO
Per calcolare il rendimento, osserviamo che i volumi *non sono indipendenti*: sono infatti legati dalle seguenti relazioni
$$
T_{B}V_{B}^{\gamma-1} = T_{C}V_{C}^{\gamma-1} \implies T_{2}V_{B}^{\gamma-1} = T_{1}V_{C}^{\gamma-1} \tag{17}
$$
$$
T_{D}V_{D}^{\gamma-1} = T_{A}V_{A}^{\gamma-1} \implies T_{1}V_{D}^{\gamma-1} = T_{2}V_{A}^{\gamma-1} \tag{18}
$$
Troviamo allora che vale:
$$
\frac{V_{A}}{V_{B}} = \frac{V_{D}}{V_{C}} \tag{19}
$$
Per cui abbiamo:
$$
Q_{A} = Q_{AB} \tag{20}
$$
E:
$$
\mathcal{W} = \mathcal{W}_{AB} + \mathcal{W}_{CD} + \cancel{ \mathcal{W}_{BC} + \mathcal{W}_{DA} }
$$
$$
= nRT_{2}\ln\left( \frac{V_{B}}{V_{A}} \right) + nRT_{1}\ln\left( \frac{V_{A}}{V_{B}} \right) = nR(T_{2}-T_{1})\ln\left( \frac{V_{B}}{V_{A}} \right) \tag{21}
$$
Per cui possiamo calcolare il rendimento del ciclo:
$$
\eta = \frac{\mathcal{W}}{Q_{A}} = \frac{T_{2}-T_{1}}{T_{2}} = 1- \frac{T_{1}}{T_{2}} \tag{22}
$$
E notiamo che il rendimento del ciclo di *Carnot* e quello del ciclo di *Stirling* (nell'ipotesi che entrambi siano reversibili) sono *uguali*.

Possiamo quindi intuire che esiste un limite al rendimento massimo di un ciclo; prima di dimostrarlo introduciamo però altre informazioni.
## MACCHINE FRIGORIFERE
>[!info] MACCHINA FRIGORIFERA
>Una macchina frigorifera *assorbe* calore $Q_{A}>0$ da una sorgente *fredda* e **cede calore** $Q_{C}$ ad una **sorgente calda**. Chiaramente, trattandosi di un processo *non spontaneo*, una macchina frigorifera *necessita di lavoro* fornito dall'esterno anzichè produrlo come una macchina termica.

Di seguito lo schema di funzionamento di una macchina frigorifera:

```tikz
\usepackage{amssymb,amsmath}
\usepackage{tikz}
\usetikzlibrary{calc,patterns,decorations.pathmorphing}
\tikzset{>=latex}

\colorlet{mydarkblue}{blue!50!black}
\colorlet{myblue}{blue!30}
\colorlet{mydarkred}{red!60!black}
\colorlet{myred}{red!30}
\colorlet{mydarkgreen}{green!60!black}
\colorlet{mygreen}{green!30}
\colorlet{mydarkorange}{yellow!40!red}
\colorlet{myorange}{yellow!80!red}
\colorlet{myyellow}{yellow!80}
\colorlet{mygrey}{black!15}
\colorlet{mydarkgrey}{black!50}

\tikzstyle{bath}=[draw=blue!40!black,top color=blue!10,
                                  bottom color=blue!20,shading angle=30,thick,rounded corners=1]
\tikzstyle{source}=[draw=red!50!black,top color=red!20,
                                   bottom color=red!30,shading angle=30,thick,rounded corners=1]
\tikzstyle{conductor}=[draw=black!40,top color=black!8,
                                  bottom color=black!20,shading angle=30,thick,rounded corners=1]
\tikzstyle{insulator}=[draw=yellow!40!red!80,top color=yellow!90!red!80,
                                          bottom color=yellow!80!red!80,shading angle=10,thick,rounded corners=1]
\tikzstyle{C1}=[draw=black!80!red!70,top color=black!40!red!10,
                                  bottom color=black!80!red!20,shading angle=30,thick]
\tikzstyle{C2}=[draw=black!90!green!70,top color=black!50!green!8,
                                    bottom color=black!90!green!20,shading angle=30,thick]
\tikzstyle{C3}=[draw=black!80!yellow!80,top color=black!20!yellow!10,
                                     bottom color=black!80!yellow!20,shading angle=30,thick]

\begin{document}

\begin{tikzpicture}[scale=1.7]
  \def\H{.8}
  \def\W{3.2}
  \def\L{1.6}
  \def\dl{0.0185}
  \coordinate (H) at (0, \L/2);
  \coordinate (C) at (0,-\L/2);
  
  \draw[source]
    (H)++(-.4*\W,0) rectangle ++(\W,\H) node[midway,align=center] {Sorgente calda, $T_2$};
  
  \draw[mydarkblue,top color=red!22,bottom color=blue!10]
    (C) ++ (-.2*\W,0) -- (-.2*\W,-.2+\L/2) --++ (-.2,0) --
    (0,.2+\L/2) -- (.2+.2*\W,-.2+\L/2) --++ (-.2,0) -- % large arrow: cold reservoir
    (.2*\W,.1*\L) to[out=-90,in=180] (.45*\W,-.1*\L) --++
    (-0.25,-0.15) coordinate (W) --++ (0.25,-0.15) % small arrow: work
    to[out=180,in=-90] (.2*\W-0.3,.05*\L) -- (.2*\W-0.3,-\L/2);

  \draw[bath]
    (C)++(-.4*\W,0) rectangle ++(\W,-\H) node[midway,align=center] {Sorgente fredda, $T_1$};
  
  \node[red,right=2,below=-1] at (H) {$Q_C$};
  \node[mydarkgreen,right=7] at (W) {$\mathcal{W}$};
  \node[blue,left=4,above=1] at (C) {$Q_A$};
  
\end{tikzpicture}

\end{document}
```

Per una macchina termica si definisce l'**efficienza** $\xi$, analoga del rendimento:
$$
\xi = \frac{Q_{A}}{\mathcal{W}} \tag{23}
$$
L'efficienza ci dice quanto calore riusciamo ad assorbire dalla sorgente calda in rapporto al lavoro fornito.

I cicli di Stirling e di Carnot termici sono eseguiti in verso opposto rispetto alla loro controparte termica. Allora, usando i risultati trovati in precedenza, abbiamo:
$$
\xi_{C,S} = \frac{T_{1}}{T_{2}-T_{1}} \tag{24}
$$
**OSS:** l'efficienza è tanto più alta quanto più simili sono $T_{2}$ e $T_{1}$.
## SECONDO PRINCIPIO DELLA TERMODINAMICA

>[!info] Enunciato di CLAUSIUS
>E' **impossibile** realizzare un *processo termodinamico* il cui **solo risultato** sia il *trasferimento di calore* da **un corpo caldo verso uno freddo**.

>[!info] Enunciato di KELVIN - PLANCK
>E' **impossibile** realizzare un *processo termodinamico* il cui **solo risultato** sia la *produzione di lavoro* da **una sola sorgente** a temperatura costante.

E i due enunciati sono equivalenti.
### DIMOSTRAZIONE
>[!warning]
>Vogliamo dimostrare $KP \Leftrightarrow C$. 
>Per farlo, dimostriamo $KP\text{ falso} \Leftrightarrow C\text{ falso}$.
#### KP FALSO $\implies$ C FALSO
Assumiamo che $KP$ sia falso.
Allora esiste una macchina termica $M$ che assorbe calore $Q_{1}$ da una sorgente a temperatura $T_{1}$, producendo lavoro $\mathcal{W}$.
Colleghiamo allora $M$ ad una macchina frigorifera $F$ che assorbe il lavoro $\mathcal{W}$ prodotto da $M$, calore $Q_{A}$ dalla stessa sorgente $T_{1}$ e cede calore $Q_{C}$ ad una sorgente a temperatura $T_{2}$.
Il risultato complessivo è una macchina $M'$ ($M+F$) che trasferisce calore dalla sorgente $T_{1}$ a $T_{2}$, cioè viola l'enunciato di Clausius.
#### C FALSO $\implies$ KP FALSO
Assumiamo che $C$ sia falso.
Allora esiste una macchina $M$ che assorbe calore $Q_{A}$ da una sorgente a temperatura $T_{1}$ e cede calore $Q_{C}=-Q_{A}$ ad una sorgente a temperatura più alta $T_{2}$, senza utilizzare lavoro esterno.
Consideriamo ora una macchina termica $M'$ che lavora fra le stesse temperature e colleghiamola ad $M$.
Il risultato complessivo è una macchina $M+M'$ che produce lavoro attingendo solo da $T_{2}$ (perchè tutto il calore ceduto da $M'$ alla sorgente $T_{1}$ viene reimmesso in $T_{2}$ dalla macchina $M$), cioè viola l'enunciato di Kelvin-Planck.
## TEOREMA DI CARNOT
Sia $C$ una *macchina di Carnot* **reversibile** e $X$ un'altra macchina *non necessariamente reversibile*.
Allora abbiamo:
$$
\begin{matrix}
\mathcal{W}_{C} = \eta_{C}Q_{A,C} \\
\mathcal{W}_{X} = \eta_{X}Q_{A,X} 
\end{matrix} \tag{25}
$$
Siccome $C$ è reversibile, possiamo compiere lo stesso ciclo in verso opposto e trasformarla in una macchina frigorifera. Chiamiamo tale macchina $CF$.

Ora colleghiamo $CF$ e $X$, in modo che parte del lavoro prodotto da $X$ alimenti $CF$ e assumendo $|Q_{A,X}|=|Q_{A,C}|$.
Allora lo scambio netto con $T_{2}$ è nullo, infatti il calore assorbito da $X$ viene poi reimmesso da $CF$.

Il risultato complessivo è una macchina $M$ che ha uno scambio di calore netto con la sorgente $T_{1}$ dato dalla somma del calore assorbito da $CF$ e quello ceduto da $X$, pari a $Q_{A,M}=-Q_{C,C}+Q_{C,X}$.
Per cui $M$ assorbe calore dalla sorgente $T_{1}$ e produce lavoro $\mathcal{W}=\mathcal{W}_{X}-\mathcal{W}_{C}$.

Ma per il secondo principio, tale macchina *non può esistere*, per cui deve essere:
$$
\mathcal{W}_{X}-\mathcal{W}_{C} \le 0 \tag{26}
$$
Cioè il lavoro deve essere assorbito o comunque nullo.
Troviamo allora:
$$
\frac{\mathcal{W}_{X}}{Q_{A,X}} - \frac{\mathcal{W}_{C}}{Q_{A,X}} \le 0 \tag{27}
$$
Da cui:
$$
\eta_{X} - \eta_{C} \le 0 \implies \eta_{X} \le \eta_{C} = 1-\frac{T_{1}}{T_{2}} \tag{28}
$$
Supponendo $X$ *reversibile* potremmo ripetere lo stesso ragionamento con la sua versione frigorifera $XF$ e $C$, trovando in quel caso:
$$
\eta_{C} \le \eta_{X} \tag{29}
$$
Per cui concludiamo che il rendimento di una macchina reversibile che opera tra due sorgenti a temperature $T_{1}$ e $T_{2}$ deve valere:
$$
\eta_{rev} = 1-\frac{T_{1}}{T_{2}} \tag{30}
$$
E per una macchina *irreversibile* $X$ deve essere:
$$
\eta_{X} < \eta_{C} \tag{31}
$$
>[!warning] NOTA
>Come avevamo intuito in seguito allo studio del ciclo di Carnot, esiste effettivamente un limite al rendimento massimo di una macchina termica, che viene eguagliato solo nel caso in cui la macchina sia reversibile.
# ==ENTROPIA==
Abbiamo visto che, per una generica macchina $X$ e una macchina $C$ operante il ciclo di Carnot fra le stesse sorgenti, vale:
$$
\eta_{X} = 1-\frac{|Q_{C}|}{Q_{A}} \le 1-\frac{T_{1}}{T_{2}} = \eta_{C} \tag{1}
$$
Da cui ricaviamo:
$$
\frac{Q_{C}}{Q_{A}} \le -\frac{T_{1}}{T_{2}} \implies \frac{Q_{C}}{T_{1}} + \frac{Q_{A}}{T_{2}} \le 0 \tag{2}
$$
Con l'uguaglianza che vale se la macchina è reversibile.
## TEOREMA DI CLAUSIUS
Vogliamo generalizzare ==(2)== lungo un ciclo completamente generico, che potenzialmente opera con più di due sorgenti.
Per farlo, dato un ciclo qualsiasi, lo *approssimiamo con $N$ cicli di Carnot*, come in figura:

![[entropy-27-00346-g004-550.jpg | center |300]]

Durante le trasformazioni adiabatiche non vi è scambio di calore e, inoltre, il lavoro prodotto da due adiabatiche appartenenti allo stesso ciclo di Carnot è uguale ed opposto (Vedi > [[#CICLO DI CARNOT]]).
Per cui è come se stessimo *approssimando il ciclo con $N$ isoterme* fra le temperature $T_{1}$ e $T_{2}$, $T_{3}$ e $T_{4}$,... fino a $T_{2N-1}$ e $T_{2N}$.

Per ognuno degli $N$ cicli possiamo quindi usare la relazione ==(2)==, ottenendo:
$$
\frac{Q_{2j+1}}{T_{2j+1}} + \frac{Q_{2j+2}}{T_{2j+2}} \le 0 \tag{3} 
$$
Con $j=0,1,\dots,N-1$.
Sommando ciascuna di queste disequazioni troviamo:
$$
\sum_{k=1}^{2N} \frac{Q_{k}}{T_{k}} \le 0 \tag{4}
$$
Per $N\to \infty$ l'approssimazione diventa sempre più fedele al ciclo di partenza e la somma discreta in ==(4)== passa all'integrale e segue quindi:

>[!info] TEOREMA DI CLAUSIUS
>Lungo un generico ciclo termodinamico vale la seguente disequazione:
>$$\int_{\Gamma_{\text{ciclo}}} \frac{dQ}{T} \leq 0 \tag{5}$$
>Dove l'uguaglianza vale se il ciclo è reversibile.
## ENTROPIA
Consideriamo due trasformazioni termodinamiche fra gli stati $A$ e $B$:
- $\Gamma_{AB,rev}$ reversibile.
- $\Gamma'_{AB,rev}$ reversibile.

Possiamo invertire una delle due e comporle per ottenere un ciclo, per esempio
$$
\Gamma_{\text{ciclo}} = \Gamma_{AB,rev} - \Gamma'_{AB,rev} \tag{6}
$$
Allora, per il teorema di Clausius, troviamo:
$$
0 = \int_{\Gamma_{\text{ciclo}}} \frac{dQ}{T} = \int_{\Gamma_{AB,rev}} \frac{dQ}{T} - \int_{\Gamma'_{AB,irr}} \frac{dQ}{T}
$$
E quindi:
$$
\int_{\Gamma_{AB,rev}} \frac{dQ}{T} = \int_{\Gamma'_{AB,rev}} \frac{dQ}{T} \tag{7}
$$
Per cui tale quantità *non dipende* da $\Gamma$, ma solo dagli stati iniziale e finale.
Introduciamo quindi la *funzione di stato* **entropia**:
$$
\Delta S = \int_{\Gamma_{rev}} \frac{dQ}{T} \tag{8}
$$
>[!warning] NOTA
>1. Si misura la variazione di entropia, non l'entropia di un singolo stato.
>2. La variazione di entropia tra due stati $A$ e $B$ si calcola lungo un processo reversibile, per definizione.
## PRINCIPIO DI AUMENTO DELL'ENTROPIA
Consideriamo ancora due trasformazioni termodinamiche tra gli stati $A$ e $B$, di cui una irreversibile:
- $\Gamma_{AB,rev}$ *reversibile*.
- $\Gamma_{AB,irr}$ *irreversibile*.

Ancora una volta, componiamole per ottenere un ciclo:
$$
\Gamma_{\text{ciclo}} = -\Gamma_{AB,rev} + \Gamma_{AB,irr} \tag{9}
$$
Per il teorema di Clausius, abbiamo:
$$
0 > \int_{\Gamma_{\text{ciclo}}} \frac{dQ}{T} = \int_{\Gamma_{AB,irr}} \frac{dQ}{T} - \int_{\Gamma_{AB,rev}} \frac{dQ}{T} = \int_{\Gamma_{AB,irr}} \frac{dQ}{T} - \Delta S_{AB} \tag{10} 
$$
Per cui segue:
$$
\Delta S_{AB} > \int_{\Gamma_{AB,irr}} \frac{dQ}{T} \tag{11} 
$$
Cioè la variazione di entropia fra gli stati $A$ e $B$ è maggiore della quantità $\frac{dQ}{T}$ integrata lungo una *qualsiasi trasformazione irreversibile* tra $A$ e $B$.

Poniamoci ora in un **sistema isolato**. Siccome non vi è scambio di calore con l'esterno, $dQ=0$ durante qualsiasi processo riguardante il sistema.
Allora ==(11)== diventa:
$$
\Delta S_{AB} > 0 \tag{12}
$$
Cioè il *processo irreversibile* ha causato un *aumento dell'entropia del sistema*.
Se anzichè considerare una trasformazione irreversibile ne avessimo considerata una reversibile, in ==(10)== avremmo trovato l'uguaglianza e concluso:
$$
\Delta S_{AB} = 0 \tag{13}
$$
Possiamo quindi enunciare il seguente principio:

>[!info] PRINCIPIO DI AUMENTO DELL'ENTROPIA
>In un **sistema isolato**, a seguito di una trasformazione termodinamica del sistema, si ha:
>$$\Delta S \ge 0 \tag{14}$$
>Con l'uguaglianza che vale nel caso in cui la trasformazione sia reversibile.

A questo punto vale la pena fare una considerazione sulle trasformazioni adiabatiche.
Sia $\Gamma_{AB}$ un'adiabatica.
Se fosse reversibile, avremmo:
$$
\Delta S_{AB} = \int_{\Gamma_{AB,rev}} \frac{\cancelto{ 0 }{ dQ }}{T} = 0 \tag{15}
$$
Se invece fosse irreversibile, dovremmo trovare una trasformazione reversibile lungo cui calcolare l'integrale. Tuttavia, per il principio di aumento dell'entropia avremmo in questo caso:
$$
\Delta S_{AB} > 0 \tag{16}
$$
Ma l'entropia è una *funzione di stato*, per cui da ==(15)== e ==(16)== segue che i due stati $A$ e $B$ sono collegati **o** da un'adiabatica *reversibile*, o da un'adiabatica *irreversibile*, ma non da entrambe.
Questo ci fa capire che, fissato uno stato di partenza $A$, determinati stati "destinazione" sono **irraggiungibili con determinate trasformazioni**.
## ENTROPIA E LAVORO SPRECATO
Consideriamo due serbatoi termici alle temperature $T_{1}$ e $T_{2}$ ($T_{2}>T_{1}$) che scambiano calore $Q$.
Calcoliamo $\Delta S$ per ciascuno dei due:
$$
\Delta S^{(1)} = \int_{\Gamma_{rev}} \frac{dQ}{T} = \int_{\text{isot. rev}} \frac{dQ}{T} = \frac{Q}{T_{1}} > 0 \tag{17}
$$
Dove abbiamo scelto di calcolare $\Delta S$ lungo un'*isoterma* perchè trattiamo i serbatoi come *serbatoi ideali*, ovvero con una capacità termica infinita, tale da non farne variare la temperatura.
Similmente, troviamo:
$$
\Delta S^{(2)} = - \frac{Q}{T_{2}} < 0 \tag{18}
$$
A questo punto, la variazione totale di entropia del sistema dopo tale processo irreversibile è:
$$
\Delta S^{(irr)} = \Delta S^{(1)} + \Delta S^{(2)} = Q\left( \frac{T_{2}-T_{1}}{T_{1}T_{2}} \right) > 0 \tag{19}
$$
Immaginiamo ora di mediare lo scambio di calore fra i serbatoi con una macchina termica reversibile di rendimento $\eta_{C}$, che assorbe calore $Q$ dal serbatoio a temperatura $T_{2}$, producendo così lavoro $\mathcal{W}$ e cedendo una parte $Q_{C}$ del calore assorbito al serbatoio a temperatura $T_{1}$. Abbiamo quindi:
$$
\mathcal{W} = \eta_{C}Q = \frac{T_{2}-T_{1}}{T_{2}}Q \tag{20}
$$
Nel caso precedente avevamo trovato ==(19)==, in cui possiamo sostituire ==(20)==, trovando allora:
$$
\Delta S^{(irr)} = \frac{\mathcal{W}}{T_{2}}
$$
Questo ci dice che la variazione di entropia è legata al *lavoro sprecato* che avremmo potuto ottenere dal processo termodinamico se avessimo sfruttato il calore scambiato per alimentare una macchina di Carnot.
## VARIAZIONE DI ENTROPIA IN UN GAS IDEALE
In generale, si ha:
$$
dS = \frac{dQ}{T} = \frac{dU}{T} + \frac{d\mathcal{W}}{T} \tag{21}
$$
Dove la seconda eguaglianza è data dal primo principio.
Sappiamo che per un gas ideale vale $dT=nc_{V}dT$.
Inoltre l'entropia si calcola lungo una trasformazione reversibile, lungo la quale vale quindi $pV=nRT$.
Allora riscriviamo ==(21)==:
$$
dS = \frac{nc_{V}dT}{T} + \frac{nRdV}{V} \tag{22}
$$
Per cui troviamo che la variazione di entropia tra due stati $A$ e $B$ per un gas ideale è data da:
$$
\Delta S_{AB} = \int_{A}^B \left( \frac{nc_{V}dT}{T} + \frac{nRdV}{V} \right) = \int_{T_{A}}^{T_{B}} \frac{nc_{V}}{T}dT + \int_{V_{A}}^{V_{B}} \frac{nR}{V}dV \tag{23} 
$$
E quindi:
$$
\Delta S = nc_{V}\ln\left( \frac{T_{B}}{T_{A}} \right) + nR\ln\left( \frac{V_{B}}{V_{A}} \right) \tag{24}
$$
A questo punto, ricordiamo che, valendo l'equazione di stato, si ha $\frac{T_{A}}{T_{B}} = \frac{p_{B}V_{B}}{p_{A}V_{A}}$.
Per cui troviamo:
$$
\Delta S = nc_{V}\ln\left( \frac{p_{B}V_{B}}{p_{A}V_{A}} \right) + nR\ln\left( \frac{V_{B}}{V_{A}} \right) = nc_{V}\ln\left( \frac{p_{B}}{p_{A}} \right) + nc_{p}\ln\left( \frac{V_{B}}{V_{A}} \right) \tag{25}
$$
$$
\Delta S = nc_{V}\left(  \ln\left( \frac{p_{B}}{p_{A}} \right) + \frac{c_{p}}{c_{V}}\ln\left( \frac{V_{B}}{V_{A}} \right) \right) = nc_{V}\ln\left( \frac{p_{B}V_{B}^\gamma}{p_{A}V_{A}^\gamma} \right) \tag{26}
$$
Ma sappiamo che:
$$
\frac{p_{B}V_{B}^\gamma}{p_{A}V_{A}^\gamma} = 1 \Longleftrightarrow \text{ AB adiabatica reversibile} \tag{27}
$$
Per cui concludiamo che:
$$
\Delta S_{AB}^{\text{gas}} = 0 \Longleftrightarrow \text{A e B collegati da un'adiabatica rev.} \tag{28}
$$
>[!warning] NOTA
>Questo vale per il gas. La *differenza* tra una trasformazione *reversibile* e una *irreversibile* è nella variazione di entropia del **sistema**, che è data dalla somma dei $\Delta S$ per il gas e per l'ambiente.
>Se la trasformazione è reversibile, si avrà $\Delta S^{\text{gas}}+\Delta S^{\text{amb}}=0$, se irreversibile tale quantità sarà invece positiva.
## PIANO (T,S)
Spesso può essere utile studiare le trasformazioni anche nel piano $(T,S)$ oltre che nel piano $(p,V)$.
Dato un ciclo $\Gamma_{\text{ciclo}}$ nel piano $(T,S)$ abbiamo:
$$
\int_{\Gamma_{\text{ciclo}}} TdS = \int_{\Gamma_{\text{ciclo}}} T \frac{dQ}{T} = \int_{\Gamma_{\text{ciclo}}}dQ = Q_{\text{ciclo}} \tag{29}
$$
In maniera analoga all'area racchiusa da un ciclo sul piano $(p,V)$ che corrispondeva al lavoro totale prodotto/subito dal ciclo.

Per un gas ideale:
- Lungo un'**isoterma** vale $dT=0$, per cui la trasformazione è rappresentata da una linea *retta orizzontale*.
- Lungo un'**adiabatica** *reversibile* vale $dQ=0$ (quindi $dS=0$) e la trasformazione è rappresentata da una linea *retta verticale*.
# ==TEORIA CINETICA DEI GAS==
Vogliamo dare un'**interpretazione microscopica** a $p,T,U,S$.
## PRESSIONE
La pressione è data dagli *urti* delle particelle di gas contro le *pareti del contenitore* e, in figura, contro il *pistone*.

```tikz
\usepackage{tikz}
\usetikzlibrary{patterns,decorations.pathmorphing}
\usetikzlibrary{arrows.meta}
\tikzset{>=latex}

\colorlet{mydarkblue}{blue!50!black}
\colorlet{myblue}{blue!30}
\colorlet{mydarkred}{red!60!black}
\colorlet{myred}{red!30}
\colorlet{mydarkgreen}{green!60!black}
\colorlet{mygreen}{green!30}
\colorlet{mydarkorange}{yellow!40!red}
\colorlet{myorange}{yellow!80!red}
\colorlet{myyellow}{yellow!80}
\colorlet{mygrey}{black!15}
\colorlet{mydarkgrey}{black!50}

\tikzstyle{piston}=[blue!40!black,top color=blue!40!black!30,bottom color=blue!40!black!30,
                    middle color=blue!50!black!15,shading angle=0]
\tikzstyle{walldark}=[blue!25!black,top color=blue!20!black!12,bottom color=blue!20!black!20,shading angle=-30]
\tikzstyle{wall}=[blue!20!black,top color=blue!35!black!6,bottom color=blue!25!black!12,shading angle=30]

% GAS MOLECULE with vector
\tikzset{
  gasparticle/.pic={
    \tikzset{/gasparticle/.cd,#1}
    \draw[-{Latex[length=4,width=3]},green!60!black,thick] (0,0) -- (\vec);
    \node[circle,fill,inner sep=1.5,ball color=black!80!blue] at (0,0) {};
  }
  /gasparticle/.search also={/tikz},
  /gasparticle/.cd,
  vec/.store in=\vec, vec={90:0.5},
}

\begin{document}


% PISTON
\begin{tikzpicture}
  \def\Ra{0.5}
  \def\Rb{1.0}
  \def\ra{0.1}
  \def\rb{0.2}
  \def\w{0.08} % wall thickness
  \def\x{2.9}  % piston position
  \def\L{3.7}  % container length
  \def\l{2.2}  % piston arm length
  \def\v{0.68} % velocity
  
  % WALL
  \draw[wall]
    (0,\Rb) -- (0,-\Rb) --++ (\L,0) arc (-90:90:{\Ra} and {\Rb}) -- cycle;
  \draw[walldark] (0,0) ellipse ({\Ra} and \Rb);
  
  % SHELL
  \draw[walldark]
    (0,\Rb) rectangle++ (\L,\w);
  \draw[walldark]
    (0,-\Rb) rectangle++ (\L,-\w);
  \draw[walldark]
    (\L,\Rb+\w) arc (90:-90:{\Ra+\w} and {\Rb+\w}) --++ (0,\w) arc (-90:90:{\Ra} and {\Rb}) -- cycle;
  \draw[walldark]
    (0,\Rb) arc (90:270:{\Ra} and {\Rb}) --++ (0,-\w) arc (-90:-270:{\Ra+\w} and {\Rb+\w}) -- cycle;
  
  % PISTON
  \draw[walldark]
    (\x,\Rb) arc (90:270:{\Ra} and {\Rb}) --++ (-2*\w,0) arc (-90:-270:{\Ra} and {\Rb}) -- cycle;
  \draw[piston] (\x,0) ellipse ({\Ra} and \Rb);
  \draw[piston]
    (\x,\rb) arc (90:270:{\ra} and {\rb}) --++ (\l,0) --++ (0,2*\rb) -- cycle;
  \draw[walldark] (\x+\l,0) ellipse ({\ra} and \rb);
  
  % LABELS
  \draw[->,very thick,orange!90!black] (\x,0.5*\Rb) --++ (0.2*\L,0)
    node[right=-2,orange!90!black] {$P$};
  
  % GAS PARTICLE
  \pic at (-0.12*\x, 0.2*\Rb) {gasparticle={vec={ -40:0.7*\v}}};
  \pic at (-0.07*\x,-0.5*\Rb) {gasparticle={vec={  48:0.6*\v}}};
  \pic at ( 0.00*\x, 0.3*\Rb) {gasparticle={vec={ 105:0.6*\v}}};
  \pic at ( 0.05*\x,-0.5*\Rb) {gasparticle={vec={-100:0.6*\v}}};
  \pic at ( 0.08*\x, 0.0*\Rb) {gasparticle={vec={  70:0.5*\v}}};
  \pic at ( 0.07*\x, 0.7*\Rb) {gasparticle={vec={ -10:0.9*\v}}};
  \pic at ( 0.15*\x,-0.2*\Rb) {gasparticle={vec={  30:0.7*\v}}};
  \pic at ( 0.20*\x,-0.8*\Rb) {gasparticle={vec={ -10:0.6*\v}}};
  \pic at ( 0.35*\x, 0.6*\Rb) {gasparticle={vec={-110:0.7*\v}}};
  \pic at ( 0.35*\x,-0.6*\Rb) {gasparticle={vec={ 140:0.4*\v}}};
  \pic at ( 0.40*\x, 0.9*\Rb) {gasparticle={vec={ -40:0.7*\v}}};
  \pic at ( 0.43*\x,-0.2*\Rb) {gasparticle={vec={  75:0.8*\v}}};
  \pic at ( 0.50*\x, 0.5*\Rb) {gasparticle={vec={-170:0.5*\v}}};
  \pic at ( 0.52*\x,-0.7*\Rb) {gasparticle={vec={ 120:0.6*\v}}};
  \pic at ( 0.60*\x, 0.4*\Rb) {gasparticle={vec={ -80:0.5*\v}}};
  \pic at ( 0.63*\x,-0.6*\Rb) {gasparticle={vec={  42:0.5*\v}}};
  \pic at ( 0.65*\x,-0.2*\Rb) {gasparticle={vec={ 150:0.6*\v}}};
  \pic at ( 0.68*\x,-0.8*\Rb) {gasparticle={vec={ 190:0.5*\v}}};
  \pic at ( 0.72*\x, 0.8*\Rb) {gasparticle={vec={ 160:0.5*\v}}};
  \pic at ( 0.72*\x, 0.3*\Rb) {gasparticle={vec={  80:0.6*\v}}};
  
\end{tikzpicture}

\end{document}
```

Consideriamo l'urto di una *singola particella* contro il pistone, assumendo che sia elastico.
Allora abbiamo le velocità $\vec{v}$ e $\vec{v}'$, rispettivamente prima e dopo l'urto:
$$
\begin{matrix}
\vec{v} = (v_{x},v_{y},v_{z}) \\
\vec{v}' = (-v_{x},v_{y},v_{z})
\end{matrix} \tag{1}
$$
Per cui l'*impulso assorbito* dal pistone è:
$$
\vec{\mathcal{I}} = \Delta \vec{p} = m\Delta \vec{v} = -2mv_{x}\vec{u}_{x} \tag{2} 
$$
Se l'intervallo medio fra un urto ed il successivo è $\Delta t$, la forza media esercitata *da una particella sul pistone* è data da:
$$
\|\vec{F}_{x}\| = \frac{\|\Delta \vec{p}\|}{\Delta t}
$$
Ma $\Delta t =\frac{2\Delta x}{v_{x}}$, per cui troviamo:
$$
\|\vec{F}_{x}\| = \frac{mv_{x}^2}{\Delta x} \tag{3}
$$
Per trovare la pressione sul pistone, dividiamo il risultato ottenuto per la superficie della parete:
$$
p = \frac{mv^2_{x}}{\Delta x} \frac{1}{\Delta y\Delta z} = \frac{mv_{x}^2}{V} \tag{4}
$$
Consideriamo ora $n$ moli di molecole del gas, ovvero $N=nN_{A}$ particelle.
La pressione totale sarà data dal contributo di ciascuna di esse:
$$
p_{tot} = \sum_{j=1}^N \frac{mv_{x,j}^2}{V} = \frac{mN}{V} \sum_{j=1}^N \frac{v_{x,j}^2}{N} = \frac{mN}{V} \overline{v_{x}^2} \tag{5}
$$
Dove $\overline{v_{x}^2}$ è la media dei $v_{x,j}^2$.
A questo punto, per semplificare il risultato notiamo che:
$$
\overline{E_{K}} = \sum_{j=1}^N \frac{\frac{1}{2}m\|\vec{v}_{j}^2\|}{N} =\frac{1}{2}m \sum_{j=1}^N \frac{v_{x,j}^2 + v_{y,j}^2 + v_{z,j}^2 }{N} \tag{6}
$$
Siccome *non esiste una direzione privilegiata*, ci aspettiamo che la velocità abbia la **stessa distribuzione** lungo le tre direzioni (**isotropia**), ovvero:
$$
\sum_{j=1}^N \frac{v_{x,j}^2}{N} = 
\sum_{j=1}^N \frac{v_{y,j}^2}{N} = 
\sum_{j=1}^N \frac{v_{z,j}^2}{N} \tag{7}
$$
Allora troviamo:
$$
\overline{E_{K}} = \frac{1}{2}m\left( 3\sum_{j=1}^N \frac{v_{x,j}^2}{N} \right) = \frac{3}{2}m \overline{v_{x}^2} \tag{8}
$$
Per cui:
$$
\overline{v_{x}^2} = \frac{2}{3} \frac{\overline{E_{K}}}{m} \tag{9}
$$
E, sostituendo ==(9)== in ==(5)==, concludiamo che:
$$
p_{tot} = \frac{2N}{3V}\overline{E_{K}} \tag{10}
$$
La pressione è quindi legata all'*energia cinetica media per unità di volume*.
## TEMPERATURA
Consideriamo un gas all'equilibrio, per cui vale quindi l'equazione di stato. Sostituendo la relazione ricavata per $p_{tot}$ ==(10)==, troviamo:
$$
\underbrace{ p_{tot}V = \frac{2N}{3}\overline{E_{K}} }_{ (10) } = nRT \tag{11}
$$
Ricordando che $N=nN_{A}$:
$$
\frac{2}{3}N_{A}\overline{E_{K}} = RT \tag{12}
$$
Introduciamo a questo punto la **costante di Boltzmann** $k_{B}$:
$$
k_{B} = \frac{R}{N_{A}} \tag{13}
$$
>[!warning] NOTA
>$R$ era una costante *macroscopica*, mentre $k_{B}$ è una costante *microscopica* che descrive singole particelle.

A questo punto troviamo:
$$
T = \frac{2}{3} \frac{\overline{E_{K}}}{k_{B}} \tag{14}
$$
Cioè anche la temperatura è legata all'energia cinetica media delle particelle che compongono il gas.
Considerando invece la relazione inversa:
$$
\overline{E_{K}} = \frac{3}{2}k_{B}T \tag{15}
$$
Notiamo che l'energia cinetica media del gas (e quindi la sua energia interna) dipende esclusivamente dalla temperatura:
$$
U = \sum_{j=1}^N E_{K,j} = N\overline{E_{K}} = \frac{3}{2}Nk_{B}T \tag{16}
$$
## GRADI DI LIBERTA'
Ricordiamo la definizione di $c_{V}$:
$$
c_{V} = \frac{1}{n} \frac{dU}{dT}\bigg|_{V \text{ cost}} \tag{17}
$$
Ma allora, derivando ==(16)==, otteniamo:
$$
c_{V} = \frac{1}{n} \frac{3}{2}Nk_{B} = \frac{3}{2\cancel{ n }}\cancel{ n }\cancel{ N_{A} } \frac{R}{\cancel{ N_{A} }} = \frac{3}{2}R \tag{18}
$$
Ma $c_{V}=\frac{3}{2}R$ valeva per **gas monoatomici**, *non in generale*.

Per cui nel derivare ==(16)== dobbiamo aver assunto, ad un certo punto, che il gas fosse monoatomico.

L'assunzione è stata fatta in ==(8)==, nel calcolo dell'energia cinetica media: abbiamo infatti considerato solo l'*energia cinetica di traslazione* e *ignorato qualsiasi forma di rotazione*, che è proprio il caso di un gas monoatomico.

Se il gas fosse stato biatomico potrebbe anche compiere un moto rotatorio che risulterebbe combinazione di rotazioni attorno a due assi di riferimento, come nella figura seguente:

```tikz
\usetikzlibrary{quotes,arrows,arrows.meta}
\tikzset{>=latex} % for LaTeX arrow head

\colorlet{myblue}{blue!20}
\colorlet{mydarkblue}{blue!50!black}
\colorlet{myred}{black!50!red}

\begin{document}

% DIATOMIC
\begin{tikzpicture}[z={(-0.6,-0.6)}, scale=4]

  %Gas axis
  \draw[densely dashed] (-0.5,0,0) -- (1,0,0);

  %Planar rotation
  \draw[->,>=stealth',shorten >=-3pt,green!50!black,xscale=0.8]
    (0.4,-0.05,0.5) arc[radius=0.16,start angle=-40,end angle=-300];
  
  %Horizontal axis
  \draw[->,thick] (0.25,0,-0.5) -- (0.25,0,0.75) node[at end, below]{$\vec{u}_1$};
  %Vertical axis
  \draw[->,thick] (0.25,-0.5,0) -- (0.25,0.5,0) node[at end, above]{$\vec{u}_2$};
  
  %Gas
  \node[circle,fill,inner sep=3,fill=red!80!black] at (0,0,0) {};
  \node[circle,fill,inner sep=1.07,fill=red!60!black] at (0.04,0,0) {};
  \draw[red!60!black,line width=3] (0.04,0,0) -- (0.5,0,0);
  \node[circle,fill,inner sep=3,fill=red!80!black] at (0.5,0,0) {};

  %Vertical rotation
  \draw[->,>=stealth',shorten >=-3pt,green!50!black,yscale=0.8]
    (0.13,0.65,0) arc[radius=0.16,start angle=-220,end angle=10];
  
\end{tikzpicture}

\end{document}
```

>[!info] GRADI DI LIBERTA'
>I gradi di libertà si riferiscono al numero di variabili indipendenti necessarie per descrivere completamente lo stato di un corpo. In altre parole, indicano quante coordinate o parametri sono necessari per definire la posizione e il movimento del corpo.

- Un gas *monoatomico* ha quindi **tre gradi di libertà**, perchè può muoversi liberamente lungo le *tre direzioni dello spazio* (e assumiamo che non ruoti perchè possiamo approssimare le sue particelle con un punto materiale).
- Un gas *biatomico*, invece, ha **cinque gradi di libertà**: tre di traslazione e due di rotazione.

E' possibile inoltre dimostrare che vale il seguente teorema:
>[!info] T. EQUIPARTIZIONE ENERGIA
>L'energia è equamente distribuita fra i vari *gradi di libertà* di di un sistema.

Nel caso di un gas *biatomico*, per esempio, si ha:
$$
\frac{1}{2}m\overline{v_{x}^2} = \frac{1}{2}m\overline{v_{y}^2} = \frac{1}{2}m\overline{v_{z}^2} = \frac{1}{2}I_{1}\overline{\omega_{1}^2} = \frac{1}{2}I_{2}\overline{\omega_{2}^2} \tag{19}
$$
In generale, per un gas con $g$ gradi di libertà, troviamo:
$$
\overline{E_{K}} = \frac{1}{2}gm\overline{v_{x}^2} \tag{20}
$$
Da cui si ricava:
$$
c_{V} = \frac{1}{2}gR \tag{21}
$$
$$
c_{p} = c_{V} + R = \frac{g+2}{2}R \tag{22}
$$
E dal loro rapporto si trova che effettivamente:
- $\gamma=\frac{5}{3}$ per gas monoatomici ($g=3$).
- $\gamma=\frac{7}{5}$ per gas biatomici ($g=5$).
## ENTROPIA
Spesso l'entropia viene associata al "grado di disordine" di un sistema, ma questa è una semplificazione. 
In realtà, l'entropia è una *misura del numero di stati microscopici* (ovvero delle configurazioni delle particelle) che risultano *accessibili* ad uno specifico stato macroscopico.
L'equazione di Boltzmann descrive tale relazione:
$$
S = k_{B}\ln W \tag{23} 
$$
Dove $W$ è il numero di stati accessibili al sistema, cioè il numero di configurazioni in cui esso può trovarsi.
## TERZO PRINCIPIO DELLA TERMODINAMICA
Sappiamo che:
$$
dS = \frac{dQ}{T} \tag{24}
$$
Se $T\to 0$ ci aspetteremmo quindi che l'entropia aumenti notevolmente.
Contrariamente a ciò, in realtà, si ha:
>[!info] TERZO PRINCIPIO DELLA TERMODINAMICA
>$$S\to 0 \text{ per } T\to 0 \tag{25}$$
>

Che è consistente con ==(23)==.
A temperature basse, infatti, *diminuisce il numero di stati accessibili al sistema*, per cui $W$ diminuisce e così anche l'entropia.
Questo ci dice inoltre che $dQ\to 0$ *più velocemente* di quanto non lo faccia $T$, cioè a *temperature particolarmente basse* risulta notevolmente difficile scambiare calore.