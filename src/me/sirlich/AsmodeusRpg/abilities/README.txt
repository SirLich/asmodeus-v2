Adding abilities to Asmodeus is really simple. Just follow these steps to add everything correctly.

1) Create the actual ability file in the abilities directory.

2) Extend Ability (public class <yourAbility> extends Ability)

3) Create a constructor that takes a player and call super(p)

4) Edit any of the Ability values you need. setName and setRecharge rate among others might be usefull.

6) Override Run and put anything you like

7) If you gave an effect, make sure to start a Task to remove the effect after some seconds

5) Add the ability to AbilitiesEditor if you want it to be accessible in-game.
    - Create the <ability name>_PREDICATE values
    - Add the item to the correct open<AbilityType>Inventory method
    - Add the correct lines to the <AbilityType>AbilityInventoryHandler