;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1901)
(include sci.sh)
(use Main)
(use Blink)

(public
	Bum 16
)

(instance Bum of Talker
	(properties
		view 828
		loop 1
		talkWidth 150
		back 15
		textX 10
		textY 10
	)
	
	(method (init)
		(= font gFont)
		(super init: &rest)
	)
)
