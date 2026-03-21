;;; Sierra Script 1.0 - (do not remove this comment)
(script# 99)
(include sci.sh)
(use Main)
(use LBRoom)
(use Print)

(public
	deathRoom 0
)

(instance deathRoom of LBRoom
	(properties
		picture 780
		horizon 120
		north 350
	)

	(method (init &tmp temp0 temp1)
		(super init:)
		(gWrapSound number: 99 flags: 1 loop: 1 play:) ; mDeathMusic
		(if (== global145 15)
			(= temp1 86)
		else
			(= temp1 62)
		)
		(= temp0 (+ global145 1))
		(Animate (gOldCast elements:) 0)
		; IMPROVEMENT: restore speech for death messages.
		;
		; The game files contain recorded speech for the death messages, but
		; they never got used in the released game.
		;
		; We make the game use those audio clips by playing them using DoAudio
		; when the active message mode isn't TEXT. We then stop the audio on
		; restart and on restore.
		(if (!= global90 1) (DoAudio audPLAY 99 1 45 temp0 1)) ; added: play the appropiate audio resource if message mode isn't TEXT
		(repeat
			(switch
				(Print
					addText: 1 45 temp0 0 100 0
					addIcon: 99 0 global145 0 0
					addButton: 1 2 0 0 1 0 temp1 99 ; "Restore"
					addButton: 2 2 0 0 2 (proc0_11 70 70 62 70 70) temp1 99 ; "Restart" (proc0_11 = localize/whichLanguage)
					addButton: 3 2 0 0 3 140 temp1 99 ; "Quit"
					init:
				)
				(1
					(DoAudio audSTOP) ; added: stop audio
					(gGame restore:)
				)
				(2
					(DoAudio audSTOP) ; added: stop audio
					(gGame restart:)
				)
				(3
					(= global4 1) ; global4 = gQuit
					(break)
				)
			)
		)
		; END OF IMPROVEMENT
	)
)