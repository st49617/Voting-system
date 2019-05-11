Toto schema slouzi predevsim jako prehled implementovanych ficur a TODO.

Cela git slozka by mela fungovat jako idea projekt. Muze byt zapotrebi idee nastavit jednotlive moduly, mne slo automaticky naimportovat pres gradle.build.

slozky:
  + voting-system-app:
    + frontendova aplikace;
  + gateway:
    + zuul gateway backendu;
    + obsahuje presmerovani na ostatni servisy a cors filtr;
    + port: 8080;
    + jmeno instance: gateway;
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
    + port: 8081 - 8090;
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
    + port: 8761;
    + jmeno instance: eureka-server;
