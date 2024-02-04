;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2740)
(include sci.sh)
(use Polygon)
(use Obj)

(public
	poly2740Code 0
	proc2740_1 1
)

(local
	[thePoints 32] = [36 189 0 189 0 0 319 0 319 189 48 189 192 106 294 106 294 37 272 37 272 104 248 104 248 37 220 37 220 104 184 104]
)
(procedure (proc2740_1)
)

(instance poly2740Code of Code
	(properties)
	
	(method (doit param1)
		(param1 add: (poly2740a init: yourself:))
	)
)

(instance poly2740a of Polygon
	(properties)
	
	(method (init)
		(= type 2)
		(= size 16)
		(= points @thePoints)
	)
)
