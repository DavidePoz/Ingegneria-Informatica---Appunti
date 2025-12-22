# INDICE SEZIONE
- [ ] [[#DEFINIZIONE]]
- [ ] [[#SPAZIO TANGENTE E VETTORE NORMALE]]
- [ ] [[#INTEGRALI DI SUPERFICIE]]
# DEFINIZIONE
>[!def] SUPERFICIE PARAMETRICA IN R3
>Chiamiamo superficie parametrica in $\mathbb{R}^3$ ogni *funzione*
>$$ \sigma:D\subset \mathbb{R}^2\to \mathbb{R}^3 \text{ , }\sigma(u,v) = ( \sigma_{1}(u,v), \sigma_{2}(u,v), \sigma_{3}(u,v) ) $$
>continua con dominio $D$ chiuso e limitato in $\mathbb{R}^2$.
>L'insieme $S=\sigma(D)\subset \mathbb{R}^3$ si dice **sostegno** di $\sigma$.

Come anche per i campi vettoriali, diciamo che $\sigma$ è $C^1$ se lo sono le sue componenti.
## ESEMPIO: NASTRO DI MOEBIUS
Consideriamo la seguente superficie parametrica:
$$
\sigma(x,y) = \begin{pmatrix}
\left( R + y\cos \frac{x}{2} \right)\cos x \\
\left( R+y\cos \frac{x}{2} \right)\sin x \\
y\sin \frac{x}{2}
\end{pmatrix}
$$
Con $x \in[0,2\pi]$ e $y\in\left[ -\frac{1}{2}, \frac{1}{2} \right]$.

```tikz
\usepackage{pgfplots}
\pgfplotsset{}
\begin{document}

\begin{tikzpicture}[scale=1.5]
  \begin{axis}[
    hide axis,
    view = {40}{40}
  ]
  \addplot3 [
    surf,
    colormap/viridis,
    point meta = x,
    samples    = 40,
    samples y  = 5,
    z buffer   = sort,
    domain     = 0:360,
    y domain   =-0.5:0.5
  ] (
    {(1+0.5*y*cos(x/2)))*cos(x)},
    {(1+0.5*y*cos(x/2)))*sin(x)},
    {0.5*y*sin(x/2)}
  );

  \addplot3 [
    samples=50,
    domain=-145:180, % The domain needs to be adjusted manually,
                     % depending on the camera angle, unfortunately
    samples y=0,
    thick
  ] (
    {cos(x)},
    {sin(x)},
    {0}
  );
  
  \end{axis}
\end{tikzpicture}

\end{document}
```
Tale superficie è nota come *nastro di Moebius* ed è un esempio di superficie **non orientabile**: percorrendola si passa con continuità da "sopra" a "sotto".
## SUPERFICI CARTESIANE
In modo del tutto analogo a quanto fatto per le curve cartesiane, definiamo cosa intendiamo per *superficie cartesiana*.
>[!def] SUPERFICIE CARTESIANA
>Se $f:D\subset \mathbb{R}^2\to \mathbb{R}$, con $D$ come sopra, la *superficie cartesiana* associata ad $f$ è:
>$$ \sigma_{f}(x,y) = \begin{pmatrix} x \\ y \\ f(x,y) \end{pmatrix} $$

>[!important] NOTA
>Nel caso delle curve, una curva cartesiana era il grafico di una funzione in una variabile.
>Ora, una superficie cartesiana è il **grafico** di una **funzione in due variabili**.
## PROPRIETA'
Data una superficie $\sigma:D\subset \mathbb{R}^2\to \mathbb{R}^3$, diciamo che essa è:
>[!def] PROPRIETA' DELLE SUPERICI
>- **Semplice** se $\sigma$ è *iniettiva* in $\text{Int}(D)$ (la superficie non ha *auto-intersezioni*).
>- **Regolare** in un punto $P=\sigma(u^*,v^*)\text{ , }(u^*,v^*)\in D$ se $\sigma$ è *differenziabile* in $(u^*,v^*)$ e se $\partial_{u}\sigma(u^*,v^*)$ e $\partial_{v}\sigma(u^*,v^*)$ sono *linearmente indipendenti*.
# SPAZIO TANGENTE E VETTORE NORMALE
## SPAZI TANGENTI
Una curva $\gamma$ di classe $C^1$ ha un vettore tangente in ogni punto $t$ del dominio nel quale $\gamma'(t)\ne 0$.
Analogamente possiamo parlare di **piano vettoriale tangente** ad una superficie parametrica.
Siano $\sigma:D\to \mathbb{R}^3$ una superficie parametrica di classe $C^1$ e $(\bar{u},\bar{v})\in D$.
Il vettore
$$
\partial_{u}\sigma(\bar{u},\bar{v}) = \begin{pmatrix}
\partial_{u}\sigma_{1}(\bar{u},\bar{v}) \\
\partial_{u}\sigma_{2}(\bar{u},\bar{v}) \\
\partial_{u}\sigma_{3}(\bar{u},\bar{v})
\end{pmatrix}
$$
è tangente alla curva $u\mapsto\sigma(u,\bar{v})$ ("profilo" a $\bar{v}$ fissata) in $u=\bar{u}$.
Similmente, anche il vettore $\partial_{v}(\bar{u},\bar{v})$ è tangente alla curva $v\mapsto(\bar{u},v)$ (profilo a $\bar{u}$ fissata) nel punto $v=\bar{v}$.
Possiamo usare tali vettori per definire lo spazio tangente alla superficie.

>[!def] SPAZIO VETTORIALE TANGENTE
>Siano $\sigma:D\to \mathbb{R}^3$ superficie parametrica di classe $C^1$ ed $(u,v)\in D$ con i vettori $\partial_{u}\sigma(u,v)$ e $\partial_{v}\sigma(u,v)$ *linearmente indipendenti* (cioè la curva è regolare).
>- Lo **spazio vettoriale tangente** a $\sigma$ in $(u,v)$ è lo *spazio vettoriale* $T_{\sigma} := \langle \partial_{u}\sigma(u,v) , \partial_{v}\sigma(u,v) \rangle$ generato dai vettori tangenti.
>- Lo **spazio tangente** a $\sigma$ in $(u,v)$ è invece lo spazio $\sigma(u,v)+T_{\sigma}$.


```tikz
\usepackage{tikz}
\usetikzlibrary{calc,fadings,decorations.pathreplacing}

\newcommand\pgfmathsinandcos[3]{%
  \pgfmathsetmacro#1{sin(#3)}%
  \pgfmathsetmacro#2{cos(#3)}%
}
\newcommand\LongitudePlane[3][current plane]{%
  \pgfmathsinandcos\sinEl\cosEl{#2} % elevation
  \pgfmathsinandcos\sint\cost{#3} % azimuth
  \tikzset{#1/.style={cm={\cost,\sint*\sinEl,0,\cosEl,(0,0)}}}
}
\newcommand\LatitudePlane[3][current plane]{%
  \pgfmathsinandcos\sinEl\cosEl{#2} % elevation
  \pgfmathsinandcos\sint\cost{#3} % latitude
  \pgfmathsetmacro\yshift{\cosEl*\sint}
  \tikzset{#1/.style={cm={\cost,0,0,\cost*\sinEl,(0,\yshift)}}} %
}
\newcommand\DrawLongitudeCircle[2][1]{
  \LongitudePlane{\angEl}{#2}
  \tikzset{current plane/.prefix style={scale=#1}}
   % angle of "visibility"
  \pgfmathsetmacro\angVis{atan(sin(#2)*cos(\angEl)/sin(\angEl))} %
  \draw[current plane] (\angVis:1) arc (\angVis:\angVis+180:1);
  \draw[current plane,dashed] (\angVis-180:1) arc (\angVis-180:\angVis:1);
}
\newcommand\DrawLatitudeCircle[2][1]{
  \LatitudePlane{\angEl}{#2}
  \tikzset{current plane/.prefix style={scale=#1}}
  \pgfmathsetmacro\sinVis{sin(#2)/cos(#2)*sin(\angEl)/cos(\angEl)}
  % angle of "visibility"
  \pgfmathsetmacro\angVis{asin(min(1,max(\sinVis,-1)))}
  \draw[current plane] (\angVis:1) arc (\angVis:-\angVis-180:1);
  \draw[current plane,dashed] (180-\angVis:1) arc (180-\angVis:\angVis:1);
}

%% document-wide tikz options and styles

\tikzset{%
  >=latex, % option for nice arrows
  inner sep=0pt,%
  outer sep=2pt,%
  mark coordinate/.style={inner sep=0pt,outer sep=0pt,minimum size=3pt,
    fill=black,circle}%
}

\begin{document}

\begin{tikzpicture}

%% some definitions
\def\R{2.5} % sphere radius
\def\angEl{35} % elevation angle
\def\angAz{-105} % azimuth angle
\def\angPhi{-40} % longitude of point P
\def\angBeta{19} % latitude of point P

%% working planes

\pgfmathsetmacro\H{\R*cos(\angEl)} % distance to north pole
\tikzset{xyplane/.style={
  cm={cos(\angAz),sin(\angAz)*sin(\angEl),-sin(\angAz),cos(\angAz)*sin(\angEl),(0,-\H)}
  }
}
\LatitudePlane[equator]{\angEl}{0}

%% characteristic points
\coordinate (O) at (0,0);
\coordinate (N) at (0,\H);

%% draw xy shifted plane and sphere
\fill[ball color=yellow!80] (0,0) circle (\R);
\filldraw[xyplane,shift={(N)},fill=blue!10,opacity=0.2] 
  (-1.4*\R,-1.7*\R) rectangle (2.2*\R,2.2*\R);
\draw (0,0) circle [radius=\R];
\coordinate[mark coordinate] (N) at (0,\H);

%% draw equator
\DrawLatitudeCircle[\R]{0}

% lines and labels
\draw[dashed]
  (N) node[above] {$A$} -- (O) node[below] {$O$};
\node at (2*\R,1.2*\R) {$T_{\sigma}$};
\end{tikzpicture}

\end{document}
```

Se $P\in \mathbb{R}^3$ è un punto della superficie parametrica $\sigma$, il piano tangente a $\sigma$ nel punto $P$ è dato da:
$$
\pi: \underline{x} = P + \lambda\partial_{u}\sigma(u,v) + \gamma \partial_{v}\sigma(u,v) \text{ , }\lambda,\gamma \in \mathbb{R}
$$
Esattamente come nella definizione.
>[!important] NOTA
>Lo spazio vettoriale tangente è l'insieme dei vettori che sono combinazione lineare dei vettori tangenti. Lo spazio tangente è invece una "traslazione" dello spazio vettoriale tangente dall'origine ad un dato punto della superficie: in figura vediamo per esempio lo spazio tangente ad una superficie sferica nel punto evidenziato $A$.
## VETTORE NORMALE
Prima di dare la definizione di vettore normale ad una superficie, ricordiamo la definizione di prodotto vettoriale di due vettori in $\mathbb{R}^3$.

>[!def] PRODOTTO VETTORIALE
>Siano $a=(a_{1},a_{2},a_{3})$ e $b=(b_{1},b_{2},b_{3})$ due vettori di $\mathbb{R}^3$.
>Il prodotto vettoriale $a\times b$ è dato da:
>$$ a\times b = \det \begin{pmatrix} \hat{x} & \hat{y} & \hat{z} \\ a_{1} & a_{2} & a_{3} \\ b_{1} & b_{2} & b_{3} \end{pmatrix} $$

In particolare, $a\times b$ è un vettore ortogonale al piano su cui giacciono $a$ e $b$ ed il suo modulo è pari all'area del parallelogramma individuato dai due vettori.
>[!def] VETTORE NORMALE (UNITARIO) AD UNA SUPERFICIE
>Siano $\sigma:D\to \mathbb{R}^3$ una superficie di classe $C^1$ ed $(u,v)\in D$ con i vettori tangenti $\partial_{u}\sigma$ e $\partial_{v}\sigma$ in $(u,v)$ *linearmente indipendenti*.
>Il *vettore normale* (**unitario**) alla superficie nel punto $(u,v)$ è:
>$$ \mathbf{N}_{\sigma}(u,v) = \frac{\partial_{u}\sigma \times \partial_{v}\sigma}{||\partial_{u}\sigma \times \partial_{v}\sigma||}(u,v) $$

>[!important] NOTA
>In quanto prodotto vettoriale dei vettori tangenti, è ortogonale allo spazio tangente alla superficie in $(u,v)$, cioè è ortogonale alla superficie stessa.
>Per convenzione, inoltre, si considera un vettore di norma unitaria.

Possiamo usare il vettore normale per determinare il piano tangente $\pi$ alla superficie $\sigma$ nel punto $P$ in un altro modo:
$$
\pi: \mathbf{n}(P)\cdot(\mathbf{x}-P) = 0 \Leftrightarrow n_{1}(x_{1}-p_{1}) + n_{2}(x_{2}-p_{2}) + n_{3}(x_{3}-p_{3}) = 0
$$
Si tratta dei punti $x \in \mathbb{R}^3$ tali che il vettore congiungente $x$ con $P$ è ortogonale alla normale alla superficie.
# INTEGRALI DI SUPERFICIE
## AREA DI UNA SUPERFICIE
Il vettore normale ad una superficie ci permette di calcolarne l'area.
>[!def] AREA DI UNA SUPERFICIE
>Sia $\sigma:D\subset \mathbb{R}^2\to \mathbb{R}^3$ una curva regolare e sia $\mathbf{n}(u,v)\in \mathbb{R}^3$ il vettore normale a $\sigma$ dato da $\mathbf{n}=\partial_{u}\sigma \times \partial_{v}\sigma$ per ogni $(u,v)\in D$.
>L'area della superficie $S=\sigma(D)$ è data da:
>$$ \mathrm{Area}(S) = \int_{S}d\sigma = \int_{D} ||\mathbf{n}(u,v)||dudv $$

>[!important] NOTA
>Si tratta di integrare l'elemento infinitesimo di area $d\sigma$ su *tutto il sostegno* $S$.
>In particolare, tale elemento infinitesimo è dato, in ogni punto $(u,v)$, proprio dalla norma di $\mathbf{n}$, che abbiamo visto essere l'area del *parallelogramma* individuato dai *vettori tangenti* $\partial_{u}\sigma$ e $\partial_{v}\sigma$.
>Possiamo quindi dire che $||\mathbf{n}||$ è il fattore moltiplicativo con cui $\sigma$ modifica localmente le aree.

Se $\sigma$ è una superficie cartesiana abbiamo $\sigma(u,v)=(u,v,f(u,v))$ per una qualche funzione continua $f:D\to \mathbb{R}$.
In tal caso è semplice verificare che si ha:
$$
\mathbf{n}(u,v) = \begin{pmatrix}
1 \\
\partial_{u}f \\
\partial_{v}f
\end{pmatrix}
$$
E quindi:
$$
||\mathbf{n}(u,v)|| = \sqrt{ \left( \frac{\partial f}{\partial u} \right)^2 + \left( \frac{\partial f}{\partial v} \right)^2 +1 }
$$
E pertanto si avrà:
$$
\mathrm{Area(S)} = \int_{D} \sqrt{ \left( \frac{\partial f}{\partial u} \right)^2 + \left( \frac{\partial f}{\partial v} \right)^2 +1 } dudv
$$
Che è un integrale di forma molto simile a quello visto per la lunghezza di una curva cartesiana.
## INTEGRALE DI UNA FUNZIONE SU UNA SUPERFICIE
Supponiamo di essere interessati ad integrare una qualche funzione *su una certa superficie* (cioè ristretta ai punti dello spazio che compongono tale superficie).
Problemi di questo tipo possono presentarsi in casi come i seguenti:
1. Dato un oggetto in buona approssimazione bidimensionale (una sorta di *lamina*) e la *densità superficiale* in ogni suo punto, vogliamo calcolarne la *massa*.
2. Data una qualche *superficie* con una certa distribuzione di temperatura, vogliamo determinare la *temperatura media*.

>[!def] INTEGRALE DI SUPERFICIE
>Siano $\sigma:D\subset \mathbb{R}^2\to \mathbb{R}^3$ una superficie di classe $C^1$ con vettore normale $\mathbf{n}$ e $\mu:A\to \mathbb{R}$ una funzione continua con $\sigma(D)\subseteq A\subset \mathbb{R}^3$.
>L'integrale di superficie di $\mu$ su $\sigma$ è:
>$$ \int_{S}\mu(x,y,z)d\sigma = \int_{D}\mu(\sigma(u,v))||\mathbf{n}||dudv $$

Dove per $\mu(\sigma(u,v))$ intendiamo:
$$
\mu(\sigma(u,v)) = \mu ( \sigma_{1}(u,v), \sigma_{2}(u,v), \sigma_{3}(u,v) )
$$
Cioè la funzione $\mu$ calcolata nei punti che appartengono al *sostegno* di $\sigma$.
>[!important] NOTA
>Anche l'area di una superficie è un integrale superficiale: la funzione $\mu$ è semplicemente $1\forall (u,v)\in S$.
## FLUSSO DI UN CAMPO ATTRAVERSO UNA SUPERFICIE
>[!def] FLUSSO ATTRAVERSO UNA SUPERFICIE
>Sia $\sigma:\Sigma\to \mathbb{R}^3$ superficie parametrica *semplice e regolare* con vettore tangente unitario $\hat{\mathbf{n}}$.
>Sia $\mathbf{F}:D\subseteq \mathbb{R}^3\to \mathbb{R}^3$ campo vettoriale continuo, con $\Sigma \subseteq D$.
>Chiamiamo **flusso** di $\mathbf{F}$ attraverso la superficie $\sigma$:
>$$ \Phi(\mathbf{F},\Sigma):= \int_{\Sigma}\mathbf{F}\cdot dS = \int_{\Sigma}\mathbf{F}\cdot \hat{\mathbf{n}} dS = \int_{\Sigma}\mathbf{F}(\sigma(u,v))\cdot \hat{\mathbf{n}}(u,v) dudv $$

Si tratta quindi di un integrale superficiale per campi vettoriali: come nel caso degli integrali curvilinei, per i campi il prodotto con la norma di $\mathbf{n}$ è sostituito dal *prodotto scalare* con il vettore stesso.
>[!important] NOTA
>Data la presenza del *prodotto scalare* con la *normale alla superficie* nella definizione, il flusso sarà tanto maggiore quanto più vicina è la superficie ad essere *ortogonale al campo* in ogni suo punto.
>Possiamo quindi dire che il **flusso** misura la *tendenza del campo* ad **attraversare la superficie**.





