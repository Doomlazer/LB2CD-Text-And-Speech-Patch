;;; Sierra Script 1.0 - (do not remove this comment)
(script# 928)
(include sci.sh)
(use Main)
(use Print)
(use Sync)
(use RandCycle)
(use Cycle)
(use View)
(use Obj)


(class Blink of Cycle
	(properties
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		waitCount 0
		lastCount 0
		waitMin 0
		waitMax 0
	)
	
	(method (init param1 param2)
		(if argc
			(= waitMin (/ param2 2))
			(= waitMax (+ param2 waitMin))
			(super init: param1)
		else
			(super init:)
		)
	)
	
	(method (doit &tmp blinkNextCel)
		(cond 
			(waitCount
				(if (> (- gB_moveCnt waitCount) 0)
					(= waitCount 0)
					(self init:)
				)
			)
			(
				(or
					(> (= blinkNextCel (self nextCel:)) (client lastCel:))
					(< blinkNextCel 0)
				)
				(= cycleDir (- cycleDir))
				(self cycleDone:)
			)
			(else (client cel: blinkNextCel))
		)
	)
	
	(method (cycleDone)
		(if (== cycleDir -1)
			(self init:)
		else
			(= waitCount (+ (Random waitMin waitMax) gB_moveCnt))
		)
	)
)

(class Narrator of Prop
	(properties
		x -1
		y -1
		z 0
		heading 0
		noun 0
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
		view -1
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
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
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		caller 0
		disposeWhenDone 1
		ticks 0
		talkWidth 0
		keepWindow 0
		modeless 0
		font 0
		cueVal 0
		initialized 0
		showTitle 0
		color 0
		back 7
		curVolume 0
		saveCursor 0
	)
	
	(method (init)
		(if (& global90 $0002)
			(= curVolume (gGame masterVolume:))
			(if (>= curVolume 6)
				(gGame masterVolume: (- curVolume 6))
			else
				(gGame masterVolume: 1)
			)
		)
		(if
			(or
				(and (& global90 $0002)(not modeless))
				(not (HaveMouse))
			)
			(= saveCursor (gGame setCursor: global21 1))
		)
		(= gB_moveCnt (+ global86 (GetTime)))
		(= initialized 1)
	)
	
	(method (doit)
		(if
		(and (!= ticks -1) (> (- gB_moveCnt ticks) 0))
			(if
				(and
					(if (& global90 $0002) (== (DoAudio 6) -1) else 1)
					(or (not keepWindow) (& global90 $0002))
				)
				(self dispose: disposeWhenDone)
				(return 0)
			)
		)
		(return 1)
	)
	
	(method (dispose param1)
		(= ticks -1)
		(if (or (not argc) param1)
			(cond 
				(modeless
					(gLb2KDH delete: self)
					(gLb2MDH delete: self)
					(gTheDoits delete: self)
				)
				(
				(and gNewEventHandler (gNewEventHandler contains: self))
					(gNewEventHandler delete: self)
					(if (gNewEventHandler isEmpty:)
						(gNewEventHandler dispose:)
						(= gNewEventHandler 0)
					)
				)
			)
			(if (& global90 $0002)(DoAudio 3))
			(= modNum -1)
			(= initialized 0)
		)
		(if gDialog (gDialog dispose:))
		(if (& global90 $0002) (gGame masterVolume: curVolume))
		(if
			(or
				(and (& global90 $0002) (not modeless))
				(not (HaveMouse))
			)
			(gGame setCursor: saveCursor)
		)
		(if caller (caller cue: cueVal))
		(= cueVal 0)
		(DisposeClone self)
	)
	
	(method (handleEvent pEvent)
		(return
			(cond 
				((pEvent claimed?))
				((== ticks -1) (return 0))
				(else
					(if (not cueVal)
						(switch (pEvent type?)
							(evJOYDOWN (= cueVal 0))
							(evMOUSEBUTTON
								(= cueVal (& (pEvent modifiers?) emSHIFT))
							)
							(evKEYBOARD
								(= cueVal (== (pEvent message?) KEY_ESCAPE))
							)
						)
					)
					(if
						(or
							(& (pEvent type?) $4101)
							(and
								(& (pEvent type?) evKEYBOARD)
								(proc999_5 (pEvent message?) 13 27)
							)
						)
						(pEvent claimed: 1)
						(self dispose: disposeWhenDone)
						(return 1)
					)
				)
			)
		)
	)
	
	; TEXT&SPEECH CHANGE: Make Narrator:say able to process text and speech
	; at the same time.
	;
	; After our changes to Messager:sayNext (in #924), it will pass
	; Narrator:say a buffer with the text message as param1, a tuple of the
	; message for playing the audio as param 2 and the caller as param 3.
	; Narrator:say can't process that because it was coded to accept 2
	; arguments and it expects param2 to be the caller and param1 to be
	; either the text buffer or the tuple for the speech.
	;
	; We alter Narrator:say to accept a third argument, make it expect the
	; caller to be param3 instead, and make it use param2 for the speech.
	; Changes according to Kawa's post (thank you!) in:
	; https://sciprogramming.com/community/index.php?topic=1577.msg8632#msg8632
;;;	(method (say param1 param2)
	(method (say param1 param2 param3) ; accept 3 arguments
		(if gIconBar (gIconBar disable:))
		(if (not initialized) (self init:))
;;;		(= caller (if (and (> argc 1) param2) param2 else 0))
		(= caller (if (and (> argc 2) param3) param3 else 0)) ; use the third argument for the caller
		(if (& global90 $0001) (self startText: param1))
;;;		(if (& global90 $0002) (self startAudio: param1))
		(if (& global90 $0002) (self startAudio: param2)) ; use the second argument for the speech
	; END OF TEXT&SPEECH CHANGE (see also Messager:sayNext in #924,
	; Narrator:display and Talker:display)
		(cond 
			(modeless
				(gLb2MDH addToFront: self)
				(gLb2KDH addToFront: self)
				(gTheDoits add: self)
			)
			((IsObject gNewEventHandler) (gNewEventHandler add: self))
			(else
				((= gNewEventHandler (EventHandler new:))
					name: {fastCast}
					add: self
				)
			)
		)
		(= ticks (+ ticks 60 gB_moveCnt))
		(return 1)
	)
	
	(method (startText param1 &tmp temp0)
		(if (not (& global90 $0002))
			(= ticks
				(proc999_3
					240
					(* global94 2 (= temp0 (StrLen param1)))
				)
			)
		)
		(if gDialog (gDialog dispose:))
		(self display: param1)
		(return temp0)
	)
	
	(method (display theText &tmp theTalkWidth newGLb2Win)
		(if (> (+ x talkWidth) 318)
			(= theTalkWidth (- 318 x))
		else
			(= theTalkWidth talkWidth)
		)
		((= newGLb2Win (gLb2Win new:)) color: color back: back)
		(if
		(and (not (HaveMouse)) (!= gCursorNumber 996))
			(= saveCursor gCursorNumber)
			(gGame setCursor: 996)
		else
			; TEXT&SPEECH CHANGE: Fix cursor being incorrectly restored after
			; messages are disposed in BOTH message mode.
			;
			; The code in this else clause is a mouse cursor fallback, meant to be
			; executed if a text message has to be displayed when the mouse isn't
			; present and the mouse cursor is invisible (996). The walking cursor
			; (0) is saved so it can be restored once the message is disposed, to
			; not leave the player with an invisible cursor. However, this never
			; worked as intended because Narrator:init always sets the cursor as
			; the hand one (997) when no mouse is present, making impossible for
			; the invisible cursor to be active when Narrator:display is called,
			; always passing the "if" tests above. Additionally, since the tests
			; are not specific enough this is also run when the mouse is present
			; and a text message has to be displayed.
			;
			; It causes issues with our new BOTH message mode because the code in
			; this else clause will always be run, saving the walking cursor which
			; will later be restored by Narrator:dispose. This isn't problematic in
			; the TEXT message mode, as Narrator:dispose never restores the cursor
			; in that mode. SPEECH message mode isn't affected either, since
			; Narrator:display isn't even called.
			;
			; We fix it by not saving the walking cursor at all, this has no side
			; effects and prevents it from being restored in BOTH mode.
;;;			(= saveCursor 0)
			; END OF TEXT&SPEECH CHANGE (see also Talker:display)
		)
		(if showTitle (Print addTitle: name))
		(Print
			window: newGLb2Win
			posn: x y
			font: font
			width: theTalkWidth
			addText: theText
			modeless: 1
			init:
		)
	)
	
	(method (startAudio param1 &tmp temp0 temp1 temp2 temp3 temp4)
		(= temp0 (proc999_6 param1 0))
		(= temp1 (proc999_6 param1 1))
		(= temp2 (proc999_6 param1 2))
		(= temp3 (proc999_6 param1 3))
		(= temp4 (proc999_6 param1 4))
		(= ticks (DoAudio 2 temp0 temp1 temp2 temp3 temp4))
	)
)

(class Talker of Narrator
	(properties
		x -1
		y -1
		z 0
		heading 0
		noun 0
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
		view -1
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
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
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		caller 0
		disposeWhenDone 1
		ticks 0
		talkWidth 318
		keepWindow 0
		modeless 0
		font 0
		cueVal 0
		initialized 0
		showTitle 0
		color 0
		back 7
		curVolume 0
		saveCursor 0
		bust 0
		eyes 0
		mouth 0
		viewInPrint 0
		textX 0
		textY 0
		useFrame 0
		blinkSpeed 100
	)
	
	(method (init theBust theEyes theMouth)
		(if argc
			(= bust theBust)
			(if (> argc 1)
				(= eyes theEyes)
				(if (> argc 2) (= mouth theMouth))
			)
		)
		(self setSize:)
		(super init:)
	)
	
	(method (doit)
		(if (and (super doit:) mouth) (self cycle: mouth))
		(if eyes (self cycle: eyes))
	)
	
	(method (dispose param1)
		(if (and mouth underBits)
			(mouth cel: 0)
			(DrawCel
				(mouth view?)
				(mouth loop?)
				0
				(+ (mouth nsLeft?) nsLeft)
				(+ (mouth nsTop?) nsTop)
				-1
			)
		)
		(if (and mouth (mouth cycler?))
			(if ((mouth cycler?) respondsTo: #cue)
				((mouth cycler?) cue:)
			)
			(mouth setCycle: 0)
		)
		(if (or (not argc) param1)
			(if (and eyes underBits)
				(eyes setCycle: 0 cel: 0)
				(DrawCel
					(eyes view?)
					(eyes loop?)
					0
					(+ (eyes nsLeft?) nsLeft)
					(+ (eyes nsTop?) nsTop)
					-1
				)
			)
			(self hide:)
		)
		(super dispose: param1)
	)
	
	(method (hide)
		(Graph grRESTORE_BOX underBits)
		(= underBits 0)
		(Graph grREDRAW_BOX nsTop nsLeft nsBottom nsRight)
		(if gIconBar (gIconBar enable:))
	)
	
	(method (show &tmp temp0)
		(if (not underBits)
			(= underBits
				(Graph grSAVE_BOX nsTop nsLeft nsBottom nsRight 1)
			)
		)
		(= temp0 (PicNotValid))
		(PicNotValid 1)
		(if bust
			(DrawCel
				(bust view?)
				(bust loop?)
				(bust cel?)
				(+ (bust nsLeft?) nsLeft)
				(+ (bust nsTop?) nsTop)
				-1
			)
		)
		(if eyes
			(DrawCel
				(eyes view?)
				(eyes loop?)
				(eyes cel?)
				(+ (eyes nsLeft?) nsLeft)
				(+ (eyes nsTop?) nsTop)
				-1
			)
		)
		(if mouth
			(DrawCel
				(mouth view?)
				(mouth loop?)
				(mouth cel?)
				(+ (mouth nsLeft?) nsLeft)
				(+ (mouth nsTop?) nsTop)
				-1
			)
		)
		(DrawCel view loop cel nsLeft nsTop -1)
		(Graph grUPDATE_BOX nsTop nsLeft nsBottom nsRight 1)
		(PicNotValid temp0)
	)
	
	(method (say)
		(if (and (> view 0) (not underBits)) (self init:))
		(super say: &rest)
	)
	
	(method (startText &tmp temp0)
		(if (not viewInPrint) (self show:))
		(= temp0 (super startText: &rest))
		(if mouth (mouth setCycle: RandCycle (* 4 temp0) 0 1))
		(if (and eyes (not (eyes cycler?)))
			(eyes setCycle: Blink blinkSpeed)
		)
	)
	
	(method (display theText &tmp temp0 theTalkWidth temp2 newGLb2Win)
		((= newGLb2Win (gLb2Win new:)) color: color back: back)
		(if
		(and (not (HaveMouse)) (!= gCursorNumber 996))
			(= saveCursor gCursorNumber)
			(gGame setCursor: 996)
		else
			; TEXT&SPEECH CHANGE: Fix cursor being incorrectly restored after
			; messages are disposed in BOTH message mode.
			;
			; The walking cursor is restored every time a message is disposed
			; while our new BOTH message mode is active. See Narrator:display for
			; more details, the exact same applies here.
			;
			; We fix it by not saving the walking cursor at all, this has no side
			; effects and prevents it from being restored in BOTH mode.
;;;			(= saveCursor 0)
			; END OF TEXT&SPEECH CHANGE (see also Narrator:display)
		)
		(if viewInPrint
			(= temp0 (if useFrame loop else (bust loop?)))
			(if showTitle (Print addTitle: name))
			(Print
				window: newGLb2Win
				posn: x y
				modeless: 1
				font: font
				addText: theText
				addIcon: view temp0 cel 0 0
				init:
			)
		else
			(if (not (+ textX textY))
				(= textX (+ (- nsRight nsLeft) 5))
			)
			(if
			(> (+ (= temp2 (+ nsLeft textX)) talkWidth) 318)
				(= theTalkWidth (- 318 temp2))
			else
				(= theTalkWidth talkWidth)
			)
			(if showTitle (Print addTitle: name))
			(Print
				window: newGLb2Win
				posn: (+ x textX) (+ y textY)
				modeless: 1
				font: font
				width: theTalkWidth
				addText: theText
				init:
			)
		)
	)
	
	(method (startAudio param1 &tmp temp0 temp1 temp2 temp3 temp4)
		(self show:)
		(super startAudio: param1)
		(if mouth
			(= temp0 (proc999_6 param1 0))
			(= temp1 (proc999_6 param1 1))
			(= temp2 (proc999_6 param1 2))
			(= temp3 (proc999_6 param1 3))
			(= temp4 (proc999_6 param1 4))
			(mouth setCycle: MouthSync temp0 temp1 temp2 temp3 temp4)
		)
		(if (and eyes (not (eyes cycler?)))
			(eyes setCycle: Blink blinkSpeed)
		)
	)
	
	(method (cycle param1 &tmp temp0 [temp1 100])
		(if (and param1 (param1 cycler?))
			(= temp0 (param1 cel?))
			((param1 cycler?) doit:)
			(if (!= temp0 (param1 cel?))
				(DrawCel
					(param1 view?)
					(param1 loop?)
					(param1 cel?)
					(+ (param1 nsLeft?) nsLeft)
					(+ (param1 nsTop?) nsTop)
					-1
				)
				(param1
					nsRight:
						(+
							(param1 nsLeft?)
							(CelWide (param1 view?) (param1 loop?) (param1 cel?))
						)
				)
				(param1
					nsBottom:
						(+
							(param1 nsTop?)
							(CelHigh (param1 view?) (param1 loop?) (param1 cel?))
						)
				)
				(Graph
					grUPDATE_BOX
					(+ (param1 nsTop?) nsTop)
					(+ (param1 nsLeft?) nsLeft)
					(+ (param1 nsBottom?) nsTop)
					(+ (param1 nsRight?) nsLeft)
					1
				)
			)
		)
	)
	
	(method (setSize)
		(= nsLeft x)
		(= nsTop y)
		(= nsRight
			(+
				nsLeft
				(proc999_3
					(if view (CelWide view loop cel) else 0)
					(if (IsObject bust)
						(+
							(bust nsLeft?)
							(CelWide (bust view?) (bust loop?) (bust cel?))
						)
					else
						0
					)
					(if (IsObject eyes)
						(+
							(eyes nsLeft?)
							(CelWide (eyes view?) (eyes loop?) (eyes cel?))
						)
					else
						0
					)
					(if (IsObject mouth)
						(+
							(mouth nsLeft?)
							(CelWide (mouth view?) (mouth loop?) (mouth cel?))
						)
					else
						0
					)
				)
			)
		)
		(= nsBottom
			(+
				nsTop
				(proc999_3
					(if view (CelHigh view loop cel) else 0)
					(if (IsObject bust)
						(+
							(bust nsTop?)
							(CelHigh (bust view?) (bust loop?) (bust cel?))
						)
					else
						0
					)
					(if (IsObject eyes)
						(+
							(eyes nsTop?)
							(CelHigh (eyes view?) (eyes loop?) (eyes cel?))
						)
					else
						0
					)
					(if (IsObject mouth)
						(+
							(mouth nsTop?)
							(CelHigh (mouth view?) (mouth loop?) (mouth cel?))
						)
					else
						0
					)
				)
			)
		)
	)
)
