package DataStorage;
import Singletons.ApplicationState;
import Singletons.User;
public class RefundRequest {
	public int transaction_id;
	public RefundRequest(int transact_id) {
		this.transaction_id = transact_id;
	}
	public boolean save() {
		if(User.getInstance() != null && User.getInstance().email == getTransaction().user.email) {
			ApplicationState.refund_requests.add(this);
			return true;
		}
		return false;
	}
	public Transaction getTransaction() {
		return ApplicationState.transactions.get(transaction_id);
	}
	
	public boolean approve() {
		Transaction transaction = getTransaction();
		User user = transaction.user;
		boolean response = transaction.payMethod.refund(user.getWallet(), transaction.paidAmount);
		if(response)
			ApplicationState.refund_requests.remove(this);
		else
			System.out.println("Unable to refund payment");
		return response;
	}
	public void reject() {
		ApplicationState.refund_requests.remove(this);
	}
}
