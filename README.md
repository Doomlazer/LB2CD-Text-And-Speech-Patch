# Laura Bow 2 CD v1.1 Both Text & Speech Patch for DOSBox.

The "Both" text & speech option has been patched into LB2 under ScummVM for some time. This repo is intended to provide the same functionality for DOSBox and retro hardware users. It should not cause any issues under ScummVM.

This patch requires replacing the existing 100.scr and 100.hep files in your LB2 game directory. The new patches include all changes from the previous patches, but be sure to backup your original files before installation!

## Installation:

Move/copy all patch files from the 'PATCHES' folder directly into your Laura Bow 2 CD (version 1.1) game folder. Alternately, copy the PATCHES folder and add the following to your RESOURCES.CFG file: patchDir=PATCHES\

The game begins with both speech and text enabled. Click the MODE button in the control panel to switch between TEXT, SPEECH, and BOTH.

## Additional changes and bug fixes

* Patches 0.scr and 923.scr remove the base control panel restrictions.
* Additional patches remove 'per room' control panel restrictions: 310.scr, 441.scr, 454.scr, 520.scr, 610.scr, 700.scr, 550.scr (left to do: 710).
* Patch 550.scr also fixes an issue with 'Lock after the back rub scene (when entering from Carrington's office, 550)'.
* Patch 99.scr enable the previously unused Death Message speech. This patch is optional.
* The NewRisingSun patch has been integrated into the game. It fixes an issue with 'Endless driving' and 'Moving painting to reveal safe bug' in DOSBox.
* NRS patches are: 28.scr, 28.hep, 250.scr, 560.scr, 994.scr, 999.scr.
* Patch 770.scr fixes an issue where the rock breaking sound effects interfere with spoken dialog. - thanks to Avo323
* Patch 350.scr fixes a bug that can softlock the game while entering the museum. - thanks to Avo323
