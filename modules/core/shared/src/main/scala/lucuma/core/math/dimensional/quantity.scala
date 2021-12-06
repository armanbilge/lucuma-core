// Copyright (c) 2016-2021 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.core.math.dimensional

import coulomb._
import monocle.Focus
import monocle.Lens

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

/**
 * A magnitude of type `N` and a runtime representation of a physical unit in group `G`.
 */
case class GroupedUnitQuantity[N, +G] private (value: N, unit: UnitType) extends Qty[N]
object GroupedUnitQuantity {

  /**
   * Create a `GroupedUnitQuantity` from a `coulomb.Quantity`.
   *
   * We could limit this to unit/groups with an GroupedUnit[G, U], requiring implicit evidence.
   */
  def apply[N, G, U](
    q:             Quantity[N, U]
  )(implicit unit: UnitOfMeasure[U]): GroupedUnitQuantity[N, G] =
    GroupedUnitQuantity(q.value, unit)

  def value[N, G]: Lens[GroupedUnitQuantity[N, G], N] = Focus[GroupedUnitQuantity[N, G]](_.value)

  def unit[N, G]: Lens[GroupedUnitQuantity[N, G], UnitType] =
    Focus[GroupedUnitQuantity[N, G]](_.unit)
}
