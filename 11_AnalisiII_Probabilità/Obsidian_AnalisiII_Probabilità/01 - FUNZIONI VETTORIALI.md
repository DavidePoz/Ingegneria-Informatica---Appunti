# INDICE SEZIONE
- [ ] [[#DEFINIZIONE]]
- [ ] [[#LIMITI DI FUNZIONI VETTORIALI]]
- [ ] [[#DERIVATE DI FUNZIONI VETTORIALI]]
# DEFINIZIONE
Diamo una definizione di funzione vettoriale.
>[!def] FUNZIONE VETTORIALE
>E' una *funzione* $\underline{f}:\mathcal{I}\subseteq \mathbb{R}\to \mathbb{R}^n$ del tipo:
>$$ \underline{f}(t) = \big( f_{1}(t),\dots,f_{n}(t) \big) $$
>Dove le componenti sono delle funzioni $f_{i}(t):\mathcal{I}\subseteq \mathbb{R}\to \mathbb{R}$.
# LIMITI DI FUNZIONI VETTORIALI
Formalizziamo di seguito la definizione di limite per funzioni vettoriali.
Sia $\underline{f}:\mathcal{I}\to \mathbb{R}^n$ una funzione vettoriale e $t_{0}$ punto di accumulazione per $\mathcal{I}$.
>[!def] LIMITE
>Si scrive $\lim_{ t \to t_{0} }\underline{f}(t)=\underline{l}\in \mathbb{R}^n$ se
>$$ \forall\varepsilon>0 \exists \delta>0 : 0<|t-t_{0}|<\delta \implies \underline{f}(t)\in B(\underline{l},\varepsilon[ $$

Dal punto di vista pratico, possiamo calcolare i limiti di funzioni vettoriali componente per componente.
>[!prop] Limite componente per componente
>Sia $\underline{f}=(f_{1},\dots,f_{n}):\mathcal{I}\to \mathbb{R}^n$, $\underline{l}=(l_{1},\dots,l_{n})$ e $t_{0}$ di accumulazione per $\mathcal{I}$.
>Si ha $\lim_{ t \to t_{0} }\underline{f}(t)=\underline{l}$ *se e solo se*:
>$$ \forall i\in\{1,\dots,n\} \lim_{ t \to t_{0} }f_{i}(t)=l_{i}  $$
## CONTINUITA'
>[!def] FUNZIONE CONTINUA
>La funzione vettoriale $\underline{f}$ è continua in $t_{0}\in\mathcal{I}$ se
>- $t_{0}$ è punto isolato, oppure
>- $\lim_{ t \to t_{0} }f(t)=f(t_{0})$.

Per cui possiamo concludere che $\underline{f}$ è continua *se e solo se* le sue componenti $f_{1},\dots mf_{n}$ lo sono.
# DERIVATE DI FUNZIONI VETTORIALI
Formalizziamo a questo punto la definizione di derivata di una funzione vettoriale, ancora una volta del tutto simile a quella vista per una funzione reale in una variabile.
Sia $\underline{f}:\mathcal{I}\to \mathbb{R}^n$ una funzione vettoriale e $t_{0}\in\mathcal{I}$.
>[!def] FUNZIONE DERIVABILE
>Diciamo che $\underline{f}$ è *derivabile* in $t_{0}$ se esiste in $\mathbb{R}^n$ il limite:
>$$ \lim_{ t \to t_{0} } \frac{\underline{f}(t)-\underline{f}(t_{0})}{t-t_{0}} = \underline{l}_{0}\in \mathbb{R}^n  $$
>In tal caso diciamo $f'(t_{0})=\underline{l}_{0}$

Anche le derivate di funzioni vettoriali possono essere calcolate componente per componente.
Valgono ancora le regole di derivazione classiche (prodotto, composizione ecc...).
## RETTA TANGENTE
Sia $\underline{f}$ una funzione vettoriale derivabile in $t_{0}$. 
>[!def] RETTA TANGENTE
>La retta tangente alla curva $\underline{f}$ in $t_{0}$ è l'insieme di punti:
>$$ \{ \underline{f}(t_{0})+\lambda\underline{f}'(t_{0}), \lambda \in \mathbb{R} \} $$
### APPROSSIMAZIONE AL PRIM'ORDINE
La retta tangente è anche la migliore approssimazione della funzione in un intorno di $t_{0}$.
Infatti:
>[!prop] APPROSSIMAZIONE
>$$ \underline{f}(t) = \underline{f}(t_{0}) + \underline{f}'(t_{0})(t-t_{0}) + R(t) $$
>e vale
>$$ \lim_{ t \to t_{0} } \frac{R(t)}{t-t_{0}}=0 $$
## VELOCITA' E ACCELERAZIONE
Data $\underline{f}:\mathcal{I}\to \mathbb{R}^n$, se $f_{1},\dots,f_{n}$ sono *derivabili* in $\mathcal{I}$ definiamo il **vettore tangente** o *velocità vettoriale* il seguente vettore:
>[!def] VETTORE TANGENTE
>$$ \underline{f}'(t) = \dot{\underline{f}}(t) = \big( \dot{f}_{1}(t),\dots,\dot{f}_{n}(t) \big) $$

Chiaramente, la velocità *scalare* sarà data dalla norma del vettore velocità.

Se $f_{1},\dots,f_{n}$ sono derivabili *due volte*, possiamo definire anche il vettore accelerazione:
>[!def] VETTORE ACCELERAZIONE
>$$ \underline{f}''(t) = \ddot{\underline{f}}(t) = \big( \ddot{f}_{1}(t),\dots,\ddot{f}_{n}(t) \big) $$

E ancora una volta l'accelerazione *scalare* sarà data dalla norma del vettore accelerazione.

