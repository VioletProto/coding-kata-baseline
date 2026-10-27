# Coding kata baseline: Java RPG

This baseline is a copy of the CSC150 `FinalCSCProgram` console RPG from
`Y1/Q2/CSC150/FinalCSCProgram/FinalCSCProgram`. All 38 Java source files are
copied unchanged; the previous C# practice projects have been removed.
The original project is left untouched.

The game includes turn-based combat, enemy statuses, equipment, inventory,
trading, and story progression. No design-pattern refactor has been applied.

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
That attribution is preserved; use your own gameplay code as the refactoring
subject for the assignment's authorship requirement.

Music playback in `StoryController.java` references an absolute Windows path.
The original WAV file is not included in this source baseline. Missing audio
prints an error; the music controller skips playback when no clip is loaded.
Gameplay behavior and existing bugs have been preserved for future katas.
