# INDICE SEZIONE
- [ ] [[#DEFINIZIONE]]
- [ ] [[#GRAFICO]]
- [ ] [[#CAMPI GRADIENTE]]
- [ ] [[#INTEGRALI CURVILINEI (II SPECIE)]]
- [ ] [[#TEOREMA FONDAMENTALE DEI CAMPI GRADIENTI]]
- [ ] [[#DIVERGENZA]]
- [ ] [[#CAMPI IRROTAZIONALI]]
# DEFINIZIONE
>[!def] CAMPO VETTORIALE
>Chiamiamo campo vettoriale una funzione del tipo:
>$$ \mathbf{F}:D\subseteq \mathbb{R}^n\to \mathbb{R}^n $$
>$$ \underline{x} \mapsto \mathbf{F}(\underline{x})=(f_{1}(\underline{x}),\dots,f_{n}(\underline{x})) $$

>[!prop] PROPRIETA'
>$\mathbf{F}$ è **continuo** / **differenziabile** / $C^1$ / **...** se lo sono *anche* le sue componenti $f_{1},\dots,f_{n}$.
# GRAFICO
Il grafico di un campo vettoriale su $\mathbb{R}^n$ è un oggetto dello spazio di dimensione $2n$.
Per tale motivo, che ne rende difficoltosa la visualizzazione, si rappresenta il campo con dei vettori disegnati in corrispondenza di ogni punto del dominio.

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}

\begin{document}

\begin{tikzpicture}[scale=1.4]
\begin{axis}[width=8cm,height=8cm, view = {0}{90},
			domain = -5:5, y domain = -5:5]

\addplot3[red, quiver={u=-y/sqrt(x^2+y^2), v=x/sqrt(x^2+y^2), scale arrows=0.4}, 
		  samples=20,
		  -latex] {0};

\end{axis}
\end{tikzpicture}

\end{document}
```

In figura è riportata la rappresentazione del campo vettoriale dato da
$$
\mathbf{F}(x,y) = (-y,x)
$$
>[!warning] NOTA
>Spesso, come anche in figura, i vettori sono **normalizzati** per rendere più comprensibile la rappresentazione.
# CAMPI GRADIENTE
>[!def] CAMPO GRADIENTE
>Sia $D\subseteq \mathbb{R}^n$.
>Un *campo continuo* $\mathbf{F}:D\to \mathbb{R}^n$ è detto **campo gradiente** se esiste $V:D\to \mathbb{R}$ funzione di classe $C^1$ tale che:
>$$ \mathbf{F}=\nabla V $$
>Una tale funzione è detta *primitiva* di $\mathbf{F}$.

Cioè un campo è un *campo gradiente* se è *gradiente* di una qualche *funzione scalare*.
>[!important] IN FISICA
>Ricordiamo che in fisica tale condizione implica la conservatività del campo, e la funzione $U=-V$ è chiamata *potenziale*. ($\mathbf{F}=-\nabla U$)
## CAMPI RADIALI
Così come avevamo definito funzioni radiali, definiamo anche i campi radiali.
>[!def] CAMPO VETTORIALE RADIALE
>Un campo vettoriale è radiale se è della forma:
>$$ \mathbf{F}(\underline{x}) = g(||\underline{x}||) \frac{x}{||\underline{x}||} $$
>E chiamiamo $\hat{r}(\underline{x})$:
>$$ \hat{r}(\underline{x}) := \frac{x}{||\underline{x}||} $$

Il campo gravitazionale e quello elettrostatico sono esempi di campi vettoriali radiali.
>[!prop] PROPOSIZIONE
>Ogni *campo radiale continuo* è un campo gradiente.
>Una primitiva di $\mathbf{F}$ è data da $\mathbf{G}(||\underline{x}||)$ tale che $\mathbf{G}'=g$.
# INTEGRALI CURVILINEI (II SPECIE)
>[!def] INTEGRALE CURVILINEO DI UN CAMPO VETTORIALE
>Sia $\gamma:[a,b]\to D\subset \mathbb{R}^n$ una curva (o unione di cammini) e sia $\mathbf{F}:D\to \mathbb{R}^n$ un campo vettoriale continuo.
>L'integrale curvilineo del campo su $\gamma$ è:
>$$ \int_{\gamma}\mathbf{F}\cdot ds = \int_{a}^b \mathbf{F}(\gamma(t))\cdot\gamma'(t)dt $$

Se $\gamma$ è chiusa si parla di *circuitazione* di $\mathbf{F}$ su $\gamma$, spesso indicata con:
$$
\oint_{\gamma}\mathbf{F}\cdot ds
$$
>[!important] NOTA
>Se $\mathbf{F}$ è un campo di forze, l'integrale curvilineo su $\gamma$ corrisponde al **lavoro del campo lungo la curva**.
>Per come è definito, il lavoro lungo una curva misura la *tendenza del campo* a **seguire la curva** stessa.
## CAMPI CONSERVATIVI
>[!def] CAMPO CONSERVATIVO
>Un campo continuo $\mathbf{F}:D\subset \mathbb{R}^n\to \mathbb{R}^n$ ($D$ aperto) si dice **conservativo** se per ogni coppia di cammini $\gamma_{1}$ e $\gamma_{2}$ che abbiano gli stessi estremi si ha:
>$$ \int_{\gamma_{1}}\mathbf{F}\cdot ds = \int_{\gamma_{2}}\mathbf{F}\cdot ds $$

La condizione sopra è equivalente a dire che la **circuitazione** del campo è nulla, cioè:
$$
\oint_{\gamma} \mathbf{F}\cdot ds = 0
$$
con $\gamma$ curva chiusa.
# TEOREMA FONDAMENTALE DEI CAMPI GRADIENTI
Il *teorema fondamentale del calcolo* (1D) afferma che se $f:[a,b]\to \mathbb{R}$ è continua e $F$ è primitiva di $f$ (cioè $F'=f$), allora $\int_{a}^b f(t)dt = F(b)-F(a)$.
Una proprietà analoga vale in $\mathbb{R}^n$ per i campi gradienti:
>[!theorem] TEOREMA FONDAMENTALE DEI CAMPI GRADIENTI
>Siano $D$ aperto di $\mathbb{R}^n$, $\mathbf{F}:D\to \mathbb{R}^n$ un *campo gradiente* e sia $U$ primitiva di $\mathbf{F}$.
>Se $\gamma:[a,b]\to D$ è cammino si ha:
>$$ \int_{\gamma}\mathbf{F}\cdot ds = U(\gamma(b)) - U(\gamma(a)) $$

>[!check] DIM.
>Essendo $\mathbf{F}$ campo gradiente, vale $\mathbf{F}=\nabla U$, per cui:
>$$ \int_{\gamma}\mathbf{F}\cdot ds = \int_{\gamma}\nabla U\cdot ds = \int_{a}^b\nabla U(\gamma(t))\cdot\gamma'(t)dt $$
>A questo punto osserviamo che, per la regola della catena, si ha:
>$$ \frac{d}{dt}U(\gamma(t)) = \nabla U(\gamma(t))\cdot\gamma'(t) $$
>Allora abbiamo:
>$$ \int_{\gamma}\mathbf{F}\cdot ds = \int_{a}^b \frac{d}{dt}U(\gamma(t))dt = U(\gamma(b))-U(\gamma(a)) $$
>E concludiamo $\square$.

Questo risultato ci permette di concludere che un campo gradiente è un *campo conservativo*, infatti il suo integrale curvilineo dipende *solo dagli estremi della curva* e *non dal cammino seguito*.
In particolare, possiamo affermare:
>[!prop] PROPOSIZIONE
>Sia $D$ aperto di $\mathbb{R}^n$.
>Un *campo continuo* su $D$ è **conservativo** *se e solo se* è un *campo gradiente*.
## METODO DELLE INTEGRAZIONI PARZIALI
Supponiamo che $\mathbf{F}:D\to \mathbb{R}^n$ sia un *campo conservativo*. 
Determinare una primitiva di $\mathbf{F}=(F_{1},\dots,F_{n})$ significa trovare una funzione scalare $U:D\to \mathbb{R}$ di classe $C^1$ che risolve il sistema seguente:
$$
\begin{cases}
\partial_{x_{1}}U = F_{1} \\ \\
\dots \\ \\
\partial_{x_{n}}U = F_{n}
\end{cases}
$$
Il metodo consiste nel cominciare da una delle equazioni e procedere una ad una.
Ad esempio, si cerca una primitiva
$$
U_{1}(x)\in \int F_{1}(x)dx_{1}
$$
di $F_{1}$ rispetto ad $x_{1}$.
Abbiamo per costruzione
$$
\partial_{x_{1}}U(x)=\partial_{x_{1}}U_{1}(x)
$$
Cioè
$$
\partial_{x_{1}}(U(x)-U_{1}(x)) = 0
$$
Ma quindi $U(x) - U_{1}(x)$ è, almeno localmente, una funzione $\varphi_{1}(x_{2},\dots,x_{n})$ di classe $C^1$ che dipende *solo da* $(x_{2},\dots,x_{n})$ e si ha allora:
$$
U(x) = U_{1}(x) + \varphi_{1}(x_{2},..,x_{n})
$$
E si continua ad applicare lo stesso procedimento usando l'uguaglianza appena trovata ad un'altra equazione per ogni componente.
### CASI CAMPI RADIALI
Per i campi radiali non è necessario seguire tale procedimento: si trova una primitiva ancora più facilmente.
Infatti se $\mathbf{F}(x)=g(||x||) \frac{x}{||x||}$, allora si ha:
$$
\mathbf{F}(x) = \nabla G(||x||)
$$
con $G'=g$, cioè $G$ primitiva di $g$.
Infatti:
$$
\nabla G\left(\sqrt{ x_{1}^2+\dots x_{n}^2 }\right) = G'(||x||)\cdot \nabla\left(\sqrt{ x_{1}^2+\dots x_{n}^2 }\right) = g(||x||) \frac{x}{||x||}
$$
# DIVERGENZA
>[!def] DIVERGENZA DI UN CAMPO VETTORIALE
>Dato $\mathbf{F}\in C^1(D)$ con $D$ aperto di $\mathbb{R}^n$ chiamiamo *divergenza* del campo la seguente quantità:
>$$ \mathrm{div}\mathbf{F} = \nabla \cdot \mathbf{F} = \sum_{i=1}^n \partial_{x_{i}}f_{i} $$

Se la divergenza di un campo vettoriale è *nulla in tutto il suo dominio*, diciamo che il campo è **indivergente**.
>[!important] NOTA
>1. E' una quantità *scalare* che determina la *tendenza* delle **linee di flusso** di un campo vettoriale a **confluire** verso una sorgente oppure a **diramarsi** da essa. 
>2. Si ottiene anche considerando il flusso di un campo attraverso una superficie chiusa che racchiude una regione di spazio $\mathbf{V}$ e calcolando il limite del rapporto tra tale flusso ed il volume di $\mathbf{V}$ per il tendere a $0$ di quest'ultimo (cioè il volume si riduce al punto $p\in \mathbb{R}^n$):
>   $$ \mathrm{div}\mathbf{F}(p) = \lim_{ \mathbf{V} \to p } \frac{1}{\mathbf{V}}\int_{S(\mathbf{V})} \mathbf{F}\cdot \vec{u}_{n}dS  $$

Per capire meglio il significato della divergenza riconduciamoci ad un esempio fisico.
Ricordiamo che la divergenza del campo elettrostatico è data da:
$$
\nabla \cdot \mathbf{E} = \frac{\rho}{\varepsilon_{0}}
$$
Dove $\rho$ è la densità di carica.
Considerando una singola carica puntiforme, abbiamo $\rho>0 \implies \nabla \cdot \mathbf{E}>0$.

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}

\begin{document}

\begin{tikzpicture}[scale=1.4]
\begin{axis}[width=8cm,height=8cm, view = {0}{90},
			domain = -5:5, y domain = -5:5]

\addplot3[red, quiver={u=x/sqrt(x^2+y^2), v=y/sqrt(x^2+y^2), scale arrows=0.4}, 
		  samples=20,
		  -latex] {0};

\end{axis}
\end{tikzpicture}

\end{document}
```

Come si può vedere dalla figura, infatti, il campo tende a *diramarsi dall'origine*.
Per una carica negativa avremmo invece $\nabla \cdot \mathbf{E}<0$ ed il campo tenderà allora a *confluire verso l'origine*:

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}

\begin{document}

\begin{tikzpicture}[scale=1.4]
\begin{axis}[width=8cm,height=8cm, view = {0}{90},
			domain = -5:5, y domain = -5:5]

\addplot3[red, quiver={u=-x/sqrt(x^2+y^2), v=-y/sqrt(x^2+y^2), scale arrows=0.4}, 
		  samples=20,
		  -latex] {0};

\end{axis}
\end{tikzpicture}

\end{document}
```


Consideriamo a questo punto il campo dato da:
$$
\mathbf{F}(x,y) = \frac{k}{x^2+y^2} \begin{pmatrix}
-y \\
x
\end{pmatrix} = (f_{1},f_{2})
$$
Abbiamo:
$$
\partial_{x}f_{1} = \frac{2xy}{(x^2+y^2)^2}
$$
E
$$
\partial_{y}f_{2} = -\frac{2xy}{(x^2+y^2)^2}
$$
Per cui 
$$
\nabla \cdot \mathbf{F} = \partial_{x}f_{1} + \partial_{y}f_{2} = 0
$$
Osservando la rappresentazione del campo notiamo infatti che esso *non si dirama* dall'origine, ma *non converge neanche* verso essa. 

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}

\begin{document}

\begin{tikzpicture}[scale=1.4]
\begin{axis}[width=8cm,height=8cm, view = {0}{90},
			domain = -5:5, y domain = -5:5]

\addplot3[red, quiver={u=-y/sqrt(x^2+y^2), v=x/sqrt(x^2+y^2), scale arrows=0.4}, 
		  samples=20,
		  -latex] {0};

\end{axis}
\end{tikzpicture}

\end{document}
```
# CAMPI IRROTAZIONALI
>[!def] CAMPI IRROTAZIONALI
>Sia $D$ aperto di $\mathbb{R}^n$.
>Un *campo* $\mathbf{F}:D\to \mathbb{R}^n$ di classe $C^1$ si dice **irrotazionale** *se e solo se*, per ogni $i,j\in\{1,\dots,n\}$ si ha:
>$$ \partial_{x_{j}}F_{i} = \partial_{x_{i}}F_{j} $$

Il termine irrotazionale deriva dal fatto che se $\mathbf{F}$ è un tale campo, il suo rotore è nullo per ogni punto dello spazio.
Se $\mathbf{F}$ è un campo in $\mathbb{R}^3$, il suo rotore è:
$$
\mathrm{rot}\mathbf{F} = \nabla \times \mathbf{F} = \det \begin{pmatrix}
\hat{x} & \hat{y} & \hat{z} \\
\frac{\partial}{\partial x} & \frac{\partial}{\partial y} & \frac{\partial}{\partial z} \\
F_{x} & F_{y} & F_{z}
\end{pmatrix}
$$
Il rotore ha un'interpretazione fisica: se $\mathbf{F}$ rappresenta per esempio il campo di velocità di un fluido, questo fa ruotare un corpo posizionato al suo interno rispetto ad un asse dato proprio dal rotore e con una velocità angolare di norma pari alla metà di quella del rotore.

>[!prop] PROPOSIZIONE
>Siano $D$ aperto di $\mathbb{R}^n$ ed $\mathbf{F}:D\to \mathbb{R}^n$ un campo $C^1$ conservativo.
>Allora $\mathbf{F}$ è *irrotazionale*.

>[!check] DIM.
>Se il campo $\mathbf{F}$ è conservativo, esiste una primitiva $U:D\to \mathbb{R}$ tale che $\nabla U=F$.
>Per ogni $i,j$ in $1,\dots,n$ si ha, per il teorema di Schwarz:
>$$ \partial_{x_{i}}F_{j} = \partial_{x_{i}}(\partial_{x_{j}}U) = \partial_{x_{j}}(\partial_{x_{i}}U) = \partial_{x_{j}}F_{i} $$
>E concludiamo $\square$.

Visto che un campo conservativo è sicuramente conservativo, possiamo usare il test delle derivate miste per capire se un campo *non* è conservativo: se il test fallisce, certamente il campo *non è conservativo*.
>[!warning] ATTENZIONE
>Se però troviamo che il campo è *irrotazionale*, non è necessariamente detto che esso sia anche conservativo.
## APERTI SEMPLICEMENTE CONNESSI
>[!def] APERTO SEMPLICEMENTE CONNESSO
>Un aperto $D$ di $\mathbb{R}^n$ è *semplicemente connesso* se è **connesso** (ogni coppia di punti del dominio è connessa da un cammino nel dominio) e se *ogni circuito* $\gamma:[a,b]\to D$ a valori in $D$ si può *contrarre con continuità ad un punto in* $D$.
>Cioè vi è una applicazione continua:
>$$ (t,\lambda)\in[a,b]\times[0,1]\mapsto\gamma_{\lambda}(t)\in D $$
>tale che ogni $\gamma_{\lambda}$ è circuito; $\gamma_{1}=\gamma$, $\gamma_{0}=p \in D$ per un qualche punto $p$.

Cioè ogni circuito può essere *"fatto collassare"* ad un punto $p \in D$.
>[!def] INSIEME STELLATO
>Un insieme $D\subset \mathbb{R}^n$ è *stellato* rispetto ad un punto $x_{0}\in D$ se per ogni $x \in D$ il segmento $[x_{0},x]$ è contenuto in $D$.

>[!prop] PROPOSIZIONE
>Un *aperto stellato* di $\mathbb{R}^n$ è semplicemente connesso.
>In particolare ogni palla è semplicemente connessa.

>[!def] INSIEME CONVESSO
>Un insieme è *convesso* se è *stellato rispetto ad ogni suo punto*.
>Oppure, equivalentemente, se esso contiene tutti i segmenti che congiungono ogni coppia di suoi punti.
## CAMPI IRROTAZIONALI SU DOMINI SEMPLICEMENTE CONNESSI
>[!theorem] CAMPI IRR. SU DOMINI SEMP. CONNESSI
>Siano $D$ aperto *semplicemente connesso* di $\mathbb{R}^n$ ed $\mathbf{F}:D\to \mathbb{R}^n$ campo $C^1$ *irrotazionale*.
>Allora $\mathbf{F}$ è conservativo.

>[!prop] COROLLARIO
>Ogni campo $C^1$ irrotazionale su un aperto è **localmente conservativo**:
>per ogni punto $p$ del dominio il campo è conservativo su ogni *palla centrata* in $p$ e contenuta nel dominio.
>
## CAMPI IRROTAZIONALI SUL PIANO BUCATO
Per verificare se un campo è conservativo dovremmo verificare che tutte le sue circuitazioni siano nulle.
Se il campo è irrotazionale sul piano bucato basta verificare la nullità delle circuitazioni solo su un circuito a scelta attorno ad *ogni buco*.
Vale infatti la seguente proposizione:
>[!prop] PROPOSIZIONE
>Sia $\Omega \subset \mathbb{R}^2$ aperto *semplicemente connesso*, $a_{1},..,a_{n}$ punti di $\Omega$.
>Sia $\mathbf{F}:D:=\Omega \setminus\{a_{1},..,a_{n}\}\to \mathbb{R}^2$ *campo irrotazionale* di classe $C^1$.
>Allora $\mathbf{F}$ è **conservativo** *se e solo se*, scelti arbitrariamente $n$ circoli $\gamma_{1},\dots,\gamma_{n}$ contenuti in $D$, attorno ai punti $a_{1},\dots,a_{n}$, si ha
>$$ \int_{\gamma_{i}}\mathbf{F}ds = 0 $$
>per $i=1,\dots,n$.

>[!tip] NOTA
>Allora, se $\mathbf{F}$ è **irrotazionale**, abbiamo:
>- Se è definito su un aperto *semplicemente connesso*, allora è anche *conservativo*.
>- Se è definito su un aperto *bucato*, dobbiamo calcolare le circuitazioni lungo delle curve arbitrarie attorno ai buchi per concludere se è *conservativo oppure no*.


