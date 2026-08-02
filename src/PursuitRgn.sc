;;; Sierra Script 1.0 - (do not remove this comment)
(script# 94)
(include sci.sh)
(use Main)
(use Timer)
(use Game)
(use Obj)

(public
	PursuitRgn 0
	pursuitTimer 1
)

(local
	local0
)
(class PursuitRgn of Rgn
	(properties
		script 0
		number 0
		modNum -1
		noun 0
		timer 0
		keep 0
		initialized 0
	)
	
	(method (init)
		(super init:)
		(cond 
			((< global87 5) (= local0 60))
			((< global87 10) (= local0 40))
			((<= global87 15) (= local0 20))
		)
		(if (not (HaveMouse)) (= local0 (* 2 local0)))
	)
	
	(method (newRoom newRoomNumber)
		(= initialized 0)
		(= keep
			(proc999_5
				newRoomNumber
				420
				430
				435
				440
				448
				450
				454
				460
				480
				490
				660
			)
		)
		; BUGFIX: Fix "death scripts" being dropped during pre-room-change scripts.
		;
		; pursuitTimer is set during act 5 chase. All the rooms involved in the chase
		; have a notify method that will be called by pursuitTimer:cue right when
		; pursuitTimer expires. This notify method sets a "death script" (it's a
		; different one depending on the script file) as the room's script that will
		; spawn the murderer and kill Laura.
		;
		; It's possible for a room to already have a script attached when pursuitTimer
		; expires. That would make the current room's script be suddenly interrupted by
		; the "death script", creating all kinds of unpredictable issues. Sierra dealt
		; with this by testing in the notify methods if the room has any script attached
		; and queueing the "death script" to be set right after the current room's
		; script finishes.
		;
		; There's an edge case: if pursuitTimer expires while the current room has a
		; script attached, and such script is one used to transition to the next room,
		; the queued "death script" will be dropped during the room change, making the
		; murderer no longer appear and leaving the timer with 0 seconds. This can occur
		; in almost every room involved in the chase and Sierra never put anything to
		; prevent this.
		;
		; The present class, PursuitRgn, is initialized by Main (#0) during act 5 every
		; time a room involved in the chase starts. These rooms will in turn set it as
		; their region. PursuitRgn wraps pursuitTimer, and this newRoom method is called
		; right before a new room starts and disposes pursuitTimer if the target room
		; is no longer part of the chase sequence.
		;
		; We fix every room-transition bug across the chase sequence by testing if
		; pursuitTimer's seconds property is zero when a target room is one involved in
		; in the chase (timers' seconds property defaults to -1, it'll only reach 0 upon
		; expiry and will remain there until disposed or re-armed, so being 0 while
		; changing rooms unambiguously means the "death script" was dropped). If it's 0,
		; we re-arm it with one cycle, allowing it to properly expire in the next room
		; so it can trigger its "death script".
;;;		(if (not keep) (pursuitTimer dispose: delete:))
		(if (not keep) ; is keep NOT true? (is set as true above when the target room is part of the chase)
			(pursuitTimer dispose: delete:) ; dispose and delete pursuitTimer
		else
			(if	(not (pursuitTimer seconds?)) ; is pursuitTimer's seconds zero?
				(pursuitTimer setCycle: pursuitTimer 1) ; re-arm pursuitTimer with 1 cycle
			)
		)
		; END OF BUGFIX
	)
	
	(method (increaseTime)
		(pursuitTimer
			seconds: (+ (pursuitTimer seconds?) local0)
		)
	)
	
	(method (decreaseTime)
		(pursuitTimer
			seconds: (- (pursuitTimer seconds?) local0)
		)
	)
)

(instance pursuitTimer of Timer
	(properties)
	
	(method (cue)
		(global2 notify:)
	)
)
