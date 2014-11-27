package parachute.scripts.pwoodcutter.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.GroundItems;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.GroundItem;
import org.rev317.min.api.wrappers.SceneObject;

import parachute.scripts.pwoodcutter.main.Boot;
import parachute.scripts.pwoodcutter.main.Data;

public class CutEvent implements Strategy {

	GroundItem[] nests;

	@Override
	public boolean activate() {
		nests = GroundItems.getNearest(Data.nestids);
		SceneObject[] willow = SceneObjects.getNearest(Data.idwillow);
		return willow[0] != null && !Inventory.isFull() && nestTrue();
	}

	@Override
	public void execute() {
		SceneObject[] willow = SceneObjects.getNearest(Data.idwillow);
		if (willow[0] != null) {
			if (willow[0].getLocation().distanceTo() < 20) {
				willow[0].interact(46);
				Time.sleep(2000);
			}
			while (Players.getMyPlayer().getAnimation() != -1
					&& !Inventory.isFull() && nestTrue()
					&& willow[0] != null && logErbij()) {
				nests = GroundItems.getNearest(Data.nestids);
				Time.sleep(10);
			}
		}
	}
	
	public boolean logErbij(){
		int Inventory1 = Inventory.getCount();
		Time.sleep(1500);
		int Inventory2 = Inventory.getCount();
		return Inventory1 < Inventory2;		
		
	}
	
	public boolean nestTrue(){
		if(Boot.Method == "Banking"){
			if(nests.length <= 0){
				return true;
			}
		}
		if(Boot.Method == "Empty"){
			return true;
		}
		return false;
	}

}
