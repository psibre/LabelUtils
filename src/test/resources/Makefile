textfiles := \
	test.UTF-8.Collection \
	test.UTF-8.IntervalTier \
	test.UTF-8.TextGrid \
	test.UTF-8.TextTier \
	test.UTF-8.short.Collection \
	test.UTF-8.short.IntervalTier \
	test.UTF-8.short.TextTier \
	test.UTF-8.short.TextGrid

all: $(textfiles) \
	$(subst UTF-8,UTF-8.CRLF,$(textfiles)) \
	$(subst UTF-8,UTF-16,$(textfiles)) \
	$(subst UTF-8,UTF-16.CRLF,$(textfiles)) \
	$(subst UTF-8,ISO-8859,$(textfiles)) \
	$(subst UTF-8,ISO-8859.CRLF,$(textfiles))

$(textfiles): Makefile.praat
	$(shell praat Makefile.praat)
	$(foreach textfile,$(textfiles), \
		$(shell unix2dos -n $(textfile) \
			$(subst UTF-8,UTF-8.CRLF,$(textfile))))

test.UTF-16.%: test.UTF-8.%
	$(shell recode utf8..utf16 < $< > $@)

test.ISO-8859.%: test.UTF-8.%
	$(shell recode utf8..latin1 < $< > $@)

clean:
	rm -fv test.*
