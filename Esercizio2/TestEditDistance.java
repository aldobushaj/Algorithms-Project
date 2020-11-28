

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestEditDistance {

	String sTest = "test";
	String sTesting = "testing";
	String sRest = "rest";
	String sEmpty = "";
	EditDistance edCanc = new EditDistance(sTest, sTesting);
	EditDistance edIns = new EditDistance(sTesting, sTest);
	EditDistance edCancIns = new EditDistance(sTest, sRest);
	EditDistance edEmpty = new EditDistance(sTest, sEmpty);

	@Test
	//Test metodo dedit_distance_rec in caso di cancellazione Testing--->Test -3 caratteri
	public void testEditDistanceRecCanc() {
		assertEquals(edCanc.edit_distance_rec(sTest, sTesting) == 3, true);
	}

	@Test
	//Test metodo dedit_distance_rec in caso di inserimento Test--->Testing +3 caratteri
	public void testEditDistanceRecIns() {
		assertEquals(edIns.edit_distance_rec(sTesting, sTest) == 3, true);
	}

	@Test
	//Test metodo dedit_distance_rec in caso di modifica  Test--->Rest 2 operazioni 
	public void testEditDistanceRecCancIns() {
		assertEquals(edCancIns.edit_distance_rec(sTest, sRest) == 2, true);
	}

	@Test
	//Test metodo dedit_distance_rec in caso di comparazione con stringa vuota 
	public void testEditDistanceRecEmpty() {
		assertEquals(edEmpty.edit_distance_rec(sTest, sEmpty) == 4, true);
	}

	@Test
	//Test metodo dedit_distance_recdyn in caso di cancellazione Testing--->Test -3 caratteri
	public void testEditDistanceRecDynCanc() {
		assertEquals(edCanc.edit_distance_recdyn(0, 0) == 3, true);
	}

	@Test
	//Test metodo dedit_distance_recdyn in caso di cancellazione Test--->Testing +3 caratteri

	public void testEditDistanceRecDynIns() {
		assertEquals(edIns.edit_distance_recdyn(0, 0) == 3, true);
	}

	@Test
	//Test metodo dedit_distance_recdyn in caso di modifica  Test--->Rest 

	public void testEditDistanceRecDynCancIns() {
		assertEquals(edCancIns.edit_distance_recdyn(0, 0) == 2, true);
	}

	@Test
	//Test metodo dedit_distance_recdyn in caso di stringa vuota
	public void testEditDistanceRecDynEmpty() {
		assertEquals(edEmpty.edit_distance_recdyn(0, 0) == 4, true);
	}

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(TestEditDistance.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		System.out.println(result.wasSuccessful());
	}

}
