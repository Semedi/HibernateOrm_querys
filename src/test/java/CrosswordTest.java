import es.ucm.abd.practica2.model.Crucigrama;
import es.ucm.abd.practica2.model.Definicion;


public class CrosswordTest extends CrosswordTestBase<Crucigrama, Definicion>{

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected AbstractCrosswordFacade buildFacade() {
		// TODO Auto-generated method stub
		
		CrosswordFacade f = new CrosswordFacade();
		
		
		return f;
	}

}
