# INDICE SEZIONE
- [ ] [[#INTEGRALE DOPPIO SECONDO RIEMANN]]
- [ ] [[#RIDUZIONE DELL'INTEGRALE]]
- [ ] [[#INTEGRALI GENERALIZZATI]]
- [ ] [[#MATRICE JACOBIANA E CAMBIO DI VARIABILI]]
# INTEGRALE DOPPIO SECONDO RIEMANN
## SU UN RETTANGOLO
Consideriamo una funzione come la seguente:
$$
f:[a,b]\times[c,d]\to[0,+\infty)
$$
Vogliamo calcolare il volume del **trapezoide** $\mathrm{Trap}(f)$ definito da:
$$
\mathrm{Trap}(f) := \{ (x,y,z) : x\in[a,b],y\in[c,d], 0\leq z\leq f(x,y) \}
$$
Possiamo approssimarlo con dei parallelepipedi che hanno come base dei rettangoli $A_{i}$ e altezza $f(c_{i})$.
Cioè abbiamo:
$$
\bigcup_{i}A_{i} = [a,b]\times[c,d] \text{ , } \mathrm{int}(A_{i}) \cap \mathrm{int}(A_{j}) = \emptyset \text{ }\forall i\ne j \text{ , }c_{i}\in A_{i}\text{ }\forall i
$$
Cioè il rettangolo $[a,b]\times[c,d]$ è formato dall'unione di rettangoli più piccoli $A_{i}$ che non si sovrappongono (se non sul bordo), e per ciascuno di essi scegliamo un punto interno $c_{i}$.
Allora abbiamo:
$$
\mathrm{Vol}(\mathrm{Trap}(f)) \approx \sum_{i}f(c_{i})\mathrm{Area}(A_{i})
$$
A partire da tale approssimazione possiamo dare una definizione di integrale secondo Riemann per funzioni in due variabili del tutto simile a quella vista in Analisi I.
>[!def] INTEGRALE DOPPIO (SECONDO RIEMANN)
>La funzione $f:[a,b]\times[c,d]\to \mathbb{R}$ si dice *Riemann-integrabile* se $f$ è **limitata** e le somme di cui sopra convergono ad un valore finito al tendere a $0$ dei diametri dei rettangoli $A_{i}$.
>In tal caso, il limite è indicato con:
>$$ \int_{[a,b]\times[c,d]}fdxdy $$

>[!prop] PROPOSIZIONE
>Ogni funzione **continua** su $[a,b]\times[c,d]$ è *integrabile secondo Riemann*.
## SU UN DOMINIO LIMITATO
>[!def] FUNZIONE INTEGRABILE SU UN INSIEME LIMITATO
>Siano $D\subset \mathbb{R}^2$ limitato, $f:D\to \mathbb{R}$ funzione ed $R$ un qualunque rettangolo contenente $D$.
>Si dice che $f$ è integrabile su $D$ se la funzione:
>$$ f_{R}:= \begin{cases} f \text{ su }D \\ \\ 0 \text{ fuori da }D \end{cases} $$
>è integrabile su $R$.
>Si dimostra che l'integrale di $f_{R}$ non dipende dal particolare rettangolo $R$.
>L'integrale di $f$ su $D$ è il numero:
>$$ \int_{D}f(x,y)dxdy := \int_{R}f_{R}(x,y)dxdy $$

Osserviamo che l'unico insieme $I\subset R$ in cui $f_{R}$ potrebbe non essere continua è la **frontiera del rettangolo**, che è un **insieme di misura nulla**.
>[!def] INSIEME DI MISURA NULLA
>$I\subset \mathbb{R}^n$ è *misurabile* ed ha **misura nulla** se è *contenuto* nell'unione di una famiglia di rettangolini (o palle) $\{ A_{i} \}_{i=1,..,k}$ di *area totale arbitrariamente piccola*.
>Formalmente:
>$$ \forall\varepsilon>0 \text{ }\exists \{ A_{i} \}_{i=1,\dots,k} \text{ : } I\subseteq \bigcup_{i=1}^k A_{i} \text{ , } \sum_{i=1}^k \mathrm{Area}(A_{i}) < \varepsilon $$

E' intuitivo comprendere che il contributo all'integrale dato da un insieme di misura nulla è anch'esso nullo.
Pertanto $f$ è integrabile se la sua estensione $f_{R}$ ad un rettangolo differisce al più su un insieme di misura nulla da una funzione Riemann-integrabile (e nel nostro caso tale insieme sarebbe la frontiera del rettangolo).
## AREA DI UNA REGIONE LIMITATA
Il volume del trapezoide $\mathrm{Trap}(f)$ di una funzione integrabile $f:D\subset \mathbb{R}^2\to[0,+\infty)$ è l'integrale di $f$ su $D$.
Se $f$ è la funzione costante $1$ possiamo definire un metodo per calcolare l'area della regione $D$.
>[!def] AREA DI UNA REGIONE LIMITATA
>L'area della regione limitata $D$ è il volume del trapezoide della funzione costante $1$ sopra $D$. 
>Si pone:
>$$ \mathrm{Area}(D) := \int_{D}dxdy $$
# RIDUZIONE DELL'INTEGRALE
## SU RETTANGOLI
Sia $f:=[a,b]\times[c,d]\to \mathbb{R}$ funzione.
Tagliamo il trapezoide di $f$ a "fette" parallele al piano $yz$.
Per $x$ fissato l'area della sezione:
$$
\{ (y,z): 0\leq z\leq f(x,y) \}
$$
E' data da:
$$
\int_{c}^d f(x,y)dy =: A(x)
$$
La risultante di tali aree è allora:
$$
\int_{a}^b A(x)dx = \int_{a}^b \left( \int_{c}^d f(x,y)dy \right)dx
$$
Tale espressione è l'**integrale iterato** di $f$, prima rispetto ad $y$ e poi rispetto ad $x$.
Chiaramente, avremmo potuto ripetere lo stesso ragionamento scambiando $x$ ed $y$, ottenendo:
$$
\int_{c}^d \left( \int_{a}^b f(x,y)dx \right)dy
$$
>[!theorem] RIDUZIONE SUI RETTANGOLI
>Supponiamo che la funzione $f:[a,b]\times[c,d]\to \mathbb{R}$ sia *integrabile*.
>Allora l'integrale doppio di $f$ sul rettangolo $[a,b]\times[c,d]$ è dato da:
>$$ \int_{[a,b]\times[c,d]}f(x,y)dxdy =  \int_{a}^b \int_{c}^d f(x,y)dydx = \int_{c}^d \int_{a}^b f(x,y)dxdy $$

## SU REGIONI SEMPLICI
Un insieme si dice semplice rispetto ad $x$ se si può descrivere per "fette" parallele al piano $yz$.
Formalmente:
>[!def] INSIEME SEMPLICE (RISPETTO AD $x$)
>L'insieme $D\subset \mathbb{R}^2$ si dice *semplice rispetto* ad $x$ se:
>$$ D=\{ (x,y):x \in[a,b]\text{ , }\alpha(x)\leq y\leq\beta(x) \} $$
>per qualche $a,b\in \mathbb{R}$, $a<b$ e $\alpha\leq\beta:[a,b]\to \mathbb{R}$ *continue*.

Chiaramente, la stessa definizione vale anche per insiemi semplici rispetto ad $y$, scambiando i ruoli di $x$ ed $y$.

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}

\begin{document}

\begin{tikzpicture}[scale=1.4]
\begin{axis}[width=8cm,height=8cm,
			domain = -4:4, y domain = -4:4]

\addplot[color=red] {4};
\addplot[color=red] {x};
\addplot +[color=red,mark=none] coordinates {(-4, -4) (-4, 4)};

\addplot[mark=none] coordinates {(-1.5,1.5)} node{$D$};

\end{axis}
\end{tikzpicture}

\end{document}
```

La regione $D$ delimitata dai segmenti rossi in figura è semplice sia rispetto ad $x$ che ad $y$.
Infatti possiamo scrivere:
$$
D=\{ (x,y): x \in[-4,4] \text{ , } x\leq y\leq 4 \}
$$
O anche:
$$
D=\{ (x,y): y\in[-4,4]\text{ , }-4\leq x\leq y \}
$$
Per insiemi di questo tipo, l'integrale è dato da:
>[!prop] RIDUZIONE SU UNA REGIONE SEMPLICE
>Se la regione è semplice rispetto ad $x$:
>$$ \int_{D}f(x,y)dxdy = \int_{a}^b\int_{\alpha(x)}^{\beta(x)}f(x,y)dydx $$
>Se la regione è semplice rispetto a $y$:
>$$ \int_{D}f(x,y)dxdy = \int_{a}^b\int_{\alpha(y)}^{\beta(y)}f(x,y)dxdy $$

>[!important] NOTA
>Se il dominio è semplice rispetto ad $x$, si integra prima rispetto a $y$.
>Se è semplice rispetto a $y$, si integra prima rispetto ad $x$.
# INTEGRALI GENERALIZZATI
Vediamo come estendere la definizione di integrabilità anche su regioni illimitate.
Sia $f:\mathbb{R}^2\to[0,+\infty)$ *positiva*.
>[!def] INTEGRALE GENERALIZZATO
>Diciamo che $f$ è integrabile su $\mathbb{R}^2$ se, fissato $D=B[0,R]$, $f$ è integrabile su $D$ e se:
>$$ \lim_{ R \to \infty } \int_{D}f(x,y)dxdy < +\infty  $$
>E in questo caso definiamo:
>$$ \int_{D}fdxdy := \lim_{ R \to \infty } \int_{D}f dxdy$$

Diamo una generalizzazione anche per gli integrali iterati.
Per $x$ fissato in $\mathbb{R}$, indichiamo l'integrale generalizzato della funzione $y\mapsto f(x,y)$ con:
$$
\int_{-\infty}^{+\infty}f(x,y)dy
$$
Esso può essere finito o infinito.
>[!def] INTEGRALE ITERATO GENERALIZZATO
>Diciamo che l'integrale iterato
>$$ \int_{-\infty}^{+\infty}\left(  \int_{-\infty}^{+\infty}f(x,y)dy  \right)dx $$
>esiste finito se:
>- Per tutti gli $x \in \mathbb{R}$, eccetto al più su un insieme finito $N$ di *misura nulla*, l'integrale generalizzato in $dy$ esiste finito.
>- La funzione $x \in \mathbb{R}\setminus \{ N \}\mapsto \int_{-\infty}^{+\infty}f(x,y)dy$ è anch'essa integrabile in senso generalizzato.

Per quanto riguarda la riduzione, vale la seguente proposizione:
>[!prop] RIDUZIONE PER INTEGRALI GENERALIZZATI
>Una funzione $f:\mathbb{R}\times \mathbb{R}\to[0,+\infty)$ è integrabile su $\mathbb{R}^2$ *se e solo se* uno dei due integrali iterati:
>$$ \int_{-\infty}^{+\infty}\left(  \int_{-\infty}^{+\infty}f(x,y)dy  \right)dx \text{ , } \int_{-\infty}^{+\infty}\left(  \int_{-\infty}^{+\infty}f(x,y)dx  \right)dy $$
>**esiste finito**.
>In tal caso i due coincidono con l'integrale di $f$ su $\mathbb{R}^2$, indicato con
>$$ \int_{\mathbb{R}^2}f(x,y)dxdy $$

>[!important] ATTENZIONE
>Se la funzione *non è positiva*, i due integrali possono **essere diversi**.
>Può infatti verificarsi il caso $\infty-\infty$.

Chiaramente, per funzioni integrabili (positive) in senso generalizzato valgono tutti i risultati visti fin'ora.
# MATRICE JACOBIANA E CAMBIO DI VARIABILI
Quando calcolavamo integrali in una dimensione utilizzavamo spesso dei cambi di variabile.
Chiaramente lo stesso si può fare anche in $\mathbb{R}^2$; vediamo come.
## MATRICE JACOBIANA
Sia $\varphi(u,v)=(\varphi_{1}(u,v),\varphi_{2}(u,v))$ una funzione di due variabili a valori in $\mathbb{R}^2$.
Definiamo la **matrice Jacobiana** di $\varphi$:
>[!def] MATRICE JACOBIANA
>Siano $X$ aperto di $\mathbb{R}^2$ e $\varphi$ come sopra, con $\varphi_{1},\varphi_{2}:X\subset \mathbb{R}^2\to \mathbb{R}$ entrambe con derivate parziali.
>La matrice Jacobiana di $\varphi=(\varphi_{1},\varphi_{2})$ è:
>$$ \varphi'(u,v) = J_{\varphi}(u,v):= \begin{pmatrix} \nabla \varphi_{1}(u,v) \\ \nabla \varphi_{2}(u,v) \end{pmatrix} = \begin{pmatrix} \partial_{u}\varphi_{1}(u,v) & \partial_{v}\varphi_{1}(u,v) \\ \partial_{u}\varphi_{2}(u,v) & \partial_{v}\varphi_{2}(u,v) \end{pmatrix} $$

>[!important] NOTA
>Scriveremo nel seguito:
>$$ ||\varphi'(u,v)|| := |\det \varphi'(u,v)| $$
## CAMBIO DI VARIABILI
Il cambio di variabili negli integrali doppi si effettua tramite *applicazioni biettive* di due variabili.
>[!theorem] CAMBIO DI VARIABILI
>Siano $X,Y$ aperti di $\mathbb{R}^2$ e $\varphi:X\to Y$ **biettiva** di classe $C^1$ con $\det \varphi'\ne 0$.
>Siano $E\subset X$ limitato, $D=\varphi(E)$.
>Allora $f:D\to \mathbb{R}$ è *integrabile se e solo se* lo è *anche* $(f\circ \varphi)|\varphi'|:E\to \mathbb{R}$ e in tal caso vale:
>$$ \int_{D}f(x,y)dxdy = \int_{E}f(\varphi(u,v))||\varphi'(u,v)||dudv $$

Cioè il nuovo differenziale è:
$$
\det J_{\varphi}(u,v)dudv
$$
## AREE E JACOBIANA
Consideriamo il seguente integrale, che ci restituisce l'area di una regione $D$:
$$
\mathrm{Area}(D) = \int_{D}dxdy
$$
E consideriamo una biezione $\varphi(u,v)$, che soddisfi le ipotesi del teorema di cambio delle variabili.
Allora abbiamo:
$$
\mathrm{Area}(D) = \int_{E}|| \varphi'(u,v) ||dudv
$$
In particolare, se $E$ è il rettangolo $[u,u+\Delta u]\times[v,v+\Delta v]$, l'area di $\varphi(E)=D$ è approssimativamente uguale a (e l'approssimazione è buona per $\Delta u$ e $\Delta v$ sufficientemente piccoli):
$$
\mathrm{Area}(D) = ||\varphi'(u,v)||\Delta u\Delta v = ||\varphi'(u,v)||\mathrm{Area}(E)
$$
Cioè abbiamo:
$$
\frac{\mathrm{Area}(D)}{\mathrm{Area}(E)} = ||\varphi'(u,v)|| = |\det J_{\varphi}(u,v)|
$$
In altri termini, il determinante della matrice Jacobiana è il fattore moltiplicativo locale con il quale si modificano le aree tramite la trasformazione $\varphi$.




