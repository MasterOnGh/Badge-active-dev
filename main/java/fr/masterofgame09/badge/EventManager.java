package fr.masterofgame09.badge;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class EventManager extends ListenerAdapter {

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        List<CommandData> data = new ArrayList<>();
        data.add(Commands.slash("badge", "Give the badge"));
        event.getGuild().updateCommands().addCommands(data).queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("badge")){
            EmbedBuilder builder = new EmbedBuilder()
                    .setTitle("How to get the badge ?")
                    .setDescription("## Hi " + event.getUser().getAsMention() + " !\n\n After the orders, you will receive a notification at [Discord Developer Portal](https://discord.com/developers/applications) approximately 24 hours after you have done it. After waiting 24 hours, you can see the notification, after you have done your configuration to have the badge, you can see your profile with the nice badge !")
                    .setFooter("Thanks for using my bot !")
                    .setTimestamp(event.getTimeCreated())
                    .setColor(Color.RED);
            event.replyEmbeds(builder.build()).queue();
        }
    }
}
