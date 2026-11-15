package myTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.List;

/**
 * <p>
 * <b>Summary</b> Classe esecutore che avvia tutte le suite di test JUnit
 * definite per il progetto e stampa un riepilogo con il numero totale di
 * test eseguiti, il conteggio dei fallimenti e il tempo di esecuzione in
 * millisecondi. Le classi di test eseguite coprono {@link myAdapter.VectorAdapter},
 * {@link myAdapter.StackAdapter}, le viste {@code subList}, gli iteratori
 * semplici e bidirezionali.
 * <br>
 * Per eseguire questa classe da riga di comando:
 * {@code java -cp "bin:JUnit/*" myTest.TestRunner}
 * </p>
 * <p>
 * <b>Test Case Design</b> La classe non contiene test methods propri;
 * esegue le 5 classi di test definite nel package {@code myTest} utilizzando
 * {@link org.junit.runner.JUnitCore#runClasses(Class...)} e stampa un
 * riepilogo con il numero totale di test eseguiti, il conteggio dei
 * fallimenti e il tempo di esecuzione in millisecondi. In caso di
 * fallimenti, vengono stampati i dettagli di ciascuno.
 * <br>
 * <b>Versione JUnit:</b> Questa classe utilizza il framework JUnit 4.13.
 * <p>
 * <b>Test Cases</b>
 * </p>
 * <p>
 * <b>Classi di test eseguite (5)</b>
 * </p>
 * <ul>
 * <li>{@link VectorAdapterTest} - Test suite per {@link myAdapter.VectorAdapter}:
 * 115 test che coprono costruttori, primitivi, operazioni bulk, ricerca,
 * eguaglianza, metodi Vector, capacity e API legacy.</li>
 * <li>{@link SubListAdapterTest} - Test suite per le viste {@code subList}:
 * 34 test che coprono costruzione, lettura, modifica, operazioni bulk,
 * iteratori, bounds e semantica backed tra vista e lista originale.</li>
 * <li>{@link IteratorAdapterTest} - Test suite per l'iteratore semplice
 * {@link myAdapter.HIterator}: 15 test che coprono navigazione, rimozione e stati illegali.</li>
 * <li>{@link ListIteratorAdapterTest} - Test suite per l'iteratore
 * bidirezionale {@link myAdapter.HListIterator}: 22 test che coprono navigazione avanti e
 * indietro, modifiche ({@code set}, {@code add}, {@code remove}),
 * eccezioni e bounds.</li>
 * <li>{@link StackAdapterTest} - Test suite per {@link myAdapter.StackAdapter}:
 * 17 test che coprono push, pop, peek, empty, search, eccezioni
 * {@link java.util.EmptyStackException} e ereditarietà da VectorAdapter.</li>
 * </ul>
 */
public class TestRunner {

    /**
     * Costruttore pubblico di default.
     */
    public TestRunner() {}

    /**
     * Esegue tutte le suite di test e stampa un riepilogo.
     *
     * @param args argomenti della riga di comando (non utilizzati)
     */
    public static void main(String[] args) {

        // Esecuzione di tutte le suite di test
        Result result = JUnitCore.runClasses(
                VectorAdapterTest.class,
                SubListAdapterTest.class,
                IteratorAdapterTest.class,
                ListIteratorAdapterTest.class,
                StackAdapterTest.class
        );

        // Stampa dei risultati
        System.out.println("========================================");
        System.out.println("    ESECUZIONE AGGREGATA DEI TEST       ");
        System.out.println("========================================");
        System.out.println("Test eseguiti:   " + result.getRunCount());
        System.out.println("Fallimenti:      " + result.getFailureCount());
        System.out.println("Tempo esecuzione " + result.getRunTime() + " ms");
        System.out.println("========================================");

        // Stampa dettagli dei fallimenti 
        if (result.getFailureCount() > 0) {
            System.out.println("\nFAILURES DETAILS:");
            List failures = result.getFailures(); 
            int count = failures.size();
            for (int i = 0; i < count; i++) {
                Failure failure = (Failure) failures.get(i); 
                System.out.println((i + 1) + ") " + failure.toString());
            }
            System.out.println();
        } else {
            System.out.println("\nTutti i test sono stati eseguiti con successo!");
        }

    }
}
