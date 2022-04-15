public class NPCClickEvent implements Listener {

    @EventHandler
    public void OnNPCLClick(NPCLeftClickEvent event) {
        if(GUIManager.getGUI(event.getClicker()) != null) {
            return;
        }
        if(event.getNPC().getId() == 7) { //SHOP
            new GUIShop(event.getClicker());
        } else if(event.getNPC().getId() == 15) { //ACHIEVEMENTS
            new GUIAchievement(event.getClicker());
        } else if(event.getNPC().getId() == 19) { //RANKS
            new GUIMainRankScreen(event.getClicker());
        } else if(event.getNPC().getId() == 32) { //UPGRADES
            new GUIMainScreen(event.getClicker());
        } else if (event.getNPC().getId() == 40) { //FISHERMAN
            new Fisherman(event.getClicker());
        }
    }
    @EventHandler
    public void OnNPCRClick(NPCRightClickEvent event) {
        if(GUIManager.getGUI(event.getClicker()) != null) {
            return;
        }
        if(event.getNPC().getId() == 7) { //SHOP
            new GUIShop(event.getClicker());
        } else if(event.getNPC().getId() == 15) { //ACHIEVEMENTS
            new GUIAchievement(event.getClicker());
        } else if(event.getNPC().getId() == 19) { //RANKS
            new GUIMainRankScreen(event.getClicker());
        } else if(event.getNPC().getId() == 32) { //UPGRADES
            new GUIMainScreen(event.getClicker());
        } else if (event.getNPC().getId() == 40) { //FISHERMAN
            new Fisherman(event.getClicker());
        }
    }
}
