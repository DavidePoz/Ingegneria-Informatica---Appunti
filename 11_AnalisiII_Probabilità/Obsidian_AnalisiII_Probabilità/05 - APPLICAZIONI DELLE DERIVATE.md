# INDICE SEZIONE
- [ ] [[#GRADIENTE]]
- [ ] [[#SPAZI TANGENTI E DIFFERENZIABILITA']]
- [ ] [[#MASSIMI E MINIMI]]
- [ ] [[#PUNTI STAZIONARI]]
- [ ] [[#RICERCA DEI PUNTI DI ESTREMO]]
# GRADIENTE
Il **vettore** le cui coordinate sono nell'ordine le $n$ derivate parziali di una funzione in un punto si chiama gradiente.
>[!def] GRADIENTE
>Sia $f:D\subseteq \mathbb{R}^n\to \mathbb{R}$ derivabile parzialmente in $p$ rispetto a tutte le direzioni.
>Il gradiente di $f$ in $p$ è il vettore
>$$ \nabla f(p) := (\partial_{x_{1}}f(p), \dots , \partial_{x_{n}}f(p)) $$

Vediamo di seguito i vettori gradienti per la funzione $z(x,y)=x^2+y^2$.

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}

\begin{document}

\begin{tikzpicture}[scale=1.4]
\begin{axis}[colormap/viridis, domain=-3:3, y domain =-3:3]

\addplot3[blue, quiver={u=2*x, v=2*y, scale arrows=0.10}, 
		  samples=10, -latex] (x,y,0);

\addplot3[
	surf,
	samples=18
]
{x^2 + y^2};

\end{axis}
\end{tikzpicture}

\end{document}
```

>[!important] NOTA: PROPRIETA' DEL GRADIENTE
>Sono le stesse viste per le derivate direzionali, chiaramente.
## FORMULA DEL GRADIENTE
>[!theorem] FORMULA DEL GRADIENTE
>Sia $f$ funzione a valori reali di classe $C^1$ attorno ad un punto $p \in \mathbb{R}^n$.
>Allora $f$ ha derivate direzionali rispetto ad *ogni vettore* e vale:
>$$ \forall u \in \mathbb{R}^n \partial_{\underline{u}}f(p) = \nabla f(p)\cdot u = \partial_{x_{1}}f(p)u_{1} + \dots + \partial_{x_{n}}f(p)u_{n} $$

Per cui se la funzione è di classe $C^1$ basta conoscere come cresce o decresce lungo $n$ direzioni principali per conoscerne il tasso di crescita lungo qualunque altra direzione.
## DIREZIONI DI MASSIMA E MINIMA CRESCITA
>[!prop] DIREZIONI E TASSI DI MASSIMA E MINIMA CRESCITA
>Sia $f$ funzione di classe $C^1$ attorno ad un punto $p$ del suo dominio in $\mathbb{R}^n$.
>Se $\nabla f(p)\ne 0$, al variare dei vettori $\underline{u}$ di norma $1$, abbiamo:
>1. $\partial_{\underline{u}}f(p)$ assume il valore **massimo** per:
>   $$ \underline{u}=u_{max}=\frac{\nabla f(p)}{||\nabla f(p)||} $$
>   ed è $\partial_{u_{max}}f(p)=||\nabla f(p)||$.
>2. $\partial_{\underline{u}}f(p)$ assume il valore **minimo** per:
>   $$ \underline{u}=u_{min}=-\frac{\nabla f(p)}{||\nabla f(p)||} $$
>   ed è $\partial_{u_{min}}f(p)=-||\nabla f(p)||$.

Un fatto interessante riguardante il gradiente è, come si può vedere nella figura seguente, che è *ortogonale* agli *insiemi di livello*:

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}

\begin{document}

\begin{tikzpicture}[scale=1.4]
\begin{axis}[width=8cm,height=8cm, view = {0}{90},
			domain = -3.5:3.5, y domain = -3.5:3.5]

\addplot[mark=*] coordinates {(0,0)};

\foreach\z in{1,4,7,10,13,16,19}
    \addplot[domain=0:360,samples=73,black]
      ({sqrt(\z)*cos(\x)},{sqrt(\z)*sin(\x)});

\addplot3[red,thick, quiver={u=2*x, v=2*y, scale arrows=0.10}, 
		  samples=10, -latex] (x,y,0);

\end{axis}
\end{tikzpicture}

\end{document}
```
## REGOLA DELLA CATENA
>[!theorem] REGOLA DELLA CATENA
>Siano $\gamma:I\subset \mathbb{R}\to D$ una curva derivabile in $t_{0}$ e $f:D\to \mathbb{R}$ di classe $C^1$ in $\gamma(t_{0})$.
>La funzione composta:
>$$ t\in I\to\gamma(t)\in D\mapsto f(\gamma(t))\in \mathbb{R} $$
>è derivabile in $t_{0}$ e si ha:
>$$ (f\circ\gamma)'(t_{0}) = \nabla f(\gamma(t_{0}))\cdot\gamma'(t_{0}) $$
### APPLICAZIONE
Supponiamo di avere una funzione $f:D\subseteq \mathbb{R}^n\to \mathbb{R}$ e una curva $\gamma:I\subseteq \mathbb{R}\to D$ derivabili e di classe $C^1$.
Supponiamo inoltre che $f(\gamma(t))=c$ $\forall t\in I$.
Fissato un punto $p=\gamma(t)$, che relazione c'è fra $\nabla f(p)$ e $\gamma'(t)$?
Date tali ipotesi, abbiamo:
$$
\frac{d}{dt}f(\gamma(t)) = 0 \text{ }\forall t\in I
$$
Ma sappiamo anche:
$$
\frac{d}{dt}f(\gamma(t)) = \nabla f(\gamma(t))\cdot\gamma'(t)
$$
Per cui concludiamo che:
$$
\nabla f(\gamma(t))\cdot\gamma'(t) = 0
$$
Cioè il gradiente di $f$ e $\gamma'$ sono **ortogonali** fra loro.
Ciò implica che muovendosi ortogonalmente al gradiente si rimane su una curva di livello.
>[!prop] PROPOSIZIONE
>Il *gradiente* è **ortogonale** alle *curve di livello*. 
# SPAZI TANGENTI E DIFFERENZIABILITA'
Dato che abbiamo a che fare con funzioni di più variabili, fissato un punto del dominio di una funzione $f$, nel punto $(p,f(p))$ non vi sarà semplicemente una retta tangente, ma uno spazio di $n$ dimensioni.
>[!def] SPAZIO TANGENTE
>Sia $f$ funzione con derivate parziali in un punto $p$ di $\mathbb{R}^n$.
>Lo *spazio tangente* al grafico di $f$ in $(p,f(p))$ è lo *spazio affine* di dimensione $n$ dato da:
>$$ \big\{ (p,f(p)) + (u,\nabla f(p)\cdot u): u \in \mathbb{R}^n \big\} $$
>ovvero $\{(x=(x_{1},\dots,x_{n}),z):z=f(p)+\nabla f(p)\cdot(x-p)\}$.
## DIFFERENZIABILITA'
>[!def] FUNZIONE DIFFERENZIABILE
>Sia $f:\mathcal{U}_{p}\subset \mathbb{R}^n\to \mathbb{R}$.
>Si dice che $f$ è **differenziabile** in $p$ se:
>$$ f(x) = f(p) + \nabla f(p)\cdot(x-p) + o(|x-p|) $$

Quando la funzione è differenziabile, lo spazio tangente assume il ruolo importante di approssimazione del grafico della funzione, analogo a quello che ha la tangente al grafico di una funzione di una sola variabile.

>[!prop] PROP: LE FUNZIONI $C^1$ SONO DIFFERENZIABILI
>Sia $f$ funzione di classe $C^1$ attorno al punto $p$.
>Allora $f$ è **differenziabile** in $p$, cioè esiste $\nabla f(p)$ e vale:
>$$ f(x) = f(p) +\nabla f(p)\cdot(x-p)+o(|x-p|)$$

>[!important] NOTA
>La funzione $L(x):=f(p)+\nabla f(p)\cdot(x-p)$ è detta **linearizzazione** di $f$ in $p$.

In $\mathbb{R}^n$ la *derivabilità non implica la continuità*, mentre la differenziabilità si:
>[!prop] PROPOSIZIONE
>Una funzione **differenziabile** in un punto è ivi **continua**.

La dimostrazione è molto semplice:
>[!check] DIM.
>Se $f$ è differenziabile in $p$ si ha:
>$$ f(x) = f(p) +\nabla f(p)\cdot(x-p) +o(|x-p|) $$
>Pertanto:
>$$ \lim_{ x \to p }f(x) = f(p) + 0 = f(p) $$
>Cioè $f$ è continua in $p$.

>[!important] IMPORTANTE
>In $\mathbb{R}^n$ con $n\geq2$ la differenziabilità di una funzione implica l'esistenza di derivate direzionali, ma il viceversa non è vero in generale!
# MASSIMI E MINIMI
In modo del tutto simile alla definizione data per le funzioni di una variabile, diamo la definizione di massimo e minimo per funzioni di più variabili.
Sia $f:\mathcal{U}_{p}\subseteq \mathbb{R}^n\to \mathbb{R}$.
>[!def] MASSIMO LOCALE
>Diciamo che $f$ ha un *massimo locale* in $p$ se:
>$$ \exists\mathcal{U'}_{p}\subseteq\mathcal{U}_{p} : \forall x \in\mathcal{U'}_{p} f(x)\leq f(p) $$

In tal caso $g(t)=f(p+tu)$, $u\in \mathbb{R}^n$ (restrizione di $f$ alla retta $p+tu$) ha massimo in $t=0$.
Quando $f$ è derivabile in $p$, abbiamo $\partial_{u}f(p)=g'(0)$ per definizione, ma per il *teorema di Fermat* $g'(0)=0$ e quindi abbiamo anche:
$$
\partial_{u}f(p) = 0
$$
A questo punto, per la formula del gradiente (se questo esiste) abbiamo:
$$
0 = \partial_{u}f(p) = \nabla f(p)\cdot u
$$
E scegliendo $u=\nabla f(p)$ troviamo:
$$
0 = \nabla f(p)\cdot\nabla f(p) = ||\nabla f(p)||^2 
$$
E quindi deve essere:
$$
\nabla f(p) = \underline{0}
$$
Concludiamo quindi che, se $p$ è punto di *massimo* ed $f$ è *derivabile* in $p$, allora il gradiente in $p$ è **nullo**.

Similmente, diamo la definizione di minimo locale.
>[!def] MINIMO LOCALE
>Diciamo che $f$ ha un *minimo locale* in $p$ se:
>$$ \exists\mathcal{U'}_{p}\subseteq\mathcal{U}_{p} : \forall x \in\mathcal{U'}_{p} f(x)\geq f(p) $$

E da un ragionamento analogo al precedente segue che, se $p$ è punto di *minimo* ed $f$ è *derivabile* in $p$, allora il gradiente (se esiste) in $p$ è **nullo**.

>[!def] MASSIMI E MINIMI GLOBALI
>La loro definizione è del tutto simile a quella dei minimi e massimi *locali*, con la differenza che le disuguaglianze devono valere in tutto il dominio della funzione anzichè solo in un intorno di un punto.

Se consideriamo funzioni su insiemi chiusi e limitati vale il teorema di Weierstrass.
>[!theorem] TH. DI WEIERSTRASS
>Sia $f:D\subseteq \mathbb{R}^n\to \mathbb{R}$ continua e se $D$ è chiuso e limitato.
>Allora $f$ ammette sia **minimo** sia **massimo** in $D$.
# PUNTI STAZIONARI
Come per le funzioni di una sola variabile, definiamo i punti stazionari.
>[!def] PUNTO STAZIONARIO (o CRITICO)
>E' un punto $p \in D$ (interno a $D$) tale che $f$ sia derivabile in $p$ e in cui vale:
>$$ \nabla f(p)=\underline{0} $$

>[!theorem] TEOREMA DI FERMAT (IN PIU' VARIABILI)
>Se $f:\mathcal{U}_{p}\to \mathbb{R}$ ha un estremo locale in $p$ e se $f$ è derivabile in $p$ rispetto a $u$ si ha:
>$$ \partial_{u}f(p)=0 $$
>In particolare, se esistono tutte le derivate parziali allora è anche:
>$$ \nabla f(p)=\underline{0} $$

Come in Analisi 1, questo teorema ci dice che possiamo cercare i punti di estremo *fra i punti stazionari*.
>[!warning] ATTENZIONE
>Non tutti i punti stazionari sono punti di estremo.
>Inoltre si potrebbero avere punti di massimo o minimo in cui la funzione non è derivabile.

Esistono infatti i *punti di sella*.
>[!def] PUNTO DI SELLA
>Un punto critico $p$ di $f$ interno al dominio che non sia nè di minimo, nè di massimo locale.
>Cioè in un intorno $\mathcal{U}_{p}$ di $p$ vi sono $x_{1}$ e $x_{2}$ tali che
>$$ f(x_{1})<f(p)<f(x_{2}) $$
## CATALOGARE I PUNTI CRITICI
Possiamo catalogare i punti critici considerando i *segni* delle *derivate seconde*.
Per farlo ricorriamo alla matrice Hessiana.
>[!theorem] CRITERIO DELL'HESSIANA (nel PIANO)
>Siano $D$ aperto di $\mathbb{R}^2$, $f:D\to \mathbb{R}$ di classe $C^2$ e $p$ interno a $D$ e *critico* per $f$.
>Allora:
>1. Se $\det(\mathrm{}{H}f(p))>0$:
> 	  - Se $\partial_{xx}f(p)>0$ allora $f$ ha un *minimo locale*.
> 	  - Se $\partial_{xx}f(p)<0$ allora $f$ ha un *massimo locale*.
>2. Se $\det(\mathrm{}{H}f(p))<0$ allora $p$ è un *punto di sella*.

>[!warning] ATTENZIONE
>1. Se il determinante è nullo, non abbiamo alcuna informazione!
>2. Questo criterio è in realtà una semplificazione per le funzioni con dominio in $\mathbb{R}^2$. Il criterio "completo" ha a che fare con la *caratterizzazione della forma quadratica associata alla matrice hessiana*.
# RICERCA DEI PUNTI DI ESTREMO
Ricapitolando, possiamo dire che i punti di estremo vanno ricercati fra:
>[!important] RICERCA PUNTI ESTREMANTI
>1. Punti **stazionari**, visto il teorema di Fermat ed il criterio dell'Hessiana.
>2. Punti di **non derivabilità**, su cui Fermat non da' informazioni.
>3. **Frontiera** del dominio, in cui non è detto che la derivata si annulli.



