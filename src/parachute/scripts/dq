package parachute.scripts.pwoodcutter.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Bank;
import org.rev317.min.api.methods.Inventory;

import parachute.scripts.pwoodcutter.main.Boot;
import parachute.scripts.pwoodcutter.main.Data;

public class BankEvent implements Strategy {

	@Override
	public boolean activate() {
		return Data.bankRegionDraynor.isInRegion() && Boot.Method == "Banking" && Inventory.isFull();
	}

	@Override
	public void execute() {
		if (Bank.isOpen()) {
			Bank.depositAllExcept(Data.hatchetsid);
		} else {
			Bank.open();
		}
		Time.sleep(1500);
	}

}
