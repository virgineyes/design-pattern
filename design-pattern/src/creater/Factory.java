package creater;

public class Factory {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Factory factory = new Factory();
		Adventurer warrior = factory.new WarriorCamp().traingAdventurer();
		Adventurer archer = factory.new ArcherCamp().traingAdventurer();
		warrior.attack();
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
			System.out.println("法師祝福");
		}
		
		public void addBlood() {
			System.out.println("補血");
		}
	}
	
	public interface TrainingCamp {
		public Adventurer traingAdventurer();
	}
	
	public class WarriorCamp implements TrainingCamp {
		@Override
		public Adventurer traingAdventurer() {	
			return new Factory().new Warrior();
		}
	}
	
	public class ArcherCamp implements TrainingCamp {
		@Override
		public Adventurer traingAdventurer() {
			return new Factory().new Archer();
		}
	}
}
