# DraftTicketPriceCalculator

Projekta realizēšanai tika izmantots gradle, junit4.12 un JDK15.

Projekts realizēts ar šādām klasēm - **Passenger, TicketPrice**, **DraftTicket** un **DraftTicketPriceCalculator**.

**Passenger** objekts reprezentē pasažieri, tam ir 2 vērtības - isChild un luggageItemCount.
Galvenā funkcija (calculateDraftTicketPrice) kā ievaddatus izmanto tieši šī objekta sarakstu.

**TicketPrice** objekts reprezentē konkrētā pasažiera individuālās biļetes cenas. Tas tika realizēts kā extension klasei *Passenger*, ieviešot papildus 2 vērtības - passengerTicketPrice un luggageTicket price.
Šīs klases metodes nodrošina gan pasažiera, gan bagāžas biļetes cenu aprēķināšanu konkrētajam pasažierim. Klasē atrodas arī metode, kas izveido pašu TicketPrice objektu no Passenger objekta.

**DraftTicket** objekts reprezentē izvaddatus. Tam ir 3 vērtības - individuālo biļešu cenu saraksts, kopējā biļetes cena un valūta (kas gan nebija prasīts, tomēr nolēmu ieviest, lai būtu pieejama visa informācija, lai varētu no izvaddatiem konstruēt rezultātu, kas norādīts uzdevuma aprakstā).
Šīs klases metode **createDraftTicketFromTicketPriceList** nodrošina izvaddatu sagatavi.

**DraftTicketPriceCalculator** ir klase, kurā atrodas galvenā programmas funkcija - **calculateDraftTicketPrice**

Skaitliskās vērtības tika realizētas, izmantojot BigDecimal klasi, lai atvieglotu aprēķinus, kas saistīti ar biļešu cenām.

Galvenās programmas klases atrodas src/main/java/ticketapp direktorijā
Servisi atrodas src/main/java/services direktorijā
testi atrodas test/java/ticketapp direktorijā

### Programmas darbība:

calculateDraftTicketPrice pieņem ievaddatos pasažieru sarakstu un maršruta nosaukumu (kas tiek izmantots, lai no servisa iegūtu pamat cenu).
Tālāk notiek iterācija caur katru pasažieri un no katra pasažiera tiek izveidots **TicketPrice** objekts. Tiek aprēķinātas individuālās cenas un attiecīgi piešķirtas jaunizveidotajam objektam. Visi jaunizveidotie objekti tiek ievietoti sarakstā.

Beigās šis saraksts tiek nodots tālākai apstrādei, lai sagatavotu **DraftTicket** klases instanci, kas arī tiek atgriezta kā izvaddati.
