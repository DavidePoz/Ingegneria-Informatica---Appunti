# PROGETTO INGEGNERIA DEL SOFTWARE 
- Terzo Appello, AA 2025-2026
- Nome: Davide
- Cognome: Pozzebon
- Matricola: 2137381

# COMANDI
## COMPILAZIONE
Per la compilazione è possibile usare il seguente comando 

```bash
javac -d bin -cp "JUnit/*" myAdapter/* myTest/*
```

## ESECUZIONE TEST
Per l'esecuzione aggregata di tutte le Test Suite:

```bash
java -cp "bin:JUnit/*" myTest.TestRunner
```

Per eseguire le singole Test Suite, invece: 

```bash
java -cp "bin:JUnit/*" org.junit.runner.JUnitCore myTest.VectorAdapterTest
java -cp "bin:JUnit/*" org.junit.runner.JUnitCore myTest.SubListAdapterTest
java -cp "bin:JUnit/*" org.junit.runner.JUnitCore myTest.IteratorAdapterTest
java -cp "bin:JUnit/*" org.junit.runner.JUnitCore myTest.ListIteratorAdapterTest
java -cp "bin:JUnit/*" org.junit.runner.JUnitCore myTest.StackAdapterTest
```

## GENERAZIONE DOCUMENTAZIONE
Per generare la documentazione è possibile utilizzare:

```bash
javadoc -d docs -cp "JUnit/*" myAdapter/* myTest/*
```

# ARCHITETTURA DEL PROGETTO
## STRUTTURA DIRECTORY
I file, le classi e la documentazione del progetto sono organizzati come segue:

```plaintext
.
├── bin/
│   ├── myAdapter/ 
│   └── myTest/ 
├── docs/
│   ├── index.html
│   └── (altri file javadoc)
├── JUnit/
│   ├── hamcrest-core-1.3-javadoc.jar
│   ├── hamcrest-core-1.3.jar
│   └── junit-4.13.2-javadoc.jar
│   └── junit-4.13.2.jar
├── myAdapter/
│   ├── HCollection.java
│   ├── HIterator.java
│   ├── HList.java
│   ├── HListIterator.java
│   ├── MyUnsupportedOperationException.java
│   ├── AbstractHList.java
│   ├── VectorAdapter.java
│   └── StackAdapter.java
├── myTest/
│   ├── VectorAdapterTest.java
│   ├── SubListAdapterTest.java
│   ├── IteratorAdapterTest.java
│   ├── ListIteratorAdapterTest.java
│   ├── StackAdapterTest.java
│   └── TestRunner.java
├── README.txt
└── README.md
```

Notare che README.txt è una versione ridotta di README.md, contenente solo le informazioni
riguardanti i comandi da usare.

## DESCRIZIONE DEI PACKAGE E DEL DESIGN
Il progetto è diviso in due package: myAdapter e myTest

### Package myAdapter
Questo package contiene l'implementazione effettiva dell'adapter.

Durante l'implementazione, nella fase di scelta dei legami fra le classi ho deciso di
cercare di replicare (per quanto possibile) la struttura dell'ambiente J2SE 1.4.2: ho
quindi creato una implementazione scheletrica AbstractHList, la quale funge da classe base
sia per l'implementazione di VectorAdapter, sia per l'implementazione di SubListAdapter, classe 
interna a VectorAdapter pensata per gestire correttamente il metodo `int subList(int fromIndex, int toIndex)`.

Gli iteratori sono già implementati come classi interne ad AbstractHList, per cui 
non è stato necessario ridefinirli in VectorAdapter.

Infine, sempre come in J2SE 1.4.2, la classe StackAdapter estende VectorAdapter
aggiungendo i metodi tipici della struttura stack.

### Package myTest
Esiste una classe di test per ogni classe concreta.

VectorAdapterTest testa il corretto funzionamento della classe 
VectorAdapter (la prima effettivamente istanziabile nella gerarchia), ma 
anche di AbstractHList (andando a testare anche tutti i metodi ereditati).

SubListAdapter, IteratorAdapter e ListIteratorAdapter sono tre classi
distinte, e pertanto ciascuna presenta una classe di test specifica.

Infine, StackAdapterTest testa il corretto funzionamento della classe
StackAdapter.

Ogni classe di test suddivide i propri test in sezioni, che rappresentano dei
"blocchi logici", all'interno dei quali i test analizzano situazioni "simili",
cercando di testare i metodi:
- In condizioni d'uso "normali".
- In caso di esecuzioni ripetute.
- Nei casi limite e in quelli che, da specifica, dovrebbero lanciare eccezioni.

Il TestRunner permette l'esecuzione aggregata dei test e stampa i risultati
dell'esecuzione tramite il proprio metodo main.
