# INDICE SEZIONE
- [ ] [[#TRASFORMATE FUNZIONALI]]
      - [[#FUNZIONI DI VARIABILI COMPLESSE]]
- [ ] [[#TRASFORMATA DI LAPLACE]]
      - [[#NOTE E OSSERVAZIONI]]
      - [[#CONVERGENZA]]
      - [[#APPROCCIO OPERATIVO E PROPRIETA']]
- [ ] [[#ANTITRASFORMATA]]
      - [[#CASO POLI SEMPLICI]]
      - [[#ESEMPIO POLI SEMPLICI REALI]]
      - [[#ESEMPIO POLI SEMPLICI COMPLESSI]]
      - [[#ANDAMENTI CASO POLI SEMPLICI]]
      - [[#CASO POLI MULTIPLI]]
      - [[#ANDAMENTI CASO POLI MULTIPLI]]
# TRASFORMATE FUNZIONALI
Per la soluzione di *equazioni differenziali* sono di notevole utilità le **trasformazioni funzionali**, cioè trasformazioni che **associano funzioni a funzioni** e che possono *semplificare il problema*.

Le trasformazioni funzionali stabiliscono una *corrispondenza biunivoca* fra **funzioni oggetto** (normalmente funzioni del *tempo*) e **funzioni immagine** di *diversa natura*.

>[!idea] MOTIVAZIONE PER L'UTILIZZO
>Le operazioni eseguite sulle *funzioni oggetto* corrispondono ad *operazioni più semplici sulle funzioni immagine*.
>In tal modo, al **problema oggetto**, associamo un **problema immagine più facile**.

>[!note] NOTA
>Dalla *soluzione immagine* si *passa* poi alla *soluzione oggetto* eseguendo sulle *funzioni immagine* l'operazione **anti-trasformazione** o *trasformazione inversa*.

```tikz
\usetikzlibrary{positioning, shadows, arrows.meta}

\begin{document}
\begin{tikzpicture}[
    % Distanze tra i blocchi
    node distance=2.5cm and 3.5cm,
    % Stile per i riquadri
    box/.style={
        rectangle,
        draw=black,
        fill=white,
        thick,
        minimum width=3.5cm,
        minimum height=2.2cm,
        align=center,
        drop shadow={opacity=0.4, shadow xshift=3pt, shadow yshift=-3pt},
        text=red!70!black, % Rosso scuro simile all'originale
        font=\large
    },
    % Stile per le frecce continue
    arr/.style={
        thick,
        -Latex
    },
    % Stile per la freccia punteggiata
    darr/.style={
        thick,
        densely dotted,
        -Latex
    }
]

% --- DEFINIZIONE DEI NODI (I RIQUADRI) ---
\node[box] (Pobj) {Problema\\oggetto};
\node[box, below=of Pobj] (Pimg) {Problema\\immagine};
\node[box, right=of Pobj] (Sobj) {Soluzione\\oggetto};
\node[box, right=of Pimg] (Simg) {Soluzione\\immagine};


% --- COLLEGAMENTI E ETICHETTE ---

% Freccia superiore (difficile)
\draw[darr] (Pobj) -- 
    node[above, text=red!70!black] {difficile} 
    node[below] {$*$} 
    (Sobj);

% Freccia sinistra (Trasformazione)
\draw[arr] (Pobj) -- 
    node[left, text=red!70!black, align=center, xshift=-2mm] {Trasformazione\\funzionale} 
    node[right, xshift=2mm] {$\mathcal{L}$} 
    (Pimg);

% Freccia inferiore (facile)
\draw[arr] (Pimg) -- 
    % Uso textcolor{black} per l'operatore pallino in modo che resti nero
    node[below, align=center, text=red!70!black, yshift=-2mm] {facile\\[0.5ex]\textcolor{black}{$\circ$}} 
    (Simg);

% Freccia destra (Antitrasformazione)
\draw[arr] (Simg) -- 
    node[left, xshift=-2mm] {$\mathcal{L}^{-1}$} 
    node[right, text=red!70!black, align=center, xshift=2mm] {Trasformazione\\inversa} 
    (Sobj);

\end{tikzpicture}
\end{document}
```

Dati due *spazi di funzioni* $V,W$, e due funzioni $f\in V$ e $F\in W$, una *trasformata funzionale* è una "*mappa*" del tipo:
$$
\mathcal{L} : \begin{cases}
f \mapsto F \\
* \mapsto \circ
\end{cases}
$$

```tikz
\usepackage{amsmath}

\begin{document}

\begin{tikzpicture}[>=latex]

% --- DEFINIZIONE DELLE COORDINATE ---
% Altezze delle righe
\def\ytop{2}   % Riga superiore (In V)
\def\ymid{1}   % Riga centrale (Linea tratteggiata)
\def\ybot{0}   % Riga inferiore (In W)

% Posizioni orizzontali degli elementi
\def\xLabel{-1.5} % Etichette di testo a sinistra
\def\xf{0}        % Colonna f/F
\def\xop{1.5}     % Colonna degli operatori (* e cerchietto)
\def\xg{3}        % Colonna g/G
\def\xeq{4.5}     % Colonna degli uguali
\def\xh{6}        % Colonna h/H

% --- RIGA SUPERIORE: SPAZIO V (Dominio del Tempo) ---
\node[anchor=east, font=\sffamily] at (\xLabel, \ytop) {In V:};
\node at (\xf, \ytop) {\LARGE $f$};
\node at (\xop, \ytop) {\LARGE $*$};
\node at (\xg, \ytop) {\LARGE $g$};
\node at (\xeq, \ytop) {\LARGE $=$};
\node at (\xh, \ytop) {\LARGE $h$};

% --- SEZIONE CENTRALE: TRASFORMATA E FRECCE ---
% Simbolo della Trasformata di Laplace
\node[anchor=east] at (\xLabel, \ymid) {\Large $\mathcal{L}$};

% Linea tratteggiata rossa
\draw[thick, densely dashed, red!80!black] (\xLabel+0.2, \ymid) -- (\xh+0.5, \ymid);

% Frecce verticali rosse
\tikzset{freccia/.style={thick, red!80!black}}
\draw[->, freccia] (\xf, \ytop-0.4) -- (\xf, \ybot+0.4);
\draw[->, freccia] (\xop, \ytop-0.4) -- (\xop, \ybot+0.4);
\draw[->, freccia] (\xg, \ytop-0.4) -- (\xg, \ybot+0.4);
% Freccia a doppia punta per h <-> H
\draw[<->, freccia] (\xh, \ytop-0.4) -- (\xh, \ybot+0.4);

% --- RIGA INFERIORE: SPAZIO W (Dominio della Frequenza) ---
\node[anchor=east, font=\sffamily] at (\xLabel, \ybot) {In W:};
\node at (\xf, \ybot) {\LARGE $F$};
\node at (\xop, \ybot) {\LARGE $\circ$};
\node at (\xg, \ybot) {\LARGE $G$};
\node at (\xeq, \ybot) {\LARGE $=$};
\node at (\xh, \ybot) {\LARGE $H$};

\end{tikzpicture}

\end{document}
```

Per cui la *soluzione oggetto* $h$ sarà data da:
$$
h = f*g = \mathcal{L}^{-1}(F\circ G) = \mathcal{L}^{-1}(H)
$$
>[!note] NOTA
>Le *funzioni* sono mappate **da uno spazio di funzioni a un altro** e **così anche le operazioni** su di esse.
>Dopo l'applicazione della trasformata, l'*operazione immagine* $\circ$ risulta *più facile* rispetto all'operazione originale $*$.  
## FUNZIONI DI VARIABILI COMPLESSE
Una funzione $F$ di *variabile complessa* $s \in \mathbb{C}$ viene assegnata *specificando* le **due funzioni di variabile reale** $u(\sigma,\omega)$ e $v(\sigma,\omega)$ che ne rappresentano, rispettivamente, la **parte reale** e la **parte immaginaria**.

Funzioni di questo tipo stabiliscono quindi una *corrispondenza biunivoca* tra i punti dei due piani: il *piano di Gauss* della *variabile indipendente* $s$ e quello della *variabile dipendente* $w$.
$$
\begin{align*}
s = \sigma + j\omega & &\longmapsto & & w = F(s) = u(\sigma,\omega) + jv(\sigma,\omega) \\ \\
\sigma,\omega \in \mathbb{R}
\end{align*}
$$

```tikz
\usetikzlibrary{arrows.meta,calc}

\begin{document}

\begin{tikzpicture}[
    % Stili generali
    >={Latex[length=3mm]},
    axis/.style={thick,->},
    label/.style={font=\small},
    complex point/.style={mark=x, mark size=2.5pt, thick},
    red label/.style={red, font=\small}
]

% --- PIANO S ---
\begin{scope}[shift={(-4,0)}]
    % Assi
    \draw[axis] (-1,0) -- (3,0) node[below, label] {Re};
    \draw[axis] (0,-1) -- (0,3) node[left, label] {Im};
    \node[anchor=north east, label] at (0,0) {0};

    % Punto s1 e coordinate
    \coordinate (s1) at (1.5, 1);
    \node[complex point, anchor=center] at (s1) {};
    \node[anchor=south, label] at ($(s1)+(0,0.15)$) {$s_1$};

    % Linee tratteggiate alle coordinate
    \draw[dashed, thin] (1.5,0) -- (1.5,1) node[below, label] at (1.5,0) {$\sigma$};
    \draw[dashed, thin] (0,1) -- (1.5,1) node[left, label] at (0,1) {$\omega$};
\end{scope}

% --- FRECCIA DI MAPPATURA ---
% Disegnata tra gli ambiti dei due piani
\draw[thick, ->] (-1.5, 1) to[bend left=30] (2.5, 1);

% --- PIANO W ---
\begin{scope}[shift={(4,0)}]
    % Assi
    \draw[axis] (-1,0) -- (3,0) node[below, label] {Re};
    \draw[axis] (0,-1) -- (0,3) node[left, label] {Im};
    \node[anchor=north east, label] at (0,0) {0};

    % Punto w1 e coordinate
    \coordinate (w1) at (1.8, 1.3);
    \node[complex point, anchor=center] at (w1) {};
    \node[anchor=west, label, inner sep=2pt] at ($(w1)+(0.2,0)$) {$w_1 = F(s_1)$};

    % Linee tratteggiate alle coordinate
    \draw[dashed, thin] (1.8,0) -- (1.8,1.3) node[below, label] at (1.8,0) {$u$};
    \draw[dashed, thin] (0,1.3) -- (1.8,1.3) node[left, label] at (0,1.3) {$v$};
\end{scope}

\end{tikzpicture}

\end{document}
```
# TRASFORMATA DI LAPLACE
Sia $f(t)$ una funzione (reale nei nostri casi di interesse) di variabile reale:
$$
f: \mathbb{R}\to \mathbb{R}
$$
Si definisce **trasformata di Laplace** (*unilatera*) di $f$:

>[!def] TRASFORMATA DI LAPLACE (UNILATERA)
>E' la *funzione* $F$ (*se esiste!*) **complessa** di **variabile complessa** così definita:
>$$ \begin{align*} F: \mathbb{C} & \to \mathbb{C} \\ \\ s = \sigma + j\omega &\mapsto F(s) = \mathcal{L}(f(t)) \end{align*} $$
>Con:
>$$ \mathcal{L}(f(t)) := \int_{0^-}^{+\infty} f(t)e^{ -st }dt $$

>[!note] NOTA
>Abbiamo definito la trasformata **unilatera** perchè relativa (*ristretta*) a **funzioni causali**, nulle per $t<0$.
## NOTE E OSSERVAZIONI
Vediamo alcune note e osservazioni sulla *trasformata di Laplace*:
1. L'integrale è da considerarsi calcolato lungo **qualunque retta parallela all'asse immaginario** *inclusa nel dominio* di $F$.
2. L'intervallo di integrazione $[0^-,+\infty]$ fa riferimento ad una **funzione causale**, relativa ad un sistema *fisicamente realizzabile*.
3. L'**estremo inferiore** $0^-$ si include per considerare per includere eventuali **componenti impulsive**.
4. Il fattore $e^{ -st }$ **rende finito l'integrale**, *anche* per $f(t)$ *divergenti*.
   Se $f(t)$ è *limitata*, la *trasformata esiste certamente*, altrimenti $F(s)$ esiste *solo se* $e^{ -st }$ *tende a 0 più rapidamente* di quanto $f(t)$ tende a $+\infty$.
5. L'esponente $-st$ deve essere **adimensionale**: l'unità di misura di $s$ è *1/secondi* (è una *pulsazione*, cioè una *frequenza complessa*).
6. Essendo l'integrale calcolato tra $0^-$ e $+\infty$ è un **integrale improprio**, e va quindi interpretato come il risultato di una operazione di *passaggio al limite*.
7. Essendo un **integrale definito**, esso *non dipende dalla variabile* $t$, ma solo dal *parametro* $s$.
## CONVERGENZA
Dato l'integrale:
$$
F(s) = \mathcal{L}(f(t)) := \int_{0^-}^{+\infty} f(t) e^{ -st }dt
$$
>[!th] TEOREMA
>Se tale integrale esiste e *converge* per un certo $s_{0}\in \mathbb{C}$, allora **converge per tutti** gli $s \in \mathbb{C}$ tali che:
>$$ \mathrm{Re}(s) > \mathrm{Re}(s_{0}) $$
>

Infatti:
$$
|e^{ -st }| = |e^{ -(\sigma+j\omega)t }| = |e^{ -\sigma t }|\cancelto{ 1 }{ |e^{ -j\omega t }| } = |e^{ -\mathrm{Re}(s)t }|
$$
>[!idea] CONSEGUENZA
>L'**insieme** degli $s \in \mathbb{C}$ per cui l'**integrale converge** è quindi un **semipiano destro** di $\mathbb{C}$.

```tikz
\usepackage{amssymb} % Necessario per il simbolo \nexists

\begin{document}
\begin{tikzpicture}[>=latex, font=\sffamily]

    % Definizione della coordinata dell'ascissa di convergenza
    \def\sigmac{2}

    % 1. Regione di convergenza (Tratteggio manuale senza librerie)
    \begin{scope}
        % Il comando \clip fa in modo che tutto ciò che viene disegnato 
        % dopo di esso in questo blocco (scope) sia visibile solo all'interno del rettangolo.
        \clip (\sigmac, -3) rectangle (5.5, 4);
        
        % Ciclo per disegnare linee parallele inclinate di 45 gradi
        % \i è l'ascissa di partenza della linea. Varia da -7 a 6 con passi di 0.2
        \foreach \i in {-7, -6.8, ..., 6} {
            \draw[gray!70, thin] (\i, -4) -- (\i+9, 5);
        }
    \end{scope}

    % 2. Assi cartesiani
    \draw[->, thick] (-3, 0) -- (6, 0) node[below] {$\sigma$};
    \draw[->, thick] (0, -3) -- (0, 4.5) node[above] {$j\omega$};

    % 3. Retta dell'ascissa di convergenza (Tratteggiata verticale)
    \draw[dashed, thick] (\sigmac, -3) -- (\sigmac, 4);

    % 4. Etichette sugli assi
    \node[below left] at (\sigmac, 0) {$\sigma_c$};

    % 5. Formule di esistenza della trasformata
    % A sinistra della retta (Non esiste)
    \node at (0.8, -1) {\Large $\nexists \mathcal{L}(f)$};
    % A destra della retta (Esiste)
    \node at (3.8, -1) {\Large $\exists \mathcal{L}(f)$};

    % 6. Annotazioni in rosso
    \begin{scope}[red!80!black]
        % "ascissa di convergenza"
        \node[align=left] (ascissa) at (\sigmac+0.5, 4.8) {ascissa di\\convergenza};
        \draw[->, thick] (ascissa.south)++(-0.5,0) -- (\sigmac, 4.05);

        % Freccia laterale per la RdC
        \node[align=left] (rdc_left) at (-1.5, 1.5) {RdC: regione di\\convergenza, $\sigma > \sigma_c$};
        \draw[->, thick] (rdc_left.east) -- (\sigmac+1.5, 1.5);

        % Testo "RdC" interno alla regione
        \node at (\sigmac+1.5, 0.8) {RdC: $\sigma > \sigma_c$};
    \end{scope}

\end{tikzpicture}
\end{document}
```
## APPROCCIO OPERATIVO E PROPRIETA'
Vediamo come trattare la trasformata di Laplace dal *punto di vista operativo*, e alcune proprietà che ne facilitano l'utilizzo.

>[!note] NOTA
>Per $\mathcal{L}$-trasformare una funzione *non occorre* ogni qualvolta *calcolare l'integrale*, in quanto per le funzioni più comuni, le trasformate sono *note e tabulate*.

Per i *segnali canonici*, per esempio, sono note le trasformate seguenti:

```tikz
\usepackage{amsmath} % Necessario per i comandi matematici avanzati

\begin{document}

\begin{tikzpicture}
    % Inseriamo la tabella all'interno di un nodo TikZ
    \node[inner sep=0pt] {
        % Aumentiamo lo spazio verticale delle celle per replicare l'aspetto "arioso" dell'immagine
        \renewcommand{\arraystretch}{2.5}
        
        \begin{tabular}{|l|l|}
            \hline
            Funzione del tempo & Trasformata di Laplace \\
            \hline\hline
            $\delta(t)$ (impulso di Dirac) & $1$ \\
            \hline
            $\delta_{-1}(t)$ (gradino unitario) & $\displaystyle\frac{1}{s}$ \\
            \hline
            $\delta_{-2}(t) = t\delta_{-1}(t)$ (rampa unitaria) & $\displaystyle\frac{1}{s^2}$ \\
            \hline
        \end{tabular}
    };
\end{tikzpicture}

\end{document}
```

Altre trasformate note particolarmente utili sono:

```tikz
\usepackage{amsmath} % Necessario per i comandi matematici avanzati e le frazioni

\begin{document}

\begin{tikzpicture}
    % Inseriamo la tabella all'interno di un nodo TikZ
    \node[inner sep=0pt] {
        % Aumentiamo ulteriormente lo spazio verticale per ospitare le doppie frazioni e le radici
        \renewcommand{\arraystretch}{2.8}
        
        \begin{tabular}{|l|l|}
            \hline
            Funzione del tempo & Trasformata di Laplace \\
            \hline
            $e^{at}$ (esponenziale) & $\displaystyle\frac{1}{s - a}$ \\
            \hline
            $\displaystyle\frac{t^{n-1}}{(n-1)!}e^{at}$ (esponenziale polinomiale) & $\displaystyle\frac{1}{(s - a)^n}$ \\
            \hline
            $\sin(\omega t)$ (sinusoide) & $\displaystyle\frac{\omega}{s^2 + \omega^2}$ \\
            \hline
            $\cos(\omega t)$ (cosinusoide) & $\displaystyle\frac{s}{s^2 + \omega^2}$ \\
            \hline
            $\displaystyle\frac{1}{\omega_n \sqrt{1 - \zeta^2}} e^{-\zeta \omega_n t} \sin(\omega_n \sqrt{1 - \zeta^2} t)$ & $\displaystyle\frac{1}{s^2 + 2\zeta \omega_n s + \omega_n^2}$ (fattore trinomio) \\
            \hline
            $e^{-at} \cos(\omega t)$ & $\displaystyle\frac{s + a}{(s + a)^2 + \omega^2}$ \\
            \hline
            $e^{-at} \sin(\omega t)$ & $\displaystyle\frac{\omega}{(s + a)^2 + \omega^2}$ \\
            \hline
        \end{tabular}
    };
\end{tikzpicture}

\end{document}
```

Valgono inoltre le seguenti proprietà, che possono risultare utili per il calcolo di altre trasformate a partire da quelle note:
1. **UNICITA'**: se esiste $F(s)$ è *unica* e soddisfa $\mathcal{L}[f(t)]=F(s)\implies \mathcal{L}^{-1}[F(s)]=f(t)$.
2. **LINEARITA'**: $\mathcal{L}[\alpha f(t)+\beta g(t)]=\alpha F(s)+\beta G(s)$
3. **PROPRIETA' DELLA DERIVATA**: $\mathcal{L}\left[ \frac{df(t)}{dt} \right] = sF(s)-f(0^-)$
4. **PROPRIETA' INTEGRALE**: $\mathcal{L}\left[ \int_{0}^t f(\tau)d\tau \right]= \frac{F(s)}{s}$
5. **CONVOLUZIONE**: $\mathcal{L}[(f*h)(t)]=F(s)H(s)$
6. **TRASLAZIONE TEMPORALE**: $\mathcal{L}[f(t-a)]=e^{ -as }F(s)$
7. **TRASLAZIONE IN FREQUENZA**: $\mathcal{L}[e^{ at }f(t)]=F(s-a)$
8. **TEOREMA VALORE INIZIALE**: $\lim_{ t \to 0^+ }f(t)=f(0^+)=\lim_{ s \to \infty }sF(s)$
9. **TEOREMA VALORE FINALE**: $\lim_{ t \to \infty }f(t)=\lim_{ s \to 0 }sF(s)$
# ANTITRASFORMATA
Data la funzione $F(s)$, frutto della trasformazione di $f(t)$:
$$
F(s) = \mathcal{L}(f(t)) := \int_{0^-}^{+\infty} f(t)e^{ -st }dt
$$
La trasformata di Laplace **inversa** risulta essere:
$$
f(t) = \mathcal{L}^{-1}[F(s)] = \frac{1}{2\pi j} \int_{\sigma-j\infty}^{\sigma+j\infty} F(s)e^{ st }ds \text{ , }\sigma>\sigma_{c}
$$
>[!note] NOTA
>L'integrazione deve essere *eseguita sul piano complesso* e può risultare piuttosto *laboriosa*.
>Solitamente questo si evita e, ricordando la *corrispondenza biunivoca tra le funzioni oggetto*, si fa uso di tabelle che legano:
>$$ f(t) \Leftrightarrow F(s) $$

>[!idea] APPLICAZIONE
>L'*antitrasformata* va *applicata alla soluzione immagine* per ricavare la *soluzione oggetto* al problema originale.

Come facilmente intuibile dalle tabelle viste in > [[#APPROCCIO OPERATIVO E PROPRIETA']], dopo aver applicato la trasformata di Laplace ci si trova spesso ad avere a che fare con *funzioni razionali*, che dobbiamo "*riconvertire*" nel *dominio originale*.
Per farlo, distinguiamo due casi:
- Fratti con soli poli **semplici**.
- Fratti con **poli multipli** (ripetuti).
## CASO POLI SEMPLICI
In questo caso, la funzione $F(s)$ trovata dopo aver applicato la trasformata è del tipo:
$$
F(s) = \frac{\mathcal{N}(s)}{\mathcal{D}(s)} = \frac{\mathcal{N}(s)}{(s-\lambda_{1})(s-\lambda_{2})\dots(s-\lambda_{n})}
$$
>[!note] NOTA
>Nel caso dei **segnali causali**, abbiamo sempre a che fare con **funzioni razionali proprie**.

Sappiamo che frazioni di questo tipo possono essere scritte come *somme di fratti semplici*:
$$
F(s) = \frac{C_{1}}{s-\lambda_{1}} + \dots + \frac{C_{i}}{s-\lambda_{i}} + \dots + \frac{C_{n}}{s-\lambda_{n}} 
$$
>[!th] TEOREMA DEI RESIDUI
>Il coefficiente $C_{i}$ è il *residuo relativo al polo* $\lambda_{i}$, ed è dato da:
>$$ C_{i} = \lim_{ s \to \lambda_{i} } (s-\lambda_{i})\mathcal{F}(s)  $$ 

>[!note] NOTA
>I *residui* sono:
>- **Reali** in corrispondenza di *poli reali*.
>- **Complessi coniugati** in corrispondenza di *poli complessi coniguati*.

Una volta che abbiamo scritto la *funzione immagine* in tale forma, possiamo sfruttare il seguente risultato:
$$
\mathcal{L}^{-1}\left[ \frac{C_{i}}{s-\lambda_{i}} \right] = C_{i}e^{ \lambda_{i}t }\delta_{-1}(t) \text{ , con } t\geq 0 \text{ , }i=1,\dots,n
$$
>[!idea] CONSEGUENZA
>Possiamo quindi *applicare l'antitrasformata ad ogni termine* per ricavare la *soluzione originale*.

Nel caso di una **coppia di poli complessi coniugati** $\lambda,\lambda^*=\sigma\pm j\omega$ e corrispondenti *coefficienti* $C,C^*=a\pm jb$ abbiamo:
$$
\begin{align*}
Ce^{ \lambda t } + C^*e^{ \lambda^*t } &= (a+jb)e^{ (\sigma+j\omega)t } + (a-jb)e^{ (\sigma-j\omega)t } \\ \\
&= ae^{ \sigma t } 2 \frac{e^{ j\omega t } + e^{ -j\omega t }}{2} + jbe^{ \sigma t } 2j \frac{e^{ j\omega t } - e^{ -j\omega t }}{2j}
\end{align*} 
$$
A questo punto possiamo sfruttare le *formule di Eulero*:
$$
\begin{align*}
\cos\theta = \frac{e^{ j\theta } + e^{ -j\theta }}{2} & & \sin\theta = \frac{e^{ j\theta} - e^{ -j\theta } }{2j}
\end{align*}
$$
Per cui ricaviamo:
$$
\begin{align*}
Ce^{ \lambda t } + C^*e^{ \lambda^*t } &= 2ae^{ \sigma t }\cos\omega t - 2be^{ \sigma t }\sin\omega t \\ \\
&= 2e^{ \sigma t }(a\cos\omega t - b\sin\omega t)
\end{align*} 
$$
Alternativamente, possiamo anche fare il seguente ragionamento:
$$
\begin{align*}
Ce^{ \lambda t } + C^*e^{ \lambda^*t } &= Ce^{ \lambda t } + (Ce^{ \lambda t })^* \\ \\
&= 2 \mathrm{Re}(Ce^{ \lambda t }) = 2\mathrm{Re}(|C|e^{ j \mathrm{Arg}(C) }e^{ \sigma t }e^{ j\omega t }) \\ \\
&= 2|C|e^{ \sigma t } \mathrm{Re}(e^{ j(\mathrm{Arg}(C)+\omega t) }) \\ \\
&= 2|C| e^{ \sigma t }\cos(\omega t + \mathrm{Arg}(C))
\end{align*}
$$
Dove:
$$
\begin{align*}
|C| = \sqrt{ a^2 + b^2 } & & \mathrm{Arg}(C) = \arctan \frac{b}{a}
\end{align*} 
$$
## ESEMPIO POLI SEMPLICI REALI
Consideriamo l'esempio seguente:
$$
F(s) = \frac{s-1}{(s+1)(s-3)} = \frac{r_{1}}{s+1} + \frac{r_{2}}{s-3}
$$
Iniziamo con il *calcolo dei residui*:
$$
\begin{align*}
r_{1} &= \lim_{ s \to -1 } \left[ (s+1) \frac{s-1}{(s+1)(s-3)} \right] = \frac{s-1}{s-3}\bigg|_{s=-1} = \frac{1}{2} \\ \\
r_{2} &= \lim_{ s \to 3 } \left[ (s-3) \frac{s-1}{(s+1)(s-3)} \right] = \frac{s-1}{s+1}\bigg|_{s=3} = \frac{1}{2}
\end{align*}
$$
Allora scriviamo $F(s)$ come:
$$
F(s) = \frac{s-1}{(s+1)(s-3)} = \frac{0.5}{s+1} + \frac{0.5}{s-3}
$$
E allora:
$$
f(t) = \mathcal{L}^{-1}[F(s)] = 0.5e^{ -t } + 0.5e^{ 3t }
$$
## ESEMPIO POLI SEMPLICI COMPLESSI
Consideriamo ora il seguente caso:
$$
F(s) = \frac{s+1}{(s+2)(s^2-2s+2)} = \frac{r_{1}}{s+2} + \frac{r_{2}}{s-(1+j)} + \frac{r_{2}^*}{s-(1-j)}
$$
Allora il calcolo dei residui è il seguente:
$$
\begin{align*}
r_{1} &= \lim_{ s \to -2 } \left[ (s+2) \frac{s+1}{(s+2)(s^2-2s+2)} \right] = \frac{s+1}{s^2-2s+2}\bigg|_{s=-2} = -\frac{1}{10} \\ \\
r_{2} &= \lim_{ s \to 1+j } \left[ (s-(1+j)) \frac{s+1}{(s+2)(s-(1+j))(s-(1-j))}  \right] = \frac{s+1}{(s+2)(s-(1-j))}\bigg|_{s=1+j} = \frac{1}{20}-j \frac{7}{20} \\ \\
r_{2}^* &= \frac{1}{20} +j \frac{7}{20}
\end{align*}
$$
Per cui abbiamo:
$$
\begin{align*}
f(t) &= \mathcal{L}^{-1}[F(s)] = -\frac{1}{10}e^{ -2t } + \left( \frac{1}{20}-j \frac{7}{20} \right)e^{ (1+j)t } + \left( \frac{1}{20} + j \frac{7}{20} \right)e^{ (1-j)t } \\ \\
&= -\frac{1}{10}e^{ -2t } + \frac{1}{10}e^{ t }(\cos t+7\sin t) \text{ , }t\geq 0
\end{align*}
$$
## ANDAMENTI CASO POLI SEMPLICI
Riassumiamo nel seguente schema l'*andamento dell'antitrasformata* in base alle *parti reale ed immaginaria* dei *poli semplici*:

```tikz
\usepackage{amsmath}
\usetikzlibrary{arrows.meta, calc, positioning}

\begin{document}

\begin{tikzpicture}[>=Stealth, scale = 0.9]

    % --- DEFINIZIONE COLORI (Tonalità pastello basate sull'immagine) ---
    \definecolor{bgBlue}{RGB}{230, 245, 250}
    \definecolor{bgYellow}{RGB}{250, 250, 230}
    \definecolor{bgGreen}{RGB}{235, 245, 235}
    \definecolor{bgPink}{RGB}{250, 235, 245}
    \definecolor{bgRed}{RGB}{250, 230, 230}
    \definecolor{plotLine}{RGB}{60, 60, 60}

    % --- MACRO PER DISEGNARE I POLI (X) ---
    \newcommand{\pole}[2][]{
        \draw[thick, #1] ($(#2)-(0.12,0.12)$) -- ($(#2)+(0.12,0.12)$);
        \draw[thick, #1] ($(#2)-(0.12,-0.12)$) -- ($(#2)+(0.12,-0.12)$);
    }

    % ==========================================
    % 1. RIQUADRI E GRAFICI
    % ==========================================

    % --- Riquadro in basso a sinistra: Polo Reale Negativo ---
    \begin{scope}[shift={(-6.5,-4.5)}]
        \fill[bgBlue] (0,0) rectangle (5,3.5);
        \node at (2.5, 2.8) {$\mathcal{L}^{-1}\left( \frac{C_i}{s-\sigma_i} \right) = C_i e^{\sigma_i t}$};
        % Assi
        \draw[->] (0.8, 0.5) -- (4.5, 0.5);
        \draw[->] (1.2, 0.2) -- (1.2, 2.2);
        % Funzione: Decadimento esponenziale
        \draw[thick, plotLine, smooth, domain=0:3] plot ({\x + 1.2}, {1.5*exp(-1.5*\x) + 0.5});
    \end{scope}

    % --- Riquadro in basso al centro: Polo nell'Origine ---
    \begin{scope}[shift={(0, -4.5)}]
        \fill[bgYellow] (0,0) rectangle (3.5, 2.5);
        \node at (1.75, 1.8) {$\mathcal{L}^{-1}\left( \frac{C_i}{s} \right) = C_i$};
        % Assi
        \draw[->] (0.4, 0.5) -- (3.2, 0.5);
        \draw[->] (0.8, 0.2) -- (0.8, 1.3);
        % Funzione: Gradino
        \draw[thick, plotLine] (0.8, 1) -- (2.8, 1);
    \end{scope}

    % --- Riquadro in basso a destra: Polo Reale Positivo ---
    \begin{scope}[shift={(4.5,-4.5)}]
        \fill[bgGreen] (0,0) rectangle (5.5, 3.5);
        \node at (2.75, 2.8) {$\mathcal{L}^{-1}\left( \frac{C_i}{s-\sigma_i} \right) = C_i e^{\sigma_i t}$};
        % Assi
        \draw[->] (0.8, 0.5) -- (4.5, 0.5);
        \draw[->] (1.2, 0.2) -- (1.2, 2.2);
        % Funzione: Crescita esponenziale
        \draw[thick, plotLine, smooth, domain=0:3.1] plot ({\x + 1.2}, {0.15*exp(0.7*\x) + 0.5});
    \end{scope}

    % --- Riquadro in alto a sinistra: Poli Complessi a Parte Reale Negativa ---
    \begin{scope}[shift={(-9, 1.5)}]
        \fill[bgGreen] (0,0) rectangle (7.5, 4.5);
        \node at (3.75, 3.8) {$\mathcal{L}^{-1} \left( \frac{C_i}{s - \lambda_i} + \frac{C_i^*}{s - \lambda_i^*} \right) = 2|C_i|e^{\sigma_i t} \cos(\omega_i t + \arg\{C_i\})$};
        \node[font=\small] at (6, 0.4) {$\text{Re}(\lambda_i) = \sigma_i < 0$};
        % Assi
        \draw[->] (2, 1.5) -- (6.5, 1.5);
        \draw[->] (2.5, 0.3) -- (2.5, 3);
        % Funzione: Sinusoide smorzata
        \draw[thick, plotLine, smooth, samples=150, domain=0:3.8] plot ({\x + 2.5}, {1.2*exp(-0.8*\x)*cos(deg(10*\x)) + 1.5});
    \end{scope}

    % --- Riquadro in alto a destra: Poli Immaginari Puri ---
    \begin{scope}[shift={(1.5, 3.5)}]
        \fill[bgPink] (0,0) rectangle (7, 4.5);
        \node at (3.5, 3.8) {$\mathcal{L}^{-1} \left( \frac{C_i}{s - j\omega_i} + \frac{C_i^*}{s + j\omega_i} \right) = 2|C_i| \cos(\omega_i t + \arg\{C_i\})$};
        \node[font=\small] at (1.5, -0.4) {$\text{Re}(\lambda_i) = \sigma_i = 0$};
        % Assi
        \draw[->] (0.5, 1.8) -- (5.5, 1.8);
        \draw[->] (1, 0.5) -- (1, 3.2);
        % Funzione: Sinusoide pura
        \draw[thick, plotLine, smooth, samples=150, domain=0:4.2] plot ({\x + 1}, {1.2*cos(deg(12*\x)) + 1.8});
    \end{scope}

    % --- Riquadro a destra centrale: Poli Complessi a Parte Reale Positiva ---
    \begin{scope}[shift={(4, -0.5)}]
        \fill[bgRed] (0,0) rectangle (6.5, 3.5);
        \node[align=center] at (3.25, 2.6) {$\mathcal{L}^{-1} \left( \frac{C_i}{s - \lambda_i} + \frac{C_i^*}{s - \lambda_i^*} \right) =$ \\[1ex] $= 2|C_i|e^{\sigma_i t} \cos(\omega_i t + \arg\{C_i\})$};
        % Assi
        \draw[->] (0.5, 1) -- (4.5, 1);
        \draw[->] (1, 0.2) -- (1, 2.2);
        % Funzione: Sinusoide divergente
        \draw[thick, plotLine, smooth, samples=150, domain=0:3.2] plot ({\x + 1}, {0.12*exp(0.7*\x)*cos(deg(12*\x)) + 1});
    \end{scope}


    % ==========================================
    % 2. PIANO COMPLESSO CENTRALE E POLI
    % ==========================================

    % Assi principali (s-plane)
    \draw[->, thick] (-5, 0) -- (5, 0) node[below] {\Large $Re$};
    \draw[->, thick] (0, -4) -- (0, 4) node[right] {\Large $Im$};

    % --- Posizionamento Poli ed Etichette ---
    
    % Origine
    \pole{0,0}
    \node[below left, fill=bgYellow, inner sep=2pt, opacity=0.8, text opacity=1] at (-0.1,-0.1) {$\lambda_i = 0$};
    \draw[->, dotted, thick] (0.2, -0.4) -- (1.5, -2);

    % Asse Reale Negativo
    \pole{-3,0}
    \node[below left, fill=bgBlue, inner sep=2pt] at (-3.1,-0.1) {$\lambda_i = \sigma_i < 0$};
    \draw[->, dotted, thick] (-3, -0.6) -- (-3, -1.5);

    % Asse Reale Positivo
    \pole{3,0}
    \node[below, fill=bgGreen, inner sep=2pt] at (3,-0.2) {$\lambda_i = \sigma_i > 0$};
    \draw[->, dotted, thick] (3, -0.6) -- (5.5, -1.5);

    % Asse Immaginario Puro
    \pole{0,2}
    \node[left, fill=bgPink, inner sep=2pt] at (-0.2, 2) {$+j\omega_i$};
    \draw[->, dotted, thick] (0.2, 2) -- (2.5, 3.5);
    
    \pole{0,-2}
    \node[left, fill=bgPink, inner sep=2pt] at (-0.2, -2) {$-j\omega_i$};

    % Complesso Coniugato Stabile (Quadranti sx)
    \node[fill=bgGreen, inner sep=4pt] at (-1.2, 1) {\tikz{\pole{0,0}}};
    \draw[->, dotted, thick] (-1.2, 1) -- (-3, 1.6); % Freccia verso il riquadro in alto a sx

    % Complesso Coniugato Instabile (Quadranti dx)
    \node[fill=bgRed, inner sep=4pt, label={[font=\small]below:$\text{Re}(\lambda_i) = \sigma_i > 0$}] at (2, 1) {\tikz{\pole{0,0}}};
    \draw[->, dotted, thick] (2.3, 1) -- (4, 1); % Freccia verso il riquadro a dx

\end{tikzpicture}

\end{document}
```
## CASO POLI MULTIPLI
Consideriamo ora il caso in cui la funzione presenta **poli multipli**:
$$
\begin{align*}
F(s) &= \frac{\mathcal{N}(s)}{\mathcal{D}(s)} = \frac{\mathcal{N}(s)}{(s-\lambda_{1})^{\mu_{1}} (s-\lambda_{2})^{\mu_{2}}\dots(s-\lambda_{r})^{\mu_{r}} } \\ \\
&= \dots + \frac{C_{i,1}}{(s-\lambda_{i})} + \frac{C_{i,2}}{(s-\lambda_{i})^2} + \dots + \frac{C_{i,\mu_{i}}}{(s-\lambda_{i})^{\mu_{i}}} + \dots
\end{align*}   
$$
Dove i coefficienti $C_{i}$ sono ancora *residui*, ma stavolta dati da:
$$
\begin{align*}
C_{i,\mu_{i}} &= \lim_{ s \to \lambda_{i} } F(s)(s-\lambda_{i})^{\mu_{i}} \\ \\
C_{i,\mu_{i}-1} &= \lim_{ s \to \lambda_{i} } \left(  F(s) - \frac{C_{i,\mu_{i}-1}}{(s-\lambda_{i})^{\mu_{i}}}  \right)(s-\lambda_{i})^{\mu_{i}-1} \\ \\
&\dots \\ \\
C_{i,1} &= \lim_{ s \to \lambda_{i} } \left( F(s) - \sum_{l=1}^{\mu_{i}-1} \frac{C_{i,l}}{(s-\lambda_{i})^{l+1}} \right)(s-\lambda_{i}) 
\end{align*}
$$
Alternativamente, possiamo anche calcolare i residui nel seguente modo:
$$
C_{i,k} = \frac{1}{(\mu_{i}-k)!} \lim_{ s \to \lambda_{i} } \frac{d^{\mu-k}}{ds^{\mu-k}} [(s-\lambda)^\mu F(s)] 
$$
Una volta scomposta $F(s)$ in questo modo, sfruttiamo il seguente fatto:
$$
\mathcal{L}^{-1} \left[ \frac{C_{i,l}}{(s-\lambda_{i})^{l}} \right] = C_{i,l} \frac{t^l}{l!} e^{ \lambda_{i}t } \delta_{-1}(t)
$$
Per cui troviamo:
$$
f(t) = \sum_{l=0}^{\mu_{i}-1} C_{i,l+1} \frac{t^l}{l!} e^{ \lambda_{i}t } \delta_{-1}(t)
$$

>[!note] NOTA
>Anche in questo caso i coefficienti sono **complessi coniugati** in corrispondenza di eventuali **poli complessi coniugati**.
>Quindi gli *esponenziali complessi* possono essere sostituiti con prodotti di *esponenziali reali* e *funzioni trigonometriche*.
## ANDAMENTI CASO POLI MULTIPLI
Riassumiamo nel seguente schema l'*andamento dell'antitrasformata* in base alle *parti reale ed immaginaria* dei *poli multipli*:

```tikz
\usepackage{amsmath}
\usetikzlibrary{arrows.meta, calc, positioning}

\begin{document}

\begin{tikzpicture}[>=Stealth, scale = 0.8]

    % --- DEFINIZIONE COLORI ---
    \definecolor{bgBlue}{RGB}{230, 245, 250}
    \definecolor{bgYellow}{RGB}{250, 250, 230}
    \definecolor{bgGreen}{RGB}{235, 245, 235}
    \definecolor{bgPink}{RGB}{250, 235, 245}
    \definecolor{bgRed}{RGB}{250, 230, 230}
    \definecolor{plotLine}{RGB}{60, 60, 60}
    \definecolor{poleGray}{RGB}{130, 130, 130}

    % --- MACRO PER DISEGNARE I POLI (X) ---
    \newcommand{\pole}[2][poleGray]{
        \draw[ultra thick, #1] ($(#2)-(0.12,0.12)$) -- ($(#2)+(0.12,0.12)$);
        \draw[ultra thick, #1] ($(#2)-(0.12,-0.12)$) -- ($(#2)+(0.12,-0.12)$);
    }

    % ==========================================
    % 1. RIQUADRI E GRAFICI
    % ==========================================

    % --- Riquadro in basso a sinistra: Polo Reale Negativo (Molteplice) ---
    \begin{scope}[shift={(-8.5,-5)}]
        \fill[bgBlue] (0,0) rectangle (5.5, 3.5);
        \node at (2, 2.5) {$\frac{t^l}{l!}C_{i,l}e^{\sigma_i t}$};
        % Assi
        \draw[->] (0.8, 0.5) -- (4.5, 0.5);
        \draw[->] (1.2, 0.2) -- (1.2, 2.5);
        % Funzione: Decadimento con inviluppo t^l (es. t^2 * e^-at)
        \draw[ultra thick, plotLine, smooth, domain=0:3.8] plot ({0.6*\x+1.2}, {10*(\x)^2*exp(-2*\x) + 0.5});
    \end{scope}

    % --- Riquadro in basso al centro: Polo nell'Origine (Molteplice) ---
    \begin{scope}[shift={(0, -5)}]
        \fill[bgYellow] (0,0) rectangle (3.5, 2.5);
        \node at (1.2, 1.8) {$\frac{t^l}{l!}C_{i,l}$};
        % Assi
        \draw[->] (0.4, 0.5) -- (3.2, 0.5);
        \draw[->] (0.8, 0.2) -- (0.8, 2.2);
        % Funzione: Crescita polinomiale (es. t^2)
        \draw[ultra thick, plotLine, smooth, domain=0:2] plot ({\x + 0.8}, {0.35*(\x)^2 + 0.5});
    \end{scope}

    % --- Riquadro in basso a destra: Polo Reale Positivo (Molteplice) ---
    \begin{scope}[shift={(4.5,-5)}]
        \fill[bgGreen] (0,0) rectangle (5.5, 3.5);
        \node at (4, 2.2) {$\frac{t^l}{l!}C_{i,l}e^{\sigma_i t}$};
        % Assi
        \draw[->] (0.8, 0.5) -- (4.5, 0.5);
        \draw[->] (1.2, 0.2) -- (1.2, 2.2);
        % Funzione: Crescita esponenziale con inviluppo t^l (es. t^2 * e^at)
        \draw[ultra thick, plotLine, smooth, domain=0:1.8] plot ({\x + 1.2}, {0.15*(\x)^2*exp(0.8*\x) + 0.5});
    \end{scope}

    % --- Riquadro in alto a sinistra: Poli Complessi a Parte Reale Negativa (Molteplici) ---
    \begin{scope}[shift={(-9, 3.5)}]
        \fill[bgGreen] (0,0) rectangle (7.5, 4.5);
        \node at (3.75, 4) {$\frac{t^l}{l!}e^{\sigma_i t} \left[ 2\text{Re}(C_{i,l})\cos(\omega_i t) - 2\text{Im}(C_{i,l})\sin(\omega_i t) \right]$};
        \node[font=\small] at (5, 0.8) {$\text{Re}(\lambda_i) = \sigma_i < 0$};
        % Assi
        \draw[->] (2, 2) -- (6.5, 2);
        \draw[->] (2.5, 0.8) -- (2.5, 3.5);
        % Funzione: Sinusoide smorzata con inviluppo t^l
        \draw[ultra thick, plotLine, smooth, samples=200, domain=0:7] plot ({0.5*\x + 2.5}, {1*\x^4*exp(-1.5*\x)*sin(deg(10*\x)) + 2});
    \end{scope}

    % --- Riquadro in alto a destra: Poli Immaginari Puri (Molteplici) ---
    \begin{scope}[shift={(1.5, 3.5)}]
        \fill[bgPink] (0,0) rectangle (7.5, 4.5);
        \node at (4.5, 3.8) {$\frac{t^l}{l!} \left[ 2\text{Re}(C_{i,l})\cos(\omega_i t) - 2\text{Im}(C_{i,l})\sin(\omega_i t) \right]$};
        \node[font=\small] at (1.5, 0.2) {$\text{Re}(\lambda_i) = 0$};
        % Assi
        \draw[->] (0.5, 1.8) -- (5.5, 1.8);
        \draw[->] (1, 0.5) -- (1, 3.5);
        % Funzione: Sinusoide divergente linearmente (t * sin(wt))
        \draw[ultra thick, plotLine, smooth, samples=200, domain=0:4.2] plot ({\x + 1}, {0.35*\x*sin(deg(20*\x)) + 1.8});
    \end{scope}

    % --- Riquadro a destra centrale: Poli Complessi a Parte Reale Positiva (Molteplici) ---
    \begin{scope}[shift={(4, -0.5)}]
        \fill[bgRed] (0,0) rectangle (7, 3.5);
        \node[align=center] at (4, 2.8) {$\frac{t^l}{l!}e^{\sigma_i t} \left[ 2\text{Re}(C_{i,l})\cos(\omega_i t) - 2\text{Im}(C_{i,l})\sin(\omega_i t) \right]$};
        \node[font=\small] at (-1.5, 2) {$\text{Re}(\lambda_i) = \sigma_i > 0$};
        % Assi
        \draw[->] (1.5, 1) -- (5.5, 1);
        \draw[->] (2, 0.2) -- (2, 2.5);
        % Funzione: Sinusoide divergente esponenzialmente con inviluppo t^l
        \draw[ultra thick, plotLine, smooth, samples=200, domain=0:3.2] plot ({\x + 2}, {0.08*\x*exp(0.5*\x)*sin(deg(13*\x)) + 1});
    \end{scope}

    % ==========================================
    % 2. PIANO COMPLESSO CENTRALE E POLI
    % ==========================================

    % Assi principali (s-plane)
    \draw[->, thick] (-5, 0) -- (5, 0) node[below] {\Large $Re$};
    \draw[->, thick] (0, -4) -- (0, 4) node[right] {\Large $Im$};

    % --- Posizionamento Poli ed Etichette ---
    
    % Origine
    \pole{0,0}
    \node[below left, fill=bgYellow, inner sep=2pt, opacity=0.8, text opacity=1] at (-0.1,-0.1) {$\lambda_i = 0$};
    \draw[->, dotted, thick] (0.2, -0.4) -- (1.5, -2.4); % Freccia solida verso box giallo

    % Asse Reale Negativo
    \pole{-4,0}
    \node[above left, fill=bgBlue, inner sep=2pt] at (-3.9, 0.2) {$\lambda_i = \sigma_i < 0$};
    \draw[->, dotted, thick] (-4, -0.2) -- (-4, -1.5); % Freccia tratteggiata verso il basso

    % Asse Reale Positivo
    \pole{3.5,0}
    \node[below left, fill=bgGreen, inner sep=2pt] at (3.5,-0.2) {$\lambda_i = \sigma_i > 0$};
    \draw[->, dotted, thick] (3.6, -0.2) -- (4.5, -1.5); % Freccia solida verso il box in basso a dx

    % Asse Immaginario Puro
    \pole{0,2}
    \node[left, fill=bgPink, inner sep=2pt] at (-0.2, 2) {$+j\omega_i$};
    \draw[->, dotted, thick] (0.2, 2) -- (2.2, 3.5); % Freccia tratteggiata verso box rosa
    
    \pole{0,-2}
    \node[left, fill=bgPink, inner sep=2pt] at (-0.2, -2) {$-j\omega_i$};

    % Posizioni miste
    \node[fill=bgGreen, inner sep=4pt] at (-2.2, -2) {\tikz{\pole[poleGray]{0,0}}};
    \node[fill=bgRed, inner sep=4pt] at (2.2, -2) {\tikz{\pole[poleGray]{0,0}}};

    \node[fill=bgGreen, inner sep=4pt] at (-2.2, 2) {\tikz{\pole[poleGray]{0,0}}};
    \draw[->, dotted, thick] (-2.2, 2) -- (-3, 3.7); 
    
    \node[fill=bgRed, inner sep=4pt] at (2.2, 2) {\tikz{\pole[poleGray]{0,0}}};
    \draw[->, dotted, thick] (2.2, 2) -- (4.1, 2);

\end{tikzpicture}

\end{document}
```