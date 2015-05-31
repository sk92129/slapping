package com.seankang.slapping.model;

import java.text.NumberFormat;

import com.seankang.slapping.view.View;

/**
 * Created by seankang on 5/16/2015.
 */
public class Fighter {
	private String name;
	private int heath;
	private int damage;
	private int attacks;
	private int dodge;
	private int critical;
	private int initiative;

	private int losses;
	private int wins;

	public Fighter() {
		losses = 0;
		wins=0;

	}
	
	public void sendToView(View view) {
		view.sendOutput(this.toString());
	}

	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("Name: ");
		sb.append(name);
		sb.append(" Health: ");
		sb.append(heath);
		sb.append(" Damage: ");
		sb.append(damage);
		sb.append(" Attacks: ");
		sb.append(attacks);
		sb.append(" Dodge: ");
		sb.append(dodge);
		sb.append(" Critical: ");
		sb.append(critical);
		sb.append(" Initiative: ");
		sb.append(initiative);

		sb.append(" Win Count: ");
		sb.append(wins);
		sb.append(" Loss Count: ");
		sb.append(losses);

		sb.append(" Win %: ");

		NumberFormat defaultFormat = NumberFormat.getPercentInstance();
		defaultFormat.setMinimumFractionDigits(1);
		double percent = calculateWinPercentage() ;
		sb.append(" " + defaultFormat.format(percent));

		return sb.toString();
	}

	private Double calculateWinPercentage() {
		double total = wins + losses;
		double value;
		if (total == 0){

			return Double.valueOf(0);
		} else {
			value = wins/total;
			return value ;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHeath() {
		return heath;
	}

	public void setHeath(int heath) {
		this.heath = heath;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getAttacks() {
		return attacks;
	}

	public void setAttacks(int attacks) {
		this.attacks = attacks;
	}

	public int getDodge() {
		return dodge;
	}

	public void setDodge(int dodge) {
		this.dodge = dodge;
	}

	public int getCritical() {
		return critical;
	}

	public void setCritical(int critical) {
		this.critical = critical;
	}

	public int getInitiative() {
		return initiative;
	}

	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}
}
