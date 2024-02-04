;;; Sierra Script 1.0 - (do not remove this comment)
(script# 785)
(include sci.sh)
(use Main)
(use LBRoom)
(use View)
(use Obj)

(public
	rm785 0
)

(local
	local0
	local1
	local2
	[local3 19] = [342 597 850 1113 1366 1628 1885 2131 2394 2651 2900 3166 3424 3683 3937 4184 4439 4688 4962]
)
(instance rm785 of LBRoom
	(properties
		picture 780
	)
	
	(method (init)
		(super init:)
		(gWrapSound number: 140 flags: 1 loop: -1 play:)
		(= local0
			(switch global126
				(1 1)
				(2 2)
				(3 3)
				(4 4)
			))
		(gIconBar disable:)
		(gGame setCursor: 996)
		(= local1 (>> (& [local3 local2] $ff00) $0008))
		(characterView
			view: (+ 1800 (& [local3 local2] $00ff))
			loop: 1
			x: 10
			y: 10
			init:
		)
		(gNarrator x: 10 y: 113 talkWidth: 290)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(global2
					drawPic: (if (== (characterView view?) 1898) 785 else 780) 10
				)
				(= cycles 2)
			)
			(1
				(gLb2Messager say: local1 0 local0 0 self)
			)
			(2 (++ local2) (= ticks 120))
			(3
				(if (== local2 19)
					(gWrapSound fade: 0 12 30 1)
					(global2
						newRoom: (if (proc999_5 global126 1 4) 780 else 790)
					)
				else
					(= local1 (>> (& [local3 local2] $ff00) $0008))
					(characterView
						view: (+ 1800 (& [local3 local2] $00ff))
						loop: 1
						cel: 0
						setPri: 15
						x:
						(switch (mod local1 3)
							(0 220)
							(1 10)
							(2 115)
						)
						y:
							(cond 
								((== local1 19) 5)
								(
								(and (< (mod local1 6) 4) (> (mod local1 6) 0)) 10)
								(else 103)
							)
						init:
					)
					(gNarrator
						x: (if (== local1 19) 160 else 10)
						y: (if (== (characterView y?) 10) 113 else 10)
						talkWidth: (if (== local1 19) 140 else 290)
					)
					(self changeState: 0)
				)
			)
		)
	)
)

(instance characterView of View
	(properties)
)
