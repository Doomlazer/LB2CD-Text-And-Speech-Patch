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
	; TWEAK: Add isBob local var.
	;
	; We frequently need to test if the driver is Bob or Rocco in this room.
	; A dedicated variable makes the tests simpler and more performant.
	isBob
	; END OF TWEAK
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
			(= isBob 1) ; TWEAK: Addition, we set isBob here
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
			; TWEAK: Adapt rm250:init to work with the combined sNoPressPass script.
			;
			; We've combined sNoPressPassD+sNoPressPassC in a script named
			; sNoPressPass that now works for either Rocco or Bob when the room
			; initializes and the press pass isn't in the inventory.
			;
			; We modify rm250:init to set sNoPressPass for both taxi drivers.
;;;			((not (gEgo has: 6))
;;;				(if (gOldCast contains: trash1)
;;;					(self setScript: sNoPressPassD) ; Bob -> sNoPressPassD
;;;				else
;;;					(self setScript: sNoPressPassC) ; Rocco -> sNoPressPassC
;;;				)
;;;			)
			((not (gEgo has: 6)) (self setScript: sNoPressPass)) ; Rocco/Bob -> sNoPressPass
			; END OF TWEAK (see also sNoPressPass)
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

; TWEAK: Combine sNoPressPassD+sNoPressPassC.
;
; If the player enters the room without the press pass, the taxi driver
; will have a conversation with Laura. The messages are different if the
; taxi driver is Rocco or Bob, so they organized this in two different
; scripts, sNoPressPassD for Bob and sNoPressPassC for Rocco. The
; differences of these scripts are minimal, and combining both of them
; makes the compiled script file smaller while reducing the heap usage,
; which are welcome optimizations after all our other additions.
;
; We combine sNoPressPassD and sNoPressPassC and rename the resulting
; script to sNoPressPass. We make use of our new isBob variable to test
; who the driver is and change its behavior. rm250:init also required
; changes to set this script instead of the other two.
;;;(instance sNoPressPassD of Script
(instance sNoPressPass of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(User canInput: 1)
				(= cycles 1)
			)
			(1
;;;				(gLb2Messager say: 1 0 9 1 self)
				(gLb2Messager say: 1 0 (if isBob 9 else 1) 1 self)
			)
			(2
				(gLb2Messager say: 1 0 10 1 self)
			)
			(3
;;;				(gLb2Messager say: 1 0 9 2 self)
				(gLb2Messager say: 1 0 (if isBob 9 else 1) 2 self)
			)
			(4
				(gGame handsOn:)
				(= seconds 15)
			)
			(5
;;;				(gLb2Messager say: 1 0 9 3 self)
				(gLb2Messager say: 1 0 (if isBob 9 else 1) 3 self)
			)
			(6
				(global2 newRoom: (if gGNumber else 210))
			)
		)
	)
)
; END OF TWEAK (see also rm250:init)

;;;(instance sNoPressPassC of Script  ; unneeded, we've combined sNoPressPassC and sNoPressPassD
;;;	(properties)
;;;	
;;;	(method (changeState newState)
;;;		(switch (= state newState)
;;;			(0
;;;				(gGame handsOff:)
;;;				(User canInput: 1)
;;;				(= cycles 1)
;;;			)
;;;			(1
;;;				(gLb2Messager say: 1 0 1 1 self)
;;;			)
;;;			(2
;;;				(gLb2Messager say: 1 0 10 1 self)
;;;			)
;;;			(3
;;;				(gLb2Messager say: 1 0 1 2 self)
;;;			)
;;;			(4
;;;				(gGame handsOn:)
;;;				(= seconds 15)
;;;			)
;;;			(5
;;;				(gLb2Messager say: 1 0 1 3 self)
;;;			)
;;;			(6
;;;				(global2 newRoom: (if gGNumber else 210))
;;;			)
;;;		)
;;;	)
;;;)

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
;;;					(and (gOldCast contains: trash1) (not (proc0_2 26))) (client setScript: s1stTimeInDirtyTaxi self))
					(and isBob (not (proc0_2 26))) (client setScript: s1stTimeInDirtyTaxi self)) ; TWEAK: Use our new isBob local var
;;;					((gOldCast contains: trash1) (gLb2Messager say: 1 0 7 6 self))
					(isBob (gLb2Messager say: 1 0 7 6 self)) ; TWEAK: Use our new isBob local var
;;;					((not (gOldCast contains: trash1)) (gLb2Messager say: 1 0 8 0 self))
					((not isBob) (gLb2Messager say: 1 0 8 0 self)) ; TWEAK: Use our new isBob local var
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
;;;				(if (not (gOldCast contains: trash1))
				(if (not isBob) ; TWEAK: Use our new isBob local var
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
;;;						(if (not (gOldCast contains: trash1))
						(if (not isBob) ; TWEAK: Use our new isBob local var
							(gLb2Messager say: 17 14 8 0)
						else
							(gLb2Messager say: 17 14 7 0)
						)
					)
					(-1 (= local0 250))
					(else 
						(= local0 250)
;;;						(if (not (gOldCast contains: trash1))
						(if (not isBob) ; TWEAK: Use our new isBob local var
							(gLb2Messager say: 12 14 8 0)
						else
							(gLb2Messager say: 12 14 7 0)
						)
					)
				)
				(= cycles 1)
			)
			(3
				; TWEAK: Adapt sWhereToBud to work with the combined taxi drive script.
				;
				; We've combined sDoTakeOffFlight+sMoveBuildings, so sDoTakeOffFlight
				; can work for either Rocco or Bob after the press pass is used and a
				; valid place to travel to is chosen.
				;
				; We modify sWhereToBud to always set sDoTakeOffFlight for both taxi
				; drivers, as sMoveBuildings is no longer needed.
;;;				(cond
;;;					((or (== local0 gGNumber) (== local0 gNumber)) (gGame handsOn:) (= cycles 1))
;;;					((not (gOldCast contains: trash1)) (global2 setScript: sDoTakeOffFlight)) ; Bob -> sDoTakeOffFlight
;;;					(else (global2 setScript: sMoveBuildings)) ; Rocco -> sMoveBuildings
;;;				)
				(if
					(or
						(== local0 gGNumber)
						(== local0 gNumber)
					)
					(gGame handsOn:)
					(= cycles 1)
				else
					(global2 setScript: sDoTakeOffFlight) ; Rocco/Bob -> sDoTakeOffFlight
				)
				; END OF TWEAK (see also sDoTakeOffFlight)
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
	
;;;	(method (changeState newState &tmp [temp0 50])
	(method (changeState newState) ; TWEAK: removed temp0, it's never used
		(switch (= state newState)
			; TWEAK: Optimize states 0 and 2 and remove unnecessary code.
			;
			; This script becomes the room's script once the player uses the press
			; pass on the taxi driver and chooses a valid place to travel to.
			;
			; State 0 doesn't really need handsOff or setting canInput. The game is
			; already in hands-off with input enabled when this script is set as the
			; room script by sWhereToBud:changeState(3).
			;
			; We disable that code and move handsOn and the gIconBar:disable call
			; from state 2 to state 0. The sooner this is done, the better, since
			; it resets the mouse cursor and prevents the player from being able to
			; use the press pass again while the taxi drive is ongoing.
			(0
;;;				(gGame handsOff:)
;;;				(User canInput: 1)
				(gGame handsOn:) ; moved here from state 2
				(gIconBar disable: 5 6 0) ; moved here from state 2
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
;;;				(gGame handsOn:)
;;;				(gIconBar disable: 5 6 0)
				(= cycles 1)
			)
			; END OF TWEAK (see also the other states of this script and
			; sWhereToBud:changeState(3))
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
				; TWEAK: Combine sDoTakeOffFlight+sMoveBuildings (1/2).
				;
				; The game uses a different script for Rocco and Bob when the taxi drive
				; starts. sDoTakeOffFlight is used for Rocco and sMoveBuildings for Bob.
				; We combine both in sDoTakeOffFlight and get rid of sMoveBuildings,
				; this way both taxi drivers will use the same script, the compiled
				; patch will be smaller and it'll reduce heap usage.
				;
				; We test who the driver is by using our new isBob local variable and
				; make the state behave differently for each driver, basing the code for
				; Bob on sMoveBuildings. We don't bring here the random wait of
				; sMoveBuildings(5), as the next state for Bob will be 9 and it will
				; already wait until the music ends.
;;;				(gGameMusic2 send: 2 224 4000)
;;;				((ScriptID 1902 13) modeless: 1) ; no effect, already modeless by default
;;;				((ScriptID 1903 14) modeless: 1) ; no effect, already modeless by default
;;;				(= register (Random 11 17))
;;;					(cond
;;;						((== register 17) (= seconds 8))
;;;						((== register 16) (= seconds 8))
;;;						(else (gLb2Messager say: 10 0 register 0 self))
;;;					)
				(gGameMusic2 send: 2 224 4000)
				(if (not isBob) ; Rocco
					(= register (Random 11 17))
						(if (< register 16)
							(gLb2Messager say: 10 0 register 0 self) ; small talk, cue
						else
							(= seconds 8)
						)
				else ; Bob
					(= state 8) ; make the next state be 9
					(= cycles 1)
				)
				; END OF TWEAK (see also the other states of this script and
				; sWhereToBud:changeState(3))
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
				; BUGFIX: Fix endless taxi drive and taxi drive prematurely ending.
				;
				; This code never worked as Sierra intended, they wanted the taxi drive
				; to last until both music and speech finished playing, but it ends
				; earlier. The test they used here to check if music has ended isn't
				; correct, and the one for the speech can cause endless looping.
				;
				; For the music, checking Sound's prevSignal property only works in the
				; floppy game, the CD version has a different Sound class. They could
				; have checked Sound's handle property instead, it's always set to 1
				; when sounds are playing and cleared when stopped/disposed.
				;
				; For the speech, testing DoAudio's audPOSITION can be problematic as
				; it'll return 0 if the game couldn't initialize the audio/voice card,
				; failing the test and letting the state endlessly loop.
				;
				; We fix the premature end of the taxi drive by testing Sound's handle
				; property to determine if music is playing, if the test passes we set
				; gWrapSound's client property so it points to this script, and reduce
				; the state by 1. The script will stay idle, and will be cued by
				; gWrapSound once music ends, that will re-run this state, the test
				; won't pass and it'll change to state 10.
				;
				; We remove the DoAudio test, we'll instead test in state 10 if the
				; drivers' talkers are initialized, as they're the only possible source
				; of speech that could be unpredictable (their talkers are modeless).
				; That new test can't cause an endless taxi drive.
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
				(if (gWrapSound handle?) ; is music is playing?
					(gWrapSound client: self) ; the script will be cued once music ends
					(-- state) ; next state will be the current one
				else
					(= cycles 1)
				)
				; END OF BUGFIX (see also the other states of this script and
				; sWhereToBud:changeState(3))
			)
			; TWEAK: New state, combine sDoTakeOffFlight+sMoveBuildings (2/2) and
			; prevent the taxi driver's messages from being interrupted.
			;
			; The taxi drivers' talkers are modeless, and if the player makes a
			; message be displayed by using the TALK/ASK/DO verbs on the taxi driver,
			; it could be interrupted by gLb2Messager (in the case of Rocco) or by
			; rm250:newRoom (in the case of Bob). Apart from the sudden interruption,
			; if the message mode is SPEECH or BOTH this can also affect the game's
			; music volume, in these modes the volume is lowered down when a message
			; is displayed and restored when disposed. However, if the message is
			; interrupted the volume won't be restored, which is problematic.
			;
			; We make a new state and deal with that by testing if the taxi drivers'
			; talkers are initialized, if they are we set gLb2Messager's caller
			; property to point to this script and reduce state by 1. This will make
			; the script remain idle, and it'll be cued by the gLb2Messager whenever
			; it's disposed. That'll let the message finish and then re-run this
			; state without risk of interruptions.
			;
			; Lastly, we test who the driver with or new isBob local variable. If
			; it's Rocco we directly change to state 11. If it's Bob we change to
			; state 12 instead, imitating the change from state 6 to 7 of
			; sMoveBuildings.
			(10
				(cond
					(
						(or
							((ScriptID 1902 13) initialized?) ; Rocco's talker is initialized
							((ScriptID 1903 14) initialized?) ; Bob's talker is initialized
						)
						(gLb2Messager caller: self) ; gLb2Messager will cue this script on dispose
						(-- state) ; next state will be the current one
					)
					((not isBob) ; Rocco
						(self changeState: 11)
					)
					(else ; Bob
						(self changeState: 12)
					)
				)
			)
			; END OF TWEAK (see also the other states of this script and
			; sWhereToBud:changeState(3))
;;;			(10
			(11 ; TWEAK: increase state by 1
				(win1 setCycle: 0)
				(win2 setCycle: 0)
				(win3 setCycle: 0)
				(win4 setCycle: 0)
				(win5 setCycle: 0)
				(gGameMusic2 send: 2 224 1000)
				(gLb2Messager say: 10 0 16 0 self)
			)
			; TWEAK: Disable no longer needed code.
			;
			; Some of the code in the original states 11 and 12 is redundant, and
			; some other is no longer necessary after our changes to the other
			; states.
			;
			; We only need to keep one state to re-enable the iconbar and change
			; to the new room.
;;;			(11
;;;				(gGameMusic2 send: 2 224 500) ; unneded, rm250:newRoom stops it before it can be heard
;;;				(= cycles 1)
;;;			)
;;;			(12
;;;				(gGameMusic2 send: 2 224 0) ; unneded, and rm250:newRoom stops it before it can be heard
;;;				(if (& global90 $0002) ((ScriptID 1902 13) dispose:)) unneeded, our changes to state 11 lets it properly dispose
;;;				(gGame handsOn:) ; unneeded, not in handsOff
;;;				(gIconBar enable: 5)
;;;				(if (!= local0 gNumber) ; unneeded, local0 can't be the current room
;;;					(global2 newRoom: local0)
;;;				else
;;;					(self dispose:) ; unneeded, the script will be disposed on room change
;;;				)
			(12
				(gIconBar enable: 5)
				(global2 newRoom: local0)
			)
			; END OF TWEAK (see also the other states of this script and
			; sWhereToBud:changeState(3))
		)
	)
)

;;;(instance sMoveBuildings of Script ; unneeded, we've combined sDoTakeOffFlight and sMoveBuildings
;;;	(properties)
;;;	
;;;	(method (changeState newState)
;;;		(switch (= state newState)
;;;			(0
;;;				(win1 setCycle: Fwd)
;;;				(win2 setCycle: Fwd)
;;;				(win3 setCycle: Fwd)
;;;				(win4 setCycle: Fwd)
;;;				(win5 setCycle: Fwd)
;;;				(= cycles 1)
;;;			)
;;;			(1
;;;				(gWrapSound number: 250 loop: 1 flags: 1 play:)
;;;				(gGame handsOn:)
;;;				(gIconBar disable: 5 6 0)
;;;				(= cycles 1)
;;;			)
;;;			(2
;;;				(gGameMusic2 send: 2 224 1000)
;;;				(= cycles 1)
;;;			)
;;;			(3
;;;				(gGameMusic2 send: 2 224 2000)
;;;				(= cycles 1)
;;;			)
;;;			(4
;;;				(gGameMusic2 send: 2 224 3000)
;;;				(= cycles 1)
;;;			)
;;;			(5
;;;				(gGameMusic2 send: 2 224 4000)
;;;				(= seconds (Random 6 10))
;;;			)
;;;			(6
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
;;;			)
;;;			(7
;;;				(gWrapSound fade:)
;;;				(gIconBar enable: 5)
;;;				(if (& global90 $0002) ((ScriptID 1903 14) dispose:))
;;;				(if (!= local0 gNumber)
;;;					(global2 newRoom: local0)
;;;				else
;;;					(self dispose:)
;;;				)
;;;			)
;;;		)
;;;	)
;;;)

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
;;;		(if (gOldCast contains: trash1)
		(if isBob ; TWEAK: Use our new isBob local var
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
