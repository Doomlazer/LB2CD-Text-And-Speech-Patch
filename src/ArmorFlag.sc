;;; Sierra Script 1.0 - (do not remove this comment)
(script# 448)
(include sci.sh)
(use Main)
(use LbDoor)
(use LBRoom)
(use ExitFeature)
(use PursuitRgn)
(use PChase)
(use Scaler)
(use PolyPath)
(use CueObj)
(use MoveFwd)
(use n958)
(use Sound)
(use Cycle)
(use View)
(use Obj)

(public
	rm448 0
)

(local
	local0
)
(instance rm448 of LBRoom
	(properties
		noun 14
		picture 448
		north 450
		south 440
		west 460
		vanishingX 150
		vanishingY 35
	)
	
	(method (init)
		(proc958_0 128 449 440 424 423 448 426 831)
		(proc958_0 132 444 443 445)
		(gEgo
			init:
			normalize: (if (== global123 5) 426 else 831)
			setScale: Scaler 138 0 190 35
		)
		(if (== global123 5)
			(self setRegions: 94)
			(global2 obstacles: (List new:))
			((ScriptID 2448 0) doit: (global2 obstacles?))
		else
			(self setRegions: 90)
		)
		(switch gGNumber
			(north
				(gEgo x: 140 y: 111)
				(self setScript: sEnterNorth)
			)
			(south
				(gEgo x: 151 y: 162 edgeHit: 0 setHeading: 0)
				(self setScript: sEnterSouth)
			)
			(west
				(gEgo x: 85 y: 130 edgeHit: 0 setHeading: 90)
				(gGame handsOn:)
			)
			(else 
				(gEgo posn: 146 130)
				(gGame handsOn:)
			)
		)
		(super init:)
		(if (== global123 5)
			(if (or (proc0_2 39) (proc0_2 116))
				(chair setPri: 7 posn: 104 123)
				(transomWin cel: 3)
			)
			(chair init: stopUpd:)
		)
		(transomWin init: stopUpd:)
		(if (proc0_2 47)
			(westNoDoor init:)
		else
			(transomDoor init: approachVerbs: 4 1 8)
		)
		(flag4 init:)
		(flag5 init:)
		(flag6 init:)
		(flag7 init:)
		(armor4 init:)
		(armor7 init:)
		(armor8 init:)
		(rearDoorway init:)
		(southExitFeature init:)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((proc0_1 gEgo 2) (gEgo edgeHit: 4) (self newRoom: west))
			((proc0_1 gEgo 8) (self newRoom: north))
		)
	)
	
	(method (dispose)
		(DisposeScript 2448)
		(gLb2WH delete: global2)
		(gLb2DH delete: global2)
		(super dispose:)
	)
	
	(method (handleEvent pEvent)
		(cond 
			(
				(and
					(& (pEvent type?) evJOYSTICK)
					(== (gIconBar curIcon?) (gIconBar walkIconItem?))
					(!= (pEvent message?) JOY_NULL)
				)
				(pEvent claimed: 1)
				(global2 setScript: sOffChair)
			)
			((& (pEvent type?) evMOVE) (super handleEvent: pEvent))
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(global2 setScript: sOffChair)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (notify)
		(if (== global123 5)
			(if (global2 script?)
				(if (!= script sHeKills)
					((global2 script?) next: sHeKills)
				)
			else
				(global2 setScript: sHeKills)
			)
		)
	)
)

(instance sEnterWest of Script ; unused, kept to not need a heap patch
	(properties)
	
	(method (changeState newState)
;;;		(switch (= state newState)
;;;			(0
;;;				(gGame handsOff:)
;;;				(= cycles 1)
;;;			)
;;;			(1
;;;				(gEgo x: 230 y: 140 edgeHit: 0 setMotion: MoveFwd 20 self)
;;;			)
;;;			(2
;;;				(gGame handsOn:)
;;;				(self dispose:)
;;;			)
;;;		)
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
				(gEgo
					edgeHit: 0
					setHeading: 180
					setMotion: MoveFwd 10 self
				)
			)
			(2
				(if (and (not (proc0_2 39)) (== global123 5))
					((ScriptID 94 1) cue:)
				)
				(gGame handsOn:)
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
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(gEgo setMotion: MoveFwd 20 self)
			)
			(2
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sHeKills of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(if local0
					(self setScript: sOffChair self)
				else
					(= cycles 1)
				)
			)
			(2
				(gGame handsOff:)
				(= cycles 1)
			)
			(3
				(gEgo setMotion: PolyPath 149 128 self)
			)
			(4
				(gWrapSound number: 3 loop: 1 flags: 1 play:)
				(oriley init: setScale: Scaler 138 0 190 35)
				(= cycles 1)
			)
			(5
				(oriley setCycle: Walk setMotion: PChase gEgo 12 self)
			)
			(6
				(proc0_5 gEgo oriley)
				(= cycles 2)
			)
			(7
				(oriley view: 424 cel: 0 setCycle: End self)
			)
			(8
				(noise number: 80 flags: 1 play:)
				(gEgo view: 858 setCycle: End self)
			)
			(9
				(= global145 0)
				(global2 newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance sMoveChair of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(gEgo ignoreActors: 1 illegalBits: 0)
				(= cycles 1)
			)
			(2
				(if (proc0_1 gEgo 16)
					(gEgo setMotion: MoveTo 112 135 self)
				else
					(gEgo setMotion: PolyPath 112 135 self)
				)
			)
			(3
				(= local0 1)
				(gEgo
					view: 449
					setScale: Scaler 100 100 190 90
					loop: 0
					cel: 0
					posn: 112 133
					ignoreActors: 0
					illegalBits: -32768
					setCycle: End self
				)
				(chair
					ignoreActors: 1
					setLoop: 2
					moveSpeed: (+ (gEgo moveSpeed?) 4)
					setMotion: MoveTo 104 123 self
				)
				(noise number: 443 flags: 1 play:)
			)
			(4 0)
			(5
				(gEgo
					normalize: (if (== global123 5) 426 else 831)
					setScale: Scaler 138 0 190 35
					setMotion: MoveTo 121 122 self
				)
			)
			(6
				(gEgo
					view: 449
					loop: 1
					cel: 0
					setScale: Scaler 100 100 190 35
					posn: 107 124
					setPri: 9
					setCycle: End self
				)
				(chair setPri: 7 stopUpd:)
				(gLb2WH addToFront: global2)
				(gLb2DH addToFront: global2)
			)
			(7
				(gGame handsOn:)
				(transomDoor approachVerbs: 0)
				(proc0_3 116)
				(southExitFeature dispose:)
				(rearDoorway sightAngle: 26505)
				(armor4 sightAngle: 26505)
				(armor7 sightAngle: 26505)
				(armor8 sightAngle: 26505)
				(flag4 sightAngle: 26505)
				(flag5 sightAngle: 26505)
				(flag6 sightAngle: 26505)
				(flag7 sightAngle: 26505)
				(self dispose:)
			)
		)
	)
)

(instance sOnChair of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(gEgo ignoreActors: 1 illegalBits: 0)
				(= cycles 1)
			)
			(2
				(if (proc0_1 gEgo 16)
					(gEgo setMotion: MoveTo 121 122 self)
				else
					(gEgo setMotion: PolyPath 121 122 self)
				)
			)
			(3
				(gEgo
					view: 449
					loop: 1
					cel: 0
					posn: 107 124
					setPri: 9
					setScale: Scaler 100 100 190 35
					ignoreActors: 0
					illegalBits: -32768
					setCycle: End self
				)
			)
			(4
				(gGame handsOn:)
				(transomDoor approachVerbs: 0)
				(= local0 1)
				(southExitFeature dispose:)
				(gLb2WH addToFront: global2)
				(gLb2DH addToFront: global2)
				(rearDoorway sightAngle: 26505)
				(armor4 sightAngle: 26505)
				(armor7 sightAngle: 26505)
				(armor8 sightAngle: 26505)
				(flag4 sightAngle: 26505)
				(flag5 sightAngle: 26505)
				(flag6 sightAngle: 26505)
				(flag7 sightAngle: 26505)
				(self dispose:)
			)
		)
	)
)

(instance sOffChair of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(gEgo
					view: 449
					loop: 1
					cel: 8
					setScale: Scaler 100 100 190 35
					posn: 107 124
					setPri: 10
					setCycle: Beg self
				)
			)
			(2
				(gEgo
					normalize: (if (== global123 5) 426 else 831)
					posn: 121 122
					setScale: Scaler 138 0 190 35
				)
				(= local0 0)
				(gGame handsOn:)
				(transomDoor approachVerbs: 4 1 8)
				(southExitFeature init:)
				(gLb2WH delete: global2)
				(gLb2DH delete: global2)
				(rearDoorway sightAngle: 40)
				(armor4 sightAngle: 40)
				(armor7 sightAngle: 40)
				(armor8 sightAngle: 40)
				(flag4 sightAngle: 40)
				(flag5 sightAngle: 40)
				(flag6 sightAngle: 40)
				(flag7 sightAngle: 40)
				(self dispose:)
			)
		)
	)
)

(instance sOpenTransom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local0
					(= cycles 1)
				else
					(self setScript: sOnChair self)
				)
			)
			(1
				(gEgo
					loop: 2
					cel: 0
					posn: 104 112
					ignoreActors: 1
					illegalBits: 0
					setCycle: CT 6 1 self
				)
			)
			(2
				(gEgo setCycle: End self)
				(transomWin setCycle: End)
				(noise number: 445 flags: 1 play:)
			)
			(3
				(gEgo cel: 0)
				(transomWin stopUpd:)
				(= cycles 1)
			)
			(4
				(proc0_3 39)
				(PursuitRgn increaseTime:)
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sCloseTransom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local0
					(= cycles 1)
				else
					(self setScript: sOnChair self)
				)
			)
			(1
				(gEgo
					loop: 2
					cel: 9
					ignoreActors: 1
					illegalBits: 0
					posn: 104 112
					setCycle: CT 6 -1 self
				)
			)
			(2
				(gEgo setCycle: Beg self)
				(transomWin setCycle: Beg)
				(noise number: 445 flags: 1 play:)
			)
			(3
				(proc0_4 39)
				(transomWin stopUpd:)
				(PursuitRgn decreaseTime:)
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance oriley of Actor
	(properties
		x 171
		y 199
		view 423
	)
)

(instance chair of Actor
	(properties
		x 86
		y 133
		noun 3
		approachX 116
		approachY 121
		view 448
		loop 2
		signal $4000
		illegalBits $0000
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					(local0 (global2 setScript: sOffChair))
					((and (>= (chair x?) 104) (not local0)) (global2 setScript: sOnChair))
					((>= (chair x?) 104) (global2 setScript: sOffChair))
					(else (global2 setScript: sMoveChair))
				)
			)
			(3
				(cond 
					((and (>= (chair x?) 104) (not local0)) (global2 setScript: sOnChair))
					((>= (chair x?) 104) (global2 setScript: sOffChair))
					(else (global2 setScript: sMoveChair))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance transomDoor of LbDoor
	(properties
		x 87
		y 100
		z -26
		noun 5
		approachX 117
		approachY 122
		view 448
		priority 8
		signal $1000
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (not local0)
					(gLb2Messager say: 5 4)
					(if (!= global90 2)
						(noise number: 48 loop: 1 flags: 1 play:)
					)
				else
					(global2 setScript: sOffChair)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (createPoly)
		(super createPoly: 94 119 108 120 88 131 80 125)
	)
)

(instance transomWin of Prop
	(properties
		x 88
		y 65
		noun 4
		view 448
		loop 1
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((not (>= (chair x?) 104)) (gLb2Messager say: 4 4 1))
					((== (transomWin cel?) 0) (global2 setScript: sOpenTransom))
					(else (global2 setScript: sCloseTransom))
				)
			)
			(8
				(if (= local0 0)
					(gLb2Messager say: 4 8 0 0)
				else
					(gLb2Messager say: 4 8 2 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance westNoDoor of Feature
	(properties
		x 92
		y 101
		noun 15
		nsTop 79
		nsLeft 88
		nsBottom 123
		nsRight 96
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (gLb2Messager say: 15 1 3 0))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance armor4 of Feature
	(properties
		x 107
		y 89
		noun 7
		nsTop 76
		nsLeft 101
		nsBottom 122
		nsRight 113
		sightAngle 40
	)
)

(instance armor7 of Feature
	(properties
		x 192
		y 100
		noun 8
		nsTop 77
		nsLeft 185
		nsBottom 123
		nsRight 200
		sightAngle 40
	)
)

(instance armor8 of Feature
	(properties
		x 212
		y 103
		noun 9
		nsTop 72
		nsLeft 202
		nsBottom 134
		nsRight 222
		sightAngle 40
	)
)

(class ArmorFlag of Feature
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 40
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8 (gLb2Messager say: 1 8 0 0))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance flag4 of ArmorFlag
	(properties
		x 111
		y 51
		noun 10
		nsTop 43
		nsLeft 104
		nsBottom 60
		nsRight 119
	)
)

(instance flag5 of ArmorFlag
	(properties
		x 116
		y 67
		noun 11
		nsTop 61
		nsLeft 110
		nsBottom 74
		nsRight 122
	)
)

(instance flag6 of ArmorFlag
	(properties
		x 172
		y 66
		noun 12
		nsTop 59
		nsLeft 168
		nsBottom 74
		nsRight 177
	)
)

(instance flag7 of ArmorFlag
	(properties
		x 174
		y 50
		noun 13
		nsTop 43
		nsLeft 167
		nsBottom 58
		nsRight 181
	)
)

(instance rearDoorway of Feature
	(properties
		y 100
		noun 2
		nsTop 83
		nsLeft 126
		nsBottom 112
		nsRight 170
	)
)

(instance southExitFeature of ExitFeature
	(properties
		nsTop 143
		nsLeft 74
		nsBottom 189
		nsRight 227
		cursor 11
		exitDir 3
		noun 17
	)
)

(instance noise of Sound
	(properties
		flags $0001
	)
)
