# INDICE SEZIONE
- [ ] [[#VARIABILI UNIFORMI]]
- [ ] [[#VARIABILI ESPONENZIALI]]
- [ ] [[#VARIABILI GAMMA]]
- [ ] [[#PROCESSI DI POISSON]]
- [ ] [[#VARIABILI NORMALI]]
# VARIABILI UNIFORMI
>[!def] VARIABILE UNIFORME
>Una v.a. $X$ è detta *uniforme* sull'*intervallo* $(0,1)$ se la sua *densità* è data da
>$$ f_{X}(x) = \begin{cases} 1 & x \in(0,1) \\ 0 & x\not\in(0,1) \end{cases} $$
>E si scrive $X\sim \text{U}(0,1)$.

Chiaramente, $\forall a,b\in[0,1]$, si ha:
$$
P(X\in[a,b]) = \int_{a}^bdx = b-a
$$
Si può *generalizzare* la definizione di variabile uniforme per **qualsiasi intervallo**:
>[!def] VARIABILI UNIFORMI (GENERALIZZAZIONE)
>Una v.a. $X$ si dice uniforme sull'*intervallo* $(a,b)$ con $a,b\in \mathbb{R}$ se la sua densità è data da
>$$ f_{X}(x) = \begin{cases} \frac{1}{b-a} & x \in(a,b) \\ 0 & x\not\in(a,b) \end{cases} $$
>E si scrive $X\sim\text{U}(a,b)$.

```tikz
\usepackage{pgfplots}
\begin{document}

\begin{tikzpicture}
    \begin{axis}[axis equal=false,grid=major]
	    \addplot [red,very thick] expression [domain=1.1:2.9,samples=100]{1/2};
		\addplot [red,very thick] expression [domain=-2:1,samples=100]{0};
		\addplot [red,very thick] expression [domain=3:5,samples=100]{0};
		
		\addplot[mark = o,very thick, red] coordinates {(1,0.5)};
		\addplot[mark = o,very thick, red] coordinates {(3,0.5)};
	\end{axis}
\end{tikzpicture}
 
\end{document}
```

Vediamo in figura un esempio di $X\sim\text{U}(1,3)$.

>[!important] NOTA
>Le variabili uniformi sono utilizzate per descrivere fenomeni che avvengono in *modo uniforme* entro un *certo intervallo*: sappiamo che si verificano *solo al suo interno*, ma non vi sono poi "*sotto-intervalli privilegiati*". 
## CARATTERISTICHE
Dalla definizione otteniamo la *funzione di ripartizione*:
$$
F_{X}(x) = \begin{cases}
0 & x\leq a \\
\frac{x-a}{b-a} & x \in(a,b) \\
1 & x\geq b 
\end{cases}
$$

```tikz
\usepackage{pgfplots}
\begin{document}

\begin{tikzpicture}
    \begin{axis}[axis equal=false,grid=major]
	    \addplot [red,very thick] expression [domain=1:3,samples=100]{(\x - 1)/2};
		\addplot [red,very thick] expression [domain=-2:1,samples=100]{0};
		\addplot [red,very thick] expression [domain=3:5,samples=100]{1};
		
	\end{axis}
\end{tikzpicture}
 
\end{document}
```

Calcoliamo la media:
$$
\mathbb{E}[X] = \int_{\mathbb{R}}xf_{X}(x)dx = \int_{a}^b x \frac{1}{b-a}dx
$$
$$
= \frac{1}{b-a}\int_{a}^bxdx = \frac{1}{b-a}\left[ \frac{x^2}{2} \right]_{a}^b
$$
$$
= \frac{a+b}{2}
$$
E calcoliamo anche la varianza:
$$
\text{Var}(X) = \mathbb{E}[X^2] - \mathbb{E}[X]^2
$$
$$
= \int_{\mathbb{R}}x^2f_{X}(x)dx - \left( \frac{a+b}{2} \right)^2
$$
$$
=\frac{1}{b-a}\int_{a}^b x^2dx - \left( \frac{a+b}{2} \right)^2 = \frac{1}{b-a}\left[ \frac{x^3}{3} \right]_{a}^b - \left( \frac{a+b}{2} \right)^2 
$$
$$
= \frac{b^3-a^3}{3(b-a)} - \left( \frac{a+b}{2} \right)^2
$$
$$
= \frac{(b-a)^2}{12}
$$
>[!prop] MEDIA E VARIANZA DI V.A. CONTINUE
>$$ \mathbb{E}[X] = \frac{a+b}{2} $$
>$$ \text{Var}(X) = \frac{(b-a)^2}{12} $$
## ESEMPIO
Supponiamo che degli autobus arrivino alla fermata ad intervalli regolari di 15 minuti esatti, a partire dalle 7 di mattina: 7, 7:15, 7:30, 7:45 ...
Se una persona arriva alla fermata in un istante uniformemente distribuito tra le 7 e le 7:30, calcoliamo la probabilità che aspetti l'autobus:
1. Meno di 5 minuti.
2. Più di 10 minuti.

Poniamo $X=$ minuto dopo le 7 al quale la persona arriva alla fermata. Abbiamo:
$$
X\sim\text{U}(0,30)
$$
La prima richiesta si calcola come segue:
$$
P(1^*) = P(10<X<15) + P(25<X<30)
$$
$$
= \int_{10}^{15} \frac{1}{30}dx + \int_{25}^{30} \frac{1}{30}dx
$$
$$
= \frac{5}{30} + \frac{5}{30} = \frac{1}{3}
$$
# VARIABILI ESPONENZIALI
>[!def] VARIABILE ESPONENZIALE
>Una v.a. $X$ è detta *esponenziale* di *parametro* $\lambda>0$ se la sua *densità* è data da
>$$ f_{X}(x) = \begin{cases} \lambda e^{ -\lambda x } & x\geq 0 \\ 0 & x<0 \end{cases} $$
>E si scrive $X\sim\text{Exp}(\lambda)$.

```tikz
\usepackage{pgfplots}
\begin{document}

\begin{tikzpicture}[scale = 1.4]
    \begin{axis}[axis equal=false,grid=major]
	    \addplot [orange,very thick] expression [domain=0:6,samples=100]{0.5*exp(-0.5*\x)};
		    \addlegendentry{$\lambda=0.5$};
			
		\addplot [cyan,very thick] expression [domain=0:6,samples=100]{exp(-\x)};
			\addlegendentry{$\lambda = 1$}
			
		\addplot [orange, very thick] expression [domain=-2:-0.1,samples=100]{0};
		\addplot[mark = o, very thick, orange] coordinates {(0,0)};
		\addplot[mark = *, orange] coordinates {(0,0.5)};
		\addplot[mark = *, cyan] coordinates {(0,1)};
			
	\end{axis}
\end{tikzpicture}
 
\end{document}
```

>[!important] NOTA
>Le variabili esponenziali sono utilizzate per modellare il *tempo trascorso tra due avvenimenti*.
>Per questo è utile pensare a $\lambda$ come al **tasso medio** con cui si verificano gli eventi.
>Un esempio di tale distribuzione è il *decadimento radioattivo*: si sa che avviene con un *tasso costante* che dipende dall'emivita dell'isotopo, e la soluzione dell'EDO associata è proprio un'esponenziale. 
## CARATTERISTICHE
Dalla definizione otteniamo la funzione di ripartizione:
Se $x\geq 0$
$$
F_{X}(x) = P(X\leq x) = \int_{0}^x \lambda e^{ -\lambda t }dt = -e^{ -\lambda t }\bigg|_{0}^x = 1-e^{ -\lambda x }
$$
Se $x<0$, invece, abbiamo banalmente $F_{X}(x)=0$.

```tikz
\usepackage{pgfplots}
\begin{document}

\begin{tikzpicture}[scale = 1.4]
    \begin{axis}[axis equal=false,grid=major]
	    \addplot [red,very thick] 
	    expression [domain=0:8.1,samples=100] {1-exp(-\x)};
			
		\addplot [red, very thick] expression [domain=-4.1:0,samples=100]{0};
	\end{axis}
\end{tikzpicture}
 
\end{document}
```

Notiamo inoltre che, per $x\geq 0$:
$$
P(X>x) = 1 - F_{X}(x) = 1 - (1-e^{ -\lambda x }) = e^{ -\lambda x }
$$
Fissiamo ora $s,t>0$ e calcoliamo:
$$
P(X>t+s|X>s) = \frac{P(X>t+s,X>s)}{P(X>s)}
$$
$$
\frac{P(X>t+s)}{P(X>s)} = \frac{e^{ -\lambda(t+s) }}{e^{ -\lambda s }} = e^{ -\lambda t } = P(X>t)
$$
>[!prop] V.A. ESPONENZIALI: ASSENZA DI MEMORIA
>Fissati $s,t>0$, si ha:
>$$ P(X>t+s|X>s) = P(X>t) $$

Cioè si considera solo ciò che succede nell'intervallo, *perdendo memoria* di ciò che è successo per $x<s$.

Calcoliamo ora la media:
$$
\mathbb{E}[X] = \int_{\mathbb{R}}xf_{X}(x)dx = \int_{0}^\infty\lambda xe^{ -\lambda x }dx
$$
$$
= \cancelto{ 0 }{ -xe^{ -\lambda x }\bigg|_{0}^{\infty} } + \int_{0}^{\infty}e^{ -\lambda x }dx
$$
$$
=- \frac{e^{ -\lambda x }}{\lambda}\bigg|_{0}^{\infty} = \frac{1}{\lambda}
$$
Calcoliamo il momento secondo:
$$
\mathbb{E}[X^2] = \int_{\mathbb{R}}x^2f_{X}(x)dx = \int_{0}^{\infty}x^2\lambda e^{ -\lambda x }dx
$$
$$
\cancelto{ 0 }{ -x^2e^{ -\lambda x }\bigg|_{0}^{\infty} } + \int_{0}^{\infty}2xe^{ -\lambda x }dx
$$
$$
= \frac{2}{\lambda}\int_{0}^{\infty} \lambda xe^{ -\lambda x }dx = \frac{2}{\lambda} \frac{1}{\lambda} = \frac{2}{\lambda^2}
$$
Possiamo quindi calcolare la varianza:
$$
\text{Var}(X) = \mathbb{E}[X^2] - \mathbb{E}[X]^2
$$
$$
=\frac{2}{\lambda^2} - \frac{1}{\lambda^2} = \frac{1}{\lambda^2}
$$
>[!prop] MEDIA E VARIANZA DI V.A. ESPONENZIALI
>$$ \mathbb{E}[X] = \frac{1}{\lambda} $$
>$$ \text{Var}(X) = \frac{1}{\lambda^2} $$
## ESEMPIO
Sia $X$ il numero di km che un'auto può percorrere prima che la batteria ceda.
Possiamo modellare $X$ come *esponenziale* con media $10000$ (ricordiamo che $P(X>x)=e^{ -\lambda x }$, per cui ha senso scegliere questo tipo variabile).

Calcoliamo la probabilità di poter fare un viaggio di $5000$ km senza cambiare batteria.
$$
X\sim\text{Exp}(\lambda)
$$
con $\frac{1}{\lambda}=10000\implies\lambda=\frac{1}{10000}$.
Sia $t$ il numero di km percorsi prima di partire.
Siccome abbiamo visto che per le variabili esponenziali vi è *assenza di memoria*:
$$
P(X>t+5000|X>t) = P(X>5000) = e^{ -5000/10000 } = e^{ -1/2 }
$$
>[!important] NOTA
>Se $X$ **non** fosse stata *esponenziale*, *non* avremmo potuto sfruttare l'assenza di memoria, e quindi sarebbe stato *necessario conoscere $t$*.
# VARIABILI GAMMA
>[!def] VARIABILE GAMMA
>Una v.a. $X$ è detta di tipo *gamma* di *parametri* $\alpha,\lambda>0$ se la sua *densità* è data da
>$$ f_{X}(x) = \begin{cases} \frac{\lambda e^{ -\lambda x }(\lambda x)^{\alpha-1}}{\Gamma(\alpha)} & x\geq 0 \\ \\ 0 & x<0 \end{cases} $$
>E si scrive $X\sim\Gamma(\alpha,\lambda)$.

Dove $\Gamma(\alpha)$ è la *funzione gamma*:
$$
\Gamma(\alpha) = \int_{0}^{\infty} t^{\alpha-1}e^{ -t }dt
$$
Consideriamo il seguente fatto relativo a tale funzione:
$$
\Gamma(\alpha) = (\alpha-1)\Gamma(\alpha-1)
$$
Allora, per $n\in \mathbb{N}$ otteniamo:
$$
\Gamma(n) = (n-1)\Gamma(n-1)
$$
$$
= (n-1)(n-2)\Gamma(n-2) = (n-1)(n-2)\dots 3\cdot2\cdot\Gamma(1)
$$
E' facile verificare che $\Gamma(1)=1$, e quindi troviamo:
$$
\Gamma(n) = (n-1)!
$$

```tikz
\usepackage{pgfplots}
\begin{document}

\begin{tikzpicture}[scale = 1.4]
    \begin{axis}[axis equal=false,grid=major]
	    \addplot [orange,very thick] expression [domain=0:20,samples=300]
	    {exp(-\x)*(\x^(1))/(1!)};
		    \addlegendentry{$\alpha=2$};
			
		\addplot [cyan,very thick] expression [domain=0:20,samples=300]
		{exp(-\x)*(\x^(4))/(4!)};
			\addlegendentry{$\alpha = 5$}
			
		\addplot [green,very thick] expression [domain=0:20,samples=300]
		{exp(-\x)*(\x^(10))/(10!)};
			\addlegendentry{$\alpha = 11$}
			
	\end{axis}
\end{tikzpicture}
 
\end{document}
```
## LEGAME CON LE VARIABILI ESPONENZIALI
Sia $Y\sim\text{Exp}(\lambda)$. Abbiamo quindi:
$$
f_{Y}(x) = \lambda e^{ -\lambda x } = \lambda e^{ -\lambda x } \underbrace{ \frac{(\lambda x)^0}{\Gamma(1)} }_{ =1 } \sim \Gamma(1, \lambda)
$$
Consideriamo ora $n$ variabili esponenziali di *parametro* $\lambda$ **indipendenti** $Y_{1},Y_{2},\dots,Y_{n}$ e sia $X_{n}=\sum_{i=0}^n Y_{i} =\sum_{i=0}^n\Gamma(1,\lambda)$. 
Si può dimostrare che vale anche:
$$
X_{n} \sim \Gamma(n,\lambda)
$$
>[!prop] PROPOSIZIONE
>La somma di $n$ v.a. *esponenziali indipendenti* ha distribuzione *gamma*.

Tale legame ci permette di calcolare facilmente *media* e *varianza* di variabili gamma:
>[!prop] MEDIA E VARIANZA DI V.A. GAMMA
>$$ \mathbb{E}[X] = \frac{n}{\lambda} $$
>$$ \text{Var}(X) = \frac{n}{\lambda^2} $$

Si trova inoltre che vale:
$$
F_{X}(x) = 1 -e^{ -\lambda x } \sum_{k=0}^{n-1} \frac{(\lambda x)^k}{k!}
$$
# PROCESSI DI POISSON 
Avevamo già accennato delle nozioni di base sui processi di Poisson in > [[P2 - TIPI DI VARIABILI DISCRETE#PROCESSI DI POISSON]].
Ora che abbiamo definito le variabili continue possiamo affrontarli con un livello di dettaglio maggiore.

>[!def] PROCESSO DI POISSON
>Una *famiglia* di v.a. $\{ N(t),t\geq 0 \}$ è detta **processo di Poisson** di *intensità* $\lambda>0$ se:
>1. $N(0)=0$
>2. Per ogni $0<t_{0}<t_{1}<\dots<t_{n}$ le variabili
>   $$ N(t_{1})-N(t_{0}), N(t_{2})-N(t_{1}),\dots,N(t_{n})-N(t_{n-1}) $$
>   sono *indipendenti*.
>3. $\forall t,s>0$ $\exists \rho$ tale che $N(t+s)-N(t)=\rho(s)$.
>4. $P(N(h)=1)=\lambda h+o(h)$
>5. $P(N(h)\geq 2)=o(h)$

>[!important] OSSERVAZIONI
>- La condizione (2) impone che i *numeri di eventi* che si verificano in *intervalli di tempo disgiunti* siano *indipendenti*.
>- La condizione (3) impone che la *distribuzione del numero di eventi* in un dato intervallo di tempo dipenda solo dalla *lunghezza dell'intervallo* e *non dalla sua posizione* (*stazionarietà degli incrementi*).
>- Le condizioni (4) e (5) impongono che il numero di eventi che si verificano in un *breve* intervallo di tempo è, in buona approssimazione, $0$ oppure $1$. La probabilità che si verifichino $2$ o più eventi è *trascurabile*.

In particolare, si ha: 
- $N(t)\sim\text{Po}(\lambda t)$  
- $N(t+s)-N(t)\sim\text{Po}(\lambda s)$.

Cioè il *numero di eventi* che si verificano in $[0,t]$ segue una *distribuzione di Poisson*.

Inoltre, se $T_{1}$ è l'istante in cui si verifica il primo evento e, per $i>1$ $T_{i}$, è il *tempo trascorso* tra l'$i-1$ esimo e l'$i$ esimo evento, la successione seguente
  $$ T_{1},T_{2},\dots $$
è detta **successione dei tempi di interarrivo**.
Siccome stiamo osservando il tempo che *trascorre fra due eventi*, abbiamo:
$$
T_{i} \sim \text{Exp}(\lambda)
$$

Consideriamo ora gli *istanti in cui si verificano gli eventi*, dati da:
$$
S_{n} = \sum_{i=1}^n T_{i}
$$
Abbiamo $S_{1}=T_{1}$, $S_{2}=T_{1}+T_{2}$ è l'istante in cui si verifica il secondo evento, e così via.
>[!important] NOTA
>$$ S_{n}\leq t \Longleftrightarrow N(t)\geq n $$
>Cioè l'$n$-esimo evento si verifica *entro l'istante* $t$ **se e solo se** $n$ eventi si sono verificati in $[0,t]$.

Per quanto visto in > [[#LEGAME CON LE VARIABILI ESPONENZIALI]] abbiamo:
$$
S_{n} =\sum_{i=1}^n T_{i} = \Gamma(n,\lambda)
$$
Infine, se assumiamo di *conoscere* il numero di eventi $N(t)=n$ avvenuti fino all'istante $t$, gli istanti in cui si verificano gli $n$ eventi sono *distribuiti uniformemente* come $n$ punti scelti a caso in $[0,t]$:
$$
(S_{1},S_{2},\dots,S_{n})|N(t) = n \sim t(U_{(1)},U_{(2)},\dots,U_{(n)})
$$
Dove $U_{(i)}$ sono variabili $\text{U}(0,t)$ *ordinate* (perchè gli eventi avvengono secondo un certo ordinamento durante il processo).

>[!tldr] CONCETTI CHIAVE
>1. La *distribuzione* dei **tempi di interarrivo** è **esponenziale**.
>2. Senza *nessun vincolo sull'ampiezza* di un intervallo, gli **istanti** *in cui si verificano gli eventi* seguono una **distribuzione gamma** (in quanto somma di esponenziali).
>3. Se ci *limitiamo* ad un intervallo $[0,t]$ e *conosciamo il numero di avvenimenti* $n$ (condizione: sappiamo che $N(t)=n$), allora gli **istanti** in cui gli eventi si verificano sono **distribuiti uniformemente** in $[0,t]$.
## ESEMPIO
Sia $\{ N(t),t\geq 0 \}$ un processo di Poisson di *intensità* $\lambda=3$.

**1)** Se non è ancora avvenuto il primo evento entro il tempo $t=\frac{2}{3}$, qual è la probabilità che avvenga dopo $t=1$?

Per la proprietà di assenza di memoria dell'esponenziale:
$$
P\left( T_{1}>1\bigg|T_{1}> \frac{2}{3} \right) = P\left( T_{1}> 1-\frac{2}{3} \right) = P\left( T_{1}> \frac{1}{3} \right)
$$
$$
= e^{ -\lambda/3 } = e^{ -1 } \approx 0.37 = 37\%
$$
**2)** Calcolare la probabilità che in $(1,2)$ avvengano *esattamente* 2 eventi e in $(3,5)$ ne avvenga *esattamente* 1.

Gli intervalli sono disgiunti, per cui gli eventi sono indipendenti:
- $N(2)-N(1)\sim\text{Po}(1\lambda)=\text{Po}(3)$
- $N(5)-N(3)\sim\text{Po}(2\lambda)=\text{Po}(6)$

Per cui:
$$
P(N(2)-N(1)=2, N(5)-N(3)=1) = P(\text{Po}(3)=2)P(\text{Po}(6)=1)
$$
$$
= e^{ -3 } \frac{3^2}{2!}\cdot e^{ -6 } \frac{6^1}{1!} = 27e^{ -9 }
$$
$$
\approx 0.003 = 0.3\%
$$
**3)** Calcolare la probabilità che il terzo evento si verifichi prima di $t=1$.

Abbiamo $S_{3}\sim\Gamma(3,3)$ con distribuzione:
$$
F_{S_{3}}(t) = 1- e^{ -3 } \sum_{k=0}^2 \frac{(3t)^k}{k!} 
$$
Quindi troviamo:
$$
P(S_{3}<1) = 1-e^{ -3 }\left( 1+3+ \frac{9}{2} \right) = 1 - \frac{17}{2}e^{ -3 }\approx 0.58
$$
Equivalentemente, possiamo determinare la probabilità che in $[0,1]$ si siano verificati almeno 3 eventi: $N(1) \sim\text{Po}(3)$ con
$$
F_{N(1)}(n) = \sum_{k=0}^n e^{ -3 } \frac{3^k}{k!}
$$
E quindi:
$$
P(N(1)\geq 3) = 1-P(N(1)\leq 2) 
$$
$$
= 1- \sum_{k=0}^2e^{ -3 } \frac{3^k}{k!} = 1-e^{ -3 }\left( 1+3+\frac{9}{2} \right) \approx 0.58
$$
**4)** Sapendo che $N(3)=4$, calcolare la probabilità che almeno 2 eventi accadano nell'intervallo $[1,2]$.

Sappiamo che i 4 eventi sono uniformemente distribuiti in $[0,3]$, per cui la probabilità che *uno* cada in $[1,2]$ è:
$$
p = \int_{1}^2 \frac{1}{3}dx = \frac{1}{3}
$$
Ora, sia $X$ il numero di arrivi in $[1,2]$. Abbiamo $X\sim\text{Bin}\left( 4, \frac{1}{3} \right)$: 4 eventi totali, con probabilità $p=\frac{1}{3}$ che cadano nell'intervallo a cui siamo interessati (probabilità di successo).
Quindi:
$$
P(X\geq 2) = 1- P(X=0)-P(X=1)
$$
$$
= 1- \begin{pmatrix} 4 \\ 0 \end{pmatrix} \left( \frac{2}{3} \right)^4 - \begin{pmatrix} 4 \\ 1 \end{pmatrix} \frac{1}{3} \left( \frac{2}{3} \right)^3
$$
$$
= 1 - \frac{16}{81} - \frac{32}{81} = \frac{11}{27} \approx 0.41 = 41\%
$$
# VARIABILI NORMALI 
Introduciamo infine le *variabili normali*, dette anche **gaussiane**.
>[!def] VARIABILE NORMALE
>Una v.a. $X$ è detta *normale* o *gaussiana* di parametri $\mu$ e $\sigma^2$ se la sua *densità* è data da
>$$ f_{X}(x) = \frac{1}{\sqrt{ 2\pi }\sigma} \exp\left( - \frac{(x-\mu)^2}{2\sigma^2} \right) $$
>E si scrive $X\sim\mathcal{N}(\mu,\sigma^2)$.

Prima di presentarne il grafico, facciamo un breve studio di funzione.
$$
f_{X}'(x) = -\frac{x-\mu}{\sigma^2}\frac{1}{\sqrt{ 2\pi }\sigma}\exp\left( -\frac{(x-\mu)^2}{2\sigma^2} \right)
$$
E notiamo che si ha:
$$
f'_{X}(x) = 0 \Longleftrightarrow x=\mu
$$
Con:
$$
f_{X}(\mu) = \frac{1}{\sqrt{ 2\pi }\sigma}
$$
Cioè abbiamo un punto critico $P$
$$
P = \left( \mu, \frac{1}{\sqrt{ 2\pi }\sigma} \right)
$$
Che è un *punto di massimo* perchè $\lim_{ x \to \pm\infty }f_{X}(x)=0$.
Notiamo anche che $f_{X}(x)$ è simmetrica rispetto alla retta verticale $x=\mu$.

```tikz
\usepackage{pgfplots}
\begin{document}

\begin{tikzpicture}[scale = 1.4]
    \begin{axis}
    [axis equal=false,
     grid=major,
     x = 0.5cm
    ]
	    \addplot [orange,very thick] expression [domain=-8:10,samples=150]
	    {1/((2*pi)^(0.5)*2)*exp( -((\x-0)^2)/(2*2) )};
		    \addlegendentry{$\mu=0$ , $\sigma^2=2$};
		    
		\addplot [green,very thick] expression [domain=-8:10,samples=150]
	    {1/((2*pi*7)^(0.5))*exp( -((\x-2)^2)/(2*7) )};
		    \addlegendentry{$\mu=2$ , $\sigma^2=5$};
		
		\addplot [cyan,very thick] expression [domain=-8:10,samples=150]
	    {1/((2*pi*12)^(0.5))*exp( -((\x)^2)/(2*12) )};
		    \addlegendentry{$\mu=0$ , $\sigma^2=12$};
			
	\end{axis}
\end{tikzpicture}
 
\end{document}
```
## INTEGRALE DI GAUSS
Sappiamo che deve valere
$$
\int_{\mathbb{R}}f_{X}(x)dx = 1
$$
E infatti:
$$
\int_{\mathbb{R}}f_{X}(x)dx = \frac{1}{\sqrt{ 2\pi }\sigma}\int_{\mathbb{R}}\exp\left( - \frac{(x-\mu)^2}{2\sigma^2} \right)dx
$$
Introduciamo il cambio di variabile $y=\frac{x-\mu}{\sigma}$ e otteniamo:
$$
\frac{1}{\sqrt{ 2\pi }}\int_{\mathbb{R}}\exp\left( -\frac{y^2}{2} \right)dy
$$
Consideriamo ora solo l'integrale e chiamiamolo $I$.
Abbiamo:
$$
I^2 = \int_{\mathbb{R}}\exp\left( -\frac{y^2}{2} \right)dy\int_{\mathbb{R}}\exp\left( -\frac{x^2}{2} \right)dx = \int_{\mathbb{R}^2}\exp\left( -\frac{y^2+x^2}{2} \right)dydx
$$
Passiamo ora alle coordinate polari:
$$
I^2 = \int_{0}^\infty \int_{0}^{2\pi}\exp\left( -\frac{r^2}{2} \right)rd\theta dr = 2\pi \int_{0}^\infty r\exp\left( -\frac{r^2}{2} \right)dr
$$
$$
I^2 = -2\pi e^{ -r^2/2 }\bigg|_{0}^\infty = 2\pi
$$
Per cui $I=\sqrt{ 2\pi }$ e concludiamo che:
$$
\int_{\mathbb{R}}f_{X}(x)dx = \frac{1}{\sqrt{ 2\pi }}I = \frac{\sqrt{ 2\pi }}{\sqrt{ 2\pi }} = 1 \text{ , }\forall \mu,\sigma \in \mathbb{R}
$$
## NORMALE STANDARD
>[!def] NORMALE STANDARD
>Una variabile $X$ segue una distribuzione *normale standard* se
>$$ X\sim\mathcal{N}(0,1) $$
>Cioè $\mu=0$ e $\sigma^2=1$.

A partire da una normale standard $Z$ si può *ottenere un'altra normale* $X$ applicando la seguente trasformazione:
$$
X = \sigma Z+\mu
$$
Trovando così $X\sim\mathcal{N}(\mu,\sigma^2)$.
Chiaramente, a partire da una normale non standard possiamo ottenere una standard con la *trasformazione inversa*:
$$
Z = \frac{X-\mu}{\sigma}
$$
Spesso conviene *ricondursi* ad una *normale standard* per semplificare lo studio della variabile.
In tal caso la funzione di densità è data da:
$$
f_{X}(x) = \frac{1}{\sqrt{ 2\pi }}e^{ -x^2/2 }
$$
## CARATTERISTICHE
Studiamo ora una variabile normale *standard* $Z$, ricordando che possiamo ottenere un'altra normale con la trasformazione vista sopra.
Cominciamo calcolando la media:
$$
\mathbb{E}[Z] = \int_{\mathbb{R}}xf_{Z}(x)dx = \frac{1}{\sqrt{ 2\pi }}\int_{\mathbb{R}}x\exp\left( -\frac{x^2}{2} \right)dx
$$
$$
= -\frac{1}{\sqrt{ 2\pi }} e^{ -x^2/2 }\bigg|_{-\infty}^{+\infty} = 0
$$
Calcoliamo anche la varianza:
$$
\text{Var}(Z) = \mathbb{E}[Z^2] - \cancelto{ 0 }{ \mathbb{E}[Z]^2 } = \mathbb{E}[Z^2]
$$
$$
=\frac{1}{\sqrt{ 2\pi }}\int_{\mathbb{R}}x^2\exp\left( -\frac{x^2}{2} \right)
$$
$$
= \frac{1}{\sqrt{ 2\pi }}\left(  \cancelto{ 0 }{ xe^{ -x^2/2 }\bigg|_{-\infty}^{+\infty} } - \int-\exp\left(-\frac{x^2}{2} \right)dx  \right)
$$
$$
= \frac{1}{\sqrt{ 2\pi }}\cancelto{ \sqrt{ 2\pi } }{ \int_{\mathbb{R}}\exp\left( -\frac{x^2}{2} \right)dx } = 1
$$
A questo punto, se $X\sim\mathcal{N}(\mu,\sigma^2)$, abbiamo:
- $\mathbb{E}[X]=\sigma \mathbb{E}[Z]+\mu =\mu$
- $\text{Var}(X)=\sigma^2\text{Var}(Z)=\sigma^2$

>[!prop] MEDIA E VARIANZA DI V.A. NORMALI
>$$ \mathbb{E}[X] = \mu $$
>$$ \text{Var}(X) = \sigma^2 $$

Per cui la trasformazione vista sopra può essere scritta anche come segue:
$$
Z = \frac{X-\mathbb{E}[X]}{\sqrt{ \text{Var}(X) }} \sim\mathcal{N}(0,1)
$$
ed è detta anche **standardizzazione**.

Per quanto riguarda la *funzione di ripartizione*, invece, troviamo:
$$
\phi(x) := F_{Z}(x) = P(Z\leq x) = \int_{-\infty}^x \exp\left( -\frac{t^2}{2} \right)dt
$$
>[!important] NOTA
>Tale funzione *non è integrabile*, per cui si ricorre ad approssimazioni numeriche e valori tabulati.

In ogni caso, un'osservazione utile è la seguente:
$$
\phi(-a) = P(Z\leq-a) = P(Z>a) = 1-\phi(a)
$$
Tale fatto deriva ancora una volta dalla simmetria della funzione $f_{Z}(x)$.
Chiaramente, se $X\sim\mathcal{N}(\mu,\sigma^2)$, si ha:
$$
P(X\leq a) = P(\sigma Z+\mu\leq a) = P\left( Z\leq \frac{a-\mu}{\sigma} \right) = \phi\left( \frac{a-\mu}{\sigma} \right)
$$
Per cui, riassumendo:
>[!prop] PROPOSIZIONE
>Per la funzione di distribuzione $\phi(a):=P(Z\leq a)$ si ha:
>$$ \phi(-a) = 1-\phi(a) $$
>$$ P(X\leq a) = \phi\left(  \frac{a-\mu}{\sigma} \right) $$
## ESEMPIO
Sia $X\sim\mathcal{N}(500,60^2)$ la vita di una lampadina in ore.
**1)** Calcoliamo la probabilità che la lampadina funzioni per più di 560 ore:
$$
P(X>560) = P\left( \frac{X-500}{60} > 1 \right)
$$
$$
= P(Z>1) = 1-\phi(1) = 1-0.8413 = 0.1587
$$
**2)** Calcoliamo la probabilità che la lampadina funzioni per meno di 440 ore:
$$
P(X<440) = P\left( \frac{X-500}{60}< -1 \right)
$$
$$
= P(Z<-1) = \phi(-1) = 1- \phi(1) = 0.1587
$$
**3)** Calcoliamo la probabilità che la lampadina funzioni per più di 560 ore, sapendo che ha funzionato per più di 440:
$$
P(X>560|X>440) = \frac{P(X>560,X>440)}{P(X>440)}
$$
$$
= \frac{P(X>560)}{P(X>440)} = \frac{0.1587}{0.8413} \approx 0.1886
$$
## PROPRIETA'
>[!prop] PROPOSIZIONE: TRASFORMAZIONI LINEARI
>Le trasformazioni lineari mandano *normali* in *normali*.

Se $X\sim\mathcal{N}(\mu,\sigma^2)$ e $Y=aX+b$ si ha:
$$
Y\sim\mathcal{N}(a\mu+b,a^2\sigma^2)
$$
E si trova:
- $\mathbb{E}[Y]=\mathbb{E}[aX+b]=a\mathbb{E}[X]+b=a\mu+b$
- $\text{Var}(Y)=\text{Var}(aX+b)=a^2\text{Var(X)}=a^2\sigma^2$

>[!important] OSSERVAZIONE
>Se $a=0$ si ha $Y$ *costante*, ma può essere vista anche come una *normale con varianza nulla* $Y\sim\mathcal{N}(b,0)$.

>[!prop] PROPOSIZIONE: COMBINAZIONI LINEARI
>La combinazione lineare di *normali indipendenti* è ancora una *normale*.

Siano $X_{k}\sim\mathcal{N}(\mu_{k},\sigma^2_{k})$ *indipendenti* per $k=1,\dots,n$. 
Sia inoltre $Y=\alpha_{1}X_{1}+\dots+\alpha_{n}X_{n}$ con $\alpha_{1},\dots,\alpha_{n}\in \mathbb{R}$.
Allora:
$$
Y\sim\mathcal{N}\left( \sum_{k=1}^n \alpha_{k}\mu_{k}, \sum_{k=1}^n (\alpha_{k}\sigma_{k})^2 \right)
$$
