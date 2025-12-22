# INDICE SEZIONE
- [ ] [[#PREMESSE INTRODUTTIVE]]
- [ ] [[#TEOREMA DELLA DIVERGENZA]]
- [ ] [[#TEOREMA DI GREEN]]
- [ ] [[#TEOREMA DI STOKES (APPROFONDIMENTO)]]
# PREMESSE INTRODUTTIVE
Diamo alcune definizioni che saranno necessarie nel resto della sezione.
## FLUSSO DI UN CAMPO IN R2
In [[10 - SUPERFICI#FLUSSO DI UN CAMPO ATTRAVERSO UNA SUPERFICIE]] abbiamo definito il flusso di un campo vettoriale attraverso una superficie.
Se ci troviamo in $\mathbb{R}^2$ possiamo definire in modo simile il *flusso attraverso una curva*.
Per farlo, diamo prima la definizione di *vettore normale* ad una *curva*.
>[!def] NORMALE AD UNA CURVA (in $\mathbb{R}^2$)
>Sia $r:[a,b]\to \mathbb{R}^2$ una curva regolare, $r=(r_{1},r_{2})$.
>Definiamo il *vettore normale* (unitario) alla curva in $p=r(t)$:
>$$ \mathbf{N}_{r}(p) := \frac{( r_{2}'(t), -r_{1}'(t) )}{||r'(t)||} $$ 

Si è scelto il vettore normale in modo tale che si passi da $\mathbf{N}_{r}(p)$ a $\mathbf{T}_{r}(p)$ (vettore tangente) con una rotazione di $\frac{\pi}{2}$.

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}

\begin{document}

\begin{tikzpicture}[scale=1.4]
\begin{axis}[width=8cm,height=8cm,
			 xmin = -1.7, xmax=1.7,
			 ymin = -1.7, ymax=1.7]

\addplot[domain=0:360,samples=73]
      ({cos(\x)},{sin(\x)});

\draw[->, very thick, black, arrows={-latex}] (0.707, 0.707) -- (0,1.414)
	node[left, below=8pt, black] {$\mathbf{T}_r$};
\draw[->, ultra thick, red, arrows={-latex}] (0.707, 0.707) -- (1.414,1.414)
	node[right, below=8pt, black] {$\mathbf{N}_r$};

\end{axis}
\end{tikzpicture}

\end{document}
```

Grazie alla definizione appena data, possiamo definire il *flusso* di un campo *attraverso un cammino (curva)*.
>[!def] FLUSSO DI UN CAMPO ATTRAVERSO UNA CURVA
>Siano $r:[a,b]\to \mathbb{R}^2$ una *curva* ed $\mathbf{F}:r([a,b])\to \mathbb{R}^2$ un *campo continuo*.
>Il **flusso** di $\mathbf{F}$ attraverso $r$ è:
>$$ \Phi(\mathbf{F},r) := \int_{r}\mathbf{F}\cdot \mathbf{N}_{r}ds $$

Operativamente, si calcola nel seguente modo:
$$
\Phi(\mathbf{F},r) = \int_{a}^b \bigg( F_{1}(r(t))r_{2}'(t) - F_{2}(r(t))r_{1}'(t) \bigg) dt
$$
>[!important] OSSERVAZIONE
>E' una definizione del tutto analoga a quella data per il flusso di un campo attraverso una superficie, riadattata adesso ad una curva.
## DOMINI STOKIANI
Per comprendere i teoremi che esporremo in questa sezione è necessario dare una definizione di *dominio Stokiano*.
Diamola intanto per il caso di $\mathbb{R}^2$.
>[!def] DOMINIO STOKIANO (in $\mathbb{R}^2$)
>Un *aperto* $D\subset \mathbb{R}^2$ *limitato* si dice **Stokiano** se:
>1. Attorno ad ogni punto del bordo di $D$ vi è una separazione tra l'interno e l'esterno di $D$.
>2. La frontiera $\partial D$ di $D$ è unione di un *numero finito di sostegni di curve chiuse, regolari e semplici* $r_{1},\dots, r_{m}$ tali che il vettore normale ad ognuna di esse punti verso l'*esterno* di $D$.
>   Si parla in tal caso di **bordo positivamente orientato** di $D$, indicato con $\partial^+D$.

![[Dominio_stokiano.png|center|500]]

>[!important] OSSERVAZIONE
>In pratica , vogliamo che la curva più *ampia* (quella "esterna") sia percorsa in verso *antiorario*, mentre quelle più piccole (quelle "*interne*") siano percorse in verso *orario*.

Diamo a questo punto la definizione per il caso di $\mathbb{R}^3$.
>[!def] APERTO SEMPLICE-STOKIANO (in $\mathbb{R}^3$)
>Diciamo che un *aperto limitato* $\Omega$ di $\mathbb{R}^3$ è **semplice-Stokiano** se, salvo *rinominare gli assi*:
>1. $\Omega$ è *semplice* rispetto a $xy$: vi sono $D\subset \mathbb{R}^2$, $\alpha,\beta$ funzioni di classe $C^1$ tali che $\Omega=\{ (x,y,z): (x,y)\in D, \alpha(x,y)<z<\beta(x,y) \}$.
>2. $D$ è *aperto Stokiano* di $\mathbb{R}^2$ con *bordo positivamente orientato*, costituito da **un solo** *circuito regolare semplice*.
### CIRCUITAZIONI E FLUSSI
Il **lavoro** (*circuitazione*) di un campo lungo la *frontiera di un dominio Stokiano* sarà pertanto definito come segue.
>[!def] CIRCUITAZIONE SU UN BORDO POSITIVAMENTE ORIENTATO
>Siano $D$ aperto Stokiano, $\mathbf{G}=(G_{1},G_{2}):\partial D\to \mathbb{R}^2$ campo continuo.
>La **circuitazione** di $\mathbf{G}$ sul bordo positivamente orientato di $D$ è:
>$$ \oint_{\partial^+D}\mathbf{G}ds :=  \int_{r_{1}}\mathbf{G}ds+\dots+\int_{r_{m}}\mathbf{G}ds $$

E possiamo definire anche il **flusso uscente** da un *dominio Stokiano*.
>[!def] FLUSSO ATTRAVERSO UN DOMINIO STOKIANO
>Sia $\mathbf{F}:D\to \mathbb{R}^2$ un *campo vettoriale* su un *dominio Stokiano* $D$.
>Il **flusso** di $\mathbf{F}=(F_{1},F_{2})$ **uscente** da $D$ è dato da:
>$$ \Phi(\mathbf{F},\partial D) := \int_{\partial^+D}\mathbf{F}\cdot \mathbf{N}ds $$
>Ricordando sempre di fare attenzione all'*orientamento delle curve*.

Si tratta della somma dei flussi di $\mathbf{F}$ attraverso le varie *porzioni del bordo* di $D$: in $\mathbb{R}^2$ è la somma dei flussi attraverso delle curve, in $\mathbb{R}^3$ è la somma dei flussi attraverso le *superfici della frontiera*.
# TEOREMA DELLA DIVERGENZA
Vediamo l'enunciato generale del teorema.
>[!theorem] TEOREMA DELLA DIVERGENZA (Enunciato generale)
>Sia $V\subset \mathbb{R}^n$ *compatto* delimitato da una superficie liscia $\partial V$.
>Sia $\mathbf{F}$ un *campo vettoriale* di classe $C^1$ definito in un intorno di $V$.
>Si ha:
>$$ \int_{V}\nabla \cdot \mathbf{F} dV = \oint_{\partial V}\mathbf{F}\cdot d\mathbf{S} $$
>Dove $d\mathbf{S}=\mathbf{n}dS$ è l'elemento di superficie.

Il teorema lega cioè il *flusso del campo* attraverso la *superficie chiusa* $\partial V$ alla *divergenza* di $\mathbf{F}$ nel *volume racchiuso dalla superficie* stessa.
>[!important] NOTA
>In fisica abbiamo visto che $\nabla \cdot \mathbf{E}=\frac{\rho}{\varepsilon_{0}}$, che è proprio una conseguenza di questo teorema.

Più che al caso generale, tuttavia, siamo interessati ai casi di $\mathbb{R}^2$ ed $\mathbb{R}^3$: vediamoli allora da un punto di vista operativo.
>[!theorem] TH. DIVERGENZA IN $\mathbb{R}^2$
>Siano $D$ aperto Stokiano, il cui bordo $\partial D$ ha vettore normale $\mathbf{N}$ ed $\mathbf{F}:\overline{D}\to \mathbb{R}^2$ campo vettoriale $C^1$, $\mathbf{F}=(F_{1},F_{2})$.
>Si ha:
>$$ \int_{D} (\partial_{x}F_{1}+\partial_{y}F_{2})dxdy = \oint_{\partial D}\mathbf{F}\cdot \mathbf{N} dr $$

>[!theorem] TH. DIVERGENZA IN $\mathbb{R}^3$
>Sia $\Omega \subseteq \mathbb{R}^3$ è un dominio Stokiano, $\mathbf{F}:\overline{\Omega}\to \mathbb{R}^3$ un campo di classe $C^1$.
>Si ha:
>$$ \iiint_{\Omega}\nabla \cdot \mathbf{F}dV = \Phi(\mathbf{F},\partial^+D) $$
## IN ALTRI SISTEMI DI COORDINATE
In [[07 - CAMPI VETTORIALI#DIVERGENZA]] abbiamo visto la definizione di *divergenza* in coordinate cartesiane.
Si trattava in realtà di un caso specifico della definizione generale, che è la seguente:
>[!def] DIVERGENZA
>Sia $\mathbf{F}$ campo vettoriale $C^1$ e sia $V$ un volume racchiuso dalla superficie chiusa $\partial V$.
>$$ \nabla \cdot \mathbf{F} = \lim_{ V \to 0 } \frac{\int_{\partial D}\mathbf{F}\cdot d\mathbf{S}}{\iiint_{V}dV} $$

>[!important] NOTA
>Cioè la *divergenza* è il rapporto tra il flusso attraverso una superficie chiusa $\partial V$ ed il *volume* da essa *racchiuso*.

Si dimostra che, in coordinate **cilindriche**, vale:
$$ \nabla \cdot \mathbf{F} = \frac{1}{\rho} \frac{\partial(\rho F_{\rho})}{\partial \rho} + \frac{1}{\rho} \frac{\partial F_{\theta}}{\partial\theta} + \frac{\partial F_{z}}{\partial z} $$
E in coordinate **sferiche**:
$$
\nabla \cdot \mathbf{F} = \frac{1}{\rho^2} \frac{\partial \rho^2F_{r}}{\partial \rho} + \frac{1}{\rho \sin\theta} \frac{\partial (F_{\theta}\sin\theta)}{\partial\theta} + \frac{1}{\rho \sin\theta} \frac{\partial F_{\varphi}}{\partial \varphi}
$$
## DOMINI CON BUCHI
Il teorema della divergenza si estende a domini semplici-Stokiani *privi di un numero finito* di *domini semplici-Stokiani*, intendendo che il flusso **uscente** è quello *uscente dal dominio più grande* **meno** la somma dei flussi *uscenti dai vari domini tolti*.

Un esempio di tale dominio è dato da:
$$
B[p,R_{2}) \setminus B[p,R_{1})
$$
Cioè una palla di raggio $R_{2}$ (semplice-Stokiano), priva di una palla più piccola di raggio $R_{1}<R_{2}$ (anch'essa semplice-Stokiano).
# TEOREMA DI GREEN
Abbiamo visto che i campi conservativi in $\mathbb{R}^2$ di classe $C^1$ sono *irrotazionali*.
Quando il campo non è conservativo alcune circuitazioni possono essere *non nulle*.
Il *teorema di Green* lega la circuitazione di un campo sul *bordo di un aperto Stokiano* all'integrale della *terza componente del rotore* del campo.
>[!theorem] TEOREMA DI GREEN
>Siano $D$ aperto Stokiano ed $\mathbf{F}=(F_{1},F_{2}):\overline{D}\to \mathbb{R}^2$ campo $C^1$.
>Si ha:
>$$ \oint_{\partial^+D}\mathbf{F}\cdot d\mathbf{s} = \int_{D} ( \partial_{x}F_{2}-\partial_{y}F_{1} ) dxdy $$
## LEGAME CON IL TEOREMA DELLA DIVERGENZA
Dato un campo $\mathbf{F}=(F_{1},F_{2})$, consideriamo la sua *rotazione di* $-\frac{\pi}{2}$:
$$
\mathbf{G}:= (F_{2},-F_{1})
$$
Notiamo allora che, lungo una curva con vettore tangente $\mathbf{T}$ e vettore normale $\mathbf{N}$, si ha:
$$
\mathbf{F}\cdot \mathbf{T} = \mathbf{G}\cdot \mathbf{N}
$$
Perchè con una rotazione di $-\frac{\pi}{2}$ si torna dal vettore *tangente* a quello *normale*.
Per cui si ha:
$$
\oint_{\partial^+D}\mathbf{F}\cdot \mathbf{T}ds = \oint_{\partial^+D}\mathbf{G}\cdot \mathbf{N}ds = \Phi(\mathbf{G},\partial^+D)
$$
E anche:
$$
\int_{D}(\partial_{x}F_{2}-\partial_{y}F_{1})dxdy = \int_{D} (\partial_{x}G_{1} + \partial_{y}G_{2})dxdy = \int_{D} \nabla \cdot \mathbf{G}dxdy
$$
Cioè a partire dal teorema di Green possiamo ottenere il teorema della divergenza applicando una rotazione di $-\frac{\pi}{2}$ al campo considerato.
>[!important] NOTA
>Ciò che succede ha a che fare con le definizioni di **circuitazione** e **flusso**:
>- La prima misura la *tendenza del campo* ad essere *allineato con una certa curva*. 
>- Il secondo ne misura la tendenza ad *attraversarla*.
>  
>Similmente, **rotore** e **divergenza** rappresentano:
>- Il primo la tendenza del campo a *ruotare*.
>- La seconda, la sua tendenza ad *allontanarsi* o *convergere* verso un punto.
>  
>Per cui ruotando il campo di $-\frac{\pi}{2}$ andiamo proprio ad "**invertire**" i ruoli della *circuitazione* e del *flusso*, e del *rotore* e della *divergenza*.
## CALCOLO DELL'AREA
>[!prop] TH. GREEN e AREE
>Sia $D\subset \mathbb{R}^2$ un *dominio Stokiano*.
>Si ha:
>$$ \text{Area}(D) = \int_{\partial^+D}xdy = \int_{\partial^+D}-ydx = \frac{1}{2}\int_{\partial^+D}xdy-ydx $$

Vala la pena dare una dimostrazione di questo fatto per comprenderlo meglio.
>[!check] DIM.
>In generale, l'area di $D$ è l'integrale di $1$ su $D$ stesso.
>Se $\mathbf{F}(x,y)=(F_{1},F_{2})(x,y)=(0,x)$ si ha:
>$$ \partial_{x}F_{2}-\partial_{y}F_{1} = 1 - 0 = 1 $$
>Applicando il teorema di Green con $\mathbf{F}=(0,x)$ si trova pertanto:
>$$ \oint_{\partial^+D}xdy = \underbrace{ \oint_{\partial^+D}\mathbf{F}\cdot d\mathbf{s} = \int_{D} (\partial_{x}F_{2}-\partial_{y}F_{1})dxdy }_{ \text{Th. Green} } = \int_{D}1dxdy $$
>Possiamo ripetere lo stesso ragionamento con il campo $\mathbf{F}=(-y,0)$ per trovare:
>$$ \int_{\partial^+D}-ydx = \text{Area}(D) $$
>Da queste due si ricava facilmente anche la terza relazione. $\square$
# TEOREMA DI STOKES (APPROFONDIMENTO)
Il *teorema della divergenza* è in realtà un *caso particolare* di un importantissimo teorema: il **teorema di Stokes**.
Anche il *teorema di Green* è un caso particolare del *teorema del rotore*, che è a sua volta una caso particolare del *teorema di Stokes*.

```tikz
\usepackage{tikz}
\usetikzlibrary{trees}
\begin{document}
\begin{tikzpicture}[level distance=1.5cm,
  level 1/.style={sibling distance=4cm},
  level 2/.style={sibling distance=2cm}]
  \node {Th. STOKES}
    child {node {Th. DIVERGENZA} }
    child {node {Th. ROTORE}
	  child {node {Th. GREEN}}
    };
\end{tikzpicture}
\end{document}
```

Approfondiamo la questione.
>[!theorem] TEOREMA DI STOKES
>Sia $\Omega$ una $n$-*varietà differenziabile*.
>Se $\omega$ è una $(n-1)$-*forma* a *supporto compatto* su $\Omega$, la cui frontiera è $\partial\Omega$ si ha:
>$$ \int_{\Omega}d\omega = \oint_{\partial\Omega}\omega $$

Per contestualizzare brevemente il teorema, ci basta sapere che una *varietà differenziabile* di dimensione $n$ (la nostra $\Omega$) è una generalizzazione del concetto di *curva* e *superficie differenziabile* in *dimensione arbitraria*, su cui è possibile definire una $k$-forma differenziale ($k<n$), cioè un'*estensione* della nozione di *funzione in più variabili*, che può essere integrata su un qualsiasi oggetto $\Gamma$ di dimensione $k$.
Infine, $d\omega$ è la *derivata esterna* di $\omega$. In generale, la derivata esterna di una *forma differenziale* di grado $n$ è una *forma differenziale* di grado $n+1$.

Nel nostro caso stiamo quindi considerando una *varietà* $\Omega$ di dimensione $n$, sul cui bordo (di dimensione $n-1$) integriamo la *forma* $\omega$.
## LEGAME CON IL TEOREMA DELLA DIVERGENZA
Consideriamo $\Omega:=V\subset \mathbb{R}^n$ *compatto*, delimitato da una superficie liscia $\partial V$.
La nostra forma differenziale $\omega$ è un campo $\mathbf{F}$ (può essere considerato una $(n-1)$-forma) definito in un intorno di $V$ e la sua derivata esterna è $\nabla \cdot \mathbf{F}$.
E si ha quindi:
$$
\int_{V}\nabla \cdot \mathbf{F}dV = \oint_{\partial V}\mathbf{F}\cdot d\mathbf{s}
$$
Notiamo infatti che ha la stessa struttura del teorema di Stokes.
## TEOREMA DEL ROTORE
Sia $r:[a,b]\to \mathbb{R}^2$ una *curva semplice chiusa*, il cui sostegno $\Gamma=r([a,b])$ è frontiera di un dominio $D$.
Sia $\sigma:D\to \mathbb{R}^3$ una *superficie parametrica* "**bordata**" da $\Gamma$ (cioè $\Gamma$ è frontiera del dominio di $\Sigma$) con sostegno $\Sigma=\sigma(D)$.
Sia $\mathbf{F}:\Sigma\to \mathbb{R}^3$ un campo di classe $C^1$
Si ha allora:
>[!theorem] TEOREMA DEL ROTORE
>$$ \oint_{\Gamma}\mathbf{F}\cdot d\mathbf{r} = \int_{\Sigma} (\nabla \times \mathbf{F}) \cdot d\mathbf{s} $$

Il teorema ci dice quindi che la **circuitazione** del campo lungo una curva $\Gamma$ è pari al **flusso del rotore** del campo stesso attraverso una *qualsiasi superficie* che abbia per *bordo* $\Gamma$.

In questo caso il campo è una $1$-forma, ed il rotore è la sua derivata esterna.

>[!important] TEOREMA DI GREEN
>A questo punto notiamo che il teorema di Green è un caso particolare del teorema del rotore:
>- $\mathbf{F}$ è un campo in $\mathbb{R}^2$, il cui rotore ha come unica componente non nulla quella lungo l'asse $z$, pari a $\partial_{x}F_{2}-\partial_{y}F_{1}$.
>- $\Sigma$ è semplicemente la superficie racchiusa da $\Gamma$ sul piano $xy$.

