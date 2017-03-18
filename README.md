# Antoine's implementation

So far, every basics requirements seems to be fulfill.
* Players can join and withdraw at any time.
* Board is redraw after a move
* Players can see the flipped card in real time, but can't play if it's not their turn

## TODO List

* [Matcher] If a non-playing player click a case, the event is handle when it's his turn instead of being dropped
* [DirtyExit] If a player close his game, the controller don't handle properly that the socket is now closed (should be caught with exceptions).
* [Bonus] A timer could be a nice feature to skip the turn of the current player after X seconds if he didn't play
* [RepoClean] Some codes should be removed such as ClaimPair which is not used anymore
