package com.booking.project;

import com.booking.project.unitteste.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * This class is used to test the implementation of the class {@link OperatiiDobanda}
 */
@SpringBootTest
class ProjectApplicationTests {
	@Mock //instantiaza el obiectul de mai jos, atunci cand nu ne intereseaza implementarea lui
	private InterfataDBOperations dbOperationsMock;
	//!!!!!!!!!!!!!!!!facem mock clasele de care depinde clasa pe care vrem noi sa o testam!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	private final OperatiiDobanda operatiiDobanda = new OperatiiDobanda();

	@Test
	void testDobandaMica() {
		int result = operatiiDobanda.calculDobanda(TipDobanda.MICA);
		int expectedResult = 5;
		assertEquals(expectedResult, result);
	}

	@Test
	void testDobandaMedie() {
		int result = operatiiDobanda.calculDobanda(TipDobanda.MEDIE);
		int expectedResult = 10;
		assertEquals(expectedResult, result);
	}

	@Test
	void testDobandaMare() {
		int result = operatiiDobanda.calculDobanda(TipDobanda.MARE);
		int expectedResult = 15;
		assertEquals(expectedResult, result);
	}

	@Test
	void testUserRisk() {
		User user = new User("Stefan", RiskType.MEDIUM);
		when(dbOperationsMock.getUser()).thenReturn(user);
		OperatiiDobanda operatiiDobanda = new OperatiiDobanda(dbOperationsMock);

		assertEquals(operatiiDobanda.calculDobanda(), 10);
		verify(dbOperationsMock).getUser(); //verifica ca o anumita metoda din mock s-a chemat in urma apelului de metoda
		//merita cand se trimite si un parametru in spate (la getUser())
	}
	//REGULA: un assert si un verify pe fiecare metoda. Daca nu (doar verify mai multe, nu assert-uri), trebuie justificat bine
}
