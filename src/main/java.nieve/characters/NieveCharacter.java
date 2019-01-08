package characters;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import java.util.ArrayList;
import javax.smartcardio.Card;
import patches.AbstractCardEnum;
import patches.NieveEnum;

public class NieveCharacter extends CustomPlayer
{

	public static final String NAME = "NIEVE";
	private static final CharacterStrings charStrings;

	public static final int ENERGY_PER_TURN = 3; // how much energy you get every turn
	public static final String NIEVE_SHOULDER_2 = "img/char/shoulder2.png"; // campfire pose
	public static final String NIEVE_SHOULDER_1 = "img/char/shoulder1.png"; // another campfire pose
	public static final String NIEVE_CORPSE = "img/char/corpse.png"; // dead corpse
	public static final String NIEVE_SKELETON_ATLAS = "img/char/skeleton.atlas"; // spine animation atlas
	public static final String NIEVE_SKELETON_JSON = "img/char/skeleton.json"; // spine animation json
	//public static final String[] orbTextures = {"SlimeboundImages/char/orb/layer1.png", "SlimeboundImages/char/orb/layer2.png", "SlimeboundImages/char/orb/layer3.png", "SlimeboundImages/char/orb/layer4.png", "SlimeboundImages/char/orb/layer5.png", "SlimeboundImages/char/orb/layer6.png", "SlimeboundImages/char/orb/layer1d.png", "SlimeboundImages/char/orb/layer2d.png", "SlimeboundImages/char/orb/layer3d.png", "SlimeboundImages/char/orb/layer4d.png", "SlimeboundImages/char/orb/layer5d.png"};
	public static Color cardRenderColor = new Color(0.0F, 0.1F, 0.0F, 1.0F);

	public NieveCharacter(String name, PlayerClass setClass) {
		super(name, setClass, null, null);
		this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
		this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values

		initializeClass(null, NIEVE_SHOULDER_2, // required call to load textures and setup energy/loadout
			NIEVE_SHOULDER_1,
			NIEVE_CORPSE,
			getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

		loadAnimation(NIEVE_SKELETON_ATLAS, NIEVE_SKELETON_JSON, 1.0F); // if you're using modified versions of base game animations or made animations in spine make sure to include this bit and the following lines

		AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
		e.setTime(e.getEndTime() * MathUtils.random());
	}

	public ArrayList<String> getStartingDeck() { // starting deck 'nuff said
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add("MyCard0");
		retVal.add("MyCard0");
		retVal.add("MyCard0");
		retVal.add("MyCard0");
		retVal.add("MyCard1");
		retVal.add("MyCard1");
		retVal.add("MyCard1");
		retVal.add("MyCard1");
		retVal.add("MyCard2");
		return retVal;
	}

	public ArrayList<String> getStartingRelics() { // starting relics - also simple
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add("MyRelic");
		UnlockTracker.markRelicAsSeen("MyRelic");
		return retVal;
	}

	public static final int STARTING_HP = 99;
	public static final int MAX_HP = 99;
	public static final int STARTING_GOLD = 99;
	public static final int HAND_SIZE = 5;
	public static final int ORB_SLOTS = 0;

	public CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen info plus hp and starting gold
		return new CharSelectInfo("My Character", "My character is a person from the outer worlds. He makes magic stuff happen.",
			STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, HAND_SIZE,
			this, getStartingRelics(), getStartingDeck(), false);
	}

	@Override
	public String getTitle(PlayerClass playerClass)
	{
		return "Nieve";
	}

	@Override
	public AbstractCard.CardColor getCardColor()
	{
		return AbstractCardEnum.NIEVE_COLOR;
	}

	@Override
	public Color getCardRenderColor()
	{
		return cardRenderColor;
	}

	@Override
	public AbstractCard getStartCardForEvent()
	{
		return new Strike_Nieve();
	}

	@Override
	public Color getCardTrailColor()
	{
		return cardRenderColor.cpy();
	}

	@Override
	public int getAscensionMaxHPLoss()
	{
		return 10;
	}

	@Override
	public BitmapFont getEnergyNumFont()
	{
		return FontHelper.energyNumFontGreen;
	}

	@Override
	public void doCharSelectScreenSelectEffect()
	{
		CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
	}

	@Override
	public String getCustomModeCharacterButtonSoundKey()
	{
		return null;
	}

	@Override
	public String getLocalizedCharacterName()
	{
		return this.name;
	}

	@Override
	public AbstractPlayer newInstance()
	{
		return new NieveCharacter(this.name, this.chosenClass);
	}

	@Override
	public String getSpireHeartText()
	{
		return "Slayer Task Complete!";
	}

	@Override
	public Color getSlashAttackColor()
	{
		return Color.GREEN;
	}

	@Override
	public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect()
	{
		return new AbstractGameAction.AttackEffect[0];
	}

	@Override
	public String getVampireText()
	{
		return Vampires.DESCRIPTIONS[5];
	}

}
