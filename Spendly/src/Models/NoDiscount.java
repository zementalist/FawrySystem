package Models;

public class NoDiscount extends Discount{

	public NoDiscount(Service service, float percentage) {
		super(service, 0);
	}


}
