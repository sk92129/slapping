package com.seankang.slapping.main;

import com.seankang.slapping.controller.TournamentController;
import com.seankang.slapping.model.BattleData;
import com.seankang.slapping.view.View;

/**
 * Created by seankang on 5/15/2015.
 */
public class Main {

	public static void main (String [] args) {
		BattleData.loadBattleData();
		View view = new View();

		view.sendOutput("Loaded all fighter data. Count: " + BattleData.getFighterCount());

		for (int ifigher = 0; ifigher < BattleData.getFighterCount(); ifigher++ ){
			BattleData.getFighter(ifigher).sendToView(view);
		}
		TournamentController tournament = new TournamentController();
		tournament.start(view);

		view.sendOutput("Tournament is over");
		view.sendOutput("Display all fighters stats");


		for (int ifigher = 0; ifigher < BattleData.getFighterCount(); ifigher++ ){
			BattleData.getFighter(ifigher).sendToView(view);

		}

	}
}
