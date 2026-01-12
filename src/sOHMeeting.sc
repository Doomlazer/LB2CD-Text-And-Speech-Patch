;;; Sierra Script 1.0 - (do not remove this comment)
(script# 442)
(include sci.sh)
(use Main)
(use Scaler)
(use PolyPath)
(use Cycle)
(use Obj)

(public
	sOHMeeting 0
	sOHNoMeet 1
	sOHLeave 2
)

(local
	local0
)
(instance sOHMeeting of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				((ScriptID 32 0)
					init:
					view: 814
					loop: 1
					setPri: 9
					setScale: Scaler 155 0 190 90
					x: 225
					y: 138
					room: 440
				)
				(= cycles 1)
			)
			(2
				((ScriptID 32 0)
					setPri: 9
					setMotion: MoveTo 189 145 self
				)
			)
			(3
				((ScriptID 32 0)
					setPri: -1
					setMotion: MoveTo 127 154 self
				)
			)
			(4
				((ScriptID 90 2) setMotion: PolyPath 124 156 self)
				((ScriptID 32 0) setMotion: MoveTo 86 159 self)
			)
			(5 0)
			(6
				(proc0_5 (ScriptID 32 0) (ScriptID 90 2))
				(= cycles 5)
			)
			(7
				(gLb2Messager say: 2 0 3 0 self 1440)
			)
			(8 (self dispose:))
		)
	)
)

(instance sOHNoMeet of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(gEgo setMotion: MoveTo 98 161 self)
			)
			(2
				((ScriptID 32 0)
					init:
					view: 814
					setPri: 9
					x: 225
					y: 138
					room: 440
				)
				(proc0_5 gEgo (ScriptID 32 0))
				(= cycles 1)
			)
			(3
				((ScriptID 32 0)
					setPri: 9
					setMotion: MoveTo 189 145 self
				)
			)
			(4
				((ScriptID 32 0)
					setPri: -1
					setMotion: MoveTo 127 154 self
				)
			)
			(5
				((ScriptID 90 2) setMotion: PolyPath 176 150 self)
			)
			(6 (self dispose:))
		)
	)
)

(instance sOHLeave of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(== (self state?) 3)
				(not ((ScriptID 90 2) mover?))
				(not ((ScriptID 32 0) mover?))
				(not local0)
			)
			(= local0 1)
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				((ScriptID 90 2) setMotion: PolyPath 228 133 sOHLeave)
				((ScriptID 32 0)
					setPri: -1
					setMotion: MoveTo 189 145 self
				)
			)
			(2
				((ScriptID 32 0)
					setPri: 9
					setMotion: MoveTo 230 138 self
				)
			)
			(3 0)
			(4 (= seconds 1))
			(5
				; BUGFIX: Fix Laura being unable to hide in the tapestry after Olympia's
				; meeting with Heimlich.
				;
				; When Olympia's meeting with Heimlich ends, both leave through the East door
				; but the room property of aHeimlich keeps being 440. In the case of aOlympia,
				; when the meeting ends its room property is changed to 430 in sMeetingNo2's
				; state 7 (in #440), and then the actor is put to wander. aHeimlich's room
				; property isn't changed. This normally wouldn't be a big deal, but in this
				; case Laura won't be able to hide in room 440's tapestry after both have
				; left, as hiding requires a "(MuseumRgn nobodyAround:)" test to pass, and it
				; won't return true because aHeimlich "is still in the room". This gets
				; corrected after re-entering the room.
				;
				; We fix it by manually setting aHeimlich's room property to 350 (default)
				; once the meeting ends. Any value except 440 fixes it.
;;;				((ScriptID 32 0) dispose:)
				((ScriptID 32 0) room: 350 dispose:) ; aHeimlich
				; END OF BUGFIX
				(self dispose:)
			)
		)
	)
)
