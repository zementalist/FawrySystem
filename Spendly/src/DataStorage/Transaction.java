package DataStorage;

import Models.PaymentMethod;
import Models.Service;
import Models.ServiceProvider;
import Singletons.ApplicationState;
import Singletons.User;

public class Transaction {
	public User user;
	public ServiceProvider provider;
	public PaymentMethod payMethod;
	public double paidAmount;
	public static int transactions_counter = 0;
	public int transaction_id;
	public Transaction(ServiceProvider provider, PaymentMethod payMethod) {
		this.user = User.getInstance();
		this.provider = provider;
		this.payMethod = payMethod;
		this.paidAmount = user.getWallet().getRecentTransactAmount();
		this.transaction_id = ++transactions_counter;
	}
	
	public void AddToContext() {
		ApplicationState.transactions.add(this);
	}
}
