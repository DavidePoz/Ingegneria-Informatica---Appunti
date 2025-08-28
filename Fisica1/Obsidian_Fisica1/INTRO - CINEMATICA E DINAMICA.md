# ==INTERAZIONI FONDAMENTALI==
In fisica, le **interazioni fondamentali** sono interazioni della natura che permettono di descrivere *tutti* i *fenomeni fisici* a *tutte le scale di distanza*, e **non** sono **riconducibili** ad *altre interazioni*. 
Ne sono state individuate quattro:
- L'interazione **gravitazionale** (responsabile dell'attrazione fra corpi con massa).
- L'interazione **elettromagnetica** (interazioni fra corpi carichi e campi elettromagnetici).
- L'interazione **debole** (responsabile dei decadimenti nucleari).
- L'interazione **forte** (tiene uniti i quark e le particelle nei nuclei atomici).

# PUNTO MATERIALE
E' un'astrazione matematica con le seguenti caratteristiche:
- **NON** ha **dimensione** (non ha un'estensione nello spazio).
- Ha una certa **massa**.
In un sistema di riferimento in 3 dimensioni, è descritto dalla sua massa e, istante per istante, dalla sua posizione:
$$
\vec{r}(t)=\biggl( x(t),y(t),z(t) \biggr)
$$
# ==CINEMATICA DEL PUNTO MATERIALE==

## VELOCITA' DEL PM
Consideriamo $z(t)$ al variare di $t$.
La *velocità* lungo l'asse $z$ ($\vec{u}_{z}$) ci dice quanto rapidamente cambia $z(t)$ tra due istanti vicini nel tempo:
$$
v_{z}(t_{0}) \approx \frac{z(t_{1})-z(t_{0})}{t_{1}-t_{0}}
$$
Per cui per $t_{1}-t_{0}$ sufficientemente piccolo abbiamo:
$$
v_{z}(t_{0})=\frac{dz(t)}{dt}\bigg|_{t_{0}}
$$
## ACCELERAZIONE DEL PM
Analogamente, l'*accelerazione* lungo l'asse $z$ ci dice quanto rapidamente varia la velocità del punto materiale fra due istanti nel tempo.
Vale:
$$
a_{z}(t_{0})=\frac{dv_{z}(t)}{dt}\bigg|_{t_{0}}
$$
## ESTENSIONE AI VETTORI
Tali relazioni valgono anche per i vettori $\vec{r}(t)$ e $\vec{v}(t)$.
Vale:
$$
\vec{v}(t)=\frac{d \vec{r}(t)}{dt}=\biggl( \frac{dx(t)}{dt}, \frac{dy(t)}{dt}, \frac{dz(t)}{dt} \biggr)=\biggl( v_{x}(t),v_{y}(t),v_{z}(t) \biggr)
$$
## LEGGI DEL MOTO
In meccanica, le **leggi del moto** si scrivono *interamente* in termini di $\vec{r}, \vec{v}$ e $\vec{a}$.
Focalizzandoci sull'asse $z$, se conosciamo $z(t_{0})$ e $v_{z}(t_{0})$, sappiamo che vale:
$$
z(t_{1})\approx z(t_{0})+(t_{1}-t_{0})v_{z}(t_{0})
$$
Possiamo iterare la procedura, trovando:
$$
z(t_{n})\approx z(t_{0})+(t_{1}-t_{0})v_{z}(t_{0})+\dots+(t_{n}-t_{(n-1)})v_{z}(t_{n-1})
$$
Per $n\to \infty$ otteniamo una somma di Riemann, da cui passiamo all'integrale:
$$
z(t)=z(t_{0})+\int_{t_{0}}^{t}v_{z}(\tau)d\tau
$$
Come prima, tale relazione vale anche per i vettori. Per cui si trova:
$$
\vec{r}(t)=\vec{r}(t_{0})+\int_{t_{0}}^t \vec{v}(\tau)d\tau
$$
E similmente, per la velocità:
$$
\vec{v}(t)=\vec{v}(t_{0})+\int_{t_{0}}^t \vec{a}(\tau)d\tau
$$
### MOTO UNIFORMEMENTE ACCELERATO
Sia $\vec{a}(t)=\vec{a}$ costante. Ad esempio $\vec{a}(t)=(0,0,-g)$.
Vogliamo descrivere il moto di un p.m. soggetto a tale accelerazione, velocità iniziale $\vec{v}_{0}$ e posizione iniziale $\vec{r}_{0}$.
Dalle equazioni di prima troviamo che vale:
$$
\begin{matrix}
\vec{v}(t)=\vec{v}_{0}+\vec{a}(t-t_{0}) \\ \\
\vec{r}(t)=\vec{r}_{0}+\vec{v}_{0}(t-t_{0})+\frac{1}{2}\vec{a}(t-t_{0})^2
\end{matrix}
$$
### MOTO CIRCOLARE UNIFORME
Consideriamo un p.m. che si muove su un piano lungo una traiettoria circolare con velocità angolare costante $\omega$.
Abbiamo.
$$
\vec{r}(t)=R_{0}\cos(\omega (t-t_{0}))\vec{u}_{x}+R_{0}\sin(\omega(t-t_{0}))\vec{u}_{y}
$$
Si osservi che vale $\lvert \lvert \vec{r}(t) \rvert \rvert=R_{0}$ $\forall t$.
Si scrive anche:
$$
\vec{r}(t)=R_{0}\cos(\omega t+\phi )\vec{u}_{x}+R_{0}\sin(\omega t+\phi))\vec{u}_{y}
$$
Dove $\phi=-\omega t_{0}$ è lo *sfasamento iniziale*.
Si noti che, per $N\in\mathbb{Z}$, vale:
$$
\vec{r}(t)=\vec{r}\left( t+\frac{2N\pi}{\omega} \right)
$$
Il moto si ripete ogni $T=\frac{2\pi}{\omega}$. $T$ è il **periodo** del moto.
Derivando per trovare la legge della velocità:
$$
\vec{v}(t)=\frac{d\vec{r}(t)}{dt}=-R_{0}\omega \sin(\omega t +\phi)\vec{u}_{x}+R_{0}\omega \cos(\omega t +\phi)\vec{u}_{y}
$$
E si osserva che vale $\lvert \lvert \vec{v}(t) \rvert \rvert=R_{0}\omega$.
**NOTA**: la velocità è tangente alla traiettoria.
Derivando nuovamente per trovare la legge dell'accelerazione:
$$
\vec{a}(t)=\frac{d \vec{v}(t)}{dt}=-R_{0}\omega^2\cos(\omega t + \phi)\vec{u}_{x}-R_{0}\omega^2\sin(\omega t +\phi)\vec{u}_{y}
$$
Notiamo che vale $\vec{a}(t)=-\omega^2\vec{r}(t)$.
E si osserva che vale anche $\lvert \lvert \vec{a}(t) \rvert \rvert=R_{0}\omega^2$.
### MOTO ARMONICO
E' la proiezione del M.C.U. lungo un asse, per esempio $\vec{u}_{x}$.
Allora, dalle equazioni trovate sopra, sappiamo:
$$
\begin{matrix}
\vec{r}(t)=R_{0}\cos(\omega t +\phi)\vec{u}_{x} \\ \\
\vec{v}(t)=-R_{0}\omega \sin(\omega t + \phi)\vec{u}_{x} \\ \\
\vec{a}(t)=-R_{0}\omega^2\cos(\omega t +\phi)\vec{u}_{x}=-\omega^2\vec{r}(t)\vec{u}_{x}
\end{matrix}
$$
## VELOCITA' E ACCELERAZIONE DI UN VERSORE ROTANTE
Consideriamo un **versore** (vettore di norma 1) rotante $\vec{u}(t)$.
$$
\frac{d \vec{u}(t)}{dt} \approx \frac{\vec{u}(t+\Delta t)-\vec{u}(t)}{\Delta t}\vec{u_{\perp}}
$$
Dove il numeratore è la "distanza percorsa". Essendo il versore rotante, vale che tale distanza è $\approx\Delta\theta||\vec{u}(t)|| \approx\Delta\theta$.
Tenendo conto di ciò e passando al limite, troviamo:
$$
\frac{d \vec{u}(t)}{dt}=\frac{d\theta(t)}{dt}\vec{u}_{\perp}
$$
Deriviamo nuovamente per trovare l'accelerazione:
$$
\frac{d^2 \vec{u}(t)}{dt^2}=\frac{d}{dt}\left( \frac{d\theta(t)}{dt}\vec{u}_{\perp} \right)
$$
$$
=\frac{d^2\theta(t)}{dt^2}\vec{u}_{\perp}+\frac{d\theta(t)}{dt} \underbrace{ \frac{d \vec{u}_{\perp}}{dt} }_{ =\frac{d\theta}{dt}\vec{u}_{\perp \perp} }
$$
E troviamo:
$$
\frac{d^2 \vec{u}(t)}{dt^2}= \frac{d^2\theta(t)}{dt^2}\vec{u}_{\perp}(t)-\left( \frac{d\theta(t)}{dt} \right)^2\vec{u}(t)
$$
# ==DINAMICA DEL PUNTO MATERIALE==
La dinamica vuole spiegare le **cause** del moto a partire dal *principio di inerzia* e sulla base del concetto di forza.
## PRINCIPI DELLA DINAMICA

> [!info] **PRIMA LEGGE DELLA DINAMICA (PRINCIPIO DI INERZIA):**
> Se su un corpo non agisce alcuna forza, allora questo si muove con velocità costante, eventualmente nulla.

Per formalizzare tale principio, si introducono la definizione di **forza** e la seconda legge della dinamica.

> [!info] **SECONDA LEGGE DELLA DINAMICA:**
> La forza agente su un corpo è pari alla variazione, istante per istante, della sua quantità di moto.
> 

Per cui si ha:
$$
\frac{d \vec{p}(t)}{dt}=\vec{F}(\vec{r},\vec{v},t)
$$
Si osservi che se la forza è nulla, allora anche la variazione di $\vec{p}$ è nulla, cioè il corpo si muove con $\vec{v}$ costante (primo principio). 
Assumendo che la massa sia costante troviamo:
$$
\vec{F}=\frac{d \vec{p}}{dt}=\frac{d}{dt}(m\vec{v})=m \vec{a}
$$
**NOTA**: $m$ è detta *massa inerziale*.
#### **INTERPRETAZIONI**
1. Il secondo principio della dinamica ci fornisce una **definizione** di forza.
2. Se conosciamo la *massa* del corpo e la/le *forza/e agenti* su di esso possiamo ricavare l'**accelerazione** a cui è soggetto, e da questa la sua legge oraria.

Introduciamo infine il terzo principio della dinamica.
> [!info] **TERZA LEGGE DELLA DINAMICA (PRINCIPIO DI AZIONE E REAZIONE):**
> Se un corpo A esercita una forza su un corpo B, allora B esercita una forza di modulo uguale ma verso opposto su A.

## VALIDITA' DELLE LEGGI DELLA DINAMICA
Immaginiamo di scrivere la legge oraria per il moto di un corpo rispetto a due diversi sistemi di riferimento.
Le leggi ottenute avranno la stessa forma? La forza "osservata" nei due sistemi sarà la stessa?
La risposta a entrambe le domande è **si, se i sistemi di riferimento sono inerziali**.

>[!info] **SISTEMI INERZIALI**
>Un sistema di riferimento si dice **inerziale** se si muove con velocità costante.

Se sono inerziali, significa che si muovono a velocità costante anche uno rispetto all'altro.
Consideriamo quindi due sistemi, $O$ e $O'$, e un corpo in posizione $\vec{r}(t)=\vec{OP}(t)$ rispetto a $O$ e $\vec{r'}(t)=\vec{O'P}$ rispetto ad $O'$. 
Consideriamo inoltre il vettore $\vec{OO'}$, distanza fra le origini dei due sistemi di riferimento. 
Si ha $\vec{OO'}=\vec{d}+\vec{v}_{OO'}t$ dove $\vec{d}$ è la distanza iniziale fra i due e $\vec{v}_{OO'}$ è la velocità relativa fra i due sistemi.
Segue anche che:
$$
\vec{OP}(t)=\vec{OO'}(t)+\vec{O'P}(t)
$$
Cioè:
$$
\vec{r}(t)=\vec{d}+\vec{v}_{OO'}t+\vec{r'}(t)
$$
Derivando ambo i membri troviamo:
$$
\vec{v}(t)=\vec{v}_{OO'}+\vec{v'}(t)
$$
Derivando nuovamente:
$$
\vec{a}(t)=\vec{a'}(t)
$$
Allora le accelerazioni misurate nei due sistemi sono uguali (l'accelerazione è invariante).
Quindi le leggi della dinamica valgono e sono compatibili con entrambi i sistemi e le leggi orarie avranno la stessa forma.
## ESEMPI DI FORZE
### FORZA ELASTICA
$$
\vec{F}_{el}=-k\vec{r}
$$
Dove $k$ è la costante elastica.
Consideriamo un corpo che può muoversi lungo una sola direzione, per esempio $\vec{u}_{x}$:
$$
\vec{F}_{el,x}=-kx \vec{u}_{x}
$$
Abbiamo:
- $F_{el}<0$ se $x>0$
- $F_{el} > 0$ se $x<0$

La forza elastica riporta il corpo verso una posizione di equilibrio, agendo come una "forza di richiamo".
Immaginiamo di avere una generica forza di richiamo $\vec{F}_{rich}(x)$ tale che $\vec{F}_{rich}(0)=0$.
Allora possiamo dire:
$$
F_{rich}(x) = 0+\underbrace{ \frac{d}{dx}F_{rich} }_{ =-k }\bigg|_{0}x+\frac{1}{2} \frac{d^2}{dx^2}F_{rich}\bigg|_{0}x^2+\dots
$$
Per $x\to 0$:
$$
F_{rich}(x)=-kx+o(x^2)
$$
Quindi qualsiasi forza di richiamo è approssimabile dalla forza elastica per piccole perturbazioni rispetto all'equilibrio.

**NOTA:** *non* è importante che la posizione di riposo sia $0$.
In generale, se $x_{0}$ è la posizione di riposo, abbiamo:
$$
F_{el,x}=-k(x-x_{0})
$$
### FORZA DI GRAVITAZIONE UNIVERSALE
Tra due corpi $A$ e $B$ con masse $m_{A}$ e $m_{B}$ vi è una forza:
$$
\vec{F}_{AB}=-G \frac{m_{A}m_{B}}{||\vec{r}_{A}-\vec{r}_{B}||^2}\vec{u}_{AB}
$$
E vale $\vec{F}_{AB}=-\vec{F}_{BA}$ per il terzo principio della dinamica.
Per il secondo principio, abbiamo:
$$
m_{A}\vec{a}_{A}=\vec{F}_{AB}
$$
Allora troviamo:
$$
\vec{a}_{A}=-G \frac{m_{B}}{||\vec{r}_{A}-\vec{r}_{B}||^2}\vec{u}_{AB}
$$
La massa inerziale era definita dalla legge di Newton, indipendentemente dal tipo di forza considerata. E' un fatto inaspettato (e risultato sperimentale) che la stessa massa abbia anche il ruolo di "massa *gravitazionale*".
### FORZA PESO SULLA SUPERFICIE TERRESTRE
Una persona ferma sulla superficie terrestre è in buona approssimazione soggetta alla sola forza gravitazionale terrestre.
Allora l'accelerazione sulla persona data da tale forza è:
$$
\vec{a}=-G \frac{m_{T}}{r_{T}^2}\vec{u}_{z}
$$
Dove $m_{T}$ ed $r_{T}$ sono, rispettivamente, la massa della terra e il raggio terrestre, e $\vec{u}_{z}$ il versore della direzione centro della terra-persona.
Si trova:
$$
||\vec{a}|| \approx 9,81 \frac{m}{s^2} \overset{ def }{ = } g
$$
E introduciamo la *forza-peso* sulla superficie terrestre, data da:
$$
\vec{F}_{P}=-mg \vec{u}_{z}
$$
### FORZA D'ATTRITO (RADENTE)
Si definisce per corpi a contatto con una **superficie**:
$$
\vec{F}_{a}=\begin{cases}
-\vec{F}_{\parallel} \text{ se } ||\vec{F}_{\parallel}|| \le \mu_{s}||\vec{F_{\perp}}|| \\
 \\
-\mu_{d}||\vec{F}_{\perp}||\vec{u}_{v} \text{ altrimenti}
\end{cases}
$$
Dove $\mu_{s}$ è il coefficiente di attrito statico e $\mu_{d}$ il coefficiente di attrito dinamico.
Se la forza parallela alla superficie non è sufficiente per contrastare l'attrito statico, il corpo rimane fermo. 
Per un corpo in movimento, invece, la forza d'attrito dinamico è proporzionale alla risultante delle forze entranti nel piano.
### FORZA D'ATTRITO (VISCOSO)
Un corpo immerso in un **fluido** (come l'aria) è soggetto, in assenza di vortici, ad una forza d'attrito (detto *attrito viscoso*):
$$
\vec{F}_{visc}=-b \vec{v}
$$
Dove $b$ è il coefficiente di attrito viscoso.
Lungo $\vec{u}_{v}$ si ha:
$$
ma=-bv
$$
Da cui:
$$
a=-\frac{b}{m}v
$$
Otteniamo allora l'equazione differenziale:
$$
\frac{dv}{dt}=-\frac{b}{m}v
$$
Una soluzione a tale equazione è
$$
v(t)=v_{0}e^{\lambda t}
$$
Sostituendo nell'equazione troviamo:
$$
v_{0}\lambda e^{\lambda t}=-\frac{b}{m}v_{0}e^{\lambda t}
$$
E questa relazione deve valere per qualsiasi $t$, quindi deve essere:
$$
\lambda=-\frac{b}{m}
$$
Mentre la costante $v_{0}$ è fissata dalla condizione iniziale ($v(0)=v_{0}$).
Per trovare la legge oraria associata, supponendo un moto in una direzione:
$$
x(t)=x_{0}+\int_{0}^t v(\tau)d\tau=x_{0}+\int_{0}^t v_{0}e^{-(b/m) \tau}d\tau
$$
E si trova:
$$
x(t)=x_{0}+\frac{v_{0}m}{b}\left( 1-e^{-(b/m)t} \right)
$$
# ==EQUAZIONI DELLA DINAMICA==
Abbiamo visto un esempio di equazione della dinamica per un corpo soggetto ad attrito viscoso.
In generale, le equazioni sono del tipo
$$
ma=F_{TOT}
$$
E quindi:
$$
a=\frac{1}{m}F_{TOT}
$$
Dove, come abbiamo detto, $F_{TOT}$ dipende da $\vec{r},\vec{v}$ e $t$.
Troveremo quindi, nel caso più generale, un'equazione differenziale *lineare, a coefficienti costanti, affine* del tipo:
$$
\frac{d^2x(t)}{dt^2}+a\frac{dx(t)}{dt}+bx(t)=F(t)
$$
