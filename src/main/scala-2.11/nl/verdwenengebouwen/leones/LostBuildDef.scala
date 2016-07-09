package nl.verdwenengebouwen
package leones

object LostBuildDef {
  def endPoint00 = "http://www.verdwenengebouwen.nl/search/json/?q="

  def translating(key: String) = translateTable.getOrElse(key, s"Onbekend gebouwtype (${key.takeRight(9)})").capitalize

  def translateTable = Map(
    "http://vocab.getty.edu/aat/300007049" -> "administratiegebouwen",
    "http://vocab.getty.edu/aat/300004898" -> "agrarische gebouwen",
    "http://vocab.getty.edu/aat/300006745" -> "armenhuizen",
    "http://vocab.getty.edu/aat/300007347" -> "badhuizen",
    "http://vocab.getty.edu/aat/300005214" -> "bankgebouwen",

    "http://vocab.getty.edu/aat/300005147" -> "bedrijfsgebouwen",
    "http://vocab.getty.edu/aat/300005674" -> "bejaardentehuizen",
    "http://vocab.getty.edu/aat/300005222" -> "beursgebouwen",
    "http://vocab.getty.edu/aat/300006824" -> "bibliotheken",
    "http://vocab.getty.edu/aat/300007135" -> "bioscopen",

    "http://vocab.getty.edu/aat/300006329" -> "blekerijen",
    "http://vocab.getty.edu/aat/300005574" -> "boerenhoeven",
    "http://vocab.getty.edu/aat/300005567" -> "buitenplaatsen",
    "http://vocab.getty.edu/aat/300006940" -> "bunkers",
    "http://vocab.getty.edu/aat/300007805" -> "busstations",

    "http://vocab.getty.edu/aat/300007102" -> "concertgebouwen",
    "http://vocab.getty.edu/aat/300004963" -> "dierenbehuizingen",
    "http://browser.aat-ned.nl/900027122" -> "doelen",
    "http://vocab.getty.edu/aat/300006247" -> "drukkerijen",
    "http://vocab.getty.edu/aat/300122175" -> "eet- en drinkgelegenheden",

    "http://vocab.getty.edu/aat/300006232" -> "fabrieksgebouwen",
    "http://vocab.getty.edu/aat/300007711" -> "fietsenstallingen",
    "http://vocab.getty.edu/aat/300005213" -> "financiële instellingen",
    "http://vocab.getty.edu/aat/300005707" -> "flatgebouwen",
    "http://vocab.getty.edu/aat/300006795" -> "follies",

    "http://vocab.getty.edu/aat/300000386" -> "gasfabrieken",
    "http://vocab.getty.edu/aat/300266430" -> "gashouders",
    "http://vocab.getty.edu/aat/300006956" -> "gedenktekens",
    "http://vocab.getty.edu/aat/300006460" -> "gevangenissen",
    "http://vocab.getty.edu/aat/300007170" -> "herbergen",

    "http://browser.aat-ned.nl/900032008" -> "hofjes",
    "http://vocab.getty.edu/aat/300007166" -> "hotels",
    "http://vocab.getty.edu/aat/300006306" -> "houtzagerijen",
    "http://vocab.getty.edu/aat/300132384" -> "instellingsgebouwen",
    "http://vocab.getty.edu/aat/300007177" -> "jachthuizen",

    "http://vocab.getty.edu/aat/300007043" -> "kantoorgebouwen",
    "http://vocab.getty.edu/aat/300006891" -> "kastelen",
    "http://vocab.getty.edu/aat/300007466" -> "kerken",
    "http://vocab.getty.edu/aat/300004826" -> "kiosken",
    "http://vocab.getty.edu/aat/300006665" -> "klinieken",

    "http://vocab.getty.edu/aat/300000641" -> "kloosters",
    "http://vocab.getty.edu/aat/300005743" -> "koninklijke paleizen",
    "http://vocab.getty.edu/aat/300006302" -> "leerlooierijen",
    "http://vocab.getty.edu/aat/300005238" -> "markten",
    "http://vocab.getty.edu/aat/300005243" -> "markthallen",

    "http://vocab.getty.edu/aat/300006887" -> "militaire gebouwen",
    "http://vocab.getty.edu/aat/300264626" -> "molens",
    "http://vocab.getty.edu/aat/300007544" -> "moskeeën",
    "http://vocab.getty.edu/aat/300006920" -> "munitiedepots",
    "http://vocab.getty.edu/aat/300005768" -> "musea",

    "http://vocab.getty.edu/aat/300008059" -> "openbare gebouwen",
    "http://vocab.getty.edu/aat/300265138" -> "pakhuizen",
    "http://vocab.getty.edu/aat/300005734" -> "paleizen",
    "http://vocab.getty.edu/aat/300005621" -> "poortgebouwen",
    "http://vocab.getty.edu/aat/300007417" -> "proveniershuizen",

    "http://vocab.getty.edu/aat/300006672" -> "psychiatrische inrichtingen",
    "http://vocab.getty.edu/aat/300007391" -> "religieuze gebouwen",
    "http://vocab.getty.edu/aat/300007797" -> "reparatieloodsen voor locomotieven",
    "http://vocab.getty.edu/aat/300006495" -> "scholen",
    "http://vocab.getty.edu/aat/300004900" -> "schuren",

    "http://vocab.getty.edu/aat/300006354" -> "slachthuizen",
    "http://vocab.getty.edu/aat/300007783" -> "spoorwegstations",
    "http://vocab.getty.edu/aat/300007250" -> "sportgebouwen",
    "http://vocab.getty.edu/aat/300122210" -> "stadhuizen",
    "http://vocab.getty.edu/aat/300007271" -> "stadions",

    "http://vocab.getty.edu/aat/300005080" -> "stadspoorten",
    "http://vocab.getty.edu/aat/300005748" -> "tentoonstellingsgebouwen",
    "http://vocab.getty.edu/aat/300007117" -> "theaters",
    "http://vocab.getty.edu/aat/300007824" -> "tolhuizen",
    "http://vocab.getty.edu/aat/300004847" -> "torens",

    "http://vocab.getty.edu/aat/300007799" -> "tramremises",
    "http://vocab.getty.edu/aat/300007698" -> "tuinhuizen",
    "http://vocab.getty.edu/aat/300006888" -> "verdedigingswerken",
    "http://vocab.getty.edu/aat/300005517" -> "villa's",
    "http://vocab.getty.edu/aat/300007741" -> "vuurtorens",

    "http://browser.aat-ned.nl/300312276" -> "waaggebouwen",
    "http://vocab.getty.edu/aat/300006204" -> "watertorens",
    "http://vocab.getty.edu/aat/300006769" -> "weeshuizen",
    "http://vocab.getty.edu/aat/300005283" -> "winkels",
    "http://vocab.getty.edu/aat/300005433" -> "woonhuizen",

    "http://vocab.getty.edu/aat/300006676" -> "ziekenhuizen",
    "http://vocab.getty.edu/aat/300007376" -> "zwembaden")
}
