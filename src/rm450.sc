;;; Sierra Script 1.0 - (do not remove this comment)
(script# 450)
(include sci.sh)
(use Main)
(use LBRoom)
(use ExitFeature)
(use MuseumRgn)
(use Scaler)
(use PolyPath)
(use Polygon)
(use CueObj)
(use MoveFwd)
(use n958)
(use Sound)
(use Cycle)
(use View)
(use Obj)

(public
	rm450 0
)

(local
	local0
)
(instance rm450 of LBRoom
	(properties
		noun 13
		picture 450
		south 448
		west 454
		vanishingX 172
		vanishingY 21
	)
	
	(method (init)
		(proc958_0 128 454 423 452 858 424)
		(proc958_0 132 450 600)
		(proc958_0 130 2450)
		(gEgo
			init:
			normalize: (if (== global123 5) 426 else 831)
			setScale: Scaler 131 30 190 21
		)
		(if (== global123 5)
			(self setRegions: 94)
			(self
				addObstacle:
					((Polygon new:)
						type: 2
						init: 0 0 319 0 319 189 314 189 314 141 272 125 224 120 0 120
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 18 124 137 124 137 144 18 144
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 7 150 61 150 61 177 7 177
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 94 147 153 147 153 180 94 180
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 202 136 239 136 239 153 202 153
						yourself:
					)
			)
		else
			(self setRegions: 90)
		)
		(switch gGNumber
			(west
				(gGame handsOn:)
				(= style 12)
				(if
					(and
						(proc0_2 35)
						(== global123 3)
						(not (proc0_10 -24319 0))
					)
					((ScriptID 90 15) seconds: 2)
				)
			)
			(south
				(= style 100)
				(gEgo x: 160 y: 350)
				(self setScript: sComeOnIn)
				(if (and (== global123 5) (proc0_2 90))
					(self notify:)
				)
			)
			(else 
				(gGame handsOn:)
				(gEgo x: 160 y: 160)
			)
		)
		(super init:)
		(if (and (!= gGNumber west) (< global123 5))
			(if (== global123 2)
				(gWrapSound pause:)
			else
				(WrapMusic pause:)
			)
			(gGameMusic2 number: 450 flags: 1 loop: -1 play:)
		)
		(if (proc0_2 38)
			(shatteredGlass init: stopUpd: approachVerbs: 1 4 8)
		else
			(glass init: stopUpd: approachVerbs: 1 4 8)
		)
		(pyramid init: approachVerbs: 1 4 8 setOnMeCheck: 1 64)
		(wallWindow init: approachVerbs: 1 4 8)
		(daggerCase init: approachVerbs: 1 4 8)
		(mummy init: approachVerbs: 1 4 8)
		(post init: approachVerbs: 1 4 8)
		(wings init: approachVerbs: 1 4 8)
		(rock1 init: approachVerbs: 1 4 8)
		(plaque init: approachVerbs: 1 4 8)
		(rock2 init: approachVerbs: 1 4 8)
		(westExitFeature init:)
		(southExitFeature init:)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((proc0_1 gEgo 16) (self setScript: sEgoLeaveSouth))
		)
	)
	
	(method (dispose)
		(DisposeScript 2450)
		(super dispose: &rest)
	)
	
	(method (newRoom newRoomNumber)
		(cond 
			(
			(and (== newRoomNumber south) (== global123 2)) (gGameMusic2 fade: 1) (gWrapSound pause: 0))
			((and (== newRoomNumber south) (< global123 5)) (gGameMusic2 fade: 1) (WrapMusic pause: 0))
		)
		(super newRoom: newRoomNumber)
	)
	
	(method (notify)
		(cond 
			((gEgo script?)
				((gEgo script?)
					next: (if (== global123 5) sDie else sLauraTutMeeting)
				)
			)
			((global2 script?)
				((global2 script?)
					next: (if (== global123 5) sDie else sLauraTutMeeting)
				)
			)
			(else
				(global2
					setScript: (if (== global123 5) sDie else sLauraTutMeeting)
				)
			)
		)
	)
)

(instance sComeOnIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(gEgo posn: 160 300)
				(gEgo setMotion: MoveTo 160 160 self)
			)
			(2
				(if
					(and
						(proc0_2 35)
						(== global123 3)
						(not (proc0_10 -24319 0))
					)
					((ScriptID 90 15) seconds: 2)
				)
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEgoLeaveSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(gEgo heading: 180)
				(= cycles 1)
			)
			(1
				(gEgo setMotion: MoveFwd 75 self)
			)
			(2 (global2 newRoom: 448))
		)
	)
)

(instance sLauraTutMeeting of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (== state 0) (not (global2 inset:)))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (gGame handsOff:))
			(1
				(if (== ((ScriptID 90 4) room?) 450)
					((ScriptID 90 4) wandering: 0 setScript: 0)
				else
					((ScriptID 90 4) moveTo: 450 view: 821 x: 190 y: 270)
				)
				(= cycles 2)
			)
			(2
				((ScriptID 90 4) setMotion: PolyPath 190 160 self)
			)
			(3
				(gEgo setMotion: PolyPath 160 160 self)
			)
			(4
				(proc0_5 gEgo (ScriptID 90 4))
				(proc0_5 (ScriptID 90 4) gEgo)
				(= cycles 4)
			)
			(5 (= cycles 1))
			(6
				(gLb2Messager say: 1 0 1 0 self 1450)
			)
			(7
				((ScriptID 90 4)
					wander:
						(if (!= ((ScriptID 90 4) originalDest?) 450)
							((ScriptID 90 4) originalDest?)
						else
							454
						)
						(ScriptID 90 4)
				)
				(gGame handsOn:)
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
				(gEgo setMotion: PolyPath 160 160 self)
			)
			(1
				(oriley
					init:
					setScale: Scaler 131 30 190 21
					setCycle: Walk
					setMotion: PolyPath 190 170 self
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

(instance sBreakIt of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (not local0) (== (glass cel?) 5))
			(nGlass play:)
			(= local0 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(gEgo
					view: 452
					loop: 2
					cel: 0
					cycleSpeed: 12
					setScale: Scaler 100 100 190 21
					setCycle: CT 2 1 self
				)
			)
			(1
				(gEgo setCycle: End self)
				(glass setCycle: End self)
			)
			(2 0)
			(3
				(gLb2Messager say: 4 4 1 0 self)
			)
			(4
				(glass dispose:)
				(shatteredGlass init: stopUpd: approachVerbs: 1 4 8)
				(proc0_3 38)
				(gEgo
					normalize: (if (== global123 5) 426 else 831)
					setScale: Scaler 131 30 190 21
				)
				(gEgo loop: 5)
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance oriley of Actor
	(properties
		x 200
		y 240
		view 423
	)
)

(instance glass of Prop
	(properties
		x 17
		y 161
		approachX 58
		approachY 164
		view 452
		loop 1
		priority 12
		signal $4010
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(1
					(gLb2Messager say: 4 1 (if (proc0_2 38) 2 else 0))
				)
				(4
					(cond 
						((proc0_2 38) (gLb2Messager say: 4 4 2))
						((not (global2 script?))
							(if
							(or (== global123 5) (MuseumRgn nobodyAround:))
								(global2 setScript: sBreakIt)
							else
								(return 1)
							)
						)
					)
				)
				(8
					(if (proc0_2 38)
						(gLb2Messager say: 4 8 2)
					else
						(gLb2Messager say: 4 8 1)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance shatteredGlass of View
	(properties
		x 17
		y 161
		noun 9
		view 452
		loop 1
		cel 9
		signal $4000
	)
)

(instance plaque of Feature
	(properties
		x 290
		y 78
		noun 12
		nsTop 69
		nsLeft 286
		nsBottom 87
		nsRight 295
		sightAngle 40
		approachX 277
		approachY 142
	)
)

(instance pyramid of Feature
	(properties
		x 69
		y 142
		noun 5
		sightAngle 40
		approachX 78
		approachY 150
	)
)

(instance daggerCase of Feature
	(properties
		x 33
		y 168
		noun 4
		nsTop 142
		nsLeft 18
		nsBottom 167
		nsRight 48
		sightAngle 40
		approachX 58
		approachY 164
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(1
					(gLb2Messager say: 4 1 (if (proc0_2 38) 2 else 0))
				)
				(4
					(cond 
						((proc0_2 38) (gLb2Messager say: 4 4 2))
						((not (global2 script?))
							(if
							(or (== global123 5) (MuseumRgn nobodyAround:))
								(global2 setScript: sBreakIt)
							else
								(return 1)
							)
						)
					)
				)
				(8
					(if (proc0_2 38)
						(gLb2Messager say: 4 8 2)
					else
						(gLb2Messager say: 4 8 1)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance post of Feature
	(properties
		x 219
		y 148
		noun 2
		nsTop 25
		nsLeft 206
		nsBottom 146
		nsRight 232
		sightAngle 40
		approachX 213
		approachY 155
	)
)

(instance mummy of Feature
	(properties
		x 120
		y 176
		noun 1
		nsTop 97
		nsLeft 105
		nsBottom 176
		nsRight 136
		sightAngle 40
		approachX 164
		approachY 172
	)
)

(instance wallWindow of Feature
	(properties
		x 177
		y 80
		noun 3
		nsTop 58
		nsLeft 158
		nsBottom 102
		nsRight 197
		sightAngle 40
		approachX 179
		approachY 123
	)
)

(instance wings of Feature
	(properties
		x 176
		y 45
		noun 6
		nsTop 36
		nsLeft 147
		nsBottom 54
		nsRight 205
		sightAngle 40
		approachX 180
		approachY 122
	)
)

(instance rock1 of Feature
	(properties
		x 76
		y 76
		noun 8
		nsTop 42
		nsLeft 22
		nsBottom 110
		nsRight 130
		sightAngle 40
		approachX 19
		approachY 125
	)
)

(instance rock2 of Feature
	(properties
		x 251
		y 79
		noun 7
		nsTop 42
		nsLeft 232
		nsBottom 116
		nsRight 270
		sightAngle 40
		approachX 251
		approachY 130
	)
)

(instance westExitFeature of ExitFeature
	(properties
		nsTop 120
		nsBottom 169
		nsRight 5
		cursor 12
		exitDir 4
		noun 11
	)
)

(instance southExitFeature of ExitFeature
	(properties
		nsTop 184
		nsBottom 189
		nsRight 320
		cursor 11
		exitDir 3
		noun 10
	)
)

(instance nGlass of Sound
	(properties
		flags $0001
		number 600
	)
)

(instance thudSound of Sound
	(properties
		flags $0001
		number 80
	)
)
