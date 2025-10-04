;;; Sierra Script 1.0 - (do not remove this comment)
(script# 454)
(include sci.sh)
(use Main)
(use LBRoom)
(use ExitFeature)
(use Inset)
(use PChase)
(use Scaler)
(use PolyPath)
(use CueObj)
(use MoveFwd)
(use n958)
(use StopWalk)
(use Sound)
(use Cycle)
(use View)
(use Obj)

(public
	rm454 0
)

(local
	local0 =  100
	local1
	local2
)
(instance rm454 of LBRoom
	(properties
		noun 11
		picture 454
		style $000b
		east 450
		vanishingY 20
	)
	
	(method (init)
		(proc958_0 132 1 451 450)
		(proc958_0 130 2450)
		(switch gGNumber
			(666
				(gGame handsOff:)
				(gGameMusic2 number: 450 flags: 1 loop: -1 play:)
				(Palette palSET_INTENSITY 0 255 100)
				(= style 100)
				(gEgo
					normalize: 831
					x: 211
					y: 120
					loop: 2
					setScale: Scaler 131 30 190 21
					init:
				)
				(lid1 init: stopUpd: approachVerbs: 1 4 2 6 setPri: 7)
				(global2 setScript: sEnterTunnel)
			)
			(455
				(gGame handsOff:)
				(= style 100)
				(gEgo
					ignoreActors:
					x: 248
					y: 121
					view: 454
					loop: 2
					cel: (gEgo lastCel:)
					init:
				)
				(global2 setScript: sFinishIt)
			)
			(456
				(= style 100)
				(gGame handsOn:)
				;(gIconBar disable: 7)
				(gEgo
					init:
					posn: 96 135
					setScale: Scaler 131 30 190 21
					normalize: 831
				)
				(lid1 init: stopUpd: approachVerbs: 1 4 2 6 setPri: 7)
			)
			(else 
				(gEgo
					init:
					posn: 160 (gEgo y?)
					setScale: Scaler 131 30 190 21
					normalize: (if (== global123 5) 426 else 831)
				)
				(lid1 init: stopUpd: approachVerbs: 1 4 2 6 setPri: 7)
				(gGame handsOn:)
				;(gIconBar disable: 7)
			)
		)
		(if (== global123 5)
			(cond 
				((< global87 5) (= local2 85))
				((< global87 10) (= local2 60))
				((<= global87 15) (= local2 35))
			)
			(if (not (HaveMouse)) (= local2 (* 2 local2)))
			(self setRegions: 94)
			(global2 obstacles: (List new:))
			((ScriptID 2454 0) doit: (global2 obstacles?))
		else
			(self setRegions: 90)
		)
		(super init:)
		(if
			(or
				(and (== global123 2) (proc0_10 -32480 1))
				(> global123 2)
			)
			(blood init: approachVerbs: 1 4 8 stopUpd: cel: global127)
			(footprint init: stopUpd: approachVerbs: 1 4 8)
			(if (and (== global123 2) (proc0_10 -32480 1))
				(deadPippin init: stopUpd: setPri: 6)
			)
			(if (not (gEgo has: 20))
				(medallion init: stopUpd: approachVerbs: 1 4 8)
			)
		)
		(lid2
			init:
			stopUpd:
			cel: (if global115 3 else 2)
			approachVerbs: 1 4 2 6
			setPri: 7
		)
		(poster init: approachVerbs: 1 4)
		(post init: approachVerbs: 1 4)
		(mummy1 init: approachVerbs: 1 4 2 6 setOnMeCheck: 1 2)
		(secretDoor1 init: approachVerbs: 1 4 2 6)
		(secretDoor2 init: approachVerbs: 1 4 2 6)
		(mummy3 init: approachVerbs: 1 4 2 6 setOnMeCheck: 1 16)
		(rosetta init: approachVerbs: 1 4 6 2 8 setOnMeCheck: 1 4)
		(pyramid init: setOnMeCheck: 1 64)
		(pyramid2 init: setOnMeCheck: 1 16384)
		(eastExitFeature init:)
	)
	
	(method (dispose)
		(gIconBar enable: 7)
		(DisposeScript 2454)
		(super dispose: &rest)
	)
	
	(method (newRoom newRoomNumber)
		(if
			(and
				(< global127 6)
				(== global123 2)
				(proc0_10 -32480 1)
			)
			(++ global127)
		)
		(super newRoom: newRoomNumber)
	)
	
	(method (notify)
		(if (== global123 5)
			(if (global2 script?)
				((global2 script?) next: sDie)
			else
				(global2 setScript: sDie)
			)
		)
	)
)

(instance sEnterTunnel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(secretDoor1
					setMotion: MoveTo (- (secretDoor1 x?) 10) (secretDoor1 y?)
				)
				(secretDoor2
					setMotion: MoveTo (+ (secretDoor2 x?) 8) (secretDoor2 y?) self
				)
			)
			(2
				(gEgo setMotion: MoveFwd 25 self)
			)
			(3
				(secretDoor1
					setMotion: MoveTo (+ (secretDoor1 x?) 10) (secretDoor1 y?)
				)
				(secretDoor2
					setMotion: MoveTo (- (secretDoor2 x?) 8) (secretDoor2 y?) self
				)
			)
			(4
				(gGame handsOn:)
				;(gIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sDie of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(gEgo setMotion: PolyPath 180 160 self)
			)
			(1
				(oriley
					init:
					setScale: Scaler 131 30 190 21
					setCycle: Walk
					setMotion: PChase gEgo 22 self
				)
				(gWrapSound number: 3 flags: 1 loop: 1 play:)
			)
			(2
				(oriley view: 424)
				(oriley cel: 0)
				(proc0_5 gEgo oriley)
				(proc0_5 oriley gEgo)
				(= cycles 4)
			)
			(3 (oriley setCycle: End self))
			(4
				(thudSound play:)
				(gEgo view: 858 setCycle: End self)
			)
			(5
				(= global145 0)
				(global2 newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance sOpenRedCoffin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(lid1 hide:)
				(gEgo
					view: 454
					setLoop: 2
					cel: 0
					posn: 248 121
					setScale: Scaler 100 100 190 21
					cycleSpeed: 6
					setCycle: End self
				)
				(nCreak number: 452 play:)
			)
			(1
				(gLb2Messager
					say: 6 4 (if (== global123 5) 1 else 2) 0 self
				)
			)
			(2
				(gEgo setCycle: Beg self)
				(nCreak number: 453 play:)
			)
			(3
				(nCreak number: 455 play:)
				(lid1 show:)
				(gEgo normalize: 831 setScale: Scaler 131 30 190 21)
				(gGame handsOn:)
				;(gIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sHandleTheCase of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if register (= global115 1) else (= global115 0))
				(gGame handsOff:)
				(lid2 hide:)
				(gEgo
					view: 454
					loop: 3
					cel: (if register 0 else (gEgo lastCel:))
					posn: 184 122
					cycleSpeed: 16
					setScale: Scaler 100 100 190 21
					setCycle: (if register End else Beg) self
				)
				(nCreak number: (if register 452 else 453) play:)
			)
			(1
				(if (not register) (nCreak number: 455 play:))
				(lid2 cel: (if register 3 else 2) stopUpd: show:)
				(gEgo normalize: 831 setScale: Scaler 131 30 190 21)
				(gEgo setHeading: 0)
				(= seconds 3)
			)
			(2
				(if (!= global123 5)
					(gGame handsOn:)
					;(gIconBar disable: 7)
				)
				(self dispose:)
			)
		)
	)
)

(instance sORileyComes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(oriley
					init:
					setScale: Scaler 131 30 190 21
					setCycle: Walk
					setMotion: PolyPath 300 172 self
				)
			)
			(1 (= seconds 4))
			(2
				(oriley setMotion: PolyPath 340 172 self)
			)
			(3
				((ScriptID 94 1) setReal: (ScriptID 94 1) local2)
				(proc0_3 47)
				(self dispose:)
			)
		)
	)
)

(instance sHide of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gWrapSound number: 17 flags: 1 loop: -1 play:)
				(gGame handsOff:)
				(proc0_3 90)
				(if (proc0_2 39) ((ScriptID 94 1) dispose: delete:))
				(if global115
					(= cycles 1)
				else
					(self setScript: sHandleTheCase self 1)
				)
			)
			(1
				(gEgo setMotion: MoveTo 178 120 self)
			)
			(2
				(gEgo setHeading: 180)
				(= cycles 1)
			)
			(3
				(lid2 hide:)
				(gEgo
					view: 454
					loop: 4
					cel: 0
					setPri: 6
					posn: 184 122
					setScale: Scaler 100 100 190 21
					setCycle: End self
				)
				(nCreak number: 453 play:)
			)
			(4
				(nCreak number: 455 play:)
				(if (proc0_2 39)
					(self setScript: sORileyComes self)
				else
					(= ticks 120)
				)
			)
			(5
				(gEgo setCycle: Beg self)
				(nCreak number: 452 play:)
			)
			(6
				(lid2 show:)
				(gEgo normalize: 426 setScale: Scaler 131 30 190 21)
				(= seconds 3)
			)
			(7
				(if (proc0_2 39)
					(gLb2Messager say: 16 0 0 0 self)
					(gGameMusic2 number: 444 flags: 1 loop: 1 play:)
				else
					(= cycles 1)
				)
				(gEgo show:)
				(gEgo
					setMotion: MoveTo (gEgo x?) (+ (gEgo y?) 10) self
				)
			)
			(8
				(= global115 0)
				(gGame handsOn:)
				;(gIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sGetAnkh of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(gEgo
					view: 451
					loop: 4
					cel: 0
					setScale: Scaler 100 100 190 21
					setCycle: CT 5 1 self
				)
			)
			(1
				(medallion dispose:)
				(gEgo setCycle: End self)
				(gEgo get: 20)
			)
			(2
				(gEgo normalize: 831 setScale: Scaler 131 30 190 21)
				((ScriptID 21 0) doit: 789)
				(gGame handsOn:)
				;(gIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sFinishIt of Script
	(properties)
	
	(method (doit)
		(if (and local1 local0)
			(Palette palSET_INTENSITY 0 255 (-- local0))
			(if (not local0) (self cue:))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(gWrapSound number: 450 flags: 1 loop: -1 play:)
				(= cycles 2)
			)
			(1
				(oriley view: 818 posn: 330 160 init:)
				(= cycles 1)
			)
			(2
				(oriley
					setCycle: StopWalk -1
					setMotion: PChase gEgo 22 self
				)
			)
			(3 (= cycles 5))
			(4
				(lid1 init: stopUpd: cel: 1)
				(gEgo
					normalize: 831
					setScale: Scaler 131 30 190 21
					setMotion: PolyPath (gEgo x?) (+ (gEgo y?) 10)
				)
				(proc0_5 gEgo oriley)
				(= cycles 5)
			)
			(5
				((ScriptID 22 0) doit: -32480)
				(= seconds 10)
			)
			(6
				(gLb2Messager say: 13 0 0 0 self)
			)
			(7
				(= local1 1)
				(gIconBar disable: 7) ;This was present in the floppy version, so leave it active
				(gWrapSound number: 454 flags: 1 loop: 1 play: self)
			)
			(8 0)
			(9 (global2 newRoom: 26))
		)
	)
)

(instance sUhOh of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(lid1 hide:)
				(gEgo
					view: 454
					setScale: Scaler 100 100 10 1
					setLoop: 2
					cel: 0
					posn: 248 121
					cycleSpeed: 6
					setCycle: 0
				)
				(= cycles 1)
			)
			(1
				(gGameMusic2 number: 451 flags: 1 loop: 1 play: self)
			)
			(2
				(gEgo setCycle: End self)
				(nCreak number: 452 play: self)
			)
			(3 0)
			(4
				(lid1 cel: 1 stopUpd: show:)
				(global2 newRoom: 455)
			)
		)
	)
)

(instance oriley of Actor
	(properties
		x 340
		y 172
		view 423
	)
)

(instance secretDoor1 of Actor
	(properties
		x 211
		y 121
		noun 5
		approachX 213
		approachY 130
		view 454
		loop 6
		signal $4800
	)
)

(instance secretDoor2 of Actor
	(properties
		x 212
		y 121
		noun 5
		approachX 213
		approachY 130
		view 454
		loop 6
		cel 1
		signal $4800
	)
)

(instance lid1 of Prop
	(properties
		x 248
		y 121
		noun 6
		view 454
		loop 5
		signal $4000
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(4
				(if (and (== global123 2) (proc0_10 -32480 1))
					(global2 setScript: sUhOh)
				else
					(global2 setScript: sOpenRedCoffin)
				)
			)
			(else 
				(super doVerb: theVerb param2 &rest)
			)
		)
	)
)

(instance lid2 of Prop
	(properties
		x 184
		y 122
		noun 4
		view 454
		loop 5
		cel 2
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					(global115 (global2 setScript: sHandleTheCase 0 0))
					((and (== global123 5) (not (proc0_2 90))) (global2 setScript: sHide))
					((== global123 5) (gLb2Messager say: 15 4 1))
					(else (global2 setScript: sHandleTheCase 0 1))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance deadPippin of View
	(properties
		x 244
		y 118
		view 454
		loop 1
		signal $4000
	)
)

(instance footprint of View
	(properties
		x 228
		y 128
		approachX 214
		approachY 126
		view 451
		loop 3
		signal $4000
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(8
				(global2 setInset: inFootPrint)
			)
			(else 
				(super doVerb: theVerb param2 &rest)
			)
		)
	)
)

(instance medallion of View
	(properties
		x 227
		y 122
		noun 1
		approachX 214
		approachY 126
		view 451
		loop 3
		cel 1
		signal $4000
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(8
				(gGame points: 1 136)
				(global2 setInset: inAnkh)
			)
			(4
				(global2 setScript: sGetAnkh)
			)
			(else 
				(super doVerb: theVerb param2 &rest)
			)
		)
	)
)

(instance blood of View
	(properties
		x 235
		y 120
		noun 2
		approachX 214
		approachY 126
		view 451
		priority 3
		signal $4010
	)
)

(instance poster of Feature
	(properties
		x 36
		y 81
		noun 8
		nsTop 72
		nsLeft 30
		nsBottom 90
		nsRight 42
		sightAngle 40
		approachX 60
		approachY 139
	)
)

(instance post of Feature
	(properties
		x 135
		y 189
		noun 7
		nsTop 15
		nsLeft 115
		nsBottom 189
		nsRight 155
		sightAngle 40
		approachX 181
		approachY 169
	)
)

(instance rosetta of Feature
	(properties
		x 76
		y 73
		noun 12
		sightAngle 40
		approachX 96
		approachY 135
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(8
				(if (== global123 5)
					(gLb2Messager say: 17)
				else
					(gGame points: 1 137)
					((ScriptID 21 0) doit: 1025)
					(= temp0 1)
					(while (< temp0 14)
						((ScriptID 21 0) doit: (+ temp0 1088))
						(++ temp0)
					)
					(global2 newRoom: 456)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance mummy1 of Feature
	(properties
		x 176
		y 87
		noun 4
		sightAngle 40
		approachX 181
		approachY 127
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((and (== global123 5) (not (proc0_2 90))) (global2 setScript: sHide))
					((== global123 5) (gLb2Messager say: 15 4 1))
					(else (super doVerb: theVerb &rest))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance mummy3 of Feature
	(properties
		x 245
		y 89
		noun 6
		sightAngle 40
		approachX 249
		approachY 129
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(4
				(if (and (== global123 2) (proc0_10 -32480 1))
					(global2 setScript: sUhOh)
				else
					(super doVerb: theVerb param2 &rest)
				)
			)
			(else 
				(super doVerb: theVerb param2 &rest)
			)
		)
	)
)

(instance pyramid of Feature
	(properties
		x 58
		y 178
		noun 9
	)
)

(instance pyramid2 of Feature
	(properties
		x 58
		y 178
		noun 10
	)
)

(instance inAnkh of Inset
	(properties
		view 451
		loop 2
		x 202
		y 101
		disposeNotOnMe 1
		noun 1
	)
	
	(method (init)
		(super init: &rest)
		(gNarrator x: 18 y: 120 talkWidth: 155)
	)
	
	(method (dispose)
		(gNarrator x: -1 y: -1)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(self dispose:)
				(global2 setScript: sGetAnkh)
			)
			(2
				(gLb2Messager say: 0 2 0 1 0 0 0)
				1
			)
			(6
				(gLb2Messager say: 0 6 0 2 0 0 0)
				1
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance inFootPrint of Inset
	(properties
		view 451
		loop 1
		x 204
		y 112
		disposeNotOnMe 1
		noun 3
	)
	
	(method (init)
		(super init: &rest)
		(gNarrator x: 18 y: 120 talkWidth: 155)
	)
	
	(method (dispose)
		(gNarrator x: -1 y: -1)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(gLb2Messager say: 0 2 0 1 0 0 0)
				1
			)
			(6
				(gLb2Messager say: 0 6 0 2 0 0 0)
				1
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance eastExitFeature of ExitFeature
	(properties
		nsTop 118
		nsLeft 314
		nsBottom 189
		nsRight 319
		cursor 14
		exitDir 2
		noun 14
	)
)

(instance nCreak of Sound
	(properties
		flags $0001
	)
)

(instance thudSound of Sound
	(properties
		flags $0001
		number 80
	)
)
