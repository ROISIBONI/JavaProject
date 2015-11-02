package boot;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import controller.Controller;
import controller.MyController;
import model.Model;
import model.MyClientHandler;
import model.MyModel;
import view.MyViewCLI;
import view.View;

// TODO: Auto-generated Javadoc
/**
 * The Class Run.
 */
public class Run {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Controller controller = new MyController();
		Model model = new MyModel(new MyClientHandler(),controller);
		View view = new MyViewCLI(new InputStreamReader(System.in),new OutputStreamWriter(System.out), controller);
		controller.setModel(model);
		controller.setView(view);
		
		view.start();
	}

}
