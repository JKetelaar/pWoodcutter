package parachute.scripts.pwoodcutter.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.Walking;
import org.rev317.min.api.wrappers.Tile;

import parachute.scripts.pwoodcutter.main.Boot;
import parachute.scripts.pwoodcutter.main.Data;

public class RunEvent implements Strategy {

	@Override
	public boolean activate() {
		return Boot.Method == "Banking" && !Data.bankRegionDraynor.isInRegion() && Inventory.isFull();
	}

	@Override
	public void execute() {
		Walking.walkTo(Players.getMyPlayer().getLocation(), new Tile(3093, 3243));
		Time.sleep(300);
		while(Players.getMyPlayer().getAnimation() != -1){
			Time.sleep(100);
		}
	}

}
