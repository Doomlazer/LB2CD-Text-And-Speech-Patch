;;; Sierra Script 1.0 - (do not remove this comment)
(script# 490)
(include sci.sh)
(use Main)
(use LBRoom)
(use ExitFeature)
(use Inset)
(use PChase)
(use Scaler)
(use PolyPath)
(use Polygon)
(use CueObj)
(use MoveFwd)
(use n958)
(use StopWalk)
(use Sound)
(use Cycle)
(use View)
(use Obj)

(public
	rm490 0
)

(local
	local0
	local1
)
(instance rm490 of LBRoom
	(properties
		noun 5
		picture 490
		east 440
		south 440
		west 440
		vanishingX 150
		vanishingY 100
	)
	
	(method (init)
		(proc958_0 128 491 424 423 818 426 831)
		(proc958_0 132 490 1)
		(gEgo
			init:
			normalize: (if (== global123 5) 426 else 831)
			setScale: Scaler 160 40 190 100
		)
		(if (== global123 5)
			(self setRegions: 94)
			(global2 obstacles: (List new:))
			((ScriptID 2490 0) doit: (global2 obstacles?))
		else
			(self setRegions: 90)
		)
		(switch gGNumber
			(south
				(self setScript: sEnterSouth)
			)
			(else 
				(gEgo posn: 160 160)
				(gGame handsOn:)
			)
		)
		(super init:)
		(if (proc0_2 143)
			(if (proc0_2 37)
				(zHead
					loop: 2
					cel: 1
					x: 231
					y: 133
					approachVerbs: 4 1 8
					init:
					setPri: 10
					approachX: 213
					approachY: 135
				)
				(self
					addObstacle:
						(= local0
							((Polygon new:)
								type: 2
								init: 252 133 236 143 201 132 227 130
								yourself:
							)
						)
				)
			else
				(zHead approachVerbs: 4 1 8 init:)
			)
		)
		(southExitFeature init:)
		(westExitFeature init:)
		(eastExitFeature init:)
		(genericHead init:)
		(floor init: setOnMeCheck: 1 16384 2)
		(if (== global123 5)
			(oriley
				init:
				setCycle: Walk
				setScale: Scaler 160 40 190 80
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((proc0_1 gEgo 2) (global2 setScript: sExitSouth))
		)
	)
	
	(method (dispose)
		(DisposeScript 2490)
		(super dispose:)
	)
	
	(method (newRoom)
		(if (IsObject local0)
			((global2 obstacles?) delete: local0)
			(local0 dispose:)
		)
		(super newRoom: &rest)
	)
	
	(method (notify)
		(if (== global123 5)
			(if (global2 script?)
				((global2 script?) next: sKillHer)
			else
				(global2 setScript: sKillHer)
			)
		)
	)
)

(instance sEnterSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(gEgo
					posn: 320 190
					setHeading: 315
					setMotion: MoveFwd 40 self
				)
			)
			(1
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(cond 
					((> (gEgo x?) 310) (gEgo setHeading: 90))
					((< (gEgo x?) 20) (gEgo setHeading: 270))
					(else (gEgo setHeading: 180))
				)
				(gEgo setMotion: MoveFwd 100 self)
			)
			(2 (global2 newRoom: 440))
		)
	)
)

(instance sOhNoMurder of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 2)
			)
			(1
				(if (not (proc0_10 -20222))
					((ScriptID 22 0) doit: -20222 self)
				else
					(= cycles 1)
				)
			)
			(2
				(WrapMusic pause: 1)
				(sWrapMusic init: -1 1 6)
				(= cycles 3)
			)
			(3
				(global2 setInset: inHead)
				(= seconds 3)
			)
			(4
				(inHead setInset: inReaction)
				(noise number: 82 flags: 1 loop: 1 play: self)
				(= local1 1)
			)
			(5
				(inReaction dispose:)
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sKillHer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(gEgo setMotion: PolyPath 186 150 self)
			)
			(2
				(gWrapSound number: 3 loop: 1 flags: 1 play:)
				(oriley
					moveSpeed: 4
					cycleSpeed: 4
					setMotion: PChase gEgo 25 self
				)
			)
			(3
				(proc0_5 gEgo oriley)
				(oriley view: 424 cel: 0 setCycle: End self)
			)
			(4
				(noise number: 80 flags: 1 loop: 1 play:)
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

(instance sGetHead of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(global2
					addObstacle:
						(= local0
							((Polygon new:)
								type: 2
								init: 252 133 236 143 201 132 227 130
								yourself:
							)
						)
				)
				(zHead loop: 1 setCycle: End self)
			)
			(2
				(zHead setLoop: 2 cel: 2 posn: 211 131 setCycle: End self)
				(noise number: 490 flags: 1 play:)
			)
			(3
				(zHead setCycle: End self setMotion: MoveTo 231 133)
			)
			(4
				(zHead setCycle: CT 1 1 self)
			)
			(5
				(proc0_3 37)
				(zHead
					approachX: 213
					approachY: 135
					setCycle: 0
					setPri: 10
				)
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sTheyComeIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(sWrapMusic dispose: 1)
				(= cycles 1)
			)
			(1
				(localSound dispose:)
				((ScriptID 32 0)
					room: 490
					init:
					view: 814
					x: 253
					y: 240
					setCycle: Walk
					setMotion: MoveTo 249 145 self
				)
				(= register ((ScriptID 90 3) room?))
				((ScriptID 90 3)
					moveTo: 490
					view: 818
					x: 280
					y: 240
					setMotion: MoveTo 290 141 self
				)
				(gGameMusic2 number: 350 flags: 1 loop: -1 play:)
			)
			(2 0)
			(3
				((ScriptID 32 0) setCycle: StopWalk -1)
				((ScriptID 90 3) loop: 7)
				(= cycles 1)
			)
			(4
				(proc0_5 gEgo (ScriptID 90 3))
				(= cycles 5)
			)
			(5
				(gLb2Messager say: 1 0 1 0 self 1490)
			)
			(6
				((ScriptID 32 0)
					setCycle: Walk
					setMotion: PolyPath 275 300 self
				)
				((ScriptID 90 3) setMotion: PolyPath 302 300 self)
			)
			(7
				0
				(gGameMusic2 fade:)
				(WrapMusic pause: 0)
				((ScriptID 90 3) moveTo: register)
				(gGame handsOn:)
				(gGame points: 1 144)
				(proc0_3 36)
				(self dispose:)
			)
		)
	)
)

(instance sExamineHead of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(WrapMusic pause: 1)
				(if (not (== global123 5)) (mExamineMusic play:))
				(global2 setInset: inHead self)
			)
			(1
				(mExamineMusic dispose:)
				(WrapMusic pause: 0)
				(self dispose:)
			)
		)
	)
)

(instance oriley of Actor
	(properties
		x 150
		y 300
		view 423
	)
)

(instance zHead of Actor
	(properties
		x 190
		y 87
		noun 1
		approachX 189
		approachY 135
		view 491
		loop 1
		signal $4001
	)
	
	(method (doVerb theVerb)
		(if (== global123 5)
			(gLb2Messager say: 36 0 0 0)
		else
			(switch theVerb
				(1
					(if (proc0_2 36)
						(global2 setScript: sExamineHead)
					else
						(global2 setScript: sOhNoMurder)
					)
				)
				(8 (self doVerb: 1))
				(4
					(cond 
						((proc0_2 37) (gLb2Messager say: 1 4 1))
						((not (proc0_2 36)) (gLb2Messager say: 1 4 2))
						(else (global2 setScript: sGetHead))
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance floor of Feature
	(properties)
)

(instance inHead of Inset
	(properties
		picture 495
		hideTheCast 1
		disposeNotOnMe 1
		noun 6
	)
	
	(method (init)
		(gEgo hide:)
		(zHead hide:)
		(super init: &rest)
		(gGame points: 1 144)
		(proc0_8 1)
	)
	
	(method (dispose)
		(gEgo show:)
		(zHead show:)
		(proc0_8 0)
		(if (not (proc0_2 36))
			(global2 setScript: sTheyComeIn)
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(proc0_8 0)
				(inHead dispose:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance inReaction of Inset
	(properties
		picture 555
		hideTheCast 1
		disposeNotOnMe 1
	)
)

(instance southExitFeature of ExitFeature
	(properties
		nsTop 184
		nsBottom 189
		nsRight 319
		cursor 11
		exitDir 3
		noun 35
	)
)

(instance eastExitFeature of ExitFeature
	(properties
		nsTop 133
		nsLeft 314
		nsBottom 189
		nsRight 319
		cursor 14
		exitDir 2
		noun 35
	)
)

(instance westExitFeature of ExitFeature
	(properties
		nsTop 147
		nsBottom 189
		nsRight 5
		cursor 12
		exitDir 3
		noun 35
	)
)

(instance genericHead of Feature
	(properties
		y 5
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (gLb2Messager say: 13 4))
			(8 (gLb2Messager say: 13 8))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe param1)
		(if (super onMe: param1)
			; BUGFIX + TWEAK: Correct the areas of every head and make ego properly
			; turn towards them.
			;
			; genericHead uses the "generic" black control color, and it's the
			; instance used for the 27 the heads in the room. Its onMe method
			; compares gX and gY with hardcoded areas to determine what head was
			; clicked on and display different messages. These areas have a wrong
			; value by 10 pixels on the y axis. This issue is just like the one we
			; fixed and explained in genericArmor:onMe, in script #440. The current
			; case is worse since the heads are really small.
			;
			; Also, since genericHead is just one instance, its x property is always
			; the same regardless of the head the player clicks on. This makes Laura
			; face towards x 0 y 5 even if the actual clicked head is far away from
			; there.
			;
			; We move the heads' areas 10 pixels down on the y axis to make them
			; accurate. We also make genericHead's x property dynamic, changing
			; whenever the player clicks on the black control color. This way Laura
			; will face towards every head in a more convincing manner.
			(= x gX) ; dynamic x
			(cond 
;;;				((and (<= 59 gX 74) (<= 68 gY 82)) (= noun 2))
				((and (<= 59 gX 74) (<= 78 gY 92)) (= noun 2))
;;;				((and (<= 39 gX 56) (<= 47 gY 65)) (= noun 9))
				((and (<= 39 gX 56) (<= 57 gY 75)) (= noun 9))
;;;				((and (<= 136 gX 147) (<= 87 gY 99)) (= noun 19))
				((and (<= 136 gX 147) (<= 97 gY 109)) (= noun 19))
;;;				((and (<= 152 gX 163) (<= 91 gY 101)) (= noun 20))
				((and (<= 152 gX 163) (<= 101 gY 111)) (= noun 20))
;;;				((and (<= 63 gX 80) (<= 108 gY 125)) (= noun 14))
				((and (<= 63 gX 80) (<= 118 gY 135)) (= noun 14))
;;;				((and (<= 276 gX 294) (<= 74 gY 91)) (= noun 31))
				((and (<= 276 gX 294) (<= 84 gY 101)) (= noun 31))
;;;				((and (<= 182 gX 195) (<= 112 gY 124)) (= noun 27))
				((and (<= 182 gX 195) (<= 122 gY 134)) (= noun 27))
;;;				((and (<= 146 gX 152) (<= 101 gY 113)) (= noun 3))
				((and (<= 146 gX 152) (<= 111 gY 123)) (= noun 3))
;;;				((and (<= 88 gX 102) (<= 107 gY 122)) (= noun 15))
				((and (<= 88 gX 102) (<= 117 gY 132)) (= noun 15))
;;;				((and (<= 133 gX 146) (<= 68 gY 84)) (= noun 4))
				((and (<= 133 gX 146) (<= 78 gY 94)) (= noun 4))
;;;				((and (<= 74 gX 91) (<= 61 gY 76)) (= noun 16))
				((and (<= 74 gX 91) (<= 71 gY 86)) (= noun 16))
;;;				((and (<= 139 gX 150) (<= 113 gY 125)) (= noun 10))
				((and (<= 139 gX 150) (<= 123 gY 135)) (= noun 10))
;;;				((and (<= 177 gX 184) (<= 85 gY 96)) (= noun 26))
				((and (<= 177 gX 184) (<= 95 gY 106)) (= noun 26))
;;;				((and (<= 243 gX 262) (<= 75 gY 93)) (= noun 30))
				((and (<= 243 gX 262) (<= 85 gY 103)) (= noun 30))
;;;				((and (<= 89 gX 101) (<= 53 gY 66)) (= noun 17))
				((and (<= 89 gX 101) (<= 63 gY 76)) (= noun 17))
;;;				((and (<= 165 gX 176) (<= 93 gY 103)) (= noun 23))
				((and (<= 165 gX 176) (<= 103 gY 113)) (= noun 23))
;;;				((and (<= 185 gX 195) (<= 89 gY 99)) (= noun 25))
				((and (<= 185 gX 195) (<= 99 gY 109)) (= noun 25))
;;;				((and (<= 279 gX 294) (<= 102 gY 119)) (= noun 24))
				((and (<= 279 gX 294) (<= 112 gY 129)) (= noun 24))
;;;				((and (<= 153 gX 163) (<= 104 gY 114)) (= noun 18))
				((and (<= 153 gX 163) (<= 114 gY 124)) (= noun 18))
;;;				((and (<= 312 gX 319) (<= 73 gY 88)) (= noun 29))
				((and (<= 312 gX 319) (<= 83 gY 98)) (= noun 29))
;;;				((and (<= 68 gX 88) (<= 88 gY 104)) (= noun 12))
				((and (<= 68 gX 88) (<= 98 gY 114)) (= noun 12))
;;;				((and (<= 245 gX 261) (<= 101 gY 117)) (= noun 28))
				((and (<= 245 gX 261) (<= 111 gY 127)) (= noun 28))
;;;				((and (<= 148 gX 156) (<= 78 gY 89)) (= noun 21))
				((and (<= 148 gX 156) (<= 88 gY 99)) (= noun 21))
;;;				((and (<= 159 gX 169) (<= 81 gY 93)) (= noun 22))
				((and (<= 159 gX 169) (<= 91 gY 103)) (= noun 22))
;;;				((and (<= 134 gX 145) (<= 99 gY 112)) (= noun 11))
				((and (<= 134 gX 145) (<= 109 gY 122)) (= noun 11))
;;;				((and (<= 313 gX 319) (<= 103 gY 118)) (= noun 33))
				((and (<= 313 gX 319) (<= 113 gY 128)) (= noun 33))
;;;				((and (<= 178 gX 188) (<= 97 gY 109)) (= noun 32))
				((and (<= 178 gX 188) (<= 107 gY 119)) (= noun 32))
			)
			; END OF BUGFIX + TWEAK (see also genericArmor:onMe, in #440)
		)
	)
)

(instance noise of Sound
	(properties
		flags $0001
	)
)

(instance mExamineMusic of Sound
	(properties
		flags $0001
		number 6
		loop -1
	)
)

(instance sWrapMusic of WrapMusic
	(properties)
	
	(method (init)
		(= wrapSound localSound)
		(super init: &rest)
	)
)

(instance localSound of Sound
	(properties)
)
