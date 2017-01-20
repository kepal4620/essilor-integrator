package essilor.integrator.adapter.domain.optosys;

public class Order {

	private long objednavka;
	private long skupina;
	private long zakazka;

	public long getObjednavka() {
		return objednavka;
	}

	public void setObjednavka(long objednavka) {
		this.objednavka = objednavka;
	}

	public long getSkupina() {
		return skupina;
	}

	public void setSkupina(long skupina) {
		this.skupina = skupina;
	}

	public long getZakazka() {
		return zakazka;
	}

	public void setZakazka(long zakazka) {
		this.zakazka = zakazka;
	}

	private OrderItem rightItem;
	private OrderItem leftItem;

	public OrderItem getRightItem() {
		return rightItem;
	}

	public void setRightItem(OrderItem rightItem) {
		this.rightItem = rightItem;
	}

	public OrderItem getLeftItem() {
		return leftItem;
	}

	public void setLeftItem(OrderItem leftItem) {
		this.leftItem = leftItem;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Order[objednavka=").append(objednavka).append(", zakazka=")
				.append(zakazka).append(", skupina=").append(skupina)
				.append("]");
		return sb.toString();
	}
}
