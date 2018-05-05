Code Review comment/changes:

1. Framework Related:
	a.Create common package for data setup repository(commonutil) were added the common methods.
	b.Create Package for Reporting purpose(Listeners+Extend report).
	c.Added screen shot method on failure test.
	d.Use @BeforeMethod and @afterMethod annotations to initialization/Open connection and close connection purpose.
	e.Added Maven dependencies.

2. class SignInTest : 
		a.code added for navigate to frame

3. Class FlightBookingTest:
		a. changed the locator value from 'toTag' to 'ToTag'.

4. Class HotelBookingTest : 
		a. PageFactory.initElements(driver, this) code added.
		b. Datepicker code added.

5. Class Listeners :
		a. added listeners and Extend report code.
			