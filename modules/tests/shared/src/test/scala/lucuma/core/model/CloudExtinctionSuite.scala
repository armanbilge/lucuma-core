// Copyright (c) 2016-2021 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.core.model

import cats.kernel.laws.discipline._
import lucuma.core.model.arb._
import munit._

final class CloudExtinctionSuite extends DisciplineSuite {
  import ArbCloudExtinction._

  // Laws
  checkAll("Order[CloudExtinction]", OrderTests[CloudExtinction].order)
}
