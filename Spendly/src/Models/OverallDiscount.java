package Models;

import Singletons.User;

public class OverallDiscount extends Discount {

	public OverallDiscount(Service service, double percentage) {
		super(service, percentage);
		// TODO Auto-generated constructor stub
	}
	public OverallDiscount(double percentage) {
		super(percentage);
	}

	@Override
	public double apply() {
		// If this is the first transaction for the user, apply discount
		if(User.getInstance().getWallet().getRecentTransactAmount() == 0) {
			return super.apply();
		}
		// else, return original service.cost
		return service.getCost();
	}
	
}
