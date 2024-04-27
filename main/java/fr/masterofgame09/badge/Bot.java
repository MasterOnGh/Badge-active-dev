package fr.masterofgame09.badge;


import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Bot {

    public Bot() throws LoginException{
        Dotenv dotenv = Dotenv.load();
        String TOKEN = dotenv.get("TOKEN");
        if(TOKEN.equals("")){
            System.out.println("Please set your token in the .env file");
            return;
        }
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(TOKEN);
        builder.setActivity(Activity.playing("github.com/masterofgame09"));

        ShardManager shardManager = builder.build();
        shardManager.addEventListener(new EventManager());
    }

    public static void main(String[] args) {
        File env = new File(".env");
        if(!env.exists()){
            try {
                env.createNewFile();
                FileWriter wr = new FileWriter(env);
                wr.write("TOKEN=");
                wr.close();
                System.out.println("Please set your token in the .env file");
                return;
            } catch (IOException e) {
                System.err.println("Erreur lors de la création du fichier .env : " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        try {
            //  Don't create gui because we use CLI interface
            //  Gui gui = new Gui();
            Bot bot = new Bot();
        }catch (LoginException e){
            throw new RuntimeException(e);
        }

    }
}
