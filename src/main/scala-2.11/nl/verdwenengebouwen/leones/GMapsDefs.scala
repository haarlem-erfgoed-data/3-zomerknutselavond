package nl.verdwenengebouwen
package leones

import google.maps.{LatLng, MapOptions, MarkerImage}
import org.scalajs.dom._

import scala.scalajs.js

object GMapsDefs {
  lazy val blueMarker =
    MarkerImage(size = null, url = "https://maps.gstatic.com/mapfiles/ms2/micons/blue-dot.png")

  def opts = MapOptions(
    center = new LatLng(52.132633, 5.291283),
    mapTypeControl = true,
    scaleControl = true,
    streetViewControl = true,
    zoom = 7)

  def initialize() = {}.asInstanceOf[js.Function]

  def markerContentBuildingsList(buildings: Seq[LostBuildingFields01]): String = {
    if (buildings.size > 1)
      buildings.groupBy(_.`type`).
        map(a => (LostBuildDef.translating(a._1), a._2.size)).toSeq.sortBy { x => (-x._2, x._1) }.
        mkString("""<div id="content">""", "<p>", "</div>")
    else
      s"""<div id="content">${buildings.head.id} ${buildings.head.name}</div>"""
  }

  def stopLoadingIndicatorSpins(img : html.Image) = img.style.display = "none"

}
