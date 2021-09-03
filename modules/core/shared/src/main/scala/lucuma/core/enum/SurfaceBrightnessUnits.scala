// Copyright (c) 2016-2021 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma
package core
package enum

import cats.syntax.eq._
import coulomb._
import lucuma.core.util.Enumerated
import lucuma.core.math.units

/**
 * Enumerated type for magnitude system.
 * @tparam A units
 * @group Enumerations (Generated)
 */
sealed abstract class SurfaceBrightnessUnits(
  val tag: String
) extends Product with Serializable {
  type Units
}

object SurfaceBrightnessUnits {

  /** @group Constructors */ case object Vega           extends SurfaceBrightnessUnits("Vega / arcsec²") {
                                                                  type Units = units.Vega %/ units.ArcSecondSquare
                                                                }
  /** @group Constructors */ case object AB             extends SurfaceBrightnessUnits("AB / arcsec²") {
                                                                  type Units = units.AB %/ units.ArcSecondSquare

                                                                }
  /** @group Constructors */ case object Jy             extends SurfaceBrightnessUnits("Jy / arcsec²") {
                                                                  type Units = units.Jansky %/ units.ArcSecondSquare

                                                                }
  /** @group Constructors */ case object Watts          extends SurfaceBrightnessUnits("W/m²/µm/arcsec²") {
                                                                  type Units = units.WattsMag %/ units.ArcSecondSquare

                                                                }
  /** @group Constructors */ case object ErgsWavelength extends SurfaceBrightnessUnits("erg/s/cm²/Å/arcsec²") {
                                                                  type Units = units.ErgsWavelengthMag %/ units.ArcSecondSquare

                                                                }
  /** @group Constructors */ case object ErgsFrequency  extends SurfaceBrightnessUnits("erg/s/cm²/Hz/arcsec²") {
                                                                  type Units = units.ErgsFrequencyMag %/ units.ArcSecondSquare

                                                                }
  /** All members of SurfaceBrightnessUnits, in canonical order. */
  val all: List[SurfaceBrightnessUnits] =
    List(Vega, AB, Jy, Watts, ErgsWavelength, ErgsFrequency)

  /** Select the member of SurfaceBrightnessUnits with the given tag, if any. */
  def fromTag(s: String): Option[SurfaceBrightnessUnits] =
    all.find(_.tag === s)

  /** Select the member of SurfaceBrightnessUnits with the given tag, throwing if absent. */
  def unsafeFromTag(s: String): SurfaceBrightnessUnits =
    fromTag(s).getOrElse(throw new NoSuchElementException(s"SurfaceBrightnessUnits: Invalid tag: '$s'"))

  /** @group Typeclass Instances */
  implicit val MagnitudeSystemEnumerated: Enumerated[SurfaceBrightnessUnits] =
    new Enumerated[SurfaceBrightnessUnits] {
      def all = SurfaceBrightnessUnits.all
      def tag(a: SurfaceBrightnessUnits) = a.tag
      override def unsafeFromTag(s: String): SurfaceBrightnessUnits =
        SurfaceBrightnessUnits.unsafeFromTag(s)
    }

}
