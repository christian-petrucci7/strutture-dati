# Java Data Structures & Algorithms Library (APSD)

Libreria di strutture dati e algoritmi sviluppata in **Java** per l'esame di **Algoritmi e Programmazione per Strutture Dati (APSD)**.

Il progetto implementa una gerarchia completa di Tipi di Dato Astratti (ADT), combinando programmazione generica (`<Data>`), programmazione funzionale (`Predicate`, `Accumulator`) ed ereditarietà multipla tramite interfacce e metodi `default`.

---

## Contesto del Progetto e Contributo Personale

* **Architettura e Specifica (Forniti dai Docenti):** La gerarchia delle interfacce, le firme dei metodi e i diagrammi di progettazione allegati nella documentazione PDF sono stati forniti dai docenti del corso come specifica architetturale di riferimento.
* **Implementazione (Sviluppata da me):** Ho realizzato l'intera implementazione concreta delle classi base e delle strutture dati, scrivendo da zero tutti i metodi, gli iteratori (in avanti, all'indietro e mutabili), la gestione della memoria/riallocazione e gli algoritmi di ricerca e ordinamento.

---

## Strutture Dati Implementate

La libreria è organizzata in due macro-package (`interfaces` e `classes`) e offre doppie implementazioni (basate su **vettori** e su **liste concatenate**):

### 1. Sequenze e Vettori (`sequences`)
* **`Vector` / `CircularVector`:** Vettori statici a dimensione fissa con gestione lineare o circolare degli indici (ottimizzazione degli shift in testa e in coda).
* **`DynVector` / `DynCircularVector`:** Vettori dinamici ridimensionabili automaticamente tramite fattori di crescita e riduzione (`Grow` / `Shrink`).

### 2. Collezioni e Liste (`collections`)
* **`VList` & `LLList`:** Liste implementate rispettivamente tramite vettore dinamico circolare e lista semplicemente concatenata (`LLNode` + riferimenti `Box`).
* **`VSortedChain` & `LLSortedChain`:** Catene ordinate (`Comparable`) con ricerca binaria ($O(\log n)$ su accesso posizionale) e metodi per individuare predecessore e successore.
* **`WSet` & `WOrderedSet`:** Implementazioni wrapper di insiemi (non ordinati e ordinati) basate sulle rispettive catene sottostanti.

### 3. Code e Pile (`deqs`)
* **`WQueue` & `WStack`:** Strutture FIFO (Coda) e LIFO (Pila) realizzate tramite pattern *Wrapper* sulle liste della libreria.

### 4. Utility e Iteratori (`utilities` & `iterators`)
* **`Natural` & `MutableNatural`:** Tipi numerici non negativi (immutabili e mutabili) con controllo esplicito di overflow e underflow.
* **`Box`:** Contenitore mutabile utilizzato per simulare il passaggio di riferimenti (stile puntatori/reference C++).
* **Iteratori personalizzati:** Supporto completo per iterazione `Forward`, `Backward` e `Mutable`, oltre a operazioni funzionali come `TraverseForward`, `FoldForward` e `Filter`.

---

## Struttura del Repository

```text
├── apsd/
│   ├── interfaces/    # Specifiche dei Traits, Container, Sequence, Chain, Set e Iterator (fornite)
│   └── classes/       # Implementazioni concrete di vettori, liste, insiemi, code e pile (sviluppate)
└── docs/              # Diagrammi UML e schemi delle gerarchie forniti dai docenti (PDF)
