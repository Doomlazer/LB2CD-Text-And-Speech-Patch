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
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(global2 newRoom: (if gGNumber else 210))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
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
				; BUGFIX: Prevent endless taxi driving.
				;
				; This code never worked as Sierra intended, they wanted the drive to last until
				; both music and speech finished playing but it ends earlier. The floppy version
				; lasts until the music ends. The test they used here for checking if music has
				; ended isn't correct, and the one for the speech can cause endless looping.
				;
				; For the music: "(== (gWrapSound prevSignal?) -1)" would only work in the floppy
				; version of the game, the CD and floppy versions have a different Sound class.
				; They could have used "(== (gWrapSound handle?) 0)" instead.
				;
				; For the speech: "(== (DoAudio audPOSITION) -1)" is unnecessary, the speech always
				; finishes before state 9 (the script won't leave state 6 until gLb2Messager ends).
				; "(DoAudio audPOSITION)" can be problematic as it'll return 0 if the game failed
				; to initialize the audio/voice card, failing the test and endlessly looping.
				;
				; We keep the short length of the travel and prevent the endless loop by disabling
				; the incorrect tests.
;;;				(if
;;;					(not
;;;						(and
;;;							(== (DoAudio audPOSITION) -1)
;;;							(== (gWrapSound prevSignal?) -1)
;;;						)
;;;					)
;;;					(-- state)
;;;				)
				; END OF BUGFIX (same has been done in sMoveBuildings:changeState(6)).
				(= cycles 1)
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
				; BUGFIX: Prevent endless taxi driving.
				;
				; We keep the short length of the travel and prevent the endless loop by disabling
				; the incorrect tests.
;;;				(if
;;;					(not
;;;						(and
;;;							(or
;;;								(not (DoSound sndGET_AUDIO_CAPABILITY))
;;;								(== (DoAudio audPOSITION) -1)
;;;							)
;;;							(== (gWrapSound handle?) 0)
;;;						)
;;;					)
;;;					(-- state)
;;;				)
;;;				(= cycles 1)
				; END OF BUGFIX (same has been done in sDoTakeOffFlight:changeState(9)).
				(= cycles 1)
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
		(switch theVerb
			(13
				(global2 newRoom: (if gGNumber else 210))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
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
		(switch theVerb
			(13
				(global2 newRoom: (if gGNumber else 210))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
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
		(switch theVerb
			(13
				(global2 newRoom: (if gGNumber else 210))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
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
		(switch theVerb
			(13
				(global2 newRoom: (if gGNumber else 210))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
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
		(switch theVerb
			(13
				(global2 newRoom: (if gGNumber else 210))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance inTicket of Inset
	(properties
		view 250
		x 190
		y 154
		disposeNotOnMe 1
		noun 9
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				((ScriptID 21 0) doit: 770)
				(ticket dispose:)
				(inTicket dispose:)
				(gEgo get: -1 1)
				(proc0_3 27)
			)
			(13
				(global2 newRoom: (if gGNumber else 210))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance showTicket of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(global2 setInset: inTicket)
				(= cycles 5)
			)
			(1
				(inTicket doVerb: 1)
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
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(1
				(global2 setScript: showTicket)
			)
			(4
				(gEgo get: 1)
				(proc0_3 27)
				((ScriptID 21 0) doit: 770)
				(self dispose:)
			)
			(13
				(global2 newRoom: (if gGNumber else 210))
			)
			(else 
				(super doVerb: theVerb param2 &rest)
			)
		)
	)
)

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
		(switch theVerb
			(13
				(global2 newRoom: (if gGNumber else 210))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
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
	
	(method (doit)
		(if (and (== theTrash self) (self inBounds:))
			(= x gX)
			(= y gY)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(gLb2MDH delete: self)
		(gLb2KDH delete: self)
		(super dispose:)
	)
	
	(method (handleEvent pEvent)
		(cond 
			(
				(and
					(== (pEvent message?) KEY_RETURN)
					(== (pEvent type?) evKEYBOARD)
					(== (gIconBar curIcon?) (gIconBar at: 2))
					(self onMe: pEvent)
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
					(== (gIconBar curIcon?) (gIconBar at: 2))
					(self onMe: pEvent)
				)
				(noise number: 54 loop: 1 flags: 1 play:)
				(= theTrash self)
				(pEvent claimed: 1)
			)
			(
				(and
					(== (pEvent type?) evMOUSERELEASE)
					(self onMe: pEvent)
				)
				(= theTrash 0)
				(pEvent claimed: 1)
			)
			(else (super handleEvent: pEvent &rest))
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(global2 newRoom: (if gGNumber else 210))
			)
			(1 (gLb2Messager say: 3 1 4))
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
