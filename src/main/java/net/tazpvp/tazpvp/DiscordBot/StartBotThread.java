package net.tazpvp.tazpvp.DiscordBot;

import javax.security.auth.login.LoginException;

public class StartBotThread extends Thread{
    public void run() {
        try {
            TazBot.main(null);
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
