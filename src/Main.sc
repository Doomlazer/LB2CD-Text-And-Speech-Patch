;;; Sierra Script 1.0 - (do not remove this comment)
;;;
;;; IMPORTANT: Do not compile this file. All our changes to this script file
;;; are actually hex edited in 0.scr because we've been unable to produce a
;;; working patch after compiling Main.sc with SCICompanion. The only change
;;; we've not hex edited is the workaround that swaps the position of the
;;; WrapMusic/Actions classes, and that change isn't included in 0.scr, it's
;;; here to prevent the generation of a problematic Main.sco if we compile
;;; Main.sc by mistake.
(script# 0)
(include sci.sh)
(use LBIconItem)
(use LBRoom)
(use ego)
(use Class_255_0)
(use Print)
(use Messager)
(use Blink)
(use Sync)
(use PseudoMouse)
(use IconI)
(use Osc)
(use PolyPath)
(use Polygon)
(use StopWalk)
(use Timer)
(use Grooper)
(use SysWindow)
(use Sound)
(use Game)
(use InvI)
(use Obj)

(public
	LB2 0
	proc0_1 1
	proc0_2 2
	proc0_3 3
	proc0_4 4
	proc0_5 5
	proc0_6 6
	proc0_7 7
	proc0_8 8
	lb2Win 9
	proc0_10 10
	proc0_11 11
	proc0_12 12
	proc0_13 13
	proc0_14 14
	proc0_15 15
	proc0_16 16
	proc0_17 17
)

(local
	gEgo
	gGame
	global2
	global3
	global4
	gOldCast
	gRegions
	gTimers
	gSounds
	gInv
	gOldATPs
	gNumber
	gGNumber
	gNewRoomNumber
	global14
	global15
	global16
	global17
	gNewSet
	gCursorNumber
	gWalkCursor =  999
	global21 =  997
	gFont =  1
	global23 =  4
	gPEvent
	gDialog
	global26 =  1
	global27
	global28
	global29
	global30
	gPicAngle
	gOldFeatures
	global33
	global34
	global35
	gPicNumber =  -1
	global37
	gLb2Win
	global39
	global40
	global41
	global42
	global43
	global44
	global45
	global46
	global47
	global48
	global49
	global50
	global51
	global52
	global53
	global54
	global55
	global56
	global57
	global58
	global59
	global60
	global61
	global62
	gGameControls
	gLb2FtrInit
	gLb2DoVerbCode
	gLb2ApproachCode
	global67 =  1
	global68
	gIconBar
	gX
	gY
	gLb2KDH
	gLb2MDH
	gLb2DH
	global75
	global76
	gPseudoMouse
	gTheDoits
	gEatTheMice =  60
	gUser
	global81
	gNewSync
	global83
	gNewEventHandler
	gFont_2
	global86
	global87
	gB_moveCnt
	gNarrator
	global90 =  1
	gLb2Messager
	global92
	gLb2WH
	global94 =  2
	gNewList
	global96
	global97
	global98
	global99
	gStopGroop
	global101 =  1234
	gWrapSound
	gGameMusic2
	global104
	global105
	global106
	global107 =  100
	gGUserCanControl
	gGUserCanInput
	global110
	global111
	global112
	global113
	global114
	global115
	global116
	global117
	global118
	global119
	global120
	gGIconBarCurIcon
	gTheNewDButtonValue
	global123
	global124
	global125
	global126
	global127
	global128
	global129
	gLb2Exits
	global131 =  1
	global132 =  1
	global133 =  1
	global134 =  1
	global135 =  1
	global136
	global137
	global138
	global139
	global140
	global141
	global142
	global143
	global144
	global145
	global146
	global147
	global148
	global149
	global150 =  4
	global151
	global152
	global153
	global154
	global155
	global156
	global157
	global158
	global159
	global160
	global161
	global162
	global163
	global164
	global165
	global166
	global167
	gLowlightColor
	global169
	global170
	global171
	global172
	global173
	global174
	global175
	global176
	gLowlightColor_2
	global178
	global179
	global180
	global181
	global182
	global183
	global184
	global185
	global186
	global187
	global188
	global189
	global190
	global191
	global192
	global193
	global194
	global195
	global196
	global197
	global198
	global199
	global200
	global201
	global202
	global203
	global204
	global205
	global206
	global207
	global208
	global209
	global210
	global211
	global212
	global213
	global214
	global215
	global216
	global217
	global218
	global219
	global220
	global221
	global222
	global223
	global224
	global225
	global226
	global227
	global228
	global229
	global230
	global231
	global232
	global233
	global234
	global235
	global236
	global237
	global238
	global239
	global240
	global241
	global242
	global243
	global244
	global245
	global246
	global247
	global248
	global249
	global250
	global251
	global252
	global253
	global254
	global255
	global256
	global257
	global258
	global259
	global260
	global261
	global262
	global263
	global264
	global265
	global266
	global267
	global268
	global269
	global270
	global271
	global272
	global273
	global274
	global275
	global276
	global277
	global278
	global279
	global280
	global281
	global282
	global283
	global284
	global285
	global286
	global287
	global288
	global289
	global290
	global291
	global292
	global293
	global294
	global295
	global296
	global297
	global298
	global299
	global300
	global301
	global302
	global303
	global304
	global305
	global306
	global307
	global308
	global309
	global310
	global311
	global312
	global313
	global314
	global315
	global316
	global317
	global318
	global319
	global320
	global321
	global322
	global323
	global324
	global325
	global326
	global327
	global328
	global329
	global330
	global331
	global332
	global333
	global334
	global335
	global336
	global337
	global338
	global339
	global340
	global341
	global342
	global343
	global344
	global345
	global346
	global347
	global348
	global349
	global350
	global351
	global352
	global353
	global354
	global355
	global356
	global357
	global358
	global359
	global360
	global361
	global362
	global363
	global364
	global365
	global366
	global367
	global368
	global369 =  6
	global370
	global371
	global372
	global373
	global374
	global375
	global376
	global377
	global378
	global379
	global380
	global381
	global382
	global383
	global384
	global385
	global386
	global387
	global388
	global389
	global390
	global391
	global392
	global393
	global394
	global395
	global396
	global397
	global398
	global399
)
(procedure (proc0_1 param1 param2)
	(return (if (& (param1 onControl: 1) param2) (return 1) else 0))
)

(procedure (proc0_2 param1)
	(return
		(&
			[global186 (/ param1 16)]
			(>> $8000 (mod param1 16))
		)
	)
)

(procedure (proc0_3 param1 &tmp temp0)
	(= temp0 (proc0_2 param1))
	(= [global186 (/ param1 16)]
		(|
			[global186 (/ param1 16)]
			(>> $8000 (mod param1 16))
		)
	)
	(return temp0)
)

(procedure (proc0_4 param1 &tmp temp0)
	(= temp0 (proc0_2 param1))
	(= [global186 (/ param1 16)]
		(&
			[global186 (/ param1 16)]
			(~ (>> $8000 (mod param1 16)))
		)
	)
	(return temp0)
)

(procedure (proc0_5 param1 param2 param3 param4 &tmp temp0 temp1 temp2 temp3)
	(= temp3 0)
	(if (IsObject param2)
		(= temp1 (param2 x?))
		(= temp2 (param2 y?))
		(if (== argc 3) (= temp3 param3))
	else
		(= temp1 param2)
		(= temp2 param3)
		(if (== argc 4) (= temp3 param4))
	)
	(= temp0
		(GetAngle (param1 x?) (param1 y?) temp1 temp2)
	)
	(param1
		setHeading: temp0 (if (IsObject temp3) temp3 else 0)
	)
)

(procedure (proc0_6 param1 param2)
	(cond 
		((proc999_5 param2 4 3 38) (gLb2Messager say: 0 param2 0 0 0 0))
		((proc999_5 param2 6 1 8) (gLb2Messager say: 0 param2 0 (Random 1 2) 0 0))
		((== param2 2) (gLb2Messager say: 0 param2 0 (Random 1 4) 0 0))
		(else (gLb2Messager say: 0 47 0 0 0 0))
	)
)

(procedure (proc0_7 &tmp temp0)
	(gUser
		canControl: gGUserCanControl
		canInput: gGUserCanInput
	)
	(= temp0 0)
	(while (< temp0 8)
		(if (& global116 (>> $8000 temp0))
			(gIconBar disable: temp0)
		)
		(++ temp0)
	)
)

(procedure (proc0_8 param1)
	(if param1
		(gIconBar
			delete: icon0
			addToFront:
				(icon10
					init:
					highlightColor: global157
					lowlightColor: gLowlightColor_2
					yourself:
				)
		)
		(if (== (gIconBar curIcon?) icon0)
			(gIconBar curIcon: icon10 walkIconItem: 0)
			(gGame setCursor: (icon10 cursor?))
		)
	else
		(gIconBar
			delete: icon10
			addToFront: (icon0 init: yourself:)
		)
		(if (== (gIconBar curIcon?) icon10)
			(gIconBar curIcon: icon0 walkIconItem: icon0)
			(gGame setCursor: (icon0 cursor?))
		)
	)
)

(procedure (proc0_10 param1 param2)
	(= param1 (& param1 $00ff))
	(return
		(if (and (> argc 1) param2)
			(== (- param1 1) (& global124 (- param1 1)))
		else
			(& global124 param1)
		)
	)
)

(procedure (proc0_11 param1 param2 param3 param4 param5)
	(switch (gGame printLang?)
		(49 param1)
		(34 param2)
		(33 param3)
		(39 param4)
		(else  param5)
	)
)

(procedure (proc0_12)
	(proc0_11 1026 1040 1051 1050 995)
)

(procedure (proc0_13)
	(proc0_3 7)
	(proc0_3 8)
	(proc0_3 9)
	(proc0_3 24)
	(proc0_3 26)
	(proc0_3 27)
	(proc0_3 28)
	(proc0_3 29)
	(proc0_3 34)
	(proc0_3 43)
	((ScriptID 21 0) doit: 791)
	((ScriptID 21 0) doit: 263)
	((ScriptID 21 0) doit: 264)
	((ScriptID 21 0) doit: 265)
	((ScriptID 21 0) doit: 266)
	((ScriptID 21 0) doit: 267)
	((ScriptID 21 0) doit: 268)
	((ScriptID 21 0) doit: 269)
	((ScriptID 21 0) doit: 270)
	((ScriptID 21 0) doit: 271)
	((ScriptID 21 0) doit: 272)
	((ScriptID 21 1) doit: 518)
	((ScriptID 21 0) doit: 520)
	(ego wearingGown: 1)
	(ego get: -1 22)
	(ego get: -1 6)
)

(procedure (proc0_14)
	(proc0_13)
	(proc0_3 1)
	(proc0_3 25)
	(proc0_3 23)
	((ScriptID 21 0) doit: 797)
	(ego get: -1 28)
	(ego get: -1 21)
)

(procedure (proc0_15)
	(proc0_14)
	(= global129 13)
	(proc0_3 2)
	(proc0_3 3)
	(proc0_3 4)
	(proc0_3 22)
	(proc0_3 31)
	(proc0_3 33)
	(proc0_3 35)
	(proc0_3 36)
	(proc0_3 37)
	(proc0_3 40)
	(proc0_3 42)
	(proc0_3 49)
	((ScriptID 21 0) doit: 790)
	((ScriptID 21 0) doit: 789)
	((ScriptID 21 0) doit: 787)
	((ScriptID 21 0) doit: 783)
	((ScriptID 21 0) doit: 788)
	((ScriptID 21 0) doit: 798)
	((ScriptID 21 0) doit: 802)
	((ScriptID 21 0) doit: 779)
	((ScriptID 21 0) doit: 777)
	((ScriptID 21 0) doit: 776)
	((ScriptID 21 0) doit: 778)
	((ScriptID 21 0) doit: 1025)
	((ScriptID 21 0) doit: 1030)
	(ego get: -1 20 18 14 19 29 33 10 8 7 9 11)
)

(procedure (proc0_16 &tmp temp0)
	(proc0_15)
	(proc0_3 72)
	(proc0_3 5)
	(proc0_3 6)
	(proc0_3 62)
	((ScriptID 21 0) doit: 794)
	((ScriptID 21 0) doit: 785)
	((ScriptID 21 0) doit: 786)
	((ScriptID 21 0) doit: 799)
	((ScriptID 21 0) doit: 796)
	((ScriptID 21 0) doit: 795)
	((ScriptID 21 0) doit: 781)
	((ScriptID 21 0) doit: 800)
	((ScriptID 21 0) doit: 782)
	((ScriptID 21 0) doit: 780)
	(ego put: 6 0 1 3 4 5 8 9 18 23 32)
	((ScriptID 21 1) doit: 775)
	((ScriptID 21 1) doit: 769)
	((ScriptID 21 1) doit: 770)
	((ScriptID 21 1) doit: 772)
	((ScriptID 21 1) doit: 773)
	((ScriptID 21 1) doit: 774)
	((ScriptID 21 1) doit: 777)
	((ScriptID 21 1) doit: 778)
	((ScriptID 21 1) doit: 787)
	((ScriptID 21 1) doit: 792)
	((ScriptID 21 1) doit: 801)
	(= temp0 1)
	(while (< temp0 27)
		((ScriptID 21 0) doit: (+ temp0 1088))
		(++ temp0)
	)
	(ego get: -1 25 16 17 30 27 26 12 31 13)
)

(procedure (proc0_17)
	(proc0_16)
	(proc0_3 10)
	((ScriptID 21 0) doit: 784)
	((ScriptID 21 0) doit: 803)
	(ego get: -1 34 15)
)

(instance gameMusic1 of Sound
	(properties)
)

(instance gameMusic2 of Sound
	(properties)
)

; WORKAROUND: Prevent WrapMusic issues after recompilation.
;
; SCICompanion compiles the LB2 Main script with the class value 0x2E for
; WrapMusic, which breaks calls from other recompiled scripts reliant on
; WrapMusic. Changing the order that these classes appear in Main assigns the
; expected 0x86 value to WrapMusic in the Main.sco file which, in turn passes
; the correct value to other scripts that require 'WrapMusic' at compile time.
; We still can't compile 0.scr to produce a working Main script patch, but
; other scripts can now be successfully compiled without the need for hex
; editing the patch files.
(class Actions of Code ; Actions class moved before WrapMusic.
	(properties)
	
	(method (doVerb)
		(return 0)
	)
)
; END OF WORKAROUND (continued right after the WrapMusic class)

(class WrapMusic of List
	(properties
		elements 0
		size 0
		wrapSound 0
		currentSound 0
		loopIt 0
		vol 127
		paused 0
	)
	
	(method (init theLoopIt)
		(Sounds eachElementDo: #check)
		(if (not wrapSound) (= wrapSound gWrapSound))
		(= loopIt theLoopIt)
		(= currentSound 0)
		(self add: &rest cue:)
	)
	
	(method (dispose param1)
		(wrapSound client: 0)
		(if (and argc param1)
			(super dispose:)
		else
			(self release:)
		)
	)
	
	(method (cue &tmp temp0 temp1 temp2)
		(cond 
			((proc999_5 (wrapSound prevSignal?) -1 0)
				(= temp0 1)
				(cond 
					(
					(and (== loopIt -1) (== currentSound (- size 1))) (= temp0 -1))
					((== currentSound size)
						(switch loopIt
							(1 (= currentSound 0))
							(else 
								(self release: dispose:)
								(return)
							)
						)
					)
				)
				(if (> (= temp1 (self at: currentSound)) 1000)
					(= temp1 (- temp1 1000))
					(= temp2 1)
				else
					(= temp2 0)
				)
				(wrapSound
					number: temp1
					setLoop: temp0
					flags: (if temp2 1 else 1)
					play: vol self
				)
				(++ currentSound)
			)
			(paused (wrapSound pause:))
			; BUGFIX: Prevent music being permanently lowered down by clock chimes.
			;
			; WrapMusic plays the music in most museum rooms. The volume it should have
			; after unpausing is stored in its vol property, and determines the maximum
			; volume of the gameMusic1 object. WrapMusic:cue updates vol with the value
			; of gameMusic1's vol property, and if it's called when gameMusic1's vol
			; isn't at maximum volume (127), WrapMusic's volume will be permanently
			; lowered (none of the scripts raise it). This can happen during the clock
			; chimes in certain situations depending on fades, the game's speed and the
			; system clock's value.
			;
			; Fixed by making WrapMusic:cue always store 127 in the vol property.
			; Ported from: https://github.com/scummvm/scummvm/blob/85702e06764f95a6b700e348dd90931613efdc29/engines/sci/engine/script_patches.cpp#L12356
;;;			(else (= vol (wrapSound vol?)))
			(else (= vol 127)) ; always be 127
			; END OF BUGFIX
		)
	)
	
	(method (pause param1)
		(if (IsObject wrapSound)
			(if (and argc (not param1))
				(= paused 0)
				(wrapSound pause: 0 fade: vol 5 5 0)
			else
				(= paused 1)
				(wrapSound fade: 0 5 5 0)
			)
		)
	)
	
	; WORKAROUND: Prevent WrapMusic issues after recompilation (continued).
;;;	(class Actions of Code ; We don't want Actions to be after WrapMusic
;;;		(properties)
;;;
;;;		(method (doVerb)
;;;			(return 0)
;;;		)
;;;	)
	; END OF WORKAROUND (see also right before the WrapMusic class)
)

(instance stopGroop of Grooper
	(properties)
)

(instance walkCursor of Cursor
	(properties)
)

(instance lookCursor of Cursor
	(properties
		view 1
	)
)

(instance doCursor of Cursor
	(properties
		view 2
	)
)

(instance talkCursor of Cursor
	(properties
		view 3
	)
)

(instance askCursor of Cursor
	(properties
		view 4
	)
)

(instance exitCursor of Cursor
	(properties
		view 6
	)
)

(instance lb2KDH of EventHandler
	(properties)
)

(instance lb2MDH of EventHandler
	(properties)
)

(instance lb2DH of EventHandler
	(properties)
)

(instance lb2WH of EventHandler
	(properties)
)

(instance lb2Exits of EventHandler
	(properties)
)

(class LB2 of Game
	(properties
		script 0
		printLang 0
		_detailLevel 3
		panelObj 0
		panelSelector 0
		handsOffCode 0
		handsOnCode 0
	)
	
	(method (init &tmp [temp0 22])
		Dialog
		Sync
		Print
		StopWalk
		Polygon
		PolyPath
		Timer
		LBRoom
		ego
		IconBar
		Inv
		LBIconItem
		(ScriptID 982)
		Narrator
		Osc
		(super init:)
		(= global27 {x.yyy.zzz})
		(= global112 {800-326-6654})
		(= global113 {0734-303171})
		(= global114 {900-370-5583})
		((ScriptID 14 0) init:) ; lb2Initcode:init
		(DisposeScript 14)
		(DoAudio 7 22050)
		; TEXT&SPEECH CHANGE: Don't let LB2:init change global90's value.
		;
		; We want to keep global90's value set by lb2Initcode:init (#14). Setting a new
		; value here is unnecessary and conflicts with ScummVM's audio/subtitles settings.
		; Ported from: https://github.com/scummvm/scummvm/blob/85702e06764f95a6b700e348dd90931613efdc29/engines/sci/engine/script_patches.cpp#L12472
;;;		(= global90 2)
		; END OF TEXT&SPEECH CHANGE
		((ScriptID 15 0) init:)
		(= gWalkCursor walkCursor)
		(= gLb2DoVerbCode lb2DoVerbCode)
		(= gLb2FtrInit lb2FtrInit)
		(= gLb2ApproachCode lb2ApproachCode)
		(= gStopGroop stopGroop)
		(= gLb2Messager lb2Messager)
		((= gLb2KDH lb2KDH) add:)
		((= gLb2MDH lb2MDH) add:)
		((= gLb2DH lb2DH) add:)
		((= gLb2WH lb2WH) add:)
		((= gLb2Exits lb2Exits) add:)
		((= gNewList (List new:)) name: {altPolys} add:)
		(gLb2MDH addToFront: lb2Exits)
		(gLb2KDH addToFront: lb2Exits)
		(= gPseudoMouse PseudoMouse)
		(WrapMusic add:)
		((= gWrapSound gameMusic1) owner: self flags: 1 init:)
		((= gGameMusic2 gameMusic2) owner: self flags: 1 init:)
		((= gIconBar IconBar)
			add: icon0 icon1 icon2 icon3 icon4 icon6 icon7 icon8 icon9
			eachElementDo: #init
			eachElementDo: #highlightColor global157
			eachElementDo: #lowlightColor gLowlightColor_2
			curIcon: icon0
			useIconItem: icon6
			helpIconItem: icon9
			walkIconItem: icon0
			disable: 5
			disable:
			state: 3072
		)
		(if (GameIsRestarting)
			(MemorySegment 1 @global107)
		else
			(= global107 28)
		)
		(if (FileIO fiEXISTS {10.scr})
			(= global110 1)
		else
			(= global110 0)
		)
		(gIconBar enable:)
		(= gEgo ego)
		(gUser alterEgo: gEgo canControl: 0 canInput: 0)
		(self newRoom: global107)
	)
	
	(method (doit &tmp thePanelObj thePanelSelector)
		(if
			(and
				(gLb2Exits size?)
				(== (gIconBar curIcon?) (gIconBar walkIconItem?))
			)
			(gLb2Exits eachElementDo: #doit)
		)
		(if panelObj
			(= thePanelObj panelObj)
			(= thePanelSelector panelSelector)
			(= panelObj (= panelSelector 0))
			(proc999_7 thePanelObj thePanelSelector)
		)
		(super doit:)
	)
	
	(method (replay &tmp temp0)
		(= gLb2Win lb2Win)
		(= gWalkCursor walkCursor)
		(if
		(and (proc999_5 gNumber 330 335) (== global123 2))
			(Palette palSET_INTENSITY 0 255 60)
		else
			(Palette palSET_INTENSITY 0 255 100)
		)
		(super replay: &rest)
	)
	
	(method (newRoom newRoomNumber)
		(gGame setCursor: global21)
		(gPseudoMouse stop:)
		(if gDialog (gDialog dispose:))
		(if
			(and
				(IsObject gNewEventHandler)
				(gNewEventHandler elements?)
			)
			(gNewEventHandler eachElementDo: #dispose 1)
		)
		(if (gLb2Exits size?)
			(gLb2Exits eachElementDo: #dispose)
		)
		(gNarrator
			x: -1
			y: -1
			disposeWhenDone: 1
			talkWidth: 0
			keepWindow: 1
			modeless: 0
			showTitle: 0
			name: {Narrator}
		)
		(gIconBar disable:)
		(super newRoom: newRoomNumber)
	)
	
	(method (startRoom param1 &tmp temp0 temp1 [temp2 2])
		((ScriptID 11) doit: param1)
		(= temp1 0)
		(while (< temp1 (gTimers size?))
			(gTimers delete: (= temp0 (gTimers at: 0)))
			(gTimers add: temp0)
			(++ temp1)
		)
		(= temp1 0)
		(while (< temp1 (WrapMusic size?))
			(WrapMusic delete: (= temp0 (WrapMusic at: 0)))
			(WrapMusic add: temp0)
			(++ temp1)
		)
		(if
			(and
				(proc999_5
					param1
					335
					340
					350
					355
					360
					370
					400
					420
					500
					510
					520
					525
					530
					540
					550
					560
					565
					430
					435
					440
					448
					450
					454
					455
					456
					460
					480
					490
					521
					600
					610
					620
					630
					640
					650
					666
					660
					700
					710
					715
					720
					730
					740
				)
				(!= global123 5)
			)
			(ScriptID 90)
		)
		(if
			(and
				(proc999_5 param1 335 340 350 355 360 370 400)
				(== global123 2)
			)
			(ScriptID 93)
		)
		(if (proc999_5 param1 280 210 260 300) (ScriptID 91))
		(if
			(and
				(== global123 5)
				(proc999_5
					param1
					420
					430
					435
					440
					448
					450
					454
					460
					480
					490
					660
				)
			)
			(ScriptID 94)
		)
		(if
			(proc999_5
				param1
				100
				105
				110
				120
				140
				150
				155
				160
				180
				190
				220
			)
			(ScriptID 92)
		)
		(if (and global110 (not (proc999_5 param1 100)))
			((ScriptID 10 0) init:)
		)
		(gIconBar enable:)
		(super startRoom: param1)
		(if
			(and
				(gEgo cycler?)
				(not (gEgo looper?))
				((gEgo cycler?) isKindOf: StopWalk)
			)
			(gEgo setLoop: stopGroop)
		)
		(if (== (gIconBar curIcon?) (gIconBar at: 5))
			(gIconBar curIcon: (gIconBar at: 0))
		)
	)
	
	(method (restart)
		(global2 style: 6 drawPic: 780)
		(gOldCast eachElementDo: #hide)
		(Animate (gOldCast elements?) 0)
		(MemorySegment 0 @global107 2)
		(super restart:)
	)
	
	(method (restore &tmp theGLb2Win theGWalkCursor temp2 newEventHandler)
		(= newEventHandler (EventHandler new:))
		(= temp2 0)
		(while (< temp2 (gOldATPs size?))
			(newEventHandler add: (gOldATPs at: temp2))
			(++ temp2)
		)
		(DrawPic 780 dpOPEN_CENTEREDGE)
		(gOldCast eachElementDo: #hide)
		(Animate 0)
		(= theGWalkCursor gWalkCursor)
		(= gWalkCursor 999)
		(= theGLb2Win gLb2Win)
		(= gLb2Win SysWindow)
		(super restore: &rest)
		(DrawPic (global2 picture?) dpOPEN_NO_TRANSITION)
		(gOldCast eachElementDo: #show)
		(= temp2 0)
		(while (< temp2 (newEventHandler size?))
			(gOldATPs add: (newEventHandler at: temp2))
			(++ temp2)
		)
		(newEventHandler release: dispose:)
		(gOldATPs doit:)
		(Animate (gOldCast elements?) 0)
		(= gLb2Win theGLb2Win)
		(= gWalkCursor theGWalkCursor)
		(if
		(== (= temp2 ((gIconBar curIcon?) cursor?)) 999)
			(gGame setCursor: global21)
		else
			(gGame setCursor: temp2)
		)
	)
	
	(method (save &tmp theGLb2Win theGWalkCursor temp2)
		(= theGWalkCursor gWalkCursor)
		(= gWalkCursor 999)
		(= theGLb2Win gLb2Win)
		(= gLb2Win SysWindow)
		(super save: &rest)
		(= gLb2Win theGLb2Win)
		(= gWalkCursor theGWalkCursor)
		(if
		(== (= temp2 ((gIconBar curIcon?) cursor?)) 999)
			(gGame setCursor: global21)
		else
			(gGame setCursor: temp2)
		)
	)
	
	(method (handleEvent pEvent)
		(if (pEvent claimed?) (return 1))
		(return
			(switch (pEvent type?)
				(evKEYBOARD
					(switch (pEvent message?)
						(KEY_TAB
							(if (not (& ((gIconBar at: 6) signal?) $0004))
								(if gNewEventHandler (return gNewEventHandler))
								(gEgo showInv:)
								(pEvent claimed: 1)
							)
						)
						(KEY_SHIFTTAB
							(if (not (& ((gIconBar at: 6) signal?) $0004))
								(if gNewEventHandler (return gNewEventHandler))
								(gEgo showInv:)
								(pEvent claimed: 1)
							)
						)
						(KEY_CONTROL
							(gGame quitGame:)
							(pEvent claimed: 1)
						)
						(JOY_RIGHT
							(if (not (& ((gIconBar at: 7) signal?) $0004))
								(gGame showControls:)
							)
						)
						(KEY_F2
							(cond 
								((gGame masterVolume:) (gGame masterVolume: 0))
								((> global106 1) (gGame masterVolume: 15))
								(else (gGame masterVolume: 1))
							)
							(pEvent claimed: 1)
						)
						(KEY_F5
							(if (not (& ((gIconBar at: 7) signal?) $0004))
								(if gNewEventHandler (return gNewEventHandler))
								(gGame save:)
								(pEvent claimed: 1)
							)
						)
						(KEY_F7
							(if (not (& ((gIconBar at: 7) signal?) $0004))
								(if gNewEventHandler (return gNewEventHandler))
								(gGame restore:)
								(pEvent claimed: 1)
							)
						)
						(KEY_EXECUTE
							(if (gUser controls?)
								(= global369 (proc999_3 0 (-- global369)))
								(gEgo setSpeed: global369)
							)
						)
						(KEY_SUBTRACT
							(if (gUser controls?)
								(++ global369)
								(gEgo setSpeed: global369)
							)
						)
						(61
							(if (gUser controls?) (gEgo setSpeed: 6))
						)
					)
				)
			)
		)
	)
	
	(method (setCursor cursorNumber param2 param3 param4 &tmp theGCursorNumber)
		(= theGCursorNumber gCursorNumber)
		(if argc
			(if (IsObject cursorNumber)
				((= gCursorNumber cursorNumber) init:)
			else
				(SetCursor (= gCursorNumber cursorNumber) 0 0)
			)
		)
		(if (and (> argc 1) (not param2)) (SetCursor 996 0 0))
		(if (> argc 2)
			(if (< param3 0) (= param3 0))
			(if (< param4 0) (= param4 0))
			(SetCursor param3 param4)
		)
		(return theGCursorNumber)
	)
	
	(method (quitGame)
		(if
			(Print
				addText: 12 0 0 1 0 0 0
				addIcon: 992 0 0 0 25
				addButton: 1 12 0 8 1 140 67 0
				addButton: 0 12 0 9 1 140 87 0
				saveCursor: 1
				init:
			)
			(super quitGame: 1)
		)
	)
	
	(method (pragmaFail)
		(if gDialog (gDialog dispose:))
		(if (gUser canInput:)
			(gLb2Messager say: 0 ((gUser curEvent?) message?))
		)
	)
	
	(method (handsOff)
		(if (not gGIconBarCurIcon)
			(= gGIconBarCurIcon (gIconBar curIcon?))
		)
		(= gGUserCanControl (gUser canControl:))
		(= gGUserCanInput (gUser canInput:))
		(gUser canControl: 0 canInput: 0)
		(gEgo setMotion: 0)
		(= global116 0)
		(gIconBar eachElementDo: #perform checkIcon)
		(gIconBar curIcon: (gIconBar at: 7))
		(gIconBar disable: 0 1 2 3 4 5 6)
		(if (not (HaveMouse))
			(gGame setCursor: 996)
		else
			(gGame setCursor: global21)
		)
	)
	
	(method (handsOn param1)
		(gUser canControl: 1 canInput: 1)
		(gIconBar enable: 0 1 2 3 4 5 6)
		(if (not (global2 inset:)) (gIconBar enable: 7))
		; IMPROVEMENT: Remove base control panel restrictions.
		;
		; This CD version of the game disables access to the control panel in many rooms
		; where the floppy version allows it. That also disables it in rooms where it
		; wasn't even intended, since handsOff/handsOn save and restore the state of
		; IconBar, passing its disabled state to other rooms that don't explicitly
		; re-enable it. These restrictions don't allow the player to load/save the game
		; or change any of the game's settings. The reasons are unclear, but we've not
		; experienced any problems after restoring the behavior of the floppy version.
		;
		; The base restrictions are a list of 14 rooms here in LB2:handsOn. Some of these
		; rooms and additional ones also have calls to "(gIconBar disable: 7)" that need
		; to be re-enabled per room. Lastly, they removed the code that re-enables access
		; to the control panel when an inset is disposed (on Inset:dispose, #923), which
		; has the side effect of disabling the control panel in ANY room after an inset
		; has been disposed (the control panel is always disabled when an inset is set).
		;
		; In this script file we remove the base control panel restrictions.
		; Ported from: https://github.com/scummvm/scummvm/blob/85702e06764f95a6b700e348dd90931613efdc29/engines/sci/engine/script_patches.cpp#L11175
;;;		(if
;;;			(proc999_5
;;;				gNumber
;;;				310
;;;				420
;;;				454
;;;				500
;;;				520
;;;				525
;;;				550
;;;				560
;;;				620
;;;				630
;;;				640
;;;				700
;;;				730
;;;				740
;;;			)
;;;			(gIconBar disable: 7)
;;;		)
		; END OF IMPROVEMENT (see also Inset:dispose in #923 and the script files of
		; rooms 310, 441, 454, 520, 550, 610, 700, and 710)
		
		; BUGFIX: Prevent displaying hands-off cursor when the player has control.
		;
		; When speech is enabled, the game often shows the hands-off cursor when the
		; player has control. The Narrator records and restores the cursor being used,
		; and if handsOn is called after say, the Narrator restores the hands-off cursor
		; when the speech completes. Calling handsOn before say doesn't cause the problem,
		; but many scripts call these in the wrong order.
		;
		; This fix makes handsOn detect if a Narrator is saying a message, updating its
		; saveCursor to the correct cursor when that's the case.
		; Ported from: https://github.com/scummvm/scummvm/blob/85702e06764f95a6b700e348dd90931613efdc29/engines/sci/engine/script_patches.cpp#L11279
		(if (and gNewEventHandler (gNewEventHandler at: 0))
			(= global97 (gNewEventHandler at: 0))
			(if gGIconBarCurIcon
				(global97 saveCursor: (gGIconBarCurIcon cursor?))
			)
		)
		; END OF BUGFIX
		(if (and argc param1) (proc0_7))
		(if (not (gIconBar curInvIcon?)) (gIconBar disable: 5))
		(if
			(and
				gGIconBarCurIcon
				(or
					(!= gGIconBarCurIcon icon10)
					(== (gIconBar at: 0) icon10)
				)
			)
			(gIconBar curIcon: gGIconBarCurIcon)
			(gGame setCursor: (gGIconBarCurIcon cursor?))
			(if
				(and
					(== (gIconBar curIcon?) (gIconBar at: 5))
					(not (gIconBar curInvIcon?))
				)
				(gIconBar advanceCurIcon:)
			)
		)
		(= gGIconBarCurIcon 0)
		(gGame setCursor: ((gIconBar curIcon?) cursor?) 1)
	)
	
	(method (points param1 param2)
		(if (and (> argc 1) (proc0_3 param2)) (= param1 0))
		(if param1 (gGame changeScore: param1))
	)
	
	(method (showControls &tmp temp0)
		((ScriptID 24 0) init: show: dispose:)
	)
	
	(method (showAbout)
		(if
			(or
				(== global123 2)
				(proc999_5
					gNumber
					435
					440
					450
					454
					455
					520
					521
					525
					550
					560
					565
					620
					630
					650
					700
					710
					715
					720
				)
			)
			(gLb2Messager say: 11 0 0 0 0 0)
		else
			((ScriptID 13 0) doit:)
		)
	)
)

(instance icon0 of IconI
	(properties
		view 990
		loop 0
		cel 0
		type $5000
		message 3
		signal $0041
		maskView 990
		maskLoop 9
		noun 1
		helpVerb 12
	)
	
	(method (init)
		(= cursor walkCursor)
		(super init:)
	)
	
	(method (select &tmp temp0)
		(return
			(if (super select: &rest)
				(gIconBar hide:)
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance icon1 of IconI
	(properties
		view 990
		loop 1
		cel 0
		message 1
		signal $0041
		maskView 990
		maskLoop 9
		noun 2
		helpVerb 12
	)
	
	(method (init)
		(= cursor lookCursor)
		(super init:)
	)
)

(instance icon2 of IconI
	(properties
		view 990
		loop 2
		cel 0
		message 4
		signal $0041
		maskView 990
		maskLoop 9
		noun 3
		helpVerb 12
	)
	
	(method (init)
		(= cursor doCursor)
		(super init:)
	)
)

(instance icon3 of IconI
	(properties
		view 990
		loop 3
		cel 0
		message 2
		signal $0041
		maskView 990
		maskLoop 9
		noun 4
		helpVerb 12
	)
	
	(method (init)
		(= cursor talkCursor)
		(super init:)
	)
)

(instance icon4 of IconI
	(properties
		view 990
		loop 4
		cel 0
		message 6
		signal $0041
		maskView 990
		maskLoop 9
		noun 5
		helpVerb 12
	)
	
	(method (init)
		(= cursor askCursor)
		(super init:)
	)
)

(instance icon6 of IconI
	(properties
		view 990
		loop 5
		cel 0
		cursor 999
		message 0
		signal $0041
		maskView 990
		maskLoop 9
		maskCel 1
		noun 9
		helpVerb 12
	)
	
	(method (select param1 &tmp newEvent temp1 gIconBarCurInvIcon temp3 temp4)
		(return
			(cond 
				((& signal $0004) 0)
				((and argc param1 (& signal notUpd))
					(if (= gIconBarCurInvIcon (gIconBar curInvIcon?))
						(= temp3
							(+
								(/
									(-
										(- nsRight nsLeft)
										(CelWide
											(gIconBarCurInvIcon view?)
											(+ (gIconBarCurInvIcon loop?) 1)
											(gIconBarCurInvIcon cel?)
										)
									)
									2
								)
								nsLeft
							)
						)
						(= temp4
							(+
								(gIconBar y?)
								(/
									(-
										(- nsBottom nsTop)
										(CelHigh
											(gIconBarCurInvIcon view?)
											(+ (gIconBarCurInvIcon loop?) 1)
											(gIconBarCurInvIcon cel?)
										)
									)
									2
								)
								nsTop
							)
						)
					)
					(DrawCel view loop (= temp1 1) nsLeft nsTop -1)
					(if (= gIconBarCurInvIcon (gIconBar curInvIcon?))
						(DrawCel
							(gIconBarCurInvIcon view?)
							(+ 1 (gIconBarCurInvIcon loop?))
							(gIconBarCurInvIcon cel?)
							temp3
							temp4
							-1
						)
					)
					(Graph grUPDATE_BOX nsTop nsLeft nsBottom nsRight 1)
					(while (!= ((= newEvent (Event new:)) type?) 2)
						(newEvent localize:)
						(cond 
							((self onMe: newEvent)
								(if (not temp1)
									(DrawCel view loop (= temp1 1) nsLeft nsTop -1)
									(if (= gIconBarCurInvIcon (gIconBar curInvIcon?))
										(DrawCel
											(gIconBarCurInvIcon view?)
											(+ 1 (gIconBarCurInvIcon loop?))
											(gIconBarCurInvIcon cel?)
											temp3
											temp4
											-1
										)
									)
									(Graph grUPDATE_BOX nsTop nsLeft nsBottom nsRight 1)
								)
							)
							(temp1
								(DrawCel view loop (= temp1 0) nsLeft nsTop -1)
								(if (= gIconBarCurInvIcon (gIconBar curInvIcon?))
									(DrawCel
										(gIconBarCurInvIcon view?)
										(+ 1 (gIconBarCurInvIcon loop?))
										(gIconBarCurInvIcon cel?)
										temp3
										temp4
										-1
									)
								)
								(Graph grUPDATE_BOX nsTop nsLeft nsBottom nsRight 1)
							)
						)
						(newEvent dispose:)
					)
					(newEvent dispose:)
					(if (== temp1 1)
						(DrawCel view loop 0 nsLeft nsTop -1)
						(if (= gIconBarCurInvIcon (gIconBar curInvIcon?))
							(DrawCel
								(gIconBarCurInvIcon view?)
								(+ 1 (gIconBarCurInvIcon loop?))
								(gIconBarCurInvIcon cel?)
								temp3
								temp4
								-1
							)
						)
						(Graph grUPDATE_BOX nsTop nsLeft nsBottom nsRight 1)
					)
					temp1
				)
				(else 1)
			)
		)
	)
)

(instance icon7 of IconI
	(properties
		view 990
		loop 6
		cel 0
		cursor 999
		type $0000
		message 0
		signal $0043
		maskView 990
		maskLoop 9
		noun 6
		helpVerb 12
	)
	
	(method (show)
		(= loop (if (gEgo wearingGown?) 11 else 6))
		(super show: &rest)
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(gIconBar hide:)
				(gEgo showInv:)
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance icon8 of IconI
	(properties
		view 990
		loop 7
		cel 0
		cursor 999
		message 7
		signal $0043
		maskView 990
		maskLoop 9
		noun 7
		helpVerb 12
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(gIconBar hide:)
				(gGame showControls:)
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance icon9 of IconI
	(properties
		view 990
		loop 8
		cel 0
		cursor 9
		type $2000
		message 12
		signal $0003
		maskView 990
		maskLoop 9
		noun 8
		helpVerb 12
	)
)

(instance icon10 of IconI
	(properties
		view 990
		loop 10
		cel 0
		message 13
		signal $0041
		maskView 990
		maskLoop 9
		noun 10
		helpVerb 12
	)
	
	(method (init)
		(= cursor exitCursor)
		(super init:)
	)
	
	(method (select &tmp temp0)
		(return
			(if (super select: &rest)
				(gIconBar hide:)
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance checkIcon of Code
	(properties)
	
	(method (doit param1)
		(if
			(and
				(param1 isKindOf: IconI)
				(& (param1 signal?) $0004)
			)
			(= global116
				(| global116 (>> $8000 (gIconBar indexOf: param1)))
			)
		)
	)
)

(instance lb2DoVerbCode of Code
	(properties)
	
	(method (doit param1 param2)
		(proc0_6 param2 param1)
	)
)

(instance lb2FtrInit of Code
	(properties)
	
	(method (doit param1)
		(if (== (param1 sightAngle?) 26505)
			(param1 sightAngle: 40)
		)
		(if (== (param1 actions?) 26505) (param1 actions: 0))
		(if
			(and
				(not (param1 approachX?))
				(not (param1 approachY?))
			)
			(param1 approachX: (param1 x?) approachY: (param1 y?))
		)
	)
)

(instance lb2Messager of Messager
	(properties)
	
	(method (findTalker param1 &tmp temp0)
		(if
			(= temp0
				(switch param1
					(99 gNarrator)
					(22 (ScriptID 310 22))
					(20 (ScriptID 1904 20))
					(33 (ScriptID 260 33))
					(14 (ScriptID 1903 14))
					(17 (ScriptID 300 17))
					(16 (ScriptID 1901 16))
					(21
						(if (== gNumber 750)
							(ScriptID 750 21)
						else
							(ScriptID 1899 21)
						)
					)
					(29 (ScriptID 1884 29))
					(7
						(if (proc0_2 30)
							(ScriptID 230 7)
						else
							(ScriptID 1896 7)
						)
					)
					(1 (ScriptID 1880 1))
					(41 (ScriptID 280 41))
					(23
						(if (== gNumber 355)
							(ScriptID 355 23)
						else
							(ScriptID 1893 23)
						)
					)
					(8 (ScriptID 1906 8))
					(18 (ScriptID 1889 18))
					(15 (ScriptID 1900 15))
					(2
						(cond 
							((proc0_2 30) (ScriptID 230 2))
							((== gNumber 155) (ScriptID 155 2))
							((== gNumber 220) (ScriptID 220 2))
							((== gNumber 330) (ScriptID 330 2))
							((and (== gNumber 355) (not (proc0_2 91))) (ScriptID 355 2))
							(
							(and (== gNumber 710) (== (global2 picture?) 716)) (ScriptID 710 2))
							(else (ScriptID 1881 2))
						)
					)
					(4 (ScriptID 1895 4))
					(24 (ScriptID 1907 24))
					(37
						(if (== gNumber 230) (ScriptID 230 37))
					)
					(36
						(if (== gNumber 230) (ScriptID 230 37))
					)
					(35
						(if (== gNumber 230) (ScriptID 230 37))
					)
					(25 (ScriptID 1892 25))
					(19
						(cond 
							((== gNumber 295) (ScriptID 295 19))
							((== gNumber 770) (ScriptID 770 19))
							(else (ScriptID 1888 19))
						)
					)
					(30 (ScriptID 310 30))
					(10 (ScriptID 1882 10))
					(27
						(if
						(and (== gNumber 710) (== (global2 picture?) 716))
							(ScriptID 710 27)
						else
							(ScriptID 1891 27)
						)
					)
					(39 (ScriptID 480 39))
					(13 (ScriptID 1902 13))
					(3
						(if (== gNumber 220)
							(ScriptID 220 3)
						else
							(ScriptID 1894 3)
						)
					)
					(5 (ScriptID 1897 5))
					(31 (ScriptID 310 31))
					(12
						(cond 
							((== gNumber 240) (ScriptID 240 12))
							((== gNumber 330) (ScriptID 330 12))
							((== gNumber 775) (ScriptID 775 12))
							(else (ScriptID 1887 12))
						)
					)
					(32 (ScriptID 260 32))
					(34 (ScriptID 260 34))
					(9 (ScriptID 1883 9))
					(38 (ScriptID 290 38))
					(11 (ScriptID 1886 11))
					(28 (ScriptID 1885 28))
					(6 (ScriptID 1890 6))
				)
			)
			(return)
		else
			(super findTalker: param1)
		)
	)
)

(instance lb2ApproachCode of Code
	(properties)
	
	(method (doit param1)
		(switch param1
			(1 1)
			(2 2)
			(3 4)
			(4 8)
			(6 16)
			(13 32)
			(8 64)
			(38 128)
			(else  -32768)
		)
	)
)

(instance lb2Win of SysWindow
	(properties
		type $0080
	)
	
	(method (open &tmp temp0 temp1)
		(cond 
			((proc999_5 gNumber 280 210 330 240 260 300) (= temp1 0))
			(
				(proc999_5
					gNumber
					210
					220
					230
					260
					270
					280
					290
					295
					300
					310
					320
				)
				(= temp1 1)
			)
			(
				(proc999_5
					gNumber
					100
					105
					110
					120
					140
					150
					155
					160
					180
					190
					220
					335
					340
					350
					355
					360
					370
					400
				)
				(= temp1 2)
			)
			(
			(proc999_5 gNumber 460 660 700 710 715 720 730 740) (= temp1 4))
			(
				(proc999_5
					gNumber
					335
					340
					350
					355
					360
					370
					400
					420
					500
					510
					520
					525
					530
					540
					550
					560
					565
					430
					435
					440
					448
					450
					454
					455
					456
					460
					480
					490
					521
					600
					610
					620
					630
					640
					650
					666
					660
					700
					710
					715
					720
					730
					740
				)
				(= temp1 3)
			)
			(else (= temp1 4))
		)
		(= lsLeft (- left (/ (CelWide 994 temp1 0) 2)))
		(= lsTop (- top (if title 19 else 10)))
		(= lsRight (+ right (/ (CelWide 994 temp1 0) 2)))
		(= lsBottom
			(proc999_3
				(+ bottom 3)
				(+ lsTop (CelHigh 994 temp1 0) 3)
			)
		)
		(= priority 15)
		(super open:)
		(= temp0 (GetPort))
		(SetPort 0)
		(Graph grFILL_BOX top left bottom right 3 global176 15)
		(Graph
			grDRAW_LINE
			(- top 1)
			(- left 1)
			(- top 1)
			right
			global151
			15
		)
		(Graph
			grDRAW_LINE
			(- top 1)
			(- left 1)
			bottom
			(- left 1)
			global151
			15
		)
		(Graph
			grDRAW_LINE
			bottom
			(- left 1)
			bottom
			right
			global151
			15
		)
		(Graph
			grDRAW_LINE
			(- top 1)
			right
			bottom
			right
			global151
			15
		)
		(Graph grUPDATE_BOX top left bottom right 1)
		(Graph
			grUPDATE_BOX
			lsTop
			lsLeft
			(+ lsTop (CelHigh 994 temp1 0))
			(+ lsLeft (CelWide 994 temp1 0))
			1
		)
		(Graph
			grUPDATE_BOX
			lsTop
			(- lsRight (CelWide 994 temp1 0))
			(+ lsTop (CelHigh 994 temp1 0))
			lsRight
			1
		)
		(DrawCel 994 temp1 0 (+ lsLeft 1) (+ lsTop 1) -1)
		(DrawCel
			994
			temp1
			1
			(- (- lsRight (CelWide 994 temp1 0)) 1)
			(+ lsTop 1)
			-1
		)
		(SetPort temp0)
	)
)
