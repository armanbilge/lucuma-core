// Copyright (c) 2016-2021 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.core.model
package arb

import cats.implicits._
import lucuma.core.enum.Band
// import lucuma.core.enum.BrightnessUnit
import lucuma.core.math.BrightnessValue
import lucuma.core.math.arb.ArbBrightnessValue._
import lucuma.core.util.arb.ArbEnumerated
import org.scalacheck.Arbitrary._
import org.scalacheck.Cogen._
import org.scalacheck._
import lucuma.core.math.units._

import scala.collection.immutable.SortedMap
import coulomb.define.UnitDefinition
import coulomb._

trait ArbBrightness {

  import ArbEnumerated._

  implicit val arbBrightness: Arbitrary[Brightness] =
    Arbitrary {
      for {
        // s <- arbitrary[BrightnessUnit.Integrated]
        s <- Gen.oneOf(BrightnessUnit.Integrated.all)
        v <- arbitrary[BrightnessValue]
        b <- arbitrary[Band]
        e <- arbitrary[Option[BrightnessValue]]
      } yield Brightness(v.withUnit[s.Type], b, e) //(s.definition)
    }

  implicit val cogUnitDefinition: Cogen[UnitDefinition] =
    Cogen[(String, String)].contramap(u => (u.name, u.abbv))

  implicit val cogBrightness: Cogen[Brightness] =
    Cogen[
      (UnitDefinition, BrightnessValue, Band, Option[BrightnessValue])
    ].contramap(u => (u.units, u.value, u.band, u.error))

  implicit val arbBrightnessesMap: Arbitrary[SortedMap[Band, Brightness]] =
    Arbitrary(arbitrary[Vector[Brightness]].map(_.fproductLeft(_.band)).map(x => SortedMap(x: _*)))

  implicit val cogBrightnessesMap: Cogen[SortedMap[Band, Brightness]] =
    Cogen[Vector[(Band, Brightness)]].contramap(_.toVector)
}

object ArbBrightness extends ArbBrightness
