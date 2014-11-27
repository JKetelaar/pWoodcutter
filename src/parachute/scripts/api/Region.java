package parachute.scripts.api;

import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Tile;

/**
 * @author parachute
 */
public class Region {

	Tile TileNW;
	Tile TileSE;

	public Region() {
	}

	public Region(Tile TileNW, Tile TileSE) {
		this.TileNW = TileNW;
		this.TileSE = TileSE;
	}

	public boolean isInRegion() {

		if (Players.getMyPlayer().getLocation().getX() > TileNW.getX()
				&& Players.getMyPlayer().getLocation().getX() < TileSE.getX()
				&& Players.getMyPlayer().getLocation().getY() < TileNW.getY()
				&& Players.getMyPlayer().getLocation().getY() > TileSE.getY()) {
			return true;
		}
		// Region bankRegion = new Region(Tile, Tile2);

		return false;
	}

}
