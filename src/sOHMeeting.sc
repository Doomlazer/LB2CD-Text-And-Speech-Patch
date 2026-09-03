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

; TWEAK + BUGFIX:
; Prevent Heimlich from passing through the door during his meeting
; with Olympia and combine sOHMeeting+sOHNoMeet.
;
; During act 3, when meetingTimer expires in the Medieval Armory (#442)
; at 2:00 am, rm440:notify will call sMeetingNo2 wich in turn will use
; ScriptID to call sOHMeeting if Laura is hiding in the tapestry, or to
; call sOHNoMeet if she isn't. This will make Heimlich and Olympia enter
; the room, have a conversation and leave afterwards. In the case of
; sOHNomeet the conversation doesn't take place, which is intended.
; However, if the Medieval Armory's door (rm440Door) is closed or closing
; when sOHNomeet/sOHMeeting starts, Heimlich will pass through the door
; when entering the room, and Olympia won't be visible during the scene.
; Opening the door before they enter the room would solve the issue, but
; none of these scripts has code to open it.
;
; We fix this by adding code to open rm440Door, but this requires various
; precautions:
; 1) Doors call handsOn(1) in their Door:cue method when they finish
; opening or closing, that would revert the hands-off set in sMeetingNo2.
; We set its exitType property to 3, which is an invalid value that makes
; it bypass handsOn(1) when it opens. To deal with it when it closes we
; need to keep checking if the player has control and call handsOff if so.
; 2) We have to make the script wait unless the door is either fully open
; or closed before deciding to open it or not, because meetingTimer could
; expire while the player is opening/closing it. We decided to make a
; state loop if the door is cycling (opening/closing).
; 3) We need to reset the rm440Door's exitType and caller properties
; before the meeting ends to not introduce crashes or softlocks if the
; player uses the door afterwards.
;
; Both sOHMeeting and sOHNoMeet need the same fixes, but adding the
; changes to both of them would result in a way larger compiled patch.
; To mitigate a bit this we combine sOHMeeting and sOHNoMeet in
; sOHMeeting, in a way that it can behave like either script depending if
; its register property is set. sOHNoMeet will now only have the role of
; calling sOHMeeting and set its register property.
;;;(instance sOHMeeting of Script
;;;	(properties)
;;;	
;;;	(method (changeState newState)
;;;		(switch (= state newState)
;;;			(0 (= cycles 1))
;;;			(1
;;;				((ScriptID 32 0)
;;;					init:
;;;					view: 814
;;;					loop: 1
;;;					setPri: 9
;;;					setScale: Scaler 155 0 190 90
;;;					x: 225
;;;					y: 138
;;;					room: 440
;;;				)
;;;				(= cycles 1)
;;;			)
;;;			(2
;;;				((ScriptID 32 0)
;;;					setPri: 9
;;;					setMotion: MoveTo 189 145 self
;;;				)
;;;			)
;;;			(3
;;;				((ScriptID 32 0)
;;;					setPri: -1
;;;					setMotion: MoveTo 127 154 self
;;;				)
;;;			)
;;;			(4
;;;				((ScriptID 90 2) setMotion: PolyPath 124 156 self)
;;;				((ScriptID 32 0) setMotion: MoveTo 86 159 self)
;;;			)
;;;			(5 0)
;;;			(6
;;;				(proc0_5 (ScriptID 32 0) (ScriptID 90 2))
;;;				(= cycles 5)
;;;			)
;;;			(7
;;;				(gLb2Messager say: 2 0 3 0 self 1440)
;;;			)
;;;			(8 (self dispose:))
;;;		)
;;;	)
;;;)
(instance sOHMeeting of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(if (!= ((ScriptID 440 2) doorState?) 2) ; is rm440Door not open?
					((ScriptID 440 2) exitType: 3) ; rm440Door. Set its exitType property to 3 so Door:cue bypasses handsOn(1)
				)
				(= cycles 1)
			)
			(2
				(if (gUser canControl?) ; does the player have control? (avoid setting hands-off if it's already set)
					(gGame handsOff:) ; set hands-off
					(gIconBar enable: 7) ; keep the settings icon enabled (it was enabled in the original hands-off)
				)
				(if ((ScriptID 440 2) cycler?) ; is rm440Door cycling (opening/closing)?
					(-- state) ; reduce state by 1. The current state will be the next state (repeat)
				)
				(= cycles 1)
			)
			(3
				(if (== ((ScriptID 440 2) doorState?) 0) ; is rm440Door closed?
					((ScriptID 440 2) caller: self open:) ; open rm440Door and make it cue this script (due to caller=self) once it's open
				else
					(= cycles 1)
				)
			)
			(4
				(if register ; is register 1? -> called via sOHNoMeet (failed meeting)
					(gEgo setMotion: MoveTo 98 161 self) ; (moved here from sOHNoMeet)
				else
					(= cycles 1)
				)
			)
			(5
				((ScriptID 32 0)
					init:
					view: 814
					setPri: 9
					x: 225
					y: 138
					room: 440
				)
				(if register ; is register 1? -> called via sOHNoMeet (failed meeting)
					(proc0_5 gEgo (ScriptID 32 0)) ; (moved here from sOHNoMeet)
				else
					((ScriptID 32 0) setScale: Scaler 155 0 190 90)
				)
				(= cycles 1)
			)
			(6
				((ScriptID 32 0)
					setPri: 9
					setMotion: MoveTo 189 145 self
				)
			)
			(7
				((ScriptID 32 0)
					setPri: -1
					setMotion: MoveTo 127 154 self
				)
			)
			(8
				(if register ; is register 1? -> called via sOHNoMeet (failed meeting)
					((ScriptID 90 2) setMotion: PolyPath 176 150 self) ; (moved here from sOHNoMeet)
					(= state 11) ; set state to 11 to make the next state be 12 (skip 9, 10 and 11)
				else
					((ScriptID 90 2) setMotion: PolyPath 124 156 self)
					((ScriptID 32 0) setMotion: MoveTo 86 159 self)
				)
			)
			(9 0)
			(10
				(proc0_5 (ScriptID 32 0) (ScriptID 90 2))
				(= cycles 5)
			)
			(11
				(gLb2Messager say: 2 0 3 0 self 1440)
			)
			(12
				(if (== ((ScriptID 440 2) exitType?) 3) ; has rm440Door its exitType property set as 3?
					((ScriptID 440 2) exitType: 2 caller: 0) ; reset rm440Door's exitType and caller properties (defaults defined in LbDoor, script file #16)
				)
				(self dispose:))
		)
	)
)


;;;(instance sOHNoMeet of Script
;;;	(properties)
;;;	
;;;	(method (changeState newState)
;;;		(switch (= state newState)
;;;			(0 (= cycles 1))
;;;			(1
;;;				(gEgo setMotion: MoveTo 98 161 self)
;;;			)
;;;			(2
;;;				((ScriptID 32 0)
;;;					init:
;;;					view: 814
;;;					setPri: 9
;;;					x: 225
;;;					y: 138
;;;					room: 440
;;;				)
;;;				(proc0_5 gEgo (ScriptID 32 0))
;;;				(= cycles 1)
;;;			)
;;;			(3
;;;				((ScriptID 32 0)
;;;					setPri: 9
;;;					setMotion: MoveTo 189 145 self
;;;				)
;;;			)
;;;			(4
;;;				((ScriptID 32 0)
;;;					setPri: -1
;;;					setMotion: MoveTo 127 154 self
;;;				)
;;;			)
;;;			(5
;;;				((ScriptID 90 2) setMotion: PolyPath 176 150 self)
;;;			)
;;;			(6 (self dispose:))
;;;		)
;;;	)
;;;)
(instance sOHNoMeet of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1 (self setScript: sOHMeeting self 1)) ; call sOHMeeting, make it cue this script and set its register to 1
			(2 (self dispose:))
		)
	)
)
; END OF TWEAK + BUGFIX

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
				; When Olympia's meeting with Heimlich ends, both leave through the east door
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
