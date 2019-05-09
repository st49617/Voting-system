Toto schema slouzi predevsim jako prehled implementovanych ficur a TODO.

Cela git slozka by mela fungovat jako idea projekt. Muze byt zapotrebi idee nastavit jednotlive moduly, mne slo automaticky naimportovat pres gradle.build.

slozky:
  voting-system-app:
    frontendova aplikace;
  voting-system-application:
    puvodni monoliticka aplikace, ktera je postupne zuzovana o odebrane mikroservisy;
    v momente, kdy budou vsechny mikroservisy hotove, bude pepracovana na gateway;
    v ramci oddelovani je tato aplikace pokazde revidovana na pouziti nove servisy, aby byla zachovana funkcionalita;
    port: 8080;
    jmeno instance: voting-system-application
    TODO zmena na gateway;
  user-service:
    user mikroservisa - neprobihala zde zadna komunikace s okolim, je tedy jen ciste odriznuta;
    port: 8078;
    jmeno instance: user-service;
  meeting-service:
    meeting mikroservisa - zajistena komunikace se suggestion API ve voting-system-application
    TODO komunikace se suggestion microservice
    port: 8079;
    jmeno instance: meeting-service;
  config-server:
    konfiguracni server - obsahuje nastaveni pro jednotlive mikroservisy;
    TODO nastaveni pro mikroservisy, ktere zatim nejsou oddeleny;
    port: 8888;
    jmeno instance: config-server;
  eureka-server;
    load balancer, rozhrani pro feign klienty jednotlivych servis;
    TODO load balance voting API;
    port: 8761;
    jmeno instance: eureka-server;
    
TODO:
  voting microservice:
    oddeleni voting mikroservisy a zajisteni komunikace s okolim;
    bude obsahovat voting API a bude load balancovana;
    bude mit svuj zaznam v configu;
    ocekavany port/jmeno: 8077/voting-service;
  suggestion microservice:
    oddeleni suggestion mikroservisy a zajisteni komunikace s okolim;
    bude obsahovat suggestion API a tag API;
    bude mit svuj zaznam v configu;
    ocekavany port/jmeno: 8076/suggestion-service;
  gateway:
    implementace pristupoveho bodu, bude slouzit namisto nynejsi monoliticke aplikace;
    ocekavany port/jmeno: 8080/gateway;
  CORS:
    vyreseni cross-origin, patrne filtrem na gateway;
  circuit-braker:
    je prubezne implementovan na urovni jednotlivych mikroservis;
  komunikace s front-endy:
    vubec nemam tuseni. :D Pomalu abychom se zacali modlit, vzyvat satana a tak.
