# StreetArtistExercise32
Street Artist problem in Java

### Algoritmo del programma

Il programma StreetArtist simula un artista di strada che disegna ritratti per i clienti. L'algoritmo utilizzato è basato su un meccanismo di sincronizzazione dei processi e prevede l'utilizzo di semafori per gestire l'accesso alle sedie disponibili.

Il programma funziona nel seguente modo:

1. Viene inizializzato un array di booleani chairs per tenere traccia dello stato delle sedie. Tutte le sedie sono inizialmente libere.
2. Viene creato un semaforo SedieDisponibili con un numero massimo di permessi pari al numero di sedie disponibili. Inizialmente, tutti i permessi sono acquisiti.
3. Viene avviato un ciclo infinito che crea un nuovo thread Customer per ogni cliente che arriva.
4. Ogni cliente viene assegnato a un numero univoco incrementale N.
5. All'interno del thread del cliente, viene tentata l'acquisizione di un permesso dal semaforo SedieDisponibili con un tempo massimo di attesa.
   - Se il cliente non riesce ad acquisire un permesso entro il tempo massimo, rinuncia ad aspettare una sedia libera e termina il thread.
6. Una volta ottenuto un permesso, il cliente cerca una sedia libera nell'array chairs.
   - Se trova una sedia libera, la marca come occupata e registra il numero di sedia assegnato.
7. Viene stampato un messaggio che indica che il cliente si è seduto sulla sedia assegnata e sta aspettando il suo turno.
8. Il cliente attende per un periodo casuale di tempo, simulando il tempo necessario per il disegno del ritratto.
9. Viene stampato un messaggio che indica che il cliente sta facendo disegnare il suo ritratto.
10. Il cliente attende nuovamente per un periodo casuale di tempo, simulando il tempo necessario per il completamento del ritratto.
11. Viene stampato un messaggio che indica che il ritratto del cliente è finito.
12. La sedia occupata dal cliente viene liberata impostando il corrispondente valore dell'array chairs a false.
13. Infine, viene rilasciato il permesso acquisito dal cliente, in modo che un altro cliente possa occupare una sedia libera.

### Tecnica di sincronizzazione dei processi

Il programma utilizza il concetto di semafori per sincronizzare l'accesso alle sedie disponibili. Il semaforo SedieDisponibili viene inizializzato con un numero massimo di permessi pari al numero di sedie disponibili ovvero 4.
Quando un cliente arriva, cerca di acquisire un permesso dal semaforo SedieDisponibili. Se il permesso è disponibile, il cliente può procedere a occupare una sedia libera. Se il permesso non è disponibile entro il tempo massimo di attesa, il cliente rinuncia ad aspettare e termina il thread.
Una volta acquisito un permesso, il cliente occupa una sedia libera nell'array chairs, indicando che quella sedia è occupata. Dopo aver completato il disegno del ritratto, il cliente libera la sedia impostando il corrispondente valore dell'array chairs a false.

### Inquadramento rispetto ai problemi di stallo e starvation

Il programma utilizza la tecnica di sincronizzazione dei processi con semafori per evitare il problema del deadlock o stallo. Ogni cliente cerca di acquisire un permesso dal semaforo SedieDisponibili prima di procedere a occupare una sedia. Se il permesso non è disponibile, il cliente non rimane in attesa indefinitamente, ma rinuncia e termina il thread. Questo evita che si verifichi una situazione di stallo in cui tutti i clienti sono bloccati in attesa di una sedia libera.
Per quanto riguarda il problema della starvation, non è presente alcuna garanzia che i clienti vengano serviti in un ordine specifico, ciò significa che, nel lungo termine, ogni cliente ha la possibilità di ottenere una sedia e di essere servito. Tuttavia, non vi è alcuna garanzia sul tempo di attesa dei singoli clienti, quindi alcuni potrebbero dover attendere più a lungo rispetto ad altri.
