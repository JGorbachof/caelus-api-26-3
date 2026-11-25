# Caelus API - Unofficial Port

Caelus provides a flight attribute and API for mods that add Elytra-style gliding to custom equipment or abilities.

Original project: [Illusive Soulworks (C4)](https://github.com/TheIllusiveC4/Caelus). This independent port is based on upstream revision `24869195a6aa96f1f9e8bdaca04cc649c05902af`.

## Installation

Minecraft 26.3, NeoForge 26.3.0.118, Java 25. Download the mod JAR from [Releases](https://github.com/JGorbachof/caelus-api-26-3/releases) and place it in the mods folder on client and server. No additional mod dependencies are required.

## Testing

Alpha build. Server GameTests passed for attribute registration, flight eligibility, native flight fallback and glider durability. Client rendering, multiplayer flight and full modpack compatibility are not yet verified.

## Build and license

Run `gradlew.bat build` on Windows or `./gradlew build` on Linux with Java 25. The original source and assets are LGPL-3.0-or-later; this port retains that license. See LICENSE, COPYING, COPYING.LESSER, README.upstream.md and PORT_NOTES.md for attribution, source and changes.

[ModHub](https://jgorbachof.github.io/) | [Discord](https://discord.gg/3zPAp2vk6Q)
