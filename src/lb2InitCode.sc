;;; Sierra Script 1.0 - (do not remove this comment)
(script# 14)
(include sci.sh)
(use Main)
(use n012)
(use Print)
(use Blink)
(use Obj)

(public
	lb2InitCode 0
)

(instance lb2InitCode of Code
	(properties)
	
	(method (init &tmp temp0)
		(= global16 1000)
		(= gFont 69)
		(= global26 2108)
		(= global23 1207)
		(= global118 2407)
		(= global119 4115)
		(= global120 2510)
		(Print font: gFont)
		((= gNarrator Narrator)
			font: gFont
			back: 15
			keepWindow: 1
		)
		; TEXT&SPEECH CHANGE: Set message mode 3 on initialization.
		;
		; lb2Initcode:init is called by LB2:init (in Main, #0) when the game starts and
		; on every restart. ScummVM will overwrite the value we set here for global90,
		; so it's an ideal place to set BOTH (3) as our default message mode. It works
		; on DOS and also honors ScummVM's audio/subtitles settings.
;;;		(= global90 1)
		(= global90 3)
		; END OF TEXT&SPEECH CHANGE
		(= gLb2Win (ScriptID 0 9))
		(= temp0 (FileIO fiOPEN {version} 1))
		(FileIO fiREAD_STRING global27 11 temp0)
		(FileIO fiREAD_STRING global112 20 temp0)
		(FileIO fiREAD_STRING global113 20 temp0)
		(FileIO fiREAD_STRING global114 20 temp0)
		(FileIO fiCLOSE temp0)
		(proc12_0)
		(= global34 1)
		(= gEatTheMice 30)
		(StrCpy @global42 {})
		(gGame setCursor: gCursorNumber 1 304 172 detailLevel: 5)
		(= global94 2)
		(= global106 (DoSound sndGET_POLYPHONY))
		(if
			(and
				(>= (= global105 (Graph grGET_COLOURS)) 2)
				(<= global105 16)
			)
			(proc0_4 0)
		else
			(proc0_3 0)
		)
		(gLb2Win color: 0 back: global176)
		((ScriptID 15 1)
			color: 0
			back: global176
			topBordColor: global176
			lftBordColor: global176
			rgtBordColor: global176
			botBordColor: global176
			insideColor: gLowlightColor
			topBordColor2: global172
			lftBordColor2: global172
			botBordColor2: global173
			rgtBordColor2: global173
		)
		((ScriptID 21 0) doit: 257)
		((ScriptID 21 0) doit: 258)
		((ScriptID 21 0) doit: 259)
		((ScriptID 21 0) doit: 260)
		((ScriptID 21 0) doit: 261)
		((ScriptID 21 0) doit: 262)
		((ScriptID 21 0) doit: 273)
		((ScriptID 21 0) doit: 513)
		((ScriptID 21 0) doit: 514)
		((ScriptID 21 0) doit: 515)
		((ScriptID 21 0) doit: 516)
		((ScriptID 21 0) doit: 517)
		((ScriptID 21 0) doit: 518)
		((ScriptID 21 0) doit: 519)
		((ScriptID 21 0) doit: 771)
		((ScriptID 21 0) doit: 1026)
		((ScriptID 21 0) doit: 1027)
		((ScriptID 21 0) doit: 1028)
		(DisposeScript 21)
		(DisposeScript 12)
	)
)
