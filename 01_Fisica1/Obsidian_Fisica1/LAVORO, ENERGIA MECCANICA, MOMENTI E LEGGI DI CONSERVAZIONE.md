# ==LAVORO ED ENERGIA==
Consideriamo una forza $\vec{F}$, che nel caso più generale dipende dalla posizione $\vec{r}$ di un corpo, dalla sua velocità $\vec{v}$ e dal tempo $t$: $\vec{F}(\vec{r},\vec{v},t)$.
Definiamo il **lavoro della forza** lungo la *traiettoria del corpo* tra l'istante $t_{0}$ e $t_{1}$ come:
$$
W_{F}=\int_{t_{0}}^{t_{1}}\left[ \vec{F}\left(\vec{r}(t),\vec{v}(t),t\right)\cdot \vec{v}(t) \right]dt \tag{1}
$$
In questo caso la traiettoria è quella determinata dalla stessa forza $\vec{F}$ della quale stiamo calcolando il lavoro.
E' possibile dare una definizione più *generale* del lavoro.
Per farlo, consideriamo una generica curva $\Gamma$ descritta dal vettore $\vec{\gamma}(\tau)$ al variare di $\tau$:
$$
\Gamma=\{ \left( \tau,\vec{\gamma}(\tau) \right)\text{ , }\tau\in \left[ \tau_{0},\tau_{1} \right] \} \tag{2}
$$
La curva $\Gamma$ *non è necessariamente la legge oraria* del corpo su cui agisce la forza.
Per questo motivo usiamo un generico parametro $\tau$ invece che $t$, il vettore $\vec{\gamma}(\tau)$ invece della legge oraria $\vec{r}(t)$ e la derivata $\frac{d \vec{\gamma}(\tau)}{d\tau}$ invece della velocità $\vec{v}(t)$.
Definiamo allora il **lavoro di una forza lungo una generica curva $\Gamma$** come:
$$
W_{F}\left[ \Gamma \right] = \int_{\tau_{0}}^{\tau_{1}} \vec{F}\left( \vec{\gamma}(\tau), \frac{d\vec{\gamma}(\tau)}{d\tau}, \tau \right)\cdot \frac{d\vec{\gamma}}{d\tau}d\tau \tag{3}
$$
## PROPRIETA'
Date due forze $\vec{F}_{1}$ e $\vec{F}_{2}$ e una curva $\Gamma$ vale:
$$
W_{\vec{F}_{1}+\vec{F}_{2}}\left[ \Gamma \right] = W_{\vec{F}_{1}}\left[ \Gamma \right] + W_{\vec{F}_{2}}\left[ \Gamma \right] \tag{4}
$$
E questo vale proprio perchè possiamo considerare una qualsiasi curva.
Se $\Gamma$ fosse dovuta essere la *legge oraria* data dalla forza considerata, avremmo avuto curve diverse nel caso avessimo considerato solo una o l'altra forza, oppure entrambe contemporaneamente.
## TEOREMA DELL'ENERGIA CINETICA
Poniamoci ora nel caso $\vec{\gamma}(\tau)=\vec{r}(t)$, cioè il caso in cui $\Gamma$ è proprio la legge oraria del corpo sottoposto a $\vec{F}$.
Allora:
- $\frac{d \vec{\gamma}(\tau)}{d\tau}=\vec{v}(t)$
- $\frac{d^2 \vec{\gamma}(\tau)}{d\tau^2}=\vec{a}(t)$

Ma allora:
$$
\vec{F}\left( \vec{\gamma}, \frac{d\vec{\gamma}}{d\tau},\tau \right) = \vec{F}(\vec{r},\vec{v},t) = m\vec{a} \tag{5}
$$
Da cui segue:
$$
W_{\vec{F}}\left[ \Gamma \right]\bigg|_{\text{Legge oraria}} = \int_{t_{0}}^{t_{1}} m\vec{a}(t) \cdot \vec{v}(t) dt \tag{6}
$$
Che riscriviamo come:
$$
W_{\vec{F}}\left[ \Gamma \right]\bigg|_{\text{Legge oraria}} = m \int_{t_{0}}^{t_{1}} \frac{d\vec{v}(t)}{dt} \cdot \vec{v}(t) dt \tag{7}
$$
A questo punto, osserviamo che:
$$
\frac{d}{dt}(\vec{v} \cdot \vec{v}) = \frac{d\vec{v}}{dt} \cdot \vec{v} + \vec{v} \cdot \frac{d \vec{v}}{dt} = 2 \frac{d\vec{v}}{dt} \cdot \vec{v} \tag{8}
$$
Per cui possiamo riscrivere ==(7)== come:
$$
W_{\vec{F}}\left[ \Gamma \right]\bigg|_{\text{Legge oraria}} = \frac{m}{2} \int_{t_{0}}^{t_{1}} \frac{d}{dt}(\vec{v}(t) \cdot \vec{v}(t))dt \tag{9}
$$
$$
= \frac{m}{2} \int_{t_{0}}^{t_{1}} \frac{d}{dt}\left( v_{x}^2(t) + v_{y}^2(t) + v_{z}^2(t) \right)dt \tag{10}
$$
$$
=\frac{m}{2}\left( v_{x}^2(t_{1}) - v_{x}^2(t_{0}) \right) + 
\frac{m}{2}\left( v_{y}^2(t_{1}) - v_{y}^2(t_{0}) \right) + 
\frac{m}{2}\left( v_{z}^2(t_{1}) - v_{z}^2(t_{0}) \right) \tag{11}
$$
$$
=\frac{m}{2}\left( v_{x}^2(t_{1}) + v_{y}^2(t_{1}) + v_{z}^2(t_{1}) \right) -\frac{m}{2}\left( v_{x}^2(t_{0}) + v_{y}^2(t_{0}) + v_{z}^2(t_{0}) \right) \tag{12}
$$
$$
=\frac{m}{2}||\vec{v}(t_{1})||^2 - \frac{m}{2}||\vec{v}(t_{0})||^2 \tag{13}
$$
A questo punto, definiamo l'**energia cinetica**:
$$
E_{K}=\frac{1}{2}m||\vec{v}||^2 \tag{14}
$$
E troviamo quindi la **relazione** fra **lavoro** ed **energia cinetica**:
$$
W_{\vec{F}}\left[ \Gamma \right]\bigg|_{\text{Legge oraria}} = E_{K,1} - E_{K,0} \tag{15}
$$
> [!info] TEOREMA DELL'ENERGIA CINETICA
> Il lavoro di una forza su un corpo, lungo la legge oraria data dalla forza stessa, è pari alla variazione di energia cinetica del corpo.

## ESEMPI DI CALCOLO DEL LAVORO
### FORZA PESO
Consideriamo la forza peso $\vec{F}_{p}=-mg\vec{u}_{z}$ e un corpo che si muove da $\vec{r}_{0}$ a $\vec{r}_{1}$ lungo $\Gamma$.
Calcoliamo il lavoro di $\vec{F}_{p}$ lungo $\Gamma$:
$$
W_{F_{p}}\left[ \Gamma \right] = \int_{\tau_{0}}^{\tau_{1}} (-mg\vec{u}_{z}) \cdot \frac{d\vec{\gamma}(\tau)}{d\tau}d\tau \tag{16}
$$
$$
=-mg \int_{\tau_{0}}^{\tau_{1}}\vec{u}_{z} \cdot \left( \frac{d\gamma_{x}(\tau)}{d\tau}\vec{u}_{x} + \frac{d\gamma_{y}(\tau)}{d\tau}\vec{u}_{y} + \frac{d\gamma_{z}(\tau)}{d\tau}\vec{u}_{z} \right)
$$
$$
=-mg \int_{\tau_{0}}^{\tau_{1}} \frac{d}{d\tau}\left( \gamma_{z}(\tau) \right)d\tau \tag{17}
$$
E concludiamo quindi che:
$$
W_{F_{p}}\left[ \Gamma \right] = -mg \left( \gamma_{z}(\tau_{1}) - \gamma_{z}(\tau_{0}) \right) \tag{18}
$$
Ma $\gamma_{z}(\tau_{1})=r_{z,1}$ e $\gamma_{z}(\tau_{0}) = r_{z,0}$. Cioè il lavoro della forza peso dipende *solo dai punti inziale e finale*, e **non dalla curva $\Gamma$**.
### FORZA ELASTICA
Consideriamo una forza elastica lungo $\vec{u}_{x}$: $\vec{F}_{el}=-k\Delta x \vec{u}_{x}$, dove $\Delta x = r_{x}-x_{0}$ è lo spostamento della molla rispetto alla posizione di riposo $x_{0}$.
Calcoliamone il lavoro per un corpo che si muove da $x_{0}$ a $x_{1}$ lungo $\Gamma$:
$$
W_{F_{el}}\left[\Gamma\right] = \int_{\tau_{0}}^{\tau_{1}} \left( -k(\gamma_{x}(\tau)-x_{0})\vec{u}_{x} \right) \cdot \frac{d \vec{\gamma}(\tau)}{d\tau}d\tau \tag{19}
$$
$$
= -k \int_{\tau_{0}}^{\tau_{1}} (\gamma_{x}(\tau)-x_{0})\vec{u}_{x} \cdot \left( \frac{d\gamma_{x}(\tau)}{d\tau}\vec{u}_{x} + \frac{d\gamma_{y}(\tau)}{d\tau}\vec{u}_{y} + \frac{d\gamma_{z}(\tau)}{d\tau}\vec{u}_{z} \right) d\tau
$$
$$
= -k \int_{\tau_{0}}^{\tau_{1}} (\gamma_{x}(\tau)-x_{0}) \frac{d\gamma_{x}(\tau)}{d\tau} d\tau
$$
$$
= -k \int_{\tau_{0}}^{\tau_{1}} \gamma_{x}(\tau) \frac{d\gamma_{x}(\tau)}{d\tau} d\tau + kx_{0} \int_{\tau_{0}}^{\tau_{1}} \frac{d\gamma_{x}(\tau)}{d\tau} d\tau
$$
$$
= -\frac{k}{2}\int_{\tau_{0}}^{\tau_{1}} \frac{d}{d\tau}\gamma_{x}^2(\tau) d\tau + kx_{0} \int_{\tau_{0}}^{\tau_{1}} \frac{d}{d\tau} \gamma_{x}(\tau) d\tau
$$
$$
=-\frac{k}{2} \big( \gamma^2_{x}(\tau_{1}) - \gamma_{x}^2(\tau_{0}) \big) +kx_{0}\big( \gamma_{x}(\tau_{1}) - \gamma_{x}(\tau_{0}) \big) \tag{20}
$$
Che con qualche passaggio algebrico possiamo riscrivere come:
$$
W_{F_{el}}\left[\Gamma\right] = -\frac{k}{2}(x_{f}-x_{0})^2 +\frac{k}{2}(x_{i}-x_{0})^2 \tag{21}
$$
Dove $x_{f}=\gamma_{x}(\tau_{1})$ e $x_{i}=\gamma_{x}(\tau_{0})$.
Anche in questo caso il lavoro **non** dipende dalla curva $\Gamma$.
### FORZA DI ATTRITO DINAMICO
Consideriamo un corpo che si muove lungo una curva $\Gamma$ e soggetto ad una forza di attrito dinamico $\vec{F}_{A} = -\mu_{d}||\vec{F}_{R}||\vec{u}_{v}$.
$$
W_{\vec{F}_{A}}\left[ \Gamma \right] = \int_{\tau_{0}}^{\tau_{1}}\left( -\mu_{d}F_{R}\vec{u}_{\frac{d\gamma}{dt}} \right) \cdot \frac{d\vec{\gamma}}{d\tau} d\tau \tag{22}
$$
$$
= -\mu_{d} \int_{\tau_{0}}^{\tau_{1}} F_{R} \bigg\lvert\bigg\lvert \frac{d\vec{\gamma}}{d\tau} \bigg\rvert\bigg\rvert d\tau \tag{23}
$$
Se assumiamo che $F_{R}$ non dipenda da $\tau$ otteniamo:
$$
W_{\vec{F}_{A}}\left[\Gamma\right] = -\mu_{d}F_{R} \int_{\tau_{0}}^{\tau_{1}} \bigg\lvert\bigg\lvert \frac{d\vec{\gamma}}{d\tau} \bigg\rvert\bigg\rvert d\tau \tag{24}
$$
Dove l'integrale che compare è esattamente la lunghezza della curva $\Gamma$.
Allora, se il modulo della reazione vincolare è costante, si ha:
$$
W_{\vec{F}_{A}}\left[\Gamma\right] = -\mu_{d}F_{R}\mathcal{L}(\Gamma) \tag{25}
$$
In questo caso il lavoro **dipende** effettivamente dalla curva $\Gamma$, in particolare è proporzionale alla sua lunghezza.
### FORZA POSIZIONALE
> [!info] FORZA POSIZIONALE
> E' una forza che dipende solamente dalla posizione del corpo su cui agisce.

Sia $\vec{F}=f(x)\vec{u}_{x}$ una forza posizionale (in questo caso dipendente solo dalla posizione lungo l'asse $x$) e consideriamo ancora una volta una generica curva $\Gamma$.
Abbiamo:
$$
W_{\vec{F}}\left[\Gamma\right] = \int_{\tau_{0}}^{\tau_{1}} f\big( \gamma_{x}(\tau) \big)\vec{u}_{x} \cdot \frac{d\vec{\gamma}(\tau)}{d\tau} d\tau \tag{26}
$$
$$
= \int_{\tau_{0}}^{\tau_{1}} f\big( \gamma_{x}(\tau) \big) \cdot \left( \frac{d\gamma_{x}(\tau)}{d\tau}\vec{u}_{x} + \frac{d\gamma_{y}(\tau)}{d\tau}\vec{u}_{y} + \frac{d\gamma_{z}(\tau)}{d\tau}\vec{u}_{z} \right) d\tau
$$
$$
= \int_{\tau_{0}}^{\tau_{1}} f\big( \gamma_{x}(\tau) \big) \frac{d\gamma_{x}(\tau)}{d\tau} d\tau \tag{27}
$$
Ora, sia $\mathcal{F}(x)$ una primitiva di $f(x)$, cioè $\frac{d}{dx}\mathcal{F}(x)=f(x)$.
Allora si ha:
$$
\frac{d}{d\tau}\mathcal{F}\big( \gamma_{x}(\tau) \big) = f\big( \gamma_{x}(\tau) \big) \frac{d\gamma_{x}(\tau)}{d\tau} \tag{28}
$$
Per cui si ha:
$$
W_{\vec{F}}\left[\Gamma\right] = \int_{\tau_{0}}^{\tau_{1}} \frac{d}{d\tau}\mathcal{F}\big( \gamma_{x}(\tau) \big) d\tau = \mathcal{F}\big( \gamma_{x}(\tau_{1}) \big) - \mathcal{F}\big( \gamma_{x}(\tau_{0}) \big) \tag{29}
$$
Cioè:
$$
W_{\vec{F}}= \mathcal{F}( x_{1} ) - \mathcal{F}( x_{0} ) \tag{30}
$$
Ma $\mathcal{F}$ dipende solo da $f$, cioè la forza di cui vogliamo calcolare il lavoro, e non da $\gamma$ o $\frac{d\gamma}{d\tau}$; per cui il lavoro **non** dipende da $\Gamma$.

> [!warning] ATTENZIONE
> Questo solo perchè la forza lungo $x$ dipende dalla posizione lungo $x$ stesso.
> Se avessimo avuto $\vec{F'}=f(x)\vec{u}_{y}$ avremmo trovato una dipendenza dalla curva scelta perchè l'integranda in (27) **non** avrebbe soddisfatto (28).

## FORZE CONSERVATIVE
> [!info] DEFINIZIONE
> Una forza è *conservativa* se il lavoro da essa compiuto dipende *solo* dalla posizione *iniziale* e *finale*, e **non** dal **percorso**.

E' *equivalente* dire:
$$
\oint \left(\vec{F}( \vec{\gamma}) \cdot \frac{d\vec{\gamma}}{d\tau} \right) d\tau =0 \text{ }\Longleftrightarrow \text{ F è conservativa} \tag{31}
$$
Cioè $F$ è conservativa se e solo se il lavoro da essa compiuto lungo una qualsiasi curva chiusa è nullo.
### DIMOSTRAZIONE
1. Se $\vec{F}$ è conservativa, sappiamo che il lavoro compiuto dipende solo dalle posizioni iniziale e finale, e quindi, fissata una curva $\Gamma_{01}$, si ha:
   $W_{\vec{F}}\left[\Gamma_{01}\right] = -W_{\vec{F}}\left[\Gamma_{10}\right]$
   dove $\Gamma_{10}$ è una qualsiasi curva con gli stessi punti iniziale e finale di $\Gamma_{01}$, ma scambiati.
   Ma $\Gamma_{01}+\Gamma_{10}$ è un percorso chiuso (si torna al punto di partenza).
   Allora $W_{\vec{F}}\left[\Gamma_{00}\right]=W_{\vec{F}}\left[\Gamma_{01}\right] + W_{\vec{F}}\left[\Gamma_{10}\right]=0$.
2. Sia $\Gamma_{01}$ una curva fra i punti $0$ e $1$ e sia $\Gamma'_{01}$ una diversa curva tra gli stessi punti.
   $\Gamma = \Gamma_{01}-\Gamma'_{01}$ è un percorso chiuso, per cui vale
   $\oint_{\Gamma}\vec{F}\cdot \frac{d\gamma}{d\tau}d\tau = 0$
   Ma tale integrale è anche uguale a
   $\int_{\Gamma_{01}}\dots d\tau -\int_{\Gamma'_{01}}\dots d\tau$
   Allora segue che
   $\int_{\Gamma_{01}}\dots d\tau=\int_{\Gamma'_{01}}\dots d\tau \implies W_{\vec{F}}\left[\Gamma_{01}\right]=W_{\vec{F}}\left[\Gamma'_{01}\right]$
   Cioè il lavoro dipende solo dalle posizioni iniziale e finale e non dal percorso. 
### FORMA GENERALE DI UNA FORZA CONSERVATIVA
Se una forza $\vec{F}$ è conservativa, il lavoro svolto deve dipendere solo dalle posizioni iniziale e finale, per cui si ha:
$$
W_{\vec{F}}\left[\Gamma_{01}\right] = \mathcal{F}(x_{1},y_{1},z_{1}) - \mathcal{F}(x_{0},y_{0},z_{0}) \tag{32}
$$
Per una qualche $\mathcal{F}(x,y,z)$, con $(x_{0},y_{0},z_{0})$ e $(x_{1},y_{1},z_{1})$ estremi di $\Gamma_{01}$.
Riscriviamo il membro di destra di ==(32)== nel seguente modo:
$$
\int_{\tau_{0}}^{\tau_{1}} \frac{d}{d\tau}\mathcal{F} \big( \gamma_{x}(\tau),\gamma_{y}(\tau),\gamma_{z}(\tau) \big) d\tau \tag{33}
$$
Dove abbiamo sostituito $x,y,z$ con $\gamma_{x}(\tau), \gamma_{y}(\tau), \gamma_{z}(\tau)$ per seguire la curva $\Gamma_{01}$ (chiaramente, per $\tau=\tau_{0}$ si ha $\big( \gamma_{x}(\tau_{0}), \gamma_{y}(\tau_{0}), \gamma_{z}(\tau_{0}) \big)=(x_{0},y_{0},z_{0})$ e per $\tau=\tau_{1}$ $\big( \gamma_{x}(\tau_{1}), \gamma_{y}(\tau_{1}), \gamma_{z}(\tau_{1}) \big)=(x_{1},y_{1},z_{1})$).
Esplicitiamo allora l'integranda di ==(33)==:
$$
\int_{\tau_{0}}^{\tau_{1}} \left[\frac{\partial}{\partial x}\mathcal{F}(x,y,z)\bigg\lvert_{(x,y,z)=(\gamma_{x}(\tau), \gamma_{y}(\tau),\gamma_{z}(\tau))} \frac{d\gamma_{x}(\tau)}{d\tau} + \right.
$$
$$
+ \frac{\partial}{\partial y}\mathcal{F}(x,y,z)\bigg\lvert_{(x,y,z)=(\gamma_{x}(\tau), \gamma_{y}(\tau),\gamma_{z}(\tau))} \frac{d\gamma_{y}(\tau)}{d\tau} + 
$$
$$
\left. + \frac{\partial}{\partial z}\mathcal{F}(x,y,z)\bigg\lvert_{(x,y,z)=(\gamma_{x}(\tau), \gamma_{y}(\tau),\gamma_{z}(\tau))} \frac{d\gamma_{z}(\tau)}{d\tau} \right] d\tau \tag{34}
$$
Notiamo che l'integranda è uguale al prodotto scalare seguente:
$$
\vec{V} \cdot \frac{d\gamma(\tau)}{d\tau} \tag{35}
$$
Dove:
$$
\vec{V}=\left( \frac{\partial}{\partial x}\mathcal{F}(x,y,z), \frac{\partial}{\partial y}\mathcal{F}(x,y,z), \frac{\partial}{\partial z}\mathcal{F}(x,y,z) \right)\bigg|_{(x,y,z)=(\gamma_{x}(\tau),\gamma_{y}(\tau),\gamma_{z}(\tau))} \tag{36}
$$
Tale vettore $\vec{V}$ è il **gradiente** di $\mathcal{F}$:
$$
\vec{\nabla}\mathcal{F} = \left( \frac{\partial}{\partial x}\mathcal{F}, \frac{\partial}{\partial y}\mathcal{F}, \frac{\partial}{\partial z}\mathcal{F} \right) \tag{37}
$$
Allora ==(32)== diventa:
$$
W_{\vec{F}}\left[\Gamma_{01}\right] = \int_{\tau_{0}}^{\tau_{1}} \vec{\nabla}\mathcal{F} \cdot \frac{d\gamma(\tau)}{d\tau} d\tau \tag{38}
$$
Ma sappiamo, dalla definizione di lavoro, che:
$$
W_{\vec{F}}\left[\Gamma_{01}\right] = \int_{\tau_{0}}^{\tau_{1}} \vec{F} \cdot \frac{d\gamma(\tau)}{d\tau} d\tau \tag{39}
$$
Per cui, eguagliando ==(38)== e ==(39)== segue che:
$$
\vec{F} = \vec{\nabla}\mathcal{F} \tag{40}
$$
Allora $\vec{F}$ è **conservativa** se è *gradiente* di una *qualche funzione scalare* $\mathcal{F}$.

Cioè, data una forza $\vec{F}(x,y,z) = f_{x}(x,y,z)\vec{u}_{x} + f_{y}(x,y,z)\vec{u}_{y} + f_{z}(x,y,z)\vec{u}_{z}$,
$\vec{F}$ è conservativa se esiste una funzione $\mathcal{F}:\mathbb{R^3}\to\mathbb{R}$ tale che:
$$
\begin{cases}
f_{x}(x,y,z) = \frac{\partial}{\partial x}\mathcal{F} \\ \\
f_{y}(x,y,z) = \frac{\partial}{\partial y}\mathcal{F} \\ \\
f_{z}(x,y,z) = \frac{\partial}{\partial z}\mathcal{F} 
\end{cases}
$$
> [!info] CONCLUSIONE
> Le forze conservative sono alcune tra le forze posizionali, e per esse vale:
> $W_{\vec{F}}\left[\Gamma_{01}\right] = \mathcal{F}(\vec{r}_{1}) - \mathcal{F}(\vec{r}_{0})$ con $\vec{F}=\vec{\nabla}\mathcal{F}$.
## ENERGIA POTENZIALE
Abbiamo quindi visto che, per una forza conservativa:
- $W_{\vec{F}}\left[\Gamma_{01}\right]=\mathcal{F}(\vec{r}_{1})-\mathcal{F}(\vec{r}_{0})$ lungo una qualsiasi traiettoria. (**I**)
- $W_{\vec{F}}\left[\Gamma_{L.O.}\right]=E_{K}(\vec{v}_{1})-E_{K}(\vec{v}_{0})$ lungo la legge oraria sotto $\vec{F}$. (**II**)

Per cui, lungo la traiettoria data dalla legge oraria, devono valere sia **I** che **II**.
Allora troviamo:
$$
\mathcal{F}(\vec{r}_{1})-\mathcal{F}(\vec{r}_{0}) = E_{K}(\vec{v}_{1})-E_{K}(\vec{v}_{0}) \tag{41}
$$
Che possiamo riscrivere come:
$$
E_{K}(\vec{v}_{0})-\mathcal{F}(\vec{r}_{0}) = E_{K}(\vec{v}_{1})-\mathcal{F}(\vec{r}_{1}) \tag{42}
$$
Quindi lungo la traiettoria della *legge oraria*, sotto l'azione di una forza *conservativa* rimane costante la quantità:
$$
E_{K}-\mathcal{F} \tag{43}
$$
Definiamo allora, per una forza conservativa, l'**energia potenziale**:
$$
E_{P}(\vec{r})=-\mathcal{F}(\vec{r}) \tag{44}
$$
Si definisce a questo punto l'*energia meccanica totale*:
$$
E_{TOT}(\vec{r},\vec{v})=E_{K}(\vec{v})+E_{P}(\vec{r}) \tag{45}
$$
Per quanto visto in ==(42)== e ==(43)==, l'energia meccanica totale è costante lungo la traiettoria sotto l'effetto di una forza conservativa.

> [!warning] NOTA
> Fisicamente si misura solo la **differenza** di energia potenziale, non una "singola" energia potenziale di un corpo in un punto.
> Infatti $\mathcal{F}$, e quindi anche $E_{P}$, è definita a meno di una costante rispetto a $f$.
### ESEMPIO: ENERGIA POTENZIALE GRAVITAZIONALE
Sappiamo che 
$$
\vec{F}_{grav}=-G \frac{m_{1}m_{2}}{\|\vec{r}\|}\vec{u}_{r}
$$
Quindi $\vec{F}_{grav}=f(r)\vec{u}_{r}$ con:
$$
f(r)=-G \frac{m_{1}m_{2}}{r^2}
$$
E abbiamo visto che la forza gravitazionale è conservativa, quindi vale:
$$
f(r) = -\frac{d}{dr}E_{P}(r)
$$
Per cui deve essere:
$$
E_{P,grav} = -G \frac{m_{1}m_{2}}{r} \tag{46}
$$
# ==LEGGI DI CONSERVAZIONE==
Abbiamo visto 
$$
W_{\vec{F}}\left[\Gamma_{01\text{,traiettoria}}\right] = \int_{t_{0}}^{t_{1}}\vec{F} \cdot \frac{d\gamma}{dt}dt = \Delta E_{K}
$$
Possiamo interpretare il teorema dell'energia cinetica nel seguente modo:
la variazione di $E_{K}$ è dovuta al lavoro di una forza, quindi se nessuna forza compie lavoro, l'energia cinetica è conservata.

Ma ci sono anche altre quantità fisiche che si comportano in questo modo.
## IMPULSO
Si definisce l'*impulso di una forza* come segue:
$$
\vec{I}_{\vec{F}}\left[\Gamma_{01}\right] = \int_{t_{0}}^{t_{1}} \vec{F}\left( \vec{\gamma}, \frac{d\vec{\gamma}}{dt}, t \right)dt \tag{47}
$$
**TEOREMA DELL'IMPULSO**
Lungo la traiettoria del moto vale:
$$
\vec{I}_{\vec{F}}\left[\Gamma_{T}\right] = \int_{t_{0}}^{t_{1}} \frac{d\vec{p}}{dt} dt = \vec{p}(t_{1}) - \vec{p}(t_{0}) = \Delta \vec{p} \tag{48}
$$
La variazione della quantità di moto è dovuta all'impulso di una forza. Perciò se l'impulso è nullo, si conserva la quantità di moto.
## MOMENTI
Si definisce il **momento** di una forza rispetto al polo $O$ come segue:
$$
\vec{M}_{\vec{F},O} = \vec{r} \times \vec{F} \tag{49}
$$
dove $\vec{r}$ è il *braccio* della forza, ovvero il vettore che congiunge il polo con il punto di applicazione della forza.
Consideriamo ora il seguente integrale:
$$
\int_{t_{0}}^{t_{1}} \vec{\gamma}(t_{1}) \times \vec{F}\left( \vec{\gamma}, \frac{d\vec{\gamma}}{dt},t \right) dt \tag{50}
$$
Lungo la traiettoria del moto diventa:
$$
\int_{t_{0}}^{t_{1}} \vec{r}(t) \times \underbrace{ \frac{d}{dt} \big( m\vec{v}(t) \big) }_{ \vec{F} } dt = \int_{t_{0}}^{t_{1}}\vec{M}_{\vec{F},O}dt \tag{51}
$$
Ora osserviamo che
$$
\frac{d}{dt}\big(\vec{r}(t) \times m\vec{v}(t) \big) = \cancelto{ 0 }{ \vec{v}(t) \times m\vec{v}(t) } + \vec{r}(t) \times \frac{d}{dt}\big( m\vec{v}(t) \big) \tag{52}
$$
Allora riscriviamo ==(51)== come segue:
$$
\int_{t_{0}}^{t_{1}} \frac{d}{dt} \big( \vec{r} \times m\vec{v} \big) dt \tag{53}
$$
Definiamo allora il **momento angolare** di un punto materiale rispetto a un polo $O$:
$$
\vec{L} = \vec{r} \times \vec{p} = \vec{r} \times m\vec{v} \tag{54}
$$
Dove ancora una volta $\vec{r}$ è il vettore che congiunge il polo $O$ con il punto materiale.
E troviamo infine, sempre lungo la traiettoria del moto:
$$
\int_{t_{0}}^{t_{1}} \vec{M}dt = \vec{L}(t_{1})-\vec{L}(t_{0}) = \Delta \vec{L} \tag{55}
$$
Cioè una variazione di momento angolare è dovuta al momento di una forza. Perciò se nessuna forza esercita momento si ha che il momento angolare è conservato lungo il moto.

Tale enunciato è noto come **teorema del momento angolare** in *forma integrale*.
E' utile considerare la sua *forma differenziale*, che otteniamo derivando ambo i membri in ==(55)==:
$$
\frac{d\vec{L}}{dt} = \vec{M} \tag{56}
$$
Tale formulazione è chiaramente equivalente: anche da questa deduciamo che se il momento risultante è nullo, il momento angolare rimane costante.


