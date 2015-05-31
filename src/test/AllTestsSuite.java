package test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
{
	CardsTestsSuite.class,
	TexasHoldemTestsSuite.class
})

public class AllTestsSuite
{

}