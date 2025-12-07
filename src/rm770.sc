;;; Sierra Script 1.0 - (do not remove this comment)
(script# 770)
(include sci.sh)
(use Main)
(use LBRoom)
(use Blink)
(use RandCycle)
(use n958)
(use Cycle)
(use View)
(use Obj)

(public
	rm770 0
	O_Riley 19
)

(local
	local0
	local1
	local2
	local3
)
(instance rm770 of LBRoom
	(properties
		picture 770
		style $000a
	)
	
	(method (init)
		(proc958_0 128 770 771)
		(proc958_0 132 770)
		(super init:)
		(gWrapSound number: 771 loop: -1 flags: 1 play:)
		(gIconBar disable:)
		(gGame setCursor: 996)
		(bird init: setScript: sFly)
		(bird2 init: setScript: sLand 0 4)
;;;		(murderer init:)
		(murderer init: stopUpd:) ; BUGFIX: Start in stopUpd to prevent part of its view disappearing when the first message is shown (see also sRunIt)
		(cop2 init: setScript: (sRandomScr new:))
		(cop3 init:)
		(cop5 init:)
		(cop6 init:)
		(badguy1 init: setScript: (sRandomScr new:))
		(badguy2 init: setScript: sRandomScr2)
		(badguy3 init:)
		(self setScript: sRunIt)
	)
	
	(method (dispose)
		(gWrapSound fade: 0 12 30 1)
		(super dispose:)
	)
)

(instance sFly of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setLoop: (Random 0 3) setPri: 15 setCycle: Fwd)
				(switch (client loop?)
					(0
						(client posn: 329 30 setMotion: MoveTo -10 17 self)
					)
					(1
						(client posn: -10 17 setMotion: MoveTo 329 30 self)
					)
					(2
						(client posn: -10 95 setMotion: MoveTo 174 -15 self)
					)
					(3
						(client posn: 329 136 setMotion: MoveTo 198 -17 self)
					)
				)
			)
			(1 (self changeState: 0))
		)
	)
)

(instance sLand of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(= temp0 (if (== register 4) 199 else 171))
				(= temp1 (if (== register 4) 116 else 124))
				(client
					setLoop: register
					setCycle: Fwd
					setMotion: MoveTo temp0 temp1 self
				)
			)
			(1
				(client setLoop: (+ register 2) setCycle: End self)
			)
			(2
				(client
					setLoop: (+ register 4)
					cycleSpeed: 10
					setCycle: RandCycle
				)
				(if (== client bird2)
					(bird3 init: setScript: (sLand new:) 0 5)
				)
			)
		)
	)
)

; BUGFIX: a) Prevent part of murderer's view disappearing while messages show on
; top, and b) fix rock-breaking sounds that stop being triggered.
;
; a) The murderer instance (of Prop) is being updated while the messages are
; shown causing its view underneath partially disappear. This happens due to how
; transparency is handled by the game, and it can be prevented by stopping
; murderer's updates before displaying the messages by using murderer's stopUpd
; method. In this room the narrator is modeless, aggravating the problem and
; making timing things a more difficult task.
;
; b) The local1 variable is used as a signal to trigger a rock-breaking sound when
; its value is 0 (and murcerer's cel is 4), by sRunIt:doit. local1 is set to 1
; immediately after the sound plays to avoid it re-triggering consecutive times,
; then is set to 0 again when gGameMusic2 cues murderer. In TEXT message mode many
; sounds are handled by gGameMusic2 and they often overlap. When this happens the
; prior sound is interruped and replaced with the newer one, and the interrupted
; one won't execute the callback, leaving local1 with value 1 without means to
; reset it anymore.
;
; We fix how the view is displayed by calling murderer:stopUpd before every call
; of gLb2Messager:say, making the Prop stop updating before messages are shown.
; For this to work we add a state to let the animation truly finish before calling
; murderer:stopUpd, and that call also needs its own state to ensure the Prop
; isn't updating before the script continues. We fix the sounds not triggering
; by resetting local1 manually instead of relying on cueing.
(instance sRunIt of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not local1)
				(== (murderer cel?) 4)
				(== (murderer loop?) 0)
			)
;;;			(gGameMusic2 number: 770 flags: 1 loop: 1 play: murderer)
			(gGameMusic2 number: 770 flags: 1 loop: 1 play:) ; do not cue
			(= local1 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= local0 1) (= cycles 1))
			(1
				(gLb2Messager say: 1 0 0 local0 self)
			)
			(2
				(murderer setCycle: End self)
			)
			(3 ; new state to ensure the animation finished
				(= cycles 1)
			)
			(4 ; new state to make murderer stop updating
				(= local1 0) ; manually reset local1
				(murderer stopUpd:) ; stop updating murderer
				(= cycles 1)
			)
;;;			(3
			(5 ; increase state # by 2
				(if (< (++ local0) 4)
					(self changeState: 1)
				else
					(gLb2Messager say: 1 0 0 4 self)
				)
			)
;;;			(4
			(6 ; increase state # by 2
				(murderer setCycle: CT 4 1 self)
			)
;;;			(5
			(7 ; increase state # by 2
				(gGameMusic2 number: 770 flags: 1 loop: 1 play:)
				(murderer loop: 1 cel: 0 setCycle: End self)
			)
			(8 ; new state to ensure the animation finished
				(= cycles 1)
			)
			(9 ; new state to make murderer stop updating
				(= local1 0) ; manually reset local1
				(murderer stopUpd:) ; stop updating murderer
				(= cycles 1)
			)
;;;			(6
			(10 ; increase state # by 4
				(gLb2Messager say: 1 0 0 5 self)
			)
;;;			(7
			(11 ; increase state # by 4
				(gLb2Messager say: 1 0 0 6 self)
			)
;;;			(8
			(12 ; increase state # by 4
				(gWrapSound number: 772 flags: 1 loop: 1 play: self)
			)
;;;			(9
			(13 ; increase state # by 4
				(global2 newRoom: (if (== global126 1) 775 else 785))
			)
		)
	)
)
; END OF BUGFIX (see also rm770:init)

; BUGFIX: a) Fix animations of the generic prisoners and the cop not looping,
; b) fix rock-breaking sounds that stop being triggered, and c) prevent
; inconsistent animation of the prisoners.
;
; a) The generic prisoners and the left cop on the tower have animations that
; were intended to be looped, but they play only once. Unlike in the script
; attached to the room (sRunIt) where murderer loops without issues, in these
; scripts attached to the Props, "setCycle: End" doesn't cycle from the
; initial cel if the cel is the last one. The first time the Props are
; animated they reach their last cel. From that moment, "setCycle: End"
; doesn't animate them anymore and they remain on their last cel.
;
; b) Exact same sound issue described in sRunIt's bugfix.
;
; c) Here cueing the prisoners not only resets the variable used as signal to
; trigger sounds, it also makes their attached script (sRandomScr/sRandomScr2)
; advance a state in an unintended way because they call super:cue. The cue
; method of the View and Prop classes make their attached script advance one
; state with "(if script (script cue:))".
;
; We fix the animations by making the scripts set the cel of the Props to 0 if
; their cel is the last one. We take the same manual approach we took in
; sRunIt to fix the sounds, not relying on cueing. Since we no longer rely on
; cueing, the erratic script execution issue also gets fixed.
(instance sRandomScr of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not local2)
				; TEXT&SPEECH COMPAT FIX: The rock-breaking sounds of the prisoners can
				; interrupt the speech. They made those only play when the mode isn't
				; SPEECH (2) testing it beforehand, we now have the BOTH mode (3) and
				; also plays speech, so we need to tweak the test.
;;;				(!= global90 2) ; mode isn't SPEECH
				(== global90 1) ; mode is TEXT
				; END OF TEXT&SPEECH COMPAT FIX
				(== (client cel?) 4)
				(!= (client loop?) 5)
			)
;;;			(gGameMusic2 number: 770 flags: 1 loop: 1 play: badguy1)
			(gGameMusic2 number: 770 flags: 1 loop: 1 play:) ; do not cue
			(= local2 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks (Random 30 120)))
;;;			(1 (client setCycle: End self))
			(1
				(if (== (client cel?) (client lastCel?)) (client setCel: 0)) ; reset cel if it's the last one
				(client setCycle: End self))
;;;			(2 (self changeState: 0))
			(2
				(= local2 0) ; manually reset local2 after the animation finishes
				(self changeState: 0))
		)
	)
)

(instance sRandomScr2 of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not local3)
				; TEXT&SPEECH COMPAT FIX, same change done in sRandomScr:doit.
;;;				(!= global90 2) ; mode isn't SPEECH
				(== global90 1) ; mode is TEXT
				; END OF TEXT&SPEECH COMPAT FIX
				(== (client cel?) 4)
			)
;;;			(gGameMusic2 number: 770 flags: 1 loop: 1 play: badguy2)
			(gGameMusic2 number: 770 flags: 1 loop: 1 play:) ; do not cue
			(= local3 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks (Random 30 120)))
;;;			(1 (client setCycle: End self))
			(1
				(if (== (client cel?) (client lastCel?)) (client setCel: 0)) ; reset cel if it's the last one
				(client setCycle: End self))
;;;			(2 (self changeState: 0))
			(2
				(= local3 0) ; manually reset local3 after the animation finishes
				(self changeState: 0)
			)
		)
	)
)
; END OF BUGFIX

(instance bird of Actor
	(properties
		x -10
		y 17
		view 771
		loop 1
		signal $6000
		cycleSpeed 0
		moveSpeed 0
	)
)

(instance bird2 of Actor
	(properties
		x 329
		y 8
		view 771
		loop 4
		signal $6000
		cycleSpeed 0
		moveSpeed 0
	)
)

(instance bird3 of Actor
	(properties
		x 58
		y -18
		view 771
		loop 5
		signal $6000
		cycleSpeed 0
		moveSpeed 0
	)
)

(instance murderer of Prop
	(properties
		x 162
		y 176
		view 770
		cel 9
		cycleSpeed 15
	)
	
	(method (cue) ; unused after the bugfixes, kept to not need a heap patch
;;;		(= local1 0)
;;;		(super cue:)
	)
)

(instance cop2 of Prop
	(properties
		x 207
		y 45
		view 770
		loop 5
		cel 8
		cycleSpeed 15
	)
)

(instance cop3 of Prop
	(properties
		x 243
		y 49
		view 770
		loop 6
		cel 6
	)
)

(instance cop5 of Prop
	(properties
		x 275
		y 155
		view 770
		loop 6
		cel 5
	)
)

(instance cop6 of Prop
	(properties
		x 45
		y 151
		view 770
		loop 6
		cel 4
	)
)

(instance badguy1 of Prop
	(properties
		x 280
		y 131
		view 770
		loop 3
		cel 3
		cycleSpeed 15
	)
	
	(method (cue) ; unused after the bugfixes, kept to not need a heap patch
;;;		(= local2 0)
;;;		(super cue:)
	)
)

(instance badguy2 of Prop
	(properties
		x 247
		y 154
		view 770
		loop 2
		cycleSpeed 15
	)
	
	(method (cue) ; unused after the bugfixes, kept to not need a heap patch
;;;		(= local3 0)
;;;		(super cue:)
	)
)

(instance badguy3 of Prop
	(properties
		x 313
		y 136
		view 770
		loop 4
	)
)

(instance O_Riley of Narrator
	(properties
		x 10
		y 155
		modeless 1
		back 15
		name "O'Riley"
	)
	
	(method (init)
		(= font gFont)
		(super init: &rest)
	)
)
