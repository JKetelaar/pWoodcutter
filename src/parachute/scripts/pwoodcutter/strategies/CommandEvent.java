package parachute.scripts.pwoodcutter.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;

import parachute.scripts.pwoodcutter.main.Boot;
import parachute.scripts.pwoodcutter.main.Data;

public class CommandEvent implements Strategy {
	@Override
	public boolean activate() {
		return Inventory.isFull() && Data.Method == "Empty";
	}

	@Override
	public void execute() {
		if (Game.getOpenBackDialogId() == Data.chatboxid) {
			Mouse.getInstance().click(220, 447, true);
		}
		if (Game.getOpenBackDialogId() == Data.chatboxfullid) {
			Mouse.getInstance().click(273, 444, true);
		}
		if (Game.getOpenBackDialogId() == -1) {
			Keyboard.getInstance().sendKeys("::empty");
		}
		Time.sleep(1000, 1500);
		System.out.println(Players.getMyPlayer().getLocation());
	}

}
