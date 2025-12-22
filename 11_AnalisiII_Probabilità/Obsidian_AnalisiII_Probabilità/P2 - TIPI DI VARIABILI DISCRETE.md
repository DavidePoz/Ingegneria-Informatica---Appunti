# INDICE SEZIONE
- [ ] [[#VARIABILI DI BERNOULLI]]
- [ ] [[#VARIABILI BINOMIALI]]
- [ ] [[#VARIABILI GEOMETRICHE]]
- [ ] [[#VARIABILI DI POISSON]]
# VARIABILI DI BERNOULLI
>[!def] ESPERIMENTO DI BERNOULLI
>Un esperimento è detto *di Bernoulli* se ha solo **due esiti possibili**: successo o insuccesso.

A partire da questa definizione possiamo definire anche una v.a. di Bernoulli:
>[!def] V.A. DI BERNOULLI
>Una v.a. $X$ si dice *di Bernoulli* di *parametro* $p$ se:
>1. $X\in \{ 0,1 \}$
>2. $P_{X}(1)=P(X=1)=p$ e $P_{X}(0)=P(X=0)=1-p$.
>
>E si indica con $X\sim\text{Be}(p)$.
## ESEMPI
Lancio di una moneta equilibrata:
$$
X = \begin{cases}
1 & \text{se esce T} \\
0 & \text{se esce C}
\end{cases}
$$
E abbiamo $X\sim\text{Be}\left( \frac{1}{2} \right)$.

Un altro esempio è il lancio di un dado regolare:
$$
X = \begin{cases}
1 & \text{se esce 6} \\
0 & \text{se non esce 6}
\end{cases}
$$
E in questo caso $X\sim\text{Be}\left( \frac{1}{6} \right)$.
## CARATTERISTICHE
Per variabili di Bernoulli si ha:
$$
F_{X}(x) = \begin{cases}
0 & x<0 \\
1-p & 0\leq x<1 \\
1 & x\geq 1
\end{cases}
$$
$$
\mathbb{E}[X] = 1\cdot p + 0\cdot(1-p) = p
$$
$$
\mathbb{E}[X^2] = 1^2\cdot p + 0^2\cdot(1-p) = p
$$
Quindi in generale:
$$
\mathbb{E}[X^n] = p
$$
Per quanto riguarda la varianza si ha:
$$
\text{Var}(X) = \mathbb{E}[X^2]-\mathbb{E}[X]^2 = p-p^2 = p(1-p)
$$
>[!prop] MEDIA E VARIANZA DI VARIABILI DI BERNOULLI
>$$ \mathbb{E}[X] = p $$
>$$ \text{Var}(X) = p(1-p) $$
# VARIABILI BINOMIALI
Immaginiamo di ripetere lo *stesso esperimento* di Bernoulli $n$ volte in maniera **indipendente**: ogni volta la probabilità di successo è $p$ e vogliamo contare il *numero totale di successi*.
>[!def] VARIABILE BINOMIALE
>Una v.a. $X$ si dice *binomiale* di *parametri* $n$ e $p$ se:
>1. $X\in \{ 0,1,\dots,n \}$
>2. $P_{X}(k)=P(X=k)= \begin{pmatrix} n \\ k \end{pmatrix}p^k(1-p)^{n-k}$, $\forall k=0,1,\dots,n$
>
>E si indica con $X\sim\text{Bin}(n,p)$

Per cui $n$ è il *numero di volte* che si ripete l'esperimento, e $p$ è la probabilità che il singolo esperimento abbia *successo*.
>[!important] NOTA
>E infatti $P(X=k)$ è calcolata proprio tenendo conto di quanti *modi possibili* esistono di avere *successo* $k$ volte e *insuccesso* le rimanenti $n-k$ volte.

```tikz
\usepackage{pgfplots}

\begin{document}
\begin{tikzpicture}[
	scale = 1.4,
    declare function={binom(\k,\n,\p)=\n!/(\k!*(\n-\k)!)*\p^\k*(1-\p)^(\n-\k);}
]
\begin{axis}[
    samples at={0,...,40},
    yticklabel style={
        /pgf/number format/fixed,
        /pgf/number format/fixed zerofill,
        /pgf/number format/precision=1
    }
]
\addplot [only marks, cyan] {binom(x,20,0.5)}; 
	\addlegendentry{$p=0.5$ e $n=20$}
\addplot [only marks, orange] {binom(x,40,0.5)}; 
	\addlegendentry{$p=0.5$ e $n=40$}
\end{axis}
\end{tikzpicture}
\end{document}
```
## ESEMPI
Lancio di una moneta equilibrata $n=4$ volte, osservando il *numero di teste* $X$.
Ad ogni lancio $P(T)=\frac{1}{2}$, per cui:
$$
X\sim\text{Bin}\left( 4, \frac{1}{2} \right)
$$
Abbiamo:
- $P(X=0)=\begin{pmatrix} 4 \\ 0 \end{pmatrix}\left( \frac{1}{2} \right)^0\left( 1-\frac{1}{2} \right)^{4-0}=\frac{1}{16}$
- $P(X=1)=\begin{pmatrix} 4 \\ 1 \end{pmatrix}\left( \frac{1}{2} \right)^1\left( 1-\frac{1}{2} \right)^{4-1}=\frac{4}{16}$
- $P(X=2)=\begin{pmatrix} 4 \\ 2 \end{pmatrix}\left( \frac{1}{2} \right)^2\left( 1-\frac{1}{2} \right)^{4-2}=\frac{6}{16}$
- $P(X=3)=P(X=1)=\frac{4}{16}$
- $P(X=4)=P(X=1)=\frac{1}{16}$

Altro esempio: $n=6$ lanci di un dado regolare. 
Qual è la probabilità che esca *almeno 4 volte* un numero minore o uguale a $2$?
Abbiamo $X=$ n° di volte che esce $1$ o $2$.
$$
X\sim\text{Bin}\left( 6, \frac{1}{3} \right)
$$
Il valore cercato è:
$$
P^* = P(X\geq 4) = P(X=4) + P(X=5) + P(X=6)
$$
Applicando la definizione di variabile binomiale troviamo:
$$
P^* = \begin{pmatrix}
6 \\
4
\end{pmatrix}\left( \frac{1}{3} \right)^4\left( \frac{2}{3} \right)^2 + 
\begin{pmatrix}
6 \\
5
\end{pmatrix}\left( \frac{1}{3} \right)^5\left( \frac{2}{3} \right)^1 +
\begin{pmatrix}
6 \\
6
\end{pmatrix}\left( \frac{1}{3} \right)^6 = \frac{73}{729} \approx 10\%
$$
## CARATTERISTICHE
Se $X_{1},X_{2},\dots,X_{n}\sim\text{Be}(p)$ *indipendenti*, allora:
$$
X= \sum_{i=1}^n X_{i} \sim \text{Bin}(n,p)
$$
Quindi è facile verificare che la media è data da:
$$
\mathbb{E}[X] = \mathbb{E}\left[ \sum_{i=1}^nX_{i} \right] = \sum_{i=1}^n\mathbb{E}[X_{i}] = \sum_{i=1}^n p = np
$$
E, per quanto riguarda la varianza:
$$
\text{Var}(X) = \text{Var}\left( \sum_{i=1}^nX_{i} \right) = \sum_{i=1}^n \text{Var}(X_{i}) = \sum_{i=1}^n p(1-p) = np(1-p)
$$
>[!prop] MEDIA E VARIANZA DI VARIABILI BINOMIALI
>$$ \mathbb{E}[X] = np $$
>$$ \text{Var}(X) = np(1-p) $$
>

La *funzione di distribuzione* è invece data da:
$$
F_{X}(k) = P(X\leq k) = \sum_{i=1}^k P(X=i) = \sum_{i=1}^k \begin{pmatrix}
n \\
i
\end{pmatrix}p^i(1-p)^{n-i}
$$
# VARIABILI GEOMETRICHE
Ripetiamo un certo esperimento *finchè non abbiamo successo*.
Come possiamo contare il numero di tentativi necessari?
>[!def] VARIABILI GEOMETRICHE
>Una v.a. si dice *geometrica* di *parametro* $p$ se:
>1. $X\in \{ 1,2,\dots, \}=\mathbb{N}_{\geq 1}$
>2. $P_{X}(k)=P(X=k)=p(1-p)^{k-1}$
>
>Si indica con $X\sim\text{Geo}(p)$.

>[!important] NOTA
>La probabilità è quella di avere successo dopo $k$ tentativi: i $k-1$ precedenti devono avere insuccesso, ed il $k$-esimo devo andare a buon fine.

```tikz
\usepackage{pgfplots}

\begin{document}
\begin{tikzpicture}[
	scale = 1.4,
    declare function={geom(\k,\p)=\p*(1-\p)^(\k -1);}
]
\begin{axis}[
    samples at={1,...,15},
    yticklabel style={
        /pgf/number format/fixed,
        /pgf/number format/fixed zerofill,
        /pgf/number format/precision=1
    }
]
\addplot [only marks, cyan] {geom(x,0.3)}; 
	\addlegendentry{$p=0.3$}
\addplot [only marks, orange] {geom(x,0.6)}; 
	\addlegendentry{$p=0.6$}
\end{axis}
\end{tikzpicture}
\end{document}
```
## ESEMPI
Lanciamo più volte una moneta equilibrata.
Qual è la probabilità che servano $5$ *o più* lanci affinchè esca $T$?
Abbiamo $X=$ n° di lanci per ottenere $T$.
$$
X\sim\text{Geo}\left( \frac{1}{2} \right)
$$
Si ha (\*) :
$$
P(X\geq 5) = P(X>4) = (1-p)^4 = \frac{1}{2^4}
$$
Altro esempio: lanciamo più volte un dado regolare.
Qual è la probabilità che servano *al più* $3$ lanci per ottenere $6$?
$$
X\sim\text{Geo}\left( \frac{1}{6} \right)
$$
Abbiamo:
$$
P(X\leq 3) = P(X=1) + P(X=2) + P(X=3)
$$
Applicando la definizione troviamo:
$$
P(X\leq 3) = \frac{1}{6} + \frac{1}{6}\cdot \frac{5}{6} + \frac{1}{6}\cdot\left( \frac{5}{6} \right)^2 = \frac{91}{216} \approx 42\%
$$
## CARATTERISTICHE
>[!prop] NOTA (\*)
>Vale:
>$$ P(X>k) = (1-p)^k \text{ , }\forall k\in \mathbb{N}$$

>[!check] DIM.
>Infatti:
>$$ P(X>k) = P(X=k+1) + P(X=k+1) + \dots $$
>$$ = p(1-p)^k + p(1-p)^{k+1} +\dots $$
>$$ = p(1-p)^k (1+(1-p)+(1-p)^2 +\dots) $$
>$$ = p(1-p)^k \sum_{i=0}^\infty(1-p)^i $$
>$$ = p(1-p)^k \frac{1}{1-(1-p)} = (1-p)^k $$

Per cui è facile verificare che la funzione di distribuzione è data da:
$$
F_{X}(k) = P(X\leq k) = 1-P(X>k) = 1-(1-p)^k 
$$
>[!prop] ASSENZA DI MEMORIA
>$$ P(X>k+m|X>k) = P(X>m) $$

>[!check] DIM.
>Infatti:
>$$ P(X>k+m|X>k) = \frac{P(X>k+m,X>k)}{P(X>k)} $$
>$$ = \frac{P(X>k+m)}{P(X>k)} = \frac{(1-p)^{k+m}}{(1-p)^k} $$
>$$ = (1-p)^m = P(X>m) $$

>[!prop] PROPOSIZIONE
>Se $X_{1},X_{2},\dots,X_{n}\sim\text{Be}(p)$ sono *indipendenti*, allora:
>$$ X=\text{min}_{k\geq1}\{ X_{k}= 1 \} \sim \text{Geo}(p) $$

Per trovare la media di una variabile geometrica presentiamo il seguente fatto:
$$
\sum_{k=1}^{\infty} kx^{k-1} = \sum_{k=1}^\infty \frac{d}{dx}(x^k) = \frac{d}{dx}\left( \sum_{k=1}^\infty x^k \right) = \frac{d}{dx}\left( \frac{1}{1-x} \right) = \frac{1}{(1-x)^2}
$$
Allora:
$$
\mathbb{E}[X] = \sum_{k=1}^\infty k\cdot P_{X}(k) = \sum_{k=1}^\infty kp(1-p)^{k-1}
$$
$$
= p \sum_{k=1}^\infty k(1-p)^{k-1} = p \frac{1}{(1-(1-p))^2} = \frac{1}{p}
$$
In modo analogo si calcola anche:
$$
\mathbb{E}[X^2] = \frac{2}{p^2} - \frac{1}{p}
$$
E quindi:
$$
\text{Var}(X) = \mathbb{E}[X^2] - \mathbb{E}[X]^2 = \frac{2}{p^2}-\frac{1}{p}-\frac{1}{p^2} = \frac{1-p}{p^2}
$$
>[!prop] MEDIA E VARIANZA DI VARIABILI GEOMETRICHE
>$$ \mathbb{E}[X] = \frac{1}{p} $$
>$$ \text{Var}(X) = \frac{1-p}{p^2} $$
# VARIABILI DI POISSON
>[!def] VARIABILE DI POISSON
>Una v.a. si dice di *Poisson* di *parametro* $\lambda>0$ se:
>1. $X\in \{ 0,1,2,\dots \}=\mathbb{N}$
>2. $P_{X}(k) = P(X=k) = e^{-\lambda} \frac{\lambda^k}{k!}$
>
>E si scrive $X\sim\text{Po}(\lambda)$.

Le variabili di Poisson descrivono la *distribuzione* del *numero di eventi* che accadono in un certo *intervallo temporale*.
Per esempio:
- n° di persone che entrano in un negozio in un giorno.
- n° di treni che passano in una stazione in un'ora.
- n° di richieste che arrivano ad un server web in un minuto.

```tikz
\usepackage{pgfplots}

\begin{document}
\begin{tikzpicture}[
	scale = 1.4,
    declare function={po(\k,\p)=e^(-\p)*(\p)^(\k)/(\k!);}
]
\begin{axis}[
    samples at={1,...,20},
    yticklabel style={
        /pgf/number format/fixed,
        /pgf/number format/fixed zerofill,
        /pgf/number format/precision=1
    }
]
\addplot [only marks, cyan] {po(x,5)}; 
	\addlegendentry{$\lambda=5$}
\addplot [only marks, orange] {po(x,10)}; 
	\addlegendentry{$\lambda=10$}
\end{axis}
\end{tikzpicture}
\end{document}
```
## ESEMPIO
Consideriamo la variabile $X=$n° di cartellini gialli in una partita di calcio.
$$
X\sim\text{Po}(5)
$$
Abbiamo, per esempio:
$$
P(X=3) = e^{ -5 }\cdot \frac{5^3}{3!} \approx 0.14 = 14\%
$$
E anche:
$$
P(X\geq 2) = 1-P(X<2) = 1-P(X=0)-P(X=1)
$$
$$
= 1 -e^{ -5 } \frac{5^0}{0!} -e^{ -5 } \frac{5}{1!}
$$
$$
= 1-6e^{ -5 } \approx 0.96 = 96\%
$$
## LEGAME CON LE VARIABILI BINOMIALI
Possiamo **approssimare** una *binomiale* $\text{Bin}(n,p)$ con una $\text{Po}(\lambda=np)$ quando $p$ è *piccolo* ed $n$ è *grande* tali che $np>0$.

Siano $\lambda>0$ e $X_{n}\sim\text{Bin}\left( n, \frac{\lambda}{n} \right)$ con $n=1,2,\dots$
Allora:
>[!prop] PROPOSIZIONE
>$$ \lim_{ n \to \infty } P(X_{n}=k) = e^{ -\lambda } \frac{\lambda^k}{k!} $$

Infatti:
>[!check] DIM.
>$$ P(X_{n}=k) = \begin{pmatrix} n \\ k \end{pmatrix} \left( \frac{\lambda}{n} \right)^k \left( 1- \frac{\lambda}{n} \right)^{n-k} $$
>$$ = \frac{n!}{k!(n-k)!} \frac{\lambda^k}{n^k} \frac{\left( 1- \frac{\lambda}{n} \right)^n}{\left( 1 - \frac{\lambda}{n} \right)^k} $$
>$$ = \frac{n(n-1)\dots(n-k+1)}{n^k} \frac{\left( 1- \frac{\lambda}{n} \right)^n}{\left( 1 - \frac{\lambda}{n} \right)^k} \frac{\lambda^k}{k!} $$
>Per $n$ "*grande*" (e $p$ "*piccolo*") troviamo:
>$$ 1\cdot \frac{e^{-\lambda}}{1}\cdot \frac{\lambda^k}{k!} = e^{-\lambda} \frac{\lambda^k}{k!} $$
## CARATTERISTICHE
Calcoliamo la media di una variabile di Poisson $X$:
$$
\mathbb{E}[X] = \sum_{k=0}^\infty kP_{X}(k) = \sum_{k=0}^\infty ke^{ -\lambda } \frac{\lambda^k}{k!}
$$
$$
= \lambda e^{ -\lambda } \sum_{k=1}^\infty \frac{\lambda^{k-1}}{(k-1)!} = \lambda e^{ -\lambda } \sum_{j=0}^\infty \frac{\lambda^j}{j!}
$$
$$
= \lambda e^{ -\lambda }\cdot e^{ \lambda } = \lambda
$$
Calcoliamo anche il momento secondo:
$$
\mathbb{E}[X^2] = \sum_{k=0}^\infty k^2e^{ -\lambda } \frac{\lambda^k}{k!} = \lambda \sum_{k=1}^\infty \frac{ke^{ -\lambda }\lambda^{k-1}}{(k-1)!}
$$
$$
= \lambda \sum_{j=0}^\infty(j+1)e^{ -\lambda } \frac{\lambda^j}{j!}
$$
$$
= \lambda\left( \underbrace{ \sum_{j=0}^\infty je^{ -\lambda } \frac{\lambda^j}{j!} }_{ \text{vedi sopra} } + e^{ -\lambda }\cancelto{ e^\lambda }{ \sum_{j=0}^\infty \frac{\lambda^j}{j!} } \right)
$$
$$
=\lambda(\lambda+1)
$$
Possiamo ora calcolare la varianza:
$$
\text{Var}(X) = \mathbb{E}[X^2] - \mathbb{E}[X]^2 = \lambda(\lambda+1)-\lambda^2 =\lambda
$$
>[!prop] MEDIA E VARIANZA DI VARIABILI DI POISSON
>$$ \mathbb{E}[X] = \lambda $$
>$$ \text{Var}(X) = \lambda $$

Vale inoltre la seguente proposizione:
>[!prop] SOMMA DI VAR. DI POISSON
>Siano $X\sim\text{Po}(\lambda)$ e $Y\sim\text{Po}(\mu)$ *indipendenti*.
>Allora:
>$$ X+Y \sim \text{Po}(\lambda+\mu) $$
## PROCESSI DI POISSON
>[!def] PROCESSO DI POISSON
>Un processo di Poisson di *intensità* $\lambda>0$, indicato con $\text{PPo}(\lambda)$, è una *famiglia di v.a.* $\{ X_{t} \}_{t>0}$ tale che:
>1. $\forall t>0$ si ha $X_{t}\sim\text{Po}(\lambda t)$
>2. $\forall t>0$ ed $s>0$ si ha $X_{t+s}-X_{t}\sim\text{Po}(\lambda s)$
>3. $\forall 0<t_{0}<t_{1}<\dots<t_{n}$ le variabili
>   $$ X_{t_{1}} - X_{t_{0}}, X_{t_{2}} - X_{t_{1}}, \dots, X_{t_{n}} - X_{t_{n-1}} $$
>   sono *indipendenti*.
### ESEMPIO
Il numero di messaggi ricevuti da un server segue un $\text{PPo}(\lambda)$ con $\lambda=24$ messaggi all'ora.
Qual è la probabilità che nei prossimi $5$ minuti *non* arrivino messaggi?
Abbiamo $X_{t}=$n° messaggi ricevuti entro il minuto $t$.

Allora:
$$
X_{5} = X_{5} - X_{0} \sim\text{Po}\left( 5\cdot \frac{24}{60} \right) = \text{Po}(2)
$$
Per cui:
$$
P(X_{5}=0) = e^{ -2 } \frac{2^0}{0!} = e^{ -2 }
$$
Supponiamo ora che il server non riceva messaggi nei primi $10$ minuti.
Qual è la probabilità che ne riceva $2$ tra i minuti $10$ e $15$?
$$
P^* = P(X_{15}-X_{10}=2|X_{10}=0) = P(X_{15}-X_{10}=2)
$$
(Dato che gli eventi sono indipendenti)
Inoltre, come prima $X_{15}-X_{10}\sim\text{Po}(2)$, per cui:
$$
P^* = P(\text{Po}(2)=2) = e^{ -2 } \frac{2^2}{2!} = 2e^{ -2 }
$$
