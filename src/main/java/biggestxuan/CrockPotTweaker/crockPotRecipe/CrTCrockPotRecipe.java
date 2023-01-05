package biggestxuan.CrockPotTweaker.crockPotRecipe;

import biggestxuan.CrockPotTweaker.CrockPotTweaker;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionRemoveRecipeByName;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionRemoveRecipeByOutput;
import com.blamejared.crafttweaker.impl.item.MCItemStackMutable;
import com.sihenzhang.crockpot.recipe.CrockPotRecipeTypes;
import com.sihenzhang.crockpot.recipe.cooking.CrockPotCookingRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;

@ZenRegister
@ZenCodeType.Name(CrockPotTweaker.PREFIX+"CrTCrockPotRecipe")
@SuppressWarnings("unused")
public class CrTCrockPotRecipe implements IRecipeManager {
    @ZenCodeType.Method
    public void addRecipe(String name, CrTRequirement requirement, IItemStack output, int priority, int weight, int cookingTime, int potLevel){
        ResourceLocation rl = CrockPotTweaker.rl(name);
        CrockPotCookingRecipe recipe = new CrockPotCookingRecipe(rl,requirement.get(),output.getImmutableInternal(),priority,weight,cookingTime,potLevel);
        CraftTweakerAPI.apply(new ActionAddRecipe(this,recipe,""));
    }

    @ZenCodeType.Method
    public void addRecipe(String name, CrTRequirement requirement, IItemStack output,int priority){
        addRecipe(name,requirement,output,priority,10,200,0);
    }

    @ZenCodeType.Method
    public void removeRecipe(IItemStack output){
        CraftTweakerAPI.apply(new ActionRemoveRecipeByOutput(this,output){
            @Override
            public void apply(){
                List<ResourceLocation> toRemove = new ArrayList<>();
                for(ResourceLocation location : getManager().getRecipes().keySet()) {
                    IRecipe<?> recipe = getManager().getRecipes().get(location);
                    if(recipe instanceof CrockPotCookingRecipe) {
                        CrockPotCookingRecipe recipeIR = (CrockPotCookingRecipe) recipe;
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
    public IRecipeType<CrockPotCookingRecipe> getRecipeType() {
        return CrockPotRecipeTypes.CROCK_POT_COOKING_RECIPE_TYPE;
    }
}
