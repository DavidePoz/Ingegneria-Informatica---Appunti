from pyscipopt import Model, quicksum
"""Problema: massimizzare il profitto di un'azienda chimica che produce
   gas e ioni cloro.
   - Il profitto per unità di gas è 40, per unità di ioni cloro è 50.
   - Ciascun prodotto richiede una certa quantità di componenti chimici per essere prodotto:
     - Gas: 1 unità di azoto, 3 unità di idrogeno, 0 unità di cloro
     - Ioni cloro: 1 unità di azoto, 4 unità di idrogeno, 1 unità di cloro
   - La ditta dispone di 50 unità di azoto, 100 unità di idrogeno e 40 unità di cloro.
"""

# Inizializziamo i dati del problema con dizionari e liste
products = ["gas", "chloride"]
components = ["nitrogen", "hydrogen", "chlorine"]
demand = {"gas":        {"nitrogen": 1, "hydrogen": 3, "chlorine": 0}, 
          "chloride":   {"nitrogen": 1, "hydrogen": 4, "chlorine": 1}}
profit = {"gas": 40, "chloride": 50}
stock  = {"nitrogen": 50, "hydrogen": 100, "chlorine": 40}


# Inizializza il modello del problema
def init_model():
    model = Model()

    # NOTA: nell'esempio precedente avevamo inserito le variabili "a mano" direttamente 
    #       come variabili del modello. Ora invece le inseriamo in un dizionario per poter
    #       scalare meglio il problema in caso di necessità
    x = {}

    # Aggiungiamo i prodotti
    for p in products:
        x[p] = model.addVar(name="x[%s]" % p)

    # Definiamo la funzione obiettivo
    model.setObjective(quicksum(profit[p] * x[p] for p in products), sense="maximize")

    # Aggiungiamo i vincoli del problema
    for c in components:
        model.addCons(quicksum(demand[p][c] * x[p] for p in products) <= stock[c])

    model.data = x

    return model


if __name__ == "__main__":
    model = init_model()
    model.writeProblem("gas_production_v2.lp")
    model.optimize()

    x = model.data
    print("Valore ottimo f. obiettivo: ", model.getObjVal())
    for p in products:
        print("{} = {}".format(p, model.getVal(x[p])))
