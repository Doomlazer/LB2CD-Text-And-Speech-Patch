;;; Sierra Script 1.0 - (do not remove this comment)
(script# 22)
(include sci.sh)
(use Main)
(use Inset)
(use Sound)
(use View)
(use Obj)

(public
	triggerAndClock 0
)

(local
	local0
	local1
	local2
	[local3 2] = [33 18]
	[local5 5]
)
(instance triggerAndClock of Code
	(properties)
	
	(method (doit param1 param2)
		(if (& param1 $ff00)
			(= local0 (>> (= local0 (& param1 $f000)) $000c))
			(= local1 (>> (= local1 (& param1 $0f00)) $0008))
			(switch (= local2 (+ (* local0 100) (* 15 local1)))
				(815 (proc0_3 1))
				(1015 (proc0_3 2))
				(1115 (proc0_3 3))
				(200 (proc0_3 4))
				(300
					(proc0_3 5)
					(= global111 15)
				)
				(315 (proc0_3 6))
			)
			(global2
				setInset: clockInset (if (> argc 1) param2 else 0)
			)
			(= param1 (& param1 $00ff))
		)
		(= global124 (+ global124 param1))
	)
)

(instance saveVolume of Code
	(properties)
	
	(method (doit param1)
		(if (param1 handle?)
			(= [local5 (gSounds indexOf: param1)] (param1 vol?))
			(if (> [local5 (gSounds indexOf: param1)] 50)
				(param1 setVol: 50)
			)
		)
	)
)

(instance restoreVolume of Code
	(properties)
	
	(method (doit param1 &tmp temp0)
		(if (= temp0 [local5 (gSounds indexOf: param1)])
			(param1 fade: temp0 1 5 0)
		)
	)
)

(instance sShowClock of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 40] temp40)
		(switch (= state newState)
			(0
				(quarterHand cel: local1 init:)
				(hourHand cel: local0 init:)
				(gSounds eachElementDo: #perform saveVolume)
				(= ticks 60)
			)
			(1
				(clockSound
					number:
					(switch local1
						(0 23)
						(1 20)
						(2 21)
						(3 22)
					)
					play: self
				)
			)
			(2 (= ticks 60))
			(3
				(gSounds eachElementDo: #perform restoreVolume)
				(if
					(= temp40
						(switch register
							(1000 -24319)
							(1245 4104)
							(145 8224)
							(245 12290)
							(else  0)
						)
					)
					((ScriptID 90 15)
						setReal: (ScriptID 90 15) 0 15 0 temp40
					)
				)
				(clockInset dispose:)
			)
		)
	)
)

(instance quarterHand of View
	(properties
		x 33
		y 19
		view 22
		loop 2
		priority 15
		signal $4010
	)
)

(instance hourHand of View
	(properties
		x 33
		y 19
		view 22
		loop 1
		priority 15
		signal $4010
	)
)

(instance clockInset of Inset
	(properties
		view 22
		; BUGFIX: Fix the chiming clock's visual priority.
		;
		; The view used by clockInset doesn't have enough visual priority and
		; will appear behind some elements in various rooms, like the black
		; frame of the Medieval Armory's corridor (#448) or a vertical tube in
		; the Alcohol Lab (#610). The clock's hands (hourHand, quarterHand) are
		; unaffected; both hands use a priority of 15 to be shown on top, but
		; clockInset's priority isn't specified.
		;
		; We fix it by setting its priority property to 15 to match hourHand and
		; quarterHand.
		priority 15
		; END OF BUGFIX
	)
	
	(method (init)
		(super init: &rest)
		(self setScript: sShowClock 0 local2)
	)
	
	(method (dispose)
		(clockSound dispose:)
		(super dispose: &rest)
		(switch local2
			(1015
				((ScriptID 90 3) moveTo: -2)
			)
			(1115
				((ScriptID 90 1) goTo: 440)
				((ScriptID 90 2) goTo: 520)
				((ScriptID 90 4) moveTo: -1)
			)
			(1245
				((ScriptID 90 6) moveTo: -2)
			)
			(145
				((ScriptID 90 1) goTo: 520)
				((ScriptID 90 4) goTo: 430)
			)
			(215
				(if (== ((gInv at: 14) owner?) 520)
					((gInv at: 14) owner: 630)
				)
			)
			(245
				((ScriptID 90 4) goTo: 510)
				((ScriptID 90 1) goTo: 520)
			)
		)
		(if
		(and (proc0_2 1) (gOldCast contains: (ScriptID 35 0)))
			((ScriptID 35 0) dispose:)
		)
		(if
		(and (proc0_2 2) (gOldCast contains: (ScriptID 90 7)))
			((ScriptID 90 7) wandering: 0 setScript: 0 moveTo: -2)
		)
		(if
		(and (proc0_2 3) (gOldCast contains: (ScriptID 90 5)))
			((ScriptID 90 5) wandering: 0 setScript: 0 moveTo: -2)
		)
		(if
		(and (proc0_2 5) (gOldCast contains: (ScriptID 90 6)))
			((ScriptID 90 6) wandering: 0 setScript: 0 moveTo: -2)
		)
		(if
		(and (proc0_2 6) (gOldCast contains: (ScriptID 90 1)))
			((ScriptID 90 1) wandering: 0 setScript: 0 room: -2)
		)
		(DisposeScript 22)
	)
	
	(method (handleEvent pEvent)
		(pEvent claimed: 1)
		(super handleEvent: pEvent)
	)
)

(instance clockSound of Sound
	(properties
		flags $0001
	)
)
