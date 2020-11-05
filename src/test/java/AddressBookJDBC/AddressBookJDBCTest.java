package AddressBookJDBC;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.sql.address.*;
import com.sql.address.AddressBookService.IOService;


public class AddressBookJDBCTest {
	@Test
    public void givenEmpPayrollDataInDB_ShouldMatchEmpCount() {
    	AddressBookService service = new AddressBookService();
    	List<AddressBookData> addList = service.readAddressBookData(IOService.DB_IO);
    	Assert.assertEquals(9, addList.size());
    }
	
	@Test 
	public void givenNewCity_WhenUpdated_shouldMatchWithDB() {
		AddressBookService service = new AddressBookService();
		service.readAddressBookData(IOService.DB_IO);
		service.updateContactsCity("abc", "btd");
		boolean result = service.checkAddressBookDataInSyncWithDB("avs", "btd");
		Assert.assertTrue(result);
	}
	
	@Test
	public void givenContactsData_WhenCountByCity_ShouldReturnProperValue() {
		AddressBookService service = new AddressBookService();
		service.readAddressBookData(IOService.DB_IO);
		Map<String, Integer> countContactsByCity = service.readCountContactsByCity(IOService.DB_IO);
		Assert.assertTrue(countContactsByCity.get("patiala").equals(1));
	}

	@Test
	public void givenContactsData_WhenCountByState_ShouldReturnProperValue() {
		AddressBookService service = new AddressBookService();
		service.readAddressBookData(IOService.DB_IO);
		Map<String, Integer> countContactsByState = service.readCountContactsByState(IOService.DB_IO);
		Assert.assertTrue(countContactsByState.get("pnb").equals(6));
	}
	
	@Test
	public void givenNewContact_WhenAdded_ShouldSyncWithDB() {
		AddressBookService service = new AddressBookService();
		service.readAddressBookData(IOService.DB_IO);
		service.addContact(20, "def", "ghi", "12345678 street", "gurgaon", "Hr", "3719331", "8888888888", "def@gmail.com");
		boolean result = service.checkAddressBookDataInSyncWithDB("def", "ghi");
		Assert.assertTrue(result);
	}
}