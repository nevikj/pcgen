/*
 * FaceToken.java
 * Copyright 2003 (C) Devon Jones <soulcatcher@evilsoft.org>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.     See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * Created on December 15, 2003, 12:21 PM
 *
 * Current Ver: $Revision$
 * Last Editor: $Author$
 * Last Edited: $Date$
 *
 */
package plugin.exporttokens.deprecated;

import java.text.DecimalFormat;

import pcgen.base.math.OrderedPair;
import pcgen.cdom.enumeration.CharID;
import pcgen.cdom.enumeration.ObjectKey;
import pcgen.cdom.facet.FacetLibrary;
import pcgen.cdom.facet.analysis.ResultFacet;
import pcgen.cdom.inst.CodeControl;
import pcgen.core.Globals;
import pcgen.core.SettingsHandler;
import pcgen.core.display.CharacterDisplay;
import pcgen.core.utils.CoreUtility;
import pcgen.io.ExportHandler;
import pcgen.io.exporttoken.AbstractExportToken;

/**
 * Deal with Tokens:
 * 
 * FACE
 * FACE.SHORT
 * FACE.1
 * FACE.2
 */
public class FaceToken extends AbstractExportToken
{
	/**
	 * @see pcgen.io.exporttoken.Token#getTokenName()
	 */
	@Override
	public String getTokenName()
	{
		return "FACE";
	}

	/**
	 * @see pcgen.io.exporttoken.Token#getToken(java.lang.String, pcgen.core.PlayerCharacter, pcgen.io.ExportHandler)
	 */
	@Override
	public String getToken(String tokenSource, CharacterDisplay display,
		ExportHandler eh)
	{
		String retString = "";

		if ("FACE".equals(tokenSource))
		{
			retString = getFaceToken(display);
		}
		else if ("FACE.SHORT".equals(tokenSource))
		{
			retString = getShortToken(display);
		}
		else if ("FACE.SQUARES".equals(tokenSource))
		{
			retString = getSquaresToken(display);
		}
		else if ("FACE.1".equals(tokenSource))
		{
			retString = get1Token(display);
		}
		else if ("FACE.2".equals(tokenSource))
		{
			retString = get2Token(display);
		}

		return retString;
	}

	/**
	 * Get FACE Token
	 * @param pc
	 * @return FACE Token
	 */
	public static String getFaceToken(CharacterDisplay display)
	{
		OrderedPair face = getFace(display.getCharID());
		String retString = "";
		if (CoreUtility.doublesEqual(face.getPreciseY().doubleValue(), 0.0))
		{
			retString =
					Globals.getGameModeUnitSet().displayDistanceInUnitSet(
						face.getPreciseX().doubleValue())
						+ Globals.getGameModeUnitSet().getDistanceUnit();
		}
		else
		{
			retString =
					Globals.getGameModeUnitSet().displayDistanceInUnitSet(
						face.getPreciseX().doubleValue())
						+ Globals.getGameModeUnitSet().getDistanceUnit()
						+ " by "
						+ Globals.getGameModeUnitSet()
							.displayDistanceInUnitSet(
								face.getPreciseY().doubleValue())
						+ Globals.getGameModeUnitSet().getDistanceUnit();
		}
		return retString;
	}

	/**
	 * Get SHORT sub token
	 * 
	 * @param pc
	 * @return SHORT sub toke
	 */
	public static String getShortToken(CharacterDisplay display)
	{
		OrderedPair face = getFace(display.getCharID());
		String retString = "";
		if (CoreUtility.doublesEqual(face.getPreciseY().doubleValue(), 0.0))
		{
			retString =
					Globals.getGameModeUnitSet().displayDistanceInUnitSet(
						face.getPreciseX().doubleValue())
						+ Globals.getGameModeUnitSet().getDistanceUnit();
		}
		else
		{
			retString =
					Globals.getGameModeUnitSet().displayDistanceInUnitSet(
						face.getPreciseX().doubleValue())
						+ Globals.getGameModeUnitSet().getDistanceUnit()
						+ " x "
						+ Globals.getGameModeUnitSet()
							.displayDistanceInUnitSet(
								face.getPreciseY().doubleValue())
						+ Globals.getGameModeUnitSet().getDistanceUnit();
		}
		return retString;
	}

	/**
	 * Get squares sub token
	 * 
	 * @param pc
	 * @return squares sub token
	 */
	public static String getSquaresToken(CharacterDisplay display)
	{
		OrderedPair face = getFace(display.getCharID());
		String retString = "";
		double squareSize = SettingsHandler.getGame().getSquareSize();
		if (CoreUtility.doublesEqual(face.getPreciseY().doubleValue(), 0.0))
		{
			retString =
					new DecimalFormat("#.#").format(face.getPreciseX()
						.doubleValue() / squareSize);
		}
		else
		{
			retString =
					new DecimalFormat("#.#").format(face.getPreciseX()
						.doubleValue() / squareSize)
						+ " x "
						+ new DecimalFormat("#.#").format(face.getPreciseY()
							.doubleValue() / squareSize);
		}
		return retString;
	}

	/**
	 * Get 1 sub token
	 * 
	 * @param pc
	 * @return 1 sub token
	 */
	public static String get1Token(CharacterDisplay display)
	{
		return Globals.getGameModeUnitSet().displayDistanceInUnitSet(
			getFace(display.getCharID()).getPreciseX().doubleValue());
	}

	/**
	 * Get 2 sub token
	 * 
	 * @param pc
	 * @return 2 sub token
	 */
	public static String get2Token(CharacterDisplay display)
	{
		return Globals.getGameModeUnitSet().displayDistanceInUnitSet(
			getFace(display.getCharID()).getPreciseY().doubleValue());
	}

	public static OrderedPair getFace(CharID id)
	{
		CodeControl controller =
				Globals.getContext().getReferenceContext().silentlyGetConstructedCDOMObject(
					CodeControl.class, "Controller");
		String varName = "Face";
		if (controller != null)
		{
			String setVarName = controller.get(ObjectKey.getKeyFor(String.class, "*FACE"));
			if (setVarName != null)
			{
				varName = setVarName;
			}
		}
		ResultFacet resultFacet = FacetLibrary.getFacet(ResultFacet.class);
		return (OrderedPair) resultFacet.getGlobalVariable(id, varName);
	}
}
