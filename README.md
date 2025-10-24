# Laura Bow 2 CD v1.1 Both Text & Speech Patch for DOSBox.

The "Both" text & speech option has been patched into LB2 under ScummVM for some time. This repo is intended to provide the same functionality for DOSBox and retro hardware users. It should not cause any issues under ScummVM.

This patch requires replacing the existing 100.scr and 100.hep files in your LB2 game directory. The new patches include all changes from the previous patches, but be sure to backup your original files before installation!

## Installation:

Move/copy all patch files from the 'PATCHES' folder directly into your Laura Bow 2 CD (version 1.1) game folder. Alternately, copy the PATCHES folder and add the following to your RESOURCES.CFG file: patchDir=PATCHES\

The game begins with both speech and text enabled. Click the MODE button in the control panel to switch between TEXT, SPEECH, and BOTH.

## Additional changes and bug fixes

* Patches 0.scr and 923.scr remove the base control panel restrictions.
* Additional patches remove 'per room' control panel restrictions: 310.scr, 441.scr, 454.scr, 520.scr, 550.scr, 610.scr, 700.scr, and 710.scr.
* Patch 0.scr also fixes an issue with "The hands-off cursor is often displayed when the player has control" and "Music volume can get permanently lowered down by clock chimes".
* Patch 99.scr enables the previously unused Death Message speech. This patch is optional.
* Patch 350.scr fixes a bug that can softlock the game while entering the museum.
* Patch 440.scr fixes an issue with 'Armor rooms don't handle verb events'.
* Patch 448.scr fixes an issue with 'Crash/freeze due to stack overflow during act 5', 'Incorrect transom re-initialization', 'Out of sync transom's animation speed' and 'Armor rooms don't handle verb events'.
* Patch 454.scr also fixes an issue with 'Lock when interacting with lid of open coffin during act 5' and 'Glitchy animation when closing the left coffin'.
* Patch 460.scr fixes an issue with "Lock when trying to pass through the eastern door after swinging the crate".
* Patch 520.scr also fixes an issue with 'Premature startup of act 5 when examining the countess' and 'Wrong music triggering when reentering the room without examining the countess'.
* Patch 550.scr also fixes an issue with 'Lock after the back rub scene (when entering from Carrington's office, 550)' and 'Objects disappear during the back rub scene, after the close-up'.
* Patch 600.scr fixes a softlock that occurs if the 'bugs with meat' appear while leaving the basement room.
* Patch 700.scr also fixes a graphical issue after unlocking the sarcophagus in the mummy storage room.
* Patch 770.scr fixes an issue where the rock breaking sound effects interfere with spoken dialog.
* Patch 2660.hep fixes a bug that causes a softlock when trying to reach the bottom left part of the elevator room.
* The NewRisingSun patch has been integrated into the game. It fixes an issue with 'Endless driving' and 'Moving painting to reveal safe bug' in DOSBox.
* NRS patches are: 28.scr, 28.hep, 250.scr, 560.scr, 994.scr, 999.scr.
