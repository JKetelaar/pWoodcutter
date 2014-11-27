package parachute.scripts.pwoodcutter.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Bank;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Players;
import org.rev317.min.callback.MenuAction;

import parachute.scripts.pwoodcutter.main.Data;

public class TeleEvent implements Strategy {

	@Override
	public boolean activate() {
		return !Data.chosenRegion.isInRegion() && !Inventory.isFull() && !Bank.isOpen();
	}

	@Override
	public void execute() {
		if (Data.Location == "VEOaks") {
			Mouse.getInstance().click(740, 185, true);
			Time.sleep(1000);
			Mouse.getInstance().click(568, 357, true);
			Time.sleep(1000);
			Mouse.getInstance().click(265, 384, true);
			Time.sleep(1000);
			Mouse.getInstance().click(265, 384, true);
			Time.sleep(3000);
		}
		if (Data.Location == "DWillows") {
			Mouse.getInstance().click(740, 185, true);
			Time.sleep(1000);
			Mouse.getInstance().click(568, 357, true);
			Time.sleep(1000);
			Mouse.getInstance().click(265, 384, true);
			Time.sleep(1000);
			Mouse.getInstance().click(265, 401, true);
			Time.sleep(3000);
		}
	}
}
