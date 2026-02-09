# Laura Bow 2 CD v1.1 Both Text & Speech Patch for DOSBox.

The BOTH text & speech option has been patched into LB2 under ScummVM for some time. This repo is intended to provide the same functionality for DOSBox and retro hardware users. It should not cause any issues under ScummVM.

## Installation:

Move/copy all patch files from the PATCHES folder directly into your Laura Bow 2 CD (version 1.1) game folder. This method requires replacing the existing 100.scr and 100.hep files in your LB2 game directory. The new patches include all changes from the original patches, but be sure to backup your original files before installation!

Alternatively, copy the PATCHES folder (folder included) into your game directory and add the following line to RESOURCE.CFG:
```
patchDir=PATCHES\.;.\;audiosfx\. 
```
If a line starting with "patchDir=" already exists, replace it. This method doesn't require overwriting any existing patch.

The game begins with both speech and text enabled. Click the MODE button in the control panel to switch between TEXT, SPEECH, and BOTH.

## Additional changes and bug fixes
While this project's main purpose is to patch text & speech support into LB2, further work has been done to add various small improvements and fix plenty of bugs present in the game. Many of those fixes have been ported from ScummVM.

<details>
<summary> List of additional changes and bug fixes </summary>

* Patch 0.scr
  * Fixes issues with:
    * The hands-off cursor is often displayed when the player has control
    * Music volume can get permanently lowered down by  clock chimes
    * Showing the about screen has a fake memory limitation
  * Adds the following changes:
    * Remove base control panel restrictions
* Patch 90.scr fixes an issue that made Yvette respond with the wrong message when asking her about Tut in acts 3+.
* Patch 99.scr has changes to enable the previously unused Death Message speech.
* Patch 110.scr fixes an issue that made the music abruptly change during the intro.
* Patch 230.scr (Tribune) fixes an issue that made the leftmost reporter answer as the one next to him, wrong voice included.
* Patch 250.scr (Taxi)
  * Fixes issues with:
    * Endless taxi driving
    * Taxi drive prematurely ends
    * Laura's messages pause the street animation during the taxi drive
* Patch 310.scr (Speakeasy)
  * Adds the following changes:
    * Remove control panel restrictions of this room
    * Prevent a crash when using the south exit after having teleported into the room in debug mode
* Patch 350.scr (Rotunda) fixes a bug that gave control to the player while entering the museum, leading to a softlock.
* Patch 420.scr (Mastodon Room) fixes a bug that could cause timers from act 5 onwards to break.
* Patch 430.scr (Pterodactyl Room)
  * Fixes issues with:
    * The east door always initializes unlocked even if it's wired shut
    * Using the wire on an opened east door during act 5 doesn't flag it as closed
    * Trying to open the east door while it's wired shut flags it as open
    * Obtaining the wire at the right moment during the chase in act 5 makes the murderer no longer appear
    * Using the west exit at the right moment during the chase in act 5 makes the murderer no longer appear
    * Laura can get stuck near the east exit
    * Closing the east door at the right moment during the chase in act 5 lets the murderer pass through the closed door
    * Closing the east door at the right moment during the chase in act 5 gives the player control while the 'death script' is ongoing
  * Adds the following changes:
    * Remove the 3 seconds wait when the murderer appears during act 5
    * Make the murderer not chase after Laura during act 5 until she has finished moving
    * Make the murderer appear and kill Laura if the player tries to use the east exit during the chase in act 5
* Patch 440.scr (Medieval Armory)
  * Fixes issues with:
    * The room of the Medieval Armory (rm440) doesn't handle verb events
    * Unhiding from the tapestry enables the inventory icon when there isn't any active inventory item, which can lead to a crash
    * Hiding or unhiding from the tapestry at the right moment during the chase in act 5 can softlock the game
    * Using the south or east exits at the right moment during the chase in act 5 can crash the game
    * The areas for clicking the armors and flags are 10 pixels off on the y axis
    * Laura doesn't properly head towards all the armors and flags
    * Olympia is shown for a fraction of a second right before her meeting with Heimlich
* Patch 441.scr
  * Fixes issues with:
    * Mouse cursor doesn't change to an arrow when hovering over the south exit right after the countess' meeting
  * Adds the following changes:
    * Remove the control panel restrictions during the countess' meeting
* Patch 442.scr fixes a bug that made Laura unable to hide in the tapestry right after Olympia and Heimlich meeting finishes.
* Patch 448.scr (Medieval Armory, northern corridor)
    * Fixes issues with:
      * The room of the northern Medieval Armory (rm448) doesn't handle verb events
      * Crash/freeze due to stack overflow during the chase in act 5
      * Incorrect transom re-initialization
      * Out of sync transom's animation speed
* Patch 454.scr (Egyptian Exhibit, west area)
  * Fixes issues with:
    * Softlock in act 5 when interacting with the lid of the left coffin if it was left open in the previous act
    * Glitchy animation when closing the left coffin
  * Adds the following changes:
    * Remove control panel restrictions of this room
* Patch 460.scr (Storage Room) fixes an issue that locked the game when trying to pass through the east door after swinging the crate.
* Patch 480.scr (T-Rex Room)
  * Fixes issues with:
    * It isn't possible to get the bone unless clicking on its surroundings
    * The bone can be picked up from far away
    * The music can abruptly change right before the start of act 6
* Patch 520.scr (Olympia's Office)
  * Fixes issues with:
    * Premature startup of act 5 when examining the countess in act 4
    * Re-entering the room during act 4 after the loose snake scene without examining the countess plays the wrong music
  * Adds the following changes:
    * Remove control panel restrictions of this room
* Patch 550.scr (Yvette's Office)
  * Fixes issues with:
    * Softlock after the back rub scene when entering from Carrington's office (#560)
    * Objects disappear during the back rub scene, after the close-up
  * Adds the following changes:
    * Remove control panel restrictions of this room
* Patch 560.scr (Carrington's Office] fixes an issue that didn't let the player move the painting to reveal the safe.
* Patch 561.scr (Carrington's Office, safe) has changes to improve the painting/safe bugfix of 560.scr.
* Patch 600.scr (Museum Basement) fixes a softlock that occurred when the 'bugs with meat' appear while leaving the basement room.
* Patch 610.scr (Alcohol Lab) has changes to remove the control panel restrictions of this room.
* Patch 700.scr (Mummy Storage)
  * Fixes issues with:
    * Graphical glitch after unlocking the left coffin
  * Adds the following changes:
    * Remove control panel restrictions of this room
* Patch 710.scr (Sun Altar Room) has changes to remove the control panel restrictions of this room.
* Patch 770.scr (Prison)
  * Fixes issues with
    * Rock-breaking sound effects interrupt spoken dialog in the BOTH message mode
    * Rock-breaking sound effects sometimes aren't played
    * The left cop on the tower and the generic prisoners have animations that don't loop and were supposed to
    * The generic prisoners' animations sometimes unexpectedly re-trigger
    * The murderer's view partially disappears when messages are shown
* Patch 920.scr has changes to remove the base control panel restrictions.
* Patch 2660.hep (Elevator) fixes a bug that caused a softlock when trying to reach the bottom left part of the elevator room.
</details>

## Note for retro hardware users:

If you plan to run LB2CD directly on retro hardware (not on DOSBox, ScummVM, 86box, etc...) and your computer is way faster than what the game supports, you can try the well known solutions listed at [sierrahelp (archive)](https://web.archive.org/web/20180623172746/https://sierrahelp.com/Games/LauraBow/LauraBow2Help.html).
NewRisingSun's patches get along well with our patches, they can help to slown down animations and work around timing issues, but you ONLY need 28.scr, 28.hep, 994.scr and 999.scr, the rest are unneeded or fix bugs we've already covered. These patches are also available at [sierrahelp (archive)](https://web.archive.org/web/20180624184636/http://www.sierrahelp.com/Files/Patches/LauraBow2(LB2CD_NRS).zip). Only use them if you need them, if your game already runs at correct speed you probably don't want to throttle it.
