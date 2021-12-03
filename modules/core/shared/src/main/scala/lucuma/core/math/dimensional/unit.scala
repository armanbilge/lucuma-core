// Copyright (c) 2016-2021 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.core.math.dimensional

import coulomb.define._
import scala.annotation.unused

// All of this is a bridge between coulomb an runtime quantities (as defined in `Qty`).

/**
 * Runtime representation of a physical unit. Wraps:
 *   - A type (usually phantom), meant to be `coulomb`'s type for the unit; and
 *   - A `UnitDefinition`, which is a common trait of `coulomb`'s `BaseUnit` and `DerivedUnit`.
 *
 * In `coulomb` units are types. However in some cases we want to have runtime representations of
 * units.
 */
trait UnitType {
  type Type
  def definition: UnitDefinition
}

/**
 * Type-parametrized runtime representation of physical unit `U`.
 *
 * Can be automatically derived if there's an implicit `BaseUnit[U]` or `DerivedUnit[U, _]` in
 * scope.
 */
trait UnitOfMeasure[U] extends UnitType {
  type Type = U
}

object UnitOfMeasure {
  def apply[U: UnitOfMeasure]: UnitOfMeasure[U] = implicitly[UnitOfMeasure[U]]

  implicit def unitOfMeasureFromBaseUnit[U](implicit ev: BaseUnit[U]): UnitOfMeasure[U] =
    new UnitOfMeasure[U] {
      val definition = ev
    }
  implicit def unitOfMeasureFromDerivedUnit[U, D](implicit
    ev: DerivedUnit[U, D]
  ): UnitOfMeasure[U] =
    new UnitOfMeasure[U] {
      val definition = ev
    }
}

/**
 * Evidence that `U` is a unit of measure for dimension `D`.
 */
trait DimensionUnit[D, U]

/**
 * A unit of measure and the physical dimension of that unit. A dimension being for example
 * `Length`, `Weight`, `Time`, etc.
 */
trait DimensionUnitType[D] extends UnitType {
  type Dimension = D

  def withValue[N](value: N): DimensionQuantity[N, D] =
    DimensionQuantity(value, this)
}

/**
 * Type-parametrized runtime representation of physical unit `U` with dimension `D`.
 *
 * Automatically derived if there's an implicit `BaseUnit[U]` or `DerivedUnit[U, _]` in scope, as
 * well as a `DimensionUnit[D, U]`.
 */
trait DimensionUnitOfMeasure[D, U] extends UnitOfMeasure[U] with DimensionUnitType[D]

object DimensionUnitOfMeasure {
  def apply[D, U](implicit
    unit:       UnitOfMeasure[U],
    @unused ev: DimensionUnit[D, U]
  ): DimensionUnitOfMeasure[D, U] =
    new DimensionUnitOfMeasure[D, U] {
      val definition = unit.definition
    }
}
