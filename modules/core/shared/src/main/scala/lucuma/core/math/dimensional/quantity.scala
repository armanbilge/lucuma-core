// Copyright (c) 2016-2021 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.core.math.dimensional

import coulomb._
import monocle.Focus
import monocle.Lens
import scala.annotation.unused

/**
 * A magnitude of type `N` and a runtime representation of a physical unit.
 */
trait Qty[N] {
  val value: N
  val unit: UnitType

  /**
   * Convert to `coulomb.Quantity`.
   */
  def toCoulomb: Quantity[N, unit.Type] = value.withUnit[unit.Type]
}

// case class DimensionlessQuantity[N](value: N, unit: UnitType) extends Qty[N]
// object DimensionlessQuantity {
//   def apply[N, U](q: Quantity[N, U])(implicit unit: UnitOfMeasure[U]): DimensionlessQuantity[N] =
//     DimensionlessQuantity(q.value, unit)

//   def value[N]: Lens[DimensionlessQuantity[N], N] = Focus[DimensionlessQuantity[N]](_.value)

//   def unit[N]: Lens[DimensionlessQuantity[N], UnitType] = Focus[DimensionlessQuantity[N]](_.unit)
// }

/**
 * A magnitude of type `N` and a runtime representation of a physical unit for a dimension `D`.
 */
case class DimensionQuantity[N, +D] private (value: N, unit: UnitType) extends Qty[N]
object DimensionQuantity {

  /**
   * Create a `DimensionQuantity` from a `coulomb.Quantity`.
   */
  def apply[N, D, U](
    q:             Quantity[N, U]
  )(implicit unit: UnitOfMeasure[U], @unused ev: DimensionUnit[D, U]): DimensionQuantity[N, D] =
    DimensionQuantity(q.value, unit)

  def value[N, D]: Lens[DimensionQuantity[N, D], N] = Focus[DimensionQuantity[N, D]](_.value)

  def unit[N, D]: Lens[DimensionQuantity[N, D], UnitType] = Focus[DimensionQuantity[N, D]](_.unit)
}
