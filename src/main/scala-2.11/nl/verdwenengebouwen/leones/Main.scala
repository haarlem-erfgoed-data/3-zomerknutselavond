package nl.verdwenengebouwen
package leones

import google.maps.{Map, Marker}
import nl.verdwenengebouwen.leones.GMapsDefs._
import org.scalajs.dom.{document, html}
import org.scalajs.dom.ext.Ajax
import org.scalajs.jquery.jQuery
import upickle.{default => upickle}

import scala.collection.immutable
import scala.concurrent.Future
import scala.scalajs.js
import scala.util.{Failure, Success}
import scalatags.JsDom.all.{pre, stringFrag}

@js.annotation.JSExport
object Main {
  @js.annotation.JSExport
  def main() = {
    import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

    lazy val target = jQuery("body")
    val gmap = new Map(document.getElementById("map-canvas"), opts)

    def getBuildings(url: String): Future[Seq[(LostBuildingFields01)]] =
      Ajax.get(url).map(xhr => upickle.read[Seq[LostBuildingFields01]](xhr.responseText))

    // event.addDomListener(instance = document.body, eventName = "load", handler = initialize())

    // Get a server response and process when collected
    getBuildings(LostBuildDef.endPoint00).onComplete {
      case Success(buildings) =>
        // .1 Process the response to a map indexed by coordinates, discard the records without a (valid) coordinate
        val geoBuildingsMap: immutable.Map[(String, String), Seq[(LostBuildingFields01)]] =
          buildings.filter(_.coords.isDefined).groupBy(crit => (crit.lat, crit.lon))

        // .2 Make a sorted list of coordinates by number of buildings in descending order and increasing coordinates
        def geoBuildings: Seq[(String, String)] = geoBuildingsMap.toSeq.sortBy { case (_, blds) =>
          (-blds.size, blds.head.coords.get.lat, blds.head.coords.get.lng)
        }.map(_._1) // List only the coordinates of sorted list by number of buildings

        // .3 Place the markers
        geoBuildings.foreach(coordGroup => {
          val build = geoBuildingsMap(coordGroup)

          val marker = new Marker(google.maps.MarkerOptions(
            // animation= Animation.BOUNCE,
            icon = if (build.size > 1) blueMarker else null,
            map = gmap,
            position = build.head.coords.get,
            title = s"Hier ${build.size} objecten"
          ))

          // .3.1 Populate each marker with handled message
          google.maps.event.addListener(marker, "click", () => {
            def infowindow =
              new google.maps.InfoWindow(google.maps.InfoWindowOptions(content = markerContentBuildingsList(build)))

            infowindow.open(gmap, marker)
            println(coordGroup, build.head)
          })
        })

        target.append(
          pre(geoBuildings.map(b => (b, geoBuildingsMap(b).size)).mkString("Frekwentie\n", "\n", "\nEind")).render)

        stopLoadingIndicatorSpins(document.getElementById("spinnerImg").asInstanceOf[html.Image])

      case Failure(retVal) => println(s"FAILURE: ${retVal.getMessage}")
        stopLoadingIndicatorSpins(document.getElementById("spinnerImg").asInstanceOf[html.Image])
    }
  }

  def debugAggregation() = {

  }

}
