;;; Sierra Script 1.0 - (do not remove this comment)
(script# 710)
(include sci.sh)
(use Main)
(use LBRoom)
(use Blink)
(use PolyPath)
(use n958)
(use StopWalk)
(use Sound)
(use Cycle)
(use View)
(use Obj)

(public
	rm710 0
	Rameses_b 2
	Rameses_a 27
)

(instance rm710 of LBRoom
	(properties
		picture 710
		west 700
	)
	
	(method (init)
		(global2 obstacles: (List new:))
		((ScriptID 2710 0) doit: (global2 obstacles?))
		(gEgo
			init:
			normalize: 831
			wearingGown: 1
			scaleSignal: 1
			scaleX: 110
			scaleY: 110
			setPri: 10
			posn: 63 155
		)
		(proc958_0 132 710 712 713 714 715 716)
		(proc958_0 128 710 714 717 716 994)
		(Load rsMESSAGE 710)
		(Load rsFONT 69)
		(super init:)
		(WrapMusic init: 1 1710 1712 1713)
		(wrapMusic2 init: 1 714 715 716)
		(sunnie1 init: setCycle: Fwd)
		(sunnie2 init: setCycle: Fwd)
		(tut init: setCycle: Walk setLoop: 0)
		(rameses init: setCycle: StopWalk -1)
		(bugsWithMeat init: setLoop: 0 setCycle: Walk)
		(ferret init: setLoop: 5 setCycle: Walk)
		(global2 setScript: sEnterRoom)
	)
	
	(method (dispose)
		(DisposeScript 2710)
		(super dispose: &rest)
	)
)

(instance sEnterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gGame handsOff:)
				((ScriptID 22 0) doit: 1)
				(= ticks 180)
			)
			(1
				(gEgo setMotion: PolyPath 96 169 self)
			)
			(2
				(gEgo setPri: -1)
				(gLb2Messager say: 1 0 0 1)
				(= cycles 1)
			)
			(3
				(sunnie1 setCycle: End sunnie1)
				(sunnie2 setCycle: End sunnie2)
			)
			(4)
			(5
				(WrapMusic dispose:)
				(wrapMusic2 dispose: 1)
				(proc958_0 132 636 637)
				(proc958_0 128 635 632)
				(Load rsPIC 716)
				(= ticks 60)
			)
			(6
				(gEgo
					view: 716
					scaleX: 128
					scaleY: 128
					setLoop: 4
					setCel: 0
					cycleSpeed: 12
					setCycle: End self
				)
				(sunnie1 stopUpd:)
				(sunnie2 stopUpd:)
			)
			(7
				(tut setMotion: PolyPath 107 171 tut)
				(= cycles 35)
			)
			(8
				(gLb2Messager say: 1 0 0 2)
				(sFXBeetles play: 0 fade: 127 25 10 0)
				(= cycles 1)
			)
			(9
				(bugsWithMeat setMotion: MoveTo 110 188)
				(= ticks 240)
				(rameses setMotion: PolyPath 76 171 self)
			)
			(10
				(ferret setMotion: MoveTo 133 185)
				(sFXFerret play:)
				(sFXBeetles fade:)
			)
			(11
				(rameses setLoop: 3 setCel: 2 setCycle: CT 6 1 self)
			)
			(12
				(rameses
					view: 716
					setLoop: 0
					setCel: 0
					setCycle: End self
				)
			)
			(13
				(tut stopUpd:)
				(rameses stopUpd:)
				(= ticks 30)
			)
			(14
				(gGameMusic2 number: 711 flags: 1 loop: -1 play:)
				;(gIconBar disable: 7)
				(gOldCast eachElementDo: #hide)
				(global2 drawPic: 716 9 picture: 716)
				(= cycles 1)
			)
			(15 (= ticks 120))
			(16
				(gLb2Messager say: 1 0 1)
				(= cycles 1)
			)
			(17 (= ticks 120))
			(18
				(gOldCast eachElementDo: #show)
				(gEgo dispose:)
				(tut dispose:)
				(rameses dispose:)
				(sunnie1 setCel: 0)
				(sunnie2 setCel: 0)
				(global2 drawPic: 710 9 picture: 716)
				(= ticks 120)
			)
			(19
				(global2 newRoom: 715)
				(self dispose:)
			)
		)
	)
)

(instance tut of Actor
	(properties
		x 335
		y 178
		view 717
		signal $4001
	)
	
	(method (cue)
		(gLb2Messager say: 1 0 0 3)
		(self view: 716 setLoop: 1 setCel: 0 setCycle: End)
	)
)

(instance rameses of Actor
	(properties
		x 335
		y 178
		view 717
		loop 1
		signal $4001
	)
)

(instance bugsWithMeat of Actor
	(properties
		x -18
		y 168
		view 635
		priority 10
		signal $4010
	)
)

(instance ferret of Actor
	(properties
		x -5
		y 171
		view 632
		loop 5
		signal $4000
		xStep 4
	)
)

(instance sunnie1 of Prop
	(properties
		x 305
		y 184
		view 710
		loop 1
		signal $4001
		cycleSpeed 12
	)
	
	(method (cue)
		(self
			view: 716
			setLoop: 2
			setCel: 0
			setCycle: End sEnterRoom
		)
	)
)

(instance sunnie2 of Prop
	(properties
		x 282
		y 174
		view 714
		loop 2
		signal $4001
		cycleSpeed 12
	)
	
	(method (cue)
		(self
			view: 716
			setLoop: 3
			setCel: 0
			setCycle: End sEnterRoom
		)
	)
)

(instance Rameses_a of Talker
	(properties
		x 66
		y 44
		view 1716
		loop 3
		disposeWhenDone 0
		talkWidth 150
		back 15
		textX 20
		textY 60
	)
	
	(method (init)
		(= font gFont)
		(super init: ramesesBust ramesesEyes ramesesMouth &rest)
	)
)

(instance ramesesBust of Prop
	(properties
		view 1716
		loop 1
	)
)

(instance ramesesEyes of Prop
	(properties
		nsTop 20
		nsLeft 12
		view 1716
		loop 2
	)
)

(instance ramesesMouth of Prop
	(properties
		nsTop 31
		nsLeft 19
		view 1716
	)
)

(instance Rameses_b of Talker
	(properties
		x 121
		y 54
		view 1717
		loop 3
		disposeWhenDone 0
		talkWidth 175
		back 15
		textY 50
	)
	
	(method (init)
		(= font gFont)
		(super init: lauraBust lauraEyes lauraMouth &rest)
	)
)

(instance lauraBust of Prop
	(properties
		view 1717
		loop 1
	)
)

(instance lauraEyes of Prop
	(properties
		nsTop 20
		nsLeft 15
		view 1717
		loop 2
	)
)

(instance lauraMouth of Prop
	(properties
		nsTop 26
		nsLeft 15
		view 1717
	)
)

(instance sFXBeetles of Sound
	(properties
		flags $0001
		number 636
		loop -1
	)
)

(instance sFXFerret of Sound
	(properties
		flags $0001
		number 637
	)
)

(instance wrapMusic2 of WrapMusic
	(properties)
	
	(method (init)
		(= wrapSound sWrap)
		(super init: &rest)
	)
)

(instance sWrap of Sound
	(properties)
)
