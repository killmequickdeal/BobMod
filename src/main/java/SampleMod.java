import com.megacrit.cardcrawl.characters.AbstractPlayer;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.PostCreateStartingRelicsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;

@SpireInitializer // this annotation tells ModTheSpire to look at this class to initialize our mod
public class SampleMod implements PostCreateStartingRelicsSubscriber,
	PostInitializeSubscriber {
	public static final Logger logger = LogManager.getLogger(SampleMod.class.getName()); // lets us log output

	public static final String MODNAME = "Sample Mod"; // mod name
	public static final String AUTHOR = "You"; // your name
	public static final String DESCRIPTION = "v1.0.0 - makes the Ironclad OP"; // description (w/ version # if you want)

	public SampleMod() {
		BaseMod.subscribeToPostCreateStartingRelics(this); // when our mod is loaded we tell BaseMod that we want to do something when the starting relics are created for the character when they start a run

		logger.info("subscribing to postInitialize event");
		BaseMod.subscribeToPostInitialize(this); // tell BaseMod that our mod wants to be notified once the game has finished initializing itself
	}

	public static void initialize() { // ModTheSpire will call this method to initialize because of the annotation we put at the top of the class definition
		@SuppressWarnings("unused")
		SampleMod mod = new SampleMod();
	}

	@Override
	public void receivePostInitialize() {
		// Mod badge
		Texture badgeTexture = new Texture("badge_img.png");
		ModPanel settingsPanel = new ModPanel();
		settingsPanel.addLabel("My mod does not have any settings (yet)!", 400.0f, 700.0f, (me) -> {});
		BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel); // once the game has initialized we want to set up a **mod badge** which is a little icon on the main menu screen that tells users that our mod has been loaded
	}

	@Override
	public void receivePostCreateStartingRelics(AbstractPlayer.PlayerClass playerClass, ArrayList<String> relicsToAdd)
	{
		relicsToAdd.add("Black Blood"); // here we simply give the player black blood in addition to their other starting relics
		UnlockTracker.markRelicAsSeen("Black Blood");
	}
}