;;; Sierra Script 1.0 - (do not remove this comment)
(script# 24)
(include sci.sh)
(use Main)
(use Print)
(use Slider)
(use IconI)
(use GameControls)
(use SysWindow)

(public
	lb2GameControls 0
	gcWin 1
)

(instance lb2GameControls of GameControls
	(properties)
	
	(method (init)
		(= gGameControls self)
		(self
			add:
				iconOk
				detailSlider
				(volumeSlider theObj: gGame selector: 391 yourself:)
				(speedSlider theObj: gEgo selector: 325 yourself:)
				textSlider
				(iconSave theObj: gGame selector: 75 yourself:)
				(iconRestore theObj: gGame selector: 76 yourself:)
				(iconRestart theObj: gGame selector: 101 yourself:)
				(iconQuit theObj: gGame selector: 100 yourself:)
				(iconAbout theObj: gGame selector: 636 yourself:)
				(iconMode theObj: gGame selector: 57 yourself:)
				iconHelp
			eachElementDo: #highlightColor global169
			eachElementDo: #lowlightColor global151
			helpIconItem: iconHelp
			curIcon: iconSave
			state: 2048
			window: gcWin
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose:)
		(gGame setCursor: gWalkCursor)
		(DisposeScript 934)
		(DisposeScript 978)
		(DisposeScript 24)
	)
	
	(method (show)
		(gGame setCursor: 999)
		(super show: &rest)
	)
)

(instance gcWin of SysWindow
	(properties)
	
	(method (open &tmp temp0 [temp1 4] temp5 [temp6 20])
		(= type 128)
		(= top (/ (- 200 (+ (CelHigh 995 1 1) 6)) 2))
		(= left (/ (- 320 (+ 191 (CelWide 995 0 1))) 2))
		(= bottom
			(+
				(CelHigh 995 1 1)
				6
				(/ (- 200 (+ (CelHigh 995 1 1) 6)) 2)
			)
		)
		(= right
			(+
				191
				(CelWide 995 0 1)
				(/ (- 320 (+ 191 (CelWide 995 0 1))) 2)
			)
		)
		(= lsLeft (- left 6))
		(= lsTop (- top 6))
		(= lsRight (+ right 6))
		(= lsBottom (+ bottom 6))
		(= priority 15)
		(super open:)
		(= temp0 (GetPort))
		(SetPort 0)
		(Graph grFILL_BOX top left bottom right 3 global151 15)
		(Graph grDRAW_LINE 33 49 33 269 global171 15)
		(Graph grDRAW_LINE 34 50 34 268 gLowlightColor 15)
		(Graph grDRAW_LINE 35 51 35 267 global170 15)
		(Graph grDRAW_LINE 33 49 166 49 global171 15)
		(Graph grDRAW_LINE 34 50 165 50 gLowlightColor 15)
		(Graph grDRAW_LINE 35 51 164 51 global170 15)
		(Graph grDRAW_LINE 166 49 166 269 global171 15)
		(Graph grDRAW_LINE 165 50 165 268 gLowlightColor 15)
		(Graph grDRAW_LINE 164 51 164 267 global170 15)
		(Graph grDRAW_LINE 33 269 166 269 global171 15)
		(Graph grDRAW_LINE 34 268 165 268 gLowlightColor 15)
		(Graph grDRAW_LINE 35 267 164 267 global170 15)
		(DrawCel
			995
			0
			6
			(proc0_11 155 155 178 155 155)
			(proc0_11 42 45 45 45 45)
			temp5
		)
		(DrawCel 995 1 1 56 39 temp5)
		(DrawCel 995 14 0 138 139 temp5)
		(DrawCel 995 13 (== global90 2) 208 145 temp5)
		(DrawCel 995 1 0 146 73 temp5)
		(DrawCel 995 1 0 186 73 temp5)
		(DrawCel 995 1 0 226 73 temp5)
		(DrawCel 995 0 4 116 (proc0_11 58 60 59 60 60) temp5)
		(DrawCel
			995
			0
			3
			(proc0_11 145 154 154 154 154)
			(proc0_11 134 60 59 60 60)
			temp5
		)
		(DrawCel
			995
			0
			2
			(proc0_11 178 198 193 198 198)
			(proc0_11 58 60 59 60 60)
			temp5
		)
		(DrawCel 995 0 5 238 (proc0_11 134 60 59 60 60) temp5)
		(Graph grUPDATE_BOX lsTop lsLeft lsBottom lsRight 1)
		(SetPort temp0)
	)
)

(instance detailSlider of Slider
	(properties
		view 995
		loop 0
		cel 1
		nsLeft 67
		nsTop 35
		signal $0080
		noun 1
		modNum 24
		helpVerb 12
		sliderView 995
		bottomValue 1
		topValue 5
	)
	
	(method (doit param1)
		(if argc (gGame detailLevel: param1))
		(gGame detailLevel:)
	)
)

(instance volumeSlider of Slider
	(properties
		view 995
		loop 0
		cel 1
		nsLeft 107
		nsTop 35
		signal $0080
		noun 2
		modNum 24
		helpVerb 12
		sliderView 995
		topValue 15
	)
)

(instance speedSlider of Slider
	(properties
		view 995
		loop 0
		cel 1
		nsLeft 147
		nsTop 35
		signal $0080
		noun 3
		modNum 24
		helpVerb 12
		sliderView 995
		bottomValue 15
	)
	
	(method (doit param1)
		(if argc (gEgo setSpeed: param1) (= global369 param1))
		(gEgo moveSpeed?)
	)
	
	(method (show)
		(if (not (gUser controls?))
			(= signal 132)
			(= sliderLoop 9)
		else
			(= sliderLoop 0)
			(= signal 128)
		)
		(super show: &rest)
	)
	
	(method (mask)
	)
	
	(method (move)
		(if (gUser controls?) (super move: &rest))
	)
)

(instance textSlider of Slider
	(properties
		view 995
		loop 0
		cel 1
		nsLeft 187
		nsTop 35
		signal $0080
		noun 4
		modNum 24
		helpVerb 12
		sliderView 995
		bottomValue 24
		topValue 1
	)
	
	(method (doit param1)
		(if argc (= global94 param1))
		(return global94)
	)
)

(instance iconSave of ControlIcon
	(properties
		view 995
		loop 2
		cel 0
		nsLeft 8
		nsTop 8
		message 8
		signal $01c3
		noun 5
		modNum 24
		helpVerb 12
	)
)

(instance iconRestore of ControlIcon
	(properties
		view 995
		loop 3
		cel 0
		nsLeft 8
		nsTop 28
		message 8
		signal $01c3
		noun 6
		modNum 24
		helpVerb 12
	)
)

(instance iconRestart of ControlIcon
	(properties
		view 995
		loop 4
		cel 0
		nsLeft 8
		nsTop 48
		message 8
		signal $01c3
		noun 7
		modNum 24
		helpVerb 12
	)
)

(instance iconQuit of ControlIcon
	(properties
		view 995
		loop 5
		cel 0
		nsLeft 8
		nsTop 68
		message 8
		signal $01c3
		noun 8
		modNum 24
		helpVerb 12
	)
)

(instance iconAbout of ControlIcon
	(properties
		view 995
		loop 6
		cel 0
		nsLeft 8
		nsTop 88
		message 8
		signal $01c3
		noun 9
		modNum 24
		helpVerb 12
	)
)

(instance iconHelp of IconI
	(properties
		view 995
		loop 7
		cel 0
		nsLeft 34
		nsTop 88
		cursor 9
		message 12
		signal $0183
		noun 10
		modNum 24
		helpVerb 12
	)
)

(instance iconOk of IconI
	(properties
		view 995
		loop 8
		cel 0
		nsLeft 8
		nsTop 108
		cursor 9
		message 8
		signal $01c3
		noun 11
		modNum 24
		helpVerb 12
	)
)

(instance iconMode of ControlIcon
	(properties
		view 995
		loop 12
		cel 0
		nsLeft 90
		nsTop 108
		message 8
		signal $0183
		noun 14
		modNum 24
		helpVerb 12
	)
	
	(method (doit &tmp temp0)
		(asm
			ldi      1
			bnt      code_054f
			lsg      global90
			dup     
			eq?     
			bnt      code_052b
			ldi      2
			sag      global90
			ldi      1
			sat      temp0
			jmp      code_0539
code_052b:
			dup     
			ldi      2
			eq?     
			bnt      code_0539
			ldi      1
			sag      global90
			ldi      0
			sat      temp0
code_0539:
			toss    
			pushi    6
			pushi    995
			pushi    13
			lst      temp0
			pushi    154
			pushi    110
			pushi    15
			callk    DrawCel,  12
			jmp      code_0563
code_054f:
			pushi    #font
			pushi    1
			lsg      gFont
			pushi    205
			pushi    1
			lofsa    {*** You're not playing a cd!}
			push    
			pushi    110
			pushi    0
			class    Print
			send     16
code_0563:
			pushi    #show
			pushi    0
			self     4
			ret     
		)
	)
)
