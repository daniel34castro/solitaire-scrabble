package com.fullstack;

import com.fullstack.services.ScrabbleServices;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

//@SpringBootTest
class FullStackApplicationTests {


	// calculateScore test
	@Test
	void calculateScoreTest() {
		// given
		String wordListToTest = "ola,zoo";
		// when
		String result = ScrabbleServices.calculateScore(wordListToTest);
		// o-1, l-1, a-1 , z-10, o-1, o-1
		// then
		assertThat(result).isEqualTo("15");
	}

	@Test
	void generateLettersTest() {
		// given
		int input = 10;
		// when
		List<Character> lettersGenerated = ScrabbleServices.generateLetters(input);
		int actualNumberOfChar = lettersGenerated.size();
		// o-1, l-1, a-1 , z-10, o-1, o-1
		// then
		int expectedNumberOfChar = 10;
		assertThat(actualNumberOfChar).isEqualTo(expectedNumberOfChar);
	}


}
