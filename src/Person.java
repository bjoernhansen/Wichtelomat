import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Person
{
    private static List<Person> personen = new ArrayList<>(6);
    
    private int id;
    private String name;
    private Person partner = null;
    private Person beschenktePerson = null;
    private Person beschenktVon = null;
    private Person zuletztBeschenktePerson = null;
    private Person zuletztBeschenktVon = null;
    
    private List<Person> ehemaligeBeschenkte = new ArrayList<>();
    private List<Person> ehemaligeBeschenkende = new ArrayList<>();
    private List<Person> beschenkbarePersonen = new ArrayList<>();
    
    
    Person(String name)
    {
        this.name = name;
        personen.add(this);
        this.id = (int)(Math.random()*Integer.MAX_VALUE);
    }
    
    
    
    
    
    private Person getBeschenktePerson()
    {
        return beschenktePerson;
    }
    
    void setBeschenktePerson(Person beschenktePerson)
    {
        this.beschenktePerson = beschenktePerson;
    }
    
    private Person getBeschenktVon()
    {
        return beschenktVon;
    }
    
    void setBeschenktVon(Person beschenktVon)
    {
        this.beschenktVon = beschenktVon;
    }
    
    public Person getZuletztBeschenktePerson()
    {
        return zuletztBeschenktePerson;
    }
    
    private Person getZuletztBeschenktVon()
    {
        return zuletztBeschenktVon;
    }
    
    void setZuletztBeschenktVon(Person zuletztBeschenktVon)
    {
        this.zuletztBeschenktVon = zuletztBeschenktVon;
        zuletztBeschenktVon.zuletztBeschenktePerson = this;
    }
    
    long getId()
    {
        return id;
    }
    
    String getName()
    {
        return name;
    }
    
    private Person getPartner()
    {
        return partner;
    }
    
 
    
    
    
    void setPartner(Person partner)
    {
        this.partner = partner;
        partner.partner = this;
    }
    
    static List<Person> getPersonen()
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
    
    boolean darfBeschenken(Person beschenktePerson)
    {
        return this != beschenktePerson
            && this.getBeschenktePerson() == null
            && this.getPartner() != beschenktePerson
            && beschenktePerson.getBeschenktVon() == null
            && this != beschenktePerson.getBeschenktePerson()
            && beschenktePerson.getZuletztBeschenktVon() != this;
    }
}