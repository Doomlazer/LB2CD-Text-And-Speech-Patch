;;; Sierra Script 1.0 - (do not remove this comment)
(script# 28)
(include sci.sh)
(use Main)
(use n958)
(use Cycle)
(use Game)
(use View)
(use Obj)

(public
	speedRoom 0
)

(local
	local0
	local1
)
(instance speedRoom of Rm
	(properties
		picture 780
	)
	
	(method (init)
		(proc958_0 128 104)
		(super init:)
		(gGame handsOff:)
		(self setScript: speedTest)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(= temp0 0)
		(while (< temp0 500)
			(++ temp0)
		)
	)
)

(instance fred of Actor
	(properties
		view 104
	)
)

(instance speedTest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fred
					setLoop: 0
					illegalBits: 0
					posn: 0 0
					setStep: 1 1
					setCycle: Fwd
					init:
				)
				(= cycles 1)
			)
			(1
				(= local1 (GetTime))
				(fred setMotion: MoveTo 320 190)
				(= cycles 50)
			)
			(2
				(= local0 (- (GetTime) local1))
				(startGame doit:)
			)
		)
	)
)

(instance startGame of Code
	(properties)
	
	(method (doit &tmp [temp0 100])
		(cond 
			((> local0 160) (= global87 0))
			((> local0 150) (= global87 1))
			((> local0 140) (= global87 2))
			((> local0 130) (= global87 3))
			((> local0 120) (= global87 4))
			((> local0 110) (= global87 5))
			((> local0 100) (= global87 6))
			((> local0 90) (= global87 7))
			((> local0 80) (= global87 8))
			((> local0 70) (= global87 9))
			((> local0 60) (= global87 10))
			((> local0 50) (= global87 11))
			((> local0 40) (= global87 12))
			((> local0 30) (= global87 13))
			((> local0 20) (= global87 14))
			(else (= global87 15))
		)
		(gGame
			detailLevel:
				(cond 
					((<= global87 3) 1)
					((<= global87 10) 2)
					(else 3)
				)
		)
		(= gEatTheMice 2)
		(global2 newRoom: (if global110 29 else 105))
	)
)
