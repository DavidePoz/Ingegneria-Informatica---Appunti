# INDICE SEZIONE
- [ ] [[#VETTORI E NORMA NELLO SPAZIO EUCLIDEO]]
- [ ] [[#DISCHI, CIRCONFERENZE, PALLE E (IPER)SFERE]]
- [ ] [[#QUADRATI, CUBI E IPERCUBI]]
- [ ] [[#DISUGUAGLIANZA TRIANGOLARE]]
- [ ] [[#INTORNI]]
- [ ] [[#PRODOTTO SCALARE]]
- [ ] [[#COORDINATE POLARI]]
# VETTORI E NORMA NELLO SPAZIO EUCLIDEO
Ricordiamo la definizione di vettore, inteso come elemento di $\mathbb{R}^n$:

>[!def] VETTORE
>Un vettore di $\mathbb{R}^n$, $n\in \mathbb{N}$, è una $n$-tupla:
$$ (x_{1},\dots,x_{n}) = \underline{x} \in \mathbb{R}^n $$
con $x_{1},\dots,x_{n}\in \mathbb{R}$. 

E la sua norma.

>[!def] NORMA
>La **norma** di un vettore $\underline{x}\in\mathbb{R}^n$ è data da: 
>$$ \lvert \lvert \underline{x} \rvert  \rvert = \sqrt{ x_{1}^2+\dots+x_{n}^2 } $$

# DISCHI, CIRCONFERENZE, PALLE E (IPER)SFERE
Sappiamo che in $\mathbb{R}^2$ un *disco* è la regione di piano delimitata da una *circonferenza*.
Similmente, in $\mathbb{R}^3$ una *palla* è la regione di spazio delimitata da una *sfera*.
In spazi di dimensioni maggiori si parla di **ipersfere** (superfici delimitanti) e **palle**.
Diamone una definizione generale:

>[!def] DISCHI e PALLE
>Un disco, o palla, ha un centro $p\in \mathbb{R}^n$ e un raggio $r\in \mathbb{R}$ ed è così definito/a:
>$$ B(\underline{p},r]:=\{ \underline{x}\in \mathbb{R}^n : \lvert \lvert \underline{x}-\underline{p} \rvert  \rvert \leq r \} $$

>[!important] NOTA
>Si parla anche di dischi o palle *aperte*, definite come:
>$$ B(\underline{p},r[:=\{ \underline{x}\in \mathbb{R}^n : \lvert \lvert \underline{x}-\underline{p} \rvert  \rvert < r \} $$

Dischi e palle sono racchiusi da **circonferenze** (in $\mathbb{R}^2$) e **(iper)sfere** (in $\mathbb{R}^n,n>2$).

>[!def] CIRCONFERENZE e (IPER)SFERE
>Di centro $\underline{p}\in \mathbb{R}^n$ e raggio $r\in \mathbb{R}$:
>$$ \partial B(\underline{p},r] := \{ \underline{x}\in \mathbb{R}^n : || \underline{x}-\underline{p} || = r \} $$
# QUADRATI, CUBI E IPERCUBI
Sono *quadrati* in $\mathbb{R}^2$, *cubi* in $\mathbb{R}^3$ e *ipercubi* in $\mathbb{R}^n,n>3$.
Sono così definiti:

>[!def] QUADRATI e (IPER)CUBI
>Hanno un centro $p\in \mathbb{R}^n$ e un semilato $r\in \mathbb{R}$:
>$$ Q(\underline{p},r] := \{ \underline{x}\in \mathbb{R}^n : \lvert\lvert x_{1}-p_{1} \rvert\rvert \leq r \dots, \lvert\lvert x_{n}-p_{n} \rvert\rvert \leq r \} $$

>[!important] NOTA
>Si parla anche di quadrati e (iper)cubi aperti:
>$$ Q(\underline{p},r[ := \{ \underline{x}\in \mathbb{R}^n : \lvert\lvert x_{1}-p_{1} \rvert\rvert < r\dots, \lvert\lvert x_{n}-p_{n} \rvert\rvert < r \} $$

Vediamo ora alcuni fatti su palle e cubi.

>[!prop] PROPOSIZIONE
>Ogni palla è inclusa in un cubo con lo stesso centro.
>Più precisamente:
>$$ B(\underline{p},r] \text{ } \subseteq \text{ } Q(\underline{p},r] $$

>[!check] DIM.
>Mostriamo che se $\underline{x}\in B$, allora è vero anche $\underline{x}\in Q$.
>Per comodità, trasliamo sia $B$ che $Q$ nell'origine $o$.
>Per cui ora abbiamo:
>$$ \underline{x}\in B(o,r) \Leftrightarrow \sqrt{ x_{1}^2+\dots x_{n}^2 } \leq r$$
>Consideriamo ora una generica componente di $\underline{x}$ $x_{i}$. Vale:
>$$ |x_{i}| = \sqrt{ x_{i}^2 } \leq \sqrt{ x_{1}^2+\dots+x_{n}^2 } \leq r $$
>Ma questo vale per $i=1,\dots,n$.
>Allora abbiamo:
>$$ |x_{i}|\leq r $$ per $i=1,\dots,n$
>E concludiamo quindi che:
>$$ \underline{x}\in B(o,r] \implies \underline{x}\in Q(o,r] $$

>[!prop] PROPOSIZIONE
>Ogni palla è inclusa in un cubo con lo stesso centro.
>Più precisamente:
>$$ B(\underline{p},r] \subseteq Q(\underline{p},\sqrt{ n }r) $$

>[!check] DIM.
>Vogliamo mostrare che se $\underline{x}\in Q$, allora è vero anche $\underline{x}\in B$.
>Come prima, per comodità, trasliamo sia $B$ che $Q$ in $o$.
>Ora, se $\underline{x}\in Q(o,r ]$ abbiamo
>$$ |x_{i}|\leq r $$
>per $i=1,\dots,n$ per definizione di $Q$.
>Allora abbiamo anche:
>$$ \lvert \lvert \underline{x} \rvert  \rvert = \sqrt{ x_{1}^2+\dots+x_{n}^2 } \leq \sqrt{ r^2+\dots+r^2 } = \sqrt{ n }r $$
>Cioè
>$$ \underline{x}\in B(o,\sqrt{ n }r] $$
>come volevamo.

# DISUGUAGLIANZA TRIANGOLARE
Dati due vettori $\underline{x}$ e $\underline{y}$ di $\mathbb{R}^n$ si ha:
>[!prop] Disuguaglianza triangolare
>$$ \lvert \lvert \underline{x}+\underline{y} \rvert  \rvert \leq \lvert \lvert \underline{x} \rvert  \rvert + \lvert \lvert \underline{y} \rvert  \rvert $$

# INTORNI
Grazie alla nozione di palla è possibile definire gli *intorni* in $\mathbb{R}^n$.
>[!def] INTORNO DI UN PUNTO
>$\mathcal{U}$ è un **intorno** di $\underline{p} \in \mathbb{R}^n$ se contiene una *palla* centrata in $p$.
>Ovvero se è verificata la seguente condizione:
>$$ \exists \delta > 0 : B(\underline{p}, \delta] \subseteq \mathcal{U} $$

E grazie agli intorni possiamo dire se un punto è **interno, esterno** o **di frontiera** per un certo insieme $\mathcal{U} \subseteq \mathbb{R}^n$.
>[!def] PUNTI INTERNI, ESTERNI e DI FRONTIERA
>Dato $\underline{p}\in \mathbb{R}^n$, esso può essere:
>- **INTERNO** se $\mathcal{U}$ è un intorno di $\underline{p}$.
>- **ESTERNO** se $\mathbb{R}^n \setminus\mathcal{U}$ è un intorno di $\underline{p}$.
>- **DI FRONTIERA** altrimenti. Cioè se si verifica:
>  $$ \forall\delta>0, B(\underline{p},\delta]\cup\mathcal{U}\ne \emptyset \text{ e } B(\underline{p},\delta]\setminus\mathcal{U}\ne \emptyset $$

A questo punto è comodo definire alcuni insiemi:
>[!def] DEF.
>- $\text{Int }\mathcal{U} = \mathcal{U}^0=$ {punti interni di $\mathcal{U}$}.
>- $\text{Est }\mathcal{U} =$ {punti esterni di $\mathcal{U}$}.
>- $\partial\mathcal{U} =$ {punti di frontiera di $\mathcal{U}$}.
>- $\overline{ \mathcal{U} } =\text{ Clos}(\mathcal{U})=\mathcal{U}\cap \partial\mathcal{U}$.

Possiamo inoltre dare una definizione di insiemi *chiusi* o *aperti*.
>[!def] INSIEMI CHIUSI/APERTI
>Dato un insieme $D\subseteq \mathbb{R}^n$, esso è:
>- **APERTO** se *tutti* i suoi punti sono *interni*.
>- **CHIUSO** se $\mathbb{R}^n\setminus D$ è aperto.

Da tale definizione appare quindi chiara la seguente proposizione:
>[!prop] PROPOSIZIONE
>$D$ è **chiuso** *se e solo se* $\partial D\subseteq D$.

>[!important] ATTENZIONE
>Esistono anche insiemi che non sono *nè chiusi nè aperti*.
# PRODOTTO SCALARE
Ricordiamo la definizione di *prodotto scalare* fra due vettori $\underline{x},\underline{y}$ di $\mathbb{R}^n$:
>[!def] PRODOTTO SCALARE
>Dati $\underline{x},\underline{y}\in \mathbb{R}^n$, il loro *prodotto scalare* è il numero reale:
>$$ \underline{x} \cdot \underline{y} = x_{1}y_{1}+\dots+x_{n}y_{n}$$

Ricordiamo inoltre che se $\underline{u}$ è un vettore di norma $1$, allora $\underline{x}\cdot \underline{u}$ rappresenta la proiezione di $\underline{x}$ sulla retta per $\underline{u}$.

Introduciamo a questo punto un'utile disuguaglianza:
>[!def] DISUGUAGLIANZA DI CAUCHY-SCHWARZ
>Dati $\underline{x},\underline{y}\in \mathbb{R}^n$ vale:
>$$ \underline{x}\cdot \underline{y} \leq ||\underline{x}||\cdot||\underline{y}|| $$

Vale la pena domandarsi quando il prodotto $\underline{x}\cdot \underline{y}$ sia *massimo*, *minimo* o nullo:
- E' **massimo** se $\underline{y}=\lambda \underline{x}$ con $\lambda>0$.
- E' **minimo** se $\underline{y}=\lambda \underline{x}$ con $\lambda<0$.
- E' **nullo** se uno dei due vettori è nullo, oppure se $\underline{x}\perp \underline{y}$.
# COORDINATE POLARI
Ogni punto $P=(x,y)\in \mathbb{R}^2$ si può individuare anche tramite i seguenti parametri:
- Il raggio $\rho(x,y)=\sqrt{ x^2+y^2 }$.
- L'argomento $\theta(x,y)\in \mathbb{R}$, ovvero l'angolo che la semiretta congiungente l'origine con il punto $P$ forma con l'asse delle $x$.







