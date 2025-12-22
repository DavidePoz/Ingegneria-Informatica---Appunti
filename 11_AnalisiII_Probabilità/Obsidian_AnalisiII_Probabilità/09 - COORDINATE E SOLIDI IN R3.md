# INDICE SEZIONE
- [ ] [[#MATRICE JACOBIANA IN R3]]
- [ ] [[#CAMBI LINEARI]]
- [ ] [[#COORDINATE SFERICHE]]
- [ ] [[#COORDINATE CILINDRICHE]]
- [ ] [[#SOLIDI DI ROTAZIONE]]
# MATRICE JACOBIANA IN R3
In $\mathbb{R}^3$ la definzione di matrice Jacobiana è analoga a quella data per $\mathbb{R}^2$.
>[!def] MATRICE JACOBIANA
>Siano $X$ aperto di $\mathbb{R}^3$, $\varphi_{1},\varphi_{2},\varphi_{3}:X\to \mathbb{R}$ con derivate parziali.
>La matrice Jacobiana di $\varphi=(\varphi_{1},\varphi_{2},\varphi_{3})$ in $u=(u_{1},u_{2},u_{3})\in X$ è:
>$$ \varphi'(u) = J_{\varphi}(u):= \begin{pmatrix} \partial_{x}\varphi_{1}(u) & \partial_{y}\varphi_{1}(u) & \partial_{z}\varphi_{1}(u) \\ \partial_{x}\varphi_{2}(u) & \partial_{y}\varphi_{2}(u) & \partial_{z}\varphi_{2}(u) \\ \partial_{x}\varphi_{3}(u) & \partial_{y}\varphi_{3}(u) & \partial_{z}\varphi_{3}(u) \end{pmatrix} = \begin{pmatrix} \nabla \varphi_{1}(u) \\ \nabla \varphi_{2}(u) \\ \nabla \varphi_{3}(u) \end{pmatrix} $$
# CAMBI LINEARI
>[!prop] CAMBI DI VARIABILI LINEARI
>Sia $A$ matrice invertibile $3\times3$ e consideriamo il cambiamento di variabili $\varphi(u)=Au$ per ogni $u\in \mathbb{R}^n$.
>Sia $f:\Omega \subset \mathbb{R}^3\to \mathbb{R}$ integrabile.
>Allora:
>$$ \int_{\Omega}f(x)dx =|\det A|\int_{A^{-1}(\Omega)}f(Au)du $$
>Dove $A^{-1}(\Omega)=\varphi^{-1}(\Omega)=\{ u\in \mathbb{R}^n : Au\in\Omega \}$.
# COORDINATE SFERICHE
In presenza di domini radiali o anelli sferici diventa conveniente il cambiamento in coordinate sferiche.
>[!def] COORDINATE SFERICHE
>Il cambiamento in coordinate sferiche è definito da:
>$$ \forall \rho\geq 0, \theta \in[0,2\pi], \phi \in[0,\pi] \text{ } \varphi(\rho,\theta,\phi)=(\rho \sin \phi \cos\theta, \rho \sin \phi \sin\theta, \rho \cos \phi) $$

In analogia alle coordinate terrestri, $\theta \in[0,2\pi]$ è chiamata **longitudine**, mentre $\phi$ è la **colatitudine** (vale $0$ al polo nord e $\pi$ al polo sud).

```tikz
\usepackage{tikz}
\usepackage{tikz-3dplot}

\usetikzlibrary{angles, quotes, intersections}

%Styles
\tikzset{axis/.style={thick,-latex}}
\tikzset{vec/.style={thick,blue}}
\tikzset{univec/.style={thick,red,-latex}}

\begin{document}
	
	\tdplotsetmaincoords{70}{110}
	%
	\pgfmathsetmacro{\thetavec}{48.17}
	\pgfmathsetmacro{\phivec}{63.5}
	%
	
	\begin{tikzpicture}[tdplot_main_coords]
		%Axis
		\draw[axis] (0,0,0) -- (6.5,0,0) node [pos=1.1] {$\hat{x}$};
		\draw[axis] (0,0,0) -- (0,6,0) node [pos=1.05] {$\hat{y}$};
		\draw[axis] (0,0,0) -- (0,0,5.5)  node [pos=1.05] {$\hat{z}$};   
		
		%Unit Vectors
		\tdplotsetcoord{P'}{7}{\thetavec}{\phivec}
    	\draw[univec] (0,0,0) -- (P') node [pos=1.05] {$\hat{\rho}$};
    	\tdplotsetcoord{P''}{1}{90}{90+\phivec}
    	\draw[univec] (2,4,0) -- ($(P'') + (2,4,0)$) node [below] {$\hat{\theta}$};
    	\tdplotsetcoord{P'''}{1}{90+\thetavec}{\phivec}
    	\draw[univec] (2,4,4) -- ($(P''') + (2,4,4)$) node [pos=1.3] {$\hat{\phi}$};
		
		%Vectors
		\tdplotsetcoord{P}{6}{\thetavec}{\phivec}
		\draw[vec] (0,0,0) -- (P) node [midway, above] {$\rho$};
		\draw[thick] (0,0,0) -- (2,4,0);
		
		%Help Lines
		\draw[dashed] (2,4,4) -- (2,4,0);
		\draw[dashed] (2,0,0) -- (2,4,0) node [pos=-0.1] {$x$};
		\draw[dashed] (0,4,0) -- (2,4,0) node [pos=-0.3] {$y$};
		\draw[dashed] (0,0,4) -- (2,4,4) node [pos=-0.1] {$z$};
		\draw[dashed, tdplot_main_coords] (4.47,0,0) arc (0:90:4.47);
		
		%Point
		\node[fill=black, circle, inner sep=0.8pt] at (2,4,4) {};
		
		%Angles
		\tdplotdrawarc{(0,0,0)}{0.7}{0}{\phivec}{below}{$\theta$}
		 
	    \tdplotsetthetaplanecoords{\phivec}
	    \tdplotdrawarc[tdplot_rotated_coords]{(0,0,0)}{0.5}{0}{\thetavec}{}{}
	    \node at (0,0.25,0.67) {$\phi$};
		
	\end{tikzpicture}
	
\end{document}
```


>[!prop] CAMBIO IN COORDINATE SFERICHE
>Sia $f:\Omega\to \mathbb{R}$ integrabile.
>Si ha:
>$$ \int_{\Omega}f(x,y,z)dxdydz = \int_{\Sigma}f(\rho,\theta,\phi) \underbrace{ \rho^2\sin \phi }_{ = \det J_{\varphi} } d\rho d\theta d\phi $$
>Dove
>$$ \Sigma := \{ (\rho,\theta,\phi)\in[0,+\infty) \times [0,2\pi] \times [0,\pi] : (\rho \sin \phi \cos\theta,\rho \sin \phi \sin\theta,\rho \cos \phi)\in\Omega \} $$

```tikz
\usepackage{tikz}  
\usepackage{tikz-3dplot} 

\begin{document}

%Axis Angles
\tdplotsetmaincoords{70}{110}

%Macros
\pgfmathsetmacro{\rvec}{6}
\pgfmathsetmacro{\thetavec}{40}
\pgfmathsetmacro{\phivec}{45}

\pgfmathsetmacro{\dphivec}{20}
\pgfmathsetmacro{\dthetavec}{20}
\pgfmathsetmacro{\drvec}{1.5}

%Layers
\pgfdeclarelayer{background}
\pgfdeclarelayer{foreground}

\pgfsetlayers{background, main, foreground}

\begin{tikzpicture}[tdplot_main_coords]

%Coordinates
\coordinate (O) at (0,0,0);
%
\tdplotsetcoord{A}{\rvec}{\thetavec}{\phivec}
\tdplotsetcoord{B}{\rvec}{\thetavec + \dthetavec}{\phivec}
\tdplotsetcoord{C}{\rvec}{\thetavec + \dthetavec}{\phivec + \dphivec}
\tdplotsetcoord{D}{\rvec}{\thetavec}{\phivec + \dphivec}
%
\tdplotsetcoord{E}{\rvec + \drvec}{\thetavec}{\phivec}
\tdplotsetcoord{F}{\rvec + \drvec}{\thetavec + \dthetavec}{\phivec}
\tdplotsetcoord{F'}{\rvec + \drvec}{90}{\phivec}
\tdplotsetcoord{G}{\rvec + \drvec}{\thetavec + \dthetavec}{\phivec + \dphivec}
\tdplotsetcoord{G'}{\rvec + \drvec}{90}{\phivec + \dphivec}
\tdplotsetcoord{H}{\rvec + \drvec}{\thetavec}{\phivec + \dphivec}


%Axis
\begin{pgfonlayer}{background}
	\draw[thick,-latex] (0,0,0) -- (7,0,0) node[pos=1.1]{$x$};
	\draw[thick,-latex] (0,0,0) -- (0,7,0) node[pos=1.05]{$y$};
	\draw[thick,-latex] (0,0,0) -- (0,0,6) node[pos=1.05]{$z$};
\end{pgfonlayer}

%Help Lines
\begin{pgfonlayer}{background}
	%Up
	\draw[thick, blue] (O) -- (A) node[pos=0.6, above left, blue] {$\rho$};
	\draw (O) -- (B);
	\draw (O) -- (C);
	\draw[dashed] (O) -- (D);
	%Down
	\draw (O) -- (F');
	\draw (O) -- (G');
\end{pgfonlayer}
\begin{pgfonlayer}{foreground}
	%%Help Curves
	\tdplotsetthetaplanecoords{\phivec}
	\tdplotdrawarc[tdplot_rotated_coords]{(O)}{\rvec}{\thetavec+\dthetavec}{90}{}{}
	\tdplotdrawarc[tdplot_rotated_coords]{(O)}{\rvec+\drvec}{\thetavec+\dthetavec}{90}{}{}
	\tdplotsetthetaplanecoords{\phivec+\dphivec}
	\tdplotdrawarc[tdplot_rotated_coords, dashed]{(O)}{\rvec}{\thetavec+\dthetavec}{90}{}{}
	\tdplotdrawarc[tdplot_rotated_coords]{(O)}{\rvec+\drvec}{\thetavec+\dthetavec}{90}{}{}
	%
	\tdplotdrawarc[tdplot_main_coords]{(O)}{\rvec}{\phivec}{\phivec+\dphivec}{}{}
	\node[rotate=13] at (3,4.45,0) {$\rho\sin\phi\mathrm{d}\theta$};
	\tdplotdrawarc[tdplot_main_coords]{(O)}{\rvec+\drvec}{\phivec}{\phivec+\dphivec}{}{}
\end{pgfonlayer}


%Angles
\begin{pgfonlayer}{foreground}
	%Phi, dPhi
	\tdplotdrawarc[-stealth]{(O)}{0.9}{0}{\phivec}{anchor=north}{$\theta$}
	\tdplotdrawarc[-stealth]{(O)}{1.5}{\phivec}{\phivec + \dphivec}{}{}
	\node at (1.4,1.9,0) {$\mathrm{d}\theta$};
	
	\tdplotsetthetaplanecoords{\phivec}
	
	%Theta, dTheta
	\tdplotdrawarc[tdplot_rotated_coords, -stealth]{(0,0,0)}{1.2}{0}{\thetavec}{}{}
	\node at (0,0.3,1.3) {$\phi$};
	\tdplotdrawarc[tdplot_rotated_coords, -stealth]{(0,0,0)}{2.}{\thetavec}{\thetavec + \dthetavec}{anchor=south west}{$\mathrm{d}\phi$}
\end{pgfonlayer}

%Differential Volume

%%Lines
\begin{pgfonlayer}{foreground}
	\draw[thick] (A) -- (E) node[midway, above left]{$\mathrm{d}\rho$};
	\draw[thick] (B) -- (F);
	\draw[thick] (C) -- (G);
\end{pgfonlayer}
\begin{pgfonlayer}{background}
	\draw[dashed, thick] (D) -- (H);
\end{pgfonlayer}


%%Curved
\begin{pgfonlayer}{background}
	\tdplotsetrotatedcoords{55}{-50.4313}{-6.4086}
	\tdplotdrawarc[dashed, tdplot_rotated_coords, thick]{(O)}{\rvec}{0}{12.8173}{}{}
	%
	\tdplotsetthetaplanecoords{\phivec + \dphivec}
	\tdplotdrawarc[dashed, tdplot_rotated_coords, thick]{(O)}{\rvec}{\thetavec}{\dthetavec + \thetavec}{}{}
\end{pgfonlayer}
\begin{pgfonlayer}{foreground}
	\tdplotsetthetaplanecoords{\phivec}
	\tdplotdrawarc[tdplot_rotated_coords, thick]{(O)}{\rvec}{\thetavec}{\dthetavec + \thetavec}{below left}{$\rho\mathrm{d}\phi$}
	\tdplotdrawarc[tdplot_rotated_coords, thick]{(O)}{\rvec + \drvec}{\thetavec}{\dthetavec + \thetavec}{}{}
	%
	\tdplotsetthetaplanecoords{\phivec + \dphivec}
	\tdplotdrawarc[tdplot_rotated_coords, thick]{(O)}{\rvec + \drvec}{\thetavec}{\dthetavec + \thetavec}{}{}
	%
	\tdplotsetrotatedcoords{55}{-50.4313}{-6.4086}
	\tdplotdrawarc[tdplot_rotated_coords, thick]{(O)}{\rvec + \drvec}{0}{12.8173}{}{}
	%
	\tdplotsetrotatedcoords{55}{-30.3813}{-8.6492}
	\tdplotdrawarc[tdplot_rotated_coords, thick]{(O)}{\rvec}{0}{17.2983}{}{}
	\tdplotdrawarc[tdplot_rotated_coords, thick]{(O)}{\rvec + \drvec}{0}{17.2983}{}{}
\end{pgfonlayer}

%Fill Color
\begin{pgfonlayer}{main}
	%Front
	\fill[black, opacity=0.15] (E) to (A)  to[bend left=4] (B) to (F) to[bend right=4] cycle;
	\fill[black, opacity=0.6] (E) to[bend left=4] (F)  to[bend left=2] (G) to[bend right=6.5] (H) to[bend right=4] cycle;
	\fill[black, opacity=0.4] (F) to[bend left=2] (G) to[bend left=1.5] (C) to[bend right=2.5] (B) to[bend right=4] cycle;
	\end{pgfonlayer}
\begin{pgfonlayer}{background}
	%Back
	\fill[black!50, opacity=0.5] (A) to[bend left=2] (D) to[bend left=6] (C) to[bend right=2.5] (B) to[bend right=4] cycle;
	\fill[black!50, opacity=0.5] (A) to[bend left=2] (D) to (H) to[bend right=2.5] (E) to[bend right=4] cycle;
	\fill[black!50, opacity=0.5] (D) to (H) to[bend left=6] (G) to[bend right=2] (C) to[bend right=6] cycle;
\end{pgfonlayer}


\end{tikzpicture}

\end{document}
```

E, come evidenziato, $\rho^2\sin \phi$ è il determinante della matrice Jacobiana associata al cambiamento in coordinate polari.
# COORDINATE CILINDRICHE
>[!def] COORDINATE CILINDRICHE
>Il cambiamento in coordinate cilindriche è definito da
>$$ \forall \rho\geq 0, \theta \in[0,2\pi], z\in \mathbb{R} \text{ , }\varphi(\rho,\theta,z) = (\rho \cos\theta,\rho \sin\theta,z) $$

```tikz
\usepackage{tikz}
\usepackage{tikz-3dplot}

\usetikzlibrary{angles, quotes, intersections}

%Styles
\tikzset{axis/.style={thick,-latex}}
\tikzset{vec/.style={thick,blue}}
\tikzset{univec/.style={thick,red,-latex}}

\begin{document}
	
	\tdplotsetmaincoords{70}{110}
	%
	\pgfmathsetmacro{\thetavec}{48.17}
	\pgfmathsetmacro{\phivec}{63.5}
	%
	
	\begin{tikzpicture}[tdplot_main_coords]
		%Axis
		\draw[axis] (0,0,0) -- (6,0,0) node [pos=1.1] {$x$};
		\draw[axis] (0,0,0) -- (0,6,0) node [pos=1.05] {$y$};
		\draw[axis] (0,0,0) -- (0,0,5.5)  node [pos=1.05] {$z$};   
		
		%Help Lines
		\draw[dashed] (2,4,4) -- (2,4,0);
		\draw[dashed] (2,0,0) -- (2,4,0) node [pos=-0.1] {$x$};
		\draw[dashed] (0,4,0) -- (2,4,0) node [pos=-0.35, left] {$y$};
		\draw[dashed] (0,0,4) -- (2,4,4) node [pos=-0.1] {$z$};
		\draw[dashed, tdplot_main_coords] (4.47,0,0) arc (0:90:4.47);
		
		%Unit Vectors
		\tdplotsetcoord{P'}{1}{90}{\phivec}
    	\draw[univec] (2,4,0) -- ($(P')+(2,4,0)$) node [pos=1.3] {$\hat{\rho}$};
    	\tdplotsetcoord{P''}{1}{90}{90+\phivec}
    	\draw[univec] (2,4,0) -- ($(P'') + (2,4,0)$) node [pos=1.3] {$\hat{\theta}$};
    	
    	%Vectors
		\tdplotsetcoord{P}{6}{\thetavec}{\phivec}
		\draw[vec] (0,0,0) -- (P);
		\draw[thick] (0,0,0) -- (2,4,0) node [pos=0.6, above] {$\rho$};
		
		%Point
		\node[fill=black, circle, inner sep=0.8pt] at (2,4,4) {};
		
		%Angles
		\tdplotdrawarc{(0,0,0)}{0.7}{0}{\phivec}{below}{$\theta$}
		
	\end{tikzpicture}
	
\end{document}
```

>[!prop] CAMBIO IN COORDINATE CILINDRICHE
>Sia $f:\Omega\to \mathbb{R}$ integrabile.
>Si ha
>$$ \int_{\Omega}f(x,y,z)dxdydz = \int_{\Sigma}f(\rho \cos\theta,\rho \sin\theta,z)\rho d\rho d\theta dz $$
>Dove, come prima, $\Sigma$ è la riparametrizzazione dell'insieme $\Omega$.

In questo caso, il determinante della matrice Jacobiana associata al cambio in coordinate cilindriche è semplicemente $\rho$.
# SOLIDI DI ROTAZIONE
## ROTAZIONE RISPETTO AD UN ASSE COORDINATO
Il cambio di variabili in coordinate cilindriche diventa ancora più conveniente quando il dominio $\Omega$ è di *rotazione intorno all'asse $z$*.
>[!def] INSIEME DI ROTAZIONE ATTORNO ALL'ASSE Z
>Si dice che $\Omega \subset \mathbb{R}^3$ è insieme di rotazione ottenuto ruotando $D\subset[0,+\infty)_{x}\times \mathbb{R}_{z}$ attorno all'asse $z$ se
>$$ (x,y,z)\in\Omega \implies (\sqrt{ x^2+y^2 },z)\in D $$

Chiaramente possiamo ottenere solidi di rotazione anche ruotando insiemi $D'$ attorno all'asse $x$ o $y$. La definizione è equivalente, cambiando opportanamente i ruoli delle coordinate $x,y,z$.
## MASSA E BARICENTRO
Sia $G\subset \mathbb{R}^n$ con $n=2$ o $n=3$.
Sia $\mu:G\to[0,+\infty)$ continua e non identicamente nulla.
Abbiamo allora:
>[!def] MASSA DI $G$
>$$ M_{G} = \int_{G}\mu dG $$
>Dove $dG$ è l'elemento infinitesimale di area o volume in $G$.

Il baricentro è invece:
>[!def] BARICENTRO DI $G$
>E' il punto $x_{G}\in \mathbb{R}^n$ di coordinate:
>$$ x_{i,G} := \frac{1}{M_{G}}\int_{G}x_{i}\mu dG \text{ , }i=1,\dots,n$$

Se $\mu=1$ $\forall x \in G$ si parla di *baricentro geometrico*.
## VOLUME
>[!theorem] VOLUME DI UN SOLIDO DI ROTAZIONE (PAPPO-GULDINO)
>Il volume di un insieme $\Omega$ ottenuto ruotando un dominio limitato $D$ del semipiano $xz$ con $x\geq 0$ attorno all'asse $z$ vale:
>$$ \mathrm{Vol}(\Omega) = 2\pi \int_{D}xdxdz $$

Quest'equazione è una semplice applicazione del cambio in coordinate cilindriche applicato all'integrale
$$
\mathrm{Vol}(\Omega) = \int_{\Omega}dxdydz
$$
unito all'osservazione che il piano $\rho z$ è equivalente al piano $xz$ per definizione di solido di rotazione.