;;; Sierra Script 1.0 - (do not remove this comment)
(script# 923)
(include sci.sh)
(use Main)
(use View)
(use Obj)


(class Inset of Code
	(properties
		picture 0
		anOverlay 0
		style $0064
		view 0
		loop 0
		cel 0
		x 0
		y 0
		priority 14
		register 0
		hideTheCast 0
		caller 0
		owner 0
		script 0
		oldCast 0
		oldFeatures 0
		oldATPs 0
		oldMH 0
		oldKH 0
		oldDH 0
		oldWH 0
		oldObstacles 0
		oldStyle 0
		inset 0
		disposeNotOnMe 0
		modNum -1
		noun 0
		insetView 0
	)
	
	(method (init theCaller theOwner theRegister)
		(= owner theOwner)
		(owner inset: self)
		(= register theRegister)
		(= caller theCaller)
		(if (and (not picture) hideTheCast) (self hideCast: 1))
		(= oldCast gOldCast)
		(= oldFeatures gOldFeatures)
		(= oldATPs gOldATPs)
		(= oldMH gLb2MDH)
		(= oldKH gLb2KDH)
		(= oldDH gLb2DH)
		(= oldWH gLb2WH)
		(= oldObstacles (global2 obstacles?))
		(global2 obstacles: ((List new:) add: yourself:))
		((= gOldCast (EventHandler new:)) name: {newCast} add:)
		((= gOldFeatures (EventHandler new:))
			name: {newFeatures}
			add: self
		)
		((= gOldATPs (EventHandler new:)) name: {newATPs} add:)
		((= gLb2MDH (EventHandler new:))
			name: {newMH}
			add: self
		)
		((= gLb2KDH (EventHandler new:))
			name: {newKH}
			add: self
		)
		((= gLb2DH (EventHandler new:)) name: {newDH} add: self)
		((= gLb2WH (EventHandler new:)) name: {newWH} add:)
		(gTheDoits add: self)
		(self drawInset:)
	)
	
	(method (doit)
		(if script (script doit:))
		(if (not hideTheCast) (Animate (oldCast elements?) 0))
	)
	
	(method (dispose param1 &tmp theCaller)
		(if inset (inset dispose: 0))
		(gOldFeatures delete: self)
		(gLb2MDH delete: self)
		(gLb2KDH delete: self)
		(gLb2DH delete: self)
		(gLb2WH delete: self)
		(gTheDoits delete: self)
		(gOldCast
			eachElementDo: #dispose
			eachElementDo: #delete
			release:
			dispose:
		)
		(gOldATPs dispose:)
		(gOldFeatures dispose:)
		(gLb2MDH dispose:)
		(gLb2KDH dispose:)
		(gLb2DH dispose:)
		(gLb2WH dispose:)
		((global2 obstacles?) dispose:)
		(owner inset: 0)
		; IMPROVEMENT: Enable control panel access when insets are disposed.
		;
		; The control panel is what lets the player adjust the settings and
		; save/load the game. This CD version of the game restricts access to
		; the control panel for unclear reasons and we've already reverted the
		; base restrictions in LB2:handsOn.
		;
		; The floppy version of the game enables access to the control panel
		; whenever an inset is disposed. This was removed for the CD version
		; and has the side effect of leaving the control panel disabled after
		; viewing an inset in any room.
		;
		; We enable access to the control panel when an inset is disposed and
		; the owner is the current room. Fix ported from:
		; https://github.com/scummvm/scummvm/blob/85702e06764f95a6b700e348dd90931613efdc29/engines/sci/engine/script_patches.cpp#L11175
		(if (== owner global2) (gIconBar enable: 7))
		; END OF IMPROVEMENT (see also LB2:handsOn in #0 and the script files of
		; rooms 310, 441, 454, 520, 550, 610, 700, and 710))
		(if (or (not argc) param1) (self refresh:))
		(global2 obstacles: oldObstacles)
		(= gOldCast oldCast)
		(= gOldATPs oldATPs)
		(= gOldFeatures oldFeatures)
		(= gLb2MDH oldMH)
		(= gLb2KDH oldKH)
		(= gLb2DH oldDH)
		(= gLb2WH oldWH)
		(if (or (not argc) param1) (gOldATPs doit:))
		(if (and (not picture) hideTheCast) (self hideCast: 0))
		(if (and (or (not argc) param1) caller)
			(= theCaller caller)
			(= caller 0)
			(theCaller cue:)
		)
	)
	
	(method (setScript theScript)
		(if (IsObject script) (script dispose:))
		(= script (if argc theScript else 0))
		(if script (script init: self &rest))
	)
	
	(method (handleEvent pEvent &tmp [temp0 2])
		(return
			(cond 
				((and inset (inset handleEvent: pEvent)) 0)
				((& (pEvent type?) evVERB)
					(cond 
						((self onMe: pEvent) (pEvent claimed: 1) (self doVerb: (pEvent message?)))
						(disposeNotOnMe (pEvent claimed: 1) (self dispose:))
						(else (return 0))
					)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (== modNum -1) (= modNum gNumber))
		(if
		(and global90 (Message msgGET modNum noun theVerb 0 1))
			(gLb2Messager say: noun theVerb 0 0 0 modNum)
		else
			(gLb2DoVerbCode doit: theVerb self)
		)
		(return 1)
	)
	
	(method (hideCast param1 &tmp temp0 temp1)
		(= temp0 0)
		(= temp1 (if param1 1000 else -1000))
		(while (< temp0 (gOldCast size?))
			((gOldCast at: temp0)
				z: (+ ((gOldCast at: temp0) z?) temp1)
			)
			(++ temp0)
		)
		(Animate (gOldCast elements?) 0)
	)
	
	(method (drawInset)
		(if (> picture 0)
			(DrawPic
				picture
				(if anOverlay 100 else style)
				(if anOverlay 0 else 1)
				global178
			)
		)
		(if view
			(= insetView
				((inView new:)
					view: view
					loop: loop
					cel: cel
					x: x
					y: y
					setPri: priority
					ignoreActors: 1
					init:
					yourself:
				)
			)
		)
	)
	
	(method (restore)
		(self drawInset:)
		(if inset ((inset oldATPs?) doit:) (inset restore:))
	)
	
	(method (refresh)
		(if view
			(DrawPic (global2 picture?) dpOPEN_NO_TRANSITION)
		else
			(DrawPic (global2 picture?) style)
		)
		(global2 style: oldStyle)
		(if (!= gPicNumber -1)
			(DrawPic
				gPicNumber
				dpOPEN_NO_TRANSITION
				dpNO_CLEAR
				global178
			)
		)
		(if (global2 inset:) ((global2 inset:) restore:))
	)
	
	(method (setInset param1 param2 param3)
		(if inset (inset dispose:))
		(if (and argc param1)
			(param1
				init:
					(if (>= argc 2) param2 else 0)
					self
					(if (>= argc 3) param3 else 0)
			)
		)
	)
	
	(method (onMe param1 param2 &tmp temp0 temp1)
		(if (IsObject param1)
			(= temp0 (param1 x?))
			(= temp1 (param1 y?))
		else
			(= temp0 param1)
			(= temp1 param2)
		)
		(return
			(if view
				(return (insetView onMe: param1 param2))
			else
				(return 1)
			)
		)
	)
)

(instance inView of View
	(properties)
	
	(method (handleEvent)
		(return 0)
	)
)
