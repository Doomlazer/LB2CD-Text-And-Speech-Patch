;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1899)
(include sci.sh)
(use Main)
(use Blink)
(use View)

(public
	Coroner 21
)

(instance Coroner of Talker
	(properties
		x 100
		y 0
		nsTop 5
		nsLeft 5
		view 1899
		loop 3
		talkWidth 150
		back 15
	)
	
	(method (init)
		(= font gFont)
		(super init: coronerBust coronerEyes coronerMouth &rest)
	)
)

(instance coronerBust of Prop
	(properties
		view 1899
		loop 1
	)
)

(instance coronerEyes of Prop
	(properties
		nsTop 36
		nsLeft 31
		view 1899
		loop 2
	)
)

(instance coronerMouth of Prop
	(properties
		nsTop 55
		nsLeft 29
		view 1899
	)
)
