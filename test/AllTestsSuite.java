import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ CardGameTest.class, CardTest.class, DeckTest.class, GameTest.class, HandTest.class, PlayerTest.class, PotTest.class, TexasRulesTest.class })
public class AllTestsSuite
{

}
