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
* Patch 90.scr fixes an issue with "Yvette responds with the wrong message when asking her about Tut in acts 3+".
* Patch 99.scr enables the previously unused Death Message speech. This patch is optional.
* Patch 110.scr fixes an issue with "The music can abruptly change during the intro".
* Patch 230.scr fixes an issue that made the leftmost reporter answer as the one next to him, wrong voice included.
* Patch 250.scr fixes an issue with 'Endless taxi driving', 'Taxi drive prematurely ends' and 'Laura's messages stop the street animation during the taxi drive'.
* Patch 310.scr also has a minor improvement that prevents a crash if one teleports into the room in debug mode and then uses the south exit.
* Patch 350.scr fixes a bug that can softlock the game while entering the museum.
* Patch 420.scr fixes a bug that can cause timers to break from act 5 onwards.
* Patch 440.scr fixes an issue with 'Armor rooms don't handle verb events'.
* Patch 448.scr fixes an issue with 'Crash/freeze due to stack overflow during act 5', 'Incorrect transom re-initialization', 'Out of sync transom's animation speed' and 'Armor rooms don't handle verb events'.
* Patch 454.scr also fixes an issue with 'Lock when interacting with lid of open coffin during act 5' and 'Glitchy animation when closing the left coffin'.
* Patch 460.scr fixes an issue with "Lock when trying to pass through the eastern door after swinging the crate".
* Patch 480.scr fixes an issue with "It isn't possible to get the bone unless clicking on its surroundings", "The bone can be picked up from far away" and "The music can abruptly change before the start of act 6".
* Patch 520.scr also fixes an issue with 'Premature startup of act 5 when examining the countess' and 'Wrong music triggering when reentering the room without examining the countess'.
* Patch 550.scr also fixes an issue with 'Lock after the back rub scene (when entering from Carrington's office, 560)' and 'Objects disappear during the back rub scene, after the close-up'.
* Patch 560.scr fixes an issue with 'Moving painting to reveal safe bug'.
* Patch 561.scr improves the 'Moving painting to reveal safe bug' fix.
* Patch 600.scr fixes a softlock that occurs if the 'bugs with meat' appear while leaving the basement room.
* Patch 700.scr also fixes a graphical issue after unlocking the sarcophagus in the mummy storage room.
* Patch 770.scr fixes an issue with "Rock-breaking sound effects interrupt spoken dialog in the BOTH message mode", "Rock-breaking sound effects sometimes aren't played", "The left cop on the tower and the generic prisoners have animations that don't loop and were supposed to", "The generic prisoners' animations sometimes unexpectedly retrigger" and "The murderer's view partially disappears when messages are shown".
* Patch 2660.hep fixes a bug that causes a softlock when trying to reach the bottom left part of the elevator room.

## Note for vintage hardware owners:

If you plan to run LB2CD directly on vintage hardware (not on DOSBox, ScummVM, 86box, etc...) and your computer is way faster than what the game supports, you can try the well known solutions listed at [sierrahelp (archive)](https://web.archive.org/web/20180623172746/https://sierrahelp.com/Games/LauraBow/LauraBow2Help.html).
NewRisingSun's patches get along well with our patches, they can help to slown down animations and work around timing issues, but you ONLY need 28.scr, 28.hep, 994.scr and 999.scr, the rest are unneeded or fix bugs we've already covered. These patches are also available at [sierrahelp (archive)](https://web.archive.org/web/20180624184636/http://www.sierrahelp.com/Files/Patches/LauraBow2(LB2CD_NRS).zip). Only use them if you need them, if your game already runs at correct speed you probably don't want to throttle it.
