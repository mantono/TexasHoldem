package test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.cards.CardTest;
import test.cards.DeckTest;
import test.cards.HandTest;
import test.cards.PlayerTest;
import test.cards.TableTest;

@RunWith(Suite.class)
@SuiteClasses(
{
	TableTest.class,
	CardTest.class,
	DeckTest.class,
	HandTest.class,
	PlayerTest.class,
})

public class CardsTestsSuite
{

}