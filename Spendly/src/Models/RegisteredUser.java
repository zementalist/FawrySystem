package Models;

import java.util.Optional;
import java.util.Random;

import Singletons.ApplicationState;
import Singletons.User;

public class RegisteredUser extends User {
	
	private RegisteredUser(String username, String email, String password){
		super(username, email, password);
	}
	
	
	public static User register(String username, String email, String password, String password_confirm) {
		Optional<User> userExistance = ApplicationState.registered_users.stream().filter(c -> email.equals(c.email)).findFirst();

		if(!username.isEmpty() && !email.isEmpty()
				&& !password.isEmpty() && password_confirm.equals(password)
				&& userExistance.isEmpty()
				) {
			
			User user = new RegisteredUser(username, email, password);
			int random_balance_amount = new Random().nextInt(100, 10000);
			Wallet wallet = new Wallet("12345689", random_balance_amount);
			user.setWallet(wallet);
			user.save();
			return user;
		}
		return null;
	}
	

}
