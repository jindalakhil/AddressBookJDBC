package AddressBookJDBC;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.sql.address.*;
import com.sql.address.AddressBookService.IOService;


public class AddressBookJDBCTest {
	@Test
    public void givenEmpPayrollDataInDB_ShouldMatchEmpCount() {
    	AddressBookService service = new AddressBookService();
    	List<AddressBookData> addList = service.readAddressBookData(IOService.DB_IO);
    	Assert.assertEquals(6, addList.size());
    }
}