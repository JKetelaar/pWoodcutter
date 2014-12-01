package parachute.scripts.pwoodcutter.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.methods.Walking;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;

import parachute.scripts.pwoodcutter.main.Boot;
import parachute.scripts.pwoodcutter.main.Data;

public class RunEvent implements Strategy {
	SceneObject[] chosen;

	@Override
	public boolean activate() {
		if (Data.Method == "Banking" && !Data.chosenBankRegion.isInRegion()
				&& Data.chosenRegion.isInRegion() && Inventory.isFull()) {
			return true;
		}
		chosen = SceneObjects.getNearest(Data.idchosen);
		if (Data.chosenRegion.isInRegion() && !Inventory.isFull()
				&& chosen.length == 0) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if (Inventory.isFull()) {
			Walking.walkTo(Players.getMyPlayer().getLocation(), Data.chosenTile);
			Time.sleep(500);
		}
		if (!Inventory.isFull()) {
			Walking.walkTo(Players.getMyPlayer().getLocation(),
					Data.TileSeersYews);
		}
		while (Players.getMyPlayer().getAnimation() != -1) {
			Time.sleep(100);
		}
	}

}
