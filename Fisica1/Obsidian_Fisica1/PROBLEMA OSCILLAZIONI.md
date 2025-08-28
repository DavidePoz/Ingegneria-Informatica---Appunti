# ==OSCILLATORE ARMONICO SMORZATO E RISONANZA==
Una massa $m$ è appesa ad una molla di costante elastica $k$ e lunghezza $l_{0}$.
La massa della molla è trascurabile. Si scomponga il moto lungo l'asse verticale (asse $z$) e si tratti il problema in una dimensione.
## 1 - TRASCURIAMO LA FORZA PESO
#### 1.1 DETERMINARE LA LEGGE ORARIA
Se la molla è ferma è a distanza $l_{0}$ dal soffitto: scegliamo allora tale quota per fissare $z=0$. Con tale scelta troviamo:
$$
F_{el}=-kz
$$
Da cui ricaviamo:
$$
ma=-kz
$$
E quindi:
$$
\frac{d^2 z(t)}{dt^2}=-\frac{k}{m}z(t) \tag{1}
$$

La cui soluzione è:
$$
z(t)=A\cos(\omega t + \phi) \tag{2}
$$
Per determinare $\omega$, sostituiamo $\eqref{eq 2}$ in $\eqref{eq 1}$:
$$
\frac{d^2}{dt^2}\left( A\cos(\omega t + \phi) \right)=-\frac{k}{m}A\cos(\omega t + \phi)
$$
$$
-\omega^2\cancel{ A\cos(\omega t + \phi) }=-\frac{k}{m}\cancel{ A\cos(\omega t + \phi) }
$$
E concludiamo che
$$
\omega=\sqrt{ \frac{k}{m} } \tag{3}
$$
L'equazione $\eqref{eq 1}$ è un'EDO di secondo grado, quindi la soluzione più generale deve dipendere da due parametri che saranno determinati dalle condizioni iniziali: nel nostro caso $A$ e $\phi$.
#### 1.2 DETERMINARE TALI PARAMETRI
Assumiamo che a $t=0$ la posizione e velocità valgano rispettivamente $z_{0}$ e $v_{0}$.
Calcoliamo $v(t)$:
$$
v(t)=\frac{dz(t)}{dt}-A\omega\sin(\omega t + \phi) \tag{4}
$$
Ora imponiamo:
$$
\begin{cases}
v(0)=v_{0}=-A\omega \sin(0 + \phi) \\ \\
z(0)=z_{0}=A\cos(\phi) 
\end{cases}
$$
Dal rapporto fra le due equazioni troviamo:
$$
\frac{v_{0}}{z_{0}}=-\frac{A\omega \sin \phi}{A\cos \phi}=-\omega \tan \phi
$$
Da cui
$$
\tan \phi=-\frac{v_{0}}{\omega z_{0}} \tag{5}
$$
Dalla prima equazione, invece:
$$
A\sin \phi=-\frac{v_{0}}{\omega}
$$
Sommiamo allora i quadrati delle due equazioni trovando:
$$
A^2\cancelto{ 1 }{ (\sin^2\phi + \cos^2\phi) }=\frac{v_{0}^2}{\omega^2}+z_{0}^2
$$
E allora:
$$
A=\sqrt{ \left( \frac{v_{0}}{\omega} \right)^2+z_{0}^2 } \tag{6}
$$

> [!warning] NOTA
> In $\eqref{eq3}$ avremmo potuto scegliere anche $\omega'=-\sqrt{ \frac{k}{m} }$, il che avrebbe semplicemente portato a trovare un valore diverso di $\phi$, che in ogni caso è arbitrario e dipende dalle condizioni al contorno.

## 2 - CONSIDERIAMO LA FORZA PESO
Sia $l_{r}$ la lunghezza di riposo della molla nella posizione verticale, cioè la lunghezza tale per cui la risultante delle forze è nulla.
#### 2.1 DETERMINARE LA LUNGHEZZA DI RIPOSO
L'equazione associata al problema è:
$$
ma=-kz-mg \tag{7}
$$
Siccome a riposo la forza sulla molla è nulla, eguagliamo $\eqref{eq:7}$ a 0 e troviamo:
$$
z_{rip}=-\frac{m}{k}g \tag{8}
$$
Il valore trovato è negativo, infatti la forza peso abbassa la posizione di riposo.
Siccome avevamo fissato $z=0$ a distanza $l_{0}$ dal soffitto, avremo:
$$
l_{r}=l_{0}+|z_{rip}|=l_{0}+\frac{m}{k}g \tag{9}
$$
#### 2.2 DETERMINARE LA LEGGE ORARIA IN QUESTO CASO
Da $\eqref{eq:7}$ troviamo l'equazione differenziale seguente:
$$
\frac{d^2}{dt^2}z(t)+\frac{k}{m}z(t)=-g \tag{10}
$$
Sappiamo che la soluzione di un'EDO non omogenea è data dalla soluzione dell'omogena associata, a cui dobbiamo sommare una soluzione particolare del caso non omogeneo.
L'equazione omogenea associata, in questo caso, ha la stessa forma di $\eqref{eq1}$, per cui la soluzione sarà analoga.
Una soluzione particolare, invece, è data dal caso in cui la molla è ferma nella posizione di riposo:
$$
z_{\star}(t)=z_{rip} \tag{11}
$$
Notiamo infatti che $\eqref{eq:11}$ soddisfa $\eqref{eq:10}$.
Allora la soluzione al problema è:
$$
z(t)=z_{rip}+A\cos(\omega t+\phi) \tag{12}
$$
Che possiamo riscrivere come:
$$
z(t)-z_{rip}=A\cos(\omega t + \phi) \tag{13}
$$
Definiamo $\bar{z}(t)=z(t)-z_{rip}$ e troviamo:
$$
\bar{z}(t)=A\cos(\omega t + \phi) \tag{14}
$$
Notiamo che le equazioni $\eqref{eq14}$ e $\eqref{eq2}$ hanno la stessa forma e differiscono solamente per la posizione di riposo, che è stata spostata dall'azione della forza peso.
Da qui in avanti lavoreremo quindi con il sistema di riferimento $\bar{z}$ in cui $\bar{z}=0$ corrisponde a $z=z_{rip}$.
## 3 - CONSIDERIAMO L'ATTRTITO VISCOSO
Consideriamo ora anche una forza di attrito viscoso che agisce sulla massa, con coefficiente d'attrito $b$.
#### 3.1  DETERMINARE LA LEGGE ORARIA
In questo caso abbiamo, ricordando che stiamo usando $z_{rip}$ come posizione $z=0$:
$$
F_{TOT}=ma=-kz-bv \tag{15}
$$
E otteniamo l'equazione differenziale:
$$
\frac{d^2z(t)}{dt^2}+\frac{b}{m} \frac{dz(t)}{dt}+\frac{k}{m}z(t)=0 \tag{16}
$$
Chiamiamo $\gamma=\frac{b}{2m}$ e ricordiamo che $\frac{k}{m}=\omega^2$. Allora l'equazione diventa:
$$
\frac{d^2z(t)}{dt^2}+2\gamma \frac{dz(t)}{dt}+\omega^2z(t)=0 \tag{17}
$$
Proviamo una soluzione del tipo $z(t)=Ae^{\lambda t}$. Sostituendo in $\eqref{eq:17}$ e con un po' di algebra troviamo:
$$
Ae^{\lambda t}(\lambda^2+2\gamma\lambda+\omega^2)=0 \tag{18}
$$
Una soluzione banale è data da $A=0$.
Una soluzione non banale è data da $\lambda^2+2\gamma\lambda+\omega^2=0$.
Le soluzioni a tale equazione di secondo grado in $\lambda$ sono:
$$
\lambda_{1,2}=-\gamma\pm \sqrt{ \gamma^2-\omega^2 }
$$
Allora la soluzione più generale sarà del tipo
$$
z(t)=Be^{( -\gamma+ \sqrt{ \gamma^2-\omega^2 } )t} + Ce^{( -\gamma - \sqrt{ \gamma^2-\omega^2 } )t} \tag{19}
$$
Che anche in questo caso dipende da due parametri, in quanto soluzione a un'EDO di secondo grado.

> [!warning] NOTA
> Può essere $\gamma^2-\omega^2< 0$ cioè $\frac{b^2}{4m^2}< \frac{k}{m}$. Ciò avviene se la forza d'attrito è molto piccola rispetto a quella elastica. In tal caso, $\lambda_{1}$ e $\lambda_{2}$ sarebbero complessi, e dovremmo imporre che la soluzione sia complessivamente reale.
##### 3.1.1 CASO $\gamma > \omega$ (FORTE SMORZAMENTO)
In tal caso abbiamo:
- $\lambda_{1}=-\gamma+ \sqrt{ \gamma^2-\omega^2 } <0$
- $\lambda_{2}=-\gamma - \sqrt{ \gamma^2-\omega^2 } <0$
E complessivamente $\lambda_{2} < \lambda_{1} < 0$.

```tikz
\usepackage{tikz} 

\begin{document}

\begin{tikzpicture}[domain=0:8]
  \draw[very thin,color=gray] (-0.1,-0.1) grid (8.1,4.1);

  \draw[->] (-0.2,0) -- (8.2,0) node[right] {$t$};
  \draw[->] (0,-0.2) -- (0,4.2) node[above] {$z(t)$};

  \draw[color=blue, smooth] plot (\x,{4*exp(-\x)}) node[left=170pt, above] {$\lambda_2$};
  \draw[color=red, smooth] plot (\x,{4*exp(-0.5*\x)}) node[above] {$\lambda_1$};
\end{tikzpicture}

\end{document}
```

Nel grafico assumiamo $B=C$ per semplicità, ma tali parametri dipendono dalle condizioni iniziali $z_{0}$ e $v_{0}$:
$$
\begin{cases}
z_{0}=B+C \\
v_{0}=\lambda_{1}B+\lambda_{2}C
\end{cases}
$$
##### 3.1.2 CASO $\gamma <\omega$ (OSCILLAZIONI SMORZATE)
Siamo nel caso della nota.
Abbiamo:
$$
\lambda_{1,2}=-\gamma\pm i\sqrt{ |\gamma^2-\omega^2| }
$$
Allora $\eqref{eq:19}$ diventa:
$$
z(t)=Be^{( -\gamma+ i\sqrt{ |\gamma^2-\omega^2| } )t} + Ce^{( -\gamma - i\sqrt{ |\gamma^2-\omega^2 |} )t} \tag{20}
$$
Che possiamo scrivere come:
$$
z(t)=e^{ -\gamma t }\big(Be^{i\sqrt{ |\gamma^2-\omega^2| } t } + Ce^{- i\sqrt{ |\gamma^2-\omega^2 |} t}\big) \tag{21}
$$
Dato che la soluzione è complessa, anche $B$ e $C$ sono complessi.
Diciamo: $B=x+iy$ e $C=r+iw$ con $x,y,r,w \in\mathbb{R}$.
Chiaramente deve essere $z(t)\in\mathbb{R} \text{ }\forall t$. Dobbiamo allora imporre che $z(t)$ sia uguale al suo coniugato:
$$
\bar{z}(t)=e^{ -\gamma t }\bigg( (x-iy)e^{ -i\sqrt{ \dots }t }+(r-iw)e^{ i\sqrt{ .. }t } \bigg) \tag{22}
$$
Eguagliando quindi $\eqref{eq:21}$ e $\eqref{eq:22}$ troviamo $x=r$ e $y=-w$ e, con alcuni passaggi algebrici, arriviamo alla forma:
$$
z(t)=2A\underbrace{ \left( \frac{e^{ i\sqrt{ \dots }t } + e^{ -i\sqrt{ \dots }t }}{2} \right) }_{ =\cos(\dots) }+2B\underbrace{ \left( \frac{e^{ -i\sqrt{ \dots }t } - e^{ i\sqrt{ \dots }t }}{2i} \right) }_{ =-\sin(\dots) } \tag{23}
$$
Che, in quanto combinazione lineare di $\sin$ e $\cos$, può essere riscritta nella forma:
$$
z(t)=e^{ -\gamma t }A\cos(\sqrt{ |\gamma^2-\omega^2| }t + \phi) \tag{24}
$$

```tikz
\usepackage{tikz} 

\begin{document}

\begin{tikzpicture}[domain=0:10]
  \draw[very thin,color=gray] (-0.1,-4.1) grid (10.1,4.1);

  \draw[->] (-0.2,0) -- (10.2,0) node[right] {$x$};
  \draw[->] (0,-4.2) -- (0,4.2) ;

  \draw[color=red, smooth,thick] plot (\x,{4*exp(-0.25*\x)*cos(\x r)}) node[left=150pt, above=20pt] {$z(t)$};
  \draw[color=gray, smooth] plot (\x,{4*exp(-0.25*\x)});
  \draw[color=gray, smooth] plot (\x,{-4*exp(-0.25*\x)});
\end{tikzpicture}

\end{document}
```

## 4 - FORZA PERIODICA ESTERNA
> [!info] RISONANZA
> Si può verificare quando un corpo *elastico* è soggetto a una forza periodica che amplifica le vibrazioni "naturali" del corpo stesso.

#### 4.1 DETERMINARE LA LEGGE ORARIA
Assumiamo che sul sistema agisca una forza esterna periodica diretta lungo l'asse $z$ e della forma:
$$
F_{ext}=F_{0}\sin(\Omega t)
$$
L'equazione omogenea $\eqref{eq:17}$ diventa:
$$
\frac{d^2z(t)}{dt^2}+2\gamma \frac{dz(t)}{dt}+\omega^2z(t)=\frac{F_0}{m}\sin(\Omega t) \tag{25}
$$
Ancora una volta, sappiamo che la soluzione a tale equazione sarà data dalla somma della soluzione all'equazione omogenea associata (che è proprio $\eqref{eq:17}$) e di una soluzione particolare.
Non ci resta che determinare quest'ultima.
Proviamo con una del tipo:
$$
z(t)=K\sin(\Omega t+\phi) \tag{26}
$$
Sostituendo in $\eqref{eq:25}$ troviamo:
$$
\begin{matrix}
\sin(\Omega t)\underbrace{ \left( -K\Omega^2\cos \phi+\omega^2K\cos \phi-2\gamma K\Omega \sin \phi- \frac{F_{0}}{m} \right) }_{ =A } + \\ \\
+ \cos(\Omega t)\underbrace{ \left( -K\Omega^2\sin \phi+\omega^2K\sin \phi+2\gamma K\Omega \cos \phi \right) }_{ =B } = 0
\end{matrix} \tag{27}
$$
E tale equazione deve valere $\forall t$.
In particolare, notiamo che:
- Per $t=0$ si ha $B=0$
- Per $t=\frac{\pi}{2\Omega}$ si ha $A=0$

Otteniamo così delle equazioni più semplici su cui lavorare.
Alla fine troviamo:
$$
\tan \phi=-\frac{2\gamma\Omega}{\omega^2-\Omega^2} \tag{28}
$$
E
$$
K=\frac{F_{0}}{m}\sqrt{ \frac{1}{(\omega^2-\Omega^2)^2+4\gamma^2\Omega^2} } \tag{29}
$$
La soluzione più generale al problema allora è:
$$
z(t)=K\sin(\Omega t+\phi) + \underbrace{ Be^{ \lambda_{1}t } + Ce^{ \lambda_{2}t } }_{ \text{caso omogeneo} } \tag{30}
$$
#### 4.2 RISONANZA
> [!warning] NOTA
> Per $\gamma t\gg 1$ si ha $|Be^{ \lambda_{1}t } + Ce^{ \lambda_{2}t}|\ll 1$ perchè esponenziale decrescente in entrambi i casi visti.
> Per cui, per $t\to \infty$ si ha $z(t) \approx K\sin(\Omega t+\phi)$.

Ma $K$ **non** dipende dalle condizioni iniziali: dipende solo da $\Omega$ e $\omega$ (proporzionali alle frequenze di vibrazione del corpo e della forza esterna), $F_{0}$ (ampiezza delle oscillazioni della forza esterna), e $\gamma$ ed $m$ (parametri "intrinsechi" della molla). 

```tikz
\usepackage{tikz} 

\begin{document}

\begin{tikzpicture}[domain=0:7]
  \draw[very thin,color=gray] (-0.1,-0.1) grid (7.1,6.1);

  \draw[->] (-0.2,0) -- (7.2,0) node[right] {$\Omega$};
  \draw[->] (0,-0.2) -- (0,6.2) node[right] {$K(\Omega)$};

  \draw[color=red, smooth,thick] plot (\x,{ 1/((0.85-\x)^2+0.3*\x^2) } );

  \draw[dotted, thick] (0.615,0) -- (0.615,5.747);

  \draw (0.7,-0.2) node {$\approx \omega$};
\end{tikzpicture}

\end{document}
```

Ma $\gamma$ è molto piccolo, per cui possiamo dire:
$$
K \approx \frac{F_{0}}{m} \frac{1}{\omega^2-\Omega^2} \tag{31}
$$
Che ci permette di intuire che il picco evidenziato nel grafico si verifica per $\Omega \approx \omega$.
Per trovare il valore esatto di $\Omega$ per cui $K$ assume valore massimo, deriviamo $\eqref{eq:29}$ rispetto a $\Omega$:
$$
\frac{dK}{d\Omega} = \frac{2F_{0}}{m} \frac{\Omega(\omega^2-2\gamma^2-\Omega^2)}{((\omega^2-\Omega^2)^2+4\gamma^2\Omega^2)^{3/2}} \tag{32}
$$
Imponendo che questa sia uguale a $0$ troviamo:
- $\Omega=0$, caso banale per cui la forza esterna è costante.
- $\Omega^2=\omega^2-2\gamma^2$, che per $\gamma$ piccolo coincide con la stima derivante da $\eqref{eq:31}$.

Abbiamo quindi la frequenza di risonanza:
$$
\Omega_{ris}=\sqrt{ \omega^2 - 2\gamma^2} \tag{33}
$$
Per cui si ha la massima ampiezza delle oscillazioni, dovuta proprio alla risonanza:
$$
K_{ris}=\frac{F_{0}}{2m\gamma} \frac{1}{\sqrt{ \omega^2 - \gamma^2 }} \tag{34}
$$
Tornando a $\eqref{eq:28}$ e sostituendo il valore di $\Omega_{ris}$ troviamo:
$$
\tan \phi_{ris}=-\frac{2\gamma\Omega}{2\gamma^2}=-\frac{\Omega}{\gamma} \tag{35}
$$
Ma essendo $\gamma\ll\Omega$, si ha $\tan \phi_{ris}\to \infty$ e:
$$
\phi_{ris} \approx \frac{\pi}{2} \tag{36}
$$
Ciò significa che, nella situazione di risonanza, vi è uno *sfasamento* di $90°$ tra la *forza esterna* e l'*oscillazione del corpo* (*quadratura di fase*).
Ma anche lo sfasamento tra l'oscillazione e la *velocità della molla* è di $90°$ (essendo $v(t)=\frac{d}{dt}z(t)$ ed essendo $z(t)$ sinusoidale). 
Per cui **velocità della molla** e **forza esterna** sono **in fase**, e questo fa si che la forza esterna "spinga" la molla esattamente quando questa si sta muovendo nella stessa direzione, amplificandone le oscillazioni.




