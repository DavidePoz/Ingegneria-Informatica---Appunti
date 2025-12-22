# INDICE SEZIONE
- [ ] [[#CASO DISCRETO]]
- [ ] [[#CASO CONTINUO]]
- [ ] [[#SOMME DI VARIABILI INDIPENDENTI]]
# CASO DISCRETO
>[!def] VARIABILE CONGIUNTA DISCRETA
>Una *variabile congiunta discreta* su uno spazio campionario $\Omega$ è una *coppia* di v.a. discrete $(X,Y)$:
>$$ \omega \in\Omega \mapsto (X(\omega),Y(\omega)) $$

La *densità congiunta* diventa quindi:
>[!def] DENSITA' CONGIUNTA
>$$ P_{X,Y} : \mathbb{R}^2\longrightarrow[0,1] $$
>$$ (x,y)\mapsto P(X=x,Y=y) $$

E chiaramente deve valere:
$$
\sum_{x,y}P_{X,Y}(x,y) = 1
$$
>[!def] DENSITA' MARGINALI
>Sono le *densità* $P_{X}$ e $P_{Y}$.
>Vale inoltre:
>$$ P_{X}(x) = \sum_{y}P_{X,Y}(x,y) $$
>$$ P_{Y}(y) = \sum_{x}P_{X,Y}(x,y) $$

>[!important] NOTA
>Dalla *densità congiunta* si ricavano le *marginali*. **Non viceversa**!
## ESEMPIO: DENSITA' MARGINALI E CONGIUNTE
Siano $X$ e $Y$ due variabili con valori in $\{ -1,1 \}$
Consideriamo:
- $P_{X,Y}(-1,-1)=\frac{1}{4}$
- $P_{X,Y}(1,-1)=\frac{1}{4}$
- $P_{X,Y}(-1,1)=\frac{1}{4}$
- $P_{X,Y}(1,1)=\frac{1}{4}$

Schematizziamo la situazione:
$$
\begin{matrix}
\begin{matrix}
\text{ }\text{ }\text{ }\textbackslash\text{ } Y \\ X\text{ }  \textbackslash
\end{matrix} & | & -1 & 1 & | & \sum_{y} \\
-- & | & -- & -- & | & -- \\
-1 & | & \frac{1}{4} & \frac{1}{4} & |  & \frac{1}{2} \\
1 & | & \frac{1}{4} & \frac{1}{4} & | & \frac{1}{2} \\
-- & | & -- & -- & | & -- \\
\sum_{x} & | & \frac{1}{2} & \frac{1}{2} & | & 
\end{matrix}
$$

Notiamo che:
- Sommando le probabilità nella prima riga troviamo $P_{X}(-1)=\frac{1}{2}$ (somma degli eventi in cui $X$ è $-1$).
- Sommando le probabilità nella prima colonna troviamo $P_{Y}(-1)=\frac{1}{2}$ (somma degli eventi in cui $Y$ è $-1$).
- Lo stesso ragionamento vale anche per la seconda riga e la seconda colonna.

Supponiamo ora che invece si abbia:
- $P_{X,Y}(-1,-1)=\frac{1}{8}$
- $P_{X,Y}(1,-1)=\frac{3}{8}$
- $P_{X,Y}(-1,1)=\frac{3}{8}$
- $P_{X,Y}(1,1)=\frac{1}{8}$

Adesso abbiamo allora:
$$
\begin{matrix}
\begin{matrix}
\text{ }\text{ }\text{ }\textbackslash\text{ } Y \\ X\text{ }  \textbackslash
\end{matrix} & | & -1 & 1 & | & \sum_{y} \\
-- & | & -- & -- & | & -- \\
-1 & | & \frac{1}{8} & \frac{3}{8} & |  & \frac{1}{2} \\
1 & | & \frac{3}{8} & \frac{1}{8} & | & \frac{1}{2} \\
-- & | & -- & -- & | & -- \\
\sum_{x} & | & \frac{1}{2} & \frac{1}{2} & | & 
\end{matrix}
$$

>[!important] NOTA
>Abbiamo le **stesse marginali** ma **diversa congiunta**: ecco perchè dalle marginali *non possiamo ottenere la congiunta*.
## CARATTERISTICHE
Il valore atteso di una *composizione di variabili congiunte* è dato da:
>[!prop] VALORE ATTESO DI COMPOSTE
>$$ \mathbb{E}[g(X,Y)] = \sum_{x,y} g(x,y)P_{X,Y}(x,y) $$

>[!prop] PROPOSIZIONE: INDIPENDENZA
>Si ha:
>$$ X\perp Y \Longleftrightarrow P_{X,Y}(x,y) = P_{X}(x)P_{Y}(y) $$
## ESEMPIO
Siano $X,Y\in \{ -1,1 \}$ con $P_{X,Y}(x,y)=\frac{1}{4}$ $\forall x,y$.
Allora
- $P_{X}(x)=\frac{1}{2}\forall x$
- $P_{Y}(y)=\frac{1}{2}\forall y$

Abbiamo quindi:
$$
P_{X,Y}(x,y) = P_{X}(x)P_{Y}(y) \text{ }\forall x,y
$$
E possiamo allora concludere che $X\perp Y$.
# CASO CONTINUO
>[!def] VARIABILE CONGIUNTA CONTINUA
>Una *variabile congiunta continua* è una *coppia* di v.a. $(X,Y)$ tale che esista una funzione $f_{X,Y}:\mathbb{R}^2\to[0,+\infty)$ tale che
>$$ P((X,Y)\in A) = \int_{A}f_{X,Y}(x,y)dxdy \text{ }\forall A\subseteq \mathbb{R}^2$$

E chiaramente deve valere:
$$
\int_{\mathbb{R}^2}f_{X,Y}(x,y)dxdy = 1
$$
>[!def] DENSITA' MARGINALI
>Le densità *marginali* sono date da:
$$ f_{X}(x) = \int_{\mathbb{R}}f_{X,Y}(x,y)dy $$
$$ f_{Y}(y) = \int_{\mathbb{R}}f_{X,Y}(x,y)dx $$

Data un composizione di una congiunta, il suo valore atteso è dato da:
>[!prop] VALORE ATTESO DI COMPOSTE
>$$ \mathbb{E}[g(X,Y)] = \int_{\mathbb{R}^2}g(x,y)f_{X,Y}(x,y)dxdy $$

>[!prop] PROPOSIZIONE: INDIPENDENZA
>Vale la proposizione seguente:
>$$ X\perp Y \Longleftrightarrow f_{X,Y}(x,y) = f_{X}(x)f_{Y}(y) \text{ }\forall x,y$$
## ESEMPIO
Due autobus arrivano *indipendentemente uno dall'altro* con tempi:
- $T_{1}\sim\text{Exp}(2)$
- $T_{2}\sim\text{Exp}(3)$

Calcoliamo $P(T_{1}<T_{2})$.

Le densità *marginali* sono:
- $f_{T_{1}}(t_{1})=2e^{ -2t_{1} }$
- $f_{T_{2}}(t_{2})=3e^{ -3t_{2} }$

Siccome le due variabili sono *indipendenti*, la densità *congiunta* è:
$$
f_{T_{1},T_{2}}(t_{1},t_{2}) = 6e^{ -2t_{1} }e^{ -3t_{2} }
$$
Abbiamo allora:
$$
P(T_{1}<T_{2}) = \int_{0}^\infty \int_{t_{1}}^{\infty} f_{T_{1},T_{2}}(t_{1},t_{2})dt_{2}dt_{1}
$$
$$
= \int_{0}^\infty f_{T_{1}}(t_{1}) \underbrace{ \left( \int_{t_{1}}^\infty f_{T_{2}}(t_{2})dt_{2} \right) }_{ P(T_{2}>t_{1}) }dt_{1}
$$
$$
= \dots = \frac{2}{5}
$$
# SOMME DI VARIABILI INDIPENDENTI
Siano $X,Y$ due v.a. *indipendenti* e consideriamo la loro somma $X+Y$.
Calcoliamo $F_{X+Y}(a) = P(X+Y\leq a)$:
$$
P(X+Y\leq a) = \iint_{x+y\leq a} f_{X}(x)f_{Y}(y)dxdy
$$
$$
= \int_{\mathbb{R}}\int_{-\infty}^{a-y} f_{X}(x)f_{Y}(y)dxdy
$$
$$
= \int_{\mathbb{R}} \left( \int_{-\infty}^{a-y} f_{X}(x)dx \right) f_{Y}(y)dy
$$
E concludiamo:
>[!prop] FUNZIONE DI RIPARTIZIONE DELLA SOMMA
>$$ F_{X+Y} = \int_{\mathbb{R}} F_{X}(a-y) f_{Y}(y)dy $$

>[!important] NOTA
>$F_{X+Y}$ è la **convoluzione** di $F_{X}$ e $f_{Y}$.

Siccome sappiamo che
$$
f_{X+Y}(a) = \frac{d}{da}F_{X+Y}(a) 
$$
Possiamo allora calcolare anche $f_{X+Y}$:
$$
f_{X+Y}(a) = \frac{d}{da}\int_{\mathbb{R}}F_{X}(a-y)f_{Y}(y)dy
$$
$$
= \int_{\mathbb{R}} \frac{d}{da}F_{X}(a-y)f_{Y}(y)dy
$$
E concludiamo:
>[!prop] DENSITA' DELLA SOMMA
>$$ f_{X+Y} = \int_{\mathbb{R}} f_{X}(a-y)f_{Y}(y)dy $$

>[!important] NOTA
>$f_{X+Y}$ è la **convoluzione** di $f_{X}$ e $f_{Y}$.
## VARIABILI UNIFORMI
Siano $X,Y\sim U(0,1)$ *indipendenti*.
Abbiamo:
$$
f_{X}(x) = \begin{cases}
1 & x \in(0,1) \\
0 & x \not\in (0,1)
\end{cases}
$$
$$
f_{Y}(y) = \begin{cases}
1 & y \in(0,1) \\
0 & y \not\in (0,1)
\end{cases}
$$
Quindi:
$$
f_{X+Y}(a) = \int_{0}^1 f_{X}(a-y)\cancelto{ 1 }{ f_{Y}(y) }dy
$$
Ora, siccome $a-y\in[a-1,a]$:
- Se $a\in[0,1]$ abbiamo $f_{X+Y}(a) = \int_\limits{0}^a dy =a$
- Se $a\in(1,2)$ abbiamo (ponendo $t=a-y$) $f_{X+Y}(a)=\int_\limits{a-1}^1dt =2-a$
Quindi $X+Y$ ha una *distribuzione triangolare*.
## VARIABILI ESPONENZIALI
Siano $X,Y\sim\text{Exp}(\lambda)$ indipendenti.
Allora:
$$
X+Y \sim \Gamma(2,\lambda)
$$
## VARIABILI GAMMA
Siano $X\sim\Gamma(n,\lambda)$ e $Y\sim\Gamma(m,\lambda)$ indipendenti.
Allora:
$$
X+Y \sim \Gamma(n+m,\lambda)
$$
## VARIABILI NORMALI
Siano $X\sim\mathcal{N}(\mu_{X},\sigma_{X}^2)$ e $Y\sim\mathcal{N}(\mu_{Y},\sigma^2_{Y})$ indipendenti.
Allora:
$$
X+Y \sim \mathcal{N}(\mu_{X}+\mu_{Y},\sigma^2_{X}+\sigma^2_{Y})
$$
## VARIABILI BINOMIALI
Siano $X\sim\text{Bin}(n,p)$ e $Y\sim\text{Bin}(m,p)$ indipendenti.
$$
P(X+Y=k) = \sum_{i=0}^k P(X=i,Y=k-i)
$$
>[!important] NOTA
>Questa espressione ha una forma molto simile alla *convoluzione* vista sopra: è infatti l'equivalente discreto.

$$
\sum_{i=0}^k P(X=i)P(Y=k-i)
$$
$$
= \sum_{i=0}^k \begin{pmatrix} n \\ i \end{pmatrix} p^i(1-p)^{n-i} \begin{pmatrix} m \\ k-i \end{pmatrix} p^{k-i}(1-p)^{m-k+i}
$$
$$
=\sum_{i=0}^k \begin{pmatrix} n \\ i \end{pmatrix} \begin{pmatrix} m \\ k-i \end{pmatrix} p^k(1-p)^{n+m-k}
$$
$$
= \begin{pmatrix} n+m \\ k \end{pmatrix} p^k(1-p)^{n+m-k}
$$
Quindi:
$$
X+Y\sim\text{Bin}(n+m,p)
$$
## VARIABILI GEOMETRICHE
Siano $X_{1},\dots,X_{r}\sim\text{Geo}(p)$ indipendenti.
La variabile $X_{1}+\dots+X_{r}$ descrive il *numero di tentativi per ottenere l'r-esimo successo*.
$$
P(X_{1}+\dots+X_{r}=n) = \begin{pmatrix} n-1 \\ r-1 \end{pmatrix}p^r(1-p)^{n-r}
$$
>[!important] NOTA
>Negli $n$ tentativi dobbiamo avere $r$ successi ($p^r$) e $n-r$ insuccessi ($p^{n-r}$).
>Per contare i modi in cui possiamo distribuire questi eventi, però, fissiamo che l'*ultimo successo avvenga all'n-esimo tentativo* (se avvenisse prima, sarebbero stati necessari *meno* di $n$ tentativi): nei primi $n-1$ distribuiamo gli altri $r-1$ successi.
## VARIABILI DI POISSON
Siano $X\sim\text{Po}(\lambda x)$ e $Y\sim\text{Po}(\lambda y)$ indipendenti.
$$
P(X+Y=k) = \sum_{i=0}^k P(X=i,Y=k-i)
$$
$$
= \sum_{i=0}^k e^{ -\lambda x } \frac{(\lambda x)^i}{i!} e^{ -\lambda y } \frac{(\lambda y)^{k-i}}{(k-i)!}
$$
$$
= e^{ -\lambda(x+y) } \sum_{i=0}^k \frac{(\lambda x)^i (\lambda y)^{k-i}}{i!(k-i!)}
$$
$$
= \frac{e^{ -\lambda(x+y) }}{k!} \sum_{i=0}^k \frac{k!}{i!(k-i)!} (\lambda x)^i(\lambda y)^{k-i}
$$
$$
\frac{e^{ -\lambda(x+y) }}{k!} (\lambda x+\lambda y)^k
$$
Quindi:
$$
X+Y \sim \text{Po}(\lambda x+\lambda y)
$$
