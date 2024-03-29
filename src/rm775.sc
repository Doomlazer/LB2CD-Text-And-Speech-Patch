;;; Sierra Script 1.0 - (do not remove this comment)
(script# 775)
(include sci.sh)
(use Main)
(use LBRoom)
(use Inset)
(use Blink)
(use PolyPath)
(use Polygon)
(use n958)
(use View)
(use Obj)

(public
	rm775 0
	Steve 12
)

(local
	local0
	local1 =  100
)
(instance rm775 of LBRoom
	(properties
		picture 120
		style $000a
	)
	
	(method (init)
		(proc958_0 128 123 121 1125 125)
		(proc958_0 132 121 120)
		(gEgo init: normalize: setScale: 167 posn: 240 160)
		(super init:)
		(gIconBar disable:)
		(gGame setCursor: 996)
		(self
			addObstacle:
				((Polygon new:)
					type: 3
					init: 84 154 83 176 183 176 241 168 241 149 199 149 126 149 91 154
					yourself:
				)
		)
		(gWrapSound number: 332 flags: 1 loop: -1 play:)
		(steve init: setScale: 158)
		(global2 setScript: sTalkSteve)
	)
)

(instance sTalkSteve of Script
	(properties)
	
	(method (doit)
		(if (and local0 local1)
			(Palette palSET_INTENSITY 0 255 (-- local1))
			(if (not local1) (self cue:))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				(gEgo setMotion: PolyPath 197 155 self)
				((ScriptID 1881 2) x: 203 y: 17 textX: -180 textY: 0)
			)
			(1 (proc0_5 gEgo steve self))
			(2
				(togetherView init: setPri: 14)
				(= cycles 4)
			)
			(3
				(gLb2Messager say: 1 0 0 0 self)
			)
			(4
				(global2 setInset: inDagger)
				(= seconds 4)
			)
			(5
				(inDagger dispose:)
				(= cycles 2)
			)
			(6
				(gLb2Messager say: 1 0 1 0 self)
			)
			(7
				(gLb2Messager say: 1 0 2 0 self)
				(gWrapSound number: 334 flags: 1 loop: 1 play: self)
			)
			(8 (= local0 1))
			(9 0)
			(10
				(togetherView hide:)
				(= cycles 4)
				(global2 newRoom: 785)
				(self dispose:)
			)
		)
	)
)

(instance steve of Actor
	(properties
		x 184
		y 145
		view 121
		loop 5
	)
)

(instance Steve of Talker
	(properties
		x 78
		y 100
		view 1125
		loop 3
		disposeWhenDone 0
		talkWidth 110
		back 15
		textX 81
		textY -90
	)
	
	(method (init)
		(= font gFont)
		(super init: tSteveBust tSteveEyes tSteveMouth &rest)
	)
)

(instance tSteveMouth of Prop
	(properties
		nsTop 14
		nsLeft 4
		view 1125
	)
)

(instance tSteveEyes of Prop
	(properties
		nsTop 9
		nsLeft 6
		view 1125
		loop 2
	)
)

(instance tSteveBust of View
	(properties
		view 1125
		loop 1
	)
)

(instance togetherView of View
	(properties
		x 25
		y 80
		view 125
	)
)

(instance inDagger of Inset
	(properties
		view 123
		x 95
		y 57
		hideTheCast 1
	)
)
