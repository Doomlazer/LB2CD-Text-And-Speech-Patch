;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1898)
(include sci.sh)
(use Main)
(use Blink)

(public
	Pulitzer 26
)

(instance Pulitzer of Narrator
	(properties
		x 100
		y 10
		talkWidth 150
		back 15
	)
	
	(method (init)
		(= font gFont)
		(super init: &rest)
	)
)
