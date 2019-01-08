package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class AbstractNieveCard extends CustomCard
{
	public AbstractNieveCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color,
								  CardRarity rarity, CardTarget target) {
		super(id, name, img, cost, rawDescription, type,
			color, rarity, target);
	}

	public int selfDamage;
	public boolean upgradeSelfDamage;
	public boolean isSelfDamageModified;
	public int poison;
	public boolean upgradePoison;
	public boolean isPoisonModified;

	public int slimed;
	public int baseSlimed;
	public boolean isSlimedModified;
	public boolean upgradeSlimed;


}
