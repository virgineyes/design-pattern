package creater;

public class SimpleFactory {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Adventurer warrio = TrainingCamp.createAdventurer("warrior");
		Adventurer archer = TrainingCamp.createAdventurer("archer");
		warrio.attack();
		archer.attack();
	}

	public interface Adventurer {
		void attack();
	}

	public class Warrior implements Adventurer {
		@Override
		public void attack() {
			System.out.println("戰士揮刀");
		}
	}

	public class Archer implements Adventurer {
		@Override
		public void attack() {
			System.out.println("弓箭手射箭");
		}
	}
	
	public class Priest implements Adventurer {

		@Override
		public void attack() {
			System.out.println("詛咒");
		}
		
		public void addBlood() {
			System.out.println("補血");
		}
	}

	public static class TrainingCamp {
		public static Adventurer createAdventurer(String type) {
			SimpleFactory factory = new SimpleFactory();
			if ("warrior".equals(type)) {
				return factory.new Warrior();
			} else if ("archer".equals(type)) {
				return factory.new Archer();
			} else if ("priest".equals(type)) {
				return factory.new Priest();
			}
			return null;
		}
	}
}
