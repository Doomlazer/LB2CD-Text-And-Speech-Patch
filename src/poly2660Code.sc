;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2660)
(include sci.sh)
(use MuseumPoints)
(use Polygon)
(use Obj)

(public
	poly2660Code 0
	pts2660 1
)

(local
	; BUGFIX: Fix game lockup in elevator room (heap patch).
	;
	; Moving to the bottom-left part of the elevator room can get ego stuck
	; softlocking the game, due to the way the room's obstacle polygon was
	; designed.
	;
	; We fix it by adjusting the coordinates of the room's obstacle polygon
	; so ego can't reach that area. Fix ported from:
	; https://github.com/scummvm/scummvm/blob/85702e06764f95a6b700e348dd90931613efdc29/engines/sci/engine/script_patches.cpp#L11798
;;;	[thePoints 20] = [123 189 0 189 0 0 319 0 319 189 197 189 195 140 182 114 139 114 123 141]
	[thePoints 20] = [123 189 0 189 0 0 319 0 319 189 197 189 195 140 182 114 143 114 123 170]
	; END OF BUGFIX
)
(instance poly2660Code of Code
	(properties)
	
	(method (doit param1)
		(param1 add: (poly2660a init: yourself:))
	)
)

(instance poly2660a of Polygon
	(properties)
	
	(method (init)
		(= type 2)
		(= size 10)
		(= points @thePoints)
	)
)

(instance pts2660 of MuseumPoints
	(properties
		midPtX 200
		midPtY 164
		northPtX 298
		northPtY 140
		eastPtX 319
		eastPtY 189
		westPtY 153
	)
)
