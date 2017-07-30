package main;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class TestIdProject {
	private Utility utility;

	@BeforeClass
	public void initTest() {
		utility = new Utility();
	}

	@Test
	public void testGenerateIds() {
		int expectedStatusCode = 200;
		int howMany = 10;
		Assert.assertEquals(utility.generateIds(howMany).getStatusCode(), expectedStatusCode);
	}

	@BeforeGroups(groups = "add")
	public void beforeAddIds() {
		String randomId = "A";
		utility.updateIds(null, randomId);
		utility.deleteId(null, randomId);
	}

	@Test(groups = "add")
	public void testAddIds() {
		String[] addWith = { "A", "B" };
		int expectedStatusCode = 201;
		Assert.assertEquals(utility.initIds(null, addWith).getStatusCode(), expectedStatusCode);
	}

	@Test
	public void testReadIds() {
		int expectedStatusCode = 200;
		Assert.assertEquals(utility.getIds().getStatusCode(), expectedStatusCode);
	}

	@Test
	public void testUpdateIds() {
		String[] updateWith = { "X", "Z" };
		int expectedStatusCode = 202;
		Assert.assertEquals(utility.updateIds(null, updateWith).getStatusCode(), expectedStatusCode);
	}

	@BeforeGroups(groups = "delete")
	public void beforeDelete() {
		String deleteMe = "D";
		utility.updateIds(deleteMe);
	}

	@Test(groups = "delete")
	public void testDeleteId() {
		int expectedStatusCode = 200;
		String deleteMe = "D";
		Assert.assertEquals(utility.deleteId(null, deleteMe).getStatusCode(), expectedStatusCode);
	}

	@Test
	public void testAddIdsWhenIdsAlreadyExist() {
		String[] addWith = { "A", "B" };
		int expectedStatusCode = 400;
		Assert.assertEquals(utility.initIds(null, addWith).getStatusCode(), expectedStatusCode);
	}

	@Test(groups = "add")
	public void testAddIdsWithoutJsonBody() {
		int expectedStatusCode = 415;
		int statusCode = utility.initIds(Utility.WITHOUT_BODY, "").getStatusCode();
		Assert.assertEquals(statusCode, expectedStatusCode);
	}

	@Test(groups = "add")
	public void testAddIdsWithInvalidJson() {
		int expectedStatusCode = 400;
		int statusCode = utility.initIds(Utility.WITH_INVALID_BODY, "").getStatusCode();
		Assert.assertEquals(statusCode, expectedStatusCode);
	}

	@Test
	public void testAddIdsWithEmptyArray() {
		int expectedStatusCode = 400;
		int statusCode = utility.initIds(Utility.WITH_EMPTY_ARRAY, "").getStatusCode();
		Assert.assertEquals(statusCode, expectedStatusCode);
	}

	@Test
	public void testUpdateIdsWithoutJsonBody() {
		int expectedStatusCode = 415;
		Assert.assertEquals(utility.updateIds(Utility.WITHOUT_BODY, "").getStatusCode(), expectedStatusCode);
	}

	@Test
	public void testUpdateIdsWithInvalidJson() {
		int expectedStatusCode = 400;
		Assert.assertEquals(utility.updateIds(Utility.WITH_INVALID_BODY, "").getStatusCode(), expectedStatusCode);
	}

	@Test
	public void testUpdateIdsWithEmptyArray() {
		int expectedStatusCode = 400;
		Assert.assertEquals(utility.updateIds(Utility.WITH_EMPTY_ARRAY, "").getStatusCode(), expectedStatusCode);
	}

	@Test
	public void testDeleteIdWithJsonBody() {
		int expectedStatusCode = 400;
		String deleteMe = "D";
		Assert.assertEquals(utility.deleteId(Utility.DELETE_WITH_BODY, deleteMe).getStatusCode(), expectedStatusCode);
	}

	@Test(groups = "delete")
	public void testDeleteIdWithUnknownId() {
		int expectedStatusCode = 400;
		String deleteMe = "UNKNOWN";
		Assert.assertEquals(utility.deleteId(null, deleteMe).getStatusCode(), expectedStatusCode);
	}

	@Test(groups = "add")
	public void testReadIdsWhenNonExist() {
		int expectedStatusCode = 400;
		Assert.assertEquals(utility.getIds().getStatusCode(), expectedStatusCode);
	}

	@Test
	public void testGenerateToMuchIds() {
		int expectedStatusCode = 200;
		int howMany = 99999999;
		utility.generateIds(howMany);
		Assert.assertEquals(utility.generateIds(howMany).getStatusCode(), expectedStatusCode);
	}
}