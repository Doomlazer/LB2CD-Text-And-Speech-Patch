;;; Sierra Script 1.0 - (do not remove this comment)
(script# 500)
(include sci.sh)
(use Main)
(use LBRoom)
(use ExitFeature)
(use MuseumRgn)
(use Inset)
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
	rm500 0
)

(local
	local0
	local1
	theGLb2DoVerbCode
	local3
)
(instance rm500 of LBRoom
	(properties
		noun 40
		picture 500
		north 420
		south 510
		vanishingX 0
	)
	
	(method (init)
		(proc958_0 128 500 504 505 831)
		(Load rsPIC 501)
		(Load rsSOUND 501 19)
		(if (proc0_2 4) (Load rsSOUND 502))
		(if
			(or
				(> global123 4)
				(and (== global123 4) (proc0_10 12548 1))
			)
			(proc958_0 129 556 505)
			(proc958_0 132 500 3 6 84)
		)
		(gEgo
			init:
			normalize: (if (== global123 5) 426 else 831)
			setScale: Scaler 100 0 190 0
		)
		(self setRegions: 90)
		(gGame handsOff:)
		(switch gGNumber
			(north
				(gEgo x: 103 y: 159 setHeading: 180)
				(cond 
					((== global123 5) (self setScript: sLauraDies))
					(
						(and
							(== global123 3)
							(proc0_10 8512)
							(not (proc0_2 85))
						)
						(if (== ((ScriptID 90 1) room?) gNumber)
							((ScriptID 90 1) moveTo: -2)
						)
						(if (== ((ScriptID 90 2) room?) gNumber)
							((ScriptID 90 2) moveTo: -2)
						)
						(self setScript: sMeeting)
					)
					(else (self setScript: sEnterNorth))
				)
			)
			(south
				(gEgo x: 260 y: 200 setHeading: 315)
				(self setScript: sEnterSouth)
			)
			(else 
				(gEgo posn: 78 176)
				(gGame handsOn:)
			)
		)
		(super init:)
		(if
			(and
				(== global123 3)
				(proc0_10 8512)
				(not (proc0_2 85))
			)
			(= local3 ((ScriptID 90 3) room?))
			; BUGFIX: Make Heimlich and O'Riley face the correct direction in the
			; Old Master's Room at the end of Act 3.
			;
			; As explained in sPartysOver:changeState(0) in script #355, Sierra
			; removed the diagonal walking loops from the museum actors' views, but
			; they didn't update the code accordingly in various places, resulting
			; in actors facing the wrong direction because loop 8 doesn't exist.
			;
			; This specific case affects to Heimlich and O'Riley, when Laura enters
			; the Old Master's Room right before the conclusion of Act 3. Heimlich
			; and O'Riley are in the room facing the wrong direction.
			;
			; We fix it by using loop 4 instead of 8. Fix ported from:
			; https://github.com/scummvm/scummvm/blob/85702e06764f95a6b700e348dd90931613efdc29/engines/sci/engine/script_patches.cpp#L11370
			((ScriptID 90 3) ; aORiley
				originalView: 818
				view: 818
				moveTo: gNumber
				posn: 227 179
;;;				setLoop: 8
				setLoop: 4
				setCel: 1
			)
			((ScriptID 32 0) ; aHeimlich
				originalView: 814
				room: gNumber
				init:
				posn: 206 179
;;;				setLoop: 8
				setLoop: 4
				setCel: 0
			)
			; END OF BUGFIX (see also:
			; - rm650:init, in #650
			; - sEnterNorth:changeState(0), in this same script file
			; - sEnterNorth:changeState(0), in #420
			; - rm400:init and sHeimlichShoos:changeState(4), in #400
			; - sPartysOver:changeState(0) and sPartysOver:changeState(15), in #355
			; )
			(WrapMusic pause:)
			(gGameMusic2 number: 350 loop: -1 flags: 1 play:)
		)
		(if
			(or
				(> global123 4)
				(and (== global123 4) (proc0_10 12548 1))
			)
			(yvetteStatue init: stopUpd:)
			(global2
				addObstacle:
					(= local1
						((Polygon new:)
							type: 2
							init: 135 183 180 183 191 189 142 189
							yourself:
						)
					)
			)
		)
		(if (not (gEgo has: 8))
			(keyGlint init: approachVerbs: 4 1 8 setScript: sKeyGlint)
		)
		(bobPortrait init:)
		(rickPortrait init:)
		(leftWall init: setOnMeCheck: 1 4)
		(dennisPortrait init: setOnMeCheck: 1 256)
		(suziPortrait init: setOnMeCheck: 1 8)
		(boschPortrait init: setOnMeCheck: 1 16)
		(erwinPortrait init: setOnMeCheck: 1 32)
		(johnPortrait init: setOnMeCheck: 1 64)
		(bench init: setOnMeCheck: 1 128)
		(sculpture1 init: setOnMeCheck: 1 1024)
		(sculpture2 init: setOnMeCheck: 1 512)
		(ceiling init: setOnMeCheck: 1 2048)
		(borderCeiling init: setOnMeCheck: 1 4096)
		(southExitFeature init:)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((proc0_1 gEgo 2) (gEgo edgeHit: 1) (self newRoom: north))
		)
	)
	
	(method (newRoom newRoomNumber)
		(cond 
			(
			(and (== global123 3) (proc0_2 85) (proc0_10 128)) (= newRoomNumber 26))
			((== global123 2) (gGameMusic2 stop:) (gWrapSound pause: 0))
			(
				(or
					(> global123 4)
					(and (== global123 4) (proc0_10 12548 1))
				)
				(local1 dispose:)
			)
		)
		(super newRoom: newRoomNumber)
	)
)

(instance sLauraDies of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gEgo setMotion: PolyPath 152 166 self)
			)
			(1
				(proc0_5 gEgo oRiley)
				(oRiley
					init:
					view: 423
					posn: 99 150
					setScale: 165
					setHeading: 180
					setCycle: StopWalk -1
					setMotion: PolyPath 124 161 self
				)
			)
			(2
				(oRiley
					view: 424
					posn: (+ (oRiley x?) 4) (oRiley y?)
					setLoop: 0
					setCel: 0
					setCycle: End self
				)
			)
			(3
				(gEgo
					setMotion: 0
					view: 858
					setLoop: 4
					setCycle: End self
				)
				(sFX number: 80 flags: 1 play:)
			)
			(4 (= ticks 60))
			(5
				(= global145 0)
				(global2 newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance oRiley of Actor
	(properties
		x 227
		y 179
		view 818
		loop 8
		cel 1
	)
)

(instance yvetteStatue of Prop
	(properties
		x 150
		y 141
		noun 8
		approachX 185
		approachY 187
		view 504
		priority 15
		signal $0010
	)
	
	(method (init)
		(if
			(or
				(> global123 4)
				(and (== global123 4) (proc0_10 12548))
			)
			(self cel: 8 stopUpd:)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(29
					(if
						(not
							(cond 
								((> global123 4))
								((== global123 4) (proc0_10 12548))
							)
						)
						(if (MuseumRgn nobodyAround:)
							(global2 setScript: sSmashPlaster)
						else
							(return 1)
						)
					)
				)
				(1
					(if
					(== (yvetteStatue cel?) (yvetteStatue lastCel:))
						(global2 setScript: sDeadYvette)
					else
						(gLb2Messager say: 8 1)
					)
				)
				(8
					(if
					(== (yvetteStatue cel?) (yvetteStatue lastCel:))
						(global2 setScript: sDeadYvette)
					else
						(gLb2Messager say: 8 8)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance keyGlint of Prop
	(properties
		x 267
		y 116
		noun 20
		view 500
		cycleSpeed 12
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(global2 setScript: sBoschPainting)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance keyGlintInset of Prop
	(properties
		x 198
		y 91
		noun 21
		view 500
		cycleSpeed 12
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(inBoschPainting setInset: inSkeletonKey)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance lauraSwingingRt of Actor ; unused, kept to not need a heap patch
	(properties
		x 185
		y 187
		view 504
		loop 1
		cel 8
	)
)

(instance lauraSwingingLt of Actor ; unused, kept to not need a heap patch
	(properties
		x 136
		y 185
		view 504
		loop 2
		cel 9
	)
)

(instance bobPortrait of Feature
	(properties
		x 50
		y 128
		noun 2
		nsTop 106
		nsLeft 33
		nsBottom 151
		nsRight 67
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (gLb2Messager say: 24 4))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rickPortrait of Feature
	(properties
		x 153
		y 113
		noun 4
		nsTop 102
		nsLeft 136
		nsBottom 124
		nsRight 170
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (gLb2Messager say: 24 4))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance leftWall of Feature
	(properties
		y 1
		noun 7
		sightAngle 40
	)
)

(instance dennisPortrait of Feature
	(properties
		x 200
		y 1
		noun 3
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (gLb2Messager say: 24 4))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance suziPortrait of Feature
	(properties
		x 240
		y 1
		noun 5
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (gLb2Messager say: 24 4))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance boschPortrait of Feature
	(properties
		x 270
		y 100
		noun 9
		sightAngle 40
	)
)

(instance skeletonKey of Feature ; unused, kept to not need a heap patch
	(properties
		x 270
		y 100
		noun 17
		sightAngle 40
		onMeCheck $0010
	)

	(method (doVerb theVerb)
;;;		(switch theVerb
;;;			(1
;;;				(global2 setScript: sBoschPainting)
;;;			)
;;;			(8
;;;				(global2 setScript: sBoschPainting)
;;;			)
;;;			(else  (super doVerb: theVerb))
;;;		)
	)
)

(instance erwinPortrait of Feature
	(properties
		x 380
		y 100
		noun 6
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (gLb2Messager say: 24 4))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance johnPortrait of Feature
	(properties
		x 380
		y 160
		noun 1
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (gLb2Messager say: 24 4))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bench of Feature
	(properties
		y 1
		noun 10
		sightAngle 40
	)
)

(instance sculpture1 of Feature
	(properties
		y 1
		noun 11
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (gLb2Messager say: 25 4))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sculpture2 of Feature
	(properties
		y 1
		noun 12
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (gLb2Messager say: 25 4))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance ceiling of Feature
	(properties
		y 1
		noun 13
		sightAngle 40
	)
)

(instance borderCeiling of Feature
	(properties
		y 1
		noun 14
		sightAngle 40
	)
)

(instance inDeadYvette of Inset
	(properties
		picture 505
		priority 12
	)
	
	(method (init)
		(super init: &rest)
		(= theGLb2DoVerbCode gLb2DoVerbCode)
		(= gLb2DoVerbCode exitDoVerbCode)
		(proc0_8 1)
		(gLb2WH addToFront: self)
		(gIconBar disable: 7)
		(if (not (gEgo has: 26)) (bifocals init:))
		(if (not (gEgo has: 27)) (redHair init:))
		(feHair init: setOnMeCheck: 1 16384)
		(feScarf init: setOnMeCheck: 1 8192)
		(feFace init: setOnMeCheck: 1 4096)
		(feDress init: setOnMeCheck: 1 2048)
		(feCleavage init: setOnMeCheck: 1 1024)
		(feBody init: setOnMeCheck: 1 512)
		(feLtHand init: setOnMeCheck: 1 256)
		(feRtHand init: setOnMeCheck: 1 128)
		(feTowel init: setOnMeCheck: 1 64)
	)
	
	(method (dispose)
		(gLb2WH delete: self)
		(gIconBar enable: 7)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(= gLb2DoVerbCode theGLb2DoVerbCode)
				(self dispose:)
			)
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
			(= gLb2DoVerbCode theGLb2DoVerbCode)
			(inDeadYvette dispose:)
		else
			(proc0_6 param2 param1)
		)
	)
)

(instance bifocals of View
	(properties
		x 102
		y 156
		noun 22
		view 505
		priority 13
		signal $4011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(if (not (gEgo has: 26))
					(inDeadYvette setInset: inBifocals)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance inBifocals of Inset
	(properties
		view 505
		loop 1
		x 90
		y 135
		hideTheCast 1
		disposeNotOnMe 1
		noun 37
	)
	
	(method (init)
		(super init: &rest)
		(if (not (gEgo has: 26))
			(bifocals hide:)
			(viBifocals init:)
		)
	)
)

(instance viBifocals of View
	(properties
		x 103
		y 156
		noun 38
		view 505
		loop 1
		cel 1
		priority 15
		signal $4011
	)
	
	(method (dispose)
		(if (not (gEgo has: 26)) (bifocals show: stopUpd:))
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(gGame points: 1 162)
				((ScriptID 21 0) doit: 795)
				(gEgo get: 26)
				(bifocals dispose:)
				(inBifocals dispose:)
				(self dispose:)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance redHair of View
	(properties
		x 161
		y 152
		noun 23
		view 505
		loop 2
		priority 13
		signal $4011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(if (not (gEgo has: 27))
					(inDeadYvette setInset: inRedHair)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance inRedHair of Inset
	(properties
		view 505
		loop 3
		x 144
		y 146
		hideTheCast 1
		disposeNotOnMe 1
		noun 35
	)
	
	(method (init)
		(super init: &rest)
		(if (not (gEgo has: 27))
			(redHair hide:)
			(viRedHair init:)
		)
	)
)

(instance viRedHair of View
	(properties
		x 155
		y 155
		noun 39
		view 505
		loop 3
		cel 1
		priority 15
		signal $4011
	)
	
	(method (dispose)
		(if (not (gEgo has: 27)) (redHair show: stopUpd:))
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(gGame points: 1 160)
				(gEgo get: 27)
				((ScriptID 21 0) doit: 796)
				(inRedHair dispose:)
				(redHair dispose:)
				(self dispose:)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance feHair of Feature
	(properties
		y 1
		noun 29
		sightAngle 40
	)
)

(instance feScarf of Feature
	(properties
		y 1
		noun 30
		sightAngle 40
	)
)

(instance feFace of Feature
	(properties
		y 1
		noun 31
		sightAngle 40
	)
)

(instance feDress of Feature
	(properties
		y 1
		noun 32
		sightAngle 40
	)
)

(instance feCleavage of Feature
	(properties
		y 1
		noun 33
		sightAngle 40
	)
)

(instance feBody of Feature
	(properties
		y 1
		noun 34
		sightAngle 40
	)
)

(instance feLtHand of Feature
	(properties
		y 1
		noun 35
		sightAngle 40
	)
)

(instance feRtHand of Feature
	(properties
		y 1
		noun 36
		sightAngle 40
	)
)

(instance feTowel of Feature
	(properties
		y 1
		noun 37
		sightAngle 40
	)
)

(instance inBoschPainting of Inset
	(properties
		picture 501
		hideTheCast 1
		noun 19
	)
	
	(method (init)
		(super init: &rest)
		(proc0_8 1)
		(gLb2WH addToFront: self)
		(if (not (gEgo has: 8))
			(keyGlintInset init: setScript: sKeyGlintInset)
		)
	)
	
	(method (dispose)
		(gLb2WH delete: self)
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

(instance inSkeletonKey of Inset
	(properties
		view 500
		loop 1
		x 179
		y 93
		disposeNotOnMe 1
		noun 18
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(22
					(if (MuseumRgn nobodyAround:)
						(gGame points: 1 146)
						(gEgo get: -1 8)
						((ScriptID 21 0) doit: 777)
						(if (!= global90 2) (sFX number: 501 play:))
						(keyGlintInset dispose:)
						(keyGlint setScript: 0)
						(gLb2Messager say: 18 22)
						(self dispose:)
						(return 1)
					else
						(return 1)
					)
				)
				(21
					(if (MuseumRgn nobodyAround:)
						(gGame points: 1 146)
						(gEgo get: -1 8)
						((ScriptID 21 0) doit: 777)
						(if (!= global90 2) (sFX number: 501 play:))
						(keyGlintInset dispose:)
						(keyGlint setScript: 0)
						(gLb2Messager say: 18 21)
						(self dispose:)
						(return 1)
					else
						(return 1)
					)
				)
				(25 (gLb2Messager say: 18 25))
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance sMeeting of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(gEgo setMotion: MoveFwd 15 self)
				(= cycles 5)
			)
			(1
				(gLb2Messager say: 3 0 0 0 self 1500)
			)
			(2)
			(3
				(gEgo setMotion: PolyPath 166 179 self)
			)
			(4 (= cycles 3))
			(5
				(gLb2Messager say: 2 0 0 0 self 1500)
			)
			(6
				((ScriptID 32 0) setHeading: 270 self)
			)
			(7
				(gLb2Messager say: 1 0 0 0 self 1500)
			)
			(8 (gEgo setHeading: 180 self))
			(9
				(if (proc0_10 128)
					(gGameMusic2 number: 502 loop: 1 flags: 1 play: self)
				)
				(gEgo setMotion: MoveFwd 70 self)
			)
			(10
				(if (not (proc0_10 128))
					(gGameMusic2 fade:)
					(WrapMusic pause: 0)
					(= cycles 1)
				)
			)
			(11
				(proc0_3 85)
				((ScriptID 90 3) moveTo: local3)
				(global2 newRoom: (global2 south?))
				(self dispose:)
			)
		)
	)
)

(instance sEnterSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gEgo setMotion: MoveFwd 20 self)
			)
			(1
				(gGame handsOn:)
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
				(if (== global123 2)
					((ScriptID 32 0)
						originalView: 814
						room: gNumber
						init:
						posn: 130 180
						; BUGFIX: Make Heimlich face the correct direction in the Old Master's
						; Room during Act 2.
						;
						; As explained in sPartysOver:changeState(0) in script #355, Sierra
						; removed the diagonal walking loops from the museum actors' views, but
						; they didn't update the code accordingly in various places, resulting
						; in actors facing the wrong direction because loop 8 doesn't exist.
						;
						; This specific case affects to Heimlich when Laura enters the Old
						; Master's Room during Act 2, after the scene with Steve. Heimlich is
						; there to kick her out, but he's facing the wrong direction.
						;
						; We fix it by using loop 4 instead of 8. Fix ported from:
						; https://github.com/scummvm/scummvm/blob/85702e06764f95a6b700e348dd90931613efdc29/engines/sci/engine/script_patches.cpp#L11370
;;;						setLoop: 8
						setLoop: 4
						; END OF BUGFIX (see also:
						; - rm650:init, in #650
						; - rm500:init, in this same script file
						; - sEnterNorth:changeState(0), in #420
						; - rm400:init and sHeimlichShoos:changeState(4), in #400
						; - sPartysOver:changeState(0) and sPartysOver:changeState(15), in #355
						; )
						setCel: 3
						noun: 1
					)
					(gWrapSound pause:)
					(gGameMusic2 number: 19 flags: 1 setLoop: 1 play:)
				)
				(gEgo setMotion: MoveFwd 15 self)
			)
			(1
				(if (gOldCast contains: (ScriptID 32 0))
					(proc0_5 (ScriptID 32 0) gEgo self)
				else
					(= cycles 1)
				)
			)
			(2 (= cycles 5))
			(3
				(if (> global123 2)
					(if
						(not
							(if (and (== global123 3) (proc0_10 8512))
								(not (proc0_2 85))
							)
						)
						(gGame handsOn:)
					)
					(self dispose:)
				else
					(gLb2Messager say: 3 0 84 0 self 1889)
				)
			)
			(4
				(gEgo
					setMotion: PolyPath (gEgo x?) (- (gEgo y?) 15) self
				)
			)
			(5
				(global2 newRoom: (global2 north?))
			)
		)
	)
)

(instance sSmashPlaster of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(gEgo setMotion: PolyPath 185 187 self)
			)
			(1
				(gEgo
					view: 504
					loop: 1
					cel: 0
					setScale: Scaler 100 100 190 0
					setCycle: CT 10 1 self
				)
			)
			(2
				(gEgo setCycle: End self)
				(sFX number: 500 play:)
				(yvetteStatue setCycle: End self)
			)
			(3 0)
			(4
				((ScriptID 22 0) doit: 12548)
				(gGame points: 1 161)
				(gEgo normalize: 831 setScale: Scaler 100 0 190 0)
				(yvetteStatue stopUpd:)
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDeadYvette of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gIconBar disable: 7)
				(gOldCast eachElementDo: #hide)
				(gEgo stopUpd:)
				(WrapMusic pause: 1)
				(if (proc0_2 68)
					(fooSound number: 6 loop: -1 flags: 1 play:)
					(= cycles 1)
				else
					(proc0_3 68)
					(global2 drawPic: 556)
					(wrapMusic init: -1 3 6)
					(= local0 1)
					(sFX number: 84 flags: 1 loop: 1 play:)
					(= ticks 180)
				)
			)
			(1
				(global2 setInset: inDeadYvette self)
			)
			(2
				(gEgo startUpd:)
				(gOldCast eachElementDo: #show)
				(global2 drawPic: 500)
				(= cycles 1)
			)
			(3
				(fooSound fade: 0 12 30 1)
				(if local0 (wrapMusic dispose: 1))
				(WrapMusic pause: 0)
				(proc0_8 0)
				(gIconBar enable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sBoschPainting of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(global2 setInset: inBoschPainting self)
			)
			(1
				(if (gEgo has: 8) (keyGlint dispose:))
				(= cycles 1)
			)
			(2 (proc0_8 0) (self dispose:))
		)
	)
)

(instance sKeyGlint of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1 (= ticks 300))
			(2
				(if (and (> (gEgo y?) 168) (< (gEgo y?) 187))
					(keyGlint setCel: 0 setCycle: End)
					(= ticks (* 60 (Random 3 10)))
				else
					(= cycles 1)
				)
			)
			(3 (self changeState: 2))
		)
	)
)

(instance sKeyGlintInset of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(keyGlintInset setCel: 0 setCycle: End)
				(= ticks (* 60 (Random 2 5)))
			)
			(1 (self changeState: 0))
		)
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

(instance southExitFeature of ExitFeature
	(properties
		nsTop 184
		nsBottom 189
		nsRight 319
		cursor 11
		exitDir 3
		noun 26
	)
)
