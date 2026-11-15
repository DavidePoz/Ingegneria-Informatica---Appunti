Progetto Ingegneria del Software - Terzo Appello, AA 2025-2026
Nome: Davide
Cognome: Pozzebon
Matricola: 2137381

Per la compilazione è possibile usare il seguente comando 

    javac -d bin -cp "JUnit/*" myAdapter/* myTest/*

Per l'esecuzione aggregata di tutte le Test Suite:

    java -cp "bin:JUnit/*" myTest.TestRunner

Per eseguire le singole Test Suite, invece: 

    java -cp "bin:JUnit/*" org.junit.runner.JUnitCore myTest.VectorAdapterTest
    java -cp "bin:JUnit/*" org.junit.runner.JUnitCore myTest.SubListAdapterTest
    java -cp "bin:JUnit/*" org.junit.runner.JUnitCore myTest.IteratorAdapterTest
    java -cp "bin:JUnit/*" org.junit.runner.JUnitCore myTest.ListIteratorAdapterTest
    java -cp "bin:JUnit/*" org.junit.runner.JUnitCore myTest.StackAdapterTest

Per generare la documentazione è possibile utilizzare:

    javadoc -d docs -cp "JUnit/*" myAdapter/* myTest/*
