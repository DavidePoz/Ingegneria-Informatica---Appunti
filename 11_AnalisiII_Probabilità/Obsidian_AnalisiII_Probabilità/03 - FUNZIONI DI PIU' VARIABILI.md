# INDICE SEZIONE
- [ ] [[#DEFINIZIONE]]
- [ ] [[#GRAFICO]]
- [ ] [[#TRACCIARE IL GRAFICO]]
# DEFINIZIONE
Una funzione di più variabili è, molto semplicemente, una funzione del tipo seguente:
>[!def] FUNZIONE DI PIU' VARIABILI
> $$ f:D\subset \mathbb{R}^n \to \mathbb{R} $$
> $$ (x_{1},\dots,x_{n})\in D \mapsto f(x_{1},\dots,x_{n})\in \mathbb{R} $$
> Con $n\geq 2$.

$D$ è un sottoinsieme dello spazio $\mathbb{R}^n$ ed è detto **dominio** della funzione.
Se $D$ non è precisato a priori, si intende il più grande insieme sul quale l'espressione della funzione $f$ ha significato.
# GRAFICO
Come le funzioni di una sola variabile, anche quelle di più variabili hanno un grafico.
>[!def] GRAFICO (IN PIU' VARIABILI)
>Il *grafico di una funzione* $f:D\subset \mathbb{R}^n\to \mathbb{R}$ è l'**insieme** dello spazio $\mathbb{R}^{n+1}$ definito da:
>$$ G_{f} := \big\{ (x,f(x)) = (x_{1},\dots,x_{n},f(x_{1},\dots,x_{n})) : x=(x_{1},\dots,x_{n})\in D \big\} $$

Vediamo per esempio il grafico della funzione $z=-xe^{-x^2-y^2}$:

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}

\begin{document}

\begin{tikzpicture}[scale=1.4]
\begin{axis}[colormap/viridis]
\addplot3[
	surf,
	samples=18,
	domain=-3:3
]
{exp(-x^2-y^2)*(-x)};
\end{axis}
\end{tikzpicture}

\end{document}
```

Notiamo che il grafico è l'insieme dei punti di $\mathbb{R}^3$ la cui quota è data dalla funzione $f$, cioè i punti della forma $(x,y,f(x,y))$.
>[!important] INTERPRETAZIONE
>Una funzione $f:\mathbb{R}^2\to \mathbb{R}$ può essere interpretata come l'assegnazione di una quota a ciascun punto di una superficie.
# TRACCIARE IL GRAFICO
Il metodo migliore per tracciare il grafico di una funzione in più variabili è chiaramente fare affidamento a del software apposito, vista la difficoltà di visualizzare uno spazio di un numero di dimensioni difficilmente rappresentabili su un foglio.
Tuttavia esiste una classe di funzioni il cui grafico risulta particolarmente facile da tracciare, mentre in altri casi è possibile ricorrere ad alcune semplici strategie.
## FUNZIONI RADIALI
Si tratta di funzioni che assumono lo *stesso valore* su *ogni sfera del dominio*, ovvero il loro valore dipende solo dalla distanza di un punto dall'origine.
Formalmente:
>[!def] FUNZIONI RADIALI
>$$ f(x) = h(|x|) \text{ }\forall x \in D\subset \mathbb{R}^n $$
>Dove $h$ è una funzione di variabile positiva reale.
>Il dominio di una funzione radiale è un disco, una palla o tutto lo spazio.

Per tali funzioni, soprattutto quelle da $\mathbb{R}^2$ a $\mathbb{R}$, risulta molto semplice tracciare il grafico perchè è sufficiente ruotare attorno all'asse $z$ il grafico della funzione $h(x)$ per $x\geq0$.
Un esempio è dato dal *paraboloide ellittico*, grafico della funzione $z=x^2+y^2$:

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}

\begin{document}

\begin{tikzpicture}[scale=1.4]
\begin{axis}[colormap/viridis]
\addplot3[
	surf,
	samples=18,
	domain=-3:3
]
{x^2 + y^2};
\end{axis}
\end{tikzpicture}

\end{document}
```
E' immediato osservare che $z(x,y)=||(x,y)||^2$ e il grafico può quindi essere ottenuto ruotando quello della funzione $x^2$ attorno all'asse $z$.
## INSIEMI DI LIVELLO
Quando osserviamo una carta geografica notiamo delle curve che rappresentano un insieme di punti che si trovano alla stessa quota.
Tali curve sono un esempio di *insiemi di livello*, definiti formalmente nel seguente modo:
>[!def] INSIEMI DI LIVELLO
>Sia $f:D\subset\mathbb{R}^n\to \mathbb{R}$ una funzione.
>L'*insieme di livello $c$* è il sottoinsieme di $D$ dato da:
>$$ Z_{c}(f) := \{ x \in D : f(x)=c \} $$

Quando $n=2$ si parla di curve di livello, infatti in tal caso $Z_{c}(f)$ è proprio il sostegno di una curva.
Vediamo per esempio le curve di livello per $c=1,2,3,4,5$ per il paraboloide iperbolico di prima:

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}

\begin{document}

\begin{tikzpicture}[scale=1.4]
\begin{axis}[width=8cm,height=8cm]

\addplot[mark=*] coordinates {(0,0)};

\foreach\z in{1,2,3,4,5}
    \addplot[domain=0:360,samples=73,red]
      ({sqrt(\z)*cos(\x)},{sqrt(\z)*sin(\x)});

\end{axis}
\end{tikzpicture}

\end{document}
```

Notiamo che più ci allontaniamo da $(0,0)$ e più le curve diventano fitte: questo fatto riflette l'andamento della funzione, che cresce sempre più velocemente all'aumentare di $x$ e $y$.
## METODO DELLE SEZIONI (O PROFILI)
Quando non si ha a che fare con funzioni radiali, o comunque con funzioni per cui potrebbe risultare complicato tracciare le curve di livello, un altro possibile approccio è quello di tracciare le **sezioni** $x\mapsto f(x,y)$ oppure $y\mapsto f(x,y)$.

>[!tldr] SEZIONI
>Data una funzione $f:\mathbb{R}^2\to \mathbb{R}$, per ottenere le sezioni (prendiamo per esempio quelle parallele al piano $xz$) seguiamo il seguente procedimento:
>- Fissiamo la $y$, interpretandola come una costante.
>- Tracciamo il grafico della funzione così ottenuta.
>
>Chiaramente vale lo stesso per i profili paralleli al piano $yz$.

Facciamo ancora una volta l'esempio con il paraboloide iperbolico, tracciando i suoi profili paralleli al piano $xz$.
Trattando la $y$ come costante troviamo che le sezioni sono date da:
$$
x\longmapsto x^2 +y^2
$$
Cioè sono delle parabole con la concavità rivolta verso l'alto e il vertice a quota $y^2$.
Prendendo $y=0,1,2,3$ otteniamo i seguenti profili:

```tikz
\usepackage{pgfplots}
\pgfplotsset{compat=1.16}

\begin{document}

\begin{tikzpicture}[scale=1.4]
\begin{axis}[xmin=-4, xmax=4, ymin=0, ymax=10]

\foreach\z in{0,1,2,3}
    \addplot[smooth, red]{x^2 + \z^2}; 

\end{axis}
\end{tikzpicture}

\end{document}
```
