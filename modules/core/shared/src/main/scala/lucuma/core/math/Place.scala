// Copyright (c) 2016-2021 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.core.math
import cats.Eq
import cats.Show
import java.time.ZoneId
import coulomb.Quantity
import coulomb.si.Meter
import eu.timepit.refined.types.numeric.NonNegInt
import monocle.Lens
import monocle.Focus
import io.chrisdavenport.cats.time._
import coulomb.cats.implicits._
import eu.timepit.refined.cats._

/** A point on Earth, given latitude, longitude and altitude in m above sea level. */
final case class Place(
  val latitude:  Lat,
  val longitude: Lon,
  val altitude:  Quantity[NonNegInt, Meter],
  val timezone:  ZoneId
) {
  // This is needed for the JVM based test. The JVM doesn't like interacting with the refined type
  final val altitudeDouble: Double = altitude.value.value.toDouble
}

object Place {

  /** @group Typeclass Instances */
  implicit val PlaceEqual: Eq[Place] =
    Eq.by(x => (x.latitude, x.longitude, x.altitude, x.timezone))

  /** @group Typeclass Instances */
  implicit val PlaceShow: Show[Place] = Show.fromToString

  /** @group Optics */
  val latitude: Lens[Place, Lat] =
    Focus[Place](_.latitude)

  /** @group Optics */
  val longitude: Lens[Place, Lon] =
    Focus[Place](_.longitude)

  /** @group Optics */
  val altitude: Lens[Place, Quantity[NonNegInt, Meter]] =
    Focus[Place](_.altitude)

  /** @group Optics */
  val timezone: Lens[Place, ZoneId] =
    Focus[Place](_.timezone)
}
