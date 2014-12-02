package parachute.scripts.pwoodcutter.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.api.utils.Timer;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.Loader;
import org.rev317.min.accessors.Client;
import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.api.events.listeners.MessageListener;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.Skill;

import parachute.scripts.pwoodcutter.strategies.BankEvent;
import parachute.scripts.pwoodcutter.strategies.CommandEvent;
import parachute.scripts.pwoodcutter.strategies.CutEvent;
import parachute.scripts.pwoodcutter.strategies.NestEvent;
import parachute.scripts.pwoodcutter.strategies.RunEvent;
import parachute.scripts.pwoodcutter.strategies.TeleEvent;

@ScriptManifest(author = "parachute", category = Category.WOODCUTTING, description = "AIO woodcutter in development. Currently supports Willows, Yews and Oaks.", name = "pWoodcutter", servers = { "Ikov" }, version = 2.0)
public class Boot extends Script implements Paintable, MessageListener {
	private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();

	private Field field;
	private Field field2;
	
	private Client client;
	
	Timer time;
	
	int logsCut;
	int nestsPicked;
	int startLevel;
	int startExp;
	int wcIndex = 8;

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

	private void setFocusField() {
		try {
			field.set(client, true);
			field2.set(client, true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onExecute() {
		startLevel = Skill.getCurrentLevel(wcIndex);
		startExp = Skill.getCurrentExperience(wcIndex);
		time = new Timer();
		time.reset();
		this.getFocusFields();
		GUI gui = new GUI();
		gui.setVisible(true);
		gui.setAlwaysOnTop(true);
		while (gui.isVisible()) {
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
	public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		
		int xpGained = (Skill.getCurrentExperience(8) - startExp);
		
		g.drawImage(img1, -1, 337, null);
		g.setFont(font1);
		g.setColor(color1);
		g.drawString("Logs cut: " + logsCut + " (+ " + nestsPicked + " nests)", 168, 399);
		g.drawString("Logs per hour: " + time.getPerHour(logsCut), 168, 418);
		g.drawString("Exp gained: " + xpGained + " (" + time.getPerHour(xpGained)/1000 + "k)", 168, 437);
		g.drawString("Runtime: " + formatTime(time.getElapsedTime()), 168, 455);
		
		setFocusField();
	}

	private String formatTime(long time) {
		long runtime = time / 1000;
		
		int seconds = (int) (runtime % 60);
		int minutes = (int) (runtime / 60);
		int hours = (int) (runtime / 3600);
		
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

	private Image getImage(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch (IOException e) {
			return null;
		}
	}

	private final Color color1 = new Color(255, 255, 255);

	private final Font font1 = new Font("Arial", 1, 14);

	private final Image img1 = getImage("http://www.image-share.com/upload/2774/240.jpg");

	@Override
	public void messageReceived(MessageEvent arg0) {

		if (arg0.getMessage().contains("You get some")) {
			logsCut++;
		}
		if (arg0.getMessage().contains("A bird nest has fallen out of the three")) {
			nestsPicked++;
		}

	}

}
