import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Person
{
    private static List<Person> personen = new ArrayList<>(6);
    
    int id;
    private String name;
    private Person partner = null;
    private Person beschenktePerson = null;
    private Person beschenktVon = null;
    
    
    Person(String name)
    {
        this.name = name;
        personen.add(this);
        this.id = (int)(Math.random()*Integer.MAX_VALUE);
    }
    
    
    
    
    
    public Person getBeschenktePerson()
    {
        return beschenktePerson;
    }
    
    public void setBeschenktePerson(Person beschenktePerson)
    {
        this.beschenktePerson = beschenktePerson;
    }
    
    public Person getBeschenktVon()
    {
        return beschenktVon;
    }
    
    public void setBeschenktVon(Person beschenktVon)
    {
        this.beschenktVon = beschenktVon;
    }
    
    
    public long getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public Person getPartner()
    {
        return partner;
    }
    
 
    
    
    
    void setPartner(Person partner)
    {
        this.partner = partner;
        partner.partner = this;
    }
    
    public static List<Person> getPersonen()
    {
        shuffleArray(personen);
        return personen;
    }
    
    
    
    
    
    private static void shuffleArray(List<Person> ar)
    {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.size() - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            Person a = ar.get(index);
            ar.set(index, ar.get(i));
            ar.set(i, a);
        }
    }
    
    @Override
    public String toString()
    {
        return String.format("[%s beschenkt %s.]",
            this.name,
            this.beschenktePerson != null ? this.beschenktePerson.id : "niemanden");
    }
    
    public boolean darfBeschenken(Person beschenkt)
    {
        return this != beschenkt
            && this.getBeschenktePerson() == null
            && this.getPartner() != beschenkt
            && beschenkt.getBeschenktVon() == null
            && this != beschenkt.getBeschenktePerson();
    }
}