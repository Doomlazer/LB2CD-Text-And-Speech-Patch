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

(instance sHeKills of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== (gEgo view?) 443) (gIconBar disable: 0 3 4))
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(cond 
					((and (proc0_2 41) (not (proc0_2 47))) (self setScript: sSmashedDoorOpen self))
					(
						(and
							(== ((ScriptID 440 2) doorState?) 0)
							(not (proc0_2 47))
						)
						((ScriptID 440 2) caller: self open:)
					)
					(else (= cycles 1))
				)
			)
			(2
				(if (proc0_2 47) (oriley posn: 160 250))
				(oriley
					init:
					setCycle: Walk
					setScale: Scaler 155 0 190 90
				)
				(= cycles 3)
			)
			(3
				(cond 
					((proc0_2 47) (self setScript: sKillFromSouth self))
					((== (gEgo view?) 443)
						(gWrapSound number: 3 loop: 1 flags: 1 play:)
						(oriley setPri: -1 setMotion: PolyPath 20 150 self)
					)
					(else (self setScript: sKillFromEast self))
				)
			)
			(4
				(oriley view: 424 cel: 0 setCycle: End self)
			)
			(5
				((ScriptID 440 3) number: 80 flags: 1 play:)
				(gEgo view: 858 setCycle: End self)
			)
			(6
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

(instance sKillFromSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(gEgo setMotion: PolyPath 198 166 self)
			)
			(2
				(gWrapSound number: 3 loop: 1 flags: 1 play:)
				(oriley setMotion: MoveTo 174 174 self)
			)
			(3 (self dispose:))
		)
	)
)

(instance sKillFromEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(= cycles 1)
			)
			(1
				(gEgo setMotion: PolyPath 93 155 self)
			)
			(2
				(gWrapSound number: 3 loop: 1 flags: 1 play:)
				(oriley setMotion: MoveTo 119 153 self)
			)
			(3 (self dispose:))
		)
	)
)

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
				((ScriptID 440 2) locked: 0 open:)
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
