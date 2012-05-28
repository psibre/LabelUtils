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
Insert boundary... 1 0.1
Insert boundary... 1 0.2
Insert boundary... 1 0.3
Insert boundary... 1 0.4
Insert boundary... 1 0.5
Insert boundary... 1 0.6
Insert boundary... 1 0.7
Insert boundary... 1 0.8
Insert boundary... 1 0.9
Insert boundary... 1 1.0
Insert boundary... 1 1.1
Insert boundary... 1 1.2
Insert boundary... 1 1.3
Insert boundary... 1 1.4
Insert boundary... 1 1.5
Insert boundary... 1 1.6
Insert boundary... 1 1.7
Insert boundary... 1 1.8
Insert boundary... 1 1.9
Set interval text... 1 1 
Set interval text... 1 2 \ao
Set interval text... 1 3 b
Set interval text... 1 4 \c,
Set interval text... 1 5 \dh
Set interval text... 1 6 \e'
Set interval text... 1 7 f
Set interval text... 1 8 G
Set interval text... 1 9 h
Set interval text... 1 10 \i"
Set interval text... 1 11 j
Set interval text... 1 12 k
Set interval text... 1 13 \L/
Convert to Unicode
Set interval text... 1 14 \mj
Set interval text... 1 15 \n~
Set interval text... 1 16 \o/
Set interval text... 1 17 \pi
Set interval text... 1 18 q
Set interval text... 1 19 \rt
Set interval text... 1 20 \sh

# tier 2
Insert point... 2 1 \th

# tier 3
Insert boundary... 3 1.2
Set interval text... 3 1 \hs
Set interval text... 3 2 \vt

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
tg1 = Extract part... 0 11 no
Write to text file... test_part1.UTF-8.TextGrid
select tg
tg2 = Extract part... 11 21 no
Write to text file... test_part2.UTF-8.TextGrid
