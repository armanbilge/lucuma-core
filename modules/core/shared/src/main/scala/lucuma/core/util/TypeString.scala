// Copyright (c) 2016-2021 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.core.util

/**
 * Typeclass defining a custom serialized representation of type `T`.
 */
trait TypeString[T] {
  def serialized: String
}
