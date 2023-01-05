package biggestxuan.CrockPotTweaker.explosionRecipe;

import biggestxuan.CrockPotTweaker.CrockPotTweaker;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionRemoveRecipeByName;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionRemoveRecipeByOutput;
import com.blamejared.crafttweaker.impl.item.MCItemStackMutable;
import com.sihenzhang.crockpot.recipe.CrockPotRecipeTypes;
import com.sihenzhang.crockpot.recipe.ExplosionCraftingRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;

@ZenRegister
@ZenCodeType.Name(CrockPotTweaker.PREFIX+"CrTExplosionRecipe")
@SuppressWarnings("unused")
public class CrTExplosionRecipe implements IRecipeManager {
    @ZenCodeType.Method
    public void addRecipe(String name, IIngredient input, IItemStack output,float lossRate,boolean onlyBlock){
        ExplosionCraftingRecipe recipe = new ExplosionCraftingRecipe(CrockPotTweaker.rl(name),input.asVanillaIngredient(),output.getImmutableInternal(),lossRate,onlyBlock);
        CraftTweakerAPI.apply(new ActionAddRecipe(this,recipe,""));
    }

    @ZenCodeType.Method
    public void removeRecipe(IItemStack output){
        CraftTweakerAPI.apply(new ActionRemoveRecipeByOutput(this,output){
            @Override
            public void apply(){
                List<ResourceLocation> toRemove = new ArrayList<>();
                for(ResourceLocation location : getManager().getRecipes().keySet()) {
                    IRecipe<?> recipe = getManager().getRecipes().get(location);
                    if(recipe instanceof ExplosionCraftingRecipe) {
                        ExplosionCraftingRecipe recipeIR = (ExplosionCraftingRecipe) recipe;
                        ItemStack recipeOutput = recipeIR.getResultItem();
                        if(output.matches(new MCItemStackMutable(recipeOutput))) {
                            toRemove.add(location);
                        }
                    }
                }
                toRemove.forEach(getManager().getRecipes()::remove);
            }
        });
    }

    @ZenCodeType.Method
    public void removeRecipeByName(String name){
        String[] n = name.split(":");
        ResourceLocation rl = new ResourceLocation(n[0],n[1]);
        CraftTweakerAPI.apply(new ActionRemoveRecipeByName(this,rl));
    }

    @Override
    public IRecipeType<ExplosionCraftingRecipe> getRecipeType() {
        return CrockPotRecipeTypes.EXPLOSION_CRAFT_RECIPE_TYPE;
    }
}
