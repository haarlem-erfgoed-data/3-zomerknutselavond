package nl.verdwenengebouwen
package leones

import google.maps.Marker
import nl.verdwenengebouwen.leones.GMapsDefs._
import org.scalajs.dom.document
import org.scalajs.dom.ext.Ajax
import org.scalajs.jquery.jQuery
import upickle.{default => upickle}

import scala.collection.immutable
import scala.concurrent.Future
import scala.scalajs.js
import scala.util.{Failure, Success}
import scalatags.JsDom.all._ //{li, stringFrag, ul}

@js.annotation.JSExport
object Main {
  @js.annotation.JSExport
  def main() = {
    import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

    val gmap = new google.maps.Map(document.getElementById("map-canvas"), opts)

    def getBuildings(url: String): Future[Seq[(LostBuildingFields01)]] =
      Ajax.get(url).map(xhr => upickle.read[Seq[LostBuildingFields01]](xhr.responseText))

    // event.addDomListener(instance = document.body, eventName = "load", handler = initialize())

    // Get a server response and process when collected
    getBuildings(LostBuildDef.endPoint00).onComplete {
      case Success(rawBuildingRecords) =>

        // .0 Do some data maintenance
        def appliedRecordCuriosities: Seq[LostBuildingFields01] = rawBuildingRecords.map {
          case bld if bld.place_name == "'s-Gravenhage" => bld.copy(place_name = "Den Haag")
          case bld => bld
        }

        // .1 Process the response to a map indexed by coordinates, discard the records without a (valid) coordinate
        val geoBuildingsMap: immutable.Map[(String, String), Seq[(LostBuildingFields01)]] =
          appliedRecordCuriosities.filter(_.coords.isDefined).groupBy(crit => (crit.lat, crit.lon))

        // .2 Make a sorted list of coordinates by number of buildings in descending order and increasing coordinates
        def geoBuildings: Seq[(String, String)] = geoBuildingsMap.toSeq.sortBy { case (_, blds) =>
          (-blds.size, blds.head.coords.get.lat, blds.head.coords.get.lng)
        }.map(_._1) // List only the coordinates of sorted list by number of buildings

        // .3 Place the markers
        geoBuildings.foreach(coordGroup => {
          def build = geoBuildingsMap(coordGroup)
          val dimension = build.size

          def marker = new Marker(google.maps.MarkerOptions(
            // animation= Animation.BOUNCE,
            icon = if (dimension > 1) blueMarker else null,
            map = gmap,
            position = build.head.coords.get,
            title = s"Hier $dimension objecten"
          ))

          // .3.1 Populate each marker with handled message
          google.maps.event.addListener(marker, "click", () => {
            def infowindow =
              new google.maps.InfoWindow(google.maps.InfoWindowOptions(content = markerContentBuildingsList(build)))

            infowindow.open(gmap, marker)
            println(coordGroup, build.head)
          })
        })

        def top25Cities = appliedRecordCuriosities.groupBy(_.place_name).toSeq.
          sortBy(bld => (-bld._2.size, bld._1)).take(25).sortBy {
          case (plaatsnaam, _) if plaatsnaam.startsWith("'s-") => plaatsnaam.drop(3)
          case (plaatsnaam, _) => plaatsnaam
        }

        jQuery("#top25-listing").append(ul(top25Cities.map { b => li(s"${b._1} (${b._2.size})") }).render)

        stopLoadingIndicatorSpins()

      case Failure(retVal) => println(s"FAILURE: ${retVal.getMessage}")
        stopLoadingIndicatorSpins()
    }
  }

}
