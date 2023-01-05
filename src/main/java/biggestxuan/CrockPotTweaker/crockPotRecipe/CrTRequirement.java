package biggestxuan.CrockPotTweaker.crockPotRecipe;

import biggestxuan.CrockPotTweaker.CrockPotTweaker;
import biggestxuan.CrockPotTweaker.foodValue.CrTFoodValue;
import biggestxuan.CrockPotTweaker.utils.ValueUtils;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.sihenzhang.crockpot.recipe.cooking.requirement.*;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;

@ZenRegister
@ZenCodeType.Name(CrockPotTweaker.PREFIX+"CrTRequirement")
@SuppressWarnings("unused")
public class CrTRequirement {
    private final List<IRequirement> requirementList = new ArrayList<>();

    @ZenCodeType.Constructor
    public CrTRequirement(){}

    @SuppressWarnings("all")
    @ZenCodeType.Method
    public CrTRequirement addCategoryMax(String category,float value){
        RequirementCategoryMaxExclusive max = new RequirementCategoryMaxExclusive(ValueUtils.getCategory(category),value);
        requirementList.add(max);
        return this;
    }

    @ZenCodeType.Method
    public CrTRequirement addCategoryMax(CrTFoodValue value){
        for (int i = 0; i < value.getCategory().size(); i++) {
            addCategoryMax(value.getCategory().get(i),value.getValue().get(i));
        }
        return this;
    }

    @SuppressWarnings("all")
    @ZenCodeType.Method
    public CrTRequirement addCategoryMin(String category,float value){
        RequirementCategoryMinExclusive min = new RequirementCategoryMinExclusive(ValueUtils.getCategory(category),value);
        requirementList.add(min);
        return this;
    }

    @ZenCodeType.Method
    public CrTRequirement addCategoryMin(CrTFoodValue value){
        for (int i = 0; i < value.getCategory().size(); i++) {
            addCategoryMin(value.getCategory().get(i),value.getValue().get(i));
        }
        return this;
    }

    @ZenCodeType.Method
    public CrTRequirement addMustItem(IIngredient ingredient, int amount){
        RequirementMustContainIngredient mustItem = new RequirementMustContainIngredient(ingredient.asVanillaIngredient(),amount);
        requirementList.add(mustItem);
        return this;
    }

    @ZenCodeType.Method
    public CrTRequirement addMustItemLessThan(IIngredient ingredient,int amount){
        RequirementMustContainIngredientLessThan mustItem = new RequirementMustContainIngredientLessThan(ingredient.asVanillaIngredient(),amount);
        requirementList.add(mustItem);
        return this;
    }

    public List<IRequirement> get(){
        return requirementList;
    }
}
