Toto schema slouzi predevsim jako prehled implementovanych ficur a TODO.

Cela git slozka by mela fungovat jako idea projekt. Muze byt zapotrebi idee nastavit jednotlive moduly, mne slo automaticky naimportovat pres gradle.build.

slozky:
  + voting-system-app:
    + frontendova aplikace;
  + voting-system-application:
    + puvodni monoliticka aplikace, ktera je postupne zuzovana o odebrane mikroservisy;
    + v momente, kdy budou vsechny mikroservisy hotove, bude prepracovana na gateway;
    + v ramci oddelovani je tato aplikace pokazde revidovana na pouziti nove servisy, aby byla zachovana funkcionalita;
    + port: 8080;
    + jmeno instance: voting-system-application
    + TODO zmena na gateway;
  + user-service:
    + user mikroservisa - neprobihala zde zadna komunikace s okolim, je tedy jen ciste odriznuta;
    + TODO vlastni DB;
    + port: 8078;
    + jmeno instance: user-service;
  + meeting-service:
    + meeting mikroservisa - zajistena komunikace se suggestion mikroservisou
    + TODO vlastni DB;
    + port: 8079;
    + jmeno instance: meeting-service;
  + voting-service:
    + voting mikroservisa - zajistena komunikace se suggestion mikroservisou
    + TODO vlastni DB;
    + port: 8077
    + jmeno instance: voting-service
  + suggestion-service:
    + suggestion resp. tag mikroservisa - zajistena komunikace s meeting mikroservisou
    + TODO vlastni DB;
    + port: 8076
    + jmeno instance: suggestion-service
  + config-server:
    + konfiguracni server - obsahuje nastaveni pro jednotlive mikroservisy;
    + port: 8888;
    + jmeno instance: config-server;
  + eureka-server;
    + load balancer, rozhrani pro feign klienty jednotlivych servis;
    + TODO load balance voting API;
    + port: 8761;
    + jmeno instance: eureka-server;
    
TODO:
  + gateway:
    + implementace pristupoveho bodu, bude slouzit namisto nynejsi monoliticke aplikace;
    + ocekavany port/jmeno: 8080/gateway;
  + CORS:
    + vyreseni cross-origin, patrne filtrem na gateway;
  + komunikace s front-endy:
    + vubec nemam tuseni. :D Pomalu abychom se zacali modlit, vzyvat satana a tak.
