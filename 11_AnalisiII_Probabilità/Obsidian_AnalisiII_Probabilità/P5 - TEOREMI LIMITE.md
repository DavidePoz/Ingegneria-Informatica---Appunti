# INDICE SEZIONE
- [ ] [[#INTRODUZIONE APPROSSIMAZIONE DELLA BINOMIALE]]
- [ ] [[#MEDIA CAMPIONARIA]]
- [ ] [[#TEOREMA DEL LIMITE CENTRALE]]
- [ ] [[#TEOREMI E DISUGUAGLIANZE]]
# INTRODUZIONE: APPROSSIMAZIONE DELLA BINOMIALE
Possiamo approssimare una *binomiale* come segue quando $n$ è *sufficientemente grande*.
$$
\text{Bin}(n,p) \approx \mathcal{N}(np, np(1-p))
$$
Abbiamo:
- $\mathbb{E}[X]=np$
- $\text{Var}(X)=np(1-p)$

Se *standardizziamo* una binomiale, allora la sua funzione di distribuzione *converge* a quella di una *normale standard* $Z\sim\mathcal{N}(0,1)$. 
Per cui:
$$
P\left(  \frac{X-np}{\sqrt{ np(1-p) }} \leq x \right) \underset{ n\to \infty }{ \longrightarrow } P(Z\leq x) = \phi(x)
$$

>[!important] NOTA
>Avevamo già visto che si possono approssimare le binomiali con le variabili di *Poisson*.
>Abbiamo quindi due possibilità:
>- **Poisson**: se $n$ grande e $p$ piccolo.
>- **Normale**: se $np(1-p)$ grande (in pratica, $\geq 10$).
## ESEMPIO
Lanciamo 100 monete regolari.
Sia $X$ il numero di teste.
Vediamo come stimare la *probabilità che escano almeno 55 teste*.
$$
\begin{matrix}
X\sim\text{Bin}\left( 100, \frac{1}{2} \right) & \longrightarrow & \mathbb{E}[X] = 100\cdot \frac{1}{2} = 50 \\
 &  &  \text{Var}(X) = 100\cdot \frac{1}{2} \cdot \frac{1}{2} = 25
\end{matrix}
$$
Approssimiamola quindi come segue:
$$
X\approx Y\sim\mathcal{N}(50,25)
$$
Si presenta però il seguente problema:
$$
P(X\geq 55) = P(X>54.5) = P(X>54)
$$
Del resto, tuttavia:
$$
P(Y\geq 55) \ne P(X> 54.5) \ne P(X>54)
$$
Viene quindi naturale chiedersi *quale usare*.
Per convenzione, si applica la **correzione di continuità**:
$$
P(X\geq 55) \approx P(Y> 54.5)
$$
Quindi
$$
P(X\geq 55) \approx P(Y> 54.5) = 1-P(Y\leq 54.5)
$$
$$
= 1 - P\left(  \frac{Y-50}{\sqrt{ 25 }} \leq \frac{54.5-50}{\sqrt{ 25 }} \right)
$$
$$
= 1 - \phi(0.9) = 1-0.8159 = 0.1841 \approx 18.4\%
$$
# MEDIA CAMPIONARIA
Consideriamo un *campione* di $n$ *osservazioni* rappresentate da v.a. **indipendenti ed identicamente distribuite** (i.i.d.) $X_{1},\dots,X_{n}$.
Definiamo allora la media campionaria:
>[!def] MEDIA CAMPIONARIA
>$$ \overline{X_{n}} = \frac{X_{1}+\dots+X_{n}}{n} = \frac{1}{n} \sum_{i=1}^nX_{i} $$

Chiaramente, all'aumentare di $n$ otteniamo una *stima più precisa* della media.
Supponiamo $\mathbb{E}[X_{i}]=\mu$ e $\text{Var}(X_{i})=\sigma^2$ $\forall i=1,\dots,n$.

Calcoliamo ora *valore atteso* e *varianza* per $\overline{X}_{n}$.
$$
\mathbb{E}[\overline{X}_{n}] = \mathbb{E}\left[ \frac{1}{n}\sum_{i=1}^nX_{i} \right] = \frac{1}{n}(n\mu) = \mu
$$
$$
\text{Var}(\overline{X}_{n}) = \text{Var}\left( \frac{1}{n}\sum_{i=1}^nX_{i} \right) = \frac{1}{n^2}\sum_{i=1}^n \text{Var}(X_{i}) = \frac{1}{n^2}(n\sigma^2) = \frac{\sigma^2}{n}
$$
>[!important] NOTA
>Quindi la *media campionaria* ha lo **stesso valore atteso** delle *singole variabili*, mentre la **varianza diminuisce** all'*aumentare di* $n$.
# TEOREMA DEL LIMITE CENTRALE
Vediamo a questo punto come generalizzare l'approssimazione vista in > [[#INTRODUZIONE APPROSSIMAZIONE DELLA BINOMIALE]].
>[!theorem] TLC (CLT in inglese)
>Siano $X_{1},\dots,X_{n}$ v.a. i.i.d. con media $\mu$ e varianza $\sigma^2$.
>$$ \frac{\overline{X}_{n}-\mu}{\frac{\sigma}{\sqrt{ n }}} \underset{ n\to \infty }{ \longrightarrow } Z\sim\mathcal{N}(0,1) $$

Per cui, $\forall a\in \mathbb{R}$ si ha:
$$  
P\left(  \frac{\overline{X}_{n}-\mu}{\frac{\sigma}{\sqrt{ n }}} \leq a \right) \underset{ n\to \infty }{ \longrightarrow } P(Z\leq a) = \phi(a)
$$
Che facilita notevolmente lo studio delle variabili.

Notiamo inoltre, *standardizzando la media campionaria* ($\mu_{m}=\mu$ e $\sigma_{m}=\frac{\sigma}{\sqrt{ n }}$):
$$
\frac{\overline{X}_{n}-\mu}{\frac{\sigma}{\sqrt{ n }}} = \frac{\frac{1}{n}\sum_{i=1}^nX_{i}-\mu}{\sigma \sqrt{ n }}\cdot \frac{n}{n}
$$
$$
= \frac{\sum_{i=1}^n X_{i}-n\mu}{\sigma \sqrt{ n }}
$$
Ora, chiamando $S_{n}:=\sum_{i=1}^nX_{i}$ abbiamo:
- $n\mu = \mathbb{E}[S_{n}]$
- $\sigma \sqrt{ n }=\sqrt{ \text{Var}(S_{n}) }$

Concludiamo quindi:
$$
Z = \frac{S_{n}-\mathbb{E}[S_{n}]}{\sqrt{ \text{Var}(S_{n}) }}
$$
Ed era $Z\sim\mathcal{N}(0,1)$. Per cui $S_{n}$ deve essere una *variabile normale*.
>[!important] NOTA
>Quindi possiamo *stimare* la *distribuzione della somma* $S_{n}$ di $n$ variabili i.i.d. quando $n$ è sufficientemente grande:
$$ S_{n} = \sum_{i=1}^nX_{i} \underset{ n\to \infty }{ \longrightarrow }\mathcal{N}(n\mu,n\sigma^2) $$
## CONSEGUENZE
Sappiamo che una variabile $\text{Bin}(n,p)$ è somma di $n$ $\text{Be}(p)$ indipendenti (i.i.d.).
Allora, per $n$ grande:
$$
\text{Bin}(n,p) \approx \mathcal{N}(np,np(1-p))
$$
E abbiamo così giustificato l'approssimazione vista in > [[#INTRODUZIONE APPROSSIMAZIONE DELLA BINOMIALE]].

Sappiamo anche che una variabile $\text{Po}(\lambda)$ può essere approssimata con una $\text{Bin}\left( n, \frac{\lambda}{n} \right)$ per $n$ grande e $p$ piccolo, la quale abbiamo visto essere somma di $n$ $\text{Be}\left( \frac{\lambda}{n} \right)$.
Per cui troviamo:
$$
\text{Po}(\lambda) \approx \mathcal{N}(\lambda,\lambda)
$$
## ESEMPI
**1)** Il numero di studenti che si iscrivono ad un corso di laurea è descritto da una Poisson di media $100$.
Se il numero di studenti iscritti è almeno $120$, il corso viene suddiviso in due canali.
Calcoliamo la probabilità di avere due canali:
$$
X\sim\text{Po}(100) \Longrightarrow \mathbb{E}[X] = \text{Var}(X) = 100
$$
La soluzione esatta sarebbe data da:
$$
P(X\geq 120) = \sum_{k=120}^\infty P(X=k) = e^{ -100 }\sum_{k=120}^\infty \frac{100^k}{k!}
$$
Chiaramente, però, risulta difficile da calcolare.

Usiamo allora il *Teorema del Limite Centrale*, sfruttando il fatto che $\text{Po}(100)=\sum_{i=1}^{100}\text{Po}(1)$.
Troviamo quindi:
$$
X \approx Y \sim\mathcal{N}(100\cdot 1,100\cdot 1) = \mathcal{N}(100,100)
$$
Il calcolo risulta ora più semplice, infatti:
$$
P(X\geq 120) \approx P(Y\geq 119.5) = P\left(  \frac{Y-100}{\sqrt{ 100 }} \geq \frac{119.5-100}{\sqrt{ 100 }} \right)
$$
$$
= 1 - \phi(1.95) = 1-0.9744 
$$
$$
= 0.0256 \approx 2.6\%
$$

Consideriamo anche l'esempio seguente.
**2)** Siano $X_{1},\dots,X_{n}\sim\text{Exp}(1)$ indipendenti e sia $S$ la loro somma. Sappiamo che vale:
$$
S = \sum_{i=1}^{100}X_{i} \sim\Gamma(100,1)
$$
Stimiamo $P(S>105)$ usando il teorema.
Innanzitutto:
$$
\mathbb{E}[X_{i}] = \text{Var}(X_{i}) = 1 \Longrightarrow \mathbb{E}[S] = \text{Var}(S) = 100
$$
Per il TLC:
$$
S \approx Y \sim\mathcal{N}(100,100)
$$
Quindi abbiamo:
$$
P(X>105) \approx P(Y>105)
$$
$$
= P\left( \frac{Y-100}{10} > \frac{105-100}{10} \right)
$$
$$
= 1-\phi(0.5) = 1-0.6915
$$
$$
= 0.3085 \approx 31\%
$$
# TEOREMI E DISUGUAGLIANZE 
## DISUGUAGLIANZA DI MARKOV
>[!theorem] DISUGUAGLIANZA DI MARKOV
>Sia $X$ una v.a. che assume *valori non negativi*.
>Sia inoltre $a>0$. Allora:
>$$ P(X\geq a) \leq \frac{\mathbb{E}[X]}{a} $$

>[!check] DIM.
>Consideriamo la variabile indicatrice $I$ seguente:
>$$ I = \begin{cases} 1 & \text{ se } X\geq a \\ 0 & \text{ altrimenti } \end{cases} $$
>Notiamo che
>$$ I \leq \frac{X}{a} $$
>Applicando la media troviamo quindi
>$$ \mathbb{E}[I] \leq \frac{\mathbb{E}[X]}{a} $$
>Del resto, sappiamo che 
>$$ \mathbb{E}[I] = P(X\geq a) $$
>in quanto variabile indicatrice.
>E concludiamo quindi che:
>$$ P(X\geq a) \leq \frac{\mathbb{E}[X]}{a}$$
>$\square$
## DISUGUAGLIANZA DI CHEBYSHEV
Immaginiamo di essere interessati alla *deviazione di una variabile dalla sua media*:
$$
P(|X-\mu|\geq c)
$$
Se *conosciamo* la variabile, possiamo *calcolarla*:
Poniamo
$$
X\sim\mathcal{N}(\mu,\sigma^2) \text{ , } X = \sigma Z+\mu
$$
con $Z\sim\mathcal{N}(0,1)$.
Allora abbiamo:
$$
P(|X-\mu|\geq c) = P(\sigma|Z|\geq c)
$$
$$
= P\left( |Z|\geq \frac{c}{\sigma} \right) = 1-\left( \phi\left( \frac{c}{\sigma} \right)-\phi\left( -\frac{c}{\sigma} \right) \right)
$$
$$
= 2\left( 1-\phi\left( \frac{c}{\sigma} \right) \right)
$$
Come possiamo fare, invece, se *non conosciamo la variabile*?
Ci viene in aiuto la seguente disuguaglianza:
>[!theorem] DISUGUAGLIANZA DI CHEBYSHEV
>Sia $X$ una v.a. e $c\in \mathbb{R}$, $c>0$. Vale:
>$$ P(|X-\mu|\geq c) \leq \frac{\text{Var}(X)}{c^2} $$

>[!check] DIM.
>Siccome $(X-\mu)^2$ è una v.a. non negativa, possiamo applicare la *disuguaglianza di Markov* con $a=c^2$:
>$$ P((X-\mu)^2 \geq c^2) \leq \frac{\mathbb{E}[(X-\mu)^2]}{c^2} $$
>Ma $(X-\mu)^2\geq c^2 \Longleftrightarrow |X-\mu|\geq c$.
>Inoltre $\mathbb{E}[(X-\mu)^2]=\text{Var}(X)$.
>Concludiamo allora:
>$$ P(|X-\mu|\geq c) \leq \frac{\text{Var}(X)}{c^2} $$
>$\square$
## LEGGE DEI GRANDI NUMERI
Siano $X_{1},\dots,X_{n}$ v.a. i.i.d. con media $\mu$.
Valgono le due leggi seguenti, dette *dei grandi numeri*:
>[!theorem] LEGGE DEBOLE
>$$ \forall \varepsilon>0 \text{ , }P(|\overline{X}_{n}-\mu|>\varepsilon)\underset{ n\to \infty }{ \longrightarrow } 0 $$

>[!theorem] LEGGE FORTE
>$$ P\left(\lim_{ n \to \infty } \overline{X}_{n} = \mu\right) = 1 $$

Dimostriamo la *legge debole*:
>[!check] DIM. Legge Debole
>Supponiamo che $\text{Var}(X_{i})=\sigma^2$ $\forall i$, per un qualche $\sigma$.
>Abbiamo che:
>- $\mathbb{E}[\overline{X}_{n}]=\mu$
>- $\text{Var}(\overline{X}_{n}) = \frac{\sigma^2}{n}$
>
>Applichiamo la *disuguaglianza di Chebyshev* a $\overline{X}_{n}$ con $c=\varepsilon>0$:
>$$ P(|\overline{X}_{n}-\mu|>\varepsilon) \leq \frac{\sigma^2}{n\varepsilon^2} $$
>Ma $\frac{\sigma^2}{n\varepsilon^2}\longrightarrow0$ per $n\to \infty$.
>Concludiamo allora:
>$$ P(|\overline{X}_{n}-\mu|>\varepsilon)\underset{ n\to \infty }{ \longrightarrow } 0 $$
>$\square$

