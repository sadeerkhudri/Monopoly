# Monopoly (Java Swing) — Student Project

A Java Swing Monopoly-style game with a complete GUI flow:
- Main menu (start game / help / quit)
- 2–6 player setup (choose token, enter name, pick custom colour)
- Turn-based gameplay with dice animation and movement
- Property buying + rent, railroads, utilities, tax, jail, doubles, bankruptcy, and win condition

Built as a learning project focused on OOP, event-driven GUI programming, and game-state logic.

---

## Quick Start (Run the Game)

### Option A — Run in an IDE (recommended)
1) Open the repo in IntelliJ / Eclipse.
2) Mark the `src/` folder as a **Sources Root**.
3) Run:
- `Monopoly.Menu` (this launches the menu and starts the music)

### Option B — Run from terminal
From the repo root:

1) Compile:
- `javac -d out src/Monopoly/*.java`

2) Copy assets into the runtime classpath (see “Assets Setup” below), then run:
- `java -cp "out:src" Monopoly.Menu`

---

## Features Implemented

### Player Setup (2–6 players)
- Choose a token (required before proceeding)
- Optional name (defaults to “Player X”)
- Optional colour selection (defaults to black)
- Enforces the Monopoly player range (2–6)

### Core Gameplay Mechanics
- Dice roll + movement around the board (clockwise)
- Buy property if unowned
- Pay rent if owned by another player
- Railroads: rent scales based on how many railroads the owner has
- Utilities: pay dice roll × (4 or 10) depending on whether the owner has 1 or 2 utilities
- Doubles: extra turn; 3 doubles in a row sends you to jail
- Jail:
  - Pay $50 bail OR attempt doubles to get out
  - If no doubles after 3 tries, pay bail and move normally
- Passing/landing on GO: +$200
- Tax spaces: pay the listed amount
- Bankruptcy:
  - If money < 0 at end of turn → player removed
  - Properties return to the bank
- Win condition: last remaining player wins

### Not Implemented Yet (In Progress)
- Houses / hotels (colour sets)
- Mortgaging properties
- Chance / Community Chest
- Player trading
- Saving/Loading game

---

## Repo Structure (Important: Code + Assets in the Same Package Folder)

Because this project loads resources via `getResource("...")`, all assets must live *inside the same package folder* as the Java classes:

Recommended layout:

Monopoly/
├── README.md
├── LICENSE
└── src/
    └── Monopoly/                 <-- package Monopoly;
        ├── Menu.java
        ├── Monopoly.java
        ├── monopolyMusic.wav
        ├── backgrounds/
        ├── cards/
        ├── dice/
        ├── tokens/
        └── ui/

Resource paths used in code (examples):
- backgrounds/menu.png
- backgrounds/game.png
- dice/one.png ... dice/six.png
- ui/new_game.png, ui/roll_dice.png, etc.
- tokens/boot.png, tokens/hat.png, etc.
- cards/1.png

---

## Assets Setup (Read This)

### If you keep the repo PRIVATE
You can include your assets normally.

### If you make the repo PUBLIC (recommended approach)
Do NOT commit Monopoly-branded artwork/audio that you don’t have permission to redistribute.

Instead:
1) Keep the folder structure in the repo (empty folders are fine).
2) Add your actual assets locally (not tracked by git).
3) Use `.gitignore` to prevent committing copyrighted images/audio.

Example `.gitignore` entries you can use:

src/Monopoly/backgrounds/*.png
src/Monopoly/cards/*.png
src/Monopoly/dice/*.png
src/Monopoly/tokens/*.png
src/Monopoly/ui/*.png
src/Monopoly/*.wav

To keep empty folders in GitHub, add an empty file named `.gitkeep` inside each folder:
- `src/Monopoly/backgrounds/.gitkeep`
- `src/Monopoly/dice/.gitkeep`
- etc.

If you want the project to run for other people without Monopoly assets, you can:
- replace assets with your own artwork, or
- use open-license assets and keep the filenames/paths identical.

---

## Controls / How to Play
- Start from the main menu: New Game → Player setup → Start Game
- Roll dice to move
- Use on-screen buttons for actions (buy property, end turn, bail, etc.)
- Turn status (name/money/token) is shown on the interface

---

## License + Trademark Notice

### Code License
The **source code** in this repository is licensed under the **MIT License** (see `LICENSE`).

### Assets / Trademarks
This project is for educational, non-commercial purposes.
Any Monopoly-related names, logos, artwork, and game assets are the property of their respective owners and are **not** licensed for redistribution by this repository. If this repo is public, please use your own or open-license assets.

---

## Credits / References
During development I used public references for Swing patterns (timers, button enabling, opening a URL, etc.) and general Java utilities. Specific links are documented in the source header comments.
