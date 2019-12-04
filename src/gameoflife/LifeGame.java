package gameoflife;

public class LifeGame {

	public static void main(String[] args) {
		LifeView view = new LifeView();
		LifeModel model = new LifeModel();
		LifeController controller = new LifeController(view, model);
	}

}
