package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

public class ExampleMod implements ModInitializer {

    @Override
    public void onInitialize() {

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {

            if (!world.isClient) {

                // Apna username yahan daalo
                boolean adminBypass =
                        player.getName().getString().equals(". peakmuffin38106");

                // Normal key check
                boolean hasKey =
                        player.getInventory().count(Items.TRIPWIRE_HOOK) > 0;

                if (adminBypass || hasKey) {

                    // Sirf normal players ki key remove hogi
                    if (!adminBypass) {
                        player.getInventory().removeOne(
                                new ItemStack(Items.TRIPWIRE_HOOK));
                    }

                    // Test reward
                    player.giveItemStack(
                            new ItemStack(Items.NETHERITE_INGOT, 2));

                    player.sendMessage(
                            Text.literal("Crate Opened!"), false);

                } else {

                    player.sendMessage(
                            Text.literal("Key nahi hai!"), false);
                }
            }

            return ActionResult.SUCCESS;
        });
    }
}
