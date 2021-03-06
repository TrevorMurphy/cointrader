package org.cryptocoinpartners.schema;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Connects an Owner to a Portfolio
 *
 * @author Tim Olson
 */
@Entity
public class Stake extends EntityBase {

	public Stake(Owner owner, BigDecimal stake, Portfolio portfolio) {
		this.owner = owner;
		this.stake = stake;
		this.portfolio = portfolio;
	}

	@ManyToOne
	public Owner getOwner() {
		return owner;
	}

	@Column(precision = 30, scale = 15)
	public BigDecimal getStake() {
		return stake;
	}

	@ManyToOne
	public Portfolio getPortfolio() {
		return portfolio;
	}

	// JPA
	protected Stake() {
	}

	protected void setOwner(Owner owner) {
		this.owner = owner;
	}

	protected void setStake(BigDecimal stake) {
		if (stake.compareTo(BigDecimal.ZERO) < 0 || stake.compareTo(BigDecimal.ONE) > 0)
			throw new IllegalArgumentException("stake must be in range [0,1]: " + stake);
		this.stake = stake;
	}

	protected void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	private Owner owner;
	private BigDecimal stake;
	private Portfolio portfolio;
}
