# Collection of solved puzzles

## [SumCombinations.kt](./SumCombinations.kt)
Find all unique combinations of numbers from the given array that add up to
the specified number

For 3 and [1, 2] there are 2 combinations: (3*1, 0*2) and (1*1, 1*2), so the solution
must return a collection with these arrays in any order [[3, 0], [1, 2]]

## [RollingMaximum.kt](./RollingMaximum.kt)
For each element in a sequence find the current maximum in a rolling window of the
specified size. The leading edge of the window begins at the first element of the sequence and
ends at the last one - the window does not roll past the end.

For [1, 2, 3, 2, 1] and the window size 2 find [<0:1>, <1:2>, <2:3>, <3:3>, <4:2>, <5:2>]

## [RendevousHash.kt](./RendevousHash.kt)
Select K resources out of the pool of N (K <= N) for processing of an object.

The object and all resource have unique and stable identifiers.