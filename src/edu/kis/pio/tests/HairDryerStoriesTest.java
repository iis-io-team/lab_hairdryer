package edu.kis.pio.tests;

import org.junit.Assert;
import org.junit.Test;

import edu.kis.pio.devices.FavouriteHairDryer;
import edu.kis.pio.sockets.IEuropeanSocket;
import edu.kis.pio.sockets.IUKSocket;
import edu.kis.pio.sockets.SocketFinder;
import edu.kis.pio.triptouk.SocketProblemHelper;

public class HairDryerStoriesTest {

	SocketFinder socketFinder = new SocketFinder();
	FavouriteHairDryer hairDryer = new FavouriteHairDryer();
	
	@Test
	public void testAtHome() {
		IEuropeanSocket socket = socketFinder.findEuropeanRegularSocket();
		hairDryer.getPlug().plugIntoEuropeanSocket(socket);
		hairDryer.turnOn();
		Assert.assertTrue(hairDryer.isWorking());
		Assert.assertFalse(hairDryer.isBroken());
	}
	
	@Test
	public void testAtStage() {
		IEuropeanSocket socket = socketFinder.findEuropeanPowerSocket();
		hairDryer.getPlug().plugIntoEuropeanSocket(socket);
		hairDryer.turnOn();
		Assert.assertFalse(hairDryer.isWorking());
		Assert.assertTrue(hairDryer.isBroken());
	}
	
	@Test
	public void testAtUKHome() {
		IUKSocket uksocket = socketFinder.findUKRegularSocket();
		SocketProblemHelper.plugEuropeanDeviceIntoUKSocket(hairDryer, uksocket);
		hairDryer.turnOn();
		Assert.assertTrue(hairDryer.isWorking());
		Assert.assertFalse(hairDryer.isBroken());
	}
	
	@Test
	public void testAtUKStage() {
		IUKSocket uksocket = socketFinder.findUKPowerSocket();
		SocketProblemHelper.plugEuropeanDeviceIntoUKSocket(hairDryer, uksocket);
		hairDryer.turnOn();
		Assert.assertFalse(hairDryer.isWorking());
		Assert.assertTrue(hairDryer.isBroken());
	}
	
}
