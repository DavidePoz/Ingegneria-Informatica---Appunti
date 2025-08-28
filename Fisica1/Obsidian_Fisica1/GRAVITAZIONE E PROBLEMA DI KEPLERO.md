# ==PROBLEMA DEI DUE CORPI==
Il problema è così posto:
>[!question] PROBLEMA DI KEPLERO
>Due corpi di masse $m_{1}$ ed $m_{2}$ sono soggetti alla forza di gravitazione universale. 
>Determinare tutte le possibili orbite.
## 1 IMPOSTAZIONE PROBLEMA
Chiamiamo il vettore $\frac{\vec{r}_{1}-\vec{r}_{2}}{\| \vec{r}_{1}-\vec{r}_{2} \|}$, cioè il versore della direzione che congiunge $m_{1}$ e $m_{2}$, $\vec{u}_{21}$ (perchè punta da $2$ a $1$).
Allora le equazioni del moto sono:
$$
\begin{cases}
m_{1}\vec{a}_{1} = m_{1} \frac{d^2 \vec{r}_{1}}{dt^2} = -G \frac{m_{1}m_{2}}{\| \vec{r}_{1}-\vec{r}_{2} \|}\vec{u}_{12} \text{ (I)} \\  \\
m_{2}\vec{a}_{2} = m_{2} \frac{d^2 \vec{r}_{2}}{dt^2} = -G \frac{m_{1}m_{2}}{\| \vec{r}_{2}-\vec{r}_{1} \|}\vec{u}_{21} \text{ (II)} 
\end{cases} \tag{1}
$$
Osserviamo che la forza in (I) punta verso il corpo $1$ e quella in (II) verso il corpo $2$.
### 1.1 SCOMPOSIZIONE DEL PROBLEMA
Le forze sono solo interne, allora $\vec{p}_{TOT}$ si conserva e vale, definita $M:=m_{1}+m_{2}$:
$$
\vec{v}_{CM}=\frac{1}{M}\vec{p}_{TOT} \tag{2}
$$
Per lo stesso motivo, il sistema di riferimento del CM è inerziale.
Vale quindi la pena calcolare $\vec{r}_{CM}$ e le posizioni $\vec{r}'_{1}$ e $\vec{r}_{2}'$ dei corpi rispetto al CM.
$$
\vec{r}_{CM} = \frac{m_{1}}{M}\vec{r}_{1} + \frac{m_{2}}{M}\vec{r}_{2} \tag{3}
$$
$$
\vec{r}_{1}' = \vec{r}_{1} - \vec{r}_{CM} = \frac{m_{2}}{M}(\vec{r}_{1}-\vec{r}_{2}) \tag{4}
$$
E similmente:
$$
\vec{r}_{2}' = \vec{r}_{2}-\vec{r}_{CM} = \frac{m_{1}}{M}(\vec{r}_{2}-\vec{r}_{1}) \tag{5}
$$
A partire da $\vec{r}_{1}'$ e $\vec{r}_{2}'$, consideriamo ora:
$$
m_{1}\vec{r}_{1} = m_{1} \frac{m_{2}}{M} (\vec{r}_{1}-\vec{r}_{2}) \tag{6}
$$
E
$$
m_{2}\vec{r}_{2} = m_{2} \frac{m_{1}}{M} (\vec{r}_{2}-\vec{r}_{1}) \tag{7}
$$
Definiamo per comodità la massa ridotta $\mu$:
$$
\mu=\frac{m_{1}m_{2}}{M} \tag{8}
$$
Deriviamo due volte ==(6)== e troviamo l'equazione del moto *nel sistema del CM*:
$$
m_{1} \frac{d^2\vec{r}_{1}'}{dt^2} = \frac{d^2}{dt^2} \mu(\vec{r}_{1}-\vec{r}_{2}) \tag{9}
$$
Il membro di sinistra diventa:
$$
= m_{1} \frac{d^2}{dt^2} (\vec{r}_{1}-\vec{r}_{CM}) = m_{1} \frac{d^2\vec{r}_{1}}{dt^2} - m_{1} \cancelto{ 0 }{ \frac{d^2\vec{r}_{CM}}{dt^2} } \tag{10}
$$
Ma il membro di destra in ==(10)== è esattamente l'equazione del moto ==(1)==.
Per cui troviamo:
$$
-G \frac{m_{1}m_{2}}{\| \vec{r}_{1}-\vec{r}_{2} \|^2}\vec{u}_{12} = m_{1} \frac{d^2}{dt^2}\vec{r}_{1}' = \mu\frac{ d^2}{dt^2}(\vec{r}_{1}-\vec{r}_{2}) \tag{11}
$$
Similmente si trova anche:
$$
-G \frac{m_{1}m_{2}}{\| \vec{r}_{2}-\vec{r}_{1} \|^2}\vec{u}_{21} = m_{2} \frac{d^2}{dt^2}\vec{r}_{2}' = \mu\frac{ d^2}{dt^2}(\vec{r}_{2}-\vec{r}_{1}) \tag{12}
$$
A questo punto chiamiamo $\vec{u}_{12}:= \vec{u}_{r}$ e le equazioni del moto diventano:
$$
\begin{cases}
\mu\frac{d^2}{dt^2}(\vec{r}_{1}-\vec{r}_{2}) = -G \frac{\mu M}{\|\vec{r}_{1}-\vec{r}_{2}\|^2}\vec{u}_{r} \\  \\
-\mu\frac{d^2}{dt^2}(\vec{r}_{1}-\vec{r}_{2}) = G \frac{\mu M}{\|\vec{r}_{1}-\vec{r}_{2}\|^2}\vec{u}_{r}
\end{cases} \tag{13}
$$
Tali equazioni sono linearmente dipendenti (una è l'opposto dell'altra), cosa che avremmo potuto dedurre già in ==(1)==; ma scrivendole in questa forma abbiamo scomposto il problema nel seguente modo:
- Un moto del CM, con massa $M$ e per cui vale 
  $M\vec{a}_{CM} =\vec{0}$ essendo il riferimento inerziale.
- Un moto di una massa $\mu$ intorno al CM.

Infatti, definendo $\vec{r}:=\vec{r}_{1}-\vec{r}_{2}$, ci basta considerare un'unica equazione:
$$
\mu\frac{d^2\vec{r}}{dt^2} = -G \frac{\mu M}{\|\vec{r}\|^2}\vec{u}_{r} \tag{14}
$$
Che rappresenta proprio il moto di una massa $\mu$ soggetta all'attrazione gravitazionale di una massa $M$ a distanza $\|\vec{r}\|$ da essa.
### 1.2 ULTERIORE SEMPLIFICAZIONE
Dovremmo scomporre l'equazione ==(14)== lungo le tre componenti nello spazio del vettore $\vec{r}$, tuttavia possiamo ragionare nel seguente modo:
l'unica forza presente è quella gravitazionale che, considerato come polo il CM, è parallela al suo braccio. 
Allora il momento totale è nullo, e quindi il momento angolare rimane costante, ma ciò significa che il moto avviene su un piano (perpendicolare al vettore $\vec{L}$), e quindi basta considerare le proiezioni di $\vec{r}$ su un piano $xy$.
```tikz
\usepackage{tikz} 
\usetikzlibrary {perspective}

\begin{document}

\begin{tikzpicture}[3d view]
  \draw[->] (-5,0,0) -- (5,0,0) node[pos=1.1]{$\vec{u}_x$};
  \draw[->] (0,-5,0) -- (0,5,0) node[pos=1.1]{$\vec{u}_y$};
  \draw[->] (0,0,-1) -- (0,0,3) node[pos=1.1]{$\vec{u}_z$};

  \draw (0,0,0)[gray, dashed] circle (4);  
  \node (A) at (3.5,-2,0) [circle,draw]  {$\mu$};
  \draw[->, red] (0,0,0) -- (A) node[midway, below]{$\vec{r}$};
  \draw[->,thick,red] (0,0,0) -- (0,0,2) node[right]{$\vec{L}$};
\end{tikzpicture}

\end{document}
```

## 2 SECONDA LEGGE DI KEPLERO
Calcoliamo ora il momento angolare del corpo di massa $\mu$:
$$
\vec{L} = \vec{r} \times \mu\frac{d\vec{r}}{dt} \tag{15}
$$
$$
=r\vec{u}_{r} \times \mu\frac{d}{dt}(r\vec{u}_{r}) = r\vec{u}_{r}\times \left( \frac{dr}{dt}\vec{u}_{r} + r \frac{d\theta}{dt}\vec{u}_{\perp} \right)
$$
E concludiamo:
$$
\vec{L} = \mu r^2 \frac{d\theta}{dt}\vec{u}_{r} \times \vec{u}_{\perp} \tag{16}
$$
Come visto in > [[#1.2 ULTERIORE SEMPLIFICAZIONE]], il momento angolare è perpendicolare al piano su cui avviene il moto, individuato dai vettori $\vec{u}_{r}$ e $\vec{u}_{\perp}$.

Abbiamo visto che $\vec{L}$ è costante, per cui anche la seguente quantità deve essere costante durante il moto:
$$
r^2 \frac{d\theta}{dt} \tag{17}
$$
Possiamo dare un'interpretazione geometrica a tale quantità.
Consideriamo la posizione $\vec{r}(t)$ di $\mu$ al tempo $t$ e $\vec{r}(t+\Delta t)$ dopo un piccolo intervallo $\Delta t$, durante il quale ha spazzato un angolo $\Delta \theta$:
```tikz
\usepackage{tikz} 
\usetikzlibrary {perspective}

\begin{document}

\begin{tikzpicture}[3d view]
  \draw[->] (-1,0,0) -- (5,0,0) node[pos=1.1]{$\vec{u}_x$};
  \draw[->] (0,-3,0) -- (0,4,0) node[pos=1.1]{$\vec{u}_y$};
  \draw[->] (0,0,-0.5) -- (0,0,3) node[pos=1.1]{$\vec{u}_z$};

  \node (A) at (3,3,0){};

  \draw[gray] (A) arc[ radius=5, start angle=45, delta angle=-30,] node(B)[at end]{};
  \draw (A) arc[ radius=5, start angle=45, delta angle=10,] node(C)[at end]{};
  \draw[dashed] (C) arc[ radius=5, start angle=55, delta angle=20];
  \draw (B) arc[ radius=5, start angle=15, delta angle=-15] node(D)[at end]{};
  \draw[dashed] (D) arc[ radius=5, start angle=0, delta angle=-25];

  \draw[gray] (1,1,0) arc[ radius=1, start angle=45, delta angle=-40,] 
  node[black, at end, below=2pt]{$\Delta \theta$};

  \draw[->,densely dashed, thick, red] (0,0,0) -- (A)
  node[at end, above, black]{$\vec{r}(t)$};;
  \draw[densely dashed, thick, red] (A) arc[radius=5, start angle=45, delta angle=-30,];
  \draw[->,densely dashed, thick, red] (0,0,0) -- (B) 
  node[at end, above=2pt, black]{$\vec{r}(t+\Delta t)$};

\end{tikzpicture}

\end{document}
```
L'area spazzata dal vettore, evidenziata in rosso nella figura, è approssimata da:
$$
\Delta A \approx \frac{1}{2}r(r\Delta\theta) \tag{18}
$$
Per cui, per $\Delta\theta$ sufficientemente piccoli, si ha:
$$
dA = \frac{1}{2}r^2d\theta \tag{19}
$$
Definiamo a questo punto la **velocità areolare** come l'area spazzata dal vettore $\vec{r}$ in un tempo $t$:
$$
\frac{dA}{dt} := \frac{1}{2}r^2 \frac{d\theta}{dt} \tag{20}
$$
Siccome ==(17)== deve rimanere costante, si ha:
>[!info] SECONDA LEGGE DI KEPLERO
>La velocità areolare rimane costante lungo il moto.

## 3 ENERGIA POTENZIALE ASSOCIATA ALL'ORBITA
Torniamo ora a ==(14)==.
Abbiamo detto che le due equazioni non banali sono quelle relative al piano di rotazione. 
### 3.1 SCOMPOSIZIONE EQUAZIONE DEL MOTO
Scomponiamo quindi $\frac{d^2\vec{r}}{dt^2}$ lungo $\vec{u}_{r}$ e $\vec{u}_{\perp}$.
Innanzitutto:
$$
\frac{d}{dt}(r\vec{u}_{r}) = \frac{dr}{dt}\vec{u}_{r} + r\frac{d\theta}{dt}\vec{u}_{\perp} \tag{21}
$$
E infine:
$$
\frac{d^2}{dt^2}(r\vec{u}_{r}) = \underbrace{ \frac{d^2r}{dt^2}\vec{u}_{r} + \frac{dr}{dt} \frac{d\theta}{dt}\vec{u}_{\perp} }_{  } + \underbrace{ \frac{dr}{dt} \frac{d\theta}{dt}\vec{u}_{\perp} + r \frac{d^2\theta}{dt^2}\vec{u}_{\perp} - r \left( \frac{d\theta}{dt} \right)^2\vec{u}_{r} }_{  } \tag{22}$$
E raccogliendo troviamo:
$$
\frac{d^2}{dt^2}(r\vec{u}_{r}) = \left( \frac{d^2r}{dt^2} - r\left( \frac{d\theta}{dt} \right)^2 \right)\vec{u}_{r} + \left( 2 \frac{dr}{dt} \frac{d\theta}{dt} +r \frac{d^2\theta}{dt^2} \right)\vec{u}_{\perp} \tag{23}
$$
Inoltre sappiamo che il momento angolare è costante in modulo, per cui:
$$
L=\mu r^2 \frac{d\theta}{dt} \implies \frac{d\theta}{dt} = \frac{L}{\mu r^2} \tag{24}
$$
Sostituendo in ==(23)== troviamo allora:
$$
\frac{d^2}{dt^2}(r\vec{u}_{r}) = \left(  \frac{d^2r}{dt^2} - \frac{L^2}{\mu^2 r^3} \right)\vec{u}_{r} + \left( \frac{2L}{\mu r^2} \frac{dr}{dt} + r \frac{d^2\theta}{dt^2} \right)\vec{u}_{\perp} \tag{25}
$$
Definiamo, per comodità:
$$
k := Gm_{1}m_{2} = GM\mu \tag{26}
$$
Possiamo finalmente scomporre ==(14)== lungo $\vec{u}_{r}$ e $\vec{u}_{\perp}$:
**LUNGO $\vec{u}_{r}$:**
$$
\mu\left(  \frac{d^2r}{dt^2} - \frac{L^2}{\mu^2r^3}  \right)  = -\frac{k}{r^2} \implies \mu\frac{d^2r}{dt^2} = \frac{L^2}{\mu r^3} - \frac{k}{r^2} \tag{27} 
$$
**LUNGO $\vec{u}_{\perp}$ :**
$$
\mu\left( \frac{2L}{\mu r^2} \frac{dr}{dt} +r \frac{d^2\theta}{dt^2} \right) = 0 \implies \mu\frac{d^2\theta}{dt^2} = -\frac{2L}{r^3} \frac{dr}{dt} \tag{28}
$$

### 3.2 ENERGIA POTENZIALE EFFICACE
Osserviamo che ==(27)== sembra l'equazione di un problema in *una dimensione* dovuto ad una *forza posizionale*.
Per capire meglio, calcoliamo l'energia totale per il corpo $\mu$:
$$
E_{TOT} = E_{K}(\vec{v}) + E_{P}(\vec{r}) = \frac{1}{2}\mu\| \vec{v} \|^2 -\frac{k}{r} \tag{29}
$$
Dove $-\frac{k}{r}$ è l'energia potenziale gravitazionale.
Abbiamo allora:
$$
E_{TOT} = \frac{1}{2}\mu\| \frac{dr}{dt}\vec{u}_{r} + r \frac{d\theta}{dt}\vec{u}_{\perp} \|^2 -\frac{k}{r} = \frac{1}{2}\mu\left( \frac{dr}{dt} \right)^2 + \underbrace{ \frac{1}{2}\mu\left( r \frac{d\theta}{dt} \right)^2 }_{ A } -\frac{k}{r} \tag{30}
$$
Grazie a ==(24)== possiamo semplificare tale equazione, trovando:
$$
E_{TOT} = \underbrace{ \frac{1}{2}\mu \left( \frac{dr}{dt} \right)^2 }_{ E_{K,r} } + \underbrace{ \frac{L^2}{2\mu r^2} -\frac{k}{r} }_{ E_{P,eff} } \tag{31}
$$
Notiamo a questo punto che il termine $A$ contribuisce insieme all'energia potenziale gravitazionale ad una *energia potenziale per il moto lungo $r$*, che chiamiamo **energia potenziale efficace**. In particolare, $\frac{L^2}{2\mu r^2}$ è detto *termine centrifugo*.
E' facile verificare che la forza derivante da $E_{P,eff}$ è proprio quella in ==(27)==, infatti vale:
$$
-\frac{d}{dr}E_{P,eff} = \frac{L^2}{\mu r^3} - \frac{k}{r^2} =F_{eff,r} \tag{32}
$$
Il fatto che la forza lungo $r$ sia gradiente della funzione scalare $E_{P,eff}(r)$ garantisce anche che tale forza sia conservativa.

Ovviamente, deve sempre essere:
$$
E_{TOT} \geq E_{P,eff} \tag{33} 
$$
Vediamo come si comporta $E_{P,eff}(r)$:

```tikz
\usepackage{tikz} 

\begin{document}

\begin{tikzpicture}[domain=0:15]
  \draw[->] (-0.2,0) -- (15.2,0) node[right] {$r$};
  \draw[->] (0,-3.3) -- (0,4.6) node[above] {$E_{P,eff}(r)$};

  \draw[color=red, smooth, thick] plot (\x,{25/((\x +1.05)^2) -20/(\x +1.05) +1});

  \draw[dashed, thick] (0,-3.05) -- (15,-3.05) node[at end, right, below]{$E_{min}$};

  \draw[dashed, thick] (0,-1) -- (15,-1) node[at end, right, below]{$E'_{TOT}$};
  \draw[dashed, thick] (0,2) -- (15,2) node[at end, right, below]{$E_{TOT}$};

\end{tikzpicture}

\end{document}
```

Notiamo che:
- Esiste un valore **minimo** per l'energia potenziale efficace, $E_{min}$.
- Se $E_{TOT}> 0$ deve essere $r>r_{min}$, cioè si ha un'**orbita aperta**.
- Se $E'_{TOT}<0$ deve essere $r'_{min}<r<r'_{max}$, cioè si ha un'**orbita chiusa**.
- Se $E_{TOT}=E_{min}$ deve essere $r=r_{min}$, cioè si ha un'**orbita circolare**.
#### 3.2.1 ORBITA CIRCOLARE
Abbiamo visto che si ha un'orbita *circolare* se $E_{P,eff}=E_{min}$.
Ciò accade per $r=r_{0}$, dove $r_{0}$ soddisfa:
$$
\frac{d}{dr}E_{P,eff}\bigg|_{r_{0}} = 0 \tag{34}
$$
Allora deve essere:
$$
\frac{L^2}{\mu r_{0}^3} -\frac{k}{r_{0}^2} = 0
$$
Da cui otteniamo:
$$
r_{0}=\frac{L^2}{\mu k} \tag{35}
$$
Per tale valore di $r$ si ha che l'energia totale in un'orbita circolare è (sostituendo ==(35)== in ==(31)== ):
$$
E_{P,eff,circ} = -\frac{\mu k^2}{2L^2} \tag{36}
$$
Ricordando che $L=\mu r_{0}^2 \frac{d\theta}{dt}$ è costante, deduciamo che, per un'orbita circolare, anche $\frac{d\theta}{dt}$ è costante (essendo $r_{0}$ costante): definiamo $\omega_{0}:= \frac{d\theta}{dt}$ e, sostituendo $L$ in ==(35)==, troviamo la seguente relazione:
$$
r_{0}^3 = \frac{k}{\mu\omega_{0}^2} \tag{37} 
$$
## 4 FORMA DELLE ORBITE
Abbiamo analizzato il caso di un'orbita circolare, ma se $r>r_{0}$ che forma hanno le orbite?
Per rispondere a questa domanda vogliamo $r(\theta)$ invece di $r(t)$ e $\theta(t)$.
Per la derivata della funzione composta, sappiamo che vale:
$$
\frac{d}{dt}\bigg(r \big( \theta(t) \big) \bigg) = \frac{d\theta}{dt} \frac{dr}{dt} \tag{38}
$$
Ma per noi $\frac{d\theta}{dt}=\frac{L}{\mu r^2}$, allora:
$$
\frac{d}{dt}\bigg(r \big( \theta(t) \big) \bigg) = \frac{L}{\mu r^2} \frac{dr}{d\theta} = -\frac{L}{\mu} \frac{d}{d\theta}\left( \frac{1}{r} \right) \tag{39}
$$
Conviene allora introdurre la variabile ausiliaria $u:=\frac{1}{r}$.
Abbiamo allora:
$$
\frac{dr}{dt} = -\frac{L}{\mu} \frac{du}{d\theta} \tag{40}
$$
Derivando tale relazione troviamo:
$$
\frac{d^2r}{dt^2} = \frac{d}{dt}\left( -\frac{L}{\mu} \frac{du}{d\theta} \right) = \frac{d}{d\theta}\left( -\frac{L}{\mu} \frac{du}{d\theta} \right) \frac{d\theta}{dt} \tag{41}
$$
E concludiamo che:
$$
\frac{d^2r}{dt^2} = -\frac{L^2}{\mu^2}u^2 \frac{d^2u}{d\theta} \tag{42}
$$
Per cui possiamo riscrivere ==(27)== in termini di $u(\theta)$, ottenendo:
$$
-\frac{L^2}{\mu} \frac{d^2u}{dt^2} = \frac{L^2}{\mu}u-k \tag{43}
$$
Riordinando i termini in questa equazione, otteniamo la seguente equazione differenziale in $u(\theta)$:
$$
\frac{d^2u}{d\theta^2} + u =\frac{k}{L^2}\mu \tag{44}
$$
Abbiamo già incontrato equazioni differenziali di questo tipo e sappiamo che la soluzione più generale è data da:
$$
u(\theta) = \frac{k}{L^2}\mu + A\cos(\theta + \theta_{0}) \tag{45}
$$
Era $u=\frac{1}{r}$, e allora l'orbita è descritta da:
$$
r(\theta) = \frac{1}{\frac{k}{L^2}\mu +A\cos(\theta) } \tag{46}
$$
Dove abbiamo scelto $\theta_{0}=0$ dato che siamo interessati a descrivere l'orbita per ogni $\theta$ e non ci interessa la condizione iniziale.
>[!warning] NOTA
>Per $\theta_{0}=0$ si ha che $r(\theta)$ è minimo per $\theta=0$ e massimo per $\theta=\pi$.

Di solito ==(46)== si scrive nella forma:
$$
r(\theta) = \frac{r_{0}}{e\cos\theta+1} \tag{47}
$$
Dove:
- $r_{0}=\frac{L^2}{\mu k}$
- $e=\frac{AL^2}{\mu k}$
**OSS:** possiamo sempre considerare $e\geq 0$, infatti se fosse $e<0$ basterebbe ricordare che $\cos(x)=-\cos(x+\pi)$ e scegliere quindi un diverso $\theta_{0}$.
### 4.1 ORBITA CIRCOLARE
Chiaramente, per $e=0$ ($A=0$) si ha $r(\theta)=r_{0}$ $\forall t$ e siamo nel caso dell'orbita circolare.

**OSS:** si tratta proprio della soluzione particolare di ==(44)==.
### 4.2 ORBITA LIMITATA
Notiamo ora che, se $0<e<1$, il denominatore in ==(47)== non si annulla mai e quindi $r(\theta)$ è limitato e avrà un massimo e un minimo:
$$
r_{max} = \frac{r_{0}}{1-e} \tag{48}
$$
$$
r_{min} = \frac{r_{0}}{1+e}
$$
Per trovare l'equazione cartesiana dell'orbita convertiamo le coordinate da polari a cartesiane, ricordando che $x=r\cos\theta$ e $y=r\sin\theta$.
Allora $r=\sqrt{ x^2+y^2 }$ e $\theta=\arctan\left( \frac{y}{x} \right)$.
Per cui ==(47)== diventa:
$$
\sqrt{ x^2+y^2 } = \frac{r_{0}}{e \frac{x}{\sqrt{ x^2+y^2 }}+1} \tag{49}
$$
Da cui ricaviamo:
$$
\left( x+ \frac{er_{0}}{1-e^2} \right)^2 + \frac{y^2}{1-e^2} = \left( \frac{r_{0}}{1-e^2} \right)^2 \tag{50}
$$
E che riscriviamo come:
$$
\left( x + \frac{er_{0}}{1-e^2} \right)\underbrace{ \left( \frac{1-e^2}{r_{0}} \right)^2 }_{ \frac{1}{a^2} } + y^2 \underbrace{ \left( \frac{1-e^2}{r_{0}^2} \right) }_{ \frac{1}{b^2} } = 1 \tag{51}
$$
Notiamo che $\frac{1}{a^2}>0$ e $\frac{1}{b^2}>0$: allora è l'equazione di un'**ellisse**.
### 4.3 ORBITA ILLIMITATA
#### 4.3.1 $e=1$
In tal caso abbiamo:
$$
r(\theta) = \frac{r_{0}}{\cos\theta+1} \tag{52}
$$
Convertendo anche questo caso in coordinate cartesiane troviamo:
$$
x=\frac{r_{0}}{2} - \frac{y^2}{2r_{0}} \tag{53}
$$
Che è l'equazione di una **parabola**.
>[!warning] NOTA
>Essendo $e$ misurato, è molto poco probabile che in natura si verifichi esattamente $e=1$.
#### 4.3.2 $e>1$
Otteniamo nuovamente ==(51)==, ma questa volta, essendo $e>1$, abbiamo $\frac{1}{b^2}<0$ e $\frac{1}{a^2}>0$, perciò abbiamo l'equazione di un'**iperbole**.
## 5 CORREZIONI RELATIVISTICHE
Oggi sappiamo che la legge di gravitazione universale di Newton non è una legge fondamentale, ma è un’approssimazione della relatività generale di Einstein. 
La relatività generale si basa su idee che abbiamo già incontrato: l’equivalenza tra la massa inerziale e quella gravitazionale, e l’equivalenza tra l’accelerazione gravitazionale e quella dovuta alle forze apparenti.
Nel caso del problema di Keplero, si può dimostrare che la relatività generale porta ad una correzione del potenziale gravitazionale per cui si ha:
$$
E_{P,r} = -G \frac{\mu M}{r}\left( 1+ \frac{3GM}{c^2r} + o(1/r) \right) \tag{54}
$$
Allora l'equazione ==(27)== (quella del moto lungo $\vec{u}_{r}$) diventa:
$$
\mu\frac{d^2r}{dt^2} = -\frac{d}{dr}E_{P,r} \tag{55}
$$
Dove il membro di destra è sempre la derivata dell'energia potenziale efficace, ma che ora tiene conto delle correzioni relativistiche.
Si trova allora l'equazione differenziale seguente:
$$
\frac{d^2u}{d\theta^2} + \underbrace{ \left( 1-6 \frac{k^2}{c^2L^2} \right) }_{ \text{prima era 1} }u = \frac{\mu k}{L^2} \tag{56}
$$
La cui soluzione è:
$$
u(\theta) =A\cos\left( \underbrace{ \sqrt{ 1-6 \frac{k^2}{c^2L^2} } }_{ <1 }\theta -\theta_{0} \right) + \frac{\mu k}{L^2-\frac{6k^2}{c^2}} \tag{57}
$$
Per cui l'orbita *non si chiude* dopo un angolo di $2\pi$, ma *solo dopo*:
$$
\Delta\theta = \frac{2\pi}{\sqrt{ 1-6 \frac{k^2}{c^2L^2} }} \approx 2\pi\left( 1+3 \frac{k^2}{c^2L^2} \right) \tag{58}
$$
Tale fenomeno è detto **precessione dell'orbita**, per cui viene definito l'*angolo di precessione*:
$$
\varphi = \frac{6\pi k^2}{c^2L^2} \tag{59} 
$$
La corretta previsione dell'angolo di precessione per l'orbita di Mercurio è stato uno dei primi successi della relatività generale.
