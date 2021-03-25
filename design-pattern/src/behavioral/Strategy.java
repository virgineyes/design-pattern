package behavioral;

public class Strategy {
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		Adventurer fireWarrio = TrainingCamp.createAdventurer("warrior");
		extracted(fireWarrio, new Strategy().new FireSkill());
		
		Adventurer iceArcher = TrainingCamp.createAdventurer("archer");
		extracted(iceArcher, new Strategy().new IceSkill());
	}

	private static void extracted(Adventurer adventurer, SkillStrategy skill) {
		adventurer.choiceStrategy(skill);
		adventurer.attack();
		adventurer.useSkill();
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
			System.out.println("(火傷害)");
		}
	}
	
	public class IceSkill implements SkillStrategy {
		@Override
		public void excute() {
			System.out.println("(冰傷害)");
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
			Strategy strategy = new Strategy();
			if ("warrior".equals(type)) {
				return strategy.new Warrior();
			} else if ("archer".equals(type)) {
				return strategy.new Archer();
			} else if ("priest".equals(type)) {
				return strategy.new Priest();
			}
			return null;
		}
	}
}
