# INDICE SEZIONE
- [ ] [[#PREMESSE]]
- [ ] [[#INTEGRALI CURVILINEI (I SPECIE)]]
# PREMESSE
## UNIONE DI CURVE
Siano $\gamma_{1}:[a,b]\to \mathbb{R}^n$ e $\gamma_{2}:[b,c]\to \mathbb{R}^n$ con $\gamma_{1}(b)=\gamma_{2}(b)$.
Chiamiamo *unione delle curve* (o giustapposizione) di $\gamma_{1}$ e $\gamma_{2}$:
>[!def] UNIONE DI CURVE
>$$ (\gamma_{1}\cup\gamma_{2})(t) = \begin{cases} \gamma_{1}(t) & t\in[a,b) \\ \gamma_{2}(t) & t\in[b,c] \end{cases} $$

Notiamo che si tratta sostanzialmente di una curva *definita a tratti*.
In generale, possiamo definire curve ottenute dall'unione di un qualsiasi numero finito di cammini che soddisfino l'ipotesi di cui sopra.
>[!important] NOTA
>Stiamo richiedendo che le due curve "siano unite" nel punto dato da $t=b$, tuttavia quando studiamo una curva definita a tratti possiamo anche trovare delle parametrizzazioni equivalenti con cui potrebbe risultare più facile lavorare.
## INVERSIONE DI CAMMINO
Sia $\gamma_{1}:[a,b]\to \mathbb{R}^n$ una curva.
Chiamiamo *inversione di cammino* (o di orientamento)  di $\gamma_{1}$:
>[!def] INVERSIONE DI CAMMINO
>$$ \gamma_{1}^I(t) := \gamma_{1}(a+b-t) \text{ : }a\leq t\leq b $$

Si tratta semplicemente della stessa curva ma percorsa in verso opposto.

>[!important] NOTA
>L'inversione di cammino è una **curva equivalente** a quella di partenza.
# INTEGRALI CURVILINEI (I SPECIE)
Sia $\gamma:[a,b]\to \mathbb{R}^n$, $\gamma \in C^1({[a,b]})$ una curva e sia $f:D\subseteq \mathbb{R}^n\to \mathbb{R}$ con $\gamma([a,b])\subseteq D$ una funzione continua.
Definiamo *integrale curvilineo di prima specie* di $f$ lungo la curva $\gamma$ l'integrale seguente.
>[!def] INTEGRALE CURVILINEO DI PRIMA SPECIE
>$$ \int_{\gamma}fds = \int_{a}^b f\left(\gamma(t)\right)||\gamma'(t)||dt $$

Chiaramente l'integrale sarà uguale anche lungo una curva $\gamma_{1}$ parametrizzazione equivalente a $\gamma$.
## ESEMPIO: MASSA DI UNA BARRETTA
Un esempio di integrale di questo tipo è dato dal calcolo della massa di una barretta descritta da una curva $\gamma:[a,b]\to \mathbb{R}^n$ e la cui densità è data da una funzione $\mu(\underline{x})$.
Possiamo infatti calcolarne la massa con:
$$
\int_{a}^b\mu(\gamma(t))||\gamma'(t)||dt
$$
## ESEMPIO: COORDINATE DEL BARICENTRO
Per un oggetto *curvilineo* descritto da una curva $\gamma$ abbiamo:
$$
M = \int_{\gamma}\mu(\underline{x})ds
$$
E le coordinate del baricentro saranno date da:
$$
x_{B} = \frac{\int_{\gamma} x\mu(x,y,z)ds}{M}
$$
$$
y_{B} = \frac{\int_{\gamma} y\mu(x,y,z)ds}{M}
$$
$$
z_{B} = \frac{\int_{\gamma} z\mu(x,y,z)ds}{M}
$$
