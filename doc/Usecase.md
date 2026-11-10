# Use Cases:
## UC1: StartMenu
Brugern vælger mellem at oprette bruger (UC2), logge ind eller afslutte program (UC10). <p>
Efter log ind, sendes brugeren videre til UC3: MainMenu

## UC2: Opret bruger
Brugeren indtaster username og password og bliver oprettet som bruger.

## UC3: MainMenu
Brugeren får følgende muligheder: <p>
Søge efter et bestemt medie (UC4) <p>
Se liste over sete medier (UC5) <p>
Se liste over gemte medier (UC6) <p>
Log ud (UC 1), brugeren logges ud og sendes tilbage til den første menu

## UC4: Search
Brugeren kan søge efter et medie ud fra kritirierne: <p>
Titel <p>
Genre <p>
Rating <p>
Release year

Efter et medie er valgt, skal brugeren nu vælge om mediet skal afspilles (UC8) eller gemmes (UC9).
Afspilles mediet, bliver det tilføjet til listen af sete medier. <p>
Gemmes mediet, bliver det tilføjet til listen af gemte medier.

## UC5: Show list of watched medias
Brugeren bliver præsenteret for en liste af medier som brugeren har afspillet

## UC6: Show list of saved medias
Brugeren bliver præsenteret for en liste af medier som brugeren har gemt. Herefter skal brugeren vælge et bestemt medie, som de vil afspille (UC8) eller fjerne fra listen (UC7)

## UC7: Remove from list of saved medias
Det valgte medie bliver fjernet fra listen af gemte medier.

## UC8: Play media
Brugeren får teksten fx. "Titanic afspilles..."
Mediet bliver tilføjet til listen af sete medier. Er mediet på listen af gemte medier, bliver det fjernet derfra.

## UC9: Save media
Brugeren gemmer mediet til listen af gemte medier. En "Se senere" liste.


## UC10: Close program


