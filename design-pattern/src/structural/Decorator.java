package structural;

public class Decorator {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Decorator dactory = new Decorator();
		Weapon sword = dactory.new Sword();
		Weapon fireSword = dactory.new FirePower(sword);
		Weapon holyFireArrow = dactory.new HolyPower(fireSword);
//		Weapon arrow = dactory.new Arrow();
//		Weapon holyArrow = dactory.new HolyPower(arrow);
		
		System.out.println("戰士攻擊");
		Adventurer warrior = dactory.new WarriorCamp().traingAdventurer(holyFireArrow);
		warrior.attack();
		
//		System.out.println("-------------------------------");
//		
//		System.out.println("弓箭手攻擊");
//		Adventurer archer = dactory.new ArcherCamp().traingAdventurer(holyArrow);
//		archer.attack();
	}

	public interface Weapon {
		
		public String getName();

		public int getHitPoint();

		public void attack();
	}

	public class Sword implements Weapon {
		private String name = "刀";
		private int hitPoint = 10;

		@Override
		public String getName() {
			return name;
		}

		@Override
		public int getHitPoint() {
			return hitPoint;
		}

		@Override
		public void attack() {
			System.out.println(name + "造成" + hitPoint + "點傷害");
		}
	}
	
	public class Arrow implements Weapon {
		private String name = "弓箭";
		private int hitPoint = 15;

		@Override
		public String getName() {
			return name;
		}

		@Override
		public int getHitPoint() {
			return hitPoint;
		}

		@Override
		public void attack() {
			System.out.println(name + "造成" + hitPoint + "點傷害");
		}
	}

	public abstract class WeaponDecorator implements Weapon {

		protected String name;
		protected int hitPoint;
		protected Weapon weapon;

		public WeaponDecorator(String name, int hitPoint, Weapon weapon) {
			this.name = name;
			this.hitPoint = hitPoint;
			this.weapon = weapon;
		}

		@Override
		public String getName() {
			return name + weapon.getName();
		}

		@Override
		public int getHitPoint() {
			return hitPoint + weapon.getHitPoint();
		}

		protected void special() {
			if (weapon instanceof WeaponDecorator) {
				WeaponDecorator deco = (WeaponDecorator) weapon;
				deco.special();
			}
		}

		public void attack() {
			System.out.println(getName() + "造成" + (hitPoint + weapon.getHitPoint()) + "點傷害");
			this.special();
		}

		public Weapon getWeapon() {
			return this.weapon;
		}
	}

	public class FirePower extends WeaponDecorator {

		public FirePower(Weapon weapon) {
			super("火", 5, weapon);
		}

		@Override
		protected void special() {
			super.special();
			System.out.println("特殊效果:火焰造成 5點傷害");
		}

	}

	public class HolyPower extends WeaponDecorator {

		public HolyPower(Weapon weapon) {
			super("聖", 3, weapon);
		}

		@Override
		protected void special() {
			super.special();
			System.out.println("特殊效果:回復5點HP");
		}

	}

	public interface Adventurer {
		void attack();
	}

	public class Warrior implements Adventurer {
		private Weapon weapon;

		public Warrior(Weapon weapon) {
			this.weapon = weapon;
		}

		@Override
		public void attack() {
			weapon.attack();
		}
	}

	public class Archer implements Adventurer {
		private Weapon weapon;

		public Archer(Weapon weapon) {
			this.weapon = weapon;
		}

		@Override
		public void attack() {
			weapon.attack();
		}
	}

	public interface TrainingCamp {
		public Adventurer traingAdventurer(Weapon weapon);
	}

	public class WarriorCamp implements TrainingCamp {
		@Override
		public Adventurer traingAdventurer(Weapon weapon) {
			return new Decorator().new Warrior(weapon);
		}
	}

	public class ArcherCamp implements TrainingCamp {
		@Override
		public Adventurer traingAdventurer(Weapon weapon) {
			return new Decorator().new Archer(weapon);
		}
	}
}
