;;; Sierra Script 1.0 - (do not remove this comment)
(script# 730)
(include sci.sh)
(use Main)
(use LBRoom)
(use Blink)
(use PFollow)
(use CueObj)
(use ForwardCounter)
(use n958)
(use Sound)
(use Jump)
(use Cycle)
(use InvI)
(use User)
(use View)
(use Obj)

(public
	rm730 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6
	local7
	local8
	local9
)
(instance rm730 of LBRoom
	(properties
		noun 1
		east 740
		south 720
	)
	
	(method (init)
		(global2 obstacles: (List new:))
		((ScriptID 2730 0) doit: (global2 obstacles?))
		(gEgo init: normalize: 732 actions: egoActions)
		(self picture: (if ((Inv at: 15) cel?) 730 else 780))
		(if ((Inv at: 15) cel?)
			(piece1 init:)
			(piece2 init:)
			(piece3 init:)
			(Palette palSET_INTENSITY 0 255 0)
		)
		(proc958_0 132 732)
		(gGame handsOff:)
		(super init:)
		(if (!= (gGameMusic2 number?) 17)
			(gGameMusic2 number: 17 loop: -1 flags: 1 play:)
		)
		(gIconBar disable: 7)
		(steve init:)
		(snake1 init:)
		(snake2 init:)
		(snake3 init:)
		(snake4 init:)
		(snake5 init:)
		(floor init: setOnMeCheck: 1 4)
		(wall init: setOnMeCheck: 1 16)
		(hieroglyphics init: setOnMeCheck: 1 2)
		(if ((Inv at: 15) cel?)
			(= local0 1)
			(self setScript: sEnterSouthLight)
		else
			(self setScript: sEnterDark)
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(if
		(and (== (self picture?) 780) ((Inv at: 15) cel?))
			(= local0 1)
			(Palette palSET_INTENSITY 0 255 0)
			(piece1 init:)
			(piece2 init:)
			(piece3 init:)
			(self picture: 730 drawPic: 730)
			(gGame handsOff:)
			(gIconBar disable: 7)
			(sFX number: 732 flags: 1 loop: -1 play:)
			(gEgo
				view: 732
				setLoop: 0
				posn: 96 161
				cycleSpeed: 4
				moveSpeed: 4
				xStep: 2
				setCycle: Walk
			)
			(steve
				setLoop: 0
				posn: 65 180
				setCycle: Walk
				cycleSpeed: 4
				moveSpeed: 4
				xStep: 2
				setMotion: PFollow gEgo 36
			)
			(snake1
				view: 731
				setLoop: 2
				setCel: 0
				posn: 248 72
				cycleSpeed: 6
				setCycle: Fwd
			)
			(snake2
				view: 731
				setLoop: 2
				setCel: 2
				posn: 252 59
				cycleSpeed: 6
				setCycle: Fwd
			)
			(snake3
				view: 731
				setLoop: 2
				setCel: 0
				posn: 266 62
				cycleSpeed: 6
				setCycle: Fwd
			)
			(snake4
				view: 731
				setLoop: 2
				setCel: 2
				posn: 265 57
				cycleSpeed: 6
				setCycle: Fwd
			)
			(snake5
				view: 731
				setLoop: 2
				setCel: 0
				posn: 276 55
				cycleSpeed: 6
				setCycle: Fwd
			)
			(snake6 cycleSpeed: 6 setCycle: Fwd init:)
			(snake7 cycleSpeed: 6 setCycle: Fwd init:)
			(Load rsVIEW 734)
			(Load rsSOUND 3)
			(= local9 1)
		)
		(if local0
			(Palette palSET_INTENSITY 0 255 (= local2 (+ local2 2)))
			(if (>= local2 100)
				(= local0 0)
				(if (!= (global2 script?) sEnterSouthLight)
					(gGame handsOn:)
					(gIconBar disable: 7)
				)
				(piece1 addToPic:)
				(piece2 addToPic:)
				(piece3 addToPic:)
				(if local9 (self setScript: sLetThereBeLight))
			)
		)
		(if local1
			(Palette
				palSET_INTENSITY
				0
				255
				(proc999_3 0 (= local2 (- local2 3)))
			)
			(if (== local2 0) (= local1 0))
		)
		(cond 
			(script 0)
			((proc0_1 gEgo 8)
				(gGame handsOff:)
				(= local1 1)
				(self setScript: sExitEast)
			)
		)
	)
	
	(method (dispose)
		(proc958_0 0 930 991)
		(DisposeScript 2730)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (== (self picture?) 780)
					(gLb2Messager say: 1 1 1)
				else
					(gLb2Messager say: 1 1 11)
				)
			)
			(4
				(if (== (self picture?) 780)
					(gLb2Messager say: 1 4 1)
				else
					(gLb2Messager say: 1 4 11)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sEnterSouthLight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(steve
					setLoop: 0
					setCycle: Walk
					cycleSpeed: 4
					moveSpeed: 4
					xStep: 2
					setMotion: PFollow gEgo 36
				)
				(gEgo
					setLoop: 0
					posn: -10 240
					edgeHit: 0
					setCycle: Walk
					cycleSpeed: 4
					moveSpeed: 4
					xStep: 2
					setMotion: MoveTo 96 161 self
				)
				(snake1
					view: 731
					setLoop: 2
					setCel: 0
					posn: 248 72
					cycleSpeed: 6
					setCycle: Fwd
				)
				(snake2
					view: 731
					setLoop: 2
					setCel: 2
					posn: 252 59
					cycleSpeed: 6
					setCycle: Fwd
				)
				(snake3
					view: 731
					setLoop: 2
					setCel: 0
					posn: 266 62
					cycleSpeed: 6
					setCycle: Fwd
				)
				(snake4
					view: 731
					setLoop: 2
					setCel: 2
					posn: 265 57
					cycleSpeed: 6
					setCycle: Fwd
				)
				(snake5
					view: 731
					setLoop: 2
					setCel: 0
					posn: 276 55
					cycleSpeed: 6
					setCycle: Fwd
				)
				(snake6 cycleSpeed: 6 setCycle: Fwd init:)
				(snake7 cycleSpeed: 6 setCycle: Fwd init:)
				(Load rsVIEW 734)
				(Load rsSOUND 3)
			)
			(1
				(gGame handsOn:)
				(gIconBar disable: 7)
				(= seconds 5)
			)
			(2
				(snake1
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 58 184
				)
				(snake2
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 45 180
				)
				(snake3
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 59 183
				)
				(snake4
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 58 178
				)
				(snake5
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 69 176
				)
				(snake6
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 72 170
				)
				(snake7
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 73 165
				)
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
				(gEgo setMotion: MoveTo 335 23 self)
			)
			(1
				(global2 newRoom: (global2 east?))
			)
		)
	)
)

(instance sEnterDark of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOn:)
				(User canControl: 0)
				(User canInput: 1)
				(gIconBar disable: 0 3 4 5 7)
				(steve setLoop: 2 posn: 66 148 setCycle: Blink 150)
				(gEgo
					view: 733
					setLoop: 1
					posn: 85 135
					setCycle: Blink 150
				)
				(= seconds 6)
			)
			(1
				(snake1 setCycle: Beg)
				(snake2 setCycle: Beg)
				(snake3 setCycle: Beg)
				(snake4 setCycle: Beg)
				(snake5 setCycle: Beg)
				(= ticks 60)
			)
			(2
				(snake1 setCycle: Blink 200)
				(snake2 setCycle: Blink 200)
				(snake3 setCycle: Blink 200)
				(snake4 setCycle: Blink 200)
				(snake5 setCycle: Blink 200)
				(= seconds 15)
			)
			(3
				(gGame handsOff:)
				(snake1 setLoop: 4 setMotion: MoveTo 110 127)
				(snake2 setLoop: 4 setMotion: MoveTo 123 117)
				(snake3 setLoop: 4 setMotion: MoveTo 137 127)
				(snake4 setLoop: 4 setMotion: MoveTo 134 106)
				(snake5 setLoop: 4 setMotion: MoveTo 152 118 self)
				(= ticks 60)
			)
			(4
				(gGameMusic2 stop:)
				(gWrapSound number: 3 flags: 1 loop: 1 play:)
				(steve
					setLoop: 6
					setCel: 0
					cycleSpeed: 3
					setCycle: ForwardCounter 4
				)
				(gEgo
					setLoop: 5
					setCel: 0
					cycleSpeed: 3
					setCycle: ForwardCounter 4 self
				)
			)
			(5
				(steve setLoop: 2 cycleSpeed: 6 setCycle: Blink 150)
				(gEgo setLoop: 1 cycleSpeed: 6 setCycle: Blink 150)
			)
			(6
				(gEgo setLoop: 1 setCel: 1 setMotion: JumpTo 98 148 self)
			)
			(7
				(gEgo cycleSpeed: 18 setCycle: End self)
			)
			(8
				(snake1 setLoop: 4 setMotion: MoveTo 89 140)
				(snake2 setLoop: 4 setMotion: MoveTo 102 130)
				(snake3 setLoop: 4 setMotion: MoveTo 118 140)
				(snake4 setLoop: 4 setMotion: MoveTo 110 118)
				(snake5 setLoop: 4 setMotion: MoveTo 127 129 self)
			)
			(9
				(steve setLoop: 2 setCel: 1 setMotion: JumpTo 77 163 self)
			)
			(10
				(steve cycleSpeed: 18 setCycle: End self)
			)
			(11 (= ticks 60))
			(12
				(= global145 11)
				(global2 newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance sLetThereBeLight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 7))
			(1
				(snake1
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 58 184
				)
				(snake2
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 45 180
				)
				(snake3
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 59 183
				)
				(snake4
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 58 178
				)
				(snake5
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 69 176
				)
				(snake6
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 72 170
				)
				(snake7
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 73 165
				)
				(self dispose:)
			)
		)
	)
)

(instance sSteveDies of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(steve
					view: 734
					setLoop: 1
					setCel: 0
					cycleSpeed: 12
					ignoreActors: 1
					setCycle: End self
				)
			)
			(1
				(snake1 setCycle: Fwd setMotion: 0)
				(snake2 setCycle: Fwd setMotion: 0)
				(snake3 setCycle: Fwd setMotion: 0)
				(snake4 setCycle: Fwd setMotion: 0)
				(snake5 setCycle: Fwd setMotion: 0)
				(snake6 setCycle: Fwd setMotion: 0)
				(snake7 setCycle: Fwd setMotion: 0)
				(= ticks 120)
			)
			(2
				(= global145 11)
				(global2 newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance sSprinkleOil of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gEgo setLoop: 1 setCel: 0 setCycle: CT 4 1 self)
			)
			(1
				(oil
					init:
					posn: (+ (gEgo x?) 23) (- (gEgo y?) 27)
					setMotion: JumpTo (+ (gEgo x?) 59) (- (gEgo y?) 39) self
				)
				(gEgo setCel: 5)
			)
			(2
				(= local3 1)
				(oil stopUpd:)
				(gEgo
					view: 732
					setLoop: 0
					cycleSpeed: 4
					moveSpeed: 4
					xStep: 2
					setCycle: Walk
				)
				(if local8 (sRepelSnakes cue:))
				(self dispose:)
			)
		)
	)
)

(instance sRepelSnakes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(snake1 setCycle: Fwd setMotion: 0)
				(snake2 setCycle: Fwd setMotion: 0)
				(snake3 setCycle: Fwd setMotion: 0)
				(snake4 setCycle: Fwd setMotion: 0)
				(snake5 setCycle: Fwd setMotion: 0)
				(snake6 setCycle: Fwd setMotion: 0)
				(snake7 setCycle: Fwd setMotion: 0)
				(if local3
					(= cycles 1)
				else
					(= local8 1)
					(gEgo setScript: sSprinkleOil)
				)
			)
			(1 (= ticks 90))
			(2
				(snake1
					setLoop: 1
					posn: (snake1 x?) (- (snake1 y?) 3)
					setCycle: Walk
					setMotion: MoveTo 320 27 self
				)
				(snake2
					setLoop: 1
					posn: (snake2 x?) (- (snake2 y?) 3)
					setCycle: Walk
					setMotion: MoveTo 324 14
				)
				(snake3
					setLoop: 1
					posn: (snake3 x?) (- (snake3 y?) 3)
					setCycle: Walk
					setMotion: MoveTo 338 17
				)
				(snake4
					setLoop: 1
					posn: (snake4 x?) (- (snake4 y?) 3)
					setCycle: Walk
					setMotion: MoveTo 337 12
				)
				(snake5
					setLoop: 1
					posn: (snake5 x?) (- (snake5 y?) 3)
					setCycle: Walk
					setMotion: MoveTo 348 10
				)
				(snake6
					setLoop: 1
					posn: (snake6 x?) (- (snake6 y?) 3)
					setCycle: Walk
					setMotion: MoveTo 351 4
				)
				(snake7
					setLoop: 1
					posn: (snake7 x?) (- (snake7 y?) 3)
					setCycle: Walk
					setMotion: MoveTo 352 -1
				)
			)
			(3
				(snake1 dispose:)
				(snake2 dispose:)
				(snake3 dispose:)
				(snake4 dispose:)
				(snake5 dispose:)
				(snake6 dispose:)
				(snake7 dispose:)
				(sFX stop:)
				(= local4 1)
				(gGame handsOn:)
				(gIconBar disable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sThrowBottle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gEgo setLoop: 1 setCel: 0 setCycle: CT 4 1 self)
			)
			(1
				(oilBottle
					init:
					setCycle: Fwd
					posn: (+ (gEgo x?) 23) (- (gEgo y?) 27)
					setMotion: JumpTo (+ (gEgo x?) 59) (- (gEgo y?) 39) self
				)
				(gEgo setCel: 5)
			)
			(2
				(oilBottle setLoop: 4 setCel: 0 setCycle: End self)
			)
			(3
				(oilBottle setCycle: 0 addToPic:)
				(gEgo
					view: 732
					setLoop: 0
					put: 14
					cycleSpeed: 4
					moveSpeed: 4
					xStep: 2
					setCycle: Walk
				)
				(self dispose:)
			)
		)
	)
)

(instance egoActions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (== (global2 picture?) 780)
					(gLb2Messager say: 3 1 1)
					1
				else
					0
				)
			)
			(25 (gLb2Messager say: 11 25) 1)
			(else  0)
		)
	)
)

(instance steve of Actor
	(properties
		x -30
		y 260
		noun 9
		view 733
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (== (global2 picture?) 780)
					(gLb2Messager say: 4 1 1)
				else
					(gLb2Messager say: 1 1 27 0 0 1887)
				)
			)
			(4
				(if (== (global2 picture?) 780)
					(super doVerb: theVerb)
				else
					(gLb2Messager say: 1 4 27 0 0 1887)
				)
			)
			(8
				(if (== (global2 picture?) 780)
					(super doVerb: theVerb)
				else
					(gLb2Messager say: 1 8 27 0 0 1887)
				)
			)
			(2
				(if local4
					(gLb2Messager say: 9 2 9)
				else
					(switch local7
						(0 (gLb2Messager say: 9 2 5))
						(else 
							(gLb2Messager say: 9 2 6)
						)
					)
					(++ local7)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance snake1 of Actor
	(properties
		x 226
		y 60
		noun 7
		view 733
		loop 4
		cel 3
		signal $4000
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(script)
			((== (global2 script?) sRepelSnakes))
			((and local3 (< (gEgo distanceTo: snake1) 65)) (global2 setScript: sRepelSnakes))
			(
				(and
					(< (self distanceTo: gEgo) 14)
					(== (gEgo view?) 732)
					(== (self view?) 731)
				)
				(gGame handsOff:)
				(gGameMusic2 stop:)
				(gWrapSound number: 3 flags: 1 loop: 1 play:)
				(gEgo
					view: 734
					setLoop: 0
					setCel: 0
					cycleSpeed: 12
					setCycle: End
				)
			)
			(
				(and
					(< (self distanceTo: steve) 14)
					(== (steve view?) 733)
					(== (self view?) 731)
				)
				(global2 setScript: sSteveDies)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (== (global2 picture?) 780)
					(gLb2Messager say: 5 1 1)
				else
					(switch local5
						(0 (gLb2Messager say: 7 1 2))
						(1 (gLb2Messager say: 7 1 3))
						(else 
							(gLb2Messager say: 7 1 4)
						)
					)
					(++ local5)
				)
			)
			(2
				(switch local6
					(0 (gLb2Messager say: 7 2 5))
					(else 
						(gLb2Messager say: 7 2 6)
					)
				)
				(++ local6)
			)
			(25
				(cond 
					((== (global2 picture?) 780) 0)
					((== global150 0) (gEgo setScript: sThrowBottle))
					((< (gEgo distanceTo: snake1) 65) (global2 setScript: sRepelSnakes))
					(else (gGame handsOff:) (gEgo setScript: sSprinkleOil))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance snake2 of Actor
	(properties
		x 240
		y 51
		noun 7
		view 733
		loop 4
		cel 3
		signal $4000
	)
	
	(method (doVerb)
		(snake1 doVerb: &rest)
	)
)

(instance snake3 of Actor
	(properties
		x 256
		y 57
		noun 7
		view 733
		loop 4
		cel 3
		signal $4000
	)
	
	(method (doVerb)
		(snake1 doVerb: &rest)
	)
)

(instance snake4 of Actor
	(properties
		x 248
		y 40
		noun 7
		view 733
		loop 4
		cel 3
		signal $4000
	)
	
	(method (doVerb)
		(snake1 doVerb: &rest)
	)
)

(instance snake5 of Actor
	(properties
		x 271
		y 49
		noun 7
		view 733
		loop 4
		cel 3
		signal $4000
	)
	
	(method (doVerb)
		(snake1 doVerb: &rest)
	)
)

(instance snake6 of Actor
	(properties
		x 279
		y 49
		noun 7
		view 731
		loop 2
		signal $4000
	)
	
	(method (doVerb)
		(snake1 doVerb: &rest)
	)
)

(instance snake7 of Actor
	(properties
		x 280
		y 44
		noun 7
		view 731
		loop 2
		cel 2
		signal $4000
	)
	
	(method (doVerb)
		(snake1 doVerb: &rest)
	)
)

(instance oil of Actor
	(properties
		view 732
		loop 2
		priority 2
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (gLb2Messager say: 10 1 10))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance oilBottle of Actor
	(properties
		view 732
		loop 3
		signal $4000
	)
)

(instance piece1 of View
	(properties
		x 37
		y 126
		view 731
		loop 3
		priority 1
		signal $4011
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance piece2 of View
	(properties
		x 115
		y 74
		view 731
		loop 3
		cel 1
		priority 1
		signal $4011
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance piece3 of View
	(properties
		x 207
		y 46
		view 731
		loop 3
		cel 2
		priority 1
		signal $4011
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance floor of Feature
	(properties
		y 1
		noun 10
	)
	
	(method (doVerb theVerb)
		(asm
			lsp      theVerb
			dup     
			ldi      25
			eq?     
			bnt      code_15c2
			lal      local4
			bnt      code_1553
			ldi      0
			jmp      code_15cb
code_1553:
			lsg      gX
			pushi    #x
			pushi    0
			lag      gEgo
			send     4
			gt?     
			bnt      code_15b1
			jmp      code_1561
code_1561:
			bnt      code_15b1
			lsg      global150
			ldi      0
			eq?     
			bnt      code_157a
			pushi    #setScript
			pushi    1
			lofsa    sThrowBottle
			push    
			lag      gEgo
			send     6
			jmp      code_15cb
code_157a:
			pushi    #distanceTo
			pushi    1
			lofsa    snake1
			push    
			lag      gEgo
			send     6
			push    
			ldi      65
			lt?     
			bnt      code_159b
			pushi    #setScript
			pushi    1
			lofsa    sRepelSnakes
			push    
			lag      global2
			send     6
			jmp      code_15cb
code_159b:
			pushi    #handsOff
			pushi    0
			lag      gGame
			send     4
			pushi    #setScript
			pushi    1
			lofsa    sSprinkleOil
			push    
			lag      gEgo
			send     6
			jmp      code_15cb
code_15b1:
			pushi    #say
			pushi    3
			pushi    10
			pushi    25
			pushi    8
			lag      gLb2Messager
			send     10
			jmp      code_15cb
code_15c2:
			pushi    #doVerb
			pushi    1
			lsp      theVerb
			super    Feature,  6
code_15cb:
			toss    
			ret     
		)
	)
)

(instance hieroglyphics of Feature
	(properties
		y 1
		noun 2
	)
)

(instance wall of Feature
	(properties
		y 1
		noun 6
	)
)

(instance sFX of Sound
	(properties
		flags $0001
	)
)
