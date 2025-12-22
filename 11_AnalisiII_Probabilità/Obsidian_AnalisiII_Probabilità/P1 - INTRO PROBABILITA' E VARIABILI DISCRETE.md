# INDICE SEZIONE
- [ ] [[#INTRODUZIONE]]
      - [[#PROPRIETA']]
      - [[#CONTINUITA' DELLA PROBABILITA']]
- [ ] [[#PROBABILITA' CONDIZIONATA]]
      - [[#DEFINIZIONE]]
      - [[#CASO GENERALE]]
      - [[#PROBABILITA' TOTALE]]
      - [[#PROBABILITA' TOTALE]]
      - [[#TEOREMA DI BAYES]]
- [ ] [[#EVENTI INDIPENDENTI]]
      - [[#INDIPENDENZA CONDIZIONATA]]
- [ ] [[#VARIABILI ALEATORIE DISCRETE]]
      - [[#DEFINIZIONE GENERALE]]
      - [[#DENSITA']]
      - [[#DISTRIBUZIONE]]
      - [[#VALORE ATTESO]]
      - [[#VALORE ATTESO DI COMPOSTE]]
      - [[#VARIANZA]]
      - [[#COVARIANZA]]
      - [[#MEDIA E VARIANZA CONDIZIONATA]]
# INTRODUZIONE
>[!def] PROBABILITA' UNIFORME
>Dato un *insieme finito* $\Omega$ (detto **spazio campionario**), chiamiamo **probabilità uniforme** su $\Omega$ la *funzione*:
>$$ P: \mathcal{P}(\Omega) \to [0,1] \text{ , } P(A) = \frac{|A|}{|\Omega|} $$
>Dove $\mathcal{P}(\Omega)$ sono i *sottoinsiemi* di $\Omega$.

>[!def] DEF.
>Chiamiamo **eventi elementari** i *singoli elementi* di $\Omega$, **eventi** i *sottoinsiemi* di $\Omega$.

>[!prop] EQUIPROBABILITA' DEGLI EVENTI ELEMENTARI
>Se $P:\mathcal{P(\Omega)}\to[0,1]$ è *uniforme* su $\Omega$ si ha:
>$$ P(\{ \omega \}) = \frac{1}{|\Omega|} $$
## PROPRIETA'
Dalla definizione di probabilità uniforme, le proprietà della cardinalità si traducono in proprietà della probabilità.
>[!important] PROP.
>1. $P(\emptyset)=0$ e $P(\Omega)=1$
>2. $P(A\cup B)=P(A)+P(B)-P(A\cap B)$
>3. $P(\Omega \setminus A)= P(A^c) =1-P(A)$
>4. Se $A\subseteq B$ allora $P(A)\leq P(B)$
## CONTINUITA' DELLA PROBABILITA'
Se $A_{1}\subseteq A_{2}\subseteq A_{3}\subseteq\dots$ è una *successione crescente* di eventi, allora:
$$
P\left( \bigcup_{n=1}^{\infty}A_{n} \right) = \lim_{ n \to \infty }P(A_{n})
$$
Se $A_{1}\supseteq A_{2}\supseteq A_{3}\supseteq\dots$ è una *successione decrescente* di eventi, allora: 
$$
P\left( \bigcap_{n=1}^{\infty}A_{n} \right) = \lim_{ n \to \infty }P(A_{n})
$$
>[!prop] IN GENERALE
>Se $\{ A_{n},n\geq 1 \}$ è una *successione monotona di eventi* si ha:
>$$ \lim_{ n \to \infty }P(A_{n}) = P(\lim_{ n \to \infty }A_{n} ) $$ 
# PROBABILITA' CONDIZIONATA
## DEFINIZIONE
>[!def] PROBABILITA DI UN EVENTO, DATO UN ALTRO
>Siano $A$ e $B$ due eventi.
>Se $P(B)>0$, allora la probabilità di $A$ condizionata da $B$ è:
>$$ P(A|B) = \frac{P(A\cap B)}{P(B)} $$

E possiamo ricavare anche:
$$
P(A\cap B) = P(B)P(A|B) = P(A)P(B|A)
$$
### ESEMPIO
Una persona è convinta all'80% che le chiavi di casa (che non trova) siano in *una delle due tasche* della giacca che ha lasciato in macchina, con la *stessa probabilità*.
Qual è la probabilità che siano nella tasca destra?
- $S=\{ \text{sono nella sinistra} \}$
- $D=\{ \text{sono nella destra} \}$

La probabilità che le chiavi siano nella tasca destra è condizionata dal fatto che *non siano nella sinistra*:
$$
P(D|S^c) = \frac{P(D\cap S^c)}{P(S^c)} = \frac{P(D)}{1-P(S)} = \frac{40\%}{60\%} = \frac{2}{3}
$$
## CASO GENERALE
Se $P(A_{1}\cap A_{2}\cap\dots\cap A_{n-1})>0$, allora:
>[!prop] PROB. CONDIZIONATA: CASO GENERALE
>$$ P(A_{1}\cap A_{2}\cap\dots\cap A_{n}) = P(A_{1})\cdot P(A_{2}|A_{1})\cdot P(A_{3}|A_{1}\cap A_{2})\dots P(A_{n}|A_{1}\cap\dots\cap A_{n-1}) $$
### ESEMPIO
Un mazzo di 52 carte è *diviso casualmente* in 4 *mazzetti di 13 carte*.
Qual è la probabilità che ci sia un asso in ogni mazzetto?
- $A_{1}=\{ \text{asso di picche in uno dei mazzetti} \}$
- $A_{2}=\{ \text{asso di picche e di cuori in due mazzetti diversi} \}$
- $A_{3}=\{ \text{assi di picche, cuori e quadri in tre mazzetti diversi} \}$
- $A_{4}=\{ \text{assi in mazzetti diversi} \}$

Abbiamo $A_{1}\subset A_{2}\subset A_{3}\subset A_{4}$, per cui:
$$
P(A_{4}) = P(A_{1}\cap A_{2}\cap A_{3}\cap A_{4})
$$
E, per quanto visto riguardo il caso generale, abbiamo:
$$
P(A_{4}) = P(A_{1})\cdot P(A_{2}|A_{1})\cdot P(A_{3}|A_{1}\cap A_{2})\cdot P(A_{4}|A_{1}\cap A_{2}\cap A_{3})
$$
Dove:
- $P(A_{1})=1$ (deve essere sicuramente in uno dei 4 mazzetti).
- $P(A_{2}|A_{1})=\frac{39}{51}$ (fissato un asso nel primo, restano 51 carte. Inoltre l'asso di picche deve trovarsi in una delle 39 carte degli altri 3 mazzetti)
- $P(A_{3}|A_{1}\cap A_{2})=\frac{26}{50}$ (come sopra).
- $P(A_{4}|A_{1}\cap A_{2}\cap A_{3})=\frac{13}{49}$

E troviamo quindi:
$$
P(A_{4}) = 1\cdot \frac{39}{51}\cdot \frac{26}{50}\cdot \frac{13}{49} \approx 0.105 =10.5\%
$$
## PROBABILITA' TOTALE
Vale anche:
$$
P(A) = P(B)P(A|B) + P(B^c)P(A|B^c)
$$
Ed in generale:
>[!prop] FORMULA DELLA PROBABILITA' TOTALE
>Se $\{ B_{1},B_{2},\dots,B_{n} \}$ è una partizione di $\Omega$:
>$$ P(A) = \sum_{i=1}^n P(B_{i})P(A|B_{i}) $$

>[!important] INTERPRETAZIONE:
>$P(B_{i})P(A|B_{i})=P(A\cap B_{i})$ è la probabilità che si verifichino **sia A che B$_{i}$**.
>Se sommiamo su tutti i $B_{i}$ della partizione troviamo quindi la *probabilità che si verifichi $A$ dato qualsiasi altro evento* dello spazio campionario, cioè la probabilità totale che si verifichi $A$.
### ESEMPIO
Consideriamo 3 monete truccate:
- $A$: testa ($T$) con probabilità $\frac{1}{2}$.
- $B$: $T$ con probabilità $\frac{1}{3}$.
- $C$: $T$ con probabilità $\frac{1}{4}$.

Scegliendo una moneta a caso e lanciandola, qual è la probabilità di ottenere testa?
$$
P(T) = P(A)P(T|A) + P(B)P(T|B) + P(C)P(T|C) 
$$
Siccome la probabilità di pescare una delle 3 monete è uniforme, abbiamo:
$$
P(T) = \frac{1}{3} \frac{1}{2} + \frac{1}{3} \frac{1}{3} + \frac{1}{3} \frac{1}{4} = \frac{13}{36} \approx 0.36 = 36\%
$$
## TEOREMA DI BAYES
Dalle relazioni viste fin'ora si ricava anche il teorema di Bayes:
$$ 
P(A|B) = \frac{P(A)P(B|A)}{P(B)}
$$
In generale:
>[!theorem] TH. BAYES
>Se $\{ A_{1},A_{2},\dots,A_{n} \}$ è una partizione di $\Omega$ si ha:
>$$ P(A_{i}|B) = \frac{P(A_{i})P(B|A_{i})}{P(B)} = \frac{P(A_{i})P(B|A_{i})}{\sum_{j=1}^n P(A_{j})P(B|A_{j})} $$
### ESEMPIO
Consideriamo 3 carte con due facce colorate:
- $\text{RR}$
- $\text{NN}$
- $\text{RN}$

Scegliamo una carta senza guardare e la appoggiamo sul tavolo.
Vediamo una faccia rossa. Qual è la probabilità che l'altra faccia sia nera?
$$
P(RN|R) = \frac{P(RN)P(R|RN)}{P(RR)P(R|RR)+P(NN)P(R|NN)+P(RN)P(R|RN)}
$$

E troviamo quindi:
$$
P(RN|R) = \frac{\frac{1}{3} \frac{1}{2}}{\frac{1}{3}\cdot1+ \frac{1}{3}\cdot0+ \frac{1}{3} \frac{1}{2}} = \frac{\frac{1}{6}}{\frac{3}{6}} = \frac{1}{3}
$$
### PROBLEMA DI MONTY HALL
Consideriamo un gioco a premi in cui si può scegliere tra 3 porte $P_{1},P_{2},P_{3}$.
Dietro ad una porta c'è una macchina, mentre dietro le altre due c'è una capra.

Il concorrente sceglie una porta (poniamo abbia scelto $P_{1}$ senza perdita di generalità).
Il conduttore apre un'altra porta, dietro cui c'è una capra (poniamo sia $P_{2}$) e offre la possibilità di cambiare la porta scelta.
Cosa conviene fare?
- $M_{i}=\{ \text{la macchina è dietro la porta }P_{i} \}$
- $P(M_{1})=P(M_{2})=P(M_{3})$
- $P(P_{i})=\{ \text{il conduttore apre la porta i} \}$ 

L'evento condizionante è l'apertura della porta $P_{2}$ da parte del conduttore.
Inizialmente il concorrente ha scelto la porta $P_{1}$ con $\frac{1}{3}$ di probabilità di aver scelto quella giusta.

Vediamo quale sarebbe la probabilità di vincere cambiando la porta scelta:
$$
P(M_{3}|P_{2}) = \frac{P(M_{3})P(P_{2}|M_{3})}{P(P_{2})}
$$
Dove:
$$
P(P_{2}) = P(M_{1})P(P_{2}|M_{1}) + P(M_{2})P(P_{2}|M_{2}) + P(M_{3})P(P_{2}|M_{3})
$$
$$
P(P_{2}) = \frac{1}{3}\cdot \underset{ (*) }{ \frac{1}{2} } + \frac{1}{3}\cdot \underset{ (**) }{ 0 } + \frac{1}{3}\cdot 1
$$
**Nota**: $(*)$ $P(P_{2}|M_{1})$ è la probabilità che il conduttore apra la porta 2 se la macchina si trova dietro la 1: è $\frac{1}{2}$ perchè, per le regole del gioco, non aprirà mai la porta dietro cui si trova la macchina, che spiega $(**)$.

Troviamo quindi:
$$
P(M_{3}|P_{2}) = \frac{\frac{1}{3}\cdot 1}{\frac{1}{3}\cdot{ \frac{1}{2} } + \frac{1}{3}\cdot 0 + \frac{1}{3}\cdot 1} = \frac{2}{3}
$$
Conviene allora cambiare scelta!
# EVENTI INDIPENDENTI
>[!def] EVENTI INDIPENDENTI
>Due eventi $A$ e $B$ sono *indipendenti*, e si scrive $A\perp B$ se
>$$ P(A\cap B) = P(A)P(B) $$

Quindi sono indipendenti se $P(A|B)=P(A)$: l'avvenire di $A$ non dipende da $B$.
In generale, se $A\perp B$ allora vale anche $A\perp B^c$.

>[!prop] EVENTI INDIPENDENTI (IN GENERALE)
>Gli eventi $\{ A_{i} \}_{i\in I}$ sono indipendenti se $\forall i_{1},\dots,i_{n}\in I$ si ha
>$$ P(A_{i_{1}})\cap\dots \cap A_{i_{n}} = P(A_{i_{1}})\dots P(A_{i_{n}}) $$
## INDIPENDENZA CONDIZIONATA
>[!def] INDIPENDENZA CONDIZIONATA
>Due eventi $A$ e $B$ sono *condizionatamente indipendenti* dato $C$ se:
>$$ P(A\cap B | C) = P(A|C)P(B|C) $$

Cioè l'avvenire di $A$ e $B$ dato $C$ è condizionato *solo* da $C$.
>[!important] NOTA
>Due *variabili dipendenti* possono *diventare indipendenti* in *particolari condizioni* (evento $C$).
### ESEMPIO
Consideriamo due monete $M_{1}$ ed $M_{2}$.
$M_{1}$ è equilibrata $T/C$, mentre $M_{2}$ dà testa $T$ con il 90% di probabilità.
Scegliamo una moneta a caso e lanciamola due volte.
Se il primo lancio dà $T$ (evento $T_{1}$), qual è la probabilità che il secondo dia $T$ (evento $T_{2}$)?

$$
P(T_{2}|T_{1}) = \frac{P(T_{1}\cap T_{2})}{P(T_{1})}
$$
Dove:
$$
P(T_{1}\cap T_{2}) = P(M_{1})P(T_{1}\cap T_{2}|M_{1}) + P(M_{2})P(T_{1}\cap T_{2}|M_{2})
$$
Per l'indipendenza condizionata:
$$
P(T_{1}\cap T_{2}) = P(M_{1})P(T_{1}|M_{1})P(T_{2}|M_{1}) + P(M_{2})P(T_{1}|M_{2})P(T_{2}|M_{2})
$$
$$
= \frac{1}{2}\cdot \frac{1}{2}\cdot \frac{1}{2} + \frac{1}{2}\cdot \frac{9}{10}\cdot \frac{9}{10} \approx 0.53
$$
Mentre:
$$
P(T_{1}) = P(M_{1})P(T_{1}|M_{1}) + P(M_{2})P(T_{1}|M_{2}) = \frac{1}{2}\cdot \frac{1}{2} + \frac{1}{2}\cdot \frac{9}{10} = 0.70
$$
E troviamo quindi:
$$
P(T_{2}|T_{1}) \approx 0.76 = 76%
$$
A questo punto osserviamo che:
- $P(T_{1}\cap T_{2})= 0.53$
- $P(T_{1})=P(T_{2})=0.7$, per cui $P(T_{1})P(T_{2})=0.49$
- $P(T_{1}\cap T_{2})\ne P(T_{1})P(T_{2})$

Allora $T_{1}$ e $T_{2}$ **non** sono *indipendenti* in generale (lo sono sono condizionatamente alla scelta della moneta).
>[!important] INTERPRETAZIONE
>Osservare $T_{1}$ rende più probabile il fatto di aver scelto la moneta $M_{2}$ (perchè è più facile ottenere testa), aumentando quindi la probabilità di $T_{2}$.
>Cioè osservare $T_{1}$ *dà informazioni su* $M_{i}$, mentre una volta fissato $M_{i}$ non c'è più bisogno di usare $T_{1}$ per *prevedere* $T_{2}$ perchè le probabilità sono già conosciute (sapendo quale moneta è stata scelta).
# VARIABILI ALEATORIE DISCRETE
## DEFINIZIONE GENERALE
>[!def] VARIABILE ALEATORIA
>Sia $\Omega$ uno spazio campionario.
>Una **variabile aleatoria** su $\Omega$ è una *funzione*
>$$ X:\Omega\to \mathbb{R} $$

>[!def] VAR. ALEATORIA DISCRETA
>Una variabile aleatoria $X$ si dice **discreta** se la sua *immagine*
>$$ \mathrm{Im}(X)=\{ x_{1},x_{2},\dots \} $$
>è **numerabile**.
### ESEMPI
1. Numero di teste su tre lanci di una moneta.
   $X\in \{ 0,1,2,3 \}$ sono i possibili valori che la variabile aleatoria può assumere.
	   - $P(X=0)=P((C,C,C))=\frac{1}{8}$
	   - $P(X=1)=P((T,C,C),(C,T,C),(C,C,T))=\frac{3}{8}$
	   - $P(X=2)=P((T,T,C),(T,C,T),(C,T,T))=\frac{3}{8}$
	   - $P(X=3)=P((T,T,T))=\frac{1}{8}$.
	   - Chiaramente, $\sum\limits_{n=0}^{3}P(X=n)=1$.
2. Istante di arrivo di un treno alla stazione.
3. Distanza percorsa in un'ora correndo.
## DENSITA'
Data una variabile aleatoria, si definisce la sua *densità*:
>[!def] DENSITA' di una V. A.
>E' la *funzione*
>$$ \begin{matrix} P_{X}:\mathbb{R}\to[0,1] \\ x \mapsto P(X=x) \end{matrix} $$
>E si ha
>$$ \sum_{n}P_{X}(x_{n}) = 1 $$

>[!important] NOTA
>La *variabile aleatoria* fornisce l'insieme degli *eventi possibili*, la sua *densità* associa a ciascun evento la sua *probabilità di verificarsi*.
### ESEMPIO
Lanciamo due dadi a 6 facce $D_{1}$ e $D_{2}$.
Analizziamo la variabile aleatoria discreta $X=D_{1}+D_{2}$.
Abbiamo:
$$
X\in \{ 2,3,4,5,6,7,8,9,10,11,12 \}
$$
Il numero di eventi favorevoli per ciascun risultato è rappresentato nel seguente grafico:

```tikz
\usepackage{tikz}
\usepackage{pgfplots}
\begin{document}

\begin{tikzpicture}
    \begin{axis}[
        ymin = 0, ymax = 6,
        ytick distance = 1,
        ylabel = {Eventi favorevoli},
        xmin = 0, xmax = 14,
        xtick distance = 1,
        xlabel = {risultato},
        area style,
        axis x line=bottom,
        axis y line=left,
        width = \textwidth
    ]
        
        \addplot+[ybar interval,mark=no] plot coordinates 
        { (0, 0) (1, 0) (2, 1) (3, 2) (4, 3) (5, 4) 
	      (6, 5) (7, 6) (8, 5) (9, 4) 
	      (10, 3) (11, 2) (12, 1) (13,0) };
    \end{axis}
\end{tikzpicture}

\end{document}
```

Da questo otteniamo anche la *densità* di $X$ dividendo il numero di casi favorevoli per 36.
## DISTRIBUZIONE
>[!def] FUNZIONE DI DISTRIBUZIONE
>La *funzione di distribuzione* (o di *ripartizione*) di una variabile aleatoria $X$ è:
>$$ \begin{matrix} F_{X}:\mathbb{R}\to[0,1] \\ x \mapsto P(X\leq x) \end{matrix} $$

Se la variabile è discreta vale la seguente proposizione:
>[!prop] DISTRIBUZIONE PER V.A. DISCRETE
>$$ F_{X}(x) = P(X\leq x) = \sum_{y\leq x}P(X=y) = \sum_{y\leq x}P_{X}(y) $$

>[!important] PROPRIETA'
>1. $F_{X}$ è *crescente*: se $x\leq y$ allora $F_{X}(x)\leq F_{X}(y)$.
>2. $\lim_\limits{ x \to -\infty }F_{X}(x)=0$ e $\lim_\limits{ x \to +\infty }F_{X}(x)=1$ 
>3. $F_{X}$ è *continua a destra*.
>4. $\forall a\in \mathbb{R}$ si ha $P(x<a)=F_{X}(a^-)=\lim_\limits{ x \to a^- }F_{X}(x)$
>5. $\forall a\in \mathbb{R}$ si ha $P(X=a)=F_{X}(a)-F_{X}(a^-)$

Se $X$ è discreta, allora $F_{X}$ è *costante a tratti*.
### ESEMPIO
Sia $X$ una v.a. discreta con valori in $\{ 1,2,3,4 \}$ e *densità*:
- $P_{X}(1)=\frac{1}{4}$
- $P_{X}(2)=\frac{1}{2}$
- $P_{X}(3)=P_{X}(4)=\frac{1}{8}$

Allora:
$$
F_{X}(a) = \begin{cases}
0 & a<1 \\
\frac{1}{4} & 1\leq a < 2 \\
\frac{3}{4} & 2\leq a < 3 \\
\frac{7}{8} & 3\leq a\leq 4 \\
1  & a \geq 4
\end{cases}
$$


```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.8}

\begin{document}

\begin{tikzpicture}
\begin{axis}[
  axis x line=middle, axis y line=middle,
  ymin=0, ymax=1, ylabel=$y$,
  xmin=0, xmax=4, xlabel=$x$,
  domain=0:4,samples=101
]
    \addplot+ [
        const plot,
    ] coordinates {
        (0,0) (1,0.25) (2,0.75) (3,0.875) (4,1)
    };
\end{axis}
\end{tikzpicture}

\end{document}
```
## VALORE ATTESO
>[!def] VALORE ATTESO
>Sia $X$ una v.a. discreta.
>Si dice *valore atteso* o **media** di $X$ il numero
>$$ \mathbb{E}[X] = \sum_{x}xP_{X}(x) $$

$\mathbb{E}[X]$ è la *media pesata* di tutti i *possibili valori* che $X$ può assumere, ciascuno pesato dalla probabilità che $X$ lo assuma.
### ESEMPI
Lancio di un dado con esito $X$.
$$
\mathbb{E}[X] = 1\cdot \frac{1}{6} + 2\cdot \frac{1}{6} + 3\cdot \frac{1}{6} + 4\cdot \frac{1}{6} + 5\cdot \frac{1}{6} + 6\cdot \frac{1}{6} = 3.5
$$
Dato un evento $A$, definiamo la **variabile indicativa**:
>[!def]
>$$ \mathbb{1}_{A} = \begin{cases} 1 & \text{se A si verifica} \\ 0 & \text{se A non si verifica} \end{cases} $$

E si ha:
$$
\mathbb{E}[\mathbb{1}_{A}] = 1\cdot P(A) + 0\cdot P(A^c) = P(A)
$$
La *media della variabile indicativa* di $A$ è la *probabilità* che $A$ si verifichi.
## VALORE ATTESO DI COMPOSTE
>[!prop] PROPOSIZIONE
>Sia $X:\Omega\to \mathbb{R}$ una v.a. discreta e $g:\mathbb{R}\to \mathbb{R}$ una *funzione*.
>1. La funzione composta $g\circ X=g(X):\Omega\to \mathbb{R}$ è una *v.a. discreta*.
>2. $\mathbb{E}[g(X)]=\sum_{x}g(x)P_{X}(x)$
### ESEMPIO (QUADRATICO)
Sia $X$ una v.a. tale che $P(X=-1)=0.2$, $P(X=0)=0.5$ e $P(X=1)=0.3$.
Calcoliamo $\mathbb{E}[X^2]$:
In questo caso $g(x)=x^2$, per cui
$$
\mathbb{E}[X^2] = g(-1)P_{X}(-1) + g(0)P_{X}(0) + g(1)P_{X}(1)
$$
$$
= (-1)^2\cdot 0.2 + 0\cdot 0.5 + 1^2\cdot 0.3 = 0.5
$$
### CASO LINEARE
>[!prop] VALORE ATTESO: COMPOSTA LINEARE
>Sia $X$ una v.a. aleatoria discreta.
>Se $a,b\in \mathbb{R}$ si ha
>$$ \mathbb{E}[aX+b] = a\mathbb{E}[X] + b $$
### MOMENTI
1. $\mathbb{E}[X]=\sum_{x} xP_{X}(x)$ : media, *valore atteso* o **momento primo**.
2. $\mathbb{E}[X^2]=\sum_{x} x^2P_{X}(x)$ : *momento secondo*.
3. $\mathbb{E}[X^n]=\sum_{x} x^nP_{X}(x)$ : *momento n-esimo*.
### PIU' VARIABILI ALEATORIE
>[!prop] PROPOSIZIONE
>Siano $X,Y$ due v.a. aleatorie discrete e $g:\mathbb{R}^2\to \mathbb{R}$ una funzione.
>Allora
>$$ \mathbb{E}[g(X,Y)] = \sum_{x,y} g(x,y)P(X=x,Y=y) $$

In particolare:
$$
\mathbb{E}[X+Y] = \mathbb{E}[X] + \mathbb{E}[Y]
$$
E:
$$
\mathbb{E}[XY] = \sum_{x,y} xyP(X=x,Y=y)
$$
se sono indipendenti si ha:
$$
\mathbb{E}[XY] = \sum_{x}xP(X=x)\cdot \sum_{y}yP(Y=y)
$$
E questi risultati si estendono ad un *numero finito* di variabili aleatorie.
## VARIANZA
>[!def] VARIANZA
>Sia $X$ una v.a. con media $\mathbb{E}[X]=\mu$.
>La **varianza** di $X$ è
>$$ \mathrm{Var}(X) = \mathbb{E}[(X-\mu)^2] $$

>[!prop] PROPOSIZIONE: ALTERNATIVA
>$$ \mathrm{Var}(X) = \mathbb{E}[X^2] - \mathbb{E}[X]^2 $$

La varianza è un *indicatore* di quanto i valori della variabile si *discostano dalla media*.
>[!important] NOTA
>Il motivo per cui si considera la differenza dei *quadrati* è che, per variabili con *distribuzioni casuali*, la somma delle *semplici differenze* porterebbe a *risultati molto vicini a 0*.

>[!def] DEVIAZIONE STANDARD
>La deviazione standard di una v.a. $X$ è:
>$$ \sigma = \sqrt{ \mathrm{Var}(X) } $$

Come abbiamo visto nelle esperienze di laboratorio di fisica, la deviazione standard è spesso associata alla *bontà di una misura*: è utilizzata infatti per quantificare l'errore commesso nell'effettuare delle misure.
>[!important] NOTA
>$\sigma$ ha la *stessa unità di misura* di $X$.
>$\text{Var}(X)$, chiaramente, **no**.

>[!prop] PROPOSIZIONE
>Due v.a. $X$ e $Y$ con gli *stessi valori* e la *stessa densità* hanno anche la stessa varianza.
>$$ \mathrm{Var}(X) = \mathrm{Var}(Y) $$
### CASO LINEARE
>[!prop] VARIANZA PER COMPOSIZIONE LINEARE
>Se $a,b\in \mathbb{R}$ e $X$ è una v.a., allora:
>$$ S_{xx} = \text{Var}(aX+b) = a^2\mathrm{Var}(X) $$

Infatti:
$$
\mathrm{Var}(aX+b) = \mathbb{E}[(aX+b-a\mu-b)^2]
$$
$$
= \mathbb{E}[a^2(X-\mu)^2] = a^2\mathbb{E}[(X-\mu)^2] = a^2\text{Var}(X)
$$
### ESEMPIO: NORMALIZZAZIONE
Trovare $a,b\in \mathbb{R}$ tali che:
1. $\mathbb{E}[aX+b]=0$
2. $\text{Var}(aX+b)=1$.

La seconda condizione si traduce in:
$$
a^2\text{Var}(X) = 1 \implies a = \frac{1}{\sigma}
$$
Mentre la prima:
$$
a\mu+b = 0 \implies b=-a\mu \implies b=-\frac{\mu}{\sigma}
$$
Quindi
$$
aX+b = \frac{X-\mu}{\sigma} = \frac{X-\mathbb{E}[X]}{\sqrt{ \text{Var}(X) }}
$$
Ha *media 0* e *varianza 1*.
## COVARIANZA
>[!def] COVARIANZA
>Date due v.a. $X$ e $Y$ con medie $\mu_{X}$ e $\mu_{Y}$, si dice *covarianza* di $X$ e $Y$ il numero
>$$ S_{xy} = \text{Cov}(X,Y) = \mathbb{E}[(X-\mu_{X})(Y-\mu_{Y})] $$

>[!important] TERMINOLOGIA
>Si dice che $X$ e $Y$ sono **positivamente correlate** se
>$$ S_{xy} > 0 $$
>Si dice che sono **negativamente correlate** se
>$$ S_{xy} < 0 $$
>Se $|S_{xy}|\approx 1$ si dice che le due variabili sono **fortemente correlate**.

>[!prop] PROPOSIZIONE: ALTERNATIVA
>E' vero anche:
>$$ S_{xy} = \mathbb{E}[(XY)]-\mathbb{E}[(X)]\mathbb{E}[(Y)] $$

La covarianza fornisce una misura di *quanto le due variabili variano assieme*.

>[!important] PROPRIETA'
>1. $S_{xy} = S_{yx}$
>2. $S_{xx}=\text{Var}(X)$
>3. Se $a\in \mathbb{R}$ si ha $\text{Cov}(X,aY)=\text{Cov}(aX,Y)=a\text{Cov}(X,Y)$
>4. Se $X$ e $Y$ sono *indipendenti* si ha $S_{xy}=0$, ma **non è vero il viceversa**.
### ESEMPIO
Siano $\mathbb{1}_{A}$ e $\mathbb{1}_{B}$ le *variabili indicative* di due eventi $A$ e $B$.
Si ha:
$$
\text{Cov}(\mathbb{1}_{A},\mathbb{1}_{B}) = \mathbb{E}[(\mathbb{1}_{A}\mathbb{1}_{B})] - \mathbb{E}[(\mathbb{1}_{A})]\mathbb{E}[(\mathbb{1}_{B})]
$$
$$
= P(A\cap B) - P(A)P(B)
$$
Se la covarianza è positiva, allora:
$$
P(A\cap B)-P(A)P(B) > 0 \implies \frac{P(A\cap B)}{P(B)} = P(A|B) > P(A)
$$
Se invece è negativa si avrà:
$$
\frac{P(A\cap B)}{P(B)} = P(A|B) < P(A)
$$
>[!important] NOTA
>Ciò è in linea con il "significato" di *covarianza*: se $A$ e $B$ sono *positivamente correlate*, il verificarsi di $B$ *favorisce* anche il verificarsi di $A$.
>Al contrario, se sono *negativamente correlate*, il *verificarsi* di $B$ *riduce la probabilità* che si verifichi *anche* $A$.
>
### VARIANZA DI SOMME
Date due v.a. $X$ e $Y$, si ha:
>[!prop] VARIANZA DI SOMME
>$$ \text{Var}(X+Y) = \text{Var}(X) + \text{Var}(Y) + 2\text{Cov}(X,Y) $$

Se le due sono *indipendenti*, la loro covarianza è nulla e quindi si ha:
$$
\text{Var}(X+Y) = \text{Var}(X)+\text{Var}(Y)
$$
In generale si ha:
>[!prop] CASO GENERALE
>$$ \text{Var}(X_{1}+\dots X_{n}) = \sum_{i=1}^n \text{Var}(X_{i}) + 2\sum_{i<j}^n\text{Cov}(X_{i},X_{j}) $$
## MEDIA E VARIANZA CONDIZIONATA
Se $A$ è un evento con $P(A)>0$ si ha:
$$
\mathbb{E}[X|A] = \frac{\sum_{x}xP(X=x,A)}{P(A)}
$$
La **media condizionata** è il *peso medio dei valori* che considerano solo l'evento dato.
La varianza condizionata sarà invece:
$$
\text{Var}(X|A) = \mathbb{E}[(X-\mathbb{E}[X|A])^2|A]
$$
La **varianza condizionata** è spesso *più piccola* della *varianza originale* perchè *considera solo alcuni esiti*.
### ESEMPIO
Sia $X$ l'esito del lancio di un dado regolare a 6 facce.
Sia $A=\{ \text{esce un numero pari} \}$.
Abbiamo:
$$
\mathbb{E}[X|A] = \frac{2\cdot \frac{1}{6}+4\cdot \frac{1}{6}+6\cdot \frac{1}{6}}{\frac{3}{6}} = 4
$$
Che è quello che ci aspettavamo: il *valore atteso* per $X$, se è uscito un numero pari, è 4 (media degli esiti $\in A$).
Per quanto riguarda la varianza, invece, abbiamo:
$$
\text{Var}(X|A) = \frac{(2-4)^2\cdot \frac{1}{6} + (4-4)^2\cdot \frac{1}{6} + (6-4)^2\cdot \frac{1}{6}}{\frac{3}{6}} = \frac{8}{3}
$$
### MEDIA E VARIANZA TOTALE
In modo del tutto simile a quanto visto per la *probabilità totale* troviamo:
>[!prop] MEDIA TOTALE
>$$ \mathbb{E}[X] = \sum_{y} \mathbb{E}[X|Y=y]P(Y=y) $$

E anche:
>[!prop] VARIANZA TOTALE
>$$ \text{Var}(X) = \mathbb{E}[\text{Var}(X|Y)] + \text{Var}(\mathbb{E}[X|Y]) $$

