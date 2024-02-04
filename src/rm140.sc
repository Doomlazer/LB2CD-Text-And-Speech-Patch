;;; Sierra Script 1.0 - (do not remove this comment)
(script# 140)
(include sci.sh)
(use Main)
(use LBRoom)
(use Scaler)
(use PolyPath)
(use n958)
(use Cycle)
(use View)
(use Obj)

(public
	rm140 0
)

(instance rm140 of LBRoom
	(properties
		picture 140
		style $000a
		vanishingX 7
		vanishingY 54
	)
	
	(method (init)
		(proc958_0 128 151 830 142)
		(proc958_0 132 140)
		(self setRegions: 92)
		(gEgo
			init:
			view: 830
			loop: 3
			cel: 1
			posn: 167 158
			setScale: Scaler 125 0 190 24
			cycleSpeed: 6
		)
		(super init:)
		(dad init:)
		(gWrapSound number: 140 flags: 1 loop: -1 play:)
		(self setScript: sCartoon)
	)
	
	(method (dispose)
		(gWrapSound fade: 0 30 12 1)
		(super dispose: &rest)
	)
)

(instance sCartoon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 1880 1)
					disposeWhenDone: 0
					x: 5
					y: 5
					textX: 10
					textY: 140
					talkWidth: 280
				)
				((ScriptID 1881 2)
					disposeWhenDone: 0
					x: 211
					y: 5
					textX: -196
					textY: 140
					talkWidth: 280
				)
				(= cycles 1)
			)
			(1
				(gNarrator x: 60 y: 100 keepWindow: 0)
				(gLb2Messager say: 1 0 0 0 self)
			)
			(2 (= cycles 2))
			(3
				(gEgo setMotion: PolyPath 167 145 self)
			)
			(4
				(gEgo hide:)
				(dad loop: 0 setCycle: End self)
			)
			(5 (= seconds 2))
			(6 (dad setCycle: Beg self))
			(7
				(gEgo show: setLoop: 4 setMotion: MoveTo 299 340 self)
				(= cycles 1)
			)
			(8
				(dad
					loop: 1
					cel: 0
					posn: 168 143
					cycleSpeed: 10
					setCycle: CT 6 1 self
				)
			)
			(9 (= ticks 20))
			(10
				(dad setCycle: CT 4 -1 self)
			)
			(11 (= ticks 20))
			(12 (dad setCycle: CT 6 1 self))
			(13 (dad setCycle: Beg self))
			(14 0)
			(15
				(dad addToPic:)
				(creditTitle
					init:
					setPri: 15
					setMotion: MoveTo 50 82 self
				)
				(creditName
					init:
					setPri: 15
					setMotion: MoveTo 107 125 self
				)
			)
			(16 0)
			(17 (= seconds 3))
			(18
				(creditTitle setMotion: MoveTo -200 82 self)
				(creditName setMotion: MoveTo 107 209 self)
			)
			(19 0)
			(20 (= seconds 3))
			(21
				(creditTitle
					loop: 10
					posn: 50 -100
					setMotion: MoveTo 50 82 self
				)
				(creditName
					loop: 10
					posn: 107 -57
					setMotion: MoveTo 107 125 self
				)
			)
			(22 0)
			(23 (= seconds 3))
			(24
				(creditTitle setMotion: MoveTo 320 82 self)
				(creditName setMotion: MoveTo -150 125 self)
			)
			(25 0)
			(26
				(global2 newRoom: 150)
				(self dispose:)
			)
		)
	)
)

(instance dad of Actor
	(properties
		x 170
		y 143
		view 142
		loop 1
		signal $4000
		cycleSpeed 10
	)
)

(instance creditTitle of Actor
	(properties
		x 50
		y -2
		view 151
		loop 3
		signal $6800
		moveSpeed 0
	)
)

(instance creditName of Actor
	(properties
		x 230
		y 125
		view 151
		loop 3
		cel 1
		signal $6800
		moveSpeed 0
	)
)
