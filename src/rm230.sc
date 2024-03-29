;;; Sierra Script 1.0 - (do not remove this comment)
(script# 230)
(include sci.sh)
(use Main)
(use LBRoom)
(use ExitFeature)
(use Inset)
(use Blink)
(use Scaler)
(use RandCycle)
(use PolyPath)
(use Polygon)
(use CueObj)
(use MoveFwd)
(use n958)
(use Cycle)
(use InvI)
(use View)
(use Obj)

(public
	rm230 0
	Laura 2
	Crodfoller 7
	ManWriting 37
)

(local
	local0 =  1
	local1 =  1
	local2 =  1
	local3 =  1
)
(instance rm230 of LBRoom
	(properties
		noun 18
		picture 230
		south 210
		vanishingX 150
		vanishingY 90
	)
	
	(method (init)
		(proc958_0 128 231 232 233 238 1231 1230 830 829)
		(Load rsSOUND 210)
		(gEgo
			signal: 4096
			init:
			viewer: (if (== gGNumber 235) 0 else checkScaling)
			normalize: 830
			setScale: Scaler 190 40 190 90
		)
		(switch gGNumber
			(south
				(global2 setScript: sEnterNorth)
			)
			(26
				(gEgo x: 160 y: 160)
				((Inv at: 2) owner: gEgo)
				(proc0_5 gEgo crod)
				(self setScript: sDoTalking)
			)
			(235
				(gEgo
					view: 231
					setLoop: 0
					cel: 10
					setPri: 11
					x: 211
					y: 164
					setScale: Scaler 100 100 91 90
					setCycle: 0
				)
				(gLb2WH addToFront: global2)
				(gLb2DH addToFront: global2)
				(gGame handsOn:)
			)
			(else 
				(gGame handsOn:)
				(gEgo posn: 160 160)
			)
		)
		(super init:)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						319
						0
						319
						189
						313
						189
						313
						164
						254
						164
						242
						158
						219
						158
						207
						165
						142
						158
						142
						148
						113
						148
						103
						158
						45
						158
						5
						167
						5
						189
						0
						189
						0
						0
					yourself:
				)
		)
		(gWrapSound number: 210 loop: -1 flags: 1 play:)
		(personS init: cycleSpeed: 10 setCycle: RandCycle)
		(personT init: cycleSpeed: 10 setCycle: RandCycle)
		(person1 init: setScript: sMoveIt)
		(person2 init: setScript: sMoveIt2)
		(gentsDoor approachVerbs: 1 4 init:)
		(crod
			approachVerbs: 1 4 2 6
			setScript: sTypeAwayCrod
			init:
		)
		(trashcan init:)
		(blotter init:)
		(windowA init:)
		(window1 init:)
		(notice approachVerbs: 4 init:)
		(aBulletin approachVerbs: 4 init:)
		(herDesk setOnMeCheck: 1 16384 init:)
		(chair init:)
		(hisDesk setOnMeCheck: 1 8192 init:)
		(if (!= gGNumber 235)
			(southExitFeature init:)
		else
			(gentsDoor approachVerbs: 0)
			(crod approachVerbs: 0)
			(notice approachVerbs: 0)
			(aBulletin approachVerbs: 0)
		)
		((ScriptID 1881 2) x: 12 y: 95 textX: 120 textY: 0)
		((ScriptID 1896 7) x: 220 y: 80)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((proc0_1 gEgo 2) (global2 setScript: sExitSouth))
		)
	)
	
	(method (dispose)
		(gWrapSound fade:)
		(gLb2WH delete: global2)
		(gLb2DH delete: global2)
		(DisposeScript 941)
		(super dispose:)
	)
	
	(method (handleEvent pEvent)
		(cond 
			(inset (inset handleEvent: pEvent))
			(
				(and
					(& (pEvent type?) evJOYSTICK)
					(== (gIconBar curIcon?) (gIconBar walkIconItem?))
					(!= (pEvent message?) JOY_NULL)
				)
				(pEvent claimed: 1)
				(global2 setScript: sStandUp)
			)
			((& (pEvent type?) evMOVE) (super handleEvent: pEvent))
			(else (super handleEvent: pEvent))
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(global2 setScript: sStandUp)
			)
			(else 
				(super doVerb: theVerb &rest)
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
				(gEgo
					x: 160
					y: 290
					setHeading: 1
					setMotion: MoveTo 160 180 self
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
				(gEgo setHeading: 180 setMotion: MoveFwd 80 self)
			)
			(2 (global2 newRoom: 210))
		)
	)
)

(instance sDoTalking of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				((ScriptID 21 0) doit: 261)
				(= cycles 4)
			)
			(1
				(crod
					loop: 1
					posn: 273 152
					setScript: 0
					setCycle: End self
				)
			)
			(2
				(gLb2Messager say: 1 0 0 0 self)
			)
			(3
				(gGame handsOn:)
				(crod setScript: sTypeAwayCrod)
				(= cycles 2)
			)
			(4 (self dispose:))
		)
	)
)

(instance sCopyProBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(crod setScript: 0)
				(gGame setCursor: 0)
				(= cycles 5)
			)
			(1
				(crod
					loop: 1
					posn: 273 152
					setScript: 0
					setCycle: End self
				)
			)
			(2 (crod setCycle: Beg self))
			(3
				(gGame handsOn:)
				(crod setScript: sTypeAwayCrod)
				(self dispose:)
			)
		)
	)
)

(instance sSitAtDesk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(if (proc0_2 30)
					(gGame handsOn:)
					(self dispose:)
				else
					(= cycles 1)
				)
			)
			(2
				(gLb2WH addToFront: global2)
				(gLb2DH addToFront: global2)
				(if (proc0_2 30)
					(= cycles 1)
				else
					(gEgo setMotion: PolyPath 170 161 self)
				)
			)
			(3
				(gEgo
					view: 231
					setLoop: 0
					cel: 0
					posn: 211 164
					setPri: 11
					setScale: Scaler 100 100 91 90
					setCycle: End self
				)
				(proc0_3 30)
			)
			(4
				(southExitFeature dispose:)
				(gentsDoor approachVerbs: 0)
				(crod approachVerbs: 0)
				(notice approachVerbs: 0)
				(aBulletin approachVerbs: 0)
				(= seconds 1)
			)
			(5
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sStandUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= seconds 1)
			)
			(1
				(if (proc0_2 30)
					(= cycles 1)
				else
					(gGame handsOn:)
					(self dispose:)
				)
			)
			(2
				(gEgo
					view: 231
					setLoop: 0
					posn: 211 164
					setCycle: Beg self
				)
			)
			(3
				(gEgo
					loop: 6
					posn: 173 164
					setScale: Scaler 190 40 190 90
					normalize: 830
				)
				(gLb2WH delete: global2)
				(gLb2DH delete: global2)
				(southExitFeature init:)
				(proc0_4 30)
				(gentsDoor approachVerbs: 1 4)
				(crod approachVerbs: 1 4 2 6)
				(notice approachVerbs: 4)
				(aBulletin approachVerbs: 4)
				(= cycles 1)
			)
			(4
				(gGame handsOn:)
				(gEgo viewer: checkScaling)
				(self dispose:)
			)
		)
	)
)

(instance sLookInset of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(gEgo setScript: sSitAtDesk self)
			)
			(2
				(gLb2Messager say: 24 1 0 0 self)
			)
			(3 (global2 newRoom: 235))
		)
	)
)

(instance sDigInTrash of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(gEgo setScript: sSitAtDesk self)
			)
			(2
				(gEgo view: 231 loop: 2 posn: 148 161 setCycle: End self)
			)
			(3
				(gGame handsOn:)
				(if
				(and (not (gEgo has: 4)) (not (gEgo has: 22)))
					(global2 setInset: inBaseball self)
					(gLb2Messager say: 3 4 5)
				else
					(gLb2Messager say: 3 4 6 0 self)
				)
			)
			(4
				(gGame handsOff:)
				(= cycles 1)
			)
			(5 (gEgo setCycle: Beg self))
			(6
				(gEgo setLoop: 0 cel: 10 posn: 211 164)
				(= cycles 4)
			)
			(7
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGentsDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: sStandUp self)
			)
			(1
				(gGame handsOff:)
				(gEgo setMotion: PolyPath 41 155 self)
			)
			(2
				(crod loop: 3 setScript: 0 setCycle: End self)
			)
			(3
				(crod posn: 277 153 cel: 0)
				(gLb2Messager say: 2 4 0 0 self)
			)
			(4
				(gGame handsOn:)
				(crod setScript: sTypeAwayCrod)
				(self dispose:)
			)
		)
	)
)

(instance sTalkCrod of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 2)
			)
			(1
				(crod
					loop: 1
					posn: 273 152
					setScript: 0
					setCycle: End self
				)
			)
			(2
				(if local1
					(= local1 0)
					(gLb2Messager say: 12 2 11 0 self)
				else
					(gLb2Messager say: 12 2 12 0 self)
				)
			)
			(3 (crod setCycle: Beg self))
			(4
				(gGame handsOn:)
				(crod setScript: sTypeAwayCrod)
				(self dispose:)
			)
		)
	)
)

(instance sAskCrod of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(crod
					loop: 1
					posn: 273 152
					setScript: 0
					setCycle: End self
				)
			)
			(1
				(switch (= temp0 (global2 setInset: (ScriptID 20 0)))
					(1026
						(gLb2Messager say: 12 6 41 0 self)
					)
					(257
						(gLb2Messager say: 12 6 19 0 self)
					)
					(773
						(gLb2Messager say: 12 6 35 0 self)
					)
					(273
						(gLb2Messager say: 12 6 31 0 self)
					)
					(1027
						((ScriptID 21 0) doit: 264)
						((ScriptID 21 0) doit: 520)
						((ScriptID 21 0) doit: 260)
						((ScriptID 21 1) doit: 518)
						(gLb2Messager say: 12 6 42 0 self)
					)
					(259
						(gLb2Messager say: 12 6 21 0 self)
					)
					(770
						(gLb2Messager say: 12 6 37 0 self)
					)
					(269
						(gLb2Messager say: 12 6 28 0 self)
					)
					(769
						(gLb2Messager say: 12 6 36 0 self)
					)
					(261
						(gLb2Messager say: 12 6 45 0 self)
					)
					(780
						(gLb2Messager say: 12 6 39 0 self)
					)
					(516
						(gLb2Messager say: 12 6 16 0 self)
					)
					(1028
						(gLb2Messager say: 12 6 44 0 self)
					)
					(518
						((ScriptID 21 0) doit: 264)
						((ScriptID 21 0) doit: 520)
						((ScriptID 21 1) doit: 518)
						(gLb2Messager say: 12 6 47 0 self)
					)
					(265
						(gLb2Messager say: 12 6 48 0 self)
					)
					(774
						(gLb2Messager say: 12 6 33 0 self)
					)
					(262
						(gLb2Messager say: 12 6 23 0 self)
					)
					(515
						(gLb2Messager say: 12 6 15 0 self)
					)
					(517
						((ScriptID 21 0) doit: 259)
						((ScriptID 21 0) doit: 258)
						(gLb2Messager say: 12 6 17 0 self)
					)
					(270
						(gLb2Messager say: 12 6 29 0 self)
					)
					(519
						(gLb2Messager say: 12 6 43 0 self)
					)
					(771
						(gLb2Messager say: 12 6 32 0 self)
					)
					(513
						((ScriptID 21 0) doit: 257)
						(gLb2Messager say: 12 6 13 0 self)
					)
					(260
						(gLb2Messager say: 12 6 22 0 self)
					)
					(775
						(gLb2Messager say: 12 6 34 0 self)
					)
					(258
						(gLb2Messager say: 12 6 20 0 self)
					)
					(514
						(gLb2Messager say: 12 6 14 0 self)
					)
					(772
						(gLb2Messager say: 12 6 38 0 self)
					)
					(520
						(gLb2Messager say: 12 6 18 0 self)
					)
					(263
						(gLb2Messager say: 12 6 24 0 self)
					)
					(271
						((ScriptID 21 0) doit: 258)
						(gLb2Messager say: 12 6 30 0 self)
					)
					(266
						(gLb2Messager say: 12 6 27 0 self)
					)
					(264
						(gLb2Messager say: 12 6 25 0 self)
					)
					(else 
						(if (== -1 temp0)
							(= cycles 1)
						else
							(gLb2Messager say: 12 6 26 0 self)
						)
					)
				)
			)
			(2 (crod setCycle: Beg self))
			(3
				(gGame handsOn:)
				(crod setScript: sTypeAwayCrod)
				(self dispose:)
			)
		)
	)
)

(instance sTypeAwayCrod of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(crod posn: 273 152 setLoop: 4 setCycle: RandCycle)
				(= seconds (Random 5 10))
			)
			(1 (= state -1) (= cycles 1))
		)
	)
)

(instance sMoveIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(person1
					setLoop: 1
					setCycle: Walk
					posn: 330 110
					setMotion: MoveTo 228 117 self
				)
			)
			(1 (= seconds (Random 4 8)))
			(2
				(person1 setLoop: 0 setMotion: MoveTo 330 117 self)
			)
			(3 (= seconds (Random 8 12)))
			(4 (= state -1) (= cycles 1))
		)
	)
)

(instance sMoveIt2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 4 9)))
			(1
				(person2
					setLoop: 3
					posn: 330 108
					setCycle: Walk
					setMotion: MoveTo 241 108 self
				)
			)
			(2
				(person2 setLoop: 4 cycleSpeed: 8 setCycle: RandCycle)
				(= seconds (Random 4 7))
			)
			(3
				(person2 setCycle: 0)
				(= seconds (Random 1 3))
			)
			(4
				(person2
					setLoop: 2
					posn: 241 108
					cycleSpeed: 6
					setCycle: Walk
					setMotion: MoveTo 330 108 self
				)
			)
			(5 (= seconds (Random 4 8)))
			(6 (= state -1) (= cycles 1))
		)
	)
)

(instance crod of Prop
	(properties
		x 273
		y 152
		noun 12
		approachX 242
		approachY 164
		view 232
		loop 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(global2 setScript: sTalkCrod)
			)
			(6
				(global2 setScript: sAskCrod)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance personS of Prop
	(properties
		x 152
		y 139
		noun 13
		view 233
		loop 5
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if local2
					(= local2 0)
					(gLb2Messager say: 13 2 11)
				else
					(gLb2Messager say: 13 2 12)
				)
			)
			(6 (gLb2Messager say: 13 6 46))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance personT of Prop
	(properties
		x 85
		y 154
		noun 14
		view 233
		loop 6
		signal $1000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6 (gLb2Messager say: 13 6 46))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance person1 of Actor
	(properties
		x 330
		y 110
		noun 15
		view 233
		loop 1
	)
)

(instance person2 of Actor
	(properties
		x 330
		y 118
		noun 15
		view 233
		loop 3
		priority 6
		signal $0010
	)
)

(instance inBaseball of Inset
	(properties
		view 238
		x 144
		y 121
		disposeNotOnMe 1
		modNum 15
		noun 1
	)
	
	(method (init)
		(gLb2WH delete: global2)
		(gLb2DH delete: global2)
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(gLb2WH addToFront: global2)
		(gLb2DH addToFront: global2)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(gLb2Messager say: 1 1 0 0 0 15)
			)
			(4
				((ScriptID 21 0) doit: 773)
				(inBaseball dispose:)
				(gEgo get: 4)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance checkScaling of Code
	(properties)
	
	(method (doit)
		(return
			(cond 
				((and (> (gEgo y?) 165) local3)
					(= local3 0)
					(gEgo view: 829 setScale: Scaler 100 100 91 90)
				)
				((gEgo edgeHit?) (gEgo actions: 0))
				((and (<= (gEgo y?) 165) (not local3))
					(= local3 1)
					(gEgo view: 830 setScale: Scaler 190 40 190 90)
				)
				(else 0)
			)
		)
	)
)

(instance trashcan of Feature
	(properties
		x 164
		y 146
		noun 3
		nsTop 140
		nsLeft 157
		nsBottom 152
		nsRight 172
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(global2 setScript: sDigInTrash)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance blotter of Feature
	(properties
		x 194
		y 117
		noun 4
		nsTop 115
		nsLeft 180
		nsBottom 120
		nsRight 208
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(global2 setScript: sLookInset)
			)
			(1
				(global2 setScript: sLookInset)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance gentsDoor of Feature
	(properties
		x 19
		y 115
		noun 2
		nsTop 78
		nsBottom 152
		nsRight 39
		sightAngle 40
		approachX 41
		approachY 155
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(global2 setScript: sGentsDoor)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance windowA of Feature
	(properties
		x 123
		y 95
		noun 5
		nsTop 85
		nsLeft 109
		nsBottom 105
		nsRight 138
		sightAngle 40
	)
)

(instance window1 of Feature
	(properties
		x 176
		y 93
		noun 5
		nsTop 85
		nsLeft 167
		nsBottom 101
		nsRight 185
		sightAngle 40
	)
)

(instance aBulletin of Feature
	(properties
		x 61
		y 98
		noun 6
		nsTop 82
		nsLeft 47
		nsBottom 114
		nsRight 76
		sightAngle 40
		approachX 83
		approachY 161
	)
	
	(method (doVerb theVerb)
		(notice doVerb: theVerb &rest)
	)
)

(instance notice of Feature
	(properties
		x 56
		y 99
		noun 7
		nsTop 85
		nsLeft 52
		nsBottom 94
		nsRight 60
		approachX 83
		approachY 161
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (proc0_2 30)
					(gLb2Messager say: 7 1 40)
				else
					(switch local0
						(1
							(gLb2Messager say: 7 1 11)
							(++ local0)
						)
						(2
							(gLb2Messager say: 7 1 8)
							(++ local0)
						)
						(3
							(gLb2Messager say: 7 1 9)
							(++ local0)
						)
						(else 
							(gLb2Messager say: 7 1 10)
							(= local0 1)
						)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance chair of Feature
	(properties
		x 195
		y 138
		noun 24
		nsTop 119
		nsLeft 179
		nsBottom 158
		nsRight 210
	)
	
	(method (doVerb theVerb)
		(herDesk doVerb: theVerb &rest)
	)
)

(instance herDesk of Feature
	(properties
		x 195
		y 145
		noun 24
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(global2 setScript: sLookInset)
			)
			(1
				(global2 setScript: sLookInset)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance hisDesk of Feature
	(properties
		x 230
		y 145
		noun 17
	)
)

(instance southExitFeature of ExitFeature
	(properties
		nsTop 184
		nsBottom 189
		nsRight 320
		cursor 11
		exitDir 3
		noun 16
	)
)

(instance Laura of Talker
	(properties
		x 12
		y 87
		view 1231
		loop 3
		talkWidth 160
		back 15
		textX 120
	)
	
	(method (init)
		(= font gFont)
		(super init: tLauraBust 0 tLauraMouth &rest)
	)
)

(instance tLauraMouth of Prop
	(properties
		nsTop 49
		nsLeft 41
		view 1231
	)
)

(instance tLauraBust of Prop
	(properties
		nsTop 10
		nsLeft 10
		view 1231
		loop 1
	)
)

(instance Crodfoller of Talker
	(properties
		x 220
		y 60
		view 1230
		loop 3
		talkWidth 150
		back 15
		textX -205
	)
	
	(method (init)
		(= font gFont)
		(super init: tCrodBust tCrodEyes tCrodMouth &rest)
	)
)

(instance tCrodMouth of Prop
	(properties
		nsTop 33
		nsLeft 17
		view 1230
	)
)

(instance tCrodEyes of Prop
	(properties
		nsTop 25
		nsLeft 21
		view 1230
		loop 2
	)
)

(instance tCrodBust of Prop
	(properties
		view 1230
		loop 1
	)
)

(instance MiscPeople of Narrator
	(properties
		x 10
		y 10
		talkWidth 150
		back 15
	)
	
	(method (init)
		(= font gFont)
		(super init: &rest)
	)
)

(instance MidForground of Narrator
	(properties
		x 100
		y 50
		talkWidth 150
		back 15
	)
	
	(method (init)
		(= font gFont)
		(super init: &rest)
	)
)

(instance ManWriting of Narrator
	(properties
		x 50
		y 50
		talkWidth 150
		back 15
	)
	
	(method (init)
		(= font gFont)
		(super init: &rest)
	)
)
