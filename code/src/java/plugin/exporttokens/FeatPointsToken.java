/*
 * FeatPointsToken.java
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
package plugin.exporttokens;

import pcgen.core.PlayerCharacter;
import pcgen.core.utils.CoreUtility;
import pcgen.io.ExportHandler;
import pcgen.io.exporttoken.Token;

/** 
 * Deal with FEATPOINTS Token
 */
public class FeatPointsToken extends Token
{
	/** Token Name */
	public static final String TOKENNAME = "FEATPOINTS";

	/**
	 * @see pcgen.io.exporttoken.Token#getTokenName()
	 */
	@Override
	public String getTokenName()
	{
		return TOKENNAME;
	}

	/**
	 * @see pcgen.io.exporttoken.Token#getToken(java.lang.String, pcgen.core.PlayerCharacter, pcgen.io.ExportHandler)
	 */
	@Override
	public String getToken(String tokenSource, PlayerCharacter pc,
		ExportHandler eh)
	{
		return Double.toString(getFeatPointsToken(pc));
	}

	/**
	 * Get the number of feat points remaining
	 * 
	 * @param pc - The PC to get the FEat points for
	 * @return The number of feat points remaining
	 */
	public static double getFeatPointsToken(PlayerCharacter pc)
	{
		if (!CoreUtility.doublesEqual(pc.getRemainingFeatPoolPoints(), 0))
		{
			return pc.getRemainingFeatPoolPoints();
		}
		return 0.0;
	}
}