#!/bin/bash/
javac -cp ".:src.com.mills.handlers:src.com.mills.main:srsc.com.mills.main.rendering:src.com.mills.world:src.com.mills.world.tiles" src/com/mills/handlers/*.java src/com/mills/main/*.java src/com/mills/main/rendering/*.java src/com/mills/world/*.java src/com/mills/world/tiles/*.java -d bin
echo "Done!"
