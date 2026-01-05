;;; Sierra Script 1.0 - (do not remove this comment)
(script# 440)
(include sci.sh)
(use Main)
(use LbDoor)
(use LBRoom)
(use ExitFeature)
(use MuseumRgn)
(use PursuitRgn)
(use Scaler)
(use PolyPath)
(use CueObj)
(use MoveFwd)
(use n958)
(use Sound)
(use Cycle)
(use View)
(use Obj)

(public
	rm440 0
	sOutTapestry 1
	rm440Door 2
	noise 3
	bolt 4
)

(instance rm440 of LBRoom
	(properties
		noun 8
		picture 440
		horizon 135
		north 448
		east 430
		south 490
		vanishingY 90
	)
	
	(method (init)
		(proc958_0 128 432 424 423 858 831 426 442 440 443)
		(proc958_0 132 442 440)
		(gEgo
			init:
			normalize: (if (== global123 5) 426 else 831)
			setScale: Scaler 155 0 190 90
		)
		(if (== global123 5)
			(self setRegions: 94)
			(global2 obstacles: (List new:))
			((ScriptID 2440 0) doit: (global2 obstacles?))
			(if (not (proc0_2 45)) ((ScriptID 94 1) seconds: 1))
		else
			(self setRegions: 90)
		)
		(switch gGNumber
			(north
				(gEgo x: 171 y: 148)
				(if (proc0_2 47) (global2 setScript: (ScriptID 444 0)))
				(gGame handsOn:)
			)
			(south
				(gEgo x: 160 y: 210 loop: 3)
				(self setScript: sEnterSouth)
			)
			(east
				(gEgo x: 197 y: 143)
				(self setScript: sEnterEast)
			)
			(else 
				(gEgo posn: 160 160)
				(gGame handsOn:)
			)
		)
		(super init:)
		(if (and (== global123 4) (proc0_10 16648 1))
			((ScriptID 443 1) addToPic:)
			(if (not (gEgo has: 12))
				((ScriptID 443 0) init: approachVerbs: 4 1 8)
			)
		)
		(if (and (> global123 2) (not (== global123 5)))
			((ScriptID 443 4)
				init:
				setOnMeCheck: 1 8192
				approachVerbs: 4 1 8
			)
		else
			(armorPippin
				init:
				approachVerbs: (if (== global123 5) 0 else 4 1 8)
			)
		)
		(rm440Door
			init:
			doubleDoor: otherHalf
			stopUpd:
			approachVerbs: 4 1 8
		)
		(otherHalf init: approachVerbs: 4 1 8)
		(bolt init:)
		(if (proc0_2 41)
			(rm440Door cel: 0)
			(otherHalf cel: 0)
			(bolt cel: 3)
		)
		(chest init:)
		(tapestry init: approachVerbs: 4 1 8)
		(painting init:)
		(dogArmor init: setOnMeCheck: 1 16384)
		(genericArmor init: setOnMeCheck: 1 8192)
		(genericFlag init: setOnMeCheck: 1 4096)
		(rightDoorway init:)
		(rearDoorway init:)
		(roundWin init:)
		(southExitFeature init:)
		((ScriptID 1881 2) x: 12 y: 85 textX: 125 textY: 0)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((proc0_1 gEgo 2)
				(otherHalf setPri: 10)
				(bolt setPri: 11)
				(global2 setScript: sExitEast)
			)
			((proc0_1 gEgo 8) (global2 setScript: sExitSouth))
		)
	)
	
	(method (dispose)
		(DisposeScript 441)
		(DisposeScript 442)
		(DisposeScript 443)
		(DisposeScript 444)
		(if (== global123 5) (DisposeScript 2440))
		(gLb2WH delete: self)
		(gLb2DH delete: self)
		(super dispose:)
	)
	
	(method (handleEvent pEvent)
		(return
			(cond 
				(inset (inset handleEvent: pEvent))
				(
					(and
						(& (pEvent type?) evJOYSTICK)
						(== (gIconBar curIcon?) (gIconBar walkIconItem?))
						(!= (pEvent message?) JOY_NULL)
						(== (gEgo view?) 443)
					)
					(pEvent claimed: 1)
					; BUGFIX: Prevent soflock after unhiding from the tapestry during the chase
					; in act 5.
					;
					; If Laura is hiding in the tapestry during the chase in act 5 and the
					; player clicks to unhide right before pursuitTimer expires, sOutTapestry
					; will be attached to gEgo and sHeKills will then be attached to the room
					; while sOutTapestry is ongoing and Laura is animating. When this is
					; correctly timed, the murderer will move next to Laura, the killing
					; animation won't occur and the game will end up softlocked while the
					; murderer and Laura stand still doing nothing.
					;
					; The cause is that sHeKills is attached to the room but sOutTapestry is
					; attached to gEgo, and being attached to different objects makes them able
					; to run simultaneously, conflicting with each other. This wouldn't happen
					; if both were attached to the current room. rm440:notify is called when
					; pursuitTimer expires, which in turn attaches sHeKills to the current
					; room if it doesn't already have a script attached, but if it has,
					; sHeKills is queued next, making them run sequentially without issues.
					;
					; We fix it by attaching sOutTapestry to the current room instead (global2).
					; This also needs to be done in rm440:doVerb.
					;(gEgo setScript: sOutTapestry)
					(global2 setScript: sOutTapestry)
					; END OF BUGFIX (see also rm440:doVerb)
				)
				; BUGFIX: Fix rm440's event handler not passing evVERB events.
				;
				; rm440 and rm448 (in #448) use their own handleEvent methods to handle
				; joystick events, overriding their default event handlers, but only pass
				; evMOVE events to super:handleEvent blocking the other event types. Clicking
				; on the rooms themselves (ex: the floor) while using any verb does nothing.
				;
				; Here we fix it in rm440:handleEvent by letting it pass both evMOVE and evVERB
				; events. SCICompanion's sci.sh doesn't define any evMOVEVERB, so we directly
				; use the $5000 value (evMOVE is $1000 and evVERB $4000, both of them = $5000).
				; Ported from:
				; https://github.com/scummvm/scummvm/blob/85702e06764f95a6b700e348dd90931613efdc29/engines/sci/engine/script_patches.cpp#L12001
;;;				((& (pEvent type?) evMOVE) (super handleEvent: pEvent))
				((& (pEvent type?) $5000) (super handleEvent: pEvent))
				; END OF BUGFIX (see also rm448:handleEvent, in #448)
				(else (return 0))
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(if (== (gEgo view?) 443)
					; BUGFIX: Prevent soflock after unhiding from the tapestry during the chase
					; in act 5 (continued).
					;
					; Continuation of the first bugfix described in rm440:handleEvenent.
					;
					; We fix it by attaching sOutTapestry to the current room instead (global2).
					;(gEgo setScript: sOutTapestry)
					(global2 setScript: sOutTapestry)
					; END OF BUGFIX (see also rm440:handleEvent)
				else
					((ScriptID 441 4) seconds: 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (notify)
		(cond 
			((== global123 5)
				(if
					(and
						(global2 script?)
						(not (== (global2 script?) (ScriptID 444 0)))
					)
					; BUGFIX: Prevent crash while exiting room 440 during the chase in act 5
					; (East and South exits).
					;
					; If pursuitTimer expires in this room during the chase in act 5,
					; rm440:notify will be called, which in turn will attach sHeKills to the
					; room to kill the player. If any other script is already attached, sHeKills
					; is queued next instead to let the current script finish. But if the timer
					; expires while the ongoing script is sExitEast or sExitSouth, sHeKills will
					; be queued right before changing rooms, consistently crashing the game.
					;
					; We fix it by modifying rm440:notify to test if sExitEast or sExitSouth are
					; attached to the current room, if so we avoid queueing sHeKills next and
					; start pursuitTimer again instead, but with a couple of seconds so it
					; immediately expires in the next room.
;;;					((global2 script?) next: (ScriptID 444 0))
					(if (proc999_5 (global2 script?) sExitEast sExitSouth) ; OneOf, are sExitEast or sExitSouth attached to the current room?
						((ScriptID 94 1) setReal: (ScriptID 94 1) 2) ; start pursuitTimer again, 2 seconds
					else
						((global2 script?) next: (ScriptID 444 0)) ; queue sHeKills next
					)
					; END OF BUGFIX
				else
					(global2 setScript: (ScriptID 444 0))
				)
			)
			((and (== global123 3) (proc0_10 8224 1)) (self setScript: sMeetingNo2))
			((and (== global123 3) (proc0_10 4104 1))
				(proc958_0 128 444 825)
				(if (== (gEgo view?) 443) (gIconBar disable: 1 2 6 5))
				(gGame handsOff:)
				(if (== (gEgo view?) 443)
					(gGame points: 1 149)
					(self setScript: (ScriptID 441 0))
				else
					(self setScript: (ScriptID 441 1))
				)
			)
		)
	)
)

(instance sMeetingNo2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(if (== (gEgo view?) 443) (gIconBar disable: 1 2 6 5))
				(gGame handsOff:)
				(proc958_0 128 820 814)
				((ScriptID 90 2) moveTo: 440)
				(= cycles 1)
			)
			(2
				((ScriptID 90 2)
					loop: 1
					setScale: Scaler 155 0 190 90
					x: 228
					y: 133
				)
				(if ((ScriptID 90 2) scaler?)
					(((ScriptID 90 2) scaler?) doit:)
				)
				(= cycles 1)
			)
			(3
				((ScriptID 90 2) view: 820)
				(= cycles 3)
			)
			(4
				(if (== (gEgo view?) 443)
					((ScriptID 90 2) setScript: (ScriptID 442 0) self)
				else
					((ScriptID 90 2) setScript: (ScriptID 442 1) self)
				)
			)
			(5
				((ScriptID 90 2) setScript: (ScriptID 442 2) self)
			)
			(6 (= cycles 3))
			(7
				(DisposeScript 442)
				(if (== (gEgo view?) 443)
					(gGame handsOn:)
					(gIconBar disable: 1 2 6 5)
				else
					(gGame handsOn:)
				)
				((ScriptID 90 2) moveTo: 430 wandering: 1)
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
				(gEgo setMotion: MoveFwd 20 self)
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
			(0
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(gEgo setMotion: MoveTo 236 136 self)
			)
			(2
				(if
					(and
						(== global123 3)
						(proc0_10 -20222 1)
						(not (proc0_2 72))
					)
					(global2 newRoom: 435)
				else
					(global2 newRoom: 430)
				)
			)
		)
	)
)

(instance sEnterSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(gEgo setMotion: MoveTo (gEgo x?) 170 self)
			)
			(2
				(gGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(gEgo setMotion: MoveTo (gEgo x?) 275 self)
			)
			(2 (global2 newRoom: 490))
		)
	)
)

(instance sHideInTapestry of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gIconBar disable: 1 2 6 5)
				(gGame handsOff:)
				(if (not (== global123 5))
					(WrapMusic pause: 1)
					(gGameMusic2 number: 5 flags: 1 loop: -1 play:)
				)
				(= cycles 1)
			)
			(1
				(gLb2WH addToFront: global2)
				(gLb2DH addToFront: global2)
				(gEgo
					view: 443
					setScale: Scaler 100 100 190 90
					loop: 1
					cel: 0
					posn: 11 147
					setCycle: CT 5 1 self
				)
			)
			(2
				(noise number: 442 flags: 1 play:)
				(gEgo setCycle: End self)
			)
			(3
				(gEgo setLoop: 0 cel: 0)
				(= cycles 1)
			)
			(4
				(if
					(or
						(and
							(== global123 3)
							(proc0_10 4104 1)
							((ScriptID 90 15) seconds?)
						)
						(and
							(== global123 3)
							(proc0_10 8224 1)
							((ScriptID 90 15) seconds?)
						)
					)
					((ScriptID 90 15) seconds: 1)
				else
					(gGame handsOn: 1)
				)
				(southExitFeature dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sOutTapestry of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(if (== (global2 script?) (ScriptID 441 0))
					(global2 script: 0)
				)
				(gEgo setCycle: End self)
				(noise number: 442 flags: 1 play:)
			)
			(2
				(gEgo
					normalize: (if (== global123 5) 426 else 831)
					setScale: Scaler 155 0 190 90
					posn: 20 151
				)
				(if
					(and
						(== ((ScriptID 90 1) room?) 440)
						(proc0_10 4104)
						(not (proc0_10 4880))
						(not (proc0_2 120))
					)
					(gEgo setScript: (ScriptID 441 3) self)
					(self dispose:)
				else
					(gLb2WH delete: global2)
					(gLb2DH delete: global2)
					(= cycles 1)
				)
			)
			(3
				(gGameMusic2 fade:)
				(if (not (== global123 5)) (WrapMusic pause: 0))
				(gGame handsOn: 1)
				(southExitFeature init:)
				; BUGFIX: Don't let sOutTapestry enable the inventory icon when there is none.
				;
				; sHideInTapestry disables the IconBar icons 1, 2, 6 and 5, and the appropiate
				; cursors. sOutTapestry enables them back, "5" being the inventory item icon.
				; If the player doesn't have any inventory item chosen (this can happen if
				; they've recently used an item and it got removed from the inventory),
				; disabling it will do nothing, as it was already disabled, but sOutTapestry
				; will enable the generic grey arrow cursor instead. The player will feel
				; tempted to try out the cursor, but clicking while it's active will make the
				; game crash instead. Moving to another room properly resets the cursors.
				;
				; We fix it by testing if there's any current inventory icon before enabling
				; the 5th icon.
;;;				(gIconBar enable: 1 2 6 5)
				(gIconBar enable: 1 2 6)
				(if (gIconBar curInvIcon?) (gIconBar enable: 5))
				; END OF BUGFIX
				(self dispose:)
			)
		)
	)
)

(instance sBoltDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(gEgo setMotion: PolyPath 213 146 self)
			)
			(2
				(gEgo
					view: 442
					loop: 2
					cel: 3
					setPri: 11
					posn: 207 144
					setScale: Scaler 100 100 190 90
					cycleSpeed: 12
					setCycle: Beg self
				)
				(bolt setPri: (- (gEgo priority?) 1))
				(bolt setCycle: End)
			)
			(3
				(noise number: 446 flags: 1 loop: 1 play:)
				(gEgo
					view: 831
					loop: 8
					setPri: -1
					cel: 6
					setScale: Scaler 155 0 190 90
					posn: 213 146
				)
				(= cycles 1)
			)
			(4
				(gEgo normalize: (if (== global123 5) 426 else 831))
				(= cycles 1)
			)
			(5
				(bolt stopUpd:)
				(if (== global123 5)
					(= cycles 1)
				else
					(client setScript: sUnBoltDoor)
				)
			)
			(6
				(gGame handsOn:)
				(rm440Door locked: 1)
				(if (== global123 5) (PursuitRgn increaseTime:))
				(proc0_3 41)
				(self dispose:)
			)
		)
	)
)

(instance sUnBoltDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(gEgo setMotion: PolyPath 213 146 self)
			)
			(2
				(gEgo
					view: 442
					loop: 2
					cel: 0
					setPri: 11
					posn: 207 144
					setScale: Scaler 100 100 190 90
					cycleSpeed: 12
					setCycle: End self
				)
				(bolt setCycle: Beg)
				(noise number: 446 flags: 1 loop: 1 play:)
			)
			(3
				(gEgo view: 831 loop: 8 setPri: -1 cel: 6 posn: 213 146)
				(= cycles 1)
			)
			(4
				(gEgo
					normalize: (if (== global123 5) 426 else 831)
					setScale: Scaler 155 0 190 90
				)
				(= cycles 1)
			)
			(5
				(bolt stopUpd:)
				(if (== global123 5)
					(= cycles 1)
				else
					(gLb2Messager say: 12 4 3 0 self)
				)
			)
			(6
				(gGame handsOn:)
				(rm440Door locked: 0)
				(if (== global123 5) (PursuitRgn decreaseTime:))
				(proc0_4 41)
				(self dispose:)
			)
		)
	)
)

(instance otherHalf of Prop
	(properties
		x 225
		y 139
		noun 12
		approachX 199
		approachY 145
		view 440
		loop 4
		cel 7
		signal $4001
	)
)

(instance rm440Door of LbDoor
	(properties
		x 211
		y 137
		noun 12
		approachX 199
		approachY 145
		view 440
		loop 3
		cel 7
		forceOpen 1
		forceClose 0
		moveToX 236
		moveToY 136
	)
	
	(method (cue)
		(super cue:)
		(bolt setPri: 11)
		(otherHalf stopUpd:)
		(bolt stopUpd:)
	)
	
	(method (open)
		(bolt setPri: 15)
		(super open:)
	)
	
	(method (close)
		(bolt setPri: 15)
		(super close:)
	)
	
	(method (createPoly)
		(super createPoly: 205 130 230 134 229 144 206 138)
	)
)

(instance bolt of Prop
	(properties
		x 225
		y 160
		z 45
		noun 12
		view 440
		loop 5
		priority 9
		signal $4011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((rm440Door locked?) (global2 setScript: sUnBoltDoor))
					((== (rm440Door doorState?) 2) (rm440Door close:))
					(else (global2 setScript: sBoltDoor))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance leftDoor of Feature ; unused, kept to not need a heap patch
	(properties
		x 94
		y 88
		noun 6
		nsTop 89
		nsLeft 90
		nsBottom 134
		nsRight 99
		sightAngle 40
	)
)

(instance chest of Feature
	(properties
		x 295
		y 140
		noun 4
		nsTop 118
		nsLeft 271
		nsBottom 163
		nsRight 319
		sightAngle 40
	)
)

(instance tapestry of Feature
	(properties
		x 28
		y 91
		noun 9
		nsTop 35
		nsLeft 5
		nsBottom 147
		nsRight 51
		sightAngle 40
		approachX 20
		approachY 151
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(if
						(and
							(not (== (gEgo view?) 443))
							(or
								(== global123 5)
								(== (== ((ScriptID 90 1) room?) 440) 0)
							)
						)
						; BUGFIX: Prevent softlock when hiding in the tapestry during the chase in
						; act 5.
						;
						; If the player clicks to hide in the tapestry at the right moment, just
						; before pursuitTimer expires, sHeKills can get attached to the current
						; room a fraction of a second before sHideInTapestry. In this specific
						; situation, sHeKills will be interrupted by sHideInTapestry, but sHeKills
						; has enough time to call handsOff before this happens. sHideInTapestry
						; also puts handsOff when it starts, so at this point we'll be in double
						; handsOff. The handsOff at the end of sHideInTapestry will only revert one
						; of the handsOffs. As a result, sHeKills will never happen and the player
						; will be left with the DO cursor but without control, softlocked while
						; Laura remains hidden in the tapestry.
						;
						; We fix it by testing if sHeKills isn't attached to the current room, if
						; it is we don't set sHideInTapestry.
;;;						(if
;;;							(or (== global123 5) (MuseumRgn nobodyAround:))
;;;								(global2 setScript: sHideInTapestry)
;;;						else
;;;							(return 1)
;;;						)
						(if
							(and
								(or (== global123 5) (MuseumRgn nobodyAround:)) ; are we in act 5 or there's nobody around?
								(!= (global2 script?) (ScriptID 444 0)) ; is sHeKills not attached to the current room?
							)
							(global2 setScript: sHideInTapestry)
						else
							(return 1)
						)
						; END OF BUGFIX
					else
						(super doVerb: theVerb &rest)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance painting of Feature
	(properties
		x 265
		y 95
		noun 5
		nsTop 79
		nsLeft 255
		nsBottom 111
		nsRight 276
		sightAngle 40
	)
)

(instance dogArmor of Feature
	(properties
		y 100
		noun 3
	)
)

(instance genericArmor of Feature
	(properties
		y 160
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (gLb2Messager say: 1 4))
			(8 (gLb2Messager say: 1 8))
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (onMe param1)
		(if (super onMe: param1)
			; BUGFIX: Correct the areas of every armor and make ego properly turn towards
			; them.
			;
			; genericArmor uses the fuschia control color, and compares mouse coordinates
			; with hardcoded areas in this onMe method, so it can pretend being multiple
			; objects by changing its noun and display different messages depending where
			; the player clicks on. The gX and gY globals are used to determine the mouse
			; coordinates, and gY takes into account the 10 extra pixels at the top used
			; for the iconbar in addition to the room's height (the top-left of the room
			; would be gY 10 gX 0). They didn't pay attention to this so all the y values
			; for the areas are wrong by 10 pixels above their intended place. As a
			; result, clicking on the greaves of the armors is handled by rm440 instead.
			;
			; Additionally, when using a verb on a Feature, ego will head towards it
			; depending on its x and y properties (these only take the room into account,
			; the 10 extra pixels of the iconbar are irrelevant). genericArmor has y set
			; but not x, so x will default to 0, making ego always face towards y 160 x 0
			; no matter what armor you click on. This looks fine for the armors on the
			; left, but totally wrong for those on the right. Lastly, the 160 value set
			; for y is more or less centered to cover all the armors, looks passable but
			; could be better, since the angle is calculated from ego's origin to the
			; target's origin, being ego's origin centered for the x axis but at the
			; bottom for the y one.
			;
			; We move the armors' areas 10 pixels down on the y axis to make them accurate
			; plus other slight adjustments. We make genericArmor's x and y properties
			; change depending on what armor has been clicked on, that way ego will head
			; towards every armor in a more convincing manner.
			(cond 
;;;				((and (< 59 gX 70) (< 83 gY 143)) (= noun 24))
				((and (< 50 gX 70) (< 93 gY 153)) (self x: 60 y: 143 noun: 24))
;;;				((and (< 72 gX 84) (< 91 gY 143)) (= noun 25))
				((and (< 70 gX 84) (< 101 gY 153)) (self x: 77 y: 143 noun: 25))
;;;				((and (< 86 gX 100) (< 93 gY 143)) (= noun 26))
				((and (< 84 gX 100) (< 103 gY 153)) (self x: 92 y: 143 noun: 26))
;;;				((and (< 103 gX 110) (< 105 gY 134)) (= noun 27))
				((and (< 103 gX 110) (< 115 gY 144)) (self x: 106 y: 134 noun: 27))
;;;				((and (< 115 gX 130) (< 104 gY 149)) (= noun 28))
				((and (< 112 gX 131) (< 114 gY 159)) (self x: 122 y: 149 noun: 28))
;;;				((and (< 171 gX 185) (< 96 gY 135)) (= noun 30))
				((and (< 171 gX 185) (< 106 gY 145)) (self x: 178 y: 135 noun: 30))
;;;				((and (< 187 gX 201) (< 91 gY 137)) (= noun 31))
				((and (< 185 gX 201) (< 101 gY 147)) (self x: 193 y: 137 noun: 31))
;;;				((and (< 225 gX 256) (< 97 gY 188)) (= noun 32))
				((and (< 220 gX 256) (< 107 gY 198)) (self x: 238 y: 188 noun: 32))
			)
			; END OF BUGFIX
		)
	)
)

(instance genericFlag of Feature
	(properties
		y 50
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (gLb2Messager say: 2 4))
			(8 (gLb2Messager say: 2 8))
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (onMe param1)
		(if (super onMe: param1)
			; BUGFIX: Correct the areas of every flag and make ego properly turn towards
			; them.
			;
			; Exact same issue described in genericArmor:onMe but for the flags. They
			; didn't take into account the 10 extra pixels that are added for the iconbar
			; and are relevant for the mouse coordinates.
			;
			; We move the flags' areas 10 pixels down on the y axis to make them accurate
			; plus other slight adjustments. We make genericFlag's x property dynamic,
			; changing whenever the player clicks on the red control color. That way ego
			; will head towards every flag in a more convincing manner.
			(= x gX) ; dynamic x
			(cond 
;;;				((and (<= 45 gX 130) (<= 0 gY 23)) (= noun 14))
				((and (<= 45 gX 130) (<= 10 gY 33)) (= noun 14))
;;;				((and (<= 76 gX 116) (<= 25 gY 43)) (= noun 15))
				((and (<= 76 gX 116) (<= 35 gY 53)) (= noun 15))
;;;				((and (<= 92 gX 117) (<= 44 gY 54)) (= noun 16))
				((and (<= 92 gX 117) (<= 54 gY 64)) (= noun 16))
;;;				((and (<= 95 gX 119) (<= 56 gY 72)) (= noun 17))
				((and (<= 95 gX 119) (<= 66 gY 82)) (= noun 17))
;;;				((and (<= 99 gX 118) (<= 72 gY 82)) (= noun 18))
				((and (<= 99 gX 118) (<= 82 gY 92)) (= noun 18))
;;;				((and (<= 106 gX 123) (<= 83 gY 95)) (= noun 19))
				((and (<= 106 gX 123) (<= 93 gY 105)) (= noun 19))
;;;				((and (<= 154 gX 177) (<= 64 gY 77)) (= noun 20))
				((and (<= 154 gX 177) (<= 74 gY 87)) (= noun 20))
;;;				((and (<= 148 gX 191) (<= 39 gY 62)) (= noun 21))
				((and (<= 148 gX 191) (<= 49 gY 72)) (= noun 21))
;;;				((and (<= 139 gX 198) (<= 0 gY 38)) (= noun 22))
				((and (<= 139 gX 198) (<= 10 gY 48)) (= noun 22))
;;;				((and (<= 215 gX 270) (<= 0 gY 20)) (= noun 23))
				((and (<= 215 gX 270) (<= 10 gY 33)) (= noun 23))
			)
			; END OF BUGFIX
		)
	)
)

(instance rightDoorway of Feature
	(properties
		x 218
		y 112
		noun 7
		nsTop 85
		nsLeft 214
		nsBottom 139
		nsRight 223
		sightAngle 40
	)
)

(instance rearDoorway of Feature
	(properties
		x 140
		y 116
		noun 13
		nsTop 101
		nsLeft 111
		nsBottom 131
		nsRight 169
		sightAngle 40
	)
)

(instance roundWin of Feature
	(properties
		x 138
		y 82
		noun 33
		nsTop 73
		nsLeft 125
		nsBottom 91
		nsRight 151
		sightAngle 40
	)
)

(instance armorPippin of Feature
	(properties
		x 151
		y 128
		noun 10
		nsTop 96
		nsLeft 140
		nsBottom 160
		nsRight 164
		sightAngle 40
		approachX 128
		approachY 165
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (== global123 5)
					(gLb2Messager say: 38)
				else
					(gLb2Messager say: 10 1 2)
				)
			)
			(4
				(if (== global123 5)
					(gLb2Messager say: 38)
				else
					(gLb2Messager say: 10 4 2)
				)
			)
			(8
				(if (== global123 5)
					(gLb2Messager say: 38)
				else
					(gLb2Messager say: 10 8 2)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance southExitFeature of ExitFeature
	(properties
		nsTop 184
		nsBottom 189
		nsRight 319
		cursor 11
		exitDir 3
		noun 35
	)
)

(instance noise of Sound
	(properties
		flags $0001
	)
)
