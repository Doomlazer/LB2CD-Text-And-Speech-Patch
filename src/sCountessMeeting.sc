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
			(0
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
			(1
				((ScriptID 90 1) view: 825)
				(if (== ((ScriptID 440 2) doorState?) 0)
					((ScriptID 440 2) caller: self open:)
				else
					(= cycles 2)
				)
			)
			(2
				((ScriptID 90 1) setMotion: PolyPath 122 154 self)
				(= ticks 480)
			)
;;;			(3 (gIconBar disable: 7)) ; IMPROVEMENT: Remove control panel restriction
			(4 (self dispose:))
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
