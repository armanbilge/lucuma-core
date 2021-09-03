// Copyright (c) 2016-2021 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma
package core
package enum

import cats.syntax.eq._
import lucuma.core.util.Enumerated
import lucuma.core.math.units

/**
 * Enumerated type for magnitude system.
 * @tparam A units
 * @group Enumerations (Generated)
 */
sealed abstract class BrightnessUnits(
  val tag: String
) extends Product with Serializable {
  type Units
}

object BrightnessUnits {

  /** @group Constructors */ case object Vega           extends BrightnessUnits("Vega") {
                                                                  type Units = units.Vega
                                                                }
  /** @group Constructors */ case object AB             extends BrightnessUnits("AB") {
                                                                  type Units = units.AB
                                                                }
  /** @group Constructors */ case object Jy             extends BrightnessUnits("Jy") {
                                                                  type Units = units.Jansky
                                                                }
  /** @group Constructors */ case object Watts          extends BrightnessUnits("W/m²/µm") {
                                                                  type Units = units.WattsMag
                                                                }
  /** @group Constructors */ case object ErgsWavelength extends BrightnessUnits("erg/s/cm²/Å") {
                                                                  type Units = units.ErgsWavelengthMag
                                                                }
  /** @group Constructors */ case object ErgsFrequency  extends BrightnessUnits("erg/s/cm²/Hz") {
                                                                  type Units = units.ErgsFrequencyMag
                                                                }
  /** All members of MagnitudeSystem, in canonical order. */
  val all: List[BrightnessUnits] =
    List(Vega, AB, Jy, Watts, ErgsWavelength, ErgsFrequency)

  /** Select the member of MagnitudeSystem with the given tag, if any. */
  def fromTag(s: String): Option[BrightnessUnits] =
    all.find(_.tag === s)

  /** Select the member of MagnitudeSystem with the given tag, throwing if absent. */
  def unsafeFromTag(s: String): BrightnessUnits =
    fromTag(s).getOrElse(throw new NoSuchElementException(s"MagnitudeSystem: Invalid tag: '$s'"))

  /** @group Typeclass Instances */
  implicit val MagnitudeSystemEnumerated: Enumerated[BrightnessUnits] =
    new Enumerated[BrightnessUnits] {
      def all = BrightnessUnits.all
      def tag(a: BrightnessUnits) = a.tag
      override def unsafeFromTag(s: String): BrightnessUnits =
        BrightnessUnits.unsafeFromTag(s)
    }

}
