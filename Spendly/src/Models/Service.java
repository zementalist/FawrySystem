package Models;

import Interfaces.IDiscountBehavior;
import Interfaces.Observer;
import Interfaces.Subject;
import Singletons.User;

public abstract class Service {
//	public ServiceProvider provider;
	public String name;
	private double cost;
	public IDiscountBehavior discountAction;
//	public Subject newDiscount;
	
	public double getCost() {
		return this.cost;
	}
	public Service(String name) {
//		this.provider = provider;
		this.name = name;
//		this.cost = cost;
		this.discountAction = new NoDiscount(this, 0);
	}
//	public Service(String name) {
//		this.name = name;
//		this.discountAction = new NoDiscount(this, 0);
//	}
	public void setDiscountBehavior(IDiscountBehavior discBehav) {
		this.discountAction = discBehav;
	}
//	public void setProvider(ServiceProvider serviceProvider) {
//		this.provider = serviceProvider;
//	}
	public void setCost(double cost) {
		this.cost = cost;
	}
//	public void serve(User user) {
//		double costAfterDiscount= discountAction.apply();
//		boolean is_success = provider.payMethod.execute(user.getWallet(), costAfterDiscount);
//		if(is_success)
//			System.out.println("User (" + user.username + ") has charged " + costAfterDiscount + "$ for " + this.name + " service, from " + this.provider.name);
//		else
//			System.out.println("Not Enough Balance!");
//	}
//	@Override
//	public void update(IDiscountBehavior discountAction) {
//		// TODO Auto-generated method stub
//		this.discountAction = discountAction;
//	}
	
}
