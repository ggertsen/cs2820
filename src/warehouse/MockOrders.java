package warehouse;

import java.util.*;

public class MockOrders implements Orders, Tickable, Picker {
	
  private Inventory I;
  private LinkedList<Order> orderqueue;
  private SimRandom randomsource;
  
  /**
   * @author Ted Herman
   * @param Inventory component, needed to create sensible
   * new Order to add to queue of work to do (for testing,
   * it just creates a few initial orders)
   * @param rand is a SimRandom, for predictable randomness
   */
  public MockOrders(Inventory I, SimRandom rand) {
	this.I = I; // so we can later call upon Inventory methods
	randomsource = rand;
	orderqueue = new LinkedList<Order>();
	for (int i=0;i<3;i++) {
	  orderqueue.addLast(makeOrder());
	  }
    }
  
  /**
   * @author Ted Herman
   * 
   * This tick method would be where Orders does the 
   * real work (see design README)
   * 
   */
  public void tick(int count) {
    }

  /**
   * @author Ted Herman
   * Picker event notify(robot), not finished.
   */
  public void notify(Robot r) { };
  
  /**
   * @author Ted Herman
   * creates a random Order
   */
  public Order makeOrder() {
	String addr = new Address(randomsource).randomAddress();
	OrderItem[] orderitems = new OrderItem[1+randomsource.nextInt(2)];
	for (int i=0;i<orderitems.length;i++) {
	  orderitems[i] = new OrderItem(I.randomItem());
	  }
	return new Order(addr,orderitems);
    }
  }

/**
 * 
 * @author Ted Herman
 * A local class just to supply addresses
 * for orders in the Orders component
 *
 */
class Address {
	
  SimRandom R;
  
  /**
   * @author Ted Herman
   * @param R is SimRandom object, so that all the random
   * choices by methods of Address will be predictably random
   */
  public Address(SimRandom R) {
	this.R = R;
    }

  /**
   * @author Ted Herman
   * @param R is a SimRandom for predictability in randomness
   * @return String containing a random address for an order
   */
  public String randomAddress() {
	this.R = R;
	String FirstName = randomFirstName();
	String LastName = randomLastName();
	String StreetNumber = new Integer(randomStreetNumber()).toString();
	String StreetName = randomStreetName();
	String City = randomCity();
	String State = randomState();
	String ZipCode = randomZip();
	String Address = FirstName + " " +
	  LastName + "\n" + StreetNumber + " " +
	  StreetName + "\n" + City + " " + State + ZipCode;
	return Address;
    }
  /**
   * @author Ted Herman
   * @return a string containing a random street name
   */
  private String randomStreetName() {
	final String[] baseNames = {"Park Street",
			"Main Street", "Washington Boulevard",
			"Third Street", "Park Road",
			"Maple Street", "Hill Road"};
	return baseNames[R.nextInt(baseNames.length)];
    }
  /**
   * @author Ted Herman
   * @return an integer in the range [1,999] for street address
   */
  private int randomStreetNumber () {
	return 1+R.nextInt(998);
    }
  /**
   * @author Ted Herman
   * @return a random first name for an address
   */
  private String randomFirstName() {
	final String[] baseFirstNames = {"Dakota", "Emma",
			"Julian", "Nigella", "Will", "Asti", "Lee",
			"Pat", "Mavis", "Jerome", "Lilly", "Tess"};
	return baseFirstNames[R.nextInt(baseFirstNames.length)];
	}
  /**
   * @author Ted Herman
   * @return a random last name for an address
   */
  private String randomLastName() {
	final String[] baseLastNames = {"Parker","Mason",
				"Smith","Wright","Jefferson","Iqbal",
				"Owens","Lafleur","Metselen","Vinceroy",
				"Saville","Troitski","Andrews"};
	return baseLastNames[R.nextInt(baseLastNames.length)];
    }
  /**
   * @author Ted Herman
   * @return a random city name
   */
  private String randomState() {
	final String[] baseState = {"IA","NE","MO",
				"IL","KS","MN","SD","AR","OK","TX"};
	return baseState[R.nextInt(baseState.length)];
    }
  /**
   * @author Ted Herman
   * @return a random state code (two letters)
   */
  private String randomCity() {
	final String[] baseCity = {"Springfield","Clinton",
				"Madison","Franklin","Chester","Marion",
				"Greenville","Salem","Anytown","Hope"};
	return baseCity[R.nextInt(baseCity.length)];
    }
  /**
   * @author Ted Herman
   * @return a random state code (two letters)
   */
  private String randomZip() {
    String ZipCode = "";
    for (int i=0; i<6; i++) 
      ZipCode += "0123456789".charAt(R.nextInt(10));
    return ZipCode;
  }
}
