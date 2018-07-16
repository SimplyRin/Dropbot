package net.simplyrin.discord.dropbot.listener;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.simplyrin.discord.dropbot.Main;

/**
 * Created by SimplyRin on 2018/07/16.
 *
 * Copyright (C) 2018 SimplyRin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
public class MessageListener extends ListenerAdapter {

	private Main instance;

	public MessageListener(Main instance) {
		this.instance = instance;
	}

	/**
	 * Erangel and Miramar drop spots list from https://gist.github.com/UsefulEndymion/2096766be1eb45c209516afc214fec78#file-bot-js-L8-L9
	 */
	private String[] erangelDropSpots = {"Rozhok", "School", "Apartments", "Yasnaya Polyana", "Severny", "Shooting Range", "Mansion", "Prison", "Mylta", "Mylta Power", "Novolepnoye", "Military", "Ferry Pier", "Primorsk", "Hospital", "Georgopol Crates", "Georgopol South", "Georgopol North", "Zharki", "Stalber", "Kameshki", "Pochinki", "Ruins", "Water Town", "Farm"};
	private String[] miramarDropSpots = {"Pecado", "San Martin", "Hacienda del Patron", "Minas Generales", "Monte Nuevo", "Chumacera", "Los Leones", "Puerto Paraiso", "Impala", "El Azahar", "Cruz del Valle", "La Cobreria", "El Pozo", "Valle del Mar", "Prison", "Los Higos", "Power Grid", "Water Treatment", "Torre Ahumada", "Campo Militar", "La Bendita", "East Islands"};

	private String[] sanhokDropSpots = {"Khao", "Mongnal", "Tat Mok", "Ha Tinh", "Paradise Resort", "Camp Bravo", "Camp Alpha", "Bootcamp", "Bhan", "Lakawi", "Ruins", "Pai Nan", "Quarry", "Kampong", "Cave", "Tambang", "Na Kham", "Camp Charlie", "Sahmee", "Ban Tai", "Docks"};

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		MessageChannel sender = event.getChannel();
		String[] args = event.getMessage().getContentRaw().split(" ");

		if(args.length > 0) {
			if(!args[0].equalsIgnoreCase("!drop")) {
				return;
			}

			if(args.length > 1) {
				/**
				 * For admin
				 */
				if(event.getAuthor().getId().equals("224428706209202177")) {
					if(args[1].equalsIgnoreCase("shutdown")) {
						sender.sendMessage("Shutting down...").complete();
						this.instance.getJda().shutdown();
						System.exit(0);
						return;
					}

					if(args[1].equalsIgnoreCase("reboot")) {
						sender.sendMessage("Restarting...").complete();
						this.instance.getJda().shutdown();
						this.instance.run();
						return;
					}
				}

				/**
				 * Erangel
				 */
				if(args[1].equalsIgnoreCase("erangel") || args[1].equalsIgnoreCase("island") || args[1].equalsIgnoreCase("e") || args[1].equalsIgnoreCase("i")) {
					List<String> list = Arrays.asList(this.erangelDropSpots);
					Collections.shuffle(list);
					String result = list.get(0);

					sender.sendMessage("[Erangel] Drop Spot: **" + result + "**").complete();
					return;
				}

				/**
				 * Miramar
				 */
				if(args[1].equalsIgnoreCase("miramar") || args[1].equalsIgnoreCase("desert") || args[1].equalsIgnoreCase("m") || args[1].equalsIgnoreCase("d")) {
					List<String> list = Arrays.asList(this.miramarDropSpots);
					Collections.shuffle(list);
					String result = list.get(0);

					sender.sendMessage("[Miramar] Drop Spot: **" + result + "**").complete();
					return;
				}

				/**
				 * Sanhok
				 */
				if(args[1].equalsIgnoreCase("sanhok") || args[1].equalsIgnoreCase("s") || args[1].equalsIgnoreCase("savage")) {
					List<String> list = Arrays.asList(this.sanhokDropSpots);
					Collections.shuffle(list);
					String result = list.get(0);

					sender.sendMessage("[Sanhok] Drop Spot: **" + result + "**").complete();
					return;
				}
			}

			sender.sendMessage("Usage: !drop <erangel,miramar,sanhok>").complete();
			return;
		}
	}

}
