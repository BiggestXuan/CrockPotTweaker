import mods.crockpot.CrTFoodValue;
import mods.crockpot.CrTRequirement;
import mods.crockpot.CrTWeightItem;

<recipetype:crockpot:food_values>.addRecipe("iron",<item:minecraft:iron_ingot>,new CrTFoodValue().put("fruit",1f),false);
<recipetype:crockpot:food_values>.addRecipe("diamond",<tag:items:forge:gems/diamond>,new CrTFoodValue().put("egg",1.5f).put("meat",1f).put("fish",2f),false);

<recipetype:crockpot:crock_pot_cooking>.addRecipe("nether_star",
new CrTRequirement()
.addCategoryMin("meat",0.5f)
.addCategoryMax("egg",1f)
.addMustItem(<item:minecraft:iron_ingot>,1)
.addMustItemLessThan(<item:minecraft:diamond>,3),
<item:minecraft:nether_star>,20);

<recipetype:crockpot:crock_pot_cooking>.addRecipe("obsidian",
new CrTRequirement()
.addCategoryMin("fish",2f)
.addMustItem(<item:minecraft:diamond>,1)
.addMustItemLessThan(<item:minecraft:cod>,1),
<item:minecraft:obsidian>,100,100,100,0);

<recipetype:crockpot:crock_pot_cooking>.removeRecipe(<item:crockpot:hot_chili>);

<recipetype:crockpot:explosion_crafting>.addRecipe("diamond",<item:minecraft:diamond_block>,<item:minecraft:diamond>*9,0.1f,false);

<recipetype:crockpot:explosion_crafting>.addRecipe("gold",<item:minecraft:gold_block>,<item:minecraft:gold_ingot>*9,0f,true);

<recipetype:crockpot:piglin_bartering>.addRecipe("emerald_to_coal",<item:minecraft:emerald>,[
    new CrTWeightItem(<item:minecraft:coal>,1,9,4),
    new CrTWeightItem(<item:minecraft:coal_block>,1,2)
]);