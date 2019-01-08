package nieve;

import basemod.ModButton;
import basemod.ModLabel;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.PostCreateStartingRelicsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;

@SpireInitializer // this annotation tells ModTheSpire to look at this class to initialize our mod
public class IroncladBlackBlood implements PostCreateStartingRelicsSubscriber,
	PostInitializeSubscriber {
	public static final Logger logger = LogManager.getLogger(IroncladBlackBlood.class.getName()); // lets us log output

	private ModLabel modOptionsLabel;
	private ModButton modOptionsButton;

	public static final String MODNAME = "Sample Mod"; // mod name
	public static final String AUTHOR = "You"; // your name
	public static final String DESCRIPTION = "v1.0.0 - makes the Ironclad OP"; // description (w/ version # if you want)

	public IroncladBlackBlood() {
		BaseMod.subscribe(this); // when our mod is loaded we tell BaseMod that we want to do something when the starting relics are created for the character when they start a run
	}

	public static void initialize() { // ModTheSpire will call this method to initialize because of the annotation we put at the top of the class definition
		@SuppressWarnings("unused")
		IroncladBlackBlood mod = new IroncladBlackBlood();
	}

	public static final String getResourcePath(String resource) {
		return "NieveModImages/" + resource;
	}

	@Override
	public void receivePostInitialize() {
		// Mod badge
		Texture badgeTexture = new Texture(getResourcePath("badge.png"));
		ModPanel settingsPanel = new ModPanel();
		modOptionsLabel = new ModLabel("Press this button to skip progress (unlock all cards/relics)", 400.0f, 700.0f,
			settingsPanel, (me) -> {
		});

		settingsPanel.addUIElement(modOptionsLabel);

		modOptionsButton = new ModButton( 400.0f, 400.0f,
			settingsPanel, (me) -> {unlockEverything();
		});
		settingsPanel.addUIElement(modOptionsButton);

		BaseMod.registerModBadge(badgeTexture, "NieveCharacter", "killmequickdeal", "Adds NieveCharacter character to the game.", settingsPanel);

	}

	public void unlockEverything()
	{
	}

	@Override
	public void receivePostCreateStartingRelics(AbstractPlayer.PlayerClass playerClass, ArrayList<String> relicsToAdd)
	{
		logger.info("CLASS: " + playerClass);
		if (playerClass == AbstractPlayer.PlayerClass.IRONCLAD) {
			relicsToAdd.add("Black Blood"); // here we simply give the player black blood in addition to their other starting relics
			relicsToAdd.remove("Burning Blood");
			UnlockTracker.markRelicAsSeen("Black Blood");
		}
	}
}