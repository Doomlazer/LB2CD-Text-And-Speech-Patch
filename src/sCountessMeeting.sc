;;; Sierra Script 1.0 - (do not remove this comment)
(script# 441)
(include sci.sh)
(use Main)
(use PolyPath)
(use Timer)
(use Cycle)
(use Obj)

(public
	sCountessMeeting 0
	sCountessNoMeet 1
	sCountessLeaves 2
	sTalkWithCountess 3
	countTimer 4
)

(instance sCountessMeeting of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1 (= cycles 1))
			(2
				(self setScript: sCountessEnters self)
			)
			(3
				((ScriptID 90 1) setMotion: PolyPath 141 171 self)
			)
			(4
				((ScriptID 90 1) view: 444 loop: 0 cel: 0)
				(gGame handsOn:)
				(if (== (gEgo view?) 443)
					(gIconBar disable: 1 2 6 7 5)
				)
				(= cycles 1)
			)
			(5 (= seconds 10))
			(6
				(if (== (gEgo view?) 443)
					(gIconBar disable: 1 2 6 7 5)
				)
				(gGame handsOff:)
				(= cycles 3)
			)
			(7
				(if (== (gEgo view?) 443)
					((ScriptID 90 1) setCycle: End self)
				else
					(gGame handsOn: 1)
					(self dispose:)
				)
			)
			(8 (= seconds 2))
			(9
				((ScriptID 90 1) setCycle: Beg self)
			)
			(10 (= seconds 3))
			(11
				(gGame handsOn: 1)
				(client setScript: sCountessLeaves)
			)
		)
	)
)

(instance sCountessNoMeet of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1 (= cycles 1))
			(2
				(self setScript: sCountessEnters self)
			)
			(3 (= seconds 2))
			(4
				(client setScript: sCountessLeaves)
			)
		)
	)
)

(instance sCountessEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			; BUGFIX:
			; a) Prevent hands-off from being reverted during the countess' meeting
			; b) Prevent the countess' from passing through the door during the meeting
			; c) Fix crash when closing the door right after the countess' meeting
			;
			; a) During act 3, if rm440's door (rm440Door) is closed when the countess'
			; meeting starts, the door will be opened and hands-off will be reverted right
			; after it opens, giving control back to the player. This happens because
			; rm440Door:cue (inherited from Door, in #954) calls handsOn(1) when its
			; exitType property is set to 2 (the default value).
			;
			; We fix this by adding a new state (0) to check if the door isn't open, in that
			; case we set rm440Door's exitType property to 3, this is an invalid value that
			; will make rm440Door:cue bypass handsOn(1) when it's opened. We then add code
			; to this script's last state to set its value back to 2.
			;
			; b) During act 3, if rm440's door (rm440Door) is being closed when the
			; countess' meeting starts, the countess will pass through the closed door. This
			; happens because when when sCountessEnters checks if the door is closed (in
			; state 1) to open it, it still isn't, and the script will continue without
			; opening the door, which will be already closed when the countess enters.
			;
			; We fix it by adding a new state (1) to check if the door has a cycler attached
			; (is opening/closing), if so we reduce sCountessEnters' state property by 1 to
			; make the next state be again the current one. This loop will keep going on
			; until the door is fully open/closed, so the test in the next state that checks
			; if the door is closed will now be reliable.
			;
			; c) During act 3, if rm440's door is closed when the countess' meeting starts
			; and the player tries to close the door right after the meeting ends, the game
			; will crash. This happens because when the door is opened in state 1, they also
			; set rm440Door's caller property to self, so sCountessEnters is cued after the
			; door opens to make this script move to its next state. When the meeting ends,
			; sCountessEnters is disposed but rm440Door's caller property still references
			; it, making the game crash the moment the door opens and rm440Door:cue attempts
			; to call a no longer existant sCountessEnters. This issue can't be reproduced
			; on ScummVM.
			;
			; We fix it by setting rm440Door's caller method to 0 in this script's last
			; state.
			(0 ; added state
				(if (!= ((ScriptID 440 2) doorState?) 2) ; is rm440Door not open?
					((ScriptID 440 2) exitType: 3) ; rm440Door. Set its exitType property to 3 so Door:cue bypasses handsOn(1)
				)
				(= cycles 1)
			)
			(1 ; added state
				(if ((ScriptID 440 2) cycler?) ; is rm440Door cycling (opening/closing)?
					(-- state) ; reduce state by 1. The current state will be the next state (repeat)
				)
				(= cycles 1)
			)
;;;			(0
			(2 ; increased state # by 2
				((ScriptID 90 1)
					moveTo: 440
					loop: 1
					x: 240
					y: 134
					actions: askQuestions
				)
				(if ((ScriptID 90 1) scaler?)
					(((ScriptID 90 1) scaler?) doit:)
				)
				(= cycles 1)
			)
;;;			(1
			(3 ; increased state # by 2
				((ScriptID 90 1) view: 825)
				(if (== ((ScriptID 440 2) doorState?) 0)
					((ScriptID 440 2) caller: self open:)
				else
					(= cycles 2)
				)
			)
;;;			(2
			(4 ; increased state # by 2
				((ScriptID 90 1) setMotion: PolyPath 122 154 self)
				(= ticks 480)
			)
			; IMPROVEMENT: Remove control panel restriction
			;
			; sCountessEnters:changeState(3) explicitly disables the control panel icon,
			; preventing the player from accessing the options menu. This is a limitation
			; that isn't present in the floppy version of the game.
			;
			; We disable the state to let the player access the control panel, matching the
			; floppy version's behavior.
;;;			(3 (gIconBar disable: 7))
			; END OF IMPROVEMENT
;;;			(4
			(6 ; increased state # by 2
				(if (== ((ScriptID 440 2) exitType?) 3) ; added check. Has rm440Door its exitType property set to 3?
					((ScriptID 440 2) caller: 0 exitType: 2) ; reset rm440Door's exitType and caller properties (defaults defined in LbDoor, script file #16)
				)
				(self dispose:)
			)
			; END OF BUGFIX
		)
	)
)

(instance sCountessLeaves of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== (gEgo view?) 443)
					(gIconBar disable: 1 2 6 7 5)
				else
					(gLb2WH delete: global2)
					(gLb2DH delete: global2)
				)
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				((ScriptID 90 1)
					view: 825
					setCycle: Walk
					setMotion: PolyPath 122 154 self
				)
			)
			(2
				((ScriptID 90 1) setMotion: PolyPath 233 134 self)
			)
			(3
				((ScriptID 90 1) setMotion: PolyPath 239 134 self)
			)
			(4
				(if (== (gEgo view?) 443) ; is Laura hiding in the tapestry? (view 443)
					(gGame handsOn: 1)
					(gUser canControl: 1)
				else
					(gGame handsOn:)
					; BUGFIX: Fix cursor not changing to a down arrow when hovering over the
					; south exit after the countess' meeting (1/2).
					;
					; In #440, southExitFeature makes the cursor change to a down arrow when
					; hovering over the bottom part of the room. Hiding in the tapestry
					; disposes the Feature making the cursor no longer change, and unhiding
					; initializes it back again.
					;
					; sOutTapestry, also in #440, is the script that triggers the unhiding
					; animation and initializes southExitFeature, but it behaves differently
					; during the countess' meeting: in state 2 it attaches the sTalkWithCountess
					; script to gEgo and disposes itself before having the chance to initialize
					; southExitFeature in state 3. The issue is that southExitFeature isn't
					; initialized when the meeting is over either, making the cursor no longer
					; change to the down arrow one until re-entering the room. This problem only
					; occurs when the player unhides before the countess leaves the room.
					;
					; Considering that southExitFeature is in #440 but isn't public and that
					; sOutTapestry is also in #440 and is public, we've chosen to fix this by:
					; - In script #440, we modify sOutTapestry:changeState(3) so it allows us to
					; bypass every instruction except the initialization of southExitFeature when
					; its register is set.
					; - Here, we test if there are no exits active (gLb2Exits size is zero when
					; it doesn't contain southExitFeature), if the test passes we attach
					; sOutTapestry to the current room via ScriptID while setting its register to
					; 1 and making it change to state 3 so it initializes southExitFeature.
					(if (not (gLb2Exits size?)) ; is gLb2Exits empty? (southExitFeature isn't initialized)
						(global2 setScript: ((ScriptID 440 1) register: 1 changeState: 3)) ; sOutTapestry, will initialize southExitFeature
					)
					; END OF BUGFIX (see also sOutTapestry:changeState(3), in #440)
				)
				((ScriptID 90 1) actions: 0 moveTo: 430 wandering: 1)
				(countTimer dispose:)
				(gGameMusic2 fade:)
				(WrapMusic pause: 0)
				(gIconBar enable: 7)
				(self dispose:)
				(DisposeScript 441)
			)
		)
	)
)

(instance sTalkWithCountess of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gEgo setMotion: PolyPath 100 172 self)
			)
			(1
				(gLb2Messager say: 1 0 1 0 self 1440)
			)
			(2
				(proc0_3 120)
				(countTimer setReal: countTimer 15)
				(gGame handsOn: 1)
;;;				(gIconBar disable: 7) ; IMPROVEMENT: Remove control panel restriction
				(self dispose:)
			)
		)
	)
)

(instance askQuestions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6
				(if (== (gEgo view?) 443)
					(global2 setScript: (ScriptID 440 1)) ; sOutTapestry
				else
					(switch (global2 setInset: (ScriptID 20 0))
						(1030
							(gLb2Messager say: 1 6 2 0 0 1440)
							(countTimer seconds: (+ (countTimer seconds?) 10))
						)
						(else 
							(gLb2Messager say: 1 6 4 0 0 1440)
							(countTimer seconds: 1)
						)
					)
				)
				1
			)
			(2
				(if (== (gEgo view?) 443)
					(global2 setScript: (ScriptID 440 1))
				else
					(gLb2Messager say: 1 2 0 0 0 1440)
				)
			)
			(17
				(gLb2Messager say: 1 17 0 0 0 1440)
				(countTimer seconds: (+ (countTimer seconds?) 10))
			)
			(else  0)
		)
	)
)

(instance countTimer of Timer
	(properties)
	
	(method (cue)
		(cond 
			((not ((ScriptID 90 1) mover?)) ((ScriptID 90 1) setScript: sCountessLeaves))
			(((ScriptID 90 1) script?) (((ScriptID 90 1) script?) next: sCountessLeaves))
		)
	)
)
