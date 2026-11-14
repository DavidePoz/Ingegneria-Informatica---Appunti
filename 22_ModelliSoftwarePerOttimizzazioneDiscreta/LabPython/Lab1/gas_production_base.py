from pyscipopt import Model
"""Problema: massimizzare il profitto di un'azienda chimica che produce
   gas e ioni cloro.
   - Il profitto per unità di gas è 40, per unità di ioni cloro è 50.
   - Ciascun prodotto richiede una certa quantità di componenti chimici per essere prodotto:
     - Gas: 1 unità di azoto, 3 unità di idrogeno, 0 unità di cloro
     - Ioni cloro: 1 unità di azoto, 4 unità di idrogeno, 1 unità di cloro
   - La ditta dispone di 50 unità di azoto, 100 unità di idrogeno e 40 unità di cloro.
"""

# Inizializza il modello del problema
def init_model():

    model = Model()

    # Aggiungiamo due variabili al modello
    # NOTA: Di default sono continue e non negative
    gas = model.addVar("gas")
    chloride = model.addVar("chloride")

    # Impostiamo la funzione obiettivo (da massimizzare)
    model.setObjective(40 * gas + 50 * chloride, sense="maximize")

    # Aggiungiamo i vincoli
    model.addCons(gas + chloride <= 50)
    model.addCons(3 * gas + 4 * chloride <= 100)
    model.addCons(chloride <= 40)

    # Salviamo le variabili del problema per potervi accedere in seguito
    model.data = gas, chloride

    return model


# Istanziamo il modello e risolviamo il problema
if __name__ == "__main__":
    
    # Inizializziamo il modello, settiamo il file su cui scrivere il modello e ottimizziamo la soluzione
    model = init_model()
    model.writeProblem("gas_production_base.lp")
    model.optimize()

    # Recuperiamo le variabili del modello e stampiamo i risultati
    gas, chloride = model.data
    print("Valore ottimo f. obiettivo: ", model.getObjVal())
    print("Gas: ", model.getVal(gas))
    print("Chloride: ", model.getVal(chloride))
