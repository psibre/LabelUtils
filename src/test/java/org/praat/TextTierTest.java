package org.praat;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

public class TextTierTest {

	private PraatObject utf8TextTier;
	private PraatObject utf8ShortTextTier;

	@Before
	public void setUp() throws Exception {
		utf8TextTier = PraatFile.read("test.UTF-8.TextTier");
		utf8ShortTextTier = PraatFile.read("test.UTF-8.short.TextTier");
	}

	@Test
	public void compareFormats() {
		assertThat(utf8TextTier, is(equalTo(utf8ShortTextTier)));
	}

}
