package parachute.scripts.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;

public class CommandEvent implements Strategy {

	@Override
	public boolean activate() {
		if (Inventory.isFull()) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		Keyboard.getInstance().sendKeys("::empty");
		Time.sleep(1000, 1500);
	}

}
