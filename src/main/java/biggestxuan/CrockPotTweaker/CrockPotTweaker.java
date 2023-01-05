package biggestxuan.CrockPotTweaker;

import com.blamejared.crafttweaker.CraftTweaker;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(CrockPotTweaker.MODID)
public class CrockPotTweaker {
    public static final String MODID = "crockpot_tweaker";
    public static final String PREFIX = "mods.crockpot.";

    public CrockPotTweaker() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation rl(String value){
        return new ResourceLocation(CraftTweaker.MODID,value);
    }

}
