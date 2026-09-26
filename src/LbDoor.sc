;;; Sierra Script 1.0 - (do not remove this comment)
(script# 16)
(include sci.sh)
(use Main)
(use Door)
(use Sound)
(use Cycle)


(class LbDoor of Door
	(properties
		x 0
		y 0
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
		entranceTo 0
		locked 0
		lockedCase 0
		openSnd 46
		closeSnd 47
		openVerb 4
		listenVerb 0
		doorState 0
		doubleDoor 0
		forceOpen 0
		forceClose 1
		caller 0
		moveToX 0
		moveToY 0
		enterType 2
		exitType 2
		closeScript 0
		openScript 0
		doorPoly 0
		polyAdjust 5
	)
	
	(method (init)
		(super init:)
		(self approachVerbs: openVerb listenVerb 18)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(18
				(if cel
					(gLb2Messager say: 1 18 2 0 0 16)
					1
				else
					(gLb2Messager say: 1 18 1 0 0 16)
					1
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (open)
		(if locked
			; TEXT&SPEECH CHANGE: Prevent locked door sound from playing if message
			; mode is BOTH.
			;
			; If the player uses the DO verb on a locked door and message mode is
			; TEXT (1), a rattling sound is played while Laura's message is shown.
			; Sierra used a test to play that sound only when message mode is not
			; SPEECH (2), because the game can't play more than one digital sound
			; at once and Laura's speech would interrupt the rattling sound anyway.
			; When our new BOTH message mode (3) is set, the test will pass and
			; play the rattling sound, which is heard for a fraction of a second
			; before the speech interrupts it.
			;
			; We fix it by making the test pass only if message mode is TEXT (1).
;;;			(if (!= global90 2) (doorSound number: 48 play:))
			(if (== global90 1) (doorSound number: 48 play:))
			; END OF TEXT&SPEECH CHANGE
			(gLb2Messager say: 1 0 3 0 0 16)
			1
		else
			(if (gUser controls?) (gGame handsOff:))
			(= doorState 1)
			(self setCycle: End self)
			(if openSnd (doorSound number: openSnd play:))
			(if doubleDoor (doubleDoor setCycle: End))
		)
	)
	
	(method (listen)
		(gLb2Messager say: 1 0 4 0 0 16)
		(return 1)
	)
)

(instance doorSound of Sound
	(properties
		flags $0001
	)
)
