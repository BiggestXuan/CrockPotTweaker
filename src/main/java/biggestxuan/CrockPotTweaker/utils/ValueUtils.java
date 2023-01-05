package biggestxuan.CrockPotTweaker.utils;

import com.sihenzhang.crockpot.base.FoodCategory;

public class ValueUtils {
    public static FoodCategory getCategory(String name){
        switch (name.toLowerCase()){
            case "meat":
                return FoodCategory.MEAT;
            case "monster":
                return FoodCategory.MONSTER;
            case "fish":
                return FoodCategory.FISH;
            case "egg":
                return FoodCategory.EGG;
            case "fruit":
                return FoodCategory.FRUIT;
            case "veggie":
                return FoodCategory.VEGGIE;
            case "dairy":
                return FoodCategory.DAIRY;
            case "sweetener":
                return FoodCategory.SWEETENER;
            case "frozen":
                return FoodCategory.FROZEN;
            default:
                return FoodCategory.INEDIBLE;
        }
    }
}
