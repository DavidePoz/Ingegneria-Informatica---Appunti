# INDICE SEZIONE
- [ ] [[#DEFINIZIONE]]
- [ ] [[#TIPOLOGIE DI CURVE]]
- [ ] [[#CURVE IN COORDINATE POLARI]]
- [ ] [[#LUNGHEZZA DI CURVE]]
- [ ] [[#PARAMETRIZZAZIONI EQUIVALENTI]]
# DEFINIZIONE
Tra le funzioni vettoriali, alcune sono definite *curve parametriche*:
>[!def] CURVE PARAMETRICHE
>Sono funzioni vettoriali le cui funzioni componenti $\gamma_{1},\dots,\gamma_{n}$ sono **continue** e l'insieme $\mathcal{I}$ è **chiuso e limitato**, cioè del tipo $[a,b]$.

Se immaginiamo la curva come un'associazione fra un istante di tempo $t$ e una posizione nello spazio $\underline{\gamma}(t)$ potremmo riferirci all'insieme di tali punti come *traiettoria* (per esempio di una particella).
Tuttavia, nel linguaggio matematico, ci riferiamo a tale insieme con i termini **sostegno, supporto** o **immagine**.
>[!def] SOSTEGNO
>Il *sostegno* $\Sigma \subset \mathbb{R}^n$ della curva $\underline{\gamma}(t)$ è l'insieme:
>$$ \Sigma = \mathrm{Im}\underline{\gamma} = \{ \underline{\gamma}(t) : a\leq t\leq b \}\subseteq \mathbb{R}^n $$
>Cioè l'insieme dei punti "toccati" dalla curva.
# TIPOLOGIE DI CURVE
Possiamo anche classificare le curve secondo alcuni criteri e dire che una curva è:
- **PIANA** se esiste un piano che contiene il suo sostegno.
- **CHIUSA** se $\underline{\gamma}(a)=\underline{\gamma}(b)$.
- **SEMPLICE** se $\underline{\gamma}$ è *iniettiva*, se non al più negli estremi di $\mathcal{I}$ (è concesso $\underline{\gamma}(a)=\underline{\gamma}(b)$, cioè una curva chiusa e semplice).
## CURVE REGOLARI
La derivata di una curva ci permette di definire una curva *regolare*:
>[!def] CURVA REGOLARE
>Diciamo che $\underline{f}:\mathcal{I}\to \mathbb{R}^n$ è **regolare** se
>$$ \underline{f}'(t)\ne 0 \text{ }\forall t\in\mathcal{I} $$

Si può anche parlare di *singoli* **valori regolari**: in tal caso diciamo, per esempio, che $t_{0}$ è valore regolare per la curva.
## CURVE CARTESIANE
Diamo la definizione di curva cartesiana:
>[!def] CURVA CARTESIANA
>E' una curva del tipo:
>$$ \underline{f}(t) = (t,h(t)) \text{ oppure } \underline{f}(t) = (h(t),t) $$

Notiamo che il sostegno di una tale curva
$$
\Sigma_{f} = \{ (t,h(t)): t\in[a,b] \}
$$
coincide con il grafico $G_{h}$ della funzione $h(t)$.
# CURVE IN COORDINATE POLARI
Dato che ogni punto di $\mathbb{R}^2$ può essere identificato con le coordinate polari, possiamo esprimere anche le curve con questo sistema.
>[!def] CURVA IN COORDINATE POLARI
>Sia $\rho:[a,b]\to \mathbb{R}$ una *funzione continua*. La *curva definita in coordinate polari da $\rho(t), t\in[a,b]$* è la curva:
>$$ \underline{\gamma}(t) := (\rho(t)\cos t,\rho(t)\sin t) $$

Riportiamo di seguito un risultato che può essere utile da ricordare:
$$
||\underline{\gamma}'(t)|| = \sqrt{ \rho(t)^2 + \rho'(t)^2 }
$$
# LUNGHEZZA DI CURVE
Sia $\underline{\gamma}:[a,b]\to \mathbb{R}^n$ una curva. Per ogni elemento
$$
\pi = \{ a=t_{0}<t_{1}<\dots<t_{m}<t_{m+1}=b \}
$$
della famiglia $\mathcal{P}(a,b)$ delle partizioni di $[a,b]$ definiamo
$$
V_{\gamma}(\pi) := \sum_{i=0}^m |\gamma(t_{i+1})-\gamma(t_{i})|
$$
Ovvero la lunghezza della *poligonale* di vertici consecutivi $f(a),f(t_{1})\dots,f(b)$.
Tale lunghezza è crescente all'aumentare della finezza della suddivisione, cioè per ogni $\pi_{1},\pi_{2}\in\mathcal{P}(a,b)$ tali che $\pi_{1}\subset \pi_{2}$ risulta
$$
V_{\gamma}(\pi_{1})<V_{\gamma}(\pi_{2})
$$
Al rendersi sempre più fine della partizione $\pi$, la corrispondente poligonale approssima la curva sempre meglio e così anche $V_{\gamma}(\pi)$ diventa un'approssimazione sempre migliore di $\underline{\gamma}$.
>[!def] CURVA RETTIFICABILE
>La curva $\underline{\gamma}$ si dice *rettificabile* se
>$$ L(\underline{\gamma},[a,b]) := \underset{ \pi \in\mathcal{P(a,b)} }{ \mathrm{sup} } V_{\gamma}(\pi) $$
>è finito. In tal caso il numero reale $L(\underline{\gamma},[a,b])$ si chiama **lunghezza** di $\underline{\gamma}$.

## LUNGHEZZE DI CURVE REGOLARI
Se la curva è *regolare* ci viene in aiuto il calcolo integrale con il seguente teorema:
>[!prop] LUNGHEZZA DI UNA CURVA
>Se $\underline{\gamma}:[a,b]\to \mathbb{R}^n$ è *regolare* e di classe $C^1$ (derivabile con derivata continua), allora $\underline{\gamma}$ è rettificabile e:
>$$ L(\underline{\gamma},[a,b]) = \int_{a}^{b} ||\underline{\gamma}'(t)||dt $$

Possiamo anche dare un'interpretazione "fisica" a questo teorema.
Possiamo interpretare $t$ come un istante di tempo, il che ci permette di pensare a $||\underline{\gamma}'(t)||$ come alla velocità di un corpo che percorre la curva.
Integrando tale quantità in $dt$ troviamo allora proprio la lunghezza della curva.
### PARENTESI: INTEGRALI CURVILINEI
Data $f:\mathbb{R}^n\to \mathbb{R}$ continua (vedremo più avanti) ed una curva $\underline{\gamma}:[a,b]\to \mathbb{R}^n$ con $\gamma_{1},\dots\gamma_{n}\in C^1([a,b])$ definiamo:
>[!def] Integrale Curvilineo di I Specie
>L'integrale curvilineo di prima specie di $f$ lungo $\underline{\gamma}$:
>$$ \int_{a}^b f\big( \underline{\gamma}(t) \big)\cdot||\underline{\gamma}'(t)|| dt $$

Notiamo a questo punto che se $f(x_{1},\dots,x_{n})=1\forall x \in[a,b]$ abbiamo esattamente $L(\underline{\gamma},[a,b])$.
### LUNGHEZZA DI CURVE CARTESIANE
Ricordiamo che una curva cartesiana ha derivata del tipo
$$
\underline{\gamma}_{c}' = \big( 1,h'(t) \big)
$$
Per cui, applicando il teorema, troviamo:
$$
L(\underline{\gamma}_{c}) = \int_{a}^b \sqrt{ 1+h'(t)^2 } dt
$$
### LUNGHEZZA DI CURVE IN COORDINATE POLARI
Sia $\underline{\gamma}$ la curva definita in coordinate polari da $\rho(t),t\in[a,b]$ con $\rho$ funzione di classe $C^1$.
Allora la sua lunghezza è:
>[!prop] LUNGHEZZA DI UNA CURVA IN COORDINATE POLARI
>$$ L(\underline{\gamma},[a,b]) = \int_{a}^b \sqrt{ \rho'(t)^2 + \rho(t)^2 } dt $$

Per dimostrarlo è sufficiente calcolare $\gamma'(t)$ e $||\underline{\gamma}'(t)||$.
# PARAMETRIZZAZIONI EQUIVALENTI
Abbiamo visto che è molto importante considerare la parametrizzazione di una curva.
A volte due curve potrebbero sembrarci in qualche modo legate, anche se le loro parametrizzazioni sono differenti.
Introduciamo allora la definizione seguente:
>[!def] CURVE o PARAMETRIZZAZIONI EQUIVALENTI
>Due *curve parametriche* $\underline{f}:I\to \mathbb{R}^n$ e $\underline{g}:J\to \mathbb{R}^n$ con $I,J$ intervalli di $\mathbb{R}$, si dicono **equivalenti** se esiste una biezione $\varphi:I\to J$ di classe $C^1$ con $\varphi'(t)\ne 0$ $\forall t\in I$ tale che:
>$$ f(t) = g(\varphi(t)) \text{ }\forall t\in I $$

La relazione può essere schematizzata come di seguito:
$$
\begin{matrix}
 &  & \mathbb{R}^n \\
 & \underset{ g(\varphi) }{ \nearrow } & \underset{ g }{ \uparrow } \\
I & \underset{ \varphi }{ \longrightarrow } & J
\end{matrix}
$$
Molte proprietà delle curve non variano se si considerano parametrizzazioni equivalenti.
>[!prop] PROPOSIZIONE
>Due parametrizzazioni equivalenti hanno lo *stesso sostegno*.

>[!theorem] TEOREMA
>Se $f,g$ sono parametrizzazioni equivalenti di classe $C^1$, allora:
>$$ L(f,I) = L(g,J) $$
## LUNGHEZZA D'ARCO
Se $\underline{f}:[a,b]\to \mathbb{R}^n$ e $t\in[a,b]$ definiamo la restrizione di $\underline{f}$ ad $[a,t]$:
>[!def] RESTRIZIONE
>$$ \underline{f}_{/[a,t]}(\tau) := f(\tau) \text{ } \forall \tau \in[a,t] $$

La lunghezza di tale arco a cui abbiamo ristretto la curva è detto **ascissa curvilinea** del punto $\underline{f}(t)$.
Questo ci permette di definire la funzione seguente:
>[!def] LUNGHEZZA D'ARCO (Funzione)
>$$ s:[a,b]\to [0,L(\underline{f},[a,b])] $$
>$$ t\mapsto s(t) $$
### RIPARAMETRIZZAZIONE CON LUNGHEZZA D'ARCO
Sia $\underline{f}:[a,b]\to \mathbb{R}^n$ una curva di classe $C^1$ con $\underline{f}'(t)\ne 0$ per ogni $t\in[a,b]$.
La lunghezza d'arco è
$$
s(t) = \int_{a}^b || \underline{f}'(\tau) ||d\tau \text{ }\forall t\in[a,b]
$$
Per cui abbiamo 
$$
s'(t) = ||\underline{f}'(t)||>0
$$
per le ipotesi su $\underline{f}$,  e quindi $s(t)$ è *invertibile*, con inversa:
$$
\begin{matrix}
s:[a,b]\to[0,L(\underline{f},[a,b])] \\
s \mapsto s(t)
\end{matrix}
$$
>[!important] NOTA
>Se $s(t)$ associava a $t$ la lunghezza dell'arco fino a quel punto, l'inversa associa ad $s$ il punto dell'arco per cui si ha lunghezza pari ad $s$ stessa.

Possiamo a questo punto porre
$$
\underline{g}(s) := \underline{f}(t(s)) \text{ }\forall s \in[0,L(\underline{f},[a,b])]
$$
ottenendo un'altra curva parametrica $\underline{g}:[0,L]\to \mathbb{R}^n$ che chiamiamo **riparametrizzazione di** $\underline{f}$ con la **lunghezza d'arco**.
Chiaramente $\underline{f}$ e $\underline{g}$ sono parametrizzazioni equivalenti.

L'utilità di tale parametrizzazione è il *significato geometrico* assunto da $s$: indica appunto la *lunghezza dell'arco* di curva compreso tra i punti $\underline{g}(0)$ e $\underline{g}(s)$, cioè l'arco **fin'ora percorso**.
>[!tldr] PROCEDIMENTO
>1. Determiniamo la funzione *lunghezza d'arco* $s(t)$.
>2. La invertiamo, ottenendo $t(s)$.
>3. Riparametrizziamo la curva, ottenendo $f(t(s))$.
>4. Così possiamo subito trovare il punto posto ad una certa lunghezza d'arco dall'inizio della curva.



