;;; Sierra Script 1.0 - (do not remove this comment)
(script# 190)
(include sci.sh)
(use Main)
(use LbDoor)
(use LBRoom)
(use PolyPath)
(use Polygon)
(use n958)
(use Cycle)
(use View)
(use Obj)

(public
	rm190 0
)

(instance rm190 of LBRoom
	(properties
		picture 210
		vanishingX 400
		vanishingY 143
	)
	
	(method (init)
		(proc958_0 128 151 809 210 284 212 213)
		(self setRegions: 92)
		(gEgo normalize: 803 init:)
		(super init:)
		(self
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						319
						188
						319
						176
						197
						176
						188
						176
						170
						179
						156
						180
						88
						184
						80
						183
						73
						180
						46
						156
						17
						156
						23
						170
						49
						188
					yourself:
				)
		)
		(frontDoor init:)
		(taxiSign init:)
		(person2
			init:
			xStep: 1
			yStep: 1
			setLoop: 2
			setCycle: Walk
			setMotion: MoveTo -10 193
		)
		(person3
			init:
			xStep: 1
			yStep: 1
			setLoop: 3
			setCycle: Walk
			setMotion: MoveTo 208 179
		)
		(person8
			init:
			xStep: 1
			yStep: 1
			setLoop: 11
			setCycle: Walk
			setMotion: MoveTo 330 184
		)
		(car init: setLoop: 0 setMotion: MoveTo 370 181)
		(global2 setScript: sIntroCartoon)
	)
	
	(method (dispose)
		(gWrapSound fade: 0 30 12 1)
		(gGameMusic2 fade: 0 30 12 1)
		(super dispose: &rest)
	)
)

(instance sIntroCartoon of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== (gWrapSound number?) 94) (== state 7))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gEgo loop: 7 x: 71 y: 185)
				((ScriptID 1881 2) modeless: 1)
				(= cycles 1)
			)
			(1
				(gEgo setMotion: PolyPath 57 181 self)
			)
			(2
				(gEgo setHeading: 1)
				(= cycles 4)
			)
			(3
				(gEgo
					view: 809
					loop: 3
					posn: 58 180
					cycleSpeed: 10
					moveSpeed: 10
					setCycle: CT 6 1 self
				)
			)
			(4
				(gLb2Messager say: 1 0 0 0 self)
			)
			(5 (gEgo setCycle: End self))
			(6
				(gEgo normalize: 803)
				(gEgo setMotion: PolyPath 40 175 self)
			)
			(7 (frontDoor setCycle: End))
			(8 (global2 newRoom: 220))
		)
	)
)

(instance frontDoor of LbDoor
	(properties
		x 39
		y 167
		approachX 36
		approachY 175
		view 210
		entranceTo 220
		moveToX 29
		moveToY 172
		enterType 0
		exitType 0
	)
)

(instance taxiSign of View
	(properties
		x 165
		y 185
		view 284
		cel 2
		signal $4000
	)
)

(instance person2 of Actor
	(properties
		x 140
		y 180
		view 212
		loop 2
		signal $4000
	)
)

(instance creditTitle of Actor
	(properties
		x -156
		y 102
		view 151
		loop 9
		signal $6800
		moveSpeed 0
	)
)

(instance creditName of Actor
	(properties
		x -190
		y 138
		view 151
		loop 9
		cel 1
		signal $6800
		moveSpeed 0
	)
)

(instance person3 of Actor
	(properties
		x -10
		y 190
		view 212
		loop 3
		signal $4000
	)
)

(instance person8 of Actor
	(properties
		x 51
		y 189
		view 212
		loop 11
		signal $4000
	)
)

(instance car of Actor
	(properties
		x 154
		y 189
		view 213
		cel 1
		priority 14
		signal $4010
	)
)
