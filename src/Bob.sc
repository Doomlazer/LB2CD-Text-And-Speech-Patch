;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1903)
(include sci.sh)
(use Main)
(use Blink)

(public
	Bob 14
)

(instance Bob of Narrator
	(properties
		x 15
		y 120
		talkWidth 275
		modeless 1
		back 15
	)
	
	(method (init)
		(= font gFont)
		(= showTitle 1)
		(super init: &rest)
	)
)
