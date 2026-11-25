# Caelus API ? unofficial 26.3 port

Upstream: https://github.com/TheIllusiveC4/Caelus
Revision: 24869195a6aa96f1f9e8bdaca04cc649c05902af (1.21.4 branch, released for 1.21.4/1.21.5).
Target: Minecraft 26.3, NeoForge 26.3.0.118, Java 25, ModDevGradle 2.0.148.
License: LGPL-3.0-or-later; upstream COPYRIGHT and COPYING files retained. Distribution requires corresponding source and notices, plus final asset review. No third-party publication authorized by this port.

Changes:
- Retain caelus mod ID, caelus:fall_flying, default 0.1, ALLOW/DEFAULT/DENY semantics, and legacy glider callback Consumer<Item>.
- Native canGlide(boolean) interception follows ground/passenger/levitation restrictions and leaves non-NeoForge connections untouched. DEFAULT delegates to native NeoForge gliding flight.
- Modify the native durability slot list before its empty guard so a registered custom glider can take damage even with no equipped vanilla glider. Preserve native onGlideDamage for equipment and adapt the old callback to the new ItemStack break hook.
- Migrate cape rendering to CapeLayer.submit / AvatarRenderState, retaining event name and accessor. Minecraft type migration requires downstream recompilation.
- Legacy flight packet uses native tryToStartFallFlying after stopping the previous flight, including native liquid/ability checks.
- Remove original publishing tasks from active build, retain upstream attribution and package source and license files.

QC: external isolated harness at ../caelus-qc exercises real runtime JAR. Initial GameTest run passed: registry/API identity, all three flight states, native fallback, ground guard, custom durability with no equipment, original list isolation, vanilla durability. Client cape hook descriptor checked against target bytecode; actual visual cape rendering and networked client flight not yet exercised. Final build and rerun logs are adjacent to the worktree and in harness directory. Do not mark full runtime acceptance or publish until client QC and asset review are complete.
