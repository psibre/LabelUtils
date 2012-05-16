# this script must not be run under windows, and Praat must be configured to write UTF-8
assert not windows
if unix
  prefs$ < 'preferencesDirectory$'/prefs5
else
  prefs$ < 'preferencesDirectory$'/Prefs5
endif
textOutEncoding$ = extractLine$(prefs$, "TextEncoding.outputEncoding: ")
assert textOutEncoding$ == "UTF-8"

# init TextGrid
tg = Create TextGrid... 0 2 "foo bar baz" bar

# tier 1
Insert boundary... 1 1
Set interval text... 1 1 
Set interval text... 1 2 \a"

# tier 2
Insert point... 2 1 \ae

# tier 3
Insert boundary... 3 1
Set interval text... 3 1 \ao
Convert to Unicode
Set interval text... 3 2 \at

# write TextGrids
Write to binary file... test.binary.TextGrid
Write to text file... test.UTF-8.TextGrid
Write to short text file... test.UTF-8.short.TextGrid

# write IntervalTiers
it = Extract tier... 1
Write to binary file... test.binary.IntervalTier
Write to text file... test.UTF-8.IntervalTier
Write to short text file... test.UTF-8.short.IntervalTier

# write PointTiers
select tg
pt = Extract tier... 2
Write to binary file... test.binary.TextTier
Write to text file... test.UTF-8.TextTier
Write to short text file... test.UTF-8.short.TextTier

# write Collections
plus it
Write to binary file... test.binary.Collection
Write to text file... test.UTF-8.Collection
Write to short text file... test.UTF-8.short.Collection

# split into parts
select tg
tg1 = Extract part... 0 1 no
Write to text file... test_part1.UTF-8.TextGrid
select tg
tg2 = Extract part... 1 2 no
Write to text file... test_part2.UTF-8.TextGrid
