package parachute.scripts.pwoodcutter.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.GroundItems;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.GroundItem;
import org.rev317.min.api.wrappers.SceneObject;

import parachute.scripts.pwoodcutter.main.Data;

public class CutEvent implements Strategy {

	GroundItem[] nests;
	SceneObject[] chosen;

	@Override
	public boolean activate() {
		nests = GroundItems.getNearest(Data.nestids);
		chosen = SceneObjects.getNearest(Data.idchosen);
		return chosen.length > 0 && chosen[0] != null && !Inventory.isFull() && nestTrue() && Data.chosenRegion.isInRegion();
	}

	@Override
	public void execute() {
		chosen = SceneObjects.getNearest(Data.idchosen);
		if (chosen[0] != null) {
				chosen[0].interact(1);
				Time.sleep(2000);
			while (Players.getMyPlayer().getAnimation() != -1
					&& !Inventory.isFull() && nestTrue()
					&& chosen[0] != null && logErbij()) {
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
		if(Data.Method == "Banking"){
			if(nests.length <= 0){
				return true;
			}
		}
		if(Data.Method == "Empty"){
			return true;
		}
		return false;
	}

}
