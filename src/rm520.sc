;;; Sierra Script 1.0 - (do not remove this comment)
(script# 520)
(include sci.sh)
(use Main)
(use LBRoom)
(use ExitFeature)
(use MuseumRgn)
(use Inset)
(use Scaler)
(use PolyPath)
(use CueObj)
(use n958)
(use StopWalk)
(use DPath)
(use Timer)
(use Sound)
(use Cycle)
(use InvI)
(use User)
(use View)
(use Obj)

(public
	rm520 0
	cobraLoose 1
)

(local
	local0
	local1
	local2
	[local3 2] = [0 1]
	theSCobraLoose
	local6
	local7
	local8 =  1
	local9 =  1
	local10
	local11
	local12 =  100
	local13
)
(procedure (localproc_2000 param1 &tmp temp0 temp1 temp2 temp3 temp4 [temp5 200])
	(if (== param1 522)
		(Message msgGET 520 60 0 10 1 @temp5)
	else
		(Message msgGET 520 60 0 11 1 @temp5)
	)
	(= local0 0)
	(= local1 0)
	(= temp2 (StrLen @temp5))
	(= temp4 21)
	(= temp3 0)
	(while (< temp3 (+ (/ temp2 2) (mod temp2 2)))
		(= temp0 (& [temp5 temp3] $00ff))
		(= temp1 (>> [temp5 temp3] $0008))
		(localproc_2092 temp0 temp4)
		(if (>= temp1 32) (localproc_2092 temp1 temp4))
		(++ temp3)
	)
)

(procedure (localproc_2092 param1 param2 &tmp temp0 temp1 [temp2 54])
	(= [temp1 0] param1)
	(TextSize @[temp2 0] @temp1 1900 0 0)
	(if
	(> (+ local0 (= temp0 (- [temp2 3] [temp2 1]))) 320)
		(= local0 0)
		(= local1 (+ local1 param2))
	)
	(Display
		@temp1
		dsCOORD
		local0
		local1
		dsFONT
		1900
		dsCOLOR
		7
	)
	(= local0 (+ local0 temp0))
)

(instance rm520 of LBRoom
	(properties
		noun 58
		picture 520
		north 666
		south 510
		vanishingX 0
	)
	
	(method (init)
		(proc958_0 129 521 780)
		(proc958_0 132 520 524 521 522 523 525 441 481 49 721)
		(gEgo init: normalize: 831 setScale: Scaler 120 100 190 0)
		(self setRegions: 90)
		(switch gGNumber
			(south
				(gEgo x: 160)
				(gGame handsOn:)
				;(gIconBar disable: 7)
			)
			(456 (gEgo x: 209 y: 127))
			(else 
				(gEgo posn: 109 125 setHeading: 62)
			)
		)
		(gGame handsOn:)
		;(gIconBar disable: 7)
		(super init:)
		(cond 
			(
				(and
					(or
						(> global123 4)
						(and (== global123 4) (proc0_10 16648 1))
					)
					(!= gGNumber 525)
					(!= gGNumber 456)
					(not (proc0_10 16648))
				)
				(gGameMusic2 number: 521 loop: -1 flags: 1 play:)
			)
			((!= gGNumber 456) (gGameMusic2 number: 520 loop: -1 flags: 1 play:))
		)
		(if
		(and (== gGNumber 525) (not (proc0_10 16648)))
			((Timer new:) setReal: self 2)
		)
		(if
			(or
				(> global123 4)
				(and (== global123 4) (proc0_10 16648 1))
			)
			(if (not (proc0_2 80))
				(cobraDoor init:)
				(= local8 0)
				(= theSCobraLoose sCobraLoose)
			else
				(cobra init: approachVerbs: 4 1 8 stopUpd:)
			)
		else
			(mountedSkull init: approachVerbs: 4 1 8 stopUpd:)
			(cobra init: approachVerbs: 4 1 8 stopUpd:)
		)
		(if (not (proc0_2 49))
			(rosettaCloth init: approachVerbs: 4 1 8 stopUpd:)
		)
		(if
			(and
				(or
					(< global123 4)
					(and (== global123 4) (not (proc0_10 12548)))
				)
				(not (proc0_2 48))
			)
			(snakeOil init: approachVerbs: 4 1 8 stopUpd:)
		)
		(secretDoor setPri: 11 init: stopUpd:)
		(if
			(or
				(> global123 4)
				(and (== global123 4) (proc0_10 16648 1))
			)
			(deadCountess init: stopUpd: approachVerbs: 1 8)
		else
			(intercom init: stopUpd: approachVerbs: 4 1 8)
		)
		(ratBack init: approachVerbs: 4 1 8)
		(ratFore init: approachVerbs: 4 1 8 setOnMeCheck: 1 4)
		(rosetta init: approachVerbs: 4 1 8 setOnMeCheck: 1 256)
		(hieroglyphics
			init:
			approachVerbs: 4 1 8
			setOnMeCheck: 1 2048
		)
		(certificate init:)
		(skeletonLegs init:)
		(cobraCage init:)
		(bookShelf init:)
		(jars init:)
		(chair init:)
		(skeletonFore init: setOnMeCheck: 1 2)
		(desk init: setOnMeCheck: 1 8)
		(windowView init: setOnMeCheck: 1 16)
		(drapes init: setOnMeCheck: 1 32)
		(roachTop init: setOnMeCheck: 1 64)
		(roachBottom init: setOnMeCheck: 1 -32768)
		(displayCase init: setOnMeCheck: 1 128)
		(cages init: setOnMeCheck: 1 512)
		(lizards init: setOnMeCheck: 1 1024)
		(bookcase init: setOnMeCheck: 1 4096)
		(lizardTable init: setOnMeCheck: 1 8192)
		(standRat init: setOnMeCheck: 1 16384)
		(if theSCobraLoose (global2 setScript: theSCobraLoose))
		(southExitFeature init:)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((and local7 (proc0_1 gEgo 16)) (self setScript: sDropCobraGetBitten))
			(
				(and
					(> (gEgo y?) 185)
					(or
						(== (gEgo view?) 522)
						(== (gEgo view?) 528)
						(== (gEgo view?) 529)
					)
				)
				(global2 setScript: sDropCobraGetBitten)
			)
			((> (gEgo y?) 185) (= local7 1))
		)
	)
	
	(method (cue)
		(if local13
			(super newRoom: 26)
		else
			(gGameMusic2 number: 520)
			((ScriptID 22 0) doit: 16648)
		)
	)
	
	(method (newRoom newRoomNumber)
		(cond 
			((== newRoomNumber 456) (super newRoom: newRoomNumber))
			((and (== global123 4) (gEgo has: 31) (== newRoomNumber 510))
				(= newRoomNumber 26)
				(WrapMusic dispose:)
				((ScriptID 22 0) doit: 31)
				(gGameMusic2 number: 524 loop: 1 flags: 1 play: self)
				(= local13 1)
			)
			(else (gGameMusic2 fade:) (super newRoom: newRoomNumber))
		)
		((ScriptID 90 2) setPri: -1)
	)
)

(instance cobra of Actor
	(properties
		x 85
		y 58
		noun 16
		approachX 78
		approachY 122
		view 521
		loop 3
		priority 2
		signal $4010
		cycleSpeed 12
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (global2 setInset: inCobra))
			(8 (global2 setInset: inCobra))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance cobraLoose of Actor
	(properties
		x 77
		y 178
		view 521
		cycleSpeed 12
	)
	
	(method (doit)
		(if
			(and
				(not local7)
				(not (cobraDoor script?))
				(not local8)
			)
			(cond 
				(
					(and
						local11
						(or
							(< (gEgo distanceTo: self) 40)
							(> (gEgo distanceTo: self) local12)
						)
					)
					(cobraDoor setScript: sCobraStrike)
				)
				((and (< (gEgo x?) (self x?)) local9) (= local9 0) (self setScript: sCobraTurn))
				((and (> (gEgo x?) (self x?)) (not local9)) (= local9 1) (self setScript: sCobraTurn))
			)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(25
				(switch local6
					(0
						(if (< (gEgo y?) 152)
							(gEgo setMotion: MoveTo 158 130)
							(= local6 1)
						else
							(gEgo setMotion: MoveTo 160 189)
							(= local6 11)
						)
					)
					(else 
						(if (gEgo has: 14) (++ local6))
					)
				)
				(cond 
					(
					(or (> local6 13) (and (> local6 3) (< local6 9))) (= global150 0) (-- local6) (gLb2Messager say: 30 0 5))
					((> global150 0) (-- global150) (cobraDoor setScript: sLauraOil))
					(else (gLb2Messager say: 30 0 5))
				)
			)
			(30
				(switch local6
					(3
						(gEgo setScript: 0)
						(= local7 1)
						(cobraDoor setScript: sLauraLasso3)
					)
					(13
						(gEgo setScript: 0)
						(= local7 1)
						(cobraDoor setScript: sLauraLasso13)
					)
					(else 
						(cobraDoor setScript: sCobraStrike)
					)
				)
			)
			(4
				(cobraDoor setScript: sCobraStrike)
			)
			(1
				(global2 setScript: sShowCobraLoose)
			)
			(8
				(global2 setScript: sShowCobraLoose)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance cobraDoor of Prop
	(properties
		x 108
		y 28
		noun 29
		view 521
		loop 8
		cel 6
		priority 2
		signal $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (self doVerb: 30))
			(30
				(cond 
					(local7 (self setScript: sPutCobraBack))
					((== (self cel?) 0) (self setCycle: End))
					(else (self setCycle: Beg))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bigCobra of View
	(properties
		x 34
		y 79
		view 521
		loop 13
		cel 3
		priority 15
		signal $0010
	)
)

(instance rosettaCloth of View
	(properties
		x 223
		y 105
		noun 21
		approachX 210
		approachY 131
		view 521
		loop 13
		priority 9
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if
					(or
						(== (gEgo view?) 522)
						(== (gEgo view?) 528)
						(== (gEgo view?) 529)
					)
					(gLb2Messager say: 58 0 7)
				else
					(global2 setScript: sRemoveCloth)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance snakeOil of View
	(properties
		x 113
		y 104
		z 10
		noun 30
		approachX 130
		approachY 145
		view 520
		loop 3
		priority 8
		signal $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(global2 setInset: inSnakeOil)
			)
			(8 (self doVerb: 1))
			(4 (self doVerb: 1))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance mountedSkull of Prop
	(properties
		x 98
		y 74
		noun 31
		approachX 91
		approachY 141
		view 520
		loop 4
		priority 8
		signal $0010
		cycleSpeed 18
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(cond 
						(
							(or
								(== (gEgo view?) 522)
								(== (gEgo view?) 528)
								(== (gEgo view?) 529)
							)
							(gLb2Messager say: 58 0 7)
						)
						((MuseumRgn nobodyAround:) (self setScript: sSecretDoor))
						(else (return 1))
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance secretDoor of Prop
	(properties
		x 294
		y 159
		view 524
		loop 1
		cycleSpeed 12
	)
)

(instance intercom of View
	(properties
		x 179
		y 96
		noun 32
		approachX 152
		approachY 135
		view 520
		loop 3
		cel 1
		priority 8
		signal $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(global2 setInset: inIntercom)
			)
			(8 (self doVerb: 1))
			(4 (self doVerb: 1))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance deadCountess of View
	(properties
		x 111
		y 89
		heading 90
		noun 33
		approachX 109
		approachY 125
		view 524
		priority 8
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if local8
					(gEgo setHeading: 62)
					(gGame points: 1 163)
					(global2 newRoom: 525)
				else
					(gLb2Messager say: 58 0 7)
				)
			)
			(8 (self doVerb: 1))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance ratFore of Feature
	(properties
		x 281
		y 200
		noun 14
		approachX 225
		approachY 171
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(global2 setInset: inRatFore)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance ratBack of Feature
	(properties
		x 88
		y 21
		noun 14
		nsTop 14
		nsLeft 74
		nsBottom 29
		nsRight 103
		approachX 80
		approachY 113
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(global2 setInset: inRatBack)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rosetta of Feature
	(properties
		x 293
		y 104
		heading 90
		noun 34
		sightAngle 40
		approachX 210
		approachY 131
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(8
				(if
					(or
						(== (gEgo view?) 522)
						(== (gEgo view?) 528)
						(== (gEgo view?) 529)
					)
					(gLb2Messager say: 58 0 7)
				else
					(gGame points: 1 137)
					((ScriptID 21 0) doit: 1025)
					(= temp0 14)
					(while (< temp0 27)
						((ScriptID 21 0) doit: (+ temp0 1088))
						(++ temp0)
					)
					(global2 newRoom: 456)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance certificate of Feature
	(properties
		y 1
	)
	
	(method (onMe param1)
		(if (super onMe: param1)
			(cond 
				(
				(and (<= 188 gX) (<= gX 206) (<= 20 gY) (<= gY 43)) (= x 197) (= y 31) (= z 0) (= noun 2))
				(
				(and (<= 185 gX) (<= gX 210) (<= 51 gY) (<= gY 72)) (= x 197) (= y 61) (= z 0) (= noun 3))
				(
				(and (<= 222 gX) (<= gX 240) (<= 17 gY) (<= gY 37)) (= x 231) (= y 27) (= z 0) (= noun 4))
				(
				(and (<= 246 gX) (<= gX 274) (<= 15 gY) (<= gY 38)) (= x 260) (= y 26) (= z 0) (= noun 5))
			)
		)
	)
)

(instance skeletonLegs of Feature
	(properties
		x 302
		y 134
		noun 6
		nsLeft 285
		nsBottom 68
		nsRight 319
		sightAngle 40
	)
)

(instance cobraCage of Feature
	(properties
		x 93
		y 46
		noun 7
		nsTop 29
		nsLeft 78
		nsBottom 63
		nsRight 109
		sightAngle 40
		approachX 81
		approachY 116
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if local7
					(cobraDoor setScript: sPutCobraBack)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(30 (self doVerb: 4))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bookShelf of Feature
	(properties
		x 91
		y 70
		noun 8
		nsTop 65
		nsLeft 78
		nsBottom 76
		nsRight 104
		sightAngle 40
	)
)

(instance jars of Feature
	(properties)
	
	(method (onMe param1)
		(if (super onMe: param1)
			(cond 
				(
				(and (<= 81 gX) (<= gX 88) (<= 79 gY) (<= gY 90)) (= x 85) (= y 83) (= z 0) (= noun 9))
				(
				(and (<= 92 gX) (<= gX 101) (<= 79 gY) (<= gY 87)) (= x 96) (= y 83) (= z 0) (= noun 10))
				(
				(and (<= 80 gX) (<= gX 90) (<= 90 gY) (<= gY 97)) (= x 85) (= y 93) (= z 0) (= noun 11))
			)
		)
	)
)

(instance chair of Feature
	(properties
		x 144
		y 82
		noun 12
		nsTop 76
		nsLeft 134
		nsBottom 88
		nsRight 154
		sightAngle 40
	)
)

(instance skeletonFore of Feature
	(properties
		x 260
		y 164
		noun 13
		sightAngle 40
	)
)

(instance desk of Feature
	(properties
		y 88
		noun 15
		sightAngle 40
	)
)

(instance windowView of Feature
	(properties
		y 47
		noun 28
		sightAngle 40
	)
)

(instance drapes of Feature
	(properties
		y 12
		noun 17
		sightAngle 40
	)
)

(instance roachTop of Feature
	(properties
		y 55
		noun 18
		sightAngle 40
	)
)

(instance roachBottom of Feature
	(properties
		y 102
		noun 19
		sightAngle 40
	)
)

(instance displayCase of Feature
	(properties
		x 320
		y 77
		noun 20
		sightAngle 40
	)
)

(instance cages of Feature
	(properties
		x 320
		y 128
		noun 22
		sightAngle 40
	)
)

(instance lizards of Feature
	(properties
		y 180
		noun 23
		sightAngle 40
	)
)

(instance bookcase of Feature
	(properties
		y 1
		noun 25
		sightAngle 40
	)
)

(instance lizardTable of Feature
	(properties
		y 183
		noun 26
		sightAngle 40
	)
)

(instance standRat of Feature
	(properties
		x 260
		y 180
		noun 27
		sightAngle 40
	)
)

(instance inCobra of Inset
	(properties
		view 521
		loop 13
		cel 1
		x 77
		y 23
		disposeNotOnMe 1
		noun 54
	)
	
	(method (init)
		(if
			(not
				(cond 
					((> global123 4))
					((== global123 4) (proc0_10 16648 1))
				)
			)
			(fang init:)
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(fang dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if
					(or
						(> global123 4)
						(and (== global123 4) (proc0_10 16648 1))
					)
					(gLb2Messager say: noun 1 8)
				else
					(gLb2Messager say: noun 1 9)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance hieroglyphics of Feature
	(properties
		x 250
		y 65
		heading 90
		noun 24
		sightAngle 90
		approachX 210
		approachY 131
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if
					(or
						(== (gEgo view?) 522)
						(== (gEgo view?) 528)
						(== (gEgo view?) 529)
					)
					(gLb2Messager say: 58 0 7)
				else
					(gGame handsOff:)
					(global2 setScript: sHieroglyphics)
				)
			)
			(8 (self doVerb: 1))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fang of View
	(properties
		x 93
		y 32
		view 521
		loop 13
		cel 2
		priority 15
		signal $0010
	)
)

(instance inSnakeOil of Inset
	(properties
		view 520
		loop 2
		x 127
		y 33
		priority 15
		disposeNotOnMe 1
		noun 38
	)
	
	(method (dispose)
		(global2 setScript: sOlympiaOil)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (self dispose:))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inIntercom of Inset
	(properties
		view 520
		x 162
		y 79
		disposeNotOnMe 1
		noun 39
	)
)

(instance inRatFore of Inset
	(properties
		view 520
		loop 1
		x 232
		y 147
		priority 15
		disposeNotOnMe 1
		noun 40
	)
)

(instance inRatBack of Inset
	(properties
		view 520
		loop 1
		x 72
		y 6
		disposeNotOnMe 1
		noun 41
	)
)

(instance inHieroglyphics of Inset
	(properties
		picture 780
		noun 24
	)
	
	(method (init)
		(= local2 522)
		(super init: &rest)
		(DrawPic 780)
		(localproc_2000 local2)
		(proc0_8 1)
		(gLb2WH addToFront: self)
	)
	
	(method (dispose)
		(gLb2WH delete: self)
		(proc0_8 0)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= local2 (if (== local2 522) 523 else 522))
				(sFX number: 525 flags: 1 play:)
				(DrawPic 780 (if (== local2 522) 11 else 12))
				(localproc_2000 local2)
			)
			(13 (self dispose:))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sHieroglyphics of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGameMusic2 fade: 80 20 20 0)
				(= cycles 1)
			)
			(1
				(gOldCast eachElementDo: #hide)
				(gGame handsOff:)
				(User canInput: 1)
				(gIconBar enable: 0 2 1)
				(global2 setInset: inHieroglyphics self)
			)
			(2
				(if (not (gEgo has: 14)) (snakeOil show:))
				(gGame handsOn:)
				;(gIconBar disable: 7)
				(gOldCast eachElementDo: #show)
				(= cycles 1)
			)
			(3
				(gGameMusic2 fade: 127 20 20 0)
				(self dispose:)
			)
		)
	)
)

(instance sRemoveCloth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(gEgo setMotion: PolyPath 215 126 self)
			)
			(1
				(rosettaCloth dispose:)
				(proc0_3 49)
				(gEgo
					view: 521
					setLoop: 14
					setCel: 0
					setScale: Scaler 100 100 190 0
					setCycle: End self
				)
			)
			(2
				(gEgo
					normalize: 831
					setScale: Scaler 120 100 190 0
					setHeading: 90
				)
				(gGame handsOn:)
				;(gIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sOlympiaOil of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(gEgo stopUpd:)
				(if (== ((ScriptID 90 2) room?) gNumber)
					((ScriptID 90 2)
						setPri: 13
						setMotion: DPath 128 183 102 160 95 125 self
					)
				else
					((ScriptID 90 2)
						moveTo: gNumber
						posn: 170 250
						setPri: 13
						setMotion: DPath 128 183 102 160 95 125 self
					)
				)
			)
			(1
				(proc0_5 (ScriptID 90 2) gEgo)
				(= cycles 1)
			)
			(2
				(proc0_3 48)
				((ScriptID 90 2)
					view: 525
					setLoop: 2
					setCel: 0
					setCycle: End self
				)
			)
			(3 (= ticks 30))
			(4
				(snakeOil dispose:)
				((ScriptID 90 2) setCycle: Beg self)
			)
			(5
				((ScriptID 90 2)
					view: 820
					setLoop: -1
					setCycle: Walk
					setMotion:
						MoveTo
						(+ ((ScriptID 90 2) x?) 10)
						((ScriptID 90 2) y?)
						self
				)
			)
			(6
				((ScriptID 90 2) setHeading: 180)
				(= cycles 1)
			)
			(7
				((ScriptID 90 2) setCycle: StopWalk -1)
				(= ticks 120)
			)
			(8
				(gLb2Messager say: 1 0 1 0 self 1520)
			)
			(9
				((ScriptID 90 2)
					view: 820
					setCycle: Walk
					setMotion: DPath 102 160 128 183 170 250 self
				)
			)
			(10
				((ScriptID 90 2) moveTo: -2)
				(gEgo startUpd:)
				((Inv at: 14) owner: 0)
				(gGame handsOn:)
				;(gIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sCobraLoose of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1 (= ticks 180))
			(2
				(sFX number: 522 flags: 1 play:)
				(= cycles 2)
			)
			(3
				(gEgo setHeading: 270)
				(= ticks 60)
			)
			(4
				(cobraLoose
					init:
					setLoop: 0
					setCycle: Fwd
					setMotion: MoveTo 86 158 self
				)
			)
			(5
				(proc0_5 gEgo cobraLoose)
				(cobraLoose setCycle: End self)
			)
			(6
				(cobraLoose
					setLoop: 9
					setCel: 0
					posn: 90 159
					setCycle: End self
				)
			)
			(7
				(cobraLoose
					setLoop: 11
					setCel: 0
					posn: 92 159
					setCycle: Fwd
				)
				(= ticks 30)
			)
			(8
				(sFX number: 522 flags: 1 play:)
				(bigCobra init:)
				(= ticks 180)
			)
			(9
				(bigCobra dispose:)
				(= local11 1)
				(gEgo setScript: sCobraTimer)
				(gGame handsOn:)
				;(gIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sShowCobraLoose of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(sFX number: 522 flags: 1 play:)
				(bigCobra
					init:
					x: (- (cobraLoose x?) 58)
					y: (- (cobraLoose y?) 80)
				)
				(= ticks 180)
			)
			(1
				(bigCobra dispose:)
				(= ticks 60)
			)
			(2
				(gGame handsOn:)
				;(gIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sLauraOil of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(gEgo setScript: 0)
				(if (< local6 9)
					(gEgo setMotion: PolyPath 158 130 self)
				else
					(gEgo setMotion: PolyPath 160 179 self)
				)
			)
			(1
				(switch local6
					(1 (= local10 0))
					(2 (= local10 0))
					(3 (= local10 1))
					(11 (= local10 2))
					(12 (= local10 2))
					(13 (= local10 3))
				)
				(gEgo
					view: 522
					setLoop: local10
					setScale: Scaler 100 100 190 0
					setCel: 0
					setCycle: End self
				)
			)
			(2
				(switch local6
					(1
						(cobraLoose setLoop: 0 setMotion: MoveTo 147 177 self)
					)
					(2
						(cobraLoose setLoop: 0 setMotion: MoveTo 213 171 self)
					)
					(3
						(= local12 120)
						(cobraLoose setLoop: 0 setMotion: MoveTo 260 150 self)
					)
					(11
						(cobraLoose setLoop: 0 setMotion: MoveTo 128 134 self)
					)
					(12
						(cobraLoose setLoop: 0 setMotion: MoveTo 187 133 self)
					)
					(13
						(cobraLoose setLoop: 15 setMotion: PolyPath 207 107 self)
					)
				)
			)
			(3
				(gEgo normalize: 831 setScale: Scaler 120 100 190 0)
				(proc0_5 gEgo cobraLoose)
				(= cycles 1)
			)
			(4
				(cobraLoose setLoop: 4 setCel: 0 setCycle: End self)
			)
			(5
				(if (< (cobraLoose x?) (gEgo x?))
					(cobraLoose setLoop: 9 setCel: 0 setCycle: End self)
				else
					(cobraLoose setLoop: 10 setCel: 0 setCycle: End self)
				)
			)
			(6
				(if (< (cobraLoose x?) (gEgo x?))
					(cobraLoose setLoop: 11 setCel: 0 setCycle: Fwd)
				else
					(cobraLoose setLoop: 12 setCel: 0 setCycle: Fwd)
				)
				(= cycles 1)
			)
			(7
				(if (< (gEgo x?) (cobraLoose x?))
					(= local9 0)
				else
					(= local9 1)
				)
				(gEgo setScript: sCobraTimer)
				(gGame handsOn:)
				;(gIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sLauraLasso3 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(gEgo setMotion: MoveTo 216 151 self)
			)
			(1 (= cycles 2))
			(2
				(gEgo view: 522 setCycle: 0 setLoop: 5 setCel: 0)
				(= ticks 30)
			)
			(3
				(cobraLoose setLoop: 3 setCel: 0 setCycle: 0)
				(= cycles 1)
			)
			(4 (gEgo setCycle: CT 3 1 self))
			(5
				(cobraLoose hide: dispose:)
				(gEgo setCycle: End self)
			)
			(6
				(gEgo
					view: 528
					setLoop: 0
					setCel: 0
					posn: 220 152
					setLoop: -1
					setCycle: StopWalk 529
				)
				(= ticks 60)
			)
			(7
				(gGame handsOn:)
				;(gIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sLauraLasso13 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= local7 1)
				(gEgo
					normalize: 831
					setScale: Scaler 120 100 190 0
					setMotion: MoveTo 209 127 self
				)
			)
			(1
				(gEgo
					view: 522
					setScale: Scaler 110 90 190 0
					setLoop: 6
					setCel: 0
					setCycle: StopWalk
				)
				(= cycles 1)
			)
			(2
				(cobraLoose hide: dispose:)
				(gEgo view: 522 setLoop: 6 cel: 0 setCycle: End self)
			)
			(3 (gEgo setCycle: Beg self))
			(4
				(gEgo
					view: 528
					setLoop: 3
					setCel: 6
					posn: 211 128
					setLoop: -1
					setCycle: StopWalk 529
				)
				(= ticks 60)
			)
			(5
				(gGame handsOn:)
				;(gIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sCobraStrike of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(proc0_5 gEgo cobraLoose)
				(= local7 1)
				(cobraLoose illegalBits: 0 ignoreActors: 1)
				(if local9
					(cobraLoose
						setLoop: 0
						setCycle: Fwd
						setMotion: PolyPath (- (gEgo x?) 34) (+ (gEgo y?) 10) self
					)
				else
					(cobraLoose
						setLoop: 1
						setCycle: Fwd
						setMotion: PolyPath (+ (gEgo x?) 35) (+ (gEgo y?) 11) self
					)
				)
			)
			(1
				(gGameMusic2 stop:)
				(sFX number: 523 flags: 1 play:)
				(if local9
					(cobraLoose
						setLoop: 2
						setCel: 0
						posn: (- (gEgo x?) 21) (+ (gEgo y?) 6)
						setCycle: CT 4 1 self
					)
				else
					(cobraLoose
						setLoop: 3
						setCel: 0
						posn: (+ (gEgo x?) 21) (+ (gEgo y?) 7)
						setCycle: CT 4 1 self
					)
				)
			)
			(2
				(cobraLoose setCycle: End self)
				(sFX number: 481 flags: 1 setLoop: 1 play:)
			)
			(3
				(gEgo
					view: 523
					setLoop: 0
					setCel: 0
					posn: (+ (gEgo x?) 15) (+ (gEgo y?) 1)
					setCycle: End self
				)
			)
			(4
				(= global145 11)
				(global2 newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance sPutCobraBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= local7 0)
				(gEgo setCycle: Walk setMotion: PolyPath 132 95 self)
			)
			(1
				(gEgo
					view: 522
					setLoop: 4
					setCel: 0
					posn: 126 98
					setCycle: CT 6 1 self
				)
			)
			(2
				(cobraDoor setCycle: End self)
			)
			(3
				(gGameMusic2 fade:)
				(gEgo setCycle: CT 9 1 self)
			)
			(4
				(cobra init: stopUpd:)
				(= cycles 1)
			)
			(5
				(gEgo setCycle: End)
				(cobraDoor setCycle: Beg self)
			)
			(6
				(sFX number: 441 flags: 1 setLoop: 1 play:)
				(gGame points: 1 164)
				(= local8 1)
				(proc0_3 80)
				(cobraLoose hide:)
				(gEgo
					normalize: 831
					setScale: Scaler 120 100 190 0
					signal: 16384
					setHeading: 270
				)
				(UnLoad 128 528)
				(UnLoad 128 529)
				(gGameMusic2 number: 520 loop: -1 flags: 1 play:)
				;(gIconBar disable: 7)
				(= cycles 1)
			)
			(7
				(cobraDoor stopUpd:)
				(gGame handsOn:)
				;(gIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sDropCobraGetBitten of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= local7 0)
				(gEgo
					view: 522
					setLoop: 7
					setCel: 0
					setScale: Scaler 100 100 190 0
					setCycle: End self
				)
			)
			(1 (= ticks 60))
			(2
				(cobraLoose
					show:
					setLoop: 4
					setCel: 3
					x: (+ (gEgo x?) 4)
					y: (+ (gEgo y?) 3)
				)
				(gEgo view: 523 setLoop: 0 setCel: 0 setCycle: End self)
			)
			(3
				(= global145 11)
				(global2 newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance sCobraTurn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local9
					(cobraLoose setLoop: 5 setCel: 0 setCycle: End self)
				else
					(cobraLoose setLoop: 4 setCel: 0 setCycle: End self)
				)
			)
			(1
				(if local9
					(cobraLoose setLoop: 11 setCel: 0 setCycle: End self)
				else
					(cobraLoose setLoop: 12 setCel: 0 setCycle: End self)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance sCobraTimer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 15))
			(1
				(gEgo setScript: sCobraStrike)
				(self dispose:)
			)
		)
	)
)

(instance sFX of Sound
	(properties
		flags $0001
	)
)

(instance southExitFeature of ExitFeature
	(properties
		nsTop 184
		nsLeft 115
		nsBottom 189
		nsRight 245
		cursor 11
		exitDir 3
		noun 57
	)
)

(instance sSecretDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(gEgo setMotion: PolyPath 93 122 self)
			)
			(1
				(proc0_5 gEgo mountedSkull)
				(= cycles 4)
			)
			(2
				(gEgo
					view: 561
					setScale: Scaler 100 100 190 0
					setLoop: 0
					setCel: 0
					setCycle: End self
				)
			)
			(3
				(gEgo cycleSpeed: 12 setCycle: Beg self)
				(sFX number: 49 flags: 1 setLoop: 1 play:)
				(mountedSkull setCycle: End self)
			)
			(4 0)
			(5
				(gEgo normalize: 831 setScale: Scaler 120 100 190 0)
				(proc0_5 gEgo secretDoor)
				(sFX number: 721 flags: 1 setLoop: 1 play:)
				(secretDoor setCycle: End self)
			)
			(6
				(sFX stop:)
				(gEgo setMotion: PolyPath 315 167 self)
			)
			(7
				(gEgo setMotion: MoveTo 330 167 self)
			)
			(8
				(gEgo setPri: 2)
				(sFX number: 721 flags: 1 setLoop: 1 play:)
				(secretDoor setCycle: Beg self)
			)
			(9
				(sFX stop:)
				(secretDoor stopUpd:)
				(global2 newRoom: (global2 north?))
				(gGame handsOn:)
				;(gIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)
