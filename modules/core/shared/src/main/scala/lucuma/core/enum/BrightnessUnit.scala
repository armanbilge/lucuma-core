// Copyright (c) 2016-2021 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

// package lucuma
// package core
// package enum

// import cats.syntax.eq._
// import coulomb.Unitless
// import lucuma.core.math.units
// import lucuma.core.util.Display
// import lucuma.core.util.Enumerated

// sealed abstract class BrightnessUnit[T](val tag: String, val show: String)
//     extends Product
//     with Serializable {
//   type Units
// }

// object BrightnessUnit {
//   type Type[T] = Object

//   // These are phantom types
//   type Integrated
//   type Surface

//   /** @group Constructors */
//   case object VegaMagnitudes extends BrightnessUnit[Integrated]("VegaMag", "Vega mags") {
//     type Units = Unitless
//   }

//   /** @group Constructors */
//   case object ABMagnitudes extends BrightnessUnit[Integrated]("ABMag", "AB mags") {
//     type Units = Unitless
//   }

//   /** @group Constructors */
//   case object Janskys extends BrightnessUnit[Integrated]("Jy", "Jy") {
//     type Units = units.Jansky
//   }

//   /** @group Constructors */
//   case object Watts extends BrightnessUnit[Integrated]("Watts", "W/m²/µm") {
//     type Units = units.WattsMag
//   }

//   /** @group Constructors */
//   case object ErgsWavelength extends BrightnessUnit[Integrated]("ErgsWavelength", "erg/s/cm²/Å") {
//     type Units = units.ErgsWavelengthMag
//   }

//   /** @group Constructors */
//   case object ErgsFrequency extends BrightnessUnit[Integrated]("ErgsFrequency", "erg/s/cm²/Hz") {
//     type Units = units.ErgsFrequencyMag
//   }

//   // TODO Adjust units

//   /** @group Constructors */
//   case object VegaMagnitudesArcsec2
//       extends BrightnessUnit[Surface]("VegaMagArcsec2", "Vega mags/arcsec²") {
//     type Units = Unitless
//   }

//   /** @group Constructors */
//   case object ABMagnitudesArcsec2
//       extends BrightnessUnit[Surface]("ABMagArcsec2", "AB mags/arcsec²") {
//     type Units = Unitless
//   }

//   /** @group Constructors */
//   case object JanskysArcsec2 extends BrightnessUnit[Surface]("JyArcsec2", "Jy/arcsec²") {
//     type Units = units.Jansky
//   }

//   /** @group Constructors */
//   case object WattsArcsec2 extends BrightnessUnit[Surface]("WattsArcsec2", "W/m²/µm/arcsec²") {
//     type Units = units.WattsMag
//   }

//   /** @group Constructors */
//   case object ErgsWavelengthArcsec2
//       extends BrightnessUnit[Surface]("ErgsWavelengthArcsec2", "erg/s/cm²/Å/arcsec²") {
//     type Units = units.ErgsWavelengthMag
//   }

//   /** @group Constructors */
//   case object ErgsFrequencyArcsec2
//       extends BrightnessUnit[Surface]("ErgsFrequencyArcsec2", "erg/s/cm²/Hz/arcsec²") {
//     type Units = units.ErgsFrequencyMag
//   }

//   object Integrated extends Type[Integrated] {

//     /** All members of BrightnessUnit[Integrated], in canonical order. */
//     val all: List[BrightnessUnit[Integrated]] =
//       List(VegaMagnitudes, ABMagnitudes, Janskys, Watts, ErgsWavelength, ErgsFrequency)

//     /** Select the member of BrightnessUnit[Integrated] with the given tag, if any. */
//     def fromTag(s: String): Option[BrightnessUnit[Integrated]] =
//       all.find(_.tag === s)

//     /** Select the member of BrightnessUnit[Integrated] with the given tag, throwing if absent. */
//     def unsafeFromTag(s: String): BrightnessUnit[Integrated] =
//       fromTag(s).getOrElse(
//         throw new NoSuchElementException(s"BrightnessUnit[Integrated]: Invalid tag: '$s'")
//       )
//   }

//   object Surface extends Type[Surface] {

//     /** All members of BrightnessUnit[Surface], in canonical order. */
//     val all: List[BrightnessUnit[Surface]] =
//       List(
//         VegaMagnitudesArcsec2,
//         ABMagnitudesArcsec2,
//         JanskysArcsec2,
//         WattsArcsec2,
//         ErgsWavelengthArcsec2,
//         ErgsFrequencyArcsec2
//       )

//     /** Select the member of BrightnessUnit[Surface] with the given tag, if any. */
//     def fromTag(s: String): Option[BrightnessUnit[Surface]] =
//       all.find(_.tag === s)

//     /** Select the member of BrightnessUnit[Surface] with the given tag, throwing if absent. */
//     def unsafeFromTag(s: String): BrightnessUnit[Surface] =
//       fromTag(s).getOrElse(
//         throw new NoSuchElementException(s"BrightnessUnit[Surface]: Invalid tag: '$s'")
//       )
//   }

//   /** @group Typeclass Instances */
//   implicit val integratedBrightnessUnitEnumerated: Enumerated[BrightnessUnit[Integrated]] =
//     new Enumerated[BrightnessUnit[Integrated]] {
//       def all                                                           = Integrated.all
//       def tag(a: BrightnessUnit[Integrated])                            = a.tag
//       override def unsafeFromTag(s: String): BrightnessUnit[Integrated] =
//         Integrated.unsafeFromTag(s)
//     }

//   /** @group Typeclass Instances */
//   implicit val surfaceBrightnessUnitEnumerated: Enumerated[BrightnessUnit[Surface]] =
//     new Enumerated[BrightnessUnit[Surface]] {
//       def all                                                        = Surface.all
//       def tag(a: BrightnessUnit[Surface])                            = a.tag
//       override def unsafeFromTag(s: String): BrightnessUnit[Surface] =
//         Surface.unsafeFromTag(s)
//     }

//   // This Display can be useful to nicely format the units
//   def unitDisplay[T]: Display[BrightnessUnit[T]] = Display.byShortName(_.show)

// }
