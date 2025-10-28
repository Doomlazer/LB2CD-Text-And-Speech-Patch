;;; Sierra Script 1.0 - (do not remove this comment)
(script# 420)
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
(use Timer)
(use Sound)
(use Cycle)
(use View)
(use Obj)

(public
	rm420 0
)

(local
	local0
	theGLb2DoVerbCode
)
(instance rm420 of LBRoom
	(properties
		picture 420
		north 350
		east 430
		west 500
		vanishingX 0
	)
	
	(method (init)
		(gGame handsOff:)
		(proc958_0 128 420 423 424 426 831)
		(if
			(or
				(> global123 3)
				(and (== global123 3) (proc0_10 8512 1))
			)
			(proc958_0 129 556 429)
			(proc958_0 132 3 80 6 84)
		)
		(Load rsSOUND 19)
		(gEgo
			init:
			normalize: (if (== global123 5) 426 else 831)
			setScale: Scaler 100 0 190 0
		)
		(if (== global123 5)
			(gGame handsOn:)
			(gWrapSound number: 16 flags: 1 setLoop: -1 play:)
			(self setRegions: 94)
			(global2 obstacles: (List new:))
			((ScriptID 2420 0) doit: (global2 obstacles?))
			(oriley init: setScale: 165 setCycle: Walk)
		else
			(self setRegions: 90)
		)
		(switch gGNumber
			(north
				(self setScript: sEnterNorth)
			)
			(east
				(if
					(and
						(or
							(> global123 3)
							(and (== global123 3) (proc0_10 8512 1))
						)
						(not (proc0_2 67))
					)
					(self setScript: sLookitDeadErnie 0 1)
				else
					(gGame handsOn:)
				)
				(gEgo y: 187)
			)
			(west
				(self setScript: sEnterWest)
			)
			(26
				(gEgo posn: 264 164)
				(gEgo setHeading: 180)
			)
			(else 
				(gEgo posn: 185 160)
				(gGame handsOn:)
			)
		)
		(super init:)
		(= local0
			(cond 
				((< global87 5) 85)
				((< global87 10) 60)
				((<= global87 15) 35)
			)
		)
		(if (not (HaveMouse)) (= local0 (* 2 local0)))
		(if (and (== global123 5) (!= script sCopyFail))
			(oRileyTimer setTicks: 300 oRileyTimer)
		)
		(if
			(or
				(> global123 3)
				(and (== global123 3) (proc0_10 8512 1))
			)
			(ernie addToPic: approachVerbs: 4 1 8)
		)
		(southExitFeature init:)
		(wall init: setOnMeCheck: 1 512)
		(floor init: setOnMeCheck: 1 1024)
		(tuskSupport init: setOnMeCheck: 1 128)
		(dinoSkeleton init: setOnMeCheck: 1 64)
		(skull init: setOnMeCheck: 1 8)
		(leftPic init: setOnMeCheck: 1 32)
		(rightPic init: setOnMeCheck: 1 16)
		(mastodon init: setOnMeCheck: 1 256 4)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((proc0_1 gEgo 2) (self setScript: sExitNorth))
			((proc0_1 gEgo 4)
				(gGame handsOff:)
				(gEgo edgeHit: 4)
				(global2 newRoom: (global2 west?))
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(switch global123
			(2
				(if (== newRoomNumber 350)
					(if (gGameMusic2 handle?)
						(gGameMusic2 stop:)
						(gWrapSound pause: 0)
					)
					(gWrapSound fade: 127 5 5 0)
				)
			)
			(3
				(if
					(and
						(== newRoomNumber 430)
						(proc0_10 -20222 1)
						(not (proc0_2 72))
					)
					(= newRoomNumber 435)
				)
			)
			(5
				; BUGFIX: oRileyTimer is started in act 5, but Sierra forgot to dispose it. Doing it prevents
				; the rest of timers from breaking if ego leaves the room before it expires. Reference:
				; https://github.com/scummvm/scummvm/blob/85702e06764f95a6b700e348dd90931613efdc29/engines/sci/engine/script_patches.cpp#L11505
				(oRileyTimer dispose: delete:)
				; COMPAT: changing "local0" to "local0 0" retains the functionality of this pursuitTimer call
				; and breaks ScummVM's detection signature. Otherwise it would patch timers:dispose to fix the
				; oRileyTimer issue, and we've already disposed oRileyTimer above.
				((ScriptID 94 1) setReal: (ScriptID 94 1) local0 0) ; pursuitTimer
				(DisposeScript 2420)
			)
		)
		(super newRoom: newRoomNumber)
	)
	
	(method (notify)
		(if (== global123 5)
			(if
				(and
					(global2 script?)
					(not (== (global2 script?) sDie))
				)
				((global2 script?) next: sDie)
			else
				(global2 setScript: sDie)
			)
		)
	)
)

(instance oriley of Actor
	(properties
		y 155
		view 423
	)
	
	(method (cue)
		(if (not (global2 script?))
			(gGame handsOff:)
			(global2 setScript: sDie)
		)
	)
)

(instance ernie of View
	(properties
		x 175
		y 189
		z 71
		noun 9
		approachX 199
		approachY 180
		view 420
		priority 15
		signal $4010
	)
	
	(method (doVerb theVerb)
		(gGame points: 1 158)
		(switch theVerb
			(1
				(if (== global123 5)
					(gLb2Messager say: 27)
				else
					(gIconBar disable: 7)
					(global2 setScript: sDeadErnie)
				)
			)
			(8 (self doVerb: 1))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance wall of Feature
	(properties
		y 1
		noun 1
		sightAngle 40
	)
)

(instance floor of Feature
	(properties
		y 1
		noun 2
		sightAngle 40
	)
)

(instance tuskSupport of Feature
	(properties
		y 1
		noun 3
		sightAngle 40
	)
)

(instance dinoSkeleton of Feature
	(properties
		y 1
		noun 4
		sightAngle 40
	)
)

(instance skull of Feature
	(properties
		y 1
		noun 5
		sightAngle 40
	)
)

(instance leftPic of Feature
	(properties
		y 1
		noun 6
		sightAngle 40
	)
)

(instance rightPic of Feature
	(properties
		y 1
		noun 7
		sightAngle 40
	)
)

(instance mastodon of Feature
	(properties
		y 1
		noun 8
		sightAngle 40
	)
)

(instance inDeadErnie of Inset
	(properties
		picture 429
		hideTheCast 1
	)
	
	(method (init)
		(super init: &rest)
		(= theGLb2DoVerbCode gLb2DoVerbCode)
		(= gLb2DoVerbCode exitDoVerbCode)
		(proc0_8 1)
		(gLb2WH addToFront: self)
		(feSuspenders init: setOnMeCheck: 1 16384)
		(feShirt init: setOnMeCheck: 1 8192)
		(feTusks init: setOnMeCheck: 1 4096)
		(feRtArm init: setOnMeCheck: 1 2048)
		(feLtArm init: setOnMeCheck: 1 1024)
		(feSupport init: setOnMeCheck: 1 512)
		(feShoe init: setOnMeCheck: 1 256)
		(fePants init: setOnMeCheck: 1 128)
		(feEye init: setOnMeCheck: 1 64)
		(feSkull init: setOnMeCheck: 1 32)
		(feEar init: setOnMeCheck: 1 16)
		(feNeck init: setOnMeCheck: 1 8)
		(feMouth init: setOnMeCheck: 1 4)
		(feHead init: setOnMeCheck: 1 2)
	)
	
	(method (dispose)
		(= gLb2DoVerbCode theGLb2DoVerbCode)
		(gLb2WH delete: self)
		(gIconBar enable: 7)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13 (inDeadErnie dispose:))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance exitDoVerbCode of Code
	(properties)
	
	(method (doit param1 param2)
		(if (== param1 13)
			(inDeadErnie dispose:)
		else
			(proc0_6 param2 param1)
		)
	)
)

(instance feSuspenders of Feature
	(properties
		y 1
		noun 11
		sightAngle 40
	)
)

(instance feShirt of Feature
	(properties
		y 1
		noun 12
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(if (not (gEgo has: 25))
					(inDeadErnie setInset: inWartHairs)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance feTusks of Feature
	(properties
		y 1
		noun 13
		sightAngle 40
	)
)

(instance feRtArm of Feature
	(properties
		y 1
		noun 14
		sightAngle 40
	)
)

(instance feLtArm of Feature
	(properties
		y 1
		noun 15
		sightAngle 40
	)
)

(instance feSupport of Feature
	(properties
		y 1
		noun 16
		sightAngle 40
	)
)

(instance feShoe of Feature
	(properties
		y 1
		noun 17
		sightAngle 40
	)
)

(instance fePants of Feature
	(properties
		y 1
		noun 18
		sightAngle 40
	)
)

(instance feEye of Feature
	(properties
		y 1
		noun 19
		sightAngle 40
	)
)

(instance feSkull of Feature
	(properties
		y 1
		noun 20
		sightAngle 40
	)
)

(instance feEar of Feature
	(properties
		y 1
		noun 21
		sightAngle 40
	)
)

(instance feNeck of Feature
	(properties
		y 1
		noun 22
		sightAngle 40
	)
)

(instance feMouth of Feature
	(properties
		y 1
		noun 23
		sightAngle 40
	)
)

(instance feHead of Feature
	(properties
		y 1
		noun 24
		sightAngle 40
	)
)

(instance inWartHairs of Inset
	(properties
		view 420
		loop 1
		x 126
		y 57
		disposeNotOnMe 1
		noun 10
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(gGame points: 1 159)
					(gEgo get: 25)
					((ScriptID 21 0) doit: 794)
					(self dispose:)
					(return 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance sExitNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(gEgo setHeading: 0 setMotion: MoveFwd 20 self)
			)
			(1
				(global2 newRoom: (global2 north?))
			)
		)
	)
)

(instance sLookitDeadErnie of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if register
					(gEgo setMotion: PolyPath 280 185 self)
				else
					(= cycles 1)
				)
			)
			(1
				(proc0_5 gEgo ernie)
				(= ticks 120)
			)
			(2
				(ernie doVerb: 1)
				(if register (gGame handsOn:))
				(self dispose:)
			)
		)
	)
)

(instance sEnterNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gEgo
					posn: 300 140
					setHeading: 180
					setMotion: MoveFwd 20 self
				)
				(if
				(and (== global123 2) (not (proc0_10 -32480 1)))
					((ScriptID 32 0)
						init:
						view: 814
						room: gNumber
						posn: 154 179
						setLoop: 8
						setCel: 6
						noun: 1
					)
					(gWrapSound pause:)
					(gGameMusic2 number: 19 flags: 1 setLoop: 1 play:)
				)
			)
			(1
				(cond 
					(
					(and (== global123 2) (not (proc0_10 -32480 1))) (global2 setScript: sHeimlichShoos))
					(
						(and
							(or
								(> global123 3)
								(and (== global123 3) (proc0_10 8512 1))
							)
							(not (proc0_2 67))
						)
						(self setScript: sLookitDeadErnie self)
					)
					(else (= cycles 1))
				)
			)
			(2
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gEgo
					posn: 2 150
					setHeading: 135
					setMotion: MoveFwd 20 self
				)
			)
			(1
				(if
					(and
						(or
							(> global123 3)
							(and (== global123 3) (proc0_10 8512 1))
						)
						(not (proc0_2 67))
					)
					(self setScript: sLookitDeadErnie self)
				else
					(= cycles 1)
				)
			)
			(2
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDeadErnie of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(WrapMusic pause:)
				(if (proc0_2 67)
					(fooSound number: 6 loop: -1 flags: 1 play:)
					(= cycles 1)
				else
					(proc0_3 67)
					(gOldCast eachElementDo: #hide)
					(DrawPic 556)
					(gOldATPs eachElementDo: #dispose release:)
					(wrapMusic init: -1 3 6)
					(sFX number: 84 flags: 1 loop: 1 play:)
					(= ticks 180)
				)
			)
			(1
				(global2 setInset: inDeadErnie self)
			)
			(2
				(gOldCast eachElementDo: #show)
				(ernie addToPic:)
				(= cycles 2)
			)
			(3
				(fooSound fade: 0 12 30 1)
				(if (gSounds contains: (wrapMusic wrapSound?))
					(wrapMusic dispose: 1)
				)
				(WrapMusic pause: 0)
				(if (and (== global123 3) (not (proc0_10 8512)))
					((ScriptID 22 0) doit: 8512)
				)
				(proc0_8 0)
				(self dispose:)
			)
		)
	)
)

(instance sHeimlichShoos of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= ticks 60)
			)
			(1
				(gLb2Messager say: 3 0 83 0 self 1889)
			)
			(2
				(gEgo setMotion: PolyPath 300 142 self)
			)
			(3
				(client setScript: sExitNorth)
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
				(oriley view: 424)
				(proc0_5 gEgo oriley)
				(= cycles 1)
			)
			(1
				(gWrapSound number: 3 flags: 1 loop: 1 play:)
				(= cycles 4)
			)
			(2
				(oriley cel: 0 setCycle: End self)
			)
			(3
				(sFX number: 80 flags: 1 loop: 1 play:)
				(gEgo
					view: 858
					setScale: Scaler 100 100 190 0
					posn: (+ (gEgo x?) 13) (gEgo y?)
					setCycle: End self
				)
			)
			(4 (= seconds 4))
			(5
				(= global145 0)
				(global2 newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance sCopyFail of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 2)
			)
			(1
				(gLb2Messager say: 26 0 0 0 self)
			)
			(2 (= seconds 5))
			(3
				(= global145 3)
				(global2 newRoom: 99)
			)
		)
	)
)

(instance southExitFeature of ExitFeature
	(properties
		nsTop 166
		nsLeft 314
		nsBottom 189
		nsRight 319
		cursor 14
		exitDir 2
		noun 25
	)
)

(instance oRileyTimer of Timer
	(properties)
	
	(method (cue)
		(oriley setMotion: PChase gEgo 20 oriley)
	)
)

(instance wrapMusic of WrapMusic
	(properties)
	
	(method (init)
		(= wrapSound fooSound)
		(super init: &rest)
	)
)

(instance fooSound of Sound
	(properties)
)

(instance sFX of Sound
	(properties
		flags $0001
	)
)
