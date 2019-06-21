# CraftMark [![Build Status](https://travis-ci.com/AtriusX/CraftMark.svg?branch=master)](https://travis-ci.com/AtriusX/CraftMark)
CraftMark is a spigot plugin that adds markdown support to minecraft's chat system. This greatly 
extends and improves the game's formatting system in a way that's much more intuitive than before.

## Supported Operations
Chat colors will continue to work as they have before; using `&` with `0-9` or `a-f`. 

Formatting codes are supported via the following markdown operations:

| Name          | Operation       | Code |
|---------------|-----------------|------|
| Bold          | \*\*example**   | &l   |
| Italic        | \*example*      | &o   |
| Underline     | ++example++     | &m   |
| Strikethrough | \~~example~~    | &n   |
| Spoiler       | \|\|example\|\| | &k   |

Later operations will support [links](#supported-operations); other options to be considered.

## Contributing

To begin contributing to this project, import the project into your preferred IDE of choice. Make 
sure to also take the time to create a spigot test server on your machine for debugging.

To build the project, run `gradle build`. I would also recommend setting up a debug runner in your
IDE for hot reloading. Follow [this guide](https://www.spigotmc.org/wiki/intellij-debug-your-plugin/)
for IntelliJ and [this one](https://www.spigotmc.org/wiki/eclipse-debug-your-plugin/) for eclipse.

Please take care to verify that you have Java 8 installed and your project is using it, additionally
please note that the project follows the K&R style guidelines.
