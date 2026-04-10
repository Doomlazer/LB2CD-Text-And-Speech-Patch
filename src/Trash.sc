;;; Sierra Script 1.0 - (do not remove this comment)
(script# 250)
(include sci.sh)
(use Main)
(use LBRoom)
(use n027)
(use Print)
(use Inset)
(use CueObj)
(use n958)
(use Sound)
(use Cycle)
(use User)
(use View)
(use Obj)

(public
	rm250 0
)

(local
	local0
	theTrash
	local2
)
(instance rm250 of LBRoom
	(properties
		noun 14
		picture 250
	)
	
	(method (init &tmp [temp0 50])
		(proc958_0 128 250 251 252 253 254)
		(proc958_0 132 300 41 250 252)
		(noise number: 41 flags: 1 play:)
		(super init:)
		(proc0_8 1)
		(gLb2WH addToFront: global2)
		(User canInput: 1)
		(laura cel: (if (gEgo wearingGown?) 1 else 0) addToPic:)
		(license init:)
		(if (proc0_10 16 1)
			(trash1 init:)
			(trash2 init:)
			(trash3 init:)
			(trash4 init:)
			(trash5 init:)
			(cornerTrash addToPic:)
			(if
				(and
					(not (gEgo wearingGown?))
					(not (gEgo has: 1))
					(not (gEgo has: 32))
				)
				(ticket init:)
			)
			(DDriver addToPic:)
		else
			(CDriver addToPic:)
		)
		(gWrapSound number: 250)
		(win1 init: stopUpd:)
		(win2 init: stopUpd:)
		(win3 init: stopUpd:)
		(win4 init: stopUpd:)
		(win5 init: stopUpd:)
		(gNarrator y: 120)
		(cond 
			((and (== gGNumber 300) (gEgo wearingGown?)) (self setScript: sACTBREAK))
			((not (gEgo has: 6))
				(if (gOldCast contains: trash1)
					(self setScript: sNoPressPassD)
				else
					(self setScript: sNoPressPassC)
				)
			)
			(else (self setScript: sHasPressPass))
		)
	)
	
	; BUGFIX: Fix graphical glitches left by the messager box (TALK verb).
	;
	; When using the TALK verb on a View/Feature/Prop that doesn't have a
	; specific TALK case (2) in its doVerb, and instead passes that verb to
	; its super class, a random message (out of 4) from 0.msg will be shown.
	; This is the correct expected behavior, but the fourth message for the
	; TALK verb is too lengthy to properly fit.
	;
	; rm250 sets gNarrator's y property to 120 during initialization, making
	; messages appear close to the bottom of the screen. If the fourth TALK
	; message from 0.msg (which is 7 lines long) is shown, the game will
	; enlarge the message box  in a glitchy way to display this message that
	; otherwise wouldn't fit. When that message is disposed, graphical
	; remains of its box will stay on screen until the room changes. ScummVM
	; isn't affected by this bug (the glitchy window is shown but it's
	; properly cleared after being disposed).
	;
	; We fix it by adding a TALK case to rm250:doVerb, it will choose a
	; random number between 1 and 4 to display one of the TALK messages, but
	; temporarily setting gNarrator's y property to 96 if the random number
	; is 4 (fourth message), this way the message will always fit. We then
	; add a TALK case to every other object that doesn't have a specific
	; case in its doVerb, to pass the verb to rm250:doVerb so it handles it
	; in their place.
;;;	(method (doVerb theVerb)
	(method (doVerb theVerb &tmp temp0) ; we need a new variable to store the random number
		(switch theVerb
			(13 ; EXIT
				(global2 newRoom: (if gGNumber else 210))
			)
			; START OF ADDITIONS
			(2 ; TALK
				(= temp0 (Random 1 4))
					(if (== temp0 4)
						(gNarrator y: 96)
					)
				(gLb2Messager say: 0 2 0 temp0 0 0)
				(gNarrator y: 120)
			)
			; END OF ADDITIONS
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	; END OF BUGFIX (see also the doVerb method of win1/2/3/4/5, ticket,
	; license, Trash and cornerTrash)
	
	(method (newRoom)
		(if inset (inset dispose:))
		(gLb2WH delete: global2)
		(proc0_8 0)
		(if (& global90 $0002) (DoAudio 3))
		(gWrapSound stop:)
		(gGameMusic2 stop:)
		(super newRoom: &rest)
	)
)

(instance sACTBREAK of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gIconBar disable:)
				(gIconBar disable: 7)
				(proc27_2)
				((ScriptID 21 1) doit: 1029)
				(win1 setCycle: Fwd)
				(win2 setCycle: Fwd)
				(win3 setCycle: Fwd)
				(win4 setCycle: Fwd)
				(win5 setCycle: Fwd)
				(gGameMusic2 send: 2 224 2400)
				(= cycles 1)
			)
			(1
				(gGameMusic2 send: 2 224 2800)
				(= cycles 1)
			)
			(2
				(gGameMusic2 send: 2 224 3200)
				(= cycles 1)
			)
			(3
				(gGameMusic2 send: 2 224 3600)
				(= cycles 1)
			)
			(4
				(gGameMusic2 send: 2 224 4000)
				(gWrapSound number: 300 loop: 1 flags: 1 play: self)
			)
			(5
				(gGameMusic2 send: 2 224 3000)
				(= cycles 1)
			)
			(6
				(gGameMusic2 send: 2 224 2000)
				(= cycles 1)
			)
			(7
				(gGameMusic2 send: 2 224 1000)
				(= cycles 1)
			)
			(8
				(gGameMusic2 send: 2 224 500)
				(= cycles 1)
			)
			(9
				(gGameMusic2 send: 2 224 0)
				(= cycles 5)
			)
			(10
				(gWrapSound fade:)
				(gGameMusic2 fade:)
				(global2 newRoom: 26)
			)
		)
	)
)

(instance sNoPressPassD of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(User canInput: 1)
				(= cycles 1)
			)
			(1
				(gLb2Messager say: 1 0 9 1 self)
			)
			(2
				(gLb2Messager say: 1 0 10 1 self)
			)
			(3
				(gLb2Messager say: 1 0 9 2 self)
			)
			(4
				(gGame handsOn:)
				(= seconds 15)
			)
			(5
				(gLb2Messager say: 1 0 9 3 self)
			)
			(6
				(global2 newRoom: (if gGNumber else 210))
			)
		)
	)
)

(instance sNoPressPassC of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(User canInput: 1)
				(= cycles 1)
			)
			(1
				(gLb2Messager say: 1 0 1 1 self)
			)
			(2
				(gLb2Messager say: 1 0 10 1 self)
			)
			(3
				(gLb2Messager say: 1 0 1 2 self)
			)
			(4
				(gGame handsOn:)
				(= seconds 15)
			)
			(5
				(gLb2Messager say: 1 0 1 3 self)
			)
			(6
				(global2 newRoom: (if gGNumber else 210))
			)
		)
	)
)

(instance sHasPressPass of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(User canInput: 1)
				(= cycles 1)
			)
			(1
				(cond 
					(
					(and (gOldCast contains: trash1) (not (proc0_2 26))) (client setScript: s1stTimeInDirtyTaxi self))
					((gOldCast contains: trash1) (gLb2Messager say: 1 0 7 6 self))
					((not (gOldCast contains: trash1)) (gLb2Messager say: 1 0 8 0 self))
					(else (= cycles 1))
				)
			)
			(2
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance s1stTimeInDirtyTaxi of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(User canInput: 1)
				(= cycles 1)
			)
			(1
				(gLb2Messager say: 1 0 7 1 self)
			)
			(2
				(gLb2Messager say: 1 0 7 2 self)
				(proc0_3 26)
			)
			(3
				(= register 0)
				(switch
					(Print
						addText: 16 0 0 0
						addButton: 1 15 0 0 1 5 18
						addButton: 2 15 0 0 2 5 48
						init:
					)
					(1
						(gLb2Messager say: 1 0 7 6 self)
					)
					(2
						(gLb2Messager say: 1 0 7 5 self)
						(= register 1)
					)
					(else  (= cycles 1))
				)
			)
			(4 (= seconds 1))
			(5
				(if (== register 1)
					(global2 newRoom: (if gGNumber else 210))
				else
					(= cycles 1)
				)
			)
			(6
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sWhereToBud of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(User canInput: 1)
				(= cycles 1)
			)
			(1
				(if (not (gOldCast contains: trash1))
					(gLb2Messager say: 5 11 8 0 self)
				else
					(gLb2Messager say: 4 11 7 0 self)
				)
			)
			(2
				(switch (global2 setInset: (ScriptID 20 0))
					(513 (= local0 210))
					(515 (= local0 260))
					(516 (= local0 240))
					(514 (= local0 280))
					(518 (= local0 300))
					(520 (= local0 300))
					(517 (= local0 330))
					(519
						(= local2 1)
						(= local0 250)
						(if (not (gOldCast contains: trash1))
							(gLb2Messager say: 17 14 8 0)
						else
							(gLb2Messager say: 17 14 7 0)
						)
					)
					(-1 (= local0 250))
					(else 
						(= local0 250)
						(if (not (gOldCast contains: trash1))
							(gLb2Messager say: 12 14 8 0)
						else
							(gLb2Messager say: 12 14 7 0)
						)
					)
				)
				(= cycles 1)
			)
			(3
				(cond 
					((or (== local0 gGNumber) (== local0 gNumber)) (gGame handsOn:) (= cycles 1))
					((not (gOldCast contains: trash1)) (global2 setScript: sDoTakeOffFlight))
					(else (global2 setScript: sMoveBuildings))
				)
			)
			(4
				(gIconBar enable: 5)
				(if (!= local0 gNumber)
					(global2 newRoom: local0)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance sDoTakeOffFlight of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0
				; BUGFIX: Fix Laura's messages pausing street animation during taxi travels.
				;
				; If the player uses a verb on any object in the taxi, the moving street
				; animation will pause until the message is disposed. This doesn't happen
				; while Rocco/Bob talk. The talker used for Laura wasn't set as modeless,
				; unlike Rocco/Bob talkers.
				;
				; We fix it by setting gNarrator (99) as modeless. It will reset on newRoom.
				(gNarrator modeless: 1)
				; END OF BUGFIX (the same has been done in sMoveBuildings:changeState(0))
				(gGame handsOff:)
				(User canInput: 1)
				(= cycles 1)
			)
			(1
				(win1 setCycle: Fwd)
				(win2 setCycle: Fwd)
				(win3 setCycle: Fwd)
				(win4 setCycle: Fwd)
				(win5 setCycle: Fwd)
				(= cycles 1)
			)
			(2
				(gWrapSound number: 250 loop: 1 flags: 1 play:)
				(gGame handsOn:)
				(gIconBar disable: 5 6 0)
				(= cycles 1)
			)
			(3
				(gGameMusic2 send: 2 224 1000)
				(= cycles 1)
			)
			(4
				(gGameMusic2 send: 2 224 2000)
				(= cycles 1)
			)
			(5
				(gGameMusic2 send: 2 224 3000)
				(= cycles 1)
			)
			(6
				(gGameMusic2 send: 2 224 4000)
				((ScriptID 1902 13) modeless: 1)
				((ScriptID 1903 14) modeless: 1)
				(= register (Random 11 17))
				(cond 
					((== register 17) (= seconds 8))
					((== register 16) (= seconds 8))
					(else (gLb2Messager say: 10 0 register 0 self))
				)
			)
			(7
				(gGameMusic2 send: 2 224 3000)
				(= cycles 1)
			)
			(8
				(gGameMusic2 send: 2 224 2000)
				(= cycles 1)
			)
			(9
				; BUGFIX: Fix endless taxi driving + taxi drive prematurely ending.
				;
				; This code never worked as Sierra intended, they wanted the drive to
				; last until both music and speech finished playing but it ends earlier.
				; The floppy version lasts until the music ends. The test they used here
				; for checking if music has ended isn't correct, and the one for the
				; speech can cause endless looping.
				;
				; For the music: "(== (gWrapSound prevSignal?) -1)" would only work in
				; the floppy version of the game, the CD and floppy versions have a
				; different Sound class. They could use "(== (gWrapSound handle?) 0)"
				; instead. Sound's handle property is always set to 1 when sounds are
				; playing, and cleared when stopped or disposed.
				;
				; For the speech: "(== (DoAudio audPOSITION) -1)" can be problematic as
				; it'll return 0 if the game failed to initialize the audio/voice card,
				; failing the test and endlessly looping.
				;
				; We fix it by using Sound's handle property to check if music has ended,
				; we also check if Rocco's (1902 13) or Laura's (gNarrator) talker is
				; initialized and the message mode isn't TEXT, which works better to
				; test if speech is ongoing. The travel now lasts what Sierra intended.
;;;				(if
;;;					(not
;;;						(and
;;;							(== (DoAudio audPOSITION) -1)
;;;							(== (gWrapSound prevSignal?) -1)
;;;						)
;;;					)
;;;					(-- state)
;;;				)
;;;				(= cycles 1)
				(if
					(or
						(and
							(or (gNarrator initialized?) ((ScriptID 1902 13) initialized?)) ; true if Laura's or Rocco's talker is initialized
							(!= global90 1) ; true if a message in mode SPEECH/BOTH is ongoing
						)
						(gWrapSound handle?) ; true if music is playing
					)
					(-- state) ; reduce state by 1 to repeat the current one
				)
				(= cycles 1)
				; END OF BUGFIX (the same has been done in sMoveBuildings:changeState(6)).
			)
			(10
				(win1 setCycle: 0)
				(win2 setCycle: 0)
				(win3 setCycle: 0)
				(win4 setCycle: 0)
				(win5 setCycle: 0)
				(gGameMusic2 send: 2 224 1000)
				(gLb2Messager say: 10 0 16 0 self)
			)
			(11
				(gGameMusic2 send: 2 224 500)
				(= cycles 1)
			)
			(12
				(gGameMusic2 send: 2 224 0)
				(if (& global90 $0002) ((ScriptID 1902 13) dispose:))
				(gGame handsOn:)
				(gIconBar enable: 5)
				(if (!= local0 gNumber)
					(global2 newRoom: local0)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance sMoveBuildings of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				; BUGFIX: Fix Laura's messages pausing street animation during taxi travels.
				;
				; We fix it by setting gNarrator (99) as modeless. It will reset on newRoom.
				(gNarrator modeless: 1)
				; END OF BUGFIX (the same has been done in sDoTakeOffFlight:changeState(0))
				(win1 setCycle: Fwd)
				(win2 setCycle: Fwd)
				(win3 setCycle: Fwd)
				(win4 setCycle: Fwd)
				(win5 setCycle: Fwd)
				(= cycles 1)
			)
			(1
				(gWrapSound number: 250 loop: 1 flags: 1 play:)
				(gGame handsOn:)
				(gIconBar disable: 5 6 0)
				(= cycles 1)
			)
			(2
				(gGameMusic2 send: 2 224 1000)
				(= cycles 1)
			)
			(3
				(gGameMusic2 send: 2 224 2000)
				(= cycles 1)
			)
			(4
				(gGameMusic2 send: 2 224 3000)
				(= cycles 1)
			)
			(5
				(gGameMusic2 send: 2 224 4000)
				(= seconds (Random 6 10))
			)
			(6
				; BUGFIX: Fix endless taxi driving + taxi drive prematurely ending.
				;
				; The tests to determine if music is playing and speech is ongoing are
				; incorrect, see the bugfix in sDoTakeOffFlight:changeState(9) for more
				; details, as the same applies here.
				;
				; We fix it by using Sound's handle property to check if music has ended,
				; we also check if Bob's (1903 14) or Laura's (gNarrator) talker is
				; initialized and the message mode isn't TEXT, which works better to
				; test if speech is ongoing. The travel now lasts what Sierra intended.
;;;				(if
;;;					(not
;;;						(and
;;;							(== (DoAudio audPOSITION) -1)
;;;							(== (gWrapSound prevSignal?) -1)
;;;						)
;;;					)
;;;					(-- state)
;;;				)
;;;				(= cycles 1)
				(if
					(or
						(and
							(or (gNarrator initialized?) ((ScriptID 1903 14) initialized?)) ; true if Laura's or Bob's talker is initialized
							(!= global90 1) ; true if a message in mode SPEECH/BOTH is ongoing
						)
						(gWrapSound handle?) ; true if music is playing
					)
					(-- state) ; reduce state by 1 to repeat the current one
				)
				(= cycles 1)
				; END OF BUGFIX (the same has been done in sDoTakeOffFlight:changeState(9)).
			)
			(7
				(gWrapSound fade:)
				(gIconBar enable: 5)
				(if (& global90 $0002) ((ScriptID 1903 14) dispose:))
				(if (!= local0 gNumber)
					(global2 newRoom: local0)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance laura of View
	(properties
		y 100
		z 75
		view 251
		priority 10
		signal $1011
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 13)
			(global2 newRoom: (if gGNumber else 210))
		else
			(gEgo doVerb: theVerb &rest)
		)
	)
)

(instance DDriver of View
	(properties
		x 232
		y 104
		noun 4
		view 252
		loop 1
		priority 4
		signal $1811
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(1 (gLb2Messager say: 4 1 7 0))
			(4 (gLb2Messager say: 4 4 7 0))
			(13
				(global2 newRoom: (if gGNumber else 210))
			)
			(6
				(cond 
					(local2 (global2 setScript: sWhereToBud))
					(
						(and
							(<= 512 (= temp0 (global2 setInset: (ScriptID 20 0))))
							(<= temp0 665)
						)
						(gLb2Messager say: 11 6 7 0)
					)
					(else (gLb2Messager say: 12 6 7 0))
				)
			)
			(2 (gLb2Messager say: 4 2 7 0))
			(11
				(global2 setScript: sWhereToBud)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance CDriver of View
	(properties
		x 232
		y 104
		noun 5
		view 252
		priority 4
		signal $1811
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(1 (gLb2Messager say: 5 1 8 0))
			(4 (gLb2Messager say: 5 4 8 0))
			(6
				(cond 
					(local2 (global2 setScript: sWhereToBud))
					(
						(and
							(<= 512 (= temp0 (global2 setInset: (ScriptID 20 0))))
							(<= temp0 665)
						)
						(gLb2Messager say: 11 6 8 0)
					)
					(else (gLb2Messager say: 12 6 8 0))
				)
			)
			(2 (gLb2Messager say: 5 2 8 0))
			(13
				(global2 newRoom: (if gGNumber else 210))
			)
			(11
				(global2 setScript: sWhereToBud)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance win1 of Prop
	(properties
		x 87
		y 96
		noun 6
		view 253
		priority 2
		signal $0011
		cycleSpeed 7
	)
	
	(method (doVerb theVerb)
		; BUGFIX: Fix graphical glitches left by the messager box (TALK verb).
		;
		; Continuation of the bugfix described in rm250:doVerb.
		;
		; We fix it by adding a TALK case and passing the verb to rm250:doVerb.
		; While we're at it, since the code for the EXIT verb (13) is identical
		; to the one used in rm250:doVerb, we also pass it. We use OneOf to pass
		; both verbs.
;;;		(switch theVerb
;;;			(13
;;;				(global2 newRoom: (if gGNumber else 210))
;;;			)
;;;			(else
;;;				(super doVerb: theVerb &rest)
;;;			)
;;;		)
		(if (proc999_5 theVerb 2 13) ; OneOf theVerb TALK EXIT
			(global2 doVerb: theVerb &rest)
		else
			(super doVerb: theVerb &rest)
		)
		; END OF BUGFIX (see also the doVerb method of win2/3/4/5, rm250, ticket,
		; license, Trash and cornerTrash)
	)
)

(instance win2 of Prop
	(properties
		x 141
		y 97
		noun 6
		view 253
		loop 1
		priority 2
		signal $4011
		cycleSpeed 7
	)
	
	(method (doVerb theVerb)
		; BUGFIX: Fix graphical glitches left by the messager box (TALK verb).
		;
		; Continuation of the bugfix described in rm250:doVerb.
		;
		; We fix it by adding a TALK case and passing the verb to rm250:doVerb.
		; While we're at it, since the code for the EXIT verb (13) is identical
		; to the one used in rm250:doVerb, we also pass it. We use OneOf to pass
		; both verbs.
;;;		(switch theVerb
;;;			(13
;;;				(global2 newRoom: (if gGNumber else 210))
;;;			)
;;;			(else
;;;				(super doVerb: theVerb &rest)
;;;			)
;;;		)
		(if (proc999_5 theVerb 2 13) ; OneOf theVerb TALK EXIT
			(global2 doVerb: theVerb &rest)
		else
			(super doVerb: theVerb &rest)
		)
		; END OF BUGFIX (see also the doVerb method of win1/3/4/5, rm250, ticket,
		; license, Trash and cornerTrash)
	)
)

(instance win3 of Prop
	(properties
		x 159
		y 92
		noun 6
		view 254
		priority 2
		signal $0011
		cycleSpeed 7
	)
	
	(method (doVerb theVerb)
		; BUGFIX: Fix graphical glitches left by the messager box (TALK verb).
		;
		; Continuation of the bugfix described in rm250:doVerb.
		;
		; We fix it by adding a TALK case and passing the verb to rm250:doVerb.
		; While we're at it, since the code for the EXIT verb (13) is identical
		; to the one used in rm250:doVerb, we also pass it. We use OneOf to pass
		; both verbs.
;;;		(switch theVerb
;;;			(13
;;;				(global2 newRoom: (if gGNumber else 210))
;;;			)
;;;			(else
;;;				(super doVerb: theVerb &rest)
;;;			)
;;;		)
		(if (proc999_5 theVerb 2 13) ; OneOf theVerb TALK EXIT
			(global2 doVerb: theVerb &rest)
		else
			(super doVerb: theVerb &rest)
		)
		; END OF BUGFIX (see also the doVerb method of win1/2/4/5, rm250, ticket,
		; license, Trash and cornerTrash)
	)
)

(instance win4 of Prop
	(properties
		x 213
		y 88
		noun 6
		view 254
		loop 1
		priority 2
		signal $0011
		cycleSpeed 7
	)
	
	(method (doVerb theVerb)
		; BUGFIX: Fix graphical glitches left by the messager box (TALK verb).
		;
		; Continuation of the bugfix described in rm250:doVerb.
		;
		; We fix it by adding a TALK case and passing the verb to rm250:doVerb.
		; While we're at it, since the code for the EXIT verb (13) is identical
		; to the one used in rm250:doVerb, we also pass it. We use OneOf to pass
		; both verbs.
;;;		(switch theVerb
;;;			(13
;;;				(global2 newRoom: (if gGNumber else 210))
;;;			)
;;;			(else
;;;				(super doVerb: theVerb &rest)
;;;			)
;;;		)
		(if (proc999_5 theVerb 2 13) ; OneOf theVerb TALK EXIT
			(global2 doVerb: theVerb &rest)
		else
			(super doVerb: theVerb &rest)
		)
		; END OF BUGFIX (see also the doVerb method of win1/2/3/5, rm250, ticket,
		; license, Trash and cornerTrash)
	)
)

(instance win5 of Prop
	(properties
		x 268
		y 89
		noun 6
		view 254
		loop 2
		priority 2
		signal $0011
		cycleSpeed 7
	)
	
	(method (doVerb theVerb)
		; BUGFIX: Fix graphical glitches left by the messager box (TALK verb).
		;
		; Continuation of the bugfix described in rm250:doVerb.
		;
		; We fix it by adding a TALK case and passing the verb to rm250:doVerb.
		; While we're at it, since the code for the EXIT verb (13) is identical
		; to the one used in rm250:doVerb, we also pass it. We use OneOf to pass
		; both verbs.
;;;		(switch theVerb
;;;			(13
;;;				(global2 newRoom: (if gGNumber else 210))
;;;			)
;;;			(else
;;;				(super doVerb: theVerb &rest)
;;;			)
;;;		)
		(if (proc999_5 theVerb 2 13) ; OneOf theVerb TALK EXIT
			(global2 doVerb: theVerb &rest)
		else
			(super doVerb: theVerb &rest)
		)
		; END OF BUGFIX (see also the doVerb method of win1/2/3/4, rm250, ticket,
		; license, Trash and cornerTrash)
	)
)

; BUGFIX & IMPROVEMENT: Fix endless taxi drive after looking at the
; ticket and make the ticket inset not pause the street animation.
;
; When the taxi driver is Bob, the ticket is initialized and appears
; in the taxi. While the taxi is already moving, the room script will be
; sMoveBuildings, but using the LOOK verb on the ticket at that moment
; will briefly set showTicket as the room script, interrupting
; sMoveBuildings and making it unable to finish. This results in an
; infinite taxi drive.
;
; Additionally, looking at the ticket will set the inTicket inset so it
; displays a close-up of the ticket. Insets aren't modeless, so if the
; street animation is ongoing, it will be paused while the close-up is
; being shown.
;
; We approach both things by creating a fake inset, this involves making
; inTicket be a View instead, and adding a new instance of Feature that
; will initialize along with inTicket. This Feature will act as an
; invisible overlay that covers the whole room and will dispose itself
; and inTicket whenever a verb is used on it. We also modify ticket and
; showTicket, ticket will directly initialize inTicket, showTicket will
; only display the LOOK message when inTicket opens and will be set as
; win1's script. Our "inset" won't pause the street animation anymore
; or interfer with the room's script, fixing both issues.
;;;(instance inTicket of Inset
(instance inTicket of View
	(properties
		view 250
		x 190
;;;		y 154
		y 190 ; highest y (= max priority)
;;;		disposeNotOnMe 1
		z 36 ; newly added property, compensate y to retain its correct location on screen
		noun 9
	)
	
	(method (init) ; newly added method
		(overlay init:)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
;;;		(switch theVerb
;;;			(4
;;;				((ScriptID 21 0) doit: 770)
;;;				(ticket dispose:)
;;;				(inTicket dispose:)
;;;				(gEgo get: -1 1)
;;;				(proc0_3 27)
;;;			)
;;;			(13
;;;				(global2 newRoom: (if gGNumber else 210))
;;;			)
;;;			(else
;;;				(super doVerb: theVerb &rest)
;;;			)
		(if (== theVerb 1) ; LOOK
			(super doVerb: theVerb &rest)
		else
			(if (== theVerb 4) ; DO
				(overlay dispose:)
			)
			(ticket doVerb: theVerb &rest)
		)
	)
)

(instance overlay of Feature ; newly added instance of Feature
	(properties
	x 0
	y 189 ; second highest y (= highest priority after inTicket)
	nsRight 319 ; max width
	nsBottom 189 ; max height
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13 ; EXIT
				(global2 newRoom: (if gGNumber else 210))
			)
			(else
				(inTicket dispose:)
				(self dispose:)
			)
		)
	)
)

(instance showTicket of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
;;;				(global2 setInset: inTicket) ; unneded, ticket now directly initializes inTicket
				(= cycles 5)
			)
			(1
				(inTicket doVerb: 1) ; LOOK
				(self dispose:)
			)
		)
	)
)

(instance ticket of View
	(properties
		x 149
		y 178
		view 250
		loop 2
		priority 8
		signal $0010
	)
	
;;;	(method (doVerb theVerb param2) ; param2 is never needed
	(method (doVerb theVerb)
		(switch theVerb
;;;			(1
;;;				(global2 setScript: showTicket)
;;;			)
			(1 ; LOOK
				(inTicket init:)
				(win1 setScript: showTicket) ; attach showTicket to win1 instead (fixes infinite drive)
			)
			; BUGFIX: Fix graphical glitches left by the messager box (TALK verb).
			;
			; Continuation of the bugfix described in rm250:doVerb.
			;
			; We fix it by adding a TALK case and passing the verb to rm250:doVerb.
			(2 ; TALK
				(global2 doVerb: theVerb &rest)
			)
			; END OF BUGFIX (see also the doVerb method of win1/2/3/4/5, rm250,
			; license, Trash and cornerTrash)
			(4 ; DO
				(gEgo get: 1)
				(proc0_3 27)
				((ScriptID 21 0) doit: 770)
				(inTicket dispose:) ; newly added call
				(self dispose:)
			)
			(13 ; EXIT
				(global2 newRoom: (if gGNumber else 210))
			)
			(else 
;;;				(super doVerb: theVerb param2 &rest) ; param2 is never needed
				(super doVerb: theVerb &rest)
			)
		)
	)
)
; END OF BUGFIX & IMPROVEMENT (see also Trash:handleEvent)

(instance license of Feature
	(properties
		x 246
		y 114
		nsTop 99
		nsLeft 219
		nsBottom 130
		nsRight 274
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (gOldCast contains: trash1)
			(= noun 7)
		else
			(= noun 8)
		)
		; BUGFIX: Fix graphical glitches left by the messager box (TALK verb).
		;
		; Continuation of the bugfix described in rm250:doVerb.
		;
		; We fix it by adding a TALK case and passing the verb to rm250:doVerb.
		; While we're at it, since the code for the EXIT verb (13) is identical
		; to the one used in rm250:doVerb, we also pass it. We use OneOf to pass
		; both verbs.
;;;		(switch theVerb
;;;			(13
;;;				(global2 newRoom: (if gGNumber else 210))
;;;			)
;;;			(else
;;;				(super doVerb: theVerb &rest)
;;;			)
;;;		)
		(if (proc999_5 theVerb 2 13) ; OneOf theVerb LOOK EXIT
			(global2 doVerb: theVerb &rest)
		else
			(super doVerb: theVerb &rest)
		)
		; END OF BUGFIX (see also the doVerb method of win1/2/3/4/5, rm250,
		; ticket, Trash and cornerTrash)
	)
)

(class Trash of View
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 2
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view 250
		loop 1
		cel 0
		priority 0
		underBits 0
		signal $0101
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		boundryLeft 0
		boundryRight 319
		boundryTop 155
		boundryBottom 189
	)
	
	(method (init)
		(if (proc0_10 16 1)
			(gLb2MDH addToFront: self)
			(gLb2KDH addToFront: self)
		)
		(super init: &rest)
	)
	
	; BUGFIX: Don't let Trash have more priority than inTicket.
	;
	; Trash uses a custom handleEvent method to make it movable by drag and
	; drop. Its doit method will keep changing its x and y properties to
	; match the mouse coordinates whenever the player is dragging it.
	;
	; gX and gY are used to determine the mouse coordinates, but gY takes
	; into account the extra 10 pixels on top of the screen (the black space
	; used to show the iconbar when hovered), which are outside of the
	; room's coordinate system (the top-left of the room returns gY 10 gX 0).
	; This means that there's a 10px mismatch between y and gY, so Trash
	; will be able to obtain an y value higher than 190, which in turn
	; gives it more priority than anything else in the room (the higher the
	; value of the y property, the higher the priority). This is a problem
	; when inTicket (the ticket close-up) is being shown, as it's supposed
	; to appear on top of everything else but instances of Trash can be
	; above it.
	;
	; We fix it by giving Trash's y property the value of "gY - 10" instead
	; of gY, correcting the mismatch and fixing its priority. We then use z
	; to preserve its original y location on screen without altering its
	; priority.
	(method (doit)
		(if (and (== theTrash self) (self inBounds:))
			(= x gX)
;;;			(= y gY)
			(= y (- gY 10))
			(= z -10) ; added
		)
		(super doit: &rest)
	)
	; END OF BUGFIX
	
	(method (dispose)
		(gLb2MDH delete: self)
		(gLb2KDH delete: self)
		(super dispose:)
	)
	
	; TWEAK: Don't let Trash handle mouse button releases while it isn't
	; being dragged, or secondary mouse button presses. Make Trash
	; compatible with our modified inTicket.
	;
	; Trash uses a custom handleEvent method to make it movable by drag and
	; drop. Drag and drop works with the primary or secondary mouse button.
	; However, no other objects in the game handle secondary mouse button
	; clicks, so apart from seeming unintentional, it isn't intuitive.
	;
	; Additionally, Trash handles mouse button releases even when trash
	; isn't being dragged, which is unnecessary.
	;
	; Lastly, since we've changed the inTicket inset to make it be a View
	; instead (to deal with another bug), Trash will still be able to handle
	; mouse clicks when inTicket is initialized, creating an inconvenience.
	; We need to prevent this.
	;
	; We modify this method adding tests to make it only handle primary
	; mouse button clicks, to make it ignore events when inTicket is part of
	; the cast and to only handle mouse button releases while trash is being
	; dragged.
	(method (handleEvent pEvent)
		(cond 
			(
				(and
					(== (pEvent message?) KEY_RETURN)
					(== (pEvent type?) evKEYBOARD)
					(== (gIconBar curIcon?) (gIconBar at: 2))
					(self onMe: pEvent)
					(not (gOldCast contains: inTicket)) ; added test (is inTicket not part of the cast?)
				)
				(if (!= theTrash self)
					(= theTrash self)
					(noise number: 54 loop: 1 flags: 1 play:)
				else
					(= theTrash 0)
				)
				(pEvent claimed: 1)
			)
			(
				(and
					(== (pEvent type?) evMOUSEBUTTON)
					(not (pEvent modifiers?)) ; added test (no event modifiers? = only primary mouse button)
					(== (gIconBar curIcon?) (gIconBar at: 2))
					(self onMe: pEvent)
					(not (gOldCast contains: inTicket)) ; added test (is inTicket not part of the cast?)
				)
				(noise number: 54 loop: 1 flags: 1 play:)
				(= theTrash self)
				(pEvent claimed: 1)
			)
			(
				(and
					(== (pEvent type?) evMOUSERELEASE)
					(== theTrash self) ; added test (trash is being dragged)
					(self onMe: pEvent)
				)
				(= theTrash 0)
				(pEvent claimed: 1)
			)
			(else (super handleEvent: pEvent &rest))
		)
	)
	; END OF TWEAK (see also inTicket)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(global2 newRoom: (if gGNumber else 210))
			)
			(1 (gLb2Messager say: 3 1 4))
			; BUGFIX: Fix graphical glitches left by the messager box (TALK verb).
			;
			; Continuation of the bugfix described in rm250:doVerb.
			;
			; We fix it by adding a TALK case and passing the verb to rm250:doVerb.
			(2 ; TALK
				(global2 doVerb: theVerb &rest)
			)
			; END OF BUGFIX (see also the doVerb method of win1/2/3/4/5, rm250,
			; ticket, license and cornerTrash)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (inBounds)
		(if
			(and
				(> gX boundryLeft)
				(< gX boundryRight)
				(> gY boundryTop)
				(< gY boundryBottom)
			)
		)
	)
)

(instance trash1 of Trash
	(properties
		x 166
		y 176
		cel 2
		signal $4000
	)
)

(instance trash2 of Trash
	(properties
		x 145
		y 163
		cel 3
		signal $4000
	)
)

(instance trash3 of Trash
	(properties
		x 148
		y 181
		cel 4
		signal $4000
	)
)

(instance trash4 of Trash
	(properties
		x 112
		y 174
		cel 5
		signal $4000
	)
)

(instance trash5 of Trash
	(properties
		x 58
		y 174
		signal $4000
	)
)

(instance cornerTrash of View
	(properties
		x 261
		y 189
		noun 2
		view 250
		loop 3
		priority 1
		signal $0810
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((proc0_2 27) (gLb2Messager say: 2 4 4))
					((proc0_10 16 1) (gLb2Messager say: 2 4 2))
					((gEgo wearingGown?) (gLb2Messager say: 2 4 4))
					((gEgo has: 0) (gLb2Messager say: 2 4 4))
					(else (gLb2Messager say: 2 4 4))
				)
			)
			(1 (gLb2Messager say: 3 1 4))
			; BUGFIX: Fix graphical glitches left by the messager box (TALK verb).
			;
			; Continuation of the bugfix described in rm250:doVerb.
			;
			; We fix it by adding a TALK case and passing the verb to rm250:doVerb.
			(2 ; TALK
				(global2 doVerb: theVerb &rest)
			)
			; END OF BUGFIX (see also the doVerb method of win1/2/3/4/5, rm250,
			; ticket, license and Trash)
			;
			; BUGFIX: Fix trash on the corner not exiting the room when using the
			; EXIT verb on it.
			;
			; Using the exit icon on the trash on the corner of the taxi (present
			; when Bob is the driver) doesn't exit the room. cornerTrash:doVerb
			; misses a case and code to handle the EXIT verb (13).
			;
			; We fix it by adding a case to handle the EXIT verb to
			; cornerTrash:doVerb, based on the ones present in other objects.
			(13 ; EXIT
				(global2 newRoom: (if gGNumber else 210))
			)
			; END OF BUGFIX
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance noise of Sound
	(properties
		flags $0001
	)
)
