;;; Sierra Script 1.0 - (do not remove this comment)
(script# 150)
(include sci.sh)
(use Main)
(use LBRoom)
(use n958)
(use Cycle)
(use View)
(use Obj)

(public
	rm150 0
)

(instance rm150 of LBRoom
	(properties
		picture 150
		style $000a
	)
	
	(method (init)
		(proc958_0 128 151 150)
		(proc958_0 132 150)
		(self setRegions: 92)
		(super init:)
		(gWrapSound number: 150 flags: 1 loop: -1 play:)
		(lauraTrain init:)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(lauraTrain setMotion: MoveTo -245 56)
				(= seconds 2)
			)
			(1
				(creditTitle init: setMotion: MoveTo 35 107 self)
				(creditName init: setMotion: MoveTo 35 151 self)
			)
			(2 0)
			(3 (= seconds 3))
			(4
				(= temp0 (CelWide 151 4 0))
				(creditTitle setMotion: MoveTo (- 0 temp0) 107 self)
				(creditName setMotion: MoveTo -250 151 self)
			)
			(5 0)
			(6 (= seconds 3))
			(7
				(= temp0 (CelWide 151 5 0))
				(creditName
					posn: 590 130
					loop: 5
					setMotion: MoveTo -222 130 self
				)
				(creditTitle
					posn: (- (- (creditName x?) temp0) 20) 130
					loop: 5
					setMotion: MoveTo (- 0 temp0) 130 self
				)
			)
			(8 0)
			(9 (= seconds 3))
			(10
				(creditTitle
					posn: -236 119
					loop: 6
					setMotion: MoveTo 42 119 self
				)
				(creditName
					posn: -135 146
					loop: 6
					setMotion: MoveTo 143 146 self
				)
			)
			(11 0)
			(12 (= seconds 3))
			(13
				(= temp0 (CelWide 151 6 0))
				(creditTitle setMotion: MoveTo (- 0 temp0) 119 self)
				(creditName setMotion: MoveTo -145 146 self)
			)
			(14 0)
			(15
				(global2 newRoom: 155)
				(self dispose:)
			)
		)
	)
)

(instance lauraTrain of Actor
	(properties
		x 300
		y 56
		view 150
		moveSpeed 10
	)
)

(instance creditTitle of Actor
	(properties
		x 35
		y 200
		view 151
		loop 4
		signal $6800
		moveSpeed 0
	)
)

(instance creditName of Actor
	(properties
		x 35
		y 244
		view 151
		loop 4
		cel 1
		signal $6800
		moveSpeed 0
	)
)
