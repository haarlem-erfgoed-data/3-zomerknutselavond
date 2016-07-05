package nl.verdwenengebouwen
package leones

import google.maps.LatLng
import org.scalajs.dom.document
import org.scalajs.dom.ext.Ajax
import org.scalajs.jquery.jQuery
import upickle.{default => upickle}

import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scalatags.JsDom.all.{pre, stringFrag}

@JSExport
object Main {
//  @JSExport
//  def main() = {
    import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

    lazy val blueMarker = google.maps.MarkerImage(
      size = null, url = "https://maps.gstatic.com/mapfiles/ms2/micons/blue-dot.png")

    lazy val target = jQuery("body")
    def url0 = "http://www.verdwenengebouwen.nl/search/json/?q="

    def opts = google.maps.MapOptions(
      center = new LatLng(52.132633, 5.291283),
      mapTypeControl = true,
      scaleControl = true,
      streetViewControl = true,
      zoom = 7)

    val gmap = new google.maps.Map(document.getElementById("map-canvas"), opts)

    def getBuildings(url: String): Future[Seq[(LostBuildingFields01)]] =
      Ajax.get(url).map(xhr => upickle.read[Seq[LostBuildingFields01]](xhr.responseText))

    def initialize() = {}.asInstanceOf[js.Function]

    google.maps.event.addDomListener(instance = document.body, eventName = "load", handler = initialize())

    // Get a server response and process when collected
    getBuildings(url0).onSuccess { case buildings =>
      // .1 Process the response to a map indexed by coordinates, discard the records without a (valid) coordinate
      val geoBuildingsMap: Map[(String, String), Seq[(LostBuildingFields01)]] =
        buildings.filter(_.coords.isDefined).groupBy(crit => (crit.lat, crit.lon))

      // .2 Make a sorted list of coordinates by number of buildings in descending order and increasing coordinates
      def geoBuildings: Seq[(String, String)] = geoBuildingsMap.toSeq.sortBy { case (_, blds) =>
        (-blds.size, blds.head.coords.get.lat, blds.head.coords.get.lng)
      }.map(_._1) // List only the coordinates of sorted list by number of buildings


      def markerContentBuildingsList(coordGroup: (String, String)): String = {
        val buildings = geoBuildingsMap(coordGroup)
        if (buildings.size > 1)
          buildings.groupBy(_.`type`).
            map(a => (LostBuildDef.translating(a._1), a._2.size)).toSeq.sortBy { x => (-x._2, x._1) }.
            mkString("""<div id="content">""", "<p>", "</div>")
        else s"""<div id="content">${buildings.head.id} ${buildings.head.name}</div>"""
      }

      // .3 Place the markers
      geoBuildings.foreach(coordGroup => {
        val build = geoBuildingsMap(coordGroup)

        val marker = new google.maps.Marker(google.maps.MarkerOptions(
          icon = if (build.size > 1) blueMarker else null,
          map = gmap,
          position = build.head.coords.get,
          title = s"Hier ${build.size} objecten"))

        // .3.1 Populate each marker with handled message
        google.maps.event.addListener(marker, "click", () => {
          def infowindow =
            new google.maps.InfoWindow(google.maps.InfoWindowOptions(content = markerContentBuildingsList(coordGroup)))

          infowindow.open(gmap, marker)
          println(coordGroup, build.head)
        })
      })

      target.append(
        pre(geoBuildings.map(b => (b, geoBuildingsMap(b).size)).mkString("Frekwentie\n", "\n", "\nEind")).render
      )
    }
//  }
}
