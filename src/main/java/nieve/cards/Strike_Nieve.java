package nieve.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import nieve.NieveMod;

public class Strike_Nieve extends AbstractNieveCard
{
	public static final String ID = NieveMod.makeID("Strike_Nieve");
	public static final String NAME;
	^public static final String DESCRIPTION;
	public static String UPGRADED_DESCRIPTION;
	public static final String IMG_PATH = "cards/attackSlime.png";
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.BASIC;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;

	private static final CardStrings cardStrings;

	private static final int COST = 1;
	private static final int POWER = 6;
	private static final int UPGRADE_BONUS = 3;

	@Override
	public void upgrade()
	{

	}

	@Override
	public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster)
	{

	}
}
