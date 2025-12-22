# INDICE SEZIONE
- [ ] [[#LIMITI]]
- [ ] [[#DERIVATE]]
- [ ] [[#DERIVATE SECONDE]]
# LIMITI 
## PREMESSE
Prima di definire i limiti per funzioni di più variabili, diamo una definizione di intorno in $\mathbb{R}^n$:
>[!def] PUNTO DI ACCUMULAZIONE
>Dato $D\subset \mathbb{R}^n$, $p \in \mathbb{R}^n$ è di *accumulazione* per $D$ se:
>$$ \forall \mathcal{U}_{p} \text{ intorno di }p \text{ si ha } \mathcal{U}_{p}\cap D\setminus\{p\}\ne 0$$
>

Un punto di $D$ che non è di accumulazione per $D$ si dice **isolato**.
## DEFINIZIONE
A questo punto possiamo dare la definizione di limite per una funzione in più variabili.

Anche in $\mathbb{R}^n$, come in $\mathbb{R}$, distinguiamo il caso *reale* ed il caso *infinito*.
Data una funzione $f:D\to \mathbb{R}$ con $\emptyset\ne D \subseteq \mathbb{R}^n$ e $p$ di accumulazione per $D$, abbiamo:

>[!def] LIMITE - CASO REALE
>$$ \lim_{ x \to p }f(x) = l \in \mathbb{R}  $$
>se vale
>$$ \forall\varepsilon>0 \exists\delta>0 : x \in D, 0<||x-p||\leq\delta \implies |f(x)-l| \leq\varepsilon $$

Oppure, equivalentemente:
$$
\forall \varepsilon >0 \exists \mathcal{U}_{p} \text{ intorno di p } : x \in\mathcal{U}_{p}\cap D\setminus \{p\} \implies |f(x)-l|\leq\varepsilon
$$
>[!def] LIMITE - CASO INFINITO
>Scriviamo
>$$ \lim_{ x \to p }f(x)=+\infty  $$
>se vale
>$$ \forall M>0 \exists\mathcal{U}_{p} \text{ intorno di p } : x \in\mathcal{U}_{p}\cap D\setminus \{p\} \implies f(x)\geq M $$
>Scriviamo
>$$ \lim_{ x \to p }f(x) = -\infty  $$
>se vale
>$$ \forall M<0 \exists \mathcal{U}_{p} \text{ intorno di p } : x \in\mathcal{U}_{p}\cap D\setminus \{p\} \implies f(x)\leq M $$

## TEOREMI E ALTRI FATTI
Vale ancora il teorema di unicità del limite.
>[!theorem] UNICITA' DEL LIMITE E RESTRIZIONI
>Sia $f:D\subseteq \mathbb{R}^n\to \mathbb{R}^m$ e $p$ di accumulazione per $D$.
>- Se $\exists \lim_{ x \to p }f(x)=l_{1}$ e se  $\exists \lim_{ x \to p }f(x)=l_{2}$ deve essere $l_{1}=l_{2}$.
>- Se $E\subset D$ e $p$ di accumulazione per $E$ allora  $\exists \lim_{ x \to p }f(x)=l_{3}$ ed $l_{1}=l_{3}$.

>[!important] RESTRIZIONI
>Il secondo punto è particolarmente utile per *dimostrare la non esistenza di un limite*:
>basta trovare due restrizioni che portano a due limiti *differenti*.
>Spesso si considerano le restrizioni sugli assi $x$ e $y$ o sulle rette del piano per un punto $p_{a}=(x_{a},y_{a})$ date da $y-y_{a}=m(x-x_{a})$ con $x \in \mathbb{R}$.
>Altre volte può invece risultare utile considerare le semirette per $p_{a}$ espresse in coordinate polari:
>- $x=x_{a}+\rho \cos t$
>- $y=y_{a}+\rho \sin t$
>
>Tenendo fisso $t$ e facendo variare $\rho$ ci si sposta sulla semiretta per $p_{a}$ di direzione $(\cos t,\sin t)$.

E anche la permanenza del segno.
>[!theorem] PERMANENZA DEL SEGNO
>Sia $f:D\subseteq \mathbb{R}^n\to \mathbb{R}^m$ e $p$ di accumulazione per $D$.
>- Se $\lim_{ x \to p }f(x)>0$ allora $f(x)>0$ in un intorno di $p$.
>- Se $\lim_{ x \to p }f(x)<0$ allora $f(x)<0$ in un intorno di $p$.

Vale anche il teorema dei carabinieri.
>[!theorem] T. DEI CARABINIERI
>Siano $f,g,h$ funzioni definite in un intorno bucato di $p$ e
>$$ f(x)\leq g(x)\leq h(x) $$
>nell'intorno.
>Se $f(x)\underset{ p }{ \to } l$ e $h(x)\underset{ p }{ \to } l$, allora
>$$ g(x)\underset{ p }{ \to } l $$
## CONTINUITA'
Definiti i limiti, possiamo ora dare la definizione di funzione continua:
>[!def] FUNZIONE CONTINUA
>Siano $f:D\subset \mathbb{R}^n\to \mathbb{R}$ e $p$ un punto di $D$.
>Si dice che $f$ è **continua** in $p$ se:
>$$ \forall\varepsilon >0 \exists\mathcal{U}_{p} \text{ intorno di p : } x \in\mathcal{U}_{p}\cap D \implies|f(x)-f(p)|\leq\varepsilon $$
>La funzione $f$ si dice continua su $D$ se è continua in ogni punto di $D$.

Ne consegue che:
- $f$ è continua in ogni punto isolato del suo dominio.
- Se $p$ è di accumulazione, $f$ è continua in $p$ se $\lim_{ x \to p }=f(p)$.
# DERIVATE 
## DERIVATA DIREZIONALE
>[!def] DERIVATA DIREZIONALE
>Sia $f:D\subseteq \mathbb{R}^n\to \mathbb{R}$, $p$ interno a $D$, $\underline{u}\in \mathbb{R}^n$ e $\underline{u}\ne \vec{0}$.
>Chiamiamo *derivata direzionale* di $f$ nel punto $p$ in *direzione* $u$ il seguente limite, se esiste:
>$$ \partial_{\underline{u}}f(p) = D_{\underline{u}}f(p) := \lim_{ t \to 0 } \frac{f(p+t\underline{u})-f(p)}{t} = \frac{d}{dt}g(t)\big|_{t=0}  $$
>Dove $g(t)=f(p+t\underline{u})$ è la restrizione di $f$ lungo la retta $p+t\underline{u}$.

La *derivata direzionale* di $f$ in $p$ rispetto ad un versore $\underline{u}$ misura quindi il *tasso di crescita* della funzione nel punto $p$ lungo la direzione data da $\underline{u}$.
Si noti che il tasso di crescita è calcolato usando l'unità di misura $\underline{u}$ sul dominio ed il versore unitario sulle ordinate: per questo motivo conviene *limitarsi* all'uso di *versori*.
>[!important] NOTA
>$\partial_{\underline{u}}f(p)$ è un **numero**, *non un vettore*.

Chiaramente la derivata direzionale dipende dal vettore $\underline{u}$ scelto, tuttavia se abbiamo due vettori $\underline{u}$ e $\underline{v}$ tali che $\underline{v}=\lambda \underline{u}$ vale la seguente proposizione:
>[!prop] PROPOSIZIONE
>Se $\exists \partial_{\underline{u}}f(p)$ e $\underline{v}=\lambda \underline{u}$, allora:
>$$ \forall\lambda \in \mathbb{R} \text{ } \partial_{\underline{v}}f(p) = \lambda \partial_{\underline{u}}f(p) $$
## DERIVATE PARZIALI
Le derivate direzionali di una funzione rispetto ai versori della base $e_{1},\dots e_{n}$ si chiamano **derivate parziali**.
>[!def] DERIVATE PARZIALI
>Sia $f:D\subseteq \mathbb{R}^n\to \mathbb{R}$.
>La $i$-esima *derivata parziale* di $f$ in $p$ è, se essa esiste:
>$$ \partial_{e_{i}}f(p) $$

>[!important] NOTA
>Spesso in $\mathbb{R}^3$ le derivate parziali si indicano con i simboli
>$$ \partial_{x}f, \partial_{y}f, \partial_{z}f $$
>oppure
>$$ f_{x},f_{y},f_{z} $$
### FUNZIONI DI CLASSE C1
>[!def] FUNZIONI $C^1$
>Si dice che $f$ è di classe $C^1$ in un *aperto* se $f$ è continua e le derivate parziali *esistono e sono continue* in tale aperto.
## PROPRIETA' DELLE DERIVATE DIREZIONALI
La derivata di una funzione in un punto, rispetto ad un vettore, è la derivata di una funzione di *una* variabile, come visto sopra.
Per cui è facile comprendere che continuano a valere le regole di derivazione viste per funzioni di una variabile.
>[!prop] PROPRIETA'
>Siano $f,g:D\subseteq\to \mathbb{R}^n$ con derivate direzionali rispetto ad $\underline{u}$ in $p$. Allora:
>1. $\partial_{\underline{u}}(f+g)(p) = \partial_{\underline{u}}f(p) + \partial_{\underline{u}}g(p)$.
>2. $\partial_{\underline{u}}(fg)(p) = \partial_{\underline{u}}f(p)g(p) + f(p)\partial_{\underline{u}}g(p)$.
>3. $\partial_{\underline{u}}(cf)(p) = c\partial_{\underline{u}}f(p)$ se $c\in \mathbb{R}$.
>4. Se $\varphi$ è di variabile reale e derivabile in $p$: $\partial_{\underline{u}}(\varphi \circ f)(p) = \varphi'(f(p))\partial_{\underline{u}}f(p)$.
# DERIVATE SECONDE
>[!def] DERIVATE PARZIALI SECONDE
>Se $f:\mathcal{U}_{p}\to \mathbb{R}$ dove $\mathcal{U_{p}}$ è un intorno di $p \in\mathbb{R}^n$, $i=1,\dots,n$, $k=1,\dots,n$ e $\partial_{x_{i}}f:\mathcal{U}_{p}\to \mathbb{R}$ sono derivabili in $p$, chiamiamo
>$$ \partial_{x_{k}x_{i}}f(p) = \partial_{x_{k}}(\partial_{x_{i}}f)\big|_{p} $$
>**derivata seconda** nella *direzione* $x_{k}x_{i}$ di $f$ in $p$.

Come possiamo intuire dagli indici $i$ e $k$, possiamo derivare prima rispetto ad una direzione e poi rispetto ad un'altra.
>[!important] NOTA
>Se $i=k$ parliamo di derivata **pura** (es. $\partial_{xx}f(x,y)$ o $\partial_{yy}f(x,y)$).
>Se $i\ne k$ parliamo di derivata **mista** (es. $\partial_{xy}f(x,y)$ o $\partial_{yx}f(x,y)$).

>[!important] NOTAZIONE
>Si scrive anche, per le derivate pure:
>$$ \partial_{xx}f(x,y) = \frac{\partial^2f}{\partial x^2} $$
>E per quelle miste:
>$$ \partial_{xy}f(x,y) = \frac{\partial^2f}{\partial x\partial y} $$
## MATRICE HESSIANA
>[!def] MATRICE HESSIANA
>L'insieme delle $n^2$ derivate parziali del secondo ordine in un punto $p$ del dominio si riassume in una matrice, detta *matrice Hessiana* della funzione $f$:
>$$ \mathrm{Hess}f(p) = \mathrm{H}f(p) = \begin{pmatrix} \partial^2_{x_{1},x_{1}}f & \dots & \partial^2_{x_{1},x_{n}}f \\ \partial^2_{x_{2},x_{1}}f & \dots & \partial^2_{x_{2},x_{n}}f \\ \dots & \dots & \dots \\ \partial^2_{x_{n},x_{1}}f & \dots & \partial^2_{x_{n},x_{n}}f
 \end{pmatrix} $$
 
Esistono dei casi in cui $\mathrm{Hess}f(p)$ è simmetrica?
Per rispondere a tale domanda ci viene in aiuto il seguente teorema:
>[!theorem] T. DI SCHWARZ SULLE DERIVATE MISTE
>Siano $D\subset \mathbb{R}^n$ aperto e $f:D\to \mathbb{R}$ di classe $C^2$ (con derivate parziali doppie continue).
>Allora per ogni $i,j=1,\dots,n$ e $x \in D$ si ha:
>$$ \partial^2_{x_{i}x_{j}}f(x) = \partial^2_{x_{j}x_i}f(x) $$
## DERIVATE SECONDE E APPROSSIMAZIONE
Abbiamo visto che una funzione è *differenziabile* quando si può approssimare con la sua linearizzazione (il cui grafico è lo spazio tangente).
Le derivate seconde permettono di *stimare l'errore* che si commette in tale approssimazione.
>[!prop] STIMA DEL RESTO NELLA LINEARIZZAZIONE PER FUNZIONI $C^2$
>Sia $f(x_{1},\dots,x_{n})$ funzione di classe $C^2$ attorno a $p=(p_{1},\dots p_{n})\in \mathbb{R}^n$.
>Allora se $|\partial_{x_{i}x_{j}}f(x)|\leq M$ in quell'intorno si ha:
>$$ f(x)=f(p)+\nabla f(p)\cdot(x-p)+R(x) $$
>Dove $R(x)$ soddisfa:
>$$ |R(x)| \leq \frac{M}{2}\left( \sum_{i=1}^n |x_{i}-p_{i}| \right)^2 \leq \frac{Mn}{2}|x-p|^2 $$

E' possibile inoltre approssimare una funzione $C^2$ al secondo ordine:
>[!def] APPROSSIMAZIONE AL SECONDO ORDINE
>Sia $f$ di classe $C^2$ attorno a $p$.
>Allora la sua approssimazione al secondo ordine nel punto $p$ è:
>$$ Q(x) = f(p) + \nabla f(p)\cdot(x-p) + \frac{1}{2}(x-p)\mathrm{H}f(p)(x-p) $$
>E vale:
>$$ f(x)-Q(x) = o(|x-p|^2) $$
