package parachute.scripts.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class CutEvent implements Strategy {
	int idwillow = 1308;

	@Override
	public boolean activate() {
		SceneObject[] willow = SceneObjects.getNearest(idwillow);
		if (willow[0] != null) {
			if (!Inventory.isFull()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void execute() {
		SceneObject[] willow = SceneObjects.getNearest(idwillow);
		if (willow[0] != null) {
			if(willow[0].getLocation().distanceTo() < 20){
			willow[0].interact(46);
			Time.sleep(1000, 1500);
			}
			while(Players.getMyPlayer().getAnimation() != -1 && !Inventory.isFull()){
				Time.sleep(100);
			}
		}
	}

}
