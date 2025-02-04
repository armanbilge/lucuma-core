// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.core.optics.laws

import cats.Eq
import org.scalacheck.Prop

package object discipline {

  implicit def gemLawsIsEqToProp[A: Eq](isEq: IsEq[A]): Prop =
    cats.kernel.laws.discipline.catsLawsIsEqToProp[A](isEq)

}
