from pyscipopt import Model, quicksum
"""Problema: minimizzare il costo di trasporto per un'azienda che spedisce prodotti 
   da due sorgenti a tre destinazioni.
   - Il costo di trasporto per unità di prodotto è dato da una matrice 
     Costo[s,d] per ogni sorgente s e destinazione d.
   - La ditta dispone di una certa quantità di prodotto in ciascuna sorgente (Produzione[s])
   - La ditta deve soddisfare una certa domanda in ciascuna destinazione (Domanda[d]).
"""

# Insiemi
Sorgenti = ["A", "B"]
Destinazioni = [1, 2, 3]

# Parametri
Costo = {}
Costo['A', 1] = 2.5
Costo['A', 2] = 1.7
Costo['A', 3] = 1.8
Costo['B', 1] = 2.5
Costo['B', 2] = 1.8
Costo['B', 3] = 1.4

Produzione  = {'A': 350, 'B': 600}
Domanda     = {1: 325, 2: 300, 3: 275}

# Inizializza il modello del problema
def init_model():
    model = Model()

    # Aggiungiamo le variabili: x[s,d] = q.tà spedita da s verso d
    x = {}
    for s in Sorgenti:
        for d in Destinazioni:
            x[s, d] = model.addVar(name="x[%s, %i]" % (s, d))

    # Funzione obiettivo (da minimizzare)
    model.setObjective(quicksum(Costo[s,d] * x[s,d] for s in Sorgenti for d in Destinazioni))

    # Vincoli di produzione: tot spedito <= q.tà prodotta (per ogni sorgente)
    for s in Sorgenti:
        model.addCons(quicksum(x[s,d] for d in Destinazioni) <= Produzione[s])

    # Vincoli di domanda: tot ricevuto >= q.tà richiesta (per ogni destinazione)
    for d in Destinazioni:
        model.addCons(quicksum(x[s,d] for s in Sorgenti) >= Domanda[d])

    model.data = x

    return model

if __name__ == '__main__':
    model = init_model()
    model.writeProblem("transport.lp")
    model.optimize()
    x = model.data

    for s in Sorgenti:
        for d in Destinazioni:
            print("{} -> {}: {}".format(s, d, model.getVal(x[s,d])))
