# INDICE SEZIONE
- [ ] [[#STATI E COPPIE DI EQUILIBRIO]]
      - [[#ESEMPIO COPPIA DI EQUILIBRIO]]
      - [[#TEOREMA STATI DI EQUILIBRIO]]
- [ ] [[#CALCOLO DEGLI STATI E DELLE COPPIE]]
      - [[#TEMPO CONTINUO]]
      - [[#TEMPO DISCRETO]]
- [ ] [[#STABILITA']]
      - [[#TEOREMA STABILITA' SISTEMI LTI]]
- [ ] [[#CRITERI DI STABILITA' (TEMPO CONTINUO)]]
      - [[#CRITERIO DI ROUTH]]
      - [[#ESEMPIO APPLICAZIONE CRITERIO DI ROUTH]]
      - [[#NOTA - REGOLA DEI SEGNI DI CARTESIO]]
      - [[#NOTA - POLINOMIO DI HURWITZ]]
- [ ] [[#CRITERI DI STABILITA' (TEMPO DISCRETO)]]
      - [[#CRITERIO ALGEBRICO]]
      - [[#APPLICAZIONE DEL CRITERIO]]
      - [[#ESEMPIO APPLICAZIONE]]
# STATI E COPPIE DI EQUILIBRIO
Dato un sistema a tempo continuo o a tempo discreto, vediamo alcune definizioni utili allo studio della **stabilità del sistema**.

>[!def] STATO DI EQUILIBRIO
>Uno stato $x_{e}$ si dice **stato di equilibrio** se l'*evoluzione libera* a partire dallo *stato iniziale* $x_{e}$ è **costante** e pari a $x_{e}$.
>Cioè deve valere:
>$$ x_{l}(t) = x_{e} \text{ }\forall t\geq 0 $$
>oppure:
>$$ x_{l}(k) = x_{e} \text{ }\forall k\geq 0 $$

>[!def] COPPIA DI EQUILIBRIO
>Una **coppia** $[x_{e},u_{e}]$ viene detta coppia **di equilibrio** per il sistema se l'*andamento delle variabili di stato*, a fronte di un *andamento costante* pari a $u_{e}$ della *variabile indipendente* e ad uno *stato iniziale* $x_{e}$, è **costante** e pari a $x_{e}$.
>Cioè:
>$$ x(t) = x_{e} \text{ }\forall t\geq 0 $$
>oppure:
>$$ x(k)=x_{e} \text{ }\forall k\geq 0 $$
## ESEMPIO COPPIA DI EQUILIBRIO
Consideriamo un semplice esempio, dato da un serbatoio con una valvola di ingresso ed un tubo di scolo.

Scegliamo come variabile di *stato* $x(t)$ il livello di liquido nel serbatoio e chiamiamo l'ingresso $u(t)$.
Abbiamo quindi:
$$
\dot{x}(t) = u(t) - \alpha x(t)
$$
Si può verificare che una **coppia di equilibrio** è la seguente:
$$
[x_{e},u_{e}] = \left[ \frac{\bar{u}}{\alpha}, \bar{u} \right]
$$
Infatti:
$$
\bar{u} -\alpha\frac{\bar{u}}{\alpha} = 0 \implies \dot{x}(t) = 0
$$
L'evoluzione del sistema, peraltro, è data da:
$$
x(t) = x_{l} + x_{f}(t) = x(0)e^{ -\alpha t } + \frac{1}{\alpha}(1-e^{ -\alpha t })\bar{u}
$$
Se $x(0)=x_{e}=\frac{\bar{u}}{\alpha}$ troviamo:
$$
x(t) = \dots = \frac{\bar{u}}{\alpha} = x_{e} \text{ } \forall t\geq 0
$$
## TEOREMA STATI DI EQUILIBRIO
Vediamo il seguente teorema sugli *stati di equilibrio* di un sistema.

>[!th] TEOREMA
>Dato un *sistema LTI* (a tempo continuo o a tempo discreto):
>1. L'*insieme di tutti gli stati di equilibrio* è un **sottospazio** dello **spazio di stato**.
>2. L'**origine** dello spazio di stato è **sempre** uno **stato di equilibrio** del sistema.

Per dimostrare che l'insieme di tutti gli stati d'equilibrio è un *sottospazio* dello spazio di stato, consideriamo un *sistema lineare* a tempo continuo e *due stati di equilibrio* $x_{e}'$ e $x_{e}''$.
Abbiamo per definizione:
$$
\begin{align*}
x_{l}'(t) = x_{e}' & & x_{l}''(t) = x_{e}''
\end{align*}
$$
per qualunque valore di $t$.
Lo stato $x_{e}'''=\alpha x_{e}'+\beta x_{e}''$ è anch'esso uno *stato di equilibrio* in quanto a partire da tale stato si ha:
$$
x_{l} = \alpha x'_{l}(t) + \beta x''_{l}(t) = \alpha x'_{e} + \beta x''_{e} = x_{e}'''
$$
per la proprietà di *sovrapposizione degli effetti*.
Analoghe considerazioni valgono per i sistemi a tempo discreto.
# CALCOLO DEGLI STATI E DELLE COPPIE
## TEMPO CONTINUO
Per definizione, l'evoluzione libera a partire da uno *stato di equilibrio* rimane *costante* e *pari allo stato di equilibrio stesso*.
Cioè si ha:
$$
0 = \dot{x}_{e} = Ax_{e}
$$

>[!idea] OSSERVAZIONE IMPORTANTE
>Questo ci dice che **tutti gli stati di equilibrio** del sistema appartengono al **nucleo della matrice** $A$:
>$$ x_{e}\in \ker\{ A \} $$

Nel caso di *autovalori distinti*, si tratta quindi di calcolare, se esiste, l'autovettore corrispondente ad un eventuale *autovalore nullo*.

>[!note] NOTA
>La conseguenza di quanto visto sopra è che, se $A$ è **invertibile** ($\det A\ne 0$) allora l'**unico stato di equilibrio** risulta essere l'**origine** e viceversa:
>$$ \{ x_{e} \} = \{ \underline{0} \} \Leftrightarrow \det A \ne 0 $$

D'altronde, se $A$ è invertibile, essa *non ha alcun autovalore nullo* e quindi i *modi naturali* del sistema sono *convergenti, divergenti o periodici*: non è possibile trovare uno stato iniziale non nullo a partire dal quale l'evoluzione libera rimanga costante.

Passiamo ora alle **coppie di equilibrio**.
Data la di coppia di equilibrio $[x_{e},u_{e}]$, per definizione deve valere:
$$
\dot{x}_{e} = Ax_{e} + Bu_{e} = 0
$$
>[!note] NOTA
>Se la matrice $A$ è **invertibile**, allora per ogni valore assegnato $u_{e}$ esiste **una sola coppia di equilibrio** $[x_{e},u_{e}]$.

In tal caso, lo *stato di equilibrio* $x_{e}$ può essere calcolato risolvendo esplicitamente:
$$
x_{e} = -A^{-1}Bu_{e}
$$
## TEMPO DISCRETO
In tempo discreto, dato uno *stato di equilibrio* $x_{e}$ abbiamo:
$$
x(k+1) = x(k) = x_{e} = Ax_{e} \text{ }\forall k\geq 0
$$
Cioè si ha:
$$
\begin{align*}
Ax_{e} &= 1x_{e} \\ \\
x_{e}[I-A] &= 0
\end{align*}
$$

>[!note] NOTA
>Lo *stato di equilibrio* appartiene al **nucleo** della matrice $I-A$:
>$$ x_{e}\in \ker\{ I-A \} $$

Vediamo quali sono le conseguenze:
1. Se la matrice $A$ ha come **autovalore** $\lambda=1$ vogliamo calcolare l'*autovettore corrispondente* $v_{1}$ e scegliere uno *stato iniziale* $x_{e}$ appartenente al sottospazio generato da $v_{1}$ ($x_{e}\in \langle v_{1} \rangle$): in tal caso l'evoluzione *rimmarà costante* in quanto il *modo naturale* associato è *costante*.
2. Se $A$ *non ha autovalore 1*, allora $\det(I-A)\ne 0$ e quindi $I-A$ è **invertibile**. Ma allora $\ker\{ I-A \}$ ha **dimensione 0**: l'**unico stato di equilibrio** possibile è l'**origine**.

Passando alle **coppie di equilibrio**, abbiamo che la coppia $[x_{e},u_{e}]$ deve soddisfare:
$$
x_{e} = Ax_{e} + Bu_{e}
$$
Se la matrice $I-A$ è **invertibile**, allora per ogni valore assegnato $u_{e}$ esiste **una sola coppia di equilibrio**, il cui stato può essere calcolato risolvendo:
$$
x_{e} = (I-A)^{-1}Bu_{e}
$$
# STABILITA'
Di grande *interesse applicativo* è capire se uno *stato* (o la *coppia*) di equilibrio sia **stabile**, cioè se *piccole deviazioni* da tale condizione generino evoluzioni che *non si allontanano sensibilmente dall'equilibrio*.

Esistono inoltre dei casi in cui il sistema può anche ritornare spontaneamente all'equilibrio.

>[!note] NOTA
>La stabilità è quindi una **proprietà dinamica**, perchè dipende da *come evolve lo stato nel tempo* a partire da *condizioni iniziali prossime all'equilibrio*.

Per determinare la stabilità di un sistema si utilizza la definizione data da Lyapunov, che è la seguente:

>[!def] STABILITA' (LYAPUNOV)
>Lo *stato di equilibrio* $x_{e}$ si dice **stabile** se *ogni traiettoria* che *parte sufficientemente vicina* a $x_{e}$, **rimane** *sufficientemente* **vicina** a $x_{e}$.

>[!def] INSTABILITA'
>Un punto $x_{e}$ di *equilibrio* è **instabile** se *non è stabile*, cioè se esistono traiettorie che, pur partendo *arbitrariamente vicine* a $x_{e}$, si **allontanano da esso**.

Questo può significare, per esempio, che vale:
$$
\begin{align*}
\exists x_{0} &: \lim_{ t \to \infty } |x_{l}(t)| = \infty \\ \\
\exists x_{0} &: \lim_{ t \to \infty } |x_{l}(k)| = \infty
\end{align*}
$$
La definizione di stabilità data sopra è piuttosto generale; possiamo infatti distinguere *due casi più precisi*:
- Stabilità **asintotica**.
- Stabilità **marginale**.

Un sistema *stabile* potrebbe rimanere *vicino all'equilibrio*, ma *non necessariamente ritornarci*. 
Se il sistema non solo *rimane in un intorno* di $x_{e}$, ma vi **converge** anche, viene detto *asintoticamente stabile*.

>[!def] STABILITA' ASINTOTICA
>Lo stato di *equilibrio* $x_{e}$ è **asintoticamente stabile** se è *stabile* e, inoltre, l'*evoluzione libera* **converge** a $x_{e}$ nel lungo periodo per *ogni stato iniziale* $x_{0}$ *sufficientemente vicino* a $x_{e}$.
>Cioè:
>$$ \lim_{ t \to \infty } x_{l}(t) = x_{e} $$
>oppure:
>$$ \lim_{ k \to \infty } x_{l}(k) = x_{e} $$

La *stabilità marginale* copre i casi rimanenti, cioè quelli in cui il sistema *rimane confinato* in un *intorno* di $x_{e}$, senza necessariamente ritornare ad $x_{e}$ stesso.

>[!def] STABILITA' MARGINALE
>Un punto di *equilibrio* $x_{e}$ è **marginalmente stabile** se *non è nè asintoticamente stabile*, *nè instabile*.
## TEOREMA STABILITA' SISTEMI LTI
Vediamo di seguito un teorema molto utile per lo *studio della stabilità* dei sistemi.

>[!th] TEOREMA
>Dato un *sistema LTI* a *tempo continuo* o a *tempo discreto*, **tutti gli stati** di equilibrio e **tutte le coppie** di equilibrio hanno la **stessa proprietà di stabilità** dell'**origine dello spazio di stato**.

Questo teorema ci permette quindi, per i sistemi lineari, di *studiare la stabilità di tutte le
coppie d’equilibrio* sulla base del **solo studio delle proprietà di stabilità dell’origine** dello spazio di stato, che è sempre uno stato d’equilibrio (vedi > [[#TEOREMA STATI DI EQUILIBRIO]]).

Inoltre, come conseguenza di questo teorema, possiamo definire un sistema
*asintoticamente stabile*, *instabile* o *marginalmente stabile* nel modo seguente:

>[!def] STABILITA' DI UN SISTEMA
>Un sistema lineare si dice **asintoticamente stabile**, **instabile** o **marginalmente stabile** se l’*origine dello spazio di stato* è uno *stato di equilibrio*, rispettivamente, *asintoticamente stabile*, *instabile* o *marginalmente stabile*.
# CRITERI DI STABILITA' (TEMPO CONTINUO)
Consideriamo l'*evoluzione libera* di un sistema a *tempo continuo*:
$$
\begin{align*}
\dot{x}_{l}(t) = Ax_{l}(t) & & x(0) = x_{0}
\end{align*}
$$
Assumiamo che $A$ abbia **autovalori distinti** (reali o complessi coniugati) e che l'*origine sia uno stato di equilibrio*.
1. Poiché l’origine dello spazio di stato è uno *stato di equilibrio*, essa è **asintoticamente stabile** *se e solo se l’evoluzione libera converge a zero per ogni stato iniziale*. 
   Questo accade **se e solo se tutti i modi naturali sono convergenti**, cioè *se e solo se tutti gli autovalori di $A$ hanno parte reale negativa*.
2. L’origine è **instabile** se *esiste almeno uno stato iniziale per cui l’evoluzione libera non converge a zero* ed è divergente.
   Nell'ipotesi assunta, ciò è equivalente a dire che esiste **almeno un modo naturale divergente**, cioè che almeno un autovalore di $A$ ha parte reale positiva.
3. L’origine è **marginalmente stabile** se *tutti gli autovalori di $A$ hanno parte reale non positiva* e **almeno uno ha parte reale nulla**.

Possiamo riassumere tutto ciò nel seguente teorema:

>[!th] TEOREMA
>Un sistema LTI a *tempo continuo* è:
>1. **Asintoticamente stabile** se e solo se $\mathrm{Re}(\lambda_{i})<0$ per ogni autovalore $\lambda_{i}$ di $A$.
>2. **Instabile** se e solo se esiste almeno un autovalore $\lambda_{i}$ della matrice $A$ tale che $\mathrm{Re}(\lambda_{i})>0$.
>3. **Marginalmente stabile** se e solo se $\mathrm{Re}(\lambda_{i})\leq 0$ per ogni autovalore della matrice $A$ ed *esiste almeno un autovalore* $\lambda_{i}$ tale che $\mathrm{Re}(\lambda_{i})=0$.

>[!warning] ATTENZIONE
>Sotto l'**ipotesi di autovalori distinti**, gli eventuali autovalori sull'asse immaginario sono *semplici*. Allora, in tal caso, la condizione del punto (3) è *necessaria e sufficiente*.
>
>In generale, per gli autovalori sull'*asse immaginario*, la stabilità **dipende anche dalla molteplicità**: se un autovalore con $\mathrm{Re}(\lambda_{i})=0$ ha *molteplicità geometrica minore della molteplicità algebrica*, nella risposta libera compaiono termini del tipo $te^{ j\omega t }, t^2e^{ j\omega t }\dots$ e il sistema è **instabile**.
## CRITERIO DI ROUTH
Il *calcolo di tutti gli autovalori della matrice* $A$ (cioè delle *radici del suo polinomio caratteristico*) richiede la soluzione di un’equazione polinomiale di grado $n$ e pertanto, in generale, **non è possibile trovare la soluzione esplicita**.

>[!idea] OSSERVAZIONE IMPORTANTE
>La *stabilità* è legata al **segno della parte reale degli autovalori**: è quindi *sufficiente stabilire se questi abbiano o meno tutti parte reale negativa* senza dover calcolare esplicitamente il loro valore.

Per farlo, possiamo sfruttare il **criterio di Routh** applicato al polinomio caratteristico della matrice $A$:
$$
p(\lambda) = a_{n}\lambda^n + a_{n-1}\lambda^{n-1} + \ldots + a_{1}\lambda + a_{0}
$$
>[!note] NOTE
>1. Consideriamo il polinomio con $a_{n}>0$. In caso contrario, basta moltiplicare $p(\lambda)$ per $-1$.
>2. Un polinomio si dice **di Hurwitz** quando tutte le sue radici hanno *parte reale strettamente negativa*.

Per utilizzare il criterio di Routh, costruiamo la seguente tabella, composta da $n+1$ righe:
$$
\begin{matrix}
\text{RIGA} &  \\
n  & a_{n} & a_{n-2} & a_{n-4} & \dots \\
n-1 & a_{n-1} & a_{n-3} & a_{n-5} & \dots \\
n-2 & b_{n-2} & b_{n-4} & \dots \\
n-3 & c_{n-3} & c_{n-5} & \dots \\
\vdots 
\end{matrix}
$$
Dove i coefficienti $b_{n-k}$ e $c_{n-k}$ sono dati da:
$$
\begin{align*}
b_{n-k} &= -\frac{\det \begin{bmatrix}
a_{n} & a_{n-k}  \\
a_{n-1} & a_{n-k-1}
\end{bmatrix}}{a_{n-1}} \\ \\
c_{n-k} &= -\frac{\det \begin{bmatrix}
b_{n} & b_{n-k}  \\
b_{n-1} & b_{n-k-1}
\end{bmatrix}}{b_{n-2}}
\end{align*}
$$

Consideriamo per esempio un polinomio di quarto grado:
$$
p(\lambda) = a_{4}\lambda^4 + a_{3}\lambda^3 + a_{2}\lambda^2 + a_{1}\lambda + a_{0}
$$
La **tabella di Routh** risultante è la seguente:

$$
\begin{matrix}
4 & | & a_{4} & a_{2} & a_{0} \\ \\
3 & | & a_{3} & a_{1} & 0 \\ \\
2 & | & \frac{-\det \begin{bmatrix}
a_{4} & a_{2} \\
a_{3} & a_{1}
\end{bmatrix}}{a_{3}} = b_{1} & \frac{-\det \begin{bmatrix}
a_{4} & a_{0} \\
a_{3} & 0
\end{bmatrix}}{a_{3}} = b_{2} & \frac{-\det \begin{bmatrix}
a_{4} & 0 \\
a_{3} & 0
\end{bmatrix}}{a_{3}} = 0 \\ \\
1 & | & \frac{-\det \begin{bmatrix}
a_{3} & a_{1} \\
b_{1} & b_{2}
\end{bmatrix}}{b_{1}} = c_{1} & \frac{-\det \begin{bmatrix}
a_{3} & 0 \\
b_{1} & 0
\end{bmatrix}}{b_{1}} = 0 & \frac{-\det \begin{bmatrix}
a_{3} & 0 \\
b_{1} & 0
\end{bmatrix}}{b_{1}} = 0 \\ \\
0 & | & \frac{-\det \begin{bmatrix}
b_{1} & b_{2} \\
c_{1} & 0
\end{bmatrix}}{c_{1}} = d_{1} & \frac{-\det \begin{bmatrix}
b_{1} & 0 \\
c_{1} & 0
\end{bmatrix}}{c_{1}} = 0 & \frac{-\det \begin{bmatrix}
b_{1} & 0 \\
b_{1} & 0
\end{bmatrix}}{c_{1}} = 0
\end{matrix}
$$

>[!th] CRITERIO DI ROUTH
>Dato un polinomio (con $a_{n}>0$), *tutte le sue radici* hanno **parte reale negativa** *se e solo se* **tutti gli elementi della prima colonna** della *tabella di Routh* sono **strettamente positivi**.
>
>Inoltre, dalla presenza di una *variazione di segno* nella prima colonna segue la *presenza di radici con parte reale positiva*.

Vediamo ora alcune note sul criterio di Routh:

>[!idea] OSSERVAZIONI IMPORTANTI
>1. Se $a_{n}>0$ e i *coefficienti del polinomio* **non sono tutti strettamente positivi**, allora il polinomio **non può avere tutte le radici a parte reale negativa**.
>2. Se durante la costruzione compare uno **zero** nel *primo elemento di una riga* oppure una *riga interamente nulla* si presenta un **caso speciale** della tabella di Routh e la procedura standard deve essere modificata.
## ESEMPIO APPLICAZIONE CRITERIO DI ROUTH
Vediamo come applicare il criterio di Routh ad una matrice di esempio:
$$
A = \begin{bmatrix}
0 & 1 & 0 \\
0 & 0 & 1 \\
-8 & -2 & -5
\end{bmatrix}
$$
Innanzitutto, dobbiamo determinare il *polinomio caratteristico* della matrice.
Nel caso in esempio, siccome la matrice $A$ è in *forma compagna*, possiamo ottenerlo semplicemente leggendo l'*ultima riga della matrice*:
$$
p(\lambda) = \det(\lambda I-A) = 8 +2\lambda +5\lambda^2 + \lambda^3
$$

>[!idea] MATRICI COMPAGNE
>La **matrice compagna** del *polinomio monico* di grado $n$:
>$$ P(x) = c_{0} + c_{1}x + \dots + c_{n-1}x^{n-1} + x^n $$
>è una *matrice quadrata* avente $1$ sulla *prima sovradiagonale* e i *coefficienti di P cambiati di segno* sull'ultima riga:
>$$ C_{P} = \begin{bmatrix} 0 & 1 & 0 & \dots & 0 \\ \vdots & \ddots & 1 & \ddots & \vdots \\ \vdots &  & \ddots & \ddots & 0 \\ 0 & \dots & \dots & 0 & 1 \\ -c_{0} & -c_{1} & \dots & \dots & -c_{n-1} \end{bmatrix} $$

Per cui la tabella di Routh sarà:
$$
\begin{matrix}
3 & | & 1 & 2 \\
2 & | & 5 & 8 \\
1 & | & \frac{5\cdot2-1\cdot8}{5} = \frac{2}{5} & 0 \\
0 & | & \frac{5\cdot0-\frac{2}{5}\cdot 8}{\frac{2}{5}}=8 & 0
\end{matrix}
$$
Gli *elementi della prima colonna* sono *tutti positivi* e quindi il polinomio *non ha radici con parte reale positiva*. 
Quindi il polinomio è un *polinomio di Hurwitz*. In conclusione, il sistema risulta **asintoticamente stabile**.
## NOTA - REGOLA DEI SEGNI DI CARTESIO
Oltre al criterio di Routh, si potrebbe utilizzare anche la *regola dei segni* di Cartesio, che presenta però una limitazione:

>[!warning] LIMITAZIONE
>Riguarda **solo le radici reali** di un polinomio.

>[!th] REGOLA DEI SEGNI di CARTESIO
>Dato il *polinomio a coefficienti reali* di grado $n$:
>$$ p(\lambda) = a_{n}\lambda^n +a_{n-1}\lambda^{n-1} + \ldots + a_{1}\lambda +a_{0} $$
>Il **numero di radici reali positive**, contate con la loro *molteplicità*, è uguale al **numero di variazioni di segno** fra **coefficienti consecutivi non nulli**, oppure è *minore di tale numero di un intero pari*.

Consideriamo il seguente esempio:
$$
p(\lambda) = \lambda^3 + \lambda^2 -\lambda -1
$$
Abbiamo *una sola variazione di segno* fra i coefficienti del polinomio: allora *esiste una sola radice reale positiva*.
Infatti:
$$
p(\lambda) = \lambda^3 + \lambda^2 -\lambda-1 = (\lambda+1)^2(\lambda-1)
$$
Consideriamo ora un altro esempio:
$$
p(\lambda) = \lambda^4 -\lambda^2 +1
$$
Abbiamo *due variazioni di segno*, per cui il *numero di radici reali positive* è **2 oppure 0** (siccome il polinomio ha grado pari, non può avere una sola radice reale).
Per capire quale dei casi si verifica, poniamo $y:=\lambda^2$:
$$
p(y) = y^2 -y +1 = \left( y-\frac{1}{2} \right)^2 + \frac{3}{4} >0 \text{ }\forall y\in \mathbb{R}
$$
Di conseguenza, *non esistono radici reali positive*.
## NOTA - POLINOMIO DI HURWITZ
Vediamo alcune note e osservazioni sui *polinomi di Hurwitz*.

>[!note] NOTA
>Una condizione **necessaria** (in generale *non sufficiente*) affinchè $p(\lambda)$ si *di Hurwitz* è che, dopo normalizzazione con *coefficiente principale positivo*, **tutti i coefficienti siano strettamente positivi**.
>
>**NOTA**: Nel caso in cui il polinomio sia di **secondo grado**, la condizione è *anche sufficiente*.

Consideriamo il seguente esempio:
$$
p(\lambda) = \lambda^3 + \lambda^2 + 2\lambda + 8
$$
La tabella di Routh risulta essere:
$$
\begin{matrix}
3 & | & 1 & 2 \\
2 & | & 1 & 8 \\
1 & | & -6 & 0 \\
0 & | & 8 & 0
\end{matrix}
$$
I *coefficienti* sono *tutti positivi*, tuttavia nella tabella di Routh ci sono **due variazioni di segno**, quindi il polinomio ha *due radici* nel *semipiano destro*.

>[!th] COROLLARIO CRITERIO DI ROUTH
>Se nella costruzione della *tabella di Routh* **non compaiono zeri** nella **prima colonna**, e non compaiono **righe interamente nulle**, allora:
>1. **Nessuna radice** di $p(\lambda)$ ha **parte reale nulla**.
>2. Il *numero di radici* di $p(\lambda)$ con *parte reale strettamente positiva* è *uguale al numero di variazioni di segno nella prima colonna* della tabella.

Se invece costruendo la tabella di Routh dovesse comparire uno *zero nel primo elemento di una riga* oppure una *riga di tutti zeri*, la costruzione richiede una **procedura speciale**. 
In tal caso il polinomio *non è Hurwitz*, quindi esiste *almeno una radice* con *parte reale non negativa* (**nulla o positiva**).

### CASO 1: ZERO NELLA PRIMA COLONNA
Consideriamo il seguente polinomio:
$$
p(\lambda) = \lambda^3 -3\lambda +2 = \lambda^3 +0\lambda^2 -3\lambda +2
$$
Le prime due righe della tabella risultano quindi essere:
$$
\begin{matrix}
3 & | & 1 & -3 \\
2 & | & 0 & 2
\end{matrix}
$$
Siccome per costruire la prossima riga dovremmo *dividere per 0*, vediamo come ovviare a questo problema: consideriamo $\varepsilon>0$
$$
\begin{align*}
\lim_{ \varepsilon \to 0^+ } & & \begin{matrix}
3 & | & 1 & -3 \\
2 & | & \varepsilon & 2 \\
1 & | & \frac{-3\varepsilon-2}{\varepsilon} & 0 \\
0 & | & 2
\end{matrix} & &= \begin{matrix}
3 & | & 1 & -3 \\
2 & | & 0^+ & 2 \\
1 & | & -\infty & 0 \\
0 & | & 2
\end{matrix}
\end{align*}
$$

>[!note] CONCLUSIONE
>Notiamo che ci sono **due variazioni di segno**, per cui *due radici nel semipiano destro* ed il sistema è **instabile**.
### CASO 2: RIGA CON TUTTI ZERI
>[!note] NOTA
>Questo si verifica sempre per una **riga dispari** della tabella.

Consideriamo il seguente polinomio:
$$
p(\lambda) = \lambda^4 + \lambda^3 -3\lambda^2 -\lambda +2
$$
Costruiamo la tabella di Routh:
$$
\begin{matrix}
4 & | & 1 & -3 & 2 \\
3 & | & 1 & -1 & 0 \\
2 & | & -2 & 2 & 0 \\
1 & | & 0 & 0 & 0 \\
0 & | & \dots
\end{matrix}
$$
Come procediamo a questo punto?
Seguiamo il seguente algoritmo:

>[!tldr] PROCEDIMENTO
>1. Si considera un *polinomio ausiliario* con i *coefficienti della riga immediatamente precedente* quella degli zeri.
>2. Si calcola la *derivata* del polinomio ausiliario.
>3. Si *sostituisce la riga di zeri* con i *coefficienti* del *polinomio ausiliario derivato*.
>4. Si *prosegue* nella *costruzione della tabella*.

>[!note] NOTA
>Il polinomio ausiliario è fatto da **potenze** che **scendono a gradini di due** (infatti la tabella presenta una riga di zeri se e solo se il sistema ha *radici speculari rispetto all'origine*).

Nel nostro caso il *polinomio ausiliario* è:
$$
\mathcal{A}(\lambda) = -2\lambda^2 +2
$$
Derivandolo troviamo:
$$
\frac{d\mathcal{A}}{d\lambda} = -4\lambda
$$
Sostituiamo la riga di zeri con $[-4,0,0]$ e completiamo la tabella:
$$
\begin{matrix}
4 & | & 1 & -3 & 2 \\
3 & | & 1 & -1 & 0 \\
2 & | & -2 & 2 & 0 \\
1 & | & -4 & 0 & 0 \\
0 & | & 2 & 0 & 0
\end{matrix}
$$
>[!note] CONCLUSIONE
>La prima colonna presenta **due variazioni di segno**, per cui il polinomio ha *due radici nel semipiano destro*: il sistema è **instabile**.

>[!idea] OSSERVAZIONE IMPORTANTE
>La riga nulla segnala *radici simmetriche rispetto all'origine*.
>Per cui se la tabella di Routh *non presenta variazioni di segno* nella prima colonna, *non vi sono radici nel semipiano destro*; dunque tali radici simmetriche devono **trovarsi sull'asse immaginario** (e quindi hanno *parte reale nulla*).
# CRITERI DI STABILITA' (TEMPO DISCRETO)
Vediamo ora come determinare se un sistema LTI a *tempo discreto* è *stabile oppure no*.
Consideriamo il sistema:
$$
x(k+1) = Ax(k)
$$

Anche in questo caso, l'**origine** dello spazio di stato è uno **stato di equilibrio**.
Inoltre, similmente a quanto già visto per i sistemi a tempo continuo, abbiamo che:
1. L'origine è **asintoticamente stabile** se e solo se l'evoluzione libera *converge a zero per qualunque condizione iniziale*. Equivalentemente, *tutti i modi naturali* del sistema sono *convergenti*. Questo accade se e solo se *tutti gli autovalori* della matrice $A$ hanno *modulo strettamente minore di 1*.
2. L'origine è **instabile** se *esiste almeno una condizione iniziale* per cui *l'evoluzione libera non rimane limitata*. Ciò accade quando *almeno un autovalore* di $A$ ha *modulo maggiore di uno* (oppure quando un autovalore di modulo unitario *non ha un numero sufficiente di autovettori indipendenti*, generando *termini crescenti nel tempo* del tipo $k^r\lambda_{i}^k$).
3. L'origine è **marginalmente stabile** se l'evoluzione libera *rimane limitata per ogni condizione iniziale*, ma *non converge necessariamente a zero*. Ciò accade quando *tutti gli autovalori* di $A$ hanno *modulo minore o uguale a uno*, *almeno uno ha modulo* pari a *esattamente 1*, e gli autovalori di modulo unitario hanno un *numero sufficiente di autovettori indipendenti*.

>[!note] NOTA
>La differenza con i sistemi a tempo continuo sta nel fatto che, anzichè valutare se la *parte reale è non positiva*, siamo interessati a valutare *se il modulo dell'autovalore è* **minore di uno**.

Infatti nel caso di sistemi dinamici LTI a *tempo discreto* la **regione di stabilità** è rappresentata dal **cerchio unitario aperto**, la cui frontiera può essere parametrizzata come segue:
$$
z = e^{ j\theta } = \cos\theta + j\sin\theta \text{ , }\theta \in[0,2\pi)
$$
Riassumendo:

>[!th] TEOREMA
>Un sistema LTI a *tempo discreto* è:
>1. **Asintoticamente stabile** se e solo se per tutti gli autovalori $\lambda_{i}$ della matrice $A$ vale $|\lambda_{i}|<1$.
>2. **Instabile** se e solo se *almeno un autovalore* ha *modulo maggiore di uno* ($\exists i: |\lambda_{i}|>1$), oppure un autovalore unitario ha *molteplicità geometrica minore di quella algebrica* ($\exists i:|\lambda_{i}(A)|=1$).
>3. **Marginalmente stabile** se e solo se per tutti gli autovalori di $A$ vale $|\lambda_{i}|\leq 1$, esiste *almeno un autovalore* $\lambda_{a}$ per cui si ha $|\lambda_{a}|=1$, e gli autovalori di modulo unitario *non generano termini crescenti nel tempo*.
## CRITERIO ALGEBRICO
Presentiamo, anche in questo caso, un **criterio algebrico** per la *verifica della stabilità* di sistemi dinamici a tempo discreto.

Si tratta di una procedura di *analisi polinomiale* che ci consente di *determinare se un polinomio assegnato abbia tutte le radici strettamente interne al disco unitario*, cioè:
$$
|\lambda_{i}|<1 \text{ }\forall i
$$
>[!def] POLINOMIO SCHUR-STABILE
>Dato il polinomio:
>$$ p(\lambda) = a_{n}\lambda^n + a_{n-1}\lambda^{n-1} + \dots + a_{1}\lambda + a_{0} $$
>si dice che esso è **Schur-stabile** se **tutte le sue radici** appartengono al **disco unitario aperto**.

Per *capire se un polinomio è Schur-stabile* usiamo una **trasformazione bilineare** per *ricondurre il problema* discreto nel disco unitario ad un *problema continuo nel semipiano sinistro*, dove possiamo applicare il *criterio di Routh*.

>[!idea] TRASFORMAZIONE BILINEARE (METODO DI TUSTIN)
>E' utilizzata nello studio dei sistemi di controllo per *trasformare* **sistemi a tempo continuo** in **sistemi a tempo discreto** e *viceversa*.

La trasformazione in questione è la seguente:
$$
\begin{align*}
\mathbb{C} &\to \mathbb{C} \\ \\
\lambda &\mapsto \frac{1+w}{1-w}
\end{align*}
$$
E si dimostra facilmente che la *trasformazione inversa* è:
$$
w = \frac{\lambda-1}{\lambda+1}
$$
Con $\lambda$ indichiamo un *autovalore associato ad un sistema discreto*, mentre con $w$ indichiamo un *autovalore associato ad un sistema continuo*: la trasformazione bilineare vista sopra è una **mappa tra i due piani**.
In particolare, stabilisce una **corrispondenza tra regioni** nei due piani:
1. Regione di **convergenza**: l'*interno del cerchio unitario* viene mappato nel *semipiano sinistro* e viceversa. Per cui $|\lambda|<1\Leftrightarrow\mathrm{Re}\{ w \}<0$.
2. Regione **limite**: la *circonferenza unitaria* viene mappata nell'*asse immaginario* e viceversa.
   Per cui: $|\lambda|=1\Leftrightarrow\mathrm{Re}\{ w \}=0$.
3. Regione di **divergenza**: l'*esterno del cerchio unitario* viene mappato nel *semipiano destro* e viceversa. Per cui: $|\lambda|>1\Leftrightarrow\mathrm{Re}\{ w \}>0$.

>[!note] OSSERVAZIONE
>Dalla definizione della trasformazione, notiamo anche che:
>1. $\lambda=1$ viene mappato in $w=0$.
>2. $\lambda=0$ viene mappato in $w=-1$.
>3. Se $\lambda\to-1$, allora $w\to \infty$ (sia $+\infty$ che $-\infty$, dipende se $-1^+$ o $-1^-$).
>4. Se $\lambda\to +\infty$, allora $w\to1$.
## APPLICAZIONE DEL CRITERIO
Dato il seguente polinomio da analizzare:
$$
p(\lambda) = a_{n}\lambda^n + a_{n-1}\lambda^{n-1} + \dots + a_{1}\lambda + a_{0}
$$
Si *applica la trasformazione bilineare* per ottenere:
$$
p\left( \frac{1+w}{1-w} \right) = a_{n} \left( \frac{1+w}{1-w} \right)^{n} + a_{n-1}\left( \frac{1+w}{1-w} \right)^{n-1} + \dots + a_{1}\left( \frac{1+w}{1-w} \right) + a_{0}
$$
Il polinomio nella variabile $\frac{1+w}{1-w}$ può essere scritto nella seguente forma:
$$
p\left( \frac{1+w}{1-w} \right) = \frac{q(w)}{(1-w)^n}
$$
Possiamo così ricavare il polinomio $q(w)$ come segue:
$$
q(w) = (1-w)^n p\left( \frac{1+w}{1-w} \right)
$$
Arrivati a questo punto, possiamo applicare il seguente teorema:

>[!th] TEOREMA
>Le seguenti *proposizioni* sono *equivalenti*:
>1. $p(\lambda)$ è *Schur-stabile*.
>2. $q(w)$ è *Hurwitz-stabile*.
>3. $\mathrm{Re}(w_{i})<0$ $\forall i$.

>[!idea] APPLICAZIONE DEL CRITERIO
>Dopo aver ottenuto $p(w)$ possiamo allora applicare il **criterio di Routh**: $p(\lambda)$ avrà lo *stesso carattere di stabilità* di $q(w)$.

>[!warning] ATTENZIONE
>1. Se $p(1)=0$ oppure $p(-1)=0$ il polinomio ha *radici sul cerchio* unitario e quindi *non è Schur-stabile*. Questi sono casi *immediatamente verificabili*, per cui *non è necessario* applicare il criterio (che richiederebbe più tempo).
>2. Nel caso $\lambda=-1$ la *trasformazione bilineare* presenta una **singolarità** e il metodo **non è applicabile direttamente**.
## ESEMPIO APPLICAZIONE
Consideriamo il seguente polinomio:
$$
p(\lambda) = \lambda^3 + 2\lambda^2 + \lambda + 1
$$
Applichiamo la trasformazione:
$$
p\left( \frac{1+w}{1-w} \right) = \left( \frac{1+w}{1-w} \right)^3 + 2\left( \frac{1+w}{1-w} \right)^2 + \left( \frac{1+w}{1-w} \right) + 1
$$
Ricaviamo a questo punto $q(w)$:
$$
\begin{align*}
q(w) &= (1-w)^3 p\left( \frac{1+w}{1-w} \right) \\ \\
&= (1+w)^3 +2(1-w)(1+w)^2 + (1-w)^2(1+w) + (1-w)^3 \\ \\
&= \dots \\ \\
&= w^3 - 3w^2 - w -5
\end{align*}
$$
Ora possiamo applicare il criterio di Routh a $q(w)$:
$$
\begin{matrix}
3 & | & 1 & -1 \\
2 & | & -3 & -5 \\
1 & | & -\frac{8}{3} & 0 \\
0 & | & -5 & 0
\end{matrix}
$$
>[!note] CONCLUSIONE
>C'è *una variazione di segno* nella prima colonna della tabella, per cui $q(w)$ ha *una radice a parte reale positiva*. Pertanto $q(w)$ *non è Hurwitz* e, di conseguenza, $p(\lambda)$ **non è Schur-stabile**.
>
>**NOTA**: Si poteva dedurre subito che $q(w)$ non è Hurwitz; infatti *condizione necessaria* affinché un polinomio con coefficiente principale positivo *sia Hurwitz* è che *tutti i suoi coefficienti siano strettamente positivi*.
