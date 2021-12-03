// Copyright (c) 2016-2021 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.core.model

import cats._
// import cats.syntax.all._
import lucuma.core.enum.Band
import lucuma.core.math.BrightnessValue
// import monocle.Focus
// import monocle.Lens
import coulomb._
import lucuma.core.math.units._
// import coulomb.unitops.UnitString
// import scala.annotation.unused
// import coulomb.cats.implicits._
import coulomb.define.UnitDefinition

/**
 * Describes the brightness of a target on a given band.
 *
 * This class replaces the previous `Magnitude`.
 */
// final case class Brightness[Units](units: BrightnessUnit)(
abstract case class Brightness private () {
  type Units

  def units: UnitDefinition
  def value: Quantity[BrightnessValue, Units]
  def band: Band
  def error: Option[BrightnessValue]

  override def toString: String = {
    val errStr = error.map(e => f"${e.toDoubleValue}%.2f")
    f"Brightness(${value.value.toDoubleValue}%.2f, ${band.shortName}, $errStr, ${units.abbv})"
  }
}

object Brightness {
  def apply[_Units](
    _value:          Quantity[BrightnessValue, _Units],
    _band:           Band,
    _error:          Option[BrightnessValue]
  )(implicit _units: BrightnessUnit[_Units]): Brightness =
    new Brightness {
      type Units = _Units
      val units                                   = _units
      val value: Quantity[BrightnessValue, Units] = _value
      val band: Band                              = _band
      val error: Option[BrightnessValue]          = _error
    }

  // def value[Units, Type](implicit
  //   @unused ev: BrightnessUnit[Units]
  // ): Lens[Brightness[Units], Quantity[BrightnessValue, Units]] =
  //   Focus[Brightness[Units]](_.value)

  // val band: Lens[Brightness, Band] = Focus[Brightness](_.band)

  // val error: Lens[Brightness, Option[BrightnessValue]] = Focus[Brightness](_.error)

  // val units: Lens[Brightness, BrightnessUnit[BrightnessUnit.Integrated]] =
  //   Focus[Brightness](_.units)

  /** Secondary constructor. */
  // def apply(value: BrightnessValue, band: Band, error: BrightnessValue) =
  //   new Brightness(value, band, Some(error))

  /** Secondary constructor defaulting to no error. */
  // def apply[Units](value: Quantity[BrightnessValue, Units], band: Band)(implicit
  //   @unused ev:           BrightnessUnit
  // ) =
  //   new Brightness(value, band, none)

  /** Secondary constructor defaulting to no given error. */
  // def apply(value: BrightnessValue, band: Band) =
  //   new Brightness(value, band, None, band.brightnessUnit)

  // by units name, band name, value and error (in that order)
  // implicit val BrightnessOrdering: Order[Brightness] =
  //   Order.by(m => (m.units.tag, m.band.tag, m.value, m.error))

  implicit def BrightnessOrder: Order[Brightness] =
    // Not same order as before, this doesn't take into account unit definition order
    Order.by(m => (m.units.name, m.band.tag, m.value.value, m.error))

  /** group Typeclass Instances */
  implicit def BrightnessShow[Units]: Show[Brightness] =
    Show.fromToString

}
