;;; Sierra Script 1.0 - (do not remove this comment)
(script# 105)
(include sci.sh)
(use Main)
(use LBRoom)
(use n958)
(use Cycle)
(use View)
(use Obj)

(public
	logo 0
)

(instance logo of LBRoom
	(properties
		picture 105
		style $000a
	)
	
	(method (init)
		(proc958_0 128 108)
		(proc958_0 129 780)
		(proc958_0 132 105)
		(self setRegions: 92)
		(global2 drawPic: 780)
		(super init:)
		(sparkle init:)
		(gUser canControl: 0 canInput: 0)
		(gWrapSound number: 105 flags: 1 loop: 1 play:)
		(self setScript: sRunIt)
	)
)

(instance sRunIt of Script
	(properties)
	
	(method (doit)
		(Palette palANIMATE 64 235 1)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Palette palSET_INTENSITY 0 254 0)
				(global2 drawPic: 105 10)
				(= cycles 1)
			)
			(1
				(gGame handsOff: setCursor: 996 1 304 172)
				(= seconds 6)
			)
			(2
				(if (== (gWrapSound prevSignal?) 20)
					(sparkle setCycle: End self)
				else
					(-- state)
					(= cycles 1)
				)
			)
			(3
				(if (== (gWrapSound prevSignal?) 30)
					(sparkle x: 60 y: 145 loop: 1 cel: 0 setCycle: End self)
				else
					(-- state)
					(= cycles 1)
				)
			)
			(4
				(if
					(and
						(== (sparkle cel?) (sparkle lastCel:))
						(== (gWrapSound prevSignal?) -1)
					)
					(sparkle dispose:)
					(= cycles 1)
				else
					(-- state)
					(= cycles 1)
				)
			)
			(5
				(global2 newRoom: 100)
				(self dispose:)
			)
		)
	)
)

(instance sparkle of Prop
	(properties
		x 121
		y 54
		view 108
		priority 15
		signal $0010
	)
)
