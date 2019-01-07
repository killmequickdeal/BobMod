import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostDungeonInitializeSubscriber;
import basemod.interfaces.PostExhaustSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import basemod.BaseMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.rooms.AbstractRoom;


@SpireInitializer
public class BobMod implements PostExhaustSubscriber,
	PostBattleSubscriber, PostDungeonInitializeSubscriber
{

	private int count, totalCount;

	private void resetCounts() {
		totalCount = count = 0;
	}

	public BobMod() {
		BaseMod.subscribe(this);
		resetCounts();
	}

	public static void initialize() {
		new BobMod();
	}

	@Override
	public void receivePostExhaust(AbstractCard c) {
		count++;
		totalCount++;
	}

	@Override
	public void receivePostBattle(AbstractRoom r) {
		System.out.println(count + " cards were exhausted this battle, " +
			totalCount + " cards have been exhausted so far this act.");
		count = 0;
	}

	@Override
	public void receivePostDungeonInitialize() {

	}

}
