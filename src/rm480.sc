;;; Sierra Script 1.0 - (do not remove this comment)
(script# 480)
(include sci.sh)
(use Main)
(use LBRoom)
(use Inset)
(use Blink)
(use Scaler)
(use Osc)
(use RandCycle)
(use PolyPath)
(use CueObj)
(use n958)
(use Rev)
(use Sound)
(use Cycle)
(use View)
(use Obj)

(public
	rm480 0
	Rex 39
)

(local
	local0
	local1
	gEgoMoveSpeed
)
(instance rm480 of LBRoom
	(properties
		noun 1
		picture 480
		north 430
	)
	
	(method (init)
		(proc958_0 128 423 424 741 426 482 442 483 481 480 831)
		(proc958_0 132 52 483 480 481 482)
		(Load rsSCRIPT 939)
		(gEgo
			init:
			normalize: (if (== global123 5) 426 else 831)
			setScale: Scaler 110 0 190 0
		)
		(if (== global123 5)
			(self setRegions: 94)
			(global2 obstacles: (List new:))
			((ScriptID 2480 0) doit: (global2 obstacles?))
		else
			(self setRegions: 90)
		)
		(switch gGNumber
			(north
				(gEgo x: 138 y: 121)
				(global2 setScript: sEnterNorth)
			)
			(740
				(global2 setScript: sChaseSequence)
				(lump init:)
				(rexMouth approachVerbs: 4 1 init:)
				(gEgo hide:)
				(steve init: hide:)
			)
			(else 
				(gEgo posn: 160 160)
				(gGame handsOn:)
			)
		)
		(super init:)
		(signButton approachVerbs: 1 4 init:)
		(painting init:)
		(dino init: setOnMeCheck: 1 8192)
		(rexMouth approachVerbs: 4 1)
		(rex approachVerbs: 4 1 setOnMeCheck: 1 16384 32 init:)
		(if (not (gEgo has: 18)) (bone init: stopUpd:))
		(dinoBones approachVerbs: 4 1 init:)
		(gNarrator x: 10 y: 10)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(
				(and
					(== (global2 script?) sChaseSequence)
					(proc0_1 gEgo 2)
				)
				((self script?) cue:)
			)
			(script)
			((proc0_1 gEgo 2) (self setScript: sExitNorth))
			((proc0_1 gEgo 4) (self setScript: sAroundTRexCCW))
			((proc0_1 gEgo 32) (self setScript: sAroundTRexCW))
			((proc0_1 gEgo 16) (self setScript: sAroundDinoCCW))
			((proc0_1 gEgo 8) (self setScript: sAroundDinoCW))
		)
	)
	
	(method (dispose)
		(if local1 (sWrapMusic dispose: 1))
		(DisposeScript 2480)
		(super dispose:)
	)
	
	(method (handleEvent pEvent)
		(if
			(and
				(gNarrator modeless?)
				(or
					(and
						(== (pEvent type?) evKEYBOARD)
						(proc999_5 (pEvent message?) 27 13)
					)
					(and
						(== (pEvent type?) evMOUSEBUTTON)
						(not (pEvent modifiers?))
					)
				)
			)
			(pEvent claimed: 1)
			(if gDialog (gDialog dispose:))
		else
			(super handleEvent: pEvent)
		)
	)
	
	(method (notify)
		(if (== global123 5)
			(if (global2 script?)
				((global2 script?) next: sCaughtYou)
			else
				(global2 setScript: sCaughtYou)
			)
		)
	)
)

(instance sCaughtYou of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(oriley
					init:
					view: 423
					posn: 137 122
					setPri: -1
					setCycle: Walk
					setScale: Scaler 100 100 190 0
				)
				(= cycles 1)
			)
			(1
				(gGame handsOff:)
				(= cycles 1)
			)
			(2
				(gEgo setMotion: PolyPath 219 179 self)
			)
			(3
				(gWrapSound number: 3 loop: 1 flags: 1 play:)
				(oriley setMotion: MoveTo 169 124 self)
			)
			(4
				(oriley setMotion: PolyPath 196 173 self)
			)
			(5
				(proc0_5 gEgo oriley)
				(= cycles 3)
			)
			(6
				(oriley view: 424 cel: 0 setCycle: End self)
			)
			(7
				(noise number: 80 flags: 1 loop: 1 play:)
				(gEgo view: 858 setCycle: End self)
			)
			(8
				(= global145 0)
				(global2 newRoom: 99)
			)
		)
	)
)

(instance sChaseSequence of Script
	(properties)
	
	(method (doit)
		(if (and (== (self state?) 12) local0)
			(gGame handsOff:)
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc958_0 128 482)
				(Load rsSOUND 480)
				(gGameMusic2 stop:)
				(gWrapSound number: 482 loop: -1 flags: 1 play:)
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(rexMouth setCycle: End self)
				(noise number: 480 flags: 1 play:)
			)
			(2
				(if (proc0_2 46)
					(self setScript: sRatsFall self)
				else
					(= cycles 1)
				)
			)
			(3
				(lump cel: 0 setCycle: End self)
			)
			(4
				(gEgo
					show:
					view: 482
					setLoop: 4
					yStep: 8
					cel: 0
					posn: 184 141
					setPri: 12
					setScale: Scaler 100 100 190 0
					setCycle: Fwd
					setMotion: MoveTo 179 160 self
				)
			)
			(5
				(gEgo setLoop: 0 setCycle: End self)
				(= gEgoMoveSpeed (gEgo moveSpeed?))
			)
			(6
				(gEgo
					view: 831
					setLoop: 6
					yStep: 2
					posn: 171 158
					setCycle: Rev
					setSpeed: 10
					setMotion: MoveTo 165 162 self
				)
			)
			(7
				(lump cel: 0 setCycle: End self)
			)
			(8
				(steve show: setCel: 0 yStep: 8 setLoop: 1)
				(= cycles 1)
			)
			(9
				(steve setMotion: MoveTo 179 160 self)
			)
			(10
				(gEgo
					normalize: 426
					setScale: Scaler 110 0 190 0
					setSpeed: gEgoMoveSpeed
				)
				(signButton
					approachX: 296
					approachY: 189
					approachVerbs: 4 1
				)
				(steve posn: 179 160 setCycle: End self)
			)
			(11
				(steve setLoop: 1 setMotion: MoveTo 160 158 self)
			)
			(12
				(gGame handsOn:)
				(= seconds 20)
			)
			(13
				(gGame handsOff:)
				(oriley init: setScale: Scaler 100 100 190 0 hide:)
				(if local0
					(self setScript: sOrileyCaught)
				else
					(self setScript: sKillThem)
				)
			)
		)
	)
)

(instance sOrileyCaught of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(== (localSound number?) 483)
				(== (localSound prevSignal?) -1)
				(== (self state?) 15)
			)
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame points: 1 138)
				(rexMouth setCycle: End self)
			)
			(1
				(lump cel: 0 setCycle: End self)
			)
			(2
				(rexMouth setCycle: End self)
				(noise number: 480 flags: 1 play:)
			)
			(3 (oriley show:) (= cycles 1))
			(4
				(gWrapSound stop:)
				(oriley
					setLoop: 0
					setPri: 15
					setMotion: MoveTo 182 120 self
				)
				(rexMouth setCycle: End self)
			)
			(5
				(= local1 1)
				(WrapMusic pause: 1)
				(sWrapMusic init: 0 1481 483)
				0
			)
			(6
				(rexMouth setCycle: Beg self)
				(oriley setCycle: End)
			)
			(7
				(oriley setCycle: CT 3 -1 self)
			)
			(8 (oriley setCycle: End self))
			(9
				(= seconds 2)
				(steve ignoreActors: 1)
			)
			(10
				(gEgo setMotion: PolyPath 169 141 self)
			)
			(11 (= seconds 1))
			(12
				(gEgo
					view: 483
					loop: 13
					cel: 0
					setScale: 135
					setCycle: End self
				)
			)
			(13 (= seconds 3))
			(14
				((ScriptID 22 0) doit: 2)
				(gGame points: 1 151)
				(gOldCast eachElementDo: #hide)
				(global2 drawPic: 485)
				(= seconds 5)
			)
			(15 0)
			(16
				(WrapMusic pause: 0)
				(global2 newRoom: 26)
			)
		)
	)
)

(instance sKillThem of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(gWrapSound number: 3 loop: 1 flags: 1 play:)
				(= cycles 1)
			)
			(1
				(lump cel: 0 setCycle: End self)
			)
			(2
				(oriley setLoop: 1 posn: 186 141 show:)
				(= cycles 1)
			)
			(3 (oriley setCycle: End self))
			(4
				(noise number: 52 flags: 1 loop: 1 play:)
				(oriley setLoop: 5 posn: 177 143 setCycle: End self)
			)
			(5
				(steve
					view: 483
					loop: 10
					cel: 0
					posn: 125 169
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(6
				(gWrapSound fade:)
				(cond 
					((<= (gEgo x?) 131) (oriley setLoop: 5))
					((and (<= (gEgo x?) 172) (>= (gEgo y?) 161)) (oriley setLoop: 5))
					((and (> (gEgo x?) 172) (>= (gEgo y?) 159)) (oriley setLoop: 6))
					((> (gEgo x?) 320) (oriley setLoop: 6))
					((and (>= (gEgo x?) 180) (<= (gEgo y?) 141)) (oriley setLoop: 7))
					(else (oriley setLoop: 8))
				)
				(oriley setCycle: End self)
				(noise number: 52 flags: 1 loop: 1 play:)
			)
			(7
				(= global145 10)
				(global2 newRoom: 99)
			)
		)
	)
)

(instance sGetDinoBoneFromInset of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(inBone dispose:)
				(bone dispose:)
				(= cycles 1)
			)
			(1
				(gGame points: 1 139)
				(gEgo get: 18)
				((ScriptID 21 0) doit: 787)
				(self dispose:)
			)
		)
	)
)

(instance sGetDinoBone of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(gEgo
					view: 481
					setLoop: 0
					cel: 0
					setScale: Scaler 100 100 190 0
					setCycle: CT 2 1 self
				)
				(bone dispose:)
			)
			(2 (gEgo setCycle: End self))
			(3
				(gEgo view: 831 loop: 7)
				(= cycles 1)
			)
			(4
				(gGame handsOn:)
				(gEgo
					setScale: Scaler 110 0 190 0
					normalize: (if (== global123 5) 426 else 831)
					get: 18
				)
				((ScriptID 21 0) doit: 787)
				(gGame points: 1 139)
				(self dispose:)
			)
		)
	)
)

(instance sRexTalks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gUser canControl: 0)
				(gGame setCursor: global21)
				(gEgo setHeading: 0)
				(rexMouth init:)
				(= seconds 2)
			)
			(1
				(gGame points: 1 138)
				(rexMouth setCycle: RandCycle)
				(gNarrator modeless: 1)
				(gEgo setMotion: PolyPath 255 184 self)
			)
			(2
				(noise number: 480 play: self)
			)
			(3
				(rexMouth setCycle: Osc)
				(gLb2Messager say: 5 4 2 0 self)
			)
			(4
				(gNarrator modeless: 0 dispose: 1)
				(rexMouth stopUpd: setCycle: 0 cel: 0)
				(= cycles 5)
			)
			(5
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sRatsFall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (rat1 init:) (= cycles 1))
			(1
				(rat2 init:)
				(rat1 setLoop: 1 yStep: 7 setMotion: MoveTo 185 157 self)
			)
			(2
				(rat2 setLoop: 1 yStep: 7 setMotion: MoveTo 186 152 self)
				(rat1
					setLoop: 8
					cel: 1
					posn: 175 155
					setMotion: MoveTo 219 200 self
				)
			)
			(3 0)
			(4
				(rat1
					setLoop: 1
					posn: 204 93
					setMotion: MoveTo 185 157 self
				)
				(rat2
					setLoop: 8
					cel: 1
					posn: 171 160
					setMotion: MoveTo 228 200 self
				)
			)
			(5 0)
			(6
				(rat1
					setLoop: 8
					cel: 1
					posn: 175 155
					setMotion: MoveTo 219 200 self
				)
			)
			(7 (= seconds 1))
			(8
				(rat1 dispose:)
				(rat2 dispose:)
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
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(gEgo setMotion: PolyPath 174 125 self)
			)
			(2
				(gGame handsOn:)
				(self dispose:)
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
				(= cycles 1)
			)
			(1
				(gEgo setMotion: PolyPath 138 121 self)
			)
			(2
				(if
					(and
						(== global123 3)
						(proc0_10 -20222 1)
						(not (proc0_2 72))
					)
					(global2 newRoom: 435)
				else
					(global2 newRoom: (global2 north?))
				)
			)
		)
	)
)

(instance sAroundTRexCCW of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 3)
			)
			(1
				(gEgo setMotion: PolyPath 320 110 self)
			)
			(2
				(gEgo setMotion: PolyPath 220 119 self)
			)
			(3
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sAroundTRexCW of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 3)
			)
			(1
				(gEgo setMotion: PolyPath 320 110 self)
			)
			(2
				(gEgo setMotion: PolyPath 234 181 self)
			)
			(3
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sAroundDinoCCW of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 3)
			)
			(1
				(gEgo setPri: 15 setMotion: PolyPath 145 250 self)
			)
			(2
				(gEgo setMotion: PolyPath 234 181 self)
			)
			(3
				(gEgo setPri: -1)
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sAroundDinoCW of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 3)
			)
			(1
				(gEgo setMotion: PolyPath 225 250 self)
			)
			(2
				(gEgo setPri: 15 setMotion: PolyPath 9 176 self)
			)
			(3
				(gEgo setPri: -1)
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLookBones of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gLb2Messager say: 7 1 0 0 self)
			)
			(1
				(global2 setInset: inBone)
				(self dispose:)
			)
		)
	)
)

(instance rexMouth of Prop
	(properties
		x 230
		y 59
		noun 3
		approachX 160
		approachY 160
		view 482
		loop 2
		priority 12
		signal $0011
	)
)

(instance bone of View
	(properties
		x 32
		y 128
		view 481
		loop 1
		signal $4000
	)
	
	(method (doVerb theVerb)
		(dinoBones doVerb: &rest)
	)
)

(instance dinoBones of Feature
	(properties
		x 1
		y 140
		noun 7
		nsTop 128
		nsLeft 20
		nsBottom 145
		nsRight 65
		sightAngle 40
		approachX 56
		approachY 152
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(cond 
					((gEgo has: 18) (gLb2Messager say: 7 1 1))
					((== (global2 script?) sChaseSequence) (gLb2Messager say: 9 1 5))
					(else (global2 setScript: sLookBones))
				)
			)
			(4
				(cond 
					((gEgo has: 18) (gLb2Messager say: 7 4 1))
					((== (global2 script?) sChaseSequence) (gLb2Messager say: 9 4 5))
					(else (global2 setScript: sGetDinoBone))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance inBone of Inset
	(properties
		view 480
		x 2
		y 121
		disposeNotOnMe 1
		modNum 15
		noun 10
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(global2 setScript: sGetDinoBoneFromInset)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance oriley of Actor
	(properties
		x 191
		y 113
		view 483
		cel 3
		priority 12
		signal $0010
	)
)

(instance steve of Actor
	(properties
		x 185
		y 142
		noun 8
		view 482
		loop 1
		priority 12
		signal $0010
	)
)

(instance lump of Prop
	(properties
		x 258
		y 75
		noun 2
		view 480
		loop 1
		priority 12
		signal $0010
	)
)

(instance rat1 of Actor
	(properties
		x 204
		y 93
		yStep 4
		view 741
		loop 1
		priority 12
		signal $4010
		xStep 4
	)
)

(instance rat2 of Actor
	(properties
		x 200
		y 90
		yStep 4
		view 741
		loop 1
		priority 12
		signal $4010
		xStep 4
	)
)

(instance signButton of Feature
	(properties
		x 296
		y 148
		noun 5
		nsTop 143
		nsLeft 290
		nsBottom 154
		nsRight 303
		sightAngle 40
		approachX 258
		approachY 181
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== (global2 script?) sChaseSequence)
					(= local0 1)
				else
					(global2 setScript: sRexTalks)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance painting of Feature
	(properties
		x 71
		y 90
		noun 4
		nsTop 53
		nsLeft 18
		nsBottom 128
		nsRight 124
		sightAngle 40
	)
)

(instance dino of Feature
	(properties
		y 100
		noun 6
	)
)

(instance rex of Feature
	(properties
		x 277
		y 149
		noun 2
		approachX 160
		approachY 160
	)
)

(instance Rex of Narrator
	(properties
		x 10
		y 10
		talkWidth 150
		modeless 1
		back 15
	)
	
	(method (init)
		(= font gFont)
		(super init: &rest)
	)
)

(instance noise of Sound
	(properties)
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
