;;; Sierra Script 1.0 - (do not remove this comment)
(script# 430)
(include sci.sh)
(use Main)
(use LbDoor)
(use LBRoom)
(use PursuitRgn)
(use PChase)
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
	rm430 0
)

(instance rm430 of LBRoom
	(properties
		noun 22
		picture 430
		north 480
		east 420
		west 440
		vanishingX 185
		vanishingY 20
	)
	
	(method (init)
		(proc958_0 128 430 432 423 431 858 424)
		(proc958_0 132 430)
		(gEgo
			init:
			normalize: (if (== global123 5) 426 else 831)
			setScale: 125
		)
		(if (== global123 5)
			(self setRegions: 94)
			(global2 obstacles: (List new:))
			((ScriptID 2430 0) doit: (global2 obstacles?))
		else
			(self setRegions: 90)
		)
		(switch gGNumber
			(435
				((Timer new:) setReal: self 3)
			)
			(north
				(gEgo posn: 113 119 edgeHit: 0 setHeading: 180)
				(gGame handsOn:)
			)
			(east
				(self setScript: sEnterEast)
			)
			(west
				(gEgo x: 51 y: 121)
				(self setScript: sEnterWest)
			)
			(else 
				(gEgo posn: 160 160)
				(gGame handsOn:)
			)
		)
		(super init:)
		(if
			(or
				(> global123 3)
				(and (== global123 3) (proc0_10 -20222 1))
			)
			(skewer init: addToPic: approachVerbs: 1 8)
			(if (not (proc0_2 44)) (wireEnd init:))
		else
			(pterodactyl init: addToPic:)
		)
		(if (proc0_2 45) (doorWire init:))
		(ceiling init:)
		(westDoor init: stopUpd: doubleDoor: westDoor2)
		(eastDoor
			forceOpen: (if (proc0_2 99) 0 else 1)
			; BUGFIX: Fix eastDoor always initializing unlocked.
			;
			; eastDoor always initializes unlocked, even when the door is wired shut.
			; There are other issues with this door that we cover in eastDoor:doVerb.
			;
			; We fix it by making its locked property match the value of the wired shut
			; flag (flag 45). Fix based on:
			; https://github.com/scummvm/scummvm/blob/85702e06764f95a6b700e348dd90931613efdc29/engines/sci/engine/script_patches.cpp#L11543
			locked: (proc0_2 45) ; IsFlag 45 (door is wired shut)
			; END OF BUGFIX (see also eastDoor:doVerb)
			init:
			stopUpd:
			doubleDoor: eastDoor2
		)
		(westDoor2 init: stopUpd: approachVerbs: 4)
		(eastDoor2
			init:
			cel: (if (proc0_2 99) 0 else 9)
			stopUpd:
			approachVerbs: 4
			; BUGFIX: Prevent ego from getting stuck near the east exit.
			;
			; If Laura is moved near the bottom part of the east exit (approx. at x 309
			; y 129), she can collide with various pixels of eastDoor2's collision box,
			; which is a small 4x4 box. This is an annoyance that interrupts ego's
			; movement, and in the worst cases it can get ego stuck there and softlock
			; the game. There's no good reason for letting eastDoor2 collide with
			; actors.
			;
			; We fix it by setting eastDoor2's ignoreActors property as true during
			; initialization to make it not collide with actors.
			ignoreActors: 1
			; END OF BUGFIX
		)
		(triceratops init:)
		(spinosaur init:)
		(eggs init: setOnMeCheck: 1 16)
		(babies init: setOnMeCheck: 1 32)
		(struth init: setOnMeCheck: 1 64)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((proc0_1 gEgo 2) (global2 setScript: sExitEast))
			((proc0_1 gEgo 4) (global2 setScript: sExitWest))
			((proc0_1 gEgo 8) (self newRoom: north))
		)
	)
	
	(method (dispose)
		(DisposeScript 2430)
		(super dispose: &rest)
	)
	
	(method (cue)
		(if
		(and (== global123 3) (not (proc0_10 -20222)))
			((ScriptID 22 0) doit: -20222)
		)
		(cond
			; BUGFIX: Prevent sDie being interrupted by sGetThatWire
			;
			; If the player doesn't have the wire in act 5 and uses the pliers to get it
			; in rm435, pursuitTimer can expire right when the game returns to rm430,
			; depending on the timing. In that case sDie will be attached to the room as
			; expected, but sGetThatWire will interrupt it immediately after. The
			; murderer will not appear, Laura won't die and the timer won't restart.
			;
			; We fix it by checking in the cond before anything else if sDie is attached
			; to the current room. If it is, we do nothing else, letting sDie continue
			; so it properly kills Laura.
;;;			((proc0_2 63) (global2 setScript: sGetThatWire) (proc0_4 63))
;;;			((!= (global2 script?) sDie) (gGame handsOn:))
			((== (global2 script?) sDie)) ; is sDie attached to the current room? Do nothing.
			((proc0_2 63) (global2 setScript: sGetThatWire) (proc0_4 63))
			(else (gGame handsOn:))
			; END OF BUGFIX
		)
	)
	
	(method (notify)
		(if (== global123 5)
			(if (global2 script?)
				; BUGFIX: Prevent pursuitTimer from breaking if it expires during sExitWest.
				;
				; In act 5, if pursuitTimer expires when the player is using the west exit
				; and sExitWest is ongoing, sDie will be queued next, but the queue will be
				; gone when changing rooms. In this case sDie won't happen and the murderer
				; will no longer appear.
				;
				; We fix this by testing in rm430:notify if sExitWest is attached to the
				; current room when pursuitTimer expires, if it is we start pursuitTimer
				; again, but with a couple of seconds so it expires in the next room.
;;;				((global2 script?) next: sDie)
				(if (== (global2 script?) sExitWest) ; is sExitWest attached to the room?
					((ScriptID 94 1) setReal: (ScriptID 94 1) 2) ; start pursuitTimer again, 2 seconds
				else
					((global2 script?) next: sDie) ; otherwise queue sDie next
				)
				; END OF BUGFIX
			else
				(global2 setScript: sDie)
			)
		)
	)
)

(instance sDie of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(eastDoor locked: 0)
				(= seconds 3)
			)
			(1
				(gGame handsOff:)
				(gGame setCursor: global21)
				(gEgo setMotion: PolyPath 160 160)
				(if (== (eastDoor doorState?) 0)
					(doorWire dispose:)
					(eastDoor caller: self open:)
					(gGameMusic2 number: 444 flags: 1 loop: 1 play:)
					(splinters init: setCycle: End)
				else
					(= cycles 1)
				)
			)
			(2
				(if (== (eastDoor doorState?) 0) (splinters addToPic:))
				(oriley
					init:
					setScale: 125
					setCycle: Walk
					setMotion: MoveTo 281 125 self
				)
				(gWrapSound number: 3 flags: 1 loop: 1 play:)
			)
			(3
				(oriley setMotion: PChase gEgo 22 self)
			)
			(4
				(oriley view: 424)
				(oriley cel: 0)
				(proc0_5 gEgo oriley)
				(proc0_5 oriley gEgo)
				(= cycles 4)
			)
			(5 (oriley setCycle: End self))
			(6
				(thudSound play:)
				(gEgo view: 858 setCycle: End self)
			)
			(7
				(= global145 0)
				(global2 newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance sWireItShut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(PursuitRgn increaseTime:)
				(eastDoor ignoreActors: forceUpd:)
				(proc0_3 45)
				(= cycles 1)
			)
			(1
				(gGame handsOff:)
				(if (!= (eastDoor doorState?) 0)
					(gEgo setMotion: PolyPath 274 131 self)
				else
					(= cycles 1)
				)
			)
			(2
				(if (!= (eastDoor doorState?) 0)
					(eastDoor close:)
					(= ticks 90)
				else
					(= cycles 1)
				)
			)
			(3
				(gEgo setMotion: MoveTo 283 124 self)
			)
			(4
				(gEgo
					view: 431
					setScale: Scaler 100 100 12 5
					loop: 3
					cel: 0
					setCycle: End self
				)
			)
			(5
				(doorWire init:)
				(gEgo normalize: 426 setScale: 125)
				(gEgo setMotion: MoveTo 283 124 self)
			)
			(6
				(gEgo put: 34)
				(eastDoor locked: 1)
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetThatWire of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(gEgo setMotion: PolyPath 43 141 self)
			)
			(1
				(gEgo
					view: 431
					setScale: 140
					loop: 0
					cel: 0
					setCycle: CT 7 1 self
				)
			)
			(2
				(gEgo setCycle: End self)
				(wireEnd dispose:)
				(gGameMusic2 number: 430 flags: 1 loop: 1 play:)
			)
			(3
				(gEgo
					normalize: (if (== global123 5) 426 else 831)
					setScale: 125
				)
				(gEgo setMotion: MoveTo 62 144 self)
			)
			(4
				(proc0_3 44)
				(gEgo get: 34)
				((ScriptID 21 0) doit: 803)
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(gEgo
					edgeHit: 0
					posn: 291 125
					setHeading: 270
					setMotion: MoveTo 256 125 self
				)
			)
			(2
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(gEgo
					edgeHit: 0
					setHeading: 90
					setMotion: PolyPath 100 133 self
				)
			)
			(2
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			; IMPROVEMENT: Kill Laura if the player tries to return to rm420 in act 5.
			;
			; When act 5 begins the initial room is rm420, it starts the chase music
			; and the murderer's entrance, and exiting from it sets pursuitTimer. If
			; the player returns to that room, everything restarts, every time, and
			; exiting sets pursuitTimer again. All this causes unexpected things to
			; happen. It's clear that the player isn't supposed to re-enter that
			; room in the first place, but Sierra never put any countermeasure to
			; prevent it from happening. Note that rm420 can only be re-entered from
			; rm430's East door.
			;
			; We approach it by not letting ego pass through rm430's East door
			; during act 5, moving ego back inside + expiring pursuitTimer whenever
			; it is attempted. This calls rm430:notify, queueing sDie so it's
			; attached to the current room next, making the murderer appear and kill
			; Laura. This is similar to how the player is punished in other rooms.
;;;			(0
;;;				(gGame handsOff:)
;;;				(gEgo heading: 90 setMotion: MoveTo 320 (gEgo y?) self)
;;;			)
;;;			(1
;;;				(gEgo edgeHit: 2)
;;;				(global2 newRoom: (global2 east?))
;;;				(self dispose:)
;;;			)
			(0
				(gGame handsOff:)
				(if (== global123 5) ; is it act 5?
					(gEgo heading: 90 setMotion: MoveTo 300 124 self) ; move ego to a safe position to prevent getting stuck
					((ScriptID 94 1) seconds: 0) ; expire pursuitTimer (will make sDie be queued next)
				else
					(gEgo heading: 90 setMotion: MoveTo 320 (gEgo y?) self)
				)
			)
			(1
				(if (!= global123 5) ; is not act 5?
					(gEgo edgeHit: 2)
					(global2 newRoom: (global2 east?))
				)
				(self dispose:)
			)
			; END OF IMPROVEMENT
		)
	)
)

(instance sExitWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(gEgo heading: 270 setMotion: MoveTo 27 (gEgo y?) self)
			)
			(1
				(gEgo edgeHit: 4)
				(global2 newRoom: (global2 west?))
				(self dispose:)
			)
		)
	)
)

(instance oriley of Actor
	(properties
		x 340
		y 125
		view 423
	)
)

(instance splinters of Prop
	(properties
		x 299
		y 131
		view 432
		loop 4
		signal $4000
	)
)

(instance westDoor2 of Prop
	(properties
		x 74
		y 66
		approachX 73
		approachY 125
		view 432
		loop 1
		cel 4
	)
	
	(method (doVerb theVerb)
		(westDoor doVerb: theVerb)
	)
)

(instance eastDoor2 of Prop
	(properties
		x 312
		y 68
		approachX 287
		approachY 128
		view 432
		loop 3
		cel 9
		priority 9
		signal $0010
	)
	
	(method (doVerb theVerb)
		(eastDoor doVerb: theVerb)
	)
)

(instance doorWire of View
	(properties
		x 300
		y 96
		view 431
		loop 2
		cel 1
		priority 9
		signal $0010
	)
)

(instance wireEnd of View
	(properties
		x 21
		y 130
		view 431
		loop 4
		cel 1
		priority 15
		signal $4010
	)
)

(instance skewer of View
	(properties
		x 3
		y 189
		z 89
		noun 7
		approachX 150
		approachY 170
		view 430
		loop 1
		priority 15
		signal $5010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (global2 newRoom: 435))
			(8 (global2 newRoom: 435))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pterodactyl of View
	(properties
		x 43
		y 189
		z 107
		noun 6
		view 430
		priority 14
		signal $5010
	)
)

(instance ceiling of Feature
	(properties
		y 6
		noun 21
		nsBottom 20
		nsRight 320
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(gLb2Messager
					say:
						21
						1
						(if
							(or
								(> global123 3)
								(and (== global123 3) (proc0_10 -20222 1))
							)
							2
						else
							1
						)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance triceratops of Feature
	(properties
		x 202
		y 72
		noun 4
		nsTop 39
		nsLeft 135
		nsBottom 105
		nsRight 270
		sightAngle 40
		approachX 208
		approachY 116
	)
)

(instance spinosaur of Feature
	(properties
		x 19
		y 87
		noun 5
		nsTop 61
		nsLeft 4
		nsBottom 114
		nsRight 34
		sightAngle 40
		approachX 37
		approachY 151
	)
)

(instance eggs of Feature
	(properties
		x 249
		y 100
		noun 1
		sightAngle 40
		approachX 254
		approachY 116
	)
)

(instance babies of Feature
	(properties
		x 252
		y 92
		noun 3
		sightAngle 40
		approachX 255
		approachY 116
	)
)

(instance struth of Feature
	(properties
		x 234
		y 147
		noun 2
		sightAngle 40
		approachX 153
		approachY 168
	)
)

(instance eastDoor of LbDoor
	(properties
		x 292
		y 68
		noun 18
		approachX 287
		approachY 128
		view 432
		loop 2
		entranceTo 420
		forceClose 0
		moveToX 319
		moveToY 115
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			; BUGFIX: Fix various issues with eastDoor when it's wired shut.
			;
			; The game uses 2 flags to determine if eastDoor is closed or wired shut.
			; Flag 99 is used when it's closed, 45 when it's wired shut. There are three
			; problems with how these flags are used:
			;
			; 1) Using the wire on an opened door sets the flag 45 but not 99.
			;
			; 2) The door is always initialized unlocked, no matter if flag 45 is set.
			;
			; 3) Using the DO verb on the closed door clears flag 99 even when wired shut.
			;
			; We fix 1 by always setting flag 99 when the wire is used on it in act 5. We
			; fix 2 in rm430:init, making eastDoor initialize with its locked property
			; matching the value of flag 45. Finally, we fix 3 by testing if the door is
			; unlocked when using the DO verb on it, to allow setting or clearing flag 99
			; only if it's unlocked. Fixes based on:
			; https://github.com/scummvm/scummvm/blob/85702e06764f95a6b700e348dd90931613efdc29/engines/sci/engine/script_patches.cpp#L11543
			(44 ; wire
				(if (== global123 5)
					(global2 setScript: sWireItShut)
					(proc0_3 99) ; SetFlag 99 (door is closed)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(4 ; DO
				(if (not locked) ; is the door unlocked?
					(if (== doorState 0) (proc0_4 99) else (proc0_3 99))
				)
				(super doVerb: theVerb &rest)
			)
			; END OF BUGFIX (see also rm430:init)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(eastDoor2 stopUpd:)
	)
	
	(method (createPoly)
		(super
			createPoly: 292 110 319 123 319 133 309 137 280 122
		)
	)
)

(instance westDoor of LbDoor
	(properties
		x 53
		y 66
		noun 19
		approachX 73
		approachY 125
		view 432
		priority 9
		signal $0010
		entranceTo 440
		forceOpen 1
		forceClose 0
		moveToX 31
		moveToY 127
	)
	
	(method (cue)
		(super cue:)
		(westDoor2 stopUpd:)
	)
	
	(method (createPoly)
		(super createPoly: 47 126 66 113 85 120 68 132)
	)
)

(instance thudSound of Sound
	(properties
		flags $0001
		number 80
	)
)
