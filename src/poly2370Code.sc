;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2370)
(include sci.sh)
(use Main)
(use MuseumPoints)
(use Polygon)
(use Obj)

(public
	poly2370Code 0
	pts2370 1
)

(local
	[local0 42] = [0 0 319 0 319 189 291 189 311 185 256 148 306 148 306 144 250 144 221 132 209 132 182 121 154 121 140 117 139 112 120 105 94 102 50 98 0 98 -100 179 -100 165]
	[local42 44] = [0 0 319 0 319 189 291 189 311 185 264 149 309 149 309 144 258 144 243 137 230 137 219 129 186 136 167 136 160 133 154 121 140 117 140 109 100 109 94 101 50 98 0 98]
	[thePoints 12] = [-100 179 -100 165 78 165 88 171 83 183 68 179]
	[thePoints_2 10] = [202 162 244 162 264 168 272 181 229 181]
	[thePoints_3 8] = [137 175 185 175 185 189 137 189]
)
(instance poly2370Code of Code
	(properties)
	
	(method (doit param1)
		(param1
			add: (poly2370a init: yourself:) (poly2370b init: yourself:)
		)
		(if (== global123 2)
			(param1 add: (poly2370c init: yourself:))
		)
		(if (proc999_5 global128 6 7 8)
			(param1 add: (poly2370d init: yourself:))
		)
	)
)

(instance poly2370a of Polygon
	(properties)
	
	(method (init)
		(= size (if (> global123 (= type 2)) 21 else 22))
		(= points (if (> global123 2) @local0 else @local42))
	)
)

(instance poly2370b of Polygon
	(properties)
	
	(method (init)
		(= type 2)
		(= size 6)
		(= points @thePoints)
	)
)

(instance poly2370c of Polygon
	(properties)
	
	(method (init)
		(= type 2)
		(= size 5)
		(= points @thePoints_2)
	)
)

(instance poly2370d of Polygon
	(properties)
	
	(method (init)
		(= type 2)
		(= size 4)
		(= points @thePoints_3)
	)
)

(instance pts2370 of MuseumPoints
	(properties
		midPtX 110
		midPtY 130
		westPtX -10
		westPtY 120
	)
)
