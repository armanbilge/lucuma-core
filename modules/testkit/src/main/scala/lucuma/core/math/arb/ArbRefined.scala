// Copyright (c) 2016-2021 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.core.math.arb

import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary._
import eu.timepit.refined.types.numeric.PosBigDecimal
import org.scalacheck.Cogen

trait ArbRefined {

  // Refined does not derive this arbitrary. Generally deriving `Arbitrary` instances for `Pos`
  // numbers requires instances of `Adjacent` and `Max`, which don't exist for `BigDecimal`.
  implicit val arbPosBigDecimal: Arbitrary[PosBigDecimal] =
    Arbitrary(arbitrary[BigDecimal].suchThat(_ > 0).map(PosBigDecimal.unsafeFrom))

  implicit val cogenPosBigDecimal: Cogen[PosBigDecimal] =
    Cogen[BigDecimal].contramap(_.value)
}

object ArbRefined extends ArbRefined
