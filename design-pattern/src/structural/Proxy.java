package structural;

public class Proxy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WarriorUserProxy proxy = new Proxy().new WarriorUserProxy(new Proxy().new WarriorUser());
		Adventurer warrior = proxy.createUser();
		warrior.attack();
		warrior.useSkill();
	}
	
	public interface User {
		Adventurer createUser();
	}
	
	public class WarriorUser implements User {
	    public Adventurer createUser() {
			System.out.println("收到代理者的需求");
	    	return TrainingCamp.createAdventurer("warrior");
	    }
	}
	
	public class WarriorUserProxy implements User {
		private User user;

		public WarriorUserProxy(User user) {
			this.user = user;
		}

		public Adventurer createUser() {
			System.out.println("代理者協助 Camp 建立戰士");
			Adventurer warrior = user.createUser();
			System.out.println("設定火傷害");
			warrior.choiceStrategy(new Proxy().new FireSkill());
			return warrior;
		}
	}
	
	public abstract class Adventurer {
		SkillStrategy flightStrategy;
	    public void choiceStrategy(SkillStrategy strategy){
	        this.flightStrategy = strategy;
	    }
		void attack() {};
		void useSkill() { 
			this.flightStrategy.excute();
		};
	}
	
	public class Warrior extends Adventurer {
		@Override
		public void attack() {
			System.out.print("戰士揮刀");
		}
	}

	public class Archer extends Adventurer {
		@Override
		public void attack() {
			System.out.print("弓箭手射箭");
		}
	}
	
	public class Priest extends Adventurer {

		@Override
		public void attack() {
			System.out.println("詛咒");
		}
		

	}
	
	public interface SkillStrategy {
		void excute();
	}
	
	public class FireSkill implements SkillStrategy {
		@Override
		public void excute() {
			System.out.println("(火焰傷害)");
		}
	}
	
	public class IceSkill implements SkillStrategy {
		@Override
		public void excute() {
			System.out.println("(冰凍傷害)");
		}
	}
	
	public class BloodSkill implements SkillStrategy {
		@Override
		public void excute() {
			System.out.println("(補血)");
		}
	}
	
	public static class TrainingCamp {
		public static Adventurer createAdventurer(String type) {
			Proxy proxy = new Proxy();
			if ("warrior".equals(type)) {
				return proxy.new Warrior();
			} else if ("archer".equals(type)) {
				return proxy.new Archer();
			} else if ("priest".equals(type)) {
				return proxy.new Priest();
			}
			return null;
		}
	}
}
