# INDICE SEZIONE
- [ ] [[#INTRODUZIONE]]
- [ ] [[#DEFINIZIONE]]
- [ ] [[#FUNZIONE DI RIPARTIZIONE]]
- [ ] [[#VALORE ATTESO]]
# INTRODUZIONE
Fin'ora abbiamo parlato di variabili aleatorie *discrete*: queste, tuttavia, non sono sufficienti a descrivere tutti gli eventi.

Pensiamo banalmente ad un tiro al bersaglio lungo un segmento di una certa lunghezza.
Sia $X$ l'esito del lancio, uniforme nell'intervallo $[0,1]$.
Abbiamo, *intuitivamente*:
- $P(X<0.5)=P(X>0.5)=0.5$
- $P(X=0.5)=0$

In generale, $P(X=x)=0$ $\forall x$ ma ha probabilità positiva l'evento che $X$ stia in un intervallo del tipo $X\geq x$ oppure $X\in[a,b]$.

>[!important] NOTA
>Notiamo quindi che:
>$$ [a,b] = \bigcup_{x\in[a,b]} x $$
>Ma
>$$ P([a,b]) \ne \sum_{x \in[a,b]}P(X=x) $$

Per trattare problemi di questo tipo introduciamo le *variabili aleatorie continue*:
- Variabili **discrete**: per trattare un *numero finito* oppure *infinità numerabile* di valori.
- Variabili **continue**: per trattare *insiemi non numerabili* di valori.
# DEFINIZIONE
>[!def] VARIABILE ALEATORIA CONTINUA
>Una v.a. $X$ si dice **continua** se esiste una *funzione*:
>$$ f_{X}:\mathbb{R}\to[0,+\infty) $$
>**integrabile** tale che:
>$$ P(X\in A) = \int_{A} f_{X}(x)dx \text{ , }\forall A\subseteq \mathbb{R}$$
>La funzione $f_{X}$ è la **densità** della variabile.

In realtà è sufficiente che $f_{X}$ sia definita ovunque eccetto eventualmente su un *insieme di misura nulla* (ad esempio una successione di punti).

Inoltre, ovviamente, deve valere:
>[!prop] PROPOSIZIONE
>$$ \int_{-\infty}^{+\infty}f_{X}(x)dx = 1 $$

Perchè $X$ deve pur assumere qualche valore, chiaramente senza "sforare" la probabilità massima $1$.
## PROBABILITA' DI UN INTERVALLO
Possiamo anche interpretare questa proposizione come conseguenza del seguente fatto:
>[!prop] PROBABILITA' DI UN INTERVALLO
>La probabilità che $X$ si in un intervallo $(a,b)\subseteq \mathbb{R}$ (o equivalentemente anche $[a,b]$) è data da:
>$$ P(X\in(a,b)) = \int_{a}^b f_{X}(x)dx $$

Notiamo, come avevamo già intuito in > [[#INTRODUZIONE]] che, se $a=b$:
$$
P(X=a) = \int_{a}^a f_{X}(x)dx = 0
$$
E quindi anche:
$$
P(X\in(a,b)) = P(X\in(a,b]) = P(X\in[a,b)) = P(X\in[a,b])
$$

>[!important] INTERPRETAZIONE DENSITA'
>Consideriamo $\varepsilon>0$. Abbiamo:
>$$ P\left( a-\frac{\varepsilon}{2} \leq X \leq a+\frac{\varepsilon}{2} \right) = \int_{a-\frac{\varepsilon}{2}}^{a+\frac{\varepsilon}{2}}f_{X}(x)dx \approx \varepsilon f(a)$$
>Per $\varepsilon$ *sufficientemente piccoli*, la probabilità che $X$ assuma valori in un *intervallo di ampiezza* $\varepsilon$ intorno ad $a$ è circa $\varepsilon f(a)$.
# FUNZIONE DI RIPARTIZIONE
Come per le variabili discrete avevamo definito la *funzione di distribuzione*, definiamo ora la **funzione di ripartizione**:
>[!def] FUNZIONE DI RIPARTIZIONE
>$$ F_{X}(a) = P(X\leq a) = P(X<a) = \int_{-\infty}^a f_{X}(x)dx $$

Dal teorema fondamentale del calcolo integrale troviamo che vale quindi:
$$
f_{X}(x) = \frac{d}{dx}F_{X}(x) = F'(x)
$$
In tutti i punti in cui $F_{X}$ è *derivabile*.
>[!important] PROPRIETA'
>1. $0\leq F_{X}(x)\leq1$ $\forall x \in \mathbb{R}$
>2. $\lim_\limits{ x \to -\infty }F_{X}(x)=0$ e $\lim_\limits{ x \to +\infty }F_{X}(x)=1$ 
>3. Se $x_{1}\leq x_{2}$ allora $F_{X}(x_{1})\leq F_{X}(x_{2})$
>4. $F_{X}$ è *continua*.
>5. $F_{X}$ è $C^1$ a tratti, cioè esistono $a_{1}<a_{2}<\dots<a_{k}\in \mathbb{R}$ tali che $\frac{d}{dx}F_{X}$ *esiste* ed è *continua* $\forall x\ne a_{1},a_{2},\dots,a_{k}$.

Dal punto 5 segue:
$$
f_{X}(x) = \begin{cases}
\frac{d}{dx}F_{X}(x) & \text{ se } x\not\in \{ a_{1},a_{2},\dots,a_{k} \} \\
 \\
\text{arbitrario} & \text{ se } x \in \{ a_{1},a_{2},\dots,a_{k} \}
\end{cases}
$$
# VALORE ATTESO
>[!def] VALORE ATTESO (v.a. continue)
>Sia $X$ una v.a. continua di densità $f_{X}$.
>Il valore atteso di $X$, indicato con $\mathbb{E}(X)$, è definito come segue:
>$$ \mathbb{E}(X) = \int_{\mathbb{R}}xf_{X}(x)dx $$

>[!important] PROPRIETA'
>1. Se $X\leq Y$ allora $\mathbb{E}[X]\leq\mathbb{E}[Y]$ (*monotonia*)
>2. Se $a,b\in \mathbb{R}$ allora $\mathbb{E}[aX+bY]=a\mathbb{E}[X]+b\mathbb{E}[Y]$ (*linearità*)
>3. Se $g:\mathbb{R}\to \mathbb{R}$, allora $\mathbb{E}[g(X)]=\int_{\mathbb{R}}g(x)f_{X}(x)dx$
>4. Se $X$ è *non negativa*, allora $\mathbb{E}[X]=\int_{0}^\infty P(X>x)dx$
# VARIANZA
>[!def] VARIANZA
>Sia $X$ una v.a. continua di densità $f_{X}$.
>La sua varianza è data da:
>$$ \text{Var}(X) = \mathbb{E}[(X-\mathbb{E}[X])^2] = \int_{\mathbb{R}}(x-\mathbb{E}[X])^2f_{X}(x)dx $$

Anche per le variabili continue vale:
$$
\text{Var}(X) = \mathbb{E}[X^2] - \mathbb{E}[X]^2
$$
>[!important] PROPRIETA'
>1. Se $a,b\in \mathbb{R}$ allora $\text{Var}(aX+b)=a^2\text{Var}(X)$
>2. $\text{Var}(X+Y)=\text{Var}(X)+\text{Var}(Y)-2\text{Cov}(X,Y)$

