;;; Sierra Script 1.0 - (do not remove this comment)
(script# 444)
(include sci.sh)
(use Main)
(use Scaler)
(use PolyPath)
(use Cycle)
(use View)
(use Obj)

(public
	sHeKills 0
)

; BUGFIX: Fix murderer passing through the Medieval Armory's door while it's
; closed and prevent rm440Door from reverting hands-off.
;
; During act 5 chase, if pursuitTimer expires in the Medieval Armory (#440),
; rm440:notify will call the present script to make the murderer spawn, enter
; through the eastern entrance and kill Laura. But if pursuitTimer expires while
; the Medieval Armory's eastern door (rm440Door) is closing, this script's state
; 1 won't open the door before the murderer enters as expected, because it tests
; if rm440Door's doorState property is 0 (closed) to open it. In this case the
; test will be done while the door is still closing (doorState 1). As a result,
; the murderer will enter the room passing through a closed door.
;
; Additionally, the rm440Door:open calls in this script's first state and
; sSmashedDoorOpen's 5th state will revert hands-off, as Door:cue (in #954)
; calls handsOn(1) after it opens or closes, which gives control back to the
; player. Sierra worked around this by calling handsOff again at the start of
; sKillFromSouth and sKillFromEast (though it's unnecessary in sKillFromSouth),
; but the hands-off is still reverted during an instant before sKillFromEast is
; called.
;
; We fix these issues by adding a new state (1) to sHeKills where we first call
; handsOff if the player has control. We then test if rm440Door has a cycler
; attached (it's opening or closing), if so we'll reduce the script's state
; property by 1, which will make the script keep looping state 1 until the door
; finishes its animation. This will prevent any attempt of Door:cue of reverting
; hands-off and won't let this script change to state 2 until the door is open
; or closed. We then modify state 2 (formerly state 1), setting rm440Door's
; exitType property to 3 in the rm440Door:open call, this is an invalid exitType
; value that will make Door:cue bypass handsOn(1) after it opens. We do the same
; to the call in sSmashedDoorOpen's 5th state, and we finally revert the no
; longer necessary handsOff calls in sKillFromSouth and sKillFromEast.
(instance sHeKills of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== (gEgo view?) 443) (gIconBar disable: 0 3 4))
				(gGame handsOff:)
				(= cycles 1)
			)
			(1 ; added state
				(if (gUser canControl?) (gGame handsOff:)) ; call handsOff if the player has control
				(if ((ScriptID 440 2) cycler?) ; is rm440Door cycling?
					(-- state) ; reduce state by 1
					(= cycles 1) ; wait 1 cycle before changing to the next state (will be this same state => loop)
				else
					(self changeState: 2) ; change to state 2
				)
			)
;;;			(1
			(2 ; increase state # by 1
				(cond 
					((and (proc0_2 41) (not (proc0_2 47))) (self setScript: sSmashedDoorOpen self))
					(
						(and
							(== ((ScriptID 440 2) doorState?) 0)
							(not (proc0_2 47))
						)
;;;						((ScriptID 440 2) caller: self open:)
						((ScriptID 440 2) caller: self exitType: 3 open:) ; rm440Door. exitType: 3 will make Door:cue skip handsOn(1)
					)
					(else (= cycles 1))
				)
			)
;;;			(2
			(3 ; increase state # by 1
				(if (proc0_2 47) (oriley posn: 160 250))
				(oriley
					init:
					setCycle: Walk
					setScale: Scaler 155 0 190 90
				)
				(= cycles 3)
			)
;;;			(3
			(4 ; increase state # by 1
				(cond 
					((proc0_2 47) (self setScript: sKillFromSouth self))
					((== (gEgo view?) 443)
						(gWrapSound number: 3 loop: 1 flags: 1 play:)
						(oriley setPri: -1 setMotion: PolyPath 20 150 self)
					)
					(else (self setScript: sKillFromEast self))
				)
			)
;;;			(4
			(5 ; increase state # by 1
				(oriley view: 424 cel: 0 setCycle: End self)
			)
;;;			(5
			(6 ; increase state # by 1
				((ScriptID 440 3) number: 80 flags: 1 play:)
				(gEgo view: 858 setCycle: End self)
			)
;;;			(6
			(7 ; increase state # by 1
				(if
				(and (== (gEgo view?) 443) (gLb2WH contains: global2))
					(gLb2WH delete: global2)
				)
				(= global145 0)
				(global2 newRoom: 99)
				(self dispose:)
			)
		)
	)
)
; END OF BUGFIX (see also sKillFromSouth, sKillFromEast and sSmashedDoorOpen)

; TWEAK: Remove no longer necessary handsOff calls.
;
; The handsOff calls in sKillFromSouth and sKillFromEast were a workaround to
; rm440Door:open reverting the hands-off set in sHeKills. After our changes in
; sHeKills:changeState(2) (formerly state 1) and sSmashedDoorOpen:changeState(5),
; these aren't necessary anymore.
;
; We remove the handsOff call of each script and reduce their next states'
; number by 1.
(instance sKillFromSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
;;;			(0
;;;				(gGame handsOff:)
;;;				(= cycles 1)
;;;			)
;;;			(1
			(0 ; decrease state # by 1
				(gEgo setMotion: PolyPath 198 166 self)
			)
;;;			(2
			(1 ; decrease state # by 1
				(gWrapSound number: 3 loop: 1 flags: 1 play:)
				(oriley setMotion: MoveTo 174 174 self)
			)
;;;			(3
			(2 ; decrease state # by 1
				(self dispose:)
			)
		)
	)
)

(instance sKillFromEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
;;;			(0
;;;				(gGame handsOff:)
;;;				(= cycles 1)
;;;			)
;;;			(1
			(0 ; decrease state # by 1
				(gEgo setMotion: PolyPath 93 155 self)
			)
;;;			(2
			(1 ; decrease state # by 1
				(gWrapSound number: 3 loop: 1 flags: 1 play:)
				(oriley setMotion: MoveTo 119 153 self)
			)
;;;			(3
			(2 ; decrease state # by 1
				(self dispose:)
			)
		)
	)
)
; END OF TWEAK (see also sHeKills and sSmashedDoorOpen)

(instance sSmashedDoorOpen of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(shavings init: setLoop: 7)
				(= cycles 1)
			)
			(1
				((ScriptID 440 3) number: 444 loop: 1 flags: 1 play:)
				(shavings setCycle: End self)
			)
			(2
				(gLb2Messager say: 37 0 5 0 self)
			)
			(3
				(shavings
					cel: 0
					posn: (- (shavings x?) 2) (- (shavings y?) 1)
				)
				(= cycles 1)
			)
			(4
				((ScriptID 440 3) number: 444 loop: 1 flags: 1 play:)
				(shavings setCycle: End self)
			)
			(5
				((ScriptID 440 3) number: 444 loop: 1 flags: 1 play:)
				(shavings setCycle: End self)
				; BUGFIX: Prevent rm440Door from reverting hands-off.
				;
				; The rm440Door:open calls in this script's fifth state and sHeKills 1st state
				; (now state 2) will revert hands-off, as Door:cue (in #954) calls
				; handsOn(1) after it opens or closes, which gives control back to the player.
				; Sierra worked around this by calling handsOff again at the start of
				; sKillFromSouth and sKillFromEast (though it's unnecessary in sKillFromSouth),
				; but the hands-off is still reverted during an instant before sKillFromEast is
				; called.
				;
				; We fix it by setting rm440Door's exitType property to 3 in the
				; rm440Door:open call, this is an invalid exitType value that will make
				; Door:cue bypass handsOn(1) after it opens. We do the same to the call in
				; sHeKills:changeState(2) (formerly state 1). Lastly, we revert the no longer
				; necessary handsOff calls in sKillFromEast and sKillFromSouth.
;;;				((ScriptID 440 2) locked: 0 open:)
				((ScriptID 440 2) locked: 0 exitType: 3 open:) ; rm440Door. exitType: 3 will make Door:cue skip handsOn(1)
				; END OF BUGFIX (see also sKillFromSouth, sKillFromEast and sHeKills)
				((ScriptID 440 4) setCycle: Beg)
			)
			(6
				(shavings addToPic:)
				(self dispose:)
			)
		)
	)
)

(instance oriley of Actor
	(properties
		x 233
		y 135
		view 423
	)
)

(instance shavings of Prop
	(properties
		x 220
		y 142
		view 440
		loop 7
		signal $4000
	)
)
