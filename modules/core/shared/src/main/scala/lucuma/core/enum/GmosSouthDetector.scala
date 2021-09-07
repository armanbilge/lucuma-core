// Copyright (c) 2016-2021 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma
package core
package enum

import cats.syntax.eq._
import coulomb._
import coulomb.refined._
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric.Positive
import eu.timepit.refined.types.numeric.PosInt
import lucuma.core.math.Angle
import lucuma.core.math.units.Pixels
import lucuma.core.util.Enumerated

/**
 * Enumerated type for GMOS detector.
 * @group Enumerations (Generated)
 */
sealed abstract class GmosSouthDetector(
  val tag: String,
  val shortName: String,
  val longName: String,
  val pixelSize: Angle,
  val shuffleOffset: Quantity[PosInt, Pixels],
  val xSize: Quantity[PosInt, Pixels],
  val ySize: Quantity[PosInt, Pixels],
  val maxRois: PosInt
) extends Product with Serializable {
}

object GmosSouthDetector {

  /** @group Constructors */ case object E2V extends GmosSouthDetector(
                               "E2V",
                               "E2V",
                               "E2V",
                               Angle.fromMicroarcseconds(73000),
                               1536.withRefinedUnit[Positive, Pixels],
                               6144.withRefinedUnit[Positive, Pixels],
                               4608.withRefinedUnit[Positive, Pixels],
                               4
                             )
  /** @group Constructors */ case object Hammamtsu extends GmosSouthDetector(
                               "HAMAMATSU",
                               "Hammamatsu",
                               "Hammamatsu",
                               Angle.fromMicroarcseconds(80000),
                               1392.withRefinedUnit[Positive, Pixels],
                               6255.withRefinedUnit[Positive, Pixels],
                               4176.withRefinedUnit[Positive, Pixels],
                               5
                             )

  /** All members of GmosSouthDetector, in canonical order. */
  val all: List[GmosSouthDetector] =
    List(E2V, Hammamtsu)

  /** Select the member of GmosSouthDetector with the given tag, if any. */
  def fromTag(s: String): Option[GmosSouthDetector] =
    all.find(_.tag === s)

  /** Select the member of GmosSouthDetector with the given tag, throwing if absent. */
  def unsafeFromTag(s: String): GmosSouthDetector =
    fromTag(s).getOrElse(throw new NoSuchElementException(s"GmosSouthDetector: Invalid tag: '$s'"))

  /** @group Typeclass Instances */
  implicit val GmosDetectorEnumerated: Enumerated[GmosSouthDetector] =
    new Enumerated[GmosSouthDetector] {
      def all = GmosSouthDetector.all
      def tag(a: GmosSouthDetector) = a.tag
      override def unsafeFromTag(s: String): GmosSouthDetector =
        GmosSouthDetector.unsafeFromTag(s)
    }

}
