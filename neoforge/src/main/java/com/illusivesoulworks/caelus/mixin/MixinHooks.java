package com.illusivesoulworks.caelus.mixin;

import com.illusivesoulworks.caelus.api.GlidingDamageEvent;
import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.util.Util;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.NeoForge;

public class MixinHooks {

  public static List<EquipmentSlot> damageGliders(
      LivingEntity livingEntity, List<EquipmentSlot> slots) {
    List<Pair<ItemStack, Consumer<Item>>> stacks = new ArrayList<>();
    var vanillaSlots = new java.util.IdentityHashMap<Pair<ItemStack, Consumer<Item>>, EquipmentSlot>();

    for (EquipmentSlot slot : slots) {
      ItemStack stack = livingEntity.getItemBySlot(slot);

      if (!stack.isEmpty()) {
        var entry = new Pair<ItemStack, Consumer<Item>>(stack, item -> {});
        stacks.add(entry);
        vanillaSlots.put(entry, slot);
      }
    }
    GlidingDamageEvent evt = NeoForge.EVENT_BUS.post(new GlidingDamageEvent(livingEntity, stacks));

    if (evt.isModified()) {
      Pair<ItemStack, Consumer<Item>> glider =
          Util.getRandom(evt.getResults(), livingEntity.getRandom());

      if (livingEntity.level() instanceof ServerLevel serverLevel) {
        EquipmentSlot slot = vanillaSlots.get(glider);
        if (slot != null) {
          glider.getFirst().onGlideDamage(livingEntity, slot);
        } else {
          glider.getFirst().hurtAndBreak(1, serverLevel, livingEntity, broken -> glider.getSecond().accept(broken.getItem()));
        }
      }
      return List.of();
    }

    return slots;
  }
}
