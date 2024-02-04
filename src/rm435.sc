;;; Sierra Script 1.0 - (do not remove this comment)
(script# 435)
(include sci.sh)
(use Main)
(use LBRoom)
(use Inset)
(use PolyPath)
(use CueObj)
(use n958)
(use Sound)
(use View)
(use Obj)

(public
	rm435 0
)

(local
	local0
)
(instance rm435 of LBRoom
	(properties
		style $800a
	)
	
	(method (init)
		(gIconBar disable: 7)
		(self picture: (if (proc0_2 72) 436 else 430))
		(proc958_0 128 431 430)
		(proc958_0 129 436 780)
		(proc958_0 132 90 6 2)
		(if (== global123 5)
			(self setRegions: 94)
		else
			(WrapMusic pause: 1)
			(self setRegions: 90)
		)
		(gEgo heading: 180)
		(super init:)
		(if (not (proc0_2 72))
			(gEgo
				init:
				normalize: (if (== global123 5) 426 else 831)
				setScale: 125
			)
			(switch gGNumber
				(440 (gEgo x: 51 y: 121))
				(480 (gEgo x: 114 y: 112))
				(420 (gEgo x: 319 y: 125))
			)
			(gGame points: 1 143)
			(eastDoor init:)
			(eastDoor2 init:)
			(westDoor init:)
			(westDoor2 init:)
			(wireEnd init:)
			(skewer init: stopUpd:)
		)
		(global2 setScript: sRunIt)
	)
	
	(method (dispose)
		(if local0 (wrapMusic dispose: 1))
		(screamAndLook dispose:)
		(if (< global123 5) (WrapMusic pause: 0))
		(super dispose:)
	)
	
	(method (notify)
		(if (== global123 5)
			(self setInset: 0)
			((ScriptID 94 1) setReal: (ScriptID 94 1) 2)
			(global2 newRoom: 430)
		)
	)
)

(instance sEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(if (== gGNumber 420)
					(gEgo setMotion: PolyPath 300 133 self)
				else
					(gEgo setMotion: PolyPath 100 133 self)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance sRunIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (proc0_2 72)
					(self changeState: 4)
				else
					(self setScript: sEnter self)
				)
			)
			(1
				(gEgo
					setMotion: PolyPath (gEgo x?) (+ (gEgo y?) 10) self
				)
			)
			(2
				(gEgo hide:)
				(eastDoor dispose:)
				(eastDoor2 dispose:)
				(westDoor dispose:)
				(westDoor2 dispose:)
				(wireEnd dispose:)
				(skewer dispose:)
				(global2 picture: 465 drawPic: 465)
				(= cycles 1)
			)
			(3
				(wrapMusic init: -1 2 6)
				(gGameMusic2 number: 83 flags: 1 loop: 1 play:)
				(= local0 1)
				(= seconds 3)
			)
			(4
				(if (and (proc0_2 72) (!= global123 5))
					(screamAndLook number: 6 loop: -1 flags: 1 play:)
				else
					(proc0_3 72)
				)
				(client setInset: inZiggyDead self)
			)
			(5
				(gIconBar enable: 7)
				(global2 newRoom: 430)
			)
		)
	)
)

(instance wireEnd2 of View
	(properties
		x 33
		y 41
		noun 9
		view 431
		loop 4
		priority 13
		signal $0010
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(21
				(if (proc0_2 44)
					(super doVerb: theVerb &rest)
				else
					(proc0_3 63)
					(inZiggyDead dispose:)
				)
			)
			(8
				(inZiggyDead setInset: inWire)
			)
			(13
				(inZiggyDead doVerb: theVerb)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance neck of Feature
	(properties
		x 1
		y 1
		noun 5
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(inZiggyDead doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance jacket of Feature
	(properties
		x 1
		y 1
		noun 3
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(inZiggyDead doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pants of Feature
	(properties
		x 1
		y 1
		noun 6
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(inZiggyDead doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance ptero of Feature
	(properties
		x 1
		y 1
		noun 2
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(inZiggyDead doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance wire1 of Feature
	(properties
		x 1
		y 1
		noun 8
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(21
				(if (proc0_2 44)
					(super doVerb: theVerb &rest)
				else
					(proc0_3 63)
					(inZiggyDead dispose:)
				)
			)
			(8
				(inZiggyDead setInset: inWire)
			)
			(13
				(inZiggyDead doVerb: theVerb)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance wire2 of Feature
	(properties
		x 1
		y 1
		noun 9
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(inZiggyDead doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance wire3 of Feature
	(properties
		x 1
		y 1
		noun 10
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(inZiggyDead doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance lHand of Feature
	(properties
		x 1
		y 1
		noun 4
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(inZiggyDead doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rHand of Feature
	(properties
		x 1
		y 1
		noun 7
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(inZiggyDead doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance blood of Feature
	(properties
		x 1
		y 1
		noun 1
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(inZiggyDead doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance wound of Feature
	(properties
		x 1
		y 1
		noun 11
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(inZiggyDead doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inWire of Inset
	(properties
		view 431
		loop 2
		x 8
		y 42
		disposeNotOnMe 1
		noun 8
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(21
				(if (proc0_2 44)
					(super doVerb: theVerb &rest)
				else
					(proc0_3 63)
					(self dispose:)
					(inZiggyDead dispose:)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance inZiggyDead of Inset
	(properties
		picture 436
		style $800a
		hideTheCast 1
	)
	
	(method (init)
		(super init: &rest)
		(global2 picture: 780)
		(gGame handsOn:)
		(proc0_8 1)
		(neck init: setOnMeCheck: 1 16384)
		(jacket init: setOnMeCheck: 1 128)
		(pants init: setOnMeCheck: 1 256)
		(ptero init: setOnMeCheck: 1 2)
		(wire1 init: setOnMeCheck: 1 4)
		(wire2 init: setOnMeCheck: 1 8)
		(wire3 init: setOnMeCheck: 1 16)
		(lHand init: setOnMeCheck: 1 32)
		(rHand init: setOnMeCheck: 1 64)
		(blood init: setOnMeCheck: 1 512)
		(wound init: setOnMeCheck: 1 1024)
		(if (not (proc0_2 44)) (wireEnd2 init:))
		(= style 100)
	)
	
	(method (dispose)
		(= style -32758)
		(screamAndLook fade: 0 12 30 1)
		(proc0_8 0)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13 (self dispose:))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance westDoor2 of View
	(properties
		x 74
		y 66
		view 432
		loop 1
		cel 4
		signal $4000
	)
)

(instance eastDoor2 of View
	(properties
		x 312
		y 68
		view 432
		loop 3
		cel 9
		priority 9
		signal $4010
	)
)

(instance eastDoor of View
	(properties
		x 292
		y 68
		view 432
		loop 2
		cel 9
		signal $4000
	)
)

(instance westDoor of View
	(properties
		x 53
		y 66
		view 432
		cel 4
		priority 9
		signal $4010
	)
)

(instance skewer of View
	(properties
		x 3
		y 189
		z 89
		view 430
		loop 1
		priority 15
		signal $5010
	)
)

(instance wireEnd of View
	(properties
		x 21
		y 130
		view 431
		loop 4
		cel 1
		priority 15
		signal $4010
	)
)

(instance wrapMusic of WrapMusic
	(properties)
	
	(method (init)
		(= wrapSound screamAndLook)
		(super init: &rest)
	)
)

(instance screamAndLook of Sound
	(properties)
)
