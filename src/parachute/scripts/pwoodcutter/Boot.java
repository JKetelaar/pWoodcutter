package parachute.scripts.pwoodcutter;

import java.awt.Graphics;
import java.util.ArrayList;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;

import parachute.scripts.strategies.CommandEvent;
import parachute.scripts.strategies.CutEvent;
@ScriptManifest(author = "parachute", category = Category.WOODCUTTING, description = "Cuts willows then uses the ::empty command", name = "pWoodcutter", servers = { "Ikov" }, version = 1.0)
public class Boot extends Script implements Paintable {
	private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();
	
	@Override
	public boolean onExecute() {
		strategies.add(new CutEvent());
		strategies.add(new CommandEvent());
		provide(strategies);
		return true;
	}

	@Override
	public void paint(Graphics g) {
		//Eventueel in een aparte class om het to ordenen.
	}
	
}
