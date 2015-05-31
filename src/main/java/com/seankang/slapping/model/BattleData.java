package com.seankang.slapping.model;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 * Created by seankang on 5/15/2015.
 */
public class BattleData {


	private static ArrayList<Fighter> list = new ArrayList<Fighter>();


	private final static Logger LOGGER = Logger.getLogger(BattleData.class.getName());
	public static boolean loadBattleData() {

		try {
			File fileData = new File("src/main/resources/2012-07-candidates.csv");
			if (fileData.exists()){
				LOGGER.debug("file exists");

				FileReader fileReader = new FileReader(fileData);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				StringBuffer stringBuffer = new StringBuffer();
				String line;
				int lines=0;
				while ((line = bufferedReader.readLine()) != null) {
					if (lines == 0 ){
						// skip
					} else {
						list.add(loadFighter(line));
					}
					lines++;

				}
				fileReader.close();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private static Fighter loadFighter(String line) {
		StringTokenizer tokenizer = new StringTokenizer(line, ",");
		Fighter fighter = new Fighter();
		if (tokenizer != null){

			if (tokenizer.hasMoreTokens()){
				String name = tokenizer.nextToken();
				fighter.setName(name);
			}
			if (tokenizer.hasMoreTokens()){
				String health = tokenizer.nextToken();
				fighter.setHeath(Integer.parseInt(health));
			}
			if (tokenizer.hasMoreTokens()){
				String damage = tokenizer.nextToken();
				fighter.setDamage(Integer.parseInt(damage));
			}
			if (tokenizer.hasMoreTokens()){
				String attack = tokenizer.nextToken();
				fighter.setAttacks(Integer.parseInt(attack));
			}
			if (tokenizer.hasMoreTokens()){
				String dodge= tokenizer.nextToken();
				fighter.setDodge(Integer.parseInt(dodge));
			}
			if (tokenizer.hasMoreTokens()){
				String critical = tokenizer.nextToken();
				fighter.setCritical(Integer.parseInt(critical));
			}
			if (tokenizer.hasMoreTokens()){
				String init = tokenizer.nextToken();
				fighter.setInitiative(Integer.parseInt(init));
			}



		}
		return fighter;
	}

	public static Fighter getFighter(int index){
		return list.get(index);
	}
	public static int getFighterCount() {
		return list.size();
	}
}
