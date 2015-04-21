package org.rubik.sandbox.guava;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

public class Product {
	private Long id;             // 商品标示
	private String name;         // 商品名称
	private Integer inventory;   // 商品库存数
	private boolean frozen;      // 是否冻结

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public boolean isFrozen() {
		return frozen;
	}

	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}

	public boolean equals(Product product) {
		return Objects.equal(this, product);
	}

	public int hashCode() {
		return Objects.hashCode(id, name, inventory, frozen);
	}

	public String toString() {
		return Objects.toStringHelper(this)
				.omitNullValues()
				.add("id", id)
				.add("name", name)
				.add("inventory", inventory)
				.add("frozen", frozen)
				.toString();
	}

	public int compareTo(Product p) {
		return ComparisonChain.start()
				.compare(this.id, p.getId())
				.compare(this.getName(), p.getName())
				.compare(this.inventory, p.getInventory())
				.compare(this.isFrozen(), p.isFrozen())
				.result();
	}
}
