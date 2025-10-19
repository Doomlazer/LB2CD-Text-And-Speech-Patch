;;; Sierra Script 1.0 - (do not remove this comment)
(script# 460)
(include sci.sh)
(use Main)
(use LbDoor)
(use LBRoom)
(use PursuitRgn)
(use PChase)
(use Scaler)
(use PolyPath)
(use Polygon)
(use CueObj)
(use n958)
(use Sound)
(use Cycle)
(use View)
(use Obj)

(public
	rm460 0
)

(local
	local0
	local1
	[thePoints 44] = [83 148 64 168 297 169 296 154 246 154 246 138 217 141 229 146 230 157 189 157 187 146 187 138 162 139 174 150 160 160 104 152 144 141 102 146 103 120 72 120 92 123 96 146]
	[thePoints_2 30] = [70 160 64 168 297 169 296 154 246 154 246 138 217 141 229 146 230 157 189 157 187 146 187 138 162 139 174 150 160 160]
	[thePoints_3 46] = [83 148 64 168 297 169 296 154 270 154 280 119 269 141 217 141 229 146 230 157 189 157 187 146 187 138 162 139 174 150 160 160 119 155 144 141 102 146 103 120 72 120 92 123 96 146]
	[thePoints_4 30] = [73 156 64 168 297 169 296 154 274 154 277 120 267 139 217 141 229 146 230 157 189 157 187 146 187 138 162 139 170 156]
)
(instance rm460 of LBRoom
	(properties
		noun 1
		picture 460
		east 660
		west 448
		vanishingY 20
	)
	
	(method (init)
		(proc958_0 128 460 462 461 858 423 424)
		(proc958_0 132 462 460)
		(self setRegions: 94)
		(gEgo init: setScale: 165 normalize: 426)
		(switch gGNumber
			(west
				(gEgo x: 84 y: 122 edgeHit: 0 setHeading: 135)
				(shoveCrate x: 261)
				(self setScript: sEnter)
				(global2 addObstacle: (poly460a init: yourself:))
				(crane init: stopUpd:)
				(moverCrate init: stopUpd: approachVerbs: 4)
			)
			(east
				(gEgo posn: 105 135 edgeHit: 0 setHeading: 135)
				(cond 
					((proc0_2 103)
						(global2 addObstacle: (poly460d init: yourself:))
						(crane init: stopUpd: cel: (crane lastCel:))
						(moverCrate
							init:
							stopUpd:
							view: 462
							loop: 2
							x: 108
							y: 153
							cel: 0
							approachVerbs: 4
						)
					)
					((proc0_2 102)
						(global2 addObstacle: (poly460d init: yourself:))
						(crane init: stopUpd: cel: (crane lastCel:))
						(moverCrate
							init:
							stopUpd:
							cel: (moverCrate lastCel:)
							approachVerbs: 4
						)
					)
					(else
						(global2 addObstacle: (poly460c init: yourself:))
						(crane init: stopUpd:)
						(moverCrate init: stopUpd: approachVerbs: 4)
					)
				)
				(shoveCrate x: 281)
			)
			(else 
				(gEgo posn: 211 160)
				(gGame handsOn:)
			)
		)
		(super init:)
		(smashedDoor init: ignoreActors: addToPic:)
		(shoveCrate init: stopUpd: setLoop: 3)
		(elevatorDoor init: stopUpd:)
		(floor init:)
		(pipe init: approachVerbs: 1 4 8)
		(cable init: approachVerbs: 1 4 21)
		(ties init: approachVerbs: 1 4)
		(crate1 init: approachVerbs: 1 4 8)
		(crate2 init: approachVerbs: 1 4 8)
		(crate3 init: approachVerbs: 1 4 8)
		(crate4 init: approachVerbs: 1 4 8)
		(crate5 init: approachVerbs: 1 4 8)
		(crate6 init: approachVerbs: 1 4 8)
		(darkCrates init: approachVerbs: 1 4 8)
		(PursuitRgn increaseTime:)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			(
				(and
					(proc0_1 gEgo 4)
					(> ((ScriptID 94 1) seconds?) 1)
				)
				((ScriptID 94 1) seconds: 1)
			)
		)
	)
	
	(method (dispose)
		(super dispose: &rest)
	)
	
	(method (notify)
		(if (global2 script?)
			((global2 script?) next: sDie)
		else
			(global2 setScript: sDie)
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
				(gEgo setMotion: PolyPath 100 140 self)
			)
			(2
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
				(gEgo setMotion: PolyPath 211 160 self)
			)
			(1
				(oriley
					init:
					setScale: 165
					setMotion: PolyPath 100 140 self
				)
				(gWrapSound number: 3 flags: 1 loop: 1 play:)
			)
			(2
				(if (proc0_2 102)
					(= cycles 1)
				else
					(self changeState: 4)
				)
			)
			(3
				(oriley view: 424 setCycle: Fwd)
				(moverCrate setCycle: End self)
				(splinters init: setCycle: End)
				(gGameMusic2 number: 444 flags: 1 loop: 1 play:)
			)
			(4
				(oriley
					view: 423
					setCycle: Walk
					setMotion: PChase gEgo 22 self
				)
			)
			(5
				(oriley view: 424)
				(oriley cel: 0)
				(proc0_5 gEgo oriley)
				(proc0_5 oriley gEgo)
				(= cycles 4)
			)
			(6 (oriley setCycle: End self))
			(7
				(thudSound play:)
				(gEgo view: 858 setCycle: End self)
			)
			(8
				(= global145 0)
				(global2 newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance sMoveCrate of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and local1 (== (shoveCrate x?) 281))
			(gGameMusic2 stop:)
			(= local1 0)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(PursuitRgn increaseTime:)
				(= local0 1)
				(gGame handsOff:)
				(gEgo setMotion: PolyPath 243 134 self)
			)
			(1
				(gEgo
					view: 461
					loop: 2
					cel: 0
					posn: 256 136
					setScale: Scaler 100 100 190 21
					setCycle: CT 2 1 self
				)
			)
			(2
				(gGameMusic2 number: 721 flags: 1 loop: 1 play:)
				(= local1 1)
				(gEgo setCycle: End self)
				(shoveCrate setMotion: MoveTo 281 140 self)
			)
			(3 0)
			(4
				((global2 obstacles?) eachElementDo: #dispose release:)
				(= cycles 1)
			)
			(5
				(shoveCrate stopUpd:)
				(global2
					addObstacle:
						(if (proc0_2 102)
							(poly460d init: yourself:)
						else
							(poly460c init: yourself:)
						)
				)
				(gEgo normalize: 426 setScale: 165)
				(= cycles 1)
			)
			(6
				(gEgo setMotion: MoveTo (gEgo x?) 140 self)
			)
			(7
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sCutRope of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(PursuitRgn increaseTime:)
				(gEgo
					view: 461
					loop: 1
					cel: 0
					posn: 196 147
					setScale: Scaler 100 100 190 21
					setCycle: End self
				)
			)
			(1
				(gGameMusic2 number: 460 flags: 1 play:)
				(moverCrate
					setLoop: 3
					posn: 108 32
					cel: 0
					setCycle: End self
				)
				(cable setCycle: End self)
			)
			(2
				(gGameMusic2 number: 462 flags: 1 play:)
			)
			(3
				(moverCrate
					view: 462
					setLoop: 2
					cel: 0
					posn: 108 153
					ignoreActors: 0
				)
				(gEgo normalize: 426 setScale: 165)
				(gEgo setMotion: MoveTo 177 149 self)
			)
			(4
				(gGame handsOn:)
				(moverCrate stopUpd:)
				(proc0_3 103)
				(self dispose:)
			)
		)
	)
)

(instance sSwingIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(PursuitRgn increaseTime:)
				(gGame handsOff:)
				(gEgo setPri: 13)
				(gEgo setMotion: MoveTo 153 148 self)
			)
			(1
				(proc0_5 gEgo crane)
				(= cycles 1)
			)
			(2
				(gEgo
					view: 461
					loop: 0
					cel: 0
					setPri: 14
					setScale: Scaler 100 100 190 21
					setCycle: End self
				)
				(crane setCycle: End self)
				(moverCrate setCycle: End self)
			)
			(3
				(if (== gGNumber 660)
					(= local0 1)
				)
			)
			(4
				((global2 obstacles?) eachElementDo: #dispose)
				((global2 obstacles?) release:)
				(= cycles 1)
			)
			(5
				(global2
					addObstacle:
						(if local0
							(poly460d init: yourself:)
						else
							(poly460b init: yourself:)
						)
				)
				(gEgo normalize: 426 setScale: 165)
				(gEgo setMotion: MoveTo 184 159 self)
			)
			(6
				(gGame handsOn:)
				(crane stopUpd:)
				(moverCrate stopUpd:)
				(proc0_3 102)
				(gEgo setPri: -1)
				(self dispose:)
			)
		)
	)
)

(instance splinters of Prop
	(properties
		x 116
		y 114
		view 462
		loop 3
	)
)

(instance oriley of Actor
	(properties
		x 84
		y 122
		view 424
	)
)

(instance cable of Prop
	(properties
		x 202
		y 53
		noun 18
		approachX 184
		approachY 147
		view 460
		loop 4
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(21
				(if (proc0_2 102)
					(global2 setScript: sCutRope)
				else
					(gLb2Messager say: 18 22 1)
				)
			)
			(22
				(if (proc0_2 102)
					(global2 setScript: sCutRope)
				else
					(gLb2Messager say: 18 22 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance shoveCrate of Actor
	(properties
		x 261
		y 140
		z 50
		noun 14
		view 461
		loop 3
		priority 11
		signal $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (and (not (== gGNumber 660)) (not local0))
					(global2 setScript: sMoveCrate)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance crane of Prop
	(properties
		x 213
		y 57
		noun 17
		view 460
		priority 11
		signal $0010
	)
)

(instance moverCrate of Prop
	(properties
		x 135
		y 57
		noun 19
		approachX 182
		approachY 153
		view 460
		loop 1
		priority 11
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (proc0_2 102)
					(super doVerb: theVerb &rest)
				else
					(global2 setScript: sSwingIt)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance elevatorDoor of LbDoor
	(properties
		x 268
		y 131
		approachX 265
		approachY 144
		view 462
		entranceTo 660
		moveToX 274
		moveToY 133
		enterType 0
		exitType 0
	)
	
	(method (createPoly)
		(super createPoly: 266 123 290 123 290 134 266 134)
	)
)

(instance smashedDoor of View
	(properties
		x 117
		y 5
		z -90
		noun 5
		view 462
		loop 1
		priority 10
		signal $0010
	)
)

(instance pipe of Feature
	(properties
		x 208
		y 89
		noun 16
		nsTop 57
		nsLeft 205
		nsBottom 122
		nsRight 212
		sightAngle 40
		approachX 186
		approachY 151
	)
)

(instance ties of Feature
	(properties
		x 209
		y 177
		noun 3
		nsTop 122
		nsLeft 205
		nsBottom 132
		nsRight 213
		sightAngle 40
		approachX 184
		approachY 147
	)
)

(instance crate1 of Feature
	(properties
		x 15
		y 47
		noun 8
		nsTop 54
		nsBottom 141
		nsRight 30
		sightAngle 40
		approachX 77
		approachY 155
	)
)

(instance crate2 of Feature
	(properties
		x 50
		y 45
		noun 9
		nsTop 106
		nsLeft 31
		nsBottom 145
		nsRight 69
		sightAngle 40
		approachX 83
		approachY 156
	)
)

(instance crate3 of Feature
	(properties
		x 155
		y 49
		noun 10
		nsTop 76
		nsLeft 133
		nsBottom 103
		nsRight 178
		sightAngle 40
		approachX 141
		approachY 145
	)
)

(instance crate4 of Feature
	(properties
		x 154
		y 49
		noun 11
		nsTop 104
		nsLeft 126
		nsBottom 135
		nsRight 182
		sightAngle 40
		approachX 174
		approachY 146
	)
)

(instance crate5 of Feature
	(properties
		x 209
		y 44
		noun 12
		nsTop 73
		nsLeft 193
		nsBottom 96
		nsRight 225
		sightAngle 40
		approachX 182
		approachY 144
	)
)

(instance crate6 of Feature
	(properties
		x 210
		y 47
		noun 13
		nsTop 99
		nsLeft 185
		nsBottom 136
		nsRight 236
		sightAngle 40
		approachX 223
		approachY 145
	)
)

(instance darkCrates of Feature
	(properties
		x 159
		y 179
		noun 6
		nsTop 170
		nsBottom 189
		nsRight 319
		sightAngle 40
		approachX 198
		approachY 165
	)
)

(instance floor of Feature
	(properties
		x 195
		y 55
		noun 4
		nsTop 142
		nsLeft 71
		nsBottom 169
		nsRight 319
		sightAngle 40
	)
)

(instance poly460a of Polygon
	(properties)
	
	(method (init)
		(= type 3)
		(= size 22)
		(= points @thePoints)
	)
)

(instance poly460b of Polygon
	(properties)
	
	(method (init)
		(= type 3)
		(= size 15)
		(= points @thePoints_2)
	)
)

(instance poly460c of Polygon
	(properties)
	
	(method (init)
		(= type 3)
		(= size 23)
		(= points @thePoints_3)
	)
)

(instance poly460d of Polygon
	(properties)
	
	(method (init)
		(= type 3)
		(= size 15)
		(= points @thePoints_4)
	)
)

(instance thudSound of Sound
	(properties
		flags $0001
		number 80
	)
)
