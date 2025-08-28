# ==SISTEMI INERZIALI E NON INERZIALI==
Abbiamo visto che i *sistemi inerziali* sono sistemi di riferimento su cui la risultante delle forze agenti è nulla, e si muovono quindi con velocità costante in modulo e direzione.

Tuttavia, esistono anche sistemi su cui agisce una forza netta non nulla e che si muovono pertanto di un moto diverso da quello rettilineo uniforme: tali sistemi sono definiti **non inerziali**.

In realtà nell'universo non esiste alcun sistema di riferimento perfettamente inerziale, e la Terra stessa, ruotando intorno al proprio asse e intorno al Sole, è un sistema non inerziale. Questo è sufficiente a giustificare il nostro interesse per lo studio di tali sistemi.
## SISTEMI NON INERZIALI
Consideriamo un riferimento inerziale $O$ e uno non inerziale $O'$:
$O'$ può spostarsi rispetto a $O$ e gli assi $\vec{u}_{x}, \vec{u}_{y}, \vec{u}_{z}$ possono ruotare.

```tikz
\usepackage{tikz} 

\begin{document}

\begin{tikzpicture}[>=stealth]
  \draw [->] (0,0,0) -- (2,0,0) node [at end, right] {$\vec{u}_x$};
  \draw [->] (0,0,0) -- (0,2,0) node [at end, left]  {$\vec{u}_y$};
  \draw [->] (0,0,0) -- (0,0,2.5) node [at end, left]  {$\vec{u}_z$};
  \filldraw (0,0,0) circle (1pt) node[left]{$O$};

  \draw [->][rotate around z=15, rotate around y=15] (5,1,0) -- (7,1,0) 
  node [at end, right] {$\vec{u}'_x$};
  \draw [->][rotate around z=15, rotate around y=15] (5,1,0) -- (5,3,0) 
  node [at end, left]  {$\vec{u}'_y$};
  \draw [->][rotate around z=15, rotate around y=15] (5,1,0) -- (5,1,3.5) 
  node [at end, right]  {$\vec{u}'_z$};
  \filldraw[rotate around z=15, rotate around y=15] (5,1,0) circle(1pt) 
  node[right=5pt, above=1pt]{$O'$};

  \filldraw (3,4,1) circle (1pt) node (P) [above=2pt]{$P$};
  \draw[->][blue][rotate around z=15, rotate around y=15] (0,0,0) -- (5,1,0) 
  node[midway, below] {$\vec{OO'}$};
  \draw [->][red] (0,0,0) -- (3,4,1) node[midway, left] {$\vec{OP}$};
  \draw [->][green] (4.9,2.7,0) -- (3,4,1)node[midway, below] {$\vec{O'P}$};
  
\end{tikzpicture}

\end{document}
```

Sia $P$ un punto materiale. Definiamo:
- $\vec{OP}(t)=\vec{r}(t)$ la posizione di $P$ rispetto al sistema $O$.
- $\vec{O'P}=\vec{r}'(t)$ la posizione di $P$ rispetto al sistema $O'$.
- $\vec{OO'}(t)=\vec{r}_{OO'}(t)$ la posizione dell'origine $O'$ rispetto ad $O$.

Abbiamo
$$
\vec{r}(t) = \vec{r}_{OO'}(t) + \vec{r}'(t) \tag{1}
$$
Deriviamo questa equazione per trovare la legge di trasformazione di $\vec{v}$.
Per farlo, scriviamo i vettori esplicitando le loro componenti:
$$
\vec{r}(t) = x(t)\vec{u}_{x} + y(t)\vec{u}_{y} + z(t)\vec{u}_{z} \tag{2}
$$
$$
\vec{r}_{OO'}(t) = x_{OO'}(t)\vec{u}_{x} + y_{OO'}(t)\vec{u}_{y} + z_{OO'}(t)\vec{u}_{z} \tag{3}
$$
$$
\vec{r}'(t) = x'(t)\vec{u}_{x}(t) + y'(t)\vec{u}_{y}(t) + z'(t)\vec{u}_{z}(t) \tag{4}
$$
**OSS.** In ==(4)== gli assi non sono fissi: possono ruotare.
Ricordiamo inoltre la derivata di un versore rotante:
$$
\frac{d}{dt}\vec{u}(t) = \frac{d\theta}{dt}\vec{u}_{\perp}(t) \tag{5}
$$
Per comodità, introduciamo un vettore $\vec{\omega}(t)$ **ortogonale al piano** di rotazione di $\vec{u}(t)$, di modulo $| \frac{d\theta}{dt} |$ e tale che:
$$
\frac{d\vec{u}(t)}{dt} = \vec{\omega}(t) \times \vec{u}(t) \tag{6}
$$

**OSS.** La direzione di $\vec{\omega}\times \vec{u}$ è la stessa di $\vec{u}_{\perp}$.
Derivando ==(1)== troviamo:
$$
\frac{d\vec{r}(t)}{dt} = \frac{d\vec{r}_{OO'}(t)}{dt} + \frac{d\vec{r}(t)}{dt} \tag{7}
$$
E calcolando ciascuna derivata con le informazioni ==(2,3,4)== e ==(6)== otteniamo:
$$
\vec{v}(t) = \vec{v}_{OO'}(t) + \vec{v}'(t) + \vec{\omega}(t) \times \vec{r}'(t) \tag{8}
$$
Oltre ai termini "banali" abbiamo trovato un terzo termine dovuto al fatto che $O'$ ruota rispetto ad $O$.
Similmente, per arrivare alla legge di trasformazione dell'accelerazione, deriviamo ==(8)==, introducendo ancora una volta un vettore per comodità:
$$
\vec{\alpha}(t) = \frac{d\vec{\omega}(t)}{dt} \tag{9}
$$
Otteniamo alla fine:
$$
\vec{a}(t) = \underbrace{ \vec{a}_{OO'}(t) + \vec{a}'(t) }_{ \text{Banali} } + \underbrace{ \vec{\alpha}(t)\times \vec{r}'(t) }_{ A } + \underbrace{ 2\vec{\omega}(t)\times \vec{v}'(t) }_{ B } + \underbrace{ \vec{\omega}(t) \times \big(\vec{\omega}(t)\times \vec{r}'(t) \big) }_{ C } \tag{10}
$$
Abbiamo ottenuto due termini che ci aspettavamo per la forma di ==(1)==, e altri tre termini assolutamente non banali:
- $A$ è dovuto all'accelerazione angolare di $O'$: scompare se questo ruota con velocità angolare costante.
- $B$ è detto *accelerazione di Coriolis* e si manifesta quando un corpo si muove con un certa velocità in un sistema di riferimento in rotazione.
- $C$ è l'*accelerazione centripeta* su un corpo in $O'$ causata dalla rotazione del sistema stesso.

A questo punto, deduciamo che:
$$
\vec{a}(t) - \vec{a}'(t) \ne 0 \tag{11}
$$
Cioè le accelerazioni misurate nei due sistemi **non sono le stesse** e allora è anche vero che:
$$
\vec{F}_{TOT} - \vec{F}'_{TOT} \ne 0 \tag{12}
$$
Chiamiamo tale differenza **forza apparente**, per enfatizzare il fatto che è percepita solo nel sistema di riferimento non inerziale a causa del fatto che non si muove di moto rettilineo uniforme.
$$
\vec{F}_{app} = \vec{F}' - \vec{F} = m(\vec{a}' -\vec{a}) \tag{13}
$$
## IL SISTEMA ROTANTE TERRA
Sappiamo che la Terra *non* è un sistema inerziale, per cui siamo soggetti a forze apparenti.

Consideriamo un punto materiale *fermo* alla latitudine $\Theta$ e un sistema di assi così orientati:
- $\vec{u}_{z}$ verso il cielo,
- $\vec{u}_{x}$ verso est,
- $\vec{u}_{y}$ verso nord.

Il vettore $\vec{\omega}$ associato alla rotazione terrestre sarà pertanto:
$$
\vec{\omega} = \omega \cos\Theta \vec{u}_{y} + \omega\sin\Theta \vec{u}_{z} \tag{14}
$$
Con $\omega=\frac{2\pi}{24\cdot 3600}\approx 7.27\cdot 10^{-5} \frac{rad}{s}$.
Essendo il punto fermo sulla superficie, *non* risente dell'accelerazione di Coriolis, e neanche del termine dovuto all'accelerazione angolare dato che la terra ruota a velocità angolare costante.
Per cui il punto risente di una forza apparente data da:
$$
\vec{F}_{app} = -m\vec{\omega} \times (\vec{\omega} \times \vec{r}) \tag{15}
$$
Dove $\vec{r} = R_{T}\vec{u}_{z}$ con $R_{T}\approx 6.38\cdot 10^{6}m$.
Notiamo che tale forza è opposta alla forza *centripeta* che misurerebbe un osservatore esterno in un sistema inerziale. La forza apparente associata alla forza centripeta è la **forza centrifuga**.
Da ==(15)== ricaviamo $\vec{a}_{app}$:
$$
\vec{a}_{app} = -\vec{\omega} \times (\vec{\omega} \times \vec{r}) = \omega^2R_{T}(\cos^2\Theta \vec{u}_{z} -\cos\Theta \sin\Theta \vec{u}_{y} ) \tag{16}
$$
Allora abbiamo $\vec{a}'=\vec{a}+\vec{a}_{app}$:
$$
\vec{a}' = -g\vec{u}_{z} + \vec{a}_{app} = -\underbrace{ \big( g-\omega^2R_{T}\cos^2\Theta \big) }_{ =g_{eff,z} }\vec{u}_{z} - \underbrace{ \big( \omega^2R_{T}\cos\Theta \sin\Theta \big) }_{ =g_{eff,y} }\vec{u}_{y} \tag{17}
$$
Notiamo che $g_{eff,z} < g$, cioè l'accelerazione di gravità da noi percepita è minore di quella "reale" a causa della forza centrifuga (apparente).
### ESPERIMENTO DI GUGLIELMINI
Per dimostrare il moto di rotazione della Terra, nel 1790 Guglielmini fece cadere delle sferette di metallo dalla sommità della torre degli Asinelli, a Bologna: se fossero state soggette alla sola forza di gravità sarebbero dovute cadere lungo una traiettoria verticale; invece, erano deviate di una certa distanza verso est.

Per semplicità, consideriamo una torre analoga posta all'equatore anzichè a Bologna.
Calcoliamo la deviazione rispetto alla base della torre, ricordando che per un corpo in caduta libera abbiamo:
$$
m\vec{a}' = \vec{F}_{P} + \vec{F}_{app} \implies \vec{a}' = -g\vec{u}_{z} -m\vec{\omega}\times(\vec{\omega}\times \vec{r}) - 2\vec{\omega}\times \vec{v} \tag{18}  
$$
I primi due termini equivalgono a $g_{eff}$ calcolata sopra, con $\Theta=0$, mentre il terzo termine è l'accelerazione di Coriolis.
$$
\vec{a}' = -(g-\omega^2R_{T})\vec{u}_{z} -2\omega \vec{u}_{y}\times v_{z}(t)\vec{u}_{z} \tag{19}
$$
Dove $\vec{\omega}$ è solo lungo $\vec{u}_{y}$ e la velocità è in buona approssimazione lungo $\vec{u}_{z}$ (lungo la verticale).
Troviamo allora:
$$
\vec{a}' = -g_{eff,z}\vec{u}_{z} -2\omega v_{z}(t)\vec{u}_{x} \tag{20}
$$
In componenti, lungo $\vec{u}_{z}$:
$$
a'_{z} = -g_{eff,z} \implies \begin{cases}
v_{z}(t) = -g_{eff,z}t \\ \\
z(t) = h-\frac{1}{2}g_{eff,z}t^2
\end{cases} \tag{21}
$$
Lungo $\vec{u}_{x}$:
$$
a'_{x} = (2\omega g_{eff,z})t \implies \begin{cases}
v_{x}(t) = \omega g_{eff,z}t^2 \\ \\
x(t) = \frac{1}{3}\omega g_{eff,z}t^3
\end{cases} \tag{22}
$$
 > [!warning] NOTA
 > Abbiamo una certa velocità lungo $\vec{u}_{x}$ che dovrebbe contribuire in ==(18)==, tuttavia $\omega$ è sufficientemente piccola in modulo da rendere trascurabile questa componente.

Per trovare la deviazione di Coriolis lungo $\vec{u}_{x}$ troviamo prima il tempo di caduta $t_{c}$ imponendo $z(t_{c})=0$:
$$
t_{c} = \sqrt{ \frac{2h}{g_{eff,z}} } \tag{23}
$$
E da questo ricaviamo $x(t_{c})=\Delta x$:
$$
\Delta x = \frac{1}{3}\omega g_{eff,z} \left( \frac{2h}{g_{eff,z}} \right)^{3/2} \tag{24}
$$
L'ordine di grandezza di tale deviazione è di qualche centimetro.