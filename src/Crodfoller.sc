;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1896)
(include sci.sh)
(use Main)
(use Blink)
(use View)

(public
	Crodfoller 7
)

(instance Crodfoller of Talker
	(properties
		x 220
		y 15
		view 1896
		loop 3
		talkWidth 150
		back 15
		textX -200
	)
	
	(method (init)
		(= font gFont)
		(super
			init: crodfollerBust crodfollerEyes crodfollerMouth &rest
		)
	)
)

(instance crodfollerBust of Prop
	(properties
		view 1896
		loop 1
	)
)

(instance crodfollerEyes of Prop
	(properties
		nsTop 31
		nsLeft 33
		view 1896
		loop 2
	)
)

(instance crodfollerMouth of Prop
	(properties
		nsTop 46
		nsLeft 27
		view 1896
	)
)
