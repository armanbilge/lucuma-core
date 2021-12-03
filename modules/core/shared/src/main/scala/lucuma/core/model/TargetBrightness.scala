// Copyright (c) 2016-2021 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.core.model

import cats._
// import cats.syntax.all._
import lucuma.core.enum.Band
import lucuma.core.math.BrightnessValue
import monocle.Focus
import monocle.Lens
// import coulomb._
import lucuma.core.math.dimensional._
import lucuma.core.math.units._
// import coulomb.unitops.UnitString
// import scala.annotation.unused
// import coulomb.cats.implicits._
// import coulomb.define.UnitDefinition

/**
 * Describes the brightness of a target on a given band.
 *
 * This class replaces the previous `Magnitude`.
 */
// final case class Brightness[Units](units: BrightnessUnit)(
final case class TargetBrightness(
  quantity: DimensionQuantity[BrightnessValue, Brightness],
  band:     Band,
  error:    Option[BrightnessValue]
) {

  override def toString: String = {
    val errStr = error.map(e => f"${e.toDoubleValue}%.2f")
    f"Brightness(${quantity.value.toDoubleValue}%.2f, ${band.shortName}, $errStr, ${quantity.unit.definition.abbv})"
  }
}

object TargetBrightness {
  val quantity: Lens[TargetBrightness, DimensionQuantity[BrightnessValue, Brightness]] =
    Focus[TargetBrightness](_.quantity)

  val value: Lens[TargetBrightness, BrightnessValue] = quantity.andThen(DimensionQuantity.value)

  val unit: Lens[TargetBrightness, UnitType] = quantity.andThen(DimensionQuantity.unit)

  val band: Lens[TargetBrightness, Band] = Focus[TargetBrightness](_.band)

  val error: Lens[TargetBrightness, Option[BrightnessValue]] = Focus[TargetBrightness](_.error)

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

  implicit def TargetBrightnessOrder: Order[TargetBrightness] =
    // Not same order as before, this doesn't take into account unit definition order
    Order.by(m => (m.quantity.unit.definition.name, m.band.tag, m.quantity.value, m.error))

  /** group Typeclass Instances */
  implicit def TargetBrightnessShow[Units]: Show[TargetBrightness] =
    Show.fromToString

}
