package nieve;

import static basemod.DevConsole.logger;
import basemod.interfaces.EditCharactersSubscriber;
import nieve.characters.NieveCharacter;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import basemod.BaseMod;
import nieve.patches.AbstractCardEnum;
import nieve.patches.NieveEnum;


@SpireInitializer
public class NieveMod implements EditCharactersSubscriber
{
	private static final com.badlogic.gdx.graphics.Color NIEVE_COLOR = com.megacrit.cardcrawl.helpers.CardHelper.getColor(25.0F, 95.0F, 25.0F);

	private static final String ATTACK_CARD = "512/bg_attack_slimebound.png";
	private static final String SKILL_CARD = "512/bg_skill_slimebound.png";
	private static final String POWER_CARD = "512/bg_power_slimebound.png";
	private static final String ENERGY_ORB = "512/card_slimebound_orb.png";
	private static final String CARD_ENERGY_ORB = "512/card_small_orb.png";

	private static final String ATTACK_CARD_PORTRAIT = "1024/bg_attack_slimebound.png";
	private static final String SKILL_CARD_PORTRAIT = "1024/bg_skill_slimebound.png";
	private static final String POWER_CARD_PORTRAIT = "1024/bg_power_slimebound.png";
	private static final String ENERGY_ORB_PORTRAIT = "1024/card_slimebound_orb.png";

	public static NieveCharacter nieveCharacter;


	public NieveMod() {
		BaseMod.subscribe(this);
		BaseMod.addColor(AbstractCardEnum.NIEVE_COLOR,
			NIEVE_COLOR, NIEVE_COLOR, NIEVE_COLOR, NIEVE_COLOR, NIEVE_COLOR, NIEVE_COLOR, NIEVE_COLOR,
			getResourcePath(ATTACK_CARD), getResourcePath(SKILL_CARD),
			getResourcePath(POWER_CARD), getResourcePath(ENERGY_ORB),
			getResourcePath(ATTACK_CARD_PORTRAIT), getResourcePath(SKILL_CARD_PORTRAIT),
			getResourcePath(POWER_CARD_PORTRAIT), getResourcePath(ENERGY_ORB_PORTRAIT), getResourcePath(CARD_ENERGY_ORB));
	}

	public static void initialize() {
		NieveMod mod = new NieveMod();
	}

	public static final String getResourcePath(String resource) {
		return "NieveImages/" + resource;
	}

	@Override
	public void receiveEditCharacters()
	{
		logger.info("begin editing nieve.characters");

		logger.info("add " + NieveEnum.NIEVE.toString());
		nieveCharacter = new NieveCharacter("Nieve", NieveEnum.NIEVE);
		BaseMod.addCharacter(nieveCharacter, getResourcePath("charSelect/button.png"), getResourcePath("charSelect/portrait.png"), NieveEnum.NIEVE);

		logger.info("done editing nieve.characters");
	}

	public static String makeID(String baseText) {
		return "nieve:" + baseText;
	}
}
