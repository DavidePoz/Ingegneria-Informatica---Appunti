# INDICE SEZIONE
- [ ] [[#ESEMPIO IN TEMPO CONTINUO]]
	    - [[#PROBLEMA DIRETTO]]
	    - [[#PROBLEMA INVERSO]]
- [ ] [[#ESEMPIO IN TEMPO DISCRETO]]
	    - [[#PROBLEMA DIRETTO]]
	    - [[#PROBLEMA INVERSO]]   
- [ ] [[#PROBLEMA DIRETTO VS INVERSO]]
# ESEMPIO IN TEMPO CONTINUO
Consideriamo un serbatoio di base $S$, che viene *riempito con un liquido* attraverso un tubo, regolato da una *valvola* la cui apertura è data da $0\leq a(t)\leq 1$.

```tikz
\usepackage{amsmath}
\usetikzlibrary{shapes.geometric, positioning, calc, arrows.meta}

\definecolor{water}{RGB}{173,216,230} 

\begin{document}

\begin{tikzpicture}[
    >={Stealth[scale=1.2]}, 
    line width=0.8pt, 
    dim_arrow/.style={<->, thick}, 
    label_font/.style={font=\normalsize} 
]

    % Dimensioni del serbatoio
    \def\tankw{4.0}
    \def\tankh{6.0}
    \def\waterh{3.0} % Livello attuale dell'acqua h(t)
    \def\initwaterh{1.0} % Livello iniziale dell'acqua h(0)
    % Dimensioni e posizione dei tubi
    \def\pipew{0.4} % Diametro del tubo
    \def\pipexin{-2.5} % Coordinata X di inizio del tubo
    \def\pipey{6.0} % Coordinata Y dell'asse del tubo superiore
    \def\pipexout{1.3} % Coordinata X centrale dell'uscita del tubo nel serbatoio
    \def\valveposx{-0.5} % Coordinata X del centro della valvola
    \def\pipeouty{5.0} % Coordinata Y della fine del tubo discendente

    % --- 1. Disegno del serbatoio e dell'acqua ---
    % Profilo del serbatoio (lati e fondo)
    \draw [thick] (0, \tankh) -- (0,0) -- (\tankw,0) -- (\tankw, \tankh);

    % Riempimento dell'acqua nel serbatoio fino al livello h(t)
    \fill [water] (0,0) rectangle (\tankw, \waterh);
    
    % Etichetta V(t) al centro dell'area dell'acqua
    \node at (\tankw/2, \waterh/2) [label_font] {$V(t)$};

    % Etichetta S sul fondo del serbatoio
    \node at (\tankw/2, -0.4) [label_font] {$S$};

    % Disegno del rubinetto e della valvola
    \draw [fill=water, draw=none] (\pipexin, \pipey+\pipew/2) -- (\pipexout+\pipew/2, \pipey+\pipew/2) -- (\pipexout+\pipew/2, \pipeouty) -- (\pipexout-\pipew/2, \pipeouty) -- (\pipexout-\pipew/2, \pipey-\pipew/2) -- (\pipexin, \pipey-\pipew/2) -- cycle;
    
    % Valvola
    \node (v) at (\valveposx, \pipey) [circle, draw, minimum size=0.8cm, thick, fill=white] {};
    \draw [thick] (v.center) + (-0.28, 0.28) -- + (0.28, -0.28);
    \draw [thick] (v.center) + (0.28, 0.28) -- + (-0.28, -0.28);
    \draw [thick] (v.north) -- +(0, 0.5);
    \node at ($(v.north) + (0, 0.5)$) [draw, fill=black, minimum size=0.18cm, anchor=south] {};

    % Segmento a sinistra della valvola
    \draw (\pipexin, \pipey+\pipew/2) -- (\valveposx-0.4, \pipey+\pipew/2);
    \draw (\pipexin, \pipey-\pipew/2) -- (\valveposx-0.4, \pipey-\pipew/2);
    % Segmento a destra della valvola e curva discendente
    \draw (\valveposx+0.4, \pipey+\pipew/2) -- (\pipexout+\pipew/2, \pipey+\pipew/2) -- (\pipexout+\pipew/2, \pipeouty);
    \draw (\valveposx+0.4, \pipey-\pipew/2) -- (\pipexout-\pipew/2, \pipey-\pipew/2) -- (\pipexout-\pipew/2, \pipeouty);
    
    % Etichetta P_max all'inizio del tubo
    \node at (\pipexin-0.1, \pipey) [anchor=east, label_font] {$P_{max}$};

    % Colonna d'acqua piena tra l'uscita del tubo e la superficie dell'acqua
    \fill [water] (\pipexout-\pipew/2, \waterh) rectangle (\pipexout+\pipew/2, \pipeouty);
    % Disegno dei bordi della colonna di flusso
    \draw (\pipexout-\pipew/2, \pipeouty) -- (\pipexout-\pipew/2, \waterh);
    \draw (\pipexout+\pipew/2, \pipeouty) -- (\pipexout+\pipew/2, \waterh);

    % Linee di estensione dal serbatoio
    \draw [thin] (-1.1, \initwaterh) -- (0, \initwaterh); % Estensione al livello h(0)
    \draw [thin] (-0.8, \waterh) -- (0, \waterh); % Estensione al livello dell'acqua h(t)
    \draw [thin] (-1.3, 0) -- (0, 0); % Estensione dal fondo del serbatoio
    
    % Freccia di dimensione e etichetta per h(0)
    \draw [dim_arrow] (-1.0, 0) -- (-1.0, \initwaterh);
    \node at (-1.0-0.1, \initwaterh/2) [anchor=east, label_font] {$h(0)$};
    
    % Freccia di dimensione e etichetta per h(t) 
    \draw [dim_arrow] (-0.7, 0) -- (-0.7, \waterh);
    \node at (-1.2, \waterh+0.1) [anchor=south, label_font] {effetto: $h(t)$}; 

\end{tikzpicture}

\end{document}
```

E' una situazione in tempo continuo: $t\in \mathbb{R}^+$.
Vediamo come possiamo modellare tale situazione.
## PROBLEMA DIRETTO
Modellazione problema:
- $V(t) = Sh(t)$
- $V(0)=V_{0}$
- $h(0)=h_{0}$
- $t\in \mathbb{R}^+$

Possiamo scrivere il bilancio della massa che, chiaramente, è dato da "accumulo=ingresso-uscita" (quest'ultima nulla nel nostro caso):
$$
\frac{dV}{dt} = a(t)P_{\text{max}} - 0
$$
Notiamo che si tratta sostanzialmente di un **problema di Cauchy**: abbiamo un'*equazione differenziale* e delle *condizioni iniziali*.

>[!def] PROBLEMA DIRETTO
>Data la *dinamica del sistema*, la **causa** e le *condizioni iniziali*, vogliamo determinare l'*effetto* (fini **previsionali**).
### SOLUZIONE
Risolviamo l'equazione differenziale vista sopra per separazione delle variabili.
$$
\underbrace{ S \frac{dh}{dt} }_{ \frac{dV}{dt} } = a(t)P_{\text{max}} \implies dh = S^{-1}P_{\text{max}}a(t)dt
$$
$$
\int_{0}^{h(t)} dh(\tau) = \int_{0}^t S^{-1}P_{\text{max}}a(\tau)d\tau
$$
E otteniamo:
$$
h(t) = h(0) + S^{-1}P_{\text{max}} \int_{0}^t a(\tau)d\tau
$$
Possiamo realizzare un diagramma per *schematizzare* questo semplice sistema di controllo:

```tikz
\usepackage{amsmath} % Per i simboli matematici come \dot{h}
\usetikzlibrary{shapes.geometric, arrows, positioning}

\begin{document}

\begin{tikzpicture}[
    auto,           
    >=latex',       
    node distance=2cm, 
    % Stile per il blocco rettangolare (Integratore)
    block/.style={
        draw, 
        fill=white, 
        rectangle, 
        minimum height=3.5em, 
        minimum width=3em,
        align=center
    },
    % Stile per il blocco di guadagno (Triangolo)
    gain/.style={
        draw,
        fill=white,
        isosceles triangle,
        isosceles triangle apex angle=60,
        %shape border rotate=-90, % Ruota il triangolo per puntare a destra
        minimum height=3em,
        inner sep=2pt
    },
    % Stili per i punti di ingresso e uscita
    input/.style={coordinate},
    output/.style={coordinate}
]
    
    % Punto di ingresso
    \node [input] (input) {};
    
    % Blocco Alpha
    \node [gain, right=of input] (gain) {$\alpha$};
    
    % Blocco Integratore
    \node [block, right=of gain] (integrator) {\huge $\int$};
    \node [draw, inner sep=2pt, font=\footnotesize, anchor=south east, xshift=-1pt, yshift=1pt] 
          at (integrator.south east) {$h_0$};

    % Punto di uscita
    \node [output, right=of integrator] (output) {};

    % Connessioni e Etichette
    
    % Freccia dall'ingresso al guadagno
    \draw [->] (input) -- node[above] {$a(t)$} (gain);
    
    % Freccia dal guadagno all'integratore
    \draw [->] (gain) -- node[above] {$\dot{h}(t)$} (integrator);
    
    % Freccia dall'integratore all'uscita
    \draw [->] (integrator) -- node[above] {$h(t)$} (output);

\end{tikzpicture}

\end{document}
```

Il blocco $\alpha$ rappresenta l'azione data dalla posizione della valvola: da questa si calcola $\dot{h}(t)$, che viene poi *integrata* per ottenere l'*effetto* (si parla infatti di **processo integrale**).

>[!def] PROCESSO INTEGRALE
>A un *ingresso* (non nullo) limitato corrisponde un'*"uscita"* **non limitata**.

Per semplicità, supponiamo $a(t)=\bar{a}$. In tal caso abbiamo:
$$
h(t) = h_{0} + S^{-1}P_{\text{max}}\bar{a}t
$$
### CENNO: INTEGRAZIONE NUMERICA
In questo caso l'integrale era semplice da calcolare. In casi più complessi si fa utilizzo di *metodi numerici* (che vedremo più avanti).

Vediamone un esempio: consideriamo un'*approssimazione al primo ordine* di $\dot{h}(t)$
$$
\frac{dh(t)}{dt} = \dot{h}(t) = \alpha a(t) \text{ , } \alpha = \frac{P_{\text{max}}}{S}
$$
Dalla definizione di derivata:
$$
\dot{h}(t) = \frac{dh(t)}{dt} = \lim_{ \varepsilon \to \infty } \frac{h(t+\varepsilon)-h(t)}{\varepsilon} \text{ , } \varepsilon > 0
$$
Per valori $\varepsilon\ll 1$ abbiamo:
$$
\dot{h}(t) \approx \frac{h(t+\varepsilon )-h(t)}{\varepsilon} \implies h(t+\varepsilon) \approx h(t) + \varepsilon \dot{h}(t)
$$
Questo ci consente di calcolare un "*valore successivo*" conoscendo un "*valore precedente*", in modo analogo alla tecnica della **ricorsione**.

Per semplicità, scegliamo un *passo di discretizzazione* unitario: $\varepsilon := 1$.
Notiamo che in tal caso la *relazione ricorsiva* è:
$$
\begin{matrix}
h(1) := h(0) + \dot{h}(0) \\
h(2) = h(1) + \dot{h}(1) \\
\vdots \\
h(n) = h(n-1) + \dot{h}(n-1)
\end{matrix}
$$
## PROBLEMA INVERSO
Vediamo ora un *approccio diverso* (opposto al precedente) al problema:

>[!def] PROBLEMA INVERSO
>Fissato un *effetto desiderato*, vogliamo determinare la **causa** che lo *realizza*.

In quest'ottica abbiamo:
- *Desiderio*: $h_{\text{des}}$ altezza desiderata.
- *Effetto*: $h(t)$, partendo da $h(0)=0$ e con l'*ipotesi* $h(t)\leq h_{\text{des}}$.

Come possiamo procedere?

>[!idea] IDEA
>Definiamo la funzione seguente (**"decisione"**):
>$$ e(t) = h_{\text{des}} - h(t) $$

A partire dalla quale possiamo definire il **feedback** moltiplicando per $\frac{1}{h_{\text{des}}}$:
$$
a(t) = \frac{e(t)}{h_{\text{des}}} = 1 - \frac{h(t)}{h_{\text{des}}}
$$

```tikz
\usepackage{tikz}
\usepackage{amsmath}
\usetikzlibrary{shapes, arrows, positioning}

\tikzset{
  block/.style={
    draw, 
    fill=white, 
    rectangle, 
    minimum height=3em, 
    minimum width=4em
  },
  sum/.style={
    draw, 
    fill=white, 
    circle, 
    minimum size=1.5em,
    inner sep=2pt
  },
  gain/.style={
    draw,
    fill=white,
    isosceles triangle,
    isosceles triangle apex angle=60,
    shape border rotate=0,
    minimum height=3em,
    inner sep=1pt
  },
  input/.style={coordinate},
  output/.style={coordinate}
}  

\begin{document}

\begin{tikzpicture}[auto, >=latex']
    % Posizionamento dei nodi e dei blocchi
    \node [input, name=input] {};
    \node [sum, right = 1.5cm of input] (sum) {};
    
    % Blocco Decisione
    \node [block, right = 2.5cm of sum] (controller) {$\displaystyle\frac{1}{h_{des}}$};
    
    % Blocco Guadagno (Triangolo)
    \node [gain, right = 1cm of controller] (gain) {$\alpha$};
    
    % Blocco Integratore con lo "0" in un riquadro in basso a destra
    \node [block, right = 1cm of gain, minimum width=3.5em, minimum height=4em] (integrator) {\huge $\int$};
    \node [draw, inner sep=2pt, font=\small, anchor=south east, xshift=-2pt, yshift=2pt] 
          at (integrator.south east) {0};

    \node [output, right = 1.5cm of integrator] (output) {};

    % Connessioni principali (in avanti)
    \draw [draw,->] (input) -- node[above] {$h_{des}$} node[pos=0.85, above] {$+$} (sum);
    \draw [->] (sum) -- node[above] {$e=h_{des}-h(t)$} (controller);
    \draw [->] (controller) -- (gain);
    \draw [->] (gain) -- node[above] {$\dot{h}(t)$} (integrator);
    \draw [->] (integrator) -- node[above] {$h(t)$} coordinate (y) (output);
    
    % Ramo di feedback
    \draw [->] (y) -- ++(0,-1.5cm) coordinate (y_down) 
               -- node[below] {feedback} (y_down -| sum) 
               -- node[pos=0.8, left] {$-$} (sum);
               
\end{tikzpicture}

\end{document}
```


>[!note] NOTA
>In tal modo stiamo *regolando l'apertura della valvola* in funzione della "*distanza*" dal valore desiderato.

Si parla in tal caso di "*sistema* a **feedback**".
### SOLUZIONE
Abbiamo in tal caso:
$$
\frac{dh(t)}{dt} = \frac{P_{\text{max}}}{S}a(t)
$$
E separando i differenziali, troviamo:
$$
\frac{S}{P_{\text{max}}} \frac{h_{\text{des}}}{h_{\text{des}}-h}dh = dt
$$
E risolvendo:
$$
\int_{0}^h \frac{S}{P_{\text{max}}} \frac{h_{\text{des}}}{h_{\text{des}}-h}dh = \int_{0}^t d\tau \implies
$$
$$
\implies - \frac{Sh_{\text{des}}}{P_{\text{max}}}[\ln(h_{\text{des}}-h(t))-\ln(h_{\text{des}})] = t \implies - \frac{Sh_{\text{des}}}{P_{\text{max}}}\ln\left( 1-\frac{h(t)}{h_{\text{des}}} \right) = t
$$
E concludiamo quindi:
$$
h(t) = h_{\text{des}}\left(1 - e^{ - \frac{P_{\text{max}}}{Sh_{\text{des}}}t } \right)
$$
>[!note] NOTA
>L'esponente è *negativo*, per cui $h(t)$ è *limitata* e **converge**.
# ESEMPIO IN TEMPO DISCRETO
Consideriamo la dinamica dell'utilizzo di una CPU dove, in un dato intervallo di tempo, un certo numero di processi viene eseguito e contribuisce all'utilizzo della CPU.

In questo caso, il tempo è dato da $k\in \mathbb{N}$
- L'*ingresso* $u(k)$ rappresenta il numero di processi da eseguire all'istante $k$ (per semplicità non consideriamo alcun buffer).
- Con la variabile $x(k)$ indichiamo l'utilizzo $\%$ della CPU all'istante $k$.

La dinamica è descritta dalla relazione a tempo discreto:
$$
x(k+1) = \text{min}\{ 100, ax(k) + bu(k) \}
$$
Qui:
- $0<a<1$ è il *coefficiente di persistenza* di processi non ancora elaborati dalla CPU.
- $b>0$ è il *contributo di ciascun processo* all'utilizzo della CPU.
- La funzione $\text{min}\{ 100, \dots \}$ impone il *vincolo fisico* di *utilizzo massimo* della CPU.

>[!note] NOTA
>- Il parametro $a$ governa l'**inerzia**: valori di $a$ "vicini" a $1$ implicano una *forte dipendenza dall'utilizzo passato* della CPU.
>- Il parametro $b$ definisce l'**impatto** di ciascun processo sull'utilizzo della CPU: valori maggiori portano più rapidamente verso la *saturazione*.
>- La presenza dell'operatore $\text{min}$ introduce **non linearità** dovuta alla *saturazione fisica* della CPU.
## PROBLEMA DIRETTO
Dati il *valore iniziale* $x(0)$ e una sequenza di ingressi $u(k)$, vogliamo determinare la *sequenza* $x(k)$ applicando la relazione di dinamica *passo dopo passo*.
### ESEMPIO
Supponiamo:
- $a=0.9$
- $b=2$
- $x(0)=10$
- $u(k)=\bar{u}=3$
Ricordiamo la *relazione ricorsiva*:
$$
x(k+1) = \text{min}\{ 100, ax(k) + bu(k) \}
$$
E iteriamo:
- Passo $k=0$ : $x(1)=0.9\cdot 10+2\cdot 3 = 9+6=15$
- Passo $k=1$ : $x(2)=0.9\cdot 15+6 = 13.5+6=19.5$
- Passo $k=2$ : $x(3)=0.9\cdot 19.5+6 = 17.55+6=23.55$
- $\dots$

A questo punto viene da chiedersi:

>[!idea] DOMANDA
>$$ \exists \bar{x} \text{ tale che } x(k+1) = x(k) = \bar{x} \text{ ?} $$

>[!note] NOTA
>Ci stiamo sostanzialmente chiedendo se esiste un valore per cui si raggiunge un **equilibrio**, oppure se il sistema è destinato a *rimanere instabile*.

Per tale $\bar{x}$ si avrà: 
$$
\bar{x}=a\bar{x}+b\bar{u}
$$
Risolvendo troviamo allora:
$$
\bar{x} = \frac{b}{1-a}\bar{u} = \frac{2}{1-0.1}\cdot 3 = 60 < 100
$$
Per cui, con i valori di $a,b$ e $\bar{u}$ considerati nel nostro esempio, il sistema raggiunge un *equilibrio* senza arrivare alla saturazione.
### RAGGIUNGIMENTO DELL'EQUILIBRIO
Analizziamo meglio la dinamica di *raggiungimento dell'equilibrio*.
Per farlo, consideriamo ancora una volta una funzione $e(k)$ che rappresenti lo scostamento da $\bar{x}$:
$$
e(k) := x(k) - \bar{x}
$$
Segue allora:
$$
\begin{align*}
e(k+1) &= x(k+1) - \bar{x} \\
& = ( ax(k) + bu(k) ) - ( a\bar{x}+b\bar{u} ) \\
& = a(x(k)-\bar{x}) - b( \cancelto{ 0 }{ u(k) - \bar{u} } ) \\
& = ae(k)
\end{align*}
$$
E concludiamo:
$$
e(k) = ae(k-1) = \ldots = a^ke(k)
$$
Ma sappiamo che $0<a<1$, per cui:
$$
\lim_{ k \to \infty } e(k) = 0
$$

>[!note] NOTA
>Abbiamo dimostrato che lo *scostamento* di $x(k)$ da $\bar{x}$ tende a 0.
>Allora, con l'assunzione $u(k)=\bar{u}\text{ }\forall k$, il sistema si **stabilizzerà** su un valore $\bar{x}$ a prescindere dai parametri $a$ (comunque compreso tra 0 e 1) e $b$.
>Chiaramente, tuttavia, va *comunque considerato il limite fisico* di utilizzo, che non può superare il $100\%$.
### SOLUZIONE GENERALE
Visto che non abbiamo a che fare con un problema particolarmente complesso, determiniamo anche una *formula chiusa* a partire dalla *relazione ricorsiva*.
$$
\begin{align*}
x(1) &= ax(0) + b\bar{u} \\
x(2) & = ax(1) + b\bar{u} = a^2x(0) + ab\bar{u} + b\bar{u} \\
x(3) & = ax(2) + b\bar{u} = a^3x(0) + a^2b\bar{u} + ab\bar{u} + b\bar{u} \\
& \vdots \\
x(k) &= a^kx(0) + b\bar{u} \sum_{i=1}^{k-1}a^i
\end{align*}
$$
Ricordiamo che:
$$
\sum_{i=0}^{k-1}a^i = \frac{1-a^k}{1-a}
$$
E otteniamo infine:
$$
x(k) = a^kx(0) + \frac{b\bar{u}}{1-a}(1-a^k)
$$

```tikz
\usepackage{pgfplots}
\usepackage{amsmath}

\definecolor{myblue}{RGB}{31,119,180}
\definecolor{myred}{RGB}{200,30,30}

\begin{document}

\begin{tikzpicture}[scale = 1.2]
    \begin{axis}[
        width=12cm, height=5cm, 
        xmin=-0.5, xmax=29.5,   
        ymin=0, ymax=100,       
        xlabel={istante k},
        ylabel={\% utilizzo CPU},
        xtick={0,5,10,15,20,25},
        ytick={0,20,40,60,80,100},
        tick align=outside,     
        grid=major,             
        grid style={line width=0.3pt, draw=gray!40}, 
        enlarge x limits=false,
        enlarge y limits=false
    ]

        % Linea valore di regime 
        \draw[myred, dashed, line width=1.5pt] (axis cs:-0.5,60) -- (axis cs:29.5,60);

        % Plot x(k)
        \addplot[
            myblue, 
            thick, 
            ycomb,          
            mark=*,          
            mark size=1.5pt,
            domain=0:29,     
            samples=30       
        ] 
        {60 - 50*(0.9^x)};

        \node[anchor=south west, font=\Large] at (axis cs: 0, 62) {$\bar{x}$};
        \node[anchor=south west, font=\Large] at (axis cs: 0.5, 25) {$x(k)$};

    \end{axis}
\end{tikzpicture}

\end{document}
```
## PROBLEMA INVERSO
Consideriamo ora il *problema inverso*: dato un **valore desiderato** di utilizzo $\bar{x}$, vogliamo determinare quale *valore costante* di *ingresso* $\bar{u}$ mantiene il sistema *in equilibrio* a quel valore.

Desideriamo $\bar{x}$ tale che $x(k+1)=x(k)=\bar{x}$.
Per cui troviamo:
$$
\bar{x} = a\bar{x} + b\bar{u}
$$
Da cui ricaviamo:
$$
\bar{u} = \frac{1-a}{b}\bar{x}
$$
# PROBLEMA DIRETTO VS INVERSO
Abbiamo affrontato entrambe le situazioni sia come *problema diretto* che come *problema inverso*; chiariamo ora le differenze.

>[!note] PROBLEMA DIRETTO
>L'*obiettivo* è: data una *causa*, **determinare l'effetto**.
>
>Siamo dei semplici **osservatori**: conosciamo le *condizioni iniziali*, *osserviamo la dinamica* del sistema e ne **prevediamo l'evoluzione** (senza *decidere* nulla).

>[!note] PROBLEMA INVERSO
>L'*obiettivo* è: dato un **effetto desiderato**, determinare la giusta **causa che lo genera**.
>
>Ora non siamo più solo osservatori, ma prendiamo delle **decisioni** per raggiungere l'effetto prefissato.

Abbiamo inoltre visto due *tipi* diversi di soluzioni al problema inverso.

>[!note] OPEN-LOOP VS FEEDBACK
>Nell'esempio della CPU, abbiamo determinato una "**decisione**" *fissa* $\bar{u}$ che non varia nel tempo.
>
>Nell'esempio del serbatoio, invece, abbiamo implementato un sistema di **feedback** che *regola costantemente* l'apertura della valvola.
>
>Vedremo che questo sistema permette di *reagire meglio a fattori esterni imprevedibili*.
