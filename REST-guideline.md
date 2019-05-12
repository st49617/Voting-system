# Struktura a návrh REST API

V tomto dokumentu bude postupně kompletně popsáno REST API naší aplikace.

**Zde popsanou strukturu a pravidla budeme všichni dodržovat.**

## Základní struktura a pravidla

Kontrolery budou vypadat následovně:
- název: `[NázevEntity]RestController.java` _(např.: SuggestionRestController.java)_
- funkcionalita: budou obsahovat pouze metody REST API
- implementace: tyto kontrolery budou využívat jednotlivé Service a jejich metody
- všechny metody/operace typu "get/select" - `@GetMapping`
- všechny metody/operace typu "add/insert" - `@PostMapping`
- všechny metody/operace typu "delete" - `@DeleteMapping`

_(Metody kontroleru, které využívají GET a DELETE budou mít parametry v URL, ty, které využívají POST v těle požadavku, viz. -> Informace o jednotlivých HTTP metodách: [Souhrn HTTP metod](https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol#Summary_table))_

Struktura Url:

`(root aplikace)/api/[NázevEntity]/[TypOperace]/([ParametryOperace])`

Každý kontroler (až na výjimky) by  měl mít všechny tyto základní operace:
- `get` - vrací entitu na základě Id
- `get-all` - vrací seznam všech entit
- `(get-page)` - vrací stránku/omezený seznam (10?) entit
- `add` - vkládá novou entitu
- `delete` - maže entitu na základě Id

příklady:
- `/api/voting/get/1` - vrací hlasování s Id 1
- `/api/suggestion/get-all` - vrací seznam všech návrhů

A následně různé další **operace specifické pro danou entitu**.

## Specifická struktura a pravidla pro jednotlivé Entity

Tady se budou postupně přidávat jednotlivé sekce pro jednotlivé entity a url mapování pro jejich operace

### Voting entita

##### POST
- `/add` - vkládá případně mění hlasování, 
formát vkládaných dat:
`{
 	"userId":1,
 	"suggestionId":1,
 	"vote": "NE"
 }`
 <br />
 parametr `vote` nabývá hodnot `[ANO, NE, ZDRZEL]`
 
##### GET
- `/get/{id}` - vrací nalezené hlasování podle zadaného id
- `/get-all` - vrací seznam všech hlasování
- `/get-user/{userId}` - vrací seznam všech hlasování pro vybraného uživatele
- `/get-suggestion/{suggestionId}` - vrací seznam všech hlasování pro vybraný návrh

### Meeting entita
Meeting (zasedani) predstavuje schuzi zastupitelstva.
Je reprezentovan svym DTO (::MeetingDto), jehoz soucasti je seznam bodu jednani (::List<SuggestionForMeetingDto>).

##### POST
- `/add` - vlozeni/zmena meetingu, format:
'{
	"timeStamp":Timestamp
}'

##### GET
- `/get/{id}`		- vraci zasedani podle zadaneho id
- `/get-all`		- vraci seznam vsech zasedani
- `/delete/{id}`	- maze zasedani podle zadaneho id

### Tag entita
Tag (štítek) predstavuje co se budu se hlasovat.
Je reprezentovan svym DTO (::TagDto), jehoz soucasti je seznam bodu jednani (::List<SuggestionForTagDto>).
	
##### GET
- `/get/{id}`		- vraci tag (štítek) podle zadaneho id
- `/get-all`		- vraci seznam vsech tagů (štítků)
- `/delete/{id}`	- maze tag (štítek) podle zadaneho id
