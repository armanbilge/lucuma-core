// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.core.model.sequence

import cats.Eq
import cats.syntax.all._
import lucuma.core.enum._
import monocle.Focus
import monocle.Lens
import monocle.Prism
import monocle.macros.GenPrism
import org.typelevel.cats.time._

import java.time.Duration

sealed trait InstrumentConfig
object InstrumentConfig {
  final case class GmosNorth(
    exposure: Duration,
    readout:  GmosCcdMode,
    dtax:     GmosDtax,
    roi:      GmosRoi,
    grating:  Option[GmosGrating.North],
    filter:   Option[GmosNorthFilter],
    fpu:      Option[GmosFpuMask[GmosNorthFpu]]
  ) extends InstrumentConfig
  object GmosNorth {
    implicit val eqInstrumentConfigGmosNorth: Eq[GmosNorth] =
      Eq.by(x => (x.exposure, x.readout, x.dtax, x.roi, x.grating, x.filter, x.fpu))

    /** @group Optics */
    val exposure: Lens[GmosNorth, Duration] =
      Focus[GmosNorth](_.exposure)

    /** @group Optics */
    val readout: Lens[GmosNorth, GmosCcdMode] =
      Focus[GmosNorth](_.readout)

    /** @group Optics */
    val dtax: Lens[GmosNorth, GmosDtax] =
      Focus[GmosNorth](_.dtax)

    /** @group Optics */
    val roi: Lens[GmosNorth, GmosRoi] =
      Focus[GmosNorth](_.roi)

    /** @group Optics */
    val grating: Lens[GmosNorth, Option[GmosGrating.North]] =
      Focus[GmosNorth](_.grating)

    /** @group Optics */
    val filter: Lens[GmosNorth, Option[GmosNorthFilter]] =
      Focus[GmosNorth](_.filter)

    /** @group Optics */
    val fpu: Lens[GmosNorth, Option[GmosFpuMask[GmosNorthFpu]]] =
      Focus[GmosNorth](_.fpu)
  }

  final case class GmosSouth(
    exposure: Duration,
    readout:  GmosCcdMode,
    dtax:     GmosDtax,
    roi:      GmosRoi,
    grating:  Option[GmosGrating.South],
    filter:   Option[GmosSouthFilter],
    fpu:      Option[GmosFpuMask[GmosSouthFpu]]
  ) extends InstrumentConfig
  object GmosSouth {
    implicit val eqInstrumentConfigGmosSouth: Eq[GmosSouth] =
      Eq.by(x => (x.exposure, x.readout, x.dtax, x.roi, x.grating, x.filter, x.fpu))

    /** @group Optics */
    val exposure: Lens[GmosSouth, Duration] =
      Focus[GmosSouth](_.exposure)

    /** @group Optics */
    val readout: Lens[GmosSouth, GmosCcdMode] =
      Focus[GmosSouth](_.readout)

    /** @group Optics */
    val dtax: Lens[GmosSouth, GmosDtax] =
      Focus[GmosSouth](_.dtax)

    /** @group Optics */
    val roi: Lens[GmosSouth, GmosRoi] =
      Focus[GmosSouth](_.roi)

    /** @group Optics */
    val grating: Lens[GmosSouth, Option[GmosGrating.South]] =
      Focus[GmosSouth](_.grating)

    /** @group Optics */
    val filter: Lens[GmosSouth, Option[GmosSouthFilter]] =
      Focus[GmosSouth](_.filter)

    /** @group Optics */
    val fpu: Lens[GmosSouth, Option[GmosFpuMask[GmosSouthFpu]]] =
      Focus[GmosSouth](_.fpu)
  }

  implicit val eqInstrumentConfig: Eq[InstrumentConfig] = Eq.instance {
    case (a @ GmosNorth(_, _, _, _, _, _, _), b @ GmosNorth(_, _, _, _, _, _, _)) => a === b
    case (a @ GmosSouth(_, _, _, _, _, _, _), b @ GmosSouth(_, _, _, _, _, _, _)) => a === b
    case _                                                                        => false
  }

  /** @group Optics */
  val gmosNorth: Prism[InstrumentConfig, GmosNorth] =
    GenPrism[InstrumentConfig, GmosNorth]

  /** @group Optics */
  val gmosSouth: Prism[InstrumentConfig, GmosSouth] =
    GenPrism[InstrumentConfig, GmosSouth]
}
