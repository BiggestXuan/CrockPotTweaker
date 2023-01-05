package biggestxuan.CrockPotTweaker.foodValue;

import biggestxuan.CrockPotTweaker.CrockPotTweaker;
import biggestxuan.CrockPotTweaker.utils.ValueUtils;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.sihenzhang.crockpot.base.FoodValues;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;

@ZenRegister
@ZenCodeType.Name(CrockPotTweaker.PREFIX+"CrTFoodValue")
@SuppressWarnings("unused")
public class CrTFoodValue{
    private final List<String> Category = new ArrayList<>();
    private final List<Float> value = new ArrayList<>();

    @ZenCodeType.Constructor
    public CrTFoodValue(){}

    public FoodValues get(){
        FoodValues values = FoodValues.create();
        for (int i = 0; i < Category.size(); i++) {
            values.put(ValueUtils.getCategory(this.Category.get(i)),this.value.get(i));
        }
        return values;
    }

    @ZenCodeType.Method
    public CrTFoodValue put(String Category,float value){
        this.Category.add(Category);
        this.value.add(value);
        return this;
    }

    public List<Float> getValue() {
        return value;
    }

    public List<String> getCategory() {
        return Category;
    }
}
