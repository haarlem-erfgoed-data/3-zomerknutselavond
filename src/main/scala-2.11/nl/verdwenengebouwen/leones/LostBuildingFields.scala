package nl.verdwenengebouwen
package leones

import google.maps.LatLng

case class ImageModelFields(
                             img_file: String,
                             permalink: String
                           )

// The structure of the VG JSON model
case class LostBuildingFields01(id: String,
                                lat: String,
                                lon: String,
                                wiki: String,
                                name: String,
                                alt_names: String,
                                place_id: String,
                                place_name: String,
                                startmin: String,
                                startmax: String,
                                endmin: String,
                                endmax: String,
                                description: String,
                                sources: String,
                                `type`: String,
                                architect_uri: String,
                                architect_name: String,
                                img_url: String,
                                uri: String
                               ) {
  lazy val coords = scala.util.Try {
    val (_lat, _lng) = (lat.toDouble, lon.toDouble)
    if (Math.abs(_lat) >= 180D || Math.abs(_lng) >= 180D) throw new IllegalArgumentException("Coordinates out of scope")

    new LatLng(_lat, _lng)
  }.toOption

}
