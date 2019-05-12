# Voting-system
Systém pro hlasování zastupitelstva města

## Zadání
[Systém pro hlasování zastupitelstva města](https://drive.google.com/file/d/1DdG2Llw376lCCrdeZjT5QZT4FvULFeLy/view)

## Struktura projektu
V root složce jsou podložky pro jednotlivé aplikace. Pro tvoření dalších aplikací zachovejte stejnou strukturu balíčků, domény atd. Struktura jednotlivých aplikací bude vypadat následovně:

- controller,
- model,
- dao,
- service.

_Takto stanovenou strukturu se snažte dodržovat._ 

## Databáze
- typ databáze: MySQL,
- login: root,
- heslo: (prázdné),
- název databází: voting_system, voting_system_meeting_service, voting_system_suggestion_service, voting_system_user_service, voting_system_voting_service.

_(login: 'root', a heslo: '', by měly být výchozí při použití XAMPPu)_

## Postup práce
Postup práce by měl probíhat v následujících krocích:
1. fork projektu k sobě,
2. vytvoření nové větve,
3. vypracování 1 issue,
4. pull request do původního repozitáře do master větve,
5. do komentáře pull reguestu uvádějte 'resolve #cislo issue', aby se pull reguest a issue automaticky propojily a issue se po merge automaticky zavřela,
6. jiný člen týmu schválí pull request a mergne do mastru,
7. smazání nové větve po dokončení issue.

_Prosím o dodržování tohoto postupu._

## Spuštění aplikace
Aplikace je projektem vývojového prostředí IntelliJ IDEA.
1. Otevřít celou složku Voting_system jako projekt,
2. jednotlivé moduly načíst přes build.gradle,
3. klasicky spustit jednotlivé moduly.

Pořadí spouštění: nejdříve eureka a config server, teprve po nich microservices a gateway.
