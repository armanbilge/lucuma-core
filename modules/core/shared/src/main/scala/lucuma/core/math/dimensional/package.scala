// Copyright (c) 2016-2021 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.core.math

import coulomb.Quantity
import coulomb.unitops.UnitString
import shapeless.tag
import shapeless.tag.@@

package object dimensional {
  type Of[+T, U] = @@[T, U]

  implicit class UnitStringModuleOps(val unitStringModule: UnitString.type) extends AnyVal {

    /**
     * Convenience method for creating UnitString instances.
     */
    def apply[U](fullName: String, abbreviation: String): UnitString[U] =
      new UnitString[U] {
        override val full: String = fullName
        override val abbv: String = abbreviation
      }

    /**
     * Convenience method for creating UnitString instances.
     */
    def apply[U](abbreviation: String): UnitString[U] =
      apply(abbreviation, abbreviation)
  }

  implicit class QuantityOps[N, U](quantity: Quantity[N, U]) {

    /**
     * Convert a coulomb `Quantity` to a `Measure` with runtime unit representation.
     */
    def toMeasure(implicit unit: UnitOfMeasure[U]): Measure[N] = Measure(quantity.value, unit)

    /**
     * Convert a coulomb `Quantity` to a `Measure` with runtime unit representation and tag `Tag`.
     */
    def toMeasureTagged[Tag](implicit unit: UnitOfMeasure[U]): Measure[N] Of Tag = {
      val tagged: Measure[N] @@ Tag = tag[Tag](Measure(quantity.value, unit))
      tagged
    }
  }
}
