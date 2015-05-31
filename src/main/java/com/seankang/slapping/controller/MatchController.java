package com.seankang.slapping.controller;

import com.seankang.slapping.model.Fighter;
import com.seankang.slapping.view.View;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by seankang on 5/16/2015.
 */
public class MatchController {

	private final static Logger LOGGER = Logger.getLogger(MatchController.class.getName());
	private ArrayList<MatchDude> dudes = new ArrayList<MatchDude>();

	private int roundNumber;
	public MatchController(final Fighter dude, final Fighter otherdude, int round) {
		dudes.add(new MatchDude(dude));
		dudes.add(new MatchDude(otherdude));
		roundNumber = round;
	}

	private View view;



	public void start(View view) {
		this.view = view;
		view.sendOutput("");
		view.sendOutput("Candidate 1:" + dudes.get(0).getName());
		view.sendOutput("\tHealth: "+ dudes.get(0).getHealth());
		view.sendOutput("\tDamage: "+ dudes.get(0).getDamage());
		view.sendOutput("\tAttacks: "+ dudes.get(0).getAttacks());
		view.sendOutput("");
		view.sendOutput("Candidate 2:" + dudes.get(1).getName());
		view.sendOutput("\tHealth: "+ dudes.get(1).getHealth());
		view.sendOutput("\tDamage: "+ dudes.get(1).getDamage());
		view.sendOutput("\tAttacks: "+ dudes.get(1).getAttacks());
		view.sendOutput("");
		view.sendOutput("Round :"+ roundNumber);



		int istart = randomStart();

		view.sendOutput(((istart == 0)? dudes.get(0).getName() : dudes.get(1).getName()) +
				" is randomly selected to go first.");
		while (!isFightDone()){
			LOGGER.debug("fighting....");
			attack(istart);
			istart++;
			if (istart > 1){
				istart =0;
			}
		}
		decideWinner();
	}

	private void decideWinner() {
		if (dudes.get(0).getHealth()  < dudes.get(1).getHealth()) {
			// dude 2 wins
			dudes.get(1).win();
			dudes.get(0).lose();
			view.sendOutput(dudes.get(1).getName() + " wins! ");
		} else {
			dudes.get(0).win();
			dudes.get(1).lose();
			view.sendOutput(dudes.get(0).getName() + " wins! ");

		}
	}

	private void attack(int iDudeAttacking) {
		int damage = 0;
		if (dudes.get(iDudeAttacking).getAttacksRemaining() > 0){
			dudes.get(iDudeAttacking).attack();
			damage = dudes.get(iDudeAttacking).getDamage();


			int PunchingBag = iDudeAttacking+1;
			if (PunchingBag > 1) {
				PunchingBag=0;
			}
			int prevHealth = dudes.get(PunchingBag).getHealth();
			view.sendOutput(dudes.get(iDudeAttacking).getName() + " hits " +
					dudes.get(PunchingBag).getName() + " " + damage + " damage "+
				"( "+ prevHealth + " -> " + (prevHealth-damage)+
				" )");


			dudes.get(PunchingBag).slap(damage);

		}


	}

	private boolean isFightDone() {
		if (dudes.get(0).getAttacksRemaining() == 0 && dudes.get(1).getAttacksRemaining() == 0) {
			return true;
		}
		if (dudes.get(0).getHealth() <= 0  || dudes.get(1).getHealth() <= 0) {
			return true;
		}

		return false;
	}

	private int randomStart() {
		int minimum = 0;
		int maximum = 1;
		int randomNum = minimum + (int)(Math.random()*maximum);
		return randomNum;
	}

	class MatchDude {
		private Fighter fighter;
		private int attacksRemaining;

		public MatchDude(Fighter dude){
			fighter = dude;
			attacksRemaining = dude.getAttacks();

		}

		public int getHealth() {
			return fighter.getHeath();
		}

		public int getAttacksRemaining() {
			return attacksRemaining;
		}

		public void attack() {
			attacksRemaining--;
		}

		public int getDamage() {
			return fighter.getDamage();
		}

		public void slap(int damage) {
			int healthCurrent = fighter.getHeath();
			healthCurrent -= damage;
			fighter.setHeath(healthCurrent);
			LOGGER.debug("slapped");
		}

		public String getName() {
			return fighter.getName();
		}

		public int getAttacks() {
			return fighter.getAttacks();
		}

		public void win() {
			int win = fighter.getWins();
			win++;
			fighter.setWins(win);
		}

		public void lose() {
			int loss = fighter.getLosses();
			loss ++;
			fighter.setLosses(loss);
		}
	}
}
