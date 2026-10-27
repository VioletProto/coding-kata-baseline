# Coding kata baseline: Java RPG

This baseline is a copy of the CSC150 `FinalCSCProgram` console RPG from
`CSC150`.

The game includes turn-based combat, enemy statuses, equipment, inventory,
trading, and story progression.

## Build and run

Use JDK 17 or newer. From the repository root, run:

```sh
mkdir -p build/classes
find src -name '*.java' -print > build/sources.txt
javac -d build/classes @build/sources.txt
java -cp build/classes edu.neumont.csc150.Main
```

The game is interactive and requires terminal input. Compiled output and IDE
settings are excluded from version control. There is no automated test suite.

## Kata opportunities

- State: extract the status-dependent attack, damage, and transition behavior
  in `models/Characers/Enemy.java` into state objects.
- Factory: centralize enemy and equipment construction in `models/battles/BattleMap.java`.
  `controllers/WeaponForge.java` already resembles a simple factory.

## Existing limitations and authorship

`controllers/MusicController.java` explicitly credits ChatGPT and online help.

Music playback uses `assets/battle-music.wav`, relative to the repository root.
Run the game with the repository root as the working directory (including in
your IDE). WAV files are ignored by Git and must be supplied locally; they are
not uploaded to GitHub. Missing audio prints an error; the music controller
skips playback when no clip is loaded. Other gameplay behavior and existing
bugs have been preserved for future katas.
