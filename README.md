<div align="center"><img alt="Static Badge" src="https://img.shields.io/badge/🐒jme3-Jungle-yellow?style=for-the-badge"  height="100" align="center">

 # Environment support for jMonkeyEngine 3

[![Maven Central](https://img.shields.io/maven-central/v/io.github.pesegato/jungle?style=for-the-badge)](https://central.sonatype.com/artifact/io.github.pesegato/jungle) 
[![GitHub Packages](https://img.shields.io/github/v/tag/Pesegato/Jungle?label=GitHub%20Packages&style=for-the-badge)](https://github.com/Pesegato/Jungle/packages/3269064)
[![javadoc](https://javadoc.io/badge2/io.github.pesegato/jungle/javadoc.svg?style=for-the-badge)](https://javadoc.io/doc/io.github.pesegato/jungle) 

</div>

<div align="center"><img alt="Jungle Logo" src="jungle-logo.png" align="center">
</div>

## Instructions

Features:
* Provides a folder for saving games, settings and logs (on Windows is on the "SavedGames" system folder).
* Shows system information

## Example

```java
import com.pesegato.jungle.Environment;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class JungleExample {
    public static void main(String[] args) throws IOException {
        // Set this once, before asking for game/settings paths.
        Environment.setGameFolderName("JungleExample");
        System.out.println(Environment.getJavaFriendlyName());
        save("slot 1", "{\"level\":1}");
        System.out.println(load("slot 1"));
    }

    public static Path getPathOfSlot(String name) {
        return Path.of(Environment.getGameFolder(), "slots", name + ".json");
    }

    public static void save(String name, String json) throws IOException {
        Path slot = getPathOfSlot(name);
        Files.createDirectories(slot.getParent());
        Files.writeString(slot, json, StandardCharsets.UTF_8);
    }

    public static String load(String name) throws IOException {
        return Files.readString(getPathOfSlot(name), StandardCharsets.UTF_8);
    }
}
```

`Environment.init(logger)` also collects GPU information and calls OpenGL. Invoke it on your running jME rendering thread with an active OpenGL context, for example inside `simpleInitApp()`, before using `getRendererFriendlyName()` or `getOSFriendlyName()`. The file example above does not need an OpenGL context. Use trusted slot names; validate user-supplied names before using them as paths.
