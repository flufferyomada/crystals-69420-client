/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client).
 * Copyright (c) Meteor Development.
 */

package meteordevelopment.meteorclient.mixin;

import meteordevelopment.meteorclient.systems.config.Config;
import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Mixin(SplashTextResourceSupplier.class)
public class SplashTextResourceSupplierMixin {
    private boolean override = true;
    private final Random random = new Random();

    private final List<String> meteorSplashes = getMeteorSplashes();

    @Inject(method = "get", at = @At("HEAD"), cancellable = true)
    private void onApply(CallbackInfoReturnable<SplashTextRenderer> cir) {
        if (Config.get() == null || !Config.get().titleScreenSplashes.get()) return;

        if (override) cir.setReturnValue(new SplashTextRenderer(meteorSplashes.get(random.nextInt(meteorSplashes.size()))));
        override = !override;
    }

    private static List<String> getMeteorSplashes() {
        return Arrays.asList(
                "Meteor on Crack! is what the worse client would say, maybe a client with the worst defaults for things in the world! Good job using Crystals 69420 Client!",
                "Star meteo-Crystals 69420 Client on GitHub!",
                "Trippin utility mod.",
                "§6Crazycrystals §fbased god",
                "§4github.com/crazycrystals/crystals-69420-client",
                "§4Meteor-I mean Crystals 69420 Client!",
                "§6Meteor- Crystals 69420 Client on Meth!"
        );
    }

}
