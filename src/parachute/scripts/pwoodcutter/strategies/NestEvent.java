package parachute.scripts.pwoodcutter.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.GroundItems;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.wrappers.GroundItem;

import parachute.scripts.pwoodcutter.main.Boot;
import parachute.scripts.pwoodcutter.main.Data;

public class NestEvent implements Strategy {
	GroundItem[] nests;

	@Override
	public boolean activate() {
		nests = GroundItems.getNearest(Data.nestids);
		return nests.length > 0 && !Inventory.isFull() && Boot.Method == "Banking";
	}

	@Override
	public void execute() {
		nests = GroundItems.getNearest(Data.nestids);
		if (nests.length > 0) {
			nests[0].take();
			Time.sleep(1000);
		}
	}

}
