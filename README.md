#Programma Zomerknutselavond

Deze avond kent geen vast programma. Het idee is dat we in groepjes aan de gang gaan. Tussentijds van groepje wisselen kan natuurlijk ook. Wat je kan gaan doen? Hieronder wat ideeën:

##Data gebruiken

Welke toepassingen kan je verzinnen met de data van Verdwenen Gebouwen? De data is per plaats en / of gebouwtype op te halen, als [JSON](http://www.verdwenengebouwen.nl/search/json/?q=haarlem&type=http://vocab.getty.edu/aat/300005080) of als [GeoJSON](http://www.verdwenengebouwen.nl/search/geojson/?q=haarlem&type=http://vocab.getty.edu/aat/300005080). Ook is [de hele database als mysqldump](http://www.verdwenengebouwen.nl/downloads/gebouwen.sql) op te halen.

Denk aan visualisaties: kaarten, tijdlijnen, afbeeldingen, [grafieken](http://www.verdwenengebouwen.nl/test/tijdlijn/?nr=1), gebouwnamen. Maar ook aan apps: 'hier stond ooit...', vergankelijkheidsroutes langs non-existente gebouwen. Of toepassingen binnen andere sites: verdwenen gebouwen tonen als laag binnen je eigen kaartapplicatie.

Is de data te gebruiken in combinatie met andere (open) data? Streetview? BAG? Een lijst Amsterdamse burgemeesters?

##Data toevoegen / editen

De lijst telt nu 3661 verdwenen gebouwen. Die zijn niet allemaal met de hand verzamelt. Iets van 800 komen uit [DBpedia](http://nl.dbpedia.org/) (categorie voormalig bouwwerk in ...), dik 2000 van [Reliwiki](http://reliwiki.nl/). Ook zijn geografische thesauri van het Rijksmuseum en het Amsterdam Museum gebruikt.

Zijn er meer datasets te verzinnen die verdwenen gebouwen bevatten? Er zijn bijvoorbeeld [kastelen te schrapen](https://nl.wikipedia.org/wiki/Lijst_van_kastelen_in_Drenthe).

En via welke api's zijn afbeeldingen van die gebouwen te vinden? De [Opensearch api](http://beeldbank.amsterdam.nl/api/opensearch/?searchTerms=Huis%20met%20de%20Kabouters) van het Amsterdamse Stadsarchief is misschien een optie - we hebben ook een [lijst gebouwnamen](data/gebouwnamen.csv) van ze gekregen.

Langzamer, maar vaak wel beter, is het ouderwetsche handwerk. Met de verstrekte inloggegevens (je krijgt ze mee naar huis ;-) kan je zowel nieuwe verdwenen gebouwen als missende gegevens en afbeeldingen toevoegen.

Beeldbanken zijn een goede startplaats (zoek bijv. eens op 'gesloopt' of 'sloop') om gebouwen te vinden. Behalve stedelijke en regionale archieven kan je terecht bij landelijk dekkende beeldbanken van [RCE](http://beeldbank.cultureelerfgoed.nl/), [DiMCoN](http://www.dimcon.nl/) en [Europeana](http://www.europeana.eu/portal/).

In de lijstweergave zie je in rood welke gebouwen locatie of type missen. Als er geen jaar van verdwijnen achter staat mist dat nog. En er zijn nog geen afbeeldingen als er geen 'img' achter een gebouwnaam staat.

Behalve beeldbanken is [Topotijdreis](http://topotijdreis.nl/) heel handig, en datzelfde geldt natuurlijk voor ander historisch kaartmateriaal. [Streetview](http://maps.google.com) helpt vaak nog bestaande (buur)panden precies te localiseren. In de [BAGviewer](https://bagviewer.kadaster.nl) wordt soms het bouwjaar van een pand gegeven, dan weet je dus ook wanneer een eventueel vorig pand op die plaats in ieder geval al gesloopt moet zijn.

##Toekomstplannen

Welke kant zou het op moeten / kunnen met dit project? Een bepaald type gebouwen extra aandacht geven? Stadspoorten? Benzinestations? Theaters? Wederopbouwarchitectuur? Rotterdam?

Inzetten op architectuurgeschiedenis en verdwenen gebouwen op architect ontsluiten? Gebouwen op 17e-eeuwse schilderijen? Meer publieksgericht met thematische artikelen rond groepen gebouwen? Of juist meer richten op data en kijken hoe die verder gelinkt kan worden met de rest van de wereld?

