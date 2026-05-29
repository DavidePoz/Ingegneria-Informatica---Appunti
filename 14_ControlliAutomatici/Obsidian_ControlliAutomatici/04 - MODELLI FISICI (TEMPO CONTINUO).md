# INDICE SEZIONE
- [ ] [[#CIRCUITI ELETTRICI]]
	    - [[#COMPONENTI E LEGGI COSTITUTIVE]]
	    - [[#ESEMPIO E1]]
	    - [[#ESEMPIO E2]]
	    - [[#ESEMPIO E3]]
	    - [[#ESEMPIO E4]]
- [ ] [[#SISTEMI MECCANICI TRASLAZIONALI]]
		- [[#COMPONENTI E LEGGI COSTITUTIVE]]
		- [[#ESEMPIO T1]]
		- [[#ESEMPIO T2]]
		- [[#ESEMPIO T3]]
- [ ] [[#SISTEMI MECCANICI ROTATIVI]]
	    - [[#COMPONENTI E LEGGI COSTITUTIVE]]
	    - [[#ESEMPIO R1]]
	    - [[#ESEMPIO R2]]
- [ ] [[#SISTEMI ELETTROMECCANICI]]
	    - [[#ESEMPIO EM1]]
# CIRCUITI ELETTRICI
## COMPONENTI E LEGGI COSTITUTIVE
Ricordiamo le due leggi di Kirchhoff:

>[!th] LEGGE DELLE CORRENTI
>La *somma algebrica* delle correnti in un singolo nodo è *nulla*.
>$$ \sum_{k}i_{k} = 0 $$

>[!th] LEGGE DELLE TENSIONI
>La *somma algebrica* delle *tensioni tra coppie di nodi* lungo un *percorso chiuso* è *nulla*.
>$$ \sum_{i}v_{i} = 0 $$

Ricordiamo inoltre che i componenti possono essere convenzionati da *utilizzatori oppure da generatori*:
1. **UTILIZZATORE**: quando il riferimento di corrente è entrante nel polo positivo.
2. **GENERATORE**: quando il riferimento di corrente è entrante nel polo negativo.

I componenti principali che consideriamo sono:
- **RESISTORI**: $v(t)=Ri(t)$
- **CONDENSATORI**: $i(t)=C \frac{dv(t)}{dt}$
- **INDUTTORI**: $v(t)=L \frac{di(t)}{dt}$
- **GENERATORI DI TENSIONE**: $v(t)=V_{g}(t)$
- **GENERATORI DI CORRENTE**: $i(t)=I_{g}(t)$

>[!note] NOTA
>Per definire un *modello di stato* di un circuito è necessario *definire opportunamente* le *variabili indipendenti* (ingressi e disturbi) e le **variabili di stato**: solitamente, si considerano come variabili di stato le *tensioni ai capi dei condensatori* e *correnti negli induttori*.
## ESEMPIO E1
Vediamo, per esempio, un circuito *RLC*:

```tikz
\usepackage{circuitikz}

\begin{document}

\begin{circuitikz} [american, scale=1.4]
    \draw
    % Generatore di tensione
    (0,0) to[V, l=$V_g(t)$, invert] (0,3)
    
    % Resistore superiore
    to[R, l=$R$] (4,3)
    
    % Condensatore a destra con etichetta di tensione x1(t)
    to[C, l=$C$, v=$x_1(t)$] (4,0)
    
    % Induttore inferiore con freccia di corrente x2(t)
    to[L, l=$L$, i_={$\quad x_2(t)$}, f^<] (0,0);
    
    % Freccia del loop centrale
    \draw[thin, <-] (2.2,1.5)  node[left] {} arc (30:330:0.4);

\end{circuitikz}

\end{document}
```

Come detto sopra, poniamo:
- $x_{1}(t)$ la *tensione ai capi* del condensatore $C$.
- $x_{2}(t)$ la *corrente nell'induttore* $L$.

Per cui abbiamo:
$$
\dot{x}_{1}(t) = \frac{1}{C}x_{2}(t)
$$
E, dalla legge delle maglie, troviamo anche:
$$
L\dot{x}_{2}(t) + Rx_{2}(t) + x_{1}(t) -V_{g}(t) = 0
$$
Identifichiamo l'**ingresso esterno** con $V_{g}(t)$: $u_{1}(t):=V_{g}(t)$ e ricaviamo $\dot{x}_{2}$.

Allora le equazioni che descrivono il sistema sono:
$$
\begin{cases}
\dot{x}_{1}(t) = \frac{1}{C}x_{2}(t) \\ \\
\dot{x}_{2}(t) = -\frac{1}{L}x_{1}(t) -\frac{R}{L}x_{2}(t) + \frac{1}{L}u_{1}(t)
\end{cases}
$$
Che scriviamo nella forma compatta $\dot{x}(t)=Ax(t)+Bu(t)$, con:
$$
\begin{align*}
A = \begin{bmatrix}
0 & \frac{1}{c} \\
-\frac{1}{L} & -\frac{R}{L}
\end{bmatrix} & & B = \begin{bmatrix}
0 \\
\frac{1}{L}
\end{bmatrix}
\end{align*}
$$
## ESEMPIO E2
Consideriamo il seguente circuito:

```tikz
\usepackage{circuitikz}

\begin{document}

\begin{circuitikz}[american, scale = 1.2]
    \draw
    (0,0) to[I, l=$I_g(t)$] (0,2)
    
    to[short, -*] (2,2) 
    to[C, l_=$C$] (2,0)
    to[short, *-] (0,0)
    
    (2,2) -- (4,2) to[R, l_=$R$, *-*, v^=$x_1(t)$,] (4,0) -- (2,0)
    
    (4,2) -- (6,2)
    to[L, l_=$L$, i>=$x_2(t)$] (6,0)
    -- (4,0);

\end{circuitikz}

\end{document}
```

Ora abbiamo:
$$
\dot{x}_{2}(t) = \frac{1}{L}x_{1}(t)
$$
E, dalla legge dei nodi:
$$
I_{g}(t) = \frac{1}{R}x_{1}(t) + x_{2}(t) + C\dot{x}_{1}(t)
$$
Identifichiamo l'ingresso esterno $u_{1}(t):=I_{g}(t)$ e ricaviamo l'equazione per $\dot{x}_{1}$.
Abbiamo così:
$$
\begin{cases}
\dot{x}_{1}(t) = -\frac{1}{RC}x_{1}(t) - \frac{1}{C}x_{2}(t) + \frac{1}{C}u_{1}(t) \\ \\
\dot{x}_{2}(t) = \frac{1}{L}x_{1}(t)
\end{cases}
$$
Che scriviamo in forma compatta: $\dot{x}(t)=Ax(t)+Bu(t)$, con:
$$
\begin{align*}
A = \begin{bmatrix}
-\frac{1}{RC} & -\frac{1}{C} \\
\frac{1}{L} & 0
\end{bmatrix} & & B = \begin{bmatrix}
\frac{1}{C} \\
0
\end{bmatrix}
\end{align*}
$$
## ESEMPIO E3
Consideriamo ora il seguente circuito più complesso:

```tikz
\usepackage[american]{circuitikz}

\begin{document}

\begin{circuitikz}[american, scale = 1.2]
    \draw
    % Maglia di sinistra
    (0,0) to[V, l=$V_1(t)$, invert] (0,3)
    to[R, l=$R$] (3,3)
    % Nodo A colorato
    node[circle, fill=cyan, inner sep=1.5pt, label=above:\textcolor{cyan}{$A$}] (A) {}
    to[C, l=$C$, v=$x_1(t)$, *-*] (3,0)
    to[short] (0,0)
    
    % Connessione a terra
    (3,0) node[ground] {}
    
    % Maglia di destra
    (A) to[L, l=$L$, i=$x_3(t)$] (6,3)
    to[C, l=$C$, v=$x_2(t)$, *-] (6,0)
    % Generatore di tensione inferiore (V2)
    to[V, l_=$V_2(t)$] (3,0);

    % Freccia del loop nella maglia di destra
    \draw[thin, <-] (4.8,2.5)  node[left] {} arc (30:330:0.3);

\end{circuitikz}

\end{document}
```

Scegliamo ancora una volta $x_{1}(t)$ e $x_{2}(t)$ per rappresentare le *tensioni ai capi dei condesatori* e $x_{3}(t)$ la *corrente attraverso l'induttore*.
Abbiamo quindi:
$$
\dot{x}_{2}(t) = \frac{1}{C}x_{3}(t)
$$
Dalla *legge delle maglie* (applicata alla maglia di destra) ricaviamo:
$$
L\dot{x}_{3}(t) + x_{2}(t) + V_{2}(t) - x_{1}(t) = 0 
$$
Da cui:
$$
\dot{x}_{3}(t) = \frac{1}{L}[x_{1}(t)-x_{2}(t)-V_{2}(t)]
$$
Infine, applicando la *legge dei nodi* al nodo $A$, troviamo l'ultima equazione che ci serve:
$$
\frac{1}{R}[V_{1}(t)-x_{1}(t)] = C\dot{x}_{1}(t) + x_{3}(t)
$$
Da cui ricaviamo:
$$
\dot{x}_{1}(t) = -\frac{1}{RC}x_{1}(t) - \frac{1}{C}x_{3}(t) + \frac{1}{RC}V_{1}(t)
$$
Identifichiamo gli *ingressi esterni* con i due *generatori di tensione*:
$$
\begin{align*}
u_{1}(t) = V_{1}(t) & & u_{2}(t) = V_{2}(t)
\end{align*}
$$
Per cui scriviamo il sistema nella forma compatta $\dot{x}(t)=Ax(t)+Bu(t)$ con
$$
\begin{align*}
A = \begin{bmatrix}
-\frac{1}{RC} & 0 & -\frac{1}{C} \\
0 & 0 & \frac{1}{C} \\
\frac{1}{L} & -\frac{1}{L} & 0
\end{bmatrix} & & B = \begin{bmatrix}
\frac{1}{RC} & 0 \\
0 & 0 \\
0 & -\frac{1}{L}
\end{bmatrix}
\end{align*} 
$$
## ESEMPIO E4
Consideriamo infine il seguente circuito:

```tikz
\usepackage{circuitikz}

\begin{document}

\begin{circuitikz}[american, scale = 1.2]
    \draw
    % Ramo di sinistra con generatore
    (0,0) to[V, l=$V_g(t)$, invert] (0,4)
    to[short] (2,4)
    
    % Ramo centrale con i due condensatori
    to[C, l=$C_2$, v=$x_2(t)$] (2,2) 
    % Nodo A azzurro
    node[circle, fill=cyan, inner sep=1.5pt, label=above right:\textcolor{cyan}{$A$}] (A) {}
    to[C, l=$C_1$, v=$x_1(t)$, -*] (2,0)
    to[short, -*] (0,0)
    
    % Ramo di destra con il resistore
    (A) -- (4,2)
    to[R, l=$R$] (4,0)
    -- (2,0);

\end{circuitikz}

\end{document}
```

Anche stavolta identifichiamo $V_{g}$ con l'*ingresso esterno*:
$$
u_{1}(t) = V_{g}(t)
$$
Notiamo che in questo caso le *variabili di stato sono legate* dalla seguente relazione:
$$
x_{1}(t) + x_{2}(t) = u_{1}(t)
$$
>[!note] NOTA
>*Non è possibile* scrivere le equazioni del circuito nella usuale *forma matriciale*, perchè le *variabili di stato non sono indipendenti*

Si può **diminuire la dimensione dello stato** scegliendo come *unica variabile* la *tensione* $x_{1}(t)$ ai capi di $C_{1}$.
Così, dalla legge dei nodi (applicata al nodo $A$), troviamo:
$$
C_{2}[\dot{V}_{g}(t)-\dot{x}_{1}(t)] = C_{1}\dot{x}_{1}(t) + \frac{1}{R}x_{1}(t)
$$
Conviene allora scegliere:
$$
u_{1}(t) = \dot{V}_{g}(t)
$$
E ricaviamo quindi:
$$
\dot{x}_{1}(t) = -\frac{1}{(C_{1}+C_{2})R}x_{1}(t) + \frac{C_{2}}{C_{1}+C_{2}}u_{1}(t)
$$
In questo modo possiamo *utilizzare la rappresentazione matriciale usuale*, con $A$ e $B$ matrici con *una sola entrata*:
$$
\begin{align*}
A = -\frac{1}{(C_{1}+C_{2})R} & & B = \frac{C_{2}}{C_{1}+C_{2}}
\end{align*} 
$$
# SISTEMI MECCANICI TRASLAZIONALI
Ricordiamo i principi della dinamica:

>[!th] PRIMO PRINCIPIO DINAMICA
>Un corpo *non soggetto a forze esterne* ha *velocità costante*.
>$$ \sum_{i}F_{i} = 0 \Leftrightarrow \dot{s}(t) = \text{ costante} $$

>[!th] SECONDO PRINCIPIO DELLA DINAMICA
>La somma delle *forze esterne* applicate ad un oggetto è data da:
>$$ \sum_{i}F_{i} = m \frac{d^2s(t)}{dt^2} = m\ddot{s}(t) $$
## COMPONENTI E LEGGI COSTITUTIVE
**MASSA**:

```tikz
\usepackage{tikz}

% Definiamo dei colori personalizzati per il blocco (grigio chiaro)
\definecolor{blockgray}{RGB}{220,220,220}

\begin{document}

\begin{tikzpicture}

    % Piano d'appoggio (linea nera)
    \draw[thick] (-2,0) -- (5,0);

    % Blocco M (carrello)
    % Usiamo 'fill' per il colore e 'draw' per il bordo
    \draw[thick, fill=blockgray] (0,0.4) rectangle (3,2.4);
    
    % Etichetta M (testo centrato nel blocco)
    \node at (1.5,1.4) {\huge $M$};

    % Ruote (cerchi neri)
    \draw[thick] (0.75,0.2) circle (0.2);
    \draw[thick] (2.25,0.2) circle (0.2);

    % Forza F (freccia verso sinistra)
    % Il punto d'ancoraggio è (3, 1.4) (centro del lato destro del blocco)
    \draw[->, >=stealth, thick] (4.5,1.4) -- (3.0,1.4);
    
    % Etichetta F (testo posizionato sopra la freccia)
    \node[anchor=south] at (3.75,1.4) {\huge $F$};

    % Spostamento s (linee e freccia)
    % Linea tratteggiata verticale di riferimento (dal centro inferiore del blocco)
    \draw[dashed, thin] (1.5,0.4) -- (1.5,-0.6);
    
    % Freccia dello spostamento s (orizzontale tra le due linee di riferimento)
    \draw[|->, >=stealth, thick] (2.3,-0.3) -- (0.5,-0.3);
    
    % Etichetta s (testo posizionato sotto la freccia)
    \node[anchor=north] at (0.75,-0.3) {\huge $s$};

\end{tikzpicture}

\end{document}
```

$$
F(t) = M\ddot{s}(t)
$$

**MOLLA**:

```tikz
\usepackage{tikz}
\usetikzlibrary{decorations.pathmorphing}

\begin{document}

\begin{tikzpicture}[>=stealth] % Usa punte di freccia "stealth" (furtive) di default

    % Coordinate di base per chiarezza e per riutilizzarle
    \coordinate (startnode) at (0,0);
    \coordinate (endnode) at (4,0);

    % --- Molla e segmenti terminali ---
    % Segmento a sinistra
    \draw[thick] (startnode) -- (0.8,0);
    
    % La molla vera e propria (usando decoration)
    \draw[thick, decorate, decoration={coil, aspect=0.7, segment length=5pt, amplitude=6pt}] 
        (0.8,0) -- (3.2,0);
    
    % Segmento a destra
    \draw[thick] (3.2,0) -- (endnode);
    
    % I due nodi (punti neri) ai terminali
    \filldraw[black] (startnode) circle (2.5pt);
    \filldraw[black] (endnode) circle (2.5pt);

    % --- Etichetta k1 (costante elastica) ---
    \node[anchor=south] at (2,0.6) {\huge $k_1$};

    % --- Forze F (frecce e testo) ---
    % Forza a sinistra (freccia verso destra)
    \draw[->, thick] (-1.2,0) -- (-0.2,0);
    \node[anchor=south] at (-0.7,0) {\huge $F$};
    
    % Forza a destra (freccia verso sinistra)
    \draw[->, thick] (5.2,0) -- (4.2,0);
    \node[anchor=south] at (4.7,0) {\huge $F$};

    % --- Spostamenti s1 e s2 (riferimenti e frecce) ---
    % Linee tratteggiate verticali di riferimento
    \draw[dashed, thin] (0,0) -- (0,-1.0);
    \draw[dashed, thin] (4,0) -- (4,-1.0);
    
    % --- Spostamento s1 (a sinistra) ---
    % Linea verticale di riferimento 2 (spostamento iniziale)
    \draw[thin] (-1.0, -1.1) -- (-1.0, -1.1); % Punto di riferimento
    
    % Freccia dello spostamento s1 (orizzontale tra le due linee di riferimento)
    \draw[|->, thick] (0.6,-0.7) -- (-0.5,-0.7);
    
    % Etichetta s1
    \node[anchor=north] at (0.5,-0.7) {\huge $s_1$};
    
    % --- Spostamento s2 (a destra) ---
    % Freccia dello spostamento s2
    \draw[|->, thick] (4.6,-0.7) -- (3.5,-0.7);

    % Etichetta s2
    \node[anchor=north] at (4.5,-0.7) {\huge $s_2$};

\end{tikzpicture}

\end{document}
```

$$
\begin{align*}
F(t) &= k_{l}[s_{2}(t)-s_{1}(t)] \\ \\
&= -k_{l}[s_{1}(t)-s_{2}(t)]
\end{align*}
$$

>[!note] NOTA
>$s,s_{1},s_{2}$ sono *posizioni relative* ai *rispettivi riferimenti a riposo*.

**SMORZATORE**:

```tikz
\usepackage{tikz}
% Libreria necessaria per disegnare lo smorzatore
\usetikzlibrary{decorations.pathmorphing, shapes.geometric}

\begin{document}

\begin{tikzpicture}[>=stealth] % Usa punte di freccia "stealth" (furtive) di default

    % Coordinate di base per chiarezza
    \coordinate (startnode) at (0,0);
    \coordinate (endnode) at (4,0);

    % --- Smorzatore (Dashpot) ---
    % Segmento a sinistra
    \draw[thick] (startnode) -- (1.0,0);
    
    % Corpo dello smorzatore (cilindro esterno)
    \draw[thick] (1.0, -0.4) rectangle (2.8, 0.4);
    
    % Pistone interno (rettangolo grigio)
    \draw[thick, fill=black!20] (2.0, -0.3) rectangle (2.4, 0.3);
    
    % Stelo del pistone
    \draw[thick] (2.4, 0) -- (3.2, 0);
    
    % Segmento a destra
    \draw[thick] (3.2,0) -- (endnode);
    
    % I due nodi (punti neri) ai terminali
    \filldraw[black] (startnode) circle (2.5pt);
    \filldraw[black] (endnode) circle (2.5pt);

    % --- Etichetta q1 (coefficiente di smorzamento) ---
    \node[anchor=south] at (2,0.5) {\huge $q_1$};

    % --- Forze F (frecce e testo) ---
    % Forza a sinistra (freccia verso destra)
    \draw[->, thick] (-1.2,0) -- (-0.2,0);
    \node[anchor=south] at (-0.7,0) {\huge $F$};
    
    % Forza a destra (freccia verso sinistra)
    \draw[->, thick] (5.2,0) -- (4.2,0);
    \node[anchor=south] at (4.7,0) {\huge $F$};

    % --- Spostamenti s1 e s2 (riferimenti e frecce) ---
    % Linee tratteggiate verticali di riferimento
    \draw[dashed, thin] (0,0) -- (0,-1.0);
    \draw[dashed, thin] (4,0) -- (4,-1.0);
    
    % --- Spostamento s1 (a sinistra) ---
    % Freccia dello spostamento s1 (orizzontale tra le due linee di riferimento)
    \draw[|->, thick] (0.6,-0.7) -- (-0.5,-0.7);

    % Etichetta s1
    \node[anchor=north] at (0.5,-0.7) {\huge $s_1$};
    
    % --- Spostamento s2 (a destra) ---
    % Freccia dello spostamento s2
    \draw[|->, thick] (4.6,-0.7) -- (3.5,-0.7);
    
    % Etichetta s2
    \node[anchor=north] at (4.5,-0.7) {\huge $s_2$};

\end{tikzpicture}

\end{document}
```

$$
\begin{align*}
F(t) &= q_{l}[\dot{s}_{2}(t) -\dot{s}_{1}(t) ] \\ \\
&= -q_{l}[\dot{s}_{1}(t) -\dot{s}_{2}(t) ]
\end{align*}
$$
>[!note] NOTA
>E' una forza di **attrito viscoso** che si oppone al moto del corpo.

Per definire un modello di stato di un sistema meccanico traslazionale è necessario *definire opportunamente* le *variabili indipendenti* (ingressi e disturbi) e le *variabili di stato*.

>[!note] NOTA
>Solitamente si considerano come *variabili di stato* le *posizioni* e le *velocità* delle masse *relativamente alla posizione di riposo*.

## ESEMPIO T1
Vediamo un esempio di *sistema traslazionale* composto dagli elementi elencati sopra.

```tikz
\usepackage{tikz}
\usetikzlibrary{decorations.pathmorphing, arrows.meta}

\begin{document}

\begin{tikzpicture}[>=Stealth, font=\large]

    % --- Parete e Pavimento ---
    \draw[ultra thick] (0,3.5) -- (0,0) -- (7,0);
    \node[below left] at (0,0) {\huge $s_0 = 0$};

    % --- Massa M ---
    \definecolor{blockgray}{RGB}{220,220,220}
    \draw[thick, fill=blockgray] (2.5,0.6) rectangle (5.2,2.4);
    \node at (3.85,1.5) {\huge $M$};
    
    % Ruote
    \draw[thick, fill=white] (3.1,0.3) circle (0.3);
    \draw[thick, fill=white] (4.6,0.3) circle (0.3);

    % --- Molla (k_l) ---
    \draw[thick] (0,1.8) -- (0.5,1.8);
    \draw[thick, decorate, decoration={coil, aspect=0.7, segment length=5pt, amplitude=7pt}] 
        (0.5,1.8) -- (2,1.8);
    \draw[thick] (2,1.8) -- (2.5,1.8);
    \node[above] at (1.25,2.1) {$k_l$};

    % --- Smorzatore (q_l) ---
    \draw[thick] (0,0.9) -- (0.6,0.9);
    \draw[thick] (0.6,0.6) -- (0.6,1.2) -- (1.6,1.2); % Cilindro sopra
    \draw[thick] (0.6,0.6) -- (1.6,0.6); % Cilindro sotto
    \draw[thick, fill=gray!50] (1.1,0.7) rectangle (1.4,1.1); % Pistone
    \draw[thick] (1.4,0.9) -- (2.5,0.9); % Stelo
    \node[below] at (1.25,0.5) {$q_l$};

    % --- Forza Esterna F ---
    \draw[<- , thick] (5.2,1.7) -- (6.5,1.7);
    \node[above] at (5.8,1.7) {$F$};

    % --- Riferimento spostamento s (sotto la massa) ---
    \draw[dashed] (3.85,0.6) -- (3.85,-0.5);
    \draw[thin] (4.2,-0.4) -- (4.2,-0.4); % Tick
    \draw[->] (4.2,-0.4) -- (2.5,-0.4) node[below] {$s$};
    \draw (4.2,-0.3) -- (4.2,-0.5); % Linea verticale tick

    % --- Etichette Arancioni (Molla) ---
    \begin{scope}[orange!80!black]
        \draw[<-|] (0.5,3.5) -- (2.5,3.5);
        \node[below] at (2.5,3.5) {\huge $s_1 = s$};
        \draw[->, thick] (2.5,2.9) -- (3.7,2.9) node[right] {\huge $k_l(s-0)$};
    \end{scope}

    % --- Etichette Azzurre (Smorzatore) ---
    \begin{scope}[cyan!70!blue]
        \draw[->, thick] (2.5,-1.1) -- (3.7,-1.1) node[right] {\huge $q_l(s-0)$};
        \draw[<-|] (0.5,-1.6) -- (2.5,-1.6);
        \node[below] at (2.5,-1.6) {\huge $s_1 = s$};
    \end{scope}

\end{tikzpicture}

\end{document}
```

Adottando i riferimenti evidenziati in figura troviamo:
$$
F(t) -k_{l}s(t) -q_{l}\dot{s}(t) = M\ddot{s}(t)
$$
Chiamiamo:
$$
\begin{align*}
x_{1}(t) := s(t) & & x_{2}(t) := \dot{s}(t) & & u_{1}(t) = F(t)
\end{align*}
$$
Per cui le due equazioni che descrivono il sistema sono:
$$
\begin{cases}
\dot{x}_{1}(t) = x_{2}(t) \\ \\
\dot{x}_{2}(t) = -\frac{k_{l}}{M}x_{1}(t) -\frac{q_{l}}{M}x_{2}(t) + \frac{1}{M}u_{1}(t)
\end{cases}
$$
Che scriviamo nella forma compatta $\dot{x}(t)=Ax(t)+Bu_{1}(t)$ con:
$$
\begin{align*}
A = \begin{bmatrix}
0 & 1 \\
-\frac{k_{l}}{M} & -\frac{q_{l}}{M}
\end{bmatrix} & & B = \begin{bmatrix}
0 \\
\frac{1}{M}
\end{bmatrix}
\end{align*}
$$
## ESEMPIO T2
Vediamo ora un sistema più complesso, come in figura:

```tikz
\usepackage{tikz}
\usetikzlibrary{decorations.pathmorphing, arrows.meta}

\begin{document}

\begin{tikzpicture}[>=Stealth, font=\large]

    % --- Parete e Pavimento ---
    \draw[ultra thick] (0,3.5) -- (0,0) -- (10,0);
    \node[below left] at (0,0) {\huge $s_0 = 0$};

    % --- Masse M1 e M2 ---
    \definecolor{blockgray}{RGB}{220,220,220}
    
    % Massa M1
    \draw[thick, fill=blockgray] (2,0.6) rectangle (4.5,2.4);
    \node at (3.25,1.5) {\huge $M_1$};
    \draw[thick, fill=white] (2.6,0.3) circle (0.3);
    \draw[thick, fill=white] (3.9,0.3) circle (0.3);

    % Massa M2
    \draw[thick, fill=blockgray] (6.5,0.6) rectangle (9,2.4);
    \node at (7.75,1.5) {\huge $M_2$};
    \draw[thick, fill=white] (7.1,0.3) circle (0.3);
    \draw[thick, fill=white] (8.4,0.3) circle (0.3);

    % --- Collegamenti Sinistra (Molla kl1) ---
    \draw[thick] (0,1.5) -- (0.3,1.5);
    \draw[thick, decorate, decoration={coil, aspect=0.7, segment length=5pt, amplitude=7pt}] 
        (0.3,1.5) -- (1.7,1.5);
    \draw[thick] (1.7,1.5) -- (2,1.5);
    \node[above] at (0.8,1.7) {$k_{l1}$};

    % --- Collegamenti tra M1 e M2 ---
    % Molla kl2
    \draw[thick] (4.5,1.9) -- (5,1.9);
    \draw[thick, decorate, decoration={coil, aspect=0.7, segment length=4pt, amplitude=6pt}] 
        (5,1.9) -- (6,1.9);
    \draw[thick] (6,1.9) -- (6.5,1.9);
    \node[above] at (5.5,2.3) {$k_{l2}, q_{l2}$};

    % Smorzatore ql2
    \draw[thick] (4.5,0.9) -- (5,0.9);
    \draw[thick] (5,0.7) -- (5,1.1) -- (5.8,1.1);
    \draw[thick] (5,0.7) -- (5.8,0.7);
    \draw[thick, fill=gray!50] (5.4,0.8) rectangle (5.6,1.0); % Pistone
    \draw[thick] (5.6,0.9) -- (6.5,0.9);

    % --- Forza Esterna F ---
    \draw[<- , thick] (9,1.7) -- (10.5,1.7);
    \node[above] at (9.7,1.7) {$F$};

    % --- Riferimenti spostamento s1 e s2 ---
    % s1
    \draw[dashed] (3.25,0.6) -- (3.25,-0.5);
    \draw[<-|, thick] (2.2,-0.4) -- (3.75,-0.4) node[pos=0, below] {$s_1$};
    % s2
    \draw[dashed] (7.75,0.6) -- (7.75,-0.5);
    \draw[<-|, thick] (6.5,-0.4) -- (8.25,-0.4) node[pos=0, below] {$s_2$};

    % --- Etichette Arancioni (Molla) ---
    \begin{scope}[orange!80!black]
        % Forza kl1
        \draw[->, thick] (2,3.8) -- (3.2,3.8) node[above left] {$k_{l1}s_1$};
        \draw[<-|, thick] (0.6,2.3) -- (1.95,2.3);
        
        % Forze kl2 (frecce opposte)
        \draw[<-, thick] (3.2,3) -- (4.5,3);
        \node[above] at (4,3) {$k_{l2}(s_2-s_1)$};

        \draw[->, thick] (6.5,3) -- (7.8,3);
        \node[above] at (7.2,3) {$k_{l2}(s_2-s_1)$};
    \end{scope}

    % --- Etichette Azzurre (Smorzatore) ---
    \begin{scope}[cyan!70!blue]
        \draw[<-, thick] (3.2,-1.2) -- (4.5,-1.2);
        \node[below] at (4,-1.2) {$q_{l2}(\dot{s}_2-\dot{s}_1)$};

        \draw[->, thick] (6.5,-1.2) -- (7.8,-1.2);
        \node[below] at (7.2,-1.2) {$q_{l2}(\dot{s}_2-\dot{s}_1)$};
    \end{scope}

\end{tikzpicture}

\end{document}
```

Con i riferimenti adottati, abbiamo:
$$
\begin{align*}
M_{1}\ddot{s}_{1}(t) &= -k_{l1}s_{1}(t) +k_{l2}(s_{2}(t) -s_{1}(t)) +q_{l2}(\dot{s}_{2}(t)-\dot{s}_{1}(t)) \\ \\
M_{2}\ddot{s}_{2}(t) &= F(t) -k_{l2}(s_{2}(t) -s_{1}(t)) -q_{l2}(\dot{s}_{2}(t)-\dot{s}_{1}(t))
\end{align*}
$$
Scegliamo le *variabili di stato* e l'*ingresso esterno* (variabile indipendente):
$$
\begin{align*}
x_{1}(t) = s_{1}(t) & & x_{2}(t) = \dot{s}_{1}(t) & & x_{3}(t) = s_{2}(t) & & x_{4}(t) = \dot{s}_{2}(t) & & u_{1}(t) = F(t)
\end{align*}
$$
>[!note] NOTA
>Le variabili sono **scelte in ordine**.

Per cui le equazioni che descrivono il sistema sono:
$$
\begin{cases}
\dot{x}_{1}(t) = x_{2}(t) \\ \\
\dot{x}_{3}(t) = x_{4}(t) \\ \\
\dot{x}_{2}(t) = -\frac{k_{l1}+k_{l2}}{M_{1}}x_{1}(t) -\frac{q_{l2}}{M_{1}}x_{2}(t) +\frac{k_{l2}}{M_{1}}x_{3}(t) + \frac{q_{l2}}{M_{1}}x_{4}(t) \\ \\
\dot{x}_{4}(t) = \frac{k_{l2}}{M_{2}}x_{1}(t) +\frac{q_{l2}}{M_{2}}x_{2}(t) -\frac{k_{l2}}{M_{2}}x_{3}(t) -\frac{q_{l2}}{M_{2}}x_{4}(t) +\frac{1}{M_{2}}u_{1}(t)
\end{cases}
$$
Che scriviamo nella forma compatta $\dot{x}(t)=Ax(t)+Bu(t)$ con:
$$
\begin{align*}
A = \begin{bmatrix}
0 & 1 & 0 & 0 \\
-\frac{k_{l1}+k_{l2}}{M_{1}}  & -\frac{q_{l2}}{M_{1}} & \frac{k_{l2}}{M_{1}} & \frac{q_{l2}}{M_{1}} \\
0 & 0 & 0 & 1 \\
\frac{k_{l2}}{M_{2}} & \frac{q_{l2}}{M_{2}} & -\frac{k_{l2}}{M_{2}} & -\frac{q_{l2}}{M_{2}}
\end{bmatrix} & & B = \begin{bmatrix}
0 \\
0 \\
0 \\
\frac{1}{M_{2}}
\end{bmatrix}
\end{align*}
$$
## ESEMPIO T3
Consideriamo infine una *sospensione meccanica* di una vettura, come quella in figura.

>[!note] NOTA
>Abbiamo $M_{1}<M_{2}=\frac{1}{4}M$ dove $M$ è la *massa del veicolo* ed $M_{1}$ è la *massa della ruota* (modello a quarto di veicolo).

```tikz
\usepackage{tikz}
\usetikzlibrary{decorations.pathmorphing, arrows.meta}

\begin{document}

\begin{tikzpicture}[>=Stealth, font=\large]

    % --- Suolo (Profilo stradale irregolare) ---
    \draw[ultra thick] (0.5,0) .. controls (1.5,0.2) and (2.5,-0.2) .. (3.5,0.1) .. controls (4.5,0.3) and (5.5,0) .. (6.5,0);
    \filldraw (3,0.12) circle (2.5pt); % Punto di contatto

    % --- Masse M1 e M2 ---
    \definecolor{blockgray}{RGB}{220,220,220}
    
    % Massa M1
    \draw[thick, fill=blockgray] (2,1.8) rectangle (4,3);
    \node at (3,2.4) {\huge $M_1$};

    % Massa M2
    \draw[thick, fill=blockgray] (1.8,5.5) rectangle (4.2,7);
    \node at (3,6.25) {\huge $M_2$};

    % --- Collegamenti Verticali ---
    % Molla kl1 (tra suolo e M1)
    \draw[thick, decorate, decoration={coil, aspect=0.7, segment length=6pt, amplitude=8pt}] 
        (3,0.12) -- (3,1.8);
    \node[right=5pt] at (3,1) {$k_{l1}$};

    % Molla kl2 (tra M1 e M2)
    \draw[thick, decorate, decoration={coil, aspect=0.7, segment length=5pt, amplitude=7pt}] 
        (3.6,3) -- (3.6,5.5);
    \node[right=5pt] at (3.6,4.25) {$k_{l2}$};

    % Smorzatore ql2 (tra M1 e M2)
    \draw[thick] (2.7,3) -- (2.7,3.8); % Asta inferiore
    \draw[thick] (2.4,3.8) -- (3,3.8); % Base cilindro
    \draw[thick] (2.4,3.8) -- (2.4,4.8); % Parete SX
    \draw[thick] (3,3.8) -- (3,4.8); % Parete DX
    \draw[thick, fill=gray!50] (2.5,4.3) rectangle (2.9,4.5); % Pistone
    \draw[thick] (2.7,4.5) -- (2.7,5.5); % Asta superiore
    \node[left=5pt] at (2.4,4.3) {$q_{l2}$};

    % --- Riferimenti Spostamento (Verticali) ---
    % u1 (Suolo)
    \draw[|->, thick] (5.5,-0.2) -- (5.5,1.2) node[right] {$u_1$};
    
    % s1 (M1)
    \draw[dashed] (4,2.4) -- (5.5,2.4);
    \draw[|->, thick] (5.5,2) -- (5.5,3.2) node[right] {$s_1$};

    % s2 (M2)
    \draw[dashed] (4.5,6.25) -- (5.5,6.25);
    \draw[|->, thick] (5.5,6) -- (5.5,7.2) node[right] {$s_2$};

    % --- Forze Arancioni (Elastiche) ---
    \begin{scope}[orange!80!black]
        % Forza kl1 (su M1 e suolo)
        \draw[->, thick] (7.2,-0.4) -- (7.2,0.4) node[pos=0, right] {};
        \draw[<-, thick] (7.2,1.8) -- (7.2,2.8) node[pos=0, right] {};
        \node at (7.3,0) [right] {$k_{l1}(s_1 - u_1)$};
        \node at (7.3,2.3) [right] {$k_{l1}(s_1 - u_1)$};

        % Forza kl2 (su M2 e M1)
        \draw[<-, thick] (7,5.5) -- (7,6.5) node[right] {$k_{l2}(s_2 - s_1)$};
        \draw[->, thick] (7,2.6) -- (7,3.6) node[right] {$k_{l2}(s_2 - s_1)$};
    \end{scope}

    % --- Forze Azzurre (Viscose) ---
    \begin{scope}[cyan!70!blue]
        % Forza ql2 (su M2 e M1)
        \draw[->, thick] (1.5,5.8) -- (1.5,4.8) node[left] {$q_{l2}(\dot{s}_2 - \dot{s}_1)$};
        \draw[->, thick] (1.5,3.2) -- (1.5,4.2) node[left] {$q_{l2}(\dot{s}_2 - \dot{s}_1)$};
    \end{scope}

\end{tikzpicture}

\end{document}
```

>[!note] NOTA
>Il disturbo $u_{1}(t)$ è l'andamento del *profilo della strada*.

Qui abbiamo:
$$
\begin{align*}
M_{1}\ddot{s}_{1}(t) &= -k_{l1}[s_{1}(t)-u_{1}(t)] +k_{l2}[s_{2}(t)-s_{1}(t)] + q_{l2}[\dot{s}_{2}(t)-\dot{s}_{1}(t)] \\ \\
M_{2}\ddot{s}_{2}(t) &= -k_{l2}[s_{2}(t)-s_{1}(t)] -q_{l2}[\dot{s}_{2}(t)-\dot{s}_{1}(t)]
\end{align*}
$$
Ancora una volta scegliamo:
$$
\begin{align*}
x_{1}(t) = s_{1}(t) & & x_{2}(t) = \dot{s}_{1}(t) & & x_{3}(t) = s_{2}(t) & & x_{4}(t) = \dot{s}_{2}(t)
\end{align*}
$$
Per cui le equazioni che governano il sistema sono:
$$
\begin{cases}
\dot{x}_{1}(t) = x_{2}(t) \\ \\
\dot{x}_{3}(t) = x_{4}(t) \\ \\
\dot{x}_{2}(t) = -\frac{k_{l1}+k_{l2}}{M_{1}}x_{1}(t) -\frac{q_{l2}}{M_{1}}x_{2}(t) +\frac{k_{l2}}{M_{1}}x_{3}(t) +\frac{q_{l2}}{M_{1}}x_{4}(t) +\frac{k_{l1}}{m_{1}}u_{1}(t) \\ \\
\dot{x}_{4}(t) = \frac{k_{l2}}{M_{2}}x_{1}(t) + \frac{q_{l2}}{M_{2}}x_{2}(t) -\frac{k_{l2}}{M_{2}}x_{3}(t) -\frac{q_{l2}}{M_{2}}x_{4}(t)
\end{cases}
$$
Che scriviamo nella forma $\dot{x}(t)=Ax(t)+Bu(t)$ con:
$$
\begin{align*}
A = \begin{bmatrix}
0 & 1 & 0 & 0 \\
-\frac{k_{l2}+k_{l2}}{M_{1}} & -\frac{q_{l2}}{M_{1}} & \frac{k_{l2}}{M_{1}} & \frac{q_{l2}}{M_{2}} \\
0 & 0 & 0 & 1 \\
\frac{k_{l2}}{M_{2}} & \frac{q_{l2}}{M_{2}} & -\frac{k_{l2}}{M_{2}} & -\frac{q_{l2}}{M_{2}}
\end{bmatrix} & & B = \begin{bmatrix}
0 \\
\frac{k_{l1}}{M_{1}} \\
0 \\
0
\end{bmatrix}
\end{align*}
$$
# SISTEMI MECCANICI ROTATIVI
Ricordiamo i principi della *meccanica rotazionale*:

>[!th] PRIMO PRINCIPIO (INERZIA ROTAZIONALE)
>Un corpo *non soggetto a momenti* (coppie) relativi a forze esterne agenti sul corpo stesso *mantiene il suo stato di moto rotatorio* (cioè continua a ruotare con **velocità angolare costante**).
>$$ \sum_{i}C_{i} = 0 \implies \dot{\theta}(t) = \text{ costante} $$

>[!th] SECONDO PRINCIPIO (TEOREMA DEL MOMENTO ANGOLARE)
>La *somma dei momenti* delle forze applicate a un corpo è uguale alla *variazione del momento angolare* del corpo nell'unità di tempo.
>In particolare, se il *momento d'inerzia* $J$ è *costante*, possiamo scrivere:
>$$ \sum_{i}C_{i} = J \frac{d^2\theta(t)}{dt^2} = J\ddot{\theta}(t) $$
## COMPONENTI E LEGGI COSTITUTIVE
**MASSA ROTANTE** (*coppia torcente*):

```tikz
\usetikzlibrary{shadings, arrows.meta}

\begin{document}
\begin{tikzpicture}[>=Stealth, line width=0.8pt]

  % Parametri geometrici
  \def\r{0.8}     % raggio del cilindro
  \def\l{3.0}     % lunghezza del cilindro
  \def\shaft{1.0}  % lunghezza dei tratti d'asse

  % Asse centrale (linea tratteggiata che attraversa il corpo)
  \draw[dash pattern=on 4pt off 3pt] (-\shaft-0.4, 0) -- (\l+\shaft+0.4, 0);

  % Faccia sinistra (ellisse completa per dare profondità)
  \draw[fill=white] (0,0) ellipse (0.25 and \r);

  % Corpo cilindrico con sfumatura
  \shade[left color=gray!20, right color=gray!60, middle color=gray!30] 
    (0, \r) -- (\l, \r) arc (90:-90:0.25 and \r) -- (0, -\r) arc (-90:90:0.25 and \r);
  
  % Bordi del corpo
  \draw (0, \r) -- (\l, \r);
  \draw (0, -\r) -- (\l, -\r);
  
  % Faccia destra (chiusura convessa)
  \draw (\l, \r) arc (90:-90:0.25 and \r);

  % Etichetta J (Momento d'inerzia)
  \node at (\l/2, 0) {$J$};

  % --- Elementi di sinistra (Coppia C) ---
  \draw (-\shaft, 0) -- (0,0); % Tratto d'asse
  % Freccia curva per C
  \draw[->] (-1.4, -0.3) arc (-140:140:0.3 and 0.6);
  \node[below=0.5cm] at (-1.4, 0) {$C$};

  % --- Elementi di destra (Angolo theta) ---
  \draw (\l, 0) -- (\l+\shaft, 0); % Tratto d'asse
  % Freccia curva per theta
  \draw[->] (\l+\shaft+0.2, -0.3) arc (-140:140:0.3 and 0.6);
  \node[below=0.5cm] at (\l+\shaft+0.2, 0) {$\theta$};

\end{tikzpicture}
\end{document}
```

$$
C(t) = J \ddot{\theta}(t)
$$
**MOLLA TORSIONALE** (*coppia elastica*):

```tikz
\usetikzlibrary{decorations.pathmorphing, arrows.meta, calc}

\begin{document}
\begin{tikzpicture}[>=Stealth, line width=0.8pt]

  % --- Parametri di posizione ---
  \coordinate (O) at (0,0);      % Inizio molla
  \coordinate (E) at (3,0);      % Fine molla
  \def\shaft{1.2}                % Lunghezza tratti d'asse

  % --- Asse centrale tratteggiato ---
  \draw[dash pattern=on 4pt off 3pt, gray] ($ (O) - (\shaft + 0.5, 0) $) -- ($ (E) + (\shaft + 0.5, 0) $);

  % --- Tratti d'asse solidi ---
  \draw ($ (O) - (\shaft, 0) $) -- (O);
  \draw (E) -- ($ (E) + (\shaft, 0) $);

  % --- La Molla (Stile Coil) ---
  \draw[decorate, decoration={coil, aspect=0.7, segment length=6pt, amplitude=8pt}] 
    (O) -- (E);

  % --- Elementi di SINISTRA ---
  % Coppia C (Esterna, punta verso il basso)
  \draw[<-] ($ (O) - (\shaft + 0.8, 0.4) $) arc (-150:150:0.3 and 0.6);
  \node[left=0.3cm] at ($ (O) - (\shaft + 0.8, 0) $) {$C$};

  % Angolo Theta 1 (Interna, punta verso l'alto)
  \draw[->] ($ (O) - (\shaft - 0.4, 0.3) $) arc (-150:150:0.25 and 0.5);
  \node[below=0.6cm] at ($ (O) - (\shaft - 0.4, 0) $) {$\theta_1$};

  % --- Elementi di DESTRA ---
  % Angolo Theta 2 (Interna, punta verso l'alto)
  \draw[->] ($ (E) + (\shaft - 0.4, -0.3) $) arc (-150:150:0.25 and 0.5);
  \node[below=0.6cm] at ($ (E) + (\shaft - 0.4, 0) $) {$\theta_2$};

  % Coppia C (Esterna, punta verso l'alto)
  \draw[->] ($ (E) + (\shaft + 0.8, -0.4) $) arc (-150:150:0.3 and 0.6);
  \node[right=0.3cm] at ($ (E) + (\shaft + 0.8, 0) $) {$C$};

\end{tikzpicture}
\end{document}
```

$$
\begin{align*}
C(t) &= k_{r}[\theta_{2}(t)-\theta_{1}(t)] \\ \\
&= -k_{r}[\theta_{1}(t)-\theta_{2}(t)]
\end{align*} 
$$
**SMORZATORE TORSIONALE** (*coppia di attrito viscoso*):

```tikz
\usetikzlibrary{arrows.meta, calc}

\begin{document}
\begin{tikzpicture}[>=Stealth, line width=0.8pt]

  % --- Parametri ---
  \def\r{0.7}      % Raggio/altezza del corpo
  \def\w{0.3}      % Larghezza del rotore grigio
  \def\gap{0.15}   % Spazio tra rotore e involucro
  \def\shaftL{1.2} % Lunghezza tratti d'asse

  % --- Asse centrale tratteggiato (Sfondo) ---
  \draw[dash pattern=on 4pt off 3pt, gray!60] (-3,0) -- (4,0);

  % --- TRATTI D'ASSE SOLIDI ---
  \draw (-1.5,0) -- (-0.2,0); % Asse sinistra
  \draw (0.5,0) -- (2.5,0);   % Asse destra

  % --- SMORZATORE (PARTE CENTRALE) ---
  % Involucro esterno (la "U" rovesciata laterale)
  \draw[line width=1pt] (-0.2, \r) -- (0.5, \r) -- (0.5, -\r) -- (-0.2, -\r);
  \draw[line width=1pt] (-0.2, 0.2) -- (-0.2, \r);   % Tratto verticale superiore sx
  \draw[line width=1pt] (-0.2, -0.2) -- (-0.2, -\r); % Tratto verticale inferiore sx

  % Rotore interno (Rettangolo grigio)
  \filldraw[fill=gray!30, draw=black] (0.05, -\r+0.15) rectangle (0.25, \r-0.15);

  % --- ETICHETTA qr o dr ---
  \node[anchor=south] at (0.15, \r) {$q_r$};

  % --- ELEMENTI DI SINISTRA ---
  % Coppia C (Esterna)
  \draw[<-] (-2.2, -0.3) arc (-150:150:0.3 and 0.6);
  \node[left=0.3cm] at (-2.2, 0) {$C$};

  % Angolo Theta 1 (Interna)
  \draw[->] (-1.0, -0.3) arc (-150:150:0.25 and 0.5);
  \node[below=0.6cm] at (-1.0, 0) {$\theta_1$};

  % --- ELEMENTI DI DESTRA ---
  % Angolo Theta 2 (Interna)
  \draw[->] (1.3, -0.3) arc (-150:150:0.25 and 0.5);
  \node[below=0.6cm] at (2.1, 0) {$\theta_2$};

  % Coppia C (Esterna)
  \draw[->] (2.5, -0.3) arc (-150:150:0.3 and 0.6);
  \node[right=0.3cm] at (3.3, 0) {$C$};

\end{tikzpicture}
\end{document}
```

$$
\begin{align*}
C(t) &= q_{r}[\dot{\theta}_{2}(t)-\dot{\theta}_{1}(t)] \\ \\
&= -q_{r}[\dot{\theta}_{1}(t)-\dot{\theta}_{2}(t)]
\end{align*}
$$

Per definire il modello di stato si devono *definire opportunamente* le *variabili indipendenti* (ingressi e disturbi) e le *variabili di stato*.

>[!note] NOTA
>Solitamente si considerano come *variabili di stato* le *posizioni angolari* e le *velocità angolari delle masse*, relativamente alle posizioni di riposo.
## ESEMPIO R1
Vediamo un esempio costituito da una *molla torsionale* e da una *massa rotante*:

```tikz
\usetikzlibrary{decorations.pathmorphing, arrows.meta, calc, patterns}

\begin{document}
\begin{tikzpicture}[>=Stealth, line width=0.8pt]

  % --- Parametri ---
  \def\r{0.7}      % Raggio del rotore
  \def\w{1.2}      % Larghezza del rotore
  \def\shaft{1.0}  % Lunghezza tratti d'asse

  % --- 1. Vincolo Fisso (Muro) ---
  \draw[line width=1.2pt] (-1.5, -1.2) -- (-1.5, 1.2);
  \fill[pattern=north east lines] (-1.8, -1.2) rectangle (-1.5, 1.2);

  % --- 2. Asse e Molla Torsionale ---
  \draw (-1.5, 0) -- (-0.5, 0); % Primo tratto asse
  
  \draw[decorate, decoration={coil, aspect=0.7, segment length=6pt, amplitude=8pt}] 
    (-0.5, 0) -- (1.5, 0);
  \node at (0.5, 1.0) {$k_r$}; % Etichetta molla

  \draw (1.5, 0) -- (2.5, 0); % Tratto asse tra molla e rotore

  % --- 3. Rotore J ---
  % Faccia sinistra (ellisse)
  \draw[fill=white] (2.5, 0) ellipse (0.2 and \r);
  % Corpo cilindrico
  \shade[left color=gray!20, right color=gray!60, middle color=gray!30] 
    (2.5, \r) -- (2.5+\w, \r) arc (90:-90:0.2 and \r) -- (2.5, -\r) arc (-90:90:0.2 and \r);
  \draw (2.5, \r) -- (2.5+\w, \r);
  \draw (2.5, -\r) -- (2.5+\w, -\r);
  % Faccia destra (arco)
  \draw (2.5+\w, \r) arc (90:-90:0.2 and \r);
  % Etichetta J
  \node at (2.5+\w/2, 0) {$J$};

  % --- 4. Variabili e Frecce ---
  % Freccia Rossa (Coppia elastica)
  \draw[->, red, line width=1.2pt] (1.8, 0.6) arc (120:240:0.4 and 0.7);

  % Angolo Theta (Nero)
  \draw[->] (2.0, -0.4) arc (-150:150:0.25 and 0.5);
  \node[below=0.6cm] at (2.0, 0) {$\theta$};

  % Coppia C (Esterna)
  \draw[->] (4.5, -0.3) arc (-150:150:0.25 and 0.5);
  \node[right=0.2cm] at (4, 0) {$C$};

\end{tikzpicture}
\end{document}
```

Il sistema è descritto da:
$$
J \ddot{\theta}(t) = C(t) -k_{r}\theta(t)
$$
Scegliamo:
$$
\begin{align*}
x_{1}(t) = \theta(t) & & x_{2}(t) = \dot{\theta}(t) & & u_{1}(t) = C(t)
\end{align*}
$$
Per cui le due equazioni che ricaviamo sono:
$$
\begin{cases}
\dot{x}_{1}(t) = x_{2}(t) \\ \\
\dot{x}_{2}(t) = -\frac{k_{r}}{J}x_{1}(t) + \frac{1}{J}u_{1}(t)
\end{cases}
$$
Che scriviamo nella forma $\dot{x}(t)=Ax(t)+Bu(t)$ con:
$$
\begin{align*}
A = \begin{bmatrix}
0 & 1 \\
-\frac{k_{r}}{J} & 0
\end{bmatrix} & & B = \begin{bmatrix}
0 \\
\frac{1}{J}
\end{bmatrix}
\end{align*}
$$
## ESEMPIO R2
Consideriamo ora un esempio leggermente più complesso, come il *puntamento di un radar metereologico*:

```tikz
\usetikzlibrary{arrows.meta, bending, calc, shapes.geometric}

\begin{document}
\begin{tikzpicture}[>=Stealth, line width=0.8pt]

    % --- DEFINIZIONE VARIABILI ---
    \def\angle{30} % Angolo theta
    
    % --- BASE FISSA ---
    \draw[fill=gray!20] (-0.4,0) rectangle (0.4,3);
    \draw (0,0) -- (0,3); % Linea centrale opzionale o asse
    \draw[line width=1.2pt] (-1.5,0) -- (1.5,0); % Terreno

    % --- SNODO E SUPPORTO ROTANTE ---
    \begin{scope}[shift={(0,3.3)}]
        % Supporto a "U" della base
        \draw[fill=white] (-0.2,-0.3) rectangle (0.2,0);
        
        % Inizio rotazione coordinata per la parte mobile
        \begin{scope}[rotate=\angle]
            
            % Linea tratteggiata asse antenna
            \draw[dashed] (-2.5,0) -- (1.5,0);
            
            % Cilindro/Attuatore posteriore
            \draw[fill=gray!40] (-1.2,-0.25) rectangle (0.3,0.25);
            
            % Perno centrale
            \fill (0,0) circle (2pt);
            
            % Antenna Parabolica (Disegnata come arco)
            \draw[fill=gray!30] (0.8,-1.5) arc (-160:-200:4.5) -- cycle;
            
            % Freccia rossa (direzione movimento/coppia)
            \draw[red, very thick, ->] (-0.8,-0.5) arc (-150:-210:1);
            
            % Freccia curva per la Coppia (Cm + Cv)
            \draw[->, very thick] (0.5,0) arc (0:260:0.4);
            \node at (-0.5, 1.1) {$C_M(t) + C_V(t)$};
            
        \end{scope}
        
        % --- ANGOLO THETA ---
        \draw[dashed] (-2.5,0) -- (-0.5,0);
        \draw[->] (-2,0) arc (180:180+\angle:2);
        \node at (-2.3, 0.3) {$\theta(t)$};
        
    \end{scope}

\end{tikzpicture}
\end{document}
```

Abbiamo in questo caso:
$$
J \ddot{\theta}(t) = C_{M}(t) + C_{V}(t) -q_{r}\dot{\theta}(t)
$$

>[!note] NOTA
>- $C_{M}$ è la *coppia motrice*.
>- $C_{V}$ è la *coppia del vento* (disturbo).
>- Il terzo termine è l'*azione dello smorzatore* che tende a far tornare all'equilibrio l'antenna. 

Scegliamo:
$$
\begin{align*}
x_{1}(t) = \theta(t) & & x_{2}(t) = \dot{\theta}(t) & & u_{1}(t) = C_{M}(t) & & u_{2}(t) = C_{V}(t)
\end{align*}
$$
Per cui abbiamo:
$$
\begin{cases}
\dot{x}_{1}(t) = x_{2}(t) \\
\dot{x}_{2}(t) = -\frac{q_{r}}{J}x_{2}(t) +\frac{1}{J}u_{1}(t) +\frac{1}{J}u_{2}(t)
\end{cases}
$$
Che scriviamo nella forma $\dot{x}(t)=Ax(t)+Bu(t)$ con:
$$
\begin{align*}
A = \begin{bmatrix}
0 & 1 \\
0 & -\frac{q_{r}}{J}
\end{bmatrix} & & B = \begin{bmatrix}
0 & 0 \\
\frac{1}{J} & \frac{1}{J}
\end{bmatrix}
\end{align*}
$$
# SISTEMI ELETTROMECCANICI
Nei sistemi *elettromeccanici* abbiamo *sia elementi meccanici che elementi circuitali*.
Vediamo direttamente un esempio.
## ESEMPIO EM1
Cominciamo con un semplice *motore elettrico*, come in figura:

```tikz
\usepackage{circuitikz}

\begin{document}

\begin{circuitikz}[american]
    % Circuito principale
    \draw (0,0) 
        to[V, l=$V(t)$, invert] (0,3) % Generatore di tensione d'ingresso
        to[R, l=$R$] (2.5,3)           % Resistenza rotorica
        to[L, l=$L$] (5,3)             % Induttanza rotorica
        -- (6,3)
        to[dcvsource] (6,0) % Forza contro-elettromotrice
        -- (0,0);
        
    % Connessione a terra
    \draw (3,0) to[ground] (3,0);

    % Freccia della corrente circolare (i(t))
    \draw[thin, <-, >=stealth, color=gray] (3,1.5) 
        node[right] {$i(t)$} 
        arc (0:330:0.8);

    % Rappresentazione dell'albero motore (lato destro)
    \draw[thick] (6.4,1.5) -- (7.2,1.5); % Albero
    \draw[->, >=stealth] (6.8,1.25) arc (200:120:0.5) node[midway, above=4pt] {$\theta(t)$};

\end{circuitikz}

\end{document}
```

>[!note] NOTA
>Il simbolo a destra rappresenta un *collegamento ad un albero motore* con momento di inerzia $J$ e tensione associata $v_{me}(t)$

Dalle *legge delle tensioni* troviamo:
$$
V(t) = Ri(t) + L \frac{di}{dt} +v_{me}(t)
$$
Dal *bilancio delle coppie sull'albero motore* troviamo:
$$
J \ddot{\theta}(t) = -q_{r}\dot{\theta}(t) +C_{em}(t)
$$
>[!note] NOTA
>- $v_{me}(t)$: **forza contro elettromotrice**. E' la tensione indotta che si genera nel circuito quando il motore è in rotazione. In particolare: $v_{me}(t)=h\dot{\theta}(t)$
>- $C_{em}(t)$: **coppia motrice**. E' la coppia torcente che si genera quando le spire percorse da corrente del rotore attraversano il campo magnetico presente nel motore. In particolare $C_{me}(t)=hi(t)$

Scegliamo quindi:
$$
\begin{align*}
x_{1}(t) = i(t) & & x_{2}(t) = \dot{\theta}(t) & & x_{3}(t) = \theta(t) & & u_{1}(t) = V(t)
\end{align*}
$$
Troviamo quindi le seguenti equazioni:
$$
\begin{cases}
\dot{x}_{1}(t) = -\frac{R}{L}x_{1}(t) -\frac{h}{L}x_{2}(t) +\frac{1}{L}u_{1}(t) \\ \\
\dot{x}_{2}(t) = +\frac{h}{J}x_{1}(t) -\frac{q_{r}}{J}x_{2}(t) \\ \\
\dot{x}_{3}(t) = x_{2}(t)
\end{cases}
$$
Che scriviamo nella forma $\dot{x}(t)=Ax(t)+Bu(t)$ con:
$$
\begin{align*}
\begin{bmatrix}
-\frac{R}{L} & -\frac{h}{L} & 0 \\
\frac{h}{J} & -\frac{q_{r}}{J} & 0 \\
0 & 1 & 0
\end{bmatrix} & & B = \begin{bmatrix}
\frac{1}{L} \\
0 \\
0 
\end{bmatrix}
\end{align*}
$$
