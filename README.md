<div align="center"><img alt="Static Badge" src="https://img.shields.io/badge/🐒jme3-Jungle-yellow?style=for-the-badge"  height="100" align="center">

 # Environment support for jMonkeyEngine 3

[![Maven Central](https://img.shields.io/maven-central/v/io.github.pesegato/jungle?style=for-the-badge)](https://central.sonatype.com/artifact/io.github.pesegato/jungle) 
[![GitHub Packages](https://img.shields.io/github/v/tag/Pesegato/Jungle?label=GitHub%20Packages&style=for-the-badge)](https://github.com/Pesegato/Jungle/packages/3221231)
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
import io.github.pesegato.jungle.Environment;
import java.nio.file.Path;
import java.nio.file.Files;

public class JungleExample {
    static Logger log = LoggerFactory.getLogger(JungleExample.class);

    public static void main(String[] args) throws Exception {
        Environment.init(log);
        log.info(Environment.getRendererFriendlyName());
        log.info(Environment.getOSFriendlyName());
        log.info(Environment.getJavaFriendlyName());
        
        save("slot 1");
    }
    
    public static String getPathOfSlot(String name) {
        return Environment.getGameFolder() + "/slots/" + name + ".json";
    }
    
    public void save() {
        Writer writer = null;
        try {
            log.info("SAVE DATA {}", name);
            writer = new FileWriter(getPathOfSlot(name));
            //GSON example
            //new Gson().toJson(this, writer);
        } catch (IOException ex) {
            log.error(null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                log.error(null, ex);
            }
        }
    }
    
    public static void load(String name) {
        try {
            Reader reader = null;
            log.info("LOAD DATA {}", name);
            reader = new FileReader(getPathOfSlot(name));
            //custom logic...
            reader.close();
        } catch (IOException ex) {
            log.error(null, ex);
        }
    }
}
```
