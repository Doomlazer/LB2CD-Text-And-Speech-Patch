;;; Sierra Script 1.0 - (do not remove this comment)
(script# 355)
(include sci.sh)
(use Main)
(use LBRoom)
(use Inset)
(use Blink)
(use Scaler)
(use Osc)
(use PolyPath)
(use StopWalk)
(use View)
(use Obj)

(public
	rm355 0
	tkrLaura 2
	tkrErnie 23
)

(local
	local0
)
(instance rm355 of LBRoom
	(properties
		noun 5
		picture 350
	)
	
	(method (init)
		(gEgo init: normalize: 831 setScale: Scaler 95 0 190 0)
		(self setRegions: 90)
		(Palette palSET_INTENSITY 0 255 0)
		(self setScript: sPartysOver)
		(super init:)
	)
)

(instance sPartysOver of Script
	(properties)
	
	(method (doit)
		(if (< local0 100)
			(Palette palSET_INTENSITY 0 255 (= local0 (+ local0 2)))
			(if (== local0 100) (self cue:))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(gEgo posn: 245 165 loop: 8 setCel: 5)
				((ScriptID 32 0)
					moveTo: 355
					originalView: 814
					init:
					; BUGFIX: Make Heimlich face the correct direction at the start of Act 3.
					;
					; The museum actors' views in the floppy version of the game contain 4
					; loops for their diagonal walking animations. Those were removed from
					; the CD version but the code wasn't updated accordingly in various
					; room scripts. The non-updated code tries to set Loop 8 but doesn't
					; exist in this CD version, and setting it falls back to a cel of the
					; actor facing a wrong direction.
					;
					; This specific case affects to Heimlich's view during the scene at the
					; start of act 3. He's standing in front of the table facing diagonally
					; towards the top-left of the screen, but he should be facing the
					; bottom of the screen instead.
					;
					; We fix it by setting loop 4 instead. Fix ported from:
					; https://github.com/scummvm/scummvm/blob/85702e06764f95a6b700e348dd90931613efdc29/engines/sci/engine/script_patches.cpp#L11370
;;;					setLoop: 8
					setLoop: 4
					; END OF BUGFIX (see also:
					; - this script's state 15
					; - rm650:init, in #650
					; - rm500:init and sEnterNorth:changeState(0), in #500
					; - sEnterNorth:changeState(0), in #420
					; - rm400:init and sHeimlichShoos:changeState(4), in #400
					; )
					setCel: 2
					posn: 190 175
					ignoreActors: 1
				)
				((ScriptID 90 3)
					moveTo: 355
					view: 353
					setLoop: 1
					posn: 204 185
					ignoreActors: 1
				)
				((ScriptID 90 5)
					moveTo: 355
					view: 815
					posn: 147 184
					ignoreActors: 1
					setCycle: StopWalk -1
					setMotion: PolyPath 184 184 self
				)
				((ScriptID 90 1)
					moveTo: 355
					view: 813
					posn: 119 184
					ignoreActors: 1
					setCycle: StopWalk -1
					setMotion: PolyPath 161 184
				)
				((ScriptID 90 6)
					moveTo: 355
					view: 817
					posn: 85 184
					ignoreActors: 1
					setCycle: StopWalk -1
					setMotion: PolyPath 132 184
				)
				(= cycles 1)
			)
			(1
				((ScriptID 32 0) addToPic:)
				(gWrapSound number: 350 setLoop: -1 flags: 1 play:)
			)
			(2 0)
			(3
				(gLb2Messager say: 2)
				((ScriptID 90 3) setCycle: Osc 2 self)
			)
			(4
				((ScriptID 22 0) doit: -24576 self)
			)
			(5
				((ScriptID 90 5) setMotion: PolyPath 350 182)
				((ScriptID 90 1) setMotion: PolyPath 184 184 self)
				((ScriptID 90 6) setMotion: PolyPath 146 184)
			)
			(6
				((ScriptID 90 3) setCycle: Osc 3 self)
			)
			(7
				((ScriptID 90 1) setMotion: PolyPath 350 182)
				((ScriptID 90 6) setMotion: PolyPath 184 184 self)
			)
			(8
				((ScriptID 90 3) setCycle: Osc 2 self)
			)
			(9
				((ScriptID 90 6) setMotion: PolyPath 350 182 self)
			)
			(10
				((ScriptID 90 6) dispose:)
				((ScriptID 90 5) dispose:)
				((ScriptID 90 1) dispose:)
				(proc0_3 42)
				(gWrapSound fade:)
				(= ticks 60)
			)
			(11
				(gWrapSound number: 642 loop: -1 flags: 1 play:)
				((ScriptID 31 0)
					room: gNumber
					init:
					posn: 350 182
					setCycle: StopWalk -1
					setMotion: PolyPath (+ (gEgo x?) 10) (+ (gEgo y?) 10) self
				)
			)
			(12 (= cycles 2))
			(13
				(global2 setInset: inErnie_Laura)
				(= cycles 2)
			)
			(14
				(gLb2Messager say: 4 0 1 0 self)
			)
			(15
				(proc0_3 91)
				(global2 setInset: 0)
				; TWEAK: Disable non-working code.
				;
				; The following code was supposed to reposition Heimlich's view and make
				; him face towards O'Riley, but it never happens. There are two issues
				; with it:
				;
				; 1) State 1 of this script calls aHeimlich:addToPic, making Heimlich's
				; view be drawn as part of the background to save memory, but it can't
				; be updated anymore by normal means. This results in the present code
				; not doing any visible changes at all to Heimlich's view. This issue is
				; present in both floppy and CD versions.
				;
				; 2) As explained in state 0 of this script, the CD version removed
				; the diagonal walking loops of the museum actors, so using loop 8 here
				; is an oversight, the loop doesn't exist. Even if we fixed issue 1,
				; Heimlich would face a wrong direction (top-left). The correct loop
				; to use would be 4.
				;
				; We disable this code. What's presumably the intended behavior looks
				; off compared to how it looks without correcting the bug (it doesn't
				; really look like he's facing O'Riley, Heimlich's idle bottom-right
				; diagonal cel kind of looks like he's facing to the right, though this
				; is subjective).
;;;				((ScriptID 32 0)
;;;					posn: (- ((ScriptID 90 3) x?) 25) (- ((ScriptID 90 3) y?) 10)
;;;					setLoop: 8 ; wrong, the correct loop is 4
;;;					setCel: 4
;;;				)
				; END OF TWEAK (see also:
				; - this script's state 0
				; - rm650:init, in #650
				; - rm500:init and sEnterNorth:changeState(0), in #500
				; - sEnterNorth:changeState(0), in #420
				; - rm400:init and sHeimlichShoos:changeState(4), in #400
				; )
				((ScriptID 21 0) doit: 267)
				((ScriptID 31 0) setMotion: PolyPath 350 185 self)
				(gWrapSound fade:)
			)
			(16
				(gLb2Messager say: 1 0 0 0 self)
			)
			(17
				(WrapMusic init: 1 90 91 92 93)
				(gEgo setMotion: PolyPath 340 185 self)
			)
			(18 (global2 newRoom: 370))
		)
	)
)

(instance sIllegal of Script ; unused, kept to not need a heap patch
	(properties)
	
	(method (doit)
;;;		(if (< local0 100)
;;;			(Palette palSET_INTENSITY 0 255 (= local0 (+ local0 2)))
;;;			(if (== local0 100) (self cue:))
;;;		)
;;;		(super doit:)
	)
;;;	
	(method (changeState newState)
;;;		(switch (= state newState)
;;;			(0
;;;				(gGame handsOn:)
;;;				(proc0_3 91)
;;;				(= global123 0)
;;;				(gUser canInput: 0 canControl: 0)
;;;				((ScriptID 32 0)
;;;					room: gNumber
;;;					originalView: 814
;;;					init:
;;;					setLoop: 8
;;;					setCel: 2
;;;					posn: 190 175
;;;				)
;;;				((ScriptID 90 3)
;;;					room: gNumber
;;;					view: 819
;;;					setLoop: 8
;;;					setCel: 2
;;;					posn: 210 180
;;;				)
;;;				(gEgo posn: 230 182 setLoop: 8 setCel: 2)
;;;			)
;;;			(1
;;;				(gLb2Messager say: 3 0 0 0 self)
;;;			)
;;;			(2 (= seconds 5))
;;;			(3
;;;				(= global145 3)
;;;				(global2 newRoom: 99)
;;;			)
;;;		)
	)
)

(instance inErnie_Laura of Inset
	(properties
		picture 475
		hideTheCast 1
		name "inErnie&Laura"
	)
)

(instance tkrErnie of Talker
	(properties
		view 1475
		loop 1
		talkWidth 250
		back 15
		textX 20
		textY 120
	)
	
	(method (init)
		(= font gFont)
		(super init: 0 ernieEyes ernieMouth &rest)
	)
)

(instance ernieMouth of Prop
	(properties
		nsTop 78
		nsLeft 119
		view 1475
	)
)

(instance ernieEyes of Prop
	(properties
		nsTop 67
		nsLeft 122
		view 1475
		loop 2
	)
)

(instance tkrLaura of Narrator
	(properties
		x 150
		y 130
		back 15
	)
	
	(method (init)
		(= font gFont)
		(super init: &rest)
	)
)
