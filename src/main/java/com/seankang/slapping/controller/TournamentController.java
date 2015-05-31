package com.seankang.slapping.controller;

import com.seankang.slapping.model.BattleData;
import com.seankang.slapping.model.Fighter;
import com.seankang.slapping.view.View;

/**
 * Created by seankang on 5/16/2015.
 */
public class TournamentController {

	public void start(View view) {
		int round=0;
		for (int ifigher = 0; ifigher < BattleData.getFighterCount(); ifigher++ ){

			Fighter dude = BattleData.getFighter(ifigher);
			for (int target = 0; target < BattleData.getFighterCount(); target++ ) {
				if (target != ifigher ){// cant fight self
					Fighter otherdude = BattleData.getFighter(target);

					MatchController match = new MatchController(dude, otherdude, ++round);
					view.sendOutput("*******************************************************************");
					view.sendOutput("****************           MATCH START           ******************");
					view.sendOutput("*******************************************************************");
					match.start(view);

					view.sendOutput("*******************************************************************");
					view.sendOutput("****************           MATCH END             ******************");
					view.sendOutput("*******************************************************************");
				}
			}
		}
	}
}
