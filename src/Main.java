import java.util.List;

public class Main
{
    
    
    public static void main(String[] args)
    {
        Person bjoern = new Person("Björn");
        Person tina = new Person("Tina");
        Person fynn = new Person("Fynn");
        Person annalice = new Person("Annalice");
        Person gunnar = new Person("Gunnar");
        Person carmen = new Person("Carmen");
    
        bjoern.setPartner(tina);
        fynn.setPartner(annalice);
        gunnar.setPartner(carmen);
        
        bjoern.setZuletztBeschenktVon(fynn, carmen, gunnar);
        fynn.setZuletztBeschenktVon(carmen, bjoern, tina);
        carmen.setZuletztBeschenktVon(bjoern, fynn, annalice);
        gunnar.setZuletztBeschenktVon(annalice, tina, fynn);
        annalice.setZuletztBeschenktVon(tina, gunnar, bjoern);
        tina.setZuletztBeschenktVon(gunnar, annalice, carmen);
        
        List<Person> personen = Person.getPersonen();
        
        for(Person schenkender : personen)
        {
            for(Person beschenkter : personen)
            {
                if( schenkender.darfBeschenken(beschenkter))
                {
                    schenkender.setBeschenktePerson(beschenkter);
                    beschenkter.setBeschenktVon(schenkender);
                    break;
                }
            }
            
        }
        personen.forEach(System.out::println);
    
        System.out.println();
        
        for(Person p : personen)
        {
            System.out.println(String.format("%s ( %d )", p.getName(), p.getId()));
        }
    }
}