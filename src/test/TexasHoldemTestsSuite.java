package test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.texasholdem.BettingPlayerTest;
import test.texasholdem.CardCombinationTest;
import test.texasholdem.GameStateMachineTest;
import test.texasholdem.GameTest;
import test.texasholdem.PlayerBookTest;
import test.texasholdem.PotTest;
import test.texasholdem.TexasRulesTest;


@RunWith(Suite.class)
@SuiteClasses(
{
	BettingPlayerTest.class,
	GameStateMachineTest.class,
	GameTest.class,
	PotTest.class,
	TexasRulesTest.class,
	PlayerBookTest.class,
	CardCombinationTest.class
})

public class TexasHoldemTestsSuite
{

}