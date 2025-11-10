;;; Sierra Script 1.0 - (do not remove this comment)
(script# 560)
(include sci.sh)
(use Main)
(use LbDoor)
(use LBRoom)
(use Inset)
(use Scaler)
(use PolyPath)
(use CueObj)
(use n958)
(use Timer)
(use Sound)
(use Cycle)
(use View)
(use Obj)

(public
	rm560 0
	westDoor 4
)

(local
	local0 ; unused after the closing safe/painting bugfix, kept to not need a heap patch
	local1
	local2
)
(instance rm560 of LBRoom
	(properties
		picture 560
		north 666
		west 550
		vanishingX 0
	)
	
	(method (init)
		(proc958_0 128 560 561 562 563 564 814 831)
		(proc958_0 132 560 561 558 562 564 566 565 44 45 721 567)
		(gEgo
			init:
			normalize: 831
			ignoreActors:
			setScale: Scaler 130 0 190 0
		)
		(self setRegions: 90)
		(switch gGNumber
			(north
				(global2 setScript: sEnterTunnel)
			)
			(west
				(gEgo edgeHit: 0 setHeading: 270)
				(if (proc999_5 global111 1 7)
					(++ global111)
					(westDoor locked: 1)
					(waterPrompt setReal: waterPrompt 5)
				)
				(if
					(or
						(> global123 3)
						(and (== global123 3) (proc0_10 -15612 1))
					)
					(proc0_5 gEgo deadWatney)
					(gGame points: 1 171)
				)
			)
			(565 0 (gGame handsOn:))
			(else 
				(gEgo posn: 20 180)
				(gGame handsOn:)
			)
		)
		(super init:)
		(if (== gGNumber north)
			(Palette palSET_INTENSITY 0 255 100)
		)
		(gGameMusic2 number: 565 loop: -1 flags: 1 play:)
		(if
			(or
				(> global123 3)
				(and (== global123 3) (proc0_10 -15612 1))
			)
			(deskClock addToPic:)
			(deadWatney addToPic:)
		else
			(deskClock setCel: 4 addToPic:)
			(porcupine addToPic: approachVerbs: 4 1 8)
		)
		(calendar addToPic: approachVerbs: 4 1 8)
		(intercom addToPic: approachVerbs: 4 1 8)
		(phoneList addToPic: approachVerbs: 4 1 8)
		(phone addToPic: approachVerbs: 4 1 8)
		(safePic init: stopUpd: approachVerbs: 4 1 8)
		(westDoor init:)
		(if (!= gGNumber north) (secretDoor init: stopUpd:))
		(genericMask init:)
		(pillars init: setOnMeCheck: 1 8)
		(desk init: approachVerbs: 1 setOnMeCheck: 1 2048)
		(drawers init: setOnMeCheck: 1 8192)
		(deskLamp init: setOnMeCheck: 1 4096)
		(chair init: setOnMeCheck: 1 16384)
		(rug init: setOnMeCheck: 1 1024)
		(fireplaceOut init: setOnMeCheck: 1 512)
		(fireplaceIn
			init:
			approachVerbs: 4 1 8
			setOnMeCheck: 1 256
		)
		(bookcase init: setOnMeCheck: 1 16)
		(genericBookshelf init:)
		(book init: approachVerbs: 4 1 8 setOnMeCheck: 1 64)
		(bigPainting init:)
		(skylightBase init: setOnMeCheck: 1 16)
		(skylightSupport init: setOnMeCheck: 1 64)
		(nightSky init: setOnMeCheck: 1 32)
		(buildingBig init: setOnMeCheck: 1 256)
		(buildingPointed init: setOnMeCheck: 1 128)
		(if
			(and
				(== gGNumber 565)
				(== global123 3)
				(not (proc0_10 -15612))
			)
			((ScriptID 22 0) doit: -15612)
			((ScriptID 90 1) wandering: 0 room: -1)
		)
	)
	
	(method (doit)
		(super doit:)
		; BUGFIX: Fix picture and safe closing by themselves.
		;
		; The floppy version checks ego's coordinates and auto-closes both painting and
		; safe when it moves, by calling sDumpSafe. This was ditched from the CD version,
		; they set everything up so when sDumpSafe is called, it won't close the painting
		; and safe while they're open or opening. The new code relies on tight timings
		; and doesn't account for fast machines, those run rm560:doit too fast, passing
		; the tests multiple times when the painting is still opening, ending up calling
		; sDumpSafe in a non-controlled manner, which in turn closes it.
		;
		; Disabling the code fixes opening the picture/safe by not using sDumpSafe anymore,
		; with an inconvenient: safePic handles the painting when the player enters the
		; room, the "DO" verb makes safePic call safePicture (from script #561) to open it
		; and then hides itself. From then on, safePicture handles the painting until it's
		; closed again. Normally, sDumpSafe would call safePic:show after closing the
		; painting to make safePic handle it back, but we don't use it now. All works, as
		; safePicture can open and close the picture by itself, but if the player for
		; example uses the "LOOK" verb on the closed picture, safePicture will trigger the
		; "opened message" instead, as it isn't meant to handle the picture while closed.
		;
		; We fix it by disabling the offending code, we also modify safePicture:doVerb in
		; script #561 to make it impersonate safePic whenever the picture is closed and the
		; player uses any verb on it except "DO".
		;
;;;		(if
;;;			(and
;;;				local0
;;;				(not ((ScriptID 561 0) cel?))
;;;				(!= ((ScriptID 32 0) room?) gNumber)
;;;			)
;;;			(= local0 0)
;;;			(self setScript: sDumpSafe)
;;;		)
		; END OF BUGFIX (continued in safePicture:doVerb, DialFeature.sc (script #561)).
	)
	
	(method (dispose)
		(proc958_0 0 561 562)
		(gGameMusic2 fade:)
		(super dispose: &rest)
	)
)

(instance westDoor of LbDoor
	(properties
		x 17
		y 148
		noun 36
		sightAngle 40
		approachX 14
		approachY 160
		view 560
		loop 1
		priority 7
		signal $0010
		entranceTo 550
		listenVerb 38
		moveToX 4
		moveToY 145
		enterType 0
		exitType 0
	)
	
	(method (init)
		(super init:)
		(self approachVerbs: 4 38)
	)
	
	(method (listen)
		(switch global111
			(2
				(gLb2Messager say: 1 38 1 0 0 1560)
				(++ global111)
				(= locked 0)
			)
			(8
				(gLb2Messager say: 1 38 2 0 0 1560)
				(++ global111)
				(= locked 0)
			)
			(else  (super listen: &rest))
		)
	)
	
	(method (createPoly)
		(super createPoly: 0 142 23 136 31 147 0 153)
	)
)

(instance secretDoor of Prop
	(properties
		x 294
		y 147
		view 560
		loop 2
		cycleSpeed 12
	)
)

(instance safePic of View
	(properties
		x 82
		y 100
		noun 77
		approachX 93
		approachY 149
		view 564
		loop 1
		priority 6
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(proc0_5 gEgo safePic)
				((ScriptID 561 0) init:)
;;;				(= local0 1) ; unused after the closing safe/painting bugfix
				(self hide:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance deadWatney of View
	(properties
		x 137
		y 114
		view 560
		priority 11
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(gGame handsOff:)
				(proc0_5 gEgo deadWatney)
				(global2 newRoom: 565)
			)
			(8
				(gGame handsOff:)
				(proc0_5 gEgo deadWatney)
				(global2 newRoom: 565)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance porcupine of View
	(properties
		x 131
		y 112
		noun 37
		approachX 162
		approachY 170
		view 560
		cel 1
		priority 12
		signal $4010
	)
)

(instance deskClock of View
	(properties
		x 128
		y 114
		noun 41
		approachX 89
		approachY 185
		view 563
		loop 4
		cel 5
		priority 11
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (global2 setInset: inClock))
			(8 (global2 setInset: inClock))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance intercom of View
	(properties
		x 132
		y 122
		noun 42
		approachX 93
		approachY 171
		view 563
		loop 4
		cel 3
		priority 12
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(global2 setInset: (ScriptID 562 0))
			)
			(8
				(global2 setInset: (ScriptID 562 0))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance phone of View
	(properties
		x 83
		y 118
		noun 58
		approachX 62
		approachY 181
		view 563
		loop 4
		cel 6
		priority 12
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (global2 setInset: inPhone))
			(8 (global2 setInset: inPhone))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance phoneList of View
	(properties
		x 109
		y 123
		noun 43
		approachX 77
		approachY 178
		view 563
		loop 4
		priority 12
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(global2 setInset: inPhonelist)
			)
			(8
				(global2 setInset: inPhonelist)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance calendar of View
	(properties
		x 120
		y 118
		noun 44
		approachX 99
		approachY 178
		view 563
		loop 4
		cel 1
		priority 12
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(++ local2)
				(global2 setInset: inCalendar)
			)
			(8
				(++ local2)
				(global2 setInset: inCalendar)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance genericMask of Feature
	(properties
		y 2
	)
	
	(method (onMe param1)
		(if (super onMe: param1)
			(cond 
				(
				(and (<= 34 gX) (<= gX 50) (<= 63 gY) (<= gY 87)) (= noun 4))
				(
				(and (<= 34 gX) (<= gX 50) (<= 94 gY) (<= gY 126)) (= noun 5))
				(
				(and (<= 265 gX) (<= gX 282) (<= 71 gY) (<= gY 97)) (= noun 6))
				(
				(and (<= 265 gX) (<= gX 281) (<= 101 gY) (<= gY 127)) (= noun 7))
			)
		)
	)
)

(instance pillars of Feature
	(properties
		y 1
		noun 8
	)
)

(instance desk of Feature
	(properties
		y 117
		noun 9
		approachX 114
		approachY 171
	)
)

(instance drawers of Feature
	(properties
		y 1
		noun 10
	)
)

(instance deskLamp of Feature
	(properties
		y 1
		noun 11
	)
)

(instance chair of Feature
	(properties
		y 1
		noun 12
	)
)

(instance rug of Feature
	(properties
		y 1
		noun 13
	)
)

(instance fireplaceOut of Feature
	(properties
		y 1
		noun 14
	)
)

(instance fireplaceIn of Feature
	(properties
		y 1
		noun 15
		approachX 78
		approachY 145
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (or (gEgo has: 33) (proc0_2 35))
					(gLb2Messager say: 15 1)
				else
					(global2 setInset: inCharcoal)
				)
			)
			(4
				(if (or (gEgo has: 33) (proc0_2 35))
					(gLb2Messager say: 15 4 10)
				else
					(gLb2Messager say: 15 4 11)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bookcase of Feature
	(properties
		y 1
		noun 16
	)
)

(instance genericBookshelf of Feature
	(properties
		y 2
		approachX 184
		approachY 169
	)
	
	(method (onMe param1)
		(if (super onMe: param1)
			(cond 
				(
				(and (<= 185 gX) (<= gX 201) (<= 80 gY) (<= gY 92)) (= noun 17))
				(
				(and (<= 203 gX) (<= gX 241) (<= 75 gY) (<= gY 89)) (= noun 18))
				(
				(and (<= 184 gX) (<= gX 201) (<= 94 gY) (<= gY 107)) (= noun 19))
				(
				(and (<= 203 gX) (<= gX 240) (<= 90 gY) (<= gY 106)) (= noun 20))
				(
				(and (<= 203 gX) (<= gX 239) (<= 108 gY) (<= gY 120)) (= noun 21))
			)
		)
	)
)

(instance book of Feature
	(properties
		x 221
		y 115
		heading 90
		noun 22
		nsTop 90
		nsLeft 229
		nsBottom 105
		nsRight 240
		approachX 214
		approachY 155
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(global2 setScript: sGetBook)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bigPainting of Feature
	(properties
		x 154
		y 99
		noun 23
		nsTop 86
		nsLeft 135
		nsBottom 113
		nsRight 174
	)
)

(instance skylightBase of Feature
	(properties
		x 161
		y 36
		noun 31
		nsTop 1
		nsLeft 50
		nsBottom 72
		nsRight 272
	)
)

(instance skylightSupport of Feature
	(properties
		x 161
		y 36
		noun 32
		nsTop 1
		nsLeft 50
		nsBottom 72
		nsRight 272
	)
)

(instance nightSky of Feature
	(properties
		x 161
		y 36
		noun 33
		nsTop 1
		nsLeft 50
		nsBottom 72
		nsRight 272
	)
)

(instance buildingBig of Feature
	(properties
		x 161
		y 36
		noun 34
		nsTop 1
		nsLeft 50
		nsBottom 72
		nsRight 272
	)
)

(instance buildingPointed of Feature
	(properties
		x 161
		y 36
		noun 35
		nsTop 1
		nsLeft 50
		nsBottom 72
		nsRight 272
	)
)

(instance inBookClosed of Inset
	(properties
		view 562
		x 216
		y 69
		disposeNotOnMe 1
		noun 47
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(global2 setInset: inBookOpen)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inBookOpen of Inset
	(properties
		view 562
		loop 1
		x 216
		y 69
		priority 13
		disposeNotOnMe 1
		noun 48
	)
	
	(method (init)
		(super init: &rest)
		(if (not (gEgo has: 24)) (file init:))
		(gIconBar disable: 7)
	)
	
	(method (dispose)
		(if (IsObject file) (file dispose: delete:))
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (gEgo has: 24)
					(gLb2Messager say: noun 1 9)
				else
					(gLb2Messager say: noun 1 8)
				)
			)
			(8
				(if (gEgo has: 24)
					(gLb2Messager say: noun 8 9)
				else
					(gLb2Messager say: noun 8 8)
				)
			)
			(4
				(if (gEgo has: 24)
					(gLb2Messager say: noun 4 9)
				else
					(gEgo get: 24)
					((ScriptID 21 0) doit: 793)
					((ScriptID 21 0) doit: 272)
					(gGame points: 1 172)
					(file dispose:)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance file of View
	(properties
		x 218
		y 71
		view 562
		loop 1
		cel 1
		priority 14
		signal $0010
	)
	
	(method (doVerb theVerb)
		(inBookOpen doVerb: theVerb &rest)
	)
)

(instance inCharcoal of Inset
	(properties
		view 563
		cel 3
		x 21
		y 124
		disposeNotOnMe 1
		noun 45
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(global2 setScript: sGetCoal)
				(self dispose:)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inClock of Inset
	(properties
		view 563
		x 101
		y 105
		disposeNotOnMe 1
		noun 52
	)
	
	(method (init)
		(if (proc0_2 3) (self cel: 1 noun: 53))
		(super init: &rest)
	)
)

(instance inPhone of Inset
	(properties
		view 563
		loop 3
		cel 3
		x 79
		y 99
		disposeNotOnMe 1
		noun 59
	)
)

(instance inPhonelist of Inset
	(properties
		view 563
		loop 2
		x 87
		y 93
		disposeNotOnMe 1
		noun 49
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(global2 setInset: inPhoneOpen)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inPhoneOpen of Inset
	(properties
		view 563
		loop 2
		cel 1
		x 87
		y 93
		disposeNotOnMe 1
		noun 50
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (gLb2Messager say: 50 1))
			(8
				(if local2
					(gLb2Messager say: noun theVerb 6)
				else
					(gLb2Messager say: noun theVerb 7)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inCalendar of Inset
	(properties
		view 563
		cel 4
		x 108
		y 99
		disposeNotOnMe 1
		noun 51
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(gGame points: 1 173)
				(gLb2Messager say: 51 1)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sGetCoal of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(gEgo
					normalize: 831
					setScale: Scaler 130 0 190 0
					setMotion: PolyPath 79 143 self
				)
			)
			(1
				(gEgo
					view: 561
					setLoop: 1
					cel: 0
					posn: 78 141
					setScale: Scaler 100 100 190 0
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(2
				(sFX number: 564 flags: 1 loop: 1 play:)
				(gGame points: 1 175)
				(= cycles 3)
			)
			(3 (gEgo setCycle: Beg self))
			(4
				(gEgo
					normalize: 831
					setScale: Scaler 130 0 190 0
					loop: 1
					posn: 79 143
				)
				(= cycles 1)
			)
			(5
				(gEgo get: 33)
				((ScriptID 21 0) doit: 802)
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetBook of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(gEgo
					normalize: 831
					setScale: Scaler 130 0 190 0
					setMotion: PolyPath 214 155 self
				)
			)
			(1
				(gEgo setHeading: 90)
				(= cycles 1)
			)
			(2
				(gEgo
					view: 561
					setScale: 155
					setLoop: 0
					cel: 0
					posn: 218 153
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(3 (= ticks 30))
			(4
				(sFX number: 566 flags: 1 loop: 1 play:)
				(gEgo setCycle: Beg self)
			)
			(5 (= ticks 60))
			(6
				(global2 setInset: inBookClosed)
				(gEgo
					normalize: 831
					setScale: Scaler 130 0 190 0
					loop: 0
					posn: 214 155
				)
				(= cycles 1)
			)
			(7
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDumpSafe of Script ; unused after the closing safe/painting bugfix, kept to not need a heap patch
	(properties)
	
	(method (changeState newState)
;;;		(switch (= state newState)
;;;			(0
;;;				(if
;;;					(==
;;;						((ScriptID 561 1) cel?)
;;;						((ScriptID 561 1) lastCel:)
;;;					)
;;;					(gGame handsOff:)
;;;					((ScriptID 561 1) setPri: 5 setCycle: Beg self)
;;;					(= local1 1)
;;;				else
;;;					(= cycles 1)
;;;				)
;;;			)
;;;			(1
;;;				(if local1
;;;					(sFX number: 561 flags: 1 loop: 1 play:)
;;;					(= local1 0)
;;;					(= ticks 60)
;;;				else
;;;					(= cycles 1)
;;;				)
;;;			)
;;;			(2
;;;				(if (!= ((ScriptID 561 0) cel?) 0)
;;;					((ScriptID 561 0) setCycle: Beg self)
;;;					(sFX number: 45 flags: 1 loop: 1 play:)
;;;				else
;;;					(= cycles 1)
;;;				)
;;;			)
;;;			(3
;;;				(safePic show:)
;;;				((ScriptID 561 0) dispose:)
;;;				(= cycles 2)
;;;			)
;;;			(4
;;;				(DisposeScript 561)
;;;				(gGame handsOn:)
;;;				(self dispose:)
;;;			)
;;;		)
	)
)

(instance sEnterTunnel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(secretDoor init:)
				(= cycles 2)
			)
			(1 (= seconds 3))
			(2
				(gEgo x: 306 y: 147 setPri: 5 setHeading: 180)
				(sFX number: 721 flags: 1 loop: 1 play:)
				(secretDoor setCycle: End self)
			)
			(3
				(sFX stop:)
				(gEgo setPri: -1 setMotion: MoveTo 303 177 self)
			)
			(4
				(sFX number: 721 flags: 1 loop: 1 play:)
				(secretDoor setCycle: Beg self)
			)
			(5
				(secretDoor stopUpd:)
				(sFX stop:)
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance waterPrompt of Timer
	(properties)
	
	(method (cue)
		(gLb2Messager say: 79)
	)
)

(instance sFX of Sound
	(properties
		flags $0001
	)
)
