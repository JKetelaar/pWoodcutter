package parachute.scripts.pwoodcutter.main;

import java.awt.Graphics;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.Loader;
import org.rev317.min.accessors.Client;

import parachute.scripts.pwoodcutter.strategies.BankEvent;
import parachute.scripts.pwoodcutter.strategies.CommandEvent;
import parachute.scripts.pwoodcutter.strategies.CutEvent;
import parachute.scripts.pwoodcutter.strategies.NestEvent;
import parachute.scripts.pwoodcutter.strategies.RunEvent;
import parachute.scripts.pwoodcutter.strategies.TeleEvent;
@ScriptManifest(author = "parachute", category = Category.WOODCUTTING, description = "AIO woodcutter in development. Currently supports Willows and Oaks.", name = "pWoodcutter", servers = { "Ikov" }, version = 2.0)
public class Boot extends Script implements Paintable {
	private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();
	
    private Field field;
    private Field field2;
    private Client client;
	
    private void getFocusFields() {
        try {
        	System.out.println("Getting focus fields ..");
            client = Loader.getClient();
            field = client.getClass().getSuperclass().getDeclaredField("x");
            field.setAccessible(true);
            field2 = client.getClass().getSuperclass().getDeclaredField("w");
            field2.setAccessible(true);
            
            System.out.println("Found fields: " + field + ", " + field2);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    private void setFocusField(){
        try {
            field.set(client, true);
            field2.set(client, true);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
	
	@Override
	public boolean onExecute() {
        this.getFocusFields();
		GUI gui = new GUI();
		gui.setVisible(true);
		gui.setAlwaysOnTop(true);
		while(gui.isVisible()){
			Time.sleep(500);
		}
		strategies.add(new CutEvent());
		strategies.add(new CommandEvent());
		strategies.add(new BankEvent());
		strategies.add(new RunEvent());
		strategies.add(new NestEvent());
		strategies.add(new TeleEvent());
		provide(strategies);
		return true;
	}
	
	@Override
	public void paint(Graphics g) {
        setFocusField();
	}

	
}
