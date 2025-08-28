# ==SISTEMI DI PUNTI MATERIALI==
## INTRODUZIONE
Abbiamo studiato leggi ed equazioni per singoli punti materiali, ma queste possono essere generalizzate a sistemi costituiti da più di un punto materiale.

Consideriamo $N$ punti materiali con posizioni $\vec{r}_{1},\dots,\vec{r}_{n}$ e masse $m_{1},\dots,m_{n}$.
Per ciascuno di essi vale:
$$
m_{i}\vec{a}_{i} = \vec{F}_{TOT,i} \tag{1}
$$
Per semplificare lo studio di tali sistemi, introduciamo alcune quantità:
$$
M_{TOT} = M =\sum_{j=1}^N m_{j} \tag{2}
$$
$$
E_{K,TOT} = \sum_{j=1}^N E_{K,j} = \frac{1}{2}\sum_{j=1}^N m_{j}v_{j}^2 \tag{3}
$$
$$
\vec{p}_{TOT} = \sum_{j=1}^N \vec{p}_{j} = \sum_{j=1}^N m_{j}\vec{v}_{j} \tag{4}
$$
$$
\vec{L}_{TOT} = \sum_{j=1}^N\vec{L}_{j} = \sum_{j=1}^N \vec{r}_{j}\times m_{j}\vec{v}_{j} \tag{5}
$$
Cioè quantità che abbiamo già visto nel caso di un singolo punto materiale e che ora comprendono tutti i punti del sistema.
## CENTRO DI MASSA
Si definisce inoltre il **centro di massa** del sistema: un punto la cui posizione è la media pesata delle posizioni di ciascun punto. I pesi assegnati sono le masse dei punti stessi.
$$
\vec{r}_{CM} = \sum_{j=1}^N \frac{m_j}{M}\vec{r}_{j} \tag{6}
$$
Derivando tale relazione possiamo trovare anche la velocità di tale punto:
$$
\vec{v}_{CM} = \sum_{j=1}^N \frac{m_{j}}{M}\vec{v}_{j} \tag{7}
$$
Notiamo che, moltiplicando ambo i membri per $M$ in ==(7)==, otteniamo:
$$
M\vec{v}_{CM} = \sum_{j=1}^N m_{j}\vec{v}_{j} = \vec{p}_{TOT} \tag{8}
$$
Possiamo quindi immaginare che tutta quantità di moto sia concentrata nel centro di massa.
Derivando ==(8)== troviamo anche:
$$
M\vec{a}_{CM} = \sum_{j=1}^N m_{j}\vec{a}_{j} = \sum_{j=1}^N \vec{F}_{TOT,j} \tag{9}
$$
Che è la **legge di Newton per il centro di massa**.
## TEOREMA DEL MOTO DEL CENTRO DI MASSA
Tale legge può essere semplificata. Per farlo distinguiamo tra forze interne e forze esterne:
- Forze *interne*: esercitate da ciascun punto sugli altri $N-1$.
- Forze *esterne*: tutte le altre, dovute quindi ad interazioni con l'esterno.

Per esempio, sul punto $P_{1}$ avremo:
$$
\vec{F}_{TOT,1} = \underbrace{ \vec{F}_{2,1} + \vec{F}_{3,1} + \dots }_{ \text{interne} } + \vec{F}_{ext,1} \tag{10}
$$
In generale, si ha:
$$
\vec{F}_{TOT,j} = \vec{F}_{ext,j} + \sum_{k=1}^N \vec{F}_{k,j} \tag{11}
$$
Avendo definito $\vec{F}_{j,j}= \vec{0}$.
Allora ==(9)== diventa:
$$
M\vec{a}_{CM} = \sum_{j=1}^N \left( \vec{F}_{ext,j} + \sum_{k=1}^N \vec{F}_{k,j} \right) \tag{12}
$$
$$
= \sum_{j=1}^N \vec{F}_{ext,j} + \sum_{j=1,k=1}^N \vec{F}_{k,j}
$$
$$
= \sum_{j=1}^N \vec{F}_{ext,j} + \sum_{j,k=1,j<k}^N \vec{F}_{k,j} + \sum_{j,k=1,j>k}^N \vec{F}_{k,j} + \cancelto{ 0 }{ \sum_{j,k=1,j=k}^N \vec{F}_{k,j} } 
$$
Con un cambio di indice otteniamo:
$$
= \sum_{j=1}^N \vec{F}_{ext,j} + \sum_{a,b=1,a<b}^N \vec{F}_{b,a} + \sum_{a,b=1,a<b}^N \vec{F}_{a,b}
$$
$$
= \sum_{j=1}^N \vec{F}_{ext,j} + \sum_{a,b=1,a<b}^N \cancelto{ 0 }{ (\vec{F}_{b,a} + \vec{F}_{a,b}) }
$$
E concludiamo allora:
>[!info] TEOREMA DEL MOTO DEL CM
>$$M\vec{a}_{CM} = \sum_{j=1}^N \vec{F}_{ext,j} \tag{13}$$

Che è effettivamente una semplificazione di ==(9)==.

>[!warning] OSSERVAZIONE
>In assenza di forze esterne, $M\vec{a}_{CM}=\vec{0}$, per cui un sistema di riferimento solidale al C.M. è *inerziale*.

## SISTEMA DI RIFERIMENTO DEL C.M.
Dato un sistema di riferimento $O$ e uno solidale al C.M. (con assi ad orientazione fissa rispetto a quelli di $O$) si ha, detti $\vec{r}_{j}$ la posizione del punto di indice $j$ rispetto ad $O$, $\vec{r}'_{j}$ la posizione dello stesso rispetto al sistema del CM ed $\vec{r}_{CM}$ la posizione del CM rispetto ad $O$:
$$
\vec{r}_{j} = \vec{r}'_{j} + \vec{r}_{CM} \tag{14}
$$
### QUANTITA' DI MOTO
Ne consegue che:
$$
\vec{v}_{j} = \vec{v}'_{j} +\vec{v}_{CM} \tag{15}
$$
Moltiplicando ambo i membri per $m_{j}$ e sommando per $j=1,\dots,N$ si trova:
$$
\underbrace{ \vec{p}_{TOT} }_{ \text{in O} } = \underbrace{ \vec{p}'_{TOT} }_{ \text{in CM} } + \underbrace{ M\vec{v}_{CM} }_{ =\vec{p}_{TOT} } \tag{16}
$$
Ma allora $\vec{p}'_{TOT} = \vec{0}$ nel sistema del C.M.
### ENERGIA CINETICA
Sostituendo ==(15)== in ==(3)== troviamo:
$$
E_{K,TOT} = \frac{1}{2}\sum_{j=1}^N m_{j}\| \vec{v}_{j}' + \vec{v}_{CM} \| \tag{17}
$$
$$
=\frac{1}{2}\sum_{j=1}^N m_{j}\left( \| \vec{v}_{j}' \|^2 + \| \vec{v}_{CM} \|^2 + 2\vec{v}_{j}'\cdot \vec{v}_{CM} \right)
$$
$$
= E_{K,TOT}' + \frac{1}{2}M\| \vec{v}_{CM} \|^2 + \cancelto{ \vec{p}'_{TOT}=\vec{0} }{ \left( \sum_{j=1}^N  m_{j}\vec{v}_{j} \right) }\cdot \vec{v}_{CM} 
$$
E concludiamo che:
>[!info] SECONDO TEOREMA DI KONIG
>$$ E_{K,TOT} = E'_{K,TOT} + \frac{1}{2}M\| \vec{v}_{CM} \|^2 \tag{18}$$

### MOMENTO ANGOLARE
Fissato un polo $P$ e un punto $P_{j}$ abbiamo:
$$
\vec{L}_{j,P} =  (\vec{r}_{j} - \vec{r}_{P}) \times m_{j}\vec{v}_{j} \tag{19}
$$
Per cui il momento angolare totale del sistema di $N$ punti, rispetto al polo $P$, sarà:
$$
\vec{L}_{TOT,P} = \sum_{j=1}^N (\vec{r}_{j}-\vec{r}_{p})\times m_{j}\vec{v}_{j} \tag{20}
$$
Vogliamo innanzitutto generalizzare il *teorema del momento angolare* al caso con $N$ punti. Calcoliamo allora:
$$
\frac{d}{dt}\vec{L}_{TOT,P} = \frac{d}{dt}\left( \sum_{j=1}^N (\vec{r}_{j}-\vec{r}_{p})\times m_{j}\vec{v}_{j} \right) \tag{21}
$$
$$
=\sum_{j=1}^N \left( (\vec{v}_{j}-\vec{v}_{P})\times m_{j}\vec{v}_{j} \right) + \sum_{j=1}^N \left( (\vec{r}_{j}-\vec{r}_{P})\times m_{j}\vec{a}_{j} \right)
$$
Sviluppando i prodotti e con una serie di passaggi algebrici arriviamo a:
>[!info] TEOREMA DEL MOMENTO ANGOLARE PER UN SISTEMA DI PUNTI
>$$\frac{d}{dt}\vec{L}_{TOT,P}=-\vec{v}_{P}\times \vec{p}_{TOT}+\vec{M}_{ext,TOT,P} \tag{22}$$

Per cui una variazione di momento angolare in un sistema di $N$ punti è causata da un momento esterno (come nel caso già visto) e anche da un altro termine.
Questo si annulla se:
- Il polo è *fermo*, per cui $\vec{v}_{P}=\vec{0}$.
- Il polo *coincide con il CM*, per cui $\vec{v}_{P}$ coincide con $\vec{v}_{CM}$ e il loro prodotto vettoriale è nullo.
- Siamo *nel sistema di riferimento del CM*, per cui $\vec{p}_{TOT}=\vec{0}$.

>[!warning] OSSERVAZIONE
>Inoltre, se le forze sono solo interne si annulla anche il secondo termine.
>Per cui, se ci trovassimo nel sistema di riferimento del CM, avremmo:
>$$\frac{d}{dt}\vec{L}_{TOT} = \vec{0}$$

Ora vogliamo trovare la relazione fra:
- $\vec{L}_{TOT,O}$ , momento angolare in $O$ rispetto all'origine del sistema.
- $\vec{L}_{TOT,O'}$ , momento angolare nel sistema del CM, rispetto al CM.

Sostituendo ==(14)== e ==(15)== in ==(5)== troviamo:
$$
\vec{L}_{TOT,O} = \sum_{j=1}^N \bigg( ( \vec{r}'_{j} +\vec{r}_{CM} ) \times m_{j}(\vec{v}'_{j} + \vec{v}_{CM}) \bigg) \tag{23}
$$
Sviluppando i termini e con dei passaggi algebrici arriviamo a:
>[!info] PRIMO TEOREMA DI KONIG
>$$ \vec{L}_{TOT,O} = \vec{L}_{TOT,O'} + \vec{r}_{CM}\times M\vec{v}_{CM} \tag{24}$$
# ==URTI==
Un urto fra corpi è detto **elastico** se l'*energia cinetica si conserva*.
Un urto *non* elastico è detto **anaelastico** e, in particolare, *completamente anaelastico* se i corpi restano attaccati.
## URTO ELASTICO
Consideriamo due corpi di masse $m_{1},m_{2}$ che si muovono uno verso l'altro con velocità $v_{1}>0$ e $v_{2}<0$.
Dopo l'urto avranno velocità $V_{1}<0$ e $V_{2}>0$.
Essendo l'urto elastico, si conserva l'energia cinetica. Inoltre, essendo le forze impulsive interne, si conserva anche la quantità di moto:
$$
\begin{cases}
\frac{1}{2}m_{1}v_{1}^2 + \frac{1}{2}m_{2}v_{2}^2 = \frac{1}{2}m_{1}V_{1}^2 + \frac{1}{2}m_{2}V_{2}^2 \\  \\
m_{1}v_{1} + m_{2}v_{2} = m_{1}V_{1} + m_{2}V_{2}
\end{cases} \tag{25}
$$
E risolvendo il sistema troviamo le velocità dopo l'urto.
>[!warning] NOTA
>E' più semplice risolvere il sistema ponendosi nel sistema solidale al CM e poi trasformare le quantità trovate nelle corrispondenti nel sistema originale con le leggi di trasformazione sopra viste.
## URTO COMPLETAMENTE ANAELASTICO
Consideriamo ancora una volta due corpi di masse $m_{1}$ e $m_{2}$ con velocità $v_{1}$ e $v_{2}$, $v_{1}>v_{2}$.
Essendo l'urto completamente anaelastico, i due corpi procederanno dopo di esso con la stessa velocità $V$.
In questo caso l'energia cinetica *non* si conserva, ma la quantità di moto si, essendo le forze impulsive interne.
Avremo quindi:
$$
m_{1}v_{1} + m_{2}v_{2} = (m_{1}+m_{2})V \tag{26}
$$
Risolvendo per $V$ troviamo:
$$
V=\frac{m_{1}v_{1} + m_{2}v_{2}}{m_{1}+m_{2}}=V_{CM} \tag{27}
$$
L'energia dissipata nell'urto è:
$$
\Delta E_{K} = \underbrace{ E_{K} }_{ \text{prima} } - \underbrace{ \frac{1}{2}(m_{1}+m_{2})V^2 }_{ \text{dopo} } \tag{28}
$$
Se ci poniamo nel sistema del CM, $V=0$ e troviamo che l'energia dissipata è pari a quella che i due corpi avevano prima dell'urto, nel sistema del CM.
In tale riferimento, infatti, prima dell'urto i corpi si muovevano, mentre in seguito sono fermi (coincidono con il CM).







